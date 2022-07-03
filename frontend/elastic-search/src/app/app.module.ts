import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { JobApplicationComponent } from './features/job-application/job-application.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { HttpClientModule } from '@angular/common/http';
import { SearchComponent } from './features/search/search.component';
import { FullNameSearchComponent } from './features/search/full-name-search/full-name-search.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { ApplicantListComponent } from './features/search/applicant-list/applicant-list.component';
import { MatTableModule } from '@angular/material/table';
import { DegreeSearchComponent } from './features/search/degree-search/degree-search.component';
import { CvContentSearchComponent } from './features/search/cv-content-search/cv-content-search.component';
import { MatSelectModule } from '@angular/material/select';
import { BooleanSimpleSearchComponent } from './features/search/boolean-simple-search/boolean-simple-search.component';
import { PhraseSearchComponent } from './features/search/phrase-search/phrase-search.component';
import { GeospatialSearchComponent } from './features/search/geospatial-search/geospatial-search.component';

@NgModule({
  declarations: [
    AppComponent,
    JobApplicationComponent,
    SearchComponent,
    FullNameSearchComponent,
    ApplicantListComponent,
    DegreeSearchComponent,
    CvContentSearchComponent,
    BooleanSimpleSearchComponent,
    PhraseSearchComponent,
    GeospatialSearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    HttpClientModule,
    MatCheckboxModule,
    MatTableModule,
    MatSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
