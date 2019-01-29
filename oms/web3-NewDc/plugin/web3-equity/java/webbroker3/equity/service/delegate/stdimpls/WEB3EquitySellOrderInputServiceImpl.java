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
filename	WEB3EquitySellOrderInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�������̓T�[�r�X���������{����(WEB3EquitySellOrderInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 �A�C��(���u) �V�K�쐬
                   2004/12/20 �X��  (SRA)  �c�Č��Ή�
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2006/07/04 ���� (���u) �d�l�ύX�Ǘ�No.937
                   2006/08/29 �ęԍg(���u) �d�l�ύX���f��970
                   2006/11/06 �����F(���u) ���f�� 986
Revesion History : 2007/12/17 ����(���u) ���f�� 1220�A1221�A1226
Revesion History : 2007/12/20 ����(���u) ���f�� 1264
Revesion History : 2008/01/15 ��іQ(���u) ���f��NO.1283
Revesion History : 2008/01/16 ��іQ(���u) ���f��NO.1288, 1292
Revesion History : 2008/04/10 �����Q(���u) ���f��No.1311
Revesion History : 2008/04/16 �����Q(���u) ���f��No.1313
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.message.WEB3EquitySellInputRequest;
import webbroker3.equity.message.WEB3EquitySellInputResponse;
import webbroker3.equity.service.delegate.WEB3EquitySellOrderInputService;
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
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�����������t�������̓T�[�r�XImpl)�B<BR>
 * <BR>
 * �����������t�������̓T�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3EquitySellOrderInputServiceImpl 
    extends WEB3EquityClientRequestService implements WEB3EquitySellOrderInputService 
{
   /**
    * (���O�o�̓��[�e�B���e�B�B)<BR>
    */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3EquitySellOrderInputServiceImpl.class);
   
   /**
    * (�R���X�g���N�^)�B<BR>
    * @@roseuid 4091EC7F0210
    */
   public WEB3EquitySellOrderInputServiceImpl() 
   {
    
   }
   
   /**
    * (execute)�B<BR>
    * <BR>
    * �����������t�������̓T�[�r�X���������{����B <BR>
    * <BR>
    * �V�[�P���X�}�u�i���t�������́j���͉�ʕ\���f�[�^�擾�v�Q�ƁB<BR>
    * <BR>
    * @@param l_request - ���N�G�X�g�f�[�^
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws WEB3BaseException
    * @@roseuid 40628C2F0153
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request)
       throws WEB3BaseException 
   {
       final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
       log.entering(STR_METHOD_NAME);
       
       WEB3EquitySellInputResponse l_sellInputResponse =
           this.getSellInputScreen((WEB3EquitySellInputRequest)l_request);
       
       log.exiting(STR_METHOD_NAME);
       return l_sellInputResponse;
   }

   /**
    * (get���͉��)<BR>
    * �����������t�������͉�ʕ\�����������{����B<BR>
    * <BR>
    * �V�[�P���X�}�u�i���t�������́j���͉�ʕ\���f�[�^�擾�v�Q�ƁB<BR>
    * @@param l_sellInputRequest - ���N�G�X�g�f�[�^
    * @@return WEB3EquitySellInputResponse
    * @@throws WEB3BaseException
    */
   protected WEB3EquitySellInputResponse getSellInputScreen(
       WEB3EquitySellInputRequest l_sellInputRequest)
       throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "getSellInputScreen(WEB3EquitySellInputRequest)";
       log.entering(STR_METHOD_NAME);
        
       //--------------------
       //�e�I�u�W�F�N�g�̎擾
       //--------------------
       FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
       WEB3EquityTradingModule l_tradingModule
           = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
       WEB3EquityOrderManager l_orderManager
           = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
       WEB3EquityProductManager l_productManager
           = (WEB3EquityProductManager) l_tradingModule.getProductManager();
       WEB3GentradeFinObjectManager l_finObjectManager
           = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();


       //--------------------
       //���N�G�X�g.validate
       //--------------------
       l_sellInputRequest.validate();

       //--------------------
       //get�ۗL���Y
       //--------------------
       Asset l_asset = this.getAsset(l_sellInputRequest);
       
       // get�⏕����()
       WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
       WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
       WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();

       //reset�s��R�[�h(�s��R�[�h�FString)
       WEB3GentradeTradingTimeManagement.resetMarketCode(l_sellInputRequest.marketCode);

       // isPTS�����J��
       // �����F�ڋq�F�@@�⏕����.getMainAccount()
       boolean l_blnIsPTSAccountEstablished = this.isPTSAccountOpen(l_mainAccount);

       //������ԊǗ�.validate������t�X�e�C�^�X()
       WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();

       // validate����\�ڋq
       // �����F
       //  �ڋq�F�@@�⏕����.getMainAccount()
       //  isPTS�����J�݁F isPTS�����J��()�̖߂�l
       //�s��R�[�h�F�@@���N�G�X�g.�s��R�[�h
       OrderValidationResult l_validationResult =
           this.validateAccountForTrading(
               l_mainAccount,
               l_blnIsPTSAccountEstablished,
               l_sellInputRequest.marketCode);

       if (l_validationResult.getProcessingResult().isFailedResult())
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               l_validationResult.getProcessingResult().getErrorInfo(),
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }

       // get�s��ǌx���s��
       // �����F
       //  ���X�F�@@�⏕����.get����X( )
       //  isPTS�����J�݁F�@@isPTS�����J��()�̖߂�l
       String[] l_strTradeCloseMarkets = this.getTradeCloseMarket(l_branch, l_blnIsPTSAccountEstablished);

       //--------------------
       //validate�����R�[�h
       //--------------------
       String l_strInstitutionCode;
       long l_lngProductId;
       EqTypeProduct l_eqtypeProduct = null;
       try
       {  
           l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
           l_lngProductId = l_asset.getProduct().getProductId();
           l_eqtypeProduct = (EqTypeProduct)l_productManager.getProduct(l_lngProductId);
       }
       catch (NotFoundException l_nfe)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }
       String l_strProductCode = l_eqtypeProduct.getProductCode();
       WEB3EquityProduct l_product
            = l_orderManager.validateProductCode(l_strProductCode, l_strInstitutionCode);

       //--------------------
       //get�戵�\�s��
       //--------------------
       // ���X�F�@@�⏕����.get����X() 
       // isPTS�����J�݁F�@@ isPTS�����J��()�̖߂�l
       String[] l_strMarketCodeList = this.getHandlingPossibleMarket(l_branch, l_blnIsPTSAccountEstablished);

       //0�����̓G���[
       if (l_strMarketCodeList == null || l_strMarketCodeList.length == 0)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00643,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }
       int l_iMarketListCnt = l_strMarketCodeList.length;
        

       //--------------------
       //�擾�������X�Ŏ�舵���\�Ȏs��R�[�h�����A�J��Ԃ�
       //--------------------
       WEB3GentradeMarket l_market;
       WEB3EquityTradedProduct l_wkTradedProduct;
       final boolean l_blnIsSell = true;                    //is���t(���Ȃ̂�true)
       
       TreeMap l_treeMapMarketCodeList = new TreeMap();
       TreeMap l_treeMapOrderBizDateList = new TreeMap();
       Integer l_iWkMarketCode = null;
       Date l_datWkOrderBizDate = null;
       
       for (int i = 0; i < l_iMarketListCnt; i++)
       {
           //reset�s��R�[�h
           WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCodeList[i]);
           try
           {
               //validate�������
               l_market = (WEB3GentradeMarket) l_finObjectManager
                   .getMarket(l_strInstitutionCode, l_strMarketCodeList[i]);
               l_wkTradedProduct = (WEB3EquityTradedProduct) l_orderManager
                   .validateTradedProduct(l_product, l_market);
               
           }
           catch (NotFoundException l_nfe)
           {
               //�s��̎擾�ŃG���[���o���ꍇ
               log.exiting(STR_METHOD_NAME);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME);
           }
           catch (WEB3BaseException l_wbe)
           {
               //��O���������Ȃ����̂̂݃��X�|���X�ւ̐ݒ�ΏۂƂ���B
               continue;
           }
           try
           {
               l_iWkMarketCode = new Integer(l_strMarketCodeList[i]);
           }
           catch (NumberFormatException l_nfe)
           {
               log.exiting(STR_METHOD_NAME);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                   this.getClass().getName() + "." + STR_METHOD_NAME);
           }

           // �s��.isPTS�s��()==false�̏ꍇ
           if (!l_market.isPTSMarket())
           {
               //get������
               l_datWkOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
           }
           //TreeMap�ɒǉ�����
           l_treeMapMarketCodeList.put(l_iWkMarketCode, l_strMarketCodeList[i]);
           l_treeMapOrderBizDateList.put(l_iWkMarketCode, l_datWkOrderBizDate);

       }
       //0�����̓G���[
       if(l_treeMapMarketCodeList.size()==0){
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00643,
				this.getClass().getName() + "." + STR_METHOD_NAME);
       }
       

       //--------------------
       //����t���[ ���N�G�X�g.�s��R�[�h��null�̏ꍇ
       //���N�G�X�g�D�s��R�[�h�̃`�F�b�N
       //--------------------
       // TreeMap��set��iterator�̐ݒ�
       Set stKeyMapMarketCodeList = l_treeMapMarketCodeList.entrySet();
       Set stKeyMapOrderBizDateList = l_treeMapOrderBizDateList.entrySet();
       Iterator l_iteMapMarketCodeList = stKeyMapMarketCodeList.iterator();
       Iterator l_iteMapOrderBizDateList = stKeyMapOrderBizDateList.iterator();
       
       Object l_objMapMarketCodeList = null;
       Map.Entry l_entMapMarketCodeList = null;
       boolean l_blnIsHandingMarket = false;
       String l_valMarketCode = null;
       
       if (l_sellInputRequest.marketCode != null)
       {           
           while (l_iteMapMarketCodeList.hasNext())
           {
               l_objMapMarketCodeList = l_iteMapMarketCodeList.next();
               l_entMapMarketCodeList = (Map.Entry)l_objMapMarketCodeList;
               
               l_valMarketCode = (String)l_entMapMarketCodeList.getValue();
               
               if (l_sellInputRequest.marketCode.equals(l_valMarketCode))
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
        

       //--------------------
       //����t���[ ���N�G�X�g.�s��R�[�h��null�̏ꍇ
       //�w��s��Ŕ��t�Ώۂ̖������戵�\���ǂ����̃`�F�b�N���s��
       //--------------------
       WEB3EquityTradedProduct l_tradedProduct = null;
        
       String[] l_orderPriceDivList = null;                        //�����P���敪�ꗗ
        
       double l_dblCurrentPrice = 0;                               //����
       Timestamp l_tsCurrentPriceTime = null;                     //���ݒl����
       double l_dblChange = 0;                                    //�O����
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
       if (l_sellInputRequest.marketCode != null)
       {
           //reset�s��R�[�h
           WEB3GentradeTradingTimeManagement.resetMarketCode(l_sellInputRequest.marketCode);

           try
           {
               //get�������
               l_tradedProduct = (WEB3EquityTradedProduct) l_productManager.getTradedProduct(
                   l_subAccount.getInstitution(), l_product.getProductCode(), l_sellInputRequest.marketCode);
           }
           catch (NotFoundException l_nfe)
           {
               log.exiting(STR_METHOD_NAME);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME);
           }
           
           //get�����P���敪�ꗗ
           l_orderPriceDivList = l_orderManager.getOrderPriceDivs(l_branch, l_tradedProduct);

           //�����A���ݒl�����A�O������擾
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
        

       //--------------------
       //�ȉ������E�敪���擾
       //--------------------
       WEB3GentradeHandlingOrderCond l_gentradeHandingOrderCond    = null;
       String[] l_handingPossibleExecCond                          = null;
       String[] l_handingPossibleOrderCond                         = null;
       String[] l_handingPossiblePriceCond                         = null;
       String[] l_handingPossibleExpirationDateType                = null;
       
           
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

       //TreeMap�̐ݒ�
       Object l_objMapOrderBizDateList = null;
       Map.Entry l_entMapOrderBizDateList = null;
       Date l_valOrderBizDate;

       //--------------------
       //�o����܂Œ����J�n���^�I�����^�����������j���ꗗ�擾
       //--------------------
       Date l_datOrderUntilDeadLineStartDate       = null;     //�o����܂Œ����J�n��
       Date l_datOrderUntilDeadLineEndDate         = null;     //�o����܂Œ����I����
       Date[] l_datOrderUntilDeadLineHolidayList   = null;     //�����������j���ꗗ

       //����t���[ �戵�\��������.is�ł���܂Œ����戵�\() == true�̏ꍇ
       //�� �戵�\���������́u�����������v�̎��̂ݎ擾
       if (l_gentradeHandingOrderCond.isOrderUntilDeadLinePossibleHandling())
       {
           boolean l_blnIsPTSMarket = false;
           // ���N�G�X�g.�s��R�[�h�I��null�̏ꍇ
           if (l_sellInputRequest.marketCode != null)
           {
               // get�s��
               // ����:
               //  �،���ЃR�[�h:�⏕����.get�،����.get�،���ЃR�[�h()
               //  �s��R�[�h�F ���N�G�X�g.�s��R�[�h
               try
               {
                   l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                       l_strInstitutionCode, l_sellInputRequest.marketCode);

                   // ���N�G�X�g.�s��R�[�h != null ���� �s�� .isPTS�s��()==false�̏ꍇ
                   l_blnIsPTSMarket = l_market.isPTSMarket();
                   if (!l_blnIsPTSMarket)
                   {
                       l_datOrderUntilDeadLineFrom = null;
                   }
               }
               catch (NotFoundException l_notFoundException)
               {
                   log.error("�e�[�u���ɊY������f�[�^������܂���B", l_notFoundException);
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                       getClass().getName() + "." + STR_METHOD_NAME,
                       l_notFoundException.getMessage(),
                       l_notFoundException);
               }
           }
           // reset�s��R�[�h
           WEB3GentradeTradingTimeManagement.resetMarketCode(l_sellInputRequest.marketCode);

           // �i���N�G�X�g.�s��R�[�h==null�j �܂���
           // �i���N�G�X�g.�s��R�[�h != null ���� �s�� .isPTS�s��()==true�j�̏ꍇ
           if (l_sellInputRequest.marketCode == null
               || (l_sellInputRequest.marketCode != null && l_blnIsPTSMarket))
           {
               while (l_iteMapOrderBizDateList.hasNext())
               {
                   l_objMapOrderBizDateList = l_iteMapOrderBizDateList.next();
                   l_entMapOrderBizDateList = (Map.Entry)l_objMapOrderBizDateList;
                   l_valOrderBizDate = (Date)l_entMapOrderBizDateList.getValue();

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
                   l_datOrderUntilDeadLineFrom = WEB3DateUtility.toDay(l_genBizDate.roll(1));
               }
           }

           // get�o����܂Œ����J�n��
           l_datOrderUntilDeadLineStartDate = l_gentradeHandingOrderCond.getOrderUntilDeadLineStartDay(
               l_datOrderUntilDeadLineFrom);

           // get�o����܂Œ����ŏI��
           l_datOrderUntilDeadLineEndDate = l_gentradeHandingOrderCond.getOrderUntilDeadLineEndDay(
               l_datOrderUntilDeadLineFrom);

           // get�����������j���ꗗ
           l_datOrderUntilDeadLineHolidayList = l_gentradeHandingOrderCond.getExpirationDateHoliday(
               l_datOrderUntilDeadLineFrom);
       }

       //get�v�w�l�p���s�����ꗗ(String[], String[])
       String[] l_strWLimitExecutionConditionTypeList = null;
       l_strWLimitExecutionConditionTypeList =
           WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(
               l_handingPossibleExecCond,
               l_handingPossibleOrderCond);

       //--------------------
       //get�T�Z�뉿�P��
       //--------------------
       String l_strEstimatedBookPrice
           = this.getEstimatedBookPrice(l_sellInputRequest);
        

       //--------------------
       //is��������J��
       //--------------------

       if (l_asset.getTaxType().equals(TaxTypeEnum.SPECIAL)
           || l_asset.getTaxType().equals(TaxTypeEnum.SPECIAL_WITHHOLD))
       {
           boolean l_isSpecialAccountEstablished = true;
           if (l_sellInputRequest.marketCode != null)
           {
               l_isSpecialAccountEstablished = l_mainAccount.isSpecialAccountEstablished(l_tradedProduct.getDailyDeliveryDate(), l_subAccount);
           }
           else
           {
               l_isSpecialAccountEstablished = l_mainAccount.isSpecialAccountEstablished(l_subAccount);
           }
           if (!l_isSpecialAccountEstablished)
           {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00026,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
           }    
       }

       //--------------------
       //is�C���T�C�_�[�x���\��
       //--------------------
       boolean l_blnIsInsiderMessageSuspension
           = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_lngProductId);
        
        
       //--------------------
       //validate�ڋq�����ʎ����~
       //--------------------
       // ����:
       //  �⏕�����F�@@this.get�⏕�����i�j�̖߂�l 
       //  ����ID�F�@@�ۗL���Y.����ID 
       //  isPTS�����J�݁F�@@isPTS�����J��()�̖߂�l 
       //�s��R�[�h�F�@@���N�G�X�g.�s��R�[�h
       this.validateAccountProductOrderStop(
           l_subAccount, l_lngProductId, l_blnIsPTSAccountEstablished, l_sellInputRequest.marketCode);
       

       //--------------------
       //create���X�|���X
       //--------------------
       WEB3EquitySellInputResponse l_response
           = (WEB3EquitySellInputResponse) l_sellInputRequest.createResponse();
           
       
       //--------------------
       //�v���p�e�B�Z�b�g
       //--------------------
       String[] l_strWkPriceDivList        = {
           WEB3OrderPriceDivDef.MARKET_PRICE, WEB3OrderPriceDivDef.LIMIT_PRICE};
                
       if (l_sellInputRequest.marketCode != null)
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
       
       // this.get�s��ǌx���s��()�̖߂�l�z����Z�b�g
       l_response.messageSuspension        = l_strTradeCloseMarkets;
       l_response.insiderWarningFlag       = l_blnIsInsiderMessageSuspension;
       
       l_response.currentPriceDiv = l_strCurrentPriceDiv;
       
       if (l_sellInputRequest.marketCode != null)
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
       }
       
       l_response.currentPriceTime         = l_tsCurrentPriceTime;
       
       EqtypeProductRow l_validateProductRow = (EqtypeProductRow)l_product.getDataSourceObject();
       l_response.productCode              = l_validateProductRow.getProductCode();
       l_response.productName              = l_validateProductRow.getStandardName();
       
       // this.get�戵�\�s��( )�̖߂�l�z��
       l_response.marketList               = (String[]) l_treeMapMarketCodeList.values().toArray(
                                               new String[l_treeMapMarketCodeList.size()]);
       l_response.taxType = WEB3EquityDataAdapter.getTaxType(l_asset.getTaxType());
       
       double l_dbQuantity                   = l_asset.getQuantity() - l_asset.getLockedQuantity();
       l_response.orderQuantity            = WEB3StringTypeUtility.formatNumber(l_dbQuantity);
       l_response.estimatedBookPrice       = l_strEstimatedBookPrice;
       l_response.expirationDateTypeList   = l_handingPossibleExpirationDateType;
       l_response.orderCondTypeList        = l_handingPossibleOrderCond;
       
       
       log.exiting(STR_METHOD_NAME);
       return l_response;
    }
   
   /**
    * (get�ۗL���Y)<BR>
    * �ۗL���Y�I�u�W�F�N�g���擾����B<BR>
    * <BR>
    * �����|�W�V�����}�l�[�W��.getAsset(���N�G�X�g.ID�i�����YID�j)��delegate����B<BR>
    * @@param l_request - ���N�G�X�g�f�[�^
    * @@return Asset
    * @@throws WEB3BaseException
    */
   protected Asset getAsset(
       WEB3EquitySellInputRequest l_request)
       throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "getAsset(WEB3EquitySellInputRequest)";
       log.entering(STR_METHOD_NAME);
        
       FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
       TradingModule l_tradingModule =
           l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
       WEB3EquityPositionManager l_positionManager =
           (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
       Asset l_asset = null;
       try
       {
           l_asset = l_positionManager.getAsset(Long.parseLong(l_request.id));
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
       return l_asset;
   }
   
   /**
    * (get�T�Z�뉿�P��)<BR>
     * �T�Z�뉿�P�����擾���Ԃ��B<BR>
     * <BR>
     * �P�j�@@�ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�����|�W�V�����}�l�[�W��.getAsset(�����̃��N�G�X�g.ID)���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����v�Z�T�[�r�X.calc�T�Z�뉿�P��(�ۗL���Y.����ID, get�⏕����(), �ۗL���Y.�ŋ敪)���R�[�����A<BR>
     * �@@�@@�@@�߂�l��ԋp����B<BR>
    * @@param l_request - ���N�G�X�g�f�[�^
    * @@return String
    * @@throws WEB3BaseException
    */
   protected String getEstimatedBookPrice(
       WEB3EquitySellInputRequest l_request)
       throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "getEstimatedBookPrice(WEB3EquitySellInputRequest)";
       log.entering(STR_METHOD_NAME);
        
       FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
       TradingModule l_tradingModule =
           l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
       WEB3EquityPositionManager l_positionManager =
           (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
       Asset l_asset = null;
       try
       {
           l_asset = l_positionManager.getAsset(Long.parseLong(l_request.id));
       }
       catch (NotFoundException l_nfe)
       {
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_nfe.getMessage(),
               l_nfe);
       }
       WEB3EquityBizLogicProvider l_bizLogicProvider =
           (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
       double l_dblEstimatedBookPrice =
           l_bizLogicProvider.calcEstimatedBookPrice(
               l_asset.getProduct().getProductId(),
               this.getSubAccount(),
               l_asset.getTaxType());
       String l_strEstimatedBookPrice =
           WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        
       log.exiting(STR_METHOD_NAME);
       return l_strEstimatedBookPrice;
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
    * �@@�@@�@@�@@�@@�@@�s��R�[�h�̔z����擾����<BR>
    * <BR>
    * �@@�@@[PTS������ԊǗ�.get�s��ǌx���s��()�ɃZ�b�g�������]<BR>
    * �@@�@@���X�F�@@����.���X<BR>
    * �@@�@@�����^�C�v�F�@@"����"<BR>
    * �@@�@@�M�p����敪�F�@@"DEFAULT" <BR>
    * <BR>
    * �@@�@@�Q�|�Q�j�P�j�̌��ʂƁ@@�Q�|�P�j�̌��ʂ��}�[�W���Ďs��R�[�h�����Ń\�[�g����B<BR>
    * <BR>
    * �R�j�擾�����s��R�[�h�̔z���ԋp����B<BR>
    * @@param l_branch - (���X)<BR>
    * ���X�I�u�W�F�N�g
    * @@param l_blnIsPTSAccountEstablished - (isPTS�����J��)<BR>
    * isPTS�����J��
    * @@return String[]
    * @@throws WEB3BaseException
    */
   protected String[] getTradeCloseMarket(
       WEB3GentradeBranch l_branch,
       boolean l_blnIsPTSAccountEstablished)throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "getTradeCloseMarket(WEB3GentradeBranch, boolean)";
       log.entering(STR_METHOD_NAME);

       // �P�j������ԊǗ�.get�s��ǌx���s��()���R�[�����A�s��R�[�h�̔z����擾����B
         //[������ԊǗ�.get�s��ǌx���s��()�ɃZ�b�g�������]
         //�@@�@@���X�F�@@����.���X
         //�@@�@@�����^�C�v�F�@@"����"
         //�@@�@@�M�p����敪�F�@@"DEFAULT"

       String[] l_strTradeCloseMarkets = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
           l_branch, ProductTypeEnum.EQUITY, WEB3MarginTradingDivDef.DEFAULT);

       // �Q�jPTS�����J�݌ڋq�̏ꍇ(����.isPTS�����J��==true)
       if (l_blnIsPTSAccountEstablished)
       {
           // �Q�|�P�jPTS������ԊǗ�.get�s��ǌx���s��()���R�[�����A�s��R�[�h�̔z����擾����
           String[] l_strPtsTradeCloses = WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
               l_branch, ProductTypeEnum.EQUITY, WEB3MarginTradingDivDef.DEFAULT);
           // �Q�|�Q�j�P�j�̌��ʂƂQ�|�P�j�̌��ʂ��}�[�W���Ďs��R�[�h�����Ń\�[�g����B
           l_strTradeCloseMarkets = this.mergeAndSort(l_strTradeCloseMarkets, l_strPtsTradeCloses);
       }

       log.exiting(STR_METHOD_NAME);
       // �R�j�擾�����s��R�[�h�̔z���ԋp����B
       return l_strTradeCloseMarkets;
   }

   /**
    * (get�戵�\�s��)<BR>
    * �戵�\�s����擾����B<BR>
    * PTS�����J�݌ڋq�̏ꍇ�APTS�̎戵�\�s����擾����B<BR>
    * <BR>
    * �P�j(���X�s���)�戵����.get�戵�\�s��()���R�[�����A�s��R�[�h�̔z����擾����B<BR>
    * <BR>
    * �@@�@@[(���X�s���)�戵����.get�戵�\�s��()�ɃZ�b�g�������] <BR>
    * �@@�@@���X�F�@@����.���X<BR>
    * �@@�@@�����^�C�v�F�@@"����"<BR>
    * <BR>
    * �Q�jPTS�����J�݌ڋq�̏ꍇ(����.isPTS�����J��==true)<BR>
    * <BR>
    * �@@�@@�Q�|�P�j(���X�s��ʁEPTS)�戵����.get�戵�\�s��()���R�[�����A<BR>
    * �@@�@@�@@�@@�@@�@@�s��R�[�h�̔z����擾����B<BR>
    * <BR>
    * �@@�@@[(���X�s��ʁEPTS)�戵����.get�戵�\�s��()�ɃZ�b�g�������]<BR>
    * �@@�@@���X�F�@@����.���X<BR>
    * �@@�@@�����^�C�v�F�@@"����"<BR>
    * <BR>
    * �@@�@@�Q�|�Q�j�P�j�̌��ʂƁ@@�Q�|�P�j�̌��ʂ��}�[�W���Ďs��R�[�h�����Ń\�[�g����B <BR>
    * <BR>
    * �R�j�擾�����s��R�[�h�̔z���ԋp����B<BR>
    * @@param l_branch - (���X)<BR>
    * ���X�I�u�W�F�N�g
    * @@param l_blnIsPTSAccountEstablished - (isPTS�����J��)<BR>
    * isPTS�����J��
    * @@return String[]
    * @@throws WEB3BaseException
    */
   protected String[] getHandlingPossibleMarket(
       WEB3GentradeBranch l_branch,
       boolean l_blnIsPTSAccountEstablished)throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "getHandlingPossibleMarket(WEB3GentradeBranch, boolean)";
       log.entering(STR_METHOD_NAME);

       // �P�j(���X�s���)�戵����.get�戵�\�s��()���R�[�����A�s��R�[�h�̔z����擾����B
       //[(���X�s���)�戵����.get�戵�\�s��()�ɃZ�b�g�������]
       //  ���X�F�@@����.���X
       //  �����^�C�v�F�@@"����"
       String[] l_strHandlingPossibleMarkets = WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
           l_branch, ProductTypeEnum.EQUITY);

       // �Q�jPTS�����J�݌ڋq�̏ꍇ(����.isPTS�����J��==true)
       if (l_blnIsPTSAccountEstablished)
       {
           // �Q�|�P�j(���X�s��ʁEPTS)�戵����.get�戵�\�s��()���R�[�����A�s��R�[�h�̔z����擾����B
           // [(���X�s��ʁEPTS)�戵����.get�戵�\�s��()�ɃZ�b�g�������]
           // ���X�F�@@����.���X
           // �����^�C�v�F�@@"����"

           String[] l_strPtsPossibleMarkets = WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
               l_branch, ProductTypeEnum.EQUITY);

           // �Q�|�Q�j�P�j�̌��ʂƂQ�|�P�j�̌��ʂ��}�[�W���Ďs��R�[�h�����Ń\�[�g����B
           l_strHandlingPossibleMarkets = this.mergeAndSort(l_strHandlingPossibleMarkets, l_strPtsPossibleMarkets);
       }

       log.exiting(STR_METHOD_NAME);
       // �R�j�擾�����s��R�[�h�̔z���ԋp����B
       return l_strHandlingPossibleMarkets;
   }

   /**
    * (isPTS�����J��)<BR>
    * �ڋq.isPTS�����J�݂��R�[�����A���ʂ�ԋp���� <BR>
    * <BR>
    * �P�j����.�ڋq�I�u�W�F�N�g��isPTS�����J��()���R�[�����A���ʂ�ԋp����B<BR>
    * @@param l_mainAccount - (�ڋq)<BR>
    * �ڋq�I�u�W�F�N�g
    * @@return boolean
    * @@throws WEB3BaseException
    */
   protected boolean isPTSAccountOpen(
       WEB3GentradeMainAccount l_mainAccount)throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "isPTSAccountOpen(WEB3GentradeMainAccount)";
       log.entering(STR_METHOD_NAME);

       if (l_mainAccount == null)
       {
         log.debug("�p�����[�^�l�s���B");
         log.exiting(STR_METHOD_NAME);
         throw new WEB3SystemLayerException(
             WEB3ErrorCatalog.SYSTEM_ERROR_80017,
             this.getClass().getName() + "." + STR_METHOD_NAME,
             "�p�����[�^�l�s���B");
       }

       // �P�j����.�ڋq�I�u�W�F�N�g��isPTS�����J��()���R�[�����A���ʂ�ԋp����B
       log.exiting(STR_METHOD_NAME);
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
       WEB3GentradeMainAccount l_mainAccount, boolean l_blnIsPTSAccountOpen, String l_strMarketCode)
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
    * �@@�@@�@@������ʁF�@@EQUITY_SELL�i�����������j<BR>
    * <BR>
    * �@@�Q�|�Q�j�@@�Q�|�P�j�ȊO�̏ꍇ<BR>
    * <BR>
    * �@@�@@�@@�g�����������}�l�[�W��.validate�ڋq�����ʎ����~()���R�[������B<BR>
    * <BR>
    * �@@�@@�@@[�����̐ݒ�]<BR>
    * �@@�@@�@@�⏕�����F�@@����.�⏕����<BR>
    * �@@�@@�@@����ID�F�@@����.����ID<BR>
    * �@@�@@�@@������ʁF�@@EQUITY_SELL�i�����������j<BR>
    * <BR>
    * @@param l_subAccount - (�⏕����)<BR>
    * �⏕�����I�u�W�F�N�g<BR>
    * @@param l_lngProductId - (����ID)<BR>
    * ����ID<BR>
    * @@param l_blnIsPTSAccountOpen - (isPTS�����J��)<BR>
    * isPTS�����J��<BR>
    * @@param l_strMarketCode - (�s��R�[�h)<BR>
    * �s��R�[�h<BR>
    * @@throws WEB3BaseException
    */
   protected void validateAccountProductOrderStop(
       WEB3GentradeSubAccount l_subAccount,
       long l_lngProductId,
       boolean l_blnIsPTSAccountOpen,
       String l_strMarketCode) throws WEB3BaseException
   {
       final String STR_METHOD_NAME =
           "validateAccountProductOrderStop(WEB3GentradeSubAccount, long, boolean, String)";
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
           //������ʁF�@@EQUITY_SELL�i�����������j
           l_orderPTSManager.validatePTSAccountProductOrderStop(
               l_subAccount, l_lngProductId, OrderTypeEnum.EQUITY_SELL);
       }
       else
       {
           //�@@�Q�|�Q�j�@@�Q�|�P�j�ȊO�̏ꍇ
           //�g�����������}�l�[�W��.validate�ڋq�����ʎ����~()���R�[������B
           //[�����̐ݒ�]
           //�⏕�����F�@@����.�⏕����
           //����ID�F�@@����.����ID
           //������ʁF�@@EQUITY_SELL�i�����������j
           l_orderPTSManager.validateAccountProductOrderStop(
               l_subAccount, l_lngProductId, OrderTypeEnum.EQUITY_SELL);
       }
       log.exiting(STR_METHOD_NAME);
   }

   /**
    * ��̔z�����̔z��ɍ������āA�����\�[�g�ŕԋp����
    * @@param l_strArraySrcs
    * @@param l_strDests
    * @@return String[]
    */
   private String[] mergeAndSort(String[] l_strArraySrcs, String[] l_strDests)
    {
        final String STR_METHOD_NAME = "mergeAndSort(String[], String[])";
        log.entering(STR_METHOD_NAME);

        Object[] l_objMergeArrays = WEB3Toolkit.merge(l_strArraySrcs, l_strDests);
        l_objMergeArrays = WEB3Toolkit.toUnique(l_objMergeArrays);

        if (l_objMergeArrays == null || l_objMergeArrays.length == 0)
        {
            return null;
        }

        int l_intMergeArrayCnt = l_objMergeArrays.length;
        String[] l_strResults = new String[l_intMergeArrayCnt];
        for (int i = 0; i < l_intMergeArrayCnt; i++)
        {
            l_strResults[i] = (String)l_objMergeArrays[i];
        }

        int l_intResultsCnt = l_strResults.length;
        String l_strTemp = null;
        for (int i = 0; i < l_intResultsCnt; i++)
        {
            for (int j = i + 1; j < l_intResultsCnt; j++)
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
