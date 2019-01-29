head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�T�[�r�XImpl(WEB3AdminMCAdminPermGrpChangeServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/29 ���z (���u) �V�K�쐬
                 : 2006/08/28 �юu�� (���u) �d�l�ύX ���f��022
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit;
import webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpChangeService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermUnitCreateService;
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
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�T�[�r�XImpl)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�T�[�r�X�����N���X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpChangeServiceImpl implements WEB3AdminMCAdminPermGrpChangeService 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpChangeServiceImpl.class);  
    
    /**
     * �Ǘ��Ҍ����O���[�v�ύX���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX���̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�����O���[�v()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�����O���[�v()���R�[������B <BR>
     * <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41771E340280
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.error("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        
        //�P�j���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B
        
        //a> �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX���̓��N�G�X�g�̏ꍇ 
        //   get���͉��()���R�[������B
        if (l_request instanceof WEB3AdminMCAdminPermGrpChangeInputRequest)
        {
            l_response = 
                getInputScreen((WEB3AdminMCAdminPermGrpChangeInputRequest)l_request);         
        }       
        //b> �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�m�F���N�G�X�g�̏ꍇ 
        //   validate�����O���[�v()���R�[������B
        else if (l_request instanceof WEB3AdminMCAdminPermGrpChangeConfirmRequest)
        {
            l_response = 
                validatePermGrp((WEB3AdminMCAdminPermGrpChangeConfirmRequest)l_request);         
        }   
        //c> �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�������N�G�X�g�̏ꍇ 
        //   submit�����O���[�v()���R�[������B
        else if (l_request instanceof WEB3AdminMCAdminPermGrpChangeCompleteRequest)
        {
            l_response = 
                submitPermGrp((WEB3AdminMCAdminPermGrpChangeCompleteRequest)l_request);
                    
        }
        else 
        {
            log.error("error in get needed request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * �Ǘ��Ҍ����O���[�v�ύX���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�ύX�jget���͉�ʁv�Q�ƁB <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�ύX�jget���͉�ʁv<BR>
     *         ��̈ʒu    :  1.7.2(*2.1) ����t���[<BR>
     *         DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01225           <BR>
     * =========================================================== <BR>              
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX���̓��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41771E340290
     */
    protected WEB3AdminMCAdminPermGrpChangeInputResponse getInputScreen(WEB3AdminMCAdminPermGrpChangeInputRequest l_request) 
        throws WEB3BaseException 
    {
        String l_strMethodName = "getInputScreen(WEB3AdminMCAdminPermGrpChangeInputRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //1.2 ���O�C�������Ǘ��҃I�u�W�F�N�g���擾����B
        //getInstanceFrom���O�C�����()
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 �Ǘ��Ҍ����`�F�b�N���s�� 
        // [validate����()�Ɏw�肷�����]
        // �@@�\�J�e�S���R�[�h�F�@@�\�J�e�S���R�[�h.�Ǘ��Ҍ����Ǘ� 
        // is�X�V�Ftrue 
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION, true);
        
        //1.4 �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        
        //���N�G�X�g�f�[�^.�������x���R�[�h
        String l_strPermissionLevel = l_request.permissionLevel;
               
        //1.5 �Ǘ��҃^�C�v�I�u�W�F�N�g�𐶐�����B 
        //[�R���X�g���N�^�̈���] 
        //�،���ЃR�[�h�Fget�،���ЃR�[�h() 
        //�������x���F���N�G�X�g�f�[�^.�������x���R�[�h 
        
        WEB3AdminMCAdminType l_web3AdminMCAdminType =
            new WEB3AdminMCAdminType(l_strInstitutionCode, l_strPermissionLevel);
        
        //1.6 �c�h�q�Ǘ��҂��𔻒肷��B 
        boolean l_booIsDirByAdministrator = l_web3Administrator.isDirAdministrator();
        
        //1.7 ����t���[ 
        //DIR�Ǘ��҂łȂ��iisDIR�Ǘ���() == false�j�ꍇ�̂ݏ������{
        if (l_booIsDirByAdministrator == false)
        {
            //1.7.1 �i�Ǘ��҃^�C�v���j�c�h�q�Ǘ��҂��𔻒肷��B
            boolean l_booIsDirByAdminType = l_web3AdminMCAdminType.isDIRAdministrator(); 
            
            //1.7.2 ����t���[ 
            //DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j�A��O���X���[����B
            //      class : WEB3BusinessLayerException <BR>
            //        tag : BUSINESS_ERROR_01225 
            if (l_booIsDirByAdminType == true)
            {
                log.error("fail isDIR by �Ǘ��҃^�C�v");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                    this.getClass().getName() + "." + l_strMethodName);                         
            }
        }
        
        //1.8 �������x�����擾����B
        String l_strPlFromAdmin = l_web3Administrator.getPermissionLevel();

        //1.9 �i�Ǘ��҃^�C�v���j�������x�����擾����B
        String l_strPlFromAdminType = l_web3AdminMCAdminType.getPermissionLevel();
        //1.10 ����t���[
        //�i�Ǘ���.get�������x��() ==�@@�Ǘ��҃^�C�v.get�������x��()�j�A��O���X���[����B
        //     class :  WEB3BusinessLayerException 
        //       tag :  BUSINESS_ERROR_01285 
        if (l_strPlFromAdmin.equals(l_strPlFromAdminType))
        {
            log.error("�Ǘ���.get�������x��() == �Ǘ��҃^�C�v.get�������x��()");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01285,
                this.getClass().getName() + "." + l_strMethodName);                           
        }
        
        //1.11 �Ǘ��҃^�C�v���𐶐�����B 
        //[create�Ǘ��҃^�C�v()�Ɏw�肷�����] 
        //�Ǘ��҃^�C�v�F���������Ǘ��҃^�C�v�I�u�W�F�N�g 
        
        //a> �Ǘ��Ҍ������쐬�T�[�r�XImpl
        WEB3AdminMCAdminPermUnitCreateService l_web3AdminMCAdminPermUnitCreateService = 
            (WEB3AdminMCAdminPermUnitCreateService)Services.getService(
                WEB3AdminMCAdminPermUnitCreateService.class);
        //b> create�Ǘ��҃^�C�v()
        WEB3AdminMCAdminTypeUnit l_web3AdminMCAdminTypeUnit = 
            l_web3AdminMCAdminPermUnitCreateService.createAdminTypeUnit(l_web3AdminMCAdminType);
        
        //1.12 �،���ЁC�������x���ɊY������@@�\�J�e�S������z��ɂĎ擾����B 
        //[get�����\�@@�\�J�e�S���ꗗ()�Ɏw�肷�����] 
        //�،���ЃR�[�h�Fget�،���ЃR�[�h() 
        //�������x���F���N�G�X�g�f�[�^.�������x���R�[�h 
        //�@@�\�J�e�S���R�[�h[]�Fnull 
        WEB3AdminMCTransactionCategoryUnit[] l_web3AdminMCTransactionCategoryUnit = 
            l_web3AdminMCAdminPermUnitCreateService.createOperatePossibleTransactionCategoryUnit(
                l_strInstitutionCode, 
                l_strPermissionLevel, 
                null);
                
        //1.13 ���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminMCAdminPermGrpChangeInputResponse l_response = 
            (WEB3AdminMCAdminPermGrpChangeInputResponse)l_request.createResponse();
            
        //1.14 (*3)�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
        //�Ǘ��҃^�C�v��� = create�Ǘ��҃^�C�v()�̖߂�l
        l_response.adminTypeUnit = l_web3AdminMCAdminTypeUnit;
        
        //�����\�@@�\�J�e�S���ꗗ = create�����\�@@�\�J�e�S���ꗗ()�̖߂�l
        l_response.transactionCategoryUnits = 
            l_web3AdminMCTransactionCategoryUnit;
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (validate�����O���[�v)<BR>
     * �����O���[�v�ύX�m�F���������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�ύX�jvalidate�����O���[�v�v�Q�ƁB <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�ύX�jvalidate�����O���[�v�v<BR>
     *         ��̈ʒu    : 1.7 ����t���[<BR>
     *         DIR�Ǘ��҃t���O���ύX����Ă���ꍇ
     *        �i�Ǘ��҃^�C�v.isDIR�Ǘ���() !=�@@���N�G�X�g�f�[�^.DIR�Ǘ��҃t���O�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01284       <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�ύX�jvalidate�����O���[�v�v<BR>
     *         ��̈ʒu    : 1.10 ����t���[<BR>
     *         ���O�C�����Ǘ��҂̌������x�����w�肳��Ă���ꍇ
     *        �i�Ǘ���.get�������x��() ==�@@�Ǘ��҃^�C�v.get�������x��()�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01285       <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�ύX�jvalidate�����O���[�v�v<BR>
     *         ��̈ʒu    : 1.12.3 ����t���[<BR>
     *         DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01225      <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeConfirmResponse
     * @@roseuid 41771E340292
     */
    protected WEB3AdminMCAdminPermGrpChangeConfirmResponse validatePermGrp(WEB3AdminMCAdminPermGrpChangeConfirmRequest l_request)
        throws WEB3BaseException 
    {
        String l_strMethodName = "validatePermGrp(WEB3AdminMCAdminPermGrpChangeConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();
        
        //1.2 ���O�C�������Ǘ��҃I�u�W�F�N�g���擾����B
        //getInstanceFrom���O�C�����()
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 �Ǘ��Ҍ����`�F�b�N���s�� 
        // [validate����()�Ɏw�肷�����]
        // �@@�\�J�e�S���R�[�h�F�@@�\�J�e�S���R�[�h.�Ǘ��Ҍ����Ǘ� 
        // is�X�V�Ftrue 
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION, true);
        
        //1.4 �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        
        //1.5 �Ǘ��҃^�C�v���擾����B 
        //[�R���X�g���N�^�̈���] 
        //�،���ЃR�[�h�Fget�،���ЃR�[�h() 
        //�������x���F���N�G�X�g�f�[�^.�Ǘ��҃^�C�v���.�������x���R�[�h
        String l_strPermissionLevel = l_request.adminTypeUnit.permissionLevel;
        WEB3AdminMCAdminType l_web3AdminMCAdminType =
            new WEB3AdminMCAdminType(l_strInstitutionCode, l_strPermissionLevel); 
        
        //1.6 �i�Ǘ��҃^�C�v���j�c�h�q�Ǘ��҂��𔻒肷��B
        boolean l_booIsDirByAdminType = l_web3AdminMCAdminType.isDIRAdministrator();
        
        //1.7 ����t���[
        //�i�Ǘ��҃^�C�v.isDIR�Ǘ���() !=�@@���N�G�X�g�f�[�^.isDIR�Ǘ���()�j�A��O���X���[����B
        //      class :  WEB3BusinessLayerException 
        //        tag :  BUSINESS_ERROR_01284
        if (l_booIsDirByAdminType != l_request.adminTypeUnit.isDIRAdmin())
        {
            log.error("�Ǘ��҃^�C�v.isDIR�Ǘ���() !=�@@���N�G�X�g�f�[�^.isDIR�Ǘ���()");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01284,
                this.getClass().getName() + "." + l_strMethodName);               
        }
        
        //1.8 �������x�����擾����B
        String l_strPlFromAdmin = l_web3Administrator.getPermissionLevel();

        //1.9 �i�Ǘ��҃^�C�v���j�������x�����擾����B
        String l_strPlFromAdminType = l_web3AdminMCAdminType.getPermissionLevel();
        
        //1.10 ����t���[
        //�i�Ǘ���.get�������x��() ==�@@�Ǘ��҃^�C�v.get�������x��()�j�A��O���X���[����B
        //     class :  WEB3BusinessLayerException 
        //       tag :  BUSINESS_ERROR_01285 
        if (l_strPlFromAdmin.equals(l_strPlFromAdminType))
        {
            log.error("�Ǘ���.get�������x��() == �Ǘ��҃^�C�v.get�������x��()");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01285,
                this.getClass().getName() + "." + l_strMethodName);                           
        }
        
        //1.11 �c�h�q�Ǘ��҂��𔻒肷��B 
        boolean l_booIsDirByAdministrator = l_web3Administrator.isDirAdministrator();
        
        //1.12 ����t���[ 
        //DIR�Ǘ��҂łȂ��iisDIR�Ǘ���() == false�j�ꍇ�̂ݏ������{
        if (l_booIsDirByAdministrator == false)
        {
            //1.12.1 �c�h�q�Ǘ��҃`�F�b�N���s�� 
            //[validateDIR�Ǘ���()�Ɏw�肷�����] 
            //isDIR�Ǘ��ҁi�I�y���[�^�j�FisDIR�Ǘ���() 
            l_request.adminTypeUnit.validateDIRAdmin(l_booIsDirByAdministrator);
            
            //1.12.2 �i�Ǘ��҃^�C�v���j�c�h�q�Ǘ��҂��𔻒肷��B
            boolean l_booIsDirByAdminTypeAgain = l_web3AdminMCAdminType.isDIRAdministrator();
            
            //1.12.3 ����t���[
            //DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j�A��O���X���[����B
            //     class : WEB3BusinessLayerException 
            //       tag : BUSINESS_ERROR_01225
            if (l_booIsDirByAdminTypeAgain == true)
            {
                log.error("fail isDIR by �Ǘ��҃^�C�v");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                    this.getClass().getName() + "." + l_strMethodName);                              
            }
        }
        
        //1.13 ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminMCAdminPermGrpChangeConfirmResponse l_response =
            (WEB3AdminMCAdminPermGrpChangeConfirmResponse)l_request.createResponse();
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (submit�����O���[�v)<BR>
     * �����O���[�v�ύX�������������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�ύX�jsubmit�����O���[�v�v�Q�ƁB <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�ύX�jvalidate�����O���[�v�v<BR>
     *         ��̈ʒu    : 1.8 ����t���[<BR>
     *         DIR�Ǘ��҃t���O���ύX����Ă���ꍇ
     *        �i�Ǘ��҃^�C�v.isDIR�Ǘ���() !=�@@���N�G�X�g�f�[�^.DIR�Ǘ��҃t���O�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01284       <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�ύX�jvalidate�����O���[�v�v<BR>
     *         ��̈ʒu    : 1.11 ����t���[<BR>
     *         ���O�C�����Ǘ��҂̌������x�����w�肳��Ă���ꍇ
     *        �i�Ǘ���.get�������x��() ==�@@�Ǘ��҃^�C�v.get�������x��()�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01285       <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :�u�Ǘ��҃��j���[����i�Ǘ��҃O���[�v�ύX�jsubmit�����O���[�v�v<BR>
     *         ��̈ʒu    : 1.13.3 ����t���[ <BR>
     *         DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01225           <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeCompleteResponse
     * @@roseuid 41771E340294
     */
    protected WEB3AdminMCAdminPermGrpChangeCompleteResponse submitPermGrp(WEB3AdminMCAdminPermGrpChangeCompleteRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "submitPermGrp(WEB3AdminMCAdminPermGrpChangeCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate(); 
        
        //1.2 ���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B 
        //getInstanceFrom���O�C�����()
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 �Ǘ��Ҍ����`�F�b�N���s�� 
        // [validate����()�Ɏw�肷�����]
        // �@@�\�J�e�S���R�[�h�F�@@�\�J�e�S���R�[�h.�Ǘ��Ҍ����Ǘ� 
        // is�X�V�Ftrue 
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION, true);
        
        //1.4 ����p�X���[�h�̃`�F�b�N���s�� 
        //[validate����p�X���[�h()�Ɏw�肷�����] 
        //�Ïؔԍ�: ���N�G�X�g�f�[�^.�Ïؔԍ�
        l_web3Administrator.validateTradingPassword(l_request.password);
        
        //1.5 �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        
        //1.6 �Ǘ��҃^�C�v���擾����B 
        //[�R���X�g���N�^�̈���] 
        //�،���ЃR�[�h�Fget�،���ЃR�[�h() 
        //�������x���F���N�G�X�g�f�[�^.�Ǘ��҃^�C�v���.�������x���R�[�h
        String l_strPermissionLevel = l_request.adminTypeUnit.permissionLevel; 
        WEB3AdminMCAdminType l_web3AdminMCAdminType = 
            new WEB3AdminMCAdminType(l_strInstitutionCode, l_strPermissionLevel);           
        
        //1.7�i�Ǘ��҃^�C�v���j�c�h�q�Ǘ��҂��𔻒肷��B
        boolean l_booIsDirByAdminType = l_web3AdminMCAdminType.isDIRAdministrator(); 
        
        //1.8 ����t���[
        //�i�Ǘ��҃^�C�v.isDIR�Ǘ���() !=�@@���N�G�X�g�f�[�^.isDIR�Ǘ���()�j�A��O���X���[����B
        //     class : WEB3BusinessLayerException
        //       tag : BUSINESS_ERROR_01284                  
        if (l_booIsDirByAdminType != l_request.adminTypeUnit.isDIRAdmin())
        {
            log.error("�Ǘ��҃^�C�v.isDIR�Ǘ���() !=�@@���N�G�X�g�f�[�^.isDIR�Ǘ���()");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01284,
                this.getClass().getName() + "." + l_strMethodName);               
        }
        
        //1.9 �i�Ǘ��҃^�C�v���j�������x�����擾����B
        String l_strPlFromAdminType = l_web3AdminMCAdminType.getPermissionLevel();
        
        //1.10 �������x�����擾����B
        String l_strPlFromAdmin = l_web3Administrator.getPermissionLevel();
        
        //1.11 ����t���[
        //�i�Ǘ���.get�������x��() ==�@@�Ǘ��҃^�C�v.get�������x��()�j�A��O���X���[����B
        //     class : WEB3BusinessLayerException 
        //       tag : BUSINESS_ERROR_01285 
        if (l_strPlFromAdmin.equals(l_strPlFromAdminType))
        {
            log.error("�Ǘ���.get�������x��() == �Ǘ��҃^�C�v.get�������x��()");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01285,
                this.getClass().getName() + "." + l_strMethodName);                           
        }
        
        //1.12 �c�h�q�Ǘ��҂��𔻒肷��B 
        boolean l_booIsDirByAdministrator = l_web3Administrator.isDirAdministrator();
        
        //1.13 ����t���[ 
        //DIR�Ǘ��҂łȂ��iisDIR�Ǘ���() == false�j�ꍇ�̂ݏ������{
        if (l_booIsDirByAdministrator == false)
        {
            //1.13.1 �c�h�q�Ǘ��҃`�F�b�N���s�� 
            //[validateDIR�Ǘ���()�Ɏw�肷�����] 
            //isDIR�Ǘ��ҁi�I�y���[�^�j�FisDIR�Ǘ���() 
            l_request.adminTypeUnit.validateDIRAdmin(l_booIsDirByAdministrator);
            
            //1.13.2 �i�Ǘ��҃^�C�v���j�c�h�q�Ǘ��҂��𔻒肷��B
            boolean l_booIsDirByAdminTypeAgain = l_web3AdminMCAdminType.isDIRAdministrator();
            
            //1.13.3 ����t���[
            //DIR�Ǘ��҂̊Ǘ��҃^�C�v�̏ꍇ�iisDIR�Ǘ���() == true�j�A��O���X���[����B
            //     class : WEB3BusinessLayerException 
            //       tag : BUSINESS_ERROR_01225
            if (l_booIsDirByAdminTypeAgain == true)
            {
                log.error("fail isDIR by �Ǘ��҃^�C�v");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                    this.getClass().getName() + "." + l_strMethodName);                              
            }
        }
        
        //1.14 �Ǘ��҃R�[�h���擾����B 
        String l_strAdministratorCode = l_web3Administrator.getAdministratorCode();
        
        //1.15 �Ǘ��҃^�C�v�e�[�u�����X�V����B
        //[save�Ǘ��҃^�C�v()�Ɏw�肷�����] 
        //a> �،���ЃR�[�h�Fget�،���ЃR�[�h() 
        //b> �������x���F���N�G�X�g�f�[�^.�Ǘ��҃^�C�v���.�������x���R�[�h        
        //c> �������x�����́F���N�G�X�g�f�[�^.�Ǘ��҃^�C�v���.�������x������ 
        String l_strPermissionLevelName = l_request.adminTypeUnit.permissionLevelName;
        
        //d> DIR�Ǘ��҃t���O�F���N�G�X�g�f�[�^.�Ǘ��҃^�C�v���.DIR�Ǘ��҃t���O 
        String l_strDirAdminFlag = l_request.adminTypeUnit.dirAdminFlag;
        
        //e> �S���X���t���O�F���N�G�X�g�f�[�^.�Ǘ��҃^�C�v���.�S���X���t���O 
        boolean l_booAllBranch = l_request.adminTypeUnit.allBranchPermissionFlag;        
        //f> �Ǘ��҃R�[�h�Fget�Ǘ��҃R�[�h() 
        
        WEB3AdminMCAdminType.saveAdminType(
            l_strInstitutionCode, 
            l_strPermissionLevel, 
            l_strPermissionLevelName, 
            l_strDirAdminFlag,
            l_booAllBranch,
            l_strAdministratorCode);
            
        //1.16 �Ǘ��Ҍ����e�[�u�����X�V����B
        //[update�����\�@@�\�J�e�S��()�Ɏw�肷�����] 
        //a> �،���ЃR�[�h�Fget�،���ЃR�[�h() 
        //b> �������x���F���N�G�X�g�f�[�^.�Ǘ��҃^�C�v���.�������x���R�[�h 
        //c> �����\�@@�\�J�e�S���F���N�G�X�g�f�[�^.�����\�@@�\�J�e�S���ꗗ 
        WEB3AdminMCTransactionCategoryUnit[] l_adminMCTransactionCategoryUnit =
            l_request.transactionCategoryUnits;        
        //d> �Ǘ��҃R�[�h�Fget�Ǘ��҃R�[�h() 

        WEB3AdminMCAdminPermUnitCreateService l_web3AdminMCAdminPermUnitCreateService =
            (WEB3AdminMCAdminPermUnitCreateService)Services.getService(
                WEB3AdminMCAdminPermUnitCreateService.class);
        l_web3AdminMCAdminPermUnitCreateService.updateOperatePossibleTransactionCategory(
            l_strInstitutionCode,
            l_strPermissionLevel,
            l_adminMCTransactionCategoryUnit,
            l_strAdministratorCode);
            
        //1.17 ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminMCAdminPermGrpChangeCompleteResponse l_response =
            (WEB3AdminMCAdminPermGrpChangeCompleteResponse)l_request.createResponse();
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
