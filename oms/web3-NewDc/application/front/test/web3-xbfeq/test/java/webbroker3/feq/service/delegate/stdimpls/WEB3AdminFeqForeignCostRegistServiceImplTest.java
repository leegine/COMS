head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.18.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqForeignCostRegistServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式現地手数料登録サービスImplTest(WEB3AdminFeqForeignCostRegistServiceImplTest.java)
Revesion History : 2008/11/12 劉仁和 (中訊) 仕様変更No.494を対応
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MarketImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.feq.data.ForeignCostParams;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistInputRequest;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistInputResponse;
import webbroker3.feq.message.WEB3AdminFeqForeignCostUnit;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.gentrade.data.TaxRateTableParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式現地手数料登録サービスImpl)<BR>
 * 外国株式現地手数料登録サービス実装クラス<BR>
 * 
 * @@author 劉仁和(中訊)
 * @@version 1.0
 */
public class WEB3AdminFeqForeignCostRegistServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminFeqForeignCostRegistServiceImplTest.class);

    WEB3AdminFeqForeignCostRegistServiceImpl l_serviceImpl;
    
	public WEB3AdminFeqForeignCostRegistServiceImplTest(String name)
	{
		super(name);
	}

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        l_serviceImpl = new WEB3AdminFeqForeignCostRegistServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     *  has a record
     *  
     * 売買の検索条件を追加(Where： " and side_div = ? ")
     */
    public void testGetInputScreen_Case001()
    {
        final String STR_METHOD_NAME = " testGetInputScreen_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminFeqForeignCostRegistInputRequest l_request
            = new WEB3AdminFeqForeignCostRegistInputRequest();
    	l_request.marketCode = "SP";
    	l_request.costDiv="01";
        l_request.dealingType = "1";
        try 
        {
        	TestDBUtility.deleteAll(ForeignCostParams.TYPE);
        	ForeignCostParams l_foreignCostParams = new ForeignCostParams();
        	l_foreignCostParams.setMarketCode("SP");
        	l_foreignCostParams.setInstitutionCode("0D");
        	l_foreignCostParams.setAmountFrom(20080124);
        	l_foreignCostParams.setAppliStartDate(WEB3DateUtility.getDate("20080124", "yyyyMMdd"));
        	l_foreignCostParams.setAppliEndDate(WEB3DateUtility.getDate("20080124", "yyyyMMdd"));
        	l_foreignCostParams.setAmountFrom(1);
        	l_foreignCostParams.setAmountTo(0);
        	l_foreignCostParams.setScale(1234);
        	l_foreignCostParams.setCostDiv("01");
        	l_foreignCostParams.setSideDiv("1");
        	TestDBUtility.insertWithDel(l_foreignCostParams);
			TestDBUtility.deleteAll(AdministratorParams.TYPE);

			AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
			TestDBUtility.insertWithDel(l_administratorParams);
			
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_administratorSet,
                WEB3TransactionCategoryDef.FEQ_LOCATION_COMMISSION_MANAGE, 
                false,
                true);

			TestDBUtility.deleteAll(ProductParams.TYPE);
			ProductParams l_productParams = TestDBUtility.getProductRow();
			TestDBUtility.insertWithDel(l_productParams);

			TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
			FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams = 
				TestDBUtility.getFeqBranchMarketDealtCondRow();
			TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);

			WEB3AdminFeqForeignCostRegistInputResponse l_response = l_serviceImpl.getInputScreen(l_request);
			
		} 
        catch (WEB3BaseException l_ex) 
        {
			log.error(l_ex.getException().getMessage(), l_ex.getException());
			fail();
		}

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * has no record
     * 
     * 売買の検索条件を追加(add to Where： " and side_div = ? ")
     */
    public void testGetInputScreen_Case002()
    {
        final String STR_METHOD_NAME = " testGetInputScreen_Case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminFeqForeignCostRegistInputRequest l_request
            = new WEB3AdminFeqForeignCostRegistInputRequest();
    	l_request.marketCode = "SP";
    	l_request.costDiv="01";
        l_request.dealingType = "2";

        try 
        {
        	TestDBUtility.deleteAll(ForeignCostParams.TYPE);
        	ForeignCostParams l_foreignCostParams = new ForeignCostParams();
        	l_foreignCostParams.setMarketCode("SP");
        	l_foreignCostParams.setInstitutionCode("0D");
        	l_foreignCostParams.setAmountFrom(20080124);
        	l_foreignCostParams.setAppliStartDate(WEB3DateUtility.getDate("20080124", "yyyyMMdd"));
        	l_foreignCostParams.setAppliEndDate(WEB3DateUtility.getDate("20080124", "yyyyMMdd"));
        	l_foreignCostParams.setAmountFrom(1);
        	l_foreignCostParams.setAmountTo(0);
        	l_foreignCostParams.setScale(1234);
        	l_foreignCostParams.setCostDiv("01");
        	l_foreignCostParams.setSideDiv("1");
        	TestDBUtility.insertWithDel(l_foreignCostParams);
			TestDBUtility.deleteAll(AdministratorParams.TYPE);

			AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
			TestDBUtility.insertWithDel(l_administratorParams);
			
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_administratorSet,
                WEB3TransactionCategoryDef.FEQ_LOCATION_COMMISSION_MANAGE, 
                false,
                true);

			TestDBUtility.deleteAll(ProductParams.TYPE);
			ProductParams l_productParams = TestDBUtility.getProductRow();
			TestDBUtility.insertWithDel(l_productParams);

			TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
			FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams = 
				TestDBUtility.getFeqBranchMarketDealtCondRow();
			TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);

			WEB3AdminFeqForeignCostRegistInputResponse l_response
			    = l_serviceImpl.getInputScreen(l_request);
		} 
        catch (WEB3BaseException l_ex) 
        {
			log.error(l_ex.getException().getMessage(), l_ex.getException());
			fail();
		}

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * delete 1 record
     * 
     * 項目「売買区分」を追加(add to Where： " and side_div = ? ")
     */
    public void testSubmitRegist_Case001()
    {
        final String STR_METHOD_NAME = " testSubmitRegist_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFeqForeignCostRegistCompleteRequest l_request
            = new WEB3AdminFeqForeignCostRegistCompleteRequest();
        
	    l_request.marketCode = "SP";
	    l_request.costDiv="01";
        l_request.dealingType = "1";
        l_request.password = "11111";

    	WEB3AdminFeqForeignCostUnit l_adminFeqForeignCostUnit = new WEB3AdminFeqForeignCostUnit();
    	l_adminFeqForeignCostUnit.additionalAmt = "10";
    	l_adminFeqForeignCostUnit.applyEndDate = WEB3DateUtility.toDay(new Date(2008,05,06));
    	l_adminFeqForeignCostUnit.applyStartDate = WEB3DateUtility.toDay(new Date(2008,01,06));
    	l_adminFeqForeignCostUnit.collectRate = "0.1";
    	l_adminFeqForeignCostUnit.tradingAmtFrom = "10";
    	l_adminFeqForeignCostUnit.tradingAmtTo = "10000000";
    	WEB3AdminFeqForeignCostUnit[] l_adminFeqForeignCostUnits = {l_adminFeqForeignCostUnit};
    	l_request.feqLocalFeeUnit = l_adminFeqForeignCostUnits;
    try 
    {
    	TestDBUtility.deleteAll(ForeignCostParams.TYPE);
    	ForeignCostParams l_foreignCostParams = new ForeignCostParams();
    	l_foreignCostParams.setMarketCode("SP");
    	l_foreignCostParams.setInstitutionCode("0D");
    	l_foreignCostParams.setAmountFrom(20080124);
    	l_foreignCostParams.setAppliStartDate(WEB3DateUtility.getDate("20080124", "yyyyMMdd"));
    	l_foreignCostParams.setAppliEndDate(WEB3DateUtility.getDate("20080124", "yyyyMMdd"));
    	l_foreignCostParams.setAmountFrom(1);
    	l_foreignCostParams.setAmountTo(0);
    	l_foreignCostParams.setScale(1234);
    	l_foreignCostParams.setCostDiv("01");
    	l_foreignCostParams.setSideDiv("1");

		TestDBUtility.deleteAll(AdministratorParams.TYPE);

		AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
		TestDBUtility.insertWithDel(l_administratorParams);
		
        WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
        
        WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
        
        WEB3AdministratorForMock.mockValidateAuthority(
            l_administratorSet,
            WEB3TransactionCategoryDef.FEQ_LOCATION_COMMISSION_MANAGE, 
            true,
            true);
        WEB3AdministratorForMock.mockValidateTradingPassword("11111",true);

		TestDBUtility.deleteAll(ProductParams.TYPE);
		ProductParams l_productParams = TestDBUtility.getProductRow();
		TestDBUtility.insertWithDel(l_productParams);

		TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
		FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams = 
			TestDBUtility.getFeqBranchMarketDealtCondRow();
		TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);
		WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

        TestDBUtility.deleteAll(MarketParams.TYPE);
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        TestDBUtility.insertWithDel(l_marketParams);

		WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
        	"webbroker3.gentrade.WEB3GentradeFinObjectManager",
            "getMarket", 
            new Class[]{ String.class ,String.class }, 
            l_market);

		WEB3AdminFeqForeignCostRegistCompleteResponse l_response
		    = l_serviceImpl.submitRegist(l_request);
	} 
    catch (Exception l_ex) 
    {
		log.error(l_ex.getMessage());
		fail();
	} 

    log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * create海外諸経費マスタ()に、項目「売買区分」を追加
     */
    public void testCreateForeignCostMaster_Case001()
    {
        final String STR_METHOD_NAME = " testCreateForeignCostMaster_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

    	WEB3AdminFeqForeignCostUnit l_adminFeqForeignCostUnit = new WEB3AdminFeqForeignCostUnit();
    	l_adminFeqForeignCostUnit.additionalAmt = "10";
    	l_adminFeqForeignCostUnit.applyEndDate = WEB3DateUtility.toDay(new Date(2008,05,06));
    	l_adminFeqForeignCostUnit.applyStartDate = WEB3DateUtility.toDay(new Date(2008,01,06));
    	l_adminFeqForeignCostUnit.collectRate = "0.1";
    	l_adminFeqForeignCostUnit.tradingAmtFrom = "10";
    	l_adminFeqForeignCostUnit.tradingAmtTo = "10000000";

    	String l_strMarketCode = "SP";
    	String l_strCostDiv = "01";
    	String l_strSideDiv = "1";

		try 
		{
			TestDBUtility.deleteAll(ForeignCostParams.TYPE);
			TestDBUtility.deleteAll(AdministratorParams.TYPE);
			AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
			TestDBUtility.insertWithDel(l_administratorParams);
			WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

			l_serviceImpl.createForeignCostMaster(l_administrator,l_adminFeqForeignCostUnit,l_strMarketCode,
					l_strCostDiv,l_strSideDiv);

		} 
		catch (WEB3BaseException e)
		{
			log.debug("error");
			fail();
		}

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
 
}

@
