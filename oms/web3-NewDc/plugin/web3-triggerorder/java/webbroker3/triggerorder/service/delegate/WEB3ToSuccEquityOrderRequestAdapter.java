head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����������N�G�X�g�A�_�v�^(WEB3ToSuccEquityOrderRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/14 �A�C��(���u) �V�K�쐬
Revesion History : 2007/01/10 ���G��(���u) ���f��215
Revesion History : 2007/01/17 ���G��(���u) ���f��221
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.service.delegate.WEB3EquityOrderRequestAdapter;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�i�A���j�����������N�G�X�g�A�_�v�^)<BR>
 * �i�A���j�����������N�G�X�g�A�_�v�^�B
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3ToSuccEquityOrderRequestAdapter extends WEB3EquityOrderRequestAdapter 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityOrderRequestAdapter.class);
    
    /**
     * (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB
     */
    public EqTypeOrderUnit parentOrderUnit;
    
    /**
     * (�P��)<BR>
     * �P���B<BR>
     * �i�w�l�^0�i�����s�j�^���s�P���i�}�w�l�j�j<BR>
     * �����s�����̏ꍇ�̊T�Z��n����v�Z�Ɏg�p���������́A<BR>
     * �������������e.get�����P��()�Ŏ擾����B<BR>
     */
    public double price;

    /**
     * @@roseuid 4348E92D037A
     */
    private WEB3ToSuccEquityOrderRequestAdapter() 
    {
    
    }
    
    /**
     * �i�A���j�����������N�G�X�g�A�_�v�^�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�e�����̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�A�������}�l�[�W��Impl.get�����e�����̒����P��(<BR>
     * �@@�@@���N�G�X�g.�A���������ʏ��.�i�e�����j����ID)���R�[������B<BR>
     * <BR>
     * �R�j�@@���������C���X�^���X�ɁA�����̃��N�G�X�g�f�[�^�A<BR>
     * �@@�@@�y�ю擾�����e�����̒����P�ʃI�u�W�F�N�g���Z�b�g����B<BR>
     * �@@�@@�v���p�e�B�̒P���ɂ�0�i�����l�j���Z�b�g����B<BR>
     * <BR>
     * �S�j�@@�C���X�^���X��ԋp����B<BR>
     * <BR>
     * �i�f�t�H���g�R���X�g���N�^��private�Ƃ��A<BR>
     * �{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3EquityOrderRequestAdapter
     * @@roseuid 432775960357
     */
    public static WEB3EquityOrderRequestAdapter create(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME =" create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�{�C���X�^���X�𐶐�����B
        WEB3ToSuccEquityOrderRequestAdapter l_adapter = new WEB3ToSuccEquityOrderRequestAdapter();
        
        //�Q�j�@@�e�����̒����P�ʃI�u�W�F�N�g���擾����B
        //�@@�@@�A�������}�l�[�W��Impl.get�����e�����̒����P��(
        //�@@�@@���N�G�X�g.�A���������ʏ��.�i�e�����j����ID)���R�[������B
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                (WEB3SuccEquityBuyConfirmRequest)l_request;
            l_succCommonInfo = l_buyConfirmRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                (WEB3SuccEquityBuyCompleteRequest)l_request;
            l_succCommonInfo = l_buyCompleteRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                (WEB3SuccEquitySellConfirmRequest)l_request;
            l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                (WEB3SuccEquitySellCompleteRequest)l_request;
            l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccEquityOrderRequestAdapter" + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_orderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);

        //�R�j�@@���������C���X�^���X�ɁA�����̃��N�G�X�g�f�[�^�A
        //�@@�@@�y�ю擾�����e�����̒����P�ʃI�u�W�F�N�g���Z�b�g����B
        //�@@�@@�v���p�e�B�̒P���ɂ�0�i�����l�j���Z�b�g����B
        l_adapter.request = l_request;
        l_adapter.parentOrderUnit = l_orderUnit;
        l_adapter.price = 0;
        
        //�S�j�@@�C���X�^���X��ԋp����B

        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }
    
    /**
     * (get�ŋ敪)<BR>
     * AP�w�Ŏg�p����ŋ敪�iTaxTypeEnum�j��ԋp����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j ���蒍���̏ꍇ�iis������( )==true�j�́A<BR>
     * �@@�m��c�ɑ΂��锄�ƁA�e�����ɑ΂��锽�Δ��̏ꍇ�Ƃŕ��򂷂�B<BR>
     * <BR>
     * �@@�A�������}�l�[�W��.is���Δ������(<BR>
     * �@@���N�G�X�g.�A���������ʏ��.�A����������敪, this.�e�����̒����P��)==true��<BR>
     * �ꍇ�́A<BR>
     * �@@�e�����ɑ΂��锽�Δ��Ɣ��肷��B<BR>
     * �@@false�̏ꍇ�́A�m��c�ɑ΂��锄�����Ɣ��肷��B<BR>
     * <BR>
     * �P�|�P�j�@@�e�����ɑ΂��锽�Δ��̏ꍇ�́A�e�����̊Y������ŋ敪��ԋp����B<BR>
     * �@@�@@�@@�e�����̒������=="����������"�̏ꍇ�́A�e�����̒����P��.�ŋ敪��<BR>
     * �ԋp����B<BR>
     * �@@�@@�@@�e�����̒������=="��������"�̏ꍇ�́A�e�����̒����P��.�ŋ敪<BR>
     * �i�������n�j��ԋp����B<BR>
     * <BR>
     * �P�|�Q�j�@@�m��c�ɑ΂��锄�����̏ꍇ�́A�Ώ�Asset�̐ŋ敪��ԋp����B<BR>
     * <BR>
     * �@@�@@�Ώ�Asset�̐ŋ敪�F�����|�W�V�����}�l�[�W��.getAsset(���N�G�X�g.ID).�ŋ敪<BR>
     * <BR>
     * �Q�j�@@���������̏ꍇ�iis������( )==false�j�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�|�P�j�@@��ʌ����̃Z�b�g<BR>
     * �@@���N�G�X�g.�����敪���h��ʁh�̏ꍇ�ATaxTypeEnum.�h��ʁh��ԋp����B<BR>
     * <BR>
     * �Q�|�Q�j�@@��������̃Z�b�g<BR>
     * �@@���N�G�X�g.�����敪���h����h�̏ꍇ�ATaxTypeEnum.�h����h��ԋp����B<BR>
     * @@return TaxTypeEnum
     * @@throws WEB3BaseException
     * @@roseuid 432775960367
     */
    public TaxTypeEnum getTaxDivision() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getTaxDivision()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j ���蒍���̏ꍇ�iis������( )==true�j�́A
        //�@@�m��c�ɑ΂��锄�ƁA�e�����ɑ΂��锽�Δ��̏ꍇ�Ƃŕ��򂷂�B
        //�@@�A�������}�l�[�W��.is���Δ������(
        //�@@���N�G�X�g.�A���������ʏ��.�A����������敪, this.�e�����̒����P��)==true��
        //  �ꍇ�́A�e�����ɑ΂��锽�Δ��Ɣ��肷��B
        //�@@false�̏ꍇ�́A�m��c�ɑ΂��锄�����Ɣ��肷��B
        boolean l_blnSellOrder = this.isSellOrder();
        if (l_blnSellOrder)
        {
            WEB3SuccCommonInfo l_succCommonInfo = null;
            long l_lngAssetId = 0;
            if (super.request instanceof WEB3SuccEquitySellConfirmRequest)
            {
                WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                    (WEB3SuccEquitySellConfirmRequest)super.request;
                l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
                if (!WEB3StringTypeUtility.isEmpty(l_sellConfirmRequest.id))
                {
                    l_lngAssetId = Long.parseLong(l_sellConfirmRequest.id);
                }
            }
            else if (super.request instanceof WEB3SuccEquitySellCompleteRequest)
            {
                WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                    (WEB3SuccEquitySellCompleteRequest)super.request;
                l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
                if (!WEB3StringTypeUtility.isEmpty(l_sellCompleteRequest.id))
                {
                    l_lngAssetId = Long.parseLong(l_sellCompleteRequest.id);
                }
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "�p�����[�^�^�C�v�s���B");
            }

            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
                l_succCommonInfo.succTradingType, 
                this.parentOrderUnit);
            if (l_blnReversingTrade)
            {
                //�P�|�P�j�@@�e�����ɑ΂��锽�Δ��̏ꍇ�́A�e�����̊Y������ŋ敪��ԋp����B
                //�@@�@@�@@�e�����̒������=="����������"�̏ꍇ�́A�e�����̒����P��.�ŋ敪��ԋp����B
                //�@@�@@�@@�e�����̒������=="��������"�̏ꍇ�́A�e�����̒����P��.�ŋ敪�i�������n�j��ԋp����B
                if (this.parentOrderUnit == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME, 
                        "�\�����Ȃ��V�X�e���G���[���������܂����B");
                }
                if (OrderTypeEnum.EQUITY_BUY.equals(this.parentOrderUnit.getOrderType()))
                {
                    log.exiting(STR_METHOD_NAME);
                    return this.parentOrderUnit.getTaxType();
                }
                else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(this.parentOrderUnit.getOrderType()))
                {
                    EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)this.parentOrderUnit.getDataSourceObject();
                    log.exiting(STR_METHOD_NAME);
                    return l_row.getSwapTaxType();
                }
            }
            else
            {
                //�P�|�Q�j�@@�m��c�ɑ΂��锄�����̏ꍇ�́A�Ώ�Asset�̐ŋ敪��ԋp����B
                //�@@�@@�Ώ�Asset�̐ŋ敪�F�����|�W�V�����}�l�[�W��.getAsset(���N�G�X�g.ID).�ŋ敪
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityPositionManager l_positionManager =
                    (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
                Asset l_asset = null;
                try
                {
                    l_asset = l_positionManager.getAsset(l_lngAssetId);
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
                log.exiting(STR_METHOD_NAME);
                return l_asset.getTaxType();
            }
        }
        else
        {        
            String l_strTaxType = null;
            if (super.request instanceof WEB3SuccEquityBuyConfirmRequest)
            {
                WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                    (WEB3SuccEquityBuyConfirmRequest)super.request;
                l_strTaxType = l_buyConfirmRequest.taxType;
            }
            else if (super.request instanceof WEB3SuccEquityBuyCompleteRequest)
            {
                WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                    (WEB3SuccEquityBuyCompleteRequest)super.request;
                l_strTaxType = l_buyCompleteRequest.taxType;
            }
            //�Q�j�@@���������̏ꍇ�iis������( )==false�j�A�ȉ��̏������s���B
            //�Q�|�P�j�@@��ʌ����̃Z�b�g
            //�@@���N�G�X�g.�����敪���h��ʁh�̏ꍇ�ATaxTypeEnum.�h��ʁh��ԋp����B
            if (WEB3TaxTypeDivDef.NORMAL.equals(l_strTaxType))
            { 
                log.exiting(STR_METHOD_NAME);
                return TaxTypeEnum.NORMAL;
            }
            //�Q�|�Q�j�@@��������̃Z�b�g
            //�@@���N�G�X�g.�����敪���h����h�̏ꍇ�ATaxTypeEnum.�h����h��ԋp����B
            else if (WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE.equals(l_strTaxType)
                || WEB3TaxTypeDivDef.SPECIAL_SOURCE.equals(l_strTaxType))
            { 
                log.exiting(STR_METHOD_NAME);
                return TaxTypeEnum.SPECIAL;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get�P��)<BR>
     * �P�������N�G�X�g�I�u�W�F�N�g���擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�m�F���N�G�X�g�̏ꍇ�i���t�m�F���N�G�X�g�^���t�m�F���N�G�X�g�j<BR>
     * <BR>
     * �@@�v���p�e�B�̒P�� == 0�̏ꍇ�A<BR>
     * �@@�A�������}�l�[�W��.get�����\�񒍕����s�P��()���R�[�����A<BR>
     * �@@�߂�l���v���p�e�B�̒P���ɃZ�b�g����B<BR>
     * �@@���e���������s�����̏ꍇ�A��������Ƃ������s�P�����v�Z���Ă��邽�߁A<BR>
     * �@@���������̎����̕ϓ����l�����A���s�P�����L�����Ă����B<BR>
     * <BR>
     * �@@---------------------------------------------------------<BR>
     * �@@��get�����\�񒍕����s�P��()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�e�����̒����P�ʁF�@@�e�����̒����P��<BR>
     * �@@�w�l�F�@@super.get�P��()�̖߂�l<BR>
     * �@@�P�������l�F�@@���N�G�X�g.�P�������l���==null�̏ꍇ�́Anull�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�A���N�G�X�g.�P�������l���.get�P�������l()�B<BR>
     * �@@������������F�@@�e�����̒����P��.getTradedProduct()<BR>
     * �@@---------------------------------------------------------<BR>
     * <BR>
     * �@@�P�|�Q�j�v���p�e�B�̒P����ԋp����B <BR>
     * <BR>
     * �Q�j�@@�������N�G�X�g�̏ꍇ�i���t�������N�G�X�g�^���t�������N�G�X�g�j<BR>
     * <BR>
     * �@@���N�G�X�g.�P�������l���==null�i�w�l�^���s�j�̏ꍇ�́A<BR>
     * �@@super.get�P��()�̖߂�l��ԋp����B<BR>
     * �@@���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ�́A<BR>
     * �@@���N�G�X�g.������P����ԋp����B<BR>
     * �@@��������P����null�̏ꍇ�́A�u���s�P����null�v�̗�O��throw����B<BR>
     * �@@ �@@ �@@class: WEB3BusinessLayerException<BR>
     * �@@ �@@ �@@tag:   BUSINESS_ERROR_02707<BR>
     * �@@��������P����0�ȉ��̏ꍇ�́A�u���s�P����0�ȉ��v�̗�O��throw����B<BR>
     * �@@ �@@ �@@class: WEB3BusinessLayerException<BR>
     * �@@ �@@ �@@tag:   BUSINESS_ERROR_02298<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 431E563800B0
     */
    public double getPrice() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getPrice()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�m�F���N�G�X�g�̏ꍇ�i���t�m�F���N�G�X�g�^���t�m�F���N�G�X�g�j
        //�@@�@@�v���p�e�B�̒P�� == 0�̏ꍇ�́A
        //�@@�@@�A�������}�l�[�W��.get�����\�񒍕����s�P��()���R�[�����A
        //�@@�@@�߂�l���v���p�e�B�̒P���ɃZ�b�g����B
        //�@@�@@���e���������s�����̏ꍇ�A��������Ƃ������s�P�����v�Z���Ă��邽�߁A
        //�@@�@@���������̎����̕ϓ����l�����A���s�P�����L�����Ă����B
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            
        boolean l_blnConfirm = false;
        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = null;
        if (super.request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                (WEB3SuccEquityBuyConfirmRequest)super.request;
            l_priceAdjustmentValueInfo = l_buyConfirmRequest.priceAdjustmentValueInfo;
            l_blnConfirm = true;
        }
        else if (super.request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                (WEB3SuccEquitySellConfirmRequest)super.request;
            l_priceAdjustmentValueInfo = l_sellConfirmRequest.priceAdjustmentValueInfo;
            l_blnConfirm = true;
        }
        if (l_blnConfirm)
        {
            if (this.price == 0)
            {
                Double l_dblPriceAdjustmentValue = null;
                if (l_priceAdjustmentValueInfo != null)
                {
                    l_dblPriceAdjustmentValue = new Double(l_priceAdjustmentValueInfo.getPriceAdjustmentValue());                    
                }

                double l_dblReserveEqtypeOrderExecPrice = l_toOrderManager.getReserveEqtypeOrderExecPrice(
                    this.parentOrderUnit,
                    super.getPrice(),
                    l_dblPriceAdjustmentValue,
                    (WEB3EquityTradedProduct)this.parentOrderUnit.getTradedProduct());

                this.price = l_dblReserveEqtypeOrderExecPrice;
                
            }
            log.exiting(STR_METHOD_NAME);
            return this.price;
        }

        //�Q�j�@@�������N�G�X�g�̏ꍇ�i���t�������N�G�X�g�^���t�������N�G�X�g�j
        //�@@���N�G�X�g.�P�������l���==null�i�w�l�^���s�j�̏ꍇ�́A
        // super.get�P��()�̖߂�l��ԋp����B
        // �@@���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ�́A
        // ���N�G�X�g.������P����ԋp����B
        boolean l_blnComplete = false;
        double l_dblAfterAdjustmentPrice = 0;
        String l_strAfterAdjustmentPrice = null;
        if (super.request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                (WEB3SuccEquityBuyCompleteRequest)super.request;
            l_strAfterAdjustmentPrice = l_buyCompleteRequest.afterAdjustmentPrice;

            if (l_strAfterAdjustmentPrice != null)
            {
                l_dblAfterAdjustmentPrice = Double.parseDouble(l_strAfterAdjustmentPrice);    
            }

            l_priceAdjustmentValueInfo = l_buyCompleteRequest.priceAdjustmentValueInfo;
            l_blnComplete = true;
        }
        else if (super.request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                (WEB3SuccEquitySellCompleteRequest)super.request;
            l_strAfterAdjustmentPrice = l_sellCompleteRequest.afterAdjustmentPrice;

            if (l_strAfterAdjustmentPrice != null)
            {
                l_dblAfterAdjustmentPrice = Double.parseDouble(l_strAfterAdjustmentPrice);    
            }

            l_priceAdjustmentValueInfo = l_sellCompleteRequest.priceAdjustmentValueInfo;
            l_blnComplete = true;
        }
        if (l_blnComplete)
        {
            if (l_priceAdjustmentValueInfo == null)
            {
                log.exiting(STR_METHOD_NAME);
                return super.getPrice();
            }
            else
            {
                if (l_strAfterAdjustmentPrice == null)
                {
                    log.debug("���s�P����null�B ");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02707,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "���s�P����null�B ");
                }
                else if (l_dblAfterAdjustmentPrice <= 0)
                {
                    log.debug("���s�P����0�ȉ��B " + l_dblAfterAdjustmentPrice);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02298,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "���s�P����0�ȉ��B ");
                }
                log.exiting(STR_METHOD_NAME);
                return l_dblAfterAdjustmentPrice;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return 0;
    }
    
    /**
     * (get�����R�[�h)<BR>
     * �����R�[�h���擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * this.get����()�Ŏ擾�������������I�u�W�F�N�g.�����R�[�h��ԋp����B<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4327B95B02DA
     */
    public String getProductCode() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getProductCode()";
        log.entering(STR_METHOD_NAME);
        
        String  l_strProductCode = this.getProduct().getProductCode(); 
        
        log.exiting(STR_METHOD_NAME);
        return l_strProductCode;
    }
    
    /**
     * (get����)<BR>
     * ���������I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �P�j�@@�������̏ꍇ�iis������()==false�j<BR>
     * <BR>
     * �@@�@@EQTYPE�̊g���v���_�N�g�}�l�[�W��.getProduct(<BR>
     * �@@�@@�e�����̒����P��.���XID�ɊY�����镔�X.getInstitution(), <BR>
     * �@@�@@���N�G�X�g.�����R�[�h)�̖߂�l��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�������̏ꍇ�iis������()==true�j<BR>
     * <BR>
     * �@@�@@�e�����ɑ΂��锽�Δ������ǂ����ŕ��򂷂�B<BR>
     * �@@�@@�|�A�������}�l�[�W��.is���Δ������(���N�G�X�g.�A����������敪, <BR>
     * �e�����̒����P��)<BR>
     * �@@�@@�@@==true�ł���΁A�e�����̔��Δ����ł���Ɣ��肷��B<BR>
     * �@@�@@�|false�ł���΁A�e�����̔��Δ����łȂ��Ɣ��肷��B<BR>
     * <BR>
     * �@@�@@��is���Δ������()�̈����́A�C���X�^���X�v���p�e�B���ݒ肷��B<BR>
     * <BR>
     * �Q�|�P�j�@@�e�����̔��Δ����̏ꍇ<BR>
     * <BR>
     * �@@�@@�e�����̒����P��.getProduct()�̖߂�l��ԋp����B<BR>
     * <BR>
     * �Q�|�Q�j�@@�e�����̔��Δ����łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@�����|�W�V�����}�l�[�W��.getAsset(���N�G�X�g.ID�i==�ۗL���YID�j)�ɂ��A<BR>
     * �@@�@@�ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�擾�����ۗL���Y.getProduct()�̖߂�l��ԋp����B<BR>
     * @@return WEB3EquityProduct
     * @@throws WEB3BaseException
     * @@roseuid 4327BD8003A5
     */
    public WEB3EquityProduct getProduct() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getProduct()";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule
            = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager
            = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            //�P�j�@@�������̏ꍇ�iis������()==false�j
            //�@@�@@EQTYPE�̊g���v���_�N�g�}�l�[�W��.getProduct(
            //�@@�@@�e�����̒����P��.���XID�ɊY�����镔�X.getInstitution(), 
            //�@@�@@���N�G�X�g.�����R�[�h)�̖߂�l��ԋp����B
            if (!this.isSellOrder())
            {
                String l_strProductCode = null;
                if (super.request instanceof WEB3SuccEquityBuyConfirmRequest)
                {
                    WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                        (WEB3SuccEquityBuyConfirmRequest)super.request;
                    l_strProductCode = l_buyConfirmRequest.productCode;
                }
                else if (super.request instanceof WEB3SuccEquityBuyCompleteRequest)
                {
                    WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                        (WEB3SuccEquityBuyCompleteRequest)super.request;
                    l_strProductCode = l_buyCompleteRequest.productCode;
                }
                Branch l_branch = l_accountManager.getBranch(this.parentOrderUnit.getBranchId());
                WEB3EquityProduct l_product = null;
                try
                {
                    l_product = (WEB3EquityProduct)l_productManager.getProduct(
                        l_branch.getInstitution(), 
                        l_strProductCode);
                }
                catch (NotFoundException l_nfe)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_nfe.getMessage(),
                        l_nfe);
                }

                log.exiting(STR_METHOD_NAME);
                return l_product;
            }
            else
            {
                //�Q�j�@@�������̏ꍇ�iis������()==true�j
                //�@@�@@�e�����ɑ΂��锽�Δ������ǂ����ŕ��򂷂�B
                //�@@�@@�|�A�������}�l�[�W��.is���Δ������(���N�G�X�g.�A����������敪,�e�����̒����P��)
                //�@@�@@�@@==true�ł���΁A�e�����̔��Δ����ł���Ɣ��肷��B
                //�@@�@@�|false�ł���΁A�e�����̔��Δ����łȂ��Ɣ��肷��B
                //�@@�@@��is���Δ������()�̈����́A�C���X�^���X�v���p�e�B���ݒ肷��B
                WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                WEB3SuccCommonInfo l_succCommonInfo = null;
                long l_lngAssetId = 0;
                if (super.request instanceof WEB3SuccEquitySellConfirmRequest)
                {
                    WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                        (WEB3SuccEquitySellConfirmRequest)super.request;
                    l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
                    if (!WEB3StringTypeUtility.isEmpty(l_sellConfirmRequest.id))
                    {
                        l_lngAssetId = Long.parseLong(l_sellConfirmRequest.id);
                    }
                }
                else if (super.request instanceof WEB3SuccEquitySellCompleteRequest)
                {
                    WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                        (WEB3SuccEquitySellCompleteRequest)super.request;
                    l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
                    if (!WEB3StringTypeUtility.isEmpty(l_sellCompleteRequest.id))
                    {
                        l_lngAssetId = Long.parseLong(l_sellCompleteRequest.id);
                    }
                }

                boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
                    l_succCommonInfo.succTradingType, 
                    this.parentOrderUnit);
                    
                //�Q�|�P�j�@@�e�����̔��Δ����̏ꍇ
                if (l_blnReversingTrade) 
                {
                    //�e�����̒����P��.getProduct()�̖߂�l��ԋp����B
                    log.exiting(STR_METHOD_NAME);
                    return (WEB3EquityProduct)this.parentOrderUnit.getProduct();
                }
                else
                {
                    //�Q�|�Q�j�@@�e�����̔��Δ����łȂ��ꍇ
                    //�@@�@@�����|�W�V�����}�l�[�W��.getAsset(���N�G�X�g.ID�i==�ۗL���YID�j)�ɂ��A
                    //�@@�@@�ۗL���Y�I�u�W�F�N�g���擾����B
                    //�@@�@@�擾�����ۗL���Y.getProduct()�̖߂�l��ԋp����B
                    WEB3EquityPositionManager l_positionManager =
                        (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
                    Asset l_asset = null;
                    try
                    {
                        l_asset = l_positionManager.getAsset(l_lngAssetId);
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
                    
                    log.exiting(STR_METHOD_NAME);
                    return (WEB3EquityProduct)l_asset.getProduct();
                }
            }
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
}
@
