package org.example.ex3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private PaymentProcessor paymentProcessor;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testProcessOrderSuccessfully(){
        when(paymentProcessor.processPayment(anyDouble())).thenReturn(true);

        boolean result = orderService.processOrder(100.0);

        assertTrue(result);

        verify(paymentProcessor,times(1)).processPayment(100.0);
    }

    @Test
    void testProcessOrderFailure(){
        when(paymentProcessor.processPayment(anyDouble())).thenReturn(false);

        boolean result = orderService.processOrder(50.0);

        assertTrue(result);

        verify(paymentProcessor,times(1)).processPayment(50.0);
    }




}
