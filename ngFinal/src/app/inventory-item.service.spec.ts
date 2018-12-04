import { TestBed } from '@angular/core/testing';

import { InventoryItemService } from './inventory-item.service';

describe('InventoryItemService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InventoryItemService = TestBed.get(InventoryItemService);
    expect(service).toBeTruthy();
  });
});
