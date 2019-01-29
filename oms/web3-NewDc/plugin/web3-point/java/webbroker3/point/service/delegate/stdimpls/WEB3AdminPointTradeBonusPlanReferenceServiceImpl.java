head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointTradeBonusPlanReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�T�[�r�XImpl(WEB3AdminPointTradeBonusPlanReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/06/14 �A�C��(���u) �V�K�쐬
*/

package webbroker3.point.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.point.data.OrixTradeBonusPlanRow;
import webbroker3.point.message.WEB3AdminPointTradeBonusPlanReferenceRequest;
import webbroker3.point.message.WEB3AdminPointTradeBonusPlanReferenceResponse;
import webbroker3.point.service.delegate.WEB3AdminPointTradeBonusPlanReferenceService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�T�[�r�XImpl)<BR>
 * �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X�����N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointTradeBonusPlanReferenceServiceImpl implements WEB3AdminPointTradeBonusPlanReferenceService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointTradeBonusPlanReferenceServiceImpl.class);

    
    /**
     * @@roseuid 42AE35330261
     */
    public WEB3AdminPointTradeBonusPlanReferenceServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�j�\���f�[�^�擾�v �Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�ǁj�g���[�h�{�[�i�X�v�����Ɖ� /�i�Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�j�\���f�[�^�擾�v) <BR>
     *   1.5 ���X�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A�u�Y�����X�Ȃ��v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01386<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�ǁj�g���[�h�{�[�i�X�v�����Ɖ� /�i�Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�j�\���f�[�^�擾�v) <BR>
     *   1.7 �ڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A�u�Y���ڋq�Ȃ��v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01387<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A4FF3A0230
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (!(l_request instanceof WEB3AdminPointTradeBonusPlanReferenceRequest))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        WEB3AdminPointTradeBonusPlanReferenceRequest l_tradeBonusPlanReferenceRequest = 
            (WEB3AdminPointTradeBonusPlanReferenceRequest)l_request;
            
        //1.1 validate( )
        l_tradeBonusPlanReferenceRequest.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        
        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.5 get���X(�،���ЃR�[�h : String, ���X�R�[�h : String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3AccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            WEB3GentradeBranch l_genBranch = l_web3AccountManager.getWeb3GenBranch(
                l_strInstitutionCode, 
                l_tradeBonusPlanReferenceRequest.branchCode);//NotFoundException 
        }
        catch (NotFoundException l_e)
        {
            log.error(STR_METHOD_NAME, l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.6 validate���X����(���X�R�[�h : String)
        l_administrator.validateBranchPermission(l_tradeBonusPlanReferenceRequest.branchCode);
        
        //1.7 get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        try
        {
            WEB3GentradeMainAccount l_mainAccount = l_web3AccountManager.getMainAccount(
                l_strInstitutionCode, 
                l_tradeBonusPlanReferenceRequest.branchCode, 
                l_tradeBonusPlanReferenceRequest.accountCode);//WEB3SystemLayerException 
        }
        catch (WEB3SystemLayerException l_e)
        {
            if (WEB3ErrorCatalog.SYSTEM_ERROR_80005.equals(l_e.getErrorInfo()))
            {
                log.error(STR_METHOD_NAME, l_e);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01387,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        //1.8 createResponse( )
        WEB3AdminPointTradeBonusPlanReferenceResponse l_response = 
            (WEB3AdminPointTradeBonusPlanReferenceResponse)l_tradeBonusPlanReferenceRequest.createResponse();          

        //(*1)�g���[�h�{�[�i�X�v����DAO�ɂāA�ȉ��̏����̃��R�[�h���擾����B
        //[�擾����]
        //�،���ЃR�[�h = get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h = ���N�G�X�g.���X�R�[�h
        //�����R�[�h�̏�6�� = ���N�G�X�g.�ڋq�R�[�h
        OrixTradeBonusPlanRow l_row = null;
        
        try
        {
            String l_strWhere = "institution_code = ? AND branch_code = ? AND substr(account_code ,0 ,6) = ? ";
            Object[] l_bindObjs = new Object[]{
                l_strInstitutionCode, 
                l_tradeBonusPlanReferenceRequest.branchCode,
                l_tradeBonusPlanReferenceRequest.accountCode 
            }; 
            List l_records = Processors.getDefaultProcessor().doFindAllQuery(
                OrixTradeBonusPlanRow.TYPE,
                l_strWhere,
                l_bindObjs);//DataQueryException, DataNetworkException
            if (l_records.size() > 0)
            {
                l_row = (OrixTradeBonusPlanRow)l_records.get(0);
            }
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
        
        //1.9 (*2)�v���p�e�B�Z�b�g
        if (l_row != null)
        {
            // �|�C���g�K�p�N�� = �g���[�h�{�[�i�X�v����.�|�C���g�K�p�N��
            l_response.applyMonthCurr = l_row.getApplyMonthCurr();
            
            // �g���[�h�|�C���g = �g���[�h�{�[�i�X�v����.�g���[�h�|�C���g
            if (!l_row.getTrdPointCurrIsNull())
            {
                l_response.trdPointCurr = WEB3StringTypeUtility.formatNumber(l_row.getTrdPointCurr());
            }
            
            // �L�����y�[���|�C���g = �g���[�h�{�[�i�X�v����.�L�����y�[���|�C���g
            if (!l_row.getCmpPointCurrIsNull())
            {
                l_response.cmpPointCurr = WEB3StringTypeUtility.formatNumber(l_row.getCmpPointCurr());
            }
            
            // ���v�|�C���g = �g���[�h�{�[�i�X�v����.�g���[�h�|�C���g + �g���[�h�{�[�i�X�v����.�L�����y�[���|�C���g
            if (!l_row.getTrdPointCurrIsNull() || !l_row.getCmpPointCurrIsNull())
            {
                l_response.totalPointCurr = WEB3StringTypeUtility.formatNumber(l_row.getTrdPointCurr() + l_row.getCmpPointCurr());
            }
            
            // ������ = �g���[�h�{�[�i�X�v����.������
            l_response.cutRateCurr = l_row.getCutRateCurr();
            
            // �|�C���g�K�p�N���i�����j = �g���[�h�{�[�i�X�v����.�|�C���g�K�p�N���i�����j
            l_response.applyMonthNext = l_row.getApplyMonthNext();
            
            // �g���[�h�|�C���g�i�����j = �g���[�h�{�[�i�X�v����.�g���[�h�|�C���g�i�����j
            if (!l_row.getTrdPointNextIsNull())
            {
                l_response.trdPointNext = WEB3StringTypeUtility.formatNumber(l_row.getTrdPointNext());
            }
            
            // �L�����y�[���|�C���g�i�����j = �g���[�h�{�[�i�X�v����.�L�����y�[���|�C���g�i�����j
            if (!l_row.getCmpPointNextIsNull())
            {
                l_response.cmpPointNext = WEB3StringTypeUtility.formatNumber(l_row.getCmpPointNext());
            }
            
            // ���v�|�C���g�i�����j = �g���[�h�{�[�i�X�v����.�g���[�h�|�C���g�i�����j + �g���[�h�{�[�i�X�v����.�L�����y�[���|�C���g�i�����j
            if (!l_row.getTrdPointNextIsNull() || !l_row.getCmpPointNextIsNull())
            {
                l_response.totalPointNext = WEB3StringTypeUtility.formatNumber(l_row.getTrdPointNext() + l_row.getCmpPointNext());
            }
            
            // �������i�����j = �g���[�h�{�[�i�X�v����.�������i�����j
            l_response.cutRateNext = l_row.getCutRateNext();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
