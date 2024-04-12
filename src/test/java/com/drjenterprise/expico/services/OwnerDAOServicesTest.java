package com.drjenterprise.expico.services;

import com.drjenterprise.expico.entities.dao.owner.OwnerDAO;
import com.drjenterprise.expico.entities.dto.request.owner.OwnerREQ;
import com.drjenterprise.expico.entities.dto.response.owner.OwnerRES;
import com.drjenterprise.expico.repositories.OwnerRepository;
import com.drjenterprise.expico.services.mappers.OwnerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OwnerDAOServicesTest {

    @Mock
    private OwnerRepository mockOwnerRepository;
    @Mock
    private OwnerMapper mockOwnerMapper;
    @InjectMocks
    private OwnerServices mockOwnerServices;

    @Test
    void createOwnerNotInDatabase() {
        OwnerREQ newOwnerREQ = new OwnerREQ();
        newOwnerREQ.setOwnerNIF(123456789);
        OwnerDAO newOwnerDAO = new OwnerDAO();
        newOwnerDAO.setOwnerNIF(newOwnerREQ.getOwnerNIF());
        OwnerRES newOwnerRES = new OwnerRES();
        newOwnerRES.setOwnerNIF(newOwnerDAO.getOwnerNIF());

        when(mockOwnerRepository.findByOwnerNIF(newOwnerREQ.getOwnerNIF())).thenReturn(null);
        when(mockOwnerRepository.save(newOwnerDAO)).thenReturn(newOwnerDAO);
        when(mockOwnerMapper.convert(newOwnerREQ)).thenReturn(newOwnerDAO);
        when(mockOwnerMapper.convert(newOwnerDAO)).thenReturn(newOwnerRES);

        OwnerRES savedUser = mockOwnerServices.createOwner(newOwnerREQ);

        assertEquals(newOwnerREQ.getOwnerNIF(), savedUser.getOwnerNIF());
    }

    @Test
    void createOwnerExistsInDatabase() {
        OwnerREQ newOwnerREQ = new OwnerREQ();
        newOwnerREQ.setOwnerNIF(123456789);
        OwnerDAO newOwnerDAO = new OwnerDAO();
        newOwnerDAO.setOwnerNIF(newOwnerREQ.getOwnerNIF());
        OwnerRES newOwnerRES = new OwnerRES();
        newOwnerRES.setOwnerNIF(newOwnerDAO.getOwnerNIF());

        OwnerDAO existingOwnerDAO = new OwnerDAO();
        existingOwnerDAO.setOwnerNIF(123456789);

        when(mockOwnerRepository.findByOwnerNIF(newOwnerREQ.getOwnerNIF())).thenReturn(Optional.of(newOwnerDAO));

        OwnerRES savedUser = mockOwnerServices.createOwner(newOwnerREQ);

        assertNull(savedUser);
    }
}