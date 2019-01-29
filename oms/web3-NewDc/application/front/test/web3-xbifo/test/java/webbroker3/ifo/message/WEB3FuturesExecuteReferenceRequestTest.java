head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesExecuteReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�������Ɖ�N�G�X�g(WEB3FuturesExecuteReferenceRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/18 �����F (���u) �V�K�쐬
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesExecuteReferenceRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesExecuteReferenceRequestTest.class);

    public WEB3FuturesExecuteReferenceRequestTest(String arg0)
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
    
    
    // ���������敪�`�F�b�N
    //�@@this.���������敪��null���A
    //�@@this.���������敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
    //�@@�@@�@@�E�h0�F�w��Ȃ��h
    //�@@�@@�@@�E�h1�F�t�w�l�h
    //�@@�@@�@@�E�h2�FW�w�l�h
    //this.���������敪��null ==3 �ُ�
    public void testValidateCase1()
    {
        String STR_METHOD_NAME = "testValidateCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3FuturesExecuteReferenceRequest l_request = new WEB3FuturesExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.FUTPRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = "3";
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00212, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //this.���������敪��null ==0 ���팋��
    public void testValidateCase2()
    {
        String STR_METHOD_NAME = "testValidateCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3FuturesExecuteReferenceRequest l_request = new WEB3FuturesExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.FUTPRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = "0";
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        
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
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //this.���������敪��null ==1 ���팋��
    public void testValidateCase3()
    {
        String STR_METHOD_NAME = "testValidateCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3FuturesExecuteReferenceRequest l_request = new WEB3FuturesExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.FUTPRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = "1";
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        
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
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //this.���������敪��null ==2 ���팋��
    public void testValidateCase4()
    {
        String STR_METHOD_NAME = "testValidateCase4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3FuturesExecuteReferenceRequest l_request = new WEB3FuturesExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.FUTPRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = "2";
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        
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
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //this.���������敪==null ���팋��
    public void testValidateCase5()
    {
        String STR_METHOD_NAME = "testValidateCase5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3FuturesExecuteReferenceRequest l_request = new WEB3FuturesExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.FUTPRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = null;
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        
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
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
