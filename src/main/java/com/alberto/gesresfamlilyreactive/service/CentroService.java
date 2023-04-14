package com.alberto.gesresfamlilyreactive.service;


import com.alberto.gesresfamlilyreactive.domain.Centro;
import com.alberto.gesresfamlilyreactive.exception.CentroNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
//import com.alberto.gesresfamily.exception.CentroNotFoundException;

//import java.util.List;

public interface CentroService {


    Flux<Centro> findAll();
    Mono<Centro> findByNombre(String nombre);

    Mono<Centro> findById(String id) throws CentroNotFoundException;

    Mono<Centro> addCentro(Centro centro);

    Mono<Centro> modifyCentro(String id, Centro centro) throws CentroNotFoundException;

    Mono<Centro> removeCentro(String id) throws CentroNotFoundException;

    /*Centro addCentro(Centro centro);

    List<Centro> findAll();

    List<Centro> findAllCentros(String nombre, String numRegistro, String email);

    Centro removeCentro(long id) throws CentroNotFoundException;

    Centro modifyCentro(long id, Centro centro) throws CentroNotFoundException;

    Centro findById(long id) throws CentroNotFoundException;

    Centro patchCentro(long id, String telefono) throws CentroNotFoundException;

    int numResidentes(long id) throws CentroNotFoundException;*/
}
