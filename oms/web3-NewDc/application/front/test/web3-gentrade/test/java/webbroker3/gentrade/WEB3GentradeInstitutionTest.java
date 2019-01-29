head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.22.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeInstitutionTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券会社テスト(WEB3GentradeInstitutionTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/27 キョウ再平 (中訊) 新規作成
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum.IntValues;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.BranchListmarketDealtCondRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


public class WEB3GentradeInstitutionTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3GentradeInstitutionTest.class);

    public WEB3GentradeInstitutionTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
        TestDBUtility.deleteAll(InstitutionRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
        TestDBUtility.deleteAll(InstitutionRow.TYPE);
    }

    public void testGetCarryoverEndType_0001()
    {
        final String STR_METHOD_NAME = " testGetCarryoverEndType_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ProductTypeEnum l_productType = new ProductTypeEnum(6, "IFO");

            OrderexecutionEndParams l_orderexecutionEndParams =
                new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(l_productType);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setCarryoverEndType("9");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            InstitutionParams l_institutionParams =
                new InstitutionParams(TestDBUtility.getInstitutionRow());
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams.institution_id);
            assertEquals("9", l_institution.getCarryoverEndType(l_productType, "0"));
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetCarryoverEndType_0002()
    {
        final String STR_METHOD_NAME = " testGetCarryoverEndType_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ProductTypeEnum l_productType = new ProductTypeEnum(6, "IFO");

            OrderexecutionEndParams l_orderexecutionEndParams =
                new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(l_productType);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setCarryoverEndType("9");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            InstitutionParams l_institutionParams =
                new InstitutionParams(TestDBUtility.getInstitutionRow());
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams.institution_id);
            assertEquals("9", l_institution.getCarryoverEndType(l_productType, "0", "0"));
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetCarryoverEndType_0003()
    {
        final String STR_METHOD_NAME = " testGetCarryoverEndType_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ProductTypeEnum l_productType = new ProductTypeEnum(6, "IFO");

            OrderexecutionEndParams l_orderexecutionEndParams =
                new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(l_productType);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setCarryoverEndType("9");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            InstitutionParams l_institutionParams =
                new InstitutionParams(TestDBUtility.getInstitutionRow());
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams.institution_id);
            assertNull(l_institution.getCarryoverEndType(l_productType, "0", "1"));
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsOrderExecEnd_0001()
    {
        final String STR_METHOD_NAME = " testIsOrderExecEnd_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ProductTypeEnum l_productType = new ProductTypeEnum(6, "IFO");

            OrderexecutionEndParams l_orderexecutionEndParams =
                new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(l_productType);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setCarryoverEndType("9");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            InstitutionParams l_institutionParams =
                new InstitutionParams(TestDBUtility.getInstitutionRow());
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams.institution_id);
            assertEquals(true, l_institution.isOrderExecEnd(l_productType, "0"));
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsOrderExecEnd_0002()
    {
        final String STR_METHOD_NAME = " testIsOrderExecEnd_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ProductTypeEnum l_productType = new ProductTypeEnum(6, "IFO");

            OrderexecutionEndParams l_orderexecutionEndParams =
                new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(l_productType);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setCarryoverEndType("9");
            l_orderexecutionEndParams.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            InstitutionParams l_institutionParams =
                new InstitutionParams(TestDBUtility.getInstitutionRow());
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams.institution_id);
            assertEquals(false, l_institution.isOrderExecEnd(l_productType, "0"));
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsForcedSettleOrder_0001()
    {
        final String STR_METHOD_NAME = " testIsForcedSettleOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            InstitutionParams l_institutionParams =
                new InstitutionParams(TestDBUtility.getInstitutionRow());
            l_institutionParams.setForcedsettleorderDiv(null);
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams.institution_id);
            assertEquals(false, l_institution.isForcedSettleOrder());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsForcedSettleOrder_0002()
    {
        final String STR_METHOD_NAME = " testIsForcedSettleOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            InstitutionParams l_institutionParams =
                new InstitutionParams(TestDBUtility.getInstitutionRow());
            l_institutionParams.setForcedsettleorderDiv("0");
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams.institution_id);
            assertEquals(false, l_institution.isForcedSettleOrder());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsForcedSettleOrder_0003()
    {
        final String STR_METHOD_NAME = " testIsForcedSettleOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            InstitutionParams l_institutionParams =
                new InstitutionParams(TestDBUtility.getInstitutionRow());
            l_institutionParams.setForcedsettleorderDiv("1");
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams.institution_id);
            assertEquals(true, l_institution.isForcedSettleOrder());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsForcedSettleOrder_0004()
    {
        final String STR_METHOD_NAME = " testIsForcedSettleOrder_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            InstitutionParams l_institutionParams =
                new InstitutionParams(TestDBUtility.getInstitutionRow());
            l_institutionParams.setForcedsettleorderDiv("2");
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams.institution_id);
            assertEquals(true, l_institution.isForcedSettleOrder());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsForcedSettleOrder_0005()
    {
        final String STR_METHOD_NAME = " testIsForcedSettleOrder_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            InstitutionParams l_institutionParams =
                new InstitutionParams(TestDBUtility.getInstitutionRow());
            l_institutionParams.setForcedsettleorderDiv("3");
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams.institution_id);
            assertEquals(true, l_institution.isForcedSettleOrder());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
