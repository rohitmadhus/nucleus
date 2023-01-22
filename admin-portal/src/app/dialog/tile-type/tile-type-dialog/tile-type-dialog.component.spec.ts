import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TileTypeDialogComponent } from './tile-type-dialog.component';

describe('TileTypeDialogComponent', () => {
  let component: TileTypeDialogComponent;
  let fixture: ComponentFixture<TileTypeDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TileTypeDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TileTypeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
