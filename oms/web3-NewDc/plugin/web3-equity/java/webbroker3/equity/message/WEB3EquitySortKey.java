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
filename	WEB3EquitySortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式ソートキー(WEB3EquitySortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 張坤芳 (中訊) 新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （現物株式ソートキー）。<BR>
 * <BR>
 * 一覧表示時のソート順制御クラス
 * @@version 1.0
 */
public class WEB3EquitySortKey extends Message
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquitySortKey.class);

    /**
     * (キー項目)<BR>
     * 当クラスを利用したリクエスト対してのレスポンスクラス中の<BR>
     * シンボル名をキー項目とする。<BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     */
    public String keyItem;

    /**
     * (昇順／降順)<BR>
     * 
     * A：昇順　@D：降順<BR>
     */
    public String ascDesc;

    /**
     * (現物株式ソートキー)<BR>
     * @@roseuid 40A118170177
     */
    public WEB3EquitySortKey()
    {

    }
    
    /**
     * （validate）<BR>
     * <BR>
     * 当クラスの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）this.キー項目＝nullの場合、<BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@tag   : BUSINESS_ERROR_00085<BR>
     * ２）this.昇順／降順＝nullの場合、<BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@tag   : BUSINESS_ERROR_00087<BR>
     * ３）this.昇順／降順が下記の項目以外の場合、<BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@tag   : BUSINESS_ERROR_00088<BR>
     * 　@　@　@・”A：昇順”<BR>
     * 　@　@　@・”D：降順”<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("キー項目のNullチェック");
        if(this.keyItem == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + ".validate()");
        }
        
        log.debug("昇順／降順のNullチェック");
        if(this.ascDesc == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + ".validate()");
        }
        
        log.debug("昇順／降順の項目チェック");
        if(!WEB3AscDescDef.ASC.equals(this.ascDesc)
            && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + ".validate()");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
