head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引決済一覧リクエスト(WEB3MarginCloseMarginListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （信用取引決済一覧リクエスト）。<br>
 * <br>
 * 信用取引決済一覧リクエストクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginCloseMarginListRequest extends WEB3GenRequest 
{
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginCloseMarginListRequest.class);
    
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginList";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101759L;     
    /**
     * (銘柄コード)
     */
    public String productCode;
    
    /**
     * (市場コード)
     */
    public String marketCode;
    
    /**
     * (ソートキー)<BR>
     * 信用取引ソートキーの一覧<BR>
     * <BR>
     * 対象項目：銘柄コード、市場コード、口座区分、建区分、弁済区分、<BR>
     * 弁済期限値、評価損益<BR>
     */
    public WEB3MarginSortKey[] sortKeys;
    
    /**
     * (要求ページ番号)<BR>
     * <BR>
     * 表示させたいページ位置を指定　@※先頭ページを"1"とする
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * <BR>
     * １ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;

    /**
     * (連続注文フラグ)<BR>
     * <BR>
     * 連続注文フラグ。 <BR>
     * （false： 通常注文、true： 連続注文）<BR>
     */
    public boolean succFlag;
    
    /**
     * @@roseuid 414032D1016D
     */
    public WEB3MarginCloseMarginListRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@市場コードチェック<BR>
     * 　@this.市場コードが下記以外の値の場合、<BR>
     * 　@「市場コードが未定義の値」の例外をスローする。<BR>
     * 　@　@・null(指定なし)<BR>
     * 　@　@・”1：東京”<BR>
     *     ・”2：大阪” <BR>
     *     ・”3：名古屋” <BR>
     *     ・”6：福岡” <BR>
     *     ・”8：札幌” <BR>
     *     ・”9：NNM” <BR>
     *     ・”10：JASDAQ”<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     * ２）　@ソートキーチェック <BR>
     * 　@２－１）this.ソートキー＝nullの場合、「ソートキーがnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00231<BR>
     * 　@２－２）this.ソートキーの要素数＝０の場合、<BR>
     * 　@　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00232<BR>
     * 　@２－３）this.ソートキーの要素数分、下記のチェックを繰り返して行う。 <BR>
     * 　@　@２－３－１）ソートキー.validate()をコールする。<BR>
     * 　@　@２－３－２）ソートキー.キー項目が下記の項目名以外の場合、<BR>
     * 　@　@　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@・”銘柄コード”<BR>
     * 　@　@　@・”市場コード”<BR>
     * 　@　@　@・”口座区分”<BR>
     * 　@　@　@・”建区分”<BR>
     * 　@　@　@・”弁済区分”<BR>
     * 　@　@　@・”弁済期限値”<BR>
     * 　@　@　@・”評価損益”<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * ３）　@要求ページ番号チェック<BR>
     * 　@this.要求ページ番号が以下のいずれかに該当する場合は、<BR>
     * 　@以下の例外をスローする。<BR>
     * 　@　@　@・null　@　@　@　@　@(「要求ページ番号がnull」の例外をスロー)<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00089<BR>
     * 　@　@　@・数字以外　@(「要求ページ番号が数字以外」の例外をスロー)<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00090<BR>
     * 　@　@　@・≦０　@　@　@　@ (「要求ページ番号が0以下」の例外をスロー)<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ４）　@ページ内表示行数チェック<BR>
     * 　@this.ページ内表示行数が以下のいずれかに該当する場合は、<BR>
     * 　@以下の例外をスローする。<BR>
     * 　@　@　@・null　@　@　@　@　@(「ページ内表示行数がnull」の例外をスロー)<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00091<BR>
     * 　@　@　@・数字以外　@(「ページ内表示行数が数字以外」の例外をスロー)<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00092<BR>
     * 　@　@　@・≦０　@　@　@　@ (「ページ内表示行数が0以下」の例外をスロー)<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00617<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4084943C007A
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "●●● WEB3MarginCloseMarginListRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("信用取引決済一覧リクエストのチェック: BEGIN");
        log.debug("市場コードのチェック: BEGIN");
        // １）　@市場コードチェック
        if (this.marketCode != null
                && !WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
                && !WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
                && !WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
                && !WEB3MarketCodeDef.NNM.equals(this.marketCode)
                && !WEB3MarketCodeDef.JASDAQ.equals(this.marketCode))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00608,
            this.getClass().getName() + "validate");
        }
        log.debug("市場コードのチェック: END");

        log.debug("ソートキーのチェック: BEGIN");
        // ２）　@ソートキーチェック
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231,
            this.getClass().getName() + "validate");
        }
        
        int l_intSortKeysLength = this.sortKeys.length;
        if (l_intSortKeysLength == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232,
            this.getClass().getName() + "validate");
        }

        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            sortKeys[i].validate();
            if (!WEB3EquityKeyItemDef.PRODUCTCODE.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.TRADEMARKET.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.CONTRACT_DIVISION.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.REPAYMENTNUM.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.INCOME.equals(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + "validate");
            }
        }
        log.debug("ソートキーのチェック: END");

        log.debug("要求ページ番号のチェック: BEGIN");
        // ３）　@要求ページ番号チェック
        if (this.pageIndex == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00089,
            this.getClass().getName() + "validate");
        }
        
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090,
            this.getClass().getName() + "validate");
        }
        
        if (Long.parseLong(this.pageIndex) <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616,
            this.getClass().getName() + "validate");
        }
        log.debug("要求ページ番号のチェック: END");

        log.debug("ページ内表示行数のチェック: BEGIN");
        // ４）　@ページ内表示行数チェック
        if (this.pageSize == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091,
            this.getClass().getName() + "validate");
        }

        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092,
            this.getClass().getName() + "validate");
        }

        if (Long.parseLong(this.pageSize) <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617,
            this.getClass().getName() + "validate");
        }
        log.debug("ページ内表示行数のチェック: END");
        log.debug("信用取引決済一覧リクエストのチェック: END");

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 414032D1018B
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginCloseMarginListResponse(this);
    }
}
@
