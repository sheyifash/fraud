package com.example.frauddetection.Service;

import com.example.frauddetection.Dao.FraudLogDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private FraudLogDao fraudLogDao;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setUp() {}

    @Test
    void testGetFlaggedTransactions_ReturnsListOfTransactions() {
        // Arrange
        Map<String, Object> transaction1 = new HashMap<>();
        transaction1.put("id", 1);
        transaction1.put("status", "FLAGGED");

        Map<String, Object> transaction2 = new HashMap<>();
        transaction2.put("id", 2);
        transaction2.put("status", "FLAGGED");

        List<Map<String, Object>> mockResponse = List.of(transaction1, transaction2);

        when(fraudLogDao.getFlaggedTransactions()).thenReturn(mockResponse);

        // Act
        List<Map<String, Object>> result = adminService.getFlaggedTransactions();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("FLAGGED", result.get(0).get("status"));

        verify(fraudLogDao, times(1)).getFlaggedTransactions();
    }

    @Test
    void testGetFlaggedTransactions_ReturnsEmptyList() {
        // Arrange
        when(fraudLogDao.getFlaggedTransactions()).thenReturn(Collections.emptyList());

        // Act
        List<Map<String, Object>> result = adminService.getFlaggedTransactions();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(fraudLogDao, times(1)).getFlaggedTransactions();
    }
}