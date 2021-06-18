import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Router } from '@angular/router';
import * as moment from 'moment';



import { Vendedor } from '../../models/IVendedores';
import { Localidad } from '../../models/ILocalidades';
import { VendedoresService } from '../../services/vendedores.service';

@Component({
  selector: 'app-vendedores-n',
  templateUrl: './vendedores-n.component.html',
  styleUrls: ['./vendedores-n.component.scss']
})
export class VendedoresNComponent implements OnInit {
  public localidades: Localidad[]=[];
  public vendedores:Vendedor[]=[];
  public dia:string='';
  public edad:number=0;
  

  usuarioLogin: string='';
  nombre: string='';
  habilitado: boolean=false;
  fechaNacimiento: string='';
  observaciones: string='';
  localidad: number=0;

  vendedor:any = {
    usuarioLogin: ' ',
    nombre:' ',
    habilitado: false,
    fechaNacimiento:' ',
    observaciones:' ',
    localidad: 0
  }

  

  //id: number = 0;
  
  //localidad: string = '';

  constructor(public vendedoresService: VendedoresService,
              private rutaActiva: ActivatedRoute,
              private router:Router ) { }

  ngOnInit(): void {    
    this.vendedoresService.getLocalidades().subscribe(data =>{
      this.localidades = data;
    });

    

  }

  salir(){
    this.router.navigate(['/']);
  }

  guardar(){
    this.vendedor.usuarioLogin = this.usuarioLogin;
    this.vendedor.nombre = this.nombre;
    this.vendedor.habilitado = this.habilitado;
    this.vendedor.fechaNacimiento = this.fechaNacimiento;
    this.vendedor.localidad = this.localidad;

    this.vendedoresService.postVendedores(this.vendedor).subscribe();
  }

  getAge(fechaNacimiento:string){
    this.edad = parseInt(moment(fechaNacimiento).fromNow().split(" ")[0]);   

  }

  hCambio(){
    console.log('Habilitado',this.habilitado)
  }

  lCambio(){
    
    console.log('Localidad',this.localidad)
  }

}