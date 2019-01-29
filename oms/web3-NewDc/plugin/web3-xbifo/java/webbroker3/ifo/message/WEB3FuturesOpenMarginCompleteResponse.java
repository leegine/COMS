head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物新規建注文完了レスポンスクラス(WEB3FuturesOpenMarginCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 呉艶飛 新規作成
*/
package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (株価指数先物新規建注文完了レスポンス)<BR>
 * 株価指数先物新規建注文完了レスポンスクラス<BR>
 * 
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3FuturesOpenMarginCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="futures_OpenMarginComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201555L;
    
    /**
     * (更新時間)<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (識別番号)<BR>
     * 注文履歴ＩＤ<BR>
     */
    public String orderActionId;
    
    /**
     * @@roseuid 40F7AE130128
     */
    public WEB3FuturesOpenMarginCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
