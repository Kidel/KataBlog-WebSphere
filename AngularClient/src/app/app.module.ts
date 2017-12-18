import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { Globals } from './globals';

import { AppComponent } from './app.component';
import { EditorComponent } from './components/editor/editor.component';
import { PostComponent } from './components/post/post.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    EditorComponent,
    PostComponent,
    LoginComponent,
    NavbarComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [Globals],
  bootstrap: [AppComponent]
})
export class AppModule { }
