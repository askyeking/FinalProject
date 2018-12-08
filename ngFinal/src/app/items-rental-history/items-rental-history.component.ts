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
  amountEarnedTotal = 0;

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
    this.inventoryItemService.getOne(id).subscribe(
      data => {
        this.itemViewed = data;
        console.log(this.itemViewed);
        this.getRentals(id);


      },
      err => {
        console.error('Observer got an error' + err);
      }
    );


  }

  getRentals(id: number) {
    this.rentService.retrieveItemRentalHistory(id).subscribe(
      data => {
        this.itemViewed.allRents = data;
        console.log(this.itemViewed.allRents);

        this.getTotalIncome();
      },
      err => {
        console.error('Observer got an error' + err);
      }
    );
  }

  getTotalIncome() {
    for (let index = 0; index < this.itemViewed.allRents.length; index++) {
      this.amountEarnedTotal += this.itemViewed.allRents[index].paidAmount;
    }
  }

  viewRental(id: number) {
    this.router.navigateByUrl('inventoryItems/rental/' + id);
  }

}
