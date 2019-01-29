head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenFinInstitutionSearchHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݋��Z�@@�֌����n���h��(WEB3AccOpenFinInstitutionSearchHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 ���w�� (���u) �V�K�쐬
*/
package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AccOpenFinancialBranchSearchRequest;
import webbroker3.accountopen.message.WEB3AccOpenFinancialBranchSearchResponse;
import webbroker3.accountopen.message.WEB3AccOpenFinancialSearchRequest;
import webbroker3.accountopen.message.WEB3AccOpenFinancialSearchResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenFinInstitutionSearchService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�݋��Z�@@�֌����n���h��)<BR>
 * �����J�݋��Z�@@�֌����n���h��<BR>
 * @@author ���w��
 * @@version 1.0 
 */
public class WEB3AccOpenFinInstitutionSearchHandler implements MessageHandler 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenFinInstitutionSearchHandler.class);
    
    /**
     * @@roseuid 41B45E74034B
     */
    public WEB3AccOpenFinInstitutionSearchHandler() 
    {
     
    }
    
    /**
     * (��s�ꗗ�\��)<BR>
     * ��s�ꗗ�\���������s���B<BR>
     * <BR>
     * �����J�݋��Z�@@�֌����T�[�r�X�N���X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����J�݋��Z�@@�֌������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AccOpenFinancialSearchResponse
     * @@roseuid 41A174E401D7
     */
    public WEB3AccOpenFinancialSearchResponse finInstitutionListDisplay(WEB3AccOpenFinancialSearchRequest l_request) 
    {
        final String STR_METHOD_NAME = " finInstitutionListDisplay(WEB3AccOpenFinancialSearchRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccOpenFinancialSearchResponse l_response = null;
        WEB3AccOpenFinInstitutionSearchService l_service = null;
        
        try
        {
            l_service = (WEB3AccOpenFinInstitutionSearchService)
                Services.getService(WEB3AccOpenFinInstitutionSearchService.class);//Exception
        }        
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AccOpenFinancialSearchResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                " �����J�݊������[�����M�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AccOpenFinancialSearchResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenFinancialSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �Ǘ��Ҍ����J�݊������[�����M�ꗗ���N�G�X�g�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (�x�X�ꗗ�\��)<BR>
     * �x�X�ꗗ�\���������s���B<BR>
     * <BR>
     * �����J�݋��Z�@@�֌����T�[�r�X�N���X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����J�ݎx�X�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AccOpenFinancialBranchSearchResponse
     * @@roseuid 41A174E401E7
     */
    public WEB3AccOpenFinancialBranchSearchResponse finBranchListDisplay(WEB3AccOpenFinancialBranchSearchRequest l_request) 
    {
        final String STR_METHOD_NAME = " finBranchListDisplay(WEB3AccOpenFinancialBranchSearchRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccOpenFinancialBranchSearchResponse l_response = null;
        WEB3AccOpenFinInstitutionSearchService l_service = null;
        
        try
        {
            l_service = (WEB3AccOpenFinInstitutionSearchService)
                Services.getService(WEB3AccOpenFinInstitutionSearchService.class);//Exception
        }       
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AccOpenFinancialBranchSearchResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �����J�݊������[�����M�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AccOpenFinancialBranchSearchResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenFinancialBranchSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " �Ǘ��Ҍ����J�݊������[�����M�ꗗ���N�G�X�g�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
