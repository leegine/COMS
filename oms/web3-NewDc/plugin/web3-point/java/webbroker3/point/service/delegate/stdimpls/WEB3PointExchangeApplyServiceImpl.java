head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointExchangeApplyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�����\���T�[�r�XImpl(WEB3PointExchangeApplyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.service.delegate.stdimpls;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ApplyAcceptDivDef;
import webbroker3.common.define.WEB3ApplyCancelDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.point.WEB3PointApply;
import webbroker3.point.WEB3PointApplyManager;
import webbroker3.point.WEB3PointCategory;
import webbroker3.point.WEB3PointClientRequestService;
import webbroker3.point.WEB3PointPremium;
import webbroker3.point.WEB3PointProductManager;
import webbroker3.point.data.PointApplyParams;
import webbroker3.point.data.PointPremiumMasterDao;
import webbroker3.point.data.PointPremiumMasterRow;
import webbroker3.point.define.WEB3ApplyStateDivDef;
import webbroker3.point.message.WEB3AdminPointCategoryUnit;
import webbroker3.point.message.WEB3PointApplyCompleteRequest;
import webbroker3.point.message.WEB3PointApplyCompleteResponse;
import webbroker3.point.message.WEB3PointApplyConfirmRequest;
import webbroker3.point.message.WEB3PointApplyConfirmResponse;
import webbroker3.point.message.WEB3PointApplyInputRequest;
import webbroker3.point.message.WEB3PointApplyInputResponse;
import webbroker3.point.message.WEB3PointApplyReferenceRequest;
import webbroker3.point.message.WEB3PointApplyReferenceResponse;
import webbroker3.point.message.WEB3PointApplyStateDetail;
import webbroker3.point.message.WEB3PointPremiumUnit;
import webbroker3.point.service.delegate.WEB3PointExchangeApplyService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�|�C���g�����\���T�[�r�XImpl)<BR>
 * �|�C���g�����\���T�[�r�X�����N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointExchangeApplyServiceImpl extends WEB3PointClientRequestService implements WEB3PointExchangeApplyService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3PointExchangeApplyServiceImpl.class);

    /**
     * �|�C���g�����\���T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * <BR>
     *    get�T�[�r�X���()<BR>
     *    get�I�����()<BR>
     *    validate�\��()<BR>
     *    submit�\��()<BR>
     * <BR>
     * ��L���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 419DA13A026B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request instanceof WEB3PointApplyReferenceRequest)
        {
            WEB3PointApplyReferenceResponse l_response = 
                this.getServiceScreen((WEB3PointApplyReferenceRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3PointApplyInputRequest)
        {
            WEB3PointApplyInputResponse l_response = 
                this.getSelectionScreen((WEB3PointApplyInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3PointApplyConfirmRequest)
        {
            WEB3PointApplyConfirmResponse l_response = 
                this.validateApply((WEB3PointApplyConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3PointApplyCompleteRequest)
        {
            WEB3PointApplyCompleteResponse l_response = 
                this.submitApply((WEB3PointApplyCompleteRequest)l_request);
        
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
     * (get�T�[�r�X���)<BR>
     * �T�[�r�X��ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g�����\���jget�T�[�r�X��ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3PointApplyReferenceResponse
     * @@roseuid 419DA13A028A
     */
    protected WEB3PointApplyReferenceResponse getServiceScreen(WEB3PointApplyReferenceRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getServiceScreen(WEB3PointApplyReferenceRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.2 get�،���ЃR�[�h( )
        String l_strInstitutionCode = this.getInstitutionCode();
        
        //1.3 get���X�R�[�h( )
        String l_strBranchCode = this.getBranchCode();
        
        //1.4 get�ڋq�R�[�h( )
        String l_strAccountCode = this.getAccountCode();
        
        //1.5 get���p�\�|�C���g(String, String, String)
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        long l_lngUsePossiblePoint = 
            l_applyManager.getUsePossiblePoint(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);//WEB3BaseException
        
        //1.6 get�������Ӄ|�C���g(String, String, String)
        long l_lngExpirationAttentionPoint = 
            l_applyManager.getExpirationAttentionPoint(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);//WEB3BaseException
        
        //1.7 get�J�e�S���[(String)
        WEB3PointProductManager l_productManager = (WEB3PointProductManager)Services.getService(WEB3PointProductManager.class);
        if (l_productManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3PointCategory[] l_categories = l_productManager.getCategories(l_strInstitutionCode);
        
        //1.8 ArrayList( )
        ArrayList l_lisCategory = new ArrayList();
        
        //1.9 (*1)�擾�����J�e�S���[�I�u�W�F�N�g����Loop����
        int l_intCategoryCount = 0;
        if (l_categories != null)
        {
            l_intCategoryCount = l_categories.length;
        }
        for (int i = 0; i < l_intCategoryCount; i++)
        {
            //1.9.1 �J�e�S���[����( )
            WEB3AdminPointCategoryUnit l_categoryUnit = new WEB3AdminPointCategoryUnit();            
            
            if (l_categories[i] == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //1.9.2 (*2)�v���p�e�B�Z�b�g
            l_categoryUnit.categoryNo = WEB3StringTypeUtility.formatNumber(l_categories[i].getCategoryNo());
            l_categoryUnit.categoryName = l_categories[i].getCategoryName();
            l_categoryUnit.categoryOutline = l_categories[i].getCategoryOutline();
            
            //1.9.3 add(arg0 : Object)
            l_lisCategory.add(l_categoryUnit);
        }
        
        //1.10 toArray( )
        WEB3AdminPointCategoryUnit[] l_categoryUnits = new WEB3AdminPointCategoryUnit[l_lisCategory.size()];
        l_lisCategory.toArray(l_categoryUnits);
        
        //1.11 get�\��(String, String, String)
        PointApplyParams[] l_pointApplyParams = l_applyManager.getApply(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);//WEB3BaseException
        
        //1.12 ArrayList( )
        ArrayList l_lisPointApply = new ArrayList();
        
        //1.13 (*3)�擾�����\���s�I�u�W�F�N�g����Loop����
        int l_intApplyCount = 0;
        if (l_pointApplyParams != null)
        {
            l_intApplyCount = l_pointApplyParams.length;
        }
        for (int i = 0; i < l_intApplyCount; i++)
        {
            if (l_pointApplyParams[i] == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.13.1 get�i�i(String, String)
            WEB3PointPremium l_premium = l_productManager.getPremium(l_pointApplyParams[i].getInstitutionCode(), l_pointApplyParams[i].getPremiumNo());//WEB3BaseException,NotFoundException
            
            //1.13.2 �\���󋵖���( )
            WEB3PointApplyStateDetail l_applyStateDetail = new WEB3PointApplyStateDetail();
            
            //1.13.3 (*4)�v���p�e�B�Z�b�g
            //�\������
            l_applyStateDetail.applyDate = l_pointApplyParams[i].getApplyTimestamp();
            //�i�i�ԍ�
            l_applyStateDetail.premiumNo = l_pointApplyParams[i].getPremiumNo();
            if (l_premium != null)
            {
                //�i�i��
                l_applyStateDetail.premiumName = l_premium.getPremiumName();
            }
            else
            {
                l_applyStateDetail.premiumName = null;
            }
            
            //�K�v�|�C���g
            if (!l_pointApplyParams[i].getUsedPointIsNull())
            {
                l_applyStateDetail.requiredPoint = WEB3StringTypeUtility.formatNumber(l_pointApplyParams[i].getUsedPoint());
            }
            
            //���l
            String l_strApplyAcceptDiv = l_pointApplyParams[i].getApplyAcceptDiv();
            String l_strApplyCancelDiv = l_pointApplyParams[i].getApplyCancelDiv();
            
            if (WEB3ApplyAcceptDivDef.APPLYING.equals(l_strApplyAcceptDiv) 
                && WEB3ApplyCancelDivDef.NOT_CANCELED.equals(l_strApplyCancelDiv))
            {
                l_applyStateDetail.applyStateDiv = WEB3ApplyStateDivDef.ACCEPTING;
            }
            
            if (WEB3ApplyAcceptDivDef.INSTITUTION_ACCEPTED.equals(l_strApplyAcceptDiv) 
                && WEB3ApplyCancelDivDef.NOT_CANCELED.equals(l_strApplyCancelDiv))
            {
                l_applyStateDetail.applyStateDiv = WEB3ApplyStateDivDef.ACCEPTED;
            }
            
            if (WEB3ApplyCancelDivDef.CANCELED.equals(l_strApplyCancelDiv))
            {
                l_applyStateDetail.applyStateDiv = WEB3ApplyStateDivDef.CANCEL;
            }
            
            //���͋敪
            l_applyStateDetail.orderRootDiv = l_pointApplyParams[i].getApplyRootDiv();
            //����
            l_applyStateDetail.operatorCode = l_pointApplyParams[i].getTraderCode();
            
            //1.13.4 add(arg0 : Object)
            l_lisPointApply.add(l_applyStateDetail);
        }
        //1.14 toArray( )
        WEB3PointApplyStateDetail[] l_applyStateDetails = new WEB3PointApplyStateDetail[l_lisPointApply.size()]; 
        l_lisPointApply.toArray(l_applyStateDetails);
        
        //1.15 createResponse( )
        WEB3PointApplyReferenceResponse l_response = (WEB3PointApplyReferenceResponse)l_request.createResponse();
        
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.16 (*5)�v���p�e�B�Z�b�g
        //���p�\�|�C���g
        l_response.availablePoint = WEB3StringTypeUtility.formatNumber(l_lngUsePossiblePoint);
        //�������Ӄ|�C���g
        l_response.lapseWarningPoint = WEB3StringTypeUtility.formatNumber(l_lngExpirationAttentionPoint);
        //�J�e�S���[�ꗗ
        l_response.categoryList = l_categoryUnits;
        //�\���󋵈ꗗ
        l_response.applyStateList = l_applyStateDetails;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�I�����)<BR>
     * �I����ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g�����\���jget�I����ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3PointApplyInputResponse
     * @@roseuid 419DA1AB0299
     */
    protected WEB3PointApplyInputResponse getSelectionScreen(WEB3PointApplyInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getSelectionScreen(WEB3PointApplyInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3 get�،���ЃR�[�h( )
        String l_strInstitutionCode = this.getInstitutionCode();
        
        //1.4 get���X�R�[�h( )
        String l_strBranchCode = this.getBranchCode();
        
        //1.5 get�ڋq�R�[�h( )
        String l_strAccountCode = this.getAccountCode();

        //1.6 get�戵�\�i�i(String, String)
        WEB3PointProductManager l_productManager = (WEB3PointProductManager)Services.getService(WEB3PointProductManager.class);
        if (l_productManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3PointPremium[] l_premiums = l_productManager.getHandingPossiblePremium(l_strInstitutionCode, l_request.categoryNo);

        //1.7 ArrayList( )
        ArrayList l_lisPremium = new ArrayList();        
        
        //1.8 (*1) �擾�����i�i�I�u�W�F�N�g����Loop����
        int  l_intPremiumCount = 0;
        if (l_premiums != null)
        {
            l_intPremiumCount = l_premiums.length;
        }
        for (int i = 0; i < l_intPremiumCount; i++)
        {
            //1.8.1 �i�i����( )
            WEB3PointPremiumUnit l_premiumUnit = new WEB3PointPremiumUnit();

            if (l_premiums[i] == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //1.8.2 (*2)�v���p�e�B�Z�b�g
            l_premiumUnit.premiumNo = l_premiums[i].getPremiumNo();
            l_premiumUnit.premiumName = l_premiums[i].getPremiumName();
            l_premiumUnit.requiredPoint = 
                WEB3StringTypeUtility.formatNumber(l_premiums[i].getRequiredPoint());
            
            //1.8.3 add(arg0 : Object)
            l_lisPremium.add(l_premiumUnit);
        }
        
        //1.9 toArray( )
        WEB3PointPremiumUnit[] l_premiumUnits = new WEB3PointPremiumUnit[l_lisPremium.size()]; 
        l_lisPremium.toArray(l_premiumUnits);
        
        //1.10 get���p�\�|�C���g(String, String, String)
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        long l_lngUsePossiblePoint = 
            l_applyManager.getUsePossiblePoint(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);//WEB3BaseException
        
        //1.11 get�������Ӄ|�C���g(String, String, String)
        long l_lngExpirationAttentionPoint = 
            l_applyManager.getExpirationAttentionPoint(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);//WEB3BaseException

        //1.12 createResponse( )
        WEB3PointApplyInputResponse l_response = (WEB3PointApplyInputResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.13 (*3)�v���p�e�B�Z�b�g
        //���p�\�|�C���g
        l_response.availablePoint = WEB3StringTypeUtility.formatNumber(l_lngUsePossiblePoint);
        //�������Ӄ|�C���g
        l_response.lapseWarningPoint = WEB3StringTypeUtility.formatNumber(l_lngExpirationAttentionPoint);
        //�i�i�ꗗ
        l_response.premiumList = l_premiumUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�\��)<BR>
     * �\���̐R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g�����\���jvalidate�\���v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3PointApplyConfirmResponse
     * @@roseuid 419DA13A0299
     */
    protected WEB3PointApplyConfirmResponse validateApply(WEB3PointApplyConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateApply(WEB3PointApplyConfirmRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();

        //1.2 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3 get�،���ЃR�[�h( )
        String l_strInstitutionCode = this.getInstitutionCode();
        
        //1.4 get���X�R�[�h( )
        String l_strBranchCode = this.getBranchCode();
        
        //1.5 get�ڋq�R�[�h( )
        String l_strAccountCode = this.getAccountCode();
        
        //1.6 validate�\���\(String, String, String, String)
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        l_applyManager.validateApplyPossible(l_strInstitutionCode, l_strBranchCode, l_strAccountCode, l_request.premiumNo);
        
        //1.7 createResponse( )
        WEB3PointApplyConfirmResponse l_response = (WEB3PointApplyConfirmResponse)l_request.createResponse();        

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�\��)<BR>
     * �\���̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g�����\���jsubmit�\���v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3PointApplyCompleteResponse
     * @@roseuid 419DA13A02B9
     */
    protected WEB3PointApplyCompleteResponse submitApply(WEB3PointApplyCompleteRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitApply(WEB3PointApplyCompleteRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();

        //1.2 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3 get�،���ЃR�[�h( )
        String l_strInstitutionCode = this.getInstitutionCode();
        
        //1.4 get���X�R�[�h( )
        String l_strBranchCode = this.getBranchCode();
        
        //1.5 get�ڋq�R�[�h( )
        String l_strAccountCode = this.getAccountCode();
        
        //1.6 validate�\���\(String, String, String, String)
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        l_applyManager.validateApplyPossible(l_strInstitutionCode, l_strBranchCode, l_strAccountCode, l_request.premiumNo);
        
        //1.7 get�⏕����( )
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.8 get�㗝���͎�( )
        Trader l_trader = this.getTrader();
        
        //1.9 validate����p�X���[�h(Trader, SubAccount, String)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3GentradeOrderValidator l_orderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        if (l_orderValidator == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        OrderValidationResult l_validationResult = l_orderValidator.validateTradingPassword(l_trader, l_subAccount, l_request.password);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����p�X���[�h���s���ł��B");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                getClass().getName() + STR_METHOD_NAME,
                l_validationResult.getProcessingResult().getErrorInfo().error_message);
        }

        //1.10 �|�C���g�\��(String, String, String, String, long)
        PointPremiumMasterRow l_premiumRow = null;
        try
        {
            l_premiumRow = PointPremiumMasterDao.findRowByPk(
                l_strInstitutionCode,
                l_request.premiumNo);//DataFindException,DataQueryException,DataNetworkException
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
        
        if (l_premiumRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3PointApply l_apply = new WEB3PointApply(
            l_strInstitutionCode,  
            l_strBranchCode,  
            l_strAccountCode,  
            l_request.premiumNo,  
            l_premiumRow.getRequiredPoint());
        
        //1.11 saveNew�\��(�|�C���g�\��, ����)
        l_applyManager.saveNewApply(l_apply, l_trader);
        
        //1.12 createResponse( )
        WEB3PointApplyCompleteResponse l_response = (WEB3PointApplyCompleteResponse)l_request.createResponse();
        
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //1.13 (*)�v���p�e�B�Z�b�g
        //�X�V����
        l_response.updateTimeStamp = l_apply.getApplyTimestamp();
        //�\��ID
        l_response.applyId = WEB3StringTypeUtility.formatNumber(l_apply.getApplyId());
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
