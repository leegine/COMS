head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.03.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSChangeOrderRequestAdapterTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)現物株式注文訂正リクエストアダプタ(WEB3EquityPTSChangeOrderRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 張騰宇 (中訊) 新規作成 モデル1241 1250
*/
package webbroker3.equity.service.delegate;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSChangeOrderRequestAdapterTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSChangeOrderRequestAdapterTest.class);

    public WEB3EquityPTSChangeOrderRequestAdapterTest(String arg0)
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

    /*
     * Test method for 'webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderRequestAdapter.create(WEB3GenRequest)'
     */
    public void testCreateCase1()
    {
        final String STR_METHOD_NAME = "testCreateCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
            l_request.id = "10";
            WEB3EquityChangeOrderRequestAdapter l_adapter =
                WEB3EquityPTSChangeOrderRequestAdapter.create(l_request);
            
            assertEquals("10", l_adapter.getRequestOrderId() + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateCase2()
    {
        final String STR_METHOD_NAME = "testCreateCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.id = "10";
            l_request.checkPrice = "100";
            WEB3EquityChangeOrderRequestAdapter l_adapter =
                WEB3EquityPTSChangeOrderRequestAdapter.create(l_request);
            assertEquals("10", l_adapter.getRequestOrderId() + "");
            assertEquals("100", ((WEB3EquityChangeCompleteRequest)l_adapter.requestData).checkPrice);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderRequestAdapter.getExpirationDate()'
     */
    public void testGetExpirationDateCase1()
    {
        final String STR_METHOD_NAME = "testGetExpirationDateCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,12);// 注意月?要減1
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc." + 
                    "gentrade.stdimpls.TradingSystemImpl", 
                "getSystemTimestamp",
                new Class[]{}, 
                st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",st);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3EquityPTSChangeOrderRequestAdapter l_adapter = new WEB3EquityPTSChangeOrderRequestAdapter();
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.id = "10";
            l_request.checkPrice = "100";
            Date l_datDate = l_adapter.getExpirationDate();
            log.debug("訂正後注文失効日 "+l_datDate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetExpirationDateCase2()
    {
        final String STR_METHOD_NAME = "testGetExpirationDateCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3EquityPTSChangeOrderRequestAdapter l_adapter = new WEB3EquityPTSChangeOrderRequestAdapter();
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.id = "10";
            l_request.checkPrice = "100";
            l_adapter.getExpirationDate();
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80006);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
