package com.alberto.gesresfamlilyreactive.repository;

import com.alberto.gesresfamlilyreactive.domain.Centro;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//import java.util.List;

@Repository
public interface CentroRepository extends ReactiveMongoRepository<Centro, String> {

    Flux<Centro> findAll();
    Mono<Centro> findByNombre(String nombre);


    //List<Centro> findAllCentrosById(long id);

    //List<Centro> findByNombreOrNumRegistroOrEmail(String nombre, String numRegistro, String email);

   /* // Contar los residentes totales de un centro
    @Query(value = "SELECT COUNT(*) FROM residentes WHERE centro_id = ?1", nativeQuery = true)
    int numResidentes(long centroId);*/
}


