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
filename	WEB3MstkBalanceReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資残高照会リクエスト(WEB3MstkBalanceReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * （株式ミニ投資残高照会リクエスト）。<BR>
 * <BR>
 * 株式ミニ投資残高照会リクエストクラス<BR>
 */
public class WEB3MstkBalanceReferenceRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBalanceReferenceRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mstk_balance_reference";

    /**
     * (銘柄コード)<BR>
     * <BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (ソートキー)<BR>
     * <BR>
     * 株式ミニ投資ソートキーの一覧<BR>
     * <BR>
     * 対象項目： 銘柄コード、市場コード<BR>
     */
    public WEB3MstkSortKey[] sortKeys;
    
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
     * １ページ内に表示させたい行数<BR>
     */
    public String pageSize;
    
    /**
     * @@roseuid 4206CC9901E0<BR>
     */
    public WEB3MstkBalanceReferenceRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ソートキーチェック <BR>
     * 　@　@１－１）this.ソートキー＝nullの場合、<BR>
     * 　@　@　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     * 　@　@１－２）this.ソートキーの要素数＝０の場合、<BR>
     * 　@　@　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     * 　@　@１－３）this.ソートキーの要素数分、下記のチェックを繰り返して行う。 <BR>
     * 　@　@　@１－３－１）ソートキー.validate()をコールする。<BR>
     * 　@　@　@１－３－２）ソートキー.キー項目が下記の項目名以外の場合、<BR>
     * 　@　@　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@　@・株式ミニ投資残高照会明細.銘柄コード<BR>
     * <BR>
     * ２）　@　@要求ページ番号チェック<BR>
     * 　@　@　@　@this．要求ページ番号が、未指定の場合、<BR>
     * 　@　@　@　@this．要求ページ番号に「１」をセットする。<BR>
     * <BR>
     * 　@　@　@　@this.要求ページ番号が、以下のいずれかに該当する場合は、<BR>
     * 　@　@　@　@以下の例外をスローする。<BR>
     * 　@　@　@・this.要求ページ番号＝null　@　@　@　@　@(「要求ページ番号がnull」の例外をスロ<BR>
     * ー)<BR>
     * 　@　@　@・this.要求ページ番号≠数字　@　@　@　@(「要求ページ番号が数字以外」の例外をス<BR>
     * ロー)<BR>
     * 　@　@　@・this.要求ページ番号≦０　@　@ 　@　@　@ <BR>
     * (「要求ページ番号が0以下」の例外をスロー)<BR>
     * <BR>
     * ３）　@　@ページ内表示行数チェック<BR>
     * 　@　@　@　@this.ページ内表示行数が、以下のいずれかに該当する場合は、<BR>
     * 　@　@　@　@以下の例外をスローする。<BR>
     * 　@　@　@・this.ページ内表示行数＝null　@　@　@　@　@(「ページ内表示行数がnull」の例外を<BR>
     * スロー)<BR>
     * 　@　@　@・this.ページ内表示行数≠数字　@　@　@　@(「ページ内表示行数が数字以外」の例外<BR>
     * をスロー)<BR>
     * 　@　@　@・this.ページ内表示行数≦０　@　@　@　@ <BR>
     * (「ページ内表示行数が0以下」の例外をスロー)<BR>
     * <BR>
     * @@roseuid 41BFDB330188<BR>
     */
    public void validate() 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@　@ソートキーチェック
        log.debug("ソートキーチェック");
        if(sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if(sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        for(int i = 0; i < sortKeys.length; i++)
        {
            sortKeys[i].validate();
            log.debug("sortKeys[i].keyItem = " + sortKeys[i].keyItem);
            if(!(WEB3EquityKeyItemDef.PRODUCTCODE).equals(sortKeys[i].keyItem) && 
                !(WEB3EquityKeyItemDef.ACCOUNTTYPE).equals(sortKeys[i].keyItem))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
        }

        //２）　@　@要求ページ番号チェック
        log.debug("要求ページ番号チェック");
        if(pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089, 
                this.getClass().getName() + STR_METHOD_NAME);    
        }
        if(!WEB3StringTypeUtility.isNumber(pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        double l_dblIndex = Double.parseDouble(pageIndex);
        if(l_dblIndex <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //３）　@　@ページ内表示行数チェック
        log.debug("ページ内表示行数チェック");
        if(pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if(!WEB3StringTypeUtility.isNumber(pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        double l_dblSize = Double.parseDouble(pageSize);
        if(l_dblSize <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    public WEB3GenResponse createResponse()
    {
        return new WEB3MstkBalanceReferenceResponse(this);
    }
}
@
