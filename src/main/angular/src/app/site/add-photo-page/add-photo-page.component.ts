import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ImageService } from '../../services/image.service';
import { Input } from '@angular/compiler/src/core';

@Component({
  selector: 'app-add-photo-page',
  templateUrl: './add-photo-page.component.html',
  styleUrls: ['./add-photo-page.component.css']
})
export class AddPhotoPageComponent implements OnInit {
  @ViewChild("fileInput") fileInput;
  private fullImagePath = '/TestProject/ng/assets/images-hello-site.jpg'
  category: string
  tags: string[]
  newTag: string
  constructor(private imageService: ImageService) { }

  ngOnInit() {
    this.tags = []
  }

  addTagToTagsList() {
    this.tags.push(this.newTag);
    this.newTag = '';
  }

  sendImage() {
    let fileBrowser = this.fileInput.nativeElement;
    if (fileBrowser.files && fileBrowser.files[0]) {
      const formData = new FormData();
      let json_array = JSON.stringify(this.tags)
      formData.append("image", fileBrowser.files[0])
      formData.append("category", this.category)
      formData.append("tags", json_array)
      this.imageService.sendImage(formData).subscribe(res => {
        console.log(res)
      },
        err => {
          console.log(err)
        });
      this.clear()
    }
  }

  clear() {
    console.log('clear')
    this.category = ''
    this.tags = []
    this.fileInput.value = ''
  }
}
