import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Component, Inject, PLATFORM_ID, Renderer2 } from '@angular/core';
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
  constructor(private userServico:userService, private router: Router,private renderer: Renderer2,
    @Inject(PLATFORM_ID) private platformId: Object){}
  user = new userLogin();
  users:userLogin[] = [];


  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.renderer.addClass(document.body, 'sign-in-bg');
    }
  }

  ngOnDestroy(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.renderer.removeClass(document.body, 'sign-in-bg');
    }
  }

  login() {
    this.userServico.login(this.user).pipe(
      catchError(erro => {
        console.error('Erro ao realizar login o usuário:', erro);
        alert('Ocorreu um erro durante o login. Por favor, tente novamente mais tarde.');
        return of(null); // Retorna um valor "neutro" para continuar o fluxo
    })
    ).subscribe(
      retorno => {
        if (retorno) {
          console.log('retorno:', retorno)
          console.log('Usuário logado:', retorno.name);        
          localStorage.setItem('userName', retorno.name);
          console.log('Email logado:', retorno.email);        
          localStorage.setItem('userEmail', retorno.email);
          this.users.push(retorno);
          this.user = new userLogin();
          this.router.navigate(['/chat']);
        }});
    }
}
