head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.39.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPSettlementReflectorExcludeExceptSettleBuyAmountTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPSettlementReflectorExcludeExceptSettleBuyAmountTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 金傑（中訊）新規作成
*/
package webbroker3.tradingpower.updtpower.settlement;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPSettlementReflectorExcludeExceptSettleBuyAmountTest  extends TestBaseForMock
{

    private WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount l_buyAmount = null;

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPSettlementReflectorExcludeExceptSettleBuyAmountTest.class);

    public WEB3TPSettlementReflectorExcludeExceptSettleBuyAmountTest(String name)
    {
        super(name);
    }
    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_buyAmount = new WEB3TPSettlementReflectorExcludeExceptSettleBuyAmountForTest();
    }

    protected void tearDown() throws Exception
    {
        this.l_buyAmount = null;
        super.tearDown();
    }
    
    public void testCalcSettlementBuyAmount_C0001()
    {
        final String STR_METHOD_NAME = "testCalcSettlementBuyAmount_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            assertEquals(5682,this.l_buyAmount.calcSettlementBuyAmount(),0);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3TPSettlementReflectorExcludeExceptSettleBuyAmountForTest extends WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount
    {
        public double calcSettlementBuyAmount()
        {
            return 5682;
        }
    }
}
@
