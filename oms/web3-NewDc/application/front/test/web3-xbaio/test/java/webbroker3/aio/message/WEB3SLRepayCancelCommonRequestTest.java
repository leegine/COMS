head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.21.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SLRepayCancelCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 証券担保ローン返済取消共通リクエストのテストクラス(WEB3SLRepayCancelCommonRequestTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/09/23 何文敏 (中訊) 新規作成
*/
package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author 何文敏
 *
 */
public class WEB3SLRepayCancelCommonRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log=
        WEB3LogUtility.getInstance(WEB3SLRepayCancelCommonRequestTest.class);
    WEB3SLRepayCancelCommonRequest l_request = new WEB3SLRepayCancelCommonRequest();

    public WEB3SLRepayCancelCommonRequestTest(String arg0)
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

    public void testValidate_case0001()
    {
        final String STR_METHOD_NAME = "testValidate_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorMessage(), "注文IDが未指定です。");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0002()
    {
        final String STR_METHOD_NAME = "testValidate_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
           l_request.orderId = "12345";
            l_request.validate();
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
