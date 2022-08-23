package com.clinic.dentalclinic.service.impl;

import com.clinic.dentalclinic.model.Address;
import com.clinic.dentalclinic.repository.AddressRepository;
import com.clinic.dentalclinic.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address getAddressById(Long id) {
        Optional<Address> op = addressRepository.findById(id);
        Address address = null;
        if (op.isPresent()){
            address = op.get();
        } else {
            logger.info("Address with id %s not found".formatted(id));
        }
        return address;
    }

    @Override
    public Address updateAddressById(Long id, Address addressUpdate) {
        Address address = getAddressById(id);
        if (address != null){
            address.setStreet(addressUpdate.getStreet());
            address.setNumber(addressUpdate.getNumber());
            address.setCity(addressUpdate.getCity());
            address.setProvince(addressUpdate.getProvince());
            addressRepository.save(address);
        } else {
            logger.info("Address with id %s not found".formatted(id));
        }
        return address;
    }

    @Override
    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }
}
