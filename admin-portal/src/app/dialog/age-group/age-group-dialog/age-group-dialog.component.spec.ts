import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgeGroupDialogComponent } from './age-group-dialog.component';

describe('AgeGroupDialogComponent', () => {
  let component: AgeGroupDialogComponent;
  let fixture: ComponentFixture<AgeGroupDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgeGroupDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgeGroupDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
