head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.42.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderExecInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 約定情報(WEB3AdminBondOrderExecInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
Revesion History : 2006/10/08 周捷 (中訊) 仕様変更・モデル108
Revesion History : 2007/03/09 徐大方 (中訊) 仕様変更・モデル159,161
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (約定情報)<BR>
 * 約定情報クラス
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondOrderExecInfo  extends Message
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderExecInfo.class);

    /**
     * (約定数量)<BR>
     * 約定数量
     */
    public String execFaceAmount;

    /**
     * (約定単価)<BR>
     * 約定単価
     */
    public String execPrice;

    /**
     * (約定為替レート)<BR>
     * 約定為替
     */
    public String execFxRate;

    /**
     * (約定日)<BR>
     * 国内約定日
     */
    public Date domesticExecutionDate;

    /**
     * (現地約定日)<BR>
     * 現地約定日
     */
    public Date foreignExecutionDate;

    /**
     * (受渡日)<BR>
     * 国内受渡日
     */
    public Date domesticDeliveryDate;

    /**
     * (現地受渡日)<BR>
     * 現地受渡日
     */
    public Date foreignDeliveryDate;

    /**
     * (売買代金（外貨）)<BR>
     * 売買代金（外貨）
     */
    public String foreignTradePrice;

    /**
     * (売買代金（円貨）)<BR>
     * 売買代金（円貨）
     */
    public String yenTradePrice;

    /**
     * (経過利子（外貨）)<BR>
     * 経過利子（外貨）
     */
    public String foreignAccruedInterest;

    /**
     * (経過利子（円貨）)<BR>
     * 経過利子（円貨）
     */
    public String yenAccruedInterest;

    /**
     * (受渡代金（外貨）)<BR>
     * 受渡代金（外貨）
     */
    public String foreignDeliveryPrice;

    /**
     * (受渡代金（円貨）)<BR>
     * 受渡代金（円貨）
     */
    public String yenDeliveryPrice;

    /**
     * (経過日数)<BR>
     * 経過日数
     */
    public String elapsedDays;

    /**
     * (基準日数)<BR>
     * 基準日数
     */
    public String calcBaseDays;

    /**
     * (カストディアン)<BR>
     * カストディアン
     */
    public WEB3AdminBondCustodianUnit custodianInfo;

    /**
     * (注文約定区分)<BR>
     * 注文約定区分<BR>
     * <BR>
     * 0：未約定　@1：約定済　@2：取消済
     */
    public String executionState;

    /**
     * (警告区分一覧)<BR>
     * 警告区分一覧 <BR>
     * <BR>
     * 1：経過利子が正確でない可能性 <BR>
     * 2：受渡代金が一致しない <BR>
     * 3：余力チェックNG <BR>
     * 4：経過利子計算不可能 <BR>
     * 5：海外市場が発注日に対して非営業日
     */
    public String[] warningDiv;

    /**
     * (入金日)<BR>
     * 入金日
     */
    public Date paymentDate;

    /**
     * (約定情報)<BR>
     * コンストラクタ
     * @@roseuid 44CB04FC00FD
     */
    public WEB3AdminBondOrderExecInfo()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * <BR>　@　@
     * １)　@約定数量チェック <BR>
     * 　@this.約定数量!=nullの場合、以下をチェックする。 <BR>
     * 　@this.約定数量が整数１１桁以内でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02635<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02641<BR>
     * <BR>
     * 　@this.約定数量 <= 0場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02636<BR>
     * <BR>
     * ２)　@約定単価チェック <BR>
     * 　@this.約定単価!=nullの場合、以下をチェックする。 <BR>
     * 　@this.約定単価が整数４桁以内（＜＝４桁）＋小数点＋
     * 　@　@　@　@小数６桁以内（＜＝６桁）でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02551<BR>
     * <BR>
     * 　@this.約定単価が数値でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02022<BR>
     * <BR>
     * 　@this.約定単価＜＝０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02023<BR>
     * <BR>
     * ３)　@約定為替レートチェック <BR>
     * 　@this.約定為替レート != nullの場合、以下をチェックする。<BR>
     * 　@this.約定為替レートが整数３桁以内（＜＝３桁）＋小数点＋
     * 　@　@　@　@小数４桁以内（＜＝４桁）でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02037<BR>
     * <BR>
     * 　@this.約定為替レートが数値でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02220<BR>
     * <BR>
     * 　@this.約定為替レート＜＝０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02196<BR>
     * <BR>
     * ４)　@約定日チェック <BR>
     * 　@this.約定日 == nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02637<BR>
     * <BR>
     * ５)　@売買代金(外貨)チェック <BR>
     * 　@this.売買代金(外貨) != nullの場合、以下をチェックする。<BR>
     * 　@this.売買代金(外貨)が整数１２桁以内（＜＝１２桁）＋小数点＋
     * 　@　@　@　@小数２桁以内（＜＝２桁）でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02555<BR>
     * <BR>
     * 　@this.売買代金(外貨)が数値でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02556<BR>
     * <BR>
     * 　@this.売買代金(外貨)＜＝０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02557<BR>
     * <BR>
     * ６)　@売買代金(円貨)チェック <BR>
     * 　@this.売買代金(円貨) != nullの場合、以下をチェックする。<BR>
     * 　@this.売買代金(円貨)が１２桁以内でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02558<BR>
     * <BR>
     * 　@this.売買代金(円貨)が整数でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02559<BR>
     * <BR>
     * 　@this.売買代金(円貨)＜＝０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02560<BR>
     * <BR>
     * ７)　@経過利子(外貨)チェック <BR>
     * 　@this.経過利子(外貨) != nullの場合、以下をチェックする。<BR>
     * 　@this.経過利子(外貨)が整数１２桁以内（＜＝１２桁）＋
     * 　@　@　@　@小数点＋小数２桁以内（＜＝２桁）でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02561<BR>
     * <BR>
     * 　@this.経過利子(外貨)が数値でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02562<BR>
     * <BR>
     * 　@this.経過利子(外貨)＜０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02563<BR>
     * <BR>
     * ８)　@経過利子(円貨)チェック <BR>
     * 　@this.経過利子(円貨) != nullの場合、以下をチェックする。<BR>
     * 　@this.経過利子(円貨)が１２桁以内でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02564<BR>
     * <BR>
     * 　@this.経過利子(円貨)が整数でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02565<BR>
     * <BR>
     * 　@this.経過利子(円貨)＜０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02566<BR>
     * <BR>
     * ９)　@受渡代金(外貨)チェック <BR>
     * 　@this.受渡代金(外貨) != nullの場合、以下をチェックする。<BR>
     * 　@this.受渡代金(外貨)が整数１１桁以内（＜＝１１桁）＋小数点＋
     * 　@　@　@　@小数２桁以内（＜＝２桁）でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02567<BR>
     * <BR>
     * 　@this.受渡代金(外貨)が数値でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02568<BR>
     * <BR>
     * 　@this.受渡代金(外貨)＜＝０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02569<BR>
     * <BR>
     * １０)　@受渡代金(円貨)チェック <BR>
     * 　@this.受渡代金(円貨) != nullの場合、以下をチェックする。<BR>
     * 　@this.受渡代金(円貨)が１２桁以内でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02570<BR>
     * <BR>
     * 　@this.受渡代金(円貨)が整数でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02571<BR>
     * <BR>
     * 　@this.受渡代金(円貨)＜＝０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02572<BR>
     * <BR>
     * １１)　@経過日数チェック <BR>
     * 　@this.経過日数 != nullの場合、以下をチェックする。<BR>
     * 　@this.経過日数が５桁以内でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02573<BR>
     * <BR>
     * 　@this.経過日数が整数でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02574<BR>
     * <BR>
     * 　@this.経過日数＜０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02575<BR>
     * <BR>
     * １２)　@基準日数チェック <BR>
     * 　@this.基準日数 != nullの場合、以下をチェックする。<BR>
     * 　@this.基準日数が５桁以内でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02576<BR>
     * <BR>
     * 　@this.基準日数が整数でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02577<BR>
     * <BR>
     * 　@this.基準日数＜０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02578
     * @@throws WEB3BaseException
     * @@roseuid 44C091EE03AC
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１)　@約定数量チェック
        //this.約定数量!=nullの場合、以下をチェックする。
        //this.約定数量が整数１１桁以内でない場合、例外をスローする。
        if ((this.execFaceAmount != null))
        {
            if(WEB3StringTypeUtility.getIntegerDigits(this.execFaceAmount) > 11)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02635,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "額面金額のサイズが不正です。");
            }      
            if (!WEB3StringTypeUtility.isInteger(this.execFaceAmount))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02641,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "額面金額が整数値ではありません。");
            }
        }
        //this.約定数量 <= 0場合、例外をスローする。
        //class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_02186
        if ((this.execFaceAmount != null) &&
            (Long.parseLong(this.execFaceAmount) <= 0))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02636,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "額面金額が0以下の値です。");
        }

        //２)　@約定単価チェック
        //this.約定単価!=nullの場合、以下をチェックする。
        if (this.execPrice != null)
        {
            //this.約定単価が整数４桁以内（＜＝４桁）＋小数点＋
            //小数６桁以内（＜＝６桁）でない場合、例外をスローする。
            if((WEB3StringTypeUtility.getIntegerDigits(this.execPrice) > 4) ||
                (WEB3StringTypeUtility.getFractionDigits(this.execPrice) > 6))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02551,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "約定単価は整数部４桁，小数部６桁の範囲外です。");
            }

            //this.約定単価が数値でない場合、例外をスローする。
            if (!WEB3StringTypeUtility.isNumber(this.execPrice))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02022,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "約定単価が数値以外の値です。");
            }
            //this.約定単価＜＝０の場合、例外をスローする。
            if ((Double.parseDouble(this.execPrice) <= 0))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02023,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "約定単価が0以下の値です。");
            }
        }
        //３)　@約定為替レートチェック
        //this.約定為替レート != nullの場合、以下をチェックする。
        if (this.execFxRate != null)
        {
            //this.約定為替レートが整数３桁以内（＜＝３桁）＋小数点＋
            //小数４桁以内（＜＝４桁）でない場合、例外をスローする。
            if ((WEB3StringTypeUtility.getIntegerDigits(this.execFxRate) > 3) ||
                (WEB3StringTypeUtility.getFractionDigits(this.execFxRate) > 4))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02037,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "約定為替レートの有効桁数が、整数部３桁，小数部４桁の範囲外です。");
            }

            //this.約定為替レートが数値でない場合、例外をスローする。
            if (!WEB3StringTypeUtility.isNumber(this.execFxRate))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02220,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "約定為替レートが数値以外の値です。");
            }

            //this.約定為替レート＜＝０の場合、例外をスローする。
            if (Double.parseDouble(this.execFxRate) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02196,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "約定為替レートが0以下の値です。");
            }
        }

        //４)　@約定日チェック
        //this.約定日 == nullの場合、例外をスローする。
        if (this.domesticExecutionDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02637,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定日が未指定です。");
        }

        //６)　@売買代金(外貨)チェック
        //this.売買代金(外貨) != nullの場合、以下をチェックする。
        if (this.foreignTradePrice != null)
        {
            //this.売買代金(外貨)が整数１２桁以内（＜＝１２桁）＋小数点＋
            //小数２桁以内（＜＝２桁）でない場合、例外をスローする。
            if ((WEB3StringTypeUtility.getIntegerDigits(this.foreignTradePrice) > 12) ||
                (WEB3StringTypeUtility.getFractionDigits(this.foreignTradePrice) > 2))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02555,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "売買代金（外貨）の有効桁数が、整数部１２桁，小数部２桁の範囲外です。");
            }

            //this.売買代金(外貨)が数値でない場合、例外をスローする。
            if (this.foreignTradePrice != null)
            {
                if (!WEB3StringTypeUtility.isNumber(this.foreignTradePrice))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02556,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "売買代金（外貨）が数値以外の値です。");
                }
            }

            //this.売買代金(外貨)＜＝０の場合、例外をスローする。7
            if ((this.foreignTradePrice != null) &&
                (Double.parseDouble(this.foreignTradePrice) <= 0))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02557,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "売買代金（外貨）が0以下の値です。");
            }
        }

        //７)　@売買代金(円貨)チェック
        //this.売買代金(円貨) != nullの場合、以下をチェックする。
        if (this.yenTradePrice != null)
        {
            //this.売買代金(円貨)が１２桁以内でない場合、例外をスローする。
            if (WEB3StringTypeUtility.getByteLength(this.yenTradePrice) > 12)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02558,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "売買代金（円貨）のサイズが不正です。");
            }

            //this.売買代金(円貨)が整数でない場合、例外をスローする。
            if (!WEB3StringTypeUtility.isInteger(this.yenTradePrice))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02559,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "売買代金（円貨）が整数以外の値です。");
            }

            //this.売買代金(円貨)＜＝０の場合、例外をスローする。
            if (Long.parseLong(this.yenTradePrice) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02560,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "売買代金（円貨）が0以下の値です。");
            }
        }

        //８)　@経過利子(外貨)チェック
        //this.経過利子(外貨) != nullの場合、以下をチェックする。
        if (this.foreignAccruedInterest != null)
        {
            //this.経過利子(外貨)が整数１２桁以内（＜＝１２桁）＋
            //小数点＋小数２桁以内（＜＝２桁）でない場合、例外をスローする。
            if ((WEB3StringTypeUtility.getIntegerDigits(this.foreignAccruedInterest) > 12) ||
                (WEB3StringTypeUtility.getFractionDigits(this.foreignAccruedInterest) > 2))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02561,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "経過利子（外貨）の有効桁数が、整数部１２桁，小数部２桁の範囲外です。");
            }

            //this.経過利子(外貨)が数値でない場合、例外をスローする。
            if (!WEB3StringTypeUtility.isNumber(this.foreignAccruedInterest))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02562,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "経過利子（外貨）が数値以外の値です。");
            }

            //this.経過利子(外貨)＜０の場合、例外をスローする。
            if (Double.parseDouble(this.foreignAccruedInterest) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02563,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "経過利子（外貨）が0より小さい値です。");
            }
        }

        //９)　@経過利子(円貨)チェック
        //this.経過利子(円貨) != nullの場合、以下をチェックする。
        if (this.yenAccruedInterest != null)
        {
            //this.経過利子(円貨)が１２桁以内でない場合、例外をスローする。
            if (WEB3StringTypeUtility.getByteLength(this.yenAccruedInterest) > 12)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02564,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "経過利子（円貨）のサイズが不正です。");
            }

            //this.経過利子(円貨)が整数でない場合、例外をスローする。
            if (!WEB3StringTypeUtility.isInteger(this.yenAccruedInterest))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02565,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "経過利子（円貨）が整数以外の値です。");
            }

            //this.経過利子(円貨)＜０の場合、例外をスローする。
            if (Long.parseLong(this.yenAccruedInterest) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02566,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "経過利子（円貨）が0より小さい値です。");
            }
        }
        //１０)　@受渡代金(外貨)チェック
        //this.受渡代金(外貨) != nullの場合、以下をチェックする。
        if (this.foreignDeliveryPrice != null)
        {
            //this.受渡代金(外貨)が整数１１桁以内（＜＝１１桁）＋小数点＋
            //小数２桁以内（＜＝２桁）でない場合、例外をスローする。
            if ((WEB3StringTypeUtility.getIntegerDigits(this.foreignDeliveryPrice) > 11) ||
                (WEB3StringTypeUtility.getFractionDigits(this.foreignDeliveryPrice) > 2))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02567,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "受渡代金（外貨）の有効桁数が、整数部１１桁，小数部２桁の範囲外です。");
            }

            //this.受渡代金(外貨)が数値でない場合、例外をスローする。68
            if (!WEB3StringTypeUtility.isNumber(this.foreignDeliveryPrice))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02568,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "受渡代金（外貨）が数値以外の値です。");
            }

            //this.受渡代金(外貨)＜＝０の場合、例外をスローする。
            if (Double.parseDouble(this.foreignDeliveryPrice) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02569,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "受渡代金（外貨）が0以下の値です。");
            }
        }
        //１１)　@受渡代金(円貨)チェック
        //this.受渡代金(円貨) != nullの場合、以下をチェックする。
        if (this.yenDeliveryPrice != null)
        {
            //this.受渡代金(円貨)が１２桁以内でない場合、例外をスローする。
            if (WEB3StringTypeUtility.getByteLength(this.yenDeliveryPrice) > 12)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02570,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "受渡代金（円貨）のサイズが不正です。");
            }

            //this.受渡代金(円貨)が整数でない場合、例外をスローする。
            if (!WEB3StringTypeUtility.isInteger(this.yenDeliveryPrice))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02571,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "受渡代金（円貨）が整数以外の値です。");
            }
            //this.受渡代金(円貨)＜＝０の場合、例外をスローする。
            if (Long.parseLong(this.yenDeliveryPrice) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02572,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "受渡代金（円貨）が0以下の値です。");
            }
        }
        //１２)　@経過日数チェック
        //this.経過日数 != nullの場合、以下をチェックする。
        if (this.elapsedDays != null)
        {
            //this.経過日数が５桁以内でない場合、例外をスローする。
            if (WEB3StringTypeUtility.getByteLength(this.elapsedDays) > 5)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02573,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "経過日数のサイズが不正です。");
            }

            //this.経過日数が整数でない場合、例外をスローする。
            if (!WEB3StringTypeUtility.isInteger(this.elapsedDays))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02574,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "経過日数が整数以外の値です。");
            }

            //this.経過日数＜０の場合、例外をスローする。
            if (Integer.parseInt(this.elapsedDays) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02575,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "経過日数が0より小さい値です。");
            }
        }
        //１３)　@基準日数チェック
        //this.基準日数 != nullの場合、以下をチェックする。
        if (this.calcBaseDays != null)
        {
            //this.基準日数が５桁以内でない場合、例外をスローする。
            if (WEB3StringTypeUtility.getByteLength(this.calcBaseDays) > 5)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02576,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "基準日数のサイズが不正です。");
            }

            //this.基準日数が整数でない場合、例外をスローする。
            if (!WEB3StringTypeUtility.isInteger(this.calcBaseDays))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02577,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "基準日数が整数以外の値です。");
            }

            //this.基準日数＜０の場合、例外をスローする。
            if (Integer.parseInt(this.calcBaseDays) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02578,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "基準日数が0より小さい値です。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
