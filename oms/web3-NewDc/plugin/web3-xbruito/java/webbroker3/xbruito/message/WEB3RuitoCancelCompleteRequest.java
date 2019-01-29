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
filename	WEB3RuitoCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資取消完了リクエストクラス(WEB3RuitoCancelCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 累積投資取消完了リクエストクラス<BR>
 */
public class WEB3RuitoCancelCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_cancel_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      
    
    /**
     * ログ出力ユーティリティ。
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoCancelCompleteRequest.class);

    /**
     * 確認時発注日<BR>
     * <BR>
     * 完了リクエストで送信する値を格納する。<BR>
     * （画面では非表示）<BR>
     */
    public Date checkDate;

    /**
     * 暗証番号<BR>
     */
    public String password;

    /**
     * 注文ID<BR>
     */
    public String id;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922C5B01F3
     */
    public WEB3RuitoCancelCompleteRequest()
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
     * <BR>
     * ２）　@確認時発注日チェック<BR>
     * 　@this.確認時発注日がnullの値である場合、例外をスローする。<BR>
     *   class    : WEB3BusinessLayerException<BR>
     *   tag      : BUSINESS_ERROR_00078<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4073677D0134
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

        //確認時発注日がnullの値である場合、例外をスローする   
        if (this.checkDate == null)
        {
            log.debug("確認時発注日が入力されていません。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "確認時発注日が入力されていません。");
        }

		log.exiting(STR_METHOD_NAME);
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 累投取消完了レスポンスオブジェクトを生成して返す。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 408335340003
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoCancelCompleteResponse(this);
    }
}
@
