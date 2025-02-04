import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { userService } from '../service/user.services';
import { userData } from '../model/userdata.model';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {
constructor(private userServico:userService){}
user = new userData();
users:userData[] = [];

send():void{
  this.userServico.cadastrar(this.user).pipe(
    catchError(erro => {
      console.error('Erro ao cadastrar o usuário:', erro);
      alert('An error occurred during registration. Please try again later.');
      return of(null); // Retorna um valor "neutro" para continuar o fluxo
  })
  ).subscribe(
    retorno => {
      if (retorno) {
        this.users.push(retorno);
        localStorage.setItem('userData', JSON.stringify(userData)); 
        this.user = new userData();
        alert('Check your email to confirm the registration!');
      }});
}

}


