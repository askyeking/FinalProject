import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { InventoryItem } from '../models/inventory-item';
import { ItemRental } from '../models/item-rental';
import { InventoryItemService } from '../inventory-item.service';
import { RentService } from '../rent.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-item-rental-view',
  templateUrl: './item-rental-view.component.html',
  styleUrls: ['./item-rental-view.component.css']
})
export class ItemRentalViewComponent implements OnInit {
  selectedInventoryItem: InventoryItem;
  selectedItemRental: ItemRental;
  rentalId;

  constructor(private inventoryItemService: InventoryItemService,
    public authService: AuthService, private rentService: RentService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.rentalId  = this.route.snapshot.paramMap.get('id');
    this.rentService.getItem(this.rentalId).subscribe(
      data => {
        this.selectedItemRental = data;
        this.selectedInventoryItem = this.selectedItemRental.inventoryItem;
        console.log(this.selectedItemRental);
      },
      err => {
        console.error('Observer got an error' + err);
      }
    );
  }

}
