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
filename	WEB3AdminToTradeStopInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~���̓T�[�r�XImpl(WEB3AdminToTradeStopInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInputResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~���̓T�[�r�XImpl)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~���̓T�[�r�X�����N���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToTradeStopInputServiceImpl implements WEB3AdminToTradeStopInputService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopInputServiceImpl.class);
    
    /**
     * @@roseuid 4430DD35034B
     */
    public WEB3AdminToTradeStopInputServiceImpl() 
    {
     
    }
    
    /**
     * �戵��~���͉�ʕ\���������s���B<BR>
     * <BR>
     * this.get���͉��()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44192D0102EE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
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
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminToTradeStopInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminToTradeStopInputRequest) l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * �戵��~���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�戵��~���̓T�[�r�X�jget���͉�ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 44192D7F0186
     */
    protected WEB3AdminToTradeStopInputResponse getInputScreen(WEB3AdminToTradeStopInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToTradeStopInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, false);
        
        //1.3 createResponse( )
        WEB3AdminToTradeStopInputResponse l_response = 
            (WEB3AdminToTradeStopInputResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
