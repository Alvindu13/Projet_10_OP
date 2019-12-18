import { Component, OnInit } from '@angular/core';
import {HttpService} from '../services/http.service';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {Book} from '../models/book.model';
import {User} from '../models/user.model';
import {AuthenticationService} from '../services/authentication.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  books;
  counts = {};
  book : Book;
  currentUsername;


  constructor(private httpService: HttpService, private authService: AuthenticationService, private route: ActivatedRoute, private router: Router, private http: HttpClient) {
    this.currentUsername = this.authService.username;
  }

  ngOnInit() {
    this.httpService.getAllBooks()
      .subscribe( data => {
        this.books = data;

        this.books.sort((a,b) => (a.name + a.author).localeCompare(b.name + b.author) );
        console.log(this.books);
        this.books.forEach((x) => {
          let id= x.name + x.author;
          this.counts[id] = (this.counts[id] || 0)+1; }
          );

        console.log(this.counts);
      }, err => {
        console.log(err);
      });

  }

  /**
   * Fonction qui permet de réserver un livre
   * Act1 => mettre available à false
   * @param b
   */
  onReserve(b) {
    b.available = false;
    b.borrower.username = this.currentUsername;
    const url : string = 'http://localhost:9005/books/' + b.id.toString() + '/reserve'
    this.httpService.patchResources(url, b).subscribe();
  }

}
