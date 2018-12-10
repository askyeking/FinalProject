import { CommentFromCustomerService } from '../comment-from-customer.service';
import { CommentFromVendor } from "./../models/comment-from-vendor";
import { CommentFromCustomer } from "./../models/comment-from-customer";
import { User } from "./../models/user";
import { UserService } from "./../user.service";
import { AuthService } from "./../auth.service";
import { Component, OnInit } from "@angular/core";
import { InventoryItem } from "../models/inventory-item";
import { ItemRental } from "../models/item-rental";
import { RentService } from "../rent.service";
import { Router, ActivatedRoute } from "@angular/router";
import { VendorService } from "../vendor.service";
import { CommentFromVendorService } from '../comment-from-vendor.service';

@Component({
  selector: "app-item-rental-view",
  templateUrl: "./item-rental-view.component.html",
  styleUrls: ["./item-rental-view.component.css"]
})
export class ItemRentalViewComponent implements OnInit {
  selectedInventoryItem: InventoryItem;
  selectedItemRental: ItemRental;
  rentalId;
  allComments = [];
  currentUser: User = null;
  vendorUser: User = null;

  newCustomerComment: CommentFromCustomer = new CommentFromCustomer();
  newVendorComment: CommentFromVendor = new CommentFromVendor();

  constructor(
    public authService: AuthService,
    private rentService: RentService,
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService,
    private vendorService: VendorService,
    private commentFromCustomerService: CommentFromCustomerService,
    private commentFromVendorService: CommentFromVendorService
  ) {}

  postVendorComment() {
    this.newVendorComment.itemRental = this.selectedItemRental;
    this.newVendorComment.poster = this.currentUser.vendor;
    this.commentFromVendorService.postComment(this.newVendorComment).subscribe(
      data => {
        // this.setup();
        this.refresh();
      },
      err => {
        console.error("Error returning an item" + err);
        this.setup();
      }
    );
  }



// TODO
  postCustomerComment() {
    this.newCustomerComment.itemRental = this.selectedItemRental;
    this.newCustomerComment.poster = this.selectedItemRental.customer;
    console.log("Customer Comment");
    console.log(this.newCustomerComment);
    this.commentFromCustomerService.postComment(this.newCustomerComment).subscribe(
      data => {
        // this.setup();
        this.refresh();
      },
      err => {
        console.error("Error returning an item" + err);
        this.setup();
      }
    );
  }





  refresh() {
    this.selectedInventoryItem = null;
    this.selectedItemRental = null;
    this.rentalId = null;
    this.allComments = [];
    this.currentUser = null;
    this.vendorUser = null;

    this.setup();
  }

  ngOnInit() {
    this.setup();
  }

  returnRentedItem() {
    this.rentService.returnItem(this.selectedItemRental).subscribe(
      data => {
        this.selectedItemRental = data;
        console.log(this.selectedItemRental);
      },
      err => {
        console.error("Error returning an item" + err);
        this.setup();
      }
    );
  }

  viewInventoryItem() {
    this.router.navigateByUrl(
      "inventoryItems/viewItem/" + this.selectedInventoryItem.id
    );
  }

  setup() {
    this.rentalId = this.route.snapshot.paramMap.get("id");
    this.rentService.getItem(this.rentalId).subscribe(
      data => {
        this.selectedItemRental = data;
        this.selectedInventoryItem = this.selectedItemRental.inventoryItem;
        console.log(this.selectedItemRental);
        this.getCurrentUser();
      },
      err => {
        console.error("Observer got an error" + err);
      }
    );
  }

  getCurrentUser() {
    this.userService.retrieveProfiles().subscribe(
      data => {
        this.currentUser = data;
        this.setItemsVendor();
      },
      err => {
        console.error("Observer got an error" + err);
      }
    );
  }

  getComments() {
    this.allComments = this.allComments.concat(
      this.selectedItemRental.customerComments
    );
    this.allComments = this.allComments.concat(
      this.selectedItemRental.vendorComments
    );
    if (this.allComments.length > 1) {
      this.allComments.sort(function(a, b) {
        a = new Date(a.postDate);
        b = new Date(b.postDate);
        return a > b ? -1 : a < b ? 1 : 0;
      });
    }

    console.log(this.allComments);
  }

  setItemsVendor() {
    this.vendorService
      .getVendorByInventoryItemId(this.selectedItemRental.inventoryItem.id)
      .subscribe(
        data => {
          this.getComments();
          this.vendorUser = data;
          this.selectedItemRental.inventoryItem.vendor = this.vendorUser.vendor;
          console.log(this.selectedItemRental);
        },
        err => {
          console.error("Observer got an error" + err);
        }
      );
  }

  isVendor(comment) {
    if (comment.poster.listedItems) {
      return true;
    }
    return false;
  }

  isPostingUser() {
    return true;
  }

  isPostingVendor(comment) {
    if (!this.currentUser.vendor || !this.isVendor(comment)) {
      return false;
    }

    if (this.currentUser.vendor.id === comment.poster.id) {
      return true;
    }
    return false;
  }

  isPostingCustomer(comment) {
    if (this.isVendor(comment)) {
      return false;
    }
    if (this.currentUser.customer.id === comment.poster.id) {
      return true;
    }
    return false;
  }
}
