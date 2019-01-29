head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.40.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminPvInfoDirectRegistServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3AdminPvInfoDirectRegistServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/27 武波 (中訊) 新規作成
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PvInfoConditionDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.AdministratorUploadTempParams;
import webbroker3.gentrade.data.DirectDebitRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.pvinfo.data.BrowseHistoryParams;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteRequest;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 武波(中訊)
 * @@version 1.0
 */
public class WEB3AdminPvInfoDirectRegistServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectRegistServiceImplTest.class);

    public WEB3AdminPvInfoDirectRegistServiceImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

//    public void test_execute_C0001()
//    {
//        final String STR_METHOD_NAME = " test_execute_C0001";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
//            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
//            l_adminPermissionParams.setPermissionLevel("1");
//            l_adminPermissionParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_adminPermissionParams);
//
//            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
//            AdministratorUploadParams l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
//            TestDBUtility.insertWithDel(l_administratorUploadParams);
//
//            AdministratorParams l_administratorParams = new AdministratorParams();
//            l_administratorParams.setInstitutionCode("0D");
//            l_administratorParams.setPermissionLevel("1");
//            l_administratorParams.setAdministratorCode("2");
//            l_administratorParams.setInstitutionId(3333L);
//            l_administratorParams.setLoginId(33L);
//
//            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
//
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
//            WEB3GenRequest l_request = new WEB3AdminPvInfoDirectRegistInputRequest();
//
//            WEB3AdminPvInfoDirectRegistServiceImplExtends l_adminPvInfoDirectRegistServiceImpl =
//                new WEB3AdminPvInfoDirectRegistServiceImplExtends();
//
//            l_adminPvInfoDirectRegistServiceImpl.execute(l_request);
//
//            l_request = new WEB3AdminPvInfoDirectRegistConfirmRequest();
//            l_adminPvInfoDirectRegistServiceImpl.execute(l_request);
//
//            l_request = new WEB3AdminPvInfoDirectRegistCompleteRequest();
//            l_adminPvInfoDirectRegistServiceImpl.execute(l_request);
//
//            l_request = new WEB3AdminPvInfoDirectRegistCancelRequest();
//            l_adminPvInfoDirectRegistServiceImpl.execute(l_request);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    public void test_submitDirectRegist_C0001()
    {
        final String STR_METHOD_NAME = " test_submitDirectRegist_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminPvInfoDirectRegistCompleteRequest l_request =
            new WEB3AdminPvInfoDirectRegistCompleteRequest();
        l_request.branchCode = "123";
        l_request.accountCode = "123456";
        l_request.uploadId = "123";
        WEB3PvInfoDisplayContentsUnit l_pvInfoDisplayContentsUnit = new WEB3PvInfoDisplayContentsUnit();
        l_pvInfoDisplayContentsUnit.displayContentsId = "3";
        l_pvInfoDisplayContentsUnit.conditionNumber = WEB3PvInfoConditionDef.DIRECT_ASSIGN;
        l_pvInfoDisplayContentsUnit.displayTitle = "5";
        l_pvInfoDisplayContentsUnit.displayMessage = "6";
        l_pvInfoDisplayContentsUnit.displayColor = null;
        l_pvInfoDisplayContentsUnit.displayDevice = "7";
        l_pvInfoDisplayContentsUnit.priorityDiv = "8";
        l_pvInfoDisplayContentsUnit.listStartDate = null;
        l_pvInfoDisplayContentsUnit.listEndDate = null;
        l_pvInfoDisplayContentsUnit.displayUrl = "http";

        l_request.displayContentsUnit = l_pvInfoDisplayContentsUnit;

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123444));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            TestDBUtility.deleteAll(BrowseHistoryParams.TYPE);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("60");
            l_adminPermissionParams.setPermissionLevel("01");
            l_adminPermissionParams.setTransactionCategory("A0301");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParmas = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParmas.setInstitutionCode("0D");
            l_tradingTimeParmas.setBranchCode("123");
            l_tradingTimeParmas.setMarketCode("N1");
            l_tradingTimeParmas.setTradingTimeType("26");
            l_tradingTimeParmas.setProductCode("0");
            l_tradingTimeParmas.setBizDateType("1");
            l_tradingTimeParmas.setStartTime("000000");
            l_tradingTimeParmas.setEndTime("235959");
            l_tradingTimeParmas.setSubmitMarketTrigger("1");
            l_tradingTimeParmas.setEnableOrder("0");
            l_tradingTimeParmas.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParmas);
            TestDBUtility.deleteAll(DirectDebitRow.TYPE);
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            DisplayContentsParams l_displayContentsParams = new DisplayContentsParams();
            //1 表示内容IDdisplay_contents_idNUMBER18NotNull
            l_displayContentsParams.setDisplayContentsId(12);
            //2 証券会社コードinstitution_codeVARCHAR23NotNull
            l_displayContentsParams.setInstitutionCode("1");
            //3 部店コードbranch_codeVARCHAR23NotNull
            l_displayContentsParams.setBranchCode("624");
            //4 表示条件番号condition_noVARCHAR24NotNull
            l_displayContentsParams.setConditionNo("12");
            //8 表示タイトルdisplay_titleVARCHAR2100NotNull
            l_displayContentsParams.setDisplayTitle("12");
            //9 表示文章display_messageVARCHAR22000NotNull
            l_displayContentsParams.setDisplayMessage("12");
            //13 最終更新日時表示フラグlast_update_time_display_flagVARCHAR21NotNull
            l_displayContentsParams.setLastUpdateTimeDisplayFlag("1");
            //14 有効/無効フラグeffective_flagVARCHAR21NotNull
            l_displayContentsParams.setEffectiveFlag("1");
            //15 表示媒体display_deviceVARCHAR21NotNull
            l_displayContentsParams.setDisplayDevice("1");
            //17 作成日付created_timestampDATENotNull
            l_displayContentsParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //18 更新日付last_updated_timestampDATENotNull
            l_displayContentsParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_displayContentsParams);

            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_tempParams = TestDBUtility.getAdministratorUploadTempRow();
            l_tempParams.setAdministratorUploadId(123);
            l_tempParams.setLineNumber(111111);
            l_tempParams.setCsvLineValue("111111,2222222");
            TestDBUtility.insertWithDel(l_tempParams);
            l_tempParams.setAdministratorUploadId(123);
            l_tempParams.setLineNumber(222222);
            l_tempParams.setCsvLineValue("624,333333");
            TestDBUtility.insertWithDel(l_tempParams);
            l_tempParams.setLineNumber(333333);
            l_tempParams.setAdministratorUploadId(123);
            l_tempParams.setCsvLineValue("625,555555");
            TestDBUtility.insertWithDel(l_tempParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_row = TestDBUtility.getAdministratorUploadRow();
            l_row.setAdministratorUploadId(123);
            TestDBUtility.insertWithDel(l_row);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("60");
            l_mainAccountParams.setAccountId(12344);
            l_mainAccountParams.setAccountCode("333333");
            l_mainAccountParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_mainAccountParams.setAccountId(123444);
            l_mainAccountParams.setInstitutionCode("60");
            l_mainAccountParams.setAccountCode("555555");
            l_mainAccountParams.setBranchCode("625");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.commit();
            WEB3AdminPvInfoDirectRegistServiceImpl l_adminPvInfoDirectRegistServiceImpl =
                new WEB3AdminPvInfoDirectRegistServiceImpl();
            l_adminPvInfoDirectRegistServiceImpl.submitDirectRegist(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void test_submitDirectRegist_C0002()
    {
        final String STR_METHOD_NAME = "test_submitDirectRegist_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminPvInfoDirectRegistCompleteRequest l_request =
            new WEB3AdminPvInfoDirectRegistCompleteRequest();
        l_request.branchCode = "123";
        l_request.accountCode = "123456";
        l_request.uploadId = "123";
        WEB3PvInfoDisplayContentsUnit l_pvInfoDisplayContentsUnit = new WEB3PvInfoDisplayContentsUnit();
        l_pvInfoDisplayContentsUnit.displayContentsId = "3";
        l_pvInfoDisplayContentsUnit.conditionNumber = WEB3PvInfoConditionDef.DIRECT_ASSIGN;
        l_pvInfoDisplayContentsUnit.displayTitle = "5";
        l_pvInfoDisplayContentsUnit.displayMessage = "6";
        l_pvInfoDisplayContentsUnit.displayColor = null;
        l_pvInfoDisplayContentsUnit.displayDevice = "7";
        l_pvInfoDisplayContentsUnit.priorityDiv = "8";
        l_pvInfoDisplayContentsUnit.listStartDate = null;
        l_pvInfoDisplayContentsUnit.listEndDate = null;
        l_pvInfoDisplayContentsUnit.displayUrl = "http";

        l_request.displayContentsUnit = l_pvInfoDisplayContentsUnit;

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123444));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            TestDBUtility.deleteAll(BrowseHistoryParams.TYPE);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("60");
            l_adminPermissionParams.setPermissionLevel("01");
            l_adminPermissionParams.setTransactionCategory("A0301");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParmas = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParmas.setInstitutionCode("0D");
            l_tradingTimeParmas.setBranchCode("123");
            l_tradingTimeParmas.setMarketCode("N1");
            l_tradingTimeParmas.setTradingTimeType("26");
            l_tradingTimeParmas.setProductCode("0");
            l_tradingTimeParmas.setBizDateType("1");
            l_tradingTimeParmas.setStartTime("000000");
            l_tradingTimeParmas.setEndTime("235959");
            l_tradingTimeParmas.setSubmitMarketTrigger("1");
            l_tradingTimeParmas.setEnableOrder("0");
            l_tradingTimeParmas.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParmas);
            TestDBUtility.deleteAll(DirectDebitRow.TYPE);
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            DisplayContentsParams l_displayContentsParams = new DisplayContentsParams();
            //1 表示内容IDdisplay_contents_idNUMBER18NotNull
            l_displayContentsParams.setDisplayContentsId(12);
            //2 証券会社コードinstitution_codeVARCHAR23NotNull
            l_displayContentsParams.setInstitutionCode("1");
            //3 部店コードbranch_codeVARCHAR23NotNull
            l_displayContentsParams.setBranchCode("624");
            //4 表示条件番号condition_noVARCHAR24NotNull
            l_displayContentsParams.setConditionNo("12");
            //8 表示タイトルdisplay_titleVARCHAR2100NotNull
            l_displayContentsParams.setDisplayTitle("12");
            //9 表示文章display_messageVARCHAR22000NotNull
            l_displayContentsParams.setDisplayMessage("12");
            //13 最終更新日時表示フラグlast_update_time_display_flagVARCHAR21NotNull
            l_displayContentsParams.setLastUpdateTimeDisplayFlag("1");
            //14 有効/無効フラグeffective_flagVARCHAR21NotNull
            l_displayContentsParams.setEffectiveFlag("1");
            //15 表示媒体display_deviceVARCHAR21NotNull
            l_displayContentsParams.setDisplayDevice("1");
            //17 作成日付created_timestampDATENotNull
            l_displayContentsParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //18 更新日付last_updated_timestampDATENotNull
            l_displayContentsParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_displayContentsParams);

            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_tempParams = TestDBUtility.getAdministratorUploadTempRow();
            l_tempParams.setAdministratorUploadId(123);
            l_tempParams.setLineNumber(111111);
            l_tempParams.setCsvLineValue("111111,2222222");
            TestDBUtility.insertWithDel(l_tempParams);
            l_tempParams.setAdministratorUploadId(123);
            l_tempParams.setLineNumber(222222);
            l_tempParams.setCsvLineValue("624,333333");
            TestDBUtility.insertWithDel(l_tempParams);
            l_tempParams.setLineNumber(333333);
            l_tempParams.setAdministratorUploadId(123);
            l_tempParams.setCsvLineValue("624,333333");
            TestDBUtility.insertWithDel(l_tempParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_row = TestDBUtility.getAdministratorUploadRow();
            l_row.setAdministratorUploadId(123);
            TestDBUtility.insertWithDel(l_row);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("60");
            l_mainAccountParams.setAccountId(12344);
            l_mainAccountParams.setAccountCode("333333");
            l_mainAccountParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_mainAccountParams.setAccountId(123444);
            l_mainAccountParams.setInstitutionCode("60");
            l_mainAccountParams.setAccountCode("555555");
            l_mainAccountParams.setBranchCode("625");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.commit();
            WEB3AdminPvInfoDirectRegistServiceImpl l_adminPvInfoDirectRegistServiceImpl =
                new WEB3AdminPvInfoDirectRegistServiceImpl();
            l_adminPvInfoDirectRegistServiceImpl.submitDirectRegist(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            try
            {

                AdministratorUploadRow l_administratorUploadRow =
                    (AdministratorUploadRow)
                    AdministratorUploadDao.findRowByAdministratorUploadId(123);
                log.debug("l_administratorUploadRow.getMessageCode()=="
                    + l_administratorUploadRow.getMessageCode());
                assertEquals("517", l_administratorUploadRow.getMessageCode());

            }
            catch (DataException l_ex1)
            {
                log.error(l_ex.getMessage(), l_ex1);
                fail();
            }
//           log.error(l_ex.getMessage(), l_ex);
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00517, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void test_submitDirectRegist_C0003()
    {
        final String STR_METHOD_NAME = "test_submitDirectRegist_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminPvInfoDirectRegistCompleteRequest l_request =
            new WEB3AdminPvInfoDirectRegistCompleteRequest();
        l_request.branchCode = "123";
        l_request.accountCode = "123456";
        l_request.uploadId = "123";
        WEB3PvInfoDisplayContentsUnit l_pvInfoDisplayContentsUnit = new WEB3PvInfoDisplayContentsUnit();
        l_pvInfoDisplayContentsUnit.displayContentsId = "3";
        l_pvInfoDisplayContentsUnit.conditionNumber = WEB3PvInfoConditionDef.DIRECT_ASSIGN;
        l_pvInfoDisplayContentsUnit.displayTitle = "5";
        l_pvInfoDisplayContentsUnit.displayMessage = "6";
        l_pvInfoDisplayContentsUnit.displayColor = null;
        l_pvInfoDisplayContentsUnit.displayDevice = "7";
        l_pvInfoDisplayContentsUnit.priorityDiv = "8";
        l_pvInfoDisplayContentsUnit.listStartDate = null;
        l_pvInfoDisplayContentsUnit.listEndDate = null;
        l_pvInfoDisplayContentsUnit.displayUrl = "http";

        l_request.displayContentsUnit = l_pvInfoDisplayContentsUnit;

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123444));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);
            TestDBUtility.deleteAll(DisplayContentsParams.TYPE);
            TestDBUtility.deleteAll(BrowseHistoryParams.TYPE);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("60");
            l_adminPermissionParams.setPermissionLevel("01");
            l_adminPermissionParams.setTransactionCategory("A0301");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParmas = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParmas.setInstitutionCode("0D");
            l_tradingTimeParmas.setBranchCode("123");
            l_tradingTimeParmas.setMarketCode("N1");
            l_tradingTimeParmas.setTradingTimeType("26");
            l_tradingTimeParmas.setProductCode("0");
            l_tradingTimeParmas.setBizDateType("1");
            l_tradingTimeParmas.setStartTime("000000");
            l_tradingTimeParmas.setEndTime("235959");
            l_tradingTimeParmas.setSubmitMarketTrigger("1");
            l_tradingTimeParmas.setEnableOrder("0");
            l_tradingTimeParmas.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParmas);
            TestDBUtility.deleteAll(DirectDebitRow.TYPE);
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            DisplayContentsParams l_displayContentsParams = new DisplayContentsParams();
            //1 表示内容IDdisplay_contents_idNUMBER18NotNull
            l_displayContentsParams.setDisplayContentsId(12);
            //2 証券会社コードinstitution_codeVARCHAR23NotNull
            l_displayContentsParams.setInstitutionCode("1");
            //3 部店コードbranch_codeVARCHAR23NotNull
            l_displayContentsParams.setBranchCode("624");
            //4 表示条件番号condition_noVARCHAR24NotNull
            l_displayContentsParams.setConditionNo("12");
            //8 表示タイトルdisplay_titleVARCHAR2100NotNull
            l_displayContentsParams.setDisplayTitle("12");
            //9 表示文章display_messageVARCHAR22000NotNull
            l_displayContentsParams.setDisplayMessage("12");
            //13 最終更新日時表示フラグlast_update_time_display_flagVARCHAR21NotNull
            l_displayContentsParams.setLastUpdateTimeDisplayFlag("1");
            //14 有効/無効フラグeffective_flagVARCHAR21NotNull
            l_displayContentsParams.setEffectiveFlag("1");
            //15 表示媒体display_deviceVARCHAR21NotNull
            l_displayContentsParams.setDisplayDevice("1");
            //17 作成日付created_timestampDATENotNull
            l_displayContentsParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //18 更新日付last_updated_timestampDATENotNull
            l_displayContentsParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_displayContentsParams);

            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_tempParams = TestDBUtility.getAdministratorUploadTempRow();
            l_tempParams.setAdministratorUploadId(123);
            l_tempParams.setLineNumber(111111);
            l_tempParams.setCsvLineValue("111111,2222222");
            TestDBUtility.insertWithDel(l_tempParams);
            l_tempParams.setAdministratorUploadId(123);
            l_tempParams.setLineNumber(222222);
            l_tempParams.setCsvLineValue("624,333333");
            TestDBUtility.insertWithDel(l_tempParams);
            l_tempParams.setLineNumber(333333);
            l_tempParams.setAdministratorUploadId(123);
            l_tempParams.setCsvLineValue("625,555555");
            TestDBUtility.insertWithDel(l_tempParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_row = TestDBUtility.getAdministratorUploadRow();
            l_row.setAdministratorUploadId(123);
            TestDBUtility.insertWithDel(l_row);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("60");
            l_mainAccountParams.setAccountId(12344);
            l_mainAccountParams.setAccountCode("333333");
            l_mainAccountParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_mainAccountParams.setAccountId(123444);
            l_mainAccountParams.setInstitutionCode("60");
            l_mainAccountParams.setAccountCode("555555");
            l_mainAccountParams.setBranchCode("625");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.commit();
            WEB3AdminPvInfoDirectRegistServiceImpl l_adminPvInfoDirectRegistServiceImpl =
                new WEB3AdminPvInfoDirectRegistServiceImpl();
            l_adminPvInfoDirectRegistServiceImpl.submitDirectRegist(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

}
@
