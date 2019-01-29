head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.17.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeTradingClendarContextForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeTradingClendarContextForMock extends WEB3GentradeTradingClendarContext
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3GentradeTradingClendarContextForMock.class);
    
    public WEB3GentradeTradingClendarContextForMock()
    {
        super();
    }

    public String getInstitutionCode()
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeTradingClendarContext",
            "getInstitutionCode",
            new Class[] {}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeTradingClendarContextr.setInstitutionCode(String)");
            return  (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingClendarContext",
                "getInstitutionCode",
                new Class[] {}).asObject();
        }
        return super.getInstitutionCode();
    }
}
@
