import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Client } from '@stomp/stompjs';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatServiceService {
private client!: Client;
private messagesSubject = new BehaviorSubject<string[]>([]);
messages$ = this.messagesSubject.asObservable();

constructor(@Inject(PLATFORM_ID) private platformId: Object) {
  if (isPlatformBrowser(this.platformId)) {
    this.connect();
  }}

 
 private connect() {
  this.client = new Client({
    brokerURL: 'ws://localhost:8084/run-livechat/websocket',
    reconnectDelay: 5000, 
    onConnect: () => {
      console.log('Conectado ao WebSocket');
      this.client.subscribe('/topics/livechat', (message) => {
        const parsedMessage = JSON.parse(message.body);
        this.addMessage(parsedMessage);
      });
    },
    onStompError: (frame) => {
      console.error('Erro no WebSocket:', frame);
    },
  });

  this.client.activate();
}


 sendMessage(user: string, message: string){
  if (this.client.connected) {
    console.log('Mensagem: ', user, message)
    this.client.publish({
      destination:'/app/new-message',
      body: JSON.stringify({user,message}),
    });
  }
}


 private addMessage(msg:string){
  console.log('📝 Adicionando mensagem ao chat:',msg);
  this.messagesSubject.next([...this.messagesSubject.value,msg]);
}
}