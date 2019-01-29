head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.18.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqOrderAndExecutionUpdateServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.data.SleRcvdQRow;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqOrderAndExecutionUpdateServiceImplTest extends TestBaseForMock {

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAndExecutionUpdateServiceImplTest.class);

    
    public WEB3FeqOrderAndExecutionUpdateServiceImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    WEB3FeqOrderAndExecutionUpdateServiceImpl l_Impl = 
        new WEB3FeqOrderAndExecutionUpdateServiceImpl();
    
    
    /*
     * Test method for 'webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAndExecutionUpdateServiceImpl.updateTempExecuteUnit(HostFeqOrderExecNotifyParams)'
     */
    public void testupdateTempExecuteUnit_T1() 
    {
//        final String STR_METHOD_NAME = "testupdateTempExecuteUnit_T1";
//        log.entering(STR_METHOD_NAME);
//        
//        HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams = null;
//           
//        SleRcvdQParams l_sleRcvdQParams = new SleRcvdQParams();
//
//        try
//        {
//
//            l_Impl.updateTempExecuteUnit(l_hostFeqOrderExecNotifyParams,l_sleRcvdQParams);
//        
//            fail();
//            
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getErrorMessage(),l_ex);
//            fail();
//        }
//        catch(WEB3BaseRuntimeException l_ex)
//        {
//            log.error(l_ex.getErrorMessage(),l_ex);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
//        }
//        log.exiting(STR_METHOD_NAME);
        
    }

    public void testupdateTempExecuteUnit_T2() 
    {
        final String STR_METHOD_NAME = "testupdateTempExecuteUnit_T2";
        log.entering(STR_METHOD_NAME);
        
//        HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams =
//            new HostFeqOrderExecNotifyParams();
//        
//        l_hostFeqOrderExecNotifyParams.setOrderBizDate(GtlUtils.getSystemTimestamp());
//        l_hostFeqOrderExecNotifyParams.setOrderEmpCode("123");
//
//        
//        try
//        {
//            
//            TestDBUtility.deleteAll(SleRcvdQParams.TYPE);
//            SleRcvdQParams l_sleRcvdQParams = this.getSleRcvdQParams();
//            TestDBUtility.insertWithDel(l_sleRcvdQParams);
//
//            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
//
//            FeqOrderUnitParams l_feqOrderUnitRow = TestDBUtility.getFeqOrderUnitRow();
//            l_feqOrderUnitRow.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"));
//            l_feqOrderUnitRow.setOrderEmpCode("123");
//            l_feqOrderUnitRow.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            TestDBUtility.insertWithDel(l_feqOrderUnitRow);
//            
//            
//            l_Impl.updateTempExecuteUnit(l_hostFeqOrderExecNotifyParams,l_sleRcvdQParams);
//        
//
//            List lisResult = null;
//            try
//            {
//                QueryProcessor process = Processors.getDefaultProcessor();
//                lisResult = process.doFindAllQuery(SleRcvdQRow.TYPE);
//    
//            }
//            catch(DataException l_ex)
//            {
//                l_ex.printStackTrace();
//            }
//
//            log.debug("lisResult.size()=============="+lisResult.size());
//            SleRcvdQRow l_sle = (SleRcvdQRow)lisResult.get(0);
//            
//            
//            assertEquals(l_sle.getStatus().intValue(), 7);
//                
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getErrorMessage(),l_ex);
//            fail();
//        }
//        
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
        l_params.setStatus(SleRcvdqProcStatusEnum.TODO);
        //作成日付    created_timestamp   DATEDEFAULT sysdate NOT NULL    
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp  DATEDEFAULT sysdate NOT NULL   
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        return l_params;
    }
    
}
@
