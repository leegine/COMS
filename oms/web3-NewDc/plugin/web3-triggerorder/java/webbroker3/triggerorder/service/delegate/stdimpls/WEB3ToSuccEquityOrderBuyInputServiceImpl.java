head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderBuyInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����������t�������̓T�[�r�XImpl(WEB3ToSuccEquityOrderBuyInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 �A�C��(���u) �V�K�쐬
Revesion History : 2007/01/20 �юu��(���u) �d�l�ύX���f��No.224
Revesion History : 2007/12/17 ��іQ(���u) �d�l�ύX���f��No.245
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderBuyInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyInputRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderBuyInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�����������t�������̓T�[�r�XImpl)<BR>
 * �i�A���j�����������t�������̓T�[�r�X�����N���X�B
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3ToSuccEquityOrderBuyInputServiceImpl extends WEB3EquityOrderBuyInputServiceImpl 
    implements WEB3ToSuccEquityOrderBuyInputService 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityOrderBuyInputServiceImpl.class);
    
    /**
     * @@roseuid 4348EC6C0119
     */
    public WEB3ToSuccEquityOrderBuyInputServiceImpl() 
    {
     
    }
    
    /**
     * �i�A���j�����������t�������̓T�[�r�X���������{����B<BR>
     * <BR>
     * get���͉��()���\�b�h���R�[������B<BR>
     * @@param l_request - ()<BR>
     * ���N�G�X�g�f�[�^�B
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431C28A80160
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
        
        if (l_request instanceof WEB3SuccEquityBuyInputRequest)
        {
            l_response = this.getInputScreen((WEB3SuccEquityBuyInputRequest) l_request);
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
     *  ======================================================== <BR>
     *  �V�[�P���X�}�u�i�i�A���j�����������t�������́jget���͉�ʁv <BR> 
     *  1.4 ���Δ�������̏ꍇ�iis���Δ������()==true�j�A���N�G�X�g�f�[�^ <BR> 
     *    �̃v���p�e�B���`�F�b�N���� <BR> 
     *    �ȉ��̂����ꂩ�ɊY������ꍇ�́A��O��throw����B <BR> 
     *   class: WEB3BusinessLayerException <BR>
     *   tag: BUSINESS_ERROR_02250 <BR>
     *  ========================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B
     * 
     * @@return WEB3SuccEquityBuyInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 431C28A8019E
     */
    protected WEB3SuccEquityBuyInputResponse getInputScreen(WEB3SuccEquityBuyInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getInputScreen(WEB3SuccEquityBuyInputRequest )";
        log.entering(STR_METHOD_NAME);

        WEB3SuccEquityBuyInputResponse l_response = null;
        
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
        
        //1.3 is���Δ������(String, OrderUnit)
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_request.succCommonInfo.succTradingType, 
            l_orderUnit);
        
        //1.4 ���Δ�������̏ꍇ�iis���Δ������()==true�j�A���N�G�X�g�f�[�^�̃v���p�e�B���`�F�b�N����
        if (l_blnReversingTrade)
        {
            EqTypeProduct l_eqtypeProduct = (EqTypeProduct)l_orderUnit.getProduct();
            if (l_eqtypeProduct == null || !l_request.productCode.equals(l_eqtypeProduct.getProductCode()))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02250,
                    getClass().getName() + STR_METHOD_NAME,
                    "���Ύ�����̖����w�肪�A�e�����ƕs�����ł��B");
            }
        }
        
        //1.5 get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        if (l_request.marketCode == null)
        {
	        // reset�s��R�[�h
            WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);
        }
        
        //1.6 validate�A������(�⏕����, ProductTypeEnum, String, String, OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_request.succCommonInfo.succTradingType,
            l_orderUnit);
            
        //1.7 validate�A�������ő�ݒ萔(OrderUnit)
        l_toOrderManager.validateSuccOrderMaxQuantity(l_orderUnit);
        
        if (l_request.marketCode == null)
        {
            // reset�s��R�[�h
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);
        }
        
        //1.8  get���͉��(���N�G�X�g�f�[�^ : �����������t�������̓��N�G�X�g)
        l_response = (WEB3SuccEquityBuyInputResponse)super.getBuyInputScreen(l_request); //WEB3BaseException
        
        //1.9 (*) �v���p�e�B�Z�b�g
        //-----------------------------------------------------------------
        //���u�i�A���j�����������t�������̓��X�|���X�v�ɂ̂ݑ��݂���v���p�e�B
        //���������F�@@
        //�����Δ����̏ꍇ
        //�i�e�����̒����P��.�������ʁj���Z�b�g�B
        //����L�ȊO�̏ꍇ
        //null���Z�b�g�B
        if (l_blnReversingTrade)
        {
            l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        }
        else
        {
            l_response.orderQuantity = null;
        }
        
        //-----------------------------------------------------------------
        //���قȂ�l���Z�b�g����v���p�e�B�i�ăZ�b�g�j
        //
        //�l�i�����ꗗ�F�@@"�w��Ȃ�"�݂̂��Z�b�g�B
        l_response.priceCondList = new String[]{WEB3PriceConditionDef.DEFAULT};
        
        //���s�����ꗗ�F�@@"������"�݂̂��Z�b�g�B
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};

        //�v�w�l�p���s�����ꗗ�F�@@null���Z�b�g�B
        l_response.wlimitExecCondList = null;

        //���������敪�ꗗ�F�@@"�w��Ȃ�"�݂̂��Z�b�g�B
        l_response.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���t�\�z)<BR>
     * ���t�\�z���擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����]�̓T�[�r�X.get�������t�\�z�`�A�������`(�����̕⏕����, null, null)��<BR>
     * �R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4326A9580118
     */
    protected double getEquityTradingPower(WEB3GentradeSubAccount l_subAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =" getEquityTradingPower(WEB3GentradeSubAccount )";
        log.entering(STR_METHOD_NAME);

        // ����]�̓T�[�r�X.get�������t�\�z�`�A�������`(�����̕⏕����, null, null)��
        // �R�[������B
        WEB3TPTradingPowerService l_trdingPowerService = 
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblBuyPower = l_trdingPowerService.getSuccEquityTradingPower(
            l_subAccount,
            null,
            null);

        log.exiting(STR_METHOD_NAME);
        return l_dblBuyPower;
    }

    /**
     * (isPTS�����J��)<BR>
     * �ڋq.isPTS�����J�݂��R�[�����A���ʂ�ԋp���� <BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * (������) <BR>
     * false��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@return boolean
     */
    protected boolean isPTSAccountOpen(WEB3GentradeMainAccount l_mainAccount)
    {
        return false;
    }
}
@
