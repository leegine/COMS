head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoPasswordChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報暗証番号変更リクエスト(WEB3AccInfoPasswordChangeCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (お客様情報暗証番号変更リクエスト)<BR>
 * お客様情報暗証番号変更リクエスト<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoPasswordChangeCompleteRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoPasswordChangeCompleteRequest.class);      
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_passwordChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082152L;

    /**
     * (ログイン名)<BR>
     * ログイン名<BR>
     * ※ 画面から入力されるユーザコード<BR>
     */
    public String loginName;

    /**
     * (旧暗証番号)<BR>
     * 旧暗証番号<BR>
     */
    public String oldPassword;

    /**
     * (新暗証番号１)<BR>
     * 新暗証番号１<BR>
     */
    public String newPassword1;

    /**
     * (新暗証番号２)<BR>
     * 新暗証番号２<BR>
     */
    public String newPassword2;

    /**
     * @@roseuid 418F39F603B9
     */
    public WEB3AccInfoPasswordChangeCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoPasswordChangeCompleteResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@ログイン名のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01091<BR>
     * <BR>
     * ２）　@旧暗証番号のチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_90210<BR>
     * <BR>
     * ３）　@新暗証番号１，新暗証番号２のチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01093<BR>
     * 　@３－２）　@新暗証番号１と新暗証番号２が同一でない場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_90211<BR>
     * @@throws WEB3BaseException
     * @@roseuid 416B8322003A
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ログイン名のチェック
        //１－１）　@未入力の場合、例外をスローする。
        
        if (this.loginName == null || "".equals(this.loginName))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01091, 
                this.getClass().getName() + STR_METHOD_NAME,
                " ログイン名を入力しません");  
        }
        
        //２）　@旧暗証番号のチェック 
        //２－１）　@未入力の場合、例外をスローする。
        if (this.oldPassword == null || "".equals(this.oldPassword))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_90210, 
                this.getClass().getName() + STR_METHOD_NAME,
                " パスワードが変更できませんでした。コールセンターにお問合せ下さい");  
        }
        
        //３）　@新暗証番号１，新暗証番号２のチェック
        //３－１）　@未入力の場合、例外をスローする。 
        if (this.newPassword1 == null || "".equals(this.newPassword1)
            || this.newPassword2 == null || "".equals(this.newPassword2))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01093, 
                this.getClass().getName() + STR_METHOD_NAME,
                " 新暗証番号１或いは新暗証番号２を入力しません");  
        }
        
        //３－２）　@新暗証番号１と新暗証番号２が同一でない場合、例外をスローする。
        if (!this.newPassword1.equals(this.newPassword2))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_90211, 
                this.getClass().getName() + STR_METHOD_NAME,
                " 新パスワードが確認用のものと一致しておりません。ご確認の上、再度入力して下さい");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
