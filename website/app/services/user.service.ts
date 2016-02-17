import {Injectable} from 'angular2/core'
import {MOCKUSERS} from '../mock-data/mock-users'
import {iUser} from '../interfaces/user'

@Injectable()
export class UserService {
    getUsers() {
      return Promise.resolve(MOCKUSERS)
    }

    createUser(user: iUser): boolean {
      if(user){
        return true
      }
      return false
    }
}
