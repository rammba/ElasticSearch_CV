import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JobService } from '../job.service';

@Component({
  selector: 'app-job-application',
  templateUrl: './job-application.component.html',
  styleUrls: ['./job-application.component.css']
})
export class JobApplicationComponent implements OnInit {

  form: FormGroup = this.createForm();
  uploadedCv!: File;
  degreeOptions = ['bez skole', '4 razreda', 'osnovna', 'srednja strucna', 'gimnazija', 'fakultet', 'master', 'doktorat'];

  constructor(private jobService: JobService, private router: Router) { }

  ngOnInit(): void {
  }

  public onSubmit() {
    if (!this.form.valid || this.uploadedCv === undefined) {
      alert('Invalid form');
      return;
    }

    this.jobService.apply(this.form.get('name')?.value,
      this.form.get('surname')?.value,
      this.form.get('mail')?.value,
      this.form.get('address')?.value,
      this.form.get('degree')?.value,
      this.uploadedCv)
      .subscribe(success => {
        if (success) {
          this.router.navigate(['/']);
        } else {
          alert('Error');
        }
      });
  }

  public handleCvInput(target: EventTarget | null) {
    var input = target as HTMLInputElement;
    var files = input.files;
    var cv = files?.item(0);
    if (cv === undefined || cv?.type !== 'application/pdf') {
      input.value = '';
      alert('Only pdf supported');
      return;
    }

    this.uploadedCv = cv;
  }

  private createForm(): FormGroup {
    return new FormGroup({
      name: new FormControl('', [Validators.required]),
      surname: new FormControl('', [Validators.required]),
      mail: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
      degree: new FormControl('', [Validators.required])
    });
  }

}
