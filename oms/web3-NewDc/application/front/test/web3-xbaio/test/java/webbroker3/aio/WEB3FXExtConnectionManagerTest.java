head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.35.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXExtConnectionManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�O���ڑ��}�l�[�W��(WEB3FXExtConnectionManager.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 �����F (���u) �V�K�쐬 �d�l�ύX�E���f��1172 ���f��1179
Revision History : 2009/09/16 �И��� (���u) �d�l�ύX�E���f��1206
*/
package webbroker3.aio;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXExtConnectionManagerTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXExtConnectionManagerTest.class);

    public WEB3FXExtConnectionManagerTest(String arg0)
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

    /*
     * Test method for 'webbroker3.aio.WEB3FXExtConnectionManager.getExtConnectionInstance(String)'
     */
    public void testGetExtConnectionInstanceCase1()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase1()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            
            WEB3FXSimplexConnectionSystem l_connectionSystem = new WEB3FXSimplexAccOpenConnectionSystem();
            
            String l_strResult = l_connectionSystem.sendURLMessage("http://10.253.111.77/web3bdview/");
            
            System.out.print("l_strResult=" + l_strResult);
            
            Object l_system = WEB3FXExtConnectionManager.getExtConnectionInstance("01", "0");
            if (l_system instanceof WEB3GFTConnectionSystem)
            {
                log.debug("GFT�ڑ��V�X�e���̃C���X�^���X��ԋp����");
            }
            else
            {
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    }
    
    public void testGetExtConnectionInstanceCase2()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase2()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            Object l_system = WEB3FXExtConnectionManager.getExtConnectionInstance("05", "01");
            if (l_system instanceof WEB3FXSimplexAccOpenConnectionSystem)
            {
                log.debug("Simplex�����J�ݐڑ��V�X�e���̃C���X�^���X��ԋp����");
            }
            else
            {
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    }
    
    public void testGetExtConnectionInstanceCase3()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase3()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            Object l_system = WEB3FXExtConnectionManager.getExtConnectionInstance("05", "07");
            if (l_system instanceof WEB3FXSimplexTransferAbleAmtConnectionSystem)
            {
                log.debug("Simplex�U�։\�z�ڑ��V�X�e���̃C���X�^���X��ԋp����");
            }
            else
            {
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    } 
    
    public void testGetExtConnectionInstanceCase4()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase4()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXExtConnectionManager.getExtConnectionInstance("05", "02");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(l_ex.getErrorInfo(),  WEB3ErrorCatalog.BUSINESS_ERROR_03184);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    } 
    
    public void testGetExtConnectionInstanceCase5()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase5()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXExtConnectionManager.getExtConnectionInstance("05", "04");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(l_ex.getErrorInfo(),  WEB3ErrorCatalog.BUSINESS_ERROR_03184);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    } 
    
    public void testGetExtConnectionInstanceCase6()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase6()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXExtConnectionManager.getExtConnectionInstance("06", "04");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(l_ex.getErrorInfo(),  WEB3ErrorCatalog.BUSINESS_ERROR_03161);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    } 
    
    public void testGetExtConnectionInstanceCase7()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase7()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXExtConnectionManager.getExtConnectionInstance("05", "03");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(l_ex.getErrorInfo(),  WEB3ErrorCatalog.BUSINESS_ERROR_03161);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    }

    public void testGetExtConnectionInstanceCase8()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase8()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Object l_system = WEB3FXExtConnectionManager.getExtConnectionInstance("01", "0");
            if (l_system instanceof WEB3FXSimplexAccOpenConnectionSystem)
            {
                log.debug("GFT�ڑ��V�X�e���̃C���X�^���X��ԋp����");
            }
            else
            {
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    }

    public void testGetExtConnectionInstanceCase9()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase9()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Object l_system = WEB3FXExtConnectionManager.getExtConnectionInstance("05", "01");
            if (l_system instanceof WEB3FXSimplexAccOpenConnectionSystem)
            {
                log.debug("Simplex�����J�ݐڑ��V�X�e���̃C���X�^���X��ԋp����");
            }
            else
            {
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    }

    public void testGetExtConnectionInstanceCase10()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase10()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Object l_system = WEB3FXExtConnectionManager.getExtConnectionInstance("05", "07");
            if (l_system instanceof WEB3FXSimplexTransferAbleAmtConnectionSystem)
            {
                log.debug("Simplex�U�։\�z�ڑ��V�X�e���̃C���X�^���X��ԋp����");
            }
            else
            {
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    }

    public void testGetExtConnectionInstanceCase11()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase11()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXExtConnectionManager.getExtConnectionInstance("05", "02");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(l_ex.getErrorInfo(),  WEB3ErrorCatalog.BUSINESS_ERROR_03184);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    }

    public void testGetExtConnectionInstanceCase12()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase12()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXExtConnectionManager.getExtConnectionInstance("05", "04");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(l_ex.getErrorInfo(),  WEB3ErrorCatalog.BUSINESS_ERROR_03184);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    }

    public void testGetExtConnectionInstanceCase13()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase13()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Object l_system = WEB3FXExtConnectionManager.getExtConnectionInstance("06", "04");
            if (l_system instanceof WEB3FXSimplexTransferAbleAmtConnectionSystem)
            {
                log.debug("IIJ�U�֐ڑ��V�X�e���̃C���X�^���X��ԋp");
            }
            else
            {
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    }

    public void testGetExtConnectionInstanceCase14()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase14()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXExtConnectionManager.getExtConnectionInstance("06", "01");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(l_ex.getErrorInfo(),  WEB3ErrorCatalog.BUSINESS_ERROR_03184);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    }

    public void testGetExtConnectionInstanceCase15()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase15()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXExtConnectionManager.getExtConnectionInstance("06", "02");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(l_ex.getErrorInfo(),  WEB3ErrorCatalog.BUSINESS_ERROR_03184);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    }

    public void testGetExtConnectionInstanceCase16()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase16()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXExtConnectionManager.getExtConnectionInstance("06", "07");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(l_ex.getErrorInfo(),  WEB3ErrorCatalog.BUSINESS_ERROR_03184);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    }

    public void testGetExtConnectionInstanceCase17()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase17()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXExtConnectionManager.getExtConnectionInstance("07", "04");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(l_ex.getErrorInfo(),  WEB3ErrorCatalog.BUSINESS_ERROR_03161);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    }

    public void testGetExtConnectionInstanceCase18()
    {
        final String STR_METHOD_NAME = "testGetExtConnectionInstanceCase18()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXExtConnectionManager.getExtConnectionInstance("05", "03");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(l_ex.getErrorInfo(),  WEB3ErrorCatalog.BUSINESS_ERROR_03161);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }

    }


}
@
