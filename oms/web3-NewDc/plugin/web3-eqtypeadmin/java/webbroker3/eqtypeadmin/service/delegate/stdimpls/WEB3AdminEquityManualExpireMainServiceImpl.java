head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityManualExpireMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����蓮�������C���T�[�r�XImpl(WEB3AdminEquityManualExpireMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/5/31 ������ (���u) �V�K�쐬
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseMainRequest;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityManualExpireMainService;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�����蓮�������C���T�[�r�XImpl)<BR>
 * �Ǘ��ҁE�����蓮�������C���T�[�r�X�����N���X<BR>
 * �i�񓯊��������s���ׂ̃G���g���[�N���X�j<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */

public class WEB3AdminEquityManualExpireMainServiceImpl implements WEB3AdminEquityManualExpireMainService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3AdminEquityManualExpireMainServiceImpl.class);
     
    /**
     * @@roseuid 447AC0CE03B9
     */
    public WEB3AdminEquityManualExpireMainServiceImpl() 
    {
     
    }
    
    /**
     *�i�񓯊��j�蓮�����������N������B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��Ҋ����蓮�������C���T�[�r�X�jexecute�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 44698678027F
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BackResponse l_response = null;
        WEB3AdminEquityManualLapseMainRequest l_mainRequest = null;
        if (l_request instanceof WEB3AdminEquityManualLapseMainRequest)
        {
            l_mainRequest = (WEB3AdminEquityManualLapseMainRequest)l_request;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT ���N�G�X�g NOT �Ǘ��ҁE�����蓮�������C�����N�G�X�g");
        }
        
        //1.1���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_mainRequest.validate();
        
        //1.2�f�[�����g���K�[�̊Y�����R�[�h��"������"��update����B
        WEB3GentradeDaemonTriggerManager l_manager = new WEB3GentradeDaemonTriggerManager();
        l_manager.startThread(l_mainRequest.threadNo.longValue());
        
        //1.3�i�񓯊��j�Ǘ��ҁE�����蓮�����T�[�r�XImpl�𐶐�����B
        WEB3AsynAdminEquityManualExpireServiceImpl l_expireServiceImpl = 
            new WEB3AsynAdminEquityManualExpireServiceImpl(l_mainRequest);
        
        WEB3AsynExecuteService l_executeService = 
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);

        //1.4�i�񓯊��j�����蓮�����������s���B
        //[����]
        // arg0�F�@@���������i�񓯊��j�Ǘ��ҁE�����蓮�����T�[�r�XImpl
        l_executeService.execute(l_expireServiceImpl);
        
        //1.5���X�|���X�f�[�^�𐶐�����B
        l_response = l_mainRequest.createResponse();
        
        //1.6 return
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
