import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AngNavigationComponent } from './ang-navigation.component';

describe('AngNavigationComponent', () => {
  let component: AngNavigationComponent;
  let fixture: ComponentFixture<AngNavigationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AngNavigationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AngNavigationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
