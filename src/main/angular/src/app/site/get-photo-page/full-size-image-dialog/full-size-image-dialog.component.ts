import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-full-size-image-dialog',
  templateUrl: './full-size-image-dialog.component.html',
  styleUrls: ['./full-size-image-dialog.component.css']
})
export class FullSizeImageDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<FullSizeImageDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }
  image;

  ngOnInit() {
    this.image = this.data.image
  }

}
