import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemsRentalHistoryComponent } from './items-rental-history.component';

describe('ItemsRentalHistoryComponent', () => {
  let component: ItemsRentalHistoryComponent;
  let fixture: ComponentFixture<ItemsRentalHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemsRentalHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemsRentalHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
