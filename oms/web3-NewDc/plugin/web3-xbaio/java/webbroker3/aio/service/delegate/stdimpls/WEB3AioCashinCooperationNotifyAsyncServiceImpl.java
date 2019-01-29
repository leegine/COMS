head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationNotifyAsyncServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�g�ʒm�񓯊��T�[�r�XImpl�N���X(WEB3AioCashinCooperationNotifyAsyncServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/11 ��O�� (���u) �V�K�쐬
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.aio.message.WEB3AioCashinCooperationNotifyRequest;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����A�g�ʒm�񓯊��T�[�r�XImpl)<BR>
 * �����A�g�ʒm�񓯊��T�[�r�XImpl�N���X<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioCashinCooperationNotifyAsyncServiceImpl implements Runnable 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinCooperationNotifyAsyncServiceImpl.class);     
    
    /**
     * �����A�g�ʒm���N�G�X�g�B<BR>
     */
    private WEB3AioCashinCooperationNotifyRequest aioCashinCooperationNotifyRequest;
    
    /**
     * (�R���X�g���N�^) <BR>
     * <BR>
     * ����.�����A�g�ʒm���N�G�X�g���Athis.�����A�g�ʒm���N�G�X�g�ɃZ�b�g����B <BR>
     * <BR>
     * @@param l_request - (�����A�g�ʒm���N�G�X�g)<BR>
     * @@roseuid 40F240CB0198
     */
    public WEB3AioCashinCooperationNotifyAsyncServiceImpl(
        WEB3AioCashinCooperationNotifyRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "WEB3AioCashinCooperationNotifyAsyncServiceImpl(" +
            "WEB3AioCashinCooperationNotifyRequest l_request)";
        
        log.entering(STR_METHOD_NAME);
        
        this.aioCashinCooperationNotifyRequest = l_request;
       
        log.exiting(STR_METHOD_NAME);
    }    
   
    /**
     * ()
     * �V�[�P���X�} <BR>
     * �u(�����A�g�ʒm�񓯊��T�[�r�XImpl)run()�v �Q�� <BR>
     * <BR>
     * @@roseuid 40F240CB0198
     */
    public void run()
    {
        final String STR_METHOD_NAME = "run()";
        log.entering(STR_METHOD_NAME); 
        
        try
        {
            //1.1 getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        
            WEB3AioCashinCooperationUnitTransactionCallback l_transactionCallback =
                new WEB3AioCashinCooperationUnitTransactionCallback();
            
            //1.1.1 doTransaction(arg0 : int, arg1 : TransactionCallback)
            //�����F
            //QueryProcessor.TX_CREATE_NEW
            //�����A�g�g�����U�N�V�����R�[���o�b�N
            
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_transactionCallback);            
        }        
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.2  (*)�X���b�h���J������    
        //�X���b�h�J��
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(
                this.aioCashinCooperationNotifyRequest.threadNo.longValue());
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug("Error In �X���b�h���J������", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);   
    }
}
@
