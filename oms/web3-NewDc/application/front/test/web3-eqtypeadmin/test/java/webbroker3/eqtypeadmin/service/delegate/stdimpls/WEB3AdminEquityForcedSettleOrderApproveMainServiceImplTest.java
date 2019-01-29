head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.07.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleOrderApproveMainServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔F���C���T�[�r�XImpl(WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/29 ��{���� (���u) �V�K�쐬
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteServiceImpl;
import webbroker3.system.tune.threadpool.WEB3ThreadPoolPlugin;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔F���C���T�[�r�XImpl)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔F���C���T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ��{����
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderApproveMainServiceImplTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveMainServiceImplTest.class);
    WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl interceptor = null;

    public WEB3AdminEquityForcedSettleOrderApproveMainServiceImplTest(String arg0)
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
     * (�i�񓯊��j�������ω��������F�^�񏳔F�������N������B)<BR>
     * validate error<BR>
     * testExecute_0001<BR>
     */
    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = " testExecute_0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveMainRequest l_request = 
                new WEB3AdminEquityForcedSettleOrderApproveMainRequest();
            /**
             * (�،���ЃR�[�h)<BR>
             * �،���ЃR�[�h<BR>
             */
            l_request.institutionCode = null;

            /**
             * (�X���b�hNo)<BR>
             * �X���b�hNo<BR>
             */
            l_request.threadNo = new Long(1234);

            /**
             * (From����ID)<BR>
             * From����ID<BR>
             */
            l_request.rangeFrom = new Long(1024);

            /**
             * (To����ID)<BR>
             * To����ID<BR>
             */
            l_request.rangeTo = new Long(1196);

            /**
             * (���F�敪)<BR>
             * ���F�敪<BR>
             * <BR>
             * 0�F�@@���F<BR>
             * 1�F�@@�񏳔F<BR>
             */
            l_request.approveType = "0";

            /**
             * (����ID�ꗗ)<BR>
             * ����ID�ꗗ<BR>
             */
            l_request.orderIdList = new String[]{"1001", "1002", "1003", "1004", "1005"};
            
            interceptor = new WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl();
            interceptor.execute(l_request);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00827, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * (�i�񓯊��j�������ω��������F�^�񏳔F�������N������B)<BR>
     * �i�񓯊��j�������ω��������F�^�񏳔F execute error<BR>
     * testExecute_0002<BR>
     */
    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = " testExecute_0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        final String errorStr = "WEB3AsynExecuteService is failure";
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveMainRequest l_request = 
                new WEB3AdminEquityForcedSettleOrderApproveMainRequest();
            /**
             * (�،���ЃR�[�h)<BR>
             * �،���ЃR�[�h<BR>
             */
            l_request.institutionCode = "0029";

            /**
             * (�X���b�hNo)<BR>
             * �X���b�hNo<BR>
             */
            l_request.threadNo = new Long(1234);

            /**
             * (From����ID)<BR>
             * From����ID<BR>
             */
            l_request.rangeFrom = new Long(1024);

            /**
             * (To����ID)<BR>
             * To����ID<BR>
             */
            l_request.rangeTo = new Long(1196);

            /**
             * (���F�敪)<BR>
             * ���F�敪<BR>
             * <BR>
             * 0�F�@@���F<BR>
             * 1�F�@@�񏳔F<BR>
             */
            l_request.approveType = "0";

            /**
             * (����ID�ꗗ)<BR>
             * ����ID�ꗗ<BR>
             */
            l_request.orderIdList = new String[]{"1001", "1002", "1003", "1004", "1005"};
            
            
            interceptor = new WEB3AdminEquityForcedSettleOrderApproveMainServiceImplForTest();
            interceptor.execute(l_request);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (RuntimeException l_ex)
        {
            assertEquals(errorStr, l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.overrideService(WEB3AsynExecuteService.class , new WEB3AsynExecuteServiceImpl());
        }
    }
    
    /**
     * (�i�񓯊��j�������ω��������F�^�񏳔F�������N������B)<BR>
     * OK<BR>
     * testExecute_0003<BR>
     */
    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = " testExecute_0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveMainRequest l_request = 
                new WEB3AdminEquityForcedSettleOrderApproveMainRequest()
                {
                    /**
                     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
                     * <BR>
                     * @@return WEB3BackResponse
                     */
                    public WEB3BackResponse createResponse()
                    {
                        return new MyWEB3AdminEquityForcedSettleOrderApproveMainResponse(this);
                    }                    
                };
            /**
             * (�،���ЃR�[�h)<BR>
             * �،���ЃR�[�h<BR>
             */
            l_request.institutionCode = "0029";

            /**
             * (�X���b�hNo)<BR>
             * �X���b�hNo<BR>
             */
            l_request.threadNo = new Long(1234);

            /**
             * (From����ID)<BR>
             * From����ID<BR>
             */
            l_request.rangeFrom = new Long(1024);

            /**
             * (To����ID)<BR>
             * To����ID<BR>
             */
            l_request.rangeTo = new Long(1196);

            /**
             * (���F�敪)<BR>
             * ���F�敪<BR>
             * <BR>
             * 0�F�@@���F<BR>
             * 1�F�@@�񏳔F<BR>
             */
            l_request.approveType = "0";

            /**
             * (����ID�ꗗ)<BR>
             * ����ID�ꗗ<BR>
             */
            l_request.orderIdList = new String[]{"1001", "1002", "1003", "1004", "1005"};
            
            l_request.administratorId = new Long(254122);
            
//            Services.overrideService(WEB3AsynExecuteService.class , new WEB3AsynExecuteServiceImpl(){});
            
            interceptor = new WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl();
            MyWEB3AdminEquityForcedSettleOrderApproveMainResponse response = 
                (MyWEB3AdminEquityForcedSettleOrderApproveMainResponse)interceptor.execute(l_request);
            assertTrue(response.flag);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
}

/**
 * MyWEB3AdminEquityForcedSettleOrderApproveMainResponse
 * extends �Ǘ��ҁE�������ω��������F�^�񏳔F���C�����X�|���X
 * @@author liu-lifeng
 *
 */
class MyWEB3AdminEquityForcedSettleOrderApproveMainResponse extends WEB3AdminEquityForcedSettleOrderApproveMainResponse
{
    public final boolean flag = true;
    
    /**
     * @@roseuid 462CA42601AA
     */
    public MyWEB3AdminEquityForcedSettleOrderApproveMainResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public MyWEB3AdminEquityForcedSettleOrderApproveMainResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
    
    
}

class WEB3AdminEquityForcedSettleOrderApproveMainServiceImplForTest extends WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl
{
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        throw new RuntimeException("WEB3AsynExecuteService is failure");
    }
}@
