import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/retry';

import { Globals } from '../../globals';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  constructor(private globals: Globals, private http: HttpClient) { }

  posts = [];
  statusMessage:String = "";

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
      this.getPosts();
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

  addPost(data) {
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
    const title = e.target.elements['title'].value;
    const content = e.target.elements['content'].value;
    if(this.globals.logged) {
      this.addPost({title: title, content: content});
      this.statusMessage = "";
    }
    else 
      this.statusMessage = "Not logged in";
  }

}
