import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-adding-photo-result-dialog',
  templateUrl: './adding-photo-result-dialog.component.html',
  styleUrls: ['./adding-photo-result-dialog.component.css']
})
export class AddingPhotoResultDialogComponent implements OnInit {

  success;
  constructor(public dialogRef: MatDialogRef<AddingPhotoResultDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }
  
  
    ngOnInit() {
      this.success = this.data.success
  }


}
