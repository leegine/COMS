head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3HashAuthenticationAcceptLoginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �n�b�V���F�؃��O�C���T�[�r�X����(WEB3HashAuthenticationAcceptLoginServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/06/20 �h�C(���u) �V�K�쐬
                      2006/12/08 ��(FLJ) �Z�b�V�����ݒ���e�ǉ�
*/

package webbroker3.login.service.delegate.stdimpls;

import java.util.StringTokenizer;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.login.message.WEB3HashAuthenticationAcceptLoginRequest;
import webbroker3.login.message.WEB3HashAuthenticationAcceptLoginResponse;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.login.service.delegate.WEB3DigestKey;
import webbroker3.login.service.delegate.WEB3DigestService;
import webbroker3.login.service.delegate.WEB3HashAuthenticationAcceptLoginService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributePK;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (�n�b�V���F�؃��O�C���T�[�r�X����)<BR>
 * <BR>
 * @@author      �h�C(���u)
 * @@version     1.00
 */
public class WEB3HashAuthenticationAcceptLoginServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3HashAuthenticationAcceptLoginService
{

    /**
     * ���O�o��
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        	WEB3HashAuthenticationAcceptLoginServiceImpl.class);

    /**
     * @@roseuid 404835050203
     */
    public WEB3HashAuthenticationAcceptLoginServiceImpl()
    {

    }

    /**
     * ������������B<BR>
     * �n�b�V���F�؃��O�C���T�[�r�X�����s����<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�n�b�V���F�؃��O�C���jexecute�v �Q��<BR>
     * <BR>
     * ���X�����O�C����~��Ԃ��`�F�b�N����B<BR>
     * ���O�C����~���̏ꍇ�A���O�C����~���G���[��throw����B<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_90201<BR>
     * <BR>
     * �����`���l�����`�F�b�N����B<BR>
     * �s���Ȓl�̏ꍇ�A���̑��F�؃G���[��throw����B<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_90221<BR>
     * <BR>
     * �ڋq�R�[�h�̑Ó������`���b�N����B<BR>
     * �R�[�h�l�G���[�Ƃ��Ă��̑��F�؃G���[��throw����B<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_90222<BR>
     * <BR>
     * �ڋq���O�C���G���[��(�L����)���`�F�b�N����B<BR>
     * �����ȏꍇ�A�ڋq���b�N���G���[��throw����B<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_90202<BR>
     * <BR>
     * ���ꂽ�L�[��F�؃T�[�r�X�ɓn���A�L���ȃL�[�ł��邩�ǂ�����F�؁A<BR>
     * ���s�̏ꍇ�A���\()�b�h���Ăяo���Ƃ��납��G���[�𓊂���B<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_90223<BR>
     * <BR>
     * ���O�C���ixTrade�Z�b�V���������j����B<BR>
     * <BR>
     * �Z�b�V���������ݒ�n���h���Ƀf�B�X�p�b�`���ăZ�b�V����������ݒ肷��B<BR>
     * <BR>
     * ���X�|���X�쐬<BR>
     *    �ڋq�iDB:�ڋq�}�X�^�j�̃f�[�^���擾����B<BR>
     *    �ڋq���ڋq���ɕϊ�����B<BR>
     *    ��Ђ���Џ��ɕϊ�����B<BR>
     *    ���X�𕔓X���ɕϊ�����B<BR>
     *    �ڋq�}�X�^�̐擪���ID���擾����B<BR>
     *    �T�[�r�X���{��Ԏ擾���擾����B<BR>
     * <BR>
     * @@param l_request
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4044589502B5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3HashAuthenticationAcceptLoginRequest l_loginRequest =
        	(WEB3HashAuthenticationAcceptLoginRequest)l_request;
 
        //���X�R�[�h���擾
        String l_strBranchCode=l_loginRequest.branchCode;

        //xTrade���[�U�����擾
        String l_strUsername=l_loginRequest.xTradeUsername;

        //�ڋq�R�[�h���擾
        String l_strAcceptCode=l_loginRequest.acceptCode;

        try
        {
            //1 getInstitution(String)
            AccountManager l_accountManager = GtlUtils.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(l_loginRequest.institutionCode);
            
            //2 getBranch(Institution, String)
            InstitutionRow l_institutionRow = (InstitutionRow) l_institution.getDataSourceObject();
            BranchRow l_branchRow = (BranchRow) l_accountManager.getBranch(
            	l_institution, l_strBranchCode).getDataSourceObject();
            
            //3 is���O�C����~(BranchRow)
            //���X�����O�C����~��Ԃ��`�F�b�N����B
            //���O�C����~���̏ꍇ�A���O�C����~���G���[��throw����B
            if (this.isLoginStop(l_branchRow))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90201,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���X(CODE: " + l_strBranchCode + ") ���O�C����~��.");
            }
            
            //3.1 check�����`���l��(String)
            //�����`���l�����`�F�b�N����B
            //�s���Ȓl�̏ꍇ�A���̑��F�؃G���[��throw����B
            if (!this.checkOrderChannel(l_loginRequest.orderChannel))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90221,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(xNAME: " + l_strUsername + ") �����`���l���l�s��.");
            }
            
            //3.2 check�ڋq�R�[�h(String, BranchRow)
            //�ڋq�R�[�h�̑Ó������`���b�N����B
            //�R�[�h�l�G���[�Ƃ��Ă��̑��F�؃G���[��throw����B(�G���[�R�[�h�V�K)
            if (!this.checkAcceptCode(l_strAcceptCode, l_branchRow))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90222,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq(CODE: " + l_strAcceptCode + ") �R�[�h�l�s��.");
            }
            
            //3.3 getLoginInfo(long)
            OpLoginAdminService l_adminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            LoginInfo l_loginInfo = l_adminService.getLoginInfo(l_strUsername);
            
            //3.4 is���O�C�����[�U�L��(LoginInfo)
            //�ڋq���O�C���G���[��(�L����)���`�F�b�N����B
            //�����ȏꍇ�A�ڋq���b�N���G���[��throw����B
            if (!isEnabledUser(l_loginInfo))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90202,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���O�C�����[�U(INFO: " + l_loginInfo + ") ����.");
            }
            
            //3.4.1 << create >>
            //���O�C�����N�G�X�g�́u�F�ؗp�n�b�V���l�v�A�u�쐬�����v�A
            //�uGUID�v�O�̒l��p���āA�F�؃L�[�I�u�W�F�N�g���쐬�B
            WEB3DigestKey l_digestKey = new WEB3DigestKey();
            l_digestKey.setKey1(WEB3DateUtility.formatDate(l_loginRequest.createDate, "yyyyMMddHHmmss"));
            l_digestKey.setKey3(l_loginRequest.guid);
            l_digestKey.setKey4(l_loginRequest.hstr);
            
            //3.4.2  is�L���ȃL�[(WEB3DigestKey)
            //���ꂽ�L�[��F�؃T�[�r�X�ɓn���A�L���ȃL�[�ł��邩�ǂ�����F�؁A���s�̏ꍇ�A
            //���\()�b�h���Ăяo���Ƃ��납��G���[�𓊂���B(�V�K�G���[�R�[�h�FSHA1�F�؃G���[)
            //�F�؃��\�b�h�����̎����F
            //valueA = ���N�G�X�g�́u�F�ؗp�n�b�V���l�v�@@�i������̃n�b�V���l�j
            //valueB = �T�[�r�X�v�Z�����n�b�V���l�@@�i������́u�쐬�����v�A�uGUID�v�A
            //	�ƌŒ蕶����uHimawariWEBBROKER3�v���v�Z�j
            //valueA = valueB�̏ꍇ�F�ؐ��� (true)�AvalueA != valueB�̏ꍇ�F�؎��s (false)�B
            WEB3DigestService l_digestService = 
            	(WEB3DigestService)Services.getService(WEB3DigestService.class);
            if (!l_digestService.isValidKey(l_digestKey))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90223,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�L�[(KEY: " + l_digestKey + ") ����.");
            }

            //3.4.3 doFindByPrimaryKeyQuery(PrimaryKey)
            //LoginAttributePK�I�u�W�F�N�g��Login_ID��
            //WEB3LoginAttributeKeyDef.PASSWORD���w�肵�āA�p�X���[�h���擾
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            LoginAttributePK l_loginAttributePK = new LoginAttributePK(
            	l_loginInfo.getLoginId(),
            	WEB3LoginAttributeKeyDef.PASSWORD);
            LoginAttributeRow l_loginAttributeRow = 
            	(LoginAttributeRow) l_queryProcessor.doFindByPrimaryKeyQuery(l_loginAttributePK);

            //3.4.4 decrypt(String)
            //�V�KWEB3Crypt�I�u�W�F�N�g��p���đO�̃X�e�b�v�Ŏ擾�����p�X���[�h�𕜍���
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strDecryptPassword = l_crypt.decrypt(l_loginAttributeRow.getAttributeValue());

            //3.4.5 logIn(String, String)
            //���O�C�������s
            OpLoginSecurityService l_securityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            String l_strSessionID = l_securityService.logIn(l_strUsername, l_strDecryptPassword);

            //3.4.6 set�Z�b�V��������(String, WEB3SetSessionRequest)
            //�Z�b�V���������ݒ�n���h���Ƀf�B�X�p�b�`���ăZ�b�V����������ݒ肷��
            WEB3SetSessionRequest l_setSessionRequest = new WEB3SetSessionRequest();
            
            //�����@@�@@2006/12/08 ��(FLJ) �Z�b�V�����ݒ���e�ǉ�
            l_setSessionRequest.sessionAttributes.put(
                    WEB3SessionAttributeDef.ORDER_CHANNEL,
                    l_loginRequest.orderChannel);
            l_setSessionRequest.sessionAttributes.put(
                    WEB3SessionAttributeDef.ORDER_ROOT_DIV,
                    (l_loginRequest.orderRootDiv != null)
                        ? l_loginRequest.orderRootDiv
                        : WEB3OrderRootDivDef.PC);
            l_setSessionRequest.sessionAttributes.put(
                    WEB3SessionAttributeDef.INSTITUTION_ID,
                    Long.toString(l_institutionRow.getInstitutionId()));
            l_setSessionRequest.sessionAttributes.put(
                    WEB3SessionAttributeDef.BRANCH_ID,
                    Long.toString(l_branchRow.getBranchId()));
                StringTokenizer l_tokenizer = new StringTokenizer(l_strSessionID, ":");
                String l_strToken = l_tokenizer.nextToken();
                l_strToken = l_tokenizer.nextToken();
                l_strToken = l_tokenizer.nextToken();
                l_setSessionRequest.sessionAttributes.put(
                    WEB3SessionAttributeDef.SESSION_ID,
                    l_strToken);
                if (l_loginRequest.ipAddress != null)
                {
                    l_setSessionRequest.sessionAttributes.put(
                        WEB3SessionAttributeDef.IP_ADDRESS,
                        l_loginRequest.ipAddress);
                }            
            //�����@@�@@2006/12/08 ��(FLJ) �Z�b�V�����ݒ���e�ǉ�
                
            setSessionAttribute(l_strSessionID, l_setSessionRequest);

            //3.4.7 getMainAccount(long)
            //�����̃X�e�b�v�ɂ�背�X�|���X�쐬
            //�ڋq(DB:�ڋq�}�X�^)�̃f�[�^���擾����
            //�ڋq���ڋq���ɕϊ�����B
            //��Ђ���Џ��ɕϊ�����B
            //���X�𕔓X���ɕϊ�����B
            //�ڋq�}�X�^�̐擪���ID���擾����B
            //�T�[�r�X���{��Ԏ擾����B
            WEB3HashAuthenticationAcceptLoginResponse l_loginResponse = 
            	(WEB3HashAuthenticationAcceptLoginResponse) l_request.createResponse();
            MainAccount l_mainAccount = l_accountManager.getMainAccount(
            	LoginDao.findRowByPk(l_loginInfo.getLoginId()).getAccountId());

            //3.4.8 get�ڋq���(String, MainAccountRow, LoginInfo, String)
            MainAccountRow l_mainAccountRow = 
            	(MainAccountRow)l_mainAccount.getDataSourceObject();
            l_loginResponse.acceptInfo = this.getAcceptInfo(
            	l_strAcceptCode, 
            	l_mainAccountRow, 
            	l_loginInfo,
                l_loginRequest.orderRootDiv);
            
            //3.5 get��Џ��(InstitutionRow)
            l_loginResponse.institutionInfo = this.getInstitutionInfo(l_institutionRow);
            
            //3.6 get���X���(BranchRow)
            l_loginResponse.branchInfo = this.getBranchInfo(l_branchRow);
            
            //3.7 get�T�[�r�X���{���(InstitutionRow, BranchRow, MainAccountRow)
            l_loginResponse.serviceImpState = this.getServiceImpState(
            	l_institutionRow, 
            	l_branchRow, 
            	l_mainAccountRow);
            
            //�ڋq�}�X�^�̐擪���ID���擾����B
            l_loginResponse.topPageID = l_mainAccountRow.getTopPageId();
            
            //xTrade�Z�b�V����ID���擾����B
            l_loginResponse.xTradeSessionID = l_strSessionID;

            log.exiting(STR_METHOD_NAME);
            return l_loginResponse;
        }
        catch (NotFoundException l_nfexp)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_nfexp.getMessage(),
                l_nfexp);
        }
        catch (DataNetworkException l_dnwexp)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_dnwexp.getMessage(),
                l_dnwexp);
        }
        catch (DataQueryException l_dqexp)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_dqexp.getMessage(),
                l_dqexp);
        }
    }
}
@
