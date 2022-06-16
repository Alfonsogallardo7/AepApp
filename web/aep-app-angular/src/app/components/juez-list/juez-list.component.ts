import { Component, OnInit } from '@angular/core';
import { Juez } from 'src/app/model/interfaces/juez.interface';
import { JuezService } from 'src/app/services/juez.service';

@Component({
  selector: 'app-juez-list',
  templateUrl: './juez-list.component.html',
  styleUrls: ['./juez-list.component.scss']
})
export class JuezListComponent implements OnInit {

  juez: Juez[] = [];
  constructor(
    private juezService: JuezService

  ) { }

  ngOnInit(): void {
    this.juezService.getJueces().subscribe(juezRespons => {
      this.juez = juezRespons.content;
    });
  }

}
