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
filename	WEB3AdminToOrderRefSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文照会ソートキー(WEB3AdminToOrderRefSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (注文照会ソートキー)<BR>
 * 注文照会ソートキークラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToOrderRefSortKey extends Message
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToOrderRefSortKey.class);

    /**
     * (キー項目)<BR>
     * 当クラスを利用したリクエスト対してのレスポンスクラス中の<BR>
     * シンボル名をキー項目とする。 <BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     */
    public String keyItem;
    
    /**
     * (昇順／降順)<BR>
     * A：昇順　@D：降順<BR>
     */
    public String ascDesc;
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F1B3C80138
     */
    public WEB3AdminToOrderRefSortKey() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@this.キー項目＝nullの場合、 <BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_00085<BR>
     * <BR>
     * ２）　@this.昇順／降順＝nullの場合、 <BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_00087<BR>
     * <BR>
     * ３）　@this.昇順／降順が下記の項目以外の場合、 <BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@・”A：昇順” <BR>
     * 　@　@　@・”D：降順” <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43DF1E2603CE
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@this.キー項目＝nullの場合、
        //　@　@「ソートキー.キー項目がnull」の例外をスローする。
        if (this.keyItem == null)
        {   
            log.debug("ソートキーのキー項目が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーのキー項目が未指定です。");
        }
        
        //２）　@this.昇順／降順＝nullの場合、
        //　@　@「ソートキー.昇順／降順がnull」の例外をスローする。
        if (this.ascDesc == null)
        {
            log.debug("昇順／降順が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "昇順／降順が未指定です。");
        }
        
        //３）　@this.昇順／降順が下記の項目以外の場合、
        //　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。 
        //　@　@　@・”A：昇順”
        //　@　@　@・”D：降順” 
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
