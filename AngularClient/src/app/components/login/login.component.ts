import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/retry';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }

  statusMessage:String = "";

  requestResponseBehavior = data => {
    if(data['status'] !== 'success') { 
      console.log("Not logged in");
      console.log(data['message']); 
    }
    else {
      console.log("Already logged in");
      this.router.navigate(['dashboard']);
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
      this.router.navigate(['dashboard']);
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

  ngOnInit() {
    this.checkLogin();
  }

  submitLogin(e) {
    e.preventDefault();
    const email = e.target.elements['email'].value;
    const password = e.target.elements['password'].value;

    this.login({email: email, password: password});
  }

}
