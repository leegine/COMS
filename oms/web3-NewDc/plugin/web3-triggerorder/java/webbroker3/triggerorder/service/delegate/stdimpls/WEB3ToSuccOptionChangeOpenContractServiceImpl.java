head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionChangeOpenContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���jOP�����V�K���T�[�r�XImpl�iWEB3ToSuccOptionChangeOpenContractServiceImpl.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/11 �g�E�N�| (���u) �V�K�쐬 ���f��282
Revision History : 2008/04/14 �g�E�N�| (���u) �d�l�ύX ���f��317
Revision History : 2008/04/22 �g�E�N�| (���u) �d�l�ύX ���f��336
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.message.WEB3OptionsCommonRequest;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderRequestAdapter;
import webbroker3.triggerorder.WEB3ToSuccIfoChangeOpenContractOrderSpec;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.define.WEB3ToSuccOpProductTypeDef;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���jOP�����V�K���T�[�r�XImpl)<BR>
 * �i�A���j�I�v�V���������V�K���T�[�r�X�����N���X<BR>
 *
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3ToSuccOptionChangeOpenContractServiceImpl
    extends WEB3ToSuccOptionOpenContractOrderServiceImpl
    implements WEB3ToSuccOptionChangeOpenContractService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToSuccOptionChangeOpenContractServiceImpl.class);

    /**
     * @@roseuid 47FDBE400186
     */
    public WEB3ToSuccOptionChangeOpenContractServiceImpl()
    {

    }

    /**
     * �i�A���j�I�v�V���������V�K���T�[�r�X���������{����B<BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate()�����܂��́Asubmit����()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A920630056
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
        if (l_request instanceof WEB3SuccOptionsOpenChangeConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccOptionsOpenChangeConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3SuccOptionsOpenChangeCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccOptionsOpenChangeCompleteRequest)l_request);
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
     * �i�A���j�I�v�V���������V�K�������̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���jOP�����V�K���T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�����w���I�v�V���������V�K�������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3SuccOptionsOpenChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A920630037
     */
    protected WEB3SuccOptionsOpenChangeConfirmResponse validateOrder(
        WEB3SuccOptionsOpenChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SuccOptionsOpenChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // get�敨OP�\�񒍕��P��(long)
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));

        // validate�����\���( )
        l_toSuccIfoOrderUnit.validateOrderForChangeability();

        // create�m�F���N�G�X�g(�i�A���j�����w���I�v�V���������V�K���m�F���N�G�X�g, �敨OP�\�񒍕��P��Impl)
        WEB3SuccOptionsOpenConfirmRequest l_confirmRequest =
            this.createConfirmRequest(l_request, l_toSuccIfoOrderUnit);

        // validate����(�i�A���j�����w���I�v�V�����V�K�������m�F���N�G�X�g)
        WEB3SuccOptionsOpenConfirmResponse l_confirmResponse = super.validateOrder(l_confirmRequest);

        // validate�[��܂Œ��������\(String, �敨OP�\�񒍕��P��Impl)
        l_orderManager.validateEveningSessionOrderPossibleChange(
            l_request.expirationDateType, l_toSuccIfoOrderUnit);

        // createResponse( )
        WEB3SuccOptionsOpenChangeConfirmResponse l_response =
            (WEB3SuccOptionsOpenChangeConfirmResponse)l_request.createResponse();

        // ����萔�� �F 0(�Œ�)
        l_response.partExecQuantity = "0";

        // �T�Z��n����@@�F�@@super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.estimatedPrice = l_confirmResponse.estimatedPrice;

        // �萔���R�[�X �F super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.commissionCourse = l_confirmResponse.commissionCourse;

        // �萔�� �F super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.commission = l_confirmResponse.commission;

        // �萔������� �F super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.commissionConsumptionTax = l_confirmResponse.commissionConsumptionTax;

        // ����I���x������ �F super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.messageSuspension = l_confirmResponse.messageSuspension;

        // �m�F���P�� �F super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.checkPrice = l_confirmResponse.checkPrice;

        // �m�F�������� �F super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.checkDate = l_confirmResponse.checkDate;

        // �����L������ �F super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.expirationDate = l_confirmResponse.expirationDate;

        // ������P�� �F
        // �@@���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ
        // �@@�@@�@@super.validate����()�̃��X�|���X���瓯���v���p�e�B���Z�b�g
        if (l_request.priceAdjustmentValueInfo != null)
        {
            l_response.afterAdjustmentPrice = l_confirmResponse.afterAdjustmentPrice;
        }
        // �@@��L�ȊO�̏ꍇ
        // �@@�@@�@@null���Z�b�g
        else
        {
            l_response.afterAdjustmentPrice = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����)<BR>
     * �i�A���j�I�v�V���������V�K�������̍X�V���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���jOP�����V�K���T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�����w���I�v�V���������V�K�������������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3SuccOptionsOpenChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A920630046
     */
    protected WEB3SuccOptionsOpenChangeCompleteResponse submitOrder(
        WEB3SuccOptionsOpenChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SuccOptionsOpenChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        // ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // get�敨OP�\�񒍕��P��(long)
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));

        // validate�����\���( )
        l_toSuccIfoOrderUnit.validateOrderForChangeability();

        // create�������N�G�X�g(�i�A���j�����w���I�v�V���������V�K���������N�G�X�g, �敨OP�\�񒍕��P��Impl)
        WEB3SuccOptionsOpenCompleteRequest l_completeRequest =
            this.createCompleteRequest(l_request, l_toSuccIfoOrderUnit);

        // submit����(�i�A���j�����w���I�v�V�����V�K�������������N�G�X�g)
        WEB3SuccOptionsOpenCompleteResponse l_completeResponse = super.submitOrder(l_completeRequest);

        // validate�[��܂Œ��������\(String, �敨OP�\�񒍕��P��Impl)
        l_orderManager.validateEveningSessionOrderPossibleChange(
            l_request.expirationDateType, l_toSuccIfoOrderUnit);

        // get�⏕����( )
        SubAccount l_subAccount = this.getSubAccount();

        // ������w�l
        double l_dblModifiedLimitPrice = 0.0;

        // ���N�G�X�g�f�[�^.�����P���敪 �� "���s" �ȊO�̏ꍇ�A���N�G�X�g�f�[�^.�����P�����Z�b�g
        if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_dblModifiedLimitPrice = Double.parseDouble(l_request.limitPrice);
        }

        // ������v�Z�P��
        double l_dblModifiedCalcUnitPrice = 0.0D;

        // ���N�G�X�g�f�[�^.�m�F���P�� != null�̏ꍇ�A���N�G�X�g�f�[�^.�m�F���P��
        if (l_request.checkPrice != null)
        {
            l_dblModifiedCalcUnitPrice = Double.parseDouble(l_request.checkPrice);
        }
        // ���N�G�X�g�f�[�^.�m�F���P�� == null�̏ꍇ
        else
        {
            // ���N�G�X�g�f�[�^.�P��������� != null�̏ꍇ�A���N�G�X�g�f�[�^.������P��
            if (l_request.priceAdjustmentValueInfo != null)
            {
                l_dblModifiedCalcUnitPrice = Double.parseDouble(l_request.afterAdjustmentPrice);
            }
            // �ȊO�A���N�G�X�g�f�[�^.�����P��
            else
            {
                l_dblModifiedCalcUnitPrice = Double.parseDouble(l_request.limitPrice);
            }
        }

        // �����㒍��������
        Date l_datModifiedExpirationDate = null;

        // ���N�G�X�g�f�[�^.���������敪 == "�o����܂Œ���"�̏ꍇ�A���N�G�X�g�f�[�^.�����L���������Z�b�g
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
        {
            l_datModifiedExpirationDate = l_request.expirationDate;
        }
        // �ȊO�A���N�G�X�g�f�[�^.�m�F�����������Z�b�g
        else
        {
            l_datModifiedExpirationDate = l_request.checkDate;
        }

        // �㗝���͎�
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();

        // ������P�������l
        Double l_modifiedPriceAdjustValue = null;
        if (l_request.priceAdjustmentValueInfo != null)
        {
            l_modifiedPriceAdjustValue =
                new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }

        // create�敨OP�\��V�K�������������e(long, double, double, double, double, Date, ����, Double, String)
        WEB3ToSuccIfoChangeOpenContractOrderSpec l_toSuccIfoChangeOpenContractOrderSpec =
            WEB3ToSuccIfoChangeOpenContractOrderSpec.createIfoChangeOpenContractOrderSpec(
            l_toSuccIfoOrderUnit.getOrderId(),
            Double.parseDouble(l_request.opOrderQuantity),
            l_dblModifiedLimitPrice,
            Double.parseDouble(l_request.estimatedPrice),
            l_dblModifiedCalcUnitPrice,
            l_datModifiedExpirationDate,
            l_trader,
            l_modifiedPriceAdjustValue,
            l_request.expirationDateType);

        // submit�敨OP�����\��V�K������(SubAccount, �敨OP�\��V�K�������������e, String, �敨OP�\�񒍕��P��Impl)
        l_orderManager.submitIfoChangeOpenContractOrder(
            l_subAccount,
            l_toSuccIfoChangeOpenContractOrderSpec,
            l_request.password,
            l_toSuccIfoOrderUnit);

        // createResponse( )
        WEB3SuccOptionsOpenChangeCompleteResponse l_response =
            (WEB3SuccOptionsOpenChangeCompleteResponse)l_request.createResponse();

        // �X�V���ԁFsuper.submit�����̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.lastUpdatedTimestamp = l_completeResponse.lastUpdatedTimestamp;
        // ���ʔԍ��Fsuper.submit�����̃��X�|���X���瓯���v���p�e�B���Z�b�g
        l_response.orderActionId = l_completeResponse.orderActionId;
        // �A�������ݒ�t���O�Ffalse(�Œ�)
        l_response.succSettingFlag = false;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�V�K������)<BR>
     * �\�񒍕���o�^����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_orderSpec - (�V�K���������e)<BR>
     * �V�K���������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * �\�񒍕��̒���ID�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_requestAdaptor - (�V�K�����N�G�X�g�A�_�v�^)<BR>
     * �I�v�V�����V�K�����N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (�敨OP�T�Z��n����v�Z����)<BR>
     * �敨OP�T�Z��n����v�Z���ʁB<BR>
     * @@roseuid 47C3A5DE0348
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        IfoOpenContractOrderSpec l_orderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3OptionOpenContractOrderRequestAdapter l_requestAdaptor,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
    {
        return;
    }

    /**
     * (validate�A�������ő�ݒ萔)<BR>
     * �A�������̍ő�ݒ萔�𒴉߂��Ȃ����`�F�b�N����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g�B<BR>
     * @@roseuid 47C3A9F40352
     */
    protected void validateSuccOrderMaxQuantity(IfoOrderUnit l_parentOrderUnit)
    {
        return;
    }

    /**
     * (create�m�F���N�G�X�g)<BR>
     * �����̃��N�G�X�g�f�[�^�A�\�񒍕��P�ʂ��A<BR>
     * �i�A���j�����w���I�v�V�����V�K�������m�F���N�G�X�g�I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * �@@�P�j �߂�l�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�Q�j ���ʃv���p�e�B�Z�b�g�B<BR>
     * <BR>
     * �@@�@@�@@ this.set�I�v�V�������ʃ��N�G�X�g()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@ [�����ݒ�d�l]<BR>
     * �@@�@@�@@ �ioutput�j���ʃ��N�G�X�g �F �P�j�̖߂�l<BR>
     * �@@�@@�@@ �iinput�j ���ʃ��N�G�X�g �F ����.���N�G�X�g�f�[�^<BR>
     * <BR>
     * �@@�R�j ���������C���X�^���X���L�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@ �����R�[�h = �\�񒍕��P��.get����().�����R�[�h<BR>
     * �@@�@@�@@�@@ ���敪 = �\�񒍕��P��.is������() == true�̏ꍇ�A"����"�B�ȊO�̏ꍇ�A"����"�B<BR>
     * �@@�@@�@@�@@ ����s�� = �\�񒍕��P��.get�s��().getMarketCode()�̖߂�l<BR>
     * �@@�@@�@@�@@ �w����� = �\�񒍕��P��.get����().get�����Y�����R�[�h()�̖߂�l<BR>
     * �@@�@@�@@�@@ ���� = �\�񒍕��P��.get����().getMonthOfDelivery()�̖߂�l<BR>
     * �@@�@@�@@�@@ �I�v�V�������i�敪 = �\�񒍕��P��.get����().getDerivativeType()<BR>
     * �@@�@@�@@�@@ �s�g���i = �\�񒍕��P��.get����().getStrikePrice()<BR>
     * �@@�@@�@@�@@ �A���������ʏ�� = �\�񒍕��P��.create�A���������ʏ��()�̖߂�l<BR>
     * �@@�@@�@@�@@ �P�������l��� = ����.���N�G�X�g�f�[�^.�P�������l���<BR>
     * <BR>
     * �@@�S�j�@@�v���p�e�B�Z�b�g�����m�F���N�G�X�g��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@param l_orderUnit - (�\�񒍕��P��)<BR>
     * �敨OP�\�񒍕��P��Impl�I�u�W�F�N�g�B<BR>
     * @@return WEB3SuccOptionsOpenConfirmRequest
     * @@throws WEB3BaseException
     * @@roseuid 47C3AB610291
     */
    protected WEB3SuccOptionsOpenConfirmRequest createConfirmRequest(
        WEB3SuccOptionsOpenChangeConfirmRequest l_request,
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createConfirmRequest(WEB3SuccOptionsOpenChangeConfirmRequest, WEB3ToSuccIfoOrderUnitImpl)";
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

        // �P�j �߂�l�̃C���X�^���X�𐶐�����B
        WEB3SuccOptionsOpenConfirmRequest l_confirmRequest = new WEB3SuccOptionsOpenConfirmRequest();

        // �Q�j ���ʃv���p�e�B�Z�b�g�B
        // �@@this.set�I�v�V�������ʃ��N�G�X�g()���R�[������B
        this.setOptionsCommonRequest(l_confirmRequest, l_request);

        IfoProductRow l_ifoProductRow = (IfoProductRow)l_orderUnit.getProduct().getDataSourceObject();

        // �R�j ���������C���X�^���X���L�̃v���p�e�B���Z�b�g����B
        // �����R�[�h = �\�񒍕��P��.get����().�����R�[�h
        l_confirmRequest.opProductCode = l_ifoProductRow.getProductCode();

        // ���敪
        // �\�񒍕��P��.is������() == true�̏ꍇ�A"����"�B
        if (l_orderUnit.isBuyOrder())
        {
            l_confirmRequest.contractType = WEB3IfoContractTypeDef.OPEN_BUY;
        }
        // �ȊO�̏ꍇ�A"����"
        else
        {
            l_confirmRequest.contractType = WEB3IfoContractTypeDef.OPEN_SELL;
        }

        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = l_orderUnit.getMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ����s�� = �\�񒍕��P��.get�s��().getMarketCode()�̖߂�l
        l_confirmRequest.marketCode = l_market.getMarketCode();

        // �w����� = �\�񒍕��P��.get����().get�����Y�����R�[�h()�̖߂�l
        l_confirmRequest.targetProductCode = l_ifoProductRow.getUnderlyingProductCode();

        // ���� = �\�񒍕��P��.get����().getMonthOfDelivery()�̖߂�l
        l_confirmRequest.delivaryMonth = l_ifoProductRow.getMonthOfDelivery();

        // �I�v�V�������i�敪 = �\�񒍕��P��.get����().getDerivativeType()
        if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
        {
            l_confirmRequest.opProductType = WEB3ToSuccOpProductTypeDef.PUT_OPTIONS;
        }
        else if ((IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType())))
        {
            l_confirmRequest.opProductType = WEB3ToSuccOpProductTypeDef.CALL_OPTIONS;
        }

        // �s�g���i = �\�񒍕��P��.get����().getStrikePrice()
        l_confirmRequest.strikePrice = WEB3StringTypeUtility.formatNumber(l_ifoProductRow.getStrikePrice());

        // �A���������ʏ�� = �\�񒍕��P��.create�A���������ʏ��()�̖߂�l
        l_confirmRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();

        // �P�������l��� = ����.���N�G�X�g�f�[�^.�P�������l���
        l_confirmRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        log.exiting(STR_METHOD_NAME);
        return l_confirmRequest;
    }

    /**
     * (create�������N�G�X�g)<BR>
     * �����̃��N�G�X�g�f�[�^�A�\�񒍕��P�ʂ��A<BR>
     * �i�A���j�����w���I�v�V�����V�K�������������N�G�X�g�I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * �@@�P�j �߂�l�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�Q�j ���ʃv���p�e�B�Z�b�g�B<BR>
     * <BR>
     * �@@�@@�@@ this.set�I�v�V�������ʃ��N�G�X�g()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@ [�����ݒ�d�l]<BR>
     * �@@�@@�@@ �ioutput�j���ʃ��N�G�X�g �F �P�j�̖߂�l<BR>
     * �@@�@@�@@ �iinput�j ���ʃ��N�G�X�g �F ����.���N�G�X�g�f�[�^<BR>
     * <BR>
     * �@@�R�j ���������C���X�^���X���L�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@ ����ID = �\�񒍕��P��.����ID<BR>
     * �@@�@@�@@�@@ �����R�[�h = �\�񒍕��P��.get����().�����R�[�h<BR>
     * �@@�@@�@@�@@ ���敪 = �\�񒍕��P��.is������() == true�̏ꍇ�A"����"�B�ȊO�̏ꍇ�A"����"�B<BR>
     * �@@�@@�@@�@@ ����s�� = �\�񒍕��P��.get�s��().getMarketCode()�̖߂�l<BR>
     * �@@�@@�@@�@@ �Ïؔԍ� = ����.���N�G�X�g�f�[�^.�Ïؔԍ�<BR>
     * �@@�@@�@@�@@ �m�F���P�� = ����.���N�G�X�g�f�[�^.�m�F���P��<BR>
     * �@@�@@�@@�@@ �m�F�������� = ����.���N�G�X�g�f�[�^.�m�F��������<BR>
     * �@@�@@�@@�@@ �w����� = �\�񒍕��P��.get����().get�����Y�����R�[�h()�̖߂�l<BR>
     * �@@�@@�@@�@@ ���� = �\�񒍕��P��.get����().getMonthOfDelivery()�̖߂�l<BR>
     * �@@�@@�@@�@@ �I�v�V�������i�敪 = �\�񒍕��P��.get����().getDerivativeType()<BR>
     * �@@�@@�@@�@@ �s�g���i = �\�񒍕��P��.get����().getStrikePrice()<BR>
     * �@@�@@�@@�@@ �A���������ʏ�� = �\�񒍕��P��.create�A���������ʏ��()�̖߂�l<BR>
     * �@@�@@�@@�@@ �P�������l��� = ����.���N�G�X�g�f�[�^.�P�������l���<BR>
     * �@@�@@�@@�@@ ������P�� = ����.���N�G�X�g�f�[�^.������P��<BR>
     * <BR>
     * �@@�S�j�@@�v���p�e�B�Z�b�g�����������N�G�X�g��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@param l_orderUnit - (�\�񒍕��P��)<BR>
     * �敨OP�\�񒍕��P��Impl�I�u�W�F�N�g�B<BR>
     * @@return WEB3SuccOptionsOpenCompleteRequest
     * @@throws WEB3BaseException
     * @@roseuid 47C3AE5D032F
     */
    protected WEB3SuccOptionsOpenCompleteRequest createCompleteRequest(
        WEB3SuccOptionsOpenChangeCompleteRequest l_request,
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createcompleteRequest(WEB3SuccOptionsOpenChangeCompleteRequest, WEB3ToSuccIfoOrderUnitImpl)";
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

        // �P�j �߂�l�̃C���X�^���X�𐶐�����B
        WEB3SuccOptionsOpenCompleteRequest l_completeRequest = new WEB3SuccOptionsOpenCompleteRequest();

        // �Q�j ���ʃv���p�e�B�Z�b�g�B
        // �@@this.set�I�v�V�������ʃ��N�G�X�g()���R�[������B
        this.setOptionsCommonRequest(l_completeRequest, l_request);

        IfoProductRow l_ifoProductRow = (IfoProductRow)l_orderUnit.getProduct().getDataSourceObject();

        // �R�j ���������C���X�^���X���L�̃v���p�e�B���Z�b�g����B
        // ����ID = �\�񒍕��P��.����ID
        l_completeRequest.orderId = WEB3StringTypeUtility.formatNumber(l_orderUnit.getOrderId());

        // �����R�[�h = �\�񒍕��P��.get����().�����R�[�h
        l_completeRequest.opProductCode = l_ifoProductRow.getProductCode();

        // ���敪
        // �\�񒍕��P��.is������() == true�̏ꍇ�A"����"�B
        if (l_orderUnit.isBuyOrder())
        {
            l_completeRequest.contractType = WEB3IfoContractTypeDef.OPEN_BUY;
        }
        // �ȊO�̏ꍇ�A"����"
        else
        {
            l_completeRequest.contractType = WEB3IfoContractTypeDef.OPEN_SELL;
        }

        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = l_orderUnit.getMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ����s�� = �\�񒍕��P��.get�s��().getMarketCode()�̖߂�l
        l_completeRequest.marketCode = l_market.getMarketCode();

        // �Ïؔԍ� = ����.���N�G�X�g�f�[�^.�Ïؔԍ�
        l_completeRequest.password = l_request.password;

        // �m�F���P�� = ����.���N�G�X�g�f�[�^.�m�F���P��
        l_completeRequest.checkPrice = l_request.checkPrice;

        // �m�F�������� = ����.���N�G�X�g�f�[�^.�m�F��������
        l_completeRequest.checkDate = l_request.checkDate;

        // �w����� = �\�񒍕��P��.get����().get�����Y�����R�[�h()�̖߂�l
        l_completeRequest.targetProductCode = l_ifoProductRow.getUnderlyingProductCode();

        // ���� = �\�񒍕��P��.get����().getMonthOfDelivery()�̖߂�l
        l_completeRequest.delivaryMonth = l_ifoProductRow.getMonthOfDelivery();

        // �I�v�V�������i�敪 = �\�񒍕��P��.get����().getDerivativeType()
        if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
        {
            l_completeRequest.opProductType = WEB3ToSuccOpProductTypeDef.PUT_OPTIONS;
        }
        else if ((IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType())))
        {
            l_completeRequest.opProductType = WEB3ToSuccOpProductTypeDef.CALL_OPTIONS;
        }

        // �s�g���i = �\�񒍕��P��.get����().getStrikePrice()
        l_completeRequest.strikePrice = WEB3StringTypeUtility.formatNumber(l_ifoProductRow.getStrikePrice());

        // �A���������ʏ�� = �\�񒍕��P��.create�A���������ʏ��()�̖߂�l
        l_completeRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();

        // �P�������l��� = ����.���N�G�X�g�f�[�^.�P�������l���
        l_completeRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        // ������P�� = ����.���N�G�X�g�f�[�^.������P��
        l_completeRequest.afterAdjustmentPrice = l_request.afterAdjustmentPrice;

        log.exiting(STR_METHOD_NAME);
        return l_completeRequest;
    }

    /**
     * (setOP���ʃ��N�G�X�g)<BR>
     * �w�肳�ꂽ�u�ioutput�j���ʃ��N�G�X�g�v�̃C���X�^���X�ɁA�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �ȉ��̃v���p�e�B�ɂ��āA�u�iinput�j���ʃ��N�G�X�g�v�̓����v���p�e�B�̒l���Z�b�g����B<BR>
     * <BR>
     * �@@[�Ώۃv���p�e�B]<BR>
     * <BR>
     * �@@�@@ ��������<BR>
     * �@@�@@ �����P���敪<BR>
     * �@@�@@ �����P��<BR>
     * �@@�@@ ���s����<BR>
     * �@@�@@ ���������敪<BR>
     * �@@�@@ �����L������<BR>
     * �@@�@@ ���������敪<BR>
     * �@@�@@ �t�w�l�p�v���~�A���^�����Y���i<BR>
     * �@@�@@ �t�w�l�p���������P��<BR>
     * �@@�@@ �t�w�l�p�����������Z�q<BR>
     * �@@�@@ W�w�l�p�v���~�A���^�����Y���i<BR>
     * �@@�@@ W�w�l�p���������P��<BR>
     * �@@�@@ W�w�l�p�����������Z�q<BR>
     * �@@�@@ W�w�l�p�����P���敪<BR>
     * �@@�@@ W�w�l�p�����P��<BR>
     * �@@�@@ W�w�l�p���s����<BR>
     * �@@�@@ W�w�l�p�L����ԋ敪<BR>
     * @@param l_outputCommonRequest - (�ioutput�j���ʃ��N�G�X�g)<BR>
     * �����w���I�v�V�������ʃ��N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@param l_inputCommonRequest - (�iinput�j���ʃ��N�G�X�g)<BR>
     * �����w���I�v�V�������ʃ��N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@return WEB3OptionsCommonRequest
     * @@roseuid 47C3B07F01DB
     */
    protected WEB3OptionsCommonRequest setOptionsCommonRequest(
        WEB3OptionsCommonRequest l_outputCommonRequest,
        WEB3OptionsCommonRequest l_inputCommonRequest)
    {
        final String STR_METHOD_NAME =
            "setOptionsCommonRequest(WEB3OptionsCommonRequest, WEB3OptionsCommonRequest)";
        log.entering(STR_METHOD_NAME);

        // ��������
        l_outputCommonRequest.opOrderQuantity = l_inputCommonRequest.opOrderQuantity;

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

        // �t�w�l�p�v���~�A���^�����Y���i
        l_outputCommonRequest.stopPremium_underlyingAssets = l_inputCommonRequest.stopPremium_underlyingAssets;

        // �t�w�l�p���������P��
        l_outputCommonRequest.stopOrderCondPrice = l_inputCommonRequest.stopOrderCondPrice;

        // �t�w�l�p�����������Z�q
        l_outputCommonRequest.stopOrderCondOperator = l_inputCommonRequest.stopOrderCondOperator;

        // W�w�l�p�v���~�A���^�����Y���i
        l_outputCommonRequest.wlimitPremium_underlyingAssets = l_inputCommonRequest.wlimitPremium_underlyingAssets;

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
     * (notify�\�񒍕��o�^)<BR>
     * �\�񒍕��̓o�^�����[���G���W���T�[�o�ɒʒm����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_lngChildOrderId - (�q�����̒���ID)<BR>
     * �q�����̒���ID�B<BR>
     * @@roseuid 47C3B3100244
     */
    protected void notifyRsvOrderRegister(long l_lngChildOrderId)
    {
        return;
    }
}
@
