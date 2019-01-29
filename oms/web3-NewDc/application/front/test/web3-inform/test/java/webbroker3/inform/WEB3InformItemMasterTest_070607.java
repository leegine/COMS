head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.52.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformItemMasterTest_070607.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 各種連絡項目マスタテスト(WEB3AdminInformTransferApplyFinancialInstitutionVoucherTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/07 武波(中訊) 新規作成 モデルNo.056
*/
package webbroker3.inform;

import test.util.TestDBUtility;
import webbroker3.inform.data.InformCtrlItemMasterParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (各種連絡項目マスタテスト)<BR>
 * 各種連絡項目マスタテスト
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3InformItemMasterTest_070607
    extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformItemMasterTest_070607.class);

    public WEB3InformItemMasterTest_070607(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    /**
     * setUp<BR>
     * @@param l_strName
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     * tearDown<BR>
     * @@param l_strName
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * 項目チェック方式() == ”投信銘柄ﾁｪｯｸ”<BR>
     * 項目チェック方式() != ”投信銘柄ﾁｪｯｸ”
     */
    public void test_isMutualProductCheck_C0001()
    {
        final String STR_METHOD_NAME = " test_isMutualProductCheck_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InformCtrlItemMasterParams.TYPE);
            InformCtrlItemMasterParams l_informCtrlItemMasterParams =
                TestDBUtility.getInformCtrlItemMasterRow();
            l_informCtrlItemMasterParams.setItemCheckMode("15");
            l_informCtrlItemMasterParams.setBranchCode("000");
            l_informCtrlItemMasterParams.setInstitutionCode("123");
            l_informCtrlItemMasterParams.setInformDiv("12");
            l_informCtrlItemMasterParams.setItemSymbolName("123");
            l_informCtrlItemMasterParams.setNecessaryFlag(1);
            TestDBUtility.insertWithDel(l_informCtrlItemMasterParams);
            WEB3InformItemMaster l_informItemMaster =
                new WEB3InformItemMaster("123", "000", "12", "123");
            boolean l_blnMutualProductCheck = l_informItemMaster.isMutualProductCheck();
            assertTrue(l_blnMutualProductCheck);

            TestDBUtility.deleteAll(InformCtrlItemMasterParams.TYPE);
            l_informCtrlItemMasterParams =
                TestDBUtility.getInformCtrlItemMasterRow();
            l_informCtrlItemMasterParams.setItemCheckMode("16");
            l_informCtrlItemMasterParams.setBranchCode("000");
            l_informCtrlItemMasterParams.setInstitutionCode("123");
            l_informCtrlItemMasterParams.setInformDiv("12");
            l_informCtrlItemMasterParams.setItemSymbolName("123");
            l_informCtrlItemMasterParams.setNecessaryFlag(1);
            TestDBUtility.insertWithDel(l_informCtrlItemMasterParams);
            l_informItemMaster =
                new WEB3InformItemMaster("123", "000", "12", "123");
            l_blnMutualProductCheck = l_informItemMaster.isMutualProductCheck();
            assertFalse(l_blnMutualProductCheck);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 項目チェック方式() == ”投信銘柄ﾁｪｯｸ”<BR>
     * 項目チェック方式() != ”投信銘柄ﾁｪｯｸ”
     */
    public void test_isBondProductCheck_C0002()
    {
        final String STR_METHOD_NAME = " test_isBondProductCheck_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InformCtrlItemMasterParams.TYPE);
            InformCtrlItemMasterParams l_informCtrlItemMasterParams =
                TestDBUtility.getInformCtrlItemMasterRow();
            l_informCtrlItemMasterParams.setItemCheckMode("15");
            l_informCtrlItemMasterParams.setBranchCode("000");
            l_informCtrlItemMasterParams.setInstitutionCode("123");
            l_informCtrlItemMasterParams.setInformDiv("12");
            l_informCtrlItemMasterParams.setItemSymbolName("123");
            l_informCtrlItemMasterParams.setNecessaryFlag(1);
            TestDBUtility.insertWithDel(l_informCtrlItemMasterParams);
            WEB3InformItemMaster l_informItemMaster =
                new WEB3InformItemMaster("123", "000", "12", "123");
            boolean l_blnMutualProductCheck = l_informItemMaster.isBondProductCheck();
            assertFalse(l_blnMutualProductCheck);

            TestDBUtility.deleteAll(InformCtrlItemMasterParams.TYPE);
            l_informCtrlItemMasterParams =
                TestDBUtility.getInformCtrlItemMasterRow();
            l_informCtrlItemMasterParams.setItemCheckMode("16");
            l_informCtrlItemMasterParams.setBranchCode("000");
            l_informCtrlItemMasterParams.setInstitutionCode("123");
            l_informCtrlItemMasterParams.setInformDiv("12");
            l_informCtrlItemMasterParams.setItemSymbolName("123");
            l_informCtrlItemMasterParams.setNecessaryFlag(1);
            TestDBUtility.insertWithDel(l_informCtrlItemMasterParams);
            l_informItemMaster =
                new WEB3InformItemMaster("123", "000", "12", "123");
            l_blnMutualProductCheck = l_informItemMaster.isBondProductCheck();
            assertTrue(l_blnMutualProductCheck);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}@
