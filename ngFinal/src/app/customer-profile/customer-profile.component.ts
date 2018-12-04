import { UserService } from './../user.service';
import { User } from './../models/user';
import { Component, OnInit } from '@angular/core';
import { InventoryItemListComponent } from '../inventory-item-list/inventory-item-list.component';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Customer } from '../models/customer';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css']
})
export class CustomerProfileComponent implements OnInit {
  user: User = null;
  customer: Customer = null;
  editUser: User = null;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit() {
    this.refresh();
    this.getCurrentUser();
  }

  getCurrentUser() {
    this.userService.retrieveProfiles().subscribe(
      data => {
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
    this.customer = null;
  }

  setEdit() {
    this.editUser = Object.assign({}, this.user);
    this.customer = this.editUser.customer;
  }

  cancelEdit() {
    this.editUser = null;
    this.getCurrentUser();
  }

  updateCustomer(user) {
    console.log('profilecomponent.updateCustomer');
    console.log(user);
    console.log(user.customer);

    this.userService.update(user).subscribe(
      data => {

        this.refresh();
        this.user = data;
      },
      err => {
        this.refresh();
        console.error('Observer got an error' + err);
      }
    );
  }
}
