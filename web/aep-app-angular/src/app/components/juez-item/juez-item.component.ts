import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Juez } from 'src/app/model/interfaces/juez.interface';
import { JuezService } from 'src/app/services/juez.service';

@Component({
  selector: 'app-juez-item',
  templateUrl: './juez-item.component.html',
  styleUrls: ['./juez-item.component.scss']
})
export class JuezItemComponent implements OnInit {
  @Input() juezInput!: Juez;

  constructor(
    private router: Router,
    private juezService: JuezService
  
  ) { }

  ngOnInit(): void {
  }

}
