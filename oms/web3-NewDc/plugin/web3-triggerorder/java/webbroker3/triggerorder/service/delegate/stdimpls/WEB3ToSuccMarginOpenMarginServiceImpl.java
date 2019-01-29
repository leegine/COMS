head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginOpenMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����V�K���T�[�r�XImpl(WEB3ToSuccMarginOpenMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08�@@���@@��(���u) �V�K�쐬
Revesion History : 2007/01/11  �  ��(���u) �d�l�ύX���f��216
Revesion History : 2007/01/17  �  ��(���u) �d�l�ύX���f��222
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3MarginNewOrderValidationResult;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginRequestAdapter;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOpenMarginServiceImpl;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginOpenMarginRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginOpenMarginService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�M�p����V�K���T�[�r�XImpl)<BR>
 * �i�A���j�M�p����V�K���T�[�r�X�����N���X<BR>
 * 
 * @@author ���@@��(���u)
 * @@version 1.0
 */
public class WEB3ToSuccMarginOpenMarginServiceImpl extends WEB3MarginOpenMarginServiceImpl
    implements WEB3ToSuccMarginOpenMarginService 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginOpenMarginServiceImpl.class);
    
    /**
     * @@roseuid 436ACF780119
     */
    public WEB3ToSuccMarginOpenMarginServiceImpl() 
    {
     
    }
    
    /**
     * �i�A���j�M�p����V�K���T�[�r�X���������{����<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * [�i�A���j�M�p����V�K�������m�F���N�G�X�g�̏ꍇ]<BR>
     * �@@this.validate����()���R�[������B<BR>
     * [�i�A���j�M�p����V�K�������������N�G�X�g�̏ꍇ]<BR>
     * �@@this.submit����()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 432F76C50123
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
        if (l_request instanceof WEB3SuccMarginOpenConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccMarginOpenConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3SuccMarginOpenCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccMarginOpenCompleteRequest) l_request);
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
     * �i�A���j�M�p����V�K�������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�M�p����V�K���T�[�r�X�jvalidate�����v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p����V�K�������m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3SuccMarginOpenConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 432F77FA0058
     */
    public WEB3SuccMarginOpenConfirmResponse validateOrder(
        WEB3SuccMarginOpenConfirmRequest l_request) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3SuccMarginOpenConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.3 get�����e�����̒����P��(�i�e�����j����ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3SuccCommonInfo l_commonInfo = l_request.succCommonInfo;
        
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(
            Long.parseLong(l_commonInfo.parentOrderId));
        
        //1.4 validate���N�G�X�g�f�[�^at���Ύ��(WEB3GenRequest, �����P��)
        this.validateRequestDataAtReversingTrade(l_request, l_orderUnit);
        
        //1.5 validate�A������(�⏕����, ProductTypeEnum, String, String, OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_commonInfo.succTradingType,
            l_orderUnit);
        
        //1.6 validate�A�������ő�ݒ萔(�e�����̒����P�� : OrderUnit)
        this.validateSuccOrderMaxQuantity(l_orderUnit);
        
        //1.7 validate����(���N�G�X�g�f�[�^ : �M�p����V�K�������m�F���N�G�X�g)
        WEB3SuccMarginOpenConfirmResponse l_response = (
            WEB3SuccMarginOpenConfirmResponse) super.validateOrder(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����)<BR>
     * �i�A���j�M�p����V�K�������o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�M�p����V�K���T�[�r�X�jsubmit�����v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p����V�K�������������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3SuccMarginOpenCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 432F77FA00A6
     */
    protected WEB3SuccMarginOpenCompleteResponse submitOrder(
        WEB3SuccMarginOpenCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3SuccMarginOpenCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.3 get�����e�����̒����P��(�i�e�����j����ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3SuccCommonInfo l_commonInfo = l_request.succCommonInfo;
        
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(
            Long.parseLong(l_commonInfo.parentOrderId));
        
        //1.4 validate���N�G�X�g�f�[�^at���Ύ��(WEB3GenRequest, �����P��)
        validateRequestDataAtReversingTrade(l_request, l_orderUnit);
        
        //1.5 validate�A������(�⏕����, ProductTypeEnum, String, String, OrderUnit)
        l_toOrderManager.validateSuccOrder(l_subAccount, 
            ProductTypeEnum.EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT,
            l_commonInfo.succTradingType,
            l_orderUnit);
 
        //1.6 validate�A�������ő�ݒ萔(�e�����̒����P�� : OrderUnit)
        this.validateSuccOrderMaxQuantity(l_orderUnit);

        //1.7 super.submit����()
        WEB3SuccMarginOpenCompleteResponse l_response = (
            WEB3SuccMarginOpenCompleteResponse) super.submitOrder(l_request);
        
        //1.8 notify�\�񒍕��o�^(long)
        this.notifyRsvOrderRegister(Long.parseLong(l_request.orderId));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �i�A���j�M�p����V�K�����N�G�X�g�A�_�v�^.create(�����̃��N�G�X�g)��<BR>
     * �R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3MarginOpenMarginRequestAdapter
     * @@roseuid 432FEC2003CB
     */
    protected WEB3MarginOpenMarginRequestAdapter createRequestAdapter(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = " createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginOpenMarginRequestAdapter l_marginRequestAdpter = 
            WEB3ToSuccMarginOpenMarginRequestAdapter.create(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_marginRequestAdpter;
    }
    
    /**
     * (validate����]��)<BR>
     * ����]�͂��`�F�b�N����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �]�̓`�F�b�N�����{���镔�X(*3)�̏ꍇ�̂݁A<BR>
     * �V�K���\�z(*1)�ƊT�Z��n����i�����(*2)�j���r���A<BR>
     * �i�T�Z��n��� > �V�K���\�z�j�̏ꍇ�́A<BR>
     * �u����]�͕s���v�̗�O��throw����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00667<BR>
     * <BR>
     * �ȊO�Anull��ԋp����B<BR>
     * <BR>
     * (*1)����]�̓T�[�r�XImpl.<BR>
     * �@@get�M�p�V�K���\�z�`�A�������`(�p�����[�^.�⏕����, null)<BR>
     * �ɂĎ擾�B<BR>
     * <BR>
     * (*2)�p�����[�^.�M�p�V�K���������e.get�����()�ɂĎ擾�B<BR>
     * <BR>
     * (*3)�]�̓`�F�b�N�����{���镔�X<BR>
     * �@@�A�������}�l�[�W��Impl.is�]�̓`�F�b�N���{���X()==true�̏ꍇ�́A<BR>
     * �@@�]�̓`�F�b�N�����{���镔�X�ł���Ɣ��肷��B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_orderSpec - (�M�p�V�K���������e)<BR>
     * �M�p�V�K���������e�I�u�W�F�N�g�B<BR>
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * �]�͍X�V�t���O�B<BR>
     * �ifalse�F�@@�m�F���Atrue�F�@@�������j<BR>
     * @@param l_commission - (�萔��)<BR>
     * �萔���I�u�W�F�N�g<BR>
     * @@param l_validationResult - (�����R������)<BR>
     * �M�p�V�K���V�K���������R�����ʃI�u�W�F�N�g<BR>
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     * @@roseuid 432FEC210002
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3MarginOpenContractOrderSpec l_orderSpec, 
        boolean l_blnUpdateFlg, 
        WEB3GentradeCommission l_commission, 
        WEB3MarginNewOrderValidationResult l_validationResult) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateTradingPower(" +
            "WEB3GentradeSubAccount, " +
            "WEB3MarginOpenContractOrderSpec, " +
            "boolean, " +
            "WEB3GentradeCommission, " +
            "WEB3MarginNewOrderValidationResult)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderSpec == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager =
        (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        if (l_toOrderManager.isCheckTradingPowerBranch(l_subAccount) == false)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3TPTradingPowerService l_tradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        double l_dblTradingPower = 0.0;

        //�V�K���\�z
        l_dblTradingPower = l_tradingPowerService.getSuccMarginTradingPower(l_subAccount, null);
        //�T�Z��n���
        double l_dblContractAmount = l_orderSpec.getContractAmount();
        
        if (l_dblContractAmount > l_dblTradingPower)
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
     * (submit�V�K������)<BR>
     * �\�񒍕���o�^����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �A�������}�l�[�W��.submit�M�p�V�K���V�K�\�񒍕�()���R�[������B<BR>
     * <BR>
     * [submit�M�p�V�K���V�K�\�񒍕�()�Ɏw�肷�����]<BR>
     * �⏕�����F�@@�����̕⏕����<BR>
     * �������e�F�@@�����̐M�p�V�K���������e<BR>
     * ����ID�F�@@�����̒���ID<BR>
     * ����p�X���[�h�F�@@�����̃p�X���[�h<BR>
     * �A����������敪�F�@@�����̃��N�G�X�g�A�_�v�^.���N�G�X�g.�A���������ʏ��.<BR>
     * �A����������敪<BR>
     * �P�������l�F�@@�����̃��N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���.get�P�������l()<BR>
     * �@@�@@�������̃��N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���==null�̏ꍇ�́A<BR>
     * null���Z�b�g<BR>
     * �e�����̒����P�ʁF�@@�����̃��N�G�X�g�A�_�v�^.�e�����̒����P��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_orderSpec - (�M�p�V�K���������e)<BR>
     * �M�p�V�K���������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * �\�񒍕��̒���ID�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_requestAdaptor - (�V�K�����N�G�X�g�A�_�v�^)<BR>
     * �M�p����V�K�����N�G�X�g�A�_�v�^�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 432FEC210012
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3MarginOpenContractOrderSpec l_orderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        WEB3MarginOpenMarginRequestAdapter l_requestAdaptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitOpenContractOrder(" +
            "WEB3GentradeSubAccount, " +
            "WEB3MarginOpenContractOrderSpec, " +
            "long, " +
            "String, " +
            "WEB3MarginOpenMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccMarginOpenMarginRequestAdapter l_toSuccEquityOrderRequestAdapter = null;
        if (l_requestAdaptor instanceof WEB3ToSuccMarginOpenMarginRequestAdapter)
        {
            l_toSuccEquityOrderRequestAdapter = 
                (WEB3ToSuccMarginOpenMarginRequestAdapter) l_requestAdaptor;
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
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3SuccCommonInfo l_succCommonInfo = null;
        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = null;
        if (l_requestAdaptor.request instanceof WEB3SuccMarginOpenConfirmRequest)
        {
            WEB3SuccMarginOpenConfirmRequest l_openConfirmRequest = 
                (WEB3SuccMarginOpenConfirmRequest) l_requestAdaptor.request;
            l_succCommonInfo = l_openConfirmRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_openConfirmRequest.priceAdjustmentValueInfo;
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccMarginOpenCompleteRequest)
        {
            WEB3SuccMarginOpenCompleteRequest l_openCompleteRequest = 
                (WEB3SuccMarginOpenCompleteRequest) l_requestAdaptor.request;
            l_succCommonInfo = l_openCompleteRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_openCompleteRequest.priceAdjustmentValueInfo;
        }

        //�A�������}�l�[�W��.submit�M�p�V�K���V�K�\�񒍕�
        Double l_dblPrice = null;
        if (l_priceAdjustmentValueInfo == null)
        {
            l_dblPrice = null;
        }
        else 
        {
            l_dblPrice = new Double(l_priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }

        l_toOrderManager.submitEqtypeOpenContractNewOrder(
            l_subAccount, 
            l_orderSpec, 
            l_lngOrderId, 
            l_strTradingPassword, 
            l_succCommonInfo.succTradingType,
            l_dblPrice,
            l_toSuccEquityOrderRequestAdapter.parentOrderUnit); 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���N�G�X�g�f�[�^at���Ύ��)<BR>
     * ���Ύ���w�莞�ɌŗL�́A���N�G�X�g�f�[�^�̃v���p�e�B�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�A�������}�l�[�W��Impl.is���Δ������(<BR>
     * �@@�@@�@@�����̃��N�G�X�g.�A���������ʏ��.�A����������敪, ����<BR>
     * �̐e�����̒����P��)==false<BR>
     * �@@�@@�@@�i�����Ύ���łȂ��j�ꍇ�́A<BR>
     * �@@�@@�@@����������return����B<BR>
     * <BR>
     * �@@�@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@�ȉ��̏����̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�u���Ύ�����̖����w�肪�A�e�����ƕs�����v�̗�O��throw����B<BR>
     * �@@�@@�@@�E�����̃��N�G�X�g�f�[�^.�����R�[�h��<BR>
     * �@@�@@�@@�@@�����̐e�����̒����P��.����ID�ɊY�����銔������.�����R�[�h<BR>
     * �@@�@@�@@�E�����̃��N�G�X�g�f�[�^�s��R�[�h��<BR>
     * �@@�@@�@@�@@�����̐e�����̒����P��.�s��ID�ɊY������s��.�s��R�[�h<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_02250<BR>
     * <BR>
     * �@@�@@�@@�������̃��N�G�X�g�f�[�^�́A�ȉ��̂����ꂩ�ɃL���X�g���邱�ƁB<BR>
     * �@@�@@�@@�@@�E�i�A���j�M�p����V�K�������m�F���N�G�X�g<BR>
     * �@@�@@�@@�@@�E�i�A���j�M�p����V�K�������������N�G�X�g<BR>
     * <BR>
     * �R�j�@@�����̐e�����̒����P��.�����J�e�S�� == "�����E���n����"�̏ꍇ�A<BR>
     * �@@�����̃��N�G�X�g�f�[�^.�P�������l���null�ł���΁A<BR>
     * �@@�u�e�������������n�����̏ꍇ�A�}�w�l�͎w��s�v��<BR>
     * �@@��O��throw����B<BR>
     * �@@class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_02291<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4343ADAB0371
     */
    protected void validateRequestDataAtReversingTrade(
        WEB3GenRequest l_request, EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRequestDataAtReversingTrade(WEB3GenRequest, EqTypeOrderUnit)";
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
        if (l_parentOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3SuccCommonInfo l_succCommonInfo = null;
        String l_strProductCode = null;
        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = null;
        if (l_request instanceof WEB3SuccMarginOpenConfirmRequest)
        {
            WEB3SuccMarginOpenConfirmRequest l_openConfirmRequest = (WEB3SuccMarginOpenConfirmRequest) l_request;
            l_succCommonInfo = l_openConfirmRequest.succCommonInfo;
            l_strProductCode = l_openConfirmRequest.productCode;
            l_priceAdjustmentValueInfo = l_openConfirmRequest.priceAdjustmentValueInfo;
        }
        else if (l_request instanceof WEB3SuccMarginOpenCompleteRequest)
        {
            WEB3SuccMarginOpenCompleteRequest l_openCompleteRequest = (WEB3SuccMarginOpenCompleteRequest) l_request;
            l_succCommonInfo = l_openCompleteRequest.succCommonInfo;
            l_strProductCode = l_openCompleteRequest.productCode;
            l_priceAdjustmentValueInfo = l_openCompleteRequest.priceAdjustmentValueInfo;
        }
        
        //1�j �A�������}�l�[�W��Impl.is���Δ������
        boolean l_blnIsReverTrade = l_toOrderManager.isReversingTrade(
            l_succCommonInfo.succTradingType, l_parentOrderUnit);
        
        if (!l_blnIsReverTrade)
        {
            log.exiting(STR_METHOD_NAME); 
            return;
        }
        
        //2�j�ȉ��̏����̂����ꂩ�ɊY������ꍇ�́A
        // �@@�@@�@@�u���Ύ�����̖����w�肪�A�e�����ƕs�����v�̗�O��throw����B
        // �@@�@@�@@�E�����̃��N�G�X�g�f�[�^.�����R�[�h��
        // �@@�@@�@@�@@�����̐e�����̒����P��.����ID�ɊY�����銔������.�����R�[�h

        // �@@�@@�@@class: WEB3BusinessLayerException 
        // �@@�@@�@@tag: BUSINESS_ERROR_02250
        // �@@�@@�@@�������̃��N�G�X�g�f�[�^�́A�ȉ��̂����ꂩ�ɃL���X�g���邱�ƁB
        // �@@�@@�@@�@@�E�i�A���j�M�p����V�K�������m�F���N�G�X�g
        // �@@�@@�@@�@@�E�i�A���j�M�p����V�K�������������N�G�X�g
        EqTypeProduct l_product = (EqTypeProduct) l_parentOrderUnit.getProduct();

        if ((l_strProductCode == null) || !l_strProductCode.equals(l_product.getProductCode()))
        {
           log.debug("���Ύ�����̖����w�肪�A�e�����ƕs�����ł��B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02250,
               getClass().getName() + "." + STR_METHOD_NAME,
               "���Ύ�����̖����w�肪�A�e�����ƕs�����ł��B");
        }
        
        //3) �����̐e�����̒����P��.�����J�e�S�� == "�����E���n����"�̏ꍇ�A
        // �@@�����̃��N�G�X�g�f�[�^.�P�������l���null�ł���΁A
        // �@@�u�e�������������n�����̏ꍇ�A�}�w�l�͎w��s�v��
        // �@@��O��throw����B
        // �@@class: WEB3BusinessLayerException 
        // �@@tag: BUSINESS_ERROR_02291
        if (OrderCategEnum.SWAP_MARGIN.equals(
            l_parentOrderUnit.getOrderCateg()) && (l_priceAdjustmentValueInfo != null))
        {
            log.debug("�e�������������n�����̏ꍇ�A�}�w�l�͎w��s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02291,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�e�������������n�����̏ꍇ�A�}�w�l�͎w��s�ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);   
    }
    
    /**
     * (validate�A�������ő�ݒ萔)<BR>
     * �A�������̍ő�ݒ萔�𒴉߂��Ă��܂�Ȃ����ǂ������`�F�b�N����B<BR>
     * <BR>
     * �A�������}�l�[�W��.validate�A�������ő�ݒ萔(�����̐e������<BR>
     * �����P��)��delegate����B<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433C933F02A9
     */
    protected void validateSuccOrderMaxQuantity(EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateSuccOrderMaxQuantity(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        
        l_toOrderManager.validateSuccOrderMaxQuantity(l_parentOrderUnit);
        
        log.exiting(STR_METHOD_NAME);   
    }
    
    /**
     * (notify�\�񒍕��o�^)<BR>
     * �\�񒍕��̓o�^�����[���G���W���T�[�o�ɒʒm����B<BR>
     * <BR>
     * �P�j�@@�\�񒍕��P�ʂ̎擾<BR>
     * �@@�A�������}�l�[�W��.get�����\�񒍕��P��()�ɂāA<BR>
     * �@@�\�񒍕��P�ʂ��擾����B<BR>
     * <BR>
     * �@@[get�����\�񒍕��P��()�Ɏw�肷�����]<BR>
     * �@@�@@����ID�F�@@�q�����̒���ID<BR>
     * <BR>
     * �Q�j�@@���[���G���W���ɒ����̓o�^��ʒm����B<BR>
     * �@@�g�����������}�l�[�W��.notify���[���G���W���T�[�o()<BR>
     * �@@���R�[������B<BR>
     * <BR>
     * �@@[notify���[���G���W���T�[�o()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�P�j�̖߂�l<BR>
     * �@@�@@�����F�@@NEW_OPEN_CONTRACT_ORDER<BR>
     * @@param l_lngSubOrderId - (�q�����̒���ID)<BR>
     * �q�����̒���ID�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 435EDBFC0375
     */
    protected void notifyRsvOrderRegister(long l_lngSubOrderId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " notifyRsvOrderRegister(long)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            //�P�j�\�񒍕��P�ʂ̎擾
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngSubOrderId);
        }
        catch (NotFoundException l_nft)
        {
            log.error(STR_METHOD_NAME, l_nft);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nft.getMessage(),
                l_nft);
        }

        //�Q�j���[���G���W���ɒ����̓o�^��ʒm����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        
        l_orderMgr.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER);
            
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (set�P��)<BR>
     * �����̃��X�|���X�D������P���ɒP����ݒ肷��B <BR>
     * <BR>
     * �P�j���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ <BR>
     * �@@�@@���X�|���X�D������P���ɁA�����̃��N�G�X�g�A�_�v�^�Dget�P��()�̖߂�l���Z�b�g����B <BR>
     * <BR>
     * �Q�j��L�ȊO�̏ꍇ�A <BR>
     * �@@�@@�����������^�[������B <BR>
     * <BR>
     * @@param l_adapter - (�M�p����V�K�����N�G�X�g�A�_�v�^)<BR>
     * �M�p����V�K�����N�G�X�g�A�_�v�^�B<BR>
     * @@param l_response - (���X�|���X)<BR>
     * ���X�|���X�B<BR>
     * @@throws WEB3BaseException 
     */
    protected void setPrice(WEB3MarginOpenMarginRequestAdapter l_adapter,
        WEB3GenResponse l_response) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setPrice(WEB3MarginOpenMarginRequestAdapter, "
            + "WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginOpenConfirmRequest l_confirmRequest = null;

        if (l_adapter == null || l_response == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }

        WEB3GenRequest l_request = l_adapter.request;
        
        if (l_request instanceof WEB3SuccMarginOpenConfirmRequest)
        {
            l_confirmRequest = (WEB3SuccMarginOpenConfirmRequest)l_adapter.request;
        
            //�P�j���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ 
            if (l_confirmRequest.priceAdjustmentValueInfo != null)
            {
                WEB3SuccMarginOpenConfirmResponse l_confirmResponse = 
                    (WEB3SuccMarginOpenConfirmResponse)l_response;
                
                //���X�|���X�D������P���ɁA�����̃��N�G�X�g�A�_�v�^�Dget�P��()�̖߂�l���Z�b�g����B 
                l_confirmResponse.afterAdjustmentPrice = 
                    WEB3StringTypeUtility.formatNumber(l_adapter.getPrice());
            }
        }
        log.exiting(STR_METHOD_NAME);  
    }
}@
