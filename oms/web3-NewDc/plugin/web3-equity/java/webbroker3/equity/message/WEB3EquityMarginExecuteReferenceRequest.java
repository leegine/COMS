head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMarginExecuteReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式・信用注文約定照会リクエスト(WEB3EquityMarginExecuteReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 関博(中訊) 新規作成
Revesion History : 2007/10/16 金傑(中訊) 仕様変更モデル1200
Revesion History : 2007/12/17 金傑(中訊) 仕様変更モデル1232
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityExecStatusTypeDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityProductDivDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株式・信用注文約定照会リクエスト)<BR>
 * 株式・信用注文約定照会リクエストクラス<BR>
 * @@author  関博
 * @@version 1.0
 */
public class WEB3EquityMarginExecuteReferenceRequest extends WEB3GenRequest
{

    /**
     * ログ出力。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_margin_execute_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701101127L;

    /**
     * (照会区分)<BR>
     * 0： 注文約定照会<BR>
     * 1： 訂正取消一覧<BR>
     * <BR>
     * ※ null 不可<BR>
     */
    public String referenceType;

    /**
     * (商品区分)<BR>
     * 0： 現物株式、信用取引 すべて<BR>
     * 1： 現物株式<BR>
     * 2： 信用取引<BR>
     * <BR>
     * ※ null 不可<BR>
     */
    public String productDiv;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     * <BR>
     * ※ null の場合、指定なし<BR>
     */
    public String productCode;

    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     * <BR>
     * ※ null の場合、指定なし<BR>
     */
    public String marketCode;

    /**
     * (約定状態区分)<BR>
     * 0： 未約定<BR>
     * 1： 一部成立<BR>
     * 2： 全部成立<BR>
     * null： 指定なし<BR>
     */
    public String execType;

    /**
     * (発注日)<BR>
     * 発注日<BR>
     * <BR>
     * ※ null の場合、指定なし<BR>
     */
    public Date orderBizDate;

    /**
     * (発注条件区分)<BR>
     * 発注条件区分<BR>
     * <BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String orderCondType;

    /**
     * (ソートキー)<BR>
     * 株式・信用取引ソートキー<BR>
     * <BR>
     * 対象項目：
     * 銘柄コード、口座区分、市場コード、取引区分、値段条件、執行<BR>
     * 条件、発注条件、注文時間、発注日、注文期限、弁済区分、弁済期限値<BR>
     */
    public WEB3EquityMarginSortKey[] sortKeys;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     * <BR>
     * 表示させたいページ位置を指定<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     * <BR>
     * １ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;

