package med.spring_angular.dtos;

import lombok.*;
import med.spring_angular.entities.PaymentStatus;
import med.spring_angular.entities.PaymentType;


import java.time.LocalDate;

 @AllArgsConstructor @NoArgsConstructor @Builder @Getter
@Setter
@ToString
public class PaymentDTO {
    private Long  id;
    private LocalDate date;
    private Double amount;
    private PaymentType type;
    private PaymentStatus status;
}
