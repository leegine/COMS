head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 問合せ管理ソートキー(WEB3FaqSortKey)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (問合せ管理ソートキー)<BR>
 * 問合せ管理ソートキー<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3FaqSortKey extends Message 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FaqSortKey.class);
        
    /**
     * (キー項目)<BR>
     * キー項目<BR>
     * <BR>
     * 当クラスを利用したリクエストに対してのレスポンスクラス中のシンボル名を<BR>
     * キー項目とする。 <BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     */
    public String keyItem;
    
    /**
     * (昇順／降順)<BR>
     * A：昇順　@D：降順<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 41C25C09000F
     */
    public WEB3FaqSortKey() 
    {
     
    }
    
    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@キー項目のチェック<BR>
     * 　@１−１）　@キー項目が未入力lの場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00085<BR>
     * <BR>
     * ２）　@昇順／降順のチェック<BR>
     * 　@２−１）　@昇順／降順が未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00087<BR>
     * 　@２−２）　@昇順／降順が不正なコード値の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41A6FBF503D5
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
       
        //１）　@キー項目のチェック
        //１−１）　@キー項目が未入力の場合、例外をスローする。

        if (WEB3StringTypeUtility.isEmpty(keyItem))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085, 
                this.getClass().getName(),
                "キー項目が未入力。");
        }
        
        //２）　@昇順／降順のチェック
        //２−１）　@昇順／降順が未入力の場合、例外をスローする。<BR>
        if (WEB3StringTypeUtility.isEmpty(ascDesc))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087, 
                this.getClass().getName(),
                "昇順／降順が未入力。");
        }    
        
        //２−２）　@昇順／降順が不正なコード値の場合、例外をスローする。
        if (!(WEB3AscDescDef.ASC.equals(ascDesc)) && !(WEB3AscDescDef.DESC.equals(ascDesc)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088, 
                this.getClass().getName(),
                "昇順／降順が”A：昇順”、”D：降順”以外の値です。" + "[昇順／降順] = " + ascDesc
                );
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
