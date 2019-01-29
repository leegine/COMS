head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引ソートキー(WEB3MarginSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引ソートキー）。<BR>
 * <BR>
 * 信用取引ソートキークラス<BR>
 * 一覧表示時のソート順制御クラス。<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginSortKey extends Message 
{
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginSortKey.class);

    /**
     * (キー項目)<BR>
     * <BR>
     * 当クラスを利用したリクエスト対してのレスポンスクラス中のシンボル名をキー<BR>
     * 項目とする。<BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     */
    public String keyItem;
    
    /**
     * (昇順／降順)<BR>
     * <BR>
     * A：昇順　@D：降順
     */
    public String ascDesc;
    
    /**
     * (信用取引ソートキー)<BR>
     * <BR>
     * コンストラクタ。
     * @@return WEB3MarginSortKey
     * @@roseuid 40C9313003DF
     */
    public WEB3MarginSortKey() 
    {
     
    }
    
    /**
     * 当クラスの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）this.キー項目＝nullの場合、<BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00085<BR>
     * ２）this.昇順／降順＝nullの場合、<BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00087<BR>
     * ３）this.昇順／降順が下記の項目以外の場合、<BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・”A：昇順”<BR>
     * 　@　@　@・”D：降順”<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41103B36019A
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "WEB3MarginSortKey: validate()";
        log.entering(STR_METHOD_NAME);
        
        if (this.keyItem == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00085,
            this.getClass().getName() + "validate");
        }

        if (this.ascDesc == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00087,
            this.getClass().getName() + "validate");
        }

        if (!WEB3AscDescDef.ASC.equals(this.ascDesc) && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00088,
            this.getClass().getName() + "validate");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
