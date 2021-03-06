head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス変更確認リクエスト(WEB3AdminSrvRegiServiceChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者サービス変更確認リクエスト)<BR>
 * サービス利用管理者サービス変更確認リクエストクラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceChangeConfirmRequest extends WEB3AdminSrvRegiServiceChangeCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151400L;
    
    /**
     * (サービス利用管理者サービス変更確認リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE4730013F
     */
    public WEB3AdminSrvRegiServiceChangeConfirmRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用管理者サービス変更確認レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40EE47C00093
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiServiceChangeConfirmResponse(this);
    }
}
@
