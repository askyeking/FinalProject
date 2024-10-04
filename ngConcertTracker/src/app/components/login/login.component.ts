import { Component } from '@angular/core';
import { User } from '../../models/user';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginUser: User = new User();

  constructor(private authService: AuthService, private router: Router){}

  login(loginUser: User) {
    this.authService.login(loginUser.username, loginUser.password).subscribe({
      next: (data) => {
        this.router.navigateByUrl("home");
      },
      error: (err) => {
        this.router.navigateByUrl("register");
      }
    });
  }

}
