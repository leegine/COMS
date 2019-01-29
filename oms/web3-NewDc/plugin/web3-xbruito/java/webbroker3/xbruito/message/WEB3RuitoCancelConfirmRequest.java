head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資取消確認リクエストトクラス(WEB3RuitoCancelConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成 
*/
package webbroker3.xbruito.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 累積投資取消確認リクエストクラス<BR>
 */
public class WEB3RuitoCancelConfirmRequest extends WEB3GenRequest
{
	/**
	* ログ出力ユーティリティ
	*/
   private static WEB3LogUtility log =
	   WEB3LogUtility.getInstance(WEB3RuitoCancelConfirmRequest.class);
	   	
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_cancel_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      

    /**
     * 注文ID<BR>
     */
    public String id;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922C3F0186
     */
    public WEB3RuitoCancelConfirmRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ＩＤチェック<BR>
     * 　@this.ＩＤがnullの値である場合、例外をスローする。<BR>
     *   class    : WEB3BusinessLayerException<BR>
     *   tag      : BUSINESS_ERROR_00080<BR>
     * @@throws WEB3BaseException
     * @@roseuid 407367780338
     */
    public void validate() throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "validate()";
    	log.entering(STR_METHOD_NAME);

        //ＩＤがnullの値である場合、例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("ＩＤが未指定です。");              
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "ＩＤが未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 累投取消確認レスポンスオブジェクトを生成して返す。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 408334C00265
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoCancelConfirmResponse(this);
    }
}
@
