head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.08.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設ソートキー(WEB3AccOpenSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 張学剛 新規作成
*/
package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (口座開設ソートキー)<BR>
 * 口座開設ソートキー<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AccOpenSortKey extends Message 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenSortKey.class);
    
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
     * @@roseuid 41B45E7C003E
     */
    public WEB3AccOpenSortKey() 
    {
     
    }
    
    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@キー項目のチェック<BR>
     * 　@１－１）　@キー項目が未入力lの場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00085<BR>
     * <BR>
     * ２）　@昇順／降順のチェック<BR>
     * 　@２－１）　@昇順／降順が未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00087<BR>
     * 　@２－２）　@昇順／降順が不正なコード値の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 418F4ABF0240
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）キー項目のチェック
        //１－１）キー項目が未入力lの場合、例外をスローする。
        if (keyItem == null || "".equals(keyItem.trim()))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                getClass().getName() + STR_METHOD_NAME,
                "キー項目が未入力lの場合、例外をスローする");
                
            log.debug("口座開設.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;     
        }
        
        //２）昇順／降順のチェック
        //２－１）昇順／降順が未入力の場合、例外をスローする。
        if (ascDesc == null || "".equals(ascDesc.trim()))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                getClass().getName() + STR_METHOD_NAME,
                "昇順／降順が未入力の場合、例外をスローする");
                
            log.debug("口座開設.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;    
        }
        
        //２－２）昇順／降順が不正なコード値の場合、例外をスローする。
        if (!ascDesc.equals(WEB3AscDescDef.ASC) && !ascDesc.equals(WEB3AscDescDef.DESC))
        {            
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                getClass().getName() + STR_METHOD_NAME,
                "昇順／降順が不正なコード値の場合、例外をスローする");
                
            log.debug("口座開設.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;    
        }
        
        log.exiting(STR_METHOD_NAME);       
    }
}@
