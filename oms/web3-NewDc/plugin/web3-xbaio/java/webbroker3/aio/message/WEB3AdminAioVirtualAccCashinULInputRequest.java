head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioVirtualAccCashinULInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : バーチャル口座入金UL入力リクエスト(WEB3AdminAioVirtualAccCashinULInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/09 李小健 (中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (バーチャル口座入金UL入力リクエスト)<BR>
 * バーチャル口座入金UL入力リクエストクラス<BR>
 * 
 * @@author 李小健(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioVirtualAccCashinULInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_aio_virtual_acc_cashin_ul_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200605091657L;
        
    /**
     * ログ出力ユーティリティ。
     */
    public static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3AdminAioVirtualAccCashinULInputRequest.class);
    
    /**
     * @@roseuid 4158EB620313
     */
    public WEB3AdminAioVirtualAccCashinULInputRequest() 
    {
    }        
    
    /**
     * (createResponseの実装）<BR>
     * <BR>
     * バーチャル口座入金UL入力レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB620327
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioVirtualAccCashinULInputResponse(this);
    }
}
@
