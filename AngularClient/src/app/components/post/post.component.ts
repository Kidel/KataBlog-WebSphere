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
      console.log('Request successful');
      this.posts = data['message'];
    } else {
      console.log('Error from the server');
    }
  }

  postResponseBehavior = data => {
    if (data['status'] === 'success') {
      console.log('Post successful');
    } else {
      console.log('Error from the server');
      console.log(data['message']);
    }
  }

  requestErrorBehavior = err => {
    console.log('Something went wrong with the request!');
    console.log(err);
  }

  getPosts() {
    this.http.get('../posts').retry(3).subscribe(
      this.requestResponseBehavior,
      this.requestErrorBehavior
    );
  }

  addEditor(data) {
    this.http.post('../posts', data).retry(3).subscribe(
      this.postResponseBehavior,
      this.requestErrorBehavior
    );
  }

  ngOnInit() {
    this.getPosts();
  }

  submitPost(e) {
    e.preventDefault();

    // TODO
  }

}
