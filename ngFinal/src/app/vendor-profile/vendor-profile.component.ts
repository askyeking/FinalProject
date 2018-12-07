import { Customer } from './../models/customer';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../models/user';
import { Vendor } from '../models/vendor';
import { VendorService } from '../vendor.service';

@Component({
  selector: 'app-vendor-profile',
  templateUrl: './vendor-profile.component.html',
  styleUrls: ['./vendor-profile.component.css']
})
export class VendorProfileComponent implements OnInit {
  user: User = null;
  vendor: Vendor = null;
  editUser: User = null;
  id = null;
  customer: Customer = null;


  constructor(private vendorService: VendorService, private router: Router, private route: ActivatedRoute) {}

  ngOnInit() {
    this.id =  this.route.snapshot.paramMap.get('id');
    this.vendorService.getUserByVendorId(this.id).subscribe(
      data => {
        this.user = data;
        this.vendor = this.user.vendor;
      },
      err => {
        console.error('Observer got an error' + err);
      }
    );
  }


  rentItem(id: number) {
    this.router.navigateByUrl('inventoryItems/viewItem/' + id);
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
