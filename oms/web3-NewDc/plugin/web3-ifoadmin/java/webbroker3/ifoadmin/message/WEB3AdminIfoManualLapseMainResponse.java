head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoManualLapseMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効メインレスポンス(WEB3AdminIfoManualLapseMainResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/

package webbroker3.ifoadmin.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (管理者・先物OP手動失効メインレスポンス)<BR>
 * 管理者・先物OP手動失効メインレスポンスクラス<BR>
 * <BR>
 * @@author 謝旋(中訊)
 * @@version 1.0
 */

public class WEB3AdminIfoManualLapseMainResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "adminIfo_manualLapseMain";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200701311315L;
    
    /**
     * @@roseuid 447AB8F40119
     */
    public WEB3AdminIfoManualLapseMainResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - リクエストオブジェクト
     */
    protected WEB3AdminIfoManualLapseMainResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    } 
}
@
