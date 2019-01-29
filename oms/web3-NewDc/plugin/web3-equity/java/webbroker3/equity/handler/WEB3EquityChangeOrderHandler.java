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
filename	WEB3EquityChangeOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文訂正ハンドラ(WEB3EquityChangeOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 周玲玲 (中訊) 新規作成
                   2004/12/15 中尾寿彦(SRA) 残案件対応による修正
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
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeCompleteResponse;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmResponse;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderService;
import webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文訂正ハンドラ）。<BR>
 * <BR>
 * 株式注文訂正ハンドラクラス
 * @@version 1.0
 */
public class WEB3EquityChangeOrderHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeOrderHandler.class);

    /**
     * @@roseuid 409EFFA9005A
     */
    public WEB3EquityChangeOrderHandler()
    {

    }

    /**
     * (株式注文訂正リクエスト)<BR>
     * クライアントよりリクエストを受け、現物株式注文訂正確認処理を実行する。 <BR>
     * （システム実装方針ガイド 4.5.ハンドラ　@参照）  <BR>
     * <BR>
     * １）　@IDチェックを行う。 <BR>
     * <BR>
     * 　@リクエストデータ.ID ＝ nullの場合、 <BR>
     * 　@「IDがnull」の例外をスローする。 <BR>
     * 　@以外、以下の処理を実行する。 <BR>
     * <BR>
     * ２）　@訂正対象注文単位を取得する。 <BR>
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
     * 　@　@株式注文訂正サービスオブジェクトを取得し、 <BR>
     * 　@　@execute（）をコールする。 <BR>
     * <BR>
     * 　@４－２）　@PTS市場の場合（市場.isPTS市場()==true） <BR>
     * 　@　@(PTS)株式注文訂正サービスオブジェクトを取得し、 <BR>
     * 　@　@execute（）をコールする。 <BR>
     * <BR>
     * ５）　@サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する。  <BR>
     * <BR>
     * ６）　@レスポンスオブジェクトを返却する。<BR>
     * @@roseuid 4021ACDE003A
     */
    public WEB3EquityChangeConfirmResponse equityChangeOrderConfirmRequest(WEB3EquityChangeConfirmRequest l_request)
    {
        // 株式注文訂正サービスオブジェクトを取得する
        final String STR_METHOD_NAME =
            "equityChangeOrderConfirmRequest(WEB3EquityChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityChangeConfirmResponse l_response = null;

        //１）　@IDチェックを行う。
        //リクエストデータ.ID ＝ nullの場合、「IDがnull」の例外をスローする。
        if (l_request.id == null)
        {
            l_response =
                (WEB3EquityChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00600;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                    STR_METHOD_NAME);
            log.error("注文IDが未指定です。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //２）　@訂正対象注文単位を取得する。
        //拡張株式注文マネージャ.getOrderUnits(リクエストデータ.ID)
        //で取得した注文単位オブジェクトのうち、
        //最初の要素を取得する。
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
                (WEB3EquityChangeConfirmResponse)l_request.createResponse();
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
                (WEB3EquityChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //４）　@市場.isPTS市場（）より、PTS市場か判定し、各処理を呼び分ける。
        //４－１）　@PTS市場でない場合（市場.isPTS市場()==false）
        //株式注文訂正サービスオブジェクトを取得し、execute（）をコールする。
        boolean l_blnIsPTSMarket = false;
        try
        {
            l_blnIsPTSMarket = l_market.isPTSMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3EquityChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        if (!l_blnIsPTSMarket)
        {
            WEB3EquityChangeOrderService l_service = null;
            // 株式注文訂正サービスオブジェクト.execute（）をコールし、
            // 処理結果であるレスポンスオブジェクトを取得する。
            try
            {
                l_service =
                    (WEB3EquityChangeOrderService) Services.getService(
                        WEB3EquityChangeOrderService.class);
            }
            // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            catch (Exception e)
            {
                l_response =
                    (WEB3EquityChangeConfirmResponse) l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "株式注文訂正サービスの取得に失敗しました。",
                    l_response.errorInfo,
                    e);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquityChangeConfirmResponse) l_service.execute(l_request);
            }
            catch (WEB3BaseException e)
            {
                l_response =
                    (WEB3EquityChangeConfirmResponse) l_request.createResponse();
                l_response.errorInfo = e.getErrorInfo();
                l_response.errorMessage = e.getErrorMessage();
                log.error(l_request, "株式注文訂正確認に失敗しました。", e);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_bre)
            {
                l_response =
                    (WEB3EquityChangeConfirmResponse) l_request.createResponse();
                l_response.errorInfo = l_bre.getErrorInfo();
                log.error(l_request, "株式注文訂正確認に失敗しました。", l_bre);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }

        //４－２）　@PTS市場の場合（市場.isPTS市場()==true）
        //(PTS)株式注文訂正サービスオブジェクトを取得し、execute（）をコールする。
        else
        {
            WEB3EquityPTSChangeOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityPTSChangeOrderService)Services.getService(
                        WEB3EquityPTSChangeOrderService.class);
            }
            // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            catch (Exception l_ex)
            {
                l_response =
                    (WEB3EquityChangeConfirmResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "(PTS)株式注文訂正サービスの取得に失敗しました。",
                    l_response.errorInfo,
                    l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquityChangeConfirmResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquityChangeConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
                log.error(l_request, "(PTS)株式注文訂正確認に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                l_response =
                    (WEB3EquityChangeConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)株式注文訂正確認に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (株式注文訂正リクエスト)<BR>
     * クライアントよりリクエストを受け、現物株式注文訂正完了処理を実行する。 <BR>
     * （システム実装方針ガイド 4.5.ハンドラ　@参照）  <BR>
     * <BR>
     * １）　@IDチェックを行う。 <BR>
     * <BR>
     * 　@リクエストデータ.ID ＝ nullの場合、 <BR>
     * 　@「IDがnull」の例外をスローする。 <BR>
     * 　@以外、以下の処理を実行する。 <BR>
     * <BR>
     * ２）　@訂正対象注文単位を取得する。 <BR>
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
     * 　@　@株式注文訂正サービスオブジェクトを取得し、 <BR>
     * 　@　@execute（）をコールする。 <BR>
     * <BR>
     * 　@４－２）　@PTS市場の場合（市場.isPTS市場()==true） <BR>
     * 　@　@(PTS)株式注文訂正サービスオブジェクトを取得し、 <BR>
     * 　@　@execute（）をコールする。 <BR>
     * <BR>
     * ５）　@サービスで例外が発生した場合は、<BR>
     * エラー情報をレスポンスメッセージに設定する。  <BR>
     * <BR>
     * ６）　@レスポンスオブジェクトを返却する。 <BR>
     * @@param l_request
     * @@return WEB3EquityChangeCompleteResponse
     * @@roseuid 403F443A029F
     */
    public WEB3EquityChangeCompleteResponse equityChangeOrderCompleteRequest(WEB3EquityChangeCompleteRequest l_request)
    {
        //株式注文訂正サービスオブジェクトを取得する。
        final String STR_METHOD_NAME =
            "equityChangeOrderCompleteRequest(WEB3EquityChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityChangeCompleteResponse l_response = null;

        //１）　@IDチェックを行う。
        //リクエストデータ.ID ＝ nullの場合、「IDがnull」の例外をスローする。
        if (l_request.id == null)
        {
            l_response =
                (WEB3EquityChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00600;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                    STR_METHOD_NAME);
            log.error("注文IDが未指定です。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //２）　@訂正対象注文単位を取得する。
        //拡張株式注文マネージャ.getOrderUnits(リクエストデータ.ID)
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
                (WEB3EquityChangeCompleteResponse)l_request.createResponse();
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
                (WEB3EquityChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //４）　@市場.isPTS市場（）より、PTS市場か判定し、各処理を呼び分ける。
        //４－１）　@PTS市場でない場合（市場.isPTS市場()==false）
        //株式注文訂正サービスオブジェクトを取得し、execute（）をコールする。
        boolean l_blnIsPTSMarket = false;
        try
        {
            l_blnIsPTSMarket = l_market.isPTSMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3EquityChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        if (!l_blnIsPTSMarket)
        {
            WEB3EquityChangeOrderService l_service = null;
            // 株式注文訂正サービスオブジェクト.execute（）をコールし、
            // 処理結果であるレスポンスオブジェクトを取得する。
            try
            {
                l_service =
                    (WEB3EquityChangeOrderService) Services.getService(
                        WEB3EquityChangeOrderService.class);
            }
            // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            catch (Exception e)
            {
                l_response =
                    (WEB3EquityChangeCompleteResponse) l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "株式注文訂正サービスの取得に失敗しました。",
                    l_response.errorInfo,
                    e);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
    
            try
            {
                l_response =
                    (WEB3EquityChangeCompleteResponse) l_service.execute(l_request);
            }
            catch (WEB3BaseException e)
            {
                l_response =
                    (WEB3EquityChangeCompleteResponse) l_request.createResponse();
                l_response.errorInfo = e.getErrorInfo();
                l_response.errorMessage = e.getErrorMessage();
                log.error(l_request, "株式注文訂正完了に失敗しました。", e);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_bre)
            {
                l_response =
                    (WEB3EquityChangeCompleteResponse) l_request.createResponse();
                l_response.errorInfo = l_bre.getErrorInfo();
                log.error(l_request, "株式注文訂正完了に失敗しました。", l_bre);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }
        //４－２）　@PTS市場の場合（市場.isPTS市場()==true）
        //(PTS)株式注文訂正サービスオブジェクトを取得し、execute（）をコールする。
        else
        {
            WEB3EquityPTSChangeOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityPTSChangeOrderService)Services.getService(
                        WEB3EquityPTSChangeOrderService.class);
            }
            // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            catch (Exception l_ex)
            {
                l_response =
                    (WEB3EquityChangeCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "(PTS)株式注文訂正サービスの取得に失敗しました。",
                    l_response.errorInfo,
                    l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquityChangeCompleteResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquityChangeCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
                log.error(l_request, "(PTS)株式注文訂正完了に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                l_response =
                    (WEB3EquityChangeCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)株式注文訂正完了に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }

        log.exiting(STR_METHOD_NAME);
        // レスポンスオブジェクトを返却する     
        return l_response;
    }
}
@
