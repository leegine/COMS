head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXSimplexConnectionSystem.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Simplex接続システム(WEB3FXSimplexConnectionSystem.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/17 張騰宇 (中訊) 新規作成・モデル1200 1212 1213 1218 1221 1226 1230 1237
Revision History : 2009/10/14 張騰宇 (中訊) モデル1240
*/
package webbroker3.aio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import webbroker3.aio.define.WEB3AdminAioGftOperationDivDef;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioFXSendRecieveDivDef;
import webbroker3.aio.define.WEB3AioTransferDetailMessageDef;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXSimplexAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3GftSoapResultCodeDef;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (Simplex接続システム) <BR>
 * Simplex接続システム実装クラス <BR>
 * 抽象クラス<BR>
 * @@author 張騰宇(中訊)
 * @@version 1.0
 */
public abstract class WEB3FXSimplexConnectionSystem extends WEB3FXExtSystemCommon implements WEB3ExtConnection
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSimplexConnectionSystem.class);
    
    /**
     * 抽象メソッド（abstract） <BR>
     * <BR>
     * 口座開設の場合、Simplex口座開設接続システムクラスのcreateHash <BR>
     * 振替可能額の場合、Simplex振替可能額接続システムのcreateHashが呼びされる。<BR>
     * @@param l_simplexAskingTelegramUnit - (Simplex依頼電文明細)<BR>
     * Simplex依頼電文明細<BR>
     * @@param l_strOperationName - (オペレーション名)<BR>
     * オペレーション名<BR>
     * @@return WEB3FXSimplexAskingTelegramUnit
     */
    protected abstract WEB3FXSimplexAskingTelegramUnit createHash(
        WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit, String l_strOperationName) throws WEB3BaseException;
    
    /**
     * 抽象メソッド（abstract） <BR>
     * <BR>
     * 口座開設の場合、Simplex口座開設接続システムクラスのcreateURL <BR>
     * 振替可能額の場合、Simplex振替可能額接続システムのcreateURLが呼びされる。<BR>
     * @@param l_strEndpointName - (エンドポイント名)<BR>
     * エンドポイント名<BR>
     * @@param l_simplexAskingTelegramUnit - (Simplex依頼電文明細)<BR>
     * Simplex依頼電文明細<BR>
     * @@return WEB3FXSimplexAskingTelegramUnit
     */
    protected abstract String createURL(
        String l_strEndpointName, WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit) throws WEB3BaseException;
    
    /**
     * (createSimplex依頼電文明細)<BR>
     * 抽象メソッド（abstract）<BR>
     * <BR>
     * 口座開設の場合、Simplex口座開設接続システムクラスのcreateSimplex依頼電文明細<BR>
     * 振替可能額の場合、Simplex振替可能額接続システムのcreateSimplex依頼電文明細が呼びされる。<BR>
     * @@param l_message - (GFT電文メッセージ)<BR>
     * GFT電文メッセージ<BR>
     * @@return WEB3FXSimplexAskingTelegramUnit
     * @@throws WEB3BaseException
     */
    protected abstract WEB3FXSimplexAskingTelegramUnit createSimplexAskingTelegramUnit(
        Message l_message) throws WEB3BaseException;

    /**
     * (saveSOAPメッセージ)<BR>
     * SOAPメッセージ保存テーブルに、メッセージを格納する。 <BR>
     * <BR>
     * FXデータ制御サービスImplには、insertSOAPメッセージ()を呼び出す。 <BR>
     * DB更新仕様「Simplex接続_SOAPメッセージ保存テーブル.xls」を参照してください。 <BR>
     * [引数]： <BR>
     * 　@部店ID：引数.部店ID <BR>
     * 　@接続区分：引数.接続区分 <BR>
     * 　@送受信区分：引数.送受信区分 <BR>
     * 　@メッセージ：引数.メッセージ <BR>
     * @@param l_lngBranchID - (部店ID)<BR>
     * 部店ID<BR>
     * @@param l_strConnectionDiv - (接続区分)<BR>
     * 接続区分<BR>
     * @@param l_strSendRcvDiv - (送受信区分)<BR>
     * 送受信区分<BR>
     * <BR>
     * 0：送信<BR>
     * 1：受信<BR>
     * @@param l_strMessage - (メッセージ)<BR>
     * メッセージ<BR>
     */
    protected void saveSOAPMessage(
        long l_lngBranchID, String l_strConnectionDiv, String l_strSendRcvDiv, String l_strMessage)
    {
        final String STR_METHOD_NAME = "saveSOAPMessage(long, String, String, String) ";
        log.entering (STR_METHOD_NAME );

        WEB3FXDataControlService l_service =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        l_service.insertSOAPMessage(l_lngBranchID, l_strConnectionDiv, l_strSendRcvDiv, l_strMessage);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (sendURLメッセージ )<BR>
     * sendURLメッセージを行う。 <BR>
     * <BR>
     * １）URLクラスのインスタンスを生成する。 <BR>
     * <BR>
     * ２）接続を生成する。 <BR>
     * <BR>
     * ３）接続属性を設定する。 <BR>
     * <BR>
     * ４）接続実行。 <BR>
     * <BR>
     * ５）出力ストリームを取得。 <BR>
     * 　@５－１）httpコード状態が正常である場合、http請求の戻り値をStringBufferに格納する。 <BR>
     * <BR>
     * ６）StringBuffer.toString()を返却する。<BR>
     * <BR>
     * ※以上処理に異常が発生する場合、<BR>
     * WEB3のエラー「BUSINESS_ERROR_02398」をスローする。<BR>
     * @@param l_strConnectionURL - (接続URL)<BR>
     * 接続URL<BR>
     * @@return String
     */
    protected String sendURLMessage(String l_strConnectionURL) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "sendURLMessage(String)";
        log.entering (STR_METHOD_NAME );

        HttpURLConnection l_connection = null;
        BufferedReader l_brResult = null;
        String l_strResult= null;
        try
        {
        	
            String l_urlArr[] = l_strConnectionURL.split(";");
            String l_soapUrl = "";
            
            if (l_urlArr.length == 3)
            {
            	l_soapUrl = l_urlArr[2];
            	if (l_soapUrl.indexOf("https://") >= 0)
            	{
                    this.registerMyHostnameVerifier();
                    URL l_postURL = new URL(null, l_strConnectionURL, new sun.net.www.protocol.https.Handler());
                    l_connection = (HttpURLConnection)l_postURL.openConnection();
                    l_connection.setRequestMethod("GET");
                    l_connection.setRequestProperty("Content-Type", "text/html;charset=Windows-31J");
                    l_connection.setRequestProperty("Connection", "Keep - Alive");
                    l_connection.connect();
            	}
            	else
            	{
                	URL l_postURL = new URL(null, l_strConnectionURL);
                	l_connection = (HttpURLConnection)l_postURL.openConnection();
                	l_connection.setRequestMethod("GET");
                	l_connection.setRequestProperty("Content-Type", "text/html;charset=Windows-31J");
                	l_connection.setRequestProperty("Connection", "Keep - Alive");
                	l_connection.connect();
            	}
            }
            else if (l_urlArr.length == 1)
            {    
            	
            	if (l_strConnectionURL.indexOf("https://") >= 0)
            	{
                    this.registerMyHostnameVerifier();
                    URL l_postURL = new URL(null, l_strConnectionURL, new sun.net.www.protocol.https.Handler());
                    l_connection = (HttpURLConnection)l_postURL.openConnection();
                    l_connection.setRequestMethod("GET");
                    l_connection.setRequestProperty("Content-Type", "text/html;charset=Windows-31J");
                    l_connection.setRequestProperty("Connection", "Keep - Alive");
                    l_connection.connect();
            	}
            	else
            	{
                	URL l_postURL = new URL(null, l_strConnectionURL);
                	l_connection = (HttpURLConnection)l_postURL.openConnection();
                	l_connection.setRequestMethod("GET");
                	l_connection.setRequestProperty("Content-Type", "text/html;charset=Windows-31J");
                	l_connection.setRequestProperty("Connection", "Keep - Alive");
                	l_connection.connect();
            	}
            }

            // 出力ストリームを取得
            String l_strResponseCode = l_connection.getHeaderField(0);
            // httpコード状態が正常である場合、http請求の戻り値をStringBufferに格納する。
            if (l_strResponseCode != null && l_strResponseCode.indexOf("200") >= 0)
            {
                l_brResult = new BufferedReader(new InputStreamReader(l_connection.getInputStream(), "Windows-31J"));
                StringBuffer l_sbResult = new StringBuffer();
                String l_strLine = null;
                while ((l_strLine = l_brResult.readLine()) != null)
                {
                    l_sbResult.append(l_strLine);
                }
                l_strResult = l_sbResult.toString();
            }
        }
        catch (Exception l_ex)
        {
            if (l_connection != null)
            {
                l_connection.disconnect();
            }
            //※以上処理に異常が発生する場合、WEB3のエラー「BUSINESS_ERROR_02398」をスローする。
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        finally
        {
            if (l_brResult != null)
            {
                try
                {
                    l_brResult.close();
                }
                catch (IOException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_strResult;
    }

    /**
     * (getURLSimplex受付結果コード)<BR>
     * sendURLメッセージの結果を解析する。 <BR>
     * <BR>
     * １）sendURLメッセージの結果パラメータにerrorIDがあれば、connectResultに先頭の一つ目のerrorIDをセット<BR>
     * それ以外は０をセット<BR>
     * 　@　@key：　@外部接続.CONNECT_RESULT<BR>
     * 　@　@value：　@sendURLメッセージの結果パラメータにerrorID　@or ０<BR>
     * <BR>
     * ２）sendURLメッセージの結果パラメータにerrorIDがあれば、<BR>
     * 先頭の一つ目のerrorIDをWEBⅢエラーコードに変換し、返却する。 <BR>
     * それ以外の場合、"00000000"を返却する<BR>
     * @@param l_strsendURLMessageResult - (sendURLメッセージの結果)<BR>
     * sendURLメッセージの結果<BR>
     * @@return String
     */
    protected String getURLSimplexResultCode(String l_strSendURLMessageResult)
    {
        final String STR_METHOD_NAME = "getURLSimplexResultCode(String)";
        log.entering (STR_METHOD_NAME );
        //１）sendURLメッセージの結果パラメータにerrorIDがあれば、connectResultに先頭の一つ目のerrorIDをセット
        String[] l_strURLMessages = l_strSendURLMessageResult.split("<errorId>");
        if (l_strURLMessages != null && l_strURLMessages.length > 1)
        {
            String[] l_strMessages = l_strURLMessages[1].split("</errorId>");
            connectionResultDetails.put(CONNECT_RESULT, l_strMessages[0].trim());
            log.exiting(STR_METHOD_NAME);
            return this.changeErrorCode(l_strMessages[0].trim());
        }
        connectionResultDetails.put(CONNECT_RESULT, "0");
        log.exiting(STR_METHOD_NAME);
        return WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
    }

    /**
     * Simplex接続のシステムへ依頼電文の送付を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「sendMessage(Simplex)」を参照する<BR>
     * @@param l_message - (GFT電文メッセージ)<BR>
     * GFT電文メッセージ<BR>
     * @@param l_prefRpcParams - (外部システムSOAPプリファ@レンス（RPC形式)<BR>
     * 外部システムSOAPプリファ@レンス（RPC形式)<BR>
     * @@throws WEB3BaseException
     */
    public void sendMessage(
        Message l_message, SoapConnectPrefRpcParams l_prefRpcParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "sendMessage(Message, SoapConnectPrefRpcParams)";
        log.entering(STR_METHOD_NAME);

        String l_strEndpointName;

        if (l_message == null || l_prefRpcParams == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        if (!(l_message instanceof WEB3FXGftAskingTelegramUnit))
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }
        //createSimplex依頼電文明細
        WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit =
            this.createSimplexAskingTelegramUnit(l_message);

        //createHash(Simplex依頼電文明細 : Simplex依頼電文明細, オペレーション名 : String)
        WEB3FXSimplexAskingTelegramUnit l_fxSimplexAskingTelegramUnit =
            this.createHash(l_simplexAskingTelegramUnit, l_prefRpcParams.getOperationName());

        //createURL(エンドポイント名 : String, Simplex依頼電文明細 : Simplex依頼電文明細)
        String[] l_strEndPointNames = l_prefRpcParams.getEndpointName().split(";");

        //引数.外部システムSOAPプリファ@レンス（RPC形式）.エンドポイント名の分割数 == 3の場合
        if (l_strEndPointNames.length == 3)
        {
        	//引数.外部システムSOAPプリファ@レンス（RPC形式）.エンドポイント名の配列第3要素
        	l_strEndpointName = l_strEndPointNames[2];
        }

        //引数.外部システムSOAPプリファ@レンス（RPC形式）.エンドポイント名の分割数 == 1の場合
        else if (l_strEndPointNames.length == 1)
        {
        	//引数.外部システムSOAPプリファ@レンス（RPC形式）.エンドポイント名の配列第1要素
        	l_strEndpointName = l_strEndPointNames[0];
        }

        //※上記の条件以外の場合、外部システム接続エラーをスローする
        else
        {
            //※上記の条件以外の場合、外部システム接続エラーをスローする
            log.debug("外部システム接続エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "外部システム接続エラー。");
        }

        String l_strURL = this.createURL(l_strEndpointName, l_fxSimplexAskingTelegramUnit);

        //saveSOAPメッセージ(部店ID : long, 接続区分 : string, 送受信区分 : string, メッセージ : string)
        //[引数]
        //　@部店ID：引数.外部システムSOAPプリファ@レンス（RPC形式）.部店ID
        //　@接続区分：引数.外部システムSOAPプリファ@レンス（RPC形式）.接続区分
        //　@送受信区分：0：送信
        //　@メッセージ：作成したURL
        this.saveSOAPMessage(
            l_prefRpcParams.getBranchId(), l_prefRpcParams.getConnectDiv(), WEB3AioFXSendRecieveDivDef.SEND, l_strURL);

        String l_strURLMessage = null;
        String l_strSimplexResultCode = null;
        try
        {
            //sendURLメッセージ(接続URL : string)
            l_strURLMessage = this.sendURLMessage(l_strURL);
            
            //getURLSimplex受付結果コード(sendURLメッセージの結果 : string)
            l_strSimplexResultCode = this.getURLSimplexResultCode(l_strURLMessage);
        }
        catch (WEB3BaseException l_ex)
        {
            if (WEB3ErrorCatalog.BUSINESS_ERROR_02398.equals(l_ex.getErrorInfo()))
            {
                //”GFT接続エラー”(00000990)、
                l_strSimplexResultCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000990;
                // ”接続エラー（システムエラー）”(9991)
                this.connectionResultDetails.put(CONNECT_RESULT, WEB3GftSoapResultCodeDef.CONNECT_ERROR);
            }
            else
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getErrorMessage(),
                    l_ex);
            }
        }

        //saveSOAPメッセージ(部店ID : long, 接続区分 : string, 送受信区分 : string, メッセージ : string)
        //[引数]
        //　@部店ID：引数.外部システムSOAPプリファ@レンス（RPC形式）.部店ID
        //　@接続区分：引数.外部システムSOAPプリファ@レンス（RPC形式）.接続区分
        //　@送受信区分：1：受信 
        //　@メッセージ：返却したXml
        this.saveSOAPMessage(
            l_prefRpcParams.getBranchId(),
            l_prefRpcParams.getConnectDiv(),
            WEB3AioFXSendRecieveDivDef.RECEIVE,
            l_strURLMessage);

        //resultCodeを設定する
        //this.接続結果明細には、対象を追加する。
        //　@key:　@外部接続.RESULT_CODE
        //　@value:　@取得した受付結果コード
        this.connectionResultDetails.put(RESULT_CODE, l_strSimplexResultCode);

        WEB3FXGftAskingTelegramUnit l_gftAskingTelegramUnit = (WEB3FXGftAskingTelegramUnit)l_message;
        String l_strOperationDiv = l_gftAskingTelegramUnit.gftOperationDiv;
        //受付結果コード = 00000000(処理完了)の場合
        if (WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000000.equals(l_strSimplexResultCode))
        {
            //引数.GFT依頼電文明細.処理区分 = 01：口座開設の場合
            if (WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN.equals( l_strOperationDiv))
            {
                //fx_acc_01を設定する
                this.connectionResultDetails.put(FX_ACC_01, new Long("111111"));
            }

            //引数.GFT依頼電文明細.処理区分 = 07：振替可能額の場合
            if (WEB3AdminAioGftOperationDivDef.TRANSFER_ABLE_AMT.equals( l_strOperationDiv))
            {
                //amountを設定する
                HashMap l_hmAmount = new HashMap();
                String[] l_strURLMessages = l_strURLMessage.split("<withdrawalLimitAmount>");
                if (l_strURLMessages != null && l_strURLMessages.length > 0)
                {
                    String[] l_strMessages = l_strURLMessages[1].split("</withdrawalLimitAmount>");
                    l_hmAmount.put("111111", l_strMessages[0].trim());
                }
                this.connectionResultDetails.put(AMOUNT, l_hmAmount);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
            
    /**
     * Simplex接続システムの結果通知から、指定した項目名のValueを取得する。 <BR>
     * <BR>
     * this.接続結果明細から、指定した項目名はKeyとして、Valueを返却する。<BR>
     * @@param l_strName - (Simplex項目名)<BR>
     * Simplex項目名<BR>
     * @@return Object
     */
    public Object getResult(String l_strName)
    {
        return this.connectionResultDetails.get(l_strName);
    }

    /**
     * コンストラクタ。<BR>
     * <BR>
     * 接続結果明細を初期化する。<BR>
     */
    public WEB3FXSimplexConnectionSystem()
    {
        this.connectionResultDetails = new HashMap();
    }

    /**
     * WEBⅢエラーコー  ドに変換する。<BR>
     * @@param l_strErrorID - (errorID)<BR>
     * @@return String
     */
    private String changeErrorCode(String l_strErrorID)
    {
        final String STR_METHOD_NAME = "changeErrorCode(String)";
        log.entering (STR_METHOD_NAME );
        String l_strWeb3ErrorCode = "";
        
        if ("MI-05201".equals(l_strErrorID)
            || "MI-05202".equals(l_strErrorID)
            || "MI-05203".equals(l_strErrorID)
            || "MI-05204".equals(l_strErrorID)
            || "MI-05205".equals(l_strErrorID)
            || "MI-05206".equals(l_strErrorID)
            || "MI-05207".equals(l_strErrorID)
            || "MI-05208".equals(l_strErrorID)
            || "MI-05209".equals(l_strErrorID)
            || "MI-05210".equals(l_strErrorID)
            || "MI-05211".equals(l_strErrorID)
            || "MI-05212".equals(l_strErrorID)
            || "MI-05213".equals(l_strErrorID)
            || "MI-05216".equals(l_strErrorID)
            || "MI-05217".equals(l_strErrorID)
            || "MI-03402".equals(l_strErrorID)
            || "MI-03406".equals(l_strErrorID)
            || "MI-03407".equals(l_strErrorID)
            || "MI-03401".equals(l_strErrorID)
            || "MI-03403".equals(l_strErrorID)
            || "MI-03404".equals(l_strErrorID)
            || "MI-03405".equals(l_strErrorID))
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000602;
        }
        else if ("MI-05226".equals(l_strErrorID)
            || "MI-03423".equals(l_strErrorID))
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000909;
        }
        else if ("MI-05220".equals(l_strErrorID)
            || "MI-03410".equals(l_strErrorID)
            || "MI-03420".equals(l_strErrorID))
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000105;
        }
        else if ("MI-05221".equals(l_strErrorID)
            || "MI-03413".equals(l_strErrorID)
            || "MI-03415".equals(l_strErrorID))
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801;
        }
        else if ("MI-03411".equals(l_strErrorID))
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501;
        }
        else if ("MI-03412".equals(l_strErrorID))
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000299;
        }
        else if ("MI-03414".equals(l_strErrorID))
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000204;
        }        
        else
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000901;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strWeb3ErrorCode;
    }

    /* 証明書エラーを削除する。
     * */ 
    private void registerMyHostnameVerifier()
    {
        javax.net.ssl.HostnameVerifier myHv = new javax.net.ssl.HostnameVerifier()
        {
           public boolean verify(String hostName,javax.net.ssl.SSLSession session)
           {
                    return true;
           }
        };
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(myHv);
    }
}
@
