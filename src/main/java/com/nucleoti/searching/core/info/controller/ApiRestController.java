package com.nucleoti.searching.core.info.controller;

import com.nucleoti.searching.core.info.Service.ApiService;
import com.nucleoti.searching.core.info.firebase.FirebaseService;
import com.nucleoti.searching.core.info.firebase.core.FirebaseModel;
import com.nucleoti.searching.core.info.model.ResponGenerally;
import com.nucleoti.searching.core.info.model.constantes.Auto;
import com.nucleoti.searching.core.info.model.constantes.Constantes;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@Api(value = "AlpesRest", produces = "application/json")
public class ApiRestController {
    private static final Logger logger = LoggerFactory.getLogger(ApiRestController.class);

    @Autowired
    ApiService apiService;


    @Autowired
    FirebaseService firebaseService;

    @RequestMapping(value = Constantes.CONSULTA_INFO_PERSONA_SUNAT, method = RequestMethod.GET)
    public @ResponseBody
    ResponGenerally consultaInfoSunat(@PathVariable String num) {
        Auto auto =new Auto();
        auto.setColor("WITH");
        auto.setDesc("MISHUBICHE");
        firebaseService.save("nodo2", FirebaseModel.create("marca",auto));
        //System.out.println(" MOSTRAR RESULTADO: " + );
        ResponGenerally info = apiService.retrieveInfoPersonaSunat(num);
        return info;
    }


    @RequestMapping(value = Constantes.CREATE_CLIENTE, method = RequestMethod.POST)
    public @ResponseBody
    String testMethod() {
        return "HOLA METODO TEST";
    }

    //@Secured({"BASIC"})
    @RequestMapping(value = Constantes.CONSULT_METHOD_SECURITY, method = RequestMethod.POST)
    public @ResponseBody
    String testMethodWithSecurity() {

        return "RESULTADO DEL METODO CON SEGURIDAD";
    }
}

