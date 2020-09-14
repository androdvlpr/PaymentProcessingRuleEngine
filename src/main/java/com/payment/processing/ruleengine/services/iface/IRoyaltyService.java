package com.payment.processing.ruleengine.services.iface;

import com.payment.processing.ruleengine.core.Order;
import com.payment.processing.ruleengine.core.PackingSlip;

public interface IRoyaltyService{
    PackingSlip generatePackingSlip(Order order);
}