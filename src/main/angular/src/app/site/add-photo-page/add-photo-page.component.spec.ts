import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPhotoPageComponent } from './add-photo-page.component';

describe('AddPhotoPageComponent', () => {
  let component: AddPhotoPageComponent;
  let fixture: ComponentFixture<AddPhotoPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPhotoPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPhotoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
