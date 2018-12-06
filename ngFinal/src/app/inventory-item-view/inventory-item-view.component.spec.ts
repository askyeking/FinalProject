import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryItemViewComponent } from './inventory-item-view.component';

describe('InventoryItemViewComponent', () => {
  let component: InventoryItemViewComponent;
  let fixture: ComponentFixture<InventoryItemViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InventoryItemViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InventoryItemViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
