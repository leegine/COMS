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
filename	WEB3EquityOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文ハンドラ(WEB3EquityOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/18 洪華(中訊) 新規作成
                   2004/12/20 中尾寿彦(SRA) 残案件対応による修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 2007/12/17 張騰宇 モデル1206
Revesion History : 2008/01/08 張騰宇 モデル1280
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyCompleteResponse;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmResponse;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteResponse;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderService;
import webbroker3.equity.service.delegate.WEB3EquityPTSOrderService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文ハンドラ）。<BR>
 * <BR>
 * 株式注文ハンドラクラス
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.kernel.handler.MessageHandler
 */
public class WEB3EquityOrderHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderHandler.class);

    /**
     * デフォルトコンストラクタ。<BR>
     * <BR>
     * @@roseuid 4034965402D6
     */
    public WEB3EquityOrderHandler()
    {

    }

    /**
     * (株式注文買付確認リクエスト)<BR>
     * クライアントよりリクエストを受け、現物株式注文確認処理を実行する。 <BR>
     * （システム実装方針ガイド 4.5.ハンドラ　@参照） <BR>
     * <BR>
     * １）市場コードのチェックを行う。 <BR>
     * <BR>
     * 　@１－１）リクエスト.市場コード ＝ nullの場合、 <BR>
     * 　@　@　@　@　@「市場コードがnull」の例外をスローする。  <BR>
     * <BR>
     * 　@１－２）リクエスト.市場コード≠nullかつ <BR>
     * 　@　@　@　@　@下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「市場コードが未定義の値」の例外をスローする。  <BR>
     * 　@　@　@　@　@・１：東京  <BR>
     * 　@　@　@　@　@・２：大阪  <BR>
     * 　@　@　@　@　@・３：名古屋  <BR>
     * 　@　@　@　@　@・６：福岡  <BR>
     * 　@　@　@　@　@・８：札幌  <BR>
     * 　@　@　@　@　@・９：NNM  <BR>
     * 　@　@　@　@　@・１０：JASDAQ  <BR>
     * 　@　@　@　@　@・１１：JNX-PTS  <BR>
     * 　@　@　@　@　@・９９：優先市場  <BR>
     * <BR>
     * ２）リクエスト.市場コード != "優先市場"の場合、市場オブジェクトを取得する。 <BR>
     * <BR>
     * 　@　@[拡張金融オブジェクトマネージャ.get市場()にセットする引数]  <BR>
     * <BR>
     * 　@　@証券会社：OpLoginSecurityServiceより取得した証券会社コード <BR>
     * 　@　@市場コード：リクエスト.市場コード <BR>
     * <BR>
     * ３）PTS市場かどうか判定し処理を呼び分ける。 <BR>
     * <BR>
     * 　@　@３－１）リクエスト.市場コード != "優先市場"の場合、 <BR>
     * 　@　@　@　@　@　@かつ、PTS市場の場合(市場.isPTS市場()==true) <BR>
     * 　@　@　@　@　@　@(PTS)現物株式注文サービスオブジェクトを取得し、execute()をコールする。 <BR>
     * <BR>
     * 　@　@３－２）リクエスト.市場コード == "優先市場"の場合、 <BR>
     * 　@　@　@　@　@　@または、PTS市場でない場合(市場.isPTS市場()==false) <BR>
     * 　@　@　@　@　@　@株式注文サービスオブジェクトを取得し、execute()をコールする。 <BR>
     * <BR>
     * ４）サービスで例外が発生した場合は、<BR>
     * エラー情報をレスポンスメッセージに設定する。 <BR>
     * <BR>
     * ５）レスポンスオブジェクトを返却する。<BR>
     * @@param l_request
     * @@return WEB3EquityBuyOrderConfirmResponse
     * @@roseuid 4042EF0E0303
     */
    public WEB3EquityBuyConfirmResponse equityBuyOrderConfirm(WEB3EquityBuyConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "equityBuyOrderConfirm(WEB3EquityBuyConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityBuyConfirmResponse l_response = null;

        //１）市場コードのチェックを行う。
        //１－１）リクエスト.市場コード ＝ nullの場合、
        //「市場コードがnull」の例外をスローする。
        if (l_request.marketCode == null)
        {
            l_response =
                (WEB3EquityBuyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00443;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                    STR_METHOD_NAME);
            log.error("市場コードが未指定です。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //１－２）リクエスト.市場コード≠nullかつ 下記の値以外の場合、
        //「市場コードが未定義の値」の例外をスローする。
        //・１：東京
        //・２：大阪
        //・３：名古屋
        //・６：福岡
        //・８：札幌
        //・９：NNM
        //・１０：JASDAQ
        //・１１：JNX-PTS
        //・９９：優先市場
        if (!(WEB3MarketCodeDef.TOKYO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.OSAKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NAGOYA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.FUKUOKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.SAPPORO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NNM.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JASDAQ.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JNX_PTS.equals(l_request.marketCode)
            || WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_request.marketCode)))
        {
            l_response =
                (WEB3EquityBuyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00608;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    STR_METHOD_NAME);
            log.error("市場コードが存在しないコード値です。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //２）リクエスト.市場コード != "優先市場"の場合、市場オブジェクトを取得する。
        //[拡張金融オブジェクトマネージャ.get市場()にセットする引数]
        //証券会社：OpLoginSecurityServiceより取得した証券会社コード
        //市場コード：リクエスト.市場コード
        WEB3GentradeMarket l_market = null;
        boolean l_blnIsPTSMarket = false;
        if (!WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_request.marketCode))
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            try
            {
                long l_lngAccountId = l_opLoginSec.getAccountId();
                MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
                //証券会社コード
                String l_strInstitutionCode =
                    l_mainAccount.getInstitution().getInstitutionCode();
                l_market = (WEB3GentradeMarket)l_objectManager.getMarket(
                    l_strInstitutionCode, l_request.marketCode);
            }
            catch (NotFoundException l_ex)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            //市場.isPTS市場()
            try
            {
                l_blnIsPTSMarket = l_market.isPTSMarket();
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }

        //３）PTS市場かどうか判定し処理を呼び分ける。
        //３－１）リクエスト.市場コード != "優先市場"の場合、
        //　@　@　@　@かつ、PTS市場の場合(市場.isPTS市場()==true)
        //　@　@　@　@(PTS)現物株式注文サービスオブジェクトを取得し、execute()をコールする。
        if (!WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_request.marketCode) && l_blnIsPTSMarket)
        {
            WEB3EquityPTSOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityPTSOrderService)Services.getService(
                        WEB3EquityPTSOrderService.class);
            }
            // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            catch (Exception l_ex)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "(PTS)現物株式注文サービスオブジェクト取得に失敗しました。",
                    l_response.errorInfo,
                    l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
                log.error(l_request, "(PTS)株式買付注文確認に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)株式買付注文確認に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }
        //３－２）リクエスト.市場コード == "優先市場"の場合、
        //または、PTS市場でない場合(市場.isPTS市場()==false)
        //株式注文サービスオブジェクトを取得し、execute()をコールする。
        else
        {
            WEB3EquityOrderService l_service = null;        
            try
            {
                l_service =
                    (WEB3EquityOrderService)Services.getService(
                        WEB3EquityOrderService.class);
            }
            catch (Exception e)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse) l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(l_request, "株式買付注文サービス取得に失敗しました。", l_response.errorInfo, e);
            }
    
            try
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException e)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_request.createResponse();
                l_response.errorInfo = e.getErrorInfo();
                l_response.errorMessage = e.getErrorMessage();
                log.error(l_request, "株式買付注文確認に失敗しました。", e.getErrorInfo(), e);
            }
            catch (WEB3BaseRuntimeException l_bre)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_bre.getErrorInfo();
                log.error(l_request, "株式買付注文確認に失敗しました。", l_bre.getErrorInfo(), l_bre);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (株式注文買付完了リクエスト)<BR>
     * クライアントよりリクエストを受け、現物株式注文完了処理を実行する。 <BR>
     * （システム実装方針ガイド 4.5.ハンドラ　@参照） <BR>
     * <BR>
     * １）市場コードのチェックを行う。 <BR>
     * <BR>
     * 　@１－１）リクエスト.市場コード ＝ nullの場合、 <BR>
     * 　@　@　@　@　@「市場コードがnull」の例外をスローする。  <BR>
     * <BR>
     * 　@１－２）リクエスト.市場コード≠nullかつ <BR>
     * 　@　@　@　@　@下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「市場コードが未定義の値」の例外をスローする。  <BR>
     * 　@　@　@　@　@・１：東京  <BR>
     * 　@　@　@　@　@・２：大阪  <BR>
     * 　@　@　@　@　@・３：名古屋  <BR>
     * 　@　@　@　@　@・６：福岡  <BR>
     * 　@　@　@　@　@・８：札幌  <BR>
     * 　@　@　@　@　@・９：NNM  <BR>
     * 　@　@　@　@　@・１０：JASDAQ  <BR>
     * 　@　@　@　@　@・１１：JNX-PTS  <BR>
     * 　@　@　@　@　@・９９：優先市場  <BR>
     * <BR>
     * ２）リクエスト.市場コード != "優先市場"の場合、市場オブジェクトを取得する。 <BR>
     * <BR>
     * 　@　@[拡張金融オブジェクトマネージャ.get市場()にセットする引数]  <BR>
     * <BR>
     * 　@　@証券会社：OpLoginSecurityServiceより取得した証券会社コード <BR>
     * 　@　@市場コード：リクエスト.市場コード <BR>
     * <BR>
     * ３）PTS市場かどうか判定し処理を呼び分ける。 <BR>
     * <BR>
     * 　@　@３－１）リクエスト.市場コード != "優先市場"の場合、 <BR>
     * 　@　@　@　@　@　@かつ、PTS市場の場合(市場.isPTS市場()==true) <BR>
     * 　@　@　@　@　@　@(PTS)現物株式注文サービスオブジェクトを取得し、execute()をコールする。 <BR>
     * <BR>
     * 　@　@３－２）リクエスト.市場コード == "優先市場"の場合、 <BR>
     * 　@　@　@　@　@　@または、PTS市場でない場合(市場.isPTS市場()==false) <BR>
     * 　@　@　@　@　@　@株式注文サービスオブジェクトを取得し、execute()をコールする。<BR>
     * <BR>
     * ４）サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する。<BR>
     * <BR>
     * ５）レスポンスオブジェクトを返却する。<BR>
     * @@param l_request
     * @@return WEB3EquityBuyOrderCompleteResponse
     * @@roseuid 4042EF0E0322
     */
    public WEB3EquityBuyCompleteResponse equityBuyOrderComplete(WEB3EquityBuyCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "equityBuyOrderComplete(WEB3EquityBuyCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityBuyCompleteResponse l_response = null;

        //１）市場コードのチェックを行う。
        //１－１）リクエスト.市場コード ＝ nullの場合、
        //「市場コードがnull」の例外をスローする。
        if (l_request.marketCode == null)
        {
            l_response =
                (WEB3EquityBuyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00443;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                    STR_METHOD_NAME);
            log.error("市場コードが未指定です。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //１－２）リクエスト.市場コード≠nullかつ 下記の値以外の場合、
        //「市場コードが未定義の値」の例外をスローする。
        //・１：東京
        //・２：大阪
        //・３：名古屋
        //・６：福岡
        //・８：札幌
        //・９：NNM
        //・１０：JASDAQ
        //・１１：JNX-PTS
        //・９９：優先市場
        if (!(WEB3MarketCodeDef.TOKYO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.OSAKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NAGOYA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.FUKUOKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.SAPPORO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NNM.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JASDAQ.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JNX_PTS.equals(l_request.marketCode)
            || WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_request.marketCode)))
        {
            l_response =
                (WEB3EquityBuyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00608;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    STR_METHOD_NAME);
            log.error("市場コードが存在しないコード値です。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //２）リクエスト.市場コード != "優先市場"の場合、市場オブジェクトを取得する。
        //[拡張金融オブジェクトマネージャ.get市場()にセットする引数]
        //証券会社：OpLoginSecurityServiceより取得した証券会社コード
        //市場コード：リクエスト.市場コード
        WEB3GentradeMarket l_market = null;
        boolean l_blnIsPTSMarket = false;
        if (!WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_request.marketCode))
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            try
            {
                long l_lngAccountId = l_opLoginSec.getAccountId();
                MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
                //証券会社コード
                String l_strInstitutionCode =
                    l_mainAccount.getInstitution().getInstitutionCode();
                l_market = (WEB3GentradeMarket)l_objectManager.getMarket(
                    l_strInstitutionCode, l_request.marketCode);
            }
            catch (NotFoundException l_ex)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            
            //市場.isPTS市場()
            try
            {
                l_blnIsPTSMarket = l_market.isPTSMarket();
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }

        //３）PTS市場かどうか判定し処理を呼び分ける。
        //３－１）リクエスト.市場コード != "優先市場"の場合、
        //かつ、PTS市場の場合(市場.isPTS市場()==true)
        //(PTS)現物株式注文サービスオブジェクトを取得し、execute()をコールする。
        if (!WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_request.marketCode) && l_blnIsPTSMarket)
        {
            WEB3EquityPTSOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityPTSOrderService)Services.getService(
                        WEB3EquityPTSOrderService.class);
            }
            // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            catch (Exception l_ex)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "(PTS)現物株式注文サービスオブジェクト取得に失敗しました。",
                    l_response.errorInfo,
                    l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
                log.error(l_request, "(PTS)株式買付注文完了に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)株式買付注文完了に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }
        //３－２）リクエスト.市場コード == "優先市場"の場合、
        //または、PTS市場でない場合(市場.isPTS市場()==false)
        //株式注文サービスオブジェクトを取得し、execute()をコールする。
        else
        {
            WEB3EquityOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityOrderService)Services.getService(
                        WEB3EquityOrderService.class);
            }
            catch (Exception e)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(l_request, "株式買付注文サービス取得に失敗しました。", l_response.errorInfo, e);
            }
    
            try
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException e)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = e.getErrorInfo();
                l_response.errorMessage = e.getErrorMessage();
                log.error(l_request, "株式買付注文完了に失敗しました。", e.getErrorInfo(), e);
            }
            catch (WEB3BaseRuntimeException l_bre)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_bre.getErrorInfo();
                log.error(l_request, "株式買付注文完了に失敗しました。", l_bre.getErrorInfo(), l_bre);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (株式注文売付確認リクエスト)<BR>
     * クライアントよりリクエストを受け、現物株式注文確認処理を実行する。 <BR>
     * （システム実装方針ガイド 4.5.ハンドラ　@参照） <BR>
     * <BR>
     * １）市場コードのチェックを行う。 <BR>
     * <BR>
     * 　@１－１）リクエスト.市場コード ＝ nullの場合、 <BR>
     * 　@　@　@　@　@「市場コードがnull」の例外をスローする。  <BR>
     * <BR>
     * 　@１－２）リクエスト.市場コード≠nullかつ <BR>
     * 　@　@　@　@　@下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「市場コードが未定義の値」の例外をスローする。  <BR>
     * 　@　@　@　@　@・１：東京  <BR>
     * 　@　@　@　@　@・２：大阪  <BR>
     * 　@　@　@　@　@・３：名古屋  <BR>
     * 　@　@　@　@　@・６：福岡  <BR>
     * 　@　@　@　@　@・８：札幌  <BR>
     * 　@　@　@　@　@・９：NNM  <BR>
     * 　@　@　@　@　@・１０：JASDAQ  <BR>
     * 　@　@　@　@　@・１１：JNX-PTS  <BR>
     * <BR>
     * ２）市場オブジェクトを取得する。 <BR>
     * <BR>
     * 　@　@[拡張金融オブジェクトマネージャ.get市場()にセットする引数]  <BR>
     * <BR>
     * 　@　@証券会社：OpLoginSecurityServiceより取得した証券会社コード <BR>
     * 　@　@市場コード：リクエスト.市場コード <BR>
     * <BR>
     * ３）PTS市場かどうか判定し処理を呼び分ける。 <BR>
     * <BR>
     * 　@　@３－１）PTS市場の場合(市場.isPTS市場()==true) <BR>
     * 　@　@　@　@　@　@(PTS)現物株式注文サービスオブジェクトを取得し、<BR>
     * 　@　@　@　@　@　@execute()をコールする。 <BR>
     * <BR>
     * 　@　@３－２）PTS市場でない場合(市場.isPTS市場()==false) <BR>
     * 　@　@　@　@　@　@株式注文サービスオブジェクトを取得し、execute()をコールする。<BR>
     * <BR>
     * ４）サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する。<BR>
     * <BR>
     * ５）レスポンスオブジェクトを返却する。<BR>
     * @@param l_request クライアントからのリクエストメッセージを指定する。
     * @@return WEB3EquitySellOrderConfirmResponse
     * @@roseuid 40A02EAD004E
     */
    public WEB3EquitySellConfirmResponse equitySellOrderConfirm(WEB3EquitySellConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "equitySellOrderConfirm(WEB3EquitySellConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquitySellConfirmResponse l_response = null;

        //１）市場コードのチェックを行う。
        //１－１）リクエスト.市場コード ＝ nullの場合、
        //「市場コードがnull」の例外をスローする。
        if (l_request.marketCode == null)
        {
            l_response =
                (WEB3EquitySellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00443;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                    STR_METHOD_NAME);
            log.error("市場コードが未指定です。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //１－２）リクエスト.市場コード≠nullかつ 下記の値以外の場合、
        //「市場コードが未定義の値」の例外をスローする。
        //・１：東京
        //・２：大阪
        //・３：名古屋
        //・６：福岡
        //・８：札幌
        //・９：NNM
        //・１０：JASDAQ
        //・１１：JNX-PTS
        if (!(WEB3MarketCodeDef.TOKYO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.OSAKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NAGOYA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.FUKUOKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.SAPPORO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NNM.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JASDAQ.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JNX_PTS.equals(l_request.marketCode)))
        {
            l_response =
                (WEB3EquitySellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00608;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    STR_METHOD_NAME);
            log.error("市場コードが存在しないコード値です。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //２）市場オブジェクトを取得する。
        //[拡張金融オブジェクトマネージャ.get市場()にセットする引数]
        //証券会社：OpLoginSecurityServiceより取得した証券会社コード
        //市場コード：リクエスト.市場コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        try
        {
            long l_lngAccountId = l_opLoginSec.getAccountId();
            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            //証券会社コード
            String l_strInstitutionCode =
                l_mainAccount.getInstitution().getInstitutionCode();
            l_market = (WEB3GentradeMarket)l_objectManager.getMarket(
                l_strInstitutionCode, l_request.marketCode);
        }
        catch (NotFoundException l_ex)
        {
            l_response =
                (WEB3EquitySellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //３）PTS市場かどうか判定し処理を呼び分ける。
        //３－１）PTS市場の場合(市場.isPTS市場()==true)
        //(PTS)現物株式注文サービスオブジェクトを取得し、execute()をコールする。
        boolean l_blnIsPTSMarket = false;
        try
        {
            l_blnIsPTSMarket = l_market.isPTSMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3EquitySellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        if (l_blnIsPTSMarket)
        {
            WEB3EquityPTSOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityPTSOrderService)Services.getService(
                        WEB3EquityPTSOrderService.class);
            }
            // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            catch (Exception l_ex)
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "(PTS)現物株式注文サービスオブジェクト取得に失敗しました。",
                    l_response.errorInfo,
                    l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
                log.error(l_request, "(PTS)株式売付注文確認に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)株式売付注文確認に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }
        //３－２）PTS市場でない場合(市場.isPTS市場()==false)
        //株式注文サービスオブジェクトを取得し、execute()をコールする。
        else
        {
            WEB3EquityOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityOrderService)Services.getService(
                        WEB3EquityOrderService.class);
            }
            catch (Exception e)
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(l_request, "株式買付注文サービス取得に失敗しました。", l_response.errorInfo, e);
            }
    
            try
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException e)
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_request.createResponse();
                l_response.errorInfo = e.getErrorInfo();
                l_response.errorMessage = e.getErrorMessage();
                log.error(l_request, "株式売付注文確認に失敗しました。", e.getErrorInfo(), e);
            }
            catch (WEB3BaseRuntimeException l_bre)
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_bre.getErrorInfo();
                log.error(l_request, "株式売付注文確認に失敗しました。", l_bre.getErrorInfo(), l_bre);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (株式注文売付完了リクエスト)<BR>
     * クライアントよりリクエストを受け、現物株式注文完了処理を実行する。 <BR>
     * （システム実装方針ガイド 4.5.ハンドラ　@参照） <BR>
     * <BR>
     * １）市場コードのチェックを行う。 <BR>
     * <BR>
     * 　@１－１）リクエスト.市場コード ＝ nullの場合、 <BR>
     * 　@　@　@　@　@「市場コードがnull」の例外をスローする。  <BR>
     * <BR>
     * 　@１－２）リクエスト.市場コード≠nullかつ <BR>
     * 　@　@　@　@　@下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「市場コードが未定義の値」の例外をスローする。  <BR>
     * 　@　@　@　@　@・１：東京  <BR>
     * 　@　@　@　@　@・２：大阪  <BR>
     * 　@　@　@　@　@・３：名古屋  <BR>
     * 　@　@　@　@　@・６：福岡  <BR>
     * 　@　@　@　@　@・８：札幌  <BR>
     * 　@　@　@　@　@・９：NNM  <BR>
     * 　@　@　@　@　@・１０：JASDAQ  <BR>
     * 　@　@　@　@　@・１１：JNX-PTS  <BR>
     * <BR>
     * ２）市場オブジェクトを取得する。 <BR>
     * <BR>
     * 　@　@[拡張金融オブジェクトマネージャ.get市場()にセットする引数]  <BR>
     * <BR>
     * 　@　@証券会社：OpLoginSecurityServiceより取得した証券会社コード <BR>
     * 　@　@市場コード：リクエスト.市場コード <BR>
     * <BR>
     * ３）PTS市場かどうか判定し処理を呼び分ける。 <BR>
     * <BR>
     * 　@　@３－１）PTS市場の場合(市場.isPTS市場()==true) <BR>
     * 　@　@　@　@　@　@(PTS)現物株式注文サービスオブジェクトを取得し、<BR>
     * 　@　@　@　@　@　@execute()をコールする。 <BR>
     * <BR>
     * 　@　@３－２）PTS市場でない場合(市場.isPTS市場()==false) <BR>
     * 　@　@　@　@　@　@株式注文サービスオブジェクトを取得し、execute()をコールする。<BR>
     * <BR>
     * ４）サービスで例外が発生した場合は、<BR>
     * エラー情報をレスポンスメッセージに設定する。 <BR>
     * <BR>
     * ５）レスポンスオブジェクトを返却する。 <BR>
     * @@param l_request
     * @@return WEB3EquitySellOrderCompleteResponse
     * @@roseuid 40A02F06004E
     */
    public WEB3EquitySellCompleteResponse equitySellOrderComplete(WEB3EquitySellCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "equitySellOrderComplete(WEB3EquitySellCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquitySellCompleteResponse l_response = null;

        //１）市場コードのチェックを行う。
        //１－１）リクエスト.市場コード ＝ nullの場合、
        //「市場コードがnull」の例外をスローする。
        if (l_request.marketCode == null)
        {
            l_response =
                (WEB3EquitySellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00443;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                    STR_METHOD_NAME);
            log.error("市場コードが未指定です。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //１－２）リクエスト.市場コード≠nullかつ 下記の値以外の場合、
        //「市場コードが未定義の値」の例外をスローする。
        //・１：東京
        //・２：大阪
        //・３：名古屋
        //・６：福岡
        //・８：札幌
        //・９：NNM
        //・１０：JASDAQ
        //・１１：JNX-PTS
        if (!(WEB3MarketCodeDef.TOKYO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.OSAKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NAGOYA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.FUKUOKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.SAPPORO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NNM.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JASDAQ.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JNX_PTS.equals(l_request.marketCode)))
        {
            l_response =
                (WEB3EquitySellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00608;
            log.debug("市場コードが存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //２）市場オブジェクトを取得する。
        //[拡張金融オブジェクトマネージャ.get市場()にセットする引数]
        //証券会社：OpLoginSecurityServiceより取得した証券会社コード
        //市場コード：リクエスト.市場コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        try
        {
            long l_lngAccountId = l_opLoginSec.getAccountId();
            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            //証券会社コード
            String l_strInstitutionCode =
                l_mainAccount.getInstitution().getInstitutionCode();
            l_market = (WEB3GentradeMarket)l_objectManager.getMarket(
                l_strInstitutionCode, l_request.marketCode);
        }
        catch (NotFoundException l_ex)
        {
            l_response =
                (WEB3EquitySellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    STR_METHOD_NAME);
            log.error("市場コードが存在しないコード値です。", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //３）PTS市場かどうか判定し処理を呼び分ける。
        //３－１）PTS市場の場合(市場.isPTS市場()==true)
        //(PTS)現物株式注文サービスオブジェクトを取得し、execute()をコールする。
        boolean l_blnIsPTSMarket = false;
        try
        {
            l_blnIsPTSMarket = l_market.isPTSMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3EquitySellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        if (l_blnIsPTSMarket)
        {
            WEB3EquityPTSOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityPTSOrderService)Services.getService(
                        WEB3EquityPTSOrderService.class);
            }
            // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            catch (Exception l_ex)
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "(PTS)現物株式注文サービスオブジェクト取得に失敗しました。",
                    l_response.errorInfo,
                    l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
                log.error(l_request, "(PTS)株式売付注文完了に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)株式売付注文完了に失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }
        //３－２）PTS市場でない場合(市場.isPTS市場()==false)
        //株式注文サービスオブジェクトを取得し、execute()をコールする。
        else
        {
            WEB3EquityOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityOrderService)Services.getService(
                        WEB3EquityOrderService.class);
            }
            catch (Exception e)
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(l_request, "株式買付注文サービス取得に失敗しました。", l_response.errorInfo, e);
            }
    
            try
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException e)
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_request.createResponse();
                l_response.errorInfo = e.getErrorInfo();
                l_response.errorMessage = e.getErrorMessage();
                log.error(l_request, "株式売付注文完了に失敗しました。", e.getErrorInfo(), e);
            }
            catch (WEB3BaseRuntimeException l_bre)
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_bre.getErrorInfo();
                log.error(l_request, "株式売付注文完了に失敗しました。", l_bre.getErrorInfo(), l_bre);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
