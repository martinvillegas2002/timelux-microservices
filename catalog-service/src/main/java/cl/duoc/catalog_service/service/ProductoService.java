package cl.duoc.catalog_service.service;

import cl.duoc.catalog_service.entity.Producto;
import cl.duoc.catalog_service.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto) {
        // Aquí podrías validar cosas (ej. precio no negativo)
        if (producto.getPrecio() < 0) {
            throw new RuntimeException("El precio no puede ser negativo");
        }
        return productoRepository.save(producto);
    }
    
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }
    
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}