import {Component, OnInit} from 'angular2/core'
import {Bootie} from './bootie'
import {BootieService} from './bootie.service';

@Component({
  selector: 'doge-bootie',
  providers: [BootieService],
  template: `This be a doge bootie list
  <ul class="booties">
    <li *ngFor="#bootie of booties" (click)="onSelect(bootie)">
      <!-- each hero goes here -->
      <span class="badge">{{bootie.id}}</span> {{bootie.name}}
    </li>
  </ul>
  `
})

export class BootieListComponent implements OnInit {

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
