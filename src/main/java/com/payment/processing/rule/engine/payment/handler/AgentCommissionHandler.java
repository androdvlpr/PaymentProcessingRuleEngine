package com.payment.processing.rule.engine.payment.handler;

import com.payment.processing.ruleengine.core.Agent;
import com.payment.processing.ruleengine.core.AvailableItem;
import com.payment.processing.ruleengine.core.Order;
import com.payment.processing.ruleengine.core.Payment;
import com.payment.processing.ruleengine.core.ProductCategory;
import com.payment.processing.ruleengine.payment.handler.iface.IPaymentHandler;

// If the payment is for a physical product or a book, generate a commission payment to the agent.
public class AgentCommissionHandler implements IPaymentHandler{

	@Override
	public void execute(Payment payment) {
		Boolean addCommission = false;
		Order order = payment.getOrder();
        AvailableItem[] lineItems = order.getItemsInStock();

        for (AvailableItem lineItem : lineItems) {
            if(lineItem.hasCategory(ProductCategory.Books) || lineItem.hasCategory(ProductCategory.Physical)){
                addCommission = true;
                break;
            }
        }

        if(addCommission){
            Agent agent = order.getAgent();
            agent.generateCommission(order);
        }
	}


}