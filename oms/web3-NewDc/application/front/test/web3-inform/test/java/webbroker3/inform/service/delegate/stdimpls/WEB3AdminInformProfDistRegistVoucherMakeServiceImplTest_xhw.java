head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.13.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xhw.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xhw.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/07 徐宏偉 (中訊) 新規作成
*/
package webbroker3.inform.service.delegate.stdimpls;

import javax.sql.DataSource;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.util.rac.MultiPoolDataSourceSettings;
import com.fitechlabs.xtrade.plugin.util.rac.RoundRobinBasedMultiPoolDataSource;
import com.fitechlabs.xtrade.plugin.util.rac.stdimpl.MultiPoolDataSourceSettingsImpl;
import com.fitechlabs.xtrade.plugin.util.rac.stdimpl.RoundRobinBasedMultiPoolDataSourceImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.DirectDebitRow;
import webbroker3.gentrade.data.FinInstitutionBankParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.inform.data.InformCtrlItemMasterParams;
import webbroker3.inform.data.VariousInformDao;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfResponse;
import webbroker3.inform.message.WEB3InformDetailInfoUnit;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistRegistVoucherMakeService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.system.tune.affinity.WEB3OrderReqNumberHead2ManageService;
import webbroker3.system.tune.affinity.WEB3OrderReqNumberHead2ManageServiceImpl;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xhw extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xhw.class);
    WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
        new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();

    WEB3AdminInformProfDistRegistVoucherMakeServiceImplForTest l_implForTest =
        new WEB3AdminInformProfDistRegistVoucherMakeServiceImplForTest();

    public WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xhw(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        this.deleteDate();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * "引数.リクエストデータ　@== nullの場合
     * "   1)引数.管理者・口座伝票変更確認リクエスト= null
     *     抛SYSTEM_ERROR_80017異常
     */
    public void testValidateAccountInfoChgCnf_case001()
    {
        final String STR_METHOD_NAME = " testValidateAccountInfoChgCnf_case001";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminInformProfDistVoucherChgCnfRequest l_request = null;
            l_impl.validateAccountInfoChgCnf(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * validate権限抛異常の場合 刪除管理者権限テーブル中所有記
     *  抛BUSINESS_ERROR_01056異常
     */
    public void testValidateAccountInfoChgCnf_case002()
    {
        final String STR_METHOD_NAME = " testValidateAccountInfoChgCnf_case002";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminInformProfDistVoucherChgCnfRequest l_request =
                new WEB3AdminInformProfDistVoucherChgCnfRequest();

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
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);

            l_impl.validateAccountInfoChgCnf(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * validate注文受付可能抛異常の場合
     * WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false)方法@的運用
     * 抛SYSTEM_ERROR_80006異常
     */
    public void testValidateAccountInfoChgCnf_case003()
    {
        final String STR_METHOD_NAME = " testValidateAccountInfoChgCnf_case003";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminInformProfDistVoucherChgCnfRequest l_request =
                new WEB3AdminInformProfDistVoucherChgCnfRequest();

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

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("60");
            l_adminPermissionParams.setPermissionLevel("01");
            l_adminPermissionParams.setTransactionCategory("A0105");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);

            l_impl.validateAccountInfoChgCnf(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * リクエストデータの簡易チェック抛異常の場合
     * リクエストデータ.部店コード=null
     * 抛BUSINESS_ERROR_02174異常
     */
    public void testValidateAccountInfoChgCnf_case004()
    {
        final String STR_METHOD_NAME = " testValidateAccountInfoChgCnf_case004";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminInformProfDistVoucherChgCnfRequest l_request =
                new WEB3AdminInformProfDistVoucherChgCnfRequest();

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

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("60");
            l_adminPermissionParams.setPermissionLevel("01");
            l_adminPermissionParams.setTransactionCategory("A0105");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            l_impl.validateAccountInfoChgCnf(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * get各種連絡行()戻り値がnullの場合
     * 例外をスロー 清空各種連絡テーブル中所有記
     * 抛BUSINESS_ERROR_00398異常
     */
    public void testValidateAccountInfoChgCnf_case005()
    {
        final String STR_METHOD_NAME = " testValidateAccountInfoChgCnf_case005";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            WEB3AdminInformProfDistVoucherChgCnfRequest l_request =
                new WEB3AdminInformProfDistVoucherChgCnfRequest();
            l_request.branchCode = "624";
            l_request.informType = "1";
            l_request.requestNumber = "234";
            WEB3InformDetailInfoUnit l_unit = new WEB3InformDetailInfoUnit();
            l_unit.informType = "1";
            l_unit.branchCode = "624";
            l_unit.institutionCode = "60";
            l_unit.num1 = "1";
            l_unit.num2 = "2";
            l_unit.num3 = "3";
            l_unit.num4 = "4";
            l_unit.num5 = "5";
            l_unit.num6 = "6";
            l_unit.num7 = "7";
            l_unit.num8 = "8";
            l_unit.num9 = "9";
            l_unit.num10 = "10";
            l_unit.num11 = "11";
            l_unit.num12 = "12";
            l_unit.num13 = "13";
            l_unit.num14 = "14";
            l_unit.num15 = "15";
            l_unit.num16 = "16";
            l_unit.num17 = "17";
            l_unit.num18 = "18";
            l_unit.num19 = "19";
            l_unit.num20 = "20";
            l_unit.num21 = "21";
            l_unit.num22 = "22";
            l_unit.num23 = "23";
            l_unit.num24 = "24";
            l_unit.num25 = "25";
            l_unit.num26 = "26";
            l_unit.num27 = "27";
            l_unit.num28 = "28";
            l_unit.num29 = "29";
            l_unit.num30 = "30";

            l_request.informInfoUnit = l_unit;

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

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("60");
            l_adminPermissionParams.setPermissionLevel("01");
            l_adminPermissionParams.setTransactionCategory("A0105");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            InformCtrlItemMasterParams l_informCtrlItemMasterParams =
                getInformCtrlItemMasterRow();

            l_informCtrlItemMasterParams.setBranchCode("624");
            l_informCtrlItemMasterParams.setInstitutionCode("60");
            l_informCtrlItemMasterParams.setInformDiv("1");
            l_informCtrlItemMasterParams.setItemSymbolName("123");
            l_informCtrlItemMasterParams.setNecessaryFlag(1);
            l_informCtrlItemMasterParams.setItemSymbolName("branch_code");
            TestDBUtility.insertWithDel(l_informCtrlItemMasterParams);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            l_impl.validateAccountInfoChgCnf(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     *  validate伝票変更抛異常の場合  "1）isトリガ発行的返回?為true (mock)
     *  2)get各種連絡行（）.get作成状況（）の戻り値 = 2
     *  "   抛BUSINESS_ERROR_02786異常
     */
    public void testValidateAccountInfoChgCnf_case006()
    {
        final String STR_METHOD_NAME = " testValidateAccountInfoChgCnf_case006";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            WEB3AdminInformProfDistVoucherChgCnfRequest l_request =
                new WEB3AdminInformProfDistVoucherChgCnfRequest();
            l_request.branchCode = "624";
            l_request.informType = "1";
            l_request.requestNumber = "234";
            WEB3InformDetailInfoUnit l_unit = new WEB3InformDetailInfoUnit();
            l_unit.informType = "1";
            l_unit.branchCode = "624";
            l_unit.institutionCode = "60";
            l_unit.num1 = "1";
            l_unit.num2 = "2";
            l_unit.num3 = "3";
            l_unit.num4 = "4";
            l_unit.num5 = "5";
            l_unit.num6 = "6";
            l_unit.num7 = "7";
            l_unit.num8 = "8";
            l_unit.num9 = "9";
            l_unit.num10 = "10";
            l_unit.num11 = "11";
            l_unit.num12 = "12";
            l_unit.num13 = "13";
            l_unit.num14 = "14";
            l_unit.num15 = "15";
            l_unit.num16 = "16";
            l_unit.num17 = "17";
            l_unit.num18 = "18";
            l_unit.num19 = "19";
            l_unit.num20 = "20";
            l_unit.num21 = "21";
            l_unit.num22 = "22";
            l_unit.num23 = "23";
            l_unit.num24 = "24";
            l_unit.num25 = "25";
            l_unit.num26 = "26";
            l_unit.num27 = "27";
            l_unit.num28 = "28";
            l_unit.num29 = "29";
            l_unit.num30 = "30";

            l_request.informInfoUnit = l_unit;

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

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("60");
            l_adminPermissionParams.setPermissionLevel("01");
            l_adminPermissionParams.setTransactionCategory("A0105");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            InformCtrlItemMasterParams l_informCtrlItemMasterParams =
                getInformCtrlItemMasterRow();

            l_informCtrlItemMasterParams.setBranchCode("624");
            l_informCtrlItemMasterParams.setInstitutionCode("60");
            l_informCtrlItemMasterParams.setInformDiv("1");
            l_informCtrlItemMasterParams.setItemSymbolName("123");
            l_informCtrlItemMasterParams.setNecessaryFlag(1);
            l_informCtrlItemMasterParams.setItemSymbolName("branch_code");
            TestDBUtility.insertWithDel(l_informCtrlItemMasterParams);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            VariousInformParams l_variousInformParams = getVariousInformRow();
            l_variousInformParams.setBranchCode("624");
            l_variousInformParams.setInstitutionCode("60");
            l_variousInformParams.setInformDiv("1");
            l_variousInformParams.setRequestNumber("234");
            l_variousInformParams.setStatus("2");
            l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_variousInformParams);
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(GtlUtils.getSystemTimestamp(), "1");
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
            l_impl.validateAccountInfoChgCnf(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02786, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     *  validate振込先登録情報(各種連絡情報)
     *  "1)リクエストデータ.連絡情報.区分4 = 3：削除 の場合
     *  2)清空自動振替登録マスター表"    抛BUSINESS_ERROR_02784異常
     */
    public void testValidateAccountInfoChgCnf_case007()
    {
        final String STR_METHOD_NAME = " testValidateAccountInfoChgCnf_case007";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            WEB3AdminInformProfDistVoucherChgCnfRequest l_request =
                new WEB3AdminInformProfDistVoucherChgCnfRequest();
            l_request.branchCode = "624";
            l_request.informType = "1";
            l_request.requestNumber = "234";
            WEB3InformDetailInfoUnit l_unit = new WEB3InformDetailInfoUnit();
            l_unit.informType = "1";
            l_unit.branchCode = "624";
            l_unit.institutionCode = "60";
            l_unit.num1 = "1";
            l_unit.num2 = "2";
            l_unit.num3 = "3";
            l_unit.num4 = "4";
            l_unit.num5 = "5";
            l_unit.num6 = "6";
            l_unit.num7 = "7";
            l_unit.num8 = "8";
            l_unit.num9 = "9";
            l_unit.num10 = "10";
            l_unit.num11 = "11";
            l_unit.num12 = "12";
            l_unit.num13 = "13";
            l_unit.num14 = "14";
            l_unit.num15 = "15";
            l_unit.num16 = "16";
            l_unit.num17 = "17";
            l_unit.num18 = "18";
            l_unit.num19 = "19";
            l_unit.num20 = "20";
            l_unit.num21 = "21";
            l_unit.num22 = "22";
            l_unit.num23 = "23";
            l_unit.num24 = "24";
            l_unit.num25 = "25";
            l_unit.num26 = "26";
            l_unit.num27 = "27";
            l_unit.num28 = "28";
            l_unit.num29 = "29";
            l_unit.num30 = "30";
            l_unit.div4 = "3";
            l_request.informInfoUnit = l_unit;

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

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("60");
            l_adminPermissionParams.setPermissionLevel("01");
            l_adminPermissionParams.setTransactionCategory("A0105");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            InformCtrlItemMasterParams l_informCtrlItemMasterParams =
                getInformCtrlItemMasterRow();

            l_informCtrlItemMasterParams.setBranchCode("624");
            l_informCtrlItemMasterParams.setInstitutionCode("60");
            l_informCtrlItemMasterParams.setInformDiv("1");
            l_informCtrlItemMasterParams.setItemSymbolName("123");
            l_informCtrlItemMasterParams.setNecessaryFlag(1);
            l_informCtrlItemMasterParams.setItemSymbolName("branch_code");
            TestDBUtility.insertWithDel(l_informCtrlItemMasterParams);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            VariousInformParams l_variousInformParams = getVariousInformRow();
            l_variousInformParams.setBranchCode("624");
            l_variousInformParams.setInstitutionCode("60");
            l_variousInformParams.setInformDiv("1");
            l_variousInformParams.setRequestNumber("234");
            l_variousInformParams.setStatus("0");
            l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_variousInformParams);

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
            TestDBUtility.deleteAll(DirectDebitRow.TYPE);
            l_impl.validateAccountInfoChgCnf(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02784, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
        /*
         *  validate銘柄コード(各種連絡情報)
         *  "1）リクエストデータ.連絡情報.区分３ = 2:投資信託
         *  2)リクエストデータ.連絡情報.コード１ のレングスが9バイト"    抛BUSINESS_ERROR_02785異常
         */
        public void testValidateAccountInfoChgCnf_case008()
        {
            final String STR_METHOD_NAME = " testValidateAccountInfoChgCnf_case008";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);

                WEB3AdminInformProfDistVoucherChgCnfRequest l_request =
                    new WEB3AdminInformProfDistVoucherChgCnfRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";
                WEB3InformDetailInfoUnit l_unit = new WEB3InformDetailInfoUnit();
                l_unit.informType = "1";
                l_unit.branchCode = "624";
                l_unit.institutionCode = "60";
                l_unit.num1 = "1";
                l_unit.num2 = "2";
                l_unit.num3 = "3";
                l_unit.num4 = "4";
                l_unit.num5 = "5";
                l_unit.num6 = "6";
                l_unit.num7 = "7";
                l_unit.num8 = "8";
                l_unit.num9 = "9";
                l_unit.num10 = "10";
                l_unit.num11 = "11";
                l_unit.num12 = "12";
                l_unit.num13 = "13";
                l_unit.num14 = "14";
                l_unit.num15 = "15";
                l_unit.num16 = "16";
                l_unit.num17 = "17";
                l_unit.num18 = "18";
                l_unit.num19 = "19";
                l_unit.num20 = "20";
                l_unit.num21 = "21";
                l_unit.num22 = "22";
                l_unit.num23 = "23";
                l_unit.num24 = "24";
                l_unit.num25 = "25";
                l_unit.num26 = "26";
                l_unit.num27 = "27";
                l_unit.num28 = "28";
                l_unit.num29 = "29";
                l_unit.num30 = "30";
                l_unit.div4 = "4";
                l_unit.div3 = "2";
                l_unit.code1 = "123456789";
                l_request.informInfoUnit = l_unit;

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_adminPermissionParams);

                InformCtrlItemMasterParams l_informCtrlItemMasterParams =
                    getInformCtrlItemMasterRow();

                l_informCtrlItemMasterParams.setBranchCode("624");
                l_informCtrlItemMasterParams.setInstitutionCode("60");
                l_informCtrlItemMasterParams.setInformDiv("1");
                l_informCtrlItemMasterParams.setItemSymbolName("123");
                l_informCtrlItemMasterParams.setNecessaryFlag(1);
                l_informCtrlItemMasterParams.setItemSymbolName("branch_code");
                TestDBUtility.insertWithDel(l_informCtrlItemMasterParams);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                VariousInformParams l_variousInformParams = getVariousInformRow();
                l_variousInformParams.setBranchCode("624");
                l_variousInformParams.setInstitutionCode("60");
                l_variousInformParams.setInformDiv("1");
                l_variousInformParams.setRequestNumber("234");
                l_variousInformParams.setStatus("0");
                l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_variousInformParams);

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
                TestDBUtility.deleteAll(DirectDebitRow.TYPE);
                l_impl.validateAccountInfoChgCnf(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02785, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

        log.exiting(STR_METHOD_NAME);
    }

        /*
         * "連絡情報.区分５ = 1：銀行振込 の場合
         * get金融機@関情報(String, String, 各種金融機@関情報)進行參數驗證"
         *  "1）連絡情報.コード３ = "2"
         *  連絡情報.コード2 = "4"
         *  2）連絡情報.コード４ = 3
         *  付加情報 = get付加情報（）の戻り値
         *  金融機@関（銀行）マスタ表中存在
         *  銀行コード = “2“
         *  支店コード = “3"
         *  支店名 = “sss”
         *  支店名（カナ） = "ddd"
         *  金融機@関名 = "fff"
         *  金融機@関名（カナ） = "ggg""的記?" "驗證方法@返回?：
         *  レスポンスオブジェクト.金融機@関情報.支店名 = “sss”
         *  レスポンスオブジェクト.金融機@関情報.支店名（カナ） = ""ddd""
         *  レスポンスオブジェクト.金融機@関情報.金融機@関名 = ""fff""
         *  レスポンスオブジェクト.金融機@関情報.金融機@関名（カナ） = ""ggg""的紀?"  正常
         */
        public void testValidateAccountInfoChgCnf_case009()
        {
            final String STR_METHOD_NAME = " testValidateAccountInfoChgCnf_case009";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);

                WEB3AdminInformProfDistVoucherChgCnfRequest l_request =
                    new WEB3AdminInformProfDistVoucherChgCnfRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";
                WEB3InformDetailInfoUnit l_unit = new WEB3InformDetailInfoUnit();
                l_unit.informType = "1";
                l_unit.branchCode = "624";
                l_unit.institutionCode = "60";
                l_unit.num1 = "1";
                l_unit.num2 = "2";
                l_unit.num3 = "3";
                l_unit.num4 = "4";
                l_unit.num5 = "5";
                l_unit.num6 = "6";
                l_unit.num7 = "7";
                l_unit.num8 = "8";
                l_unit.num9 = "9";
                l_unit.num10 = "10";
                l_unit.num11 = "11";
                l_unit.num12 = "12";
                l_unit.num13 = "13";
                l_unit.num14 = "14";
                l_unit.num15 = "15";
                l_unit.num16 = "16";
                l_unit.num17 = "17";
                l_unit.num18 = "18";
                l_unit.num19 = "19";
                l_unit.num20 = "20";
                l_unit.num21 = "21";
                l_unit.num22 = "22";
                l_unit.num23 = "23";
                l_unit.num24 = "24";
                l_unit.num25 = "25";
                l_unit.num26 = "26";
                l_unit.num27 = "27";
                l_unit.num28 = "28";
                l_unit.num29 = "29";
                l_unit.num30 = "30";
                l_unit.div4 = "4";
                l_unit.div2 = "4";
                l_unit.div3 = "2";
                l_unit.div5 = "1";
                l_unit.code1 = "1234567";
                l_unit.code3 = "2";
                l_unit.code4 = "3";
                l_request.informInfoUnit = l_unit;

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_adminPermissionParams);

                InformCtrlItemMasterParams l_informCtrlItemMasterParams =
                    getInformCtrlItemMasterRow();

                l_informCtrlItemMasterParams.setBranchCode("624");
                l_informCtrlItemMasterParams.setInstitutionCode("60");
                l_informCtrlItemMasterParams.setInformDiv("1");
                l_informCtrlItemMasterParams.setItemSymbolName("123");
                l_informCtrlItemMasterParams.setNecessaryFlag(1);
                l_informCtrlItemMasterParams.setItemSymbolName("branch_code");
                TestDBUtility.insertWithDel(l_informCtrlItemMasterParams);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                VariousInformParams l_variousInformParams = getVariousInformRow();
                l_variousInformParams.setBranchCode("624");
                l_variousInformParams.setInstitutionCode("60");
                l_variousInformParams.setInformDiv("1");
                l_variousInformParams.setRequestNumber("234");
                l_variousInformParams.setStatus("0");
                l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_variousInformParams);

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

                FinInstitutionBankParams l_finInstitutionBankParams = new FinInstitutionBankParams();
                l_finInstitutionBankParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_finInstitutionBankParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_finInstitutionBankParams.setFinBranchCode("3");
                l_finInstitutionBankParams.setFinBranchName("sss");
                l_finInstitutionBankParams.setFinBranchNameKana("ddd");
                l_finInstitutionBankParams.setFinInstitutionCode("2");
                l_finInstitutionBankParams.setFinInstitutionName("fff");
                l_finInstitutionBankParams.setFinInstitutionNameKana("ggg");
                TestDBUtility.insertWithDel(l_finInstitutionBankParams);
                WEB3AdminInformProfDistVoucherChgCnfResponse l_response =
                    l_impl.validateAccountInfoChgCnf(l_request);

                String l_strFinancialBranchName =
                    l_response.financialInstitutionInfo.financialBranchName;
                String l_strFinancialBranchNameKana =
                    l_response.financialInstitutionInfo.financialBranchNameKana;
                String l_strFinancialInstitutionName =
                    l_response.financialInstitutionInfo.financialInstitutionName;
                String l_strFinancialInstitutionNameKana =
                    l_response.financialInstitutionInfo.financialInstitutionNameKana;

                assertEquals("sss", l_strFinancialBranchName);
                assertEquals("ddd", l_strFinancialBranchNameKana);
                assertEquals("fff", l_strFinancialInstitutionName);
                assertEquals("ggg", l_strFinancialInstitutionNameKana);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

        log.exiting(STR_METHOD_NAME);
    }

        /*
         * "引数.リクエストデータ　@== nullの場合
         * "   1)引数.管理者・口座伝票変更確認リクエスト= null
         *     抛SYSTEM_ERROR_80017異常
         */
        public void testSubmitAccountInfoChgCmp_case001()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoChgCmp_case001";
            log.entering(STR_METHOD_NAME);

            try
            {
                WEB3AdminInformProfDistVoucherChgCmpRequest l_request = null;
                l_impl.submitAccountInfoChgCmp(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * validate権限抛異常の場合 刪除管理者権限テーブル中所有記
         *  抛BUSINESS_ERROR_01056異常
         */
        public void testSubmitAccountInfoChgCmp_case002()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoChgCmp_case002";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherChgCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherChgCmpRequest();

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
                TestDBUtility.deleteAll(AdminPermissionRow.TYPE);

                l_impl.submitAccountInfoChgCmp(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         *validate取引パスワード   validate取引パスワード （mock實現）
         *抛BUSINESS_ERROR_00009異常 
         */
        public void testSubmitAccountInfoChgCmp_case003()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoChgCmp_case003";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherChgCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherChgCmpRequest();

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
                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_adminPermissionParams);

                WEB3AdministratorForMock.mockValidateTradingPassword("123", false);
                l_impl.submitAccountInfoChgCmp(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * validate注文受付可能抛異常の場合
         * WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false)方法@的運用
         * 抛SYSTEM_ERROR_80006異常
         */
        public void testSubmitAccountInfoChgCmp_case004()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoChgCmp_case004";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherChgCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherChgCmpRequest();

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);

                l_impl.submitAccountInfoChgCmp(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * リクエストデータの簡易チェック抛異常の場合
         * リクエストデータ.部店コード=null
         * 抛BUSINESS_ERROR_02174異常
         */
        public void testSubmitAccountInfoChgCmp_case005()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoChgCmp_case005";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherChgCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherChgCmpRequest();

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                l_impl.submitAccountInfoChgCmp(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * get各種連絡行()戻り値がnullの場合、
         * 例外をスロー 清空各種連絡テーブル中所有記
         * 抛BUSINESS_ERROR_00398異常
         */
        public void testSubmitAccountInfoChgCmp_case006()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoChgCmp_case006";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherChgCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherChgCmpRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";
                WEB3InformDetailInfoUnit l_unit = new WEB3InformDetailInfoUnit();
                l_unit.informType = "1";
                l_unit.branchCode = "624";
                l_unit.institutionCode = "60";
                l_unit.num1 = "1";
                l_unit.num2 = "2";
                l_unit.num3 = "3";
                l_unit.num4 = "4";
                l_unit.num5 = "5";
                l_unit.num6 = "6";
                l_unit.num7 = "7";
                l_unit.num8 = "8";
                l_unit.num9 = "9";
                l_unit.num10 = "10";
                l_unit.num11 = "11";
                l_unit.num12 = "12";
                l_unit.num13 = "13";
                l_unit.num14 = "14";
                l_unit.num15 = "15";
                l_unit.num16 = "16";
                l_unit.num17 = "17";
                l_unit.num18 = "18";
                l_unit.num19 = "19";
                l_unit.num20 = "20";
                l_unit.num21 = "21";
                l_unit.num22 = "22";
                l_unit.num23 = "23";
                l_unit.num24 = "24";
                l_unit.num25 = "25";
                l_unit.num26 = "26";
                l_unit.num27 = "27";
                l_unit.num28 = "28";
                l_unit.num29 = "29";
                l_unit.num30 = "30";

                l_request.informInfoUnit = l_unit;
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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                TestDBUtility.deleteAll(VariousInformParams.TYPE);
                l_impl.submitAccountInfoChgCmp(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         *  validate伝票変更抛異常の場合  "1）isトリガ発行的返回?為true (mock)
         *  2)get各種連絡行（）.get作成状況（）の戻り値 = 2
         *  "   抛BUSINESS_ERROR_02786異常
         */
        public void testSubmitAccountInfoChgCmp_case007()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoChgCmp_case007";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherChgCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherChgCmpRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";
                WEB3InformDetailInfoUnit l_unit = new WEB3InformDetailInfoUnit();
                l_unit.informType = "1";
                l_unit.branchCode = "624";
                l_unit.institutionCode = "60";
                l_unit.num1 = "1";
                l_unit.num2 = "2";
                l_unit.num3 = "3";
                l_unit.num4 = "4";
                l_unit.num5 = "5";
                l_unit.num6 = "6";
                l_unit.num7 = "7";
                l_unit.num8 = "8";
                l_unit.num9 = "9";
                l_unit.num10 = "10";
                l_unit.num11 = "11";
                l_unit.num12 = "12";
                l_unit.num13 = "13";
                l_unit.num14 = "14";
                l_unit.num15 = "15";
                l_unit.num16 = "16";
                l_unit.num17 = "17";
                l_unit.num18 = "18";
                l_unit.num19 = "19";
                l_unit.num20 = "20";
                l_unit.num21 = "21";
                l_unit.num22 = "22";
                l_unit.num23 = "23";
                l_unit.num24 = "24";
                l_unit.num25 = "25";
                l_unit.num26 = "26";
                l_unit.num27 = "27";
                l_unit.num28 = "28";
                l_unit.num29 = "29";
                l_unit.num30 = "30";

                l_request.informInfoUnit = l_unit;
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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                VariousInformParams l_variousInformParams = getVariousInformRow();
                l_variousInformParams.setBranchCode("624");
                l_variousInformParams.setInstitutionCode("60");
                l_variousInformParams.setInformDiv("1");
                l_variousInformParams.setRequestNumber("234");
                l_variousInformParams.setStatus("2");
                l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_variousInformParams);

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
                l_impl.submitAccountInfoChgCmp(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02786, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * validate振込先登録情報(各種連絡情報)
         * "1)リクエストデータ.連絡情報.区分4 = 3：削除 の場合
         * 2)清空自動振替登録マスター表"
         * 抛BUSINESS_ERROR_02784異常
         */
        public void testSubmitAccountInfoChgCmp_case008()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoChgCmp_case008";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherChgCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherChgCmpRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";

                WEB3InformDetailInfoUnit l_unit = new WEB3InformDetailInfoUnit();
                l_unit.informType = "1";
                l_unit.accountNumber = "123";
                l_unit.branchCode = "624";
                l_unit.institutionCode = "60";
                l_unit.num1 = "1";
                l_unit.num2 = "2";
                l_unit.num3 = "3";
                l_unit.num4 = "4";
                l_unit.num5 = "5";
                l_unit.num6 = "6";
                l_unit.num7 = "7";
                l_unit.num8 = "8";
                l_unit.num9 = "9";
                l_unit.num10 = "10";
                l_unit.num11 = "11";
                l_unit.num12 = "12";
                l_unit.num13 = "13";
                l_unit.num14 = "14";
                l_unit.num15 = "15";
                l_unit.num16 = "16";
                l_unit.num17 = "17";
                l_unit.num18 = "18";
                l_unit.num19 = "19";
                l_unit.num20 = "20";
                l_unit.num21 = "21";
                l_unit.num22 = "22";
                l_unit.num23 = "23";
                l_unit.num24 = "24";
                l_unit.num25 = "25";
                l_unit.num26 = "26";
                l_unit.num27 = "27";
                l_unit.num28 = "28";
                l_unit.num29 = "29";
                l_unit.num30 = "30";
                l_unit.div4 = "3";

                l_request.informInfoUnit = l_unit;
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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                VariousInformParams l_variousInformParams = getVariousInformRow();
                l_variousInformParams.setBranchCode("624");
                l_variousInformParams.setInstitutionCode("60");
                l_variousInformParams.setInformDiv("1");
                l_variousInformParams.setRequestNumber("234");
                l_variousInformParams.setStatus("4");
                l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_variousInformParams);

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
                TestDBUtility.deleteAll(DirectDebitRow.TYPE);
                l_impl.submitAccountInfoChgCmp(l_request);

                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02784, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * validate銘柄コード(各種連絡情報) 
         *  "1）リクエストデータ.連絡情報.区分３ = 2:投資信託
         * 2)リクエストデータ.連絡情報.コード１ のレングスが9バイト"
         * 抛BUSINESS_ERROR_02785異常
         */
        public void testSubmitAccountInfoChgCmp_case009()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoChgCmp_case009";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherChgCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherChgCmpRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";
                WEB3InformDetailInfoUnit l_unit = new WEB3InformDetailInfoUnit();
                l_unit.informType = "1";
                l_unit.branchCode = "624";
                l_unit.institutionCode = "60";
                l_unit.accountNumber = "123";
                l_unit.num1 = "1";
                l_unit.num2 = "2";
                l_unit.num3 = "3";
                l_unit.num4 = "4";
                l_unit.num5 = "5";
                l_unit.num6 = "6";
                l_unit.num7 = "7";
                l_unit.num8 = "8";
                l_unit.num9 = "9";
                l_unit.num10 = "10";
                l_unit.num11 = "11";
                l_unit.num12 = "12";
                l_unit.num13 = "13";
                l_unit.num14 = "14";
                l_unit.num15 = "15";
                l_unit.num16 = "16";
                l_unit.num17 = "17";
                l_unit.num18 = "18";
                l_unit.num19 = "19";
                l_unit.num20 = "20";
                l_unit.num21 = "21";
                l_unit.num22 = "22";
                l_unit.num23 = "23";
                l_unit.num24 = "24";
                l_unit.num25 = "25";
                l_unit.num26 = "26";
                l_unit.num27 = "27";
                l_unit.num28 = "28";
                l_unit.num29 = "29";
                l_unit.num30 = "30";
                l_unit.div4 = "4";
                l_unit.div3 = "2";
                l_unit.code1 = "123456799";
                l_request.informInfoUnit = l_unit;
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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                VariousInformParams l_variousInformParams = getVariousInformRow();
                l_variousInformParams.setBranchCode("624");
                l_variousInformParams.setInstitutionCode("60");
                l_variousInformParams.setInformDiv("1");
                l_variousInformParams.setRequestNumber("234");
                l_variousInformParams.setStatus("4");
                l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_variousInformParams);

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
                TestDBUtility.deleteAll(DirectDebitRow.TYPE);
                l_impl.submitAccountInfoChgCmp(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02785, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * "連絡情報.区分５ = 1：銀行振込 の場合
         * is銀行登録伝票（） == true の場合
         * isトリガ発行 == true の場合
         *"   リクエストデータ.連絡情報.証券会社コード = "123"
         *"1)update各種連絡変更情報(各種連絡Params, 各種連絡Params, String, String)參數驗證
         *データコード：　@= “GI823“（銀行）
         *2)トリガ発行(String, String)
         *証券会社コード：　@リクエストデータ.連絡情報.証券会社コード
         *データコード：　@”GI823”（銀行）"
         */
        public void testSubmitAccountInfoChgCmp_case010()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoChgCmp_case010";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherChgCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherChgCmpRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";
                WEB3InformDetailInfoUnit l_unit = new WEB3InformDetailInfoUnit();
                l_unit.informType = "1";
                l_unit.branchCode = "624";
                l_unit.accountNumber = "123";
                l_unit.institutionCode = "60";
                l_unit.num1 = "1";
                l_unit.num2 = "2";
                l_unit.num3 = "3";
                l_unit.num4 = "4";
                l_unit.num5 = "5";
                l_unit.num6 = "6";
                l_unit.num7 = "7";
                l_unit.num8 = "8";
                l_unit.num9 = "9";
                l_unit.num10 = "10";
                l_unit.num11 = "11";
                l_unit.num12 = "12";
                l_unit.num13 = "13";
                l_unit.num14 = "14";
                l_unit.num15 = "15";
                l_unit.num16 = "16";
                l_unit.num17 = "17";
                l_unit.num18 = "18";
                l_unit.num19 = "19";
                l_unit.num20 = "20";
                l_unit.num21 = "21";
                l_unit.num22 = "22";
                l_unit.num23 = "23";
                l_unit.num24 = "24";
                l_unit.num25 = "25";
                l_unit.num26 = "26";
                l_unit.num27 = "27";
                l_unit.num28 = "28";
                l_unit.num29 = "29";
                l_unit.num30 = "30";
                l_unit.div4 = "4";
                l_unit.div2 = "4";
                l_unit.div3 = "2";
                l_unit.div5 = "1";
                l_unit.code1 = "1234567";
                l_unit.code3 = "2";
                l_unit.code4 = "3";
                l_request.informInfoUnit = l_unit;

                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
                l_mainAccountParams.setInstitutionCode("60");
                l_mainAccountParams.setBranchCode("624");
                l_mainAccountParams.setAccountCode("123");
                TestDBUtility.insertWithDel(l_mainAccountParams);

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                VariousInformParams l_variousInformParams = getVariousInformRow();
                l_variousInformParams.setBranchCode("624");
                l_variousInformParams.setInstitutionCode("60");
                l_variousInformParams.setInformDiv("1");
                l_variousInformParams.setRequestNumber("234");
                l_variousInformParams.setStatus("4");
                l_variousInformParams.setAccountCode("123");
                l_variousInformParams.setRequestCode("123");
                l_variousInformParams.setExtDiv5("1");
                l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_variousInformParams);

                TradingTimeParams l_tradingTimeParmas = TestDBUtility.getTradingTimeRow();
                l_tradingTimeParmas.setInstitutionCode("0D");
                l_tradingTimeParmas.setBranchCode("123");
                l_tradingTimeParmas.setMarketCode("N1");
                l_tradingTimeParmas.setTradingTimeType("01");
                l_tradingTimeParmas.setProductCode("0");
                l_tradingTimeParmas.setBizDateType("1");
                l_tradingTimeParmas.setStartTime("000000");
                l_tradingTimeParmas.setEndTime("235959");
                l_tradingTimeParmas.setSubmitMarketTrigger("0");
                l_tradingTimeParmas.setEnableOrder("0");
                l_tradingTimeParmas.setBizdateCalcParameter("1");
                TestDBUtility.insertWithDel(l_tradingTimeParmas);
                FinInstitutionBankParams l_finInstitutionBankParams = new FinInstitutionBankParams();
                l_finInstitutionBankParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_finInstitutionBankParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_finInstitutionBankParams.setFinBranchCode("3");
                l_finInstitutionBankParams.setFinBranchName("sss");
                l_finInstitutionBankParams.setFinBranchNameKana("ddd");
                l_finInstitutionBankParams.setFinInstitutionCode("2");
                l_finInstitutionBankParams.setFinInstitutionName("fff");
                l_finInstitutionBankParams.setFinInstitutionNameKana("ggg");
                TestDBUtility.insertWithDel(l_finInstitutionBankParams);
                
                WEB3AdminInformProfDistVoucherChgCmpResponse l_response =
                    l_implForTest.submitAccountInfoChgCmp(l_request);

            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * "get各種連絡行（）.区分５ == 5:郵貯振替の場合
         * is銀行登録伝票（） == false の場合
         * isトリガ発行 == false の場合
         * 1)update各種連絡変更情報(各種連絡Params, 各種連絡Params, String, String)參數驗證
         * データコード：　@= GI828（郵貯）"
         */
        public void testSubmitAccountInfoChgCmp_case011()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoChgCmp_case011";
            log.entering(STR_METHOD_NAME);

