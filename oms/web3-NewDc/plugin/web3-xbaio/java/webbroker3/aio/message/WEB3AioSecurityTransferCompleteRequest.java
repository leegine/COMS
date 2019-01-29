head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替完了リクエスト(WEB3AioSecurityTransferCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (証券振替完了リクエスト)<BR>
 * 証券振替完了リクエストクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferCompleteRequest extends WEB3AioSecurityTransferCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_security_transfer_complete";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412071521L;     
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferCompleteRequest.class);
    
    /**
     * (振替数量)<BR>
     * 画面にて入力された振替数量
     */
    public String changeQuantity;
    
    /**
     * (暗証番号)<BR>
     * 画面にて入力された暗証番号
     */
    public String password;
    
    /**
     * (確認時発注日)<BR>
     * 確認時発注日
     */
    public Date checkDate;
    
    /**
     * (注文ID)<BR>
     * 確認時に取得した注文ID
     */
    public String orderId;
    
    /**
     * @@roseuid 41B0255E0138
     */
    public WEB3AioSecurityTransferCompleteRequest() 
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
     *   の場合、例外をスローする。<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01298<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01299<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01300<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01301<BR> 
     * <BR>
     * ３）確認時発注日<BR>
     * <BR>
     *   リクエストデータ.確認時発注日 = null <BR>
     * <BR>
     *   の場合、例外をスローする。 <BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00078<BR> 
     * <BR>
     * ４）注文ID<BR>
     * <BR>
     *   リクエストデータ.注文ID = null <BR>
     * <BR>
     *   の場合、例外をスローする。
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00600<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4153D4DA024A
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
        if (WEB3StringTypeUtility.isEmpty(this.changeQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01298,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.振替数量 = null");                    
        }
        else if (!WEB3StringTypeUtility.isNumber(this.changeQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01301,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.振替数量 != 半角数字, " +
                "リクエストデータ.振替数量 = " + this.changeQuantity);             
        } 
        else if (Double.parseDouble(this.changeQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01299,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.振替数量 <= 0, " +
                "リクエストデータ.振替数量 = " + this.changeQuantity);             
        }
        else if (this.changeQuantity.length() >= 10)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01300,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.振替数量.length() >= 10, " +
                "リクエストデータ.振替数量.length() = " + this.changeQuantity.length());             
        }
       

        //３）確認時発注日
        //  リクエストデータ.確認時発注日 = null 
        //  の場合、例外をスローする。 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00078 
        if (this.checkDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.確認時発注日 = null ");             
        }

        //４）注文ID
        //  リクエストデータ.注文ID = null 
        //  の場合、例外をスローする。
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00600     
        if (WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.注文ID = null");             
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 証券振替完了レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioSecurityTransferCompleteResponse(this);
    }
}
@
