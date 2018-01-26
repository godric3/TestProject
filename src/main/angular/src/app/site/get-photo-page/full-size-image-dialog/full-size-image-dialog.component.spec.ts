import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FullSizeImageDialogComponent } from './full-size-image-dialog.component';

describe('FullSizeImageDialogComponent', () => {
  let component: FullSizeImageDialogComponent;
  let fixture: ComponentFixture<FullSizeImageDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FullSizeImageDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FullSizeImageDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
