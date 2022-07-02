import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { SearchService } from '../../search.service';

@Component({
  selector: 'app-full-name-search',
  templateUrl: './full-name-search.component.html',
  styleUrls: ['./full-name-search.component.css']
})
export class FullNameSearchComponent implements OnInit {

  form: FormGroup = this.createForm();

  constructor(private searchService: SearchService) { }

  ngOnInit(): void {
  }

  public onSubmit() {
    this.searchService.byFullName(this.form.get('name')?.value, this.form.get('surname')?.value);
  }

  private createForm(): FormGroup {
    return new FormGroup({
      name: new FormControl(''),
      surname: new FormControl('')
    });
  }

}
