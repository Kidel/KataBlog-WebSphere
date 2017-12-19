import { Injectable } from '@angular/core';

@Injectable()
export class Globals {
  logged:boolean = false;
  currentPage:String = "home";
  currentUserId = -1; // workaround foe websphere bug. Don't do this
}