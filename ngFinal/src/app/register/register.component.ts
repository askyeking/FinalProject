import { UserService } from './../user.service';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from './../models/user';
import { ActivatedRoute, Router } from '@angular/router';
import {Customer} from '../models/customer';
import { Vendor } from '../models/vendor';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  newUser = new User();
  userProfiles = null;
  customerProfile = new Customer();
  vendorProfile = null;
  isVendor = false;



  constructor(private authService: AuthService, private userService: UserService, private currentRoute: ActivatedRoute,
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
            this.userProfiles = Object.assign({}, this.newUser);
            this.newUser = new User();
            this.newUser.customer = new Customer();
          },
          err => {
            console.log('we think login didnt work');
          }
        );
      },
      err => {
        console.error('Observer got an error' + err);
      }
    );
    // this.router.navigateByUrl('/home');
  }

  createVendor() {
    if (this.isVendor) {
      this.vendorProfile = new Vendor();
    } else {
      this.vendorProfile = null;
    }
  }

  createProfile(user: User) {
    console.log('customer profile ' + this.customerProfile);
    user.customer = this.customerProfile;
    if (this.vendorProfile) {
      user.vendor = this.vendorProfile;
    }
    this.userService.createProfiles(user).subscribe(
      data => {
        console.log('create Profile - success');
        this.customerProfile = new Customer();
        this.vendorProfile = null;
        this.isVendor = false;
        this.router.navigateByUrl('/home');
      },
      err => {
        console.error('Observer got an error: ' + err);
      }
    );
  }


}
