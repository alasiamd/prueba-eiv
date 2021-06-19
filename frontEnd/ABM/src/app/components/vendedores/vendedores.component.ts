import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Router } from '@angular/router';


import { Vendedor } from '../../models/IVendedores';
import { Localidad } from '../../models/ILocalidades';
import { VendedoresService } from '../../services/vendedores.service';
import * as moment from 'moment';

@Component({
  selector: 'app-vendedores',
  templateUrl: './vendedores.component.html',
  styleUrls: ['./vendedores.component.scss']
})
export class VendedoresComponent implements OnInit {

  public localidades: Localidad[]=[];
  public vendedores:Vendedor[]=[];
  //public vendedor:any;
  idRoute:number=0;
  ahora = moment();
  public edad:number=0;

  usuarioLogin: string='';
  nombre: string='';
  habilitado: boolean=false;
  fechaNacimiento: string='';
  observaciones: string='';
  localidad: string='';

  vendedor:any = {
    usuarioLogin: ' ',
    nombre:' ',
    habilitado: false,
    fechaNacimiento:' ',
    observaciones:' ',
    localidad: ''
  }

  constructor(public vendedoresService: VendedoresService,
              private rutaActiva: ActivatedRoute,
              private router:Router ) { }

  ngOnInit(): void {
    this.vendedoresService.getLocalidades().subscribe(data =>{
      this.localidades = data;
    });

    this.idRoute = this.rutaActiva.snapshot.params.idRoute;    
    
    if (this.idRoute != 0) {
      this.vendedoresService.getVendedores().subscribe(data =>{
        this.vendedores = data;
              
        this.vendedor = this.vendedores.find(x => x.id == this.idRoute); 
        this.edad = this.ahora.diff(moment(this.vendedor.fechaNacimiento),'years');
      });
    }

    
    
  }

  salir(){
    this.router.navigate(['/']);
    
  }

  getAge(fechaNacimiento:string){
    this.edad = this.ahora.diff(moment(fechaNacimiento),'years');
    
  }

  guardar(vendedor:Vendedor){
    if (this.edad < 18 || this.edad > 65) {
      alert('No se admiten edades menores a 18 ni mayores a 65 años!!! '+ this.edad);
    }
    else {
      if (this.idRoute == 0) {

        this.vendedor.usuarioLogin = this.usuarioLogin;
        this.vendedor.nombre = this.nombre;
        this.vendedor.habilitado = this.habilitado;
        this.vendedor.fechaNacimiento = this.fechaNacimiento;
        this.vendedor.localidad = this.localidad;

        this.vendedoresService.postVendedores(this.vendedor).subscribe();
      }
      else {
        
        this.vendedoresService.putVendedores(this.vendedor).subscribe();
      }
    
    }
  }

  hCambio(){
    //console.log('Habilitado',this.vendedor.habilitado)
  }

  lCambio(){
    
    //console.log('Localidad',this.vendedor.localidad.localidad)
  }

}
