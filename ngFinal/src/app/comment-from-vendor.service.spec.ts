import { TestBed } from '@angular/core/testing';

import { CommentFromVendorService } from './comment-from-vendor.service';

describe('CommentFromVendorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CommentFromVendorService = TestBed.get(CommentFromVendorService);
    expect(service).toBeTruthy();
  });
});
