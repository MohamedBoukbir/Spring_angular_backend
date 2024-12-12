import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {StudentsService} from "../services/students.service";
import {Payment} from "../model/students.model";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrl: './student-details.component.css'
})
export class StudentDetailsComponent implements OnInit {
  public studentCode!: string;
  studentsPayments!:Array<Payment>;
  paymentsDataSource!:MatTableDataSource<Payment>;
  public displayedColumns = ['id', 'date', 'amount', 'type', 'status', 'firstName','details'];
  @ViewChild(MatPaginator) paginator!:MatPaginator;
  @ViewChild(MatSort) sort!:MatSort;
 constructor(private activatedRoute:ActivatedRoute,
             private studentService:StudentsService,
             private router:Router){

 }
  ngOnInit(){
this.studentCode=this.activatedRoute.snapshot.params['code'];
    console.log("studentcode: "+this.studentCode);
this.studentService.getStudentsPayments(this.studentCode).subscribe({
  next:data=>{
    this.studentsPayments=data;
    this.paymentsDataSource=new MatTableDataSource<Payment>(this.studentsPayments);
    this.paymentsDataSource.paginator=this.paginator;
    this.paymentsDataSource.sort=this.sort;

  },
  error:error=>{
    console.log(error);
  }
});
  }

  newPayment() {
    this.router.navigateByUrl(`/admin/new-payment/${this.studentCode}`);
  }

  paymentDetails(payment:Payment) {
    this.router.navigateByUrl(`/admin/payment-details/${payment.id}`)
  }
}
