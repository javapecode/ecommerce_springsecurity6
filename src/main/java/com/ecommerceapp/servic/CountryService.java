package com.ecommerceapp.servic;

import java.util.List;

import com.ecommerceapp.entity.Country;

public interface CountryService {
	Country createCountry(Country country);

    Country getCountryById(int id);

    List<Country> getAllCountries();

    Country updateCountry(int id, Country country);
    
    List<Country> filterCountries(String name, Boolean status);
    
    void deleteCountry(int id);
}
