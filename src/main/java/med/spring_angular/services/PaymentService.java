package med.spring_angular.services;

import lombok.AllArgsConstructor;
import med.spring_angular.entities.Payment;
import med.spring_angular.entities.PaymentStatus;
import med.spring_angular.entities.PaymentType;
import med.spring_angular.entities.Student;
import med.spring_angular.repository.PaymentRepository;
import med.spring_angular.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class PaymentService {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    public Payment savePayment( MultipartFile file , LocalDate date ,
                               double amount , PaymentType type, String studentCode ) throws IOException {

        Path folderPath = Paths.get(System.getProperty("user.home"),"fs-data","payments");
        if (!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        String fileName = UUID.randomUUID().toString();
        Path folePath = Paths.get(System.getProperty("user.home"),"fs-data","payments",fileName+".pdf");
        Files.copy(file.getInputStream(),folePath);
        Student student=studentRepository.findByCode(studentCode);
        Payment payment=Payment.builder()
                .date(date).type(type).student(student)
                .amount(amount)
                .file(folePath.toUri().toString())
                .status(PaymentStatus.CREATED)
                .build();
        return paymentRepository.save(payment);
    }

    public Payment updatePaymentStatus( Long  id, PaymentStatus status) {
        Payment payment = paymentRepository.findById(id).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    public byte[] getPaymentFile( Long paymentId) throws IOException {
        Payment payment =paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));

    }
}
