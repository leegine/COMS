head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCommonMessageCreatedServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������ʃ��b�Z�[�W�쐬�T�[�r�XImpl(WEB3FeqCommonMessageCreatedServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  䈋�(���u) �V�K�쐬
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
*/
package webbroker3.feq.service.delegate.stdimpls;


import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.feq.WEB3FeqFinTransactionManager;
import webbroker3.feq.WEB3FeqOrderExecution;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.define.WEB3FeqAccountDivDef;
import webbroker3.feq.message.WEB3AdminFeqExecuteGroup;
import webbroker3.feq.message.WEB3FeqExecDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqOrderAndExecutionUnit;
import webbroker3.feq.message.WEB3FeqOrderCommonUnit;
import webbroker3.feq.service.delegate.WEB3FeqCommonMessageCreatedService;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O���������ʃ��b�Z�[�W�쐬�T�[�r�XImpl) <BR>
 * �O���������ʃ��b�Z�[�W�쐬�T�[�r�X�����N���X
 * 
 * @@author 䈋�
 * @@version 1.0 
 */
public class WEB3FeqCommonMessageCreatedServiceImpl implements WEB3FeqCommonMessageCreatedService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqCommonMessageCreatedServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F4033C
     */
    public WEB3FeqCommonMessageCreatedServiceImpl() 
    {
     
    }
    
    /**
     * (create�O�������������ʖ���) <BR>
     * �����P�ʂ̓��e�ŁA�O�������������ʖ��׃��b�Z�[�W <BR>
     * �I�u�W�F�N�g�v���p�e�B�ɒl���Z�b�g����B <BR>
     *  <BR>
     * �@@�O�������������ʖ��ׂ̊e�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B <BR>
     *  <BR>
     * �@@�����h�c�F�@@�����P��.�����h�c <BR>
     * �@@�^�p�R�[�h�F�@@�����P��.�^�p�R�[�h <BR>
     * �@@���ʃR�[�h�F�@@�����P��.���ʃR�[�h <BR>
     * �@@�`�[�ԍ��F�@@�����P��.�`�[No <BR>
     * �@@���X�R�[�h�F�@@�����P��.get���X�R�[�h() <BR>
     * �@@�ڋq�R�[�h�F�@@�����P��.get�ڋq().get�\���ڋq�R�[�h() <BR>
     * �@@�ڋq���F�@@�����P��.get�ڋq().get�ڋq�\����() <BR>
     * �@@�����R�[�h�F�@@�����P��.getProduct().getProductCode() <BR>
     * �@@���n�����R�[�h�F�@@�����P��.getProduct().getOffshoreProductCode() <BR>
     * �@@�������F�@@�����P��.getProduct().get�\��������() <BR>
     * �@@�s��R�[�h�F�@@�����P��.get�s��().�s��R�[�h  <BR>
     * �@@��������敪�F�@@�����P��.�ŋ敪�ɊY������敪 <BR>
     * �@@�����敪�F�@@�����P��.getSide()�ɊY������敪 <BR>
     * �@@���ϋ敪�F�@@�����P��.���ϋ敪 <BR>
     * �@@���s�����F�@@�����P��.getExecutionConditionType()�ɊY������敪 <BR>
     * �@@�����L�������F�@@�����P��.�����������t <BR>
     * �@@���������F�@@�����P��.�������� <BR>
     * �@@���������P���F�@@�����P��.�t�w�l��l <BR>
     * �@@�����������Z�q�F�@@�����P��.�����������Z�q <BR>
     * �@@�v�w�l�p�����P���敪�F�@@ <BR>
     * �@@�@@���i�������� == �v�w�l�j�̏ꍇ�̂݃Z�b�g <BR>
     * �@@�@@�����P��.�iW�w�l�j�����w�l == 0�̏ꍇ�A�h���s�h�B�ȊO�h�w�l�h�B  <BR>
     * �@@�v�w�l�p�����P���F�@@�����P��.�iW�w�l�j�����w�l<BR> 
     * �@@�������ʁF�@@�����P��.�������� <BR>
     * �@@�����P���敪�F�@@�i�����P��.is�w�l() == true�j�̏ꍇ�h�w�l�h�A <BR>
     * �@@�@@�@@�@@�ȊO�h���s�h�B <BR>
     * �@@�����P���F�@@�����P��.�w�l <BR>
     * �@@�ʉ݃R�[�h�F�@@�����P��.�ʉ݃R�[�h <BR>
     * �@@�T�Z��n����F�@@�����P��.�T�Z��n��� <BR>
     * �@@�T�Z��n����i�O�݁j�F�@@�����P��.�T�Z��n����i�O�݁j <BR>
     * �@@�������ԁF�@@�����P��.�󒍓��� <BR>
     * �@@�������F�@@�����P��.������ <BR>
     *   �ڋq�敪�F�@@�����P��.�ڋq�敪<BR>
     * @@param l_feqOrderCommonUnit - (�O�������������ʖ���) <BR>
     * �O�������������ʖ��׃��b�Z�[�W�I�u�W�F�N�g
     * 
     * @@param l_feqOrderUnit - (�����P��)
     * @@throws WEB3BaseException
     * @@roseuid 42943F200046
     */
    public void createFeqOrderCommonUnit(WEB3FeqOrderCommonUnit l_feqOrderCommonUnit, 
        WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createFeqOrderCommonUnit";
        log.entering(STR_METHOD_NAME);

        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
        // �@@�����h�c�F�@@�����P��.�����h�c 
        l_feqOrderCommonUnit.orderId = "" + l_feqOrderUnitRow.getOrderId();
        // �@@�^�p�R�[�h�F�@@�����P��.�^�p�R�[�h 
        l_feqOrderCommonUnit.managementCode = l_feqOrderUnitRow.getOrderEmpCode();
        // �@@���ʃR�[�h�F�@@�����P��.���ʃR�[�h 
        l_feqOrderCommonUnit.requestNumber = l_feqOrderUnitRow.getOrderRequestNumber();
        // �@@�`�[�ԍ��F�@@�����P��.�`�[No 
        l_feqOrderCommonUnit.orderNumber = l_feqOrderUnitRow.getVoucherNo();
        // �@@���X�R�[�h�F�@@�����P��.get���X�R�[�h() 
        l_feqOrderCommonUnit.branchCode = l_feqOrderUnit.getBranchCode();
        // �@@�ڋq�R�[�h�F�@@�����P��.get�ڋq().get�\���ڋq�R�[�h() 
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_feqOrderUnit.getMainAccount();
        l_feqOrderCommonUnit.accountCode = l_mainAccount.getDisplayAccountCode();
        // �@@�ڋq���F�@@�����P��.get�ڋq().get�ڋq�\����() 
        l_feqOrderCommonUnit.accountName = 
            l_mainAccount.getDisplayAccountName();
        // �@@�����R�[�h�F�@@�����P��.getProduct().getProductCode() 
        WEB3FeqProduct l_product = (WEB3FeqProduct)l_feqOrderUnit.getProduct();
        l_feqOrderCommonUnit.productCode = l_product.getProductCode();
        // �@@���n�����R�[�h�F�@@�����P��.getProduct().getOffshoreProductCode() 
        l_feqOrderCommonUnit.localProductCode = l_product.getOffshoreProductCode();
        // �@@�������F�@@�����P��.getProduct().get�\��������() 
        l_feqOrderCommonUnit.productName = l_product.getDisplayProductName();
        // �@@�s��R�[�h�F�@@�����P��.get�s��().�s��R�[�h 
        l_feqOrderCommonUnit.marketCode= l_feqOrderUnit.getMarket().getMarketCode();
        // �@@��������敪�F�@@�����P��.�ŋ敪�ɊY������敪 
        if (TaxTypeEnum.NORMAL.equals(l_feqOrderUnitRow.getTaxType()))
        {
            l_feqOrderCommonUnit.taxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_feqOrderUnitRow.getTaxType()) ||
            TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_feqOrderUnitRow.getTaxType()))
        {
            l_feqOrderCommonUnit.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }
        
        // �@@�����敪�F�@@�����P��.getSide()�ɊY������敪 
        if (SideEnum.BUY.equals(l_feqOrderUnit.getSide()))
        {   
            l_feqOrderCommonUnit.dealingType = WEB3BuySellTypeDef.BUY;
        }
        else if (SideEnum.SELL.equals(l_feqOrderUnit.getSide()))
        {
            l_feqOrderCommonUnit.dealingType = WEB3BuySellTypeDef.SELL;
        }

        // �@@���ϋ敪�F�@@�����P��.���ϋ敪 
        l_feqOrderCommonUnit.settleDiv = l_feqOrderUnitRow.getSettleDiv();
        // �@@���s�����F�@@�����P��.getExecutionConditionType()�ɊY������敪 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        String l_strExeCondType = "" + l_feqOrderUnit.getExecutionConditionType().intValue();
        l_feqOrderCommonUnit.execCondType = l_orderManager.getExecutionConditionTypeSonar(l_strExeCondType);
        // �@@�����L�������F�@@�����P��.�����������t 
        l_feqOrderCommonUnit.expirationDate = l_feqOrderUnitRow.getExpirationDate();
        // �@@���������F�@@�����P��.�������� 
        l_feqOrderCommonUnit.orderCondType = l_feqOrderUnitRow.getOrderConditionType();
        // �@@���������P���F�@@�����P��.�t�w�l��l 
        if (!l_feqOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_feqOrderCommonUnit.orderCondPrice = 
                WEB3StringTypeUtility.formatNumber(l_feqOrderUnitRow.getStopOrderPrice());
        }

        // �@@�����������Z�q�F�@@�����P��.�����������Z�q 
        l_feqOrderCommonUnit.condOperator = l_feqOrderUnitRow.getOrderCondOperator();
        // �@@�v�w�l�p�����P���敪�F�@@ 
        // �@@�@@���i�������� == �v�w�l�j�̏ꍇ�̂݃Z�b�g 
        //    �����P��.�iW�w�l�j�����w�l == 0�̏ꍇ�A�h���s�h�B�ȊO�h�w�l�h�B 
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            if (l_feqOrderUnitRow.getWLimitPrice() == 0)
            {
                l_feqOrderCommonUnit.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                //�v�w�l�p�����P���F�@@�����P��.�iW�w�l�j�����w�l 
                l_feqOrderCommonUnit.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_feqOrderCommonUnit.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_feqOrderUnitRow.getWLimitPrice());
            }
        }

        // �@@�������ʁF�@@�����P��.�������� 
        l_feqOrderCommonUnit.orderQuantity = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderUnit.getQuantity());
        // �@@�����P���敪�F�@@�i�����P��.is�w�l() == true�j�̏ꍇ�h�w�l�h�A 
        // �@@�@@�@@�@@�ȊO�h���s�h�B 
        if (l_feqOrderUnit.isLimitOrder())
        {
            l_feqOrderCommonUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        else
        {
            l_feqOrderCommonUnit.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }

        // �@@�����P���F�@@�����P��.�w�l 
        l_feqOrderCommonUnit.orderPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderUnit.getLimitPrice());
        // �@@�ʉ݃R�[�h�F�@@�����P��.�ʉ݃R�[�h 
        l_feqOrderCommonUnit.currencyCode = l_feqOrderUnitRow.getCurrencyCode();
        // �@@�T�Z��n����F�@@�����P��.�T�Z��n��� 
        l_feqOrderCommonUnit.estimatedPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderUnitRow.getEstimatedPrice());
        // �@@�T�Z��n����i�O�݁j�F�@@�����P��.�T�Z��n����i�O�݁j 
        l_feqOrderCommonUnit.foreignEstimatedPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderUnitRow.getFEstimatedPrice());
        // �@@�������ԁF�@@�����P��.�󒍓��� 
        l_feqOrderCommonUnit.orderDate = 
            l_feqOrderUnitRow.getReceivedDateTime();
        // �@@�������F�@@�����P��.������ 
        l_feqOrderCommonUnit.orderBizDate = 
            WEB3DateUtility.getDate(l_feqOrderUnitRow.getBizDate(), "yyyyMMdd");
        //  �ڋq�敪�F�@@�����P��.�ڋq�敪
        l_feqOrderCommonUnit.accountDiv = l_feqOrderUnitRow.getAccountDiv();
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create�O���������ڍׁi�Ǘ��ҁj) <BR>
     * ���C�g�����U�N�V�����i������薾�ׁj�̓��e�ŁA <BR>
     * �O���������ڍׁi�Ǘ��ҁj���b�Z�[�W�I�u�W�F�N�g�v���p�e�B�ɒl���Z�b�g����B <BR>
     *  <BR>
     * �@@�O���������ڍׁi�Ǘ��ҁj�̊e�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B <BR>
     *  <BR>
     * �@@���P���F�@@���.���P�� <BR>
     * �@@��萔�ʁF�@@���.��萔�� <BR>
     * �@@�������F�@@���.������ <BR>
     * �@@���h�c�F�@@���.���h�c <BR>
     * �@@���ԍ��F�@@���.���ʔԂ��Apattern="000"�Ńt�H�[�}�b�g����������  <BR>
     * �@@��������F�@@���.��萔�ʁ~���.���P�� <BR>
     * �@@���n�萔���F�@@�g�����U�N�V�����i������薾�ׁj.���n�萔�� <BR>
     * �@@���n����ŁF�@@�g�����U�N�V�����i������薾�ׁj.���n����� <BR>
     * �@@���̑��R�X�g�P�F�@@�g�����U�N�V�����i������薾�ׁj.���̑��R�X�g�P <BR>
     * �@@���̑��R�X�g�Q�F�@@�g�����U�N�V�����i������薾�ׁj.���̑��R�X�g�Q <BR>
     * �@@���Z����i�~�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.���n���Z����i�~�݁j <BR>
     * �@@���Z����i�O�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.���n���Z��� <BR>
     * �@@�����萔���i�~�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.�ϑ��萔�� <BR>
     * �@@�����萔���i�O�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.�ϑ��萔���i�O�݁j <BR>
     * �@@����Łi�~�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.�ϑ��萔������� <BR>
     * �@@����Łi�O�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.�ϑ��萔������Łi�O�݁j <BR>
     * �@@��n���z�i�~�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.��n��� <BR>
     * �@@��n���z�i�O�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.��n����i�O�݁j <BR>
     * �@@������n���F�@@���.��n�� <BR>
     * @@param l_adminFeqExecDetailInfoUnit - (�O���������ڍׁi�Ǘ��ҁj) <BR>
     * �O���������ڍׁi�Ǘ��ҁj���b�Z�[�W�I�u�W�F�N�g
     * @@param l_feqExecute - (���)
     * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s) <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 42A0458302FC
     */
    public void createAdminFeqExecDetailInfoUnit
        (WEB3FeqExecDetailInfoUnit l_adminFeqExecDetailInfoUnit, 
        WEB3FeqOrderExecution l_feqExecute, 
        FeqFinTransactionParams l_feqFinTransactionParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAdminFeqExecDetailInfoUnit";
        log.entering(STR_METHOD_NAME);
        FeqOrderExecutionRow l_feqOrderExeRow = 
            (FeqOrderExecutionRow)l_feqExecute.getDataSourceObject();
        // �@@���P���F�@@���.���P�� 
        l_adminFeqExecDetailInfoUnit.execPrice = WEB3StringTypeUtility.formatNumber(l_feqOrderExeRow.getExecPrice());
        // �@@��萔�ʁF�@@���.��萔�� 
        l_adminFeqExecDetailInfoUnit.execQuantity = WEB3StringTypeUtility.formatNumber(l_feqOrderExeRow.getExecQuantity());
        // �@@�������F�@@���.������ 
        l_adminFeqExecDetailInfoUnit.executionTimestamp = l_feqExecute.getExecutionTimestamp();
        // �@@���h�c�F�@@���.���h�c 
        l_adminFeqExecDetailInfoUnit.execId = "" + l_feqOrderExeRow.getOrderExecutionId();
        // �@@���ԍ��F�@@���.���ʔԂ��Apattern="000"�Ńt�H�[�}�b�g���������� 
        l_adminFeqExecDetailInfoUnit.execNo = 
            WEB3StringTypeUtility.formatNumber(l_feqExecute.getExecutionSerialNo(), 3);
        // �@@��������F�@@���.��萔�ʁ~���.���P��
        l_adminFeqExecDetailInfoUnit.foreignTradePrice = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderExeRow.getExecPrice() * l_feqOrderExeRow.getExecQuantity());
            
        WEB3FeqProduct l_product = (WEB3FeqProduct)l_feqExecute.getProduct();
        WEB3GentradeCurrency l_currency = l_product.getCurrency();
        int l_intDecimalPlace = l_currency.getScale();
        BigDecimal l_bdforeignTradePrice = new BigDecimal(l_adminFeqExecDetailInfoUnit.foreignTradePrice);
        l_bdforeignTradePrice =
            l_bdforeignTradePrice.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_adminFeqExecDetailInfoUnit.foreignTradePrice = l_bdforeignTradePrice.toString();

        // �@@���n�萔���F�@@�g�����U�N�V�����i������薾�ׁj.���n�萔��
        l_adminFeqExecDetailInfoUnit.localCommission = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getForeignCommissionFee());
        // �@@���n����ŁF�@@�g�����U�N�V�����i������薾�ׁj.���n�����
        l_adminFeqExecDetailInfoUnit.localTradingTax = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getForeignTax());
        // �@@���̑��R�X�g�P�F�@@�g�����U�N�V�����i������薾�ׁj.���̑��R�X�g�P
        l_adminFeqExecDetailInfoUnit.otherCost1 = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getForeignFeeExt1());
        // �@@���̑��R�X�g�Q�F�@@�g�����U�N�V�����i������薾�ׁj.���̑��R�X�g�Q
        l_adminFeqExecDetailInfoUnit.otherCost2 = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getForeignFeeExt2());
        // �@@���Z����i�~�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.���n���Z����i�~�݁j
        l_adminFeqExecDetailInfoUnit.clearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getBalanceAmount());
        // �@@���Z����i�O�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.���n���Z���
        l_adminFeqExecDetailInfoUnit.foreignClearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getBalanceAmountFc());
        // �@@�����萔���i�~�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.�ϑ��萔��
        l_adminFeqExecDetailInfoUnit.commission = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getCommissionFee());
        // �@@�����萔���i�O�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.�ϑ��萔���i�O�݁j
        l_adminFeqExecDetailInfoUnit.foreignCommission =
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getCommissionFeeFc());
        // �@@����Łi�~�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.�ϑ��萔�������
        l_adminFeqExecDetailInfoUnit.consumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getCommissionFeeTax());
        // �@@����Łi�O�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.�ϑ��萔������Łi�O�݁j
        l_adminFeqExecDetailInfoUnit.foreignConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getCommissionFeeTaxFc());

        //��n��� (�~��)
        if(l_feqFinTransactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_BUY))
        {   
            //��n���z�i�~�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.��n��� �~�i-1�j 
            l_adminFeqExecDetailInfoUnit.deliveryPrice =
                WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getNetAmount() * (-1));
        }
        else if (l_feqFinTransactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_SELL))
        {
            //��n���z�i�~�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.��n���
            l_adminFeqExecDetailInfoUnit.deliveryPrice =
                WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getNetAmount());
        }
        
        
        //��n��� (�O��)        
        if(l_feqFinTransactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_BUY))
        {   
            //��n���z�i�O�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.��n����i�O�݁j�~�i-1�j 
            l_adminFeqExecDetailInfoUnit.foreignDeliveryPrice =
                WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getNetAmountFc() * (-1));
        }
        else if (l_feqFinTransactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_SELL))
        {
            //��n���z�i�O�݁j�F�@@�g�����U�N�V�����i������薾�ׁj.��n����i�O�݁j            
            l_adminFeqExecDetailInfoUnit.foreignDeliveryPrice =
                WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getNetAmountFc());
        }  
                
        // �@@������n���F�@@���.��n�� 
        l_adminFeqExecDetailInfoUnit.deliveryDate = l_feqExecute.getDeliveryDate();
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (create�O�������������ׁi�Ǘ��ҁj <BR>
     * ���C�g�����U�N�V�����i������薾�ׁj�̓��e�ŁA <BR>
     * �O�������������ׁi�Ǘ��ҁj���b�Z�[�W�I�u�W�F�N�g�v���p�e�B�ɒl���Z�b�g����B<BR>
     *  <BR>
     * �P�j�@@super�N���X�̏����Z�b�g <BR>
     * �@@this.create�O�������������ʖ���()���R�[������B <BR>
     *  <BR>
     * �@@[create�O�������������ʖ���()�Ɏw�肷�����] <BR>
     * �@@�O�������������ʖ��ׁF�@@�O�������������ׁi�Ǘ��ҁj <BR>
     * �@@�����P�ʁF�@@�����P�� <BR>
     *  <BR>
     * �Q�j�@@�ʃv���p�e�B�Z�b�g <BR>
     * �@@�O�������������ׁi�Ǘ��ҁj�̊e�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B <BR>
     *  <BR>
     * �@@��������ԍ��F�@@�����P��.���������ŏI�ʔ� <BR>
     * �@@��萔�ʁF�@@�����P��.getExecutedQuantity() <BR>
     * �@@��n����i�O�݁j�F�@@ <BR>
     * �@@�@@�@@�@@�O�������g�����U�N�V�����}�l�[�W��.get��n������v�i�O�݁j(�����P��)  <BR>
     * �@@�����󋵁F �����P��.get������() <BR>
     * @@param l_adminFeqExecuteGroup - (�O�������������ׁi�Ǘ��ҁj) <BR>
     * �O�������������ׁi�Ǘ��ҁj���b�Z�[�W�I�u�W�F�N�g
     * @@param l_feqOrderUnit - (�����P��)
     * @@throws WEB3BaseException
     * @@roseuid 4294416701EC
     */
    public void createAdminFeqExecuteGroup(WEB3AdminFeqExecuteGroup l_adminFeqExecuteGroup, 
        WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAdminFeqExecuteGroup";
        log.entering(STR_METHOD_NAME);

        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
        // �P�j�@@super�N���X�̏����Z�b�g 
        // �@@this.create�O�������������ʖ���()���R�[������B 
        //  
        // �@@[create�O�������������ʖ���()�Ɏw�肷�����] 
        // �@@�O�������������ʖ��ׁF�@@�O�������������ׁi�Ǘ��ҁj 
        // �@@�����P�ʁF�@@�����P�� 
        //
        this.createFeqOrderCommonUnit(l_adminFeqExecuteGroup, l_feqOrderUnit);
        // �Q�j�@@�ʃv���p�e�B�Z�b�g 
        // �@@�O�������������ׁi�Ǘ��ҁj�̊e�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B 
        //  
        // �@@��������ԍ��F�@@�����P��.���������ŏI�ʔ�
        l_adminFeqExecuteGroup.orderActionId = "" + l_feqOrderUnitRow.getLastOrderActionSerialNo();
        // �@@��萔�ʁF�@@�����P��.getExecutedQuantity() 
        l_adminFeqExecuteGroup.execQuantity = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderUnit.getExecutedQuantity());
        
        // �@@��n����F�@@ 
        // �@@�@@�@@�@@�O�������g�����U�N�V�����}�l�[�W��.get��n������v(�����P��) 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqFinTransactionManager l_finTransaction = 
            (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
        double l_dblNetAmount = l_finTransaction.getNetAmount(l_feqOrderUnit);
        if (l_dblNetAmount == 0)
        {
            l_adminFeqExecuteGroup.deliveryPrice = null;
        }
        else
        {
            if(l_feqOrderUnit.isBuy())
            {
                //��n���z�i�~�݁j �~�i-1�j
                l_adminFeqExecuteGroup.deliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblNetAmount * (-1));
            }
            else
            {
                l_adminFeqExecuteGroup.deliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblNetAmount);
            }
        }

        //�@@��n����i�O�݁j�F�@@�O�������g�����U�N�V�����}�l�[�W��.get��n������v�i�O�݁j(�����P��)
        double l_dblNetAmountFc = l_finTransaction.getNetAmountFc(l_feqOrderUnit);
        if (l_dblNetAmountFc == 0)
        {
            l_adminFeqExecuteGroup.foreignDeliveryPrice = null;
        }
        else
        {
            if(l_feqOrderUnit.isBuy())
            {
                //��n���z�i�O�݁j �~�i-1�j 
                l_adminFeqExecuteGroup.foreignDeliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblNetAmountFc * (-1));
            }
            else
            {
                l_adminFeqExecuteGroup.foreignDeliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblNetAmountFc);
            }
            
        }
        
        // �@@�����󋵁F �����P��.get������() 
        l_adminFeqExecuteGroup.transactionStateType = l_feqOrderUnit.getTransactionStateType();
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create�O�����������͏��) <BR>
     * �����P�ʁC���C�g�����U�N�V�����i������薾�ׁj�̓��e�ŁA <BR>
     * �O�������������ׁi�Ǘ��ҁj���b�Z�[�W�I�u�W�F�N�g�v���p�e�B�ɒl���Z�b�g����B <BR>
     *  <BR> 
     * �P�j�@@super�N���X�̏����Z�b�g <BR>
     * �@@this.create�O�������������ʖ���()���R�[������B <BR>
     *  <BR>
     * �@@[create�O�������������ʖ���()�Ɏw�肷�����] <BR>
     * �@@�O�������������ʖ��ׁF�@@�O�����������͏�� <BR>
     * �@@�����P�ʁF�@@�����P�� <BR>
     *  <BR>
     * �Q�j�@@���ڍ׍쐬 <BR>
     * �@@������񂪎w�肳�ꂽ�ꍇ <BR>
     *   �i�g�����U�N�V�����i������薾�ׁj�s != null && ��� != null�j�̂� <BR>
     *  <BR>
     * �@@�O���������ڍׁi�Ǘ��ҁj�𐶐����A <BR>
     * �@@this.create�O���������ڍׁi�Ǘ��ҁj()���R�[�����l���Z�b�g����B <BR>
     *  <BR>
     * �@@[create�O���������ڍׁi�Ǘ��ҁj()�Ɏw�肷�����] <BR>
     * �@@�O���������ڍׁi�Ǘ��ҁj�F�@@�i���������I�u�W�F�N�g�j <BR>
     * �@@���F�@@��� <BR>
     * �@@�g�����U�N�V�����i������薾�ׁj�s�F�@@�g�����U�N�V�����i������薾�ׁj�s <BR>
     *  <BR>
     * �R�j�@@�ʃv���p�e�B�Z�b�g <BR>
     * �@@�O�������������ׁi�Ǘ��ҁj�̊e�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B <BR>
     *  <BR>
     * �@@�ڋq�敪�F�@@ <BR>
     * �@@�@@�i�O�����������͏��.���X�R�[�h�̉E2byte == �h92�h�j�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�h2�F���ȁh�B <BR>
     * �@@�@@�i�O�����������͏��.���X�R�[�h�̉E2byte != �h92�h�j &&  <BR>
     *     �i�O�����������͏��.�ڋq�R�[�h ��2byte == �h80�h�j�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�h1�F���Ǝҁh�B <BR>
     * �@@�@@�ȊO�A�h0�F��ʁh�B <BR>
     * �@@���҃R�[�h�F�@@�����P��.���҃R�[�h�iSONAR�j <BR>
     *  <BR>
     * �@@�� ����񂪎w�肳�ꂽ�ꍇ <BR>
     *    �i�g�����U�N�V�����i������薾�ׁj�s != null && ��� != null�j <BR>
     * �@@�@@���n�v�F�@@�g�����U�N�V�����i������薾�׍s�j.���n���v <BR>
     * �@@�@@���n�v�ŁF�@@�g�����U�N�V�����i������薾�׍s�j.���n�v�� <BR>
     * �@@�@@���בփ��[�g�F�@@���.�בփ��[�g <BR>
     * �@@�@@���n��n���F�@@���.���n��n�� <BR>
     * �@@�@@���ڍׁF�@@�i�Q�j�ō쐬�����I�u�W�F�N�g�j <BR>
     *  <BR>
     * �@@�� ����񂪎w�肳��Ă��Ȃ��ꍇ <BR>
     *    �i�g�����U�N�V�����i������薾�ׁj�s == null || ��� == null�j <BR>
     * �@@�@@���בփ��[�g�F�@@ <BR>
     * �@@�@@�@@�@@�@@�����P��.getProduct().get�ʉ�().get�בփ��[�g()�̖߂�l <BR>
     *  <BR>
     * �@@�@@�@@�@@[get�בփ��[�g()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@is���t�F�@@�����P��.getSide() == SideEnum.���̏ꍇtrue�A�ȊOfalse�B <BR>
     * �@@�@@�@@�@@is���v�Z�F�@@true <BR>
     * �@@�@@�@@�@@���͈בփ��[�g�F�@@0 <BR>
     *  <BR>
     * @@param l_feqOrderAndExecutionUnit - (�O�����������͏��) <BR>
     * �O�����������͏�񃁃b�Z�[�W
     * @@param l_feqOrderUnit - (�����P��)
     * @@param l_feqExecute - (���) <BR>
     *  <BR>
     * �� null�w��� <BR>
     * 
     * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s) <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g <BR>
     *  <BR>
     * �� null�w��� <BR>
     * @@throws WEB3BaseException
     * @@roseuid 429442B70102
     */
    public void createFeqOrderAndExecutionUnit(WEB3FeqOrderAndExecutionUnit l_feqOrderAndExecutionUnit, 
        WEB3FeqOrderUnit l_feqOrderUnit, WEB3FeqOrderExecution l_feqExecute, 
        FeqFinTransactionParams l_feqFinTransactionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createFeqOrderAndExecutionUnit";
        log.entering(STR_METHOD_NAME);
        FeqOrderUnitRow l_feqOrderUnitRow = 
            (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();

        WEB3FeqExecDetailInfoUnit l_feqExecDetailInfoUnit = new WEB3FeqExecDetailInfoUnit();
        // �P�j�@@super�N���X�̏����Z�b�g 
        // �@@this.create�O�������������ʖ���()���R�[������B 
        //  
        // �@@[create�O�������������ʖ���()�Ɏw�肷�����] 
        // �@@�O�������������ʖ��ׁF�@@�O�����������͏�� 
        // �@@�����P�ʁF�@@�����P�� 
        //  
        this.createFeqOrderCommonUnit(l_feqOrderAndExecutionUnit, l_feqOrderUnit);
        // �Q�j�@@���ڍ׍쐬 
        // �@@������񂪎w�肳�ꂽ�ꍇ 
        //   �i�g�����U�N�V�����i������薾�ׁj�s != null && ��� != null�j�̂� 
        //  
        // �@@�O���������ڍׁi�Ǘ��ҁj�𐶐����A 
        // �@@this.create�O���������ڍׁi�Ǘ��ҁj()���R�[�����l���Z�b�g����B 
        //  
        // �@@[create�O���������ڍׁi�Ǘ��ҁj()�Ɏw�肷�����] 
        // �@@�O���������ڍׁi�Ǘ��ҁj�F�@@�i���������I�u�W�F�N�g�j 
        // �@@���F�@@��� 
        // �@@�g�����U�N�V�����i������薾�ׁj�s�F�@@�g�����U�N�V�����i������薾�ׁj�s 
        //  
        if ((l_feqExecute != null) && (l_feqFinTransactionParams != null))
        {
            
            this.createAdminFeqExecDetailInfoUnit
                (l_feqExecDetailInfoUnit, l_feqExecute, l_feqFinTransactionParams);

        }
        // �R�j�@@�ʃv���p�e�B�Z�b�g 
        // �@@�O�������������ׁi�Ǘ��ҁj�̊e�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B 
        //  
        // �@@�ڋq�敪�F�@@ 
        // �@@�@@�i�O�����������͏��.���X�R�[�h�̉E2byte == �h92�h�j�̏ꍇ�A 
        // �@@�@@�@@�@@�@@�@@�h2�F���ȁh�B 
        // �@@�@@�i�O�����������͏��.���X�R�[�h�̉E2byte != �h92�h�j &&  
        //     �i�O�����������͏��.�ڋq�R�[�h ��2byte == �h80�h�j�̏ꍇ�A 
        // �@@�@@�@@�@@�@@�@@�h1�F���Ǝҁh�B 
        // �@@�@@�ȊO�A�h0�F��ʁh�B 
        if (l_feqOrderAndExecutionUnit.branchCode != null)
        {
            int l_length = l_feqOrderAndExecutionUnit.branchCode.length();
            if ("92".equals(l_feqOrderAndExecutionUnit.branchCode.substring(l_length-2)))
            {
                l_feqOrderAndExecutionUnit.accountDiv = WEB3FeqAccountDivDef.SELF;
            }
            else
            {
                if ("80".equals(l_feqOrderAndExecutionUnit.accountCode.substring(0, 2)))
                {
                    l_feqOrderAndExecutionUnit.accountDiv = WEB3FeqAccountDivDef.FELLOW_TRADER;
                }
                else
                {
                    l_feqOrderAndExecutionUnit.accountDiv = WEB3FeqAccountDivDef.NORMAL;
                }
            }
        }

        // �@@���҃R�[�h�F�@@�����P��.���҃R�[�h�iSONAR�j 
        //  
        l_feqOrderAndExecutionUnit.traderCode = l_feqOrderUnitRow.getSonarTraderCode();
        // �@@�� ����񂪎w�肳�ꂽ�ꍇ 
        //    �i�g�����U�N�V�����i������薾�ׁj�s != null && ��� != null�j 
        // �@@�@@���n�v�F�@@�g�����U�N�V�����i������薾�׍s�j.���n���v 
        // �@@�@@���n�v�ŁF�@@�g�����U�N�V�����i������薾�׍s�j.���n�v�� 
        // �@@�@@���בփ��[�g�F�@@���.�בփ��[�g 
        // �@@�@@���n��n���F�@@���.���n��n�� 
        // �@@�@@���ڍׁF�@@�i�Q�j�ō쐬�����I�u�W�F�N�g�j 
        // 
        if ((l_feqFinTransactionParams != null) && (l_feqExecute != null))
        {
            FeqOrderExecutionRow l_feqOrderExeRow = 
                (FeqOrderExecutionRow)l_feqExecute.getDataSourceObject();
            l_feqOrderAndExecutionUnit.passProfit = 
                WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getCapitalGain());
            l_feqOrderAndExecutionUnit.passProfitTax = 
                WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getCapitalGainTax());
            l_feqOrderAndExecutionUnit.execExchangeRate = 
                WEB3StringTypeUtility.formatNumber(l_feqOrderExeRow.getFxRate());
            l_feqOrderAndExecutionUnit.localDeliveryDate = l_feqOrderExeRow.getFDeliveryDate();
            l_feqOrderAndExecutionUnit.execDetailInfoUnit = l_feqExecDetailInfoUnit;
        }
        // �@@�� ����񂪎w�肳��Ă��Ȃ��ꍇ 
        //    �i�g�����U�N�V�����i������薾�ׁj�s == null || ��� == null�j 
        // �@@�@@���בփ��[�g�F�@@ 
        // �@@�@@�@@�@@�@@�����P��.getProduct().get�ʉ�().get�בփ��[�g()�̖߂�l 
        //  
        // �@@�@@�@@�@@[get�בփ��[�g()�Ɏw�肷�����] 
        // �@@�@@�@@�@@is���t�F�@@�����P��.getSide() == SideEnum.���̏ꍇtrue�A�ȊOfalse�B 
        // �@@�@@�@@�@@is���v�Z�F�@@true 
        // �@@�@@�@@�@@���͈בփ��[�g�F�@@0 
        //
        else
        {
            WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)l_feqOrderUnit.getProduct();
            boolean l_isBuy = false;
            if (SideEnum.BUY.equals(l_feqOrderUnit.getSide()))
            {
                l_isBuy = true;
            }
            double l_dblFxRate = l_feqProduct.getCurrency().getExchangeRate(l_isBuy, true, 0);
            l_feqOrderAndExecutionUnit.execExchangeRate = WEB3StringTypeUtility.formatNumber(l_dblFxRate);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
