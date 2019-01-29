head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.45.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondHelperServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者ヘルパーサービスImpl(WEB3AdminBondHelperServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/03/12 徐大方(中訊) 新規作成
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BranchImpl;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.data.BondBranchConditionRow;
import webbroker3.bd.message.WEB3AdminBondOrderExecInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券管理者ヘルパーサービスImpl)<BR>
 * 債券管理者ヘルパーサービスImplクラス
 * 
 * @@author 徐大方
 * @@version 1.0
 */
public class WEB3AdminBondHelperServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3AdminBondHelperServiceImplTest.class);

    /**
     * 債券管理者ヘルパーサービスImpl
     */
    private WEB3AdminBondHelperServiceImpl l_adminBondHelperServiceImpl;

    public WEB3AdminBondHelperServiceImplTest(String name)
    {
        super(name);
    }

    public void testResetExecuteDateInfoCase1()
    {
        final String STR_METHOD_NAME = "testResetExecuteDateInfoCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminBondHelperServiceImpl l_impl = new WEB3AdminBondHelperServiceImpl();
        WEB3AdminBondOrderExecInfo l_orderExecInfo = new WEB3AdminBondOrderExecInfo();
        WEB3BondExecuteDateInfo l_executeDateInfo = new WEB3BondExecuteDateInfo();
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge();
        WEB3BondProduct l_bondProduct = null;
        String l_strSettleDiv = "";
        Branch l_branch = null;
        try
        {
            l_impl.resetExecuteDateInfo(
                l_orderExecInfo,
                l_executeDateInfo,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_strSettleDiv,
                l_branch);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01079, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testResetExecuteDateInfoCase2()
    {
        final String STR_METHOD_NAME = "testResetExecuteDateInfoCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();

        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.deleteAll(BondBranchConditionRow.TYPE);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3AdminBondHelperServiceImpl l_impl = new WEB3AdminBondHelperServiceImpl();
        WEB3AdminBondOrderExecInfo l_orderExecInfo = new WEB3AdminBondOrderExecInfo();
        l_orderExecInfo.domesticDeliveryDate = WEB3DateUtility.getDate("20070312", "yyyyMMdd");
        WEB3BondExecuteDateInfo l_executeDateInfo = new WEB3BondExecuteDateInfo();
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge();
        WEB3BondProduct l_bondProduct = null;
        String l_strSettleDiv = "";
        BranchImpl l_branch = null;
        try
        {
            l_branch = new BranchImpl(33381);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            WEB3BondExecuteDateInfo l_info = l_impl.resetExecuteDateInfo(
                l_orderExecInfo,
                l_executeDateInfo,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_strSettleDiv,
                l_branch);
            assertEquals(WEB3DateUtility.getDate("20070312", "yyyyMMdd"), l_info.getDeliveryDate()); 
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testResetExecuteDateInfoCase3()
    {
        final String STR_METHOD_NAME = "testResetExecuteDateInfoCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();

        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.deleteAll(BondBranchConditionRow.TYPE);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3AdminBondHelperServiceImpl l_impl = new WEB3AdminBondHelperServiceImpl();
        WEB3AdminBondOrderExecInfo l_orderExecInfo = new WEB3AdminBondOrderExecInfo();
        l_orderExecInfo.paymentDate = WEB3DateUtility.getDate("20070312", "yyyyMMdd");
        WEB3BondExecuteDateInfo l_executeDateInfo = new WEB3BondExecuteDateInfo();
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge = null;
        try
        {
            l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge(
                OrderTypeEnum.BOND_BUY,
                WEB3DealTypeDef.RECRUIT_TRADING);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        WEB3BondProduct l_bondProduct = null;
        String l_strSettleDiv = "";
        BranchImpl l_branch = null;
        try
        {
            l_branch = new BranchImpl(33381);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            WEB3BondExecuteDateInfo l_info = l_impl.resetExecuteDateInfo(
                l_orderExecInfo,
                l_executeDateInfo,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_strSettleDiv,
                l_branch);
            assertEquals(null, l_info.getDeliveryDate()); 
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testGetExecuteChangButtonDiv_case0001()
    {

        String STR_METHOD_NAME = " testGetExecuteChangButtonDiv_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminBondHelperServiceImpl = new WEB3AdminBondHelperServiceImpl();

        BondOrderUnitParams l_bondOrderUnitParams =
            TestDBUtility.getBondOrderUnitRow();
        l_bondOrderUnitParams.setBondType(BondTypeEnum.FOREIGN_BOND);
        l_bondOrderUnitParams.setOrderExecStatus(
            WEB3BondOrderExecStatusDef.UNEXECUTED);

        try
        {
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {

            WEB3BondOrderUnit l_bondOrderUnit =
                new WEB3BondOrderUnit(l_bondOrderUnitParams);

            String l_strActual = null;

            l_strActual =
                l_adminBondHelperServiceImpl.getExecuteChangButtonDiv(
                    l_bondOrderUnit);

            String l_strExpected = "1";
            assertEquals(l_strExpected, l_strActual);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
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
    public void testGetExecuteChangButtonDiv_case0002()
    {

        String STR_METHOD_NAME = " testGetExecuteChangButtonDiv_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminBondHelperServiceImpl = new WEB3AdminBondHelperServiceImpl();

        BondOrderUnitParams l_bondOrderUnitParams =
            TestDBUtility.getBondOrderUnitRow();
        l_bondOrderUnitParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
        l_bondOrderUnitParams.setOrderExecStatus(
            WEB3BondOrderExecStatusDef.UNEXECUTED);

        try
        {
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {

            WEB3BondOrderUnit l_bondOrderUnit =
                new WEB3BondOrderUnit(l_bondOrderUnitParams);

            String l_strActual = null;

            l_strActual =
                l_adminBondHelperServiceImpl.getExecuteChangButtonDiv(
                    l_bondOrderUnit);

            String l_strExpected = "0";
            assertEquals(l_strExpected, l_strActual);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
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
    public void testToOrderExecInfo_case0001()
    {

        String STR_METHOD_NAME = " testToOrderExecInfo_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminBondHelperServiceImpl = new WEB3AdminBondHelperServiceImpl();

        ProductParams l_productParams = TestDBUtility.getProductRow();

        BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
        l_bondProductParams.setProductId(l_productParams.getProductId());

        BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
        l_bondOrderUnitParams.setProductId(l_productParams.getProductId());
        l_bondOrderUnitParams.setOrderExecStatus(WEB3BondOrderExecStatusDef.UNEXECUTED);
        l_bondOrderUnitParams.setQuantity(100D);
        l_bondOrderUnitParams.setLimitPrice(15.0D);
        l_bondOrderUnitParams.setExecutedQuantity(200D);
        l_bondOrderUnitParams.setExecutedPrice(30.0D);

        try
        {
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_bondProductParams);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070801", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datOrderBizDate);

            WEB3BondOrderUnit l_bondOrderUnit =
                new WEB3BondOrderUnit(l_bondOrderUnitParams);

            WEB3AdminBondOrderExecInfo l_actualAdminBondOrderExecInfo = null;

            l_actualAdminBondOrderExecInfo =
                l_adminBondHelperServiceImpl.toOrderExecInfo(l_bondOrderUnit);

            assertEquals("100", l_actualAdminBondOrderExecInfo.execFaceAmount);
            assertEquals("15", l_actualAdminBondOrderExecInfo.execPrice);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(ProductParams.TYPE);
                TestDBUtility.deleteAll(BondProductParams.TYPE);
                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
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
    public void testToOrderExecInfo_case0002()
    {

        String STR_METHOD_NAME = " testToOrderExecInfo_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminBondHelperServiceImpl = new WEB3AdminBondHelperServiceImpl();

        ProductParams l_productParams = TestDBUtility.getProductRow();

        BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
        l_bondProductParams.setProductId(l_productParams.getProductId());

        BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
        l_bondOrderUnitParams.setProductId(l_productParams.getProductId());
        l_bondOrderUnitParams.setOrderExecStatus(WEB3BondOrderExecStatusDef.EXECUTED);
        l_bondOrderUnitParams.setQuantity(100D);
        l_bondOrderUnitParams.setLimitPrice(15.0D);
        l_bondOrderUnitParams.setExecutedQuantity(200D);
        l_bondOrderUnitParams.setExecutedPrice(30.0D);

        try
        {
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_bondProductParams);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070801", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datOrderBizDate);

            WEB3BondOrderUnit l_bondOrderUnit =
                new WEB3BondOrderUnit(l_bondOrderUnitParams);

            WEB3AdminBondOrderExecInfo l_actualAdminBondOrderExecInfo = null;

            l_actualAdminBondOrderExecInfo =
                l_adminBondHelperServiceImpl.toOrderExecInfo(l_bondOrderUnit);

            assertEquals("200", l_actualAdminBondOrderExecInfo.execFaceAmount);
            assertEquals("30", l_actualAdminBondOrderExecInfo.execPrice);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(ProductParams.TYPE);
                TestDBUtility.deleteAll(BondProductParams.TYPE);
                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
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
    public void testToOrderExecInfo_case0003()
    {

        String STR_METHOD_NAME = " testToOrderExecInfo_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminBondHelperServiceImpl = new WEB3AdminBondHelperServiceImpl();

        ProductParams l_productParams = TestDBUtility.getProductRow();

        BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
        l_bondProductParams.setProductId(l_productParams.getProductId());

        BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
        l_bondOrderUnitParams.setProductId(l_productParams.getProductId());
        l_bondOrderUnitParams.setOrderExecStatus(WEB3BondOrderExecStatusDef.UNEXECUTED);
        l_bondOrderUnitParams.setBondType(BondTypeEnum.FOREIGN_BOND);
        l_bondOrderUnitParams.setCurrencyCode("T0");

        try
        {
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_bondProductParams);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070801", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datOrderBizDate);

            WEB3BondOrderUnit l_bondOrderUnit =
                new WEB3BondOrderUnit(l_bondOrderUnitParams);

            WEB3AdminBondOrderExecInfo l_actualAdminBondOrderExecInfo = null;

            l_actualAdminBondOrderExecInfo =
                l_adminBondHelperServiceImpl.toOrderExecInfo(l_bondOrderUnit);

            assertEquals(l_datOrderBizDate, l_actualAdminBondOrderExecInfo.domesticExecutionDate);
            assertNull(l_actualAdminBondOrderExecInfo.domesticDeliveryDate);
            assertEquals(l_datOrderBizDate, l_actualAdminBondOrderExecInfo.foreignExecutionDate);
            assertNull(l_actualAdminBondOrderExecInfo.foreignDeliveryDate);
            assertNull(l_actualAdminBondOrderExecInfo.paymentDate);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(ProductParams.TYPE);
                TestDBUtility.deleteAll(BondProductParams.TYPE);
                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
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
    public void testToOrderExecInfo_case0004()
    {

        String STR_METHOD_NAME = " testToOrderExecInfo_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminBondHelperServiceImpl = new WEB3AdminBondHelperServiceImpl();

        ProductParams l_productParams = TestDBUtility.getProductRow();

        BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
        l_bondProductParams.setProductId(l_productParams.getProductId());

        BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
        l_bondOrderUnitParams.setProductId(l_productParams.getProductId());
        l_bondOrderUnitParams.setOrderExecStatus(WEB3BondOrderExecStatusDef.EXECUTED);
        l_bondOrderUnitParams.setBondType(BondTypeEnum.FOREIGN_BOND);
        l_bondOrderUnitParams.setExecFxRate(1.25D);
        l_bondOrderUnitParams.setExecDate(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
        l_bondOrderUnitParams.setForeignExecDate(
            WEB3DateUtility.getDate("20070802", "yyyyMMdd"));
        l_bondOrderUnitParams.setDeliveryDate(
            WEB3DateUtility.getDate("20070803", "yyyyMMdd"));
        l_bondOrderUnitParams.setForeignDeliveryDate(
            WEB3DateUtility.getDate("20070804", "yyyyMMdd"));
        l_bondOrderUnitParams.setPaymentDate(
            WEB3DateUtility.getDate("20070805", "yyyyMMdd"));
        l_bondOrderUnitParams.setCurrencyCode("T0");

        try
        {
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_bondProductParams);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070801", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datOrderBizDate);

            WEB3BondOrderUnit l_bondOrderUnit =
                new WEB3BondOrderUnit(l_bondOrderUnitParams);

            WEB3AdminBondOrderExecInfo l_actualAdminBondOrderExecInfo = null;

            l_actualAdminBondOrderExecInfo =
                l_adminBondHelperServiceImpl.toOrderExecInfo(l_bondOrderUnit);

            assertEquals("1.25", l_actualAdminBondOrderExecInfo.execFxRate);
            assertEquals(WEB3DateUtility.getDate("20070801", "yyyyMMdd"),
                l_actualAdminBondOrderExecInfo.domesticExecutionDate);
            assertEquals(WEB3DateUtility.getDate("20070803", "yyyyMMdd"),
                l_actualAdminBondOrderExecInfo.domesticDeliveryDate);
            assertEquals(WEB3DateUtility.getDate("20070802", "yyyyMMdd"),
                l_actualAdminBondOrderExecInfo.foreignExecutionDate);
            assertEquals(WEB3DateUtility.getDate("20070804", "yyyyMMdd"),
                l_actualAdminBondOrderExecInfo.foreignDeliveryDate);
            assertEquals(WEB3DateUtility.getDate("20070805", "yyyyMMdd"),
                l_actualAdminBondOrderExecInfo.paymentDate);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(ProductParams.TYPE);
                TestDBUtility.deleteAll(BondProductParams.TYPE);
                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
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
    public void testToOrderExecInfo_case0005()
    {

        String STR_METHOD_NAME = " testToOrderExecInfo_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminBondHelperServiceImpl = new WEB3AdminBondHelperServiceImpl();

        ProductParams l_productParams = TestDBUtility.getProductRow();

        BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
        l_bondProductParams.setProductId(l_productParams.getProductId());

        BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
        l_bondOrderUnitParams.setProductId(l_productParams.getProductId());
        l_bondOrderUnitParams.setOrderExecStatus(WEB3BondOrderExecStatusDef.UNEXECUTED);
        l_bondOrderUnitParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
        l_bondOrderUnitParams.setExecFxRate(1.25D);
        l_bondOrderUnitParams.setExecDate(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
        l_bondOrderUnitParams.setForeignExecDate(
            WEB3DateUtility.getDate("20070802", "yyyyMMdd"));
        l_bondOrderUnitParams.setDeliveryDate(
            WEB3DateUtility.getDate("20070803", "yyyyMMdd"));
        l_bondOrderUnitParams.setForeignDeliveryDate(
            WEB3DateUtility.getDate("20070804", "yyyyMMdd"));
        l_bondOrderUnitParams.setPaymentDate(
            WEB3DateUtility.getDate("20070805", "yyyyMMdd"));
        l_bondOrderUnitParams.setCurrencyCode("T0");

        try
        {
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_bondProductParams);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070801", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datOrderBizDate);

            WEB3BondOrderUnit l_bondOrderUnit =
                new WEB3BondOrderUnit(l_bondOrderUnitParams);

            WEB3AdminBondOrderExecInfo l_actualAdminBondOrderExecInfo = null;

            l_actualAdminBondOrderExecInfo =
                l_adminBondHelperServiceImpl.toOrderExecInfo(l_bondOrderUnit);

            assertEquals("1.25", l_actualAdminBondOrderExecInfo.execFxRate);
            assertEquals(WEB3DateUtility.getDate("20070801", "yyyyMMdd"),
                l_actualAdminBondOrderExecInfo.domesticExecutionDate);
            assertEquals(WEB3DateUtility.getDate("20070803", "yyyyMMdd"),
                l_actualAdminBondOrderExecInfo.domesticDeliveryDate);
            assertEquals(WEB3DateUtility.getDate("20070802", "yyyyMMdd"),
                l_actualAdminBondOrderExecInfo.foreignExecutionDate);
            assertEquals(WEB3DateUtility.getDate("20070804", "yyyyMMdd"),
                l_actualAdminBondOrderExecInfo.foreignDeliveryDate);
            assertEquals(WEB3DateUtility.getDate("20070805", "yyyyMMdd"),
                l_actualAdminBondOrderExecInfo.paymentDate);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(ProductParams.TYPE);
                TestDBUtility.deleteAll(BondProductParams.TYPE);
                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
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
