head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.53.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityPTSInputExecInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����iPTS�j�o�����̓��N�G�X�gTest(WEB3AdminEquityPTSInputExecInputRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 ��іQ(���u) �V�K�쐬
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityPTSInputExecInputRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSInputExecInputRequestTest.class);

    public WEB3AdminEquityPTSInputExecInputRequestTest(String arg0)
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
     * ����ID == null�̏ꍇ
     */
    public void testValidateCase0001()
    {
        final String STR_METHOD_NAME = "testValidateCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityPTSInputExecInputRequest l_request = new WEB3AdminEquityPTSInputExecInputRequest();
            l_request.orderId = null;
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00600);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * ����ID != null�̏ꍇ
     */
    public void testValidateCase0002()
    {
        final String STR_METHOD_NAME = "testValidateCase0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityPTSInputExecInputRequest l_request = new WEB3AdminEquityPTSInputExecInputRequest();
            l_request.orderId = "123";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
