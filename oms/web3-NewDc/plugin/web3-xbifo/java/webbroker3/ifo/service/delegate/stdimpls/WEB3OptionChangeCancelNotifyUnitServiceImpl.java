head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeCancelNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               /**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP訂正取消通知UnitServiceImpl(WEB3OptionChangeCancelNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/16 盧法@旭 (中訊) 新規作成
              001: 2004/07/22 王暁傑 (中訊) 時価の関連内容をコメント
              002: 2004/08/05 盧法@旭 WEB3_IFO_UT-000117 
              003: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
              004: 2006/7/14 徐宏偉 (中訊) 仕様変更　@モデル458
              005: 2006/09/28 郭英 (中訊) 仕様変更 モデル564
              006: 2006/11/29 徐大方 (中訊) 仕様変更 モデル576
Revesion History : 2008/04/10 張騰宇 (中訊) モデル 845
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoBizLogicProvider;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoCanmodReceiptTypeDef;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeCancelNotifyUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP訂正取消通知UnitServiceImpl)<BR>
 * 株価指数オプション訂正取消通知１件サービス実装クラス<BR>
 * <BR>
 * 注文１件ごとの訂正取消通知処理を実施する。<BR>
 * <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_JOIN_EXISTING)を指定する。<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3OptionChangeCancelNotifyUnitServiceImpl implements WEB3OptionChangeCancelNotifyUnitService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionChangeCancelNotifyUnitServiceImpl.class);
    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
    TradingModule l_tradingMod =
        l_finApp.getTradingModule(ProductTypeEnum.IFO);
    /**
     * @@roseuid 40C0752E0138
     */
    public WEB3OptionChangeCancelNotifyUnitServiceImpl() 
    {
     
    }
    
    /**
     * (notify訂正)<BR>
     * 訂正通知処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP訂正取消通知）notify訂正」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param l_ifoChangeCancelNotifyInterceptor - 先物OP訂正取消通知インタセプタオブジェクト
     * @@roseuid 4084C37D0150
     */
    public void notifyChange(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor) throws WEB3BaseException 
    { 
        final String STR_METHOD_NAME =
            "notifyChange(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor) ";
        log.entering(STR_METHOD_NAME);
        if ((l_orderUnit == null) || (l_ifoChangeCancelNotifyInterceptor == null))
        {
            log.error("Enter the path that the paramter l_orderUnit is null.");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        IfoOrderManager l_orderManager = (IfoOrderManager)l_tradingMod.getOrderManager();
        
        //1.2.setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
        //スレッドにインタセプタをセットする。 
        // [引数] 
        //arg0： 引数.先物OP訂正取消通知インタセプタ


        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_ifoChangeCancelNotifyInterceptor);

		//1.1.getOrderUnit(注文単位.注文単位ID : long)
        //注文単位を再取得する。（口座ロック競合した場合を考慮）
		OrderUnit l_reOrderUnit = l_orderUnit;
		try
		{
			l_reOrderUnit =  l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
		}
		catch (NotFoundException l_nfe)
		{
			log.error("データ不整合エラー。", l_nfe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80006,
				this.getClass().getName() + STR_METHOD_NAME,
				l_nfe.getMessage(),
				l_nfe); 
		}
        
        //Start 2004/08/13 呉艶飛  対応バッグ BUG79
        //1.3.取得訂正後指値 
        double l_dblChangedLimitPrice = 
           l_ifoChangeCancelNotifyInterceptor.getChangedLimitPrice();
        log.debug("訂正後指値"+ l_dblChangedLimitPrice);
        
        if (Double.isNaN(l_dblChangedLimitPrice))
        {
            l_dblChangedLimitPrice = 0D;
        }
       
        //1.4.取得訂正後数量
        double l_dblChangedQuantity = 
              l_ifoChangeCancelNotifyInterceptor.getChangedQuantity();
        if (Double.isNaN(l_dblChangedQuantity))
        {
           l_dblChangedQuantity = 0D;
        }
         
        log.debug("訂正後数量"+l_dblChangedQuantity );
        //取得訂正前数量
        double l_dblQuantity = l_reOrderUnit.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
           l_dblQuantity = 0D;
        }
        
        log.debug("取得訂正前数量"+l_dblQuantity );
        //1.6.取得side
        SideEnum l_side = l_reOrderUnit.getSide();
        log.debug("取得side"+l_side.toString() );
        //1.7.取得注文カテゴリ            
        OrderCategEnum l_intOrderCateg = l_orderUnit.getOrderCateg();
        log.debug("取得注文カテゴリ"+l_side.toString() );
        //1.8.約定数量          
        double l_dblExecutedQuantity = l_reOrderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
           l_dblExecutedQuantity = 0D;
        }
        log.debug("約定数量"+l_dblExecutedQuantity );
        //1.9.合計約定金額               
        double l_dblExecutedAmount = l_reOrderUnit.getExecutedAmount();
        if (Double.isNaN(l_dblExecutedAmount))
        {
           l_dblExecutedAmount = 0D;
        }
        log.debug(" 合計約定金額"+ l_dblExecutedAmount );

        //isストップ注文切替中(IfoOrderUnit)
        //ストップ注文切替中かどうか判別する。
        //[引数]
        //注文単位：　@注文単位
        boolean l_blnIsStopOrderSwitching =
            ((WEB3OptionOrderManagerImpl)l_orderManager).isStopOrderSwitching((IfoOrderUnit)l_reOrderUnit);

        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
        WEB3IfoEstimateDeliveryAmountCalcResult l_wEstimateResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
        //注文UnitId
        long l_lngOrderUnitId = l_reOrderUnit.getOrderUnitId();

        //注文Id
        long l_lngOrderId = l_reOrderUnit.getOrderId();

        //顧客Id
        long l_lngMainAccountId = l_reOrderUnit.getAccountId();

        //SubAccountID
        long l_lngSubAccountId = l_reOrderUnit.getSubAccountId();

        //取得OrderManager
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                l_lngMainAccountId, l_lngSubAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //ストップ注文失効時にリミット注文の単価で再計算が必要となる注文の場合
        //注文単位.注文種別 == "OP新規買建注文" かつ
        //get訂正取消通知区分 == ”訂正失敗” かつ
        //isストップ注文切替中() == true
        if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_reOrderUnit.getOrderType())
            && WEB3IfoCanmodReceiptTypeDef.CHANGED_FAILED.equals(
                l_ifoChangeCancelNotifyInterceptor.getChangeCancelNotifyDivision())
                && l_blnIsStopOrderSwitching)
        {
            //getストップ注文失効時概算代金計算結果(IfoOrderUnit, 補助口座)
            //ストップ注文失効時の概算受渡代金を計算する。
            //[引数]
            //注文単位：　@注文単位
            //補助口座：　@注文単位.補助口座IDに該当する補助口座
            l_estimateResult =
                l_orderMgr.getStopOrderExpireEstimatedPrice(
                    (IfoOrderUnit)l_reOrderUnit,
                    l_subAccount);
        }
        else
        {
            // is返済注文 92：先物返済注文 94：OP返済注文
            boolean l_blnIsSettleContract ;
            if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_intOrderCateg))
            {
                l_blnIsSettleContract = true;
            }
            else
            {
                l_blnIsSettleContract = false;
            }
            
            //1.10. 手数料オブジェクトを生成する
            WEB3IfoBizLogicProvider l_bizLogicProvider =
                (WEB3IfoBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();
            WEB3GentradeCommission l_commision =
                l_bizLogicProvider.createCommission(l_reOrderUnit.getOrderUnitId(),
                l_dblChangedQuantity);
    
            //IfoOrderUnitRowを生成する
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_reOrderUnit.getDataSourceObject();

            //1.11.setIs指値(is指値 : boolean)
			//setIs指値()に指定する引数]
			//is指値：　@先物OP訂正取消通知更新インタセプタ.get訂正後指値 != 0の場合、true。以外、false。
            if (l_ifoChangeCancelNotifyInterceptor.getChangedLimitPrice() != 0)
            {
                l_commision.setIsLimitPrice(true);
            }
            else
            {
                l_commision.setIsLimitPrice(false);
            }


            WEB3IfoTradedProductImpl l_tradedProduct1 =  (WEB3IfoTradedProductImpl)l_reOrderUnit.getTradedProduct();
            // 1.12.calc訂正時概算受渡代金
    		//[calc訂正時概算受渡代金()に指定する引数] 
    		// 手数料：　@手数料オブジェクト 
    		// 計算単価：　@（get訂正後指値()戻り値） 
    		// 補助口座：　@注文単位.補助口座ＩＤに該当する補助口座オブジェクト 
    		// 先物OP取引銘柄：　@注文単位.getTradedProduct() 
    		// 数量： （get訂正後数量()戻り値） 
    		// 売買： 注文単位.getSide() 
    		// is返済注文：　@ 
    		// 　@注文カテゴリが”返済注文”の場合true、以外false。 
    		// 約定数量：　@注文単位.getExecutedQuantity() 
    		// 合計約定金額：　@注文単位.getExecutedAmount() 
    		// isSkip金額チェック：　@true 

            l_estimateResult =
                l_orderMgr.calcChangeEstimateDeliveryAmount(
                    l_commision,
                    l_dblChangedLimitPrice,
                    l_subAccount,
                    l_tradedProduct1,
                    l_dblChangedQuantity,
                    l_side,
                    l_blnIsSettleContract,
                    l_dblExecutedQuantity,
                    l_dblExecutedAmount,
                    true);
            log.debug("訂正時概算受渡代金 is:" + l_estimateResult.getCalcUnitPrice());

            //1.13.getＷ指値用有効状態区分(注文単位 : IfoOrderUnit)
            // W指値用有効状態区分を取得する。 
    		//[引数の設定] 
    		// 注文単位：　@注文単位
            String l_strWLimitEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv((IfoOrderUnit)l_orderUnit);
    
    		//1.14.買建注文 && W指値リミット注文有効の場合（注文単位.注文種別＝"OP新規買建注文" 
            //&& getＷ指値用有効状態区分()の戻り値 == "リミット注文有効")
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderUnit.getOrderType()) 
                && WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
            {
    			//1.14.1.setIs指値(is指値 : boolean)
            	//[setIs指値()に指定する引数] 
    			// is指値：　@注文単位.（W指値）訂正指値 != 0 の場合は、true。以外、false。
                if (l_orderUnitRow.getWLimitPrice() != 0)
                {
                    l_commision.setIsLimitPrice(true);
                }
                else
                {
                    l_commision.setIsLimitPrice(false);
                }
                
    			//1.14.2.calc訂正時概算受渡代金(手数料, double, SubAccount, 先物OP取引銘柄, 
                //		double, SideEnum, boolean, double, double, boolean)
    			//[calc訂正時概算受渡代金()に指定する引数] 
    			// 手数料：　@W指値用手数料オブジェクト 
    			// 計算単価：　@注文単位.（W指値）訂正指値 
    			// 補助口座：　@注文単位.補助口座ＩＤに該当する補助口座オブジェクト 
    			// 先物OP取引銘柄：　@注文単位.getTradedProduct() 
    			// 数量： （get訂正後数量()戻り値） 
    			// 売買： 注文単位.getSide() 
    			// is返済注文：　@ 
    			// 　@注文カテゴリが”返済注文”の場合true、以外false。 
    			// 約定数量：　@注文単位.getExecutedQuantity() 
    			// 合計約定金額：　@注文単位.getExecutedAmount() 
    			// isSkip金額チェック：　@true 
                l_wEstimateResult =
                    l_orderMgr.calcChangeEstimateDeliveryAmount(
                    l_commision,
                    l_orderUnitRow.getWLimitPrice(),
                    l_subAccount,
                    l_tradedProduct1,
                    l_dblChangedQuantity,
                    l_side,
                    l_blnIsSettleContract,
                    l_dblExecutedQuantity,
                    l_dblExecutedAmount,
                    true);
            }
        }

        //1.15.set計算単価(double)
        //[set計算単価()に指定する引数] 
        // 計算単価：　@calc訂正時概算受渡代金()の戻り値より計算単価を取得する
        //1.16.set概算受渡代金
        //[set概算受渡代金()に指定する引数] 
        // 概算受渡代金：　@calc訂正時概算受渡代金()の戻り値より概算受渡代金を取得する

        //get計算単価
        double l_dblCalcUnitPrice = 0 ;

        //get概算受渡代金
        double l_dblEstimateAmount = 0;

        if (l_estimateResult.getEstimateDeliveryAmount() > l_wEstimateResult.getEstimateDeliveryAmount())
        {
            l_dblCalcUnitPrice = l_estimateResult.getCalcUnitPrice();
            l_dblEstimateAmount = l_estimateResult.getEstimateDeliveryAmount();
        }
        else
        {
            l_dblCalcUnitPrice = l_wEstimateResult.getCalcUnitPrice();
            l_dblEstimateAmount = l_wEstimateResult.getEstimateDeliveryAmount();
        }

        log.debug("計算単価 is:" +l_dblCalcUnitPrice);
        log.debug("概算受渡代金 is:" +l_dblEstimateAmount);

        //set計算単価 
        l_ifoChangeCancelNotifyInterceptor.setCalcUnitPrice(l_dblCalcUnitPrice);

        //set概算受渡代金
        l_ifoChangeCancelNotifyInterceptor.setEstimateDeliveryAmount(l_dblEstimateAmount);

        //1.17.返済 の場合（注文単位.getOrderCateg()＝"94：OP返済注文"）
		if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_intOrderCateg))
		{
			//1.17.1.:adjust返済指定情報(long, long, long, long, double, double)
			//返済指定情報テーブルのデータを訂正後のデータに更新する。
			//[adjust返済指定情報()に指定する引数] 
			// 口座ＩＤ：　@（注文単位.getAccountId()の戻り値） 
			// 補助口座ＩＤ：　@（注文単位.getSubAccountId()の戻り値） 
			// 注文ＩＤ：　@（注文単位.getOrderId()の戻り値） 
			// 注文単位ＩＤ：　@（注文単位.getOrderUnitId()の戻り値） 
			// 訂正前数量：　@（注文単位.getQuantity()の戻り値） 
			// 訂正後数量：　@（OP訂正取消通知インタセプタ.get訂正後数量()の戻り値） 
            WEB3IfoPositionManagerImpl  l_positionMgr =
                (WEB3IfoPositionManagerImpl) l_tradingMod.getPositionManager();
            l_positionMgr.adjustClosingContractSpecs(
                l_lngMainAccountId,
                l_lngSubAccountId,
                l_lngOrderId,
                l_lngOrderUnitId,
                l_dblQuantity,
                l_dblChangedQuantity
                );
        }
        
		//1.18.setBusinessTimestamp( )	
		//取引カレンダコンテキスト.受付日時の再セットを行う。
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();
        
        //1.19.get訂正取消通知区分
        String l_strChangeCancelNotifyDivision =
            l_ifoChangeCancelNotifyInterceptor.getChangeCancelNotifyDivision();
        log.debug("訂正取消通知区分" + l_strChangeCancelNotifyDivision);
        //取得MarketAdapter
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        IfoMarketResponseReceiverCallbackService  l_receiverCallbackService =
            (IfoMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();                                                             
        //1.20.訂正完了の場合（get訂正取消通知区分 == ”訂正完了”）
        if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strChangeCancelNotifyDivision))
        {
            //1.20.1.DefaultChangeOrderAcceptedMarketResponseMessage(注文ID : long)
        	//訂正結果（訂正完了）オブジェクトを生成
			//[コンストラクタ引数] 
			// 注文ＩＤ：　@（取得した注文ＩＤ） 
            DefaultChangeOrderAcceptedMarketResponseMessage l_marketAcceptedResponseMessage =
                new DefaultChangeOrderAcceptedMarketResponseMessage(l_lngOrderId);
            
            //1.20.2.process(訂正結果 : ChangeOrderAcceptedMarketResponseMessage)
            //訂正完了を注文に更新する
			//[process()に指定する引数] 
			// 訂正結果：　@（生成した訂正結果オブジェクト） 
            ProcessingResult l_result = l_receiverCallbackService.process(l_marketAcceptedResponseMessage);
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            OrderUnit l_newOrderUnit = l_reOrderUnit;
            try
            {
                l_newOrderUnit =  l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("データ不整合エラー。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            //1.20.3.isFullyExecuted( )
            boolean l_isFullyExecuted = l_newOrderUnit.isFullyExecuted();

            //1.20.4.訂正後に全部約定（注文数量 == 約定数量）した場合（isFullyExecuted()＝true）
			//[sendMainProcess()に指定する引数] 
			// 注文単位：　@注文単位オブジェクト 
			// 失効理由コード：　@null 
            if (l_isFullyExecuted)
            {   
                WEB3IfoExecutedMailSendService l_mailSendService = 
                    (WEB3IfoExecutedMailSendService) Services.
                    getService(WEB3IfoExecutedMailSendService.class);
            
                //sendMailProcess(OrderUnit, String)(先物OP約定メール送信サービスImpl::sendMailProcess)            
                l_mailSendService.sendMailProcess(l_newOrderUnit, null);                 
                
                //1.20.4.2.notifyルールエンジンサーバ(IfoOrderUnit, OrderManagerPersistenceContext)
				//[引数の設定] 
				// 注文単位：　@注文単位オブジェクト 
				// 処理：　@ FILL_ORDER   
                //1.20.4.3.(*)notifyルールエンジンサーバ()にて業務エラーがスローされた場合
				//*)catchして処理を続行する。
				//   　@※ロールバックしない。
                try
                {
	                l_orderMgr.notifyRLS((IfoOrderUnit) l_newOrderUnit,
	                    OrderManagerPersistenceContext.FILL_ORDER); 
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                	log.debug("notifyルールエンジンサーバ()にて業務エラー");
                	log.debug(this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        //1.21.訂正失敗の場合（get訂正取消通知区分 == ”訂正失敗”）
        if (WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_strChangeCancelNotifyDivision))
        {
        	//1.21.1DefaultChangeOrderRejectedMarketResponseMessage(注文ID : long)
			//[コンストラクタ引数] 
			// 注文ＩＤ：　@（取得した注文ＩＤ） 
            //訂正結果（訂正失敗）オブジェクトを生成
            DefaultChangeOrderRejectedMarketResponseMessage l_marketRejectedResponseMessage =
                new DefaultChangeOrderRejectedMarketResponseMessage(l_lngOrderId);
            //1.21.2process(訂正失敗 : ChangeOrderRejectedMarketResponseMessage)
			//[process()に指定する引数] 
			// 訂正失敗：　@（生成した訂正失敗結果オブジェクト） 
            //訂正失敗を注文に更新する
            ProcessingResult l_result = l_receiverCallbackService.process(l_marketRejectedResponseMessage);

            //1.21.2process(訂正失敗 : ChangeOrderRejectedMarketResponseMessage)処
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
         }
         
         if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
         {
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
         }
         
         log.exiting(STR_METHOD_NAME);          
    }
   
   /**
     * (notify取消)<BR>
     * 取消通知処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP訂正取消通知）notify取消」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param l_ifoChangeCancelNotifyInterceptor - 先物OP訂正取消通知インタセプタオブジェクト
     * @@roseuid 4084C37D0152
     */
    public String notifyCancel(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCancel(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor)";
        log.entering(STR_METHOD_NAME);
         //取得MarketAdapter
        MarketAdapter l_marketAdapter =
            l_tradingMod.getMarketAdapter();
        // get the service
        IfoMarketResponseReceiverCallbackService l_callbackServiceImpl =
             (IfoMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();                

        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }         
        IfoOrderManager l_orderManager = (IfoOrderManager)l_tradingMod.getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_ifoChangeCancelNotifyInterceptor);

		//注文単位を再取得する。（口座ロック競合した場合を考慮）
		OrderUnit l_reOrderUnit = l_orderUnit;
		try
		{
			l_reOrderUnit =  l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
		}
		catch (NotFoundException l_nfe)
		{
			log.error("データ不整合エラー。", l_nfe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80006,
				this.getClass().getName() + STR_METHOD_NAME,
				l_nfe.getMessage(),
				l_nfe); 
		}
        //1.3 取得訂正取消通知区分
        String l_strDivision = l_ifoChangeCancelNotifyInterceptor.getChangeCancelNotifyDivision();
        //取得OrderId
        long l_lngOrderId = l_reOrderUnit.getOrderId();
        
        //1.4 get訂正取消結果コード
        String l_strResultCode = l_ifoChangeCancelNotifyInterceptor.getChangeCancelResultCode();
        
        //1.5 get訂正後数量
        double l_dblChangedQuantity = l_ifoChangeCancelNotifyInterceptor.getChangedQuantity();
        double l_dblExecutedQuantity = l_reOrderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0;
        }

        if  ((WEB3IfoCanmodReceiptTypeDef.CANCELED_COMPLETE.equals(l_strDivision))&&
        (l_dblChangedQuantity > l_dblExecutedQuantity))
        {
            return WEB3StatusDef.DEALING;                
        }
                
        //OP注文マネージャを取得
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

        //1.8 （get訂正取消通知区分 == ”取消完了”）の場合
        if (WEB3IfoCanmodReceiptTypeDef.CANCELED_COMPLETE.equals(l_strDivision))
        {
	        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

            log.debug("訂正後数量 = " + l_dblChangedQuantity);
            //1.8.1 (get訂正後数量 > 0)の場合実施。
            if (l_dblChangedQuantity > 0)
            {
                double l_dblNetAmount = 0D;

                //1.8.1.1 get受渡金額合計(IfoOrderUnit)
                l_dblNetAmount = l_orderMgr.getNetAmount((IfoOrderUnit)l_reOrderUnit);

                //1.8.1.2 set概算受渡代金(double)
                l_ifoChangeCancelNotifyInterceptor.setEstimateDeliveryAmount(l_dblNetAmount);
            }
            
            //1.8.2 isSONAR取消(IfoOrderUnit)
            boolean l_blnIsSonarCancel = l_orderMgr.isSONARCancel((IfoOrderUnit) l_orderUnit);

            //1.8.3 取消結果（取消完了）オブジェクトを生成する
            DefaultCancelOrderAcceptedMarketResponseMessage l_acceptedResponseMessage =
                new DefaultCancelOrderAcceptedMarketResponseMessage(
                l_lngOrderId);
            //1.8.4 取消完了を注文に更新する    
            
            ProcessingResult l_result = l_callbackServiceImpl.process(l_acceptedResponseMessage);
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //is予約注文確認要(IfoOrderUnit)
            boolean l_blnIsReserveOrderExist = l_orderMgr.isReserveOrderExist((IfoOrderUnit)l_orderUnit);

            //予約注文確認要（is予約注文確認要() == true）の場合
            if (l_blnIsReserveOrderExist)
            {
                //cancelAll予約注文単位(親注文の注文ID : long)
                WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);
                l_ifoOrderUpdateService.cancelAllOrderUnit(((IfoOrderUnit)l_orderUnit).getOrderId());
            }

            OrderUnit l_newOrderUnit = l_reOrderUnit;
            try
            {
                l_newOrderUnit =  l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("データ不整合エラー。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
        	//1.8.5.SONAR入力の取消（isSONAR取消()の戻り値 == true）の場合	
            if (l_blnIsSonarCancel)
            {
            	//1.8.5.1.notifyルールエンジンサーバ(IfoOrderUnit, OrderManagerPersistenceContext)
            	//[引数の設定] 
            	//注文単位：　@注文単位オブジェクト 
            	//処理：　@ CANCEL_ORDER_CONFIRMED_BY_MKT 
    			//*)catchして処理を続行する。
    			//   　@※ロールバックしない。
                try
                {
                    l_orderMgr.notifyRLS( (IfoOrderUnit)l_newOrderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_CONFIRMED_BY_MKT); 
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                	log.debug("notifyルールエンジンサーバ()にて業務エラー");
                	log.debug(this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            
            //l_isUnexecuted = false( )
            boolean l_isUnexecuted = l_newOrderUnit.isUnexecuted();
            //sendMailProcess  
            //1.8.4 全部約定済みかを判定する
            if (!l_isUnexecuted)
            {   
                WEB3IfoExecutedMailSendService l_mailSendService = 
                    (WEB3IfoExecutedMailSendService) Services.
                    getService(WEB3IfoExecutedMailSendService.class);
            
                //1.8.5 約定メール送信テーブルに約定メ-ル行を挿入する    
                l_mailSendService.sendMailProcess(l_newOrderUnit, null);
            }
        }
        //1.9 取消失敗
        else if (WEB3IfoCanmodReceiptTypeDef.CANCELED_FAILED.equals(l_strDivision))
        {
            //getＷ指値用有効状態区分(IfoOrderUnit)
            //W指値の有効状態区分を取得する。 
            //[引数] 
            //注文単位：　@注文単位
            String l_strEnableStatusDiv =
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv((IfoOrderUnit)l_reOrderUnit);

            //ストップ注文失効となる注文（getW指値用有効状態区分() == "リミット注文有効"）の場合
            if (WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strEnableStatusDiv))
            {
                WEB3GentradeSubAccount l_subAccount = null;
                try
                {
                    l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                        l_orderUnit.getAccountId(),
                        l_orderUnit.getSubAccountId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //getストップ注文失効時概算代金計算結果(IfoOrderUnit, 補助口座)
                //ストップ注文失効時の概算受渡代金計算結果を取得する。
                //[引数]
                //注文単位：　@注文単位
                //補助口座：　@注文単位.補助口座IDに該当する補助口座オブジェクト
                WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult =
                    l_orderMgr.getStopOrderExpireEstimatedPrice((IfoOrderUnit)l_reOrderUnit, l_subAccount);

                double l_dblCalcUnitPrice = ((IfoOrderUnitRow)l_reOrderUnit.getDataSourceObject()).getPrice();
                double l_dblEstimatedPrice = ((IfoOrderUnitRow)l_reOrderUnit.getDataSourceObject()).getEstimatedPrice();
                if (l_estimateDeliveryAmountCalcResult != null)
                {
                    l_dblCalcUnitPrice = l_estimateDeliveryAmountCalcResult.getCalcUnitPrice();
                    l_dblEstimatedPrice = l_estimateDeliveryAmountCalcResult.getEstimateDeliveryAmount();
                }

                //set計算単価(double)
                //計算単価をセットする。
                //[引数]
                //計算単価：　@getストップ注文失効時概算代金計算結果()の戻り値.計算単価
                //※戻り値がnullだった場合は、注文単位.注文単価をセット。
                l_ifoChangeCancelNotifyInterceptor.setCalcUnitPrice(l_dblCalcUnitPrice);

                //set概算受渡代金(double)
                //概算受渡代金をセットする。
                //[引数]
                //概算受渡代金：　@getストップ注文失効時概算代金計算結果()の戻り値.概算受渡代金
                //※戻り値がnullだった場合は、注文単位.概算受渡代金をセット。
                l_ifoChangeCancelNotifyInterceptor.setEstimateDeliveryAmount(l_dblEstimatedPrice);
            }
            
            IfoOrderUnit l_ifoOrderUnit = null;
            try
            {
                l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            } 
            catch (NotFoundException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    getClass().getName() + STR_METHOD_NAME);
            }
            
	        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

            //SONAR入力の取消（isSONAR取消()の戻り値 == true）の場合
            if (l_orderMgr.isSONARCancel((IfoOrderUnit)l_orderUnit))
            {
                //1.9.3.notifyルールエンジンサーバ(IfoOrderUnit, OrderManagerPersistenceContext)
                //(OP注文マネージャ::notifyルールエンジンサーバ)
        		//[引数の設定] 
        		// 注文単位：　@注文単位オブジェクト 
        		// 処理：　@ CANCEL_ORDER_REJECTED_BY_MKT
            	//1.9.4.notifyルールエンジンサーバ()にて業務エラーがスローされた場合
                try
                {
                    l_orderMgr.notifyRLS(l_ifoOrderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_REJECTED_BY_MKT); 
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                	log.debug("notifyルールエンジンサーバ()にて業務エラー");
                	log.debug(this.getClass().getName() + STR_METHOD_NAME);
                }
            }

            //1.9.1 取消結果（取消失敗）オブジェクトを生成する
            DefaultCancelOrderRejectedMarketResponseMessage l_rejectedResponseMessage =
                new DefaultCancelOrderRejectedMarketResponseMessage(l_lngOrderId);
            //1.9.2 取消失敗を注文に更新する             
            ProcessingResult l_result = l_callbackServiceImpl.process(l_rejectedResponseMessage); 
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        WEB3GentradeSubAccount l_subAccount = null;
        try
        { 
            //顧客Id
            long l_lngMainAccountId = l_reOrderUnit.getAccountId();
            //取得補助口座         
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().
               getSubAccount(l_lngMainAccountId, l_reOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("データ不整合エラー。", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe); 
        }

        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
        {
			WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
				(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
			l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        }
        log.exiting(STR_METHOD_NAME);
        return WEB3StatusDef.DEALT;
    }
   
    /**
     * (is訂正更新済)<BR>
     * 指定注文単位が訂正更新済みかを判定する。<BR>
     * <BR>
     * －訂正更新済みの場合はtrueを返却する。<BR>
     * －訂正更新していない（HOSTからの訂正入力）場合はfalseを返却する。<BR>
     * <BR>
     * 引数の注文単位オブジェクト.注文状態が以下の場合trueを返却する。<BR>
     * 　@”受付済（変更注文）”：OrderStatusEnum.MODIFY_ACCEPTED<BR>
     * 　@”発注中（変更注文）”：OrderStatusEnum.MODIFYING<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@param l_orderUnit - 注文単位
     * @@return boolean
     * @@roseuid 4084F4EB01FC
     */
    protected  boolean isChangeUpdateEnd(OrderUnit l_orderUnit)     
    {
  
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if ((OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)) ||
           (OrderStatusEnum.MODIFYING.equals(l_orderStatus)))
        {
            log.debug("Enter the path that the value is ture.");
            return true;      
        }
        else
        {
            log.debug("Enther the path that the value is false."); 
            return false;
        }
    }
    
    /**
     * (is取消更新済)<BR>
     * 指定注文単位が取消更新済みかを判定する。<BR>
     * <BR>
     * －取消更新済みの場合はtrueを返却する。<BR>
     * －取消更新していない（HOSTからの取消入力）場合はfalseを返却する。<BR>
     * <BR>
     * 引数の注文単位オブジェクト.注文状態が以下の場合trueを返却する。<BR>
     * 　@”受付済（取消注文）”：OrderStatusEnum.CANCEL_ACCEPTED<BR>
     * 　@”発注中（取消注文）”：OrderStatusEnum.CANCELLING<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@param l_orderUnit - 注文単位
     * @@return boolean
     * @@roseuid 4084F6F90299
     */
    public boolean isCancelUpdateEnd(OrderUnit l_orderUnit) 
    {
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if ((OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)) ||
           (OrderStatusEnum.CANCELLING.equals(l_orderStatus)))
        {
            log.debug("Enter the path that the value is ture."); 
            return true;      
        }
        else
        {
            log.debug("Enter the path that the value is false."); 
            return false;
        }
    }
}
@
