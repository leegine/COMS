head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座への振替入力レスポンス(WEB3FEqConTransferInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外株口座への振替入力レスポンス)<BR>
 * 外株口座への振替入力レスポンスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FEqConTransferInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "feq_con_transfer_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;

    
    /**
     * (取引口座番号)<BR>
     * 取引口座番号
     */
    public String tradeAccountCode;
    
    /**
     * (振替可能額)<BR>
     * 振替可能額
     */
    public String changePossAmt;
    
    /**
     * @@roseuid 42354D7702DE
     */
    public WEB3FEqConTransferInputResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FEqConTransferInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
