import { UserService } from './../user.service';
import { User } from './../models/user';
import { Component, OnInit } from '@angular/core';
import { InventoryItemListComponent } from '../inventory-item-list/inventory-item-list.component';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css']
})
export class CustomerProfileComponent implements OnInit {
  user: User = null;


  constructor(private authService: AuthService, private userService: UserService,
    private router: Router) { }

  ngOnInit() {
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

}
