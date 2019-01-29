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
filename	WEB3BondSellInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����p���̓T�[�r�XImpl(WEB3BondSellInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/07 ������ (���u) �V�K�쐬
                 : 2006/09/29 ��іQ (���u) ���f�� 094
                   2006/10/12 �ęԍg (���u)  WEB�V�J���W���̌������̑Ή��inew BigDecimal�����j
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondEstimatedAssetCalcResult;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondPositionManager;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.bd.message.WEB3BondSellInputRequest;
import webbroker3.bd.message.WEB3BondSellInputResponse;
import webbroker3.bd.service.delegate.WEB3BondSellInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����p���̓T�[�r�XImpl)<BR>
 * �����p���̓T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondSellInputServiceImpl extends WEB3BondClientRequestService implements WEB3BondSellInputService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondSellInputServiceImpl.class);

    /**
     * @@roseuid 44FBFD3A0177
     */
    public WEB3BondSellInputServiceImpl()
    {

    }

    /**
     * �����p���̓T�[�r�X���������{����B <BR>
     * <BR>
     * �V�[�P���X�}�u�����p���́v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C0280C0299
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (! (l_request instanceof WEB3BondSellInputRequest))
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        WEB3BondSellInputRequest l_inputRequest = (WEB3BondSellInputRequest)l_request;

        //1.1 validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_inputRequest.validate();

        //1.2 getAsset(ID : long)
        //�ۗL���Y���擾����B
        //[����]
        //ID�F ���N�G�X�g�f�[�^.ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondPositionManager l_bondPositionManager =
            (WEB3BondPositionManager)l_tradingModule.getPositionManager();
        Asset l_asset = null;
        try
        {
            l_asset = l_bondPositionManager.getAsset(Long.parseLong(l_inputRequest.id));
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.3 get������(long)
        //���������擾����B
        //[����]
        //����ID�F �擾�����ۗL���Y.����ID
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_bondProductManager.getBondProduct(
                l_asset.getProduct().getProductId());

        //1.4 validate������t�\(String)
        //��t���ԃ`�F�b�N�A�ً}��~�`�F�b�N�A�o�b�`�������`�F�b�N���s�Ȃ��B
        //[����]
        //�������F�@@�擾����������
        WEB3BondTradingTimeManagement.validateOrderAccept(l_bondProduct);

        //1.5 get�⏕����( )
        //�⏕�������擾����B
        SubAccount l_subAccount = this.getSubAccount();

        //1.6 validate����\�ڋq(�⏕���� : SubAccount)
        //�ڋq�ʎ����~�����`�F�b�N���s�Ȃ��B
        //[����]
        //�⏕�����F �擾�����⏕����
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����\�ڋq�`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "����\�ڋq�`�F�b�N�G���[");
        }

        //1.7 validate�O���،������J��(SubAccount, ������)
        //�O���،������J�݃`�F�b�N���s���B
        //[����]
        //�⏕�����F �擾�����⏕����
        //�������F �擾����������
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        l_bondOrderManager.validateFeqAccountOpen(l_subAccount, l_bondProduct);

        //1.8 validate�ڋq�戵�\����(������, String)
        //�戵�\�����`�F�b�N���s���B
        //[����]
        //�������F �擾����������
        //����敪�F "���p"
        l_bondOrderManager.validateAccountHandlingPossibleProduct(
            l_bondProduct,
            WEB3BondDealDivDef.SELL);

        //1.9  calc�T�Z�]���z(�⏕����, double, ������, boolean, boolean)
        //���T�Z�]���z���Z�o����B
        //[����]
        //�⏕�����F �擾�����⏕����
        //���ʁF �ۗL���Y.���� �| �ۗL���Y.get���b�N������
        //�������F �擾����������
        //is���t�F false
        //is���v�Z�F false
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_tradingModule.getBizLogicProvider();
        WEB3BondEstimatedAssetCalcResult l_calcEstimatedAsset =
            l_bizLogicProvider.calcEstimatedAsset(
                l_subAccount,
                l_asset.getQuantity() - l_asset.getLockedQuantity(),
                l_bondProduct,
                false,
                false);

        //1.10 is�O�݌�( )
        //�O�݌��������ǂ����𔻒肷��B
        boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();

        //1.11 �����򏈗���is�O�݌�()�̖߂�l == true �̏ꍇ
        double l_dblExchangeRate = 0.0D;
        if (l_blnIsForeignCurrency)
        {
            //1.11.1 get�ʉ�( )
            //�i���ʁj�ʉ݂��擾����B
            WEB3GentradeCurrency l_currency = l_bondProduct.getCurrency();

            //1.11.2 get�בփ��[�g(is���t : boolean, is���v�Z : boolean, ���͈בփ��[�g : double)
            //�בփ��[�g���擾����B
            //[����]
            //is���t�F false
            //is���v�Z�F false
            //���͈בփ��[�g�F 0
            l_dblExchangeRate = l_currency.getExchangeRate(false, false, 0);
        }

        //1.12 createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3BondSellInputResponse l_response =
            (WEB3BondSellInputResponse)l_inputRequest.createResponse();

        //1.13 �v���p�e�B�Z�b�g
        //���p�\�z�ʋ��z�@@�@@���@@�ۗL���Y.���� �|�ۗL���Y.get���b�N������
        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_asset.getQuantity()));
        BigDecimal l_bdLockedQuantity = new BigDecimal(String.valueOf(l_asset.getLockedQuantity()));
        BigDecimal l_bdSellAbleFaceAmount = l_bdQuantity.subtract(l_bdLockedQuantity);
        l_response.sellAbleFaceAmount =
            WEB3StringTypeUtility.formatNumber(l_bdSellAbleFaceAmount.doubleValue());

        //�T�Z�]���z�i�~�݁j�@@���@@���T�Z�]���z.get�T�Z�]���z�i�~�݁j()�̖߂�l
        l_response.yenEstimatedAsset =
            WEB3StringTypeUtility.formatNumber(
                l_calcEstimatedAsset.getEstimatedAsset().doubleValue());

        //�T�Z�]���z�i�O�݁j�@@���@@���T�Z�]���z.get�T�Z�]���z�i�O�݁j()�̖߂�l
        if (l_calcEstimatedAsset.getForeignEstimatedAsset() == null)
        {
            l_response.foreignEstimatedAsset = null;
        }
        else
        {
            l_response.foreignEstimatedAsset =
                WEB3StringTypeUtility.formatNumber(
                    l_calcEstimatedAsset.getForeignEstimatedAsset().doubleValue());
        }

        //�������@@�@@�@@�@@�@@�@@�@@���@@������.������
        l_response.productName = l_bondProduct.getProductName();

        //(*1)������.is�]���Ѝ�()�̖߂�l == true�@@�̏ꍇ�̂݃Z�b�g����B����ȊO�̏ꍇ�Anull�B
        //����(*1)�@@�@@�@@�@@�@@�@@���@@�ۗL���Y.�ŋ敪 == "���"�̏ꍇ�A"���"���Z�b�g�B
        //      �@@�@@"����" or "������������򒥎�"�̏ꍇ�A"����"���Z�b�g�B
        if (l_bondProduct.isExperienceDivWt())
        {
            if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
            {
                l_response.taxType = WEB3TaxTypeSpecialDef.NORMAL;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType())
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_asset.getTaxType()))
            {
                l_response.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
        }
        else
        {
            l_response.taxType = null;
        }

        //�ʉ݃R�[�h�@@�@@�@@�@@�@@���@@������.�ʉ݃R�[�h
        l_response.currencyCode = l_bondProduct.getCurrencyCode();

        //���p�i�]���j�P���@@�@@���@@������.���p�P��
        l_response.sellEvaluationPrice =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getSellPrice());

        //���s���@@�@@�@@�@@�@@�@@�@@���@@������.���s��
        l_response.issueDate = l_bondProduct.getIssueDate();

        //���ғ��@@�@@�@@�@@�@@�@@�@@���@@������.���ғ�
        l_response.maturityDate = l_bondProduct.getMaturityDate();

        //�����񐔁@@�@@�@@�@@�@@�@@���@@������.�N�ԗ�����
        l_response.yearlyInterestPayments = l_bondProduct.getYearlyInterestPayments() + "";

        //�������P�@@�@@�@@�@@�@@�@@���@@������.�������P
        l_response.interestPaymentDay1 = l_bondProduct.getInterestPaymentDay1();

        //�������Q�@@�@@�@@�@@�@@�@@���@@������.�������Q
        l_response.interestPaymentDay2 = l_bondProduct.getInterestPaymentDay2();

        //�����@@�@@�@@�@@�@@�@@�@@�@@���@@������.����
        l_response.coupon =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());

        //�\���P�ʁ@@�@@�@@�@@�@@�@@���@@������.�\���P��
        l_response.tradeUnit =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getTradeUnit());

        //���p��ב�(*2)�@@�@@���@@get�בփ��[�g()�̖߂�l
        //(*2)������.is�O�݌�()�̖߂�l == true�@@�̏ꍇ�̂݃Z�b�g����B����ȊO�̏ꍇ�Anull�B
        if (l_blnIsForeignCurrency)
        {
            l_response.sellBaseFx = WEB3StringTypeUtility.formatNumber(l_dblExchangeRate);
        }
        else
        {
            l_response.sellBaseFx = null;
        }

        //���ϋ敪�ꗗ�@@�@@�@@�@@���@@������.is�O�݌�()�̖߂�l == false�@@�̏ꍇ�A��~�ݣ���Z�b�g
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@������.is�O�݌�()�̖߂�l == true�@@�̏ꍇ�A��~�ݣ�A��O�ݣ���Z�b�g
        if (l_blnIsForeignCurrency)
        {
            l_response.settleDivList =
                new String[]{WEB3SettlementDivDef.JAPANESE_CURRENCY, WEB3SettlementDivDef.FOREIGN_CURRENCY};
        }
        else
        {
            l_response.settleDivList =
                new String[] {WEB3SettlementDivDef.JAPANESE_CURRENCY};
        }

        //1.14 ���X�|���X�f�[�^

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
