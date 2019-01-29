head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3OpeAcceptAutoLoginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq���肷�܂��������O�C���T�[�r�X(WEB3OpeAcceptAutoLoginServiceImpl.java)
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import webbroker3.login.service.delegate.WEB3OpeAcceptAutoLoginService;
import webbroker3.login.message.WEB3OpeAcceptAutoLoginRequest;
import webbroker3.login.message.WEB3OpeAcceptAutoLoginResponse;
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
 * (�ڋq���肷�܂��������O�C���T�[�r�X)<BR>
 * <BR>
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3OpeAcceptAutoLoginServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3OpeAcceptAutoLoginService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OpeAcceptAutoLoginServiceImpl.class);

    /**
     * @@roseuid 406D318D02CE
     */
    public WEB3OpeAcceptAutoLoginServiceImpl()
    {

    }

    /**
     * ������������B<BR>
     * ��ЁiDB:�،���Ёj�̃f�[�^���擾����B<BR>
     * CC�I�y���[�^�̕��X�iDB:���X�j�̃f�[�^���擾����B<BR>
     * <BR>
     * �����`���l�����`�F�b�N����B<BR>
     * �@@�@@�s���Ȓl�̏ꍇ�A����s�\�G���[��throw����B<BR>
     * <BR>
     * CC�I�y���[�^�̕��X�����O�C����~��Ԃ��`�F�b�N����B<BR>
     * �@@�@@���O�C����~���̏ꍇ�A���O�C����~���G���[��throw����B<BR>
     * <BR>
     * CC�I�y���[�^�R�[�h�̑Ó������`�F�b�N����B<BR>
     * �@@�@@�R�[�h�l�G���[�Ƃ��Ď���s�\�G���[��throw����B<BR>
     * <BR>
     * �ڋq�̕��X�iDB:���X�j�̃f�[�^���擾����B<BR>
     * ������ȍ~�A���X�̏��͌ڋq�̂��̂��g�p����B<BR>
     * <BR>
     * �ڋq�̕��X�����O�C����~��Ԃ��`�F�b�N����B<BR>
     * �@@�@@���O�C����~���̏ꍇ�A���O�C����~���G���[��throw����B<BR>
     * <BR>
     * �ڋq�R�[�h�̑Ó������`�F�b�N����B<BR>
     * �@@�@@�R�[�h�l�G���[�Ƃ��Ď���s�\�G���[��throw����B<BR>
     * <BR>
     * CC�I�y���[�^��LoginInfo���擾����B<BR>
     * �@@�@@�s���A�N�Z�X�Ƃ��Ď���s�\�G���[��throw����B<BR>
     * <BR>
     * �ڋq��LoginInfo���擾����B<BR>
     * �@@�@@�s���A�N�Z�X�Ƃ��Ď���s�\�G���[��throw����B<BR>
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
     * �ڋq�ɐ��肷�܂��A�Z�b�V����ID���擾����B<BR>
     * ��login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B<BR>
     * ��xTrade�Z�b�V����ID��n���K�v������B<BR>
     * <BR>
     * FinObjectManager���擾����B<BR>
     * CC�I�y���[�^�iDB:���҃}�X�^�j�̃f�[�^���擾����B<BR>
     * <BR>
     * �Z�b�V���������ݒ�n���h���Ƀf�B�X�p�b�`���ăZ�b�V����������ݒ肷��B<BR>
     * �����XID�ɂ͌ڋq�̕��XID��ݒ肷��B<BR>
     * <BR>
     * CC�I�y���[�^��LoginInfo���Z�b�g����B<BR>
     * CC�I�y���[�^��CC�I�y���[�^���ɕϊ�����B<BR>
     * �ڋq��LoginInfo���Z�b�g����B<BR>
     * �ڋq���ڋq���ɕϊ�����B<BR>
     * ��Ђ���Џ��ɕϊ�����B<BR>
     * ���X�𕔓X���ɕϊ�����B<BR>
     * �T�[�r�X���{��Ԏ擾���擾����B<BR>
     * <BR>
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
     * @@roseuid 406BE37B0109
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3OpeAcceptAutoLoginRequest l_loginReq =
            (WEB3OpeAcceptAutoLoginRequest) l_request;
        WEB3OpeAcceptAutoLoginResponse l_loginRes = null;
        try
        {
            String l_icode = l_loginReq.institutionCode;
            String l_opeBcode = l_loginReq.ccOpeBranchCode;
            String l_opeUname = l_loginReq.xTradeCCOpeUsername;
            String l_pwd = l_loginReq.ccOperatorPassword;
            String l_opeUcode = l_loginReq.ccOperatorCode;
            String l_userBcode = l_loginReq.acceptBranchCode;
            String l_userUname = l_loginReq.xTradeAcceptUsername;
            log.debug(
                STR_METHOD_NAME
                    + " .... inst = "
                    + l_icode
                    + ", opebrch = "
                    + l_opeBcode
                    + ", ope = "
                    + l_opeUname
                    + ", userbrch = "
                    + l_userBcode
                    + ", user = "
                    + l_userUname);

            AccountManager l_accountManager = GtlUtils.getAccountManager();
            Institution l_inst = l_accountManager.getInstitution(l_icode);
            InstitutionRow l_institutionRow = (InstitutionRow) l_inst.getDataSourceObject();

            if (!checkOrderChannel(l_loginReq.orderChannel))
            { /* �����`���l���l�s�� */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_opeUname + ") �����`���l���l�s��.");
            }

            /*-------- CC�I�y���[�^�֌W�f�[�^�擾���`�F�b�N --------*/
            BranchRow l_branchRow =
                (BranchRow) l_accountManager
                    .getBranch(l_inst, l_opeBcode)
                    .getDataSourceObject();

            if (isLoginStop(l_branchRow))
            { /* ���O�C����~�� */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90201,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "CC�I�y���[�^�̕��X(CODE: " + l_opeBcode + ") ���O�C����~��.");
            }

            if (!checkTraderCode(l_loginReq.ccOperatorCode, l_institutionRow))
            { /* CC�I�y���[�^�R�[�h�l�s�� */
                log.debug(STR_METHOD_NAME + " .... operator code check error.");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "CC�I�y���[�^(CODE: " + l_loginReq.ccOperatorCode + ") �R�[�h�l�s��.");
            }

            /*-------- �ڋq�֌W�f�[�^�擾���`�F�b�N --------*/
            l_branchRow =
                (BranchRow) l_accountManager
                    .getBranch(l_inst, l_userBcode)
                    .getDataSourceObject();
            /* ������ȍ~�A���X�͌ڋq�̂��̂��g�p����B */

            if (isLoginStop(l_branchRow))
            { /* ���O�C����~�� */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90201,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�̕��X(CODE: " + l_userBcode + ") ���O�C����~��.");
            }

            if (!checkAcceptCode(l_loginReq.acceptCode, l_branchRow))
            { /* �ڋq�R�[�h�l�s�� */
                log.debug(STR_METHOD_NAME + " .... user code check error.");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(CODE: " + l_loginReq.acceptCode + ") �R�[�h�l�s��.");
            }

            /*-------- CC�I�y���[�^�E���O�C����񑶍݃`�F�b�N --------*/
            OpLoginAdminService l_adminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            LoginInfo l_loginInfo = l_adminService.getLoginInfo(l_opeUname);
            if (l_loginInfo == null)
            {
                log.debug(STR_METHOD_NAME + " .... LoginInfo not found.");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "CC�I�y���[�^(xNAME: " + l_opeUname + ") ���݂��Ȃ�.");
            }
            LoginInfo l_opeLoginInfo = l_loginInfo;
            log.debug(
                STR_METHOD_NAME
                    + " .... operator login_id = "
                    + l_loginInfo.getLoginId());

            /*-------- �ڋq�E���O�C����񑶍݃`�F�b�N --------*/
            l_loginInfo = l_adminService.getLoginInfo(l_userUname);
            if (l_loginInfo == null)
            {
                log.debug(STR_METHOD_NAME + " .... LoginInfo not found.");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_userUname + ") ���݂��Ȃ�.");
            }
            LoginInfo l_userLoginInfo = l_loginInfo;
            log.debug(
                STR_METHOD_NAME
                    + " .... user login_id = "
                    + l_loginInfo.getLoginId());

            //�ڋq�iDB:�ڋq�}�X�^�j�̃f�[�^���擾����B
            // ��login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B
            LoginRow l_loginRow = LoginDao.findRowByPk(l_loginInfo.getLoginId());
            MainAccountRow l_mainAccountRow =
                (MainAccountRow) l_accountManager.getMainAccount(l_loginRow.getAccountId()).getDataSourceObject();
            log.debug(
                STR_METHOD_NAME
                    + " .... user = "
                    + l_mainAccountRow.getFamilyName());

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
                    "�ڋq(xNAME: " + l_userUname + ") �n�b�V���l�s��.");
            }

            OpLoginSecurityService l_securityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            String l_sessionID = l_securityService.logIn(l_opeUname, l_pwd);
            if (l_sessionID == null)
            { /* CC�I�y���[�^�E���O�C�����s */
                log.debug(
                    STR_METHOD_NAME
                        + " .... operator login failed(authenticate).");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90208,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "CC�I�y���[�^(xNAME: " + l_opeUname + ") �F�؃G���[.");
            }
            log.debug(STR_METHOD_NAME + " .... sessionID(���肷�܂��O) = " + l_sessionID);

            /* �ڋq�ɐ��肷�܂�. */
            l_sessionID = changeAccount(l_loginRow.getAccountId(), l_sessionID);
            log.debug(STR_METHOD_NAME + " .... sessionID(���肷�܂���) = " + l_sessionID);
            
            FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
            TraderRow l_traderRow =
                (TraderRow) l_finMng
                    .getTrader(l_inst, l_opeUcode, l_opeBcode)
                    .getDataSourceObject();
            log.debug(
                STR_METHOD_NAME
                    + " .... operator = "
                    + l_traderRow.getFamilyName());

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
            l_setSessionReq
                .sessionAttributes
                .put(
                    WEB3SessionAttributeDef.BRANCH_ID,
                    Long.toString(l_branchRow.getBranchId()) /* �ڋq�̕��XID */
            );
            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.CCOPERATOR_ID,
                Long.toString(l_traderRow.getTraderId()));

            setSessionAttribute(l_sessionID, l_setSessionReq);

            l_loginRes =
                (WEB3OpeAcceptAutoLoginResponse) l_request.createResponse();
            l_loginRes.xTradeSessionID = l_sessionID;
            l_loginInfo = l_opeLoginInfo;
            l_loginRes.ccOperatorInfo = getTraderInfo(l_traderRow, l_loginInfo);
            l_loginInfo = l_userLoginInfo;
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
                    + ", �I�y���X(CODE: "
                    + l_loginReq.ccOpeBranchCode
                    + ")"
                    + ", �I�y(xNAME: "
                    + l_loginReq.xTradeCCOpeUsername
                    + ")"
                    + ", �ڕ��X(CODE: "
                    + l_loginReq.acceptBranchCode
                    + ")"
                    + ", �ڋq(xNAME: "
                    + l_loginReq.xTradeAcceptUsername
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
