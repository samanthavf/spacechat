import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Client } from '@stomp/stompjs';
import { BehaviorSubject } from 'rxjs';
import SockJS from 'sockjs-client';
import { Chat } from './model/chat.model';
import { isPlatformBrowser } from '@angular/common';

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
    }
  }


  private connect() {
    this.client = new Client({
      brokerURL: 'ws://localhost:8084/buildrun-livechat',
      reconnectDelay: 5000, 
      onConnect: () => {
        console.log('Conectado ao WebSocket');
        this.client.subscribe('/topics/livechat', (message) => {
          this.addMessage(message.body);
        });
      },
      onStompError: (frame) => {
        console.error('Erro no WebSocket:', frame);
      },
    });

    this.client.activate();
  }


sendMessage(user: string, message: string) {
  if (this.client.connected) {
    this.client.publish({
      destination: '/app/new-message',
      body: JSON.stringify({ user, message }),
    });
  }
}

private addMessage(msg: string) {
  this.messagesSubject.next([...this.messagesSubject.value, msg]);
}

}


