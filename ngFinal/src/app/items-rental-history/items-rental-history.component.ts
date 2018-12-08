import { InventoryItem } from './../models/inventory-item';
import { Component, OnInit } from '@angular/core';
import { InventoryItemService } from '../inventory-item.service';
import { AuthService } from '../auth.service';
import { RentService } from '../rent.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-items-rental-history',
  templateUrl: './items-rental-history.component.html',
  styleUrls: ['./items-rental-history.component.css']
})
export class ItemsRentalHistoryComponent implements OnInit {
  itemViewed: InventoryItem;

  constructor(
    private inventoryItemService: InventoryItemService,
    public authService: AuthService,
    private rentService: RentService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.retrieveRentalHistory(Number(this.route.snapshot.paramMap.get('id')));
  }

  retrieveRentalHistory(id: number) {
    this.rentService.retrieveItemRentalHistory(id).subscribe(
      data => {
        this.itemViewed = data;
      },
      err => {
        console.error('Observer got an error' + err);
      }
    );
  }

}
