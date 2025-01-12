package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Mapper.IMapperProveedor;
import com.CarritoCompras.Model.DTO.ProveedorDTO;
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

    public ProveedorDTO saveProveedor(ProveedorDTO proveedorDTO){

        Optional<ProveedorEntity> proveedorEntity = this.proveedorRepository.findByName(proveedorDTO.getName());

        if (proveedorEntity.isPresent()){

            throw new IllegalArgumentException("El usuario con el nombre" + proveedorDTO.getName() + "Ya existe.");
        }
        try{
            System.out.println("Dentro del try" + proveedorDTO.getName());

            ProveedorEntity proveedor = IMapperProveedor.INSTANCE.toEntity(proveedorDTO);

            System.out.println("Dentro del try despues del mapeo" + proveedor.getName());
            return IMapperProveedor.INSTANCE.toDTO(proveedorRepository.save(proveedor));

        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al guardar el usuario");
        }

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
        if (proveedorDTO.getProducts() != null) {

            List<String> validProducts = proveedorDTO.getProducts().stream()
                    .filter(product -> product != null && !product.isEmpty()) // esto es para validación
                    .distinct() // para evitar duplicados
                    .collect(Collectors.toList());
            proveedor.setProducts(validProducts);
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
