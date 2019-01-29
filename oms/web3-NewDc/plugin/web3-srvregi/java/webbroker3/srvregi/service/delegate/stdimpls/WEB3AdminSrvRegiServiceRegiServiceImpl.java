head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceRegiServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�T�[�r�XImpl(WEB3AdminSrvRegiServiceRegiServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 �s�p (���u) �V�K�쐬
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SrvUseKeyTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUseKey;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistConfirmResponse;
import webbroker3.srvregi.message.WEB3SrvRegiExecKey;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceRegiService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�T�[�r�XImpl)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�T�[�r�X�����N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceRegiServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiServiceRegiService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceRegiServiceImpl.class);

    /**
     * @@roseuid 416F392B00BB
     */
    public WEB3AdminSrvRegiServiceRegiServiceImpl() 
    {
     
    }
    
    /**
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate�o�^( )�܂��́A <BR>
     * submit�o�^( )���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F510070150
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";

        log.entering(STR_METHOD_NAME);
        
        //1.1:<l_request instanceof �T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�m�F���N�G�X�g�̏ꍇ>
        if (l_request instanceof WEB3AdminSrvRegiServiceRegistConfirmRequest)
        {
            //1.1.1:validate�T�[�r�X�o�^(�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�m�F���N�G�X�g)
            log.debug(" WEB3AdminSrvRegiServiceRegistConfirmRequest ");
            WEB3AdminSrvRegiServiceRegistConfirmResponse l_serviceRegistConfirmResponse = validateServiceRegi(
                (WEB3AdminSrvRegiServiceRegistConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_serviceRegistConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminSrvRegiServiceRegistCompleteRequest)
        //1.2:<l_request instanceof �T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�������N�G�X�g�̏ꍇ>
        {
            //1.2.1:submit�T�[�r�X�o�^(�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�������N�G�X�g)
            log.debug(" WEB3AdminSrvRegiServiceRegistCompleteRequest ");
            WEB3AdminSrvRegiServiceRegistCompleteResponse l_serviceRegistCompleteResponse = submitServiceRegi(
                (WEB3AdminSrvRegiServiceRegistCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_serviceRegistCompleteResponse;
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }               
    }
    
    /**
     * (validate�T�[�r�X�o�^)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�R���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�o�^�R���v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�o�^�R���v): <BR>
     *         1.6. isDIR�Ǘ���( )<BR>
     *        �Ǘ��҃I�u�W�F�N�g.isDIR�Ǘ���( )==false�̏ꍇ�A<BR>
     *        ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00986<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�o�^�R���v): <BR>
     *         1.7. getSrvMaster(String, String, boolean)<BR>
     *          �T�[�r�X�}�X�^�[�I�u�W�F�N�g���擾�ł����ꍇ�A<BR>
     *          ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01989<BR>
     * ==========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F510270140
     */
    protected WEB3AdminSrvRegiServiceRegistConfirmResponse validateServiceRegi(WEB3AdminSrvRegiServiceRegistConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateServiceRegi(WEB3AdminSrvRegiServiceRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1:validate( )
        l_request.validate();
        
        //1.3:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE,true);
        
        //1.5:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6:isDIR�Ǘ���( )
        if (!l_admin.isDirAdministrator())
        {
            log.debug("isDIR�Ǘ���()=false");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00985, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.7:get�T�[�r�X�}�X�^�[(String, String, boolean)
        WEB3SrvRegiServiceInfoManagement l_srvInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        
        WEB3SrvRegiServiceMaster l_srvMasterGetted = null;
        
        try
        {
            l_srvMasterGetted = l_srvInfoManagement.getSrvMaster(
                l_strInstitutionCode, 
                l_request.serviceDiv, 
                false);
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug("1.7:get�T�[�r�X�}�X�^�[(String, String, boolean) = null");
            
            log.debug(l_ex.getMessage(), l_ex);
        }
        
        if ( l_srvMasterGetted != null)
        {
            log.debug("1.7:get�T�[�r�X�}�X�^�[(String, String, boolean) != null");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01989, 
                this.getClass().getName() + STR_METHOD_NAME);                            
        }
        
        //1.8:createNew�T�[�r�X�}�X�^�[(�،���ЃR�[�h : String, �T�[�r�X�敪 : String, �\���敪 : String)
        WEB3SrvRegiServiceMaster l_srvMaster =  
            WEB3SrvRegiServiceMaster.createNewSrvMaster(
                l_strInstitutionCode, l_request.serviceDiv, l_request.applyDiv);
        
        //1.9:get�m�F���[�����( )
        WEB3GentradeMailInfo l_comfirmMailInfo = l_srvMaster.getConfirmMailInfo();
        
        //1.10:get�_��������[�����( )
        WEB3GentradeMailInfo l_endMailDiv = l_srvMaster.getEndMaiDivInfo();
        
        //1.11:create���X�|���X( )
        WEB3AdminSrvRegiServiceRegistConfirmResponse l_response = 
            (WEB3AdminSrvRegiServiceRegistConfirmResponse)l_request.createResponse();
        
        //1.12:<���X�|���X�E�Z�b�g>
        if (l_comfirmMailInfo == null)
        {   
            log.debug("l_comfirmMailInfo == null");
            
            l_response.confirmMailFrom = null;
            l_response.confirmMailSubject = null;
            l_response.confirmMailHeader = null;
            l_response.confirmMailBody = null;
            l_response.confirmMailFooter = null;
        }
        else
        {
            log.debug("l_comfirmMailInfo != null");
            
            l_response.confirmMailFrom = l_comfirmMailInfo.getMailSender();
            l_response.confirmMailSubject = l_comfirmMailInfo.getSubject();
            l_response.confirmMailHeader = l_comfirmMailInfo.getMailHeader();
            l_response.confirmMailBody = l_comfirmMailInfo.getMailText();
            l_response.confirmMailFooter = l_comfirmMailInfo.getMailFooter();
        }
              
        if (l_endMailDiv == null)
        {   
            log.debug("l_endMailDiv == null");
            
            l_response.noticeMailFrom = null;
            l_response.noticeMailSubject = null;
            l_response.noticeMailHeader = null;
            l_response.noticeMailBody = null;
            l_response.noticeMailFooter = null;
        }
        else
        {
            log.debug("l_endMailDiv != null");
            
            l_response.noticeMailFrom = l_endMailDiv.getMailSender();
            l_response.noticeMailSubject = l_endMailDiv.getSubject();
            l_response.noticeMailHeader = l_endMailDiv.getMailHeader();
            l_response.noticeMailBody = l_endMailDiv.getMailText();
            l_response.noticeMailFooter = l_endMailDiv.getMailFooter();
        }        
        
        log.exiting(STR_METHOD_NAME); 
        
        return l_response;
    }
    
    /**
     * (submit�T�[�r�X�o�^)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�o�^�v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�o�^�v): <BR>
     *         1.7 isDIR�Ǘ���( )  <BR>
     *         �Ǘ��҃I�u�W�F�N�g.isDIR�Ǘ���( )==false�̏ꍇ�A<BR>
     *         ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00986<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�o�^�v): <BR>
     *         1.8 getSrvMaster(String, String, boolean)<BR>
     *         �T�[�r�X�}�X�^�[�I�u�W�F�N�g���擾�ł����ꍇ�A<BR>
     *         ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01989<BR>
     * ==========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5108002D7
     */
    protected WEB3AdminSrvRegiServiceRegistCompleteResponse submitServiceRegi(WEB3AdminSrvRegiServiceRegistCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitServiceRegi(WEB3AdminSrvRegiServiceRegistCompleteReques)";
        log.entering(STR_METHOD_NAME);

        //1.1:validate( )
        l_request.validate();
        
        //1.3:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE,true);        
        
        //1.5:validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.6:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7:isDIR�Ǘ���( )
        if (!l_admin.isDirAdministrator())
        {
            log.debug("1.7:isDIR�Ǘ���()=false");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00985, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.8:get�T�[�r�X�}�X�^�[(String, String, boolean)
        WEB3SrvRegiServiceInfoManagement l_srvInfoManagement = new WEB3SrvRegiServiceInfoManagement();
       
        WEB3SrvRegiServiceMaster l_srvMasterGetted = null;
        
        try
        {
            l_srvMasterGetted = l_srvInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug("1.8:get�T�[�r�X�}�X�^�[(String, String, boolean) = null");
    
            log.debug(l_ex.getMessage(), l_ex);
        }
        
        if (l_srvMasterGetted != null)
        {
            log.debug("1.8:get�T�[�r�X�}�X�^�[(String, String, boolean) != null");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01989, 
                this.getClass().getName() + STR_METHOD_NAME);                            
        }
        
        //1.9:createNew�T�[�r�X�}�X�^�[(�،���ЃR�[�h : String, �T�[�r�X�敪 : String, �\���敪 : String)
        WEB3SrvRegiServiceMaster l_srvMaster =  
            WEB3SrvRegiServiceMaster.createNewSrvMaster(
                l_strInstitutionCode, l_request.serviceDiv, l_request.applyDiv);        
        
        //1.10:set�T�[�r�X����(�T�[�r�X���� : String)
        l_srvMaster.setSrvName(l_request.serviceName);
        
        //1.11:set�T�[�r�X���pURL(String)
        l_srvMaster.setSrvUrl(l_request.url);
        
        //1.12:saveNew�T�[�r�X�}�X�^�[( )
        l_srvMaster.saveNewSrvMaster();
        
        //1.13:<���N�G�X�g�f�[�^.�E�v != null�̏ꍇ>
        if (l_request.summary != null)
        {
            log.debug("1.13:<���N�G�X�g�f�[�^.�E�v != null�̏ꍇ>");
            
            //1.13.1:createNew�\���v�T�[�r�X(String, String)
            WEB3SrvRegiApplicationRequiredService l_appliRequiredSrv = 
                WEB3SrvRegiApplicationRequiredService.createNewAppliRequiredSrv(
                    l_strInstitutionCode, l_request.serviceDiv);
        
            //1.13.2:set���I�ݒ�(String)
            l_appliRequiredSrv.setLotDiv(l_request.lotteryDiv);
        
            //1.13.3:set�E�v(String)
            l_appliRequiredSrv.setSummary(l_request.summary);
        
            //1.13.4:set�m�F���[��(String)
            l_appliRequiredSrv.setStartMailDiv(l_request.confirmMailDiv);
        
            //1.13.5:set�_��������[��(String)
            l_appliRequiredSrv.setEndMailDiv(l_request.noticeMailDiv);
            
            //1.13.6:set��萔�����v�z(double)
            l_appliRequiredSrv.setMinCommAmt(0);
        
            //1.13.7: saveNew�\���v�T�[�r�X( )
            l_appliRequiredSrv.saveNewAppliRequiredSrv();
        }
        
        //1.14: �����N�G�X�g�f�[�^.��QURL!=null�̏ꍇ��
        if (l_request.url2 != null)
        {
            //1.14.1:createNew�T�[�r�X���p�L�[(String, String, String)
            WEB3SrvRegiServiceUseKey l_srvUseKey = 
                WEB3SrvRegiServiceUseKey.createNewSrvUseKey(l_strInstitutionCode,
                    l_request.serviceDiv, WEB3SrvUseKeyTypeDef.URL2);
    
            //1.14.2:set�T�[�r�X���p�L�[(String)
            l_srvUseKey.setSrvUseKey(l_request.url2);
            
            //1.14.3:saveNew�T�[�r�X���p�L�[( )
            l_srvUseKey.saveNewSrvUseKey();
        }
        
        //1.15:���T�[�r�X���p�L�[�̂������͕K�{���ڂ̓o�^�i���葱�����T����{����j
        //�n�b�V���v�Z�����敪
        //1.15.1:createNew�T�[�r�X���p�L�[(String, String, String)
        WEB3SrvRegiServiceUseKey l_srvUseKey = 
            WEB3SrvRegiServiceUseKey.createNewSrvUseKey(l_strInstitutionCode,
                l_request.serviceDiv, WEB3SrvUseKeyTypeDef.HASH_CAL_HOW_TO_DIV);
        //1.15.2:set�T�[�r�X���p�L�[(String)
        l_srvUseKey.setSrvUseKey(l_request.hashCalHowToDiv);
        //1.15.3:saveNew�T�[�r�X���p�L�[( )
        l_srvUseKey.saveNewSrvUseKey();

        //�n�b�V���v�Z�菇�敪
        //1.15.1:createNew�T�[�r�X���p�L�[(String, String, String)
        WEB3SrvRegiServiceUseKey l_srvUseKey2 = 
            WEB3SrvRegiServiceUseKey.createNewSrvUseKey(l_strInstitutionCode,
                l_request.serviceDiv, WEB3SrvUseKeyTypeDef.HASH_CAL_ORDER_DIV);
        //1.15.2:set�T�[�r�X���p�L�[(String)
        l_srvUseKey2.setSrvUseKey(l_request.hashCalOrderDiv);
        //1.15.3:saveNew�T�[�r�X���p�L�[( )
        l_srvUseKey2.saveNewSrvUseKey();
         
        //���M���@@�敪 
        //1.15.1:createNew�T�[�r�X���p�L�[(String, String, String)
        WEB3SrvRegiServiceUseKey l_srvUseKey3 = 
            WEB3SrvRegiServiceUseKey.createNewSrvUseKey(l_strInstitutionCode,
                l_request.serviceDiv, WEB3SrvUseKeyTypeDef.SEND_HOW_TO_DIV);
        //1.15.2:set�T�[�r�X���p�L�[(String)
        l_srvUseKey3.setSrvUseKey(l_request.sendHowToDiv);
        //1.15.3:saveNew�T�[�r�X���p�L�[( )
        l_srvUseKey3.saveNewSrvUseKey();
        
        //���M�p�����[�^�敪
        //1.15.1:createNew�T�[�r�X���p�L�[(String, String, String)
        WEB3SrvRegiServiceUseKey l_srvUseKey4 = 
            WEB3SrvRegiServiceUseKey.createNewSrvUseKey(l_strInstitutionCode,
                l_request.serviceDiv, WEB3SrvUseKeyTypeDef.SEND_PARAM_DIV);
        //1.15.2:set�T�[�r�X���p�L�[(String)
        l_srvUseKey4.setSrvUseKey(l_request.sendParamDiv);
        //1.15.3:saveNew�T�[�r�X���p�L�[( )
        l_srvUseKey4.saveNewSrvUseKey();
         
        //�Í����ڋq�R�[�h�敪
        //1.15.1:createNew�T�[�r�X���p�L�[(String, String, String)
        WEB3SrvRegiServiceUseKey l_srvUseKey5 = 
            WEB3SrvRegiServiceUseKey.createNewSrvUseKey(l_strInstitutionCode,
                l_request.serviceDiv, WEB3SrvUseKeyTypeDef.CRYPT_ACCOUNT_CODE_DIV);
        //1.15.2:set�T�[�r�X���p�L�[(String)
        l_srvUseKey5.setSrvUseKey(l_request.cryptAccountCodeDiv);
        //1.15.3:saveNew�T�[�r�X���p�L�[( )
        l_srvUseKey5.saveNewSrvUseKey();

        //1.16:�����N�G�X�g�f�[�^.�n�b�V���l�ꗗ�̌������J��Ԃ���
        if (l_request.hashList != null)
        {
            int l_intCnt = l_request.hashList.length;
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3SrvRegiExecKey l_exceKey = l_request.hashList[i];
                //1.16.1:�����N�G�X�g�f�[�^.�n�b�V���l�ꗗ[n].�����敪=�L���̏ꍇ��
                if (!l_exceKey.invalidDiv)
                {
                    //1.16.1.1:createNew�T�[�r�X���p�L�[(String, String, String)
                    WEB3SrvRegiServiceUseKey l_srvUseKeyNew = 
                        WEB3SrvRegiServiceUseKey.createNewSrvUseKey(
                            l_strInstitutionCode, 
                            l_request.serviceDiv, 
                            WEB3SrvUseKeyTypeDef.HSAH_VALUE);
                            
                    //1.16.1.2:set�T�[�r�X���p�L�[(String)
                    l_srvUseKeyNew.setSrvUseKey(l_exceKey.key);
                    
                    //1.16.1.3:saveNew�T�[�r�X���p�L�[( )
                    l_srvUseKeyNew.saveNewSrvUseKey();
                }
            }
        }

        
        //1.17:�����N�G�X�g�f�[�^.���M�p�����[�^�ꗗ�̌������J��Ԃ���
        if (l_request.paramList != null)
        {
            int l_intCnt = l_request.paramList.length;
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3SrvRegiExecKey l_exceKey = l_request.paramList[i];
                //1.17.1:�����N�G�X�g�f�[�^.���M�p�����[�^�ꗗ[n].�����敪=�L���̏ꍇ��
                if (!l_exceKey.invalidDiv)
                {
                    //1.17.1.1:createNew�T�[�r�X���p�L�[(String, String, String)
                    WEB3SrvRegiServiceUseKey l_srvUseKeyNew = 
                        WEB3SrvRegiServiceUseKey.createNewSrvUseKey(
                            l_strInstitutionCode, 
                            l_request.serviceDiv, 
                            WEB3SrvUseKeyTypeDef.SEND_PARAM);
                            
                    //1.17.1.2:set�T�[�r�X���p�L�[(String)
                    l_srvUseKeyNew.setSrvUseKey(l_exceKey.key);
                    
                    //1.17.1.3:saveNew�T�[�r�X���p�L�[( )
                    l_srvUseKeyNew.saveNewSrvUseKey();
                }
            }
        }
        
        //1.15:create���X�|���X( )
        WEB3AdminSrvRegiServiceRegistCompleteResponse l_response = 
            (WEB3AdminSrvRegiServiceRegistCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME); 
        
        return l_response;
    }
}
@
