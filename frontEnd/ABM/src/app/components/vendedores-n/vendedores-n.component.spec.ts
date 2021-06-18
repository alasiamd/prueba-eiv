import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendedoresNComponent } from './vendedores-n.component';

describe('VendedoresNComponent', () => {
  let component: VendedoresNComponent;
  let fixture: ComponentFixture<VendedoresNComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VendedoresNComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VendedoresNComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
