import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ImageService } from '../../../services/image.service';

@Component({
  selector: 'app-full-size-image-dialog',
  templateUrl: './full-size-image-dialog.component.html',
  styleUrls: ['./full-size-image-dialog.component.css']
})
export class FullSizeImageDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<FullSizeImageDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private imageService: ImageService) { }
  image;
  dataImage;
  imageId;
  tagsString: string

  ngOnInit() {
    this.image = this.data.image.safeUrl
    this.imageId = this.data.image.id
    this.imageService.getImageInfo(this.data.image.id).subscribe(res => {
      this.dataImage = res
      this.tagsString = ''
      this.dataImage.tags.forEach(tag => {
        this.tagsString += tag + ' '
      });

    })
  }

}
