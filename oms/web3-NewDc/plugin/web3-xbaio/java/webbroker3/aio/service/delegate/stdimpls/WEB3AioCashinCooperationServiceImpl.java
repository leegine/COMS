head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�g�T�[�r�XImpl(WEB3AioCashinCooperationServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/12 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.message.WEB3AioCashinCooperationNotifyRequest;
import webbroker3.aio.service.delegate.WEB3AioCashinCooperationService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����A�g�T�[�r�XImpl) <BR>
 * �����A�g�T�[�r�X�����N���X <BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioCashinCooperationServiceImpl implements WEB3AioCashinCooperationService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinCooperationServiceImpl.class);  
    
    /**
     * @@roseuid 41E77F4A01D4
     */
    public WEB3AioCashinCooperationServiceImpl()
    {
    }

    /**
     * (execute)
     * �����A�g�T�[�r�X�������s���B <BR>
     * <BR>
     * �����ŗ^����ꂽ���N�G�X�g����ɋƖ��������s���A<BR>
     * �������ʂ����X�|���X�ɐݒ肵�Ă�Ԃ��B <BR>
     * <BR>
     * �P�j�X���b�h���J�n����B <BR>
     * �@@�E�f�[�����g���K�[�}�l�[�W���[�N���X�̃C���X�^���X�𐶐�����B <BR>
     * �@@�E�f�[�����g���K�[�}�l�[�W���[�N���X.�X���b�h�J�n()���R�[�� <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@�X���b�h�m���F�����A�g�ʒm���N�G�X�g.�X���b�hNo <BR>
     * <BR>
     * �Q�j�񓯊����s <BR>
     * �@@�E�����A�g�ʒm�񓯊��T�[�r�XImpl�̃C���X�^���X�𐶐�����B <BR>
     * �@@�@@�m�����n <BR>
     * �@@�@@�@@�����A�g�ʒm���N�G�X�g�F����.�����A�g�ʒm���N�G�X�g <BR>
     * �@@�E�񓯊����s�����T�[�r�X���擾����B <BR>
     * �@@�E�񓯊����s�����T�[�r�X.execute()���R�[�� <BR>
     * �@@�@@�m�����n <BR>
     * �@@�@@�@@Runnable�F�������������A�g�ʒm�񓯊��T�[�r�XImpl�I�u�W�F�N�g <BR>
     * <BR>
     * �S�j���X�|���X�I�u�W�F�N�g�𐶐����ԋp����B <BR>
     * 
     * @@param l_request ���N�G�X�g 
     * @@return �������ʂ��ݒ肳�ꂽ���X�|���X 
     * @@roseuid 41C7B2080071
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AioCashinCooperationNotifyRequest l_aioCashinInputRequest =
            (WEB3AioCashinCooperationNotifyRequest)l_request;
        
        //�P�j�X���b�h���J�n����B 
        //�@@�E�f�[�����g���K�[�}�l�[�W���[�N���X�̃C���X�^���X�𐶐�����B 
        //�@@�E�f�[�����g���K�[�}�l�[�W���[�N���X.�X���b�h�J�n()���R�[�� 
        //�@@�@@[����] 
        //�@@�@@�X���b�h�m���F�����A�g�ʒm���N�G�X�g.�X���b�hNo         
        
        WEB3GentradeDaemonTriggerManager l_daemonTriggerManager = 
            new WEB3GentradeDaemonTriggerManager();
        
        l_daemonTriggerManager.startThread(
            l_aioCashinInputRequest.threadNo.longValue());
        
        //�Q�j�񓯊����s 
        //�E�����A�g�ʒm�񓯊��T�[�r�XImpl�̃C���X�^���X�𐶐�����B 
        //�@@�m�����n 
        //�@@�@@�����A�g�ʒm���N�G�X�g�F����.�����A�g�ʒm���N�G�X�g 
        //�E�񓯊����s�����T�[�r�X���擾����B 
        //�E�񓯊����s�����T�[�r�X.execute()���R�[�� 
        //�@@�m�����n 
        //�@@�@@Runnable�F�������������A�g�ʒm�񓯊��T�[�r�XImpl�I�u�W�F�N�g 
        
        WEB3AioCashinCooperationNotifyAsyncServiceImpl l_asyncServiceImpl =
            new WEB3AioCashinCooperationNotifyAsyncServiceImpl(l_aioCashinInputRequest);            

        WEB3AsynExecuteService l_asynExecuteService =
            (WEB3AsynExecuteService)Services.getService(
                WEB3AsynExecuteService.class);
        
        l_asynExecuteService.execute(l_asyncServiceImpl);
     
        //�S�j���X�|���X�I�u�W�F�N�g�𐶐����ԋp����B
        WEB3BackResponse l_response = l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
}@
