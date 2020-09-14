package com.payment.processing.ruleengine.payment.handler.iface;

import com.payment.processing.ruleengine.core.Payment;

public interface IPaymentHandler {
    void execute(final Payment payment);
}
