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
filename	WEB3BondCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������T�[�r�XImpl(WEB3BondCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/22 ��іQ (���u) �V�K�쐬
                 : 2006/09/29 ��іQ (���u) ���f�� 094 �c�a�X�V�d�lNo.013
Revesion History : 2007/07/25 �Ӑ� (���u) �d�l�ύX�E���f��222
*/

package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

import webbroker3.bd.WEB3BondCancelUpdateInterceptor;
import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.message.WEB3BondCancelCompleteRequest;
import webbroker3.bd.message.WEB3BondCancelCompleteResponse;
import webbroker3.bd.message.WEB3BondCancelConfirmRequest;
import webbroker3.bd.message.WEB3BondCancelConfirmResponse;
import webbroker3.bd.service.delegate.WEB3BondCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderUnitIntroduceDiv;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (������T�[�r�XImpl)<BR>
 * ������T�[�r�XImpl
 *
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3BondCancelServiceImpl
    extends WEB3BondClientRequestService
    implements WEB3BondCancelService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondCancelServiceImpl.class);

    /**
     * @@roseuid 44FBFD3A0128
     */
    public WEB3BondCancelServiceImpl()
    {

    }

    /**
     * ������T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * validate���p�����Asubmit���p�t����<BR>
     * �̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44E93E610353
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        //validate���p����
        if (l_request instanceof WEB3BondCancelConfirmRequest)
        {
            l_response = this.validateCancelOrder(
                (WEB3BondCancelConfirmRequest) l_request);
        }
        //submit���p����
        else if (l_request instanceof WEB3BondCancelCompleteRequest)
        {
            l_response = this.submitCancelOrder(
                (WEB3BondCancelCompleteRequest) l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s��");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�������)<BR>
     * ��������������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uvalidate��������v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3BondSellConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44E9470401FC
     */
    protected WEB3BondCancelConfirmResponse validateCancelOrder(
        WEB3BondCancelConfirmRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCancelOrder(WEB3BondCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2get�������P��By����ID(long)]
        //[get�������P��By����ID()�ɓn������]
        //�@@����ID�F�@@���N�G�X�g�f�[�^.����ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_orderManager =
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        WEB3BondOrderUnit l_bondOrderUnit = (
            WEB3BondOrderUnit)l_orderManager.getBondOrderUnitByOrderId(
                Long.parseLong(l_request.orderId));

        //1.3get������(long)
        //[get������()�ɓn������]
        //����ID�F�@@�������P��.����ID
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = (
            WEB3BondProduct) l_bondProductManager.getBondProduct(
                l_bondOrderUnit.getProductId());

        //1.4validate������t�\(String)
        //��t���ԃ`�F�b�N�A�ً}��~�`�F�b�N�A�o�b�`�������`�F�b�N���s�Ȃ��B
        //[validate������t�\()�ɓn������]
        //�������F�@@�擾����������
        WEB3BondTradingTimeManagement.validateOrderAccept(
            l_bondProduct);

        //1.5get�⏕����( )
        SubAccount l_subAccount = this.getSubAccount();

        //1.6 validate����\�ڋq(SubAccount)
        //[validate����\�ڋq()�ɓn������]
        //�⏕�����F�@@get�⏕����()�̖߂�l
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

        //1.7get����敪( )
        String l_strDealDiv = l_bondOrderUnit.getDealDiv();

        //������.���^�C�v == "�O����"�̏ꍇ
        if (BondTypeEnum.FOREIGN_BOND.equals(l_bondProduct.getBondType()))
        {
            //validate�ڋq�戵�\����(������, String)
            //[validate�ڋq�戵�\����()�ɓn������]
            //�@@�������F�@@get������()�̖߂�l
            //�@@����敪�F�@@get����敪()�̖߂�l
            l_orderManager.validateAccountHandlingPossibleProduct(
                l_bondProduct,
                l_strDealDiv);
        }
        else
        {
            //������.���^�C�v �� "�O����"�̏ꍇ
            //validate�ڋq�戵�\����<������>(������, String)
            //[validate�ڋq�戵�\����<������>()�ɓn������]
            //�@@�������F�@@get������()�̖߂�l
            //�@@����敪�F�@@get����敪()�̖߂�l
            l_orderManager.validateAccountHandlingPossibleProductBondDomestic(
                l_bondProduct,
                l_strDealDiv);
        }

        //1.9validate��������\���(�g���������P��)
        //[validate��������\���()�ɓn������]
        //�@@�������P�ʁF�@@�擾�����������P��
        l_orderManager.validateOrderCancelPossibleStatus(l_bondOrderUnit);

        //1.10createResponse( )
        WEB3BondCancelConfirmResponse l_response =
            (WEB3BondCancelConfirmResponse)l_request.createResponse();

        //1.11�����P�ʏЉ�敪(long, ProductTypeEnum)
        //[�R���X�g���N�^�ɓn������]
        //�����P��ID�F�@@�������P��.�����P��ID
        //�����^�C�v�F�@@�������P��.�����^�C�v
        boolean l_blnIntroduceDiv = true;
        WEB3GentradeOrderUnitIntroduceDiv l_orderUnitIntroduceDiv = null;
        try
        {
            l_orderUnitIntroduceDiv =
                new WEB3GentradeOrderUnitIntroduceDiv(
                    l_bondOrderUnit.getOrderUnitId(),
                    l_bondOrderUnit.getProductType());
        }
        catch(WEB3BaseException l_ex)
        {
            l_blnIntroduceDiv = false;
        }

        BondOrderUnitRow l_bondOrderUnitRow =
            (BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();

        //1.12�v���p�e�B�E�Z�b�g
        //�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
        //������         = ������.������
        l_response.productName = l_bondProduct.getProductName();

        //�ʉ݃R�[�h           = ������.�ʉ݃R�[�h
        l_response.currencyCode = l_bondProduct.getCurrencyCode();

        //�����P��            = �������P��.�w�l
        if(!l_bondOrderUnitRow.getLimitPriceIsNull())
        {
            l_response.buySellPrice =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getLimitPrice());
        }

        //����          = ������.����
        l_response.coupon =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());

        //���s��         = ������.���s��
        l_response.issueDate = l_bondProduct.getIssueDate();

        //�N�ԗ�����      = ������.�N�ԗ�����
        l_response.yearlyInterestPayments =
            l_bondProduct.getYearlyInterestPayments() + "";

        //�������P            = ������.�������P
        l_response.interestPaymentDay1 = l_bondProduct.getInterestPaymentDay1();

        //�������Q            = ������.�������Q
        l_response.interestPaymentDay2 = l_bondProduct.getInterestPaymentDay2();

        //���ғ�         = ������.���ғ�
        l_response.maturityDate = l_bondProduct.getMaturityDate();

        //����敪            = get����敪�i�j�̖߂�l
        l_response.stateDiv = l_strDealDiv;

        //���ϋ敪            = �������P��.���ϋ敪
        l_response.settleDiv = l_bondOrderUnit.getSettlementDiv();

        //�בփ��[�g           = �������P��.��בփ��[�g
        if(!l_bondOrderUnitRow.getBaseFxRateIsNull())
        {
            l_response.fxRate =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getBaseFxRate());
        }

        //�z�ʋ��z            = �������P��.��������
        l_response.faceAmount= WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getQuantity());

        //��������i�O�݁j        = �������P��.��������i�O�݁j
        if(!l_bondOrderUnitRow.getForeignTradingPriceIsNull())
        {
            l_response.foreignTradePrice =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getForeignTradingPrice());
        }

        //��������i�~�݁j        = �������P��.��������i�~�݁j
        if(!l_bondOrderUnitRow.getTradingPriceIsNull())
        {
            l_response.yenTradePrice =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getTradingPrice());
        }

        //�o�ߗ��q�i�O�݁j        = �������P��.�o�ߗ��q�i�O�݁j
        if(!l_bondOrderUnitRow.getForeignAccruedInterestIsNull())
        {
            l_response.foreignAccruedInterest =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getForeignAccruedInterest());
        }

        //�o�ߗ��q�i�~�݁j        = �������P��.�o�ߗ��q�i�~�݁j
        if(!l_bondOrderUnitRow.getAccruedInterestIsNull())
        {
            l_response.yenAccruedInterest =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getAccruedInterest());
        }

        //��n����i�O�݁j        = �������P��.��n����i�O�݁j
        if(!l_bondOrderUnitRow.getForeignEstimatedPriceIsNull())
        {
            l_response.foreignDeliveryPrice =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getForeignEstimatedPrice());
        }

        //��n����i�~�݁j        = �������P��.��n����i�~�݁j
        if(!l_bondOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_response.yenDeliveryPrice =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getEstimatedPrice());
        }

        //��������            = �������P��.�󒍓���
        l_response.orderDate = l_bondOrderUnit.getReceivedDateTime();

        //������         = �������P��.������
        l_response.orderBizDate =
            WEB3DateUtility.getDate(l_bondOrderUnit.getBizDate(), "yyyyMMdd");

        //����         = �������P��.����
        l_response.executionUpdateDate = l_bondOrderUnit.getExecDate();

        //��n��         = �������P��.��n��
        l_response.deliveryDate = l_bondOrderUnit.getDeliveryDate();

        if (l_blnIntroduceDiv)
        {
            //�Љ�敪            = �����P�ʏЉ�敪.get�Љ�敪()(*1)
            l_response.introduceStoreDiv = l_orderUnitIntroduceDiv.getIntroduceBranchDiv();

            //�Љ�X�R�[�h      = �����P�ʏЉ�敪.get�Љ�X�R�[�h()(*1)
            l_response.introduceStoreCode = l_orderUnitIntroduceDiv.getIntroduceBranchCode();
        }
        else
        {
            //(*1)�����P�ʏЉ�敪���擾�ł��Ȃ������ꍇ�Anull���Z�b�g����B
            l_response.introduceStoreDiv = null;
            l_response.introduceStoreCode = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * (submit�������)<BR>
     * ����������o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usubmit��������v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3BondCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 44E947700315
     */
    protected WEB3BondCancelCompleteResponse submitCancelOrder(WEB3BondCancelCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitCancelOrder(WEB3BondCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2get�������P��By����ID(long)]
        //[get�������P��By����ID()�ɓn������]
        //�@@����ID�F�@@���N�G�X�g�f�[�^.����ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_orderManager =
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        WEB3BondOrderUnit l_bondOrderUnit = (
            WEB3BondOrderUnit)l_orderManager.getBondOrderUnitByOrderId(
                Long.parseLong(l_request.orderId));

        //1.3get������(long)
        //[get������()�ɓn������]
        //����ID�F�@@�������P��.����ID
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = (
            WEB3BondProduct) l_bondProductManager.getBondProduct(
                l_bondOrderUnit.getProductId());

        //1.4validate������t�\(String)
        //��t���ԃ`�F�b�N�A�ً}��~�`�F�b�N�A�o�b�`�������`�F�b�N���s�Ȃ��B
        //[validate������t�\()�ɓn������]
        //�������F�@@�擾����������
        WEB3BondTradingTimeManagement.validateOrderAccept(
            l_bondProduct);

        //1.5get�⏕����( )
        SubAccount l_subAccount = this.getSubAccount();

        //1.6 validate����\�ڋq(SubAccount)
        //[validate����\�ڋq()�ɓn������]
        //�⏕�����F�@@get�⏕����()�̖߂�l
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

        //1.7get����敪( )
        String l_strDealDiv = l_bondOrderUnit.getDealDiv();

        //������.���^�C�v == "�O����"�̏ꍇ
        if (BondTypeEnum.FOREIGN_BOND.equals(l_bondProduct.getBondType()))
        {
            //validate�ڋq�戵�\����(������, String)
            //[validate�ڋq�戵�\����()�ɓn������]
            //�@@�������F�@@get������()�̖߂�l
            //�@@����敪�F�@@get����敪()�̖߂�l
            l_orderManager.validateAccountHandlingPossibleProduct(
                l_bondProduct,
                l_strDealDiv);
        }
        else
        {
            //������.���^�C�v �� "�O����"�̏ꍇ
            //validate�ڋq�戵�\����<������>(������, String)
            //[validate�ڋq�戵�\����<������>()�ɓn������]
            //�@@�������F�@@get������()�̖߂�l
            //�@@����敪�F�@@get����敪()�̖߂�l
            l_orderManager.validateAccountHandlingPossibleProductBondDomestic(
                l_bondProduct,
                l_strDealDiv);
        }

        //1.9validate��������\���(�g���������P��)
        //[validate��������\���()�ɓn������]
        //�@@�������P�ʁF�@@�擾�����������P��
        l_orderManager.validateOrderCancelPossibleStatus(l_bondOrderUnit);

        //1.10CancelOrderSpec(arg0 : long)
        //����ID�F �����P��.����ID
        CancelOrderSpec l_cancelOrderSpec =
            new CancelOrderSpec(Long.parseLong(l_request.orderId));

        //1.11get�㗝���͎�( )
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();

        //1.12������X�V�C���^�Z�v�^( )
        WEB3BondCancelUpdateInterceptor l_bondCancelUpdateInterceptor =
            new WEB3BondCancelUpdateInterceptor();

        //1.13�v���p�e�B�E�Z�b�g
        //�㗝���͎ҁF�@@get�㗝���͎ҁi�j�̖߂�l
        //���擾�ł��Ȃ������ꍇnull���Z�b�g����j
        l_bondCancelUpdateInterceptor.setTrader(l_trader);

        //1.14setThreadLocalPersistenceEventInterceptor(
        //arg0 : BondOrderManagerPersistenceEventInterceptor)
        //arg0�F ������X�V�C���^�Z�v�^
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_bondCancelUpdateInterceptor);


        //1.15submitCancelOrder(arg0 : SubAccount, arg1 : CancelOrderSpec, arg2 : String, arg3 : boolean)
        //�⏕�����F get�⏕����()�̖߂�l
        //�������e�F CancelOrderSpec
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        //isSkip�����R���F true
        OrderSubmissionResult l_submitNewOrderResult = 
            l_orderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                true);
        
        if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitNewOrder" +
                l_submitNewOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.16�]�͍Čv�Z(�⏕���� : �⏕����)
        //�⏕�����F get�⏕����()�̖߂�l
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        l_tpTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);

        //1.17createResponse( )
        WEB3BondCancelCompleteResponse l_response =
            (WEB3BondCancelCompleteResponse)l_request.createResponse();

        //1.18�v���p�e�B�E�Z�b�g
        //�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
        //�X�V�����@@���@@���ݓ���
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }


}
@
