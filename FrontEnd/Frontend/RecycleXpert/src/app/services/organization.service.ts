import { Injectable } from '@angular/core';
import { Organization } from '../models/organization';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {
    // Ruta a donde se conectará
    servidor: string = "http://localhost:8080/api";
    recurso: string = "organizations";

  constructor(private http: HttpClient) {}

  getOrganizations(){
    return this.http.get<Organization[]>(this.servidor+"/"+this.recurso);
  }

  getOrganization(id: number){
    return this.http.get<Organization>(this.servidor+"/"+this.recurso+"/"+id.toString());
  }
}
