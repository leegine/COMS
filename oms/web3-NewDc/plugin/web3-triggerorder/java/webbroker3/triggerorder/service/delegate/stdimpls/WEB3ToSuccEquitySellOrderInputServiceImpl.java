head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquitySellOrderInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����������t���̓T�[�r�XImpl(WEB3ToSuccEquitySellOrderInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 �A�C��(���u) �V�K�쐬
Revesion History : 2007/01/20 �юu��(���u) �d�l�ύX���f��No.224
Revesion History : 2007/12/17 ��іQ(���u) �d�l�ύX���f��No.246
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.message.WEB3EquitySellInputRequest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquitySellOrderInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradePriceCondDef;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccEquitySellInputRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquitySellOrderInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�����������t���̓T�[�r�XImpl)<BR>
 * �i�A���j�����������t���̓T�[�r�X�����N���X�B
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3ToSuccEquitySellOrderInputServiceImpl extends WEB3EquitySellOrderInputServiceImpl 
    implements WEB3ToSuccEquitySellOrderInputService 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquitySellOrderInputServiceImpl.class);
    
    /**
     * @@roseuid 4348EC75038A
     */
    public WEB3ToSuccEquitySellOrderInputServiceImpl() 
    {
     
    }
    
    /**
     * �i�A���j�����������t�������̓T�[�r�X���������{����B<BR>
     * <BR>
     * get���͉��()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^�B
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43265B4E00AA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3SuccEquitySellInputRequest)
        {
            l_response = this.getInputScreen((WEB3SuccEquitySellInputRequest) l_request);
        }
        else
        {
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
     * (get���͉��)<BR>
     * �i�A���j�����������t�������͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�i�A���j�����������t�������́jget���͉�ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B
     * 
     * @@return WEB3SuccEquitySellInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 43265B4E00CA
     */
    protected WEB3SuccEquitySellInputResponse getInputScreen(WEB3SuccEquitySellInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getInputScreen(WEB3SuccEquitySellInputRequest )";
        log.entering(STR_METHOD_NAME);

        WEB3SuccEquitySellInputResponse l_response = null;        
        //1.1 validate( )
        l_request.validate();

        //1.2 get�����e�����̒����P��(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_request.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�Y���f�[�^�Ȃ�");
        }

        //1.3 get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        if (l_request.marketCode == null)
        {
            // reset�s��R�[�h
            WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);
        }
        
        //1.4 validate�A������(�⏕����, ProductTypeEnum, String, String, OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_request.succCommonInfo.succTradingType,
            l_orderUnit);

        //1.5 validate�A�������ő�ݒ萔(OrderUnit)
        l_toOrderManager.validateSuccOrderMaxQuantity(l_orderUnit);
        
        if (l_request.marketCode == null)
        {
            // reset�s��R�[�h
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);
        }
        
        //1.6  get���͉��(���N�G�X�g�f�[�^ : �����������t�������̓��N�G�X�g)
        l_response = (WEB3SuccEquitySellInputResponse)super.getSellInputScreen(l_request); //WEB3BaseException

        //1.7 is���Δ������(String, OrderUnit)
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_request.succCommonInfo.succTradingType, 
            l_orderUnit);
            
        //1.8 (*) �v���p�e�B�Z�b�g
        //���قȂ�l���Z�b�g����v���p�e�B�i�ăZ�b�g�j
        //�l�i�����ꗗ�F�@@"�w��Ȃ�"�݂̂��Z�b�g�B
        l_response.priceCondList = new String[]{WEB3GentradePriceCondDef.DEFAULT};
        
        //���s�����ꗗ�F�@@"������"�݂̂��Z�b�g�B
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};

        //�v�w�l�p���s�����ꗗ�F�@@null���Z�b�g�B
        l_response.wlimitExecCondList = null;
        
        //�����敪�F�@@
        // �����Δ����̏ꍇ
        //  �e�����̒����P��.�������=="����������"�̏ꍇ�́A
        //  �i�e�����̒����P��.�ŋ敪�j�ɊY����������敪�݂̂��Z�b�g�B
        //  �e�����̒����P��.�������=="��������"�̏ꍇ�́A
        //  �i�e�����̒����P��.�ŋ敪�i�������n�j�j�ɊY����������敪�݂̂��Z�b�g�B
        //  ���ŋ敪=="���"�̏ꍇ�́A"���"�B�ȊO�A"����"�B
        // ����L�ȊO�̏ꍇ
        //  �i���X�|���X.�����敪�j�����̂܂܃Z�b�g�B
        if (l_blnReversingTrade)
        {
            if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnit.getOrderType()))
            {
                if (TaxTypeEnum.NORMAL.equals(l_orderUnit.getTaxType()))
                {
                    l_response.taxType = WEB3AccountDivDef.NORMAL;
                }
                else
                {
                    l_response.taxType = WEB3AccountDivDef.SPECIAL;
                }
            }
            else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderUnit.getOrderType()))
            {
                EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                if (TaxTypeEnum.NORMAL.equals(l_row.getSwapTaxType()))
                {
                    l_response.taxType = WEB3AccountDivDef.NORMAL;
                }
                else
                {
                    l_response.taxType = WEB3AccountDivDef.SPECIAL;
                }
            }
        }
        
        //���������敪�ꗗ�F�@@"�w��Ȃ�"�݂̂��Z�b�g�B
        l_response.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};

        //���������F�@@
        // �����Δ����̏ꍇ
        //  �i�e�����̒����P��.�������ʁj���Z�b�g�B
        // ����L�ȊO�̏ꍇ
        //  �i���X�|���X.���������j�����̂܂܃Z�b�g�B
        //�T�Z�뉿�P���F
        // �����Δ����̏ꍇ
        //  null���Z�b�g�B
        // ����L�ȊO�̏ꍇ
        //  �i���X�|���X.�T�Z�뉿�P���j�����̂܂܃Z�b�g�B
        if (l_blnReversingTrade)
        {
            l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
            l_response.estimatedBookPrice = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�ۗL���Y)<BR>
     * �ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@���Ύ��(*1)�̏ꍇ<BR>
     * �@@�@@�A�������}�l�[�W��Impl.create�ۗL���Y(�e�����̒����P��(*2))��delegate����B<BR>
     * <BR>
     * �Q�j�@@���Ύ���łȂ��ꍇ<BR>
     * �@@�@@�����|�W�V�����}�l�[�W��.getAsset(���N�G�X�g.ID�i�����YID�j)��delegate����B<BR>
     * <BR>
     * (*1)���Ύ��<BR>
     * �@@�A�������}�l�[�W��Impl.is���Δ������(<BR>
     * �@@���N�G�X�g.�A���������ʏ��.�A����������敪, �e�����̒����P��(*2))<BR>
     * �@@==true�̏ꍇ�́A���Ύ���B<BR>
     * �@@false�̏ꍇ�́A���Ύ���łȂ��B<BR>
     * <BR>
     * (*2)�e�����̒����P��<BR>
     * �@@�A�������}�l�[�W��Impl.get�����e�����̒����P��<BR>
     * (���N�G�X�g.�A���������ʏ��.�i�e�����j����ID)��<BR>
     * �@@�擾�B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return Asset
     * @@throws WEB3BaseException
     * @@roseuid 43295F390264
     */
    protected Asset getAsset(WEB3EquitySellInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getAsset(WEB3EquitySellInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccEquitySellInputRequest l_succRequest = 
            (WEB3SuccEquitySellInputRequest)l_request;
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = 
            Long.parseLong(l_succRequest.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = 
            l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_succRequest.succCommonInfo.succTradingType, 
            l_orderUnit);
        //�P�j�@@���Ύ��(*1)�̏ꍇ
        //�@@�@@�A�������}�l�[�W��Impl.create�ۗL���Y(�e�����̒����P��(*2))��delegate����B
        Asset l_asset = null;
        if (l_blnReversingTrade)
        {
            l_asset = l_toOrderManager.createAsset(l_orderUnit);
        }
        else
        {
            //�Q�j�@@���Ύ���łȂ��ꍇ
            //�@@�@@�����|�W�V�����}�l�[�W��.getAsset(���N�G�X�g.ID�i�����YID�j)��delegate����B
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            try
            {
                l_asset = l_positionManager.getAsset(Long.parseLong(l_succRequest.id));
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_asset;
    }
    
    /**
     * (get�T�Z�뉿�P��)<BR>
     * �T�Z�뉿�P�����擾���Ԃ��B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�e�����̔��Δ���������ǂ����𔻒肷��B<BR>
     * <BR>
     * �@@�@@�A�������}�l�[�W��Impl.is���Δ������()==true�i���Δ����j�̏ꍇ�́A<BR>
     * �@@�@@null��ԋp����B<BR>
     * <BR>
     * �@@�@@-------------------------------------------------------<BR>
     * �@@�@@��is���Δ������()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�A����������敪�F�@@�����̃��N�G�X�g.�A���������ʏ��.�A����������敪<BR>
     * �@@�@@�e�����̒����P�ʁF�@@�����̃��N�G�X�g.�A���������ʏ��.�i�e�����j����ID��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Y������e�����̒����P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�@@�@@���A�������}�l�[�W��Impl.get�����e�����̒����P��(�i�e�����j����ID)<BR>
     * �Ŏ擾�B<BR>
     * �@@�@@-------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@���Δ����łȂ��ꍇ�A�ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�����|�W�V�����}�l�[�W��.getAsset(�����̃��N�G�X�g.ID)���R�[������B<BR>
     * <BR>
     * �R�j�@@�����v�Z�T�[�r�X.calc�T�Z�뉿�P��(�ۗL���Y.����ID, get�⏕����(), <BR>
     * �ۗL���Y.�ŋ敪)���R�[�����A<BR>
     * �@@�@@�@@�߂�l��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 433A2FFF028D
     */
    protected String getEstimatedBookPrice(WEB3EquitySellInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getEstimatedBookPrice(WEB3EquitySellInputRequest )";
        log.entering(STR_METHOD_NAME);

        WEB3SuccEquitySellInputRequest l_succRequest = (WEB3SuccEquitySellInputRequest)l_request;

        //�P�j�@@�e�����̔��Δ���������ǂ����𔻒肷��B
        //�@@�@@�A�������}�l�[�W��Impl.is���Δ������()==true�i���Δ����j�̏ꍇ�́A
        //�@@�@@null��ԋp����B
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = 
            Long.parseLong(l_succRequest.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = 
            l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_succRequest.succCommonInfo.succTradingType, 
            l_orderUnit);
        Asset l_asset = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        if (l_blnReversingTrade)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            //�Q�j�@@���Δ����łȂ��ꍇ�A�ۗL���Y�I�u�W�F�N�g���擾����B
            //�@@�����|�W�V�����}�l�[�W��.getAsset(�����̃��N�G�X�g.ID)���R�[������B
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            try
            {
                l_asset = l_positionManager.getAsset(Long.parseLong(l_succRequest.id));
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        
        //�R�j�@@�����v�Z�T�[�r�X.calc�T�Z�뉿�P��(�ۗL���Y.����ID, get�⏕����(), 
        //�ۗL���Y.�ŋ敪)���R�[�����A
        //�@@�@@�@@�߂�l��ԋp����B
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblEstimatedBookPrice =  l_bizLogicProvider.calcEstimatedBookPrice(
            l_asset.getProduct().getProductId(),
            this.getSubAccount(),
            l_asset.getTaxType());
        String l_strEstimatedBookPrice =
            WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
            
        log.exiting(STR_METHOD_NAME);
        return l_strEstimatedBookPrice;
    }

    /**
     * (isPTS�����J��)<BR>
     * �ڋq.isPTS�����J�݂��R�[�����A���ʂ�ԋp���� <BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * (������) <BR>
     * false��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isPTSAccountOpen(
        WEB3GentradeMainAccount l_mainAccount)throws WEB3BaseException
    {
        return false;
    }
}
@
