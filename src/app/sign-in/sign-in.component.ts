import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { userService } from '../service/user.services';
import { catchError, of } from 'rxjs';
import { userLogin } from '../model/userdata.login';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
}) 
export class SignInComponent {
  constructor(private userServico:userService, private router: Router){}
  user = new userLogin();
  users:userLogin[] = [];

  login() {
    this.userServico.login(this.user).pipe(
      catchError(erro => {
        console.error('Erro ao realizar login o usuário:', erro);
        alert('An error occurred during Login. Please try again later.');
        return of(null); // Retorna um valor "neutro" para continuar o fluxo
    })
    ).subscribe(
      retorno => {
        if (retorno) {
        this.users.push(retorno);
          this.user = new userLogin();
          this.router.navigate(['/chat']);
        }});
    }
}
