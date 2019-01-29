head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��҃J�e�S���[�o�^�T�[�r�XImpl(WEB3AdminMutualCategoryRegistServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 ���� (���u) �V�K�쐬 
Revesion History : 2008/04/29 ���g (���u) �d�l�ύX���f��597,600
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualProductCategory;
import webbroker3.mf.data.MutualFundProductCategoryParams;
import webbroker3.mf.data.MutualFundProductCategoryRow;
import webbroker3.mf.define.WEB3ProcessAddChangeDivDef;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistChangeRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistChangeResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistConfirmResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistInputRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistInputResponse;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.service.delegate.WEB3AdminMutualCategoryRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���M�Ǘ��҃J�e�S���[�o�^�T�[�r�XImpl)<BR>
 * �����M���Ǘ��҃J�e�S���[�o�^�T�[�r�X�����N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminMutualCategoryRegistServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3AdminMutualCategoryRegistService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminMutualCategoryRegistServiceImpl.class);
    /**
     * (execute)<BR>
     * �����M�������J�e�S���[�o�^�����{����B<BR>
     * <BR>
     * ���b�Z�[�W�ɂ���āA�ȉ��̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * �@@��create�o�^���͉��( )<BR>
     * �@@��create�ύX���͉��( )<BR>
     * �@@��validate���M�����J�e�S���[�o�^( )<BR>
     * �@@��submit���M�����J�e�S���[�o�^( )
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153ED9E0269
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("if l_request == null");
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminMutualCategoryRegistInputRequest)
        {
            //create�o�^���͉��( )
            l_response = 
                this.createRegistrInput(
                    (WEB3AdminMutualCategoryRegistInputRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminMutualCategoryRegistChangeRequest)
        {
            //create�ύX���͉��( )
            l_response = 
                this.createRegistChangeInput(
                    (WEB3AdminMutualCategoryRegistChangeRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminMutualCategoryRegistConfirmRequest)
        {
            //validate���M�����J�e�S���[�o�^( )
            l_response =
                this.validateMutualProductCategoryRegistr(
                    (WEB3AdminMutualCategoryRegistConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminMutualCategoryRegistCompleteRequest)
        {
            //submit���M�����J�e�S���[�o�^( )
            l_response =
                this.submitMutualProductCategoryRegistr(
                    (WEB3AdminMutualCategoryRegistCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                STR_METHOD_NAME
                    + " l_Request ��"
                    + " WEB3AioCashoutCancelConfirmRequest "
                    + " �� WEB3AioCashoutCancelCompleteRequest"
                    + "��  WEB3AdminMutualCategoryRegistConfirmRequest"
                    + "��  WEB3AdminMutualCategoryRegistCompleteRequest"
                    + "�ȊO�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (create�o�^���͉��)<BR>
     * �����M�������J�e�S���[�o�^�p���͉�ʎ擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���M�j�Ǘ��҃J�e�S���[�o�^���͉�ʁv�Q��<BR>
     * --------------------------------------------------<BR>
     * �P�j���������̐ݒ�<BR>
     * �@@ ���M������ԊǗ�.setBusinessTimestamp( )���R�[������B<BR>
     * <BR>
     * �Q�j�Ǘ��҃I�u�W�F�N�g�̎擾<BR>
     * �@@�Q�|�P�j�Ǘ��҃N���X.getInstanceFrom���O�C�����( )���R�[�����A<BR>
     * �@@�@@�@@�@@   �Ǘ��҃I�u�W�F�N�g���擾����B<BR>
     * �@@�Q�|�Q�j�擾�����Ǘ��҃I�u�W�F�N�g.validate����( )���R�[������B<BR>
     * <BR>
     * �R�j���M�����J�e�S���[�̖��ׂ̎擾<BR>
     * �@@�R�|�P�j�擾�����Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h( )���R�[������B<BR>
     * �@@�R�|�Q�j�g�����M�����}�l�[�W��.get���M�����J�e�S���[���X�g( 
     * )���R�[������B<BR>
     * �@@�R�|�R�j�g�����M�����}�l�[�W��.create���M�����J�e�S���[�ꗗ( 
     * )���R�[������B<BR>
     * <BR>
     * �S�j�߂�l�̍쐬<BR>
     * �@@�S�|�P�j���N�G�X�g�f�[�^.create���X�|���X( )���R�[�����A<BR>
     *           ���X�|���X�f�[�^���擾����B<BR>
     * �@@�S�|�Q�j�擾�������X�|���X�f�[�^�ɁA�ȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@  �����M�����J�e�S���[�ꗗ��create���M�����J�e�S���[�ꗗ( )�̖߂�l<BR>
     * <BR>
     * �T�j���X�|���X�f�[�^��ԋp����B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���M�Ǘ��҃J�e�S���[�o�^���͉�ʃ��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminMutualCategoryRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153EE6202F6
     */
    protected WEB3AdminMutualCategoryRegistInputResponse createRegistrInput(
        WEB3AdminMutualCategoryRegistInputRequest l_request)
            throws WEB3BaseException
    {        
        final String STR_METHOD_NAME = 
            "createRegistrInput(WEB3AdminMutualCategoryRegistInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //�Q�j�Ǘ��҃I�u�W�F�N�g�̎擾 
        //�@@ �Q�|�P�j�Ǘ��҃N���X.getInstanceFrom���O�C�����( )���R�[�����A 
        //�@@�@@�@@�@@ �Ǘ��҃I�u�W�F�N�g���擾����B 
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //�@@ �Q�|�Q�j�擾�����Ǘ��҃I�u�W�F�N�g.validate����( )���R�[������B 
        //  [validate����()�Ɏw�肷�����] 
        //  �@@�\�J�e�S���R�[�h���@@�\�J�e�S���R�[�h.���M�i�����Ǘ��j 
        //  is�X�V��true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS, false);
        
        //   �R�j���M�����J�e�S���[�̖��ׂ̎擾 
        //�@@   �R�|�P�j�擾�����Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h( )���R�[������B 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //�@@   �R�|�Q�j�g�����M�����}�l�[�W��.get���M�����J�e�S���[���X�g( )���R�[������B 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager) l_tradingModule.getProductManager();
        List l_lisMFProductCategorysList = 
            l_mfProductManager.getMutualFundProductCategoryList(l_strInstitutionCode);
        
        //�@@   �R�|�R�j�g�����M�����}�l�[�W��.create���M�����J�e�S���[�ꗗ( )���R�[������B         
        WEB3MutualProductCategoryUnit[] l_mfProductCategoryUnits =
            l_mfProductManager.createMutualFundProductCategoryList(l_lisMFProductCategorysList);
        
        //�S�j�߂�l�̍쐬 
        //�@@  �S�|�P�j���N�G�X�g�f�[�^.create���X�|���X( )���R�[�����A���X�|���X�f�[�^���擾����B
        WEB3AdminMutualCategoryRegistInputResponse l_response =
            (WEB3AdminMutualCategoryRegistInputResponse) l_request.createResponse();
        
        //�@@  �S�|�Q�j�擾�������X�|���X�f�[�^�ɁA�ȉ��̃v���p�e�B���Z�b�g����B 
        //�@@�@@�@@�����M�����J�e�S���[�ꗗ��create���M�����J�e�S���[�ꗗ( )�̖߂�l 
        l_response.categoryList = l_mfProductCategoryUnits;
        
        //�T�j���X�|���X�f�[�^��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (create�ύX���͉��)<BR>
     * �����M�������J�e�S���[�ύX�p���͉�ʎ擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���M�j�Ǘ��҃J�e�S���[�ύX���͉�ʁv�Q��<BR>
     * --------------------------------------------------<BR>
     * �P�j���̓`�F�b�N<BR>
     * �@@  ���N�G�X�g�f�[�^.validate( )���R�[������B<BR>
     * <BR>
     * �Q�j���������̐ݒ�<BR>
     * �@@ ���M������ԊǗ�.setBusinessTimestamp( )���R�[������B<BR>
     * <BR>
     * �R�j�Ǘ��҃I�u�W�F�N�g�̎擾<BR>
     * �@@�R�|�P�j�Ǘ��҃N���X.getInstanceFrom���O�C�����( )���R�[������B<BR>
     * �@@�R�|�Q�j�擾�����Ǘ��҃I�u�W�F�N�g.validate����( )���R�[������B<BR>
     * <BR>
     * �S�j���M�����J�e�S���[�̃`�F�b�N<BR>
     * �@@�S�|�P�j�擾�����Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h( )���R�[������B<BR>
     * �@@�S�|�Q�j�g�����M�����}�l�[�W��.get���M�����J�e�S���[( )���R�[������B<BR>
     * �@@�S�|�R�j�S�|�Q�j��get���M�����J�e�S���[( )�̖߂�l��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@[�f�[�^�s����]�Ƃ��ė�O���X���[����B<BR>
     *          class: WEB3SystemLayerException<BR>
     *          tag:SYSTEM_ERROR_80006<BR>
     * <BR>
     * �T�j�߂�l�̍쐬<BR>
     * �@@�T�|�P�j���N�G�X�g�f�[�^.create���X�|���X( 
     * )���R�[�����A���X�|���X�f�[�^���擾����B<BR>
     * �@@�T�|�P�j�擾�������X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@    
     * �����M�����J�e�S���[�R�[�h�����N�G�X�g�f�[�^.���M�����J�e�S���[�R�[�h<BR>
     * �@@�@@�@@    �����M�����J�e�S���[���́�<BR>
     *              �擾�������M�����J�e�S���[�I�u�W�F�N�g.get�J�e�S���[����( )<BR>
     * �@@�@@�@@    ���e�J�e�S���[�R�[�h��<BR>
     *              �擾�������M�����J�e�S���[�I�u�W�F�N�g.get�e�J�e�S���[�R�[�h( )<BR>
     * <BR>
     * �U�j���X�|���X�f�[�^��ԋp����B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���M�Ǘ��҃J�e�S���[�ύX���͉�ʃ��N�G�X�g�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@return WEB3AdminMutualCategoryRegistChangeResponse
     * @@roseuid 4153EE6A0056
     */
    protected WEB3AdminMutualCategoryRegistChangeResponse createRegistChangeInput(
        WEB3AdminMutualCategoryRegistChangeRequest l_request)
            throws WEB3BaseException
    {       
        final String STR_METHOD_NAME = 
            "createRegistChangeInput(WEB3AdminMutualCategoryRegistChangeRequest l_request)";
          log.entering(STR_METHOD_NAME);
        
        //�P�j���̓`�F�b�N 
        //�@@ ���N�G�X�g�f�[�^.validate( )���R�[������B 
        l_request.validate();
      
        //�R�j�Ǘ��҃I�u�W�F�N�g�̎擾 
        //�@@ �R�|�P�j�Ǘ��҃N���X.getInstanceFrom���O�C�����( )���R�[������B 
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //�@@ �R�|�Q�j�擾�����Ǘ��҃I�u�W�F�N�g.validate����( )���R�[������B 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS, true);
   
        //�S�j���M�����J�e�S���[�̃`�F�b�N 
        //�@@ �S�|�P�j�擾�����Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h( )���R�[������B
        String l_strInstitutionCode = l_admin.getInstitutionCode();
     
        //�@@ �S�|�Q�j�g�����M�����}�l�[�W��.get���M�����J�e�S���[( )���R�[������B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
         TradingModule l_tradingModule = 
             l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
         WEB3MutualFundProductManager l_mfProductManager =
             (WEB3MutualFundProductManager) l_tradingModule.getProductManager();
        WEB3MutualProductCategory l_mfProductCategory =
             l_mfProductManager.getMutualFundProductCategory(
                l_strInstitutionCode, 
                l_request.categoryCode);
         
        //�@@ �S�|�R�j�S�|�Q�j��get���M�����J�e�S���[( )�̖߂�l��null�̏ꍇ�A 
        //�@@�@@�@@�@@ [�f�[�^�s����]�Ƃ��ė�O���X���[����B 
        if (l_mfProductCategory == null)
        {
            log.debug("�f�[�^�s�����G���[�B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�T�j�߂�l�̍쐬 
        //�@@ �T�|�P�j���N�G�X�g�f�[�^.create���X�|���X( )���R�[�����A���X�|���X�f�[�^���擾����B 
        WEB3AdminMutualCategoryRegistChangeResponse l_response =
            (WEB3AdminMutualCategoryRegistChangeResponse)l_request.createResponse();
        
        //�T�|�P�j�擾�������X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B 
        //�@@�@@�����M�����J�e�S���[�R�[�h�����N�G�X�g�f�[�^.���M�����J�e�S���[�R�[�h 
        l_response.categoryCode = l_request.categoryCode;
        //    �����M�����J�e�S���[���́��擾�������M�����J�e�S���[�I�u�W�F�N�g.get�J�e�S���[����( )
        l_response.categoryName = 
            l_mfProductCategory.getCategoryName();
        //    ���e�J�e�S���[�R�[�h���擾�������M�����J�e�S���[�I�u�W�F�N�g.get�e�J�e�S���[�R�[�h( ) 
        l_response.parentCategoryCode = 
            l_mfProductCategory.getParentCategoryCode();
        
        //�U�j���X�|���X�f�[�^��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (validate���M�����J�e�S���[�o�^)<BR>
     * �����M�������J�e�S���[�o�^�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���M�j�Ǘ��҃J�e�S���[�o�^�m�F�v�Q��<BR>
     * <BR>
     * ======================================================== <BR>
     * ��̈ʒu�Fget���M�����J�e�S���[( )�̖߂�l == null�̏ꍇ<BR>
     * �@@�@@�w�肳�ꂽ���M�����J�e�S���[�����݂��Ȃ������ꍇ�A<BR>
     * �@@�@@[�f�[�^�s����]�Ƃ��ė�O���X���[����B<BR>
     * �@@�@@class: WEB3SystemLayerException <BR>
     * �@@�@@tag�@@: SYSTEM_ERROR_80006 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * ��̈ʒu�Fget���ʓ��M�����J�e�S���[���X�g�i�j�̖߂�l��0���łȂ��ꍇ�A<BR>
     * �@@�@@[�폜�s��]�Ƃ��ė�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@tag�@@: BUSINESS_ERROR_03081 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * ��̈ʒu�Fget���M�����i�j�̖߂�l��0���łȂ��ꍇ<BR>
     *   [�폜�s��]�Ƃ��ė�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@tag�@@: BUSINESS_ERROR_03081 <BR>
     * ======================================================== <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���M�Ǘ��҃J�e�S���[�o�^�m�F���N�G�X�g�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@return WEB3AdminMutualCategoryRegistConfirmResponse
     * @@roseuid 4153EE700373
     */
    protected WEB3AdminMutualCategoryRegistConfirmResponse validateMutualProductCategoryRegistr(
        WEB3AdminMutualCategoryRegistConfirmRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMutualProductCategoryRegistr(WEB3AdminMutualCategoryRegistConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1�jvalidate( )
        //�@@���N�G�X�g�f�[�^.validate( )���R�[������B 
        l_request.validate();
        
        //1.3�jgetInstanceFrom���O�C�����( )
        //  �Ǘ��҃N���X.getInstanceFrom���O�C�����( )���R�[������B 
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4�jvalidate����(String, boolean)
        //  [validate����()�Ɏw�肷�����] 
        //    �@@�\�J�e�S���R�[�h���@@�\�J�e�S���R�[�h.���M�i�����Ǘ��j 
        //    is�X�V��true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS, true);
        
        //1.5�jget�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6�jget���M�����J�e�S���[(String, String) 
        //  �w�肳�ꂽ���M�����J�e�S���[�R�[�h���瓊�M�����J�e�S���[�I�u�W�F�N�g�̎擾����B 
        //  [get���M�����J�e�S���[�ɓn������] 
        //    �،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g���擾�����،���ЃR�[�h 
        //    ���M�����J�e�S���[�R�[�h�����N�G�X�g�f�[�^.���M�����J�e�S���[�R�[�h
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager) l_tradingModule.getProductManager();
        WEB3MutualProductCategory l_mutualFundProductCategory =
            l_mfProductManager.getMutualFundProductCategory(
                l_strInstitutionCode, 
                l_request.categoryCode);

        //1.7�j���N�G�X�g�f�[�^.�e�J�e�S���[�R�[�h!=null�̏ꍇ
        if (l_request.parentCategoryCode != null)
        {
            //1.7.1�jvalidate���M�����J�e�S���[�K�w(String, String, String)
            //   [validate���M�����J�e�S���[�ɓn������] 
            //�@@ �،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g���擾�����،���ЃR�[�h 
            //�@@ �J�e�S���[�R�[�h�����N�G�X�g�f�[�^.���M�����J�e�S���[�R�[�h
            //   �e�J�e�S���[�R�[�h�����N�G�X�g�f�[�^.�e�J�e�S���[�R�[�h
			String l_strCategoryCode = null;
			if (WEB3ProcessAddChangeDivDef.CHANGE.equals(l_request.procedureDiv))
			{
				l_strCategoryCode = l_request.categoryCode;
			}
			
			l_mfProductManager.validateProductCategorystorey(
				l_strInstitutionCode, 
				l_strCategoryCode,
				l_request.parentCategoryCode);  
        }
        
        List l_lisReturn = new ArrayList();    
        //1.8�jget���M�����J�e�S���[���X�g(String)
        //     �،���ЂɕR�t���S�Ă̓��M�����J�e�S���[���擾����B 
        //    [get���M�����J�e�S���[���X�g�ɓn������] 
        //�@@  �،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g����擾�����،���ЃR�[�h
        List l_lisMutualFundProductCategoryList = null;
        
        //1.9�j���N�G�X�g�f�[�^.�����敪���h�ǉ��h�̏ꍇ
        if (WEB3ProcessAddChangeDivDef.ADD.equals(l_request.procedureDiv))
        {
            //�w�肳�ꂽ���M�����J�e�S���[���A���ɓo�^����Ă��郌�R�[�h�������ꍇ�A
            //[�J�e�S���R�[�h�d���G���[]�Ƃ��ė�O���X���[����B
            //get���M�����J�e�S���[( )�̖߂�l!=null�̏ꍇ�A��O�B�j
            if (l_mutualFundProductCategory != null)
            {
                log.debug("�J�e�S���[�R�[�h�d���G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01278,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            l_lisMutualFundProductCategoryList =
                l_mfProductManager.getMutualFundProductCategoryList(l_strInstitutionCode);
            MutualFundProductCategoryParams l_mfProductCategoryChangeParams = null;   
            for (int i = 0; i < l_lisMutualFundProductCategoryList.size(); i++)
            {
                l_mfProductCategoryChangeParams =
                    (MutualFundProductCategoryParams) l_lisMutualFundProductCategoryList.get(i);
                l_lisReturn.add(l_mfProductCategoryChangeParams);                  
            }
            //1.9.1�j�M�����J�e�S���[( )�̖߂�l��null�̏ꍇ
            l_mutualFundProductCategory = new WEB3MutualProductCategory();
            
            //1.9.1.1�jcreateNew���M�����J�e�S���[(String, String, String)
            //   �V�K�s�ƂȂ铊�M�����J�e�S���[�I�u�W�F�N�g�𐶐�����B 
            //   [createNew���M�����J�e�S���[�ɓn������] 
            //     �،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g���擾�����،���ЃR�[�h 
            //     �J�e�S���[�R�[�h�����N�G�X�g�f�[�^.���M�����J�e�S���[�R�[�h 
            //     �J�e�S���[���́����N�G�X�g�f�[�^.���M�����J�e�S���[����
            l_mutualFundProductCategory.createNewMutualProductCategory(
                l_strInstitutionCode,
                l_request.categoryCode,
                l_request.categoryName);
                
            //1.9.1.2�jset�e�J�e�S���[�R�[�h(String) 
            l_mutualFundProductCategory.setParentCategoryCode(l_request.parentCategoryCode);  
            
            //1.9.1.3�j�o�^��ꗗ�̎擾
            l_lisReturn.add(l_mutualFundProductCategory.getDataSourceObject());
        }
        
        //1.10�j���N�G�X�g�f�[�^.�����敪���h�ύX�h�̏ꍇ
    
        if (WEB3ProcessAddChangeDivDef.CHANGE.equals(l_request.procedureDiv))
        {
            //�w�肳�ꂽ���M�����J�e�S���[�����݂��Ȃ������ꍇ�A
            //[�f�[�^�s����]�Ƃ��ė�O���X���[����B
            //�iget���M�����J�e�S���[( )�̖߂�l��null�̏ꍇ�A��O�B�j
            if (l_mutualFundProductCategory == null)
            {
                log.debug("�f�[�^�s�����G���[�B");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.10.1�jget���M�����J�e�S���[( )�̖߂�l != null�̏ꍇ
            //1.10.1.1�j�J��Ԃ�����
            l_lisMutualFundProductCategoryList =
                l_mfProductManager.getMutualFundProductCategoryList(l_strInstitutionCode);

            MutualFundProductCategoryParams l_mfProductCategoryChangeParams = null;

            for (int i = 0; i < l_lisMutualFundProductCategoryList.size(); i++)
            {
                l_mfProductCategoryChangeParams =
                    (MutualFundProductCategoryParams) l_lisMutualFundProductCategoryList.get(i);
                
                //1.10.1.1.1�j�ύX�Ώۂ���肷��
                //  <�ύX��ꗗ�̎擾>
                //  get���M�����J�e�S���[���X�g( )�̖߂�l�����[�v�����A
                //  �ύX�ΏۂƂȂ������M�����J�e�S���[Params�I�u�W�F�N�g����肵�A
                //  ���̓��M�����J�e�S���[Params�I�u�W�F�N�g�̃v���p�e�B�ɒl�̃Z�b�g���s���B
                if (l_request.categoryCode.equals(l_mfProductCategoryChangeParams.getCategoryCode()))
                {
                    //1.10.1.1.1.1�j set�J�e�S���[����(String)
                    l_mutualFundProductCategory.setCategoryName(l_request.categoryName);
                
                    //1.10.1.1.1.2�jset�e�J�e�S���[�R�[�h(String)
                    l_mutualFundProductCategory.setParentCategoryCode(l_request.parentCategoryCode);
                    
                    l_lisReturn.add(l_mutualFundProductCategory.getDataSourceObject());
                }
                else 
                {
                    l_lisReturn.add(l_mfProductCategoryChangeParams);
                }
            }

        }

        //���N�G�X�g�f�[�^.�����敪=�h�폜�h�̏ꍇ
        if (WEB3ProcessAddChangeDivDef.DELETE.equals(l_request.procedureDiv))
        {
            //get���M�����J�e�S���[( )�̖߂�l == null�̏ꍇ
            //�w�肳�ꂽ���M�����J�e�S���[�����݂��Ȃ������ꍇ�A
            //[�f�[�^�s����]�Ƃ��ė�O���X���[����B
            if (l_mutualFundProductCategory == null)
            {
                log.debug("�f�[�^�s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�f�[�^�s�����G���[�B");
            }

            //get���ʓ��M�����J�e�S���[���X�g(String, String)
            //[get���ʓ��M�����J�e�S���[���X�g�ɓn������]
            //�،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g���擾�����،���ЃR�[�h
            //�J�e�S���[�R�[�h�����N�G�X�g�f�[�^.���M�����J�e�S���[�R�[�h
            List l_lisLowMutualFundProductCategorys =
                l_mfProductManager.getLowMutualFundProductCategoryList(
                    l_strInstitutionCode,
                    l_request.categoryCode);

            //get���ʓ��M�����J�e�S���[���X�g�i�j�̖߂�l��0���łȂ��ꍇ�A[�폜�s��]�Ƃ��ė�O���X���[����B
            if (l_lisLowMutualFundProductCategorys.size() != 0)
            {
                log.debug("�폜�s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03081,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�폜�s�B");
            }

            //get���M����(String, String)
            //[get���M�����ɓn������]
            //�،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g����擾�����،���ЃR�[�h
            //�J�e�S���[�R�[�h�����N�G�X�g�D���M�����J�e�S���[�R�[�h
            List l_lisMutualFundProducts = l_mfProductManager.getMutualFundProduct(
                l_strInstitutionCode,
                l_request.categoryCode);

            //get���M�����i�j�̖߂�l��0���łȂ��ꍇ
            //get���M�����i�j�̖߂�l!=null �̏ꍇ�A
            //[�폜�s��]�Ƃ��ė�O���X���[����B
            if (l_lisMutualFundProducts != null
                && l_lisMutualFundProducts.size() != 0)
            {
                log.debug("�폜�s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03081,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�폜�s�B");
            }

            //�J��Ԃ�����
            //<�폜��ꗗ�̎擾>
            //get���M�����J�e�S���[���X�g( )�̖߂�l�����[�v�����A
            //�폜�ΏۂƂȂ������M�����J�e�S���[Params�I�u�W�F�N�g����肵�A
            //���̓��M�����J�e�S���[Params�I�u�W�F�N�g�����X�g����폜����B
            //�폜�Ώۂ���肷��
            l_lisMutualFundProductCategoryList =
                l_mfProductManager.getMutualFundProductCategoryList(l_strInstitutionCode);
            MutualFundProductCategoryParams l_mfProductCategoryChangeParams = null;
            for (int i = 0; i < l_lisMutualFundProductCategoryList.size(); i++)
            {
                MutualFundProductCategoryRow l_mutualFundProductCategoryRow =
                    (MutualFundProductCategoryRow)l_lisMutualFundProductCategoryList.get(i);
                l_mfProductCategoryChangeParams =
                    new MutualFundProductCategoryParams(l_mutualFundProductCategoryRow);

                if (!l_request.categoryCode.equals(l_mfProductCategoryChangeParams.getCategoryCode()))
                {
                    l_lisReturn.add(l_mfProductCategoryChangeParams);
                }
            }
        }

        //1.11�jcreate���M�����J�e�S���[�ꗗ(List)
        //[create���M�����J�e�S���[�ꗗ�ɓn������]
        //�����J�e�S���[�ꗗ��<�o�^��ꗗ�̎擾>�܂���<�ύX��ꗗ�̎擾>
        //�܂���<�폜��ꗗ�̎擾>�ō쐬�������M�����J�e�S���[Params�I�u�W�F�N�g�̈ꗗ
        WEB3MutualProductCategoryUnit[] l_mutualProductCategoryUnit =
            l_mfProductManager.createMutualFundProductCategoryList(l_lisReturn);
            
        //1.12�jcreate���X�|���X( )
        WEB3AdminMutualCategoryRegistConfirmResponse l_response =
            (WEB3AdminMutualCategoryRegistConfirmResponse) l_request.createResponse();
        l_response.categoryList = l_mutualProductCategoryUnit;
            
        //���X�|���X�f�[�^��ԋp����B    
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    /**
     * (submit���M�����J�e�S���[�o�^)<BR>
     * �����M�������J�e�S���[�o�^�������s���B<BR>
     * BR>
     * �V�[�P���X�}�u�i���M�j�Ǘ��҃J�e�S���[�o�^�����v�Q��<BR>
     * <BR>
     * ======================================================== <BR>
     * ��̈ʒu�Fget���M�����J�e�S���[( )�̖߂�l == null�̏ꍇ<BR>
     * �@@�@@�w�肳�ꂽ���M�����J�e�S���[�����݂��Ȃ������ꍇ�A<BR>
     * �@@�@@[�f�[�^�s����]�Ƃ��ė�O���X���[����B<BR>
     * �@@�@@class: WEB3SystemLayerException <BR>
     * �@@�@@tag�@@: SYSTEM_ERROR_80006 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * ��̈ʒu�Fget���ʓ��M�����J�e�S���[���X�g�i�j�̖߂�l��0���łȂ��ꍇ�A<BR>
     * �@@�@@[�폜�s��]�Ƃ��ė�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@tag�@@: BUSINESS_ERROR_03081 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * ��̈ʒu�Fget���M�����i�j�̖߂�l��0���łȂ��ꍇ<BR>
     *   [�폜�s��]�Ƃ��ė�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@tag�@@: BUSINESS_ERROR_03081 <BR>
     * ======================================================== <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���M�Ǘ��҃J�e�S���[�o�^�������N�G�X�g�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@return WEB3AdminMutualCategoryRegistCompleteResponse
     * @@roseuid 4153F01501AE
     */
    protected WEB3AdminMutualCategoryRegistCompleteResponse submitMutualProductCategoryRegistr(
        WEB3AdminMutualCategoryRegistCompleteRequest l_request) 
            throws WEB3BaseException   
    {
        final String STR_METHOD_NAME =
            "submitMutualProductCategoryRegistr(WEB3AdminMutualCategoryRegistCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1�jvalidate( ) 
        //�@@���N�G�X�g�f�[�^.validate( )���R�[������B 
        l_request.validate(); 
       
        //1.3�jgetInstanceFrom���O�C�����( )
        //�@@�Ǘ��҃N���X.getInstanceFrom���O�C�����( )���R�[������B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
         
        //1.4�j�擾�����Ǘ��҃I�u�W�F�N�g.validate����( )���R�[������B 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS, true);
        
        //1.5�jvalidate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.6�jget�،���ЃR�[�h( )
        //�擾�����Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h( )���R�[������B
        String l_strInstitutionCode = l_admin.getInstitutionCode();
         
        //1.7�jget���M�����J�e�S���[(String, String) 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager) l_tradingModule.getProductManager();
        WEB3MutualProductCategory l_mutualFundProductCategory =
            l_mfProductManager.getMutualFundProductCategory(
                l_strInstitutionCode, 
                l_request.categoryCode);
      
        //1.8�j���N�G�X�g�f�[�^.�e�J�e�S���[�R�[�h != null�̏ꍇ
        //   1.8.1�jvalidate���M�����J�e�S���[�K�w(String, String)
        if (l_request.parentCategoryCode != null)
        {
        	String l_strCategoryCode = null;
			if (WEB3ProcessAddChangeDivDef.CHANGE.equals(l_request.procedureDiv))
			{
				l_strCategoryCode = l_request.categoryCode;
			}
			
            l_mfProductManager.validateProductCategorystorey(
                l_strInstitutionCode, 
				l_strCategoryCode,
                l_request.parentCategoryCode);  
        }
       
        //1.9�j���N�G�X�g�f�[�^.�����敪���h�ǉ��h�̏ꍇ 
        if (WEB3ProcessAddChangeDivDef.ADD.equals(l_request.procedureDiv))
        {
            //�w�肳�ꂽ���M�����J�e�S���[���A���ɓo�^����Ă��郌�R�[�h�������ꍇ�A
            //[�J�e�S���R�[�h�d���G���[]�Ƃ��ė�O���X���[����B
            //�iget���M�����J�e�S���[( )�̖߂�l!=null�̏ꍇ�A��O�B�j
            if (l_mutualFundProductCategory != null)
            {
                log.debug("�J�e�S���[�R�[�h�d���G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01278,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.9.1�jget���M�����J�e�S���[( )�̖߂�l��null�̏ꍇ 
            l_mutualFundProductCategory = new WEB3MutualProductCategory();
              
            //1.9.1.1�j�@@createNew���M�����J�e�S���[(String, String, String)
            //  ���M�����J�e�S���[�I�u�W�F�N�g�𐶐�����B
            //[createNew���M�����J�e�S���[�ɓn������] 
            //  �،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g���擾�����،���ЃR�[�h 
            //  �J�e�S���[�R�[�h�����N�G�X�g�f�[�^.���M�����J�e�S���[�R�[�h 
            //  �J�e�S���[���́����N�G�X�g�f�[�^.���M�����J�e�S���[����  
            l_mutualFundProductCategory.createNewMutualProductCategory(
                l_strInstitutionCode,
                l_request.categoryCode,
                l_request.categoryName);

            //1.9.1.2�jset�e�J�e�S���[�R�[�h(String)
            //[set�e�J�e�S���[�R�[�h�ɓn������] 
            //�e�J�e�S���[�R�[�h�����N�G�X�g�f�[�^.�e�J�e�S���[�R�[�h 
            l_mutualFundProductCategory.setParentCategoryCode(l_request.parentCategoryCode);
            
            //1.9.1.3�j�@@ saveNew���M�����J�e�S���[( ) 
            //INSERT�������s���B 
            l_mutualFundProductCategory.saveNewMutualProductCategory();
        }
        
        //1.10�j���N�G�X�g�f�[�^.�����敪���h�ύX�h�̏ꍇ
        else
        {
            if (WEB3ProcessAddChangeDivDef.CHANGE.equals(l_request.procedureDiv))
            {
                //�w�肳�ꂽ���M�����J�e�S���[�����݂��Ȃ������ꍇ�A
                //[�f�[�^�s����]�Ƃ��ė�O���X���[����B
                //�iget���M�����J�e�S���[( )�̖߂�l��null�̏ꍇ�A��O�B�j
                if (l_mutualFundProductCategory == null)
                {
                    log.debug("�f�[�^�s�����G���[�B");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                
                //1.10.1�jget���M�����J�e�S���[( )�̖߂�l != null�̏ꍇ
                //1.10.1.1�jcreateForUpdateParams( )
                l_mutualFundProductCategory.createForUpdateParams();
                
                //1.10.1.2�jset�J�e�S���[����(String)
                l_mutualFundProductCategory.setCategoryName(l_request.categoryName);
                
                //1.10.1.3�jset�e�J�e�S���[�R�[�h(String)
                l_mutualFundProductCategory.setParentCategoryCode(l_request.parentCategoryCode);
                
                //1.10.1.4�jsave���M�����J�e�S���[( ) 
                l_mutualFundProductCategory.saveMutualProductCategory();
            }
        }

        //���N�G�X�g�f�[�^.�����敪=�h�폜�h�̏ꍇ
        if (WEB3ProcessAddChangeDivDef.DELETE.equals(l_request.procedureDiv))
        {
            //get���M�����J�e�S���[( )�̖߂�l == null�̏ꍇ
            //�w�肳�ꂽ���M�����J�e�S���[�����݂��Ȃ������ꍇ�A
            //[�f�[�^�s����]�Ƃ��ė�O���X���[����B
            if (l_mutualFundProductCategory == null)
            {
                log.debug("�f�[�^�s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�f�[�^�s�����G���[�B");
            }

            //get���ʓ��M�����J�e�S���[���X�g(String, String)
            //[get���ʓ��M�����J�e�S���[���X�g�ɓn������]
            //�،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g���擾�����،���ЃR�[�h
            //�J�e�S���[�R�[�h�����N�G�X�g�f�[�^.���M�����J�e�S���[�R�[�h
            List l_lisLowMutualFundProductCategorys =
                l_mfProductManager.getLowMutualFundProductCategoryList(
                    l_strInstitutionCode,
                    l_request.categoryCode);

            //get���ʓ��M�����J�e�S���[���X�g�i�j�̖߂�l��0���łȂ��ꍇ�A[�폜�s��]�Ƃ��ė�O���X���[����B
            if (l_lisLowMutualFundProductCategorys.size() != 0)
            {
                log.debug("�폜�s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03081,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�폜�s�B");
            }

            //get���M����(String, String)
            //[get���M�����ɓn������]
            //�،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g����擾�����،���ЃR�[�h
            //�J�e�S���[�R�[�h�����N�G�X�g�D���M�����J�e�S���[�R�[�h
            List l_lisMutualFundProducts = l_mfProductManager.getMutualFundProduct(
                l_strInstitutionCode,
                l_request.categoryCode);

            //get���M�����i�j�̖߂�l��0���łȂ��ꍇ
            //get���M�����i�j�̖߂�l!=null �̏ꍇ�A
            //[�폜�s��]�Ƃ��ė�O���X���[����B
            if (l_lisMutualFundProducts != null
                && l_lisMutualFundProducts.size() != 0)
            {
                log.debug("�폜�s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03081,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�폜�s�B");
            }

            //delete���M�����J�e�S���[(String, String)
            //[delete���M�����J�e�S���[�ɓn������]
            //�،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g���擾�����،���ЃR�[�h
            //�J�e�S���[�R�[�h�����N�G�X�g�f�[�^.���M�����J�e�S���[�R�[�h
            l_mutualFundProductCategory.deleteMutualProductCategory(
                l_strInstitutionCode,
                l_request.categoryCode);
        }


        //1.10.11�jcreate���X�|���X( )
        WEB3AdminMutualCategoryRegistCompleteResponse l_response =
            (WEB3AdminMutualCategoryRegistCompleteResponse) l_request.createResponse();
        //���X�|���X�f�[�^��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
