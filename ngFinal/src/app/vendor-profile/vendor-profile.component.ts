import { OnDestroy } from '@angular/core';
import { AuthService } from './../auth.service';
import { Customer } from './../models/customer';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import {NavigationEnd, Router,  ActivatedRoute} from '@angular/router';
import { User } from '../models/user';
import { Vendor } from '../models/vendor';
import { VendorService } from '../vendor.service';
import { isNullOrUndefined } from 'util';

@Component({
  selector: 'app-vendor-profile',
  templateUrl: './vendor-profile.component.html',
  styleUrls: ['./vendor-profile.component.css']
})
export class VendorProfileComponent implements OnInit, OnDestroy {

  navigationSubscription;
  userViewed: User = null;
  vendor: Vendor = null;
  userLoggedIn: User = null;
  editUser: User = null;
  id = null;
  customer: Customer = null;
  isOriginalUser = null;


  constructor(private authService: AuthService,  private vendorService: VendorService, private userService: UserService,
    private router: Router,
    private route: ActivatedRoute) {
      this.navigationSubscription = this.router.events.subscribe((e: any) => {
        if (e instanceof NavigationEnd) {
          this.retrieveUserLoggedIn();
          // subscription the events to allow you to navigate to your vendor profile from another
          // vendor's profile
          this.userViewed = this.userLoggedIn;
        }
      });
    }

    // OnInit is used to get the user who's profile we are navigating to when we navigate
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
  // method for getting the logged in user, used on init
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

  // boolean field used for identifying if you are the owner of the profile
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

  // used to navigate to the item rental page, passing it the item's id to navigate to the correct item
  rentItem(id: number) {
    this.router.navigateByUrl('inventoryItems/viewItem/' + id);
  }
  // sets the editUser field for checks in our html to show the edit user view
  setEditUser() {
    this.editUser = Object.assign({}, this.userViewed);
  }
  // persists changes to the user using the userservice
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

  // used to reset the value of editUser
  cancelEdit() {
    this.editUser = null;
  }
  // used to navigate to the inventory view for you
  viewInventory() {
    this.router.navigateByUrl('vendorInventory');
  }
  // stop subscription set in constructor to prevent memory leaks
  ngOnDestroy() {
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
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
