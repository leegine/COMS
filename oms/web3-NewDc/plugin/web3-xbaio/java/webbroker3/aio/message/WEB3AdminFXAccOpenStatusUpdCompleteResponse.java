head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenStatusUpdCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX口座開設ステータス更新完了レスポンス(WEB3AdminFXAccOpenStatusUpdCompleteResponse)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX口座開設ステータス更新完了レスポンス) <BR>
 * 管理者・FX口座開設ステータス更新完了レスポンスクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenStatusUpdCompleteResponse extends
    WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_open_status_upd_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * @@roseuid 41E78F6502AF
     */
    public WEB3AdminFXAccOpenStatusUpdCompleteResponse()
    {
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminFXAccOpenStatusUpdCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
