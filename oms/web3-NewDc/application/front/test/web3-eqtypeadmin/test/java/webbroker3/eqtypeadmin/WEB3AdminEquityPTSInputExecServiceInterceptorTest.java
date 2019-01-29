head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.27.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityPTSInputExecServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�C���^�Z�v�^Test(WEB3AdminEquityPTSInputExecServiceInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/01 ��іQ(���u) �V�K�쐬
*/

package webbroker3.eqtypeadmin;

import test.util.TestSpecialClassUtility;

import webbroker3.eqtypeadmin.WEB3AdminEquityPTSInputExecServiceInterceptor;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityPTSInputExecServiceInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSInputExecServiceInterceptorTest.class);

    public WEB3AdminEquityPTSInputExecServiceInterceptorTest(String arg0)
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
    
    /**
     * OnReturnAndoOnThrowable
     */
    public void testOnReturnAndoOnThrowableCase001()
    {
        final String STR_METHOD_NAME = "testOnReturnAndoOnThrowableCase001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestSpecialClassUtility.testServiceInterceptor(new WEB3AdminEquityPTSInputExecServiceInterceptor());
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
