import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/retry';

import { Globals } from '../../globals';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private globals: Globals, private http: HttpClient) { }

  statusMessage:String = "";

  requestResponseBehavior = data => {
    if(data['status'] !== 'success') { 
      console.log("Error");
      console.log(data['message']); 
    }
    else {
      console.log("Success");
      this.globals.logged=true
    }
  }

  logoutResponseBehavior = data => {
    if(data['status'] !== 'success') { 
      console.log("Error");
      console.log(data['message']); 
    }
    else {
      console.log("Success");
      this.globals.logged=false
    }
  }

  loginResponseBehavior = data => {
    if(data['status'] !== 'success') { 
      console.log("Error logging in");
      console.log(data['message']); 
      this.statusMessage = data['message'];
    }
    else {
      console.log("Logged in");
      this.globals.logged=true
    }
  }

  requestErrorBehavior = err => { 
    console.log('Something went wrong with the request!'); 
    console.log(err['message']); 
  }; 

  checkLogin() {
    this.http.get('../login').retry(3).subscribe(
      this.requestResponseBehavior, 
      this.requestErrorBehavior
    );
  }

  login(data) {
    this.http.post('../login', data).retry(3).subscribe(
      this.loginResponseBehavior, 
      this.requestErrorBehavior
    );
  }

  logout() {
    this.http.delete('../login').retry(3).subscribe(
      this.logoutResponseBehavior, 
      this.requestErrorBehavior
    );
  }

  ngOnInit() {
    console.log("Checking login...");
    this.checkLogin();
  }

  submitLogin(e) {
    e.preventDefault();
    const email = e.target.elements['email'].value;
    const password = e.target.elements['password'].value;

    console.log("Logging in...");
    this.login({email: email, password: password});
  }

  submitLogout(e) { 
    e.preventDefault();
    console.log("Logging out...");
    this.logout();
  }

}
