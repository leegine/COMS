head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.53.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointTradeBonusPlanReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トレードボーナスプラン照会リクエスト(WEB3PointTradeBonusPlanReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (トレードボーナスプラン照会リクエスト)<BR>
 *  トレードボーナスプラン照会リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointTradeBonusPlanReferenceRequest extends WEB3GenRequest
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_tradeBonusPlanReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502241605L;
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D12552000F
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3PointTradeBonusPlanReferenceResponse(this);
    }
}
@
