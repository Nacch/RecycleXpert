import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  ocultarPass: boolean = true;
  isLoading = false;


  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private snack: MatSnackBar,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.creaFormulario();
  }

  creaFormulario() {
    this.loginForm = this.formBuilder.group({
      userName: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  login() {
    if (this.loginForm.invalid) {
      return;
    }

    const user: User = {
      id: 0,
      userName: this.loginForm.get('userName')?.value,
      password: this.loginForm.get('password')?.value,
      authorities: ''
    };

    this.userService.login(user).subscribe({
      next: (data) => {
        this.router.navigate(['/home']);
      },
      error: (err) => {
        this.snack.open('Hubo un error en la identificación del usuario', 'OK', { duration: 2000 });
      }
    });
  }

  cancelar() {
    this.router.navigate(['/']);
  }

  redirectToRegister(){
    
  }

  
}
