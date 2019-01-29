head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginSwapMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����������n���̓T�[�r�XImpl(WEB3ToSuccMarginSwapMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/8 杊��](���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginSwapMarginInputRequest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginSwapMarginInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�M�p����������n���̓T�[�r�XImpl)<BR>
 * �i�A���j�M�p����������n���̓T�[�r�X�����N���X�B<BR>
 *   
 * @@author 杊��]
 * @@version 1.0
 */
public class WEB3ToSuccMarginSwapMarginInputServiceImpl 
    extends WEB3MarginSwapMarginInputServiceImpl
    implements WEB3ToSuccMarginSwapMarginInputService 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginSwapMarginInputServiceImpl.class);

    /**
     * @@roseuid 436ACF760157
     */
    public WEB3ToSuccMarginSwapMarginInputServiceImpl() 
    {
     
    }
    
    /**
     * �i�A���j�M�p����������n���͉�ʕ\�����������{����B<BR>
     * <BR>
     * this.get�������n���͉��()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4340E2BB03AE
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
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccMarginSwapInputRequest)
        {
            l_response = this.getSwapMarginInputScreen((WEB3SuccMarginSwapInputRequest) l_request);
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
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�������n���͉��)<BR>
     * �i�A���j�M�p����������n���͉�ʕ\���T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�M�p����������n���̓T�[�r�X�jget�������n���͉�ʁv<BR>
     * �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccMarginSwapInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 43424D140379
     */
    protected WEB3SuccMarginSwapInputResponse getSwapMarginInputScreen(WEB3SuccMarginSwapInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSwapMarginInputScreen(WEB3SuccMarginSwapInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 get�����e�����̒����P��(�i�e�����j����ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_request.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        if (l_orderUnit == null)
        {
            log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�\�����Ȃ��V�X�e���G���[���������܂����B");
        }
        
        //1.3 get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.4 reset�s��R�[�h(�s��R�[�h : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //1.5 validate�A������(�⏕���� : �⏕����, �����^�C�v : ProductTypeEnum, �敨�^�I�v�V�����敪 : String, 
        //  �A����������敪 : String, �e�����̒����P�� : OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount, 
            ProductTypeEnum.EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT, 
            l_request.succCommonInfo.succTradingType,
            l_orderUnit);
        
        //1.6 validate�A�������ő�ݒ萔(�e�����̒����P�� : OrderUnit)
        l_toOrderManager.validateSuccOrderMaxQuantity(l_orderUnit);
        
        //1.7 get�������n���͉��(���N�G�X�g�f�[�^ : �M�p����������n�������̓��N�G�X�g)
        WEB3SuccMarginSwapInputResponse l_response = (WEB3SuccMarginSwapInputResponse) 
            super.getSwapMarginInputScreen(l_request);
        
        //1.8 is���Δ������(�A����������敪 : String, �e�����̒����P�� : OrderUnit)
        boolean l_blnIsReversingTrade = l_toOrderManager.isReversingTrade(
            l_request.succCommonInfo.succTradingType,
            l_orderUnit);
        
        //1.9 �i(*) �v���p�e�B�Z�b�g�j
        //�u�i�A���j�M�p����������n�������̓��X�|���X�v�ɂ̂ݑ��݂���v���p�e�B
        //���������F�@@
        //���A�������}�l�[�W��Impl.is���Δ������()==true�̏ꍇ
        //�i�e�����̒����P��.�������ʁj���Z�b�g�B
        //���A�������}�l�[�W��Impl.is���Δ������()==false�̏ꍇ
        //  null���Z�b�g�B
        if (l_blnIsReversingTrade)
        {
            l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        }
        else 
        {
            l_response.orderQuantity = null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get����)<BR>
     * �������擾���ԋp����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�p�����[�^.���N�G�X�g�f�[�^��<BR>
     * �@@�i�A���j�M�p����������n�������̓��N�G�X�g�ɃL���X�g����B<BR>
     * <BR>
     * �Q�j�@@�e�����̒����P�ʂ��擾����B<BR>
     * �@@�A�������}�l�[�W��Impl.get�����e�����̒����P��()��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �@@[get�����e�����̒����P��()�Ɏw�肷�����]<BR>
     * �@@�@@�i�e�����j����ID�F�@@�P�j�Ŏ擾�������N�G�X�g.�A���������ʏ��̓�����<BR>
     * <BR>
     * �R�j�@@���Δ���(*1)�̏ꍇ�A<BR>
     * �@@�A�������}�l�[�W��Impl.create����()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[create����()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�Q�j�Ŏ擾�����e�����̒����P��<BR>
     * �@@�@@<BR>
     * �S�j�@@�����c�ɑ΂��錈�ρi�R�j�ȊO�j�̏ꍇ�A<BR>
     * �@@super.get����()���R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[get����()�Ɏw�肷�����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * <BR>
     * (*1)�A�������}�l�[�W��Impl.is���Δ������() == true�B<BR>
     * �@@[is���Δ������()�Ɏw�肷�����]<BR>
     * �@@�@@�A����������敪�F�@@�P�j�Ŏ擾�������N�G�X�g.�A���������ʏ��̓�����<BR>
     * �@@�@@�e�����̒����P�ʁF�@@�Q�j�Ŏ擾�����e�����̒����P��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p����������n�������̓��N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     * @@roseuid 43424DBB0146
     */
    protected WEB3EquityContract getContract(WEB3MarginSwapMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getContract(WEB3MarginSwapMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityContract l_contract = null;
        
        //�P�j�@@�p�����[�^.���N�G�X�g�f�[�^���i�A���j�M�p����������n�������̓��N�G�X�g�ɃL���X�g����B
        WEB3SuccMarginSwapInputRequest l_succRequest = (WEB3SuccMarginSwapInputRequest) l_request;
        
        //�Q�j�@@�e�����̒����P�ʂ��擾����B
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succRequest.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        if (l_orderUnit == null)
        {
            log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�\�����Ȃ��V�X�e���G���[���������܂����B");
        }
        
        //(*1)�A�������}�l�[�W��Impl.is���Δ������()
        boolean l_blnIsReversingTrade = l_toOrderManager.isReversingTrade(
            l_succRequest.succCommonInfo.succTradingType,
            l_orderUnit);
        
        //�R�j�@@���Δ���(*1)�̏ꍇ
        if (l_blnIsReversingTrade)
        {
            //�A�������}�l�[�W��Impl.create����()���R�[����,�߂�l��ԋp����B
            l_contract = l_toOrderManager.createContract(l_orderUnit);
        }
        //�S�j�@@�����c�ɑ΂��錈�ρi�R�j�ȊO�j�̏ꍇ
        else 
        {
            //super.get����()���R�[�����A�߂�l��ԋp����B
            l_contract = super.getContract(l_request);
        }

        log.exiting(STR_METHOD_NAME);
        return l_contract;
    }
    
    /**
     * (create�������׈ꗗ)<BR>
     * ���N�G�X�g�f�[�^���M�p����������ׂ̈ꗗ��<BR>
     * �쐬����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�p�����[�^.���N�G�X�g�f�[�^��<BR>
     * �@@�i�A���j�M�p����������n�������̓��N�G�X�g�ɃL���X�g����B<BR>
     * <BR>
     * �Q�j�@@�e�����̒����P�ʂ��擾����B<BR>
     * �@@�A�������}�l�[�W��Impl.get�����e�����̒����P��()��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �@@[get�����e�����̒����P��()�Ɏw�肷�����]<BR>
     * �@@�@@�i�e�����j����ID�F�@@�P�j�Ŏ擾�������N�G�X�g.�A���������ʏ��̓�����<BR>
     * <BR>
     * �R�j�@@���Δ���(*1)�̏ꍇ�A<BR>
     * �@@�ȉ��̎菇�ɂĐM�p����������ׂ��쐬����B<BR>
     * �@@�R�|�P�j�@@�A�������}�l�[�W��Impl.create��������()��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@[create��������()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�Q�j�Ŏ擾�����e�����̒����P��<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�v���p�e�B�Z�b�g�����C���X�^���X�݂̂�v�f�Ƃ���<BR>
     * �@@�@@�z��𐶐����A�ԋp����B<BR>
     * <BR>
     * �S�j�@@�����c�ɑ΂��錻�����n�i�R�j�ȊO�j�̏ꍇ�A<BR>
     * �@@super.create�������׈ꗗ()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[create�������׈ꗗ()�Ɏw�肷�����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * <BR>
     * (*1)�A�������}�l�[�W��Impl.is���Δ������() == true�B<BR>
     * �@@[is���Δ������()�Ɏw�肷�����]<BR>
     * �@@�@@�A����������敪�F�@@�P�j�Ŏ擾�������N�G�X�g.�A���������ʏ��̓�����<BR>
     * �@@�@@�e�����̒����P�ʁF�@@�Q�j�Ŏ擾�����e�����̒����P��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p����������n�������̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@param l_dblCurrentPrice - (����)<BR>
     * ����<BR>
     * @@return webbroker3.equity.message.WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 43424DC50202
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        WEB3MarginSwapMarginInputRequest l_request, 
        double l_dblCurrentPrice) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMarginContractUnitList(WEB3MarginSwapMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginContractUnit[] l_contractUnits = null;
        
        //�P�j�@@�p�����[�^.���N�G�X�g�f�[�^���i�A���j�M�p����������n�������̓��N�G�X�g�ɃL���X�g����B
        WEB3SuccMarginSwapInputRequest l_succRequest = (WEB3SuccMarginSwapInputRequest) l_request;

        //�Q�j�@@�e�����̒����P�ʂ��擾����B
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succRequest.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        if (l_orderUnit == null)
        {
            log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�\�����Ȃ��V�X�e���G���[���������܂����B");
        }

        //(*1)�A�������}�l�[�W��Impl.is���Δ������()
        boolean l_blnIsReversingTrade = l_toOrderManager.isReversingTrade(
            l_succRequest.succCommonInfo.succTradingType,
            l_orderUnit);

        //�R�j�@@���Δ���(*1)�̏ꍇ
        if (l_blnIsReversingTrade)
        {
            //�R�|�P�j�@@�A�������}�l�[�W��Impl.create��������()���R�[������B
            WEB3MarginContractUnit l_contractUnit = l_toOrderManager.createMarginContractUnit(l_orderUnit);
            
            //�R�|�Q�j�@@�v���p�e�B�Z�b�g�����C���X�^���X�݂̂�v�f�Ƃ���z��𐶐����A�ԋp����B
            l_contractUnits = new WEB3MarginContractUnit[]{l_contractUnit};
        }
        //�S�j�@@�����c�ɑ΂��錻�����n�i�R�j�ȊO�j�̏ꍇ
        else 
        {
            l_contractUnits = super.createMarginContractUnitList(l_request, l_dblCurrentPrice);
        }

        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
    
    /**
     * (sort�������׈ꗗ)<BR>
     * �����̌������׈ꗗ���\�[�g����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�p�����[�^.���N�G�X�g�f�[�^��<BR>
     * �@@�i�A���j�M�p����������n�������̓��N�G�X�g�ɃL���X�g����B<BR>
     * <BR>
     * �Q�j�@@�e�����̒����P�ʂ��擾����B<BR>
     * �@@�A�������}�l�[�W��Impl.get�����e�����̒����P��()��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �@@[get�����e�����̒����P��()�Ɏw�肷�����]<BR>
     * �@@�@@�i�e�����j����ID�F�@@�P�j�Ŏ擾�������N�G�X�g.�A���������ʏ��̓�����<BR>
     * <BR>
     * �R�j�@@���Δ���(*1)�̏ꍇ�A<BR>
     * �@@�ʌ��ς݂̂Ȃ̂Ń\�[�g�s�v�ł���ׁA<BR>
     * �@@�������I������B<BR>
     * <BR>
     * �S�j�@@�����c�ɑ΂��錻�����n�i�R�j�ȊO�j�̏ꍇ�A<BR>
     * �@@super.sort�������׈ꗗ()���R�[������B<BR>
     * <BR>
     * �@@[sort�������׈ꗗ()�Ɏw�肷�����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * <BR>
     * (*1)�A�������}�l�[�W��Impl.is���Δ������() == true�B<BR>
     * �@@[is���Δ������()�Ɏw�肷�����]<BR>
     * �@@�@@�A����������敪�F�@@�P�j�Ŏ擾�������N�G�X�g.�A���������ʏ��̓�����<BR>
     * �@@�@@�e�����̒����P�ʁF�@@�Q�j�Ŏ擾�����e�����̒����P��<BR>
     * @@param l_contractUnits - (�������׈ꗗ)<BR>
     * �M�p����������ׂ̔z��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p����������n�������̓��N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43424DC50240
     */
    protected void sortMarginContractUnitList(
        WEB3MarginContractUnit[] l_contractUnits, 
        WEB3MarginSwapMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " sortMarginContractUnitList(WEB3MarginContractUnit[], " +
            "WEB3MarginSwapMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�p�����[�^.���N�G�X�g�f�[�^���i�A���j�M�p����������n�������̓��N�G�X�g�ɃL���X�g����B
        WEB3SuccMarginSwapInputRequest l_succRequest = (WEB3SuccMarginSwapInputRequest) l_request;
     
        //�Q�j�@@�e�����̒����P�ʂ��擾����B
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succRequest.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        if (l_orderUnit == null)
        {
            log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�\�����Ȃ��V�X�e���G���[���������܂����B");
        }

        //(*1)�A�������}�l�[�W��Impl.is���Δ������()
        boolean l_blnIsReversingTrade = l_toOrderManager.isReversingTrade(
            l_succRequest.succCommonInfo.succTradingType,
            l_orderUnit);

        //�S�j�@@�����c�ɑ΂��錻�����n�i���Δ���false�j�ȊO�j�̏ꍇ
        if (!l_blnIsReversingTrade)
        {
            super.sortMarginContractUnitList(l_contractUnits, l_request);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�����\�z)<BR>
     * �����\�z���擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����̌���.isLong()==false�inot�����j�̏ꍇ�́Anull��ԋp����B<BR>
     * �@@�@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@����]�̓T�[�r�X.get�M�p�����\�z�`�A�������`(�����̕⏕����, null)��<BR>
     * �@@�@@�@@�߂�l��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_contract - (����)<BR>
     * �����I�u�W�F�N�g�B<BR>
     * @@return Double
     * @@throws WEB3BaseException
     * @@roseuid 43435A40006F
     */
    protected Double getActualReceiptTradingPower(WEB3GentradeSubAccount l_subAccount, WEB3EquityContract l_contract)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getActualReceiptTradingPower(WEB3GentradeSubAccount, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);
        
        //�����̌���.isLong()==false�inot�����j�̏ꍇ�́Anull��ԋp����B
        if (!l_contract.isLong())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //����]�̓T�[�r�X.get�M�p�����\�z�`�A�������`(�����̕⏕����, null)�̖߂�l��ԋp����B
        WEB3TPTradingPowerService l_tradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        double l_dblSuccActualReceiptTradingPower =
            l_tradingPowerService.getSuccActualReceiptTradingPower(l_subAccount, null);

        log.exiting(STR_METHOD_NAME);
        return new Double(l_dblSuccActualReceiptTradingPower);
    }
}
@
