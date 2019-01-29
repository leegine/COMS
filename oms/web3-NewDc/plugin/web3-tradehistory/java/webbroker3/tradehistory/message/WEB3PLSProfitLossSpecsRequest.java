head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 損益明細照会リクエスト(WEB3PLSProfitLossSpecsRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  範慧琴(中訊) 新規作成
                 : 2006/10/19  趙林鵬 (中訊) モデル056
                 : 2006/12/15  何文敏 (中訊) モデル068
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.tradehistory.define.WEB3PlsBvsDisplayTermDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryProductDivDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryTransactionDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (損益明細照会リクエスト)<BR>
 * 損益明細照会リクエストクラス<BR>
 *
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3PLSProfitLossSpecsRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
     private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PLSProfitLossSpecsRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PLSBVS_profitLossSpecs";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411051040L;

    /**
     * (表示期間)<BR>
     * <BR>
     * 0：　@前月月初以降(DEFAULT)<BR>
     * 1：　@1ヶ月分<BR>
     * 2：　@1週間分<BR>
     * 3：　@前日1日分<BR>
     */
    public String displayTerm;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     */
    public String pageSize;

    /**
     * (表示期間From)<BR>
     * 表示期間From<BR>
     * (YYYYMMDD)<BR>
     */
    public String listStartDate;

    /**
     * (表示期間To)<BR>
     * 表示期間To<BR>
     * (YYYYMMDD)<BR>
     */
    public String listEndDate;

    /**
     * (商品区分)<BR>
     * 商品区分<BR>
     * 　@A：　@全商品<BR>
     * 　@B：　@現物・信用<BR>
     * 　@C：　@現物<BR>
     * 　@D：　@信用<BR>
     * 　@L：　@債券<BR>
     * 　@H：　@投信<BR>
     * 　@K：　@外国株式<BR>
     * 　@I：　@入出金<BR>
     */
    public String commodityType;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;

    /**
     * (処理区分)<BR>
     * 処理区分<BR>
     * 　@null：　@2ヶ月表示<BR>
     * 　@01：　@18ヶ月表示<BR>
     */
    public String transactionDiv;

    /**
     * (ソートキー)
     * 損益明細ソートキー[]
     */
    public WEB3PLSProfitLossSpecsSortKeyUnit[] sortKeys;

    /**
     * @@roseuid 418877BC00AB
     */
    public WEB3PLSProfitLossSpecsRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １） 2ヶ月表示チェック<BR>
     * 　@※this.処理区分 == nullの場合に実施する。<BR>
     * <BR>
     * １－１）　@表示期間チェック<BR>
     * 　@１－１－１）this.表示期間 == nullの場合、<BR>
     * 　@　@　@　@　@「表示期間がnull」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_01082              <BR>
     * <BR>
     * 　@１－１－２）this.表示期間が以下に示す値のいづれにも該当しない場合、<BR>
     * 　@　@　@　@　@「表示期間が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・"0：前月月初以降(DEFAULT)"<BR>
     * 　@　@　@　@　@　@・"1：1ヶ月分"<BR>
     * 　@　@　@　@　@　@・"2：1週間分"<BR>
     * 　@　@　@　@　@　@・"3：前日1日分"<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_01083              <BR>
     * <BR>
     * ２） 18ヶ月表示チェック <BR>
     * 　@※this.処理区分 == 01の場合に実施する。<BR>
     * 　@２－１） 表示期間From・Toチェック<BR>
     * 　@　@・this.表示期間From != null かつ this.表示期間To != nullの場合は、<BR>
     * 　@　@　@以下のチェックを行う。<BR>
     * <BR>
     * 　@　@２－１－１） this.表示期間FromをDate型に変換し、エラーが発生した場合は、<BR>
     * 　@　@　@　@　@　@　@　@　@「表示期間(自)日付エラー」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException<BR>
     *            tag            : BUSINESS_ERROR_01065<BR>
     * <BR>
     * 　@　@２－１－２） this.表示期間ToをDate型に変換し、エラーが発生した場合は、<BR>
     * 　@　@　@　@　@　@　@　@　@「表示期間(至)日付エラー」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException<BR>
     *            tag            : BUSINESS_ERROR_01066<BR>
     * <BR>
     * 　@　@２－１－３） this.表示期間From > this.表示期間Toである場合は、<BR>
     * 　@　@　@　@　@　@　@　@　@「表示期間(自)(至)整合性エラー」の例外をスローする。<BR>
     *            class          :  WEB3BusinessLayerException<BR>
     *            tag             : BUSINESS_ERROR_01051<BR>
     * <BR>
     *  　@２－２） 銘柄コードチェック <BR>
     *  　@　@・this.銘柄コード != nullの場合は、以下のチェックを行う<BR>
     *  <BR>
     *  　@　@２－２－１） this.銘柄コードが以下の条件に該当する場合は、<BR>
     *  　@　@　@　@　@　@　@　@　@「銘柄コードエラー」の例外をスローする。<BR>
     *  　@　@　@　@　@　@　@　@　@　@・this.銘柄コード.length() != 4桁 かつ 5桁<BR>
     *            class         :  WEB3BusinessLayerException<BR>
     *            tag            : BUSINESS_ERROR_00439<BR>
     * <BR>
     *  　@　@２－２－２） this.商品区分が以下に該当しない場合、<BR>
     *  　@　@　@　@　@　@　@　@　@「商品整合性エラー」の例外をスローする。<BR>
     *  　@　@　@　@　@　@　@　@　@　@・ ”A：　@全商品”<BR>
     *  　@　@　@　@　@　@　@　@　@　@・ ”B：　@現物・信用”<BR>
     *  　@　@　@　@　@　@　@　@　@　@・ ”C：　@現物”<BR>
     *  　@　@　@　@　@　@　@　@　@　@・ ”D：　@信用”<BR>
     *  　@　@　@　@　@　@　@　@　@　@・ ”K：　@外国株式”<BR>
     *            class         :  WEB3BusinessLayerException<BR>
     *            tag            : BUSINESS_ERROR_01068<BR>
     * <BR>
     * 　@２－３） 商品区分チェック<BR>
     * 　@　@・this.商品区分 != null の場合は、以下のチェックを行う。<BR>
     * <BR>
     * 　@　@２－３－１） this.商品区分が以下に示す値のいづれにも該当しない場合<BR>
     * 　@　@　@　@　@　@　@　@　@「商品整合性エラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@・　@A：　@全商品<BR>
     * 　@　@　@　@　@　@　@　@　@　@・　@B：　@現物・信用<BR>
     * 　@　@　@　@　@　@　@　@　@　@・　@C：　@現物<BR>
     * 　@　@　@　@　@　@　@　@　@　@・　@D：　@信用<BR>
     * 　@　@　@　@　@　@　@　@　@　@・　@L：　@債券<BR>
     * 　@　@　@　@　@　@　@　@　@　@・　@H：　@投信<BR>
     * 　@　@　@　@　@　@　@　@　@　@・　@K：　@外国株式<BR>
     * 　@　@　@　@　@　@　@　@　@　@・　@I：　@入出金<BR>
     *            class         :  WEB3BusinessLayerException<BR>
     *            tag            : BUSINESS_ERROR_01068<BR>
     * <BR>
     * 　@２－４） ソートキーチェック<BR>
     * <BR>
     * 　@　@２－４－１） this.ソートキー == nullであった場合、<BR>
     * 　@　@　@　@　@　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException<BR>
     *            tag            : BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@　@２－４－２） this.ソートキー.要素数 == 0だった場合、<BR>
     * 　@　@　@　@　@　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException<BR>
     *            tag            : BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@　@２－４－３） this.ソートキーの全要素に対して下記のチェックを行う。<BR>
     * 　@　@　@　@　@　@　@　@　@・ソートキー.validate()をコールする。<BR>
     * <BR>
     * <BR>
     * ３） 共通チェック<BR>
     *  <BR>
     * ３）要求ページ番号チェック<BR>
     * 　@３－１－１）this.要求ページ番号 == nullであった場合、<BR>
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00089              <BR>
     * <BR>
     * 　@３－１－２）this.要求ページ番号が数字以外の値であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00090              <BR>
     * <BR>
     * 　@３－１－３）this.要求ページ番号 <= 0であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00616              <BR>
     * <BR>
     * ３－２）ページ内表示行数チェック<BR>
     * 　@３－２－１）this.ページ内表示行数 == nullであった場合、<BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00091              <BR>
     * <BR>
     * 　@３－２－２）this.ページ内表示行数が数字以外の値であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00092              <BR>
     * <BR>　@
     * 　@３－２－３）this.ページ内表示行数 <= 0であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00617              <BR>
     * <BR>
     * @@roseuid 416CDE4C00C2
     */
    public void validate() throws  WEB3BaseException
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //    １） 2ヶ月表示チェック
        //   　@※this.処理区分 == nullの場合に実施する.
        if (this.transactionDiv == null)
        {
            // 　@１－１－１）this.表示期間 == nullの場合、
            // 　@　@　@　@　@「表示期間がnull」の例外をスローする。
            if (this.displayTerm == null)
            {
                log.error("表示期間がnullの値のエラー 。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01082,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "表示期間が未指定です。");
            }

            // 　@１－１－２）this.表示期間が以下に示す値のいづれにも該当しない場合、
            // 　@　@　@　@　@「表示期間が未定義の値」の例外をスローする。
            // 　@　@　@　@　@　@・"0：前月月初以降(DEFAULT)"
            // 　@　@　@　@　@　@・"1：1ヶ月分"
            // 　@　@　@　@　@　@・"2：1週間分"
            // 　@　@　@　@　@　@・"3：前日1日分"
            if (!WEB3PlsBvsDisplayTermDef.DEFAULT.equals(this.displayTerm) &&
                !WEB3PlsBvsDisplayTermDef.ONE_MONTH.equals(this.displayTerm) &&
                !WEB3PlsBvsDisplayTermDef.ONE_WEEK.equals(this.displayTerm) &&
                !WEB3PlsBvsDisplayTermDef.THE_PREVIOUS_DAY.equals(this.displayTerm))
            {
                log.error("表示期間が未定義の値のエラー 。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01083,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "表示期間が存在しないコード値です。");
            }
        }

        //    ２） 18ヶ月表示チェック
        //   　@※this.処理区分 == 01の場合に実施する。
        if (WEB3TradeHistoryTransactionDivDef.EIGHTEEN_MONTH_DISPLAY.equals(this.transactionDiv))
        {
            //   　@２－１） 表示期間From・Toチェック
            //  　@　@・this.表示期間From != null かつ this.表示期間To != nullの場合は
            //  　@　@　@以下のチェックを行う。
            if (this.listStartDate != null && this.listEndDate != null)
            {
                //２－１－１）this.表示期間FromをDate型に変換し、エラーが発生した場合は、
                //   　@　@「表示期間(自)日付エラー」の例外をスローする。
                if (!(WEB3StringTypeUtility.isDateStr(this.listStartDate, "yyyyMMdd")))
                {
                    log.error(" 表示期間(自)日付エラー 。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01065,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "表示期間（自）日付フォーマットエラー。");
                }

                //２－１－２）this.表示期間ToをDate型に変換し、エラーが発生した場合は、
                // 　@　@　@「表示期間(至)日付エラー」の例外をスローする。
                if (!(WEB3StringTypeUtility.isDateStr(this.listEndDate, "yyyyMMdd")))
                {
                    log.error(" 表示期間(至)日付エラー 。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01066,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "表示期間（至）日付フォーマットエラー。");
                }

                //２－１－３）this.表示期間From > this.表示期間Toである場合は、
                // 　@　@　@「表示期間(自)(至)整合性エラー」の例外をスローする。
                Date l_datListStartDate = WEB3DateUtility.getDate(this.listStartDate, "yyyyMMdd");
                Date l_datListEndDate = WEB3DateUtility.getDate(this.listEndDate, "yyyyMMdd");
                if (WEB3DateUtility.compareToDay(l_datListStartDate,l_datListEndDate) > 0)
                {
                    log.error(" 表示期間(自)(至)整合性エラー 。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01051,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "表示期間（自）は表示期間（至）より大きいです。");
                }
            }

            //   　@２－２） 銘柄コードチェック
            //  　@　@・this.銘柄コード != nullの場合は、以下のチェックを行う。
            if (this.productCode != null)
            {
                //２－２－１）this.銘柄コードが以下の条件に該当する場合は、
                // 　@　@　@「銘柄コードエラー」の例外をスローする。
                // 　@　@　@・this.銘柄コード.length() != 4桁 かつ 5桁
                if (this.productCode.length() != 4
                    && this.productCode.length() != 5)
                {
                    log.error(" 銘柄コードエラー 。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "銘柄コードのサイズが不正です。");
                }

                //２－２－２）this.商品区分が以下に該当しない場合、
                // 　@　@　@「商品整合性エラー」の例外をスローする。
                // 　@　@　@・”A:全商品”
                // 　@　@　@・”B:現物・信用”
                // 　@　@　@・”C:現物”
                // 　@　@　@・”D:信用”
                // 　@　@　@・”K:外国株式”
                if (!WEB3TradeHistoryProductDivDef.ALL_PRODUCT.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.EQUITY_MARGIN.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.EQUITY.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.MARGIN.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.FOREIGN.equals(this.commodityType))
                {
                    log.error(" 商品整合性エラー 。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "商品区分が存在しないコード値です。");
                }
            }

            //   　@２－３） 商品区分チェック
            //  　@　@・this.商品区分 != null の場合は、以下のチェックを行う。
            if (this.commodityType != null)
            {
                //２－３－１） this.商品区分が以下に示す値のいづれにも該当しない場合、
                //   「商品整合性エラー」の例外をスローする.
                //  ・　@A：　@全商品
                //  ・　@B：　@現物・信用
                //  ・　@C：　@現物
                //  ・　@D：　@信用
                //  ・　@L：　@債券
                //  ・　@H：　@投信
                //  ・　@K：　@外国株式
                //  ・　@I：　@入出金
                if (!WEB3TradeHistoryProductDivDef.ALL_PRODUCT.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.EQUITY_MARGIN.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.EQUITY.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.MARGIN.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.BOND.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.MUTUAL_FUND_RUITO.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.FOREIGN.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.AIO.equals(this.commodityType))
                {
                    log.error(" 商品整合性エラー 。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "商品区分が存在しないコード値です。");
                }
            }

            // ２－４－１） this.ソートキー == nullであった場合、
            //　@　@　@　@　@「ソートキーがnull」の例外をスローする。
            if (this.sortKeys == null)
            {
                log.debug("ソートキーがnull。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ソートキーが未指定です。" + this.sortKeys);
            }

            // ２－４－２） this.ソートキー.要素数 == 0だった場合、
            //　@　@　@　@「ソートキー.要素数が0」の例外をスローする。
            if (this.sortKeys.length == 0)
            {
                log.debug("ソートキー.要素数が0。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ソートキーの要素数が０です。" + this.sortKeys.length);
            }

            // ２－４－３） this.ソートキーの全要素に対して下記のチェックを行う。
            //　@　@　@　@・ソートキー.validate()をコールする。
            for (int i = 0; i < this.sortKeys.length; i++)
            {
                this.sortKeys[i].validate();
            }
        }

        // ３－１）要求ページ番号チェック
        // 　@３－１－１）this.要求ページ番号 == nullであった場合、
        // 　@　@　@　@「要求ページ番号がnull」の例外をスローする。
        if (this.pageIndex == null)
        {
             //例外
             log.error(" 要求ページ番号がnull 。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "要求ページ番号が未指定です。");
        }

        // 　@３－１－２）this.要求ページ番号が数字以外の値であった場合、
        // 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.error(" 要求ページ番号が数字以外 。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }

        // 　@３－１－３）this.要求ページ番号 <= 0であった場合、
        // 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
             log.error(" 要求ページ番号が0以下 。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "要求ページ番号の値が0以下です。");
        }

        // ３－２）ページ内表示行数チェック
        // 　@３－２－１）this.ページ内表示行数 == nullであった場合、
        // 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。
        if (this.pageSize == null)
        {
             log.error(" ページ内表示行数がnull 。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "ページ内表示行数の入力が不正です。");
        }

        // 　@３－２－２）this.ページ内表示行数が数字以外の値であった場合、
        // 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
             log.error(" ページ内表示行数が数字以外 。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "ページ内表示行数が数字以外の値です。");
        }

        // 　@３－２－３）this.ページ内表示行数 <= 0であった場合、
        // 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
             log.error("ページ内表示行数が0以下 。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "ページ内表示行数の値が0以下です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 418877BC00CB
     */
    public WEB3GenResponse createResponse()
    {
     return new WEB3PLSProfitLossSpecsResponse(this);
    }
}
@
