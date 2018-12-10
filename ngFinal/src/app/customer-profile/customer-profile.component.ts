import { AuthService } from './../auth.service';
import { UserService } from "./../user.service";
import { User } from "./../models/user";
import { Component, OnInit } from "@angular/core";
import { InventoryItemListComponent } from "../inventory-item-list/inventory-item-list.component";
import { Router } from "@angular/router";
import { AuthService } from "../auth.service";
import { Customer } from "../models/customer";
import { ItemRental } from "../models/item-rental";
import { RentService } from "../rent.service";
import { Vendor } from "../models/vendor";

@Component({
  selector: "app-customer-profile",
  templateUrl: "./customer-profile.component.html",
  styleUrls: ["./customer-profile.component.css"]
})
export class CustomerProfileComponent implements OnInit {
  currentUser: User = null;
  customer: Customer = null;
  editUser: User = null;
  createVendor: Vendor = null;
  itemRentals: ItemRental[];

  constructor(
    private userService: UserService,
    private router: Router,
    private rentService: RentService,
    private authService: AuthService,
  ) {}

  ngOnInit() {
    // this.refresh();
    this.getCurrentUser();
  }

  startVendorCreation() {
    if (this.createVendor) {
      this.createVendor = null;
    } else {
      this.createVendor = new Vendor();
    }
  }

  createVendorProfile() {
    this.userService.createVendor(this.createVendor).subscribe(
      data => {
        this.currentUser = data;
        this.refresh();
      },
      err => {
        console.error("Observer got an error" + err);
        this.refresh();
      }
    );
    // this.authService.logout();
  }

  refresh() {
    this.currentUser = null;
    this.customer = null;
    this.editUser = null;
    this.createVendor = null;
    this.itemRentals = [];

    this.getCurrentUser();
  }

  getCurrentUser() {
    this.userService.retrieveProfiles().subscribe(
      data => {
        this.currentUser = data;
        // has value
        console.log(this.currentUser);
        this.getUserRentals();
      },
      err => {
        console.error("Observer got an error" + err);
      }
    );
    // is null
    // console.log(this.user);
  }

  viewRental(rentalId: number) {
    this.router.navigateByUrl("inventoryItems/rental/" + rentalId);
  }

  viewItem(inventoryItemId) {
    this.router.navigateByUrl("inventoryItems/viewItem/" + inventoryItemId);
  }

  getUserRentals() {
    this.rentService.retrieveCustomersRentals(this.currentUser.id).subscribe(
      data => {
        console.log("getUserRentals()");
        this.currentUser.customer.rentedItems = data;
        console.log(this.currentUser.customer.rentedItems);
      },
      err => {
        console.log("Error: getUserRentals()");
        console.error("Observer got an error" + err);
      }
    );
  }


  setEdit() {
    this.editUser = Object.assign({}, this.currentUser);
    this.customer = this.editUser.customer;
  }

  cancelEdit() {
    this.editUser = null;
    this.getCurrentUser();
  }

  updateCustomer(user) {
    console.log("profilecomponent.updateCustomer");
    console.log(user);
    console.log(user.customer);

    this.userService.update(user).subscribe(
      data => {
        this.refresh();
        this.currentUser = data;
      },
      err => {
        this.refresh();
        console.error("Observer got an error" + err);
      }
    );
  }
}
