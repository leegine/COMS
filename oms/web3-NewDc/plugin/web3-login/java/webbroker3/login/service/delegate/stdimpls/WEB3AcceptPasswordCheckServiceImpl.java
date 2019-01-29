head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptPasswordCheckServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq�p�X���[�h�`�F�b�N�T�[�r�X(WEB3AcceptPasswordCheckServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/10/28 �V���@@�h�O(FLJ) �V�K�쐬
Revesion History    : 2006/03/24 ���k(FLJ)�@@IVR�F�؏����p�X���[�h�Ή�
*/
package webbroker3.login.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.PasswordUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.login.message.WEB3AcceptPasswordCheckRequest;
import webbroker3.login.message.WEB3AcceptPasswordCheckResponse;
import webbroker3.login.service.delegate.WEB3AcceptPasswordCheckService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;


/**
 * (�ڋq�p�X���[�h�`�F�b�N�T�[�r�X)<BR>
 * <BR>
 * @@author      Eizo Saito(FLJ)
 * @@version     1.00
 */
public class WEB3AcceptPasswordCheckServiceImpl
    extends WEB3LoginServiceBaseImpl
    implements WEB3AcceptPasswordCheckService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AcceptPasswordCheckServiceImpl.class);

    /**
     * �R���X�g���N�^
     */
    public WEB3AcceptPasswordCheckServiceImpl()
    {
        super();
    }

    /**
     * ������������B<BR>
     * <BR>
     * ���N�G�X�g����xTrade���[�U�����擾����B<BR>
     * �@@�@@��null�̏ꍇ�A�G���[�Ƃ���B<BR>
     * <BR>
     * ���N�G�X�g����ڋq�p�X���[�h���擾����B<BR>
     * �@@�@@��null�̏ꍇ�A�G���[�Ƃ���B<BR>
     * <BR>
     * login_username�e�[�u�����烌�R�[�h���擾����B<BR>
     * �@@��������<BR>
     * �@@�@@�Eusername �� xTrade���[�U��
     * �@@�@@�����R�[�h���擾�ł��Ȃ��ꍇ�A�G���[�Ƃ���B<BR>
     * <BR>
     * loginAttribute�e�[�u�����烌�R�[�h���擾����B<BR>
     * �@@��������<BR>
     * �@@�@@�ELOGIN_ID �� login_username.login_id
     * �@@�@@�EATTRIBUTE_NAME��WEB3LoginAttributeKeyDef.INITIAL_PASSWORD
     * �@@�@@�����R�[�h���擾�ł��Ȃ��ꍇ�A�G���[�Ƃ���B<BR>
     * <BR>
     * �ڋq�p�X���[�h��DB�̃p�X���[�h����v���邩���肷��B<BR>
     * �@@�ڋq�p�X���[�h���Í����������e��LOGIN_ATTRIBUTE.ATTRIBUTE_VALUE���r����B<BR>
     * <BR>
     * ���茋�ʂŃ��X�|���X���쐬���A�������I������B<BR>
     * <BR>
     * �����̉ߒ��ŗ�O�����������ꍇ�A���̗l�ɐU�����B<BR>
     * WEB3BaseException�����������ꍇ<BR>
     * �@@�@@catch������O�����̂܂�throw����B<BR>
     * DataException�����������ꍇ<BR>
     * �@@�@@DB�A�N�Z�X���s�Ƃ��ăV�X�e���G���[��throw����B<BR>
     * ����ȊO�̗�O�����������ꍇ<BR>
     * �@@�@@�L�蓾�Ȃ��ُ�Ƃ��Ēv���I�ȃV�X�e���G���[��throw����B<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AcceptPasswordCheckRequest l_passwordCheckReq = (WEB3AcceptPasswordCheckRequest) l_request;
        WEB3AcceptPasswordCheckResponse l_passwordCheckRes = null;
        try
        {
            //1)���N�G�X�g�����擾����B
            String l_strInstitutionCode = l_passwordCheckReq.institutionCode;
            String l_strBranchCode = l_passwordCheckReq.branchCode;
            String l_strAcceptCode = l_passwordCheckReq.acceptCode;
            String l_strAccountId = l_passwordCheckReq.account_id;
            //2)xTrade���[�U�����擾����B
            String l_strUserName = l_passwordCheckReq.xTradeUsername;
            //3)�ڋq�p�X���[�h���擾����B
            String l_strPassword = l_passwordCheckReq.acceptPassword;

            log.debug(
                STR_METHOD_NAME
                    + " .... institution = "
                    + l_strInstitutionCode
                    + ", branch = "
                    + l_strBranchCode
                    + ", accept = "
                    + l_strAcceptCode
                    + ", accountid(affinity) = "
                    + l_strAccountId
                    + ", xTradeUsername = "
                    + l_strUserName
                    + ", password = "
                    + l_strPassword);

            //xTrade���[�U���`�F�b�N
            if(l_strUserName == null || l_strUserName.length() == 0)
            {
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "WEB3AcceptPasswordCheckRequest.xTradeUsername = " + l_strUserName);
            }

            //�ڋq�p�X���[�h�`�F�b�N
            if(l_strPassword == null || l_strPassword.length() == 0)
            {
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "WEB3AcceptPasswordCheckRequest.acceptPassword = " + l_strPassword);
            }


            //4)login_username����P���R�[�h���擾����B
            LoginUsernameRow l_userNameRow = LoginUsernameDao.findRowByPk(l_strUserName);

            //5)loginAttribute����P���R�[�h���擾����B
            LoginAttributeRow l_loginAttributeRow = LoginAttributeDao.findRowByPk(
                l_userNameRow.getLoginId(), WEB3LoginAttributeKeyDef.INITIAL_PASSWORD);

            //6)�p�X���[�h���r����B
            String l_strHashedInitPasswordFromDb = l_loginAttributeRow.getAttributeValue();

            boolean l_isCertified = false;


            //�C�j�V�����p�X���[�h�Ɣ�r����
            String l_encryptedPwd = WEB3AcceptPasswordCheckUtil.getInstance().encrypt(
                l_strPassword);
            l_isCertified = l_encryptedPwd.equals(l_strHashedInitPasswordFromDb);

            //���X�|���X����
            l_passwordCheckRes = (WEB3AcceptPasswordCheckResponse)l_passwordCheckReq.createResponse();

            //7)���茋�ʂ����X�|���X�ɓ����
            l_passwordCheckRes.certifiedPasswordFlg = l_isCertified;

        }
        catch (WEB3BaseException ex)
        {
            throw ex;
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
        return l_passwordCheckRes;
    }

}
@
