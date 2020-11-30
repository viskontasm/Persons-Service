import { Component } from '@angular/core';
import { HttpService } from './http.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Persons App';
  persons = [] as any;

  constructor(private  service: HttpService) {
  }

  searchCustomers(value: string): void {
    if (this.service !== null && value) {
      this.service.callPersonsService(value).subscribe(data => {
        this.persons = data;
        console.log(this.persons);
      });
    }
  }
}
