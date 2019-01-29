head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginSwapMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����������n�T�[�r�XImpl(WEB3ToSuccMarginSwapMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 ���(���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginSwapContractOrderSpec;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginRequestAdapter;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginServiceImpl;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginSwapMarginRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginSwapMarginService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�M�p����������n�T�[�r�XImpl)<BR>
 * �i�A���j�M�p����������n�T�[�r�X�����N���X�B<BR>
 * 
 * @@author ���
 * @@version 1.0
 */
public class WEB3ToSuccMarginSwapMarginServiceImpl extends WEB3MarginSwapMarginServiceImpl
    implements WEB3ToSuccMarginSwapMarginService 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginSwapMarginServiceImpl.class);
    
    /**
     * @@roseuid 436ACF75003E
     */
    public WEB3ToSuccMarginSwapMarginServiceImpl() 
    {
     
    }
    
    /**
     * �i�A���j�M�p����������n�T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()���\�b�h�A<BR>
     * submit����()���\�b�h�̂����ꂩ���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4343697B0189
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
        
        if (l_request instanceof WEB3SuccMarginSwapConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccMarginSwapConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3SuccMarginSwapCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccMarginSwapCompleteRequest) l_request);
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
     * �i�A���j�M�p����������n�����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�M�p����������n�T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3SuccMarginSwapConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4343697B01A8
     */
    protected WEB3SuccMarginSwapConfirmResponse validateOrder(WEB3SuccMarginSwapConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3SuccMarginSwapConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate()
        l_request.validate();
        
        //get�����e�����̒����P��(�i�e�����j����ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_request.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_parentOrderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        
        //get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //reset�s��R�[�h(�s��R�[�h : String)
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter =
            (WEB3ToSuccMarginSwapMarginRequestAdapter) this.createRequestAdapter(l_request);
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        
        long l_lngMarketId = 0L;
        if (l_toRequestAdapter.isReversingOrder())
        {
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_parentOrderUnit.getDataSourceObject();
            l_lngMarketId = l_orderUnitRow.getMarketId();
        }
        else
        {
            l_lngMarketId = l_toRequestAdapter.getContract().getMarketId();
        }
        
        try
        {
            Market l_market = l_objectManager.getMarket(l_lngMarketId);
            String l_strMarketCode = l_market.getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�s��e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�s��e�[�u���ɊY������f�[�^������܂���B");
        }
        
        //validate�A������(�⏕���� : �⏕����, �����^�C�v : ProductTypeEnum,
        //        �敨�^�I�v�V�����敪 : String, �A����������敪 : String,
        //        �e�����̒����P�� : OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_request.succCommonInfo.succTradingType,
            l_parentOrderUnit);
        
        //validate�A�������ő�ݒ萔(�e�����̒����P�� : OrderUnit)
        l_toOrderManager.validateSuccOrderMaxQuantity(l_parentOrderUnit);
        
        //validate����(���N�G�X�g�f�[�^ : �M�p����������n�����m�F���N�G�X�g)
        WEB3SuccMarginSwapConfirmResponse l_response = 
            (WEB3SuccMarginSwapConfirmResponse) super.validateOrder(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * �i�A���j�M�p����������n�����o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�M�p����������n�T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3SuccMarginSwapCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4343697B01C7
     */
    protected WEB3SuccMarginSwapCompleteResponse submitOrder(WEB3SuccMarginSwapCompleteRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3SuccMarginSwapCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate()
        l_request.validate();
        
        //get�����e�����̒����P��(�i�e�����j����ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_request.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_parentOrderUnit =
            l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        
        //get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //reset�s��R�[�h(�s��R�[�h : String)
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter =
            (WEB3ToSuccMarginSwapMarginRequestAdapter) this.createRequestAdapter(l_request);
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        
        long l_lngMarketId = 0L;
        if (l_toRequestAdapter.isReversingOrder())
        {
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_parentOrderUnit.getDataSourceObject();
            l_lngMarketId = l_orderUnitRow.getMarketId();
        }
        else
        {
            l_lngMarketId = l_toRequestAdapter.getContract().getMarketId();
        }
        
        try
        {
            Market l_market = l_objectManager.getMarket(l_lngMarketId);
            String l_strMarketCode = l_market.getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�s��e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�s��e�[�u���ɊY������f�[�^������܂���B");
        }
        
        //validate�A������(�⏕���� : �⏕����, �����^�C�v : ProductTypeEnum,
        //        �敨�^�I�v�V�����敪 : String, �A����������敪 : String,
        //        �e�����̒����P�� : OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_request.succCommonInfo.succTradingType,
            l_parentOrderUnit);
        
        //validate�A�������ő�ݒ萔(�e�����̒����P�� : OrderUnit)
        l_toOrderManager.validateSuccOrderMaxQuantity(l_parentOrderUnit);
        
        //submit����(���N�G�X�g�f�[�^ : �M�p����������n�����������N�G�X�g)
        WEB3SuccMarginSwapCompleteResponse l_response = 
            (WEB3SuccMarginSwapCompleteResponse) super.submitOrder(l_request);
        
        //get�����\�񒍕��P��(����ID : long)
        long l_lngOrderId = Long.parseLong(l_request.orderId);
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId); 
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�����\�񒍕��P�ʃe�[�u���ɊY������f�[�^������܂���B�u����ID:" + l_lngOrderId + "�v";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        
        //notify���[���G���W���T�[�o(�����P�� : EqTypeOrderUnit,
        //        ���� : OrderManagerPersistenceContext)
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        l_orderManager.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.NEW_SWAP_CONTRACT_ORDER);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �i�A���j�M�p����������n���N�G�X�g�A�_�v�^.create(�����̃��N�G�X�g)��<BR>
     * �R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3MarginSwapMarginRequestAdapter
     * @@roseuid 4344BEA203C4
     */
    protected WEB3MarginSwapMarginRequestAdapter createRequestAdapter(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = " createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginSwapMarginRequestAdapter l_adapter =
            WEB3ToSuccMarginSwapMarginRequestAdapter.create(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_adapter;
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
     * �@@(*1)�ȉ��̃v���p�e�B���Z�b�g�����M�p������ό������׃C���X�^���X<BR>
     * �@@�@@�݂̂�v�f�Ƃ���z��<BR>
     * <BR>
     * �@@�@@�������� �Ƃ��āA�ȉ��̒l���g�p����B<BR>
     * �@@�@@�����߂��������� > ���N�G�X�g�A�_�v�^.�e�����̒����P��.�������ʂ̏ꍇ�A<BR>
     * �@@�@@�@@�u�����������e�����̒������ʂ𒴉߁v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_02290<BR>
     * �@@�@@<BR>
     * ----------------------------------------------------------------------<BR>
     * �@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���Ϗ����敪��"�����_��"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�������� ���g�p�B<BR>
     * �@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���Ϗ����敪=="�����_��"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���ό����ꗗ�̑S�v�f��<BR>
     * �@@�@@�@@�@@�@@����������SUM�l���g�p�B<BR>
     * �@@�@@<BR>
     * ----------------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�P�j�ȊO�̏ꍇ�A<BR>
     * �@@super.create���ό����G���g��()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[super.create���ό����G���g��()�Ɏw�肷�����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * @@param l_closeMarginContractUnits - (���ό������׈ꗗ)<BR>
     * �M�p������ό����I�u�W�F�N�g�̔z��B<BR>
     * �i���N�G�X�g�j<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * �i�A���j�M�p����������n���N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@return EqTypeSettleContractOrderEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 4344DF250078
     */
    protected EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits, 
        WEB3MarginSwapMarginRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createClosingContractEntry(WEB3MarginCloseMarginContractUnit[], WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntries = null;
        
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter = 
            (WEB3ToSuccMarginSwapMarginRequestAdapter) l_requestAdapter;
        
        //�P�j�@@���Δ����̏ꍇ
        //�@@�i�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true�j
        if (l_toRequestAdapter.isReversingOrder())
        {
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            
            //(*1)�ȉ��̃v���p�e�B���Z�b�g�����M�p������ό������׃C���X�^���X
            //�@@�݂̂�v�f�Ƃ���z��   
            WEB3MarginCloseMarginContractUnit l_contractUnit = new WEB3MarginCloseMarginContractUnit();
            l_contractUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(getOrderQuantityCnt(l_toRequestAdapter));
            
            //�@@�A�������}�l�[�W��Impl.create���ό����G���g��()���R�[�����A
            //�@@�߂�l��ԋp����B
            l_settleContractOrderEntries = l_toOrderManager.createClosingContractEntry(
                new WEB3MarginCloseMarginContractUnit[]{l_contractUnit});
        }
        //�Q�j�@@�P�j�ȊO�̏ꍇ�A
        //�@@super.create���ό����G���g��()���R�[�����A
        //�@@�߂�l��ԋp����B
        else
        {
            l_settleContractOrderEntries =
                super.createClosingContractEntry(l_closeMarginContractUnits, l_requestAdapter);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntries;
    }
    
    /**
     * (get�T�Z��n����i�������n�j)<BR>
     * �T�Z��n����i�������n�j���擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@���Δ����̏ꍇ�A<BR>
     * �@@�i�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true�j<BR>
     * �@@�ȉ��̎菇�ɂĐM�p����������ׂ��쐬����B<BR>
     * �@@�P�|�P�j�@@�A�������}�l�[�W��Impl.create����()��<BR>
     * �@@�@@�R�[�����A�������쐬����B<BR>
     * <BR>
     * �@@�@@[create����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.���N�G�X�g�A�_�v�^.�e�����̒����P��<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�g�����������}�l�[�W��.calc�T�Z��n����i�������n�j()��<BR>
     * �@@�@@�@@�@@�@@�@@�R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * �@@�@@[calc�T�Z��n����i�������n�j()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@���ό����G���g���F �@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@���ʁF�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@�����P�ʁF�@@null�i�Œ�j<BR>
     * �@@�@@�@@�����F�@@���Δ����̏ꍇ�A�P�|�P�j�̖߂�l�B�ȊO�Anull�B<BR>
     * <BR>
     * �Q�j�@@�����c�ɑ΂��錻�����n�i�P�j�ȊO�j�̏ꍇ�A<BR>
     * �@@super.get�T�Z��n����i�������n�j()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[get�T�Z��n����i�������n�j()�Ɏw�肷�����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * @@param l_entries - (���ό����G���g���̔z��)<BR>
     * ���ό����G���g���̔z��<BR>
     * @@param l_dblQuantity -(����)<BR> 
     * ����<BR>
     * @@param l_requestAdapter - (�M�p����������n���N�G�X�g�A�_�v�^)<BR>
     * �M�p����������n���N�G�X�g�A�_�v�^�B<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4344E010026C
     */
    protected double getEstimatedSwapPrice(
        EqTypeSettleContractOrderEntry[] l_entries,
        double l_dblQuantity,
        WEB3MarginSwapMarginRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getEstimatedSwapPrice(EqTypeSettleContractOrderEntry[], double, WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblEstimatedSwapPrice = 0.0;
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter = 
            (WEB3ToSuccMarginSwapMarginRequestAdapter) l_requestAdapter;
        boolean l_blnIsReversingOrder = l_toRequestAdapter.isReversingOrder();
        
        //�P�j�@@���Δ����̏ꍇ�A
        if (l_blnIsReversingOrder)
        {
            //�P�|�P�j�@@�A�������}�l�[�W��Impl.create����()��
            //�@@�R�[�����A�������쐬����B
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            
            WEB3EquityContract l_contract = l_toOrderManager.createContract(l_toRequestAdapter.parentOrderUnit);
            
            //�P�|�Q�j�@@�g�����������}�l�[�W��.calc�T�Z��n����i�������n�j()���R�[�����A
            //�@@�߂�l��ԋp����B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_dblEstimatedSwapPrice = l_orderManager.calcEstimatedSwapPrice(
                l_entries,
                l_dblQuantity,
                null,
                l_contract);
        }
        
        //�Q�j�@@�����c�ɑ΂��錻�����n�i�P�j�ȊO�j�̏ꍇ�A
        //�@@super.get�T�Z��n����i�������n�j()���R�[�����A
        //�@@�߂�l��ԋp����B
        else
        {
            l_dblEstimatedSwapPrice = super.getEstimatedSwapPrice(l_entries, l_dblQuantity, l_requestAdapter);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblEstimatedSwapPrice;
    }
    
    /**
     * (create�������׈ꗗ)<BR>
     * ���ό����G���g�����M�p����������ׂ̈ꗗ��<BR>
     * �쐬����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@���Δ����̏ꍇ�A<BR>
     * �@@�i�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true�j<BR>
     * �@@�ȉ��̎菇�ɂĐM�p����������ׂ��쐬����B<BR>
     * �@@�P�|�P�j�@@�A�������}�l�[�W��Impl.create��������()��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@[create��������()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.���N�G�X�g�A�_�v�^.�e�����̒����P��<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�v���p�e�B�Z�b�g�����C���X�^���X�݂̂�v�f�Ƃ���<BR>
     * �@@�@@�z��𐶐����A�ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����c�ɑ΂��錻�����n�i�P�j�ȊO�j�̏ꍇ�A<BR>
     * �@@super.create�������׈ꗗ()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[create�������׈ꗗ()�Ɏw�肷�����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * @@param l_settleContractOrderEntrys - (���ό����G���g��)<BR>
     * ���ό����G���g���̔z��B<BR>
     * @@param l_dblUnitPrice - (����)<BR>
     * �����B<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * �i�A���j�M�p����������n���N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@return webbroker3.equity.message.WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4344E1BE020E
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
        double l_dblUnitPrice,
        WEB3MarginSwapMarginRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createMarginContractUnitList(EqTypeSettleContractOrderEntry[]," + 
            " double, WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginContractUnit[] l_marginContractUnits = null;
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter = 
            (WEB3ToSuccMarginSwapMarginRequestAdapter) l_requestAdapter;
        boolean l_blnIsReversingOrder = l_toRequestAdapter.isReversingOrder();
        
        //�P�j�@@���Δ����̏ꍇ�A
        if (l_blnIsReversingOrder)
        {
            //�P�|�P�j�@@�A�������}�l�[�W��Impl.create��������()��
            //�@@�R�[������B
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            WEB3MarginContractUnit l_marginContractUnit =
                l_toOrderManager.createMarginContractUnit(l_toRequestAdapter.parentOrderUnit);
            
            //�P�|�Q�j�@@�v���p�e�B�Z�b�g�����C���X�^���X�݂̂�v�f�Ƃ���
            //�@@�z��𐶐����A�ԋp����B
            l_marginContractUnits = new WEB3MarginContractUnit[]{l_marginContractUnit};
        }
        //�Q�j�@@�����c�ɑ΂��錻�����n�i�P�j�ȊO�j�̏ꍇ�A
        //�@@super.create�������׈ꗗ()���R�[�����A
        //�@@�߂�l��ԋp����B
        else
        {
            l_marginContractUnits = 
                super.createMarginContractUnitList(l_settleContractOrderEntrys, l_dblUnitPrice, l_requestAdapter);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_marginContractUnits;
    }
    
    /**
     * (validate�������n����)<BR>
     * �M�p����������n���������R�����s���B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@���Δ����̏ꍇ<BR>
     * �@@�i�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true�j<BR>
     * �@@�g�����������}�l�[�W��.validate�������n����()<BR>
     * �@@���R�[������B<BR>
     * <BR>
     * �@@[validate�������n����()�Ɏw�肷�����]<BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�@@�M�p�������n�������e�F�@@�p�����[�^.�M�p�������n�������e<BR>
     * �@@�@@�����F�@@�p�����[�^.���N�G�X�g�A�_�v�^.get����()<BR>
     * <BR>
     * �Q�j�@@�P�j�ȊO�̏ꍇ<BR>
     * �@@super.validate�������n����()���R�[������B<BR>
     * <BR>
     * �@@[validate�������n����()�Ɏw�肷�����]<BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�@@�M�p�������n�������e�F�@@�p�����[�^.�M�p�������n�������e<BR>
     * �@@�@@���N�G�X�g�A�_�v�^�F�@@�p�����[�^.���N�G�X�g�A�_�v�^<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_orderSpec - (�M�p�������n�������e)<BR>
     * �M�p�������n�������e�I�u�W�F�N�g<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����������n���N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4344E22D003A
     */
    protected void validateSwapContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginSwapContractOrderSpec l_orderSpec,
        WEB3MarginSwapMarginRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateSwapContractOrder(WEB3GentradeSubAccount," +
            " WEB3MarginSwapContractOrderSpec, WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter = 
            (WEB3ToSuccMarginSwapMarginRequestAdapter) l_requestAdapter;
        boolean l_blnIsReversingOrder = l_toRequestAdapter.isReversingOrder();
        
        //�P�j�@@���Δ����̏ꍇ
        if (l_blnIsReversingOrder)
        {
            //�g�����������}�l�[�W��.validate�������n����()
            //���R�[������B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeNewOrderValidationResult l_result =
                l_orderManager.validateSwapContractOrder(l_subAccount, l_orderSpec, l_toRequestAdapter.getContract());
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.debug("�g�����������}�l�[�W��.validate�������n����()�����s�̏ꍇ�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�g�����������}�l�[�W��.validate�������n����()�����s�̏ꍇ�B");
            }
        }
        //�Q�j�@@�P�j�ȊO�̏ꍇ
        //�@@super.validate�������n����()���R�[������B
        else
        {
            super.validateSwapContractOrder(l_subAccount, l_orderSpec, l_requestAdapter);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�������n����)<BR>
     * �������n�̗\�񒍕���o�^����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �A�������}�l�[�W��Impl.submit�M�p�������n�V�K�\�񒍕�()���R�[������B<BR>
     * <BR>
     * [submit�M�p�������n�V�K�\�񒍕�()�Ɏw�肷�����]<BR>
     * �⏕�����F�@@�����̕⏕����<BR>
     * �������e�F�@@�����̐M�p�������n�������e<BR>
     * ����ID�F�@@�����̒���ID<BR>
     * ����p�X���[�h�F�@@�����̃p�X���[�h<BR>
     * �A����������敪�F�@@�����̃��N�G�X�g�A�_�v�^.���N�G�X�g.<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�A���������ʏ��.�A����������敪<BR>
     * �e�����̒����P�ʁF�@@�����̃��N�G�X�g�A�_�v�^.�e�����̒����P��<BR>
     * �T�Z��n����F�@@�����̊T�Z��n���<BR>
     * ���n�v���z�F�@@�����̏��n�v���z<BR>
     * ���n�v�Ŋz�F�@@�����̏��n�v�Ŋz<BR>
     * �����F�@@�����̃��N�G�X�g�A�_�v�^.get����()<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_orderSpec - (�M�p�������n�������e)<BR>
     * �M�p�������n�������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * �i�A���j�M�p����������n���N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@param l_dblEstimatedPrice - (�T�Z��n���)<BR>
     * �T�Z��n����B<BR>
     * @@param l_dblCapitalGain - (���n�v���z)<BR>
     * ���n�v���z�B<BR>
     * @@param l_dblCapitalGainTax - (���n�v�Ŋz)<BR>
     * ���n�v�Ŋz�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4344E34B000B
     */
    protected void submitSwapContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginSwapContractOrderSpec l_orderSpec,
        long l_lngOrderId, String l_strTradingPassword,
        WEB3MarginSwapMarginRequestAdapter l_requestAdapter,
        double l_dblEstimatedPrice,
        double l_dblCapitalGain,
        double l_dblCapitalGainTax) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitSwapContractOrder(WEB3GentradeSubAccount, WEB3MarginSwapContractOrderSpec," +
            " long, String, WEB3MarginSwapMarginRequestAdapter, double, double, double)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter = 
            (WEB3ToSuccMarginSwapMarginRequestAdapter) l_requestAdapter;
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        
        WEB3GenRequest l_request = l_toRequestAdapter.request;
        String l_strSuccTradingType = null;
        
        if (l_request instanceof WEB3SuccMarginSwapConfirmRequest)
        {
            WEB3SuccMarginSwapConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginSwapConfirmRequest) l_request;
            l_strSuccTradingType = l_confirmRequest.succCommonInfo.succTradingType;
        }
        else if (l_request instanceof WEB3SuccMarginSwapCompleteRequest)
        {
            WEB3SuccMarginSwapCompleteRequest l_completeRequest = 
                (WEB3SuccMarginSwapCompleteRequest) l_request;
            l_strSuccTradingType = l_completeRequest.succCommonInfo.succTradingType;
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
        
        //�A�������}�l�[�W��Impl.submit�M�p�������n�V�K�\�񒍕�()���R�[������B
        l_toOrderManager.submitEqtypeSwapContractNewOrder(
            l_subAccount,
            l_orderSpec,
            l_lngOrderId,
            l_strTradingPassword,
            l_strSuccTradingType,
            l_toRequestAdapter.parentOrderUnit,
            l_dblEstimatedPrice,
            l_dblCapitalGain,
            l_dblCapitalGainTax,
            l_toRequestAdapter.getContract());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate����]��)<BR>
     * ����]�͂��`�F�b�N���A����]�͌��ʃI�u�W�F�N�g��ԋp����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�����̃��N�G�X�g�A�_�v�^.get����()�ɂ��擾�B<BR>
     * <BR>
     * �Q�j�@@�擾��������.isLong()==false�i���n�j�̏ꍇ�́A<BR>
     * �@@�@@����������return����B�inull��ԋp�j<BR>
     * <BR>
     * �R�j�@@�擾��������.isLong()==true�i�����j�̏ꍇ�́A<BR>
     * �@@�@@�]�̓`�F�b�N�����{���镔�X(*3)�̏ꍇ�̂݁A<BR>
     * �@@�@@�����\�z(*1)�ƊT�Z��n���(*2)���r���A<BR>
     * �@@�@@�i�T�Z��n���(*2) > �����\�z(*1)�j�̏ꍇ�́A<BR>
     * �@@�@@�u����]�͕s���v�̗�O��throw����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@tag: BUSINESS_ERROR_00855<BR>
     * <BR>
     * �@@�@@�ȊO�Anull��ԋp����B<BR>
     * <BR>
     * (*1)���t�\�z<BR>
     * ����]�̓T�[�r�X.get�M�p�����\�z�`�A�������`(������<BR>
     * �⏕����, �����̎������.��n��)��<BR>
     * �߂�l���A�����\�z�Ƃ���B<BR>
     * <BR>
     * (*2)�T�Z��n���<BR>
     * �����̊T�Z��n����~�i�|�P�j���g�p�B<BR>
     * <BR>
     * (*3)�]�̓`�F�b�N�����{���镔�X<BR>
     * �A�������}�l�[�W��Impl.is�]�̓`�F�b�N���{���X()==true�̏ꍇ�́A<BR>
     * �]�̓`�F�b�N�����{���镔�X�ł���Ɣ��肷��B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_orderSpec - (�M�p�������n�������e)<BR>
     * �M�p�������n�������e�I�u�W�F�N�g�B<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g�B<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����������n���N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@param l_dblEstimatedPrice - (�T�Z��n���)<BR>
     * �T�Z��n����B<BR>
     * @@param l_dblCapitalGain - (���n�v���z)<BR>
     * ���n�v���z�B<BR>
     * @@param l_dblCapitalGainTax - (���n�v�Ŋz)<BR>
     * ���n�v�Ŋz�B<BR>
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * �]�͍X�V�t���O�B<BR>
     * �ifalse�F�@@�m�F���Atrue�F�@@�������j<BR>
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     * @@roseuid 4344EB53002A
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginSwapContractOrderSpec l_orderSpec,
        WEB3EquityTradedProduct l_tradedProduct,
        WEB3MarginSwapMarginRequestAdapter l_requestAdapter,
        double l_dblEstimatedPrice,
        double l_dblCapitalGain,
        double l_dblCapitalGainTax,
        boolean l_blnUpdateFlg) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateTradingPower(WEB3GentradeSubAccount, WEB3MarginSwapContractOrderSpec," +
            " WEB3EquityTradedProduct, WEB3MarginSwapMarginRequestAdapter, double, double, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����I�u�W�F�N�g���擾����B
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter = 
            (WEB3ToSuccMarginSwapMarginRequestAdapter) l_requestAdapter;
        WEB3EquityContract l_equityContract = l_toRequestAdapter.getContract();
        
        //�R�j�@@�擾��������.isLong()==true�i�����j�̏ꍇ�́A
        if (l_equityContract.isLong())
        {
            WEB3ToSuccOrderManagerImpl l_toOrderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            if (l_toOrderManager.isCheckTradingPowerBranch(l_subAccount) == false)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //(*1)�����\�z
            WEB3TPTradingPowerService l_tradingPowerService
                = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            double l_dblSuccActualReceiptTradingPower = l_tradingPowerService.getSuccActualReceiptTradingPower(
                l_subAccount, l_tradedProduct.getDailyDeliveryDate());
            
            //�����\�z(*1)�ƊT�Z��n���(*2)���r���A
            //�i�T�Z��n���(*2) > �����\�z(*1)�j�̏ꍇ�́A
            //�u����]�͕s���v�̗�O��throw����B
            if (l_dblEstimatedPrice * (-1) > l_dblSuccActualReceiptTradingPower)
            {
                log.debug("����]�͕s���G���[�B�i�M�p�����\�z�s���j");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00855,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����]�͕s���G���[�B�i�M�p�����\�z�s���j");
            }
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * get��������<BR>
     * <BR>
     * �������� �Ƃ��āA�ȉ��̒l���g�p����B<BR>
     * �����߂��������� > ���N�G�X�g�A�_�v�^.�e�����̒����P��.�������ʂ̏ꍇ�A<BR>
     * �@@�@@�@@�u�����������e�����̒������ʂ𒴉߁v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_02290<BR>
     * <BR>
     * ----------------------------------------------------------------------<BR>
     * �@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���Ϗ����敪��"�����_��"�̏ꍇ�́A<BR>
     * �@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�������� ���g�p�B<BR>
     * �@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���Ϗ����敪=="�����_��"�̏ꍇ�́A<BR>
     * �@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���ό����ꗗ�̑S�v�f��<BR>
     * �@@�@@�@@����������SUM�l���g�p�B<BR>
     * <BR>
     * ----------------------------------------------------------------------<BR>
     * <BR>
     * @@param l_toRequestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * �i�A���j�M�p����������n���N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    private double getOrderQuantityCnt(
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderQuantityCnt(WEB3ToSuccMarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblOrderCnt = 0;
        WEB3GenRequest l_request = l_toRequestAdapter.request;
        
        String l_strClosingOrder = null;
        String l_strOrderQuantity = null;
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits = null;
        
        if (l_request instanceof WEB3SuccMarginSwapConfirmRequest)
        {
            WEB3SuccMarginSwapConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginSwapConfirmRequest) l_request;
            l_strClosingOrder = l_confirmRequest.closingOrder;
            l_strOrderQuantity = l_confirmRequest.orderQuantity;
            l_closeMarginContractUnits = l_confirmRequest.closeMarginContractUnits;

        }
        else if(l_request instanceof WEB3SuccMarginSwapCompleteRequest)
        {
            WEB3SuccMarginSwapCompleteRequest l_completeRequest = 
                (WEB3SuccMarginSwapCompleteRequest) l_request;
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
