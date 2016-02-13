import {Component} from 'angular2/core'
import {BootieListComponent} from './bootie-list.component'


@Component({
  selector: 'doge-app',
  template: `<h2>{{title}}</h2>

    <h3>Much booties WOW!</h3>

    <doge-bootie>Loading a list of doge booties...</doge-bootie>
    `,
  directives: [BootieListComponent]

})

export class AppComponent {
  public title = 'Doge Booties'

}
