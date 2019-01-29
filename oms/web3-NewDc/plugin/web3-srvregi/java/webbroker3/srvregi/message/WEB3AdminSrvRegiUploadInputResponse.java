head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiUploadInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客データアップロード入力レスポンス(WEB3AdminSrvRegiUploadInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者顧客データアップロード入力レスポンス)<BR>
 * サービス利用管理者アップロード入力レスポンスクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiUploadInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_uploadInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151639L;
    
    /**
     * (アップロード状況)<BR>
     * 0:処理中　@1:処理済<BR>
     */
    public String uploadStatus;
    
    /**
     * (終了日時)
     */
    public Date endDate;
    
    /**
     * (サービス利用管理者アップロード入力レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 4100769E0049
     */
    public WEB3AdminSrvRegiUploadInputResponse() 
    {
     
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminSrvRegiUploadInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
