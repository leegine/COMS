head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoBaseInfoReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l����{���Ɖ�T�[�r�X�����N���X(WEB3AccInfoBaseInfoReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfoReferenceRequest;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfoReferenceResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoAccountBaseInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoBaseInfoReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l����{���Ɖ�T�[�r�XImpl)<BR>
 * ���q�l����{���Ɖ�T�[�r�X�����N���X<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AccInfoBaseInfoReferenceServiceImpl extends WEB3AccInfoClientRequestService 
    implements WEB3AccInfoBaseInfoReferenceService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoBaseInfoReferenceServiceImpl.class);
        
    /**
     * @@roseuid 418F3A060271
     */
    public WEB3AccInfoBaseInfoReferenceServiceImpl() 
    {
     
    }
    
    /**
     * �ڋq��{���Ɖ�����s���B<BR>
     * <BR>
     * �|get�ڋq��{���()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4163B5F501E7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoAccountBaseInfoReferenceResponse l_response;
        if(l_request instanceof WEB3AccInfoAccountBaseInfoReferenceRequest)
        {
            l_response = this.getAccountBaseInfo((WEB3AccInfoAccountBaseInfoReferenceRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (get�ڋq��{���)<BR>
     * ��{���Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i��{���Ɖ�jget�ڋq��{���v�Q�ƁB <BR>
     * @@param l_request - ���q�l����{���Ɖ�N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfoReferenceResponse
     * @@roseuid 4163B5F501F6
     */
    protected WEB3AccInfoAccountBaseInfoReferenceResponse 
        getAccountBaseInfo(WEB3AccInfoAccountBaseInfoReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccountBaseInfo(WEB3AccInfoAccountBaseInfoReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //2) get�ڋq( )
        WEB3GentradeMainAccount l_gentradeMainAccount = (WEB3GentradeMainAccount)getMainAccount();
        
        //3) create�ڋq��{���(�ڋq)
        WEB3AccInfoAccountBaseInfoCreatedService l_service = 
            (WEB3AccInfoAccountBaseInfoCreatedService)Services.getService(WEB3AccInfoAccountBaseInfoCreatedService.class);
        WEB3AccInfoAccountBaseInfo l_accInfoAccountBaseInfo = 
            l_service.createAccountBaseInfo(l_gentradeMainAccount);
        
        //4) ���q�l����{���Ɖ�X�|���X(WEB3GenRequest)
        WEB3AccInfoAccountBaseInfoReferenceResponse l_response = 
            (WEB3AccInfoAccountBaseInfoReferenceResponse)l_request.createResponse();
            
        //5) �v���p�e�B�Z�b�g
        l_response.accountBaseInfo = l_accInfoAccountBaseInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
