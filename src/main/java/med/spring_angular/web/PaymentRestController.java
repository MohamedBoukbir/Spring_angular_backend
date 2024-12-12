package med.spring_angular.web;

import lombok.AllArgsConstructor;
import med.spring_angular.dtos.PaymentDTO;
import med.spring_angular.entities.*;
import med.spring_angular.repository.PaymentRepository;
import med.spring_angular.repository.StudentRepository;
import med.spring_angular.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;
@GetMapping(path="/payments")
public List<Payment> getAllPayments() {
    return paymentRepository.findAll();
}

    @GetMapping(path="/student/{code}/payments")
    public List<Payment> paymentsByStudent(@PathVariable String  code) {
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping(path="/payments/byStatus")
    public List<Payment> paymentsByStatus(@RequestParam PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    @GetMapping(path="/payments/byType")
    public List<Payment> paymentsByType(@RequestParam PaymentType type) {
        return paymentRepository.findByType(type);
    }

@GetMapping(path="/payments/{id}")
public Payment getPaymentById(@PathVariable Long id) {
    return paymentRepository.findById(id).get();
}

@GetMapping(path="/students")
public List<Student> allStudent() {
    return studentRepository.findAll();
}
@GetMapping(path="/student/{code}")
public Student getStudentById(@PathVariable String  code) {
    return studentRepository.findByCode(code);
}

@GetMapping(path="/stdents/byProgramId")
public List<Student> getStudentByProgramId(@RequestParam String programId) {
    return studentRepository.findByProgramId(programId);
}
@PutMapping(path="payments/{id}")
public Payment updatePaymentStatus(@PathVariable Long  id,@RequestParam PaymentStatus status) {
    return paymentService.updatePaymentStatus(id, status);
}

@PostMapping(value = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public Payment savePayment(@RequestParam("file") MultipartFile file , PaymentDTO  paymentDTO) throws IOException {

    return this.paymentService.savePayment(file,paymentDTO);
}

@GetMapping(path="/payment/{paymentId}/file",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
    return paymentService.getPaymentFile(paymentId);

}

}
