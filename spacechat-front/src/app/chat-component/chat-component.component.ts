import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Component, Inject, PLATFORM_ID, Renderer2 } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChatServiceService } from '../chat-service.service';
import { ChatModel } from '../model/chat.model';
import { Router } from '@angular/router';



@Component({
  selector: 'app-chat-component',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './chat-component.component.html',
  styleUrl: './chat-component.component.css'
})
export class ChatComponentComponent {
  chat = new ChatModel();
  message = this.chat.message;
  userEmail: string = ''
  messages$: any;

  constructor(private service: ChatServiceService, private router: Router, private renderer: Renderer2, @Inject(PLATFORM_ID) private platformId: Object) {
    this.messages$ = this.service.messages$;
  }

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.renderer.addClass(document.body, 'chat');
    }
    this.userEmail = localStorage.getItem('userEmail') || '';
    console.log("Email carregado do localStorage:", this.userEmail);
  }

  ngOnDestroy(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.renderer.removeClass(document.body, 'chat');
    }
  }

  sendMessage() {
    if (this.message.trim()) {
      this.service.sendMessage(this.chat.name, this.message);
      this.message = '';
    }
  }

  sair() {
    console.log("Email antes do logout:", this.userEmail);
    this.service.logout(this.userEmail).subscribe({
      next: (resposta) => {
        console.log("Logout bem-sucedido:", resposta);
        localStorage.removeItem('userEmail');
        this.router.navigate(['/sign-in']);
      },
      error: (error) => {
        console.log('Erro ao deslogar:', error)
      }
    });
  }
}
