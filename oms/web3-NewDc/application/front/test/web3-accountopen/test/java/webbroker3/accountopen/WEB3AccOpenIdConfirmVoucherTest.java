head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenIdConfirmVoucherTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AccOpenIdConfirmVoucherTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/19 武波 (中訊) 新規作成
*/
package webbroker3.accountopen;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.AccOpenVoucherItemParams;
import webbroker3.accountopen.data.AccOpenVoucherMasterParams;
import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherParams;
import webbroker3.accountopen.data.IdConfirmVoucherRow;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenIdConfirmVoucherTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenIdConfirmVoucherTest.class);
    public WEB3AccOpenIdConfirmVoucherTest(String arg0)
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
            WEB3AccOpenIdConfirmVoucher l_accOpenIdConfirmVoucher =
                WEB3AccOpenIdConfirmVoucher.getInstance(l_accOpenExpAccountOpen);
            l_accOpenIdConfirmVoucher.saveVoucherRow("1");
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
            TestDBUtility.deleteAll(IdConfirmVoucherRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class },"1");

            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            l_expAccountOpenParams.setEraBorn("5");
            l_expAccountOpenParams.setBornDate("010902");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);


            AccOpenVoucherItemParams l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA1);
            l_accOpenVoucherItemParams.setFixedValue("1");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA1);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);


            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.DIV);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RELATION);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RELATION);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_WAY);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_WAY);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DOC);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DOC);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ADD_CONFIRM_DOC);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADD_CONFIRM_DOC);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_CONFIRM_DATE);
            l_accOpenVoucherItemParams.setFixedValue("20090820");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_CONFIRM_DATE);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_NAME);
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_NAME);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.BORN_DATE);
            l_accOpenVoucherItemParams.setFixedValue("20090820");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.WEST_DATE_CHANGE_TO_JAP_DATE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ERA_BORN);
            l_accOpenVoucherItemParams.setInputItemSymbolName2(WEB3AccountOpenOutputItemSymbolNameDef.BORN_DATE);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE1);
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE1);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE2);
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE2);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE3);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE3);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE1);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE1);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE2);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE2);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1_KANA);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1_KANA);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2_KANA);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2_KANA);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3_KANA);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3_KANA);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AccOpenIdConfirmVoucher l_accOpenIdConfirmVoucher =
                WEB3AccOpenIdConfirmVoucher.getInstance(l_accOpenExpAccountOpen);
            l_accOpenIdConfirmVoucher.saveVoucherRow("1");
            
            String l_strWhere =
                "institution_code = ? ";

            Object l_bindVars[] =
                {"0D"};
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcesser.doFindAllQuery(
                IdConfirmVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);   
            
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
            WEB3AccOpenIdConfirmVoucher l_accOpenIdConfirmVoucher =
                WEB3AccOpenIdConfirmVoucher.getInstance(l_accOpenExpAccountOpen);
            assertEquals(0, l_accOpenIdConfirmVoucher.getConfirmedItemName().length);
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
            l_accOpenVoucherMasterParams.setRequestCode("GI839");
            l_accOpenVoucherMasterParams.setSerialNo("1");
            TestDBUtility.insertWithDel(l_accOpenVoucherMasterParams);

            AccOpenVoucherStatusParams l_accOpenVoucherStatusRow =
                new AccOpenVoucherStatusParams();
            l_accOpenVoucherStatusRow.setInstitutionCode("0D");
            l_accOpenVoucherStatusRow.setAccOpenRequestNumber(
                l_expAccountOpenParams.getAccOpenRequestNumber());
            l_accOpenVoucherStatusRow.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
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
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.DIV);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.DIV);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.RELATION);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.RELATION);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_WAY);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_WAY);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DOC);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DOC);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ADD_CONFIRM_DOC);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADD_CONFIRM_DOC);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_CONFIRM_DATE);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_CONFIRM_DATE);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_NAME);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_NAME);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.BORN_DATE);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.BORN_DATE);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE1);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE1);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE2);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE2);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE3);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE3);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE1);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE1);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE2);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE2);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1_KANA);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1_KANA);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2_KANA);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2_KANA);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            l_accOpenVoucherItemParams =
                new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setSerialNo("1");
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv("4");
            l_accOpenVoucherItemParams.setRequestCode("GI839");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(
                WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3_KANA);
            l_accOpenVoucherItemParams.setEditWayDiv("0");
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
            l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3_KANA);
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);

            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AccOpenIdConfirmVoucher l_accOpenIdConfirmVoucher =
                WEB3AccOpenIdConfirmVoucher.getInstance(l_accOpenExpAccountOpen);
            assertEquals(49, l_accOpenIdConfirmVoucher.getConfirmedItemName().length);
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
