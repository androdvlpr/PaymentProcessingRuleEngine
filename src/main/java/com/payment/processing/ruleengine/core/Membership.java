package com.payment.processing.ruleengine.core;

public class Membership extends Product{

	private Membership previousMemberShip;

    public Membership(String sStockKeepingUnit, Membership prev) {
        super(sStockKeepingUnit);
        previousMemberShip = prev;
    }

	public Membership getPreviousLevel() {
		return previousMemberShip;
	}

}
