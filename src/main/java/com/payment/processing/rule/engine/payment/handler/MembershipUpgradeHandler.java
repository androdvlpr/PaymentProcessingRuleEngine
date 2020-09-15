package com.payment.processing.rule.engine.payment.handler;

import com.payment.processing.ruleengine.core.AvailableItem;
import com.payment.processing.ruleengine.core.Customer;
import com.payment.processing.ruleengine.core.Membership;
import com.payment.processing.ruleengine.core.Order;
import com.payment.processing.ruleengine.core.Payment;
import com.payment.processing.ruleengine.core.ProductCategory;
import com.payment.processing.ruleengine.membership.datatbase.iface.IMembershipDatabase;
import com.payment.processing.ruleengine.payment.handler.iface.IPaymentHandler;
import com.payment.processing.ruleengine.services.iface.INotificationService;

// If the payment is an upgrade to a membership, apply the upgrade.
// If the payment is for a membership or upgrade, e-mail the owner and inform them of the activation/upgrade.
public class MembershipUpgradeHandler implements IPaymentHandler {
	
	private final IMembershipDatabase m_MemeberDatabase;
    private final INotificationService m_NotificationService;
    
    public MembershipUpgradeHandler(final IMembershipDatabase memberShipDatabase, final INotificationService notificationService) {
    	m_MemeberDatabase = memberShipDatabase;
    	m_NotificationService = notificationService;
	}

	@Override
	public void execute(Payment payment) {
		Order order = payment.getOrder();
        Customer customer = order.getCustomer();
        AvailableItem[] availableItems = order.getItemsInStock();
        for (AvailableItem availableItem : availableItems) {
            if(!availableItem.hasCategory(ProductCategory.Membership))
                continue;

            Membership membership = m_MemeberDatabase.findByAvailableUnitInStock(availableItem.getItemInUnit());
            Membership previousLevel = membership.getPreviousLevel();
            if(customer.hasMembership(previousLevel))
                customer.addMembership(membership, m_NotificationService);
        }
	}


    
}