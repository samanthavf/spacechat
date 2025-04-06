import { Component } from '@angular/core';
import { ChatServiceService } from '../chat-service.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Chat } from '../model/chat.model';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent {
user = '';
message = '';
messages$: any;
constructor(private liveChatService: ChatServiceService) {
  this.messages$ = this.liveChatService.messages$;

}

sendMessage() {
  if (this.message.trim()) {
    this.liveChatService.sendMessage(this.user, this.message);
    this.message = '';
  }
}

}
