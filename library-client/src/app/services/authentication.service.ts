import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {JwtHelperService} from '@auth0/angular-jwt';
import {User} from '../models/user.model';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  host: string = environment.apiUrl;
  jwt: string;
  username: string;
  currentId:number;
  roles: Array<string>;
  isAuth: boolean = false;

  constructor(private http: HttpClient) {}

  login(data) {
    return this.http.post(this.host + '/login', data, {observe : 'response'});
  }

  saveToken(jwt: string) {
    localStorage.setItem('token', jwt);
    this.jwt = jwt;
    this.loadUserIdentity();

  }

  // JwtParse/decode
  private loadUserIdentity() {
    const jwtHelper = new JwtHelperService();
    const objJWT = jwtHelper.decodeToken(this.jwt);
    this.username = objJWT.sub;
    this.roles = objJWT.roles;
    this.isAuth = true;
  }

  isAdmin() {
    return this.roles.indexOf('ADMIN') >= 0;
  }

  isUser() {
    return this.roles.indexOf('USER') >= 0;
  }

  isAuthenticated() {
    return this.roles && (this.isAdmin() || this.isUser());
  }

  loadToken() {
    this.jwt = localStorage.getItem('token');
    this.loadUserIdentity();
  }

  logout() {
    localStorage.removeItem('token');
    this.isAuth = false;
    this.initParams();
  }

  initParams() {
    this.jwt = undefined;
    this.username = undefined;
    this.roles = undefined;
  }
}
