head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundMarketRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信市場リクエスト送信サービスクラス(WEB3MutualFundMarketRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 王蘭芬(中訊) 新規作成
Revesion History : 2004/08/23 于美麗 (中訊) レビュー 
Revesion History : 2004/12/09 于美麗 (中訊) 残対応
Revesion History : 2006/05/24 韋念瓊 (中訊) ＤＢ更新仕様 No.075
Revesion History : 2006/10/12 周捷 (中訊) モデル 498
Revesion History : 2006/10/25  張騰宇 (中訊) モデル 511 ＤＢ更新仕様 084
Revesion History : 2006/11/24 松本 (SRA) ＤＢ更新仕様 086
Revesion History : 2007/02/05  張騰宇 (中訊) モデル 533
Revesion History : 2007/04/10 趙林鵬 (中訊) モデル 559 ＤＢ更新仕様 091
Revesion History : 2007/06/19 キョウ再平 (中訊) モデル 568 ＤＢ更新仕様 092
*/

package webbroker3.mf.marketadaptor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundMarketRequestSenderService;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrder;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.market.messages.MutualFundNewOrderMarketRequestMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3ExecuteDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3InputUnitDef;
import webbroker3.common.define.WEB3MfRecruitMqSendDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PrDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3SpecifyDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3SwtDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.data.HostFrgnMmfOrderParams;
import webbroker3.mf.data.HostFrgnMmfOrderRow;
import webbroker3.mf.data.HostXbmfOrderCancelParams;
import webbroker3.mf.data.HostXbmfOrderParams;
import webbroker3.mf.data.HostXbmfOrderRow;
import webbroker3.mf.define.WEB3MFBuyDivDef;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.mf.define.WEB3MFSettlementDivDef;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信市場リクエスト送信サービスクラス。<BR>
 *
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundMarketRequestService implements MutualFundMarketRequestSenderService
{

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundMarketRequestService.class);

    /**
     * (投信市場リクエスト送信サービス)<BR>
     * コンストラクタ。<BR>
     * @@roseuid 40B5BBBA0196
     */
    public WEB3MutualFundMarketRequestService()
    {

    }

    /**
     * (取消注文市場リクエストメッセージ送信)<BR>
     * （sendの実装）<BR>
     * <BR>
     * 指定の取消注文市場リクエストメッセージを市場へ送信する。<BR>
     * <BR>
     * １）　@注文取得<BR>
     * 　@－取消注文市場リクエストメッセージ.getOrderId() にて注文IDを取得する。<BR>
     * 　@－拡張投信注文マネージャ.getOrder()をコールして、投信注文オブジェクトを取得す・
     *
     * 。<BR>
     * 　@　@［getOrderに渡すパラメタ］<BR>
     * 　@　@　@注文ID： 取得した注文ID<BR>
     * 　@－取得した投信注文オブジェクト.getOrderUnits()をコールし、<BR>
     * 投信注文単位オブジェクトの配列を取得する。<BR>
     * <BR>
     * ２）　@取消注文市場リクエストメッセージ送信<BR>
     * ２－１）　@is市場未送信の値がtrueの場合<BR>
     * 　@－取消注文市場リクエストメッセージ送信（市場送信なし）()をコールする。<BR>
     * 　@［取消注文市場リクエストメッセージ送信（市場送信なし）に渡すパラメタ］<BR>
     * 　@　@投信注文単位： 取得した投信注文単位オブジェクトの配列[0]<BR>
     * 　@　@補助口座： 取消注文市場リクエストメッセージ.getSubAccount()の戻り値<BR>
     * 　@（シーケンス図「（投信市場リクエスト）取消注文送信（市場送信なし）」を参照）<BR>
     * <BR>
     * ２－２）　@引数.is市場未送信の値がfalseの場合<BR>
     * 　@－取消注文市場リクエストメッセージ送信（市場送信あり）()をコールする。<BR>
     * 　@［取消注文市場リクエストメッセージ送信（市場送信あり）に渡すパラメタ］<BR>
     * 　@　@投信注文単位： 取得した投信注文単位オブジェクトの配列[0]<BR>
     * 　@　@補助口座： 取消注文市場リクエストメッセージ.getSubAccount()の戻り値<BR>
     * 　@（シーケンス図「（投信市場リクエスト）取消注文送信（市場送信あり）」を参照）<BR>
     * <BR>
     * ２－３）　@新規注文市場リクエストメッセージ送信の各メソッドが正常終了した場合、<BR>
     * 　@DefaultMarketRequestSendResult.newSuccessResultInstance()の戻り値を返す。<BR>
     * 　@［newSuccessResultInstanceに渡すパラメタ］<BR>
     * 　@　@メッセージトークンID： 0<BR>
     * <BR>
     * ２－４）　@新規注文市場リクエストメッセージ送信の各メソッドが例外をスローした場合
     * <BR>
     * 　@－ProcessingResult.newFailedResultInstance()をコールして<BR>
     * ProcessingResultオブジェクトを生成する。<BR>
     * 　@　@［newFailedResultInstanceに渡すパラメタ］<BR>
     * 　@　@　@エラー情報： 例外オブジェクト.getErrorInfo()の戻り値<BR>
     * 　@－DefaultMarketRequestSendResult.newFailedResultInstance()の戻り値を返す。<BR>
     * 　@　@［newFailedResultInstanceに渡すパラメタ］<BR>
     * 　@　@　@オペレーション結果： 生成したProcessingResultオブジェクト<BR>
     * @@param l_marketRequest - 取消注文市場リクエストメッセージ<BR>
     * @@param l_blnIsMarketNotSendMessage - (is市場未送信)<BR>
     * 注文が市場に未送信で送信サービスにローカル取消をするよう通知する場合は
     * true を指定する。<BR>
     * falseに指定の場合は市場へ取消メッセージを送信する。<BR>
     *
     * @@return MarketRequestSendResult
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException
     * @@roseuid 40B5BBBA01B5
     */
    public MarketRequestSendResult send(
        CancelOrderMarketRequestMessage l_marketRequest,
        boolean l_blnIsMarketNotSendMessage)
    {
        final String l_strMethodName = "send("
            + "CancelOrderMarketRequestMessage l_marketRequest, "
            + "boolean l_blnIsMarketNotSendMessage)";
        log.entering(l_strMethodName);

        if (l_marketRequest == null)
        {
            log.debug("パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // １）　@注文取得
        // －取消注文市場リクエストメッセージ.getOrderId() にて注文IDを取得する。
        long l_lngOrderId = l_marketRequest.getOrderId();
        // －拡張投信注文マネージャ.getOrder()をコールして、投信注文オブジェクトを取得する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundOrderManager l_mfOrderManager =
            (WEB3MutualFundOrderManager)l_tradingModule.getOrderManager();
        try
        {
            MutualFundOrder l_mfOrder =
                (MutualFundOrder)l_mfOrderManager.getOrder(l_lngOrderId);
            // －取得した投信注文オブジェクト.getOrderUnits()をコールし、投信注文単位オブジェクトの配列を取得する。
            OrderUnit[] l_orderUnits = l_mfOrder.getOrderUnits();
            MutualFundOrderUnit[] l_mfOrderUnits = new MutualFundOrderUnit[l_orderUnits.length];
            for (int i = 0; i < l_orderUnits.length; i ++)
            {
                l_mfOrderUnits[i] = (MutualFundOrderUnit)l_orderUnits[i];
            }
            // ２）　@取消注文市場リクエストメッセージ送信
            // ２－１）　@is市場未送信の値がtrueの場合
            if (l_blnIsMarketNotSendMessage)
            {
                // －取消注文市場リクエストメッセージ送信（市場送信なし）()をコールする。
                this.cancelOrderMarketRequestSendMessageNotSubmit(
                    l_mfOrderUnits[0],
                    l_marketRequest.getSubAccount());
            }
            // ２－２）　@引数.is市場未送信の値がfalseの場合
            else
            {
                // －取消注文市場リクエストメッセージ送信（市場送信あり）()をコールする。
                this.cancelOrderMarketRequestSendMessageSubmit(
                    l_mfOrderUnits[0],
                    l_marketRequest.getSubAccount());
            }
            // ２－３）　@新規注文市場リクエストメッセージ送信の各メソッドが正常終了した場合、
            // DefaultMarketRequestSendResult.newSuccessResultInstance()の戻り値を返す。
            MarketRequestSendResult l_sendResult = DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
            return l_sendResult;
        }
        catch (NotFoundException l_ex)
        {
            log.error("該当する投信注文単位オブジェクトまたは投信注文オブジェクトがありません for OrderId = "
                + l_lngOrderId);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (WEB3BaseException l_ex)
        {
            // ２－４）　@新規注文市場リクエストメッセージ送信の各メソッドが例外をスローした場合
            // －ProcessingResult.newFailedResultInstance()をコールしてProcessingResultオブジェクトを生成する。
            // －DefaultMarketRequestSendResult.newFailedResultInstance()の戻り値を返す。
            ProcessingResult l_sendResult =
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo());
            MarketRequestSendResult l_returnResult = DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
            return l_returnResult;
        }
        finally
        {
            log.exiting(l_strMethodName);
        }
    }

    /**
     * (新規注文市場リクエストメッセージ送信)<BR>
     * （send(MutualFundNewOrderMarketRequestMessage)の実装）<BR>
     * <BR>
     * 指定の投信注文マーケットリクエストメッセージを市場へ送信する。<BR>
     * <BR>
     * １）　@注文取得<BR>
     * 　@－投信新規注文市場リクエストメッセージ.getOrderUnitId()
     * にて注文単位IDを取得する。<BR>
     * 　@－拡張投信注文マネージャ.getOrderUnit()をコールして、投信注文単位オブジェクト・
     * 取得する。<BR>
     * 　@　@［getOrderUnitに渡すパラメタ］<BR>
     * 　@　@　@注文単位ID： 取得した注文単位ID<BR>
     * <BR>
     * ２）　@新規注文市場リクエストメッセージ送信<BR>
     * ２－１）　@取得した投信注文単位.getDataSourceObject().get注文経路区分()の戻り値が
     * <BR>
     * 　@WEB3OrderRootDivDef.HOSTと等しくない場合（＝WebⅢ入力注文）<BR>
     * <BR>
     * 　@－新規注文市場リクエストメッセージ送信()をコールする。<BR>
     * 　@［新規注文市場リクエストメッセージ送信（買付・解約・乗換）に渡すパラメタ］<BR>
     * 　@　@投信注文単位： 取得した投信注文単位オブジェクト<BR>
     * 　@　@補助口座： 投信新規注文市場リクエストメッセージ.getSubAccount()の戻り値<BR>
     * 　@　@（シーケンス図「（投信市場リクエスト）新規注文送信（買付・解約・乗換）」を参
     * 照）<BR>
     * <BR>
     * ２－２）　@取得した投信注文単位.getDataSourceObject().get注文経路区分()の戻り値が
     * <BR>
     * 　@WEB3OrderRootDivDef.HOSTと等しい場合（＝SONAR入力注文）<BR>
     * <BR>
     * 　@－新規注文市場リクエストメッセージ送信（売買注文通知）()をコールする。<BR>
     * 　@　@［新規注文市場リクエストメッセージ送信（売買注文通知）に渡すパラメタ］<BR>
     * 　@　@　@投信注文単位： 取得した投信注文単位オブジェクト<BR>
     * 　@　@（シーケンス図「（投信市場リクエスト）新規注文送信（売買注文通知）」を参照）
     * <BR>
     * ２－３）　@新規注文市場リクエストメッセージ送信の各メソッドが正常終了した場合、<BR>
     * 　@DefaultMarketRequestSendResult.newSuccessResultInstance()の戻り値を返す。<BR>
     * 　@［newSuccessResultInstanceに渡すパラメタ］<BR>
     * 　@　@メッセージトークンID： 0<BR>
     * <BR>
     * ２－４）　@新規注文市場リクエストメッセージ送信の各メソッドが例外をスローした場合
     * <BR>
     * 　@－ProcessingResult.newFailedResultInstance()をコールして<BR>
     * ProcessingResultオブジェクトを生成する。<BR>
     * 　@　@［newFailedResultInstanceに渡すパラメタ］<BR>
     * 　@　@　@エラー情報： 例外オブジェクト.getErrorInfo()の戻り値<BR>
     * 　@－DefaultMarketRequestSendResult.newFailedResultInstance()の戻り値を返す。<BR>
     * 　@　@［newFailedResultInstanceに渡すパラメタ］<BR>
     * 　@　@　@オペレーション結果： 生成したProcessingResultオブジェクト<BR>
     * @@param l_marketRequest - 投信新規注文市場リクエストメッセージ<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@roseuid 40B5BBBA01D4
     */
    public MarketRequestSendResult send(
        MutualFundNewOrderMarketRequestMessage l_marketRequest)
    {
        final String l_strMethodName = "send("
            + "MutualFundNewOrderMarketRequestMessage "
            + "l_mutualFundNewOrderMarketRequestMessage)";
        log.entering(l_strMethodName);

        if (l_marketRequest == null)
        {
            log.debug("パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // １）　@注文取得
        // －投信新規注文市場リクエストメッセージ.getOrderUnitId() にて注文単位IDを取得する。
        long l_lngOrderUnitId = l_marketRequest.getOrderUnitId();
        // －拡張投信注文マネージャ.getOrderUnit()をコールして、投信注文単位オブジェクトを取得する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundOrderManager l_mfOrderManager =
            (WEB3MutualFundOrderManager)l_tradingModule.getOrderManager();
        try
        {
            MutualFundOrderUnit l_mfOrderUnit = (MutualFundOrderUnit)l_mfOrderManager.getOrderUnit(l_lngOrderUnitId);
            // ２）　@新規注文市場リクエストメッセージ送信
            // ２－１）　@取得した投信注文単位.getDataSourceObject().get注文経路区分()の戻り値が
            //      WEB3OrderRootDivDef.HOSTと等しくない場合（＝WebⅢ入力注文）
            String l_strOrderRootDiv =
                ((MutualFundOrderUnitRow)l_mfOrderUnit.getDataSourceObject()).getOrderRootDiv();
            if (!WEB3OrderRootDivDef.HOST.equals(l_strOrderRootDiv))
            {
                // - 新規注文市場リクエストメッセージ送信（買付・解約・乗換）をコールする。
                this.newOrderMarketRequestSendMessageAcquiredSellSwt(
                    l_mfOrderUnit,
                    l_marketRequest.getSubAccount()
                );
            }
            // ２－２）　@取得した投信注文単位.getDataSourceObject().get注文経路区分()の戻り値が
            //      WEB3OrderRootDivDef.HOSTと等しい場合（＝SONAR入力注文）
            else
            {
                // －新規注文市場リクエストメッセージ送信（売買注文通知）()をコールする。
                this.newOrderMarketRequestSendMessageTradeOrderNotify(l_mfOrderUnit);
            }

            // ２－３）　@新規注文市場リクエストメッセージ送信の各メソッドが正常終了した場合、
            //  DefaultMarketRequestSendResult.newSuccessResultInstance()の戻り値を返す。
            MarketRequestSendResult l_sendResult = 
                DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
            return l_sendResult;
        }
        catch (NotFoundException l_ex)
        {
            log.error("該当する投信注文単位オブジェクトありません for OrderUnitId = "
                + l_lngOrderUnitId);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (WEB3BaseException l_ex)
        {
            // ２－４）　@新規注文市場リクエストメッセージ送信の各メソッドが例外をスローした場合
            // －ProcessingResult.newFailedResultInstance()をコールしてProcessingResultオブジェクトを生成する。
            // －DefaultMarketRequestSendResult.newFailedResultInstance()の戻り値を返す。
            ProcessingResult l_sendResult =
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo());
            MarketRequestSendResult l_returnResult = 
                DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
            return l_returnResult;
        }
        finally
        {
            log.exiting(l_strMethodName);
        }
    }

    /**
     * (新規注文市場リクエストメッセージ送信（買付・解約・乗換）)<BR>
     * 新規注文市場リクエストメッセージ送信<BR>
     * <BR>
     * 買付注文、解約、乗換の市場リクエストメッセージを送信する。<BR>
     * <BR>
     * １）　@キューデータインサート<BR>
     * 　@引数.投信注文単位の内容をもとに、投信注文キューテーブ<BR>
     * 　@に注文データを挿入する。<BR>
     * 　@(*) 引数.投信注文単位.getOrderType()の戻り値が<BR>
     * 　@　@OrderTypeEnum.投資信託買注文の場合は、DB更新仕様「投資信託買付_投信注文<BR>
     * 　@　@キューテーブル.xls」参照。<BR>
     * 　@(*) 引数.投信注文単位.getOrderType()の戻り値が<BR>
     * 　@　@OrderTypeEnum.投資信託売注文でかつ<BR>
     * 　@　@引数.投信注文単位.getDataSourceObject().get銘柄コード（乗換先）()<BR>
     * 　@　@の戻り値がnullの場合は、DB更新仕様「投資信託解約_投信注文キューテーブル.xls・
     * 参照。<BR>
     * 　@(*) 引数.投信注文単位.getOrderType()の戻り値が<BR>
     * 　@　@OrderTypeEnum.投資信託売注文でかつ<BR>
     * 　@　@引数.投信注文単位.getDataSourceObject().get銘柄コード（乗換先）()<BR>
     * 　@　@の戻り値がnullでない場合は、DB更新仕様「投資信託乗換_投信注文キューテーブル.
     * xls」参照。<BR>
     *   (*) 引数.投信注文単位.getOrderType()の戻り値が <BR>
     * 　@　@OrderTypeEnum.投資信託募集注文の場合は、DB更新仕様 <BR>
     *      「投資信託募集_投信注文キューテーブル.xls」参照。<BR>
     * <BR>
     * ２）　@トリガ発行<BR>
     * 　@－引数.投信注文単位.get受注日時()の戻り値をYYYYMMDD形式に変換したものと<BR>
     * 　@　@引数.投信注文単位.getDataSourceObject().getBizDate()の戻り値を比較し、<BR>
     * 　@　@値が異なる場合は以降の処理を行わない。<BR>
     * <BR>
     * 　@－トリガを発行するかのチェック<BR>
     * 　@　@投信取引時間管理.isトリガ発行()をコールし、戻り値がtrueであればトリガを<BR>
     * 　@　@発行する。戻り値がfalseであれば以降の処理を行わない。<BR>
     * 　@　@　@［isトリガ発行に渡すパラメタ］<BR>
     * 　@　@　@　@発注条件：<BR>
     * 　@　@　@　@　@”0 ： DEFAULT”<BR>
     * <BR>
     * 　@－WEB3MQMessageSpecの生成<BR>
     * 　@　@WEB3MQMessageSpecを生成する。<BR>
     * 　@　@[コンストラクタに渡すパラメタ]<BR>
     * 　@　@　@証券会社コード：
     * 引数.補助口座.getInstitution().getInstitutionCode()の戻り値<BR>
     * 　@　@　@データコード： 投信注文キューテーブルのデータコードの値<BR>
     * <BR>
     * 　@－トリガを発行する。<BR>
     * 　@　@WEB3MQGatewayServiceImpl.send()をコールし、トリガを発行する。<BR>
     * 　@　@［sendに渡すパラメタ］<BR>
     * 　@　@　@MQに送信するメッセージのスペック：
     * 生成したWEB3MQMessageSpecオブジェクト<BR>
     * @@param l_mutualFundOrderUnit - 投信注文単位<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B5BFAE03B8
     */
    public void newOrderMarketRequestSendMessageAcquiredSellSwt(
        MutualFundOrderUnit l_mutualFundOrderUnit, SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String l_strMethodName = "newOrderMarketRequestSendMessageAcquiredSellSwt("
            + "MutualFundOrderUnit l_mutualFundOrderUnit, "
            + "SubAccount l_subAccount)";
        log.entering(l_strMethodName);

        if (l_subAccount == null || l_mutualFundOrderUnit == null)
        {
            log.debug("パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        try
        {
            // １）　@キューデータインサート
            HostFrgnMmfOrderParams l_hostFrgnMmfOrderParams =
                new HostFrgnMmfOrderParams();
            HostXbmfOrderParams l_hostOrderParams = new HostXbmfOrderParams();

            MutualFundOrderUnitRow l_mfOderUnitRow =
                (MutualFundOrderUnitRow) l_mutualFundOrderUnit.getDataSourceObject();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
            WEB3MutualFundProductManager l_mfProductManager =
                (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
            OrderTypeEnum l_orderType = l_mfOderUnitRow.getOrderType();

            //引数.投信注文単位オブジェクト.投信タイプ = "外貨MMF"の場合
            if (MutualFundTypeEnum.FOREIGN_MMF.equals(l_mfOderUnitRow.getFundType()))
            {
                // 引数.投信注文単位オブジェクト.getOrderType()の戻り値が
                //  OrderTypeEnum.投資信託買注文の場合は、
                //  DB更新仕様「投資信託買付_外貨MMF注文キューテーブル.xls」参照。
                if (OrderTypeEnum.MF_BUY.equals(l_orderType))
                {
                    WEB3MutualFundProduct l_mfProduct =
                        (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                            l_mfOderUnitRow.getProductId());

                    //部店を取得する
                    Branch l_banch =
                        l_accountManager.getBranch(l_mfOderUnitRow.getBranchId());
                    //証券会社コード
                    String l_strInstitutionCode =
                        l_banch.getInstitution().getInstitutionCode();
                    //顧客コード
                    String l_strAccountCode =
                        l_accountManager.getMainAccount(
                            l_mfOderUnitRow.getAccountId()).getAccountCode();
                    //DB更新仕様「投資信託買付_外貨MMF注文キューテーブル.xls」参照。
                    //データコード  DI821
                    l_hostFrgnMmfOrderParams.setRequestCode(WEB3HostRequestCodeDef.FOREIGN_MMF);
                    //証券会社コード 投信注文単位.部店IDに該当する証券会社コード
                    l_hostFrgnMmfOrderParams.setInstitutionCode(l_strInstitutionCode);
                    //部店コード 投信注文単位.部店IDに該当する部店コード
                    l_hostFrgnMmfOrderParams.setBranchCode(l_banch.getBranchCode());
                    //顧客コード 投信注文単位.口座IDに該当する顧客コード
                    l_hostFrgnMmfOrderParams.setAccountCode(l_strAccountCode);
                    //扱者コード 投信注文単位.取引者IDに該当する扱者コード
                    if (!l_mfOderUnitRow.getTraderIdIsNull())
                    {
                        String l_strTraderCode =
                            l_finObjMgr.getTrader(
                                l_mfOderUnitRow.getTraderId()).getTraderCode();
                        l_hostFrgnMmfOrderParams.setTraderCode(l_strTraderCode);
                    }
                    else
                    {
                        l_hostFrgnMmfOrderParams.setTraderCode(null);
                    }
                    //銘柄コード 投信注文単位.銘柄IDに該当する銘柄コード
                    l_hostFrgnMmfOrderParams.setProductCode(l_mfProduct.getProductCode());
                    //指定 投信注文単位.注文数量タイプが数量の場合”1：口数”金額ならば”2：金額”
                    QuantityTypeEnum l_quantityTypeEnum = l_mfOderUnitRow.getQuantityType();
                    if (QuantityTypeEnum.QUANTITY.equals(l_quantityTypeEnum))
                    {
                        l_hostFrgnMmfOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.COUNT);
                    }
                    if (QuantityTypeEnum.AMOUNT.equals(l_quantityTypeEnum))
                    {
                        l_hostFrgnMmfOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.MONEY);
                    }
                    //売数量 0
                    l_hostFrgnMmfOrderParams.setSellOrderQuantity(0);
                    //買数量 投信注文単位.注文数量
                    l_hostFrgnMmfOrderParams.setBuyOrderQuantity((long)l_mfOderUnitRow.getQuantity());
                    //伝票№ 9 + 識別コードの下3桁
                    l_hostFrgnMmfOrderParams.setTicketNumber(
                        Integer.parseInt("9" + l_mfOderUnitRow.getOrderRequestNumber().substring(6)));
                    //買付区分 ブランク
                    l_hostFrgnMmfOrderParams.setBuyDiv(WEB3MFBuyDivDef.HALF_SPACE);
                    //決済 投信注文単位.決済区分が円貨の場合"0:円貨決済"外貨の場合"1:外貨決済"
                    if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_mfOderUnitRow.getSettlementDiv()))
                    {
                        l_hostFrgnMmfOrderParams.setSettlementDiv(WEB3MFSettlementDivDef.EN_SETTLE);
                    }
                    else if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_mfOderUnitRow.getSettlementDiv()))
                    {
                        l_hostFrgnMmfOrderParams.setSettlementDiv(WEB3MFSettlementDivDef.FOREIGN_SETTLE);
                    }
                    //実行 1：実行 
                    l_hostFrgnMmfOrderParams.setExecuteDiv(WEB3ExecuteDivDef.EXECUTE);
                    //識別コード 投信注文単位.識別番号
                    l_hostFrgnMmfOrderParams.setOrderRequestNumber(l_mfOderUnitRow.getOrderRequestNumber());
                    //受注日 投信注文単位.受注日時
                    l_hostFrgnMmfOrderParams.setCreateDate(l_mfOderUnitRow.getCreatedTimestamp());
                    //受注時間 投信注文単位.受注日時
                    l_hostFrgnMmfOrderParams.setCreateTime(l_mfOderUnitRow.getCreatedTimestamp());
                    //注文日 投信注文単位.発注日
                    l_hostFrgnMmfOrderParams.setOrderDate(
                        WEB3DateUtility.getDate(l_mfOderUnitRow.getBizDate(), "yyyyMMdd"));
                    //処理区分 0：当日未処理
                    l_hostFrgnMmfOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);
                }
                // OrderTypeEnum.投資信託売注文の場合は、
                // DB更新仕様「投資信託解約_外貨MMF注文キューテーブル.xls」参照。
                if (OrderTypeEnum.MF_SELL.equals(l_orderType))
                {
                    WEB3MutualFundProduct l_mfProduct =
                        (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                            l_mfOderUnitRow.getProductId());

                    // 部店を取得する
                    Branch l_banch =
                        l_accountManager.getBranch(l_mfOderUnitRow.getBranchId());
                    //証券会社コード
                    String l_strInstitutionCode =
                        l_banch.getInstitution().getInstitutionCode();
                    //顧客コード
                    String l_strAccountCode =
                        l_accountManager.getMainAccount(
                            l_mfOderUnitRow.getAccountId()).getAccountCode();
                    //投資信託解約_外貨MMF注文キューテーブル
                    //データコード  DI821
                    l_hostFrgnMmfOrderParams.setRequestCode(WEB3HostRequestCodeDef.FOREIGN_MMF);
                    //証券会社コード 投信注文単位.部店IDに該当する証券会社コード
                    l_hostFrgnMmfOrderParams.setInstitutionCode(l_strInstitutionCode);
                    //部店コード 投信注文単位.部店IDに該当する部店コード
                    l_hostFrgnMmfOrderParams.setBranchCode(l_banch.getBranchCode());
                    //顧客コード 投信注文単位.口座IDに該当する顧客コード
                    l_hostFrgnMmfOrderParams.setAccountCode(l_strAccountCode);
                    //扱者コード 投信注文単位.取引者IDに該当する扱者コード
                    if (!l_mfOderUnitRow.getTraderIdIsNull())
                    {
                        String l_strTraderCode =
                            l_finObjMgr.getTrader(
                                l_mfOderUnitRow.getTraderId()).getTraderCode();
                        l_hostFrgnMmfOrderParams.setTraderCode(l_strTraderCode);
                    }
                    else
                    {
                        l_hostFrgnMmfOrderParams.setTraderCode(null);
                    }
                    //銘柄コード 投信注文単位.銘柄IDに該当する銘柄コード
                    l_hostFrgnMmfOrderParams.setProductCode(l_mfProduct.getProductCode());
                    //指定 投信注文単位.投信解約区分が口数指定、全部指定ならば”1：口数”
                    //金額指定ならば”2：金額”
                    String l_strSellDiv = l_mfOderUnitRow.getFundSellDiv();
                    if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_strSellDiv)
                        || WEB3SellDivDef.ALL_DESIGNATE.equals(l_strSellDiv))
                    {
                        l_hostFrgnMmfOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.COUNT);
                    }
                    if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strSellDiv))
                    {
                        l_hostFrgnMmfOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.MONEY);
                    }
                    //売数量 投信注文単位.注文数量
                    l_hostFrgnMmfOrderParams.setSellOrderQuantity((long)l_mfOderUnitRow.getQuantity());
                    //買数量 0
                    l_hostFrgnMmfOrderParams.setBuyOrderQuantity(0);
                    //伝票№ 9 + 識別コードの下3桁
                    l_hostFrgnMmfOrderParams.setTicketNumber(
                        Integer.parseInt("9" + l_mfOderUnitRow.getOrderRequestNumber().substring(6)));
                    //買付区分 ブランク
                    l_hostFrgnMmfOrderParams.setBuyDiv(WEB3MFBuyDivDef.HALF_SPACE);
                    //決済 投信注文単位.決済区分が円貨の場合"0:円貨決済"外貨の場合"1:外貨決済"
                    if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_mfOderUnitRow.getSettlementDiv()))
                    {
                        l_hostFrgnMmfOrderParams.setSettlementDiv(WEB3MFSettlementDivDef.EN_SETTLE);
                    }
                    else if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_mfOderUnitRow.getSettlementDiv()))
                    {
                        l_hostFrgnMmfOrderParams.setSettlementDiv(WEB3MFSettlementDivDef.FOREIGN_SETTLE);
                    }
                    //実行 1：実行
                    l_hostFrgnMmfOrderParams.setExecuteDiv(WEB3ExecuteDivDef.EXECUTE);
                    //識別コード 投信注文単位.識別番号
                    l_hostFrgnMmfOrderParams.setOrderRequestNumber(l_mfOderUnitRow.getOrderRequestNumber());
                    //受注日 投信注文単位.受注日時
                    l_hostFrgnMmfOrderParams.setCreateDate(l_mfOderUnitRow.getCreatedTimestamp());
                    //受注時間 投信注文単位.受注日時
                    l_hostFrgnMmfOrderParams.setCreateTime(l_mfOderUnitRow.getCreatedTimestamp());
                    //注文日 投信注文単位.発注日
                    l_hostFrgnMmfOrderParams.setOrderDate(
                        WEB3DateUtility.getDate(l_mfOderUnitRow.getBizDate(), "yyyyMMdd"));
                    //処理区分 0：当日未処理
                    l_hostFrgnMmfOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);
                }
                l_hostFrgnMmfOrderParams.markAllValuesAsSet();
                Processors.getDefaultProcessor().doInsertQuery(l_hostFrgnMmfOrderParams);
                log.debug("キューデータインサート...... with HostFrgnMmfOrderParams = " + l_hostFrgnMmfOrderParams);

                log.exiting(l_strMethodName);
                return;
            }
            
            
            // (*) 引数.投信注文単位.getOrderType()の戻り値が
            // 　@OrderTypeEnum.投資信託買注文の場合は、DB更新仕様「投資信託買付_投信注文キューテーブル.xls」参照。

            if (OrderTypeEnum.MF_BUY.equals(l_orderType))
            {
                WEB3MutualFundProduct l_mfProduct =
                    (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                        l_mfOderUnitRow.getProductId());
                
                //・投信銘柄.is外国投信()==false or
                //（投信銘柄.isFWF()==true and 投信銘柄.通貨コード == ”円”） の場合、
                //"CI801"をセット                
                boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();
                boolean l_blnIsFWF = l_mfProduct.isFWF();
                String l_strCruuencyCode = l_mfProduct.getCurrencyCode();
                
                if (!l_blnIsForeignFund || 
                    (l_blnIsFWF && WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    String l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_DOMESTIC_ORDER;
                    l_hostOrderParams.setRequestCode(l_strRequestCode);
                }
                //・投信銘柄.is外国投信()==true or
                //（投信銘柄.isFWF()==true and 投信銘柄.通貨コード != ”円”） の場合、
                //"CI803"をセット
                if (l_blnIsForeignFund || 
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {                
                    String l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_FOREIGN_ORDER;
                    l_hostOrderParams.setRequestCode(l_strRequestCode); 
                }
                
                // 部店を取得する
                Branch l_banch =
                    l_accountManager.getBranch(l_mfOderUnitRow.getBranchId());
                // 部店コード
                l_hostOrderParams.setBranchCode(l_banch.getBranchCode());
                // 証券会社コード
                String l_strInstitutionCode =
                    l_banch.getInstitution().getInstitutionCode();
                l_hostOrderParams.setInstitutionCode(l_strInstitutionCode);
                // 顧客コード
                String l_strAccountCode =
                    l_accountManager.getMainAccount(
                        l_mfOderUnitRow.getAccountId()).getAccountCode();
                l_hostOrderParams.setAccountCode(l_strAccountCode);
                // 扱者コード
                if (!l_mfOderUnitRow.getTraderIdIsNull())
                {
                    String l_strTraderCode =
                        l_finObjMgr.getTrader(
                            l_mfOderUnitRow.getTraderId()).getTraderCode();
                    l_hostOrderParams.setTraderCode(l_strTraderCode);
                }
                else
                {
                    l_hostOrderParams.setTraderCode(null);
                }
                // 銘柄コード
                l_hostOrderParams.setProductCode(l_mfProduct.getProductCode());
                // 指定
                QuantityTypeEnum l_quantityTypeEnum = l_mfOderUnitRow.getQuantityType();
                if (QuantityTypeEnum.QUANTITY.equals(l_quantityTypeEnum))
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.COUNT);
                }
                if (QuantityTypeEnum.AMOUNT.equals(l_quantityTypeEnum))
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.MONEY);
                }
                // 決済
                l_hostOrderParams.setSettlementDiv(l_mfOderUnitRow.getSettlementDiv());
                // 売数量
                l_hostOrderParams.setSellOrderQuantity(0);
                
                // 買数量                
                //注）投信銘柄は、投信注文単位.銘柄IDに該当する投信銘柄                
                String l_strInputUnit = l_mfProduct.getInputUnit();
                BigDecimal l_bdBuyOrderQuantity = new BigDecimal("0");
                BigDecimal l_bdCalcNumber1 = new BigDecimal ("1000");
                BigDecimal l_bdCalcNumber2 = new BigDecimal ("10000");
                BigDecimal l_bdQuantity = new BigDecimal(Double.toString(l_mfOderUnitRow.getQuantity()));
                
                //・投信銘柄.is外国投信()==true or
                //（投信銘柄.isFWF()==true and 投信銘柄.通貨コード != ”円”） の場合、
                
                if (l_blnIsForeignFund ||
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    //・投信銘柄.入力単位が”1：1”であれば
                    //    投信注文単位.注文数量　@×　@1000
                    if(WEB3InputUnitDef.ONE.equals(l_strInputUnit))
                    {
                        l_bdBuyOrderQuantity = l_bdQuantity.multiply(l_bdCalcNumber1);
                        
                        l_hostOrderParams.setBuyOrderQuantity(l_bdBuyOrderQuantity.longValue());
                    }
                    //・投信銘柄.入力単位が”2：1万”であれば
                    //    投信注文単位.注文数量 / 10000　@×　@1000
                    else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_strInputUnit))
                    {
                        l_bdBuyOrderQuantity =
                            l_bdQuantity.divide(l_bdCalcNumber2,10,BigDecimal.ROUND_HALF_UP).multiply(
                                l_bdCalcNumber1);
                                
                        l_hostOrderParams.setBuyOrderQuantity(l_bdBuyOrderQuantity.longValue());
                    }
                }
                //・それ以外の場合
                else
                {
                    //・投信銘柄.入力単位が”1：1”であれば
                    //　@　@投信注文単位.注文数量
                    if(WEB3InputUnitDef.ONE.equals(l_strInputUnit))
                    {
                        l_hostOrderParams.setBuyOrderQuantity(l_bdQuantity.longValue());
                    }
                    //・投信銘柄.入力単位が”2：1万”であれば
                    //　@　@投信注文単位.注文数量 / 10000
                    else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_strInputUnit))
                    {
                        l_bdBuyOrderQuantity =
                            l_bdQuantity.divide(l_bdCalcNumber2,10,BigDecimal.ROUND_HALF_UP);
                            
                        l_hostOrderParams.setBuyOrderQuantity(l_bdBuyOrderQuantity.longValue());
                    }               
                }
                
                // 伝票No.
                l_hostOrderParams.setTicketNumber(
                    Integer.parseInt("9" + l_mfOderUnitRow.getOrderRequestNumber().substring(6)));
                
                // 仕法@区分
                //・投信銘柄.is外国投信()==false or
                //（投信銘柄.isFWF()==true and 投信銘柄.通貨コード == ”円”） の場合、
                // ”1：本券”をセット。
                //・投信銘柄.is外国投信()==true or
                //（投信銘柄.isFWF()==true and 投信銘柄.通貨コード != ”円”） の場合、
                // "空白"をセット。
                if (!l_blnIsForeignFund || 
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    l_hostOrderParams.setPrDiv(WEB3PrDivDef.ISSUE_TICKET);
                }
                if (l_blnIsForeignFund || 
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    l_hostOrderParams.setPrDiv(" ");
                }
                // 無手数料区分
                l_hostOrderParams.setCommissionDiv(l_mfOderUnitRow.getNoContractCommissionDiv());

                // 預りチェック
                l_hostOrderParams.setDepositCheckDiv(" ");
                // 識別コード
                l_hostOrderParams.setOrderRequestNumber(l_mfOderUnitRow.getOrderRequestNumber());
                // 受注日/受注時間
                Date l_orderDate =
                    l_mfOderUnitRow.getReceivedDateTime();
                l_hostOrderParams.setCreateDate(l_orderDate);
                l_hostOrderParams.setCreateTime(l_orderDate);
                // 注文日
                Date l_bizDate = WEB3DateUtility.getDate(l_mfOderUnitRow.getBizDate(), "yyyyMMdd");
                l_hostOrderParams.setOrderDate(l_bizDate);
                // 乗換区分
                l_hostOrderParams.setSwtDiv(null);
                // 買付銘柄コード
                l_hostOrderParams.setSwtProductCode(null);
                // 特定口座区分
                TaxTypeEnum l_taxType = l_mfOderUnitRow.getTaxType();
                if (TaxTypeEnum.NORMAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.NORMAL);
                }
                if (TaxTypeEnum.SPECIAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.SPECIAL);
                }
                // 特定口座区分（乗換先）
                l_hostOrderParams.setSwtTaxType(null);
                // 請求区分
                l_hostOrderParams.setClaimDiv(" ");
                // 注文チャネル
                l_hostOrderParams.setOrderChanel(l_mfOderUnitRow.getOrderChanel());
                // 処理区分 = ”0：当日未処理 ”
                l_hostOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);
                // 入金日 = NULL
                l_hostOrderParams.setPaymentDate(null);
            }
            
            // (*) 引数.投信注文単位.getOrderType()の戻り値が
            // 　@OrderTypeEnum.投資信託売注文の場合は、
            //   DB更新仕様「投資信託解約_投信注文キューテーブル.xls」参照。            
            if (OrderTypeEnum.MF_SELL.equals(l_orderType))
            {
                WEB3MutualFundProduct l_mfProduct =
                    (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                        l_mfOderUnitRow.getProductId());
                
                //データコード
                //・投信銘柄.is外国投信()==false or
                //（投信銘柄.isFWF()==true and 投信銘柄.通貨コード == ”円”） の場合、
                //"CI801"をセット

                boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();
                boolean l_blnIsFWF = l_mfProduct.isFWF();
                String l_strCruuencyCode = l_mfProduct.getCurrencyCode();
                
                if (!l_blnIsForeignFund || 
                    (l_blnIsFWF && WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    String l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_DOMESTIC_ORDER;
                    l_hostOrderParams.setRequestCode(l_strRequestCode);
                }
                //・投信銘柄.is外国投信()==true or
                //（投信銘柄.isFWF()==true and 投信銘柄.通貨コード != ”円”） の場合、
                //"CI803"をセット
                if (l_blnIsForeignFund || 
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {                
					String l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_FOREIGN_ORDER;
					l_hostOrderParams.setRequestCode(l_strRequestCode);	
				}
                
                // 部店を取得する
                Branch l_banch =
                    l_accountManager.getBranch(l_mfOderUnitRow.getBranchId());
                // 部店コード
                l_hostOrderParams.setBranchCode(l_banch.getBranchCode());
                // 証券会社コード
                String l_strInstitutionCode =
                    l_banch.getInstitution().getInstitutionCode();
                l_hostOrderParams.setInstitutionCode(l_strInstitutionCode);
                // 顧客コード
                String l_strAccountCode =
                    l_accountManager.getMainAccount(
                        l_mfOderUnitRow.getAccountId()).getAccountCode();
                l_hostOrderParams.setAccountCode(l_strAccountCode);
                // 扱者コード
                if (!l_mfOderUnitRow.getTraderIdIsNull())
                {
                    String l_strTraderCode =
                        l_finObjMgr.getTrader(
                            l_mfOderUnitRow.getTraderId()).getTraderCode();
                    l_hostOrderParams.setTraderCode(l_strTraderCode);
                }
                else
                {
                    l_hostOrderParams.setTraderCode(null);
                }
                // 銘柄コード
                l_hostOrderParams.setProductCode(l_mfProduct.getProductCode());
                // 指定
                if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_mfOderUnitRow.getFundSellDiv())
                    && l_mfOderUnitRow.getQuantity() == 0)
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.ALL);
                }
                else
                {
                    QuantityTypeEnum l_quantityTypeEnum = l_mfOderUnitRow.getQuantityType();
                    if (QuantityTypeEnum.QUANTITY.equals(l_quantityTypeEnum))
                    {
                        l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.COUNT);
                    }
                    if (QuantityTypeEnum.AMOUNT.equals(l_quantityTypeEnum))
                    {
                        l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.MONEY);
                    }
                }
                // 決済
                l_hostOrderParams.setSettlementDiv(l_mfOderUnitRow.getSettlementDiv());
                
                // 売数量

                //注）投信銘柄は、投信注文単位.銘柄IDに該当する投信銘柄                
                String l_strInputUnit = l_mfProduct.getInputUnit();
                BigDecimal l_bdSellOrderQuantity = new BigDecimal("0");
                BigDecimal l_bdCalcNumber1 = new BigDecimal ("1000");
                BigDecimal l_bdCalcNumber2 = new BigDecimal ("10000");
                BigDecimal l_bdQuantity = new BigDecimal (Double.toString(l_mfOderUnitRow.getQuantity()));
                
                //・投信銘柄.is外国投信()==true or
                //（投信銘柄.isFWF()==true and 投信銘柄.通貨コード != ”円”） の場合、
                
                if (l_blnIsForeignFund ||
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    //  ・投信銘柄.入力単位が”1：1”であれば
                    //      投信注文単位.注文数量　@×　@1000
                    if(WEB3InputUnitDef.ONE.equals(l_strInputUnit))
                    {
                        l_bdSellOrderQuantity = l_bdQuantity.multiply(l_bdCalcNumber1);
                        
                        l_hostOrderParams.setSellOrderQuantity(l_bdSellOrderQuantity.longValue());
                    }
                    //  ・投信銘柄.入力単位が”2：1万”であれば
                    //      投信注文単位.注文数量 / 10000　@×　@1000
                    else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_strInputUnit))
                    {
                        l_bdSellOrderQuantity =
                            l_bdQuantity.divide(l_bdCalcNumber2,10,BigDecimal.ROUND_HALF_UP).multiply(
                                l_bdCalcNumber1);
                                
                        l_hostOrderParams.setSellOrderQuantity(l_bdSellOrderQuantity.longValue());
                    }
                }
                //・それ以外の場合
                else
                {
                    //  ・投信銘柄.入力単位が”1：1”であれば
                    //      投信注文単位.注文数量
                    if(WEB3InputUnitDef.ONE.equals(l_strInputUnit))
                    {
                        l_hostOrderParams.setSellOrderQuantity(l_bdQuantity.longValue());
                    }
                    //  ・投信銘柄.入力単位が”2：1万”であれば
                    //      投信注文単位.注文数量 / 10000
                    else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_strInputUnit))
                    {
                        l_bdSellOrderQuantity =
                            l_bdQuantity.divide(l_bdCalcNumber2,10,BigDecimal.ROUND_HALF_UP);
                              
                        l_hostOrderParams.setSellOrderQuantity(l_bdSellOrderQuantity.longValue());
                    }
                }
                
                // 買数量
                l_hostOrderParams.setBuyOrderQuantity(0);

                // 伝票No.
                l_hostOrderParams.setTicketNumber(
                    Integer.parseInt("9" + l_mfOderUnitRow.getOrderRequestNumber().substring(6)));

                // 仕法@区分
                //・投信銘柄.is外国投信()==false or
                //（投信銘柄.isFWF()==true and 投信銘柄.通貨コード == ”円”） の場合、
                // ”1：本券”をセット。
                //・投信銘柄.is外国投信()==true or
                //（投信銘柄.isFWF()==true and 投信銘柄.通貨コード != ”円”） の場合、
                // "空白"をセット。
                if (!l_blnIsForeignFund || 
                    (l_blnIsFWF && WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    l_hostOrderParams.setPrDiv(WEB3PrDivDef.ISSUE_TICKET);
                }
                if (l_blnIsForeignFund || 
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    l_hostOrderParams.setPrDiv(" ");
                }
                // 無手数料区分
                l_hostOrderParams.setCommissionDiv(l_mfOderUnitRow.getNoContractCommissionDiv());

                // 預りチェック = 空白
                l_hostOrderParams.setDepositCheckDiv(" ");
                
                // 識別コード
                l_hostOrderParams.setOrderRequestNumber(l_mfOderUnitRow.getOrderRequestNumber());
                // 受注日/受注時間
                Date l_orderDate =
                    l_mfOderUnitRow.getReceivedDateTime();
                l_hostOrderParams.setCreateDate(l_orderDate);
                l_hostOrderParams.setCreateTime(l_orderDate);
                // 注文日
                Date l_bizDate = WEB3DateUtility.getDate(l_mfOderUnitRow.getBizDate(), "yyyyMMdd");
                l_hostOrderParams.setOrderDate(l_bizDate);
                // 乗換区分
                l_hostOrderParams.setSwtDiv(null);
                // 買付銘柄コード
                l_hostOrderParams.setSwtProductCode(null);
                // 特定口座区分
                TaxTypeEnum l_taxType = l_mfOderUnitRow.getTaxType();
                if (TaxTypeEnum.NORMAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.NORMAL);
                }
                if (TaxTypeEnum.SPECIAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.SPECIAL);
                }
                // 特定口座区分（乗換先）
                l_hostOrderParams.setSwtTaxType(null);
                
                // 請求区分
                //・投信銘柄.isFWF() == true and 投信銘柄.通貨コード == ”円”の場合
                //”解約請求”
                if (l_blnIsFWF && WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode))
                {
                    l_hostOrderParams.setClaimDiv(WEB3ClaimDivDef.SELL);
                }
                //・それ以外の場合
                //    投信注文単位.請求区分
                else
                {
                    l_hostOrderParams.setClaimDiv(l_mfOderUnitRow.getRequestDiv());
                }

                // 注文チャネル
                l_hostOrderParams.setOrderChanel(l_mfOderUnitRow.getOrderChanel());
                // 処理区分 = ”0：当日未処理 ”
                l_hostOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);
                //入金日 = NULL
                l_hostOrderParams.setPaymentDate(null);                    
            }
            
            // (*) 引数.投信注文単位.getOrderType()の戻り値が
            // OrderTypeEnum.投資信託乗換注文の場合は、
            // DB更新仕様「投資信託乗換_投信注文キューテーブル.xls」参照。
            if (OrderTypeEnum.MF_SWITCHING.equals(l_orderType))
            {
                // データコード
                String l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_SWITCHING_ORDER;
                l_hostOrderParams.setRequestCode(l_strRequestCode);
                // 部店を取得する
                Branch l_banch =
                    l_accountManager.getBranch(l_mfOderUnitRow.getBranchId());
                // 部店コード
                l_hostOrderParams.setBranchCode(l_banch.getBranchCode());
                // 証券会社コード
                String l_strInstitutionCode =
                    l_banch.getInstitution().getInstitutionCode();
                l_hostOrderParams.setInstitutionCode(l_strInstitutionCode);
                // 顧客コード
                String l_strAccountCode =
                    l_accountManager.getMainAccount(
                        l_mfOderUnitRow.getAccountId()).getAccountCode();
                l_hostOrderParams.setAccountCode(l_strAccountCode);
                // 扱者コード
                if (!l_mfOderUnitRow.getTraderIdIsNull())
                {
                    String l_strTraderCode =
                        l_finObjMgr.getTrader(
                            l_mfOderUnitRow.getTraderId()).getTraderCode();
                    l_hostOrderParams.setTraderCode(l_strTraderCode);
                }
                else
                {
                    l_hostOrderParams.setTraderCode(null);
                }
                // 銘柄コード
                WEB3MutualFundProduct l_mfProduct =
                    (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                        l_mfOderUnitRow.getProductId());
                l_hostOrderParams.setProductCode(l_mfProduct.getProductCode());
                
                // 指定
                //・投信注文単位.注文数量タイプ==”数量” の場合、”口数”               
                QuantityTypeEnum l_quantityTypeEnum = l_mfOderUnitRow.getQuantityType();
                if (QuantityTypeEnum.QUANTITY.equals(l_quantityTypeEnum))
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.COUNT);
                }
                //・投信注文単位.注文数量タイプ==”金額” の場合、”金額”
                else if (QuantityTypeEnum.AMOUNT.equals(l_quantityTypeEnum))
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.MONEY);
                }
                //・投信注文単位.投信解約区分==”全部指定” and
                //  投信注文単位.注文数量==0 の場合、”全部”  
                String l_strSellDiv = l_mfOderUnitRow.getFundSellDiv();
                double l_dblQuantity = l_mfOderUnitRow.getQuantity();
                
                if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_strSellDiv) && l_dblQuantity == 0)
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.ALL);
                }
                
                // 決済
                l_hostOrderParams.setSettlementDiv(l_mfOderUnitRow.getSettlementDiv());
                
                // 売数量
                
                String l_strInputUnit = l_mfProduct.getInputUnit();
                BigDecimal l_bdSellOrderQuantity = new BigDecimal("0");
                BigDecimal l_bdCalcNumber2 = new BigDecimal ("10000");
                BigDecimal l_bdQuantity = new BigDecimal(Double.toString(l_mfOderUnitRow.getQuantity()));
                
                //・投信銘柄.入力単位が”1：1”であれば
                //　@　@投信注文単位.注文数量
                if(WEB3InputUnitDef.ONE.equals(l_strInputUnit))
                {
                    l_hostOrderParams.setSellOrderQuantity(l_bdQuantity.longValue());
                }
                //・投信銘柄.入力単位が”2：1万”であれば
                //　@　@投信注文単位.注文数量 / 10000
                else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_strInputUnit))
                {
                    l_bdSellOrderQuantity =
                        l_bdQuantity.divide(l_bdCalcNumber2,10,BigDecimal.ROUND_HALF_UP);
                    l_hostOrderParams.setSellOrderQuantity(l_bdSellOrderQuantity.longValue());
                }
               
                // 買数量
                l_hostOrderParams.setBuyOrderQuantity(0);

                // 伝票No.
                l_hostOrderParams.setTicketNumber(
                    Integer.parseInt("9" + l_mfOderUnitRow.getOrderRequestNumber().substring(6)));

                // 仕法@区分 ”1：本券”
                l_hostOrderParams.setPrDiv(WEB3PrDivDef.ISSUE_TICKET);

                // 無手数料区分
                l_hostOrderParams.setCommissionDiv(l_mfOderUnitRow.getNoContractCommissionDiv());

                // 預りチェック = 空白
                l_hostOrderParams.setDepositCheckDiv(" ");
                // 識別コード
                l_hostOrderParams.setOrderRequestNumber(l_mfOderUnitRow.getOrderRequestNumber());
                // 受注日/受注時間
                Date l_orderDate =
                    l_mfOderUnitRow.getReceivedDateTime();
                l_hostOrderParams.setCreateDate(l_orderDate);
                l_hostOrderParams.setCreateTime(l_orderDate);
                // 注文日
                Date l_bizDate = WEB3DateUtility.getDate(l_mfOderUnitRow.getBizDate(), "yyyyMMdd");
                l_hostOrderParams.setOrderDate(l_bizDate);
                // 乗換区分
                l_hostOrderParams.setSwtDiv(WEB3SwtDivDef.SWITCHING);
                // 買付銘柄コード
                l_hostOrderParams.setSwtProductCode(l_mfOderUnitRow.getSwtProductCode());
                // 特定口座区分
                TaxTypeEnum l_taxType = l_mfOderUnitRow.getTaxType();
                if (TaxTypeEnum.NORMAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.NORMAL);
                }
                if (TaxTypeEnum.SPECIAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.SPECIAL);
                }
                // 特定口座区分（乗換先）
                TaxTypeEnum l_strSwtTaxType = l_mfOderUnitRow.getSwtTaxType();
                if (TaxTypeEnum.NORMAL.equals(l_strSwtTaxType))
                {
                    l_hostOrderParams.setSwtTaxType(WEB3TaxTypeSpecialDef.NORMAL);
                }
                if (TaxTypeEnum.SPECIAL.equals(l_strSwtTaxType))
                {
                    l_hostOrderParams.setSwtTaxType(WEB3TaxTypeSpecialDef.SPECIAL);
                }
                
                // 請求区分
                //・投信銘柄.isFWF() == true and 投信銘柄.通貨コード == ”円”の場合
                //”解約請求”
                
                boolean l_blnIsFWF = l_mfProduct.isFWF();
                String l_strCruuencyCode = l_mfProduct.getCurrencyCode();
                
                if (l_blnIsFWF && WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode))
                {
                    l_hostOrderParams.setClaimDiv(WEB3ClaimDivDef.SELL);
                }
                //・それ以外の場合
                //    投信注文単位.請求区分
                else
                {
                    l_hostOrderParams.setClaimDiv(l_mfOderUnitRow.getRequestDiv());
                }
                
                // 注文チャネル
                l_hostOrderParams.setOrderChanel(l_mfOderUnitRow.getOrderChanel());
                // 処理区分”0：当日未処理 ”
                l_hostOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);
                //入金日 = NULL
                l_hostOrderParams.setPaymentDate(null);
                
            }
            
            //(*) 引数.投信注文単位.getOrderType()の戻り値が 
            // 　@　@OrderTypeEnum.投資信託募集注文の場合は、DB更新仕様 
            //      「投資信託募集_投信注文キューテーブル.xls」参照。
            if (OrderTypeEnum.MF_RECRUIT.equals(l_orderType))
            {
                //データコード = CI807：募集
                String l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_RECRUIT;
                l_hostOrderParams.setRequestCode(l_strRequestCode);
                
                // 部店を取得する
                Branch l_banch =
                    l_accountManager.getBranch(l_mfOderUnitRow.getBranchId());
                
                // 部店コード = 投信注文単位.部店IDに該当する部店コード
                l_hostOrderParams.setBranchCode(l_banch.getBranchCode());
                
                // 証券会社コード = 投信注文単位.部店IDに該当する証券会社コード
                String l_strInstitutionCode =
                    l_banch.getInstitution().getInstitutionCode();
                l_hostOrderParams.setInstitutionCode(l_strInstitutionCode);
                
                // 顧客コード = 投信注文単位.口座IDに該当する顧客コード
                String l_strAccountCode =
                    l_accountManager.getMainAccount(
                        l_mfOderUnitRow.getAccountId()).getAccountCode();
                l_hostOrderParams.setAccountCode(l_strAccountCode);
                
                // 扱者コード = 投信注文単位.扱者コード（SONAR）
                l_hostOrderParams.setTraderCode(l_mfOderUnitRow.getSonarTraderCode());
                
                // 銘柄コード = 投信注文単位.銘柄IDに該当する銘柄コード                
                WEB3MutualFundProduct l_mfProduct =
                    (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                        l_mfOderUnitRow.getProductId());
                l_hostOrderParams.setProductCode(l_mfProduct.getProductCode());    
                
                // 指定 = 投信注文単位.注文数量タイプが数量ならば”1：口数”、金額ならば”2：金額”
                QuantityTypeEnum l_quantityTypeEnum = l_mfOderUnitRow.getQuantityType();
                
                if (QuantityTypeEnum.QUANTITY.equals(l_quantityTypeEnum))
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.COUNT);
                }
                if (QuantityTypeEnum.AMOUNT.equals(l_quantityTypeEnum))
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.MONEY);
                }
                
                // 決済 = 投信注文単位.決済区分
                l_hostOrderParams.setSettlementDiv(l_mfOderUnitRow.getSettlementDiv());
                
                // 売数量 = 0
                l_hostOrderParams.setSellOrderQuantity(0);
                
                // 買数量 = 
                //・投信銘柄.入力単位が”1：1”であれば投信注文単位.注文数量
                //・投信銘柄.入力単位が”2：1万”であれば投信注文単位.注文数量 / 10000
                //注）投信銘柄は、投信注文単位.銘柄IDに該当する投信銘柄
                String l_strInputUnit = l_mfProduct.getInputUnit();
                BigDecimal l_bdBuyOrderQuantity = new BigDecimal("0");
                BigDecimal l_bdCalcNumber2 = new BigDecimal ("10000");
                BigDecimal l_bdQuantity = new BigDecimal(Double.toString(l_mfOderUnitRow.getQuantity()));
                
                if(WEB3InputUnitDef.ONE.equals(l_strInputUnit))
                {
                    l_hostOrderParams.setBuyOrderQuantity(l_bdQuantity.longValue());
                }
                if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_strInputUnit))
                {
                    l_bdBuyOrderQuantity =
                        l_bdQuantity.divide(l_bdCalcNumber2,10,BigDecimal.ROUND_HALF_UP);
                    l_hostOrderParams.setBuyOrderQuantity(l_bdBuyOrderQuantity.longValue());
                }

                // 伝票No. = 9 + 識別lコードの下3桁
                l_hostOrderParams.setTicketNumber(
                    Integer.parseInt("9" + l_mfOderUnitRow.getOrderRequestNumber().substring(6)));

                // 仕法@区分
                //・投信銘柄.is外国投信()==false or
                //（投信銘柄.isFWF()==true and 投信銘柄.通貨コード == ”円”） の場合、
                // ”1：本券”をセット。
                //・投信銘柄.is外国投信()==true or
                //（投信銘柄.isFWF()==true and 投信銘柄.通貨コード != ”円”） の場合、
                // "空白"をセット。
                boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();
                boolean l_blnIsFWF = l_mfProduct.isFWF();
                String l_strCruuencyCode = l_mfProduct.getCurrencyCode();

                if (!l_blnIsForeignFund || 
                    (l_blnIsFWF && WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    l_hostOrderParams.setPrDiv(WEB3PrDivDef.ISSUE_TICKET);
                }
                if (l_blnIsForeignFund || 
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    l_hostOrderParams.setPrDiv(" ");
                }
                
                // 無手数料区分 = 投信注文単位.無手数料区分
                l_hostOrderParams.setCommissionDiv(l_mfOderUnitRow.getNoContractCommissionDiv());

                // 預りチェック = 空白
                l_hostOrderParams.setDepositCheckDiv(" ");
                
                // 識別コード = 投信注文単位.識別番号
                l_hostOrderParams.setOrderRequestNumber(l_mfOderUnitRow.getOrderRequestNumber());
                
                // 受注日/受注時間 = 投信注文単位.受注日時
                Date l_orderDate =
                    l_mfOderUnitRow.getReceivedDateTime();
                l_hostOrderParams.setCreateDate(l_orderDate);
                l_hostOrderParams.setCreateTime(l_orderDate);
                
                // 注文日 = 投信注文単位.発注日
                Date l_bizDate = WEB3DateUtility.getDate(l_mfOderUnitRow.getBizDate(), "yyyyMMdd");
                l_hostOrderParams.setOrderDate(l_bizDate);
                
                // 乗換区分 = NULL
                l_hostOrderParams.setSwtDiv(null);
                
                // 買付銘柄コード
                // is再投資銘柄＝trueの場合、部店用プリファ@レンス．諸口（再投資）
                // is再投資銘柄＝falseの場合、部店用プリファ@レンス．諸口（分配）
                // レコードに存在しない場合、"00"
                l_hostOrderParams.setSwtProductCode("00");
                if (l_mfProduct.isPlowbackProduct())
                {
                    // 部店用プリファ@レンスから「諸口(再投資)」を取得する。
                    BranchPreferencesRow l_preferencesRow = null;
                    try
                    {
                        l_preferencesRow = 
                            BranchPreferencesDao.findRowByPk(
                                l_mutualFundOrderUnit.getBranchId(), 
                                WEB3BranchPreferencesNameDef.PLOWBACK_SYOKUCHI,
                                1);
                        l_hostOrderParams.setSwtProductCode(l_preferencesRow.getValue());
                    }
                    catch(DataFindException l_ex)
                    {
                        l_preferencesRow = null;
                    }
                }
                else
                {
                    // 部店用プリファ@レンスから「諸口(分配)」を取得する。
                    BranchPreferencesRow l_preferencesRow = null;
                    try
                    {
                        l_preferencesRow = 
                            BranchPreferencesDao.findRowByPk(
                                l_mutualFundOrderUnit.getBranchId(), 
                                WEB3BranchPreferencesNameDef.NOPLOWBACK_SYOKUCHI,
                                1);
                        l_hostOrderParams.setSwtProductCode(l_preferencesRow.getValue());
                    }
                    catch(DataFindException l_ex)
                    {
                        l_preferencesRow = null;
                    }
                }
                
                // 特定口座区分 = 投信注文単位.税区分が一般の場合”0：一般”、特定の場合”1：特定”
                TaxTypeEnum l_taxType = l_mfOderUnitRow.getTaxType();
                if (TaxTypeEnum.NORMAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.NORMAL);
                }
                if (TaxTypeEnum.SPECIAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.SPECIAL);
                }
                
                // 特定口座区分（乗換先）= NULL                
                l_hostOrderParams.setSwtTaxType(null);               
                
                // 請求区分 = 空白
                l_hostOrderParams.setClaimDiv(" ");
                
                // 注文チャネル = 投信注文単位.初回注文の注文チャネル
                l_hostOrderParams.setOrderChanel(l_mfOderUnitRow.getOrderChanel());
                
                // 処理区分 = 0：未処理
                l_hostOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);
                
                //入金日 = 募集の場合は発注日の翌営業日
                //（それ以外の場合はNULL）
                if (OrderTypeEnum.MF_RECRUIT.equals(l_mfOderUnitRow.getOrderType()))
                {
                    Date l_datPaymentDate = 
                        new WEB3GentradeBizDate(new Timestamp(l_bizDate.getTime())).roll(1);
                    
                    l_hostOrderParams.setPaymentDate(l_datPaymentDate);
                }
                else
                {
                    l_hostOrderParams.setPaymentDate(null);
                }
            }
            
            l_hostOrderParams.markAllValuesAsSet();
            Processors.getDefaultProcessor().doInsertQuery(l_hostOrderParams);
            log.debug("キューデータインサート...... with HostXbmfOrderParams = " + l_hostOrderParams);

            //1.2.<分岐処理> (*2)
            if (OrderTypeEnum.MF_RECRUIT.equals(l_mutualFundOrderUnit.getOrderType()))
            {
                //1.2.1部店用プリファ@レンスから「投信募集注文一括送信区分」を取得する。
                BranchPreferencesRow l_preferencesRow = null;
                try
                {
                    l_preferencesRow = 
                        BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                            l_mutualFundOrderUnit.getBranchId(), 
                            WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV,
                            1);
                }
                catch(DataFindException l_ex)
                {
                    l_preferencesRow = null;
                }
                
                //<分岐処理>部店用プリファ@レンス．プリファ@レンス値 = 「一括送信する」の場合
                if (l_preferencesRow == null || 
                    WEB3MfRecruitMqSendDivDef.DEFAULT.equals(l_preferencesRow.getValue()) )
                {
                    return;
                }
            }

            // 3）　@トリガ発行
            // －引数.投信注文単位.get受注日時()の戻り値をYYYYMMDD形式に変換したものと
            // 引数.投信注文単位.getBizDate()の戻り値を比較し、
            // 値が異なる場合は以降の処理を行わない。
            String l_strOrderDate =
                GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
                    l_mfOderUnitRow.getReceivedDateTime());
            String l_strBizDate = l_mfOderUnitRow.getBizDate();
            log.debug(" 投信注文単位.注文日時(YYYYMMDD)、投信注文単位.発注日（YYYYMMDD）＝ " + l_strOrderDate + "、" + l_strBizDate);
            if (!l_strOrderDate.equals(l_strBizDate))
            {
                return;
            }

            // －トリガを発行するかのチェック
			log.debug("トリガ発行時間帯かのチェック");
            if (WEB3MutualFundTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT))
            {
                // －WEB3MQMessageSpecの生成
                WEB3MQMessageSpec l_mqMessageSpec =
                    new WEB3MQMessageSpec(
                        l_subAccount.getInstitution().getInstitutionCode(),
                        l_hostOrderParams.getRequestCode() + "T");
                log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
                log.debug("DataCode =" + l_hostOrderParams.getRequestCode() + "T");
                // －トリガを発行する。
                WEB3MQGatewayService l_mqGatewayService =
                    (WEB3MQGatewayService) Services.getService(
                        WEB3MQGatewayService.class);
                log.debug("トリガを発行する........");
                l_mqGatewayService.send(l_mqMessageSpec);
                log.debug("トリガを発行する........OK!");
            }

        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In 部店、顧客コードまたは銘柄コードを取得する ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In 投信注文取消キューデータインサート ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In 投信注文取消キューデータインサート ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        finally
        {
            log.exiting(l_strMethodName);
        }
    }

    /**
     * 新規注文市場リクエストメッセージ送信（売買注文通知）<BR>
     * <BR>
     * 注文単位の注文状態を発注済（新規注文）に変更する。<BR>
     * <BR>
     * １）　@投信市場応答コールバックサービスオブジェクトの取得<BR>
     * 　@MutualFundMarketResponseReceiverCallbackService( )<BR>
     * 　@をコールし、投信市場応答コールバックサービスオブジェクトを取得する。<BR>
     * <BR>
     * ２）　@DefaultNewOrderAcceptedMarketResponseMessage<BR>
     * 　@オブジェクトを生成する。<BR>
     * 　@［コンストラクタに渡すパラメタ］<BR>
     * 　@　@注文ID： 引数.投信注文単位.getOrderId()の戻り値<BR>
     * <BR>
     * ３）　@投信受付確定インタセプタオブジェクトを生成する。<BR>
     * <BR>
     * ４）　@投信受付確定インタセプタ.set注文エラー理由コード()をコールし、注文エラーコ
     * ードの<BR>
     * 　@設定を行う。<BR>
     * 　@[set注文エラー理由コードに渡すパラメタ]<BR>
     * 　@　@注文エラー理由コード： null<BR>
     * <BR>
     * ５）　@拡張投信注文マネージャ.setThreadLocalPersistenceEventInterceptor()をコール
     * し、<BR>
     * 　@インタセプタの設定を行う。<BR>
     * 　@[setThreadLocalPersistenceEventInterceptorに渡すパラメタ]<BR>
     * 　@　@投信受付確定インタセプタ： 生成した投信受付確定インタセプタ<BR>
     * <BR>
     * ６）　@MutualFundMarketResponseReceiverCallbackService.process()をコールする。<BR>
     * 　@［processに渡すパラメタ］<BR>
     * 　@　@新規注文受付済市場応答メッセージ： <BR>
     * 　@　@　@生成したDefaultNewOrderAcceptedMarketResponseMessageオブジェクト<BR>
     * @@param l_mutualFundOrderUnit - 投信注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B5BFAE03C8
     */
    public void newOrderMarketRequestSendMessageTradeOrderNotify(
        MutualFundOrderUnit l_mutualFundOrderUnit)
        throws WEB3BaseException
    {
        final String l_strMethodName = "newOrderMarketRequestSendMessageTradeOrderNotify("
            + "MutualFundOrderUnit l_mutualFundOrderUnit)";
        log.entering(l_strMethodName);
        // １）　@投信市場応答コールバックサービスオブジェクトの取得
//　@        MutualFundMarketResponseReceiverCallbackService( )
//　@        をコールし、投信市場応答コールバックサービスオブジェクトを取得する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        MarketAdapter l_marketAdaptor = l_tm.getMarketAdapter();
        MutualFundMarketResponseReceiverCallbackService l_service =
            (MutualFundMarketResponseReceiverCallbackService)l_marketAdaptor.getMarketResponseReceiverCallbackService();

//
        // ２）　@DefaultNewOrderAcceptedMarketResponseMessage
        // オブジェクトを生成する。
        // ［コンストラクタに渡すパラメタ］
//　@　@        注文ID： 引数.投信注文単位.getOrderId()の戻り値
        DefaultNewOrderAcceptedMarketResponseMessage
            l_defaultCancelOrderRejectedMarketResponseMessage =
                new DefaultNewOrderAcceptedMarketResponseMessage(
                    l_mutualFundOrderUnit.getOrderId());
//
        // ３）　@投信受付確定インタセプタオブジェクトを生成する。
        WEB3MutualFundAcceptConfirmInterceptor l_mfAcceptconfirmInterceptor =
            new WEB3MutualFundAcceptConfirmInterceptor();

        // ４）　@投信受付確定インタセプタ.set注文エラー理由コード()をコールし、注文エラーコードの
        // 設定を行う。
        // [set注文エラー理由コードに渡すパラメタ]
        //  注文エラー理由コード： null
        l_mfAcceptconfirmInterceptor.setOrderErrorReasonCode(null);
        // ５）　@拡張投信注文マネージャ.setThreadLocalPersistenceEventInterceptor()をコールし、
        // インタセプタの設定を行う。
        // [setThreadLocalPersistenceEventInterceptorに渡すパラメタ]
        //   投信受付確定インタセプタ： 生成した投信受付確定インタセプタ
        WEB3MutualFundOrderManager l_mfOrderManager =
            (WEB3MutualFundOrderManager)l_tm.getOrderManager();
        l_mfOrderManager.setThreadLocalPersistenceEventInterceptor(l_mfAcceptconfirmInterceptor);

        // ６）　@MutualFundMarketResponseReceiverCallbackService.process()をコールする。
        // ［processに渡すパラメタ］
        //   新規注文受付済市場応答メッセージ：
        // 生成したDefaultNewOrderAcceptedMarketResponseMessageオブジェクト
        l_service.process(l_defaultCancelOrderRejectedMarketResponseMessage);

        log.exiting(l_strMethodName);
    }

    /**
     * 取消注文市場リクエストメッセージ送信（市場送信あり）<BR>
     * <BR>
     * 注文取消の市場リクエストメッセージを送信する。<BR>
     * <BR>
     * １）　@キューデータインサート<BR>
     * 　@引数.投信注文単位オブジェクトの内容をもとに、投信注文取消キューテーブル<BR>
     * 　@に注文データを挿入する。<BR>
     * 　@（DB更新仕様「投資信託取消_投信注文取消キューテーブル.xls」参照）<BR>
     * <BR>
     * ２）　@トリガ発行<BR>
     * 　@－トリガを発行するかのチェック<BR>
     * 　@　@投信取引時間管理.isトリガ発行()をコールし、戻り値がtrueであればトリガを<BR>
     * 　@　@発行する。戻り値がfalseであれば以降の処理を行わない。<BR>
     * 　@　@　@［isトリガ発行に渡すパラメタ］<BR>
     * 　@　@　@　@発注条件：<BR>
     * 　@　@　@　@　@”0 ： DEFAULT”<BR>
     * <BR>
     * 　@－WEB3MQMessageSpecの生成<BR>
     * 　@　@WEB3MQMessageSpecを生成する。<BR>
     * 　@　@[コンストラクタに渡すパラメタ]<BR>
     * 　@　@　@証券会社コード：
     * 引数.補助口座.getInstitution().getInstitutionCode()の戻り値<BR>
     * 　@　@　@データコード： 投信注文取消キューテーブルのデータコードの値<BR>
     * <BR>
     * 　@－トリガを発行する。<BR>
     * 　@　@WEB3MQGatewayServiceImpl.send()をコールし、トリガを発行する。<BR>
     * 　@　@［sendに渡すパラメタ］<BR>
     * 　@　@　@MQに送信するメッセージのスペック：
     * 生成したWEB3MQMessageSpecオブジェクト<BR>
     * @@param l_mutualFundOrderUnit - 投信注文単位<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B5BFB80109
     */
    public void cancelOrderMarketRequestSendMessageSubmit(
        MutualFundOrderUnit l_mutualFundOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String l_strMethodName = "cancelOrderMarketRequestSendMessageSubmit("
            + "MutualFundOrderUnit l_mutualFundOrderUnit, "
            + "SubAccount l_subAccount)";
        log.entering(l_strMethodName);
        if (l_subAccount == null || l_mutualFundOrderUnit == null)
        {
            log.debug("パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        try
        {
            // １）　@キューデータインサート
            HostXbmfOrderCancelParams l_hostOrderCancelParams =
                new HostXbmfOrderCancelParams();

            MutualFundOrderUnitRow l_mfOderUnitRow =
                (MutualFundOrderUnitRow) l_mutualFundOrderUnit.getDataSourceObject();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
            WEB3MutualFundProductManager l_mfProductManager =
                (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

            WEB3MutualFundProduct l_mfProduct =
                (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                    l_mfOderUnitRow.getProductId());
                    
            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
            
            // データコード
            //・投信銘柄.is外国投信()==false or
            //（投信銘柄.isFWF()==true and 投信銘柄.通貨コード == ”円”） の場合、
            //"CI802"をセット
            boolean l_blnIsForergnFund = l_mfProduct.isForeignFund();
            boolean l_blnIsFWF = l_mfProduct.isFWF();
            String l_strCruuencyCode = l_mfProduct.getCurrencyCode();
            String l_strRequestCode = null;
            
            if (!l_blnIsForergnFund || 
                (l_blnIsFWF && WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
            {
                l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_DOMESTIC_ORDER_CANCEL;
                l_hostOrderCancelParams.setRequestCode(l_strRequestCode);
            }
            //・投信銘柄.is外国投信()==true or
            //（投信銘柄.isFWF()==true and 投信銘柄.通貨コード != ”円”） の場合、
            //"CI804"をセット
            if (l_blnIsForergnFund || 
                (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
            {                
                l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_FOREIGN_ORDER_CANCEL;
                l_hostOrderCancelParams.setRequestCode(l_strRequestCode); 
            }
            //・投信注文単位.注文種別 == ”投信乗換注文” の場合、"CI806"をセット                       
			OrderTypeEnum l_orderType = l_mfOderUnitRow.getOrderType();
			if (OrderTypeEnum.MF_SWITCHING.equals(l_orderType))
			{
                l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_SWITCHING_ORDER_CANCEL;
				l_hostOrderCancelParams.setRequestCode(l_strRequestCode);
			}
            
            // 部店を取得する
            Branch l_banch =
                l_accountManager.getBranch(l_mfOderUnitRow.getBranchId());
            // 部店コード
            l_hostOrderCancelParams.setBranchCode(l_banch.getBranchCode());
            // 証券会社コード
            String l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            l_hostOrderCancelParams.setInstitutionCode(l_strInstitutionCode);
            // 顧客コード
            String l_strAccountCode =
                l_accountManager.getMainAccount(
                    l_mfOderUnitRow.getAccountId()).getAccountCode();
            l_hostOrderCancelParams.setAccountCode(l_strAccountCode);
            // 扱者コード
            if (!l_mfOderUnitRow.getTraderIdIsNull())
            {
                String l_strTraderCode =
                    l_finObjMgr.getTrader(
                        l_mfOderUnitRow.getTraderId()).getTraderCode();
                l_hostOrderCancelParams.setTraderCode(l_strTraderCode);
            }
            else
            {
                l_hostOrderCancelParams.setTraderCode(null);
            }
            // 銘柄コード
            l_hostOrderCancelParams.setProductCode(l_mfProduct.getProductCode());
            // 取消区分
            l_hostOrderCancelParams.setCancelDiv("-");
            // 識別コード
            l_hostOrderCancelParams.setOrderRequestNumber(
                l_mfOderUnitRow.getOrderRequestNumber());
            // 受注日/受注時間
            Date l_strOrderDate =
                l_mfOderUnitRow.getReceivedDateTime();
            l_hostOrderCancelParams.setOrderDate(l_strOrderDate);
            l_hostOrderCancelParams.setOrderTime(l_strOrderDate);
            // 処理区分
            String l_strStatus = WEB3StatusDef.NOT_DEAL;
            l_hostOrderCancelParams.setStatus(l_strStatus);

            Processors.getDefaultProcessor().doInsertQuery(
                l_hostOrderCancelParams);

            // ２）　@トリガ発行
            // －トリガを発行するかのチェック
            if (WEB3MutualFundTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT))
            {
                // －WEB3MQMessageSpecの生成
                WEB3MQMessageSpec l_web3MQMessageSpec =
                    new WEB3MQMessageSpec(
                        l_subAccount.getInstitution().getInstitutionCode(),
                        l_strRequestCode + "T");
                log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
                log.debug("DataCode =" + l_strRequestCode + "T");
                
                // －トリガを発行する。
                WEB3MQGatewayService l_web3MQGatewayService =
                    (WEB3MQGatewayService) Services.getService(
                        WEB3MQGatewayService.class);

                l_web3MQGatewayService.send(l_web3MQMessageSpec);
            }

        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In 部店、顧客コードまたは銘柄コードを取得する ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In 投信注文取消キューデータインサート ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In 投信注文取消キューデータインサート ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        finally
        {
            log.exiting(l_strMethodName);
        }
    }

    /**
     * 取消注文市場リクエストメッセージ送信（市場送信なし）<BR>
     * <BR>
     * １）　@キューデータ削除<BR>
     * 　@引数.投信注文単位オブジェクトの識別コードに対応するキューデータが存在する場合・
     * 、<BR>
     * 　@投信注文キューテーブルのデータを削除する。<BR>
     * 　@［削除条件］<BR>
     * 　@　@証券会社コード =
     * 引数.補助口座.getInstitution().getInstitutionCode()の戻り値 AND<BR>
     * 　@　@部店コード =
     * <BR>引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値 AND<BR>
     * 　@　@顧客コード = 引数.補助口座.getMainAccount().getAccountCode()の戻り値 AND<BR>
     * 　@　@識別コード = 引数.投信注文単位.getDataSourceObject().get識別コード()<BR>
     * @@param l_mutualFundOrderUnit - 投信注文単位<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B5BFC00157
     */
    public void cancelOrderMarketRequestSendMessageNotSubmit(
        MutualFundOrderUnit l_mutualFundOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String l_strMethodName = "cancelOrderMarketRequestSendMessageNotSubmit("
            + "MutualFundOrderUnit l_mutualFundOrderUnit, "
            + "SubAccount l_subAccount)";
        log.entering(l_strMethodName);
        if (l_subAccount == null || l_mutualFundOrderUnit == null)
        {
            log.debug("パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // １）　@キューデータ削除
        // 引数.投信注文単位オブジェクトの識別コードに対応するキューデータが存在する場合、
        // 投信注文キューテーブルのデータを削除する。
        try
        {
            QueryProcessor processor = Processors.getDefaultProcessor();
            String l_strWhere =
                " institution_code=? and branch_code=? and account_code=? and order_request_number=? ";
            MutualFundOrderUnitRow l_mfOrderUnitRow =
                (MutualFundOrderUnitRow) l_mutualFundOrderUnit.getDataSourceObject();
            //［削除条件］
            // 証券会社コード = 引数.補助口座.getInstitution().getInstitutionCode()の戻り値
            // 部店コード = 引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値 AND
            // 顧客コード = 引数.補助口座.getMainAccount().getAccountCode()の戻り値 AND
            // 識別コード = 引数.投信注文単位.getDataSourceObject().get識別コード()
            Object[] l_objWhereValues = {
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_mfOrderUnitRow.getOrderRequestNumber()
            };
            
            //①@投信注文単位．投信タイプ = 外貨MMFの場合
            //　@　@外貨MMF注文キューテーブルに該当する行がある場合は削除する
            if (MutualFundTypeEnum.FOREIGN_MMF.equals(l_mfOrderUnitRow.getFundType()))
            {
                processor.doDeleteAllQuery(
                    HostFrgnMmfOrderRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
            }
            //②投信注文単位．投信タイプ  != 外貨MMFの場合
            //投信注文キューテーブルに該当する行がある場合は削除する
            else
            {
                //対象データ削除
                log.debug("キューデータ削除HostXbmfOrderRow.............. with where = " + l_strWhere);
                log.debug("キューデータ削除HostXbmfOrderRow.............. with where Value0 = " + l_objWhereValues[0]);
                log.debug("キューデータ削除HostXbmfOrderRow.............. with where Value1 = " + l_objWhereValues[1]);
                log.debug("キューデータ削除HostXbmfOrderRow.............. with where Value2 = " + l_objWhereValues[2]);
                log.debug("キューデータ削除HostXbmfOrderRow.............. with where Value3 = " + l_objWhereValues[3]);
                processor.doDeleteAllQuery(
                    HostXbmfOrderRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
            }
            log.debug("キューデータ削除.............. OK!");
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In 投信注文キューテーブルのデータを削除する ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In 投信注文キューテーブルのデータを削除する ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }

        log.exiting(l_strMethodName);
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSenderService#send(com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestMessage)
     */
    public MarketRequestSendResult send(MarketRequestMessage arg0)
    {
     return null;
    }
}
@
