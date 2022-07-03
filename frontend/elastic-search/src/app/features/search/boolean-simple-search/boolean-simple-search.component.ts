import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Applicant } from '../../models/applicant.model';
import { SearchService } from '../../search.service';

@Component({
  selector: 'app-boolean-simple-search',
  templateUrl: './boolean-simple-search.component.html',
  styleUrls: ['./boolean-simple-search.component.css']
})
export class BooleanSimpleSearchComponent implements OnInit {

  form: FormGroup = this.createForm();
  applicants: Applicant[] = [];
  fieldOptions = ['name', 'surname', 'degree', 'cvContent'];

  constructor(private searchService: SearchService) { }

  ngOnInit(): void {
  }

  public onSubmit() {
    this.searchService.booleanSimple(this.form.get('key1')?.value,
      this.form.get('value1')?.value,
      this.form.get('key2')?.value,
      this.form.get('value2')?.value,
      this.form.get('isAndOperation')?.value)
      .subscribe((applicants) => this.applicants = applicants);
  }

  private createForm(): FormGroup {
    return new FormGroup({
      key1: new FormControl(''),
      key2: new FormControl(''),
      value1: new FormControl(''),
      value2: new FormControl(''),
      isAndOperation: new FormControl(false)
    });
  }
}
