package com.alberto.gesresfamlilyreactive.controller;

import com.alberto.gesresfamlilyreactive.domain.Centro;
//import com.alberto.gesresfamlilyreactive.exception.*;
import com.alberto.gesresfamlilyreactive.exception.CentroNotFoundException;
import com.alberto.gesresfamlilyreactive.exception.ErrorResponse;
import com.alberto.gesresfamlilyreactive.service.CentroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//import javax.validation.Valid;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CentroController {

    private final Logger logger = LoggerFactory.getLogger(CentroController.class);

    @Autowired
    private CentroService centroService;


    @PostMapping("/centros")
    public ResponseEntity<?> addCentro ( @RequestBody Centro centro) {
        logger.info("Inicio addCentro");
        Mono<Centro> newCentro = centroService.addCentro(centro);
        logger.info("Fin addCentro");
        //return ResponseEntity.status(HttpStatus.CREATED).body(newCentro);
        return ResponseEntity.ok(newCentro.block());
    }

    @GetMapping("/centro/{id}")
    public Mono<Centro> getCentro (@PathVariable String id) throws CentroNotFoundException {
        logger.info("Inicio getCentro");
        Mono<Centro> centro = centroService.findById(id);
        logger.info("Fin getCentro");
        return centro.delayElement(Duration.ofSeconds(1));
    }

    @GetMapping(value = "/centros", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Centro> getCentros(
            @RequestParam(name = "nombre", required = false, defaultValue = "") String nombre,
            @RequestParam(name = "numRegistro", required = false, defaultValue = "") String numRegistro,
            @RequestParam(name = "email", required = false, defaultValue = "") String email){
        logger.info("Inicio getCentros");
        Flux<Centro> centros = null;

       if(nombre.equals("") && numRegistro.equals("") && email.equals("")){
            logger.info("Muestra todos los centros");
            centros = centroService.findAll().delayElements(Duration.ofSeconds(3));
        } else {
            logger.info("Muestra centros que cumplen los parametros");
            //centros = centroService.findAllCentros(nombre, numRegistro, email);
        }
        logger.info("Fin getCentros");
        return centros;
    }


    @DeleteMapping("/centro/{id}")
    public ResponseEntity<Void> removeCentro(@PathVariable String id) throws CentroNotFoundException {
        logger.info("Inicio removeCentro");
        Mono<Centro> centro = centroService.removeCentro(id);
        logger.info("Fin removeCentro");
        return ResponseEntity.noContent().build();
    }



    @PutMapping("/centro/{id}")
    public Mono<Centro> modifyCentro(@RequestBody Centro centro, @PathVariable String id)
            throws CentroNotFoundException{
        logger.info("Inicio modifyCentro");
        Mono<Centro> newCentro = centroService.modifyCentro(id, centro);
        logger.info("Fin modifyCentro");
        return ResponseEntity.status(HttpStatus.OK).body(newCentro).getBody();
    }

    // Cambiar el telefono de un centro
    /*@PatchMapping("/centro/{id}")
    public Centro patchCentro (@PathVariable long id, @RequestBody String telefono) throws CentroNotFoundException {
        logger.info("Start Patchcentro " + id);
        Centro centro = centroService.patchCentro(id, telefono);
        logger.info("End patchCentro " + id);
        return centro;
    }*/

    // Contar los residentes totales de un centro. SQL
    /*@GetMapping("/centro/{id}/numResidentes")
    public int numResidentes(@PathVariable long id) throws CentroNotFoundException {
        logger.info("Inicio numResidentes " + id);
        int residentes = centroService.numResidentes(id);
        logger.info("rin numResidentes " + id);
        return residentes;
    }*/



    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException manve) {
        logger.info("400: Argument not valid");
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        logger.error(manve.getMessage(), manve);
        logger.error(Arrays.toString(manve.getStackTrace()));
        return ResponseEntity.badRequest().body(ErrorResponse.validationError(errors));
    }*/

    /*@ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException bre) {
        logger.error(bre.getMessage(), bre);
        logger.error(Arrays.toString(bre.getStackTrace()));
        return ResponseEntity.badRequest().body(ErrorResponse.badRequest(bre.getMessage()));
    }*/

    @ExceptionHandler(CentroNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCentroNotFoundException(CentroNotFoundException cnfe) {
        logger.error(cnfe.getMessage(), cnfe);
        logger.error(Arrays.toString(cnfe.getStackTrace()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(cnfe.getMessage()));
    }

    /*@ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException isee) {
        logger.error(isee.getMessage(), isee);
        logger.error(Arrays.toString(isee.getStackTrace()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.internalServerError(isee.getMessage()));
    }*/

}
