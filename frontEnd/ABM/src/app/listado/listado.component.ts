import { Component, OnInit } from '@angular/core';

import { Vendedor } from '../models/IVendedores';
import { VendedoresService } from '../services/vendedores.service';

@Component({
  selector: 'app-listado',
  templateUrl: './listado.component.html',
  styleUrls: ['./listado.component.scss']
})
export class ListadoComponent implements OnInit {

  public vendedores:Vendedor[]=[];

  constructor(public vendedoresService: VendedoresService) { }

  ngOnInit(): void {
    this.vendedoresService.getVendedores().subscribe(data =>{
      this.vendedores = data;
    })
  }
  borrar(vendedor:Vendedor){
    this.vendedoresService.delVendedores(vendedor).subscribe();
  }

}
