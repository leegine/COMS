head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCloseMarginContractListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍό��ʈꗗ���N�G�X�g(WEB3OptionsCloseMarginContractListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/04 �И��� (���u) �V�K�쐬
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsCloseMarginContractListRequestTest extends TestBaseForMock
{
    public WEB3OptionsCloseMarginContractListRequestTest(String name)
    {
        super(name);
    }

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3OptionsCommonRequestTest.class);
    
    /**
     * �h�c��null�̒l�ł���Η�O���X���[����
     * date:
     * this.�����h�c = null
     * �e�X�g�m�F���e:
     * BUSINESS_ERROR_00080
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsCloseMarginContractListRequest l_request = new WEB3OptionsCloseMarginContractListRequest();
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �h�c!=null�̒l  pass
     * date:
     * this.�����h�c = "1"
     * �e�X�g�m�F���e:
     * pass
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsCloseMarginContractListRequest l_request = new WEB3OptionsCloseMarginContractListRequest();
        
        l_request.id = "1";
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
