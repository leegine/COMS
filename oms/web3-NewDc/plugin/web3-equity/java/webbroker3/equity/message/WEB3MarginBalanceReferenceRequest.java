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
filename	WEB3MarginBalanceReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引残高照会リクエスト(WEB3MarginBalanceReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquitySettlementStateDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * （信用取引残高照会リクエスト）。<BR>
 * <BR>
 * 信用取引残高照会リクエストクラス<BR>
 */
public class WEB3MarginBalanceReferenceRequest extends WEB3GenRequest 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginBalanceReferenceRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "margin_balance_reference";

    /**
     * (銘柄コード)<BR>
     * <BR>
     * (検索条件指定時に使用)<BR>
     * <BR>
     * null：指定なし<BR>
     */
    public String productCode = null;
    
    /**
     * (市場コード)<BR>
     * <BR>
     * (検索条件指定時に使用)<BR>
     * <BR>
     * null：指定なし<BR>
     */
    public String marketCode = null;
    
    /**
     * (決済状態区分)<BR>
     * <BR>
     * 1：未決済　@2：決済中<BR>
     */
    public String settlementState = null;
    
    /**
     * (ソートキー)<BR>
     */
    public WEB3MarginSortKey[] sortKeys;
    
    /**
     * (要求ページ番号)<BR>
     * <BR>
     * 表示させたいページ位置を指定　@<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * <BR>
     * １ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;
    
    /**
     * @@roseuid 4206CDBB0069<BR>
     */
    public WEB3MarginBalanceReferenceRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）銘柄コードのチェック<BR>
     * 　@銘柄コード ≠ nullの場合、以下のチェックを行う。<BR>
     * 　@１－１）以下の条件に該当する場合、<BR>
     * 　@　@「銘柄コードエラー」の例外をスローする。<BR>
     * 　@　@　@・銘柄コード ≠ 数値<BR>
     * 　@　@　@・銘柄コード.length != 5<BR>
     * <BR>
     * ２）市場コードのチェック<BR>
     * 　@市場コード ≠ nullの場合、以下のチェックを行う。<BR>
     * 　@２－１）以下の値以外が存在した場合、<BR>
     * 　@　@「市場コードが未定義の値」の例外をスローする。<BR>
     * 　@　@　@・"その他"<BR>
     *         ・"東京"<BR>
     *         ・"大阪"<BR>
     * 　@　@　@・"名古屋"<BR>
     * 　@　@　@・"福岡"<BR>
     * 　@　@　@・"札幌"<BR>
     * 　@　@　@・"NNM"<BR>
     * 　@　@　@・"JASDAQ"<BR>
     * <BR>
     * ３）　@決済状態区分チェック <BR>
     * 　@this.決済状態区分が下記以外の値の場合、<BR>
     * 　@「決済状態区分が未定義の値」の例外をスローする。<BR>
     * 　@　@・null(指定なし) <BR>
     * 　@　@・”1：未決済” <BR>
     * 　@　@・”2：決済中” <BR>
     * <BR>
     * ４）ソートキーのチェック<BR>
     * 　@４－１）ソートキー＝nullの場合、<BR>
     * 　@　@「ソートキーがnull」の例外をスローする。<BR>
     * <BR>
     * 　@４－２）ソートキーの要素数が0の場合、<BR>
     * 　@　@「ソートキーの要素数が0」の例外をスローする。<BR>
     * <BR>
     * 　@４－３）ソートキーの配列の個数分、<BR>
     * 　@　@繰り返して以下のチェックを行う。<BR>
     * 　@　@４－３－１）ソートキー.validate()メソッドをコールする。<BR>
     * 　@　@４－３－２）ソートキー.キー項目に<BR>
     * 　@　@　@以下の項目名以外が存在した場合、<BR>
     * 　@　@　@「ソートキーのキー項目が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・信用取引残高照会明細.銘柄コード<BR>
     * 　@　@　@　@・信用取引残高照会明細.市場コード<BR>
     * 　@　@　@　@・信用取引残高照会明細.口座区分<BR>
     * 　@　@　@　@・信用取引残高照会明細.建区分<BR>
     * 　@　@　@　@・信用取引残高照会明細.弁済.弁済区分<BR>
     * 　@　@　@　@・信用取引残高照会明細.弁済.弁済期限値<BR>
     * 　@　@　@　@・信用取引残高照会明細.評価損益<BR>
     * 　@　@　@　@・信用取引残高照会明細.評価損益（諸経費考慮）<BR>
     * <BR>
     * ５）要求ページ番号チェック<BR>
     * 　@５－１）this.要求ページ番号＝nullであった場合、<BR>
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。<BR>
     * <BR>
     * 　@５－２）this.要求ページ番号が数字以外の値であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。<BR>
     * <BR>
     * 　@５－３）this.要求ページ番号≦０であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。<BR>
     * <BR>
     * ６）ページ内表示行数チェック<BR>
     * 　@６－１）this.ページ内表示行数＝nullであった場合、<BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。<BR>
     * <BR>
     * 　@６－２）this.ページ内表示行数が数字以外の値であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。<BR>
     * 　@<BR>
     * 　@６－３）this.ページ内表示行数≦０であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41BFCB980050<BR>
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("銘柄コードのチェック");
        if (this.productCode != null)
        {
            // 数値チェック
            if (!WEB3StringTypeUtility.isNumber(this.productCode))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                    this.getClass().getName() + ".validate()");
            }
            
            // 桁数チェック
            if (this.productCode.length() != 5)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                    this.getClass().getName() + ".validate()");
            }
        }
        
        log.debug("市場コードチェック");
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
                    this.getClass().getName() + ".validate()");
            }
        }
    
        log.debug("決済状態のチェック");
        if (this.settlementState != null)
        {
	        if (!WEB3EquitySettlementStateDef.HAVE_NOT_SETTLED.equals(this.settlementState)
	            && !WEB3EquitySettlementStateDef.SETTLING.equals(this.settlementState))
	        {
	            throw new WEB3BusinessLayerException(
	                WEB3ErrorCatalog.BUSINESS_ERROR_00233,
	                this.getClass().getName() + ".validate()");
	        }
        }
        
        log.debug("ソートキーのチェック");
        // nullチェック
        if(this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + ".validate()");
        }
        
        // 要素数チェック
        int sortKeysCount = this.sortKeys.length;
        if(sortKeysCount == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + ".validate()");
        }
        
        for(int i = 0 ; i < sortKeysCount ; i++)
        {
            // ソートキー.validate()をコール
            sortKeys[i].validate();
            
            // キー項目チェック
            if(!WEB3EquityKeyItemDef.PRODUCTCODE.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.TRADEMARKET.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.CONTRACT_DIVISION.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.OPEN_DATE.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.CLOSEDATE.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.REPAYMENTNUM.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.INCOME.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.INCOME_COST.equals(sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + ".validate()");
            }
        }

        log.debug("要求ページ番号チェック");
        // 要求ページ番号がnullの場合
        if(this.pageIndex == null)
        {
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
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                    this.getClass().getName() + ".validate()");
            }
        } catch(NumberFormatException e)
        {
            // 要求ページ番号が数字以外の場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + ".validate()");
        }

        log.debug("ページ内表示行数チェック");
        // ページ内表示行数チェック
        if(this.pageSize == null)
        {
            // ページ内表示行数がnullの場合
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
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                    this.getClass().getName() + ".validate()");
            }
        }catch(NumberFormatException e)
        {
            // ページ内表示行数が数字以外の場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + ".validate()");
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public WEB3GenResponse createResponse()
    {
        return new WEB3MarginBalanceReferenceResponse(this);
    }
}
@
