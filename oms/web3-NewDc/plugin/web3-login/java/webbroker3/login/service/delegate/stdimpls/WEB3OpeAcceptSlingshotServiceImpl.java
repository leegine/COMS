head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3OpeAcceptSlingshotServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq���肷�܂�SS�J�ڃT�[�r�X(WEB3OpeAcceptSlingshotServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/17 �e�n(SRA) �V�K�쐬
 */

package webbroker3.login.service.delegate.stdimpls;

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

import webbroker3.login.service.delegate.WEB3OpeAcceptSlingshotService;
import webbroker3.login.message.WEB3OpeAcceptSlingshotRequest;
import webbroker3.login.message.WEB3OpeAcceptSlingshotResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3PasswordUtility;

/**
 * (�ڋq���肷�܂�SS�J�ڃT�[�r�X)<BR>
 * <BR>
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3OpeAcceptSlingshotServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3OpeAcceptSlingshotService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OpeAcceptSlingshotServiceImpl.class);

    /**
     * @@roseuid 406D2D4E03E7
     */
    public WEB3OpeAcceptSlingshotServiceImpl()
    {

    }

    /**
     * ������������B<BR>
     * <BR>
     * �Z�b�V���������F���ID���擾����B<BR>
     * �@@�@@���ID���擾�ł��Ȃ������ꍇ<BR>
     * �@@�@@�@@�@@�L�蓾�Ȃ��ُ�Ƃ��Ēv���I�ȃV�X�e���G���[��throw����B<BR>
     * <BR>
     * ��ЁiDB:�،���Ёj�̃f�[�^���擾����B<BR>
     * ���X�iDB:���X�j�̃f�[�^���擾����B<BR>
     * <BR>
     * ���X�����O�C����~��Ԃ��`�F�b�N����B<BR>
     * �@@�@@���O�C����~���̏ꍇ�A���O�C����~���G���[��throw����B<BR>
     * <BR>
     * ���肷�܂��Ώۂ̌ڋq�R�[�h���`�F�b�N����B<BR>
     * �@@�@@�ڋq�R�[�h�l�s���G���[��throw����B<BR>
     * <BR>
     * LoginInfo���擾����B<BR>
     * �@@�@@�����̎��擾����LoginInfo�͐��肷�܂��ڋq�̂��́B<BR>
     * �@@�@@���肷�܂��Ώیڋq��LoginInfo���擾�ł��Ȃ������ꍇ<BR>
     * �@@�@@�w��ڋq�f�[�^�Ȃ��G���[��throw����B<BR>
     * <BR>
     * ���ʁD�p�X���[�h�E���[�e�B���e�B���g���Čڋq���肷�܂����p�X���[�h<BR>
     * �`�F�b�N���s���B<BR>
     * �@@�@@�����[�e�B���e�B��CC�I�y���[�^�Ő����B�����Ōڋq��ID��n���B<BR>
     * �@@�@@���肷�܂����p�X���[�h�`�F�b�N���{�ŕs��v�����������ꍇ<BR>
     * �@@�@@�@@�@@�p�X���[�h�s��v�i���肷�܂��s�\�j�G���[��throw����B<BR>
     * <BR>
     * �ڋq�ɐ��肷�܂��B<BR>
     * ��login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B<BR>
     * ��xTrade�Z�b�V����ID�͕s�v�B<BR>
     * <BR>
     * �ڋq�iDB:�ڋq�}�X�^�j�̃f�[�^���擾����B<BR>
     * �@@�@@��login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B<BR>
     * <BR>
     * �X�����O�V���b�g�����p�\���`�F�b�N����B<BR>
     * ���p�s�\�ȏꍇ<BR>
     * �@@�@@���肷�܂�����������iaccount_id:0�Ōڋq�ɐ��肷�܂��j�B<BR>
     * �@@�@@�X�����O�V���b�g���p�s�G���[��throw����B<BR>
     * <BR>
     * �u���v�̃n�b�V���l���v�Z����B<BR>
     * <BR>
     * ���O�A�E�g�E�n���h���Ƀf�B�X�p�b�`���ă��O�A�E�g����B<BR>
     * <BR>
     * ���ʂŃ��X�|���X���쐬���A�������I������B<BR>
     * <BR>
     * �����̉ߒ��ŗ�O�����������ꍇ�A���̗l�ɐU�����B<BR>
     * WEB3BaseException�����������ꍇ<BR>
     * �@@�@@catch������O�����̂܂�throw����B<BR>
     * NotFoundException�����������ꍇ<BR>
     * �@@�@@�L�蓾�Ȃ��ُ�Ƃ��Ēv���I�ȃV�X�e���G���[��throw����B<BR>
     * ����ȊO�̗�O�����������ꍇ<BR>
     * �@@�@@�L�蓾�Ȃ��ُ�Ƃ��Ēv���I�ȃV�X�e���G���[��throw����B<BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 406BBE1003B8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3OpeAcceptSlingshotRequest l_ssReq =
            (WEB3OpeAcceptSlingshotRequest) l_request;
        WEB3OpeAcceptSlingshotResponse l_ssRes = null;
        try
        {
            String l_uname = l_ssReq.xTradeUsername;
            String l_pwd = l_ssReq.acceptPassword;
            String l_bcode = l_ssReq.branchCode;
            log.debug(
                STR_METHOD_NAME
                    + " .... user = "
                    + l_uname
                    + ", branch = "
                    + l_bcode);

            OpLoginSecurityService l_securityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            String l_instIDStr =
                l_securityService.getSessionProperty(
                    WEB3SessionAttributeDef.INSTITUTION_ID);
            if (l_instIDStr == null)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���ID���Z�b�V���������ɃZ�b�g����Ă��Ȃ�.�i�V�X�e���ُ�j");
            }

            AccountManager l_accountManager = GtlUtils.getAccountManager();
            Institution l_inst =
                l_accountManager.getInstitution(Long.parseLong(l_instIDStr));
            InstitutionRow l_institutionRow = (InstitutionRow) l_inst.getDataSourceObject();
            BranchRow l_branchRow =
                (BranchRow) l_accountManager
                    .getBranch(l_inst, l_bcode)
                    .getDataSourceObject();

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
                    WEB3ErrorCatalog.BUSINESS_ERROR_90207,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(CODE: " + l_ssReq.acceptCode + ") �R�[�h�l�s��.");
            }

            OpLoginAdminService l_adminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            LoginInfo l_loginInfo = l_adminService.getLoginInfo(l_uname);
            if (l_loginInfo == null)
            { /* CC�I�y���[�^�ɂ͎w�胆�[�U�������݂��Ȃ�����m�点��K�v������ */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90205,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_uname + ") �͑��݂��Ȃ�.");
            }
            WEB3PasswordUtility l_pwdUtil =
                new WEB3PasswordUtility(l_securityService.getLoginId());
            log.debug(
                STR_METHOD_NAME
                    + " .... brch = "
                    + l_branchRow.getBranchName()
                    + ", user = "
                    + l_loginInfo.getUsername());

            if (!l_pwdUtil
                .checkSetAccountPassword(
                    l_ssReq.passwordCheckFlag,
                    l_loginInfo.getLoginId(),
                    l_pwd))
            { /* �p�X���[�h�s��v */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90213,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_uname + ") �p�X���[�h�s��v.");
            }
            
            //login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B
            LoginRow l_loginRow = LoginDao.findRowByPk(l_loginInfo.getLoginId());

            /* �ڋq�ɐ��肷�܂�. */
            changeAccount(l_loginRow.getAccountId(), null);

            //�ڋq�iDB:�ڋq�}�X�^�j�̃f�[�^���擾����B
            // ��login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B
            MainAccountRow l_mainAccountRow =
                (MainAccountRow) l_accountManager.getMainAccount(l_loginRow.getAccountId()).getDataSourceObject();
            log.debug(
                STR_METHOD_NAME
                    + " .... user = "
                    + l_mainAccountRow.getFamilyName());

            if (!isRegiSlingshot(l_ssReq.infoServiceCode, true, l_institutionRow))
            { /* �X�����O�V���b�g���p�s�� */
                changeAccount(0, null);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90209,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_uname + ") �X�����O�V���b�g���p�s��.");
            }

            l_ssRes =
                (WEB3OpeAcceptSlingshotResponse) l_request.createResponse();
            l_ssRes.hashValue = calcHashValue(
                UP_HASH_VALUE,
                l_institutionRow,
                l_branchRow,
                l_mainAccountRow);
            l_ssRes.acceptCodeCD = l_mainAccountRow.getAccountCode();

            dispatchLogout(null);
        }
        catch (WEB3BaseException ex)
        {
            throw ex;
        }
        catch (NotFoundException ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���X�A�܂��͌ڋq�}�X�^�ɕK�v�ȃf�[�^�����݂��Ȃ�.",
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
