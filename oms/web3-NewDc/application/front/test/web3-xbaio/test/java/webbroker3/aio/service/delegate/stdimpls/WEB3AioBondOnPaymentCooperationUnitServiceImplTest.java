head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.36.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioBondOnPaymentCooperationUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3AioBondOnPaymentCooperationUnitServiceImplTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/02/08  何文敏(中訊) 新規作成
 */
package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.aio.WEB3AioBondOnPaymentInfo;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioBondOnPaymentCooperationUnitServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioBondOnPaymentCooperationUnitServiceImplTest.class);

    WEB3AioBondOnPaymentInfo l_aioBondPaymentInfo = null;
    WEB3AioBondOnPaymentCooperationUnitServiceImpl l_impl =
        new WEB3AioBondOnPaymentCooperationUnitServiceImpl();
    
    public WEB3AioBondOnPaymentCooperationUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        MOCK_MANAGER.setIsMockUsed(true);
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testSubmitOrder()
    {
        final String STR_METHOD_NAME = "testSubmitOrder()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20061225","yyyyMMdd");
            l_aioBondPaymentInfo =
                new WEB3AioBondOnPaymentInfo();
            l_aioBondPaymentInfo.setSettlementDiv("1");
            l_aioBondPaymentInfo.setAccountId(new Long(333812512246L));
            l_aioBondPaymentInfo.setDeliveryDate(l_datExpect);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            l_mainAccountParams.setBranchLock("2");
            l_mainAccountParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            
            // 注文単位テーブル
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams1.setBranchId(33381l);
            l_aioOrderUnitParams1.setAccountId(333812512246L);
            l_aioOrderUnitParams1.setDescription(null);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);
            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[] {String.class, String.class, ProductTypeEnum.class },
                "111");     
            
            OrderSubmissionResult l_result =
                new OrderSubmissionResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "submitNewOrder",
                    new Class[] {SubAccount.class, 
                    ProductTypeEnum.class, 
                    NewOrderSpec.class, long.class, 
                    String.class, boolean.class},
                    l_result);
            
            //債券出金情報不為空，決済区分等於円貨
            //（引数.債券出金情報.決済区分 = "1"（円貨））の場合、null
            //settlementDiv="1"   驗證記述是否為null;
            l_impl.submitOrder(l_aioBondPaymentInfo);
            List l_aioOrderUnitRows = AioOrderUnitDao.findRowsByAccountId(333812512246L); 
            AioOrderUnitRow l_row = (AioOrderUnitRow)l_aioOrderUnitRows.get(0);
            assertEquals(l_row.getDescription(), null);

            //債券出金情報不為空，決済区分等於円貨
            //（引数.債券出金情報.決済区分 = "2"（円貨））の場合、null
            //settlementDiv="1"   驗證記述是否為null;
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            l_aioBondPaymentInfo.setSettlementDiv("2");
            l_aioBondPaymentInfo.setBondOrderUnitId(new Long(123));
            l_impl.submitOrder(l_aioBondPaymentInfo);
            
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246L);
            l_aioOrderUnitParams2.setDescription("123");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);
            l_aioOrderUnitRows = AioOrderUnitDao.findRowsByAccountId(333812512246L); 
            AioOrderUnitRow l_afterRow = (AioOrderUnitRow)l_aioOrderUnitRows.get(0);
            assertEquals(l_afterRow.getDescription(), "123");
            
            //債券出金情報不為空，決済区分等於円貨
            //（引数.債券出金情報.決済区分 = "3"（円貨））の場合、null
            //settlementDiv="1"   驗證記述是否為null;
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            l_aioBondPaymentInfo.setSettlementDiv("3");
            l_aioBondPaymentInfo.setBondOrderUnitId(new Long(123));
            l_impl.submitOrder(l_aioBondPaymentInfo);
            
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams3.setBranchId(33381l);
            l_aioOrderUnitParams3.setAccountId(333812512246L);
            l_aioOrderUnitParams3.setDescription(null);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);            
                      
            l_aioOrderUnitRows = AioOrderUnitDao.findRowsByAccountId(333812512246L); 
            AioOrderUnitRow l_afterRow3 = (AioOrderUnitRow)l_aioOrderUnitRows.get(0);
            assertEquals(l_afterRow3.getDescription(), "123");
        } 
        catch (Exception l_ex)
        {
            log.error("error in Exception", l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateForeignOrder()
    {
        final String STR_METHOD_NAME = "testcreateForeignOrder()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_aioBondPaymentInfo =
                new WEB3AioBondOnPaymentInfo();
            TestDBUtility.deleteAll(BondOrderUnitRow.TYPE);
            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123L);
            l_aioBondPaymentInfo = l_impl.createForeignOrder(l_bondOrderUnitParams);
            assertEquals(l_aioBondPaymentInfo.getBondOrderUnitId() + "", "123");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
        
    }
}
@
