head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.30.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス登録完了リクエスト(WEB3AdminSrvRegiServiceRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者サービス登録完了リクエスト)<BR>
 * サービス利用管理者サービス登録完了リクエストクラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceRegistCompleteRequest extends WEB3AdminSrvRegiServiceRegistCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceRegistComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151400L;
    
    /**
     * (暗証番号)
     */
    public String password;
    
    /**
     * (サービス利用管理者サービス登録完了リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE31770006
     */
    public WEB3AdminSrvRegiServiceRegistCompleteRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用管理者サービス登録完了レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40EE31770016
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiServiceRegistCompleteResponse(this);
    }
}
@
