head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座への振替取消完了レスポンス(WEB3FEqConTransferCancelCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/17 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import java.util.Date;

/**
 * (外株口座への振替取消完了レスポンス)<BR>
 * 外株口座への振替取消完了レスポンスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FEqConTransferCancelCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "feq_con_transfer_cancel_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;

    /**
     * (取消日時)<BR>
     * 取消日時
     */
    public Date cancelDate;
    
    /**
     * @@roseuid 4235526F0186
     */
    public WEB3FEqConTransferCancelCompleteResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FEqConTransferCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
