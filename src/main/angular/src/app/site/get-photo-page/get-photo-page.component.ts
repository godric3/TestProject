import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-get-photo-page',
  templateUrl: './get-photo-page.component.html',
  styleUrls: ['./get-photo-page.component.css']
})
export class GetPhotoPageComponent implements OnInit {

  filtrs = [
    {value: 'filtr-0', viewValue: 'Kategoria'},
    {value: 'filtr-1', viewValue: 'Tagi'},
    {value: 'filtr-2', viewValue: 'Rozmiar'}
  ];

  constructor() { }

  ngOnInit() {
  }

}
