head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替確認リクエスト(WEB3AioSecurityTransferConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (証券振替確認リクエスト)<BR>
 * 証券振替確認リクエストクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferConfirmRequest extends WEB3AioSecurityTransferCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_security_transfer_confirm";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412071344L;     
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferConfirmRequest.class);
    
    /**
     * (振替数量)<BR>
     * 画面にて入力された振替数量
     */
    public String transferQuantity;
    
    /**
     * @@roseuid 41B0255E000F
     */
    public WEB3AioSecurityTransferConfirmRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）スーパークラスのvalidate()メソッドをコールする。<BR>
     * <BR>
     * ２）振替数量<BR>
     * <BR>
     *   リクエストデータ.振替数量 = null or<BR>
     *   リクエストデータ.振替数量 <= 0 or<BR>
     *   リクエストデータ.振替数量.length() >= 10 or<BR>
     *   リクエストデータ.振替数量 != 半角数字<BR>
     * <BR>
     *   の場合、例外をスローする。
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01298<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01299<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01300<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01301<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4153D33A0112
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        //１）スーパークラスのvalidate()メソッドをコールする。
        super.validate();
        
        //２）振替数量
        //  リクエストデータ.振替数量 = null or
        //  リクエストデータ.振替数量 <= 0 or
        //  リクエストデータ.振替数量.length() >= 10 or
        //  リクエストデータ.振替数量 != 半角数字
        //  の場合、例外をスローする。
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01298 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01299 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01300 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01301
        if (WEB3StringTypeUtility.isEmpty(this.transferQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01298,
                this.getClass().getName() + "." + l_strMethodName,
                "振替数量 = null");                    
        }
        else if (!WEB3StringTypeUtility.isNumber(this.transferQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01301,
                this.getClass().getName() + "." + l_strMethodName,
                "振替数量 != 半角数字 and 振替数量 = " + this.transferQuantity);             
        }
        else if (Double.parseDouble(this.transferQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01299,
                this.getClass().getName() + "." + l_strMethodName,
                "振替数量 <= 0 and 振替数量 = " + this.transferQuantity);             
        }
        else if (this.transferQuantity.length() >= 10)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01300,
                this.getClass().getName() + "." + l_strMethodName,
                "振替数量.length() >= 10 and 振替数量.length() = " + this.transferQuantity.length());             
        }
      
        log.exiting(l_strMethodName);
    }
    
    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 証券振替確認レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioSecurityTransferConfirmResponse(this);
    }
}
@
