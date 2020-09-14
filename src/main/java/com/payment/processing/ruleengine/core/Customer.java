package com.payment.processing.ruleengine.core;

import com.payment.processing.ruleengine.services.iface.INotificationService;

//empty class, behaviour is mocked in the tests 
public class Customer {

	public void addMembership(Membership membership, INotificationService notificationService) {
		// notifications are event driven,
		// this is for testing purpose.
		notificationService.notify(this, membership);
	}

	public boolean hasMembership(Membership membership) {
		return false;
	}
}
