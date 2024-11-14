import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventListarComponent } from './event-listar.component';

describe('EventListarComponent', () => {
  let component: EventListarComponent;
  let fixture: ComponentFixture<EventListarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EventListarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
