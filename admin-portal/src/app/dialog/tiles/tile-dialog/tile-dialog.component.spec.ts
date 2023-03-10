import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TileDialogComponent } from './tile-dialog.component';

describe('TileDialogComponent', () => {
  let component: TileDialogComponent;
  let fixture: ComponentFixture<TileDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TileDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TileDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
