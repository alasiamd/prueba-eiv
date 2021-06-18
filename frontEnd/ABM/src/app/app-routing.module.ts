import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ListadoComponent } from './listado/listado.component';
import { VendedoresComponent } from './components/vendedores/vendedores.component';
import { VendedoresNComponent } from './components/vendedores-n/vendedores-n.component';

const routes: Routes = [
  {
    path:'',
    component: ListadoComponent
  },
  {
    path:'vendedores/:idRoute',
    component: VendedoresComponent
  },
  {
    path:'vendedoresN/nuevo',
    component: VendedoresNComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
