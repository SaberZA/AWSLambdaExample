import {Component, OnInit} from 'angular2/core'
import {Bootie} from '../bootie'
import {BootieService} from '../services/bootie.service';

@Component({
  selector: 'doge-bootie',
  providers: [BootieService],
  templateUrl: 'app/product-list/product-list.component.html'
})

export class ProductListComponent implements OnInit {

  constructor(private _bootieService: BootieService) { }

  public booties : Bootie[]
  public selectedBootie: Bootie
  public onSelect(bootie: Bootie) {
      this.selectedBootie = bootie
  }

  getBooties() {
    this._bootieService.getBooties()
        .then(booties => this.booties = booties)
  }

  ngOnInit(){
    this.getBooties()
  }
}
