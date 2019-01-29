head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToIfoManualOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�蓮����UnitServiceImpl(WEB3ToIfoManualOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 杊��](���u) �V�K�쐬
                 : 2006/08/24 ������(���u) ���f��No.158
                 : 2006/11/13 �юu��(���u) ���f��No.190
                   2006/11/30 �����(���u) ���f��No.199
                   2006/12/04 �����(���u) ���f��No.204
                   2006/12/19 �����(���u) ���f��No.211
Revesion History : 2007/06/29 ����(���u) ���f��No.237
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoBizLogicProvider;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.service.WEB3RlsRequestSenderService;
import webbroker3.triggerorder.WEB3ToRlsCoopDataManager;
import webbroker3.triggerorder.define.WEB3ToManualOrderErrorCodeDef;
import webbroker3.triggerorder.define.WEB3ToManualTaxTypeDef;
import webbroker3.triggerorder.define.WEB3ToSuccOpProductTypeDef;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualUnit;
import webbroker3.triggerorder.message.WEB3ManualCommissionInfoUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToIfoManualOrderUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�敨OP�蓮����UnitServiceImpl)<BR>
 * �敨OP�蓮�����P���T�[�r�X�����N���X<BR>
 * <BR>
 * �P�����Ƃ̎蓮�������������{����B<BR> 
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 *   
 * @@author 杊��]
 * @@version 1.0
 */
public class WEB3ToIfoManualOrderUnitServiceImpl implements WEB3ToIfoManualOrderUnitService 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToIfoManualOrderUnitServiceImpl.class);

    /**
     * @@roseuid 43F4933F0290
     */
    public WEB3ToIfoManualOrderUnitServiceImpl() 
    {
     
    }
    
    /**
     * (exec�蓮����)<BR>
     * ����1�����Ƃ̎蓮�������s���B<BR>
     * @@param l_strProductType - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * @@param l_strTriggerOrderType - (�����������)<BR>
     * �����������<BR>
     * @@param l_strOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_blnIsUpdated - (is�X�V)<BR>
     * is�X�V<BR>
     * <BR>
     * true�F�@@���[���G���W������̒ʒm�e�[�u���Ƀ��R�[�h��insert����<BR>
     * false�F�@@���[���G���W������̒ʒm�e�[�u���Ƀ��R�[�h��insert���Ȃ�<BR>
     * @@param l_lngSubmitterLoginId - (�����҃��O�C��ID)<BR>
     * �����҃��O�C��ID<BR>
     * @@param l_strSubmitnotifyType - (�ʒm�o�H)<BR>
     * �ʒm�o�H<BR>
     * @@return WEB3FuturesOptionsManualUnit
     * @@throws WEB3BaseException
     * @@roseuid 43EB2E0403AD
     */
    public WEB3FuturesOptionsManualUnit execManualOrder(
        String l_strProductType, 
        String l_strTriggerOrderType, 
        String l_strOrderId, 
        boolean l_blnIsUpdated,
        Long l_lngSubmitterLoginId,
        String l_strSubmitnotifyType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execManualOrder(String, String, String, boolean, Long, String)";
        log.entering(STR_METHOD_NAME);
        
        long l_lngOrderId = Long.parseLong(l_strOrderId);
        
        WEB3FuturesOptionsManualUnit l_manualUnit = null;
        try
        {
            try
            {
                //1.1 getOrderUnits(arg0 : long)
                //[����]
                //����ID�F�@@�p�����[�^.����ID
                //�ȍ~�̏����ł́A�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f���g�p����B
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3OptionOrderManagerImpl l_orderManager = 
                    (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
                WEB3GentradeAccountManager l_accountManager = 
                    (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                
                OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
                
                //1.2 �i����t���[�F getOrderUnits()�̖߂�l�̗v�f��  == 0�̏ꍇ�j
                if (l_orderUnits.length == 0)
                {
                    //1.2.1 �敨OP�蓮����Unit( )
                    //�敨OP�蓮����Unit�C���X�^���X�𐶐�����B
                    l_manualUnit = new WEB3FuturesOptionsManualUnit();
                    
                    //1.2.2 (*)�v���p�e�B�Z�b�g
                    //�蓮�����G���[�R�[�h = "�Y�������Ȃ�"
                    //������ȊO�̍���    = null
                    l_manualUnit.manualOrderErrorCode = WEB3ToManualOrderErrorCodeDef.NOT_AVAILABLE;
                    
                    log.exiting(STR_METHOD_NAME);
                    return l_manualUnit;
                }

                //1.3 getProduct()
                IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit) l_orderUnits[0];
                IfoProduct l_ifoProduct = (IfoProduct) l_ifoOrderUnit.getProduct();

                //1.4 get�����Y�����R�[�h()
                String l_strUnderlyingProductCode = l_ifoProduct.getUnderlyingProductCode();

                //1.5 getBranchId( )
                long l_lngBranchId = l_ifoOrderUnit.getBranchId();
                
                //1.6 getBranch(arg0 : long)
                WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_accountManager.getBranch(l_lngBranchId);

                //����J�����_�R���e�L�X�g.������t���i�ɏ��i�敪���Z�b�g����B
                IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow) l_ifoOrderUnit.getDataSourceObject();
                String l_strOrderAccProduct = "";
                //�����P��.�敨/�I�v�V�����敪�F�敨�̏ꍇ
                if (WEB3FuturesOptionDivDef.FUTURES.equals(l_ifoOrderUnitRow.getFutureOptionDiv()))
                {
                    l_strOrderAccProduct = WEB3OrderAccProductDef.FUTURE;
                }
                //�����P��.�敨/�I�v�V�����敪�F�I�v�V�����̏ꍇ
                else if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoOrderUnitRow.getFutureOptionDiv()))
                {
                    l_strOrderAccProduct = WEB3OrderAccProductDef.OPTION;
                }

                String l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
                String l_strBranchCode = l_branch.getBranchCode();

                //������t�g�����U�N�V����
                String l_strOrderAcceptTransaction = null;

                //�p�����[�^.����������� == "�t�w�l"�̏ꍇ
                if (WEB3TriggerOrderTypeDef.STOP.equals(l_strTriggerOrderType))
                {
                    //"���t(�V�K��)"
                    l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
                }

                //�p�����[�^.����������� == "W�w�l"�̏ꍇ
                else if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_strTriggerOrderType))
                {
                    //"����"
                    l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.CHANGE;
                }

                //1.7 set����J�����_�R���e�L�X�g()
                this.setTradingCalendarContext(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strUnderlyingProductCode,
                    l_strOrderAccProduct,
                    l_strOrderAcceptTransaction);

                //1.8 validate������t�\()
                WEB3GentradeTradingTimeManagement.validateOrderAccept();

                //1.9 is����ԑ�()
                boolean l_blnIsSessionTimeZone =
                    WEB3GentradeTradingTimeManagement.isSessionTimeZone();

                //1.10 ������t���[�Fis����ԑт�false��ԋp�����ꍇ
                if (!l_blnIsSessionTimeZone)
                {
                    log.debug(STR_METHOD_NAME + "�F��t�\���ԊO");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                        WEB3GentradeTradingTimeManagement.class.getName()
                            + "." + STR_METHOD_NAME);
                }

                //1.13 getAccountId( )
                long l_lngAccountId = l_ifoOrderUnit.getAccountId();
                
                //1.14 getMainAccount(arg0 : long)
                WEB3GentradeMainAccount l_mainAccount =
                    (WEB3GentradeMainAccount) l_accountManager.getMainAccount(l_lngAccountId); 
                
                //1.15 getOP��������^�C�v( )
                SubAccountTypeEnum l_opSubAccountType = l_mainAccount.getOpSubAccountType();
                
                //1.16 get�\���ڋq�R�[�h( )
                String l_strDisplayAccountCode = l_mainAccount.getDisplayAccountCode();

                //1.17 get���i�敪(IfoOrderUnit)
                //[����]
                //�����P�ʁF�@@�����P��
                String l_strCommodity = WEB3IfoDataAdapter.getCommodityDiv(l_ifoOrderUnit);
                
                //1.18 get����敪(������� : OrderTypeEnum)
                //[����]  
                //������ʁF�@@�����P��.�������
                String l_strTradingType = WEB3IfoDataAdapter.getTradingType(l_ifoOrderUnit.getOrderType());
                
                //1.19 get������ԋ敪(�����P�� : IfoOrderUnit)
                //[����]  
                //�����P�ʁF�@@�����P��
                String l_strOrderState = WEB3IfoDataAdapter.getOrderStatusType(l_ifoOrderUnit);
                
                //1.20 get���s����(PR�w)(���s���� : IfoOrderExecutionConditionType)
                //[����]  
                //���s�����F�@@�����P��.���s����
                String l_strPRExecCond = WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnit.getExecutionConditionType());
                
                //1.21 get����ԋ敪(�����P�� : IfoOrderUnit)
                //[����]  
                //�����P�ʁF�@@�����P��
                String l_strExecType = WEB3IfoDataAdapter.getExecStatusType(l_ifoOrderUnit);

                //1.22 get�����󋵋敪(�����P�� : IfoOrderUnit, ����������� : String)
                //[����] 
                //�����P�ʁF�@@�����P�� 
                //����������ʁF�@@�p�����[�^.�����������
                String l_strTriggerOrderState = WEB3IfoDataAdapter.getTriggerOrderStatusType(l_ifoOrderUnit, l_strTriggerOrderType);

                //1.23 get�����󋵋敪(IfoOrderUnit)
                String l_strTransStatusType = WEB3IfoDataAdapter.getTransactionStatusType(l_ifoOrderUnit);

                //1.24 isMarketOrder( )
                boolean l_blnIsMarketOrder = l_ifoOrderUnit.isMarketOrder();
                
                //1.25 getTradedProduct( )
                TradedProduct l_tradedProduct = l_ifoOrderUnit.getTradedProduct();
                
                //1.26 is���A���ڋq( )
                boolean l_blnIsRealCustomer = l_mainAccount.isRealCustomer();
                
                //1.27 getQuote(tradedProduct : TradedProduct, realType : RealType)
                //��������F�@@getTradedProduct( )�̖߂�l
                //realType�F�@@�ڋq.is���A���ڋq( )==true�̏ꍇ�́h���A���h�A 
                //    �@@�@@�@@�@@false�̏ꍇ�́h20���f�B���C�h���Z�b�g�B
                RealType l_realType = null;
                if (l_blnIsRealCustomer)
                {
                    l_realType = RealType.REAL;
                }
                else
                {
                    l_realType = RealType.DELAY;
                }
                
                WEB3QuoteDataSupplierService l_supplierService = 
                    (WEB3QuoteDataSupplierService) l_tradingModule.getQuoteDataSupplierService();
                WEB3IfoQuoteData l_quoteData
                    = (WEB3IfoQuoteData) l_supplierService.getQuote(l_tradedProduct, l_realType);
                
                if (l_quoteData == null)
                {
                    log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�e�[�u���ɊY������f�[�^������܂���B");
                }
                
                //1.28 getCurrentPrice( )
                double l_dblCurrentPrice = l_quoteData.getCurrentPrice();
                
                //1.29 getChange( )
                double l_dblChange = l_quoteData.getChange();
                
                //1.30 getCurrentPriceTime( )
                Timestamp l_tsCurrentPriceTime = l_quoteData.getCurrentPriceTime();
                
                //1.31 create���ʖ���ByOrder(�����h�c : long)
                //[����] 
                //�����h�c�F�@@�p�����[�^.���N�G�X�g�f�[�^.����ID
                WEB3FuturesOptionsContractUnit[] l_contractUnits = 
                    l_orderManager.createContractUnitByOrder(l_lngOrderId);
                
                //1.32 get�s��ǌx���w��(���X : ���X, �敨�^�I�v�V�����敪 : String)
                //[get�s��ǌx���w��()�Ɏw�肷�����] 
                //���X�F�@@.getBranch() �̖߂�l
                //�敨�^�I�v�V�����敪�F�@@�h�I�v�V�����h 
                String[] l_strTradeCloseSuspensions =
                    WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_branch, WEB3FuturesOptionDivDef.OPTION);

                //1.33 validate�蓮��������(�����P�� : OrderUnit, ����������� : String)
                //[����]
                //�����P�ʁF�@@�����P��
                //����������ʁF�@@�p�����[�^.�����������
                String l_strManualOrder =
                    WEB3ToRlsCoopDataManager.validateManualOrder(l_ifoOrderUnit, l_strTriggerOrderType);
                
                //1.34 getSubAccount(arg0 : long, arg1 : SubAccountTypeEnum)
                //[����]
                //����ID�F�@@getAccountId()�̖߂�l
                //�⏕�����^�C�v �F�@@getOP��������^�C�v�̖߂�l
                SubAccount l_subAccount = l_accountManager.getSubAccount(l_lngAccountId, l_opSubAccountType);

                //W�w�l�����i�����P��.�������� == "W�w�l"�j�̏ꍇ
                WEB3ManualCommissionInfoUnit l_commissionInfoUnit = null;
                WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = null;
                if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
                {
                    //calc�X�g�b�v�����ؑ֌�T�Z���(IfoOrderUnit, �⏕����)
                    //�X�g�b�v�����ؑ֌�̊T�Z������Z�o����B
                    //[����]
                    //�@@�����P�ʁF�@@�����P��
                    //�@@�⏕�����F�@@getSubAccount()�̖߂�l
                    l_estimateDeliveryAmountCalcResult =
                        this.calcStopOrderSwitchOverEstimatedPrice(
                            l_ifoOrderUnit,
                            (WEB3GentradeSubAccount)l_subAccount);

                    //create�蓮�����萔�����(�敨OP�T�Z��n����v�Z����)
                    //�蓮�����萔�����𐶐�����B 
                    //[����] 
                    //�@@�T�Z����v�Z���ʁF�@@calc�X�g�b�v�����ؑ֌�T�Z���()�̖߂�l
                    l_commissionInfoUnit = this.createManualCommissionInfoUnit(l_estimateDeliveryAmountCalcResult);
                }
                //��L�ȊO�̏ꍇ
                else
                {
                    //1.35 create�蓮�����萔�����(IfoOrderUnit, SubAccount)
                    l_commissionInfoUnit =
                        this.createManualCommissionInfoUnit(l_ifoOrderUnit, l_subAccount);
                }

                //1.36 get���[���G���W������̒ʒm�f�[�^(�����P�ʁFOrderUnit, ����������ʁFString, �����^�C�v�FProductTypeEnum)
                RlsConOrderHitNotifyParams l_rlsConOrderHitNotifyParams =
                    WEB3ToRlsCoopDataManager.getRLSConOrderHitNotifyData(
                        l_orderUnits[0],
                        l_strTriggerOrderType,
                        new ProductTypeEnum(ProductTypeEnum.IntValues.IFO, l_strProductType)); 
                
                //1.37 �i����t���[�F �p�����[�^.is�X�V == true�̏ꍇ(submit�蓮��������call���ꂽ�ꍇ)
                //       ���Avalidate�蓮��������()�̖߂�l == "����"�̏ꍇ�j
                if (l_blnIsUpdated && l_strManualOrder.equals(WEB3ToManualOrderErrorCodeDef.NORMAL))
                {
                    //1.37.1 sendManualSubmitConOrder(
                    //�@@�@@�⏕���� : SubAccount, 
                    //�@@�@@�����t�����^�C�v : int,
                    //�@@�@@�����̖����^�C�v : ProductTypeEnum,
                    //�@@�@@�����̒���ID : Long,
                    //�@@�@@�e�����̖����^�C�v : ProductTypeEnum,
                    //�@@�@@�e�����̒���ID : Long,
                    //�@@�@@�������� : int)
                    WEB3RlsRequestSenderService l_requestSenderService = 
                        (WEB3RlsRequestSenderService) Services.getService(WEB3RlsRequestSenderService.class);
                    
                    ProductTypeEnum l_productType =
                        new ProductTypeEnum(ProductTypeEnum.IntValues.IFO, l_strProductType);
                    l_requestSenderService.sendManualSubmitConOrder(
                        l_lngSubmitterLoginId,
                        l_strSubmitnotifyType,
                        l_subAccount,
                        Integer.parseInt(l_strTriggerOrderType),
                        l_productType,
                        new Long(l_lngOrderId),
                        null,
                        null,
                        0);
                }
                
                //1.39 �敨OP�蓮����Unit()
                l_manualUnit = new WEB3FuturesOptionsManualUnit();
                //1.40 (*)�v���p�e�B�Z�b�g
                //---------- ���i���ʃv���p�e�B --------------------
                //ID      �� �p�����[�^.����ID
                l_manualUnit.id = l_strOrderId;
                //�����������    �� �p�����[�^.�����������
                l_manualUnit.triggerOrderType = l_strTriggerOrderType;
                //(*1)�����P��.����������("�t�w�l","W�w�l")�̏ꍇ�Z�b�g
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType())
                    || WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
                {
                    //�����������Z�q  �� (*1)�����P��.�����������Z�q
                    l_manualUnit.condOperator = l_ifoOrderUnitRow.getOrderCondOperator();
                    //���������P��    �� (*1)�����P��.�t�w�l��l
                    if (!l_ifoOrderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_manualUnit.orderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());
                    }
                    //���������P���敪    �� (*1)�����P��.�t�w�l��l�^�C�v
                    l_manualUnit.orderCondPriceDiv = l_ifoOrderUnitRow.getStopPriceType();
                }
                
                //(*2)�����P��.�������� == ("W�w�l")�̏ꍇ�Z�b�g
                if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
                {
	                //1.11 get�v�w�l�p�����P���敪(�����P�� : IfoOrderUnit)
	                String l_strWLimitOrderPriceDiv = 
	                    WEB3IfoDataAdapter.getWLimitOrderPriceDiv(l_ifoOrderUnit);
	                
	                //1.12 get���s�����iPR�w�j(���s���� : IfoOrderExecutionConditionType)
	                String l_strExecutionCondByPr = WEB3IfoDataAdapter.getExecutionCondByPr(
	                        l_ifoOrderUnitRow.getWLimitExecCondType());
	                        
                    //W�w�l�p�����P���敪    �� (*2)get�v�w�l�p�����P���敪()�̖߂�l
                    l_manualUnit.wLimitOrderPriceDiv = l_strWLimitOrderPriceDiv;
                    
                    //W�w�l�p�����P��        �� (*2)W�w�l�p�����P���敪��"�w�l"�̏ꍇ�A�����P��.�iW�w�l�j�����w�l���Z�b�g
                    if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strWLimitOrderPriceDiv))
                    {
                        if (!l_ifoOrderUnitRow.getWLimitPriceIsNull())
                        {
                            l_manualUnit.wLimitPrice = 
                                WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getWLimitPrice());
                        }
                        else 
                        {
                            l_manualUnit.wLimitPrice = null;
                        }
                        
                    }
                    
                    //W�w�l�p�������s����    �� (*2)get���s����(PR�w)()�̖߂�l
                    l_manualUnit.wlimitExecCondType = l_strExecutionCondByPr;
                        
                }
                               
                //���X�R�[�h      �� getBranch()�̖߂�l.���X�R�[�h
                l_manualUnit.branchCode = l_strBranchCode; 
                //�ڋq�R�[�h      �� getMainAccount()�̖߂�l.get�\���ڋq�R�[�h()
                l_manualUnit.accountCode = l_strDisplayAccountCode;
                //�s��R�[�h      �� �����P��.�s��ID�ɊY������s��.�s��R�[�h
                WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                    (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                l_manualUnit.marketCode =
                    l_gentradeFinObjectManager.getMarket(l_ifoOrderUnitRow.getMarketId()).getMarketCode();
                
                IfoProductRow l_ifoProductRow = (IfoProductRow) l_ifoProduct.getDataSourceObject();
                //�����R�[�h �� getProduct()�̖߂�l.getProductCode()
                l_manualUnit.productCode = l_ifoProductRow.getProductCode();
                //������      �� getProduct()�̖߂�l.������
                l_manualUnit.productName = l_ifoProductRow.getStandardName();
                //���i�敪        �� get���i�敪()�̖߂�l
                l_manualUnit.productDiv = l_strCommodity;
                //����敪        �� get����敪(PR�w)()�̖߂�l
                l_manualUnit.tradingType = l_strTradingType;
                //���s����        �� getPR�w���s����()�̖߂�l
                l_manualUnit.execCondType = l_strPRExecCond;
                //���������敪        �� �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l
                String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_ifoOrderUnit);
                l_manualUnit.expirationDateType = l_strExpirationDateType;
                //�����L������        �� �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l��
                //"�o����܂Œ���"�̏ꍇ�̂݁A�����P��.�������������Z�b�g�B�ȊO�̏ꍇ�Anull���Z�b�g�B
                if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
                {
                    l_manualUnit.expirationDate = WEB3DateUtility.toDay(l_ifoOrderUnitRow.getExpirationDate());
                }
                else
                {
                    l_manualUnit.expirationDate = null;
                }
                //��������        �� �����P��.��������
                l_manualUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnit.getQuantity());
                //�����P���敪    �� isMarketOrder()�̖߂�l��true�̏ꍇ�A"���s"���Z�b�g�B
                //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@false�̏ꍇ�A"�w�l"���Z�b�g�B
                if (l_blnIsMarketOrder)
                {
                    l_manualUnit.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {
                    l_manualUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    //�����P��        �� �����P���敪��"�w�l"�̏ꍇ�A�����P��.�w�l���Z�b�g�B
                    l_manualUnit.limitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnit.getLimitPrice());
                }
                //������ԋ敪    �� get������ԋ敪(PR�w)()�̖߂�l
                l_manualUnit.orderState = l_strOrderState;
                //����ԋ敪    �� get����ԋ敪(PR�w)()�̖߂�l
                l_manualUnit.execType = l_strExecType;
                //��������敪    �� �����P��.���������E����敪
                l_manualUnit.changeCancelDiv = l_ifoOrderUnitRow.getModifyCancelType();
                //��������        �� �����P��.�󒍓���
                l_manualUnit.orderDate = l_ifoOrderUnitRow.getReceivedDateTime();
                //������      �� �����P��.������
                l_manualUnit.orderBizDate = WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");
                //��n��      �� �����P��.��n��
                l_manualUnit.deliveryDate = l_ifoOrderUnit.getDeliveryDate();
                //�����󋵋敪    �� get�����󋵋敪(PR�w)()�̖߂�l
                l_manualUnit.triggerOrderState = l_strTriggerOrderState;
                //�T�Z��n���    �� �t�w�l�����̏ꍇ�A�����P��.�T�Z��n����B
                //W�w�l�����̏ꍇ�Acalc�X�g�b�v�����ؑ֌�T�Z���()�̖߂�l.�T�Z��n����B
                if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
                {
                    l_manualUnit.estimatedPrice =
                        WEB3StringTypeUtility.formatNumber(
                            l_estimateDeliveryAmountCalcResult.getEstimateDeliveryAmount());
                }
                else
                {
                    if (!l_ifoOrderUnitRow.getEstimatedPriceIsNull())
                    {
                        l_manualUnit.estimatedPrice =
                            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getEstimatedPrice());
                    }
                }
                //���Ϗ���        �� �����P��.���Ϗ���
                l_manualUnit.closingOrder = l_ifoOrderUnitRow.getClosingOrder();
                //�����敪        �� null
                l_manualUnit.currentPriceDiv = null;
                //����(���ݒl)    �� getCurrentPrice()�̖߂�l
                l_manualUnit.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
                //�O����      �� getChange()�̖߂�l
                l_manualUnit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
                //�������(�������\����)  �� getCurrentPriceTime()�̖߂�l
                l_manualUnit.currentPriceTime = l_tsCurrentPriceTime;
                //�蓮�����G���[�R�[�h    �� validate�蓮��������()�̖߂�l
                l_manualUnit.manualOrderErrorCode = l_strManualOrder;
                //�蓮�����萔����� create�蓮�����萔�����()�̖߂�l
                l_manualUnit.commissionInfo = l_commissionInfoUnit;

                //(*4)get���[���G���W������̒ʒm�f�[�^()�̖߂�l��null�̏ꍇ�A�ȉ��̃v���p�e�B���Z�b�g
                if (l_rlsConOrderHitNotifyParams != null)
                {
                    //��������M���ԁ����[���G���W������̒ʒmParams.tick�q�b�g�^�C���X�^���v
                    l_manualUnit.currentPriceInfoAcceptTime = l_rlsConOrderHitNotifyParams.getHitTickTimestamp();
                    //�g���K�[�N�����ԁ����[���G���W������̒ʒmParams.���[���G���W���t�@@�C�A�^�C���X�^���v
                    l_manualUnit.triggerStartTime = l_rlsConOrderHitNotifyParams.getRlsHitTimestamp();
                    //�����������ԁ����[���G���W������̒ʒmParams.���������^�C���X�^���v
                    l_manualUnit.orderCompleteTime = l_rlsConOrderHitNotifyParams.getOrderSubmitTimestamp();
                }
                //�����󋵋敪 �� get�����󋵋敪()�̖߂�l
                l_manualUnit.transactionStateType = l_strTransStatusType;
                //�P�������l��null
                //(*5)�A�������Ή����ɏ�����ǉ�����B
                l_manualUnit.priceAdjustmentValue = null;
                //����敪 �� �����P��.����敪
                l_manualUnit.sessionType = l_ifoOrderUnitRow.getSessionType();

                //---------- �敨�I�v�V���������v���p�e�B --------------------
                //getOP��������^�C�v()�̖߂�l��SubAccountTypeEnum.������������̏ꍇ
                //�����敪        �� "�I�v�V������������"
                if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_opSubAccountType))
                {
                    l_manualUnit.taxType = WEB3ToManualTaxTypeDef.OPTION_BUY_TAX;
                }
                //getOP��������^�C�v()�̖߂�l��SubAccountTypeEnum.�����I�v�V�����������(�敨�؋���)�̏ꍇ
                //�����敪        �� "�敨�I�v�V��������"
                else if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_opSubAccountType))
                {
                    l_manualUnit.taxType = WEB3ToManualTaxTypeDef.FUTURE_OPTION_TAX;
                }
                //�w�����        �� getProduct()�̖߂�l.�����Y�����R�[�h
                l_manualUnit.targetProductCode = l_ifoProduct.getUnderlyingProductCode();
                //����        �� getProduct()�̖߂�l.����
                l_manualUnit.delivaryMonth = l_ifoProduct.getMonthOfDelivery();
                //�s�g���i        �� getProduct()�̖߂�l.�s�g���i
                l_manualUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_ifoProduct.getStrikePrice());
                //�I�v�V�������i�敪  �� 
                //�@@�@@�@@getProduct()�̖߂�l.�敨�I�v�V�������i��
                //�@@�@@�@@�@@�@@�@@�@@"�R�[���I�v�V����"�̏ꍇ�F"�R�[���I�v�V����"
                //�@@�@@�@@getProduct()�̖߂�l.�敨�I�v�V�������i��
                //�@@�@@�@@�@@�@@�@@�@@"�v�b�g�I�v�V����"�̏ꍇ�F"�v�b�g�I�v�V����"
                //�@@�@@�@@��L�ȊO�̏ꍇ��null
                if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
                {
                    l_manualUnit.opProductType = WEB3ToSuccOpProductTypeDef.PUT_OPTIONS;
                }
                else if ((IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType())))
                {
                    l_manualUnit.opProductType = WEB3ToSuccOpProductTypeDef.CALL_OPTIONS;
                }
                else
                {
                    l_manualUnit.opProductType = null;
                }
                //����I���x������    �� get�s��ǌx���w��()�̖߂�l
                l_manualUnit.messageSuspension = l_strTradeCloseSuspensions;
                //���ʖ���        �� create���ʖ���ByOrder()�̖߂�l
                l_manualUnit.contractUnits = l_contractUnits;
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B",
                    l_nfe);
            }
        }
        //1.38 (*)�������ɗ�O�����������ꍇ
        //�@@�E�Ɩ��G���[
        catch (WEB3BusinessLayerException l_be)
        {
            log.debug("�Ɩ��G���[");
            //1.38.1 �敨OP�蓮����Unit()
            l_manualUnit = new WEB3FuturesOptionsManualUnit();
            //1.38.2 (*)�v���p�e�B�Z�b�g
            //�蓮�����G���[�R�[�h = "���̑��G���["
            l_manualUnit.manualOrderErrorCode = WEB3ToManualOrderErrorCodeDef.OTHER;
        }
        //�@@�E�V�X�e���G���[
        catch (WEB3SystemLayerException l_se)
        {
            log.debug("�V�X�e���G���[");
            //1.38.1 �敨OP�蓮����Unit()
            l_manualUnit = new WEB3FuturesOptionsManualUnit();
            //1.38.2 (*)�v���p�e�B�Z�b�g
            //�蓮�����G���[�R�[�h = "���̑��G���["
            l_manualUnit.manualOrderErrorCode = WEB3ToManualOrderErrorCodeDef.OTHER;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_manualUnit;
    }

    /**
     * (create�蓮�����萔�����)<BR>
     * ���X�|���X�ɃZ�b�g����蓮�����萔�������쐬����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return WEB3ManualCommissionInfoUnit
     * @@throws WEB3BaseException
     * @@roseuid 43F185940346
     */
    public WEB3ManualCommissionInfoUnit createManualCommissionInfoUnit(
        IfoOrderUnit l_orderUnit, 
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createManualCommissionInfoUnit(IfoOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3IfoBizLogicProvider l_bizLogicProvider =
            (WEB3IfoBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();
        //1.1 create�萔��(�����P��ID : long)
        WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(l_orderUnit.getOrderUnitId());
        
        //1.2 calc���o��v�Z�p���(IfoOrderUnit)
        double l_dblExpensesCalcAmount = this.calcExpensesCalcAmount(l_orderUnit);
        
        //1.3 set���o��v�Z�p���(���o��v�Z�p��� : double)
        l_commission.setExpensesCalcAmount(l_dblExpensesCalcAmount);
        
        //1.4 setIs�w�l(is�w�l : boolean)
        l_commission.setIsLimitPrice(false);  
        
        //1.5 calc�ϑ��萔��(�萔�� : �萔��, �⏕���� : SubAccount)
        l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
        
        //1.6 calc�����(���z : double, ��� : Timestamp, �⏕���� : �⏕����)
        double l_dblCommission = l_commission.getCommission();
        double l_dblSalesTax = l_bizLogicProvider.calcSalesTax(
            l_dblCommission,
            l_commission.getOrderBizDate(),
            l_subAccount);
        
        //1.7 �蓮�����萔�����( )
        WEB3ManualCommissionInfoUnit l_commissionInfoUnit = new WEB3ManualCommissionInfoUnit();
        
        //1.8 (*)�v���p�e�B�Z�b�g
        //�萔���R�[�X    �� �萔��.get�萔���R�[�X�R�[�h()�̖߂�l
        l_commissionInfoUnit.commissionCourse = l_commission.getCommissionCourseDiv();
        
        //�萔��       �� �萔��.get�萔�����z()�̖߂�l
        l_commissionInfoUnit.commission =
            WEB3StringTypeUtility.formatNumber(l_dblCommission);
        
        //�萔�������    �� calc�����()�̖߂�l
        l_commissionInfoUnit.commissionConsumptionTax =
            WEB3StringTypeUtility.formatNumber(l_dblSalesTax);
        
        log.exiting(STR_METHOD_NAME);
        return l_commissionInfoUnit;
    }
    
    /**
     * (calc���o��v�Z�p���)<BR>
     * �萔���v�Z�ׂ̈̏��o��v�Z�p������擾����B<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ɂ�蕪�򂵁A�Ή����郁�\�b�h��call����B<BR>
     * <BR>
     * �@@[�p�����[�^.�����P��.�����J�e�S�� ==<BR>
     * �@@�@@"�敨�V�K������" or "OP�V�K������"�̏ꍇ]<BR>
     * �@@�敨OP�v�Z�T�[�r�X.calc�S���������()��call����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���ʁF�@@����.��������<BR>
     * �@@�@@�@@�v�Z�P���F�@@����.�����P��<BR>
     * �@@�@@�@@���XID�F�@@����.getBranchId( )�̖߂�l<BR>
     * �@@�@@�@@�萔�����i�R�[�h�F�@@����.�萔�����i�R�[�h<BR>
     * �@@�@@�@@is�w�l�F�@@����.isMarketOrder() == false�̏ꍇ��true<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����.isMarketOrder() == true�̏ꍇ��false<BR>
     * �@@�@@�@@�敨OP��������F�@@����.getTradedProduct( )�̖߂�l<BR>
     * <BR>
     * �@@[�p�����[�^.�����P��.�����J�e�S�� ==<BR>
     * �@@�@@"�敨�ԍϒ���" or "OP�ԍϒ����̏ꍇ]<BR>
     * �@@�敨OP�v�Z�T�[�r�X.calc�������()��call����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���ʁF�@@����.��������<BR>
     * �@@�@@�@@�v�Z�P���F�@@����.�����P��<BR>
     * �@@�@@�@@�敨OP��������F�@@����.getTradedProduct( )�̖߂�l<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return double
     * throws WEB3BaseException
     * @@roseuid 43F1AFCD0302
     */
    public double calcExpensesCalcAmount(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " calcExpensesCalcAmount(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3IfoBizLogicProvider l_bizLogicProvider =
            (WEB3IfoBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();
        
        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
        double l_dblExpensesCalcAmount = 0;
        
        //�P�j�@@�ȉ��̏����ɂ�蕪�򂵁A�Ή����郁�\�b�h��call����B
        //[�p�����[�^.�����P��.�����J�e�S�� ==
        //�@@"�敨�V�K������" or "OP�V�K������"�̏ꍇ]
        //�敨OP�v�Z�T�[�r�X.calc�S���������()��call����B
        if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg()))
        {
            boolean l_blnIsLimitPrice = false;
            if (l_orderUnit.isMarketOrder())
            {
                //is�w�l�F�@@����.isMarketOrder() == true�̏ꍇ��false
                l_blnIsLimitPrice = false;
            }
            else
            {
                //is�w�l�F�@@����.isMarketOrder() == false�̏ꍇ��true
                l_blnIsLimitPrice = true;
            }
            l_dblExpensesCalcAmount = l_bizLogicProvider.calcRestraintTurnOver(
                l_orderUnit.getQuantity(),
                l_orderUnitRow.getPrice(),
                l_orderUnit.getBranchId(),
                l_orderUnitRow.getCommProductCode(),
                l_blnIsLimitPrice,
                (WEB3IfoTradedProductImpl) l_orderUnit.getTradedProduct());
        }
        
        //[�p�����[�^.�����P��.�����J�e�S�� ==
        //�@@"�敨�ԍϒ���" or "OP�ԍϒ����̏ꍇ]
        //�敨OP�v�Z�T�[�r�X.calc�������()��call����B
        else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg()))
        {
            l_dblExpensesCalcAmount = l_bizLogicProvider.calcTurnOver(
                l_orderUnit.getQuantity(),
                l_orderUnitRow.getPrice(),
                (WEB3IfoTradedProductImpl) l_orderUnit.getTradedProduct());
        }
        
        //�Q�j�@@�P�j�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblExpensesCalcAmount;
    }

    /**
     * (set����J�����_�R���e�L�X�g)<BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR> 
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR> 
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �p�����[�^.���X�R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h<BR> 
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �p�����[�^.�����R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �p�����[�^.������t���i<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h01�F���t(�V�K��)�h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ� <BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strOrderAccProduct - (������t���i)<BR>
     * ������t���i<BR>
     * @@param l_strOrderAcceptTransaction - (������t�g�����U�N�V����)<BR>
     * ������t�g�����U�N�V����<BR>
     * throws WEB3BaseException
     */
    public void setTradingCalendarContext(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strProductCode,
        String l_strOrderAccProduct,
        String l_strOrderAcceptTransaction) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setTradingCalendarContext(" +
            "String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B

        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
        l_context.setInstitutionCode(l_strInstitutionCode);
        //����J�����_�R���e�L�X�g.���X�R�[�h = �p�����[�^.���X�R�[�h
        l_context.setBranchCode(l_strBranchCode);
        //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
        //����J�����_�R���e�L�X�g.�����R�[�h = �p�����[�^.�����R�[�h
        l_context.setProductCode(l_strProductCode);
        //����J�����_�R���e�L�X�g.������t���i = �p�����[�^.������t���i
        l_context.setOrderAcceptProduct(l_strOrderAccProduct);
        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �p�����[�^.������t�g�����U�N�V����
        l_context.setOrderAcceptTransaction(l_strOrderAcceptTransaction);

        //�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�
        //�@@������ԃR���e�L�X�g���Z�b�g����B
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //�Q�j�@@��t�����A���t���[�����Z�b�g����B
        //�@@�|������ԊǗ�.setTimestamp()���R�[������B
        WEB3GentradeTradingTimeManagement.setTimestamp();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�蓮�����萔�����)<BR>
     * �icreate�蓮�����萔�����()�̃I�[�o�[���[�h�j<BR>
     * �����̊T�Z����v�Z���ʂ��蓮�����萔�������쐬����B<BR>
     * <BR>
     * �P�j�@@�蓮�����萔�����C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�萔���R�[�X�F�@@�p�����[�^.�T�Z����v�Z����.�萔���R�[�X<BR>
     * �@@�萔���F�@@�p�����[�^.�T�Z����v�Z����.�萔��<BR>
     * �@@�萔������ŁF�@@�p�����[�^.�T�Z����v�Z����.�萔�������<BR>
     * <BR>
     * �R�j�@@�v���p�e�B�Z�b�g�����C���X�^���X��ԋp����B<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (�敨OP�T�Z��n����v�Z���� �T�Z����v�Z����)<BR>
     * �T�Z��n����v�Z���ʃI�u�W�F�N�g<BR>
     * @@return WEB3ManualCommissionInfoUnit
     */
    public WEB3ManualCommissionInfoUnit createManualCommissionInfoUnit(
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
    {
        //�蓮�����萔�����C���X�^���X�𐶐�����B
        WEB3ManualCommissionInfoUnit l_manualCommissionInfoUnit =
            new WEB3ManualCommissionInfoUnit();

        //���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
        l_manualCommissionInfoUnit.commissionCourse = l_estimateDeliveryAmountCalcResult.getCommissionCourse();
        l_manualCommissionInfoUnit.commission =
            WEB3StringTypeUtility.formatNumber(l_estimateDeliveryAmountCalcResult.getCommission());
        l_manualCommissionInfoUnit.commissionConsumptionTax =
            WEB3StringTypeUtility.formatNumber(l_estimateDeliveryAmountCalcResult.getCommissionTax());

        return l_manualCommissionInfoUnit;
    }

    /**
     * (calc�X�g�b�v�����ؑ֌�T�Z���)<BR>
     * �X�g�b�v�����ؑ֌�̊T�Z��n������Z�o����B <BR>
     * <BR>
     * �P�j�@@�萔���I�u�W�F�N�g���쐬����B <BR>
     * �@@�敨OP�v�Z�T�[�r�X.create�萔��()���R�[������B <BR>
     * <BR>
     * �@@[create�萔��()�Ɏw�肷�����] <BR>
     * �@@�@@�����P��ID�F�@@�p�����[�^.�����P��.�����P��ID <BR>
     * �@@�@@���ʁF�@@�p�����[�^.�����P��.�������� <BR>
     * <BR>
     * �Q�j�@@�쐬�����萔��.setIs�w�l()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@[setIs�w�l()�Ɏw�肷�����] <BR>
     * �@@�@@is�w�l�F�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l == 0�̏ꍇ�Afalse�B <BR>
     * �@@�@@�@@�ȊO�Atrue���Z�b�g�B <BR>
     * <BR>
     * �R�j�@@�����P��.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ <BR>
     * �@@OP�����}�l�[�W��.calc�������T�Z��n���()���\�b�h�� <BR>
     * �@@�R�[������B <BR>
     * <BR>
     * �@@[calc�������T�Z��n���()�Ɏw�肷�����] <BR>
     * �@@�@@�萔���F�@@�쐬�����萔���I�u�W�F�N�g <BR>
     * �@@�@@�w�l�F�@@�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l <BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * �@@�@@�敨OP��������F�@@�p�����[�^.�����P��.getTradedProduct() <BR>
     * �@@�@@���ʁF�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�����F�@@�p�����[�^.�����P��.getSide()  <BR>
     * �@@�@@is�ԍϒ����F <BR>
     * �@@�@@�@@[�p�����[�^.�����P��.�����J�e�S�� == "OP�V�K������"�̏ꍇ] <BR>
     * �@@�@@�@@�@@false���Z�b�g�B <BR>
     * �@@�@@�@@[��L�ȊO�̏ꍇ] <BR>
     * �@@�@@�@@�@@true���Z�b�g�B <BR>
     * �@@�@@��萔�ʁF�@@�p�����[�^.�����P��.��萔�� <BR>
     * �@@�@@���v�����z�F�@@�p�����[�^.�����P��.���v�����z <BR>
     * �@@�@@isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j�Œ� <BR>
     * <BR>
     * �S�j�@@��L�ȊO�i�敨�����j�̏ꍇ�A <BR>
     * �@@�p�����[�^.�����P��.�����J�e�S���ɂ���ăR�[�����郁�\�b�h���Ăѕ�����B <BR>
     * �@@[�p�����[�^.�����P��.�����J�e�S�� == "�敨�V�K������"�̏ꍇ] <BR>
     * �@@�@@�敨�����}�l�[�W��.calc�������T�Z�����()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@�@@[calc�������T�Z�����()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�萔���F�@@�쐬�����萔���I�u�W�F�N�g <BR>
     * �@@�@@�@@�w�l�F�@@�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l <BR>
     * �@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * �@@�@@�@@�敨OP��������F�@@�p�����[�^.�����P��.getTradedProduct() <BR>
     * �@@�@@�@@���ʁF�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�@@��萔�ʁF�@@�p�����[�^.�����P��.��萔�� <BR>
     * �@@�@@�@@���v�����z�F�@@�p�����[�^.�����P��.���v�����z <BR>
     * �@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j�Œ� <BR>
     * <BR>
     * �@@[��L�ȊO�i�ԍϒ����j�̏ꍇ] <BR>
     * �@@�@@�敨�����}�l�[�W��.calc�������T�Z���ϑ��v()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@�@@[calc�������T�Z���ϑ��v()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�萔���F�@@�쐬�����萔���I�u�W�F�N�g <BR>
     * �@@�@@�@@�w�l�F�@@�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l <BR>
     * �@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * �@@�@@�@@�敨OP��������F�@@�p�����[�^.�����P��.getTradedProduct() <BR>
     * �@@�@@�@@�ԍό��ʃG���g���F <BR>
     * �@@�@@�@@�@@�敨OP�|�W�V�����}�l�[�W��.create�ԍό��ʃG���g��(�p�����[�^.�����P��.�����P��ID) <BR>
     * �@@�@@�@@���ʁF�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�@@��萔�ʁF�@@�p�����[�^.�����P��.��萔�� <BR>
     * �@@�@@�@@�����P��ID�F�@@�p�����[�^.�����P��.�����P��ID <BR>
     * �@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j�Œ� <BR>
     * �@@�@@�@@�����F <BR>
     * �@@�@@�@@�@@�p�����[�^.�����P��.getSide()��SideEnum.BUY(��)�i=�������ԍρj�̏ꍇ�A�h���h���Z�b�g�B <BR>
     * �@@�@@�@@�@@�p�����[�^.�����P��.getSide()��SideEnum.SELL(��)�i=�������ԍρj�̏ꍇ�A�h���h���Z�b�g�B <BR>
     * <BR>
     * �T�j�@@�e�T�Z����v�Z���\�b�h�̖߂�l��ԋp����B<BR>
     * @@param l_infoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@return WEB3IfoEstimateDeliveryAmountCalcResult
     * @@throws WEB3BaseException
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcStopOrderSwitchOverEstimatedPrice(
        IfoOrderUnit l_infoOrderUnit,
        WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcStopOrderSwitchOverEstimatedPrice(" +
            "IfoOrderUnit, WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3IfoBizLogicProvider l_bizLogicProvider =
            (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3OptionOrderManagerImpl l_optionOrderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        WEB3FuturesOrderManagerImpl l_futuresOrderManager =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        WEB3IfoPositionManagerImpl l_ifoPositionManagerImpl =
            (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_infoOrderUnit.getDataSourceObject();

        //�P�j�@@�萔���I�u�W�F�N�g���쐬����B
        //�@@�敨OP�v�Z�T�[�r�X.create�萔��()���R�[������B
        //�@@[create�萔��()�Ɏw�肷�����]
        //�@@�@@�����P��ID�F�@@�p�����[�^.�����P��.�����P��ID
        //�@@�@@���ʁF�@@�p�����[�^.�����P��.��������
        WEB3GentradeCommission l_commission =
            l_bizLogicProvider.createCommission(
                l_infoOrderUnit.getOrderUnitId(), l_infoOrderUnit.getQuantity());

        //�Q�j�@@�쐬�����萔��.setIs�w�l()���\�b�h���R�[������B
        //�@@[setIs�w�l()�Ɏw�肷�����]
        //�@@�@@is�w�l�F�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l == 0�̏ꍇ�Afalse�B
        //�@@�@@�@@�ȊO�Atrue���Z�b�g�B
        boolean l_blnIsLimitPrice = true;
        if (l_ifoOrderUnitRow.getWLimitPrice() == 0)
        {
            l_blnIsLimitPrice = false;
        }

        l_commission.setIsLimitPrice(l_blnIsLimitPrice);

        //�R�j�@@�����P��.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ
        //�@@OP�����}�l�[�W��.calc�������T�Z��n���()���\�b�h��
        //�@@�R�[������B
        //�@@[calc�������T�Z��n���()�Ɏw�肷�����]
        //�@@�@@�萔���F�@@�쐬�����萔���I�u�W�F�N�g
        //�@@�@@�w�l�F�@@�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l
        //�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�敨OP��������F�@@�p�����[�^.�����P��.getTradedProduct()
        //�@@�@@���ʁF�@@�p�����[�^.�����P��.��������
        //�@@�@@�����F�@@�p�����[�^.�����P��.getSide()
        //�@@�@@is�ԍϒ����F
        //�@@�@@�@@[�p�����[�^.�����P��.�����J�e�S�� == "OP�V�K������"�̏ꍇ]
        //�@@�@@�@@�@@false���Z�b�g�B
        //�@@�@@�@@[��L�ȊO�̏ꍇ]
        //�@@�@@�@@�@@true���Z�b�g�B
        //�@@�@@��萔�ʁF�@@�p�����[�^.�����P��.��萔��
        //�@@�@@���v�����z�F�@@�p�����[�^.�����P��.���v�����z
        //�@@�@@isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j�Œ�
        boolean l_blnIsClosingContractOrder = true;
        WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult = null;
        if (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_ifoOrderUnitRow.getOrderCateg()))
        {
            l_blnIsClosingContractOrder = false;
        }
        if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoOrderUnitRow.getFutureOptionDiv()))
        {
            l_calcResult =
                l_optionOrderManager.calcChangeEstimateDeliveryAmount(
                    l_commission,
                    l_ifoOrderUnitRow.getWLimitPrice(),
                    l_subAccount,
                    (WEB3IfoTradedProductImpl)l_infoOrderUnit.getTradedProduct(),
                    l_infoOrderUnit.getQuantity(),
                    l_infoOrderUnit.getSide(),
                    l_blnIsClosingContractOrder,
                    l_ifoOrderUnitRow.getExecutedQuantity(),
                    l_ifoOrderUnitRow.getExecutedAmount(),
                    false);
        }

        //�S�j�@@��L�ȊO�i�敨�����j�̏ꍇ�A
        //�@@�p�����[�^.�����P��.�����J�e�S���ɂ���ăR�[�����郁�\�b�h���Ăѕ�����B
        //�@@[�p�����[�^.�����P��.�����J�e�S�� == "�敨�V�K������"�̏ꍇ]
        //�@@�@@�敨�����}�l�[�W��.calc�������T�Z�����()���\�b�h���R�[������B
        //�@@�@@[calc�������T�Z�����()�Ɏw�肷�����]
        //�@@�@@�@@�萔���F�@@�쐬�����萔���I�u�W�F�N�g
        //�@@�@@�@@�w�l�F�@@�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�@@�敨OP��������F�@@�p�����[�^.�����P��.getTradedProduct()
        //�@@�@@�@@���ʁF�@@�p�����[�^.�����P��.��������
        //�@@�@@�@@��萔�ʁF�@@�p�����[�^.�����P��.��萔��
        //�@@�@@�@@���v�����z�F�@@�p�����[�^.�����P��.���v�����z
        //�@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j�Œ�
        else
        {
            if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_ifoOrderUnitRow.getOrderCateg()))
            {
                l_calcResult =
                    l_futuresOrderManager.calcChangeEstimatePrice(
                        l_commission,
                        l_ifoOrderUnitRow.getWLimitPrice(),
                        l_subAccount,
                        (WEB3IfoTradedProductImpl)l_infoOrderUnit.getTradedProduct(),
                        l_infoOrderUnit.getQuantity(),
                        l_ifoOrderUnitRow.getExecutedQuantity(),
                        l_ifoOrderUnitRow.getExecutedAmount(),
                        false);
            }
            //�@@[��L�ȊO�i�ԍϒ����j�̏ꍇ]
            //�@@�@@�敨�����}�l�[�W��.calc�������T�Z���ϑ��v()���\�b�h���R�[������B
            //�@@�@@[calc�������T�Z���ϑ��v()�Ɏw�肷�����]
            //�@@�@@�@@�萔���F�@@�쐬�����萔���I�u�W�F�N�g
            //�@@�@@�@@�w�l�F�@@�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l
            //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
            //�@@�@@�@@�敨OP��������F�@@�p�����[�^.�����P��.getTradedProduct()
            //�@@�@@�@@�ԍό��ʃG���g���F
            //�@@�@@�@@�@@�敨OP�|�W�V�����}�l�[�W��.create�ԍό��ʃG���g��(�p�����[�^.�����P��.�����P��ID)
            //�@@�@@�@@���ʁF�@@�p�����[�^.�����P��.��������
            //�@@�@@�@@��萔�ʁF�@@�p�����[�^.�����P��.��萔��
            //�@@�@@�@@�����P��ID�F�@@�p�����[�^.�����P��.�����P��ID
            //�@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j�Œ�
            //�@@�@@�@@�����F
            //�@@�@@�@@�@@�p�����[�^.�����P��.getSide()��SideEnum.BUY(��)�i=�������ԍρj�̏ꍇ�A�h���h���Z�b�g�B
            //�@@�@@�@@�@@�p�����[�^.�����P��.getSide()��SideEnum.SELL(��)�i=�������ԍρj�̏ꍇ�A�h���h���Z�b�g�B
            else
            {
                SideEnum l_dealing = null;
                if (SideEnum.BUY.equals(l_infoOrderUnit.getSide()))
                {
                    l_dealing = SideEnum.SELL;
                }
                else if (SideEnum.SELL.equals(l_infoOrderUnit.getSide()))
                {
                    l_dealing = SideEnum.BUY;
                }

                SettleContractEntry[] l_settleContractEntry =
                    l_ifoPositionManagerImpl.createSettleContractEntry(l_ifoOrderUnitRow.getOrderUnitId());
                l_calcResult =
                    l_futuresOrderManager.calcChangeEstimateSettlementIncome(
                        l_commission,
                        l_ifoOrderUnitRow.getWLimitPrice(),
                        l_subAccount,
                        (WEB3IfoTradedProductImpl)l_infoOrderUnit.getTradedProduct(),
                        l_settleContractEntry,
                        l_infoOrderUnit.getQuantity(),
                        l_dealing,
                        l_ifoOrderUnitRow.getExecutedQuantity(),
                        l_ifoOrderUnitRow.getOrderUnitId(),
                        false);
            }
        }

        //�T�j�@@�e�T�Z����v�Z���\�b�h�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }
}
@
