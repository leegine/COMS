head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.18.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqOrderCarryOverServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqOrderCarryOverServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderCarryOverServiceImplTest.class);

    public WEB3FeqOrderCarryOverServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testUpdateOrderCarryOverStatus()
    {
        final String STR_METHOD_NAME = "testUpdateOrderCarryOverStatus()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqOrderCarryOverServiceImplTestForMock l_implForMock =
                new WEB3FeqOrderCarryOverServiceImplTestForMock();

            //OrderexecutionEndParams  1111111111
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            OrderexecutionEndParams l_endParams = TestDBUtility.getOrderexecutionEndRow();
            l_endParams.setInstitutionCode("0D");
            l_endParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_endParams.setFutureOptionDiv("0");
            l_endParams.setOrderexecutionEndType("0");
            l_endParams.setCarryoverEndType("1");
            TestDBUtility.insertWithDel(l_endParams);

            //22222222222
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_endParams.setOrderexecutionEndType("1");
            l_queryProcessor.doInsertQuery(l_endParams);
            
            l_implForMock.updateOrderCarryOverStatus(
                "0D",
                "4");
            List l_result = l_queryProcessor.doFindAllQuery(
                OrderexecutionEndRow.TYPE,
                "orderexecution_end_type = ? and carryover_end_type = ?",
                new Object[]{"0", "4"});
            assertEquals(1, l_result.size());
            log.info(STR_METHOD_NAME + "------------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public class WEB3FeqOrderCarryOverServiceImplTestForMock extends WEB3FeqOrderCarryOverServiceImpl
    {
        
    }
}
@
