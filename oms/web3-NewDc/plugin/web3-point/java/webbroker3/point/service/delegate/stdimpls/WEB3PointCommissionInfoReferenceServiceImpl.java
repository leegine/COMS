head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointCommissionInfoReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����萔���������Ɖ�T�[�r�XImpl(WEB3PointCommissionInfoReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.service.delegate.stdimpls;

import java.util.Date;

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
import webbroker3.point.message.WEB3PointCommissionInfoReferenceRequest;
import webbroker3.point.message.WEB3PointCommissionInfoReferenceResponse;
import webbroker3.point.service.delegate.WEB3PointCommissionInfoReferenceService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����萔���������Ɖ�T�[�r�XImpl)<BR>
 * �����萔���������Ɖ�T�[�r�X�����N���X<BR>
 * 
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3PointCommissionInfoReferenceServiceImpl extends WEB3PointClientRequestService implements WEB3PointCommissionInfoReferenceService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PointCommissionInfoReferenceServiceImpl.class);

    /**
     * @@roseuid 421D7B39006D
     */
    public WEB3PointCommissionInfoReferenceServiceImpl() 
    {
     
    }
    
    /**
     * �����萔���������Ɖ�T�[�r�X���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�\���f�[�^�擾�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@roseuid 420716B900F7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME); 
        
        if (!(l_request instanceof WEB3PointCommissionInfoReferenceRequest))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.1 get�،���ЃR�[�h()
        String l_strInstitutionCode = this.getInstitutionCode();
        
        //1.2 get���X�R�[�h()
        String l_strBranchCode = this.getBranchCode();
        
        //1.3 get�ڋq�R�[�h()
        String l_strAccountCode = this.getAccountCode();
        
        //1.4 validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        if (l_finApp == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        if (l_accountManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
            
        WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
            l_strInstitutionCode, 
            l_strBranchCode, 
            l_strAccountCode);//WEB3SystemLayerException
            
        if (l_mainAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //(*1)�g���[�h�{�[�i�X�v����DAO�ɂāA
        //     �ȉ��̏����̃��R�[�h���擾����B
        //[�擾����]
        //�،���ЃR�[�h = get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h = get���X�R�[�h()�̖߂�l
        //�����R�[�h = get�ڋq�R�[�h()�̖߂�l�̏�6��
        OrixTradeBonusPlanRow l_row = null;
        
        try
        {
            l_row = OrixTradeBonusPlanDao.findRowByInstitutionCodeBranchCodeAccountCode(
                l_strInstitutionCode, l_strBranchCode, l_mainAccount.getAccountCode());//DataFindException,DataQueryException,DataNetworkException
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
            
        //1.5 createResponse()
        WEB3PointCommissionInfoReferenceRequest l_referenceRequest = 
            (WEB3PointCommissionInfoReferenceRequest)l_request;
            
        //(*2)�v���p�e�B�Z�b�g
        //(*2)�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //���X�|���X.�萔���������ԕ\���t���O = �i�ȉ��̂Ƃ���j
        //     �g���[�h�{�[�i�X�v����.�萔����������FROM != null �̏ꍇ�Atrue
        //     �g���[�h�{�[�i�X�v����.�萔����������FROM == null �̏ꍇ�Afalse
        //���X�|���X.�萔���������ԁi���j = �g���[�h�{�[�i�X�v����.�萔����������FROM
        //���X�|���X.�萔���������ԁi���j = �g���[�h�{�[�i�X�v����.�萔����������TO
        
        //���g���[�h�{�[�i�X�v�����e�[�u���̃��R�[�h���擾�ł��Ȃ������ꍇ�́A�ȉ��̂Ƃ���B
        //���X�|���X.�萔���������ԕ\���t���O = false
        //���X�|���X.�萔���������ԁi���j = null
        //���X�|���X.�萔���������ԁi���j = null 
        WEB3PointCommissionInfoReferenceResponse l_response = 
            (WEB3PointCommissionInfoReferenceResponse)l_referenceRequest.createResponse();
            
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        if (l_row != null)
        {
            if (l_row.getFreeTermFrom() != null)
            {
                l_response.freeTermDisplayFlag = true;
            }
            else
            {
                l_response.freeTermDisplayFlag = false;
            }
            
            Date l_freeTermFromdate = WEB3DateUtility.getDate(l_row.getFreeTermFrom(), "yyyyMMdd");
            Date l_freeTermTodate = WEB3DateUtility.getDate(l_row.getFreeTermTo(), "yyyyMMdd");
            
            //���X�|���X.�萔���������ԁi���j = �g���[�h�{�[�i�X�v����.�萔����������FROM
            l_response.freeTermFrom = l_freeTermFromdate;
            
            //���X�|���X.�萔���������ԁi���j = �g���[�h�{�[�i�X�v����.�萔����������TO
            l_response.freeTermTo = l_freeTermTodate;            
        }
        else
        {
            //���X�|���X.�萔���������ԕ\���t���O = false
            l_response.freeTermDisplayFlag = false;
            
            //���X�|���X.�萔���������ԁi���j = null
            l_response.freeTermFrom = null;
            
            //���X�|���X.�萔���������ԁi���j = null
            l_response.freeTermTo = null;            
        }
   
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
