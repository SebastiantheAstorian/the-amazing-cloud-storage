import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatTabsModule,
  MatToolbarModule
} from "@angular/material";
import { InsertionComponent } from './insertion/insertion.component';
import { QueryComponent } from './query/query.component';
import { DeletionComponent } from './deletion/deletion.component';
import {FormsModule} from "@angular/forms";
import {WebclientService} from "./service/webclient.service";

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
    MatButtonModule
  ],
  providers: [WebclientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
