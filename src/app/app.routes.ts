import { Routes } from '@angular/router';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SignInComponent } from './sign-in/sign-in.component';

export const routes: Routes = [
    { path: 'sign-up', component: SignUpComponent},
    { path: 'sign-in', component: SignInComponent},
    { path: '', redirectTo: '/sign-up', pathMatch: 'full' },
    { path: '**', redirectTo: '/sign-in' } 
];
