import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Applicant } from '../../models/applicant.model';
import { SearchService } from '../../search.service';

@Component({
  selector: 'app-cv-content-search',
  templateUrl: './cv-content-search.component.html',
  styleUrls: ['./cv-content-search.component.css']
})
export class CvContentSearchComponent implements OnInit {

  form: FormGroup = this.createForm();
  applicants: Applicant[] = [];

  constructor(private searchService: SearchService) { }

  ngOnInit(): void {
  }

  public onSubmit() {
    this.searchService.byCvContent(this.form.get('content')?.value)
      .subscribe((applicants) => this.applicants = applicants);
  }

  private createForm(): FormGroup {
    return new FormGroup({
      content: new FormControl('')
    });
  }

}