//            Services.registerService(WEB3OrderReqNumberHead2ManageService.class, new WEB3OrderReqNumberHead2ManageServiceImpl());
            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherChgCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherChgCmpRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";
                WEB3InformDetailInfoUnit l_unit = new WEB3InformDetailInfoUnit();
                l_unit.informType = "1";
                l_unit.branchCode = "624";
                l_unit.accountNumber = "123";
                l_unit.institutionCode = "60";
                l_unit.num1 = "1";
                l_unit.num2 = "2";
                l_unit.num3 = "3";
                l_unit.num4 = "4";
                l_unit.num5 = "5";
                l_unit.num6 = "6";
                l_unit.num7 = "7";
                l_unit.num8 = "8";
                l_unit.num9 = "9";
                l_unit.num10 = "10";
                l_unit.num11 = "11";
                l_unit.num12 = "12";
                l_unit.num13 = "13";
                l_unit.num14 = "14";
                l_unit.num15 = "15";
                l_unit.num16 = "16";
                l_unit.num17 = "17";
                l_unit.num18 = "18";
                l_unit.num19 = "19";
                l_unit.num20 = "20";
                l_unit.num21 = "21";
                l_unit.num22 = "22";
                l_unit.num23 = "23";
                l_unit.num24 = "24";
                l_unit.num25 = "25";
                l_unit.num26 = "26";
                l_unit.num27 = "27";
                l_unit.num28 = "28";
                l_unit.num29 = "29";
                l_unit.num30 = "30";
                l_unit.div4 = "4";
                l_unit.div2 = "4";
                l_unit.div3 = "2";
                l_unit.div5 = "1";
                l_unit.code1 = "1234567";
                l_unit.code3 = "2";
                l_unit.code4 = "3";
                l_request.informInfoUnit = l_unit;

                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
                l_mainAccountParams.setInstitutionCode("60");
                l_mainAccountParams.setBranchCode("624");
                l_mainAccountParams.setAccountCode("123");
                TestDBUtility.insertWithDel(l_mainAccountParams);

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                VariousInformParams l_variousInformParams = getVariousInformRow();
                l_variousInformParams.setBranchCode("624");
                l_variousInformParams.setInstitutionCode("60");
                l_variousInformParams.setInformDiv("1");
                l_variousInformParams.setRequestNumber("234");
                l_variousInformParams.setStatus("4");
                l_variousInformParams.setAccountCode("123");
                l_variousInformParams.setRequestCode("123");
                l_variousInformParams.setExtDiv5("5");
                l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_variousInformParams);

                TradingTimeParams l_tradingTimeParmas = TestDBUtility.getTradingTimeRow();
                l_tradingTimeParmas.setInstitutionCode("0D");
                l_tradingTimeParmas.setBranchCode("123");
                l_tradingTimeParmas.setMarketCode("N1");
                l_tradingTimeParmas.setTradingTimeType("01");
                l_tradingTimeParmas.setProductCode("0");
                l_tradingTimeParmas.setBizDateType("1");
                l_tradingTimeParmas.setStartTime("000000");
                l_tradingTimeParmas.setEndTime("235959");
                l_tradingTimeParmas.setSubmitMarketTrigger("0");
                l_tradingTimeParmas.setEnableOrder("0");
                l_tradingTimeParmas.setBizdateCalcParameter("1");
                TestDBUtility.insertWithDel(l_tradingTimeParmas);
                FinInstitutionBankParams l_finInstitutionBankParams = new FinInstitutionBankParams();
                l_finInstitutionBankParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_finInstitutionBankParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_finInstitutionBankParams.setFinBranchCode("3");
                l_finInstitutionBankParams.setFinBranchName("sss");
                l_finInstitutionBankParams.setFinBranchNameKana("ddd");
                l_finInstitutionBankParams.setFinInstitutionCode("2");
                l_finInstitutionBankParams.setFinInstitutionName("fff");
                l_finInstitutionBankParams.setFinInstitutionNameKana("ggg");
                TestDBUtility.insertWithDel(l_finInstitutionBankParams);

                WEB3AdminInformProfDistVoucherChgCmpResponse l_response =
                    l_implForTest.submitAccountInfoChgCmp(l_request);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * "get各種連絡行（）.区分５ == 5:郵貯振替の場合
         * is銀行登録伝票（） == false の場合
         * isトリガ発行 == true の場合
         * 1)update各種連絡変更情報(各種連絡Params, 各種連絡Params, String, String)參數驗證
         * データコード：　@= GI828（郵貯）"
         */
        public void testSubmitAccountInfoChgCmp_case012()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoChgCmp_case012";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherChgCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherChgCmpRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";
                WEB3InformDetailInfoUnit l_unit = new WEB3InformDetailInfoUnit();
                l_unit.informType = "1";
                l_unit.branchCode = "624";
                l_unit.accountNumber = "123456";
                l_unit.institutionCode = "60";
                l_unit.num1 = "1";
                l_unit.num2 = "2";
                l_unit.num3 = "3";
                l_unit.num4 = "4";
                l_unit.num5 = "5";
                l_unit.num6 = "6";
                l_unit.num7 = "7";
                l_unit.num8 = "8";
                l_unit.num9 = "9";
                l_unit.num10 = "10";
                l_unit.num11 = "11";
                l_unit.num12 = "12";
                l_unit.num13 = "13";
                l_unit.num14 = "14";
                l_unit.num15 = "15";
                l_unit.num16 = "16";
                l_unit.num17 = "17";
                l_unit.num18 = "18";
                l_unit.num19 = "19";
                l_unit.num20 = "20";
                l_unit.num21 = "21";
                l_unit.num22 = "22";
                l_unit.num23 = "23";
                l_unit.num24 = "24";
                l_unit.num25 = "25";
                l_unit.num26 = "26";
                l_unit.num27 = "27";
                l_unit.num28 = "28";
                l_unit.num29 = "29";
                l_unit.num30 = "30";
                l_unit.div4 = "4";
                l_unit.div2 = "4";
                l_unit.div3 = "2";
                l_unit.div5 = "5";
                l_unit.code1 = "1234567";
                l_unit.code3 = "2";
                l_unit.code4 = "3";
                l_request.informInfoUnit = l_unit;

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
                WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(GtlUtils.getSystemTimestamp(), "1");
                VariousInformParams l_variousInformParams = getVariousInformRow();
                l_variousInformParams.setBranchCode("624");
                l_variousInformParams.setInstitutionCode("60");
                l_variousInformParams.setInformDiv("1");
                l_variousInformParams.setRequestNumber("234");
                l_variousInformParams.setStatus("4");
                l_variousInformParams.setAccountCode("123456");
                l_variousInformParams.setRequestCode("123");
                l_variousInformParams.setExtDiv5("5");
                l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_variousInformParams);

                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                MainAccountParams l_mainAccountRow = TestDBUtility.getMainAccountRow();
                l_mainAccountRow.setInstitutionCode("60");
                l_mainAccountRow.setBranchCode("624");
                l_mainAccountRow.setAccountCode("123456");
                l_mainAccountRow.setOnlyMblOpnDivTimestamp(GtlUtils.getSystemTimestamp());
                l_mainAccountRow.setOnlyMblOpnDivLastUpdater("123");
                l_mainAccountRow.setOnlyMobileOpenDiv("1");
                TestDBUtility.insertWithDel(l_mainAccountRow);
                
                TestDBUtility.deleteAll(BranchParams.TYPE);
                BranchParams l_branchParams = TestDBUtility.getBranchRow();
                l_branchParams.setBranchCode("123");
                l_branchParams.setInstitutionCode("0D");
                TestDBUtility.insertWithDel(l_branchParams);
                
                TradingTimeParams l_tradingTimeParmas = TestDBUtility.getTradingTimeRow();
                l_tradingTimeParmas.setInstitutionCode("0D");
                l_tradingTimeParmas.setBranchCode("123");
                l_tradingTimeParmas.setMarketCode("0");
                l_tradingTimeParmas.setTradingTimeType("35");
                l_tradingTimeParmas.setProductCode("0");
                l_tradingTimeParmas.setBizDateType("1");
                l_tradingTimeParmas.setStartTime("000000");
                l_tradingTimeParmas.setEndTime("235959");
                l_tradingTimeParmas.setSubmitMarketTrigger("1");
                l_tradingTimeParmas.setEnableOrder("0");
                l_tradingTimeParmas.setBizDateType("1");
                l_tradingTimeParmas.setBizdateCalcParameter("1");
                TestDBUtility.insertWithDel(l_tradingTimeParmas);

                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
                TestDBUtility.insertWithDel(l_institutionParams);

                FinInstitutionBankParams l_finInstitutionBankParams = new FinInstitutionBankParams();
                l_finInstitutionBankParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_finInstitutionBankParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_finInstitutionBankParams.setFinBranchCode("3");
                l_finInstitutionBankParams.setFinBranchName("sss");
                l_finInstitutionBankParams.setFinBranchNameKana("ddd");
                l_finInstitutionBankParams.setFinInstitutionCode("2");
                l_finInstitutionBankParams.setFinInstitutionName("fff");
                l_finInstitutionBankParams.setFinInstitutionNameKana("ggg");
                TestDBUtility.insertWithDel(l_finInstitutionBankParams);
