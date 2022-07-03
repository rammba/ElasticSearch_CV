import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Applicant } from '../../models/applicant.model';
import { SearchService } from '../../search.service';

@Component({
  selector: 'app-phrase-search',
  templateUrl: './phrase-search.component.html',
  styleUrls: ['./phrase-search.component.css']
})
export class PhraseSearchComponent implements OnInit {

  form: FormGroup = this.createForm();
  applicants: Applicant[] = [];
  fieldOptions = ['name', 'surname', 'degree', 'cvContent'];

  constructor(private searchService: SearchService) { }

  ngOnInit(): void {
  }

  public onSubmit() {
    this.searchService.phrase(this.form.get('field')?.value, this.form.get('value')?.value)
      .subscribe((applicants) => this.applicants = applicants);
  }

  private createForm(): FormGroup {
    return new FormGroup({
      field: new FormControl(''),
      value: new FormControl('')
    });
  }

}
