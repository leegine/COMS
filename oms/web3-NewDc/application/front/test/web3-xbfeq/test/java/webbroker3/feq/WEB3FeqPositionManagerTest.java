head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.54.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqPositionManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : WEB3FeqPositionManagerTest.java
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/09/12 趙天月(中訊) 新規作成 モデル545
*/
package webbroker3.feq;

import java.util.Date;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqPositionManagerTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderManagerTest.class);
    
    WEB3FeqPositionManager l_manager = null;
    public WEB3FeqPositionManagerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_manager = new WEB3FeqPositionManager();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testUpdateTransaction_case001()
    {
        final String METHOD_NAME = "testUpdateTransaction_case001";
        log.entering(METHOD_NAME);
        
        Class clazz = l_manager.getClass();
        try
        {
            clazz.getMethod("updateTransaction", new Class[]{long[].class, Date.class, boolean.class});
        }
        catch (NoSuchMethodException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
            log.exiting(METHOD_NAME);
        }
    }
}
@