//                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
//                    "send",
//                    new Class[] {WEB3MQMessageSpec.class},
//                    new Object[]{l_mqSendResult});
//                WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
                
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.system.tune.affinity.WEB3OrderReqNumberHead2ManageServiceImpl",
                  "getOrderReqNumberHead2",
                  new Class[] {},
                  "00");
                 WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
                
                WEB3GentradeTradingTimeManagementForMock.setSystemAttributes();
                MOCK_MANAGER.setIsMockUsed(true);
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long(333812512246L));

                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "isAccountIdSet",
                        new Class[] {}, Boolean.TRUE);

                WEB3AdminInformProfDistRegistVoucherMakeService l_WEB3AdminInformProfDistRegistVoucherMakeService =
                    (WEB3AdminInformProfDistRegistVoucherMakeService)Services.getService(WEB3AdminInformProfDistRegistVoucherMakeService.class);

                WEB3GenResponse l_response =
                    l_WEB3AdminInformProfDistRegistVoucherMakeService.execute(l_request);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * "get各種連絡行（）.区分５ == 1の場合
         * is銀行登録伝票（） == false の場合
         * isトリガ発行 == true の場合
         * 1)update各種連絡変更情報(各種連絡Params, 各種連絡Params, String, String)參數驗證
         * データコード：　@= GI828（郵貯）"
         */
        public void testSubmitAccountInfoChgCmp_case013()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoChgCmp_case013";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherChgCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherChgCmpRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";
                WEB3InformDetailInfoUnit l_unit = new WEB3InformDetailInfoUnit();
                l_unit.informType = "1";
                l_unit.branchCode = "624";
                l_unit.accountNumber = "123456";
                l_unit.institutionCode = "60";
                l_unit.num1 = "1";
                l_unit.num2 = "2";
                l_unit.num3 = "3";
                l_unit.num4 = "4";
                l_unit.num5 = "5";
                l_unit.num6 = "6";
                l_unit.num7 = "7";
                l_unit.num8 = "8";
                l_unit.num9 = "9";
                l_unit.num10 = "10";
                l_unit.num11 = "11";
                l_unit.num12 = "12";
                l_unit.num13 = "13";
                l_unit.num14 = "14";
                l_unit.num15 = "15";
                l_unit.num16 = "16";
                l_unit.num17 = "17";
                l_unit.num18 = "18";
                l_unit.num19 = "19";
                l_unit.num20 = "20";
                l_unit.num21 = "21";
                l_unit.num22 = "22";
                l_unit.num23 = "23";
                l_unit.num24 = "24";
                l_unit.num25 = "25";
                l_unit.num26 = "26";
                l_unit.num27 = "27";
                l_unit.num28 = "28";
                l_unit.num29 = "29";
                l_unit.num30 = "30";
                l_unit.div4 = "4";
                l_unit.div2 = "4";
                l_unit.div3 = "2";
                l_unit.div5 = "1";
                l_unit.code1 = "1234567";
                l_unit.code3 = "2";
                l_unit.code4 = "3";
                l_request.informInfoUnit = l_unit;

                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
                l_mainAccountParams.setInstitutionCode("60");
                l_mainAccountParams.setBranchCode("624");
                l_mainAccountParams.setAccountCode("123456");
                TestDBUtility.insertWithDel(l_mainAccountParams);

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
                WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(GtlUtils.getSystemTimestamp(), "1");
                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                VariousInformParams l_variousInformParams = getVariousInformRow();
                l_variousInformParams.setBranchCode("624");
                l_variousInformParams.setInstitutionCode("60");
                l_variousInformParams.setInformDiv("1");
                l_variousInformParams.setRequestNumber("234");
                l_variousInformParams.setStatus("4");
                l_variousInformParams.setAccountCode("123456");
                l_variousInformParams.setRequestCode("123");
                l_variousInformParams.setExtDiv5("1");
                l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_variousInformParams);

                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                MainAccountParams l_mainAccountRow = TestDBUtility.getMainAccountRow();
                l_mainAccountRow.setInstitutionCode("60");
                l_mainAccountRow.setBranchCode("624");
                l_mainAccountRow.setAccountCode("123456");
                l_mainAccountRow.setOnlyMblOpnDivTimestamp(GtlUtils.getSystemTimestamp());
                l_mainAccountRow.setOnlyMblOpnDivLastUpdater("123");
                l_mainAccountRow.setOnlyMobileOpenDiv("1");
                TestDBUtility.insertWithDel(l_mainAccountRow);
                
                TestDBUtility.deleteAll(BranchParams.TYPE);
                BranchParams l_branchParams = TestDBUtility.getBranchRow();
                l_branchParams.setBranchCode("123");
                l_branchParams.setInstitutionCode("0D");
                TestDBUtility.insertWithDel(l_branchParams);
                
                TradingTimeParams l_tradingTimeParmas = TestDBUtility.getTradingTimeRow();
                l_tradingTimeParmas.setInstitutionCode("0D");
                l_tradingTimeParmas.setBranchCode("123");
                l_tradingTimeParmas.setMarketCode("0");
                l_tradingTimeParmas.setTradingTimeType("35");
                l_tradingTimeParmas.setProductCode("0");
                l_tradingTimeParmas.setBizDateType("1");
                l_tradingTimeParmas.setStartTime("000000");
                l_tradingTimeParmas.setEndTime("235959");
                l_tradingTimeParmas.setSubmitMarketTrigger("1");
                l_tradingTimeParmas.setEnableOrder("0");
                l_tradingTimeParmas.setBizDateType("1");
                l_tradingTimeParmas.setBizdateCalcParameter("1");
                TestDBUtility.insertWithDel(l_tradingTimeParmas);

                FinInstitutionBankParams l_finInstitutionBankParams = new FinInstitutionBankParams();
                l_finInstitutionBankParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_finInstitutionBankParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_finInstitutionBankParams.setFinBranchCode("3");
                l_finInstitutionBankParams.setFinBranchName("sss");
                l_finInstitutionBankParams.setFinBranchNameKana("ddd");
                l_finInstitutionBankParams.setFinInstitutionCode("2");
                l_finInstitutionBankParams.setFinInstitutionName("fff");
                l_finInstitutionBankParams.setFinInstitutionNameKana("ggg");
                TestDBUtility.insertWithDel(l_finInstitutionBankParams);
                WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
                MOCK_MANAGER.setIsMockUsed(true);
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long(333812512246L));
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "isAccountIdSet",
                        new Class[] {}, Boolean.TRUE);
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "webbroker3.system.tune.affinity.WEB3OrderReqNumberHead2ManageServiceImpl",
                        "getOrderReqNumberHead2",
                        new Class[] {},
                        "00");
                WEB3AdminInformProfDistRegistVoucherMakeService l_WEB3AdminInformProfDistRegistVoucherMakeService =
                    (WEB3AdminInformProfDistRegistVoucherMakeService)Services.getService(WEB3AdminInformProfDistRegistVoucherMakeService.class);

                WEB3GenResponse l_response =
                    l_WEB3AdminInformProfDistRegistVoucherMakeService.execute(l_request);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * "引数.リクエストデータ　@== nullの場合
         * 
         * 1)引数.管理者・口座伝票変更確認リクエスト= null
         * 抛SYSTEM_ERROR_80017異常   異常
         */
        public void testValidateAccountInfoCancCnf_case001()
        {
            final String STR_METHOD_NAME = " testValidateAccountInfoCancCnf_case001";
            log.entering(STR_METHOD_NAME);

            try
            {
                WEB3AdminInformProfDistVoucherCancCnfRequest l_request = null;
                l_impl.validateAccountInfoCancCnf(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * validate権限抛異常の場合 刪除管理者権限テーブル中所有記
         * 抛BUSINESS_ERROR_01056異常 異常
         */
        public void testValidateAccountInfoCancCnf_case002()
        {
            final String STR_METHOD_NAME = " testValidateAccountInfoCancCnf_case002";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherCancCnfRequest l_request =
                    new WEB3AdminInformProfDistVoucherCancCnfRequest();

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
                TestDBUtility.deleteAll(AdminPermissionRow.TYPE);

                l_impl.validateAccountInfoCancCnf(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * validate注文受付可能抛異常の場合
         * 取引時間コンテキスト.証券会社コード為null
         * 取引時間コンテキスト.部店コード為null
         * 取引時間コンテキスト.受付時間区分為null
         * 抛SYSTEM_ERROR_80006異常
         */
        public void testValidateAccountInfoCancCnf_case003()
        {
            final String STR_METHOD_NAME = " testValidateAccountInfoCancCnf_case003";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherCancCnfRequest l_request =
                    new WEB3AdminInformProfDistVoucherCancCnfRequest();

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);

                l_impl.validateAccountInfoCancCnf(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * リクエストデータの簡易チェック抛異常の場合
         * リクエストデータ.部店コード=null
         * 抛BUSINESS_ERROR_02174異常
         */
        public void testValidateAccountInfoCancCnf_case004()
        {
            final String STR_METHOD_NAME = " testValidateAccountInfoCancCnf_case004";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherCancCnfRequest l_request =
                    new WEB3AdminInformProfDistVoucherCancCnfRequest();

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                l_impl.validateAccountInfoCancCnf(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * get各種連絡行()戻り値がnullの場合
         * 例外をスロー 清空各種連絡テーブル中所有
         * 抛BUSINESS_ERROR_00398異常
         */
        public void testValidateAccountInfoCancCnf_case005()
        {
            final String STR_METHOD_NAME = " testValidateAccountInfoCancCnf_case005";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherCancCnfRequest l_request =
                    new WEB3AdminInformProfDistVoucherCancCnfRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                TestDBUtility.deleteAll(VariousInformParams.TYPE);
                l_impl.validateAccountInfoCancCnf(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * validate伝票取消抛異常の場合
         * 1）isトリガ発行的返回?為true (mock) 抛BUSINESS_ERROR_02798異常
         */
        public void testValidateAccountInfoCancCnf_case006()
        {
            final String STR_METHOD_NAME = " testValidateAccountInfoCancCnf_case006";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherCancCnfRequest l_request =
                    new WEB3AdminInformProfDistVoucherCancCnfRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
                WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(GtlUtils.getSystemTimestamp(), "1");
                VariousInformParams l_variousInformParams = getVariousInformRow();
                l_variousInformParams.setBranchCode("624");
                l_variousInformParams.setInstitutionCode("60");
                l_variousInformParams.setInformDiv("1");
                l_variousInformParams.setRequestNumber("234");
                l_variousInformParams.setStatus("4");
                l_variousInformParams.setAccountCode("123");
                l_variousInformParams.setRequestCode("123");
                l_variousInformParams.setExtDiv5("5");
                l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_variousInformParams);

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

                l_impl.validateAccountInfoCancCnf(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02798, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * 各種連絡.各種連絡行.get区分５ = 1：銀行振込 の場合   "1)金融機@関（銀行）マスタ表中存在 
         * 証券会社コード = 60
         * 部店コード= 624
         * 識別コード = 234
         * 連絡種別 = 1
         * 銀行コード = “2“
         * 支店コード = “3” 
         * 支店名 = “sss”
         * 支店名（カナ） = ""ddd""
         * 金融機@関名 = ""fff""
         * 金融機@関名（カナ） = ""ggg""的記?
         * コード３ = “2”
         * コード４ = “3”
         * 2）
         * 証券会社コード： get証券会社コード（）の戻り値 = 60
         * リクエストデータ.部店コード = 624
         * リクエストデータ.識別コード = 234
         * リクエストデータ.連絡種別 = 1"  "參數驗證：
         * get金融機@関情報(String, String, 各種金融機@関情報)
         * [引数]
         * 金融機@関コード=333
         * 支店コード=444
         * 金融機@関情報=null
         * 
         * 方法@返回?驗證：
         * 1）
         * レスポンスオブジェクト.金融機@関情報.支店名 = “sss”
         * レスポンスオブジェクト.金融機@関情報.支店名（カナ） = ""ddd""
         * レスポンスオブジェクト.金融機@関情報.金融機@関名 = ""fff""
         * レスポンスオブジェクト.金融機@関情報.金融機@関名（カナ） = ""ggg""的紀?
         * 2）
         * レスポンスオブジェクト.連絡情報.連絡種別=1
         * レスポンスオブジェクト.連絡情報.証券会社コード=60
         * レスポンスオブジェクト.連絡情報.部店コード=624
         */
        public void testValidateAccountInfoCancCnf_case007()
        {
            final String STR_METHOD_NAME = " testValidateAccountInfoCancCnf_case007";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherCancCnfRequest l_request =
                    new WEB3AdminInformProfDistVoucherCancCnfRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());
 
                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
                WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(GtlUtils.getSystemTimestamp(), "0");
                VariousInformParams l_variousInformParams = getVariousInformRow();
                l_variousInformParams.setBranchCode("624");
                l_variousInformParams.setInstitutionCode("60");
                l_variousInformParams.setInformDiv("1");
                l_variousInformParams.setRequestNumber("234");
                l_variousInformParams.setStatus("1");
                l_variousInformParams.setAccountCode("1234567");
                l_variousInformParams.setRequestCode("123");
                l_variousInformParams.setExtDiv5("1");
                l_variousInformParams.setExtCode3("2");
                l_variousInformParams.setExtCode4("3");
                l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_variousInformParams);
                TradingTimeParams l_tradingTimeParmas = TestDBUtility.getTradingTimeRow();
                l_tradingTimeParmas.setInstitutionCode("0D");
                l_tradingTimeParmas.setBranchCode("123");
                l_tradingTimeParmas.setMarketCode("N1");
                l_tradingTimeParmas.setTradingTimeType("01");
                l_tradingTimeParmas.setProductCode("0");
                l_tradingTimeParmas.setBizDateType("0");
                l_tradingTimeParmas.setStartTime("000000");
                l_tradingTimeParmas.setEndTime("235959");
                l_tradingTimeParmas.setSubmitMarketTrigger("1");
                l_tradingTimeParmas.setEnableOrder("0");
                l_tradingTimeParmas.setBizdateCalcParameter("1");
                TestDBUtility.insertWithDel(l_tradingTimeParmas);

                
                MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
                l_mainAccountParams.setInstitutionCode("60");
                l_mainAccountParams.setBranchCode("624");
                l_mainAccountParams.setAccountCode("1234567");
                l_mainAccountParams.setOnlyMblOpnDivTimestamp(GtlUtils.getSystemTimestamp());
                l_mainAccountParams.setOnlyMblOpnDivLastUpdater("123");
                l_mainAccountParams.setOnlyMobileOpenDiv("1");
                TestDBUtility.insertWithDel(l_mainAccountParams);

                FinInstitutionBankParams l_finInstitutionBankParams = new FinInstitutionBankParams();
                l_finInstitutionBankParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_finInstitutionBankParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_finInstitutionBankParams.setFinBranchCode("3");
                l_finInstitutionBankParams.setFinBranchName("sss");
                l_finInstitutionBankParams.setFinBranchNameKana("ddd");
                l_finInstitutionBankParams.setFinInstitutionCode("2");
                l_finInstitutionBankParams.setFinInstitutionName("fff");
                l_finInstitutionBankParams.setFinInstitutionNameKana("ggg");
                TestDBUtility.insertWithDel(l_finInstitutionBankParams);

                WEB3AdminInformProfDistVoucherCancCnfResponse l_response =
                    l_impl.validateAccountInfoCancCnf(l_request);

                String l_strFinancialBranchName =
                    l_response.financialInstitutionInfo.financialBranchName;
                String l_strFinancialBranchNameKana =
                    l_response.financialInstitutionInfo.financialBranchNameKana;
                String l_strFinancialInstitutionName =
                    l_response.financialInstitutionInfo.financialInstitutionName;
                String l_strFinancialInstitutionNameKana =
                    l_response.financialInstitutionInfo.financialInstitutionNameKana;

                assertEquals("sss", l_strFinancialBranchName);
                assertEquals("ddd", l_strFinancialBranchNameKana);
                assertEquals("fff", l_strFinancialInstitutionName);
                assertEquals("ggg", l_strFinancialInstitutionNameKana);

                //* レスポンスオブジェクト.連絡情報.連絡種別=1
                //* レスポンスオブジェクト.連絡情報.証券会社コード=60
                //* レスポンスオブジェクト.連絡情報.部店コード=624
                String l_strInstitutionCode = l_response.informInfoUnit.institutionCode;
                String l_strBranchCode = l_response.informInfoUnit.branchCode;
                String l_strInformType = l_response.informInfoUnit.informType;
                assertEquals("60", l_strInstitutionCode);
                assertEquals("624", l_strBranchCode);
                assertEquals("1", l_strInformType);

            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * "引数.リクエストデータ　@== nullの場合
         *  1)引数.管理者・口座伝票変更確認リクエスト= null
         *  抛SYSTEM_ERROR_80017異常
         */
        public void testSubmitAccountInfoCancCmp_case001()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoCancCmp_case001";
            log.entering(STR_METHOD_NAME);

            try
            {
                WEB3AdminInformProfDistVoucherCancCmpRequest l_request = null;
                l_impl.submitAccountInfoCancCmp(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * validate権限抛異常の場合 刪除管理者権限テーブル中所
         *  抛BUSINESS_ERROR_01056異常
         */
        public void testSubmitAccountInfoCancCmp_case002()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoCancCmp_case002";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherCancCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherCancCmpRequest();

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
                TestDBUtility.deleteAll(AdminPermissionRow.TYPE);

                l_impl.submitAccountInfoCancCmp(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * validate取引パスワード  validate取引パスワード （mock實現
         *  抛BUSINESS_ERROR_00009異常
         */
        public void testSubmitAccountInfoCancCmp_case003()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoCancCmp_case003";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherCancCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherCancCmpRequest();

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
                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_adminPermissionParams);

                WEB3AdministratorForMock.mockValidateTradingPassword("123", false);
                l_impl.submitAccountInfoCancCmp(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * validate注文受付可能抛異常の場合
         * "取引時間コンテキスト.証券会社コード為null
         * 取引時間コンテキスト.部店コード為null
         * 取引時間コンテキスト.受付時間区分為null
         * 抛SYSTEM_ERROR_80006異常
         */
        public void testSubmitAccountInfoCancCmp_case004()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoCancCmp_case004";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherCancCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherCancCmpRequest();

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);

                l_impl.submitAccountInfoCancCmp(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * リクエストデータの簡易チェック抛異常の場合
         * リクエストデータ.部店コード=null
         * 抛BUSINESS_ERROR_02174異常
         */
        public void testSubmitAccountInfoCancCmp_case005()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoCancCmp_case005";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherCancCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherCancCmpRequest();

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                l_impl.submitAccountInfoCancCmp(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * get各種連絡行()戻り値がnullの場合
         * 例外をスロー 清空各種連絡テーブル中所有
         *  抛BUSINESS_ERROR_00398異常
         */
        public void testSubmitAccountInfoCancCmp_case006()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoCancCmp_case006";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherCancCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherCancCmpRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                TestDBUtility.deleteAll(VariousInformParams.TYPE);
                l_impl.submitAccountInfoCancCmp(l_request);
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * get各種連絡行()戻り値がnullの場合
         * 例外をスロー 清空各種連絡テーブル中所有
         *  抛BUSINESS_ERROR_00398異常
         */
        public void testSubmitAccountInfoCancCmp_case007()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoCancCmp_case007";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherCancCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherCancCmpRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                VariousInformParams l_variousInformParams = getVariousInformRow();
                l_variousInformParams.setBranchCode("624");
                l_variousInformParams.setInstitutionCode("60");
                l_variousInformParams.setInformDiv("1");
                l_variousInformParams.setRequestNumber("234");
                l_variousInformParams.setStatus("1");
                l_variousInformParams.setAccountCode("1234567");
                l_variousInformParams.setRequestCode("123");
                l_variousInformParams.setExtDiv5("1");
                l_variousInformParams.setExtCode3("2");
                l_variousInformParams.setExtCode4("3");
                l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_variousInformParams);

                l_impl.submitAccountInfoCancCmp(l_request);
                
                String p_institutionCode = "60";
                String p_informDiv = "1";
                String p_requestNumber = "234";
                String p_branchCode = "624";
                VariousInformRow l_row =
                    VariousInformDao.findRowByPk(p_institutionCode, p_informDiv, p_requestNumber, p_branchCode);
                assertEquals("123456789", l_row.getLastUpdater());
                assertEquals("0", l_row.getStatus());
                log.debug("l_row.getLastUpdatedTimestamp()=" + l_row.getLastUpdatedTimestamp());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * is銀行登録伝票（） == true の場合
         */
        public void testSubmitAccountInfoCancCmp_case008()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoCancCmp_case008";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherCancCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherCancCmpRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                VariousInformParams l_variousInformParams = getVariousInformRow();
                l_variousInformParams.setBranchCode("624");
                l_variousInformParams.setInstitutionCode("60");
                l_variousInformParams.setInformDiv("1");
                l_variousInformParams.setRequestNumber("234");
                l_variousInformParams.setStatus("1");
                l_variousInformParams.setAccountCode("1234567");
                l_variousInformParams.setRequestCode("123");
                l_variousInformParams.setExtDiv5("1");
                l_variousInformParams.setExtCode3("2");
                l_variousInformParams.setExtCode4("3");
                l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_variousInformParams);

                l_impl.submitAccountInfoCancCmp(l_request);
                
                String p_institutionCode = "60";
                String p_informDiv = "1";
                String p_requestNumber = "234";
                String p_branchCode = "624";
                VariousInformRow l_row =
                    VariousInformDao.findRowByPk(p_institutionCode, p_informDiv, p_requestNumber, p_branchCode);
                assertEquals("123456789", l_row.getLastUpdater());
                assertEquals("0", l_row.getStatus());
                log.debug("l_row.getLastUpdatedTimestamp()=" + l_row.getLastUpdatedTimestamp());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }

        /*
         * is銀行登録伝票（） == false の場合
         */
        public void testSubmitAccountInfoCancCmp_case009()
        {
            final String STR_METHOD_NAME = " testSubmitAccountInfoCancCmp_case009";
            log.entering(STR_METHOD_NAME);

            try
            {
                MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminInformProfDistVoucherCancCmpRequest l_request =
                    new WEB3AdminInformProfDistVoucherCancCmpRequest();
                l_request.branchCode = "624";
                l_request.informType = "1";
                l_request.requestNumber = "234";

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

                AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
                l_adminPermissionParams.setInstitutionCode("60");
                l_adminPermissionParams.setPermissionLevel("01");
                l_adminPermissionParams.setTransactionCategory("A0105");
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
                l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

                TestDBUtility.insertWithDel(l_adminPermissionParams);
                WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
                WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

                VariousInformParams l_variousInformParams = getVariousInformRow();
                l_variousInformParams.setBranchCode("624");
                l_variousInformParams.setInstitutionCode("60");
                l_variousInformParams.setInformDiv("1");
                l_variousInformParams.setRequestNumber("234");
                l_variousInformParams.setStatus("1");
                l_variousInformParams.setAccountCode("1234567");
                l_variousInformParams.setRequestCode("123");
                l_variousInformParams.setExtDiv5("5");
                l_variousInformParams.setExtCode3("2");
                l_variousInformParams.setExtCode4("3");
                l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                TestDBUtility.insertWithDel(l_variousInformParams);

                l_impl.submitAccountInfoCancCmp(l_request);
                
                String p_institutionCode = "60";
                String p_informDiv = "1";
                String p_requestNumber = "234";
                String p_branchCode = "624";
                VariousInformRow l_row =
                    VariousInformDao.findRowByPk(p_institutionCode, p_informDiv, p_requestNumber, p_branchCode);
                assertEquals("123456789", l_row.getLastUpdater());
                assertEquals("0", l_row.getStatus());
                log.debug("l_row.getLastUpdatedTimestamp()=" + l_row.getLastUpdatedTimestamp());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }
        
        
    public static InformCtrlItemMasterParams getInformCtrlItemMasterRow()
    {
        InformCtrlItemMasterParams informCtrlItemMasterParams = new InformCtrlItemMasterParams();
        informCtrlItemMasterParams.setBranchCode("000");
        informCtrlItemMasterParams.setInstitutionCode("123");
        informCtrlItemMasterParams.setInformDiv("123");
        informCtrlItemMasterParams.setItemSymbolName("123");
        informCtrlItemMasterParams.setNecessaryFlag(1);
        return informCtrlItemMasterParams;
    }

    public static VariousInformParams getVariousInformRow()
    {
        VariousInformParams variousInformParams = new VariousInformParams();
        variousInformParams.setBranchCode("000");
        variousInformParams.setInstitutionCode("123");
        variousInformParams.setInformDiv("123");
        variousInformParams.setRequestNumber("123");
        variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        return variousInformParams;
    }

    public class WEB3AdminInformProfDistRegistVoucherMakeServiceImplForTest extends WEB3AdminInformProfDistRegistVoucherMakeServiceImpl
    {
        /**
         * (update各種連絡変更情報)<BR>
         * 各種連絡の変更情報を更新する。<BR>
         * <BR>
         * DB更新仕様「分配金振替口座伝票変更_各種連絡テーブル.xls」参照<BR>
         * <BR>
         * ［更新条件］<BR>
         * 証券会社コード = 変更前各種連絡行.get証券会社コード（）<BR>
         * 連絡種別 = 変更前各種連絡行.get連絡種別（）<BR>
         * 識別コード = 変更前各種連絡行.get識別コード（）<BR>
         * 部店コード = 変更前各種連絡行.get部店コード（）<BR>
         * @@param l_beforeChgVariousInformParams - (変更前各種連絡行)<BR>
         * 変更前各種連絡行
         * @@param l_afterChgVariousInformParams - (変更後各種連絡行)<BR>
         * 変更後各種連絡行
         * @@param l_strRequestNumber - (識別コード)<BR>
         * 識別コード
         * @@param l_strDataCode - (データコード)<BR>
         * データコード
         * @@throws WEB3BaseException
         * @@roseuid 46539C9401E9
         */
        private void updateInformDetailInfoUnitChgInfo(
            VariousInformParams l_beforeChgVariousInformParams,
            VariousInformParams l_afterChgVariousInformParams,
            String l_strRequestNumber,
            String l_strDataCode) throws WEB3BaseException
        {
            assertEquals("GI823", l_strDataCode);
            log.debug("l_strRequestNumber" + l_strRequestNumber);
        }
    }

    private void deleteDate() throws WEB3BaseException
    {
        TestDBUtility.deleteAll(TradingTimeParams.TYPE);
        TestDBUtility.deleteAll(VariousInformParams.TYPE);
        TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
        TestDBUtility.deleteAll(AdministratorParams.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
    }

}
@
