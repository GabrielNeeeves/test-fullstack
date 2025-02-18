import { Component } from '@angular/core';
import { ClienteService } from '../../service/cliente.service';
import { Observable } from 'rxjs';
import { ClienteModel } from '../../Cliente.interface';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-cliente',
  imports: [CommonModule, FormsModule],
  templateUrl: './cliente.component.html',
  styleUrl: './cliente.component.css'
})
export class ClienteComponent {

  constructor(private clienteService:  ClienteService) {}

  clientes$ = new Observable<ClienteModel[]>

  id = '';
  email = '';
  password = '';

  //GET
  buscarDados() {
    this.clientes$ = this.clienteService.getData();
  }

  //
  buttonClick() {

    if(!this.email || !this.password) {
      alert("Um ou mais campos vazios")
      return;
    }

    if(this.id) {
      this.editarDados();
      return;
    }

    this.clienteService.postData({email: this.email, password: this.password})
      .subscribe(() => this.buscarDados());

      alert('Cliente cadastrado!')
  }

  //PUT
  editarDados() {
    this.clienteService.putData({
      id: parseInt(this.id),
      email: this.email,
      password: this.password}).subscribe(() => this.buscarDados());
  } 

  preencherCampos(cliente: ClienteModel) {
    this.id = cliente.id!.toString();
    this.email = cliente.email;
    this.password = cliente.password;
  }

  //DELETE
  excluirDados(id:number) {
    this.clienteService.deleteData(id)
      .subscribe(() => this.buscarDados());
  }

}
