package cl.duoc.catalog_service.controller;

import cl.duoc.catalog_service.entity.Producto;
import cl.duoc.catalog_service.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products") // Coincide con la ruta del Gateway
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // 1. Listar todos: GET /api/products
    @GetMapping
    public List<Producto> listarTodos() {
        return productoService.listarTodos();
    }

    // 2. Buscar por ID: GET /api/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.buscarPorId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    // 3. Guardar: POST /api/products
    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    // 4. Eliminar: DELETE /api/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}