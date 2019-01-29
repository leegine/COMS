head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.05.27.33;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3c84d8ad6447b3c;
filename	WEB3LoginServiceBaseImpl.java;

1.1
date	2011.03.15.05.24.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3LoginServiceBaseImpl.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name           : ���O�C���T�[�r�X�̃x�[�X�N���X(WEB3LoginServiceBaseImpl.java)
Author Name         : Daiwa Institute of Research Business Innovation
Revesion History    : 2004/05/25 �e�n(SRA) �V�K�쐬
Revesion History    : 2004/07/28 ����(SRA) �Ǘ��҃e�[�u���֘A��DAO��gtl-base����
�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@webbroker3.gentrade.data�ֈړ��������ɔ����Ή�
Revesion History    : 2005/04/05 ��(FLJ) LOGIN_ATTRIBUTE�̍X�V�ɔ����f�b�g���b�N�Ή�
Revesion History    : 2006/06/26 �h�C (���u) QA�uWEB3-login-A-FT-0021�v��Ή�
Revesion History    : 2006/08/24 �h�C (���u)�d�l�ύX�E���f��030
Revesion History    : 2006/09/1 �h�C (���u)�d�l�ύX�E���f��031
Revesion History    : 2006/09/1 �h�C (���u)�w�E������Ή�
Revesion History    : 2007/06/14 ������ (���u)�d�l�ύX�E���f��034
Revesion History    : 2007/07/25 ���n�m (���u)�d�l�ύX�E���f��041
Revesion History    : 2007/08/30 ���n�m (���u)�d�l�ύX�E���f��044
Revesion History    : 2007/10/31 �đo�g (���u) �d�l�ύX�E���f��048
Revesion History    : 2008/01/28 �Ӑ� (���u) �d�l�ύX�E���f��050
Revesion History    : 2008/02/01 �Ӑ� (���u) �d�l�ύX�E���f��051
Revesion History    : 2008/02/05 �Ӑ� (���u) �d�l�ύX�E���f��052
Revesion History    : 2008/02/15 ���g (���u) �d�l�ύX�E���f��054
Revesion History    : 2009/03/12 �Ԑi (���u) �d�l�ύX�E���f��057
Revesion History    : 2010/11/10 �����C (���u) �d�l�ύX�E���f��058
*/
package webbroker3.login.service.delegate.stdimpls;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandlerDispatcher;
import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginPlugin;
import com.fitechlabs.xtrade.plugin.security.oplogin.message.AttachToAccountRequest;
import com.fitechlabs.xtrade.plugin.security.oplogin.message.AttachToAccountResponse;
import com.fitechlabs.xtrade.plugin.security.oplogin.message.SessionRequest;
import com.fitechlabs.xtrade.plugin.security.oplogin.message.SessionResponse;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.gentrade.WEB3ServiceImpState;
import webbroker3.gentrade.data.AccOpenDivRow;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeDao;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DocCategoryManagementRow;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.gentrade.data.EleDeliveryManagementDao;
import webbroker3.gentrade.data.EleDeliveryManagementRow;
import webbroker3.gentrade.data.InstitutionPreferencesDao;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.gentrade.data.SonarTraderRow;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.login.define.WEB3DeliverFlagDivDef;
import webbroker3.login.define.WEB3SecuredLoanAccOpenDivDef;
import webbroker3.login.message.WEB3AcceptInfo;
import webbroker3.login.message.WEB3AccOpenDivUnit;
import webbroker3.login.message.WEB3AdministratorInfo;
import webbroker3.login.message.WEB3BranchInfo;
import webbroker3.login.message.WEB3DocumentDeliverInfoUnit;
import webbroker3.login.message.WEB3InstitutionInfo;
import webbroker3.login.message.WEB3LogoutRequest;
import webbroker3.login.message.WEB3LogoutResponse;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.login.message.WEB3SetSessionResponse;
import webbroker3.login.message.WEB3SonarOperatorInfo;
import webbroker3.login.message.WEB3TraderInfo;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AccountOpenStatusDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3CumulativeAccountDivDef;
import webbroker3.common.define.WEB3DeliveryTargetDef;
import webbroker3.common.define.WEB3DocumentCheckDivDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3ForeignSecAccOpenDiv;
import webbroker3.common.define.WEB3FutOpTradeRegistDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginStopDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3OnlyMobileOpenDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3PTSAccOpenDivDef;
import webbroker3.common.define.WEB3SecuredLoanSecAccOpenDivDef;
import webbroker3.common.define.WEB3SlingshotCheckFlagDef;
import webbroker3.common.define.WEB3StockOptionAccOpenDivDef;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.common.define.WEB3YellowCustomerDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeDao;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeRow;
/**
 * (WEB3���O�C���T�[�r�X�x�[�X)<BR>
 * ���O�C���T�[�r�X�̃x�[�X�N���X<BR>
 * <BR>
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3LoginServiceBaseImpl implements WEB3BusinessService
{

    /**
     * �X�����O�V���b�g���̃n�b�V���l���v�Z���鎞�Ɏg�p����L�[�B<BR>
     * ��ЁA���M�A��M�Ɋւ�炸�Œ�̕������v���O���������ɕێ�����B<BR>
     * <BR>
     * �l�F"0011050220218934748520ZJxwDdj84DDjerIUED4iORIXSecurities0074857517917wqw90H
     * <BR>
     * Sjh4H9Wk345Q9FJreafaesfdsfdsdOKYhkOIIRfhklQWP"<BR>
     */
    protected static final String HASH_CONST =
        "0011050220218934748520ZJxwDdj84DDjerIUED4iORIXSecurities0074857517917wqw90HSjh4H9Wk345Q9FJreafaesfdsfdsdOKYhkOIIRfhklQWP";

    /**
     * �u���v�iDIR��CSK�j�����̃n�b�V���l���w������l
     */
    protected static final int UP_HASH_VALUE = 1;

    /**
     * �u����v�iCSK��DIR�j�����̃n�b�V���l���w������l
     */
    protected static final int DOWN_HASH_VALUE = 2;

    /**
     * �ʏ��Webbroker�V�X�e���p�̃n�b�V���l���w������l
     */
    protected static final int OTHER_HASH_VALUE = 0;

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3LoginServiceBaseImpl.class);

    /**
     * @@roseuid 403EE52A0258
     */
    public WEB3LoginServiceBaseImpl()
    {

    }

    /**
     * (is���O�C����~)<BR>
     * ���X�̃��O�C�����敪���擾����B<BR>
     * ���O�C�����敪���h�����Ȃ��h�̏ꍇ�A���O�C����~�itrue�j��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�h������h�@@�̏ꍇ�A���O�C���\�ifalse�j��Ԃ��B<BR>
     * @@param l_branchRow - ���X<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4017AD04002E
     */
    protected boolean isLoginStop(BranchRow l_branchRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isLoginStop()";
        log.entering(STR_METHOD_NAME);

        String l_stopDiv = l_branchRow.getLoginStopDiv();
        log.debug(STR_METHOD_NAME + " .... LOGIN_STOP_DIV = " + l_stopDiv);
        if (WEB3LoginStopDef.DISABLE.equals(l_stopDiv))
        {
            log.debug(
                STR_METHOD_NAME
                    + " .... ���X(ID: "
                    + l_branchRow.getBranchId()
                    + ") ���O�C����~��");
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (check�����`���l��)<BR>
     * �����`���l����WEB3ChannelDef�ɒ�`����Ă���l�����`�F�b�N����B<BR>
     * ��`����Ă���l�̏ꍇ�Ftrue�A��`����Ă��Ȃ��l�̏ꍇ�Ffalse��Ԃ��B<BR>
     * @@param l_orderChannel - �����`���l��<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     */
    protected boolean checkOrderChannel(String l_orderChannel)
        throws WEB3BaseException
    {
        final String ORDER_CHANNELS[] =
            new String[] {
                WEB3ChannelDef.BRANCH,
                WEB3ChannelDef.INTERNET,
                WEB3ChannelDef.CALL_CENTER,
                WEB3ChannelDef.MOBILE,
                WEB3ChannelDef.FIXED_TIME_AMOUNT };
        final String STR_METHOD_NAME = "checkOrderChannel(String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderChannel == null)
        {
            log.debug(STR_METHOD_NAME + " .... �����`���l����null.");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        for (int idx = 0; idx < ORDER_CHANNELS.length; idx++)
        {
            if (l_orderChannel.equals(ORDER_CHANNELS[idx]))
            { /* �����`���l���͐������l�ł��� */
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.debug(
            STR_METHOD_NAME + " .... �����`���l�����s��.[�l�F" + l_orderChannel + "]");
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (check�ڋq�R�[�h)<BR>
     * ���X����ڋq�R�[�h�ŏ������ő咷�A�ڋq�R�[�h�`�F�b�N���[�h��<BR>
     * �擾����B<BR>
     * check�R�[�h()���\�b�h���g�p���Čڋq�R�[�h���`�F�b�N����B<BR>
     * �Ó��Ȓl�̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * @@param l_inputCode - ���͂��ꂽ�ڋq�R�[�h<BR>
     * @@param l_branchRow - ���X<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 403D76C6009B
     */
    protected boolean checkAcceptCode(String l_inputCode, BranchRow l_branchRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "checkAcceptCode(String)";
        log.entering(STR_METHOD_NAME);

        int l_codeMin = l_branchRow.getAccountCodeMin();
        int l_codeMax = l_branchRow.getAccountCodeMax();
        String l_chkMode = l_branchRow.getAccountCodeCheckMode();
        int l_result =
            WEB3PasswordUtility.checkCode(
                l_codeMin,
                l_codeMax,
                l_chkMode,
                l_inputCode);
        log.exiting(STR_METHOD_NAME);
        return (l_result == WEB3PasswordUtility.CHECK_NORMAL);
    }

    /**
     * (checkCC�I�y���[�^�R�[�h)<BR>
     * �،���Ђ���CC�I�y���[�^�R�[�h�ŏ������ő咷�A<BR>
     * CC�I�y���[�^�R�[�h�`�F�b�N���[�h���擾����B<BR>
     * check�R�[�h()���\�b�h���g�p����CC�I�y���[�^�R�[�h���`�F�b�N����B<BR>
     * �Ó��Ȓl�̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * @@param l_inputCode - ���͂��ꂽCC�I�y���[�^�R�[�h<BR>
     * @@param l_institutionRow - �،����<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 403D76D30280
     */
    protected boolean checkTraderCode(String l_inputCode, InstitutionRow l_institutionRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "checkTraderCode(String)";
        log.entering(STR_METHOD_NAME);

        int l_codeMin = l_institutionRow.getTrdCodeMin();
        int l_codeMax = l_institutionRow.getTrdCodeMax();
        String l_chkMode = l_institutionRow.getTrdCodeCheckMode();
        int l_result =
            WEB3PasswordUtility.checkCode(
                l_codeMin,
                l_codeMax,
                l_chkMode,
                l_inputCode);
        log.exiting(STR_METHOD_NAME);
        return (l_result == WEB3PasswordUtility.CHECK_NORMAL);
    }

    /**
     * (check�Ǘ��҃R�[�h)<BR>
     * �،���Ђ���Ǘ��҃R�[�h�ŏ������ő咷�A<BR>
     * �Ǘ��҃R�[�h�`�F�b�N���[�h���擾����B<BR>
     * check�R�[�h()���\�b�h���g�p����CC�I�y���[�^�R�[�h���`�F�b�N����B<BR>
     * �Ó��Ȓl�̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * @@param l_inputCode - ���͂��ꂽ�Ǘ��҃R�[�h<BR>
     * @@param l_institutionRow - �،����<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A2F0AE0171
     */
    protected boolean checkAdministratorCode(String l_inputCode, InstitutionRow l_institutionRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "checkAdministratorCode(String)";
        log.entering(STR_METHOD_NAME);

        int l_codeMin = l_institutionRow.getAdminCodeMin();
        int l_codeMax = l_institutionRow.getAdminCodeMax();
        String l_chkMode = l_institutionRow.getAdminCodeCheckMode();
        int l_result =
            WEB3PasswordUtility.checkCode(
                l_codeMin,
                l_codeMax,
                l_chkMode,
                l_inputCode);
        log.exiting(STR_METHOD_NAME);
        return (l_result == WEB3PasswordUtility.CHECK_NORMAL);
    }

    /**
     * (�ڋq���肷�܂�)<BR>
     * AttachToAccountRequest�𐶐�����B<BR>
     * ��������AttachToAccountRequest�Ɉ����FaccountID���Z�b�g����B<BR>
     * �����FxTrade�Z�b�V����ID��null�̏ꍇ<BR>
     * �@@�@@AttachToAccountRequest���f�B�X�p�b�`�Ɏg�p���郊�N�G�X�g�Ƃ���B<BR>
     * �����łȂ��ꍇ<BR>
     * �@@�@@�Z�b�V�����E���N�G�X�g�𐶐�����B<BR>
     * �@@�@@�Z�b�V�����E���N�G�X�g��xTrade�Z�b�V����ID���Z�b�g����B<BR>
     * �@@�@@�Z�b�V�����E���N�G�X�g��AttachToAccountRequest���Z�b�g����B<BR>
     * �@@�@@�Z�b�V�����E���N�G�X�g���f�B�X�p�b�`�Ɏg�p���郊�N�G�X�g�Ƃ���B<BR>
     * PlainRequestsHandler�Ƀf�B�X�p�b�`���Čڋq�ɐ��肷�܂��B<BR>
     * <BR>
     * �����FxTrade�Z�b�V����ID��null�̏ꍇ<BR>
     * �@@�@@���ʂ�Response�Ƃ��Ď擾����B<BR>
     * �����łȂ��ꍇ<BR>
     * �@@�@@���ʂ��Z�b�V�����E���X�|���X�Ƃ��Ď擾����B<BR>
     * �@@�@@�Z�L�����e�B�E�G���[���������Ă���ꍇ<BR>
     * �@@�@@�@@�@@�Ԃ��ꂽ�G���[����WEB3SystemLayerException��throw����B<BR>
     * �@@�@@�Z�b�V�����E���X�|���X����Response���擾����B<BR>
     * <BR>
     * Response���m�F����B<BR>
     * �@@�@@CC�I�y���[�^���ڋq�ɐ��肷�܂������������Ă��Ȃ��ꍇ<BR>
     * �@@�@@���Ԃ��ꂽErrorInfo��ErrorCode���AOpLoginPlugin��<BR>
     * �@@�@@��NOT_REACHABLE_ERROR��ErrorCode�Ɠ������ꍇ<BR>
     * �@@�@@�@@�@@���肷�܂������Ȃ��G���[��throw����B<BR>
     * �@@�@@����ȊO�̃G���[�̏ꍇ<BR>
     * �@@�@@�@@�@@�Ԃ��ꂽ�G���[����WEB3BusinessLayerException��throw����B<BR>
     * <BR>
     * Response�ɐݒ肳�ꂽ�A�V�����Z�b�V����ID��Ԃ��B<BR>
     * <BR>
     * @@param l_accountID - ����ID<BR>
     * @@param l_sessionID - �Z�b�V����ID<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@return String
     * @@roseuid 4017B0AD0000
     */
    protected String changeAccount(long l_accountID, String l_sessionID)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "changeAccount(long, String)";
        log.entering(STR_METHOD_NAME);

        Request l_request = new AttachToAccountRequest();
        ((AttachToAccountRequest) l_request).account_id = l_accountID;
        if (l_sessionID != null)
        {
            SessionRequest l_sessionReq = new SessionRequest();
            l_sessionReq.session_id = l_sessionID;
            l_sessionReq.request = new Request[1];
            l_sessionReq.request[0] = l_request;
            l_request = l_sessionReq;
        }

        Response l_response = MessageHandlerDispatcher.Handle(l_request);
        if (l_sessionID != null)
        {
            SessionResponse l_sessionRes = (SessionResponse) l_response;
            if (l_sessionRes.getSecurityError() != null)
            {
                log.debug(
                    STR_METHOD_NAME
                        + " .... exception: "
                        + l_sessionRes.getSecurityError().toString());
                throw new WEB3SystemLayerException(
                    l_sessionRes.getSecurityError(),
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq���肷�܂��ւ̃f�B�X�p�b�`�ŃZ�L�����e�B�E�G���[������");
            }
            l_response = l_sessionRes.response[0];
        }

        if (l_response.server_exception != null)
        {
            log.debug(
                STR_METHOD_NAME
                    + " .... exception: "
                    + l_response.server_exception.toString());

            String l_errorCode = l_response.server_exception.getErrorCode();
            String l_notReachable =
                OpLoginPlugin.NOT_REACHABLE_ERROR.getErrorCode();
            if (l_errorCode.equals(l_notReachable))
            { /* �w��ڋq�ɐ��肷�܂��������Ȃ� */
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90204,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "CC�I�y���[�^�͌ڋq(ID: " + l_accountID + ") �ɐ��肷�܂��������Ȃ�.");
            }
            else
            { /* �����Ȃ��ȊO�̃G���[ */
                throw new WEB3BusinessLayerException(
                    l_response.server_exception,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq���肷�܂���Exception������");
            }
        }

        log.exiting(STR_METHOD_NAME);
        return ((AttachToAccountResponse)l_response).getSessionId();
    }

    /**
     * (is���O�C�����[�U�L��)<BR>
     * �Ώۃ��[�U��disable�ɂȂ��Ă��邩�`�F�b�N����B<BR>
     * �@@disable�̏ꍇ�A����(false)��Ԃ��B<BR>
     * <BR>
     * ���O�C���^�C�v��������Ώۃ��[�U�̃��O�C���G���[�񐔂��擾����B<BR>
     * ���O�C���G���[�񐔂������̏���l�ȏ�̏ꍇ<BR>
     * �@@�@@�Ώۃ��[�U��disable()����B<BR>
     * �@@�@@�����ifalse�j��Ԃ��B<BR>
     * �L���itrue�j��Ԃ��B<BR>
     * @@param l_loginInfo - ���O�C�����<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 403D60C200DA
     */
    protected boolean isEnabledUser(LoginInfo l_loginInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isEnabledUser()";
        log.entering(STR_METHOD_NAME);

        if (l_loginInfo.isDisabled())
        {
            log.debug(
                STR_METHOD_NAME
                    + " .... ���[�U(xTradeName: "
                    + l_loginInfo.getUsername()
                    + ") disabled.");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        OpLoginAdminService l_adminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
        HashMap loginTypeAttrs = new HashMap();
        loginTypeAttrs.putAll(
            l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId()));
        String l_tempErrorMax =
            (String) loginTypeAttrs.get(
                WEB3LoginTypeAttributeKeyDef.LOGIN_ERROR_MAX);
        if (l_tempErrorMax == null)
        {
            log.error(
                STR_METHOD_NAME
                    + " .... ���O�C���G���[�񐔏�����ݒ肳��Ă��Ȃ�.�iLOGIN_ID: "
                    + l_loginInfo.getLoginId()
                    + "�j");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        int l_errorMax = Integer.parseInt(l_tempErrorMax);
        if (l_loginInfo.getFailureCount() >= l_errorMax)
        {
            log.debug(
                STR_METHOD_NAME
                    + " .... ���[�U(xTradeName: "
                    + l_loginInfo.getUsername()
                    + ") ���b�N��");
            l_adminService.disableLogin(l_loginInfo.getLoginId());
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (is�X�����O�V���b�g���p�\)<BR>
     * �ڋq���肷�܂��̏ꍇ�i�����̐��肷�܂��t���O��true�̏ꍇ�j<BR>
     * �@@�@@��Ђ�SS���p�\�`�F�b�N�L���t���O���擾����B<BR>
     * �@@�@@SS���p�\�`�F�b�N�L���t���O���u�`�F�b�N�Ȃ��v�̏ꍇ<BR>
     * �@@�@@�@@�@@�ȍ~�̗��p�\�`�F�b�N���s�킸�A���p�\(true)�Ƃ���B<BR>
     * <BR>
     * ����̓I�Ȕ�����@@�͗L�����̎戵���d�l�̌�����B<BR>
     * <BR>
     * �����𖞂����ꍇ<BR>
     * �@@�@@�X�����O�V���b�g���p�\(true)�Ƃ���B<BR>
     * �����𖞂����Ȃ��ꍇ<BR>
     * �@@�@@�X�����O�V���b�g���p�s�\(false)�Ƃ���B<BR>
     * @@param l_serviceCode<BR>
     * @@param l_isOpeAccept - ���肷�܂��Ftrue�A�ʏ�Ffalse<BR>
     * @@param l_institutionRow - �،����<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4017B38B0290
     */
    protected boolean isRegiSlingshot(
        String l_serviceCode,
        boolean l_isOpeAccept,
        InstitutionRow l_institutionRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isRegiSlingshot(String, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_isOpeAccept)
        { /* �ڋq���肷�܂��̏ꍇ */
            String l_ssCheckFlag = l_institutionRow.getSlingshotCheckFlag();
            if (l_ssCheckFlag.equals(WEB3SlingshotCheckFlagDef.DISABLED))
            { /* ���肷�܂����͗��p�\�`�F�b�N���s��Ȃ��i�������ŗ��p�\�Ƃ���j */
                log.debug(
                    STR_METHOD_NAME + " .... ���肷�܂����X�����O�V���b�g���p�\�`�F�b�N�Ȃ����.���p�\.");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return true;

        // TODO: �L�����̎戵���d�l������Ɏ���
        // try
        // {
        //     QueryProcessor l_qp  = Processors.getDefaultProcessor();
        //     PayinfoRgstPK  l_pk  = new PayinfoRgstPK(mainAccountRow.getAccountId(), l_serviceCode);
        //     PayinfoRgstRow l_row = (PayinfoRgstRow)l_qp.doFindByPrimaryKeyQuery(l_pk);
        //
        //     if ( l_row.getPayinfoRgstStatus().equals(WEB3PayinfoRgstStatusDef.REGISTERED) ||
        //          l_row.getPayinfoRgstStatus().equals(WEB3PayinfoRgstStatusDef.NO_CHARGE ) ||
        //          l_row.getPayinfoRgstStatus().equals(WEB3PayinfoRgstStatusDef.TRIAL     ) )
        //     {   /* �X�����O�V���b�g���p�\ */
        //         log.debug(STR_METHOD_NAME + " .... �X�����O�V���b�g���p�\�i�o�^��ԁF" + l_row.getPayinfoRgstStatus() + "�j");
        //         log.exiting(STR_METHOD_NAME);
        //         return true;
        //     }
        // }
        // catch ( DataFindException e )
        // {   /* �Y���f�[�^�Ȃ� */
        //     log.debug(STR_METHOD_NAME + " .... �Y������L�����o�^�f�[�^�Ȃ�.�X�����O�V���b�g���p�s��.");
        //     log.exiting(STR_METHOD_NAME);
        //     return false;
        // }
        // catch ( DataQueryException e )
        // {   /* DB�A�N�Z�X�G���[ */
        //     throw new WEB3SystemLayerException(
        //         WEB3ErrorCatalog.SYSTEM_ERROR_00002,
        //         getClass().getName() + "." + STR_METHOD_NAME,
        //         e.getMessage() + " .... �L�����o�^���̌�����DataQueryException����.",
        //         e
        //     );
        // }
        // catch ( DataNetworkException e )
        // {   /* �l�b�g���[�N�G���[ */
        //     throw new WEB3SystemLayerException(
        //         WEB3ErrorCatalog.SYSTEM_ERROR_00002,
        //         getClass().getName() + "." + STR_METHOD_NAME,
        //         e.getMessage() + " .... �L�����o�^���̌�����DataNetworkException����.",
        //         e
        //     );
        // }
        //
        // log.debug(STR_METHOD_NAME + " .... �ΏۗL����񂪓o�^����Ă��Ȃ�.�X�����O�V���b�g���p�s��.");
        // log.exiting(STR_METHOD_NAME);
        // return false;
    }

    /**
     * (calc�n�b�V���l)<BR>
     * ���ݓ��t�E�������擾����B<BR>
     * ���ݎ����������̌ߑO4:30�����̏ꍇ�A�O���̓��t������𐶐�����B<BR>
     * ���ݎ����������̌ߑO4:30�ȏ�̏ꍇ�A�����̓��t������𐶐�����B<BR>
     * �������FyyyyMMdd<BR>
     * <BR>
     * ���̗l�Ɍڋq��񕶎�������B<BR>
     * �@@�@@��ЃR�[�h�i2���j�{���X�R�[�h�i3���j�{�ڋq�R�[�h�iCD�t7���j<BR>
     * <BR>
     * �������u���v���w�肵�Ă���ꍇ<BR>
     * �@@�@@��Ђ̑��M���n�b�V���L�[A�AB���擾����B<BR>
     * �������u����v���w�肵�Ă���ꍇ<BR>
     * �@@�@@��Ђ̎�M���n�b�V���L�[A,B���擾����B<BR>
     * �������u���v�ł��u����v�ł��Ȃ��ꍇ<BR>
     * �@@�@@�n�b�V���L�[A�ɒ萔�̃n�b�V���L�[���Z�b�g����B<BR>
     * �@@�@@�n�b�V���L�[B�ɋ󕶎�����Z�b�g����B<BR>
     * �@@�@@�ڋq��񕶎���ɋ󕶎�����Z�b�g����B<BR>
     * <BR>
     * ���̗l�ɁA�n�b�V�����镶������쐬����B<BR>
     * �@@�@@���t������{�n�b�V���L�[A�{�ڋq��񕶎���{�n�b�V���L�[B<BR>
     * Java�W���̃��b�Z�[�W�E�_�C�W�F�X�g�@@�\���g�p����SHA-1�Ńn�b�V������B<BR>
     * ���ʂ�16�i������ɕϊ�����B<BR>
     * �@@�@@���R�[�h��16�i�ɕϊ�����ہA1���̏ꍇ�͐擪��"0"��t����B<BR>
     * <BR>
     * ���ʂ�Ԃ��B<BR>
     * @@param l_isSend - ���iDIR���X�����O�V���b�g�j�F1
     * ����i�X�����O�V���b�g��DIR�j�F2
     * ����ȊO�F0
     * @@param l_institutionRow - �،����<BR>
     * @@param l_branchRow - ���X<BR>
     * @@param l_mainAccountRow - �ڋq<BR>
     * @@return java.lang.String
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4017B3B40196
     */
    protected String calcHashValue(
        int l_isSend,
        InstitutionRow l_institutionRow,
        BranchRow l_branchRow,
        MainAccountRow l_mainAccountRow)
        throws WEB3BaseException
    {
        final SimpleDateFormat l_hashFormat = new SimpleDateFormat("yyyyMMdd");

        final String STR_METHOD_NAME = "calcHashValue(int)";
        log.entering(STR_METHOD_NAME);

        Calendar l_now = Calendar.getInstance();
        int l_hour = l_now.get(Calendar.HOUR_OF_DAY);
        int l_minute = l_now.get(Calendar.MINUTE);
        log.debug(
            STR_METHOD_NAME
                + "now  = "
                + l_now.getTime()
                + " (hour = "
                + l_hour
                + ", minute = "
                + l_minute
                + ")");

        if ((l_hour < 4) || ((l_hour == 4) && (l_minute < 30)))
        { /* �ߑO4:30�����̏ꍇ */
            log.debug(STR_METHOD_NAME + "�n�b�V���l�̌v�Z�ɑO���̓��t���g�p����.");
            l_now.add(Calendar.DAY_OF_MONTH, -1); /* �O���̓��t���g�p���� */
        }
        String l_dateString = l_hashFormat.format(l_now.getTime());
        log.debug(STR_METHOD_NAME + "date = " + l_dateString);

        String l_acceptString =
            l_institutionRow.getInstitutionCode()
                + l_branchRow.getBranchCode()
                + l_mainAccountRow.getAccountCode();
        log.debug(STR_METHOD_NAME + "acpt = " + l_acceptString);

        String l_hashA;
        String l_hashB;
        switch (l_isSend)
        {
            case UP_HASH_VALUE : /* DIR �� CSK */
                l_hashA = l_institutionRow.getHashSendA();
                l_hashB = l_institutionRow.getHashSendB();
                break;
            case DOWN_HASH_VALUE : /* CSK �� DIR */
                l_hashA = l_institutionRow.getHashReceiveA();
                l_hashB = l_institutionRow.getHashReceiveB();
                break;
            default :
                l_hashA = HASH_CONST;
                l_hashB = "";
                l_acceptString = "";
        }
        String l_hashString = l_dateString + l_hashA + l_acceptString + l_hashB;

        String l_hashValue = "";
        try
        {
            byte[] l_byteKey = l_hashString.getBytes();
            MessageDigest l_md = MessageDigest.getInstance("SHA");
            l_md.reset();
            l_md.update(l_byteKey);
            byte[] l_byteValue = l_md.digest();

            StringBuffer l_hashBuffer = new StringBuffer();
            for (int idx = 0; idx < l_byteValue.length; idx++)
            {
                String l_hexCode =
                    Integer.toHexString((int) (l_byteValue[idx] & 0x00FF));
                if (l_hexCode.length() == 1)
                {
                    l_hashBuffer.append("0"); /* "0"��t����2����16�i�\���ɂ��� */
                }
                l_hashBuffer.append(l_hexCode);
            }
            l_hashValue = l_hashBuffer.toString();
            log.debug(STR_METHOD_NAME + "hash_value(str) = " + l_hashValue);
        }
        catch (Exception e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage() + " .... �n�b�V���l�v�Z���ɗ�O������.�ʏ�ł͗L�蓾�Ȃ�.",
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_hashValue;
    }

    /**
     * (update�ŏI���O�C������)<BR>
     * �Ώێ҂̃��O�C�������S�Ă��X�V�p��Map�Ɏ擾����B<BR>
     * ���ݎ������擾���A���O�C���������������̕�����ɕϊ�����B<BR>
     * �ϊ��������ʂōX�V�pMap���̍ŏI���O�C�������̑����l���X�V����B<BR>
     * �X�V�pMap�̓��e�őΏێ҂̃��O�C���������X�V����B<BR>
     * @@param l_loginID - �ڋq�A�܂���CC�I�y���[�^�̃��O�C��ID
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4017B3F901E4
     */
    protected void updateLastLoginTime(final long l_loginID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateLastLoginTime(long)";
        log.entering(STR_METHOD_NAME);

        final Date now = new Date();
//�C���@@���iFLJ�jbegin

//        HashMap l_loginAttr = new HashMap();
//        l_loginAttr.putAll(adminService.getLoginAttributes(l_loginID));
//
//        log.debug(
//            STR_METHOD_NAME
//                + " .... LAST_LOGIN_TIME = "
//                + l_loginAttr.get(WEB3LoginAttributeKeyDef.LAST_LOGIN));
//
//        l_loginAttr.put(
//            WEB3LoginAttributeKeyDef.LAST_LOGIN,
//            WEB3PasswordUtility.loginAttributeDateFormat.format(now));
//        adminService.setLoginAttributes(l_loginID, l_loginAttr);

        try
        {
            final QueryProcessor qp = Processors.getDefaultProcessor();
            qp.doTransaction(QueryProcessor.TX_CREATE_NEW, new TransactionCallback()
            {

                public Object process() throws DataQueryException, DataNetworkException
                {
                    qp.lockAccount(l_loginID, true);
                    LoginAttributeParams params = new LoginAttributeParams();
                    params.setLoginId(l_loginID);
                    params.setAttributeName(WEB3LoginAttributeKeyDef.LAST_LOGIN);
                    params.setAttributeValue(WEB3PasswordUtility.loginAttributeDateFormat.
                                             format(now));
                    LoginAttributeRow row = null;
                    try
                    {
                        row = LoginAttributeDao.findRowByPk(l_loginID,
                            WEB3LoginAttributeKeyDef.LAST_LOGIN);
                    }
                    catch (Exception e)
                    {

                    }

                    if (row == null)
                    {
                        qp.doInsertQuery(params);
                    }
                    else
                    {
                        qp.doUpdateQuery(params);
                    }

                    return null;
                }

            });

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
//�C���@@���iFLJ�jend

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getCC�I�y���[�^���)<BR>
     * CC�I�y���[�^�iDB:���҃}�X�^�j�̃f�[�^���R�s�[���ă��X�|���X�p��CC�I�y���[�^<BR>
     * �����쐬����B<BR>
     * <BR>
     * ���X�|���X�ݒ�<BR>
     * �@@���X�R�[�h�@@�@@�@@�@@�F����.l_traderRow.getBranchCode()�̖߂�l<BR>
     * �@@����ID�@@�@@�@@�@@�@@�@@�F����.l_traderRow.getTraderId()�̖߂�l<BR>
     * �@@����Code�@@�@@�@@�@@�@@�F����.l_traderRow.getTraderCode()�̖߂�l<BR>
     * �@@xTrade���[�U���@@�@@�F����.l_loginInfo.getUsername()�̖߂�l<BR>
     * �@@���Җ��i�����j�@@�@@�F����.l_traderRow.getFamilyName()�̖߂�l<BR>
     * �@@���Җ��i�J�i�j�@@�@@�F����.l_traderRow.getFamilyNameAlt1()�̖߂�l<BR>
     * �@@��s�����ۃt���O�F����.l_traderRow.getAccountOrderFlag()�̖߂�l<BR>
     * �@@�ŏI���O�C�������@@�F����.l_loginInfo.���O�C������.�ŏI���O�C������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���擾�ł��Ȃ����null<BR>`
     * �@@�����R�[�h�@@�@@�@@�@@�F����.l_traderRow.getDepartmentCode()�̖߂�l<BR>
     * <BR>
     * �R�s�[�̉ߒ��ŉ��炩�̗�O�����������ꍇ<BR>
     * �@@�@@�v���I�ȃV�X�e���G���[��throw����B<BR>
     * @@param l_traderRow - ����<BR>
     * @@param l_loginInfo - ���O�C�����<BR>
     * @@return webbroker3.login.message.WEB3TraderInfo
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4017B4FD00CB
     */
    protected WEB3TraderInfo getTraderInfo(
        TraderRow l_traderRow,
        LoginInfo l_loginInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTraderInfo()";
        log.entering(STR_METHOD_NAME);

        WEB3TraderInfo l_traderInfo = new WEB3TraderInfo();
        try
        {
            l_traderInfo.branchCode = l_traderRow.getBranchCode();
            l_traderInfo.traderID = l_traderRow.getTraderId();
            l_traderInfo.traderCode = l_traderRow.getTraderCode();
            l_traderInfo.xTradeUsername = l_loginInfo.getUsername();
            l_traderInfo.nameKanji = l_traderRow.getFamilyName();
            l_traderInfo.nameKana = l_traderRow.getFamilyNameAlt1();
            l_traderInfo.accountOrderFlag = l_traderRow.getAccountOrderFlag();

            String l_lastLogin =
                (String) l_loginInfo.getAttributes().get(
                    WEB3LoginAttributeKeyDef.LAST_LOGIN);
            l_traderInfo.lastLoginTime = null;

            l_traderInfo.departmentCode = l_traderRow.getDepartmentCode();

            if (l_lastLogin != null)
            {
                try
                {
                    l_traderInfo.lastLoginTime =
                        WEB3PasswordUtility.loginAttributeDateFormat.parse(
                            l_lastLogin);
                }
                catch (ParseException pex)
                {
                }
            }
            log.debug(
                STR_METHOD_NAME
                    + " .... lastLogin = "
                    + l_traderInfo.lastLoginTime);
        }
        catch (Exception ex)
        {
            log.debug(STR_METHOD_NAME + " .... exception: " + ex.toString());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                ex.getMessage() + " .... �����ŗ�O���������鎖�͗L�蓾�Ȃ���",
                ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_traderInfo;
    }

    /**
     * (get�ڋq���)<BR>
     *  <BR>
     * �ڋq�iDB:�ڋq�}�X�^�j�̃f�[�^���R�s�[���ă��X�|���X�p�̌ڋq����<BR>
     * �쐬����B�R�s�[�̉ߒ��ŉ��炩�̗�O�����������ꍇ�A<BR>
     * �v���I�ȃV�X�e���G���[��throw����B<BR>
     * �@@<BR>
     * [�ҏW���e]<BR>
     * �����h�c = ����.�ڋq.�����h�c<BR>
     * �ڋq�R�[�h = �����̂U���ڋq�R�[�h<BR>
     * �b�c�t�ڋq�R�[�h = ����.�ڋq.�����R�[�h<BR>
     * ���O�i�����j = ����.�ڋq.���O�i�c���j ��1 �ڋq���i�����j�Ƃ��Ďg�p<BR>
     * ���O�i�J�i�j = ����.�ڋq.���O�i�c���P�j ��2 �ڋq���i�J�i�j�Ƃ��Ďg�p<BR>
     * ���� = ����.�ڋq.����<BR>
     * �ŏI���O�C������ = ����.���O�C�����.getAttributes().get(���O�C��������.�ŏI���O�C������)<BR>
     * �M�p����o�^�t���O = <BR>
     * ��3 ���x�C��ʂ̂ǂ��炩�Ō����J�݂����Ă����true�C<BR>
     * �ǂ�����J�݂��Ă��Ȃ����false�B<BR>
     * �|�i����.�ڋq.���x�M�p�����J�݋敪 == �P�F�����J�� || <BR>
     * �@@�@@����.�ڋq.��ʐM�p�����J�݋敪 == �P�F�����J�݁j�̏ꍇtrue�C<BR>
     * �|�ȊO�Afalse�B<BR>
     * �敨OP����o�^ = <BR>
     * �|�i�ڋq��4.is�敨OP�����J�݁i�敨�^�I�v�V�����敪.�敨�j == false &&<BR>
     * �ڋq��4.is�敨OP�����J�݁i�敨�^�I�v�V�����敪.�I�v�V�����j == true�j�̏ꍇ�A<BR>
     * �P�F�o�^�ς݁iOP��������j<BR>
     * �|�i�ڋq��4.is�敨OP�����J�݁i�敨�^�I�v�V�����敪.�敨�j == true &&<BR>
     * �ڋq��4.is�敨OP�����J�݁i�敨�^�I�v�V�����敪.�I�v�V�����j == false�j�̏ꍇ�A<BR>
     * �P�F�o�^�ς݁i�敨����j<BR>
     * �|�i�ڋq��4.is�敨OP�����J�݁i�敨�^�I�v�V�����敪.�敨�j == true &&<BR>
     * �ڋq��4.is�敨OP�����J�݁i�敨�^�I�v�V�����敪.�I�v�V�����j == true�j�̏ꍇ�A<BR>
     * �P�F�o�^�ς݁i�敨�^OP����j<BR>
     * �|�ȊO�A0:�o�^�Ȃ��B<BR>
     * �ݓ��o�^�t���O = <BR>
     * �i����.�ڋq.�ݓ������J�݋敪 == �ݓ������J�݁j�̏ꍇtrue�C�ȊOfalse<BR>
     * �ב֕ۏ؋������J�݋敪 = <BR>
     *   �|�i����.�ڋq.�e�w�����J�݋敪 == 0�FDEFAULT�j�̏ꍇ�A0�F�@@�����Ȃ��B<BR>
     *   �|�i����.�ڋq.�e�w�����J�݋敪 == 1�F�����J�݁j�̏ꍇ�A1�F�@@�����J�݁B<BR>
     *   �|�i����.�ڋq.�e�w�����J�݋敪 == 2�F���������j�̏ꍇ�A2�F�@@���������B<BR>
     *   �|�i����.�ڋq.�e�w�����J�݋敪 == 3�F�U�֒�~�j�̏ꍇ�A3�F�@@�U�֒�~�B<BR>
     * �O�������A�g�����J�݋敪 = <BR>
     *   �|�i����.�ڋq.�O�������A�g�����J�݋敪 == 0�FDEFAULT�j�̏ꍇ�A0�F�@@�����Ȃ��B<BR>
     *   �|�i����.�ڋq.�O�������A�g�����J�݋敪 == 1�F�����J�݁j�̏ꍇ�A1�F�@@�����J�݁B<BR>
     *   �|�i����.�ڋq.�O�������A�g�����J�݋敪 == 2�F���������j�̏ꍇ�A2�F�@@��������<BR>
     * ���A�������擾�t���O = �ڋq��4.is���A���q()<BR>
     * ��~�t���O = ��5<BR>
     * �x�q�t���O = �i����.�ڋq.�x�q�敪 == �x�q�j�̏ꍇtrue�C�ȊOfalse�B<BR>
     * �O���،������J�݋敪 = <BR>
     *   �|�i����.�ڋq.�O���،������J�݋敪 == 0�F���J�݁j�̏ꍇ�A0�F�@@�����Ȃ��B<BR>
     *   �|�i����.�ڋq.�O���،������J�݋敪 == 1�F�J�݁j�̏ꍇ�A1�F�@@�����J�݁B<BR>
     * �a��،��]�����敪 = <BR>
     *   �|�i�ڋq�]�͏�����6.�a��،��]�����敪 == 0�F�����{�j�̏ꍇ�A0�F�@@�����{�B<BR>
     *   �|�i�ڋq�]�͏�����6.�a��،��]�����敪 == 1�F���{�j�̏ꍇ�A1�F�@@���{�B<BR>
     * �X�g�b�N�I�v�V���������J�݋敪 = <BR>
     *   �|�i����.�ڋq.�X�g�b�N�I�v�V���������J�݋敪 == NULL�j�̏ꍇ�A0�F�@@�����Ȃ��B <BR>
     *   �|�i����.�ڋq.�X�g�b�N�I�v�V���������J�݋敪 == 0�F���J�݁j�̏ꍇ�A0�F�@@�����Ȃ��B<BR> 
     *   �|�i����.�ڋq.�X�g�b�N�I�v�V���������J�݋敪 == 1�F�J�݁j�̏ꍇ�A1�F�@@�����J�݁B <BR>
     *   �|�i����.�ڋq.�X�g�b�N�I�v�V���������J�݋敪�̒l����L�ȊO�j�̏ꍇ�A <BR>
     * �@@�@@�@@�u�p�����[�^�l�s���v�̗�O��throw����B <BR>
     *   class: WEB3SystemLayerException<BR>
     *   tag:  SYSTEM_ERROR_80017<BR>
     *  <BR>
     *  ���o�C����p�����J�݋敪 = <BR>
     *   �|�i����.�ڋq.���o�C����p�����J�݋敪 == NULL�j�̏ꍇ�A0�F�@@�����Ȃ��B <BR>
     *   �|�i����.�ڋq.���o�C����p�����J�݋敪 == 0�F���J�݁j�̏ꍇ�A0�F�@@�����Ȃ��B <BR>
     *   �|�i����.�ڋq.���o�C����p�����J�݋敪 == 1�F�J�݁j�̏ꍇ�A1�F�@@�����J�݁B <BR>
     *   �|�i����.�ڋq.���o�C����p�����J�݋敪�̒l����L�ȊO�j�̏ꍇ�A <BR>
�@@�@@ *�@@     �u�p�����[�^�l�s���v�̗�O��throw����B<BR>
�@@�@@ *   class: WEB3BaseRuntimeException<BR>
�@@�@@ *   tag:  SYSTEM_ERROR_80017<BR>
     *  <BR>
     * �S�ۃ��[�������J�݋敪 = <BR>
     * �@@�|�i����.�ڋq.�،��S�ۃ��[���敪 == NULL�j�̏ꍇ�A0�F�@@�����Ȃ��B<BR>
     * �@@�|�i����.�ڋq.�،��S�ۃ��[���敪 == 0�F���J�݁j�̏ꍇ�A0�F�@@�����Ȃ��B<BR>
     * �@@�|�i����.�ڋq.�،��S�ۃ��[���敪 == 1�F�J�݁j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@1)�����S�ۃ��[�������e�[�u������Y���ڋq�̃f�[�^���擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@����ID = ����.�ڋq.����ID and �J�ݏ� = 2�F����<BR>
     * �@@�@@�@@�@@�@@2)�f�[�^���擾�ł��Ȃ������ꍇ�A0�F�@@�����Ȃ��B<BR>
     * �@@�@@�@@�@@�@@3)�f�[�^���擾�ł����ꍇ�A1�F�@@�����J�݁B<BR>
     * �@@�|�i����.�ڋq.�،��S�ۃ��[���敪�̒l����L�ȊO�j�̏ꍇ�A<BR>
     * �@@�@@�@@�u�p�����[�^�l�s���v�̗�O��throw����B <BR>
     * �@@class : WEB3SystemLayerException<BR>
     * �@@tag   : SYSTEM_ERROR_80017<BR>
     *  <BR>
     * ���ʌ�t�Ǘ���� = this.get���ʌ�t�Ǘ����()�̖߂�l�B <BR>
     * �@@�@@[get���ʌ�t�Ǘ����̈���] <BR>
     * �@@�@@�ڋq�F����.�ڋq <BR>
     * �@@�@@�����o�H�敪�F����.�����o�H�敪 <BR>
     * �@@�@@�M�p����o�^�t���O�F��̏����Ŏ擾�����M�p����o�^�t���O <BR>
     * �@@�@@�敨OP����o�^�F��̏����Ŏ擾�����敨OP����o�^<BR>
     * <BR>
     * SONAR���ҏ�� =<BR>
     * �@@�|�i����.�ڋq.���҃R�[�h�iSONAR�j == NULL�j�̏ꍇ�ANULL�B<BR>
     * �@@�|�i����.�ڋq.���҃R�[�h�iSONAR�j����L�ȊO�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�P�jSONAR���҃}�X�^���Y���f�[�^���擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h = ����.�ڋq.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���X�R�[�h       = ����.�ڋq.���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���҃R�[�h       = ����.�ڋq.���҃R�[�h�iSONAR�j<BR>
     * �@@�@@�@@�@@�Q�j�f�[�^���擾�ł��Ȃ������ꍇ�ANULL�B<BR>
     * �@@�@@�@@�@@�R�j�f�[�^���擾�ł����ꍇ�A�ȉ��̒ʂ�ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@SONAR���ҏ��.���҃R�[�h�@@�@@�@@= SONAR���҃}�X�^.���҃R�[�h<BR>
     * �@@�@@�@@�@@�@@SONAR���ҏ��.���Җ�(�J�i) �@@= SONAR���҃}�X�^.���Җ�(�J�i)<BR>
     * �@@�@@�@@�@@�@@SONAR���ҏ��.���Җ�(����)�@@= SONAR���҃}�X�^.���Җ�(����)<BR>
     * <BR>
     * PTS�����J�݋敪 =<BR>
     * �@@�|�i����.�ڋq.PTS�����J�݋敪 == NULL�j�̏ꍇ�A0�F�@@�����Ȃ��B<BR>
     * �@@�|�i����.�ڋq.PTS�����J�݋敪 == 0�F���J�݁j�̏ꍇ�A0�F�@@�����Ȃ��B<BR>
     * �@@�|�i����.�ڋq.PTS�����J�݋敪 == 1�F�J�݁j�̏ꍇ�A1�F�@@�����J�݁B<BR>
     * �@@�|�i����.�ڋq.PTS�����J�݋敪�̒l����L�ȊO�j�̏ꍇ�A<BR>
     * �@@�@@�@@�u�p�����[�^�l�s���v�̗�O��throw����B<BR>
     * �@@class : WEB3BaseRuntimeException<BR>
     * �@@tag   : SYSTEM_ERROR_80017<BR>
     * <BR>
     * �،���Ѓv���t�@@�����X�e�[�u������A�����J�݋敪���捞���{���擾����B<BR>
     * �@@[����]<BR>
     * �@@�،����ID = ����.�ڋq.�،����ID<BR>
     * �@@And�@@�v���t�@@�����X�� = �v���t�@@�����X��.�����J�݋敪���捞���{<BR>
     * �@@And�@@�v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �����J�݋敪�ꗗ =<BR>
     * �|�i�����J�݋敪���捞���{�f�[�^���擾�ł��Ȃ������j�̏ꍇ�ANULL�B<BR>
     * �|�i�����J�݋敪���捞���{�f�[�^���擾�ł����j�̏ꍇ�Athis.get�����J�݋敪()�̖߂�l�B<BR>
     * �@@�@@�@@[get�����J�݋敪�̈���]<BR>
     * �@@�@@�@@����ID�F����.�ڋq.����ID<BR>
     * <BR>
     * �d�q��t�\���`�F�b�N�t���O = �d�q��t�Ǘ��e�[�u��.�d�q��t�\���`�F�b�N�t���O<BR>
     * �i���d�q��t�Ǘ��e�[�u���Ƀ��R�[�h�����݂��Ȃ��ꍇ�Anull���Z�b�g����B�j<BR>
     * �d�q��t�Ǘ��e�[�u������A�d�q��t�\���`�F�b�N�t���O���擾����B<BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�ڋq.getInstitution().getInstitutionCode() And<BR>
     * �@@���X�R�[�h = ����.�ڋq.getBranch().getBranchCode() And<BR>
     * �@@�����R�[�h = ����.�ڋq.getAccountCode()<BR>
     * <BR>
     * ��4�@@�ڋq�I�u�W�F�N�g<BR>
     * �g���A�J�E���g�}�l�[�W��.get�ڋq(����.�ڋq.�����h�c�Flong)�ɂĎ擾����B<BR>
     *  <BR>
     * ��5�@@��~�t���O<BR>
     * �����`�F�b�N.validate����\�ڋq()���G���[�ɂȂ�ꍇ��true�C�ȊOfalse�B<BR>
     *  <BR>
     * [validate����\�ڋq()�Ɏw�肷�����]<BR>
     * �ڋq�F�@@��4<BR>
     *  <BR>
     * ��6�@@�ڋq�]�͏���<BR>
     * TradingpowerCalcConditionDao.findRowByAccountId(����.�ڋq.�����h�c)�ɂĎ擾����B<BR>
     *  <BR>
     * @@param l_acceptCode - ���͂��ꂽ�U���̌ڋq�R�[�h<BR>
     * @@param l_mainAccountRow - �ڋq<BR>
     * @@param l_loginInfo - ���O�C�����<BR>
     * @@param l_orderRootDiv - �o�H�敪<BR>
     * @@return webbroker3.login.message.WEB3AcceptInfo
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4017B55E02DE
     */
    protected WEB3AcceptInfo getAcceptInfo(
        String l_acceptCode,
        MainAccountRow l_mainAccountRow,
        LoginInfo l_loginInfo,
        String l_orderRootDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAcceptInfo(String)";
        log.entering(STR_METHOD_NAME);

        WEB3AcceptInfo l_acceptInfo = new WEB3AcceptInfo();
        try
        {
            //get �ڋq�I�u�W�F�N�g
            WEB3GentradeMainAccount l_genMainAccount =
                new WEB3GentradeMainAccount(l_mainAccountRow.getAccountId());

            //�����h�c = mainAccountRow.�����h�c
            l_acceptInfo.accountID = l_mainAccountRow.getAccountId();

            //�ڋq�R�[�h = �����̂U���ڋq�R�[�h
            l_acceptInfo.acceptCode = l_acceptCode;

            //�b�c�t�ڋq�R�[�h = mainAccountRow.�����R�[�h
            l_acceptInfo.acceptCodeCD = l_mainAccountRow.getAccountCode();

            l_acceptInfo.xTradeUsername = l_loginInfo.getUsername();

            //���O�i�����j = mainAccountRow.���O�i�c���j ��1 �ڋq���i�����j�Ƃ��Ďg�p
            l_acceptInfo.nameKanji = l_mainAccountRow.getFamilyName();

            //���O�i�J�i�j = mainAccountRow.���O�i�c���P�j ��2 �ڋq���i�J�i�j�Ƃ��Ďg�p
            l_acceptInfo.nameKana = l_mainAccountRow.getFamilyNameAlt1();

            //���� = mainAccountRow.����
            l_acceptInfo.sex = l_mainAccountRow.getSex();

            //�ŏI���O�C������ = loginInfo.getAttributes().get(���O�C��������.�ŏI���O�C������)
            String l_lastLogin =
                (String) l_loginInfo.getAttributes().get(
                    WEB3LoginAttributeKeyDef.LAST_LOGIN);
            l_acceptInfo.lastLoginTime = null;
            if (l_lastLogin != null)
            {
                try
                {
                    l_acceptInfo.lastLoginTime =
                        WEB3PasswordUtility.loginAttributeDateFormat.parse(
                            l_lastLogin);
                }
                catch (ParseException pex)
                {
                }
            }
            log.debug(STR_METHOD_NAME + " .... lastLogin = " + l_acceptInfo.lastLoginTime);

            //�M�p����o�^�t���O
            if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv())
                || WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv()))
            {
                l_acceptInfo.marginRegistFlag = true;
            }
            else
            {
                l_acceptInfo.marginRegistFlag = false;
            }

            //�敨OP����o�^
            if ((l_genMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES) == false)
                && (l_genMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION) == true))
            {
                l_acceptInfo.futOpTradeRegist =
                    WEB3FutOpTradeRegistDef.OP_REGIST;
            }
            else if ((l_genMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES) == true)
                    && (l_genMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION) == false))
            {
                l_acceptInfo.futOpTradeRegist =
                    WEB3FutOpTradeRegistDef.FUT_REGIST;
            }
            else if ((l_genMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES) == true)
                    && (l_genMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION) == true))
            {
                l_acceptInfo.futOpTradeRegist =
                    WEB3FutOpTradeRegistDef.FUT_OP_REGIST;
            }
            else
            {
                l_acceptInfo.futOpTradeRegist = WEB3FutOpTradeRegistDef.NOT_REGIST;
            }

            //�ݓ��o�^�t���O
            //�imainAccountRow.�ݓ������J�݋敪 == �ݓ������J�݁j�̏ꍇtrue�C�ȊOfalse
            if (WEB3CumulativeAccountDivDef.ESTABLISH.equals(l_mainAccountRow.getRuitoAccOpenDiv()))
            {
                l_acceptInfo.ruitoRegistFlag = true;
            }
            else
            {
                l_acceptInfo.ruitoRegistFlag = false;
            }

            //�ב֕ۏ؋������J�݋敪
            String l_strFxAccOpenDiv = l_mainAccountRow.getFxAccOpenDiv();
            if (WEB3AccountOpenDef.OPEN.equals(l_strFxAccOpenDiv)
                || WEB3AccountOpenDef.DELETED.equals(l_strFxAccOpenDiv)
                || WEB3AccountOpenDef.NOT_OPEN.equals(l_strFxAccOpenDiv)
                || WEB3AccountOpenDef.TRANSFER_STOP.equals(l_strFxAccOpenDiv))
            {
                l_acceptInfo.fxAccOpenDiv = l_strFxAccOpenDiv;
            }
            else if(l_strFxAccOpenDiv == null)
            {
                l_acceptInfo.fxAccOpenDiv = WEB3AccountOpenDef.NOT_OPEN;
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "mainAccountRow.�ב֕ۏ؋������J�݋敪 = " + l_strFxAccOpenDiv);
            }

            //�O�������A�g�����J�݋敪
            String l_strFeqConAccOpenDiv = l_mainAccountRow.getFeqConAccOpenDiv();
            if (WEB3AccountOpenDef.OPEN.equals(l_strFeqConAccOpenDiv)
                || WEB3AccountOpenDef.DELETED.equals(l_strFeqConAccOpenDiv)
                || WEB3AccountOpenDef.NOT_OPEN.equals(l_strFeqConAccOpenDiv))
            {
                l_acceptInfo.fstkCooperateAccOpenDiv = l_strFeqConAccOpenDiv;
            }
            else if(l_strFeqConAccOpenDiv == null)
            {
                l_acceptInfo.fstkCooperateAccOpenDiv = WEB3AccountOpenDef.NOT_OPEN;
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "mainAccountRow.�O�������A�g�����J�݋敪 = " + l_strFeqConAccOpenDiv);
            }

            //���A�������擾�t���O = �ڋq��4.is���A���q()
            l_acceptInfo.quotoFlag = l_genMainAccount.isRealCustomer();

            //��~�t���O
            //�����`�F�b�N.validate����\�ڋq()���G���[�ɂȂ�ꍇ��true�C�ȊOfalse
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeOrderValidator l_genOrderValidator =
                (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();
            OrderValidationResult l_orderValidationResult =
                l_genOrderValidator.validateAccountForTrading(l_genMainAccount);
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                l_acceptInfo.stopFlag = true;
            }
            else
            {
                l_acceptInfo.stopFlag = false;
            }

            //�x�q�t���O = �imainAccountRow.�x�q�敪 == �x�q�j�̏ꍇtrue�C�ȊOfalse
            if (WEB3YellowCustomerDef.YELLOW_CUSTOMER.equals(l_mainAccountRow.getYellowCustomer()))
            {
                l_acceptInfo.yellowCustomerFlag = true;
            }
            else
            {
                l_acceptInfo.yellowCustomerFlag = false;
            }
            
            //�O���،������J�݋敪
            String l_strFeAccOpenDiv = l_mainAccountRow.getForeignSecAccOpenDiv();
            if (WEB3ForeignSecAccOpenDiv.NOT_OPEN.equals(l_strFeAccOpenDiv)
                || WEB3ForeignSecAccOpenDiv.OPEN.equals(l_strFeAccOpenDiv))
            {
                l_acceptInfo.feAccOpenDiv = l_strFeAccOpenDiv;
            }
            else if (l_strFeAccOpenDiv == null)
            {
                l_acceptInfo.feAccOpenDiv = WEB3ForeignSecAccOpenDiv.NOT_OPEN;
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "mainAccountRow.�O���،������J�݋敪 = " + l_strFeAccOpenDiv);
            }
            
            //�a��،��]�����敪
            TradingpowerCalcConditionRow l_tradingpowerCalcConditionRow =
                TradingpowerCalcConditionDao.findRowByAccountId(
                    l_mainAccountRow.getAccountId());
            if (l_tradingpowerCalcConditionRow == null)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�]�͏����e�[�u���ɁA����ID[" + l_mainAccountRow.getAccountId() + "]�̃��R�[�h�����݂��܂���B");
            }
            String l_strAssetEvaluationDiv =
                l_tradingpowerCalcConditionRow.getAssetEvaluationDiv();
            if (WEB3EnforcementDef.NOT_ENFORCEMENT.equals(l_strAssetEvaluationDiv)
                || WEB3EnforcementDef.ENFORCEMENT.equals(l_strAssetEvaluationDiv))
            {
                l_acceptInfo.assetEvaluationDiv = l_strAssetEvaluationDiv;
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "TradingpowerCalcConditionRow.�a��،��]�����敪 = " + l_strAssetEvaluationDiv);
            }

            //�X�g�b�N�I�v�V���������J�݋敪
            String l_strStockOptionAccOpenDiv = l_mainAccountRow.getStockOptionAccOpenDiv();
            if (l_strStockOptionAccOpenDiv == null)
            {
                l_acceptInfo.stockOpAccOpenDiv = WEB3StockOptionAccOpenDivDef.NOT_OPEN;
            }
            else if (WEB3StockOptionAccOpenDivDef.NOT_OPEN.equals(l_strStockOptionAccOpenDiv) 
                || WEB3StockOptionAccOpenDivDef.OPEN.equals(l_strStockOptionAccOpenDiv))
            {
                l_acceptInfo.stockOpAccOpenDiv = l_strStockOptionAccOpenDiv;
            }
            else
            {
                log.error("�X�g�b�N�I�v�V���������J�݋敪�F" + l_strStockOptionAccOpenDiv);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "MainAccountRow.�X�g�b�N�I�v�V���������J�݋敪 = " + l_strStockOptionAccOpenDiv);
            }

            //���o�C����p�����J�݋敪
            String l_strOnlyMobileOpenDiv = l_mainAccountRow.getOnlyMobileOpenDiv();
            if (l_strOnlyMobileOpenDiv == null)
            {
                l_acceptInfo.mobileAccOpenDiv = WEB3OnlyMobileOpenDivDef.DEFAULT;
            }
            else if (WEB3OnlyMobileOpenDivDef.DEFAULT.equals(l_strOnlyMobileOpenDiv)
                || WEB3OnlyMobileOpenDivDef.OPEN.equals(l_strOnlyMobileOpenDiv))
            {
                l_acceptInfo.mobileAccOpenDiv = l_strOnlyMobileOpenDiv;
            }
            else
            {
                log.error("���o�C����p�����J�݋敪�F" + l_strOnlyMobileOpenDiv);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "MainAccountRow.���o�C����p�����J�݋敪 = " + l_strOnlyMobileOpenDiv);
            }

            //�S�ۃ��[�������J�݋敪 =
            String l_strSecuredLoanAccOpenDiv = l_mainAccountRow.getSecuredLoanSecAccOpenDiv();
            if (l_strSecuredLoanAccOpenDiv == null)
            {
                //  �|�i����.�ڋq.�،��S�ۃ��[���敪 == NULL�j�̏ꍇ�A0�F�@@�����Ȃ��B
                l_acceptInfo.securedLoanAccOpenDiv = WEB3SecuredLoanAccOpenDivDef.SECURED_LOAN_ACCOUNT_NOT_OPEN;
            }
            else if (WEB3SecuredLoanSecAccOpenDivDef.NOT_OPEN.equals(l_strSecuredLoanAccOpenDiv))
            {
                //  �|�i����.�ڋq.�،��S�ۃ��[���敪 == 0�F���J�݁j�̏ꍇ�A0�F�@@�����Ȃ��B
                l_acceptInfo.securedLoanAccOpenDiv = WEB3SecuredLoanAccOpenDivDef.SECURED_LOAN_ACCOUNT_NOT_OPEN;
            }
            else if (WEB3SecuredLoanSecAccOpenDivDef.OPEN.equals(l_strSecuredLoanAccOpenDiv))
            {
                //  �|�i����.�ڋq.�،��S�ۃ��[���敪 == 1�F�J�݁j�̏ꍇ�A
                //  1)�����S�ۃ��[�������e�[�u������Y���ڋq�̃f�[�^���擾����B
                //        [����]
                //        ����ID = ����.�ڋq.����ID and �J�ݏ� = 2�F����
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                StringBuffer l_sbQueryString = new StringBuffer();
                l_sbQueryString.append(" account_id = ? ");
                l_sbQueryString.append(" and account_open_status = ? ");

                List l_lisValues = new ArrayList();
                l_lisValues.add(new Long(l_mainAccountRow.getAccountId()));
                l_lisValues.add(WEB3AccountOpenStatusDef.PROMISE);

                Object[] l_queryDataContainers = new Object[l_lisValues.size()];

                l_lisValues.toArray(l_queryDataContainers);

                try
                {
                    List l_lisStockSecuredLoanRecords =
                        l_queryProcessor.doFindAllQuery(
                            StockSecuredLoanRow.TYPE,
                            l_sbQueryString.toString(),
                            l_queryDataContainers);

                    if (l_lisStockSecuredLoanRecords.isEmpty())
                    {
                        //  2)�f�[�^���擾�ł��Ȃ������ꍇ�A0�F�@@�����Ȃ��B
                        l_acceptInfo.securedLoanAccOpenDiv = WEB3SecuredLoanAccOpenDivDef.SECURED_LOAN_ACCOUNT_NOT_OPEN;
                    }
                    else
                    {
                        //  3)�f�[�^���擾�ł����ꍇ�A1�F�@@�����J�݁B
                        l_acceptInfo.securedLoanAccOpenDiv = WEB3SecuredLoanAccOpenDivDef.SECURED_LOAN_ACCOUNT_OPEN;
                    }
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            else
            {
                //  �|�i����.�ڋq.�،��S�ۃ��[���敪�̒l����L�ȊO�j�̏ꍇ�A
                //�@@�@@�@@�u�p�����[�^�l�s���v�̗�O��throw����B
                log.error("�S�ۃ��[�������J�݋敪�F" + l_strSecuredLoanAccOpenDiv);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "MainAccountRow.�S�ۃ��[�������J�݋敪 = " + l_strSecuredLoanAccOpenDiv);
            }

            // ���ʌ�t�Ǘ����
            //  [get���ʌ�t�Ǘ����̈���]
            //  �ڋq�F����.�ڋq
            //  �����o�H�敪�F����.�����o�H�敪
            //  �M�p����o�^�t���O�F��̏����Ŏ擾�����M�p����o�^�t���O
            //  �敨OP����o�^�F��̏����Ŏ擾�����敨OP����o�^
            l_acceptInfo.documentDeliverList = this.getDocumentDeliverInfoUnit(
                l_mainAccountRow,
                l_orderRootDiv,
                l_acceptInfo.marginRegistFlag,
                l_acceptInfo.futOpTradeRegist);

            //SONAR���ҏ��
            if (l_mainAccountRow.getSonarTraderCode() != null)
            {
                StringBuffer l_sbSql = new StringBuffer();
                l_sbSql.append(" institution_code = ? ");
                l_sbSql.append(" and branch_code = ? ");
                l_sbSql.append(" and sonar_trader_code = ? ");

                Object[] l_sbValues = new Object[]{
                    l_mainAccountRow.getInstitutionCode(),
                    l_mainAccountRow.getBranchCode(),
                    l_mainAccountRow.getSonarTraderCode()};

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisResults = null;
                try
                {
                    l_lisResults = l_queryProcessor.doFindAllQuery(
                        SonarTraderRow.TYPE,
                        l_sbSql.toString(),
                        l_sbValues);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                if (l_lisResults.size() != 0)
                {
                    //�f�[�^���擾�ł����ꍇ�A�ȉ��̒ʂ�ݒ肷��B
                    SonarTraderRow l_sonarTraderRow =
                        (SonarTraderRow)l_lisResults.get(0);
                    WEB3SonarOperatorInfo l_sonarOperatorInfo =
                        new WEB3SonarOperatorInfo();
                    //SONAR���ҏ��.���҃R�[�h�@@�@@�@@= SONAR���҃}�X�^.���҃R�[�h
                    l_sonarOperatorInfo.operatorCode = l_sonarTraderRow.getSonarTraderCode();

                    //SONAR���ҏ��.���Җ�(�J�i) �@@= SONAR���҃}�X�^.���Җ�(�J�i)
                    l_sonarOperatorInfo.nameKana = l_sonarTraderRow.getFamilyNameAlt1();

                    //SONAR���ҏ��.���Җ�(����)�@@= SONAR���҃}�X�^.���Җ�(����)
                    l_sonarOperatorInfo.nameKanji = l_sonarTraderRow.getFamilyName();

                    l_acceptInfo.sonarOperatorInfo = l_sonarOperatorInfo;
                }
            }

            //PTS�����J�݋敪 =
            //�|�i����.�ڋq.PTS�����J�݋敪 == NULL�j�̏ꍇ�A0�F�@@�����Ȃ��B
            if (l_mainAccountRow.getPtsAccOpenDiv() == null)
            {
                l_acceptInfo.ptsAccOpenDiv = WEB3PTSAccOpenDivDef.DEFAULT;
            }
            //�|�i����.�ڋq.PTS�����J�݋敪 == 0�F���J�݁j�̏ꍇ�A0�F�@@�����Ȃ��B
            else if (WEB3PTSAccOpenDivDef.DEFAULT.equals(l_mainAccountRow.getPtsAccOpenDiv()))
            {
                l_acceptInfo.ptsAccOpenDiv = WEB3PTSAccOpenDivDef.DEFAULT;
            }
            //�|�i����.�ڋq.PTS�����J�݋敪 == 1�F�J�݁j�̏ꍇ�A1�F�@@�����J�݁B
            else if (WEB3PTSAccOpenDivDef.ACCOUNT_OPEN.equals(l_mainAccountRow.getPtsAccOpenDiv()))
            {
                l_acceptInfo.ptsAccOpenDiv = WEB3PTSAccOpenDivDef.ACCOUNT_OPEN;
            }
            else
            {
                log.debug("�p�����[�^�l�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�p�����[�^�l�s���B");
            }

            //�،���Ѓv���t�@@�����X�e�[�u������A�����J�݋敪���捞���{���擾����B
            //����:
            //�،����ID = ����.�ڋq.�،����ID
            //�v���t�@@�����X�� = �v���t�@@�����X��.�����J�݋敪���捞���{
            //�v���t�@@�����X���̘A�� = 1
            InstitutionPreferencesRow l_institutionPreferencesRow = null;
            try
            {
                l_institutionPreferencesRow =
                    InstitutionPreferencesDao.findRowByInstitutionIdNameNameSerialNo(
                    l_mainAccountRow.getInstitutionId(),
                    WEB3InstitutionPreferencesNameDef.ACCOUNTOPEN_INFO_TAKING_DIV,
                    1);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);

                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //�����J�݋敪�ꗗ =
            //�|�i�����J�݋敪���捞���{�f�[�^���擾�ł��Ȃ������j�̏ꍇ�ANULL�B
            //�|�i�����J�݋敪���捞���{�f�[�^���擾�ł����j�̏ꍇ�Athis.get�����J�݋敪()�̖߂�l�B
            //[get�����J�݋敪�̈���]
            //����ID�F����.�ڋq.����ID
            if (l_institutionPreferencesRow != null)
            {
            	l_acceptInfo.accOpenDivList = this.getAccOpenDivUnit(l_mainAccountRow.getAccountId());
            }
            else
            {
            	l_acceptInfo.accOpenDivList = null;
            }

            //�d�q��t�\���`�F�b�N�t���O = �d�q��t�Ǘ��e�[�u��.�d�q��t�\���`�F�b�N�t���O
            //�i���d�q��t�Ǘ��e�[�u���Ƀ��R�[�h�����݂��Ȃ��ꍇ�Anull���Z�b�g����B�j
            //�d�q��t�Ǘ��e�[�u������A�d�q��t�\���`�F�b�N�t���O���擾����B
            //�@@[����]
            //�@@�،���ЃR�[�h = ����.�ڋq.getInstitution().getInstitutionCode() And
            //�@@���X�R�[�h = ����.�ڋq.getBranch().getBranchCode() And
            //�@@�����R�[�h = ����.�ڋq.getAccountCode()
            EleDeliveryManagementRow l_eleDeliveryManagementRow = null;

            try
            {
                l_eleDeliveryManagementRow =
                    EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                        l_mainAccountRow.getInstitutionCode(),
                        l_mainAccountRow.getBranchCode(),
                        l_mainAccountRow.getAccountCode());
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            if (l_eleDeliveryManagementRow != null)
            {
                //�d�q��t�\���`�F�b�N�t���O = �d�q��t�Ǘ��e�[�u��.�d�q��t�\���`�F�b�N�t���O
                l_acceptInfo.edCheckFlag =
                    String.valueOf(l_eleDeliveryManagementRow.getEleDelRegiFlag());
            }
            else
            {
                //�d�q��t�Ǘ��e�[�u���Ƀ��R�[�h�����݂��Ȃ��ꍇ�Anull���Z�b�g����B
                l_acceptInfo.edCheckFlag = null;
            }
        }
        catch (Exception ex)
        {
        	// catch�����I�u�W�F�N�g���Ɩ��G���[�̏ꍇ�A��O�I�u�W�F�N�g�����̂܂܃X���[�B
        	// �ȊO�A�V�X�e���G���[���X���[����B
        	if (ex instanceof WEB3BaseException)
        	{
        		throw (WEB3BaseException)ex;
        	}
        	else
        	{
				log.error(STR_METHOD_NAME, ex);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80002,
					getClass().getName() + "." + STR_METHOD_NAME,
					ex.getMessage() + " .... �����ŗ�O���������鎖�͗L�蓾�Ȃ���",
					ex);
        	}
        }

        log.exiting(STR_METHOD_NAME);
        return l_acceptInfo;
    }

    /**
     * (get�Ǘ��ҏ��)<BR>
     *  <BR>
     * �Ǘ��ҁiDB:�Ǘ��҃}�X�^�C�Ǘ��҃^�C�v�j�̃f�[�^���R�s�[���ă��X�|���X�p<BR>
     * �̊Ǘ��ҏ����쐬����B<BR>
     *  <BR>
     * [�ҏW���e] <BR>
     * �Ǘ��҂h�c = ����.�Ǘ���.�Ǘ��҂h�c<BR>
     * �Ǘ��҃R�[�h = ����.�Ǘ���.�Ǘ��҃R�[�h<BR>
     * xTrade���[�U�� = ����.���O�C��.getUserName()<BR>
     * �Ǘ��Җ� = ����.�Ǘ���.�Ǘ��Җ�<BR>
     * �������x�� = ����.�Ǘ���.�������x��<BR>
     * �ŏI���O�C������ = ����.���O�C��.getAttributes().get(���O�C��������.�ŏI���O�C������)<BR>
     * �S���X���t���O = (*1)�Ǘ��҃^�C�v�s.�S���X���t���O<BR>
     * DIR�Ǘ��҃t���O = (*1)�Ǘ��҃^�C�v�s.DIR�Ǘ��҃t���O<BR>
     * <BR>
     * (*1) �Ǘ��҃^�C�v�s�̎擾<BR>
     * �Ǘ��҃^�C�v�e�[�u�����ȉ��̏����Ō������A�Y���s���擾����B<BR>
     * <BR>
     * [����]<BR>
     * �Ǘ��҃^�C�v�e�[�u��.�،���ЃR�[�h = ����.�Ǘ���.�،���ЃR�[�h<BR>
     * �Ǘ��҃^�C�v�e�[�u��.�������x�� = ����.�Ǘ���.�������x��<BR>
     * <BR>
     * �R�s�[�̉ߒ��ŉ��炩�̗�O�����������ꍇ�A<BR>
     * �v���I�ȃV�X�e���G���[��throw����B<BR>
     * <BR>
     * @@param l_administratorRow - �Ǘ���<BR>
     * @@param l_loginInfo - ���O�C�����<BR>
     * @@return webbroker3.login.message.WEB3AdministratorInfo
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A2F0B20019
     */
    protected WEB3AdministratorInfo getAdministratorInfo(
        AdministratorRow l_administratorRow,
        LoginInfo l_loginInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdministratorInfo()";
        log.entering(STR_METHOD_NAME);

        WEB3AdministratorInfo l_administratorInfo = new WEB3AdministratorInfo();
        try
        {
            //�Ǘ��҃^�C�v�s�̎擾
            AdministratorTypeRow l_administratorTypeRow =
                AdministratorTypeDao.findRowByPk(
                    l_administratorRow.getInstitutionCode(),
                    l_administratorRow.getPermissionLevel());

            //�Ǘ��҂h�c = administratorRow.�Ǘ��҂h�c
            l_administratorInfo.administratorID =
                l_administratorRow.getAdministratorId();
            //�Ǘ��҃R�[�h = administratorRow.�Ǘ��҃R�[�h
            l_administratorInfo.administratorCode =
                l_administratorRow.getAdministratorCode();
            //xTrade���[�U�� = loginInfo.getUserName()
            l_administratorInfo.xTradeUsername = l_loginInfo.getUsername();
            //�Ǘ��Җ� = administratorRow.�Ǘ��Җ�
            l_administratorInfo.administratorName = l_administratorRow.getName();
            //�������x�� = administratorRow.�������x��
            l_administratorInfo.permissionLevel =
                l_administratorRow.getPermissionLevel();
            //�ŏI���O�C������ = loginInfo.getAttributes().get(���O�C��������.�ŏI���O�C������)
            String l_lastLogin =
                (String) l_loginInfo.getAttributes().get(
                    WEB3LoginAttributeKeyDef.LAST_LOGIN);
            l_administratorInfo.lastLoginTime = null;
            if (l_lastLogin != null)
            {
                try
                {
                    l_administratorInfo.lastLoginTime =
                        WEB3PasswordUtility.loginAttributeDateFormat.parse(
                            l_lastLogin);
                }
                catch (ParseException pex)
                {
                }
            }
            log.debug(STR_METHOD_NAME + " .... lastLogin = " + l_administratorInfo.lastLoginTime);

            //�S���X���t���O = (*1)�Ǘ��҃^�C�v�s.�S���X���t���O
            if (BooleanEnum.TRUE.equals(l_administratorTypeRow.getAllBranchPermissionFlag()))
            {
                l_administratorInfo.allBranchPermissionFlag = true;
            }
            else
            {
                l_administratorInfo.allBranchPermissionFlag = false;
            }
            // DIR�Ǘ��҃t���O = (*1)�Ǘ��҃^�C�v�s.DIR�Ǘ��҃t���O
            l_administratorInfo.dirAdminFlag = 
                String.valueOf(l_administratorTypeRow.getDirAdminFlag());

        }
        catch (Exception ex)
        {
            log.error(STR_METHOD_NAME, ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                ex.getMessage() + " .... �����ŗ�O���������鎖�͗L�蓾�Ȃ���",
                ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_administratorInfo;
    }

    /**
     * (get��Џ��)<BR>
     * ��ЁiDB:�،���Ёj�̃f�[�^���R�s�[����<BR>
     * ���X�|���X�p�̉�Џ����쐬����B<BR>
     * �R�s�[�̉ߒ��ŉ��炩�̗�O�����������ꍇ<BR>
     * �@@�@@�v���I�ȃV�X�e���G���[��throw����B<BR>
     * @@param l_institutionRow - �،����<BR>
     * @@return webbroker3.login.message.WEB3InstitutionInfo
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4017B5A1033C
     */
    protected WEB3InstitutionInfo getInstitutionInfo(
        InstitutionRow l_institutionRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInstitutionInfo()";
        log.entering(STR_METHOD_NAME);

        WEB3InstitutionInfo l_instInfo = new WEB3InstitutionInfo();
        try
        {
            l_instInfo.institutionID = l_institutionRow.getInstitutionId();
            l_instInfo.institutionCode = l_institutionRow.getInstitutionCode();
            l_instInfo.institutionName = l_institutionRow.getInstitutionName();
        }
        catch (Exception ex)
        {
            log.debug(STR_METHOD_NAME + " .... exception: " + ex.toString());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                ex.getMessage() + " .... �����ŗ�O���������鎖�͗L�蓾�Ȃ���",
                ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_instInfo;
    }

    /**
     * (get���X���)<BR>
     * ���X�iDB:���X�j�̃f�[�^���R�s�[����<BR>
     * ���X�|���X�p�̕��X�����쐬����B<BR>
     * �R�s�[�̉ߒ��ŉ��炩�̗�O�����������ꍇ<BR>
     * �@@�@@�v���I�ȃV�X�e���G���[��throw����B<BR>
     * @@param l_branchRow - ���X<BR>
     * @@return webbroker3.login.message.WEB3BranchInfo
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40762E4F02B1
     */
    protected WEB3BranchInfo getBranchInfo(
        BranchRow l_branchRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBranchInfo()";
        log.entering(STR_METHOD_NAME);

        WEB3BranchInfo l_branchInfo = new WEB3BranchInfo();
        try
        {
            l_branchInfo.branchID = l_branchRow.getBranchId();
            l_branchInfo.branchCode = l_branchRow.getBranchCode();
            l_branchInfo.branchName = l_branchRow.getBranchName();
        }
        catch (Exception ex)
        {
            log.debug(STR_METHOD_NAME + " .... exception: " + ex.toString());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                ex.getMessage() + " .... �����ŗ�O���������鎖�͗L�蓾�Ȃ���",
                ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_branchInfo;
    }

    /**
     * (get�T�[�r�X���{���)<BR>
     * ���ID�A���XID�A����ID�ŃT�[�r�X���{��Ԃ𐶐�����B<BR><BR>
     * @@param l_institutionRow - �،����<BR>
     * @@param l_branchRow - ���X<BR>
     * @@param l_mainAccountRow - �ڋq<BR>
     * @@return WEB3ServiceImpState
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4045B47A00D6
     */
    protected WEB3ServiceImpState getServiceImpState(
        InstitutionRow l_institutionRow,
        BranchRow l_branchRow,
        MainAccountRow l_mainAccountRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getServiceImpState()";
        log.entering(STR_METHOD_NAME);

        WEB3ServiceImpState l_serviceImp;
        try
        {
            l_serviceImp =
                new WEB3ServiceImpState(
                    l_institutionRow.getInstitutionId(),
                    l_branchRow.getBranchId(),
                    l_mainAccountRow.getAccountId());
        }
        catch (WEB3BaseException ex)
        {
            log.debug(STR_METHOD_NAME + " .... exception: " + ex.toString());
            throw ex;
        }
        catch (Exception ex)
        {
            log.debug(STR_METHOD_NAME + " .... exception: " + ex.toString());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                ex.getMessage() + " .... �����ŗ�O���������鎖�͗L�蓾�Ȃ���",
                ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_serviceImp;
    }

    /**
     * (set�Z�b�V��������)<BR>
     * �Z�b�V�����E���N�G�X�g�𐶐�����B<BR>
     * �����̃Z�b�V����ID�A�Z�b�V���������ݒ胊�N�G�X�g�C���X�^���X��<BR>
     * �Z�b�V�����E���N�G�X�g�ɃZ�b�g����B<BR>
     * <BR>
     * �Z�b�V���������ݒ�n���h���Ƀf�B�X�p�b�`����B<BR>
     * <BR>
     * �Z�L�����e�B�E�G���[�ɂ���Ď��s�����ꍇ<BR>
     * �@@�@@�Ԃ��ꂽ�G���[����WEB3SystemLayerException��throw����B<BR>
     * ����ȊO�̃G���[�ɂ���Ď��s�����ꍇ<BR>
     * �@@�@@�Ԃ��ꂽ�G���[����WEB3BusinessLayerException��throw����B<BR>
     * @@param l_sessionID - xTrade�Z�b�V����ID
     * @@param l_request - �Z�b�V���������ݒ胊�N�G�X�g
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4045B4EF02CA
     */
    protected void setSessionAttribute(
        String l_sessionID,
        WEB3SetSessionRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "setSessionAttribute(String, WEB3SetSessionRequest)";
        log.entering(STR_METHOD_NAME);

        SessionRequest l_sessionReq = new SessionRequest();
        l_sessionReq.session_id = l_sessionID;
        l_sessionReq.request = new Request[1];
        l_sessionReq.request[0] = l_request;

        SessionResponse l_sessionRes =
            (SessionResponse) MessageHandlerDispatcher.Handle(l_sessionReq);
        if (l_sessionRes.getSecurityError() != null)
        {
            log.debug(
                STR_METHOD_NAME
                    + " .... exception: "
                    + l_sessionRes.getSecurityError().toString());
            throw new WEB3SystemLayerException(
                l_sessionRes.getSecurityError(),
                getClass().getName() + "." + STR_METHOD_NAME,
                "�Z�b�V���������ݒ�n���h���ւ̃f�B�X�p�b�`�ŃZ�L�����e�B�E�G���[������");
        }

        WEB3SetSessionResponse l_setSessionRes =
            (WEB3SetSessionResponse) l_sessionRes.response[0];
        if (l_setSessionRes.errorInfo != null)
        {
            log.debug(
                STR_METHOD_NAME
                    + " .... exception: "
                    + l_setSessionRes.errorInfo.toString());
            throw new WEB3BusinessLayerException(
                l_setSessionRes.errorInfo,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�Z�b�V���������ݒ�n���h����Exception������");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �idispatch���O�A�E�g�j<BR>
     * ���O�A�E�g�E���N�G�X�g�𐶐�����B<BR>
     * �����FxTrade�Z�b�V����ID��null�̏ꍇ<BR>
     * �@@�@@���O�A�E�g�E���N�G�X�g���f�B�X�p�b�`�Ɏg�p���郊�N�G�X�g�Ƃ���B<BR>
     * �����łȂ��ꍇ<BR>
     * �@@�@@�Z�b�V�����E���N�G�X�g�𐶐�����B<BR>
     * �@@�@@�Z�b�V�����E���N�G�X�g�Ɉ����FxTrade�Z�b�V����ID���Z�b�g����B<BR>
     * �@@�@@�Z�b�V�����E���N�G�X�g�Ƀ��O�A�E�g�E���N�G�X�g���Z�b�g����B<BR>
     * �@@�@@�Z�b�V�����E���N�G�X�g���f�B�X�p�b�`�Ɏg�p���郊�N�G�X�g�Ƃ���B<BR>
     * ���O�A�E�g�E�n���h���Ƀf�B�X�p�b�`����B<BR>
     * <BR>
     * �����FxTrade�Z�b�V����ID��null�̏ꍇ<BR>
     * �@@�@@���ʂ����O�A�E�g�E���X�|���X�Ƃ��Ď擾����B<BR>
     * �����łȂ��ꍇ<BR>
     * �@@�@@���ʂ��Z�b�V�����E���X�|���X�Ƃ��Ď擾����B<BR>
     * �@@�@@�Z�L�����e�B�E�G���[���������Ă���ꍇ<BR>
     * �@@�@@�@@�@@�Ԃ��ꂽ�G���[����WEB3SystemLayerException��throw����B<BR>
     * �@@�@@�Z�b�V�����E���X�|���X���烍�O�A�E�g�E���X�|���X���擾����B<BR>
     * <BR>
     * ���O�A�E�g�E���X�|���X���m�F���A���s���Ă���ꍇ��<BR>
     * �@@�@@�Ԃ��ꂽ�G���[����WEB3BusinessLayerException��throw����B<BR>
     * @@param l_sessionID - xTrade�Z�b�V����ID
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40640EA902ED
     */
    protected void dispatchLogout(String l_sessionID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "dispatchLogout(String)";
        log.entering(STR_METHOD_NAME);

        Request l_request = new WEB3LogoutRequest();
        if (l_sessionID != null)
        {
            SessionRequest l_sessionReq = new SessionRequest();
            l_sessionReq.session_id = l_sessionID;
            l_sessionReq.request = new Request[1];
            l_sessionReq.request[0] = l_request;
            l_request = l_sessionReq;
        }

        WEB3LogoutResponse l_logoutRes = null;
        Response l_response = MessageHandlerDispatcher.Handle(l_request);
        if (l_sessionID == null)
        {
            l_logoutRes = (WEB3LogoutResponse) l_response;
        }
        else
        {
            SessionResponse l_sessionRes = (SessionResponse) l_response;
            if (l_sessionRes.getSecurityError() != null)
            {
                log.debug(
                    STR_METHOD_NAME
                        + " .... exception: "
                        + l_sessionRes.getSecurityError().toString());
                throw new WEB3SystemLayerException(
                    l_sessionRes.getSecurityError(),
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���O�A�E�g�E�n���h���ւ̃f�B�X�p�b�`�ŃZ�L�����e�B�E�G���[������");
            }
            l_logoutRes = (WEB3LogoutResponse) l_sessionRes.response[0];
        }

        if (l_logoutRes.errorInfo != null)
        {
            log.debug(
                STR_METHOD_NAME
                    + " .... exception: "
                    + l_logoutRes.errorInfo.toString());
            throw new WEB3BusinessLayerException(
                l_logoutRes.errorInfo,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���O�A�E�g�E�n���h����Exception������");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �����ŗ^����ꂽ���N�G�X�g����ɋƖ��������s���A�������ʂ����X�|���X�ɐݒ�<BR>
     * ���Ă�Ԃ��B<BR>
     * @@param l_request ���N�G�X�g
     * @@return �������ʂ��ݒ肳�ꂽ���X�|���X
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4057ED630195
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        return null;
    }

    /**
     * (get���ʌ�t�Ǘ���)<BR>
     * ���ʂ���t�ς���t���ς��̏����擾���� <BR>
     * <BR>
     * �P�j���ʎ�ފǗ��e�[�u�������L�����Ō�������B <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�،���ЃR�[�h = ����.�ڋq.�،���ЃR�[�h <BR>
     * �@@�@@���X�R�[�h = ����.�ڋq.���X�R�[�h <BR>
     * <BR>
     * �Q�j���ʎ�ފǗ��e�[�u���̌��������[�v����B <BR>
     * <BR>
     * �@@�Q�|�P�j ���ʋ敪�Ǘ��e�[�u�����珑�ʋ敪���擾����B  <BR>
     * �@@�@@�@@�@@�@@[����] <BR>
     * �@@�@@�@@�@@�@@�،���ЃR�[�h = ����.�ڋq.�،���ЃR�[�h <BR>
     * �@@�@@�@@�@@�@@���X�R�[�h = ����.�ڋq.���X�R�[�h <BR>
     * �@@�@@�@@�@@�@@���ʃ`�F�b�N�敪 = �h�����@@�h <BR>
     * �@@�@@�@@�@@�@@���ʒʔ� = 0 <BR>
     * �@@�@@�@@�@@�@@���ʎ�ރR�[�h = �P�j�Ŏ擾�������ʎ�� <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�����R�[�h���擾�ł��Ȃ������ꍇ�́A���[�v�̎n�߂ɖ߂�B <BR>
     * <BR>
     *   �Q�|�Q�j �d�q�������R�[�h�Ǘ��e�[�u������d�q�������R�[�h���擾����B  <BR>
     * �@@�@@�@@�@@�@@[����] <BR>
     * �@@�@@�@@�@@�@@�،���ЃR�[�h = ����.�ڋq.�،���ЃR�[�h <BR>
     * �@@�@@�@@�@@�@@���X�R�[�h = ����.�ڋq.���X�R�[�h <BR>
     * �@@�@@�@@�@@�@@���ʋ敪 = �Q�|�P�j�Ŏ擾�������ʋ敪 <BR>
     * �@@�@@�@@�@@�@@�d�q�������R�[�h��1�`3������ = �P�j�Ŏ擾�������ʎ��<BR>
     * �@@�@@�@@�@@�@@�L���敪 = �u0�Fvalid�v <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�����R�[�h���擾�ł��Ȃ������ꍇ�́A���[�v�̎n�߂ɖ߂�B <BR>
     * �@@�@@�@@�@@�@@�����������R�[�h���擾�ł����ꍇ�́A�Ɩ��G���[�u�L���ȓd�q����<BR>
     * �@@�@@�@@�@@�@@���R�[�h���������݂��܂��B�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_03005<BR>
     * <BR>
     *   �Q�|�R�j�ȉ��̉��ꂩ�ɊY������ꍇ�A���ʌ�t�`�F�b�N���s���B  <BR>
     * �@@�@@�@@�@@�@@�Q�j�Ŏ擾�������ʎ�ފǗ����R�[�h�̌�t�Ώۂ��u0�F�S�ڋq�v  <BR>
     * �@@�@@�@@�@@�@@�Q�j�Ŏ擾�������ʎ�ފǗ����R�[�h�̌�t�Ώۂ�<BR>
     * �@@�@@�@@�@@�@@�u1�F�M�p�J�ݍόڋq�v������.�M�p����o�^�t���O��ture <BR>
     * �@@�@@�@@�@@�@@�Q�j�Ŏ擾�������ʎ�ފǗ����R�[�h�̌�t�Ώۂ�<BR>
     * �@@�@@�@@�@@�@@�u2�F�敨�E�I�v�V�����J�ݍόڋq�v������.�敨OP����o�^���u0�F�o�^�Ȃ��v�ȊO <BR>
     * <BR>
     *     �Q�|�R�|�P�j ����.�ڋq.is���ʌ�t���R�[������B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@[����] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���ʋ敪 = �Q�|�P�j�Ŏ擾�������ʋ敪 <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����R�[�h = �Q�|�Q�j�Ŏ擾�����d�q�������R�[�h <BR>
     * <BR>
     *     �Q�|�R�|�Q�j �i����.�����o�H�敪���h���b�`�h�������́hIVR�h�̏ꍇ�j���� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@is���ʌ�t���\�b�h�̖߂�l��false�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�u���ʖ���t�G���[�i���O�C�����j�B�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02939<BR>
     * <BR>
     *   �Q�|�S�j �v���p�e�B�Z�b�g <BR>
     * �@@�@@�@@�@@�@@���ʋ敪 �� �Q�|�P�j�Ŏ擾�������ʋ敪 <BR>
     * �@@�@@�@@�@@�@@���ʎ�� �� �P�j�Ŏ擾�������ʎ�� <BR>
     * �@@�@@�@@�@@�@@�d�q�������R�[�h �� �Q�|�Q�j�Ŏ擾�����d�q�������R�[�h <BR>
     * �@@�@@�@@�@@�@@��t�σt���O <BR>
     * �@@�@@�@@�@@�@@is���ʌ�t���\�b�h�̖߂�l��false�̏ꍇ�A�u0�F��t���ρv <BR>
     * �@@�@@�@@�@@�@@is���ʌ�t���\�b�h�̖߂�l��true�̏ꍇ�A �u1�F��t�ρv <BR>
     * �@@�@@�@@�@@�@@is���ʌ�t���\�b�h���R�[������Ȃ������ꍇ�A�u2�F��t�s�v�v <BR>
     * <BR>
     *   �Q�|�T�j ���ʌ�t�Ǘ������ꌏ�ǉ�����B <BR>
     * <BR>
     * �R�j�����������ʌ�t�Ǘ��������^�[������B<BR>
     * @@param l_mainAccountRow - (�ڋq)<BR>
     * �ڋq<BR>
     * @@param l_strOrderRootDiv - (�����o�H�敪)<BR>
     * �����o�H�敪<BR>
     * @@param l_blnMarginRegistFlag - (�M�p����o�^�t���O)<BR>
     * �M�p����o�^�t���O<BR>
     * true�F�@@�o�^�ς݁@@false�F�@@���o�^<BR>
     * @@param l_strFutOpTradeRegist - (�敨OP����o�^)<BR>
     * �敨OP����o�^<BR>
     * 0�F�@@�o�^�Ȃ�<BR>
     * 1�F�@@�o�^�ς݁iOP��������j<BR>
     * 2�F�@@�o�^�ς݁i�敨����j<BR>
     * 3�F�@@�o�^�ς݁i�敨�^OP����j<BR>
     * @@return WEB3DocumentDeliverInfoUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3DocumentDeliverInfoUnit[] getDocumentDeliverInfoUnit(
        MainAccountRow l_mainAccountRow,
        String l_strOrderRootDiv,
        boolean l_blnMarginRegistFlag,
        String l_strFutOpTradeRegist) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocumentDeliverInfoUnit(MainAccountRow, String, boolean, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j���ʎ�ފǗ��e�[�u�������L�����Ō�������B
        //[����]
        //�،���ЃR�[�h = ����.�ڋq.�،���ЃR�[�h
        //���X�R�[�h = ����.�ڋq.���X�R�[�h
        List l_lisDocCategoryManagements = null;

        String l_strQueryDocCategoryManagement = " institution_code = ? and branch_code = ? ";

        Object[] l_queryValueDocCategoryManagements = {
            l_mainAccountRow.getInstitutionCode(), l_mainAccountRow.getBranchCode()};

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisDocCategoryManagements = l_queryProcessor.doFindAllQuery(
                DocCategoryManagementRow.TYPE,
                l_strQueryDocCategoryManagement,
                l_queryValueDocCategoryManagements);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j���ʎ�ފǗ��e�[�u���̌��������[�v����B
        int l_intDocCategoryManagementsLength = l_lisDocCategoryManagements.size();
        //���ʌ�t�Ǘ����
        List l_lisDocumentDeliverInfoUnits = new ArrayList();
        for (int i = 0; i < l_intDocCategoryManagementsLength; i++)
        {
            //�Q�|�P�j ���ʋ敪�Ǘ��e�[�u�����珑�ʋ敪���擾����B
            //[����]
            //�،���ЃR�[�h = ����.�ڋq.�،���ЃR�[�h
            //���X�R�[�h = ����.�ڋq.���X�R�[�h
            //���ʃ`�F�b�N�敪 = �h�����@@�h
            //���ʒʔ� = 0
            //���ʎ�ރR�[�h = �P�j�Ŏ擾�������ʎ��
            //�����R�[�h���擾�ł��Ȃ������ꍇ�́A���[�v�̎n�߂ɖ߂�B
            StringBuffer l_sbWhereDocDivManagementRow = new StringBuffer();
            l_sbWhereDocDivManagementRow.append(" institution_code = ?");
            l_sbWhereDocDivManagementRow.append(" and branch_code = ? ");
            l_sbWhereDocDivManagementRow.append(" and document_check_div = ? ");
            l_sbWhereDocDivManagementRow.append(" and document_number = ? ");
            l_sbWhereDocDivManagementRow.append(" and document_category = ? ");

            Object[] l_valueDocDivManagementRows = new Object[5];
            DocCategoryManagementRow l_docCategoryManagementRow =
                (DocCategoryManagementRow)l_lisDocCategoryManagements.get(i);
            l_valueDocDivManagementRows[0] = l_mainAccountRow.getInstitutionCode();
            l_valueDocDivManagementRows[1] = l_mainAccountRow.getBranchCode();
            l_valueDocDivManagementRows[2] = WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW;
            l_valueDocDivManagementRows[3] = "0";
            l_valueDocDivManagementRows[4] = l_docCategoryManagementRow.getDocumentCategory();

            List l_lisDocDivManagementRows = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisDocDivManagementRows = l_queryProcessor.doFindAllQuery(
                    DocDivManagementRow.TYPE,
                    l_sbWhereDocDivManagementRow.toString(),
                    l_valueDocDivManagementRows);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�����R�[�h���擾�ł��Ȃ������ꍇ�́A���[�v�̎n�߂ɖ߂�B
            if (l_lisDocDivManagementRows.isEmpty())
            {
                continue;
            }

            //�Q�|�Q�j �d�q�������R�[�h�Ǘ��e�[�u������d�q�������R�[�h���擾����B
            //[����]
            //�،���ЃR�[�h = ����.�ڋq.�،���ЃR�[�h
            //���X�R�[�h = ����.�ڋq.���X�R�[�h
            //���ʋ敪 = �Q�|�P�j�Ŏ擾�������ʋ敪
            //�d�q�������R�[�h��1�`3������ = �P�j�Ŏ擾�������ʎ��
            //�L���敪 = �u0�Fvalid�v
            //�����R�[�h���擾�ł��Ȃ������ꍇ�́A���[�v�̎n�߂ɖ߂�B
            StringBuffer l_sbWhereBatoProductManagement = new StringBuffer();
            l_sbWhereBatoProductManagement.append(" institution_code = ? ");
            l_sbWhereBatoProductManagement.append(" and branch_code = ? ");
            l_sbWhereBatoProductManagement.append(" and document_div = ? ");
            l_sbWhereBatoProductManagement.append(" and substr(bato_product_code, 1, 3) = ? ");
            l_sbWhereBatoProductManagement.append(" and valid_flag = ? ");

            Object[] l_valueBatoProductManagements = new Object[5];
            DocDivManagementRow l_docDivManagementRow =
                (DocDivManagementRow)l_lisDocDivManagementRows.get(0);
            l_valueBatoProductManagements[0] = l_mainAccountRow.getInstitutionCode();
            l_valueBatoProductManagements[1] = l_mainAccountRow.getBranchCode();
            l_valueBatoProductManagements[2] = l_docDivManagementRow.getDocumentDiv();
            l_valueBatoProductManagements[3] = l_docCategoryManagementRow.getDocumentCategory();
            l_valueBatoProductManagements[4] = WEB3ValidFlagDef.VALID;

            List l_lisBatoProductManagements = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisBatoProductManagements = l_queryProcessor.doFindAllQuery(
                    BatoProductManagementRow.TYPE,
                    l_sbWhereBatoProductManagement.toString(),
                    l_valueBatoProductManagements);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�����R�[�h���擾�ł��Ȃ������ꍇ�́A���[�v�̎n�߂ɖ߂�B
            if (l_lisBatoProductManagements.isEmpty())
            {
                continue;
            }

            //�����������R�[�h���擾�ł����ꍇ�́A
            //�Ɩ��G���[�u�L���ȓd�q�������R�[�h���������݂��܂��B�v�̗�O���X���[����B
            if (l_lisBatoProductManagements.size() > 1)
            {
                log.debug("�L���ȓd�q�������R�[�h���������݂��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�L���ȓd�q�������R�[�h���������݂��܂��B");
            }

            BatoProductManagementRow l_batoProductManagementRow =
                (BatoProductManagementRow)l_lisBatoProductManagements.get(0);

            boolean l_blnIsDocumentDelivery = false;
            //is���ʌ�t���\�b�h���R�[������Ȃ�����
            boolean l_blnIsDocumentDeliveryCallFlag = false;
            //�Q�|�R�j�ȉ��̉��ꂩ�ɊY������ꍇ�A���ʌ�t�`�F�b�N���s���B
            //  �Q�j�Ŏ擾�������ʎ�ފǗ����R�[�h�̌�t�Ώۂ��u0�F�S�ڋq�v
            //  �Q�j�Ŏ擾�������ʎ�ފǗ����R�[�h�̌�t�Ώۂ��u1�F�M�p�J�ݍόڋq�v
            //    ������.�M�p����o�^�t���O��ture
            //  �Q�j�Ŏ擾�������ʎ�ފǗ����R�[�h�̌�t�Ώۂ��u2�F�敨�E�I�v�V�����J�ݍόڋq�v
            //    ������.�敨OP����o�^���u0�F�o�^�Ȃ��v�ȊO
            if (WEB3DeliveryTargetDef.ALL_ACCOUNT.equals(l_docCategoryManagementRow.getDeliveryTarget())
                || (WEB3DeliveryTargetDef.MARGIN_OPENED_ACCOUNT.equals(l_docCategoryManagementRow.getDeliveryTarget())
                    && l_blnMarginRegistFlag)
                || (WEB3DeliveryTargetDef.FUTURE_OPTION_OPENED_ACCOUNT.equals(
                    l_docCategoryManagementRow.getDeliveryTarget())
                    && !WEB3FutOpTradeRegistDef.NOT_REGIST.equals(l_strFutOpTradeRegist)))
            {
                try
                {
                    //get �ڋq�I�u�W�F�N�g
                    WEB3GentradeMainAccount l_genMainAccount =
                        new WEB3GentradeMainAccount(l_mainAccountRow.getAccountId());

                    //�Q�|�R�|�P�j ����.�ڋq.is���ʌ�t���R�[������B
                    //   [����]
                    //      ���ʋ敪 = �Q�|�P�j�Ŏ擾�������ʋ敪
                    //      �����R�[�h = �Q�|�Q�j�Ŏ擾�����d�q�������R�[�h
                    l_blnIsDocumentDelivery = l_genMainAccount.isDocumentDelivery(
                        l_docDivManagementRow.getDocumentDiv(),
                        l_batoProductManagementRow.getBatoProductCode());

                    l_blnIsDocumentDeliveryCallFlag = true;
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //�Q�|�R�|�Q�j �i����.�����o�H�敪���h���b�`�h�������́hIVR�h�̏ꍇ�j����
                // is���ʌ�t���\�b�h�̖߂�l��false�̏ꍇ�A�u���ʖ���t�G���[�i���O�C�����j�B�v�̗�O���X���[����B
                if ((WEB3OrderRootDivDef.RICH_CLIENT.equals(l_strOrderRootDiv)
                    || WEB3OrderRootDivDef.IVR.equals(l_strOrderRootDiv))
                    && !l_blnIsDocumentDelivery)
                {
                    log.debug("���ʂ̌�t������ĂȂ����߁A���O�C���ł��܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02939,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���ʂ̌�t������ĂȂ����߁A���O�C���ł��܂���B");
                }
            }

            //�Q�|�S�j �v���p�e�B�Z�b�g
            WEB3DocumentDeliverInfoUnit l_documentDeliverInfoUnit = new WEB3DocumentDeliverInfoUnit();
            //���ʋ敪 �� �Q�|�P�j�Ŏ擾�������ʋ敪
            l_documentDeliverInfoUnit.documentDiv = l_docDivManagementRow.getDocumentDiv();
            //���ʎ�� �� �P�j�Ŏ擾�������ʎ��
            l_documentDeliverInfoUnit.documentCategory = l_docCategoryManagementRow.getDocumentCategory();
            //�d�q�������R�[�h �� �Q�|�Q�j�Ŏ擾�����d�q�������R�[�h
            l_documentDeliverInfoUnit.batoProductCode = l_batoProductManagementRow.getBatoProductCode();
            //��t�σt���O
            if (l_blnIsDocumentDeliveryCallFlag)
            {
                if (l_blnIsDocumentDelivery)
                {
                    //  is���ʌ�t���\�b�h�̖߂�l��true�̏ꍇ�A �u1�F��t�ρv
                    l_documentDeliverInfoUnit.deliverFlag = WEB3DeliverFlagDivDef.DELIVERY;
                }
                else
                {
                    //  is���ʌ�t���\�b�h�̖߂�l��false�̏ꍇ�A�u0�F��t���ρv
                    l_documentDeliverInfoUnit.deliverFlag = WEB3DeliverFlagDivDef.UNDELIVERY;
                }
            }
            else
            {
                //  is���ʌ�t���\�b�h���R�[������Ȃ������ꍇ�A�u2�F��t�s�v�v
                l_documentDeliverInfoUnit.deliverFlag = WEB3DeliverFlagDivDef.NO_DELIVERY;
            }

            //�Q�|�T�j ���ʌ�t�Ǘ������ꌏ�ǉ�����B
            l_lisDocumentDeliverInfoUnits.add(l_documentDeliverInfoUnit);
        }
        //�R�j�����������ʌ�t�Ǘ��������^�[������B
        WEB3DocumentDeliverInfoUnit[] l_documentDeliverInfoUnits =
            new WEB3DocumentDeliverInfoUnit[l_lisDocumentDeliverInfoUnits.size()];
        l_lisDocumentDeliverInfoUnits.toArray(l_documentDeliverInfoUnits);

        log.exiting(STR_METHOD_NAME);
        return l_documentDeliverInfoUnits;
    }

    /**
     * (get�����J�݋敪)<BR>
     * �����J�݋敪�����擾����B<BR>
     * <BR>
     * �P�j�@@�����J�݋敪���擾<BR>
     * �@@�P�|�P�j�@@�����J�݋敪�e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@����ID = ����.����ID<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR> 
     * <BR>
     * �Q�j�@@ArrayList�𐶐�����B<BR> 
     * <BR>
     * �R�j�@@�P�j�̖߂�l�ɂ��āA�ȉ��̏������J��Ԃ��B<BR> 
     * <BR>
     * �@@�R�|�P�j�@@�����J�݋敪�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�v���p�e�B�Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@�����J�݋敪.������� �� �P�j�Ŏ擾���������J�݋敪�s. �������<BR>
     * �@@�@@�@@�@@�@@�@@�����J�݋敪.�����J�݋敪 �� �P�j�Ŏ擾���������J�݋敪�s. �����J�݋敪<BR>
     * <BR>
     * �@@�R�|�R�j�@@�������������J�݋敪��ArrayList�ɒǉ�����B<BR> 
     * <BR>
     * �S�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B<BR> 
     * @@param l_lngAccountID - (����ID)<BR>
     * ����ID<BR>
     * @@return WEB3AccOpenDivUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3AccOpenDivUnit[] getAccOpenDivUnit(
        long l_lngAccountID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAccOpenDivUnit(long)";
        log.entering(STR_METHOD_NAME);

        WEB3AccOpenDivUnit[] l_accOpenDivUnits = null;

        //�����J�݋敪�e�[�u�����ȉ��̏����Ō�������B
        //[����]:����ID = ����.����ID
        String l_strQueryString = "account_id = ?";
        Object[] l_objWhereValues = new Object[]{new Long(l_lngAccountID)};
        List l_lisAccOpeDivRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAccOpeDivRecords =
                l_queryProcessor.doFindAllQuery(
                    AccOpenDivRow.TYPE,
                    l_strQueryString.toString(),
                    l_objWhereValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if (l_lisAccOpeDivRecords.size() != 0)
        {
            //ArrayList�𐶐�����B
            List l_lisAccOpenDivUnitList = new ArrayList();
            
            //�P�j�̖߂�l�ɂ��āA�ȉ��̏������J��Ԃ��B
            Iterator l_AccOpeDivIterator = l_lisAccOpeDivRecords.iterator();
            WEB3AccOpenDivUnit l_accOpenDivUnit = null;
            AccOpenDivRow l_accOpDivRow = null;
            while (l_AccOpeDivIterator.hasNext())
            {
                 l_accOpDivRow = (AccOpenDivRow)l_AccOpeDivIterator.next();

                //�����J�݋敪�I�u�W�F�N�g�𐶐�����B
                l_accOpenDivUnit = new WEB3AccOpenDivUnit();

                //�v���p�e�B�Z�b�g
                //�����J�݋敪.������� �� �P�j�Ŏ擾���������J�݋敪�s. �������
                //�����J�݋敪.�����J�݋敪 �� �P�j�Ŏ擾���������J�݋敪�s. �����J�݋敪
                l_accOpenDivUnit.accType = l_accOpDivRow.getAccType();
                l_accOpenDivUnit.accOpenDiv = l_accOpDivRow.getAccOpenDiv();

                //�������������J�݋敪��ArrayList�ɒǉ�����B
            	l_lisAccOpenDivUnitList.add(l_accOpenDivUnit);
            }

            //��������ArrayList.toArray()�̖߂�l��ԋp����B
            l_accOpenDivUnits = new WEB3AccOpenDivUnit[l_lisAccOpenDivUnitList.size()];
            l_lisAccOpenDivUnitList.toArray(l_accOpenDivUnits);
        }

        //�������������J�݋敪��ArrayList�ɒǉ�����B
        //�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        return l_accOpenDivUnits;
    }
}@


1.1
log
@*** empty log message ***
@
text
@d2 1
a2 1
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
d4 1
a4 1
Author Name         : Daiwa Institute of Research
d22 1
d71 2
d1027 9
d1544 50
@

