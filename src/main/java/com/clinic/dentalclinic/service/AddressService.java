package com.clinic.dentalclinic.service;

import com.clinic.dentalclinic.model.Address;


public interface AddressService {
    Address createAddress(Address address);

    Address getAddressById(Long id);

    Address updateAddressById(Long id, Address addressUpdate);

    void deleteAddressById(Long id);

}
