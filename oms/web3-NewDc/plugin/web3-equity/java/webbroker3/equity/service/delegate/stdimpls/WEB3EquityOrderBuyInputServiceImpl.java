head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderBuyInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������t���̓T�[�r�XImpl(WEB3EquityOrderBuyInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/10 �X��   (SRA) �V�K�쐬
                   2006/07/04 ���� (���u) �d�l�ύX�Ǘ�No.937
                   2006/11/06 �����F(���u) ���f�� 986
                   2006/12/25 �đo�g(���u) ���f��No.1088�A1089
Revesion History : 2007/12/17 ��іQ(���u) ���f��NO.1211,1212,1225,1264,1272
Revesion History : 2008/01/15 ��іQ(���u) ���f��NO.1283
Revesion History : 2008/01/16 ��іQ(���u) ���f��NO.1288,1292
Revesion History : 2008/02/28 ��іQ(���u) ���f��NO.1305
Revesion History : 2008/04/16 �����Q(���u) ���f��No.1313,1316
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.define.WEB3EquityProductIdDef;
import webbroker3.equity.message.WEB3EquityBuyInputRequest;
import webbroker3.equity.message.WEB3EquityBuyInputResponse;
import webbroker3.equity.message.WEB3EquityProductSelectRequest;
import webbroker3.equity.message.WEB3EquityProductSelectResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderBuyInputService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * �i�����������t�������̓T�[�r�XImpl�j�B<BR>
 * <BR>
 * �����������t�������̓T�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3EquityOrderBuyInputServiceImpl
    extends WEB3EquityClientRequestService 
    implements WEB3EquityOrderBuyInputService
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityOrderBuyInputServiceImpl.class);
    
    /**
     * (�R���X�g���N�^)�B<BR>
     * @@roseuid 409A008A0399
     */
    public WEB3EquityOrderBuyInputServiceImpl()
    {
    }


    /**
     * (get�����I�����)�B<BR>
     * <BR>
     * ���������̖����I����ʕ\���T�[�r�X�����{����B <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���t�������́jget�����I����ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3EquityProductSelectResponse<BR>
     * @@throws WEB3BaseException
     */
    protected WEB3EquityProductSelectResponse getProductSelectScreen(WEB3EquityProductSelectRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductSelectScreen(WEB3EquityProductSelectRequest)";
        log.entering(STR_METHOD_NAME);

        //get�⏕����
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        // isPTS�����J��(�ڋq)
        //�ڋq�F �⏕����.get�ڋq()
        boolean l_blnIsPTSAccountOpen =
            this.isPTSAccountOpen(l_mainAccount);

        //������ԊǗ�.validate������t�X�e�C�^�X()
        WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();

        //validate����\�ڋq(�ڋq, boolean, String)
        //�ڋq�F�@@�⏕����.getMainAccount()
        //isPTS�����J�݁F isPTS�����J��()�̖߂�l
        //�s��R�[�h�F null
        OrderValidationResult l_validationResult =
            this.validateAccountForTrading(l_mainAccount, l_blnIsPTSAccountOpen, null);
        if (l_validationResult.getProcessingResult().isFailedResult() )
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //get�s��ǌx���s��(���X, boolean)
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        String[] l_strTradeCloseMarkets = this.getTradeCloseMarket(
            l_branch,
            l_blnIsPTSAccountOpen);

        //get�戵�\�s��(���X, boolean)
        String[] l_strMarketList = this.getHandlingPossibleMarket(l_branch, l_blnIsPTSAccountOpen);
        //0�����̓G���[
        if (l_strMarketList == null || l_strMarketList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00643,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //validate�ڋq�����ʎ����~(�⏕����, long, OrderTypeEnum)
        //�⏕�����F�@@this.get�⏕����()�̖߂�l
        //����ID�F�@@�h0�h�i�S�����j
        //isPTS�����J�݁F�@@�ڋq.isPTS�����J��()�̖߂�l
        //�s��R�[�h�F null
        this.validateAccountProductOrderStop(
            l_subAccount, WEB3EquityProductIdDef.ALL_PRODUCTS, l_blnIsPTSAccountOpen, null);

        //createResponse
        WEB3EquityProductSelectResponse l_response
            = (WEB3EquityProductSelectResponse) l_request.createResponse();
        l_response.marketList = l_strMarketList;
        l_response.messageSuspension = l_strTradeCloseMarkets;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }


    /**
     * (execute)�B <BR>
     * <BR>
     * �����������t�������̓T�[�r�X���������{����B  <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Aget�����I�����()�܂��́A<BR>
     * get�V�K�����͉��()���\�b�h�̂����ꂩ���R�[������B<BR>
     * <BR>

     * @@param l_request - ���N�G�X�g�f�[�^ <BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406285B601EF
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3EquityProductSelectRequest)
        {
            l_response = this.getProductSelectScreen((WEB3EquityProductSelectRequest) l_request);
        }
        else if (l_request instanceof WEB3EquityBuyInputRequest)
        {
            l_response = this.getBuyInputScreen((WEB3EquityBuyInputRequest) l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }


    /**
     * (get���͉��)�B <BR>
     * <BR>
     * �����������t�������͉�ʕ\�����������{����B <BR>
     * <BR>
     * �V�[�P���X�}�u�i���t�������́j���͉�ʕ\���f�[�^�擾�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^ <BR>
     * @@return webbroker3.equity.message.WEB3EquityBuyInputResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406285B601EF
     */
    protected WEB3EquityBuyInputResponse getBuyInputScreen(
        WEB3EquityBuyInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getBuyInputScreen(WEB3EquityBuyInputRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule
            = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager
            = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        WEB3GentradeFinObjectManager l_finObjectManager
            = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        l_request.validate();

        //get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        log.debug("get�⏕�����i�⏕����ID=[" + l_subAccount.getSubAccountId() + "]�j");

        //get�s��R�[�h(String, String, String)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h
        //�s��R�[�h�F�@@���N�G�X�g�f�[�^.�s��R�[�h
        //�،���ЃR�[�h�F�@@�⏕����.�،����
        l_request.marketCode =
            this.getMarketCode(
                l_request.productCode,
                l_request.marketCode,
                l_subAccount.getInstitution().getInstitutionCode());

        //reset�s��R�[�h(�s��R�[�h : String)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�s��R�[�h�F�@@���N�G�X�g.�s��R�[�h
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);

        // isPTS�����J��(�ڋq)
        //�ڋq�F �⏕����.get�ڋq()
        boolean l_blnIsPTSAccountOpen =
            this.isPTSAccountOpen(l_mainAccount);

        //������ԊǗ�.validate������t�X�e�C�^�X()
        WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();

        WEB3GentradeMarket l_market = null;
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        //���N�G�X�g.�s��R�[�h�I��null�̏ꍇ
        if (l_request.marketCode != null)
        {
            //���N�G�X�g.����敪=="����������"�@@�̏ꍇ
            if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType))
            {
                //validate�s��R�[�h
                //�s��R�[�h�F�@@���N�G�X�g.�s��R�[�h
                //�،���ЃR�[�h�F�@@�⏕����.�،���ЃR�[�h
                l_market = (WEB3GentradeMarket)l_orderManager.validateMarket(
                    l_request.marketCode,
                    l_strInstitutionCode);
            }

            //���N�G�X�g.����敪=="����O����"�@@�̏ꍇ
            if (WEB3TradingTypeDef.PRESENCE_ORDER.equals(l_request.tradingType))
            {
                try
                {
                    //get�s��
                    //�،���ЃR�[�h�F�@@this.get�⏕����( ).�،���ЃR�[�h
                    //�s��R�[�h�F�@@���N�G�X�g.�s��R�[�h
                    l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                        l_strInstitutionCode,
                        l_request.marketCode);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("��戵�s��G���[�B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00645,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
        }

        //validate����\�ڋq(�ڋq, boolean, String)
        OrderValidationResult l_validationResult =
            this.validateAccountForTrading(l_mainAccount, l_blnIsPTSAccountOpen, l_request.marketCode);
        if (l_validationResult.getProcessingResult().isFailedResult() )
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //get�s��ǌx���s��(���X, boolean)
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        String[] l_strTradeCloseMarkets = this.getTradeCloseMarket(
            l_branch,
            l_blnIsPTSAccountOpen);

        //--------------------
        //get�������t�\�z
        //--------------------
        double l_dblEquityTradingPower = this.getEquityTradingPower(l_subAccount);
        
        //--------------------
        //�ȉ������E�敪���擾
        //--------------------
        WEB3GentradeHandlingOrderCond l_gentradeHandingOrderCond    = null;
        String[] l_handingPossibleExecCond                          = null;
        String[] l_handingPossibleOrderCond                         = null;
        String[] l_handingPossiblePriceCond                         = null;
        String[] l_handingPossibleExpirationDateType                = null;
        String[] l_strWLimitExecutionConditionTypeList              = null;

        //--------------------
        //����t���[�F�����̏ꍇ�̂�
        //--------------------
        if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType))
        {
            //--------------------
            //�戵�\���������擾
            //--------------------
            l_gentradeHandingOrderCond = new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                WEB3MarketCodeDef.DEFAULT);

            //--------------------
            //�戵�\���s�������
            //���s�����A���������A�l�i�����A���������敪�擾
            //--------------------
            l_handingPossibleExecCond
                = l_gentradeHandingOrderCond.getHandlingPossibleExecCond();
            l_handingPossibleOrderCond
                = l_gentradeHandingOrderCond.getHandlingPossibleOrderCond();
            l_handingPossiblePriceCond
                = l_gentradeHandingOrderCond.getHandlingPriceCond();
            l_handingPossibleExpirationDateType
                = l_gentradeHandingOrderCond.getHandlingPossibleExpirationDateType();
            // get�v�w�l�p���s�����ꗗ(String[], String[])
            l_strWLimitExecutionConditionTypeList =
                WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(
                    l_handingPossibleExecCond,
                    l_handingPossibleOrderCond);
        }

        //--------------------
        //get�戵�\�s��
        //--------------------
        String[] l_strMarketCodeList = null;
        int l_iMarketListCnt = 0;
        if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType))
        {
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //���X�F�@@�⏕����.get����X( )
            //isPTS�����J��: isPTS�����J��()�̖߂�l
            l_strMarketCodeList = this.getHandlingPossibleMarket(l_branch, l_blnIsPTSAccountOpen);
            //0�����̓G���[
            if (l_strMarketCodeList == null || l_strMarketCodeList.length == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00643,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            l_iMarketListCnt = l_strMarketCodeList.length;
        }

        //--------------------
        //����t���[ ���N�G�X�g.�����R�[�h��null�̏ꍇ
        //--------------------
        boolean l_blnIsInsiderMessageSuspension = false;
        EqTypeProduct l_eqTypeProduct = null;
        long l_lngProductId = WEB3EquityProductIdDef.ALL_PRODUCTS;
        if (l_request.productCode != null)
        {
            //validate�����R�[�h
            l_eqTypeProduct = l_orderManager.validateProductCode(
                l_request.productCode, l_strInstitutionCode);
            l_lngProductId = l_eqTypeProduct.getProductId();

            //is�C���T�C�_�[�x���\��
            l_blnIsInsiderMessageSuspension
                = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_lngProductId);
        }

        final boolean l_blnIsSell = false;                          //is���t(���Ȃ̂�false)

        String[] l_strResMarketCodeList = null;                     //���X�|���X�ݒ�p�s��R�[�h�ꗗ
        ArrayList l_lstWkMarketCodeList;                             //���X�|���X�ݒ�p�s��R�[�h�ꗗ�쐬�p���[�N
        
        long l_lngMarketId = 0;                                     //�s��ID
        WEB3EquityTradedProduct l_tradedProduct = null;             //�������
        
        String[] l_orderPriceDivList = null;                        //�����P���敪�ꗗ
        
        double l_dblCurrentPrice = 0;                               //����
        Timestamp l_tsCurrentPriceTime = null;                      //���ݒl����
        double l_dblChange = 0;                                     //�O����
        String l_strCurrentPriceDiv = null;
        String l_strBoardCurrentPrice = null;                       //���ݒl
        Timestamp l_tsBoardCurrentPriceTime = null;                 //���ݒl����
        String l_strBoardCurrentPriceDiv = null;                    //���ݒl�敪
        String l_strBoardChange = null;                             //���ݒl�O����
        String l_strVolume = null;                                  //�o����
        Timestamp l_tsVolumeTime = null;                            //�o��������
        String l_strAskPriceTitle = null;                           //���C�z�l�^�C�g���敪
        String l_strAskPrice = null;                                //���C�z�l
        Timestamp l_tsAskPriceTime = null;                          //���C�z�l����
        String l_strBidPriceTitle = null;                           //���C�z�l�^�C�g���敪
        String l_strbBidPrice = null;                               //���C�z�l
        Timestamp l_tsBidPriceTime = null;                          //���C�z�l����
        String l_strBasePrice = null;                               //��l�i

        //--------------------
        //����t���[ ���N�G�X�g.�����R�[�h��null ���� ���N�G�X�g.�s��R�[�h��null�̏ꍇ
        //--------------------
        if (l_request.productCode != null && l_request.marketCode != null)
        {
            l_lngMarketId = l_market.getMarketId();

            //validate�������
            l_tradedProduct
                = (WEB3EquityTradedProduct) l_orderManager.validateTradedProduct(
                    l_subAccount, l_eqTypeProduct, l_market, l_blnIsSell);
            
            //validateJASDAQ�����戵�\
            l_tradedProduct.validateJASDAQProductHandling(l_branch);

            //--------------------
            //���N�G�X�g�D����敪������O�����̏ꍇ
            //--------------------
            if (WEB3TradingTypeDef.PRESENCE_ORDER.equals(l_request.tradingType))
            {
                //validate����O������t�\
                l_orderManager.validateOffFloorOrderPossible(l_lngProductId, l_lngMarketId, l_subAccount);
                
                //validate����O������������
                l_orderManager.validateOffFloorDuplicateOrder(l_lngProductId, l_lngMarketId, l_subAccount);
            }
            //
            //--------------------
            //���N�G�X�g�D����敪�������������̏ꍇ
            //--------------------
            else if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType))
            {
                //get�����P���敪�ꗗ
                l_orderPriceDivList = l_orderManager.getOrderPriceDivs(l_branch, l_tradedProduct);
            }

            //--------------------
            //�������擾
            //--------------------
            //�����A���ݒl�����A�O������擾
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
            WEB3EquityProductQuote l_productQuote =
                l_productManager.getDisplayEquityProductQuote(
                    l_tradedProduct,
                    l_subAccount);
            l_dblCurrentPrice = l_productQuote.getQuote();
            l_tsCurrentPriceTime = l_productQuote.getQuoteTime();
            l_dblChange = l_productQuote.getComparedPreviousDay();
            l_strCurrentPriceDiv = l_productQuote.getQuoteTypeDiv();
            
            l_strBoardCurrentPrice = l_productQuote.getBoardCurrentPrice(); 
            l_tsBoardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime(); 
            l_strBoardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv();  
            l_strBoardChange = l_productQuote.getBoardChange(); 
            l_strVolume = l_productQuote.getVolume();  
            l_tsVolumeTime = l_productQuote.getVolumeTime();  
            l_strAskPriceTitle = l_productQuote.getAskPriceTitle(); 
            l_strAskPrice = l_productQuote.getAskPrice();  
            l_tsAskPriceTime = l_productQuote.getAskPriceTime(); 
            l_strBidPriceTitle = l_productQuote.getBidPriceTitle(); 
            l_strbBidPrice = l_productQuote.getBidPrice();  
            l_tsBidPriceTime = l_productQuote.getBidPriceTime();  
            l_strBasePrice = l_productQuote.getBasePrice();  
        }
        //
        //--------------------
        //����t���[ ���N�G�X�g.�����R�[�h��null
        //--------------------
        if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType) && 
            l_request.productCode != null)
        {
            l_lstWkMarketCodeList = new ArrayList();
            for (int i = 0; i < l_iMarketListCnt; i++)
            {
                //reset�s��R�[�h
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCodeList[i]);
                
                try {
                    //get�s��
                    l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                        l_strInstitutionCode, l_strMarketCodeList[i]);
                    
                    //validate�������
                    l_tradedProduct = (WEB3EquityTradedProduct) l_orderManager
                        .validateTradedProduct(l_eqTypeProduct, l_market);
                    
                    //��O���������Ȃ��ꍇ�̂ݎs��R�[�h�ꗗ�ւ̐ݒ�ΏۂƂ���
                    l_lstWkMarketCodeList.add(l_strMarketCodeList[i]);
                }
                catch (WEB3BaseException l_wbe){}
                catch (NotFoundException l_nfe){
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00645,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }

            //0�����̓G���[
            if (l_lstWkMarketCodeList.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00643,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                //��O���������Ȃ��ꍇ�A
                //���Y�s��R�[�h�����X�|���X�D�s��R�[�h�ꗗ�ւ̐ݒ�ΏۂƂ���B
                l_strResMarketCodeList = new String[l_lstWkMarketCodeList.size()];
                l_lstWkMarketCodeList.toArray(l_strResMarketCodeList);
            }
        }
        else
        {
            l_strResMarketCodeList = l_strMarketCodeList;
        }

        //--------------------
        //���N�G�X�g�s��R�[�h�̃`�F�b�N
        //--------------------
        TreeMap l_treeMapMarketCodeList = new TreeMap();
        TreeMap l_treeMapOrderBizDateList = new TreeMap();
        Integer l_iWkMarketCode;
        Date l_datWkOrderBizDate = null;
        boolean l_blnIsHandingMarket = false;

        //���N�G�X�g�D����敪�������������̏ꍇ�̂�
        if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType))
        {
            
            //--------------------
            //�s��R�[�h�̔z��(=get�戵�\�s��()�̖߂�l)�̗v�f���ALOOP����
            //�iTreeMap�ɒǉ����邱�ƂŃ\�[�g�����j
            //--------------------
            for (int i = 0; i < l_strResMarketCodeList.length; i++)
            {
                //reset�s��R�[�h
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strResMarketCodeList[i]);

                // get�s��(�،���ЃR�[�h : , �s��R�[�h : )
                //�،���ЃR�[�h:�⏕����.get�،����.get�،���ЃR�[�h()
                //�s��R�[�h�F LOOP�������Ŏ擾�����s��R�[�h
                try
                {
                    l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                        l_strInstitutionCode,
                        l_strResMarketCodeList[i]);
                }
                catch(NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //get������
                try {
                    l_iWkMarketCode = new Integer(l_strResMarketCodeList[i]);
                }
                catch (NumberFormatException l_nfe)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }

                //�s��.isPTS�s��==false�̏ꍇ
                if (!l_market.isPTSMarket())
                {
                    l_datWkOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                }

                //TreeMap�ɒǉ�����
                l_treeMapMarketCodeList.put(l_iWkMarketCode, l_strResMarketCodeList[i]);
                l_treeMapOrderBizDateList.put(l_iWkMarketCode, l_datWkOrderBizDate);
            }

            //--------------------
            //����t���[ ���N�G�X�g.�s��R�[�h��null�̏ꍇ
            //���N�G�X�g�D�s��R�[�h�̃`�F�b�N
            //--------------------
            if (l_request.marketCode != null)
            {
                Set stKey = l_treeMapMarketCodeList.entrySet();
                Iterator l_iteMapMarketCodeList = stKey.iterator();
                while (l_iteMapMarketCodeList.hasNext())
                {
                    Object l_objMapMarketCodeList = l_iteMapMarketCodeList.next();
                    Map.Entry l_entMapMarketCodeList = (Map.Entry)l_objMapMarketCodeList;
                    String l_valMarketCode = (String)l_entMapMarketCodeList.getValue();
                    if (l_request.marketCode.equals(l_valMarketCode))
                    {
                        l_blnIsHandingMarket = true;
                        break;
                    }
                }

                //���N�G�X�g�D�s��R�[�h���戵�\�s��Ɋ܂܂�Ă��Ȃ��ꍇ�G���[
                if (l_blnIsHandingMarket == false)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00645,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        //���N�G�X�g�D����敪������O�����̏ꍇ�̂�
        else if (WEB3TradingTypeDef.PRESENCE_ORDER.equals(l_request.tradingType))
        {
            //--------------------
            //is�戵�\�s��
            //--------------------
            if (l_request.marketCode != null)
            {
                //is�戵�\�s��
                l_blnIsHandingMarket = l_branch.isHandlingPossibleMarket(
                    l_request.marketCode, ProductTypeEnum.EQUITY);
                
                //���N�G�X�g�D�s��R�[�h���戵�\�s��Ɋ܂܂�Ă��Ȃ��ꍇ�G���[
                if (l_blnIsHandingMarket == false)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00645,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }

        //--------------------
        //�o����܂Œ���From���t�̍쐬
        //--------------------
        //�o����܂Œ���from���t
        Date l_datOrderUntilDeadLineFrom            = null;
        
        //��t���t�iTimestamp�^�^Date�^�^�c�Ɠ��v�Z���[�e�B���e�B�[�^�j
        Timestamp l_tsSysTimestamp = GtlUtils.getSystemTimestamp();
        Date l_datSysTimestamp = new Date(l_tsSysTimestamp.getTime());
        WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsSysTimestamp);

        //��t���t�Ɠ����������������s�ꂪ����ꍇ true : �ȊOfalse
        boolean l_blnContainSysDatInOdrBizDatLst = false;

        //���N�G�X�g.�s��R�[�h�I��null�̏ꍇ
        //get�s��(�،���ЃR�[�h : , �s��R�[�h : )
        //�،���ЃR�[�h:�⏕����.get�،����.get�،���ЃR�[�h()
        //�s��R�[�h�F ���N�G�X�g.�s��R�[�h
        if (l_request.marketCode != null)
        {
            try
            {
                l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                    l_strInstitutionCode,
                    l_request.marketCode);
            }
            catch(NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //�i���N�G�X�g.�s��R�[�h==nul�j �܂���
        //�i���N�G�X�g.�s��R�[�h != null ���� �s��.isPTS�s��()==true) �̏ꍇ
        //���o����܂Œ���From���t���K�v�Ȃ̂͌����������̏ꍇ�̂�
        if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType)
            && (l_request.marketCode == null
                || (l_request.marketCode != null && l_market.isPTSMarket())))
        {
            Set stKey = l_treeMapOrderBizDateList.entrySet();
            Iterator l_iteMapOrderBizDateList = stKey.iterator();
            
            while (l_iteMapOrderBizDateList.hasNext())
            {
                Object l_objMapOrderBizDateList = l_iteMapOrderBizDateList.next();
                Map.Entry l_entMapOrderBizDateList = (Map.Entry)l_objMapOrderBizDateList;
                Date l_valOrderBizDate = (Date)l_entMapOrderBizDateList.getValue();
                
                if (WEB3DateUtility.compareToDay(l_datSysTimestamp, l_valOrderBizDate) == 0)
                {
                    l_blnContainSysDatInOdrBizDatLst = true;
                    break;
                }
            }

            //��t���t�Ɠ����������������s�ꂪ����ꍇ�A��t���t��ݒ肷��B
            if (l_blnContainSysDatInOdrBizDatLst)
            {
                l_datOrderUntilDeadLineFrom = WEB3DateUtility.toDay(l_datSysTimestamp);
            }
            //�S�s��̔���������t���t�ƈقȂ�ꍇ�A���c�Ɠ���ݒ肷��B
            else
            {
                l_datOrderUntilDeadLineFrom = WEB3DateUtility.toDay(l_genBizDate.roll(1));;
            }
        }
        //���N�G�X�g.�s��R�[�h != null ���� �s��.isPTS�s��()==false�̏ꍇ
        //�o����܂Œ���From���t��null��ݒ肷��B
        else
        {
            l_datOrderUntilDeadLineFrom = null;
        }

        //--------------------
        //�o����܂Œ����J�n���^�I�����^�����������j���ꗗ�擾
        //--------------------
        Date l_datOrderUntilDeadLineStartDate       = null;     //�o����܂Œ����J�n��
        Date l_datOrderUntilDeadLineEndDate         = null;     //�o����܂Œ����I����
        Date[] l_datOrderUntilDeadLineHolidayList   = null;     //�����������j���ꗗ
        
        //����t���[ �戵�\��������.is�ł���܂Œ����戵�\() == true�̏ꍇ
        //�� �戵�\���������́u�����������v�̎��̂ݎ擾
        if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType)
            && l_gentradeHandingOrderCond.isOrderUntilDeadLinePossibleHandling())
        {
			//reset�s��R�[�h
			WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);

            //�o����܂Œ����J�n���E�I�����E�����������j���ꗗ�擾
            l_datOrderUntilDeadLineStartDate = l_gentradeHandingOrderCond
                .getOrderUntilDeadLineStartDay(l_datOrderUntilDeadLineFrom);
            l_datOrderUntilDeadLineEndDate = l_gentradeHandingOrderCond
                .getOrderUntilDeadLineEndDay(l_datOrderUntilDeadLineFrom);
            l_datOrderUntilDeadLineHolidayList = l_gentradeHandingOrderCond
                .getExpirationDateHoliday(l_datOrderUntilDeadLineFrom);
        }

        //--------------------
        //is��������J��
        //--------------------
        boolean l_blnIsSpecialAccountEstablished = false;
        if (l_request.productCode != null && l_request.marketCode != null)
        {
            l_blnIsSpecialAccountEstablished = l_mainAccount
                .isSpecialAccountEstablished(l_tradedProduct.getDailyDeliveryDate(), l_subAccount);
        }
        else
        {
            l_blnIsSpecialAccountEstablished = l_mainAccount
                .isSpecialAccountEstablished(l_subAccount);
        }

        //--------------------
        //validate�ڋq�����ʎ����~
        //--------------------
        this.validateAccountProductOrderStop(
            l_subAccount, l_lngProductId, l_blnIsPTSAccountOpen, l_request.marketCode);

        //--------------------
        //�v���p�e�B�Z�b�g
        //--------------------
        WEB3EquityBuyInputResponse l_response = (WEB3EquityBuyInputResponse) l_request.createResponse();
        
        String[] l_strTaxTypeList;
        if (l_blnIsSpecialAccountEstablished)
        {
            l_strTaxTypeList = new String[2];
            l_strTaxTypeList[0] = WEB3AccountDivDef.NORMAL;
            l_strTaxTypeList[1] = WEB3AccountDivDef.SPECIAL;
        }
        else
        {
            l_strTaxTypeList = new String[1];
            l_strTaxTypeList[0] = WEB3AccountDivDef.NORMAL;
        }

        //���N�G�X�g�D����敪���u�����������v�̏ꍇ
        if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType))
        {
            String[] l_strWkPriceDivList        = {
                WEB3OrderPriceDivDef.MARKET_PRICE, WEB3OrderPriceDivDef.LIMIT_PRICE};
                
            if (l_request.productCode != null && l_request.marketCode != null)
            {
                l_response.orderPriceDivList        = l_orderPriceDivList;
            }
            else
            {
                l_response.orderPriceDivList        = l_strWkPriceDivList;
            }
            l_response.priceCondList            = l_handingPossiblePriceCond;
            l_response.execCondList             = l_handingPossibleExecCond;
            l_response.wlimitExecCondList       = l_strWLimitExecutionConditionTypeList;
            l_response.expirationStartDate      = l_datOrderUntilDeadLineStartDate;
            l_response.expirationEndDate        = l_datOrderUntilDeadLineEndDate;
            l_response.holidayList              = l_datOrderUntilDeadLineHolidayList;
            l_response.marketList               = (String[]) l_treeMapMarketCodeList.values().toArray(
                                                    new String[l_treeMapMarketCodeList.size()]);
            l_response.taxTypeList              = l_strTaxTypeList;
            l_response.expirationDateTypeList   = l_handingPossibleExpirationDateType;
            l_response.orderCondTypeList        = l_handingPossibleOrderCond;
            l_response.marketCode = l_request.marketCode;
        }
        //
        //���N�G�X�g�D����敪���u����O�����v�̏ꍇ
        else if (WEB3TradingTypeDef.PRESENCE_ORDER.equals(l_request.tradingType))
        {
            String[] l_strWkPriceDivList        = {WEB3OrderPriceDivDef.LIMIT_PRICE};
            String[] l_strWkPriceCondList       = {WEB3PriceConditionDef.DEFAULT};
            String[] l_strWkExecCondList        = {WEB3ExecutionConditionDef.NO_CONDITION};
            String[] l_strWkMktCdList           = {l_request.marketCode};
            String[] l_strWkExpDatTypList       = {WEB3OrderExpirationDateTypeDef.DAY_LIMIT};
            String[] l_strWkOrderCondList       = {WEB3OrderingConditionDef.DEFAULT};
            
            l_response.orderPriceDivList        = l_strWkPriceDivList;
            l_response.priceCondList            = l_strWkPriceCondList;
            l_response.execCondList             = l_strWkExecCondList;
            l_response.wlimitExecCondList       = null;
            l_response.expirationStartDate      = null;
            l_response.expirationEndDate        = null;
            l_response.holidayList              = null;
            l_response.marketList               = l_strWkMktCdList;
            l_response.taxTypeList              = l_strTaxTypeList;
            l_response.expirationDateTypeList   = l_strWkExpDatTypList;
            l_response.orderCondTypeList        = l_strWkOrderCondList;
        }
        //�����̎�������ʂ̃v���p�e�B
        l_response.messageSuspension        = l_strTradeCloseMarkets;
        l_response.insiderWarningFlag       = l_blnIsInsiderMessageSuspension;
        
        l_response.currentPriceDiv = l_strCurrentPriceDiv;
        
        //(*2)�@@�����R�[�h�E�s��R�[�h�w��(���N�G�X�g�f�[�^.�����R�[�h != null�A���A���N�G�X�g�f�[�^.�s��R�[�h != null)
        //      �̏ꍇ�̂ݐݒ�B�ȊO�Anull��ݒ�B
        if (l_request.productCode != null && l_request.marketCode != null)
        {
            l_response.currentPrice             = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
            l_response.comparedPreviousDay      = WEB3StringTypeUtility.formatNumber(l_dblChange);
            
            //���ݒl�F�@@�擾�������������������.get���ݒl( )�̖߂�l���Z�b�g
            l_response.boardCurrentPrice = l_strBoardCurrentPrice;
            
            //���ݒl�����F�@@�擾�������������������.get���ݒl����( )�̖߂�l���Z�b�g
            l_response.boardCurrentPriceTime = l_tsBoardCurrentPriceTime; 
            
            //���ݒl�敪�F�@@�擾�������������������.get���ݒl�敪( )�̖߂�l���Z�b�g           
            l_response.boardCurrentPriceDiv = l_strBoardCurrentPriceDiv; 
            
            //���ݒl�O����F�@@�擾�������������������.get���ݒl�O����( )�̖߂�l���Z�b�g
            l_response.boardChange = l_strBoardChange;
            
            //�o�����F�@@�擾�������������������.get�o����( )�̖߂�l���Z�b�g
            l_response.volume = l_strVolume;
            
            //�o���������F�@@�擾�������������������.get�o��������( )�̖߂�l���Z�b�g
            l_response.volumeTime = l_tsVolumeTime;
            
            //���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪( )�̖߂�l���Z�b�g
            l_response.askPriceTitle = l_strAskPriceTitle;
            
            //���C�z�l�F�@@�擾�������������������.get���C�z�l( )�̖߂�l���Z�b�g
            l_response.askPrice = l_strAskPrice; 
            
            //���C�z�l�����F�@@�擾�������������������.get���C�z�l����( )�̖߂�l���Z�b�g
            l_response.askPriceTime = l_tsAskPriceTime;
            
            //���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪( )�̖߂�l���Z�b�g
            l_response.bidPriceTitle = l_strBidPriceTitle;
            
            //���C�z�l�F�@@�擾�������������������.get���C�z�l( )�̖߂�l���Z�b�g
            l_response.bidPrice = l_strbBidPrice;
            
            //���C�z�l�����F�@@�擾�������������������.get���C�z�l����( )�̖߂�l���Z�b�g
            l_response.bidPriceTime = l_tsBidPriceTime;
            
            //��l�i�F�@@�擾�������������������.get��l�i( )�̖߂�l���Z�b�g
            l_response.basePrice = l_strBasePrice;

        }
        else 
        {
            l_response.currentPrice             = null;
            l_response.comparedPreviousDay      = null;        
            l_response.boardCurrentPrice        = null;  
            l_response.boardCurrentPriceTime    = null; 
            l_response.boardCurrentPriceDiv     = null;  
            l_response.boardChange              = null; 
            l_response.volume                   = null; 
            l_response.volumeTime               = null; 
            l_response.askPriceTitle            = null;  
            l_response.askPrice                 = null; 
            l_response.askPriceTime             = null; 
            l_response.bidPriceTitle            = null;  
            l_response.bidPrice                 = null;  
            l_response.bidPriceTime             = null; 
            l_response.basePrice                = null;  
        }
        
        l_response.currentPriceTime         = l_tsCurrentPriceTime;
        l_response.tradingPower             = WEB3StringTypeUtility.formatNumber(l_dblEquityTradingPower);
        
        if (l_request.productCode != null)
        {
            EqtypeProductRow l_eqTypeProductRow = (EqtypeProductRow)l_eqTypeProduct.getDataSourceObject();
            l_response.productName              = l_eqTypeProductRow.getStandardName();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���t�\�z)<BR>
     * get���t�\�z���擾����B<BR>
     * <BR>
     * ����]�̓T�[�r�X.get�������t�\�z(�����̕⏕����)���R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@return double
     * @@throws WEB3BaseException
     */
    protected double getEquityTradingPower(
        WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEquityTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblEquityTradingPower =
            l_tradingpowerService.getEquityTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblEquityTradingPower;
    }

    /**
     * (get�s��R�[�h)<BR>
     * �s��R�[�h���擾����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�s��R�[�h == null�܂���<BR>
     * �@@�@@�p�����[�^.�����R�[�h == null�̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�s��R�[�h != null<BR>
     * �@@�@@���@@�p�����[�^.�����R�[�h != null<BR>
     * �@@�@@���@@�p�����[�^.�s��R�[�h != "99�F�D��s��"�̏ꍇ�A�p�����[�^.�s��R�[�h��ԋp����B<BR>
     * <BR>
     * �R�j�@@��L�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�R�|�P�j�@@�g���v���_�N�g�}�l�[�W��.getProduct()���R�[�����A�����������擾����B<BR>
     * �@@�@@�@@�@@�@@[getProduct()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�����R�[�h�@@�@@�@@�F�@@�p�����[�^.�����R�[�h<BR>
     * <BR>
     * �R�|�Q�j�@@��������.get�D��s��()���R�[�����A�s����擾����B<BR>
     * <BR>
     * �R�|�R�j�@@�R�|�Q�j�Ŏ擾�����s�ꂪnull�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:�@@ WEB3ErrorCatalog.BUSINESS_ERROR_02702<BR>
     * �@@�@@�@@�@@�@@�ȊO�A�s��.getMarketCode()�̖߂�l��ԋp����B<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getMarketCode(
        String l_strProductCode,
        String l_strMarketCode,
        String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarketCode(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�p�����[�^.�s��R�[�h == null�܂���
        //�@@�p�����[�^.�����R�[�h == null�̏ꍇ�Anull��ԋp����B
        if (l_strMarketCode == null || l_strProductCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�p�����[�^.�s��R�[�h != null
        //�@@���@@�p�����[�^.�����R�[�h != null
        //�@@���@@�p�����[�^.�s��R�[�h != "99�F�D��s��"�̏ꍇ�A�p�����[�^.�s��R�[�h��ԋp����B
        if (l_strMarketCode != null &&
            l_strProductCode != null &&
            !WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_strMarketCode))
        {
            log.exiting(STR_METHOD_NAME);
            return l_strMarketCode;
        }

        //�R�j�@@��L�ȊO�̏ꍇ
        //�@@�R�|�P�j�@@�g���v���_�N�g�}�l�[�W��.getProduct()���R�[�����A�����������擾����B
        //�@@�@@�@@�@@�@@�@@[getProduct()�ɐݒ肷�����]
        //�@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h
        //�@@�@@�@@�@@�@@�@@�@@�����R�[�h�@@�@@�@@�F�@@�p�����[�^.�����R�[�h
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
            l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            l_eqTypeProduct =
                (WEB3EquityProduct)l_equityProductManager.getProduct(l_institution, l_strProductCode);
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

        //�R�|�Q�j�@@��������.get�D��s��()���R�[�����A�s����擾����B
        Market l_market = l_eqTypeProduct.getPrimaryMarket();

        //�@@�R�|�R�j�@@�R�|�Q�j�Ŏ擾�����s�ꂪnull�̏ꍇ�A��O��throw����B
        //�@@�@@�@@�@@�@@�@@�ȊO�A�s��.getMarketCode()�̖߂�l��ԋp����B
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
     * (get�s��ǌx���s��)<BR>
     * �s��ǌx���s����擾����B<BR>
     * PTS�����J�݌ڋq�̏ꍇ�APTS�̕ǌx���s����擾����B<BR>
     * <BR>
     * �P�j������ԊǗ�.get�s��ǌx���s��()���R�[�����A�s��R�[�h�̔z����擾����B<BR>
     * <BR>
     * �@@�@@[������ԊǗ�.get�s��ǌx���s��()�ɃZ�b�g�������]<BR>
     * �@@�@@���X�F�@@����.���X<BR>
     * �@@�@@�����^�C�v�F�@@"����"<BR>
     * �@@�@@�M�p����敪�F�@@"DEFAULT"<BR>
     * <BR>
     * <BR>
     * �Q�jPTS�����J�݌ڋq�̏ꍇ(����.isPTS�����J��==true)<BR>
     * <BR>
     * �@@�@@�Q�|�P�jPTS������ԊǗ�.get�s��ǌx���s��()���R�[�����A<BR>
     * �@@�@@�s��R�[�h�̔z����擾����<BR>
     * <BR>
     * �@@�@@[PTS������ԊǗ�.get�s��ǌx���s��()�ɃZ�b�g�������]<BR>
     * �@@�@@���X�F�@@����.���X<BR>
     * �@@�@@�����^�C�v�F�@@"����"<BR>
     * �@@�@@�M�p����敪�F�@@"DEFAULT"<BR>
     * <BR>
     * �@@�@@�Q�|�Q�j�@@�P�j�̌��ʂƁ@@�Q�|�P�j�̌��ʂ��}�[�W����<BR>
     * �@@�@@�s��R�[�h�����Ń\�[�g����B<BR>
     * <BR>
     * �R�j�擾�����s��R�[�h�̔z���ԋp����B<BR>
     * <BR>
     * @@param l_gentradeBranch - (���X)<BR>
     * �⏕����.get����X( )<BR>
     * @@param l_blnIsPTSAccountOpen - (isPTS�����J��)<BR>
     * �ڋq.isPTS�����J��()�Ŏ擾�����l<BR>
     * @@throws WEB3BaseException
     * @@return String[]
     */
    protected String[] getTradeCloseMarket(
        WEB3GentradeBranch l_gentradeBranch,
        boolean l_blnIsPTSAccountOpen)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradeCloseMarket(WEB3GentradeBranch, boolean)";
        log.entering(STR_METHOD_NAME);

        //�P�j������ԊǗ�.get�s��ǌx���s��()���R�[�����A�s��R�[�h�̔z����擾����B
        //[������ԊǗ�.get�s��ǌx���s��()�ɃZ�b�g�������]
        //���X�F�@@����.���X
        //�����^�C�v�F�@@"����"
        //�M�p����敪�F�@@"DEFAULT"
        String[] l_strMarketCodes = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            l_gentradeBranch, ProductTypeEnum.EQUITY, WEB3MarginTradingDivDef.DEFAULT);

        //�Q�jPTS�����J�݌ڋq�̏ꍇ(����.isPTS�����J��==true)
        if (l_blnIsPTSAccountOpen)
        {
            //�Q�|�P�jPTS������ԊǗ�.get�s��ǌx���s��()���R�[�����A�s��R�[�h�̔z����擾����
            //[PTS������ԊǗ�.get�s��ǌx���s��()�ɃZ�b�g�������]
            //���X�F�@@����.���X
            //�����^�C�v�F�@@"����"
            //�M�p����敪�F�@@"DEFAULT"
            String[] l_strPTSMarketCodes = WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
                l_gentradeBranch, ProductTypeEnum.EQUITY, WEB3MarginTradingDivDef.DEFAULT);

            //�Q�|�Q�j�@@�P�j�̌��ʂƁ@@�Q�|�P�j�̌��ʂ��}�[�W���Ďs��R�[�h�����Ń\�[�g����B
            l_strMarketCodes = this.mergeAndSort(l_strMarketCodes, l_strPTSMarketCodes);
        }

        //�R�j�擾�����s��R�[�h�̔z���ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strMarketCodes;
    }

    /**
     * (get�戵�\�s��)<BR>
     * �戵�\�s����擾����B<BR>
     * PTS�����J�݌ڋq�̏ꍇ�APTS�̎戵�\�s����擾����B<BR>
     * <BR>
     * �P�j(���X�s���)�戵����.get�戵�\�s��()���R�[�����A<BR>
     * �@@�s��R�[�h�̔z����擾����B<BR>
     * <BR>
     * �@@�@@[(���X�s���)�戵����.get�戵�\�s��()�ɃZ�b�g�������]<BR>
     * �@@�@@���X�F�@@����.���X<BR>
     * �@@�@@�����^�C�v�F�@@"����"<BR>
     * <BR>
     * �Q�jPTS�����J�݌ڋq�̏ꍇ(����.isPTS�����J��==true)<BR>
     * <BR>
     * �@@�@@�Q�|�P�j(���X�s��ʁEPTS)�戵����.get�戵�\�s��()���R�[�����A<BR>
     * �@@�@@�s��R�[�h�̔z����擾����B<BR>
     * <BR>
     * �@@�@@[(���X�s��ʁEPTS)�戵����.get�戵�\�s��()�ɃZ�b�g�������]<BR>
     * �@@�@@���X�F�@@����.���X<BR>
     * �@@�@@�����^�C�v�F�@@"����"<BR>
     * <BR>
     * �@@�@@�Q�|�Q�j�@@�P�j�̌��ʂƁ@@�Q�|�P�j�̌��ʂ��}�[�W����<BR>
     * �@@�@@�s��R�[�h�����Ń\�[�g����B<BR>
     * <BR>
     * �R�j�擾�����s��R�[�h�̔z���ԋp����B<BR>
     * <BR>
     * @@param l_gentradeBranch - (���X)<BR>
     * �⏕����.get����X( )<BR>
     * @@param l_blnIsPTSAccountOpen - (isPTS�����J��)<BR>
     * �ڋq.isPTS�����J��()�Ŏ擾�����l<BR>
     * @@throws WEB3BaseException
     * @@return String[]
     */
    protected String[] getHandlingPossibleMarket(
        WEB3GentradeBranch l_gentradeBranch,
        boolean l_blnIsPTSAccountOpen)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHandlingPossibleMarket(WEB3GentradeBranch, boolean)";
        log.entering(STR_METHOD_NAME);

        //�P�j(���X�s���)�戵����.get�戵�\�s��()���R�[�����A�s��R�[�h�̔z����擾����B
        //[(���X�s���)�戵����.get�戵�\�s��()�ɃZ�b�g�������]
        //���X�F�@@����.���X
        //�����^�C�v�F�@@"����"
        String[] l_strMarketCodes = WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
            l_gentradeBranch,  ProductTypeEnum.EQUITY);

        //�Q�jPTS�����J�݌ڋq�̏ꍇ(����.isPTS�����J��==true)
        if (l_blnIsPTSAccountOpen)
        {
            //�Q�|�P�j(���X�s��ʁEPTS)�戵����.get�戵�\�s��()���R�[�����A�s��R�[�h�̔z����擾����B
            //[(���X�s��ʁEPTS)�戵����.get�戵�\�s��()�ɃZ�b�g�������]
            //���X�F�@@����.���X
            //�����^�C�v�F�@@"����"
            String[] l_strPTSMarketCodes = WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                l_gentradeBranch,  ProductTypeEnum.EQUITY);

            //�Q�|�Q�j�@@�P�j�̌��ʂƁ@@�Q�|�P�j�̌��ʂ��}�[�W���Ďs��R�[�h�����Ń\�[�g����B
            l_strMarketCodes = this.mergeAndSort(l_strMarketCodes, l_strPTSMarketCodes);
        }

        //�R�j�擾�����s��R�[�h�̔z���ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strMarketCodes;
    }

    /**
     * (isPTS�����J��)<BR>
     * �ڋq.isPTS�����J�݂��R�[�����A���ʂ�ԋp����<BR>
     * <BR>
     * �P�j����.�ڋq�I�u�W�F�N�g��isPTS�����J��()���R�[�����A���ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@return boolean
     */
    protected boolean isPTSAccountOpen(WEB3GentradeMainAccount l_mainAccount)
    {
        return l_mainAccount.isPTSAccountOpen();
    }

    /**
     * (validate����\�ڋq)<BR>
     * ����\�ڋq���ǂ����`�F�b�N����B<BR>
     * PTS�����J�݌ڋq��PTS�s�ꂪ�w�肳��Ă���ꍇ�́A<BR>
     * PTS������ԊǗ����甭�������擾���A<BR>
     * �������w��Ń`�F�b�N���s���B<BR>
     * <BR>
     * �P�j����.�s��R�[�h��null�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[�����A<BR>
     * �@@�@@�s��I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�،���ЃR�[�h:�@@����.�ڋq.getInstitution().getInstitutionCode()<BR>
     * �@@�@@�@@�s��R�[�h:�@@����.�s��R�[�h<BR>
     * <BR>
     * �Q�j�����`�F�b�N�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �R�j�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�R�|�P�j����.isPTS�����J�݁�true�@@����<BR>
     * �@@�@@�@@�@@�@@����.�s��R�[�h��null�@@����<BR>
     * �@@�@@�@@�@@�@@�s��.isPTS�s��()��true�@@�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�����`�F�b�N.validate����\�ڋq(�ڋq�A������)���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�ڋq�F�@@����.�ڋq<BR>
     * �@@�@@�@@�������F�@@PTS������ԊǗ�.get������()<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�R�|�P�j�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�����`�F�b�N.validate����\�ڋq(�ڋq)���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�ڋq�F�@@����.�ڋq<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@param l_blnIsPTSAccountOpen - (isPTS�����J��)<BR>
     * isPTS�����J��<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@return OrderValidationResult
     */
    protected OrderValidationResult validateAccountForTrading(
        WEB3GentradeMainAccount l_mainAccount,
        boolean l_blnIsPTSAccountOpen,
        String l_strMarketCode)
    {
        final String STR_METHOD_NAME = "validateAccountForTrading(WEB3GentradeMainAccount, boolean, String)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017));
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //�P�j����.�s��R�[�h��null�̏ꍇ�A
        WEB3GentradeMarket l_market = null;
        boolean l_blnIsPTSMarket = false;
        if (l_strMarketCode != null)
        {
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[�����A�s��I�u�W�F�N�g�𐶐�����B
            //[�����̐ݒ�]
            //�،���ЃR�[�h:�@@����.�ڋq.getInstitution().getInstitutionCode()
            //�s��R�[�h:�@@����.�s��R�[�h
            try
            {
                l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_strMarketCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005));
            }

            try
            {
                l_blnIsPTSMarket = l_market.isPTSMarket();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        l_ex.getErrorInfo()));
            }
        }

        //�Q�j�����`�F�b�N�I�u�W�F�N�g�𐶐�����B
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //�R�|�P�j����.isPTS�����J�݁�true�@@���� ����.�s��R�[�h��null�@@���� �s��.isPTS�s��()��true�@@�̏ꍇ
        if (l_blnIsPTSAccountOpen && l_strMarketCode != null && l_blnIsPTSMarket)
        {
            //PTS������ԊǗ�.get������()
            Date l_datOrderBizDate = null;
            try
            {
                l_datOrderBizDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
            }
            catch (WEB3SystemLayerException l_ex)
            {
                log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        l_ex.getErrorInfo()));
            }

            //�����`�F�b�N.validate����\�ڋq(�ڋq�A������)���R�[������B
            //[�����̐ݒ�]
            //�ڋq�F�@@����.�ڋq
            //�������F�@@PTS������ԊǗ�.get������()
            OrderValidationResult l_validationPtsResult =
                l_gentradeOrderValidator.validateAccountForTrading(
                    l_mainAccount,
                    new Timestamp(l_datOrderBizDate.getTime()));

            log.exiting(STR_METHOD_NAME);
            return l_validationPtsResult;
        }
        else
        {
            //�ȊO�̏ꍇ�����`�F�b�N.validate����\�ڋq(�ڋq)���R�[������B
            //[�����̐ݒ�]
            //�ڋq�F�@@����.�ڋq
            OrderValidationResult l_validationResult =
                l_gentradeOrderValidator.validateAccountForTrading(l_mainAccount);

            log.exiting(STR_METHOD_NAME);
            return l_validationResult;
        }
    }

    /**
     * (validate�ڋq�����ʎ����~)<BR>
     * �ڋq���w�肳�ꂽ�����̎���ɂ��Ď����~���ł��邩�`�F�b�N����B<BR>
     * PTS�����J�݌ڋq��PTS�s�ꂪ�w�肳��Ă���ꍇ�́A<BR>
     * PTS�����}�l�[�W���̓������\�b�h�Ń`�F�b�N���s���B<BR>
     * <BR>
     * �P�j����.�s��R�[�h��null�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[�����A<BR>
     * �@@�@@�s��I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�،���ЃR�[�h:�@@����.�⏕����.getInstitution().getInstitutionCode()<BR>
     * �@@�@@�@@�s��R�[�h:�@@����.�s��R�[�h<BR>
     * <BR>
     * �Q�j�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�Q�|�P�j����.isPTS�����J�݁�true�@@����<BR>
     * �@@�@@�@@�@@�@@����.�s��R�[�h��null�@@����<BR>
     * �@@�@@�@@�@@�@@�s��.isPTS�s��()��true�@@�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@PTS�����}�l�[�W��.validate�ڋq�����ʎ����~(PTS)()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�⏕�����F�@@����.�⏕����<BR>
     * �@@�@@�@@����ID�F�@@����.����ID<BR>
     * �@@�@@�@@������ʁF EQUITY_BUY�i�����������j<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�Q�|�P�j�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�g�����������}�l�[�W��.validate�ڋq�����ʎ����~()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�⏕�����F�@@����.�⏕����<BR>
     * �@@�@@�@@����ID�F�@@����.����ID<BR>
     * �@@�@@�@@������ʁF EQUITY_BUY�i�����������j<BR>
     * <BR>
     * @@param l_subAccount -�i�⏕�����j<BR>
     * �⏕����<BR>
     * @@param l_lngProductId -�i����ID�j<BR>
     * ����ID<BR>
     * @@param l_blnIsPTSAccountOpen - (isPTS�����J��)<BR>
     * isPTS�����J��<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@throws WEB3BaseException
     */
    protected void validateAccountProductOrderStop(
        SubAccount l_subAccount,
        long l_lngProductId,
        boolean l_blnIsPTSAccountOpen,
        String l_strMarketCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccountProductOrderStop(SubAccount, long, boolean, String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "," + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule =
            (WEB3EquityTradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityPTSOrderManager l_orderPTSManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();

        //�P�j����.�s��R�[�h��null�̏ꍇ�A
        WEB3GentradeMarket l_market = null;
        if (l_strMarketCode != null)
        {
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[�����A�s��I�u�W�F�N�g�𐶐�����B
            //[�����̐ݒ�]
            //�،���ЃR�[�h:�@@����.�ڋq.getInstitution().getInstitutionCode()
            //�s��R�[�h:�@@����.�s��R�[�h
            try
            {
                l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_strMarketCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //�Q�|�P�j����.isPTS�����J�݁�true�@@���� ����.�s��R�[�h��null�@@���� �s��.isPTS�s��()��true�@@�̏ꍇ
        if (l_blnIsPTSAccountOpen && l_strMarketCode != null && l_market.isPTSMarket())
        {
            //PTS�����}�l�[�W��.validate�ڋq�����ʎ����~(PTS)()���R�[������B
            //[�����̐ݒ�]
            //�⏕�����F�@@����.�⏕����
            //����ID�F�@@����.����ID
            //������ʁF EQUITY_BUY�i�����������j
            l_orderPTSManager.validatePTSAccountProductOrderStop(
                l_subAccount, l_lngProductId, OrderTypeEnum.EQUITY_BUY);
        }
        else
        {
            //�@@�Q�|�Q�j�@@�Q�|�P�j�ȊO�̏ꍇ
            //�g�����������}�l�[�W��.validate�ڋq�����ʎ����~()���R�[������B
            //[�����̐ݒ�]
            //�⏕�����F�@@����.�⏕����
            //����ID�F�@@����.����ID
            //������ʁF EQUITY_BUY�i�����������j
            l_orderPTSManager.validateAccountProductOrderStop(
                l_subAccount, l_lngProductId, OrderTypeEnum.EQUITY_BUY);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ��̔z�����̔z��ɍ������āA�����\�[�g�ŕԋp����
     * @@param l_strArraySrc
     * @@param l_strDest
     * @@return String[]
     */
    private String[] mergeAndSort(String[] l_strArraySrcs, String[] l_strDests)
    {
        final String STR_METHOD_NAME = "mergeAndSort(String[], String[])";
        log.entering(STR_METHOD_NAME);

         Object[] l_objMergeArrays =
             WEB3Toolkit.toUnique(WEB3Toolkit.merge(l_strArraySrcs, l_strDests));

         if (l_objMergeArrays == null || l_objMergeArrays.length == 0)
         {
             return null;
         }

         String[] l_strResults = new String[l_objMergeArrays.length];

         for (int i = 0; i < l_objMergeArrays.length; i++)
         {
             l_strResults[i] = (String)l_objMergeArrays[i];
         }

         String l_strTemp = null;
         for (int i = 0; i < l_strResults.length; i++)
         {
             for (int j = i + 1; j < l_strResults.length; j++)
             {
                 if (Integer.parseInt(l_strResults[i]) > Integer.parseInt(l_strResults[j]))
                 {
                     l_strTemp = l_strResults[j];
                     l_strResults[j] = l_strResults[i];
                     l_strResults[i] = l_strTemp;
                 }
             }
         }

         log.exiting(STR_METHOD_NAME);
         return l_strResults;
    }
}
@
