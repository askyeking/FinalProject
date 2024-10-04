import { TestBed } from '@angular/core/testing';

import { LookaheadService } from './lookahead.service';

describe('LookaheadService', () => {
  let service: LookaheadService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LookaheadService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
