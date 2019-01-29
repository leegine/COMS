head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSwitchOrderRouteServiceImplTest_xiexuan.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.service.delegate.stdimpls;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3ValidFlag;
import webbroker3.dirsec.message.WEB3AdminOrderRouteSwitchingInfo;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSwitchOrderRouteServiceImplTest_xiexuan extends TestBaseForMock
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSwitchOrderRouteServiceImplTest_xiexuan.class);

    public WEB3AdminSwitchOrderRouteServiceImplTest_xiexuan(String arg0)
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
    
    public void testGetValidSwitchOrder_0001()
    {
        final String STR_METHOD_NAME = "testGetValidSwitchOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);
            l_orderSwitchingParams.setValidFlag(WEB3ValidFlag.ON);
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            
            WEB3AdminOrderRouteSwitchingInfo l_info = new WEB3AdminOrderRouteSwitchingInfo();
            l_info.productType = "1";
            l_info.marketCode = "N1";
            l_info.frontOrderSystemCode = "1";
            String l_strInstitutionCode = "0D";
            
            Method l_thisMethod =
                WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                    "getExpirationOrderRoute",
                    new Class[]{String.class, WEB3AdminOrderRouteSwitchingInfo.class});
            l_thisMethod.setAccessible(true);
            WEB3GentradeOrderSwitching l_gentradeOrderSwitching =
                (WEB3GentradeOrderSwitching)l_thisMethod.invoke(new WEB3AdminSwitchOrderRouteServiceImpl(), new Object[]{l_strInstitutionCode, l_info});
            
            assertEquals("N1", ((OrderSwitchingRow)l_gentradeOrderSwitching.getDataSourceObject()).getMarketCode());
            assertEquals("0D", ((OrderSwitchingRow)l_gentradeOrderSwitching.getDataSourceObject()).getInstitutionCode());
            assertEquals("1", ((OrderSwitchingRow)l_gentradeOrderSwitching.getDataSourceObject()).getFrontOrderSystemCode());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

}
@
