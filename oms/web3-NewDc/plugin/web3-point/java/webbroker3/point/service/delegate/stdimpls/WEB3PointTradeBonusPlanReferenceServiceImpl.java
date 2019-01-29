head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointTradeBonusPlanReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���[�h�{�[�i�X�v�����Ɖ�T�[�r�XImpl(WEB3PointTradeBonusPlanReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.point.WEB3PointClientRequestService;
import webbroker3.point.data.OrixTradeBonusPlanDao;
import webbroker3.point.data.OrixTradeBonusPlanRow;
import webbroker3.point.message.WEB3PointTradeBonusPlanReferenceRequest;
import webbroker3.point.message.WEB3PointTradeBonusPlanReferenceResponse;
import webbroker3.point.service.delegate.WEB3PointTradeBonusPlanReferenceService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���[�h�{�[�i�X�v�����Ɖ�T�[�r�XImpl)<BR>
 * �g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X�����N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointTradeBonusPlanReferenceServiceImpl extends WEB3PointClientRequestService implements WEB3PointTradeBonusPlanReferenceService 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PointTradeBonusPlanReferenceServiceImpl.class);        
    
    /**
     * @@roseuid 421D7B390000
     */
    public WEB3PointTradeBonusPlanReferenceServiceImpl() 
    {
     
    }
    
    /**
     * �g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�\���f�[�^�擾�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 42071E930368
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (!(l_request instanceof WEB3PointTradeBonusPlanReferenceRequest))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.1get�،���ЃR�[�h( )
        String l_strInstitutionCode = this.getInstitutionCode();        
        
        //1.2get���X�R�[�h()
        String l_strBranchCode = this.getBranchCode();
        
         
        //1.3get�ڋq�R�[�h( )
        String l_strAccountCode = this.getAccountCode();        
        
        //1.4validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        OrixTradeBonusPlanRow l_row = null;
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode);//WEB3SystemLayerException

            l_row = OrixTradeBonusPlanDao.findRowByInstitutionCodeBranchCodeAccountCode(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_mainAccount.getAccountCode());//DataFindException, DataQueryException, DataNetworkException            
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        //1.5createResponse( )
        WEB3PointTradeBonusPlanReferenceRequest l_tradeBonusPlanReferenceRequest = 
            (WEB3PointTradeBonusPlanReferenceRequest)l_request;        
        WEB3PointTradeBonusPlanReferenceResponse  l_response = 
            (WEB3PointTradeBonusPlanReferenceResponse)l_tradeBonusPlanReferenceRequest.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.6(*2)�v���p�e�B�Z�b�g
        // (�|�C���g�K�p�N��)
        if (l_row != null)
        {
            l_response.applyMonthCurr = l_row.getApplyMonthCurr();

            // (�g���[�h�|�C���g)
            if (!l_row.getTrdPointCurrIsNull())
            {
                l_response.trdPointCurr = 
                    WEB3StringTypeUtility.formatNumber(l_row.getTrdPointCurr());
            }

            // �L�����y�[���|�C���g
            if (!l_row.getCmpPointCurrIsNull())
            {
                l_response.cmpPointCurr = 
                    WEB3StringTypeUtility.formatNumber(l_row.getCmpPointCurr());
            }
        
            // (���v�|�C���g)
            if (!l_row.getTrdPointCurrIsNull() || !l_row.getCmpPointCurrIsNull())
            {
                l_response.totalPointCurr = 
                    WEB3StringTypeUtility.formatNumber(l_row.getTrdPointCurr() + l_row.getCmpPointCurr());
            }
            
            // (������)
            l_response.cutRateCurr = l_row.getCutRateCurr();

            // (�|�C���g�K�p�N���i�����j)
            l_response.applyMonthNext = l_row.getApplyMonthNext();

            // (�g���[�h�|�C���g�i�����j)
            if (!l_row.getTrdPointNextIsNull())
            {
                l_response.trdPointNext = 
                    WEB3StringTypeUtility.formatNumber(l_row.getTrdPointNext());
            }

            // (�L�����y�[���|�C���g�i�����j)
            if (!l_row.getCmpPointNextIsNull())
            {
                l_response.cmpPointNext = 
                    WEB3StringTypeUtility.formatNumber(l_row.getCmpPointNext());
            }

            // (���v�|�C���g�i�����j)
            if (!l_row.getTrdPointNextIsNull() || !l_row.getCmpPointNextIsNull())
            {
                l_response.totalPointNext = 
                    WEB3StringTypeUtility.formatNumber(l_row.getTrdPointNext() + l_row.getCmpPointNext());
            }

            // (�������i�����j)
            l_response.cutRateNext = l_row.getCutRateNext();
        }
        else
        {
            l_response.applyMonthCurr = null;

            // (�g���[�h�|�C���g)
            l_response.trdPointCurr = null;

            // �L�����y�[���|�C���g
            l_response.cmpPointCurr = null;
        
            // (���v�|�C���g)
            l_response.totalPointCurr = null;

            // (������)
            l_response.cutRateCurr = null;

            // (�|�C���g�K�p�N���i�����j)
            l_response.applyMonthNext = null;

            // (�g���[�h�|�C���g�i�����j)
            l_response.trdPointNext = null;

            // (�L�����y�[���|�C���g�i�����j)
            l_response.cmpPointNext = null;

            // (���v�|�C���g�i�����j)
            l_response.totalPointNext = null;

            // (�������i�����j)
            l_response.cutRateNext = null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;           
    }
}
@
