head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.20.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文受付更新インタセプタ(WEB3IfoAcceptedUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/22 盧法@旭 (中訊) 新規作成
              001: 2004/08/05 王暁傑 (中訊) Review修正
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (株価指数先物取消確認リクエスト)<BR>
 * 株価指数先物取消確認リクエストクラス
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3FuturesCancelConfirmRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="futures_cancelConfirm";
        
    /**
     * (ＩＤ)<BR>
     * 注文ＩＤ<BR>
     */
    public String id;
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407220949L;
    /**
     * @@roseuid 40F7AE1B0177
     */
    public WEB3FuturesCancelConfirmRequest() 
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
     * @@throws WEB3BaseException
     * @@roseuid 40A20E130316
     */
    public void validate() throws WEB3BaseException 
    {
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                getClass().getName() + "validate",
                "ＩＤがnullの値である。");
        }     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 40F7AE1B0196
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3FuturesCancelConfirmResponse(this);
    }
}
@
