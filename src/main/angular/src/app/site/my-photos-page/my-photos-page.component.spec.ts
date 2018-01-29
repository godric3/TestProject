import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyPhotosPageComponent } from './my-photos-page.component';

describe('MyPhotosPageComponent', () => {
  let component: MyPhotosPageComponent;
  let fixture: ComponentFixture<MyPhotosPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyPhotosPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyPhotosPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
