head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoManualExpireMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�敨OP�蓮�������C���T�[�r�XImpl(WEB3AdminIfoManualExpireMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30�@@�Ӑ�(���u) �V�K�쐬
*/
package webbroker3.ifoadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainRequest;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoManualExpireMainService;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�敨OP�蓮�������C���T�[�r�XImpl)<BR>
 * �Ǘ��ҁE�敨OP�蓮�������C���T�[�r�X�����N���X<BR>
 * �i�񓯊��������s���ׂ̃G���g���[�N���X�j<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */

public class WEB3AdminIfoManualExpireMainServiceImpl implements WEB3AdminIfoManualExpireMainService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3AdminIfoManualExpireMainServiceImpl.class);
     
    /**
     * @@roseuid 447AC0CE03B9
     */
    public WEB3AdminIfoManualExpireMainServiceImpl() 
    {
     
    }
    
    /**
     *�i�񓯊��j�蓮�����������N������B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��Ґ敨OP�蓮�������C���T�[�r�X�jexecute�v�Q�ƁB<BR>
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
        WEB3AdminIfoManualLapseMainRequest l_mainRequest = null;
        if (l_request instanceof WEB3AdminIfoManualLapseMainRequest)
        {
            l_mainRequest = (WEB3AdminIfoManualLapseMainRequest)l_request;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT ���N�G�X�g NOT �Ǘ��ҁE�����蓮�������C�����N�G�X�g");
        }
        
        // ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_mainRequest.validate();
        
        // �f�[�����g���K�[�̊Y�����R�[�h��"������"��update����B
        WEB3GentradeDaemonTriggerManager l_manager = new WEB3GentradeDaemonTriggerManager();
        l_manager.startThread(l_mainRequest.threadNo.longValue());
        
        // �i�񓯊��j�Ǘ��ҁE�����蓮�����T�[�r�XImpl�𐶐�����B
        WEB3AsynAdminIfoManualExpireServiceImpl l_expireServiceImpl = 
            new WEB3AsynAdminIfoManualExpireServiceImpl(l_mainRequest);
        
        WEB3AsynExecuteService l_executeService = 
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);

        // �i�񓯊��j�����蓮�����������s���B
        //[����]
        // arg0�F�@@���������i�񓯊��j�Ǘ��ҁE�����蓮�����T�[�r�XImpl
        l_executeService.execute(l_expireServiceImpl);
        
        // ���X�|���X�f�[�^�𐶐�����B
        l_response = l_mainRequest.createResponse();
        
        //  return
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
