import {Injectable} from 'angular2/core'
import {Bootie} from './bootie'

var booties: Bootie[] = [
  {"id":1,"name":"mana booties"} ,
  {"id":2,"name":"speed booties"} ,
  {"id":3,"name":"intellisense booties"} ,
  {"id":4,"name":"brown laminate booties"}
]


@Injectable()
export class BootieService {
  getBooties() {
    // return Promise.resolve(booties)
    return new Promise<Bootie[]>(resolve =>
      setTimeout(()=>resolve(booties), 500) // .5 seconds
    );
  }
}
