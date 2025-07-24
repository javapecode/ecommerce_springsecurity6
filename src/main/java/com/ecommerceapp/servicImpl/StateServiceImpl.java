package com.ecommerceapp.servicImpl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ecommerceapp.entity.State;
import com.ecommerceapp.exception.ResourceNotFoundException;
import com.ecommerceapp.resitory.StateRepo;
import com.ecommerceapp.servic.StateService;

@Service
public class StateServiceImpl implements StateService {

    private static final Logger logger = LoggerFactory.getLogger(StateServiceImpl.class);

    @Autowired
    private StateRepo stateRepo;

    @Override
    public State createState(State state) {
        try {
            if (state.getName() == null || state.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("State name must not be empty.");
            }
            return stateRepo.save(state);
        } catch (IllegalArgumentException e) {
            logger.warn("Validation error in createState(): {}", e.getMessage());
            throw e;
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation in createState(): {}", e.getMostSpecificCause().getMessage());
            throw new RuntimeException("Constraint violation occurred.");
        } catch (Exception e) {
            logger.error("Unexpected error in createState(): {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error occurred while saving state.");
        }
    }

    @Override
    public State getStateById(int id) {
        try {
            return stateRepo.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("State not found with ID: " + id,
                            this.getClass().getSimpleName(), "getStateById"));
        } catch (Exception e) {
            logger.error("Error in getStateById(): {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<State> getAllStates() {
        try {
            List<State> list = stateRepo.findAll();
            logger.info("Fetched {} states", list.size());
            return list;
        } catch (Exception e) {
            logger.error("Error in getAllStates(): {}", e.getMessage(), e);
            throw new RuntimeException("Error fetching states.");
        }
    }

    @Override
    public State updateState(int id, State updated) {
        try {
            State existing = stateRepo.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("State not found with ID: " + id,
                            this.getClass().getSimpleName(), "updateState"));

            existing.setName(updated.getName());
            existing.setStatus(updated.isStatus());
            existing.setCountry(updated.getCountry());

            State saved = stateRepo.save(existing);
            logger.info("State updated with ID: {}", saved.getId());
            return saved;

        } catch (Exception e) {
            logger.error("Error in updateState(): {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteState(int id) {
        try {
            State existing = stateRepo.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("State not found with ID: " + id,
                            this.getClass().getSimpleName(), "deleteState"));
            stateRepo.delete(existing);
            logger.info("Deleted state with ID: {}", id);
        } catch (Exception e) {
            logger.error("Error in deleteState(): {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while deleting state.");
        }
    }
    
    public List<State> searchStatesByName(String name) {
        return stateRepo.findByNameContainingIgnoreCase(name);
    }

}
