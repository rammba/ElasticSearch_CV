import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { JobApplicationComponent } from './features/job-application/job-application.component';
import { SearchComponent } from './features/search/search.component';

const routes: Routes = [
  { path: 'job-application', component: JobApplicationComponent },
  { path: 'search', component: SearchComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
