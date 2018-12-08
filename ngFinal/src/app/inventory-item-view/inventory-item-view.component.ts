import { RentService } from './../rent.service';
import { Router, ActivatedRoute } from '@angular/router';
import { SearchService } from './../search.service';
import { Component, OnInit } from '@angular/core';
import { InventoryItemService } from '../inventory-item.service';
import { AuthService } from '../auth.service';
import { getValueInRange } from '@ng-bootstrap/ng-bootstrap/util/util';
import { InventoryItem } from '../models/inventory-item';
import { ItemRental } from '../models/item-rental';
import { UserService } from '../user.service';
import { User } from '../models/user';

@Component({
  selector: 'app-inventory-item-view',
  templateUrl: './inventory-item-view.component.html',
  styleUrls: ['./inventory-item-view.component.css']
})
export class InventoryItemViewComponent implements OnInit {


  id = null;
  selected: InventoryItem = null;
  itemRental: ItemRental = new ItemRental();

  // try;
  constructor(private inventoryItemService: InventoryItemService,
    public authService: AuthService, private rentService: RentService, private router: Router, private route: ActivatedRoute) {

     }

  ngOnInit() {
    // this.try = this.langId;

    this.id  = this.route.snapshot.paramMap.get('id');
    this.inventoryItemService.getOne(this.id).subscribe(
      data => this.selected = data,
      err => console.error('itemView.ngOnInit.getOne: ' + err)
    );
  }

  backToHome() {
    this.router.navigateByUrl('');
  }

  refresh() {
    this.id = null;
    this.selected = null;
    this.itemRental = new ItemRental();
  }

  persistItemRental() {
    this.itemRental.inventoryItem = this.selected;
    this.itemRental.paidAmount = this.calculatePrice();
    this.itemRental.active = true;

    console.log(this.itemRental);

    this.rentService.rentItem(this.itemRental).subscribe(
      data => {
        this.itemRental = data;
        console.log(this.itemRental);
        this.router.navigateByUrl('inventoryItems/rental/' + this.itemRental.id);
      },
      err => {
        console.error('Observer got an error' + err);
      }
    );
  }

  calculatePrice(): number {
    console.log(this.selected.price);
    const oneDay = 1000 * 60 * 60 * 24;
    const difference = new Date(this.itemRental.endDate).getTime() - new Date(this.itemRental.startDate).getTime();
    return Math.round(difference / oneDay) * this.selected.price + 5;
  }

}
