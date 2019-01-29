head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替請求受付リクエストクラス(WEB3AccTransChangeRequestAcceptRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 于美麗 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (振替請求受付リクエスト)<BR>
 * 振替請求受付リクエストクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestAcceptRequest extends WEB3BackRequest 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "acc_trans_change_request_accept";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;
        
    /**
     * @@roseuid 4158E8E30224
     */
    public WEB3AccTransChangeRequestAcceptRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装） <BR>
     * <BR>
     * 振替請求受付レスポンスオブジェクトを生成して返す。 <BR>
     * @@return WEB3BackResponse
     * @@roseuid 4158E8E30238
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AccTransChangeRequestAcceptResponse(this);
    }
}
@
