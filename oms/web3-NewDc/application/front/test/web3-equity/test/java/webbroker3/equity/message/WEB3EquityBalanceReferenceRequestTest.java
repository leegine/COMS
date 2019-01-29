head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.50.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityBalanceReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部 
File Name        : WEB3EquityBalanceReferenceRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/27 楊夫志 (中訊) 新規作成  
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityBalanceReferenceRequestTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityBalanceReferenceRequestTest.class);

    WEB3EquityBalanceReferenceRequest equityBalanceReferenceRequest = null;

    public WEB3EquityBalanceReferenceRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.equityBalanceReferenceRequest = new WEB3EquityBalanceReferenceRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //市場コードはチェックしない。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            equityBalanceReferenceRequest.productCode = null;
            equityBalanceReferenceRequest.marketCode = "N1";
            equityBalanceReferenceRequest.taxTypeList = null;
            equityBalanceReferenceRequest.sortKeys = new WEB3EquitySortKey[]{new WEB3EquitySortKey(),new WEB3EquitySortKey()};
            int sortKeysCount = equityBalanceReferenceRequest.sortKeys.length;
            for(int i = 0 ; i < sortKeysCount ; i++)
            {
               equityBalanceReferenceRequest.sortKeys[i].keyItem = "productCode";
               equityBalanceReferenceRequest.sortKeys[i].ascDesc = "A";
            }
            equityBalanceReferenceRequest.pageIndex = "0001";
            equityBalanceReferenceRequest.pageSize = "100";
            equityBalanceReferenceRequest.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
