head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.31.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d9c24957ec8;
filename	WEB3AccInfoOccupationChangeRegistVoucherCreatedTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �E�ƕύX�\���`�[�쐬�e�X�g (WEB3AccInfoOccupationChangeRegistVoucherCreatedTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/12 ���؎q (���u) �V�K�쐬
*/
package webbroker3.accountinfo;



import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountopen.data.TradeConditionVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AccountInfoMstParams;
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�E�ƕύX�\���`�[�쐬�e�X�g)<BR>
 * �E�ƕύX�\���`�[�쐬�N���X�e�X�g<BR>
 * <BR>
 * @@author ���؎q<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoOccupationChangeRegistVoucherCreatedTest extends
        TestBaseForMock {

    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoOccupationChangeRegistVoucherCreatedTest.class);

    public WEB3AccInfoOccupationChangeRegistVoucherCreatedTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * is�`�[�쐬<BR>
     * �`�[�쐬�̉ۂ𔻕ʂ���B<BR>
     * <BR>
     */
    public void test_isVoucherCreated_0001()
    {
        final String STR_METHOD_NAME = ".test_isVoucherCreated_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoOccupationChangeRegistVoucherCreated l_accInfoOccupationChangeRegistVoucherCreated =
            new WEB3AccInfoOccupationChangeRegistVoucherCreated();
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = new MobileOfficeInfoRegistParams();

        l_mobileOfficeInfoRegistParams.setMobileOfficeInfoRegistId(123456789L);
        l_mobileOfficeInfoRegistParams.setInstitutionCode("0D");
        l_mobileOfficeInfoRegistParams.setBranchId(33381L);
        l_mobileOfficeInfoRegistParams.setAccountId(333812512246L);
        l_mobileOfficeInfoRegistParams.setAccountCode("2450007");
        l_mobileOfficeInfoRegistParams.setRegistUpdater("333812512246");
        l_mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.FALSE);
        l_mobileOfficeInfoRegistParams.setLastUpdater("2450007");
        l_mobileOfficeInfoRegistParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mobileOfficeInfoRegistParams.setOccupationDiv("999999999");

        WEB3AccInfoMaster l_accInfoMaster = null;

        try
        {
            boolean l_blnVoucherCreated = l_accInfoOccupationChangeRegistVoucherCreated.isVoucherCreated(
                l_mobileOfficeInfoRegistParams, l_accInfoMaster);
            assertTrue(l_blnVoucherCreated);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * is�`�[�쐬<BR>
     * �`�[�쐬�̉ۂ𔻕ʂ���B<BR>
     * <BR>
     */
    public void test_isVoucherCreated_0002()
    {
        final String STR_METHOD_NAME = ".test_isVoucherCreated_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoOccupationChangeRegistVoucherCreated l_accInfoOccupationChangeRegistVoucherCreated =
            new WEB3AccInfoOccupationChangeRegistVoucherCreated();
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = new MobileOfficeInfoRegistParams();
        AccountInfoMstParams l_accountInfoMstParams = new AccountInfoMstParams();

        l_accountInfoMstParams.setAccountCode("2450007");

        l_mobileOfficeInfoRegistParams.setMobileOfficeInfoRegistId(123456789L);
        l_mobileOfficeInfoRegistParams.setInstitutionCode("0D");
        l_mobileOfficeInfoRegistParams.setBranchId(33381L);
        l_mobileOfficeInfoRegistParams.setAccountId(333812512246L);
        l_mobileOfficeInfoRegistParams.setAccountCode("2450007");
        l_mobileOfficeInfoRegistParams.setRegistUpdater("333812512246");
        l_mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.FALSE);
        l_mobileOfficeInfoRegistParams.setLastUpdater("2450007");
        l_mobileOfficeInfoRegistParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mobileOfficeInfoRegistParams.setOccupationDiv("999999999");

        WEB3AccInfoMaster l_accInfoMaster = new WEB3AccInfoMaster(l_accountInfoMstParams);

        try
        {
            boolean l_blnVoucherCreated = l_accInfoOccupationChangeRegistVoucherCreated.isVoucherCreated(
                l_mobileOfficeInfoRegistParams, l_accInfoMaster);
            assertTrue(l_blnVoucherCreated);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * is�`�[�쐬<BR>
     * �`�[�쐬�̉ۂ𔻕ʂ���B<BR>
     * <BR>
     */
    public void test_isVoucherCreated_0003()
    {
        final String STR_METHOD_NAME = ".test_isVoucherCreated_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoOccupationChangeRegistVoucherCreated l_accInfoOccupationChangeRegistVoucherCreated =
            new WEB3AccInfoOccupationChangeRegistVoucherCreated();
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = new MobileOfficeInfoRegistParams();
        AccountInfoMstParams l_accountInfoMstParams = new AccountInfoMstParams();

        l_accountInfoMstParams.setOccupationDiv("888888888");

        l_mobileOfficeInfoRegistParams.setMobileOfficeInfoRegistId(123456789L);
        l_mobileOfficeInfoRegistParams.setInstitutionCode("0D");
        l_mobileOfficeInfoRegistParams.setBranchId(33381L);
        l_mobileOfficeInfoRegistParams.setAccountId(333812512246L);
        l_mobileOfficeInfoRegistParams.setAccountCode("2450007");
        l_mobileOfficeInfoRegistParams.setRegistUpdater("333812512246");
        l_mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.FALSE);
        l_mobileOfficeInfoRegistParams.setLastUpdater("2450007");
        l_mobileOfficeInfoRegistParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mobileOfficeInfoRegistParams.setOccupationDiv("999999999");

        WEB3AccInfoMaster l_accInfoMaster = new WEB3AccInfoMaster(l_accountInfoMstParams);

        try
        {
            boolean l_blnVoucherCreated = l_accInfoOccupationChangeRegistVoucherCreated.isVoucherCreated(
                l_mobileOfficeInfoRegistParams, l_accInfoMaster);
            assertTrue(l_blnVoucherCreated);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * is�`�[�쐬<BR>
     * �`�[�쐬�̉ۂ𔻕ʂ���B<BR>
     * <BR>
     */
    public void test_isVoucherCreated_0004()
    {
        final String STR_METHOD_NAME = ".test_isVoucherCreated_0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoOccupationChangeRegistVoucherCreated l_accInfoOccupationChangeRegistVoucherCreated =
            new WEB3AccInfoOccupationChangeRegistVoucherCreated();
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = new MobileOfficeInfoRegistParams();
        
        l_mobileOfficeInfoRegistParams.setMobileOfficeInfoRegistId(123456789L);
        l_mobileOfficeInfoRegistParams.setInstitutionCode("0D");
        l_mobileOfficeInfoRegistParams.setBranchId(33381L);
        l_mobileOfficeInfoRegistParams.setAccountId(333812512246L);
        l_mobileOfficeInfoRegistParams.setAccountCode("2450007");
        l_mobileOfficeInfoRegistParams.setRegistUpdater("333812512246");
        l_mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.FALSE);
        l_mobileOfficeInfoRegistParams.setLastUpdater("2450007");
        l_mobileOfficeInfoRegistParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        WEB3AccInfoMaster l_accInfoMaster = null;

        try
        {
            boolean l_blnVoucherCreated = l_accInfoOccupationChangeRegistVoucherCreated.isVoucherCreated(
                l_mobileOfficeInfoRegistParams, l_accInfoMaster);
            assertTrue(!l_blnVoucherCreated);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�E�ƕύX�`�[)<BR>
     * �E�ƕύX�ɔ�����c�E�d�q��t�E��������`�[�iGI844�j���쐬����B<BR>
     */
    public void test_createOccupationChangeVoucher_0001()
    {
        final String STR_METHOD_NAME = ".test_isVoucherCreated_0003()";
        log.entering(STR_METHOD_NAME);

        MainAccountAllRow l_mainAccountAllRow = null;
        WEB3AccInfoOccupationChangeRegistVoucherCreated l_accInfoOccupationChangeRegistVoucherCreated =
            new WEB3AccInfoOccupationChangeRegistVoucherCreated(l_mainAccountAllRow);
        MainAccountParams l_mainAccountRow = this.getMainAccountRow();
        TradeConditionVoucherParams l_tradeConditionVoucherParams = new TradeConditionVoucherParams();

        try
        {
            TestDBUtility.deleteAll(l_tradeConditionVoucherParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_mainAccountRow);
        }
        catch (Exception e)
        {
            fail();
        }

        try
        {
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(33L, "38", "2512246");

            l_accInfoOccupationChangeRegistVoucherCreated.createOccupationChangeVoucher(11111111L, "88", l_mainAccount);

            QueryProcessor l_qp = Processors.getDefaultProcessor();

            List l_listTradeConditionVoucher = l_qp.doFindAllQuery(l_tradeConditionVoucherParams.getRowType());

            TradeConditionVoucherParams l_tradeConditionVoucherParamsForTest =
                (TradeConditionVoucherParams)l_listTradeConditionVoucher.get(0);

            assertEquals("0D", l_tradeConditionVoucherParamsForTest.getInstitutionCode());
            assertEquals("38", l_tradeConditionVoucherParamsForTest.getBranchCode());
            assertEquals("2512246", l_tradeConditionVoucherParamsForTest.getAccountCode());
            assertEquals("11124", l_tradeConditionVoucherParamsForTest.getTraderCode());
            assertNull(l_tradeConditionVoucherParamsForTest.getTradingEReportDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getInvEReportDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getPosReportTermDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getPosReportCycleDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getPosReportCertifDepoDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getPosReportAccStateDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getEquityTaxDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getEquityTaxDivNext());
            assertNull(l_tradeConditionVoucherParamsForTest.getEquitySpAccOpenDat());
            assertNull(l_tradeConditionVoucherParamsForTest.getMarginTaxDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getMarginTaxDivNext());
            assertNull(l_tradeConditionVoucherParamsForTest.getMarginSpAccOpenDat());
            assertNull(l_tradeConditionVoucherParamsForTest.getSpMngAccOpenDiv());
            assertEquals("88", l_tradeConditionVoucherParamsForTest.getOccupationDiv());
            assertEquals("1", l_tradeConditionVoucherParamsForTest.getRegistDiv());
            assertEquals(11111111L, l_tradeConditionVoucherParamsForTest.getMobileOfficeInfoRegistId());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ڋq�}�X�^�[Row���쐬.<BR>
     */
    public MainAccountParams getMainAccountRow()
    {
        MainAccountParams l_mainAccountParams = new MainAccountParams();

        // �����h�c
        l_mainAccountParams.setAccountId(333812512246L);
        // �،���ЃR�[�h
        l_mainAccountParams.setInstitutionCode("0D");
        // �،����ID
        l_mainAccountParams.setInstitutionId(33L);
        // �����R�[�h
        l_mainAccountParams.setAccountCode("2512246");
        // ���X�h�c
        l_mainAccountParams.setBranchId(33381L);
        // ���X�R�[�h
        l_mainAccountParams.setBranchCode("38");
        // ���҃R�[�h�iSONAR�j
        l_mainAccountParams.setSonarTraderCode("11124");
        // �����^�C�v
        l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
        // ���O�i�c���j
        l_mainAccountParams.setFamilyName("�����@@�l�Y");
        // ���O�i�c��1�j
        l_mainAccountParams.setFamilyNameAlt1("Ųĳ �۳");
        // ���O�i���O1�j
        l_mainAccountParams.setGivenNameAlt1("Siro");
        // �X�֔ԍ�
        l_mainAccountParams.setZipCode("1001238");
        // �i�⏕�j�X�֔ԍ�
        l_mainAccountParams.setSubZipCode("1001238");
        // �Z���P
        l_mainAccountParams.setAddressLine1("�����s");
        // �Z���Q
        l_mainAccountParams.setAddressLine2("�]����");
        // �Z���R
        l_mainAccountParams.setAddressLine3("�[��T");
        // �d�b�ԍ�
        l_mainAccountParams.setTelephone("38201115");
        // �A����d�b�ԍ��i�g�сj
        l_mainAccountParams.setMobile("901115");
        // �e�`�w�ԍ�
        l_mainAccountParams.setFax("38202226");
        // ������胁�[�����M�t���O
        l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
        // ��������胁�[�����M�t���O
        l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
        // �敨OP��胁�[�����M�t���O
        l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
        // �敨OP����胁�[�����M�t���O
        l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
        // �ē����[�����M�t���O
        l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
        // ���Z�^�񋏏Z�敪
        l_mainAccountParams.setResident("0");
        // �V�K�����敪
        l_mainAccountParams.setNewAccountDiv("0");
        // �M���o�R�敪
        l_mainAccountParams.setViaTrustBankDiv("0");
        // email�A�h���X
        l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
        // ����p�X���[�h
        l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
        // �����o�^��
        l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
        // ��������
        l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
        // �{�l�m�F�敪
        l_mainAccountParams.setPersonIdentify("1");
        // ���N�����N��
        l_mainAccountParams.setEraBorn("3");
        // ���N����
        l_mainAccountParams.setBornDate("101013");
        // ����
        l_mainAccountParams.setSex("1");
        // �x�q�敪
        l_mainAccountParams.setYellowCustomer("0");
        // �z�[���g���[�h���ϕ��@@
        l_mainAccountParams.setHtSettlementWay("0");
        // �U����i��s�����j�o�^�敪
        l_mainAccountParams.setBankAccountRegi("0");
        // �����X�e�[�^�X
        l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
        // �l�����b�N
        l_mainAccountParams.setExaminLockFlag("0");
        // �Ǘ����b�N
        l_mainAccountParams.setMngLockFlag("0");
        // �Ǘ����b�N���R�t���O�i���֋��j
        l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
        // �Ǘ����b�N���R�t���O�i�ۏ؋������j
        l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
        // �Ǘ����b�N���R�t���O�i�K�i�S�ەs���j
        l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
        // �Ǘ����b�N���R�t���O�i�a��ؒ����������j
        l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
        // �x�X���b�N
        l_mainAccountParams.setBranchLock("0");
        // �����F��
        l_mainAccountParams.setOrderPermission("0");
        // �ŋ敪
        l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
        // �ŋ敪�i���N�j
        l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
        // �M�p����ŋ敪
        l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
        // �M�p����ŋ敪�i���N�j
        l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
        // �K�i�@@�֓����Ƌ敪
        l_mainAccountParams.setQualifiedInstInvestorDiv("0");
        // ���x�M�p��������J�݋敪
        l_mainAccountParams.setMarginSysAccOpenDiv("0");
        // ��ʐM�p��������J�݋敪
        l_mainAccountParams.setMarginGenAccOpenDiv("0");
        // ����������������J�ݓ�
        l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
        // �O���،������J�݋敪
        l_mainAccountParams.setForeignSecAccOpenDiv("1");
        // �敨OP�����J�݋敪�i���؁j
        l_mainAccountParams.setIfoAccOpenDivTokyo("0");
        // �敨OP�����J�݋敪�i��؁j
        l_mainAccountParams.setIfoAccOpenDivOsaka("0");
        // �敨OP�����J�݋敪�i���؁j
        l_mainAccountParams.setIfoAccOpenDivNagoya("0");
        // �ݓ������J�݋敪
        l_mainAccountParams.setRuitoAccOpenDiv("0");
        // �l�q�e�����J�݋敪
        l_mainAccountParams.setMrfAccOpenDiv("0");
        // �e�w�����J�݋敪
        l_mainAccountParams.setFxAccOpenDiv("0");
        // �O�������A�g�����J�݋敪
        l_mainAccountParams.setFeqConAccOpenDiv("0");
        // �擪���ID
        l_mainAccountParams.setTopPageId("0");
        // �����擾�敪
        l_mainAccountParams.setQuotoType("0");
        // ����񍐏���t�敪
        l_mainAccountParams.setTradingReportDiv("1");
        // ����c���񍐏���t�敪
        l_mainAccountParams.setPositionReportDiv("9");
        // ����c���񍐏��쐬�����敪
        l_mainAccountParams.setPositionReportCycleDiv("1");
        // ����c���񍐏��a��؍쐬�t���O
        l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
        // ����c���񍐏��v�Z���쐬�t���O
        l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
        // email�A�h���X�X�V�҃R�[�h
        l_mainAccountParams.setEmailLastUpdater("2512246");
        // email�A�h���X�X�V����
        l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
        // ����p�X���[�h�X�V�҃R�[�h
        l_mainAccountParams.setTradingPasswordUpdater("2512246");
        // ����p�X���[�h�X�V����
        l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
        // �g�єԍ��E�Ζ�����X�V�҃R�[�h
        l_mainAccountParams.setMbOffLastUpdater("2512246");
        // �g�єԍ��E�Ζ�����X�V����
        l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
        // ��~�󋵍X�V�҃R�[�h
        l_mainAccountParams.setEnableOrderLastUpdater("2512246");
        // ��~�󋵍X�V����
        l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
        // �e�w�����J�݋敪�X�V�҃R�[�h
        l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
        // �e�w�����J�݋敪�X�V����
        l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
        // �O�������A�g�����J�݋敪�X�V�҃R�[�h
        l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
        // �O�������A�g�����J�݋敪�X�V����
        l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
        // �l�q�e�ݒ���
        l_mainAccountParams.setMrfFundCode("1");
        // �쐬����
        l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        // �X�V����
        l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        // ����Ǘ������J�݋敪
        l_mainAccountParams.setSpMngAccOpenDiv("0");

        return l_mainAccountParams;
    }
}
@
