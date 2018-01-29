import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ImageService } from '../../services/image.service';
import { Input } from '@angular/compiler/src/core';
import { AddingPhotoResultDialogComponent } from './adding-photo-result-dialog/adding-photo-result-dialog.component';
import { MatDialog } from '@angular/material/dialog';

export interface catygoriesFromServer {
  name:string
  numberOfImages:number
}
@Component({
  selector: 'app-add-photo-page',
  templateUrl: './add-photo-page.component.html',
  styleUrls: ['./add-photo-page.component.css']
})
export class AddPhotoPageComponent implements OnInit {
  @ViewChild("fileInput") fileInput:ElementRef;
  private fullImagePath = '/TestProject/ng/assets/images-hello-site.jpg'
  category: string
  categoryFromSelect:string
  tagsFromSelect
  tags: string[]
  newTag: string
  catygoriesFromServer;
  tagsFromServer;
  constructor(private imageService: ImageService, public dialog: MatDialog) { }

  ngOnInit() {
    this.tags = []
    //pobranie z serwera list kategorii
    this.getAllCatygories();
    this.getAllTags();
  }

  private getAllCatygories() {
    this.imageService.getAllCatygoriesFromServer().subscribe(response => {
      this.catygoriesFromServer = response;
    });
  }

  private getAllTags() {
    this.imageService.getAllTagsFromServer().subscribe(response => {
      this.tagsFromServer = response;
    });
  }

  addTagToTagsList() {
    if (this.newTag) {
      this.tags.push(this.newTag);
      this.newTag = '';
    }
  }

  selectedCategoryFromList(){
    console.log('change')
    console.log(this.categoryFromSelect)
    this.category = this.categoryFromSelect
  }

  selectedCTagsFromList(){
    console.log('change tag')
    this.newTag =  this.tagsFromSelect
  }
  sendImage() {
    let fileBrowser = this.fileInput.nativeElement;
    if (fileBrowser.files && fileBrowser.files[0] && this.category &&  this.tags.length>0) {
      const formData = new FormData();
      let json_array = JSON.stringify(this.tags)
      formData.append("image", fileBrowser.files[0])
      formData.append("title", fileBrowser.files[0].name)
      formData.append("category", this.category)
      formData.append("tags", json_array)
      this.imageService.sendImage(formData).subscribe(res => {
        console.log(res)
        this.getAllCatygories();
        this.openDialog(true)
      },
        err => {
          console.log(err)
          this.openDialog(false)
        });
      this.clear()
    }
  }

  clear() {
    console.log('clear')
    this.category = ''
    this.tags = []
    this.newTag = ''
    this.fileInput.nativeElement.value = ""

  }

  openDialog(isSuccess:boolean): void {
    let dialogRef = this.dialog.open(AddingPhotoResultDialogComponent, {
      data: { success: isSuccess }
    });
  }
}
