import { Component, OnInit } from '@angular/core';
import { LoginComponent } from '../login/login.component';

import { Globals } from '../../globals';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private globals: Globals) { }

  ngOnInit() {  }

  changePage(page:String) {
    this.globals.currentPage = page;
  }

  goHome(e) {
    e.preventDefault();
    this.changePage('home');
  }

  goBlog(e) {
    e.preventDefault();
    this.changePage('blog');
  }

  goUsers(e) {
    e.preventDefault();
    this.changePage('users');
  }

}
