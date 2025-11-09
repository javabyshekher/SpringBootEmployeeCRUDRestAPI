package in.ashokit.repository;

import in.ashokit.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "UPDATE EMPLOYEES SET SAL = ? WHERE EMPNO = ?", nativeQuery=true)
    @Modifying
    @Transactional
    void updateSal(double sal, int empno);
}
