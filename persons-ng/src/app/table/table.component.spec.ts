import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableComponent } from './table.component';

describe('TableComponent', () => {
  let component: TableComponent;
  let fixture: ComponentFixture<TableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should table contains text', () => {
    fixture = TestBed.createComponent(TableComponent);
    component = fixture.componentInstance;
    component.persons = [{id: 1, firstName: 'John', lastName: 'Smith', personalId: '123', gender: 'male', birthDay: '1990-02-01'}];
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('table').textContent)
      .toContain('1990-02-01');
  });

  it('should message "No persons to show" appear', () => {
    fixture = TestBed.createComponent(TableComponent);
    component = fixture.componentInstance;
    component.persons = [];
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('div').textContent)
      .toContain('No persons to show');
  });
});
