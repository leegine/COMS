head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTelegramProcessServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : FX電文処理サービスImpl(WEB3FXTelegramProcessServiceImpl)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
                  : 2006/08/01 鈴木(SCS)　@モデルNo.609対応
 Revesion History : 2009/08/25 孟亞南(中訊)　@モデルNo.1193対応
 Revesion History : 2009/10/20 張騰宇(中訊)　@モデルNo.1242対応
 */

package webbroker3.aio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.data.GftMessageParams;
import webbroker3.aio.define.WEB3AioHashAlgorithmDef;
import webbroker3.aio.define.WEB3GftTelegramFormatDef;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3GftMessageDivDef;
import webbroker3.common.define.WEB3GftMessageOperationDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX電文処理サービスImpl) <BR>
 * FX電文処理サービス実装クラス <BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FXTelegramProcessServiceImpl implements
    WEB3FXTelegramProcessService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3FXTelegramProcessServiceImpl.class);
    
    /**
     * (createGFT電文ハッシュ値) <BR>
     * GFT依頼電文の内容からハッシュ値を生成し、返却する。 <BR>
     * <BR>
     * １） ハッシュ項目の連結文字列の作成 <BR>
     * <BR>
     * 「キー名=値」をセットにした文字列の配列を以下の順で作成する <BR>
     * <BR>
     * DIR_sendTime=引数.GFT依頼電文明細.DIR→GFT送信日時 <BR>
     * Operation=引数.GFT依頼電文明細.処理区分 <BR>
     * Account=引数.GFT依頼電文明細.為替保証金口座番号 <BR>
     * email==引数.GFT依頼電文明細.メールアドレス <BR>
     * GFT_link_1=引数.GFT依頼電文明細.初期ログインID <BR>
     * GFT_link_2=引数.GFT依頼電文明細.初期パスワード <BR>
     * Group_name=引数.GFT依頼電文明細.担当区分 <BR>
     * Amount=引数.GFT依頼電文明細.入出金額 <BR>
     * wolfSessionKey=引数.GFT依頼電文明細.WOLFセッションキー <BR>
     * aid=引数.GFT依頼電文明細.アプリケーションID <BR>
     * aa_icd=引数.GFT依頼電文明細.会社コード <BR>
     * aa_bcd=引数.GFT依頼電文明細.部店コード <BR>
     * aa_accd=引数.GFT依頼電文明細.顧客コード <BR>
     * linked_1=引数.GFT依頼電文明細.識別コード <BR>
     * <BR>
     * ２） ハッシュ値の生成 <BR>
     * <BR>
     * WEB3StringTypeUtility.createHashValue()を用いてハッシュ値を生成する。 <BR>
     * [引数の設定] <BR>
     * 計算方式： "MD5" <BR>
     * 計算対象： １）にて作成した文字列配列 <BR>
     * <BR>
     * ３） 生成したハッシュ値を返却する。 <BR>
     * 
     * @@param l_fxGftAskingTelegramUnit - GFT依頼電文明細
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41BE7FC202DE
     */
    public String createGFTTelegramHashValue(WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createGFTTelegramHashValue(" +
            "WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit)";
        log.entering(STR_METHOD_NAME);
        if (l_fxGftAskingTelegramUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@ハッシュ項目の連結文字列の作成 
        //  「キー名=値」をセットにした文字列の配列を以下の順で作成する 
        List l_lisWhere = new Vector();
        
        //   DIR_sendTime=引数.GFT依頼電文明細.DIR→GFT送信日時 
        l_lisWhere.add(WEB3GftTelegramFormatDef.DIR_sendTime + "=" + 
            ((l_fxGftAskingTelegramUnit.dirSendTime == null) ? "" : 
                l_fxGftAskingTelegramUnit.dirSendTime));
        
        //　@ Operation=引数.GFT依頼電文明細.処理区分 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Operation + "=" + 
            ((l_fxGftAskingTelegramUnit.gftOperationDiv == null) ? "" : 
                l_fxGftAskingTelegramUnit.gftOperationDiv));
        
        //　@ Account=引数.GFT依頼電文明細.為替保証金口座番号 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Account + "=" + 
            ((l_fxGftAskingTelegramUnit.fxAccountCode == null) ? "" : 
                l_fxGftAskingTelegramUnit.fxAccountCode));
        
        //　@ email==引数.GFT依頼電文明細.メールアドレス 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.email + "=" + 
            ((l_fxGftAskingTelegramUnit.fxMailAddress == null) ? "" : 
                l_fxGftAskingTelegramUnit.fxMailAddress));
        
        //　@ GFT_link_1=引数.GFT依頼電文明細.初期ログインID 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.GFT_link_1 +  "=" + 
            ((l_fxGftAskingTelegramUnit.fxFirstLoginId == null) ? "" : 
                l_fxGftAskingTelegramUnit.fxFirstLoginId));
        
        //　@ GFT_link_2=引数.GFT依頼電文明細.初期パスワード 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.GFT_link_2 + "=" + 
            ((l_fxGftAskingTelegramUnit.fxFirstPassword == null) ? "" : 
                l_fxGftAskingTelegramUnit.fxFirstPassword));
        
        //　@ Group_name=引数.GFT依頼電文明細.担当区分 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Group_name + "=" + 
            ((l_fxGftAskingTelegramUnit.groupName == null) ? "" : 
                l_fxGftAskingTelegramUnit.groupName));
        
        //　@ Amount=引数.GFT依頼電文明細.入出金額 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Amount + "=" + 
            ((l_fxGftAskingTelegramUnit.cashinoutAmt == null) ? "" : 
                l_fxGftAskingTelegramUnit.cashinoutAmt));
        
        //　@ wolfSessionKey=引数.GFT依頼電文明細.WOLFセッションキー 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.wolfSessionKey + "=" + 
            ((l_fxGftAskingTelegramUnit.wolfSession == null) ? "" : 
                l_fxGftAskingTelegramUnit.wolfSession));
        
        //　@ aid=引数.GFT依頼電文明細.アプリケーションID 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.aa_aid + "=" + 
            ((l_fxGftAskingTelegramUnit.wolfAid == null) ? "" : 
                l_fxGftAskingTelegramUnit.wolfAid));
        
        //　@ aa_icd=引数.GFT依頼電文明細.会社コード 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.cpy + "=" + 
            ((l_fxGftAskingTelegramUnit.institutionCode == null) ? "" : 
                l_fxGftAskingTelegramUnit.institutionCode));
        
        //　@ aa_bcd=引数.GFT依頼電文明細.部店コード 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.brn + "=" + 
            ((l_fxGftAskingTelegramUnit.branchCode == null) ? "" :
                l_fxGftAskingTelegramUnit.branchCode));
        
        //　@ aa_accd=引数.GFT依頼電文明細.顧客コード 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.acc + "=" + 
            ((l_fxGftAskingTelegramUnit.accountCode == null) ? "" : 
                l_fxGftAskingTelegramUnit.accountCode));
        
        //　@ linked_1=引数.GFT依頼電文明細.識別コード 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.req + "=" + 
            ((l_fxGftAskingTelegramUnit.requestNumber == null) ? "" : 
                l_fxGftAskingTelegramUnit.requestNumber));
        
        // test log
        for (int i = 0; i < l_lisWhere.size(); i ++)
        {
            log.debug("l_lisWhere(" + i + ") = " + l_lisWhere.get(i));
            
        }
        //２）　@ハッシュ値の生成 
        //   WEB3StringTypeUtility.createHashValue()を用いてハッシュ値を生成する。 
        //　@ [引数の設定] 
        //　@ 計算方式：　@"MD5" 
        //　@ 計算対象：　@１）にて作成した文字列配列 
        String[] l_strAlgorithmObj = new String[l_lisWhere.size()];
        l_lisWhere.toArray(l_strAlgorithmObj);
        String l_strHashValue = 
            WEB3StringTypeUtility.createHashValue(
                WEB3AioHashAlgorithmDef.MD5, l_strAlgorithmObj); 
        
        //３）　@生成したハッシュ値を返却する。 
        log.exiting(STR_METHOD_NAME);
        return l_strHashValue;
    }

    /**
     * (createGFT電文ハッシュ値) <BR>
     * GFT結果通知電文の内容からハッシュ値を生成し、返却する。 <BR>
     * <BR>
     * １） ハッシュ項目の連結文字列の作成 <BR>
     * <BR>
     * 「キー名=値」をセットにした文字列の配列を以下の順で作成する <BR>
     * <BR>
     * DIR_sendTime=引数.GFT結果通知電文明細.DIR→GFT送信日時 <BR>
     * Operation=引数.GFT結果通知電文明細.処理区分 <BR>
     * Account=引数.GFT結果通知電文明細.為替保証金口座番号 <BR>
     * email==引数.GFT結果通知電文明細.メールアドレス <BR>
     * GFT_link_1=引数.GFT結果通知電文明細.初期ログインID <BR>
     * GFT_link_2=引数.GFT結果通知電文明細.初期パスワード <BR>
     * Group_name=引数.GFT結果通知電文明細.担当区分 <BR>
     * Amount=引数.GFT結果通知電文明細.入出金額 <BR>
     * wolfSessionKey=引数.GFT結果通知電文明細.WOLFセッションキー <BR>
     * aid=引数.GFT結果通知電文明細.アプリケーションID <BR>
     * aa_icd=引数.GFT結果通知電文明細.会社コード <BR>
     * aa_bcd=引数.GFT結果通知電文明細.部店コード <BR>
     * aa_accd=引数.GFT結果通知電文明細.顧客コード <BR>
     * linked_1=引数.GFT結果通知電文明細.識別コード <BR>
     * resultCode=引数.GFT結果通知電文明細.受付結果 <BR>
     * GFT_sendTime=引数.GFT結果通知電文明細.GFT→DIR送信日時 <BR>
     * GFT_act1=引数.GFT結果通知電文明細.為替保証金口座番号（1万通貨） <BR>
     * GFT_act2=引数.GFT結果通知電文明細.為替保証金口座番号（10万通貨） <BR>
     * <BR>
     * ２） ハッシュ値の生成 <BR>
     * <BR>
     * WEB3StringTypeUtility.createHashValue()を用いてハッシュ値を生成する。 <BR>
     * [引数の設定] <BR>
     * 計算方式： "MD5" <BR>
     * 計算対象： １）にて作成した文字列配列 <BR>
     * <BR>
     * ３） 生成したハッシュ値を返却する。 <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41BE94CC0167
     */
    public String createGFTTelegramHashValue(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createGFTTelegramHashValue(" +
            "WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)";
        log.entering(STR_METHOD_NAME);
        if (l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）ハッシュ項目の連結文字列の作成 
        //「キー名=値」をセットにした文字列の配列を以下の順で作成する 
        List l_lisWhere = new Vector();

        //DIR_sendTime=引数.GFT結果通知電文明細.DIR→GFT送信日時 
        l_lisWhere.add(WEB3GftTelegramFormatDef.DIR_sendTime + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.dirSendTime == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.dirSendTime));
        
        //Operation=引数.GFT結果通知電文明細.処理区分 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Operation + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.gftOperationDiv == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.gftOperationDiv));
        
        //Account=引数.GFT結果通知電文明細.為替保証金口座番号 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Account+ "=" + 
            ((l_fxGftResultNoticeTelegramUnit.fxAccountCode == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.fxAccountCode));
        
        //email==引数.GFT結果通知電文明細.メールアドレス 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.email + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.fxMailAddress == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.fxMailAddress));
        
        //GFT_link_1=引数.GFT結果通知電文明細.初期ログインID 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.GFT_link_1 + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.fxFirstLoginId == null) ? "" : 
                l_fxGftResultNoticeTelegramUnit.fxFirstLoginId));
        
        //GFT_link_2=引数.GFT結果通知電文明細.初期パスワード 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.GFT_link_2 + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.fxFirstPassword == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.fxFirstPassword));
        
        //Group_name=引数.GFT結果通知電文明細.担当区分 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Group_name + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.groupName == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.groupName));
        
        //Amount=引数.GFT結果通知電文明細.入出金額 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Amount + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.cashinoutAmt == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.cashinoutAmt));
        
        //wolfSessionKey=引数.GFT結果通知電文明細.WOLFセッションキー 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.wolfSessionKey + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.wolfSession == null) ? "" : 
                l_fxGftResultNoticeTelegramUnit.wolfSession));
        
        //aid=引数.GFT結果通知電文明細.アプリケーションID 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.aa_aid + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.wolfAid == null) ? "" : 
                l_fxGftResultNoticeTelegramUnit.wolfAid));
        
        //aa_icd=引数.GFT結果通知電文明細.会社コード 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.cpy + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.institutionCode == null) ? "" : 
                l_fxGftResultNoticeTelegramUnit.institutionCode));
        
        //aa_bcd=引数.GFT結果通知電文明細.部店コード 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.brn + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.branchCode == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.branchCode));
        
        //aa_accd=引数.GFT結果通知電文明細.顧客コード 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.acc + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.accountCode == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.accountCode));
        
        //linked_1=引数.GFT結果通知電文明細.識別コード 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.req + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.requestNumber == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.requestNumber));

        //resultCode=引数.GFT結果通知電文明細.受付結果 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.resultCode + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.resultCode == null) ? "" : 
                l_fxGftResultNoticeTelegramUnit.resultCode));
        
        //GFT_sendTime=引数.GFT結果通知電文明細.GFT→DIR送信日時 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.GFT_sendTime + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.gftSendTime == null) ? "" : 
                l_fxGftResultNoticeTelegramUnit.gftSendTime));
        
        //GFT_act1=引数.GFT結果通知電文明細.為替保証金口座番号（1万通貨）
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.GFT_ac1 + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.gftAcc1 == null) ? "" : 
                l_fxGftResultNoticeTelegramUnit.gftAcc1)); 
        
        //GFT_act2=引数.GFT結果通知電文明細.為替保証金口座番号（10万通貨）
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.GFT_ac2 + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.gftAcc2 == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.gftAcc2)); 
       
        // test log
        for (int i = 0; i < l_lisWhere.size(); i ++)
        {
            log.debug("l_lisWhere(" + i + ") = " + l_lisWhere.get(i));
            
        }

        //２）ハッシュ値の生成 
        //WEB3StringTypeUtility.createHashValue()を用いてハッシュ値を生成する。 
        //[引数の設定] 
        //計算方式：　@"MD5" 
        //計算対象：　@１）にて作成した文字列配列
        String[] l_strAlgorithmObj = new String[l_lisWhere.size()];
        l_lisWhere.toArray(l_strAlgorithmObj);
        String l_strHashValue = 
            WEB3StringTypeUtility.createHashValue(
                WEB3AioHashAlgorithmDef.MD5, l_strAlgorithmObj); 
        
        //３）　@生成したハッシュ値を返却する。 
        log.exiting(STR_METHOD_NAME);
        return l_strHashValue;
    }

    /**
     * (isGFT電文項目設定) <BR>
     * GFT結果通知電文明細の必須項目に値が設定されているかを判定する。 <BR>
     * 設定されている場合、trueを、設定されていない場合、falseを返却する。 <BR>
     * <BR>
     * １） 共通必須項目チェック <BR>
     * <BR>
     * 以下の値のいずれかがnullであった場合、falseを返却する。 <BR>
     * <BR>
     * ・引数.GFT結果通知電文明細.DIR→GFT送信日時 <BR>
     * ・引数.GFT結果通知電文明細.処理区分 <BR>
     * ・引数.GFT結果通知電文明細.初期ログインID <BR>
     * ・引数.GFT結果通知電文明細.担当区分 <BR>
     * ・引数.GFT結果通知電文明細.WOLFセッションキー <BR>
     * ・引数.GFT結果通知電文明細.アプリケーションID <BR>
     * ・引数.GFT結果通知電文明細.再生成サービスID <BR>
     * ・引数.GFT結果通知電文明細.SSID <BR>
     * ・引数.GFT結果通知電文明細.会社コード <BR>
     * ・引数.GFT結果通知電文明細.部店コード <BR>
     * ・引数.GFT結果通知電文明細.顧客コード <BR>
     * ・引数.GFT結果通知電文明細.識別コード <BR>
     * ・引数.GFT結果通知電文明細.受付結果 <BR>
     * ・引数.GFT結果通知電文明細.GFT→DIR送信日時 <BR>
     * ・引数.GFT結果通知電文明細.ハッシュ値 <BR>
     * <BR>
     * ２） 処理区分別必須項目チェック <BR>
     * <BR>
     * ○引数.GFT結果通知電文明細.処理区分＝01（口座開設）の場合 <BR>
     * <BR>
     * 以下の値のいずれかがnullであった場合、falseを返却する。 <BR>
     * <BR>
     * ・引数.GFT結果通知電文明細.メールアドレス <BR>
     * ・引数.GFT結果通知電文明細.初期パスワード <BR>
     * ・引数.GFT結果通知電文明細.名前（姓） <BR>
     * 
     * 　@以下のチェックは、引数.GFT結果通知電文明細.受付結果が"00000000"の場合のみ行う。
     * 　@それ以外の場合は、チェックをスキップする。
     * ・引数.GFT結果通知電文明細.為替保証金口座番号（1万通貨） <BR>
     * ・引数.GFT結果通知電文明細.為替保証金口座番号（10万通貨） <BR>
     * <BR>
     * ○引数.GFT結果通知電文明細.処理区分＝02（入金）、または、03（出金）の場合 <BR>
     * <BR>
     * 以下の値のいずれかがnullであった場合、falseを返却する。 <BR>
     * <BR>
     * ・引数.GFT結果通知電文明細.為替保証金口座番号 <BR>
     * ・引数.GFT結果通知電文明細.入出金額 <BR>
     * <BR>
     * ３） trueを返却する。 <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41C6C39D0017
     */
    public boolean isGFTTelegramSet(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "isGFTTelegramSet(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit) ";
        log.entering(STR_METHOD_NAME);
        if (l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@共通必須項目チェック 
        //  以下の値のいずれかがnullであった場合、falseを返却する。 
        //・引数.GFT結果通知電文明細.DIR→GFT送信日時 
        //・引数.GFT結果通知電文明細.処理区分 
        //・引数.GFT結果通知電文明細.初期ログインID 
        //・引数.GFT結果通知電文明細.担当区分 
        //・引数.GFT結果通知電文明細.WOLFセッションキー 
        //・引数.GFT結果通知電文明細.アプリケーションID 
        //・引数.GFT結果通知電文明細.再生成サービスID 
        //・引数.GFT結果通知電文明細.SSID 
        //・引数.GFT結果通知電文明細.会社コード 
        //・引数.GFT結果通知電文明細.部店コード 
        //・引数.GFT結果通知電文明細.顧客コード 
        //・引数.GFT結果通知電文明細.識別コード 
        //・引数.GFT結果通知電文明細.受付結果 
        //・引数.GFT結果通知電文明細.GFT→DIR送信日時 
        //・引数.GFT結果通知電文明細.ハッシュ値 
        if (WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.dirSendTime)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.gftOperationDiv)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.fxFirstLoginId)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.groupName)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.wolfSession)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.wolfAid)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.regetServiceId)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.wolfSsid)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.institutionCode)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.branchCode)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.accountCode)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.requestNumber)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.resultCode)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.gftSendTime)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.hashValue))    
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        } 
                   
        //２）　@処理区分別必須項目チェック 
        //○引数.GFT結果通知電文明細.処理区分＝01（口座開設）の場合 
        //以下の値のいずれかがnullであった場合、falseを返却する。
        if (WEB3GftMessageOperationDef.ACCOUNT_OPEN.equals(l_fxGftResultNoticeTelegramUnit.gftOperationDiv))
        {
            //・引数.GFT結果通知電文明細.メールアドレス 
            //・引数.GFT結果通知電文明細.初期パスワード 
            //・引数.GFT結果通知電文明細.名前（姓） 
            //・引数.GFT結果通知電文明細.為替保証金口座番号（1万通貨） 
            //・引数.GFT結果通知電文明細.為替保証金口座番号（10万通貨） 
                       
			if (WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.fxMailAddress)
				|| WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.fxFirstPassword)
				|| WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.fxLastName))

			{
				log.exiting(STR_METHOD_NAME);
				return false;
			}


			if(l_fxGftResultNoticeTelegramUnit.resultCode.equals(WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000))
			{
				if(WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.gftAcc1)
				   || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.gftAcc2))
				{
					log.exiting(STR_METHOD_NAME);
					return false;
				}				   
			}
        }
        
        //○引数.GFT結果通知電文明細.処理区分＝02（入金）、または、03（出金）の場合 
        //以下の値のいずれかがnullであった場合、falseを返却する。 
        if (WEB3GftMessageOperationDef.CASH_OUT.equals(l_fxGftResultNoticeTelegramUnit.gftOperationDiv)
            || WEB3GftMessageOperationDef.CASH_IN.equals(l_fxGftResultNoticeTelegramUnit.gftOperationDiv))
        {
            //・引数.GFT結果通知電文明細.為替保証金口座番号 
            //・引数.GFT結果通知電文明細.入出金額 
            if (WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.fxAccountCode)
                || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.cashinoutAmt))
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }   

        //３）　@trueを返却する。   
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (isGFT電文桁数属性一致) <BR>
     * GFT結果通知電文明細の特有項目（GFT設定項目）について、<BR>
     * 桁数と属性がフォーマットと 一致しているか判定する。 <BR>
     * 一致している場合、trueを、一致していない場合、falseを返却する。 <BR>
     * <BR>
     * １） 受付結果 <BR>
     * 引数.GFT結果通知電文明細.受付結果が以下のいずれかに当てはまる場合、falseを返却する。<BR>
     * <BR>
     * ・数字以外 <BR>
     * ・8桁以外 <BR>
     * <BR>
     * ２） GFT→DIR送信日時 <BR>
     * 引数.GFT結果通知電文明細.GFT→DIR送信日時が以下のいずれかに当てはまる場合、<BR>
     *      falseを返却する。 <BR>
     * <BR>
     * ・数字以外 <BR>
     * ・14桁以外 <BR>
     * <BR>
     * ３） 為替保証金口座番号(1万通貨)、為替保証金口座番号(10万通貨) <BR>
     * <BR>
     * ※引数.GFT結果通知電文明細.処理区分＝01(口座開設)の場合かつ、<BR>
     *   引数.GFT結果通知電文明細.受付結果が"00000000"の場合のみ行う。 <BR>
     * <BR>
     * 引数.GFT結果通知電文明細.為替保証金口座番号(1万通貨)、および <BR>
     * 引数.GFT結果通知電文明細.為替保証金口座番号(10万通貨)、<BR>
     * が以下のいずれかに当てはまる場合、falseを返却する。 <BR>
     * <BR>
     * ・数字以外 <BR>
     * ・10桁 より大きい <BR>
     * <BR>
     * ４） trueを返却する。 <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41C6C39D0027
     */
    public boolean isGFTTelegramLengthPropSame(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "isGFTTelegramLengthPropSame(" +
            "WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)";
        log.entering(STR_METHOD_NAME);    
        if (l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@受付結果 
        //引数.GFT結果通知電文明細.受付結果が以下のいずれかに当てはまる場合、falseを返却する。 
        //・数字以外 
        //・8桁以外
        if (!WEB3StringTypeUtility.isDigit(l_fxGftResultNoticeTelegramUnit.resultCode)
            || l_fxGftResultNoticeTelegramUnit.resultCode.getBytes().length != 8)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）　@GFT→DIR送信日時 
        // 引数.GFT結果通知電文明細.GFT→DIR送信日時が以下のいずれかに当てはまる場合、falseを返却する。 
        //・数字以外 
        //・14桁以外 
        if (!WEB3StringTypeUtility.isDigit(l_fxGftResultNoticeTelegramUnit.gftSendTime)
            || l_fxGftResultNoticeTelegramUnit.gftSendTime.getBytes().length != 14)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //３）　@為替保証金口座番号(1万通貨)、為替保証金口座番号(10万通貨) 
        // ※引数.GFT結果通知電文明細.処理区分＝01(口座開設)の場合かつ、
		//   引数.GFT結果通知電文明細.受付結果が"00000000"の場合のみ行う。
        //引数.GFT結果通知電文明細.為替保証金口座番号(1万通貨)、および 
        //引数.GFT結果通知電文明細.為替保証金口座番号(10万通貨)、が以下のいずれかに当てはまる場合、falseを返却する。 
        //・数字以外 
        //・10桁 より大きい 
        if (WEB3GftMessageOperationDef.ACCOUNT_OPEN.equals(l_fxGftResultNoticeTelegramUnit.gftOperationDiv)
            && l_fxGftResultNoticeTelegramUnit.resultCode.equals(WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000))
        {
            if (!WEB3StringTypeUtility.isDigit(l_fxGftResultNoticeTelegramUnit.gftAcc1)
                || l_fxGftResultNoticeTelegramUnit.gftAcc1.getBytes().length > 10
                || !WEB3StringTypeUtility.isDigit(l_fxGftResultNoticeTelegramUnit.gftAcc2)
                || l_fxGftResultNoticeTelegramUnit.gftAcc2.getBytes().length > 10)
            {   
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        //４）　@trueを返却する。 
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (isGFT電文送受信項目一致) <BR>
     * GFT結果通知電文明細の項目値が依頼電文の項目値と一致しているか判定する。 <BR>
     * 一致している場合、trueを、一致していない場合、falseを返却する。 <BR>
     * <BR>
     * １） 依頼電文内容の取得 <BR>
     * <BR>
     * FXデータ制御サービスImpl.getGFT電文保存()をコールし、GFT電文保存Paramsを取得する。<BR>
     * ※戻り値がnullの場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * [引数の設定] <BR>
     * 電文種別区分： 1(送信) <BR>
     * 処理区分： 引数.GFT結果通知電文明細.処理区分 <BR>
     * 証券会社コード： 引数.GFT結果通知電文明細.会社コード <BR>
     * 部店コード： 引数.GFT結果通知電文明細.部店コード <BR>
     * 識別コード： 引数.GFT結果通知電文明細.識別コード <BR>
     * <BR>
     * ２） 送受信項目一致チェック <BR>
     * <BR>
     * 以下のいずれかに当てはまる場合、falseを返却する。 <BR>
     * <BR>
     * ・GFT電文明細Params.DIR→GFT送信日時 != 引数.GFT結果通知電文明細.DIR→GFT送信日時 <BR>
     * ・GFT電文明細Params.処理区分 != 引数.GFT結果通知電文明細.処理区分 <BR>
     * ・GFT電文明細Params.為替保証金口座番号 != 引数.GFT結果通知電文明細.為替保証金口座番号 <BR>
     * ・GFT電文明細Params.メールアドレス != 引数.GFT結果通知電文明細.メールアドレス <BR>
     * ・GFT電文明細Params.初期ログインID != 引数.GFT結果通知電文明細.初期ログインID <BR>
     * ・GFT電文明細Params.初期パスワード != <BR>
     *     引数.GFT結果通知電文明細.初期パスワードをマスク処理したもの(*) <BR>
     * ・GFT電文明細Params.担当区分 != 引数.GFT結果通知電文明細.担当区分 <BR>
     * ・GFT電文明細Params.入出金額 != 引数.GFT結果通知電文明細.入出金額 (*2)<BR>
     * ・GFT電文明細Params.WOLFセッションキー != 引数.GFT結果通知電文明細.WOLFセッションキー <BR>
     * ・GFT電文明細Params.アプリケーションID != 引数.GFT結果通知電文明細.アプリケーションID <BR>
     * ・GFT電文明細Params.再生成サービスID != 引数.GFT結果通知電文明細.再生成サービスID <BR>
     * ・GFT電文明細Params.SSID != 引数.GFT結果通知電文明細.SSID <BR>
     * ・GFT電文明細Params.会社コード != 引数.GFT結果通知電文明細.会社コード <BR>
     * ・GFT電文明細Params.部店コード != 引数.GFT結果通知電文明細.部店コード <BR>
     * ・GFT電文明細Params.顧客コード != 引数.GFT結果通知電文明細.顧客コード <BR>
     * ・GFT電文明細Params.識別コード != 引数.GFT結果通知電文明細.識別コード <BR>
     * <BR>
     *  (*)this.maskパスワード()を使用<BR>
     *  (*2)GFT結果通知電文明細.処理区分が05：入金（先OP）または06：出金（先OP）の場合、<BR>
     *      入出金額+入出金額2で送信時の入出金額と比較する。<BR>
     * <BR>
     * ３） ハッシュ値一致チェック <BR>
     * <BR>
     * ３－１）ハッシュ値生成 <BR>
     * this.createGFT電文ハッシュ値()をコールし、ハッシュ値を生成する。 <BR>
     * <BR>
     * [引数の設定] <BR>
     * GFT結果通知電文明細： 引数.GFT結果通知電文明細 <BR>
     * <BR>
     * ３－２）以下に当てはまる場合、falseを返却する。 <BR>
     * <BR>
     * ３－１）にて生成したハッシュ値 != 引数.GFT結果通知電文明細.ハッシュ値 <BR>
     * <BR>
     * ４） trueを返却する。 <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41C6C39D0036
     */
    public boolean isGFTTelegramSendAndReceiveValueSame(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "isGFTTelegramSendAndReceiveValueSame(" +
            "WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)";
        log.entering(STR_METHOD_NAME);
        if (l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@依頼電文内容の取得 
        //  FXデータ制御サービスImpl.getGFT電文保存()をコールし、GFT電文保存Paramsを取得する。 
        //　@※戻り値がnullの場合、例外をthrowする。 
        //　@[引数の設定] 
        //　@電文種別区分：　@1(送信) 
        //　@処理区分：　@引数.GFT結果通知電文明細.処理区分 
        //　@証券会社コード：　@引数.GFT結果通知電文明細.会社コード 
        //　@部店コード：　@引数.GFT結果通知電文明細.部店コード 
        //　@識別コード：　@引数.GFT結果通知電文明細.識別コード
        WEB3FXDataControlService l_fXDataControlService =
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        GftMessageParams l_gftMessageParams =
            l_fXDataControlService.getGFTMessage(
                WEB3GftMessageDivDef.SEND,
                l_fxGftResultNoticeTelegramUnit.gftOperationDiv,
                l_fxGftResultNoticeTelegramUnit.institutionCode,
                l_fxGftResultNoticeTelegramUnit.branchCode,
                l_fxGftResultNoticeTelegramUnit.requestNumber);
        if (l_gftMessageParams == null)
        {
            log.debug("GFT電文保存取得エラー。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT電文保存取得エラー。");
        }
                
        //２）　@送受信項目一致チェック 
        //  以下のいずれかに当てはまる場合、falseを返却する。 
        //・GFT電文明細Params.DIR→GFT送信日時　@ != 引数.GFT結果通知電文明細.DIR→GFT送信日時 
        //・GFT電文明細Params.処理区分 　@!= 引数.GFT結果通知電文明細.処理区分 
        //・GFT電文明細Params.為替保証金口座番号 != 引数.GFT結果通知電文明細.為替保証金口座番号 
        //・GFT電文明細Params.メールアドレス 　@!= 引数.GFT結果通知電文明細.メールアドレス 
        //・GFT電文明細Params.初期ログインID 　@!= 引数.GFT結果通知電文明細.初期ログインID 
        //・GFT電文明細Params.初期パスワード 　@!= 引数.GFT結果通知電文明細.初期パスワード 
        //・GFT電文明細Params.担当区分 　@ != 引数.GFT結果通知電文明細.担当区分 
        //・GFT電文明細Params.入出金額 　@ != 引数.GFT結果通知電文明細.入出金額 
        //・GFT電文明細Params.WOLFセッションキー　@!= 引数.GFT結果通知電文明細.WOLFセッションキー 
        //・GFT電文明細Params.アプリケーションID  != 引数.GFT結果通知電文明細.アプリケーションID 
        //・GFT電文明細Params.再生成サービスID 　@!= 引数.GFT結果通知電文明細.再生成サービスID 
        //・GFT電文明細Params.SSID 　@　@　@!= 引数.GFT結果通知電文明細.SSID 
        //・GFT電文明細Params.会社コード != 引数.GFT結果通知電文明細.会社コード 
        //・GFT電文明細Params.部店コード != 引数.GFT結果通知電文明細.部店コード 
        //・GFT電文明細Params.顧客コード != 引数.GFT結果通知電文明細.顧客コード 
        //・GFT電文明細Params.識別コード != 引数.GFT結果通知電文明細.識別コード 

        // GFT電文明細Params.メールアドレス == 引数.GFT結果通知電文明細.メールアドレス --> true
        boolean l_blnIsMailAddressSame = false;
        if (l_fxGftResultNoticeTelegramUnit.fxMailAddress == null)
        {
            if (l_gftMessageParams.getEmail() == null)
            {
                l_blnIsMailAddressSame = true;
            }
        }
        else
        {
            l_blnIsMailAddressSame = 
                l_fxGftResultNoticeTelegramUnit.fxMailAddress.equals(l_gftMessageParams.getEmail());
        }

        //GFT電文明細Params.初期パスワード == 引数.GFT結果通知電文明細.初期パスワード --> true
        boolean l_blnIsFirstPasswordSame =  false;
        if (l_fxGftResultNoticeTelegramUnit.fxFirstPassword == null)
        {
            if (l_gftMessageParams.getGftLink2() == null)
            {
                l_blnIsFirstPasswordSame = true;
            }
        }
        else
        {
            String l_fxFirstPasswordMasked = 
                this.maskPassword(l_fxGftResultNoticeTelegramUnit.fxFirstPassword);
            l_blnIsFirstPasswordSame = 
                l_fxFirstPasswordMasked.equals(l_gftMessageParams.getGftLink2());
        }
        
        //GFT電文明細Params.為替保証金口座番号 != 引数.GFT結果通知電文明細.為替保証金口座番号-->true
        boolean l_blnIsAccountCodeSame = false;
        if (l_fxGftResultNoticeTelegramUnit.fxAccountCode == null)
        {
            if (l_gftMessageParams.getAccount() == null)
            {
                l_blnIsAccountCodeSame = true;
            }
        }
        else
        {
            l_blnIsAccountCodeSame = 
                l_fxGftResultNoticeTelegramUnit.fxAccountCode.equals(l_gftMessageParams.getAccount());
        }
        
        //GFT電文明細Params.入出金額 　@ != 引数.GFT結果通知電文明細.入出金額-->true
        boolean l_blnIscashinoutAmtSame = false;

        //GFT結果通知電文明細.処理区分が05：入金（先OP）または06：出金（先OP）の場合、入出金額+入出金額2で送信時の入出金額と比較する。
        if (WEB3GftMessageOperationDef.CASH_IN_FUOP.equals(l_gftMessageParams.getOperation())
                || WEB3GftMessageOperationDef.CASH_OUT_FUOP.equals(l_gftMessageParams.getOperation())) {

            if (l_gftMessageParams.getAmount() == null && l_fxGftResultNoticeTelegramUnit.cashinoutAmt == null
                    && l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 == null) {
                l_blnIscashinoutAmtSame = true;
            } else {

                BigDecimal l_bdAmount1 = null;
                BigDecimal l_bdAmount2 = null;

                //金額１のチェック
                if (WEB3StringTypeUtility.isEmptyOrBlank(l_fxGftResultNoticeTelegramUnit.cashinoutAmt)) {
                    l_bdAmount1 = new BigDecimal("0");
                } else {
                    l_bdAmount1 = new BigDecimal(l_fxGftResultNoticeTelegramUnit.cashinoutAmt);
                }

               //金額２のチェック
                if (WEB3StringTypeUtility.isEmptyOrBlank(l_fxGftResultNoticeTelegramUnit.cashinoutAmt2)) {
                    l_bdAmount2 = new BigDecimal("0");
                } else {
                    l_bdAmount2 = new BigDecimal(l_fxGftResultNoticeTelegramUnit.cashinoutAmt2);
                }

                //金額１＋金額２
                BigDecimal l_bdAmount12 = l_bdAmount1.add(l_bdAmount2);

                l_blnIscashinoutAmtSame = l_bdAmount12.toString().equals(l_gftMessageParams.getAmount());
            }

        }

        else if (l_fxGftResultNoticeTelegramUnit.cashinoutAmt == null)
        {
            if (l_gftMessageParams.getAmount() == null)
            {
                l_blnIscashinoutAmtSame = true;
            }
        }
        else
        {
            l_blnIscashinoutAmtSame = 
                l_fxGftResultNoticeTelegramUnit.cashinoutAmt.equals(l_gftMessageParams.getAmount());   
        }

        Date l_datDirSendTime = 
            WEB3DateUtility.getDate(l_gftMessageParams.getDirSendTime(), "yyyyMMddHHmmss");
        Date l_datGFTDirSendTime =
            WEB3DateUtility.getDate(l_fxGftResultNoticeTelegramUnit.dirSendTime, "yyyyMMddHHmmss");
        
        //GFT電文明細Params.DIR→GFT送信日時　@ != 引数.GFT結果通知電文明細.DIR→GFT送信日時 
        boolean l_blnIsDirSendTimeDifferent = 
            WEB3DateUtility.compareToSecond(l_datDirSendTime, l_datGFTDirSendTime) != 0;  
        //GFT電文明細Params.処理区分 　@!= 引数.GFT結果通知電文明細.処理区分    
        boolean l_blnIsOperationDivSame = 
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv.equals(l_gftMessageParams.getOperation());
        //GFT電文明細Params.初期ログインID 　@!= 引数.GFT結果通知電文明細.初期ログインID     
        boolean l_blnIsFirstLoginIdSame = 
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId.equals(l_gftMessageParams.getGftLink1());
        //GFT電文明細Params.担当区分 　@ != 引数.GFT結果通知電文明細.担当区分    
        boolean l_blnIsGroupNameSame = 
            l_fxGftResultNoticeTelegramUnit.groupName.equals(l_gftMessageParams.getGroupName());
        //GFT電文明細Params.WOLFセッションキー　@!= 引数.GFT結果通知電文明細.WOLFセッションキー 
        boolean l_blnIsWolfSessionKeySame = 
            l_fxGftResultNoticeTelegramUnit.wolfSession.equals(l_gftMessageParams.getWolfSessionKey());
        //GFT電文明細Params.アプリケーションID  != 引数.GFT結果通知電文明細.アプリケーションID     
        boolean l_blnIsAidSame = 
            l_fxGftResultNoticeTelegramUnit.wolfAid.equals(l_gftMessageParams.getAaAid());
        //GFT電文明細Params.再生成サービスID 　@!= 引数.GFT結果通知電文明細.再生成サービスID     
        boolean l_blnIsAaRsidSame = 
            l_fxGftResultNoticeTelegramUnit.regetServiceId.equals(l_gftMessageParams.getAaRsid());
        //GFT電文明細Params.SSID 　@　@　@!= 引数.GFT結果通知電文明細.SSID    
        boolean l_blnIsSsidSame = 
            l_fxGftResultNoticeTelegramUnit.wolfSsid.equals(l_gftMessageParams.getSsid());
        //GFT電文明細Params.会社コード != 引数.GFT結果通知電文明細.会社コード     
        boolean l_blnIsCpySame = 
            l_fxGftResultNoticeTelegramUnit.institutionCode.equals(l_gftMessageParams.getCpy());
        //GFT電文明細Params.部店コード != 引数.GFT結果通知電文明細.部店コード     
        boolean l_blnIsBrnSame = 
            l_fxGftResultNoticeTelegramUnit.branchCode.equals(l_gftMessageParams.getBrn());
        //GFT電文明細Params.顧客コード != 引数.GFT結果通知電文明細.顧客コード    
        boolean l_blnIsAccSame = 
            l_fxGftResultNoticeTelegramUnit.accountCode.equals(l_gftMessageParams.getAcc());
        //GFT電文明細Params.識別コード != 引数.GFT結果通知電文明細.識別コード    
        boolean l_blnIsReqSame = 
            l_fxGftResultNoticeTelegramUnit.requestNumber.equals(l_gftMessageParams.getReq());
        if (l_blnIsDirSendTimeDifferent
            || !l_blnIsOperationDivSame
            || !l_blnIsAccountCodeSame
            || !l_blnIsMailAddressSame
            || !l_blnIsFirstLoginIdSame
            || !l_blnIsFirstPasswordSame
            || !l_blnIsGroupNameSame
            || !l_blnIscashinoutAmtSame
            || !l_blnIsWolfSessionKeySame
            || !l_blnIsAidSame
            || !l_blnIsAaRsidSame
            || !l_blnIsSsidSame
            || !l_blnIsCpySame
            || !l_blnIsBrnSame
            || !l_blnIsAccSame
            || !l_blnIsReqSame)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //３）　@ハッシュ値一致チェック 
        // ３－１）ハッシュ値生成 
        //  this.createGFT電文ハッシュ値()をコールし、ハッシュ値を生成する。 
        //  [引数の設定] 
        //  GFT結果通知電文明細：　@引数.GFT結果通知電文明細 
        String l_strGFTTelegramHashValue = 
            this.createGFTTelegramHashValue(l_fxGftResultNoticeTelegramUnit);
        
        // ３－２）以下に当てはまる場合、falseを返却する。 
        //   ３－１）にて生成したハッシュ値 != 引数.GFT結果通知電文明細.ハッシュ値 
        if (!l_fxGftResultNoticeTelegramUnit.hashValue.equals(l_strGFTTelegramHashValue))
        {   
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //４）　@trueを返却する。
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (maskパスワード) <BR>
     * パスワードにマスクをかける。 <BR>
     * <BR>
     * １）空の文字列（＝Aとする。）を生成する。<BR>
     * ２）以下のとおりに、引数.パスワードの配列を置き換えて、<BR>
     *      １）の文字列（＝A）にセットする。<BR>
     * <BR>
     *   A[0] = 引数.パスワード[1] 
     *   A[1] = 引数.パスワード[8] 
     *   A[2] = 引数.パスワード[4]
     *   A[3] = 引数.パスワード[9] 
     *   A[4] = 引数.パスワード[7] 
     *   A[5] = 引数.パスワード[10] 
     *   A[6] = 引数.パスワード[6] 
     *   A[7] = 引数.パスワード[11] 
     *   A[8] = 引数.パスワード[3] 
     *   A[9] = 引数.パスワード[0] 
     *   A[10] = 引数.パスワード[2] 
     *   A[11] = 引数.パスワード[5] 
     * <BR>
     * ２）Aの文字列を返却する。<BR>
     * 
     * @@param l_strMaskPassword  String
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41C6C39D0027
     */
    public String maskPassword(String l_strMaskPassword)
       throws WEB3BaseException
    {
       final String STR_METHOD_NAME = "maskPassword(String[] l_strMaskPassword) ";
       log.entering(STR_METHOD_NAME);
        
       if (l_strMaskPassword == null)
       {
           log.exiting(STR_METHOD_NAME);
           return null;
       }

       String l_strPasswordModified = l_strMaskPassword;
       if (l_strMaskPassword.length() < 12)
       {
           for (int i = 12; i > l_strMaskPassword.length(); i --)
           {
               l_strPasswordModified = l_strPasswordModified + " ";
           }
       }
       
       //１）空の文字列（＝Aとする。）を生成する。
       StringBuffer A = new StringBuffer();
        
       //２）以下のとおりに、引数.パスワードの配列を置き換えて、
       //１）の文字列（＝A）にセットする。
       //A[0] = 引数.パスワード[1] 
       //A[1] = 引数.パスワード[8] 
       //A[2] = 引数.パスワード[4]
       //A[3] = 引数.パスワード[9] 
       //A[4] = 引数.パスワード[7] 
       //A[5] = 引数.パスワード[10] 
       //A[6] = 引数.パスワード[6] 
       //A[7] = 引数.パスワード[11] 
       //A[8] = 引数.パスワード[3] 
       //A[9] = 引数.パスワード[0] 
       //A[10] = 引数.パスワード[2] 
       //A[11] = 引数.パスワード[5] 
       A.append(l_strPasswordModified.charAt(1));
       A.append(l_strPasswordModified.charAt(8));
       A.append(l_strPasswordModified.charAt(4));
       A.append(l_strPasswordModified.charAt(9));
       A.append(l_strPasswordModified.charAt(7));
       A.append(l_strPasswordModified.charAt(10));
       A.append(l_strPasswordModified.charAt(6));
       A.append(l_strPasswordModified.charAt(11));
       A.append(l_strPasswordModified.charAt(3));
       A.append(l_strPasswordModified.charAt(0));
       A.append(l_strPasswordModified.charAt(2));
       A.append(l_strPasswordModified.charAt(5));
       
       if (l_strMaskPassword.length() > 12)
       {
           A.append(l_strMaskPassword.substring(12, l_strMaskPassword.length()));
       }
       log.exiting(STR_METHOD_NAME);
       return A.toString();
    }
     
    
}@
