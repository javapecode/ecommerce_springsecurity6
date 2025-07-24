package com.ecommerceapp.servicImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ecommerceapp.entity.District;
import com.ecommerceapp.exception.ResourceNotFoundException;
import com.ecommerceapp.resitory.DistrictRepo;
import com.ecommerceapp.servic.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService {

    private static final Logger logger = LoggerFactory.getLogger(DistrictServiceImpl.class);

    @Autowired
    private DistrictRepo districtRepo;

    @Override
    public District createDistrict(District district) {
        try {
            if (district == null || district.getName() == null || district.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("District name must not be empty.");
            }
            return districtRepo.save(district);
        } catch (DataIntegrityViolationException e) {
            logger.error("Constraint violation in createDistrict(): {}", e.getMostSpecificCause().getMessage());
            throw new RuntimeException("Constraint violation while creating district.");
        } catch (Exception e) {
            logger.error("Unexpected error in createDistrict(): {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error while creating district.");
        }
    }

    @Override
    public District getDistrictById(int id) {
        return districtRepo.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("District not found with ID: " + id,
                this.getClass().getSimpleName(), "getDistrictById"));
    }

    @Override
    public List<District> getAllDistricts() {
        return districtRepo.findAll();
    }

    @Override
    public List<District> searchDistrictsByName(String name) {
        return districtRepo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public District updateDistrict(int id, District updatedDistrict) {
        try {
            District existing = getDistrictById(id);
            existing.setName(updatedDistrict.getName());
            existing.setStatus(updatedDistrict.isStatus());
            existing.setState(updatedDistrict.getState());
            return districtRepo.save(existing);
        } catch (Exception e) {
            logger.error("Error updating district: {}", e.getMessage(), e);
            throw new RuntimeException("Error while updating district.");
        }
    }

    @Override
    public void deleteDistrict(int id) {
        District existing = getDistrictById(id);
        districtRepo.delete(existing);
    }
}
