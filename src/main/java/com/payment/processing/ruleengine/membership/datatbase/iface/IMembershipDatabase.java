package com.payment.processing.ruleengine.membership.datatbase.iface;

import com.payment.processing.ruleengine.core.Membership;

public interface IMembershipDatabase{
	Membership findByAvailableUnitInStock(String sAvailableUnitInStock);
}