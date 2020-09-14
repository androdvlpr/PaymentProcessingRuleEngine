package com.payment.processing.ruleengine.services.iface;

import com.payment.processing.ruleengine.core.Customer;
import com.payment.processing.ruleengine.core.Membership;

public interface INotificationService{
	void notify(Customer customer, Membership membership);
}
