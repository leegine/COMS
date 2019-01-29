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
filename	WEB3AdminIfoManualLapseStatusRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効処理ステータス確認リクエスト(WEB3AdminIfoManualLapseStatusRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/

package webbroker3.ifoadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・先物OP手動失効処理ステータス確認リクエスト)<BR>
 * 管理者・先物OP手動失効処理ステータス確認リクエストクラス<BR>
 * <BR>
 * @@author 謝旋(中訊)
 * @@version 1.0
 */

public class WEB3AdminIfoManualLapseStatusRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "adminIfo_manualLapseStatus";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200701311315L;
    
    /**
     * @@roseuid 447AB8F4031C
     */
    public WEB3AdminIfoManualLapseStatusRequest() 
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
        return new WEB3AdminIfoManualLapseStatusResponse(this);
    }
}
@
