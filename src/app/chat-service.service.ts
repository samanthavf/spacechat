import { Injectable } from '@angular/core';
import { Client } from '@stomp/stompjs';
import { BehaviorSubject } from 'rxjs';
import SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root'
})
export class ChatServiceService {
  private stompClient!: Client;
  private messageSubject = new BehaviorSubject<string>('');
  public messages$ = this.messageSubject.asObservable();
  constructor() {
    this.connect();
   }

  private connect(){
    this.stompClient = new Client({
      webSocketFactory: () => new SockJS('http://localhost:8084/buildrun-livechat'),
      reconnectDelay: 500
    });

    this.stompClient.onConnect = () =>{
      console.log('Conectado ao WebSocket!');

      // Assina o tópico para receber mensagens
      this.stompClient.subscribe('/topics/livechat', (message) => {
        this.messageSubject.next(message.body);
      });
    };

    this.stompClient.activate();
  }

// Enviar mensagem para o servido
sendMessage(message: string){
  if(this.stompClient.connected){
    this.stompClient.publish({destination: '/app/new-message', body:message});
  }else{
    console.warn('WebSocket não está conectado.')
  }
}

}
