head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SetAccountServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq���肷�܂��T�[�r�X(WEB3SetAccountServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/26 �e�n(SRA) �V�K�쐬
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

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.login.message.WEB3SetAccountRequest;
import webbroker3.login.message.WEB3SetAccountResponse;
import webbroker3.login.service.delegate.WEB3SetAccountService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ڋq���肷�܂��T�[�r�X)
 * <BR>
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3SetAccountServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3SetAccountService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SetAccountServiceImpl.class);

    /**
     * @@roseuid 404835050203
     */
    public WEB3SetAccountServiceImpl()
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
     * �����`���l�����`�F�b�N����B<BR>
     * �@@�@@�s���Ȓl�̏ꍇ�A�p�X���[�h�s��v�i���肷�܂��s�\�j�G���[��throw����B<BR>
     * �@@�@@���ʏ�L�蓾�Ȃ��B�s���A�N�Z�X�Ƃ݂Ȃ��B<BR>
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
     * �ڋq�ɐ��肷�܂��A�Z�b�V����ID���擾����B<BR>
     *  ��login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B<BR>
     *  ��xTrade�Z�b�V����ID�͕s�v�B<BR>
     * <BR>
     * �Z�b�V����������ݒ肷��B<BR>
     * �@@�@@�������ł�ActiveSession�Ɗ֘A�t�����Ă���̂ŁA���ڐݒ肷��B<BR>
     * �@@�@@�����肷�܂��ڋq�̕��XID���Đݒ肷��B<BR>
     * <BR>
     * �ڋq�iDB:�ڋq�}�X�^�j�f�[�^���擾����B<BR>
     * �@@�@@��login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B<BR>
     * �ڋq���ڋq���ɕϊ�����B<BR>
     * ���X�𕔓X���ɕϊ�����B<BR>
     * �ڋq�}�X�^�̐擪���ID���擾����B<BR>
     * <BR>
     * �T�[�r�X���{��Ԏ擾���擾����B<BR>
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
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4044589502B5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SetAccountRequest l_setAccountReq =
            (WEB3SetAccountRequest) l_request;
        WEB3SetAccountResponse l_setAccountRes = null;
        try
        {
            String l_uname = l_setAccountReq.xTradeUsername;
            String l_pwd = l_setAccountReq.acceptPassword;
            String l_bcode = l_setAccountReq.branchCode;
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

            if (!checkOrderChannel(l_setAccountReq.orderChannel))
            { /* �����`���l���l�s�� */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90213,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_uname + ") �����`���l���l�s��.");
            }

            if (!checkAcceptCode(l_setAccountReq.acceptCode, l_branchRow))
            { /* �ڋq�R�[�h�l�s�� */
                log.debug(
                    STR_METHOD_NAME + " .... login failed(code value check).");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90207,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(CODE: " + l_setAccountReq.acceptCode + ") �R�[�h�l�s��.");
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
                    l_setAccountReq.passwordCheckFlag,
                    l_loginInfo.getLoginId(),
                    l_pwd))
            { /* �p�X���[�h�s��v */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90213,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_uname + ") �p�X���[�h�s��v.");
            }
            
            //login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����
            LoginRow l_loginRow = LoginDao.findRowByPk(l_loginInfo.getLoginId());
            
            /* �ڋq�ɐ��肷�܂�. */
            String l_sessionID = 
                changeAccount(l_loginRow.getAccountId(), null);
            log.debug("�Z�b�V����ID:[" + l_sessionID + "]");

            /* �Z�b�V���������ݒ� */
            l_securityService.setSessionProperty(
                WEB3SessionAttributeDef.ORDER_CHANNEL,
                l_setAccountReq.orderChannel);
            l_securityService.setSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV,
                (l_setAccountReq.orderRootDiv != null)
                    ? l_setAccountReq.orderRootDiv
                    : WEB3OrderRootDivDef.CALLCENTER);
            l_securityService.setSessionProperty(
                WEB3SessionAttributeDef.BRANCH_ID,
                Long.toString(l_branchRow.getBranchId()));

            //�ڋq�iDB:�ڋq�}�X�^�j�̃f�[�^���擾����B
            // ��login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B
            MainAccountRow l_mainAccountRow =
                (MainAccountRow) l_accountManager.getMainAccount(l_loginRow.getAccountId()).getDataSourceObject();
                
            log.debug(
                STR_METHOD_NAME
                    + " .... user = "
                    + l_mainAccountRow.getFamilyName());

            l_setAccountRes =
                (WEB3SetAccountResponse) l_request.createResponse();
            l_setAccountRes.acceptInfo = getAcceptInfo(
                l_setAccountReq.acceptCode,
                l_mainAccountRow,
                l_loginInfo,
                l_setAccountReq.orderRootDiv);
            l_setAccountRes.branchInfo = getBranchInfo(l_branchRow);
            l_setAccountRes.serviceImpState = getServiceImpState(
                l_institutionRow,
                l_branchRow,
                l_mainAccountRow);
            l_setAccountRes.topPageID = l_mainAccountRow.getTopPageId();
            l_setAccountRes.xTradeSessionID = l_sessionID;
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
        return l_setAccountRes;
    }
}
@
