head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����ԍσ��N�G�X�g�A�_�v�^�N���X(WEB3MarginCloseMarginRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ������ (���u) �V�K�쐬
Revesion History : 2004/12/09 �X��   (SRA)  �c�Č��Ή�
Revesion History : 2005/01/05 ����   (SRA) JavaDoc�C��
Revesion History : 2006/11/27 �đo�g(���u) ���f��1010
Revesion History : 2007/06/14 �����q(���u) ���f��1171
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.message.WEB3MarginCloseMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����ԍσ��N�G�X�g�A�_�v�^�j�B<BR>
 * <BR>
 * �M�p����ԍσ��N�G�X�g�A�_�v�^�N���X�B
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCloseMarginRequestAdapter 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B)�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginCloseMarginRequestAdapter.class);
    
    /**
     * (���N�G�X�g�f�[�^)�B<BR>
     */
    public WEB3GenRequest request;
    
    /**
     * (�R���X�g���N�^)�B 
     */
    protected WEB3MarginCloseMarginRequestAdapter()
    {
    }

    /**
     * (create)�B<BR>
     * <BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�����B<BR>
     * �Q�j�@@���������C���X�^���X�Ɉ����̃��N�G�X�g�f�[�^���Z�b�g����B<BR>
     * �R�j�@@�C���X�^���X��ԋp����B<BR>
     * <BR>
     * �i�f�t�H���g�R���X�g���N�^��private�Ƃ��A<BR>
     * �{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3MarginCloseMarginRequestAdapter<BR>
     * @@roseuid 40B70E3E028F
     */
    public static WEB3MarginCloseMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = " create(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginCloseMarginRequestAdapter l_adapter = new WEB3MarginCloseMarginRequestAdapter();
        l_adapter.request = l_request;
        log.exiting(STR_METHOD_NAME);
         return l_adapter;
    }
    
    /**
     * (get���s����)�B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.���s�������AAP�w�Ŏg�p���鎷�s�����i<BR>
     * EqTypeExecutionConditionType�j��ԋp����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.���s�������A <BR>
     * AP�w�Ŏg�p���鎷�s�����iEqTypeExecutionConditionType�j��ԋp����B<BR> 
     * <BR>
     * �g�����������}�l�[�W��.get���s����(���N�G�X�g�f�[�^.���s����)��delegate����B<BR>
     * <BR> 
     * @@return EqTypeExecutionConditionType<BR>
     * @@roseuid 40B70E3E0291
     */
    public EqTypeExecutionConditionType getExecCondType() 
    {
        final String STR_METHOD_NAME = " getExecCondType( )";
        log.entering(STR_METHOD_NAME);
        
        String l_executionCondition = null;
        
        if (this.request instanceof WEB3MarginCloseMarginConfirmRequest)
        {
            l_executionCondition = ((WEB3MarginCloseMarginConfirmRequest)this.request).execCondType;
        }
        else if (this.request instanceof WEB3MarginCloseMarginCompleteRequest)
        {
            l_executionCondition = ((WEB3MarginCloseMarginCompleteRequest)this.request).execCondType;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            
        EqTypeExecutionConditionType l_EqTypeExecutionConditionType;
        try {
            l_EqTypeExecutionConditionType = l_orderManager.getExecutionConditionType(l_executionCondition);
        }
        catch (WEB3BaseException l_wbe) {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00019, STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_EqTypeExecutionConditionType;
    }
    
    /**
     * (get��������)�B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.���Ϗ����敪�A����у��N�G�X�g�f�[�^.�����������A<BR>
     * AP�w�Ŏg�p���钍��������ԋp����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^.���Ϗ����敪���h�����_���h�̏ꍇ�A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@���N�G�X�g�f�[�^.���Ϗ����敪���h�����_���h�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^.����������double�l��ԋp����B<BR>
     * <BR>
     * @@return double<BR>
     * @@roseuid 40BED92C014F
     */
    public double getOrderQuantity() 
    {
        final String STR_METHOD_NAME = " getOrderQuantity( )";
        log.entering(STR_METHOD_NAME);
        WEB3MarginCloseMarginConfirmRequest l_confirmRequest = null;
        WEB3MarginCloseMarginCompleteRequest l_completeRequest = null;
        double l_dblReturn = 0D;
        if (request instanceof WEB3MarginCloseMarginConfirmRequest) //validate����
        {
            l_confirmRequest = (WEB3MarginCloseMarginConfirmRequest) request;
            if (WEB3ClosingOrderDef.RANDOM.equals(l_confirmRequest.closingOrder))
            {
                l_dblReturn = 0D;
            }
            else
            {
                l_dblReturn = Double.parseDouble(l_confirmRequest.orderQuantity);
            }

        }
        else if (request instanceof WEB3MarginCloseMarginCompleteRequest) //submitOrder����
        {
            l_completeRequest = (WEB3MarginCloseMarginCompleteRequest) request;
            if (WEB3ClosingOrderDef.RANDOM.equals(l_completeRequest.closingOrder))
            {
                l_dblReturn = 0D;
            }
            else
            {
                l_dblReturn = Double.parseDouble(l_completeRequest.orderQuantity);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblReturn;
    }
    
    /**
     * (get�P��)<BR>
     * �P�������N�G�X�g�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�����P���敪=="�w�l"�̏ꍇ�́A<BR>
     * ���N�G�X�g�f�[�^.�����P����ԋp����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�����P���敪=="���s"�̏ꍇ�́A<BR>
     * 0��ԋp����B<BR>
     * @@return double
     */
    public double getPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPrice()";
        log.entering(STR_METHOD_NAME);
        
        String l_strOrderPriceDiv = null; 
        if (this.request instanceof WEB3MarginCloseMarginConfirmRequest)
        {
            l_strOrderPriceDiv =
                ((WEB3MarginCloseMarginConfirmRequest)this.request).orderPriceDiv;
        }
        else if (this.request instanceof WEB3MarginCloseMarginCompleteRequest)
        {
            l_strOrderPriceDiv =
                ((WEB3MarginCloseMarginCompleteRequest)this.request).orderPriceDiv;
        }
        
        double l_dblPrice = 0.0D;
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrderPriceDiv))
        {
            String l_strLimitPrice = null; 
            if (this.request instanceof WEB3MarginCloseMarginConfirmRequest)
            {
                l_strLimitPrice =
                    ((WEB3MarginCloseMarginConfirmRequest)this.request).limitPrice;
            }
            else if (this.request instanceof WEB3MarginCloseMarginCompleteRequest)
            {
                l_strLimitPrice =
                    ((WEB3MarginCloseMarginCompleteRequest)this.request).limitPrice;
            }
            if (l_strLimitPrice != null)
            {
                l_dblPrice = Double.parseDouble(l_strLimitPrice);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblPrice;
    }
    
    /**
     * (get����)<BR>
     * �����I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * ���N�G�X�g.���ό������׈ꗗ[0].ID�ɊY�����錚����<BR>
     * �擾���A�ԋp����B<BR>
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     */
    public WEB3EquityContract getContract() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContract()";
        log.entering(STR_METHOD_NAME);
        

        String l_strId = null;
        if (this.request instanceof WEB3MarginCloseMarginConfirmRequest)
        {
            l_strId =
                ((WEB3MarginCloseMarginConfirmRequest)this.request).closeMarginContractUnits[0].id;
        }
        else if (this.request instanceof WEB3MarginCloseMarginCompleteRequest)
        {
            l_strId =
                ((WEB3MarginCloseMarginCompleteRequest)this.request).closeMarginContractUnits[0].id;
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        WEB3EquityContract l_contract = null;
        try
        {
            l_contract =
                (WEB3EquityContract)l_positionManager.getContract(
                    Long.parseLong(l_strId));
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_contract;
    }

    /**
     * (get�i�v�w�l�j���s����)<BR>
     * ���N�G�X�g�f�[�^.���s�������A<BR>
     * AP�w�Ŏg�p���鎷�s�����iEqTypeExecutionConditionType�j��ԋp����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.get���s����(���N�G�X�g�f�[�^.�i�v�w�l�j���s����)��delegate����B<BR>
     * @@return EqTypeExecutionConditionType
     * @@throws WEB3BaseException
     */
    public EqTypeExecutionConditionType getWLimitExecCondType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitExecCondType()";
        log.entering(STR_METHOD_NAME);

        String l_strWlimitExecCondType = null;
        if (this.request instanceof WEB3MarginCloseMarginConfirmRequest)
        {
            l_strWlimitExecCondType =
                ((WEB3MarginCloseMarginConfirmRequest)this.request).wlimitExecCondType;
        }
        else if (this.request instanceof WEB3MarginCloseMarginCompleteRequest)
        {
            l_strWlimitExecCondType =
                ((WEB3MarginCloseMarginCompleteRequest)this.request).wlimitExecCondType;
        }
        else
        {
            log.error("�p�����[�^.���N�G�X�g�f�[�^�̌^���s���ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeExecutionConditionType l_eqyTypeExecutionConditonType =
            l_orderManager.getExecutionConditionType(l_strWlimitExecCondType);

        log.exiting(STR_METHOD_NAME);
        return l_eqyTypeExecutionConditonType;
    }

    /**
     * (get�����L������)<BR>
     * �����L���������擾����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^.�����L��������null�̏ꍇ<BR>
     * �@@�@@�@@(��������̒����̏ꍇ)<BR>
     * �@@�@@�@@�@@null��ԋp����B<BR>
     * <BR>
     * �Q�j�@@��L�ȊO�̏ꍇ<BR>
     * �@@�@@�g�����������}�l�[�W��.get�����L������()���R�[�����A<BR>
     * �@@�@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@�@@[get�����L�������̈����ݒ�]<BR>
     * �@@�@@�@@�����L�������F�@@���N�G�X�g�f�[�^.�����L������<BR>
     * �@@�@@�@@�����R�[�h�F�@@this.get����()�Ŏ擾��������.����ID�ɊY����������R�[�h<BR>
     * �@@�@@�@@�s��R�[�h�F�@@this.get����()�Ŏ擾��������.�s��ID�ɊY������s��R�[�h<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getExpirationDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDate()";
        log.entering(STR_METHOD_NAME);

        // �����L�������F�@@���N�G�X�g�f�[�^.�����L������
        Date l_datExpirationDate = null;
        if (request instanceof WEB3MarginCloseMarginConfirmRequest)
        {
            l_datExpirationDate = ((WEB3MarginCloseMarginConfirmRequest)request).expirationDate;

        }
        else if (request instanceof WEB3MarginCloseMarginCompleteRequest)
        {
            l_datExpirationDate = ((WEB3MarginCloseMarginCompleteRequest)request).expirationDate;
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

        // ���N�G�X�g�f�[�^.�����L��������null�̏ꍇ
        if (l_datExpirationDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            // ��L�ȊO�̏ꍇ
            // �����R�[�h�F�@@this.get����()�Ŏ擾��������.����ID�ɊY����������R�[�h
            EqtypeContractRow l_eqtypeContractRow =
                (EqtypeContractRow)this.getContract().getDataSourceObject();
            long l_lngProductId = l_eqtypeContractRow.getProductId();
            long l_lngMarketId = l_eqtypeContractRow.getMarketId();
            // �g�����������}�l�[�W��.get�����L������()���R�[�����A�߂�l��ԋp����B
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            ProductManager l_productManager = l_tradingModule.getProductManager();
            WEB3EquityProduct l_product = null;
            Market l_market = null;
            try
            {
                l_product =
                    (WEB3EquityProduct)l_productManager.getProduct(l_lngProductId);
                l_market = (Market)l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // �����R�[�h�F�@@(*)��������.�����R�[�h
            String l_strProductCode = l_product.getProductCode();
            // �s��R�[�h�F�@@(*)�s��.�s��R�[�h
            String l_strMarketCode = l_market.getMarketCode();
            l_datExpirationDate = l_orderManager.getExpirationDate(
                l_datExpirationDate, l_strProductCode, l_strMarketCode);

            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }
    }
}
@
