head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス一覧リクエスト(WEB3AdminSrvRegiServiceReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者サービス一覧リクエスト)<BR>
 * サービス利用管理者サービス一覧リクエストクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceReferenceRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151448L;
    
    /**
     * (サービス利用管理者サービス一覧リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE2D4B01FA
     */
    public WEB3AdminSrvRegiServiceReferenceRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用管理者サービス一覧レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40EE2D4302B6
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiServiceReferenceResponse(this);
    }
}
@
