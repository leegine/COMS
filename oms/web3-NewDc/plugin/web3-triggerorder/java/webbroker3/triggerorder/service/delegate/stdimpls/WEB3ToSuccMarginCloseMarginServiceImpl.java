head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginCloseMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����ԍσT�[�r�XImpl(WEB3ToSuccMarginCloseMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/18 ���@@��(���u) �V�K�쐬
Revesion History : 2007/01/11 �  ��(���u) �d�l�ύX���f��216
Revesion History : 2007/01/17 �  ��(���u) �d�l�ύX���f��222
Revesion History : 2007/08/08 ���g(���u) �d�l�ύX���f��1192
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

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
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginRequestAdapter;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginCloseMarginServiceImpl;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPAttentionObjection;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCloseMarginRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCloseMarginService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�i�A���j�M�p����ԍσT�[�r�XImpl)<BR>
 * �i�A���j�M�p����ԍσT�[�r�X�����N���X<BR>
 * 
 * @@author ���@@��(���u)
 * @@version 1.0
 */
public class WEB3ToSuccMarginCloseMarginServiceImpl extends 
    WEB3MarginCloseMarginServiceImpl implements WEB3ToSuccMarginCloseMarginService 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginCloseMarginServiceImpl.class);
    
    /**
     * @@roseuid 436ACF7E01D4
     */
    public WEB3ToSuccMarginCloseMarginServiceImpl() 
    {
     
    }
    
    /**
     * �i�A���j�M�p����ԍσT�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * [�i�A���j�M�p����ԍϒ����m�F���N�G�X�g�̏ꍇ]<BR>
     * �@@this.validate����()���R�[������B<BR>
     * [�i�A���j�M�p����ԍϒ����������N�G�X�g�̏ꍇ]<BR>
     * �@@this.submit����()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4332445D0170
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
        if (l_request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccMarginCloseConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3SuccMarginCloseCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccMarginCloseCompleteRequest) l_request);
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
     * �i�A���j�M�p����ԍϔ����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�M�p����ԍσT�[�r�X�jvalidate�����v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccMarginCloseConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 433245F10096
     */
    protected WEB3SuccMarginCloseConfirmResponse validateOrder(
        WEB3SuccMarginCloseConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3SuccMarginCloseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 get�����e�����̒����P��(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(
            Long.parseLong(l_request.succCommonInfo.parentOrderId));
        
        //1.3 get�⏕����
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //1.4 reset�s��R�[�h(�s��R�[�h : String)
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdaptor = 
            (WEB3ToSuccMarginCloseMarginRequestAdapter) this.createRequestAdapter(l_request);
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        long l_lngMarketId = 0L;
        if (l_toRequestAdaptor.isReversingOrder())
        {
            l_lngMarketId = l_orderUnitRow.getMarketId();
        }
        else
        {
            l_lngMarketId = l_toRequestAdaptor.getContract().getMarketId();
        }
        
        try
        {
            Market l_market = l_objectManager.getMarket(l_lngMarketId);
            String l_strMarketCode = l_market.getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        
        //1.5 validate�A������(�⏕����, ProductTypeEnum, String, String, OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount, 
            ProductTypeEnum.EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT, 
            l_request.succCommonInfo.succTradingType, 
            l_orderUnit);
        
        //1.6 validate�A�������ő�ݒ萔(�����P��)
        this.validateSuccOrderMaxQuantity(l_orderUnit);
        
        //1.7 validate����
        WEB3SuccMarginCloseConfirmResponse l_response = (
            WEB3SuccMarginCloseConfirmResponse) super.validateOrder(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * �i�A���j�M�p����ԍϒ����o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�M�p����ԍσT�[�r�X�jsubmit�����v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccMarginCloseCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 433245F10180
     */
    protected WEB3SuccMarginCloseCompleteResponse submitOrder(
        WEB3SuccMarginCloseCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3SuccMarginCloseCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 get�����e�����̒����P��(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(
            Long.parseLong(l_request.succCommonInfo.parentOrderId));
        
        //1.3 get�⏕����
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //1.4 reset�s��R�[�h(�s��R�[�h : String)
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdaptor = 
            (WEB3ToSuccMarginCloseMarginRequestAdapter) this.createRequestAdapter(l_request);
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        
        long l_lngMarketId = 0L;
        if (l_toRequestAdaptor.isReversingOrder())
        {
            l_lngMarketId = l_orderUnitRow.getMarketId();
        }
        else
        {
            l_lngMarketId = l_toRequestAdaptor.getContract().getMarketId();
        }
        
        try
        {
            Market l_market = l_objectManager.getMarket(l_lngMarketId);
            String l_strMarketCode = l_market.getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        
        //1.5 validate�A������(�⏕����, ProductTypeEnum, String, String, OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount, 
            ProductTypeEnum.EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT, 
            l_request.succCommonInfo.succTradingType, 
            l_orderUnit);
        
        //1.6 validate�A�������ő�ݒ萔(�����P��)
        this.validateSuccOrderMaxQuantity(l_orderUnit);
        
        //1.7 submit����
        WEB3SuccMarginCloseCompleteResponse l_response = (
            WEB3SuccMarginCloseCompleteResponse) super.submitOrder(l_request);
        
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
     * �i�A���j�M�p����ԍσ��N�G�X�g�A�_�v�^.create<BR>
     * (�����̃��N�G�X�g)���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3MarginCloseMarginRequestAdapter
     * @@roseuid 433246330067
     */
    protected WEB3MarginCloseMarginRequestAdapter createRequestAdapter(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = " createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginCloseMarginRequestAdapter l_marginRequestAdapter = 
            WEB3ToSuccMarginCloseMarginRequestAdapter.create(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_marginRequestAdapter;
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
     *   �u�����������e�����̒������ʂ𒴉߁v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_02290<BR>
     * �@@�@@------------------------------------------------------<BR>
     * �@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���Ϗ����敪��"�����_��"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�������� ���g�p�B<BR>
     * �@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���Ϗ����敪=="�����_��"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���ό����ꗗ��<BR>
     * �@@�@@�@@�@@�@@�S�v�f�̒���������SUM�l���g�p�B<BR>
     * �@@�@@--------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�P�j�ȊO�̏ꍇ�A<BR>
     * �@@super.create���ό����G���g��()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[super.create���ό����G���g��()�Ɏw�肷�����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * @@param l_closeMarginContractUnits - (���ό������׈ꗗ)<BR>
     * �M�p������ό����I�u�W�F�N�g�̔z��B<BR>
     * �i���N�G�X�g�f�[�^�j<BR>
     * @@param l_requestAdaptor - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g<BR>
     * @@return EqTypeSettleContractOrderEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 433246330113
     */
    protected EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits, 
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createClosingContractEntry(" +
            "WEB3MarginCloseMarginContractUnit[], " +
            "WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
     
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntries = null;
        
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdapter = 
            (WEB3ToSuccMarginCloseMarginRequestAdapter) l_requestAdaptor;
        
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
                super.createClosingContractEntry(l_closeMarginContractUnits, l_requestAdaptor);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntries;
    }
    
    /**
     * (get�T�Z���ϑ��v���)<BR>
     * �T�Z���ϑ��v������擾����B<BR>
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
     * �@@�P�|�Q�j�@@�g�����������}�l�[�W��.calc�T�Z���ϑ��v���()���R�[�����A<BR>
     * �@@�@@�@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@�@@[calc�T�Z���ϑ��v���()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�萔���F�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@�w�l�F�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@�⏕�����F�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@��������F�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@���ό����G���g���F �@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@���ʁF�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@�����萔�ʁF�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@������P���F�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@isSkip���z�`�F�b�N�F�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@�����F�@@���Δ����̏ꍇ�A�P�|�P�j�̖߂�l�B�ȊO�Anull�B<BR>
     * <BR>
     * �Q�j�@@�����c�ɑ΂���ԍρi�P�j�ȊO�j�̏ꍇ�A<BR>
     * �@@super.get�T�Z���ϑ��v���()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[get�T�Z���ϑ��v���()�Ɏw�肷�����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * @@param l_genCommission - (�萔��)<BR>
     * �萔���I�u�W�F�N�g�B<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �w�l�B<BR>
     * ���s�̏ꍇ��0���Z�b�g�B<BR>
     * 
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_equityTradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g�B<BR>
     * @@param l_settleContractOrderEntries - (���ό����G���g��)<BR>
     * ���ό����G���g���̔z��<BR>
     * @@param l_dblQuantity - (����)<BR>
     * ����<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �������^���Ώہ^������Ώے����̒����P�ʃI�u�W�F�N�g<BR>
     * �i�V�K�̒����o�^����null���Z�b�g�j<BR>
     * @@param l_dblNowExecQuantity - (�����萔��)<BR>
     * �����萔��<BR>
     * �i���^������̏ꍇ�ɕҏW�j<BR>
     * 
     * @@param l_dblNowExecPrice - (������P��)<BR>
     * ������P��<BR>
     * �i���^������̏ꍇ�ɕҏW�j<BR>
     * @@param l_blnIsSkipAmountRange - (isSkip���z�`�F�b�N)<BR>
     * �v�Z���ʂ̑���ɂ��āA�Ó����`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * <BR>
     * @@param l_requestAdaptor - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g<BR>
     * �`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j<BR>
     * �ꍇ��true���w�肷��B<BR>
     * 
     * @@return WEB3EquityRealizedProfitAndLossPrice
     * @@throws WEB3BaseException
     * @@roseuid 43422354027B
     */
    protected WEB3EquityRealizedProfitAndLossPrice getEstimatedRealizedProfitAndLossAmount(
        WEB3GentradeCommission l_genCommission, 
        double l_dblLimitPrice, 
        SubAccount l_subAccount, 
        WEB3EquityTradedProduct l_equityTradedProduct, 
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntries, 
        double l_dblQuantity, 
        EqTypeOrderUnit l_orderUnit, 
        double l_dblNowExecQuantity, 
        double l_dblNowExecPrice, 
        boolean l_blnIsSkipAmountRange,
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getEstimatedRealizedProfitAndLossAmount(" +
            " WEB3GentradeCommission, " +
            " double," +
            " SubAccount," +
            " WEB3EquityTradedProduct," +
            " EqTypeSettleContractOrderEntry," +
            " double," +
            " EqTypeOrderUnit," +
            " double," +
            " double," +
            " boolean," +
            " WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdaptor = (
            WEB3ToSuccMarginCloseMarginRequestAdapter) l_requestAdaptor;
        
        WEB3EquityRealizedProfitAndLossPrice l_profitAndLossPrice = null;
        //�P�j�@@���Δ����̏ꍇ
        if (l_toRequestAdaptor.isReversingOrder())
        {
            //�P�|�P�j�@@�A�������}�l�[�W��Impl.create����()���R�[�����A�������쐬����B
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            
            WEB3EquityContract l_equityContract = l_toOrderManager.createContract(l_toRequestAdaptor.parentOrderUnit);
            
            //�P�|�Q�j�@@�g�����������}�l�[�W��.calc�T�Z���ϑ��v���()���R�[�����A�߂�l��ԋp����B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            
            l_profitAndLossPrice = l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                l_genCommission,
                l_dblLimitPrice,
                (WEB3GentradeSubAccount) l_subAccount,
                l_equityTradedProduct,
                l_settleContractOrderEntries,
                l_dblQuantity,
                l_orderUnit,
                l_dblNowExecQuantity,
                l_dblNowExecPrice,
                l_blnIsSkipAmountRange,
                l_equityContract); 
        }
        else 
        {
            //�Q�j�@@�����c�ɑ΂���ԍρi�P�j�ȊO�j�̏ꍇ�A super.get�T�Z���ϑ��v���()���R�[�����A�߂�l��ԋp����B
            l_profitAndLossPrice = super.getEstimatedRealizedProfitAndLossAmount(
                l_genCommission, 
                l_dblLimitPrice, 
                l_subAccount, 
                l_equityTradedProduct, 
                l_settleContractOrderEntries, 
                l_dblQuantity, 
                l_orderUnit, 
                l_dblNowExecQuantity, 
                l_dblNowExecPrice, 
                l_blnIsSkipAmountRange,
                l_requestAdaptor);   
        }
        log.exiting(STR_METHOD_NAME);
        return l_profitAndLossPrice;
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
     * �Q�j�@@�����c�ɑ΂���ԍρi�P�j�ȊO�j�̏ꍇ�A<BR>
     * �@@super.create�������׈ꗗ()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[create�������׈ꗗ()�Ɏw�肷�����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * @@param l_settleContractOrderEntries - (���ό����G���g��)<BR>
     * ���ό����G���g���̔z��<BR>
     * @@param l_dblUnitPrice - (�v�Z�P��)<BR>
     * �v�Z�P��<BR>
     * @@param l_requestAdaptor - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.equity.message.WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4332463301FD
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntries, 
        double l_dblUnitPrice, 
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createMarginContractUnitList(" +
            " EqTypeSettleContractOrderEntry[]," +
            " double," +
            " WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginContractUnit[] l_marginContractUnits = null;
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdaptor = (
            WEB3ToSuccMarginCloseMarginRequestAdapter) l_requestAdaptor;
        
        //�P�j�@@���Δ����̏ꍇ
        if (l_toRequestAdaptor.isReversingOrder())
        {
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            
            //�P�|�P�j�@@�A�������}�l�[�W��Impl.create��������()���@@�R�[������
            WEB3MarginContractUnit l_marginContractUnit = 
                l_toOrderManager.createMarginContractUnit(l_toRequestAdaptor.parentOrderUnit);
            
            //�P�|�Q�j�@@�v���p�e�B�Z�b�g�����C���X�^���X�݂̂�v�f�Ƃ���z��𐶐����A�ԋp����
            l_marginContractUnits = new WEB3MarginContractUnit[] {l_marginContractUnit};
        }
        else
        {
            // �Q�j �����c�ɑ΂���ԍρi�P�j�ȊO�j�̏ꍇ�Asuper.create�������׈ꗗ()���R�[�����A�߂�l��ԋp����
            l_marginContractUnits = super.createMarginContractUnitList(
                l_settleContractOrderEntries, l_dblUnitPrice, l_requestAdaptor);
        }
        log.exiting(STR_METHOD_NAME);
        return l_marginContractUnits;
    }
    
    /**
     * (validate�ԍϒ���)<BR>
     * �M�p����ԍϒ��������R�����s���B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@���Δ����̏ꍇ<BR>
     * �@@�i�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true�j<BR>
     * �@@�g�����������}�l�[�W��.validate�ԍϒ���()<BR>
     * �@@���R�[������B<BR>
     * <BR>
     * �@@[validate�ԍϒ���()�Ɏw�肷�����]<BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�@@�M�p�ԍϒ������e�F�@@�p�����[�^.�M�p�ԍϒ������e<BR>
     * �@@�@@�����F�@@�p�����[�^.���N�G�X�g�A�_�v�^.get����()<BR>
     * <BR>
     * �Q�j�@@�P�j�ȊO�̏ꍇ<BR>
     * �@@super.validate�ԍϒ���()���R�[������B<BR>
     * <BR>
     * �@@[validate�ԍϒ���()�Ɏw�肷�����]<BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�@@�M�p�ԍϒ������e�F�@@�p�����[�^.�M�p�ԍϒ������e<BR>
     * �@@�@@���N�G�X�g�A�_�v�^�F�@@�p�����[�^.���N�G�X�g�A�_�v�^<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_orderSpec - (�M�p�ԍϒ������e)<BR>
     * �M�p�ԍϒ������e�I�u�W�F�N�g<BR>
     * @@param l_requestAdaptor - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g<BR>
     * @@return EqTypeNewOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 433246330316
     */
    protected EqTypeNewOrderValidationResult validateSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount, 
        EqTypeSettleContractOrderSpec l_orderSpec, 
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateSettleContractOrder(" +
            " WEB3GentradeSubAccount," +
            " EqTypeSettleContractOrderSpec," +
            " WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdaptor = (
            WEB3ToSuccMarginCloseMarginRequestAdapter) l_requestAdaptor;
        
        //�P�j�@@���Δ����̏ꍇ
        if (l_toRequestAdaptor.isReversingOrder())
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            
            EqTypeNewOrderValidationResult l_result =
                l_orderManager.validateSettleContractOrder(l_subAccount, l_orderSpec, l_toRequestAdaptor.getContract());

            log.exiting(STR_METHOD_NAME);
            return l_result;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return super.validateSettleContractOrder(l_subAccount, l_orderSpec, l_toRequestAdaptor);
        }
    }
    
    /**
     * (submit�ԍϒ���)<BR>
     * �\�񒍕���o�^����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �A�������}�l�[�W��Impl.submit�M�p�ԍϐV�K�\�񒍕�()���R�[������B<BR>
     * <BR>
     * [submit�M�p�ԍϐV�K�\�񒍕�()�Ɏw�肷�����]<BR>
     * �⏕�����F�@@�����̕⏕����<BR>
     * �������e�F�@@�����̐M�p�ԍϒ������e<BR>
     * ����ID�F�@@�����̒���ID<BR>
     * ����p�X���[�h�F�@@�����̃p�X���[�h<BR>
     * �A����������敪�F�@@�����̕ԍσ��N�G�X�g�A�_�v�^.���N�G�X�g.<BR>
     * �A���������ʏ��.�A����������敪<BR>
     * �P�������l�F�@@�����̕ԍσ��N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���.get�P�������l()<BR>
     * �@@�@@�������̕ԍσ��N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���==null�̏ꍇ�́Anull���Z�b�g<BR>
     * �e�����̒����P�ʁF�@@�����̕ԍσ��N�G�X�g�A�_�v�^.�e�����̒����P��<BR>
     * �v�Z���ʁF�@@�����̌v�Z����<BR>
     * �����F�@@�����̕ԍσ��N�G�X�g�A�_�v�^.get����()<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_orderSpec - (�M�p�ԍϒ������e)<BR>
     * �M�p�ԍϒ������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_commission - (�萔��)<BR>
     * �萔���I�u�W�F�N�g�B<BR>
     * @@param l_profitAndLossCalcResult - (�v�Z����)<BR>
     * �T�Z���ϑ��v����v�Z���ʃI�u�W�F�N�g�B<BR>
     * @@param l_requestAdaptor - (�ԍσ��N�G�X�g�A�_�v�^)<BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433246340047
     */
    protected void submitSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3MarginSettleContractOrderSpec l_orderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        WEB3GentradeCommission l_commission, 
        WEB3EquityRealizedProfitAndLossPrice l_profitAndLossCalcResult, 
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitSettleContractOrder(" +
            " WEB3GentradeSubAccount," +
            " WEB3MarginSettleContractOrderSpec," +
            " long," +
            " String," +
            " WEB3GentradeCommission," +
            " WEB3EquityRealizedProfitAndLossPrice," +
            " WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        
        WEB3ToSuccMarginCloseMarginRequestAdapter l_closeMarginRequestAdapter = (
            WEB3ToSuccMarginCloseMarginRequestAdapter) l_requestAdaptor;
        
        WEB3GenRequest l_request = l_closeMarginRequestAdapter.request;
        String l_strSuccTradingType = null;
        Double l_priceAdjustValue = null;
       
        if (l_request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            WEB3SuccMarginCloseConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginCloseConfirmRequest) l_request;
            l_strSuccTradingType = l_confirmRequest.succCommonInfo.succTradingType;
            
            if (l_confirmRequest.priceAdjustmentValueInfo != null)
            {
                //�����̕ԍσ��N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���.get�P�������l()
                l_priceAdjustValue = new Double(l_confirmRequest.priceAdjustmentValueInfo.getPriceAdjustmentValue());
            }
        }
        else if (l_request instanceof WEB3SuccMarginCloseCompleteRequest)
        {
            WEB3SuccMarginCloseCompleteRequest l_completeRequest = 
                (WEB3SuccMarginCloseCompleteRequest) l_request;
            l_strSuccTradingType = l_completeRequest.succCommonInfo.succTradingType;
            
            if (l_completeRequest.priceAdjustmentValueInfo != null)
            {
                //�����̕ԍσ��N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���.get�P�������l()
                l_priceAdjustValue = new Double(l_completeRequest.priceAdjustmentValueInfo.getPriceAdjustmentValue());
            }
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

        //�A�������}�l�[�W��Impl.submit�M�p�ԍϐV�K�\�񒍕�()���R�[������
        l_toOrderManager.submitEqtypeCloseContractNewOrder(
            l_subAccount, 
            l_orderSpec, 
            l_lngOrderId, 
            l_strTradingPassword, 
            l_strSuccTradingType, 
            l_priceAdjustValue,
            l_closeMarginRequestAdapter.parentOrderUnit,
            l_profitAndLossCalcResult,
            l_closeMarginRequestAdapter.getContract());

        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (validate�A�������ő�ݒ萔)<BR>
     * �A�������̍ő�ݒ萔�𒴉߂��Ă��܂�Ȃ����ǂ������`�F�b�N����B<BR>
     * <BR>
     * �A�������}�l�[�W��.validate�A�������ő�ݒ萔<BR>
     * (�����̐e�����̒����P��)��delegate����B<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433D01F10355
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
     * �@@�@@�����F�@@NEW_SETTLE_CONTRACT_ORDER<BR>
     * @@param l_lngSubOrderId - (�q�����̒���ID)<BR>
     * �q�����̒���ID�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 435EDE190096
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
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
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
        
        l_orderMgr.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER);
        log.exiting(STR_METHOD_NAME);  
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

    /**
     * (create�ԍώ����ӕ���)<BR>
     * ���X�|���X�ɐݒ肷��A�ԍώ����ӕ������擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * null��return����B�i�J�������j<BR>
     * <BR>
     * @@param�@@l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_updateInterceptor - (�M�p�ԍύX�V�C���^�Z�v�^)<BR>
     * �M�p�ԍύX�V�C���^�Z�v�^
     * @@param l_orderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e
     * @@return WEB3TPAttentionObjection
     */
    protected WEB3TPAttentionObjection createCloseMarginAttentionWording(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginCloseMarginUpdateInterceptor l_updateInterceptor,
        WEB3MarginSettleContractOrderSpec l_orderSpec)

    {
        return null;
    }

    /**
     * (exec�]�͍Čv�Z)<BR>
     * �]�͍Čv�Z���s���B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * <BR>
     * @@param�@@l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     */
    protected void execReCalcTradingPower(WEB3GentradeSubAccount l_subAccount)
    {

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
     * @@param l_adapter - (�M�p����ԍσ��N�G�X�g�A�_�v�^)<BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�B<BR>
     * @@param l_response - (���X�|���X)<BR>
     * ���X�|���X�B<BR>
     * @@throws WEB3BaseException 
     */
    protected void setPrice(WEB3MarginCloseMarginRequestAdapter l_adapter,
        WEB3GenResponse l_response) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setPrice(WEB3MarginCloseMarginRequestAdapter, " 
            + "WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginCloseConfirmRequest l_confirmRequest = null;

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
        
        if (l_request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            l_confirmRequest = (WEB3SuccMarginCloseConfirmRequest)l_adapter.request;
            
            //�P�j���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ 
            if (l_confirmRequest.priceAdjustmentValueInfo != null)
            {
                WEB3SuccMarginCloseConfirmResponse l_confirmResponse = 
                    (WEB3SuccMarginCloseConfirmResponse)l_response;
                
                //���X�|���X�D������P���ɁA�����̃��N�G�X�g�A�_�v�^�Dget�P��()�̖߂�l���Z�b�g����B 
                l_confirmResponse.afterAdjustmentPrice = 
                    WEB3StringTypeUtility.formatNumber(l_adapter.getPrice());
            }
        }

        log.exiting(STR_METHOD_NAME);  
    }
}
@
