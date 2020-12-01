import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { HttpService } from './http.service';
import { of } from 'rxjs';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { by, element } from 'protractor';

describe('AppComponent', () => {
  let component: AppComponent;
  let service: HttpService;

  beforeEach(async () => {
    // @ts-ignore
    service = new HttpService(null);
    component = new AppComponent(service);
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ]
    });

    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [
        AppComponent
      ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'Persons App'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    console.log(`title ${app.title}`);
    expect(app.title).toEqual('Persons App');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('Welcome to Persons App');
  });

  it('should Search button exist', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('button').textContent).toContain('Search');
  });

  it('should Search field exist', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('input').getAttribute('placeholder'))
      .toContain('Enter personal id or birthday(YYYY-MM-DD)');
  });

  it('should get person', () => {
    const persons: any[] = [{id: 1, firstName: 'John', lastName: 'Smith', personalId: '123', gender: 'male', birthDay: '1990-02-01'}];
    spyOn(service, 'callPersonsService').and.callFake(() => {
      return of(persons);
    });
    component.searchCustomers('123');
    expect(component.persons).toEqual(persons);
  });
});
