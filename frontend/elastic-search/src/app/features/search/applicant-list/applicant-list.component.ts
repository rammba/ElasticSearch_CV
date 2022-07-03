import { Component, Input, OnInit } from '@angular/core';
import { Applicant } from '../../models/applicant.model';

@Component({
  selector: 'app-applicant-list',
  templateUrl: './applicant-list.component.html',
  styleUrls: ['./applicant-list.component.css']
})
export class ApplicantListComponent implements OnInit {

  @Input()
  applicants: Applicant[] = [];

  displayedColumns: string[] = ['name', 'surname', 'degree', 'latitude', 'longitude'];

  constructor() { }

  ngOnInit(): void {
  }

}
