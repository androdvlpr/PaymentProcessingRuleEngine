package com.payment.processing.ruleengine.core;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.payment.processing.rule.engine.payment.handler.MembershipUpgradeHandler;
import com.payment.processing.ruleengine.membership.datatbase.iface.IMembershipDatabase;
import com.payment.processing.ruleengine.payment.handler.iface.IPaymentHandler;
import com.payment.processing.ruleengine.services.iface.INotificationService;


public class MembershipUpgradeHandlerTests{
    @Test
    public void upgradeMembershipTest() throws Exception {
        Membership membershipSilver = new Membership("membership-silver", null);
        Membership membershipGold = new Membership("membership-gold", membershipSilver);

        AvailableItem[] availableItems = new AvailableItem[]{
            new AvailableItem(membershipGold.getStockInUnit(), "gold", new ProductCategory[]{
                ProductCategory.Membership
            })
        };
        Customer customer = mock(Customer.class);
        when(customer.hasMembership(membershipSilver)).thenReturn(true);

        Order order = new Order(customer, availableItems, null);
        Payment payment = new Payment(order);

        IMembershipDatabase repo = mock(IMembershipDatabase.class);
        when(repo.findByAvailableUnitInStock(membershipGold.getStockInUnit())).thenReturn(membershipGold);
        
        INotificationService notificationService = mock(INotificationService.class);

        IPaymentHandler paymentHandle = new MembershipUpgradeHandler(repo, notificationService);
        paymentHandle.execute(payment);

        verify(customer, times(1)).addMembership(membershipGold, notificationService);
    }

    @Test
    public void notifyCustomerTest() throws Exception {
        Membership membershipSilver = new Membership("membership-silver", null);
        Membership membershipGold = new Membership("membership-gold", membershipSilver);

        AvailableItem[] availableItems = new AvailableItem[]{
            new AvailableItem(membershipGold.getStockInUnit(), "gold", new ProductCategory[]{
                ProductCategory.Membership
            })
        };
        Customer customer = spy(Customer.class);
        when(customer.hasMembership(membershipSilver)).thenReturn(true);

        Order order = new Order(customer, availableItems, null);
        Payment payment = new Payment(order);

        IMembershipDatabase memberDataBase = mock(IMembershipDatabase.class);
        when(memberDataBase.findByAvailableUnitInStock(membershipGold.getStockInUnit())).thenReturn(membershipGold);
        
        INotificationService notificationService = mock(INotificationService.class);

        IPaymentHandler paymentHandler = new MembershipUpgradeHandler(memberDataBase, notificationService);
        paymentHandler.execute(payment);

        verify(notificationService, times(1)).notify(customer, membershipGold);
    }
}