head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.01.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondDomesticOrderCancelUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券注文種別判定のテストクラス(WEB3AdminBondDomesticOrderCancelUpdateInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/11 周墨洋 (中訊) 仕様変更・モデル No.239
*/

package webbroker3.bd;

import java.lang.reflect.Constructor;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (国内債券管理者注文取消更新インタセプタのテストクラス)<BR>
 * 国内債券管理者注文取消更新インタセプタのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminBondDomesticOrderCancelUpdateInterceptorTest
    extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3AdminBondDomesticOrderCancelUpdateInterceptorTest.class);

    /**
     * 国内債券管理者注文取消更新インタセプタ
     */
    WEB3AdminBondDomesticOrderCancelUpdateInterceptor l_interceptor;

    /**
     * @@param arg0
     */
    public WEB3AdminBondDomesticOrderCancelUpdateInterceptorTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     *
     */
    public void testMutate_case0001()
    {

        String STR_METHOD_NAME = " testMutate_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_interceptor = new WEB3AdminBondDomesticOrderCancelUpdateInterceptor();

        try
        {
            Constructor l_constructor;

            OrderManagerPersistenceType l_orderManagerPersistenceType;
            l_constructor =
                OrderManagerPersistenceType.class.getDeclaredConstructor(new Class[] {int.class});
            l_constructor.setAccessible(true);
            l_orderManagerPersistenceType =
                (OrderManagerPersistenceType)l_constructor.newInstance(new Object[] {new Integer(1)});

            OrderManagerPersistenceContext l_orderManagerPersistenceContext;
            l_constructor =
                OrderManagerPersistenceContext.class.getDeclaredConstructor(new Class[] {int.class});
            l_constructor.setAccessible(true);
            l_orderManagerPersistenceContext =
                (OrderManagerPersistenceContext)l_constructor.newInstance(new Object[] {new Integer(1)});

            BondOrderUnitParams l_bondOrderUnitParams = null;

            l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_bondOrderUnitParams);

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseRuntimeException l_exBRE)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exBRE.getErrorInfo());
            log.error(l_exBRE.getMessage(), l_exBRE);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testMutate_case0002()
    {

        String STR_METHOD_NAME = " testMutate_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_interceptor = new WEB3AdminBondDomesticOrderCancelUpdateInterceptor();

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
        l_interceptor.setAdministrator(l_administrator);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            Constructor l_constructor;

            OrderManagerPersistenceType l_orderManagerPersistenceType;
            l_constructor =
                OrderManagerPersistenceType.class.getDeclaredConstructor(
                    new Class[] {int.class});
            l_constructor.setAccessible(true);
            l_orderManagerPersistenceType =
                (OrderManagerPersistenceType)l_constructor.newInstance(
                    new Object[] {new Integer(1)});

            OrderManagerPersistenceContext l_orderManagerPersistenceContext;
            l_constructor =
                OrderManagerPersistenceContext.class.getDeclaredConstructor(
                    new Class[] {int.class});
            l_constructor.setAccessible(true);
            l_orderManagerPersistenceContext =
                (OrderManagerPersistenceContext)l_constructor.newInstance(
                    new Object[] {new Integer(1)});

            BondOrderUnitParams l_bondOrderUnitParams =
                TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setBizDate("20070102");
            l_bondOrderUnitParams.setForeignBizDate("20070801");
            l_bondOrderUnitParams.setOrderChanel("9");
            Date l_datTemp = WEB3DateUtility.getDate("20070802", "yyyyMMdd");
            l_datTemp.setHours(3);
            l_datTemp.setMinutes(4);
            l_datTemp.setSeconds(5);
            l_bondOrderUnitParams.setReceivedDateTime(l_datTemp);
            l_bondOrderUnitParams.setSonarTraderCode("98765");
            l_bondOrderUnitParams.setPrice(123.45D);
            l_bondOrderUnitParams.setOrderRequestNumber("987654321");
            l_bondOrderUnitParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondOrderUnitParams.setCurrencyCode("286");
            l_bondOrderUnitParams.setSettlementDiv("4");
            l_bondOrderUnitParams.setAutoExecDiv("8");
            l_bondOrderUnitParams.setExecutedPrice(999.87D);
            l_bondOrderUnitParams.setBaseFxRate(444.55D);
            l_bondOrderUnitParams.setExecFxRate(666.33D);
            l_bondOrderUnitParams.setTradingPrice(543.21D);
            l_bondOrderUnitParams.setForeignTradingPrice(888.53D);
            l_bondOrderUnitParams.setAccruedInterest(21.54D);
            l_bondOrderUnitParams.setForeignAccruedInterest(1.02D);
            l_bondOrderUnitParams.setEstimatedPrice(23121231.22D);
            l_bondOrderUnitParams.setForeignEstimatedPrice(12.00D);
            l_bondOrderUnitParams.setAdjustmentBeforeMaturity(999.99D);
            l_bondOrderUnitParams.setElapsedDays(10);
            l_bondOrderUnitParams.setCalcBaseDays(40);
            l_bondOrderUnitParams.setExecDate(
                WEB3DateUtility.getDate("20070802", "yyyyMMdd"));
            l_bondOrderUnitParams.setForeignExecDate(
                WEB3DateUtility.getDate("20070803", "yyyyMMdd"));
            l_bondOrderUnitParams.setPaymentDate(
                WEB3DateUtility.getDate("20070804", "yyyyMMdd"));
            l_bondOrderUnitParams.setCustodianCode("22");
            l_bondOrderUnitParams.setOrderRootDiv("3");

            l_bondOrderUnitParams.setErrorReasonCode("abcd");
            l_bondOrderUnitParams.setExecHostReflectDiv("5");
            l_bondOrderUnitParams.setCancelHostReflectDiv("6");

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_datOrderBizDate =
                WEB3DateUtility.getDate("20070103", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                l_datOrderBizDate);

            BondOrderUnitParams l_actualBondOrderUnitParams;
            l_actualBondOrderUnitParams =
                l_interceptor.mutate(
                    l_orderManagerPersistenceType,
                    l_orderManagerPersistenceContext,
                    l_bondOrderUnitParams);

            assertEquals("35", l_actualBondOrderUnitParams.getDealType());
            assertEquals(WEB3DateUtility.getDate("20070209", "yyyyMMdd"),
                l_actualBondOrderUnitParams.getForeignDeliveryDate());
            assertEquals("1", l_actualBondOrderUnitParams.getLockStatus());
            assertEquals("2", l_actualBondOrderUnitParams.getOrderExecStatus());
            assertEquals("20070801",
                l_actualBondOrderUnitParams.getForeignBizDate());
            assertEquals("9", l_actualBondOrderUnitParams.getOrderChanel());
            assertEquals(l_datTemp,
                l_actualBondOrderUnitParams.getReceivedDateTime());
            assertEquals("98765",
                l_actualBondOrderUnitParams.getSonarTraderCode());
            assertEquals(123.45D,
                l_actualBondOrderUnitParams.getPrice(), 0.01D);
            assertEquals("987654321",
                l_actualBondOrderUnitParams.getOrderRequestNumber());
            assertEquals(BondTypeEnum.DOMESTIC_BOND,
                l_actualBondOrderUnitParams.getBondType());
            assertEquals("286", l_actualBondOrderUnitParams.getCurrencyCode());
            assertEquals("4", l_actualBondOrderUnitParams.getSettlementDiv());
            assertEquals("8", l_actualBondOrderUnitParams.getAutoExecDiv());
            assertEquals(999.87D,
                l_actualBondOrderUnitParams.getExecutedPrice(),
                0.01D);
            assertEquals(
                444.55D, l_actualBondOrderUnitParams.getBaseFxRate(),
                0.01D);
            assertEquals(
                666.33D, l_actualBondOrderUnitParams.getExecFxRate(),
                0.01D);
            assertEquals(543.21D,
                l_actualBondOrderUnitParams.getTradingPrice(),
                0.01D);
            assertEquals(888.53D,
                l_actualBondOrderUnitParams.getForeignTradingPrice(),
                0.01D);
            assertEquals(21.54D,
                l_actualBondOrderUnitParams.getAccruedInterest(),
                0.01D);
            assertEquals(1.02D,
                l_actualBondOrderUnitParams.getForeignAccruedInterest(),
                0.01D);
            assertEquals(
                23121231.22D, l_actualBondOrderUnitParams.getEstimatedPrice(),
                0.01D);
            assertEquals(12.00D,
                l_actualBondOrderUnitParams.getForeignEstimatedPrice(),
                0.01D);
            assertEquals(999.99D,
                l_actualBondOrderUnitParams.getAdjustmentBeforeMaturity(),
                0.01D);
            assertEquals(10, l_actualBondOrderUnitParams.getElapsedDays());
            assertEquals(40, l_actualBondOrderUnitParams.getCalcBaseDays());
            assertEquals(WEB3DateUtility.getDate("20070802", "yyyyMMdd"),
                l_actualBondOrderUnitParams.getExecDate());
            assertEquals(WEB3DateUtility.getDate("20070803", "yyyyMMdd"),
                l_actualBondOrderUnitParams.getForeignExecDate());
            assertEquals(WEB3DateUtility.getDate("20070804", "yyyyMMdd"),
                l_actualBondOrderUnitParams.getPaymentDate());
            assertEquals("22", l_actualBondOrderUnitParams.getCustodianCode());
            assertEquals("3", l_actualBondOrderUnitParams.getOrderRootDiv());

            assertEquals("0", l_actualBondOrderUnitParams.getHostSendDiv());

            assertEquals("330001",
                l_actualBondOrderUnitParams.getAdministratorCode());
            assertEquals("abcd",
                l_actualBondOrderUnitParams.getErrorReasonCode());
            assertEquals("5",
                l_actualBondOrderUnitParams.getExecHostReflectDiv());
            assertEquals("6",
                l_actualBondOrderUnitParams.getCancelHostReflectDiv());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseRuntimeException l_exBRE)
        {
            log.error(l_exBRE.getMessage(), l_exBRE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {

            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

    /**
     *
     */
    public void testMutate_case0003()
    {

        String STR_METHOD_NAME = " testMutate_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_interceptor = new WEB3AdminBondDomesticOrderCancelUpdateInterceptor();

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
        l_interceptor.setAdministrator(l_administrator);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            Constructor l_constructor;

            OrderManagerPersistenceType l_orderManagerPersistenceType;
            l_constructor =
                OrderManagerPersistenceType.class.getDeclaredConstructor(
                    new Class[] {int.class});
            l_constructor.setAccessible(true);
            l_orderManagerPersistenceType =
                (OrderManagerPersistenceType)l_constructor.newInstance(
                    new Object[] {new Integer(1)});

            OrderManagerPersistenceContext l_orderManagerPersistenceContext;
            l_constructor =
                OrderManagerPersistenceContext.class.getDeclaredConstructor(
                    new Class[] {int.class});
            l_constructor.setAccessible(true);
            l_orderManagerPersistenceContext =
                (OrderManagerPersistenceContext)l_constructor.newInstance(
                    new Object[] {new Integer(1)});

            BondOrderUnitParams l_bondOrderUnitParams =
                TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setBizDate("20070102");
            l_bondOrderUnitParams.setForeignBizDate("20070801");
            l_bondOrderUnitParams.setOrderChanel("9");
            Date l_datTemp = WEB3DateUtility.getDate("20070802", "yyyyMMdd");
            l_datTemp.setHours(3);
            l_datTemp.setMinutes(4);
            l_datTemp.setSeconds(5);
            l_bondOrderUnitParams.setReceivedDateTime(l_datTemp);
            l_bondOrderUnitParams.setSonarTraderCode("98765");
            l_bondOrderUnitParams.setPrice(123.45D);
            l_bondOrderUnitParams.setOrderRequestNumber("987654321");
            l_bondOrderUnitParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondOrderUnitParams.setCurrencyCode("286");
            l_bondOrderUnitParams.setSettlementDiv("4");
            l_bondOrderUnitParams.setAutoExecDiv("8");
            l_bondOrderUnitParams.setExecutedPrice(999.87D);
            l_bondOrderUnitParams.setBaseFxRate(444.55D);
            l_bondOrderUnitParams.setExecFxRate(666.33D);
            l_bondOrderUnitParams.setTradingPrice(543.21D);
            l_bondOrderUnitParams.setForeignTradingPrice(888.53D);
            l_bondOrderUnitParams.setAccruedInterest(21.54D);
            l_bondOrderUnitParams.setForeignAccruedInterest(1.02D);
            l_bondOrderUnitParams.setEstimatedPrice(23121231.22D);
            l_bondOrderUnitParams.setForeignEstimatedPrice(12.00D);
            l_bondOrderUnitParams.setAdjustmentBeforeMaturity(999.99D);
            l_bondOrderUnitParams.setElapsedDays(10);
            l_bondOrderUnitParams.setCalcBaseDays(40);
            l_bondOrderUnitParams.setExecDate(
                WEB3DateUtility.getDate("20070802", "yyyyMMdd"));
            l_bondOrderUnitParams.setForeignExecDate(
                WEB3DateUtility.getDate("20070803", "yyyyMMdd"));
            l_bondOrderUnitParams.setPaymentDate(
                WEB3DateUtility.getDate("20070804", "yyyyMMdd"));
            l_bondOrderUnitParams.setCustodianCode("22");
            l_bondOrderUnitParams.setOrderRootDiv("3");

            l_bondOrderUnitParams.setErrorReasonCode("abcd");
            l_bondOrderUnitParams.setExecHostReflectDiv("5");
            l_bondOrderUnitParams.setCancelHostReflectDiv("6");

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_datOrderBizDate =
                WEB3DateUtility.getDate("20070102", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                l_datOrderBizDate);

            BondOrderUnitParams l_actualBondOrderUnitParams;
            l_actualBondOrderUnitParams =
                l_interceptor.mutate(
                    l_orderManagerPersistenceType,
                    l_orderManagerPersistenceContext,
                    l_bondOrderUnitParams);

            assertEquals("35", l_actualBondOrderUnitParams.getDealType());
            assertEquals(WEB3DateUtility.getDate("20070209", "yyyyMMdd"),
                l_actualBondOrderUnitParams.getForeignDeliveryDate());
            assertEquals("1", l_actualBondOrderUnitParams.getLockStatus());
            assertEquals("2", l_actualBondOrderUnitParams.getOrderExecStatus());
            assertEquals("20070801",
                l_actualBondOrderUnitParams.getForeignBizDate());
            assertEquals("9", l_actualBondOrderUnitParams.getOrderChanel());
            assertEquals(l_datTemp,
                l_actualBondOrderUnitParams.getReceivedDateTime());
            assertEquals("98765",
                l_actualBondOrderUnitParams.getSonarTraderCode());
            assertEquals(123.45D,
                l_actualBondOrderUnitParams.getPrice(), 0.01D);
            assertEquals("987654321",
                l_actualBondOrderUnitParams.getOrderRequestNumber());
            assertEquals(BondTypeEnum.DOMESTIC_BOND,
                l_actualBondOrderUnitParams.getBondType());
            assertEquals("286", l_actualBondOrderUnitParams.getCurrencyCode());
            assertEquals("4", l_actualBondOrderUnitParams.getSettlementDiv());
            assertEquals("8", l_actualBondOrderUnitParams.getAutoExecDiv());
            assertEquals(999.87D,
                l_actualBondOrderUnitParams.getExecutedPrice(),
                0.01D);
            assertEquals(
                444.55D, l_actualBondOrderUnitParams.getBaseFxRate(),
                0.01D);
            assertEquals(
                666.33D, l_actualBondOrderUnitParams.getExecFxRate(),
                0.01D);
            assertEquals(543.21D,
                l_actualBondOrderUnitParams.getTradingPrice(),
                0.01D);
            assertEquals(888.53D,
                l_actualBondOrderUnitParams.getForeignTradingPrice(),
                0.01D);
            assertEquals(21.54D,
                l_actualBondOrderUnitParams.getAccruedInterest(),
                0.01D);
            assertEquals(1.02D,
                l_actualBondOrderUnitParams.getForeignAccruedInterest(),
                0.01D);
            assertEquals(
                23121231.22D, l_actualBondOrderUnitParams.getEstimatedPrice(),
                0.01D);
            assertEquals(12.00D,
                l_actualBondOrderUnitParams.getForeignEstimatedPrice(),
                0.01D);
            assertEquals(999.99D,
                l_actualBondOrderUnitParams.getAdjustmentBeforeMaturity(),
                0.01D);
            assertEquals(10, l_actualBondOrderUnitParams.getElapsedDays());
            assertEquals(40, l_actualBondOrderUnitParams.getCalcBaseDays());
            assertEquals(WEB3DateUtility.getDate("20070802", "yyyyMMdd"),
                l_actualBondOrderUnitParams.getExecDate());
            assertEquals(WEB3DateUtility.getDate("20070803", "yyyyMMdd"),
                l_actualBondOrderUnitParams.getForeignExecDate());
            assertEquals(WEB3DateUtility.getDate("20070804", "yyyyMMdd"),
                l_actualBondOrderUnitParams.getPaymentDate());
            assertEquals("22", l_actualBondOrderUnitParams.getCustodianCode());
            assertEquals("3", l_actualBondOrderUnitParams.getOrderRootDiv());

            assertEquals("2", l_actualBondOrderUnitParams.getHostSendDiv());

            assertEquals("330001",
                l_actualBondOrderUnitParams.getAdministratorCode());
            assertEquals("abcd",
                l_actualBondOrderUnitParams.getErrorReasonCode());
            assertEquals("5",
                l_actualBondOrderUnitParams.getExecHostReflectDiv());
            assertEquals("6",
                l_actualBondOrderUnitParams.getCancelHostReflectDiv());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseRuntimeException l_exBRE)
        {
            log.error(l_exBRE.getMessage(), l_exBRE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {

            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

    /**
     *
     */
    public void testMutate_case0004()
    {

        String STR_METHOD_NAME = " testMutate_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_interceptor = new WEB3AdminBondDomesticOrderCancelUpdateInterceptor();

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
        l_interceptor.setAdministrator(l_administrator);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            Constructor l_constructor;

            OrderManagerPersistenceType l_orderManagerPersistenceType;
            l_constructor =
                OrderManagerPersistenceType.class.getDeclaredConstructor(
                    new Class[] {int.class});
            l_constructor.setAccessible(true);
            l_orderManagerPersistenceType =
                (OrderManagerPersistenceType)l_constructor.newInstance(
                    new Object[] {new Integer(1)});

            OrderManagerPersistenceContext l_orderManagerPersistenceContext;
            l_constructor =
                OrderManagerPersistenceContext.class.getDeclaredConstructor(
                    new Class[] {int.class});
            l_constructor.setAccessible(true);
            l_orderManagerPersistenceContext =
                (OrderManagerPersistenceContext)l_constructor.newInstance(
                    new Object[] {new Integer(1)});

            BondOrderUnitParams l_bondOrderUnitParams =
                TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setBizDate("20070102");
            l_bondOrderUnitParams.setForeignBizDate("20070801");
            l_bondOrderUnitParams.setOrderChanel("9");
            Date l_datTemp = WEB3DateUtility.getDate("20070802", "yyyyMMdd");
            l_datTemp.setHours(3);
            l_datTemp.setMinutes(4);
            l_datTemp.setSeconds(5);
            l_bondOrderUnitParams.setReceivedDateTime(l_datTemp);
            l_bondOrderUnitParams.setSonarTraderCode("98765");
            l_bondOrderUnitParams.setPrice(123.45D);
            l_bondOrderUnitParams.setOrderRequestNumber("987654321");
            l_bondOrderUnitParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondOrderUnitParams.setCurrencyCode("286");
            l_bondOrderUnitParams.setSettlementDiv("4");
            l_bondOrderUnitParams.setAutoExecDiv("8");
            l_bondOrderUnitParams.setExecutedPrice(999.87D);
            l_bondOrderUnitParams.setBaseFxRate(444.55D);
            l_bondOrderUnitParams.setExecFxRate(666.33D);
            l_bondOrderUnitParams.setTradingPrice(543.21D);
            l_bondOrderUnitParams.setForeignTradingPrice(888.53D);
            l_bondOrderUnitParams.setAccruedInterest(21.54D);
            l_bondOrderUnitParams.setForeignAccruedInterest(1.02D);
            l_bondOrderUnitParams.setEstimatedPrice(23121231.22D);
            l_bondOrderUnitParams.setForeignEstimatedPrice(12.00D);
            l_bondOrderUnitParams.setAdjustmentBeforeMaturity(999.99D);
            l_bondOrderUnitParams.setElapsedDays(10);
            l_bondOrderUnitParams.setCalcBaseDays(40);
            l_bondOrderUnitParams.setExecDate(
                WEB3DateUtility.getDate("20070802", "yyyyMMdd"));
            l_bondOrderUnitParams.setForeignExecDate(
                WEB3DateUtility.getDate("20070803", "yyyyMMdd"));
            l_bondOrderUnitParams.setPaymentDate(
                WEB3DateUtility.getDate("20070804", "yyyyMMdd"));
            l_bondOrderUnitParams.setCustodianCode("22");
            l_bondOrderUnitParams.setOrderRootDiv("3");

            l_bondOrderUnitParams.setErrorReasonCode("abcd");
            l_bondOrderUnitParams.setExecHostReflectDiv("5");
            l_bondOrderUnitParams.setCancelHostReflectDiv("6");

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_datOrderBizDate =
                WEB3DateUtility.getDate("20070101", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                l_datOrderBizDate);

            BondOrderUnitParams l_actualBondOrderUnitParams;
            l_actualBondOrderUnitParams =
                l_interceptor.mutate(
                    l_orderManagerPersistenceType,
                    l_orderManagerPersistenceContext,
                    l_bondOrderUnitParams);

            assertEquals("35", l_actualBondOrderUnitParams.getDealType());
            assertEquals(WEB3DateUtility.getDate("20070209", "yyyyMMdd"),
                l_actualBondOrderUnitParams.getForeignDeliveryDate());
            assertEquals("1", l_actualBondOrderUnitParams.getLockStatus());
            assertEquals("2", l_actualBondOrderUnitParams.getOrderExecStatus());
            assertEquals("20070801",
                l_actualBondOrderUnitParams.getForeignBizDate());
            assertEquals("9", l_actualBondOrderUnitParams.getOrderChanel());
            assertEquals(l_datTemp,
                l_actualBondOrderUnitParams.getReceivedDateTime());
            assertEquals("98765",
                l_actualBondOrderUnitParams.getSonarTraderCode());
            assertEquals(123.45D,
                l_actualBondOrderUnitParams.getPrice(), 0.01D);
            assertEquals("987654321",
                l_actualBondOrderUnitParams.getOrderRequestNumber());
            assertEquals(BondTypeEnum.DOMESTIC_BOND,
                l_actualBondOrderUnitParams.getBondType());
            assertEquals("286", l_actualBondOrderUnitParams.getCurrencyCode());
            assertEquals("4", l_actualBondOrderUnitParams.getSettlementDiv());
            assertEquals("8", l_actualBondOrderUnitParams.getAutoExecDiv());
            assertEquals(999.87D,
                l_actualBondOrderUnitParams.getExecutedPrice(),
                0.01D);
            assertEquals(
                444.55D, l_actualBondOrderUnitParams.getBaseFxRate(),
                0.01D);
            assertEquals(
                666.33D, l_actualBondOrderUnitParams.getExecFxRate(),
                0.01D);
            assertEquals(543.21D,
                l_actualBondOrderUnitParams.getTradingPrice(),
                0.01D);
            assertEquals(888.53D,
                l_actualBondOrderUnitParams.getForeignTradingPrice(),
                0.01D);
            assertEquals(21.54D,
                l_actualBondOrderUnitParams.getAccruedInterest(),
                0.01D);
            assertEquals(1.02D,
                l_actualBondOrderUnitParams.getForeignAccruedInterest(),
                0.01D);
            assertEquals(
                23121231.22D, l_actualBondOrderUnitParams.getEstimatedPrice(),
                0.01D);
            assertEquals(12.00D,
                l_actualBondOrderUnitParams.getForeignEstimatedPrice(),
                0.01D);
            assertEquals(999.99D,
                l_actualBondOrderUnitParams.getAdjustmentBeforeMaturity(),
                0.01D);
            assertEquals(10, l_actualBondOrderUnitParams.getElapsedDays());
            assertEquals(40, l_actualBondOrderUnitParams.getCalcBaseDays());
            assertEquals(WEB3DateUtility.getDate("20070802", "yyyyMMdd"),
                l_actualBondOrderUnitParams.getExecDate());
            assertEquals(WEB3DateUtility.getDate("20070803", "yyyyMMdd"),
                l_actualBondOrderUnitParams.getForeignExecDate());
            assertEquals(WEB3DateUtility.getDate("20070804", "yyyyMMdd"),
                l_actualBondOrderUnitParams.getPaymentDate());
            assertEquals("22", l_actualBondOrderUnitParams.getCustodianCode());
            assertEquals("3", l_actualBondOrderUnitParams.getOrderRootDiv());

            assertEquals("2", l_actualBondOrderUnitParams.getHostSendDiv());

            assertEquals("330001",
                l_actualBondOrderUnitParams.getAdministratorCode());
            assertEquals("abcd",
                l_actualBondOrderUnitParams.getErrorReasonCode());
            assertEquals("5",
                l_actualBondOrderUnitParams.getExecHostReflectDiv());
            assertEquals("6",
                l_actualBondOrderUnitParams.getCancelHostReflectDiv());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseRuntimeException l_exBRE)
        {
            log.error(l_exBRE.getMessage(), l_exBRE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {

            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

    /**
     *
     */
    public void testMutate_case0005()
    {

        String STR_METHOD_NAME = " testMutate_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_interceptor = new WEB3AdminBondDomesticOrderCancelUpdateInterceptor();

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
        l_interceptor.setAdministrator(l_administrator);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            Constructor l_constructor;

            OrderManagerPersistenceType l_orderManagerPersistenceType;
            l_constructor =
                OrderManagerPersistenceType.class.getDeclaredConstructor(
                    new Class[] {int.class});
            l_constructor.setAccessible(true);
            l_orderManagerPersistenceType =
                (OrderManagerPersistenceType)l_constructor.newInstance(
                    new Object[] {new Integer(1)});

            OrderManagerPersistenceContext l_orderManagerPersistenceContext;
            l_constructor =
                OrderManagerPersistenceContext.class.getDeclaredConstructor(
                    new Class[] {int.class});
            l_constructor.setAccessible(true);
            l_orderManagerPersistenceContext =
                (OrderManagerPersistenceContext)l_constructor.newInstance(
                    new Object[] {new Integer(1)});

            BondOrderUnitParams l_bondOrderUnitParams =
                TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setBizDate("20070102");
            l_bondOrderUnitParams.setForeignBizDate("20070801");
            l_bondOrderUnitParams.setOrderChanel("9");
            Date l_datTemp = WEB3DateUtility.getDate("20070802", "yyyyMMdd");
            l_datTemp.setHours(3);
            l_datTemp.setMinutes(4);
            l_datTemp.setSeconds(5);
            l_bondOrderUnitParams.setReceivedDateTime(l_datTemp);
            l_bondOrderUnitParams.setSonarTraderCode("98765");
            l_bondOrderUnitParams.setPrice(123.45D);
            l_bondOrderUnitParams.setOrderRequestNumber("987654321");
            l_bondOrderUnitParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondOrderUnitParams.setCurrencyCode("286");
            l_bondOrderUnitParams.setSettlementDiv("4");
            l_bondOrderUnitParams.setAutoExecDiv("8");
            l_bondOrderUnitParams.setExecutedPrice(999.87D);
            l_bondOrderUnitParams.setBaseFxRate(444.55D);
            l_bondOrderUnitParams.setExecFxRate(666.33D);
            l_bondOrderUnitParams.setTradingPrice(543.21D);
            l_bondOrderUnitParams.setForeignTradingPrice(888.53D);
            l_bondOrderUnitParams.setAccruedInterest(21.54D);
            l_bondOrderUnitParams.setForeignAccruedInterest(1.02D);
            l_bondOrderUnitParams.setEstimatedPrice(23121231.22D);
            l_bondOrderUnitParams.setForeignEstimatedPrice(12.00D);
            l_bondOrderUnitParams.setAdjustmentBeforeMaturity(999.99D);
            l_bondOrderUnitParams.setElapsedDays(10);
            l_bondOrderUnitParams.setCalcBaseDays(40);
            l_bondOrderUnitParams.setExecDate(
                WEB3DateUtility.getDate("20070802", "yyyyMMdd"));
            l_bondOrderUnitParams.setForeignExecDate(
                WEB3DateUtility.getDate("20070803", "yyyyMMdd"));
            l_bondOrderUnitParams.setPaymentDate(
                WEB3DateUtility.getDate("20070804", "yyyyMMdd"));
            l_bondOrderUnitParams.setCustodianCode("22");
            l_bondOrderUnitParams.setOrderRootDiv("3");

            l_bondOrderUnitParams.setErrorReasonCode("abcd");
            l_bondOrderUnitParams.setExecHostReflectDiv("5");
            l_bondOrderUnitParams.setCancelHostReflectDiv("6");

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_datOrderBizDate =
                WEB3DateUtility.getDate("20070101", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                l_datOrderBizDate);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            BondOrderUnitParams l_actualBondOrderUnitParams;
            l_actualBondOrderUnitParams =
                l_interceptor.mutate(
                    l_orderManagerPersistenceType,
                    l_orderManagerPersistenceContext,
                    l_bondOrderUnitParams);

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseRuntimeException l_exBRE)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                l_exBRE.getErrorInfo());
            log.error(l_exBRE.getMessage(), l_exBRE);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {

            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

}
@
