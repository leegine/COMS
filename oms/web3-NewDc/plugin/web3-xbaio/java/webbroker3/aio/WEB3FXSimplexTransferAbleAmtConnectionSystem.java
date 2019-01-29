head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.21.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXSimplexTransferAbleAmtConnectionSystem.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Simplex振替可能額接続システム（WEB3FXSimplexTransferAbleAmtConnectionSystem.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/17 張騰宇 (中訊) 新規作成・モデル1200 1226
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.aio.define.WEB3AioHashAlgorithmDef;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXSimplexAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (Simplex振替可能額接続システム)<BR>
 * Simplex振替可能額接続システムクラス<BR>
 * <BR>
 * @@author 張騰宇(中訊)
 * @@version 1.0
 */
public class WEB3FXSimplexTransferAbleAmtConnectionSystem extends WEB3FXSimplexConnectionSystem
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSimplexTransferAbleAmtConnectionSystem.class);
    
    /**
     * Simplex振替可能額用ハッシュを生成し、返却する。 <BR>
     * <BR>
     * １）Simplex依頼電文明細.タイムスタンプ と <BR>
     * 　@引数.オペレーション名を連結し、MD5暗号化したものを大文字にした値を取得する。 <BR>
     * <BR>
     * ２）引数.Simplex依頼電文明細のハッシュ値に取得したハッシュ値をセットする。 <BR>
     * <BR>
     * ３）引数.Simplex依頼電文明細を返却する。<BR>
     * <BR>
     * @@param l_simplexAskingTelegramUnit - (Simplex依頼電文明細)<BR>
     * Simplex依頼電文明細<BR>
     * @@param l_strOperationName - (オペレーション名)<BR>
     * オペレーション名<BR>
     * @@return WEB3FXSimplexAskingTelegramUnit
     */
    protected WEB3FXSimplexAskingTelegramUnit createHash(
        WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit, String l_strOperationName) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createHash(WEB3FXSimplexAskingTelegramUnit, String)";
        log.entering (STR_METHOD_NAME);
        if (l_simplexAskingTelegramUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //Simplex依頼電文明細.タイムスタンプ と
        //引数.オペレーション名を連結し、MD5暗号化したものを大文字にした値を取得する。
        String[] l_strValue =  new String[]{
            l_simplexAskingTelegramUnit.timeStamp,
            l_strOperationName};
        String l_strHashValue =
            WEB3StringTypeUtility.createHashValue(WEB3AioHashAlgorithmDef.MD5, l_strValue); 
        //引数.Simplex依頼電文明細のハッシュ値に取得したハッシュ値をセットする。 
        l_simplexAskingTelegramUnit.hashValue = l_strHashValue.toUpperCase();
        //引数.Simplex依頼電文明細を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_simplexAskingTelegramUnit;
    }

    /**
     * Simplex振替可能額取得用接続URLを生成し、返却する。 <BR>
     * <BR>
     * １）StringBufferのインスタンスを作成する。 <BR>
     * <BR>
     * ２）StringBufferに、引数.エンドポイント名を追加する。 <BR>
     * <BR>
     * ３）StringBufferに、文字列GetWithdrawLimit.doを追加 <BR>
     * <BR>
     * ４）StringBufferに、http請求URL用 '?' を末尾に追加する。 <BR>
     * <BR>
     * ５）引数.simplex依頼電文の項目分 <BR>
     * 補足資料「（DIR-Simplex）電文フォーマット.xls」のsheet「URLについての振替可能額取得」を参照し、<BR>
     * 下記処理を行う： <BR>
     * 　@５－１）項目名　@+　@"="　@+　@項目値 + "&"　@ <BR>
     * 　@　@　@　@　@(最後の項目に処理時、"&"を追加不要、項目値がnullの場合、項目値を追加しない) <BR>
     * 　@５－２）1にて生成した文字列をStringBufferの末尾に追加する。 <BR>
     * <BR>
     * ６）StringBuffer.toString()を返す。<BR>
     * <BR>
     * @@param l_strEndpointName - (エンドポイント名)<BR>
     * エンドポイント名<BR>
     * @@param l_simplexAskingTelegramUnit - (Simplex依頼電文明細)<BR>
     * Simplex依頼電文明細<BR>
     * @@return String
     */
    protected String createURL(String l_strEndpointName, WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createURL(String, WEB3FXSimplexAskingTelegramUnit)";
        log.entering (STR_METHOD_NAME);
        if (l_simplexAskingTelegramUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //StringBufferのインスタンスを作成する
        StringBuffer l_sbValue = new StringBuffer();
        //StringBufferに、引数.エンドポイント名を追加する
        l_sbValue.append(l_strEndpointName);
        //StringBufferに、文字列GetWithdrawLimit.doを追加 
        l_sbValue.append("/GetWithdrawLimit.do");
        //StringBufferに、http請求URL用 '?' を末尾に追加する
        l_sbValue.append("?");
        //引数.simplex依頼電文の項目分
        //補足資料「（DIR-Simplex）電文フォーマット.xls」のsheet「URLについての振替可能額取得」を参照し、
        //下記処理を行う：
        //項目名　@+　@"="　@+　@項目値 + "&"
        //(最後の項目に処理時、"&"を追加不要、項目値がnullの場合、項目値を追加しない)
        //1にて生成した文字列をStringBufferの末尾に追加する。 
        if (l_simplexAskingTelegramUnit.oseAccountId != null)
        {
            l_sbValue.append("oseAccountId" + "=" + l_simplexAskingTelegramUnit.oseAccountId + "&");
        }
        else
        {
            l_sbValue.append("oseAccountId" + "=" + "&");
        }
        l_sbValue.append("tie-upAccountId" + "=" + "&");
        l_sbValue.append("tie-upSystemId" + "=" + "&");
        if (l_simplexAskingTelegramUnit.timeStamp != null)
        {
            l_sbValue.append("timeStamp" + "=" + l_simplexAskingTelegramUnit.timeStamp + "&");
        }
        else
        {
            l_sbValue.append("timeStamp" + "=" + "&");
        }
        if (l_simplexAskingTelegramUnit.hashValue != null)
        {
            l_sbValue.append("hashValue" + "=" + l_simplexAskingTelegramUnit.hashValue);
        }
        else
        {
            l_sbValue.append("hashValue" + "=");
        }
        //StringBuffer.toString()を返す。
        log.exiting(STR_METHOD_NAME);
        return l_sbValue.toString();
    }

    /**
     * (createSimplex依頼電文明細)<BR>
     * Simplex口座開設依頼電文明細を作成する。<BR>
     * <BR>
     * 引数.GFT電文メッセージから、「（DIR-Simplex）電文フォーマット.xls」の振替可能額取得部分を参考し、<BR>
     * Simplex依頼電文明細を作成する。<BR>
     * <BR>
     * （*）Simplex依頼電文明細.処理区分 = GFT電文メッセージ.処理区分<BR>
     * @@param l_message - (GFT電文メッセージ)<BR>
     * GFT電文メッセージ<BR>
     * @@return WEB3FXSimplexAskingTelegramUnit
     * @@throws WEB3BaseException
     */
    protected WEB3FXSimplexAskingTelegramUnit createSimplexAskingTelegramUnit(
        Message l_message)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSimplexAskingTelegramUnit(Message)";
        log.entering(STR_METHOD_NAME);
        if (l_message == null)
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
        WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit = new WEB3FXSimplexAskingTelegramUnit();
        WEB3FXGftAskingTelegramUnit l_gftAskingTelegramUnit = (WEB3FXGftAskingTelegramUnit)l_message;
        String l_strOperationDiv = l_gftAskingTelegramUnit.gftOperationDiv;
        l_simplexAskingTelegramUnit.simplexOperationDiv = l_strOperationDiv;

        //振替可能額
        //大証FX口座番号
        l_simplexAskingTelegramUnit.oseAccountId = l_gftAskingTelegramUnit.fxFirstLoginId;
        //タイムスタンプ
        l_simplexAskingTelegramUnit.timeStamp = l_gftAskingTelegramUnit.dirSendTime;
        //ハッシュ値
        l_simplexAskingTelegramUnit.hashValue = l_gftAskingTelegramUnit.hashValue;

        log.exiting(STR_METHOD_NAME);
        return l_simplexAskingTelegramUnit;
    }
}
@
