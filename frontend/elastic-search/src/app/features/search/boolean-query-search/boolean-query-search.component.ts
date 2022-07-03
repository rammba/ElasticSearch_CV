import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Applicant } from '../../models/applicant.model';
import { SearchService } from '../../search.service';

@Component({
  selector: 'app-boolean-query-search',
  templateUrl: './boolean-query-search.component.html',
  styleUrls: ['./boolean-query-search.component.css']
})
export class BooleanQuerySearchComponent implements OnInit {

  form: FormGroup = this.createForm();
  applicants: Applicant[] = [];

  constructor(private searchService: SearchService) { }

  ngOnInit(): void {
  }

  public onSubmit() {
    this.searchService.booleanQuery(this.form.get('query')?.value)
      .subscribe((applicants) => this.applicants = applicants);
  }

  private createForm(): FormGroup {
    return new FormGroup({
      query: new FormControl('')
    });
  }

}
