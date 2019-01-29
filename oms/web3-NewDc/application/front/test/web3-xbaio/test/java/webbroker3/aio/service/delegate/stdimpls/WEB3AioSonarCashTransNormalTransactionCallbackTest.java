head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.37.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSonarCashTransNormalTransactionCallbackTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : //TODO(WEB3AioSonarCashTransNormalTransactionCallbackTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/08 èôçGàÃ (íÜêu) êVãKçÏê¨
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.HostCashTransferParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXÉNÉâÉX//TODO
 *
 * @@author èôçGàÃ(íÜêu)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransNormalTransactionCallbackTest extends TestBaseForMock
{

    /**
     * ÉçÉOèoóÕÉÜÅ[ÉeÉBÉäÉeÉBÅB<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSonarCashTransNormalTransactionCallbackTest.class);

    WEB3AioSonarCashTransNormalTransactionCallback l_transactionCallback =
        new WEB3AioSonarCashTransNormalTransactionCallback(null);

    public WEB3AioSonarCashTransNormalTransactionCallbackTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     */
    public void testGetOrderUnit_case001()
    {
        final String STR_METHOD_NAME = ".testGetOrderUnit_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostCashTransferParams l_hostCashTransferParams = TestDBUtility.getHostCashTransferRow();
            l_hostCashTransferParams.setInstitutionCode("0D");
            l_hostCashTransferParams.setBranchCode("624");
            l_hostCashTransferParams.setAccountCode("321");
            l_hostCashTransferParams.setOrderDiv("1");
            l_hostCashTransferParams.setAmount(100);
            l_hostCashTransferParams.setCashTransDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_hostCashTransferParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //å˚ç¿ÇhÇc]
            l_subAccountParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_subAccountParams.setSubAccountId(33381251220301L);
            //ï‚èïå˚ç¿É^ÉCÉv
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            //å˚ç¿ÇhÇc]
            l_aioOrderUnitParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setBranchId(222);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_OUT);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            l_aioOrderUnitParams.setQuantity(-100);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            AioOrderUnit l_aioOrderUnit =
                l_transactionCallback.getOrderUnit(l_hostCashTransferParams);
            AioOrderUnitRow l_row = (AioOrderUnitRow)l_aioOrderUnit.getDataSourceObject();
            assertEquals(2000011L, l_row.getOrderUnitId());
            assertEquals(-100, l_row.getQuantity(), 0);
            assertEquals(333812512203L, l_row.getAccountId());
            assertEquals(222, l_row.getBranchId());
            assertEquals(33381251220301L, l_row.getSubAccountId());
            assertEquals("1001", l_row.getOrderType().intValue() + "");

        }
        catch (WEB3BaseException e)
        {
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetOrderUnit_case002()
    {
        final String STR_METHOD_NAME = ".testGetOrderUnit_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostCashTransferParams l_hostCashTransferParams = null;
            l_transactionCallback.getOrderUnit(l_hostCashTransferParams);
            fail();

        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug("Error in WEB3BaseException ...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetOrderUnit_case003()
    {
        final String STR_METHOD_NAME = ".testGetOrderUnit_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostCashTransferParams l_hostCashTransferParams = TestDBUtility.getHostCashTransferRow();
            l_hostCashTransferParams.setInstitutionCode("0D");
            l_hostCashTransferParams.setBranchCode("624");
            l_hostCashTransferParams.setAccountCode("321");
            l_hostCashTransferParams.setOrderDiv("2");
            l_hostCashTransferParams.setAmount(100);
            l_hostCashTransferParams.setCashTransDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_hostCashTransferParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //å˚ç¿ÇhÇc]
            l_subAccountParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_subAccountParams.setSubAccountId(33381251220301L);
            //ï‚èïå˚ç¿É^ÉCÉv
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            //å˚ç¿ÇhÇc]
            l_aioOrderUnitParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setBranchId(222);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            l_aioOrderUnitParams.setQuantity(-100);
            l_aioOrderUnitParams.setEstTransferDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);


            AioOrderUnit l_aioOrderUnit =
                l_transactionCallback.getOrderUnit(l_hostCashTransferParams);
            AioOrderUnitRow l_row = (AioOrderUnitRow)l_aioOrderUnit.getDataSourceObject();
            assertEquals(2000011L, l_row.getOrderUnitId());
            assertEquals(-100, l_row.getQuantity(), 0);
            assertEquals(333812512203L, l_row.getAccountId());
            assertEquals(222, l_row.getBranchId());
            assertEquals(33381251220301L, l_row.getSubAccountId());
            assertEquals("1002", l_row.getOrderType().intValue() + "");

        }
        catch (WEB3BaseException e)
        {
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * (*1)ì¸èoã‡Params.ì¸èoã‡ì˙Ç™îÒâcã∆ì˙ÇÃèÍçáÅAóÇâcã∆ì˙ÇÉZÉbÉg
     */
    public void testGetOrderUnit_case004()
    {
        final String STR_METHOD_NAME = ".testGetOrderUnit_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostCashTransferParams l_hostCashTransferParams = TestDBUtility.getHostCashTransferRow();
            l_hostCashTransferParams.setInstitutionCode("0D");
            l_hostCashTransferParams.setBranchCode("624");
            l_hostCashTransferParams.setAccountCode("321");
            l_hostCashTransferParams.setOrderDiv("2");
            l_hostCashTransferParams.setAmount(100);
            l_hostCashTransferParams.setCashTransDate(WEB3DateUtility.getDate("20040712","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_hostCashTransferParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //å˚ç¿ÇhÇc]
            l_subAccountParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_subAccountParams.setSubAccountId(33381251220301L);
            //ï‚èïå˚ç¿É^ÉCÉv
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            //å˚ç¿ÇhÇc]
            l_aioOrderUnitParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setBranchId(222);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            l_aioOrderUnitParams.setQuantity(-100);
            l_aioOrderUnitParams.setEstTransferDate(WEB3DateUtility.getDate("20040712","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            AioOrderUnit l_aioOrderUnit =
                l_transactionCallback.getOrderUnit(l_hostCashTransferParams);
            AioOrderUnitRow l_row = (AioOrderUnitRow)l_aioOrderUnit.getDataSourceObject();
            assertEquals(2000011L, l_row.getOrderUnitId());
            assertEquals(-100, l_row.getQuantity(), 0);
            assertEquals(333812512203L, l_row.getAccountId());
            assertEquals(222, l_row.getBranchId());
            assertEquals(33381251220301L, l_row.getSubAccountId());
            assertEquals("1002", l_row.getOrderType().intValue() + "");

        }
        catch (WEB3BaseException e)
        {
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
