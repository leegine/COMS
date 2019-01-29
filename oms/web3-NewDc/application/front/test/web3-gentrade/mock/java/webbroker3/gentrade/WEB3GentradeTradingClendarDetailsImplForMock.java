head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.17.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeTradingClendarDetailsImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : //TODO(WEB3GentradeTradingClendarDetailsImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/28 ™GˆÌ (’†u) V‹Kì¬
*/
package webbroker3.gentrade;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXƒNƒ‰ƒX//TODO
 *
 * @@author ™GˆÌ(’†u)
 * @@version 1.0
 */
public class WEB3GentradeTradingClendarDetailsImplForMock extends WEB3GentradeTradingClendarDetailsImpl
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeTradingClendarDetailsImplForMock.class);

    /**
     * (getsê•Â‹ÇŠÔ(Mock))<BR>
     * sê•Â‹ÇŠÔ‚ğæ“¾‚·‚éB<BR>
     * @@return java.lang.String
     */
    public String getTradeCloseTime()
    {
        final String STR_METHOD_NAME = "getTradeCloseTime()-->ForMock";
        log.entering(STR_METHOD_NAME);

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl",
            "getTradeCloseTime",
            new Class[] {}))
        {
            //1)MockFor --r asVoid
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl",
                "getTradeCloseTime",
                new Class[] {}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getTradeCloseTime();
    }
}
@
