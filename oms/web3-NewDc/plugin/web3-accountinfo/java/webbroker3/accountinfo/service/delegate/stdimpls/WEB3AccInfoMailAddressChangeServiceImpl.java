head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailAddressChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񃁁[���A�h���X�ύX�T�[�r�XImpl(WEB3AccInfoMailAddressChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
                 : 2006/05/19 ���� (���u) �d�l�ύX�E���f��104
                 : 2006/05/22 ���� (���u) �d�l�ύX�E���f��105
                 : 2006/05/23 ���� (���u) �d�l�ύX�E���f��106�A107
                 : 2006/06/12 �����iSCS�j�@@�d�l�ύX�E���f��109
Revesion History : 2007/08/28 �Ӑ��i���u�j�c�a�X�V�d�l�E���f��048
Revesion History : 2007/08/28 ���g (���u) �d�l�ύX�Ǘ�No.218
Revesion History : 2007/08/30 ���g (���u) �d�l�ύX�Ǘ�No.221
Revesion History : 2008/03/05 �Ԑi (���u) �c�a�X�V�d�l�E���f��050
Revesion History : 2009/02/12 SCS�哈 �d�l�ύX�Ǘ�No.253
Revesion History : 2010/02/22 ���g (���u) �d�l�ύX�Ǘ����f��No.259,No.265,No.266,No.268,No.269,No.270,No.272 �c�a�X�V�d�lNo.060,No062,No063
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressInfoUnit;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressTypeUnit;
import webbroker3.accountinfo.message.WEB3AccInfoMultiMailAddressInfo;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMailAddressChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenSendMailStatusDef;
import webbroker3.common.define.WEB3AccountinfoMultiMailaddressFlagDef;
import webbroker3.common.define.WEB3AddressDivDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3DuplicationMailAddressCheckDef;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3InformationMail2FlagDef;
import webbroker3.common.define.WEB3MailAssortmentDivDef;
import webbroker3.common.define.WEB3NameSerialNoDef;
import webbroker3.common.define.WEB3OrderExeFlagDef;
import webbroker3.common.define.WEB3OrderUnexeFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMailAddressDuplicationCheck;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMobileMailAddressCheck;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AccountMailAddressPK;
import webbroker3.gentrade.data.AccountMailAddressParams;
import webbroker3.gentrade.data.AccountMailAddressRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.MailAssortmentPK;
import webbroker3.gentrade.data.MailAssortmentParams;
import webbroker3.gentrade.data.MailAssortmentRow;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

/**
 * (���q�l��񃁁[���A�h���X�ύX�T�[�r�XImpl)<BR>
 * ���q�l��񃁁[���A�h���X�ύX�T�[�r�X�����N���X<BR>
 * 
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AccInfoMailAddressChangeServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AccInfoMailAddressChangeService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMailAddressChangeServiceImpl.class);
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
     * @@roseuid 418F39FE0261
     */
    public WEB3AccInfoMailAddressChangeServiceImpl() 
    {
     
    }
    
    /**
     * ���[���A�h���X�ύX���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���q�l��񃁁[���A�h���X�ύX�m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�ύX()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���q�l��񃁁[���A�h���X�ύX�������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�ύX()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413D3CCB0047
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AccInfoMailAddressChangeConfirmRequest)
        {
            l_response = this.validateChange((WEB3AccInfoMailAddressChangeConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AccInfoMailAddressChangeCompleteRequest)
        {           
            l_response = this.submitChange((WEB3AccInfoMailAddressChangeCompleteRequest)l_request);
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
     * (validate�ύX)<BR>
     * ���[���A�h���X�ύX�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u���q�l���i���[���A�h���X�ύX�jvalidate�ύX�v�Q�ƁB<BR>
     * <BR>
     *  =============================================== <BR>
     *          �V�[�P���X�} :  �i���[���A�h���X�ύX�jvalidate�ύX<BR>
     *          ��̈ʒu     :  1.4  isMailAddress(String)<BR>
     *          ���[���A�h���X�Ƃ��ēK�؂łȂ��ꍇ�A��O���X���[����B<BR>
     *          �iisMailAddress() == false<BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag              : BUSINESS_ERROR_00777 <BR>
     * ===============================================<BR>
     * ===============================================<BR>
     *          �V�[�P���X�} :  �i���[���A�h���X�ύX�jvalidate�ύX<BR>
     *          ��̈ʒu     :  1.6.3�d�����[���A�h���X�����݂����ꍇ�iget�d���A�h���X()�߂�l�̒���>0�j<BR>
     *                          ���O�C�����ڋq�̏ꍇ<BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag              : BUSINESS_ERROR_02443 <BR> 
     * ===============================================<BR>
     * ===============================================<BR>
     *          �V�[�P���X�} :  �i���[���A�h���X�ύX�jvalidate�ύX<BR>
     *          ��̈ʒu     :  1.6.3�d�����[���A�h���X�����݂����ꍇ�iget�d���A�h���X()�߂�l�̒���>0�j<BR>
     *                          ���O�C�����㗝���͎҂̏ꍇ <BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_02444<BR> 
     * ===============================================<BR>
     * @@param l_request - ���q�l��񃁁[���A�h���X�ύX�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413D3CCB0067
     */
    protected WEB3AccInfoMailAddressChangeConfirmResponse validateChange(WEB3AccInfoMailAddressChangeConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AccInfoMailAddressChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3 get�ڋq( )
        MainAccount l_mainAccount = this.getMainAccount();
        
        //1.5 isMailAddress(String)
        if (!l_request.mailAddressDelFlag)
        {
            if (!WEB3StringTypeUtility.isMailAddress(l_request.changedMailAddress))
            {
                log.debug("���[���A�h���X�Ƃ��ēK�؂łȂ��ꍇ�A��O���X���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, getClass().getName() + " " +"validateChange");
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
        int l_intSerialNo = Integer.parseInt(WEB3NameSerialNoDef.SERIAL_NO);
        WEB3GentradeBranch l_gentradeBranch = (WEB3GentradeBranch)l_mainAccount.getBranch();
        String l_strBranchPreferences = l_gentradeBranch.getMultiMailAddressEnforcement(
            l_gentradeBranch.getBranchCode(),
            l_mainAccount.getInstitution().getInstitutionCode(),
            WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG,
            l_intSerialNo);
        if (l_accInfoMultiMailAddressInfo != null)
        {
            //�������[���A�h���X�Ή���Ёiget���X�v���t�@@�����X() == "1"�j�̏ꍇ
            if (WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T0.equals(l_strBranchPreferences))
            {
                //  [validate�����A�h���X���M�t���O()�ɐݒ肷�����]
                //��{���[���A�h���X�F ���N�G�X�g�f�[�^.�ύX�チ�[���A�h���X
                l_accInfoMultiMailAddressInfo.validateMultiSendMailAddressInfoFlag(
                    l_request.changedMailAddress);
            }
        }
        //�������[���A�h���X�Ή����{�i1.7 get�������[���A�h���X�Ή����{() == "2"�j�̏ꍇ
        if (WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strBranchPreferences))
        {
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���̗v�f����Loop�������s���B
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.multiMailAddressList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.multiMailAddressList.mailAddressInfoList;
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
                    l_accInfoMailAddressInfoUnits[i].mailAddressDiv))
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
            WEB3AccInfoMailAddressTypeUnit[] l_accInfoMailAddressTypeUnits = null;
            if (l_request.multiMailAddressList != null)
            {
                l_accInfoMailAddressTypeUnits =
                    l_request.multiMailAddressList.mailTypeInfoList;
            }
            int l_intLengthMailAddressType = 0;
            if (l_accInfoMailAddressTypeUnits != null)
            {
                l_intLengthMailAddressType = l_accInfoMailAddressTypeUnits.length;
            }
            for (int j = 0; j < l_intLengthMailAddressType; j++)
            {
                l_accInfoMailAddressTypeUnits[j].validateMailAddressTypeInfo(
                    l_accInfoMailAddressInfoUnits);
            }
        }
        //1.6get�d���`�F�b�N���s�t���O(long, String, int)
        // �d�����[���`�F�b�N�̑Ώۂ��ǂ����𔻕ʂ���B 
        // �E���XID�Fget�ڋq().getBranch().getBranchId()�̖߂�l 
        // �E�v���t�@@�����X���FWEB3BranchPreferencesNameDef.PRE_NAME_DUPLO�i���X�v���t�@@�����X�e�[�u���d�l>�V�[�g
        //  �u�l�ݒ�d�l�v�Q�Ɓj 
        // �E�A�ԁF�v���t�@@�����X���̘A�� 
        int l_intDuplicateCheckExecFlag = 0;
        l_intDuplicateCheckExecFlag = 
            getDuplicateCheckExecFlag(
                l_mainAccount.getBranch().getBranchId(),
                WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO, 
                1);

        Trader l_trader = null;
        String[] l_strDuplicationAccounts = null;
        StringBuffer l_strDuplicationAccount = new StringBuffer();
        Object[] l_objDuplicationCheck = null;
        String[] l_strDuplicationAddressInfos = null;
        int l_intDuplicationCheckLenth = 0;

        //����t���[�B
        //�d�����[���A�h���X�`�F�b�N�Ώہiget�d���`�F�b�N���s�t���O() != 0�j�ł���ꍇ�A�d���A�h���X�������s���B 
        if (l_intDuplicateCheckExecFlag != 0)
        {
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���̗v�f����Loop�������s���B
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.multiMailAddressList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.multiMailAddressList.mailAddressInfoList;
            }
            int l_intLength = 0;
            if (l_accInfoMailAddressInfoUnits != null)
            {
                l_intLength = l_accInfoMailAddressInfoUnits.length;
            }
            List l_lisDuplicationChecks = new ArrayList();
            for (int i = 0; i < l_intLength; i++)
            {
                //1.6.1get�d���A�h���X(String, String, String, String)
                //�E���[���A�h���X�F ���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X
                //�E�،���ЃR�[�h�Fget�ڋq().getInstitution().getInstitutionCode()�̖߂�l
                //�E���X�R�[�h�Fget�ڋq().getBranch().getBranchCode()�̖߂�l
                //�E�ڋq�R�[�h�Fget�ڋq().getAccountCode()�̖߂�l
                if (l_accInfoMailAddressInfoUnits[i].mailAddress == null)
                {
                    continue;
                }
                l_objDuplicationCheck =
                    WEB3GentradeMailAddressDuplicationCheck.getDuplicateAddress(
                        l_accInfoMailAddressInfoUnits[i].mailAddress,
                        l_mainAccount.getAccountCode(),
                        l_mainAccount.getBranch().getBranchCode(),
                        l_mainAccount.getInstitution().getInstitutionCode());
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
            //1.6.2get�㗝���͎�( )
            l_trader = this.getTrader();
            
            //1.6.3
            //����t���[�B
            //�d�����[���A�h���X�����݂����ꍇ�iget�d���A�h���X()�߂�l����>0�j�A�ȉ��������ɗ�O���X���[����B 
            //(�u�d�����[���A�h���X�`�F�b�N�Ώہv�iget�d���`�F�b�N���s�t���O() != 0)&& 
            //�u�d�����[���A�h���X�����݂���v�iget�d���A�h���X()�߂�l�̒���>0�j&& 
            //�u���݂̃��O�C�����ڋq�iget�㗝���͎�()==null�j�v) 
            l_strDuplicationAccounts = new String[l_intDuplicationCheckLenth];
            l_strDuplicationAddressInfos = new String[l_intDuplicationCheckLenth];
            for (int i = 0; i < l_intDuplicationCheckLenth; i++)
            {
//				�R�[�f�B���O�~�X�̈וύX***2006.06.12 SCS Inomata-->
//                l_strDuplicationAccounts[i] = 
//                    "[" + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//                    + ":" + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]) + "]";
//                l_strDuplicationAddressInfos[i] =
//                    WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//                    + "," + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]);
				l_strDuplicationAccounts[i] = 
					"\n[���X " + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
					+ "�F�ڋq�R�[�h " + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]) + "]";
				l_strDuplicationAddressInfos[i] =
					WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
					+ "," + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]);
//				<--�R�[�f�B���O�~�X�̈וύX***2006.06.12 SCS Inomata
                l_strDuplicationAccount.append(l_strDuplicationAccounts[i]);
            }
            
            if (l_intDuplicateCheckExecFlag != 0 && l_intDuplicationCheckLenth > 0 && l_trader == null)
            {
                //l_errorMethod �G���[�������������\�b�h�� �FgetClass().getName() + "validateChange" 
                //l_errorMessage �G���[�̓��e �F"[���X�R�[�h:�ڋq�R�[�h]" 
                //�iget�d���A�h���X()�߂�l�v�f�B�������݂���ꍇ�́A"[���X�R�[�h:�ڋq�R�[�h][���X�R�[�h:�ڋq�R�[�h]"�j                
                log.exiting(STR_METHOD_NAME);
//				�d�l�ύX***2006.06.12 SCS Inomata-->
//                throw new WEB3BusinessLayerException(
//                    WEB3ErrorCatalog.BUSINESS_ERROR_02443, 
//                    getClass().getName() + " " + "validateChange",
//                    l_strDuplicationAccount.toString());

				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02443, 
					getClass().getName() + " " + "validateChange", "");
//				<--�d�l�ύX***2006.06.12 SCS Inomata
            }
            
            //or
            //(�u�d�����[���A�h���X�`�F�b�N�Ώہv(get�d���`�F�b�N���s�t���O() == 2)&& 
            //�u�d�����[���A�h���X�����݂���v�iget�d���A�h���X()�߂�l�̒���>0�j&& 
            //�u���݂̃��O�C�����㗝���͎ҁiget�㗝���͎�()!=null�j�v) 
            else if (l_intDuplicateCheckExecFlag == 2 && l_intDuplicationCheckLenth > 0 && l_trader != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02444, 
                    getClass().getName() + " " + "validateChange", 
                    l_strDuplicationAccount.toString());
            } 
        }      

        //1.7createResponse( )
        WEB3AccInfoMailAddressChangeConfirmResponse l_response = 
            (WEB3AccInfoMailAddressChangeConfirmResponse) l_request.createResponse();
        
        if (l_intDuplicationCheckLenth > 0 && l_trader != null && l_intDuplicateCheckExecFlag  == 1)
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
     * �u���q�l���i���[���A�h���X�ύX�jsubmit�ύX�v�Q�ƁB<BR>
     * <BR>
     *  ===============================================<BR>
     *          �V�[�P���X�} :  �i���[���A�h���X�ύX�jsubmit�ύX<BR>
     *          ��̈ʒu     :  1.3  isMailAddress(String)<BR>
     *          ���[���A�h���X�Ƃ��ēK�؂łȂ��ꍇ�A��O���X���[����B<BR>
     *          �iisMailAddress() == false�j<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_00777 <BR> 
     *  ===============================================<BR>
     * ================================================<BR>
     *          �V�[�P���X�} :  �i���[���A�h���X�ύX�jsubmit�ύX<BR>
     *          ��̈ʒu     :  1.8.3�d�����[���A�h���X�����݂���<BR> 
     *          �u���݂̃��O�C�����ڋq�iget�㗝���͎�()==null�j�v<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_02443 <BR> 
     * ================================================ <BR>
     * ================================================  <BR>
     *          �V�[�P���X�} :  �i���[���A�h���X�ύX�jsubmit�ύX<BR>
     *          ��̈ʒu     :  1.8.3�d�����[���A�h���X�����݂���<BR> 
     *          �u���݂̃��O�C�����㗝���͎ҁiget�㗝���͎�()!=null�j�v<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_02444<BR> 
     * ================================================<BR>
     * @@param l_request - ���q�l��񃁁[���A�h���X�ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413D3CF00374
     */
    protected WEB3AccInfoMailAddressChangeCompleteResponse 
        submitChange(WEB3AccInfoMailAddressChangeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AccInfoMailDistributionChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        //1.1 get�ڋq( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();

        //�،���ЃR�[�h
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        //���X�R�[�h
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //�ڋq�R�[�h
        String l_accountCode = l_mainAccount.getAccountCode();
        
        //1.2 lock����
        WEB3GentradeAccountManager l_accManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            l_accManager.lockAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_accountCode);

        
        //1.3 validate( )
        l_request.validate();
        
        //1.4 validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5isMailAddress(String)
        if (!l_request.mailAddressDelFlag)
        {
            if (!WEB3StringTypeUtility.isMailAddress(l_request.changedMailAddress))
            {
                //1.6 ���[���A�h���X�Ƃ��ēK�؂łȂ��ꍇ�iisMailAddress() == false�j�A��O���X���[����B
                log.exiting(STR_METHOD_NAME);
                log.debug("���[���A�h���X�Ƃ��ēK�؂łȂ��ꍇ�A��O���X���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, getClass().getName() + " " + "submitChange");
            }
        }
        
        //1.7 get�⏕����( )
        //   �⏕�����I�u�W�F�N�g���擾����B 
        SubAccount l_subAccount = getSubAccount();

        //1.8 get�㗝���͎�( )
        //   �㗝���͎҃I�u�W�F�N�g���擾����B
        Trader l_trader = getTrader();

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
        int l_intSerialNo = Integer.parseInt(WEB3NameSerialNoDef.SERIAL_NO);
        WEB3GentradeBranch l_gentradeBranch = (WEB3GentradeBranch)l_mainAccount.getBranch();
        String l_strBranchPreferences = l_gentradeBranch.getMultiMailAddressEnforcement(
            l_strBranchCode,
            l_mainAccount.getInstitution().getInstitutionCode(),
            WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG,
            l_intSerialNo);

        if (l_accInfoMultiMailAddressInfo != null)
        {
            //�������[���A�h���X�Ή���Ёiget���X�v���t�@@�����X() == "1"�j�̏ꍇ
            if (WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T0.equals(l_strBranchPreferences))
            {
                //  [validate�����A�h���X���M�t���O()�ɐݒ肷�����]
                //��{���[���A�h���X�F ���N�G�X�g�f�[�^.�ύX�チ�[���A�h���X
                l_accInfoMultiMailAddressInfo.validateMultiSendMailAddressInfoFlag(
                    l_request.changedMailAddress);
            }
        }
        //�������[���A�h���X�Ή����{�i1.7 get�������[���A�h���X�Ή����{() == "2"�j�̏ꍇ
        if (WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strBranchPreferences))
        {
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���̗v�f����Loop�������s���B
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.multiMailAddressList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.multiMailAddressList.mailAddressInfoList;
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
                    l_accInfoMailAddressInfoUnits[i].mailAddressDiv))
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
            WEB3AccInfoMailAddressTypeUnit[] l_accInfoMailAddressTypeUnits = null;
            if (l_request.multiMailAddressList != null)
            {
                l_accInfoMailAddressTypeUnits =
                    l_request.multiMailAddressList.mailTypeInfoList;
            }
            int l_intLengthMailAddressType = 0;
            if (l_accInfoMailAddressTypeUnits != null)
            {
                l_intLengthMailAddressType = l_accInfoMailAddressTypeUnits.length;
            }
            for (int j = 0; j < l_intLengthMailAddressType; j++)
            {
                l_accInfoMailAddressTypeUnits[j].validateMailAddressTypeInfo(
                    l_accInfoMailAddressInfoUnits);
            }
        }
        //1.9 get�d���`�F�b�N���s�t���O(long, String, int)
        // �d�����[���`�F�b�N�̑Ώۂ��ǂ����𔻕ʂ���B 
        // �E���XID�Fget�ڋq().getBranch().getBranchId()�̖߂�l 
        // �E�v���t�@@�����X���FWEB3BranchPreferencesNameDef.PRE_NAME_DUPLO�i���X�v���t�@@�����X�e�[�u���d�l>�V�[�g
        //  �u�l�ݒ�d�l�v�Q�Ɓj 
        // �E�A�ԁF�v���t�@@�����X���̘A�� 
        int l_intDuplicateCheckExecFlag = 
            this.getDuplicateCheckExecFlag(
                l_mainAccount.getBranch().getBranchId(), 
                WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO, 
                1);      
        String[] l_strDuplicationAccounts = null;
        String[] l_strDuplicationAddressInfos = null;
        StringBuffer l_strDuplicationAccount = new StringBuffer();
        Object[] l_objDuplicationCheck = null;
        int l_intDuplicationCheckLenth = 0;
        if (l_intDuplicateCheckExecFlag != 0)
        {
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���̗v�f����Loop�������s���B
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.multiMailAddressList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.multiMailAddressList.mailAddressInfoList;
            }
            int l_intLength = 0;
            if (l_accInfoMailAddressInfoUnits != null)
            {
                l_intLength = l_accInfoMailAddressInfoUnits.length;
            }
            List l_lisDuplicationChecks = new ArrayList();
            for (int i = 0; i < l_intLength; i++)
            {
                //1.9.1get�d���A�h���X(String, String, String, String)
                //�E���[���A�h���X�F ���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X
                //�E�،���ЃR�[�h�Fget�ڋq().getInstitution().getInstitutionCode()�̖߂�l 
                //�E���X�R�[�h�Fget�ڋq().getBranch().getBranchCode()�̖߂�l 
                //�E�ڋq�R�[�h�Fget�ڋq().getAccountCode()�̖߂�l
                if (l_accInfoMailAddressInfoUnits[i].mailAddress == null)
                {
                    continue;
                }
                l_objDuplicationCheck = 
                    WEB3GentradeMailAddressDuplicationCheck.getDuplicateAddress(
                        l_accInfoMailAddressInfoUnits[i].mailAddress,
                        l_mainAccount.getAccountCode(),
                        l_mainAccount.getBranch().getBranchCode(),
                        l_mainAccount.getInstitution().getInstitutionCode());
                
                if(l_objDuplicationCheck != null)
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
            //1.9.2
            //����t���[�B
            //�d�����[���A�h���X�����݂����ꍇ�iget�d���A�h���X()�߂�l����>0�j�A�ȉ��������ɗ�O���X���[����B 
            //(�u�d�����[���A�h���X�`�F�b�N�Ώہv�iget�d���`�F�b�N���s�t���O() != 0)&& 
            //�u�d�����[���A�h���X�����݂���v�iget�d���A�h���X()�߂�l�̒���>0�j&& 
            //�u���݂̃��O�C�����ڋq�iget�㗝���͎�()==null�j�v) 
            l_strDuplicationAccounts = new String[l_intDuplicationCheckLenth];
            l_strDuplicationAddressInfos = new String[l_intDuplicationCheckLenth];
            for (int i = 0; i < l_intDuplicationCheckLenth; i++)
            {
            	
//				�R�[�f�B���O�~�X�̈וύX***2006.06.12 SCS Inomata-->
//				l_strDuplicationAccounts[i] = 
//					"[" + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//					+ ":" + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]) + "]";
//				l_strDuplicationAddressInfos[i] =
//					WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//					+ "," + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]);
//				l_strDuplicationAccount.append(l_strDuplicationAccounts[i]);
                l_strDuplicationAccounts[i] = 
                    "\n[���X " + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
                    + "�F�ڋq�R�[�h " + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]) + "]";
                l_strDuplicationAddressInfos[i] =
                    WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
                    + "," + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]);
//				<--�R�[�f�B���O�~�X�̈וύX***2006.06.12 SCS Inomata
                l_strDuplicationAccount.append(l_strDuplicationAccounts[i]);
            }
            if (l_intDuplicateCheckExecFlag != 0 && l_intDuplicationCheckLenth > 0 && l_trader == null)
            {
                log.exiting(STR_METHOD_NAME);
//				�d�l�ύX***2006.06.12 SCS Inomata-->
//                throw new WEB3BusinessLayerException(
//                    WEB3ErrorCatalog.BUSINESS_ERROR_02443, 
//                    getClass().getName() + " " + "submitChange",
//                    l_strDuplicationAccount.toString());
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02443, 
					getClass().getName() + " " + "submitChange","");
//				<--�d�l�ύX***2006.06.12 SCS Inomata
            }
            
            //or
            //(�u�d�����[���A�h���X�`�F�b�N�Ώہv(get�d���`�F�b�N���s�t���O() == 2)&& 
            //�u�d�����[���A�h���X�����݂���v�iget�d���A�h���X()�߂�l�̒���>0�j&& 
            //�u���݂̃��O�C�����㗝���͎ҁiget�㗝���͎�()!=null�j�v) 
            else if (l_intDuplicateCheckExecFlag == 2 && l_intDuplicationCheckLenth > 0 && l_trader != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02444, 
                    getClass().getName() + " " + "submitChange",
                    l_strDuplicationAccount.toString());
            } 
        }

        //getDataSourceObject( )
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        MainAccountParams l_mainAccountParams = new MainAccountParams(l_mainAccountRow);

        if (l_intDuplicateCheckExecFlag == 0 
            || (l_intDuplicateCheckExecFlag != 0 && l_intDuplicationCheckLenth == 0) 
            || (l_intDuplicateCheckExecFlag == 1 && l_intDuplicationCheckLenth > 0 && l_trader != null))
        {
            //1.10 getCommonOrderValidator( )
            //�����`�F�b�N�C���X�^���X���擾����B
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
            
            //1.11 validate����p�X���[�h(�㗝���͎� : Trader, �⏕���� : SubAccount, �p�X���[�h : String)
            OrderValidationResult l_validationResult = 
                l_orderValidator.validateTradingPassword(
                    l_trader, 
                    l_subAccount, 
                    l_request.password); 
            
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.debug("�`�F�b�N�G���[�̏ꍇ�͂��O���X���[����");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.13 doUpdateQuery(arg0 : PrimaryKey, arg1(*2) : Map)
            if (l_request.multiMailAddressInfo.execMailFlag != null)
            {
                //���N�G�X�g�f�[�^.�����A�h���X���.���ʒm���M�t���O != null���A
                //���N�G�X�g�f�[�^.�����A�h���X���.���ʒm���M�t���O=0�̏ꍇ
                if (WEB3OrderExeFlagDef.NOT_SEND_MAIL.equals(
                    l_request.multiMailAddressInfo.execMailFlag))
                {
                    //������胁�[�����M�t���O: 0
                    l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.FALSE);

                    //�敨OP��胁�[�����M�t���O:0
                    l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.FALSE);
                }
                //���N�G�X�g�f�[�^.�����A�h���X���.���ʒm���M�t���O != null���A
                //���N�G�X�g�f�[�^.�����A�h���X���.���ʒm���M�t���O!=0�̏ꍇ
                else
                {
                    //������胁�[�����M�t���O: 1
                    l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);

                    //�敨OP��胁�[�����M�t���O:1
                    l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
                }
            }

            if (l_request.multiMailAddressInfo.unExecMailFlag != null)
            {
                //���N�G�X�g�f�[�^.�����A�h���X���.�����ʒm���M�t���O != null���A
                //���N�G�X�g�f�[�^.�����A�h���X���.�����ʒm���M�t���O=0�̏ꍇ
                if (WEB3OrderUnexeFlagDef.NOT_SEND_MAIL.equals(
                    l_request.multiMailAddressInfo.unExecMailFlag))
                {
                    //��������胁�[�����M�t���O:0
                    l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.FALSE);

                    //�敨OP����胁�[�����M�t���O:0
                    l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.FALSE);
                }
                //���N�G�X�g�f�[�^.�����A�h���X���.�����ʒm���M�t���O != null���A
                //���N�G�X�g�f�[�^.�����A�h���X���.�����ʒm���M�t���O!=0�̏ꍇ
                else
                {
                    //��������胁�[�����M�t���O:1
                    l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);

                    //�敨OP����胁�[�����M�t���O:1
                    l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
                }
            }

            if (l_request.multiMailAddressInfo.guidanceMailFlag2 != null)
            {
                //���N�G�X�g�f�[�^.�����A�h���X���.�ē����[���Q���M�t���O != null���A
                //���N�G�X�g�f�[�^.�����A�h���X���.�ē����[���Q���M�t���O=0�̏ꍇ
                //�ē����[�����M�t���O:0
                if (WEB3InformationMail2FlagDef.NOT_SEND_MAIL.equals(
                    l_request.multiMailAddressInfo.guidanceMailFlag2))
                {
                    l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);
                }
                //���N�G�X�g�f�[�^.�����A�h���X���.�ē����[���Q���M�t���O != null���A
                //���N�G�X�g�f�[�^.�����A�h���X���.�ē����[���Q���M�t���O!=0�̏ꍇ
                else
            	{
                	//�ē����[�����M�t���O:1
                    l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
                }
            }
            
            if (l_request.mailAddressDelFlag)
            {
                l_mainAccountParams.setEmailAddress(null);

                //email�A�h���X�X�V�҃R�[�h
                //�����R�[�h
                l_mainAccountParams.setEmailLastUpdater(l_mainAccountRow.getAccountCode());
                //email�A�h���X�X�V����
                //���������@@��TradingSystem.getSystemTimestamp()
                l_mainAccountParams.setEmailLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            }
            else
            {
                l_mainAccountParams.setAccOpenSendMailStatus(WEB3EmailStatusDef.EMAIL_SEND_END);

                if (l_request.changedMailAddress != null
                    && !l_request.changedMailAddress.equals(l_mainAccountParams.getEmailAddress()))
                {
                    //email�A�h���X�X�V�҃R�[�h
                    //�����R�[�h
                    l_mainAccountParams.setEmailLastUpdater(l_mainAccountRow.getAccountCode());
                    //email�A�h���X�X�V����
                    //���������@@��TradingSystem.getSystemTimestamp()
                    l_mainAccountParams.setEmailLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
                }
                //�i���N�G�X�g�f�[�^.���[���A�h���X�폜�t���O == false && ���N�G�X�g�f�[�^.�ύX�チ�[���A�h���X != null�j�̏ꍇ�A
                //���N�G�X�g�f�[�^.�ύX�チ�[���A�h���X
                if (l_request.changedMailAddress != null)
                {
                    l_mainAccountParams.setEmailAddress(l_request.changedMailAddress);
                }
            }

            l_mainAccountParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());

            //�i���N�G�X�g�f�[�^.���[���A�h���X�폜�t���O == false�j�̏ꍇ�A1�F�����ρiEmail���M�ρj�B
            //�ȊO�A�i�����l�j�B
            if (!l_request.mailAddressDelFlag)
            {
                l_mainAccountParams.setAccOpenSendMailStatus(WEB3AccOpenSendMailStatusDef.EMAIL_SENDED);
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

            //���N�G�X�g�f�[�^.�����A�h���X���.���ʒm���M�t���O != null�̏ꍇ�A���N�G�X�g�f�[�^.�����A�h���X���.���ʒm���M�t���O
            if (l_request.multiMailAddressInfo.execMailFlag != null)
            {
                int l_intExecMailFlag = Integer.parseInt(l_request.multiMailAddressInfo.execMailFlag);
                l_mainAccountParams.setOrderExeFlag(l_intExecMailFlag);
            }

            //���N�G�X�g�f�[�^.�����A�h���X���.�����ʒm���M�t���O != null�̏ꍇ�A���N�G�X�g�f�[�^.�����A�h���X���.�����ʒm���M�t���O
            if (l_request.multiMailAddressInfo.unExecMailFlag != null)
            {
                int l_intUnExecMailFlag = Integer.parseInt(l_request.multiMailAddressInfo.unExecMailFlag);
                l_mainAccountParams.setOrderUnexeFlag(l_intUnExecMailFlag);
            }

            //���N�G�X�g�f�[�^.�����A�h���X���.�d�v�A�����[�����M�t���O != null�̏ꍇ�A���N�G�X�g�f�[�^.�����A�h���X���.�d�v�A�����[�����M�t���O
            if (l_request.multiMailAddressInfo.importantMailFlag != null)
            {
                int l_intImportantMailFlag =
                    Integer.parseInt(l_request.multiMailAddressInfo.importantMailFlag);
                l_mainAccountParams.setImportantConnectionMailFlag(l_intImportantMailFlag);
            }

            //���N�G�X�g�f�[�^.�����A�h���X���.�ē����[���Q���M�t���O != null�̏ꍇ�A���N�G�X�g�f�[�^.�����A�h���X���.�ē����[���Q���M�t���O
            if (l_request.multiMailAddressInfo.guidanceMailFlag2 != null)
            {
                int l_intGuidanceMailFlag2 =
                    Integer.parseInt(l_request.multiMailAddressInfo.guidanceMailFlag2);
                l_mainAccountParams.setInformationMail2Flag(l_intGuidanceMailFlag2);
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
        if (l_request.multiMailAddressList != null)
        {
            l_accInfoMailAddressInfoUnits =
                l_request.multiMailAddressList.mailAddressInfoList;
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
                this.updateAccountMailAddress(l_mainAccount, l_accInfoMailAddressInfoUnits[i]);
            }
        }

        WEB3AccInfoMailAddressTypeUnit[] l_accInfoMailAddressTypeUnits = null;
        if (l_request.multiMailAddressList != null)
        {
            l_accInfoMailAddressTypeUnits =
                l_request.multiMailAddressList.mailTypeInfoList;
        }
        int l_intLengthMailAddressType = 0;
        if (l_accInfoMailAddressTypeUnits != null)
        {
            l_intLengthMailAddressType = l_accInfoMailAddressTypeUnits.length;
        }

        Map l_hmMainAccounts = new HashMap();
        for (int j = 0; j < l_intLengthMailAddressType; j++)
        {
            //������胁�[�����M�t���O
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[����ʔԍ� = 1:������胁�[���̏ꍇ�A�X�V����B
            if (WEB3MailAssortmentDivDef.EQUITY_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null)
                {
                    //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ�= null�̏ꍇ�A0
                    l_hmMainAccounts.put("equity_order_exe_mail_flag", BooleanEnum.FALSE);
                }
                else
                {
                    //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null�̏ꍇ�A1
                    l_hmMainAccounts.put("equity_order_exe_mail_flag", BooleanEnum.TRUE);
                }
            }
            
            //��������胁�[�����M�t���O
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[����ʔԍ� = 2:��������胁�[���̏ꍇ�A�X�V����B
            if (WEB3MailAssortmentDivDef.EQUITY_NOT_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null)
                {
                    //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� = null�̏ꍇ�A0
                    l_hmMainAccounts.put("equity_order_unexec_mail_flag", BooleanEnum.FALSE);
                }
                else
                {
                    //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null�̏ꍇ�A1
                    l_hmMainAccounts.put("equity_order_unexec_mail_flag", BooleanEnum.TRUE);
                }
            }

            //�敨OP��胁�[�����M�t���O
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[����ʔԍ� = 3:��OP��胁�[���̏ꍇ�A �X�V����B
            if (WEB3MailAssortmentDivDef.FUTURES_OPTION_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null)
                {
                    //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� = null�̏ꍇ�A0
                    l_hmMainAccounts.put("ifo_order_exec_mail_flag", BooleanEnum.FALSE);
                }
                else
                {
                    //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null�̏ꍇ�A1
                    l_hmMainAccounts.put("ifo_order_exec_mail_flag", BooleanEnum.TRUE);
                }
            }

            //�敨OP����胁�[�����M�t���O
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[����ʔԍ� = 4:��OP����胁�[���̏ꍇ�A�X�V����B
            if (WEB3MailAssortmentDivDef.FUTURES_OPTION_NOT_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null)
                {
                    //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� = null�̏ꍇ�A0
                    l_hmMainAccounts.put("ifo_order_unexec_mail_flag", BooleanEnum.FALSE);
                }
                else
                {
                    //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null�̏ꍇ�A1
                    l_hmMainAccounts.put("ifo_order_unexec_mail_flag", BooleanEnum.TRUE);
                }
            }

            //�ē����[�����M�t���O
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[����ʔԍ� = 6:�ē����[���̏ꍇ�A�X�V����B
            if (WEB3MailAssortmentDivDef.GUIDE_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null)
                {
                    //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� = null�̏ꍇ�A0
                    l_hmMainAccounts.put("information_mail_flag", BooleanEnum.FALSE);
                }
                else
                {
                    //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null�̏ꍇ�A1
                    l_hmMainAccounts.put("information_mail_flag", BooleanEnum.TRUE);
                }
            }

            //�X�V����
            //���������@@��TradingSystem.getSystemTimestamp() 
            l_hmMainAccounts.put("last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());

            //�ē����[�����M�t���O�X�V�҃R�[�h
            //�ē����[�����M�t���O�X�V����
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[����ʔԍ� =
            //6:�ē����[���̏ꍇ�A�ȉ���������{����B
            if (WEB3MailAssortmentDivDef.GUIDE_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� = null�̊��A
                //�ڋq�}�X�^�[�s.�ē����[�����M�t���O!=0))�̏ꍇ�A�����R�[�h�B
                //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� = null�̊��A 
                //�ڋq�}�X�^�[�s.�ē����[�����M�t���O!=0))�̏ꍇ�A���������@@��TradingSystem.getSystemTimestamp() �B
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null
                    && !BooleanEnum.FALSE.equals(l_mainAccountParams.getInformationMailFlag()))
                {
                    l_hmMainAccounts.put("inf_mail_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("inf_mail_flg_updated_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
                //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null���A
                // �ڋq�}�X�^�[�s.�ē����[�����M�t���O !=1))�̏ꍇ�A�����R�[�h�B
                //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null���A 
                //�ڋq�}�X�^�[�s.�ē����[�����M�t���O !=1))�̏ꍇ�A���������@@��TradingSystem.getSystemTimestamp() �B
                else if (l_accInfoMailAddressTypeUnits[j].mailAddressNo != null
                    && !BooleanEnum.TRUE.equals(l_mainAccountParams.getInformationMailFlag()))
                {
                    l_hmMainAccounts.put("inf_mail_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("inf_mail_flg_updated_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
            }

            //������胁�[�����M�t���O�X�V�҃R�[�h
            //������胁�[�����M�t���O�X�V����
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[����ʔԍ� =
            //1:������胁�[���̏ꍇ�A�ȉ���������{����B
            if (WEB3MailAssortmentDivDef.EQUITY_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� = null�̊��A
                //�ڋq�}�X�^�[�s.������胁�[�����M�t���O!=0))�̏ꍇ�A�����R�[�h�B
                //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� = null�̊��A
                //�ڋq�}�X�^�[�s.������胁�[�����M�t���O!=0))�̏ꍇ�A���������@@��TradingSystem.getSystemTimestamp() �B
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null
                    && !BooleanEnum.FALSE.equals(l_mainAccountParams.getEquityOrderExeMailFlag()))
                {
                    l_hmMainAccounts.put("eq_exe_ml_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("eq_exe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
                //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null���A
                //�ڋq�}�X�^�[�s.������胁�[�����M�t���O!=1))�̏ꍇ�A�����R�[�h�B
                //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null���A 
                //�ڋq�}�X�^�[�s.������胁�[�����M�t���O!=1))�̏ꍇ�A���������@@��TradingSystem.getSystemTimestamp() �B
                else if (l_accInfoMailAddressTypeUnits[j].mailAddressNo != null
                    && !BooleanEnum.TRUE.equals(l_mainAccountParams.getEquityOrderExeMailFlag()))
                {
                    l_hmMainAccounts.put("eq_exe_ml_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("eq_exe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
            }

            //��������胁�[�����M�t���O�X�V�҃R�[�h
            //��������胁�[�����M�t���O�X�V����
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[����ʔԍ� =  
            //2:��������胁�[���̏ꍇ�A�ȉ���������{����B
            if (WEB3MailAssortmentDivDef.EQUITY_NOT_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� = null�̊��A
                //�ڋq�}�X�^�[�s.��������胁�[�����M�t���O!=0))�̏ꍇ�A�����R�[�h�B
                //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� = null�̊��A
                //�ڋq�}�X�^�[�s.��������胁�[�����M�t���O!=0))�̏ꍇ�A���������@@��TradingSystem.getSystemTimestamp() �B
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null
                    && !BooleanEnum.FALSE.equals(l_mainAccountParams.getEquityOrderUnexecMailFlag()))
                {
                    l_hmMainAccounts.put("eq_unexe_ml_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("eq_unexe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
                //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null���A
                //�ڋq�}�X�^�[�s.��������胁�[�����M�t���O !=1))�̏ꍇ�A�����R�[�h�B
                //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null���A
                //�ڋq�}�X�^�[�s.��������胁�[�����M�t���O !=1))�̏ꍇ�A���������@@��TradingSystem.getSystemTimestamp() �B
                else if (l_accInfoMailAddressTypeUnits[j].mailAddressNo != null
                    && !BooleanEnum.TRUE.equals(l_mainAccountParams.getEquityOrderUnexecMailFlag()))
                {
                    l_hmMainAccounts.put("eq_unexe_ml_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("eq_unexe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
            }

            //��OP��胁�[�����M�t���O�X�V�҃R�[�h
            //��OP��胁�[�����M�t���O�X�V����
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[����ʔԍ� =
            //3:��OP��胁�[���̏ꍇ�A�ȉ���������{����B
            if (WEB3MailAssortmentDivDef.FUTURES_OPTION_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� = null�̊��A
                //�ڋq�}�X�^�[�s.��OP��胁�[�����M�t���O!=0))�̏ꍇ�A�����R�[�h�B
                //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� = null�̊��A
                //�ڋq�}�X�^�[�s.��OP��胁�[�����M�t���O!=0))�̏ꍇ�A���������@@��TradingSystem.getSystemTimestamp() �B
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null
                    && !BooleanEnum.FALSE.equals(l_mainAccountParams.getIfoOrderExecMailFlag()))
                {
                    l_hmMainAccounts.put("ifo_exe_ml_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("ifo_exe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
                //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null���A
                //�ڋq�}�X�^�[�s.��OP��胁�[�����M�t���O !=1))�̏ꍇ�A�����R�[�h�B
                //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null���A
                //�ڋq�}�X�^�[�s.��OP��胁�[�����M�t���O !=1))�̏ꍇ�A���������@@��TradingSystem.getSystemTimestamp() �B
                else if (l_accInfoMailAddressTypeUnits[j].mailAddressNo != null
                    && !BooleanEnum.TRUE.equals(l_mainAccountParams.getIfoOrderExecMailFlag()))
                {
                    l_hmMainAccounts.put("ifo_exe_ml_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("ifo_exe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
            }

            //��OP����胁�[�����M�t���O�X�V�҃R�[�h
            //��OP����胁�[�����M�t���O�X�V����
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[����ʔԍ� =
            //4:��OP����胁�[���̏ꍇ�A�ȉ���������{����B
            if (WEB3MailAssortmentDivDef.FUTURES_OPTION_NOT_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� = null�̊��A
                //�ڋq�}�X�^�[�s.��OP����胁�[�����M�t���O!=0))�̏ꍇ�A�����R�[�h�B
                //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� = null�̊��A
                //�ڋq�}�X�^�[�s.��OP����胁�[�����M�t���O!=0))�̏ꍇ�A���������@@��TradingSystem.getSystemTimestamp() �B
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null
                    && !BooleanEnum.FALSE.equals(l_mainAccountParams.getIfoOrderUnexecMailFlag()))
                {
                    l_hmMainAccounts.put("ifo_unexe_ml_flg_last_updater",l_accountCode);
                    l_hmMainAccounts.put("ifo_unexe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
                //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null���A
                //�ڋq�}�X�^�[�s.��OP����胁�[�����M�t���O !=1))�̏ꍇ�A�����R�[�h�B
                //�A���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ� != null���A
                //�ڋq�}�X�^�[�s.��OP����胁�[�����M�t���O !=1))�̏ꍇ�A���������@@��TradingSystem.getSystemTimestamp() �B
                else if (l_accInfoMailAddressTypeUnits[j].mailAddressNo != null
                    && !BooleanEnum.TRUE.equals(l_mainAccountParams.getIfoOrderUnexecMailFlag()))
                {
                    l_hmMainAccounts.put("ifo_unexe_ml_flg_last_updater",l_accountCode);
                    l_hmMainAccounts.put("ifo_unexe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
            }

            if (l_accInfoMailAddressTypeUnits[j] != null)
            {
                this.updateMailAssortment(l_mainAccount, l_accInfoMailAddressTypeUnits[j]);
            }
        }
        
        if (l_intLengthMailAddressType != 0)
        {
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(
                    (MainAccountPK)l_mainAccountRow.getPrimaryKey(), l_hmMainAccounts);
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
        //1.14 createResponse()
        WEB3AccInfoMailAddressChangeCompleteResponse l_response = 
            (WEB3AccInfoMailAddressChangeCompleteResponse) l_request.createResponse();
        
        if (l_intDuplicationCheckLenth > 0 && l_trader != null && l_intDuplicateCheckExecFlag  == 1)
        {
            l_response.duplicationAddressInfo = l_strDuplicationAddressInfos ;
        }
        
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
     * �@@���R�[�h.�v���t�@@�����X�̒l�F "1" OR "4" �� 1 <BR>
     * �@@���R�[�h.�v���t�@@�����X�̒l�F "2" OR "3" �� 2 <BR>
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
        
        //���R�[�h.�v���t�@@�����X�̒l�F "1" OR "4" �� 1
        else if (WEB3DuplicationMailAddressCheckDef.NO_EXCEPTION.equals(l_branchPreferencesRow.getValue()) 
            || WEB3DuplicationMailAddressCheckDef.CREATE_EXCEPTION_ADMIN.equals(l_branchPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return 1;
        }
        
        //���R�[�h.�v���t�@@�����X�̒l�F "2" OR "3" �� 2
        else if (WEB3DuplicationMailAddressCheckDef.CREATE_EXCEPTION.equals(l_branchPreferencesRow.getValue()) 
            || WEB3DuplicationMailAddressCheckDef.CREATE_EXCEPTION_CUST.equals(l_branchPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return 2;
        }
        
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (update�ڋq���[���A�h���X)<BR>
     * �P�j���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X�敪==<BR>
     * �h2:�g�у��[���A�h���X�h<BR>
     * �����N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X == null �̏ꍇ�A<BR>
     * �ȉ��̏����Łu�ڋq���[���A�h���X�e�[�u���v���猟������B<BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h�F�ڋq.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�ڋq.���X�R�[�h<BR>
     * �@@�@@�����R�[�h�F�ڋq.�����R�[�h<BR>
     * �@@�@@���[���A�h���X�ԍ��F���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X�ԍ�<BR>
     * <BR>
     * �@@�������ʂ��擾�ł���ꍇ�A<BR>
     * �@@�c�a�X�V���s���B<BR>
     * �@@�u���[���A�h���X�ύX_�ڋq���[���A�h���X�e�[�u��.xls#�ڋq���[���A�h���X�e�[�u��_delete�v�Q�ƁB<BR>
     * <BR>
     * �Q�j �P�j�ȊO�̏ꍇ�A�ȉ��̏����Łu�ڋq���[���A�h���X�e�[�u���v���猟������B<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h�F�ڋq.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�ڋq.���X�R�[�h<BR>
     * �@@�@@�����R�[�h�F�ڋq.�����R�[�h<BR>
     * �@@�@@���[���A�h���X�ԍ��F���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�j �������ʂ��擾�ł���ꍇ�A<BR>
     * �@@�c�a�X�V���s���B<BR>
     * �@@�u���[���A�h���X�ύX_�ڋq���[���A�h���X�e�[�u��.xls#�ڋq���[���A�h���X�e�[�u��_update�v�Q�ƁB<BR>
     * <BR>
     * �@@�Q�|�Q�j �������ʂ��擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�c�a�X�V���s���B<BR>
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

            //�P�j���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X�敪==�h2:�g�у��[���A�h���X�h
            //�����N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���.���[���A�h���X == null �̏ꍇ�A
            if (WEB3AddressDivDef.MOBILE_MAIL_ADDRESS.equals(l_mailAddressInfo.mailAddressDiv)
                && l_mailAddressInfo.mailAddress == null)
            {
                if (l_lisAccountMailAddressRows.size() == 1)
                {
                    //�������ʂ��擾�ł���ꍇ�A
                    //�u���[���A�h���X�ύX_�ڋq���[���A�h���X�e�[�u��.xls#�ڋq���[���A�h���X�e�[�u��_delete�v�Q�ƁB
                    AccountMailAddressPK l_accountMailAddressPK =
                        new AccountMailAddressPK(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode,
                            Long.parseLong(l_mailAddressInfo.mailAddressNo));
    
                    l_queryProcessor.doDeleteQuery(l_accountMailAddressPK);
                    log.exiting(STR_METHOD_NAME);
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
                    //�u���[���A�h���X�ύX_�ڋq���[���A�h���X�e�[�u��.xls#�ڋq���[���A�h���X�e�[�u��_update�v�Q�ƁB
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
                        l_map.put("email_last_updater", l_strAccountCode);
                        l_map.put("email_last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());
                    }
                    //TradingSystem.getSystemTimestamp() �B
                    l_map.put("last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_queryProcessor.doUpdateAllQuery(
                        AccountMailAddressRow.TYPE,
                        l_strQuery,
                        l_objQuery,
                        l_map);
                    log.exiting(STR_METHOD_NAME);
                }
                else
                {
                    //�Q�|�Q�j �������ʂ��擾�ł��Ȃ��ꍇ�A
                    //�ڋq���[���A�h���X�e�[�u��_insert
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
                    //�����R�[�h
                    l_accountMailAddressParams.setEmailLastUpdater(l_strAccountCode);
                    //TradingSystem.getSystemTimestamp() �B
                    l_accountMailAddressParams.setEmailLastUpdatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_accountMailAddressParams.setCreatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_accountMailAddressParams.setLastUpdatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_queryProcessor.doInsertQuery(l_accountMailAddressParams);
                    log.exiting(STR_METHOD_NAME);
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
    }

    /**
     * (update���[�����)<BR>
     * �P�j���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��[i]���[���A�h���X�ԍ� == null �̏ꍇ�A<BR>
     * �@@�ȉ��̏����Łu���[����ʏ��e�[�u���v���猟������B<BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h�F�ڋq.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�ڋq.���X�R�[�h<BR>
     * �@@�@@�����R�[�h�F�ڋq.�����R�[�h<BR>
     * �@@�@@���[����ʋ敪�F���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[����ʋ敪<BR>
     * <BR>
     * �@@�������ʂ��擾�ł���ꍇ�A<BR>
     * �@@�c�a�X�V���s���B<BR>
     * �@@�u���[���A�h���X�ύX_���[����ʃe�[�u��.xls#���[����ʃe�[�u��_delete�v�Q�ƁB<BR>
     * <BR>
     * �Q�j���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��[i]���[���A�h���X�ԍ� != null �̏ꍇ�A<BR>
     * �@@�ȉ��̏����Łu���[����ʏ��e�[�u���v���猟������B<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h�F�ڋq.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�ڋq.���X�R�[�h<BR>
     * �@@�@@�����R�[�h�F�ڋq.�����R�[�h<BR>
     * �@@�@@���[����ʋ敪�F���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[����ʋ敪<BR>
     * <BR>
     * �@@�Q�|�P�j�������ʂ��擾�ł���ꍇ�A<BR>
     * �@@�c�a�X�V���s���B<BR>
     * �@@�u���[���A�h���X�ύX_���[����ʃe�[�u��.xls#���[����ʃe�[�u��_update�v�Q�ƁB<BR>
     * <BR>
     * �@@�Q�|�Q�j�������ʂ��擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�c�a�X�V���s���B<BR>
     * �@@�u���[���A�h���X�ύX_���[����ʃe�[�u��.xls#���[����ʃe�[�u��_insert�v�Q�ƁB<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@param l_mailAddressTypeUnit - (���[����ʏ��)<BR>
     * ���[����ʏ��<BR>
     * @@throws WEB3BaseException
     */
    protected void updateMailAssortment(
        WEB3GentradeMainAccount l_mainAccount,
        WEB3AccInfoMailAddressTypeUnit l_mailAddressTypeUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateMailAssortment("
            + "WEB3GentradeMainAccount, WEB3AccInfoMailAddressTypeUnit)";
        log.entering(STR_METHOD_NAME);

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
            + " and account_code = ? ";

        l_strQuery += " and mail_assortment_div = ? ";

        Object[] l_objQuery = null;

        l_objQuery = new Object[]{
            l_strInstitutionCode,
            l_strBranchCode,
            l_strAccountCode,
            l_mailAddressTypeUnit.mailAddressTypeNo};

        List l_lisMailAssortmentRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisMailAssortmentRows = l_queryProcessor.doFindAllQuery(
                MailAssortmentRow.TYPE,
                l_strQuery,
                l_objQuery);

            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��[i]���[���A�h���X�ԍ� == null �̏ꍇ�A
            if (l_mailAddressTypeUnit.mailAddressNo == null)
            {
                if (l_lisMailAssortmentRows.size() > 0)
                {
                    int l_intSize = l_lisMailAssortmentRows.size();
                    for (int i = 0; i < l_intSize; i++)
                    {
                        MailAssortmentPK l_mailAssortmentPK = new MailAssortmentPK(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode,
                            ((MailAssortmentRow)l_lisMailAssortmentRows.get(i)).getEmailAddressNumber(),
                            l_mailAddressTypeUnit.mailAddressTypeNo);
                        l_queryProcessor.doDeleteQuery(l_mailAssortmentPK);
                        log.exiting(STR_METHOD_NAME);
                    }
                }
            }
            //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��[i]���[���A�h���X�ԍ� != null �̏ꍇ
            else
            {
                if (l_lisMailAssortmentRows.size() > 0)
                {
                    int l_intSize = l_lisMailAssortmentRows.size();
                    for (int i = 0; i < l_intSize; i++)
                    {
                        MailAssortmentRow l_mailAssortmentRow =
                            (MailAssortmentRow)l_lisMailAssortmentRows.get(i);

                        MailAssortmentPK l_mailAssortmentPK = new MailAssortmentPK(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode,
                            l_mailAssortmentRow.getEmailAddressNumber(),
                            l_mailAddressTypeUnit.mailAddressTypeNo);
                        l_queryProcessor.doDeleteQuery(l_mailAssortmentPK);
    
                        MailAssortmentParams l_mailAssortmentParams = new MailAssortmentParams(l_mailAssortmentRow);
                        //���[����ʍs.���[���A�h���X�ԍ� !=���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ�
                        //�����N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ��I��null�̏ꍇ
                        //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ��B
                        //��L�ȊO�͍X�V�����B
                        //���[����ʍs.���[���A�h���X�ԍ� !=
                        //���N�G�X�g�f�[�^.�����A�h���X���X�g.���[����ʏ��.���[���A�h���X�ԍ��̏ꍇ�A�����R�[�h�B
                        //��L�ȊO�͍X�V�����B
                        if (!WEB3Toolkit.isEquals(
                            l_mailAddressTypeUnit.mailAddressNo, l_mailAssortmentRow.getEmailAddressNumber() + ""))
                        {
                            l_mailAssortmentParams.setEmailAddressNumber(new Long(l_mailAddressTypeUnit.mailAddressNo).longValue());
                            l_mailAssortmentParams.setLastUpdater(l_strAccountCode);
                        }
    
                        l_mailAssortmentParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
                        l_queryProcessor.doInsertQuery(l_mailAssortmentParams);
                    }
                }
                else
                {
                    MailAssortmentParams l_mailAssortmentParams =
                        new MailAssortmentParams();
                    l_mailAssortmentParams.setInstitutionCode(l_strInstitutionCode);
                    l_mailAssortmentParams.setBranchCode(l_strBranchCode);
                    l_mailAssortmentParams.setAccountCode(l_strAccountCode);
                    l_mailAssortmentParams.setEmailAddressNumber(
                        Long.parseLong(l_mailAddressTypeUnit.mailAddressNo));
                    l_mailAssortmentParams.setMailAssortmentDiv(l_mailAddressTypeUnit.mailAddressTypeNo);
                    l_mailAssortmentParams.setLastUpdater(l_strAccountCode);
                    l_mailAssortmentParams.setCreatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_mailAssortmentParams.setLastUpdatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_queryProcessor.doInsertQuery(l_mailAssortmentParams);
                    log.exiting(STR_METHOD_NAME);
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
    }
}
@
