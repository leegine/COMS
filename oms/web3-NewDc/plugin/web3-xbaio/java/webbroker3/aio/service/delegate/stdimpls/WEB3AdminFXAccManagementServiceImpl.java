head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccManagementServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���FX�����Ǘ��T�[�r�X�����N���X(WEB3AdminFXAccManagementServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/25 �����(���u)�V�K�쐬
                 : 2006/02/24 ���_�O (���u) �d�l�ύX�E���f��489
                 : 2006/03/17 �ʉ� (SRA) �d�l�ύX�E���f��521
Revesion History : 2008/05/20 �đo�g (���u) �d�l�ύX�E���f��No.862�ANo.863�ANo.864�ANo.871�ANo.879�ANo.880
                                                     ���f��No.881�ANo.885
Revesion History : 2008/06/23 �đo�g (���u) �d�l�ύX�E���f��No.902�ANo.903
Revesion History : 2008/09/22 ���@@�g (���u) �d�l�ύX�E���f��1020,1037,1078
Revesion History : 2008/11/14 ���� (SCS) �d�l�ύX�E���f��1085
Revesion History : 2009/03/23 �Ԑi (���u) �d�l�ύX�E���f��1134�A1157�A�c�a�X�V�d�l217
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.define.WEB3AioFxAccountInfoDivDef;
import webbroker3.aio.define.WEB3AioInputNumberDivDef;
import webbroker3.aio.define.WEB3AioAccountOpenCompleteDef;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeInputRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeInputResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchConditionRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchConditionResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchResponse;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.service.delegate.WEB3AdminFXAccManagementService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccTypeDef;
import webbroker3.common.define.WEB3ExtConnectSystemCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccOpenDiv;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ���FX�����Ǘ��T�[�r�XImpl) <BR>
 * �Ǘ���FX�����Ǘ��T�[�r�X�����N���X
 * 
 * @@author ����� (���u)
 * @@version 1.0
 */
public class WEB3AdminFXAccManagementServiceImpl implements
    WEB3AdminFXAccManagementService
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccManagementServiceImpl.class);

    /**
     * @@roseuid 41E7934A03B9
     */
    public WEB3AdminFXAccManagementServiceImpl()
    {
    }

    /**
     * (execute) <BR>
     * �Ǘ���FX�����Ǘ��������s���B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B <BR>
     * <BR>
     * ���Ǘ��ҁEFX������񌟍��������̓��N�G�X�g�̏ꍇ <BR>
     * this.get�������͉��()���\�b�h���R�[������B <BR>
     * <BR>
     * ���Ǘ��ҁEFX������񌟍����N�G�X�g�̏ꍇ <BR>
     * this.get��������()���\�b�h���R�[������B <BR>
     * <BR>
     * ���Ǘ��ҁEFX�������ύX���̓��N�G�X�g�̏ꍇ <BR>
     * this.get�ύX���͉��()���\�b�h���R�[������B <BR>
     * <BR>
     * ���Ǘ��ҁEFX�������ύX�m�F���N�G�X�g�̏ꍇ <BR>
     * this.validate�ύX()���\�b�h���R�[������B <BR>
     * <BR>
     * ���Ǘ��ҁEFX�������ύX�������N�G�X�g�̏ꍇ <BR>
     * this.submit�ύX()���\�b�h���R�[������B
     * 
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD377602AC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        String l_strMETHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMETHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(l_strMETHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMETHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        
        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h�� 
        //�Ăѕ�����B 

        //���Ǘ��ҁEFX������񌟍��������̓��N�G�X�g�̏ꍇ 
        if (l_request instanceof WEB3AdminFXAccInfoSearchConditionRequest)
        {
            //�@@this.get�������͉��()���\�b�h���R�[������B 
            l_response =
                this.getCondInputScreen((WEB3AdminFXAccInfoSearchConditionRequest) l_request);
        }
        //���Ǘ��ҁEFX������񌟍����N�G�X�g�̏ꍇ 
        else if (l_request instanceof WEB3AdminFXAccInfoSearchRequest)
        {
            //�@@this.get��������()���\�b�h���R�[������B 
            l_response =
                this.getQueryResult((WEB3AdminFXAccInfoSearchRequest) l_request);
        }
        //���Ǘ��ҁEFX�������ύX���̓��N�G�X�g�̏ꍇ 
        else if (l_request instanceof WEB3AdminFXAccInfoChangeInputRequest)
        {
            //�@@this.get�ύX���͉��()���\�b�h���R�[������B 
            l_response =
                this.getUpdInputScreen((WEB3AdminFXAccInfoChangeInputRequest) l_request);
        }
        //���Ǘ��ҁEFX�������ύX�m�F���N�G�X�g�̏ꍇ 
        else if (l_request instanceof WEB3AdminFXAccInfoChangeConfirmRequest)
        {
            //�@@this.validate�ύX()���\�b�h���R�[������B 
            l_response =
                this.validateChange((WEB3AdminFXAccInfoChangeConfirmRequest) l_request);
        }
        //���Ǘ��ҁEFX�������ύX�������N�G�X�g�̏ꍇ 
        else if (l_request instanceof WEB3AdminFXAccInfoChangeCompleteRequest)
        {
            //�@@this.submit�ύX()���\�b�h���R�[������B
            l_response =
                this.submitChange((WEB3AdminFXAccInfoChangeCompleteRequest) l_request);
        }
       else
        {
            log.debug(l_strMETHOD_NAME
                    + "���N�G�X�g�f�[�^���^�C�v�s��, but is " + l_request.getClass().getName());
            log.exiting(l_strMETHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                l_strMETHOD_NAME);
        }
        log.exiting(l_strMETHOD_NAME);
        return l_response;
    }

    /**
     * (get�������͉��) <BR>
     * (�Ǘ���FX�����Ǘ�)������񌟍��������͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ���FX�����Ǘ��T�[�r�X)get�������͉�ʁv�Q��
     * 
     * @@param l_request - �Ǘ��ҁEFX������񌟍��������̓��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccInfoSearchConditionResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD380D022F
     */
    protected WEB3AdminFXAccInfoSearchConditionResponse getCondInputScreen(
        WEB3AdminFXAccInfoSearchConditionRequest l_request)
        throws WEB3BaseException
    {
        String l_strMETHOD_NAME = "getCondInputScreen(WEB3AdminFXAccInfoSearchConditionRequest l_request)";
        log.entering(l_strMETHOD_NAME);

        //1.1 validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //1.2  getInstanceFrom���O�C�����( )
        // ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3 �Ǘ��Ҍ����̃`�F�b�N���s���B 
        // [����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ב֕ۏ؋��Ǘ�(�����Ǘ��E�����J�݊Ǘ�) 
        // is�X�V�F�@@false(�X�V�Ȃ�) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
            false);
        
        // 1.4 ���X�����̃`�F�b�N���s���B
        // [����] 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h�ꗗ
        l_admin.validateBranchPermission(l_request.branchCodeList);
        
        // 1.5 createResponse
        WEB3AdminFXAccInfoSearchConditionResponse l_response = 
            (WEB3AdminFXAccInfoSearchConditionResponse)l_request.createResponse();
        
        log.exiting(l_strMETHOD_NAME);
        return l_response;
    }

    /**
     * (get��������) <BR>
     * (�Ǘ���FX�����Ǘ�)������񌟍��������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ���FX�����Ǘ��T�[�r�X)get�������ʁv�Q�� <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u�ב֕ۏ؋��T�[�r�X���f���i�Ǘ��ҁj/ �Ǘ���FX�����Ǘ��v) <BR>
     * (get��������)getQueryResult <BR>
     * : 1.8 getFX�ڋq(String, String[], String)null���ԋp���ꂽ �̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01808 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - �Ǘ��ҁEFX������񌟍����N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccInfoSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD380D024E
     */
    protected WEB3AdminFXAccInfoSearchResponse getQueryResult(
        WEB3AdminFXAccInfoSearchRequest l_request)
        throws WEB3BaseException
    {
        String l_strMETHOD_NAME = "getQueryResult(WEB3AdminFXAccInfoSearchRequest l_request)";
        log.entering(l_strMETHOD_NAME);

        // 1 ) ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        // 2 ) ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) �Ǘ��Ҍ����̃`�F�b�N���s���B 
        // [����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ב֕ۏ؋��Ǘ�(�����Ǘ��E�����J�݊Ǘ�) 
        // is�X�V�F�@@false(�X�V�Ȃ�) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
            false);
        
        // 4 ) ���X�����̃`�F�b�N���s���B
        // [����] 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        l_admin.validateBranchPermission(l_request.branchCode);
        
        // 5 ) �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        // 6 ) ����������������쐬����B 
        // [����] 
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h 
        // ���͔ԍ��敪�F�@@���N�G�X�g�f�[�^.���͔ԍ��敪 
        // ���͔ԍ��F�@@���N�G�X�g�f�[�^.���͔ԍ�
        // FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        String l_strWhere = 
            this.createQueryString(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.inputNumberDiv,
                l_request.inputNumber,
                l_request.fxSystemCode);
        
        // 7 ) ���������f�[�^�R���e�i���쐬����B 
        // [����] 
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h 
        // ���͔ԍ��敪�F�@@���N�G�X�g�f�[�^.���͔ԍ��敪 
        // ���͔ԍ��F�@@���N�G�X�g�f�[�^.���͔ԍ�
        // FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        String[] l_arrVars = 
            this.createQueryContainer(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.inputNumberDiv,
                l_request.inputNumber,
                l_request.fxSystemCode);
        
        // 8 )  getFX�ڋq(String, String[], String)
        WEB3FXDataControlService l_controlService = 
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class); 
        
        // FX�ڋq���擾����B
        // [����] 
        // ��������������F�@@create��������������()�̖߂�l 
        // ���������Ł[���R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l 
        // �\�[�g�����F�@@null
        FxAccountParams[] l_fxAccountParamses = 
            l_controlService.getFXAccounts(l_strWhere, l_arrVars, null);
        
        // getFX�ڋq�̖߂�l��null�ł��� �̏ꍇ
        if (l_fxAccountParamses == null)
        {
            log.debug("FX�ڋq���擾�ł��Ȃ�");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01808,
                this.getClass().getName() + "." + l_strMETHOD_NAME,
                "FX�ڋq���擾�ł��Ȃ�");
        }
        
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
		//�g���A�J�E���g�}�l�[�W���擾����    
		WEB3GentradeAccountManager l_accountManager =
			(WEB3GentradeAccountManager) l_finApp.getAccountManager();
		
		boolean l_mainAccountFlg = true;	
		try
		{
			// 9 ) �ڋq�C���X�^���X���擾����B 
			// [����] 
			// �،���ЃR�[�h�F getFX�ڋq()�̖߂�l.�،���ЃR�[�h 
			// ���X�R�[�h�F getFX�ڋq()�̖߂�l.���X�R�[�h 
			// �����R�[�h�F getFX�ڋq()�̖߂�l.�ڋq�R�[�h 
			WEB3GentradeMainAccount l_mainAccount = 
				l_accountManager.getMainAccount(
					l_fxAccountParamses[0].getInstitutionCode(),
					l_fxAccountParamses[0].getBranchCode(),
					l_fxAccountParamses[0].getAccountCode()
					);			
		}
		catch(WEB3BaseException l_ex)
		{
			l_mainAccountFlg = false;			
		}
		// update end
       
        
        // 9 ) createFX�������ꗗ
        //FX�������ꗗ���쐬����B 
        //[����] 
        //�،���ЃR�[�h�F�@@getFX�ڋq()�̖߂�l.�،���ЃR�[�h 
        //���X�R�[�h�F�@@getFX�ڋq()�̖߂�l.���X�R�[�h 
        //�ڋq�R�[�h�F�@@getFX�ڋq()�̖߂�l.�ڋq�R�[�h
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        WEB3FXAccInformationUnit[] l_fxAccINformationUnits = 
            l_controlService.createFXAccInformationUnits(
                l_fxAccountParamses[0].getInstitutionCode(),
                l_fxAccountParamses[0].getBranchCode(),
                l_fxAccountParamses[0].getAccountCode(),
                l_request.fxSystemCode);
        
        // 10 ) createResponse( )
        // ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFXAccInfoSearchResponse l_response = 
            (WEB3AdminFXAccInfoSearchResponse)l_request.createResponse();
        
        // 11 )�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        // ��FX�ڋq�́AgetFX�ڋq()�̖߂�l��0�Ԗڂ̗v�f���g�p����B
        //(�{�@@�\�ł͕K����ӂɂȂ����������
        
        // ���X�|���X�f�[�^.�ڋq�R�[�h       ���@@FX�ڋq.�ڋq�R�[�h
        String l_strAccountCodeToSet = l_fxAccountParamses[0].getAccountCode();
        if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() > 6)
        {
            l_strAccountCodeToSet = l_fxAccountParamses[0].getAccountCode().substring(0, 6);
        }
        l_response.accountCode = l_strAccountCodeToSet;
        //���X�|���X�f�[�^.(FX)���O�C��ID       ���@@FX�ڋq.FX���O�C��ID
        l_response.fxLoginId = l_fxAccountParamses[0].getFxLoginId() + "";
        //���X�|���X�f�[�^.(FX)���O(��)      ���@@FX�ڋq.���O(��)
        l_response.fxFirstName = l_fxAccountParamses[0].getFxFirstName();
        //���X�|���X�f�[�^.(FX)���O(��)      ���@@FX�ڋq.���O(��)
        l_response.fxLastName = l_fxAccountParamses[0].getFxLastName();
        //���X�|���X�f�[�^.(FX)���[���A�h���X    ���@@FX�ڋq.FX���[���A�h���X
        l_response.fxMailAddress = l_fxAccountParamses[0].getFxMailAddress();
        //���X�|���X�f�[�^.FX�������ꗗ   ���@@createFX�������ꗗ()�̖߂�l
        l_response.fxAccInformationList = l_fxAccINformationUnits;
        //���X�|���X�f�[�^.�����J�ݏ󋵋敪   ���@@FX�ڋq.FX�����敪
        //get�ڋq()�ɂė�O�����������ꍇ�́A�u99(��������)�v���Z�b�g
        if ( l_mainAccountFlg)
        {
			l_response.accountOpenStatusDiv = l_fxAccountParamses[0].getFxAccountDiv();
        }
        else
        {
			l_response.accountOpenStatusDiv = WEB3AioAccountOpenCompleteDef.OPEN_DELETE;
        }

        //���X�|���X�f�[�^.FX�V�X�e���R�[�h = FX�ڋq.FX�V�X�e���R�[�h
        l_response.fxSystemCode = l_fxAccountParamses[0].getFxSystemCode();

        log.exiting(l_strMETHOD_NAME);
        return l_response;
    }

    /**
     * (get�ύX���͉��) <BR>
     * (�Ǘ���FX�����Ǘ�)�������ύX���͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ���FX�����Ǘ��T�[�r�X)get�ύX���͉�ʁv�Q�� <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u�ב֕ۏ؋��T�[�r�X���f���i�Ǘ��ҁj/ �Ǘ���FX�����Ǘ��v) <BR>
     * �iget�ύX���͉�ʁjgetUpdInputScreen <BR>
     * : 1.6 getFX�ڋq(String, String, String)�߂�l��null �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01808 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - �Ǘ��ҁEFX�������ύX���̓��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccInfoChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD380D025D
     */
    protected WEB3AdminFXAccInfoChangeInputResponse getUpdInputScreen(
        WEB3AdminFXAccInfoChangeInputRequest l_request)
        throws WEB3BaseException
    {
        String l_strMETHOD_NAME = "getUpdInputScreen(WEB3AdminFXAccInfoChangeInputRequest l_request)";
        log.entering(l_strMETHOD_NAME);

        // 1 ) ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        // 2 ) ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) �Ǘ��Ҍ����̃`�F�b�N���s���B 
        // [����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ב֕ۏ؋��Ǘ�(�����Ǘ��E�����J�݊Ǘ�) 
        // is�X�V�F�@@false(�X�V�Ȃ�) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
            false);
        
        // 4 ) ���X�����̃`�F�b�N���s���B
        // [����] 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        l_admin.validateBranchPermission(l_request.branchCode);
        
        // 5 ) �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        WEB3FXDataControlService l_controlService = 
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class); 

        //get��Е�FX�V�X�e������
        // (�،���ЃR�[�h : String, ���X�R�[�h : String, FX�V�X�e���R�[�h : String)
        //[�����̐ݒ�]
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        // FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        CompFxConditionParams l_compFxConditionParams;
        try
        {
            l_compFxConditionParams = l_controlService.getCompFxCondition(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(l_strMETHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMETHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //getFX�ڋq(String, String, String, String)
        // FX�ڋq���擾����B
        // [����] 
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h 
        // FX�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        // �ڋq�R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
        FxAccountParams l_fxAccountParams = null;
        try
        {
            l_fxAccountParams = l_controlService.getFXAccount(l_strInstitutionCode, 
                l_request.branchCode, l_compFxConditionParams.getFxSystemCode(),
                l_request.accountCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("FX�ڋq���擾�ł��Ȃ�", l_ex);
            l_fxAccountParams = null;
        }
        
        // getFX�ڋq�̖߂�l��null�ł��� �̏ꍇ
        if (l_fxAccountParams == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01808,
                this.getClass().getName() + "." + l_strMETHOD_NAME,
                "FX�ڋq���擾�ł��Ȃ�");
        }
        
        //createFX�������ꗗ(String, String, String, String)
        //FX�������ꗗ���쐬����B 
        //[����] 
        //�،���ЃR�[�h�F�@@getFX�ڋq()�̖߂�l.�،���ЃR�[�h 
        //���X�R�[�h�F�@@getFX�ڋq()�̖߂�l.���X�R�[�h 
        //�ڋq�R�[�h�F�@@getFX�ڋq()�̖߂�l.�ڋq�R�[�h
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        WEB3FXAccInformationUnit[] l_fxAccINformationUnits = 
            l_controlService.createFXAccInformationUnits(
                l_fxAccountParams.getInstitutionCode(),
                l_fxAccountParams.getBranchCode(),
                l_fxAccountParams.getAccountCode(),
                l_request.fxSystemCode);
        
        // 10 ) createResponse( )
        // ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFXAccInfoChangeInputResponse l_response = 
            (WEB3AdminFXAccInfoChangeInputResponse)l_request.createResponse();
        
        // 11 )�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        
        //���X�|���X�f�[�^.���X�R�[�h        ���@@FX�ڋq.���X�R�[�h
        l_response.branchCode = l_fxAccountParams.getBranchCode();
        //���X�|���X�f�[�^.�ڋq�R�[�h       ���@@FX�ڋq.�ڋq�R�[�h
        String l_strAccountCodeToSet = l_fxAccountParams.getAccountCode();
        if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() > 6)
        {
            l_strAccountCodeToSet = l_fxAccountParams.getAccountCode().substring(0, 6);
        }
        l_response.accountCode = l_strAccountCodeToSet;
        //���X�|���X�f�[�^.(FX)���O�C��ID       ���@@FX�ڋq.FX���O�C��ID
        l_response.fxLoginId = l_fxAccountParams.getFxLoginId() + "";
        //���X�|���X�f�[�^.(FX)���O(��)      ���@@FX�ڋq.���O(��)
        l_response.fxFirstName = l_fxAccountParams.getFxFirstName();
        //���X�|���X�f�[�^.(FX)���O(��)      ���@@FX�ڋq.���O(��)
        l_response.fxLastName = l_fxAccountParams.getFxLastName();
        //���X�|���X�f�[�^.(FX)���[���A�h���X    ���@@FX�ڋq.FX���[���A�h���X
        l_response.fxMailAddress = l_fxAccountParams.getFxMailAddress();
        //���X�|���X�f�[�^.FX�������ꗗ   ���@@createFX�������ꗗ()�̖߂�l
        l_response.fxAccInformationList = l_fxAccINformationUnits;
        //���X�|���X�f�[�^.�����J�ݏ󋵋敪   ���@@FX�ڋq.FX�����敪
        l_response.accountOpenStatusDiv = l_fxAccountParams.getFxAccountDiv();

        log.exiting(l_strMETHOD_NAME);
        return l_response;
    }

    /**
     * (validate�ύX) <BR>
     * (�Ǘ���FX�����Ǘ�)�������ύX�m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ���FX�����Ǘ��T�[�r�X)validate�ύX�v�Q��
     * @@param l_request - �Ǘ��ҁEFX�������ύX�m�F���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccInfoChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD380D027D
     */
    protected WEB3AdminFXAccInfoChangeConfirmResponse validateChange(
        WEB3AdminFXAccInfoChangeConfirmRequest l_request)
        throws WEB3BaseException
    {
        String l_strMETHOD_NAME = "validateChange(WEB3AdminFXAccInfoChangeConfirmRequest l_request)";
        log.entering(l_strMETHOD_NAME);

        // 1 ) ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        // 2 ) ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) �Ǘ��Ҍ����̃`�F�b�N���s���B 
        // [����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ב֕ۏ؋��Ǘ�(�����Ǘ��E�����J�݊Ǘ�) 
        // is�X�V�F�@@true(�X�V����) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
            true);
        
        // 4 ) ���X�����̃`�F�b�N���s���B
        // [����] 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        l_admin.validateBranchPermission(l_request.branchCode);
        
        // 5 )  (*)���N�G�X�g�f�[�^.�X�V�チ�[���A�h���X != null�̏ꍇ
        // �\�\�\�\�\�X�e�[�v1 )
        if(l_request.updatedMailAddress != null)
        {
            // 5.1) isMailAddress(String)
            // ���[���A�h���X�Ɏg�p�ł��镶���̂݃Z�b�g����Ă��邩���`�F�b�N����B
            // [����] 
            // l_str�F�@@���N�G�X�g�f�[�^.�X�V�チ�[���A�h���X
            if (!WEB3StringTypeUtility.isMailAddress(l_request.updatedMailAddress))
            {
                log.debug("���[���A�h���X�`�F�b�N�G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                    this.getClass().getName() + "." + l_strMETHOD_NAME,
                    "���N�G�X�g�f�[�^.FX���[���A�h���X���K�؂ȓ��e" + 
                    "���N�G�X�g�f�[�^.FX���[���A�h���X = " + l_request.updatedMailAddress); 
            }
        }
        
        // 6 ) createResponse( )
        // ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFXAccInfoChangeConfirmResponse l_response = 
            (WEB3AdminFXAccInfoChangeConfirmResponse)l_request.createResponse();
        
        log.exiting(l_strMETHOD_NAME);
        return l_response;
    }

    /**
     * (submit�ύX) <BR>
     * (�Ǘ���FX�����Ǘ�)�������ύX�����������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ���FX�����Ǘ��T�[�r�X)submit�ύX�v�Q�� <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u�ב֕ۏ؋��T�[�r�X���f���i�Ǘ��ҁj/ �Ǘ���FX�����Ǘ��v) <BR>
     * �isubmit�ύX�jsubmitUpd <BR>
     * : 1.9 getFX�ڋq(String, String, String)�߂�l��null �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01808 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - �Ǘ��ҁEFX�������ύX�������N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccInfoChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD380D029C
     */
    protected WEB3AdminFXAccInfoChangeCompleteResponse submitChange(
        WEB3AdminFXAccInfoChangeCompleteRequest l_request)
        throws WEB3BaseException
    {
        String l_strMETHOD_NAME = "submitChange(WEB3AdminFXAccInfoChangeCompleteRequest l_request)";
        log.entering(l_strMETHOD_NAME);

        // 1.1 ) ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        // 1.2 ) ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3 ) �Ǘ��Ҍ����̃`�F�b�N���s���B 
        // [����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ב֕ۏ؋��Ǘ�(�����Ǘ��E�����J�݊Ǘ�) 
        // is�X�V�F�@@true(�X�V����) 
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
                true);
        
        // 1.4 ) ���X�����̃`�F�b�N���s���B 
        // [����] 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.FX��������.���X�R�[�h
        l_admin.validateBranchPermission(l_request.branchCode);
        
        // 1.5 ) �Ïؔԍ��̃`�F�b�N���s���B 
        // [����] 
        // �p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);

        // 1.6 ) �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        // 1.7 ) �Ǘ��҃R�[�h���擾����B
        String l_strAdminCode =  l_admin.getAdministratorCode();
        
        // 1.8 )  (*)���N�G�X�g�f�[�^.�X�V�チ�[���A�h���X != null�̏ꍇ
        // �\�\�\�\�\�X�e�[�v1 )
        if(l_request.updatedMailAddress != null)
        {
            // 1.8.1) isMailAddress(String)
            // ���[���A�h���X�Ɏg�p�ł��镶���̂݃Z�b�g����Ă��邩���`�F�b�N����B
            // [����] 
            // l_str�F�@@���N�G�X�g�f�[�^.�X�V�チ�[���A�h���X
            if (!WEB3StringTypeUtility.isMailAddress(l_request.updatedMailAddress))
            {
                log.debug("���[���A�h���X�`�F�b�N�G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                    this.getClass().getName() + "." + l_strMETHOD_NAME,
                    "���N�G�X�g�f�[�^.FX���[���A�h���X���K�؂ȓ��e" + 
                    "���N�G�X�g�f�[�^.FX���[���A�h���X = " + l_request.updatedMailAddress); 
            }
        }
        
        //getFX�ڋq(String, String, String, String)
        WEB3FXDataControlService l_controlService = 
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class); 

        //get��Е�FX�V�X�e������(String, String, String)
        //[�����̐ݒ�]
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        CompFxConditionParams l_compFxConditionParams;
        try
        {
            l_compFxConditionParams = l_controlService.getCompFxCondition(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(l_strMETHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMETHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // FX�ڋq���擾����B
        // [����] 
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h 
        // FX�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        // �ڋq�R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
        // 
        FxAccountParams l_fxAccountParams = null;
        try
        {
            l_fxAccountParams = l_controlService.getFXAccount(l_strInstitutionCode, 
                l_request.branchCode, l_compFxConditionParams.getFxSystemCode(), l_request.accountCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("FX�ڋq���擾�ł��Ȃ�", l_ex);
            l_fxAccountParams = null;
        }
        
        // getFX�ڋq�̖߂�l��null�ł��� �̏ꍇ
        if (l_fxAccountParams == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01808,
                this.getClass().getName() + "." + l_strMETHOD_NAME,
                "FX�ڋq���擾�ł��Ȃ�");
        }

        // ���N�G�X�g�f�[�^.�X�V������J�ݏ󋵋敪 != null�̏ꍇ
        if(l_request.updatedAccountOpenStatusDiv != null){
            //updateFX�ڋq(FX�ڋqParams, String, String)
            //FX�ڋq�e�[�u���̌����J�ݏ󋵋敪���X�V����B 
            //[����] 
            //FX�ڋqParams�F�@@getFX�ڋq()�̖߂�l 
            //�X�V������J�ݏ󋵋敪�F�@@���N�G�X�g�f�[�^.�X�V������J�ݏ󋵋敪 
            //�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
            l_controlService.updateFXAccount(l_fxAccountParams, 
                    l_request.updatedAccountOpenStatusDiv,
                    l_strAdminCode);
        }
        
        // ���N�G�X�g�f�[�^.�X�V�チ�[���A�h���X != null�̏ꍇ
        if(l_request.updatedMailAddress != null)
        {
            //get��Е�FX�V�X�e�������̖߂�l�O���ڑ��V�X�e���R�[�h=="01:GFT"�̏ꍇ
        	if (WEB3ExtConnectSystemCodeDef.GFT.equals(
        	    l_compFxConditionParams.getExtConnectSystemCode()))
        	{
                //FX�V�X�e���R�[�h�ꗗ���擾����B
                //[�����̐ݒ�]
                //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
                //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
                ArrayList l_lisFxSystemCodeLists =
                    l_controlService.getGFTFxSystemCodeLists(
                        l_strInstitutionCode,
                        l_request.branchCode);

                //updateFX�ڋq(FX�ڋqParams, String, String, ArrayList)
                //FX�ڋq�e�[�u���̃��[���A�h���X���X�V����B
                //[����]
                //FX�ڋqParams�F�@@getFX�ڋq()�̖߂�l
                //�X�V�チ�[���A�h���X�F�@@���N�G�X�g�f�[�^.�X�V�チ�[���A�h���X
                //�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
                //FX�V�X�e���R�[�h�ꗗ�F�@@getGFTFX�V�X�e���R�[�h�ꗗ()�̖߂�l
                l_controlService.updateFXAccount(
                    l_fxAccountParams,
                    l_request.updatedMailAddress,
                    l_strAdminCode,
                    l_lisFxSystemCodeLists);
        	}
        	//get��Е�FX�V�X�e�������̖߂�l�O���ڑ��V�X�e���R�[�h=="01:GFT"�ȊO�̏ꍇ
        	else
        	{
                //updateFX�ڋq���[���A�h���X(FX�ڋqParams, String, String)
                //[����]
                //�@@FX�ڋqParams�F�@@getFX�ڋq()�̖߂�l
                //�@@�X�V�チ�[���A�h���X�F�@@���N�G�X�g�f�[�^.�X�V�チ�[���A�h���X
                //�@@�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
                l_controlService.updateFXAccountMailAddress(
                    l_fxAccountParams,
                    l_request.updatedMailAddress,
                    l_strAdminCode);
        	}
        }
        
        // 1.11 )  (*)���N�G�X�g�f�[�^.�X�V��FX�������ꗗ != null�̏ꍇ
        // �\�\�\�\�\�X�e�[�v1 )
        
        if (l_request.updatedFxAccInformationList != null)
        {
			// 1.11.1 ) (*)���N�G�X�g�f�[�^.�X�V��FX�������ꗗ�̗v�f����Loop����
			for (int i = 0; i < l_request.updatedFxAccInformationList.length; i ++)
			{
				WEB3FXAccInformationUnit l_fxAccInfoUnit = 
					l_request.updatedFxAccInformationList[i];
            
				FxAccountCodeParams l_fxAccountCodeParams = null;
            
				//getFX�����ԍ�(String, String, String, String, String)
				//FX�����ԍ����擾����B 
				//[����] 
				//�،���ЃR�[�h�F�@@getFX�ڋq()�̖߂�l.�،���ЃR�[�h 
				//���X�R�[�h�F�@@getFX�ڋq()�̖߂�l.���X�R�[�h 
				//�ڋq�R�[�h�F�@@getFX�ڋq()�̖߂�l.�ڋq�R�[�h 
				//�R�[�X�敪�F�@@�����Ώۂ�FX�������.�R�[�X�敪
                //FX�V�X�e���R�[�h�F�@@getFX�ڋq()�̖߂�l.FX�V�X�e���R�[�h
				try
				{
					l_fxAccountCodeParams = l_controlService.getFXAccountCode(
						l_fxAccountParams.getInstitutionCode(),
						l_fxAccountParams.getBranchCode(),
						l_fxAccountParams.getAccountCode(),
						l_fxAccInfoUnit.fxCourseDiv,
                        l_fxAccountParams.getFxSystemCode());
				}
				catch (NotFoundException l_ex)
				{
					log.debug("NotFoundException: ", l_ex);
					throw new WEB3SystemLayerException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80005,
						this.getClass().getName() + "." + l_strMETHOD_NAME,
						l_ex.getMessage(),
						l_ex);
				}
            
				// 1.11.1.2 ) updateFX�����ԍ�(FX�����ԍ�Params, String, String)
				//FX�����ԍ��e�[�u�����X�V����B 
				// [����] 
				// FX�����ԍ�Params�F�@@getFX�����ԍ�()�̖߂�l 
				// �X�V������ԍ��F�@@�����Ώۂ�FX�������.�����ԍ� 
				// �X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
				l_controlService.updateFXAccountCode(l_fxAccountCodeParams, 
					l_fxAccInfoUnit.fxAccountCode, l_strAdminCode);
			}
        }

        //���N�G�X�g�f�[�^.�����敪 == "�����J�ݏ󋵕ύX"�̏ꍇ
        if (WEB3AioFxAccountInfoDivDef.ACCOUNT_OPEN_STATUS.equals(l_request.operationDiv))
        {
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

            //�ڋq�I�u�W�F�N�g���擾����B
            //[����]
            //�،���ЃR�[�h�F�@@getFX�ڋq()�̖߂�l.�،���ЃR�[�h
            //���X�R�[�h�F�@@getFX�ڋq()�̖߂�l.���X�R�[�h
            //�����R�[�h�F�@@getFX�ڋq()�̖߂�l.�ڋq�R�[�h
            WEB3GentradeMainAccount l_mainAccount =
                l_accountManager.getMainAccount(
                    l_fxAccountParams.getInstitutionCode(),
                    l_fxAccountParams.getBranchCode(),
                    l_fxAccountParams.getAccountCode());

            //FX�����J�݋敪���擾����B
            //[����]
            //�X�V������J�ݏ󋵋敪�F�@@���N�G�X�g�f�[�^.�X�V������J�ݏ󋵋敪
            String l_strFXAccOpenDiv =
                l_controlService.getFXAccountOpenDiv(l_request.updatedAccountOpenStatusDiv);

            //get��Е�FX�V�X�e�������̖߂�l.�������=="01:(FX)�h�̏ꍇ
            if (WEB3AccTypeDef.FX.equals(l_compFxConditionParams.getAccType()))
            {
                //�ڋq��FX�����J�݋敪���X�V����B
                //[����]
                //�@@�،���ЃR�[�h�F�@@getFX�ڋq()�̖߂�l.�،���ЃR�[�h
                //�@@���X�R�[�h�F�@@getFX�ڋq()�̖߂�l.���X�R�[�h
                //�@@�ڋq�R�[�h�F�@@getFX�ڋq()�̖߂�l.�ڋq�R�[�h
                //�@@�X�V��FX�����J�݋敪�F�@@getFX�����J�݋敪()�̖߂�l
                //�@@�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
                l_controlService.updateFXAccountOpenDiv(
                    l_fxAccountParams.getInstitutionCode(),
                    l_fxAccountParams.getBranchCode(),
                    l_fxAccountParams.getAccountCode(),
                    l_strFXAccOpenDiv,
                    l_strAdminCode);
            }

            //�����J�݋敪�e�[�u��.�����J�݋敪���X�V����B
            //[����]
            //�@@����ID�F�@@get�ڋq()�̖߂�l.����ID
            //�@@������ʁFget��Е�FX�V�X�e�������i�j�̖߂�l.�������
            //�@@�����J�݋敪�F�@@getFX�����J�݋敪()�̖߂�l
            //�@@�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
            WEB3GentradeAccOpenDiv l_genAccOpenDiv = new WEB3GentradeAccOpenDiv();
            l_genAccOpenDiv.updateAccOpenDiv(
                l_mainAccount.getAccountId(),
                l_compFxConditionParams.getAccType(),
                l_strFXAccOpenDiv,
                l_strAdminCode);
        }

        // 1.13 ) createResponse( )
        // ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFXAccInfoChangeCompleteResponse l_response = 
            (WEB3AdminFXAccInfoChangeCompleteResponse)l_request.createResponse();
        
        log.exiting(l_strMETHOD_NAME);
        return l_response;
    }

    /**
     * (create��������������) <BR>
     * ����������������쐬����B <BR>
     * <BR>
     * �P�j��̌�������������(�FString)�𐶐�����B <BR>
     * <BR>
     * �Q�j�،���Џ��������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += " institution_code = ? "<BR>
     * <BR>
     * �R�j���X���������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and branch_code = ? "<BR>
     * <BR>
     * �S�j���͂��ꂽ�ԍ������������ɒǉ�����B <BR>
     * �p�����[�^.���͔ԍ��敪���A <BR>
     * ["FX���O�C��ID"�̏ꍇ] <BR>
     * FX���O�C��ID��������������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and fx_login_id = ? "<BR>
     * <BR>
     * ["FX�����ԍ�" or "�ڋq�R�[�h"�̏ꍇ] <BR>
     * �ڋq�R�[�h��������������������ɒǉ�����B <BR>
     * ���擪6byte�Ŕ�r����B <BR>
     * <BR>
     * �������������� += "and substr(account_code, 0, 6) = ? "<BR>
     * <BR>
     * �T�jFX�V�X�e���R�[�h<BR>
     * <BR>
     * �@@�p�����[�^.FX�V�X�e���R�[�h != null�̏ꍇFX�V�X�e���R�[�h�����������ɒǉ�����B<BR>
     * �@@�������������� += "and fx_system_code= ? "<BR>
     * <BR>
     * �U�j�쐬�������������������ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strInputNumberDiv - ���͔ԍ��敪
     * 1�FFX���O�C��ID 2�FFX�����ԍ� 3�F�ڋq�R�[�h
     * @@param l_strInputNumber - ���͔ԍ�
     * ���͔ԍ��敪��1�iFX���O�C��ID�j�̏ꍇ�A<BR>
     * FX�j���O�C��ID ���͔ԍ��敪��2�iFX�����ԍ��j�̏ꍇ�A<BR>
     * FX�j�����ԍ� ���͔ԍ��敪��3�i�ڋq�R�[�h�j�̏ꍇ�A�ڋq�R�[�h
     * @@param l_strFxSystemCode - FX�V�X�e���R�[�h
     * @@return String
     * @@roseuid 41BECB5F00BE
     */
    protected String createQueryString(String l_strInstitutionCode,
        String l_strBranchCode, String l_strInputNumberDiv,
        String l_strInputNumber, String l_strFxSystemCode)
    {
        String l_strMETHOD_NAME = "createQueryString(String l_strInstitutionCode," +
            "String l_strBranchCode, String l_strInputNumberDiv,String l_strInputNumber, " +
            "String l_strFxSystemCode)";
        log.entering(l_strMETHOD_NAME);

        //����������������쐬����B 

        //�P�j��̌�������������(�FString)�𐶐�����B 
        String l_strQueryString = "";
        
        //�Q�j�،���Џ��������������ɒǉ�����B 
        //�@@�������������� += " institution_code = ? " 
        l_strQueryString += " institution_code = ? ";

        //�R�j���X���������������ɒǉ�����B 
        //�@@�������������� += "and branch_code = ? " 
        l_strQueryString += "and branch_code = ? ";

        //�S�j���͂��ꂽ�ԍ������������ɒǉ�����B 
        //�@@�p�����[�^.���͔ԍ��敪���A 
        //�@@["FX���O�C��ID"�̏ꍇ] 
        //�@@�@@FX���O�C��ID��������������������ɒǉ�����B 
        if (WEB3AioInputNumberDivDef.FX_LOGINID.equals(l_strInputNumberDiv))
        {
            //�@@�@@�������������� += "and fx_login_id = ? "
            l_strQueryString += "and fx_login_id = ? ";
        }

        //�@@["FX�����ԍ�" or "�ڋq�R�[�h"�̏ꍇ] 
        //�@@�@@�ڋq�R�[�h��������������������ɒǉ�����B 
        //�@@�@@���擪6byte�Ŕ�r����B 
        if (WEB3AioInputNumberDivDef.FX_ACCOUNT_CODE.equals(l_strInputNumberDiv)
            || WEB3AioInputNumberDivDef.ACCOUNT_CODE.equals(l_strInputNumberDiv))
        {
            //�@@�@@�������������� += "and substr(account_code, 0, 6) = ? "
            l_strQueryString += "and substr(account_code, 0, 6) = ? ";
        }

        //�T�jFX�V�X�e���R�[�h
        //�p�����[�^.FX�V�X�e���R�[�h != null�̏ꍇFX�V�X�e���R�[�h�����������ɒǉ�����B
        //�@@�������������� += "and fx_system_code= ? "
        if (l_strFxSystemCode != null)
        {
            l_strQueryString += " and fx_system_code = ? ";
        }

        //�쐬�������������������ԋp����B 
        log.exiting(l_strMETHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (create���������f�[�^�R���e�i) <BR>
     * ����������������쐬����B <BR>
     * <BR>
     * �P�jArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j�،���Џ�����ǉ�����B <BR>
     * �p�����[�^.�،���ЃR�[�h��ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �R�j���X������ǉ�����B <BR>
     * �p�����[�^.���X�R�[�h��ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �S�j���͂��ꂽ�ԍ���ǉ�����B <BR>
     * [�p�����[�^.���͔ԍ��敪 == "FX�����ԍ�"�̏ꍇ] <BR>
     * FX�f�[�^����T�[�r�XImpl.get�ڋq�R�[�h()�̖߂�l��<BR>
     * �擪6byte��ArrayList�ɒǉ�����B <BR>
     * <BR>
     * [get�ڋq�R�[�h()�ɃZ�b�g����p�����[�^] <BR>
     * �،���ЃR�[�h�F �p�����[�^.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F �p�����[�^.���X�R�[�h <BR>
     * FX�����ԍ��F �p�����[�^.���͔ԍ� <BR>
     * FX�V�X�e���R�[�h�F�@@�p�����[�^.FX�V�X�e���R�[�h<BR>
     * <BR>
     * [��L�ȊO�̏ꍇ] <BR>
     * �p�����[�^.���͔ԍ���ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �T�jFX�V�X�e���R�[�h��ǉ�����B<BR>
     * �@@�@@[�p�����[�^.FX�V�X�e���R�[�h != null�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.FX�V�X�e���R�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �U�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strInputNumberDiv - ���͔ԍ��敪
     * 1�FFX���O�C��ID 2�FFX�����ԍ� 3�F�ڋq�R�[�h
     * @@param l_strInputNumber - ���͔ԍ�
     * ���͔ԍ��敪��1�iFX���O�C��ID�j�̏ꍇ�A<BR>
     * FX�j���O�C��ID ���͔ԍ��敪��2�iFX�����ԍ��j�̏ꍇ�A<BR>
     * FX�j�����ԍ� ���͔ԍ��敪��3�i�ڋq�R�[�h�j�̏ꍇ�A�ڋq�R�[�h
     * @@param l_strFxSystemCode - FX�V�X�e���R�[�h
     * @@return String[]
     * @@roseuid 41BECB5F00CE
     */
    protected String[] createQueryContainer(String l_strInstitutionCode,
        String l_strBranchCode, String l_strInputNumberDiv,
        String l_strInputNumber, String l_strFxSystemCode)
        throws WEB3BaseException
    {
        String l_strMETHOD_NAME = "createQueryContainer(String l_strInstitutionCode," +
            "String l_strBranchCode, String l_strInputNumberDiv,String l_strInputNumber, " +
            "String l_strFxSystemCode)";
        log.entering(l_strMETHOD_NAME);

        //����������������쐬����B 

        //�P�jArrayList�𐶐�����B 
        List l_lisQuereContainer = new ArrayList();
        
        //�Q�j�،���Џ�����ǉ�����B 
        //�@@�p�����[�^.�،���ЃR�[�h��ArrayList�ɒǉ�����B 
        l_lisQuereContainer.add(l_strInstitutionCode);
        
        //�R�j���X������ǉ�����B 
        //�@@�p�����[�^.���X�R�[�h��ArrayList�ɒǉ�����B 
        l_lisQuereContainer.add(l_strBranchCode);
        
        //�S�j���͂��ꂽ�ԍ���ǉ�����B 
        //�@@[�p�����[�^.���͔ԍ��敪 == "FX�����ԍ�"�̏ꍇ] 
        if (WEB3AioInputNumberDivDef.FX_ACCOUNT_CODE.equals(l_strInputNumberDiv))
        {
            WEB3FXDataControlService l_controlService = 
                (WEB3FXDataControlService) Services.getService(
                    WEB3FXDataControlService.class); 
            //�@@�@@FX�f�[�^����T�[�r�XImpl.get�ڋq�R�[�h()�̖߂�l��
            //�@@�@@�擪6byte��ArrayList�ɒǉ�����B 
            //
            //�@@�@@[get�ڋq�R�[�h()�ɃZ�b�g����p�����[�^] 
            //�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h 
            //�@@�@@�@@���X�R�[�h�F�@@�p�����[�^.���X�R�[�h 
            //�@@�@@�@@FX�����ԍ��F�@@�p�����[�^.���͔ԍ�
            //      FX�V�X�e���R�[�h�F�@@�p�����[�^.FX�V�X�e���R�[�h
            try
            {
                String l_strAccountCode =
                    l_controlService.getAccountCode(l_strInstitutionCode, 
                        l_strBranchCode, l_strInputNumber, l_strFxSystemCode);
                //�@@�@@FX�f�[�^����T�[�r�XImpl.get�ڋq�R�[�h()�̖߂�l��
                //�@@�@@�擪6byte��ArrayList�ɒǉ�����B 
                l_lisQuereContainer.add(l_strAccountCode.substring(0, 6));
            }
            catch (NotFoundException l_ex)
            {
                log.debug("NotFoundException: ", l_ex);
				log.debug("FX�ڋq���擾�ł��Ȃ�");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01808,
					this.getClass().getName() + "." + l_strMETHOD_NAME,
					"FX�ڋq���擾�ł��Ȃ�",
					l_ex);
            }
        }
        //�@@[��L�ȊO�̏ꍇ] 
        //�@@�@@�p�����[�^.���͔ԍ���ArrayList�ɒǉ�����B 
        else
        {
            l_lisQuereContainer.add(l_strInputNumber);
        }

        //FX�V�X�e���R�[�h��ǉ�����B
        //�@@[�p�����[�^.FX�V�X�e���R�[�h != null�̏ꍇ]
        //�@@�@@�p�����[�^.FX�V�X�e���R�[�h��ArrayList�ɒǉ�����B
        if (l_strFxSystemCode != null)
        {
            l_lisQuereContainer.add(l_strFxSystemCode);
        }

        //��������ArrayList.toArray()�̖߂�l��ԋp����B 
        String[] l_strQuereContainers = new String[l_lisQuereContainer.size()];
        l_lisQuereContainer.toArray(l_strQuereContainers);
        
        log.exiting(l_strMETHOD_NAME);
        return l_strQuereContainers;
    }
}@
