head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.38.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminOrderExecinquiryAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3AdminOrderExecuteDataManagerTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/08 ��{����(���u) �V�K�쐬
*/
package webbroker3.adminorderexecinquiry;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceRequestTest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommonRequestTest;
import webbroker3.adminorderexecinquiry.service.delegate.stdimpls.WEB3AdminEquityExecuteReferenceServiceImplTest;
import webbroker3.adminorderexecinquiry.service.delegate.stdimpls.WEB3AdminFeqExecutionReferenceServiceImplTest;
import webbroker3.adminorderexecinquiry.service.delegate.stdimpls.WEB3AdminIfoExecuteReferenceServiceImplTest;
import webbroker3.adminorderexecinquiry.service.delegate.stdimpls.WEB3AdminMutualRuitoExecuteReferenceServiceImplTest;

/**
 * (�Ǘ��Ғ������Ɖ�f�[�^�}�l�[�W��)<BR>
 * �Ǘ��Ғ������Ɖ��DB I/O�A�f�[�^�ϊ��Ȃǂ��Ǘ�����N���X�B<BR>
 *
 * @@author ��{����(���u)
 * @@version 1.0
 */
public class WEB3AdminOrderExecinquiryAllTests
{

	private static boolean WEB3AdminEquityExecuteReferenceServiceImplTest = true;
	private static boolean WEB3AdminOROrderExecutionRefCommonRequestTest = true;
    private static boolean WEB3AdminMutualRuitoExecuteReferenceServiceImplTest = true;
    private static boolean WEB3AdminFeqExecutionReferenceServiceImplTest = true;
    private static boolean WEB3AdminIfoExecuteReferenceServiceImplTest = true;

	public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.adminorderexecinquiry");
        //$JUnit-BEGIN$
		if (WEB3AdminEquityExecuteReferenceServiceImplTest)
		{
			suite.addTestSuite(WEB3AdminEquityExecuteReferenceServiceImplTest.class);
		}
		if (WEB3AdminOROrderExecutionRefCommonRequestTest)
		{
			suite.addTestSuite(WEB3AdminOROrderExecutionRefCommonRequestTest.class);
		}

        if (WEB3AdminMutualRuitoExecuteReferenceServiceImplTest)
        {
            suite.addTestSuite(WEB3AdminMutualRuitoExecuteReferenceServiceImplTest.class);
        }
        suite.addTestSuite(WEB3AdminOrderExecuteDataManagerTest.class);        

        if (WEB3AdminFeqExecutionReferenceServiceImplTest)
        {
            suite.addTestSuite(WEB3AdminFeqExecutionReferenceServiceImplTest.class);
        }
        
        if (WEB3AdminIfoExecuteReferenceServiceImplTest)
        {
            suite.addTestSuite(WEB3AdminIfoExecuteReferenceServiceImplTest.class);
        }        
        suite.addTestSuite(WEB3AdminORBondExecRefReferenceRequestTest.class);
        //$JUnit-END$
        return suite;
    }

}
@
