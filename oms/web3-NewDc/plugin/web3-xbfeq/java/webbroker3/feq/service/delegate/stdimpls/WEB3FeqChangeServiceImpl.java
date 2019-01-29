head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������T�[�r�XImpl(WEB3FeqChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬       
                 : 2005/08/03 �A�C��(���u) ���r���[  
                 : 2006/09/20 ꎉ�(���u) ���r���[   
                 : 2006/10/12 ���G��(���u) �o�O3072�̑Ή� 
Revesion History : 2008/01/21 �đo�g(���u) ���f��No.381�A���f��No.372
Revesion History : 2010/01/12 �����F(���u) ���f��No.532
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqAmountCalcResult;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqChangeOrderSpec;
import webbroker3.feq.WEB3FeqChangeUpdateInterceptor;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqProductQuote;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.message.WEB3FeqChangeCompleteRequest;
import webbroker3.feq.message.WEB3FeqChangeCompleteResponse;
import webbroker3.feq.message.WEB3FeqChangeConfirmRequest;
import webbroker3.feq.message.WEB3FeqChangeConfirmResponse;
import webbroker3.feq.message.WEB3FeqChangeInputRequest;
import webbroker3.feq.message.WEB3FeqChangeInputResponse;
import webbroker3.feq.service.delegate.WEB3FeqChangeService;
import webbroker3.feq.util.WEB3FeqOrderUtility;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�����������T�[�r�XImpl)<BR>
 * �O�����������T�[�r�X�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqChangeServiceImpl extends WEB3FeqClientRequestService 
    implements WEB3FeqChangeService 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqChangeServiceImpl.class);  
    
    /**
     * @@roseuid 42CE39F70280
     */
    public WEB3FeqChangeServiceImpl() 
    {
     
    }
    
    /**
     * �O�����������T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *    get���͉��()<BR>
     *    validate����()<BR>
     *    submit����()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4295F0E703E7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response;
        
        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B 
        // get���͉��()
        // validate����() 
        // submit����() 
        if (l_request instanceof WEB3FeqChangeInputRequest)
        {
            l_response = 
                getInputScreen((WEB3FeqChangeInputRequest)l_request);   
        }        
        else if (l_request instanceof WEB3FeqChangeConfirmRequest)
        {
            l_response = 
                validateOrder((WEB3FeqChangeConfirmRequest)l_request);   
        }        
        else if (l_request instanceof WEB3FeqChangeCompleteRequest)
        {
            l_response =
                submitOrder((WEB3FeqChangeCompleteRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̕\���ɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������jget���͉�ʁv �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3FeqChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4295F1E102BE
     */
    protected WEB3FeqChangeInputResponse getInputScreen(
        WEB3FeqChangeInputRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 �⏕�����I�u�W�F�N�g���擾����B
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)
            this.getSubAccount();
        
        //1.3 validate����(�⏕����)(�O�����������}�l�[�W��::validate����)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        l_feqOrderManager.validateOrder(l_subAccount);
        
        //1.4 validate�����\�s��(long)(�O�����������}�l�[�W��::validate�����\�s��)
        //�����\�Ȏs�ꂩ�ǂ������`�F�b�N����B 
        //[����] 
        //����ID�F ���N�G�X�g.����ID 
        l_feqOrderManager.validateChangePossMarket(Long.parseLong(l_request.orderId));
        
        //1.5 validate���������\���(long)
        //�����Ώۂ̒����������\���ǂ������`�F�b�N����B 
        //[����] 
        //����ID�F ���N�G�X�g.����ID 
        l_feqOrderManager.validateOrderChangePossibleStatus(
            Long.parseLong(l_request.orderId));
        
        //1.6 get�����P��ByOrderId(long)(�O�����������}�l�[�W��::get�����P��ByOrderId)
        //�����P�ʃI�u�W�F�N�g���擾����B 
        //[����] 
        //����ID�F ���N�G�X�g.����ID 
        FeqOrderUnit l_feqOrderUnit = l_feqOrderManager.getOrderUnitByOrderId(
                Long.parseLong(l_request.orderId));        
        
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
        
        WEB3FeqProductManager l_feqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();        

        WEB3FeqProduct l_feqProduct = null;
        try
        {
            //1.7 get�O����������(long)(�O�������v���_�N�g�}�l�[�W��::get�O����������)
            //�O�����������I�u�W�F�N�g���擾����B 
            //[����] 
            //����ID�F �����P��.����ID 
            l_feqProduct =
                (WEB3FeqProduct) l_feqProductManager.getProduct(
                        l_feqOrderUnitParams.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when "
                + "l_feqProductManager.getProduct(ProductId) with "
                + "ProductId = "
                + l_feqOrderUnitParams.getProductId(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_feqProduct == null)
        {
            log.debug("Error in �O�����������I�u�W�F�N�g���擾����");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.8 validate�O������(�،����, String)
        //�����̃`�F�b�N���s���B 
        //[����] 
        //�،���ЁF �⏕����.getInstitutuin()�̖߂�l 
        //�����R�[�h�F �O����������.getProductCode()�̖߂�l 
        WEB3GentradeInstitution l_institution = 
            (WEB3GentradeInstitution)l_subAccount.getInstitution();
        
        l_feqOrderManager.validateFeqProduct(
            l_institution,
            l_feqProduct.getProductCode());
        
        //1.9 get�s��( )(�O����������::get�s��)
        //�s��I�u�W�F�N�g���擾����B
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.10 validate�s��(�s��)(�O�����������}�l�[�W��::validate�s��)
        //�s��̃`�F�b�N���s���B 
        l_feqOrderManager.validateMarket(l_market);
        
        //1.11 validate�������(�O����������, �s��, boolean)
        //��������̃`�F�b�N���s���B 
        //[����] 
        //�O�����������F �O�����������I�u�W�F�N�g 
        //�s��F get�s��()�̖߂�l 
        //is�������F �����P��.is���t()�̖߂�l
        
        WEB3FeqOrderUnit l_web3FeqOrderUnit = (WEB3FeqOrderUnit) l_feqOrderUnit;
        boolean l_blnIsBuy = l_web3FeqOrderUnit.isBuy();
        
        l_feqOrderManager.validateTradedProduct(
            l_feqProduct, 
            l_market, 
            l_blnIsBuy);
        
        //1.12 validate�ڋq�����ʎ����~(SubAccount, long, OrderTypeEnum)
        //�ڋq�����ʎ�������`�F�b�N���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //����ID�F �O����������.����ID 
        //������ʁF �����P��.�������
        l_feqOrderManager.validateAccountProductTradedStop(
            l_subAccount, 
            l_feqProduct.getProductId(), 
            l_feqOrderUnitParams.getOrderType());
        
        //1.13 �戵�\��������()
        //�戵�\���������C���X�^���X���擾����B 
        //[����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //�����^�C�v�F �h�O�������h 
        //�敨�^�I�v�V�����敪�F �hDEFAULT�h(�Œ�) 
        //�M�p����敪�F �hDEFAULT�h(�Œ�) 
        //�s��R�[�h�F �s��.�s��R�[�h 
        String l_strMarketCode = null;
        if (l_market != null)
        {
            l_strMarketCode = l_market.getMarketCode();
        }
        
        WEB3GentradeHandlingOrderCond l_handingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(), 
                ProductTypeEnum.FOREIGN_EQUITY, 
                WEB3FuturesOptionDivDef.DEFAULT, 
                WEB3MarginTradingDivDef.DEFAULT, 
                l_strMarketCode);
        
        //1.14 �戵�\���s�����擾() �戵�\�Ȏ��s�������擾����B 
        String[] l_strHandingOrderCond = 
            l_handingOrderCond.getHandlingPossibleExecCond();
        
        //1.15 is�o����܂Œ����戵�\( )
        //�o����܂Œ������戵�\���𔻒肷��B 
        boolean l_blnPossHanding = 
            l_handingOrderCond.isOrderUntilDeadLinePossibleHandling();
        
        //1.16 is�o����܂Œ����P��(FeqOrderUnit)
        //�����Ώۂ̒������o����܂Œ������ǂ����𔻒肷��B 
        //[����] 
        //�����P�ʁF �����P�ʃI�u�W�F�N�g 
        boolean l_blnCarriedOrderUnit = 
            l_feqOrderManager.isCarriedOrderUnit(l_feqOrderUnit);
        
        Date l_datOrderUntilDeadLineStartDay = null;
        Date l_datOrderUntilDeadLineEndDay = null;
        Date[] l_datExpirationDateHolidays = null; 
            
        log.debug("is�o����܂Œ����戵�\()�̖߂�l == " + l_blnPossHanding);
        log.debug("is�o����܂Œ����P��()�̖߂�l == " + l_blnCarriedOrderUnit);
        
        //1.17 is�o����܂Œ����戵�\()�̖߂�l == true and
        //     is�o����܂Œ����P��()�̖߂�l == true �̏ꍇ�A���{
        if (l_blnPossHanding && l_blnCarriedOrderUnit)
        {
            //1.17.1 get���񒍕��̒����P��(FeqOrderUnit)(�O�����������}�l�[�W��)
            //���񒍕��̒����P�ʂ��擾����B 
            //[����] 
            //�����P�ʁF �����P�ʃI�u�W�F�N�g 
            FeqOrderUnit l_firstOrderUnit = 
                l_feqOrderManager.getFirstOrderUnit(l_feqOrderUnit);
            
            //1.17.2 get�o����܂Œ����J�n��(�o����܂Œ���from���t : Date)
            //�o����܂Œ������\�ȊJ�n�����擾����B 
            //[����] 
            //�o����܂Œ���from���t�F get���񒍕��̒����P��()�Ŏ擾���������P��.������ 
            FeqOrderUnitParams l_firstOrderUnitParams = 
                new FeqOrderUnitParams(
                    (FeqOrderUnitRow) l_firstOrderUnit.getDataSourceObject());
            
            log.debug("get���񒍕��̒����P��() = " + l_firstOrderUnitParams);
            
            l_datOrderUntilDeadLineStartDay = 
                l_handingOrderCond.getOrderUntilDeadLineStartDay(
                    WEB3DateUtility.getDate(
                        l_firstOrderUnitParams.getBizDate(), "yyyyMMdd"));
                
            log.debug("get�o����܂Œ����J�n�� = " + l_datOrderUntilDeadLineStartDay);
            
            //1.17.3 get�o����܂Œ����ŏI��(�o����܂Œ���from���t : Date)
            //�o����܂Œ������\�ȍŏI�����擾����B 
            //[����] 
            //�o����܂Œ���from���t�F get���񒍕��̒����P��()�Ŏ擾���������P��.������ 
            l_datOrderUntilDeadLineEndDay = 
                l_handingOrderCond.getOrderUntilDeadLineEndDay(
                    WEB3DateUtility.getDate(
                        l_firstOrderUnitParams.getBizDate(), "yyyyMMdd"));
            
            log.debug("get�o����܂Œ����ŏI�� = " + l_datOrderUntilDeadLineEndDay);
            
            //1.17.4 get�����������j���ꗗ(�o����܂Œ���from���t : Date)
            //�o����܂Œ����̗L���������̏j���̈ꗗ���擾����B 
            //[����] 
            //�o����܂Œ���from���t�F get���񒍕��̒����P��()�Ŏ擾���������P��.������
            l_datExpirationDateHolidays = 
                l_handingOrderCond.getExpirationDateHoliday(
                    WEB3DateUtility.getDate(
                        l_firstOrderUnitParams.getBizDate(), "yyyyMMdd"));
            
            if (l_datExpirationDateHolidays != null)
            {
                for (int i = 0; i < l_datExpirationDateHolidays.length; i++)
                {
                    log.debug("get�����������j���ꗗ = " + l_datExpirationDateHolidays[i]);
                }                
            }
        }
        //1.18  is�s��J�ǎ��ԑ�( )
        boolean l_blnIsTradeOpenTimeZone = 
            WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        
        //1.19 is�s��J�ǎ��ԑ�()�̖߂�l == false and
        //�����P��.�s�ꂩ��m�F�ς݂̐��� != NaN �̏ꍇ�A���{
        
        if (!l_blnIsTradeOpenTimeZone && !Double.isNaN(
            l_feqOrderUnit.getConfirmedQuantity()))
        {
            log.debug("is�s��J�ǎ��ԑ�()�̖߂�l == false and " + 
                    "�����P��.�s�ꂩ��m�F�ς݂̐��� != NaN �̏ꍇ");
            //1.19.1 validate�ǌ���������t�\(�����^�C�v : ProductTypeEnum)
            //�ǌ�ɒ����̒����^����̎�t���\���ǂ������肷��B  
            //[����] 
            //�����^�C�v�F �h�O�������h 
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                ProductTypeEnum.FOREIGN_EQUITY);
        }
        
        //1.20 get�\���p�������(�O�������������, �⏕����)
        //��ʕ\���p�̎��������擾����B 
        //[����] 
        //��������F �O��������������I�u�W�F�N�g 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        WEB3FeqTradedProduct l_feqTradedProduct = 
            l_feqProduct.getFeqTradedProduct();
        
        WEB3FeqProductQuote l_feqProductQuote =
            l_feqProductManager.getIndicationCurrentPriceUnit(
                l_feqTradedProduct, 
                l_subAccount);
        
        //1.29 ���X�|���X�f�[�^�𐶐�����B
        WEB3FeqChangeInputResponse l_response = 
            (WEB3FeqChangeInputResponse) l_request.createResponse();
        
        if (l_feqProductQuote != null)
        {        
            //1.21 get�����擾�敪( )(�O�������������::get�����擾�敪)
            //�����擾�敪���擾����B 
            String l_strCurrentPriceGetDiv = 
                l_feqProductQuote.getCurrentPriceGetDiv();
            
            //1.22 �������擾����B 
            double l_dblCurrentPrice = l_feqProductQuote.getCurrentPrice();
            
            //1.23 �O������擾����B
            double l_dblComparedPreviousDay = 
                l_feqProductQuote.getComparedPreviousDay();
            
            //1.24 �������\���Ԃ��擾����B 
            Date l_datCurrentPricePublicTime = 
                l_feqProductQuote.getCurrentPricePublicTime();
            
            //�����擾�敪�F get�����擾�敪()�̖߂�l
            l_response.currentPriceGetDiv = l_strCurrentPriceGetDiv;
            
            //�����F get����()�̖߂�l
            l_response.currentPrice = 
                WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
            
            //�O����F get�O����()�̖߂�l
            l_response.comparedPreviousDay = 
                WEB3StringTypeUtility.formatNumber(l_dblComparedPreviousDay);
            
            //������ԁF get�������\����()�̖߂�l
            l_response.currentPriceTime = l_datCurrentPricePublicTime;
        }
        //1.25 get���s����() SONAR�̎��s�������擾����B 
        //���s�����F �����P��.���s���� 
        String l_strExecutionConditionSonar =    
            WEB3FeqOrderUtility.getExecutionConditionTypeSonar(                          
                l_feqOrderUnitParams.getExecutionConditionType().intValue() + "");        
            
        double l_dblOtherTradingPower = 0.0D;
        BigDecimal l_bdCalcForeignCCYAmount = new BigDecimal("0");
        
        //1.26 (*1)�����P��.is���t()�̖߂�l == true �̏ꍇ
        if (l_blnIsBuy)
        {
            log.debug("�����P��.is���t()�̖߂�l == true �̏ꍇ");
            
            //1.26.1 get���̑����i���t�\�z(�⏕���� : �⏕����, ��n�� : Date)
            //���t�\�z���擾����B 
            //[����] 
            //�⏕�����F �⏕�����I�u�W�F�N�g 
            //��n���F �������.get��n��()�̖߂�l
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            
            l_dblOtherTradingPower =
                l_tpTradingPowerService.getOtherTradingPower(
                    l_subAccount, 
                    l_feqTradedProduct.getDailyDeliveryDate());
        }
        //1.27 (*2)�����P��.is���t()�̖߂�l == false �̏ꍇ
        else
        {
            log.debug("�����P��.is���t()�̖߂�l == false �̏ꍇ");
            
            //1.27.1 calc�T�Z�뉿�P��(�⏕����, long, TaxTypeEnum)
            //�T�Z�뉿�P�����v�Z����B 
            //[����] 
            //�⏕�����F �⏕�����I�u�W�F�N�g 
            //����ID�F �����P��.����ID 
            //�ŋ敪�F �����P��.�ŋ敪 
            WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
                (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
            BigDecimal l_bdEstimatedBookValuePrice = 
                l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                    l_subAccount, 
                    l_feqOrderUnitParams.getProductId(), 
                    l_feqOrderUnitParams.getTaxType());
            
            //1.27.2 �ʉ݃I�u�W�F�N�g���擾����B
            WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
                        
            //1.27.3 calc�O�݊��Z(BigDecimal, double, int, String)
            //�T�Z�뉿�P�����O�݊��Z����B 
            //[����] 
            //���z�i�~�݁j�F calc�T�Z�뉿�P��()�̖߂�l 
            //���[�g�F �ʉ�.get���t��בփ��[�g()�̖߂�l 
            //�����������F �ʉ�.get����������()�̖߂�l 
            //�O�݊��Z�ۂߕ����F �ʉ�.get�O�݊��Z�ۂߕ���()�̖߂�l
            l_bdCalcForeignCCYAmount = 
                l_feqBizLogicProvider.calcForeignCCYAmount(
                    l_bdEstimatedBookValuePrice,
                    l_currency.getSellBaseRate(), 
                    l_currency.getScale(), 
                    l_currency.getChangeFCcyRoundDiv());
            log.debug("calc�O�݊��Z�̖߂�l = " + l_bdCalcForeignCCYAmount);
        }
        
        //1.28 get�s��ǌx���O���s��(���X : ���X)
        //�Ǌԋ߂̎s��R�[�h�̈ꗗ���擾����B 
        //[����] 
        //���X�F�@@�⏕����.get����X()
        String[] l_strTradeCloseFeqMarkets = 
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch());
                      
        //1.30  (*)�v���p�e�B�Z�b�g       
        //(*3) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B

        //�����P���敪�ꗗ�F �h���s�h�Ɓh�w�l�h�̋敪�̔z��
        String[] l_strPriceDivs = {
            WEB3OrderPriceDivDef.MARKET_PRICE, 
            WEB3OrderPriceDivDef.LIMIT_PRICE}; 
        
        l_response.orderPriceDivList = l_strPriceDivs;
        
        //���s�����ꗗ�F �戵�\���s�����擾()�̖߂�l
        l_response.execCondList = l_strHandingOrderCond;
        
        //���������敪�ꗗ�F null
        l_response.expirationDateTypeList = null;
            
        //�L�������J�n���F get�o����܂Œ����J�n��()�̖߂�l
        l_response.expirationStartDate = l_datOrderUntilDeadLineStartDay;
        
        //�L�������I�����F get�o����܂Œ����ŏI��()�̖߂�l
        l_response.expirationEndDate = l_datOrderUntilDeadLineEndDay;
        
        //�L���������j���ꗗ�F get�L���������j���ꗗ()�̖߂�l
        l_response.holidayList = l_datExpirationDateHolidays;
        
        //���������ꗗ�F null
        l_response.orderCondTypeList = null;        
        
        //����I���x���s��R�[�h�ꗗ�F get�s��ǌx���O���s��()�̖߂�l
        l_response.messageSuspension = l_strTradeCloseFeqMarkets;
            
        //�s��R�[�h�F �O����������.get�s��R�[�h()�̖߂�l
        l_response.marketCode = l_feqProduct.getMarketCode();
            
        //�����R�[�h�F �O����������.getProductCode()�̖߂�l
        l_response.productCode = l_feqProduct.getProductCode();
        
        //���n�����R�[�h�F �O����������.get���n�����R�[�h()�̖߂�l
        l_response.localProductCode = l_feqProduct.getOffshoreProductCode();
        
        //�������F �O����������.get�\��������()�̖߂�l
        l_response.productName = l_feqProduct.getDisplayProductName();
        
        //��������敪�F�i�ȉ��̂Ƃ���j 
        //      �����P��.�ŋ敪 == "���"�̏ꍇ�A"���"���Z�b�g�B
        //      �����P��.�ŋ敪 == "����"�̏ꍇ�A"����"���Z�b�g�B
        
        if (TaxTypeEnum.NORMAL.equals(l_feqOrderUnitParams.getTaxType()))
        {
            l_response.taxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_feqOrderUnitParams.getTaxType()))
        {
            l_response.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }        
        
        //�����敪�F �i�ȉ��̂Ƃ���j
        //   �����P��.������� == �h�O�������h �̏ꍇ�A�h���t�h
        //   �����P��.������� == �h�O������h �̏ꍇ�A�h���t�h
        String l_strDealingType = null;
        OrderTypeEnum l_orderType = l_feqOrderUnitParams.getOrderType();
        
        if (OrderTypeEnum.FEQ_BUY.equals(l_orderType))
        {
            log.debug("�����P��.������� == �h�O�������h �̏ꍇ");
            l_strDealingType = WEB3BuySellTypeDef.BUY;
        }
        else if (OrderTypeEnum.FEQ_SELL.equals(l_orderType))
        {
            log.debug("�����P��.������� == �h�O������h �̏ꍇ");
            l_strDealingType = WEB3BuySellTypeDef.SELL;
        }
        l_response.dealingType = l_strDealingType;        
        
        //���ϋ敪�F �����P��.���ϋ敪
        l_response.settleDiv = l_feqOrderUnitParams.getSettleDiv();
        
        //�������ʁF �����P��.��������
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(
            l_feqOrderUnitParams.getQuantity());
        
        //���o�����ʁF �����P��.��萔��
        l_response.partContQuantity = WEB3StringTypeUtility.formatNumber(
            l_feqOrderUnitParams.getExecutedQuantity());
        
        //�����P���敪�F �i�ȉ��̂Ƃ���j
        //   �����P��.isMarketOrder()�̖߂�l == true �̏ꍇ�A�h���s�h
        //   �����P��.isMarketOrder()�̖߂�l == false �̏ꍇ�A�h�w�l�h
        String l_strOrderPriceDiv = null;
        if (l_feqOrderUnit.isMarketOrder())
        {
            log.debug("�����P��.isMarketOrder()�̖߂�l == true �̏ꍇ");
            l_strOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            log.debug("�����P��.isMarketOrder()�̖߂�l == false �̏ꍇ");
            l_strOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        l_response.orderPriceDiv = l_strOrderPriceDiv;
        
        //�����P���F �����P��.�w�l
        l_response.limitPrice = WEB3StringTypeUtility.formatNumber(
            l_feqOrderUnitParams.getLimitPrice());
        
        //�ʉ݃R�[�h: �O����������.get�ʉ݃R�[�h()�̖߂�l
        l_response.currencyCode = l_feqProduct.getCurrencyCode();
        
        //���s�����F get���s�����iSONAR�j()�̖߂�l
        l_response.execCondType = l_strExecutionConditionSonar;
        
        //���������敪�F �i�ȉ��̂Ƃ���j
        //   is�o����܂Œ����P��()�̖߂�l == true �̏ꍇ�A�h�o����܂Œ����h
        //   is�o����܂Œ����P��()�̖߂�l == false �̏ꍇ�A�h��������h
        String l_strCarriedOrderUnit = null;
        if (l_blnCarriedOrderUnit)
        {
            log.debug("is�o����܂Œ����P��()�̖߂�l == true �̏ꍇ");
            l_strCarriedOrderUnit = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        }
        else
        {
            log.debug("is�o����܂Œ����P��()�̖߂�l == false �̏ꍇ");
            l_strCarriedOrderUnit = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        }
        l_response.expirationDateType = l_strCarriedOrderUnit;
        
        //�����L�������F �i�ȉ��̂Ƃ���j
        //   is�o����܂Œ����P��()�̖߂�l == true �̏ꍇ�A�����P��.����������
        //   is�o����܂Œ����P��()�̖߂�l == false �̏ꍇ�Anull
        if (l_blnCarriedOrderUnit)
        {
            log.debug("is�o����܂Œ����P��()�̖߂�l == true �̏ꍇ");
            l_response.expirationDate = WEB3DateUtility.toDay(
                l_feqOrderUnitParams.getExpirationDate());
        }
        else
        {
            log.debug("is�o����܂Œ����P��()�̖߂�l == false �̏ꍇ");
            l_response.expirationDate = null;
        }
        
        //���������F �����P��.��������
        l_response.orderCondType = l_feqOrderUnitParams.getOrderConditionType();
        
        //�T�Z��n����F �����P��.�T�Z��n���
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(
            l_feqOrderUnitParams.getEstimatedPrice());
                
        //(*A)�������� == �h�t�w�l�h �̏ꍇ
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
            l_feqOrderUnitParams.getOrderConditionType()))
        {
            //�t�w�l�p���������P���F �����P��.�t�w�l��l
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getStopOrderPrice());
            
            //�t�w�l�p�����������Z�q�F �����P��.�����������Z�q
            l_response.stopOrderCondOperator = 
                l_feqOrderUnitParams.getOrderCondOperator();
        }        
        //(*B)�������� == �hW�w�l�h �̏ꍇ
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
            l_feqOrderUnitParams.getOrderConditionType()))
        {
            //W�w�l�p���������P���F �����P��.�t�w�l��l
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitParams.getStopOrderPrice());
            
            //W�w�l�p�����������Z�q�F �����P��.�����������Z�q
            l_response.wlimitOrderCondOperator = 
                l_feqOrderUnitParams.getOrderCondOperator();
            
            //W�w�l�p�����P���敪�F �i�ȉ��̂Ƃ���j
            //   �����P��.�iW�w�l�j�����w�l == 0 �̏ꍇ�A�h���s�h
            //   �����P��.�iW�w�l�j�����w�l != 0 �̏ꍇ�A�h�w�l�h
            //W�w�l�p�����P���F �i�ȉ��̂Ƃ���j
            //   �����P��.�iW�w�l�j�����w�l == 0 �̏ꍇ�Anull
            //   �����P��.�iW�w�l�j�����w�l != 0 �̏ꍇ�A�����P��.�iW�w�l�j�����w�l
            String l_strWLimitOrderPriceDiv = null;
            String l_strWLimitPrice = null;
            if (l_feqOrderUnitParams.getWLimitPrice() == 0)
            {
                l_strWLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_strWLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_strWLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_feqOrderUnitParams.getWLimitPrice());
            }
            l_response.wLimitOrderPriceDiv = l_strWLimitOrderPriceDiv;
            l_response.wLimitPrice = l_strWLimitPrice;
        }
        
        //(*C)�����P��.is���t()�̖߂�l == true �̏ꍇ
        //���t�\�z�F get���̑����t�\�z()�̖߂�l
        if (l_blnIsBuy)
        {
            l_response.tradingPower = WEB3StringTypeUtility.formatNumber(
                l_dblOtherTradingPower);
        }        
        //(*D)�����P��.is���t()�̖߂�l == false �̏ꍇ
        //�T�Z�뉿�P���F calc�O�݊��Z()�̖߂�l
        else
        {
            l_response.estimatedBookPrice = WEB3StringTypeUtility.formatNumber(
                l_bdCalcForeignCCYAmount.doubleValue());
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * ���������̊m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������jvalidate�����v �Q��<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�O�������v(�O�������jvalidate����)<BR>
     * �@@�@@:   1.17.2 validate����]��<BR> 
     * �@@�@@�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3FeqChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4295F1E102DD
     */
    protected WEB3FeqChangeConfirmResponse validateOrder(
        WEB3FeqChangeConfirmRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 �⏕�����I�u�W�F�N�g���擾����B
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.3 get�㗝���͎�( )
        //�㗝���͎҃I�u�W�F�N�g���擾����B 
        Trader l_trader = this.getTrader();

        //1.4 get�����P��ByOrderId(long)
        //�����P�ʃI�u�W�F�N�g���擾����B 
        //[����] 
        //����ID�F ���N�G�X�g.����ID 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        FeqOrderUnit l_feqOrderUnit = l_feqOrderManager.getOrderUnitByOrderId(
            Long.parseLong(l_request.orderId));        
        
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
        
        WEB3FeqProductManager l_feqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();        

        WEB3FeqProduct l_feqProduct = null;
        try
        {
            //1.5 get�O����������(long)
            //�O�����������I�u�W�F�N�g���擾����B 
            //[����] 
            //����ID�F �����P��.����ID 
            l_feqProduct =
                (WEB3FeqProduct) l_feqProductManager.getProduct(
                    l_feqOrderUnitParams.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when "
                + "l_feqProductManager.getProduct(ProductId) with "
                + "ProductId = "
                + l_feqOrderUnitParams.getProductId());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_feqProduct == null)
        {
            log.debug("Error in �O�����������I�u�W�F�N�g���擾����");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6 get������( )
        //���������擾����B
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.7 �O�����������������e�C���X�^���X�𐶐�����B 
        //[����] 
        //����ID�F �����P��.����ID 
        //�����P��ID�F �����P��.�����P��ID 
        //�������ʁF ���N�G�X�g.�������� 
        //�����P���F ���N�G�X�g.�����P�� 
        WEB3FeqChangeOrderSpec l_feqChangeOrderSpec = 
            new WEB3FeqChangeOrderSpec(
                l_feqOrderUnitParams.getOrderId(), 
                l_feqOrderUnitParams.getOrderUnitId(), 
                Double.parseDouble(l_request.orderQuantity), 
                Double.parseDouble(l_request.limitPrice));
        
        //1.8 (*1) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //�������F get������()�̖߂�l
        l_feqChangeOrderSpec.setBizDate(l_datBizDate);
        
        //�������s�����F ���N�G�X�g.���s����
        FeqExecutionConditionType l_feqExecutionConditionType = 
            WEB3FeqOrderUtility.getExecutionCondition(l_request.execCondType);
        
        l_feqChangeOrderSpec.setChangeExecutionCondition(
            l_feqExecutionConditionType);
        
        //���������L�������F ���N�G�X�g.�����L������
        //�����N�G�X�g.�����L������==null�̏ꍇ�́Aget������()�̖߂�l
        Date l_datExpirationDate = null; 
        if (l_request.expirationDate == null)
        {
            l_datExpirationDate = l_datBizDate;
        }
        else
        {
            l_datExpirationDate = l_request.expirationDate;
        }
        l_feqChangeOrderSpec.setChangeOrderExpirationDate(
            l_datExpirationDate);
        
        //�������������敪�F ���N�G�X�g.���������敪
        l_feqChangeOrderSpec.setChangeOrderExpirationDiv(
            l_request.expirationDateType);
        
        //���������F ���N�G�X�g.��������
        l_feqChangeOrderSpec.setOrderConditionType(l_request.orderCondType);
        
        //�P�j�������� == �h�t�w�l�h �̏ꍇ
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
            l_request.orderCondType))
        {
            log.debug("�������� == �h�t�w�l�h �̏ꍇ");
            
            //���������������Z�q�F ���N�G�X�g.�t�w�l�p�����������Z�q
            l_feqChangeOrderSpec.setChangeOrderCondOperator(
                l_request.stopOrderCondOperator);
            
            log.debug("�O�����������������e.���������������Z�q�F = " + 
                l_request.stopOrderCondOperator);
            
            //�������������P���F ���N�G�X�g.�t�w�l�p���������P��
            l_feqChangeOrderSpec.setChangeOrderCondPrice(
                Double.parseDouble(l_request.stopOrderCondPrice));
            
            log.debug("�O�����������������e.�������������P���F = " + 
                l_request.stopOrderCondPrice);
        }        
        //�Q�j�������� == �hW�w�l�h �̏ꍇ
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                l_request.orderCondType))
        {
            log.debug("�������� == �hW�w�l�h �̏ꍇ");
            
            //���������������Z�q�F ���N�G�X�g.W�w�l�p�����������Z�q
            l_feqChangeOrderSpec.setChangeOrderCondOperator(
                l_request.wlimitOrderCondOperator);
            
            log.debug("�O�����������������e.���������������Z�q�F = " + 
                l_request.wlimitOrderCondOperator);
            
            //�������������P���F ���N�G�X�g.W�w�l�p���������P��
            l_feqChangeOrderSpec.setChangeOrderCondPrice(
                Double.parseDouble(l_request.wlimitOrderCondPrice));
            
            log.debug("�O�����������������e.�������������P���F = " + 
                l_request.wlimitOrderCondPrice);
            
            //�����iW�w�l�j�����w�l�F ���N�G�X�g.W�w�l�p�����P��
            double l_dblWlimitPrice = 0.0D;
            if (!WEB3StringTypeUtility.isEmpty(l_request.wLimitPrice))
            {
                l_dblWlimitPrice = Double.parseDouble(l_request.wLimitPrice);
            }
            l_feqChangeOrderSpec.setChangeWLimitPrice(l_dblWlimitPrice);
            
            log.debug("�O�����������������e.�����iW�w�l�j�����w�l�F = " + 
                l_request.wLimitPrice);
        }
        
        //1.9 ���������̔����R�����s���B 
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        //�������e�F �O�����������������e 
        OrderValidationResult l_orderValidationResult = 
        l_feqOrderManager.validateChangeOrder(
            l_subAccount,
            l_feqChangeOrderSpec);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����R���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R���`�F�b�N�G���[");
        }
        
        //1.10 get�v�Z�p�����P��(�O�������������, String, double, double, boolean)
        //�v�Z�p�����P�����擾����B 
        //[����] 
        //��������F �O����������.get�������()�̖߂�l 
        //���X�F �⏕����.get����X()�̖߂�l 
        //�����P���敪�F ���N�G�X�g.�����P���敪 
        //�����P���F ���N�G�X�g.�����P�� 
        //�����P���F ���N�G�X�g.W�w�l�p�����P�� 
        //is���t�F �����P��.is���t()�̖߂�l 
        WEB3FeqOrderUnit l_web3FeqOrderUnit = (WEB3FeqOrderUnit) l_feqOrderUnit;
        boolean l_blnIsBuy = l_web3FeqOrderUnit.isBuy();
        
        double l_dblLimitPrice = 0.0D;
        double l_dblWlimitPrice = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_request.limitPrice))
        {
            l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
        }
        if (!WEB3StringTypeUtility.isEmpty(l_request.wLimitPrice))
        {
            l_dblWlimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        
        double l_dblUnitPrice = 
            l_feqOrderManager.getUnitPrice(
                l_feqProduct.getFeqTradedProduct(), 
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(), 
                l_request.orderPriceDiv, 
                l_dblLimitPrice, 
                l_dblWlimitPrice, 
                l_blnIsBuy);
        
        //1.11 calc�������(double, double) ����������v�Z����B 
        //[����] 
        //�����F ���N�G�X�g.�������� - �����P��.��萔�� 
        //�P���F get�v�Z�p�����P��()�̖߂�l 
        WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
       
        double l_lngQuantity = 
            Double.parseDouble(l_request.orderQuantity) - 
            l_feqOrderUnitParams.getExecutedQuantity();
                    
        double l_dblExecutionAmount = 
            l_feqBizLogicProvider.calcExecutionAmount(
                l_lngQuantity, 
                l_dblUnitPrice);
        
        //1.12 �s��I�u�W�F�N�g���擾����B
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.13 �ʉ݃I�u�W�F�N�g���擾����B
        WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
        
        //calc�������(double, double)�̌��ʂ��A���Y�ʉ݂̏����_�����Ŋۂ߂�
        BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblExecutionAmount);
        int l_intDecimalPlace = l_currency.getScale();
        l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_dblExecutionAmount = l_bdTradePriceFc.doubleValue();
        log.debug("��������̊ۂ߂����� �� "+ l_dblExecutionAmount);
        
        //1.14 get�בփ��[�g(boolean, boolean, double)(�ʉ�::get�בփ��[�g)
        //��בփ��[�g���擾����B 
        //[����] 
        //is���t�F �����P��.is���t()�̖߂�l 
        //is���v�Z�F false 
        //���͈בփ��[�g�F 0
        double l_dblFxRate = l_currency.getExchangeRate(
            l_blnIsBuy, false, 0);
        
        //1.15 calc�����O���������z() ������̊e����z�̌v�Z���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�O�����������F get�O����������()�̖߂�l 
        //�s��F get�s��()�̖߂�l 
        //����F get������()�̖߂�l 
        //��������i�O�݁j�F calc�������()�̖߂�l 
        //�בփ��[�g�F get�בփ��[�g()�̖߂�l 
        //is���t�F �����P��.is���t()�̖߂�l 
        //is���v�Z�F false 
        //is�w�l�F �i�ȉ��̂Ƃ���j 
        //   ���N�G�X�g.�����P���敪 == �h�w�l�h �̏ꍇ�Atrue 
        //   ���N�G�X�g.�����P���敪 == �h���s�h �̏ꍇ�Afalse 
        //�����P�ʁF �����P�ʃI�u�W�F�N�g 
        boolean l_blnIsLimitPrice = false;
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.orderPriceDiv))
        {
            l_blnIsLimitPrice = true;
        }
        else if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(
            l_request.orderPriceDiv)) 
        {
            l_blnIsLimitPrice = false;
        }        
        WEB3FeqAmountCalcResult l_feqAmountCalcResult = 
            l_feqBizLogicProvider.calcChangeFeqAmount(
                (WEB3GentradeSubAccount)l_subAccount, 
                l_feqProduct, 
                l_market, 
                l_datBizDate, 
                l_dblExecutionAmount, 
                l_dblFxRate, 
                l_blnIsBuy, 
                false,
                l_blnIsLimitPrice,
                l_feqOrderUnit);
        
        BigDecimal l_dbForeignCCYAmount = new BigDecimal("0");   //�T�Z�뉿�P�����O�݊��Z

        //1.16.1 �O�����������X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
        //[����] 
        //�������e�F �O�����������������e�I�u�W�F�N�g 
        //�v�Z���ʁF �O���������z�v�Z���ʃI�u�W�F�N�g
        //�v�Z�P���F get�v�Z�p�����P��()�̖߂�l 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        
        WEB3FeqChangeUpdateInterceptor l_feqChangeUpdateInterceptor = 
            new WEB3FeqChangeUpdateInterceptor(
                l_feqChangeOrderSpec, 
                l_feqAmountCalcResult,
                l_dblUnitPrice, 
                l_trader);

        // is���v�����̗p( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
        boolean l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();

        //is���v��s��( )
        boolean l_blnIsDayTradeMarket = l_market.isDayTradeMarket();

        //�����P��.is���t()�̖߂�l == true �܂��́@@(�����P��.is���t()�̖߂�l ==false
        //���� is���v�����̗p()��true�@@���@@is���v��s��()�@@���@@true)�̏ꍇ
        if (l_blnIsBuy || (!l_blnIsBuy && l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket))
        {
            log.debug("�����P��.is���t()�̖߂�l == true �܂��́@@(�����P��.is���t()�̖߂�l ==false" +
                "�@@���� is���v�����̗p()��true�@@���@@is���v��s��()�@@���@@true)�̏ꍇ");
            //1.16.2 ����]�͂̃`�F�b�N���s���B 
            //[����] 
            //�⏕�����F get�⏕����()�̖߂�l 
            //�������e�C���^�Z�v�^�F �O�����������X�V�C���^�Z�v�^��v�f�Ƃ����z�� 
            //�������e�F �O�����������������e��v�f�Ƃ����z�� 
            //������ʁF �����P��.������� 
            //�]�͍X�V�t���O�F false 
            
            //�������e�C���^�Z�v�^�̔z��            
            WEB3FeqChangeUpdateInterceptor[] l_feqChangeUpdateInterceptors = 
                {l_feqChangeUpdateInterceptor};
                
            //�������e�̔z��
            WEB3FeqChangeOrderSpec[] l_feqChangeOrderSpecs = 
                {l_feqChangeOrderSpec}; 
            
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            
            WEB3TPTradingPowerResult l_tPTradingPowerResult = 
                l_tpTradingPowerService.validateTradingPower(
                    (WEB3GentradeSubAccount)l_subAccount, 
                    l_feqChangeUpdateInterceptors, 
                    l_feqChangeOrderSpecs, 
                    l_feqOrderUnitParams.getOrderType(), 
                    false);
            
            //�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[����B
            log.debug("�߂�l�̎���]�͌���.����t���O == " + l_tPTradingPowerResult.isResultFlg());
            
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
        //1.17 (*3) �����P��.is���t()�̖߂�l == false �̏ꍇ
        else
        {
            log.debug("�����P��.is���t()�̖߂�l == false �̏ꍇ");
            
            //1.17.1 calc�T�Z�뉿�P��() �T�Z�뉿�P�����v�Z����B 
            //[����] 
            //�⏕�����F �⏕�����I�u�W�F�N�g 
            //����ID�F �����P��.����ID 
            //�ŋ敪�F �����P��.�ŋ敪 
            BigDecimal l_bdEstimatedBookValuePrice = 
                l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                    (WEB3GentradeSubAccount)l_subAccount, 
                    l_feqOrderUnitParams.getProductId(),
                    l_feqOrderUnitParams.getTaxType());
            
            //1.17.2  calc�O�݊��Z(double, double, int, String)
            //�T�Z�뉿�P�����O�݊��Z����B 
            //[����] 
            //���z�i�~�݁j�F calc�T�Z�뉿�P��()�̖߂�l 
            //���[�g�F get�בփ��[�g()�̖߂�l 
            //�����������F �ʉ�.get����������()�̖߂�l 
            //�O�݊��Z�ۂߕ����F �ʉ�.get�O�݊��Z�ۂߕ���()�̖߂�l 
            l_dbForeignCCYAmount = 
                l_feqBizLogicProvider.calcForeignCCYAmount(
                    l_bdEstimatedBookValuePrice, 
                    l_dblFxRate, 
                    l_currency.getScale(),
                    l_currency.getChangeFCcyRoundDiv());
        }
        
        //1.18 get�s��ǌx���O���s��(���X : ���X)
        //�s��ǌx���s����擾����B 
        //[����] 
        //���X�F �⏕����.get����X()�̖߂�l 
        String[] l_strTradeCloseFeqMarkets = 
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch());
        
        //1.19 ���X�|���X�f�[�^�𐶐�����B         
        WEB3FeqChangeConfirmResponse l_response = 
            (WEB3FeqChangeConfirmResponse) l_request.createResponse();
        
        //1.20 (*4)�v���p�e�B�Z�b�g
        //(*4) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //���o�����ʁF �����P��.��萔��
        l_response.partContQuantity = WEB3StringTypeUtility.formatNumber(
            l_feqOrderUnitParams.getExecutedQuantity());
        
        //(*A)�����P��.���ϋ敪 == �h�~�݁h �̏ꍇ�A�~�݊��Z��������
        //    �����P��.���ϋ敪 == �h�O�݁h �̏ꍇ�A�O�݊��Z�������� ���Z�b�g
        
        if (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(
            l_feqOrderUnitParams.getSettleDiv()))
        {
            log.debug("�����P��.���ϋ敪 == �h�~�݁h �̏ꍇ");
            //�T�Z��n����F �O���������z�v�Z����.��n���(*A)
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(
                l_feqAmountCalcResult.getNetAmount());
            
            //�萔���F �O���������z�v�Z����.�ϑ��萔��(*A)
            l_response.commission = WEB3StringTypeUtility.formatNumber(
                l_feqAmountCalcResult.getCommissionFee());
            
            //�萔������ŁF �O���������z�v�Z����.�ϑ��萔�������(*A)
            l_response.commissionConsumptionTax = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getCommisionFeeTax());        
        }
        else
        {
            log.debug("�����P��.���ϋ敪 == �h�O�݁h �̏ꍇ");
            //�T�Z��n����F �O���������z�v�Z����.��n����i�O�݁j(*A)
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(
                l_feqAmountCalcResult.getNetAmountFc());
            
            //�萔���F �O���������z�v�Z����.�ϑ��萔���i�O�݁j(*A)
            l_response.commission = WEB3StringTypeUtility.formatNumber(
                l_feqAmountCalcResult.getCommissionFeeFc());
            
            //�萔������ŁF �O���������z�v�Z����.�ϑ��萔������Łi�O�݁j(*A)
            l_response.commissionConsumptionTax = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getCommisionFeeTaxFc());
        }
        //(*B) �����P��.is���t()�̖߂�l == false �̏ꍇ
        if (!l_blnIsBuy)
        {  
            //�T�Z�뉿�P���F calc�O�݊��Z()�̖߂�l
            l_response.estimatedBookPrice = 
                WEB3StringTypeUtility.formatNumber(l_dbForeignCCYAmount.doubleValue());
            
            log.debug("�T�Z�뉿�P���F= " + l_response.estimatedBookPrice);
        }
        
        //�m�F���P���F get�v�Z�p�����P��()�̖߂�l
        l_response.checkPrice = 
            WEB3StringTypeUtility.formatNumber(l_dblUnitPrice);
        
        //�m�F���������F get������()�̖߂�l
        l_response.checkDate = l_datBizDate;            
        
        //����I���x���s��R�[�h�ꗗ�F get�s��ǌx���O���s��()�̖߂�l
        l_response.messageSuspension = l_strTradeCloseFeqMarkets;            
                
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * ���������̍X�V�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������jsubmit�����v �Q��<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�O�������v(�O�������jsubmit����)<BR>
     * �@@�@@:   1.17.1 validate����]��<BR> 
     * �@@�@@�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3FeqChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4295F1E102ED
     */
    protected WEB3FeqChangeCompleteResponse submitOrder(
        WEB3FeqChangeCompleteRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 �⏕�����I�u�W�F�N�g���擾����B
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.3 get�㗝���͎�( )
        //�㗝���͎҃I�u�W�F�N�g���擾����B 
        Trader l_trader = this.getTrader();

        //1.4 get�����P��ByOrderId(long)
        //�����P�ʃI�u�W�F�N�g���擾����B 
        //[����] 
        //����ID�F ���N�G�X�g.����ID 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        FeqOrderUnit l_feqOrderUnit = l_feqOrderManager.getOrderUnitByOrderId(
            Long.parseLong(l_request.orderId));
        
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
        
        WEB3FeqProductManager l_feqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();        

        WEB3FeqProduct l_feqProduct = null;
        try
        {
            //1.5 get�O����������(long)
            //�O�����������I�u�W�F�N�g���擾����B 
            //[����] 
            //����ID�F �����P��.����ID 
            l_feqProduct =
                (WEB3FeqProduct) l_feqProductManager.getProduct(
                    l_feqOrderUnitParams.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_feqProduct == null)
        {
            log.debug("Error in �O�����������I�u�W�F�N�g���擾����");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6 get������( )
        //���������擾����B
        Date l_datBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.7 �O�����������������e�C���X�^���X�𐶐�����B 
        //[����] 
        //����ID�F �����P��.����ID 
        //�����P��ID�F �����P��.�����P��ID 
        //�������ʁF ���N�G�X�g.�������� 
        //�����P���F ���N�G�X�g.�����P�� 
        WEB3FeqChangeOrderSpec l_feqChangeOrderSpec = 
            new WEB3FeqChangeOrderSpec(
                l_feqOrderUnitParams.getOrderId(), 
                l_feqOrderUnitParams.getOrderUnitId(), 
                Double.parseDouble(l_request.orderQuantity), 
                Double.parseDouble(l_request.limitPrice));
        
        //1.8 (*1) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //�������F get������()�̖߂�l
        l_feqChangeOrderSpec.setBizDate(l_datBizDate);
        
        //�������s�����F ���N�G�X�g.���s����

        FeqExecutionConditionType l_feqExecutionConditionType = 
            WEB3FeqOrderUtility.getExecutionCondition(l_request.execCondType);

        l_feqChangeOrderSpec.setChangeExecutionCondition(l_feqExecutionConditionType);
        
        //���������L�������F ���N�G�X�g.�����L������
        //�����N�G�X�g.�����L������==null�̏ꍇ�́Aget������()�̖߂�l
        Date l_datExpirationDate = null;
        if (l_request.expirationDate == null)
        {
            l_datExpirationDate = l_datBizDate;
        }
        else
        {
            l_datExpirationDate = l_request.expirationDate;
        }
        l_feqChangeOrderSpec.setChangeOrderExpirationDate(l_datExpirationDate);
        
        //�������������敪�F ���N�G�X�g.���������敪
        l_feqChangeOrderSpec.setChangeOrderExpirationDiv(l_request.expirationDateType);
        
        //���������F ���N�G�X�g.��������
        l_feqChangeOrderSpec.setOrderConditionType(l_request.orderCondType);
       
        //�P�j�������� == �h�t�w�l�h �̏ꍇ
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
            l_request.orderCondType))
        {
            log.debug("�������� == �h�t�w�l�h �̏ꍇ");
            //���������������Z�q�F ���N�G�X�g.�t�w�l�p�����������Z�q
            l_feqChangeOrderSpec.setChangeOrderCondOperator(
                l_request.stopOrderCondOperator);
            
            //�������������P���F ���N�G�X�g.�t�w�l�p���������P��
            l_feqChangeOrderSpec.setChangeOrderCondPrice(
                Double.parseDouble(l_request.stopOrderCondPrice));
        }        
        //�Q�j�������� == �hW�w�l�h �̏ꍇ
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                l_request.orderCondType))
        {
            log.debug("�������� == �hW�w�l�h �̏ꍇ");
            //���������������Z�q�F ���N�G�X�g.W�w�l�p�����������Z�q
            l_feqChangeOrderSpec.setChangeOrderCondOperator(
                l_request.wlimitOrderCondOperator);
            
            double l_dblWlimitOrderCondPrice = 0.0D;
            double l_dblWlimitPrice = 0.0D;
            if (!WEB3StringTypeUtility.isEmpty(l_request.wlimitOrderCondPrice))
            {
                l_dblWlimitOrderCondPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
            if (!WEB3StringTypeUtility.isEmpty(l_request.wLimitPrice))
            {
                l_dblWlimitPrice = Double.parseDouble(l_request.wLimitPrice);
            }
            
            //�������������P���F ���N�G�X�g.W�w�l�p���������P��
            l_feqChangeOrderSpec.setChangeOrderCondPrice(l_dblWlimitOrderCondPrice);
            
            //�����iW�w�l�j�����w�l�F ���N�G�X�g.W�w�l�p�����P��
            l_feqChangeOrderSpec.setChangeWLimitPrice(l_dblWlimitPrice);
        }
        
        //1.9 ���������̔����R�����s���B 
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        //�������e�F �O�����������������e 
        OrderValidationResult l_orderValidationResult = 
            l_feqOrderManager.validateChangeOrder(
                l_subAccount,
                l_feqChangeOrderSpec);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����R���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R���`�F�b�N�G���[");
        }
        
        //1.10 calc�������(double, double) ����������v�Z����B 
        //[����] 
        //�����F �����F ���N�G�X�g.�������� - �����P��.��萔�� 
        //�P���F ���N�G�X�g.�m�F���P��  
        WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
             
        double l_lngQuantity = 
            Double.parseDouble(l_request.orderQuantity) - 
                l_feqOrderUnitParams.getExecutedQuantity();
        
        double l_dblExecutionAmount = 
            l_feqBizLogicProvider.calcExecutionAmount(
                l_lngQuantity, 
                Double.parseDouble(l_request.checkPrice));
        
        //1.11 �s��I�u�W�F�N�g���擾����B
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.12 �ʉ݃I�u�W�F�N�g���擾����B
        WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
        
        //calc�������(double, double)�̌��ʂ��A���Y�ʉ݂̏����_�����Ŋۂ߂�
        BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblExecutionAmount);
        int l_intDecimalPlace = l_currency.getScale();
        l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_dblExecutionAmount = l_bdTradePriceFc.doubleValue();
        log.debug("��������̊ۂ߂����� �� "+ l_dblExecutionAmount);
        
        //1.13 get�בփ��[�g(boolean, boolean, double)(�ʉ�::get�בփ��[�g)
        //��בփ��[�g���擾����B 
        //[����] 
        //is���t�F �����P��.is���t()�̖߂�l 
        //is���v�Z�F false 
        //���͈בփ��[�g�F 0 

        WEB3FeqOrderUnit l_web3FeqOrderUnit = (WEB3FeqOrderUnit) l_feqOrderUnit;
        boolean l_blnIsBuy = l_web3FeqOrderUnit.isBuy();
        
        double l_dblFxRate = l_currency.getExchangeRate(
            l_blnIsBuy, 
            false,
            0);
        
        //1.14 calc�����O���������z() ������̊e����z�̌v�Z���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�O�����������F get�O����������()�̖߂�l 
        //�s��F get�s��()�̖߂�l 
        //����F get������()�̖߂�l 
        //��������i�O�݁j�F calc�������()�̖߂�l 
        //�בփ��[�g�F get�בփ��[�g()�̖߂�l 
        //is���t�F �����P��.is���t()�̖߂�l 
        //is���v�Z�F false 
        //is�w�l�F �i�ȉ��̂Ƃ���j 
        //   ���N�G�X�g.�����P���敪 == �h�w�l�h �̏ꍇ�Atrue 
        //   ���N�G�X�g.�����P���敪 == �h���s�h �̏ꍇ�Afalse 
        //�����P�ʁF �����P�ʃI�u�W�F�N�g 
        boolean l_blnIsLimitPrice = false;
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.orderPriceDiv))
        {
            l_blnIsLimitPrice = true;
        }
        else if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv)) 
        {
            l_blnIsLimitPrice = false;
        }
        
        WEB3FeqAmountCalcResult l_feqAmountCalcResult = 
            l_feqBizLogicProvider.calcChangeFeqAmount(
                (WEB3GentradeSubAccount)l_subAccount, 
                l_feqProduct, 
                l_market, 
                l_datBizDate, 
                l_dblExecutionAmount, 
                l_dblFxRate, 
                l_blnIsBuy, 
                false,
                l_blnIsLimitPrice,
                l_feqOrderUnit);        
        
        //1.15 �O�����������X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
        //[����] 
        //�������e�F �O�����������������e�I�u�W�F�N�g 
        //�v�Z���ʁF �O���������z�v�Z���ʃI�u�W�F�N�g
        //�v�Z�P���F ���N�G�X�g.�m�F���P��  
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        
        WEB3FeqChangeUpdateInterceptor l_feqChangeUpdateInterceptor = 
            new WEB3FeqChangeUpdateInterceptor(
                l_feqChangeOrderSpec, 
                l_feqAmountCalcResult, 
                Double.parseDouble(l_request.checkPrice), 
                l_trader);

        // is���v�����̗p( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
        boolean l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();

        //is���v��s��( )
        boolean l_blnIsDayTradeMarket = l_market.isDayTradeMarket();

        //�����P��.is���t()�̖߂�l == true �܂��́@@(�����P��.is���t()�̖߂�l ==false
        //���� is���v�����̗p()��true�@@���@@is���v��s��()�@@���@@true)�̏ꍇ
        if (l_blnIsBuy || (!l_blnIsBuy && l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket))
        {
            //1.16.1 ����]�͂̃`�F�b�N���s���B 
            //[����] 
            //�⏕�����F get�⏕����()�̖߂�l 
            //�������e�C���^�Z�v�^�F �O�����������X�V�C���^�Z�v�^��v�f�Ƃ����z�� 
            //�������e�F �O�����������������e��v�f�Ƃ����z�� 
            //������ʁF �����P��.������� 
            //�]�͍X�V�t���O�F false 
            
            //�������e�C���^�Z�v�^�̔z��            
            WEB3FeqChangeUpdateInterceptor[] l_feqChangeUpdateInterceptors = 
                {l_feqChangeUpdateInterceptor};
                
            //�������e�̔z��
            WEB3FeqChangeOrderSpec[] l_feqChangeOrderSpecs = 
                {l_feqChangeOrderSpec}; 
            
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            
            WEB3TPTradingPowerResult l_tPTradingPowerResult = 
                l_tpTradingPowerService.validateTradingPower(
                    (WEB3GentradeSubAccount)l_subAccount, 
                    l_feqChangeUpdateInterceptors, 
                    l_feqChangeOrderSpecs, 
                    l_feqOrderUnitParams.getOrderType(), 
                    false);
            
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
        
        //1.17 setThreadLocalPersistenceEventInterceptor
        l_feqOrderManager.setThreadLocalPersistenceEventInterceptor(
        	l_feqChangeUpdateInterceptor);
        
        //1.18 submitChangeOrder
        OrderSubmissionResult l_orderSubmissionResult = l_feqOrderManager.submitChangeOrder(
            l_subAccount,
        	l_feqChangeOrderSpec,
        	l_request.password,
        	true);
        
        if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitChangeOrder" +
                l_orderSubmissionResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }
        
        //1.19 �]�͍Čv�Z
        //�]�͍Čv�Z
        WEB3TPTradingPowerService l_tradingPowerService = 
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        l_tradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        
        //1.20 getOrder
        Order l_order = null;
        try
        {
        	l_order = l_feqOrderManager.getOrder(l_feqChangeOrderSpec.getOrderId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.21 ���X�|���X�f�[�^�𐶐�����B  
        
        WEB3FeqChangeCompleteResponse l_response = 
            (WEB3FeqChangeCompleteResponse) l_request.createResponse();
        
        FeqOrderRow l_feqOrderRow = (FeqOrderRow) l_order.getDataSourceObject();
     
        //1.22 (*3) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //�X�V���ԁF ����.�X�V����
        l_response.lastUpdatedTimestamp = l_feqOrderRow.getLastUpdatedTimestamp();
        
        //����ID�F ����.����ID
        l_response.orderId = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderRow.getOrderId());
      
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
