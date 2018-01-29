import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { ImageService } from '../../services/image.service';
import { UserService } from '../../services/user-service.service';
import { FullSizeImageDialogComponent } from '../get-photo-page/full-size-image-dialog/full-size-image-dialog.component';
import { MatDialog } from '@angular/material/dialog';

export interface ImageData {
  id: string
  safeUrl: SafeUrl

}
@Component({
  selector: 'app-my-photos-page',
  templateUrl: './my-photos-page.component.html',
  styleUrls: ['./my-photos-page.component.css']
})
export class MyPhotosPageComponent implements OnInit {

  imageContener: ImageData[];
  constructor(private imageService: ImageService, private sanitizer: DomSanitizer, private userService: UserService,public dialog: MatDialog) { }

  ngOnInit() {
    this.imageContener = []
    this.imageService.getImagesByUserName(this.userService.getLoggedUserName()).subscribe(image => {
      this.addFewImagesToContainer(image);
    })
  }

  private addFewImagesToContainer(res: Object) {
    this.imageContener = [];
    let array: any;
    array = res;
    array.forEach(element => {
      this.imageService.getImage(element).subscribe(res => {
        const imageUrl = URL.createObjectURL(res);
        let trustURl = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
        this.imageContener.push({ id: (element as string), safeUrl: trustURl });
      });
    });
  }

  openDialog(imageData): void {
    let dialogRef = this.dialog.open(FullSizeImageDialogComponent, {
      height: '80%',
      width: '80%',
      data: { image: imageData }
    });
  }

}
