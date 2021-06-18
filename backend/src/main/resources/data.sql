

INSERT INTO localidades ( id, localidad, codigopostal ) VALUES 
	( 1, 'Rosario', '2000' ),
 	( 2, 'Santa Fe', '3000' ),
 	( 3, 'Funes', '2132' ),
 	( 4, 'Roldan', '2134' ),
 	( 5, 'Perez', '2121' );
 	
 	
 	
INSERT INTO vendedores ( id, usuario_login, nombre, habilitado, fecha_nac, observaciones, localidad_id  ) VALUES 
	( 1, 'anibal123', 'Perez, Anibal ', 1, '1982-05-01', null, 2 ),
	( 2, 'puesto23ventas', 'Puentes Victoria', 1, '1983-06-15', 'supervisora', 4 ),
	( 3, 'gg2001', 'Gomez Gaston ', 0, '1972-02-03', null, 1 );
 	
 	
 	