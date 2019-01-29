head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.52.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminForcedSettleApproveStatusRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (WEB3AdminForcedSettleApproveStatusRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/30�@@�Ј���(���u)
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;

public class WEB3AdminForcedSettleApproveStatusRequestTest extends TestBaseForMock 
{
    public WEB3AdminForcedSettleApproveStatusRequestTest(String name)
    {
        super(name);
    }

    /**
     * ���F�敪�`�F�b�N
     * ���F�敪 == null
     */
    public void test_validate_0001()
    {
        WEB3AdminForcedSettleApproveStatusRequest l_request = new  WEB3AdminForcedSettleApproveStatusRequest();

        l_request.approveType = null;
        
        try 
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02761,e.getErrorInfo());
        }
        
    }
    
    /**
     * ���F�敪�`�F�b�N
     * OK
     */
    public void test_validate_0002()
    {
        WEB3AdminForcedSettleApproveStatusRequest l_request = new  WEB3AdminForcedSettleApproveStatusRequest();

        l_request.approveType = "1";
        
        try 
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
        
    }
    
    /**
     * ���F�敪�`�F�b�N
     * OK
     */
    public void test_validate_0003()
    {
        WEB3AdminForcedSettleApproveStatusRequest l_request = new  WEB3AdminForcedSettleApproveStatusRequest();

        l_request.approveType = "0";
        
        try 
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
        
    }
    
    /**
     * ���F�敪�`�F�b�N
     * ���F�敪�ɉ��L�̍��ڈȊO
     */
    public void test_validate_0004()
    {
        WEB3AdminForcedSettleApproveStatusRequest l_request = new  WEB3AdminForcedSettleApproveStatusRequest();

        l_request.approveType = "3";
        
        try 
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02760,e.getErrorInfo());
        }
        
    }
}
@
