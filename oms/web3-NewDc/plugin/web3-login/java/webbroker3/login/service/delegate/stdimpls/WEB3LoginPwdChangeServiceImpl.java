head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3LoginPwdChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ���O�C�����p�X���[�h�ύX�T�[�r�X(WEB3LoginPwdChangeServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/20 �e�n(SRA) �V�K�쐬
 */

package webbroker3.login.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;

import webbroker3.login.message.WEB3LoginPwdChangeRequest;
import webbroker3.login.message.WEB3LoginPwdChangeResponse;
import webbroker3.login.service.delegate.WEB3LoginPwdChangeService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LogoutFlagDef;
import webbroker3.common.define.WEB3ReloginFlagDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3PasswordUtility;

/**
 * �i���O�C�����p�X���[�h�ύX�T�[�r�X�j
 * <BR>
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3LoginPwdChangeServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3LoginPwdChangeService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3LoginPwdChangeServiceImpl.class);

    /**
     * @@roseuid 40693597008C
     */
    public WEB3LoginPwdChangeServiceImpl()
    {
        super();
    }

    /**
     * ������������B<BR>
     * <BR>
     * ���݃p�X���[�h�����O�C�����̃��[�U�̂��̂ƈ�v���邩�`�F�b�N����B<BR>
     * �@@�@@��v���Ȃ��ꍇ<BR>
     * �@@�@@�@@�@@���݃p�X���[�h�s��v�i�s���A�N�Z�X�j��throw����B<BR>
     * <BR>
     * �V�p�X���[�h�P�A�Q����v���Ă��邩�`�F�b�N����B<BR>
     * �@@�@@��v���Ȃ��ꍇ<BR>
     * �@@�@@�@@�@@�p�X���[�h���̓~�X��throw����B<BR>
     * <BR>
     * ���ʁD�p�X���[�h�E���[�e�B���e�B���g���ăZ�L�����e�B�E���x���ɏ]�����p�X���[�h�l<BR>
     * �`�F�b�N�����s����B<BR>
     * �@@�@@�Ԃ�l���`�F�b�N����ȊO�̏ꍇ�A�Ԃ�l�ɏ]������O��throw����B<BR>
     * �@@�@@�@@�@@CHECK_ERROR_LENGTH�F�@@�����s��<BR>
     * �@@�@@�@@�@@CHECK_ERROR_CTYPE�F�@@������s��<BR>
     * �@@�@@�@@�@@CHECK_ERROR_SAME_CURRENT�F�@@���݃p�X���[�h�ƈ�v<BR>
     * �@@�@@�@@�@@CHECK_ERROR_SAME_NAME�F�@@���O�C�����ƈ�v<BR>
     * �@@�@@�@@�@@CHECK_ERROR_SAME_BEFORE�F�@@���p�X���[�h�ƈ�v<BR>
     * �@@�@@�@@�@@CHECK_ERROR_SIMILAR_BEFORE�F�@@���p�X���[�h�Ɨގ�<BR>
     * �@@�@@�@@�@@CHECK_ERROR_CONFIG�F�@@�v���I�ȃV�X�e���G���[<BR>
     * <BR>
     * ���ʁD�p�X���[�h�E���[�e�B���e�B���g�p���ăp�X���[�h��ύX����B<BR>
     * �@@�@@���V�p�X���[�h�����݃p�X���[�h�Ɠ����ꍇ�ł����s����B<BR>
     * �@@�@@�@@�i�p�X���[�h�ύX�������X�V����ׁj<BR>
     * �@@�@@�p�X���[�h��ύX�ł��Ȃ������i�Ԃ�l�����s�������j�ꍇ<BR>
     * �@@�@@�@@�@@�v���I�ȃV�X�e���G���[��throw����B<BR>
     * <BR>
     * ���O�C�����[�U�̍ŏI���O�C�����Ԃ��X�V����B<BR>
     * �@@�@@������p�X���[�h�ύX�v�����I�񂳂��Ȃ��ׁA�����ōŏI���O�C���������X�V�B<BR>
     * <BR>
     * ���ʁD�p�X���[�h�E���[�e�B���e�B���g���ĕύX��ă��O�C�������{���邩�m�F����B<BR>
     * �ă��O�C�����K�v�ȏꍇ�A<BR>
     * �@@�܂��̓��O�A�E�g�t���O���u���O�A�E�g���s�v�̏ꍇ<BR>
     * �@@�@@���O�A�E�g�E�n���h���Ƀf�B�X�p�b�`���ă��O�A�E�g����B<BR>
     * <BR>
     * ���ʂŃ��X�|���X���쐬���A�������I������B<BR>
     * <BR>
     * �����̉ߒ��ŗ�O�����������ꍇ�A���̗l�ɐU�����B<BR>
     * WEB3BaseException�����������ꍇ<BR>
     * �@@�@@catch������O�����̂܂�throw����B<BR>
     * NotFoundException�����������ꍇ<BR>
     * �@@�@@�L�蓾�Ȃ��ُ�Ƃ��Ēv���I�ȃV�X�e���G���[��throw����B<BR>
     * ����ȊO�̗�O�����������ꍇ<BR>
     * �@@�@@��changePassword���s���̗�O���܂ށB�r�W�l�X�E�G���[�ł͂Ȃ����B<BR>
     * �@@�@@�L�蓾�Ȃ��ُ�Ƃ��Ēv���I�ȃV�X�e���G���[��throw����B<BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse@@throws 
     * webbroker3.common.WEB3BaseException
     * @@roseuid 4063BC3F0365
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3LoginPwdChangeRequest l_pwdChangeReq =
            (WEB3LoginPwdChangeRequest) l_request;
        WEB3LoginPwdChangeResponse l_pwdChangeRes = null;
        try
        {
            OpLoginSecurityService l_securityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            log.debug(
                STR_METHOD_NAME
                    + " .... user = "
                    + l_securityService.getLoginId());

            String l_loginName = l_pwdChangeReq.loginName;
            String l_oldPwd = l_pwdChangeReq.oldPassword;
            String l_newPwd1 = l_pwdChangeReq.newPassword1;
            String l_newPwd2 = l_pwdChangeReq.newPassword2;

            if (!l_securityService.checkPassword(l_oldPwd))
            { /* �{�l�m�F�G���[.���p�X���[�h�s��v */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90210,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: "
                        + l_securityService.getLoginId()
                        + "�@@�{�l�m�F�G���[�i���p�X���[�h�s��v�j.");
            }

            if (!l_newPwd1.equals(l_newPwd2))
            { /* �V�p�X���[�h���̓~�X */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90211,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: "
                        + l_securityService.getLoginId()
                        + "�@@�V�p�X���[�h���̓~�X�`�F�b�N�ŃG���[.");
            }

            WEB3PasswordUtility l_pwdUtil =
                new WEB3PasswordUtility(l_securityService.getLoginId());
            switch (l_pwdUtil.checkPassword(l_loginName, l_oldPwd, l_newPwd1))
            {
                case WEB3PasswordUtility.CHECK_ERROR_LENGTH :
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_90216,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "LOGIN_ID: "
                            + l_securityService.getLoginId()
                            + "�@@�V�p�X���[�h���s��.");
                case WEB3PasswordUtility.CHECK_ERROR_CTYPE :
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_90214,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "LOGIN_ID: "
                            + l_securityService.getLoginId()
                            + "�@@�V�p�X���[�h������s��.");
                case WEB3PasswordUtility.CHECK_ERROR_SAME_CURRENT :
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_90217,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "LOGIN_ID: "
                            + l_securityService.getLoginId()
                            + "�@@�V�p�X���[�h�����݃p�X���[�h�Ɠ���.");
                case WEB3PasswordUtility.CHECK_ERROR_SAME_NAME :
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_90218,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "LOGIN_ID: "
                            + l_securityService.getLoginId()
                            + "�@@�V�p�X���[�h�����O�C�����Ɠ���.");
                case WEB3PasswordUtility.CHECK_ERROR_SAME_BEFORE :
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_90219,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "LOGIN_ID: "
                            + l_securityService.getLoginId()
                            + "�@@�V�p�X���[�h�����p�X���[�h�i�R����ȑO�j�Ɠ���.");
                case WEB3PasswordUtility.CHECK_ERROR_SIMILAR_BEFORE :
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_90220,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "LOGIN_ID: "
                            + l_securityService.getLoginId()
                            + "�@@�V�p�X���[�h�����p�X���[�h�i�R����ȑO�j�Ɨގ�.");
                case WEB3PasswordUtility.CHECK_ERROR_CONFIG :
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "�p�X���[�h�ύX���`�F�b�N�֘A�̃��O�C���^�C�v�����ɕs��������.");
            }

            if (!l_pwdUtil.changePassword(l_oldPwd, l_newPwd1))
            { /* �p�X���[�h�ύX���s */
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: "
                        + l_securityService.getLoginId()
                        + "�@@�p�X���[�h�ύX���s.");
            }

            updateLastLoginTime(l_securityService.getLoginId());

            l_pwdChangeRes =
                (WEB3LoginPwdChangeResponse) l_request.createResponse();
            if (l_pwdUtil.isReloginNecessity())
            { /* �p�X���[�h�ύX���A�ă��O�C���K�v */
                /* ���A�N�V�������͂��̃t���O������WOLF2�Z�b�V���������O�A�E�g����B                 */
                /* �@@���N�G�X�g�̃��O�A�E�g�t���O��SS�J�ڎ���xTrade�����O�A�E�g����ׂ̂��́B         */
                /* �@@�]���āA���N�G�X�g�Ń��O�A�E�g�w�����ꂽ����Ƃ����Ă��̃t���O�𗧂ĂĂ͂����Ȃ��B*/
                /* �@@�����͎��Ă��邪�Ӗ����قȂ�B                                               */
                l_pwdChangeRes.reLoginFlag = WEB3ReloginFlagDef.RELOGIN;
            }
            else
            { /* �p�X���[�h�ύX���A�ă��O�C���s�v */
                l_pwdChangeRes.reLoginFlag = WEB3ReloginFlagDef.CONTINUE;
            }

            if (WEB3ReloginFlagDef.RELOGIN.equals(l_pwdChangeRes.reLoginFlag)
                || WEB3LogoutFlagDef.LOGOUT.equals(l_pwdChangeReq.logoutFlag))
            { /* ���O�A�E�g���� */
                dispatchLogout(null);
            }
        }
        catch (WEB3BaseException ex)
        {
            throw ex;
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
        return l_pwdChangeRes;
    }
}
@
