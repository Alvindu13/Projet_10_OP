import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
  }

  onLogin(data) {
    this.authService.login(data)
      .subscribe(resp => {
        const jwt = resp.headers.get('Authorization');
        console.log(jwt)
        this.authService.saveToken(jwt);
        this.authService.username = data.username;
        this.router.navigateByUrl('/');
      }, err => {
        console.log(err);
      });

  }


}
