head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.39.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminOrderExecuteDataManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.adminorderexecinquiry;


import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;

import test.util.TestDBUtility;

import webbroker3.adminorderexecinquiry.define.WEB3AdminExecTypeDef;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommonRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者注文約定照会データマネージャ)<BR>
 * 管理者注文約定照会のDB I/O、データ変換などを管理するクラス。<BR>
 * 
 * @@author 稲本潤に(中訊)
 * @@version 1.0
 */
public class WEB3AdminOrderExecuteDataManagerTest extends TestBaseForMock
{

    public WEB3AdminOrderExecuteDataManagerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        // 管理者注文約定照会データマネージャ
        l_adminOrderExecuteDataManager = new WEB3AdminOrderExecuteDataManager();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminOrderExecuteDataManagerTest.class);
    /**
     * 管理者注文約定照会データマネージャ<BR>
     */
    private WEB3AdminOrderExecuteDataManager l_adminOrderExecuteDataManager;
    
    
    /**
     * create共通検索条件文字列<BR>
     * Method-Name: createCommonQueryString<BR>
     * expect:SQL-Language: 
     * "branch_id in (?)  and biz_date >= ?  and order_open_status = ? and expiration_status = ? and error_reason_code in (?, ?)"<BR>
     * @@param なし
     * @@return なし
     * @@throws なし
     */
    public void testCreateCommonQueryString_0001()
    {
        final String STR_METHOD_NAME = " testCreateCommonQueryString_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // 管理者・株式注文約定照会リクエスト
            WEB3AdminOROrderExecutionRefCommonRequest l_request = 
                new WEB3AdminOREquityOrderExecutionRefReferenceRequest();
            // 部店
            l_request.branchCode = new String[]{"00001"};
            // 注文状態区分
            l_request.orderState = "23";
            
            String l_strQueryCond = 
                l_adminOrderExecuteDataManager.createCommonQueryString("00123", l_request);

            assertEquals(
                    "branch_id in (?)  and biz_date >= ?  and order_open_status = ? and expiration_status = ? and error_reason_code in (?, ?)"
                    , l_strQueryCond
                    );
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * create共通検索条件データコンテナ<BR>
     * Method-Name: createCommonQueryDataContainer<BR>
     * expect: 株式管理者手動失効済"W001",先物OP管理者手動失効済W004
     * @@param なし
     * @@return なし
     * @@throws なし
     */
    public void testCreateCommonQueryDataContainer_0001()
    {
        final String STR_METHOD_NAME = " testCreateCommonQueryDataContainer_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        RowType l_rowType_administrator = new RowType( "administrator", "master" );
        RowType l_rowType_institution = new RowType("institution", "master");
        RowType l_rowType_branch = new RowType("branch", "master");
        
        try
        {
            
            TestDBUtility.deleteAll(l_rowType_administrator);
            TestDBUtility.deleteAll(l_rowType_institution);
            TestDBUtility.deleteAll(l_rowType_branch);
                        
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(1010L);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_BranchParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(1010L);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getInstitution",
                    new Class[] {long.class},
                    new InstitutionImpl(33));
            
            // 管理者・株式注文約定照会リクエスト
            WEB3AdminOROrderExecutionRefCommonRequest l_request = 
                new WEB3AdminOREquityOrderExecutionRefReferenceRequest();
            // 部店
            l_request.branchCode = new String[0];
            // 注文状態区分
            l_request.orderState = "23";

            String[] strArray = l_adminOrderExecuteDataManager.createCommonQueryDataContainer("00123", l_request);

            assertEquals(5, strArray.length);
            assertEquals("2", strArray[1]);
            assertEquals("3", strArray[2]);
            assertEquals("W001", strArray[3]);
            assertEquals("W004", strArray[4]);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.error("",e);
            fail();            
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType_administrator);
                TestDBUtility.deleteAll(l_rowType_institution);
                TestDBUtility.deleteAll(l_rowType_branch);
            } catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    /**
     * get注文状態区分（PR層）<BR>
     * Method-Name: getOrderStateDivPR<BR>
     * expect: 株式管理者手動失効済"W001",return 23
     * @@param なし
     * @@return なし
     * @@throws なし
     */
    public void testGetOrderStateDivPR_0001()
    {
        final String STR_METHOD_NAME = " testGetOrderStateDivPR_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // EqTypeOrderUnit EqtypeOrderUnitParams
            EqtypeOrderUnitParams l = new EqtypeOrderUnitParams();
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = new RsvEqOrderUnitParams(){};
                   
            OrderUnit l_orderUnit = new WEB3ToSuccEqTypeOrderUnitImpl(l_rsvEqOrderUnitRow)
            {
                /**
                 * （getDataSourceObjectの実装）<BR> 
                 * <BR>
                 * this.株式予約注文単位Rowを返却する。
                 * @@return Object
                 * @@roseuid 431E765B01F0
                 */
                public Object getDataSourceObject() 
                {    
                    EqtypeOrderUnitParams order = new EqtypeOrderUnitParams()
                    {
                    };
                    order.setErrorReasonCode("W001");
                    return order;
                }
                
                /**
                 * (get注文有効状態)<BR>
                 * （getOrderOpenStatusの実装）<BR>
                 * <BR>
                 * this.株式予約注文単位Row.注文有効状態を返却する。
                 * @@return OrderOpenStatusEnum
                 * @@roseuid 43218C600140
                 */
                public OrderOpenStatusEnum getOrderOpenStatus() 
                {
                    return new OrderOpenStatusEnum( 2, "CLOSED" );
                }
                
                /**
                 * (get失効区分)<BR>
                 * （getExpirationStatusの実装）<BR>
                 * <BR>
                 * this.株式予約注文単位Row.失効区分を返却する。
                 * @@return OrderExpirationStatusEnum
                 * @@roseuid 43218C6001FB
                 */
                public OrderExpirationStatusEnum getExpirationStatus() 
                {
                    return new OrderExpirationStatusEnum( 3, "INVALIDATED_BY_MKT" );
                }
            };
            
            
            String l_strreturnValue = l_adminOrderExecuteDataManager.getOrderStateDivPR(l_orderUnit);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("23", l_strreturnValue);

        }
        catch (Exception e) 
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * get注文状態区分（PR層）<BR>
     * Method-Name: getOrderStateDivPR<BR>
     * expect: 先物OP管理者手動失効済W004 return 23
     * @@param なし
     * @@return なし
     * @@throws なし
     */
    public void testGetOrderStateDivPR_0002()
    {
        final String STR_METHOD_NAME = " testGetOrderStateDivPR_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // EqTypeOrderUnit EqtypeOrderUnitParams
            EqtypeOrderUnitParams l = new EqtypeOrderUnitParams();
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = new RsvEqOrderUnitParams(){};
                   
            OrderUnit l_orderUnit = new WEB3ToSuccEqTypeOrderUnitImpl(l_rsvEqOrderUnitRow)
            {
                /**
                 * （getDataSourceObjectの実装）<BR> 
                 * <BR>
                 * this.株式予約注文単位Rowを返却する。
                 * @@return Object
                 * @@roseuid 431E765B01F0
                 */
                public Object getDataSourceObject() 
                {    
                    EqtypeOrderUnitParams order = new EqtypeOrderUnitParams()
                    {
                    };
                    order.setErrorReasonCode("W004");
                    return order;
                }
                
                /**
                 * (get注文有効状態)<BR>
                 * （getOrderOpenStatusの実装）<BR>
                 * <BR>
                 * this.株式予約注文単位Row.注文有効状態を返却する。
                 * @@return OrderOpenStatusEnum
                 * @@roseuid 43218C600140
                 */
                public OrderOpenStatusEnum getOrderOpenStatus() 
                {
                    return new OrderOpenStatusEnum( 2, "CLOSED" );
                }
                
                /**
                 * (get失効区分)<BR>
                 * （getExpirationStatusの実装）<BR>
                 * <BR>
                 * this.株式予約注文単位Row.失効区分を返却する。
                 * @@return OrderExpirationStatusEnum
                 * @@roseuid 43218C6001FB
                 */
                public OrderExpirationStatusEnum getExpirationStatus() 
                {
                    return new OrderExpirationStatusEnum( 3, "INVALIDATED_BY_MKT" );
                }
            };
            
            
            String l_strreturnValue = l_adminOrderExecuteDataManager.getOrderStateDivPR(l_orderUnit);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("23", l_strreturnValue);

        }
        catch (Exception e) 
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateCommonQueryString_ES_C0001()
    {
        final String STR_METHOD_NAME = " testCreateCommonQueryString_ES_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 管理者・株式注文約定照会リクエスト
            WEB3AdminOROrderExecutionRefCommonRequest l_request = 
                new WEB3AdminOREquityOrderExecutionRefReferenceRequest();
            // 部店
            l_request.branchCode = new String[]{"00001"};
            // 注文状態区分
            l_request.orderState = "51";
            
            l_request.expirationDateType = "1";
            
            String l_strQueryCond = 
                l_adminOrderExecuteDataManager.createCommonQueryString("00123", l_request);

            assertEquals("branch_id in (?)  and biz_date >= ?  and first_order_unit_id is null " +
                "and expiration_date >= ? and order_open_status = ? and expiration_status = ? " +
                "and error_reason_code not in (?, ?)  and first_order_unit_id is not null ", l_strQueryCond);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateCommonQueryString_ES_C0003()
    {
        final String STR_METHOD_NAME = " testCreateCommonQueryString_ES_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 管理者・株式注文約定照会リクエスト
            WEB3AdminOROrderExecutionRefCommonRequest l_request = 
                new WEB3AdminOREquityOrderExecutionRefReferenceRequest();
            l_request.branchCode = new String[0];
            l_request.execType = WEB3AdminExecTypeDef.EXEC_TYPE_NOT_PROMISE;
            
            String l_strQueryCond = 
                l_adminOrderExecuteDataManager.createCommonQueryString("00123", l_request);

            assertEquals(" and biz_date >= ?  and (executed_quantity is null or executed_quantity = ?)", l_strQueryCond);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateCommonQueryString_ES_C0004()
    {
        final String STR_METHOD_NAME = " testCreateCommonQueryString_ES_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 管理者・株式注文約定照会リクエスト
            WEB3AdminOROrderExecutionRefCommonRequest l_request = 
                new WEB3AdminOREquityOrderExecutionRefReferenceRequest();
            l_request.branchCode = new String[0];
            l_request.execType = WEB3AdminExecTypeDef.EXEC_TYPE_ONE_COMPLETE;
            
            String l_strQueryCond = 
                l_adminOrderExecuteDataManager.createCommonQueryString("00123", l_request);
            StringBuffer l_strQueryCond1 = new StringBuffer();
            l_strQueryCond1.append(" and executed_quantity is not null");
            l_strQueryCond1.append(" and executed_quantity != ?");
            l_strQueryCond1.append(" and executed_quantity < confirmed_quantity");
//            l_strQueryCond1.append(" and executed_amount is not null ");
            assertEquals(" and biz_date >= ? " + l_strQueryCond1.toString(), l_strQueryCond);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateCommonQueryString_ES_C0005()
    {
        final String STR_METHOD_NAME = " testCreateCommonQueryString_ES_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 管理者・株式注文約定照会リクエスト
            WEB3AdminOROrderExecutionRefCommonRequest l_request = 
                new WEB3AdminOREquityOrderExecutionRefReferenceRequest();
            l_request.branchCode = new String[0];
            l_request.execType = WEB3AdminExecTypeDef.EXEC_TYPE_ALL_COMPLETE;
            
            String l_strQueryCond = 
                l_adminOrderExecuteDataManager.createCommonQueryString("00123", l_request);
            StringBuffer l_strQueryCond1 = new StringBuffer();
            l_strQueryCond1.append(" and executed_quantity = confirmed_quantity");
//            l_strQueryCond1.append(" and executed_amount is not null ");
            assertEquals(" and biz_date >= ? "+ l_strQueryCond1.toString(), l_strQueryCond);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateCommonQueryString_ES_C0006()
    {
        final String STR_METHOD_NAME = " testCreateCommonQueryString_ES_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 管理者・株式注文約定照会リクエスト
            WEB3AdminOROrderExecutionRefCommonRequest l_request = 
                new WEB3AdminOREquityOrderExecutionRefReferenceRequest();
            l_request.branchCode = new String[0];
            l_request.execType = WEB3AdminExecTypeDef.EXEC_PROCESSING_ONE_COMPLETE;
            
            String l_strQueryCond = 
                l_adminOrderExecuteDataManager.createCommonQueryString("00123", l_request);
            StringBuffer l_strQueryCond1 = new StringBuffer();
            l_strQueryCond1.append(" and executed_quantity is not null");
            l_strQueryCond1.append(" and executed_quantity != ?");
            l_strQueryCond1.append(" and executed_quantity < confirmed_quantity");
//            l_strQueryCond1.append(" and executed_amount is null ");
            assertEquals(" and biz_date >= ? " + l_strQueryCond1.toString(), l_strQueryCond);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateCommonQueryString_ES_C0002()
    {
        final String STR_METHOD_NAME = " testCreateCommonQueryString_ES_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 管理者・株式注文約定照会リクエスト
            WEB3AdminORFutOpOrderExecutionRefReferenceRequest l_request = 
                new WEB3AdminORFutOpOrderExecutionRefReferenceRequest();
            // 部店
            l_request.branchCode = new String[]{"00001"};
            // 注文状態区分
            l_request.orderState = "51";
            
            l_request.expirationDateType = "1";
            
            String l_strQueryCond = 
                l_adminOrderExecuteDataManager.createCommonQueryString("00123", l_request);

            assertEquals("branch_id in (?)  and biz_date >= ? ", l_strQueryCond);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateCommonQueryDataContainer_ES_C0001()
    {
        final String STR_METHOD_NAME = " testCreateCommonQueryDataContainer_ES_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(1010L);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_BranchParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(1010L);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            // 管理者・株式注文約定照会リクエスト
            WEB3AdminOROrderExecutionRefCommonRequest l_request = 
                new WEB3AdminOREquityOrderExecutionRefReferenceRequest();
            // 部店
            l_request.branchCode = new String[0];
            // 注文状態区分
            l_request.orderState = "51";

            String[] l_strArrays = l_adminOrderExecuteDataManager.createCommonQueryDataContainer("0D", l_request);
            
            Timestamp l_tsBizDate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
            WEB3GentradeBizDate l_dateCalc = new WEB3GentradeBizDate(l_tsBizDate);
            Date l_datPrevBizDate = l_dateCalc.roll(-1);
            

            assertEquals(6, l_strArrays.length);
            assertEquals(WEB3DateUtility.formatDate(l_datPrevBizDate, "yyyyMMdd"), l_strArrays[0]);
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd"), l_strArrays[1]);
            assertEquals("2", l_strArrays[2]);
            assertEquals("2", l_strArrays[3]);
            assertEquals("0000", l_strArrays[4]);
            assertEquals("W002", l_strArrays[5]);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateCommonQueryDataContainer_ES_C0002()
    {
        final String STR_METHOD_NAME = " testCreateCommonQueryDataContainer_ES_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(1010L);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_BranchParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(1010L);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            // 管理者・株式注文約定照会リクエスト
            WEB3AdminORFutOpOrderExecutionRefReferenceRequest l_request = 
                new WEB3AdminORFutOpOrderExecutionRefReferenceRequest();
            // 部店
            l_request.branchCode = new String[0];
            // 注文状態区分
            l_request.orderState = "51";

            String[] l_strArrays = l_adminOrderExecuteDataManager.createCommonQueryDataContainer("0D", l_request);
            
            Timestamp l_tsBizDate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
            WEB3GentradeBizDate l_dateCalc = new WEB3GentradeBizDate(l_tsBizDate);
            Date l_datPrevBizDate = l_dateCalc.roll(-1);
            

            assertEquals(1, l_strArrays.length);
            assertEquals(WEB3DateUtility.formatDate(l_datPrevBizDate, "yyyyMMdd"), l_strArrays[0]);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
