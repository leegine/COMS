head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設管理ステータス更新完了リクエスト(WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (外株口座開設管理ステータス更新完了リクエスト)<BR>
 * 外株口座開設管理ステータス更新完了リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest extends WEB3AdminFEqConAccountMngStateChangeCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_status_upd_complete";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号
     */
    public String password;
    
    /**
     * @@roseuid 423552EB00CB
     */
    public WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 外株口座開設管理ステータス更新完了レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse(this);
    }
}
@
