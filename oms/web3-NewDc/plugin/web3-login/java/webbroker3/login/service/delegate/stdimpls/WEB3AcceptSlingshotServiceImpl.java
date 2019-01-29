head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptSlingshotServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋqSS�J�ڃT�[�r�X(WEB3AcceptSlingshotServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/21 �e�n(SRA) �V�K�쐬
 */

package webbroker3.login.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.login.service.delegate.WEB3AcceptSlingshotService;
import webbroker3.login.message.WEB3AcceptSlingshotResponse;
import webbroker3.login.message.WEB3AcceptSlingshotRequest;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3PwdChangeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3PasswordUtility;

/**
 * �i�ڋqSS�J�ڃT�[�r�X�j<BR>
 * <BR>
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3AcceptSlingshotServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3AcceptSlingshotService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AcceptSlingshotServiceImpl.class);

    /**
     * @@roseuid 40690783000F
     */
    public WEB3AcceptSlingshotServiceImpl()
    {
        super();
    }

    /**
     * ������������B<BR>
     * ��ЁiDB:�،���Ёj�̃f�[�^���擾����B<BR>
     * ���X�iDB:���X�j�̃f�[�^���擾����B<BR>
     * <BR>
     * ���X�����O�C����~��Ԃ��`�F�b�N����B<BR>
     * �@@�@@���O�C����~���̏ꍇ�A���O�C����~���G���[��throw����B<BR>
     * <BR>
     * �ڋq�R�[�h�̑Ó������`�F�b�N����B<BR>
     * �@@�@@�R�[�h�l�G���[�Ƃ��ăp�X���[�h�G���[��throw����B<BR>
     * <BR>
     * LoginInfo���擾����B<BR>
     * �@@�@@�擾�ł��Ȃ������ꍇ�ANotFoundException��throw����B<BR>
     * <BR>
     * �ڋq���O�C���G���[�񐔁i�L�����j���`�F�b�N����B<BR>
     * �@@�@@�����̏ꍇ�A�ڋq���b�N���G���[��throw����B<BR>
     * <BR>
     * ���O�C���ixTrade�Z�b�V���������j����B<BR>
     * �@@�@@���O�C���G���[�̏ꍇ�A�F�؃G���[�Ƃ��ăp�X���[�h�G���[��throw����B<BR>
     * <BR>
     * �ڋq�iDB:�ڋq�}�X�^�j�̃f�[�^���擾����B<BR>
     * �@@�@@��login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B<BR>
     * <BR>
     * �X�����O�V���b�g�����p�\���`�F�b�N����B<BR>
     * ���p�s�\�ȏꍇ<BR>
     * �@@�@@���O�A�E�g�E�n���h���Ƀf�B�X�p�b�`���ă��O�A�E�g����B
     * �@@�@@�X�����O�V���b�g���p�s�G���[��throw����B<BR>
     * <BR>
     * �u���v�̃n�b�V���l���v�Z����B<BR>
     * <BR>
     * ���ʁD�p�X���[�h�E���[�e�B���e�B���g���ăp�X���[�h�ύX�̕K�v���𔻒肷��B<BR>
     * �p�X���[�h�ύX�K�v�̏ꍇ<BR>
     * �@@�@@���X�|���X��xTrade�Z�b�V����ID���Z�b�g����B<BR>
     * �@@�@@�Z�b�V���������ݒ�n���h���Ƀf�B�X�p�b�`���đ�����ݒ肷��B<BR>
     * �p�X���[�h�ύX�s�v�̏ꍇ<BR>
     * �@@�@@�ڋq�ŏI���O�C�������X�V�B<BR>
     * �@@�@@���O�A�E�g�E�n���h���Ƀf�B�X�p�b�`���ă��O�A�E�g����B<BR>
     * <BR>
     * ���ʂŃ��X�|���X���쐬���A�������I������B<BR>
     * <BR>
     * �����̉ߒ��ŗ�O�����������ꍇ�A���̗l�ɐU�����B<BR>
     * WEB3BaseException�����������ꍇ<BR>
     * �@@�@@catch������O�����̂܂�throw����B<BR>
     * NotFoundException�����������ꍇ<BR>
     * �@@�@@�s���A�N�Z�X�Ƃ��ăp�X���[�h�G���[��throw����B<BR>
     * ����ȊO�̗�O�����������ꍇ<BR>
     * �@@�@@�L�蓾�Ȃ��ُ�Ƃ��Ēv���I�ȃV�X�e���G���[��throw����B<BR>
     * �����P�j�@@���X�|���X�ҏW�ɂ���<BR>
     * �i���X�|���X.�p�X���[�h�ύX�t���O == "1"�F�ύX�K�v�j�̏ꍇ�A<BR>
     * ���O�C���^�C�v�������A�p�X���[�h�ŏ����C�p�X���[�h�ő咷�C<BR>
     * �p�X���[�h�`�F�b�N������ҏW����B<BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406278D30251
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AcceptSlingshotRequest l_ssReq =
            (WEB3AcceptSlingshotRequest) l_request;
        WEB3AcceptSlingshotResponse l_ssRes = null;
        try
        {
            String l_uname = l_ssReq.xTradeUsername;
            String l_pwd = l_ssReq.acceptPassword;
            String l_icode = l_ssReq.institutionCode;
            String l_bcode = l_ssReq.branchCode;
            log.debug(
                STR_METHOD_NAME
                    + " .... inst = "
                    + l_icode
                    + ", brch = "
                    + l_bcode
                    + ", user = "
                    + l_uname);

            AccountManager l_accountManager = GtlUtils.getAccountManager();
            Institution l_inst = l_accountManager.getInstitution(l_icode);
            InstitutionRow l_institutionRow = (InstitutionRow) l_inst.getDataSourceObject();
            BranchRow l_branchRow =
                (BranchRow) l_accountManager.getBranch(l_inst, l_bcode).getDataSourceObject();

            if (isLoginStop(l_branchRow))
            { /* ���O�C����~�� */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90201,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���X(CODE: " + l_bcode + ") ���O�C����~��.");
            }

            if (!checkAcceptCode(l_ssReq.acceptCode, l_branchRow))
            { /* �ڋq�R�[�h�l�s�� */
                log.debug(
                    STR_METHOD_NAME + " .... login failed(code value check).");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90221,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(CODE: " + l_ssReq.acceptCode + ") �R�[�h�l�s��.");
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
                    + ", brch = "
                    + l_branchRow.getBranchName()
                    + ", user = "
                    + l_loginInfo.getUsername());

            if (!isEnabledUser(l_loginInfo))
            { /* ���b�N�� */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90202,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_uname + ") ���b�N��.");
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
                    "�ڋq(xNAME: " + l_uname + ") �F�؃G���[.");
            }
            log.debug(STR_METHOD_NAME + " .... sessionID = " + l_sessionID);

            //�ڋq�iDB:�ڋq�}�X�^�j�̃f�[�^���擾����B
            // ��login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B
            LoginRow l_loginRow = LoginDao.findRowByPk(l_loginInfo.getLoginId());
            MainAccountRow l_mainAccountRow =
                (MainAccountRow) l_accountManager.getMainAccount(l_loginRow.getAccountId()).getDataSourceObject();
            log.debug(
                STR_METHOD_NAME
                    + " .... user = "
                    + l_mainAccountRow.getFamilyName());

            if (!isRegiSlingshot(l_ssReq.infoServiceCode, false, l_institutionRow))
            { /* �X�����O�V���b�g���p�s�� */
                dispatchLogout(l_sessionID);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90209,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_uname + ") �X�����O�V���b�g���p�s��.");
            }

            l_ssRes = (WEB3AcceptSlingshotResponse) l_request.createResponse();
            l_ssRes.hashValue = calcHashValue(
                UP_HASH_VALUE,
                l_institutionRow,
                l_branchRow,
                l_mainAccountRow);
            l_ssRes.acceptCodeCD = l_mainAccountRow.getAccountCode();

            if (l_pwdUtil.isChangeNecessity())
            {
                l_ssRes.passwordChangeFlag = WEB3PwdChangeDef.REQUIRED;
                l_ssRes.xTradeSessionID = l_sessionID;

                WEB3SetSessionRequest l_setSessionReq =
                    new WEB3SetSessionRequest();
                l_setSessionReq.sessionAttributes.put(
                    WEB3SessionAttributeDef.INSTITUTION_ID,
                    Long.toString(l_institutionRow.getInstitutionId()));
                l_setSessionReq.sessionAttributes.put(
                    WEB3SessionAttributeDef.BRANCH_ID,
                    Long.toString(l_branchRow.getBranchId()));
                setSessionAttribute(l_sessionID, l_setSessionReq);
            }
            else
            {
                l_ssRes.passwordChangeFlag = WEB3PwdChangeDef.UNNECESSARY;

                updateLastLoginTime(l_loginInfo.getLoginId());
                dispatchLogout(l_sessionID);
            }
            
            // �i���X�|���X.�p�X���[�h�ύX�t���O == "1"�F�ύX�K�v�j�̏ꍇ
            if(WEB3PwdChangeDef.REQUIRED.equals(l_ssRes.passwordChangeFlag))
            {
                //���O�C���^�C�v�������擾����
                HashMap loginTypeAttrs = new HashMap();
                loginTypeAttrs.putAll(l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId()));
                
                //���O�C���^�C�v�������A�p�X���[�h�ŏ����C�p�X���[�h�ő咷�C�p�X���[�h�`�F�b�N������ҏW����B
                l_ssRes.passwordMaxLength = 
                    (String) loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH);
                l_ssRes.passwordMinLength = 
                    (String) loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH);
                l_ssRes.passwordCheckMethod = 
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
                    + l_ssReq.institutionCode
                    + ")"
                    + ", ���X(CODE: "
                    + l_ssReq.branchCode
                    + ")"
                    + ", �ڋq(xNAME: "
                    + l_ssReq.xTradeUsername
                    + ")"
                    + " ���ꂩ�����݂��Ȃ�.�s���A�N�Z�X�Ƃ݂Ȃ�.",
                ex);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
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
        return l_ssRes;
    }
}
@
