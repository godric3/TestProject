import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetPhotoPageComponent } from './get-photo-page.component';

describe('GetPhotoPageComponent', () => {
  let component: GetPhotoPageComponent;
  let fixture: ComponentFixture<GetPhotoPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetPhotoPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetPhotoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
