import { VendorService } from './../vendor.service';
import { Vendor } from './../models/vendor';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SearchService } from '../search.service';

@Component({
  selector: 'app-vendor-list',
  templateUrl: './vendor-list.component.html',
  styleUrls: ['./vendor-list.component.css']
})
export class VendorListComponent implements OnInit {

  vendors: Vendor[] = [];
  keyword: string;


  index() {
    this.vendorService.index().subscribe(
      data => {
        this.vendors = data;
        console.log(this.vendors);
      },
      err => {
       console.error('Observer got an error: ' + err);
      }
    );
  }


  searchVendors() {
    this.searchService.searchVendors(this.keyword).subscribe(
      data => {
        this.vendors = data;
        console.log(this.vendors);
      },
      err => {
       console.error('Observer got an error: ' + err);
      }
    );
  }

  constructor(private router: Router, private route: ActivatedRoute, private searchService: SearchService,
     private vendorService: VendorService) { }


  ngOnInit() {
    this.keyword = this.route.snapshot.paramMap.get("keyword");

    if (this.keyword) {
      this.searchVendors();
    } else {
      this.index();
    }


  }

}
