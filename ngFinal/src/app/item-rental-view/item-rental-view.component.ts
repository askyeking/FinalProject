import { AuthService } from "./../auth.service";
import { Component, OnInit } from "@angular/core";
import { InventoryItem } from "../models/inventory-item";
import { ItemRental } from "../models/item-rental";
import { InventoryItemService } from "../inventory-item.service";
import { RentService } from "../rent.service";
import { Router, ActivatedRoute } from "@angular/router";
import { ArrayDataSource } from "@angular/cdk/collections";

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

  constructor(
    private inventoryItemService: InventoryItemService,
    public authService: AuthService,
    private rentService: RentService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

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
        this.getComments();
      },
      err => {
        console.error("Observer got an error" + err);
      }
    );
  }
}
