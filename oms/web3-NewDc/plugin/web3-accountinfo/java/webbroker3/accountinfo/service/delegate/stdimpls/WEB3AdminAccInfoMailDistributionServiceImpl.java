head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailDistributionServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�܂����ē����[���z�M�w���T�[�r�XImpl(WEB3AdminAccInfoMailDistributionServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 ���]�� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoMailDistribution;
import webbroker3.accountinfo.data.InformationMailRequestPK;
import webbroker3.accountinfo.data.InformationMailRequestRow;
import webbroker3.accountinfo.message.WEB3AccInfoMailDistributionInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailDistributionService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�܂����ē����[���z�M�w���T�[�r�XImpl)<BR>
 * �Ǘ��҂��q�܂����ē����[���z�M�w���T�[�r�X�����N���X<BR>
 * @@author ���]��
 * @@version 1.0 
 */

public class WEB3AdminAccInfoMailDistributionServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoMailDistributionService
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailDistributionServiceImpl.class);
        
    public WEB3AdminAccInfoMailDistributionServiceImpl()
    {
        
    }
    /**
     * �ē����[���z�M�w�����������{����B<BR>
     *<BR>
     *�P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     *<BR>
     *�� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ē����[���z�M�w�����N�G�X�g�̏ꍇ<BR> 
�@@   * �|get�z�M�w�����()���R�[������B <BR>
     *<BR>
     *�� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ē����[���z�M�w���m�F���N�G�X�g�̏ꍇ<BR>
�@@   * �|validate�z�M�w��()���R�[������B<BR> 
     *<BR>
     *�� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ē����[���z�M�w���������N�G�X�g�̏ꍇ<BR>
�@@   * �|submit�z�M�w��()���R�[������B<BR>
     *<BR>
     *�� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ē����[���z�M�w������m�F���N�G�X�g�̏ꍇ<BR>
�@@   *  �|validate�z�M�w��()���R�[������B<BR>
     *<BR>
     *�� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ē����[���z�M�w������������N�G�X�g�̏ꍇ<BR>
�@@   *  �|submit�z�M�w��()���R�[������B<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ē����[���z�M�w�����N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminAccInfoMailDistributionRequest)
        {
            
            l_response = this.getMailDistributionScreen((WEB3AdminAccInfoMailDistributionRequest) l_request);
        }
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ē����[���z�M�w���m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminAccInfoMailDistributionConfirmRequest)
        {
            
            l_response = this.validateMailDistribution((WEB3AdminAccInfoMailDistributionConfirmRequest) l_request);
        }
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ē����[���z�M�w���������N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminAccInfoMailDistributionCompleteRequest)
        {
            
            l_response = this.submintMailDistribution((WEB3AdminAccInfoMailDistributionCompleteRequest) l_request);
        }
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ē����[���z�M�w������m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminAccInfoMailDistributionCancelConfirmRequest)
        {
            
            l_response = this.validateMailDistributionCancle((WEB3AdminAccInfoMailDistributionCancelConfirmRequest) l_request);
        }
        
        //���̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ē����[���z�M�w������������N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminAccInfoMailDistributionCancelCompleteRequest)
        {
            
            l_response = this.submitMailDistributionCancle((WEB3AdminAccInfoMailDistributionCancelCompleteRequest) l_request);
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
     * (get�z�M�w�����)<BR>
     * �ē����[���z�M�w����ʕ\���������s���B<BR> 
     *<BR>
     *�V�[�P���X�} <BR>
     *�u���q�l���i�ē����[���z�M�jget�z�M�w����ʁv�Q�ƁB<BR>
     * @@param l_request - �Ǘ��҂��q�l���ē����[���z�M�w�����N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA301D8
     */
    protected WEB3AdminAccInfoMailDistributionResponse getMailDistributionScreen(WEB3AdminAccInfoMailDistributionRequest l_request)throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " getMailDistributionScreen(WEB3AccInfoMailDistributionRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2) validate����(�@@�\�J�e�S���R�[�h�i=���[�����j : String, is�X�V�i=true�j : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        //1.3) is�S���X����( )
        boolean l_blnIsAllBranchsPermission = l_administrator.isAllBranchsPermission();
        
        //1.4) �S���X���̊Ǘ��҂łȂ��ꍇ�iis�S���X����() == false�j�A��O���X���[����
        if(!l_blnIsAllBranchsPermission)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �S���X���̊Ǘ��҂łȂ��ꍇ�́A����s��");
        }
        
        //1.5) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.6) get�ŐV����(String)
        WEB3AccInfoMailDistribution l_AccInfoMailDistribution = WEB3AccInfoMailDistribution.getLastAction(l_strInstitutionCode);
        
        //1.8)�Ǘ��҂��q�l���ē����[���z�M�w�����X�|���X(WEB3GenRequest)
        WEB3AdminAccInfoMailDistributionResponse l_response =
            (WEB3AdminAccInfoMailDistributionResponse) l_request.createResponse();
            
        //1.7)  �ē����[���z�M�w���̍ŐV�������擾�ł����ꍇ�iget�ŐV����() != null�j
        if(l_AccInfoMailDistribution != null)
        {
            //1.7.1) �ē����[���z�M�w�����( )
            WEB3AccInfoMailDistributionInfo l_AccInfoMailDistributionInfo = new WEB3AccInfoMailDistributionInfo();
            
            //1.7.2)get�ē����[���z�M�w���h�c( )
            long l_lngInformationMailRequestId = l_AccInfoMailDistribution.getInformationMailRequestId();
            
            //1.7.3)get�z�M�w������( )
            Date l_datRequestTimestamp = l_AccInfoMailDistribution.getRequestTimestamp();
            
            //1.7.4)get�z�M�ڋq��( )
            long l_lngAccountCount = l_AccInfoMailDistribution.getAccountCount();
            
            //1.7.5)is�S�ڋq( )
            boolean l_blnisAllAccountFlag = l_AccInfoMailDistribution.isAllAccount();
            
            //1.7.6)get�ē����[������( )
            String l_strMailSubject = l_AccInfoMailDistribution.getMailSubject();
            
            //1.7.7)get�X�V�҃R�[�h( )
            String l_strLastUpdater = l_AccInfoMailDistribution.getLastUpdater();
            
            //1.7.8)is�z�M��( )
            boolean l_blnisMailRequestStatus = l_AccInfoMailDistribution.isMailRequestStatus();
            
            //1.7.9)�v���p�e�B�Z�b�g
            //�@@�h�c�F�@@get�ē����[���z�M�w���h�c()
            l_AccInfoMailDistributionInfo.id = "" + l_lngInformationMailRequestId;
            
            //�z�M�w�������F�@@get�z�M�w������()
            l_AccInfoMailDistributionInfo.distributionRequestDate = l_datRequestTimestamp;
            
            //�z�M�ڋq���F�@@get�z�M�ڋq��()
            l_AccInfoMailDistributionInfo.distributionAccountNumber = "" + l_lngAccountCount;
            
            //�S�ڋq�t���O�F�@@is�S�ڋq()
            l_AccInfoMailDistributionInfo.allAccountFlag = l_blnisAllAccountFlag;
            
            //�����F�@@get�ē����[������()
            l_AccInfoMailDistributionInfo.mailSubject = l_strMailSubject;
            
            //�X�V�҃R�[�h�F�@@get�X�V�҃R�[�h()
            l_AccInfoMailDistributionInfo.updaterCode = l_strLastUpdater;
            
            //�@@�z�M�w���t���O�F�ŐV�������擾�o�����ꍇ�Ais�z�M��()�̖߂�l
            l_response.requestFlag = l_blnisMailRequestStatus;
            
            //�@@�ē����[���z�M�w�����F�ŐV�������擾�o�����ꍇ�A���������ē����[���z�M�w�����I�u�W�F�N�g
            l_response.mailDistributionInfo = l_AccInfoMailDistributionInfo;
        }
        else
        {
            //�v���p�e�B�Z�b�g 
            //�ȊO�̏ꍇ�Atrue
            l_response.requestFlag = true;
            //�ȊO�̏ꍇ�Anull
            l_response.mailDistributionInfo = null;
        }   
                    
        //�@@���M���[���敪�F�@@�ē����[���z�M�w��.�ē����[�����ʂh�c
        l_response.sendMailDiv = WEB3AccInfoMailDistribution.SENDMAILDIV;
        //���ʂh�c�F�@@�ē����[���z�M�w��.���M���[���敪
        l_response.discernId = WEB3AccInfoMailDistribution.DISCERNMENTID;
        
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    /**
     * (validate�z�M�w��)<BR>
     * �ē����[���z�M�w���m�F�������s���B<BR>
     *<BR>
     *�V�[�P���X�} <BR>
     *�u���q�l���i�ē����[���z�M�jvalidate�z�M�w���v�Q�ƁB<BR>
     * @@param l_request - :�Ǘ��҂��q�l���ē����[���z�M�w���m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA302D8 
     */
    protected WEB3AdminAccInfoMailDistributionConfirmResponse validateMailDistribution(WEB3AdminAccInfoMailDistributionConfirmRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateMailDistribution(WEB3AccInfoMailDistributionConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //validate( )
        l_request.validate();
        
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate����(�@@�\�J�e�S���R�[�h�i=���[�����j : String, is�X�V�i=true�j : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        //1.3) is�S���X����( )
        boolean l_blnAllBranchsPermission = l_administrator.isAllBranchsPermission();
        if (!l_blnAllBranchsPermission)
        {
         
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �S���X���̊Ǘ��҂łȂ��ꍇ�́A����s��");     
        }
        
        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //get�ŐV����(�،���ЃR�[�h : String)
        WEB3AccInfoMailDistribution l_strLastAction = WEB3AccInfoMailDistribution.getLastAction(l_strInstitutionCode);
        if (l_strLastAction != null)
        {
            //is�z�M��( )
            boolean l_blnMailRequestStatus = l_strLastAction.isMailRequestStatus();
            if (!l_blnMailRequestStatus)
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01381, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �ŐV�������z�M���ς̏ꍇ�́A�z�M�w���s��");                 
            }             
        }
        //calc�z�M�ڋq��(is�S�ڋq : boolean)
        long l_lngCalcAccountCount = WEB3AccInfoMailDistribution.calcAccountCount(l_strInstitutionCode, l_request.allAccountFlag);
        
        //1.8createResponse()
        WEB3AdminAccInfoMailDistributionConfirmResponse l_response = 
            (WEB3AdminAccInfoMailDistributionConfirmResponse) l_request.createResponse(); 
            
        // �z�M�ڋq���F�@@get�z�M�ڋq��() 
        l_response.distributionAccountNumber = "" + l_lngCalcAccountCount;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (submit�z�M�w��)<BR>
     * �ē����[���z�M�w�������������s���B<BR>
     *<BR>
     *�V�[�P���X�}<BR> 
     *�u���q�l���i�ē����[���z�M�jsubmit�z�M�w���v�Q�ƁB<BR>
     * @@param l_request - �Ǘ��҂��q�l���ē����[���z�M�w���������N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA303D8 
     */
    protected WEB3AdminAccInfoMailDistributionCompleteResponse submintMailDistribution(WEB3AdminAccInfoMailDistributionCompleteRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submintMailDistribution(WEB3AccInfoMailDistributionCompleteRequest)";
        log.entering(STR_METHOD_NAME );
        
        //1.1)validate()
        l_request.validate();
        
        //1.2getInstanceFrom���O�C�����( )
        
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
       
        //1.3validate����(String, boolean)        
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        //1.4is�S���X����( )        
        boolean l_blnAllBranchsPermission = l_administrator.isAllBranchsPermission();
        
        //1.5(*) �S���X���̊Ǘ��҂łȂ��ꍇ�iis�S���X����() == false�j�A��O���X���[����B
        if (!l_blnAllBranchsPermission)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �S���X���̊Ǘ��҂łȂ��ꍇ�́A����s��");   
        }
        
        //1.6get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.7validate����p�X���[�h(�p�X���[�h : String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //1.8get�ŐV����(�،���ЃR�[�h : String)
        WEB3AccInfoMailDistribution l_mailDistribution = WEB3AccInfoMailDistribution.getLastAction(l_strInstitutionCode);
        
        //1.9(*) �ē����[���z�M�w���̍ŐV�������擾�ł����ꍇ�iget�ŐV����() != null�j
        if (l_mailDistribution != null)
        {
            if (!l_mailDistribution.isMailRequestStatus())
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01381, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �ŐV�������z�M���ς̏ꍇ�́A�z�M�w���s��");  
            }
        }
        
        //1.10get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();
        
        //1.11createNew�ē����[���z�M�w��(�،���ЃR�[�h : String, is�S�ڋq : boolean, �X�V�҃R�[�h : String)
        WEB3AccInfoMailDistribution l_accInfoMailDistribution = 
            WEB3AccInfoMailDistribution.createNewMailDistribution(l_strInstitutionCode, l_request.allAccountFlag, l_strAdministratorCode);
        
        //1.12getDataSourceObject( )
        InformationMailRequestRow l_informationMailRequestRow = 
            (InformationMailRequestRow)l_accInfoMailDistribution.getDataSourceObject();
            
        //1.13doInsertQuery(arg0�i=�ē����[���z�M�w���s�j : Row)
        try
        {
                
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_informationMailRequestRow);
        }
        catch (DataFindException l_ex)
        {
                
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���! ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
                
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
        
        //1.14createResponse( )
        WEB3AdminAccInfoMailDistributionCompleteResponse l_response = 
            (WEB3AdminAccInfoMailDistributionCompleteResponse)l_request.createResponse();
        
        return l_response;
    }
    /**
     * (validate�z�M���)<BR>
     * �ē����[���z�M�w������m�F�������s���B<BR> 
     *<BR>
     *�V�[�P���X�}<BR> 
     *�u���q�l���i�ē����[���z�M�jvalidate�z�M����v�Q�ƁB<BR> 
     * @@param l_request - �Ǘ��҂��q�l���ē����[���z�M�w������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA304D8
     */
    protected WEB3AdminAccInfoMailDistributionCancelConfirmResponse validateMailDistributionCancle(WEB3AdminAccInfoMailDistributionCancelConfirmRequest l_request)throws WEB3BaseException
    { 
        final String STR_METHOD_NAME = " validateMailDistributionCancle(WEB3AccInfoMailDistributionCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3) validate����(�@@�\�J�e�S���R�[�h�i=���[�����j : String, is�X�V�i=true�j : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        //1.4) is�S���X����( )
        boolean l_blnAllBranchsPermission = l_administrator.isAllBranchsPermission();
        if (!l_blnAllBranchsPermission)
        {
         
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �S���X���̊Ǘ��҂łȂ��ꍇ�́A����s��");   
        }
        //get�،���ЃR�[�h( )
        l_administrator.getInstitutionCode();
        
        //�ē����[���z�M�w��(�ē����[���z�M�w���h�c : long)
        WEB3AccInfoMailDistribution l_accInfoMailDistribution = null;
        try
        {
            l_accInfoMailDistribution =
                new WEB3AccInfoMailDistribution(Long.parseLong(l_request.id));
        }
        catch(NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);  
        }

        //is�z�M��( )
        if (l_accInfoMailDistribution.isMailRequestStatus())
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01382, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �ŐV�������z�M�ς̏ꍇ�́A�z�M����s��");
        }
        
        WEB3AdminAccInfoMailDistributionCancelConfirmResponse l_response = 
            (WEB3AdminAccInfoMailDistributionCancelConfirmResponse) l_request.createResponse();  
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (submit�z�M���)<BR>
     * �ē����[���z�M�w����������������s���B<BR> 
     *<BR>
     *�V�[�P���X�} <BR>
     *�u���q�l���i�ē����[���z�M�jsubmit�z�M����v�Q�ƁB<BR> 
     * @@param l_request - �Ǘ��҂��q�l���ē����[���z�M�w������������N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA305D8
     */
    protected WEB3AdminAccInfoMailDistributionCancelCompleteResponse submitMailDistributionCancle(WEB3AdminAccInfoMailDistributionCancelCompleteRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitMailDistributionCancle(WEB3AccInfoMailDistributionCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3) validate����(�@@�\�J�e�S���R�[�h�i=���[�����j : String, is�X�V�i=true�j : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        //1.4) is�S���X����( )
        boolean l_blnAllBranchsPermission = l_administrator.isAllBranchsPermission();
        if(!l_blnAllBranchsPermission)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �S���X���̊Ǘ��҂łȂ��ꍇ�́A����s��"); 
        }
        
        //1.6)get�،���ЃR�[�h( )
        l_administrator.getInstitutionCode();
        
        //1.7)validate����p�X���[�h(�p�X���[�h : String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //1.8)�ē����[���z�M�w��(long)
        WEB3AccInfoMailDistribution l_accInfoMailDistribution = null;
        try
        {
            l_accInfoMailDistribution =
                new WEB3AccInfoMailDistribution(Long.parseLong(l_request.id));
        }
        catch(NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);  
        }

            
       //1.9)is�z�M��( )
       boolean l_blnisMailRequestStatus = l_accInfoMailDistribution.isMailRequestStatus();
       //1.10)
        if(l_blnisMailRequestStatus)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01382, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �ŐV�������z�M�ς̏ꍇ�́A�z�M����s��");
        }
        //1.11)get�ē����[���z�M�w���h�c( )
        long l_lngRequestId = l_accInfoMailDistribution.getInformationMailRequestId();
        
        //1.12)
        QueryProcessor l_queryProcessor;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            InformationMailRequestPK l_informationMailRequesPK = new InformationMailRequestPK(l_lngRequestId);
            l_queryProcessor.doDeleteQuery(l_informationMailRequesPK);
        }
        catch (DataFindException l_ex)
        {
            
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.13)
        WEB3AdminAccInfoMailDistributionCancelCompleteResponse l_response = 
        (WEB3AdminAccInfoMailDistributionCancelCompleteResponse) l_request.createResponse();  
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}@
