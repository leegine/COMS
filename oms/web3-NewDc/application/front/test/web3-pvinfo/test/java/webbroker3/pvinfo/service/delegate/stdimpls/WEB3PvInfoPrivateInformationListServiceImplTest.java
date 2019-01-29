head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.40.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3PvInfoPrivateInformationListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3PvInfoPrivateInformationListServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/27 関博 新規作成
Revision History : 2007/07/17 謝旋 謝旋(中訊) 仕様変更モデル082
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3PvInfoConditionDef;
import webbroker3.common.define.WEB3PvInfoDisplayDeviceDef;
import webbroker3.common.define.WEB3PvInfoEffectiveFlagDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.data.DisplayContentsRow;
import webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionRequest;
import webbroker3.util.WEB3LogUtility;

public class WEB3PvInfoPrivateInformationListServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PvInfoPrivateInformationListServiceImplTest.class);
    
    WEB3PvInfoPrivateInformationListServiceImpl l_impl =
        new WEB3PvInfoPrivateInformationListServiceImpl();

    public WEB3PvInfoPrivateInformationListServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    
    public void testIsDisplayObject_C0002()
    {
        final String STR_METHOD_NAME = " testIsDisplayObject_C0002";
        log.entering(STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);
            l_mainAccountParams.setOnlyMobileOpenDiv("1");

            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_mainAccountParams.getRowType());
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(10100L);
            l_subAccountParams.setSubAccountId(101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1034");
            TestDBUtility.insertWithDel(l_displayContentsParams);

            assertTrue(l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsDisplayObject_C0003()
    {
        final String STR_METHOD_NAME = " testIsDisplayObject_C0003";
        log.entering(STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);
            l_mainAccountParams.setOnlyMobileOpenDiv("2");

            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_mainAccountParams.getRowType());
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(10100L);
            l_subAccountParams.setSubAccountId(101001L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1035");
            TestDBUtility.insertWithDel(l_displayContentsParams);

            assertTrue(l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

//    /**
//     * 表示条件番号： 1036（証券担保ローン口座開設）
//     * true
//     */
//    public void testIsDisplayObject_C0004()
//    {
//        final String STR_METHOD_NAME = " testIsDisplayObject_C0004";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            TestDBUtility.deleteAll(SubAccountParams.TYPE);
//            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
//            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
//            
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(10100L);
//            l_mainAccountParams.setOnlyMobileOpenDiv("2");
//            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
//
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            l_processor.doDeleteAllQuery(l_mainAccountParams.getRowType());
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
//
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(10100L);
//            l_subAccountParams.setSubAccountId(101001L);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
//
//            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
//            l_displayContentsParams.setConditionNo("1036");
//            TestDBUtility.insertWithDel(l_displayContentsParams);
//            
//            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
//            l_stockSecuredLoanParams.setAccountId(10100L);
//            l_stockSecuredLoanParams.setAccountOpenStatus("2");
//            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
//
//            assertTrue(l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams));
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * 表示条件番号： 1036（証券担保ローン口座開設）
//     * false
//     */
//    public void testIsDisplayObject_C0005()
//    {
//        final String STR_METHOD_NAME = " testIsDisplayObject_C0005";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            TestDBUtility.deleteAll(SubAccountParams.TYPE);
//            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
//            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
//            
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(10100L);
//            l_mainAccountParams.setOnlyMobileOpenDiv("2");
//            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
//
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            l_processor.doDeleteAllQuery(l_mainAccountParams.getRowType());
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
//
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(10100L);
//            l_subAccountParams.setSubAccountId(101001L);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
//
//            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
//            l_displayContentsParams.setConditionNo("1036");
//            TestDBUtility.insertWithDel(l_displayContentsParams);
//            
//            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
//            l_stockSecuredLoanParams.setAccountId(10100L);
//            l_stockSecuredLoanParams.setAccountOpenStatus("2");
//            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
//
//            assertFalse(l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams));
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
    /**
     * パラメータ.表示条件番号一覧 = null<BR>
     */
    public void testCreateQueryStringForDispContents_C0001()
    {
        final String STR_METHOD_NAME = " testCreateQueryStringForDispContents_C0001";
        log.entering(STR_METHOD_NAME);
        try
        {
            String[] l_strDispConditonNoList = null;

            String l_strQueryStringForDispContents =
                "( institution_code = ? and "
                + "effective_flag = ? and "
                + "(to_char(term_from, 'YYYYMMDDHH24') <= ? or term_from is null) and "
                + "(to_char(term_to, 'YYYYMMDDHH24') >= ? or term_to is null) and "
                + "display_device in (?, ?) " + ")";

            Hashtable l_htBrowseHistory = new Hashtable();
            String l_strQuery =
                l_impl.createQueryStringForDispContents(l_strDispConditonNoList, l_htBrowseHistory);
            assertEquals(l_strQueryStringForDispContents, l_strQuery);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * パラメータ.表示条件番号一覧 != null<BR>
     * パラメータ.閲覧履歴Tbl = null
     */
    public void testCreateQueryStringForDispContents_C0002()
    {
        final String STR_METHOD_NAME = " testCreateQueryStringForDispContents_C0002";
        log.entering(STR_METHOD_NAME);
        try
        {
            String[] l_strDispConditonNoList = {"1002", WEB3PvInfoConditionDef.DIRECT_ASSIGN};

            String l_strQueryStringForDispContents =
                "( institution_code = ? and "
                + "effective_flag = ? and "
                + "(to_char(term_from, 'YYYYMMDDHH24') <= ? or term_from is null) and "
                + "(to_char(term_to, 'YYYYMMDDHH24') >= ? or term_to is null) and "
                + "display_device in (?, ?) "
                + " and condition_no in ("
                + "?"
                + ")"
                + ")";

            Hashtable l_htBrowseHistory = null;
            String l_strQuery =
                l_impl.createQueryStringForDispContents(l_strDispConditonNoList, l_htBrowseHistory);
            assertEquals(l_strQueryStringForDispContents, l_strQuery);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * パラメータ.表示条件番号一覧 != null<BR>
     * パラメータ.閲覧履歴Tbl != null
     */
    public void testCreateQueryStringForDispContents_C0003()
    {
        final String STR_METHOD_NAME = " testCreateQueryStringForDispContents_C0003";
        log.entering(STR_METHOD_NAME);
        try
        {
            String[] l_strDispConditonNoList = {"1002", "1003"};

            String l_strQueryStringForDispContents =
                "( institution_code = ? and "
                + "effective_flag = ? and "
                + "(to_char(term_from, 'YYYYMMDDHH24') <= ? or term_from is null) and "
                + "(to_char(term_to, 'YYYYMMDDHH24') >= ? or term_to is null) and "
                + "display_device in (?, ?) "
                + " and condition_no in ("
                + "?"
                + ", ?"
                + ")";

            l_strQueryStringForDispContents += ") or ( effective_flag = ? and "
                + "(to_char(term_from, 'YYYYMMDDHH24') <= ? or term_from is null) and "
                + "(to_char(term_to, 'YYYYMMDDHH24') >= ? or term_to is null) and "
                + "display_device in (?, ?) and "
                + "condition_no = '0000' and "
                + "display_contents_id in (";

            l_strQueryStringForDispContents += "?";
            l_strQueryStringForDispContents += ", ?";
            l_strQueryStringForDispContents += ", ?";
            l_strQueryStringForDispContents += ")";
            l_strQueryStringForDispContents += ")";

            Hashtable l_htBrowseHistory = new Hashtable();
            l_htBrowseHistory.put("1", "1");
            l_htBrowseHistory.put("2", "2");
            l_htBrowseHistory.put("3", "3");
            String l_strQuery =
                l_impl.createQueryStringForDispContents(l_strDispConditonNoList, l_htBrowseHistory);
            assertEquals(l_strQueryStringForDispContents, l_strQuery);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * this.getログインチャネル()の戻り値 == "3：モバイル"の場合<BR>
     * パラメータ.閲覧履歴Tbl = nullの場合
     */
    public void testCreateQueryDataContainerForDispContents_C0001()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainerForDispContents_C0001";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.MOBILE);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
            String[] l_strDispConditionNoList = {"1002", WEB3PvInfoConditionDef.DIRECT_ASSIGN};
            Hashtable l_htBrowseHistory = null;
            String[] l_strQueryDataContainerForDispContents =
                l_impl.createQueryDataContainerForDispContents(
                    l_mainAccount, l_strDispConditionNoList, l_htBrowseHistory);
            assertEquals("0D", l_strQueryDataContainerForDispContents[0]);
            assertEquals(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES, l_strQueryDataContainerForDispContents[1]);
            assertEquals(WEB3PvInfoDisplayDeviceDef.DEFAULT_DEVICE,
                l_strQueryDataContainerForDispContents[4]);
            assertEquals(WEB3PvInfoDisplayDeviceDef.MOBILE_DEVICE,
                l_strQueryDataContainerForDispContents[5]);
            assertEquals("1002", l_strQueryDataContainerForDispContents[6]);

            String l_strMethodReturnValue = (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class}).asObject();
            assertEquals("3", l_strMethodReturnValue);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * this.getログインチャネル()の戻り値 == "3：モバイル"以外の場合<BR>
     * パラメータ.閲覧履歴Tbl != nullの場合
     */
    public void testCreateQueryDataContainerForDispContents_C0002()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainerForDispContents_C0002";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.BRANCH);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
            String[] l_strDispConditionNoList = {"1002", WEB3PvInfoConditionDef.DIRECT_ASSIGN};
            Hashtable l_htBrowseHistory = new Hashtable();
            l_htBrowseHistory.put("1", "1");
            l_htBrowseHistory.put("2", "2");
            l_htBrowseHistory.put("3", "3");

            String[] l_strQueryDataContainerForDispContents =
                l_impl.createQueryDataContainerForDispContents(
                    l_mainAccount, l_strDispConditionNoList, l_htBrowseHistory);
            assertEquals("0D", l_strQueryDataContainerForDispContents[0]);
            assertEquals(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES, l_strQueryDataContainerForDispContents[1]);
            assertEquals(WEB3PvInfoDisplayDeviceDef.DEFAULT_DEVICE,
                l_strQueryDataContainerForDispContents[4]);
            assertEquals(WEB3PvInfoDisplayDeviceDef.PC_DEVICE,
                l_strQueryDataContainerForDispContents[5]);
            assertEquals("1002", l_strQueryDataContainerForDispContents[6]);
            assertEquals(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES, l_strQueryDataContainerForDispContents[7]);
            assertEquals(WEB3PvInfoDisplayDeviceDef.DEFAULT_DEVICE,
                l_strQueryDataContainerForDispContents[10]);
            assertEquals(WEB3PvInfoDisplayDeviceDef.PC_DEVICE,
                    l_strQueryDataContainerForDispContents[11]);
            assertEquals("3",
                    l_strQueryDataContainerForDispContents[12]);
            assertEquals("2",
                    l_strQueryDataContainerForDispContents[13]);
            assertEquals("1",
                    l_strQueryDataContainerForDispContents[14]);

            String l_strMethodReturnValue = (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class}).asObject();
            assertEquals("0", l_strMethodReturnValue);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * this.getログインチャネル()の戻り値 == "3：モバイル"の場合<BR>
     * パラメータ.閲覧履歴Tbl = nullの場合
     */
    public void testCreateQueryDataContainerForDispContents_C0003()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainerForDispContents_C0003";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.MOBILE);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
            String[] l_strDispConditionNoList = {"1002", WEB3PvInfoConditionDef.DIRECT_ASSIGN};
            Hashtable l_htBrowseHistory = new Hashtable();
            l_htBrowseHistory.put("1", "1");
            l_htBrowseHistory.put("2", "2");
            l_htBrowseHistory.put("3", "3");

            String[] l_strQueryDataContainerForDispContents =
                l_impl.createQueryDataContainerForDispContents(
                    l_mainAccount, l_strDispConditionNoList, l_htBrowseHistory);
            assertEquals("0D", l_strQueryDataContainerForDispContents[0]);
            assertEquals(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES, l_strQueryDataContainerForDispContents[1]);
            assertEquals(WEB3PvInfoDisplayDeviceDef.DEFAULT_DEVICE,
                l_strQueryDataContainerForDispContents[4]);
            assertEquals(WEB3PvInfoDisplayDeviceDef.MOBILE_DEVICE,
                l_strQueryDataContainerForDispContents[5]);
            assertEquals("1002", l_strQueryDataContainerForDispContents[6]);
            assertEquals(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES, l_strQueryDataContainerForDispContents[7]);
            assertEquals(WEB3PvInfoDisplayDeviceDef.DEFAULT_DEVICE,
                l_strQueryDataContainerForDispContents[10]);
            assertEquals(WEB3PvInfoDisplayDeviceDef.MOBILE_DEVICE,
                    l_strQueryDataContainerForDispContents[11]);
            assertEquals("3",
                    l_strQueryDataContainerForDispContents[12]);
            assertEquals("2",
                    l_strQueryDataContainerForDispContents[13]);
            assertEquals("1",
                    l_strQueryDataContainerForDispContents[14]);

            String l_strMethodReturnValue = (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class}).asObject();
            assertEquals("3", l_strMethodReturnValue);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateQueryDataContainerForDispContentsCreateQueryStringForDispContents()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainerForDispContentsCreateQueryStringForDispContents";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.MOBILE);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
            String[] l_strDispConditionNoList = {"1002", WEB3PvInfoConditionDef.DIRECT_ASSIGN};
            Hashtable l_htBrowseHistory = new Hashtable();
            l_htBrowseHistory.put("1", "1");
            l_htBrowseHistory.put("2", "2");
            l_htBrowseHistory.put("3", "3");

            String[] l_strQueryDataContainerForDispContents =
                l_impl.createQueryDataContainerForDispContents(
                    l_mainAccount, l_strDispConditionNoList, l_htBrowseHistory);

            String l_strQuery =
                l_impl.createQueryStringForDispContents(l_strDispConditionNoList, l_htBrowseHistory);

            Timestamp l_tsNowDateTime =
                (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setInstitutionCode("0D");
            l_displayContentsParams.setEffectiveFlag("0");
            l_displayContentsParams.setTermFrom(null);
            l_displayContentsParams.setTermTo(null);
            l_displayContentsParams.setDisplayDevice("0");
            l_displayContentsParams.setConditionNo("1002");
            l_displayContentsParams.setDisplayContentsId(1L);
            l_displayContentsParams.setBranchCode("123");
            TestDBUtility.insertWithDel(l_displayContentsParams);

            List l_lisDisplayContentsParams = null;
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisDisplayContentsParams = l_queryProcessor.doFindAllQuery(
                DisplayContentsRow.TYPE,
                l_strQuery,
                l_strQueryDataContainerForDispContents);
            assertEquals("123", ((DisplayContentsRow)l_lisDisplayContentsParams.get(0)).getBranchCode());
            assertFalse(l_lisDisplayContentsParams.isEmpty());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //getAccountConnectionScreen
    public void testgetAccountConnectionScreen()
    {
        final String STR_METHOD_NAME = " testgetAccountConnectionScreen";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.MOBILE);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setAccountCode("333333");
            l_mainAccountParams.setBranchCode("123");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParmas = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParmas.setInstitutionCode("0D");
            l_tradingTimeParmas.setBranchCode("123");
            l_tradingTimeParmas.setMarketCode("N1");
            l_tradingTimeParmas.setTradingTimeType("01");
            l_tradingTimeParmas.setProductCode("0");
            l_tradingTimeParmas.setBizDateType("1");
            l_tradingTimeParmas.setStartTime("000000");
            l_tradingTimeParmas.setEndTime("235959");
            l_tradingTimeParmas.setSubmitMarketTrigger("1");
            l_tradingTimeParmas.setEnableOrder("0");
            l_tradingTimeParmas.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParmas);

            WEB3PvInfoAccountConnectionRequest l_request = new WEB3PvInfoAccountConnectionRequest();
            l_request.pageIndex = "15";
            l_request.pageSize = "3";
            l_impl.getAccountConnectionScreen(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetManagedOutDispCondNoList_0001()
    {
        final String STR_METHOD_NAME = " testGetManagedOutDispCondNoList()";
        log.entering(STR_METHOD_NAME);

        WEB3PvInfoPrivateInformationListServiceImpl l_pvInfoPrivateInformationListServiceImpl =
            new WEB3PvInfoPrivateInformationListServiceImpl();
        String[] l_strManagedOutDispCondNos =
            l_pvInfoPrivateInformationListServiceImpl.getManagedOutDispCondNoList();
        
        int l_intManagedOutDispCondNo = l_strManagedOutDispCondNos.length;
        
        assertEquals("1037", l_strManagedOutDispCondNos[l_intManagedOutDispCondNo-5]);
        assertEquals("1036", l_strManagedOutDispCondNos[l_intManagedOutDispCondNo-6]);
        assertEquals("1035", l_strManagedOutDispCondNos[l_intManagedOutDispCondNo-7]);
    }
    
    public void testGetManagedOutDispCondNoList_0002()
    {
        final String STR_METHOD_NAME = " testGetManagedOutDispCondNoList_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3PvInfoPrivateInformationListServiceImpl l_pvInfoPrivateInformationListServiceImpl =
            new WEB3PvInfoPrivateInformationListServiceImpl();
        String[] l_strManagedOutDispCondNos =
            l_pvInfoPrivateInformationListServiceImpl.getManagedOutDispCondNoList();
        
        int l_intManagedOutDispCondNo = l_strManagedOutDispCondNos.length;
        
        assertEquals("1037", l_strManagedOutDispCondNos[l_intManagedOutDispCondNo-5]);
    }
    
    public void testGetManagedOutDispCondNoList_0003()
    {
        final String STR_METHOD_NAME = " testGetManagedOutDispCondNoList_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3PvInfoPrivateInformationListServiceImpl l_pvInfoPrivateInformationListServiceImpl =
            new WEB3PvInfoPrivateInformationListServiceImpl();
        String[] l_strManagedOutDispCondNos =
            l_pvInfoPrivateInformationListServiceImpl.getManagedOutDispCondNoList();
        
        int l_intManagedOutDispCondNo = l_strManagedOutDispCondNos.length;
        
        assertEquals("1039", l_strManagedOutDispCondNos[l_intManagedOutDispCondNo-3]);
        assertEquals("1038", l_strManagedOutDispCondNos[l_intManagedOutDispCondNo-4]);
    }

    public void testGetManagedOutDispCondNoList_0004()
    {
        final String STR_METHOD_NAME = "testGetManagedOutDispCondNoList_0004()";
        log.entering(STR_METHOD_NAME);
        try{
            WEB3PvInfoPrivateInformationListServiceImpl l_impl =
                new WEB3PvInfoPrivateInformationListServiceImpl();
            String[] l_strManageOutNos = l_impl.getManagedOutDispCondNoList();
            
            int l_intManagedOutNoLength = l_strManageOutNos.length;
            
            assertEquals(36,l_intManagedOutNoLength);
            assertEquals("1059",l_strManageOutNos[l_intManagedOutNoLength-1]);
            assertEquals("1058",l_strManageOutNos[l_intManagedOutNoLength-2]);
            assertEquals("1039", l_strManageOutNos[l_intManagedOutNoLength-3]);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 表示条件番号：1053（追証請求＆信用口座開設）
     * return false
     *
     */
    public void testIsDisplayObjectC0002()
    {
        final String STR_METHOD_NAME = " testIsDisplayObjectC0002";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);

//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
//            "isAdditionalDepositRequest", 
//            new Class[]{ WEB3GentradeMainAccount.class },
//            Boolean.TRUE);
        
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);

            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_mainAccountParams.getRowType());
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(10100L);
            l_subAccountParams.setSubAccountId(101001L);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1053");
            TestDBUtility.deleteAll(DisplayContentsRow.TYPE);
            TestDBUtility.insertWithDel(l_displayContentsParams);

            assertFalse(l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams));
            
//            WEB3MockObjectParamsValue l_paramsValue1 = 
//                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue("webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
//                    "isAdditionalDepositRequest", 
//                    new Class[]{ WEB3GentradeMainAccount.class });
//            assertEquals(10100L,((WEB3GentradeMainAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }  

    
    /**
     * 表示条件番号： 1037（書面交付日より11ヶ月経過）
     * return false
     */
    public void testIsDisplayObjectC0005()
    {
        final String STR_METHOD_NAME = " testIsDisplayObjectC0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(10100L);
            l_subAccountParams.setSubAccountId(101001L);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1037");
            TestDBUtility.deleteAll(DisplayContentsRow.TYPE);
            TestDBUtility.insertWithDel(l_displayContentsParams);
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);

            assertFalse(l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams));
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 表示条件番号： 1038 (PTS口座開設)
     * return true
     */
    public void testIsDisplayObjectC0006()
    {
        final String STR_METHOD_NAME = " testIsDisplayObjectC0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(10100L);
            l_subAccountParams.setSubAccountId(101001L);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1038");
            TestDBUtility.deleteAll(DisplayContentsRow.TYPE);
            TestDBUtility.insertWithDel(l_displayContentsParams);
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);

            assertTrue(l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams));
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 表示条件番号： 1039（PTS口座未開設）
     * return true
     */
    public void testIsDisplayObjectC0007()
    {
        final String STR_METHOD_NAME = " testIsDisplayObjectC0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setPtsAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(10100L);
            l_subAccountParams.setSubAccountId(101001L);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1039");
            TestDBUtility.deleteAll(DisplayContentsRow.TYPE);
            TestDBUtility.insertWithDel(l_displayContentsParams);
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);

            assertTrue(l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams));
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 表示条件番号： 1058（CFD口座開設）and isCFD口座開設() == true
     * return true
     */
    public void testIsDisplayObjectC0008()
    {
        final String STR_METHOD_NAME = "testIsDisplayObjectC0008()";
        log.entering(STR_METHOD_NAME);
        
        //MainAccountParams
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setCfdAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            //DisplayContentsParams
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1058");
            TestDBUtility.insertWithDel(l_displayContentsParams);
            
            WEB3PvInfoPrivateInformationListServiceImpl l_impl =
                new WEB3PvInfoPrivateInformationListServiceImpl();
            boolean l_blnReturn = l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams);
            assertEquals(true,l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
       
        log.exiting(STR_METHOD_NAME);
        
    }
    
    
    /**
     * 表示条件番号： 1058（CFD口座開設）and isCFD口座開設() == false
     * return true
     */
    public void testIsDisplayObjectC0010()
    {
        final String STR_METHOD_NAME = "testIsDisplayObjectC0010()";
        log.entering(STR_METHOD_NAME);
        
        //MainAccountParams
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setCfdAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            //DisplayContentsParams
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1058");
            TestDBUtility.insertWithDel(l_displayContentsParams);
            
            WEB3PvInfoPrivateInformationListServiceImpl l_impl =
                new WEB3PvInfoPrivateInformationListServiceImpl();
            boolean l_blnReturn = l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams);
            assertEquals(false,l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
       
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * 表示条件番号： 1059（CFD口座未開設）and isCFD口座開設() == false
     * return true
     */
    public void testIsDisplayObjectC0009()
    {
        final String STR_METHOD_NAME = "testIsDisplayObjectC0009()";
        log.entering(STR_METHOD_NAME);
        
        //MainAccountParams
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setCfdAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            //DisplayContentsParams
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1059");
            TestDBUtility.insertWithDel(l_displayContentsParams);
            
            WEB3PvInfoPrivateInformationListServiceImpl l_impl =
                new WEB3PvInfoPrivateInformationListServiceImpl();
            boolean l_blnReturn = l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams);
            assertEquals(true,l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
       
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * 表示条件番号： 1059（CFD口座未開設）and isCFD口座開設() == true
     * return true
     */
    
    public void testIsDisplayObjectC0011()
    {
        final String STR_METHOD_NAME = "testIsDisplayObjectC0011()";
        log.entering(STR_METHOD_NAME);
        
        //MainAccountParams
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setCfdAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            //DisplayContentsParams
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1059");
            TestDBUtility.insertWithDel(l_displayContentsParams);
            
            WEB3PvInfoPrivateInformationListServiceImpl l_impl =
                new WEB3PvInfoPrivateInformationListServiceImpl();
            boolean l_blnReturn = l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams);
            assertEquals(false,l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
       
        log.exiting(STR_METHOD_NAME);
        
    }

    /**
     * 表示条件番号：1054（不足金発生＆信用口座未開設）
     * get不足金発生状況() == "1"
     * return true
     */
    public void testIsDisplayObjectC00012()
    {
        final String STR_METHOD_NAME = "testIsDisplayObjectC00012()";
        log.entering(STR_METHOD_NAME);
        
        //MainAccountParams
        try
        {
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
//                    "getLackCashOccurSituation",
//    				new Class[] { WEB3GentradeMainAccount.class },
//                    "1");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getLackCashOccurSituation", new Class[]
                    { MainAccount.class },
                    "1");

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setCfdAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            //DisplayContentsParams
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1054");
            TestDBUtility.insertWithDel(l_displayContentsParams);
            
            WEB3PvInfoPrivateInformationListServiceImpl l_impl =
                new WEB3PvInfoPrivateInformationListServiceImpl();
           
            boolean l_blnReturn = l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams);
            
            assertEquals(true,l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
       
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 表示条件番号：1054（不足金発生＆信用口座未開設）
     * get不足金発生状況() != "1"
     * return false
     */
    public void testIsDisplayObjectC00013()
    {
        final String STR_METHOD_NAME = "testIsDisplayObjectC00013()";
        log.entering(STR_METHOD_NAME);
        
        //MainAccountParams
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getLackCashOccurSituation",
    				new Class[] { MainAccount.class },
                    "3");
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setCfdAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            //DisplayContentsParams
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1054");
            TestDBUtility.insertWithDel(l_displayContentsParams);
            
            WEB3PvInfoPrivateInformationListServiceImpl l_impl =
                new WEB3PvInfoPrivateInformationListServiceImpl();
           
            boolean l_blnReturn = l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams);
            
            assertFalse(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
       
        log.exiting(STR_METHOD_NAME);
    }
    

    /**
     * 表示条件番号：1055（不足金発生＆信用口座開設）
     * get不足金発生状況() == "2"
     * return true
     */
    public void testIsDisplayObjectC00014()
    {
        final String STR_METHOD_NAME = "testIsDisplayObjectC00014()";
        log.entering(STR_METHOD_NAME);
        
        //MainAccountParams
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getLackCashOccurSituation",
    				new Class[] { MainAccount.class },
                    "2");
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setCfdAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            //DisplayContentsParams
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1055");
            TestDBUtility.insertWithDel(l_displayContentsParams);
            
            WEB3PvInfoPrivateInformationListServiceImpl l_impl =
                new WEB3PvInfoPrivateInformationListServiceImpl();
           
            boolean l_blnReturn = l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams);
            
            assertEquals(true,l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
       
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 表示条件番号：1055（不足金発生＆信用口座開設）
     * get不足金発生状況() != "2"
     * return false
     */
    public void testIsDisplayObjectC00015()
    {
        final String STR_METHOD_NAME = "testIsDisplayObjectC00015()";
        log.entering(STR_METHOD_NAME);
        
        //MainAccountParams
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getLackCashOccurSituation",
    				new Class[] { MainAccount.class },
                    "3");
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setCfdAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            //DisplayContentsParams
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1055");
            TestDBUtility.insertWithDel(l_displayContentsParams);
            
            WEB3PvInfoPrivateInformationListServiceImpl l_impl =
                new WEB3PvInfoPrivateInformationListServiceImpl();
           
            boolean l_blnReturn = l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams);
            
            assertEquals(false,l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
       
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 表示条件番号：1056（第一水準追証発生）
     *  get追証発生状況() == "1"
     * return true
     */
    public void testIsDisplayObjectC00016()
    {
        final String STR_METHOD_NAME = "testIsDisplayObjectC00016()";
        log.entering(STR_METHOD_NAME);
        
        //MainAccountParams
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdditionalMarginSituation",
    				new Class[] { MainAccount.class },
                    "1");
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setCfdAccOpenDiv("2");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            //DisplayContentsParams
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1056");
            TestDBUtility.insertWithDel(l_displayContentsParams);
            
            WEB3PvInfoPrivateInformationListServiceImpl l_impl =
                new WEB3PvInfoPrivateInformationListServiceImpl();
           
            boolean l_blnReturn = l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams);
            
            assertEquals(true,l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
       
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 表示条件番号：1056（第一水準追証発生）
     *  get追証発生状況() != "1"
     * return true
     */
    public void testIsDisplayObjectC00017()
    {
        final String STR_METHOD_NAME = "testIsDisplayObjectC00017()";
        log.entering(STR_METHOD_NAME);
        
        //MainAccountParams
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdditionalMarginSituation",
    				new Class[] { MainAccount.class },
                    "3");
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setCfdAccOpenDiv("2");
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            //DisplayContentsParams
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1056");
            TestDBUtility.insertWithDel(l_displayContentsParams);
            
            WEB3PvInfoPrivateInformationListServiceImpl l_impl =
                new WEB3PvInfoPrivateInformationListServiceImpl();
           
            boolean l_blnReturn = l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams);
            
            assertEquals(false,l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
       
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 表示条件番号：1057（第二水準追証発生）
     *  get追証発生状況() == "2"
     * return true
     */
    public void testIsDisplayObjectC00018()
    {
        final String STR_METHOD_NAME = "testIsDisplayObjectC00018()";
        log.entering(STR_METHOD_NAME);
        
        //MainAccountParams
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdditionalMarginSituation",
    				new Class[] { MainAccount.class },
                    "2");
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setCfdAccOpenDiv("2");
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            //DisplayContentsParams
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1057");
            TestDBUtility.insertWithDel(l_displayContentsParams);
            
            WEB3PvInfoPrivateInformationListServiceImpl l_impl =
                new WEB3PvInfoPrivateInformationListServiceImpl();
           
            boolean l_blnReturn = l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams);
            
            assertEquals(true,l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
       
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 表示条件番号：1057（第二水準追証発生）
     *  get追証発生状況() != "2"
     * return true
     */
    public void testIsDisplayObjectC00019()
    {
        final String STR_METHOD_NAME = "testIsDisplayObjectC00019()";
        log.entering(STR_METHOD_NAME);
        
        //MainAccountParams
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdditionalMarginSituation",
    				new Class[] { MainAccount.class },
                    "3");
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setCfdAccOpenDiv("2");
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            //DisplayContentsParams
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            DisplayContentsParams l_displayContentsParams = TestDBUtility.getDisplayContentsRow();
            l_displayContentsParams.setConditionNo("1057");
            TestDBUtility.insertWithDel(l_displayContentsParams);
            
            WEB3PvInfoPrivateInformationListServiceImpl l_impl =
                new WEB3PvInfoPrivateInformationListServiceImpl();
           
            boolean l_blnReturn = l_impl.isDisplayObject(l_mainAccount,l_subAccount,l_displayContentsParams);
            
            assertEquals(false,l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
       
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * "1054"（不足金発生＆信用口座未開設）
     * "1055"（不足金発生＆信用口座開設）
     * "1056"（第一水準追証発生）
     * "1057"（第二水準追証発生）
     */
    public void testGetManagedDispCondNoList_Case0001()
    {
        final String STR_METHOD_NAME = "testGetManagedDispCondNoList_Case0001()";
        log.entering(STR_METHOD_NAME);
        List l_lisArrayLists = new ArrayList();
        //返却する表示条件番号は以下の通り
        //"1001"（入金請求発生＆信用口座開設）
        l_lisArrayLists.add(WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN);
        //"1002"（入金請求発生＆信用口座未開設）
        l_lisArrayLists.add(WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE);
        //"1007"（決済期限間近(一週間前)の建玉保有）
        l_lisArrayLists.add(WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS);
        //"1003"（立替金発生）
        l_lisArrayLists.add(WEB3PvInfoConditionDef.ADVANCE_GENERATION);
        //"1005"（証拠金不足）
        l_lisArrayLists.add(WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE);
        //"1041"（20％割れ1日＆30％割れ5日以下)
        l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN);
        //　@"1042"（20％割れ1日＆30％割れ6日） 
        l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_1DAY_AND_6DAY);
        //　@"1043"（20％割れ2日＆30％割れ6日以下） 
        l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN);
        //　@"1044"（20％割れ3日以上） 
        l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_3DAY_OVER);
        //　@"1045"（30％割れ2～4日） 
        l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_2TO4DAY);
        //　@"1046"（30％割れ5日） 
        l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_5DAY);
        //　@"1047"（30％割れ6日） 
        l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_6DAY);
        //　@"1048"（30％割れ7日以上）
        l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_7DAY_OVER);
        //"1049"（一部出金停止） 
        l_lisArrayLists.add(WEB3PvInfoConditionDef.PART_PAYMENT_STOP);
        //"1050"（全額出金停止）
        l_lisArrayLists.add(WEB3PvInfoConditionDef.FULL_PAYMENT_STOP);
        //"1051"（手数料割引キャンペーン）
        l_lisArrayLists.add(WEB3PvInfoConditionDef.COMMISSION_DISCOUNT_CAMPAIGN);
        //"1054"（不足金発生＆信用口座未開設）
        l_lisArrayLists.add(WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_CLOSE);
        //"1055"（不足金発生＆信用口座開設）
        l_lisArrayLists.add(WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_OPEN);
        //"1056"（第一水準追証発生）
        l_lisArrayLists.add(WEB3PvInfoConditionDef.FIRST_ADDITIONAL_DEPOSIT_OCCUR);
        //"1057"（第二水準追証発生）
        l_lisArrayLists.add(WEB3PvInfoConditionDef.SECOND_ADDITIONAL_DEPOSIT_OCCUR);
        //"0000"（ダイレクト指定）
        l_lisArrayLists.add(WEB3PvInfoConditionDef.DIRECT_ASSIGN);
        String[] l_strConLists = new String[l_lisArrayLists.size()];
        l_lisArrayLists.toArray(l_strConLists);
        assertEquals(l_strConLists[16],"1054");
        assertEquals(l_strConLists[17],"1055");
        assertEquals(l_strConLists[18],"1056");
        assertEquals(l_strConLists[19],"1057");

    }
}
@
