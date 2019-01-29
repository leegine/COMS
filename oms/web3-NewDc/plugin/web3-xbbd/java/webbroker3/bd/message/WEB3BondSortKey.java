head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.54.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券ソートキー(WEB3BondSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (債券ソートキー)<BR>
 * 債券ソートキー
 * 
 * @@author  趙林鵬
 * @@version 1.0
 */
public class WEB3BondSortKey extends Message 
{
    /** logger. */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondSortKey.class);
    
    /**
     * (キー項目)<BR>
     * キー項目
     */
    public String keyItem;
    
    /**
     * (昇順／降順)<BR>
     * A：　@昇順<BR>
     * D：　@降順
     */
    public String ascDesc;
    
    /**
     * (債券ソートキー)<BR>
     * コンストラクタ。
     * @@roseuid 42194BCD0253
     */
    public WEB3BondSortKey() 
    {
     
    }
    
    /**
     * 当クラスの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）this.キー項目 == nullの場合、 <BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。 <BR>
     * 　@　@tag:  BUSINESS_ERROR_00085<BR>
     * 　@　@class:WEB3BusinessLayerException<BR>
     * <BR>
     * ２）this.昇順／降順 == nullの場合、 <BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。 <BR>
     * 　@　@tag:  BUSINESS_ERROR_00087<BR>
     * 　@　@class:WEB3BusinessLayerException<BR>
     * <BR>
     * ３）this.昇順／降順が下記の項目以外の場合、 <BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@・"A：昇順"<BR>
     * 　@　@　@・"D：降順"<BR>
     * 　@　@　@tag:  BUSINESS_ERROR_00088<BR>
     * 　@　@　@class:WEB3BusinessLayerException
     * @@throws  WEB3BaseException
     * @@roseuid 42194C070178
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）this.キー項目 == nullの場合、ソートキー.キー項目がnull」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.keyItem))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //２）this.昇順／降順 == nullの場合、「ソートキー.昇順／降順がnull」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.ascDesc))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //３）this.昇順／降順が下記の項目以外の場合、「ソートキー.昇順／降順が未定義の値」の例外をスローする。
        if (!WEB3AscDescDef.ASC.equals(this.ascDesc) && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
