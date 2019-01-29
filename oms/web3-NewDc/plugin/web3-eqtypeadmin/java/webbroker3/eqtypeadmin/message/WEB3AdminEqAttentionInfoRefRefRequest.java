head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEqAttentionInfoRefRefRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・注意情報照会リクエスト(WEB3AdminEqAttentionInfoRefRefRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 孟亞南 (中訊) 新規作成 モデルNo.217,モデルNo.221-224
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AttentionInfoDivCodeDef;
import webbroker3.common.define.WEB3AttentionInfoTypeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・注意情報照会リクエスト)<BR>
 * 管理者・注意情報照会リクエストクラス<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminEqAttentionInfoRefRefRequest extends WEB3GenRequest
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEqAttentionInfoRefRefRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_eq_attention_info_ref_ref";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200812301405L;

    /**
     * (注意情報種別)<BR>
     * 注意情報種別<BR>
     */
    public String attentionInfoType;

    /**
     * (注意情報区分コード)<BR>
     * 注意情報区分コード<BR>
     */
    public String attentionInfoDivCode;

    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;

    /**
     * (有効日)<BR>
     * 有効日<BR>
     */
    public String validDate;

    /**
     * (情報発生日時From)<BR>
     * 情報発生日時From<BR>
     */
    public String infoOccuredDateFrom;

    /**
     * (情報発生日時To)<BR>
     * 情報発生日時To<BR>
     */
    public String infoOccuredDateTo;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     */
    public String pageSize;

    /**
     * (注意情報照会ソートキー)<BR>
     * 注意情報照会ソートキー<BR>
     */
    public WEB3AdminEqAttentionInfoRefSortKey[] sortKeys;

    /**
     * @@roseuid 49588AEF0167
     */
    public WEB3AdminEqAttentionInfoRefRefRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）注意情報種別チェック<BR>
     * 　@this.注意情報種別 != nullの場合、以下のチェックを行う。<BR>
     * 　@１-１）this.注意情報種別に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「注意情報種別が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"売停情報"<BR>
     * 　@　@　@　@・"値幅制限情報"<BR>
     * 　@　@　@　@・"フリーフォーマット"<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03147<BR>
     * <BR>
     * ２）注意情報区分チェック<BR>
     * 　@this.注意情報区分コード != nullの場合、以下のチェックを行う。<BR>
     * 　@２-１）this.注意情報区分コードに下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「注意情報区分コードが未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"売買停止（注文受付可）"<BR>
     * 　@　@　@　@・"売買停止（注文受付不可）"<BR>
     * 　@　@　@　@・"売買停止（注文受付可）の取消"<BR>
     * 　@　@　@　@・"売買停止（注文受付不可）の取消"<BR>
     * 　@　@　@　@・"売買停止（注文受付可）の解除"<BR>
     * 　@　@　@　@・"売買停止（注文受付不可）の解除"<BR>
     * 　@　@　@　@・"売買停止（注文受付可）の解除の取消"<BR>
     * 　@　@　@　@・"売買停止（注文受付不可）の解除の取消"<BR>
     * 　@　@　@　@・"売買中断"<BR>
     * 　@　@　@　@・"売買中断の取消"<BR>
     * 　@　@　@　@・"売買中断の解除"<BR>
     * 　@　@　@　@・"売買中断の解除の取消"<BR>
     * 　@　@　@　@・"新規上場銘柄の初値が付いた場合"<BR>
     * 　@　@　@　@・"フリーフォーマット"<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03149<BR>
     * <BR>
     * ３）注意情報種別-注意情報区分コードの相関チェック<BR>
     * 　@　@　@　@　@　@　@　@　@this.注意情報種別 != null　@かつ<BR>
     * 　@　@　@　@　@　@　@　@　@this.注意情報区分コード != null の場合、以下のチェックを行う。<BR>
     * <BR>
     * 　@３－１）this.注意情報種別 ＝ "フリーフォーマット" かつ<BR>
     * 　@　@　@　@　@　@　@　@　@　@this.注意情報区分コード ≠ "フリーフォーマット"の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@「注意情報種別／注意情報区分指定が不整合」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_03148<BR>
     * <BR>
     * 　@３－２）this.注意情報種別 ＝ "値幅制限情報" かつ<BR>
     * 　@　@　@　@　@　@　@　@　@　@this.注意情報区分コード ≠ "新規上場銘柄の初値が付いた場合"の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@「注意情報種別／注意情報区分指定が不整合」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_03148<BR>
     * <BR>
     * 　@３－３）this.注意情報種別 ＝ "売停情報" の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@this.注意情報区分コード ＝ "フリーフォーマット"<BR>
     * 　@　@　@　@　@　@　@　@　@　@あるいは、this.注意情報区分コード ＝ "新規上場銘柄の初値が付いた場合"<BR>
     * 　@　@　@　@　@　@　@　@　@　@であれば、「注意情報種別／注意情報区分指定が不整合」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_03148<BR>
     * <BR>
     * ４）市場チェック<BR>
     * 　@this.市場コード != nullの場合、以下のチェックを行う。<BR>
     * 　@４-１）this.市場コードに下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「市場コードが未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"東京"<BR>
     * 　@　@　@　@・"大阪"<BR>
     * 　@　@　@　@・"名古屋"<BR>
     * 　@　@　@　@・"福岡"<BR>
     * 　@　@　@　@・"札幌"<BR>
     * 　@　@　@　@・"NNM"<BR>
     * 　@　@　@　@・"JASDAQ"<BR>
     * 　@　@　@　@・"JNX-PTS"<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     * ５）銘柄コードチェック<BR>
     * 　@this.銘柄コード != nullの場合、以下のチェックを行う。<BR>
     * 　@５-１）this.銘柄コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「銘柄コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・this.銘柄コード != 数字<BR>
     * 　@　@　@　@　@　@・this.銘柄コード.length != 5<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02785<BR>
     * <BR>
     * ６）有効日チェック<BR>
     * 　@this.有効日 != nullの場合、以下のチェックを行う。<BR>
     * 　@６-１）this.有効日が以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「有効日エラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・this.有効日をDate型に変換できなかった場合。<BR>
     * 　@　@　@　@　@　@　@※日時フォーマット「YYYYMMDD」<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_03150<BR>
     * <BR>
     * ７）情報発生日時Fromチェック<BR>
     * 　@this.情報発生日時From != nullの場合、以下のチェックを行う。<BR>
     * 　@７-１）this.情報発生日時Fromが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「情報発生日時Fromエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・this.情報発生日時FromをDate型に変換できなかった場合。<BR>
     * 　@　@　@　@　@　@　@※日時フォーマット「YYYYMMDDHH24MISS」<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_03151<BR>
     * <BR>
     * ８）情報発生日時Toチェック<BR>
     * 　@this.情報発生日時To != nullの場合、以下のチェックを行う。<BR>
     * 　@８-１）this.情報発生日時Toが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「情報発生日時Toエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・this.情報発生日時ToをDate型に変換できなかった場合。<BR>
     * 　@　@　@　@　@　@　@※日時フォーマット「YYYYMMDDHH24MISS」<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_03152<BR>
     * <BR>
     * ９）情報発生日時From/To整合性チェック<BR>
     * 　@this.情報発生日時From != null　@かつ　@this.情報発生日時To != nullの場合、以下のチェックを行う。<BR>
     * 　@９-１）this.情報発生日時From > this.情報発生日時Toの場合、<BR>
     * 　@　@　@　@　@「入力時間整合性エラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_01481<BR>
     * <BR>
     * １０）ソートキーチェック<BR>
     * 　@１０－１）　@this.ソートキー == nullであった場合、<BR>
     * 　@　@　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@１０－２）this.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@　@１０－２－１）ソートキー.validate()をコールする。<BR>
     * <BR>
     * １１）表示ページ番号チェック<BR>
     * 　@１１-１）this.表示ページ番号 == nullであった場合、<BR>
     * 　@　@　@　@　@　@「表示ページ番号がnull」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@１１-２）this.表示ページ番号が数字以外の値であった場合、<BR>
     * 　@　@　@　@　@　@「表示ページ番号が数字以外」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@１１-３）this.表示ページ番号 <= 0であった場合、<BR>
     * 　@　@　@　@　@　@「表示ページ番号が0以下」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * １２）ページ内表示行数チェック<BR>
     * 　@１２-１）this.ページ内表示行数 == nullであった場合、<BR>
     * 　@　@　@　@　@　@「ページ内表示行数がnull」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02224<BR>
     * <BR>
     * 　@１２-２）this.ページ内表示行数が数字以外の値であった場合、<BR>
     * 　@　@　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * 　@１２-３）this.ページ内表示行数 <= 0であった場合、<BR>
     * 　@　@　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@roseuid 4945DC320281
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）注意情報種別チェック
        //　@this.注意情報種別 != nullの場合、以下のチェックを行う。
        //　@１-１）this.注意情報種別に下記の項目以外が設定されていたら、
        //　@　@　@　@「注意情報種別が未定義の値」の例外をスローする。
        //　@　@　@　@・"売停情報"
        //　@　@　@　@・"値幅制限情報"
        //　@　@　@　@・"フリーフォーマット"
        if (this.attentionInfoType != null)
        {
            if (!WEB3AttentionInfoTypeDef.SELL_STOP_INFO.equals(this.attentionInfoType)
                && !WEB3AttentionInfoTypeDef.LIMIT_RANGE_INFO.equals(this.attentionInfoType)
                && !WEB3AttentionInfoTypeDef.FREE_FORMAT.equals(this.attentionInfoType))
            {
                log.debug("注意情報種別が未定義の値。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03147,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注意情報種別が未定義の値。");
            }
        }

        //２）注意情報区分チェック
        //　@this.注意情報区分コード != nullの場合、以下のチェックを行う。
        //　@２-１）this.注意情報区分コードに下記の項目以外が設定されていたら、
        //　@　@　@　@「注意情報区分コードが未定義の値」の例外をスローする。
        //　@　@　@　@・"売買停止（注文受付可）"
        //　@　@　@　@・"売買停止（注文受付不可）"
        //　@　@　@　@・"売買停止（注文受付可）の取消"
        //　@　@　@　@・"売買停止（注文受付不可）の取消"
        //　@　@　@　@・"売買停止（注文受付可）の解除"
        //　@　@　@　@・"売買停止（注文受付不可）の解除"
        //　@　@　@　@・"売買停止（注文受付可）の解除の取消"
        //　@　@　@　@・"売買停止（注文受付不可）の解除の取消"
        //　@　@　@　@・"売買中断"
        //　@　@　@　@・"売買中断の取消"
        //　@　@　@　@・"売買中断の解除"
        //　@　@　@　@・"売買中断の解除の取消"
        //　@　@　@　@・"新規上場銘柄の初値が付いた場合"
        //　@　@　@　@・"フリーフォーマット"
        if (this.attentionInfoDivCode != null)
        {
            if (!WEB3AttentionInfoDivCodeDef.TRADE_STOP_ORDER_ACCEPT_ENABLE.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_STOP_ORDER_ACCEPT_DISABLE.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_STOP_CANCEL_ORDER_ACCEPT_ENABLE.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_STOP_CANCEL_ORDER_ACCEPT_DISABLE.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_ORDER_ACCEPT_ENABLE.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_ORDER_ACCEPT_DISABLE.equals(
                    this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_CANCEL_ORDER_ACCEPT_ENABLE.equals(
                    this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_CANCEL_ORDER_ACCEPT_DISABLE.equals(
                    this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT_CANCEL.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT_RELEASE.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT_RELEASE_CANCEL.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.OPEN_LISTING_PRODUCT_GIVEN_FIRST_VALUE.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.FREE_FORMAT.equals(this.attentionInfoDivCode))
            {
                log.debug("注意情報区分コードが未定義の値。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03149,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注意情報区分コードが未定義の値。");
            }
        }

        //３）注意情報種別-注意情報区分コードの相関チェック
        //　@　@　@　@　@　@　@　@　@this.注意情報種別 != null　@かつ
        //　@　@　@　@　@　@　@　@　@this.注意情報区分コード != null の場合、以下のチェックを行う。
        if (this.attentionInfoType != null && this.attentionInfoDivCode != null)
        {
            //　@３－１）this.注意情報種別 ＝ "フリーフォーマット" かつ
            //　@　@　@　@　@　@　@　@　@　@this.注意情報区分コード ≠ "フリーフォーマット"の場合、
            //　@　@　@　@　@　@　@　@　@「注意情報種別／注意情報区分指定が不整合」の例外をスローする。
            if (WEB3AttentionInfoTypeDef.FREE_FORMAT.equals(this.attentionInfoType)
                && !WEB3AttentionInfoDivCodeDef.FREE_FORMAT.equals(this.attentionInfoDivCode))
            {
                log.debug("注意情報種別／注意情報区分指定が不整合。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03148,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注意情報種別／注意情報区分指定が不整合。");
            }

            //　@３－２）this.注意情報種別 ＝ "値幅制限情報" かつ
            //　@　@　@　@　@　@　@　@　@　@this.注意情報区分コード ≠ "新規上場銘柄の初値が付いた場合"の場合、
            //　@　@　@　@　@　@　@　@　@「注意情報種別／注意情報区分指定が不整合」の例外をスローする。
            if (WEB3AttentionInfoTypeDef.LIMIT_RANGE_INFO.equals(this.attentionInfoType)
                && !WEB3AttentionInfoDivCodeDef.OPEN_LISTING_PRODUCT_GIVEN_FIRST_VALUE.equals(
                       this.attentionInfoDivCode))
            {
                log.debug("注意情報種別／注意情報区分指定が不整合。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03148,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注意情報種別／注意情報区分指定が不整合。");
            }

            //　@３－３）this.注意情報種別 ＝ "売停情報" の場合、
            //　@　@　@　@　@　@　@　@　@　@this.注意情報区分コード ＝ "フリーフォーマット"
            //　@　@　@　@　@　@　@　@　@　@あるいは、this.注意情報区分コード ＝ "新規上場銘柄の初値が付いた場合"
            //　@　@　@　@　@　@　@　@　@　@であれば、「注意情報種別／注意情報区分指定が不整合」の例外をスローする。
            if (WEB3AttentionInfoTypeDef.SELL_STOP_INFO.equals(this.attentionInfoType))
            {
                if (WEB3AttentionInfoDivCodeDef.FREE_FORMAT.equals(this.attentionInfoDivCode)
                    || WEB3AttentionInfoDivCodeDef.OPEN_LISTING_PRODUCT_GIVEN_FIRST_VALUE.equals(
                           this.attentionInfoDivCode))
                {
                    log.debug("注意情報種別／注意情報区分指定が不整合。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03148,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "注意情報種別／注意情報区分指定が不整合。");
                }
            }
        }

        //４）市場チェック
        //　@this.市場コード != nullの場合、以下のチェックを行う。
        //　@４-１）this.市場コードに下記の項目以外が設定されていたら、
        //　@　@　@　@「市場コードが未定義の値」の例外をスローする。
        //　@　@　@　@・"東京"
        //　@　@　@　@・"大阪"
        //　@　@　@　@・"名古屋"
        //　@　@　@　@・"福岡"
        //　@　@　@　@・"札幌"
        //　@　@　@　@・"NNM"
        //　@　@　@　@・"JASDAQ"
        //　@　@　@　@・"JNX-PTS"
        if (this.marketCode != null)
        {
            if (!WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
                && !WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
                && !WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
                && !WEB3MarketCodeDef.NNM.equals(this.marketCode)
                && !WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)
                && !WEB3MarketCodeDef.JNX_PTS.equals(this.marketCode))
            {
                log.debug("市場コードが存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "市場コードが存在しないコード値です。");
            }
        }

        //５）銘柄コードチェック
        //　@this.銘柄コード != nullの場合、以下のチェックを行う。
        //　@５-１）this.銘柄コードが以下の条件に該当する場合、
        //　@　@　@　@　@「銘柄コードエラー」の例外をスローする。
        //　@　@　@　@　@　@・this.銘柄コード != 数字
        //　@　@　@　@　@　@・this.銘柄コード.length != 5
        if (this.productCode != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.productCode)
                || this.productCode.length() != 5)
            {
                log.debug("銘柄コードエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02785,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄コードエラー。");
            }
        }

        //６）有効日チェック
        //　@this.有効日 != nullの場合、以下のチェックを行う。
        //　@６-１）this.有効日が以下の条件に該当する場合、
        //　@　@　@　@　@「有効日エラー」の例外をスローする。
        //　@　@　@　@　@　@・this.有効日をDate型に変換できなかった場合。
        //　@　@　@　@　@　@　@※日時フォーマット「YYYYMMDD」
        if (this.validDate != null)
        {
            if (WEB3DateUtility.getDate(this.validDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD) == null)
            {
                log.debug("有効日エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03150,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "有効日エラー。");
            }
        }

        //７）情報発生日時Fromチェック
        //　@this.情報発生日時From != nullの場合、以下のチェックを行う。
        //　@７-１）this.情報発生日時Fromが以下の条件に該当する場合、
        //　@　@　@　@　@「情報発生日時Fromエラー」の例外をスローする。
        //　@　@　@　@　@　@・this.情報発生日時FromをDate型に変換できなかった場合。
        //　@　@　@　@　@　@　@※日時フォーマット「YYYYMMDDHH24MISS」
        if (this.infoOccuredDateFrom != null)
        {
            if (WEB3DateUtility.getDate(this.infoOccuredDateFrom,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS) == null)
            {
                log.debug("情報発生日時Fromエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03151,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "情報発生日時Fromエラー。");
            }
        }

        //８）情報発生日時Toチェック
        //　@this.情報発生日時To != nullの場合、以下のチェックを行う。
        //　@８-１）this.情報発生日時Toが以下の条件に該当する場合、
        //　@　@　@　@　@「情報発生日時Toエラー」の例外をスローする。
        //　@　@　@　@　@　@・this.情報発生日時ToをDate型に変換できなかった場合。
        //　@　@　@　@　@　@　@※日時フォーマット「YYYYMMDDHH24MISS」
        if (this.infoOccuredDateTo != null)
        {
            if (WEB3DateUtility.getDate(this.infoOccuredDateTo,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS) == null)
            {
                log.debug("情報発生日時Toエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03152,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "情報発生日時Toエラー。");
            }
        }

        //９）情報発生日時From/To整合性チェック
        //　@this.情報発生日時From != null　@かつ　@this.情報発生日時To != nullの場合、以下のチェックを行う。
        //　@９-１）this.情報発生日時From > this.情報発生日時Toの場合、
        //　@　@　@　@　@「入力時間整合性エラー」の例外をスローする。
        if (this.infoOccuredDateFrom != null && this.infoOccuredDateTo != null)
        {
            Date l_datFrom =
                WEB3DateUtility.getDate(this.infoOccuredDateFrom,
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
            Date l_datTo =
                WEB3DateUtility.getDate(this.infoOccuredDateTo,
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
            if (WEB3DateUtility.compareToSecond(l_datFrom, l_datTo) > 0)
            {
                log.debug("入力時間整合性エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01481,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力時間整合性エラー。");
            }
        }

        //１０）ソートキーチェック
        //　@１０－１）　@this.ソートキー == nullであった場合、
        //　@　@　@　@　@　@「ソートキーがnull」の例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }

        //　@１０－２）this.ソートキーの要素数分以下の処理を繰り返す。
        //　@　@１０－２－１）ソートキー.validate()をコールする。
        int l_intSortKeys = this.sortKeys.length;
        for (int i = 0; i < l_intSortKeys; i++)
        {
            this.sortKeys[i].validate();
        }

        //１１）表示ページ番号チェック
        //　@１１-１）this.表示ページ番号 == nullであった場合、
        //　@　@　@　@　@　@「表示ページ番号がnull」の例外をスローする。
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        //　@１１-２）this.表示ページ番号が数字以外の値であった場合、
        //　@　@　@　@　@　@「表示ページ番号が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }

        //　@１１-３）this.表示ページ番号 <= 0であった場合、
        //　@　@　@　@　@　@「表示ページ番号が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        //１２）ページ内表示行数チェック
        //　@１２-１）this.ページ内表示行数 == nullであった場合、
        //　@　@　@　@　@　@「ページ内表示行数がnull」の例外をスローする。
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }

        //　@１２-２）this.ページ内表示行数が数字以外の値であった場合、
        //　@　@　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }

        //　@１２-３）this.ページ内表示行数 <= 0であった場合、
        //　@　@　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminEqAttentionInfoRefRefResponse(this);
    }
}
@
