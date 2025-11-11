package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMPLOYEES")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    @Id
//    @NotNull(message = "empno is required")
    @NotNull(message = "{emp.empno.not.null}")
    @Positive(message = "{emp.empno.positive}")
    private Integer empno;

    @NotBlank(message = "{emp.ename.not.blank}")
    private String ename;

    @NotNull(message = "{emp.sal.not.null}")
    @Positive(message = "{emp.sal.positive}")
    private Double sal;
}
