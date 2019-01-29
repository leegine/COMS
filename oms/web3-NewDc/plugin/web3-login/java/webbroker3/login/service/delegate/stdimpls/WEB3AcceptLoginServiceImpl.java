head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptLoginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq���O�C���T�[�r�X(WEB3AcceptLoginServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/25 �e�n(SRA) �V�K�쐬
Revesion History    : 2007/02/12 �h�C (���u) �d�l�ύX���f��No.032
Revesion History    : 2007/07/25 �� (FLJ) �������\�ȕ��@@�ňÍ������ꂽ�p�X���[�h�ŔF�؁@@��ǉ�
Revesion History    : 2007/06/12 �Ӑ� (���u) �d�l�ύX���f��No.033
Revesion History    : 2007/06/12 ���G�� (���u) �d�l�ύX���f��No.033
Revesion History    : 2007/06/18 ���G�� (���u) �d�l�ύX���f��No.035
Revesion History    : 2007/06/28 �����q (���u) �d�l�ύX���f��No.036
Revesion History    : 2008/09/10 �đo�g (���u) �d�l�ύX���f��No.055
Revesion History    : 2008/10/24 ���O (���u) �d�l�ύX���f��No.056
 */
package webbroker3.login.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginLogRecordDef;
import webbroker3.common.define.WEB3LoginRejectIpStatusDef;
import webbroker3.common.define.WEB3LoginRejectipCheckDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3NameSerialNoDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3PwdChangeDef;
import webbroker3.common.define.WEB3PwdCheckFlagDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesPK;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.LoginHistoryDao;
import webbroker3.gentrade.data.LoginHistoryParams;
import webbroker3.gentrade.data.LoginRejectIpRow;
import webbroker3.login.define.WEB3LoginPasswordDef;
import webbroker3.login.message.WEB3AcceptLoginRequest;
import webbroker3.login.message.WEB3AcceptLoginResponse;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.login.service.delegate.WEB3AcceptLoginService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�ڋq���O�C���T�[�r�X)<BR>
 * <BR>
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3AcceptLoginServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3AcceptLoginService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AcceptLoginServiceImpl.class);

    /**
     * @@roseuid 403EE9270073
     */
    public WEB3AcceptLoginServiceImpl()
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
     * �@@�@@�s���Ȓl�̏ꍇ�A�p�X���[�h�G���[��throw����B<BR>
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
     * ���N�G�X�g.�����o�H�敪��IVR�̂Ƃ�<BR> 
     * �@@�@@���O�C������.�������\�p�X���[�h�𕜍������Ď擾����B<BR> 
     * �@@�@@�߂�l���A���O�C��()���̈���.�p�X���[�h�Ɏg�p����<BR> 
     * <BR> 
     * ���O�C���ixTrade�Z�b�V���������j����B<BR>
     * �@@�@@���O�C���G���[�̏ꍇ�A�F�؃G���[�Ƃ��ăp�X���[�h�G���[��throw����B<BR>
     * <BR>
     * �Z�b�V���������ݒ�n���h���Ƀf�B�X�p�b�`���ăZ�b�V����������ݒ肷��B<BR>
     * <BR>
     * �ڋq�iDB:�ڋq�}�X�^�j�̃f�[�^���擾����B<BR>
     * �@@�@@��login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B<BR>
     * <BR>
     * �ڋq���ڋq���ɕϊ�����B<BR>
     * ��Ђ���Џ��ɕϊ�����B<BR>
     * ���X�𕔓X���ɕϊ�����B<BR>
     * �ڋq�}�X�^�̐擪���ID���擾����B<BR>
     * �T�[�r�X���{��Ԏ擾���擾����B<BR>
     * <BR>
     * ���N�G�X�g.�����o�H�敪��IVR�̂Ƃ�<BR> 
     * �@@���ʁD�p�X���[�h�E���[�e�B���e�B���g���ăp�X���[�h�ύX�̕K�v���𔻒肷��B<BR> 
     * �@@�p�X���[�h�ύX���s�v�ȏꍇ<BR> 
     * �@@�@@�@@�ڋq�̍ŏI���O�C���������X�V����B<BR> 
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
     * <BR>
     * �����P�j�@@���X�|���X�ҏW�ɂ���<BR>
     * �i���X�|���X.�p�X���[�h�ύX�t���O == "1"�F�ύX�K�v�j�̏ꍇ�A<BR>
     * ���O�C���^�C�v�������A�p�X���[�h�ŏ����C�p�X���[�h�ő咷�C<BR>
     * �p�X���[�h�`�F�b�N������ҏW����B<BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4016583F039F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AcceptLoginRequest l_loginReq = (WEB3AcceptLoginRequest) l_request;
        WEB3AcceptLoginResponse l_loginRes = null;
        try
        {
            //1) ��ЁiDB:�،���Ёj�̃f�[�^���擾����B���X�iDB:���X�j�̃f�[�^���擾����B
            String l_uname = l_loginReq.xTradeUsername;
            String l_pwd = l_loginReq.acceptPassword;
            String l_icode = l_loginReq.institutionCode;
            String l_bcode = l_loginReq.branchCode;
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

            //�}�V�����t�擾
            //new Date()�Ń}�V�����t�擾����
            Date l_datMachineTime = new Date();

            //2) ���X�����O�C����~��Ԃ��`�F�b�N����B
            //���O�C����~���̏ꍇ�A���O�C����~���G���[��throw����B
            if (isLoginStop(l_branchRow))
            {
                log.debug(STR_METHOD_NAME + ".... ���O�C����~��.");

                ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_90201;

                //����O���A���O�C������o�^
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_errorInfo);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_errorInfo,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���X(CODE: " + l_bcode + ") ���O�C����~��.");
            }
            
            //3) �����`���l�����`�F�b�N����B
            //�s���Ȓl�̏ꍇ�A���̑��F�؃G���[��throw����B
            if (!checkOrderChannel(l_loginReq.orderChannel))
            {
                log.debug(STR_METHOD_NAME + ".... �����`���l���l�s��.");

                ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_90221;

                //����O���A���O�C������o�^
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_errorInfo);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_errorInfo,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_uname + ") �����`���l���l�s��.");
            }

            //is����IP(long, String, String, String)
            //���XID�@@�F�@@getBranch().���XID
            //��ЃR�[�h�@@�F�@@���N�G�X�g.��ЃR�[�h
            //�����o�H�敪�@@�F�@@���N�G�X�g.�����o�H�敪
            //IP�A�h���X�@@�F�@@���N�G�X�g.IP�A�h���X
            boolean l_blnIsRejectIp = this.isRejectIp(
                l_branchRow.getBranchId(),
                l_loginReq.institutionCode,
                l_loginReq.orderRootDiv,
                l_loginReq.ipAddress);
            if (l_blnIsRejectIp)
            {
                log.debug(STR_METHOD_NAME + ".... ����IP�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02821,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "����IP�G���[�B");
            }

            //4) �ڋq�R�[�h�̑Ó������`�F�b�N����B
            //�R�[�h�l�G���[�Ƃ��Ă��̑��F�؃G���[��throw����B
            if (!checkAcceptCode(l_loginReq.acceptCode, l_branchRow))
            {
                log.debug(
                    STR_METHOD_NAME + " .... login failed(code value check).");

                ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_90221;

                //����O���A���O�C������o�^
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_errorInfo);

                throw new WEB3BusinessLayerException(
                    l_errorInfo,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(CODE: " + l_loginReq.acceptCode + ") �R�[�h�l�s��.");
            }
            
            //5) LoginInfo���擾����B
            //�擾�ł��Ȃ������ꍇ�ANotFoundException��throw����B
            OpLoginAdminService l_adminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            LoginInfo l_loginInfo = l_adminService.getLoginInfo(l_uname);
            if (l_loginInfo == null)
            {
                ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_90221;
                //����O���A���O�C������o�^
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_errorInfo);

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

            //���O�C���^�C�v�������擾����
            HashMap loginTypeAttrs = new HashMap();

            Map l_mapLoginTypeAttributes =
                l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
            loginTypeAttrs.putAll(l_mapLoginTypeAttributes);

            //���O�C���������擾����
            Map l_loginAttributes = l_loginInfo.getAttributes();

            //validate���ʔԍ�(String, String, map, map)
            //���ʔԍ��@@�F�@@���N�G�X�g.���q�l���ʔԍ�
            //�����o�H�敪�@@�F�@@���N�G�X�g.�����o�H�敪
            //���O�C���^�C�v�����@@�F�@@getLoginTypeAttributes()�̖߂�l
            //���O�C�������@@�F�@@getLoginAttributes()�̖߂�l
            ProcessingResult l_processingResult =
                this.validateDiscriminationCode(
                    l_loginReq.discriminationCode,
                    l_loginReq.orderRootDiv,
                    l_mapLoginTypeAttributes,
                    l_loginAttributes);

            if (l_processingResult.isFailedResult())
            {
                log.debug(STR_METHOD_NAME +
                    ".... ���O�C���ł��܂���ł����B���͓��e�����m�F�̏�A�ēx���O�C�����ĉ������B");

                //����O���A���O�C������o�^
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_processingResult.getErrorInfo());

                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90221,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���O�C���ł��܂���ł����B���͓��e�����m�F�̏�A�ēx���O�C�����ĉ������B");
            }

            //6) �ڋq���O�C���G���[�񐔁i�L�����j���`�F�b�N����B
            //�����̏ꍇ�A�ڋq���b�N���G���[��throw����B<
            if (!isEnabledUser(l_loginInfo))
            {
                log.debug(STR_METHOD_NAME + ".... ���b�N��.");

                ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_90202;

                //����O���A���O�C������o�^
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_errorInfo);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_errorInfo,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_uname + ") ���b�N��.");
            }

            //���N�G�X�g.�����o�H�敪��IVR�̂Ƃ�
            //���O�C������.�������\�p�X���[�h�𕜍������Ď擾����B
            //�߂�l���A���O�C��()���̈���.�p�X���[�h�Ɏg�p����
            if (WEB3OrderRootDivDef.IVR.equals(l_loginReq.orderRootDiv))
            {
                l_pwd = getPasswordForIVR(l_pwd, l_loginAttributes);
            }

            //20070725 ���@@�Í������ꂽ�p�X���[�h�ŔF�؂�ǉ�
            try
            {
                WEB3DesPasswordCheckUtil.getInstance().checkDesPassword(l_icode,l_pwd,l_loginInfo);
            }
            catch (WEB3BusinessLayerException l_ex)
            {
                //����O���A���O�C������o�^
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_ex.getErrorInfo());
                throw l_ex;
            }

            //7) ���O�C���ixTrade�Z�b�V���������j����B
            //���O�C���G���[�̏ꍇ�A�F�؃G���[�Ƃ��ăp�X���[�h�G���[��throw����B
            OpLoginSecurityService l_securityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            String l_sessionID = l_securityService.logIn(l_uname, l_pwd);
            if (l_sessionID == null)
            {
                log.debug(
                    STR_METHOD_NAME + " .... login failed(authenticate).");

                ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_90203;

                //����O���A���O�C������o�^
                this.insertLoginHistory(
                    l_branchRow.getBranchId(),
                    l_loginReq.institutionCode,
                    l_loginReq.branchCode,
                    l_loginReq.acceptCode,
                    l_loginReq.discriminationCode,
                    l_loginReq.account_id,
                    l_loginReq.ipAddress,
                    l_loginReq.orderRootDiv,
                    l_loginReq.orderChannel,
                    l_datMachineTime,
                    l_errorInfo);

                throw new WEB3BusinessLayerException(
                    l_errorInfo,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_uname + ") �F�؃G���[.");
            }
            log.debug(STR_METHOD_NAME + " .... sessionID = " + l_sessionID);
            
            //8) �Z�b�V���������ݒ�n���h���Ƀf�B�X�p�b�`���ăZ�b�V����������ݒ肷��B
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
            StringTokenizer l_tokenizer = new StringTokenizer(l_sessionID, ":");
            String l_strToken = l_tokenizer.nextToken();
            l_strToken = l_tokenizer.nextToken();
            //���u�� U00720�̎b��Ή� start
//            l_tokenizer = new StringTokenizer(l_strToken, "-");
			//���u�� U00720�̎b��Ή� end
            l_strToken = l_tokenizer.nextToken();
            l_setSessionReq.sessionAttributes.put(
                WEB3SessionAttributeDef.SESSION_ID,
                l_strToken);
            if (l_loginReq.ipAddress != null)
            {
                l_setSessionReq.sessionAttributes.put(
                    WEB3SessionAttributeDef.IP_ADDRESS,
                    l_loginReq.ipAddress);
            }

            setSessionAttribute(l_sessionID, l_setSessionReq);

            //9) �ڋq�iDB:�ڋq�}�X�^�j�̃f�[�^���擾����B
            // ��login_id ���L�[�Ƃ��ă��O�C���e�[�u�����������Aaccount_id���擾����B
            LoginRow l_loginRow = LoginDao.findRowByPk(l_loginInfo.getLoginId());
            MainAccountRow l_mainAccountRow =
                (MainAccountRow) l_accountManager.getMainAccount(l_loginRow.getAccountId()).getDataSourceObject();
            log.debug(
                STR_METHOD_NAME
                    + " .... user = "
                    + l_mainAccountRow.getFamilyName());

            l_loginRes = (WEB3AcceptLoginResponse) l_request.createResponse();
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
            l_loginRes.topPageID = l_mainAccountRow.getTopPageId();
            
            //���N�G�X�g.�����o�H�敪��IVR�̂Ƃ�
            //���ʁD�p�X���[�h�E���[�e�B���e�B���g���ăp�X���[�h�ύX�̕K�v���𔻒肷��
            //�p�X���[�h�ύX���s�v�ȏꍇ �ڋq�̍ŏI���O�C���������X�V����
            if (!WEB3OrderRootDivDef.IVR.equals(l_loginReq.orderRootDiv))
            {
                if (!l_pwdUtil.isChangeNecessity())
                {
                    updateLastLoginTime(l_loginInfo.getLoginId());
                    l_loginRes.passwordChangeFlag = WEB3PwdChangeDef.UNNECESSARY;
                }
                else
                {
                    l_loginRes.passwordChangeFlag = WEB3PwdChangeDef.REQUIRED;
                }
            }
            else
            {
                l_loginRes.passwordChangeFlag = WEB3PwdChangeDef.UNNECESSARY;
            }

            //insert���O�C������
            //���XID�@@�F�@@getBranch().���XID
            //��ЃR�[�h�@@�F�@@���N�G�X�g.��ЃR�[�h
            //���X�R�[�h�@@�F�@@���N�G�X�g.���X�R�[�h
            //�ڋq�R�[�h�@@�F�@@���N�G�X�g.�ڋq�R�[�h
            //���ʔԍ��@@�F�@@���N�G�X�g.���q�l���ʔԍ�
            //�����h�c�@@�F�@@���N�G�X�g.�ڋqID
            //IP�A�h���X�@@�F�@@���N�G�X�g.IP�A�h���X
            //�����o�H�敪�@@�F�@@���N�G�X�g.�����o�H�敪
            //�����`���l���@@�F�@@���N�G�X�g.�����`���l��
            //�}�V�����t�@@�F�@@�擾�����}�V�����t
            //�G���[���@@�F�@@null
            this.insertLoginHistory(
                l_branchRow.getBranchId(),
                l_loginReq.institutionCode,
                l_loginReq.branchCode,
                l_loginReq.acceptCode,
                l_loginReq.discriminationCode,
                l_loginReq.account_id,
                l_loginReq.ipAddress,
                l_loginReq.orderRootDiv,
                l_loginReq.orderChannel,
                l_datMachineTime,
                null);

            //11) �i���X�|���X.�p�X���[�h�ύX�t���O == "1"�F�ύX�K�v�j�̏ꍇ
            if(WEB3PwdChangeDef.REQUIRED.equals(l_loginRes.passwordChangeFlag))
            {
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

    /**
     * (get�p�X���[�hForIVR)<BR>
     * <BR>
     * ���O�C������.�������\�p�X���[�h�𕜍������Ď擾����B <BR>
     * �i�����o�H��IVR�̂Ƃ��̂ݎg�p�j <BR>
     * <BR>
     * �P�j���O�C������.�������\�����p�X���[�h�𕜍��� <BR>
     * <BR>
     * �@@�@@���������\�����p�X���[�h���擾�ł��Ȃ��Ƃ���NotFoundException��throw���� <BR>
     * <BR>
     * �Q�j����.�p�X���[�h�ƕ����������p�X���[�h�������Ƃ� <BR>
     * <BR>
     * �@@�@@���O�C������.�������\�p�X���[�h�𕜍������ă��^�[������ <BR>
     * <BR>
     * �@@�@@���������\�p�X���[�h���擾�ł��Ȃ��Ƃ���NotFoundException��throw���� <BR>
     * <BR>
     * �R�j��L�ȊO�̂Ƃ� <BR>
     * <BR>
     * �@@�@@space4�������^�[������<BR>
     * <BR>
     * @@param l_strPassword - (�p�X���[�h)<BR>
     * @@param l_loginAttributes - (���O�C������)<BR>
     * @@return String
     * @@throws NotFoundException
     */
    public String getPasswordForIVR(String l_strPassword, Map l_loginAttributes) 
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getPasswordForIVR(String, Map)";
        log.entering(STR_METHOD_NAME);

        //�P�j���O�C������.�������\�����p�X���[�h�𕜍���
        //���������\�����p�X���[�h���擾�ł��Ȃ��Ƃ���NotFoundException��throw����
        Object l_objInitialPassword
            = l_loginAttributes.get(WEB3LoginAttributeKeyDef.INITIAL_PASSWORD);
        String l_strDecryptInitialPassword = null;
        if (l_objInitialPassword == null)
        {
            log.debug("�������\�����p�X���[�h���擾�ł��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new NotFoundException("�������\�����p�X���[�h���擾�ł��Ȃ��B");
        }
        else
        {
            WEB3Crypt l_crypt = new WEB3Crypt();
            l_strDecryptInitialPassword = l_crypt.decrypt(l_objInitialPassword.toString());
        }

        //�Q�j����.�p�X���[�h�ƕ����������p�X���[�h�������Ƃ�
        //���O�C������.�������\�p�X���[�h�𕜍������ă��^�[������
        //���������\�p�X���[�h���擾�ł��Ȃ��Ƃ���NotFoundException��throw����
        if (l_strPassword.equals(l_strDecryptInitialPassword))
        {
            Object l_objEncryptedPassword
                = l_loginAttributes.get(WEB3LoginAttributeKeyDef.PASSWORD);
            if (l_objEncryptedPassword == null)
            {
                log.debug("�������\�p�X���[�h���擾�ł��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new NotFoundException("�������\�p�X���[�h���擾�ł��Ȃ��B");
            }
            else
            {
                WEB3Crypt l_crypt = new WEB3Crypt();
                log.exiting(STR_METHOD_NAME);
                return l_crypt.decrypt(l_objEncryptedPassword.toString());
            }
        }

        //�R�j��L�ȊO�̂Ƃ� 
        //space4�������^�[������
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3LoginPasswordDef.SPACE;
        }
    }

    /**
     * (insert���O�C������)<BR>
     * ���O�C�������Ƀf�[�^�o�^����<BR>
     * <BR>
     * �P�j���X�v���t�@@�����X�e�[�u�����A�ȉ��̑����̃��R�[�h���擾����<BR>
     * <BR>
     * �@@�@@[�擾����]<BR>
     * �@@�@@���XID�F����.���XID<BR>
     * �@@�@@�������F"login.log.record."�{����.�����o�H�敪<BR>
     * �@@�@@�A�ԁF1<BR>
     * <BR>
     * �@@�@@�����R�[�h�Ȃ����A���R�[�h�̒l���u���{���Ȃ��v�̏ꍇ�͓����\�b�h<BR>
     * �@@�@@�@@�@@�I��<BR>
     * <BR>
     * �Q�j�擾�������R�[�h�̒l���u���{����v�̂Ƃ��A���O�C������<BR>
     * �@@�@@�e�[�u����insert����<BR>
     * <BR>
     * �@@�@@���ҏW���e�͌ڋq���O�C���|���O�C������.xls�̃��O�C�������|<BR>
     * �@@�@@�@@�@@DB�X�V�i�o�^�j���Q��<BR>
     * <BR>
     * @@param l_lngBranchID - ���XID
     * @@param l_strInstitutionCode - ��ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strDiscriminationCode - ���ʔԍ�
     * @@param l_strAccountID - ����ID
     * @@param l_strIpAddress - IP�A�h���X
     * @@param l_strOrderRootDiv - �����o�H�敪
     * @@param l_strOrderChannel - �����`���l��
     * @@param l_datMachineTime - �}�V�����t
     * @@param l_errorInfo - �G���[���
     * @@throws WEB3BaseException
     */
    protected void insertLoginHistory(
        long l_lngBranchID,
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strDiscriminationCode,
        String l_strAccountID,
        String l_strIpAddress,
        String l_strOrderRootDiv,
        String l_strOrderChannel,
        Date l_datMachineTime,
        ErrorInfo l_errorInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " insertLoginHistory(long, String, String, String, String,"
            + "long, String, String, String, Date, ErrorInfo)";
        log.entering(STR_METHOD_NAME);

        BranchPreferencesPK l_branchPreferencesPK = new BranchPreferencesPK();

        //���XID�F����.���XID
        l_branchPreferencesPK.branch_id = l_lngBranchID;
        //�������F"login.log.record."�{����.�����o�H�敪
        l_branchPreferencesPK.name =
            WEB3BranchPreferencesNameDef.LOGIN_LOG_RECORD + l_strOrderRootDiv;
        //�A�ԁF1
        l_branchPreferencesPK.name_serial_no = Integer.parseInt(WEB3NameSerialNoDef.SERIAL_NO);

        try
        {
            BranchPreferencesRow l_branchPreferencesRow = BranchPreferencesDao.findRowByPk(l_branchPreferencesPK);

            if (l_branchPreferencesRow != null)
            {
                if (WEB3EnforcementDef.NOT_ENFORCEMENT.equals(l_branchPreferencesRow.getValue()))
                {
                    //���R�[�h�̒l���u���{���Ȃ��v�̏ꍇ�͓����\�b�h�I��
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
                else if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchPreferencesRow.getValue()))
                {
                    //�擾�������R�[�h�̒l���u���{����v�̂Ƃ��A���O�C�������e�[�u����insert����
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    LoginHistoryParams l_loginHistoryParams = new LoginHistoryParams();

                    //���O�C������ID
                    l_loginHistoryParams.setLoginHistoryId(LoginHistoryDao.newPkValue());
                    //�،���ЃR�[�h
                    l_loginHistoryParams.setInstitutionCode(l_strInstitutionCode);
                    //���X�R�[�h
                    l_loginHistoryParams.setBranchCode(l_strBranchCode);
                    //�ڋq�R�[�h
                    l_loginHistoryParams.setAccountCode(l_strAccountCode);
                    //���ʔԍ�
                    //���N�G�X�g.���q�l���ʔԍ� != null�͓����ځA�ȊOnull
                    if (l_strDiscriminationCode != null)
                    {
                        l_loginHistoryParams.setDiscriminationCode(l_strDiscriminationCode);
                    }
                    //�����h�c
                    l_loginHistoryParams.setAccountId(
                        l_strAccountID != null ? Long.parseLong(l_strAccountID) : 0
                    );
                    //IP�A�h���X
                    //���N�G�X�g.IP�A�h���X != null�͓����ځA�ȊOnull
                    if (l_strIpAddress != null)
                    {
                        l_loginHistoryParams.setIpAddress(l_strIpAddress);
                    }
                    //�����o�H�敪
                    l_loginHistoryParams.setOrderRootDiv(l_strOrderRootDiv);
                    //�����`���l��
                    l_loginHistoryParams.setOrderChannel(l_strOrderChannel);
                    //���O�C������
                    l_loginHistoryParams.setLoginTimestamp(l_datMachineTime);

                    if (l_errorInfo == null)
                    {
                        //���O�C������
                        //����I�����iErrorInfo == null�j�@@�F�@@0
                        l_loginHistoryParams.setLoginFailure(WEB3LoginLogRecordDef.DEFAULT);

                        //���O�C���G���[���
                        //����I�����iErrorInfo == null�j�@@�F�@@null
                        l_loginHistoryParams.setLoginErrorInfo(null);
                    }
                    else
                    {
                        //���O�C������
                        //�G���[�I�����iErrorInfo != null�j�F�@@1
                        l_loginHistoryParams.setLoginFailure(WEB3LoginLogRecordDef.EXECUTE);

                        //���O�C���G���[���
                        //�G���[�I�����iErrorInfo != null�j�F�@@ErrorInfo.toString()
                        l_loginHistoryParams.setLoginErrorInfo(l_errorInfo.toString());
                    }

                    Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
                    //�쐬���t
                    //���ݎ����i��GtlUtils.getSystemTimestamp()�j
                    l_loginHistoryParams.setCreatedTimestamp(l_tsSystemTimestamp);
                    //�X�V���t
                    //���ݎ����i��GtlUtils.getSystemTimestamp()�j
                    l_loginHistoryParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

                    l_queryProcessor.doInsertQuery(l_loginHistoryParams);
                }
            }
        }
        catch (DataFindException l_ex)
        {
            //���R�[�h�Ȃ��̏ꍇ�͓����\�b�h�I��
            log.debug(l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);

            return;
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is����IP)<BR>
     * ����IP����̃A�N�Z�X�����ۂ���<BR>
     * <BR>
     * �P�j���X�v���t�@@�����X�e�[�u�����A�ȉ��̑����̃��R�[�h���擾����<BR>
     * <BR>
     * �@@�@@[�擾����]<BR>
     * �@@�@@���XID�F����.���XID<BR>
     * �@@�@@�������F"login.rejectip.check."�{����.�����o�H�敪<BR>
     * �@@�@@�A�ԁF1<BR>
     * <BR>
     * �@@�@@�����R�[�h�Ȃ����A���R�[�h�̒l���u�`�F�b�N�Ȃ��v�̏ꍇ��false��ԋp����<BR>
     * <BR>
     * �Q�j�擾�������R�[�h�̒l���u�`�F�b�N����v�̂Ƃ��ȉ��̏������s��<BR>
     * <BR>
     * �@@�@@���O�C������IP tbl���������A�Y��rec����ꍇ��true�A�ȊO��false��ԋp����<BR>
     * <BR>
     * �@@�@@[�擾����]<BR>
     * �@@�@@�،���ЃR�[�h�F����.��ЃR�[�h<BR>
     * �@@�@@IP�A�h���X�F����.IP�A�h���X<BR>
     * �@@�@@�X�e�[�^�X�F0�i�Ώہj<BR>
     * �@@�@@�K�p�J�n���� <= ���ݎ����i��1�j < �K�p�I������<BR>
     * <BR>
     * �@@�@@��1�FGtlUtils.getSystemTimestamp()<BR>
     * @@param l_lngBranchId - (���XID)<BR>
     * ���XID
     * @@param l_strInstitutionCode - (��ЃR�[�h)<BR>
     * ��ЃR�[�h
     * @@param l_strOrderRootDiv - (�����o�H�敪)<BR>
     * �����o�H�敪
     * @@param l_strIpAddress - (IP�A�h���X)<BR>
     * IP�A�h���X
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isRejectIp(
        long l_lngBranchId,
        String l_strInstitutionCode,
        String l_strOrderRootDiv,
        String l_strIpAddress) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isRejectIp(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j���X�v���t�@@�����X�e�[�u�����A�ȉ��̑����̃��R�[�h���擾����
        //�@@�@@[�擾����]
        //�@@�@@���XID�F����.���XID
        //�@@�@@�������F"login.rejectip.check."�{����.�����o�H�敪
        //�@@�@@�A�ԁF1
        //�@@�@@�����R�[�h�Ȃ����A���R�[�h�̒l���u�`�F�b�N�Ȃ��v�̏ꍇ��false��ԋp����
        BranchPreferencesRow l_branchPreferencesRow = null;

        try
        {
            String l_strBranchPreferencesName =
                WEB3BranchPreferencesNameDef.LOGIN_REJECTIP_CHECK + l_strOrderRootDiv;
            l_branchPreferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId,
                    l_strBranchPreferencesName,
                    Integer.parseInt(WEB3NameSerialNoDef.SERIAL_NO));

            //���R�[�h�Ȃ����A���R�[�h�̒l���u�`�F�b�N�Ȃ��v�̏ꍇ��false��ԋp����
            if (l_branchPreferencesRow == null)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                String l_strValue = l_branchPreferencesRow.getValue();
                if (WEB3LoginRejectipCheckDef.DEFAULT.equals(l_strValue))
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }

                //* �Q�j�擾�������R�[�h�̒l���u�`�F�b�N����v�̂Ƃ��ȉ��̏������s��
                //* �@@�@@���O�C������IP tbl���������A�Y��rec����ꍇ��true�A�ȊO��false��ԋp����
                //* �@@�@@[�擾����]
                //* �@@�@@�،���ЃR�[�h�F����.��ЃR�[�h
                //* �@@�@@IP�A�h���X�F����.IP�A�h���X
                //* �@@�@@�X�e�[�^�X�F0�i�Ώہj
                //* �@@�@@�K�p�J�n���� <= ���ݎ����i��1�j < �K�p�I������
                //* �@@�@@��1�FGtlUtils.getSystemTimestamp()
                else if (WEB3LoginRejectipCheckDef.EXECUTE.equals(l_strValue))
                {
                    StringBuffer l_sbQueryString = new StringBuffer();
                    l_sbQueryString.append(" institution_code = ? ");
                    l_sbQueryString.append(" and ip_address = ? ");
                    l_sbQueryString.append(" and status = ? ");
                    l_sbQueryString.append(" and appli_start_timestamp <= ? ");
                    l_sbQueryString.append(" and appli_end_timestamp > ? ");

                    List l_lisValue = new ArrayList();
                    l_lisValue.add(l_strInstitutionCode);
                    l_lisValue.add(l_strIpAddress);
                    l_lisValue.add(WEB3LoginRejectIpStatusDef.OBJECT);

                    Timestamp l_tsNowTime = GtlUtils.getSystemTimestamp();
                    String l_strNowTime =
                        WEB3DateUtility.formatDate(l_tsNowTime, "yyyyMMddHHmmss");
                    Date l_datNowTime = WEB3DateUtility.getDate(l_strNowTime, "yyyyMMddHHmmss");
                    l_lisValue.add(l_datNowTime);
                    l_lisValue.add(l_datNowTime);

                    Object[] l_queryDataContainers = new Object[l_lisValue.size()];

                    l_lisValue.toArray(l_queryDataContainers);

                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    List l_lisLoginRejectIpRecord =
                        l_queryProcessor.doFindAllQuery(
                            LoginRejectIpRow.TYPE,
                            l_sbQueryString.toString(),
                            l_queryDataContainers);

                    if (l_lisLoginRejectIpRecord.isEmpty())
                    {
                        log.exiting(STR_METHOD_NAME);
                        return false;
                    }
                    else
                    {
                        log.exiting(STR_METHOD_NAME);
                        return true;
                    }
                }
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate���ʔԍ�)<BR>
     * ���͂��ꂽ���q�l���ʔԍ����A�o�^�ςݓ��e�Ɠ������`�F�b�N����<BR>
     * <BR>
     * �P�j���O�C���^�C�v����.���q�l���ʔԍ��`�F�b�N�v�ۂɁA<BR>
     * �@@����.�����o�H�敪�̃��R�[�h���Ȃ����A�u�`�F�b�N�Ȃ��v�̏ꍇ<BR>
     * <BR>
     * �@@ProcessingResult.newSuccessResultInstance()�̖߂�l��ԋp����<BR>
     * <BR>
     * �Q�j�擾�������R�[�h�̒l���u�`�F�b�N����v�̂Ƃ��ȉ��̏������s��<BR>
     * <BR>
     * �@@�i�P�j���O�C������.�������\���q�l���ʔԍ����Ȃ�<BR>
     * <BR>
     * �@@�@@�@@���D����.���ʔԍ� != null�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@ProcessingResult.newFailedResultInstance()�̖߂�l��ԋp����<BR>
     * �@@�@@�@@�@@�@@�@@[�ԋp������e]<BR>
     * �@@�@@�@@�@@�@@�@@���q�l���ʔԍ��s���G���[<BR>
     * <BR>
     * �@@�i�Q�j���O�C������.�������\���q�l���ʔԍ�������<BR>
     * <BR>
     * �@@�@@�@@���DWEB3�Í����@@�\�ɂāA����.���ʔԍ����Í���<BR>
     * <BR>
     * �@@�@@�@@���D�Í����������ʔԍ� != ���O�C������.�������\���q�l���ʔԍ��̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@ProcessingResult.newFailedResultInstance()�̖߂�l��ԋp����<BR>
     * �@@�@@�@@�@@�@@�@@[�ԋp������e]<BR>
     * �@@�@@�@@�@@�@@�@@���q�l���ʔԍ�����G���[<BR>
     * <BR>
     * �R�jProcessingResult.newSuccessResultInstance()�̖߂�l��ԋp����<BR>
     * @@param l_strDiscriminationCode - (���ʔԍ�)<BR>
     * ���ʔԍ�
     * @@param l_strOrderRootDiv - (�����o�H�敪)<BR>
     * �����o�H�敪
     * @@param l_mapLoginTypeAttributes - (���O�C���^�C�v����)<BR>
     * ���O�C���^�C�v����
     * @@param l_mapLoginAttributes - (���O�C������)<BR>
     * ���O�C������
     * @@return ProcessingResult
     * @@throws WEB3BaseException
     */
    protected ProcessingResult validateDiscriminationCode(
        String l_strDiscriminationCode,
        String l_strOrderRootDiv,
        Map l_mapLoginTypeAttributes,
        Map l_mapLoginAttributes) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDiscriminationCode(String, String, Map, Map)";
        log.entering(STR_METHOD_NAME);

        // ���͂��ꂽ���q�l���ʔԍ����A�o�^�ςݓ��e�Ɠ������`�F�b�N����
        // �P�j���O�C���^�C�v����.���q�l���ʔԍ��`�F�b�N�v�ۂɁA
        // ����.�����o�H�敪�̃��R�[�h���Ȃ����A�u�`�F�b�N�Ȃ��v�̏ꍇ
        //  �@@ProcessingResult.newSuccessResultInstance()�̖߂�l��ԋp����
        String l_strDiscriminationCodeCheck = (String)l_mapLoginTypeAttributes.get(
            WEB3LoginTypeAttributeKeyDef.DISCRIMINATION_CD_CHECK + l_strOrderRootDiv);
        if (l_strDiscriminationCodeCheck == null
            || WEB3PwdCheckFlagDef.NO_CHECK.equals(l_strDiscriminationCodeCheck))
        {
            log.exiting(STR_METHOD_NAME);
            return ProcessingResult.newSuccessResultInstance();
        }
        else if (WEB3PwdCheckFlagDef.CHECK.equals(l_strDiscriminationCodeCheck))
        {
            //�Q�j�擾�������R�[�h�̒l���u�`�F�b�N����v�̂Ƃ��ȉ��̏������s��
            // �i�P�j���O�C������.�������\���q�l���ʔԍ����Ȃ�
            String l_strEncryptedDiscriminationCode =
                (String)l_mapLoginAttributes.get(WEB3LoginAttributeKeyDef.WEB3_ENCRYPTED_DISCRIMINATION_CD);
            if (l_strEncryptedDiscriminationCode == null)
            {
                //���D����.���ʔԍ� != null�̏ꍇ
                //ProcessingResult.newFailedResultInstance()�̖߂�l��ԋp����
                //[�ԋp������e]
                //���q�l���ʔԍ��s���G���[
                if (l_strDiscriminationCode != null)
                {
                    log.exiting(STR_METHOD_NAME);
                    return ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_02819);
                }
            }
            // �i�Q�j���O�C������.�������\���q�l���ʔԍ�������
            else
            {
                //  ���DWEB3�Í����@@�\�ɂāA����.���ʔԍ����Í���
                WEB3Crypt l_crypt = new WEB3Crypt();
                String l_strEncryptPwd = l_crypt.encrypt(l_strDiscriminationCode);
                //  ���D�Í����������ʔԍ� != ���O�C������.�������\���q�l���ʔԍ��̏ꍇ
                //ProcessingResult.newFailedResultInstance()�̖߂�l��ԋp����
                //�@@[�ԋp������e]
                // ���q�l���ʔԍ�����G���[
                if (!l_strEncryptedDiscriminationCode.equals(l_strEncryptPwd))
                {
                    log.exiting(STR_METHOD_NAME);
                    return ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_02820);
                }
            }
        }

        //�R�jProcessingResult.newSuccessResultInstance()�̖߂�l��ԋp����
        log.exiting(STR_METHOD_NAME);
        return ProcessingResult.newSuccessResultInstance();
    }
}
@
