head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityManualLapseStatusRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式手動失効処理ステータス確認リクエスト(WEB3AdminEquityManualLapseStatusRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30　@肖志偉(中訊) 新規作成
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・株式手動失効処理ステータス確認リクエスト)<BR>
 * 管理者・株式手動失効処理ステータス確認リクエストクラス<BR>
 * <BR>
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */

public class WEB3AdminEquityManualLapseStatusRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "adminEquity_manualLapseStatus";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200605291315L;
    
    /**
     * @@roseuid 447AB8F4031C
     */
    public WEB3AdminEquityManualLapseStatusRequest() 
    {
     
    }
    
    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 管理者・株式手動失効処理ステータス確認レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6301A2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminEquityManualLapseStatusResponse(this);
    }
}
@
