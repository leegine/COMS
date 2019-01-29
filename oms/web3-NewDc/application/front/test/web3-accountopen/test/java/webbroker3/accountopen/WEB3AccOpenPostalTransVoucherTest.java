head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.09.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenPostalTransVoucherTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : U‘Ö\i‹âsj“`•[(WEB3AccOpenBankTransVoucherTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/006/01 ‰½•¶•q (’†u)
*/
package webbroker3.accountopen;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (U‘Ö\i‹âsj“`•[)<BR>
 *
 * @@author ‰½•¶•q
 */
public class WEB3AccOpenPostalTransVoucherTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenBankTransVoucherTest.class);
    WEB3AccOpenPostalTransVoucher l_voucher = new WEB3AccOpenPostalTransVoucher();
    
    public WEB3AccOpenPostalTransVoucherTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetUserData()
    {
        final String STR_METHOD_NAME = "testGetUserData()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strUserData = l_voucher.getUserData();
            assertEquals("1", l_strUserData);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
