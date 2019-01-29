head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToManualExpireMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�蓮�������C���T�[�r�XImpl(WEB3AdminToManualExpireMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainRequest;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToManualExpireMainService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�蓮�������C���T�[�r�XImpl)<BR>
 * �iWEB3AdminToManualExpireMainServiceImpl�j<BR>
 * �g���K�[�����Ǘ��ҁE�蓮�������C���T�[�r�X�����N���X<BR>
 * �i�񓯊��������s���ׂ̃G���g���[�N���X�j<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToManualExpireMainServiceImpl implements WEB3AdminToManualExpireMainService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToManualExpireMainServiceImpl.class);
    
    /**
     * @@roseuid 4419312B03A9
     */
    public WEB3AdminToManualExpireMainServiceImpl() 
    {
     
    }
    
    /**
     * �i�񓯊��j�蓮�����������N������B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�蓮�������C���T�[�r�X�jexecute�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 440E806C03A2
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        
        if (!(l_request instanceof WEB3AdminToManualLapseMainRequest))
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        WEB3AdminToManualLapseMainRequest l_mainRequest = (WEB3AdminToManualLapseMainRequest) l_request;
        log.debug("***** ���C�����N�G�X�g�f�[�^���e *****");
        log.debug("�،���ЃR�[�h�F[" + l_mainRequest.institutionCode + "]");
        log.debug("�X���b�hNo�F[" + l_mainRequest.threadNo + "]");
        log.debug("From����ID�F[" + l_mainRequest.rangeFrom + "]");
        log.debug("To����ID�F[" + l_mainRequest.rangeTo + "]");
        log.debug("�����Ώے��������F[" + l_mainRequest.lapseTargetOrderCondition + "]");
        
        //1.1 validate( )
        l_mainRequest.validate();
        
        //1.2 startThread(l_lngThreadNo : long)
        //�f�[�����g���K�[�̊Y�����R�[�h��"������"��update����B
        //����d�N���̖h�~�B
        WEB3GentradeDaemonTriggerManager l_triggerManager = new WEB3GentradeDaemonTriggerManager();
        l_triggerManager.startThread(l_mainRequest.threadNo.longValue());
        
        //1.3 �i�񓯊��j�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�XImpl(�g���K�[�����Ǘ��ҁE�蓮�������C�����N�G�X�g)
        WEB3AsynAdminToManualExpireServiceImpl l_serviceImpl = 
            new WEB3AsynAdminToManualExpireServiceImpl(l_mainRequest);
        
        //1.4 execute(arg0 : Runnable)
        //�񓯊������Ȃ̂ŁA���\�b�h�̏I����҂��Ȃ��B
        WEB3AsynExecuteService l_executeService = 
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_executeService.execute(l_serviceImpl);
        
        //1.5 createResponse( )
        WEB3BackResponse l_mainResponse = l_mainRequest.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_mainResponse;
    }
}
@
