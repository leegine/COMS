head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBuyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������t�T�[�r�XImpl(WEB3FeqBuyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
Revesion History : 2009/09/23 �И���(���u) ���f��No.523,No.524,No.525,No.526,No.527
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
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
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqNewOrderSpec;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUpdateInterceptor;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqProductQuote;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.message.WEB3FeqBuyCompleteRequest;
import webbroker3.feq.message.WEB3FeqBuyCompleteResponse;
import webbroker3.feq.message.WEB3FeqBuyConfirmRequest;
import webbroker3.feq.message.WEB3FeqBuyConfirmResponse;
import webbroker3.feq.message.WEB3FeqBuyInputRequest;
import webbroker3.feq.message.WEB3FeqBuyInputResponse;
import webbroker3.feq.message.WEB3FeqBuyProductSelectRequest;
import webbroker3.feq.message.WEB3FeqBuyProductSelectResponse;
import webbroker3.feq.message.WEB3FeqTradingPowerUnit;
import webbroker3.feq.service.delegate.WEB3FeqBuyService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O���������t�T�[�r�XImpl)<BR>
 * �O���������t�T�[�r�X�����N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqBuyServiceImpl 
    extends WEB3FeqClientRequestService implements WEB3FeqBuyService 
{
   /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBuyServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F8000F
     */
    public WEB3FeqBuyServiceImpl() 
    {
     
    }
    
    /**
     * �O���������t�T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *    get���͉��()<BR>
     *    validate����()<BR>
     *    submit����()<BR>
     *    get�����I�����()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AF0B202F6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3FeqBuyInputRequest)
        {
            //get���͉��
            l_response =
                this.getInputScreen(
                    (WEB3FeqBuyInputRequest) l_request);
        }
        else if (l_request instanceof WEB3FeqBuyConfirmRequest)
        {
            //validate����
            l_response =
                this.validateOrder(
                    (WEB3FeqBuyConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3FeqBuyCompleteRequest)
        {
            //submit����
            l_response =
                this.submitOrder(
                    (WEB3FeqBuyCompleteRequest) l_request);
        }
        else if (l_request instanceof WEB3FeqBuyProductSelectRequest)
        {
            //get�����I�����
            l_response =
                this.getProductSelectScreen(
                    (WEB3FeqBuyProductSelectRequest)l_request);
        }
        else
        {
            log.debug(
                "���N�G�X�g�f�[�^��"
                    + " WEB3FeqBuyInputRequest "
                    + "�� WEB3FeqBuyConfirmRequest "
                    + "�� WEB3FeqBuyCompleteRequest "
                    + "�� WEB3FeqBuyProductSelectRequest �ȊO�ł���, but is "
                    + l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
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
     * �V�[�P���X�}�u�i�O�����t�jget���͉�ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * 
     * @@return webbroker3.feq.message.WEB3FeqBuyInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AF4BA018F
     */
    protected WEB3FeqBuyInputResponse getInputScreen(WEB3FeqBuyInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqBuyInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1)get�⏕����()
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();
            
        //�O�����������}�l�[�W��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        //�،���ЁF �iget�⏕����()�̖߂�l�j.getInstitutuin()�̖߂�l
        WEB3GentradeInstitution l_institution = 
            (WEB3GentradeInstitution) l_subAccount.getInstitution(); 
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        
        //������( )
        Date l_datOrderBizDate = null;
        //�戵�\��������
        WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond = null;

        //�ڋq
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            
        //1.4.3 (*3)�擾����������
        Date l_datOrderResultBizDate = null;    

        //�����R�[�h
        String l_strProductCode = null;
        //���n�����R�[�h
        String l_strOffshoreProductCode = null;
        //�\��������
        String l_strProductName = null;

        //�s��R�[�h
        String l_strMarketCode = null;
        //�O������            
        WEB3FeqProduct l_feqProduct = null;
        //�O�������������
        WEB3FeqTradedProduct l_feqTradedProduct = null;
        
        //1.2 (*1)���N�G�X�g.�����R�[�h != null �̏ꍇ
        log.debug("���N�G�X�g.�����R�[�h = " + l_request.productCode);
        if (l_request.productCode != null)
        {
            //1.3.1 validate�O������(�،����, String)
            l_feqProduct =
                (WEB3FeqProduct) l_feqOrderManager.validateFeqProduct(
                    l_institution, 
                    l_request.productCode);
                    
            //�����R�[�h
            l_strProductCode = l_feqProduct.getProductCode();  
            //���n�����R�[�h
            l_strOffshoreProductCode = l_feqProduct.getOffshoreProductCode();
            //�\���������F
            l_strProductName = l_feqProduct.getDisplayProductName();
            
            //1.2.2 get�s��( )
            WEB3GentradeMarket l_market = l_feqProduct.getMarket();
            l_strMarketCode = l_market.getMarketCode();
            log.debug("�s��R�[�h = " + l_strMarketCode);
                        
            //1.2.3 validate�s��(�s��)
            l_feqOrderManager.validateMarket(l_market);
            
            //1.2.4 reset�s��R�[�h(�s��R�[�h : String)
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            
            //1.2.5 validate����(�⏕����)
            l_feqOrderManager.validateOrder(l_subAccount); 
            
            //1.2.6 validate�������(�O����������, �s��, boolean)
            l_feqTradedProduct =
                (WEB3FeqTradedProduct) l_feqOrderManager.validateTradedProduct(
                    l_feqProduct,
                    l_market,
                    true);
                    
            //1.2.7 validate�ڋq�����ʎ����~(SubAccount, long, OrderTypeEnum)
            l_feqOrderManager.validateAccountProductTradedStop(
                l_subAccount, 
                l_feqProduct.getProductId(), 
                OrderTypeEnum.FEQ_BUY);
                
            //1.2.8 get������( )
            l_datOrderBizDate = 
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        else
        {
            //1.3 (*2) ���N�G�X�g.�����R�[�h == null �̏ꍇ
            
            //1.3.1 validate����(�⏕����)
            l_feqOrderManager.validateOrder(l_subAccount); 
            
            //1.3.2 get������戵�\�s��(���X : ���X, �����^�C�v : ProductTypeEnum)
            //���X�F �⏕����.get����X()�̖߂�l
            WEB3GentradeBranch l_branch = 
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
            String[] l_strHandlingPossibleMarkets =
                WEB3GentradeFeqBranchMarketDealtCond.getTradingHandlingPossibleMarket(
                    l_branch,
                    ProductTypeEnum.FOREIGN_EQUITY);
            if (l_strHandlingPossibleMarkets == null || 
                l_strHandlingPossibleMarkets.length == 0)
            {
                log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } 
            int l_intLengthTemp = l_strHandlingPossibleMarkets.length;

            //1.3.3 �s��R�[�h�̔z��iget������戵�\�s��()�̖߂�l�j�̗v�f���ALoop���� 
            for (int i = 0; i < l_intLengthTemp; i++)
            {
                //1.3.3.1 reset�s��R�[�h(�s��R�[�h : String)
                WEB3GentradeTradingTimeManagement.resetMarketCode(
                    l_strHandlingPossibleMarkets[i]);
                
                //1.3.3.2  get������( )
                l_datOrderBizDate = 
                    WEB3GentradeTradingTimeManagement.getOrderBizDate();
               
                //���ׂĂ̔�����!=���[�J���X���b�h�̌��ݎ����̓��t���� 
                //�łȂ��ꍇ�A1�Ԗڂ̗v�f���L�����Ă����B
                if (i == 0)
                {
                    l_datOrderResultBizDate = l_datOrderBizDate;
                }

                //1.3.4 (*3)
                //�擾����������==���[�J���X���b�h�̌��ݎ����̓��t���� 
                //�ƂȂ�v�f������ꍇ�A���̓��t���L�����Ă����B
                Date l_datCurrentDate = 
                    WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
                if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datCurrentDate) == 0)
                {
                    l_datOrderResultBizDate = l_datCurrentDate;
                }
            }
        }  
        
        //���N�G�X�g.�����R�[�h == null �̏ꍇ
        //1.4 �戵�\��������(String, ProductTypeEnum, String, String)
        //[����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //�����^�C�v�F �h�O�������h 
        //�敨�^�I�v�V�����敪�F �hDEFAULT�h(�Œ�) 
        //�M�p����敪�F �hDEFAULT�h(�Œ�) 
        if (l_request.productCode == null)
        {
            l_gentradeHandlingOrderCond = 
                new WEB3GentradeHandlingOrderCond(
                    l_strInstitutionCode,
                    ProductTypeEnum.FOREIGN_EQUITY,
                    WEB3FuturesOptionDivDef.DEFAULT,
                    WEB3MarginTradingDivDef.DEFAULT);
        }
            
        //���N�G�X�g.�����R�[�h != null �̏ꍇ
        //1.5 �戵�\��������(String, ProductTypeEnum, String, String, String)
        //�戵�\���������C���X�^���X���擾����B 
        //[����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //�����^�C�v�F �h�O�������h 
        //�敨�^�I�v�V�����敪�F �hDEFAULT�h(�Œ�) 
        //�M�p����敪�F �hDEFAULT�h(�Œ�) 
        //�s��R�[�h�F �O����������.get�s��R�[�h�̖߂�l
        else
        {
            l_gentradeHandlingOrderCond = 
                new WEB3GentradeHandlingOrderCond(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    ProductTypeEnum.FOREIGN_EQUITY,
                    WEB3FuturesOptionDivDef.DEFAULT,
                    WEB3MarginTradingDivDef.DEFAULT,
                    l_strMarketCode);
        }
        
        //1.6 �戵�\���s�����擾( )
        String[] l_strHandlingPossibleExecConds = 
            l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();
        
        //1.7 �戵�\���������擾( )      
        String[] l_strHandlingPossibleOrderConds = 
            l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();
            
        //1.8 �戵�\���������敪�擾( )
        String[] l_strHandlingPossibleExpirationDateTypes =
            l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateType();
        
        //1.9 is�o����܂Œ����戵�\( )
        boolean l_blnIsOrderUntilHandling =
            l_gentradeHandlingOrderCond.isOrderUntilDeadLinePossibleHandling();
        log.debug("is�o����܂Œ����戵�\()�̖߂�l = " + l_blnIsOrderUntilHandling);
        
        //1.10 (*3) is�o����܂Œ����戵�\()�̖߂�l == true �̏ꍇ
        
        //�o����܂Œ����J�n��
        Date l_datOrderUntilDeadLineStartDay = null;
        //�o����܂Œ����ŏI��
        Date l_datOrderUntilDeadLineEndDay = null;       
        //�����������j���ꗗ
        Date[] l_datExpirationDateHolidays = null;
        
        if (l_blnIsOrderUntilHandling)
        {
            //1.10.1 get�o����܂Œ����J�n��(Date)
            //[����] 
            //�o����܂Œ���from���t�F �i�ȉ��̂Ƃ���j 
            //���N�G�X�g.�����R�[�h != null �̏ꍇ�Anull 
            if (l_request.productCode != null)
            {
                l_datOrderUntilDeadLineStartDay = 
                    l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay(null);
            }
            else
            {
                //���N�G�X�g.�����R�[�h == null �̏ꍇ�A(*3)�ŋL���������t
                l_datOrderUntilDeadLineStartDay = 
                    l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay(
                        l_datOrderResultBizDate);
            }

            //1.10.2 get�o����܂Œ����ŏI��(Date)
            //[����] 
            //�o����܂Œ���from���t�F �i�ȉ��̂Ƃ���j 
            //���N�G�X�g.�����R�[�h != null �̏ꍇ�Anull 
            if (l_request.productCode != null)
            {
                l_datOrderUntilDeadLineEndDay = 
                    l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDay(null);
            }
            else
            {
                //���N�G�X�g.�����R�[�h == null �̏ꍇ�A(*3)�ŋL���������t
                l_datOrderUntilDeadLineEndDay = 
                    l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDay(
                        l_datOrderResultBizDate);
            }
             
            //1.10.3 get�����������j���ꗗ(Date)
            //[����] 
            //�o����܂Œ���from���t�F �i�ȉ��̂Ƃ���j 
            //���N�G�X�g.�����R�[�h != null �̏ꍇ�Anull 
            if (l_request.productCode != null)
            {
                l_datExpirationDateHolidays = 
                    l_gentradeHandlingOrderCond.getExpirationDateHoliday(null);
            }
            else
            {
                //���N�G�X�g.�����R�[�h == null �̏ꍇ�A(*3)�ŋL���������t
                l_datExpirationDateHolidays = 
                    l_gentradeHandlingOrderCond.getExpirationDateHoliday(
                        l_datOrderResultBizDate);
            }
        }
           
        //1.11 get�s��ǌx���O���s��(���X : ���X) 
        String[] l_strTradeCloseFeqMarkets =
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(
                l_subAccount.getWeb3GenBranch());
                
        //�����擾�敪
        String l_strCurrentPriceGetDiv = null;
        //����
        String l_strCurrentPrice = null;
        //�O����
        String l_strComparedPreviousDay = null;
        //�������\����
        Date l_DatCurrentPricePublicTime = null;
        boolean l_blnfeqProductQuoteIsNull = true;
        
        //1.12 ���N�G�X�g�f�[�^.�����R�[�h != null�̏ꍇ
        if (l_request.productCode != null)
        {
            l_blnfeqProductQuoteIsNull = false;
            
            //1.12.1 get�\���p�������(�O�������������, �⏕����)
            WEB3FeqProductManager l_feqProductManager =
                (WEB3FeqProductManager) GtlUtils.getTradingModule(
                    ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
            //�O�������������   
            WEB3FeqProductQuote l_feqProductQuote =
                l_feqProductManager.getIndicationCurrentPriceUnit(
                    l_feqTradedProduct, l_subAccount);
            if (l_feqProductQuote != null)
            {
                //1.13.2 get�����擾�敪( )
                l_strCurrentPriceGetDiv =
                    l_feqProductQuote.getCurrentPriceGetDiv();

                //1.12.3 get����( )
                l_strCurrentPrice = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqProductQuote.getCurrentPrice());
            
                //1.12.4 get�O����( )
                l_strComparedPreviousDay = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqProductQuote.getComparedPreviousDay());
        
                //1.12.5 get�������\����( )
                l_DatCurrentPricePublicTime = 
                    l_feqProductQuote.getCurrentPricePublicTime();
            }
        }
        
        //1.13 get���̑����i���t�\�z(�⏕���� : �⏕����, ��n�� : Date)
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        //��n���F �i�ȉ��̂Ƃ���j 
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        //���̑����i���t�\�z
        double l_dblBuyPossiblePrice = 0D;

        //���N�G�X�g.�����R�[�h == null �̏ꍇ�A(*3)�ŋL���������t�̂R�c�Ɠ���i��n���j     
        if (l_request.productCode == null)
        {
            WEB3GentradeBizDate l_genBizDate = 
                new WEB3GentradeBizDate(new Timestamp(l_datOrderResultBizDate.getTime()));
            Timestamp l_timeThreeBizDate = l_genBizDate.roll(3);
            l_dblBuyPossiblePrice = 
                l_tpTradingPowerService.getOtherTradingPower(
                    l_subAccount, 
                    l_timeThreeBizDate);
        }
        //���N�G�X�g.�����R�[�h != null �̏ꍇ�A�������.get��n��()�̖߂�l 
        else
        {
            l_dblBuyPossiblePrice = 
                l_tpTradingPowerService.getOtherTradingPower(
                    l_subAccount, 
                    l_feqTradedProduct.getDailyDeliveryDate());
        }

        //get���̑����i���t�\�i�O�݁j
        WEB3FeqTradingPowerUnit[] l_feqTradingPowerUnits = null;
        if (l_request.productCode == null)
        {
            //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h
            //�O�����������F �i�ȉ��̂Ƃ���j
            //���N�G�X�g.�����R�[�h == null�̏ꍇ�Anull
            //���t�\�z�i�~�݁j�Fget���̑����i���t�\�z�i�j�̖߂�l
            l_feqTradingPowerUnits = this.getFeqOtherTradingPowerForeignUnit(
                l_subAccount.getInstitution().getInstitutionCode(),
                null,
                l_dblBuyPossiblePrice);
        }
        else
        {
            //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h
            //�O�����������F �i�ȉ��̂Ƃ���j
            //���N�G�X�g.�����R�[�h != null �̏ꍇ�A�O�����������I�u�W�F�N�g
            //���t�\�z�i�~�݁j�Fget���̑����i���t�\�z�i�j�̖߂�l
            l_feqTradingPowerUnits = this.getFeqOtherTradingPowerForeignUnit(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_feqProduct,
                l_dblBuyPossiblePrice);
        }

        //QA72
        //���t�\�z�i�O�݁j�̎擾���W�b�N
        List l_lisCurrencyRows = new ArrayList();
        //���t�\�z�i�O�݁j
        double l_dblCalcForeignCCYAmount = 0D;
                        
        //1.14 ���N�G�X�g.�����R�[�h != null is��������J��(
        //��n�� : Date, �⏕���� : �⏕����)
        //����������J�݂���Ă��邩�ǂ����𔻒肷��B 
        //[����] 
        //��n���F �O�������������.get��n��()�̖߂�l 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        
        //�����敪�ꗗ�F
        boolean l_blnIsSpecialAccountEstablished = false;
        if (l_request.productCode != null)
        {
            l_blnIsSpecialAccountEstablished = 
                l_mainAccount.isSpecialAccountEstablished(
                    l_feqTradedProduct.getDailyDeliveryDate(), 
                    l_subAccount);
        }

        //1.15 ���N�G�X�g.�����R�[�h == null �̏ꍇ  
        //      is��������J��(�⏕���� : �⏕����)
        else
        {
            l_blnIsSpecialAccountEstablished = 
                l_mainAccount.isSpecialAccountEstablished(l_subAccount);    
        }
        
        //1.16 ���X�|���X�f�[�^�𐶐�����B 
        WEB3FeqBuyInputResponse l_response = 
            (WEB3FeqBuyInputResponse)l_request.createResponse();
        
        //1.17(*4) �ȉ��̂Ƃ���A�v���p�e�B���Z�b�g����B
            
        //�����P���敪�ꗗ�F �h���s�h�Ɓh�w�l�h�̋敪�̔z��
        String[] l_strPriceDivs = 
            {WEB3OrderPriceDivDef.MARKET_PRICE, 
             WEB3OrderPriceDivDef.LIMIT_PRICE};
        l_response.orderPriceDivList = l_strPriceDivs;
        
        //���s�����ꗗ�F �戵�\���s�����擾()�̖߂�l
        l_response.execCondList = l_strHandlingPossibleExecConds;
        
        //���������敪�ꗗ�F �戵�\���������敪�擾()�̖߂�l
        l_response.expirationDateTypeList = 
            l_strHandlingPossibleExpirationDateTypes;
        
        //�L�������J�n���F get�o����܂Œ����J�n��()�̖߂�l
        l_response.expirationStartDate = l_datOrderUntilDeadLineStartDay;
        
        //�L�������I�����F get�o����܂Œ����ŏI��()�̖߂�l
        l_response.expirationEndDate = l_datOrderUntilDeadLineEndDay;
        
        //�L���������j���ꗗ�F get�����������j���ꗗ()�̖߂�l
        l_response.holidayList = l_datExpirationDateHolidays;
        
        //���������ꗗ�F �戵�\���������擾()�̖߂�l
        l_response.orderCondTypeList = l_strHandlingPossibleOrderConds;
        
        //����I���x���s��R�[�h�ꗗ�F get�s��ǌx���O���s��()�̖߂�l
        l_response.messageSuspension = l_strTradeCloseFeqMarkets;
        
        //���t�\�z�i�~�݁j�F get���̑����t�\�z()�̖߂�l
        l_response.tradingPowerYen = 
            WEB3StringTypeUtility.formatNumber(l_dblBuyPossiblePrice);

        //���t�\�z�i�O�݁j�Fget���̑����i���t�\�i�O�݁j�i�j�̖߂�l
        l_response.tradingPowerFrnList = l_feqTradingPowerUnits;

        //�����敪�ꗗ�F �i�ȉ��̂Ƃ���j
        //�ڋq.is��������J��() == true �̏ꍇ�A�h��ʁh�Ɓh����h�̋敪�̔z��
        // ����ȊO�̏ꍇ�A�h��ʁh�݂̂̔z��
        if (l_blnIsSpecialAccountEstablished)
        {
            String[] l_strTaxTypeList = 
                { WEB3TaxTypeSpecialDef.NORMAL, WEB3TaxTypeSpecialDef.SPECIAL };
            l_response.taxTypeList = l_strTaxTypeList;
        }
        else
        {
            String[] l_strTaxTypeList = { WEB3TaxTypeSpecialDef.NORMAL };
            l_response.taxTypeList = l_strTaxTypeList;
        }
            
        //���ϋ敪�ꗗ�F �i�ȉ��̂Ƃ���j
        //���t�\�z�i�O�݁j���擾�ł��Ă���ꍇ�A�h�~�݁h�Ɓh�O�݁h�̋敪�̔z��
        String[] l_strSettleDivList = null;
        if (l_dblCalcForeignCCYAmount > 0)
        {
            l_strSettleDivList = new String[2];
            l_strSettleDivList[0] = WEB3InputOutputActionSettlementDivDef.EN_SETTLE;
            l_strSettleDivList[1] = WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE;
            l_response.settleDivList = l_strSettleDivList;
        }
        else
        {
            //���t�\�z�i�O�݁j���擾�ł��ĂȂ� or ���ׂĂ̗v�f��0�̏ꍇ�A�h�~�݁h�݂̂̔z��
            if (l_dblCalcForeignCCYAmount == 0 || l_lisCurrencyRows.size() == 0)
            {
                l_strSettleDivList = new String[1];
                l_strSettleDivList[0] = WEB3InputOutputActionSettlementDivDef.EN_SETTLE;
                l_response.settleDivList = l_strSettleDivList;
            }
        }        

        //(*A)���N�G�X�g.�����R�[�h != null �̏ꍇ
        if (l_request.productCode != null)
        {
            //�����R�[�h�F �O����������.getProductCode()�̖߂�l
            l_response.productCode = l_strProductCode;
            
            //���n�����R�[�h�F �O����������.get���n�����R�[�h()�̖߂�l
            l_response.localProductCode = l_strOffshoreProductCode;
        
            //�������F �O����������.get�\��������()�̖߂�l
            l_response.productName =  l_strProductName;   
            
            //�s��R�[�h�F �O����������.get�s��R�[�h()�̖߂�l
            l_response.marketCode = l_strMarketCode;
            
            if (!l_blnfeqProductQuoteIsNull)
            {
                //�����擾�敪�F get�����擾�敪()�̖߂�l
                l_response.currentPriceGetDiv = l_strCurrentPriceGetDiv;
        
                //�����F get����()�̖߂�l
                l_response.currentPrice = l_strCurrentPrice;
        
                //�O����F get�O����()�̖߂�l
                l_response.comparedPreviousDay = l_strComparedPreviousDay;
        
                //������ԁF get�������\����()�̖߂�l
                l_response.currentPriceTime = l_DatCurrentPricePublicTime;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * ���t�����̊m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�O�����t�jvalidate�����v �Q�ƁB<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�O�����t�v(�O�����t�jvalidate����)<BR>
     * �@@�@@:  1.17 validate����]��<BR> 
     * �@@�@@�߂�l�̎���]�͌���.����t���O == false�̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3FeqBuyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AF4BA01AE
     */
    protected WEB3FeqBuyConfirmResponse validateOrder(WEB3FeqBuyConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqBuyConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1)validate( )
        l_request.validate();
        
        //1.2)get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();

        //1.3 get�㗝���͎�( )
        WEB3GentradeTrader l_trade = (WEB3GentradeTrader)this.getTrader();
        
        //1.4 getFeqProduct(arg0 : Institution, arg1 : �_���r���[::java::lang::String)
        //�O�����������I�u�W�F�N�g���擾����B 
        //[����] 
        //�،���ЁF �⏕����.getInstitution()�̖߂�l 
        //�����R�[�h�F ���N�G�X�g�����R�[�h 
        
        //�،����            
        WEB3GentradeInstitution l_institution = 
            (WEB3GentradeInstitution) l_subAccount.getInstitution();
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        WEB3FeqProduct l_feqProduct = null;
        try
        {
            l_feqProduct = 
                (WEB3FeqProduct) l_feqProductManager.getFeqProduct(
                    l_institution, 
                    l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�O�������������擾�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
        String l_strMarketCode = l_feqProduct.getMarketCode();
        
        //1.5 reset�s��R�[�h(�s��R�[�h : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        
        //1.6 get������()
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.7 get���s����(String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        FeqExecutionConditionType l_executionCond = 
            l_feqOrderManager.getExecutionCondition(l_request.execCondType);
        
        //1.8 create�V�K�������e
        //�V�K�������e�I�u�W�F�N�g�𐶐�����B 
        //[����] 
        //�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
        //���ҁF get�㗝���͎�()�̖߂�l 
        //is���t�����F true 
        //�����R�[�h�F �O����������.�����R�[�h 
        //�s��R�[�h�F�O����������.�s��R�[�h 
        //�������ʁF ���N�G�X�g.�������� 
        //�����P���F ���N�G�X�g.�����P�� 
        //���s�����F get���s����()�̖߂�l 
        //�����������F ���N�G�X�g.�����L������ 
        //�ŋ敪�F ���N�G�X�g.��������敪 
        //�ʉ݃R�[�h�F �O����������.�ʉ݃R�[�h 
        //���������F ���N�G�X�g.�������� 
        //�iW�w�l�j�����w�l�F ���N�G�X�g.W�w�l�p�����P�� 
        //���ϋ敪�F ���N�G�X�g.���ϋ敪 
        //���񒍕��̒����P��ID�F �i�ȉ��̂Ƃ���j 
        //   ���N�G�X�g.���������敪 == �h��������h �̏ꍇ�Anull 
        //   ���N�G�X�g.���������敪 == �h�o����܂Œ����h �̏ꍇ�A0 
        String l_stringInstitutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        Long l_lngFirstOrderUnitId = null;
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_lngFirstOrderUnitId = null;
        }
        else
        {
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
            {
                l_lngFirstOrderUnitId = new Long(0);
            }
        }
        
        //�����P��
        double l_dblLimitPrice = 0.0D;
        //W�w�l�p�����P��
        double l_dblWLimitPrice = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_request.limitPrice))
        {
            l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
        }
        if (!WEB3StringTypeUtility.isEmpty(l_request.wLimitPrice))
        {
            l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        
        //��������敪
        TaxTypeEnum l_taxTypeEnum = null;
        //��������敪���h0�F��ʌ����h �̏ꍇ
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;
        }
        else 
        {
            //��������敪���h1:��������h �̏ꍇ
            if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
            {
                l_taxTypeEnum = TaxTypeEnum.SPECIAL;
            }
        }
        WEB3FeqNewOrderSpec l_feqNewOrderSpec =
            WEB3FeqNewOrderSpec.createNewOrderSpec(
                l_stringInstitutionCode,
                l_trade,
                true,
                l_feqProduct.getProductCode(),
                l_strMarketCode,
                Double.parseDouble(l_request.orderQuantity),
                l_dblLimitPrice,
                l_executionCond,
                l_request.expirationDate,
                l_taxTypeEnum,
                l_feqProduct.getCurrencyCode(),
                l_request.orderCondType,
                l_dblWLimitPrice,
                l_request.settleDiv,
                l_lngFirstOrderUnitId);
               
        //1.9  validate�V�K����(SubAccount, ProductTypeEnum, NewOrderSpec)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�����^�C�v�F �h�O�������h 
        //�������e�F �O�������V�K�������e�I�u�W�F�N�g
        NewOrderValidationResult  l_newOrderValidationResult =
            l_feqOrderManager.validateNewOrder(
                l_subAccount,
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_feqNewOrderSpec);
       
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�������e�̃`�F�b�N���s�� Error " +
            l_newOrderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        
        //1.10 get�v�Z�p�����P��(�O�������������, String, double, double, boolean)
        WEB3GentradeBranch l_branch = 
            (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        //�O�������������
        WEB3FeqTradedProduct l_feqTradedProduct = 
            l_feqProduct.getFeqTradedProduct();
            
        double l_dblUnitPrice =
            l_feqOrderManager.getUnitPrice(
                l_feqTradedProduct,
                l_branch,
                l_request.orderPriceDiv,
                l_dblLimitPrice,
                l_dblWLimitPrice,
                true);
        
        //1.11 calc�������(double, double)
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        //�O�������v�Z�T�[�r�X
        WEB3FeqBizLogicProvider l_feqBizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblCalcExecutionAmount =
            l_feqBizLogicProvider.calcExecutionAmount(
                Double.parseDouble(l_request.orderQuantity), 
                l_dblUnitPrice);
        
        //1.12 get�s��( )
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.13 get�ʉ�( )
        WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
        String l_strCurrencyCode = l_feqProduct.getCurrencyCode();    
        
        //calc�������(double, double)�̌��ʂ��A���Y�ʉ݂̏����_�����Ŋۂ߂�
        BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblCalcExecutionAmount);
        int l_intDecimalPlace = l_currency.getScale();
        l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_dblCalcExecutionAmount = l_bdTradePriceFc.doubleValue();
        log.debug("��������̊ۂ߂����� �� "+ l_dblCalcExecutionAmount);
        
        //1.14 get���t��בփ��[�g( )
        double l_dblBuyBaseFxRate = l_currency.getBuyBaseRate();
        
        //1.15 calc�O���������z(�⏕����, �O����������, �s��, Date, 
        //      double, double, boolean, boolean, boolean)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�O�����������F getFeqProduct()�̖߂�l 
        //�s��F get�s��()�̖߂�l 
        //����F get������()�̖߂�l 
        //��������i�O�݁j�F calc�������()�̖߂�l 
        //�בփ��[�g�F get���t��בփ��[�g()�̖߂�l 
        //is���t�F true 
        //is���v�Z�F false 
        //is�w�l�F �i�ȉ��̂Ƃ���j 
        //   ���N�G�X�g.�����P���敪 == �h�w�l�h �̏ꍇ�Atrue 
        //   ���N�G�X�g.�����P���敪 == �h���s�h �̏ꍇ�Afalse 
        //�����`���l���F�@@this.get���O�C���`���l��()
        
        //is�w�l
        boolean l_blnOrderPrice = true;
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_blnOrderPrice = false;
        }
        else
        {
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.orderPriceDiv))
            {
                l_blnOrderPrice = true;
            }
        }
        WEB3FeqAmountCalcResult l_feqAmountCalcResult =
            l_feqBizLogicProvider.calcFeqAmount(
                l_subAccount,
                l_feqProduct,
                l_market,
                l_datOrderBizDate,
                l_datOrderBizDate,
                l_dblCalcExecutionAmount,
                l_dblBuyBaseFxRate,
                true,
                false,
                l_blnOrderPrice,
                this.getLoginChannel());

        //1.16 �O�����������X�V�C�x���g�C���^�Z�v�^
        //�����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
        //[����] 
        //�������e�F �O�������V�K�������e�I�u�W�F�N�g 
        //�v�Z���ʁF calc�O���������z()�̖߂�l 
        //�v�Z�P���F get�v�Z�p�����P��()�̖߂�l 
        //���������F ���N�G�X�g.�������� 
        //�����������Z�q�F �i�ȉ��̂Ƃ���j 
        //���N�G�X�g.�������� == �h�t�w�l�h �̏ꍇ�A���N�G�X�g.�t�w�l�p�����������Z�q 
        //���N�G�X�g.�������� == �hW�w�l�h �̏ꍇ�A���N�G�X�g.W�w�l�p�����������Z�q 
        //���������P���F �i�ȉ��̂Ƃ���j 
        //���N�G�X�g.�������� == �h�t�w�l�h �̏ꍇ�A���N�G�X�g.�t�w�l�p���������P�� 
        //���N�G�X�g.�������� == �hW�w�l�h �̏ꍇ�A���N�G�X�g.W�w�l�p���������P��     
        
        //�����������Z�q
        String l_strOrderCondOperator = null;
        //���������P��
        double l_dblOrderCondPrice = 0.0D;
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = l_request.stopOrderCondOperator;
            l_dblOrderCondPrice = 
                Double.parseDouble(l_request.stopOrderCondPrice);
        }
        else
        {
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblOrderCondPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
        }
        WEB3FeqOrderUpdateInterceptor l_feqOrderUpdateInterceptor = 
            new WEB3FeqOrderUpdateInterceptor(
                l_feqNewOrderSpec,
                l_feqAmountCalcResult,
                l_dblUnitPrice,
                l_request.orderCondType,
                l_strOrderCondOperator,
                l_dblOrderCondPrice);
      
        //1.17 validate����]��
        //����]�͂̍X�V���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������e�C���^�Z�v�^�F �O�����������X�V�C���^�Z�v�^��v�f�Ƃ����z�� 
        //�������e�F �O�������V�K�������e��v�f�Ƃ����z�� 
        //������ʁF �h�O�����h 
        //�]�͍X�V�t���O�F false 
        
        //����]�̓T�[�r�X�C���^�t�F�[�X
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        //�O�����������X�V�C���^�Z�v�^��v�f�Ƃ����z�� 
        WEB3FeqOrderUpdateInterceptor[] l_feqOrderUpdateInterceptors = 
            {l_feqOrderUpdateInterceptor};
            
        //�O�������V�K�������e��v�f�Ƃ����z�� 
        WEB3FeqNewOrderSpec[] l_feqNewOrderSpecs = {l_feqNewOrderSpec}; 
                
        WEB3TPTradingPowerResult l_tPTradingPowerResult = 
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount, 
                l_feqOrderUpdateInterceptors, 
                l_feqNewOrderSpecs, 
                OrderTypeEnum.FEQ_BUY, 
                false);
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
        
        //1.18 get�s��ǌx���O���s��(���X : ���X)
        String[] l_strTradeCloseFeqMarkets =
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(l_branch);
        
        //1.19 createNewOrderId( )
        //�V�K�̒���ID���擾����B
        long l_lngNewOrderId = l_feqOrderManager.createNewOrderId();
                
        //1.20  createResponse( )
        WEB3FeqBuyConfirmResponse l_response = 
            (WEB3FeqBuyConfirmResponse)l_request.createResponse();
                    
        //1.21 (*) �v���p�e�B�Z�b�g
        //(*) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //�����R�[�h�F �O����������.getProductCode()�̖߂�l
        l_response.productCode = l_feqProduct.getProductCode();
        
        //���n�����R�[�h�F �O����������.get���n�����R�[�h()�̖߂�l
        l_response.localProductCode= l_feqProduct.getOffshoreProductCode();
        
        //�������F �O����������.get�\��������()�̖߂�l
        l_response.productName = l_feqProduct.getDisplayProductName();
        
        //�s��R�[�h�F�O����������.get�s��R�[�h()�̖߂�l
        l_response.marketCode = l_strMarketCode;
        
        //���t��בցF�ʉ�.get���t��בփ��[�g()�̖߂�l
        l_response.buyExchange = 
            WEB3StringTypeUtility.formatNumber(l_dblBuyBaseFxRate);

        //�ʉ݃R�[�h�F �O����������.get�ʉ݃R�[�h()�̖߂�l
        l_response.currencyCode = l_strCurrencyCode;
        
        //����ID�F createNewOrderId()�̖߂�l
        l_response.orderId = l_lngNewOrderId + "";
        
        //�m�F���P���F get�v�Z�p�����P��()�̖߂�l
        l_response.checkPrice = 
            WEB3StringTypeUtility.formatNumber(l_dblUnitPrice);
        
        //�m�F���������F get������()�̖߂�l
        l_response.checkDate = l_datOrderBizDate;
        
        //����I���x���s��R�[�h�ꗗ�Fget�s��ǌx���s��()�̖߂�l
        l_response.messageSuspension = l_strTradeCloseFeqMarkets;
           
        //(*1) ���N�G�X�g.���ϋ敪 == �h�~�݁h�̏ꍇ�A�ݒ肷��l�͉~�݊��Z���ꂽ���́B
        if (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(l_request.settleDiv))
        {
            //�T�Z��n����F �O���������z�v�Z����.��n���(*1)
            l_response.estimatedPrice = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getNetAmount());
        
            //���n���Z����F �O���������z�v�Z����.���n���Z���(*1)
            l_response.localClearUpPrice = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getBalanceAmount());
                
            //�萔���F �O���������z�v�Z����.�ϑ��萔��(*1)
            l_response.commission = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getCommissionFee());
            
            //�萔������ŁF �O���������z�v�Z����.�ϑ��萔�������(*1)
            l_response.commissionConsumptionTax = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqAmountCalcResult.getCommisionFeeTax());
        }
        else
        {
            //���N�G�X�g.���ϋ敪 == �h�O�݁h�̏ꍇ�A�ݒ肷��l�͊O�݊��Z���ꂽ���́B
            if (WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(l_request.settleDiv))
            {
                //�T�Z��n����F �O���������z�v�Z����.��n���(�O��)
                l_response.estimatedPrice = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqAmountCalcResult.getNetAmountFc());
        
                //���n���Z����F �O���������z�v�Z����.���n���Z���
                l_response.localClearUpPrice = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqAmountCalcResult.getBalanceAmountFc());
                
                //�萔���F �O���������z�v�Z����.�ϑ��萔��(�O��)
                l_response.commission = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqAmountCalcResult.getCommissionFeeFc());
            
                //�萔������ŁF �O���������z�v�Z����.�ϑ��萔�������(�O��)
                l_response.commissionConsumptionTax = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqAmountCalcResult.getCommisionFeeTaxFc());
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * ���t�����̓o�^�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�O�����t�jsubmit�����v �Q�ƁB<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�O�����t�v(�O�����t�jsubmit����)<BR>
     * �@@�@@:  1.16 validate����]��<BR> 
     * �@@�@@�߂�l�̎���]�͌���.����t���O == false�̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3FeqBuyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AF4BA01CD
     */
    protected WEB3FeqBuyCompleteResponse submitOrder(WEB3FeqBuyCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqBuyCompleteRequest l_request)";
            
        //1.1) validate( )
        l_request.validate();
        
        //1.2)get�⏕����()
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();

        //�،���ЃR�[�h
        WEB3GentradeInstitution l_institution = 
            (WEB3GentradeInstitution) l_subAccount.getInstitution();
        
        //1.3 get�㗝���͎�( )
        WEB3GentradeTrader l_trade = (WEB3GentradeTrader) this.getTrader();
        
        //1.4 getFeqProduct(arg0 : Institution, arg1 : �_���r���[::java::lang::String)
        //�O�����������I�u�W�F�N�g���擾����B 
        //[����] 
        //�،���ЁF �⏕����.getInstitution()�̖߂�l 
        //�����R�[�h�F ���N�G�X�g�����R�[�h 
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        WEB3FeqProduct l_feqProduct = null;
        try
        {
            l_feqProduct = 
                (WEB3FeqProduct) l_feqProductManager.getFeqProduct(
                    l_institution, 
                    l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�O�������������擾�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                  
        String l_strMarketCode = l_feqProduct.getMarketCode();
        
        //1.5 get������(�m�F�������� : Date)
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.6 get���s����(String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        FeqExecutionConditionType l_executionCond = 
            l_feqOrderManager.getExecutionCondition(l_request.execCondType);
        
        //1.7 create�V�K�������e
        //�V�K�������e�I�u�W�F�N�g�𐶐�����B 
        //[����] 
        //�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
        //���ҁF get�㗝���͎�()�̖߂�l 
        //is���t�����F true 
        //�����R�[�h�F �O����������.�����R�[�h 
        //�s��R�[�h�F�O����������.�s��R�[�h 
        //�������ʁF ���N�G�X�g.�������� 
        //�����P���F ���N�G�X�g.�����P�� 
        //���s�����F get���s����()�̖߂�l 
        //�����������F ���N�G�X�g.�����L������ 
        //�ŋ敪�F ���N�G�X�g.��������敪 
        //�ʉ݃R�[�h�F �O����������.�ʉ݃R�[�h 
        //���������F ���N�G�X�g.�������� 
        //�iW�w�l�j�����w�l�F ���N�G�X�g.W�w�l�p�����P�� 
        //���ϋ敪�F ���N�G�X�g.���ϋ敪 
        //���񒍕��̒����P��ID�F �i�ȉ��̂Ƃ���j 
        //   ���N�G�X�g.���������敪 == �h��������h �̏ꍇ�Anull 
        //   ���N�G�X�g.���������敪 == �h�o����܂Œ����h �̏ꍇ�A0 
        String l_stringInstitutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        Long l_lngFirstOrderUnitId = null;
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_lngFirstOrderUnitId = null;
        }
        else
        {
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
            {
                l_lngFirstOrderUnitId = new Long(0);
            }
        }
        //�����P��
        double l_dblLimitPrice = 0.0D;
        //W�w�l�p�����P��
        double l_dblWLimitPrice = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_request.limitPrice))
        {
            l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
        }
        if (!WEB3StringTypeUtility.isEmpty(l_request.wLimitPrice))
        {
            l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        
        //��������敪
        TaxTypeEnum l_taxTypeEnum = null;
        //��������敪���h0�F��ʌ����h �̏ꍇ
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;
        }
        else 
        {
            //��������敪���h1:��������h �̏ꍇ
            if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
            {
                l_taxTypeEnum = TaxTypeEnum.SPECIAL;
            }
        }
        WEB3FeqNewOrderSpec l_feqNewOrderSpec =
            WEB3FeqNewOrderSpec.createNewOrderSpec(
                l_stringInstitutionCode,
                l_trade,
                true,
                l_feqProduct.getProductCode(),
                l_strMarketCode,
                Double.parseDouble(l_request.orderQuantity),
                l_dblLimitPrice,
                l_executionCond,
                l_request.expirationDate,
                l_taxTypeEnum,
                l_feqProduct.getCurrencyCode(),
                l_request.orderCondType,
                l_dblWLimitPrice,
                l_request.settleDiv,
                l_lngFirstOrderUnitId);

        //1.8  validate�V�K����(SubAccount, ProductTypeEnum, NewOrderSpec)
        NewOrderValidationResult  l_newOrderValidationResult =
            l_feqOrderManager.validateNewOrder(
                l_subAccount,
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_feqNewOrderSpec);
       
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�������e�̃`�F�b�N���s�� Error " +
            l_newOrderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        
        //1.9 calc�������(double, double)
        
        //�m�F���P��
        double l_dblCheckPrice = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_request.checkPrice))
        {
            l_dblCheckPrice = Double.parseDouble(l_request.checkPrice);
        }
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_feqBizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblCalcExecutionAmount =
            l_feqBizLogicProvider.calcExecutionAmount(
                Double.parseDouble(l_request.orderQuantity), 
                l_dblCheckPrice);
        
        //1.10 get�s��( )
        WEB3GentradeMarket l_market = l_feqProduct.getMarket();
        
        //1.11 get�ʉ�( )
        WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
        
        //calc�������(double, double)�̌��ʂ��A���Y�ʉ݂̏����_�����Ŋۂ߂�
        BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblCalcExecutionAmount);
        int l_intDecimalPlace = l_currency.getScale();
        l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_dblCalcExecutionAmount = l_bdTradePriceFc.doubleValue();
        log.debug("��������̊ۂ߂����� �� "+ l_dblCalcExecutionAmount);
        
        //1.12 get���t��בփ��[�g( )
        double l_dblBuyBaseFxRate = l_currency.getBuyBaseRate();
        
        //1.13 calc�O���������z(�⏕����, �O����������, �s��, Date, double, 
                //double, boolean, boolean, boolean)
        //�e����z�̌v�Z���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�O�����������F getFeqProduct()�̖߂�l 
        //�s��F get�s��()�̖߂�l 
        //����F get������()�̖߂�l 
        //��������i�O�݁j�F calc�������()�̖߂�l 
        //�בփ��[�g�F get���t��בփ��[�g()�̖߂�l 
        //is���t�F true 
        //is���v�Z�F false 
        //is�w�l�F �i�ȉ��̂Ƃ���j 
        //   ���N�G�X�g.�����P���敪 == �h�w�l�h �̏ꍇ�Atrue 
        //   ���N�G�X�g.�����P���敪 == �h���s�h �̏ꍇ�Afalse 
        //�����`���l���F�@@this.get���O�C���`���l��()
        
        //is�w�l
        boolean l_blnOrderPrice = true;
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_blnOrderPrice = false;
        }
        else
        {
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.orderPriceDiv))
            {
                l_blnOrderPrice = true;
            }
        }
        WEB3FeqAmountCalcResult l_feqAmountCalcResult =
            l_feqBizLogicProvider.calcFeqAmount(
                l_subAccount,
                l_feqProduct,
                l_market,
                l_datOrderBizDate,
                l_datOrderBizDate,
                l_dblCalcExecutionAmount,
                l_dblBuyBaseFxRate,
                true,
                false,
                l_blnOrderPrice, 
                this.getLoginChannel());
        
        //1.14 �O�����������X�V�C�x���g�C���^�Z�v�^
        //�����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
        //[����] 
        //�������e�F �O�������V�K�������e�I�u�W�F�N�g 
        //�v�Z���ʁF calc�O���������z()�̖߂�l 
        //�v�Z�P���F ���N�G�X�g.�m�F���P�� 
        //���������F ���N�G�X�g.�������� 
        //�����������Z�q�F �i�ȉ��̂Ƃ���j 
        //���N�G�X�g.�������� == �h�t�w�l�h �̏ꍇ�A���N�G�X�g.�t�w�l�p�����������Z�q 
        //���N�G�X�g.�������� == �hW�w�l�h �̏ꍇ�A���N�G�X�g.W�w�l�p�����������Z�q 
        //���������P���F �i�ȉ��̂Ƃ���j 
        //���N�G�X�g.�������� == �h�t�w�l�h �̏ꍇ�A���N�G�X�g.�t�w�l�p���������P�� 
        //���N�G�X�g.�������� == �hW�w�l�h �̏ꍇ�A���N�G�X�g.W�w�l�p���������P�� 
        
        //�����������Z�q
        String l_strOrderCondOperator = null;
        //���������P��
        double l_dblOrderCondPrice = 0D;
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = l_request.stopOrderCondOperator;
            l_dblOrderCondPrice = 
                Double.parseDouble(l_request.stopOrderCondPrice);
        }
        else
        {
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblOrderCondPrice = 
                    Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
        }
        WEB3FeqOrderUpdateInterceptor l_feqOrderUpdateInterceptor = 
            new WEB3FeqOrderUpdateInterceptor(
                l_feqNewOrderSpec,
                l_feqAmountCalcResult,
                l_dblCheckPrice,
                l_request.orderCondType,
                l_strOrderCondOperator,
                l_dblOrderCondPrice);
        
        //1.15 validate����]��
        //����]�͂̍X�V���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������e�C���^�Z�v�^�F �O�����������X�V�C���^�Z�v�^��v�f�Ƃ����z�� 
        //�������e�F �O�������V�K�������e��v�f�Ƃ����z�� 
        //������ʁF �h�O�����h 
        //�]�͍X�V�t���O�F true 
        
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        //�O�����������X�V�C���^�Z�v�^��v�f�Ƃ����z�� 
        WEB3FeqOrderUpdateInterceptor[] l_feqOrderUpdateInterceptors = 
            {l_feqOrderUpdateInterceptor};
        //�������e�̔z��
        WEB3FeqNewOrderSpec[] l_feqNewOrderSpecs = {l_feqNewOrderSpec}; 
        WEB3TPTradingPowerResult l_tPTradingPowerResult = 
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount, 
                l_feqOrderUpdateInterceptors, 
                l_feqNewOrderSpecs, 
                OrderTypeEnum.FEQ_BUY, 
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

        
        //1.16 setThreadLocalPersistenceEventInterceptor(
            //arg0 : FeqOrderManagerPersistenceEventInterceptor)
        l_feqOrderManager.setThreadLocalPersistenceEventInterceptor(l_feqOrderUpdateInterceptor);     
        
        //1.17 submitNewOrder
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        //�����^�C�v�F �h�O�������h 
        //�������e�F �O�������������e�I�u�W�F�N�g 
        //����ID�F ���N�G�X�g.����ID 
        //�p�X���[�h�F ���N�G�X�g.�Ïؔԍ� 
        //isSkip�����R���F true
        long l_lngOrderId = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.orderId))
        {
            l_lngOrderId =  Long.parseLong(l_request.orderId);
        } 
        OrderSubmissionResult l_submitNewOrderResult =
            l_feqOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_feqNewOrderSpec,
                l_lngOrderId,
                l_request.password,
                true);
            
        if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitNewOrder" +
            l_submitNewOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  

        //1.18 getOrder(����ID : long)
        FeqOrderRow l_orderRow = null;
        try
        {
            Order l_order = l_feqOrderManager.getOrder(l_lngOrderId);
            l_orderRow = (FeqOrderRow) l_order.getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.19  createResponse( )
        WEB3FeqBuyCompleteResponse l_response = 
            (WEB3FeqBuyCompleteResponse)l_request.createResponse();
        
        //1.20 (*) �v���p�e�B�Z�b�g
        //(*) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //�X�V���ԁF ����.�X�V����
        l_response.lastUpdatedTimestamp = l_orderRow.getLastUpdatedTimestamp();
        //����ID�F���N�G�X�g.����ID
        l_response.orderId = l_request.orderId;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����I�����)<BR>
     * �����I����ʂ̕\���ɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�O�����t�jget�����I����ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3FeqBuyProductSelectResponse
     * @@throws WEB3BaseException
     */
    protected WEB3FeqBuyProductSelectResponse getProductSelectScreen(
        WEB3FeqBuyProductSelectRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "getProductSelectScreen(WEB3FeqBuyProductSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount)this.getSubAccount();
        
        WEB3FeqOrderManager l_feqOrderManager =
            (WEB3FeqOrderManager)GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        l_feqOrderManager.validateOrder(l_subAccount);
        
        String[] l_strMessageSuspension =
            WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(
                l_subAccount.getWeb3GenBranch());
        
        WEB3FeqBuyProductSelectResponse l_response =
            (WEB3FeqBuyProductSelectResponse)l_request.createResponse();
        
        l_response.messageSuspension = l_strMessageSuspension;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���̑����i���t�\�i�O�݁j)<BR>
     * ���t�\�z�i�O�݁j�ꗗ���擾����B<BR>
     * <BR>
     * �P�j�@@�O�����������I�u�W�F�N�g != null�̏ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�@@�P�|�P�j�@@�ʉ݃I�u�W�F�N�g�擾<BR>
     * �@@�@@�@@�@@�@@�@@�@@�O����������.get�ʉ�()�ɂĒʉ݃I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�P�|�Q�j�@@���t�\�z�i�O�݁j�̊e�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@���t�\�z�i�O�݁j.�ʉ݃R�[�h�@@=�@@�O����������.get�ʉ݃R�[�h()�̖߂�l�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@���t�\�z�i�O�݁j.���t�\�z�@@=�@@�O�������v�Z�T�[�r�X.calc�O�݊��Z�i�j�̖߂�l�B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[calc�O�݊��Z�i�j�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���z�i�~�݁j�F�@@����.���t�\�z�i�~�݁j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���[�g�F�@@get�ʉ�().get���t��בփ��[�g ()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����������Fget�ʉ�().get����������()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�O�݊��Z�ۂߕ����Fget�ʉ�().get�O�݊��Z�ۂߕ���()�̖߂�l<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@��calc�O�݊��Z�i�j�̖߂�l��double�ł��B<BR>
     * <BR>
     * �Q�j�@@�O�����������I�u�W�F�N�g == null�̏ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�@@�Q�|�P�j�@@�O�������v���_�N�g�}�l�[�W��.get�ʉ݁i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[get�ʉ݁i�j�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ʉ݃I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�Q�|�Q�j�@@�擾�����ʉ݃I�u�W�F�N�g�̗v�f����Loop���A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���t�\�z�i�O�݁j�̊e�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@���t�\�z�i�O�݁j.�ʉ݃R�[�h�@@=�@@get�ʉ�().get�ʉ݃R�[�h()�̖߂�l�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@���t�\�z�i�O�݁j.���t�\�z�@@=�@@�O�������v�Z�T�[�r�X.calc�O�݊��Z�i�j�̖߂�l�B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[calc�O�݊��Z�i�j�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���z�i�~�݁j�F�@@����.���t�\�z�i�~�݁j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���[�g�F�@@get�ʉ�().get���t��בփ��[�g ()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����������Fget�ʉ�().get����������()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�O�݊��Z�ۂߕ����Fget�ʉ�().get�O�݊��Z�ۂߕ���()�̖߂�l<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��calc�O�݊��Z�i�j�̖߂�l��double�ł��B<BR>
     * <BR>
     * �R�j�@@���t�\�z�i�O�݁j�̔z���ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * �،���ЃR�[�h
     * @@param l_web3FeqProduct - �O����������<BR>
     * �O����������
     * @@param l_dbTradingPowerYen - ���t�\�z�i�~�݁j<BR>
     * ���t�\�z�i�~�݁j
     * @@return WEB3FeqTradingPowerUnit[]
     */
    protected WEB3FeqTradingPowerUnit[] getFeqOtherTradingPowerForeignUnit(
        String l_strInstitutionCode, WEB3FeqProduct l_web3FeqProduct, double l_dblTradingPowerYen)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getFeqOtherTradingPowerForeignUnit(String, WEB3FeqProduct, double)";
        log.entering(STR_METHOD_NAME);
        List l_lisFeqTradingPowerUnit = new ArrayList();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        //�O�����������I�u�W�F�N�g != null�̏ꍇ�A�ȉ��̏������s���B 
        if (l_web3FeqProduct != null)
        {
            //�ʉ݃R�[�h�擾
            //�O����������.get�ʉ݃R�[�h()�ɂĒʉ݃R�[�h���擾����B
            String l_strCurrencyCode = l_web3FeqProduct.getCurrencyCode();
            //�ʉ݃I�u�W�F�N�g�擾
            //�O����������.get�ʉ�()�ɂĒʉ݃I�u�W�F�N�g���擾����B
            WEB3GentradeCurrency l_currency = l_web3FeqProduct.getCurrency();

            //���t�\�z�i�O�݁j�̊e�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
            //���t�\�z�i�O�݁j.�ʉ݃R�[�h�@@=�@@�O����������.get�ʉ݃R�[�h()�̖߂�l�B
            //���t�\�z�i�O�݁j.���t�\�z�@@=�@@�O�������v�Z�T�[�r�X.calc�O�݊��Z�i�j�̖߂�l�B
            //[calc�O�݊��Z�i�j�Ɏw�肷�����] 
            //���z�i�~�݁j�F�@@����.���t�\�z�i�~�݁j 
            //���[�g�F�@@get�ʉ�().get���t��בփ��[�g ()�̖߂�l
            //�����������Fget�ʉ�().get����������()�̖߂�l
            //�O�݊��Z�ۂߕ����Fget�ʉ�().get�O�݊��Z�ۂߕ���()�̖߂�l
            //��calc�O�݊��Z�i�j�̖߂�l��double�ł��B
            WEB3FeqTradingPowerUnit l_feqTradingPowerUnit = new WEB3FeqTradingPowerUnit();
            l_feqTradingPowerUnit.currencyCode = l_strCurrencyCode;
            double l_dblForeignCCYAmount =
                l_feqBizLogicProvider.calcForeignCCYAmount(
                    l_dblTradingPowerYen,
                    l_currency.getBuyBaseRate(),
                    l_currency.getScale(),
                    l_currency.getChangeFCcyRoundDiv());
            l_feqTradingPowerUnit.tradingPower = WEB3StringTypeUtility.formatNumber(l_dblForeignCCYAmount);
            l_lisFeqTradingPowerUnit.add(l_feqTradingPowerUnit);
        }
        else
        {
            //�O�����������I�u�W�F�N�g == null�̏ꍇ�A�ȉ��̏������s���B 
            //�O�������v���_�N�g�}�l�[�W��.get�ʉ݁i�j
            //[get�ʉ݁i�j�Ɏw�肷�����]
            //�،���ЃR�[�h�F�@@����.�،���ЃR�[�h
            WEB3FeqProductManager l_productMgr = (WEB3FeqProductManager)l_tradingModule.getProductManager();
            WEB3GentradeCurrency[] l_gentradeCurrencys = l_productMgr.getCurrency(l_strInstitutionCode);

            //�ʉ݃I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�Anull��ԋp����B
            if (l_gentradeCurrencys == null)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //���t�\�z�i�O�݁j�̊e�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
            //���t�\�z�i�O�݁j.�ʉ݃R�[�h�@@=�@@get�ʉ�().get�ʉ݃R�[�h()�̖߂�l�B
            //���t�\�z�i�O�݁j.���t�\�z�@@=�@@�O�������v�Z�T�[�r�X.calc�O�݊��Z�i�j�̖߂�l�B
            //[calc�O�݊��Z�i�j�Ɏw�肷�����]
            //���z�i�~�݁j�F�@@����.���t�\�z�i�~�݁j
            //���[�g�F�@@get�ʉ�().get���t��בփ��[�g ()�̖߂�l
            //�����������Fget�ʉ�().get����������()�̖߂�l
            //�O�݊��Z�ۂߕ����Fget�ʉ�().get�O�݊��Z�ۂߕ���()�̖߂�l
            //��calc�O�݊��Z�i�j�̖߂�l��double�ł��B
            for (int i = 0; i < l_gentradeCurrencys.length; i++)
            {
                WEB3FeqTradingPowerUnit l_feqTradingPowerUnit = new WEB3FeqTradingPowerUnit();
                l_feqTradingPowerUnit.currencyCode = l_gentradeCurrencys[i].getCurrencyCode();
                double l_dblForeignCCYAmount =
                    l_feqBizLogicProvider.calcForeignCCYAmount(
                        l_dblTradingPowerYen,
                        l_gentradeCurrencys[i].getBuyBaseRate(),
                        l_gentradeCurrencys[i].getScale(),
                        l_gentradeCurrencys[i].getChangeFCcyRoundDiv());
                l_feqTradingPowerUnit.tradingPower = WEB3StringTypeUtility.formatNumber(l_dblForeignCCYAmount);
                l_lisFeqTradingPowerUnit.add(l_feqTradingPowerUnit);
            }
        }

        //���t�\�z�i�O�݁j�̔z���ԋp����B
        WEB3FeqTradingPowerUnit[] l_feqTradingPowerUnits =
            new WEB3FeqTradingPowerUnit[l_lisFeqTradingPowerUnit.size()];
        if (l_lisFeqTradingPowerUnit.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            l_lisFeqTradingPowerUnit.toArray(l_feqTradingPowerUnits);
        }

        log.exiting(STR_METHOD_NAME);
    	return l_feqTradingPowerUnits;
    }
}
@
