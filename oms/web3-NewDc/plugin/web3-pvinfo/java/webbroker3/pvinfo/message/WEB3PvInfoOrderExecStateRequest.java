head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.05.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoOrderExecStateRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文約定状況リクエスト(WEB3PvInfoOrderExecStateRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/20 李弘毅(中訊) 作成
*/
package webbroker3.pvinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (注文約定状況リクエスト)<BR>
 * 注文約定状況リクエストクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoOrderExecStateRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PvInfo_orderExecState";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4173439B00AB
     */
    public WEB3GenResponse createResponse() 
    {
     return new WEB3PvInfoOrderExecStateResponse(this);
    }
}
@
