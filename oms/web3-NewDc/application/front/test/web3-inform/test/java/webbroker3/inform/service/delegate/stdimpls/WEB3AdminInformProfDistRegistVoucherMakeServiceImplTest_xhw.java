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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xhw.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/07 ���G�� (���u) �V�K�쐬
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
 * XXXXXX�N���X//TODO
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xhw extends TestBaseForMock
{
    /**
     * (���O�o�̓��[�e�B���e�B)
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
     * "����.���N�G�X�g�f�[�^�@@== null�̏ꍇ
     * "   1)����.�Ǘ��ҁE�����`�[�ύX�m�F���N�G�X�g= null
     *     �eSYSTEM_ERROR_80017�ُ�
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
     * validate�����e�ُ�̏ꍇ �����Ǘ��Ҍ����e�[�u�������L�L
     *  �eBUSINESS_ERROR_01056�ُ�
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
     * validate������t�\�e�ُ�̏ꍇ
     * WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false)���@@�I�^�p
     * �eSYSTEM_ERROR_80006�ُ�
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
     * ���N�G�X�g�f�[�^�̊ȈՃ`�F�b�N�e�ُ�̏ꍇ
     * ���N�G�X�g�f�[�^.���X�R�[�h=null
     * �eBUSINESS_ERROR_02174�ُ�
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
     * get�e��A���s()�߂�l��null�̏ꍇ
     * ��O���X���[ ����e��A���e�[�u�������L�L
     * �eBUSINESS_ERROR_00398�ُ�
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
     *  validate�`�[�ύX�e�ُ�̏ꍇ  "1�jis�g���K���s�I�ԉ�?��true (mock)
     *  2)get�e��A���s�i�j.get�쐬�󋵁i�j�̖߂�l = 2
     *  "   �eBUSINESS_ERROR_02786�ُ�
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
     *  validate�U����o�^���(�e��A�����)
     *  "1)���N�G�X�g�f�[�^.�A�����.�敪4 = 3�F�폜 �̏ꍇ
     *  2)���󎩓��U�֓o�^�}�X�^�[�\"    �eBUSINESS_ERROR_02784�ُ�
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
         *  validate�����R�[�h(�e��A�����)
         *  "1�j���N�G�X�g�f�[�^.�A�����.�敪�R = 2:�����M��
         *  2)���N�G�X�g�f�[�^.�A�����.�R�[�h�P �̃����O�X��9�o�C�g"    �eBUSINESS_ERROR_02785�ُ�
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
         * "�A�����.�敪�T = 1�F��s�U�� �̏ꍇ
         * get���Z�@@�֏��(String, String, �e����Z�@@�֏��)�i�s�ҝ���"
         *  "1�j�A�����.�R�[�h�R = "2"
         *  �A�����.�R�[�h2 = "4"
         *  2�j�A�����.�R�[�h�S = 3
         *  �t����� = get�t�����i�j�̖߂�l
         *  ���Z�@@�ցi��s�j�}�X�^�\������
         *  ��s�R�[�h = �g2�g
         *  �x�X�R�[�h = �g3"
         *  �x�X�� = �gsss�h
         *  �x�X���i�J�i�j = "ddd"
         *  ���Z�@@�֖� = "fff"
         *  ���Z�@@�֖��i�J�i�j = "ggg""�I�L?" "�暕��@@�ԉ�?�F
         *  ���X�|���X�I�u�W�F�N�g.���Z�@@�֏��.�x�X�� = �gsss�h
         *  ���X�|���X�I�u�W�F�N�g.���Z�@@�֏��.�x�X���i�J�i�j = ""ddd""
         *  ���X�|���X�I�u�W�F�N�g.���Z�@@�֏��.���Z�@@�֖� = ""fff""
         *  ���X�|���X�I�u�W�F�N�g.���Z�@@�֏��.���Z�@@�֖��i�J�i�j = ""ggg""�I�I?"  ����
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
         * "����.���N�G�X�g�f�[�^�@@== null�̏ꍇ
         * "   1)����.�Ǘ��ҁE�����`�[�ύX�m�F���N�G�X�g= null
         *     �eSYSTEM_ERROR_80017�ُ�
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
         * validate�����e�ُ�̏ꍇ �����Ǘ��Ҍ����e�[�u�������L�L
         *  �eBUSINESS_ERROR_01056�ُ�
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
         *validate����p�X���[�h   validate����p�X���[�h �imock�����j
         *�eBUSINESS_ERROR_00009�ُ� 
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
         * validate������t�\�e�ُ�̏ꍇ
         * WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false)���@@�I�^�p
         * �eSYSTEM_ERROR_80006�ُ�
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
         * ���N�G�X�g�f�[�^�̊ȈՃ`�F�b�N�e�ُ�̏ꍇ
         * ���N�G�X�g�f�[�^.���X�R�[�h=null
         * �eBUSINESS_ERROR_02174�ُ�
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
         * get�e��A���s()�߂�l��null�̏ꍇ�A
         * ��O���X���[ ����e��A���e�[�u�������L�L
         * �eBUSINESS_ERROR_00398�ُ�
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
         *  validate�`�[�ύX�e�ُ�̏ꍇ  "1�jis�g���K���s�I�ԉ�?��true (mock)
         *  2)get�e��A���s�i�j.get�쐬�󋵁i�j�̖߂�l = 2
         *  "   �eBUSINESS_ERROR_02786�ُ�
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
         * validate�U����o�^���(�e��A�����)
         * "1)���N�G�X�g�f�[�^.�A�����.�敪4 = 3�F�폜 �̏ꍇ
         * 2)���󎩓��U�֓o�^�}�X�^�[�\"
         * �eBUSINESS_ERROR_02784�ُ�
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
         * validate�����R�[�h(�e��A�����) 
         *  "1�j���N�G�X�g�f�[�^.�A�����.�敪�R = 2:�����M��
         * 2)���N�G�X�g�f�[�^.�A�����.�R�[�h�P �̃����O�X��9�o�C�g"
         * �eBUSINESS_ERROR_02785�ُ�
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
         * "�A�����.�敪�T = 1�F��s�U�� �̏ꍇ
         * is��s�o�^�`�[�i�j == true �̏ꍇ
         * is�g���K���s == true �̏ꍇ
         *"   ���N�G�X�g�f�[�^.�A�����.�،���ЃR�[�h = "123"
         *"1)update�e��A���ύX���(�e��A��Params, �e��A��Params, String, String)�ҝ���
         *�f�[�^�R�[�h�F�@@= �gGI823�g�i��s�j
         *2)�g���K���s(String, String)
         *�،���ЃR�[�h�F�@@���N�G�X�g�f�[�^.�A�����.�،���ЃR�[�h
         *�f�[�^�R�[�h�F�@@�hGI823�h�i��s�j"
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
         * "get�e��A���s�i�j.�敪�T == 5:�X���U�ւ̏ꍇ
         * is��s�o�^�`�[�i�j == false �̏ꍇ
         * is�g���K���s == false �̏ꍇ
         * 1)update�e��A���ύX���(�e��A��Params, �e��A��Params, String, String)�ҝ���
         * �f�[�^�R�[�h�F�@@= GI828�i�X���j"
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
         * "get�e��A���s�i�j.�敪�T == 5:�X���U�ւ̏ꍇ
         * is��s�o�^�`�[�i�j == false �̏ꍇ
         * is�g���K���s == true �̏ꍇ
         * 1)update�e��A���ύX���(�e��A��Params, �e��A��Params, String, String)�ҝ���
         * �f�[�^�R�[�h�F�@@= GI828�i�X���j"
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
         * "get�e��A���s�i�j.�敪�T == 1�̏ꍇ
         * is��s�o�^�`�[�i�j == false �̏ꍇ
         * is�g���K���s == true �̏ꍇ
         * 1)update�e��A���ύX���(�e��A��Params, �e��A��Params, String, String)�ҝ���
         * �f�[�^�R�[�h�F�@@= GI828�i�X���j"
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
         * "����.���N�G�X�g�f�[�^�@@== null�̏ꍇ
         * 
         * 1)����.�Ǘ��ҁE�����`�[�ύX�m�F���N�G�X�g= null
         * �eSYSTEM_ERROR_80017�ُ�   �ُ�
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
         * validate�����e�ُ�̏ꍇ �����Ǘ��Ҍ����e�[�u�������L�L
         * �eBUSINESS_ERROR_01056�ُ� �ُ�
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
         * validate������t�\�e�ُ�̏ꍇ
         * ������ԃR���e�L�X�g.�،���ЃR�[�h��null
         * ������ԃR���e�L�X�g.���X�R�[�h��null
         * ������ԃR���e�L�X�g.��t���ԋ敪��null
         * �eSYSTEM_ERROR_80006�ُ�
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
         * ���N�G�X�g�f�[�^�̊ȈՃ`�F�b�N�e�ُ�̏ꍇ
         * ���N�G�X�g�f�[�^.���X�R�[�h=null
         * �eBUSINESS_ERROR_02174�ُ�
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
         * get�e��A���s()�߂�l��null�̏ꍇ
         * ��O���X���[ ����e��A���e�[�u�������L
         * �eBUSINESS_ERROR_00398�ُ�
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
         * validate�`�[����e�ُ�̏ꍇ
         * 1�jis�g���K���s�I�ԉ�?��true (mock) �eBUSINESS_ERROR_02798�ُ�
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
         * �e��A��.�e��A���s.get�敪�T = 1�F��s�U�� �̏ꍇ   "1)���Z�@@�ցi��s�j�}�X�^�\������ 
         * �،���ЃR�[�h = 60
         * ���X�R�[�h= 624
         * ���ʃR�[�h = 234
         * �A����� = 1
         * ��s�R�[�h = �g2�g
         * �x�X�R�[�h = �g3�h 
         * �x�X�� = �gsss�h
         * �x�X���i�J�i�j = ""ddd""
         * ���Z�@@�֖� = ""fff""
         * ���Z�@@�֖��i�J�i�j = ""ggg""�I�L?
         * �R�[�h�R = �g2�h
         * �R�[�h�S = �g3�h
         * 2�j
         * �،���ЃR�[�h�F get�،���ЃR�[�h�i�j�̖߂�l = 60
         * ���N�G�X�g�f�[�^.���X�R�[�h = 624
         * ���N�G�X�g�f�[�^.���ʃR�[�h = 234
         * ���N�G�X�g�f�[�^.�A����� = 1"  "�ҝ��暁F
         * get���Z�@@�֏��(String, String, �e����Z�@@�֏��)
         * [����]
         * ���Z�@@�փR�[�h=333
         * �x�X�R�[�h=444
         * ���Z�@@�֏��=null
         * 
         * ���@@�ԉ�?�暁F
         * 1�j
         * ���X�|���X�I�u�W�F�N�g.���Z�@@�֏��.�x�X�� = �gsss�h
         * ���X�|���X�I�u�W�F�N�g.���Z�@@�֏��.�x�X���i�J�i�j = ""ddd""
         * ���X�|���X�I�u�W�F�N�g.���Z�@@�֏��.���Z�@@�֖� = ""fff""
         * ���X�|���X�I�u�W�F�N�g.���Z�@@�֏��.���Z�@@�֖��i�J�i�j = ""ggg""�I�I?
         * 2�j
         * ���X�|���X�I�u�W�F�N�g.�A�����.�A�����=1
         * ���X�|���X�I�u�W�F�N�g.�A�����.�،���ЃR�[�h=60
         * ���X�|���X�I�u�W�F�N�g.�A�����.���X�R�[�h=624
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

                //* ���X�|���X�I�u�W�F�N�g.�A�����.�A�����=1
                //* ���X�|���X�I�u�W�F�N�g.�A�����.�،���ЃR�[�h=60
                //* ���X�|���X�I�u�W�F�N�g.�A�����.���X�R�[�h=624
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
         * "����.���N�G�X�g�f�[�^�@@== null�̏ꍇ
         *  1)����.�Ǘ��ҁE�����`�[�ύX�m�F���N�G�X�g= null
         *  �eSYSTEM_ERROR_80017�ُ�
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
         * validate�����e�ُ�̏ꍇ �����Ǘ��Ҍ����e�[�u������
         *  �eBUSINESS_ERROR_01056�ُ�
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
         * validate����p�X���[�h  validate����p�X���[�h �imock����
         *  �eBUSINESS_ERROR_00009�ُ�
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
         * validate������t�\�e�ُ�̏ꍇ
         * "������ԃR���e�L�X�g.�،���ЃR�[�h��null
         * ������ԃR���e�L�X�g.���X�R�[�h��null
         * ������ԃR���e�L�X�g.��t���ԋ敪��null
         * �eSYSTEM_ERROR_80006�ُ�
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
         * ���N�G�X�g�f�[�^�̊ȈՃ`�F�b�N�e�ُ�̏ꍇ
         * ���N�G�X�g�f�[�^.���X�R�[�h=null
         * �eBUSINESS_ERROR_02174�ُ�
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
         * get�e��A���s()�߂�l��null�̏ꍇ
         * ��O���X���[ ����e��A���e�[�u�������L
         *  �eBUSINESS_ERROR_00398�ُ�
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
         * get�e��A���s()�߂�l��null�̏ꍇ
         * ��O���X���[ ����e��A���e�[�u�������L
         *  �eBUSINESS_ERROR_00398�ُ�
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
         * is��s�o�^�`�[�i�j == true �̏ꍇ
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
         * is��s�o�^�`�[�i�j == false �̏ꍇ
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
         * (update�e��A���ύX���)<BR>
         * �e��A���̕ύX�����X�V����B<BR>
         * <BR>
         * DB�X�V�d�l�u���z���U�֌����`�[�ύX_�e��A���e�[�u��.xls�v�Q��<BR>
         * <BR>
         * �m�X�V�����n<BR>
         * �،���ЃR�[�h = �ύX�O�e��A���s.get�،���ЃR�[�h�i�j<BR>
         * �A����� = �ύX�O�e��A���s.get�A����ʁi�j<BR>
         * ���ʃR�[�h = �ύX�O�e��A���s.get���ʃR�[�h�i�j<BR>
         * ���X�R�[�h = �ύX�O�e��A���s.get���X�R�[�h�i�j<BR>
         * @@param l_beforeChgVariousInformParams - (�ύX�O�e��A���s)<BR>
         * �ύX�O�e��A���s
         * @@param l_afterChgVariousInformParams - (�ύX��e��A���s)<BR>
         * �ύX��e��A���s
         * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
         * ���ʃR�[�h
         * @@param l_strDataCode - (�f�[�^�R�[�h)<BR>
         * �f�[�^�R�[�h
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
