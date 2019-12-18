import { Component, OnInit } from '@angular/core';
import {HttpService} from '../services/http.service';

@Component({
  selector: 'app-admin-books',
  templateUrl: './admin-books.component.html',
  styleUrls: ['./admin-books.component.css']
})
export class AdminBooksComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    // this.onGetAllBooks();
  }
/*
  onGetAllBooks() {
    this.booksService.getAllBooks()
      .subscribe(data => {
        this.books = data;
      }, err => {
        console.log(err);
      });
  }

  onDeleteBooks(books) {
    const c = confirm(' êtes-vous sûre de voulour supprimer ? ')
    if (!c) { return; }
    this.booksService.deleteResources(books._links.self.href)
      .subscribe(data => {
        this.onGetAllBooks();
      }, err => {
        console.log(err);
      });
  }

  onNewBook() {
    this.mode = 'new-book';
  }

  onSaveBook(data) {
    const url = this.booksService.host + '/books';
    this.booksService.postResources(url, data)
    // tslint:disable-next-line:no-shadowed-variable
      .subscribe(data => {
        this.mode = 'list';
        this.onGetAllBooks();
      }, err => {
        console.log(err);
      });
  }

  onEditBook(books) {
    this.booksService.getResources(books._links.self.href)
      .subscribe(data => {
        this.currentBook = data;
        this.mode = 'edit-book';
      }, err => {
        console.log(err);
      });
  }

  onUpdateBook(data) {
    this.booksService.putResources(this.currentBook._links.self.href, data)
    // tslint:disable-next-line:no-shadowed-variable
      .subscribe(data => {
        this.mode = 'list';
        this.onGetAllBooks();
      }, err => {
        console.log(err);
      });
  }*/
}
