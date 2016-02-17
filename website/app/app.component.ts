import {Component} from 'angular2/core'
import {RegistrationComponent} from './registration/registration.component'
import {UserService} from './services/user.service'
import {ProductListComponent} from './product-list/product-list.component'
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from 'angular2/router'


@Component({
  selector: 'doge-app',
  templateUrl: 'app/app.component.html',
  directives: [
    ProductListComponent,
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
  },
  {
    path: '/product-list',
    name: 'ProductList',
    component: ProductListComponent
  }
])


export class AppComponent {
  public title = 'Doge Booties'

}
