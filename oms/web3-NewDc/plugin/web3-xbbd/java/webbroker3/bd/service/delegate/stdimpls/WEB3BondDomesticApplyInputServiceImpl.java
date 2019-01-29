head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������̓T�[�r�XImpl(WEB3BondDomesticApplyInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.225
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderManagerReusableValidationsCheck;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.bd.message.WEB3BondDomesticApplyInputRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyInputResponse;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3ProspectusCheckDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.WEB3BondTradingTimeManagement;

/**
 * (������������̓T�[�r�XImpl)<BR>
 * ������������̓T�[�r�XImpl<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondDomesticApplyInputServiceImpl
    extends WEB3BondClientRequestService
    implements WEB3BondDomesticApplyInputService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyInputServiceImpl.class);

    /**
     * @@roseuid 46A473FC0399
     */
    public WEB3BondDomesticApplyInputServiceImpl()
    {

    }

    /**
     * ������������̓T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uinput���������咍���v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 466CCFC70301
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        if (!(l_request instanceof WEB3BondDomesticApplyInputRequest))
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        WEB3BondDomesticApplyInputRequest l_inputRequest = (WEB3BondDomesticApplyInputRequest)l_request;

        //validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_inputRequest.validate();

        //validate������t�\( )
        //��t���ԃ`�F�b�N�A�ً}��~�`�F�b�N�A�o�b�`�������`�F�b�N���s�Ȃ��B
        WEB3BondTradingTimeManagement.validateOrderAccept();

        //get�⏕����( )
        //�⏕�����I�u�W�F�N�g���擾����B
        SubAccount l_subAccount = this.getSubAccount();

        //get������(long)
        //���������擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager =
         (WEB3BondProductManager)l_finApp.getTradingModule(
             ProductTypeEnum.BOND).getProductManager();
        long l_lngProductId = Long.parseLong(l_inputRequest.productId);
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_bondProductManager.getBondProduct(l_lngProductId);

        //validate����\�ڋq
        //�ڋq�ʎ����~�����`�F�b�N������
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        OrderValidationResult l_validationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����\�ڋq�`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "����\�ڋq�`�F�b�N�G���[");
        }

        //validate�@@�l�ڋq(�⏕����, ������)
        //�ڋq���@@�l���ǂ������`�F�b�N����B
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();
        l_bondOrderManager.validateCorporationAccount(l_subAccount, l_bondProduct);

        //validate�ڋq�戵�\����<������>(������, String)
        //�ڋq�戵�\�`�F�b�N�A����\�`�F�b�N���s�Ȃ��B
        l_bondOrderManager.validateAccountHandlingPossibleProductBondDomestic(
            l_bondProduct,
            WEB3BondDealDivDef.RECRUIT);

        //validate����������g(long, ������, double)
        //�������ʂ�����������g�͈͓̔��ł��邩�ǂ����`�F�b�N����B
        WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck =
            new WEB3BondOrderManagerReusableValidationsCheck();
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)(l_subAccount.getMainAccount().getDataSourceObject());
        l_validationsCheck.validateDomesticBondRecruitLimit(
            l_mainAccountRow.getBranchId(),
            l_bondProduct,
            0);

        //�����򏈗����ژ_�����{���`�F�b�N�����{����B
        //  ���N�G�X�g.�d�q���`�F�b�N�t���O   == True ����
        //������.�ژ_�����{���`�F�b�N�敪 == ��ژ_�������`�F�b�N���飏ꍇ
        WEB3GentradeProspectusResult l_validateBataResult = null;
        if(l_inputRequest.batoCheckFlag &&
            WEB3ProspectusCheckDivDef.PROSPECTUS_CHECK.equals(l_bondProduct.getProspectusCheckDiv()))
        {
            //1.91 validate�ژ_�����{��(��ʃR�[�h : String, �����R�[�h : String)
            //�ژ_�����{���`�F�b�N�����{����B
            WEB3GentradeBatoClientService l_bataService =
                (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);

            l_validateBataResult =
                l_bataService.validateProspectus(
                    l_inputRequest.typeCode,
                    l_bondProduct.getProductCode());
        }

        //�ŗ�(�،���ЃR�[�h : String, �Ŏ�� : String, ������ : Date)
        WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(
            l_bondProduct.getInstitution().getInstitutionCode(),
            WEB3DutyTypeDef.DOMESTIC_BOND_WITHHOLDING_TAX,
            new Timestamp(l_bondProduct.getBondDomesticBizDate().getTime()));

        //get���̑����i���t�\�z(�⏕���� : �⏕����, ��n�� : Date)
        //���t�\�z���擾����B
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        double l_dblOtherTradingPower =
            l_tpTradingPowerService.getOtherTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                l_bondProduct.getDeliveryDate());

        //createResponse( )
        WEB3BondDomesticApplyInputResponse l_response =
            (WEB3BondDomesticApplyInputResponse)l_inputRequest.createResponse();

        //�v���p�e�B�E�Z�b�g
        //�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
        //���t�\�z   = get���̑����i���t�\�z()�̖߂�l
        l_response.tradingPower = WEB3StringTypeUtility.formatNumber(l_dblOtherTradingPower);
        //����ID        = ������.����ID
        l_response.productId = String.valueOf(l_bondProduct.getProductId());
        //������     = ������.������
        l_response.productName = l_bondProduct.getProductName();
        //����J�n��   = ������.�戵�J�n����
        l_response.recruitStartDate = l_bondProduct.getTradeStartDate();
        //����I����   = ������.�戵�I������
        l_response.recruitEndDate = l_bondProduct.getTradeEndDate();
        //����      = ������.����
        l_response.coupon = WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());
        //����(�ېŌ�) = ������.���� �~ ( 1 - (�ŗ�.get�ŗ��~0.01))
        BigDecimal l_bdCoupon = new BigDecimal(String.valueOf(l_bondProduct.getCoupon()));
        BigDecimal l_dbTaxRate = new BigDecimal(String.valueOf(l_taxRate.getTaxRate()));
        l_response.couponAfterTax =
            WEB3StringTypeUtility.formatNumber(
                l_bdCoupon.multiply(
                    (new BigDecimal(String.valueOf("1")).subtract(
                        l_dbTaxRate.multiply(
                            new BigDecimal(String.valueOf("0.01")))))).doubleValue());
        //�N�ԗ�����  = ������.�N�ԗ�����
        l_response.yearlyInterestPayments =
            String.valueOf(l_bondProduct.getYearlyInterestPayments());
        //������1        = ������.������1
        l_response.couponPaymentDate1 = l_bondProduct.getInterestPaymentDay1();
        //������2        = ������.������2
        l_response.couponPaymentDate2 = l_bondProduct.getInterestPaymentDay2();
        //����P��        = ������.���t�P��
        l_response.applyPrice = WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyPrice());
        //�\���P��        = ������.�\���P��
        l_response.applyUnit = WEB3StringTypeUtility.formatNumber(l_bondProduct.getTradeUnit());
        //���s��     = ������.���s��
        l_response.issueDate = l_bondProduct.getIssueDate();
        //���ғ�     = ������.���ғ�
        l_response.maturityDate = l_bondProduct.getMaturityDate();
        //�ژ_�����{���`�F�b�N���� = ���N�G�X�g.�d�q���`�F�b�N�t���O   == True ����
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ �@@ ������.�ژ_�����{���`�F�b�N�敪 == ��ژ_�������`�F�b�N���飏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@validate�ژ_�����{��()�̌��ʃI�u�W�F�N�g���Z�b�g�B
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ �@@ ��L�ȊO�̏ꍇ�Anull���Z�b�g����B
        l_response.prospectusResult = l_validateBataResult;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
