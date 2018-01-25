import { Component, OnInit, ChangeDetectorRef, } from '@angular/core';

@Component({
  selector: 'app-get-photo-page',
  templateUrl: './get-photo-page.component.html',
  styleUrls: ['./get-photo-page.component.css']
})
export class GetPhotoPageComponent implements OnInit {
  selected: string
  filtrType: any
  filtrs = [
    { value: 'filtr0', viewValue: 'Kategoria' },
    { value: 'filtr1', viewValue: 'Tagi' },
    { value: 'filtr2', viewValue: 'Rozmiar' }
  ];

  constructor(private cdRef:ChangeDetectorRef) { }

  ngOnInit() {
  }

  showChange(){
    console.log('change')
    this.filtrType = null 
  }
  checkSelected(value: string) {
    if (this.selected == value) {
      return true
    }
    else
      return false
  }

}
