import {OnInit, Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth.service';
import { User } from '../models/user';
import { InventoryItemListComponent } from '../inventory-item-list/inventory-item-list.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginUser = new User();


  // private currentRoute: ActivatedRoute,
  constructor(private authService: AuthService,
    private router: Router, private userComp: InventoryItemListComponent) { }

  ngOnInit() {
  }

  login(user: User) {
    console.log('in here');
    console.log(user);

    this.authService.login(user.email, user.password).subscribe(
      data => {
        // this.todo.selected = user;
        this.loginUser = new User();
        // this.openTodos();
      },
      err => {
        console.error('Observer got an error' + err);
      }
    );
    // this.router.navigateByUrl('todo');
  }

  openTodos() {
    // this.router.navigateByUrl('todo');
  }


}
