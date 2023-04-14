package com.alberto.gesresfamlilyreactive.service;

import com.alberto.gesresfamlilyreactive.domain.Centro;
//import com.alberto.gesresfamily.exception.CentroNotFoundException;
import com.alberto.gesresfamlilyreactive.exception.CentroNotFoundException;
import com.alberto.gesresfamlilyreactive.repository.CentroRepository;
//import org.modelmapper.ModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class CentroServiceImpl implements CentroService {

    @Autowired
    private CentroRepository centroRepository;

    @Override
    public Flux<Centro> findAll() {
        return centroRepository.findAll();
    }

    @Override
    public Mono<Centro> findByNombre(String nombre) {
        return centroRepository.findByNombre(nombre);
    }

    @Override
    public Mono<Centro> findById(String id) throws CentroNotFoundException{
        return centroRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new CentroNotFoundException())));
    }

    @Override
    public Mono<Centro> addCentro(Centro centro) {
        return centroRepository.save(centro);
    }

    @Override
    public Mono<Centro> removeCentro(String id) throws CentroNotFoundException{
        Mono<Centro> centro = centroRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new CentroNotFoundException())));
        //orElseThrow(CentroNotFoundException::new);
        centroRepository.deleteById(id).block();
        return centro;
    }

    @Override
    public Mono<Centro> modifyCentro(String id, Centro newCentro) throws CentroNotFoundException {
        Mono<Centro> existingCentro = centroRepository.findById(id)
                .switchIfEmpty(Mono.defer(()-> Mono.error(new CentroNotFoundException())));
                //orElseThrow(CentroNotFoundException::new);
        /*
         *ToDo Con ModelMapper evito escribir todos los getters y setters pero debo incluir el id tambien en Json
         * para que realice la modificación sobre el existente sin crear uno nuevo.
         * revisar como evitar esto.
         */
        Centro centro = existingCentro.block();
        /*centro.setNombre(newCentro.getNombre());
        centro.setDireccion(newCentro.getDireccion());
        centro.setNumRegistro(newCentro.getNumRegistro());
        centro.setEmail(newCentro.getEmail());
        centro.setTelefono(newCentro.getTelefono());*/
        ModelMapper mapper = new ModelMapper();
        centro = mapper.map(newCentro, Centro.class);
        return centroRepository.save(centro);
    }

/*    @Override
    public Mono<Centro> removeCentro(String id) {
        Mono<Centro> centro = centroRepository.findById(id);
                //orElseThrow(CentroNotFoundException::new);
        centroRepository.delete(centro);
        return centro;
    }*/

    /*@Override
    public Centro addCentro(Centro centro) {
        return centroRepository.save(centro);
    }

    @Override
    public Centro findById(long id) throws CentroNotFoundException{
        return centroRepository.findById(id).
                orElseThrow(CentroNotFoundException::new);
    }

    @Override
    public List<Centro> findAll(){
        return centroRepository.findAll();
    }


    @Override
    public Centro removeCentro(long id) throws CentroNotFoundException{
        Centro centro = centroRepository.findById(id).
                orElseThrow(CentroNotFoundException::new);
        centroRepository.delete(centro);
        return centro;
    }


    @Override
    public Centro modifyCentro(long id, Centro newCentro) throws CentroNotFoundException {
        Centro existingCentro = centroRepository.findById(id).
                orElseThrow(CentroNotFoundException::new);
        *//*
         *ToDo Con ModelMapper evito escribir todos los getters y setters pero debo incluir el id tambien en Json
         * para que realice la modificación sobre el existente sin crear uno nuevo.
         * revisar como evitar esto.
         *//*
        ModelMapper mapper = new ModelMapper();
        existingCentro = mapper.map(newCentro, Centro.class);
        return centroRepository.save(existingCentro);
    }

    @Override
    public List<Centro> findAllCentros(String nombre, String numRegistro, String email) {
        return centroRepository.findByNombreOrNumRegistroOrEmail(nombre, numRegistro, email);
    }

    @Override
    public Centro patchCentro(long id, String telefono) throws CentroNotFoundException {
        Centro centro = centroRepository.findById(id).
                orElseThrow(CentroNotFoundException::new);
        centro.setTelefono(telefono);
        return centroRepository.save(centro);
    }

    @Override
    public int numResidentes(long id) throws CentroNotFoundException {
        Centro centro = centroRepository.findById(id)
                .orElseThrow(CentroNotFoundException::new);
        return centroRepository.numResidentes(id);
    }*/
}
