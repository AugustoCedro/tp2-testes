package org.example.ex4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    private CustomerService service;

    @BeforeEach
    void setup() {
        service = new CustomerService();
    }

    // --- Cadastro com análise de valor limite ---
    @Test
    void testRegisterCustomerAgeBelow18() {
        Customer c = new Customer(1, "Ana", "ana@email.com", 17, true);
        assertFalse(service.registerCustomer(c), "Idade < 18 deve ser inválida");
    }

    @Test
    void testRegisterCustomerAgeExactly18() {
        Customer c = new Customer(2, "Bruno", "bruno@email.com", 18, true);
        assertTrue(service.registerCustomer(c), "Idade 18 deve ser válida");
    }

    @Test
    void testRegisterCustomerAgeExactly99() {
        Customer c = new Customer(3, "Carlos", "carlos@email.com", 99, true);
        assertTrue(service.registerCustomer(c), "Idade 99 deve ser válida");
    }

    @Test
    void testRegisterCustomerAgeAbove99() {
        Customer c = new Customer(4, "Diana", "diana@email.com", 100, true);
        assertFalse(service.registerCustomer(c), "Idade > 99 deve ser inválida");
    }

    // --- Atualização ---
    @Test
    void testUpdateCustomerActive() {
        Customer c = new Customer(5, "Eva", "eva@email.com", 25, true);
        boolean updated = service.updateCustomer(c, "Eva Maria", "eva.maria@email.com", 26);

        assertTrue(updated);
        assertEquals("Eva Maria", c.getName());
        assertEquals("eva.maria@email.com", c.getEmail());
        assertEquals(26, c.getAge());
    }

    @Test
    void testUpdateCustomerInactive() {
        Customer c = new Customer(6, "Felipe", "felipe@email.com", 30, false);
        boolean updated = service.updateCustomer(c, "Felipe Novo", "felipenovo@email.com", 31);

        assertFalse(updated);
        assertEquals("Felipe", c.getName(), "Cliente inativo não deve ser atualizado");
    }

    // --- Exclusão ---
    @Test
    void testDeleteCustomerActive() {
        Customer c = new Customer(7, "Gustavo", "gustavo@email.com", 40, true);
        assertTrue(service.deleteCustomer(c));
    }

    @Test
    void testDeleteCustomerInactive() {
        Customer c = new Customer(8, "Helena", "helena@email.com", 45, false);
        assertFalse(service.deleteCustomer(c));
    }

    @Test
    void testRegisterCustomerEmailValid() {
        Customer c = new Customer(9, "Igor", "igor@email.com", 30, true);
        assertTrue(service.registerCustomer(c));
    }

    // --- Cadastro Completo ---
    @Test
    void testRegisterCustomerValidFullData() {
        Customer c = new Customer(12, "Lucas", "lucas@email.com", 28, true);
        assertTrue(service.registerCustomer(c));
    }
}