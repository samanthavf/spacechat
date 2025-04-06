import { isPlatformBrowser } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Client } from '@stomp/stompjs';
import { BehaviorSubject, Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ChatServiceService {
  private client!: Client;
  private messagesSubject = new BehaviorSubject<string[]>([]);
  messages$ = this.messagesSubject.asObservable();

  constructor(@Inject(PLATFORM_ID) private platformId: Object, private http: HttpClient) {
    if (isPlatformBrowser(this.platformId)) {
      this.connect();
    }
  }


  private connect() {
    this.client = new Client({
      brokerURL: 'ws://localhost:8084/run-livechat/websocket',
      reconnectDelay: 5000,
      onConnect: () => {
        console.log('Conectado ao WebSocket');
        this.client.subscribe('/topics/livechat', (message) => {
          console.log('🔍 Mensagem recebida do WebSocket:', message.body);
          this.addMessage(message.body);
        });
      },
      onStompError: (frame) => {
        console.error('Erro no WebSocket:', frame);
      },
    });

    this.client.activate();
  }


  sendMessage(user: string, msg: string) {
    console.log('Mensagem do sendMessage: ', { name: user, message: msg })
    if (this.client.connected) {
      this.client.publish({
        destination: '/app/new-message',
        body: JSON.stringify({ name: user, message: msg }),
      });
    }
  }

  private addMessage(msg: string) {
    const parseMsg = JSON.parse(msg)
    console.log('📝 Mensagem processada:', parseMsg);
    this.messagesSubject.next([...this.messagesSubject.value, parseMsg]);
  }

  private logoutURL: string = 'http://localhost:8082/auth/logout';
  logout(useremail: string): Observable<any> {
    const body = {
      email: useremail,
      logedIn: false
    };
    console.log('Json enviado no logout:', JSON.stringify(body))
    return this.http.put<any>(this.logoutURL, body, { headers: { 'Content-Type': 'application/json' }, responseType: 'text' as 'json' });
  }
}