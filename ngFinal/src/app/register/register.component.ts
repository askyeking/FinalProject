import { HttpClient } from '@angular/common/http';
import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from './../models/user';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  newUser = new User();



  constructor(private authService: AuthService, private currentRoute: ActivatedRoute,
    private router: Router) { }

    ngOnInit() {
  }

  register(user: User) {
    this.authService.register(user).subscribe(
      data => {
        console.log('component register email & password');
        console.log(user.email);
        console.log(user.password);

        this.authService.login(user.email, user.password).subscribe(
          // tslint:disable-next-line:no-shadowed-variable
          data => {
            console.log('we think login worked');
          },
          err => {
            console.log('we think login didnt work');
          }
        );
        this.newUser = new User();
      },
      err => {
        console.error('Observer got an error' + err);
      }
    );
    this.router.navigateByUrl('/home');
  }


}
