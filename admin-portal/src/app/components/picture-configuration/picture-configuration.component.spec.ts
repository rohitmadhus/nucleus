import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PictureConfigurationComponent } from './picture-configuration.component';

describe('PictureConfigurationComponent', () => {
  let component: PictureConfigurationComponent;
  let fixture: ComponentFixture<PictureConfigurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PictureConfigurationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PictureConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
