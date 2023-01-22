import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContentTypeDialogComponent } from './content-type-dialog.component';

describe('ContentTypeDialogComponent', () => {
  let component: ContentTypeDialogComponent;
  let fixture: ComponentFixture<ContentTypeDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContentTypeDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContentTypeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
