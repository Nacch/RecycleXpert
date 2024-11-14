import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MenuComponent } from './components/menu/menu.component';
import { RegisterVolunteerComponent } from './components/register-volunteer/register-volunteer.component';
import { EventRegisterComponent } from './components/event-register/event-register.component';
import { VolunteerListarComponent } from './components/volunteer-listar/volunteer-listar.component';
import { LoginComponent } from './components/login/login.component';


const routes: Routes = [

  {path: 'menu', component: MenuComponent},
  {path: 'register-volunteer', component: RegisterVolunteerComponent},
  {path: 'event-register', component: EventRegisterComponent},
  {path: 'volunteer-listar', component: VolunteerListarComponent},
  { path:"login", component: LoginComponent  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
