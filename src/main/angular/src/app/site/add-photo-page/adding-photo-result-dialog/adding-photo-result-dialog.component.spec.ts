import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddingPhotoResultDialogComponent } from './adding-photo-result-dialog.component';

describe('AddingPhotoResultDialogComponent', () => {
  let component: AddingPhotoResultDialogComponent;
  let fixture: ComponentFixture<AddingPhotoResultDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddingPhotoResultDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddingPhotoResultDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