    /**
     * @@roseuid 45A33C7A032C
     */
    public WEB3EquityMarginExecuteReferenceRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）照会区分チェック<BR>
     * <BR>
     * １－１）<BR>
     *     this.照会区分 ＝ null の場合、「照会区分がnull」の例外をスローする。<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00081<BR>
     * １－２）<BR>
     * <BR>
     * this.照会区分が下記の値以外が設定されている場合、「照会区分が<BR>
     * 未定義の値」の例外をスローする。<BR>
     * <BR>
     *     ・”注文約定照会”<BR>
     *     ・”訂正取消一覧”<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00082<BR>
     * ２）商品区分チェック<BR>
     * <BR>
     * ２－１）<BR>
     *     this.商品区分 ＝ null の場合、「商品区分がnull」の例外をスローする。<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02182<BR>
     * ２－２）<BR>
     * <BR>
     * this.商品区分が下記の値以外が設定されている場合、「商品区分が<BR>
     * 未定義の値」の例外をスローする。<BR>
     * <BR>
     *     ・”現物株式、信用取引 すべて”<BR>
     *     ・”現物株式”<BR>
     *     ・”信用取引”<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01068<BR>
     * ３）約定状態区分チェック<BR>
     * <BR>
     * this.約定状態区分 ≠ null and<BR>
     * this.約定状態区分が下記の値以外が設定されている場合、<BR>
     * 「約定状態区分が未定義の値」の例外をスローする。<BR>
     * <BR>
     *     ・”未約定”<BR>
     *     ・”一部成立”<BR>
     *     ・”全部成立”<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00626<BR>
     * ４）ソートキーチェック<BR>
     * <BR>
     * ４－１）<BR>
     *     this.ソートキー ＝ null の場合、「ソートキーがnull」<BR>
     *     の例外をスローする。<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00231<BR>
     * ４－２）<BR>
     *     this.ソートキー.要素数 ＝ 0<BR>
     * の場合、「ソートキー.要素数が0」の例外をスローする。<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00232<BR>
     * ４－３）<BR>
     *     this.ソートキーの全要素に対して、下記のチェックを行う。<BR>
     * <BR>
     * ４－３－１）<BR>
     *     ソートキー.validate()をコールする。<BR>
     * <BR>
     * ４－３－２）<BR>
     *     ソートキー.キー項目に下記の項目以外が設定されている場合、<BR>
     *     「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
     * <BR>
     *     ・”銘柄コード”<BR>
     *     ・”口座区分”<BR>
     *     ・”市場コード”<BR>
     *     ・”取引区分”<BR>
     *     ・”値段条件”<BR>
     *     ・”執行条件”<BR>
     *     ・”発注条件”<BR>
     *     ・”注文時間”<BR>
     *     ・”発注日”<BR>
     *     ・”注文期限”<BR>
     *     ・”弁済区分”<BR>
     *     ・”弁済期限値”<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00086<BR>
     * ５）要求ページ番号チェック<BR>
     * <BR>
     * ５－１）<BR>
     *     this.要求ページ番号 ＝ null<BR>
     * の場合、「要求ページ番号がnull」の例外をスローする。<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00089<BR>
     * ５－２）<BR>
     * <BR>
     * this.要求ページ番号が数字以外の値の場合、「要求ページ番号が数字<BR>
     * 以外」の例外をスローする。<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00090<BR>
     * ５－３）<BR>
     *     this.要求ページ番号 ≦ 0<BR>
     * の場合、「要求ページ番号が0以下」の例外をスローする。<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00616<BR>
     * ６）ページ内表示行数チェック<BR>
     * <BR>
     * ６－１）<BR>
     *     this.ページ内表示行数 ＝ null<BR>
     * の場合、「ページ内表示行数がnull」の例外をスローする。<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00091<BR>
     * ６－２）<BR>
     * <BR>
     * this.ページ内表示行数が数字以外の値の場合、「ページ内表示行数が数<BR>
     * 字以外」の例外をスローする。<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00092<BR>
     * ６－３）<BR>
     *     this.ページ内表示行数 ≦ 0<BR>
     * の場合、「ページ内表示行数が0以下」の例外をスローする。<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00617<BR>
     * ７）市場コードチェック<BR>
     * <BR>
     * this.市場コード ≠ null and<BR>
     * 下記の値以外の場合、「市場コードが未定義の値」の例外をスローする。<BR>
     * <BR>
     *     ・”東京”<BR>
     *     ・”大阪”<BR>
     *     ・”名古屋”<BR>
     *     ・”福岡”<BR>
     *     ・”札幌”<BR>
     *     ・”NNM”<BR>
     *     ・”JASDAQ”<BR>
     *     ・”JNX-PTS”<BR>
     * <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     * ８）発注条件区分 ≠ null and 下記の値以外の場合、<BR>
     * 「発注条件区分が未定義の値」の例外をスローする。<BR>
     * <BR>
     * 　@・”指定なし” <BR>
     * 　@・”逆指値”<BR>
     * 　@・”W指値”<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00212<BR>
     * <BR>
     * @@throws webbroker3.equity.common.WEB3BaseException
     * @@roseuid 455C352701B0
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " .validate()";
        log.entering(STR_METHOD_NAME);

