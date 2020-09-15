package com.payment.processing.rule.engine.payment.handler;

import com.payment.processing.ruleengine.core.AvailableItem;
import com.payment.processing.ruleengine.core.Order;
import com.payment.processing.ruleengine.core.Payment;
import com.payment.processing.ruleengine.core.ProductCategory;
import com.payment.processing.ruleengine.payment.handler.iface.IPaymentHandler;
import com.payment.processing.ruleengine.services.iface.IPackingSlipService;
import com.payment.processing.ruleengine.services.iface.IRoyaltyService;
import com.payment.processing.ruleengine.services.iface.IShippingService;

// If the payment is for a physical product, generate a packing slip for shipping.
// If the payment is for a book, create a duplicate packing slip for the royalty department.
// if the payment is for the video Learning to Ski, add a free First Aid video to the packing slip
public class PackingSlipHandler implements IPaymentHandler{
    private final IPackingSlipService m_PackingSlipService;
    private final IShippingService m_ShippingService;
    private final IRoyaltyService m_RoyaltyService;

    public PackingSlipHandler(final IShippingService shippingService, 
                              final IRoyaltyService royaltyService,
                              final IPackingSlipService packingSlipService){
        m_ShippingService = shippingService;
        m_RoyaltyService = royaltyService;
        m_PackingSlipService = packingSlipService;
    }

    @Override
    public void execute(final Payment payment) {
        final Order order = payment.getOrder();
        final AvailableItem[] availableItems = order.getItemsInStock();

        Boolean generateForShipping = false;
        Boolean generateForRoyalty = false;

        for (final AvailableItem availableItem : availableItems) {
            if(availableItem.hasCategory(ProductCategory.Physical))
                generateForShipping = true;
            if(availableItem.hasCategory(ProductCategory.Books))
                generateForRoyalty = true;

            if(availableItem.getItemInUnit() == "learning-to-ski") 
                order.addGiftByAvailableItemInUnit("first-aid");
        }

        m_PackingSlipService.generate(order);

        if(generateForShipping)
            m_ShippingService.generatePackingSlip(order);
        if(generateForRoyalty)
            m_RoyaltyService.generatePackingSlip(order);
    }
}
