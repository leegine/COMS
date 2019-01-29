head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AdministratorLoginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��҃��O�C���T�[�r�X(WEB3AdministratorLoginServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/20 �e�n(SRA) �V�K�쐬
Revesion History    : 2004/07/28 ����(SRA) �Ǘ��҃e�[�u���֘A��DAO��gtl-base����
�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@webbroker3.gentrade.data�ֈړ��������ɔ����Ή�
 */

package webbroker3.login.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

import webbroker3.login.message.WEB3AdministratorLoginRequest;
import webbroker3.login.message.WEB3AdministratorLoginResponse;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.login.service.delegate.WEB3AdministratorLoginService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeDef;
import webbroker3.common.define.WEB3PwdChangeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorRow;

/**
 * �i�Ǘ��҃��O�C���T�[�r�X�j
 * <BR>
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3AdministratorLoginServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3AdministratorLoginService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdministratorLoginServiceImpl.class);

    /**
     * @@roseuid 408F4D8001D1
     */
    public WEB3AdministratorLoginServiceImpl()
    {

    }

    /**
     * ������������B<BR>
     * ��ЁiDB:�،���Ёj�̃f�[�^���擾����B<BR>
     * <BR>
     * �Ǘ��҃R�[�h�̑Ó������`�F�b�N����B<BR>
     * �@@�@@�s���̏ꍇ�A�R�[�h�l�G���[�Ƃ��ăp�X���[�h�E�G���[��throw����B<BR>
     * <BR>
     * LoginInfo���擾����B<BR>
     * �@@�@@�擾�ł��Ȃ������ꍇ�ANotFoundException��throw����B<BR>
     * <BR>
     * �Ǘ��҃��O�C���G���[�񐔁i�L�����j���`�F�b�N����B<BR>
     * �@@�@@�����̏ꍇ�A�Ǘ��҂̃��b�N���G���[��throw����B<BR>
     * <BR>
     * ���O�C���ixTrade�Z�b�V���������j����B<BR>
     * �@@�@@���O�C���G���[�̏ꍇ�A�F�؃G���[�Ƃ��ăp�X���[�h�G���[��throw����B<BR>
     * <BR>
     * �Ǘ��ҁiDB:�Ǘ��҃}�X�^�j�̃f�[�^���擾����B<BR>
     * �Ǘ��҂̕��XID���g�p���ĕ��X�iDB:���X�j�̃f�[�^���擾����B<BR>
     * <BR>
     * �Z�b�V���������ݒ�n���h���Ƀf�B�X�p�b�`���ăZ�b�V����������ݒ肷��B<BR>
     * <BR>
     * �Ǘ��҂��Ǘ��ҏ��ɕϊ�����B<BR>
     * ��Ђ���Џ��ɕϊ�����B<BR>
     * ���X�𕔓X���ɕϊ�����B<BR>
     * <BR>
     * ���N�G�X�g�����F���O�C���^�C�v���u�p�X���[�h�ύX�v�̏ꍇ�͖�������<BR>
     * �p�X���[�h�ύX�K�v�Ƃ���B<BR>
     * �����łȂ��ꍇ�͋��ʁD�p�X���[�h�E���[�e�B���e�B���g���ĕK�v���𔻒肷��B<BR>
     * <BR>
     * �p�X���[�h�ύX���s�v�ȏꍇ<BR>
     * �@@�@@�Ǘ��҂̍ŏI���O�C���������X�V����B<BR>
     * <BR>
     * ���ʂŃ��X�|���X���쐬���A�������I������B<BR>
     * <BR>
     * �����̉ߒ��ŗ�O�����������ꍇ�A���̗l�ɐU�����B<BR>
     * WEB3BaseException�����������ꍇ<BR>
     * �@@�@@catch������O�����̂܂�throw����B<BR>
     * NotFoundException�����������ꍇ<BR>
     * �@@�@@�s���A�N�Z�X�Ƃ��ăp�X���[�h�E�G���[��throw����B<BR>
     * ����ȊO�̗�O�����������ꍇ<BR>
     * �@@�@@�L�蓾�Ȃ��ُ�Ƃ��Ēv���I�ȃV�X�e���G���[��throw����B<BR>
     * <BR>
     * �����P�j�@@���X�|���X�ҏW�ɂ���<BR>
     * �i���X�|���X.�p�X���[�h�ύX�t���O == "1"�F�ύX�K�v�j�̏ꍇ�A<BR>
     * ���O�C���^�C�v�������A�p�X���[�h�ŏ����C�p�X���[�h�ő咷�C<BR>
     * �p�X���[�h�`�F�b�N������ҏW����B<BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 4084F84E0096
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdministratorLoginRequest l_loginReq =
            (WEB3AdministratorLoginRequest) l_request;
        WEB3AdministratorLoginResponse l_loginRes = null;
        try
        {
            String l_uname = l_loginReq.xTradeUsername;
            String l_ucode = l_loginReq.administratorCode;
            String l_pwd = l_loginReq.administratorPassword;
            String l_icode = l_loginReq.institutionCode;
            /*���݂̓_�~�[�FString l_bcode = l_loginReq.branchCode;*/
            log.debug(
                STR_METHOD_NAME
                    + " .... inst = "
                    + l_icode
                    + ", mgr = "
                    + l_uname);

            AccountManager l_accountManager = GtlUtils.getAccountManager();
            Institution l_inst = l_accountManager.getInstitution(l_icode);
            InstitutionRow l_institutionRow = (InstitutionRow) l_inst.getDataSourceObject();

            if (!checkAdministratorCode(l_ucode, l_institutionRow))
            { /* �Ǘ��҃R�[�h�l�s�� */
                log.debug(
                    STR_METHOD_NAME + " .... login failed(code value check).");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90221,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�Ǘ���(CODE: " + l_ucode + ") �R�[�h�l�s��.");
            }

            OpLoginAdminService l_adminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            LoginInfo l_loginInfo = l_adminService.getLoginInfo(l_uname);
            if (l_loginInfo == null)
            {
                throw new NotFoundException(l_uname + " not found.");
            }
            WEB3PasswordUtility l_pwdUtil =
                new WEB3PasswordUtility(l_loginInfo.getLoginId());
            log.debug(
                STR_METHOD_NAME
                    + " .... inst = "
                    + l_institutionRow.getInstitutionName()
                    + ", mgr = "
                    + l_loginInfo.getUsername());

            if (!isEnabledUser(l_loginInfo))
            { /* ���b�N�� */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90215,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�Ǘ���(xNAME: " + l_uname + ") ���b�N��.");
            }

            OpLoginSecurityService l_securityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            String l_sessionID = l_securityService.logIn(l_uname, l_pwd);
            if (l_sessionID == null)
            { /* ���O�C�����s */
                log.debug(
                    STR_METHOD_NAME + " .... login failed(authenticate).");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90203,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�Ǘ���(xNAME: " + l_uname + ") �F�؃G���[.");
            }
            log.debug(STR_METHOD_NAME + " .... sessionID = " + l_sessionID);

            AdministratorRow l_administratorRow =
                AdministratorDao.findRowByLoginId(l_loginInfo.getLoginId());
            if (l_administratorRow == null)
            {
                throw new NotFoundException(
                    "administrator not found.(login_id: "
                        + l_loginInfo.getLoginId()
                        + ")");
            }

            BranchRow l_branchRow =
                (BranchRow) l_accountManager.getBranch(l_administratorRow.getBranchId()).getDataSourceObject();

            WEB3SetSessionRequest l_setSessionReq = new WEB3SetSessionRequest();

            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.INSTITUTION_ID,
                Long.toString(l_institutionRow.getInstitutionId()));
            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.BRANCH_ID,
                Long.toString(l_branchRow.getBranchId()));
            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.RIGHT_LEVEL,
                l_administratorRow.getPermissionLevel());

            setSessionAttribute(l_sessionID, l_setSessionReq);

            l_loginRes =
                (WEB3AdministratorLoginResponse) l_request.createResponse();
            l_loginRes.xTradeSessionID = l_sessionID;
            l_loginRes.administratorInfo = getAdministratorInfo(
                l_administratorRow,
                l_loginInfo);
            l_loginRes.institutionInfo = getInstitutionInfo(l_institutionRow);
            l_loginRes.branchInfo = getBranchInfo(l_branchRow);

            if (WEB3LoginTypeDef.PWDCHANGE.equals(l_loginReq.loginType)
                || l_pwdUtil.isChangeNecessity())
            {
                l_loginRes.passwordChangeFlag = WEB3PwdChangeDef.REQUIRED;
            }
            else
            {
                l_loginRes.passwordChangeFlag = WEB3PwdChangeDef.UNNECESSARY;
                updateLastLoginTime(l_loginInfo.getLoginId());
            }
            
            //�i���X�|���X.�p�X���[�h�ύX�t���O == "1"�F�ύX�K�v�j�̏ꍇ
            if(WEB3PwdChangeDef.REQUIRED.equals(l_loginRes.passwordChangeFlag))
            {
                //���O�C���^�C�v�������擾����
                HashMap loginTypeAttrs = new HashMap();
                loginTypeAttrs.putAll(l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId()));
                
                //���O�C���^�C�v�������A�p�X���[�h�ŏ����C�p�X���[�h�ő咷�C�p�X���[�h�`�F�b�N������ҏW����B
                l_loginRes.passwordMaxLength = 
                    (String) loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH);
                l_loginRes.passwordMinLength = 
                    (String) loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH);
                l_loginRes.passwordCheckMethod = 
                    (String) loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE);
                
            }
        }
        catch (WEB3BaseException ex)
        {
            throw ex;
        }
        catch (NotFoundException ex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_90221,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���(CODE: "
                    + l_loginReq.institutionCode
                    + ")"
                    + ", �Ǘ���(xNAME: "
                    + l_loginReq.xTradeUsername
                    + ")"
                    + " ���ꂩ�����݂��Ȃ�.�s���A�N�Z�X�Ƃ݂Ȃ�.",
                ex);
        }
        catch (Exception ex)
        {
            log.debug(STR_METHOD_NAME + " .... exception: " + ex.toString());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                ex.getMessage(),
                ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_loginRes;
    }
}
@