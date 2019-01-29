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
filename	WEB3MstkSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資ソートキー(WEB3MstkSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 彭巍(中訊) 新規作成
                   2005/01/05 岡村(SRA) JavaDoc修正
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式ミニ投資ソートキー）。<br>
 * <br>
 * 一覧表示時のソート順制御クラス
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3MstkSortKey extends Message 
{
    
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginSortKey.class);

    
    /**
     * （キー項目）。<BR>
     * <BR>
     * 当クラスを利用したリクエストに対してのレスポンスクラス中<BR>
     * のシンボル名をキー項目とする。<BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。
     */
    public String keyItem;
    
    /**
     * （昇順／降順）。<BR>
     * <BR>
     * A：昇順　@D：降順
     */
    public String ascDesc;
    
    /**
     * 
     */
    public WEB3MstkSortKey() 
    {
     
    }
    
    /**
     * （validate）。<BR>
     * <BR>
     * 当クラスの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）this.キー項目＝nullの場合、<BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00085<BR>
     * <BR>
     * ２）this.昇順／降順＝nullの場合、<BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00087<BR>
     * <BR>
     * ３）this.昇順／降順が下記の項目以外の場合、<BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・”A：昇順”<BR>
     * 　@　@　@・”D：降順”<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        log.debug("株式ミソートキーのチェック： BEGIN");
        log.debug("１）this.キー項目＝nullのチェック: BEGIN");
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
 
     
    }
}
@
