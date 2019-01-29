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
filename	WEB3BondRecruitBuyInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t���̓T�[�r�XImpl(WEB3BondRecruitBuyInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/06 ������ (���u) �V�K�쐬
                 : 2006/10/09 �����F (���u) ���f��No.094,098
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.bd.message.WEB3BondApplyBuyInputRequest;
import webbroker3.bd.message.WEB3BondApplyBuyInputResponse;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AutoExecDivDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (������/���t���̓T�[�r�XImpl)<BR>
 * ������/���t���̓T�[�r�XImpl<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondRecruitBuyInputServiceImpl extends WEB3BondClientRequestService implements WEB3BondRecruitBuyInputService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondRecruitBuyInputServiceImpl.class);

    /**
     * @@roseuid 44FBFD3A006D
     */
    public WEB3BondRecruitBuyInputServiceImpl()
    {

    }

    /**
     * ������/���t���̓T�[�r�X���������{����B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C453040084
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (! (l_request instanceof WEB3BondApplyBuyInputRequest))
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        WEB3BondApplyBuyInputRequest l_inputRequest = (WEB3BondApplyBuyInputRequest)l_request;

        //1.1 validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_inputRequest.validate();

        //1.2 get������(long)
        //���������擾����B
        //[get�������i�j�ɓn������]
        //�@@����ID�F�@@���N�G�X�g�f�[�^.����ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager) l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_productManager.getBondProduct(Long.parseLong(l_inputRequest.productId));

        //1.3 validate������t�\(String)
        //��t���ԃ`�F�b�N�A�ً}��~�`�F�b�N�A�o�b�`�������`�F�b�N���s�Ȃ��B
        //[validate������t�\()�ɓn������]
        //�������F�@@�擾����������
        WEB3BondTradingTimeManagement.validateOrderAccept(l_bondProduct);

        //1.4 get�⏕����( )
        //�⏕�����I�u�W�F�N�g���擾����B
        SubAccount l_subAccount = this.getSubAccount();

        //1.5 validate����\�ڋq(�⏕���� : SubAccount)
        //�ڋq�ʎ����~�����`�F�b�N������
        //�mvalidate����\�ڋq()�ɓn������]
        //�@@�@@�@@�⏕�����F�@@get�⏕����( )�̖߂�l
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();

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

        //1.6 validate�O���،������J��(SubAccount, ������)
        //�ڋq���O���،��������J�݂��Ă��邩�`�F�b�N����B
        //[validate�O���،������J��(�j�ɓn������]
        //�@@�⏕�����F�@@get�⏕����()�̖߂�l
        //�@@�������F�@@get������()�̖߂�l
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        l_bondOrderManager.validateFeqAccountOpen(l_subAccount, l_bondProduct);

        //1.7 validate�ڋq�戵�\����(������, String)
        //�ڋq�戵�\�`�F�b�N�A����\�`�F�b�N���s�Ȃ��B
        //[validate�ڋq�戵�\����()�ɓn������]
        //�@@�������F�@@get������()�̖߂�l
        //�@@����敪�F�@@���N�G�X�g�f�[�^.����敪
        l_bondOrderManager.validateAccountHandlingPossibleProduct(
            l_bondProduct,
            l_inputRequest.tradeDiv);

        //1.8 get�������敪( )
        //�������̎������敪���擾����B
        String l_strAutoExecDiv = l_bondProduct.getAutoExecDiv();

        //1.9 �����򏈗���get�������敪()�̖߂�l == �h�������h�̏ꍇ
        if (WEB3AutoExecDivDef.AUTO_EXECUTE.equals(l_strAutoExecDiv))
        {
            //1.9.1 validate�������g(�،����, ������, String)
            //�������ʂ��������g�͈͓̔��ł��邩�`�F�b�N����B
            //[validate�������g()�ɓn������]
            //�@@�،���ЁF�@@�⏕����.getInstitution()�̖߂�l
            //�@@�������F�@@�擾����������
            //�@@�������ʁF�@@0
            l_bondOrderManager.validateAutoExecLimit(
                l_subAccount.getInstitution(),
                l_bondProduct,
                "0");
        }

        //1.10  is�O�݌�( )
        //���������O�݌��Ă��ǂ����`�F�b�N����B
        boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();

        //1.11 �����򏈗���is�O�݌�()�̖߂�l == true �̏ꍇ
        double l_dblExchangeRate = 0.0D;
        if (l_blnIsForeignCurrency)
        {
            //1.11.1 get�ʉ݃R�[�h( )
            //�������̒ʉ݃R�[�h���擾����B
            String l_strCurrencyCode = l_bondProduct.getCurrencyCode();

            //1.11.2 �i���ʁj�ʉ�(�،���ЃR�[�h : String, �ʉ݃R�[�h : String)
            //�i���ʁj�ʉ݂̃C���X�^���X�𐶐�����B
            //[�R���X�g���N�^�ɓn������]
            //�@@�،���ЃR�[�h�F�@@�擾�����⏕����.getInstitution().getInstitutionCode()�̖߂�l
            //�@@�ʉ݃R�[�h�F�@@������.get�ʉ݃R�[�h()�̖߂�l
            WEB3GentradeCurrency l_currency =
                WEB3GentradeCurrency.genCurrency(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_strCurrencyCode);

            //1.11.3 get�בփ��[�g(is���t : boolean, is���v�Z : boolean, ���͈בփ��[�g : double)
            //�בփ��[�g���擾����B
            //[get�בփ��[�g()�ɓn������]
            //�@@is���t�F�@@true
            //�@@is���v�Z�F�@@false
            //�@@���͈בփ��[�g�F�@@0
            l_dblExchangeRate = l_currency.getExchangeRate(true, false, 0);
        }

        //1.12 get������( )
        //���������擾����B
        Date l_datOrderBizDate = WEB3BondTradingTimeManagement.getOrderBizDate();

        //1.13 ��������ʔ���(�������, String)
        //��������ʔ�����쐬����B
        //[�R���X�g���N�^�ɓn������]
        //�@@������ʁF�@@OrderTypeEnum.��������
        //�@@����F�@@���N�G�X�g�f�[�^.����敪 == "���t"�̏ꍇ�A92�F�����d�؎���B
        //�@@�@@�@@�@@�@@�@@���N�G�X�g�f�[�^.����敪 == "����"�̏ꍇ�A35�F��W����B
        String l_strDealType = null;
        if (WEB3BondDealDivDef.BUY.equals(l_inputRequest.tradeDiv))
        {
            l_strDealType = WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING;
        }
        else if (WEB3BondDealDivDef.RECRUIT.equals(l_inputRequest.tradeDiv))
        {
            l_strDealType =WEB3DealTypeDef.RECRUIT_TRADING;
        }
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
            new WEB3BondOrderTypeJudge(
                OrderTypeEnum.BOND_BUY,
                l_strDealType);

        //create���������(java.util.Date, ��������ʔ���, ������, String, Branch)
        WEB3BondExecuteDateInfo l_execDateInfo = l_bondOrderManager.createBondExecutionDateInfo(
            l_datOrderBizDate,
            l_bondOrderTypeJudge,
            l_bondProduct,
            WEB3SettlementDivDef.JAPANESE_CURRENCY,
            l_subAccount.getMainAccount().getBranch());

        //1.15 get���̑����i���t�\�z(�⏕���� : �⏕����, ��n�� : Date)
        //���t�\�z���擾����B
        //[����]
        //�@@�⏕�����F�@@get�⏕�����i�j�̖߂�l
        //�@@��n���F�@@���������.get�������i�j�̖߂�l
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        double l_dblOtherTradingPower =
            l_tpTradingPowerService.getOtherTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                l_execDateInfo.getPaymentDate());

        //1.16 createResponse( )
        WEB3BondApplyBuyInputResponse l_response =
            new WEB3BondApplyBuyInputResponse();

        //1.17 �v���p�e�B�E�Z�b�g
        //���t�\�z = get���̑����i���t�\�z()�̖߂�l
        l_response.tradingPower =
            WEB3StringTypeUtility.formatNumber(l_dblOtherTradingPower);

        //����ID      = ������.����ID
        l_response.productId =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getProductId());

        //������       = ������.������
        l_response.productName = l_bondProduct.getProductName();

        //��ʃR�[�h     = ������.��ʃR�[�h
        l_response.bondCategCode = l_bondProduct.getBondCategCode();

        //S&P       = ������.S&P
        l_response.sAndP = l_bondProduct.getSAndP();

        //Moody's       = ������.MOODY'S
        l_response.moodys = l_bondProduct.getMoodys();

        //����        = ������.����
        l_response.coupon =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());

        //�N�ԗ�����    = ������.�N�ԗ�����
        l_response.yearlyInterestPayments =
            WEB3StringTypeUtility.formatNumber(l_bondProduct .getYearlyInterestPayments());

        //������1      = ������.������1
        l_response.interestPaymentDay1 = l_bondProduct.getInterestPaymentDay1();

        //������2      = ������.������2
        l_response.interestPaymentDay2 = l_bondProduct.getInterestPaymentDay2();

        //�ʉ݃R�[�h     = ������.�ʉ݃R�[�h
        l_response.currencyCode = l_bondProduct.getCurrencyCode();

        //�\���P��      = ������.�\���P��
        l_response.tradeUnit =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getTradeUnit());

        //�Œ�\������    = ������.�Œ�z��
        l_response.minOrderQuantity =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getMinFaceAmount());

        //�ō��\������    = ������.�ō��z��
        BondProductRow l_bondProductRow = (BondProductRow)l_bondProduct.getDataSourceObject();
        if (l_bondProductRow.getMaxFaceAmountIsNull())
        {
            l_response.maxOrderQuantity = null;
        }
        else
        {
            l_response.maxOrderQuantity =
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getMaxFaceAmount());
        }

        //(*1)���N�G�X�g�f�[�^.����敪 == �h����h�̏ꍇ�̂݃Z�b�g����B����ȊO�̏ꍇ�Anull�B
        if (WEB3BondDealDivDef.RECRUIT.equals(l_inputRequest.tradeDiv))
        {
            //����J�n�� = ������.����J�n��(*1)
            l_response.recruitStartDate = l_bondProduct.getRecruitStartDate();

            //����I���� = ������.����I����(*1)
            l_response.recruitEndDate = l_bondProduct.getRecruitEndDate();
        }
        else
        {
            //����J�n��
            l_response.recruitStartDate = null;

            //����I����
            l_response.recruitEndDate = null;
        }

        //���t�P��      = ������.���t�P��
        if (l_bondProductRow.getBuyPriceIsNull())
        {
            l_response.buyPrice = null;
        }
        else
        {
            l_response.buyPrice =
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyPrice());            
        }

        //���s��       = ������.���s��
        l_response.issueDate = l_bondProduct.getIssueDate();

        //���ғ�       = ������.���ғ�
        l_response.maturityDate = l_bondProduct.getMaturityDate();

        //(*2)is�O�݌�()�̖߂�l == false �̏ꍇ�Anull���Z�b�g����B
        //���t��בփ��[�g = get�בփ��[�g()�̖߂�l(*2)
        if (l_blnIsForeignCurrency)
        {
            l_response.buyBaseFxRate =
                WEB3StringTypeUtility.formatNumber(l_dblExchangeRate);
        }
        else
        {
            l_response.buyBaseFxRate = null;
        }

        //���ϋ敪�ꗗ    = is�O�݌�()�̖߂�l == false�̏ꍇ�A�h�~�݁h���Z�b�g����B
        //         is�O�݌�()�̖߂�l == true�̏ꍇ�A�h�~�݁h�A�h�O�݁h���Z�b�g����B
        if (l_blnIsForeignCurrency)
        {
            l_response.settleDivList =
                new String[] {WEB3SettlementDivDef.JAPANESE_CURRENCY, WEB3SettlementDivDef.FOREIGN_CURRENCY};
        }
        else
        {
            l_response.settleDivList =
                new String[] {WEB3SettlementDivDef.JAPANESE_CURRENCY};
        }

        //�d�����̈בփ��[�g = ������.�d�����̈בփ��[�g
        if (!l_bondProductRow.getBuyingFxRateIsNull())
        {
            l_response.fxRateAtStock = WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyingFxRate());
        }

        //1.18
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
