package com.example.ejerciciohibernateandres.controller;

import com.example.ejerciciohibernateandres.dao.ExpertoDAO;
import com.example.ejerciciohibernateandres.model.Experto;
import com.example.ejerciciohibernateandres.service.ExpertoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://gifted-babbage-2d491e.netlify.app", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ExpertoController {

    private final Logger log = LoggerFactory.getLogger (ExpertoController.class);

    private final ExpertoService expertoService;
    private final ExpertoDAO expertoDAO;
    public ExpertoController(ExpertoService expertoService, ExpertoDAO expertoDAO) {
        this.expertoService = expertoService;
        this.expertoDAO = expertoDAO;
    }

    // Crear Experto
    @PostMapping("/expertos")
    public ResponseEntity<Experto> crearExperto(@RequestBody Experto experto){
        Experto expertoNuevo = expertoService.crearExperto(experto);
        if(expertoNuevo == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return ResponseEntity.ok().body(expertoNuevo);
        }
    }

    // Encontrar todas los expertos
//    @GetMapping("/expertos")
//    public ResponseEntity<List<Experto>> encontrarTodos(){
//        List<Experto> listaExpertos = expertoService.encontrarTodos();
//
//        if(listaExpertos.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }else{
//            return ResponseEntity.ok().body(listaExpertos);
//        }
//    }

    // Encontrar todos los expertos paginados
//    @GetMapping("/expertos")
//    public Page<Experto> encontrarTodosExpertosPaginacion(@PageableDefault(size=10, page=0) Pageable pageable){
//        Page<Experto> listaExpertos = expertoService.encontrarTodos(pageable);
//        if(listaExpertos.isEmpty()){
//            return null;
//        }else{
//            return listaExpertos;
//        }
//    }

    // Devuelve todos los expertos - Filtros: límite, página, Etiqueta, Nombre, Modalidad, Estado
    @GetMapping("/expertos")
    public Page<Experto> encontrarExpertoConFiltros(@RequestParam Map<String, String> parametros){

        int page, size;
        // Valores por defecto para -> page , size
        // Si no está el parámetro `page` por defecto será 0
        page = parametros.containsKey("page")? Integer.parseInt(parametros.get("page")) :0;
        // Si no está el parámetro `size`, por defecto será 3
        size = parametros.containsKey("size")? Integer.parseInt(parametros.get("size")) :10;

        String etiqueta, nombre, modalidad, estado;
        Long idEtiqueta;
        //
//        if(parametros.containsKey("nombre")== false){
//            nombre = "";
//        }else{
//            nombre = parametros.get("nombre");
//        }
        nombre = (parametros.containsKey("nombre")== false)?"":parametros.get("nombre");

        //        if(parametros.containsKey("etiqueta")== false){
//            etiqueta = "";
//        }else{
//            etiqueta = parametros.get("etiqueta");
//        }
        etiqueta = (parametros.containsKey("etiqueta")== false)?"0":parametros.get("etiqueta");
        try{
            idEtiqueta = Long.valueOf(etiqueta);
        }catch(NumberFormatException ex){
            log.error("ERROR valor de la etiqueta, DEBE ser un entero: valor introducido= {}", etiqueta);
            return null;
        }

        //        if(parametros.containsKey("modalidad")== false){
//            modalidad = "";
//        }else{
//            modalidad = parametros.get("modalidad");
//        }/
        modalidad = (parametros.containsKey("modalidad")== false)?"":parametros.get("modalidad");

        //        if(parametros.containsKey("estado")== false){
//            estado = "";
//        }else{
//            estado = parametros.get("estado");
//        }
        estado = (parametros.containsKey("estado")== false)?"":parametros.get("estado");

        List<Experto> listaExperto = expertoDAO.encontrarConFiltros(nombre, idEtiqueta, modalidad, estado);
        if(listaExperto.isEmpty()){
            return null;
        }else{
            Pageable pageable = PageRequest.of(page, size);
            return convertirListAPage(listaExperto, pageable);
        }


    }




    // Método para convertir una Lista en un objeto Pageable
    public static <T> Page<T> convertirListAPage(List<T> list, Pageable pageable) {
        int inicio = (int) pageable.getOffset();
        int fin = (inicio + pageable.getPageSize()) > list.size() ? list.size() : (inicio + pageable.getPageSize());

        try {
            Page<T> page = new PageImpl<T>(list.subList(inicio, fin), pageable, list.size());
            return page;
        }catch(Exception ex){
            return null;
        }
    }












    // Encontrar un experto por su Id
    @GetMapping("/expertos/{id}")
    public ResponseEntity<Optional<Experto>> encontrarUno(@PathVariable Long id){
        Optional<Experto> resultadoExperto = expertoService.encontrarExperto(id);

        if(resultadoExperto.isPresent()){
            return ResponseEntity.ok().body(resultadoExperto);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Borrar un experto
    @DeleteMapping("/expertos/{id}")
    public ResponseEntity<Boolean> borrarExperto(@PathVariable Long id){

        Boolean resultado = expertoService.borrarExperto(id);

        if(resultado == true){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

//    // Actualizar un experto
//    @PutMapping("/experto")
//    public ResponseEntity<Experto> actualizarExperto(@RequestBody Experto experto){
//
//        // Cogemos el experto que se quiere actualizar
//        Optional<Experto> originalExperto = expertoService.encontrarExperto(experto.getId());
//
//        //COGEMOS sus etiquetas
//        List<Etiqueta> listaEtiquetas = originalExperto.get().getEtiquetas();
//
//        experto.setEtiquetas(listaEtiquetas);
//
//        Experto resultadoExperto = expertoService.actualizarExperto(experto);
//        if(resultadoExperto == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }else{
//            return ResponseEntity.ok().body(resultadoExperto);
//        }
//    }

    // Actualizar un experto por su Id
    @PutMapping("/expertos/{id}")
    public ResponseEntity<Experto> actualizarExpertoPorId(@RequestBody Experto experto, @PathVariable Long id){
        experto.setId(id);

        Experto resultadoExperto = expertoService.actualizarExperto(experto);
        if(resultadoExperto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return ResponseEntity.ok().body(resultadoExperto);
        }
    }



}
