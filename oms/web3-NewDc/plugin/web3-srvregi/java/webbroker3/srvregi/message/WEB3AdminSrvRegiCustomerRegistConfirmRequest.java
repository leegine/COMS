head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客登録確認リクエスト(WEB3AdminSrvRegiCustomerRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 張学剛 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者顧客登録確認リクエスト)<BR>
 * サービス利用管理者顧客登録確認リクエストクラス<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminSrvRegiCustomerRegistConfirmRequest extends WEB3AdminSrvRegiCustomerRegistCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_customerRegistConfirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151418L;
    
    
    /**
     * (サービス利用管理者顧客登録確認リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F22E6F01B5
     */
    public WEB3AdminSrvRegiCustomerRegistConfirmRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用管理者顧客登録確認レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F22F990261
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiCustomerRegistConfirmResponse(this);
    }
}
@
