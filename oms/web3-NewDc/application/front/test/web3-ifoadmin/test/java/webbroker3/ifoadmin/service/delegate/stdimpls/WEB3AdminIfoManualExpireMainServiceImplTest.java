head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.02.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoManualExpireMainServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifoadmin.service.delegate.stdimpls;


import webbroker3.accountinfo.message.WEB3AccInfoLockRegistReleaseAcceptRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainResponse;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoManualExpireMainServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�敨OP�蓮�������C���T�[�r�XImpl)<BR>
 * �Ǘ��ҁE�敨OP�蓮�������C���T�[�r�X�����N���X<BR>
 * �i�񓯊��������s���ׂ̃G���g���[�N���X�j<BR>
 * <BR>
 * @@author ��{����(���u)
 * @@version 1.0
 */
public class WEB3AdminIfoManualExpireMainServiceImplTest extends TestBaseForMock
{

    public WEB3AdminIfoManualExpireMainServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_adminIfoManualExpireMainServiceImpl = new WEB3AdminIfoManualExpireMainServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminIfoManualExpireMainServiceImplTest.class);
    
    /**
     * �Ǘ��ҁE�敨OP�蓮�������C���T�[�r�XImpl<BR>
     */
    private WEB3AdminIfoManualExpireMainServiceImpl l_adminIfoManualExpireMainServiceImpl = null;

    /**
     * �i�񓯊��j�蓮�����������N������B<BR>
     * Method-Name: execute<BR>
     * expect: SYSTEM_ERROR_80017<BR>
     * @@param �Ȃ�
     * @@return �Ȃ�
     * @@throws �Ȃ�
     */
   public void testExecute_0001()
    {
        final String STR_METHOD_NAME = " testExecute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3BackRequest l_request = new WEB3AccInfoLockRegistReleaseAcceptRequest();
            l_adminIfoManualExpireMainServiceImpl.execute(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error(TEST_END + STR_METHOD_NAME,e);
            assertEquals(WEB3SystemLayerException.class, e.getClass());
            WEB3SystemLayerException xe = (WEB3SystemLayerException)e;
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, xe.getErrorInfo());
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * �i�񓯊��j�蓮�����������N������B<BR>
     * Method-Name: execute<BR>
     * expect: response����<BR>
     * @@param �Ȃ�
     * @@return �Ȃ�
     * @@throws �Ȃ�
     */
    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = " testExecute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3BackRequest l_request = new WEB3AdminIfoManualLapseMainRequest();
            l_adminIfoManualExpireMainServiceImpl.execute(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error(TEST_END + STR_METHOD_NAME,e);
            assertEquals(WEB3BusinessLayerException.class, e.getClass());

        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * �i�񓯊��j�蓮�����������N������B<BR>
     * Method-Name: execute<BR>
     * expect: validate�ُ�<BR>
     * @@param �Ȃ�
     * @@return �Ȃ�
     * @@throws �Ȃ�
     */
    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = " testExecute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3BackRequest l_request = new WEB3AdminIfoManualLapseMainRequest()
            {
                public void validate() throws WEB3BaseException
                {
                    this.threadNo = Long.valueOf("10");
                }
                /**
                 *�icreateResponse�̎����j<BR>
                 * <BR>
                 * �Ǘ��ҁE�����蓮�������C�����X�|���I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
                 * @@return WEB3GenResponse
                 * @@roseuid 4158EB6301A2
                 */
                public WEB3BackResponse createResponse() 
                {
                    return new MyWEB3AdminIfoManualLapseMainResponse();
                }
            };

            WEB3BackResponse l_response = l_adminIfoManualExpireMainServiceImpl.execute(l_request);
            if (l_response instanceof MyWEB3AdminIfoManualLapseMainResponse)
            {
                MyWEB3AdminIfoManualLapseMainResponse l_response1 = (MyWEB3AdminIfoManualLapseMainResponse)l_response;
                assertTrue(l_response1.testMethodFlag);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            else
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();                
            }
        }
        catch (Exception e)
        {
            log.error(TEST_END + STR_METHOD_NAME,e);
            fail();

        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}

/**
 * (�Ǘ��ҁE�敨OP�蓮�������C�����X�|���X)<BR>
 * �Ǘ��ҁE�敨OP�蓮�������C�����X�|���X�N���X<BR>
 * <BR>
 */
class MyWEB3AdminIfoManualLapseMainResponse extends WEB3AdminIfoManualLapseMainResponse
{
    /**
     * @@roseuid 447AB8F40119
     */
    public MyWEB3AdminIfoManualLapseMainResponse() 
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    protected MyWEB3AdminIfoManualLapseMainResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    } 

    public boolean testMethodFlag = true;
}@
