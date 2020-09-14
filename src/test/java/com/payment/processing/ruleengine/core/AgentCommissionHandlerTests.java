package com.payment.processing.ruleengine.core;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.payment.processing.rule.engine.payment.handler.AgentCommissionHandler;
import com.payment.processing.ruleengine.payment.handler.iface.IPaymentHandler;



public class AgentCommissionHandlerTests{
    @Test
    public void runShouldNotGenerateAgentCommissionIfItemsInvalid(){
        LineItem[] lineItems = new LineItem[]{
            new LineItem("item", "item", new ProductCategory[]{
                ProductCategory.Membership,
            })
        };
        Customer customer = mock(Customer.class);
        Agent agent = mock(Agent.class);
        Order order = new Order(customer, lineItems, agent);
        Payment payment = new Payment(order);

        IPaymentHandler paymentHandler = new AgentCommissionHandler();
        paymentHandler.execute(payment);

        verify(agent, never()).generateCommission(any());
    }

    @Test
    public void runShouldGenerateAgentCommissionIfBookInOrder(){
        LineItem[] lineItems = new LineItem[]{
            new LineItem("item", "item", new ProductCategory[]{
                ProductCategory.Membership
            }),
            new LineItem("book1", "book1", new ProductCategory[]{
                ProductCategory.Books
            })
        };
        Customer customer = mock(Customer.class);
        Agent agent = mock(Agent.class);
        Order order = new Order(customer, lineItems, agent);
        Payment payment = new Payment(order);

        IPaymentHandler paymentHandler = new AgentCommissionHandler();
        paymentHandler.execute(payment);

        verify(agent, times(1)).generateCommission(any());
    }

    @Test
    public void runShouldGenerateAgentCommissionOnceIfMultipleBooksInOrder(){
        LineItem[] lineItems = new LineItem[]{
            new LineItem("book1", "book1", new ProductCategory[]{
                ProductCategory.Books
            }),
            new LineItem("book2", "book2", new ProductCategory[]{
                ProductCategory.Books
            }),
            new LineItem("book3", "book3", new ProductCategory[]{
                ProductCategory.Books
            })
        };
        Customer customer = mock(Customer.class);
        Agent agent = mock(Agent.class);
        Order order = new Order(customer, lineItems, agent);
        Payment payment = new Payment(order);

        IPaymentHandler paymentHandler = new AgentCommissionHandler();
        paymentHandler.execute(payment);

        verify(agent, times(1)).generateCommission(any());
    }

    @Test
    public void runShouldGenerateAgentCommissionIfPhysicalItemInOrder(){
        LineItem[] lineItems = new LineItem[]{
            new LineItem("item", "item", new ProductCategory[]{
                ProductCategory.Physical
            }),
            new LineItem("membership", "membership", new ProductCategory[]{
                ProductCategory.Membership
            })
        };
        Customer customer = mock(Customer.class);
        Agent agent = mock(Agent.class);
        Order order = new Order(customer, lineItems, agent);
        Payment payment = new Payment(order);

        IPaymentHandler paymentHandler = new AgentCommissionHandler();
        paymentHandler.execute(payment);

        verify(agent, times(1)).generateCommission(any());
    }
}