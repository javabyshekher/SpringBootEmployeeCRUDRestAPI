package in.ashokit.service;

import in.ashokit.entity.Employee;
import in.ashokit.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repo;

    public Employee save(Employee employee) {
        return repo.save(employee);
    }

    public Employee fetchById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Employee> fetchAll() {
        return repo.findAll();
    }

    public Employee updateBySal(int empno, double sal) {
        repo.updateSal(sal, empno);
        return fetchById(empno);
    }

}
