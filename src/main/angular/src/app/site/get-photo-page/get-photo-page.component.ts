import { Component, OnInit, ChangeDetectorRef, } from '@angular/core';
import { ImageService } from '../../services/image.service';
import { DomSanitizer } from '@angular/platform-browser';
import { SafeUrl } from '@angular/platform-browser/src/security/dom_sanitization_service';

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
  imageContener: SafeUrl[];
  filtrs = [
    { value: 'filtr0', viewValue: 'Kategoria' },
    { value: 'filtr1', viewValue: 'Tagi' },
    { value: 'filtr2', viewValue: 'Rozmiar' },
    { value: 'filtr3', viewValue: 'Rozszerzenie' },
    { value: 'filtr4', viewValue: 'Nazwa' }
  ];

  constructor(private imageService: ImageService, private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.imageContener = []
  }

  addTagToTagsList() {
    this.tags.push(this.filtrType);
    this.filtrType = '';
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
      this.imageContener.push(this.sanitizer.bypassSecurityTrustUrl(imageUrl))
    })
  }

  getAllImages() {
    this.imageService.getAllImages().subscribe(res => {
      console.log(res)
    })

  }
}
