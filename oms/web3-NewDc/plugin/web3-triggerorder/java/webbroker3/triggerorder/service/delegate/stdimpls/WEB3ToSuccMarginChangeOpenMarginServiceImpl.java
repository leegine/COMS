head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeOpenMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �i�A���j�M�p��������V�K���T�[�r�XImpl(WEB3ToSuccMarginChangeOpenMarginServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/17�@@���@@��(���u) �V�K�쐬
 Revesion History : 2006/08/30 �ęԍg(���u) �d�l�ύX���f��165
 Revesion History : 2007/01/11 ꎉ�  (���u) �d�l�ύX���f��216
 Revesion History : 2007/01/19 �юu��(���u) �d�l�ύX���f��225                 
 Revesion History : 2007/08/20 ���g(���u) ���f��242
 */

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3MarginNewOrderValidationResult;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.message.WEB3MarginCommonRequest;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginRequestAdapter;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccEqtypeChangeOrderSpec;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeOpenMarginService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�M�p��������V�K���T�[�r�XImpl) <BR>
 * �i�A���j�M�p��������V�K���T�[�r�X�����N���X <BR>
 * 
 * @@author �� ��(���u)
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeOpenMarginServiceImpl extends WEB3ToSuccMarginOpenMarginServiceImpl implements
    WEB3ToSuccMarginChangeOpenMarginService
{
    /**
     * (���O�o�̓��[�e�B���e�B�B) <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeOpenMarginServiceImpl.class);

    /**
     * @@roseuid 436ACF7A01D4
     */
    public WEB3ToSuccMarginChangeOpenMarginServiceImpl()
    {

    }

    /**
     * �i�A���j�M�p��������V�K���T�[�r�X���������{���� <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A <BR>
     * validate������submit�������Ăѕ�����B <BR>
     * 
     * @@param l_request -
     *            ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BCCF803B0
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
        if (l_request instanceof WEB3SuccMarginOpenChangeConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccMarginOpenChangeConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3SuccMarginOpenChangeCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccMarginOpenChangeCompleteRequest) l_request);
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
     * (validate����) <BR>
     * �i�A���j�M�p����̒����V�K�������R�����s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�i�A���j�M�p��������V�K���T�[�r�X�jvalidate�����v�Q�ƁB <BR>
     * 
     * @@param l_request -
     *            (���N�G�X�g�f�[�^) <BR>
     *            �i�A���j�M�p������������V�K���m�F���N�G�X�g <BR>
     * @@return WEB3SuccMarginOpenChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BCDF903B0
     */
    protected WEB3SuccMarginOpenChangeConfirmResponse validateOrder(WEB3SuccMarginOpenChangeConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3SuccMarginOpenChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate
        l_request.validate();

        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        //1.2 get�����\�񒍕��P��(long)
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try
        {
            l_orderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_nfe.getMessage(),
                l_nfe);
        }

        //1.3 validate�����\���
        l_orderUnitImpl.validateOrderForChangeability();

        //1.4 create�m�F���N�G�X�g
        WEB3SuccMarginOpenConfirmRequest l_confirmRequest = this.createConfirmRequest(l_request, l_orderUnitImpl);

        //1.5 validate����(�i�A���j�M�p����V�K�������m�F���N�G�X�g)
        WEB3SuccMarginOpenConfirmResponse l_confirmResponse = super.validateOrder(l_confirmRequest);

        //1.6 get�⏕����
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        if (l_confirmResponse.estimatedPrice == null)
        {
            l_confirmResponse.estimatedPrice = "0";
        }

        //1.7 validate����������]��(�⏕����, double, �����\�񒍕��P��Impl)
        this.validateChangeTradingPower(
            l_subAccount, 
            Double.parseDouble(l_confirmResponse.estimatedPrice),
            l_orderUnitImpl);

        //1.8 createResponse( )
        WEB3SuccMarginOpenChangeConfirmResponse l_response = 
            (WEB3SuccMarginOpenChangeConfirmResponse) l_request.createResponse();

        //�m�F��������
        l_response.checkDate = l_confirmResponse.checkDate;
        
        //�T�Z��n���
        l_response.estimatedPrice = l_confirmResponse.estimatedPrice;

        //����I���x���s��R�[�h�ꗗ
        l_response.messageSuspension = l_confirmResponse.messageSuspension;

        //���o������
        l_response.partContQuantity = null;

        //�萔�����
        l_response.commissionInfo = l_confirmResponse.commissionInfo;

        //�m�F���P��
        l_response.checkPrice = l_confirmResponse.checkPrice;

        //�C���T�C�_�[�x���\���t���O
        l_response.insiderWarningFlag = l_confirmResponse.insiderWarningFlag;

        //�����L�������F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.expirationDate = l_confirmResponse.expirationDate;

        //������P��
        if (l_confirmRequest.priceAdjustmentValueInfo != null)
        {
            l_response.afterAdjustmentPrice = l_confirmResponse.afterAdjustmentPrice;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����) <BR>
     * �i�A���j�M�p����̒����V�K��������o�^����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�i�A���j�M�p��������V�K���T�[�r�X�jsubmit�����v�Q�ƁB <BR>
     * 
     * @@param l_request -
     *            (���N�G�X�g�f�[�^) <BR>
     *            �i�A���j�M�p������������V�K���������N�G�X�g <BR>
     * @@return WEB3SuccMarginOpenChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BCDF903BF
     */
    protected WEB3SuccMarginOpenChangeCompleteResponse submitOrder(WEB3SuccMarginOpenChangeCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3SuccMarginOpenChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate
        l_request.validate();

        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        //1.2 get�����\�񒍕��P��(long)
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try
        {
            l_orderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_nfe.getMessage(), 
                l_nfe);
        }

        //1.3 validate�����\���
        l_orderUnitImpl.validateOrderForChangeability();

        //1.4 create�������N�G�X�g
        WEB3SuccMarginOpenCompleteRequest l_completeRequest = this.createCompleteRequest(l_request, l_orderUnitImpl);

        //1.5 submit����(�i�A���j�M�p����V�K�������������N�G�X�g)
        WEB3SuccMarginOpenCompleteResponse l_completeResponse = super.submitOrder(l_completeRequest);

        //1.6 get�⏕����
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.7 validate����������]��(�⏕����, double, �����\�񒍕��P��Impl)
        this.validateChangeTradingPower(l_subAccount, Double.parseDouble(l_request.estimatedPrice), l_orderUnitImpl);

        //1.8 create�����\�񒍕��������e(long, double, double, double, double, Date,
        // boolean, ����, Double)
        if (l_request.limitPrice == null)
        {
            l_request.limitPrice = "0";
        }
        double l_dblModifiedLimitPrice = Double.parseDouble(l_request.limitPrice);
        Date l_datModifiedExpirationDate = l_request.checkDate;
        boolean l_blnModifiedIsCarriedOrder = false;
        Double l_modifiedPriceAdjustValue = null;
        if ((WEB3OrderPriceDivDef.MARKET_PRICE).equals(l_request.orderPriceDiv))
        {
            l_dblModifiedLimitPrice = 0;
        }

        if ((WEB3OrderExpirationDateTypeDef.CARRIED_ORDER).equals(l_request.expirationDateType))
        {
            l_datModifiedExpirationDate = l_request.expirationDate;
            l_blnModifiedIsCarriedOrder = true;
        }

        if (l_request.priceAdjustmentValueInfo != null)
        {
            l_modifiedPriceAdjustValue = new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }
        
        if (l_request.orderQuantity == null)
        {
            l_request.orderQuantity = "0";
        }
        
        if (l_request.checkPrice == null)
        {
            l_request.checkPrice = "0";
        }
        
        WEB3ToSuccEqtypeChangeOrderSpec l_changeOrderSpec = WEB3ToSuccEqtypeChangeOrderSpec.createEqtypeChangeOrderSpec(
            l_orderUnitImpl.getOrderId(), 
            Double.parseDouble(l_request.orderQuantity),
            l_dblModifiedLimitPrice, 
            Double.parseDouble(l_request.estimatedPrice), 
            Double.parseDouble(l_request.checkPrice), 
            l_datModifiedExpirationDate, 
            l_blnModifiedIsCarriedOrder,
            (WEB3GentradeTrader) this.getTrader(), 
            l_modifiedPriceAdjustValue);

        //1.9 submit���������\�񒍕�(SubAccount, �����\�񒍕��������e, String, �����\�񒍕��P��Impl)
        l_toOrderManager.submitEqtypeChangeOrder(l_subAccount, l_changeOrderSpec, l_request.password, l_orderUnitImpl);

        //1.10 createResponse( )
        WEB3SuccMarginOpenChangeCompleteResponse l_response = 
            (WEB3SuccMarginOpenChangeCompleteResponse) l_request.createResponse();

        //�X�V����
        l_response.lastUpdatedTimestamp = l_completeResponse.lastUpdatedTimestamp;

        //���ʔԍ�
        l_response.orderActionId = l_completeResponse.orderActionId;

        //�C���T�C�_�[�x���\���t���O
        l_response.insiderWarningFlag = l_completeResponse.insiderWarningFlag;

        //�A�������ݒ�t���O
        l_response.succSettingFlag = false;

        //�����L�������F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.expirationDate = l_completeResponse.expirationDate;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate����]��) <BR>
     * ����]�͂��`�F�b�N����B <BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * ����������return����B�i�J�������j <BR>
     * 
     * @@param l_subAccount -
     *            (�⏕����) <BR>
     *            �⏕�����I�u�W�F�N�g�B <BR>
     * @@param l_orderSpec -
     *            (�M�p�V�K���������e) <BR>
     *            �M�p�V�K���������e�I�u�W�F�N�g�B <BR>
     * @@param l_blnUpdateFlg -
     *            (�]�͍X�V�t���O) <BR>
     *            �]�͍X�V�t���O�B <BR>
     *            �ifalse�F �m�F���Atrue�F �������j <BR>
     * @@param l_commission -
     *            (�萔��) <BR>
     *            �萔���I�u�W�F�N�g <BR>
     * @@param l_validationResult -
     *            (�����R������) <BR>
     *            �M�p�V�K���V�K���������R�����ʃI�u�W�F�N�g <BR>
     * @@return WEB3TPTradingPowerResult
     * @@roseuid 433BCEEF0016
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginOpenContractOrderSpec l_orderSpec, 
        boolean l_blnUpdateFlg, 
        WEB3GentradeCommission l_commission,
        WEB3MarginNewOrderValidationResult l_validationResult)
    {
        final String STR_METHOD_NAME = " validateTradingPower(" +
            " WEB3GentradeSubAccount," +
            " WEB3MarginOpenContractOrderSpec," +
            " boolean," +
            " WEB3GentradeCommission," +
            " WEB3MarginNewOrderValidationResult)";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (validate����������]��) <BR>
     * ����]�͂��`�F�b�N���A����]�͌��ʃI�u�W�F�N�g��ԋp����B <BR>
     * <BR>
     * �]�̓`�F�b�N�����{���镔�X(*2)�̏ꍇ�̂݁A<BR>
     * �V�K���\�z(*1)�ƈ����̊T�Z��n������r���A <BR>
     * �i�T�Z��n��� > �V�K���\�z(*1)�j�̏ꍇ�́A <BR>
     * �u����]�͕s���v�̗�O��throw����B <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00667 <BR>
     * <BR>
     * �ȊO�Anull��ԋp����B <BR>
     * <BR>
     * (*1)�V�K���\�z <BR>
     * ����]�̓T�[�r�X.get�M�p�V�K���\�z�`�A�������`( <BR>
     * �����̕⏕����, �����̒����O�����P��.�T�Z��n���)�̖߂�l���A <BR>
     * �V�K���\�z�Ƃ���B <BR>
     * <BR>
     * (*2)�]�̓`�F�b�N�����{���镔�X<BR>
     * �A�������}�l�[�W��Impl.is�]�̓`�F�b�N���{���X()==true�̏ꍇ�́A<BR>
     * �]�̓`�F�b�N�����{���镔�X�ł���Ɣ��肷��B<BR>
     * @@param l_subAccount -
     *            (�⏕����) <BR>
     *            �⏕�����I�u�W�F�N�g�B <BR>
     * @@param l_dblEstimatedPrice -
     *            (�T�Z��n���) <BR>
     *            �T�Z��n����B <BR>
     * @@param l_beforeChangingRsvEqOrderUnit -
     *            (�����O�����P��) <BR>
     *            �����Ώۂ̊����\�񒍕��P�ʃI�u�W�F�N�g�B <BR>
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     * @@roseuid 433C92BD03C2
     */
    protected WEB3TPTradingPowerResult validateChangeTradingPower(WEB3GentradeSubAccount l_subAccount,
        double l_dblEstimatedPrice, WEB3ToSuccEqTypeOrderUnitImpl l_beforeChangingRsvEqOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChangeTradingPower(" +
            " WEB3GentradeSubAccount," + 
            " double,"+ 
            " WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        if (l_toOrderManager.isCheckTradingPowerBranch(l_subAccount) == false)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3TPTradingPowerService l_tradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);

        RsvEqOrderUnitRow l_orderUnitRow = (RsvEqOrderUnitRow) l_beforeChangingRsvEqOrderUnit.getDataSourceObject();
        //�V�K���\�z
        double l_dblTradingPower = l_tradingPowerService.getSuccMarginTradingPower(
            l_subAccount, 
            new Double(l_orderUnitRow.getEstimatedPrice()));

        if (l_dblEstimatedPrice > l_dblTradingPower)
        {
            log.debug("����]�͕s���G���[�B�i�M�p�V�K���\�z�s���j");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00667, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "����]�͕s���G���[�B�i�M�p�V�K���\�z�s���j");
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (submit�V�K������) <BR>
     * �\�񒍕���o�^����B <BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * ����������return����B�i�J�������j <BR>
     * 
     * @@param l_subAccount -
     *            (�⏕����) <BR>
     *            �⏕�����I�u�W�F�N�g�B <BR>
     * @@param l_orderSpec -
     *            (�⏕����) <BR>
     *            �M�p�V�K���������e�I�u�W�F�N�g�B <BR>
     * @@param l_lngOrderId -
     *            (����ID) <BR>
     *            �\�񒍕��̒���ID�B
     * @@param l_strTradingPassword -
     *            (����p�X���[�h) <BR>
     *            ����p�X���[�h�B <BR>
     * @@param l_requestAdaptor -
     *            (�V�K�����N�G�X�g�A�_�v�^) <BR>
     *            �M�p����V�K�����N�G�X�g�A�_�v�^�I�u�W�F�N�g <BR>
     * @@roseuid 433C92F800F3
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginOpenContractOrderSpec l_orderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword,
        WEB3MarginOpenMarginRequestAdapter l_requestAdaptor)
    {
        final String STR_METHOD_NAME = " submitOpenContractOrder(" +
            " WEB3GentradeSubAccount,"+
            " WEB3MarginOpenContractOrderSpec," + 
            " long,"+
            " String,"+
            " WEB3MarginOpenMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (validate�A�������ő�ݒ萔) <BR>
     * �A�������̍ő�ݒ萔�𒴉߂��Ă��܂�Ȃ����ǂ������`�F�b�N����B <BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * ����������return����B�i�J�������j <BR>
     * 
     * @@param l_parentOrderUnit -
     *            (�e�����̒����P��) <BR>
     *            �e�����̒����P�ʁB <BR>
     * @@roseuid 433C92BD03C7
     */
    protected void validateSuccOrderMaxQuantity(EqTypeOrderUnit l_parentOrderUnit)
    {
        final String STR_METHOD_NAME = " validateSuccOrderMaxQuantity(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (create�m�F���N�G�X�g) <BR>
     * �����̃��N�G�X�g�f�[�^�A�\�񒍕��P�ʂ�� <BR>
     * �i�A���j�M�p����V�K�������m�F���N�G�X�g�I�u�W�F�N�g�� <BR>
     * �쐬����B <BR>
     * <BR>
     * �P�j �߂�l�̃C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j ���ʃv���p�e�B�Z�b�g�B <BR>
     * this.set�M�p������ʃ��N�G�X�g()���R�[������B <BR>
     * <BR>
     * [set�M�p������ʃ��N�G�X�g()�ɃZ�b�g����p�����[�^] <BR>
     * �ioutput�j���ʃ��N�G�X�g�F �P�j�̖߂�l <BR>
     * �iinput�j���ʃ��N�G�X�g�F �p�����[�^.���N�G�X�g�f�[�^ <BR>
     * <BR>
     * �R�j ���������C���X�^���X���L�̃v���p�e�B���Z�b�g����B <BR>
     * �����R�[�h = �\�񒍕��P��.get����().�����R�[�h <BR>
     * �s��R�[�h = �\�񒍕��P��.get�s��().�s��R�[�h <BR>
     * �����敪 = �����f�[�^�A�_�v�^.get�����敪(�\�񒍕��P��.getTaxType)  <BR>
     * ����敪 = �\�񒍕��P��.get���b�Z�[�W�p����敪() <BR>
     * �ٍ� = �M�p����ٍσI�u�W�F�N�g(*1) <BR>
     * �A���������ʏ�� = �\�񒍕��P��.create�A���������ʏ��() <BR>
     * �P�������l��� = �p�����[�^.���N�G�X�g�f�[�^.�P�������l���<BR>
     * <BR>
     * (*1)�M�p����ٍσC���X�^���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g�B <BR>
     * �ٍϋ敪 = �\�񒍕��P��.�ٍϋ敪 <BR>
     * �ٍϊ����l = �\�񒍕��P��.�ٍϊ����l <BR>
     * <BR>
     * �S�j �v���p�e�B�Z�b�g�����m�F���N�G�X�g��ԋp����B <BR>
     * 
     * @@param l_request -
     *            (���N�G�X�g�f�[�^) <BR>
     *            ���N�G�X�g�f�[�^�B <BR>
     * @@param l_orderUnit -
     *            (�\�񒍕��P��) <BR>
     *            �����\�񒍕��P��Impl�I�u�W�F�N�g <BR>
     * @@return WEB3SuccMarginOpenConfirmRequest
     * @@throws WEB3BaseException
     * @@roseuid 433BD3BF0267
     */
    protected WEB3SuccMarginOpenConfirmRequest createConfirmRequest(
        WEB3SuccMarginOpenChangeConfirmRequest l_request,
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createConfirmRequest(" +
            " WEB3SuccMarginOpenChangeConfirmRequest, "+
            " WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //�P�j �߂�l�̃C���X�^���X�𐶐�����
        WEB3SuccMarginOpenConfirmRequest l_confirmRequest = new WEB3SuccMarginOpenConfirmRequest();

        //�Q�j ���ʃv���p�e�B�Z�b�g
        this.setMarginCommonRequest(l_confirmRequest, l_request);

        //�R�j ���������C���X�^���X���L�̃v���p�e�B���Z�b�g����
        WEB3MarginRepaymentUnit l_marginRepaymentUnit = new WEB3MarginRepaymentUnit();
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow) l_orderUnit.getDataSourceObject();
        //�ٍϋ敪
        l_marginRepaymentUnit.repaymentDiv = l_rsvEqOrderUnitRow.getRepaymentType();
        //�ٍϊ����l
        l_marginRepaymentUnit.repaymentTimeLimit = String.valueOf(l_rsvEqOrderUnitRow.getRepaymentNum());
        
        Product l_product = l_orderUnit.getProduct();
        
        if (l_product == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        
        //�����R�[�h
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject(); 
        l_confirmRequest.productCode = l_productRow.getProductCode();

        //�s��R�[�h
        try
        {
            l_confirmRequest.marketCode = l_orderUnit.getMarket().getMarketCode();
        }
        catch (NotFoundException l_ne)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ne.getMessage(), 
                l_ne);
        }

        //�����敪
        l_confirmRequest.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());

        //����敪
        l_confirmRequest.tradingType = l_orderUnit.getMsgTradingType();

        //�ٍ�
        l_confirmRequest.repayment = l_marginRepaymentUnit;

        //�A���������ʏ��
        l_confirmRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();

        //�P�������l���
        l_confirmRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        //�S�j �v���p�e�B�Z�b�g�����m�F���N�G�X�g��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_confirmRequest;
    }

    /**
     * (create�������N�G�X�g) <BR>
     * �����̃��N�G�X�g�f�[�^�A�\�񒍕��P�ʂ�� <BR>
     * �i�A���j�M�p����V�K�������������N�G�X�g�I�u�W�F�N�g�� <BR>
     * �쐬����B <BR>
     * <BR>
     * �P�j �߂�l�̃C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j ���ʃv���p�e�B�Z�b�g�B <BR>
     * this.set�M�p������ʃ��N�G�X�g()���R�[������B <BR>
     * <BR>
     * [set�M�p������ʃ��N�G�X�g()�ɃZ�b�g����p�����[�^] <BR>
     * �ioutput�j���ʃ��N�G�X�g�F �P�j�̖߂�l <BR>
     * �iinput�j���ʃ��N�G�X�g�F �p�����[�^.���N�G�X�g�f�[�^ <BR>
     * <BR>
     * �R�j ���������C���X�^���X���L�̃v���p�e�B���Z�b�g����B <BR>
     * ����ID = �\�񒍕��P��.����ID <BR>
     * �����R�[�h = �\�񒍕��P��.get����().�����R�[�h <BR>
     * �s��R�[�h = �\�񒍕��P��.get�s��().�s��R�[�h <BR>
     * �����敪 = �����f�[�^�A�_�v�^.get�����敪(�\�񒍕��P��.getTaxType)  <BR>
     * ����敪 = �\�񒍕��P��.get���b�Z�[�W�p����敪() <BR>
     * �ٍ� = �M�p����ٍσI�u�W�F�N�g(*1) <BR>
     * �A���������ʏ�� = �\�񒍕��P��.create�A���������ʏ��() <BR>
     * �P�������l��� = �p�����[�^.���N�G�X�g�f�[�^.�P�������l���<BR>
     * ������P�� = �p�����[�^.���N�G�X�g�f�[�^.������P��<BR>
     * �m�F���P�� = �p�����[�^.���N�G�X�g�f�[�^.�m�F���P�� <BR>
     * �m�F�������� = �p�����[�^.���N�G�X�g�f�[�^.�m�F�������� <BR>
     * �Ïؔԍ� = �p�����[�^.���N�G�X�g�f�[�^.�Ïؔԍ� <BR>
     * <BR>
     * (*1)�M�p����ٍσC���X�^���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g�B <BR>
     * �ٍϋ敪 = �\�񒍕��P��.�ٍϋ敪 <BR>
     * �ٍϊ����l = �\�񒍕��P��.�ٍϊ����l <BR>
     * <BR>
     * �S�j �v���p�e�B�Z�b�g�����������N�G�X�g��ԋp����B <BR>
     * 
     * @@param l_request -
     *            (���N�G�X�g�f�[�^) <BR>
     *            ���N�G�X�g�f�[�^�B <BR>
     * @@param l_orderUnit -
     *            (�\�񒍕��P��) <BR>
     *            �����\�񒍕��P��Impl�I�u�W�F�N�g <BR>
     * @@return WEB3SuccMarginOpenCompleteRequest
     * @@throws WEB3BaseException
     * @@roseuid 433C91CE0326
     */
    protected WEB3SuccMarginOpenCompleteRequest createCompleteRequest(
        WEB3SuccMarginOpenChangeCompleteRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createCompleteRequest(" +
            " WEB3SuccMarginOpenChangeCompleteRequest, "+
            " WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //�P�j �߂�l�̃C���X�^���X�𐶐�����
        WEB3SuccMarginOpenCompleteRequest l_completeRequest = new WEB3SuccMarginOpenCompleteRequest();

        //�Q�j ���ʃv���p�e�B�Z�b�g
        this.setMarginCommonRequest(l_completeRequest, l_request);

        //���������C���X�^���X���L�̃v���p�e�B���Z�b�g����
        WEB3MarginRepaymentUnit l_marginRepaymentUnit = new WEB3MarginRepaymentUnit();
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow) l_orderUnit.getDataSourceObject();

        //�ٍϋ敪
        l_marginRepaymentUnit.repaymentDiv = l_rsvEqOrderUnitRow.getRepaymentType();

        //�ٍϊ����l
        l_marginRepaymentUnit.repaymentTimeLimit = String.valueOf(l_rsvEqOrderUnitRow.getRepaymentNum());

        //����ID
        l_completeRequest.orderId = String.valueOf(l_orderUnit.getOrderId());
        
        Product l_product = l_orderUnit.getProduct();

        if (l_product == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        
        //�����R�[�h
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject(); 
        l_completeRequest.productCode = l_productRow.getProductCode();

        //�s��R�[�h
        try
        {
            l_completeRequest.marketCode = l_orderUnit.getMarket().getMarketCode();
        }
        catch (NotFoundException l_ne)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ne.getMessage(), 
                l_ne);
        }

        //�����敪
        l_completeRequest.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());

        //����敪
        l_completeRequest.tradingType = l_orderUnit.getMsgTradingType();

        //�ٍ�
        l_completeRequest.repayment = l_marginRepaymentUnit;

        //�A���������ʏ��
        l_completeRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();

        //�P�������l���
        l_completeRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        //������P��
        l_completeRequest.afterAdjustmentPrice = l_request.afterAdjustmentPrice;

        //�m�F���P��
        l_completeRequest.checkPrice = l_request.checkPrice;

        //�m�F��������
        l_completeRequest.checkDate = l_request.checkDate;

        //�Ïؔԍ�
        l_completeRequest.password = l_request.password;

        log.exiting(STR_METHOD_NAME);
        return l_completeRequest;
    }

    /**
     * (set�M�p������ʃ��N�G�X�g) <BR>
     * �w�肳�ꂽ�u�ioutput�j���ʃ��N�G�X�g�v�̃C���X�^���X�ɁA�v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �ȉ��̃v���p�e�B�ɁA�u�iinput�j���ʃ��N�G�X�g�v�̓����v���p�e�B�̒l���Z�b�g����B <BR>
     * <BR>
     * [�Ώۃv���p�e�B] <BR>
     * �������� <BR>
     * �����P���敪 <BR>
     * �����P�� <BR>
     * �l�i���� <BR>
     * ���s���� <BR>
     * ���������敪 <BR>
     * �����L������ <BR>
     * ���������敪 <BR>
     * �t�w�l�p���������P�� <BR>
     * �t�w�l�p�����������Z�q <BR>
     * W�w�l�p���������P�� <BR>
     * W�w�l�p�����������Z�q <BR>
     * W�w�l�p�����P���敪 <BR>
     * W�w�l�p�����P�� <BR>
     * 
     * @@param l_outputCommonRequest -
     *            (�ioutput�j���ʃ��N�G�X�g) <BR>
     *            �M�p������ʃ��N�G�X�g�I�u�W�F�N�g <BR>
     * @@param l_inputCommonRequest -
     *            (�iinput�j���ʃ��N�G�X�g) <BR>
     *            �M�p������ʃ��N�G�X�g�I�u�W�F�N�g <BR>
     * @@return WEB3MarginCommonRequest
     * @@roseuid 433BD8530333
     */
    protected WEB3MarginCommonRequest setMarginCommonRequest(WEB3MarginCommonRequest l_outputCommonRequest,
        WEB3MarginCommonRequest l_inputCommonRequest)
    {
        final String STR_METHOD_NAME = " setMarginCommonRequest(WEB3MarginCommonRequest, WEB3MarginCommonRequest)";
        log.entering(STR_METHOD_NAME);

        //��������
        l_outputCommonRequest.orderQuantity = l_inputCommonRequest.orderQuantity;

        //�����P���敪
        l_outputCommonRequest.orderPriceDiv = l_inputCommonRequest.orderPriceDiv;

        //�����P��
        l_outputCommonRequest.limitPrice = l_inputCommonRequest.limitPrice;

        //�l�i����
        l_outputCommonRequest.priceCondType = l_inputCommonRequest.priceCondType;

        //���s����
        l_outputCommonRequest.execCondType = l_inputCommonRequest.execCondType;

        //���������敪
        l_outputCommonRequest.expirationDateType = l_inputCommonRequest.expirationDateType;

        //�����L������
        l_outputCommonRequest.expirationDate = l_inputCommonRequest.expirationDate;

        //���������敪
        l_outputCommonRequest.orderCondType = l_inputCommonRequest.orderCondType;

        //�t�w�l�p���������P��
        l_outputCommonRequest.stopOrderCondPrice = l_inputCommonRequest.stopOrderCondPrice;

        //�t�w�l�p�����������Z�q
        l_outputCommonRequest.stopOrderCondOperator = l_inputCommonRequest.stopOrderCondOperator;

        //W�w�l�p���������P��
        l_outputCommonRequest.wlimitOrderCondPrice = l_inputCommonRequest.wlimitOrderCondPrice;

        //W�w�l�p�����������Z�q
        l_outputCommonRequest.wlimitOrderCondOperator = l_inputCommonRequest.wlimitOrderCondOperator;

        //W�w�l�p�����P���敪
        l_outputCommonRequest.wLimitOrderPriceDiv = l_inputCommonRequest.wLimitOrderPriceDiv;

        //W�w�l�p�����P��
        l_outputCommonRequest.wLimitPrice = l_inputCommonRequest.wLimitPrice;

        log.exiting(STR_METHOD_NAME);
        return l_outputCommonRequest;
    }

    /**
     * (notify�\�񒍕��o�^) <BR>
     * �\�񒍕��̓o�^�����[���G���W���T�[�o�ɒʒm����B <BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * ����������return����B�i�J�������j <BR>
     * 
     * @@param l_lngSubOrderId -
     *            (�q�����̒���ID) <BR>
     *            �q�����̒���ID�B <BR>
     * @@roseuid 435EE19103E2
     */
    protected void notifyRsvOrderRegister(long l_lngSubOrderId)
    {
        final String STR_METHOD_NAME = " notifyRsvOrderRegister(long)";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return;
    }
}@
