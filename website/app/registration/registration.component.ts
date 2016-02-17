import {Component} from 'angular2/core'
import {iUser} from '../interfaces/user'
import {UserService} from '../services/user.service'


@Component({
  selector: 'registration',
  templateUrl: 'app/registration/registration.component.html'
  // templateUrl:'C:/Users/dwebb/_Dev/angularexample/AWSLambdaExample/website/app/registration/registration.component.html'
})

export class RegistrationComponent{
  constructor(private _userService: UserService){
  }
  //Can't initialize a interface with new()
  public customer: iUser = {
    "firstNames":"",
    "lastName": "",
    "email": "",
    "password":""
  }

  createCustomer(customer: iUser) {
    this._userService.createUser(customer)
  }
  //Promise example: not to be used
  // getCustomer(): void{
  //   this._userService.getUsers().then(users => this.customer = users)
  // }

}
