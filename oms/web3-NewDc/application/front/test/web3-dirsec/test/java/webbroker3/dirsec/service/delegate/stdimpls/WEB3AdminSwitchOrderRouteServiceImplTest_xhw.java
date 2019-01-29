head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSwitchOrderRouteServiceImplTest_xhw.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3AdminSwitchOrderRouteServiceImplTest_xhw.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/22 徐宏偉 (中訊) 新規作成
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.lang.reflect.Method;

import webbroker3.dirsec.message.WEB3AdminOrderRouteSwitchingInfo;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminSwitchOrderRouteServiceImplTest_xhw extends TestBaseForMock
{

    WEB3AdminSwitchOrderRouteServiceImpl l_impl = new WEB3AdminSwitchOrderRouteServiceImpl();
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminSwitchOrderRouteServiceImplTest_xhw.class);

    public WEB3AdminSwitchOrderRouteServiceImplTest_xhw(String arg0)
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

    /*
     * 
     */
    public void testUpdateEqtypeOrder()
    {
        String STR_METHOD_NAME =
            "testUpdateEqtypeOrder()";
        log.entering(STR_METHOD_NAME);
        Method l_method;
        String l_strInstitutioncode = null;
        String l_strFrotExCode = null;
        WEB3AdminOrderRouteSwitchingInfo l_infoUnit = new WEB3AdminOrderRouteSwitchingInfo();
        l_infoUnit.changedValidFlag = "0";
        WEB3GentradeOrderSwitching l_orderSwitching = null;

        try
        {
            l_method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "updateEqtypeOrder", new Class[]{
                    String.class, String.class,
                    WEB3AdminOrderRouteSwitchingInfo.class, WEB3GentradeOrderSwitching.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutioncode, l_strFrotExCode, l_infoUnit, l_orderSwitching});
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        
    }
}
@
