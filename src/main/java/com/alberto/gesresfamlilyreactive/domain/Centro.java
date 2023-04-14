package com.alberto.gesresfamlilyreactive.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "centros")
public class Centro {

    @Id
    private String id;
    @Field
    //@NotNull(message="El nombre del centro es obligatorio")
    //@NotBlank(message = "El nombre del centro no puede estar vacío")
    private String nombre;
    @Field
    private String direccion;
    @Field(value = "num_registro")
    //@NotBlank(message="El número de registro no puede estar vacío")
    //@Size(min = 3, max = 8, message="El num registro tiene que tener entre 3 y 8 caracteres")
    //@Pattern(regexp = "[A-Z0-9]+", message="El num registro solo puede tener letras mayúsculas o números")
    private String numRegistro;
    @Field
    //@NotBlank(message="El mail no puede estar vacío")
    //@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            //+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message= "Escribe un correo válido, ejemplo:" +
            //"user.name@domain.com, username@domain.com")
    private String email;
    @Field
    private String telefono;

    //@OneToMany(mappedBy = "centro")
    //@JsonBackReference(value = "centroResidente")
    //private List<Residente> residentes;

}
