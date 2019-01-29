head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.08.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFrontNoticeHistoryServiceImplTest_xiexuan.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFrontNoticeHistoryServiceImplTest_xiexuan extends TestBaseForMock
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontNoticeHistoryServiceImplTest_xiexuan.class);

    public WEB3AdminFrontNoticeHistoryServiceImplTest_xiexuan(String arg0)
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
    
    public void testGetProductTypeList_0001()
    {
        final String STR_METHOD_NAME = "testGetProductTypeList_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setProductType(ProductTypeEnum.IFO);
            l_orderSwitchingParams.setSubmitOrderRouteDiv("2");
            
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            
            WEB3AdminFrontNoticeHistoryServiceImpl l_adminFrontNoticeHistoryServiceImpl =
                new WEB3AdminFrontNoticeHistoryServiceImpl();
            
            String[] l_strProductTypeLists = l_adminFrontNoticeHistoryServiceImpl.getProductTypeList("0D");
            
            String[] l_strExpects = {"6"};
            
            for (int i = 0; i < l_strExpects.length; i++)
            {
                assertEquals(l_strExpects[i], l_strProductTypeLists[i]);
            }
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

}
@
