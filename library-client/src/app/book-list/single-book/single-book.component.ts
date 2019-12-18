import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpService} from '../../services/http.service';
import {Book} from '../../models/book.model';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-single-book',
  templateUrl: './single-book.component.html',
  styleUrls: ['./single-book.component.css']
})
export class SingleBookComponent implements OnInit {

  book : Object;

  constructor(private route: ActivatedRoute,
              private httpService: HttpService,
              private router : Router) { }

  ngOnInit() {
    this.book = new Book('', '', '', '');
    const id = this.route.snapshot.params['id'];

    this.httpService.getSinglebook(+id)
      .subscribe( data => {
        this.book = data;
      }, err => {
        console.log(err);
      });
  }

  onBack() {
    this.router.navigate(['/books']);
  }

}
