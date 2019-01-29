head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物訂正新規建入力画面リクエストクラス(WEB3FuturesOpenMarginChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 李強(中訊) 新規作成
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (株価指数先物訂正新規建入力画面リクエスト)<BR>
 * 株価指数先物訂正新規建入力画面リクエストクラス
 * @@author 李強
 * @@version 1.0
 */
public class WEB3FuturesOpenMarginChangeInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_openMarginChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407211423L;
    /**
     * (注文ＩＤ)
     */
    public String id;
    
    /**
     * @@roseuid 40F7AE120109
     */
    public WEB3FuturesOpenMarginChangeInputRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ＩＤチェック<BR>
     * 　@this.ＩＤがnullの値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00080<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A2D0A5007E
     */
    public void validate() throws WEB3BaseException 
    {
        //ＩＤチェック
        //this.ＩＤがnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                getClass().getName() + ".validate()",
                "ＩＤがnullの値である。");
        }
    }
    

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesOpenMarginChangeInputResponse(this);
    }

}
@
