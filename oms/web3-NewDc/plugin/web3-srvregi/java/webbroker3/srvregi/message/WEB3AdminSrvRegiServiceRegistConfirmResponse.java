head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceRegistConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス登録確認レスポンス(WEB3AdminSrvRegiServiceRegistConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;



/**
 * (サービス利用管理者サービス登録確認レスポンス)<BR>
 * サービス利用管理者サービス登録確認レスポンスクラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceRegistConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceRegistConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151400L;
        
    /**
     * (差出人（確認メール）)
     */
    public String confirmMailFrom;
    
    /**
     * (件名（確認メール）)
     */
    public String confirmMailSubject;
    
    /**
     * (メールヘッダー（確認メール）)
     */
    public String confirmMailHeader;
    
    /**
     * (メール本文（確認メール）)
     */
    public String confirmMailBody;
    
    /**
     * (メールフッター（確認メール）)
     */
    public String confirmMailFooter;
    
    /**
     * (差出人（契約期限メール）)
     */
    public String noticeMailFrom;
    
    /**
     * (件名（契約期限メール）)
     */
    public String noticeMailSubject;
    
    /**
     * (メールヘッダー（契約期限メール）)
     */
    public String noticeMailHeader;
    
    /**
     * (メール本文（契約期限メール）)
     */
    public String noticeMailBody;
    
    /**
     * (メールフッター（契約期限メール）)
     */
    public String noticeMailFooter;
    
    /**
     * (サービス利用管理者サービス登録確認レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE324800F0
     */
    public WEB3AdminSrvRegiServiceRegistConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected  WEB3AdminSrvRegiServiceRegistConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }        
    
}
@
