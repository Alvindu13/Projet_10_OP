import { Component, OnInit } from '@angular/core';
import {HttpService} from '../../services/http.service';
import {AuthenticationService} from '../../services/authentication.service';
import {Book} from '../../models/book.model';

@Component({
  selector: 'app-book-reserve',
  templateUrl: './book-reserve.component.html',
  styleUrls: ['./book-reserve.component.css']
})
export class BookReserveComponent implements OnInit {

  books;
  currentUserId;

  constructor(private httpService: HttpService, private authService: AuthenticationService) {
    this.currentUserId = this.authService.currentId;
  }

  ngOnInit() {
    const url = 'http://localhost:9005/books/user'
    this.httpService.getResources(url)
      .subscribe(data => {
          console.log(data);
          this.books = data;
          console.log('books', this.books);
      }, err => {
          console.log(err);
      });
    }

  onExtend(b: Book) {
    const url = 'http://localhost:9005/books/extend/' + b.id;
    this.httpService.patchResources(url, b)
      .subscribe();
  }
}
