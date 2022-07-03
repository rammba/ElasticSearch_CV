import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  fullName: boolean = false;
  degree: boolean = false;
  cvContent: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

}
