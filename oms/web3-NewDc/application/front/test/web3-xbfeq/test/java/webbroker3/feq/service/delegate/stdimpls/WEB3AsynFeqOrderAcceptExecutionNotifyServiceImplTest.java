head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.18.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AsynFeqOrderAcceptExecutionNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;

import test.util.TestDBUtility;

import webbroker3.feq.message.WEB3FeqOrderAcceptExecNotifyRequest;
import webbroker3.feq.service.delegate.stdimpls.WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl.WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.data.SleRcvdQRow;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.util.WEB3LogUtility;

public class WEB3AsynFeqOrderAcceptExecutionNotifyServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynFeqOrderAcceptExecutionNotifyServiceImplTest.class);
    WEB3FeqOrderAcceptExecNotifyRequest l_request =
        new WEB3FeqOrderAcceptExecNotifyRequest();
    WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl l_impl =
        new WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl(l_request);

    public WEB3AsynFeqOrderAcceptExecutionNotifyServiceImplTest(String arg0)
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
    
    public void testProcess()
    {
        final String STR_METHOD_NAME = "";
        log.entering(STR_METHOD_NAME);
        try
        {
            //DaemonTriggerRow
            TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
            DaemonTriggerParams l_triggerParams = TestDBUtility.getDaemonTriggerRow();
            TestDBUtility.insertWithDelAndCommit(l_triggerParams);

            //SleRcvdQRow  11111
            TestDBUtility.deleteAllAndCommit(SleRcvdQRow.TYPE);
            SleRcvdQParams l_sleRcvdQRow = this.getSleRcvdQParams();
            l_sleRcvdQRow.setStatus(SleRcvdqProcStatusEnum.TODO);
            l_sleRcvdQRow.setRepliesIndex(987l);
            TestDBUtility.insertWithDelAndCommit(l_sleRcvdQRow);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //222222222
            l_sleRcvdQRow.setQueueId(321654987l);
            l_sleRcvdQRow.setRepliesIndex(789l);
            TestDBUtility.insertWithDelAndCommit(l_sleRcvdQRow);
            
            //33333333333
            l_sleRcvdQRow.setQueueId(987654321l);
            l_sleRcvdQRow.setRepliesIndex(456l);
            TestDBUtility.insertWithDelAndCommit(l_sleRcvdQRow);
            
            //444444444
            l_sleRcvdQRow.setQueueId(9876579l);
            l_sleRcvdQRow.setRepliesIndex(132l);
            TestDBUtility.insertWithDelAndCommit(l_sleRcvdQRow);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback l_callBack =
                l_impl.new WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback(
                    new Long(100000l),
                    new Long(200000l),
                    new Long(l_triggerParams.getThreadNo()));
            l_callBack.process();
            
            //
            List l_list =l_queryProcessor.doFindAllQuery(SleRcvdQParams.TYPE);
            assertEquals(4, l_list.size());
            assertEquals(SleRcvdqProcStatusEnum.SKIP_PROCESSING_ERROR, ((SleRcvdQRow)l_list.get(0)).getStatus());
            assertEquals(SleRcvdqProcStatusEnum.SKIP_PROCESSING_ERROR, ((SleRcvdQRow)l_list.get(1)).getStatus());
            assertEquals(SleRcvdqProcStatusEnum.SKIP_PROCESSING_ERROR, ((SleRcvdQRow)l_list.get(2)).getStatus());
            assertEquals(SleRcvdqProcStatusEnum.SKIP_PROCESSING_ERROR, ((SleRcvdQRow)l_list.get(3)).getStatus());
            log.info(STR_METHOD_NAME + "----------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testProcess_Case001()
    {
        final String STR_METHOD_NAME = "testProcess_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DaemonTriggerRow
            TestDBUtility.deleteAll(DaemonTriggerRow.TYPE);
            DaemonTriggerParams l_triggerParams = TestDBUtility.getDaemonTriggerRow();
            l_queryProcessor.doInsertQuery(l_triggerParams);
           
            //SleRcvdQRow  11111
            TestDBUtility.deleteAllAndCommit(SleRcvdQRow.TYPE);
            SleRcvdQParams l_sleRcvdQRow = this.getSleRcvdQParams();
            l_sleRcvdQRow.setStatus(SleRcvdqProcStatusEnum.TODO);
            l_sleRcvdQRow.setRepliesIndex(987l);
            l_sleRcvdQRow.setInternalRef("123456");
            TestDBUtility.insertWithDelAndCommit(l_sleRcvdQRow);
    
            //222222222
            l_sleRcvdQRow.setQueueId(321654987l);
            l_sleRcvdQRow.setRepliesIndex(789l);
            l_sleRcvdQRow.setStatus(SleRcvdqProcStatusEnum.EXEC_PROCESSING);
            l_sleRcvdQRow.setRouteDiv("0");
            l_sleRcvdQRow.setStatus(SleRcvdqProcStatusEnum.EXEC_PROCESSING);
            TestDBUtility.insertWithDelAndCommit(l_sleRcvdQRow);
            
            //33333333333
            l_sleRcvdQRow.setQueueId(987654321l);
            l_sleRcvdQRow.setRepliesIndex(456l);
            l_sleRcvdQRow.setStatus(SleRcvdqProcStatusEnum.EXEC_PROCESSING);
            l_sleRcvdQRow.setRouteDiv("1");
            l_sleRcvdQRow.setStatus(SleRcvdqProcStatusEnum.TODO);
            TestDBUtility.insertWithDelAndCommit(l_sleRcvdQRow);
            
            //444444444
            l_sleRcvdQRow.setQueueId(9876579l);
            l_sleRcvdQRow.setRepliesIndex(132l);
            l_sleRcvdQRow.setStatus(SleRcvdqProcStatusEnum.EXEC_PROCESSING);
            l_sleRcvdQRow.setRouteDiv("2");
            l_sleRcvdQRow.setStatus(SleRcvdqProcStatusEnum.EXEC_PROCESSING);
            TestDBUtility.insertWithDelAndCommit(l_sleRcvdQRow);
    
            //FeqOrderUnitParams
            TestDBUtility.deleteAllAndCommit(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderId(123456);
            TestDBUtility.insertWithDelAndCommit(l_feqOrderUnitParams);
    
            //FeqOrderParams
            TestDBUtility.deleteAllAndCommit(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams =
                TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(123456);
            TestDBUtility.insertWithDelAndCommit(l_feqOrderParams); 
            
            //GenCurrencyParams
            TestDBUtility.deleteAllAndCommit(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams =
                TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrentBuyExecRate(12.23);
            l_genCurrencyParams.setCurrentSellExecRate(10.33);
            TestDBUtility.insertWithDelAndCommit(l_genCurrencyParams);
    
            //FeqProductParams
            TestDBUtility.deleteAllAndCommit(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams =
                TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_feqOrderUnitParams.getProductId());
            l_feqProductParams.setCurrencyCode(l_genCurrencyParams.getCurrencyCode());
            TestDBUtility.insertWithDelAndCommit(l_feqProductParams);
            
            //InstitutionParams
            TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback l_callBack =
                l_impl.new WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback(
                    new Long(100000l),
                    new Long(200000l),
                    new Long(l_triggerParams.getThreadNo()));
            l_callBack.process();

        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public SleRcvdQParams getSleRcvdQParams()
    {
        SleRcvdQParams l_params = new SleRcvdQParams();

        //キューID    queue_id   NUMBER  18NotNULL    
        l_params.setQueueId(123456789l);
        //銘柄タイプ　@xblocks_product_type  NUMBER  6NotNULL
        l_params.setXblocksProductType(ProductTypeEnum.FOREIGN_EQUITY);
        //アカウントID account_id  NUMBER  18NotNULL    
        l_params.setAccountId(123456l);
        //オペレータタイプ    op_type NUMBER  6NotNULL 
        l_params.setOpType(SleSendqOpTypeEnum.CANCEL_ORDER);
        //処理区分    status  NUMBER  6NotNULL   
        l_params.setStatus(SleRcvdqProcStatusEnum.NO_DEFINE_2);
        //作成日付    created_timestamp   DATEDEFAULT sysdate NOT NULL    
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp  DATEDEFAULT sysdate NOT NULL   
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        return l_params;
    }


}
@
