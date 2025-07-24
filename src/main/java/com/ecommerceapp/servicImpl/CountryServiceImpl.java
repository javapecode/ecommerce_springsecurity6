package com.ecommerceapp.servicImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ecommerceapp.entity.Country;
import com.ecommerceapp.exception.ResourceNotFoundException;
import com.ecommerceapp.resitory.CountryRepo;
import com.ecommerceapp.servic.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	private static final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

	@Autowired
	private CountryRepo countryRepository;

	/**
	 * Creates and saves a new country.
	 */
	@Override
	public Country createCountry(Country country) {
		try {
			if (country == null) {
				logger.warn("Attempted to create a null country.");
				throw new IllegalArgumentException("Country object must not be null.");
			}

			if (country.getName() == null || country.getName().trim().isEmpty()) {
				logger.warn("Attempted to create country with empty name.");
				throw new IllegalArgumentException("Country name must not be empty.");
			}

			Country saved = countryRepository.save(country);
			logger.info("Country created successfully with ID: {}", saved.getId());
			return saved;

		} catch (IllegalArgumentException | DataIntegrityViolationException e) {
			logger.error("Error while creating country: {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Unexpected error in createCountry(): {}", e.getMessage(), e);
			throw new RuntimeException("Unexpected error occurred while creating country.");
		}
	}

	@Override
	public List<Country> filterCountries(String name, Boolean status) {
		try {
			if (name != null && status != null) {
				logger.info("Filtering countries by name and status");
				return countryRepository.findByNameContainingIgnoreCaseAndStatus(name, status);
			} else if (name != null) {
				logger.info("Filtering countries by name only");
				return countryRepository.findByNameContainingIgnoreCase(name);
			} else if (status != null) {
				logger.info("Filtering countries by status only");
				return countryRepository.findByStatus(status);
			} else {
				logger.info("No filters provided, fetching all countries");
				return countryRepository.findAll();
			}
		} catch (Exception e) {
			logger.error("Error while filtering countries: {}", e.getMessage(), e);
			throw new RuntimeException("Failed to filter countries.");
		}
	}

	/**
	 * Retrieves a country by its ID.
	 */
	@Override
	public Country getCountryById(int id) {
		try {
			Country country = countryRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Country not found with ID: " + id,
							this.getClass().getSimpleName(), "getCountryById"));

			logger.info("Country fetched with ID: {}", id);
			return country;

		} catch (Exception e) {
			logger.error("Error retrieving country with ID {}: {}", id, e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * Retrieves all countries.
	 */
	@Override
	public List<Country> getAllCountries() {
		try {
			List<Country> countries = countryRepository.findAll();
			logger.info("Fetched {} countries from the database.", countries.size());
			return countries;
		} catch (Exception e) {
			logger.error("Error retrieving countries: {}", e.getMessage(), e);
			throw new RuntimeException("Failed to fetch country list.");
		}
	}

	/**
	 * Updates a country by ID.
	 */
	@Override
	public Country updateCountry(int id, Country updatedCountry) {
		try {
			Country existing = countryRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Country not found with ID: " + id,
							this.getClass().getSimpleName(), "updateCountry"));

			existing.setName(updatedCountry.getName());
			existing.setStatus(updatedCountry.isStatus());

			Country saved = countryRepository.save(existing);
			logger.info("Country updated successfully with ID: {}", saved.getId());
			return saved;

		} catch (DataIntegrityViolationException e) {
			logger.error("Constraint violation during updateCountry(): {}", e.getMostSpecificCause().getMessage(), e);
			throw new RuntimeException("Constraint violation during country update.");

		} catch (Exception e) {
			logger.error("Error updating country with ID {}: {}", id, e.getMessage(), e);
			throw new RuntimeException("Unexpected error occurred while updating country.");
		}
	}

	/**
	 * Deletes a country by ID.
	 */
	@Override
	public void deleteCountry(int id) {
		try {
			Country country = countryRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Country not found with ID: " + id,
							this.getClass().getSimpleName(), "deleteCountry"));

			countryRepository.delete(country);
			logger.info("Country deleted successfully with ID: {}", id);

		} catch (Exception e) {
			logger.error("Error deleting country with ID {}: {}", id, e.getMessage(), e);
			throw new RuntimeException("Unexpected error occurred while deleting country.");
		}
	}
}
