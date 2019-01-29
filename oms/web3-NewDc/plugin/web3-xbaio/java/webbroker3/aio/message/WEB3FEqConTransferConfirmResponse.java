head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座への振替確認レスポンス(WEB3FEqConTransferConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/17 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import java.util.Date;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外株口座への振替確認レスポンス)<BR>
 * 外株口座への振替確認レスポンスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FEqConTransferConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "feq_con_transfer_confirm";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;

    /**
     * (振替可能額)<BR>
     * 振替可能額
     */
    public String changePossAmt;
    
    /**
     * (確認時発注日)<BR>
     * 確認時発注日
     */
    public Date checkDate;
    
    /**
     * (受渡予定日)<BR>
     * 受渡予定日
     */
    public Date deliveryScheduledDate;
    
    /**
     * @@roseuid 42354D780203
     */
    public WEB3FEqConTransferConfirmResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FEqConTransferConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
