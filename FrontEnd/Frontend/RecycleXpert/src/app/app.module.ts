import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MaterialModule } from './modules/material/material.module';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MenuComponent } from './components/menu/menu.component';
import { RegisterVolunteerComponent } from './components/register-volunteer/register-volunteer.component';
import { EventRegisterComponent } from './components/event-register/event-register.component';
import { VolunteerListarComponent } from './components/volunteer-listar/volunteer-listar.component';
import { EventTypeRegisterComponent } from './components/event-type-register/event-type-register.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { DatePipe } from '@angular/common';
import { EventListarComponent } from './components/event-listar/event-listar.component';  // Agrega esta importación

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    RegisterVolunteerComponent,
    EventRegisterComponent,
    VolunteerListarComponent,
    EventTypeRegisterComponent,
    HomeComponent,
    LoginComponent,
    EventListarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync(),
    provideNativeDateAdapter(),
    DatePipe, 
  
  ],
  
  bootstrap: [AppComponent]
})
export class AppModule { }
