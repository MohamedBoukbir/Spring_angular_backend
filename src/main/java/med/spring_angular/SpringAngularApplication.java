package med.spring_angular;

import med.spring_angular.entities.Payment;
import med.spring_angular.entities.PaymentStatus;
import med.spring_angular.entities.PaymentType;
import med.spring_angular.entities.Student;
import med.spring_angular.repository.PaymentRepository;
import med.spring_angular.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SpringAngularApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAngularApplication.class, args);


    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        return args -> {
            studentRepository.save(Student.builder()
                    .id(UUID.randomUUID().toString())
                    .firstName("Mohamed").code("123444").programId("SDIA")
                    .build());
            studentRepository.save(Student.builder()
                    .id(UUID.randomUUID().toString())
                    .firstName("Imane").code("123411").programId("SDIA")
                    .build());
            studentRepository.save(Student.builder()
                    .id(UUID.randomUUID().toString())
                    .firstName("Yasmine").code("123422").programId("GLPC")
                    .build());
            studentRepository.save(Student.builder()
                    .id(UUID.randomUUID().toString())
                    .firstName("Najat").code("123433").programId("BDCC")
                    .build());
            PaymentType[] paymentTypes = PaymentType.values();
            Random random = new Random();
            studentRepository.findAll().forEach(st->{
                int index;
                for (int i=0 ;i<10;i++){
                    index=random.nextInt(paymentTypes.length);
                    Payment payment=Payment.builder()
                            .amount(1000+(Math.random()*20000))
                            .type(paymentTypes[index])
                            .status(PaymentStatus.CREATED)
                            .date(LocalDate.now())
                            .student(st)
                            .build();
                    paymentRepository.save(payment);
                }
            });

        };
    }
}
