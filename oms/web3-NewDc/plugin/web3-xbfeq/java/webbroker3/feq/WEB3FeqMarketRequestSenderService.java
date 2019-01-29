head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqMarketRequestSenderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式市場リクエスト送信サービス(WEB3FeqMarketRequestSenderService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  艾興(中訊) 新規作成
                   2005/07/26 王亞洲(中訊) レビュー
                 　@                   2006/09/18  黄建(中訊) 仕様変更・モデル237、238 、239    
                   2006/10/08  齊珂(中訊) 仕様変更・ＤＢ更新仕様 067 
                   2006/10/12 徐宏偉(中訊) バグ3074の対応
                   2006/11/22 徐大方(中訊)仕様変更・ＤＢ更新仕様 075
                   2006/12/04 李 俊(中訊) 仕様変更・ モデル 308 309 ＤＢ更新仕様 076
                   2007/01/09 徐大方(中訊)仕様変更・ モデル 328
*/
package webbroker3.feq;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqMarketRequestSenderService;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.market.messages.FeqChangeOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.market.messages.FeqNewOrderMarketRequestMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.feq.service.delegate.WEB3FeqMailSenderService;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.slebase.data.SleSendQDao;
import webbroker3.slebase.data.SleSendQParams;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式市場リクエスト送信サービス) <BR>
 * 外国株式市場リクエスト送信サービス
 * 
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3FeqMarketRequestSenderService implements FeqMarketRequestSenderService 
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3FeqMarketRequestSenderService.class);

    
    /**
     * @@roseuid 42D0D3CE0148
     */
    public WEB3FeqMarketRequestSenderService() 
    {
     
    }
    
    /**
     * (新規注文送信) <BR>
     * （send(FeqNewOrderMarketRequestMessage)の実装） <BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（市場リクエスト）新規注文送信」 参照 <BR>
     * @@param l_feqNewOrderRequest - (新規注文リクエストメッセージ)
     * @@return MarketRequestSendResult
     * @@roseuid 4284571702A8
     */
    public MarketRequestSendResult send(FeqNewOrderMarketRequestMessage l_feqNewOrderRequest) 
    {
        final String STR_METHOD_NAME = "send(FeqNewOrderMarketRequestMessage l_feqNewOrderRequest) ";
        log.entering(STR_METHOD_NAME);
        if (l_feqNewOrderRequest == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
        
        //1.1 getFeqOrderUnitRow( )
        //注文単位の行オブジェクトを取得する。
        FeqOrderUnitRow l_feqOrderUnitRow = l_feqNewOrderRequest.getFeqOrderUnitRow();
        
        //1.2 toOrderUnit(Row)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        WEB3FeqOrderUnit l_feqOrderUnit = 
            (WEB3FeqOrderUnit)l_orderManager.toOrderUnit(l_feqOrderUnitRow);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //1.3 get市場( )
            WEB3GentradeMarket l_gentradeMarket = l_feqOrderUnit.getMarket();
            
            //1.4 isシステム連動( )
            boolean l_blnIsSystemLock = l_gentradeMarket.isSystemInterLock();
            
            //1.5  （分岐フロー：　@isシステム連動() == false（非連動）の場合）
            if (!l_blnIsSystemLock)
            {
                //1.5.1 create新規注文Mail(FeqOrderUnit)
                WEB3FeqMailSenderService l_mailSenderService = 
                    (WEB3FeqMailSenderService)Services.getService(WEB3FeqMailSenderService.class);
                l_mailSenderService.createNewOrderMail(l_feqOrderUnit);
            }
            
            //1.6 （分岐フロー：　@isシステム連動() == true（連動）の場合）
            else
            {
                //1.6.1 (注文キューデータ登録)
                //注文キューデータの登録を行う。
                //詳細は　@DB更新仕様「新規_外国株取引SEND_Qテーブル」参照
                SleSendQParams l_sleSendQParams = new SleSendQParams();
                
                //キューID: 自動採番した値
                l_sleSendQParams.setQueueId(SleSendQDao.newPkValue());
                
                //銘柄タイプ: 外株注文単位.銘柄タイプ
                l_sleSendQParams.setProductType(l_feqOrderUnit.getProductType());
                
                //市場コード: 外株注文単位.市場ＩＤに該当する市場.市場コード
                l_sleSendQParams.setMarketCode(l_gentradeMarket.getMarketCode());
                
                //ブローカー: 
                //外株注文単位.市場ＩＤに該当する市場プリファ@レンスの「プリファ@レンス項目名」＝
                //"feq.sle.broker"かつ「項目名連番」＝1の「プリファ@レンスの値」
                int l_intValue = 1;
                MarketPreferencesRow l_marketPreferencesRow = null;
                String l_strName = WEB3MarketPreferencesNameDef.FEQ_SLE_BROKER;
                l_marketPreferencesRow = MarketPreferencesDao.findRowByPk(
                    l_gentradeMarket.getMarketId(),
                    l_strName,
                    l_intValue);
                
                l_sleSendQParams.setBrokerName(l_marketPreferencesRow.getValue());
                
                //証券会社コード： 外株注文単位.証券会社コード
                l_sleSendQParams.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());
                
                //部店コード: 外株注文単位.部店コード
                l_sleSendQParams.setBranchCode(l_feqOrderUnit.getBranchCode());
                
                //銘柄コード: 外株注文単位.銘柄ＩＤに該当する外株銘柄.現地銘柄コード
                WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)l_feqOrderUnit.getProduct();
                String l_strProductCode = l_feqProduct.getOffshoreProductCode();
                if (l_strProductCode != null)
                {
                    while (l_strProductCode.startsWith("0"))
                    {
                        l_strProductCode = l_strProductCode.replaceFirst("0", "");
                    }
                }
                l_sleSendQParams.setProductCode(l_strProductCode);
                
                //注文ID: 外株注文単位.注文ID
                l_sleSendQParams.setOrderId(l_feqOrderUnit.getOrderId());
                
                //注文単位ID: 外株注文単位.注文単位ID
                l_sleSendQParams.setOrderUnitId(l_feqOrderUnit.getOrderUnitId());
                
                //発注日: 外株注文単位.発注日
                l_sleSendQParams.setBizDate(l_feqOrderUnit.getBizDate());
                
                //オペレータタイプ: 0:新規(（SleSendqOpTypeEnumにて定義)
                l_sleSendQParams.setOpType(SleSendqOpTypeEnum.NEW_ORDER);
                
                //注文種別: 外株注文単位.注文種別
                l_sleSendQParams.setOrderType(l_feqOrderUnit.getOrderType());
                
                //指値: 外株注文単位.指値
                if (!l_feqOrderUnitRow.getLimitPriceIsNull())
                {
                    l_sleSendQParams.setLimitPrice(l_feqOrderUnit.getLimitPrice());
                }

                //注文数量: 外株注文単位.注文数量
                if(l_feqOrderUnitRow.getQuantityIsSet())
                {
                    l_sleSendQParams.setQuantity(l_feqOrderUnit.getQuantity());
                }

                //アカウントID: 外株注文単位.口座ID
                l_sleSendQParams.setAccountId(l_feqOrderUnit.getAccountId());
                
                //アカウントコード: 外株注文単位.口座IDに該当する顧客.口座コード
                MainAccount l_mainAccount = 
                    l_accountManager.getMainAccount(
                        l_feqOrderUnit.getAccountId());
                l_sleSendQParams.setAccountCode(l_mainAccount.getAccountCode());
                    
                //サブアカウントID: 外株注文単位.補助口座ID:
                l_sleSendQParams.setSubAccountId(l_feqOrderUnit.getSubAccountId());
                
                //処理区分:
                boolean l_blnIsTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null);
                SleSendqProcStatusEnum l_sleSendqProcStatusEnum = null;
                //取引時間管理.isトリガ発行()==trueの場合、0:処理待ち
                if (l_blnIsTrigger)
                {
                    l_sleSendqProcStatusEnum = SleSendqProcStatusEnum.TODO;
                }
                //取引時間管理.isトリガ発行()==falseの場合、7:未送信
                //（SleSendqProcStatusEnumにて定義）
                else
                {
                    l_sleSendqProcStatusEnum = SleSendqProcStatusEnum.NOT_PROCESSED;
                }
                l_sleSendQParams.setStatus(l_sleSendqProcStatusEnum);
                
                //受信確認フラグ: 0:FALSE BooleanEnumにて定義）
                l_sleSendQParams.setConfirmedBySleRcvdQ(BooleanEnum.FALSE);
                
                //運用コード: 外株注文単位.運用コード
                l_sleSendQParams.setOrderEmpCode(l_feqOrderUnit.getOrderEmpCode());
                
                //識別コード: 外株注文単位.識別コード
                l_sleSendQParams.setOrderRequestNumber(
                    l_feqOrderUnitRow.getOrderRequestNumber());
                
                //電子メール送信日時: Null
                l_sleSendQParams.setSendProcessDateTime(null); 
                
                //作成日付: 登録時でsysdate が設定される
                l_sleSendQParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //更新日付: 登録時でsysdate が設定される
                l_sleSendQParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                l_queryProcessor.doInsertQuery(l_sleSendQParams);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
      }
        
      log.exiting(STR_METHOD_NAME);
      return DefaultMarketRequestSendResult.newSuccessResultInstance(0);
      
    }
    
    /**
     * (訂正注文送信) <BR>
     * （send(FeqChangeOrderMarketRequestMessage)の実装） <BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（市場リクエスト）訂正注文送信」 参照 <BR>
     * @@param l_feqChangeOrderRequest - (訂正注文リクエストメッセージ)
     * @@param l_blnIsMarketNoSend - (is市場未送信) <BR>
     *  <BR>
     * 訂正元注文が市場未送信の場合はtrue、 <BR>
     * 訂正元注文が市場送信済の場合はfalseを指定する。  <BR>
     * falseの場合、SONARへ訂正を通知する。  <BR>
     * 
     * @@return MarketRequestSendResult
     * @@roseuid 4284571702AA
     */
    public MarketRequestSendResult send(FeqChangeOrderMarketRequestMessage l_feqChangeOrderRequest, 
        boolean l_blnIsMarketNoSend)
    {
        final String STR_METHOD_NAME = "send(FeqChangeOrderMarketRequestMessage l_feqChangeOrderRequest, " +
                "boolean l_blnIsMarketNoSend)";
        log.entering(STR_METHOD_NAME);
        if (l_feqChangeOrderRequest == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
        
        //1.1 getOrderId( )
        long l_lngOrderId = l_feqChangeOrderRequest.getOrderId();
        
        //1.2 getFeqOrderUnitRow( )
        //注文単位の行オブジェクトを取得する。
        FeqOrderUnitRow l_feqOrderUnitRow = l_feqChangeOrderRequest.getFeqOrderUnitRow();
        
        //1.3 toOrderUnit(Row)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        WEB3FeqOrderUnit l_feqOrderUnit = 
            (WEB3FeqOrderUnit)l_orderManager.toOrderUnit(l_feqOrderUnitRow);
        SleSendQParams l_sleSendQParams = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();    
            
            //1.4 get市場( )
            WEB3GentradeMarket l_gentradeMarket = l_feqOrderUnit.getMarket();
            
            //1.5 isシステム連動( )
            boolean l_blnIsSystemLock = l_gentradeMarket.isSystemInterLock();
            
            //1.6 isトリガ発行(発注条件 : String)
            boolean l_blnIsTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null);
            
            //1.7 （分岐フロー：　@isシステム連動() == false（非連動）の場合）
            if (!l_blnIsSystemLock)
            {
                //1.5.1 create訂正注文Mail(FeqOrderUnit)
                WEB3FeqMailSenderService l_mailSenderService = 
                    (WEB3FeqMailSenderService)Services.getService(WEB3FeqMailSenderService.class);
                l_mailSenderService.createChangeOrderMail(l_feqOrderUnit);
            }
            //1.8 （分岐フロー：　@isシステム連動() == true（連動）の場合）
            else
            {
                //1.8.1 （分岐フロー：　@引数.is市場未送信==true(市場未送信)の場合）
                if (l_blnIsMarketNoSend)
                {
                    //注文キューデータの登録を行う。
                    //詳細は　@DB更新仕様「訂正_外国株取引SEND_Qテーブル.xls」のシート
                    //「訂正_外国株取引SEND_Qテーブル(市場未送信)」参照
                    
                    //○　@注文ID==外株注文単位.注文IDかつオペレータタイプ=="新規"
                    //かつ処理区分=="未送信"データが存在する場合、当該レコードを更新（Update）する。
                    
                    String l_strWhere = " order_id = ? and op_type = ? and status = ? ";
                    Object[] l_objWhere = new Object[3];
                    l_objWhere[0] = l_feqOrderUnit.getOrderId() + "";
                    l_objWhere[1] = SleSendqOpTypeEnum.NEW_ORDER;
                    l_objWhere[2] = SleSendqProcStatusEnum.NOT_PROCESSED;
                    List l_lisSleSendQRow =  
                        l_queryProcessor.doFindAllQuery(
                            SleSendQRow.TYPE,
                            l_strWhere,
                            l_objWhere);
                    if (l_lisSleSendQRow != null && l_lisSleSendQRow.size() != 0)
                    {
                        SleSendQRow l_sleSendQRow = (SleSendQRow)l_lisSleSendQRow.get(0);
                        l_sleSendQParams = new SleSendQParams(l_sleSendQRow);
                       
                        //指値: 外株注文単位.指値
                        if (!l_feqOrderUnitRow.getLimitPriceIsNull())
                        {
                            l_sleSendQParams.setLimitPrice(l_feqOrderUnit.getLimitPrice());
                        }
                        
                        //注文数量: 外株注文単位.注文数量
                        if(l_feqOrderUnitRow.getQuantityIsSet())
                        {
                            l_sleSendQParams.setQuantity(l_feqOrderUnit.getQuantity());
                        } 
                        //更新日付: 現在日時（GtlUtils.getSystemTimestamp()）
                        l_sleSendQParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        int l_updcount = l_queryProcessor.doUpdateQuery(l_sleSendQParams);
                        
                        if (l_updcount == 0)
                        {
                            // 更新行数が0件だった場合、業務エラーとする。
                            log.error("SLE_SEND_Qの該当レコードに対する訂正処理に失敗しました。" + l_sleSendQParams);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_03104, 
                                    getClass().getName() + STR_METHOD_NAME);
                        }
                    }
                    else
                    {
                        // 新規注文かつ市場未送信の注文で、SEND_Qに存在しない事はあり得ないので
                        // 業務エラーとする。
                        log.error("訂正対象の新規注文レコードが、SLE_SEND_Qに存在しません。" +
                            "[ order_id：" + l_feqOrderUnit.getOrderId() + " ]");
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03104, 
                                getClass().getName() + STR_METHOD_NAME);
                    }
                }
                
                //1.8.2 （分岐フロー：　@引数.is市場未送信 == false(市場送信済み)の場合）
                else
                {
                    //1.8.2.1 （注文キューデータ登録）
                    //注文キューデータの登録を行う。
                    //詳細は　@DB更新仕様「訂正_外国株取引SEND_Qテーブル.xls」のシート
                    //「訂正_外国株取引SEND_Qテーブル(市場送信済)」参照
                    l_sleSendQParams = new SleSendQParams();
                    
                    //キューID: 自動採番した値
                    l_sleSendQParams.setQueueId(SleSendQDao.newPkValue());
                    
                    //銘柄タイプ: 外株注文単位.銘柄タイプ
                    l_sleSendQParams.setProductType(l_feqOrderUnit.getProductType());
                    
                    //市場コード: 外株注文単位.市場ＩＤに該当する市場.市場コード
                    l_sleSendQParams.setMarketCode(l_gentradeMarket.getMarketCode());
                    
                    //ブローカー: 
                    //外株注文単位.市場ＩＤに該当する市場プリファ@レンスの「プリファ@レンス項目名」＝
                    //"feq.sle.broker"かつ「項目名連番」＝1の「プリファ@レンスの値」
                    int l_intValue = 1;
                    MarketPreferencesRow l_marketPreferencesRow = null;
                    String l_strName = WEB3MarketPreferencesNameDef.FEQ_SLE_BROKER;
                    l_marketPreferencesRow = MarketPreferencesDao.findRowByPk(
                        l_gentradeMarket.getMarketId(),
                        l_strName,
                        l_intValue);
                    l_sleSendQParams.setBrokerName(l_marketPreferencesRow.getValue());
                    
                    //証券会社コード： 外株注文単位.証券会社コード
                    l_sleSendQParams.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());
                    
                    //部店コード: 外株注文単位.部店コード
                    l_sleSendQParams.setBranchCode(l_feqOrderUnit.getBranchCode());
                    
                    //銘柄コード: 外株注文単位.銘柄ＩＤに該当する外株銘柄.現地銘柄コード
                    WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)l_feqOrderUnit.getProduct();
                    String l_strProductCode = l_feqProduct.getOffshoreProductCode();
                    if (l_strProductCode != null)
                    {
                        while (l_strProductCode.startsWith("0"))
                        {
                            l_strProductCode = l_strProductCode.replaceFirst("0", "");
                        }
                    }
                    l_sleSendQParams.setProductCode(l_strProductCode);
                    //注文ID: 外株注文単位.注文ID
                    l_sleSendQParams.setOrderId(l_feqOrderUnit.getOrderId());
                    
                    //注文単位ID: 外株注文単位.注文単位ID
                    l_sleSendQParams.setOrderUnitId(l_feqOrderUnit.getOrderUnitId());
                    
                    //発注日: 外株注文単位.発注日
                    l_sleSendQParams.setBizDate(l_feqOrderUnit.getBizDate());
                    
                    //オペレータタイプ: 1:訂正(（SleSendqOpTypeEnumにて定義)
                    l_sleSendQParams.setOpType(SleSendqOpTypeEnum.CHANGE_ORDER);
                    
                    //注文種別: 外株注文単位.注文種別
                    l_sleSendQParams.setOrderType(l_feqOrderUnit.getOrderType());
                    
                    //指値: 外株注文単位.指値
                    if (!l_feqOrderUnitRow.getLimitPriceIsNull())
                    {
                        l_sleSendQParams.setLimitPrice(l_feqOrderUnit.getLimitPrice());
                    }
    
                    //注文数量: 外株注文単位.市場から確認済みの数量
                    if(l_feqOrderUnitRow.getQuantityIsSet())
                    {
                        l_sleSendQParams.setQuantity(l_feqOrderUnit.getQuantity());
                    }
                    
                    //訂正数量: 外株注文単位.市場から確認済みの数量
                    if(!l_feqOrderUnitRow.getConfirmedQuantityIsNull())
                    {
                        l_sleSendQParams.setChangeQuantity(l_feqOrderUnit.getConfirmedQuantity());
                    }
                    
                    //訂正指値: 外株注文単位.市場から確認済みの指値
                    if (!l_feqOrderUnitRow.getConfirmedPriceIsNull())
                    {
                        l_sleSendQParams.setChangeLimitPrice(l_feqOrderUnit.getConfirmedPrice());  
                    }
                   
                    //約定数量: 外株注文単位.約定数量
                    if (!l_feqOrderUnitRow.getExecutedQuantityIsNull())
                    {
                        l_sleSendQParams.setAlreadyExecdQuantity(l_feqOrderUnitRow.getExecutedQuantity());
                    }
                    
                    //アカウントID: 外株注文単位.口座ID
                    l_sleSendQParams.setAccountId(l_feqOrderUnit.getAccountId());
                    
                    //アカウントコード: 外株注文単位.口座IDに該当する顧客.口座コード
                    MainAccount l_mainAccount = 
                        l_accountManager.getMainAccount(
                            l_feqOrderUnit.getAccountId());
                    l_sleSendQParams.setAccountCode(l_mainAccount.getAccountCode());
                        
                    //サブアカウントID: 外株注文単位.補助口座ID:
                    l_sleSendQParams.setSubAccountId(l_feqOrderUnit.getSubAccountId());
                    
                    //処理区分:
                    SleSendqProcStatusEnum l_sleSendqProcStatusEnum = null;
                    //取引時間管理.isトリガ発行()==trueの場合、0:処理待ち
                    if (l_blnIsTrigger)
                    {
                        l_sleSendqProcStatusEnum = SleSendqProcStatusEnum.TODO;
                    }
                    //取引時間管理.isトリガ発行()==falseの場合、7:未送信
                    //（SleSendqProcStatusEnumにて定義）
                    else
                    {
                        l_sleSendqProcStatusEnum = SleSendqProcStatusEnum.NOT_PROCESSED;
                    }
                    l_sleSendQParams.setStatus(l_sleSendqProcStatusEnum);
                    
                    //受信確認フラグ: 0:FALSE BooleanEnumにて定義）
                    l_sleSendQParams.setConfirmedBySleRcvdQ(BooleanEnum.FALSE);
                    
                    //運用コード: 外株注文単位.運用コード
                    l_sleSendQParams.setOrderEmpCode(l_feqOrderUnit.getOrderEmpCode());
                    
                    //識別コード: 外株注文単位.識別コード
                    l_sleSendQParams.setOrderRequestNumber(
                        l_feqOrderUnitRow.getOrderRequestNumber());
                    
                    //電子メール送信日時: Null
                    l_sleSendQParams.setSendProcessDateTime(null);
                    
                    //作成日付: 登録時でsysdate が設定される
                    l_sleSendQParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                    //更新日付: 登録時でsysdate が設定される
                    l_sleSendQParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                    l_queryProcessor.doInsertQuery(l_sleSendQParams);
                }
                
                //1.9 （分岐フロー：　@is市場開局時間帯()の戻り値 == false  
                //かつ引数.is市場未送信 == falseの場合）
                boolean l_blnIsTradeOpenTimeZone =
                    WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
                if (!l_blnIsMarketNoSend && !l_blnIsTradeOpenTimeZone)
                {
                    //1.9.1 訂正確定(long)
                    this.changeConfirm(l_lngOrderId);
                }
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
      }
      
      log.exiting(STR_METHOD_NAME);
      return DefaultMarketRequestSendResult.newSuccessResultInstance(0);
  }
    
    /**
     * (取消注文送信) <BR>
     * （send(CancelOrderMarketRequestMessage)の実装） <BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（市場リクエスト）取消注文送信」 参照 <BR>
     * @@param l_cancelOrderRequest - (取消注文リクエストメッセージ)
     * @@param l_blnIsMarketNoSend - (is市場未送信) <BR>
     *  <BR>
     * 取消元注文が市場未送信の場合はtrue、 <BR>
     * 取消元注文が市場送信済の場合はfalseを指定する。  <BR>
     * falseの場合、SONARへ取消を通知する。  <BR>
     * 
     * @@return MarketRequestSendResult
     * @@roseuid 428457170298
     */
    public MarketRequestSendResult send(CancelOrderMarketRequestMessage l_cancelOrderRequest, 
        boolean l_blnIsMarketNoSend)
    {
        final String STR_METHOD_NAME = "send(CancelOrderMarketRequestMessage l_cancelOrderRequest, " +
                "boolean l_blnIsMarketNoSend)";
        log.entering(STR_METHOD_NAME);
        if (l_cancelOrderRequest == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();   
            
            //1.1 getOrderId( )
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            WEB3FeqOrderManager l_orderManager = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            long l_lngOrderId = l_cancelOrderRequest.getOrderId();
            
            //1.2 get注文単位ByOrderId(long)。
            WEB3FeqOrderUnit l_feqOrderUnit = 
                (WEB3FeqOrderUnit)l_orderManager.getOrderUnitByOrderId(
                    l_lngOrderId);
            FeqOrderUnitRow l_feqOrderUnitRow = 
                (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();

            SleSendQParams l_sleSendQParams = null;
            
            //1.3 get市場( )
            WEB3GentradeMarket l_gentradeMarket = l_feqOrderUnit.getMarket();
            
            //1.4 isシステム連動( )
            boolean l_blnIsSystemLock = l_gentradeMarket.isSystemInterLock();
            
            //1.5 isトリガ発行(発注条件 : String)
            boolean l_blnIsTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null);
            
            //1.6 （分岐フロー：　@isシステム連動() == false（非連動）の場合）
            if (!l_blnIsSystemLock)
            {
                //1.6.1  create取消注文Mail(FeqOrderUnit)
                //取消注文の情報をメール送信テーブル、メール送信拡張テーブルに登録する。 
                //[create取消注文Mail()に指定する引数] 
                //　@注文単位：　@取得した注文単位オブジェクト 
                WEB3FeqMailSenderService l_mailSenderService = 
                    (WEB3FeqMailSenderService)Services.getService(WEB3FeqMailSenderService.class);
                l_mailSenderService.createCancelOrderMail(l_feqOrderUnit);
            }
            //1.7 （分岐フロー：　@isシステム連動() == true（連動）の場合）
            else
            {
                //1.7.1 （分岐フロー：　@引数.is市場未送信==true(市場未送信)の場合）
                if (l_blnIsMarketNoSend)
                {
                    //注文キューデータの登録を行う。
                    //詳細は　@DB更新仕様「取消_外国株取引SEND_Qテーブル.xls」のシート
                    //「取消_外国株取引SEND_Qテーブル(市場未送信)」参照
                    
                    //○　@注文ID==外株注文単位.注文IDかつオペレータタイプ=="新規"
                    //かつ処理区分=="未送信"データが存在する場合、当該レコードを更新（Update）する。
                    
                    String l_strWhere = " order_id = ? and op_type = ? and status = ? ";
                    Object[] l_objWhere = new Object[3];
                    l_objWhere[0] = l_feqOrderUnit.getOrderId() + "";
                    l_objWhere[1] = SleSendqOpTypeEnum.NEW_ORDER;
                    l_objWhere[2] = SleSendqProcStatusEnum.NOT_PROCESSED;
                    List l_lisSleSendQRow =  
                        l_queryProcessor.doFindAllQuery(
                            SleSendQRow.TYPE,
                            l_strWhere,
                            l_objWhere);
                    if (l_lisSleSendQRow != null && l_lisSleSendQRow.size() != 0)
                    {
                        SleSendQRow l_sleSendQRow = (SleSendQRow)l_lisSleSendQRow.get(0);
                        l_sleSendQParams = new SleSendQParams(l_sleSendQRow);
                       
                        //処理区分: 8:処理省略
                        l_sleSendQParams.setStatus(SleSendqProcStatusEnum.SKIP_PROCESSING_LOCAL);

                        //更新日付: 現在日時（GtlUtils.getSystemTimestamp()）
                        l_sleSendQParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        
                        int l_updcount = l_queryProcessor.doUpdateQuery(l_sleSendQParams);
                        
                        if (l_updcount == 0)
                        {
                            // 更新行数が0件だった場合、業務エラーとする。
                            log.error("SLE_SEND_Qの該当レコードに対する取消処理に失敗しました。" + l_sleSendQParams);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00394, 
                                    getClass().getName() + STR_METHOD_NAME);
                        }
                    }
                    else
                    {
                        // 新規注文かつ市場未送信の注文で、SEND_Qに存在しない事はあり得ないので
                        // 業務エラーとする。
                        log.error("取消対象の新規注文レコードが、SLE_SEND_Qに存在しません。" +
                            "[ order_id：" + l_feqOrderUnit.getOrderId() + " ]");
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00394, 
                                getClass().getName() + STR_METHOD_NAME);
                    }
                }
                
                //1.7.2 （分岐フロー：　@引数.is市場未送信 == false(市場送信済み)の場合）
                else
                {
                    //1.7.2.1 （注文キューデータ登録）
                    //注文キューデータの登録を行う。
                    //詳細は　@DB更新仕様「取消_外国株取引SEND_Qテーブル.xls」のシート
                    //「取消_外国株取引SEND_Qテーブル(市場送信済)」参照
                    l_sleSendQParams = new SleSendQParams();
                    
                    //キューID: 自動採番した値
                    l_sleSendQParams.setQueueId(SleSendQDao.newPkValue());
                    
                    //銘柄タイプ: 外株注文単位.銘柄タイプ
                    l_sleSendQParams.setProductType(l_feqOrderUnit.getProductType());
                    
                    //市場コード: 外株注文単位.市場ＩＤに該当する市場.市場コード
                    l_sleSendQParams.setMarketCode(l_gentradeMarket.getMarketCode());
                    
                    //ブローカー: 
                    //外株注文単位.市場ＩＤに該当する市場プリファ@レンスの「プリファ@レンス項目名」＝
                    //"feq.sle.broker"かつ「項目名連番」＝1の「プリファ@レンスの値」
                    MarketPreferencesRow l_marketPreferencesRow = null;
                    int l_intValue = 1;
                    String l_strName = WEB3MarketPreferencesNameDef.FEQ_SLE_BROKER;
                    l_marketPreferencesRow = MarketPreferencesDao.findRowByPk(
                        l_gentradeMarket.getMarketId(),
                        l_strName,
                        l_intValue);
                    l_sleSendQParams.setBrokerName(l_marketPreferencesRow.getValue());
                    
                    //証券会社コード： 外株注文単位.証券会社コード
                    l_sleSendQParams.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());
                    
                    //部店コード: 外株注文単位.部店コード
                    l_sleSendQParams.setBranchCode(l_feqOrderUnit.getBranchCode());
                    
                    //銘柄コード: 外株注文単位.銘柄ＩＤに該当する外株銘柄.現地銘柄コード
                    WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)l_feqOrderUnit.getProduct();
                    String l_strProductCode = l_feqProduct.getOffshoreProductCode();
                    if (l_strProductCode != null)
                    {
                        while (l_strProductCode.startsWith("0"))
                        {
                            l_strProductCode = l_strProductCode.replaceFirst("0", "");
                        }
                    }
                    l_sleSendQParams.setProductCode(l_strProductCode);
                    //注文ID: 外株注文単位.注文ID
                    l_sleSendQParams.setOrderId(l_feqOrderUnit.getOrderId());
                    
                    //注文単位ID: 外株注文単位.注文単位ID
                    l_sleSendQParams.setOrderUnitId(l_feqOrderUnit.getOrderUnitId());
                    
                    //発注日: 外株注文単位.発注日
                    l_sleSendQParams.setBizDate(l_feqOrderUnit.getBizDate());
                    
                    //オペレータタイプ: 2:取消(（SleSendqOpTypeEnumにて定義)
                    l_sleSendQParams.setOpType(SleSendqOpTypeEnum.CANCEL_ORDER);
                    
                    //注文種別: 外株注文単位.注文種別
                    l_sleSendQParams.setOrderType(l_feqOrderUnit.getOrderType());
                    
                    //アカウントID: 外株注文単位.口座ID
                    l_sleSendQParams.setAccountId(l_feqOrderUnit.getAccountId());
                    
                    //アカウントコード: 外株注文単位.口座IDに該当する顧客.口座コード
                    MainAccount l_mainAccount = 
                        l_accountManager.getMainAccount(
                            l_feqOrderUnit.getAccountId());
                    l_sleSendQParams.setAccountCode(l_mainAccount.getAccountCode());
                        
                    //サブアカウントID: 外株注文単位.補助口座ID:
                    l_sleSendQParams.setSubAccountId(l_feqOrderUnit.getSubAccountId());
                    
                    //処理区分:
                    SleSendqProcStatusEnum l_sleSendqProcStatusEnum = null;
                    //取引時間管理.isトリガ発行()==trueの場合、0:処理待ち
                    if (l_blnIsTrigger)
                    {
                        l_sleSendqProcStatusEnum = SleSendqProcStatusEnum.TODO;
                    }
                    //取引時間管理.isトリガ発行()==falseの場合、7:未送信
                    //（SleSendqProcStatusEnumにて定義）
                    else
                    {
                        l_sleSendqProcStatusEnum = SleSendqProcStatusEnum.NOT_PROCESSED;
                    }
                    l_sleSendQParams.setStatus(l_sleSendqProcStatusEnum);
                    
                    //受信確認フラグ: 0:FALSE BooleanEnumにて定義）
                    l_sleSendQParams.setConfirmedBySleRcvdQ(BooleanEnum.FALSE);
                    
                    //運用コード: 外株注文単位.運用コード
                    l_sleSendQParams.setOrderEmpCode(l_feqOrderUnit.getOrderEmpCode());
                    
                    //識別コード: 外株注文単位.識別コード
                    l_sleSendQParams.setOrderRequestNumber(
                        l_feqOrderUnitRow.getOrderRequestNumber());
                    
                    //電子メール送信日時: Null
                    l_sleSendQParams.setSendProcessDateTime(null);  
                    
                    l_queryProcessor.doInsertQuery(l_sleSendQParams);
                }
                
                //1.8 （分岐フロー：　@is市場開局時間帯()の戻り値 == false
                //かつ引数.is市場未送信 == falseの場合）
                boolean l_blnIsTradeOpenTimeZone =
                    WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
                if (!l_blnIsMarketNoSend && !l_blnIsTradeOpenTimeZone)
                {
                    //1.8.1 取消確定(long)
                    this.cancelConfirm(l_lngOrderId);
                }
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
      }
      
      log.exiting(STR_METHOD_NAME);
      return DefaultMarketRequestSendResult.newSuccessResultInstance(0);
    }
    
    /**
     * (訂正確定) <BR>
     * 訂正注文を確定させる。 <BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（市場リクエスト）訂正確定」 参照 <BR>
     * @@param l_lngOrderId - (注文ID)
     * @@throws WEB3BaseException
     * @@roseuid 429D6E220379
     */
    protected void changeConfirm(long l_lngOrderId) throws WEB3BaseException
    {       
        final String STR_METHOD_NAME = "changeConfirm(long l_lngOrderId) ";
        log.entering(STR_METHOD_NAME);
        //１．１外国株式訂正確定更新イベントインタセプタ( )
        //インタセプタを生成する。
        WEB3FeqChangeConfirmUpdateInterceptor l_interceptor = new WEB3FeqChangeConfirmUpdateInterceptor();
        //１．２
        //setThreadLocalPersistenceEventInterceptor(arg0 : FeqOrderManagerPersistenceEventInterceptor)
        //インタセプタをセットする。

        //[引数]
        //arg0： 外国株式訂正確定更新インタセプタ
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //１．３DefaultChangeOrderAcceptedMarketResponseMessage(注文ID : long)
        //訂正結果（訂正完了）オブジェクトを生成する。

        //[引数]
        //注文ＩＤ： 引数.注文ID
        DefaultChangeOrderAcceptedMarketResponseMessage l_message = 
            new DefaultChangeOrderAcceptedMarketResponseMessage(l_lngOrderId);
        //１．４process(arg0 : ChangeOrderAcceptedMarketResponseMessage)
        //訂正完了を注文に更新する。

        //[引数]
        //arg0： 訂正結果オブジェクト
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        MarketResponseReceiverCallbackService l_service = 
            l_marketAdapter.getMarketResponseReceiverCallbackService();
        ProcessingResult l_result = l_service.process(l_message);
        if (l_result.isFailedResult())
        {
            log.debug("訂正完了を注文に更新Failed!" + l_result.getErrorInfo());
            throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (取消確定) <BR>
     * 取消注文を確定させる。 <BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（市場リクエスト）取消確定」 参照 <BR>
     * @@param l_lngOrderId - (注文ID)
     * @@throws WEB3BaseException
     * @@roseuid 429D7F02031C
     */
    protected void cancelConfirm(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "cancelConfirm(long l_lngOrderId) ";
        log.entering(STR_METHOD_NAME);
        //１．１    外国株式取消確定更新イベントインタセプタ( ) インタセプタを生成する。
        WEB3FeqCancelConfirmUpdateInterceptor l_interceptor = 
            new WEB3FeqCancelConfirmUpdateInterceptor();
        //１．２    setThreadLocalPersistenceEventInterceptor(arg0 : FeqOrderManagerPersistenceEventInterceptor)
        //インタセプタをセットする。

        //[引数]
        //arg0： 外国株式取消確定更新インタセプタ
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        //１．３    DefaultCancelOrderAcceptedMarketResponseMessage(注文ID : long)
        //取消結果（取消完了）オブジェクトを生成する。

        //[引数]
        //注文ID： 引数.注文ID
        DefaultCancelOrderAcceptedMarketResponseMessage l_message
            = new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);
        //１．４    process(arg0 : CancelOrderAcceptedMarketResponseMessage)
        //取消完了を注文に更新する。

        //[引数]
        //arg0： 取消結果オブジェクト
    
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        MarketResponseReceiverCallbackService l_service = 
            l_marketAdapter.getMarketResponseReceiverCallbackService();
        ProcessingResult l_result = l_service.process(l_message);
        if (l_result.isFailedResult())
        {
            log.debug("取消完了を注文に更新Failed!" + l_result.getErrorInfo());
            throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
        }

        log.exiting(STR_METHOD_NAME);
     
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSenderService#send(com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage)
     */
    public MarketRequestSendResult send(MarketRequestMessage arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }
}@
