head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文取消完了レスポンス(WEB3MstkCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 カク寛新 (中訊) 新規作成
                   2004/12/10 桑原 (SRA) 修正
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （株式ミニ投資注文取消完了レスポンス）。<BR>
 * <BR>
 * 株式ミニ投資注文取消完了レスポンスクラス
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3MstkCancelCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "mstk_cancelComplete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410101054L;    
    
    /**
     * (更新時間)<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (識別番号)<BR>
     */
    public String orderActionId;
    
    /**
     * @@roseuid 4167B04E0002
     */
	public WEB3MstkCancelCompleteResponse()
	{
           
	}
    public WEB3MstkCancelCompleteResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
           
    }
}@
