package med.spring_angular.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity @AllArgsConstructor @NoArgsConstructor @Builder @Getter
@Setter
@ToString
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;
    private LocalDate date;
    private Double amount;
    private PaymentType type;
    private PaymentStatus status;
    private String file;
    @ManyToOne
    private Student student;
}
