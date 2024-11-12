import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { VolunteerService } from '../../services/volunteer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-register-volunteer',
  templateUrl: './register-volunteer.component.html',
  styleUrl: './register-volunteer.component.css'
})
export class RegisterVolunteerComponent {
  addForm! : FormGroup;

  constructor(
    private volunteerService: VolunteerService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router,
    private activatedRoute:ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.CrearFormulario();
  }

  CrearFormulario(){
    this.addForm = this.fb.group({


    });

  }
  registrarVoluntario(): void {



  }

  onCancel(): void {
    this.router.navigate(['/volunteer-listar']);
  }

}
