head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiUploadStateRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客データUL状況照会リクエスト(WEB3AdminSrvRegiUploadStateRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用管理者顧客データUL状況照会リクエスト)<BR>
 * サービス利用管理者顧客データUL状況照会リクエスト　@クラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiUploadStateRequest extends WEB3GenRequest 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiUploadStateRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_uploadState";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151639L;
    
    /**
     * (アップロードID)
     */
    public String uploadId;
    
    /**
     * (サービス利用管理者顧客データUL状況照会リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 411AC95B008A
     */
    public WEB3AdminSrvRegiUploadStateRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用管理者顧客データUL状況照会レスポンスオブジェクトを<BR>
     * 生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 411AC96201E2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiUploadStateResponse();
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) アップロードIDのチェック<BR>
     *  this.アップロードID==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00973<BR>
     * @@roseuid 411AC97D033A
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //1) アップロードIDのチェック
        if(this.uploadId == null || "".equals(this.uploadId.trim()))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00973,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("アップロードIDエラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
