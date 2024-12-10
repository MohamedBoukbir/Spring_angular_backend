package med.spring_angular.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Builder(toBuilder = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student1 {
    @Id
    private String id ;
    private String firstName;
    private String lastName;
    @Column(unique=true)
    private String code;
    private String programId;
    private String photo;
}
