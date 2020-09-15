package com.payment.processing.ruleengine.core;

public class Membership extends Product{

	private Membership m_previousMemberShip;

    public Membership(String sItemInStock, Membership prev) {
        super(sItemInStock);
        m_previousMemberShip = prev;
    }

	public Membership getPreviousLevel() {
		return m_previousMemberShip;
	}

}
