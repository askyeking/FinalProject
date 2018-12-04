import { Component, OnInit } from '@angular/core';
import { InventoryItemService } from '../inventory-item.service';

@Component({
  selector: 'app-inventory-item-list',
  templateUrl: './inventory-item-list.component.html',
  styleUrls: ['./inventory-item-list.component.css']
})
export class InventoryItemListComponent implements OnInit {

  inventoryItems = [];
  selected = null;

  loadInventoryItems() {
    this.inventoryItemService.index().subscribe(
      data => this.inventoryItems = data,
      err => console.error('Observer got an error: ' + err)
    );
  }

  constructor(private inventoryItemService: InventoryItemService) { }

  ngOnInit() {
    this.loadInventoryItems();
  }

}
