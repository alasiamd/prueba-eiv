import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Vendedor } from '../models/IVendedores';
import { Localidad } from '../models/ILocalidades';

@Injectable({
  providedIn: 'root'
})
export class VendedoresService {

  url:string = 'http://localhost:8080/api/vendedores/todos';
  urlABM:string = 'http://localhost:8080/api/vendedores/';
  urlLoc:string = 'http://localhost:8080/api/localidades/todas';

  httpOptions = {
    headers: {
      'Content-Type': 'application/json'
    }
  };
  httpOptionsFoto = {
    responseType: "blob"
  };

  vendedores: Vendedor[] = [];
  localidades: Localidad[] = [];

  constructor(private http:HttpClient) { }


  getLocalidades():Observable<Localidad[]>{
    return this.http.get<Localidad[]>(this.urlLoc,this.httpOptions);
  }

  getFoto(id:number):Observable<Blob>{
    return this.http.get(`${this.urlABM}${id}/foto`, { responseType: 'blob' });
  }
  
  posFoto(id:number):Observable<any>{
    return this.http.post(`${this.urlABM}${id}/foto`, { responseType: 'formData' });
  }

  getVendedores():Observable<Vendedor[]>{
    return this.http.get<Vendedor[]>(this.url,this.httpOptions);
  }

  putVendedores(vendedor:Vendedor):Observable<Vendedor[]>{
    return this.http.put<Vendedor[]>(this.urlABM + vendedor.id , this.httpOptions);
  }
  postVendedores(vendedor:Vendedor):Observable<Vendedor[]>{
    return this.http.post<Vendedor[]>(this.urlABM  , this.httpOptions);
  }
  delVendedores(vendedor:Vendedor):Observable<Vendedor[]>{
    return this.http.delete<Vendedor[]>(this.urlABM + vendedor.id );
  }
}