        //１）照会区分チェック
        //１－１）
        //    this.照会区分 ＝ null の場合、「照会区分がnull」の例外をスローする。
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00081
        if (WEB3StringTypeUtility.isEmpty(this.referenceType))
        {
            log.debug("照会区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + STR_METHOD_NAME,
                "照会区分 = " + this.referenceType);
        }

        //１－２）
        //this.照会区分が下記の値以外が設定されている場合、「照会区分が未定義の値」の例外をスローする。
        //・”注文約定照会”
        //・”訂正取消一覧”
        //class: WEB3BusinessLayerException
        // 　@　@tag:   BUSINESS_ERROR_00082
        if (!WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(this.referenceType) &&
            !WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(this.referenceType))
        {
            log.debug("照会区分の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + STR_METHOD_NAME,
                "照会区分 = " + this.referenceType);
        }

        //２）商品区分チェック
        //２－１）
        //    this.商品区分 ＝ null の場合、「商品区分がnull」の例外をスローする。
        // 　@ class: WEB3BusinessLayerException
        // 　@ tag:   BUSINESS_ERROR_02182
        if (WEB3StringTypeUtility.isEmpty(this.productDiv))
        {
            log.debug("商品区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02182,
                this.getClass().getName() + STR_METHOD_NAME,
                "商品区分 = " + this.productDiv);
        }

        //２－２）
        //this.商品区分が下記の値以外が設定されている場合、「商品区分が未定義の値」の例外をスローする。
        //    ・”現物株式、信用取引 すべて”
        //    ・”現物株式”
        //    ・”信用取引”
        //     class: WEB3BusinessLayerException
        // 　@　@tag:   BUSINESS_ERROR_01068
        if (!WEB3EquityProductDivDef.EQUITY_MARGIN.equals(this.productDiv) &&
            !WEB3EquityProductDivDef.EQUITY.equals(this.productDiv) &&
            !WEB3EquityProductDivDef.MARGIN.equals(this.productDiv))
        {
            log.debug("商品区分が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                this.getClass().getName() + STR_METHOD_NAME,
                "商品区分 = " + this.productDiv);
        }

        //３）約定状態区分チェック
        //this.約定状態区分 ≠ null and this.約定状態区分が下記の値以外が設定されている場合、
        //「約定状態区分が未定義の値」の例外をスローする。
        //    ・”未約定”
        //    ・”一部成立”
        //    ・”全部成立”
        // 　@　@class: WEB3BusinessLayerException
        // 　@　@tag:   BUSINESS_ERROR_00626
        if (!WEB3StringTypeUtility.isEmpty(this.execType) &&
            !WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE.equals(this.execType) &&
            !WEB3EquityExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(this.execType) &&
            !WEB3EquityExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(this.execType))
        {
            log.debug("約定状態区分の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00626,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定状態区分 = " + this.execType);
        }

