package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Mapper.IMapperProducto;
import com.CarritoCompras.Model.DTO.ProductoDTO;
import com.CarritoCompras.Model.DTO.ProductoProveedorDTO;
import com.CarritoCompras.Model.Entity.ProductoEntity;
import com.CarritoCompras.Model.Entity.ProveedorEntity;
import com.CarritoCompras.Repository.IProveedorRepository;
import com.CarritoCompras.Repository.ProductoRepository;
import com.CarritoCompras.Service.Interface.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceIMP implements IProdutoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private IProveedorRepository proveedorRepository;

    @Override
    public List<ProductoDTO> findAll(){
        return this.productoRepository.findAll().stream()
                .map(productoEntity -> IMapperProducto.INSTANCE.toDTO(productoEntity))
                .collect(Collectors.toList());
    }

    public ProductoProveedorDTO findById(Long id){

        ProductoEntity producto = productoRepository.findByIdWithProveedor(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        return IMapperProducto.INSTANCE.toProductoProvedorDTO(producto);

    }

    public List<ProductoDTO> findByName(String name){

        return this.productoRepository.findByNameContainingIgnoreCase(name).stream()
                .map(IMapperProducto.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }



    public ProductoDTO saveProducto(ProductoDTO productoDTO){

        ProveedorEntity proveedor = proveedorRepository.findById(productoDTO.getProveedorId())
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));

        ProductoEntity producto = IMapperProducto.INSTANCE.toEntity(productoDTO);
        producto.setProveedor(proveedor);

        return IMapperProducto.INSTANCE.toDTO(this.productoRepository.save(producto));
    }

    public ProductoDTO updateProducto(Long id, ProductoDTO productoDTO){

        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        producto.setName(productoDTO.getName());
        producto.setPrice(productoDTO.getPrice());
        producto.setDescription(productoDTO.getDescription());
        producto.setStock(productoDTO.getStock());

        if (productoDTO.getProveedorId() != null) {

            ProveedorEntity proveedor = proveedorRepository.findById(productoDTO.getProveedorId())
                    .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));

            producto.setProveedor(proveedor);
        }

        return IMapperProducto.INSTANCE.toDTO( this.productoRepository.save(producto));

    }

    public String deletProducto (Long id){

        if(productoRepository.existsById(id)){
            this.productoRepository.deleteById(id);
            return "El producto con el id: "+ id + "ah sido eliminado";
        }
        else {
            throw new IllegalArgumentException("El prudcto con el id: "+id+" No existe");
        }

    }


}








