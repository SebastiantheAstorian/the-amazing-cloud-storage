import {Component, OnInit} from '@angular/core';
import {WebclientService} from "../service/webclient.service";
import {BikeData} from "../model/BikeData";
import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'app-query',
  templateUrl: './query.component.html',
  styleUrls: ['./query.component.scss']
})
export class QueryComponent implements OnInit {
  queryValue: string = "";
  toValue: string = "";
  fromValue: string = "";

  displayedColumns: string[] = [
    'trip_id', 'year', 'month', 'week', 'day', 'hour',
    'usertype', 'gender', 'starttime', 'stoptime', 'tripduration', 'temperature',
    'events', 'from_station_name', 'to_station_name'
  ];

  dataSource: BikeData[] = [
    {
      trip_id: '123',
      year: 2020,
      month: 12,
      week: 3,
      day: 17,
      hour: 11,
      usertype: 'Subscriber',
      gender: 'Male',
      starttime: '2014-06-30 23:33:00',
      stoptime: '2014-06-30 23:33:00',
      tripduration: 19.1254655251651,
      temperature: 68.3,
      events: 'deadly fireballs',
      from_station_id: '1234',
      from_station_name: 'home-station',
      dpcapacity_end: 123,
      dpcapacity_start: 111,
      latitude_end: 831,
      latitude_start: 110,
      longitude_end: 111,
      longitude_start: 123,
      to_station_id: 'da_finish',
      to_station_name: 'this da finish'
    },
    {
      trip_id: '123',
      year: 2020,
      month: 12,
      week: 3,
      day: 17,
      hour: 11,
      usertype: 'Subscriber',
      gender: 'Male',
      starttime: '2014-06-30 23:33:00',
      stoptime: '2014-06-30 23:33:00',
      tripduration: 19.1254655251651,
      temperature: 68.3,
      events: 'deadly fireballs',
      from_station_id: '1234',
      from_station_name: 'home-station',
      dpcapacity_end: 123,
      dpcapacity_start: 111,
      latitude_end: 123.2,
      latitude_start: 123,
      longitude_end: 1,
      longitude_start: 123,
      to_station_id: 'da_finish',
      to_station_name: 'this da finish'
    }
  ];

  constructor(private webclient: WebclientService, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
  }

  querySingleValue() {
    this.webclient.querySingleValue(this.queryValue).subscribe(response => {
        this.dataSource = [response];
        this.queryValue = '';
      },
      (error) => {
        this._snackBar.open('There was an error uploading the input values', 'Close', {duration: 3000});
      });
  }

  queryRange() {
    this.webclient.queryRange(this.fromValue, this.toValue).subscribe(response => {
        this.dataSource = response;
        this.fromValue = '';
        this.toValue = '';
      },
      (error) => {
        this._snackBar.open('There was an error uploading the input values', 'Close', {duration: 3000});
      }
    );
  }
}
