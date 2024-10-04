import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddConcertComponent } from './add-concert.component';

describe('AddConcertComponent', () => {
  let component: AddConcertComponent;
  let fixture: ComponentFixture<AddConcertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddConcertComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddConcertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
