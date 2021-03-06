head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountInfoSearchInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座情報検索条件入力レスポンス(WEB3AdminFEqConAccountInfoSearchInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外株口座情報検索条件入力レスポンス)<BR>
 * 外株口座情報検索条件入力レスポンスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountInfoSearchInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_info_search_input";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171810L;
    
    /**
     * @@roseuid 423554FD02AF
     */
    public WEB3AdminFEqConAccountInfoSearchInputResponse() 
    {
     
    }

    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminFEqConAccountInfoSearchInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
