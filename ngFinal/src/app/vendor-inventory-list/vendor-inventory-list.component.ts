import { Router } from "@angular/router";
import { Component, OnInit } from "@angular/core";
import { InventoryItemService } from "../inventory-item.service";
import { InventoryItem } from "../models/inventory-item";

@Component({
  selector: "app-vendor-inventory-list",
  templateUrl: "./vendor-inventory-list.component.html",
  styleUrls: ["./vendor-inventory-list.component.css"]
})
export class VendorInventoryListComponent implements OnInit {
  vendorInventory = [];
  selected = null;
  newItem = new InventoryItem();
  editItem = null;

  constructor(
    private inventoryItemService: InventoryItemService,
    private router: Router
  ) {
  this.vendorInventory = [];
  this.selected = null;
  this.newItem = new InventoryItem();
  this.editItem = null;
  }

  refresh() {}

  ngOnInit() {
    console.log('vendor inventory');
    console.log(this.vendorInventory);
    console.log('selected');
    console.log(this.selected);
    console.log('editItem');
    console.log(this.editItem);

    this.loadVendorInventory();
  }

  loadVendorInventory() {
    this.inventoryItemService.loadVendorItems().subscribe(
      data => {
        this.vendorInventory = data;
        this.selected = false;
        this.editItem = false;
      },
      err => {
        console.log(this.vendorInventory);
        console.error("Observer got an error: " + err);
      }
    );
  }

  addVendorInventory() {
    this.inventoryItemService.addVendorItems(this.newItem).subscribe(
      data => {
        this.newItem = new InventoryItem();
        this.loadVendorInventory();
      },
      error => {
        console.error("Error adding new item");
        console.error(error);
      }
    );
  }

  setEditVendorInventory(item: InventoryItem) {
    this.editItem = item;
    // this.editItem =  Object.assign({}, this.selected);
    //  console.log(this.editItem);
  }

  setItemActiveToFalse(item: InventoryItem) {
    this.setEditVendorInventory(item);
    this.editItem.active = false;
    this.updateVendorInventory();
    this.loadVendorInventory();
    console.log(this.editItem);
  }

  updateVendorInventory() {
    this.inventoryItemService.updateVendorItems(this.editItem).subscribe(
      data => {
        this.editItem = null;
        this.selected = data;
        this.loadVendorInventory();
      },
      error => {
        console.error("Error updating VendorInvetoryItem");
        console.error(error);
      }
    );
  }

  viewRentalHistory(item: InventoryItem) {
    this.router.navigateByUrl("inventoryItems/viewItem/history/" + item.id);
  }
}
