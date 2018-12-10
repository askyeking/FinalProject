import { VendorService } from "./../vendor.service";
import { AuthService } from "./../auth.service";
import { Component, OnInit, OnDestroy } from "@angular/core";
import { InventoryItemService } from "../inventory-item.service";
import { SearchService } from "../search.service";
import { ActivatedRoute, NavigationEnd } from "@angular/router";
import { Router } from "@angular/router";
import { InventoryItem } from "../models/inventory-item";
import { User } from "../models/user";

@Component({
  selector: "app-inventory-item-list",
  templateUrl: "./inventory-item-list.component.html",
  styleUrls: ["./inventory-item-list.component.css"]
})
export class InventoryItemListComponent implements OnInit, OnDestroy {
  navigationSubscription;
  inventoryItems = [];
  selected: InventoryItem = null;
  temp: string;
  parameter: string;
  keyword: string;
  vendorsUser: User;

  loadInventoryItems() {
    console.log("INSIDE LOAD INVENTORY ITEMS");
    this.inventoryItemService
      .index()
      .subscribe(
        data => {
          this.inventoryItems = data;
         },
        err => console.error("Observer got an error: " + err)
      );
  }




  openItemView(itemId: number) {
    console.log(itemId);
    this.router.navigateByUrl("inventoryItems/viewItem/" + itemId);
  }

  loadParameterizedInventoryItems() {
    console.log("INSIDE LOAD PARAMETERIZED INVENTORY ITEMS");
    this.searchService.search(this.parameter, this.keyword).subscribe(
      data => {
        this.inventoryItems = data;
      },
      err => {
        console.error("Observer got an error: " + err);
      }
    );
  }

  setSelectedItem(item: InventoryItem) {
    this.router.navigateByUrl("inventoryItems/viewItem/" + item.id);
  }

  viewVendor() {
    this.router.navigateByUrl("vendor/profile/" + this.selected.vendor.id);
  }

  constructor(
    private inventoryItemService: InventoryItemService,
    public authService: AuthService,
    private searchService: SearchService,
    private route: ActivatedRoute,
    private router: Router,
    private vendorService: VendorService
  ) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      if (e instanceof NavigationEnd) {
        this.initializeItems();
      }
    });
  }

  initializeItems() {
    this.inventoryItems = [];
    this.parameter = this.route.snapshot.paramMap.get("parameter");
    this.keyword = this.route.snapshot.paramMap.get("keyword");
    if (this.parameter && this.keyword) {
      this.loadParameterizedInventoryItems();
    } else {
      this.loadInventoryItems();
    }
  }

  ngOnDestroy() {
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }

  ngOnInit() {
    console.log(
      "INSIDE ON INIT METHOD POST SEARCH REROUTE -- INVENTORY ITEM LIST COMPONENT"
    );
    this.parameter = this.route.snapshot.paramMap.get("parameter");
    this.keyword = this.route.snapshot.paramMap.get("keyword");
    if (this.parameter && this.keyword) {
      this.loadParameterizedInventoryItems();
    } else {
      this.loadInventoryItems();
    }
  }
}
