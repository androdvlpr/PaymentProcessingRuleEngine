package com.payment.processing.ruleengine.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import org.junit.Test;

import com.payment.processing.rule.engine.payment.handler.PackingSlipHandler;
import com.payment.processing.ruleengine.payment.handler.iface.IPaymentHandler;
import com.payment.processing.ruleengine.services.iface.IPackingSlipService;
import com.payment.processing.ruleengine.services.iface.IRoyaltyService;
import com.payment.processing.ruleengine.services.iface.IShippingService;


public class PackingSlipHandlerTests{
    public PackingSlipHandlerTests(){}

    @Test
    public void shouldGenerateForShippingWhenPhysicalAvailableTest() throws Exception {
        AvailableItem[] availableItems = new AvailableItem[]{
            new AvailableItem("item1", "item1", new ProductCategory[]{
                ProductCategory.Physical
            }),
            new AvailableItem("item2", "item2", new ProductCategory[]{
                ProductCategory.Membership,
            })
        };
        Customer customer = mock(Customer.class);
        Order order = new Order(customer, availableItems, null);
        Payment payment = new Payment(order);

        IPackingSlipService packingSlipService = mock(IPackingSlipService.class);
        IShippingService shippingService = mock(IShippingService.class);
        IRoyaltyService royaltyService = mock(IRoyaltyService.class);
        
        IPaymentHandler paymentHandler = new PackingSlipHandler(shippingService, royaltyService, packingSlipService);
        paymentHandler.execute(payment);

        verify(shippingService, times(1)).generatePackingSlip(order);
    }

    @Test
    public void shouldGenerateForRoyaltyWhenBooksAvailableTest() throws Exception {
    	AvailableItem[] availableItems = new AvailableItem[]{
            new AvailableItem("item1", "item1", new ProductCategory[]{
                ProductCategory.Books
            }),
            new AvailableItem("item2", "item2", new ProductCategory[]{
                ProductCategory.Membership,
            })
        };
        Customer customer = mock(Customer.class);
        Order order = new Order(customer, availableItems, null);
        Payment payment = new Payment(order);

        IPackingSlipService packingSlipService = mock(IPackingSlipService.class);
        IShippingService shippingService = mock(IShippingService.class);
        IRoyaltyService royaltyService = mock(IRoyaltyService.class);
        
        IPaymentHandler paymentHandler = new PackingSlipHandler(shippingService, royaltyService, packingSlipService);
        paymentHandler.execute(payment);

        verify(royaltyService, times(1)).generatePackingSlip(order);
    }

    @Test
    public void shouldAddFirstAidGiftWhenRequestedTest() throws Exception {
    	AvailableItem[] availableItems = new AvailableItem[]{
            new AvailableItem("learning-to-ski", "Learning to Ski", new ProductCategory[]{
                ProductCategory.Videos
            })
        };
        Customer customer = mock(Customer.class);
        Order order = new Order(customer, availableItems, null);
        Payment payment = new Payment(order);

        IPackingSlipService packingSlipService = mock(IPackingSlipService.class);
        IShippingService shippingService = mock(IShippingService.class);
        IRoyaltyService royaltyService = mock(IRoyaltyService.class);
        
        IPaymentHandler paymentHandler = new PackingSlipHandler(shippingService, royaltyService, packingSlipService);
        paymentHandler.execute(payment);

        String[] gifts = order.getGiftbyStockInUnit();
        assertNotNull(gifts);
        assertTrue(gifts.length == 1);
        assertTrue(Arrays.binarySearch(gifts, "first-aid") == 0); 
    }

    @Test
    public void runShouldGeneratePackingSlip() throws Exception {
        AvailableItem[] availableItems = new AvailableItem[]{
            new AvailableItem("item1", "item1", new ProductCategory[]{
                ProductCategory.Physical
            })
        };
        Customer customer = mock(Customer.class);
        Order order = new Order(customer, availableItems, null);
        Payment payment = new Payment(order);

        IPackingSlipService packingSlipService = mock(IPackingSlipService.class);
        IShippingService shippingService = mock(IShippingService.class);
        IRoyaltyService royaltyService = mock(IRoyaltyService.class);
        
        IPaymentHandler paymentHandler = new PackingSlipHandler(shippingService, royaltyService, packingSlipService);
        paymentHandler.execute(payment);

        verify(packingSlipService, times(1)).generate(order);
    }
}