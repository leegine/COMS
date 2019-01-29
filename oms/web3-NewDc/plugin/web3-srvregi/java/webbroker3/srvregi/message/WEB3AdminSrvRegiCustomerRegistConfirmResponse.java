head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerRegistConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客登録確認レスポンス(WEB3AdminSrvRegiCustomerRegistConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 張学剛 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者顧客登録確認レスポンス)<BR>
 * サービス利用管理者顧客登録確認レスポンスクラス<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminSrvRegiCustomerRegistConfirmResponse extends WEB3GenResponse 
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
     * (サービス利用管理者顧客登録確認レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F22FDA03B8
     */
    public WEB3AdminSrvRegiCustomerRegistConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminSrvRegiCustomerRegistConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
