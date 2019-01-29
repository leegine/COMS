head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス履歴情報レスポンス(WEB3AdminSrvRegiServiceHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者サービス履歴情報レスポンス)<BR>
 * サービス利用管理者サービス履歴情報レスポンスクラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceHistoryResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceHistory";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151400L;    

    /**
     * (サービス区分)
     */
    public String serviceDiv;
    
    /**
     * (サービス名称)
     */
    public String serviceName;
    
    /**
     * (サービス履歴情報一覧)
     */
    public WEB3AdminSrvRegiHistoryGroup[] histories;
    
    /**
     * (サービス利用管理者サービス履歴情報レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE4F6601BC
     */
    public WEB3AdminSrvRegiServiceHistoryResponse() 
    {
     
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminSrvRegiServiceHistoryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }        
    
}
@
