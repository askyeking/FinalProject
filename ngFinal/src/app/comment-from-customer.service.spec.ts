import { TestBed } from '@angular/core/testing';

import { CommentFromCustomerService } from './comment-from-customer.service';

describe('CommentFromCustomerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CommentFromCustomerService = TestBed.get(CommentFromCustomerService);
    expect(service).toBeTruthy();
  });
});
