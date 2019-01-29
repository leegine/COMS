head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXSimplexAccOpenConnectionSystem.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Simplex口座開設接続システム（WEB3FXSimplexAccOpenConnectionSystem.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/17 張騰宇 (中訊) 新規作成・モデル1200 1226
Revision History : 2009/10/27 張騰宇(中訊) 仕様変更 モデルNo.1247
Revision History : 2009/12/10 張騰宇(中訊) 実装の問題 No.22
*/
package webbroker3.aio;

import java.net.URLEncoder;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.define.WEB3AioHashAlgorithmDef;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXSimplexAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (Simplex口座開設接続システム)<BR>
 * Simplex口座開設接続システムクラス<BR>
 * <BR>
 * @@author 張騰宇(中訊)
 * @@version 1.0
 */
public class WEB3FXSimplexAccOpenConnectionSystem extends WEB3FXSimplexConnectionSystem
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSimplexAccOpenConnectionSystem.class);

    /**
     * Simplex口座開設用ハッシュを生成し、返却する。<BR>
     * <BR>
     * １）Simplex依頼電文明細.大証FXログインID と<BR>
     * 　@　@Simplex依頼電文明細.第一メールアドレス と<BR>
     * 　@　@Simplex依頼電文明細.タイムスタンプ と<BR>
     * 　@　@引数.オペレーション名を連結し、MD5暗号化したものを大文字にした値を取得する。<BR>
     * <BR>
     * ２）引数.Simplex依頼電文明細のハッシュ値に取得したハッシュ値をセットする。<BR>
     * <BR>
     * ３）引数.Simplex依頼電文明細を返却する。<BR>
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
        //Simplex依頼電文明細.大証FXログインID と
        //Simplex依頼電文明細.第一メールアドレス と
        //Simplex依頼電文明細.タイムスタンプ と
        //引数.オペレーション名を連結し、MD5暗号化したものを大文字にした値を取得する。
        String[] l_strValue =  new String[]{
            l_simplexAskingTelegramUnit.oseFxLoginId,
            l_simplexAskingTelegramUnit.mail1,
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
     * Simplex口座開設用接続URLを生成し、返却する。<BR>
     * <BR>
     * １）StringBufferのインスタンスを作成する。<BR>
     * <BR>
     * ２）StringBufferに、引数.エンドポイント名を追加する。<BR>
     * <BR>
     * ３）StringBufferに、文字列RegisterOseAccount.doを追加<BR>
     * <BR>
     * ４）StringBufferに、http請求URL用 '?' を末尾に追加する。<BR>
     * <BR>
     * ５）引数.simplex依頼電文の項目分<BR>
     * 補足資料「（DIR-Simplex）電文フォーマット.xls」<BR>
     * 　@のsheet「URLについての口座開設部分」を参照し、下記処理を行う：<BR>
     * 　@５－１）項目名　@+　@"="　@+　@項目値 + "&"　@<BR>
     * 　@　@　@　@　@(最後の項目に処理時、"&"を追加不要、項目値がnullの場合、項目値を追加しない)<BR>
     * 　@５－２）1にて生成した文字列をStringBufferの末尾に追加する。<BR>
     * <BR>
     * ６）StringBuffer.toString()を返す。<BR>
     * <BR>
     * @@param l_strEndpointName - (エンドポイント名)<BR>
     * エンドポイント名<BR>
     * @@param l_simplexAskingTelegramUnit - (Simplex依頼電文明細)<BR>
     * Simplex依頼電文明細<BR>
     * @@return String
     */
    protected String createURL(
        String l_strEndpointName, WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit) throws WEB3BaseException
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
        //StringBufferに、文字列RegisterOseAccount.doを追加
        l_sbValue.append("/RegisterOseAccount.do");
        //StringBufferに、http請求URL用 '?' を末尾に追加する
        l_sbValue.append("?");
        //引数.simplex依頼電文の項目分
        //補足資料「（DIR-Simplex）電文フォーマット.xls」のsheet「URLについての口座開設部分」を参照し、
        //下記処理を行う：
        //項目名　@+　@"="　@+　@項目値 + "&"
        //(最後の項目に処理時、"&"を追加不要、項目値がnullの場合、項目値を追加しない)
        //1にて生成した文字列をStringBufferの末尾に追加する。 
        try
        {
            if (l_simplexAskingTelegramUnit.oseFxLoginId != null)
            {
                l_sbValue.append("oseFxLoginId" + "=" + l_simplexAskingTelegramUnit.oseFxLoginId + "&");
            }
            else
            {
                l_sbValue.append("oseFxLoginId" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.fullNameKana != null)
            {
                l_sbValue.append("fullNameKana" + "=" + URLEncoder.encode(l_simplexAskingTelegramUnit.fullNameKana,"Windows-31J") + "&");
            }
            else
            {
                l_sbValue.append("fullNameKana" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.fullName != null)
            {
                l_sbValue.append("fullName" + "=" + URLEncoder.encode(l_simplexAskingTelegramUnit.fullName,"Windows-31J") + "&");
            }
            else
            {
                l_sbValue.append("fullName" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.genderType != null)
            {
                l_sbValue.append("genderType" + "=" + l_simplexAskingTelegramUnit.genderType + "&");
            }
            else
            {
                l_sbValue.append("genderType" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.zipCode != null)
            {
                l_sbValue.append("zipCode" + "=" + l_simplexAskingTelegramUnit.zipCode + "&");
            }
            else
            {
                l_sbValue.append("zipCode" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.address1 != null)
            {
                l_sbValue.append("address1" + "=" + URLEncoder.encode(l_simplexAskingTelegramUnit.address1,"Windows-31J") + "&");
            }
            else
            {
                l_sbValue.append("address1" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.address2 != null)
            {
                l_sbValue.append("address2" + "=" + URLEncoder.encode(l_simplexAskingTelegramUnit.address2,"Windows-31J") + "&");
            }
            else
            {
                l_sbValue.append("address2" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.address3 != null)
            {
                l_sbValue.append("address3" + "=" + URLEncoder.encode(l_simplexAskingTelegramUnit.address3,"Windows-31J") + "&");
            }
            else
            {
                l_sbValue.append("address3" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.mail1 != null)
            {
                l_sbValue.append("mail1" + "=" + l_simplexAskingTelegramUnit.mail1 + "&");
            }
            else
            {
                l_sbValue.append("mail1" + "=" + "&");
            }
            l_sbValue.append("mail2" + "=" + "&");
            if (l_simplexAskingTelegramUnit.initialLoginPassword != null)
            {
                l_sbValue.append("initialLoginPassword" + "=" + l_simplexAskingTelegramUnit.initialLoginPassword + "&");
            }
            else
            {
                l_sbValue.append("initialLoginPassword" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.initialTradePassword != null)
            {
                l_sbValue.append("initialTradePassword" + "=" + l_simplexAskingTelegramUnit.initialTradePassword + "&");
            }
            else
            {
                l_sbValue.append("initialTradePassword" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.initialOsePassword != null)
            {
                l_sbValue.append("initialOsePassword" + "=" + l_simplexAskingTelegramUnit.initialOsePassword + "&");
            }
            else
            {
                l_sbValue.append("initialOsePassword" + "=" + "&");
            }
            l_sbValue.append("cashOutBankCd" + "=" + "&");
            l_sbValue.append("cashOutBranchCd" + "=" + "&");
            l_sbValue.append("cashOutAccountType" + "=" + "&");
            l_sbValue.append("cashOutAccountNo" + "=" + "&");
            l_sbValue.append("cashOutAccountName" + "=" + "&");
            l_sbValue.append("virtualBankCd" + "=" + "&");
            l_sbValue.append("virtualBranchCd" + "=" + "&");
            l_sbValue.append("virtualAccountType" + "=" + "&");
            l_sbValue.append("virtualAccountNo" + "=" + "&");
            l_sbValue.append("virtualAccountName" + "=" + "&");
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
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "予期しないシステムエラーが発生しました。");
        }
        //StringBuffer.toString()を返す。
        log.exiting(STR_METHOD_NAME);
        return l_sbValue.toString();
    }

    /**
     * (createSimplex依頼電文明細)<BR>
     * Simplex口座開設依頼電文明細を作成する。<BR>
     * <BR>
     * 引数.GFT電文メッセージから、「（DIR-Simplex）電文フォーマット.xls」の口座開設部分を参考し、<BR>
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
        //口座開設
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount =
            l_accountManager.getMainAccount(
                l_gftAskingTelegramUnit.institutionCode,
                l_gftAskingTelegramUnit.branchCode,
                l_gftAskingTelegramUnit.accountCode);
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //大証FXログインID
        l_simplexAskingTelegramUnit.oseFxLoginId = l_gftAskingTelegramUnit.fxFirstLoginId;
        //顧客名カナ
        //対応としては、"（"、"）"、"."に関してそれを半角スペースに変換するという対応を入れる
        String l_strfullNameKana = WEB3StringTypeUtility.to1byteKana(l_mainAccountRow.getFamilyNameAlt1());
        l_simplexAskingTelegramUnit.fullNameKana = l_strfullNameKana.replaceAll("\\(|\\)|\\."," ");
        //顧客名
        l_simplexAskingTelegramUnit.fullName = l_mainAccountRow.getFamilyName();
        //性別
        l_simplexAskingTelegramUnit.genderType = l_mainAccountRow.getSex();
        //郵便番号
        l_simplexAskingTelegramUnit.zipCode = l_mainAccountRow.getZipCode();
        //住所１
        l_simplexAskingTelegramUnit.address1 = l_gftAskingTelegramUnit.address1;
        //住所2
        if(l_gftAskingTelegramUnit.address2 == null)
        {
        	l_simplexAskingTelegramUnit.address2 = " ";	
        }
        else
        {
            l_simplexAskingTelegramUnit.address2 = l_gftAskingTelegramUnit.address2;
        }
        //住所3
        l_simplexAskingTelegramUnit.address3 = l_gftAskingTelegramUnit.address3;
        //第一メールアドレス
        l_simplexAskingTelegramUnit.mail1 = l_gftAskingTelegramUnit.fxMailAddress;
        //初期ログインパスワード
        l_simplexAskingTelegramUnit.initialLoginPassword = l_gftAskingTelegramUnit.fxFirstPassword;
        //初期取引パスワード
        l_simplexAskingTelegramUnit.initialTradePassword = l_gftAskingTelegramUnit.fxFirstPassword;
        //初期大証パスワード
        l_simplexAskingTelegramUnit.initialOsePassword = l_gftAskingTelegramUnit.fxPassword2;
        //タイムスタンプ
        l_simplexAskingTelegramUnit.timeStamp = l_gftAskingTelegramUnit.dirSendTime;
        //ハッシュ値
        l_simplexAskingTelegramUnit.hashValue = l_gftAskingTelegramUnit.hashValue;

        log.exiting(STR_METHOD_NAME);
        return l_simplexAskingTelegramUnit;
    }
}
@
