head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesChangeClosingContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�敨�����ԍσT�[�r�XImpl�iWEB3ToSuccFuturesChangeClosingContractServiceImpl.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 �g�E�N�| (���u) �V�K�쐬 ���f��No.270�A291�A296
Revision History : 2008/04/10 �g�E�N�| (���u) �d�l�ύX ���f��No.308
Revision History : 2008/04/22 �И���(���u) �d�l�ύX ���f��No.336
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.message.WEB3FuturesCommonRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderRequestAdapter;
import webbroker3.triggerorder.WEB3ToSuccIfoChangeSettleContractOrderSpec;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeClosingContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractOrderRequestAdapter;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�敨�����ԍσT�[�r�XImpl)<BR>
 * �i�A���j�敨�����ԍσT�[�r�X�����N���X<BR>
 *
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3ToSuccFuturesChangeClosingContractServiceImpl
    extends WEB3ToSuccFuturesSettleContractOrderServiceImpl
    implements WEB3ToSuccFuturesChangeClosingContractService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesChangeClosingContractServiceImpl.class);

    /**
     * @@roseuid 47D6593401A6
     */
    public WEB3ToSuccFuturesChangeClosingContractServiceImpl()
    {

    }

    /**
     * �i�A���j�敨�����ԍσT�[�r�X���������{����B<BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B <BR>
     * <BR>
     * �@@�@@[�i�A���j�����w���敨�����ԍϊm�F���N�G�X�g�̏ꍇ] <BR>
     * �@@�@@�@@this.validate����()���R�[������B <BR>
     * �@@�@@[�i�A���j�����w���敨�����ԍϊ������N�G�X�g�̏ꍇ] <BR>
     * �@@�@@�@@this.submit����()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A951BB0134
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
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

        // [�i�A���j�����w���敨�����ԍϊm�F���N�G�X�g�̏ꍇ]
        if (l_request instanceof WEB3SuccFuturesCloseChangeConfirmRequest)
        {
            // this.validate����()���R�[������B
            l_response = this.validateOrder((WEB3SuccFuturesCloseChangeConfirmRequest)l_request);
        }
        // [�i�A���j�����w���敨�����ԍϊ������N�G�X�g�̏ꍇ]
        else if (l_request instanceof WEB3SuccFuturesCloseChangeCompleteRequest)
        {
            // this.submit����()���R�[������B
            l_response = this.submitOrder((WEB3SuccFuturesCloseChangeCompleteRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate����)<BR>
     * �i�A���j�敨�����ԍς̔����R�����s���B<BR>
     * <BR>
     * �u�i�i�A���j�敨�����ԍσT�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccFuturesCloseChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A951BB0143
     */
    protected WEB3SuccFuturesCloseChangeConfirmResponse validateOrder(
        WEB3SuccFuturesCloseChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SuccFuturesCloseChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // get�敨OP�\�񒍕��P��(long)
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));

        // validate���N�G�X�g�f�[�^at���Ύ��(WEB3GenRequest, �敨OP�\�񒍕��P��Impl)
        this.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);

        // validate�����\���( )
        l_ifoOrderUnit.validateOrderForChangeability();

        // validate���ύό���(�敨OP�\�񒍕��P��Impl)
        this.validateSettledContract(l_ifoOrderUnit);

        // create�m�F���N�G�X�g(�i�A���j�����w���敨�����ԍϊm�F���N�G�X�g, �敨OP�\�񒍕��P��Impl)
        WEB3SuccFuturesCloseConfirmRequest l_confirmRequest =
            this.createConfirmRequest(l_request, l_ifoOrderUnit);

        // validate����(�i�A���j�����w���敨�ԍϒ����m�F���N�G�X�g)
        WEB3SuccFuturesCloseConfirmResponse l_confirmResponse = super.validateOrder(l_confirmRequest);

        // validate�[��܂Œ��������\(String, �敨OP�\�񒍕��P��Impl)
        l_orderManager.validateEveningSessionOrderPossibleChange(l_request.expirationDateType, l_ifoOrderUnit);

        // createResponse( )
        WEB3SuccFuturesCloseChangeConfirmResponse l_response =
            (WEB3SuccFuturesCloseChangeConfirmResponse)l_request.createResponse();

        // ���ʖ��ׁF�@@super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.contractUnits = l_confirmResponse.contractUnits;

        // �T�Z���ϑ��v�F�@@super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.estimatedSettleIncome = l_confirmResponse.estimatedSettleIncome;

        // �萔���R�[�X�F�@@super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.commissionCourse = l_confirmResponse.commissionCourse;

        // �萔���F�@@super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.commission = l_confirmResponse.commission;

        // �萔������ŁF�@@super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.commissionConsumptionTax = l_confirmResponse.commissionConsumptionTax;

        // ����I���x�������F�@@super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.messageSuspension = l_confirmResponse.messageSuspension;

        // �m�F���P���F�@@super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.checkPrice = l_confirmResponse.checkPrice;

        // �m�F���������F�@@super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.checkDate = l_confirmResponse.checkDate;

        // �����L�������F�@@super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.expirationDate = l_confirmResponse.expirationDate;

        // ������P�� �F
        // ���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ
        if (l_request.priceAdjustmentValueInfo != null)
        {
            // super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
            l_response.afterAdjustmentPrice = l_confirmResponse.afterAdjustmentPrice;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����)<BR>
     * �i�A���j�敨�����ԍς̓o�^���s���B<BR>
     * <BR>
     * �u�i�i�A���j�敨�����ԍσT�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccFuturesCloseChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A951BB0153
     */
    protected WEB3SuccFuturesCloseChangeCompleteResponse submitOrder(
        WEB3SuccFuturesCloseChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SuccFuturesCloseChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        // ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // get�敨OP�\�񒍕��P��(long)
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));

        // validate���N�G�X�g�f�[�^at���Ύ��(WEB3GenRequest, �敨OP�\�񒍕��P��Impl)
        this.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);

        // validate�����\���( )
        l_ifoOrderUnit.validateOrderForChangeability();

        // validate���ύό���(�敨OP�\�񒍕��P��Impl)
        this.validateSettledContract(l_ifoOrderUnit);

        // create�������N�G�X�g(�i�A���j�����w���敨�����ԍϊ������N�G�X�g, �敨OP�\�񒍕��P��Impl)
        WEB3SuccFuturesCloseCompleteRequest l_completeRequest =
            this.createCompleteRequest(l_request, l_ifoOrderUnit);

        // submit����(�i�A���j�����w���敨�ԍϒ����������N�G�X�g)
        WEB3SuccFuturesCloseCompleteResponse l_completeResponse = super.submitOrder(l_completeRequest);

        // validate�[��܂Œ��������\(String, �敨OP�\�񒍕��P��Impl)
        l_orderManager.validateEveningSessionOrderPossibleChange(l_request.expirationDateType, l_ifoOrderUnit);

        // create���N�G�X�g�A�_�v�^
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter =
            this.createRequestAdapter(l_completeRequest);

        // create�ԍό��ʃG���g��(�敨�ԍϒ������N�G�X�g�A�_�v�^, �ԍό���[])
        SettleContractEntry[] l_settleContractEntries =
            this.createSettleContractEntry(l_requestAdapter, l_request.closeMarginContractUnits);

        // ������w�l
        double l_dblModifiedLimitPrice = 0.0D;
        // ���N�G�X�g�f�[�^.�����P���敪 != "���s"�̏ꍇ
        if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_dblModifiedLimitPrice = Double.parseDouble(l_request.limitPrice);
        }

        // �����㒍��������
        Date l_datModifiedExpirationDate = null;
        // ���N�G�X�g�f�[�^.���������敪 == "�o����܂Œ���"�̏ꍇ
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
        {
            l_datModifiedExpirationDate = l_request.expirationDate;
        }
        else
        {
            l_datModifiedExpirationDate = l_request.checkDate;
        }

        // �㗝���͎�
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();

        // ������P�������l
        Double l_modifiedPriceAdjustValue = null;
        // ���N�G�X�g�f�[�^.�P�������l��� != null�̏ꍇ
        if (l_request.priceAdjustmentValueInfo != null)
        {
            l_modifiedPriceAdjustValue =
                new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }

        // create�敨OP�\��ԍϒ����������e(long, double, SettleContractEntry[],
        //  double, double, Date, ����, Double, String)
        WEB3ToSuccIfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec =
            WEB3ToSuccIfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                l_ifoOrderUnit.getOrderId(),
                l_dblModifiedLimitPrice,
                l_settleContractEntries,
                Double.parseDouble(l_request.estimatedSettleIncome),
                Double.parseDouble(l_request.checkPrice),
                l_datModifiedExpirationDate,
                l_trader,
                l_modifiedPriceAdjustValue,
                l_request.expirationDateType);

        // submit�敨OP�����\��ԍϒ���(SubAccount, �敨OP�\��ԍϒ����������e, String, �敨OP�\�񒍕��P��Impl)
        l_orderManager.submitIfoChangeSettleContractOrder(
            this.getSubAccount(),
            l_changeSettleContractOrderSpec,
            l_request.password,
            l_ifoOrderUnit);

        // createResponse( )
        WEB3SuccFuturesCloseChangeCompleteResponse l_response =
            (WEB3SuccFuturesCloseChangeCompleteResponse)l_request.createResponse();

        // �X�V���ԁF�@@�@@super.submit����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.lastUpdatedTimestamp = l_completeResponse.lastUpdatedTimestamp;

        // ���ʔԍ��F�@@�@@super.submit����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.orderActionId = l_completeResponse.orderActionId;

        // �A�������ݒ�t���O�F�@@false�i�Œ�j
        l_response.succSettingFlag = false;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate���N�G�X�g�f�[�^at���Ύ��)<BR>
     * ���Ύ���w�莞�ɌŗL�́A���N�G�X�g�f�[�^�̃v���p�e�B�`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�p�����[�^.�\�񒍕��P��.is���Δ������()==false <BR>
     * �@@�@@�@@�i�����Ύ���łȂ��j�̏ꍇ�́A <BR>
     * �@@�@@�@@���N�G�X�g�f�[�^.validateAT�����c���()���R�[�����A <BR>
     * �@@�@@�@@return����B <BR>
     * <BR>
     * �Q�j�@@���N�G�X�g�f�[�^.validateAT���Ύ��()���R�[������B<BR>
     * <BR>
     * �R�j�@@�p�����[�^.�\�񒍕��P��.���Ϗ��� != null�@@���� <BR>
     * �@@�p�����[�^.�\�񒍕��P��.���Ϗ��� == "�����_��"�� <BR>
     * �@@�ꍇ�A�ȉ��̏��������{����B <BR>
     * �@@�R�|�P�j�@@���N�G�X�g�f�[�^.�ԍό��ʂ̗v�f�����A <BR>
     * �@@�@@�ȉ��̏������J��Ԃ��B <BR>
     * �@@�@@�R�|�P�|�P�j�@@�ԍό���.���ʂ��ȉ��̂����ꂩ�̏ꍇ�́A <BR>
     * �@@�@@�@@�@@�@@�u�ԍό��ʂ̐��ʎw�肪�s���v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@�Enull  <BR>
     * �@@�@@�@@�@@�@@�@@�E�����ȊO  <BR>
     * �@@�@@�@@�@@�@@�@@�E�O�ȉ��̐���  <BR>
     * �@@�@@�@@�@@�@@�@@�E�W���𒴂��鐔��  <BR>
     * �@@�@@class�FWEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�FBUSINESS_ERROR_03060<BR>
     * <BR>
     * �@@�@@�������̃��N�G�X�g�f�[�^�́A�ȉ��̂����ꂩ�ɃL���X�g���邱�ƁB <BR>
     * �@@�@@�@@�E�i�A���j�����w���敨�����ԍϊm�F���N�G�X�g <BR>
     * �@@�@@�@@�E�i�A���j�����w���敨�����ԍϊ������N�G�X�g <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@param l_orderUnit - (�\�񒍕��P��)<BR>
     * �\�񒍕��P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C6660D0286
     */
    protected void validateRequestDataAtReversingTrade(
        WEB3GenRequest l_request,
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateRequestDataAtReversingTrade(WEB3GenRequest, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null || l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3SuccFuturesCloseChangeConfirmRequest l_confirmRequest = null;
        WEB3SuccFuturesCloseChangeCompleteRequest l_completeRequest = null;
        WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits = null;
        if (l_request instanceof WEB3SuccFuturesCloseChangeConfirmRequest)
        {
            l_confirmRequest = (WEB3SuccFuturesCloseChangeConfirmRequest)l_request;
            l_closeMarginContractUnits = l_confirmRequest.closeMarginContractUnits;
        }
        else if (l_request instanceof WEB3SuccFuturesCloseChangeCompleteRequest)
        {
            l_completeRequest = (WEB3SuccFuturesCloseChangeCompleteRequest)l_request;
            l_closeMarginContractUnits = l_completeRequest.closeMarginContractUnits;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        // �p�����[�^.�\�񒍕��P��.is���Δ������()==false�i�����Ύ���łȂ��j�̏ꍇ
        if (!l_orderUnit.isReversingTrade())
        {
            // ���N�G�X�g�f�[�^.validateAT�����c���()���R�[�����Areturn����B
            if (l_confirmRequest != null)
            {
                l_confirmRequest.validateATExistingRemainderTrading();
            }
            else if (l_completeRequest != null)
            {
                l_completeRequest.validateATExistingRemainderTrading();
            }

            log.exiting(STR_METHOD_NAME);
            return;
        }

        // ���N�G�X�g�f�[�^.validateAT���Ύ��()���R�[������B
        if (l_confirmRequest != null)
        {
            l_confirmRequest.validateAtReverseOrder();
        }
        else if (l_completeRequest != null)
        {
            l_completeRequest.validateAtReverseOrder();
        }

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
            (RsvIfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        // ���Ϗ���
        String l_strClosingOrder = l_rsvIfoOrderUnitRow.getClosingOrder();

        WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = null;

        // �p�����[�^.�\�񒍕��P��.���Ϗ��� != null�@@����
        // �p�����[�^.�\�񒍕��P��.���Ϗ��� == "�����_��"�̏ꍇ�A�ȉ��̏��������{����B
        if (l_strClosingOrder != null && WEB3ClosingOrderDef.RANDOM.equals(l_strClosingOrder))
        {
            int l_intLength = l_closeMarginContractUnits.length;
            for (int i = 0; i < l_intLength; i++)
            {
                l_closeMarginContractUnit = l_closeMarginContractUnits[i];
                String l_strQuantity = l_closeMarginContractUnit.contractOrderQuantity;

                if (l_strQuantity == null || !WEB3StringTypeUtility.isNumber(l_strQuantity)
                    || Long.parseLong(l_strQuantity) <= 0 || l_strQuantity.length() > 8)
                {
                    log.debug("�ԍό��ʂ̒������ʎw�肪�s���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03060,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ԍό��ʂ̒������ʎw�肪�s���B");
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�m�F���N�G�X�g)<BR>
     * �����̃��N�G�X�g�f�[�^�A�\�񒍕��P�ʂ�� <BR>
     * �i�A���j�����w���敨�ԍϒ����m�F���N�G�X�g�I�u�W�F�N�g�� <BR>
     * �쐬����B <BR>
     * <BR>
     * �P�j�@@�߂�l�̃C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@���ʃv���p�e�B�Z�b�g�B <BR>
     * �@@this.set�����w���敨���ʓ��̓��N�G�X�g()���R�[������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�ioutput�j���ʃ��N�G�X�g�F�@@�P�j�̖߂�l <BR>
     * �@@�@@�iinput�j���ʃ��N�G�X�g�F�@@�p�����[�^.���N�G�X�g�f�[�^ <BR>
     * <BR>
     * �R�j�@@���������C���X�^���X���L�̃v���p�e�B���Z�b�g����B <BR>
     * �@@�ԍό��� = �p�����[�^.���N�G�X�g�f�[�^.�ԍό���<BR>
     * �@@���Ϗ��� = �\�񒍕��P��.���Ϗ���<BR>
     * �@@�A���������ʏ�� = �\�񒍕��P��.create�A���������ʏ��()<BR>
     * �@@�P�������l��� = �p�����[�^.���N�G�X�g�f�[�^.�P�������l��� <BR>
     * <BR>
     * �S�j�@@�v���p�e�B�Z�b�g�����m�F���N�G�X�g��ԋp����B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@param l_orderUnit - (�\�񒍕��P��)<BR>
     * �\�񒍕��P��<BR>
     * @@return WEB3SuccFuturesCloseConfirmRequest
     * @@roseuid 47C6629B0266
     */
    protected WEB3SuccFuturesCloseConfirmRequest createConfirmRequest(
        WEB3SuccFuturesCloseChangeConfirmRequest l_request,
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "createConfirmRequest(WEB3SuccFuturesCloseChangeConfirmRequest, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        // �߂�l�̃C���X�^���X�𐶐�����B
        WEB3SuccFuturesCloseConfirmRequest l_confirmRequest = new WEB3SuccFuturesCloseConfirmRequest();

        // ���ʃv���p�e�B�Z�b�g�B
        // this.set�����w���敨���ʓ��̓��N�G�X�g()���R�[������B
        this.setFuturesCommonRequest(l_confirmRequest, l_request);

        // ���������C���X�^���X���L�̃v���p�e�B���Z�b�g����B
        // �ԍό��� = �p�����[�^.���N�G�X�g�f�[�^.�ԍό���
        l_confirmRequest.closeMarginContractUnits = l_request.closeMarginContractUnits;

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
            (RsvIfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        // ���Ϗ��� = �\�񒍕��P��.���Ϗ���
        l_confirmRequest.closingOrder = l_rsvIfoOrderUnitRow.getClosingOrder();

        // �A���������ʏ�� = �\�񒍕��P��.create�A���������ʏ��()
        l_confirmRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();

        // �P�������l��� = �p�����[�^.���N�G�X�g�f�[�^.�P�������l���
        l_confirmRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        log.exiting(STR_METHOD_NAME);
        return l_confirmRequest;
    }

    /**
     * (create�������N�G�X�g)<BR>
     * �����̃��N�G�X�g�f�[�^�A�\�񒍕��P�ʂ�� <BR>
     * �i�A���j�����w���敨�ԍϒ����������N�G�X�g�I�u�W�F�N�g�� <BR>
     * �쐬����B <BR>
     * <BR>
     * �P�j�@@�߂�l�̃C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@���ʃv���p�e�B�Z�b�g�B <BR>
     * �@@this.set�����w���敨���ʓ��̓��N�G�X�g()���R�[������B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�ioutput�j���ʃ��N�G�X�g�F�@@�P�j�̖߂�l <BR>
     * �@@�@@�iinput�j���ʃ��N�G�X�g�F�@@�p�����[�^.���N�G�X�g�f�[�^ <BR>
     * <BR>
     * �R�j�@@���������C���X�^���X���L�̃v���p�e�B���Z�b�g����B<BR>
     * �@@����ID = �\�񒍕��P��.����ID<BR>
     * �@@�ԍό��� = �p�����[�^.���N�G�X�g�f�[�^.�ԍό���<BR>
     * �@@���Ϗ��� = �\�񒍕��P��.���Ϗ���<BR>
     * �@@�Ïؔԍ� = �p�����[�^.���N�G�X�g�f�[�^.�Ïؔԍ�<BR>
     * �@@�m�F���P�� = �p�����[�^.���N�G�X�g�f�[�^.�m�F���P��<BR>
     * �@@�m�F�������� = �p�����[�^.���N�G�X�g�f�[�^.�m�F��������<BR>
     * �@@�A���������ʏ�� = �\�񒍕��P��.create�A���������ʏ��()<BR>
     * �@@�P�������l��� = �p�����[�^.���N�G�X�g�f�[�^.�P�������l���<BR>
     * �@@������P�� = �p�����[�^.���N�G�X�g�f�[�^.������P��<BR>
     * <BR>
     * �S�j�@@�v���p�e�B�Z�b�g�����������N�G�X�g��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@param l_orderUnit - (�\�񒍕��P��)<BR>
     * �\�񒍕��P��<BR>
     * @@return WEB3SuccFuturesCloseCompleteRequest
     * @@roseuid 47C6685A02C1
     */
    protected WEB3SuccFuturesCloseCompleteRequest createCompleteRequest(
        WEB3SuccFuturesCloseChangeCompleteRequest l_request,
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "createCompleteRequest(WEB3SuccFuturesCloseCompleteRequest, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        // �߂�l�̃C���X�^���X�𐶐�����B
        WEB3SuccFuturesCloseCompleteRequest l_completeRequest = new WEB3SuccFuturesCloseCompleteRequest();

        // ���ʃv���p�e�B�Z�b�g�B
        // this.set�����w���敨���ʓ��̓��N�G�X�g()���R�[������B
        this.setFuturesCommonRequest(l_completeRequest, l_request);

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
            (RsvIfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        // ���������C���X�^���X���L�̃v���p�e�B���Z�b�g����B
        // ����ID = �\�񒍕��P��.����ID
        l_completeRequest.orderId = String.valueOf(l_rsvIfoOrderUnitRow.getOrderId());

        // �ԍό��� = �p�����[�^.���N�G�X�g�f�[�^.�ԍό���
        l_completeRequest.closeMarginContractUnits = l_request.closeMarginContractUnits;

        // ���Ϗ��� = �\�񒍕��P��.���Ϗ���
        l_completeRequest.closingOrder = l_rsvIfoOrderUnitRow.getClosingOrder();

        // �Ïؔԍ� = �p�����[�^.���N�G�X�g�f�[�^.�Ïؔԍ�
        l_completeRequest.password = l_request.password;

        // �m�F���P�� = �p�����[�^.���N�G�X�g�f�[�^.�m�F���P��
        l_completeRequest.checkPrice = l_request.checkPrice;

        // �m�F�������� = �p�����[�^.���N�G�X�g�f�[�^.�m�F��������
        l_completeRequest.checkDate = l_request.checkDate;

        // �A���������ʏ�� = �\�񒍕��P��.create�A���������ʏ��()
        l_completeRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();

        // �P�������l��� = �p�����[�^.���N�G�X�g�f�[�^.�P�������l���
        l_completeRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        // ������P�� = �p�����[�^.���N�G�X�g�f�[�^.������P��
        l_completeRequest.afterAdjustmentPrice = l_request.afterAdjustmentPrice;

        log.exiting(STR_METHOD_NAME);
        return l_completeRequest;
    }

    /**
     * (create�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g�����쐬����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * �P�j�@@���Δ����̏ꍇ <BR>
     * �@@�i�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true�j <BR>
     * �@@�A�������}�l�[�W��Impl.create�ԍό��ʃG���g��()���R�[�����A <BR>
     * �@@�߂�l��ԋp����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�ԍό��ʁF�@@���������ԍό���(*1) <BR>
     * <BR>
     * �@@(*1)�ȉ��̃v���p�e�B���Z�b�g�����ԍό��ʃC���X�^���X <BR>
     * �@@�@@�݂̂�v�f�Ƃ���z�� <BR>
     * <BR>
     * �@@�@@�ԍό���.�������ʁF�@@this.get��������()�̖߂�l��ݒ肷��B <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@���N�G�X�g�A�_�v�^�F�@@�p�����[�^.���N�G�X�g�A�_�v�^ <BR>
     * <BR>
     * �Q�j�@@�P�j�ȊO�̏ꍇ�A <BR>
     * �@@�敨�����}�l�[�W��.create�ԍό��ʃG���g��()���R�[�����A <BR>
     * �@@�߂�l��ԋp����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�����P��ID�F�@@-1�i�\�񒍕��������̏�Ԃ�\���B�j <BR>
     * �@@�@@�������ʁF�@@�p�����[�^.���N�G�X�g�A�_�v�^.get��������()�߂�l <BR>
     * �@@�@@�ԍό���[]�F�@@�p�����[�^.�ԍό��� <BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@param l_closeMarginContractUnits - (�ԍό���)<BR>
     * �ԍό��ʃI�u�W�F�N�g�̔z��<BR>
     * �i���N�G�X�g�f�[�^�j<BR>
     * @@return SettleContractEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 47C683400180
     */
    protected SettleContractEntry[] createSettleContractEntry(
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntry("
            + "WEB3FuturesSettleContractOrderRequestAdapter, WEB3FuturesOptionsCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;

        SettleContractEntry[] l_settleContractEntries = null;

        // ���Δ����̏ꍇ
        if (l_adapter.isReversingOrder())
        {
            // this.get��������()�̖߂�l��ݒ肷��B
            double l_dblOrderQuantity = this.getOrderQuantity(l_adapter);

            int l_intLength = l_closeMarginContractUnits.length;
            for (int i = 0; i < l_intLength; i++)
            {
                l_closeMarginContractUnits[i].contractOrderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity);
            }

            // �A�������}�l�[�W��Impl���擾
            WEB3ToSuccOrderManagerImpl l_orderManager =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

            // �A�������}�l�[�W��Impl.create�ԍό��ʃG���g��()
            l_settleContractEntries =
                l_orderManager.createSettleContractEntry(l_closeMarginContractUnits);
        }
        // �ȊO�̏ꍇ
        else
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

            // �敨�����}�l�[�W��Impl���擾
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

            // �敨�����}�l�[�W��.create�ԍό��ʃG���g��()
            l_settleContractEntries =
                l_orderManager.createSettleContractEntry(
                -1,
                l_adapter.getOrderQuantity(),
                l_closeMarginContractUnits);
        }

        log.exiting(STR_METHOD_NAME);
        return l_settleContractEntries;
    }

    /**
     * (get��������)<BR>
     * �ԍό��ʂɐݒ肷�钍�����ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@�������ʂ����߂�B�@@<BR>
     * �@@�@@�擾�������Ϗ�����"�����_��"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�������� ���g�p�B<BR>
     * �@@�@@�擾�������Ϗ���=="�����_��"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�ԍό��ʂ̑S�v�f�̒������ʂ�SUM�l���g�p�B<BR>
     * <BR>
     * �Q�j�@@�P�j�ŋ��߂��������� > ���N�G�X�g�A�_�v�^.�e�����̒����P��.�������ʂ̏ꍇ�A<BR>
     * �@@�@@�u�������ʂ��e�����̒������ʂ𒴉߁v�̗�O���X���[����B<BR>
     * �@@�@@�@@class�FWEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�FBUSINESS_ERROR_03065<BR>
     * <BR>
     * �R�j�@@�Q�j�̏����ɊY�����Ȃ��ꍇ�A�P�j�ŋ��߂��������ʂ�ԋp����B<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 47C68A69021A
     */
    private double getOrderQuantity(WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderQuantity(WEB3ToSuccFuturesSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        // �������ʂ����߂�B
        double l_dblOrderQuantity = 0.0D;

        WEB3SuccFuturesCloseConfirmRequest l_confirmRequest = null;
        WEB3SuccFuturesCloseCompleteRequest l_completeRequest = null;
        String l_strClosingOrder = null;
        String l_strfutOrderQuantity = null;
        WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits = null;

        if (l_requestAdapter.request instanceof WEB3SuccFuturesCloseConfirmRequest)
        {
            l_confirmRequest = (WEB3SuccFuturesCloseConfirmRequest)l_requestAdapter.request;
            l_strClosingOrder = l_confirmRequest.closingOrder;
            l_strfutOrderQuantity = l_confirmRequest.futOrderQuantity;
            l_closeMarginContractUnits = l_confirmRequest.closeMarginContractUnits;
        }
        else if (l_requestAdapter.request instanceof WEB3SuccFuturesCloseCompleteRequest)
        {
            l_completeRequest = (WEB3SuccFuturesCloseCompleteRequest)l_requestAdapter.request;
            l_strClosingOrder = l_completeRequest.closingOrder;
            l_strfutOrderQuantity = l_completeRequest.futOrderQuantity;
            l_closeMarginContractUnits = l_completeRequest.closeMarginContractUnits;
        }

        // �擾�������Ϗ�����"�����_��"�̏ꍇ
        if (!WEB3ClosingOrderDef.RANDOM.equals(l_strClosingOrder))
        {
            // ���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�������� ���g�p
            l_dblOrderQuantity = Double.parseDouble(l_strfutOrderQuantity);
        }
        // �擾�������Ϗ���=="�����_��"�̏ꍇ
        else
        {
            BigDecimal l_bdOrderQuantitySum = new BigDecimal("0");

            int l_intLength = l_closeMarginContractUnits.length;
            // ���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�ԍό��ʂ̑S�v�f�̒������ʂ�SUM�l���g�p�B
            for (int i = 0; i < l_intLength; i++)
            {
                String l_strOrderQuantity = l_closeMarginContractUnits[i].contractOrderQuantity;
                l_bdOrderQuantitySum = l_bdOrderQuantitySum.add(new BigDecimal(l_strOrderQuantity));
            }

            l_dblOrderQuantity = l_bdOrderQuantitySum.doubleValue();
        }

        // ���߂��������� > ���N�G�X�g�A�_�v�^.�e�����̒����P��.�������ʂ̏ꍇ
        if (l_dblOrderQuantity > l_requestAdapter.parentOrderUnit.getQuantity())
        {
            log.debug("�������ʂ��e�����̒������ʂ𒴉߂��Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03065,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂ��e�����̒������ʂ𒴉߂��Ă��܂��B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblOrderQuantity;
    }

    /**
     * (validate�A�������ő�ݒ萔)<BR>
     * �A�������̍ő�ݒ萔�𒴉߂��Ȃ����`�F�b�N������B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P��<BR>
     * @@roseuid 47C68CA1017E
     */
    protected void validateSuccOrderMaxQuantity(OrderUnit l_parentOrderUnit)
    {
        return;
    }

    /**
     * (notify�\�񒍕��o�^)<BR>
     * �\�񒍕��̓o�^�����[���G���W���T�[�o�ɒʒm����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_lngChildOrderId - (�q�����̒���ID)<BR>
     * �q�����̒���ID�B<BR>
     * @@roseuid 47C68CCB00F3
     */
    protected void notifyRsvOrderRegister(long l_lngChildOrderId)
    {
        return;
    }

    /**
     * (submit�ԍϒ���)<BR>
     * �敨�ԍϒ�����o�^����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_orderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_commission - (�萔��)<BR>
     * �萔��<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (�T�Z��n����v�Z����)<BR>
     * �T�Z��n����v�Z����<BR>
     * @@roseuid 47C6938901B3
     */
    protected void submitSettleContractOrder(
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_orderSpec,
        long l_lngOrderId,
        WEB3GentradeCommission l_commission,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
    {
        return;
    }

    /**
     * (set�����w���敨���ʓ��̓��N�G�X�g)<BR>
     * �w�肳�ꂽ�u�ioutput�j���ʃ��N�G�X�g�v�̃C���X�^���X�ɁA�v���p�e�B���Z�b�g����<BR>
     * <BR>
     * �ȉ��̃v���p�e�B�ɁA�u�iinput�j���ʃ��N�G�X�g�v�̓����v���p�e�B�̒l���Z�b�g����B<BR>
     * <BR>
     * [�Ώۃv���p�e�B]<BR>
     * �@@��������<BR>
     * �@@�����P���敪<BR>
     * �@@�����P��<BR>
     * �@@���s����<BR>
     * �@@���������敪<BR>
     * �@@�����L������<BR>
     * �@@���������敪<BR>
     * �@@�t�w�l�p���������P��<BR>
     * �@@�t�w�l�p�����������Z�q<BR>
     * �@@W�w�l�p���������P��<BR>
     * �@@W�w�l�p�����������Z�q<BR>
     * �@@W�w�l�p�����P���敪<BR>
     * �@@W�w�l�p�����P��<BR>
     * �@@W�w�l�p���s����<BR>
     * �@@W�w�l�p�L����ԋ敪<BR>
     * @@param l_outputCommonRequest - (�ioutput�j���ʃ��N�G�X�g)<BR>
     * �����w���敨���ʓ��̓��N�G�X�g<BR>
     * @@param l_inputCommonRequest - (�iinput�j���ʃ��N�G�X�g)<BR>
     * �����w���敨���ʓ��̓��N�G�X�g<BR>
     * @@return WEB3FuturesCommonRequest
     * @@roseuid 47C7654903E6
     */
    protected WEB3FuturesCommonRequest setFuturesCommonRequest(
        WEB3FuturesCommonRequest l_outputCommonRequest,
        WEB3FuturesCommonRequest l_inputCommonRequest)
    {
        final String STR_METHOD_NAME =
            "setFuturesCommonRequest(WEB3FuturesCommonRequest, WEB3FuturesCommonRequest)";
        log.entering(STR_METHOD_NAME);

        // ��������
        l_outputCommonRequest.futOrderQuantity = l_inputCommonRequest.futOrderQuantity;

        // �����P���敪
        l_outputCommonRequest.orderPriceDiv = l_inputCommonRequest.orderPriceDiv;

        // �����P��
        l_outputCommonRequest.limitPrice = l_inputCommonRequest.limitPrice;

        // ���s����
        l_outputCommonRequest.execCondType = l_inputCommonRequest.execCondType;

        // ���������敪
        l_outputCommonRequest.expirationDateType = l_inputCommonRequest.expirationDateType;

        // �����L������
        l_outputCommonRequest.expirationDate = l_inputCommonRequest.expirationDate;

        // ���������敪
        l_outputCommonRequest.orderCondType = l_inputCommonRequest.orderCondType;

        // �t�w�l�p���������P��
        l_outputCommonRequest.stopOrderCondPrice = l_inputCommonRequest.stopOrderCondPrice;

        // �t�w�l�p�����������Z�q
        l_outputCommonRequest.stopOrderCondOperator = l_inputCommonRequest.stopOrderCondOperator;

        // W�w�l�p���������P��
        l_outputCommonRequest.wlimitOrderCondPrice = l_inputCommonRequest.wlimitOrderCondPrice;

        // W�w�l�p�����������Z�q
        l_outputCommonRequest.wlimitOrderCondOperator = l_inputCommonRequest.wlimitOrderCondOperator;

        // W�w�l�p�����P���敪
        l_outputCommonRequest.wLimitOrderPriceDiv = l_inputCommonRequest.wLimitOrderPriceDiv;

        // W�w�l�p�����P��
        l_outputCommonRequest.wLimitPrice = l_inputCommonRequest.wLimitPrice;

        // W�w�l�p���s����
        l_outputCommonRequest.wlimitExecCondType = l_inputCommonRequest.wlimitExecCondType;

        // W�w�l�p�L����ԋ敪
        l_outputCommonRequest.wlimitEnableStatusDiv = l_inputCommonRequest.wlimitEnableStatusDiv;

        log.exiting(STR_METHOD_NAME);
        return l_outputCommonRequest;
    }

    /**
     * (validate���ύό���)<BR>
     * �\�񌈍ϒ����̑ΏۂƂȂ��Ă��錚�ʂ�<BR>
     * �ʒ����ɂ�茈�ύςƂȂ��Ă��邩�ǂ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�A�������}�l�[�W��Impl.create���ʖ���ByOrder()<BR>
     * �@@���R�[������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�\�񒍕��P�ʁF�@@�p�����[�^.�\�񒍕��P��<BR>
     * <BR>
     * �@@null���ԋp���ꂽ�ꍇ�A<BR>
     * �@@�u�\�񌈍ϑΏی��ʂ͕ʒ����ɂ�茈�ύρv<BR>
     * �@@�̗�O���X���[����B<BR>
     * �@@class�FWEB3BusinessLayerException<BR>
     * �@@�@@tag�FBUSINESS_ERROR_03066<BR>
     * <BR>
     * @@param l_orderUnit - (�\�񒍕��P��)<BR>
     * �敨OP�\�񒍕��P�ʃI�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C7698D0338
     */
    protected void validateSettledContract(WEB3ToSuccIfoOrderUnitImpl l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSettledContract(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // �A�������}�l�[�W��Impl.create���ʖ���ByOrder()���R�[������B
        WEB3FuturesOptionsContractUnit[] l_contractUnits =
            l_orderManager.createIfoContractUnitByOrder(l_orderUnit);

        // null���ԋp���ꂽ�ꍇ
        if (l_contractUnits == null)
        {
            log.debug("�\�񌈍ϑΏی��ʂ͕ʒ����ɂ�茈�ύςł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03066,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�񌈍ϑΏی��ʂ͕ʒ����ɂ�茈�ύςł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
