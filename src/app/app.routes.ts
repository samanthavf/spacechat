import { Routes } from '@angular/router';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { ChatComponentComponent } from './chat-component/chat-component.component';

export const routes: Routes = [
    { path: 'sign-up', component: SignUpComponent},
    { path: 'sign-in', component: SignInComponent},
    { path: 'chat', component: ChatComponentComponent},
    { path: '**', redirectTo: '/sign-up' } ,
    { path: '', redirectTo: '/sign-up', pathMatch: 'full' },
];
