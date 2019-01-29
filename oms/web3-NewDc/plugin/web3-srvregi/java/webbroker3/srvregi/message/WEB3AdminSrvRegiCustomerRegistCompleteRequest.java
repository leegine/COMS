head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客登録完了リクエスト(WEB3AdminSrvRegiCustomerRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 張学剛 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者顧客登録完了リクエスト)<BR>
 * サービス利用管理者顧客登録完了リクエストクラス<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminSrvRegiCustomerRegistCompleteRequest extends WEB3AdminSrvRegiCustomerRegistCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_customerRegistComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151418L;
    
    /**
     * (暗証番号)
     */
    public String password;
    
    /**
     * (サービス利用管理者顧客登録完了リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F22F94028F
     */
    public WEB3AdminSrvRegiCustomerRegistCompleteRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用管理者顧客登録完了レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F22F9402AF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiCustomerRegistCompleteResponse(this);
    }
}
@
