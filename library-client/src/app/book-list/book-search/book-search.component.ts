import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {HttpService} from '../../services/http.service';
import {HttpClient} from '@angular/common/http';
import {fromEvent, of} from 'rxjs';
import {debounceTime, distinctUntilChanged, filter, map} from 'rxjs/operators';

@Component({
  selector: 'app-book-search',
  templateUrl: './book-search.component.html',
  styleUrls: ['./book-search.component.css']
})
export class BookSearchComponent implements OnInit {
  // @ts-ignore
  @ViewChild('bookSearchInput') bookSearchInput: ElementRef;
  apiResponse:any;
  isSearching:boolean;
  isSuccess: boolean;

  constructor(
    private httpClient: HttpClient
  ) {
    this.isSearching = false;
    this.apiResponse = [];
    this.isSuccess = true;
  }

  ngOnInit() {
    fromEvent(this.bookSearchInput.nativeElement, 'keyup').pipe(
      // get value
      map((event: any) => {
        return event.target.value;
      })
      // if character length greater then 2
      , filter(res => res.length > 2)
      // Time in milliseconds between key events
      , debounceTime(1000)
      // If previous query is diffent from current
      , distinctUntilChanged()
      // subscription for response
    ).subscribe((text: string) => {
      this.isSearching = true;
      this.searchGetCall(text).subscribe((res) => {
        console.log('res', res);
        this.isSearching = false;
        this.apiResponse = res;
        if(this.apiResponse.length === 0) {
          this.isSuccess = false;
        }
        console.log('s', this.isSuccess);

        console.log('tableau', this.apiResponse);
      }, ( err ) => {
        this.isSearching = false;
        console.log('error', err);
      });
    });
  }

  searchGetCall(term: string) {
    if (term === '') {
      return of ([]);
    }
    return this.httpClient.get('http://localhost:9005/books/selected/' + term);
  }

}
