head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金通知訂正入力画面リクエスト(WEB3AdminAioCashinNoticeChangeInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/20 李俊 (中訊) 新規作成
                 : 2006/11/09 徐宏偉(中訊) 仕様変更 モデル 682
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (入金通知訂正入力画面リクエスト)<BR>
 * 入金通知訂正入力画面リクエストクラス<BR>
 * 
 * @@author 李俊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinNoticeChangeInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashin_notice_change_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200601211657L;    
        
    /**
     * (入金通知テーブルID)<BR>
     * データ取込区分 + 入金通知テーブルID<BR>
     */
    public String[] cashinNoticeTableId;
    
    /**
     * ログ出力ユーティリティ。
     */
    public static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinNoticeChangeInputRequest.class);

    
    /**
     * @@roseuid 4158EB620313
     */
    public WEB3AdminAioCashinNoticeChangeInputRequest() 
    {
     
    }
   
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 入金通知訂正入力画面レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB620327
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashinNoticeChangeInputResponse(this);
    }
    
    /**
     * 入金通知テーブルIDの配列あるいは<BR>
     * その要素がnullの場合<BR>
     * 「入金通知テーブルID未指定エラー」をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:   BUSINESS_ERROR_02345<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        

        if (this.cashinNoticeTableId == null)
        {
        log.debug("入金通知テーブルID未指定です。");
        throw new WEB3BusinessLayerException(
            WEB3ErrorCatalog.BUSINESS_ERROR_02345,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            "入金通知テーブルID未指定です。");
        }
        
        for (int i = 0; i < this.cashinNoticeTableId.length; i++)
        {
            if (WEB3StringTypeUtility.isEmpty(this.cashinNoticeTableId[i]))
            {
            log.debug("入金通知テーブルID未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02345,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入金通知テーブルID未指定です。");
            }
        }
       
    }
}
@
