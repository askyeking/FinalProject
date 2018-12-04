import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryItemListComponent } from './inventory-item-list.component';

describe('InventoryItemListComponent', () => {
  let component: InventoryItemListComponent;
  let fixture: ComponentFixture<InventoryItemListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InventoryItemListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InventoryItemListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
