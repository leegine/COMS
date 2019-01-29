head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.54.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginSwapMarginAcceptServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MarginSwapMarginAcceptServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/08/01 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.equity.data.HostEqtypeSwapAcceptParams;
import webbroker3.equity.data.HostEqtypeSwapAcceptRow;
import webbroker3.equity.message.WEB3MarginSwapMarginAcceptRequest;
import webbroker3.gentrade.data.RequestCodesForReadParams;
import webbroker3.gentrade.data.RequestCodesForReadRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MarginSwapMarginAcceptServiceImplTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginSwapMarginAcceptServiceImplTest.class);

    WEB3MarginSwapMarginAcceptServiceImpl l_serviceImpl= null;

    WEB3MarginSwapMarginAcceptServiceImpl.WEB3MarginSwapMarginAcceptTransactionCallback l_callback= null;
    public WEB3MarginSwapMarginAcceptServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_serviceImpl = new WEB3MarginSwapMarginAcceptServiceImpl();
        l_callback = l_serviceImpl.new WEB3MarginSwapMarginAcceptTransactionCallback();
        TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);
        RequestCodesForReadParams codesForReadParams = TestDBUtility.getRequestCodesForReadRow();
        codesForReadParams.setRequestCode("AI826");
        codesForReadParams.setPtype("margin_swapMarginAccept");
        codesForReadParams.setSubmitOrderRouteDiv("0");
        codesForReadParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        codesForReadParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        TestDBUtility.insertWithDel(codesForReadParams);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //入力參數為空的場合
    //SYSTEM_ERROR_80017
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
            
        try
        {
            WEB3BackRequest l_request = null;
            l_serviceImpl.execute(l_request);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //1.1 validate()
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MarginSwapMarginAcceptRequest l_request = new WEB3MarginSwapMarginAcceptRequest();
            l_serviceImpl.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01291, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //1.5 doTransactionなし
    //DataCallbackException
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MarginSwapMarginAcceptRequest l_request = new WEB3MarginSwapMarginAcceptRequest();
            l_request.orderRequestNumberPrefixGroup = new String[]{"1"};
            l_serviceImpl.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //normalcase
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MarginSwapMarginAcceptRequest l_request = new WEB3MarginSwapMarginAcceptRequest();
            l_request.orderRequestNumberPrefixGroup = new String[]{"1"};

//            HostEqtypeOrderReceiptParams l_hostEqtypeOrderReceiptParams = new HostEqtypeOrderReceiptParams();
//            l_hostEqtypeOrderReceiptParams.setRequestCode("AI826");
//            l_hostEqtypeOrderReceiptParams.setInstitutionCode("0D");
//            l_hostEqtypeOrderReceiptParams.setBranchCode("381");
//            l_hostEqtypeOrderReceiptParams.setAccountCode("2512246");
//            l_hostEqtypeOrderReceiptParams.setTraderCode("1234");
//            l_hostEqtypeOrderReceiptParams.setProductCode("0001000");
//            l_hostEqtypeOrderReceiptParams.setOrderRequestNumber("1");
//            l_hostEqtypeOrderReceiptParams.setTradeType(WEB3SwapTradeTypeDef.DELIVERY);
//            l_hostEqtypeOrderReceiptParams.setSonarRepaymentType("1");
//            l_hostEqtypeOrderReceiptParams.setSonarMarketCode(WEB3MarketCodeSONARDef.TOKYO);
//            l_hostEqtypeOrderReceiptParams.setQuantity(100);
//            l_hostEqtypeOrderReceiptParams.setCapitalGainTaxType(WEB3CapitalGainTaxTypeDef.NOTHING);
//            l_hostEqtypeOrderReceiptParams.setBizDatetime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_hostEqtypeOrderReceiptParams.setDeliveryDate("20070412");
//            l_hostEqtypeOrderReceiptParams.setStatus("0");
//            l_hostEqtypeOrderReceiptParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_hostEqtypeOrderReceiptParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            l_hostEqtypeOrderReceiptParams.setSonarTradedCode("51");
//            TestDBUtility.deleteAll(HostEqtypeOrderReceiptRow.TYPE);        
//            TestDBUtility.insertWithDel(l_hostEqtypeOrderReceiptParams);
            
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);
            RequestCodesForReadParams codesForReadParams = TestDBUtility.getRequestCodesForReadRow();
            codesForReadParams.setRequestCode("AI826");
            codesForReadParams.setPtype("margin_swapMarginAccept");
            codesForReadParams.setSubmitOrderRouteDiv("0");
            codesForReadParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            codesForReadParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(codesForReadParams);

            TestDBUtility.deleteAll(HostEqtypeSwapAcceptRow.TYPE);
            HostEqtypeSwapAcceptParams acceptParams = new HostEqtypeSwapAcceptParams();
            acceptParams.setRequestCode("AI826");
            acceptParams.setStatus("0");
            acceptParams.setOrderRequestNumber("1");
            TestDBUtility.insertWithDel(acceptParams);

            l_serviceImpl.execute(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //処理対象データコードテーブル読込なし
    public void testProcess_C0001()
    {
        final String STR_METHOD_NAME = "testProcess_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);
            RequestCodesForReadParams codesForReadParams = TestDBUtility.getRequestCodesForReadRow();
            codesForReadParams.setPtype("margin");
            TestDBUtility.insertWithDel(codesForReadParams);

            String[] orderRequestNumberPrefixGroup = new String[]{"1"};
            l_callback.setOrderRequestNumberPrefixGroup(orderRequestNumberPrefixGroup);

            l_callback.process();
            fail();
        }
        catch(DataCallbackException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, ((WEB3BaseException)l_ex.getDetails()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    // 取得したキューテーブルのレコード数分、Loop処理 record = 1
    //l_lisSearchResult = l_QueryProcessor.doFindAllQuery(
    //HostEqtypeSwapAcceptRow.TYPE, l_sbWhere.toString(),
    //null, "FOR UPDATE", l_objIfoOrderUnitWhere);
    //l_lisSearchResult = null
    public void testProcess_C0002()
    {
        final String STR_METHOD_NAME = "testProcess_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);
            RequestCodesForReadParams codesForReadParams = TestDBUtility.getRequestCodesForReadRow();
            codesForReadParams.setPtype("margin_swapMarginAccept");
            TestDBUtility.insertWithDel(codesForReadParams);

            String[] orderRequestNumberPrefixGroup = new String[]{"1"};
            l_callback.setOrderRequestNumberPrefixGroup(orderRequestNumberPrefixGroup);

            assertNull(l_callback.process());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //l_lisSearchResult = l_QueryProcessor.doFindAllQuery(
    //HostEqtypeSwapAcceptRow.TYPE, l_sbWhere.toString(),
    //null, "FOR UPDATE", l_objIfoOrderUnitWhere);
    //l_lisSearchResult = not null
    //notify現引現渡受付(現引現渡受付キューParams : 現引現渡受付キューParams)
    public void testProcess_C0003()
    {
        final String STR_METHOD_NAME = "testProcess_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);
            RequestCodesForReadParams codesForReadParams = TestDBUtility.getRequestCodesForReadRow();
            codesForReadParams.setPtype("margin_swapMarginAccept");
            TestDBUtility.insertWithDel(codesForReadParams);

            TestDBUtility.deleteAll(HostEqtypeSwapAcceptRow.TYPE);
            HostEqtypeSwapAcceptParams acceptParams = new HostEqtypeSwapAcceptParams();
            acceptParams.setRequestCode("AI826");
            acceptParams.setStatus("0");
            acceptParams.setOrderRequestNumber("1");
            TestDBUtility.insertWithDel(acceptParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptUnitServiceImpl",
                    "notifySwapMarginAccept",
                    new Class[] {HostEqtypeSwapAcceptParams.class},
                    null);
            
            String[] orderRequestNumberPrefixGroup = new String[]{"1"};
            l_callback.setOrderRequestNumberPrefixGroup(orderRequestNumberPrefixGroup);

            assertNull(l_callback.process());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //l_lisSearchResult = l_QueryProcessor.doFindAllQuery(
    //HostEqtypeSwapAcceptRow.TYPE, l_sbWhere.toString(),
    //null, "FOR UPDATE", l_objIfoOrderUnitWhere);
    //l_lisSearchResult = not null
    //notify現引現渡受付(現引現渡受付キューParams : 現引現渡受付キューParams)なし
    //1.3.6 処理対象キューレコードエラーをupdateする
    public void testProcess_C0004()
    {
        final String STR_METHOD_NAME = "testProcess_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);
            RequestCodesForReadParams codesForReadParams = TestDBUtility.getRequestCodesForReadRow();
            codesForReadParams.setPtype("margin_swapMarginAccept");
            TestDBUtility.insertWithDel(codesForReadParams);

            TestDBUtility.deleteAll(HostEqtypeSwapAcceptRow.TYPE);
            HostEqtypeSwapAcceptParams acceptParams = new HostEqtypeSwapAcceptParams();
            acceptParams.setRequestCode("AI826");
            acceptParams.setStatus("0");
            acceptParams.setOrderRequestNumber("1");
            TestDBUtility.insertWithDel(acceptParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptUnitServiceImpl",
                    "notifySwapMarginAccept",
                    new Class[] {HostEqtypeSwapAcceptParams.class},
                    new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00003, ""));
            
            String[] orderRequestNumberPrefixGroup = new String[]{"1"};
            l_callback.setOrderRequestNumberPrefixGroup(orderRequestNumberPrefixGroup);

            l_callback.process();
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            Object[] l_objBindValues = new Object[]{"AI826"};
            List l_lisSearchResult = l_QueryProcessor.doFindAllQuery(
                HostEqtypeSwapAcceptRow.TYPE, "request_code = ?", l_objBindValues);
            HostEqtypeSwapAcceptRow l_row = (HostEqtypeSwapAcceptRow)l_lisSearchResult.get(0);

            log.debug("更新後Status =========" + l_row.getStatus());

            assertEquals(WEB3StatusDef.DATA_ERROR, l_row.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //l_lisSearchResult = l_QueryProcessor.doFindAllQuery(
    //HostEqtypeSwapAcceptRow.TYPE, l_sbWhere.toString(),
    //null, "FOR UPDATE", l_objIfoOrderUnitWhere);
    //l_lisSearchResult = not null
    //notify現引現渡受付(現引現渡受付キューParams : 現引現渡受付キューParams)なし
    //1.3.6 処理対象キューレコードエラーをupdateする
    //口座ロック失敗：
    public void testProcess_C0005()
    {
        final String STR_METHOD_NAME = "testProcess_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);
            RequestCodesForReadParams codesForReadParams = TestDBUtility.getRequestCodesForReadRow();
            codesForReadParams.setPtype("margin_swapMarginAccept");
            TestDBUtility.insertWithDel(codesForReadParams);

            TestDBUtility.deleteAll(HostEqtypeSwapAcceptRow.TYPE);
            HostEqtypeSwapAcceptParams acceptParams = new HostEqtypeSwapAcceptParams();
            acceptParams.setRequestCode("AI826");
            acceptParams.setStatus("0");
            acceptParams.setOrderRequestNumber("1");
            TestDBUtility.insertWithDel(acceptParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptUnitServiceImpl",
                    "notifySwapMarginAccept",
                    new Class[] {HostEqtypeSwapAcceptParams.class},
                    new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80076, ""));
            
            String[] orderRequestNumberPrefixGroup = new String[]{"1"};
            l_callback.setOrderRequestNumberPrefixGroup(orderRequestNumberPrefixGroup);

            assertNull(l_callback.process());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
