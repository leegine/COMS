head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス変更完了レスポンス(WEB3AdminSrvRegiServiceChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者サービス変更完了レスポンス)<BR>
 * サービス利用管理者サービス変更完了レスポンスクラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceChangeCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151400L;
    
    
    /**
     * (サービス利用管理者サービス変更完了レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE48000054
     */
    public WEB3AdminSrvRegiServiceChangeCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminSrvRegiServiceChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }        
    
}
@
