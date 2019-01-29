head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�XImpl(WEB3AdminAccInfoMailAddressChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
                 : 2006/05/19 ���� (���u) �d�l�ύX�E���f��104
                 : 2006/05/22 ���� (���u) �d�l�ύX�E���f��105
                 : 2006/05/23 ���� (���u) �d�l�ύX�E���f��106�A107
                 : 2006/06/12 �����iSCS�j�@@�d�l�ύX�E���f��109
Revesion History : 2007/08/28 �Ӑ��i���u�j�c�a�X�V�d�l�E���f��049
Revesion History : 2007/08/28 ���g (���u) �d�l�ύX�Ǘ�No.218
Revesion History : 2007/08/30 ���g (���u) �d�l�ύX�Ǘ�No.221
Revesion History : 2010/02/22 ���g (���u) �d�l�ύX�Ǘ����f��No.260,No.265,No.269,No.270,No.273,No.274 �c�a�X�V�d�lNo.061,No062
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressInfoUnit;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressUpdateInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMultiMailAddressInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountinfoMultiMailaddressFlagDef;
import webbroker3.common.define.WEB3AddressDivDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3DuplicationMailAddressCheckDef;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3NameSerialNoDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMailAddressDuplicationCheck;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMobileMailAddressCheck;
import webbroker3.gentrade.data.AccountMailAddressPK;
import webbroker3.gentrade.data.AccountMailAddressParams;
import webbroker3.gentrade.data.AccountMailAddressRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�XImpl)<BR>
 * �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X�����N���X<BR>
 * 
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeServiceImpl 
    extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoMailAddressChangeService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressChangeServiceImpl.class);
    /**
     * (���X�R�[�h�L�[��)<BR>
     * ���X�R�[�h�L�[��<BR>
     */
    private static String KEY_BRANCH_CODE = "branch_code";

    /**
     * (�ڋq�R�[�h�L�[��)<BR>
     * �ڋq�R�[�h�L�[��<BR>
     */
    private static String KEY_ACCOUNT_CODE = "account_code";
    /**
     * @@roseuid 418F3A02037A
     */
    public WEB3AdminAccInfoMailAddressChangeServiceImpl() 
    {
     
    }
    
    /**
     * ���[���A�h���X�ύX���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l��񃁁[���A�h���X�ύX<BR>
     * ���̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l��񃁁[���A�h���X�ύX<BR>
     * �m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�ύX()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l��񃁁[���A�h���X�ύX<BR>
     * �������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�ύX()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F9B503E3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminAccInfoMailAddressChangeInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminAccInfoMailAddressChangeInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoMailAddressChangeConfirmRequest)
        {           
            l_response = this.validateChange((WEB3AdminAccInfoMailAddressChangeConfirmRequest)l_request) ;
        }
        else if (l_request instanceof WEB3AdminAccInfoMailAddressChangeCompleteRequest)
        {           
            l_response = this.submitChange((WEB3AdminAccInfoMailAddressChangeCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME
                );            
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���[���A�h���X�ύX���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��҂��q�l���i���[���A�h���X�ύX�jget���͉�ʁv�Q�ƁB<BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�ύX���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeInputResponse
     * @@roseuid 416662610376
     */
    protected WEB3AdminAccInfoMailAddressChangeInputResponse getInputScreen(WEB3AdminAccInfoMailAddressChangeInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminAccInfoMailAddressChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);
        
        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.5 get�ڋq(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        l_accountManager.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        
        //1.6 validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        //get�ڋq���[���A�h���X
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        //�����R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
        List l_lisAccountMailAddresss =
            this.getAccountMailAddress(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);

        List l_lisAccInfoMailAddressUpdateInfo = new ArrayList();
        //get�ڋq���[���A�h���X()�߂�l�̗v�f����LOOP����
        Iterator l_itAccountMailAddresss = l_lisAccountMailAddresss.iterator();
        while (l_itAccountMailAddresss.hasNext())
        {
            AccountMailAddressRow l_accountMailAddressRow =
                (AccountMailAddressRow)l_itAccountMailAddresss.next();
            //create���[���A�h���X�ύX���
            //�ڋq���[���A�h���X�s �F get�ڋq���[���A�h���X()�ɂĎ擾�����ڋq���[���A�h���XParams
            WEB3AccInfoMailAddressUpdateInfo l_accInfoMailAddressUpdateInfo =
                this.createMailAddressUpdateInfo(l_accountMailAddressRow);
            l_lisAccInfoMailAddressUpdateInfo.add(l_accInfoMailAddressUpdateInfo);
        }
        //1.7 createResponse()
        WEB3AdminAccInfoMailAddressChangeInputResponse l_response = 
            (WEB3AdminAccInfoMailAddressChangeInputResponse) l_request.createResponse();
        if (l_lisAccountMailAddresss.isEmpty())
        {
            l_response.mailAddressUpdateInfo = null;
        }
        else
        {
            WEB3AccInfoMailAddressUpdateInfo[] l_accInfoMailAddressUpdateInfo =
                new WEB3AccInfoMailAddressUpdateInfo[l_lisAccInfoMailAddressUpdateInfo.size()];
            l_lisAccInfoMailAddressUpdateInfo.toArray(l_accInfoMailAddressUpdateInfo);
            l_response.mailAddressUpdateInfo = l_accInfoMailAddressUpdateInfo;
        }
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (validate�ύX)<BR>
     * ���[���A�h���X�ύX�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��҂��q�l���i���[���A�h���X�ύX�jvalidate�ύX�v�Q�ƁB<BR>
     * <BR> 
     * ===============================================<BR>
     *          �V�[�P���X�} :  �Ǘ��҂��q�l���i���[���A�h���X�ύX�jvalidate�ύX<BR>
     *          ��̈ʒu     :  1.7  isMailAddress(String)<BR>
     *          ���[���A�h���X�Ƃ��ēK�؂łȂ��ꍇ�A��O���X���[����B<BR>
     *          �iisMailAddress() == false�j<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_00777<BR>
     * ================================================<BR> 
     * ================================================<BR>
     *          �V�[�P���X�} :  �Ǘ��҂��q�l���i���[���A�h���X�ύX�jvalidate�ύX<BR>
     *          ��̈ʒu     :  1.9.2 �d�����[���A�h���X�����݂����ꍇ�iget�d���A�h���X()�߂�l�̒���>0�j�A<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����ɂ���O���X���[����B<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_02444<BR> 
     * ================================================<BR>
     * <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F9B503E5
     */
    protected WEB3AdminAccInfoMailAddressChangeConfirmResponse 
        validateChange(WEB3AdminAccInfoMailAddressChangeConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AdminAccInfoMailAddressChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);
        
        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.5 get�ڋq(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_gentradMainAccount = l_accountManager.getMainAccount(
            l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        
        //1.6 validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.7 isMailAddress(String)
        if (l_request.changedMailAddress != null)
        {
            if (!WEB3StringTypeUtility.isMailAddress(l_request.changedMailAddress))
            {
                //1.8 ���[���A�h���X�Ƃ��ēK�؂łȂ��ꍇ�iisMailAddress() == false�j�A��O���X���[����B
                log.exiting(STR_METHOD_NAME);
                log.error("���[���A�h���X�Ƃ��ēK�؂łȂ��ꍇ�A��O���X���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                    getClass().getName() + " " + "validateChange");
            }
        }

        //validate�������[���A�h���X
        WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo =
            l_request.multiMailAddressInfo;
        if (l_accInfoMultiMailAddressInfo != null)
        {
            l_accInfoMultiMailAddressInfo.validateMultiMailAddressInfo();
        }

        //get�������[���A�h���X�Ή����{
        //���X�R�[�h�Fget�ڋq().getBranch().getBranchCode()�̖߂�l
        //�،���ЃR�[�h�Fget�ڋq().getInstitution().getInstitutionCode()�̖߂�l
        //�v���t�@@�����X���F "accountinfo.multi.mailaddress.flag"
        //�v���t�@@�����X���̘A�ԁF 1
        WEB3GentradeBranch l_gentradeBranch = (WEB3GentradeBranch)l_gentradMainAccount.getBranch();
        int l_intSerialNo = Integer.parseInt(WEB3NameSerialNoDef.SERIAL_NO);
        String l_strBranchPreferences = l_gentradeBranch.getMultiMailAddressEnforcement(
            l_gentradeBranch.getBranchCode(),
            l_gentradMainAccount.getInstitution().getInstitutionCode(),
            WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG,
            l_intSerialNo);

        //�������[���A�h���X�Ή����{�i1.7 get�������[���A�h���X�Ή����{() == "2"�j�̏ꍇ
        if (WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strBranchPreferences))
        {
            //���N�G�X�g�f�[�^.���[���A�h���X���̗v�f����Loop�������s���B
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.mailAddressInfoList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.mailAddressInfoList;
            }
            int l_intLength = 0;
            if (l_accInfoMailAddressInfoUnits != null)
            {
                l_intLength = l_accInfoMailAddressInfoUnits.length;
            }

            for (int i = 0; i < l_intLength; i++)
            {
                l_accInfoMailAddressInfoUnits[i].validateMailAddressInfo();

                //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X�敪�@@== "1"
                if (WEB3AddressDivDef.PC_MAIL_ADDRESS.equals(
                    l_accInfoMailAddressInfoUnits[i].mailAddressDiv)
                    && l_accInfoMailAddressInfoUnits[i].mailAddress != null)
                {
                    //validatePC���[���A�h���X(���[���A�h���X : String)
                    //���[���A�h���X�F ���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X
                    WEB3GentradeMobileMailAddressCheck.validatePCMailAddress(
                        l_accInfoMailAddressInfoUnits[i].mailAddress);
                }
                //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X�敪�@@== "2"
                //�@@���@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X != null�j�̏ꍇ
                if (WEB3AddressDivDef.MOBILE_MAIL_ADDRESS.equals(
                    l_accInfoMailAddressInfoUnits[i].mailAddressDiv)
                    && l_accInfoMailAddressInfoUnits[i].mailAddress != null)
                {
                    //validate�g�у��[���A�h���X
                    //���[���A�h���X�F ���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X
                    try
                    {
                        WEB3GentradeMobileMailAddressCheck.validateMobileAddress(
                            l_accInfoMailAddressInfoUnits[i].mailAddress);

                        log.debug("���͂��ꂽ���[���A�h���X�͌g�у��[���A�h���X�ł͂���܂���B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03189,
                            WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                            "���͂��ꂽ���[���A�h���X�͌g�у��[���A�h���X�ł͂���܂���B");
                    }
                    catch(WEB3BaseException l_ex)
                    {
                        if (WEB3ErrorCatalog.BUSINESS_ERROR_03168 != l_ex.getErrorInfo())
                        {
                            log.error(l_ex.getErrorMessage(), l_ex);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                l_ex.getErrorInfo(),
                                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);
                        }
                    }
                }
            }
        }

        String[] l_strDuplicationAccounts = null;
        String[] l_strDuplicationAddressInfos = null;
        StringBuffer l_strDuplicationAccount = new StringBuffer();
        Object[] l_objDuplicationCheck = null;
        int l_intDuplicationCheckLenth = 0;
        
        //1.9 get�d���`�F�b�N���s�t���O(long, String, int)
        // �d�����[���`�F�b�N�̑Ώۂ��ǂ����𔻕ʂ���B 
        // �E���XID�Fget�ڋq().getBranch().getBranchId()�̖߂�l 
        // �E�v���t�@@�����X���FWEB3BranchPreferencesNameDef.PRE_NAME_DUPLO�i���X�v���t�@@�����X�e�[�u���d�l>�V�[�g
        //  �u�l�ݒ�d�l�v�Q�Ɓj 
        // �E�A�ԁF�v���t�@@�����X���̘A�� 
        int l_intDuplicateCheckExecFlag = 
            this.getDuplicateCheckExecFlag(
                l_gentradMainAccount.getBranch().getBranchId(), 
                WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO, 
                1);
        
        //����t���[�B
        //�d�����[���A�h���X�`�F�b�N�Ώہiget�d���`�F�b�N���s�t���O() != 0�j�ł���ꍇ�A�d���A�h���X�������s���B 
        if (l_intDuplicateCheckExecFlag != 0)
        {
            //���N�G�X�g�f�[�^.���[���A�h���X���̗v�f����Loop�������s���B
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.mailAddressInfoList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.mailAddressInfoList;
            }
            int l_intLength = 0;
            if (l_accInfoMailAddressInfoUnits != null)
            {
                l_intLength = l_accInfoMailAddressInfoUnits.length;
            }
            List l_lisDuplicationChecks = new ArrayList();
            for (int i = 0; i < l_intLength; i++)
            {
                //get�d���A�h���X(String, String, String, String)
                //�E���[���A�h���X�F ���N�G�X�g�f�[�^.���[���A�h���X���.���[���A�h���X
                //�E�ڋq�R�[�h�Fget�ڋq().getAccountCode()�̖߂�l
                //�E���X�R�[�h�Fget�ڋq().getBranch().getBranchCode()�̖߂�l
                //�E�،���ЃR�[�h�Fget�ڋq().getInstitution().getInstitutionCode()�̖߂�l
                if (l_accInfoMailAddressInfoUnits[i].mailAddress == null)
                {
                    continue;
                }
                l_objDuplicationCheck =
                    WEB3GentradeMailAddressDuplicationCheck.getDuplicateAddress(
                        l_accInfoMailAddressInfoUnits[i].mailAddress,
                        l_gentradMainAccount.getAccountCode(),
                        l_gentradMainAccount.getBranch().getBranchCode(),
                        l_gentradMainAccount.getInstitution().getInstitutionCode());

                if (l_objDuplicationCheck != null)
                {
                    l_intDuplicationCheckLenth = l_objDuplicationCheck.length;
                }
                for (int j = 0; j < l_intDuplicationCheckLenth; j++)
                {
                    if (!(l_lisDuplicationChecks.contains(l_objDuplicationCheck[j])))
                    {
                        l_lisDuplicationChecks.add(l_objDuplicationCheck[j]);
                    }
                }
            }
            int l_intSize = l_lisDuplicationChecks.size();
            for (int i = l_intSize - 1; i >= 0; i--)
            {
                for (int j = 0; j < i; j ++)
                {
                    HashMap l_hmDuplicationCheckFor = (HashMap)l_lisDuplicationChecks.get(j);
                    String l_strBranchCodeFor = (String)l_hmDuplicationCheckFor.get(KEY_BRANCH_CODE);
                    String l_strAccountCodeFor = (String)l_hmDuplicationCheckFor.get(KEY_ACCOUNT_CODE);
                    HashMap l_hmDuplicationCheckUp = (HashMap)l_lisDuplicationChecks.get(j + 1);
                    String l_strBranchCodeUp = (String)l_hmDuplicationCheckUp.get(KEY_BRANCH_CODE);
                    String l_strAccountCodeUp = (String)l_hmDuplicationCheckUp.get(KEY_ACCOUNT_CODE);
                    if (Long.parseLong(l_strBranchCodeFor) > Long.parseLong(l_strBranchCodeUp))
                    {
                        l_lisDuplicationChecks.set(j, l_hmDuplicationCheckUp);
                        l_lisDuplicationChecks.set(j + 1, l_hmDuplicationCheckFor);
                    }
                    else if (Long.parseLong(l_strBranchCodeFor) == Long.parseLong(l_strBranchCodeUp))
                    {
                        if (Long.parseLong(l_strAccountCodeFor) > Long.parseLong(l_strAccountCodeUp))
                        {
                            l_lisDuplicationChecks.set(j, l_hmDuplicationCheckUp);
                            l_lisDuplicationChecks.set(j + 1, l_hmDuplicationCheckFor);
                        }
                    }
                }
            }
            l_objDuplicationCheck = new Object[l_lisDuplicationChecks.size()];
            l_lisDuplicationChecks.toArray(l_objDuplicationCheck);
            l_intDuplicationCheckLenth = 0;
            if (l_objDuplicationCheck != null)
            {
                l_intDuplicationCheckLenth = l_objDuplicationCheck.length;
            }
            l_strDuplicationAccounts = new String[l_intDuplicationCheckLenth];
            l_strDuplicationAddressInfos = new String[l_intDuplicationCheckLenth];
            for (int i = 0; i < l_intDuplicationCheckLenth; i++)
            {
//				�R�[�f�B���O�~�X�̈וύX***2006.06.12 SCS Inomata-->
//                l_strDuplicationAccounts[i] = 
//                    "[" + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//                    + ":" + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]) + "]";
//                
//                l_strDuplicationAddressInfos[i] =
//                    WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//                    + "," + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]);
//                
				l_strDuplicationAccounts[i] = 
					"\n[���X " + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
					+ "�F�ڋq�R�[�h " + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]) + "]";
				l_strDuplicationAddressInfos[i] =
					WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
					+ "," + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]);
//				<--�R�[�f�B���O�~�X�̈וύX***2006.06.12 SCS Inomata
                l_strDuplicationAccount.append(l_strDuplicationAccounts[i]);
            }
            //1.9.2 �d�����[���A�h���X�����݂����ꍇ�iget�d���A�h���X()�߂�l�̒���>0�j�A�����ɂ���O���X���[����B
            //����t���[�B
            //�d�����[���A�h���X�����݂����ꍇ�iget�d���A�h���X()�߂�l����>0�j�A�ȉ��������ɗ�O���X���[����B
            //�u�d�����[���A�h���X�`�F�b�N�Ώہv(get�d���`�F�b�N���s�t���O() == 2)&& 
            //�u�d�����[���A�h���X�����݂���v�iget�d���A�h���X()�߂�l�̒���>0�j
            if (l_intDuplicateCheckExecFlag == 2 && l_intDuplicationCheckLenth > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02444, 
                    getClass().getName() + " " + "validateChange", 
                    l_strDuplicationAccount.toString());
            }
        }
        //1.10 createResponse()
        WEB3AdminAccInfoMailAddressChangeConfirmResponse l_response = 
            (WEB3AdminAccInfoMailAddressChangeConfirmResponse) l_request.createResponse();
        
        if (l_intDuplicationCheckLenth > 0 && l_intDuplicateCheckExecFlag == 1)
        {
            l_response.duplicationAddressInfo = l_strDuplicationAddressInfos ;
        }
        
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (submit�ύX)<BR>
     * ���[���A�h���X�ύX�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��҂��q�l���i���[���A�h���X�ύX�jsubmit�ύX�v�Q�ƁB<BR>
     * <BR>
     *  ===============================================<BR>
     *          �V�[�P���X�} :  �Ǘ��҂��q�l���i���[���A�h���X�ύX�jsubmit�ύX<BR>
     *          ��̈ʒu     :  1.8  isMailAddress(String)<BR>
     *          ���[���A�h���X�Ƃ��ēK�؂łȂ��ꍇ�A��O���X���[����B<BR>
     *          �iisMailAddress() == false�j<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_00777<BR> 
     * ================================================<BR>
     * ================================================<BR>
     *          �V�[�P���X�} :  �Ǘ��҂��q�l���i���[���A�h���X�ύX�jsubmit�ύX<BR>
     *          ��̈ʒu     :  1.11.2�d�����[���A�h���X�����݂����ꍇ�iget�d���A�h���X()�߂�l����>0�j�A<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����ɂ���O���X���[����B<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_02444<BR> 
     * ================================================<BR>
     * <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F9B503E7
     */
    protected WEB3AdminAccInfoMailAddressChangeCompleteResponse submitChange(
        WEB3AdminAccInfoMailAddressChangeCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AdminAccInfoMailAddressChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);
        
        //1.4 validate����p�X���[�h(String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //1.5 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.6 get�ڋq(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_gentradeMainAccount = 
            l_accountManager.getMainAccount(
                l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
            
        //1.7 validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.8 isMailAddress(String)
        if (l_request.changedMailAddress != null)
        {
            if (!WEB3StringTypeUtility.isMailAddress(l_request.changedMailAddress))
            {
                //1.9 (*1) ���[���A�h���X�Ƃ��ēK�؂łȂ��ꍇ�iisMailAddress() == false�j�A��O���X���[����B
                log.exiting(STR_METHOD_NAME);
                log.debug("���[���A�h���X�Ƃ��ēK�؂łȂ��ꍇ�A��O���X���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, getClass().getName() + " " + "submitChange");
            }            
        }      

        //validate�������[���A�h���X
        WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo =
            l_request.multiMailAddressInfo;
        if (l_accInfoMultiMailAddressInfo != null)
        {
            l_accInfoMultiMailAddressInfo.validateMultiMailAddressInfo();
        }

        //get�������[���A�h���X�Ή����{
        //���X�R�[�h�Fget�ڋq().getBranch().getBranchCode()�̖߂�l
        //�،���ЃR�[�h�Fget�ڋq().getInstitution().getInstitutionCode()�̖߂�l
        //�v���t�@@�����X���F "accountinfo.multi.mailaddress.flag"
        //�v���t�@@�����X���̘A�ԁF 1
        WEB3GentradeBranch l_gentradeBranch = (WEB3GentradeBranch)l_gentradeMainAccount.getBranch();
        int l_intSerialNo = Integer.parseInt(WEB3NameSerialNoDef.SERIAL_NO);
        String l_strBranchPreferences = l_gentradeBranch.getMultiMailAddressEnforcement(
            l_gentradeBranch.getBranchCode(),
            l_gentradeMainAccount.getInstitution().getInstitutionCode(),
            WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG,
            l_intSerialNo);

        //�������[���A�h���X�Ή����{�i1.7 get�������[���A�h���X�Ή����{() == "2"�j�̏ꍇ
        if (WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strBranchPreferences))
        {
            //���N�G�X�g�f�[�^.���[���A�h���X���̗v�f����Loop�������s���B
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.mailAddressInfoList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.mailAddressInfoList;
            }
            int l_intLength = 0;
            if (l_accInfoMailAddressInfoUnits != null)
            {
                l_intLength = l_accInfoMailAddressInfoUnits.length;
            }

            for (int i = 0; i < l_intLength; i++)
            {
                l_accInfoMailAddressInfoUnits[i].validateMailAddressInfo();

                //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X�敪�@@== "1"
                if (WEB3AddressDivDef.PC_MAIL_ADDRESS.equals(
                    l_accInfoMailAddressInfoUnits[i].mailAddressDiv)
                    && l_accInfoMailAddressInfoUnits[i].mailAddress != null)
                {
                    //validatePC���[���A�h���X(���[���A�h���X : String)
                    //���[���A�h���X�F ���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X
                    WEB3GentradeMobileMailAddressCheck.validatePCMailAddress(
                        l_accInfoMailAddressInfoUnits[i].mailAddress);
                }
                //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X�敪�@@== "2"
                //�@@���@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X != null�j�̏ꍇ
                if (WEB3AddressDivDef.MOBILE_MAIL_ADDRESS.equals(
                    l_accInfoMailAddressInfoUnits[i].mailAddressDiv)
                    && l_accInfoMailAddressInfoUnits[i].mailAddress != null)
                {
                    //validate�g�у��[���A�h���X
                    //���[���A�h���X�F ���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X
                    try
                    {
                        WEB3GentradeMobileMailAddressCheck.validateMobileAddress(
                            l_accInfoMailAddressInfoUnits[i].mailAddress);

                        log.debug("���͂��ꂽ���[���A�h���X�͌g�у��[���A�h���X�ł͂���܂���B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03189,
                            WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                            "���͂��ꂽ���[���A�h���X�͌g�у��[���A�h���X�ł͂���܂���B");
                    }
                    catch(WEB3BaseException l_ex)
                    {
                        if (WEB3ErrorCatalog.BUSINESS_ERROR_03168 != l_ex.getErrorInfo())
                        {
                            log.error(l_ex.getErrorMessage(), l_ex);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                l_ex.getErrorInfo(),
                                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);
                        }
                    }
                }
            }
        }

        String[] l_strDuplicationAccounts = null;
        String[] l_strDuplicationAddressInfos = null;
        StringBuffer l_strDuplicationAccount = new StringBuffer();
        Object[] l_objDuplicationCheck = null;
        int l_intDuplicationCheckLenth = 0;
        
        //1.10 get�d���`�F�b�N���s�t���O(long, String, int)
        // �d�����[���`�F�b�N�̑Ώۂ��ǂ����𔻕ʂ���B 
        // �E���XID�Fget�ڋq().getBranch().getBranchId()�̖߂�l 
        // �E�v���t�@@�����X���FWEB3BranchPreferencesNameDef.PRE_NAME_DUPLO�i���X�v���t�@@�����X�e�[�u���d�l>�V�[�g
        //  �u�l�ݒ�d�l�v�Q�Ɓj 
        // �E�A�ԁF�v���t�@@�����X���̘A�� 
        int l_intDuplicateCheckExecFlag = 
            this.getDuplicateCheckExecFlag(
                l_gentradeMainAccount.getBranch().getBranchId(), 
                WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO, 
                1);
        
        //����t���[�B
        //�d�����[���A�h���X�`�F�b�N�Ώہiget�d���`�F�b�N���s�t���O() != 0�j�ł���ꍇ�A�d���A�h���X�������s���B 
        if (l_intDuplicateCheckExecFlag != 0)
        {
            //���N�G�X�g�f�[�^.���[���A�h���X���̗v�f����Loop�������s���B
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.mailAddressInfoList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.mailAddressInfoList;
            }
            int l_intLength = 0;
            if (l_accInfoMailAddressInfoUnits != null)
            {
                l_intLength = l_accInfoMailAddressInfoUnits.length;
            }
            List l_lisDuplicationChecks = new ArrayList();
            for (int i = 0; i < l_intLength; i++)
            {
                //get�d���A�h���X(String, String, String, String)
                //�E���[���A�h���X�F ���N�G�X�g�f�[�^.���[���A�h���X���.���[���A�h���X
                //�E�ڋq�R�[�h�Fget�ڋq().getAccountCode()�̖߂�l
                //�E���X�R�[�h�Fget�ڋq().getBranch().getBranchCode()�̖߂�l
                //�E�،���ЃR�[�h�Fget�ڋq().getInstitution().getInstitutionCode()�̖߂�l
                if (l_accInfoMailAddressInfoUnits[i].mailAddress == null)
                {
                    continue;
                }
                l_objDuplicationCheck =
                    WEB3GentradeMailAddressDuplicationCheck.getDuplicateAddress(
                        l_accInfoMailAddressInfoUnits[i].mailAddress,
                        l_gentradeMainAccount.getAccountCode(),
                        l_gentradeMainAccount.getBranch().getBranchCode(),
                        l_gentradeMainAccount.getInstitution().getInstitutionCode());
                if (l_objDuplicationCheck != null)
                {
                    l_intDuplicationCheckLenth = l_objDuplicationCheck.length;
                }
                for (int j = 0; j < l_intDuplicationCheckLenth; j++)
                {
                    if (!(l_lisDuplicationChecks.contains(l_objDuplicationCheck[j])))
                    {
                        l_lisDuplicationChecks.add(l_objDuplicationCheck[j]);
                    }
                }
            }
            int l_intSize = l_lisDuplicationChecks.size();
            for (int i = l_intSize - 1; i >= 0; i--)
            {
                for (int j = 0; j < i; j ++)
                {
                    HashMap l_hmDuplicationCheckFor = (HashMap)l_lisDuplicationChecks.get(j);
                    String l_strBranchCodeFor = (String)l_hmDuplicationCheckFor.get(KEY_BRANCH_CODE);
                    String l_strAccountCodeFor = (String)l_hmDuplicationCheckFor.get(KEY_ACCOUNT_CODE);
                    HashMap l_hmDuplicationCheckUp = (HashMap)l_lisDuplicationChecks.get(j + 1);
                    String l_strBranchCodeUp = (String)l_hmDuplicationCheckUp.get(KEY_BRANCH_CODE);
                    String l_strAccountCodeUp = (String)l_hmDuplicationCheckUp.get(KEY_ACCOUNT_CODE);
                    if (Long.parseLong(l_strBranchCodeFor) > Long.parseLong(l_strBranchCodeUp))
                    {
                        l_lisDuplicationChecks.set(j, l_hmDuplicationCheckUp);
                        l_lisDuplicationChecks.set(j + 1, l_hmDuplicationCheckFor);
                    }
                    else if (Long.parseLong(l_strBranchCodeFor) == Long.parseLong(l_strBranchCodeUp))
                    {
                        if (Long.parseLong(l_strAccountCodeFor) > Long.parseLong(l_strAccountCodeUp))
                        {
                            l_lisDuplicationChecks.set(j, l_hmDuplicationCheckUp);
                            l_lisDuplicationChecks.set(j + 1, l_hmDuplicationCheckFor);
                        }
                    }
                }
            }
            l_objDuplicationCheck = new Object[l_lisDuplicationChecks.size()];
            l_lisDuplicationChecks.toArray(l_objDuplicationCheck);
            l_intDuplicationCheckLenth = 0;
            if (l_objDuplicationCheck != null)
            {
                l_intDuplicationCheckLenth = l_objDuplicationCheck.length;
            }
            
            l_strDuplicationAccounts = new String[l_intDuplicationCheckLenth];
            l_strDuplicationAddressInfos = new String[l_intDuplicationCheckLenth];
            
            for (int i = 0; i < l_intDuplicationCheckLenth; i++)
            {
//				�R�[�f�B���O�~�X�̈וύX***2006.06.12 SCS Inomata-->
//                l_strDuplicationAccounts[i] = 
//                    "[" + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//                    + ":" + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]) + "]";
//                
//                l_strDuplicationAddressInfos[i] =
//                    WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//                    + "," + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]);
//                
				l_strDuplicationAccounts[i] = 
					"\n[���X " + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
					+ "�F�ڋq�R�[�h " + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]) + "]";
				l_strDuplicationAddressInfos[i] =
					WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
					+ "," + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]);
//				<--�R�[�f�B���O�~�X�̈וύX***2006.06.12 SCS Inomata
                l_strDuplicationAccount.append(l_strDuplicationAccounts[i]);
            }
            
            //1.10.2 �d�����[���A�h���X�����݂����ꍇ�iget�d���A�h���X()�߂�l�̒���>0�j�A�����ɂ���O���X���[����B
            //����t���[�B
            //�d�����[���A�h���X�����݂����ꍇ�iget�d���A�h���X()�߂�l����>0�j�A�ȉ��������ɗ�O���X���[����B
            //�u�d�����[���A�h���X�`�F�b�N�Ώہv(get�d���`�F�b�N���s�t���O() == 2)&& 
            //�u�d�����[���A�h���X�����݂���v�iget�d���A�h���X()�߂�l�̒���>0�j
            
            if (l_intDuplicateCheckExecFlag == 2 && l_intDuplicationCheckLenth > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02444, 
                    getClass().getName() + " " + "submitChange", 
                    l_strDuplicationAccount.toString());
            }
        }
               
        if (l_intDuplicateCheckExecFlag == 0 
            || (l_intDuplicateCheckExecFlag != 0 && l_intDuplicationCheckLenth == 0) 
            || (l_intDuplicateCheckExecFlag == 1 && l_intDuplicationCheckLenth > 0))
        {
            //1.11 getDataSourceObject( )
            MainAccountParams l_mainAccountParams = 
                new MainAccountParams((MainAccountRow)l_gentradeMainAccount.getDataSourceObject());
            
            //1.12 doUpdateQuery(arg0 : PrimaryKey, arg1(*2) : Map)
            l_mainAccountParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            
            if (l_request.mailAddressDelFlag)
            {
                l_mainAccountParams.setEmailAddress(null);

                //email�A�h���X�X�V�҃R�[�h
                //�Ǘ��҃e�[�u��.�Ǘ��҃R�[�h
                l_mainAccountParams.setEmailLastUpdater(l_administrator.getAdministratorCode());
                //email�A�h���X�X�V����
                //���������@@��TradingSystem.getSystemTimestamp()
                l_mainAccountParams.setEmailLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            }
            else if (l_request.changedMailAddress != null)
            {
                if (!l_request.changedMailAddress.equals(l_mainAccountParams.getEmailAddress()))
                {
                    //email�A�h���X�X�V�҃R�[�h
                    //�Ǘ��҃e�[�u��.�Ǘ��҃R�[�h
                    l_mainAccountParams.setEmailLastUpdater(l_administrator.getAdministratorCode());
                    //email�A�h���X�X�V����
                    //���������@@��TradingSystem.getSystemTimestamp()
                    l_mainAccountParams.setEmailLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
                }

                l_mainAccountParams.setEmailAddress(l_request.changedMailAddress);
            }
            if (l_request.accountOpenMailFlag)
            {
                l_mainAccountParams.setAccOpenSendMailStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);
            }
            else
            {
                l_mainAccountParams.setAccOpenSendMailStatus(WEB3EmailStatusDef.EMAIL_SEND_END);
            }
            
            //�ē����[�����M�t���O
            //get�������[���A�h���X�Ή����{�̖߂�l != 2 ���� �ē����[�����M�t���O�̕ύX�����������ꍇ
            boolean l_blnInformationMailFlag = true;
            //�ڋq�}�X�^.�ē����[�����M�t���O��false�̏ꍇ�Afalse���Z�b�g
            if (BooleanEnum.FALSE.equals(l_mainAccountParams.getInformationMailFlag()))
            {
                l_blnInformationMailFlag = false;
            }

            //�����N�G�X�g.�ē����[�����M�t���O != �ڋq�}�X�^.�ē����[�����M�t���O
            if ((!WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strBranchPreferences))
                && l_request.guideMailFlag != l_blnInformationMailFlag)
            {
                //�ē����[�����M�t���O���A�����l�Ƌt�̒l�ōX�V�iDB Update�j�X�V����
                if (!l_blnInformationMailFlag)
                {
                    l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
                }
                else
                {
                    l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);
                }

                //�ē����[�����M�t���O�X�V�҃R�[�h = �Ǘ��҃e�[�u��.�Ǘ��҃R�[�h
                l_mainAccountParams.setInfMailFlgLastUpdater(l_administrator.getAdministratorCode());

                //�ē����[�����M�t���O�X�V���� = ���������@@��TradingSystem.getSystemTimestamp() 
                l_mainAccountParams.setInfMailFlgUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            }

            //�i���N�G�X�g�f�[�^.�����A�h���X���.���[���A�h���X�Q�폜�t���O == true�j�̏ꍇ�Anull�B
            if (l_request.multiMailAddressInfo.mailAddressDelFlag2)
            {
                l_mainAccountParams.setEmailAddressAlt1(null);
            }
            else
            {
                //�i���N�G�X�g�f�[�^.�����A�h���X���.���[���A�h���X�Q�폜�t���O == false�j�̏ꍇ�A
                //���N�G�X�g�f�[�^.�����A�h���X���.���[���A�h���X�Q�B
                l_mainAccountParams.setEmailAddressAlt1(l_request.multiMailAddressInfo.mailAddress2);
            }

            //�i���N�G�X�g�f�[�^.�����A�h���X���.���[���A�h���X�R�폜�t���O == true�j�̏ꍇ�Anull�B
            if (l_request.multiMailAddressInfo.mailAddressDelFlag3)
            {
                l_mainAccountParams.setEmailAddressAlt2(null);
            }
            else
            {
                //�i���N�G�X�g�f�[�^.�����A�h���X���.���[���A�h���X�R�폜�t���O == false)�̏ꍇ�A
                //���N�G�X�g�f�[�^.�����A�h���X���.���[���A�h���X�R�B
                l_mainAccountParams.setEmailAddressAlt2(l_request.multiMailAddressInfo.mailAddress3);
            }

            try 
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_mainAccountParams);
            } 
            catch (DataFindException l_ex) 
            { 
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }  
            catch (DataQueryException l_ex) 
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            catch (DataNetworkException l_ex) 
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
        if (l_request.mailAddressInfoList != null)
        {
            l_accInfoMailAddressInfoUnits =
                l_request.mailAddressInfoList;
        }
        int l_intLength = 0;
        if (l_accInfoMailAddressInfoUnits != null)
        {
            l_intLength = l_accInfoMailAddressInfoUnits.length;
        }

        for (int i = 0; i < l_intLength; i++)
        {
            if (l_accInfoMailAddressInfoUnits[i] != null)
            {
                this.updateAccountMailAddress(
                    l_gentradeMainAccount, l_accInfoMailAddressInfoUnits[i]);
            }
        }

        //get�ڋq���[���A�h���X
        List l_lisAccountMailAddresss = this.getAccountMailAddress(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        List l_lisAccInfoMailAddressUpdateInfo = new ArrayList();
        //get�ڋq���[���A�h���X()�߂�l�̗v�f����LOOP����
        Iterator l_itAccountMailAddresss = l_lisAccountMailAddresss.iterator();
        while (l_itAccountMailAddresss.hasNext())
        {
            AccountMailAddressRow l_accountMailAddressRow =
                (AccountMailAddressRow)l_itAccountMailAddresss.next();
            //create���[���A�h���X�ύX���
            //�ڋq���[���A�h���X�s �F get�ڋq���[���A�h���X()�ɂĎ擾�����ڋq���[���A�h���XParams
            WEB3AccInfoMailAddressUpdateInfo l_accInfoMailAddressUpdateInfo =
                this.createMailAddressUpdateInfo(l_accountMailAddressRow);
            l_lisAccInfoMailAddressUpdateInfo.add(l_accInfoMailAddressUpdateInfo);
        }
        
        //1.13 createResponse()
        WEB3AdminAccInfoMailAddressChangeCompleteResponse l_response = 
            (WEB3AdminAccInfoMailAddressChangeCompleteResponse) l_request.createResponse();
        
        if(l_intDuplicationCheckLenth > 0 && l_intDuplicateCheckExecFlag  == 1)
        {
            l_response.duplicationAddressInfo = l_strDuplicationAddressInfos;
        }
        WEB3AccInfoMailAddressUpdateInfo[] l_accInfoMailAddressUpdateInfo =
            new WEB3AccInfoMailAddressUpdateInfo[l_lisAccInfoMailAddressUpdateInfo.size()];
        l_lisAccInfoMailAddressUpdateInfo.toArray(l_accInfoMailAddressUpdateInfo);
        l_response.mailAddressUpdateInfo = l_accInfoMailAddressUpdateInfo;
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (get�d���`�F�b�N���s�t���O)<BR>
     * ���X�v���t�@@�����X���Q�Ƃ��A�d�����[���`�F�b�N�̑Ώۂ��ǂ����𔻕ʂ���B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR> 
     * �@@[����] <BR>
     * �@@���XID = �����F���XID <BR>
     * �@@�v���t�@@�����X�� = �����F�v���t�@@�����X�� <BR>
     * �@@�v���t�@@�����X���̘A�� = �����F�v���t�@@�����X���̘A�� <BR>
     * <BR>             
     * �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l��ԋp����B <BR>
     * <BR>             
     * �@@���R�[�h.�v���t�@@�����X�̒l�F "0" OR ""�@@�� 0 <BR>
     * �@@���R�[�h.�v���t�@@�����X�̒l�F "1" OR "3" �� 1 <BR>
     * �@@���R�[�h.�v���t�@@�����X�̒l�F "2" OR "4" �� 2 <BR>
     * �@@�����R�[�h���擾�ł��Ȃ������ꍇ �� 0<BR>
     * @@param l_lngBranchId - (���XID)<BR>
     * ���XID�B<BR>
     * @@param l_strName - (�v���t�@@�����X��)<BR>
     * �v���t�@@�����X���B<BR>
     * @@param l_strNameSerialNo - (�v���t�@@�����X���̘A��)<BR>
     * �v���t�@@�����X���̘A�ԁB<BR>
     * @@return int
     * @@throws WEB3BaseException
     */
    protected int getDuplicateCheckExecFlag(
        long l_lngBranchId, String l_strName, int l_strNameSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDuplicateCheckExecFlag(long, String, int)";
        log.entering(STR_METHOD_NAME);
        
        BranchPreferencesRow l_branchPreferencesRow = null;
        
        try
        {
            l_branchPreferencesRow = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId, 
                    l_strName, 
                    l_strNameSerialNo);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }  
        
        //�Q�j�擾�������R�[�h.�v���t�@@�����X�̒l��ԋp����B
        //�����R�[�h���擾�ł��Ȃ������ꍇ �� 0
        if (l_branchPreferencesRow == null) 
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
            
        }
        //���R�[�h.�v���t�@@�����X�̒l�F "0" OR "" �� 0
        else if ("".equals(l_branchPreferencesRow.getValue()) 
            || WEB3DuplicationMailAddressCheckDef.DEFAULT.equals(l_branchPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        
        //���R�[�h.�v���t�@@�����X�̒l�F "1" OR "3" �� 1
        else if (WEB3DuplicationMailAddressCheckDef.NO_EXCEPTION.equals(l_branchPreferencesRow.getValue()) 
            || WEB3DuplicationMailAddressCheckDef.CREATE_EXCEPTION_CUST.equals(l_branchPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return 1;
        }
        
        //���R�[�h.�v���t�@@�����X�̒l�F "2" OR "4" �� 2
        else if (WEB3DuplicationMailAddressCheckDef.CREATE_EXCEPTION.equals(l_branchPreferencesRow.getValue()) 
            || WEB3DuplicationMailAddressCheckDef.CREATE_EXCEPTION_ADMIN.equals(l_branchPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return 2;
        }
        
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (get�ڋq���[���A�h���X)<BR>
     * �ڋq���o�^���Ă��郁�[���A�h���X�����ׂĎ擾����B<BR>
     * <BR>
     * �ڋq���[���A�h���X�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�ڋq���[���A�h���X.�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�ڋq���[���A�h���X.���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@�ڋq���[���A�h���X.�����R�[�h = ����.�����R�[�h<BR>
     * <BR>
     * �Y���s�ɂČڋq���[���A�h���X�I�u�W�F�N�g�𐶐����A�z��ɂĕԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    protected List getAccountMailAddress(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAccountMailAddress("
            + "String, String, String)";
        log.entering(STR_METHOD_NAME);

        String l_strQuery =
            " institution_code = ? "
            + " and branch_code = ? "
            + " and SubStr(account_code,0,6) = ? ";

        Object[] l_objQuery =
            new Object[]{
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode};
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisAccountMailAddressRows = l_queryProcessor.doFindAllQuery(
                AccountMailAddressRow.TYPE,
                l_strQuery,
                l_objQuery);

            log.exiting(STR_METHOD_NAME);
            return l_lisAccountMailAddressRows;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (create���[���A�h���X�ύX���)<BR>
     * ���[���A�h���X�ύX��񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j�@@���[���A�h���X�ύX���I�u�W�F�N�g�̐���<BR>
     * <BR>
     * �Q�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�E�@@���[���A�h���X�ύX���.���[���A�h���X�X�V�҃R�[�h�@@ ��<BR>
     * �@@�@@�@@�@@(����)�ڋq���[���A�h���X�s.���[���A�h���X�X�V�҃R�[�h<BR>
     * �@@�@@�E�@@���[���A�h���X�ύX���.���[���A�h���X�X�V�����@@ ��<BR>
     * �@@�@@�@@�@@(����)�ڋq���[���A�h���X�s.���[���A�h���X�X�V����<BR>
     * �@@�@@�E�@@���[���A�h���X���.���[���A�h���X�@@���@@(����)�ڋq���[���A�h���X�s.���[���A�h���X <BR>
     * �@@�@@�E�@@���[���A�h���X���.���[���A�h���X�ԍ��@@���@@(����)�ڋq���[���A�h���X�s.���[���A�h���X�ԍ�<BR>
     * �@@�@@�E�@@���[���A�h���X���.���[���A�h���X�敪�@@���@@(����)�ڋq���[���A�h���X�s.���[���A�h���X�敪<BR>
     * <BR>
     * �R�j�@@�����������[���A�h���X�ύX���I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_accountMailAddressRow - (�ڋq���[���A�h���X�s)<BR>
     * �ڋq���[���A�h���X�s<BR>
     * @@return WEB3AccInfoMailAddressUpdateInfo
     */
    protected WEB3AccInfoMailAddressUpdateInfo createMailAddressUpdateInfo(
        AccountMailAddressRow l_accountMailAddressRow)
    {
        final String STR_METHOD_NAME = "createMailAddressUpdateInfo(AccountMailAddressRow)";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoMailAddressUpdateInfo l_mailAddressUpdateInfo =
            new WEB3AccInfoMailAddressUpdateInfo();

        l_mailAddressUpdateInfo.mailAddressUpdaterCode =
            l_accountMailAddressRow.getEmailLastUpdater();
        if (l_accountMailAddressRow.getEmailLastUpdatedTimestamp() != null)
        {
            l_mailAddressUpdateInfo.mailAddressUpdateDate =
                WEB3DateUtility.formatDate(
                    l_accountMailAddressRow.getEmailLastUpdatedTimestamp(),
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
        }
        l_mailAddressUpdateInfo.mailAddress = l_accountMailAddressRow.getEmailAddress();
        l_mailAddressUpdateInfo.mailAddressNo = l_accountMailAddressRow.getEmailAddressNumber() + "";
        l_mailAddressUpdateInfo.mailAddressDiv = l_accountMailAddressRow.getAddressDiv();
        return l_mailAddressUpdateInfo;
    }

    /**
     * (update�ڋq���[���A�h���X)<BR>
     *�P�j���N�G�X�g�f�[�^.���[���A�h���X���.���[���A�h���X == null �̏ꍇ�A�ȉ����������{����B<BR>
     *�ȉ��̏����Łu�ڋq���[���A�h���X�e�[�u���v���猟������B<BR>
     *�@@�@@[��������]<BR>
     *�@@�@@�،���ЃR�[�h�F�ڋq.�،���ЃR�[�h<BR>
     *�@@�@@���X�R�[�h�F�ڋq.���X�R�[�h<BR>
     *�@@�@@�����R�[�h�F�ڋq.�����R�[�h<BR>
     *�@@�@@���[���A�h���X�ԍ��F���N�G�X�g�f�[�^.���[���A�h���X���.���[���A�h���X�ԍ�<BR>
     *<BR>
     *�@@�������ʂ��擾�ł���ꍇ�A<BR>
     *�@@�c�a�X�V���s���B<BR>
     *�@@�u�Ǘ��ҁE���[���A�h���X�ύX_�ڋq���[���A�h���X�e�[�u��.xls#�ڋq���[���A�h���X�e�[�u��_delete�v�Q�ƁB<BR>
     *<BR>
     *�Q�j �P�j�ȊO�̏ꍇ�A�ȉ��̏����Łu�ڋq���[���A�h���X�e�[�u���v���猟������B<BR>
     *<BR>
     *�@@�@@[��������]<BR>
     *�@@�@@�،���ЃR�[�h�F�ڋq.�،���ЃR�[�h<BR>
     *�@@�@@���X�R�[�h�F�ڋq.���X�R�[�h<BR>
     *�@@�@@�����R�[�h�F�ڋq.�����R�[�h<BR>
     *�@@�@@���[���A�h���X�ԍ��F���N�G�X�g�f�[�^.���[���A�h���X���.���[���A�h���X�ԍ�<BR>
     *<BR>
     *�@@�Q�|�P�j �������ʂ��擾�ł���ꍇ�A<BR>
     *�@@�c�a�X�V���s���B<BR>
     *�@@�u�Ǘ��ҁE���[���A�h���X�ύX_�ڋq���[���A�h���X�e�[�u��.xls#�ڋq���[���A�h���X�e�[�u��_update�v�Q�ƁB<BR>
     *<BR>
     *�@@�Q�|�Q�j �������ʂ��擾�ł��Ȃ��ꍇ�A<BR>
     *�@@�c�a�X�V���s���B<BR>
     *�@@�u�Ǘ��ҁE���[���A�h���X�ύX_�ڋq���[���A�h���X�e�[�u��.xls#�ڋq���[���A�h���X�e�[�u��_insert�v�Q�ƁB<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@param l_mailAddressInfo - (���[���A�h���X���)<BR>
     * ���[���A�h���X���<BR>
     * @@throws WEB3BaseException
     */
    protected void updateAccountMailAddress(
        WEB3GentradeMainAccount l_mainAccount,
        WEB3AccInfoMailAddressInfoUnit l_mailAddressInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateAccountMailAddress("
            + "WEB3GentradeMainAccount, WEB3AccInfoMailAddressInfoUnit)";
        log.entering(STR_METHOD_NAME);

        //�ȉ��̏����Łu�ڋq���[���A�h���X�e�[�u���v���猟������B
        //�،���ЃR�[�h�F�ڋq.�،���ЃR�[�h
        //���X�R�[�h�F�ڋq.���X�R�[�h
        //�����R�[�h�F�ڋq.�����R�[�h
        //���[���A�h���X�ԍ��F���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X�ԍ�
        //�ڋq.getInstitution().getInstitutionCode()
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        //�ڋq.getBranch().getBranchCode()
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //�ڋq.getAccountCode()
        String l_strAccountCode = l_mainAccount.getAccountCode();
        //�P�j�@@�ڋq���[���A�h���X�e�[�u�����ȉ��̏����Ō�������B
        String l_strQuery =
            " institution_code = ? "
            + " and branch_code = ? "
            + " and account_code = ? "
            + " and email_address_number = ? ";

        Object[] l_objQuery =
            new Object[]{
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_mailAddressInfo.mailAddressNo};
        
        List l_lisAccountMailAddressRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAccountMailAddressRows = l_queryProcessor.doFindAllQuery(
                AccountMailAddressRow.TYPE,
                l_strQuery,
                l_objQuery);

            //�P�j���N�G�X�g�f�[�^.���[���A�h���X���.���[���A�h���X == null �̏ꍇ�A�ȉ����������{����B
            if (l_mailAddressInfo.mailAddress == null)
            {
                if (l_lisAccountMailAddressRows.size() == 1)
                {
                    //�������ʂ��擾�ł���ꍇ�A
                    //�u�Ǘ��ҁE���[���A�h���X�ύX_�ڋq���[���A�h���X�e�[�u��.xls#�ڋq���[���A�h���X�e�[�u��_delete�v�Q�ƁB
                    AccountMailAddressPK l_accountMailAddressPK =
                        new AccountMailAddressPK(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode,
                            Long.parseLong(l_mailAddressInfo.mailAddressNo));
    
                    l_queryProcessor.doDeleteQuery(l_accountMailAddressPK);
                }
            }
            //�Q�j �P�j�ȊO�̏ꍇ�A�ȉ��̏����Łu�ڋq���[���A�h���X�e�[�u���v���猟������B
            else
            {
                if (l_lisAccountMailAddressRows.size() == 1)
                {
                    AccountMailAddressRow l_accountMailAddressRow =
                        (AccountMailAddressRow)l_lisAccountMailAddressRows.get(0);
                    //�������ʂ��擾�ł���ꍇ�A
                    //�u�Ǘ��ҁE���[���A�h���X�ύX_�ڋq���[���A�h���X�e�[�u��.xls#�ڋq���[���A�h���X�e�[�u��_update�v�Q�ƁB
                    Map l_map = new HashMap();
                    //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.�A�h���X�敪
                    l_map.put("address_div", l_mailAddressInfo.mailAddressDiv);
                    //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X
                    l_map.put("email_address", l_mailAddressInfo.mailAddress);
                    //�ڋq���[���A�h���X�s.���[���A�h���X !=
                    // �u���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X�v�̏ꍇ�A�����R�[�h�B
                    if (!WEB3Toolkit.isEquals(
                        l_mailAddressInfo.mailAddress,
                        l_accountMailAddressRow.getEmailAddress()))
                    {
                        l_map.put("email_last_updater",
                            WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode());
                        l_map.put("email_last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());
                    }
                    //TradingSystem.getSystemTimestamp() �B
                    l_map.put("last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());
                    log.exiting(STR_METHOD_NAME);
                    l_queryProcessor.doUpdateAllQuery(
                        AccountMailAddressRow.TYPE,
                        l_strQuery,
                        l_objQuery,
                        l_map);
                }
                else
                {
                    //�Q�|�Q�j �������ʂ��擾�ł��Ȃ��ꍇ�A
                    //�u�Ǘ��ҁE���[���A�h���X�ύX_�ڋq���[���A�h���X�e�[�u��.xls#�ڋq���[���A�h���X�e�[�u��_insert�v�Q�ƁB
                    AccountMailAddressParams l_accountMailAddressParams =
                        new AccountMailAddressParams();
                    //�ڋq.�،���ЃR�[�h
                    l_accountMailAddressParams.setInstitutionCode(l_strInstitutionCode);
                    //�ڋq.���X�R�[�h
                    l_accountMailAddressParams.setBranchCode(l_strBranchCode);
                    //�ڋq.�����R�[�h
                    l_accountMailAddressParams.setAccountCode(l_strAccountCode);
                    //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X�ԍ�
                    l_accountMailAddressParams.setEmailAddressNumber(
                        Long.parseLong(l_mailAddressInfo.mailAddressNo));
                    //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.�A�h���X�敪
                    l_accountMailAddressParams.setAddressDiv(l_mailAddressInfo.mailAddressDiv);
                    //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X
                    l_accountMailAddressParams.setEmailAddress(l_mailAddressInfo.mailAddress);
                    //�Ǘ��҃e�[�u��.�Ǘ��҃R�[�h
                    l_accountMailAddressParams.setEmailLastUpdater(
                        WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode());
                    //TradingSystem.getSystemTimestamp() �B
                    l_accountMailAddressParams.setEmailLastUpdatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_accountMailAddressParams.setCreatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_accountMailAddressParams.setLastUpdatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_queryProcessor.doInsertQuery(l_accountMailAddressParams);
                }
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
