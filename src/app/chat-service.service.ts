import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Client } from '@stomp/stompjs';
import { BehaviorSubject } from 'rxjs';
import { ChatModel } from './model/chat.model';

@Injectable({
  providedIn: 'root'
})
export class ChatServiceService {
private client!: Client;
private messagesSubject = new BehaviorSubject<ChatModel[]>([]);
messages$ = this.messagesSubject.asObservable();

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
if (isPlatformBrowser(this.platformId)) {
  this.connect();
}}

private connect(){
  this.client = new Client({
    brokerURL: 'ws://localhost:8084/run-livechat',
    reconnectDelay: 6000,
    onConnect :()=>{
      console.log('Conectado ao WebSocket');
      this.client.subscribe('/topics/livechat', (message) => {
        const parsedMessage = JSON.parse(message.body);
        this.addMessage(parsedMessage);
      });
      },
    onStompError(frame) {
        console.log('Erro no WebSocket:', frame)
    },
  });
  this.client.activate();
}

 sendMessage(output:ChatModel){
  if (this.client.connected) {
    console.log('📤 Enviando mensagem:', { output});
    this.client.publish({
      destination: '/app/new-message',
      body: JSON.stringify({output})
    });
  }
 }


private addMessage(output:ChatModel){
  console.log('📝 Adicionando mensagem ao chat:', output);
  this.messagesSubject.next([...this.messagesSubject.value, output]);
}

}
