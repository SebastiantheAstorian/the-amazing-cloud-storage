import { Component, OnInit } from '@angular/core';
import {WebclientService} from "../service/webclient.service";

@Component({
  selector: 'app-query',
  templateUrl: './query.component.html',
  styleUrls: ['./query.component.scss']
})
export class QueryComponent implements OnInit {
  queryValue: string;

  constructor(private webclient: WebclientService) { }

  ngOnInit() {
  }

  querySingleValue() {

  }
}
