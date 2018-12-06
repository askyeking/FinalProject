import { TestBed } from '@angular/core/testing';

import { RentService } from './rent.service';

describe('RentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RentService = TestBed.get(RentService);
    expect(service).toBeTruthy();
  });
});
