import {Component, OnInit, ViewChild} from '@angular/core';
import {StudentsService} from "../services/students.service";

import {Student} from "../model/students.model";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatTableDataSource} from "@angular/material/table";
import {Router} from "@angular/router";

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrl: './students.component.css'
})
export class StudentsComponent implements  OnInit{
  students!:Array<Student>;
  public dataSource: any;
  public displayedColumns = ['id', 'firstName', 'lastName', 'code', 'programId', 'action'];
  @ViewChild(MatPaginator) paginator!:MatPaginator;
  @ViewChild(MatSort) sort!:MatSort;
  constructor(private studentService:StudentsService,private router:Router) {

  }

  ngOnInit() {
    this.studentService.getStudents().subscribe(
      {
        next:data=>{
      this.students=data;
          this.dataSource = new MatTableDataSource(this.students);
          this.dataSource.paginator=this.paginator;
          this.dataSource.sort=this.sort;
    },
    error:err=>{
          console.log(err)
    }
      });
}

  studentPayments(student: Student) {
this.router.navigateByUrl(`/admin/student-details/${student.code}`);
  }
}
