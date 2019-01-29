head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.09.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualTradeOrderNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeAccountManagerForMock;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mf.data.HostXbmfOrderNotifyParams;
import webbroker3.mf.data.HostXbmfOrderNotifyRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualTradeOrderNotifyServiceImplTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3MutualTradeOrderNotifyServiceImplTest.class);
    
    
    public WEB3MutualTradeOrderNotifyServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    WEB3MutualTradeOrderNotifyServiceImpl l_mutualTradeOrderNotifyServiceImpl = 
        new WEB3MutualTradeOrderNotifyServiceImpl();
    
    WEB3MutualTradeOrderNotifyServiceImpl.WEB3MutualTradeOrderNotifyTransactionCallback l_callback =
        l_mutualTradeOrderNotifyServiceImpl.new WEB3MutualTradeOrderNotifyTransactionCallback();
    

    public void testProcess_C0001()
    {
        final String STR_METHOD_NAME = "testProcess_C0001()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);

            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualTradeOrderNotifyUnitServiceImpl",
                    "notifyTradeOrderNotify",
                    new Class[] {HostXbmfOrderNotifyParams.class},
                    null);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);

            //1111111
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            //2222222
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_branchParams);

            //33333333
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            
            TestDBUtility.insertWithDel(l_productParams);
            

            //5555555555
            TestDBUtility.deleteAll(HostXbmfOrderNotifyParams.TYPE);
            HostXbmfOrderNotifyParams l_orderNotifyQueueParams = new HostXbmfOrderNotifyParams();
            
            l_orderNotifyQueueParams.setBranchCode("381");
            l_orderNotifyQueueParams.setInstitutionCode("0D");
            l_orderNotifyQueueParams.setOrderRequestNumber("1");
            l_orderNotifyQueueParams.setRequestCode("CI811");
            l_orderNotifyQueueParams.setAccountCode("123456");
            l_orderNotifyQueueParams.setOrderDateDiv("1");
            l_orderNotifyQueueParams.setProductCode("11");
            l_orderNotifyQueueParams.setStatus("0");
            TestDBUtility.insertWithDel(l_orderNotifyQueueParams);
            
            
            
            //66666666666
            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("12");  //-----------
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("06");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("11");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            

            
            l_callback.process();
            
            
            QueryProcessor l_processorObject = Processors.getDefaultProcessor();
            String l_strUpdateWhere =
                " institution_code = ? "+          //更新証券会社コード
                " and branch_code = ? "+           //更新部店コード
                " and order_request_number = ? ";  //更新識別コード
            Object[]  l_Params = { "0D","381","1"};
            
            List l_HostXbmfOrderNotifyRows = 
                l_processorObject.doFindAllQuery(
                    HostXbmfOrderNotifyRow.TYPE,
                    l_strUpdateWhere,
                    l_Params);
            HostXbmfOrderNotifyParams l_HostXbmfOrderNotifyParams = 
                (HostXbmfOrderNotifyParams) l_HostXbmfOrderNotifyRows.get(0);
            assertEquals(l_HostXbmfOrderNotifyRows.size(), 1);
            assertEquals(l_HostXbmfOrderNotifyParams.getStatus(), "1");
            
             
        }
        catch(Exception e)
        {
            log.debug(e.getMessage(),e);
            fail();
            
        }
      

    }
    
    public void testProcess_C0002()
    {
        final String STR_METHOD_NAME = "testProcess_C0002()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);

            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualTradeOrderNotifyUnitServiceImpl",
                    "notifyTradeOrderNotify",
                    new Class[] {HostXbmfOrderNotifyParams.class},
                    null);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);

            //1111111
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            //2222222
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_branchParams);

            //33333333
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            
            TestDBUtility.insertWithDel(l_productParams);
            

            //5555555555
            TestDBUtility.deleteAll(HostXbmfOrderNotifyParams.TYPE);
            HostXbmfOrderNotifyParams l_orderNotifyQueueParams = new HostXbmfOrderNotifyParams();
            
            l_orderNotifyQueueParams.setBranchCode("381");
            l_orderNotifyQueueParams.setInstitutionCode("0D");
            l_orderNotifyQueueParams.setOrderRequestNumber("1");
            l_orderNotifyQueueParams.setRequestCode("CI811");
            l_orderNotifyQueueParams.setAccountCode("123456");
            l_orderNotifyQueueParams.setOrderDateDiv("1");
            l_orderNotifyQueueParams.setProductCode("11");
            l_orderNotifyQueueParams.setStatus("0");
            TestDBUtility.insertWithDel(l_orderNotifyQueueParams);
            
            l_orderNotifyQueueParams.setBranchCode("381");
            l_orderNotifyQueueParams.setInstitutionCode("0D");
            l_orderNotifyQueueParams.setOrderRequestNumber("2");
            TestDBUtility.insertWithDel(l_orderNotifyQueueParams);
            
            
            
            //66666666666
            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("12");  //-----------
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("06");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("11");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            l_callback.process();
            
            
            QueryProcessor l_processorObject = Processors.getDefaultProcessor();
            String l_strUpdateWhere =
                " institution_code = ? "+          //更新証券会社コード
                " and branch_code = ? ";     //更新部店コード

            Object[]  l_Params = { "0D","381"};
            
            List l_HostXbmfOrderNotifyRows = 
                l_processorObject.doFindAllQuery(
                    HostXbmfOrderNotifyRow.TYPE,
                    l_strUpdateWhere,
                    l_Params);
            HostXbmfOrderNotifyParams l_HostXbmfOrderNotifyParams = 
                (HostXbmfOrderNotifyParams) l_HostXbmfOrderNotifyRows.get(0);
            
            assertEquals(l_HostXbmfOrderNotifyRows.size(), 2);
            assertEquals(l_HostXbmfOrderNotifyParams.getStatus(), "1");
            
            HostXbmfOrderNotifyParams l_HostXbmfOrderNotifyParams2 = 
                (HostXbmfOrderNotifyParams) l_HostXbmfOrderNotifyRows.get(1);
            assertEquals(l_HostXbmfOrderNotifyParams2.getStatus(), "1");
            
            
        }
        catch(Exception e)
        {
            log.debug(e.getMessage(),e);
            fail();
            
        }
      

    }

    public void testProcess_C0003()
    {
        final String STR_METHOD_NAME = "testProcess_C0003()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);

            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualTradeOrderNotifyUnitServiceImpl",
                    "notifyTradeOrderNotify",
                    new Class[] {HostXbmfOrderNotifyParams.class},
                    null);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);

            //1111111
            
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_institutionParams);

            //2222222
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_branchParams);

            //33333333
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            
            TestDBUtility.insertWithDel(l_productParams);
            

            //5555555555
            TestDBUtility.deleteAll(HostXbmfOrderNotifyParams.TYPE);
            HostXbmfOrderNotifyParams l_orderNotifyQueueParams = new HostXbmfOrderNotifyParams();
            
            l_orderNotifyQueueParams.setBranchCode("381");
            l_orderNotifyQueueParams.setInstitutionCode("0D");
            l_orderNotifyQueueParams.setOrderRequestNumber("1");
            l_orderNotifyQueueParams.setRequestCode("CI811");
            l_orderNotifyQueueParams.setAccountCode("123456");
            l_orderNotifyQueueParams.setOrderDateDiv("1");
            l_orderNotifyQueueParams.setProductCode("11");
            l_orderNotifyQueueParams.setStatus("0");
            TestDBUtility.insertWithDel(l_orderNotifyQueueParams);
            
            
            
            //66666666666
            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("12");  //-----------
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("06");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("11");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            

            
            l_callback.process();
            
            
            QueryProcessor l_processorObject = Processors.getDefaultProcessor();
            String l_strUpdateWhere =
                " institution_code = ? "+          //更新証券会社コード
                " and branch_code = ? "+           //更新部店コード
                " and order_request_number = ? ";  //更新識別コード
            Object[]  l_Params = { "0D","381","1"};
            
            List l_HostXbmfOrderNotifyRows = 
                l_processorObject.doFindAllQuery(
                    HostXbmfOrderNotifyRow.TYPE,
                    l_strUpdateWhere,
                    l_Params);
            HostXbmfOrderNotifyParams l_HostXbmfOrderNotifyParams = 
                (HostXbmfOrderNotifyParams) l_HostXbmfOrderNotifyRows.get(0);
            assertEquals(l_HostXbmfOrderNotifyRows.size(), 1);
            assertEquals(l_HostXbmfOrderNotifyParams.getStatus(), "9");
            
             
        }
        catch(Exception e)
        {
            log.debug(e.getMessage(),e);
            fail();
            
        }
      

    }
    
}
@
