import {Component, OnInit} from '@angular/core';
import {WebclientService} from '../service/webclient.service';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-deletion',
  templateUrl: './deletion.component.html',
  styleUrls: ['./deletion.component.scss']
})
export class DeletionComponent implements OnInit {
  deleteValue: string;

  constructor(private webclient: WebclientService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
  }

  delete() {
    this.webclient.deleteValue(this.deleteValue).subscribe(
      response => {
        if (response) {
          this.snackBar.open('Deleted value with key: ' + this.deleteValue, 'Close', {duration: 3000});
          this.deleteValue = '';
        } else {
          this.snackBar.open('Could not find value with key: ' + this.deleteValue, 'Close', {duration: 3000});
        }
      },
      error => {
        this.snackBar.open('Error occured deleting value with key: ' + this.deleteValue, 'Close', {duration: 3000});
      }
    );
  }
}
