import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { RegisterVolunteerComponent } from '../../register-volunteer/register-volunteer.component';
import { Router } from '@angular/router';
import { OrganizationService } from '../../../services/organization.service';
import { UserService } from '../../../services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../../../models/user';

@Component({
  selector: 'app-organization-signup',
  templateUrl: './organization-signup.component.html',
  styleUrl: './organization-signup.component.css'
})
export class OrganizationSignupComponent {
  addForm!: FormGroup;

  constructor(
    private organizationService: OrganizationService,
    private userService: UserService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router,
    private dialog: MatDialog

  /*
    id: number;                  // ID único de la organización
    organizationName: string;    // Nombre de la organización
    orgType: string;             // Tipo de organización (por ejemplo, NGO, Non-Profit)
    contactInfo: string;         // Información de contacto (email, teléfono, etc.)
    collaborationArea: string;
  */

  ) {
    this.addForm = this.fb.group({
      userName: ['', Validators.required],    
      password: ['', Validators.required],
      organizationName: ['', Validators.required],
      orgType: ['', Validators.required],
      contactInfo: ['', Validators.required],
      collaborationArea: ['', Validators.required],

    });
  }

  registrarOrganzation(): void {
    if (this.addForm.invalid) {
      this.snackBar.open("Por favor, complete todos los campos correctamente.", "Ok", { duration: 3000 });
      return;
    }

    const user: User = {
      id: 0,
      userName: this.addForm.get('userName')?.value,
      password: this.addForm.get('password')?.value,
      authorities: "ORGANIZACION"



    };





  }




  RegisterVolunteer():void {
      //console.log(this.registerForm.value); // Aquí puedes enviar los datos al backend
      this.router.navigate(['/register-volunteer']);
  }

  onCancel(): void {
    this.router.navigate(['/login']);
  }



}
