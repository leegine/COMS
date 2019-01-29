head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連絡完了レスポンス(WEB3InformCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 艾興 (中訊) 新規作成
*/
package webbroker3.inform.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (連絡完了レスポンス)<BR>
 * 連絡完了レスポンスクラス
 */
public class WEB3InformCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "inform_complete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200501251410L;   
    
    /**
     * (識別コード)
     */
    public String requestNumber; 
    
    /**
     * 
     *(更新日時)
     */
    public Date updateTimeStamp;
    
    /**
     * @@roseuid 41EE625C0167
     */
    public WEB3InformCompleteResponse() 
    {
     
    }
    public WEB3InformCompleteResponse(WEB3InformCompleteRequest l_request)
    {
        super(l_request);
    }
}
@
