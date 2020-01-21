import { Component, OnInit } from '@angular/core';
import {BikeData} from "../model/BikeData";

@Component({
  selector: 'app-insertion',
  templateUrl: './insertion.component.html',
  styleUrls: ['./insertion.component.scss']
})
export class InsertionComponent implements OnInit {
  value: BikeData=  {
    trip_id: '',
    year: null,
    month: null,
    week: null,
    day: null,
    hour: null,
    usertype: '',
    gender: '',
    starttime: null,
    stoptime: null,
    tripduration: null,
    temperature: null,
    events: '',
    from_station_id: '',
    from_station_name: '',
    dpcapacity_end: null,
    dpcapacity_start: null,
    latitude_end: null,
    latitude_start: null,
    longitude_end: null,
    longitude_start: null,
    to_station_id: '',
    to_station_name: ''
  };

  constructor() { }

  ngOnInit() {
  }

  addValue() {
    console.log(this.value);
  }

}

