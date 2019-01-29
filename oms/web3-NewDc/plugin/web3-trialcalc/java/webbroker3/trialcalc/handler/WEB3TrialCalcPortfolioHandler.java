head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�n���h��(WEB3TrialCalcPortfolioHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditInputResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditResponse;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcPortfolioService;

/**
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�n���h���j<BR>
 * <BR>
 * �|�[�g�t�H���I�n���h���N���X�B<BR>
 * <BR>
 * WEB3TrialCalcPortfolioHandler<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioHandler implements MessageHandler
{
    /**
      * ���O�o�̓��[�e�B���e�B�B
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcPortfolioHandler.class);

    /**
     * @@roseuid 41C7FFFD0229
     */
    public WEB3TrialCalcPortfolioHandler()
    {

    }

    /**
     * �iget�|�[�g�t�H���I�\���j<BR>
     * <BR>
     * �|�[�g�t�H���I�\���T�[�r�X�����{����B<BR>
     * <BR>
     * �v�Z�T�[�r�X�|�[�g�t�H���I�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getPortfolioDisplay<BR>
     * Aquire WEB3TrialCalcPortfolioService and call method 'execute()'<BR>
     * @@param l_request ���N�G�X�g�f�[�^ - �|�[�g�t�H���I�i�ꗗ��ʕ\���j�̃��N�G�X�g�f�[�^�B
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayResponse
     * @@roseuid 4193391D011B
     */
    public WEB3TrialCalcPortfolioDisplayResponse
        getPortfolioDisplay(WEB3TrialCalcPortfolioDisplayRequest l_request)
    {
        final String STR_METHOD_NAME = "getPortfolioDisplay(WEB3TrialCalcPortfolioDisplayRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TrialCalcPortfolioDisplayResponse l_response = null;
        WEB3TrialCalcPortfolioService l_service = null;

        try
        {
            l_service =
                (WEB3TrialCalcPortfolioService) Services.getService(
                    WEB3TrialCalcPortfolioService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3TrialCalcPortfolioDisplayResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while displaying the Portfolio",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3TrialCalcPortfolioDisplayResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Display of portfolio failed", l_exp);
            l_response = (WEB3TrialCalcPortfolioDisplayResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �iupdate�|�[�g�t�H���I�ҏW�j<BR>
     * <BR>
     * �|�[�g�t�H���I�ҏW�T�[�r�X�����{����B<BR>
     * <BR>
     * �|�[�g�t�H���I�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * updatePortfolioEdit<BR>
     * Aquire WEB3TrialCalcPortfolioService and call method 'execute()'<BR>
     * @@param l_request ���N�G�X�g�f�[�^ - �|�[�g�t�H���I�ҏW���ʍX�V�̃��N�G�X�g�f�[�^�B
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditResponse
     * @@roseuid 4193391D011D
     */
    public WEB3TrialCalcPortfolioEditResponse
        updatePortfolioEdit(WEB3TrialCalcPortfolioEditRequest l_request)
    {
        final String STR_METHOD_NAME = "updatePortfolioEdit(WEB3TrialCalcPortfolioEditRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TrialCalcPortfolioEditResponse l_response = null;
        WEB3TrialCalcPortfolioService l_service = null;

        try
        {
            l_service =
                (WEB3TrialCalcPortfolioService) Services.getService(
                    WEB3TrialCalcPortfolioService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3TrialCalcPortfolioEditResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while updateing the Portfolio",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3TrialCalcPortfolioEditResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Updateing the portfolio failed", l_exp);
            l_response = (WEB3TrialCalcPortfolioEditResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �iget�|�[�g�t�H���I�ҏW���́j<BR>
     * <BR>
     * �|�[�g�t�H���I�ҏW���͕\���T�[�r�X�����{����B<BR>
     * <BR>
     * �v�Z�T�[�r�X�|�[�g�t�H���I�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getPortfolioEditInput<BR>
     * Aquire WEB3TrialCalcPortfolioService and call method 'execute()'<BR>
     * @@param l_request
     * �|�[�g�t�H���I�i�|�[�g�t�H���I�ҏW���͉�ʕ\���j�̃��N�G�X�g�f�[�^�B
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditInputResponse
     * @@roseuid 4198ABE60259
     */
    public WEB3TrialCalcPortfolioEditInputResponse
        getPortfolioEditInput(WEB3TrialCalcPortfolioEditInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getPortfolioEditInput(WEB3TrialCalcPortfolioEditInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TrialCalcPortfolioEditInputResponse l_response = null;
        WEB3TrialCalcPortfolioService l_service = null;

        try
        {
            l_service =
                (WEB3TrialCalcPortfolioService) Services.getService(
                    WEB3TrialCalcPortfolioService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3TrialCalcPortfolioEditInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while displaying the PortfolioEditInput",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3TrialCalcPortfolioEditInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "PortfolioEditInput failed", l_exp);
            l_response = (WEB3TrialCalcPortfolioEditInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
