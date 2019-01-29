head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キューテーブルソートキー(WEB3AdminDirSecSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 奚翔(中訊) 新規作成
*/

package webbroker3.dirsec.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (キューテーブルソートキー)<BR>
 * キューテーブルソートキー。<BR>
 * 
 * @@author 奚翔
 * @@version 1.0
 */
public class WEB3AdminDirSecSortKey extends Message 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecSortKey.class);
    
    /**
     * (キー項目)<BR>
     * 当クラスを利用したリクエスト対してのレスポンスクラス中のシンボル名<BR>
     * 　@をキー項目とする。 <BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     */
    public String keyItem;
    
    /**
     * (昇順／降順)<BR>
     * 昇順／降順 <BR>
     * <BR>
     * A：　@昇順 <BR>
     * D：　@降順<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 442A1C8902FD
     */
    public WEB3AdminDirSecSortKey() 
    {
     
    }
    
    /**
     * 当クラスの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR> 
     * <BR>
     * １）this.キー項目＝nullの場合、<BR> 
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00085<BR>
     * ２）this.昇順／降順＝nullの場合、 <BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00087<BR>
     * ３）this.昇順／降順が下記の項目以外の場合、 <BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。<BR> 
     * 　@　@　@・”A：昇順” <BR>
     * 　@　@　@・”D：降順” <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00088<BR>
     * 
     * @@throws WEB3BusinessLayerException
     * @@roseuid 44164A0D0197
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）this.キー項目＝nullの場合、
        //　@　@「ソートキー.キー項目がnull」の例外をスローする。 
        //　@　@class: WEB3BusinessLayerException
        //　@　@tag:   BUSINESS_ERROR_00085
        if (this.keyItem == null)
        {
            log.debug("ソートキーのキー項目が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーのキー項目が未指定です。");
        }
        
        //２）this.昇順／降順＝nullの場合、 
        //　@　@「ソートキー.昇順／降順がnull」の例外をスローする。
        //　@　@class: WEB3BusinessLayerException
        //　@　@tag:   BUSINESS_ERROR_00087
        if (this.ascDesc == null)
        {
            log.debug("昇順／降順が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + STR_METHOD_NAME,
                "昇順／降順が未指定です。");
        }
        
        //３）this.昇順／降順が下記の項目以外の場合、 
        //　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。 
        //　@　@　@・”A：昇順” 
        //　@　@　@・”D：降順” 
        //　@　@class: WEB3BusinessLayerException
        //　@　@tag:   BUSINESS_ERROR_00088
        if (!WEB3AscDescDef.ASC.equals(this.ascDesc)
                && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            log.debug("昇順／降順が”A：昇順”、”D：降順”以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + STR_METHOD_NAME,
                "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
