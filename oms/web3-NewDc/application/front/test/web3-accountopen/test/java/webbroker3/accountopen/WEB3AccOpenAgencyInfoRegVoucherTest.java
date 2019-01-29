head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenAgencyInfoRegVoucherTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AccOpenAgencyInfoRegVoucherTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 武波 (中訊) 新規作成
*/
package webbroker3.accountopen;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.AccOpenVoucherItemParams;
import webbroker3.accountopen.data.AccOpenVoucherMasterParams;
import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherParams;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenAgencyInfoRegVoucherTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenAgencyInfoRegVoucherTest.class);

    public WEB3AccOpenAgencyInfoRegVoucherTest(String arg0)
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

    public void testGetInstance_C0001()
    {
        final String STR_METHOD_NAME = "testGetInstance_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
                WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);
            assertEquals("4", l_accOpenAgencyInfoRegVoucher.getAccountDiv());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsOnlineVoucher_C0001()
    {
        final String STR_METHOD_NAME = "testIsOnlineVoucher_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
                WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);
            assertTrue(l_accOpenAgencyInfoRegVoucher.isOnlineVoucher());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetRequestCode_C0001()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
                WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);
            assertEquals("GI858", l_accOpenAgencyInfoRegVoucher.getRequestCode());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetVoucherCode_C0001()
    {
        final String STR_METHOD_NAME = "testGetVoucherCode_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
                WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);
            assertEquals("GS103", l_accOpenAgencyInfoRegVoucher.getVoucherCode());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetConfirmedItemName_C0001()
    {
        final String STR_METHOD_NAME = "testGetConfirmedItemName_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            TestDBUtility.deleteAll(AccOpenVoucherMasterParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
                WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);
            assertEquals(0, l_accOpenAgencyInfoRegVoucher.getConfirmedItemName().length);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetConfirmedItemName_C0002()
    {
        final String STR_METHOD_NAME = "testGetConfirmedItemName_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            TestDBUtility.deleteAll(AccOpenVoucherMasterParams.TYPE);
            TestDBUtility.deleteAll(AccOpenVoucherStatusParams.TYPE);
            TestDBUtility.deleteAll(AccOpenVoucherItemParams.TYPE);

            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccountOpenDate(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            AccOpenVoucherMasterParams l_accOpenVoucherMasterParams =
                new AccOpenVoucherMasterParams();
            l_accOpenVoucherMasterParams.setInstitutionCode("0D");
            l_accOpenVoucherMasterParams.setBranchCode("381");
            l_accOpenVoucherMasterParams.setAccountDiv("4");
            l_accOpenVoucherMasterParams.setRequestCode("GI858");
            l_accOpenVoucherMasterParams.setSerialNo("1");
            TestDBUtility.insertWithDel(l_accOpenVoucherMasterParams);

            AccOpenVoucherStatusParams l_accOpenVoucherStatusRow =
                new AccOpenVoucherStatusParams();
            l_accOpenVoucherStatusRow.setInstitutionCode("0D");
            l_accOpenVoucherStatusRow.setAccOpenRequestNumber(
                l_expAccountOpenParams.getAccOpenRequestNumber());
            l_accOpenVoucherStatusRow.setRequestCode("GI858");
            l_accOpenVoucherStatusRow.setSerialNo("1");
            l_accOpenVoucherStatusRow.setVoucherStatus("1");
            l_accOpenVoucherStatusRow.setSendTimestamp(GtlUtils.getSystemTimestamp());
            l_accOpenVoucherStatusRow.setRecvTimestamp(GtlUtils.getSystemTimestamp());
            l_accOpenVoucherStatusRow.setErrorCode(null);
            l_accOpenVoucherStatusRow.setLastUpdater(null);
            l_accOpenVoucherStatusRow.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_accOpenVoucherStatusRow.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_accOpenVoucherStatusRow);

            AccOpenVoucherItemParams l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_KANA);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_KANA);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_NAME1);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_NAME1);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_NAME2);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_NAME2);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_INSTITUTION);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_INSTITUTION);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_BRANCH);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_BRANCH);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_TYPE);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_TYPE);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_NO);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_NO);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
                WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);
            assertEquals(28, l_accOpenAgencyInfoRegVoucher.getConfirmedItemName().length);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSaveVoucherRow_C0001()
    {
        final String STR_METHOD_NAME = "testSaveVoucherRow_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AccOpenVoucherItemParams.TYPE);
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class },"1");

            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
                WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);
            l_accOpenAgencyInfoRegVoucher.saveVoucherRow("1");
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSaveVoucherRow_C0002()
    {
        final String STR_METHOD_NAME = "testSaveVoucherRow_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AccOpenVoucherItemParams.TYPE);
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            TestDBUtility.deleteAll(HostAgencyNotifyVoucherParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class },"1");

            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);


            AccOpenVoucherItemParams l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE);
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE);
            l_accOpenVoucherItemParams.setFixedValue("123456");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE);
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE);
            l_accOpenVoucherItemParams.setFixedValue("1234");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE);
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE);
            l_accOpenVoucherItemParams.setFixedValue("38111");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE);
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE);
            l_accOpenVoucherItemParams.setFixedValue("12345678");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE);
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE);
            l_accOpenVoucherItemParams.setFixedValue("12345678");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER);
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER);
            l_accOpenVoucherItemParams.setFixedValue("12345678");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO);
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO);
            l_accOpenVoucherItemParams.setFixedValue("1");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV);
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV);
            l_accOpenVoucherItemParams.setFixedValue("1");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_KANA);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_KANA);
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_KANA);
            l_accOpenVoucherItemParams.setFixedValue("1");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_NAME1);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_NAME1);
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_NAME1);
            l_accOpenVoucherItemParams.setFixedValue("1");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_NAME2);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_NAME2);
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_NAME2);
            l_accOpenVoucherItemParams.setFixedValue("1");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_INSTITUTION);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_INSTITUTION);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_INSTITUTION);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_BRANCH);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_BRANCH);
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_BRANCH);
            l_accOpenVoucherItemParams.setFixedValue("1");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_TYPE);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_TYPE);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_TYPE);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_NO);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_NO);
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_NO);
            l_accOpenVoucherItemParams.setFixedValue("1");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.STATUS);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.STATUS);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME2);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME2);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME1);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME1);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME_KANA2);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME_KANA2);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME_KANA1);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME_KANA1);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_POST);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_POST);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE2);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE2);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE1);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE1);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME2);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME2);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME1);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME1);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA2);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA2);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI858");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA1);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA1);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
                WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);
            l_accOpenAgencyInfoRegVoucher.saveVoucherRow("1");
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSaveDeleteVoucherRow_C0001()
    {
        final String STR_METHOD_NAME = "testSaveDeleteVoucherRow_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            TestDBUtility.deleteAll(HostAgencyNotifyVoucherParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
                WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);
            assertFalse(l_accOpenAgencyInfoRegVoucher.saveDeleteVoucherRow("1"));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSaveDeleteVoucherRow_C0002()
    {
        final String STR_METHOD_NAME = "testSaveDeleteVoucherRow_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            TestDBUtility.deleteAll(HostAgencyNotifyVoucherParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            HostAgencyNotifyVoucherParams l_hostAgencyNotifyVoucherParams =
                TestDBUtility.getHostAgencyNotifyVoucherRow();

            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            l_hostAgencyNotifyVoucherParams.setAccOpenRequestNumber(
                l_accOpenExpAccountOpen.getRequestNumber());
            l_hostAgencyNotifyVoucherParams.setStatus(WEB3StatusDef.NOT_DEAL);
            TestDBUtility.insertWithDel(l_hostAgencyNotifyVoucherParams);
            WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
                WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);
            assertFalse(!l_accOpenAgencyInfoRegVoucher.saveDeleteVoucherRow("1"));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
