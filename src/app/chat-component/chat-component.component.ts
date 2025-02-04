import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Component, Inject, PLATFORM_ID, Renderer2 } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChatServiceService } from '../chat-service.service';
import { ChatModel } from '../model/chat.model';
import { userData } from '../model/userdata.model';

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
message = '';
userName = { name: 'Usuário' }
outputs: ChatModel[]=[]

messages$: any;

  constructor(private service:ChatServiceService,private renderer: Renderer2,@Inject(PLATFORM_ID) private platformId: Object){
    this.messages$ = this.service.messages$
  }

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.renderer.addClass(document.body, 'chat');
    }
  }

  ngOnDestroy(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.renderer.removeClass(document.body, 'chat');
    }
  }

  sendMessage() {
    if (this.message.trim()) {
      const newMessage = new ChatModel(this.userName.name, this.message);
      this.service.sendMessage(newMessage);
      this.outputs.push(newMessage);
      this.message='';
    }}

}
