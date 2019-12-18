import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthenticationService} from './authentication.service';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';



@Injectable({
  providedIn: 'root'
})
export class HttpService {

  public host : string = environment.apiUrl;

  constructor(private http: HttpClient, private authService: AuthenticationService) {
  }

  getAllBooks() {
    return this.http.get( this.host + '/books');
  }

  getSinglebook(id: number){
    console.log(this.host + '/books/' + id);
    return this.http.get(this.host + '/books/' + id);
  }

  register(url, data) {
    return this.http.post(url, data);
  }

  getResources(url) {
    const header = new HttpHeaders({'Authorization': 'Bearer ' + this.authService.jwt})
    return this.http.get(url,{headers: header});
  }

  deleteResources(url) {
    const header = new HttpHeaders({'Authorization': 'Bearer ' + this.authService.jwt})
    return this.http.delete(url, {headers: header});
  }


  postResources(url, data) {
    const header = new HttpHeaders({'Authorization': 'Bearer ' + this.authService.jwt})
    return this.http.post(url, data, {headers: header});
  }

  putResources(url, data) {
    const header = new HttpHeaders({'Authorization': 'Bearer ' + this.authService.jwt})
    return this.http.put(url, data, {headers: header});
  }

  patchResources(url, data) {
    const header = new HttpHeaders({'Authorization': 'Bearer ' + this.authService.jwt})
    return this.http.patch(url, data, {headers: header});
  }

}
