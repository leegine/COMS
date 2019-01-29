head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioVirtualAccCashinULInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : バーチャル口座入金UL入力レスポンス(WEB3AdminAioVirtualAccCashinULInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/5/9 李小健 (中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (バーチャル口座入金UL入力レスポンス)<BR>
 * バーチャル口座入金UL入力レスポンスクラス<BR>
 * 
 * @@author 李小健(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioVirtualAccCashinULInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_virtual_acc_cashin_ul_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200605091615L;
    
    /**
     * (アップロード履歴一覧)<BR>
     */
    public WEB3AioUploadHistoryUnit uploadHistoryList;
    
    /**
     * @@roseuid 423552AB00FA
     */
    public WEB3AdminAioVirtualAccCashinULInputResponse() 
    {
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3AdminAioVirtualAccCashinULInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}
@
