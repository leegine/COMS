head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCarryOverSkipRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        :  ถJzXLbvมฟสmNGXg(WEB3EquityCarryOverSkipRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ็พญ (u) VK์ฌ
                   2004/12/06 ชบaพ(SRA) cฤฮ m.RRT
                   2004/12/21 ชบaพ(SRA) JavaDocCณ
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * iฎถJzXLbvมฟสmNGXgjB<br>
 * <br>
 * ฎถJzXLbvมฟสmNGXgNX
 * @@version 1.0
 */
public class WEB3EquityCarryOverSkipRequest extends WEB3BackRequest
{

    /**
     * <p>iserialVersionUIDjB</p>
     */
    public static final long serialVersionUID = 200405231030L;

    /**
     * <p>iPTYPEjB</p>
     */
    public static final String PTYPE = "equity_carryover_skip";

    /**
     * <p>iฎถJzXLbvมฟสmNGXgjB</p>
     * <p>RXgN^B</p>
     */
    public WEB3EquityCarryOverSkipRequest()
    {

    }

    /**
     * <p>icreateX|XjB</p>
     * <p>ฎถJzXLbvมฟสmX|X๐ถฌตฤิทB</p>
     * @@return ฎถJzXLbvมฟสmX|X
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3EquityCarryOverSkipResponse(this);
    }
}
@
