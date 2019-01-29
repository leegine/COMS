head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.01.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondNewOrderSpecTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 拡張債券新規注文内容のテストクラス(WEB3AccOpenRealUnitServiceImplTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/08/01 周墨洋 (中訊) 仕様変更・モデルNo.234
*/

package webbroker3.bd;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (拡張債券新規注文内容)<BR>
 * 拡張債券新規注文内容のテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3BondNewOrderSpecTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3BondNewOrderSpecTest.class);

    /**
     * 拡張債券新規注文内容
     */
    WEB3BondNewOrderSpec l_bondNewOrderSpec;

    /**
     * @@param arg0
     */
    public WEB3BondNewOrderSpecTest(String arg0)
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
    public void testCreateBondNewOrderSpecDomesticBond_case0001()
    {
        String STR_METHOD_NAME = " testCreateBondNewOrderSpecDomesticBond_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();

        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        TraderParams l_traderParams = TestDBUtility.getTraderRow();

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.product_id = 3304140763000L;
        BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
        l_bondProductParams.setBuyPrice(666.54D);

        Date l_datTemp = WEB3DateUtility.getDate("20070801", "yyyyMMdd");
        l_datTemp.setHours(11);
        l_datTemp.setMinutes(12);
        l_datTemp.setSeconds(13);
        l_bondProductParams.setDeliveryDate(l_datTemp);

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {

            WEB3GentradeInstitution l_gentradeInstitution = new WEB3GentradeInstitution(l_institutionParams);
            Institution l_institution = l_gentradeInstitution;
            String l_strTraderCode = "11123";
            String l_strBranchCode = "381";
            Trader l_trader = new WEB3GentradeTrader(l_institution, l_strTraderCode, l_strBranchCode);

            OrderTypeEnum l_orderType = OrderTypeEnum.DOMESTIC_BOND_BUY;
            String l_strTrading = "987";
            WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
                new WEB3BondOrderTypeJudge(l_orderType, l_strTrading);

            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);

            BigDecimal l_bdQuantity = new BigDecimal("555.123");

            WEB3BondNewOrderSpec l_bondNewOrderSpec =
                WEB3BondNewOrderSpec.createBondNewOrderSpecDomesticBond(
                    l_trader,
                    l_bondOrderTypeJudge,
                    l_bondProduct,
                    l_bdQuantity);

            assertEquals(l_trader, l_bondNewOrderSpec.getTrader());
            assertEquals(new Long(l_traderParams.getTraderId()),
                l_bondNewOrderSpec.getTraderIdAsObject());
            assertEquals(l_datTemp, l_bondNewOrderSpec.getOrderExpDate());

            assertTrue(l_bondNewOrderSpec.isBuyOrder());
            assertEquals(l_bondProductParams.getProductCode(), l_bondNewOrderSpec.getProductCode());
            assertEquals("0", l_bondNewOrderSpec.getIssueCode());
            assertEquals(555.123D, l_bondNewOrderSpec.getQuantity(), 0.001D);
            assertEquals("0", l_bondNewOrderSpec.getMarketCode());
            assertEquals(666.54, l_bondNewOrderSpec.getLimitPrice(), 0.1D);

            assertEquals(BondExecutionConditionType.NONE, l_bondNewOrderSpec.getExecConditionType());
            assertEquals(1, l_bondNewOrderSpec.getExecConditionType().intValue());
            assertEquals("NONE", l_bondNewOrderSpec.getExecConditionType().stringValue());

            assertEquals(TaxTypeEnum.NORMAL, l_bondNewOrderSpec.getTaxType());
            assertEquals(1, l_bondNewOrderSpec.getTaxType().intValue());
            assertEquals("NORMAL", l_bondNewOrderSpec.getTaxType().stringValue());

            assertEquals(l_bondOrderTypeJudge, l_bondNewOrderSpec.getBondOrderTypeJudge());
            assertEquals(OrderTypeEnum.DOMESTIC_BOND_BUY,
                l_bondNewOrderSpec.getBondOrderTypeJudge().getOrderType());
            assertEquals(404, l_bondNewOrderSpec.getBondOrderTypeJudge().getOrderType().intValue());
            assertEquals("DOMESTIC_BOND_BUY",
                l_bondNewOrderSpec.getBondOrderTypeJudge().getOrderType().stringValue());
            assertEquals("987", l_bondNewOrderSpec.getBondOrderTypeJudge().getTrading());

            assertEquals("1", l_bondNewOrderSpec.getSettlementDiv());

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
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(TraderParams.TYPE);
                TestDBUtility.deleteAll(ProductParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
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
    public void testCreateBondNewOrderSpecDomesticBond_case0002()
    {
        String STR_METHOD_NAME = " testCreateBondNewOrderSpecDomesticBond_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
 
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
 
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
 
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
 
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
 
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_traderParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
 
            fail();
        }
 
        try
        {
 
            WEB3GentradeInstitution l_gentradeInstitution = new WEB3GentradeInstitution(l_institutionParams);
            Institution l_institution = l_gentradeInstitution;
            String l_strTraderCode = "11123";
            String l_strBranchCode = "381";
            Trader l_trader = new WEB3GentradeTrader(l_institution, l_strTraderCode, l_strBranchCode);
 
            OrderTypeEnum l_orderType = OrderTypeEnum.DOMESTIC_BOND_BUY;
            String l_strTrading = "987";
            WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
                new WEB3BondOrderTypeJudge(l_orderType, l_strTrading);
 
            WEB3BondProduct l_bondProduct = null;
 
            BigDecimal l_bdQuantity = new BigDecimal("555.123");
 
            WEB3BondNewOrderSpec l_bondNewOrderSpec =
                WEB3BondNewOrderSpec.createBondNewOrderSpecDomesticBond(
                    l_trader,
                    l_bondOrderTypeJudge,
                    l_bondProduct,
                    l_bdQuantity);
 
            log.exiting(TEST_END + STR_METHOD_NAME);
 
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
 
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
 
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(TraderParams.TYPE);
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
    public void testCreateBondNewOrderSpecDomesticBond_case0003()
    {
        String STR_METHOD_NAME = " testCreateBondNewOrderSpecDomesticBond_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
 
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
 
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
 
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
 
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.product_id = 3304140763000L;
        BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
        l_bondProductParams.setBuyPrice(666.54D);
 
        Date l_datTemp = WEB3DateUtility.getDate("20070801", "yyyyMMdd");
        l_datTemp.setHours(11);
        l_datTemp.setMinutes(12);
        l_datTemp.setSeconds(13);
        l_bondProductParams.setDeliveryDate(l_datTemp);
 
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
 
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
 
            fail();
        }
 
        try
        {
 
            WEB3GentradeInstitution l_gentradeInstitution = new WEB3GentradeInstitution(l_institutionParams);
            Institution l_institution = l_gentradeInstitution;
            String l_strTraderCode = "11123";
            String l_strBranchCode = "381";
            Trader l_trader = new WEB3GentradeTrader(l_institution, l_strTraderCode, l_strBranchCode);
 
            OrderTypeEnum l_orderType = OrderTypeEnum.DOMESTIC_BOND_BUY;
            String l_strTrading = "987";
            WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
                new WEB3BondOrderTypeJudge(l_orderType, l_strTrading);
 
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
 
            BigDecimal l_bdQuantity = null;
 
            WEB3BondNewOrderSpec l_bondNewOrderSpec =
                WEB3BondNewOrderSpec.createBondNewOrderSpecDomesticBond(
                    l_trader,
                    l_bondOrderTypeJudge,
                    l_bondProduct,
                    l_bdQuantity);
 
            log.exiting(TEST_END + STR_METHOD_NAME);
 
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
 
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
 
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(TraderParams.TYPE);
                TestDBUtility.deleteAll(ProductParams.TYPE);
                TestDBUtility.deleteAll(BondProductParams.TYPE);
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
