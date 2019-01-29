head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取扱停止ソートキー(WEB3AdminToTradeStopSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 張　@芳(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (取扱停止ソートキー)<BR>
 * 取扱停止ソートキークラス<BR>
 * 
 * @@author 張　@芳
 * @@version 1.0
 */
public class WEB3AdminToTradeStopSortKey extends Message 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopSortKey.class);
    
    /**
     * (キー項目)<BR>
     * 当クラスを利用したリクエスト対してのレスポンスクラス中のシンボル名をキー項目とする。 <BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     */
    public String keyItem;
    
    /**
     * (昇順／降順)<BR>
     * A：昇順　@D：降順<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 4430D2C00177
     */
    public WEB3AdminToTradeStopSortKey() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@this.キー項目＝nullの場合、 <BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。 <BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * ２）　@this.昇順／降順＝nullの場合、 <BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。 <BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * ３）　@this.昇順／降順が下記の項目以外の場合、 <BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@・”A：昇順” <BR>
     * 　@　@　@・”D：降順” <BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 441110220194
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）this.キー項目＝nullの場合、 
        // 　@ 「ソートキー.キー項目がnull」の例外をスローする。 
        //     class : WEB3BusinessLayerException
        //     tag : BUSINESS_ERROR_00085
        if (WEB3StringTypeUtility.isEmpty(this.keyItem))
        {
            log.debug("ソートキーのキー項目が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーのキー項目が未指定です。"); 
        }
        
        // ２）this.昇順／降順＝nullの場合、 
        //　@　@「ソートキー.昇順／降順がnull」の例外をスローする。
        //     class : WEB3BusinessLayerException
        //     tag : BUSINESS_ERROR_00087
        if (WEB3StringTypeUtility.isEmpty(this.ascDesc))
        {
            log.debug("昇順／降順が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "昇順／降順が未指定です。"); 
        }
        
        // ３）this.昇順／降順が下記の項目以外の場合、 
        //　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。 
        // 　@　@　@・”A：昇順” 
        // 　@　@　@・”D：降順” 
        //     class : WEB3BusinessLayerException
        //     tag : BUSINESS_ERROR_00088
        if (!(WEB3AscDescDef.ASC.equals(this.ascDesc) || WEB3AscDescDef.DESC.equals(this.ascDesc)))
        {
            log.debug("昇順／降順が”A：昇順”、”D：降順”以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@
