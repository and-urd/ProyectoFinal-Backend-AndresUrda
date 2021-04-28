package com.example.ejerciciohibernateandres.controller;

import com.example.ejerciciohibernateandres.dao.EtiquetaDAO;
import com.example.ejerciciohibernateandres.model.Etiqueta;
import com.example.ejerciciohibernateandres.service.EtiquetaService;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://gifted-babbage-2d491e.netlify.app", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class EtiquetaController {

    private final EtiquetaService etiquetaService;
    private final EtiquetaDAO etiquetaDAO;
    public EtiquetaController(EtiquetaService etiquetaService, EtiquetaDAO etiquetaDAO) {
        this.etiquetaService = etiquetaService;
        this.etiquetaDAO = etiquetaDAO;
    }

    // Devuelve todas las etiquetas - Filtros: límite , página , nombre
    @GetMapping("/etiquetas")
    public Page<Etiqueta> encontrarEtiquetasPorNombrePaginacion(@RequestParam Map<String, String> parametros){

        String nombre;
        // Si no está el parámetro `nombre` entonces se listan todas las etiquetas.
        if(parametros.containsKey("nombre")== false){
            nombre = "";
        }else{
            nombre = parametros.get("nombre");
        }

        // Si no está el parámetro `page` por defecto será 0
        int page = parametros.containsKey("page")? Integer.parseInt(parametros.get("page")) :0;
        // Si no está el parámetro `size`, por defecto será 3
        int size = parametros.containsKey("size")? Integer.parseInt(parametros.get("size")) :10;

        List<Etiqueta> listaEtiqueta = etiquetaDAO.encontrarPorNombre(nombre);
        if(listaEtiqueta.isEmpty()){
            return null;
        }else{
            //return listaEtiqueta;
            Pageable pageable =  PageRequest.of(page,size);
            return convertirListAPage(listaEtiqueta,pageable);
        }
    }

    // Devuelve la etiqueta con ID = id
    @GetMapping("/etiquetas/{id}")
    public ResponseEntity<Optional<Etiqueta>> encontrarEtiqueta(@PathVariable Long id){
        Optional<Etiqueta> resultado = etiquetaService.encontrarEtiqueta(id);
        if(resultado.isPresent()){
            return ResponseEntity.ok().body(resultado);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear una etiqueta
    @PostMapping("/etiquetas")
    public ResponseEntity<Etiqueta> crearEtiqueta(@RequestBody Etiqueta etiqueta){

        Etiqueta resultado = etiquetaService.crearEtiqueta(etiqueta);

        if(resultado == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return ResponseEntity.ok().body(resultado);
        }
    }

    // Actualiza la etiqueta con ID = id
    @PutMapping("/etiquetas/{id}")
    public ResponseEntity<Etiqueta> actualizaEtiquetaPorId(@RequestBody Etiqueta etiqueta, @PathVariable Long id){
        etiqueta.setId(id);
        Etiqueta resultado = etiquetaService.actualizarEtiqueta(etiqueta);
        if(resultado == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return ResponseEntity.ok().body(resultado);
        }
    }

    // Borra la etiqueta con ID = id
    @DeleteMapping("/etiquetas/{id}")
    public ResponseEntity<Boolean> borrarEtiqueta(@PathVariable Long id){

        Boolean resultado = etiquetaService.borrarEtiqueta(id);
        if(resultado == true){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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










    // Encontrar todas las etiquetas
//    @GetMapping("/etiquetas")
//    public ResponseEntity<List<Etiqueta>> encontrarTodasEtiquetas(){
//        List<Etiqueta> listaEtiquetas = etiquetaService.encontrarTodas();
//        if(listaEtiquetas.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }else{
//            return ResponseEntity.ok().body(listaEtiquetas);
//        }
//    }


    // Encontrar todas las etiquetas CON PAGINACION
//    @GetMapping("/etiquetas")
//    public Page<Etiqueta> encontrarTodasEtiquetasPaginacion(@PageableDefault(size=10, page=0) Pageable pageable){
//        Page<Etiqueta> listaEtiquetas = etiquetaService.encontrarTodas(pageable);
//
//        if(listaEtiquetas.isEmpty()){
//            return null;
//        }else{
//            return listaEtiquetas;
//        }
//    }

    // Actualiza etiqueta
//    @PutMapping("/etiqueta")
//    public ResponseEntity<Etiqueta> actualizaEtiqueta(@RequestBody Etiqueta etiqueta){
//        Etiqueta resultado = etiquetaService.actualizarEtiqueta(etiqueta);
//        if(etiquetaService == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }else{
//            return ResponseEntity.ok().body(resultado);
//        }
//    }

    // Crear una lista de etiquetas
//    @PostMapping("/etiqueta/lista")
//    public ResponseEntity<List<Etiqueta>> crearListEtiquetas(@RequestBody List<Etiqueta> etiquetas){
//
//        List<Etiqueta> listaEtiquetas = etiquetaService.crearEtiquetas(etiquetas);
//
//        if(listaEtiquetas.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }else{
//            return ResponseEntity.ok().body(listaEtiquetas);
//        }
//    }

    // Recupera las etiquetas por su nombre
//    @GetMapping("/etiqueta/nombre/{nombre}")
//    public ResponseEntity<List<Etiqueta>> encontrarEtiquetaPorNombre(@PathVariable String nombre){
//        List<Etiqueta> listaEtiqueta = etiquetaDAO.encontrarPorNombre(nombre);
//        if(listaEtiqueta.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }else{
//            return ResponseEntity.ok().body(listaEtiqueta);
//        }
//    }

    // Filtrar por nombre
//    @GetMapping("/etiqueta")
//    public ResponseEntity<List<Etiqueta>> encontrarEtiquetaPorNombre(@RequestParam Map<String, String> parametros){
//
//        if(parametros.containsKey("nombre")== false)
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//        String nombre = parametros.get("nombre");
//        List<Etiqueta> listaEtiqueta = etiquetaDAO.encontrarPorNombre(nombre);
//        if(listaEtiqueta.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }else{
//            return ResponseEntity.ok().body(listaEtiqueta);
//        }
//    }

}
