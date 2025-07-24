package com.ecommerceapp.servic;

import java.util.List;

import com.ecommerceapp.entity.District;

public interface DistrictService {
    District createDistrict(District district);
    District getDistrictById(int id);
    List<District> getAllDistricts();
    List<District> searchDistrictsByName(String name);
    District updateDistrict(int id, District updatedDistrict);
    void deleteDistrict(int id);
}

