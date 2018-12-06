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



  loadVendors() {
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

  constructor(private router: Router, private route: ActivatedRoute, private searchService: SearchService) { }
  ngOnInit() {
    this.keyword = this.route.snapshot.paramMap.get("keyword");
    this.loadVendors();
  }

}
