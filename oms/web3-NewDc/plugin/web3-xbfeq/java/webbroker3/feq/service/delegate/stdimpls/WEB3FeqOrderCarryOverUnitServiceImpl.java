head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderCarryOverUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������J�zUnitServiceImpl(WEB3FeqOrderCarryOverUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[       
Revesion History : 2007/07/13 �đo�g�@@���f��No.353
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.feq.WEB3FeqAmountCalcResult;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqNewOrderSpec;
import webbroker3.feq.WEB3FeqOrderCarryOverUpdateInterceptor;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.service.delegate.WEB3FeqOrderCarryOverUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�����������J�zUnitServiceImpl)<BR>
 * �O�����������J�zUnitService�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqOrderCarryOverUnitServiceImpl implements 
    WEB3FeqOrderCarryOverUnitService 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderCarryOverUnitServiceImpl.class);  
    
    /**
     * @@roseuid 42CE39F60251
     */
    public WEB3FeqOrderCarryOverUnitServiceImpl() 
    {
     
    }
    
    /**
     * (exec�����J�z)<BR>
     * �ڋq�P�ʂŒ����J�z���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����J�z�jexec�����J�z�v�Q�ƁB<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 42B8A39B0394
     */
    public void execOrderCarryOver(WEB3GentradeMainAccount l_mainAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "execOrderCarryOver(WEB3GentradeMainAccount l_mainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 lock����(String, String, String)(�g���A�J�E���g�}�l�[�W��::lock����)
        //���������b�N����B 
        //[����] 
        //�،���ЃR�[�h�F�@@�p�����[�^.�ڋq.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�p�����[�^.�ڋq.���X�R�[�h 
        //�����R�[�h�F�@@�p�����[�^.�ڋq.�����R�[�h
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                
        //�g���A�J�E���g�}�l�[�W���擾����    
        WEB3GentradeAccountManager l_genAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        l_genAccountManager.lockAccount(
            l_mainAccount.getInstitution().getInstitutionCode(), 
            l_mainAccount.getBranch().getBranchCode(), 
            l_mainAccount.getAccountCode());
        
        //1.2 get�J�z�Ώے����P��(Long, String)
        //�J�z�ΏۂƂȂ钍���P�ʂ��擾����B 
        //[����] 
        //����ID�F�@@�p�����[�^.�ڋq.����ID 
        //�،���ЃR�[�h�F�@@�p�����[�^.�ڋq.�،���ЃR�[�h
        
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        WEB3FeqOrderUnit[] l_web3FeqOrderUnits = 
            l_feqOrderManager.getCarryOverOrderUnit(
                new Long(l_mainAccount.getAccountId()), 
                l_mainAccount.getInstitution().getInstitutionCode());
        
        int l_intLength = 0;
        
        if (l_web3FeqOrderUnits != null)
        {
            l_intLength = l_web3FeqOrderUnits.length;
        }
        
        //1.3 (*)get�J�z�Ώے����P��()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_intLength; i++)
        {
            //1.3.1 �O�����������J�z�X�V�C�x���g�C���^�Z�v�^�𐶐�����B 
            //[����] 
            //�J�z�������P�ʍs�F�@@null 
            //���z�v�Z���ʁF�@@null 
            //�v�Z�P���F�@@0
            WEB3FeqOrderCarryOverUpdateInterceptor l_orderCarryUpdInterceptor = 
                new WEB3FeqOrderCarryOverUpdateInterceptor(
                    null, null, 0);
            
            //1.3.2 setThreadLocalPersistenceEventInterceptor()
            //ThreadLocal�ɃC���^�Z�v�^���Z�b�g����B 
            //[����] 
            //arg0�F�@@���������C���^�Z�v�^
            l_feqOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_orderCarryUpdInterceptor);
            
            //1.3.3 expireOrder(arg0 : long)
            //�J�z�����������������s���B 
            //[����] 
            //arg0�F�@@�����Ώۂ̒����P��.����ID
            ProcessingResult l_processingResult = 
                l_feqOrderManager.expireOrder(l_web3FeqOrderUnits[i].getOrderId());
            
            if (l_processingResult.isFailedResult())
            {
                log.debug("�J�z�����������G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_processingResult.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�J�z�����������G���[");
            }            
        }
        
        //1.4 (*)get�J�z�Ώے����P��()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_intLength; i++)
        {     
            WEB3FeqOrderUnit l_feqOrderUnit = (WEB3FeqOrderUnit)
                l_feqOrderManager.getOrderUnitByOrderId(
                    l_web3FeqOrderUnits[i].getOrderId());
            
            //1.4.1 insert�J�z����(�O�����������P��)
            //�J�z�����V�K�������쐬�A�o�^����B 
            //[����] 
            //�����P�ʁF�@@�����Ώۂ̒����P��
            try
            {
                this.insertOrderCarryOver(l_feqOrderUnit);
            }
            //1.4.2 (*)insert�J�z�����ɂė�O���X���[���ꂽ�ꍇ
            catch( WEB3BaseException l_ex)
            {                
                log.debug(STR_METHOD_NAME, l_ex);
                log.debug("error order_unit_id = [" + l_feqOrderUnit.getOrderUnitId() + "]");
                ErrorInfo l_errorInfo = l_ex.getErrorInfo();
                
                //error_tag��"SYSTEM_ERROR"�Ŏn�܂�ꍇ�́AWEB3SystemLayerException���X���[����B
                if (l_errorInfo.getErrorTag().startsWith("SYSTEM_ERROR"))
                {
                    throw new WEB3SystemLayerException(
                        l_errorInfo,
                        l_ex.getErrorMethod(),
                        l_ex.getMessage(),
                        l_ex.getException());
                }
                
                //1.4.2.1 get�����G���[���R�R�[�h(ErrorInfo)
                //�G���[���ɑΉ����钍���G���[���R�R�[�h���擾����B 
                //[����] 
                //�G���[���F�@@catch������O����擾����ErrorInfo
                String l_strErrorCode = 
                    this.getErrorCode(l_ex.getErrorInfo());
                
                //1.4.2.2 update�J�z������(�O�����������P��, String)
                //�J�z�������̒����G���[���R�R�[�h�Ȃǂ�update����B 
                //[����] 
                //�����P�ʁF�@@�����Ώۂ̒����P�� 
                //�����G���[���R�R�[�h�F�@@get�����G���[���R�R�[�h()�̖߂�l
                this.updateOrderCarryOverBase(l_feqOrderUnit, l_strErrorCode);
            }
        }
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (insert�J�z����)<BR>
     * �J�z�����V�K�������쐬���A�o�^����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����J�z�jinsert�J�z�����v�Q�ƁB<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �����J�z �v(�����J�z�jinsert�J�z���� )<BR>
     * �@@�@@:  1.8validate����]��<BR> 
     * �@@�@@�߂�l�̎���]�͌���.����t���O == false�̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �O�����������P�ʃI�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 42B8A5060123
     */
    protected void insertOrderCarryOver(WEB3FeqOrderUnit l_feqOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "insertOrderCarryOver(WEB3FeqOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
        
        //1.1 (*)�p�����[�^.�����P��.�����ID != null�̏ꍇ
        Trader l_trader = null;
        if (!l_feqOrderUnitParams.getTraderIdIsNull())
        {
            //1.1.1 ���҂��擾����B 
            //[����] 
            //arg0�F�@@�����P��.�����ID            
            FinObjectManager l_finObjectManager = GtlUtils.getFinObjectManager();
            try
            {
                l_trader = l_finObjectManager.getTrader(
                    l_feqOrderUnit.getTraderId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(" �e�[�u���ɊY������f�[�^������܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        
        //1.2 �⏕�������擾����B
        SubAccount l_subAccount = l_feqOrderUnit.getSubAccount();
        
        //1.3  is���t( )(�O�����������P��::is���t)
        //���t�������ǂ������ʂ���B
        boolean l_blnIsBuy = l_feqOrderUnit.isBuy();
        
        //1.4 �O���������擾����B
        FeqProduct l_feqProduct = (FeqProduct)l_feqOrderUnit.getProduct();
        if (l_feqProduct == null)
        {
            log.debug("Error in �O���������擾����");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.5 �s����擾����B
        Market l_market = l_feqOrderUnit.getMarket();
        
        //1.6 reset�s��R�[�h(String)(������ԊǗ�::reset�s��R�[�h)
        //����J�����_�R���e�L�X�g�̎s��R�[�h���ăZ�b�g����B 
        //[����] 
        //�s��R�[�h�F�@@get�s��()�̖߂�l.�s��R�[�h
        WEB3GentradeTradingTimeManagement.resetMarketCode(
            l_market.getMarketCode());
        
        //1.7 create�V�K�������e() �V�K���������e���쐬����B 
        //[����] 
        //�m�[�g�A���J�[�Q��                
        double l_dblOrderQuantity = 
            l_feqOrderUnitParams.getQuantity() - 
                l_feqOrderUnitParams.getExecutedQuantity();
        
        Long l_lngFirstOrderUnitId = null;
        if (l_feqOrderUnitParams.getFirstOrderUnitId() == 0)
        {
            l_lngFirstOrderUnitId = new Long(
                l_feqOrderUnitParams.getOrderUnitId());
        }
        else
        {
            l_lngFirstOrderUnitId = new Long(
                l_feqOrderUnitParams.getFirstOrderUnitId());
        }
        
        WEB3FeqNewOrderSpec l_feqNewOrderSpec = 
            WEB3FeqNewOrderSpec.createNewOrderSpec(
                l_feqOrderUnitParams.getInstitutionCode(),
                (WEB3GentradeTrader)l_trader,
                l_blnIsBuy, 
                l_feqProduct.getProductCode(), 
                l_market.getMarketCode(), 
                l_dblOrderQuantity, 
                l_feqOrderUnitParams.getLimitPrice(), 
                l_feqOrderUnitParams.getExecutionConditionType(), 
                l_feqOrderUnitParams.getExpirationDate(), 
                l_feqOrderUnitParams.getTaxType(), 
                l_feqOrderUnitParams.getCurrencyCode(), 
                l_feqOrderUnitParams.getOrderConditionType(), 
                l_feqOrderUnitParams.getWLimitPrice(), 
                l_feqOrderUnitParams.getSettleDiv(), 
                l_lngFirstOrderUnitId);
            
        //1.8 validate�V�K����(SubAccount, ProductTypeEnum, NewOrderSpec)
        //�V�K���������R�����s���B 
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //�����^�C�v�F�@@ProductTypeEnum.�O������ 
        //�������e�F�@@create�V�K�������e()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        NewOrderValidationResult l_newOrderValidationResult = 
            l_feqOrderManager.validateNewOrder(
                l_subAccount, 
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_feqNewOrderSpec);
        
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����R���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R���`�F�b�N�G���[");
        }
        
        //1.9 ����������擾����B
        WEB3FeqTradedProduct l_web3FeqTradedProduct = (WEB3FeqTradedProduct)
            l_feqOrderUnit.getTradedProduct();
        
        //1.10 �w�l�������ǂ������ʂ���B
        boolean l_blnIsLimitPrice = l_feqOrderUnit.isLimitOrder();
        
        //1.11 get�v�Z�p�����P��()
        //�v�Z�p�̒����P�����擾����B 
        //[����] 
        //��������F�@@getTradedProduct()�̖߂�l 
        //���X�F �⏕����.get����X()�̖߂�l 
        //�����P���敪�F 
        //�@@[is�w�l()�̖߂�l == true�̏ꍇ] "�w�l"���Z�b�g�B 
        //�@@[��L�ȊO�̏ꍇ] �@@               "���s"���Z�b�g�B 
        //�����P���F�@@�����P��.�w�l
        //�����P���F�@@�����P��.�i�v�w�l�j�����w�l 
        //is���t�F�@@is���t()�̖߂�l
        
        String l_strOrderPriceDiv = null;
        if (l_blnIsLimitPrice)
        {
            l_strOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        else
        {
            l_strOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        
        double l_dblUnitPrice = 
            l_feqOrderManager.getUnitPrice(
                l_web3FeqTradedProduct, 
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(), 
                l_strOrderPriceDiv, 
                l_feqOrderUnitParams.getLimitPrice(), 
                l_feqOrderUnitParams.getWLimitPrice(), 
                l_blnIsBuy);
        
        //1.12 calc�������(double, double)(�O�������v�Z�T�[�r�X::calc�������)
        //��������i�O�݁j���Z�o����B 
        //[����] 
        //�����F�@@�V�K�������e.getQuantity() 
        //�P���F�@@get�v�Z�p�����P��()�̖߂�l
        WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        
        double l_dblExecutionAmount = 
            l_feqBizLogicProvider.calcExecutionAmount(
                l_feqNewOrderSpec.getQuantity(), 
                l_dblUnitPrice);

        //1.13 get�ʉ�( )
        WEB3GentradeCurrency l_genCurrency = l_feqOrderUnit.getCurrency();
        
        //calc�������(double, double)�̌��ʂ��A���Y�ʉ݂̏����_�����Ŋۂ߂�
        BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblExecutionAmount);
        int l_intDecimalPlace = l_genCurrency.getScale();
        l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_dblExecutionAmount = l_bdTradePriceFc.doubleValue();
        log.debug("��������̊ۂ߂����� �� "+ l_dblExecutionAmount);
        
        double l_dblBaseFxRate = 0.0D;
        
        //1.14 (*)���t����(is���t()�̖߂�l == true)�̏ꍇ
        if (l_blnIsBuy)
        {
            //1.14.1  get���t��בփ��[�g( ) ���t��בփ��[�g���擾����B
            l_dblBaseFxRate = l_genCurrency.getBuyBaseRate();
        }
        //1.15 (*)���t����(is���t()�̖߂�l == false)�̏ꍇ
        else
        {
            //1.15.1 get���t��בփ��[�g( ) ���t��בփ��[�g���擾����B
            l_dblBaseFxRate = l_genCurrency.getSellBaseRate();
        }
        
        //1.6 calc�O���������z() �e����z�̌v�Z���s���B 
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //�O�����������F�@@getProduct()�̖߂�l 
        //�s��F�@@get�s��()�̖߂�l 
        //����F�@@�V�K�������e.get������() 
        //��������i�O�݁j�F�@@calc�������()�̖߂�l 
        //�בփ��[�g�F�@@�擾������בփ��[�g 
        //is���t�F�@@is���t()�̖߂�l 
        //is���v�Z�F�@@false 
        //is�w�l�F�@@is�w�l()�̖߂�l
        //�����`���l���F�@@�p�����[�^.�����P��.���񒍕��̒����`���l��
        WEB3FeqAmountCalcResult l_feqAmountCalcResult = 
            l_feqBizLogicProvider.calcFeqAmount(
                (WEB3GentradeSubAccount)l_subAccount, 
                (WEB3FeqProduct)l_feqProduct, 
                (WEB3GentradeMarket)l_market, 
                l_feqNewOrderSpec.getBizDate(), 
                l_feqNewOrderSpec.getBizDate(),
                l_dblExecutionAmount, 
                l_dblBaseFxRate,
                l_blnIsBuy, 
                false, 
                l_blnIsLimitPrice, 
                l_feqOrderUnitParams.getOrderChanel());
        
        //1.7 �O�����������J�z�X�V�C�x���g�C���^�Z�v�^()
        //�O�����������X�V�C�x���g�C���^�Z�v�^�𐶐�����B 
        //[����] 
        //�J�z�������P�ʍs�F�@@�����P�� 
        //���z�v�Z���ʁF�@@calc�O���������z()�̖߂�l 
        //�v�Z�P���F�@@get�v�Z�p�����P��()�̖߂�l
        WEB3FeqOrderCarryOverUpdateInterceptor l_orderUpdateInterceptor = 
            new WEB3FeqOrderCarryOverUpdateInterceptor(
                l_feqOrderUnitParams, 
                l_feqAmountCalcResult, 
                l_dblUnitPrice);
        
        //1.8 validate����]��() ����]�̓`�F�b�N���s���B 
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //�������e�C���^�Z�v�^�F�@@���������X�V�C�x���g�C���^�Z�v�^ 
        //�������e�F�@@�V�K�������e 
        //������ʁF�@@�����P��.������� 
        //�]�͍X�V�t���O�F�@@true(������)
        //�������e�C���^�Z�v�^�̔z��            
        if (l_blnIsBuy)
        {
            WEB3FeqOrderCarryOverUpdateInterceptor[] l_orderUpdateInterceptors = 
                {l_orderUpdateInterceptor};
                
            //�������e�̔z��
            WEB3FeqNewOrderSpec[] l_feqNewOrderSpecs = 
                {l_feqNewOrderSpec}; 
            
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            
            WEB3TPTradingPowerResult l_tPTradingPowerResult = 
                l_tpTradingPowerService.validateTradingPower(
                    (WEB3GentradeSubAccount)l_subAccount, 
                    l_orderUpdateInterceptors, 
                    l_feqNewOrderSpecs, 
                    l_feqOrderUnitParams.getOrderType(), 
                    true);
            
            if (l_tPTradingPowerResult == null)
            {
                log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[����B
            if (!l_tPTradingPowerResult.isResultFlg())
            {
                log.debug("����]�̓`�F�b�N�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����]�̓`�F�b�N�G���[�B");
            }
        }

        //1.19 ThreadLocal�ɍX�V�C�x���g�C���^�Z�v�^���Z�b�g����B 
        //[����] 
        //arg0�F�@@���������X�V�C�x���g�C���^�Z�v�^
        l_feqOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_orderUpdateInterceptor);
        
        //1.20 ����ID��V�K�̔Ԃ���B
        long l_lngNewOrderId = l_feqOrderManager.createNewOrderId();
        
        //1.21 submitNewOrder()
        //�V�K������DB�ɓo�^����B 
        //[����] 
        //arg0�F�@@get�⏕����()�̖߂�l 
        //arg1�F�@@ProductTypeEnum.�O������ 
        //arg2�F�@@�V�K�������e 
        //arg3�F�@@createNewOrderId()�̖߂�l 
        //arg4�F�@@get�⏕����()�̖߂�l.getMainAccount().getTradingPassword()�̒l
        //       ��decript�����l 
        //arg5�F�@@true(�X�L�b�v)
        WEB3Crypt l_web3Crypt = new WEB3Crypt();
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        String l_strPassword = l_web3Crypt.decrypt(
            l_mainAccount.getTradingPassword());
        
        OrderSubmissionResult l_submitNewOrderResult =
            l_feqOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_feqNewOrderSpec,
                l_lngNewOrderId, 
                l_strPassword,
                true);

        if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitNewOrder" +
                l_submitNewOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (update�J�z������)<BR>
     * �J�z�������P�ʂ̒����G���[���R�R�[�h�Ȃǂ�update����B<BR>
     * <BR>
     * �P�j�@@�J�z�������̒����G���[���R�R�[�h��update����B<BR>
     * <BR>
     * �@@�P�|�P�j�@@�ȉ��̏����ɊY������J�z�������̒����P�ʃ��R�[�h��update����B<BR>
     * �@@�@@<����><BR>
     * �@@�@@�@@�����P�ʃe�[�u��.�����P��ID = �p�����[�^.�����P��.�����P��ID<BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�@@�����P�ʃ��R�[�h.�����G���[���R�R�[�h = �p�����[�^.�����G���[���R�R�[�h<BR>
     * �@@�@@�@@�����P�ʃ��R�[�h.�X�V���t = ���ݓ���<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�ȉ��̏����ɊY������J�z�������̒��������́A<BR>
     * �@@�@@�@@�@@�@@�ŏI�������R�[�h�̒����G���[���R�R�[�h ��update����B<BR>
     * <BR>
     * �@@�@@<����><BR>
     * �@@�@@�����e�[�u��.�����P��ID = �p�����[�^.�����P��.�����P��ID�@@����<BR>
     * �@@�@@�����e�[�u��.��������ԍ� = �p�����[�^.�����P��.���������ŏI�ʔ�<BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�������R�[�h.�����G���[���R�R�[�h = �p�����[�^.�����G���[���R�R�[�h<BR>
     * �@@�@@�������R�[�h.�X�V���t = ���ݓ���<BR>
     * <BR>
     * �@@�P�|�R�j�@@�ȉ��̏����ɊY������A<BR>
     * �J�z�������̒����i�w�b�_�j�̍X�V������update����B<BR>
     * <BR>
     * �@@�@@<����><BR>
     * �@@�@@�����i�w�b�_�j�e�[�u��.����ID = �p�����[�^.�����P��.����ID<BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�����i�w�b�_�j���R�[�h.�X�V���t = ���ݓ���<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �O�����������P�ʃI�u�W�F�N�gBR>
     * @@param l_strOrderErrorCode - (�����G���[���R�R�[�h)<BR>
     * �����G���[���R�R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B938910311
     */
    protected void updateOrderCarryOverBase(
        WEB3FeqOrderUnit l_feqOrderUnit, String l_strOrderErrorCode) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderCarryOverBase(" +
            "WEB3FeqOrderUnit, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //�P�j�@@�J�z�������̒����G���[���R�R�[�h��update����B
            //�P�|�P�j�@@�ȉ��̏����ɊY������J�z�������̒����P�ʃ��R�[�h��update����B 
            //<����> 
            //�@@�����P�ʃe�[�u��.�����P��ID = �p�����[�^.�����P��.�����P��ID 
            FeqOrderUnitParams l_feqOrderUnitParams = 
                new FeqOrderUnitParams(
                    (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
            
            //<�X�V���e> 
            //�@@�����P�ʃ��R�[�h.�����G���[���R�R�[�h = �p�����[�^.�����G���[���R�R�[�h 
            l_feqOrderUnitParams.setErrorReasonCode(l_strOrderErrorCode);
            
            //�@@�����P�ʃ��R�[�h.�X�V���t = ���ݓ��� 
            l_feqOrderUnitParams.setLastUpdatedTimestamp(
                GtlUtils.getSystemTimestamp());
           
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_feqOrderUnitParams);
            
            //�P�|�Q�j�@@�ȉ��̏����ɊY������J�z�������̒��������́A 
            //�ŏI�������R�[�h�̒����G���[���R�R�[�h ��update����B 
            //<����> 
            //�����e�[�u��.�����P��ID = �p�����[�^.�����P��.�����P��ID�@@���� 
            //�����e�[�u��.��������ԍ� = �p�����[�^.�����P��.���������ŏI�ʔ� 
            String l_strWhereClause = 
                "order_unit_id = ? and order_action_serial_no = ?";
            
            Object l_bindVars[] = {
                new Long(l_feqOrderUnitParams.getOrderUnitId()), 
                new Integer(l_feqOrderUnitParams.getLastOrderActionSerialNo())};
        
            List l_lisOrderActionRows = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    FeqOrderActionRow.TYPE,
                    l_strWhereClause,                    
                    null,
                    l_bindVars);    
           
            if (!l_lisOrderActionRows.isEmpty() && l_lisOrderActionRows.size() == 1)
            {
                FeqOrderActionRow l_feqOrderActionRow = 
                    (FeqOrderActionRow)l_lisOrderActionRows.get(0);
                
                FeqOrderActionParams l_feqOrderActionParams = 
                    new FeqOrderActionParams(l_feqOrderActionRow); 
                
                //<�X�V���e> 
                //�������R�[�h.�����G���[���R�R�[�h = �p�����[�^.�����G���[���R�R�[�h 
                l_feqOrderActionParams.setErrorReasonCode(l_strOrderErrorCode);
                
                //�������R�[�h.�X�V���t = ���ݓ��� 
                l_feqOrderActionParams.setLastUpdatedTimestamp(
                    GtlUtils.getSystemTimestamp());        
               
                l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_feqOrderActionParams); 
            }
            
            //�P�|�R�j�@@�ȉ��̏����ɊY������A�J�z�������̒����i�w�b�_�j�̍X�V������update����B 
            //<����> 
            //�����i�w�b�_�j�e�[�u��.����ID = �p�����[�^.�����P��.����ID
            FeqOrderRow l_feqOrderRow = 
                FeqOrderDao.findRowByOrderId(l_feqOrderUnitParams.getOrderId());
           
            FeqOrderParams l_feqOrderParams = new FeqOrderParams(l_feqOrderRow);
            
            //<�X�V���e> 
            //�����i�w�b�_�j���R�[�h.�X�V���t = ���ݓ��� 
            l_feqOrderParams.setLastUpdatedTimestamp(
                GtlUtils.getSystemTimestamp());
        
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_feqOrderParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�����G���[���R�R�[�h)<BR>
     * �����̃G���[���ɑΉ����钍���G���[���R�R�[�h��<BR>
     * �ԋp����B<BR>
     * <BR>
     * ���ԋp����钍���G���[���R�R�[�h�ɂ��ẮA<BR>
     * �@@DB���C�A�E�g�u�O�������P�ʃe�[�u���d�l.xls#�i�����P�ʃe�[�u���⑫�j<BR>
     * �����G���[���R�R�[�h�v�Q�ƁB<BR>
     * <BR>
     * �P�j�p�����[�^.�G���[���ɂ��A�����G���[���R�R�[�h�����肷��B <BR>
     * <BR>
     * �@@[�p�����[�^.�G���[��� == "�l���G���["�̏ꍇ]<BR>
     * �@@(�O�����������R���ʃ`�F�b�N.validate�l��()����X���[���ꂽ��O�̏ꍇ)<BR>
     * �@@�@@�����G���[���R�R�[�h = "0001�F�l���G���["<BR>
     * <BR>
     * �@@[�p�����[�^.�G���[��� == "�a����s��"�̏ꍇ]<BR>
     * �@@(validate����]��()�̌��ʁA�X���[���ꂽ��O�̏ꍇ)<BR>
     * �@@�@@�����G���[���R�R�[�h = "0002�F�a����s���G���["<BR>
     * <BR>
     * �@@[�p�����[�^.�G���[��� == "�����c���s���G���["�̏ꍇ]<BR>
     * �@@(�O�����������R���ʃ`�F�b�N.validate���t�\����()����X���[���ꂽ<BR>
     * ��O�̏ꍇ)<BR>
     * �@@�@@�����G���[���R�R�[�h = "0003�F�����c���s���G���["<BR>
     * <BR>
     * �@@[�p�����[�^.�G���[��� == "������~�����G���["�̏ꍇ]<BR>
     * �@@(�O�����������R���ʃ`�F�b�N.validate�����������X���[���ꂽ<BR>
     *  �@@����K���̗�O�̏ꍇ)<BR>
     * �@@�@@�����G���[���R�R�[�h = "0006�F������~�����G���["<BR>
     * <BR>
     * �@@[�p�����[�^.�G���[��� == "�s��ύX�����G���["�̏ꍇ]<BR>
     * �@@(�O�����������R���ʃ`�F�b�N.validate�������()����<BR>
     * �@@�@@super.validateTradedProduct()�ɂăX���[���ꂽ��O�̏ꍇ)<BR>
     * �@@�@@������������擾�ł��Ȃ������ꍇ�܂��͊Y���������������̏ꍇ<BR>
     * �@@�@@�����G���[���R�R�[�h = "0007�F�s��ύX�����G���["<BR>
     * <BR>
     * �@@[�p�����[�^.�G���[��� == "��������J�݃G���["�̏ꍇ]<BR>
     * �@@(�O�����������R���ʃ`�F�b�N.validate��������J��()����X���[���ꂽ��O�̏ꍇ)<BR>
     * �@@�@@�����G���[���R�R�[�h = "0010�F��������G���["<BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�����G���[���R�R�[�h = "9001�F���̑��G���["<BR>
     * <BR>
     * �Q�j�@@���肵�������G���[���R�R�[�h��ԋp����B<BR>
     * @@param l_errorInfo - (�G���[���)<BR>
     * �G���[���I�u�W�F�N�g
     * @@return String
     * @@roseuid 42BA5BE00354
     */
    protected String getErrorCode(ErrorInfo l_errorInfo) 
    {
        final String STR_METHOD_NAME = 
            "getErrorCode(ErrorInfo l_errorInfo)";
        log.entering(STR_METHOD_NAME);
        
        String l_strErrorCode = null;
        //�P�j�p�����[�^.�G���[���ɂ��A�����G���[���R�R�[�h�����肷��B 
       
        //[�p�����[�^.�G���[��� == "�l���G���["�̏ꍇ] 
        //(�O�����������R���ʃ`�F�b�N.validate�l��()����X���[���ꂽ��O�̏ꍇ) 
        //�����G���[���R�R�[�h = "0001�F�l���G���[" 
        if (WEB3ErrorCatalog.BUSINESS_ERROR_00031.equals(l_errorInfo))
        {
            l_strErrorCode = WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR;
        }

        //[�p�����[�^.�G���[��� == "�a����s��"�̏ꍇ] 
        //(validate����]��()�̌��ʁA�X���[���ꂽ��O�̏ꍇ) 
        //�����G���[���R�R�[�h = "0002�F�a����s���G���[" 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_01306.equals(l_errorInfo))
        {
            l_strErrorCode = 
                WEB3ErrorReasonCodeDef.DEPOSIT_MONEY_SHORT_ERROR;
        }
        
        //[�p�����[�^.�G���[��� == "�����c���s���G���["�̏ꍇ] 
        //(�O�����������R���ʃ`�F�b�N.validate���t�\����()����X���[���ꂽ��O�̏ꍇ) 
        //�����G���[���R�R�[�h = "0003�F�����c���s���G���[" 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_02109.equals(l_errorInfo))
        {
            l_strErrorCode = WEB3ErrorReasonCodeDef.POSITION_SHORT_ERROR;
        }
        
        //[�p�����[�^.�G���[��� == "������~�����G���["�̏ꍇ] 
        //(�O�����������R���ʃ`�F�b�N.validate�����������X���[���ꂽ 
        //����K���̗�O�̏ꍇ) 
        //�����G���[���R�R�[�h = "0006�F������~�����G���[" 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_02087.equals(l_errorInfo))
        {
            l_strErrorCode = WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR;
        }

        //[�p�����[�^.�G���[��� == "�s��ύX�����G���["�̏ꍇ] 
        //(�O�����������R���ʃ`�F�b�N.validate�������()���� 
        //super.validateTradedProduct()�ɂăX���[���ꂽ��O�̏ꍇ) 
        //������������擾�ł��Ȃ������ꍇ�܂��͊Y���������������̏ꍇ
        //�����G���[���R�R�[�h = "0007�F�s��ύX�����G���[" 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_02088.equals(l_errorInfo) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_02089.equals(l_errorInfo) )
        {
            l_strErrorCode = 
                WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR;
        }
        
        //[�p�����[�^.�G���[��� == "��������J�݃G���["�̏ꍇ] 
        //(�O�����������R���ʃ`�F�b�N.validate��������J��()����X���[���ꂽ��O�̏ꍇ) 
        //�����G���[���R�R�[�h = "0010�F��������G���[" 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_02096.equals(l_errorInfo))
        {
            l_strErrorCode = WEB3ErrorReasonCodeDef.SPEC_ACCOUNT_ERROR;
        }
        
        //[��L�ȊO�̏ꍇ] 
        //�����G���[���R�R�[�h = "9001�F���̑��G���[" 
        else
        {
            l_strErrorCode = WEB3ErrorReasonCodeDef.OTHRE_ERROR;
        }       
        
        //�Q�j�@@���肵�������G���[���R�R�[�h��ԋp����B        
        log.exiting(STR_METHOD_NAME);
        return l_strErrorCode;
    }
}
@
