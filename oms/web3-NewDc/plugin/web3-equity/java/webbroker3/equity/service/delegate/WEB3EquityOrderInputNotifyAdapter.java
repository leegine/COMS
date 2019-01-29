head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderInputNotifyAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������͒ʒm�f�[�^�A�_�v�^(WEB3EquityOrderInputNotifyAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 �R�w��(���u) �쐬
                   2006/08/29 �ęԍg(���u) �d�l�ύX���f��970
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TradeTypeDef;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�����������͒ʒm�f�[�^�A�_�v�^�j�B
 * @@version 1.0
 */
public class WEB3EquityOrderInputNotifyAdapter 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderInputNotifyAdapter.class);
 
    /**
     * (�����������͒ʒm�L���[Params) <BR>
     */
    private HostEqtypeOrderReceiptParams hostEqtypeOrderReceipt;
   
    /**
     * @@roseuid 40B43077006D
     */
    private  WEB3EquityOrderInputNotifyAdapter() 
    {
    
    }
    
    /**
     * (get���s����)<BR>
     * <BR>
     * �y�����������͒ʒm�L���[�e�[�u���z���s����(SONAR)�ɉ�����<BR>
     * EqTypeExecutionConditionType��Ԃ��B<BR>
     * <BR>
     * �g�����������}�l�[�W��.get���s����(���������ʒm�L���[Params.���s����(SONAR))��<BR>
     * delegate����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType
     * @@throws WEB3SystemLayerException
     * @@roseuid 402714D7026F
     */
    public EqTypeExecutionConditionType getExecutionCondition() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getExecutionCondition()";
        log.entering(STR_METHOD_NAME);
        
        EqTypeExecutionConditionType l_executionConditionType = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr = (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        
        try {
            l_executionConditionType = l_orderMgr.getExecutionConditionType(this.getHostEqtypeOrderReceipt().execution_condition);
        } catch (WEB3BaseException e) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���������ʒm�L���[Params.���s����(SONAR)���s���Ȓl�ł��B:["
                + this.getHostEqtypeOrderReceipt().execution_condition + "]");
        }


        log.exiting(STR_METHOD_NAME);
        return l_executionConditionType;
    }
   
    /**
     * (is������) <BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�����敪���A <BR>
     * AP�w�Ŏg�p����is��������ԋp����B <BR>
     * �����敪��"�������� ���t"�̏ꍇ��true�A <BR>
     * �����敪��"�������� ���t"�̏ꍇ��false��ԋp����B <BR>
     * ��L�ȊO�͗�O���X���[����B <BR>
     * class�FWEB3BusinessLayerException <BR>
     * tag�F  BUSINESS_ERROR_00068 <BR>
     * <BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BusinessLayerException
     * @@roseuid 4027151C029E
     */
    public boolean isSellOrder() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "isSellOrder()";
        log.entering(STR_METHOD_NAME);

        String l_strTradeType = this.hostEqtypeOrderReceipt.getTradeType();
        if(l_strTradeType.equals(WEB3TradeTypeDef.SELL))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else if(l_strTradeType.equals(WEB3TradeTypeDef.BUY))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00068,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * (get�ŋ敪)<BR>
     * <BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�ŋ敪�i��������敪�j�i�ȉ��u�ŋ敪�v�j���A<BR>
     * AP�w�Ŏg�p����ŋ敪�iTaxTypeEnum�j��ԋp����B <BR>
     * <BR>
     * �P�j�����f�[�^�A�_�v�^.getAP�����敪(�ŋ敪)�ɂ��ŋ敪���擾����B <BR>
     * <BR>
     * �Q�j�ڋq�I�u�W�F�N�g���擾����B(*1) <BR>
     * <BR>
     * �R�j���������R���ʃ`�F�b�N.validate��������J��()(*2)���R�[������B<BR>
     * <BR>
     * �S�j�P�j�Ŏ擾�����ŋ敪��ԋp����B<BR>
     * <BR>
     * (*1) �ڋq�I�u�W�F�N�g�擾���@@�̓V�[�P���X�}�u�i�����ʒm�jget�ڋq�v���Q�ƁB<BR>
     * <BR>
     * �@@�@@�،���ЃI�u�W�F�N�g�́A�g���A�J�E���g�}�l�[�W��.getInstitution( <BR>
     * �@@�@@this.�����������͒ʒm�L���[Params.�،���ЃR�[�h)�Ŏ擾�B<BR>
     * <BR>
     * (*2)�w�肷������͈ȉ��̒ʂ�<BR> 
     * �@@�E�⏕���� <BR>
     *   �@@�@@�ڋq.getSubAccount()�̖߂�l��ݒ�<BR> 
     * �@@�@@�@@���V�[�P���X�}�u�i���������ʒm�T�[�r�X�jcalc�T�Z��n����R�[���v�Q�ƁB<BR>
     * �@@�E�ŋ敪<BR>
     * �@@�@@�@@�P�j�Ŏ擾�����ŋ敪<BR>
     * �@@�E��n��<BR>
     * �@@�@@�@@�g���v���_�N�g�}�l�[�W��.get�������(�،����, this.�����������͒ʒm�L���[Params.�����R�[�h,<BR>
     * �@@�@@this.get�s��R�[�h()).getDailyDeliveryDate( )���Z�b�g�B<BR> 
     * @@return TaxTypeEnum
     * @@throws WEB3BaseException
     */
    public TaxTypeEnum getTaxDivision() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = "getTaxDivision()";
        log.entering(STR_METHOD_NAME);

        TaxTypeEnum l_taxTypeEnum = null;
        //�P�j�����f�[�^�A�_�v�^.getAP�����敪(�ŋ敪)�ɂ��ŋ敪���擾����B
        int l_intTaxType = this.getHostEqtypeOrderReceipt().getTaxType();
        String l_strTaxType = WEB3EquityDataAdapter.getApTaxType(l_intTaxType + "");
        //�Q�j�ڋq�I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        Institution l_institution = null;
            
        try
        {
            l_institution =
                l_accMgr.getInstitution(this.getHostEqtypeOrderReceipt().getInstitutionCode());
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accMgr.getMainAccount(
                    l_institution.getInstitutionId(),
                    this.getHostEqtypeOrderReceipt().getBranchCode(),
                    this.getHostEqtypeOrderReceipt().getAccountCode());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�R�j���������R���ʃ`�F�b�N.validate��������J��()(*2)���R�[������B
        
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            //is�M�p�����J��()==true�i���M�p�q�j
            if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                l_subAccount =
                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            //is�M�p�����J��()��true�i����M�p�q�j�̏ꍇ
            else
            {
                l_subAccount =
                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();

        Date l_dailyDeliveryDate = null;
        try
        {
            EqTypeTradedProduct l_tradedProduct =
                l_productManager.getTradedProduct(
                    l_institution,
                    this.getHostEqtypeOrderReceipt().getProductCode(),
                    this.getMarketCode());
            l_dailyDeliveryDate = l_tradedProduct.getDailyDeliveryDate();
        }
        catch (NotFoundException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if((TaxTypeEnum.NORMAL.intValue() + "").equals(l_strTaxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;
        }
        else if((TaxTypeEnum.SPECIAL.intValue() + "").equals(l_strTaxType))
        {
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        else if((TaxTypeEnum.STOCK_OPTION.intValue() + "").equals(l_strTaxType))
        {
            l_taxTypeEnum = TaxTypeEnum.STOCK_OPTION;
        }

        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations = 
            (WEB3EquityTypeOrderManagerReusableValidations)
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();     
        l_orderManagerReusableValidations.validateSpecialAccountEstablish(
            l_subAccount,
            l_taxTypeEnum,
            l_dailyDeliveryDate);        
        
        log.exiting(STR_METHOD_NAME);
        return l_taxTypeEnum;
    }
   
    /**
     * �����������͒ʒm�f�[�^�A�_�v�^�C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�������B <BR>
     * �Q�j�@@���������C���X�^���X�Ɉ����̃L���[�f�[�^���Z�b�g����B <BR>
     * �R�j�@@�C���X�^���X��ԋp����B <BR>
     * <BR>
     * �i�f�t�H���g�R���X�g���N�^��private�Ƃ��A <BR>
     * �{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j <BR>
     * <BR>
     * @@param l_hostEqtypeOrderReceipt - 
     * �y�����������͒ʒm�L���[�e�[�u���z�f�[�^�I�u�W�F�N�g <BR>
     * @@return webbroker3.equity.service.delegate.WEB3EquityOrderInputNotifyAdapter
     * @@roseuid 402776A60369
     */
    public static WEB3EquityOrderInputNotifyAdapter create(HostEqtypeOrderReceiptParams l_hostEqtypeOrderReceipt)
    {
        WEB3EquityOrderInputNotifyAdapter l_adapter =
            new WEB3EquityOrderInputNotifyAdapter();
        l_adapter.hostEqtypeOrderReceipt = l_hostEqtypeOrderReceipt;

        return l_adapter;
    }
   
    /**
     * (get�s��R�[�h)<BR>
     * <BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�s��R�[�h�iSONAR�j���A<BR>
     * Web�V�̎s��R�[�h��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��BySONAR( )�ɂ��A<BR>
     * �@@�@@�@@�s��I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�s��BySONAR( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�����������͒ʒm�L���[Params.�،���ЃR�[�h<BR>
     * �@@�@@�@@�s��R�[�h�iSONAR�j�F�@@�����������͒ʒm�L���[Params.�s��R�[�h�iSONAR�j<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�擾�����s��I�u�W�F�N�g.�s��R�[�h ��Ԃ��B<BR>
     * <BR>
     * @@return java.lang.String
     * @@throws WEB3SystemLayerException
     * @@roseuid 4031D82E005D
     */
    public String getMarketCode() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMarketCode()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        Market l_market = null;
        try
        {
	        l_market = l_gentradeFinObjectManager.getMarketBySONAR(
	            this.getHostEqtypeOrderReceipt().getInstitutionCode(),
	            this.getHostEqtypeOrderReceipt().getSonarMarketCode());
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("�s��I�u�W�F�N�g�̎擾�Ɏ��s���܂����B�،���ЃR�[�h:[" +
                this.getHostEqtypeOrderReceipt().getInstitutionCode() + "] " +
                "�s��R�[�h(SONAR):[" + this.getHostEqtypeOrderReceipt().getSonarMarketCode() +
                "]");
            throw l_ex;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_market.getMarketCode();
    }
   
    /**
     * (is�w�l) <BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�w�l�敪���A <BR>
     * AP�w�Ŏg�p����is�w�l��ԋp����B <BR>
     * �w�l�敪��"1�F�w�l"�̏ꍇ��true�A <BR>
     * �w�l�敪��"0�F���s"��false��ԋp����B <BR>
     * ��L�ȊO�͗�O���X���[����B <BR>
     * class�FWEB3BusinessLayerException <BR>
     * tag�F  BUSINESS_ERROR_00069 <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4087B4BF02BA
     */
    public boolean isLimitOrder() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "isLimitOrder()";
        log.entering(STR_METHOD_NAME);
        
        String l_strExecutionType = this.hostEqtypeOrderReceipt.getExecutionType();
        if(WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_strExecutionType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strExecutionType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            WEB3BusinessLayerException l_businessLayerException =  
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00069,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w�l�敪 = " + l_strExecutionType);
            log.error(STR_METHOD_NAME,l_businessLayerException);
            throw l_businessLayerException;
        }
    }
    
    /**
     * (get�l�i����)<BR>
     * <BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�l�i����(SONAR)�ɉ�����<BR>
     * WEB�V�ɂ�����l�i������Ԃ��B<BR>
     * <BR>
     * �g�����������}�l�[�W��.get�l�i����(���������ʒm�L���[Params.�l�i����(SONAR))��<BR>
     * delegate����B<BR>
     * @@return �l�i����
     * @@throws WEB3BaseException
     */
    public String getPriceConditionType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPriceConditionType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        String l_strPriceConditionType = null;
        try
        {
	        l_strPriceConditionType =
	            l_orderMgr.getPriceConditionType(this.getHostEqtypeOrderReceipt().price_condition_type);
        }
        catch (WEB3BaseException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
	            "���������ʒm�L���[Params.�l�i����(SONAR)���s���Ȓl�ł��B:["
	            + this.getHostEqtypeOrderReceipt().price_condition_type + "]");
        }

        log.exiting(STR_METHOD_NAME);
        return l_strPriceConditionType;
    }
    
    
    /**
     * (get�����������͒ʒm�L���[Params)<BR>
     * <BR>
     * @@return �����������͒ʒm�L���[Params
     */
    public HostEqtypeOrderReceiptParams getHostEqtypeOrderReceipt() {
        return this.hostEqtypeOrderReceipt;
    }

    /**
     * (set�����������͒ʒm�L���[Params)<BR>
     * @@param params �����������͒ʒm�L���[Params
     */
    public void setHostEqtypeOrderReceipt(HostEqtypeOrderReceiptParams hostEqtypeOrderReceipt) {
        this.hostEqtypeOrderReceipt = hostEqtypeOrderReceipt;
    }
}
@
