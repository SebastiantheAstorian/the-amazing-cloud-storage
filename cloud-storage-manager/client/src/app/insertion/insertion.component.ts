import {Component, OnInit} from '@angular/core';
import {BikeData} from '../model/BikeData';
import {WebclientService} from '../service/webclient.service';
import {MatSnackBar} from '@angular/material';
import * as Papa from 'papaparse';
import {from} from 'rxjs';
import {finalize, flatMap} from 'rxjs/operators';

@Component({
  selector: 'app-insertion',
  templateUrl: './insertion.component.html',
  styleUrls: ['./insertion.component.scss']
})
export class InsertionComponent implements OnInit {
  initialvalue: BikeData;
  value: BikeData = {
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

  constructor(private webClient: WebclientService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    // copy initialize
    this.initialvalue = {...this.value};
  }

  addValue() {
    this.webClient.insertValue(this.value).subscribe(
      response => {
        this.snackBar.open('Insert successful. Key: ' + response, 'Close', {duration: 10000});
        this.value = this.initialvalue;
      },
      error => {
        console.log('error occured on insert!');
        this.snackBar.open('Could not insert key. Are you sure it does not exist yet?', 'Close');
      },
    );
  }

  addValues(data: BikeData[]) {
    from(data)
      .pipe(
        finalize(() => {
          this.snackBar.open('All values uploaded. Key is Trip Id', 'Close', {duration: 3000});

        }),
        flatMap(value => {
          return this.webClient.insertValue(value);
        }),
      )
      .subscribe(response => {
          console.log('sent value', response);
        },
        error => {
          console.log('error occured on insert!');
          this.snackBar.open('There was an error uploading the input values', 'Close', {duration: 3000});
        }
      );
  }


  changeListener(files: FileList) {
    console.log(files);
    if (files && files.length > 0) {
      const file: File = files.item(0);
      Papa.parse(file, {
        header: true,
        skipEmptyLines: true,
        complete: (result, inputFile) => {
          console.log(result);
          this.addValues(result.data);
        },
        delimiter: ';'
      });

    }
  }
}

