import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { tap } from 'rxjs';
import { Token } from '../models/token';

@Injectable({
  providedIn: 'root'
})
export class UserService {

 
  ruta_servidor: string = "http://localhost:8080/api";
  recurso:string = "users";

  constructor(private http:HttpClient) { }


  getUser(id: number){
    return this.http.get<User>(this.ruta_servidor+"/"+this.recurso+"/"+id.toString());
  }

  newUser(user: User){
    return this.http.post<User>(this.ruta_servidor+"/"+this.recurso + "/" + "register",user);
  }

  login(user: User){
    this.logout();
    return this.http.post<Token>(this.ruta_servidor+"/"+this.recurso + "/" + "login",user).pipe(
      tap( (resultado:Token)=>{
        localStorage.setItem('jwtToken', resultado.jwtToken);
        localStorage.setItem('user_id', resultado.user_id.toString());
        localStorage.setItem('authorities', resultado.authorities);
      }
      )
    );
  }

  logout(){
    if (typeof localStorage !== 'undefined') {
      localStorage.clear();
    }
  }

  hayUsuarioLogeado(){
    if (this.getUserIdActual() == null || this.getUserIdActual() == "") {
      return false;
    }
    return true;
  }


  getTokenActual() {
    if (typeof localStorage !== 'undefined') {
      return localStorage.getItem('jwtToken');  
    } 
    return null;
  }

  getUserIdActual() {
    if (typeof localStorage !== 'undefined') {
      return localStorage.getItem('user_id');
    }
    return null;

  }

  getAuthoritiesActual() {
    if (typeof localStorage !== 'undefined') {
      return localStorage.getItem('authorities');
    }
    return null;
  }


}
