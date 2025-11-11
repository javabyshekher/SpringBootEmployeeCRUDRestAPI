package in.ashokit.api;

import in.ashokit.entity.Employee;
import in.ashokit.exception.ResourceNotFoundException;
import in.ashokit.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    @Autowired
    EmployeeService service;

    //produces : defines the media type that the method can
    //           produce and send it back to the client in the response body

    @GetMapping(value = "/employee/{id}",
                produces = {"application/xml", "application/json"})
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id) {
        Employee e = service.fetchById(id);
        if (e != null) {
            return new ResponseEntity<Employee>(e, HttpStatus.OK);
        }
        else {
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }
    }

    @GetMapping(value = "/employees",
                produces = {"application/json", "application/xml"} )
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> list = service.fetchAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //consumes :  defines the media type that the method can
    //            consume from the client in the request body
    @PostMapping(value = "/save",
                 consumes = {"application/json", "application/xml"},
                 produces = {"application/json", "application/xml"})
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee e) {
        try {
            Employee emp = service.save(e);
            return new ResponseEntity<>(emp, HttpStatus.CREATED);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            value = "/update",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"}
    )
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee e) {

            Employee empFromDB = service.fetchById(e.getEmpno());
            if (empFromDB != null) {
                Employee emp = service.save(e);
                return new ResponseEntity<>(emp, HttpStatus.OK);
            }
            else {
                throw new ResourceNotFoundException("Employee not found with id " + e.getEmpno());
            }
    }

    @PatchMapping(
            value = "/update/{id}",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"}
    )
    public ResponseEntity<Employee> partialUpdate(@PathVariable Integer id, @RequestBody Map<String, Object> map) {
        Employee empFromDB = service.fetchById(id);
        if (empFromDB != null) {
            Employee emp = service.updateBySal(id, (double)map.get("sal") );
            return new ResponseEntity<>(emp, HttpStatus.OK);
        }
        else  {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        Employee empFromDB = service.fetchById(id);
        if (empFromDB != null) {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }

    }
}
