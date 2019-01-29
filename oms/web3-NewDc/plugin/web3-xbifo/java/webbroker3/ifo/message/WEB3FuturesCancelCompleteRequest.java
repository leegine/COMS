head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物取消完了リクエストクラス(WEB3FuturesCancelCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/22 盧法@旭 (中訊) 新規作成
              001: 2004/08/05 王暁傑 (中訊) Review修正
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (株価指数先物取消完了リクエスト)<BR>
 * 株価指数先物取消完了リクエストクラス
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3FuturesCancelCompleteRequest extends WEB3GenRequest 
{
    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesCancelCompleteRequest.class);  

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="futures_cancelComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407220954L;    
    /**
     * (ＩＤ)<BR>
     * 注文ＩＤ<BR>
     */
    public String id;
    
    /**
     * (暗証番号)<BR>
     */
    public String password;
    
    /**
     * (確認時発注日)<BR>
     * 画面では非表示。完了リクエストで送信する値。<BR>
     */
    public Date checkDate;
    
    /**
     * @@roseuid 40F7AE1A0138
     */
    public WEB3FuturesCancelCompleteRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ＩＤチェック<BR>
     * 　@this.ＩＤがnullの値であれば例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00080<BR>
     * @@roseuid 40A20E750325
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //ＩＤチェック
        //this.ＩＤがnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                getClass().getName() + "validate",
                "ＩＤがnullの値である。");
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 40F7AE1A0157
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3FuturesCancelCompleteResponse(this);
    }
}
@
