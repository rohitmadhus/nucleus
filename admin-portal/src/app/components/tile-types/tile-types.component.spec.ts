import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TileTypesComponent } from './tile-types.component';

describe('TileTypesComponent', () => {
  let component: TileTypesComponent;
  let fixture: ComponentFixture<TileTypesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TileTypesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TileTypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
