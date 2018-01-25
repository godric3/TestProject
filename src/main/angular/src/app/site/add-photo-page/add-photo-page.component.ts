import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ImageService } from '../../services/image.service';

@Component({
  selector: 'app-add-photo-page',
  templateUrl: './add-photo-page.component.html',
  styleUrls: ['./add-photo-page.component.css']
})
export class AddPhotoPageComponent implements OnInit {
  fileToUpload: File;

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

  handleFileInput(file: FileList) {
    this.fileToUpload = file.item(0)
  }
  sendImage() {
    let fileBrowser = this.fileInput.nativeElement;
    if (fileBrowser.files && fileBrowser.files[0]) {
      const formData = new FormData();
      let json_array = JSON.stringify(this.tags)
      formData.append("image", fileBrowser.files[0])
      formData.append("category", this.category)
      formData.append("tags", json_array)
      console.log(formData.get("image"));
      this.imageService.sendImage(formData).subscribe(res => {
        console.log(res)
      },
        err => {
          console.log(err)
        });
    }
  }

}
