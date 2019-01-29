head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション訂正返済入力画面リクエスト(WEB3OptionsCloseMarginChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 李海波 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株価指数オプション訂正返済入力画面リクエスト)<BR>
 * 株価指数オプション訂正返済入力画面リクエストクラス<BR>
 * @@author 李海波
 * @@version 1.0 
 */
public class WEB3OptionsCloseMarginChangeInputRequest extends WEB3GenRequest 
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionsCloseMarginChangeInputRequest.class);
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="options_closeMarginChangeInput";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406141530L;
       
    /**
     * 注文ＩＤ
     */
    public String id;
   
    /**
     * @@roseuid 40C0A8EB0157
     */
    public WEB3OptionsCloseMarginChangeInputRequest() 
    {
    
    }
   
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ＩＤチェック<BR>
     * 　@this.ＩＤがnullの値であれば例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
     * @@throws WEB3BaseException
     * @@roseuid 406A173501B8
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //ＩＤチェック
        if(WEB3StringTypeUtility.isEmpty(this.id))
        {
             throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + "validate",
                "ＩＤがnullの値である。"); 
        }
        
        log.exiting(STR_METHOD_NAME);
    }
   
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3OptionsCloseMarginChangeInputResponse(this);
    }
}
@
