import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule, MatGridListModule,
  MatInputModule, MatSnackBarModule, MatTableModule,
  MatTabsModule,
  MatToolbarModule
} from '@angular/material';
import { InsertionComponent } from './insertion/insertion.component';
import { QueryComponent } from './query/query.component';
import { DeletionComponent } from './deletion/deletion.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {WebclientService} from './service/webclient.service';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    InsertionComponent,
    QueryComponent,
    DeletionComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatToolbarModule,
    MatTabsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    HttpClientModule,
    MatGridListModule,
    MatTableModule,
    ReactiveFormsModule,
    MatSnackBarModule
  ],
  providers: [WebclientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
