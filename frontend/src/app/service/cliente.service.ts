import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClienteModel } from '../Cliente.interface';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private apiUrl = "http://localhost:8080/clients";

  constructor(private http: HttpClient) { }

  //GET
  getData() {
    return this.http.get<ClienteModel[]>(this.apiUrl);
  }

  //POST
  postData(cliente: ClienteModel) {
    return this.http.post<ClienteModel>(this.apiUrl, cliente);
  }

  //PUT
  putData(cliente: ClienteModel) {
    return this.http.put<ClienteModel>(`${this.apiUrl}/${cliente.id}`, {
      email: cliente.email,
      password: cliente.password
    });
  }

  //DELETE
  deleteData(id:number) {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

}
