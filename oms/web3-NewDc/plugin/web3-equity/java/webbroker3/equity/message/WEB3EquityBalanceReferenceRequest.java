head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBalanceReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現現物株式残高照会リクエスト(WEB3EquityBalanceReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
				   2006/08/29 李俊　@(中訊)　@仕様変更 モデルNo.971
Revesion History : 2008/03/27 楊夫志(中訊)　@仕様変更 モデルNo.1309
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * （現物株式残高照会リクエスト）。<BR>
 * <BR>
 * 現物株式残高照会リクエストクラス<BR>
 */
public class WEB3EquityBalanceReferenceRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBalanceReferenceRequest.class);
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_balance_reference";
    
    /**
     * (銘柄コード)<BR>
     *<BR>
     * (検索条件指定時に使用)<BR>
     * <BR>
     * null：指定なし<BR>
     */
    public String productCode = null;
    
    /**
     * (市場コード)<BR>
     *<BR>
     * (検索条件指定時に使用)<BR>
     * <BR>
     * null：指定なし<BR>
     */
    public String marketCode = null;
    
    /**
     * (口座区分一覧)
     * <BR>
     * (口座区分の配列)<BR>
     * <BR>
     */
    public String[] taxTypeList;
    /**
     * (ソートキー)<BR>
     */
    public WEB3EquitySortKey[] sortKeys;
    
    /**
     * (要求ページ番号)<BR>
     *<BR>
     * 表示させたいページ位置を指定　@<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     *<BR>
     * １ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;
    
    /**
     * @@roseuid 4206C8AA00A8<BR>
     */
    public WEB3EquityBalanceReferenceRequest() 
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
     * <BR>
     * 　@　@（ 市場コードはチェックしない。）<BR>
     * <BR>
     * ３）口座区分一覧のチェック <BR>
     * 　@３－１）口座区分一覧＝要素数が0の場合、 <BR>
     * 　@　@「口座区分一覧の要素数が０です。」の例外をスローする。 <BR>
     * 　@３－２）口座区分一覧の配列の個数分、繰り返して以下のPRコード値チェックを行う。<BR> 
     * 　@　@口座区分一覧項目に以下の項目名以外が存在した場合、 <BR>
     * 　@　@　@「口座区分が存在しないコード値です。」の例外をスローする。<BR> 
     * 　@　@　@　@・一般口座 <BR>
     * 　@　@　@　@・特定口座 <BR>
     * 　@　@　@　@・ストックオプション口座 <BR>
     * <BR>
     * 
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
     * 　@　@　@　@・現物株式残高照会明細.銘柄コード<BR>
     * 　@　@　@　@・現物株式残高照会明細.口座区分<BR>
     * 　@　@　@　@・現物株式残高照会明細.概算評価額(残高株数)<BR>
     * 　@　@　@　@・現物株式残高照会明細.概算評価損益(残高株数)<BR>
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
     * @@throws<BR>
     *  WEB3BaseException<BR>
     * @@roseuid 41B5701501F1<BR>
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
	    
	    log.debug("口座区分一覧のチェック");
	    //口座区分一覧＝要素数が0の場合、 
	    //「口座区分一覧の要素数が０です。」の例外をスローする。
	    if(this.taxTypeList != null)
	    {
	        if (this.taxTypeList.length == 0)
	        {
	            throw new WEB3BusinessLayerException(
	                WEB3ErrorCatalog.BUSINESS_ERROR_02630,
	                this.getClass().getName() + ".validate()");
	        }    
	        
		    //口座区分一覧の配列の個数分、繰り返して以下のPRコード値チェックを行う。 
		    //　@口座区分一覧項目に以下の項目名以外が存在した場合、 
		    //「口座区分が存在しないコード値です。」の例外をスローする。 
		    //　@・一般口座 
		    //　@・特定口座 
		    //　@・ストックオプション口座 
		    for(int i = 0; i < this.taxTypeList.length; i++)
		    {
		        if (!(WEB3TaxTypeDef.NORMAL.equals(this.taxTypeList[i])
		            ||WEB3TaxTypeDef.SPECIAL.equals(this.taxTypeList[i])
		            ||WEB3TaxTypeDef.STOCK_OPTION.equals(this.taxTypeList[i])))
		            throw new WEB3BusinessLayerException(
		                WEB3ErrorCatalog.BUSINESS_ERROR_00605,
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
                && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.ESTIMATED_ASSET_BALANCE_QUANTITY.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.ESTIMATED_INCOME_BALANCE_QUANTITY.equals(sortKeys[i].keyItem))
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
        return new WEB3EquityBalanceReferenceResponse(this);
    }
}
@
