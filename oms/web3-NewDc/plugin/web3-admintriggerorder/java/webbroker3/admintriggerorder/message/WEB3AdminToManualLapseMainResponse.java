head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToManualLapseMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・手動失効メインレスポンス(WEB3AdminToManualLapseMainResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * (トリガー注文管理者・手動失効メインレスポンス)<BR>
 * トリガー注文管理者・手動失効メインレスポンス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToManualLapseMainResponse extends WEB3BackResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_manual_lapse_main";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603170900L;
    
    /**
     * @@roseuid 441A08CD0157
     */
    public WEB3AdminToManualLapseMainResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminToManualLapseMainResponse(WEB3AdminToManualLapseMainRequest l_request)
    {
        super(l_request);
    }
}
@
