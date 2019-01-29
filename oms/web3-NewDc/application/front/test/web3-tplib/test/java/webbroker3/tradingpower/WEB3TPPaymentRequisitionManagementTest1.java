head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.49.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPPaymentRequisitionManagementTest1.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower;

import webbroker3.mock.TestBaseForMock;

public class WEB3TPPaymentRequisitionManagementTest1 extends TestBaseForMock
{

    public WEB3TPPaymentRequisitionManagementTest1(String name)
    {
        super(name);
    }

    public void test_001()
    {
        
    }
    
    class WEB3TPPaymentRequisitionManagement111 extends WEB3TPPaymentRequisitionManagement
    {
        protected double calcAccountBalanceShortfallEquity(int l_intSpecifiedPoint)
        {
            return 0;
        }
    }
}
@
