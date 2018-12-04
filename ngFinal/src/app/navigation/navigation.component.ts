import { environment } from './../../environments/environment';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  loginUser = new User();
  private baseUrl = environment.baseUrl;
  constructor(private router: Router, private authService: AuthService) { }


login(user: User) {
  console.log('in here');
  console.log(user);

  this.authService.login(user.email, user.password).subscribe(
    data => {
      this.loginUser = new User();
    },
    err => {
      console.error('Observer got an error' + err);
    }
  );
}






  ngOnInit() {

  }

}
