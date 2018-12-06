import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemRentalViewComponent } from './item-rental-view.component';

describe('ItemRentalViewComponent', () => {
  let component: ItemRentalViewComponent;
  let fixture: ComponentFixture<ItemRentalViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemRentalViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemRentalViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
