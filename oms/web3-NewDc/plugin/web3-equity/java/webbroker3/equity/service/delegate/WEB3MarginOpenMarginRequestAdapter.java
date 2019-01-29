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
filename	WEB3MarginOpenMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����V�K�����N�G�X�g�A�_�v�^(WEB3MarginOpenMarginRequestAdapter)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ���Ō�(Sinocom) �V�K�쐬
Revesion History : 2006/11/24 ������(Sinocom)�@@���f��No.1001
Revesion History : 2006/12/25 �����F (���u) ���f�� 1090
Revesion History : 2007/06/13 �����q (���u) ���f�� 1169
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginCommonRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmRequest;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenRequest;

/**
 * �i�M�p����V�K�����N�G�X�g�A�_�v�^�j�B<BR>
 * <BR>
 * �M�p����V�K�����N�G�X�g�A�_�v�^�N���X
 * @@author ���Ō�
 * @@version 1.0
 */
public class WEB3MarginOpenMarginRequestAdapter 
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOpenMarginRequestAdapter.class);
            
    /**
     * (���N�G�X�g�f�[�^)�B<BR>
     */
    public WEB3GenRequest request;
    
    /**
     * (�R���X�g���N�^)�B
     * @@roseuid 4142B32D0153
     */
    protected WEB3MarginOpenMarginRequestAdapter() 
    {
    }
    
    /**
     * (create)�B<BR>
     * <BR>
     * �M�p����V�K�����N�G�X�g�A�_�v�^�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�������B<BR>
     * �Q�j�@@���������C���X�^���X�Ɉ����̃��N�G�X�g�f�[�^���Z�b�g����B<BR>
     * �R�j�@@�C���X�^���X��ԋp����B<BR>
     * <BR>
     * �i�f�t�H���g�R���X�g���N�^��private�Ƃ��A�{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MarginOpenMarginRequestAdapter
     * @@roseuid 40AAC1B700F0
     */
    public static WEB3MarginOpenMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        //�{�C���X�^���X�𐶐�������B
        WEB3MarginOpenMarginRequestAdapter l_openMarginRequestAdapter = new WEB3MarginOpenMarginRequestAdapter();
        //�������C���X�^���X�Ɉ����̃��N�G�X�g�f�[�^���Z�b�g����B
        l_openMarginRequestAdapter.request = l_request;
        //�C���X�^���X��ԋp����B        
        return l_openMarginRequestAdapter;
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
     * @@return EqTypeExecutionConditionType
     * @@roseuid 40AAC1B700F2
     */
    public EqTypeExecutionConditionType getExecutionCondition()
    {
        final String STR_METHOD_NAME = "getExecutionCondition()";
        log.entering(STR_METHOD_NAME);
        
        String l_executionCondition = null;
        
        if (this.request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_executionCondition = ((WEB3MarginOpenMarginConfirmRequest)this.request).execCondType;
        }
        else if (this.request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_executionCondition = ((WEB3MarginOpenMarginCompleteRequest)this.request).execCondType;
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
     * (get�ŋ敪)�B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�����敪���AAP�w�Ŏg�p����ŋ敪�iTaxTypeEnum�j��ԋp����B <BR>
     * <BR>
     * �P�j ��ʌ����̃Z�b�g <BR>
     * �@@�E���N�G�X�g�f�[�^.�����敪���h��ʁh�̏ꍇ�ATaxTypeEnum.�h��ʁh��ԋp����B <BR>
     * <BR>
     * �Q�j�@@��������̃Z�b�g <BR>
     * �@@�E���N�G�X�g�f�[�^.�����敪���h����h�̏ꍇ�A TaxTypeEnum.�h����h��ԋp����B<BR>
     * <BR>
     * @@return TaxTypeEnum
     * @@throws WEB3BaseException
     * @@roseuid 40AAC1B700F3
     */
    public TaxTypeEnum getTaxType() 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTaxType()";
        log.entering(STR_METHOD_NAME);
        
        String l_strTaxType = null;
        
        if (this.request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_strTaxType = ((WEB3MarginOpenMarginConfirmRequest)this.request).taxType;
        }
        else if (this.request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_strTaxType = ((WEB3MarginOpenMarginCompleteRequest)this.request).taxType;
        }
        
        TaxTypeEnum l_taxTypeEnum = null;
        log.debug("l_strTaxType = " + l_strTaxType);
        
        //1)��ʌ����̃Z�b�g
        if (WEB3TaxTypeDef.NORMAL.equals(l_strTaxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;
        }
        //�Q�j��������̃Z�b�g
        else if (WEB3TaxTypeDef.SPECIAL.equals(l_strTaxType))
        {
        	l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        log.exiting(STR_METHOD_NAME);
        return l_taxTypeEnum;
    }
    
    /**
     * (is����)�B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.���敪���AAP�w�Ŏg�p����is������ԋp����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.����敪���h�V�K���������h�̏ꍇ��true�A<BR>
     * ���N�G�X�g�f�[�^.����敪���h�V�K���������h�̏ꍇ��false��ԋp����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.����敪����L�ȊO�̏ꍇ�́A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00639<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40AAC1B700F4
     */
    public boolean isLong() 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isLong()";
        log.entering(STR_METHOD_NAME);
        
        String l_strTradingType = null;
        if (this.request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_strTradingType = ((WEB3MarginOpenMarginConfirmRequest)this.request).tradingType;
        }
        else if (this.request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_strTradingType = ((WEB3MarginOpenMarginCompleteRequest)this.request).tradingType;
        }
        
        if (WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN.equals(l_strTradingType))
        {
            //���N�G�X�g�f�[�^.����敪���h�V�K���������h�̏ꍇ��true�A
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else if (WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN.equals(l_strTradingType))
        {
            //���N�G�X�g�f�[�^.����敪���h�V�K���������h�̏ꍇ��false��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else 
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00639, STR_METHOD_NAME);
        }        
    }
    
    /**
     * (is����)�B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.���敪���AAP�w�Ŏg�p����is������ԋp����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.����敪���h�V�K���������h�̏ꍇ��true�A<BR>
     * ���N�G�X�g�f�[�^.����敪���h�V�K���������h�̏ꍇ��false��ԋp����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.����敪����L�ȊO�̏ꍇ�́A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00640<BR>     
     * <BR>
     * @@return boolean
     * @@roseuid 40B42854022A
     */
    public boolean isShort() 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isLong()";
        log.entering(STR_METHOD_NAME);
        
        String l_strTradingType = null;
        if (this.request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_strTradingType = ((WEB3MarginOpenMarginConfirmRequest)this.request).tradingType;
        }
        else if (this.request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_strTradingType = ((WEB3MarginOpenMarginCompleteRequest)this.request).tradingType;
        }
        
        if (WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN.equals(l_strTradingType))
        {
            //���N�G�X�g�f�[�^.����敪���h�V�K���������h�̏ꍇ��true�A
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else if (WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN.equals(l_strTradingType))
        {
            //���N�G�X�g�f�[�^.����敪���h�V�K���������h�̏ꍇ��false��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else 
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00640, STR_METHOD_NAME);
        }   
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
        if (this.request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_strOrderPriceDiv =
                ((WEB3MarginOpenMarginConfirmRequest)this.request).orderPriceDiv;
        }
        else if (this.request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_strOrderPriceDiv =
                ((WEB3MarginOpenMarginCompleteRequest)this.request).orderPriceDiv;
        }
        
        double l_dblPrice = 0.0D;
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrderPriceDiv))
        {
            String l_strLimitPrice = null; 
            if (this.request instanceof WEB3MarginOpenMarginConfirmRequest)
            {
                l_strLimitPrice =
                    ((WEB3MarginOpenMarginConfirmRequest)this.request).limitPrice;
            }
            else if (this.request instanceof WEB3MarginOpenMarginCompleteRequest)
            {
                l_strLimitPrice =
                    ((WEB3MarginOpenMarginCompleteRequest)this.request).limitPrice;
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

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.EQUITY).getOrderManager();
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType = null;

        if (this.request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_eqTypeExecutionConditionType =
                l_orderManager.getExecutionConditionType(
                    ((WEB3MarginCommonRequest)this.request).wlimitExecCondType);
            log.exiting(STR_METHOD_NAME);
            return l_eqTypeExecutionConditionType;
        }
        else if (this.request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_eqTypeExecutionConditionType =
                l_orderManager.getExecutionConditionType(
                    ((WEB3MarginCommonRequest)this.request).wlimitExecCondType);
            log.exiting(STR_METHOD_NAME);
            return l_eqTypeExecutionConditionType;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * (get�s��R�[�h )<BR>
     * �s��R�[�h���擾����B <BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^.�s��R�[�h != "99�F�D��s��"�̏ꍇ <BR>
     * �@@�@@���N�G�X�g�f�[�^.�s��R�[�h��ԋp����B <BR>
     * <BR>
     * �Q�j�@@��L�ȊO�̏ꍇ <BR>
     * <BR>
     * �@@�Q�|�P�j�@@�g���v���_�N�g�}�l�[�W��.getProduct()���R�[�����A�����������擾����B <BR>
     * �@@�@@�@@�@@�@@�@@[getProduct()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@�����R�[�h�@@�@@�@@�F�@@���N�G�X�g�f�[�^.�����R�[�h <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@��������.get�D��s��()���R�[�����A�s����擾����B <BR>
     * <BR>
     * �@@�Q�|�R�j�@@�Q�|�Q�j�Ŏ擾�����s�ꂪnull�̏ꍇ�A��O��throw����B <BR>
     * �@@�@@�@@�@@�@@�@@�ȊO�A�s��.getMarketCode()�̖߂�l��ԋp����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:    BUSINESS_ERROR_02702<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getMarketCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarketCode()";
        log.entering(STR_METHOD_NAME);

        String l_strMarketCode = "";
        String l_strProductCode = "";
        //�P�j�@@���N�G�X�g�f�[�^.�s��R�[�h != "99�F�D��s��"�̏ꍇ
        //�@@�@@���N�G�X�g�f�[�^.�s��R�[�h��ԋp����B
        if (request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_strMarketCode = ((WEB3MarginOpenMarginConfirmRequest)request).marketCode;
            l_strProductCode = ((WEB3MarginOpenMarginConfirmRequest)request).productCode;
        }
        else if (request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_strMarketCode = ((WEB3MarginOpenMarginCompleteRequest)request).marketCode;
            l_strProductCode = ((WEB3MarginOpenMarginCompleteRequest)request).productCode;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (!WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_strMarketCode))
        {
            log.exiting(STR_METHOD_NAME);
            return l_strMarketCode;
        }

        //�@@�Q�|�P�j�@@�g���v���_�N�g�}�l�[�W��.getProduct()���R�[�����A�����������擾����B
        //�@@�@@�@@�@@�@@�@@[getProduct()�ɐݒ肷�����]
        //�@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h
        //�@@�@@�@@�@@�@@�@@�@@�����R�[�h�@@�@@�@@�F�@@���N�G�X�g�f�[�^.�����R�[�h
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3EquityProductManager l_equityProductManager
            = (WEB3EquityProductManager)l_tradingModule.getProductManager();
        Institution l_institution = null;
        WEB3EquityProduct l_eqTypeProduct = null;
        try
        {
            l_institution = l_accountManager.getInstitution(l_context.getInstitutionCode());
            l_eqTypeProduct =
                (WEB3EquityProduct)l_equityProductManager.getProduct(
                    l_institution,
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

        //�Q�|�Q�j�@@��������.get�D��s��()���R�[�����A�s����擾����B
        Market l_market = l_eqTypeProduct.getPrimaryMarket();

        //�Q�|�R�j�@@�Q�|�Q�j�Ŏ擾�����s�ꂪnull�̏ꍇ�A��O��throw����B
        //�@@�@@�@@�@@�@@�@@�ȊO�A�s��.getMarketCode()�̖߂�l��ԋp����B
        if (l_market == null)
        {
            log.debug("�D��s�ꂪ���w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02702,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "�D��s�ꂪ���w��ł��B");
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_market.getMarketCode();
        }
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
     * �@@�@@�@@�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h<BR>
     * �@@�@@�@@�s��R�[�h�F�@@this.get�s��R�[�h()�̖߂�l<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getExpirationDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDate()";
        log.entering(STR_METHOD_NAME);

        Date l_datExpirationDate = null;
        String l_strProductCode = null;
        if (request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_datExpirationDate = ((WEB3MarginOpenMarginConfirmRequest)request).expirationDate;
            l_strProductCode = ((WEB3MarginOpenMarginConfirmRequest)request).productCode;
        }
        else if (request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_datExpirationDate = ((WEB3MarginOpenMarginCompleteRequest)request).expirationDate;
            l_strProductCode = ((WEB3MarginOpenMarginCompleteRequest)request).productCode;
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
            // �g�����������}�l�[�W��
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getOrderManager();

            // �s��R�[�h�F�@@this.get�s��R�[�h()�̖߂�l
            String l_strMarketCode = this.getMarketCode();

            // �����L������
            l_datExpirationDate = l_orderManager.getExpirationDate(
                l_datExpirationDate, l_strProductCode, l_strMarketCode);

            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }
    }
}
@