        //４）ソートキーチェック
        //４－１）
        //    this.ソートキー ＝ null の場合、「ソートキーがnull」の例外をスローする。
        // 　@　@class: WEB3BusinessLayerException
        // 　@　@tag:   BUSINESS_ERROR_00231
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキー = " + this.sortKeys);
        }

        //４－２）
        //this.ソートキー.要素数 ＝ 0 の場合、「ソートキー.要素数が0」の例外をスローする。
        // 　@　@class: WEB3BusinessLayerException
        // 　@　@tag:   BUSINESS_ERROR_00232
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキー = " + this.sortKeys);
        }

        //４－３）
        //this.ソートキーの全要素に対して、下記のチェックを行う。
        int l_intSortKeyLength = sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            //４－３－１）
            //ソートキー.validate()をコールする。
            sortKeys[i].validate();
            //４－３－２）
            //     ソートキー.キー項目に下記の項目以外が設定されている場合、
            //     「ソートキー.キー項目が未定義の値」の例外をスローする。
            //     ・”銘柄コード”
            //     ・”口座区分”
            //     ・”市場コード”
            //     ・”取引区分”
            //     ・”値段条件”
            //     ・”執行条件”
            //     ・”発注条件”
            //     ・”注文時間”
            //     ・”発注日”
            //     ・”注文期限”
            //     ・”弁済区分”
            //     ・”弁済期限値”
            if (!WEB3EquityKeyItemDef.PRODUCTCODE.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.TRADEMARKET.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.TRADETYPE.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.PRICE_COND.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.EXECUTE_COND.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.SEND_COND.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.ORDER_TIME.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.SEND_DATE.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.ORDER_TIMELIMIT.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.REPAYMENTNUM.equals(sortKeys[i].keyItem))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "ソートキーのキー項目の値が存在しないコード値です。");
                }
            }

        //５）要求ページ番号チェック
        //
        //５－１）
        //    this.要求ページ番号 ＝ null の場合、「要求ページ番号がnull」の例外をスローする。
        //
        // 　@　@class: WEB3BusinessLayerException
        // 　@　@tag:   BUSINESS_ERROR_00089
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号 = " + this.pageIndex);
        }

        //５－２）
        //this.要求ページ番号が数字以外の値の場合、「要求ページ番号が数字以外」の例外をスローする。
        //
        // 　@　@class: WEB3BusinessLayerException
        // 　@　@tag:   BUSINESS_ERROR_00090
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号 = " + this.pageIndex);
        }

        //５－３）
        //this.要求ページ番号 ≦ 0 の場合、「要求ページ番号が0以下」の例外をスローする。
        //
        // 　@　@class: WEB3BusinessLayerException
        // 　@　@tag:   BUSINESS_ERROR_00616
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号が0以下。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号 = " + this.pageIndex);
        }

        //６）ページ内表示行数チェック
        //６－１）
        //    this.ページ内表示行数 ＝ null の場合、「ページ内表示行数がnull」の例外をスローする。
        //
        // 　@　@class: WEB3BusinessLayerException
        // 　@　@tag:   BUSINESS_ERROR_00091
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数の入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数 = " + this.pageSize);
        }

        //６－２）
        //this.ページ内表示行数が数字以外の値の場合、「ページ内表示行数が数字以外」の例外をスローする。
        //
        // 　@　@class: WEB3BusinessLayerException
        // 　@　@tag:   BUSINESS_ERROR_00092
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数 = " + this.pageSize);
        }

        //６－３）
        //this.ページ内表示行数 ≦ 0 の場合、「ページ内表示行数が0以下」の例外をスローする。
        //
        // 　@　@class: WEB3BusinessLayerException
        // 　@　@tag:   BUSINESS_ERROR_00617
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数が0以下。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数 = " + this.pageSize);
        }

        //７）市場コードチェック
        //this.市場コード ≠ null and 下記の値以外の場合、「市場コードが未定義の値」の例外をスローする。
        //    ・”東京”
        //    ・”大阪”
        //    ・”名古屋”
        //    ・”福岡”
        //    ・”札幌”
        //    ・”NNM”
        //    ・”JASDAQ”
        //    ・”JNX-PTS”
        //
        // 　@　@class: WEB3BusinessLayerException
        // 　@　@tag:   BUSINESS_ERROR_00608
        if (this.marketCode != null)
        {
            if (!(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
                || WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
                || WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
                || WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
                || WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
                || WEB3MarketCodeDef.NNM.equals(this.marketCode)
                || WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)
                || WEB3MarketCodeDef.JNX_PTS.equals(this.marketCode)))
            {
                log.debug("市場コードが存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "市場コード = " + this.marketCode);
            }
        }

        // ８）発注条件区分 ≠ null and 下記の値以外の場合、、「発注条件区分が未定義の値」の例外をスローする。
        if (this.orderCondType != null)
        {
            if (!(WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
                || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
                || WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)))
            {
                log.debug("発注条件区分の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00212,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "発注条件区分の値が存在しないコード値です。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityMarginExecuteReferenceResponse(this);
    }
}
@
