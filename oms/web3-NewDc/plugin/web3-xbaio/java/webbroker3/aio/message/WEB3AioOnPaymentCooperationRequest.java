head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOnPaymentCooperationRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金連携リクエスト(WEB3AioOnPaymentCooperationRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 李俊 (中訊) 新規作成
*/
package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (出金連携リクエスト)<BR>
 * 出金連携リクエストクラス
 * @@author 李俊
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationRequest extends WEB3BackRequest
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOnPaymentCooperationRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_on_payment_cooperation";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511161604L;
    
    /**
     * 証券会社コード<BR>
     */
    public String institutionCode;
    
    /**
     * デフォルトコンストラク<BR>
     */
	public WEB3AioOnPaymentCooperationRequest() {

	}
    /**
    * 当リクエストデータの整合性チェックを行う。<BR>  
    * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>  
    * <BR>
    * １）証券会社コードのチェック<BR>
    *  this.証券会社コード＝null の場合、<BR>  
    * 「証券会社コードの指定なし」の例外をthrowする。<BR>
    * <BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00827<BR> 
    * <BR>
    */    
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        // this.証券会社コード＝null の場合
        // 「証券会社コードの指定なし」の例外をthrowする。
        if(WEB3StringTypeUtility.isEmpty(this.institutionCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.証券会社コード = null");
        }
        
        log.exiting(STR_METHOD_NAME);
    }   
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB330258
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioOnPaymentCooperationResponse(this);
    }    
    
}
@
