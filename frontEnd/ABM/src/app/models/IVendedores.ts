import { Localidad } from './ILocalidades';

export interface Vendedor{
    id: number;
    usuarioLogin: string;
    nombre: string;
    habilitado: boolean;
    fechaNacimiento: string;
    observaciones: string;
    localidad: Localidad;
}