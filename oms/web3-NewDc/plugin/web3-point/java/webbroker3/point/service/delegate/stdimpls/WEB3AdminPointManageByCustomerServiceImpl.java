head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointManageByCustomerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq�ʃ|�C���g�Ǘ��T�[�r�XImpl(WEB3AdminPointManageByCustomerServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

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
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.point.WEB3PointAdjust;
import webbroker3.point.WEB3PointApplyManager;
import webbroker3.point.data.PointTermDao;
import webbroker3.point.data.PointTermRow;
import webbroker3.point.data.PointTotalRow;
import webbroker3.point.message.WEB3AdminPointAdjustCompleteRequest;
import webbroker3.point.message.WEB3AdminPointAdjustCompleteResponse;
import webbroker3.point.message.WEB3AdminPointAdjustConfirmRequest;
import webbroker3.point.message.WEB3AdminPointAdjustConfirmResponse;
import webbroker3.point.message.WEB3AdminPointAdjustInputRequest;
import webbroker3.point.message.WEB3AdminPointAdjustInputResponse;
import webbroker3.point.message.WEB3AdminPointHistoryDetail;
import webbroker3.point.message.WEB3AdminPointHistoryReferenceRequest;
import webbroker3.point.message.WEB3AdminPointHistoryReferenceResponse;
import webbroker3.point.message.WEB3AdminPointManageDisplayRequest;
import webbroker3.point.message.WEB3AdminPointManageDisplayResponse;
import webbroker3.point.service.delegate.WEB3AdminPointManageByCustomerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�ڋq�ʃ|�C���g�Ǘ��T�[�r�XImpl)<BR>
 * �ڋq�ʃ|�C���g�Ǘ��T�[�r�X�����N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointManageByCustomerServiceImpl implements WEB3AdminPointManageByCustomerService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminPointManageByCustomerServiceImpl.class);

    /**
     * �ڋq�ʃ|�C���g�Ǘ��T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * <BR>
     *    get�Ǘ����()<BR>
     *    get���͉��()<BR>
     *    validate����()<BR>
     *    submit����()<BR>
     *    get�Ɖ���()<BR>
     * <BR>
     * ��L���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 41945155009C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request instanceof WEB3AdminPointManageDisplayRequest)
        {
            WEB3AdminPointManageDisplayResponse  l_response = 
                this.getManageScreen((WEB3AdminPointManageDisplayRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointAdjustInputRequest)
        {
            WEB3AdminPointAdjustInputResponse  l_response = 
                this.getInputScreen((WEB3AdminPointAdjustInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointAdjustConfirmRequest)
        {
            WEB3AdminPointAdjustConfirmResponse  l_response = 
                this.validateAdjust((WEB3AdminPointAdjustConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointAdjustCompleteRequest)
        {
            WEB3AdminPointAdjustCompleteResponse  l_response = 
                this.submitAdjust((WEB3AdminPointAdjustCompleteRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointHistoryReferenceRequest)
        {
            WEB3AdminPointHistoryReferenceResponse  l_response = 
                this.getReferenceScreen((WEB3AdminPointHistoryReferenceRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get�Ǘ����)<BR>
     * �Ǘ���ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g�Ǘ��jget�Ǘ���ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointManageDisplayResponse
     * @@roseuid 4194547B0186
     */
    protected WEB3AdminPointManageDisplayResponse getManageScreen(WEB3AdminPointManageDisplayRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getManageScreen(WEB3AdminPointManageDisplayRequest )";
        log.entering(STR_METHOD_NAME);

        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_MANAGE_BY_CUSTOMER, false);//WEB3BaseException
        
        //1.3 createResponse( )
        WEB3AdminPointManageDisplayResponse l_response = (WEB3AdminPointManageDisplayResponse)l_request.createResponse();  
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g�Ǘ��jget���͉�ʁv�Q�ƁB<BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�|�C���g�Ǘ��jget���͉�ʁv): <BR>
     *         1.5 get�ڋq(String, String, String)<BR>
     *          �ڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointAdjustInputResponse
     * @@roseuid 419454E500CB
     */
    protected WEB3AdminPointAdjustInputResponse getInputScreen(WEB3AdminPointAdjustInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminPointAdjustInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.3 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_MANAGE_BY_CUSTOMER, true);//WEB3BaseException
        
        //1.4 validate���X����(String[])
        l_admin.validateBranchPermission(l_request.branchCode);//WEB3BaseException
        
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
        //1.5 get�ڋq(String, String, String) QA:WEB3-POINT-A-CD-0006.xls
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            if (l_accountManager == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
 
            l_mainAccount = 
                l_accountManager.getMainAccount(l_admin.getInstitutionCode(), l_request.branchCode, l_request.accountCode);//WEB3SystemLayerException
        }
        catch (WEB3BaseException l_e)
        {
            String l_strMessage = "get�ڋq(String, String, String)error! " 
                + "InstitutionCode = " + l_admin.getInstitutionCode() 
                + ",BranchCode = " + l_request.branchCode 
                + ",AccountCode = " + l_request.accountCode;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage , 
                l_e);
        }                
         
        //1.6 get���p�\�|�C���g(String, String, String)
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        if (l_mainAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        long l_lngUsePossiblePoint = 
            l_applyManager.getUsePossiblePoint(l_admin.getInstitutionCode(), l_request.branchCode, l_mainAccount.getAccountCode());//WEB3SystemLayerException
        
        //1.7 createResponse( )
        WEB3AdminPointAdjustInputResponse l_response = (WEB3AdminPointAdjustInputResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.8 �v���p�e�B�Z�b�g
        l_response.availablePoint = WEB3StringTypeUtility.formatNumber(l_lngUsePossiblePoint);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * �����f�[�^�̐R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g�Ǘ��jvalidate�����v�Q�ƁB<BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�|�C���g�Ǘ��jvalidate�����v): <BR>
     *         1.5 get�ڋq(String, String, String)<BR>
     *          �ڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointAdjustConfirmResponse
     * @@roseuid 4194551B0271
     */
    protected WEB3AdminPointAdjustConfirmResponse validateAdjust(WEB3AdminPointAdjustConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAdjust(WEB3AdminPointAdjustConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.3 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_MANAGE_BY_CUSTOMER, true);//WEB3BaseException
        
        //1.4 validate���X����(String[])
        l_admin.validateBranchPermission(l_request.branchCode);//WEB3BaseException
        
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
        //1.5 get�ڋq(String, String, String)
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        { 
            if (l_accountManager == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_mainAccount = 
                l_accountManager.getMainAccount(l_admin.getInstitutionCode(), l_request.branchCode, l_request.accountCode);//WEB3SystemLayerException
        }
        catch (WEB3BaseException l_e)
        {
            String l_strMessage = "get�ڋq(String, String, String)error! " 
                + "InstitutionCode = " + l_admin.getInstitutionCode() 
                + ",BranchCode = " + l_request.branchCode 
                + ",AccountCode = " + l_request.accountCode;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage , 
                l_e);
        }                
         
        //1.6 get���p�\�|�C���g(String, String, String)
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        
        //1.7 validate�����|�C���g(String, long)
        if (l_mainAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        long l_lngUsePossiblePoint = 
            l_applyManager.getUsePossiblePoint(l_admin.getInstitutionCode(), l_request.branchCode, l_mainAccount.getAccountCode());//WEB3SystemLayerException           
        log.debug("���p�\�|�C���g = " + l_lngUsePossiblePoint);
        l_applyManager.validateAdjustPoint(l_request.adjustPoint, l_lngUsePossiblePoint);    

        //1.8 createResponse( )
        WEB3AdminPointAdjustConfirmResponse l_response = (WEB3AdminPointAdjustConfirmResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.9 �v���p�e�B�Z�b�g
        l_response.beforeAvailablePoint = WEB3StringTypeUtility.formatNumber(l_lngUsePossiblePoint);
        
        long l_lngAdjustPoint = 0;
        if (l_request.adjustPoint != null || WEB3StringTypeUtility.isNumber(l_request.adjustPoint))
        {
            l_lngAdjustPoint = Long.parseLong(l_request.adjustPoint);
        }

        l_response.afterAvailablePoint = WEB3StringTypeUtility.formatNumber(
            l_lngAdjustPoint + l_lngUsePossiblePoint);
        log.debug("������̃|�C���g = " + l_response.afterAvailablePoint);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * �����f�[�^�̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g�Ǘ��jsubmit�����v�Q�ƁB<BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�|�C���g�Ǘ��jsubmit�����v): <BR>
     *         1.6 get�ڋq(String, String, String)<BR>
     *          �ڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�|�C���g�Ǘ��jsubmit�����v): <BR>
     *         1.7 get���p�\�|�C���g(String, String, String)<BR>
     *          ���N�G�X�g�f�[�^.�m�F�������O���p�\�|�C���g != <BR>
     *          get���p�\�|�C���g()�̖߂�l �̏ꍇ�A<BR>
     *          ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01731<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointAdjustCompleteResponse
     * @@roseuid 4194555601F4
     */
    protected WEB3AdminPointAdjustCompleteResponse submitAdjust(WEB3AdminPointAdjustCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitAdjust(WEB3AdminPointAdjustCompleteRequest )";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.3 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_MANAGE_BY_CUSTOMER, true);//WEB3BaseException
        
        //1.4 validate���X����(String[])
        l_admin.validateBranchPermission(l_request.branchCode);//WEB3BaseException
        
        //1.5 validate����p�X���[�h
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException
        
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
        //1.6 get�ڋq(String, String, String)
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            if (l_accountManager == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
 
            l_mainAccount = 
                l_accountManager.getMainAccount(l_admin.getInstitutionCode(), l_request.branchCode, l_request.accountCode);//WEB3SystemLayerException
        }
        catch (WEB3BaseException l_e)
        {
            String l_strMessage = "get�ڋq(String, String, String)error! " 
                + "InstitutionCode = " + l_admin.getInstitutionCode() 
                + ",BranchCode = " + l_request.branchCode 
                + ",AccountCode = " + l_request.accountCode;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage , 
                l_e);
        }                
        
        //1.7 get���p�\�|�C���g(String, String, String)
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //QA:WEB3-POINT-A-CD-0006.xls
        if (l_mainAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        long l_lngUsePossiblePoint = 
            l_applyManager.getUsePossiblePoint(l_admin.getInstitutionCode(), l_request.branchCode, l_mainAccount.getAccountCode());//WEB3SystemLayerException
        log.debug("���p�\�|�C���g = " + l_lngUsePossiblePoint);
        
        if (l_request.beforeAvailablePoint == null || !WEB3StringTypeUtility.isNumber(l_request.beforeAvailablePoint))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01731,
                getClass().getName() + STR_METHOD_NAME);
        }
       
        long l_lngBeforeAvailablePoint = Long.parseLong(l_request.beforeAvailablePoint);
        log.debug("�����O�̃|�C���g = " + l_lngBeforeAvailablePoint);
        
        if (l_lngBeforeAvailablePoint != l_lngUsePossiblePoint)
        {
            String l_strMessage = "���p�\�|�C���gerror!";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01731,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
            
        //1.8 validate�����|�C���g(String, long)
        l_applyManager.validateAdjustPoint(l_request.adjustPoint, l_lngUsePossiblePoint);
        
        //1.9 �|�C���g����(String, String, String, long) QA:WEB3-POINT-A-CD-0006.xls
        int l_intAdjustPoint = 0;
        if (l_request.adjustPoint != null && WEB3StringTypeUtility.isNumber(l_request.adjustPoint))
        {
            l_intAdjustPoint = Integer.parseInt(l_request.adjustPoint);
        }

        WEB3PointAdjust l_applyAdjust = new WEB3PointAdjust(
            l_admin.getInstitutionCode(), 
            l_request.branchCode, 
            l_mainAccount.getAccountCode(), 
            l_intAdjustPoint);
            
        //1.10 saveNew����(�|�C���g����, �Ǘ���)
        l_applyManager.saveNewAdjust(l_applyAdjust, l_admin);//WEB3SystemLayerException
        
        //1.11 createResponse( )
        WEB3AdminPointAdjustCompleteResponse l_response = (WEB3AdminPointAdjustCompleteResponse)l_request.createResponse();
    
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�Ɖ���)<BR>
     * �Ɖ��ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g�Ǘ��jget�Ɖ��ʂP�C�Q�v�Q�ƁB<BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�|�C���g�Ǘ��jget�Ɖ��ʂP�v): <BR>
     *         1.5 get�ڋq(String, String, String)<BR>
     *          �ڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointHistoryReferenceResponse
     * @@roseuid 4194558400BB
     */
    protected WEB3AdminPointHistoryReferenceResponse getReferenceScreen(WEB3AdminPointHistoryReferenceRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminPointHistoryReferenceRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.3 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_MANAGE_BY_CUSTOMER, false);//WEB3BaseException
        
        //1.4 validate���X����(String[])
        l_admin.validateBranchPermission(l_request.branchCode);//WEB3BaseException
        
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
        //1.5 get�ڋq(String, String, String)
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        { 
            if (l_accountManager == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_mainAccount = 
                l_accountManager.getMainAccount(l_admin.getInstitutionCode(), l_request.branchCode, l_request.accountCode);//WEB3SystemLayerException
        }
        catch (WEB3BaseException l_e)
        {
            String l_strMessage = "get�ڋq(String, String, String)error! " 
                + "InstitutionCode = " + l_admin.getInstitutionCode() 
                + ",BranchCode = " + l_request.branchCode 
                + ",AccountCode = " + l_request.accountCode;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage , 
                l_e);
        }                
        
        //1.6 get����\������(String)
        String[] l_strHistoryIndicationTerms = this.getHistoryIndicationTerm(l_admin.getInstitutionCode());
        
        //1.7 ArrayList( )
        ArrayList l_arrayListApply = new ArrayList();
        
        //1.8 �擾��������\�����Ԃ̔z��̗v�f����Loop����
        int l_intCount = 0;
        if (l_strHistoryIndicationTerms != null)
        {
            l_intCount = l_strHistoryIndicationTerms.length;
        }
        for (int i = 0; i < l_intCount; i++)
        {
            log.debug("����\������:" + l_strHistoryIndicationTerms[i]);
            
            //1.8.1 �|�C���g���𖾍�( )
            WEB3AdminPointHistoryDetail l_historyDetail = new WEB3AdminPointHistoryDetail();
            
            //1.8.2 �v���p�e�B�Z�b�g
            l_historyDetail.period = l_strHistoryIndicationTerms[i];
            
            List l_lisRecord = null;
            try
            {
                String l_strWhere = "institution_code = ? and branch_code = ? and account_code = ? and period = ?";
                //QA:WEB3-POINT-A-CD-0006.xls
                Object[] l_objBinds = new Object[]{
                    l_admin.getInstitutionCode(),
                    l_request.branchCode,
                    l_mainAccount.getAccountCode(),
                    l_strHistoryIndicationTerms[i]};
                
                l_lisRecord = Processors.getDefaultProcessor().doFindAllQuery(
                    PointTotalRow.TYPE,
                    l_strWhere,
                    l_objBinds);//DataFindException,DataQueryException,DataNetworkException
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
            if (l_lisRecord != null && l_lisRecord.size() > 1)
            {
                log.debug("�e�[�u���ɏd������Y���f�[�^�����݂��܂�");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            if (l_lisRecord != null && l_lisRecord.size() > 0)
            {
                log.debug("�|�C���g���v�e�[�u���̂��[�^�����݂���̏ꍇ");
                PointTotalRow l_row = (PointTotalRow)l_lisRecord.get(0);
                if (l_row == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //�����|�C���g
                log.debug("���v�l���|�C���g:" + l_row.getTotalGetPoint());
                if (!l_row.getTotalAdjustPointIsNull())
                {
                    int l_intTotalAdjustPoint = l_row.getTotalAdjustPoint();
                    log.debug("���v�����|�C���g:" + l_intTotalAdjustPoint);
                    if (l_intTotalAdjustPoint > 0)
                    {
                        int l_intAccrualPoint = l_intTotalAdjustPoint;
                        if (!l_row.getTotalGetPointIsNull())
                        {
                            l_intAccrualPoint += l_row.getTotalGetPoint();
                        }
                        l_historyDetail.accrualPoint = WEB3StringTypeUtility.formatNumber(l_intAccrualPoint);
                    }
                    else
                    {
                        if (!l_row.getTotalGetPointIsNull())
                        {
                            l_historyDetail.accrualPoint = WEB3StringTypeUtility.formatNumber(l_row.getTotalGetPoint());
                        }
                        else
                        {
                            l_historyDetail.accrualPoint = "0";
                        }
                    }
                }
                else
                {
                    if (!l_row.getTotalGetPointIsNull())
                    {
                        l_historyDetail.accrualPoint = WEB3StringTypeUtility.formatNumber(l_row.getTotalGetPoint());
                    }
                    else
                    {
                        l_historyDetail.accrualPoint = "0";
                    }
                }
                log.debug("*************** �����|�C���g1:" + l_historyDetail.accrualPoint);
                
                //���p�|�C���g
                log.debug("�����m��\���|�C���g:" + l_row.getWithdrawnApplyPoint());
                log.debug("�����m�蒲���|�C���g:" + l_row.getWithdrawnAdjustPoint());
                int l_intUsedPoint = 0;
                if (!l_row.getWithdrawnAdjustPointIsNull())
                {
                    l_intUsedPoint += l_row.getWithdrawnAdjustPoint();
                }
                if (!l_row.getWithdrawnApplyPointIsNull())
                {
                    l_intUsedPoint += l_row.getWithdrawnApplyPoint();
                }
                l_historyDetail.usedPoint = WEB3StringTypeUtility.formatNumber(l_intUsedPoint);
                log.debug("*************** ���p�|�C���g1:" + l_historyDetail.usedPoint);
                
                //�����|�C���g
                log.debug("���v�����|�C���g:" + l_row.getTotalAdjustPoint());
                if (!l_row.getTotalAdjustPointIsNull())
                {
                    l_historyDetail.adjustPoint = WEB3StringTypeUtility.formatNumber(l_row.getTotalAdjustPoint());
                }
                else
                {
                    l_historyDetail.adjustPoint = "0";
                }
                log.debug("*************** �����|�C���g1:" + l_historyDetail.adjustPoint);
            }
            else
            {
                log.debug("�|�C���g���v�e�[�u���̂��[�^�����݂��Ȃ��̏ꍇ");
                log.debug("*************** �����|�C���g1:0");
                log.debug("*************** ���p�|�C���g1:0");
                log.debug("*************** �����|�C���g1:0");
                l_historyDetail.accrualPoint = "0";
                l_historyDetail.usedPoint = "0";
                l_historyDetail.adjustPoint = "0";
            }
            
            //1.8.3 add(arg0 : Object)
            l_arrayListApply.add(l_historyDetail);
        }
        
        //1.9 toArray
        WEB3AdminPointHistoryDetail[] l_historyDetails = new WEB3AdminPointHistoryDetail[l_arrayListApply.size()];
        l_arrayListApply.toArray(l_historyDetails);
        
        //below code is wroten by the �V�[�P���X�}�u�i�|�C���g�Ǘ��jget�Ɖ��ʂQ�v
        
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.2 get���W�v�����|�C���g(String, String, String, String)
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        Calendar l_cal = new GregorianCalendar();            
        if (l_tsSystemTime == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_cal.setTime(l_tsSystemTime);            
        l_cal.add(Calendar.MONTH, -1);            
        Timestamp l_tsDate = new Timestamp(l_cal.getTimeInMillis());
        String l_strPreMonthDate = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM");        
        long l_lngNotTotalAdjustPoint_pre = l_applyManager.getNotTotalAdjustPoint(
            l_admin.getInstitutionCode(),
            l_request.branchCode,
            l_mainAccount.getAccountCode(),
            l_strPreMonthDate);//WEB3BaseException
        log.debug("�挎�̖��W�v�����|�C���g�F" + l_lngNotTotalAdjustPoint_pre);
        
        //1.3 get���W�v�����|�C���g(String, String, String, String)
        if (l_tsSystemTime == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_cal.setTime(l_tsSystemTime);            
        l_tsDate = new Timestamp(l_cal.getTimeInMillis());
        String l_strCurrMonthDate = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM");        
        long l_lngNotTotalAdjustPoint_curr = l_applyManager.getNotTotalAdjustPoint(
            l_admin.getInstitutionCode(),
            l_request.branchCode,
            l_mainAccount.getAccountCode(),
            l_strCurrMonthDate);//WEB3BaseException
        log.debug("�����̖��W�v�����|�C���g�F" + l_lngNotTotalAdjustPoint_curr);
        
        //�P�j�����Ɛ挎�̔N�������|�C���g���𖾍ׂ�index���擾���A���ꂼ��a�Ab�Ƃ���B
        int l_intHistoryDetailIndex_curr = 0;
        int l_intHistoryDetailIndex_pre = 1;
        //2) �ȉ��̂Ƃ���ɁA�����|�C���g�ɉ��Z����BWEB3StringTypeUtility.formatNumber
        if (l_historyDetails[l_intHistoryDetailIndex_pre].adjustPoint != null
            && !"".equals(l_historyDetails[l_intHistoryDetailIndex_pre].adjustPoint.trim()))
        {
            l_historyDetails[l_intHistoryDetailIndex_pre].adjustPoint = 
                WEB3StringTypeUtility.formatNumber(
                    Long.parseLong(l_historyDetails[l_intHistoryDetailIndex_pre].adjustPoint) + l_lngNotTotalAdjustPoint_pre);
        }
        else
        {
            l_historyDetails[l_intHistoryDetailIndex_pre].adjustPoint = 
                WEB3StringTypeUtility.formatNumber(l_lngNotTotalAdjustPoint_pre);
        }
        log.debug("******** �挎�̒����|�C���g�F" + l_historyDetails[l_intHistoryDetailIndex_pre].adjustPoint);
        
        if (l_historyDetails[l_intHistoryDetailIndex_curr].adjustPoint != null
            && !"".equals(l_historyDetails[l_intHistoryDetailIndex_curr].adjustPoint.trim()))
        {
            l_historyDetails[l_intHistoryDetailIndex_curr].adjustPoint = 
                WEB3StringTypeUtility.formatNumber(
                    Long.parseLong(l_historyDetails[l_intHistoryDetailIndex_curr].adjustPoint) + l_lngNotTotalAdjustPoint_curr);
        }
        else
        {
            l_historyDetails[l_intHistoryDetailIndex_curr].adjustPoint = 
                WEB3StringTypeUtility.formatNumber(l_lngNotTotalAdjustPoint_curr);
        }
        log.debug("******** �����̒����|�C���g�F" + l_historyDetails[l_intHistoryDetailIndex_curr].adjustPoint);
        
        //�R�j�����̖��W�v�����|�C���g>0�̏ꍇ�A�����̔����|�C���g�ɉ��Z����B
        if (l_lngNotTotalAdjustPoint_curr > 0)
        {
            log.debug("�����̖��W�v�����|�C���g>0�̏ꍇ");
            if (l_historyDetails[l_intHistoryDetailIndex_curr].accrualPoint != null
                && !"".equals(l_historyDetails[l_intHistoryDetailIndex_curr].accrualPoint.trim()))
            {
                l_historyDetails[l_intHistoryDetailIndex_curr].accrualPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intHistoryDetailIndex_curr].accrualPoint) + l_lngNotTotalAdjustPoint_curr);
            }
            else
            {
                l_historyDetails[l_intHistoryDetailIndex_curr].accrualPoint = 
                    WEB3StringTypeUtility.formatNumber(l_lngNotTotalAdjustPoint_curr);
            }
        }
        log.debug("******** �����̔����|�C���g:" + l_historyDetails[l_intHistoryDetailIndex_curr].accrualPoint);
        
        //�S�j�挎�̖��W�v�����|�C���g>0�̏ꍇ�A�挎�̔����|�C���g�ɉ��Z����B
        if (l_lngNotTotalAdjustPoint_pre > 0)
        {
            log.debug("�挎�̖��W�v�����|�C���g>0�̏ꍇ");
            if (l_historyDetails[l_intHistoryDetailIndex_pre].accrualPoint != null
                && !"".equals(l_historyDetails[l_intHistoryDetailIndex_pre].accrualPoint.trim()))
            {
                l_historyDetails[l_intHistoryDetailIndex_pre].accrualPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intHistoryDetailIndex_pre].accrualPoint) + l_lngNotTotalAdjustPoint_pre);
            }
            else
            {
                l_historyDetails[l_intHistoryDetailIndex_pre].accrualPoint = 
                    WEB3StringTypeUtility.formatNumber(l_lngNotTotalAdjustPoint_pre);
            }
        }
        log.debug("******** �挎�̔����|�C���g:" + l_historyDetails[l_intHistoryDetailIndex_pre].accrualPoint);
        
        //1.4 get�\���|�C���g(String, String, String, String)
        l_cal.setTime(l_tsSystemTime);            
        l_cal.add(Calendar.MONTH, -2);
        l_tsDate = new Timestamp(l_cal.getTimeInMillis());
        String l_strPre2MonthDate = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM");        
        long l_lngApplyPoint_pre2 = l_applyManager.getApplyPoint(
            l_admin.getInstitutionCode(),
            l_request.branchCode,
            l_mainAccount.getAccountCode(),
            l_strPre2MonthDate);//WEB3BaseException
        log.debug("�惖���̐\���|�C���g:" + l_lngApplyPoint_pre2);
        
        //1.5 get�\���|�C���g(String, String, String, String)
        if (l_tsSystemTime == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_cal.setTime(l_tsSystemTime);            
        l_cal.add(Calendar.MONTH, -1);            
        l_tsDate = new Timestamp(l_cal.getTimeInMillis());
        l_strPreMonthDate = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM");        
        long l_lngApplyPoint_pre = l_applyManager.getApplyPoint(
            l_admin.getInstitutionCode(),
            l_request.branchCode,
            l_mainAccount.getAccountCode(),
            l_strPreMonthDate);//WEB3BaseException
        log.debug("�挎�̐\���|�C���g:" + l_lngApplyPoint_pre);
        
        //1.6 get�\���|�C���g(String, String, String, String)
        l_cal.setTime(l_tsSystemTime);            
        l_tsDate = new Timestamp(l_cal.getTimeInMillis());
        l_strCurrMonthDate = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM");        
        long l_lngApplyPoint_curr = l_applyManager.getApplyPoint(
            l_admin.getInstitutionCode(),
            l_request.branchCode,
            l_mainAccount.getAccountCode(),
            l_strCurrMonthDate);//WEB3BaseException
        log.debug("�����̐\���|�C���g:" + l_lngApplyPoint_curr);
        
        //1.7 get�L��������(String, String)
        String l_strValidTermMon = l_applyManager.getValidTermMon(l_admin.getInstitutionCode(), l_strCurrMonthDate);//WEB3BaseException
        log.debug("�L��������:" + l_strValidTermMon);
        
        //�P�jget�L��������()�̖߂�l�ƈ�v����N�������|�C���g���𖾍ׂ�index���擾���Ax �Ƃ���B
        int l_intValidTermMonIndex = -1;
        l_intCount = 0;
        if (l_strHistoryIndicationTerms != null)
        {
            l_intCount = l_strHistoryIndicationTerms.length;
        }
        for (int i = 0; i < l_intCount; i++)
        {
            if (l_strHistoryIndicationTerms[i] == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            if (l_strHistoryIndicationTerms[i].equals(l_strValidTermMon))
            {
                l_intValidTermMonIndex = i;
                break;
            }
        }
        if (l_intValidTermMonIndex == -1)
        {
            String l_strMessage = "�L��������error! " + l_strValidTermMon;
            log.debug(l_strMessage);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�Q�j�e�����ɏ]���A�ȉ��̏������s���B
        //�Q�|�P�j��X���̐\���|�C���g>0�̏ꍇ
        if (l_lngApplyPoint_pre2 > 0)
        {
            if (l_historyDetails[l_intValidTermMonIndex + 2].usedPoint != null
                && !"".equals(l_historyDetails[l_intValidTermMonIndex + 2].usedPoint.trim()))
            {
                l_historyDetails[l_intValidTermMonIndex + 2].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 2].usedPoint) + l_lngApplyPoint_pre2);
            }
            else
            {
                l_historyDetails[l_intValidTermMonIndex + 2].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(l_lngApplyPoint_pre2);
            }
        }
        //�Q�|�Q�j�挎�̐\���|�C���g>0�̏ꍇ
        if (l_lngApplyPoint_pre > 0)
        {
            if (l_historyDetails[l_intValidTermMonIndex + 1].usedPoint != null
                && !"".equals(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint.trim()))
            {
                l_historyDetails[l_intValidTermMonIndex + 1].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint) + l_lngApplyPoint_pre);
            }
            else
            {
                l_historyDetails[l_intValidTermMonIndex + 1].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(l_lngApplyPoint_pre);
            }
        }
        //�Q�|�R�j�挎�̖��W�v�����|�C���g<0�̏ꍇ
        if (l_lngNotTotalAdjustPoint_pre < 0)
        {
            if (l_historyDetails[l_intValidTermMonIndex + 1].usedPoint != null
                && !"".equals(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint.trim()))
            {
                l_historyDetails[l_intValidTermMonIndex + 1].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint) - l_lngNotTotalAdjustPoint_pre);
            }
            else
            {
                l_historyDetails[l_intValidTermMonIndex + 1].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(0 - l_lngNotTotalAdjustPoint_pre);
            }
        }
        //�Q�|�S�j�����̐\���|�C���g>0�̏ꍇ
        if (l_lngApplyPoint_curr > 0)
        {
            if (l_historyDetails[l_intValidTermMonIndex].usedPoint != null
                && !"".equals(l_historyDetails[l_intValidTermMonIndex].usedPoint.trim()))
            {
                l_historyDetails[l_intValidTermMonIndex].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intValidTermMonIndex].usedPoint) + l_lngApplyPoint_curr);
            }
            else
            {
                l_historyDetails[l_intValidTermMonIndex].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(l_lngApplyPoint_curr);
            }
        }
        //�Q�|�T�j�����̖��W�v�����|�C���g<0�̏ꍇ
        if (l_lngNotTotalAdjustPoint_curr < 0)
        {
            if (l_historyDetails[l_intValidTermMonIndex].usedPoint != null
                && !"".equals(l_historyDetails[l_intValidTermMonIndex].usedPoint.trim()))
            {
                l_historyDetails[l_intValidTermMonIndex].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intValidTermMonIndex].usedPoint) - l_lngNotTotalAdjustPoint_curr);
            }
            else
            {
                l_historyDetails[l_intValidTermMonIndex].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(0 - l_lngNotTotalAdjustPoint_curr);
            }
        }
        //(*4) �ȉ��̏������s���B
        long l_lngTempA = 0;
        long l_lngTempB = 0;
        long l_lngTempC = 0;
        //�P�j�|�C���g���𖾍�[x+2].�����|�C���g < �|�C���g���𖾍�[x+2].���p�|�C���g �̏ꍇ
        long l_lngAccrualPoint = 0;
        long l_lngUsedPoint = 0;
        if (l_historyDetails[l_intValidTermMonIndex + 2].accrualPoint != null
            && !"".equals(l_historyDetails[l_intValidTermMonIndex + 2].accrualPoint.trim()))
        {
            l_lngAccrualPoint = Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 2].accrualPoint);
        }
        if (l_historyDetails[l_intValidTermMonIndex + 2].usedPoint != null
            && !"".equals(l_historyDetails[l_intValidTermMonIndex + 2].usedPoint.trim()))
        {
            l_lngUsedPoint = Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 2].usedPoint);
        }
        if(l_lngAccrualPoint < l_lngUsedPoint)
        {
            l_lngTempA = l_lngUsedPoint - l_lngAccrualPoint;
            l_historyDetails[l_intValidTermMonIndex + 2].usedPoint = WEB3StringTypeUtility.formatNumber(l_lngAccrualPoint);
            if (l_historyDetails[l_intValidTermMonIndex + 1].usedPoint != null
                && !"".equals(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint.trim()))
            {
                l_historyDetails[l_intValidTermMonIndex + 1].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint) + l_lngTempA);
            }
            else
            {
                l_historyDetails[l_intValidTermMonIndex + 1].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(l_lngTempA);
            }
        }
        //�Q�j�|�C���g���𖾍�[x+1].�����|�C���g < �|�C���g���𖾍�[x+1].���p�|�C���g �̏ꍇ
        l_lngAccrualPoint = 0;
        l_lngUsedPoint = 0;
        if (l_historyDetails[l_intValidTermMonIndex + 1].accrualPoint != null
            && !"".equals(l_historyDetails[l_intValidTermMonIndex + 1].accrualPoint.trim()))
        {
            l_lngAccrualPoint = Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 1].accrualPoint);
        }
        if (l_historyDetails[l_intValidTermMonIndex + 1].usedPoint != null
            && !"".equals(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint.trim()))
        {
            l_lngUsedPoint = Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint);
        }
        if(l_lngAccrualPoint < l_lngUsedPoint)
        {
            l_lngTempB = l_lngUsedPoint - l_lngAccrualPoint;
            l_historyDetails[l_intValidTermMonIndex + 1].usedPoint = WEB3StringTypeUtility.formatNumber(l_lngAccrualPoint);
            if (l_historyDetails[l_intValidTermMonIndex].usedPoint != null
                && !"".equals(l_historyDetails[l_intValidTermMonIndex].usedPoint.trim()))
            {
                l_historyDetails[l_intValidTermMonIndex].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intValidTermMonIndex].usedPoint) + l_lngTempB);
            }
            else
            {
                l_historyDetails[l_intValidTermMonIndex].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(l_lngTempB);
            }
        }
        //�R�j�|�C���g���𖾍�[x].�����|�C���g < �|�C���g���𖾍�[x].���p�|�C���g �̏ꍇ
        l_lngAccrualPoint = 0;
        l_lngUsedPoint = 0;
        if (l_historyDetails[l_intValidTermMonIndex].accrualPoint != null
            && !"".equals(l_historyDetails[l_intValidTermMonIndex].accrualPoint.trim()))
        {
            l_lngAccrualPoint = Long.parseLong(l_historyDetails[l_intValidTermMonIndex].accrualPoint);
        }
        if (l_historyDetails[l_intValidTermMonIndex].usedPoint != null
            && !"".equals(l_historyDetails[l_intValidTermMonIndex].usedPoint.trim()))
        {
            l_lngUsedPoint = Long.parseLong(l_historyDetails[l_intValidTermMonIndex].usedPoint);
        }
        if(l_lngAccrualPoint < l_lngUsedPoint)
        {
            for (int i = l_intValidTermMonIndex; i >= 0; i--)
            {
                l_lngAccrualPoint = 0;
                l_lngUsedPoint = 0;
                if (l_historyDetails[i].accrualPoint != null
                    && !"".equals(l_historyDetails[i].accrualPoint.trim()))
                {
                    l_lngAccrualPoint = Long.parseLong(l_historyDetails[i].accrualPoint);
                }
                if (l_historyDetails[i].usedPoint != null
                    && !"".equals(l_historyDetails[i].usedPoint.trim()))
                {
                    l_lngUsedPoint = Long.parseLong(l_historyDetails[i].usedPoint);
                }
                if(l_lngAccrualPoint >= l_lngUsedPoint)
                {
                    break;
                }
                if (i > 0)
                {
                    l_lngTempC = l_lngUsedPoint - l_lngAccrualPoint;
                    l_historyDetails[i].usedPoint = WEB3StringTypeUtility.formatNumber(l_lngAccrualPoint);
                    if (l_historyDetails[i - 1].usedPoint != null
                        && !"".equals(l_historyDetails[i - 1].usedPoint.trim()))
                    {
                        l_historyDetails[i - 1].usedPoint = 
                            WEB3StringTypeUtility.formatNumber(
                                Long.parseLong(l_historyDetails[i - 1].usedPoint) + l_lngTempC);
                    }
                    else
                    {
                        l_historyDetails[i - 1].usedPoint = 
                            WEB3StringTypeUtility.formatNumber(l_lngTempC);
                    }
                }
            }
        }
                
        //1.8 get���p�\�|�C���g(String, String, String)
        long l_lngUsePossiblePoint =  l_applyManager.getUsePossiblePoint(
            l_admin.getInstitutionCode(),
            l_request.branchCode,
            l_mainAccount.getAccountCode());//WEB3BaseException
        
        //1.9 createResponse( )
        WEB3AdminPointHistoryReferenceResponse l_response = (WEB3AdminPointHistoryReferenceResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.10 �v���p�e�B�Z�b�g
        l_response.availablePoint = WEB3StringTypeUtility.formatNumber(l_lngUsePossiblePoint);
        l_response.pointHistoryList = l_historyDetails;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get����\������)<BR>
     * ����\�����ԂƂȂ�N���iYYYYMM�j�̔z����擾����B<BR>
     * <BR>
     * �P�j�|�C���g�L�������e�[�u������A����\�����Ԃ��擾����B<BR>
     * <BR>
     *    [�擾����]<BR>
     *    �،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * <BR>
     * �Q�j���ݎ�������擾�����N���iYYYYMM�j�����ɁA�P�j�Ŏ擾<BR>
     * ��������\�����Ԃ̊Ԃ̔N���iYYYYMM�j���Z�o����B<BR>
     * <BR>
     *    �����݂̔N������i����\������-1�j�����O�܂ł̔N�����Z�o����B<BR>
     * <BR>
     * �R�j �Q�j�̎Z�o���ʂ��~���̔z��ɂ��ĕԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@return String[]
     * @@roseuid 41AFC64303D0
     */
    protected String[] getHistoryIndicationTerm(String l_strInstitutionCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getHistoryIndicationTerm(String )";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            // �P�j�|�C���g�L�������e�[�u������A����\�����Ԃ��擾����B
            PointTermRow l_pointTermRow = PointTermDao.findRowByPk(l_strInstitutionCode);
            if (l_pointTermRow == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            // �Q�j���ݎ�������擾�����N���iYYYYMM�j�����ɁA�P�j�Ŏ擾
            String l_strDisplayPeriod = l_pointTermRow.getDisplayPeriod();
            if (l_strDisplayPeriod == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            int l_intDisplayPeriod = Integer.parseInt(l_strDisplayPeriod);
            String[] l_strHistoryIndicationTerms = new String[l_intDisplayPeriod];

            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            Calendar l_cal = new GregorianCalendar();            
            l_cal.setTime(l_tsSystemTime);            
            for (int i = 0; i < l_intDisplayPeriod; i++)
            {
                Timestamp l_tsDate = new Timestamp(l_cal.getTimeInMillis());
                l_strHistoryIndicationTerms[i] = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM");
                l_cal.add(Calendar.MONTH, -1);            
            }
            
            // �R�j �Q�j�̎Z�o���ʂ��~���̔z��ɂ��ĕԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_strHistoryIndicationTerms;
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
    }
}
@
