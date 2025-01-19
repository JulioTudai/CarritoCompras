package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Mapper.IMapperProveedor;
import com.CarritoCompras.Model.DTO.ProveedorDTO;
import com.CarritoCompras.Model.Entity.ProductoEntity;
import com.CarritoCompras.Model.Entity.ProveedorEntity;
import com.CarritoCompras.Repository.IProveedorRepository;
import com.CarritoCompras.Service.Interface.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceIMP implements IProveedorService {

    @Autowired
    private IProveedorRepository proveedorRepository;

    @Override
    public List<ProveedorDTO> findAll() {
        return this.proveedorRepository.findAll().stream()
                .map(proveedorEntity -> IMapperProveedor.INSTANCE.toDTO(proveedorEntity))
                .collect(Collectors.toList());
    }

    public ProveedorDTO findById(Long id){

        Optional<ProveedorEntity> proveedorEntity = this.proveedorRepository.findById(id);

        if(proveedorEntity.isPresent()){

            ProveedorEntity proveedor = proveedorEntity.get();

            return IMapperProveedor.INSTANCE.toDTO(proveedor);
        }

        return  new ProveedorDTO();
    }

    public ProveedorDTO saveProveedor(ProveedorDTO proveedorDTO) {
        Optional<ProveedorEntity> proveedorEntity = this.proveedorRepository.findByName(proveedorDTO.getName());

        if (proveedorEntity.isPresent()) {
            throw new IllegalArgumentException("El proveedor con el nombre " + proveedorDTO.getName() + " ya existe.");
        }

        ProveedorEntity proveedor = IMapperProveedor.INSTANCE.toEntity(proveedorDTO);

        // Mapeamos los productos DTO a entidades
        List<ProductoEntity> productos = proveedorDTO.getProducts().stream()
                .map(productDTO -> ProductoEntity.builder()
                        .name(productDTO.getName())
                        .price(productDTO.getPrice())
                        .description(productDTO.getDescription())
                        .stock(productDTO.getStock())
                        .proveedor(proveedor) // Asociar al proveedor
                        .build())
                .collect(Collectors.toList());
        proveedor.setProducts(productos);

        return IMapperProveedor.INSTANCE.toDTO(proveedorRepository.save(proveedor));
    }


    public ProveedorDTO updateProveedor(Long id, ProveedorDTO proveedorDTO){

        if ((!proveedorRepository.existsById(id))){

            throw new IllegalArgumentException("El usuario con el id " + proveedorDTO.getId() + " no existe.");
        }

        ProveedorEntity proveedor = proveedorRepository.findById(id).get();

        proveedor.setName(proveedorDTO.getName());
        proveedor.setEmail(proveedorDTO.getEmail());
        proveedor.setPhone(proveedor.getPhone());
        proveedor.setCompanyName(proveedorDTO.getCompanyName());

      /*  if (proveedorDTO.getProducts() != null) {
            List<ProductoEntity> productos = proveedorDTO.getProducts().stream()
                    .map(productDTO -> ProductoEntity.builder()
                            .name(productDTO.getName())
                            .price(productDTO.getPrice())
                            .description(productDTO.getDescription())
                            .stock(productDTO.getStock())
                            .proveedor(proveedor) // Asociar al proveedor
                            .build())
                    .collect(Collectors.toList());
            proveedor.setProducts(productos);
        }

       */
        if (proveedorDTO.getProducts() != null) {
            // Limpia la lista existente sin perder la referencia
            proveedor.getProducts().clear();

            // Agrega los nuevos productos a la lista existente
            proveedorDTO.getProducts().forEach(productDTO -> {
                ProductoEntity producto = ProductoEntity.builder()
                        .name(productDTO.getName())
                        .price(productDTO.getPrice())
                        .description(productDTO.getDescription())
                        .stock(productDTO.getStock())
                        .proveedor(proveedor) // Asociar al proveedor
                        .build();
                proveedor.getProducts().add(producto);
            });
        }

        return IMapperProveedor.INSTANCE.toDTO(this.proveedorRepository.save(proveedor));

    }

    public String deletProveedor(Long id){

        if (proveedorRepository.existsById(id)){
            proveedorRepository.deleteById(id);
            return "Proveedor con el ID"+ id +"eliminado correctamente.";
        }
        else {
            throw new IllegalArgumentException("Cliente no encontrado ID: " + id);
        }

    }












}
