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
filename	WEB3AdminIfoManualLapseRunResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効処理起動レスポンス(WEB3AdminIfoManualLapseRunResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/

package webbroker3.ifoadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・先物OP手動失効処理起動レスポンス)<BR>
 * 管理者・先物OP手動失効処理起動レスポンスクラス<BR>
 * <BR>
 * @@author 謝旋(中訊)
 * @@version 1.0
 */

public class WEB3AdminIfoManualLapseRunResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "adminIfo_manualLapseRun";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200701311315L;
    
    /**
     * (現在時刻)<BR>
     * 現在時刻
     */
    public Date currentTime;

    
    /**
     * @@roseuid 447AB8F40290
     */
    public WEB3AdminIfoManualLapseRunResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - リクエストオブジェクト
     */
    protected WEB3AdminIfoManualLapseRunResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
