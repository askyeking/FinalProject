import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VendorInventoryListComponent } from './vendor-inventory-list.component';

describe('VendorInventoryListComponent', () => {
  let component: VendorInventoryListComponent;
  let fixture: ComponentFixture<VendorInventoryListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VendorInventoryListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VendorInventoryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
