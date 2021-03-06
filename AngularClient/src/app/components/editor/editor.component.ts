import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/retry';

import { Globals } from '../../globals';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  constructor(private globals: Globals, private http: HttpClient) { }

  editors = [];

  requestResponseBehavior = data => {
    if (data['status'] === 'success') {
      console.log('Request successful');
      this.editors = data['message'];
    } else {
      console.log('Error from the server');
    }
  }

  editResponseBehavior = data => {
    if (data['status'] === 'success') {
      console.log('Request successful');
    } else {
      console.log('Error from the server');
    }
    this.getEditors();
  }

  postResponseBehavior = data => {
    if (data['status'] === 'success') {
      console.log('Post successful');
      this.getEditors();
    } else {
      console.log('Error from the server');
      console.log(data['message']);
    }
  }

  requestErrorBehavior = err => {
    console.log('Something went wrong with the request!');
    console.log(err);
  }

  getEditors() {
    this.http.get('../editors').retry(3).subscribe(
      this.requestResponseBehavior,
      this.requestErrorBehavior
    );
  }

  addEditor(data) {
    this.http.post('../editors', data).retry(3).subscribe(
      this.postResponseBehavior,
      this.requestErrorBehavior
    );
  }

  ngOnInit() {
    this.getEditors();
  }

  submitEditor(e) {
    e.preventDefault();
    const name = e.target.elements['name'].value;
    const email = e.target.elements['email'].value;
    const password = e.target.elements['password'].value;

    this.addEditor({name: name, email: email, password: password});
  }

  deleteAllEditors(e) {
    e.preventDefault();
    this.http.delete('../editors').retry(3).subscribe(
      this.editResponseBehavior,
      this.requestErrorBehavior
    );
  }

}
