head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeBizLogicProviderTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービステスト(WEB3GentradeBizLogicProviderTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/10  栄イ (中訊) 新規作成
*/
package webbroker3.gentrade;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.EquityCommAccountCondMstParams;
import webbroker3.gentrade.data.EquityCommAccountCondMstRow;
import webbroker3.gentrade.data.EquityCommCondMstParams;
import webbroker3.gentrade.data.EquityCommCondMstRow;
import webbroker3.gentrade.data.EquityCommCondParams;
import webbroker3.gentrade.data.EquityCommCondRow;
import webbroker3.gentrade.data.EquityCommRevMstParams;
import webbroker3.gentrade.data.EquityCommRevMstRow;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (計算サービステスト)<BR>
 * 
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public class WEB3GentradeBizLogicProviderTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3GentradeBizLogicProviderTest.class);

    public WEB3GentradeBizLogicProviderTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
        TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
        TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
        TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
        TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
        TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
        TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
        TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
        TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0001:
     * 手数料=null
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0001()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = null;

            SubAccountParams l_subAcctParams = new SubAccountParams();
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            assertEquals(false, true);
        }
        catch(WEB3BaseRuntimeException l_bre)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_bre.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0002:
     * 補助口座=null
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0002()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

            SubAccount l_subAccount = null;

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            assertEquals(false, true);
        }
        catch(WEB3BaseRuntimeException l_bre)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_bre.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0003:
     * 手数料条件決定FLAGを取得なし
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0003()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));

            SubAccountParams l_subAcctParams = new SubAccountParams();
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            assertEquals(false, true);
        }
        catch(WEB3SystemLayerException l_sle)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_sle.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0004:
     * 手数料No.、顧客徴収率を取得なし
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0004()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            assertEquals(false, true);
        }
        catch(WEB3SystemLayerException l_sle)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_sle.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0005:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0005()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(false);
            l_commission.setPayType("00");
            l_commission.setSonarMarketCode(null);
            l_commission.setUnderlyingProductCode(null);
            l_commission.setDayTradeType(null);
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000.1);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("1");
            l_equityCommRevMstParams.setRevision("0");
            l_equityCommRevMstParams.setPayType(" ");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setDayTradeType(" ");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("010");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("010");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setSpcEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setMaxCommission(999999);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            assertEquals(1800, l_commission.getCommission(), 0.1);
            assertEquals("01", l_commission.getCommissionNo());
            assertEquals("0", l_commission.getCommissionRevNo());
            assertEquals("02", l_commission.getCommissionCourseDiv());
            assertEquals(0, l_commission.getMinCommission(), 0.1);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0006:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0006()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(false);
            l_commission.setPayType("03");
            l_commission.setSonarMarketCode("1");
            l_commission.setUnderlyingProductCode("0005");
            l_commission.setDayTradeType("1");
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo("02");
            l_commission.setExpensesCalcAmount(234567.8);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setCommissionFeeCondFlag("L");
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel(" ");
            l_equityCommRevMstParams.setTransactionType(" ");
            l_equityCommRevMstParams.setExecCondType(" ");
            l_equityCommRevMstParams.setRevision("1");
            l_equityCommRevMstParams.setPayType("3");
            l_equityCommRevMstParams.setSonarMarketCode("1");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode("0005");
            l_equityCommRevMstParams.setDayTradeType("1");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("011");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            l_equityCommCondParams.setSequenceNo("1");
            TestDBUtility.insertWithDel(l_equityCommCondParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            assertEquals(false, true);
        }
        catch(WEB3SystemLayerException l_sle)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80004, l_sle.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0007:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0007()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(true);
            l_commission.setPayType("3");
            l_commission.setSonarMarketCode("1");
            l_commission.setUnderlyingProductCode("0005");
            l_commission.setDayTradeType("1");
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("2");
            l_equityCommRevMstParams.setRevision("2");
            l_equityCommRevMstParams.setPayType(" ");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setDayTradeType(" ");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("012");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("012");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setMaxCommission(999999);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040713","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            assertEquals(false, true);
        }
        catch(WEB3SystemLayerException l_sle)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80004, l_sle.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0008:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0008()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(true);
            l_commission.setPayType("3");
            l_commission.setSonarMarketCode("1");
            l_commission.setUnderlyingProductCode("0005");
            l_commission.setDayTradeType("1");
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1001.1);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("2");
            l_equityCommRevMstParams.setRevision("3");
            l_equityCommRevMstParams.setPayType("3");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setDayTradeType(" ");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("013");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("013");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setSpcStartDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setSpcEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setMaxCommission(999999);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            assertEquals(1801, l_commission.getCommission(), 0.1);
            assertEquals("01", l_commission.getCommissionNo());
            assertEquals("3", l_commission.getCommissionRevNo());
            assertEquals("02", l_commission.getCommissionCourseDiv());
            assertEquals(0, l_commission.getMinCommission(), 0.1);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0009:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0009()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(true);
            l_commission.setPayType("3");
            l_commission.setSonarMarketCode("1");
            l_commission.setUnderlyingProductCode("0005");
            l_commission.setDayTradeType("1");
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1011.1);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("1");
            l_equityCommRevMstParams.setRevision("3");
            l_equityCommRevMstParams.setPayType("3");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setDayTradeType(" ");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(0);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("01 ");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setSpcStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setMaxCommission(999999);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            assertEquals(2120, l_commission.getCommission(), 0.1);
            assertEquals("01", l_commission.getCommissionNo());
            assertEquals(" ", l_commission.getCommissionRevNo());
            assertEquals("02", l_commission.getCommissionCourseDiv());
            assertEquals(0, l_commission.getMinCommission(), 0.1);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0010:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0010()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(true);
            l_commission.setPayType("03");
            l_commission.setSonarMarketCode("1");
            l_commission.setUnderlyingProductCode("0005");
            l_commission.setDayTradeType("1");
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo("02");
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setCommissionFeeCondFlag("L");
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("2");
            l_equityCommRevMstParams.setRevision("3");
            l_equityCommRevMstParams.setPayType("03");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setDayTradeType(" ");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(0);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("01 ");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setSpcStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setSpcEndDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setMaxCommission(999999);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            assertEquals(2120, l_commission.getCommission(), 0.1);
            assertEquals("01", l_commission.getCommissionNo());
            assertEquals(" ", l_commission.getCommissionRevNo());
            assertEquals("02", l_commission.getCommissionCourseDiv());
            assertEquals(0, l_commission.getMinCommission(), 0.1);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0011:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0011()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(true);
            l_commission.setPayType("3");
            l_commission.setSonarMarketCode("1");
            l_commission.setUnderlyingProductCode("0005");
            l_commission.setDayTradeType("1");
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("2");
            l_equityCommRevMstParams.setRevision("3");
            l_equityCommRevMstParams.setPayType("3");
            l_equityCommRevMstParams.setSonarMarketCode("2");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setDayTradeType(" ");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("01 ");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(30000);
            l_equityCommCondMstParams.setMaxCommission(999999);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            assertEquals(2700, l_commission.getCommission(), 0.1);
            assertEquals("01", l_commission.getCommissionNo());
            assertEquals(" ", l_commission.getCommissionRevNo());
            assertEquals("02", l_commission.getCommissionCourseDiv());
            assertEquals(30000, l_commission.getMinCommission(), 0.1);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0012:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0012()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(true);
            l_commission.setPayType("3");
            l_commission.setSonarMarketCode("1");
            l_commission.setUnderlyingProductCode("0005");
            l_commission.setDayTradeType("1");
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("2");
            l_equityCommRevMstParams.setRevision("3");
            l_equityCommRevMstParams.setPayType("3");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode("0001");
            l_equityCommRevMstParams.setDayTradeType(" ");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("01 ");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setSpcStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setSpcEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setSpcChargeRatio(91.111);
            l_equityCommCondMstParams.setMaxCommission(999999);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            assertEquals(1639, l_commission.getCommission(), 0.1);
            assertEquals("01", l_commission.getCommissionNo());
            assertEquals(" ", l_commission.getCommissionRevNo());
            assertEquals("02", l_commission.getCommissionCourseDiv());
            assertEquals(0, l_commission.getMinCommission(), 0.1);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0013:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0013()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(true);
            l_commission.setPayType("3");
            l_commission.setSonarMarketCode("1");
            l_commission.setUnderlyingProductCode("0005");
            l_commission.setDayTradeType("1");
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("2");
            l_equityCommRevMstParams.setRevision("3");
            l_equityCommRevMstParams.setPayType("3");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setDayTradeType("2");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("01 ");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setSpcStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setSpcEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setSpcChargeRatio(0.91111);
            l_equityCommCondMstParams.setSpcMinCommission(21000);
            l_equityCommCondMstParams.setMaxCommission(999999);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            assertEquals(1890, l_commission.getCommission(), 0.1);
            assertEquals("01", l_commission.getCommissionNo());
            assertEquals(" ", l_commission.getCommissionRevNo());
            assertEquals("02", l_commission.getCommissionCourseDiv());
            assertEquals(0, l_commission.getMinCommission(), 0.1);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0014:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0014()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(true);
            l_commission.setPayType("3");
            l_commission.setSonarMarketCode("1");
            l_commission.setUnderlyingProductCode("0005");
            l_commission.setDayTradeType("1");
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("2");
            l_equityCommRevMstParams.setRevision("0");
            l_equityCommRevMstParams.setPayType("3");
            l_equityCommRevMstParams.setSonarMarketCode("1");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode("0005");
            l_equityCommRevMstParams.setDayTradeType("1");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            l_equityCommRevMstParams.setOrderChannel(" ");
            l_equityCommRevMstParams.setRevision("1");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType(" ");
            l_equityCommRevMstParams.setRevision("2");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType(" ");
            l_equityCommRevMstParams.setRevision("3");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            l_equityCommRevMstParams.setExecCondType("2");
            l_equityCommRevMstParams.setPayType(" ");
            l_equityCommRevMstParams.setRevision("4");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            l_equityCommRevMstParams.setPayType("3");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setRevision("5");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            l_equityCommRevMstParams.setSonarMarketCode("1");
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setRevision("6");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            l_equityCommRevMstParams.setUnderlyingProductCode("0005");
            l_equityCommRevMstParams.setDayTradeType(" ");
            l_equityCommRevMstParams.setRevision("7");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("010");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("010");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setSpcStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setSpcEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setSpcChargeRatio(0.91111);
            l_equityCommCondMstParams.setSpcMinCommission(17000);
            l_equityCommCondMstParams.setMaxCommission(999999);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            assertEquals(1530, l_commission.getCommission(), 0.1);
            assertEquals("01", l_commission.getCommissionNo());
            assertEquals("0", l_commission.getCommissionRevNo());
            assertEquals("02", l_commission.getCommissionCourseDiv());
            assertEquals(0, l_commission.getMinCommission(), 0.1);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0015:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0015()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(true);
            l_commission.setPayType("3");
            l_commission.setSonarMarketCode("1");
            l_commission.setUnderlyingProductCode("0005");
            l_commission.setDayTradeType("1");
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo("02");
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setCommissionFeeCondFlag("L");
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("2");
            l_equityCommRevMstParams.setRevision("0");
            l_equityCommRevMstParams.setPayType("3");
            l_equityCommRevMstParams.setSonarMarketCode("1");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode("0005");
            l_equityCommRevMstParams.setDayTradeType("1");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            l_equityCommRevMstParams.setOrderChannel(" ");
            l_equityCommRevMstParams.setRevision("1");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType(" ");
            l_equityCommRevMstParams.setRevision("2");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType(" ");
            l_equityCommRevMstParams.setRevision("3");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            l_equityCommRevMstParams.setExecCondType("2");
            l_equityCommRevMstParams.setPayType(" ");
            l_equityCommRevMstParams.setRevision("4");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            l_equityCommRevMstParams.setPayType("3");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setRevision("5");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            l_equityCommRevMstParams.setSonarMarketCode("1");
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setRevision("6");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            l_equityCommRevMstParams.setUnderlyingProductCode("0005");
            l_equityCommRevMstParams.setDayTradeType(" ");
            l_equityCommRevMstParams.setRevision("7");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("010");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("010");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setMaxCommission(1900);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            assertEquals(1800, l_commission.getCommission(), 0.1);
            assertEquals("01", l_commission.getCommissionNo());
            assertEquals("0", l_commission.getCommissionRevNo());
            assertEquals("02", l_commission.getCommissionCourseDiv());
            assertEquals(0, l_commission.getMinCommission(), 0.1);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0016:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0016()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(false);
            l_commission.setPayType("00");
            l_commission.setSonarMarketCode(null);
            l_commission.setUnderlyingProductCode(null);
            l_commission.setDayTradeType(null);
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo("02");
            l_commission.setOrgCommissionRevNo("0");
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setCommissionFeeCondFlag("F");
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("020");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("020");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setMaxCommission(1700);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            assertEquals(1700, l_commission.getCommission(), 0.1);
            assertEquals("02", l_commission.getCommissionNo());
            assertEquals("0", l_commission.getCommissionRevNo());
            assertEquals("02", l_commission.getCommissionCourseDiv());
            assertEquals(0, l_commission.getMinCommission(), 0.1);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0017:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0017()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(false);
            l_commission.setPayType("00");
            l_commission.setSonarMarketCode(null);
            l_commission.setUnderlyingProductCode(null);
            l_commission.setDayTradeType(null);
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("1");
            l_equityCommRevMstParams.setRevision("0");
            l_equityCommRevMstParams.setPayType(" ");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setDayTradeType(" ");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("01 ");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setMaxCommission(999999);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            assertEquals(1800, l_commission.getCommission(), 0.1);
            assertEquals("01", l_commission.getCommissionNo());
            assertEquals(" ", l_commission.getCommissionRevNo());
            assertEquals("02", l_commission.getCommissionCourseDiv());
            assertEquals(0, l_commission.getMinCommission(), 0.1);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0018:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0018()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0018()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(false);
            l_commission.setPayType("03");
            l_commission.setSonarMarketCode("1");
            l_commission.setUnderlyingProductCode("0005");
            l_commission.setDayTradeType("1");
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo("02");
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setCommissionFeeCondFlag("L");
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel(" ");
            l_equityCommRevMstParams.setTransactionType(" ");
            l_equityCommRevMstParams.setExecCondType(" ");
            l_equityCommRevMstParams.setRevision("1");
            l_equityCommRevMstParams.setPayType("3");
            l_equityCommRevMstParams.setSonarMarketCode("1");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode("0005");
            l_equityCommRevMstParams.setDayTradeType("1");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            l_equityCommCondParams.setSequenceNo("1");
            TestDBUtility.insertWithDel(l_equityCommCondParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            assertEquals(false, true);
        }
        catch(WEB3SystemLayerException l_sle)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80004, l_sle.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0019:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0019()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(true);
            l_commission.setPayType("3");
            l_commission.setSonarMarketCode("1");
            l_commission.setUnderlyingProductCode("0005");
            l_commission.setDayTradeType("1");
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("2");
            l_equityCommRevMstParams.setRevision("2");
            l_equityCommRevMstParams.setPayType(" ");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setDayTradeType(" ");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("01 ");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setMaxCommission(999999);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040713","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            assertEquals(false, true);
        }
        catch(WEB3SystemLayerException l_sle)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80004, l_sle.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0020:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0020()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0020()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(false);
            l_commission.setPayType("00");
            l_commission.setSonarMarketCode(null);
            l_commission.setUnderlyingProductCode(null);
            l_commission.setDayTradeType(null);
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("1");
            l_equityCommRevMstParams.setRevision("0");
            l_equityCommRevMstParams.setPayType(" ");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setDayTradeType(" ");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            assertEquals(false, true);
        }
        catch(WEB3SystemLayerException l_sle)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_sle.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0021:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0021()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0021()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(false);
            l_commission.setPayType("00");
            l_commission.setSonarMarketCode(null);
            l_commission.setUnderlyingProductCode(null);
            l_commission.setDayTradeType(null);
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("1");
            l_equityCommRevMstParams.setRevision("0");
            l_equityCommRevMstParams.setPayType(" ");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setDayTradeType(" ");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("010");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            assertEquals(false, true);
        }
        catch(WEB3SystemLayerException l_sle)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_sle.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0022:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0022()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0022()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(false);
            l_commission.setPayType("00");
            l_commission.setSonarMarketCode(null);
            l_commission.setUnderlyingProductCode(null);
            l_commission.setDayTradeType(null);
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("16");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("1");
            l_equityCommRevMstParams.setRevision("0");
            l_equityCommRevMstParams.setPayType(" ");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setDayTradeType(" ");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("01 ");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setMaxCommission(999999);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            assertEquals(0, l_commission.getCommission(), 0.1);
            assertEquals("01", l_commission.getCommissionNo());
            assertEquals(" ", l_commission.getCommissionRevNo());
            assertEquals("02", l_commission.getCommissionCourseDiv());
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0023:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0023()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0023()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(false);
            l_commission.setPayType("00");
            l_commission.setSonarMarketCode(null);
            l_commission.setUnderlyingProductCode(null);
            l_commission.setDayTradeType(null);
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1000.1);
            l_commission.setCommitionPerUnit(10);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("1");
            l_equityCommRevMstParams.setRevision("0");
            l_equityCommRevMstParams.setPayType(" ");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setDayTradeType(" ");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("010");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(20);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("010");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setSpcEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setMaxCommission(999999);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            assertEquals(1800, l_commission.getCommission(), 0.1);
            assertEquals("01", l_commission.getCommissionNo());
            assertEquals("0", l_commission.getCommissionRevNo());
            assertEquals("02", l_commission.getCommissionCourseDiv());
            assertEquals(0, l_commission.getMinCommission(), 0.1);
            assertEquals(20, l_commission.getCommitionPerUnit(), 0.1);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testCalcCommissionWEB3GentradeCommissionSubAccount_Case0024:
     * 
     */
    public void testCalcCommissionWEB3GentradeCommissionSubAccount_Case0024()
    {
        final String STR_METHOD_NAME = " testCalcCommissionWEB3GentradeCommissionSubAccount_Case0024()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider= new WEB3GentradeBizLogicProvider();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(10100);
            l_commission.setCommissionProductCode("10");
            l_commission.setInstitutionCode("10");
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_commission.setIsLimitPrice(true);
            l_commission.setPayType("3");
            l_commission.setSonarMarketCode("1");
            l_commission.setUnderlyingProductCode("0005");
            l_commission.setDayTradeType("1");
            l_commission.setOrderChannel("0");
            l_commission.setSonarTradedCode("11");
            l_commission.setOrgCommissionNo(null);
            l_commission.setExpensesCalcAmount(234567.8);
            l_commission.setQuantity(1011.1);
            l_commission.setCommitionPerUnit(10);

            SubAccountParams l_subAcctParams = new SubAccountParams();
            l_subAcctParams.setAccountId(101001000);
            SubAccount l_subAccount = new SubAccountImpl(l_subAcctParams);

            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommRevMstRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
            TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);

            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(10100);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_instBranchProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
            l_equityCommAccountCondMstParams.setInstitutionCode("10");
            l_equityCommAccountCondMstParams.setBranchId(10100);
            l_equityCommAccountCondMstParams.setAccountId(101001000);
            l_equityCommAccountCondMstParams.setCommProductCode("10");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20040715");
            l_equityCommAccountCondMstParams.setCommissionNo("01");
            l_equityCommAccountCondMstParams.setAccountChargeRatio(9);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            EquityCommRevMstParams l_equityCommRevMstParams = new EquityCommRevMstParams();
            l_equityCommRevMstParams.setInstitutionCode("10");
            l_equityCommRevMstParams.setCommProductCode("10");
            l_equityCommRevMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommRevMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommRevMstParams.setOrderChannel("0");
            l_equityCommRevMstParams.setTransactionType("11");
            l_equityCommRevMstParams.setExecCondType("1");
            l_equityCommRevMstParams.setRevision("3");
            l_equityCommRevMstParams.setPayType("3");
            l_equityCommRevMstParams.setSonarMarketCode(" ");
            l_equityCommRevMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommRevMstParams.setUnderlyingProductCode(" ");
            l_equityCommRevMstParams.setDayTradeType(" ");
            TestDBUtility.insertWithDel(l_equityCommRevMstParams);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("10");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("0");
            l_equityCommCondParams.setMinTurnover(23.45677);
            l_equityCommCondParams.setMaxTurnover(23.45679);
            l_equityCommCondParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondParams.setChargeRatio(10);
            l_equityCommCondParams.setAddedPrice(100.1);
            l_equityCommCondParams.setCommitionPerUnit(0);
            TestDBUtility.insertWithDel(l_equityCommCondParams);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("10");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("01 ");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_equityCommCondMstParams.setCommissionCourseDiv("02");
            l_equityCommCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_equityCommCondMstParams.setMinCommission(0);
            l_equityCommCondMstParams.setSpcStartDate(WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            l_equityCommCondMstParams.setMaxCommission(999999);
            l_equityCommCondMstParams.setSpcMaxCommission(999999);
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            assertEquals(2120, l_commission.getCommission(), 0.1);
            assertEquals("01", l_commission.getCommissionNo());
            assertEquals(" ", l_commission.getCommissionRevNo());
            assertEquals("02", l_commission.getCommissionCourseDiv());
            assertEquals(0, l_commission.getMinCommission(), 0.1);
            assertEquals(10, l_commission.getCommitionPerUnit(), 0.1);
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
