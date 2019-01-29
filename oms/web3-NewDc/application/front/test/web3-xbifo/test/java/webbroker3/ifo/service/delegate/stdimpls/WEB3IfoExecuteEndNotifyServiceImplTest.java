head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoExecuteEndNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP出来終了通知サービス実装Test(WEB3IfoExecuteEndNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/15 趙林鵬(中訊) 新規作成
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.HostOrderexecutionEndParams;
import webbroker3.gentrade.data.HostOrderexecutionEndRow;
import webbroker3.gentrade.data.OnlineRunStatusParams;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.ifo.data.IfoOrderExecSendMailRow;
import webbroker3.ifo.message.WEB3IfoExecEndNotifyRequest;
import webbroker3.ifo.message.WEB3IfoExecEndNotifyResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoExecuteEndNotifyServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3IfoExecuteEndNotifyServiceImplTest.class);
    
    WEB3IfoExecuteEndNotifyServiceImpl l_impl = new WEB3IfoExecuteEndNotifyServiceImpl();

    public WEB3IfoExecuteEndNotifyServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAllAndCommit(OnlineRunStatusParams.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.checkMethodValue();
        super.tearDown();
    }

    /**
     * 指定AP起動中（二重起動エラー）。
     */
    public void test_execute_0001()
    {
        final String STR_METHOD_NAME = "test_execute_0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoExecEndNotifyRequest l_ifoExecEndNotifyRequest =
            new WEB3IfoExecEndNotifyRequest();
        l_ifoExecEndNotifyRequest.institutionCode = "0D";
        l_ifoExecEndNotifyRequest.fuOpDiv = "2";
        l_ifoExecEndNotifyRequest.execEndDiv = "1";
        l_ifoExecEndNotifyRequest.rangeFrom = 1;
        l_ifoExecEndNotifyRequest.rangeTo = 5;

        try
        {
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            
            IfoProductParams l_IfoProductParams = TestDBUtility.getIfoProductRow();
            l_IfoProductParams.setFutureOptionDiv("2");
            l_IfoProductParams.setProductId(1006169090018L);
            l_IfoProductParams.setProductCode("160030005");
            l_IfoProductParams.setInstitutionCode("0D");
            TestDBUtility.deleteAllAndCommit(IfoProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_IfoProductParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(4);
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4);
            l_mainAccountParams.setBranchCode("381");
            
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            HostOrderexecutionEndParams l_hostOrderexecutionEndParams =
                this.getHostOrderexecutionEndRow();
            l_hostOrderexecutionEndParams.setInstitutionCode("0D");
            TestDBUtility.deleteAllAndCommit(HostOrderexecutionEndRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostOrderexecutionEndParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("3B");
            l_branchParams.setInstitutionId(33);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setAccountId(4);
            l_ifoOrderUnitParams.setRequestType("0");
            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
            
            
            l_ifoOrderUnitParams.setRequestType("3");
            l_ifoOrderUnitParams.setFirstOrderUnitId(1);
            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAllAndCommit(IfoOrderExecSendMailRow.TYPE);
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            
            TestDBUtility.insertWithDelAndCommit(l_enableOrderConditionParams);
            
            IfoTradedProductParams l_IfoTradedProductParams = new IfoTradedProductParams();
            l_IfoTradedProductParams.setTradedProductId(1006160060005L);
//            l_IfoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
            l_IfoTradedProductParams.setInstitutionCode("0D");
            l_IfoTradedProductParams.setMarketId(1002);
            l_IfoTradedProductParams.setProductId(1006169090018L);
            l_IfoTradedProductParams.setUnitSize(10000L);
            l_IfoTradedProductParams.setUnitMargin(0L);
            l_IfoTradedProductParams.setPerOrderMaxUnits(1000L);
            l_IfoTradedProductParams.setOrderCloseTime("");
            l_IfoTradedProductParams.setLastClosingPrice(8D);
            l_IfoTradedProductParams.setStartTradingDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
            l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20040718","yyyyMMdd")); 
            l_IfoTradedProductParams.setTradeStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setListFlag(BooleanEnum.TRUE);
            l_IfoTradedProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_IfoTradedProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.deleteAllAndCommit(IfoTradedProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(OrderexecutionEndRow.TYPE);
            
            OnlineRunStatusParams l_onlineRunStatusParams = TestDBUtility.getOnlineRunStatusRow();
            l_onlineRunStatusParams.setOnlineServiceDiv("9");
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams);
            WEB3IfoExecEndNotifyResponse l_response =
                (WEB3IfoExecEndNotifyResponse)l_ifoExecEndNotifyRequest.createResponse();
            assertEquals(l_response.getClass(),l_impl.execute(l_ifoExecEndNotifyRequest).getClass());
  
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    /**
     * "1：出来終了通知"
     * 1：処理済
     */
    public void test_execute_0002()
    {
        final String STR_METHOD_NAME = "test_execute_0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoExecEndNotifyRequest l_ifoExecEndNotifyRequest =
            new WEB3IfoExecEndNotifyRequest();
        l_ifoExecEndNotifyRequest.institutionCode = "0D";
        l_ifoExecEndNotifyRequest.fuOpDiv = "2";
        l_ifoExecEndNotifyRequest.execEndDiv = "0";
        l_ifoExecEndNotifyRequest.rangeFrom = 1;
        l_ifoExecEndNotifyRequest.rangeTo = 5;
        
//        WEB3OnlineRunStatusTransactionCallback l_onlineCallback =
//            l_impl.new WEB3OnlineRunStatusTransactionCallback("0D",1,5,"2","0");

        try
        {
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            
            IfoProductParams l_IfoProductParams = TestDBUtility.getIfoProductRow();
            l_IfoProductParams.setFutureOptionDiv("2");
            l_IfoProductParams.setProductId(1006169090018L);
            l_IfoProductParams.setProductCode("160030005");
            l_IfoProductParams.setInstitutionCode("0D");
            TestDBUtility.deleteAllAndCommit(IfoProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_IfoProductParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(4);
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4);
            l_mainAccountParams.setBranchCode("381");
            
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            HostOrderexecutionEndParams l_hostOrderexecutionEndParams =
                this.getHostOrderexecutionEndRow();
            l_hostOrderexecutionEndParams.setInstitutionCode("0D");
            TestDBUtility.deleteAllAndCommit(HostOrderexecutionEndRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostOrderexecutionEndParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("3B");
            l_branchParams.setInstitutionId(33);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setAccountId(4);
            l_ifoOrderUnitParams.setRequestType("0");
            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
            
            
            l_ifoOrderUnitParams.setRequestType("3");
            l_ifoOrderUnitParams.setFirstOrderUnitId(1);
            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAllAndCommit(IfoOrderExecSendMailRow.TYPE);
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            
            TestDBUtility.insertWithDelAndCommit(l_enableOrderConditionParams);
            
            IfoTradedProductParams l_IfoTradedProductParams = new IfoTradedProductParams();
            l_IfoTradedProductParams.setTradedProductId(1006160060005L);
//            l_IfoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
            l_IfoTradedProductParams.setInstitutionCode("0D");
            l_IfoTradedProductParams.setMarketId(1002);
            l_IfoTradedProductParams.setProductId(1006169090018L);
            l_IfoTradedProductParams.setUnitSize(10000L);
            l_IfoTradedProductParams.setUnitMargin(0L);
            l_IfoTradedProductParams.setPerOrderMaxUnits(1000L);
            l_IfoTradedProductParams.setOrderCloseTime("");
            l_IfoTradedProductParams.setLastClosingPrice(8D);
            l_IfoTradedProductParams.setStartTradingDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
            l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20040718","yyyyMMdd")); 
            l_IfoTradedProductParams.setTradeStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setListFlag(BooleanEnum.TRUE);
            l_IfoTradedProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_IfoTradedProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.deleteAllAndCommit(IfoTradedProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(OrderexecutionEndRow.TYPE);
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            l_impl.execute(l_ifoExecEndNotifyRequest);
            
            OnlineRunStatusRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? and product_type = ? and future_option_div = ?";
            Object[] l_objWhere = {"0D", ProductTypeEnum.IFO, "2"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    OnlineRunStatusRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (OnlineRunStatusRow)l_lstRecords.get(0);

            assertEquals("1", l_row.getRunStatusDiv());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * "9：夕場前出来終了通知"
     * 9：エラー
     */
    public void test_execute_0003()
    {
        final String STR_METHOD_NAME = "test_execute_0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoExecEndNotifyRequest l_ifoExecEndNotifyRequest =
            new WEB3IfoExecEndNotifyRequest();
        l_ifoExecEndNotifyRequest.institutionCode = "0D";
        l_ifoExecEndNotifyRequest.fuOpDiv = "2";
        l_ifoExecEndNotifyRequest.execEndDiv = "1";
        l_ifoExecEndNotifyRequest.rangeFrom = 1;
        l_ifoExecEndNotifyRequest.rangeTo = 5;
        
//        WEB3OnlineRunStatusTransactionCallback l_onlineCallback =
//            l_impl.new WEB3OnlineRunStatusTransactionCallback("0D",1,5,"2","0");

        try
        {
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            
            IfoProductParams l_IfoProductParams = TestDBUtility.getIfoProductRow();
            l_IfoProductParams.setFutureOptionDiv("2");
            l_IfoProductParams.setProductId(1006169090018L);
            l_IfoProductParams.setProductCode("160030005");
            l_IfoProductParams.setInstitutionCode("0D");
            TestDBUtility.deleteAllAndCommit(IfoProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_IfoProductParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            
            l_subAccountParams.setAccountId(16);//TODO
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4);
            l_mainAccountParams.setBranchCode("381");
            
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            HostOrderexecutionEndParams l_hostOrderexecutionEndParams =
                this.getHostOrderexecutionEndRow();
            l_hostOrderexecutionEndParams.setInstitutionCode("0D");
            TestDBUtility.deleteAllAndCommit(HostOrderexecutionEndRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostOrderexecutionEndParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("3B");
            l_branchParams.setInstitutionId(33);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setAccountId(4);
            l_ifoOrderUnitParams.setRequestType("0");
            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
            
            
            l_ifoOrderUnitParams.setRequestType("3");
            l_ifoOrderUnitParams.setFirstOrderUnitId(1);
            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAllAndCommit(IfoOrderExecSendMailRow.TYPE);
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            
            TestDBUtility.insertWithDelAndCommit(l_enableOrderConditionParams);
            
            IfoTradedProductParams l_IfoTradedProductParams = new IfoTradedProductParams();
            l_IfoTradedProductParams.setTradedProductId(1006160060005L);
//            l_IfoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
            l_IfoTradedProductParams.setInstitutionCode("0D");
            l_IfoTradedProductParams.setMarketId(1002);
            l_IfoTradedProductParams.setProductId(1006169090018L);
            l_IfoTradedProductParams.setUnitSize(10000L);
            l_IfoTradedProductParams.setUnitMargin(0L);
            l_IfoTradedProductParams.setPerOrderMaxUnits(1000L);
            l_IfoTradedProductParams.setOrderCloseTime("");
            l_IfoTradedProductParams.setLastClosingPrice(8D);
            l_IfoTradedProductParams.setStartTradingDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
            l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20040718","yyyyMMdd")); 
            l_IfoTradedProductParams.setTradeStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setListFlag(BooleanEnum.TRUE);
            l_IfoTradedProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_IfoTradedProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.deleteAllAndCommit(IfoTradedProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(OrderexecutionEndRow.TYPE);
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
           
            l_impl.execute(l_ifoExecEndNotifyRequest);
            
            OnlineRunStatusRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? and product_type = ? and future_option_div = ?";
            Object[] l_objWhere = {"0D", ProductTypeEnum.IFO, "2"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    OnlineRunStatusRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (OnlineRunStatusRow)l_lstRecords.get(0);

            assertEquals("9", l_row.getRunStatusDiv());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * "1：出来終了通知"
     * 1：処理済
     * 
     * duo tiao 
     */
    public void test_execute_0004()
    {
        final String STR_METHOD_NAME = "test_execute_0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoExecEndNotifyRequest l_ifoExecEndNotifyRequest =
            new WEB3IfoExecEndNotifyRequest();
        l_ifoExecEndNotifyRequest.institutionCode = "0D";
        l_ifoExecEndNotifyRequest.fuOpDiv = "2";
        l_ifoExecEndNotifyRequest.execEndDiv = "0";
        l_ifoExecEndNotifyRequest.rangeFrom = 1;
        l_ifoExecEndNotifyRequest.rangeTo = 5;

        try
        {
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            
            IfoProductParams l_IfoProductParams = TestDBUtility.getIfoProductRow();
            l_IfoProductParams.setFutureOptionDiv("2");
            l_IfoProductParams.setProductId(1006169090018L);
            l_IfoProductParams.setProductCode("160030005");
            l_IfoProductParams.setInstitutionCode("0D");
            TestDBUtility.deleteAllAndCommit(IfoProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_IfoProductParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(4);
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setSubAccountId(10100101001007L);
            
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(5);
            l_subAccountParams1.setInstitutionId(33);
            l_subAccountParams1.setSubAccountId(10100101001007L);
            TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams1);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4);
            l_mainAccountParams.setBranchCode("381");
            
            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams1.setAccountId(5);
            l_mainAccountParams1.setBranchCode("381");
            l_mainAccountParams1.setAccountCode("101");
            
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams1);
            
            HostOrderexecutionEndParams l_hostOrderexecutionEndParams =
                this.getHostOrderexecutionEndRow();
            l_hostOrderexecutionEndParams.setInstitutionCode("0D");
            TestDBUtility.deleteAllAndCommit(HostOrderexecutionEndRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostOrderexecutionEndParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("3B");
            l_branchParams.setInstitutionId(33);
            
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionCode("3B");
            l_branchParams1.setInstitutionId(33);
            l_branchParams.setBranchId(1);
            l_branchParams.setBranchCode("f");
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            TestDBUtility.insertWithDelAndCommit(l_branchParams1);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setAccountId(4);
            l_ifoOrderUnitParams.setRequestType("0");
            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
            l_ifoOrderUnitParams.setRequestType("3");
            l_ifoOrderUnitParams.setFirstOrderUnitId(1);
            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams1.setOrderUnitId(1002);
            l_ifoOrderUnitParams1.setBranchId(1);
            l_ifoOrderUnitParams1.setFutureOptionDiv("2");
            l_ifoOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams1.setAccountId(5);
            l_ifoOrderUnitParams1.setRequestType("0");
            l_ifoOrderUnitParams1.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams1.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
            l_ifoOrderUnitParams1.setRequestType("3");
            l_ifoOrderUnitParams1.setFirstOrderUnitId(1);
            l_ifoOrderUnitParams1.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams1);
            
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            
            TestDBUtility.insertWithDelAndCommit(l_enableOrderConditionParams);
            
            IfoTradedProductParams l_IfoTradedProductParams = new IfoTradedProductParams();
            l_IfoTradedProductParams.setTradedProductId(1006160060005L);
//            l_IfoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
            l_IfoTradedProductParams.setInstitutionCode("0D");
            l_IfoTradedProductParams.setMarketId(1002);
            l_IfoTradedProductParams.setProductId(1006169090018L);
            l_IfoTradedProductParams.setUnitSize(10000L);
            l_IfoTradedProductParams.setUnitMargin(0L);
            l_IfoTradedProductParams.setPerOrderMaxUnits(1000L);
            l_IfoTradedProductParams.setOrderCloseTime("");
            l_IfoTradedProductParams.setLastClosingPrice(8D);
            l_IfoTradedProductParams.setStartTradingDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
            l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20040718","yyyyMMdd")); 
            l_IfoTradedProductParams.setTradeStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
            l_IfoTradedProductParams.setListFlag(BooleanEnum.TRUE);
            l_IfoTradedProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_IfoTradedProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.deleteAllAndCommit(IfoTradedProductParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(OrderexecutionEndRow.TYPE);
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.deleteAllAndCommit(IfoOrderExecSendMailRow.TYPE);
            
            l_impl.execute(l_ifoExecEndNotifyRequest);
            
            OnlineRunStatusRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? and product_type = ? and future_option_div = ?";
            Object[] l_objWhere = {"0D", ProductTypeEnum.IFO, "2"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    OnlineRunStatusRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (OnlineRunStatusRow)l_lstRecords.get(0);

            assertEquals("1", l_row.getRunStatusDiv());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public HostOrderexecutionEndParams getHostOrderexecutionEndRow()
    {
        HostOrderexecutionEndParams l_params = new HostOrderexecutionEndParams();
        
        l_params.setRequestCode("EI814");
        l_params.setInstitutionCode("0D");
        l_params.setProductType(ProductTypeEnum.IFO);
        l_params.setFutureOptionDiv("1");
        l_params.setStatus("0");
        
        return l_params;
    }  
}
@
