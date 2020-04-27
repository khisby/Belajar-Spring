package id.khisoft.javaspring.Entity;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Employee {
    private Long employeeId;

    @Size(min=2, max=15, message="Last name min 2 and max 15 char")
    private String lastName;
    private String firstName;
    private String address;
    private String city;

}
