import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/retry';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  constructor(private http: HttpClient) { }

  editors = [];

  requestResponseBehavior = data => {
    if (data['status'] === 'success') {
      this.editors = data['message'];
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
    this.http.get('../editors').retry(3).subscribe(
      this.requestResponseBehavior,
      this.requestErrorBehavior
    );
  }

}
