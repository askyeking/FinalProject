import { SearchService } from './../search.service';
import { CategoryService } from './../category.service';
import { Category } from './../models/category';
import { environment } from './../../environments/environment';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  loginUser = new User();
  public isCollapsed = false;
  public dropdownButtonText = 'Search By';

  categories: Category[] = [];
  selected: Category;
  parameter: String = "";
  keyword: String = "";

  constructor(private router: Router, public authService: AuthService,
     public catService: CategoryService, private searchService: SearchService, route: ActivatedRoute) { }


// login(user: User) {
//   console.log('in here');
//   console.log(user);

//   this.authService.login(user.email, user.password).subscribe(
//     data => {
//       this.loginUser = new User();
//     },
//     err => {
//       console.error('Observer got an error' + err);
//     }
//   );
// }


  loadCategories() {
    this.catService.loadCategories().subscribe(
      data => {
        console.log(data);
        this.categories = data;
        console.log(this.categories);
      },
      err => console.error('Observer got an error: ' + err)
      );
  }

  searchByCategory() {
    this.parameter = this.dropdownButtonText;
    console.log('IN NAV COMPONENT !!!!!!!' + this.parameter);
    console.log("in Search before reroute");
    this.router.navigateByUrl("items/search/" + this.parameter + "/" + this.keyword);
  }

  searchByKeyword() {

  }

  searchByVendor() {

  }

  get searchData(): string {
    return this.searchService.searchParameter;
  }

  set searchData(value: string) {
    this.searchService.searchParameter = value;
  }

  selectCategory(selectedCategory: Category) {
      this.selected = selectedCategory;
      this.dropdownButtonText = 'category';
      this.keyword = this.selected.name;
      console.log(this.keyword);
      console.log(this.parameter);
      console.log();
  }

  ngOnInit() {
    this.loadCategories();
  }


}
