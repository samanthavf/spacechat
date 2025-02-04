import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Component, Inject, PLATFORM_ID, Renderer2 } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChatServiceService } from '../chat-service.service';
import { userLogin } from '../model/userdata.login';
import { userData } from '../model/userdata.model';
import { ChatModel } from '../model/chat.model';

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
 output = new ChatModel();
 outputs: ChatModel[]=[]
 name = this.output.user || 'Anônimo';
 message = '';
 messages$: any;

  constructor(private service:ChatServiceService,private renderer: Renderer2,@Inject(PLATFORM_ID) private platformId: Object){}

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
    if (this.output.message.trim()) {
      this.service.sendMessage(this.output);
      this.outputs.push(this.output)
      this.output = new ChatModel();
    }}

}
