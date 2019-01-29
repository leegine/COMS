head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.53.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminForcedSettleApproveRunRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AdminForcedSettleApproveRunRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/30　@孟亜南(中訊)
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;

public class WEB3AdminForcedSettleApproveRunRequestTest extends TestBaseForMock
{

    public WEB3AdminForcedSettleApproveRunRequestTest(String name)
    {
        super(name);
    }

    /**
     * IDチェック 
     * ID == null
     */
    public void test_validate_0001()
    {
        WEB3AdminForcedSettleApproveRunRequest l_request = new  WEB3AdminForcedSettleApproveRunRequest();
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
     * 承認区分チェック 
     * 承認区分 == null
     */
    public void test_validate_0002()
    {
        WEB3AdminForcedSettleApproveRunRequest l_request = new  WEB3AdminForcedSettleApproveRunRequest();
        l_request.id = new String[]{"0"};
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
     * 承認区分チェック 
     * 承認区分に下記
     */
    public void test_validate_0003()
    {
        WEB3AdminForcedSettleApproveRunRequest l_request = new  WEB3AdminForcedSettleApproveRunRequest();
        l_request.id = new String[]{"0"};
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
     * OK
     */
    public void test_validate_0004()
    {
        WEB3AdminForcedSettleApproveRunRequest l_request = new  WEB3AdminForcedSettleApproveRunRequest();
        l_request.id = new String[]{"0"};
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
     * OK
     */
    public void test_validate_0005()
    {
        WEB3AdminForcedSettleApproveRunRequest l_request = new  WEB3AdminForcedSettleApproveRunRequest();
        l_request.id = new String[]{"0"};
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
}
@
