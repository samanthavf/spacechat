import { Component } from '@angular/core';
import { ChatServiceService } from '../chat-service.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

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
message: string = '';
messages: string[] = []

constructor(private service: ChatServiceService){}

ngOnInt(){
  this.service.messages$.subscribe((message) => {
    if(message){
      this.messages.push(message);
    }
  });
}

sendMessage(){
  if(this.message.trim()){
    this.service.sendMessage(this.message);
    this.message ='';
  }
}

}
