head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報ソートキー(WEB3AccInfoSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (お客様情報ソートキー)<BR>
 * お客様情報ソートキー<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AccInfoSortKey extends Message 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoSortKey.class);
        
    /**
     * (キー項目)<BR>
     * キー項目<BR>
     * <BR>
     * 当クラスを利用したリクエストに対してのレスポンスクラス中の<BR>
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
     * @@roseuid 418F38610251
     */
    public WEB3AccInfoSortKey() 
    {
     
    }
    
    /**
     * (validate)<BR>
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@キー項目のチェック<BR>
     * 　@１－１）　@キー項目が未入力lの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00085<BR>
     * <BR>
     * ２）　@昇順／降順のチェック<BR>
     * 　@２－１）　@昇順／降順が未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00087<BR>
     * 　@２－２）　@昇順／降順が不正なコード値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00088<BR>  
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 416B927001B1
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
       /*
        * １）　@キー項目のチェック<BR>
        * 　@１－１）　@キー項目が未入力lの場合、例外をスローする。<BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_00085<BR>
        * <BR>
        */
        if(keyItem == null || "".equals(keyItem))
        {
            log.debug("[キー項目] = " + keyItem);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00085, 
                this.getClass().getName(),
                "キー項目が未入力");
        }
        
       /* 
        * 　@２－１）　@昇順／降順が未入力の場合、例外をスローする。<BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_00087<BR>
        */
        if(ascDesc == null || "".equals(ascDesc))
        {
            log.debug("[昇順／降順] = " + ascDesc);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00087, 
                this.getClass().getName(),
                "昇順／降順が未入力");
        }
       /* 
        * 　@２－２）　@昇順／降順が不正なコード値の場合、例外をスローする。<BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_00088<BR>
        */
        if(!(WEB3AscDescDef.ASC.equals(ascDesc)) && !(WEB3AscDescDef.DESC.equals(ascDesc)))
        {
            log.debug("[昇順／降順] = " + ascDesc);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00088, 
                this.getClass().getName(),
                "昇順／降順が不正なコード値");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
