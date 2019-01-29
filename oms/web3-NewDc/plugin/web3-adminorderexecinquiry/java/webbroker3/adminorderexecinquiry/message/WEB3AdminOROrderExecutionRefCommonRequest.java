head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.42.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOROrderExecutionRefCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・注文約定照会共通リクエスト (WEB3AdminOROrderExecutionRefCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/20 黄　@浩澎(中訊) モデル変更点092
Revesion History : 2007/02/14 金　@　@傑(中訊) モデル変更点091	
Revesion History : 2007/06/05 柴双紅(中訊) モデル変更点099
Revesion History : 2007/07/01 張騰宇(中訊) モデル変更点101
Revesion History : 2008/10/02 大澤(SRA) 【管理者注文約定照会】仕様変更管理台帳（モデル）No.130
*/
package webbroker3.adminorderexecinquiry.message;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.adminorderexecinquiry.define.WEB3AdminExecTypeDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminProductDivDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminTradingTypeDef;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.equity.define.WEB3EquityExecStatusTypeDef;

/**
 * (管理者・注文約定照会共通リクエスト)<BR>
 * <BR>
 * 管理者・注文約定照会共通リクエストクラス<BR>
 * <BR>
 * WEB3AdminOROrderExecutionRefCommonRequest<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public abstract class WEB3AdminOROrderExecutionRefCommonRequest extends WEB3GenRequest
{
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (注文ID)<BR>
     * <BR>
     * 注文ID<BR>
     * <BR>
     * orderId<BR>
     * <BR>
     */
    public String orderId = null;

    /**
     * (部店コード)<BR>
     * <BR>
     * 部店コードの配列<BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している<BR>
     * 　@取扱可能部店コード一覧がセットされる。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * branchCode<BR>
     * <BR>
     * An array of branchCode<BR>
     * <BR>
     * ※The handling possible branchCodeList held by PR layer is set when branchCode
     * is not input.<BR>
     * <BR>
     */
    public String[] branchCode;

    /**
     * (発注日)<BR>
     * <BR>
     * 発注日<BR>
     * <BR>
     * orderBizDate<BR>
     * <BR>
     */
    public Date orderBizDate = null;

    /**
     * (顧客コード)<BR>
     * <BR>
     * 顧客コード<BR>
     * <BR>
     * accountCode<BR>
     * <BR>
     */
    public String accountCode = null;
    
    /**
     * (扱者コード(SONAR))<BR>
     * <BR>
     * 顧客の扱者コード(SONAR)<BR>
     * <BR>
     */
    public String sonarTraderCode = null;

    /**
     * (商品区分)<BR>
     * <BR>
     * 商品区分<BR>
     * <BR>
     * 0：　@現物株式<BR>
     * 1：　@信用取引<BR>
     * 2：　@株式ミニ投資<BR>
     * 3：　@オプション<BR>
     * 4：　@先物<BR>
     * 5：　@投信<BR>
     * 6：　@累投<BR>
     * 7：　@外国株式<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * productDiv<BR>
     * <BR>
     * 0：　@Def.EQUITY<BR>
     * 1：　@Def.MARGIN<BR>
     * 2：　@Def.MINI_STOCK<BR>
     * 3：　@Def.OPTION<BR>
     * 4：　@Def.FUTURE<BR>
     * 5：　@Def.MF<BR>
     * 6：　@Def.RUITO<BR>
     * 7：　@Def.FEQ<BR>
     * <BR>
     */
    public String productDiv = null;

    /**
     * (取引区分)<BR>
     * <BR>
     * 取引区分<BR>
     * <BR>
     * 1：　@現物買付注文<BR>
     * 2：　@現物売付注文<BR>
     * 3：　@新規買建注文<BR>
     * 4：　@新規売建注文<BR>
     * 5：　@買建返済注文<BR>
     * 6：　@売建返済注文<BR>
     * 7：　@現引注文<BR>
     * 8：　@現渡注文<BR>
     * 99：　@立会外分売<BR>
     * 101：　@ミニ株買付注文<BR>
     * 102：　@ミニ株売付注文<BR>
     * 201：　@投資信託買付注文<BR>
     * 202：　@投資信託売付注文<BR>
     * 203：　@投資信託募集注文<BR>
     * 204：　@投資信託乗換注文<BR>
     * 501：　@累投買付注文<BR>
     * 502：　@累投売付注文<BR>
     * 601：　@指数先物新規買建注文<BR>
     * 602：　@指数先物新規売建注文<BR>
     * 603：　@指数先物売建返済注文<BR>
     * 604：　@指数先物買建返済注文<BR>
     * 605：　@指数オプション新規買建注文<BR>
     * 606：　@指数オプション新規売建注文<BR>
     * 607：　@指数オプション売建返済注文<BR>
     * 608：　@指数オプション買建返済注文<BR>
     * 701：　@外国株式買付<BR>
     * 702：　@外国株式売付<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * tradingType<BR>
     * 1: Def.EQUITY_BUY<BR>
     * 2: Def.EQUITY_SELL<BR>
     * 3: Def.MARGIN_LONG<BR>
     * 4: Def.MARGIN_SHORT<BR>
     * 5: Def.CLOSE_MARGIN_LONG<BR>
     * 6: Def.CLOSE_MARGIN_SHORT<BR>
     * 7: Def.SWAP_MARGIN_LONG<BR>
     * 8: Def.SWAP_MARGIN_SHORT<BR>
     * 99: Def.SALES_OUTSIDE_MARKET <BR>
     * 101: Def.MINI_STOCK_BUY<BR>
     * 102: Def.MINI_STOCK_SELL<BR>
     * 201: Def.MF_BUY<BR>
     * 202: Def.MF_SELL<BR>
     * 203: Def.MF_RECRUIT<BR>
     * 204: Def.MF_SWITCHING<BR>
     * 501: Def.RUITO_BUY<BR>
     * 502: Def.RUITO_SELL<BR>
     * 601: Def.IDX_FUTURES_BUY_TO_OPEN<BR>
     * 602: Def.IDX_FUTURES_SELL_TO_OPEN<BR>
     * 603: Def.IDX_FUTURES_BUY_TO_CLOSE<BR>
     * 604: Def.IDX_FUTURES_SELL_TO_CLOSE<BR>
     * 605: Def.IDX_OPTIONS_BUY_TO_OPEN<BR>
     * 606: Def.IDX_OPTIONS_SELL_TO_OPEN<BR>
     * 607: Def.IDX_OPTIONS_BUY_TO_CLOSE<BR>
     * 608: Def.IDX_OPTIONS_SELL_TO_CLOSE<BR>
     * 701：Def.FEQ_BUY<BR>
     * 702：Def.FEQ_SELL<BR>
     * <BR>
     */
    public String tradingType = null;

    /**
     * (執行条件)<BR>
     * <BR>
     * 執行条件<BR>
     * <BR>
     * 1：　@無条件<BR>
     * 3：　@寄付<BR>
     * 4：　@引け<BR>
     * 7：　@不出来引け成行<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * execCondType<BR>
     * <BR>
     * 1: Def.NO_CONDITION<BR>
     * 3: Def.AT_MARKET_OPEN<BR>
     * 4: Def.AT_MARKET_CLOSE<BR>
     * 7: Def.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * <BR>
     */
    public String execCondType = null;

    /**
     * (注文期限区分)<BR>
     * <BR>
     * 注文期限区分<BR>
     * <BR>
     * 1：　@当日限り<BR>
     * 2：　@出来るまで注文<BR>
     * 3：　@夕場まで注文<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * expirationDateType<BR>
     * <BR>
     * 1: Def.DAY_LIMIT<BR>
     * 2: Def.CARRIED_ORDER<BR>
     * <BR>
     */
    public String expirationDateType = null;

    /**
     * (発注条件区分)<BR>
     * <BR>
     * 発注条件区分<BR>
     * <BR>
     * 0：　@指定なし<BR>
     * 1：　@逆指値<BR>
     * 2：　@W指値<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderCondType<BR>
     * <BR>
     * 0: Def.DEFAULT<BR>
     * 1: Def.STOP_LIMIT_PRICE <BR>
     * 2: Def.W_LIMIT_PRICE<BR>
     * <BR>
     */
    public String orderCondType = null;

    /**
     * (注文経路区分)<BR>
     * <BR>
     * 注文経路区分<BR>
     * <BR>
     * 1：　@コールセンター<BR>
     * 2：　@ＰＣ<BR>
     * 3：　@スリングショット<BR>
     * 4：　@i-mode<BR>
     * 5：　@Vodafone<BR>
     * 6：　@AU<BR>
     * 9：　@HOST<BR>
     * A：　@管理者<BR>
     * C：　@リッチクライアント<BR>
     * F：　@IVR
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderRootDiv<BR>
     * <BR>
     * 1: Def.CALLCENTER<BR>
     * 2: Def.PC <BR>
     * 3: Def.SLINGSHOT<BR>
     * 4: Def.I_MODE<BR>
     * 5: Def.VODAFONE<BR>
     * 6: Def.AU<BR>
     * 9: Def.HOST<BR>
     * A: Def.ADMIN<BR>
     * <BR>
     */
    public String orderRootDiv = null;

    /**
     * (注文状態区分)<BR>
     * <BR>
     * 注文状態区分<BR>
     * <BR>
     * 0：　@その他<BR>
     * 1：　@受付済（新規注文）<BR>
     * 2：　@発注中（新規注文）<BR>
     * 3：　@発注済（新規注文）<BR>
     * 6：　@発注失敗（新規注文）<BR>
     * 7：　@受付済（変更注文）<BR>
     * 8：　@発注中（変更注文）<BR>
     * 10：　@発注済（変更注文）<BR>
     * 11：　@発注失敗（変更注文）<BR>
     * 12：　@受付済（取消注文）<BR>
     * 13：　@発注中（取消注文）<BR>
     * 14：　@発注済（取消注文）<BR>
     * 15：　@発注失敗（取消注文）<BR>
     * 20：　@一部失効<BR>
     * 21：　@全部失効<BR>
     * 22：　@無効<BR>
     * 50：　@繰越済<BR>
     * 51：　@繰越失敗<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderState<BR>
     * <BR>
     * 0: Def.UNDEFINED<BR>
     * 1: Def.ACCEPTED<BR>
     * 2: Def.ORDERING<BR>
     * 3: Def.ORDERED<BR>
     * 6: Def.NOT_ORDERED<BR>
     * 7: Def.MODIFY_ACCEPTED）<BR>
     * 8: Def.MODIFYING<BR>
     * 10: Def.MODIFIED<BR>
     * 11: Def.NOT_MODIFIED<BR>
     * 12: Def.CANCEL_ACCEPTED<BR>
     * 13: Def.CANCELLING<BR>
     * 14: Def.CANCELLED<BR>
     * 15: Def.NOT_CANCELLED<BR>
     * 20: Def.PART_INAFFECTED<BR>
     * 21: Def.FULL_INAFFECTED<BR>
     * 22: Def.CLOSED<BR>
     * 50: Def.TRANSFERED<BR>
     * 51: Def.NOT_TRANSFERED<BR>
     * <BR>
     */
    public String orderState = null;

    /**
     * (約定状態区分)<BR>
     * <BR>
     * 約定状態区分<BR>
     * <BR>
     * 0：　@未約定<BR>
     * 1：　@一部成立<BR>
     * 2：　@全部成立<BR>
     * 3：　@約定処理中(一部成立)<BR>
     * 4：　@約定処理中(全部成立)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * execType<BR>
     * 0: Def.EXEC_TYPE_NOT_PROMISE<BR>
     * 1: Def.EXEC_TYPE_ONE_COMPLETE<BR>
     * 2: Def.EXEC_TYPE_ALL_COMPLETE<BR>
     * 3: Def.EXEC_PROCESSING_ONE_COMPLETE<BR>
     * 4: Def.EXEC_PROCESSING_ALL_COMPLETE<BR>
     * <BR>
     */
    public String execType = null;

    /**
     * (訂正取消区分)<BR>
     * <BR>
     * 訂正取消区分<BR>
     * <BR>
     * 0：　@初期値<BR>
     * 1：　@取消中<BR>
     * 2：　@一部取消完了<BR>
     * 3：　@全部取消完了<BR>
     * 4：　@取消失敗<BR>
     * 5：　@訂正中<BR>
     * 6：　@一部訂正完了<BR>
     * 7：　@全部訂正完了<BR>
     * 8：　@訂正失敗<BR>
     * 9：　@エラー<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * changeCancelDiv<BR>
     * 0: Def.INITIAL_VALUE<BR>
     * 1: Def.CANCELING<BR>
     * 2: Def.PART_CANCELED<BR>
     * 3: Def.CANCELED<BR>
     * 4: Def.CANCEL_ERROR<BR>
     * 5: Def.CHANGING<BR>
     * 6: Def.PARTIALLY_CHANGED<BR>
     * 7: Def.CHANGED<BR>
     * 8: Def.CHANGE_ERROR<BR>
     * 9: Def.ERROR<BR>
     * <BR
     */
    public String changeCancelDiv = null;

    /**
     * (注文時間From)<BR>
     * <BR>
     * 注文時間From<BR>
     * (YYYYMMDDhhmm)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderStartDate<BR>
     * (YYYYMMDDhhmm)<BR>
     * <BR>
     */
    public String orderStartDate = null;

    /**
     * (注文時間To)<BR>
     * <BR>
     * 注文時間To<BR>
     * (YYYYMMDDhhmm)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderEndDate<BR>
     * (YYYYMMDDhhmm)<BR>
     * <BR>
     */
    public String orderEndDate = null;

    /**
     * (受渡日)<BR>
     * <BR>
     * 受渡日<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * deliveryDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     */
    public String deliveryDate = null;

    /**
     * (要求ページ番号)<BR>
     * <BR>
     * 要求ページ番号<BR>
     * <BR>
     * 表示させたいページ位置を指定　@※先頭ページを"1"とする<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * pageIndex<BR>
     * <BR>
     * Specify a page position to be displayed . Let the first page index 1<BR>
     * <BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * <BR>
     * ページ内表示行数<BR>
     * <BR>
     * １ページ内に表示させたい行数を指定<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * pageSize<BR>
     * <BR>
     * Specify the line number to be displayed in one page<BR>
     * <BR>
     */
    public String pageSize;

    /**
     * （ソートキー）<BR>
     * <BR>
     */
    public WEB3AdminOROrderExecutionSortKeyUnit[] sortKeys;

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOROrderExecutionRefCommonRequest.class);

    /**
     * @@roseuid 4212FB230269
     */
    public WEB3AdminOROrderExecutionRefCommonRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）注文IDチェック<BR>
     * 　@this.注文ID != nullの場合、以下のチェックを行う。<BR>
     * 　@１－１）this.注文IDが数字以外の場合、<BR>
     * 　@　@　@　@　@「注文IDが数字以外」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01476<BR>
     * <BR>
     * ２）部店コードチェック<BR>
     * 　@２－１）this.部店コード == nullの場合、<BR>
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@２－２）this.部店コード.length == 0の場合、<BR>
     * 　@　@　@　@　@「部店コードの要素数が0」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01757<BR>
     * <BR>
     * 　@２－３）this.部店コードの要素数分以下の処理を行う。<BR >
     * 　@　@２－３－１）this.部店コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@　@　@「部店コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@・部店コード != 数字 <BR>
     * 　@　@　@　@　@　@　@・部店コード.length != 3 <BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * ３）顧客コードチェック<BR>
     * 　@this.顧客コード != nullの場合、以下のチェックを行う。<BR>
     * 　@３－１）this.顧客コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@「顧客コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@・顧客コード != 数字<BR>
     * 　@　@　@　@・顧客コード.length != 6<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00780<BR>
     * <BR>
     * ４）扱者コード（SONAR）チェック<BR>
     * 　@this.扱者コード（SONAR） != null かつ this.扱者コード（SONAR）.length > 5の場合、<BR>
     * 　@「扱者コード（SONAR）エラー」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01912<BR>
     * <BR>
     * ５）商品区分チェック<BR>
     * 　@this.商品区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@５－１）this.商品区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「商品区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"現物株式"<BR>
     * 　@　@　@　@・"信用取引"<BR>
     * 　@　@　@　@・"株式ミニ投資"<BR>
     * 　@　@　@　@・"オプション"<BR>
     * 　@　@　@　@・"先物"<BR>
     * 　@　@　@　@・"投信"<BR>
     * 　@　@　@　@・"累投"<BR>
     * 　@　@　@　@・"外国株式"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01068<BR>
     * <BR>
     * ６）取引区分チェック<BR>
     * 　@this.取引区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@６－１）this.取引区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「取引区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"現物買付注文"<BR>
     * 　@　@　@　@・"現物売付注文"<BR>
     * 　@　@　@　@・"新規買建注文"<BR>
     * 　@　@　@　@・"新規売建注文"<BR>
     * 　@　@　@　@・"買建返済注文"<BR>
     * 　@　@　@　@・"売建返済注文"<BR>
     * 　@　@　@　@・"現引注文"<BR>
     * 　@　@　@　@・"現渡注文"<BR>
     * 　@　@　@　@・"立会外分売"<BR>
     * 　@　@　@　@・"ミニ株買付注文"<BR>
     * 　@　@　@　@・"ミニ株売付注文"<BR>
     * 　@　@　@　@・"投資信託買付注文"<BR>
     * 　@　@　@　@・"投資信託売付注文"<BR>
     * 　@　@　@　@・"投資信託募集注文"<BR>
     * 　@　@　@　@・"投資信託乗換注文"<BR>    
     * 　@　@　@　@・"累投買付注文"<BR>
     * 　@　@　@　@・"累投売付注文"<BR>
     * 　@　@　@　@・"指数先物新規買建注文"<BR>
     * 　@　@　@　@・"指数先物新規売建注文"<BR>
     * 　@　@　@　@・"指数先物売建返済注文"<BR>
     * 　@　@　@　@・"指数先物買建返済注文"<BR>
     * 　@　@　@　@・"指数オプション新規買建注文"<BR>
     * 　@　@　@　@・"指数オプション新規売建注文"<BR>
     * 　@　@　@　@・"指数オプション売建返済注文"<BR>
     * 　@　@　@　@・"指数オプション買建返済注文"<BR>
     * 　@　@　@　@・"外国株式買付"<BR>
     * 　@　@　@　@・"外国株式売付"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00602<BR>
     * <BR>
     * ７）執行条件チェック<BR>
     * 　@this.執行条件 != nullの場合、以下のチェックを行う。<BR>
     * 　@７－１）this.執行条件に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「執行条件が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"無条件"<BR>
     * 　@　@　@　@・"寄付"<BR>
     * 　@　@　@　@・"引け"<BR>
     * 　@　@　@　@・"不出来引け成行"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00127<BR>
     * <BR>
     * ８）注文期限区分チェック<BR>
     * 　@this.注文期限区分 != nullの場合、以下のチェックを行う<BR>
     * 　@８－１）this.注文期限区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「注文期限区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"当日限り"<BR>
     * 　@　@　@　@・"出来るまで注文"<BR>
     * 　@　@　@　@・"夕場まで注文"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00209<BR>
     * <BR>
     * ９）発注条件区分チェック<BR>
     * 　@this.発注条件区分 != nullの場合、以下のチェックを行う<BR>
     * 　@９－１）this.発注条件区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「発注条件区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"指定なし"<BR>
     * 　@　@　@　@・"逆指値"<BR>
     * 　@　@　@　@・"W指値"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00212<BR>
     * <BR>
     * １０）注文経路区分チェック<BR>
     * 　@this.注文経路区分 != nullの場合、以下のチェックを行う<BR>
     * 　@１０－１）this.注文経路区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「注文経路区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"コールセンター"<BR>
     * 　@　@　@　@・"ＰＣ"<BR>
     * 　@　@　@　@・"スリングショット"<BR>
     * 　@　@　@　@・"i-mode"<BR>
     * 　@　@　@　@・"Vodafone"<BR>
     * 　@　@　@　@・"AU"<BR>
     * 　@　@　@　@・"HOST"<BR>
     * 　@　@　@　@・"管理者"<BR>
     * 　@　@　@　@・"リッチクライアント"<BR>
     * 　@　@　@　@・"IVR"<BR>
     * 　@　@　@　@・"強制決済"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01472<BR>
     * <BR>
     * １１）注文状態区分チェック<BR>
     * 　@this.注文状態区分 != nullの場合、以下のチェックを行う<BR>
     * 　@１１－１）this.注文状態区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「注文状態区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"その他"<BR>
     * 　@　@　@　@・"受付済（新規注文）" <BR>
     * 　@　@　@　@・"発注中（新規注文）" <BR>
     * 　@　@　@　@・"発注済（新規注文）" <BR>
     * 　@　@　@　@・"発注失敗（新規注文）" <BR>
     * 　@　@　@　@・"受付済（変更注文）"<BR>
     * 　@　@　@　@・"発注中（変更注文）"<BR>
     * 　@　@　@　@・"発注済（変更注文）"<BR>
     * 　@　@　@　@・"発注失敗（変更注文）"<BR>
     * 　@　@　@　@・"受付済（取消注文）"<BR>
     * 　@　@　@　@・"発注中（取消注文）"<BR>
     * 　@　@　@　@・"発注済（取消注文）"<BR>
     * 　@　@　@　@・"発注失敗（取消注文）"<BR>
     * 　@　@　@　@・"一部失効"<BR>
     * 　@　@　@　@・"全部失効"<BR>
     * 　@　@　@　@・"無効"<BR>
     * 　@　@　@　@・"手動失効"<BR>
     * 　@　@　@　@・"繰越済"<BR>
     * 　@　@　@　@・"繰越失敗"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01477<BR>
     * <BR>
     * １２）約定状態区分チェック<BR>
     * 　@this.約定状態区分 != nullの場合、以下のチェックを行う<BR>
     * 　@１２－１）this.約定状態区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「約定状態区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"未約定"<BR>
     * 　@　@　@　@・"一部成立"<BR>
     * 　@　@　@　@・"全部成立"<BR>
     * 　@　@　@　@・"約定処理中(一部成立)"<BR> 
     * 　@　@　@　@・"約定処理中(全部成立)"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00626<BR>
     * <BR>
     * １３）訂正取消区分チェック<BR>
     * 　@this.訂正取消区分 != nullの場合、以下のチェックを行う<BR>
     * 　@１３－１）this.訂正取消区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「訂正取消区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"初期値"<BR>
     * 　@　@　@　@・"取消中"<BR>
     * 　@　@　@　@・"一部取消完了"<BR>
     * 　@　@　@　@・"全部取消完了"<BR>
     * 　@　@　@　@・"取消失敗"<BR>
     * 　@　@　@　@・"訂正中"<BR>
     * 　@　@　@　@・"一部訂正完了"<BR>
     * 　@　@　@　@・"全部訂正完了"<BR>
     * 　@　@　@　@・"訂正失敗"<BR>
     * 　@　@　@　@・"エラー"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01478<BR>
     * <BR>
     * １４）注文時間Fromチェック<BR>
     * 　@this.注文時間From != nullの場合、以下のチェックを行う。<BR>
     * 　@１４－１）this.注文時間FromをDate型に変換できなかった場合、<BR>
     * 　@　@　@　@　@「入力時間エラー(注文時間From)」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01479<BR>
     * <BR>
     * １５）注文時間Toチェック<BR>
     * 　@this.注文時間To != nullの場合、以下のチェックを行う。<BR>
     * 　@１５－１）this.注文時間ToをDate型に変換できなかった場合、<BR>
     * 　@　@　@　@　@「入力時間エラー(注文時間To)」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01480<BR>
     * <BR>
     * １６）注文時間From/To整合性チェック<BR>
     * 　@this.注文時間From != null かつ
     * this.注文時間Toの場合、以下のチェックを行う。<BR>
     * 　@１６－１）this.注文時間From > this.注文時間Toの場合、<BR>
     * 　@　@　@　@　@「入力時間整合性エラー)」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01481<BR>
     * <BR>
     * １７）受渡日チェック<BR>
     * 　@this.受渡日 != nullの場合、以下のチェックを行う。<BR>
     * 　@１７－１）this.受渡日をDate型に変換できなかった場合、<BR>
     * 　@　@　@　@　@「入力時間エラー(受渡日)」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01482<BR>
     * <BR>
     * １８）　@ソートキーチェック <BR>
     * 　@１８－１）this.ソートキー == nullであった場合 <BR>
     * 　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@１８－２）this.ソートキー.length == 0だった場合<BR>
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@１８－３）this.ソートキーの全要素に対して <BR>
     * 　@　@　@　@下記のチェックを行う。<BR>
     * 　@　@１８－３－１）ソートキー.validate()をコールする。<BR>
     * <BR>
     * １９）要求ページ番号チェック <BR>
     * 　@１９－１）this.要求ページ番号 == nullであった場合、<BR>
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@１９－２）this.要求ページ番号が数字以外の値であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@１９－３）this.要求ページ番号 <= 0であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00616<BR>
     * <BR>
     * ２０）ページ内表示行数チェック<BR>
     * 　@２０－１）this.ページ内表示行数 == nullであった場合、<BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00091<BR>
     * <BR>
     * 　@２０－２）this.ページ内表示行数が数字以外の値であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。<BR>
     * 　@<BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00092<BR>
     * <BR>
     * 　@２０－３）this.ページ内表示行数 <= 0であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00617<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * 1)orderId check<BR>
     * 　@Check the following if this.orderId != null<BR>
     * 　@1-1)If this.orderId != numerical numeber<BR>
     * 　@　@　@　@　@Throw the exception "orderId is not a numerical value"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01476<BR>
     * <BR>
     * 2）branchCode check<BR>
     * 　@2-1)If this.branchCode== null<BR>
     * 　@　@　@　@　@Throw the exception "branchCode is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@2-2)If this.branchCode.length == 0,<BR>
     * 　@　@　@　@　@Throw the exception "The number of the elements of branchCode is
     * 0"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01757<BR>
     * <BR>
     * 　@2-3)Loop for as many times as the number of the elements of
     * this.branchCode<BR>
     * 　@　@2-3-1)If this.branchCode meets with the following conditions<BR>
     * 　@　@　@　@　@　@Throw the exception "branchCode error"<BR>
     * 　@　@　@　@　@　@・this.branchCode != numerical number<BR>
     * 　@　@　@　@　@　@・this.branchCode.length != 3<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * 3)accountCode check<BR>
     * 　@Check the followings if this.accountCode != null<BR>
     * 　@3-1)If this.accountCode meets with the following conditions<BR>
     * 　@　@　@　@　@　@Throw the exception "accountCode error"<BR>
     * 　@　@　@　@　@　@・this.accountCode != numerical value<BR>
     * 　@　@　@　@　@　@・this.accountCode.length != 6<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00780<BR>
     * <BR>
     * 4)productDiv check<BR>
     * 　@Check the followings if this.productDiv != null<BR>
     * 　@4-1)If this.productDiv contains values other than the followings<BR>
     * 　@　@　@　@　@Throw the exception "productDiv has an undefined value"<BR>
     * 　@　@　@　@・ Def.EQUITY<BR>
     * 　@　@　@　@・ Def.MARGIN<BR>
     *         ・ Def.MINI_STOCK<BR>
     *         ・ Def.OPTION<BR>
     *         ・ Def.FUTURE<BR>
     *         ・ Def.MF<BR>
     *         ・ Def.RUITO<BR>
     *         ・ Def.FEQ<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01068<BR>
     * <BR>
     * 5)tradingType check<BR>
     * 　@Check the followings if this.tradingType!= null<BR>
     * 　@5-1)If this.tradingType contains values other than the followings<BR>
     * 　@　@　@　@　@Throw the exception "tradingType has an undefined value"<BR>
     * 　@　@　@　@・Def.EQUITY_BUY<BR>
     * 　@　@　@　@・Def.EQUITY_SELL<BR>
     * 　@　@　@　@・Def.MARGIN_LONG<BR>
     * 　@　@　@　@・Def.MARGIN_SHORT<BR>
     * 　@　@　@　@・Def.CLOSE_MARGIN_LONG<BR>
     * 　@　@　@　@・Def.CLOSE_MARGIN_SHORT<BR>
     * 　@　@　@　@・Def.SWAP_MARGIN_LONG<BR>
     * 　@　@　@　@・Def.SWAP_MARGIN_SHORT<BR>
     * 　@　@　@　@・Def.SALES_OUTSIDE_MARKET<BR>
     * 　@　@　@　@・Def.MINI_STOCK_BUY<BR>
     * 　@　@　@　@・Def.MINI_STOCK_SELL<BR>
     * 　@　@　@　@・Def.MF_BUY<BR>
     * 　@　@　@　@・Def.MF_SELL<BR>
     * 　@　@　@　@・Def.RUITO_BUY<BR>
     * 　@　@　@　@・Def.RUITO_SELL<BR>
     * 　@　@　@　@・Def.IDX_FUTURES_BUY_TO_OPEN<BR>
     * 　@　@　@　@・Def.IDX_FUTURES_SELL_TO_OPEN<BR>
     * 　@　@　@　@・Def.IDX_FUTURES_BUY_TO_CLOSE<BR>
     * 　@　@　@　@・Def.IDX_FUTURES_SELL_TO_CLOSE<BR>
     * 　@　@　@　@・Def.IDX_OPTIONS_BUY_TO_OPEN<BR>
     * 　@　@　@　@・Def.IDX_OPTIONS_SELL_TO_OPEN<BR>
     * 　@　@　@　@・Def.IDX_OPTIONS_BUY_TO_CLOSE<BR>
     * 　@　@　@　@・Def.IDX_OPTIONS_SELL_TO_CLOSE<BR>
     * 　@　@　@　@・Def.FEQ_BUY<BR>
     * 　@　@　@　@・Def.FEQ_SELL<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00602<BR>
     * <BR>
     * 6)execCondType check<BR>
     * 　@Check the followings if this.execCondType!= null<BR>
     * 　@6-1)If this.execCondType contains values other than the followings<BR>
     * 　@　@　@　@　@Throw the exception "execCondType has an undefined value"<BR>
     * 　@　@　@  ・ Def.NO_CONDITION<BR>
     * 　@      ・ Def.AT_MARKET_OPEN<BR>
     * 　@      ・ Def.AT_MARKET_CLOSE<BR>
     * 　@      ・ Def.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00127<BR>
     * <BR>
     * 7)expirationDateType check<BR>
     * 　@Check the followings if this.expirationDateType!= null<BR>
     * 　@7-1)If this.expirationDate contains values other than the followings<BR>
     * 　@　@　@　@Throw the excetion "expirationDateType has an undefined value"<BR>
     * 　@　@　@ ・Def.DAY_LIMIT<BR>
     *        ・Def.CARRIED_ORDER<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00209<BR>
     * <BR>
     * 8)orderCondType check<BR>
     * 　@　@Check the followings if this.orderCondType!= null<BR>
     * 　@8-1)If this.orderCondType contains values other than the followings,<BR>
     * 　@　@　@　@Throw the excetion "orderCondType has an undefined value"<BR>
     * 　@　@　@ ・Def.DEFAULT<BR>
     *        ・Def.STOP_LIMIT_PRICE <BR>
     *        ・Def.W_LIMIT_PRICE<BR>>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00212<BR>
     * <BR>
     * 9)orderRootDiv check<BR>
     * 　@Check the followings if this.orderRootDiv!= null<BR>
     * 　@9-1)If this.orderRootDiv contains values other than the followings<BR>
     * 　@　@　@　@Throw the exception "orderRootDiv has an undefined value"<BR>
     *        ・ Def.CALLCENTER<BR>
     *        ・ Def.PC <BR>
     *        ・ Def.SLINGSHOT<BR>
     *        ・ Def.I_MODE<BR>
     *        ・ Def.VODAFONE<BR>
     *        ・ Def.AU<BR>
     *        ・ Def.HOST<BR>
     *        ・ Def.ADMIN<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01472<BR>
     * <BR>
     * 10)orderState check<BR>
     * 　@Check the followings if this.orderState!= null<BR>
     * 　@10-1)If this.orderState contains values other than the followings<BR>
     * Throw the excetion "orderState has an undefined value"<BR>
     * 　@　@　@ ・Def.UNDEFINED<BR>
     *         ・Def.ACCEPTED<BR>
     *         ・Def.ORDERING<BR>
     *         ・Def.ORDERED<BR>
     *         ・Def.NOT_ORDERED<BR>
     *         ・Def.MODIFY_ACCEPTED<BR>
     *         ・Def.MODIFYING<BR>
     *         ・Def.MODIFIED<BR>
     *         ・Def.NOT_MODIFIED<BR>
     *         ・Def.CANCEL_ACCEPTED<BR>
     *         ・Def.CANCELLING<BR>
     *         ・Def.CANCELLED<BR>
     *         ・Def.NOT_CANCELLED<BR>
     *         ・Def.PART_INAFFECTED<BR>
     *         ・Def.FULL_INAFFECTED<BR>
     *         ・Def.CLOSED<BR>
     *         ・Def.TRANSFERED<BR>
     *         ・Def.NOT_TRANSFERED<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01477<BR>
     * <BR>
     * 11)execType check<BR>
     * 　@Check the followings if this.execType!= null<BR>
     * 　@11-1)If this.execType contains values other than the followings<BR>
     * Throw the exception "execType has an undefined value"<BR>
     * 　@　@　@　@・Def.EXEC_TYPE_NOT_PROMISE<BR>
     *         ・Def.EXEC_TYPE_ONE_COMPLETE<BR>
     *         ・Def.EXEC_TYPE_ALL_COMPLETE<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00626<BR>
     * <BR>
     * 12)changeCancelDiv check<BR>
     * 　@Check the followings if this.changeCancelDiv!= null<BR>
     * 　@12-1)If this.changeCancelDivis contains values other than the followings<BR>
     * Throw the exception "changeCancelDiv has an undefined value"<BR>
     * 　@　@　@　@・Def.INITIAL_VALUE<BR>
     *         ・Def.CANCELING<BR>
     *         ・Def.PART_CANCELED<BR>
     *         ・Def.CANCELED<BR>
     *         ・Def.CANCEL_ERROR <BR>
     *         ・Def.CHANGING<BR>
     *         ・Def.PARTIALLY_CHANGED<BR>
     *         ・Def.CHANGE_ERROR<BR>
     *         ・Def.ERROR<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01478<BR>
     * <BR>
     * １3)orderStartDate check<BR>
     * 　@Cehck the followings if this.orderStartDate !=null<BR>
     * 　@13-1)If it is unable to convert this.orderStartDate to Date type<BR>
     * 　@　@　@　@　@Throw the excetion "invalid input date(orderStartDate)"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01479<BR>
     * <BR>
     * 14)orderEndDate check<BR>
     * 　@Check the followng if this.orderEndDate != null<BR>
     * 　@14-1)If it is unable to convert this.orderEndDate to Date type,<BR>
     * 　@　@　@　@　@Throw the exception "invalid input date(orderEndDate)"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01480<BR>
     * <BR>
     * 15)orderStartDate / orderEndDate integrity check<BR>
     * 　@Check the following if this.orderStartDate != null and this.orderEndDateTo !=
     * null,<BR>
     * 　@15-1)If this.orderStartDate > this.orderEndDateTo<BR>
     * 　@　@　@　@　@Throw the excetion "input date integrity error"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01481<BR>
     * <BR>
     * 16)deliveryDate check <BR>
     * 　@Check the following if this.deliveryDate != null<BR>
     * 　@16-1)If it is unable to convert this.deliveryDate to Date type<BR>
     * 　@　@　@　@　@Throw the exception "invalid input date(deliveryDate)"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01482<BR>
     * <BR>
     * 17)sortKey Check <BR>
     * 　@17-1)If this.sortKeys == null<BR>
     * 　@　@　@　@Throw the exception "sortKeys is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@17-2)If this.sortKeys.length == null<BR>
     * 　@　@　@　@Throw the exception "The number of the elements of sortKeys.length is
     * 0"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@17-3)Check the following for all the this.sortKeys elements<BR>
     * 　@　@17-3-1)Call sortKeys.Validate()<BR>
     * <BR>
     * 18)pageIndex check <BR>
     * 　@18-1)If this.pageIndex == null<BR>
     * 　@　@　@　@Throw the exception "pageIndex is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@18-2)If this.pageIndex is not a numerical value,<BR>
     * 　@　@　@　@Throw the exception "pageIndex is not a numerical value"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@18-3)If this.pageIndex <= 0<BR>
     * 　@　@　@　@Throw the exception "pageIndex is less than 0"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00616<BR>
     * <BR>
     * 19)pageSize check <BR>
     * 　@19-1)If this.pageSize == null<BR>
     * 　@　@　@　@Throw the exception "pageSize is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00091<BR>
     * <BR>
     * 　@19-2)If this.pageSize is not a numerical value<BR>
     * 　@　@　@　@Throw the exception "pageSize is not a numerical value"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00092<BR>
     * <BR>
     * 　@19-3)If this.pageSize <= 0<BR>
     * 　@　@　@　@Throw the exception "pageSize is less than 0"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41A5B3CB0168
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        final int l_intSix = 6;
        final int l_intFive = 5;
        final int l_intThree = 3;
        int l_intSortKeyLength = 0;
        int l_intBranchCodeLength = 0;

        // 1-1 if orderId != null ,then if orderId in not number throw exception
        if (this.orderId != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.orderId))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01476,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-1 If branchCode== null throw exception
        if (this.branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 2-2 If branchCode.length = 0, throw exception
            l_intBranchCodeLength = branchCode.length;
            if (l_intBranchCodeLength == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                // 2-3 If branchCode is Not a Number & its length not equal to 3, throw Exception
                for (int i = 0; i < l_intBranchCodeLength; i++)
                {
                    if ((!WEB3StringTypeUtility.isNumber(this.branchCode[i]))
                        || (WEB3StringTypeUtility.getByteLength(this.branchCode[i]) != l_intThree))
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
                    }
                }
            }
        }

        // 3-1 If accountCode is Not a Number & its length not equal to 6, throw Exception
        if (this.accountCode != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(this.accountCode))
                || (WEB3StringTypeUtility.getByteLength(this.accountCode) != l_intSix))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 4-1 If sonarTraderCode's length is over than 5, throw Exception
        if (this.sonarTraderCode != null)
        {
            if ((WEB3StringTypeUtility.getByteLength(this.sonarTraderCode) > l_intFive))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01912,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 5-1 if productDiv is not of any Def values throw Exception
        if (this.productDiv != null)
        {
            if ((!WEB3AdminProductDivDef.EQUITY.equals(this.productDiv))
                && (!WEB3AdminProductDivDef.MARGIN.equals(this.productDiv))
                && (!WEB3AdminProductDivDef.MINI_STOCK.equals(this.productDiv))
                && (!WEB3AdminProductDivDef.OPTION.equals(this.productDiv))
                && (!WEB3AdminProductDivDef.FUTURE.equals(this.productDiv))
                && (!WEB3AdminProductDivDef.MF.equals(this.productDiv))
                && (!WEB3AdminProductDivDef.RUITO.equals(this.productDiv))
                && (!WEB3AdminProductDivDef.FEQ.equals(this.productDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 6-1 If tradingType is not of any of Def values, throw an Exception.
        if (this.tradingType != null)
        {
            if ((!String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.SWAP_MARGIN_LONG.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue()).equals(this.tradingType))
                && (!WEB3AdminTradingTypeDef.SALES_OUTSIDE_MARKET.equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MINI_STOCK_BUY.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MINI_STOCK_SELL.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MF_BUY.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MF_SELL.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MF_RECRUIT.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MF_SWITCHING.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.RUITO_BUY.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.RUITO_SELL.intValue()).equals(this.tradingType))                
                && (!String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.FEQ_BUY.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.FEQ_SELL.intValue()).equals(this.tradingType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00602,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 7-1 if execCondType is not any of Def values, throw Exception.
        if (this.execCondType != null)
        {
            if ((!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
                && (!WEB3ExecutionConditionDef.AT_MARKET_OPEN.equals(this.execCondType))
                && (!WEB3ExecutionConditionDef.AT_MARKET_CLOSE.equals(this.execCondType))
                && (!WEB3ExecutionConditionDef
                    .AT_MARKET_CLOSE_NOT_EXECUTED
                    .equals(this.execCondType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00127,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 8-1 if expirationDateType is not any of Def values throw Exception.
        if (this.expirationDateType != null)
        {
            if ((!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType))
                && (!WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType))
                && (!WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(this.expirationDateType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00209,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 9-1 if orderCondType is not any of Def values, throw Exception.
        if (this.orderCondType != null)
        {
            if ((!WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
                && (!WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType))
                && (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00212,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 10-1 if orderRootDiv is not any of Def values, throw Exception.
        if (this.orderRootDiv != null)
        {
            if ((!WEB3OrderRootDivDef.CALLCENTER.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.PC.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.SLINGSHOT.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.I_MODE.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.VODAFONE.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.AU.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.HOST.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.ADMIN.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.RICH_CLIENT.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.IVR.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.FORCED_SETTLE.equals(this.orderRootDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01472,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 11-1 if orderState is not any of Def values throw Exception.
        if (this.orderState != null)
        {
            if ((!WEB3OrderStatusDef.OTHER.equals(this.orderState))
                && (!WEB3OrderStatusDef.ACCEPTED_OPENORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFYING.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFYED.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFY_FAIL_OPENORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.ACCEPTED_CHANGEORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFYING_CHANGEORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFYED_CHANGEORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFY_FAIL_CHANGEORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.ACCEPTED_CANCELORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFYING_CANCELORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFYED_CANCELORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFY_FAIL_CANCELORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.PART_INAFFECTED.equals(this.orderState))
                && (!WEB3OrderStatusDef.FULL_INAFFECTED.equals(this.orderState))
                && (!WEB3OrderStatusDef.CLOSED.equals(this.orderState))
                && (!WEB3OrderStatusDef.MANUAL_EXPIRED.equals(this.orderState))
                && (!WEB3OrderStatusDef.TRANSFERED.equals(this.orderState))
                && (!WEB3OrderStatusDef.NOT_TRANSFERED.equals(this.orderState)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01477,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 12-1 if execType is not any of Def values throw Exception.
        if (this.execType != null)
        {
            if ((!WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE.equals(this.execType))
                && (!WEB3EquityExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(this.execType))
                && (!WEB3EquityExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(this.execType))
                && (!WEB3AdminExecTypeDef.EXEC_PROCESSING_ONE_COMPLETE.equals(this.execType))
                && (!WEB3AdminExecTypeDef.EXEC_PROCESSING_ALL_COMPLETE.equals(this.execType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00626,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 13-1 if  changeCancelDiv is not any of Def values throw Exception.
        if (this.changeCancelDiv != null)
        {
            if ((!WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.CANCELING.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.PART_CANCELED.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.CANCELED.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.CANCEL_ERROR.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.CHANGING.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.CHANGED.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.CHANGE_ERROR.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.ERROR.equals(this.changeCancelDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01478,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 14-1 If orderStartDate is unable to change to Date type throw exception
        if (this.orderStartDate != null)
        {
            if (!WEB3StringTypeUtility.isDateStr(this.orderStartDate, "yyyyMMddHHmm"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01479,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 15-1 If orderEndDate is unable to change  to Date type throw exception
        if (this.orderEndDate != null)
        {
            if (!WEB3StringTypeUtility.isDateStr(this.orderEndDate, "yyyyMMddHHmm"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01480,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 16-1 if applyStartDate > applyEndDate throw exception
        if ((orderStartDate != null) && (orderEndDate != null))
        {
            Date l_oredrStartDate = WEB3DateUtility.getDate(this.orderStartDate, "yyyyMMddHHmm");
            Date l_orderEndDate = WEB3DateUtility.getDate(this.orderEndDate, "yyyyMMddHHmm");

            int l_resultcompare = WEB3DateUtility.compare(l_oredrStartDate, l_orderEndDate);
            if (l_resultcompare == 1)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01481,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 17-1 If deliveryDate is unable to change  to Date type throw exception
        if (this.deliveryDate != null)
        {
            if (!WEB3StringTypeUtility.isDateStr(this.deliveryDate, "yyyyMMdd"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01482,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 18-1 if sortKeys is  null throw exception.
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            l_intSortKeyLength = sortKeys.length;
            if (l_intSortKeyLength == 0)
            {
                // 18-2 If this.sortKeys.length == null
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                // 18-3 Check the following for all the this.sortKeys elements
                for (int i = 0; i < l_intSortKeyLength; i++)
                {
                    sortKeys[i].validate();
                }
            }
        }

        // 19-1 if pageIndex  is not null throw exception.
        if (this.pageIndex == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 19-2 If this.pageIndex is not a numerical value, throw Exception.
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 19-3 If this.pageIndex <= 0, throw Exception.
        int l_intPageIndex = Integer.parseInt(this.pageIndex);
        if (l_intPageIndex <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 20-1 If this.pageSize = null, throw Exception.
        if (this.pageSize == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 20-2 If this.pageSize is not a numerical value, throw Exception.
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 20-3 If this.pageSize <= 0, throw Exception.
        int l_intPageSize = Integer.parseInt(pageSize);
        if (l_intPageSize <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public abstract WEB3GenResponse createResponse();
}
@
