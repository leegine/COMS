head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.30.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiUploadConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客データアップロード確認レスポンス(WEB3AdminSrvRegiUploadConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者顧客データアップロード確認レスポンス)<BR>
 * サービス利用管理者アップロード確認レスポンスクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiUploadConfirmResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_uploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151639L;
    
    /**
     * (アップロード件数)
     */
    public String lineCount;
    
    /**
     * (アップロードID)
     */
    public String uploadId;
    
    /**
     * (初期申込区分)
     * null:表示対象外
     * 0:無
     * 1:有
     */
    public String firstApplyDiv;
    
    /**
     * (サービス利用管理者アップロード確認レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 41009077002E
     */
    public WEB3AdminSrvRegiUploadConfirmResponse() 
    {
     
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminSrvRegiUploadConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
