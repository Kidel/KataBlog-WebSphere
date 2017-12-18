import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/retry';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  constructor(private http: HttpClient) { }

  posts = [];

  requestResponseBehavior = data => {
    if (data['status'] === 'success') {
      this.posts = data['message'];
    } else {
      console.log('Error from the server');
    }
  }

  requestErrorBehavior = err => {
    console.log('Something went wrong with the request!');
    console.log(err);
  }

  ngOnInit() {
    // Make the HTTP request:
    this.http.get('../posts').retry(3).subscribe(
      this.requestResponseBehavior,
      this.requestErrorBehavior
    );
  }

}
