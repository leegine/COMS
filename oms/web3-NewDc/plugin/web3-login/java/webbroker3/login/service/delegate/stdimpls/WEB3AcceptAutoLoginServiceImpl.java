head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptAutoLoginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq�������O�C���T�[�r�X(WEB3AcceptAutoLoginServiceImpl.java)
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

import webbroker3.login.service.delegate.WEB3AcceptAutoLoginService;
import webbroker3.login.message.WEB3AcceptAutoLoginRequest;
import webbroker3.login.message.WEB3AcceptAutoLoginResponse;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * �i�ڋq�������O�C���T�[�r�X�j
 * <BR>
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3AcceptAutoLoginServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3AcceptAutoLoginService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AcceptAutoLoginServiceImpl.class);

    /**
     * @@roseuid 40691E840222
     */
    public WEB3AcceptAutoLoginServiceImpl()
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
     * �����`���l�����`�F�b�N����B<BR>
     * �@@�@@�s���Ȓl�̏ꍇ�A����s�\�G���[��throw����B<BR>
     * <BR>
     * �ڋq�R�[�h�̑Ó������`�F�b�N����B<BR>
     * �@@�@@�R�[�h�l�G���[�Ƃ��Ď���s�\�G���[��throw����B<BR>
     * <BR>
     * LoginInfo���擾����B<BR>
     * �@@�@@�擾�ł��Ȃ������ꍇ�ANotFoundException��throw����B<BR>
     * <BR>
     * �ڋq�iDB:�ڋq�}�X�^�j�̃f�[�^���擾����B<BR>
     * �@@�@@��login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B<BR>
     * <BR>
     * �u����v�Ƃ��ăn�b�V���l���v�Z����B<BR>
     * ��M�����n�b�V���l�ƌv�Z�����n�b�V���l���r����B<BR>
     * �@@�@@��v���Ă��Ȃ��ꍇ�A����s�\�G���[��throw����B<BR>
     * <BR>
     * ���O�C���ixTrade�Z�b�V���������j����B<BR>
     * �@@�@@���O�C���Ɏ��s�����ꍇ<BR>
     * �@@�@@�@@�@@�F�؃G���[�Ƃ��Ď���s�\�G���[��throw����B<BR>
     * <BR>
     * �Z�b�V���������ݒ�n���h���Ƀf�B�X�p�b�`���ăZ�b�V����������ݒ肷��B<BR>
     * <BR>
     * �ڋq���ڋq���ɕϊ�����B<BR>
     * ��Ђ���Џ��ɕϊ�����B<BR>
     * ���X�𕔓X���ɕϊ�����B<BR>
     * �T�[�r�X���{��Ԏ擾���擾����B<BR>
     * ���ʂŃ��X�|���X���쐬���A�������I������B<BR>
     * <BR>
     * �����̉ߒ��ŗ�O�����������ꍇ�A���̗l�ɐU�����B<BR>
     * WEB3BaseException�����������ꍇ<BR>
     * �@@�@@catch������O�����̂܂�throw����B<BR>
     * NotFoundException�����������ꍇ<BR>
     * �@@�@@�s���A�N�Z�X�Ƃ��Ď���s�\�G���[��throw����B<BR>
     * ����ȊO�̗�O�����������ꍇ<BR>
     * �@@�@@�L�蓾�Ȃ��ُ�Ƃ��Ēv���I�ȃV�X�e���G���[��throw����B<BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4062B5090280
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AcceptAutoLoginRequest l_loginReq =
            (WEB3AcceptAutoLoginRequest) l_request;
        WEB3AcceptAutoLoginResponse l_loginRes = null;
        try
        {
            String l_uname = l_loginReq.xTradeUsername;
            String l_pwd = l_loginReq.acceptPassword;
            String l_icode = l_loginReq.institutionCode;
            String l_bcode = l_loginReq.branchCode;
            log.debug(STR_METHOD_NAME + " .... inst = " + l_icode + ", brch = " + l_bcode + ", user = " + l_uname);

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

            if (!checkOrderChannel(l_loginReq.orderChannel))
            { /* �����`���l���l�s�� */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_uname + ") �����`���l���l�s��.");
            }

            if (!checkAcceptCode(l_loginReq.acceptCode, l_branchRow))
            { /* �ڋq�R�[�h�l�s�� */
                log.debug(
                    STR_METHOD_NAME + " .... login failed(code value check).");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(CODE: " + l_loginReq.acceptCode + ") �R�[�h�l�s��.");
            }

            OpLoginAdminService l_adminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            LoginInfo l_loginInfo = l_adminService.getLoginInfo(l_uname);
            if (l_loginInfo == null)
            {
                throw new NotFoundException(l_uname + " not found.");
            }
            log.debug(
                STR_METHOD_NAME
                    + " .... inst = "
                    + l_institutionRow.getInstitutionName()
                    + ", brch = "
                    + l_branchRow.getBranchName()
                    + ", user = "
                    + l_loginInfo.getUsername());

            //�ڋq�iDB:�ڋq�}�X�^�j�̃f�[�^���擾����B
            // ��login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B
            LoginRow l_loginRow = LoginDao.findRowByPk(l_loginInfo.getLoginId());
            MainAccountRow l_mainAccountRow =
                (MainAccountRow) l_accountManager.getMainAccount(l_loginRow.getAccountId()).getDataSourceObject();
            log.debug(
                STR_METHOD_NAME + " .... user = " + l_mainAccountRow.getFamilyName());

            String l_web3HashValue = calcHashValue(
                DOWN_HASH_VALUE,
                l_institutionRow,
                l_branchRow,
                l_mainAccountRow);
            if (!l_web3HashValue.equals(l_loginReq.hashValue))
            { /* �n�b�V���l�s�� */
                log.debug(STR_METHOD_NAME + " .... invalid hash value..");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_uname + ") �n�b�V���l�s��.");
            }

            OpLoginSecurityService l_securityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            String l_sessionID = l_securityService.logIn(l_uname, l_pwd);
            if (l_sessionID == null)
            { /* ���O�C�����s */
                log.debug(
                    STR_METHOD_NAME + " .... login failed(authenticate).");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_uname + ") �F�؃G���[.");
            }
            log.debug(STR_METHOD_NAME + " .... sessionID = " + l_sessionID);

            WEB3SetSessionRequest l_setSessionReq = new WEB3SetSessionRequest();

            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.ORDER_CHANNEL,
                l_loginReq.orderChannel);
            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV,
                (l_loginReq.orderRootDiv != null)
                    ? l_loginReq.orderRootDiv
                    : WEB3OrderRootDivDef.PC);
            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.INSTITUTION_ID,
                Long.toString(l_institutionRow.getInstitutionId()));
            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.BRANCH_ID,
                Long.toString(l_branchRow.getBranchId()));

            setSessionAttribute(l_sessionID, l_setSessionReq);

            l_loginRes =
                (WEB3AcceptAutoLoginResponse) l_request.createResponse();
            l_loginRes.xTradeSessionID = l_sessionID;
            l_loginRes.acceptInfo = getAcceptInfo(
                l_loginReq.acceptCode,
                l_mainAccountRow,
                l_loginInfo,
                l_loginReq.orderRootDiv);
            l_loginRes.institutionInfo = getInstitutionInfo(l_institutionRow);
            l_loginRes.branchInfo = getBranchInfo(l_branchRow);
            l_loginRes.serviceImpState = getServiceImpState(
                l_institutionRow,
                l_branchRow,
                l_mainAccountRow);
        }
        catch (WEB3BaseException ex)
        {
            throw ex;
        }
        catch (NotFoundException ex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���(CODE: "
                    + l_loginReq.institutionCode
                    + ")"
                    + ", ���X(CODE: "
                    + l_loginReq.branchCode
                    + ")"
                    + ", �ڋq(xNAME: "
                    + l_loginReq.xTradeUsername
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
        return l_loginRes;
    }
}
@
