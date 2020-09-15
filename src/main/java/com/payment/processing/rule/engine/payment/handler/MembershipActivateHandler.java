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


// If the payment is for a membership, activate that membership.
// If the payment is for a membership or upgrade, e-mail the owner and inform them of the activation/upgrade.
public class MembershipActivateHandler implements IPaymentHandler{
    private final IMembershipDatabase m_memberShipService;
    private final INotificationService m_notificatinService;

    public MembershipActivateHandler(final IMembershipDatabase membershipService, final INotificationService notificationService) {
        m_memberShipService = membershipService;
        m_notificatinService = notificationService;
    }

    @Override
    public void execute(final Payment payment) {
        final Order order = payment.getOrder();
        final AvailableItem[] lineItems = order.getItemsInStock();
        final Customer customer = order.getCustomer();
        for (final AvailableItem lineItem : lineItems) {
            if (!lineItem.hasCategory(ProductCategory.Membership))
                continue;

            final Membership membership = m_memberShipService.findByAvailableUnitInStock(lineItem.getItemInUnit());
            if(membership != null)
                customer.addMembership(membership, m_notificatinService);
        }
    }

}