head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoSettleContractOrderNotifyUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP返済注文通知更新インタセプタクラス(WEB3IfoSettleContractOrderNotifyUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 呉艶飛 (中訊) 新規作成
Revesion History : 2007/01/25 張騰宇 (中訊) DB更新仕様No.133、139
Revesion History : 2007/06/11 孟亜南 (中訊) 仕様変更モデルNo.665 DB更新仕様No.170
Revesion History : 2007/06/21 孟亜南 (中訊) DB更新仕様No.180
Revesion History : 2007/07/02 孟亜南 (中訊) 仕様変更モデルNo.770 DB更新仕様No.192
Revesion History : 2008/04/25 張騰宇 (中訊) DB更新仕様No.197
*/
package webbroker3.ifo;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.ifo.define.WEB3IfoVoucherNoFirstNumDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP返済注文通知更新インタセプタ)<BR>
 * 先物OP返済注文通知更新インタセプタクラス
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3IfoSettleContractOrderNotifyUpdateInterceptor extends WEB3IfoOrderUpdateInterceptor 
{
   
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3IfoSettleContractOrderNotifyUpdateInterceptor.class);
    
   /**
    * (先物OP返済注文内容)<BR>
    */
   private WEB3IfoSettleContractOrderSpec ifoSettleContractOrderSpec;
   
   /**
    * (決済順序)<BR>
    * 0：ランダム　@1：単価益順　@2：単価損順　@3：建日順<BR>
    * 一括返済時の場合設定<BR>
    */
   private String closingOrder;
   
   /**
    * 識別コード<BR>
    */
   private String orderRequestNumber;
   
   /**
    * 受注日時<BR>
    */
   private Date receivedDateTime;

   /**
    * 受渡日
    */
   private Date deliveryDate;

   /**
    * 発注日
    */
   private Date bizDate;

   /**
    * 立会区分
    */
   private String sessionType;

   /**
    * @@roseuid 41AD749600CB
    */
   public WEB3IfoSettleContractOrderNotifyUpdateInterceptor() 
   {
        
   }
   
   /**
    * (先物OP返済注文通知更新インタセプタ)<BR>
    * コンストラクタ<BR>
    * <BR>
    * インスタンスを生成し、<BR>
    * 引数の先物OP返済注文内容をプロパティにセットする。<BR>
    * @@param l_ifoSettleContractOrderSpec - 先物OP返済注文内容
    * @@roseuid 4165191202EA
    */
   public WEB3IfoSettleContractOrderNotifyUpdateInterceptor(WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec) 
   {
       ifoSettleContractOrderSpec = l_ifoSettleContractOrderSpec;
   }
   
   /**
    * (更新値設定)<BR>
    * （mutateメソッドの実装）<BR>
    * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
    * <BR>
    * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
    * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
    * <BR>
    * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、<BR>・
    * 却する。<BR>
    * <BR>
    * 【ｘTrade】補足資料.DB更新<BR>
    * 注文単位.先物／オプション区分 == "オプション"の場合<BR>
    * 「OP注文通知_注文単位テーブル仕様.xls」の<BR>
    * 「OP注文通知[返済]注文単位テーブル_DB更新仕様」シート<BR>
    * <BR>
    * 注文単位.先物／オプション区分 == "先物"の場合<BR>
    * 「先物注文通知_注文単位テーブル仕様.xls」の<BR>
    * 「先物注文通知[返済]注文単位テーブル_DB更新仕様」シート<BR>
    * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
    * INSERTまたは、UPDATE。<BR>
    * <BR>
    * OrderManagerPersistenceTypeにて定数定義。<BR>
    * 
    * @@param l_orderManagerPersistenceContext - (処理)<BR>
    * （OrderManagerPersistenceContextにて定数定義）<BR>
    * @@param l_ifoOrderUnitParams - (注文単位Params)<BR>
    * 注文単位が保持する項目のパラメータクラス。<BR>
    * @@return com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams
    * @@roseuid 4165191202CB
    */
   public IfoOrderUnitParams mutate(
    OrderManagerPersistenceType l_orderManagerPersistenceType, 
    OrderManagerPersistenceContext l_orderManagerPersistenceContext, 
    IfoOrderUnitParams l_ifoOrderUnitParams)
   {
       final String STR_METHOD_NAME = "mutate";

       FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
       TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
       WEB3IfoProductManagerImpl l_productManagerImpl = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
       long l_productId = l_ifoOrderUnitParams.getProductId();
       WEB3IfoProductImpl l_productImpl = null;
       MainAccount l_mainAccount = null;
       Market l_market = null;
       long l_lngMarketId = l_ifoOrderUnitParams.getMarketId();
       try
       {
           l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);

           l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_ifoOrderUnitParams.getAccountId());

           l_productImpl = (WEB3IfoProductImpl) l_productManagerImpl.getProduct(l_productId);
       }
       catch (NotFoundException l_nfe)
       {
           log.error(STR_METHOD_NAME, l_nfe);
           throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + "." + STR_METHOD_NAME);
       }

       MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
       IfoProductRow l_productRow = (IfoProductRow) l_productImpl.getDataSourceObject();

       //先物OP銘柄テーブル.先物／オプション区分
       l_ifoOrderUnitParams.setFutureOptionDiv(l_productRow.getFutureOptionDiv());
       if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
       {
           l_ifoOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
           //（* null固定）
           l_ifoOrderUnitParams.setOrderCondOperator(null);
           //（* null固定）
           l_ifoOrderUnitParams.setStopPriceType(null);
           //（* null固定）
           l_ifoOrderUnitParams.setStopOrderPrice(null);
           //（* null固定）
           l_ifoOrderUnitParams.setWLimitPrice(null);
       }
       else
       {
           //インタセプタ.発注条件
           l_ifoOrderUnitParams.setOrderConditionType(this.orderCond);
           //インタセプタ.発注条件演算子
           l_ifoOrderUnitParams.setOrderCondOperator(this.orderCondOperator);
           //インタセプタ.逆指値基準値タイプ
           l_ifoOrderUnitParams.setStopPriceType(this.stopOrderBasePriceType);
           //インタセプタ.逆指値基準値
           l_ifoOrderUnitParams.setStopOrderPrice(this.stopOrderBasePrice);
           //インタセプタ.（W指値）訂正指値
           l_ifoOrderUnitParams.setWLimitPrice(this.wLimitPriceChange);
       }

       //インタセプタ.受渡日
       l_ifoOrderUnitParams.setDeliveryDate(this.deliveryDate);
        
       //インタセプタ.発注日の日付部分まで(yyyymmdd)を設定
       //（* 実際に発注する営業日。OP注文通知キューテーブル.発注日時と同じ値。）
       l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.bizDate, "yyyyMMdd"));

       l_ifoOrderUnitParams.setOrderChanel(this.commRevMstId.getOrderChannel());
       l_ifoOrderUnitParams.setReceivedDateTime(this.receivedDateTime);
       final String l_strBaseNumber = WEB3IfoVoucherNoFirstNumDef.FIRST_NUMBER;
       //伝票Noを設定する
       int l_intRequestNumberLength = this.getOrderRequestNumber().length() - 3;
       String l_strVoucherNo = l_strBaseNumber + this.getOrderRequestNumber().substring(l_intRequestNumberLength);
       l_ifoOrderUnitParams.setVoucherNo(l_strVoucherNo);

       //インタセプタ.手数料.手数料No
       l_ifoOrderUnitParams.setCommTblNo(this.commRevMstId.getCommissionNo());

       //インタセプタ.手数料.手数料No枝番
       l_ifoOrderUnitParams.setCommTblSubNo(this.commRevMstId.getCommissionRevNo());

       //顧客.扱者コード（SONAR）
       l_ifoOrderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
       //インタセプタ.概算受渡代金計算結果.計算単価
       l_ifoOrderUnitParams.setPrice(this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());

       //インタセプタ.識別コード
       l_ifoOrderUnitParams.setOrderRequestNumber(this.getOrderRequestNumber());

       //インタセプタ.概算受渡代金計算結果.概算受渡代金
       l_ifoOrderUnitParams.setEstimatedPrice(this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());

       //51：新規建
       l_ifoOrderUnitParams.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);

       if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoOrderUnitParams.getFutureOptionDiv()))
       {
           //51：株価指数OP
           l_ifoOrderUnitParams.setCommProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
       }
       else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_ifoOrderUnitParams.getFutureOptionDiv()))
       {
           //50：先物
           l_ifoOrderUnitParams.setCommProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
       }
       //市場コード（SONAR）
       MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
       l_ifoOrderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());

       //注文訂正・取消区分
       l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);

       //注文経路区分
       l_ifoOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);

       //市場から確認済みの注文単価
       if (l_ifoOrderUnitParams.getPriceIsNull())
       {
           l_ifoOrderUnitParams.setConfirmedOrderPrice(null);
       }
       else
       {
           l_ifoOrderUnitParams.setConfirmedOrderPrice(l_ifoOrderUnitParams.getPrice());
       }
       
       //市場から確認済みの概算受渡代金を設定する
       if (l_ifoOrderUnitParams.getEstimatedPriceIsNull())
       {
           l_ifoOrderUnitParams.setConfirmedEstimatedPrice(null);
       }
       else
       {
           l_ifoOrderUnitParams.setConfirmedEstimatedPrice(l_ifoOrderUnitParams.getEstimatedPrice());
       }
       
       //市場から確認済みの執行条件
       l_ifoOrderUnitParams.setConfirmedExecConditionType(l_ifoOrderUnitParams.getExecutionConditionType()); 

       //決済順序 
       l_ifoOrderUnitParams.setClosingOrder(this.getClosingOrder());

       //注文エラー理由コード
       l_ifoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

       //リクエストタイプ 
       l_ifoOrderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);

       //繰越元注文単位ＩＤ
       l_ifoOrderUnitParams.setFirstOrderUnitId(null);

        //発注経路区分
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION);
        
        //夕場前繰越対象フラグ
        boolean l_blnEveningSessionCarryoverFlag = this.ifoSettleContractOrderSpec.getEveningSessionCarryoverFlag();
        if (l_blnEveningSessionCarryoverFlag)
        {
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(
                BooleanEnum.TRUE);
        }
        else
        {
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(
                BooleanEnum.FALSE);
        }

        //立会区分
        l_ifoOrderUnitParams.setSessionType(this.sessionType);

        //日計り区分
        l_ifoOrderUnitParams.setDayTradeType(this.commRevMstId.getDayTradeType());

        //注文期限区分
        l_ifoOrderUnitParams.setExpirationDateType(this.ifoSettleContractOrderSpec.getExpirationDateType());

       log.exiting(STR_METHOD_NAME);
       return l_ifoOrderUnitParams;

   }
   
   /**
    * (set決済順序)<BR>
    * 決済順序をセットする。<BR>
    * @@param l_strSettletSequence - 決済順序<BR>
    * <BR>
    * 0：ランダム　@1：単価益順　@2：単価損順　@3：建日順<BR>
    * 一括返済時の場合設定<BR>
    * @@roseuid 416518F000E7
    */
   public void setClosingOrder(String l_strClosingOrder) 
   {
        this.closingOrder = l_strClosingOrder;
   }
   
   /**
    * (set識別コード)<BR>
    * 識別コードをセットする。<BR>
    * @@param l_strRequestCode - 識別コード
    * @@roseuid 416519920125
    */
   public void setOrderRequestNumber(String l_strOrderRequestNumber) 
   {
        this.orderRequestNumber = l_strOrderRequestNumber;
   }
   
   /**
    * (set受注日時)<BR>
    * 受注日時をセットする。<BR>
    * @@param l_strReceivedDateTime - 受注日時
    * @@roseuid 416519920135
    */
   public void setReceivedDateTime(Date l_strReceivedDateTime) 
   {
        this.receivedDateTime = l_strReceivedDateTime;
   }

   /**
    * (set受渡日)<BR>
    * 受注日をセットする。<BR>
    * @@param l_datDeliveryDate - 受注日
    */
   public void setDeliveryDate(Date l_datDeliveryDate)
   {
       deliveryDate = l_datDeliveryDate;
   }

   /**
    * (set発注日)<BR>
    * 発注日をセットする。<BR>
    * @@param l_datBizDate - 発注日
    * @@roseuid 4165184603D5
    */
   public void setBizDate(Date l_datBizDate)
   {
       bizDate = l_datBizDate;
   }
   
   /**
    * (get決済順序)<BR>
    * 決済順序を取得する。<BR>
    * @@return String
    * @@roseuid 416518F000F6
    */
   public String getClosingOrder() 
   {
        return this.closingOrder;
   }
   
   /**
    * (get識別コード)<BR>
    * 識別コードを取得する。<BR>
    * @@return String
    * @@roseuid 416519920144
    */
   public String getOrderRequestNumber() 
   {
        return this.orderRequestNumber;
   }
   
   /**
    * (get受注日時)<BR>
    * 受注日時を取得する。<BR>
    * @@return java.util.Date
    * @@roseuid 416519920154
    */
   public Date getReceivedDateTime() 
   {
        return this.receivedDateTime;
   }

   /**
    * (get受渡日)<BR>
    * 受注日を取得する。<BR>
    * @@return Date
    */
   public Date getDeliveryDate()
   {
       return this.deliveryDate;
   }

   /**
    * (get発注日)<BR>
    * 発注日を取得する。<BR>
    * @@return Date
    * @@roseuid 4165195E028D
    */
   public Date getBizDate()
   {
       return this.bizDate;
   }

   /**
    * (get立会区分)<BR>
    * 立会区分を取得する。<BR>
    * <BR>
    * @@return String
    */
   public String getSessionType()
   {
       return this.sessionType;
   }

   /**
    * (set立会区分)<BR>
    * 立会区分をセットする<BR>
    * @@param l_strSessionType - (立会区分)<BR>
    * 立会区分
    */
   public void setSessionType(String l_strSessionType)
   {
       this.sessionType = l_strSessionType;
   }
}
@
