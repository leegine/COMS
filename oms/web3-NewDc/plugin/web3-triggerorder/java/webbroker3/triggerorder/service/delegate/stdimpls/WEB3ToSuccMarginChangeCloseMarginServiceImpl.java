head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeCloseMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p��������ԍσT�[�r�XImpl(WEB3ToSuccMarginChangeCloseMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17 杊��](���u) �V�K�쐬
Revesion History : 2006/10/30 ������(���u)�@@���f��No.161
Revesion History : 2007/01/11 ꎉ�  (���u) �d�l�ύX���f��216
Revesion History : 2007/01/19 �юu��(���u) ���f��225
Revesion History : 2007/08/20 ���g(���u) ���f��242
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginCommonRequest;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginRequestAdapter;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccMarginChangeSettleContractOrderSpec;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeCloseMarginService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCloseMarginRequestAdapter;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�M�p��������ԍσT�[�r�XImpl)<BR>
 * �i�A���j�M�p��������ԍσT�[�r�X�����N���X<BR>
 *   
 * @@author 杊��]
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeCloseMarginServiceImpl 
    extends WEB3ToSuccMarginCloseMarginServiceImpl 
    implements WEB3ToSuccMarginChangeCloseMarginService 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeCloseMarginServiceImpl.class);

    /**
     * @@roseuid 436ACF7C01C5
     */
    public WEB3ToSuccMarginChangeCloseMarginServiceImpl() 
    {
     
    }
    
    /**
     * �i�A���j�M�p��������ԍσT�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * validate������submit�������Ăѕ�����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433CFD070141
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
                getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccMarginCloseChangeConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccMarginCloseChangeConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3SuccMarginCloseChangeCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccMarginCloseChangeCompleteRequest) l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * �M�p����̒����ԍϔ����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�M�p��������ԍσT�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p������������ԍϊm�F���N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@return WEB3SuccMarginCloseChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 433D001803C2
     */
    protected WEB3SuccMarginCloseChangeConfirmResponse validateOrder(WEB3SuccMarginCloseChangeConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3SuccMarginCloseChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 get�����\�񒍕��P��(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            long l_lngOrderId = Long.parseLong(l_request.id);
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.3 validate���N�G�X�g�f�[�^at���Ύ��(WEB3GenRequest, �����\�񒍕��P��Impl)
        this.validateRequestDataAtReversingTrade(l_request, l_orderUnit);
        
        //1.4 validate�����\���( )
        l_orderUnit.validateOrderForChangeability();
        
        //1.5 validate���ύό���(�����\�񒍕��P��Impl)
        this.validateClosedMarginContract(l_orderUnit);
        
        //1.6 create�m�F���N�G�X�g(�i�A���j�M�p������������ԍϊm�F���N�G�X�g, �����\�񒍕��P��Impl)
        WEB3SuccMarginCloseConfirmRequest l_confirmRequest = this.createConfirmRequest(l_request, l_orderUnit);
        
        //1.7 validate����(�i�A���j�M�p����ԍϒ����m�F���N�G�X�g)
        WEB3SuccMarginCloseConfirmResponse l_confirmResponse = super.validateOrder(l_confirmRequest);

        //1.8 createResponse( )
        WEB3SuccMarginCloseChangeConfirmResponse l_response =
            (WEB3SuccMarginCloseChangeConfirmResponse) l_request.createResponse();
        
        //1.9 (*)�v���p�e�B�Z�b�g
        //�i*�j���X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B
        // �m�F���������F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.checkDate = l_confirmResponse.checkDate;
        
        //�T�Z��n����F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.estimatedPrice = l_confirmResponse.estimatedPrice;
        
        //����I���x���s��R�[�h�ꗗ�F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.messageSuspension = l_confirmResponse.messageSuspension; 
        
        //�������׈ꗗ�F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.contractUnits = l_confirmResponse.contractUnits;
        
        //�萔�����F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.commissionInfo = l_confirmResponse.commissionInfo;
        
        //�m�F���P���F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.checkPrice = l_confirmResponse.checkPrice;
        
        //�C���T�C�_�[�x���\���t���O�F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
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
     * (submit����)<BR>
     * �M�p����̒����ԍϒ�����o�^����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�M�p��������ԍσT�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p������������ԍϊ������N�G�X�g�I�u�W�F�N�g
     * @@return WEB3SuccMarginCloseChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 433D001803E1
     */
    protected WEB3SuccMarginCloseChangeCompleteResponse submitOrder(WEB3SuccMarginCloseChangeCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3SuccMarginCloseChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 get�����\�񒍕��P��(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            long l_lngOrderId = Long.parseLong(l_request.id);
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.3 validate���N�G�X�g�f�[�^at���Ύ��(WEB3GenRequest, �����\�񒍕��P��Impl)
        this.validateRequestDataAtReversingTrade(l_request, l_orderUnit);

        //1.4 validate�����\���( )
        l_orderUnit.validateOrderForChangeability();

        //1.5 validate���ύό���(�����\�񒍕��P��Impl)
        this.validateClosedMarginContract(l_orderUnit);

        //1.6 create�������N�G�X�g(�i�A���j�M�p������������ԍϊ������N�G�X�g, �����\�񒍕��P��Impl)
        WEB3SuccMarginCloseCompleteRequest l_completeRequest = this.createCompleteRequest(l_request, l_orderUnit);
        
        //1.7 submit����(�i�A���j�M�p����ԍϒ����������N�G�X�g)
        WEB3SuccMarginCloseCompleteResponse l_completeResponse = super.submitOrder(l_completeRequest);

        //1.8 create���N�G�X�g�A�_�v�^(WEB3GenRequest)
        WEB3MarginCloseMarginRequestAdapter l_adapter = this.createRequestAdapter(l_completeRequest);
        
        //1.9 create���ό����G���g��(�M�p������ό�������[], �M�p����ԍσ��N�G�X�g�A�_�v�^)
        EqTypeSettleContractOrderEntry[] l_contractOrderEntrys = 
            this.createClosingContractEntry(l_request.closeMarginContractUnits, l_adapter);
        
        //1.10 create�����\��ԍϒ����������e(����ID : long, ���ό����G���g�� : EqTypeSettleContractOrderEntry[], 
        //     �����㊔�� : double, ������w�l : double, ������T�Z��n��� : double, ������v�Z�P�� : double, 
        //     �����㒍�������� : Date, ������is�o����܂Œ��� : boolean, �㗝���͎� : ����, ������P�������l : Double)
        double l_dbModifiedOrderQuantity = 0.0D;
        double l_dblModifiedCalcUnitPrice = 0.0D;
        Date l_datModifiedExpirationDate = l_request.checkDate;
        boolean l_blnModifiedIsCarriedOrder = false;
        Double l_dblModifiedPriceAdjustValue = null;

        if (l_request.orderQuantity != null)
        {
            //���N�G�X�g�f�[�^�D�������� != null�̏ꍇ�A�����㊔���F���N�G�X�g�f�[�^�D��������
            l_dbModifiedOrderQuantity = Double.parseDouble(l_request.orderQuantity);
        }
        else
        {
            //�ȊO�A�����㊔���Fcreate���ό����G���g��()�̖߂�l�D������SUM�l
            for (int i = 0; i < l_contractOrderEntrys.length; i++)
            {
                l_dbModifiedOrderQuantity += l_contractOrderEntrys[i].getQuantity();
            }
        }

        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_dblModifiedCalcUnitPrice = 0;
        }
        else
        {
            l_dblModifiedCalcUnitPrice = Double.parseDouble(l_request.limitPrice);
        }
        
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
        {
            l_datModifiedExpirationDate = l_request.expirationDate;
            l_blnModifiedIsCarriedOrder = true;
        }
        
        if (null != l_request.priceAdjustmentValueInfo)
        {
            l_dblModifiedPriceAdjustValue = new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }
        
        WEB3ToSuccMarginChangeSettleContractOrderSpec l_orderSpec = 
            WEB3ToSuccMarginChangeSettleContractOrderSpec.createMarginChangeSettleContractOrderSpec(
                l_orderUnit.getOrderId(),
                l_contractOrderEntrys,
                l_dbModifiedOrderQuantity,
                l_dblModifiedCalcUnitPrice,
                Double.parseDouble(l_request.estimatedPrice),
                Double.parseDouble(l_request.checkPrice),
                l_datModifiedExpirationDate,
                l_blnModifiedIsCarriedOrder,
                (WEB3GentradeTrader) this.getTrader(),
                l_dblModifiedPriceAdjustValue);
        
        //1.11 submit���������\��ԍϒ���(�⏕���� : SubAccount, �\��ԍϒ����������e : �����\��ԍϒ����������e, 
        //     ����p�X���[�h : String, �����O�\�񒍕��P�� : �����\�񒍕��P��Impl)
        l_toOrderManager.submitEqtypeChangeSettleContractOrder(
            this.getSubAccount(),
            l_orderSpec,
            l_request.password,
            l_orderUnit);
        
        //1.12 createResponse( )
        WEB3SuccMarginCloseChangeCompleteResponse l_response = 
            (WEB3SuccMarginCloseChangeCompleteResponse) l_request.createResponse();
        
        //1.13 (*)�v���p�e�B�Z�b�g
        //�i*�j���X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B
        //  �X�V���ԁF�@@super�N���X��submit����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.lastUpdatedTimestamp = l_completeResponse.lastUpdatedTimestamp;
        
        // ���ʔԍ��F�@@super�N���X��submit����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.orderActionId = l_completeResponse.orderActionId;
        
        // �C���T�C�_�[�x���\���t���O�F�@@super�N���X��submit����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.insiderWarningFlag = l_completeResponse.insiderWarningFlag;
        
        // �A�������ݒ�t���O�F�@@false�i�Œ�j
        l_response.succSettingFlag = false;

        //�����L�������F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
        l_response.expirationDate = l_completeResponse.expirationDate;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create���ό����G���g��)<BR>
     * ���ό����G���g�����쐬����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@���Δ����̏ꍇ<BR>
     * �@@�i�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true�j<BR>
     * �@@�A�������}�l�[�W��Impl.create���ό����G���g��()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[create���ό����G���g��()�Ɏw�肷�����]<BR>
     * �@@�@@���ό������׈ꗗ�F�@@�����������ό������׈ꗗ(*1)<BR>
     * <BR>
     * �@@(*1)�ȉ��̃v���p�e�B���Z�b�g�����M�p������ό������׃C���X�^���X�݂̂�<BR>
     * �@@�@@�v�f�Ƃ���z��<BR>
     * <BR>
     * �@@�@@�@@�M�p������ό�������.���������F�@@this.get��������()�̖߂�l��ݒ肷��B<BR>
     * <BR>
     * �@@�@@�@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@�@@���N�G�X�g�A�_�v�^�F�@@�p�����[�^.���N�G�X�g�A�_�v�^ <BR>
     * <BR>
     * �Q�j�@@�P�j�ȊO�̏ꍇ�A<BR>
     * �@@�g�����������}�l�[�W��.create���ό����G���g��()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[.create���ό����G���g��()�Ɏw�肷�����]<BR>
     * �@@�@@�����P��ID�F�@@-1�i�\�񒍕��������̏�Ԃ�\���B�j<BR>
     * �@@�@@���������F�@@�p�����[�^.���N�G�X�g�A�_�v�^.get��������()�߂�l<BR>
     * �@@�@@���ό������׈ꗗ[]�F�@@�p�����[�^.���ό������׈ꗗ[]<BR>
     * @@param l_closeMarginContractUnits - (���ό������׈ꗗ)<BR>
     * �M�p������ό����I�u�W�F�N�g�̔z��B<BR>
     * �i���N�G�X�g�f�[�^�j<BR>
     * @@param l_requestAdaptor - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g<BR>
     * @@return EqTypeSettleContractOrderEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 433D071F0086
     */
    protected EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits, 
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createClosingContractEntry(WEB3MarginCloseMarginContractUnit[], " +
            "WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntries = null;
                                       
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdaptor = 
            (WEB3ToSuccMarginCloseMarginRequestAdapter) l_requestAdaptor;

        //�P�j�@@���Δ����̏ꍇ
        if (l_toRequestAdaptor.isReversingOrder())
        {
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                   (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();

            //(*1)�ȉ��̃v���p�e�B���Z�b�g�����M�p������ό������׃C���X�^���X�݂̂�v�f�Ƃ���z��
            WEB3MarginCloseMarginContractUnit l_contractUnit = new WEB3MarginCloseMarginContractUnit();
            l_contractUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(getOrderQuantityCnt(l_toRequestAdaptor));

            //�A�������}�l�[�W��Impl.create���ό����G���g��()���R�[�����A�߂�l��ԋp����B
            l_settleContractOrderEntries = l_toOrderManager.createClosingContractEntry(
                new WEB3MarginCloseMarginContractUnit[]{l_contractUnit});
        }
        //�Q�j�@@�P�j�ȊO�̏ꍇ�A�g�����������}�l�[�W��.create���ό����G���g��()���R�[�����A�߂�l��ԋp����B
        else
        {
            //�g�����������}�l�[�W��
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

            l_settleContractOrderEntries = l_orderManager.createClosingContractEntry(
                -1, 
                l_toRequestAdaptor.getOrderQuantity(),
                l_closeMarginContractUnits);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntries;
    }
    
    /**
     * (submit�ԍϒ���)<BR>
     * �\�񒍕���o�^����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_orderSpec - (�M�p�ԍϒ������e)<BR>
     * �M�p�ԍϒ������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * �\�񒍕��̒���ID�B
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_commission - (�萔��)<BR>
     * �萔���I�u�W�F�N�g�B<BR>
     * @@param l_profitAndLossCalcResult - (�v�Z����)<BR>
     * �T�Z���ϑ��v����v�Z���ʃI�u�W�F�N�g�B<BR>
     * @@param l_requestAdaptor - (�ԍσ��N�G�X�g�A�_�v�^)<BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g<BR>
     * @@roseuid 434254C1038D
     */
    protected void submitSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3MarginSettleContractOrderSpec l_orderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        WEB3GentradeCommission l_commission, 
        WEB3EquityRealizedProfitAndLossPrice l_profitAndLossCalcResult, 
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor) 
    {
        
    }
    
    /**
     * (validate�A�������ő�ݒ萔)<BR>
     * �A�������̍ő�ݒ萔�𒴉߂��Ă��܂�Ȃ����ǂ������`�F�b�N����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@roseuid 433D0198023B
     */
    protected void validateSuccOrderMaxQuantity(EqTypeOrderUnit l_parentOrderUnit) 
    {
     
    }
    
    /**
     * (create�m�F���N�G�X�g)<BR>
     * �����̃��N�G�X�g�f�[�^�A�\�񒍕��P�ʂ��<BR>
     * �i�A���j�M�p����ԍϒ����m�F���N�G�X�g�I�u�W�F�N�g��<BR>
     * �쐬����B<BR>
     * <BR>
     * �P�j�@@�߂�l�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���ʃv���p�e�B�Z�b�g�B<BR>
     * �@@this.set�M�p������ʃ��N�G�X�g()���R�[������B<BR>
     * <BR>
     * �@@[set�M�p������ʃ��N�G�X�g()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�ioutput�j���ʃ��N�G�X�g�F�@@�P�j�̖߂�l<BR>
     * �@@�@@�iinput�j���ʃ��N�G�X�g�F�@@�p�����[�^.���N�G�X�g�f�[�^<BR>
     * <BR>
     * �R�j�@@���������C���X�^���X���L�̃v���p�e�B���Z�b�g����B<BR>
     * �@@���Ϗ����敪 = �\�񒍕��P��.���Ϗ����敪<BR>
     * �@@���ό����ꗗ = �p�����[�^.���N�G�X�g�f�[�^.���ό����ꗗ<BR>
     * �@@�v�����敪 = null<BR>
     * �@@�A���������ʏ�� = �\�񒍕��P��.create�A���������ʏ��()<BR>
     * �@@�P�������l��� = �p�����[�^.���N�G�X�g�f�[�^.�P�������l���<BR>
     * <BR>
     * �S�j�@@�v���p�e�B�Z�b�g�����m�F���N�G�X�g��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@param l_orderUnit - (�\�񒍕��P��)<BR>
     * �����\�񒍕��P��Impl�I�u�W�F�N�g<BR>
     * @@return WEB3SuccMarginCloseConfirmRequest
     * @@roseuid 433D019801AF
     */
    protected WEB3SuccMarginCloseConfirmRequest createConfirmRequest(
        WEB3SuccMarginCloseChangeConfirmRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) 
    {
        final String STR_METHOD_NAME = " createConfirmRequest(" +
            "WEB3SuccMarginCloseChangeConfirmRequest" +
            "WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�߂�l�̃C���X�^���X�𐶐�����B
        WEB3SuccMarginCloseConfirmRequest l_confirmRequest = new WEB3SuccMarginCloseConfirmRequest();
        
        //�Q�j�@@���ʃv���p�e�B�Z�b�g�B
        this.setMarginCommonRequest(l_confirmRequest, l_request);
        
        //�R�j�@@���������C���X�^���X���L�̃v���p�e�B���Z�b�g����B
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //���Ϗ����敪 = �\�񒍕��P��.���Ϗ����敪
        l_confirmRequest.closingOrder = l_rsvEqOrderUnitRow.getClosingOrderType();
        
        //���ό����ꗗ = �p�����[�^.���N�G�X�g�f�[�^.���ό����ꗗ
        l_confirmRequest.closeMarginContractUnits = l_request.closeMarginContractUnits;
        
        //�v�����敪 = null
        l_confirmRequest.requestFromType = null;
        
        //�A���������ʏ�� = �\�񒍕��P��.create�A���������ʏ��()
        l_confirmRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();
        
        //�P�������l��� = �p�����[�^.���N�G�X�g�f�[�^.�P�������l���
        l_confirmRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_confirmRequest;
    }
    
    /**
     * (create�������N�G�X�g)<BR>
     * �����̃��N�G�X�g�f�[�^�A�\�񒍕��P�ʂ��<BR>
     * �i�A���j�M�p����ԍϒ����������N�G�X�g�I�u�W�F�N�g��<BR>
     * �쐬����B<BR>
     * <BR>
     * �P�j�@@�߂�l�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���ʃv���p�e�B�Z�b�g�B<BR>
     * �@@this.set�M�p������ʃ��N�G�X�g()���R�[������B<BR>
     * <BR>
     * �@@[set�M�p������ʃ��N�G�X�g()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�ioutput�j���ʃ��N�G�X�g�F�@@�P�j�̖߂�l<BR>
     * �@@�@@�iinput�j���ʃ��N�G�X�g�F�@@�p�����[�^.���N�G�X�g�f�[�^<BR>
     * <BR>
     * �R�j�@@���������C���X�^���X���L�̃v���p�e�B���Z�b�g����B<BR>
     * �@@����ID = �\�񒍕��P��.����ID<BR>
     * �@@���Ϗ����敪 = �\�񒍕��P��.���Ϗ����敪<BR>
     * �@@���ό����ꗗ = �p�����[�^.���N�G�X�g�f�[�^.���ό����ꗗ<BR>
     * �@@�m�F���P�� = �p�����[�^.���N�G�X�g�f�[�^.�m�F���P��<BR>
     * �@@�m�F�������� = �p�����[�^.���N�G�X�g�f�[�^.�m�F��������<BR>
     * �@@�Ïؔԍ� = �p�����[�^.���N�G�X�g�f�[�^.�Ïؔԍ�<BR>
     * �@@�A���������ʏ�� = �\�񒍕��P��.create�A���������ʏ��()<BR>
     * �@@�P�������l��� = �p�����[�^.���N�G�X�g�f�[�^.�P�������l���<BR>
     * �@@������P�� = �p�����[�^.���N�G�X�g�f�[�^.������P��<BR>
     * <BR>
     * �S�j�@@�v���p�e�B�Z�b�g�����������N�G�X�g��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@param l_orderUnit - (�\�񒍕��P��)<BR>
     * �����\�񒍕��P��Impl�I�u�W�F�N�g<BR>
     * @@return WEB3SuccMarginCloseCompleteRequest
     * @@roseuid 433D019801CE
     */
    protected WEB3SuccMarginCloseCompleteRequest createCompleteRequest(
        WEB3SuccMarginCloseChangeCompleteRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) 
    {
        final String STR_METHOD_NAME = " createCompleteRequest(" +
            "WEB3SuccMarginCloseChangeCompleteRequest, " +
            "WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�߂�l�̃C���X�^���X�𐶐�����B
        WEB3SuccMarginCloseCompleteRequest l_completeRequest = new WEB3SuccMarginCloseCompleteRequest();
        
        //�Q�j�@@���ʃv���p�e�B�Z�b�g�B
        this.setMarginCommonRequest(l_completeRequest, l_request);
        
        //�R�j�@@���������C���X�^���X���L�̃v���p�e�B���Z�b�g����B
        //����ID = �\�񒍕��P��.����ID
        l_completeRequest.orderId = String.valueOf(l_orderUnit.getOrderId());

        //���Ϗ����敪 = �\�񒍕��P��.���Ϗ����敪
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow) l_orderUnit.getDataSourceObject();
        l_completeRequest.closingOrder = l_rsvEqOrderUnitRow.getClosingOrderType();
        
        //���ό����ꗗ = �p�����[�^.���N�G�X�g�f�[�^.���ό����ꗗ
        l_completeRequest.closeMarginContractUnits = l_request.closeMarginContractUnits;
        
        //�m�F���P�� = �p�����[�^.���N�G�X�g�f�[�^.�m�F���P��
        l_completeRequest.checkPrice = l_request.checkPrice;
        
        //�m�F�������� = �p�����[�^.���N�G�X�g�f�[�^.�m�F��������
        l_completeRequest.checkDate = l_request.checkDate;
        
        //�Ïؔԍ� = �p�����[�^.���N�G�X�g�f�[�^.�Ïؔԍ�
        l_completeRequest.password = l_request.password;
        
        //�A���������ʏ�� = �\�񒍕��P��.create�A���������ʏ��()
        l_completeRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();
        
        //�P�������l��� = �p�����[�^.���N�G�X�g�f�[�^.�P�������l���
        l_completeRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        //������P�� = �p�����[�^.���N�G�X�g�f�[�^.������P��
        l_completeRequest.afterAdjustmentPrice = l_request.afterAdjustmentPrice;

        log.exiting(STR_METHOD_NAME);
        return l_completeRequest;
    }
    
    /**
     * (set�M�p������ʃ��N�G�X�g)<BR>
     * �w�肳�ꂽ�u�ioutput�j���ʃ��N�G�X�g�v�̃C���X�^���X�ɁA�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �ȉ��̃v���p�e�B�ɁA�u�iinput�j���ʃ��N�G�X�g�v�̓����v���p�e�B�̒l���Z�b�g����B<BR>
     * <BR>
     * [�Ώۃv���p�e�B]<BR>
     * �@@��������<BR>
     * �@@�����P���敪<BR>
     * �@@�����P��<BR>
     * �@@�l�i����<BR>
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
     * @@param l_outputCommonRequest - (�ioutput�j���ʃ��N�G�X�g)<BR>
     * �M�p������ʃ��N�G�X�g�I�u�W�F�N�g<BR>
     * @@param l_inputCommonRequest - (�iinput�j���ʃ��N�G�X�g)<BR>
     * �M�p������ʃ��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3MarginCommonRequest
     * @@roseuid 433D019801ED
     */
    protected WEB3MarginCommonRequest setMarginCommonRequest(
        WEB3MarginCommonRequest l_outputCommonRequest, 
        WEB3MarginCommonRequest l_inputCommonRequest) 
    {
        final String STR_METHOD_NAME = " setMarginCommonRequest(WEB3MarginCommonRequest, WEB3MarginCommonRequest)";
        log.entering(STR_METHOD_NAME);
        
        //�w�肳�ꂽ�u�ioutput�j���ʃ��N�G�X�g�v�̃C���X�^���X�ɁA�v���p�e�B���Z�b�g����B
        //�ȉ��̃v���p�e�B�ɁA�u�iinput�j���ʃ��N�G�X�g�v�̓����v���p�e�B�̒l���Z�b�g����B
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
     * (validate���ύό���)<BR>
     * �\�񌈍ϒ����̑ΏۂƂȂ��Ă��錚����<BR>
     * �ʒ����ɂ�茈�ύςƂȂ��Ă��邩�ǂ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�A�������}�l�[�W��Impl.create��������ByOrder()<BR>
     * �@@���R�[������B<BR>
     * <BR>
     * �@@[create��������ByOrder()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�\�񒍕��P�ʁF�@@�p�����[�^.�\�񒍕��P��<BR>
     * <BR>
     * �@@null���ԋp���ꂽ�ꍇ�A<BR>
     * �@@�u�\�񌈍ϑΏی����͕ʒ����ɂ�茈�ύρv<BR>
     * �@@�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_02289<BR>
     * @@param l_rsvEqOrderUnit - (�\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 434E227D0113
     */
    protected void validateClosedMarginContract(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateClosedMarginContract(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�A�������}�l�[�W��Impl.create��������ByOrder()���R�[������B
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3MarginContractUnit[] l_contractUnits = l_toOrderManager.createContractUnitByOrder(l_rsvEqOrderUnit);
        
        //null���ԋp���ꂽ�ꍇ�A�u�\�񌈍ϑΏی����͕ʒ����ɂ�茈�ύρv�̗�O���X���[����B
        if (l_contractUnits == null)
        {
            log.debug("�\�񌈍ϑΏی����͕ʒ����ɂ�茈�ύςł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02289,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�\�񌈍ϑΏی����͕ʒ����ɂ�茈�ύςł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notify�\�񒍕��o�^)<BR>
     * �\�񒍕��̓o�^�����[���G���W���T�[�o�ɒʒm����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_lngSubOrderId - (�q�����̒���ID)<BR>
     * �q�����̒���ID�B<BR>
     * @@roseuid 435EE218023C
     */
    protected void notifyRsvOrderRegister(long l_lngSubOrderId) 
    {
        
    }
    
    /**
     * (validate���N�G�X�g�f�[�^at���Ύ��)<BR>
     * ���Ύ���w�莞�ɌŗL�́A���N�G�X�g�f�[�^�̃v���p�e�B�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�\�񒍕��P��.is���Δ������()==false<BR>
     * �@@�@@�@@�i�����Ύ���łȂ��j�̏ꍇ�́A<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^.validateAT�����c���()���R�[�����A<BR>
     * �@@�@@�@@return����B<BR>
     * <BR>
     * �Q�j�@@���N�G�X�g�f�[�^.validateAT���Ύ��()���R�[������B<BR>
     * <BR>
     * �R�j�@@�p�����[�^.�\�񒍕��P��.���Ϗ����敪 != nul�@@����<BR>
     * �@@�p�����[�^.�\�񒍕��P��.���Ϗ����敪 == "�����_��"��<BR>
     * �@@�ꍇ�A�ȉ��̏��������{����B<BR>
     * �@@�R�|�P�j�@@���N�G�X�g�f�[�^.���ό����ꗗ�̗v�f�����A<BR>
     * �@@�@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�R�|�P�|�P�j�@@���ό�������.�������� ���ȉ��̂����ꂩ�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u���ϖ��ׂ̒��������w�肪�s���v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Enull <BR>
     * �@@�@@�@@�@@�@@�@@�E�����ȊO <BR>
     * �@@�@@�@@�@@�@@�@@�E�O�ȉ��̐��� <BR>
     * �@@�@@�@@�@@�@@�@@�E�W���𒴂��鐔�� <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02285<BR>
     * <BR>
     * �@@�@@�������̃��N�G�X�g�f�[�^�́A�ȉ��̂����ꂩ�ɃL���X�g���邱�ƁB<BR>
     * �@@�@@�@@�E�i�A���j�M�p������������ԍϊm�F���N�G�X�g<BR>
     * �@@�@@�@@�E�i�A���j�M�p������������ԍϊ������N�G�X�g<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@param l_rsvEqOrderUnit - (�\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4361EDE40319
     */
    protected void validateRequestDataAtReversingTrade(
        WEB3GenRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRequestDataAtReversingTrade(" +
            "WEB3GenRequest, WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null || l_rsvEqOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        
        WEB3SuccMarginCloseChangeConfirmRequest l_confirmRequest = null;
        WEB3SuccMarginCloseChangeCompleteRequest l_completeRequest = null;
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits = null;

        if (l_request instanceof WEB3SuccMarginCloseChangeConfirmRequest)
        {
            //�����̃��N�G�X�g�f�[�^��(�A��)�M�p������������ԍϊm�F���N�G�X�g�ɃL���X�g
            l_confirmRequest = (WEB3SuccMarginCloseChangeConfirmRequest) l_request;
            l_closeMarginContractUnits = l_confirmRequest.closeMarginContractUnits;
        }
        else if (l_request instanceof WEB3SuccMarginCloseChangeCompleteRequest)
        {
            //�����̃��N�G�X�g�f�[�^��(�A��)�M�p������������ԍϊ������N�G�X�g�ɃL���X�g
           l_completeRequest = (WEB3SuccMarginCloseChangeCompleteRequest) l_request;
           l_closeMarginContractUnits = l_completeRequest.closeMarginContractUnits;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        //�P�j�@@�p�����[�^.�\�񒍕��P��.is���Δ������()==false�i�����Ύ���łȂ��j�̏ꍇ�́A
        //���N�G�X�g�f�[�^.validateAT�����c���()���R�[�����Areturn����B
        if (!l_rsvEqOrderUnit.isReversingTrade())
        {
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
        
        //�Q�j�@@���N�G�X�g�f�[�^.validateAT���Ύ��()���R�[������B
        if (l_confirmRequest != null)
        {
            l_confirmRequest.validateAtReverseOrder();
        }
        else if (l_completeRequest != null)
        {
            l_completeRequest.validateAtReverseOrder();
        }
        
        //�R�j�@@�p�����[�^.�\�񒍕��P��.���Ϗ����敪 != null�@@���p�����[�^.�\�񒍕��P��.���Ϗ����敪 == "�����_��"��
        //      �ꍇ�A�ȉ��̏��������{����B
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow) l_rsvEqOrderUnit.getDataSourceObject();
        if (null != l_rsvEqOrderUnitRow.getClosingOrderType() && 
            WEB3ClosingOrderDef.RANDOM.equals(l_rsvEqOrderUnitRow.getClosingOrderType()))
        {
            //�R�|�P�j�@@���N�G�X�g�f�[�^.���ό����ꗗ�̗v�f�����A�ȉ��̏������J��Ԃ��B
            int l_intLength = l_closeMarginContractUnits.length;
            for (int i = 0; i < l_intLength; i++)
            {
                //�R�|�P�|�P�j�@@���ό�������.�������� ���ȉ��̂����ꂩ�̏ꍇ�́A
                //             �u���ϖ��ׂ̒��������w�肪�s���v�̗�O���X���[����B
                if (null == l_closeMarginContractUnits[i].orderQuantity ||
                    !WEB3StringTypeUtility.isInteger(l_closeMarginContractUnits[i].orderQuantity) ||
                    Long.parseLong(l_closeMarginContractUnits[i].orderQuantity) <= 0 ||
                    l_closeMarginContractUnits[i].orderQuantity.length() > 8)
                {
                    log.debug("���ϖ��ׂ̒��������w�肪�s���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02285,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "���ϖ��ׂ̒��������w�肪�s���B");
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * get��������<BR>
     * <BR>
     * �M�p����������ׂɐݒ肷�钍��������ԋp����B <BR>
     * <BR>
     * �P�j�@@�������������߂�B<BR>
     * �@@�@@�擾�������Ϗ����敪��"�����_��"�̏ꍇ�́A <BR>
     * �@@�@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�������� ���g�p�B <BR>
     * �@@�@@�擾�������Ϗ����敪=="�����_��"�̏ꍇ�́A <BR>
     * �@@�@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���ό����ꗗ�̑S�v�f�̒���������SUM�l���g�p�B<BR> 
     * <BR>
     * �Q�j�@@�P�j�ŋ��߂��������� > ���N�G�X�g�A�_�v�^.�e�����̒����P��.�������ʂ̏ꍇ�A <BR>
     * �@@�@@�u�����������e�����̒������ʂ𒴉߁v�̗�O���X���[����B <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_02290<BR>
     * <BR>
     * �R�j�@@�Q�j�̏����ɊY�����Ȃ��ꍇ�A�P�j�ŋ��߂�����������ԋp����B<BR>
     * @@param l_toRequestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * �i�A���j�M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    private double getOrderQuantityCnt(
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderQuantityCnt(WEB3ToSuccMarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblOrderCnt = 0;
        WEB3GenRequest l_request = l_toRequestAdapter.request;
        
        String l_strClosingOrder = null;
        String l_strOrderQuantity = null;
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits = null;
        
        if (l_request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            WEB3SuccMarginCloseConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginCloseConfirmRequest) l_request;
            l_strClosingOrder = l_confirmRequest.closingOrder;
            l_strOrderQuantity = l_confirmRequest.orderQuantity;
            l_closeMarginContractUnits = l_confirmRequest.closeMarginContractUnits;

        }
        else if (l_request instanceof WEB3SuccMarginCloseCompleteRequest)
        {
            WEB3SuccMarginCloseCompleteRequest l_completeRequest = 
                (WEB3SuccMarginCloseCompleteRequest) l_request;
            l_strClosingOrder = l_completeRequest.closingOrder;
            l_strOrderQuantity = l_completeRequest.orderQuantity;
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
        
        if (!WEB3ClosingOrderDef.RANDOM.equals(l_strClosingOrder))
        {
            l_dblOrderCnt = Double.parseDouble(l_strOrderQuantity);
        }
        else
        {
            //���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���Ϗ����敪=="�����_��"�̏ꍇ�́A
            //�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���ό����ꗗ�̑S�v�f�̒���������SUM�l���g�p�B
            int l_intUnitLength = l_closeMarginContractUnits.length;
            for (int i = 0; i < l_intUnitLength; i++)
            {
                l_dblOrderCnt += 
                    Double.parseDouble(l_closeMarginContractUnits[i].orderQuantity);
            }
        }
        
        //�����߂��������� > ���N�G�X�g�A�_�v�^.�e�����̒����P��.�������ʂ̏ꍇ�A
        //�@@�u�����������e�����̒������ʂ𒴉߁v�̗�O���X���[����B
        if (l_dblOrderCnt > l_toRequestAdapter.parentOrderUnit.getQuantity())
        {
            log.debug("�����������e�����̒������ʂ𒴉߂��Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02290,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����������e�����̒������ʂ𒴉߂��Ă��܂��B");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblOrderCnt;
    }
}
@
