head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.12.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginSwapMarginUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MarginSwapMarginUpdateInterceptorTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/07/04 楊夫志 (中訊) 新規作成
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MarginSwapMarginUpdateInterceptorTest extends TestBaseForMock {

	/**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3MarginSwapMarginUpdateInterceptorTest.class);
	
	public WEB3MarginSwapMarginUpdateInterceptorTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MOCK_MANAGER.setIsMockUsed(true);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//信用現引現渡注文内容 
	public void testMutate_C0001() 
	{
		final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	WEB3MarginSwapMarginUpdateInterceptor l_interceptor = 
        		new WEB3MarginSwapMarginUpdateInterceptor(null, null, 0, null,0,0,0,null,null);
        	EqtypeOrderUnitParams l_orderUnitParams = null;
        	assertNull(l_interceptor.mutate(null,null,l_orderUnitParams));
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//WEB3ErrorCatalog.SYSTEM_ERROR_80005
	public void testMutate_C0002() 
	{
		final String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	Trader l_trader = null;
        	EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry= null;
        	TaxTypeEnum l_taxType = null;
        	WEB3MarginSwapContractOrderSpec l_equitySwapMarginOrderSpec = 
        		 new WEB3MarginSwapContractOrderSpec(l_trader,l_closeMarginContractEntry,l_taxType);
        	String l_strOrderRootDiv = "01";
        	WEB3MarginSwapMarginUpdateInterceptor l_interceptor = 
        		new WEB3MarginSwapMarginUpdateInterceptor(l_equitySwapMarginOrderSpec, null, 0, null,0,0,0,null,l_strOrderRootDiv);
        	
        	 TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
             EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
             l_eqtypeOrderUnitParams.setAccountId(0001);
             TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

             TestDBUtility.deleteAll(MainAccountRow.TYPE);
             MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
             l_accountParams.setAccountId(0002);
             TestDBUtility.insertWithDel(l_accountParams);
             
             l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams);
             fail();
	    }
        catch(WEB3BaseRuntimeException l_ex)
        {
        	assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//WEB3ErrorCatalog.SYSTEM_ERROR_80005
	public void testMutate_C0003() 
	{
		final String STR_METHOD_NAME = "testOnCall_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	Trader l_trader = null;
        	EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry= null;
        	TaxTypeEnum l_taxType = null;
        	WEB3MarginSwapContractOrderSpec l_equitySwapMarginOrderSpec = 
        		 new WEB3MarginSwapContractOrderSpec(l_trader,l_closeMarginContractEntry,l_taxType);
        	String l_strOrderRootDiv = "01";
        	WEB3MarginSwapMarginUpdateInterceptor l_interceptor = 
        		new WEB3MarginSwapMarginUpdateInterceptor(l_equitySwapMarginOrderSpec, null, 0, null,0,0,0,null,l_strOrderRootDiv);
        	
        	 TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
             EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
             l_eqtypeOrderUnitParams.setAccountId(0001);
             l_eqtypeOrderUnitParams.setMarketId(001);
             TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

             TestDBUtility.deleteAll(MainAccountRow.TYPE);
             MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
             l_accountParams.setAccountId(0001);
             TestDBUtility.insertWithDel(l_accountParams);
             
             l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams);
             fail();
	    }
        catch(WEB3BaseRuntimeException l_ex)
        {
        	assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//受渡日=null && 受注日時=null
	public void testMutate_C0004() 
	{
		final String STR_METHOD_NAME = "testOnCall_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	Trader l_trader = null;
        	EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry= null;
        	TaxTypeEnum l_taxType = null;
        	WEB3MarginSwapContractOrderSpec l_equitySwapMarginOrderSpec = 
        		 new WEB3MarginSwapContractOrderSpec(l_trader,l_closeMarginContractEntry,l_taxType);
        	String l_strOrderRootDiv = "01";
        	WEB3MarginSwapMarginUpdateInterceptor l_interceptor = 
        		new WEB3MarginSwapMarginUpdateInterceptor(l_equitySwapMarginOrderSpec, null, 0, null,0,0,0,null,l_strOrderRootDiv);
        	
        	 TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
             EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
             l_eqtypeOrderUnitParams.setAccountId(0001);
             l_eqtypeOrderUnitParams.setMarketId(001);
             TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

             TestDBUtility.deleteAll(MainAccountRow.TYPE);
             MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
             l_accountParams.setAccountId(0001);
             l_accountParams.setBranchId(0001);
             l_accountParams.setInstitutionId(0001);
             TestDBUtility.insertWithDel(l_accountParams);
             
             TestDBUtility.deleteAll(BranchRow.TYPE);
             BranchParams l_branchParams = TestDBUtility.getBranchRow();
             l_branchParams.setBranchId(0001);
             l_branchParams.setBranchCode("001");
             TestDBUtility.insertWithDel(l_branchParams);
             
             TestDBUtility.deleteAll(InstitutionRow.TYPE);
             InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
             l_institutionParams.setInstitutionId(0001);
             l_institutionParams.setInstitutionCode("001");
             TestDBUtility.insertWithDel(l_institutionParams);
             
             TestDBUtility.deleteAll(MarketRow.TYPE);
             MarketParams l_params = TestDBUtility.getMarketRow();
             l_params.setMarketId(001);
             TestDBUtility.insertWithDel(l_params);
             
             assertNull("is null?",l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getConfirmedPriceConditionType());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//受渡日!=null
	public void testMutate_C0005() 
	{
		final String STR_METHOD_NAME = "testOnCall_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	Trader l_trader = null;
        	EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry= null;
        	TaxTypeEnum l_taxType = null;
        	WEB3MarginSwapContractOrderSpec l_equitySwapMarginOrderSpec = 
        		 new WEB3MarginSwapContractOrderSpec(l_trader,l_closeMarginContractEntry,l_taxType);
        	String l_strOrderRootDiv = "01";
        	WEB3MarginSwapMarginUpdateInterceptor l_interceptor = 
        		new WEB3MarginSwapMarginUpdateInterceptor(l_equitySwapMarginOrderSpec, null, 0, null,0,0,0,null,l_strOrderRootDiv);
        	l_interceptor.setDeliveryDate(WEB3DateUtility.getDate("20080707","yyyyMMdd"));
        	
        	 TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
             EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
             l_eqtypeOrderUnitParams.setAccountId(0001);
             l_eqtypeOrderUnitParams.setMarketId(001);
             TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

             TestDBUtility.deleteAll(MainAccountRow.TYPE);
             MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
             l_accountParams.setAccountId(0001);
             l_accountParams.setBranchId(0001);
             l_accountParams.setInstitutionId(0001);
             TestDBUtility.insertWithDel(l_accountParams);
             
             TestDBUtility.deleteAll(BranchRow.TYPE);
             BranchParams l_branchParams = TestDBUtility.getBranchRow();
             l_branchParams.setBranchId(0001);
             l_branchParams.setBranchCode("001");
             TestDBUtility.insertWithDel(l_branchParams);
             
             TestDBUtility.deleteAll(InstitutionRow.TYPE);
             InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
             l_institutionParams.setInstitutionId(0001);
             l_institutionParams.setInstitutionCode("001");
             TestDBUtility.insertWithDel(l_institutionParams);
             
             TestDBUtility.deleteAll(MarketRow.TYPE);
             MarketParams l_params = TestDBUtility.getMarketRow();
             l_params.setMarketId(001);
             TestDBUtility.insertWithDel(l_params);
             
             assertEquals(WEB3DateUtility.getDate("20080707","yyyyMMdd"),l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getDeliveryDate());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//受注日時!=null
	public void testMutate_C0006() 
	{
		final String STR_METHOD_NAME = "testOnCall_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	Trader l_trader = null;
        	EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry= null;
        	TaxTypeEnum l_taxType = null;
        	WEB3MarginSwapContractOrderSpec l_equitySwapMarginOrderSpec = 
        		 new WEB3MarginSwapContractOrderSpec(l_trader,l_closeMarginContractEntry,l_taxType);
        	String l_strOrderRootDiv = "01";
        	WEB3MarginSwapMarginUpdateInterceptor l_interceptor = 
        		new WEB3MarginSwapMarginUpdateInterceptor(l_equitySwapMarginOrderSpec, null, 0, null,0,0,0,null,l_strOrderRootDiv);
        	l_interceptor.setDeliveryDate(WEB3DateUtility.getDate("20080707","yyyyMMdd"));
        	l_interceptor.setReceivedDateTime(WEB3DateUtility.getDate("20080707","yyyyMMdd"));
        	
        	 TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
             EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
             l_eqtypeOrderUnitParams.setAccountId(0001);
             l_eqtypeOrderUnitParams.setMarketId(001);
             TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

             TestDBUtility.deleteAll(MainAccountRow.TYPE);
             MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
             l_accountParams.setAccountId(0001);
             l_accountParams.setBranchId(0001);
             l_accountParams.setInstitutionId(0001);
             TestDBUtility.insertWithDel(l_accountParams);
             
             TestDBUtility.deleteAll(BranchRow.TYPE);
             BranchParams l_branchParams = TestDBUtility.getBranchRow();
             l_branchParams.setBranchId(0001);
             l_branchParams.setBranchCode("001");
             TestDBUtility.insertWithDel(l_branchParams);
             
             TestDBUtility.deleteAll(InstitutionRow.TYPE);
             InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
             l_institutionParams.setInstitutionId(0001);
             l_institutionParams.setInstitutionCode("001");
             TestDBUtility.insertWithDel(l_institutionParams);
             
             TestDBUtility.deleteAll(MarketRow.TYPE);
             MarketParams l_params = TestDBUtility.getMarketRow();
             l_params.setMarketId(001);
             TestDBUtility.insertWithDel(l_params);
             
             assertEquals(WEB3DateUtility.getDate("20080707","yyyyMMdd"),l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getReceivedDateTime());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//発注日
	//（this．注文経路区分＝”HOST”の場合のみ）YYYYMMDD形式に変換してセットする
	public void testMutate_C0007() 
	{
		final String STR_METHOD_NAME = "testOnCall_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	Trader l_trader = null;
        	EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry= null;
        	TaxTypeEnum l_taxType = null;
        	WEB3MarginSwapContractOrderSpec l_equitySwapMarginOrderSpec = 
        		 new WEB3MarginSwapContractOrderSpec(l_trader,l_closeMarginContractEntry,l_taxType);
        	String l_strOrderRootDiv = "9";
        	WEB3MarginSwapMarginUpdateInterceptor l_interceptor = 
        		new WEB3MarginSwapMarginUpdateInterceptor(l_equitySwapMarginOrderSpec, null, 0, null,0,0,0,null,l_strOrderRootDiv);
        	l_interceptor.setDeliveryDate(WEB3DateUtility.getDate("20080707","yyyyMMdd"));
        	l_interceptor.setReceivedDateTime(WEB3DateUtility.getDate("20080707","yyyyMMdd"));
        	l_interceptor.setOrderRequestNumber("1111");
        	
        	 TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
             EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
             l_eqtypeOrderUnitParams.setAccountId(0001);
             l_eqtypeOrderUnitParams.setMarketId(001);
             TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

             TestDBUtility.deleteAll(MainAccountRow.TYPE);
             MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
             l_accountParams.setAccountId(0001);
             l_accountParams.setBranchId(0001);
             l_accountParams.setInstitutionId(0001);
             TestDBUtility.insertWithDel(l_accountParams);
             
             TestDBUtility.deleteAll(BranchRow.TYPE);
             BranchParams l_branchParams = TestDBUtility.getBranchRow();
             l_branchParams.setBranchId(0001);
             l_branchParams.setBranchCode("001");
             TestDBUtility.insertWithDel(l_branchParams);
             
             TestDBUtility.deleteAll(InstitutionRow.TYPE);
             InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
             l_institutionParams.setInstitutionId(0001);
             l_institutionParams.setInstitutionCode("001");
             TestDBUtility.insertWithDel(l_institutionParams);
             
             TestDBUtility.deleteAll(MarketRow.TYPE);
             MarketParams l_params = TestDBUtility.getMarketRow();
             l_params.setMarketId(001);
             TestDBUtility.insertWithDel(l_params);
             
             assertEquals("1111",l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getOrderRequestNumber());
             assertEquals(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(GtlUtils.getSystemTimestamp()),
        		 l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getBizDate());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//発注日 this．注文経路区分!＝”HOST”の場合
	public void testMutate_C0008() 
	{
		final String STR_METHOD_NAME = "testOnCall_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	Trader l_trader = null;
        	EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry= null;
        	TaxTypeEnum l_taxType = null;
        	WEB3MarginSwapContractOrderSpec l_equitySwapMarginOrderSpec = 
        		 new WEB3MarginSwapContractOrderSpec(l_trader,l_closeMarginContractEntry,l_taxType);
        	String l_strOrderRootDiv = "7";
        	WEB3MarginSwapMarginUpdateInterceptor l_interceptor = 
        		new WEB3MarginSwapMarginUpdateInterceptor(l_equitySwapMarginOrderSpec, null, 0, null,0,0,0,null,l_strOrderRootDiv);
        	l_interceptor.setDeliveryDate(WEB3DateUtility.getDate("20080707","yyyyMMdd"));
        	l_interceptor.setReceivedDateTime(WEB3DateUtility.getDate("20080707","yyyyMMdd"));
        	
        	 TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
             EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
             l_eqtypeOrderUnitParams.setAccountId(0001);
             l_eqtypeOrderUnitParams.setMarketId(001);
             TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

             TestDBUtility.deleteAll(MainAccountRow.TYPE);
             MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
             l_accountParams.setAccountId(0001);
             l_accountParams.setBranchId(0001);
             l_accountParams.setInstitutionId(0001);
             TestDBUtility.insertWithDel(l_accountParams);
             
             TestDBUtility.deleteAll(BranchRow.TYPE);
             BranchParams l_branchParams = TestDBUtility.getBranchRow();
             l_branchParams.setBranchId(0001);
             l_branchParams.setBranchCode("001");
             TestDBUtility.insertWithDel(l_branchParams);
             
             TestDBUtility.deleteAll(InstitutionRow.TYPE);
             InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
             l_institutionParams.setInstitutionId(0001);
             l_institutionParams.setInstitutionCode("001");
             TestDBUtility.insertWithDel(l_institutionParams);
             
             TestDBUtility.deleteAll(MarketRow.TYPE);
             MarketParams l_params = TestDBUtility.getMarketRow();
             l_params.setMarketId(001);
             TestDBUtility.insertWithDel(l_params);
	  
             WEB3HostReqOrderNumberManageService l_numberService =
                 (WEB3HostReqOrderNumberManageService) Services.getService(
                 WEB3HostReqOrderNumberManageService.class);
             
        	assertEquals(Integer.parseInt(l_numberService.getNewNumber("001","001",ProductTypeEnum.EQUITY))+1,
        			Integer.parseInt(l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getOrderRequestNumber()));
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
