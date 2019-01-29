head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCancelOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文取消ハンドラ(WEB3EquityCancelOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 李雲峰 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 2007/12/17 張騰宇 モデル1204
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.message.WEB3EquityCancelCompleteRequest;
import webbroker3.equity.message.WEB3EquityCancelCompleteResponse;
import webbroker3.equity.message.WEB3EquityCancelConfirmRequest;
import webbroker3.equity.message.WEB3EquityCancelConfirmResponse;
import webbroker3.equity.service.delegate.WEB3EquityCancelOrderService;
import webbroker3.equity.service.delegate.WEB3EquityPTSCancelOrderService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文取消ハンドラ）。<BR>
 * <BR>
 * 株式注文取消ハンドラクラス
 * @@version 1.0
 */
public class WEB3EquityCancelOrderHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityCancelOrderHandler.class);

    /**
     * @@roseuid 40AC7C5103A5
     */
    public WEB3EquityCancelOrderHandler()
    {

    }

    /**
     * (株式注文取消確認リクエスト)。<BR>
     * クライアントよりリクエストを受け、現物株式注文取消確認処理を実行する。<BR>
     * （システム実装方針ガイド 4.5.ハンドラ　@参照） <BR>
     * <BR>
     * １）　@IDチェックを行う。 <BR>
     * <BR>
     * 　@リクエストデータ.ID ＝ nullの場合、 <BR>
     * 　@「IDがnull」の例外をスローする。 <BR>
     * 　@以外、以下の処理を実行する。 <BR>
     * <BR>
     * ２）　@取消対象注文単位を取得する <BR>
     * <BR>
     * 　@拡張株式注文マネージャ.getOrderUnits(リクエストデータ.ID)<BR>
     * 　@で取得した注文単位オブジェクトのうち、 <BR>
     * 　@最初の要素を取得する。 <BR>
     * <BR>
     * ３）　@市場オブジェクトを取得する。 <BR>
     * <BR>
     * 　@拡張金融オブジェクトマネージャ.getMarket()をコールする。 <BR>
     * 　@[.getMarket()にセットする引数] <BR>
     * 　@　@市場ID:１）で取得した注文単位.市場ID <BR>
     * <BR>
     * ４）　@市場.isPTS市場（）より、PTS市場か判定し、各処理を呼び分ける。 <BR>
     * <BR>
     * 　@４－１）　@PTS市場でない場合（市場.isPTS市場()==false） <BR>
     * 　@　@株式注文取消サービスオブジェクトを取得し、 <BR>
     * 　@　@execute（）をコールする。 <BR>
     * <BR>
     * 　@４－２）　@PTS市場の場合（市場.isPTS市場()==true） <BR>
     * 　@　@(PTS)株式注文取消サービスオブジェクトを取得し、 <BR>
     * 　@　@execute（）をコールする。 <BR>
     * <BR>
     * ５）　@サービスで例外が発生した場合は、エラー情報をレスポンスメッセージ<BR>
     * 　@に設定する 。<BR>
     * <BR>
     * ６）　@レスポンスオブジェクトを返却する。<BR>
     * @@param  l_request <BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 403097930172
     */
    public WEB3EquityCancelConfirmResponse equityCancelConfirmRequest(WEB3EquityCancelConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "equityCancelConfirmRequest(WEB3EquityCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityCancelConfirmResponse l_response = null;

        //１）　@IDチェックを行う。
        //リクエストデータ.ID ＝ nullの場合、「IDがnull」の例外をスローする。
        if (l_request.id == null)
        {
            l_response =
                (WEB3EquityCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00600;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                    STR_METHOD_NAME);
            log.error("注文IDが未指定です。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //２）　@取消対象注文単位を取得する
        //　@拡張株式注文マネージャ.getOrderUnits(リクエストデータ.ID)
        //で取得した注文単位オブジェクトのうち、
        //　@最初の要素を取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            l_response =
                (WEB3EquityCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME);
            log.error("テーブルに該当するデータがありません。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        OrderUnit l_orderUnit = l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //３）　@市場オブジェクトを取得する。
        //拡張金融オブジェクトマネージャ.getMarket()をコールする。
        //[.getMarket()にセットする引数]市場ID:１）で取得した注文単位.市場ID
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_objectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            l_response =
                (WEB3EquityCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //４）　@市場.isPTS市場（）より、PTS市場か判定し、各処理を呼び分ける。
        //４－１）　@PTS市場でない場合（市場.isPTS市場()==false）
        //株式注文取消サービスオブジェクトを取得し、execute（）をコールする。
        boolean l_blnIsPTSMarket = false;
        try
        {
            l_blnIsPTSMarket = l_market.isPTSMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3EquityCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        if (!l_blnIsPTSMarket)
        {
            WEB3EquityCancelOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityCancelOrderService) Services.getService(
                        WEB3EquityCancelOrderService.class);
            }
            catch (Exception e)
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse) l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request, "株式注文取消サービスの取得に失敗しました。", l_response.errorInfo, e);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            
            
            try
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse) l_service.execute(l_request);
            }
            catch (WEB3BaseException e)
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse) l_request.createResponse();
                l_response.errorInfo = e.getErrorInfo();
                log.error(l_request, "株式注文取消確認に失敗しました。", e);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_bre)
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse) l_request.createResponse();
                l_response.errorInfo = l_bre.getErrorInfo();
                log.error(l_request, "株式注文取消確認に失敗しました。", l_bre);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }
        //４－２）　@PTS市場の場合（市場.isPTS市場()==true）
        //(PTS)株式注文取消サービスオブジェクトを取得し、execute（）をコールする。
        else
        {
            WEB3EquityPTSCancelOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityPTSCancelOrderService)Services.getService(
                        WEB3EquityPTSCancelOrderService.class);
            }
            // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            catch (Exception l_ex)
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "(PTS)株式注文取消サービスの取得に失敗しました。",
                    l_response.errorInfo,
                    l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)株式注文訂正確認に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)株式注文訂正確認に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (株式注文取消完了リクエスト)。<BR>
     * クライアントよりリクエストを受け、現物株式注文取消完了処理を実行する。 <BR>
     * （システム実装方針ガイド 4.5.ハンドラ　@参照） <BR>
     * <BR>
     * １）　@IDチェックを行う。 <BR>
     * <BR>
     * 　@リクエストデータ.ID ＝ nullの場合、 <BR>
     * 　@「IDがnull」の例外をスローする。 <BR>
     * 　@以外、以下の処理を実行する。 <BR>
     * <BR>
     * ２）　@取消対象注文単位を取得する <BR>
     * <BR>
     * 　@拡張株式注文マネージャ.getOrderUnits(リクエストデータ.ID)<BR>
     * 　@で取得した注文単位オブジェクトのうち、 <BR>
     * 　@最初の要素を取得する。 <BR>
     * <BR>
     * ３）　@市場オブジェクトを取得する。 <BR>
     * <BR>
     * 　@拡張金融オブジェクトマネージャ.getMarket()をコールする。 <BR>
     * 　@[.getMarket()にセットする引数] <BR>
     * 　@　@市場ID:１）で取得した注文単位.市場ID <BR>
     * <BR>
     * ４）　@市場.isPTS市場（）より、PTS市場か判定し、各処理を呼び分ける。 <BR>
     * <BR>
     * 　@４－１）　@PTS市場でない場合（市場.isPTS市場()==false） <BR>
     * 　@　@株式注文取消サービスオブジェクトを取得し、 <BR>
     * 　@　@execute（）をコールする。 <BR>
     * <BR>
     * 　@４－２）　@PTS市場の場合（市場.isPTS市場()==true） <BR>
     * 　@　@(PTS)株式注文取消サービスオブジェクトを取得し、 <BR>
     * 　@　@execute（）をコールする。 <BR>
     * <BR>
     * ５）　@サービスで例外が発生した場合は、<BR>
     * エラー情報をレスポンスメッセージに設定する。  <BR>
     * <BR>
     * ６）　@レスポンスオブジェクトを返却する。<BR>
     * @@param l_request クライアントからのリクエストメッセージを指定する。
     * @@return WEB3EquityCancelCompleteResponse
     * @@roseuid 40A02F06004E
     */
    public WEB3EquityCancelCompleteResponse equityCancelCompleteRequest(WEB3EquityCancelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "equityCancelComplete(WEB3EquityCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityCancelCompleteResponse l_response = null;

        //１）　@IDチェックを行う。
        //リクエストデータ.ID ＝ nullの場合、「IDがnull」の例外をスローする。
        if (l_request.id == null)
        {
            l_response =
                (WEB3EquityCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00600;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                    STR_METHOD_NAME);
            log.error("注文IDが未指定です。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //２）　@取消対象注文単位を取得する
        //　@拡張株式注文マネージャ.getOrderUnits(リクエストデータ.ID)
        //で取得した注文単位オブジェクトのうち、
        //　@最初の要素を取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            l_response =
                (WEB3EquityCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME);
            log.error("テーブルに該当するデータがありません。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        OrderUnit l_orderUnit = l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //３）　@市場オブジェクトを取得する。
        //拡張金融オブジェクトマネージャ.getMarket()をコールする。
        //[.getMarket()にセットする引数]市場ID:１）で取得した注文単位.市場ID
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_objectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            l_response =
                (WEB3EquityCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //４）　@市場.isPTS市場（）より、PTS市場か判定し、各処理を呼び分ける。
        //４－１）　@PTS市場でない場合（市場.isPTS市場()==false）
        //株式注文取消サービスオブジェクトを取得し、execute（）をコールする。
        boolean l_blnIsPTSMarket = false;
        try
        {
            l_blnIsPTSMarket = l_market.isPTSMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3EquityCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        if (!l_blnIsPTSMarket)
        {
            WEB3EquityCancelOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityCancelOrderService) Services.getService(
                        WEB3EquityCancelOrderService.class);
            }
            catch (Exception e)
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse) l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request, "株式注文取消サービスの取得に失敗しました。", l_response.errorInfo, e);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            
            
            try
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse) l_service.execute(l_request);
            }
            catch (WEB3BaseException e)
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse) l_request.createResponse();
                l_response.errorInfo = e.getErrorInfo();
                log.error(l_request, "株式注文取消完了に失敗しました。", e);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_bre)
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse) l_request.createResponse();
                l_response.errorInfo = l_bre.getErrorInfo();
                log.error(l_request, "株式注文取消完了に失敗しました。", l_bre);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }
        //４－２）　@PTS市場の場合（市場.isPTS市場()==true）
        //(PTS)株式注文取消サービスオブジェクトを取得し、execute（）をコールする。
        else
        {
            WEB3EquityPTSCancelOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityPTSCancelOrderService)Services.getService(
                        WEB3EquityPTSCancelOrderService.class);
            }
            // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            catch (Exception l_ex)
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "(PTS)株式注文取消サービスの取得に失敗しました。",
                    l_response.errorInfo,
                    l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)株式注文取消完了に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)株式注文取消完了に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
