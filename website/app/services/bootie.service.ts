import {Injectable} from 'angular2/core'
import {Bootie} from '../bootie'

var booties: Bootie[] = [
  {"id":1,"name":"Mana booties","description":"These booties will give your doge much mana"} ,
  {"id":2,"name":"Speed booties","description":"These booties will give your doge much speed , very fast wow"} ,
  {"id":3,"name":"Intellisense booties","description":"These booties will give your doge much clever, very alt+enter WOW"} ,
  {"id":4,"name":"Brown laminate booties","description":"These booties will give your doge brown laminate, much style"},
  {"id":5,"name":"Deduction booties","description":"Much science , very Sherlock , wow"}
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
