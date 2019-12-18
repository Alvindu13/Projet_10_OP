import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpService} from '../../services/http.service';
import {Router} from '@angular/router';
import {Book} from '../../models/book.model';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {
  bookForm : FormGroup;
  public host : string = environment.apiUrl + '/books';

  constructor(private formBuilder: FormBuilder,
              private httpService: HttpService,
              private router: Router) { }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {
    this.bookForm = this.formBuilder.group({
      name: ['', Validators.required],
      author: ['', Validators.required],
      genre: ['', Validators.required],
      price: ['', Validators.required]
    });
  }

  onSavebook() {
    const title = this.bookForm.get('name').value;
    const author = this.bookForm.get('author').value;
    const genre = this.bookForm.get('genre').value;
    const price = this.bookForm.get('price').value;
    const newBook = new Book(title, author, genre, price);
    this.httpService.postResources(this.host, newBook).subscribe();
    this.router.navigate(['/books'])
  }

}
