import {Component} from 'angular2/core'
import {RegistrationComponent} from './registration/registration.component'
import {UserService} from './services/user.service'
import {BootieListComponent} from './bootie-list.component'
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from 'angular2/router'


@Component({
  selector: 'doge-app',
  templateUrl: 'app/app.component.html',
  directives: [
    BootieListComponent,
    ROUTER_DIRECTIVES
  ],
  providers: [
    ROUTER_PROVIDERS,
    UserService
  ]
})

@RouteConfig([
  {
    path: '/registration',
    name: 'Registration',
    component: RegistrationComponent
  }
])


export class AppComponent {
  public title = 'Doge Booties'

}
