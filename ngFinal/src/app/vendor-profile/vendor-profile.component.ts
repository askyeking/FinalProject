import { AuthService } from './../auth.service';
import { Customer } from './../models/customer';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../models/user';
import { Vendor } from '../models/vendor';
import { VendorService } from '../vendor.service';
import { isNullOrUndefined } from 'util';

@Component({
  selector: 'app-vendor-profile',
  templateUrl: './vendor-profile.component.html',
  styleUrls: ['./vendor-profile.component.css']
})
export class VendorProfileComponent implements OnInit {
  userViewed: User = null;
  vendor: Vendor = null;
  userLoggedIn: User = null;
  editUser: User = null;
  id = null;
  customer: Customer = null;
  isOriginalUser = null;

  constructor(private authService: AuthService,  private vendorService: VendorService, private userService: UserService,
    private router: Router,
    private route: ActivatedRoute) {}

  ngOnInit() {
    this.id =  this.route.snapshot.paramMap.get('id');
    this.vendorService.getUserByVendorId(this.id).subscribe(
      data => {
        this.userViewed = data;
        this.vendor = this.userViewed.vendor;
        this.retrieveUserLoggedIn();
      },
      err => {
        console.error('Observer got an error' + err);
      }
    );
  }

  retrieveUserLoggedIn() {
    this.userService.retrieveProfiles().subscribe(
      data => {
        this.userLoggedIn = data;
        this.isOriginalUser = this.setIsOriginalUser();
        console.log("original user: ");
        console.log(this.isOriginalUser);

      },
      err => {
        console.error('Observer got an error' + err);
      }
    );
  }

  setIsOriginalUser(): boolean {
    if (isNullOrUndefined(this.userLoggedIn.vendor)) {
      return false;
    }
    if (this.userLoggedIn.vendor.id === this.userViewed.vendor.id) {
      return true;
    } else {
      return false;
    }
  }


  rentItem(id: number) {
    this.router.navigateByUrl('inventoryItems/viewItem/' + id);
  }

  setEditUser() {
    this.editUser = Object.assign({}, this.userViewed);
  }

  updateVendor(editUser: User) {
    this.userService.updateVendor(editUser).subscribe(
      data => {
        this.userLoggedIn = data;
        this.editUser = null;
      },
      err => {
        console.error('Observer got an error' + err);
      }
    );
  }


  cancelEdit() {
    this.editUser = null;
  }

  viewInventory() {
    this.router.navigateByUrl('vendorInventory');
  }










}













  // ngOnInit() {
    // this.getCurrentUser();
    // console.log('active?');
    // console.log(this.user.vendor.active);
  // }

  // getCurrentUser() {
  //   this.userService.retrieveProfiles().subscribe(
  //     data => {
  //       console.log(data);

  //       this.user = data;
  //     },
  //     err => {
  //       console.error('Observer got an error' + err);
  //     }
  //   );
  // }

  // updateVendor(user) {
  //   console.log('vendorProfilecomponent.updateVendor()');
  //   console.log(user);
  //   console.log(user.vendor);

  //   this.userService.updateVendor(user).subscribe(
  //     data => {

  //       this.refresh();
  //       this.user = data;
  //     },
  //     err => {
  //       this.refresh();
  //       console.error('Observer got an error' + err);
  //     }
  //   );
  // }

  // refresh() {
  //   this.getCurrentUser();
  //   this.editUser = null;
  //   this.vendor = null;
  // }

  // setEdit() {
  //   this.editUser = Object.assign({}, this.user);
  //   this.vendor = this.editUser.vendor;
  //   console.log(this.vendor);
  // }

  // cancelEdit() {
  //   this.editUser = null;
  //   this.vendor = null;
  //   this.getCurrentUser();
  // }
