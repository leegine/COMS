head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.47.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoChangeCancelOrderAcceptUnitServiceImplTestZJ.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 先物OP訂正取消受付UnitServiceImplテスト(WEB3IfoChangeCancelOrderAcceptUnitServiceImplTestZJ.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2007/02/02 周捷 (中訊) 新規作成 仕様変更 モデル605
Revesion History : 2010/07/16 趙天月(中訊) 大証次期デリバティブシステム対応 モデル938
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.ifo.data.HostFotypeOrderAllDao;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.service.delegate.WEB3IfoChangeCancelOrderAcceptUnitService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （先物OP訂正取消受付UnitServiceImplテスト）<BR>
 *
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3IfoChangeCancelOrderAcceptUnitServiceImplTestZJ extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3IfoChangeCancelOrderAcceptUnitServiceImplTestZJ.class);
    
    public WEB3IfoChangeCancelOrderAcceptUnitServiceImplTestZJ(String arg0)
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

    WEB3IfoChangeCancelOrderAcceptUnitService service = null;
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptUnitServiceImpl.notifyChangeCancelOrderAcceptOvertime(HostFotypeOrderAcceptParams)'
     */
    public void testNotifyChangeCancelOrderAcceptOvertime1()
    {
        final String STR_METHOD_NAME = "testNotifyChangeCancelOrderAcceptOvertime1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        HostFotypeOrderAcceptParams l_params = null;

        try
        {
            service =
                (WEB3IfoChangeCancelOrderAcceptUnitService)Services.getService(WEB3IfoChangeCancelOrderAcceptUnitService.class);
            service.notifyChangeCancelOrderAcceptOvertime(l_params);
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testNotifyChangeCancelOrderAcceptOvertime2()
    {
        final String STR_METHOD_NAME = "testNotifyChangeCancelOrderAcceptOvertime2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        HostFotypeOrderAcceptParams l_params = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        
        BranchParams l_branchRarams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();

        try
        {
            l_params = new HostFotypeOrderAcceptParams();
            l_params.setRequestCode("EI80A");
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("1010010");
            l_params.setTraderCode("10011");
            l_params.setOrderRequestNumber("111");
            l_params.setAcceptStatus("1");
            l_params.setStatus("1");
            l_params.setSubmitOrderRouteDiv("");
            l_params.setAcceptNumber("12345678901234567890");
            l_params.setProductCode("1234567890");
            l_params.setBuySellDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_params);

            TestDBUtility.insertWithDelAndCommit(l_branchRarams);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            service =
                (WEB3IfoChangeCancelOrderAcceptUnitService)Services.getService(WEB3IfoChangeCancelOrderAcceptUnitService.class);
            service.notifyChangeCancelOrderAcceptOvertime(l_params);
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testNotifyChangeCancelOrderAcceptOvertime3()
    {
        final String STR_METHOD_NAME = "testNotifyChangeCancelOrderAcceptOvertime3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        HostFotypeOrderAcceptParams l_params = null;
        IfoOrderUnitParams l_unitParams = null;
        HostFotypeOrderAllParams l_hostAllParams = null;
        
        BranchParams l_branchRarams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAcceptParams.TYPE);
            l_params = new HostFotypeOrderAcceptParams();
            l_params.setRequestCode("EI80A");
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("1010010");
            l_params.setTraderCode("10011");
            l_params.setOrderRequestNumber("000003006");
            l_params.setAcceptStatus("1");
            l_params.setErrorMessage("1001");
            l_params.setStatus("1");
            l_params.setSubmitOrderRouteDiv("");
            l_params.setCreatedTimestamp(new Date("2004/01/01"));
            l_params.setLastUpdatedTimestamp(new Date("2004/01/01"));
            l_params.setAcceptNumber("12345678901234567890");
            l_params.setProductCode("1234567890");
            l_params.setBuySellDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_params);

            TestDBUtility.deleteAllAndCommit(IfoOrderUnitParams.TYPE);
            l_unitParams = this.ifoOrderUnit();
            l_unitParams.setAcceptNumber("12345678901234567890");
            l_unitParams.setProductCode("1234567890");
            l_unitParams.setBuySellDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_unitParams);

            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllParams.TYPE);
            l_hostAllParams = this.hostFotypeOrderAll();
            TestDBUtility.insertWithDelAndCommit(l_hostAllParams);

            TestDBUtility.insertWithDelAndCommit(l_branchRarams);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);

            service =
                (WEB3IfoChangeCancelOrderAcceptUnitService)Services.getService(WEB3IfoChangeCancelOrderAcceptUnitService.class);
            service.notifyChangeCancelOrderAcceptOvertime(l_params);

            IfoOrderUnitRow l_unitRow =
                (IfoOrderUnitRow)IfoOrderUnitDao.findRowByOrderUnitId(1001);
            List l_lis =
                HostFotypeOrderAllDao.findRowsByAccountIdOrderRequestNumber(new Long(101001010010L), "000003006");
            HostFotypeOrderAllRow l_hostAllRow =
                (HostFotypeOrderAllRow)l_lis.get(0);
            
            assertEquals("02", l_unitRow.getOrderRev());
            assertEquals("0", l_hostAllRow.getStatus());
            log.info(l_hostAllRow.getCorpCode());
            assertEquals("33381600000300602999", l_hostAllRow.getCorpCode());
            String l_date = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getSystemTimestamp(), "yyyyMMdd");
            assertEquals(l_date, WEB3DateUtility.formatDate(l_unitRow.getLastUpdatedTimestamp(), "yyyyMMdd"));
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test fail !");
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeOrderAcceptParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
        }
    }

    public void testNotifyChangeCancelOrderAcceptOvertime4()
    {
        final String STR_METHOD_NAME = "testNotifyChangeCancelOrderAcceptOvertime4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        HostFotypeOrderAcceptParams l_params = null;
        IfoOrderUnitParams l_unitParams = null;
        HostFotypeOrderAllParams l_hostAllParams = null;
        BranchParams l_branchRarams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        try
        {
            l_params = new HostFotypeOrderAcceptParams();
            l_params.setRequestCode("EI80A");
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("1010010");
            l_params.setTraderCode("10011");
            l_params.setOrderRequestNumber("000003006");
            l_params.setAcceptStatus("1");
            l_params.setErrorMessage("1001");
            l_params.setStatus("1");
            l_params.setSubmitOrderRouteDiv("");
            l_params.setCreatedTimestamp(new Date("2004/01/01"));
            l_params.setLastUpdatedTimestamp(new Date("2004/01/01"));
            l_params.setAcceptNumber("12345678901234567890");
            l_params.setProductCode("1234567890");
            l_params.setBuySellDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_params);

            l_unitParams = this.ifoOrderUnit();
            l_unitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_unitParams.setAcceptNumber("12345678901234567890");
            l_unitParams.setProductCode("1234567890");
            l_unitParams.setBuySellDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_unitParams);

            l_hostAllParams = this.hostFotypeOrderAll();
            l_hostAllParams.setCancelDiv("1");
            l_hostAllParams.setCorpCode("3338160000030062999");
            TestDBUtility.insertWithDelAndCommit(l_hostAllParams);

            TestDBUtility.insertWithDelAndCommit(l_branchRarams);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);  

            service =
                (WEB3IfoChangeCancelOrderAcceptUnitService)Services.getService(WEB3IfoChangeCancelOrderAcceptUnitService.class);
            service.notifyChangeCancelOrderAcceptOvertime(l_params);

            IfoOrderUnitRow l_unitRow =
                (IfoOrderUnitRow)IfoOrderUnitDao.findRowByOrderUnitId(1001);
            List l_lis =
                HostFotypeOrderAllDao.findRowsByAccountIdOrderRequestNumber(new Long(101001010010L), "000003006");
            HostFotypeOrderAllRow l_hostAllRow =
                (HostFotypeOrderAllRow)l_lis.get(0);
            
            assertEquals(l_unitParams.getOrderRev(), l_unitRow.getOrderRev());
            assertEquals("0", l_hostAllRow.getStatus());
            assertEquals(l_unitParams.getLastUpdatedTimestamp(), l_unitRow.getLastUpdatedTimestamp());
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeOrderAcceptParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
        }
    }

    public IfoOrderUnitParams ifoOrderUnit()
    {
        IfoOrderUnitParams l_params = new IfoOrderUnitParams();
        l_params.setOrderUnitId(1001);
        l_params.setAccountId(101001010010L);
        l_params.setSubAccountId(10100101001007L);
        l_params.setBranchId(33381);
        l_params.setTraderId(null);
        l_params.setOrderId(1001);
        l_params.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
        l_params.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
        l_params.setLastOrderActionSerialNo(1);
        l_params.setLastExecutionSerialNo(0);
        l_params.setProductType(ProductTypeEnum.IFO);
        l_params.setFutureOptionDiv("1");
        l_params.setMarketId(1002);
        l_params.setQuantity(100);
        l_params.setLimitPrice(0);
        l_params.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
        l_params.setOrderConditionType("0");
        l_params.setOrderCondOperator(null);
        l_params.setStopPriceType(null);
        l_params.setStopOrderPrice(null);
        l_params.setWLimitPrice(null);
        l_params.setDeliveryDate(new Date("2004/01/01"));
        l_params.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        l_params.setBizDate("20040101");
        l_params.setProductId(1006169090018L);
        l_params.setCreatedTimestamp(new Date("2004/01/01"));
        l_params.setLastUpdatedTimestamp(new Date("2004/01/02"));
        l_params.setOrderRequestNumber("000003006");
        l_params.setConfirmedOrderRev("2");
        l_params.setOrderRev("1");
        return l_params;
    }
    public HostFotypeOrderAllParams hostFotypeOrderAll()
    {
        HostFotypeOrderAllParams l_params =new HostFotypeOrderAllParams();
        l_params.setAccountId(101001010010L);
        l_params.setOrderRequestNumber("000003006");
        l_params.setSubmitOrderRouteDiv("0");
        l_params.setCancelDiv("0");
        l_params.setCorpCode("3338160000030061999");
        l_params.setAllOrderChangeDiv("0");
        return l_params;
    }

}
@
