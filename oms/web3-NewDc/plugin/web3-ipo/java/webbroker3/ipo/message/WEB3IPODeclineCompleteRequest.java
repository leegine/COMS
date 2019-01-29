head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODeclineCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IIPO辞退完了リクエストクラス(WEB3IPODeclineCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 彭巍 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (IPO辞退完了リクエストクラス)<BR>
 * 
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3IPODeclineCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_declineComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408100942L;
    
    /**
     * (ＩＤ)<BR>
     * IPO申告ＩＤ
     */
    public String id;
    
    /**
     * 暗証番号
     */
    public String password;
    
    /**
     * 確認時発注日
     */
    public Date checkDate;
    
    /**
     * @@roseuid 4112E4E10033
     */
    public WEB3IPODeclineCompleteRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E4E1007A
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPODeclineCompleteResponse(this);
    }
}
@
