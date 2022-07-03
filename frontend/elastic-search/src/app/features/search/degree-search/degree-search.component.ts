import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Applicant } from '../../models/applicant.model';
import { SearchService } from '../../search.service';

@Component({
  selector: 'app-degree-search',
  templateUrl: './degree-search.component.html',
  styleUrls: ['./degree-search.component.css']
})
export class DegreeSearchComponent implements OnInit {

  form: FormGroup = this.createForm();
  applicants: Applicant[] = [];
  degreeOptions = ['bez skole', '4 razreda', 'osnovna', 'srednja strucna', 'gimnazija', 'fakultet', 'master', 'doktorat'];

  constructor(private searchService: SearchService) { }

  ngOnInit(): void {
  }

  public onSubmit() {
    this.searchService.byDegree(this.form.get('degree')?.value)
      .subscribe((applicants) => this.applicants = applicants);
  }

  private createForm(): FormGroup {
    return new FormGroup({
      degree: new FormControl('')
    });
  }

}
