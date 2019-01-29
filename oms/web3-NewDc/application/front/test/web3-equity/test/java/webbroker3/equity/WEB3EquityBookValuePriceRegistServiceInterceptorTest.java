head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.13.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityBookValuePriceRegistServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3EquityBookValuePriceRegistServiceInterceptorTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/07/04 楊夫志 (中訊) 新規作成
*/
package webbroker3.equity;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityBookValuePriceRegistServiceInterceptorTest extends
		TestBaseForMock {


	/**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3EquityBookValuePriceRegistServiceInterceptorTest.class);

    private WEB3EquityBookValuePriceRegistServiceInterceptor serviceInterceptor = null;

	public WEB3EquityBookValuePriceRegistServiceInterceptorTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MOCK_MANAGER.setIsMockUsed(true);
		serviceInterceptor = new WEB3EquityBookValuePriceRegistServiceInterceptor();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	Object[] l_serviceParam = new Object[]{new WEB3EquityAssetInquiryServiceInterceptor()};
	
	//セキュリティサービスを取得失敗
	public void testOnCall_C0001()
	{
		final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(123));
        	
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
            // 設置並獲取表中的數據
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            // 自定義數據設置
            l_mainAccountParams.setAccountId(111);
            l_mainAccountParams.setInstitutionId(111);
            l_mainAccountParams.setInstitutionCode("0A");
            // 向DB中插入數據
            TestDBUtility.insertWithDel(l_mainAccountParams);
        	
        	serviceInterceptor.onCall(null,l_serviceParam);
        	fail();
	    }
        catch(WEB3BaseRuntimeException l_ex)
        {
        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}
	// 取引カレンダコンテキスト.市場コード = null
	public void testOnCall_C0002()
	{
		final String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(123));
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                    "getSystemTimestamp",
                    new Class[]
                    {}, l_tsOrderAcceptTime);
            
            // 清除表中的數據,?次只能清除一個表
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            // 設置並獲取表中的數據
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            // 自定義數據設置
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(111);
            l_mainAccountParams.setInstitutionCode("0A");
            // 向DB中插入數據
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(111);
            l_institutionParams.setInstitutionCode("0A");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(111);
            l_branchParams.setInstitutionCode("0A");
            TestDBUtility.insertWithDel(l_branchParams);

            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "2");
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0A");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context = (WEB3GentradeTradingClendarContext) serviceInterceptor.onCall(null, l_serviceParam);
            assertEquals(null, l_context.getMarketCode());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}
	//取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
	public void testOnCall_C0003()
	{
		final String STR_METHOD_NAME = "testOnCall_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(123));
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                    "getSystemTimestamp",
                    new Class[]
                    {}, l_tsOrderAcceptTime);
            
            // 清除表中的數據,?次只能清除一個表
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            // 設置並獲取表中的數據
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            // 自定義數據設置
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(111);
            l_mainAccountParams.setInstitutionCode("0A");
            // 向DB中插入數據
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(111);
            l_institutionParams.setInstitutionCode("0A");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(111);
            l_branchParams.setInstitutionCode("0A");
            TestDBUtility.insertWithDel(l_branchParams);

            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "2");
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0A");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context = (WEB3GentradeTradingClendarContext) serviceInterceptor.onCall(null, l_serviceParam);
            assertEquals("0A", l_context.getInstitutionCode());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}
	//取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
	public void testOnCall_C0004()
	{
		final String STR_METHOD_NAME = "testOnCall_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(123));
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                    "getSystemTimestamp",
                    new Class[]
                    {}, l_tsOrderAcceptTime);
            
            // 清除表中的數據,?次只能清除一個表
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            // 設置並獲取表中的數據
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            // 自定義數據設置
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(111);
            l_mainAccountParams.setInstitutionCode("0A");
            // 向DB中插入數據
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(111);
            l_institutionParams.setInstitutionCode("0A");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(111);
            l_branchParams.setInstitutionCode("0A");
            TestDBUtility.insertWithDel(l_branchParams);

            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "2");
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0A");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context = (WEB3GentradeTradingClendarContext) serviceInterceptor.onCall(null, l_serviceParam);
            assertEquals("381", l_context.getBranchCode());
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
