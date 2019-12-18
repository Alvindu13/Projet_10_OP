import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';
import {HttpService} from '../services/http.service';
import {environment} from '../../environments/environment';
import {Book} from '../models/book.model';
import {User} from '../models/user.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  url = environment.apiUrl + '/register';
  userForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private httpService: HttpService,
              private router: Router) { }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {
    this.userForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern(/[0-9a-zA-Z]{6,}/)]],
      confirmedPassword: ['', [Validators.required, Validators.pattern(/[0-9a-zA-Z]{6,}/)]],
    });
  }

  onSubmitForm() {

    const email = this.userForm.get('email').value;
    const password = this.userForm.get('password').value;
    const confirmedPassword = this.userForm.get('confirmedPassword').value;
    const newUser = new User(email, password, confirmedPassword);
    this.httpService.register(this.url, newUser).subscribe()
    this.router.navigate(['/login'])
  }
}
