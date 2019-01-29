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
filename	WEB3EquityOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������N�G�X�g�A�_�v�^(WEB3EquityOrderRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/04 �Ή� (���u) �C��
Revesion History : 2006/11/02 ������@@(���u)���f��No.988
Revesion History : 2006/12/25 �đo�g(���u) ���f��No.1090,No.1098
Revesion History : 2007/06/14 �����q(���u) ���f��No.1173
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquityCommonRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����������N�G�X�g�A�_�v�^�j�B<BR>
 * <BR>
 * ��ʂɈˑ��������������b�v����A�_�v�^�N���X�B<BR>
 * <BR>
 * ���Y�N���X�́A�e�،���Ђ̉�ʗp���ɂ���āA<BR>
 * �����쐬����邱�Ƃ�O��Ƃ���B
 * @@version 1.0
 */
public class WEB3EquityOrderRequestAdapter
{

    /**
     * (���N�G�X�g�f�[�^)<BR>
     */
    public WEB3GenRequest request;

    /**
     * (���O�o�̓��[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityOrderRequestAdapter.class);

    /**
     * @@roseuid 40A1E05202FD
     */
    protected WEB3EquityOrderRequestAdapter()
    {

    }

    /**
     * �����������N�G�X�g�A�_�v�^�C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�������B <BR>
     * �Q�j�@@���������C���X�^���X�Ɉ����̃��N�G�X�g�f�[�^���Z�b�g����B <BR>
     * �R�j�@@�C���X�^���X��ԋp����B <BR>
     * <BR>
     * �i�f�t�H���g�R���X�g���N�^��private�Ƃ��A <BR>
     * �{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g) <BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g <BR>
     * @@return webbroker3.equity.service.delegate.WEB3EquityOrderRequestAdapter
     * @@roseuid 401885E30243
     */
    public static WEB3EquityOrderRequestAdapter create(WEB3GenRequest l_request)
    {
        // �P�j�@@�{�C���X�^���X�𐶐�������B
        WEB3EquityOrderRequestAdapter l_adapter = new WEB3EquityOrderRequestAdapter();
        
        // �Q�j�@@���������C���X�^���X�Ɉ����̃��N�G�X�g�f�[�^���Z�b�g����B
        l_adapter.request = l_request;

        // �R�j�@@�C���X�^���X��ԋp����B
        return l_adapter;
    }

    /**
     * (get���s����) <BR>
     * ��ʂ̎��s�������A<BR>
     * AP�w�Ŏg�p���鎷�s�����iEqTypeExecutionConditionType�j��ԋp����B<BR>
     * <BR>
     * �����������}�l�[�W��.get���s����(���N�G�X�g�f�[�^.���s����)��delegate����B<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType
     * @@roseuid 40188A6E00EB
     */
    public EqTypeExecutionConditionType getExecCondType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecCondType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        WEB3EquityCommonRequest l_request = (WEB3EquityCommonRequest)request;
        return l_orderManager.getExecutionConditionType(l_request.execCondType);
    }

    /**
     * (get�ŋ敪)<BR>
     * <BR>
     * AP�w�Ŏg�p����ŋ敪�iTaxTypeEnum�j��ԋp����B<BR>
     * <BR>
     * �P�j ���蒍���̏ꍇ�ithis.is������( )==true�j�A�Ώ�Asset�̐ŋ敪��ԋp����B<BR>
     * <BR>
     * �@@�@@�Ώ�Asset�̐ŋ敪�F�����|�W�V�����}�l�[�W��.getAsset(���N�G�X�g�f�[�^.ID).�ŋ敪<BR>
     * <BR>
     * �Q�j�@@���������̏ꍇ�ithis.is������( )==false�j�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�|�P�j ��ʌ����̃Z�b�g<BR>
     * �@@���N�G�X�g�f�[�^.�����敪���h��ʁh�̏ꍇ�ATaxTypeEnum.�h��ʁh��ԋp����B<BR>
     * <BR>
     * �Q�|�Q�j�@@��������̃Z�b�g<BR>
     * �@@���N�G�X�g�f�[�^.�����敪���h����h�̏ꍇ�ATaxTypeEnum.�h����h��ԋp����B<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum
     * @@throws WEB3BaseException
     * @@roseuid 40188F9902D0
     */
    public TaxTypeEnum getTaxDivision() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTaxDivision()";
        log.entering(STR_METHOD_NAME);

        TaxTypeEnum l_taxType = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        // �P�j ���蒍���̏ꍇ
        if (this.isSellOrder())
        {
            long l_lngAssetId;
            if (request instanceof WEB3EquitySellConfirmRequest)
            {
                l_lngAssetId = Long.parseLong(((WEB3EquitySellConfirmRequest)request).id);
            }
            else
            {
                l_lngAssetId = Long.parseLong(((WEB3EquitySellCompleteRequest)request).id);
            }
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            try
            {
                l_taxType = l_positionManager.getAsset(l_lngAssetId).getTaxType();
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
        }
        // �Q�j�@@���������̏ꍇ
        else
        {
            String l_strTaxType;
            if (request instanceof WEB3EquityBuyConfirmRequest)
            {
                l_strTaxType = ((WEB3EquityBuyConfirmRequest)request).taxType;
            }
            else
            {
                l_strTaxType = ((WEB3EquityBuyCompleteRequest)request).taxType;
            }
            // �Q�|�P�j ��ʌ����̃Z�b�g
            if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_strTaxType))
            {
                l_taxType = TaxTypeEnum.NORMAL;
            }
            // �Q�|�Q�j�@@��������̃Z�b�g
            else
            {
            	l_taxType = TaxTypeEnum.SPECIAL;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_taxType;
    }

    /**
     * (is������) <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A <BR>
     * AP�w�Ŏg�p����is��������ԋp����B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�������������t���N�G�X�g�̏ꍇ��true�A <BR>
     * �����������t���N�G�X�g�̏ꍇ��false��ԋp����B <BR>
     * <BR>
     * �ȊO�͗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag:   BUSINESS_ERROR_00170 <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 40188FC6008D
     */
    public boolean isSellOrder() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSellOrder()";
        log.entering(STR_METHOD_NAME);

        if (request instanceof WEB3EquitySellCompleteRequest || 
            request instanceof WEB3EquitySellConfirmRequest)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else if (request instanceof WEB3EquityBuyCompleteRequest || 
                  request instanceof WEB3EquityBuyConfirmRequest)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.error("__isSellOrder�Ń��N�G�X�g�f�[�^�̔����ȊO�̏ꍇ�ŃG���[__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00170,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get�P��) <BR>
     * �P�������N�G�X�g�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * ���N�G�X�g.�����P���敪=="�w�l"�̏ꍇ�́A<BR>
     * ���N�G�X�g�f�[�^.�����P����ԋp����B<BR>
     * <BR>
     * ���N�G�X�g.�����P���敪=="���s"�̏ꍇ�́A<BR>
     * 0��ԋp����B<BR>
     * <BR>
     * @@return double
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum
     * @@throws WEB3BaseException
     */
    public double getPrice() throws WEB3BaseException
    {
        double l_dbLimitPrice = 0D;
        WEB3EquityCommonRequest l_request = (WEB3EquityCommonRequest)request;
        // ���N�G�X�g.�����P���敪=="�w�l"�̏ꍇ
        if (l_request.orderPriceDiv.equals(WEB3OrderPriceDivDef.LIMIT_PRICE))
        {
            l_dbLimitPrice = Double.parseDouble(l_request.limitPrice);
            return l_dbLimitPrice;
        }
        return l_dbLimitPrice;
    }
    
    /**
     * (get�����R�[�h)<BR>
     * �����R�[�h���擾����B<BR>
     * <BR>
     * �P�j�@@�������̏ꍇ�iis������()==false�j<BR>
     * <BR>
     * �@@�@@���N�G�X�g.�����R�[�h��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�������̏ꍇ�iis������()==true�j<BR>
     * <BR>
     * �@@�@@�����|�W�V�����}�l�[�W��.getAsset(���N�G�X�g.ID�i==�ۗL���YID�j)�ɂ��A<BR>
     * �@@�@@�ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�擾�����ۗL���Y.getProduct()���A�����R�[�h��ԋp����B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getProductCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductCode()";
        log.entering(STR_METHOD_NAME);
        
        String l_strProductCode = null;
        
        if (!this.isSellOrder())
        {
            if (request instanceof WEB3EquityBuyConfirmRequest)
            {
                l_strProductCode =
                    ((WEB3EquityBuyConfirmRequest)request).productCode;
            }
            else if (request instanceof WEB3EquityBuyCompleteRequest)
            {
                l_strProductCode =
                    ((WEB3EquityBuyCompleteRequest)request).productCode;
            }
        }
        else
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            long l_lngAssetId = 0L;
            String l_strId = null;
            if (request instanceof WEB3EquitySellConfirmRequest)
            {
                l_strId = ((WEB3EquitySellConfirmRequest)request).id;
            }
            else if (request instanceof WEB3EquitySellCompleteRequest)
            {
                l_strId = ((WEB3EquitySellCompleteRequest)request).id;
            }
            if (l_strId != null)
            {
                l_lngAssetId = Long.parseLong(l_strId);
            }
            Asset l_asset = null;
            try
            {
                l_asset = l_positionManager.getAsset(l_lngAssetId);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_asset.getProduct();
            l_strProductCode = l_product.getProductCode();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strProductCode;
    }
    
    /**
     * (get�i�v�w�l�j���s����)<BR>
     * ��ʂ̎��s�������A<BR>
     * AP�w�Ŏg�p���鎷�s�����iEqTypeExecutionConditionType�j��ԋp����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.get���s����(���N�G�X�g�f�[�^.�i�v�w�l�j���s����)��delegate����B<BR>
     * <BR>
     * @@return EqTypeExecutionConditionType
     * @@throws WEB3BaseException
     */
    public EqTypeExecutionConditionType getWLimitExecCondType()
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitExecCondType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.EQUITY).getOrderManager();
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType = null;

        if (this.request instanceof WEB3EquityBuyConfirmRequest)
        {
            l_eqTypeExecutionConditionType =
                l_orderManager.getExecutionConditionType(
                    ((WEB3EquityCommonRequest)this.request).wlimitExecCondType);
            log.exiting(STR_METHOD_NAME);
            return l_eqTypeExecutionConditionType;
        }
        else if (this.request instanceof WEB3EquityBuyCompleteRequest)
        {
            l_eqTypeExecutionConditionType =
                l_orderManager.getExecutionConditionType(
                    ((WEB3EquityCommonRequest)this.request).wlimitExecCondType);
            log.exiting(STR_METHOD_NAME);
            return l_eqTypeExecutionConditionType;
        }
        else if (this.request instanceof WEB3EquitySellConfirmRequest)
        {
            l_eqTypeExecutionConditionType =
                l_orderManager.getExecutionConditionType(
                    ((WEB3EquityCommonRequest)this.request).wlimitExecCondType);
            log.exiting(STR_METHOD_NAME);
            return l_eqTypeExecutionConditionType;
        }
        else if (this.request instanceof WEB3EquitySellCompleteRequest)
        {
            l_eqTypeExecutionConditionType =
                l_orderManager.getExecutionConditionType(
                    ((WEB3EquityCommonRequest)this.request).wlimitExecCondType);
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
     * (get�s��R�[�h)<BR>
     * �s��R�[�h���擾����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^.�s��R�[�h != "99�F�D��s��"�̏ꍇ<BR>
     * �@@�@@���N�G�X�g�f�[�^.�s��R�[�h��ԋp����B <BR>
     * <BR>
     * �Q�j�@@��L�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�g���v���_�N�g�}�l�[�W��.getProduct()���R�[�����A�����������擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�mgetProduct()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����R�[�h�@@�@@�@@�F�@@this.get�����R�[�h( )�̖߂�l<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@��������.get�D��s��()���R�[�����A�s����擾����B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�Q�|�Q�j�Ŏ擾�����s�ꂪnull�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:�@@ WEB3ErrorCatalog.BUSINESS_ERROR_02702<BR>
     * �@@�@@�@@�@@�@@�@@�ȊO�A�s��.getMarketCode()�̖߂�l��ԋp����B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getMarketCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarketCode()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���N�G�X�g�f�[�^.�s��R�[�h != "99�F�D��s��"�̏ꍇ
        //�@@�@@���N�G�X�g�f�[�^.�s��R�[�h��ԋp����B
        String l_strMarketCode = null;
        if (request instanceof WEB3EquityBuyConfirmRequest)
        {
            l_strMarketCode =
                ((WEB3EquityBuyConfirmRequest)request).marketCode;
        }
        else if (request instanceof WEB3EquityBuyCompleteRequest)
        {
            l_strMarketCode =
                ((WEB3EquityBuyCompleteRequest)request).marketCode;
        }
        else if (request instanceof WEB3EquitySellConfirmRequest)
        {
            l_strMarketCode =
                ((WEB3EquitySellConfirmRequest)request).marketCode;
        }
        else if (request instanceof WEB3EquitySellCompleteRequest)
        {
            l_strMarketCode =
                ((WEB3EquitySellCompleteRequest)request).marketCode;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (!WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_strMarketCode))
        {
            return l_strMarketCode;
        }

        //�Q�j�@@��L�ȊO�̏ꍇ
        //�@@�Q�|�P�j�@@�g���v���_�N�g�}�l�[�W��.getProduct()���R�[�����A�����������擾����B
        //�@@�@@�@@�@@�@@�@@�mgetProduct()�ɐݒ肷�����]
        //�@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h
        //�@@�@@�@@�@@�@@�@@�@@�����R�[�h�@@�@@�@@�F�@@this.get�����R�[�h( )�̖߂�l
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
        String l_strInstitutionCode = l_context.getInstitutionCode();
        Institution l_institution = null;
        WEB3EquityProduct l_eqTypeProduct = null;
        try
        {
            l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            l_eqTypeProduct =
                (WEB3EquityProduct)l_equityProductManager.getProduct(l_institution, this.getProductCode());
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
        //�ȊO�A�s��.getMarketCode()�̖߂�l��ԋp����B
        if (l_market == null)
        {
            log.debug("�D��s�ꂪ���w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02702,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�D��s�ꂪ���w��ł��B");
        }
        log.exiting(STR_METHOD_NAME);
        return l_market.getMarketCode();
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
     * �@@�@@�@@�����R�[�h�F�@@this.get�����R�[�h()�̖߂�l<BR>
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
        if (request instanceof WEB3EquityBuyConfirmRequest)
        {
            l_datExpirationDate =
                ((WEB3EquityBuyConfirmRequest)request).expirationDate;
        }
        else if (request instanceof WEB3EquityBuyCompleteRequest)
        {
            l_datExpirationDate =
                ((WEB3EquityBuyCompleteRequest)request).expirationDate;
        }
        else if (request instanceof WEB3EquitySellConfirmRequest)
        {
            l_datExpirationDate =
                ((WEB3EquitySellConfirmRequest)request).expirationDate;
        }
        else if (request instanceof WEB3EquitySellCompleteRequest)
        {
            l_datExpirationDate =
                ((WEB3EquitySellCompleteRequest)request).expirationDate;
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

        if (l_datExpirationDate == null)
        {
            // ���N�G�X�g�f�[�^.�����L��������null�̏ꍇ
            // �@@�@@�@@�@@null��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            // �Q�j�@@��L�ȊO�̏ꍇ
            // �����R�[�h�F�@@this.get�����R�[�h()�̖߂�l
            String l_strProductCode = this.getProductCode();
            // �s��R�[�h�F�@@this.get�s��R�[�h()�̖߂�l
            String l_strMarketCode = this.getMarketCode();
            // �g�����������}�l�[�W��.get�����L������()���R�[�����A�߂�l��ԋp����B
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            l_datExpirationDate = l_orderManager.getExpirationDate(
                l_datExpirationDate, l_strProductCode, l_strMarketCode);

            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }
    }
}
@
