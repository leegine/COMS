head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.10.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualSwitchingInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundAsset;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundAssetImpl;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mf.message.WEB3MutualSwitchingInputRequest;
import webbroker3.mf.message.WEB3MutualSwitchingInputResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualSwitchingInputServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingInputServiceImplTest.class);
    
    WEB3MutualSwitchingInputServiceImpl l_impl = new WEB3MutualSwitchingInputServiceImpl();
    
    public WEB3MutualSwitchingInputServiceImplTest(String arg0)
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
    
    public void testExecute()
    {
        final String STR_METHOD_NAME = "testInputProductConditionsRegist01()"; 
        log.entering(STR_METHOD_NAME); 
        
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3MutualSwitchingInputRequest l_request = new WEB3MutualSwitchingInputRequest();
        l_request.id = "01";
        l_request.switchingProductId = "3304148080000";
        
        try
        {
            //mainAcc
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccRow = TestDBUtility.getMainAccountRow();
            l_mainAccRow.setYellowCustomer("1");            
            TestDBUtility.insertWithDel(l_mainAccRow);
            
            //subAcc
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAcc = TestDBUtility.getSubAccountRow();
            l_subAcc.setAccountId(333812512246L);
            l_subAcc.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAcc);
            
            //MutualFundProductParams
            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
            l_mfProductParams.setProductId(3304148080000L);
            l_mfProductParams.setInstitutionCode("0D");
            l_mfProductParams.setProductCode("0001000");
            l_mfProductParams.setProductIssueCode("0");
            l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mfProductParams.setCurrencyCode("0");
            TestDBUtility.insertWithDel(l_mfProductParams);
            
            //TradingTimeParams
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setProductCode("0001000");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setEnableOrder("1");
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            //TradingTimeParams4
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("06");
            l_tradingTimeParams4.setProductCode("0001000");
            l_tradingTimeParams4.setBizDateType("1");
            l_tradingTimeParams4.setSubmitMarketTrigger("0");
            l_tradingTimeParams4.setEnableOrder("1");
            l_tradingTimeParams4.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            
            //TradingTimeParams3
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("06");
            l_tradingTimeParams3.setProductCode("0001000");
            l_tradingTimeParams3.setBizDateType("2");
            l_tradingTimeParams3.setSubmitMarketTrigger("0");
            l_tradingTimeParams3.setEnableOrder("1");
            l_tradingTimeParams3.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            //TradingTimeParams2
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            Long l_lngMainAcc = new Long(333812512246L); 
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                l_lngMainAcc);
            
            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_assetParams);
            MutualFundAsset l_mfAssert = new MutualFundAssetImpl(l_assetParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "getAsset",
                new Class[] {long.class},
                l_mfAssert);
            
            
            WEB3MutualSwitchingInputResponse l_response = 
                (WEB3MutualSwitchingInputResponse)l_impl.execute(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            l_ex.printStackTrace();
            assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275.error_code, 
                l_ex.getErrorInfo().getErrorCode());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            l_ex.printStackTrace();
            fail();
        }
      
    }

}
@
