import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Applicant } from '../../models/applicant.model';
import { SearchService } from '../../search.service';

@Component({
  selector: 'app-geospatial-search',
  templateUrl: './geospatial-search.component.html',
  styleUrls: ['./geospatial-search.component.css']
})
export class GeospatialSearchComponent implements OnInit {

  form: FormGroup = this.createForm();
  applicants: Applicant[] = [];

  constructor(private searchService: SearchService) { }

  ngOnInit(): void {
  }

  public onSubmit() {
    this.searchService.geospatial(this.form.get('city')?.value, this.form.get('radius')?.value)
      .subscribe((applicants) => this.applicants = applicants);
  }

  private createForm(): FormGroup {
    return new FormGroup({
      city: new FormControl(''),
      radius: new FormControl(0)
    });
  }

}
