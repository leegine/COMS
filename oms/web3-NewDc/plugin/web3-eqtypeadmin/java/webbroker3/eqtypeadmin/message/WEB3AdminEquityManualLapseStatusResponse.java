head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityManualLapseStatusResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式手動失効処理ステータス確認レスポンス(WEB3AdminEquityManualLapseStatusResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30　@肖志偉(中訊) 新規作成
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・株式手動失効処理ステータス確認レスポンス)<BR>
 * 管理者・株式手動失効処理ステータス確認レスポンスクラス<BR>
 * <BR>
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */

public class WEB3AdminEquityManualLapseStatusResponse extends WEB3GenResponse 
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
     * (処理ステータス)<BR>
     * 処理ステータス<BR>
     * <BR>
     * 1：　@処理済<BR>
     * 5：　@処理中<BR>
     * 9：　@エラー<BR>
     */
    public String lapseStatus;
    
    /**
     * (現在時刻)<BR>
     * 現在時刻
     */
    public Date currentTime;
    
    /**
     * @@roseuid 447AB8F403B9
     */
    public WEB3AdminEquityManualLapseStatusResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - リクエストオブジェクト
     */
    protected WEB3AdminEquityManualLapseStatusResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
