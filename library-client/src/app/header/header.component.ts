import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {BookSearchComponent} from '../book-list/book-search/book-search.component';
import {HttpService} from '../services/http.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  userCurrentId;

  constructor(private authService: AuthenticationService) {
    this.userCurrentId = this.authService.currentId
  }

  ngOnInit() {

  }

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  logout() {
    this.authService.logout();
  }


}
