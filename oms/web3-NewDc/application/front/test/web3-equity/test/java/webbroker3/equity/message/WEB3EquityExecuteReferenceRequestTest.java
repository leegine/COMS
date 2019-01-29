head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.50.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityExecuteReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3EquityExecuteReferenceRequestTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/18 �����i���u�j�V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityExecuteReferenceRequestTest extends TestBaseForMock
{
    private WEB3EquityExecuteReferenceRequest l_request = null;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityExecuteReferenceRequestTest.class);
 
    public WEB3EquityExecuteReferenceRequestTest(String name)
    {
        super(name);
    }
    
    public void setUp() throws Exception
    {
        super.setUp();
        this.l_request = new WEB3EquityExecuteReferenceRequest();
        WEB3EquitySortKey[] sortKeys = new WEB3EquitySortKey[1];
        sortKeys[0] = new WEB3EquitySortKey();
        sortKeys[0].keyItem = "productCode";
        sortKeys[0].ascDesc = "A";
        this.l_request.sortKeys = sortKeys;
        // referenceType
        this.l_request.referenceType = "0";
        //execType
        this.l_request.execType = "0";
        // pageIndex
        this.l_request.pageIndex = "1";
        this.l_request.pageSize ="1";
        this.l_request.marketCode = "1";
    }

    public void tearDown() throws Exception
    {
        this.l_request = null;
        super.tearDown();
    }
    
    /**
     * ���������敪�̒l�����݂��Ȃ��R�[�h�l�ł�
     * 
     * �X���[:BUSINESS_ERROR_00212�̃��b�Z�[�W
     *
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // ���������敪
            this.l_request.orderCondType = "3";
            this.l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00212,l_ex.getErrorInfo());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ���팋��
     *
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // ���������敪
            this.l_request.orderCondType = "2";
            this.l_request.validate();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
}
@
