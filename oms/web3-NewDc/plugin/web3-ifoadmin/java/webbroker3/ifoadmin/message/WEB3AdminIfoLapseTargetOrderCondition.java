head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoLapseTargetOrderCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP失効対象注文条件(WEB3AdminIfoLapseTargetOrderCondition.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/

package webbroker3.ifoadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (先物OP失効対象注文条件)<BR>
 * 先物OP失効対象注文条件<BR>
 * <BR>
 * @@author 謝旋(中訊)
 * @@version 1.0
 */

public class WEB3AdminIfoLapseTargetOrderCondition extends Message 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoLapseTargetOrderCondition.class);
    
    /**
     * (部店コード)<BR>
     * 部店コードの配列 <BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している <BR>
     * 　@取扱可能部店コード一覧がセットされる。<BR>
     */
    public String[] branchCode;

    /**
     * (先物／オプション区分)<BR>
     * 先物／オプション区分<BR>
     * <BR>
     * 1：　@先物<BR>
     * 2：　@オプション<BR>
     * ※銘柄個別指定の場合のみ入力<BR>
     */
    public String fuOpDiv = null;

    /**
     * (指数種別)<BR>
     * 指数種別<BR>
     * 0005：　@TOPIX<BR>
     * 0018：　@日経225<BR>
     * 0016：　@日経300<BR>
     * 0019：　@ミニ日経225<BR>
     * ※銘柄個別指定の場合のみ入力<BR>
     */
    public String targetProductCode = null;

    /**
     * (限月)<BR>
     * yyyyMM形式<BR>
     * ※銘柄個別指定の場合のみ入力<BR>
     */
    public String delivaryMonth = null;

    /**
     * (行使価格)<BR>
     * ※銘柄個別指定の場合のみ入力<BR>
     */
    public String strikePrice = null;

    /**
     * (オプション商品区分)<BR>
     * P：　@プット<BR>
     * C：　@コール<BR>
     * ※銘柄個別指定の場合のみ入力<BR>
     */
    public String opProductType = null;

    /**
     * (取引区分一覧)<BR>
     * 取引区分の一覧<BR>
     * <BR>
     *601：　@先物新規買建注文<BR>
     *602：　@先物新規売建注文<BR>
     *603：　@先物売建買返済注文<BR>
     *604：　@先物買建売返済注文<BR>
     *605：　@OP新規買建注文<BR>
     *606：　@OP新規売建注文<BR>
     *607：　@OP売建買返済注文<BR>
     *608：　@OP買建売返済注文<BR>
     */
    public String[] tradingTypeList;

    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode = null;

    /**
     * (先物OP失効対象注文条件)<BR>
     * コンストラクタ
     * @@return WEB3AdminIfoLapseTargetOrderCondition
     * @@roseuid 4469235C0148
     */
    public WEB3AdminIfoLapseTargetOrderCondition() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     *１）　@部店コードチェック<BR>
     *１－１）　@this.部店コード == nullの場合、<BR>
     *　@　@　@「部店コードがnull」の例外をスローする。<BR>
     *<BR>
     *１－２）　@this.部店コードの要素数分以下の処理を行う。<BR>
     *    １－２－１）　@this.部店コードが以下の条件に該当する場合、<BR>
     *　@　@　@　@　@「部店コードエラー」の例外をスローする。<BR>
     *　@　@　@　@　@・部店コード != 数字 <BR>
     *　@　@　@　@　@・部店コード.length != 3 <BR>
     *<BR>
     *２）　@先物／オプション区分チェック<BR>
     *   this.先物／オプション区分 != nullの場合、以下のチェックを行う。<BR>
     *   ２－１）　@this.先物／オプション区分に下記の項目以外が設定されていたら、<BR>
     *   「先物／オプション区分の値が存在しないコード値です」の例外をスローする。<BR>
     *   ・"先物" <BR>
     *   ・"オプション" <BR>
     *<BR>
     *３）　@指数種別チェック<BR>
     *   this.指数種別 != nullの場合、以下のチェックを行う。<BR>
     *   ３－１）　@this.指数種別 != 数字の場合、  <BR>
     *       「指数種別が数字以外」の例外をスローする。<BR>
     *<BR>
     *   ３－２）　@this.指数種別 != 4桁の場合、<BR>
     *     「指数種別桁数エラー」の例外をスローする。<BR>
     *<BR>
     *４）　@限月チェック<BR>
     *   this.限月 != nullの場合、以下のチェックを行う。<BR>
     *   ４－１）　@this.限月 != 数字の場合、<BR>
     *       「限月が数字以外」の例外をスローする。<BR>
     *<BR>
     *   ４－２）　@this.限月 != YYYYMM形式の値であった場合、<BR>
     *       「限月日付形式エラー」の例外をスローする。<BR>
     *<BR>
     *５）　@行使価格チェック<BR>
     *   this.行使価格 != nullの場合、以下のチェックを行う。<BR>
     *   ５－１）　@this.行使価格 != 数字の場合、 <BR>
     *       「行使価格が数字以外」の例外をスローする。<BR>
     *<BR>
     *   ５－２）　@this.行使価格 <= 0 の場合、<BR>
     *       「行使価格が0以下」の例外をスローする。<BR>
     *<BR>
     *   ５－３）　@this.行使価格 > 8桁の値であった場合、<BR>
     *       「行使価格桁数エラー」の例外をスローする。<BR>
     *<BR>
     *６）　@オプション商品区分チェック<BR>
     *   this.オプション商品区分 != nullの場合、以下のチェックを行う。<BR>
     *   ６－１）　@this.オプション商品区分に下記の項目以外が設定されていたら、<BR>
     *       「オプション商品区分が未定義の値」の例外をスローする。<BR>
     *       ・"プット"<BR>
     *       ・"コール"<BR>
     *<BR>
     *７）　@銘柄指定チェック<BR>
     *   ７－１）　@銘柄条件が入力されている場合、<BR>
     *       (this.先物／オプション区分 != null or<BR>
     *       this.指数種別 != null or<BR>
     *       this.限月 != null or<BR>
     *       this.行使価格 != null or<BR>
     *       this.オプション商品区分 != null)<BR>
     *   以下の条件のいずれにも該当しなければ、「銘柄指定エラー」の例外をスローする。<BR>
     *<BR>
     *   ①@銘柄検索条件（先物）<BR>
     *       this.先物／オプション区分 == "先物" かつ<BR>
     *       this.指数種別 != null かつ<BR>
     *       this.限月 != null かつ<BR>
     *       this.行使価格 == null かつ <BR>
     *       this.オプション商品区分 == null<BR>
     *<BR>
     *   ②銘柄検索条件（オプション）<BR>
     *       this.先物／オプション区分 == "オプション" かつ<BR>
     *       this.指数種別 != null かつ<BR>
     *       this.限月 != null かつ<BR>
     *       this.行使価格 != null かつ<BR>
     *       this.オプション商品区分 != null<BR>
     *<BR>
     * ８）　@取引区分一覧チェック<BR>
     *   ８－１）　@this.取引区分一覧 == nullであれば、<BR>
     *        「取引区分が未指定です。」の例外をスローする。<BR>
     *<BR>
     *   ８－２）　@this.取引区分一覧に下記の項目以外が設定 <BR>
     *       されていたら、「取引区分が存在しないコード値です。」の <BR>
     *       例外をスローする。<BR>
     *       ・"先物新規買建注文"<BR>
     *       ・"先物新規売建注文" <BR>
     *       ・"先物売建買返済注文" <BR>
     *       ・"先物買建売返済注文" <BR>
     *       ・"OP新規買建注文" <BR>
     *       ・"OP新規売建注文" <BR>
     *       ・"OP売建買返済注文" <BR>
     *       ・"OP買建売返済注文" <BR>
     *<BR>
     *９）　@顧客コードチェック<BR>
     *   this.顧客コード != nullの場合、以下のチェックを行う。<BR>
     *   ９－１）　@this.顧客コードが以下の条件に該当する場合、<BR>
     *       「顧客コードエラー」の例外をスローする。<BR>
     *       ・顧客コード != 数字 <BR>
     *        ・顧客コード.length != 6<BR>
     * <BR>
     * (*1)現物株式の取引区分<BR>
     * 　@　@・"現物買付注文"<BR>
     * 　@　@・"現物売付注文"<BR>
     * <BR>
     * (*2)信用取引の取引区分<BR>
     * 　@　@・"新規買建注文"<BR>
     * 　@　@・"新規売建注文"<BR>
     * 　@　@・"買建返済注文"<BR>
     * 　@　@・"売建返済注文"<BR>
     * 　@　@・"現引注文"<BR>
     * 　@　@・"現渡注文"<BR>
     *<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4469235C0167
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@部店コードチェック  
        //　@１－１）　@this.部店コード == nullの場合、  
        //　@　@　@　@　@「部店コードがnull」の例外をスローする。  
        if (this.branchCode == null || this.branchCode.length == 0) 
        {
            log.debug("部店コードがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードがnullです。");
        }

        //　@１－２）　@this.部店コードの要素数分以下の処理を行う。
        //　@　@１－２－１）　@this.部店コードが以下の条件に該当する場合、
        //　@　@　@　@　@　@　@「部店コードエラー」の例外をスローする。
        //　@　@　@　@　@　@　@・部店コード != 数字
        //　@　@　@　@　@　@　@・部店コード.length != 3
        for (int i = 0; i < this.branchCode.length; i++)
        {
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i])
                || this.branchCode[i].length() != 3)
            {
                log.debug("部店コードの入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードの入力が不正です。");
            }
        }

        // ２）　@先物／オプション区分チェック
        //　@this.先物／オプション区分 != nullの場合、以下のチェックを行う。
        //　@２－１）　@this.先物／オプション区分に下記の項目以外が設定されていたら、
        //　@　@「先物／オプション区分の値が存在しないコード値です」の例外をスローする。
        //　@　@　@・"先物"
        //　@　@　@・"オプション"
        if (this.fuOpDiv != null)
        {
            if (!(WEB3FuturesOptionDivDef.FUTURES.equals(this.fuOpDiv)
                || WEB3FuturesOptionDivDef.OPTION.equals(this.fuOpDiv)))
            {
                log.debug("先物／オプション区分が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01737,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "先物／オプション区分が存在しないコード値です。");
            }
        }

        // ３）　@指数種別チェック
        //　@this.指数種別 != nullの場合、以下のチェックを行う。
        //　@３－１）　@this.指数種別 != 数字の場合、
        //　@　@「指数種別が数字以外」の例外をスローする。
        //　@３－２）　@this.指数種別 != 4桁の場合、
        //　@　@「指数種別桁数エラー」の例外をスローする。
        if (this.targetProductCode != null)
        {
            if (!WEB3StringTypeUtility.isDigit(this.targetProductCode))
            {
                log.debug("指数種別が数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02441,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指数種別が数字以外の値です。");
            }
            
            if (this.targetProductCode.length() != 4)
            {
                log.debug("指数種別のサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02442,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指数種別のサイズが不正です。");
            }
        }

        // ４）　@限月チェック
        //　@this.限月 != nullの場合、以下のチェックを行う。
        //　@４－１）　@this.限月 != 数字の場合、
        //　@　@「限月が数字以外」の例外をスローする。
        //　@４－２）　@this.限月 != yyyyMM形式の値であった場合、
        //　@「限月日付形式エラー」の例外をスローする。
        if (this.delivaryMonth != null)
        {
            if (!WEB3StringTypeUtility.isDigit(this.delivaryMonth))
            {
                log.debug("限月が数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02351,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "限月が数字以外の値です。");
            }

            if (!WEB3StringTypeUtility.isDateStr(this.delivaryMonth, "yyyyMM"))
            {
                log.debug("限月がＹＹＹＹＭＭ形式で入力してください。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00268,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "限月がＹＹＹＹＭＭ形式で入力してください。");
            }
        }

        // ５）　@行使価格チェック
        //　@this.行使価格 != nullの場合、以下のチェックを行う。
        //　@５－１）　@this.行使価格 != 数字の場合、
        //　@　@「行使価格が数字以外」の例外をスローする。
        //　@５－２）　@this.行使価格 <= 0 の場合、
        //　@　@「行使価格が0以下」の例外をスローする。
        //　@５－３）　@this.行使価格 > 8桁の値であった場合、
        //　@　@「行使価格桁数エラー」の例外をスローする。
        if (this.strikePrice != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.strikePrice))
            {
                log.debug("行使価格が数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00272,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "行使価格が数字以外の値です。");
            }

            if (Double.parseDouble(this.strikePrice) <= 0)
            {
                log.debug("行使価格が0以下の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00273,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "行使価格が0以下の値です。");
            }

            if (this.strikePrice.length() > 8)
            {
                log.debug("行使価格のサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00274,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "行使価格のサイズが不正です。");
            }
        }

        // ６）　@オプション商品区分チェック
        //　@this.オプション商品区分 != nullの場合、以下のチェックを行う。
        //　@６－１）　@this.オプション商品区分に下記の項目以外が設定されていたら、
        //　@　@「オプション商品区分が未定義の値」の例外をスローする。
        //　@　@　@・"プット"
        //　@　@　@・"コール"
        if (this.opProductType != null)
        {
            if (!(WEB3IfoProductTypeDef.PUT_OPTIONS.equals(this.opProductType)
                || WEB3IfoProductTypeDef.CALL_OPTIONS.equals(this.opProductType)))
            {
                log.debug("オプション商品区分の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00270,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "オプション商品区分の値が存在しないコード値です。");
            }
        }

        //７）　@銘柄指定チェック
        //　@７－１）　@銘柄条件が入力されている場合、
        //　@　@(this.先物／オプション区分 != null or
        //　@　@　@this.指数種別 != null or
        //　@　@　@this.限月 != null or
        //　@　@　@this.行使価格 != null or
        //　@　@　@this.オプション商品区分 != null)
        //　@　@以下の条件のいずれにも該当しなければ、「銘柄指定エラー」の例外をスローする。
        //　@　@①@銘柄検索条件（先物）
        //　@　@　@this.先物／オプション区分 == "先物" かつ
        //　@　@　@this.指数種別 != null かつ
        //　@　@　@this.限月 != null かつ
        //　@　@　@this.行使価格 == null かつ
        //　@　@　@this.オプション商品区分 == null
        //　@　@②銘柄検索条件（オプション）
        //　@　@　@this.先物／オプション区分 == "オプション" かつ
        //　@　@　@this.指数種別 != null かつ
        //　@　@　@this.限月 != null かつ
        //　@　@　@this.行使価格 != null かつ
        //　@　@　@this.オプション商品区分 != null
        if (this.fuOpDiv != null
            || this.targetProductCode != null
            || this.delivaryMonth != null
            || this.strikePrice != null
            || this.opProductType != null)
        {
            boolean l_blnIfoFlag = false;
            boolean l_blnOpFlag = false;
            if (WEB3FuturesOptionDivDef.FUTURES.equals(this.fuOpDiv)
                && this.targetProductCode != null
                && this.delivaryMonth != null
                && this.strikePrice == null
                && this.opProductType == null)
            {
                l_blnIfoFlag = true;
            }
            
            if (WEB3FuturesOptionDivDef.OPTION.equals(this.fuOpDiv)
                && this.targetProductCode != null
                && this.delivaryMonth != null
                && this.strikePrice != null
                && this.opProductType != null)
            {
                l_blnOpFlag = true;
            }
            
            if (!(l_blnIfoFlag || l_blnOpFlag))
            {
                log.debug("銘柄指定エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00334,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄指定エラー。");
            }
        }

        //8）　@取引区分一覧チェック
        //　@8－１）　@this.取引区分一覧 == nullであれば、
        //　@　@「取引区分が未指定です。」の例外をスローする。
        if (this.tradingTypeList == null || this.tradingTypeList.length == 0)
        {
            log.debug("取引区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00601,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引区分が未指定です。");
        }

        //　@8－２）　@this.取引区分一覧に下記の項目以外が設定
        //　@　@されていたら、「取引区分が存在しないコード値です。」の
        //　@　@例外をスローする。
        //    601：　@先物新規買建注文
        //    602：　@先物新規売建注文
        //    603：　@先物売建買返済注文
        //    604：　@先物買建売返済注文
        //    605：　@OP新規買建注文
        //    606：　@OP新規売建注文
        //    607：　@OP売建買返済注文
        //    608：　@OP買建売返済注文
        for (int i = 0; i < this.tradingTypeList.length; i++)
        {
            if (!(String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.intValue()).equals(this.tradingTypeList[i])))
            {
                log.debug("取引区分が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00602,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引区分が存在しないコード値です。");
            }

        }
         
        //9）　@顧客コードチェック 
        //　@this.顧客コード != nullの場合、以下のチェックを行う。 
        //　@9－１）　@this.顧客コードが以下の条件に該当する場合、  
        //　@　@　@　@「顧客コードエラー」の例外をスローする。  
        //　@　@　@　@・顧客コード != 数字  
        //　@　@　@　@・顧客コード.length != 6  
        if (this.accountCode != null) 
        {
            if (!WEB3StringTypeUtility.isDigit(this.accountCode)
                || this.accountCode.length() != 6)
            {
                log.debug("顧客コードの入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードの入力が不正です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
