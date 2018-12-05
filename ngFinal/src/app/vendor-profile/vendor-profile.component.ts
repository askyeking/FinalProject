import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { Vendor } from '../models/vendor';

@Component({
  selector: 'app-vendor-profile',
  templateUrl: './vendor-profile.component.html',
  styleUrls: ['./vendor-profile.component.css']
})
export class VendorProfileComponent implements OnInit {
  user: User = null;
  vendor: Vendor = null;
  editUser: User = null;


  constructor(private userService: UserService, private router: Router) {}

  ngOnInit() {
    this.getCurrentUser();
    console.log('active?');
    console.log(this.user.vendor.active);

  }

  getCurrentUser() {
    this.userService.retrieveProfiles().subscribe(
      data => {
        console.log(data);

        this.user = data;
      },
      err => {
        console.error('Observer got an error' + err);
      }
    );
  }

  refresh() {
    this.getCurrentUser();
    this.editUser = null;
    this.vendor = null;
  }

  setEdit() {
    this.editUser = Object.assign({}, this.user);
    this.vendor = this.editUser.vendor;
  }
}
