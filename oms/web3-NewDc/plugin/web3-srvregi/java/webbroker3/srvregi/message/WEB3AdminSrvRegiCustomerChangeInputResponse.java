head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客変更入力レスポンス(WEB3AdminSrvRegiCustomerChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 張学剛 新規作成
*/
package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者顧客変更入力レスポンス)<BR>
 * サービス利用管理者顧客変更入力レスポンスクラス<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminSrvRegiCustomerChangeInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_customerChangeInput";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151418L;
    
    /**
     * (サービス名称)
     */
    public String serviceName;
    
    /**
     * (変更顧客一覧)
     */
    public webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeGroup[ ] chgCustomerList;
    
    /**
     * (サービス利用管理者顧客変更入力レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F4EC390354
     */
    public WEB3AdminSrvRegiCustomerChangeInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminSrvRegiCustomerChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
