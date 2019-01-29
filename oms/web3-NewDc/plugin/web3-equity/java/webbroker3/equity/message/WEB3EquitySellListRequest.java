head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquitySellListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式売付一覧リクエスト(WEB3EquitySellListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 任林林 (中訊) 新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式売付一覧リクエスト）。<BR>
 * <BR>
 * 現物株式保有資産一覧照会要求　@リクエストデータクラス
 * @@version 1.0
 */
public class WEB3EquitySellListRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquitySellListRequest.class);
        
    /**
     * 銘柄コード
     */
    public String productCode;

    /**
     * 市場コード
     */
    public String marketCode;

    /**
     * 現物株式ソートキーの一覧
     * 
     * 対象項目：銘柄ｺｰﾄﾞ、口座区分
     */
    public WEB3EquitySortKey[] sortKeys;

    /**
     * 要求ページ番号<BR>
     * 表示させたいページ位置を指定<BR>
     */
    public String pageIndex;

    /**
     * ページ内表示行数<BR>
     * １ページ内に表示させたい行数<BR>
     */
    public String pageSize;

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405101030L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_sell_list";

    /**
     * @@roseuid 409F5AB90000
     */
    public WEB3EquitySellListRequest()
    {

    }

    /**
     * レスポンスデータを作成する。
     * @@return WEB3EquityCommonInputResponse
     * @@roseuid 4063B6D90191
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquitySellListResponse(this);
    }

    /**
     * (validate)<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）ソートキーのチェック<BR>
     * 　@１－１）this.ソートキー＝nullであった場合<BR>
     * 　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     * <BR>
     * 　@１－２）this.ソートキー.要素数＝０だった場合<BR>
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     * <BR>
     * 　@１－３）this.ソートキーの全要素に対して<BR>
     * 　@　@　@　@下記のチェックを行う。<BR>
     * 　@　@１－３－１）ソートキー.validate()をコールする。<BR>
     * <BR>
     * 　@　@１－３－２）ソートキーの配列の個数分、繰り返してチェックを行う。<BR>
     * 　@　@　@　@　@　@　@　@　@以下の項目名以外が存在した場合、<BR>
     * 　@　@　@　@　@　@　@　@　@「キー項目に項目名以外の値が存在」の例外をスローする。<BR>
     * 　@　@　@　@・銘柄（銘柄コード）<BR>
     * 　@　@　@　@・口座区分<BR>
     * <BR>
     * ２）　@要求ページ番号チェック<BR>
     * 　@this.要求ページ番号が以下のいずれかに該当する場合は、<BR>
     * 　@以下の例外をスローする。<BR>
     * 　@　@　@・null　@　@　@　@　@(「要求ページ番号がnull」の例外をスロー)<BR>
     * 　@　@　@・数字以外　@(「要求ページ番号が数字以外」の例外をスロー)<BR>
     * 　@　@　@・≦０　@　@　@　@ (「要求ページ番号が0以下」の例外をスロー)<BR>
     * <BR>
     * ３）　@ページ内表示行数チェック<BR>
     * 　@this.ページ内表示行数が以下のいずれかに該当する場合は、<BR>
     * 　@以下の例外をスローする。<BR>
     * 　@　@　@・null　@　@　@　@　@(「ページ内表示行数がnull」の例外をスロー)<BR>
     * 　@　@　@・数字以外　@(「ページ内表示行数が数字以外」の例外をスロー)<BR>
     * 　@　@　@・≦０　@　@　@　@ (「ページ内表示行数が0以下」の例外をスロー)<BR>
     * <BR>
     * ４）　@市場コードチェック<BR>
     * 　@this.市場コード≠null、<BR>
     * 　@かつ下記の値以外の場合、<BR>
     * 　@「市場コードが未定義の値」の例外をスローする。<BR>
     * 　@　@　@・１：東京<BR>
     * 　@　@　@・２：大阪<BR>
     * 　@　@　@・３：名古屋<BR>
     * 　@　@　@・６：福岡<BR>
     * 　@　@　@・８：札幌<BR>
     * 　@　@　@・９：NNM<BR>
     * 　@　@　@・１０：JASDAQ<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4067DC4602FC
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("ソートキーのチェック");
        // １）ソートキーのチェック
        // 　@１－１）this.ソートキー＝nullであった場合
        // 　@　@　@　@「ソートキーがnull」の例外をスローする。
        if(this.sortKeys == null)
        {
            // 例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + ".validate()");
        }
        
        // 　@１－２）this.ソートキー.要素数＝０だった場合
        // 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。
        int sortKeysCount = this.sortKeys.length;
        if(sortKeysCount == 0)
        {
            // 例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + ".validate()");
        }
        
        // 　@１－３）this.ソートキーの全要素に対して
        // 　@　@　@　@下記のチェックを行う。
        for(int i = 0 ; i < sortKeysCount ; i++)
        {
            // 　@　@１－３－１）ソートキー.validate()をコールする。
            sortKeys[i].validate();
            
            // 　@　@１－３－２）ソートキーの配列の個数分、繰り返してチェックを行う。
            // 　@　@　@　@　@　@　@　@　@以下の項目名以外が存在した場合、
            // 　@　@　@　@　@　@　@　@　@「キー項目に項目名以外の値が存在」の例外をスローする。
            // 　@　@　@　@・銘柄（銘柄コード）
            // 　@　@　@　@・口座区分<BR>
            if(!WEB3EquityKeyItemDef.PRODUCTCODE.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(sortKeys[i].keyItem))
            {
                // 例外
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + ".validate()");
            }
        }

        log.debug("要求ページ番号チェック");
        // ２）　@要求ページ番号チェック
        // 　@this.要求ページ番号が以下のいずれかに該当する場合は、
        // 　@以下の例外をスローする。
        // 　@　@　@・null　@　@　@　@　@(「要求ページ番号がnull」の例外をスロー)
        // 　@　@　@・数字以外　@(「要求ページ番号が数字以外」の例外をスロー)
        // 　@　@　@・≦０　@　@　@　@ (「要求ページ番号が0以下」の例外をスロー)
        if(this.pageIndex == null)
        {
            // 要求ページ番号がnullの場合
            // 例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + ".validate()");
        }
        try
        {
            int l_intPageIndex= Integer.parseInt(this.pageIndex);
            if(l_intPageIndex <= 0)
            {
                // 要求ページ番号が０以下の場合
                // 例外
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                    this.getClass().getName() + ".validate()");
            }
        } catch(NumberFormatException e)
        {
            // 例外
            // 要求ページ番号が数字以外の場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + ".validate()");
        }

        // ３）　@ページ内表示行数チェック
        // 　@this.ページ内表示行数が以下のいずれかに該当する場合は、
        // 　@以下の例外をスローする。
        // 　@　@　@・null　@　@　@　@　@(「ページ内表示行数がnull」の例外をスロー)
        // 　@　@　@・数字以外　@(「ページ内表示行数が数字以外」の例外をスロー)
        // 　@　@　@・≦０　@　@　@　@ (「ページ内表示行数が0以下」の例外をスロー)
        if(this.pageSize == null)
        {
            // ページ内表示行数がnullの場合
            // 例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + ".validate()");
        }
        try
        {
            int l_intPageSize= Integer.parseInt(this.pageSize);
            if(l_intPageSize <= 0)
            {
                // ページ内表示行数が０以下の場合
                // 例外
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                    this.getClass().getName() + ".validate()");
            }
        }catch(NumberFormatException e)
        {
            // ページ内表示行数が数字以外の場合
            // 例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + ".validate()");
        }

        log.debug("市場コードチェック");
        // ４）　@市場コードチェック
        // 　@this.市場コード≠null、
        // 　@かつ下記の値以外の場合、
        // 　@「市場コードが未定義の値」の例外をスローする。
        // 　@　@　@・１：東京
        // 　@　@　@・２：大阪
        // 　@　@　@・３：名古屋
        // 　@　@　@・６：福岡
        // 　@　@　@・８：札幌
        // 　@　@　@・９：NNM
        // 　@　@　@・１０：JASDAQ
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
                // 例外
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + ".validate()");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
