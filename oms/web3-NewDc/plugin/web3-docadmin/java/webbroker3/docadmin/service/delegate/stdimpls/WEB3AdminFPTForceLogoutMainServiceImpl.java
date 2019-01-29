head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��� ���ʖ����� �������O�A�E�g���C���T�[�r�XImpl(WEB3AdminFPTForceLogoutMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 ��(FLJ) �V�K�쐬
*/
package webbroker3.docadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutMainRequest;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutMainService;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;


/**
 * �Ǘ��� ���ʖ����� �������O�A�E�g���C���T�[�r�XImpl
 * 
 * @@author ��
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutMainServiceImpl implements WEB3AdminFPTForceLogoutMainService 
{
	
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutMainServiceImpl.class);
    
    /**
     * @@roseuid 47DF46760353
     */
    public WEB3AdminFPTForceLogoutMainServiceImpl() 
    {
     
    }
    
    /**
     * �i�񓯊��j���ʖ����� �������O�A�E�g�������N������B 
     * 
     * �V�[�P���X�} 
     * �u�i�Ǘ��� ���ʖ����� �������O�A�E�g�jexecute�v�Q�ƁB
     * @@param l_request - ���N�G�X�g
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 47D64BA90008
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BackResponse l_response = null;
        WEB3AdminFPTForceLogoutMainRequest l_mainRequest = null;
        if (l_request instanceof WEB3AdminFPTForceLogoutMainRequest)
        {
            l_mainRequest = (WEB3AdminFPTForceLogoutMainRequest)l_request;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT ���N�G�X�g NOT �Ǘ��� ���ʖ����� �������O�A�E�g���C�����N�G�X�g");
        }
        
        //1.1���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_mainRequest.validate();
        
        //1.2�f�[�����g���K�[�̊Y�����R�[�h��"������"��update����B 
        //[����] 
        //l_lngThreadNo�F�@@���N�G�X�g�f�[�^.�X���b�hNo
        WEB3GentradeDaemonTriggerManager l_manager = new WEB3GentradeDaemonTriggerManager();
        l_manager.startThread(l_mainRequest.threadNo.longValue());
        
        //1.3(�񓯊�)�Ǘ��� ���ʖ����� ����r����A�E�gImpl�𐶐�����B 
        //��Services.getService()�ł͂Ȃ��B 
		//[����] 
		//���N�G�X�g�f�[�^�F�@@���N�G�X�g�f�[�^
        WEB3AsynAdminFPTForceLogoutServiceImpl l_expireServiceImpl = 
            new WEB3AsynAdminFPTForceLogoutServiceImpl(l_mainRequest);
        
        WEB3AsynExecuteService l_executeService = 
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);

        //1.4 �i�񓯊��j���ʖ����� �������O�A�E�g�������s���B 
        //[����] 
        //arg0�F�@@���������i�񓯊��j�Ǘ��� ���ʖ����� �������O�A�E�g�T�[�r�XImpl
        l_executeService.execute(l_expireServiceImpl);
        
        //1.5���X�|���X�f�[�^�𐶐�����B
        l_response = l_mainRequest.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
