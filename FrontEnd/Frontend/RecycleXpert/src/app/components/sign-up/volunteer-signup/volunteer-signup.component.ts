import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { VolunteerService } from '../../../services/volunteer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-volunteer-signup',
  templateUrl: './volunteer-signup.component.html',
  styleUrl: './volunteer-signup.component.css'
})
export class VolunteerSignupComponent {
  registerForm! : FormGroup;

  constructor(
    //services
    private volunteerService: VolunteerService,

    //----------------------
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router,
    private activatedRoute:ActivatedRoute,
    private datePipe: DatePipe 
  ){}


  onSubmit(){

  }

}
