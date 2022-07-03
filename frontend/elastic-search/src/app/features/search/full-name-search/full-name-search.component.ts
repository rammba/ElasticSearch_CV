import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Applicant } from '../../models/applicant.model';
import { SearchService } from '../../search.service';

@Component({
  selector: 'app-full-name-search',
  templateUrl: './full-name-search.component.html',
  styleUrls: ['./full-name-search.component.css']
})
export class FullNameSearchComponent implements OnInit {

  form: FormGroup = this.createForm();
  applicants: Applicant[] = [];

  constructor(private searchService: SearchService) { }

  ngOnInit(): void {
  }

  public onSubmit() {
    this.searchService.byFullName(this.form.get('name')?.value, this.form.get('surname')?.value)
      .subscribe((applicants) => this.applicants = applicants);
  }

  private createForm(): FormGroup {
    return new FormGroup({
      name: new FormControl(''),
      surname: new FormControl('')
    });
  }

  private getApplicants(applicants: Applicant[]) {
    for (let a of applicants) {
      console.log(a);
    }
  }

}
