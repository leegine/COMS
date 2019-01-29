head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointExchangeApplyAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g������t�T�[�r�XImpl(WEB3AdminPointExchangeApplyAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
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
import webbroker3.point.WEB3AdminPointAcceptDivComparator;
import webbroker3.point.WEB3AdminPointAcceptUserComparator;
import webbroker3.point.WEB3AdminPointAccountCodeComparator;
import webbroker3.point.WEB3AdminPointApplyTimestampComparator;
import webbroker3.point.WEB3AdminPointBranchCodeComparator;
import webbroker3.point.WEB3AdminPointCancelDivComparator;
import webbroker3.point.WEB3AdminPointLastUpdatedTimestampComparator;
import webbroker3.point.WEB3AdminPointPremiumNameComparator;
import webbroker3.point.WEB3AdminPointPremiumNoComparator;
import webbroker3.point.WEB3PointApply;
import webbroker3.point.WEB3PointApplyManager;
import webbroker3.point.WEB3PointPremium;
import webbroker3.point.WEB3PointProductManager;
import webbroker3.point.data.PointApplyRow;
import webbroker3.point.define.WEB3AcceptDivDef;
import webbroker3.point.define.WEB3PointKeyItemDef;
import webbroker3.point.message.WEB3AdminPointApplyDetail;
import webbroker3.point.message.WEB3AdminPointExchangeAcceptRequest;
import webbroker3.point.message.WEB3AdminPointExchangeAcceptResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelCompleteRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelCompleteResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelConfirmRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelConfirmResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseCompleteRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseCompleteResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseConfirmRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseConfirmResponse;
import webbroker3.point.message.WEB3AdminPointExchangeStateReferenceRequest;
import webbroker3.point.message.WEB3AdminPointExchangeStateReferenceResponse;
import webbroker3.point.message.WEB3PointSortKey;
import webbroker3.point.service.delegate.WEB3AdminPointExchangeApplyAcceptService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�|�C���g������t�T�[�r�XImpl)<BR>
 * �|�C���g������t�T�[�r�X�����N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointExchangeApplyAcceptServiceImpl implements WEB3AdminPointExchangeApplyAcceptService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminPointExchangeApplyAcceptServiceImpl.class);

    /**
     * �|�C���g������t�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * <BR>
     *    get�ꗗ���()<BR>
     *    submit��t()<BR>
     *    validate���()<BR>
     *    submit���()<BR>
     *    validate�������()<BR>
     *    submit�������()<BR>
     * <BR>
     * ��L���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 419B0A74012F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request instanceof WEB3AdminPointExchangeStateReferenceRequest)
        {
            WEB3AdminPointExchangeStateReferenceResponse l_response = 
                this.getReferenceScreen((WEB3AdminPointExchangeStateReferenceRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointExchangeAcceptRequest)
        {
            WEB3AdminPointExchangeAcceptResponse l_response = 
                this.submitAccept((WEB3AdminPointExchangeAcceptRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointExchangeCancelConfirmRequest)
        {
            WEB3AdminPointExchangeCancelConfirmResponse l_response = 
                this.validateCancel((WEB3AdminPointExchangeCancelConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointExchangeCancelCompleteRequest)
        {
            WEB3AdminPointExchangeCancelCompleteResponse l_response = 
                this.submitCancel((WEB3AdminPointExchangeCancelCompleteRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointExchangeCancelReleaseConfirmRequest)
        {
            WEB3AdminPointExchangeCancelReleaseConfirmResponse l_response = 
                this.validateCancelRelease((WEB3AdminPointExchangeCancelReleaseConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointExchangeCancelReleaseCompleteRequest)
        {
            WEB3AdminPointExchangeCancelReleaseCompleteResponse l_response = 
                this.submitCancelRelease((WEB3AdminPointExchangeCancelReleaseCompleteRequest)l_request);
        
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
     * (get�ꗗ���)<BR>
     * �ꗗ��ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g������t�jget�ꗗ��ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeStateReferenceResponse
     * @@roseuid 419B0A74014F
     */
    protected WEB3AdminPointExchangeStateReferenceResponse getReferenceScreen(
        WEB3AdminPointExchangeStateReferenceRequest l_request)
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminPointExchangeStateReferenceRequest )";
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
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_EXCHANGE, true);//WEB3BaseException
        
        //1.4 validate���X����(String[])
        l_admin.validateBranchPermission(l_request.branchCode);//WEB3BaseException
        
        //1.5 create�擾����������(String[], String)
        String l_strWhere = this.createGetString(l_request.branchCode, l_request.acceptDiv);
        
        //1.6 create�擾�����f�[�^�R���e�i(String, String[], String)
        Object[] l_objBinds = this.createGetContainer(l_admin.getInstitutionCode(), l_request.branchCode, l_request.acceptDiv);
        
        List l_lisApply = null;
        int l_intApplyCount = 0;
        ArrayList l_arrayListApply = null;
        try
        {
            //1.7 getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataQueryException, DataNetworkException
            
            //1.8 doFindAllQuery(RowType, String, String, String, Object[], int, int)
            if (l_queryProcessor == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_lisApply = l_queryProcessor.doFindAllQuery(
                PointApplyRow.TYPE,
                l_strWhere,
                l_objBinds);
            //1.9 ArrayList( )
            l_arrayListApply = new ArrayList();
                
            //1.10 (*1)�擾�������R�[�h����Loop����
            if (l_lisApply != null)
            {
                l_intApplyCount = l_lisApply.size();
            }
            WEB3PointProductManager l_productManager = (WEB3PointProductManager)Services.getService(WEB3PointProductManager.class);
            if (l_productManager == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
    
            for (int i = 0; i < l_intApplyCount; i++)
            {
                PointApplyRow l_applyRow = (PointApplyRow)l_lisApply.get(i);
                if (l_applyRow == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                //1.10.1 get�i�i(String, String)
                WEB3PointPremium l_premium = l_productManager.getPremium(l_applyRow.getInstitutionCode(), l_applyRow.getPremiumNo());//WEB3BaseException
                        
                //1.10. 2�|�C���g�\������( )
                WEB3AdminPointApplyDetail l_applyDetail = new WEB3AdminPointApplyDetail();
                    
                Timestamp l_tsApplyAcceptTime = l_applyRow.getApplyAcceptTimestamp();
                Timestamp l_tsApplyCancelTime = l_applyRow.getApplyCancelTimestamp();
                //1.10.3 (*2)�v���p�e�B�Z�b�g
                l_applyDetail.applyId = WEB3StringTypeUtility.formatNumber(l_applyRow.getApplyId());
                l_applyDetail.branchCode = l_applyRow.getBranchCode();
                l_applyDetail.accountCode = l_applyRow.getAccountCode().substring(0, 6);
                l_applyDetail.premiumNo = l_applyRow.getPremiumNo();
                if (l_premium != null)
                {
                    l_applyDetail.premiumName = l_premium.getPremiumName();
                }
                else
                {
                    l_applyDetail.premiumName = null;
                }
                l_applyDetail.applyDate = l_applyRow.getApplyTimestamp();
                l_applyDetail.acceptDiv = l_applyRow.getApplyAcceptDiv();
                if (l_tsApplyAcceptTime == null && l_tsApplyCancelTime == null)
                {
                    l_applyDetail.updateTimeStamp = null;
                }
                else if (l_tsApplyAcceptTime != null && l_tsApplyCancelTime == null)
                {
                    l_applyDetail.updateTimeStamp = l_tsApplyAcceptTime;
                }
                else if (l_tsApplyAcceptTime == null && l_tsApplyCancelTime != null)
                {
                    l_applyDetail.updateTimeStamp = l_tsApplyCancelTime;
                }
                else if (l_tsApplyAcceptTime != null && l_tsApplyCancelTime != null)
                {
                    if (WEB3DateUtility.compareToSecond(l_tsApplyAcceptTime, l_tsApplyCancelTime) > 0)
                    {
                        l_applyDetail.updateTimeStamp = l_tsApplyAcceptTime;
                    }
                    else
                    {
                        l_applyDetail.updateTimeStamp = l_tsApplyCancelTime;
                    }
                }
                    
                if (l_applyDetail.updateTimeStamp == null)
                {
                    l_applyDetail.acceptUser = null;
                }
                else if (WEB3DateUtility.compareToSecond(l_applyDetail.updateTimeStamp, l_tsApplyAcceptTime) == 0)
                {
                    l_applyDetail.acceptUser = l_applyRow.getApplyAcceptUser();
                }
                else
                {
                    l_applyDetail.acceptUser = l_applyRow.getApplyCancelUser();
                }                
                l_applyDetail.cancelDiv = l_applyRow.getApplyCancelDiv();
                    
                //1.10.4 add(arg0 : Object)
                l_arrayListApply.add(l_applyDetail);
            }
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

        //1.11 toArray( )
        WEB3AdminPointApplyDetail[] l_applyDetails = new WEB3AdminPointApplyDetail[l_arrayListApply.size()];
        l_arrayListApply.toArray(l_applyDetails);
            
        //1.12 sort�|�C���g�\������(�|�C���g�\������[], �|�C���g�����ꗗ�\�[�g�L�[[])
        WEB3AdminPointApplyDetail[] l_applyDetails_sorted  = this.sortPointApplyDetail(l_applyDetails, l_request.sortKeys);
            
        //1.13 get�\������(�|�C���g�\������[], int, int)
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_applyDetails_sorted,
            l_intPageIndex, 
            l_intPageSize);
        //1.14 createResponse( )
        WEB3AdminPointExchangeStateReferenceResponse l_response = (WEB3AdminPointExchangeStateReferenceResponse)l_request.createResponse();  
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.15 (*4)�v���p�e�B�Z�b�g
        WEB3AdminPointApplyDetail[] l_indicationDetail = (WEB3AdminPointApplyDetail[])l_pageIndexInfo.getArrayReturned(WEB3AdminPointApplyDetail.class); 
        l_response.applyList = l_indicationDetail;
        l_response.pageIndex = WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getPageIndex());
        if (l_pageIndexInfo.getTotalRecords() == 0)
        {
            l_response.pageIndex = "0";
        }
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getTotalPages());
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getTotalRecords());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit��t)<BR>
     * ��t�̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g������t�jsubmit��t�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeAcceptResponse
     * @@roseuid 419B0BED0391
     */
    protected WEB3AdminPointExchangeAcceptResponse submitAccept(
        WEB3AdminPointExchangeAcceptRequest l_request)
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " submitAccept(WEB3AdminPointExchangeAcceptRequest )";
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
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_EXCHANGE, true);//WEB3BaseException
        
        //1.4 validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException
        
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        //1.5 ���N�G�X�g�f�[�^.�\��ID�̊e�v�f����Loop����
        if (l_request.applyId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        int l_intApplyIdCounnt = l_request.applyId.length;
        for (int i = 0; i < l_intApplyIdCounnt; i++)
        {
            //1.5.1 get�\��(long)
            if (l_request.applyId[i] == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            if (!WEB3StringTypeUtility.isDigit(l_request.applyId[i]))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01885,
                    getClass().getName() + STR_METHOD_NAME);
            }
            WEB3PointApply l_apply = l_applyManager.getApply(Long.parseLong(l_request.applyId[i]));//WEB3BaseException
                        
            //1.5.2 validate���X����(String)
            if (l_apply == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_admin.validateBranchPermission(l_apply.getBranchCode());//WEB3BaseException
            
            //1.5.3 save�\����t(�|�C���g�\��, �Ǘ���)
            l_applyManager.saveApplyAccept(l_apply, l_admin);//WEB3BaseException
        }
        
        //1.6 createResponse( )
        WEB3AdminPointExchangeAcceptResponse l_response = (WEB3AdminPointExchangeAcceptResponse)l_request.createResponse();  
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * ����̐R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g������t�jvalidate����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeCancelConfirmResponse
     * @@roseuid 419B0A74016E
     */
    protected WEB3AdminPointExchangeCancelConfirmResponse validateCancel(
        WEB3AdminPointExchangeCancelConfirmRequest l_request)
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " validateCancel(WEB3AdminPointExchangeCancelConfirmRequest )";
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
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_EXCHANGE, true);//WEB3BaseException
        
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        
        //1.4 get�\��(long)
        if (l_request.applyId == null)
        {
            String l_strMessage = "�\��IDerror! " + l_request.applyId;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01732,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        if (!WEB3StringTypeUtility.isDigit(l_request.applyId))
        {
            String l_strMessage = "�\��IDerror! " + l_request.applyId;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01885,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }

        WEB3PointApply l_apply = l_applyManager.getApply(Long.parseLong(l_request.applyId));//WEB3BaseException
        if (l_apply == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        PointApplyRow l_applyRow = (PointApplyRow)l_apply.getDataSourceObject();
        
        //1.5 validate���X����(String)
        l_admin.validateBranchPermission(l_apply.getBranchCode());//WEB3BaseException
        
        WEB3PointProductManager l_productManager = (WEB3PointProductManager)Services.getService(WEB3PointProductManager.class);
        if (l_productManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.6 get�i�i(String, String)
        WEB3PointPremium l_premium = l_productManager.getPremium(l_admin.getInstitutionCode(), l_apply.getPremiumNo());//WEB3BaseException
        
        //1.7 �|�C���g�\������( )
        WEB3AdminPointApplyDetail l_applyDetail = new WEB3AdminPointApplyDetail();

        //1.8 �v���p�e�B�Z�b�g
        if (l_applyRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        Timestamp l_tsApplyAcceptTime = l_applyRow.getApplyAcceptTimestamp();
        Timestamp l_tsApplyCancelTime = l_applyRow.getApplyCancelTimestamp();

        l_applyDetail.applyId = WEB3StringTypeUtility.formatNumber(l_applyRow.getApplyId());
        l_applyDetail.branchCode = l_applyRow.getBranchCode();
        l_applyDetail.accountCode = l_applyRow.getAccountCode().substring(0, 6);
        l_applyDetail.premiumNo = l_applyRow.getPremiumNo();
        if (l_premium != null)
        {
            l_applyDetail.premiumName = l_premium.getPremiumName();
        }
        else
        {
            l_applyDetail.premiumName = null;
        }
        l_applyDetail.applyDate = l_applyRow.getApplyTimestamp();
        l_applyDetail.acceptDiv = l_applyRow.getApplyAcceptDiv();
        if (l_tsApplyAcceptTime == null && l_tsApplyCancelTime == null)
        {
            l_applyDetail.updateTimeStamp = null;
        }
        else if (l_tsApplyAcceptTime != null && l_tsApplyCancelTime == null)
        {
            l_applyDetail.updateTimeStamp = l_tsApplyAcceptTime;
        }
        else if (l_tsApplyAcceptTime == null && l_tsApplyCancelTime != null)
        {
            l_applyDetail.updateTimeStamp = l_tsApplyCancelTime;
        }
        else if (l_tsApplyAcceptTime != null && l_tsApplyCancelTime != null)
        {
            if (WEB3DateUtility.compareToSecond(l_tsApplyAcceptTime, l_tsApplyCancelTime) > 0)
            {
                l_applyDetail.updateTimeStamp = l_tsApplyAcceptTime;
            }
            else
            {
                l_applyDetail.updateTimeStamp = l_tsApplyCancelTime;
            }
        }
                
        if (l_applyDetail.updateTimeStamp == null)
        {
            l_applyDetail.acceptUser = null;
        }
        else if (WEB3DateUtility.compareToSecond(l_applyDetail.updateTimeStamp, l_tsApplyAcceptTime) == 0)
        {
            l_applyDetail.acceptUser = l_applyRow.getApplyAcceptUser();
        }
        else
        {
            l_applyDetail.acceptUser = l_applyRow.getApplyCancelUser();
        }
        l_applyDetail.cancelDiv = l_applyRow.getApplyCancelDiv();
        
        //1.9 createResponse( )
        WEB3AdminPointExchangeCancelConfirmResponse l_response = (WEB3AdminPointExchangeCancelConfirmResponse)l_request.createResponse();  
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.10 �v���p�e�B�Z�b�g                
        l_response.applyDetail = l_applyDetail;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit���)<BR>
     * ����̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g������t�jsubmit����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeCancelCompleteResponse
     * @@roseuid 419B0A74017D
     */
    protected WEB3AdminPointExchangeCancelCompleteResponse submitCancel(
        WEB3AdminPointExchangeCancelCompleteRequest l_request)
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " submitCancel(WEB3AdminPointExchangeCancelCompleteRequest )";
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
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_EXCHANGE, true);//WEB3BaseException
        
        //1.4 validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException

        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.5 get�\��(long)
        WEB3PointApply l_apply = l_applyManager.getApply(Long.parseLong(l_request.applyId));//WEB3BaseException
        if (l_apply == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.6 validate���X����(String)
        l_admin.validateBranchPermission(l_apply.getBranchCode());//WEB3BaseException
        
        //1.7 save�\�����(�|�C���g�\��, �Ǘ���)
        l_applyManager.saveApplyCancel(l_apply, l_admin);//WEB3BaseException
        
        //1.8 createResponse( )
        WEB3AdminPointExchangeCancelCompleteResponse l_response = (WEB3AdminPointExchangeCancelCompleteResponse)l_request.createResponse();  

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�������)<BR>
     * ��������̐R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g������t�jvalidate��������v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseConfirmResponse
     * @@roseuid 419B0B9E0314
     */
    protected WEB3AdminPointExchangeCancelReleaseConfirmResponse validateCancelRelease(
        WEB3AdminPointExchangeCancelReleaseConfirmRequest l_request)
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " validateCancelRelease(WEB3AdminPointExchangeCancelReleaseConfirmRequest )";
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
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_EXCHANGE, true);//WEB3BaseException
        
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.5 get�\��(long)
        WEB3PointApply l_apply = l_applyManager.getApply(Long.parseLong(l_request.applyId));//WEB3BaseException
        if (l_apply == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.5 validate���X����(String)
        l_admin.validateBranchPermission(l_apply.getBranchCode());//WEB3BaseException
        
        //1.6 validate�|�C���g�]��(�|�C���g�\��)
        l_applyManager.validatePointPower(l_apply);//WEB3BaseException
        
        //1.7 get�i�i(String, String)
        WEB3PointProductManager l_productManager = (WEB3PointProductManager)Services.getService(WEB3PointProductManager.class);
        if (l_productManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3PointPremium l_premium = l_productManager.getPremium(l_admin.getInstitutionCode(), l_apply.getPremiumNo());//WEB3BaseException
        
        //1.8 �|�C���g�\������( )
        WEB3AdminPointApplyDetail l_applyDetail = new WEB3AdminPointApplyDetail();

        //1.9 �v���p�e�B�Z�b�g
        PointApplyRow l_applyRow = (PointApplyRow)l_apply.getDataSourceObject();
        if (l_applyRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        Timestamp l_tsApplyAcceptTime = l_applyRow.getApplyAcceptTimestamp();
        Timestamp l_tsApplyCancelTime = l_applyRow.getApplyCancelTimestamp();

        l_applyDetail.applyId = WEB3StringTypeUtility.formatNumber(l_applyRow.getApplyId());
        l_applyDetail.branchCode = l_applyRow.getBranchCode();
        l_applyDetail.accountCode = l_applyRow.getAccountCode().substring(0, 6);
        l_applyDetail.premiumNo = l_applyRow.getPremiumNo();
        if (l_premium != null)
        {
            l_applyDetail.premiumName = l_premium.getPremiumName();
        }
        else
        {
            l_applyDetail.premiumName = null;
        }
        l_applyDetail.applyDate = l_applyRow.getApplyTimestamp();
        l_applyDetail.acceptDiv = l_applyRow.getApplyAcceptDiv();
        if (l_tsApplyAcceptTime == null && l_tsApplyCancelTime == null)
        {
            l_applyDetail.updateTimeStamp = null;
        }
        else if (l_tsApplyAcceptTime != null && l_tsApplyCancelTime == null)
        {
            l_applyDetail.updateTimeStamp = l_tsApplyAcceptTime;
        }
        else if (l_tsApplyAcceptTime == null && l_tsApplyCancelTime != null)
        {
            l_applyDetail.updateTimeStamp = l_tsApplyCancelTime;
        }
        else if (l_tsApplyAcceptTime != null && l_tsApplyCancelTime != null)
        {
            if (WEB3DateUtility.compareToSecond(l_tsApplyAcceptTime, l_tsApplyCancelTime) > 0)
            {
                l_applyDetail.updateTimeStamp = l_tsApplyAcceptTime;
            }
            else
            {
                l_applyDetail.updateTimeStamp = l_tsApplyCancelTime;
            }
        }
                
        if (l_applyDetail.updateTimeStamp == null)
        {
            l_applyDetail.acceptUser = null;
        }
        else if (WEB3DateUtility.compareToSecond(l_applyDetail.updateTimeStamp, l_tsApplyAcceptTime) == 0)
        {
            l_applyDetail.acceptUser = l_applyRow.getApplyAcceptUser();
        }
        else
        {
            l_applyDetail.acceptUser = l_applyRow.getApplyCancelUser();
        }
        l_applyDetail.cancelDiv = l_applyRow.getApplyCancelDiv();
        
        //1.10 createResponse( )
        WEB3AdminPointExchangeCancelReleaseConfirmResponse l_response = (WEB3AdminPointExchangeCancelReleaseConfirmResponse)l_request.createResponse();  
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.11 �v���p�e�B�Z�b�g                
        l_response.applyDetail = l_applyDetail;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�������)<BR>
     * ��������̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�C���g������t�jsubmit��������v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseCompleteResponse
     * @@roseuid 419B0B9E0343
     */
    protected WEB3AdminPointExchangeCancelReleaseCompleteResponse submitCancelRelease(
        WEB3AdminPointExchangeCancelReleaseCompleteRequest l_request)
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " submitCancelRelease(WEB3AdminPointExchangeCancelReleaseCompleteRequest )";
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
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_EXCHANGE, true);//WEB3BaseException
        
        //1.4 validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException

        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        //1.5 get�\��(long)
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3PointApply l_apply = l_applyManager.getApply(Long.parseLong(l_request.applyId));//WEB3BaseException
        if (l_apply == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.6 validate���X����(String)
        l_admin.validateBranchPermission(l_apply.getBranchCode());//WEB3BaseException
        
        //1.7 validate�|�C���g�]��(�|�C���g�\��)
        l_applyManager.validatePointPower(l_apply);//WEB3BaseException
        
        //1.8 save�\���������(�|�C���g�\��, �Ǘ���)
        l_applyManager.saveApplyCancelRelease(l_apply, l_admin);//WEB3BaseException

        //1.9 createResponse( )
        WEB3AdminPointExchangeCancelReleaseCompleteResponse l_response = (WEB3AdminPointExchangeCancelReleaseCompleteResponse)l_request.createResponse();  

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create�擾����������)<BR>
     * ���N�G�X�g�f�[�^����A�f�[�^�擾����������𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * <BR>
     * ����������F "institution_code=?"<BR>
     * <BR>
     * ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h<BR>
     * <BR>
     * �R�|�P�j����.���X�R�[�h�̗v�f�� = 1 �̏ꍇ<BR>
     * <BR>
     * ����������F " and branch_code=?"<BR>
     * <BR>
     * ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �R�|�Q�j����.���X�R�[�h�̗v�f�� > 1 �̏ꍇ<BR>
     * <BR>
     * ����������F " and (branch_code=? or branch_code=? <BR>
     * or ... or branch_code=?)"<BR>
     * �����ʓ��́ubranch_code=?�v�̐����v�f��������悤�ɂ���B<BR>
     * <BR>
     * ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �S�j��t�敪<BR>
     * <BR>
     * ����.��t�敪 != 2�i���ׂāj�̏ꍇ<BR>
     * <BR>
     * ����������F " and apply_accept_div=?"<BR>
     * <BR>
     * ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �T�j�X�V����<BR>
     * <BR>
     * ����������F<BR>
     *    " and ((apply_accept_timestamp=null and apply_cancel_timestamp=null) or<BR>
     *       (apply_accept_timestamp=null and apply_cancel_timestamp>=?) or<BR>
     *       (apply_cancel_timestamp=null and apply_accept_timestamp>=?) or<BR>
     *       (apply_accept_timestamp>apply_cancel_timestamp and <BR>
     * apply_accept_timestamp>=?) or<BR>
     *       (apply_accept_timestamp<apply_cancel_timestamp and <BR>
     * apply_cancel_timestamp>=?))"<BR>
     * <BR>
     * ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �U�j���������������ԋp����B<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��<BR>
     * 
     * @@param l_strAcceptDiv - (��t�敪)<BR>
     * ��t�敪<BR>
     * 
     * @@return String
     * @@roseuid 419C0E7C01FD
     */
    protected String createGetString(String[] l_strBranchCodes, String l_strAcceptDiv) 
    {
        final String STR_METHOD_NAME = " createGetString(String, String )";
        log.entering(STR_METHOD_NAME);
        
        // �P�j��̕�����𐶐�����B
        String l_strQueryString = "";
        
        // �Q�j�،���ЃR�[�h
        l_strQueryString += " institution_code = ? ";
        
        // �R�j���X�R�[�h
        // �R�|�P�j����.���X�R�[�h�̗v�f�� = 1 �̏ꍇ
        if (l_strBranchCodes != null && l_strBranchCodes.length == 1)
        {
            l_strQueryString += " and branch_code = ? ";
        }
        //�R�|�Q�j����.���X�R�[�h�̗v�f�� > 1 �̏ꍇ
        else if (l_strBranchCodes != null && l_strBranchCodes.length != 0)
        {
            String l_strBranchCodeCondition = "";
            int l_intBranchCodeCount = l_strBranchCodes.length;
            l_strBranchCodeCondition += " and ( ";
        
            for (int i = 0; i < l_intBranchCodeCount; i++)
            {
                if ( i == 0)
                {
                    l_strBranchCodeCondition += " branch_code = ? ";
                }
                else
                {
                    l_strBranchCodeCondition += " or branch_code = ? ";
                }
            }
        
            l_strBranchCodeCondition += " ) ";
            l_strQueryString += l_strBranchCodeCondition;
        }
        
        // �S�j��t�敪
        if (!WEB3AcceptDivDef.ALL.equals(l_strAcceptDiv))
        {
            l_strQueryString += " and apply_accept_div = ? ";
        }
        
        // �T�j�X�V����
        l_strQueryString += " and ( " +
            " (apply_accept_timestamp is null and apply_cancel_timestamp is null) " +
            " or (apply_accept_timestamp is null and apply_cancel_timestamp >= ? ) " +
            " or (apply_cancel_timestamp is null and apply_accept_timestamp>=?) " +
            " or (apply_accept_timestamp > apply_cancel_timestamp and apply_accept_timestamp >=? ) " +
            " or (apply_accept_timestamp < apply_cancel_timestamp and apply_cancel_timestamp >=? ) " +
            " ) ";

        log.exiting(STR_METHOD_NAME);

        // �U�j���������������ԋp����B
        return l_strQueryString;
    }
    
    /**
     * (create�擾�����f�[�^�R���e�i)<BR>
     * ���N�G�X�g�f�[�^����A�擾�����̃f�[�^���X�g�𐶐�����B <BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * <BR>
     * ����.�،���ЃR�[�h���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h<BR>
     * <BR>
     * ����.���X�R�[�h�̊e�v�f���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �S�j��t�敪<BR>
     * <BR>
     * ����.��t�敪 != 2�i���ׂāj�̏ꍇ<BR>
     * <BR>
     * ����.��t�敪���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �T�j�X�V����<BR>
     * <BR>
     * TradingSystem.getSystemTimestamp() - 7�� �̒l���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �U�j���X�g����z����擾���A�ԋp����B<BR>
     * @@param l_strInsitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��<BR>
     * 
     * @@param l_strAcceptDiv - (��t�敪)<BR>
     * ��t�敪<BR>
     * 
     * @@return Object[]
     * @@roseuid 419C21B000E4
     */
    protected Object[] createGetContainer(
        String l_strInsitutionCode, 
        String[] l_strBranchCodes, 
        String l_strAcceptDiv) 
    {
        final String STR_METHOD_NAME = " createGetContainer(String, String[], String )";
        log.entering(STR_METHOD_NAME);
        
        // �P�j���ArrayList�𐶐�����B 
        ArrayList l_arrayList = new ArrayList();
        
        // �Q�j�،���ЃR�[�h
        l_arrayList.add(l_strInsitutionCode);
        
        // �R�j���X�R�[�h
        int l_intBranchCodeCount = 0;
        if (l_strBranchCodes != null)
        {
            l_intBranchCodeCount = l_strBranchCodes.length;
        }
        for (int i = 0; i < l_intBranchCodeCount; i++)
        {
            l_arrayList.add(l_strBranchCodes[i]);
        }

        // �S�j��t�敪
        if (!WEB3AcceptDivDef.ALL.equals(l_strAcceptDiv))
        {
            l_arrayList.add(l_strAcceptDiv);
        }

        // �T�j�X�V����
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        Calendar l_cal = new GregorianCalendar();            
        if (l_tsSystemTime == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_cal.setTime(l_tsSystemTime);            
        l_cal.add(Calendar.DAY_OF_MONTH, -7);            
        Timestamp l_tsDate = new Timestamp(l_cal.getTimeInMillis());
        
        l_arrayList.add(l_tsDate);
        l_arrayList.add(l_tsDate);
        l_arrayList.add(l_tsDate);
        l_arrayList.add(l_tsDate);

        // �U�j���X�g����z����擾���A�ԋp����B
        Object[] l_queryContainers = new Object[l_arrayList.size()];
        l_arrayList.toArray(l_queryContainers); 

        log.exiting(STR_METHOD_NAME);
        return l_queryContainers;
    }
    
    /**
     * (sort�|�C���g�\������)<BR>
     * �|�C���g�\�����ׂ̔z����A���N�G�X�g�f�[�^.�\�[�g�L�[�ɏ]���\�[�g����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j����.�\�[�g�L�[�̊e�v�f�ɂ��ď�������B<BR>
     * <BR>
     * �Q�|�P�j�\�[�g�L�[.�L�[����=�h���X�R�[�h�h�̏ꍇ<BR>
     * <BR>
     *    ���X�R�[�hComparator�C���X�^���X�𐶐����A�P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     *    [�R���X�g���N�^�ɃZ�b�g�������]<BR>
     *    orderBy�F �\�[�g�L�[.����/�~��<BR>
     * <BR>
     * �Q�|�Q�j�\�[�g�L�[.�L�[����=�h�ڋq�R�[�h�h�̏ꍇ<BR>
     * <BR>
     *    �ڋq�R�[�hComparator�C���X�^���X�𐶐����A�P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     *    [�R���X�g���N�^�ɃZ�b�g�������]<BR>
     *    orderBy�F �\�[�g�L�[.����/�~��<BR>
     * <BR>
     * �Q�|�R�j�\�[�g�L�[.�L�[����=�h�i�i�ԍ��h�̏ꍇ<BR>
     * <BR>
     *    �i�i�ԍ�Comparator�C���X�^���X�𐶐����A�P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     *    [�R���X�g���N�^�ɃZ�b�g�������]<BR>
     *    orderBy�F �\�[�g�L�[.����/�~��<BR>
     * <BR>
     * �Q�|�S�j�\�[�g�L�[.�L�[����=�h�i�i���h�̏ꍇ<BR>
     * <BR>
     *    �i�i��Comparator�C���X�^���X�𐶐����A�P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     *    [�R���X�g���N�^�ɃZ�b�g�������]<BR>
     *    orderBy�F �\�[�g�L�[.����/�~��<BR>
     * <BR>
     * �Q�|�T�j�\�[�g�L�[.�L�[����=�h�\�������h�̏ꍇ<BR>
     * <BR>
     *    �\������Comparator�C���X�^���X�𐶐����A�P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     *    [�R���X�g���N�^�ɃZ�b�g�������]<BR>
     *    orderBy�F �\�[�g�L�[.����/�~��<BR>
     * <BR>
     * �Q�|�U�j�\�[�g�L�[.�L�[����=�h��t�敪�h�̏ꍇ<BR>
     * <BR>
     *    ��t�敪Comparator�C���X�^���X�𐶐����A�P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     *    [�R���X�g���N�^�ɃZ�b�g�������]<BR>
     *    orderBy�F �\�[�g�L�[.����/�~��<BR>
     * <BR>
     * �Q�|�V�j�\�[�g�L�[.�L�[����=�h�X�V�����h�̏ꍇ<BR>
     * <BR>
     *    �X�V����Comparator�C���X�^���X�𐶐����A�P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     *    [�R���X�g���N�^�ɃZ�b�g�������]<BR>
     *    orderBy�F �\�[�g�L�[.����/�~��<BR>
     * <BR>
     * �Q�|�W�j�\�[�g�L�[.�L�[����=�h��t���[�U�h�̏ꍇ<BR>
     * <BR>
     *    ��t���[�UComparator�C���X�^���X�𐶐����A�P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     *    [�R���X�g���N�^�ɃZ�b�g�������]<BR>
     *    orderBy�F �\�[�g�L�[.����/�~��<BR>
     * <BR>
     * �Q�|�X�j�\�[�g�L�[.�L�[����=�h����敪�h�̏ꍇ<BR>
     * <BR>
     *    ����敪Comparator�C���X�^���X�𐶐����A�P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     *    [�R���X�g���N�^�ɃZ�b�g�������]<BR>
     *    orderBy�F �\�[�g�L�[.����/�~��<BR>
     * <BR>
     * �R�jArrayList����Comparator�̔z����擾����B <BR>
     * <BR>
     * �S�jComparator�̔z�񏇂̃\�[�g�������s���B<BR>
     * <BR>
     *    WEB3ArraysUtility.sort(obj, com)<BR>
     * <BR>
     *    [sort�ɃZ�b�g�������]<BR>
     *    obj�F ����.����<BR>
     *    com�F �R�j�Ŏ擾�����z��<BR>
     * <BR>
     * �T�j�\�[�g���ꂽ���ׂ̔z���ԋp����B<BR>
     * @@param l_pointApplyDetails - (����)<BR>
     * �|�C���g�\�����ׂ̔z��<BR>
     * 
     * @@param l_pointSortKeys - (�\�[�g�L�[)<BR>
     * �|�C���g�����ꗗ�\�[�g�L�[�̔z��<BR>
     * 
     * @@return WEB3AdminPointApplyDetail[]
     * @@roseuid 419C29C70180
     */
    protected WEB3AdminPointApplyDetail[] sortPointApplyDetail(
        WEB3AdminPointApplyDetail[] l_pointApplyDetails, 
        WEB3PointSortKey[] l_pointSortKeys) 
    {
        final String STR_METHOD_NAME = " sortPointApplyDetail(WEB3AdminPointApplyDetail[], WEB3PointSortKey[] )";
        log.entering(STR_METHOD_NAME);
        
        // �P�j���ArrayList�𐶐�����B
        ArrayList l_arrayList = new ArrayList();
        
        // �Q�j����.�\�[�g�L�[�̊e�v�f�ɂ��ď�������B
        int l_intSortKeyCount = 0;
        if (l_pointSortKeys != null)
        {
            l_intSortKeyCount = l_pointSortKeys.length;
        }
        for (int i = 0; i < l_intSortKeyCount; i++)
        {
            if (l_pointSortKeys[i] == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            String l_strKeyItem = l_pointSortKeys[i].keyItem;
            String l_strAscDesc = l_pointSortKeys[i].ascDesc;
            Comparator l_com = null;

            // �Q�|�P�j�\�[�g�L�[.�L�[����=�h���X�R�[�h�h�̏ꍇ
            if (WEB3PointKeyItemDef.BRANCH_CODE.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointBranchCodeComparator(l_strAscDesc);
            }            
            // �Q�|�Q�j�\�[�g�L�[.�L�[����=�h�ڋq�R�[�h�h�̏ꍇ
            else if (WEB3PointKeyItemDef.ACCOUNT_CODE.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointAccountCodeComparator(l_strAscDesc);
            }
            // �Q�|�R�j�\�[�g�L�[.�L�[����=�h�i�i�ԍ��h�̏ꍇ
            else if (WEB3PointKeyItemDef.PREMINUM_NO.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointPremiumNoComparator(l_strAscDesc);
            }
            // �Q�|�S�j�\�[�g�L�[.�L�[����=�h�i�i���h�̏ꍇ
            else if (WEB3PointKeyItemDef.PREMIUM_NAME.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointPremiumNameComparator(l_strAscDesc);
            }
            // �Q�|�T�j�\�[�g�L�[.�L�[����=�h�\�������h�̏ꍇ
            else if (WEB3PointKeyItemDef.APPLY_TIMESTAMP.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointApplyTimestampComparator(l_strAscDesc);
            }
            // �Q�|�U�j�\�[�g�L�[.�L�[����=�h��t�敪�h�̏ꍇ
            else if (WEB3PointKeyItemDef.APPLY_ACCEPT_DIV.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointAcceptDivComparator(l_strAscDesc);
            }
            // �Q�|�V�j�\�[�g�L�[.�L�[����=�h�X�V�����h�̏ꍇ
            else if (WEB3PointKeyItemDef.LAST_UPDATED_TIMESTAMP.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointLastUpdatedTimestampComparator(l_strAscDesc);
            }
            // �Q�|�W�j�\�[�g�L�[.�L�[����=�h��t���[�U�h�̏ꍇ
            else if (WEB3PointKeyItemDef.APPLY_ACCEPT_USER.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointAcceptUserComparator(l_strAscDesc);
            }
            // �Q�|�X�j�\�[�g�L�[.�L�[����=�h����敪�h�̏ꍇ
            else if (WEB3PointKeyItemDef.APPLY_CANCEL_DIV.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointCancelDivComparator(l_strAscDesc);
            }

            if (l_com != null)
            {
                l_arrayList.add(l_com);
            }
        }
        // �R�jArrayList����Comparator�̔z����擾����B 
        Comparator[] l_comparators = new Comparator[l_arrayList.size()];
        l_arrayList.toArray(l_comparators);
        
        // �S�jComparator�̔z�񏇂̃\�[�g�������s���B
        WEB3ArraysUtility.sort(l_pointApplyDetails, l_comparators);

        log.exiting(STR_METHOD_NAME);
        return l_pointApplyDetails;
    }
    
}
@
