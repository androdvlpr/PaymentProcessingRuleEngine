package com.payment.processing.ruleengine.core;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.payment.processing.rule.engine.payment.handler.MembershipActivateHandler;
import com.payment.processing.ruleengine.membership.datatbase.iface.IMembershipDatabase;
import com.payment.processing.ruleengine.payment.handler.iface.IPaymentHandler;
import com.payment.processing.ruleengine.services.iface.INotificationService;

public class MembershipActivateHandlerTests{
	
    @Test
    public void doNothingIfNoMembershipsInOrderTest() throws Exception {
        AvailableItem[] availableItems = new AvailableItem[]{
            new AvailableItem("item1", "item1", new ProductCategory[]{
                ProductCategory.Physical
            })
        };
        Customer customer = mock(Customer.class);
        Order order = new Order(customer, availableItems, null);
        Payment payment = new Payment(order);

        IMembershipDatabase service = mock(IMembershipDatabase.class);
        INotificationService notificationService = mock(INotificationService.class);
        IPaymentHandler paymentHandler = new MembershipActivateHandler(service, notificationService);
        paymentHandler.execute(payment);

        verify(customer, never()).addMembership(any(), any());
    }

    @Test
    public void activateMembershipIfInOrderTest() throws Exception {
        AvailableItem[] availableItems = new AvailableItem[]{
            new AvailableItem("item1", "item1", new ProductCategory[]{
                ProductCategory.Membership
            })
        };
        Customer customer = mock(Customer.class);
        Order order = new Order(customer, availableItems, null);
        Payment payment = new Payment(order);

        IMembershipDatabase repo = mock(IMembershipDatabase.class);
        Membership membership = new Membership("item1", null);
        when(repo.findByAvailableUnitInStock("item1")).thenReturn(membership);

        INotificationService notificationService = mock(INotificationService.class);

        IPaymentHandler paymentHandler = new MembershipActivateHandler(repo, notificationService);
        paymentHandler.execute(payment);

        verify(customer, times(1)).addMembership(membership, notificationService);
    }

    @Test
    public void notifyCustomerTest() throws Exception {
        AvailableItem[] availableItems = new AvailableItem[]{
            new AvailableItem("item1", "item1", new ProductCategory[]{
                ProductCategory.Membership
            })
        };
        Customer customer = spy(Customer.class);
        Order order = new Order(customer, availableItems, null);
        Payment payment = new Payment(order);

        IMembershipDatabase repo = mock(IMembershipDatabase.class);
        Membership membership = new Membership("item1", null);
        when(repo.findByAvailableUnitInStock("item1")).thenReturn(membership);

        INotificationService notificationService = mock(INotificationService.class);

        IPaymentHandler paymentHandler = new MembershipActivateHandler(repo, notificationService);
        paymentHandler.execute(payment);

        verify(notificationService, times(1)).notify(customer, membership);
    }
}