head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.53.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminForcedSettleApproveConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (WEB3AdminForcedSettleApproveConfirmRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/30�@@�Ј���(���u)
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;

public class WEB3AdminForcedSettleApproveConfirmRequestTest extends TestBaseForMock
{

    public WEB3AdminForcedSettleApproveConfirmRequestTest(String name) 
    {
        super(name);
    }
    
    /**
     * ID�`�F�b�N 
     * ID == null
     */
    public void test_validate_0001()
    {
        WEB3AdminForcedSettleApproveConfirmRequest l_request = new WEB3AdminForcedSettleApproveConfirmRequest();
        l_request.id = null;
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600,e.getErrorInfo());
        }
    }

    /**
     * ���F�敪�`�F�b�N 
     * ���F�敪 == null
     */
    public void test_validate_0002()
    {
        WEB3AdminForcedSettleApproveConfirmRequest l_request = new WEB3AdminForcedSettleApproveConfirmRequest();
        l_request.id = new String[]{"12"};
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
     * ���F�敪�ɉ��L�̍��ڈȊO
     */
    public void test_validate_0003()
    {
        WEB3AdminForcedSettleApproveConfirmRequest l_request = new WEB3AdminForcedSettleApproveConfirmRequest();
        l_request.id = new String[]{"12"};
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
    
    /**
     * �\�[�g�L�[�`�F�b�N 
     * �\�[�g�L�[��������
     */
    public void test_validate_0004()
    {
        WEB3AdminForcedSettleApproveConfirmRequest l_request = new WEB3AdminForcedSettleApproveConfirmRequest();
        l_request.id = new String[]{"12"};
        l_request.approveType = "0";
        l_request.sortKeys = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231,e.getErrorInfo());
        }
    }
    
    /**
     * �\�[�g�L�[�`�F�b�N 
     * �\�[�g�L�[�̗v�f�����ȉ�
     */
    public void test_validate_0005()
    {
        
        WEB3AdminForcedSettleApproveConfirmRequest l_request = new WEB3AdminForcedSettleApproveConfirmRequest();
        l_request.id = new String[]{"12"};
        l_request.approveType = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_keyItem = new WEB3AdminForcedSettleSortKeyUnit();
        l_keyItem.keyItem = null;
        WEB3AdminForcedSettleSortKeyUnit l_keyItem1 = new WEB3AdminForcedSettleSortKeyUnit();
        l_keyItem1.keyItem = "branchCode";
        
        
        l_request.sortKeys = new WEB3AdminForcedSettleSortKeyUnit[]{l_keyItem,l_keyItem1};
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085,e.getErrorInfo());
        }
        
    }
    
    /**
     * �\�[�g�L�[�`�F�b�N 
     * �\�[�g�L�[�̗v�f�����ȉ�
     */
    public void test_validate_0006()
    {
        WEB3AdminForcedSettleApproveConfirmRequest l_request = new WEB3AdminForcedSettleApproveConfirmRequest();
        l_request.id = new String[]{"12"};
        l_request.approveType = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_keyItem = new WEB3AdminForcedSettleSortKeyUnit();
        l_keyItem.keyItem = "branchCode";
        l_keyItem.ascDesc = "A";
        WEB3AdminForcedSettleSortKeyUnit l_keyItem1 = new WEB3AdminForcedSettleSortKeyUnit();
        l_keyItem1.keyItem = null;
        
        
        l_request.sortKeys = new WEB3AdminForcedSettleSortKeyUnit[]{l_keyItem,l_keyItem1};
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085,e.getErrorInfo());
        }
    }

}
@
