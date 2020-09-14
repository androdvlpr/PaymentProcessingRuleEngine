package com.payment.processing.ruleengine.core;

import com.payment.processing.ruleengine.payment.handler.iface.IPaymentHandler;

public class Payment {
	private final Order order;
	
    public Payment(final Order order){
        this.order = order;
    }

    public Order getOrder(){
        return order;
    }

    public void Process(IPaymentHandler[] rules){
        for(IPaymentHandler rule : rules)
            rule.execute(this);
    }
}