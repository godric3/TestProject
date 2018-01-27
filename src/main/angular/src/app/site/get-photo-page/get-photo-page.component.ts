import { Component, OnInit, ChangeDetectorRef, } from '@angular/core';
import { ImageService } from '../../services/image.service';
import { DomSanitizer } from '@angular/platform-browser';
import { SafeUrl } from '@angular/platform-browser/src/security/dom_sanitization_service';
import { MatDialog } from '@angular/material/dialog';
import { FullSizeImageDialogComponent } from './full-size-image-dialog/full-size-image-dialog.component';

export interface ImageData {
  id: string
  safeUrl: SafeUrl

}

@Component({
  selector: 'app-get-photo-page',
  templateUrl: './get-photo-page.component.html',
  styleUrls: ['./get-photo-page.component.css']
})

export class GetPhotoPageComponent implements OnInit {
  selected: string
  filtrType: any
  filtrType2: any
  tags: string[]
  imageContener: ImageData[];
  filtrs = [
    { value: 'filtr0', viewValue: 'Kategoria' },
    { value: 'filtr1', viewValue: 'Tagi' },
    { value: 'filtr2', viewValue: 'Rozmiar' },
    { value: 'filtr3', viewValue: 'Rozszerzenie' },
    { value: 'filtr4', viewValue: 'Nazwa' }
  ];

  constructor(private imageService: ImageService, private sanitizer: DomSanitizer, public dialog: MatDialog) { }

  ngOnInit() {
    this.imageContener = []
  }

  addTagToTagsList() {
    if (this.filtrType) {
      this.tags.push(this.filtrType);
      this.filtrType = '';
    }
  }

  showChange() {
    console.log('change')
    this.filtrType = null
    this.filtrType2 = null
  }
  checkSelected(value: string) {
    if (this.selected == value) {
      return true
    }
    else
      return false
  }

  searchImage() {
    this.imageContener = []
    this.imageService.getImage(this.filtrType).subscribe(res => {
      const imageUrl = URL.createObjectURL(res);
      let trustURl = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
      this.imageContener.push({ id: this.filtrType, safeUrl: trustURl })
    })
  }

  getImageUrlById(id: string) {
    this.imageService.getImage(id).subscribe(res => {
      const imageUrl = URL.createObjectURL(res);
      return imageUrl
    })
    return ''

  }

  getAllImages() {
    this.imageService.getAllImages().subscribe(res => {
      this.imageContener = []
      let array: any
      array = res
      array.forEach(element => {
        this.imageService.getImage(element).subscribe(res => {
          const imageUrl = URL.createObjectURL(res);
          let trustURl = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
          this.imageContener.push({ id: (element as string), safeUrl: trustURl })
        })

      })
    })
  }

  openDialog(imageData): void {
    let dialogRef = this.dialog.open(FullSizeImageDialogComponent, {
      height: '80%',
      width: '80%',
      data: { image: imageData }
    });
  }
}
