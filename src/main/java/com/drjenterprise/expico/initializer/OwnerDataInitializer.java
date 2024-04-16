package com.drjenterprise.expico.initializer;

import com.drjenterprise.expico.entities.dao.owner.OwnerDAO;
import com.drjenterprise.expico.repositories.OwnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class OwnerDataInitializer implements CommandLineRunner {

    private static final Logger logger = Logger.getLogger(OwnerDataInitializer.class.getName());
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ProfileOwner profileOwner;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(String... args){
        File profileOwnerFile = new File("src/main/resources/static/DataInitialization/profile_owner.json");

        if(profileOwnerFile.exists()) {
            try {

                OwnerDAO ownerDAO = objectMapper.readValue(profileOwnerFile, OwnerDAO.class);
                OwnerDAO existingOwnerDao = ownerRepository.findByOwnerNIF(ownerDAO.getOwnerNIF()).orElse(null);

                if (existingOwnerDao != null){
                    setProfileOwner(profileOwner, existingOwnerDao);
                    logger.info("The owner profile already exists in the DB. Profile not loaded.");
                } else {
                    OwnerDAO newOwnerDao = ownerRepository.save(ownerDAO);
                    setProfileOwner(profileOwner, newOwnerDao);
                    logger.info("The owner profile was loaded into the DB.");
                }
            } catch (IOException e) {
                logger.severe("Failed to parse profile owner JSON file: " + e.getMessage());
            }
        } else {
            logger.severe("Profile owner JSON file not found.");
        }
    }

    private void setProfileOwner(ProfileOwner profileOwner, OwnerDAO ownerDAO) {
        profileOwner.setProfileOwnerInternalId(ownerDAO.getOwnerInternalId());
        profileOwner.setProfileOwnerGovId(ownerDAO.getOwnerGovId());
        profileOwner.setProfileOwnerNIF(ownerDAO.getOwnerNIF());
        profileOwner.setProfileOwnerName(ownerDAO.getOwnerName());
        profileOwner.setProfileOwnerCellNumber(ownerDAO.getOwnerCellNumber());
        profileOwner.setProfileOwnerEmail(ownerDAO.getOwnerEmail());
    }
}
