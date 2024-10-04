import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { AddConcertComponent } from './components/add-concert/add-concert.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  // { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'new/concert', component: AddConcertComponent}

];
