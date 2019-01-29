head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecuteReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文約定照会リクエスト(WEB3EquityExecuteReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 張坤芳 (中訊) 新規作成
Revesion History : 2007/10/16 金傑(中訊) 仕様変更モデル1195
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityExecStatusTypeDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式注文約定照会リクエスト）。<BR>
 * <BR>
 * 現物株式注文約定照会要求　@リクエストデータクラス<BR>
 * <BR>
 * 現物株式：「注文約定照会」<BR>
 * 現物株式：「訂正取消一覧」の両画面で使用する。
 * @@version 1.0
 */
public class WEB3EquityExecuteReferenceRequest extends WEB3GenRequest
{

    /**
     * ログ出力。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityExecuteReferenceRequest.class);

    /**
     * (照会区分)<BR>
     * 0：注文約定照会<BR>
     * 1：訂正取消一覧（訂正取消可能なもののみ表示）
     */
    public String referenceType;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String productCode;

    /**
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;

    /**
     * (約定状態区分)<BR>
     * 画面初回表示時などデフォルトは「指定なし」<BR>
     * null:指定無し<BR>
     * 0:未約定　@1:一部成立　@2:全部成立
     */
    public String execType;

    /**
     * (発注日)<BR>
     * 発注日
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
     * 現物株式ソートキーの一覧<BR>
     * <BR>
     * 対象項目：銘柄ｺｰﾄﾞ、口座区分、市場、取引区分、値段条件、執行条件、<BR>
     * 　@　@　@　@　@発注条件、注文時間、発注日、注文期限
     */
    public WEB3EquitySortKey[] sortKeys;

    /**
     * (要求ページ番号)<BR>
     * 表示させたいページ位置を指定
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * １ページ内に表示させたい行数
     */
    public String pageSize;

    /**
     * PTYPE
     */
    public static final String PTYPE = "equity_exec_reference";

    /**
     * serialVersionUID
     */
    public static final long serialVersionUID = 200405121057L;

    /**
     * (現物株式注文約定照会リクエスト)
     * コンストラクタ<BR>
     * @@roseuid 40638FD400F3
     */
    public WEB3EquityExecuteReferenceRequest()
    {

    }

    /**
     * レスポンスデータを作成する。
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40638FB70383
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityExecuteReferenceResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）ソートキーのチェック<BR>
     * 　@１?１）this.ソートキー＝nullであった場合<BR>
     * 　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@１?２）this.ソートキー.要素数＝０だった場合<BR>
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@１?３）this.ソートキーの全要素に対して<BR>
     * 　@　@　@　@下記のチェックを行う。<BR>
     * 　@　@１?３?１）ソートキー.validate()をコールする。<BR>
     * <BR>
     * 　@　@１?３?２）ソートキーの配列の個数分、繰り返してチェックを行う。<BR>
     * 　@　@　@　@　@　@　@　@　@以下の項目名以外が存在した場合、例外とする。<BR>
     * 　@　@　@　@　@　@　@　@　@・（銘柄）コード<BR>
     * 　@　@　@　@　@　@　@　@　@・口座<BR>
     * 　@　@　@　@　@　@　@　@　@・市場<BR>
     * 　@　@　@　@　@　@　@　@　@・取引区分<BR>
     * 　@　@　@　@　@　@　@　@　@・値段条件<BR>
     * 　@　@　@　@　@　@　@　@　@・執行条件<BR>
     * 　@　@　@　@　@　@　@　@　@・発注条件<BR>
     * 　@　@　@　@　@　@　@　@　@・注文時間<BR>
     * 　@　@　@　@　@　@　@　@　@・発注日<BR>
     * 　@　@　@　@　@　@　@　@　@・注文期限<BR>
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_00055<BR>
     * <BR>
     * ２）　@照会区分チェック<BR>
     * 　@２－１）　@リクエストデータ．照会区分がnullの値であれば<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_00081<BR>
     * <BR>
     * 　@２－２）　@リクエストデータ．照会区分が”デフォルト（注文約定<BR>
     * 　@　@　@　@照会画面）”、”訂正取消可能なもののみ表示（訂正<BR>
     * 　@　@　@　@取消一覧画面）”以外の値であれば例外をスローする。<BR>
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_00082<BR>
     * <BR>
     * ３）　@約定状態区分チェック<BR>
     * 　@約定状態がnullでなく、かつ以下の値以外の場合、例外を<BR>
     * スローする。<BR>
     * 　@　@0:未約定　@1:一部約定　@2:全部約定<BR>
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_01697<BR>
     * <BR>
     * ４）　@要求ページ番号チェック<BR>
     * 　@this.要求ページ番号が以下のいずれかに該当する場合は、<BR>
     * 　@以下の例外をスローする。<BR>
     * 　@　@　@・null　@　@　@　@　@(「要求ページ番号がnull」の例外をスロー)<BR>
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_00089<BR>
     * 　@　@　@・数字以外　@(「要求ページ番号が数字以外」の例外をスロー)<BR>
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_00090<BR>
     * 　@　@　@・≦０　@　@　@　@ (「要求ページ番号が0以下」の例外をスロー)<BR>
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_00616<BR>
     * <BR>
     * ５）　@ページ内表示行数チェック<BR>
     * 　@this.ページ内表示行数が以下のいずれかに該当する場合は、<BR>
     * 　@以下の例外をスローする。<BR>
     * 　@　@　@・null　@　@　@　@　@(「ページ内表示行数がnull」の例外をスロー)<BR>
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_00091<BR>
     * 　@　@　@・数字以外　@(「ページ内表示行数が数字以外」の例外をスロー)<BR>
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_00092<BR>
     * 　@　@　@・≦０　@　@　@　@ (「ページ内表示行数が0以下」の例外をスロー)<BR>
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_00617<BR>
     * <BR>
     * ６）　@市場コードチェック<BR>
     * 　@this.市場コード≠null、<BR>
     * 　@かつ下記の値以外の場合、<BR>
     * 　@「市場コードが未定義の値」の例外をスローする。<BR>
     * 　@　@　@・１：東京<BR>
     * 　@　@　@・２：大阪<BR>
     * 　@　@　@・３：名古屋<BR>
     * 　@　@　@・６：福岡<BR>
     * 　@　@　@・８：札幌<BR>
     * 　@　@　@・９：NNM<BR>
     * 　@　@　@・１０：JASDAQ 
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_00608<BR>
     * <BR>
     * ７）　@発注条件区分チェック <BR>
     * 　@this.発注条件区分≠null、<BR>
     * 　@かつ下記の値以外の場合、<BR>
     *　@「発注条件区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@・０：指定なし<BR>
     * 　@　@・１：逆指値<BR>
     * 　@　@・２：W指値<BR>
     *          class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_00212<BR>
     * <BR>
     * @@throws webbroker3.common.WEB3BusinessLayerException
     * @@roseuid 4064035A00EC
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）ソートキーのチェック
        if(this.sortKeys == null)
        {
            // ソートキーがnullの場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            
        }
        int l_intLength = this.sortKeys.length;
        if(l_intLength == 0)
        {
            // ソートキー.要素数が0の場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        for (int i = 0; i < l_intLength; i++)
        {
            // ソートキー.validate()を行う
            this.sortKeys[i].validate();
            
            if (!sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.PRODUCTCODE) //（銘柄）コード
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.ACCOUNTTYPE) //口座
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.TRADEMARKET) //市場
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.TRADETYPE) //取引区分
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.PRICE_COND) //値段条件
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.EXECUTE_COND) //執行条件
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.SEND_COND) //発注条件
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.ORDER_TIME) //注文時間
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.SEND_DATE) //発注日
                && !sortKeys[i].keyItem.equals(WEB3EquityKeyItemDef.ORDER_TIMELIMIT) //注文期限
            )
            {
                log.error("BUSINESS_ERROR_00055:'ソート項目が不正です。");
                //例外
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00055,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //２）照会区分チェック
        if (referenceType == null)
        {
            //照会区分がnullの値であれば例外をスローする。
            log.error("BUSINESS_ERROR_00081:該当データなし。");
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else if (
            !this.referenceType.equals(
                WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW)
            // not 注文約定照会画面
                && !this.referenceType.equals(
                    WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE))
            // not 訂正取消一覧画面
        {
            //照会区分が”デフォルト（注文約定照会画面）”、”訂正取消可能なもののみ表示（訂正取消一覧画面）”以外の値であれば例外をスローする。
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //３）約定状態区分チェック
        if (execType != null // 約定状態がnullでなく
            && !this.execType.equals(
                WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE)
            // not 未約定  
            && !this.execType.equals(
                WEB3EquityExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE)
            // not 一部成立
            && !this.execType.equals(
                WEB3EquityExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE)
            // not 全部成立
        )
        {
            //約定状態がnullでなく、かつ以下の値以外の場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01697,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //４）要求ページ番号のチェック
        if(this.pageIndex == null)
        {
            // 要求ページ番号がnullの場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            if(Integer.parseInt(this.pageIndex) <= 0)
            {
                // 要求ページ番号が0以下の場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        } catch(NumberFormatException e)
        {
            // 要求ページ番号が数字以外の場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //５）　@ページ内表示行数のチェック
        if(this.pageSize == null)
        {
            // ページ内表示行数がnullの場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            if(Integer.parseInt(this.pageSize) <= 0)
            {
                // ページ内表示行数が0以下の場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        } catch(NumberFormatException e)
        {
            // ページ内表示行数が数字以外の場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //６）　@市場コードチェック
        if(this.marketCode != null)
        {
            if(!(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
                || WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
                || WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
                || WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
                || WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
                || WEB3MarketCodeDef.NNM.equals(this.marketCode)
                || WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

         //７）　@発注条件区分チェック
         //  this.発注条件区分≠null、且つ下記の値以外の場合
        //   「発注条件区分が未定義の値」の例外をスローする。
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
}
@
