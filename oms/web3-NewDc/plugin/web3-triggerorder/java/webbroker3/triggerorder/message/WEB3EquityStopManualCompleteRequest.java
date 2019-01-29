head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3EquityStopManualCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式逆指値注文手動発注完了リクエスト(WEB3EquityStopManualCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06　@魏新(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (株式逆指値注文手動発注完了リクエスト)<BR>
 * 株式逆指値注文手動発注完了リクエスト<BR>
 * 
 * @@author 魏新
 * @@version 1.0
 */
public class WEB3EquityStopManualCompleteRequest extends WEB3EquityManualCompleteRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_stop_manual_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603071345L;

    /**
     * コンストラクタ<BR>
     * @@roseuid 43F488920196
     */
    public WEB3EquityStopManualCompleteRequest() 
    {
     
    }
    
    /**
     * (createResponseの実装)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3EquityManualCompleteResponse();        
    }
    
}
@
