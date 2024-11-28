import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { userData } from '../model/userdata.model';
import { userService } from '../service/user.services';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
}) 
export class SignInComponent {
  constructor(private userServico:userService){}
  user = new userData();
  users:userData[] = [];

  login() {
    throw new Error('Method not implemented.');
    }
}
