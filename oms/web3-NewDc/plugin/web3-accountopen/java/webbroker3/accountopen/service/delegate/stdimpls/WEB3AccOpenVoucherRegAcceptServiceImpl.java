head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.08.52.25;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b06481808;
filename	WEB3AccOpenVoucherRegAcceptServiceImpl.java;

1.1
date	2011.03.14.03.35.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherRegAcceptServiceImpl.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �����J�ݓ`�[�o�^��t�T�[�r�XImpl(WEB3AccOpenVoucherRegAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2004/12/15 �A�C��(���u) �V�K�쐬
Revesion History : 2006/07/13 ���� (���u) �d�l�ύX ���f��072�A080
Revesion History : 2006/08/15 �Ԑi (���u) �d�l�ύX ���f��091 �c�a�X�V�d�l  No.009
Revesion History : 2006/09/12 ���r (���u) �d�l�ύX ���f��099
Revesion History : 2006/09/14 ���r (���u) �d�l�ύX ���f��104
Revesion History : 2006/09/14 �ęԍg (���u) �d�l�ύX ���f��098�A103 �c�a�X�V�d�lNo.014 015 016
Revesion History : 2006/09/19 �ęԍg (���u) ���f�� No.105  �c�a�X�V�d�l  No.017
Revesion History : 2006/10/08 ���G�� (���u) �c�a�X�V�d�l  No.019
Revesion History : 2006/10/17 �ęԍg (���u) �@@�d�l�ύX ���f��No.107�A�A�\�[�X�̏C���˗�
Revesion History : 2006/10/26 ���G�� (���u) ��QNo.�t02917�Ή�
Revesion History : 2006/10/29 �����q (���u) �d�l�ύX ���f�� No.108  �c�a�X�V�d�l  No.021 022
Revesion History : 2006/11/16 �����q (���u) �d�l�ύX ���f�� No.109  �c�a�X�V�d�l  No.023
Revesion History : 2006/11/20 �����q (���u) �d�l�ύX ���f�� No.110
Revesion History : 2006/11/20 �����q (���u) ��QNo.�t02952�Ή�
Revesion History : 2006/11/20 �����q (���u) ��QNo.�t02953�Ή�
Revesion History : 2006/11/21 �����q (���u) �d�l�ύX ���f�� No.111
Revesion History : 2006/11/28 �����q (���u) �d�l�ύX ���f�� No.114
Revesion History : 2006/11/28 �����q (���u) �d�l�ύX ���f�� No.116
Revesion History : 2006/12/05 �����q (���u) �d�l�ύX ���f�� No.118
Revesion History : 2007/05/28 �����q (���u) �d�l�ύX ���f�� No.124,No.125,No.126,No.129
Revesion History : 2007/06/05 �����q (���u) �d�l�ύX ���f�� No.131,No.134
Revesion History : 2007/06/11 �����q (���u) �d�l�ύX ���f�� No.143
Revesion History : 2007/09/20 �����F (���u) �d�l�ύX ���f�� No.144,145
Revesion History : 2009/08/13 �đo�g (���u) �d�l�ύX ���f�� No.177,178,183 �c�a�X�V�d�lNo.046
Revesion History : 2009/09/04 �����F (���u) �d�l�ύX ���f��211
Revesion History : 2010/02/10 ���g (���u) �d�l�ύX ���f��No.218
Revesion History : 2010/11/10 �����C (���u) �d�l�ύX ���f�� No.221  �c�a�X�V�d�l  No.054
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountopen.WEB3AccOpenBranch;
import webbroker3.accountopen.data.ExpAccountOpenDao;
import webbroker3.accountopen.data.ExpAccountOpenPK;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.accountopen.data.HostAccOpenAcceptRow;
import webbroker3.accountopen.data.HostAccRegVoucherDao;
import webbroker3.accountopen.data.HostAccRegVoucherPK;
import webbroker3.accountopen.data.HostAccRegVoucherRow;
import webbroker3.accountopen.data.HostConditionRegVoucherDao;
import webbroker3.accountopen.data.HostConditionRegVoucherRow;
import webbroker3.accountopen.define.WEB3AccOpenLastUpdaterDef;
import webbroker3.accountopen.message.WEB3AccOpenVoucherRegAcceptResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInformAcceptUnitService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRealUnitService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherRegAcceptService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherRegAcceptUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenRealDivDef;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3AccountOpenDateUpdateDeterminationDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3PrintFlagDef;
import webbroker3.common.define.WEB3ReportRegDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.EleDeliveryManagementDao;
import webbroker3.gentrade.data.EleDeliveryManagementParams;
import webbroker3.gentrade.data.EleDeliveryManagementRow;
import webbroker3.gentrade.data.InstitutionPreferencesDao;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����J�ݓ`�[�o�^��t�T�[�r�XImpl)<BR>
 * �����J�ݓ`�[�o�^��t�T�[�r�X�����N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AccOpenVoucherRegAcceptServiceImpl implements WEB3AccOpenVoucherRegAcceptService
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenVoucherRegAcceptServiceImpl.class);

    /**
     * @@roseuid 41B45E740000
     */
    public WEB3AccOpenVoucherRegAcceptServiceImpl()
    {

    }

    /**
     * �����J�ݓ`�[�o�^��t���������{����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�`�[�o�^��t�jexecute�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A19AD600AE
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3AccOpenVoucherRegAcceptResponse l_response = null;

        try
        {
            //1.1 getDefaultProcessor( )
            QueryProcessor l_processor = Processors.getDefaultProcessor();//DataException

            //1.2 �����J�ݓ`�[�o�^��tTransactionCallback( )
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptTransactionCallback();//DataCallbackException

            //1.3 doTransaction(int, TransactionCallback)
            Object l_objRet = l_processor.doTransaction(TransactionalInterceptor.TX_JOIN_EXISTING, l_callback);//DataException

            if (l_objRet instanceof WEB3BaseException)
            {
                WEB3BaseException l_e = (WEB3BaseException)l_objRet;
                log.debug(l_e.getErrorMessage(),l_e);
                throw l_e;
            }

            //1.4 createResponse( )
            l_response =
                (WEB3AccOpenVoucherRegAcceptResponse)l_request.createResponse();
        }
        catch (DataException l_e)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B",l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (�����J�ݓ`�[�o�^��tTransactionCallback)<BR>
     * �����J�ݓ`�[�o�^��tTransactionCallback<BR>
     */
    public class WEB3AccOpenVoucherRegAcceptTransactionCallback implements TransactionCallback
    {

        /**
         * (�����J�ݓ`�[�o�^��tTransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^<BR>
         * @@return
         * webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptServiceImpl.WEB3AccOpenVoucherRegAcceptTrans
         * actionCallback
         * @@throws DataCallbackException
         * @@roseuid 41A19B490012
         */
        public WEB3AccOpenVoucherRegAcceptTransactionCallback() throws DataCallbackException
        {

        }

        /**
         * (process)<BR>
         * �g�����U�N�V�������������{����B <BR>
         * <BR>
         * �V�[�P���X�} <BR>
         * �u�����J�݁i�`�[�o�^��t�jprocess�v�Q��<BR>
         * @@return Object
         * @@throws DataCallbackException
         * @@roseuid 41A19B110050
         */
        public Object process() throws DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            try
            {
                //1.1 get��t�L���[( )
                HostAccOpenAcceptParams[] l_acceptParams = this.getAcceptQueue();

                int l_intCount = l_acceptParams.length;
                WEB3AccOpenVoucherRegAcceptUnitService l_unitService =
                    (WEB3AccOpenVoucherRegAcceptUnitService)Services.getService(WEB3AccOpenVoucherRegAcceptUnitService.class);

                WEB3AccOpenRealUnitService l_accOpenRealUnitService =
                    (WEB3AccOpenRealUnitService)Services.getService(
                        WEB3AccOpenRealUnitService.class);
                
                //1.2 ��t�L���[�iget��t�L���[()�߂�l�j�e�v�f����LOOP����
                for (int i = 0; i < l_intCount; i++)
                {
                    // is�����q�\����t(�����J�ݓ`�[�o�^��t�L���[Params)
                    // �f�[�^�R�[�h�������q����̐\����t���ǂ����𔻒肷��B
                    boolean l_blnIsExistingAccountAccept = this.isExistingAccountAccept(l_acceptParams[i]);

                    String l_strRequestCode = null;
                    // �����J�ݓ`�[�o�^��t�L���[Params.�f�[�^�R�[�h��GI82C�̏ꍇ�AGI823�F�U�֐\���i��s�j
                    if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK_ACCEPT.equals(
                        l_acceptParams[i].getRequestCode()))
                    {
                        l_strRequestCode = WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK;
                    }
                    // �����J�ݓ`�[�o�^��t�L���[Params.�f�[�^�R�[�h��GI82H�̏ꍇ�AGI828�F�U�֐\���i�X���j
                    else if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL_ACCEPT.equals(
                        l_acceptParams[i].getRequestCode()))
                    {
                        l_strRequestCode = WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL;
                    }
                    //�����J�ݓ`�[�o�^��t�L���[Params.�f�[�^�R�[�h��GI84C�̏ꍇ�A
                    //GI843�F���E��c�d�q��t�E��������o�^
                    else if (WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REG_ACCEPT.equals(
                        l_acceptParams[i].getRequestCode()))
                    {
                        l_strRequestCode = WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST;
                    }

                    //  �����q����̐\����t�̏ꍇ
                    if (l_blnIsExistingAccountAccept)
                    {
                        VariousInformParams l_variousInformParams = this.getVariousInformParams(
                            l_acceptParams[i].getInstitutionCode(),
                            l_acceptParams[i].getBranchCode(),
                            l_acceptParams[i].getOrderRequestNumber(),
                            l_strRequestCode);

                        // �e��A��Params���擾�ł���ꍇ
                        if (l_variousInformParams != null)
                        {
                            // notify�e��A����t
                            WEB3AccOpenInformAcceptUnitService l_accOpenInformAcceptUnitService =
                                (WEB3AccOpenInformAcceptUnitService)Services.getService(
                                    WEB3AccOpenInformAcceptUnitService.class);
                            l_accOpenInformAcceptUnitService.notifyInformAccept(
                                l_acceptParams[i],
                                l_variousInformParams,
                                WEB3StatusDef.DEALT);

                            // continue
                            continue;
                        }
                    }

                    //1.2.1 notify�`�[�o�^��t(�����J�ݓ`�[�o�^��t�L���[Params)
                    String l_strStatusDiv = l_unitService.notifyVoucherRegAccept(l_acceptParams[i]);//WEB3BaseException
                 
                    //1.2.2 update�L���[(�����J�ݓ`�[�o�^��t�L���[Params, String)
                    this.updateQueue(l_acceptParams[i], l_strStatusDiv);
                    
                    //1.2.3 ���X�s�I�u�W�F�N�g���擾���� 
                    //[���X()�Ɏw�肷�����] 
                    //�،���ЃR�[�h�F�����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h 
                    //���X�R�[�h�F�����J�ݓ`�[�o�^��t�L���[.���X�R�[�h 
                    WEB3AccOpenBranch l_accOpenBranch = new WEB3AccOpenBranch(
                        l_acceptParams[i].getInstitutionCode(),
                        l_acceptParams[i].getBranchCode());
                    
                    //1.2.4���X�p�v���t�@@�����X�f�[�^���擾����B�@@ 
                    //[get���X�p�v���t�@@�����X()�ɐݒ肷�����] 
                    //���XID�F���X.get���XID �̖߂�l 
                    //�v���t�@@�����X�� �F"accopen.real.div"(�����J�݃��A���A�g���{�敪)
                    String l_strPerences = this.getBranchPreferences(l_accOpenBranch.getBranchId(),
                        WEB3BranchPreferencesNameDef.ACCOPEN_REAL_DIV);

                    //get�،���Ѓv���t�@@�����X
                    //�،���ЃR�[�h�F�����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h
                    //�v���t�@@�����X�� �F"accountopendate.update.determination"(�����J�ݓ��X�V����敪)
                    String l_strInstitutionPreferences = this.getInstitutionPreferences(
                        l_acceptParams[i].getInstitutionCode(),
                        WEB3InstitutionPreferencesNameDef.ACCOUNTOPENDATE_UPDATE_DETERMINATION);
                    //1.2.5���L�̏����ɊY������ꍇ�A�����J�݃��A���A�g���������{����B
                    //[�������{����]
                    //�@@�P�j1.2.1.1.�̖߂�l = "1" (������)�@@���A
                    //�@@�Q�j1.2.4. get���X�p�v���t�@@�����X()�̖߂�l = "1"�@@�i�������{�j�@@���A
                    //�@@�R�j�����J�ݓ`�[�o�^��t�L���[.�f�[�^�R�[�h = "GI84E" �܂��� "GI82A"
                    //  �S�j�����J�ݓ`�[�o�^��t�L���[.��t���� = "1"�i��t�����j
                    boolean l_blnDataCode = 
                        WEB3HostRequestCodeDef.ACCOPEN_ACC_REG_ACCEPT.equals(
                            l_acceptParams[i].getRequestCode()) || 
                            WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST_ACCEPT.equals(
                                l_acceptParams[i].getRequestCode());
                    
                    if (WEB3HostStatusDef.COMPLETE_PROCESS.equals(l_strStatusDiv) && 
                        WEB3AccOpenRealDivDef.EXECUTE.equals(l_strPerences) && 
                        l_blnDataCode &&
                        WEB3AcceptStatusDef.OVER.equals(l_acceptParams[i].getAcceptStatus()))
                    {
                        try
                        {
                            l_accOpenRealUnitService.process(l_acceptParams[i]);
                        }
                        catch (WEB3BaseException l_e)
                        {
                            log.debug(STR_METHOD_NAME);
                        }
                    }
                    
                    // �����o�^���X�V�������{
                    // get���X�p�v���t�@@�����X()�̖߂�l = "1"(�������{)�̏ꍇ
                    if (WEB3AccOpenRealDivDef.EXECUTE.equals(l_strPerences))
                    {
                        // update�����J�ݓo�^��
                        WEB3AccOpenRealUnitServiceImpl l_accOpenRealUnitServiceImpl =
                            new WEB3AccOpenRealUnitServiceImpl();
                        l_accOpenRealUnitServiceImpl.updateAccOpenOpenDate(l_acceptParams[i]);
                    }

                    //����t���O�ƌ����o�^���X�V�������{
                    //���L�̏����ɊY������ꍇ�A�����J�݌����q�̈���t���O���X�V����B
                    //[�������{����]
                    //�@@�P�j1.2.3.�̖߂�l = "1" (������)�@@���A
                    //�@@�Q�j�����J�ݓ`�[�o�^��t�L���[.�f�[�^�R�[�h = "GI84E" �܂��� "GI82A"�@@���A
                    //�@@�R�j�����J�ݓ`�[�o�^��t�L���[.��t���� = "1"�@@�i��t�����j
                    if (WEB3HostStatusDef.COMPLETE_PROCESS.equals(l_strStatusDiv)
                        && l_blnDataCode
                        && WEB3AcceptStatusDef.OVER.equals(l_acceptParams[i].getAcceptStatus()))
                    {
                        //update����t���O(�����J�ݓ`�[�o�^��t�L���[ : �����J�ݓ`�[�o�^��t�L���[Params)
                        //[����]
                        // �����J�ݓ`�[�o�^��t�L���[�F �����J�ݓ`�[�o�^��t�L���[Params
                        this.updatePrintFlag(l_acceptParams[i]);

                        if (WEB3AccountOpenDateUpdateDeterminationDef.EXECUTE.equals(
                            l_strInstitutionPreferences))
                        {
                            this.updateAccOpenOpenDate(l_acceptParams[i]);
                        }
                    }

                    //update�d�q��t�Ǘ�
                    //���L�̏����ɊY������ꍇ�A���������{����B
                    //[�������{����]
                    //�@@�P�j1.2.3.�̖߂�l = "1" (������)�@@���A
                    //�@@�Q�j�����J�ݓ`�[�o�^��t�L���[.�f�[�^�R�[�h = "GI84C"�@@���A
                    //�@@�R�j�����J�ݓ`�[�o�^��t�L���[.��t���� = "1"�@@�i��t�����j
                    if (WEB3HostStatusDef.COMPLETE_PROCESS.equals(l_strStatusDiv)
                        && WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REG_ACCEPT.equals(
                            l_acceptParams[i].getRequestCode())
                        && WEB3AcceptStatusDef.OVER.equals(
                            l_acceptParams[i].getAcceptStatus()))
                    {
                        //�d�q��t�Ǘ��e�[�u�����X�V����B
                        //[����]
                        //�����J�ݓ`�[�o�^��t�L���[�F �����J�ݓ`�[�o�^��t�L���[Params
                        this.updateEleDeliveryManagement(l_acceptParams[i]);
                    }
                }
            }
            catch (WEB3BaseException l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                return l_ex;
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (update�L���[)<BR>
         * �����J�ݓ`�[�o�^��t�L���[���X�V����B<BR>
         * <BR>
         * �|�i�����敪 == null�j�̏ꍇ�A�����ΏۊO�Ɣ��f���A�X�V���s�킸������<BR>
         * �I������B<BR>
         * �|�ȊO�A�����̌����J�ݓ`�[�o�^��t�L���[�Ɉȉ��̒ʂ�l��<BR>
         * �Z�b�g���čX�V����B<BR>
         * <BR>
         * �@@�@@�����J�ݓ`�[�o�^��t�L���[.�����敪 = �����敪<BR>
         * @@param l_accOpenAcceptParams - �����J�ݓ`�[�o�^��t�L���[<BR>
         * <BR>
         * ���@@�����J�ݓ`�[�o�^��t�L���[Params�N���X�́ADDL��莩����������B<BR>
         *
         * @@param l_strProcessDiv - �����敪<BR>
         * <BR>
         * 1�F ������<BR>
         * 9�F �G���[<BR>
         * @@roseuid 41A4472B02E7
         */
        protected void updateQueue(HostAccOpenAcceptParams l_accOpenAcceptParams, String l_strProcessDiv)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " updateQueue(HostAccOpenAcceptParams, String)";
            log.entering(STR_METHOD_NAME);

            //�i�����敪 == null�j�̏ꍇ�A�����ΏۊO�Ɣ��f���A�X�V���s�킸�������I������B
            if (l_strProcessDiv == null || WEB3HostStatusDef.COMPLETE_PROCESS.equals(l_strProcessDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }

            //�ȊO�A�����̌����J�ݓ`�[�o�^��t�L���[�Ɉȉ��̒ʂ�l���Z�b�g���čX�V����B
            try
            {
                l_accOpenAcceptParams.setStatus(l_strProcessDiv);
                Processors.getDefaultProcessor().doUpdateQuery(l_accOpenAcceptParams);//DataException
            }
            catch (DataException l_e)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B",l_e);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * (get��t�L���[)<BR>
         * �����J�ݓ`�[�o�^��t�L���[�f�[�^���擾����B <BR>
         * <BR>
         * �P�j�@@�L���[�e�[�u������ <BR>
         * �@@�ȉ��̏����ŁA�����J�ݓ`�[�o�^��t�L���[�e�[�u������������B <BR>
         * <BR>
         * �@@[����] <BR>
         * �@@�����J�ݓ`�[�o�^��t�L���[.�����敪 = 0:������ And <BR>
         * �@@�i�����J�ݓ`�[�o�^��t�L���[.�f�[�^�R�[�h in  <BR>
         * �@@�@@�iGI82A�F�ڋq�o�^��t�CGI82G�F�_�񏑒�����t�C<BR>
         * �@@�@@�@@GI82C�F�U�֐\���i��s�j��t�C<BR>
         * �@@�@@�@@GI82H�F�U�֐\���i�X���j��t�CGI82B�F�ېU���ӎ�t�C<BR>
         * �@@�@@�@@GI83G�F�L������t�C<BR>
         * �@@�@@�@@GI82E�FMRF������t�CGI84I�F���O���E����o�^��t�C<BR>
         * �@@�@@�@@GI84H�F�ڋq���̓o�^��t�C<BR>
         * �@@�@@�@@GI81I�F�����ғo�^��t�CGI82D�FGP�����o�^��t�C<BR>
         * �@@�@@�@@GI84E�F�ڋq�o�^�i����Ɓj��t�j�C<BR>
         * �@@�@@�@@GI85D�F�O�ݗa�������o�^��t,<BR>
         * �@@�@@�@@GI84C�F���E��c�d�q��t�E��������o�^��t�C<BR>
         * �@@�@@�@@GI86E�F�@@�\�ʒm���o�^��t�j<BR>
         * <BR>
         * �@@���@@�������̊e�`�[��t�f�[�^<BR>
         * <BR>
         * �Q�j�@@�������ʂ�z��ɂ��ĕԋp����B<BR>
         * �@@�@@
         * @@return webbroker3.accountopen.data.HostAccOpenAcceptParams[]
         * @@roseuid 41A4483200E3
         */
        protected HostAccOpenAcceptParams[] getAcceptQueue()
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " getAcceptQueue()";
            log.entering(STR_METHOD_NAME);

            List l_lisRecords = null;

            try
            {
                //execute query
                String l_strWhere = "status = ? and request_code in (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                Object[] l_objs = new Object[]{
                    WEB3HostStatusDef.NOT_STARTED_PROCESS,
                    WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_CONTRACT_COLLECT_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_AGREE_TRANSFER_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_CHARGED_INFO_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_MRF_ACCOUNT_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_INSIDER_REG_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_GP_REG_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_STOCKHOLDER_REG_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_REALNAME_REG_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_ACC_REG_ACCEPT,
                    WEB3HostRequestCodeDef.F_DEPOSIT_REG_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REG_ACCEPT,
                    WEB3HostRequestCodeDef.ACCOPEN_AGENCY_INFO_REG_ACCEPT};
                    l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                    HostAccOpenAcceptRow.TYPE,
                    l_strWhere,
                    null, 
                    null,
                    l_objs);//DataException
            }
            catch (DataException l_e)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B",l_e);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }

            if (l_lisRecords.isEmpty())
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
            //to array
            HostAccOpenAcceptParams[] l_params = new HostAccOpenAcceptParams[l_lisRecords.size()];
            l_lisRecords.toArray(l_params);

            log.exiting(STR_METHOD_NAME);
            return l_params;
        }
        
        /**
         * (get���X�p�v���t�@@�����X)<BR>
         * ���X�p�v���t�@@�����X�f�[�^���擾����B<BR>
         * <BR>
         * �P�j ���X�p�v���t�@@�����X�e�[�u�������L�̏����Ō�������B<BR>
         *   [����] <BR>
         *   ���X�p�v���t�@@�����X.���XID = ����.���XID<BR>
         *   ���X�p�v���t�@@�����X.�v���t�@@�����X�� = ����.�v���t�@@�����X��<BR>
         *   ���X�p�v���t�@@�����X.�v���t�@@�����X���̘A�� = 1 <BR>
         * <BR>
         * �Q�j �߂�l <BR>
         *   �P�j�̏������A�������ʂ�0���̏ꍇ�A�߂�l�Ƃ��āu0�v��Ԃ��B<BR>
         *   �������ʂ�1���̏ꍇ�A�߂�l�Ƃ��āA�v���t�@@�����X�̒l ��Ԃ��B<BR>
         * �@@�@@
         * @@return String
         * @@roseuid 41A4483200E3
         */  
        protected String getBranchPreferences(String l_strBranchId, String l_strPreferencesname)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getBranchPreferences()";
            log.entering(STR_METHOD_NAME);
            
            String l_strBranchPreferences = null;
            try
            {
                //�P�j ���X�p�v���t�@@�����X�e�[�u�������L�̏����Ō�������B 
                //[����] 
                //���X�p�v���t�@@�����X.���XID = ����.���XID 
                //���X�p�v���t�@@�����X.�v���t�@@�����X�� = ����.�v���t�@@�����X��
                //���X�p�v���t�@@�����X.�v���t�@@�����X���̘A�� = 1
                if (WEB3StringTypeUtility.isEmpty(l_strBranchId))
                {
                    log.debug("�p�����[�^�l�s���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                
                log.debug("����.���XID" + Long.parseLong(l_strBranchId) + 
                    "����.�v���t�@@�����X��" +l_strPreferencesname + 
                    "���X�p�v���t�@@�����X.�v���t�@@�����X���̘A��" + 1);
                BranchPreferencesRow l_row = 
                    (BranchPreferencesRow)BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                        Long.parseLong(l_strBranchId), l_strPreferencesname, 1);
                
                //�Q�j �߂�l 
                //�P�j�̏������A�������ʂ�0���̏ꍇ�A�߂�l�Ƃ��āu0�v��Ԃ��B 
                //�������ʂ�1���̏ꍇ�A�߂�l�Ƃ��āA�v���t�@@�����X�̒l ��Ԃ��B
                if (l_row == null)
                {
                    l_strBranchPreferences = "0";
                } 
                else if (l_row != null)
                {
                    l_strBranchPreferences = l_row.getValue();
                }
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }            
            log.exiting(STR_METHOD_NAME);
            return l_strBranchPreferences;   
        }    

        /**
         * (get�e��A���e�[�u��)<BR>
         * �e��A���e�[�u���f�[�^���擾����B<BR>
         * <BR>
         * �P�j�@@�e��A���e�[�u��������<BR>
         * <BR>
         * �@@�m�����n<BR>
         * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h AND<BR>
         * �@@���X�R�[�h = ����.���X�R�[�h AND<BR>
         * �@@�`�[���ʃR�[�h = ����.�`�[���ʃR�[�h AND<BR>
         * �@@�f�[�^�R�[�h = ����.�f�[�^�R�[�h<BR>
         * <BR>
         * �Q�j�@@��������(List�̐擪�v�f)��ԋp<BR>
         * ���������ʂ�0���̏ꍇ�Anull��Ԃ��B<BR>
         * <BR>
         * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h
         * @@param l_strBranchCode - (���X�R�[�h)<BR>
         * ���X�R�[�h
         * @@param l_strOrderRequestNumber - (�`�[���ʃR�[�h)<BR>
         * �`�[���ʃR�[�h
         * @@param l_strRequestCode - (�f�[�^�R�[�h)<BR>
         * �f�[�^�R�[�h
         * @@return VariousInformParams
         * @@throws WEB3BaseException
         */
        private VariousInformParams getVariousInformParams(
            String l_strInstitutionCode,
            String l_strBranchCode,
            String l_strOrderRequestNumber,
            String l_strRequestCode) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " getVariousInformParams(String, String, String, String)";
            log.entering(STR_METHOD_NAME);

            List l_lisRecord = null;
            try
            {
                // �e��A���e�[�u��������
                // �،���ЃR�[�h = ����.�،���ЃR�[�h AND
                // ���X�R�[�h = ����.���X�R�[�h AND
                // �`�[���ʃR�[�h = ����.�`�[���ʃR�[�h AND
                // �f�[�^�R�[�h = ����.�f�[�^�R�[�h
                StringBuffer l_sbQueryString = new StringBuffer();
                l_sbQueryString.append("institution_code = ? and ");
                l_sbQueryString.append(" branch_code = ? and ");
                l_sbQueryString.append(" order_request_number = ? and ");
                l_sbQueryString.append(" request_code = ?");

                Object[] l_queryDataContainers = new Object[]{
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strOrderRequestNumber,
                    l_strRequestCode};

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                l_lisRecord = l_queryProcessor.doFindAllQuery(
                    VariousInformRow.TYPE,
                    l_sbQueryString.toString(),
                    l_queryDataContainers);
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

            // �Q�j�@@��������(List�̐擪�v�f)��ԋp
            // ���������ʂ�0���̏ꍇ�Anull��Ԃ��B
            if (l_lisRecord.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            VariousInformRow l_variousInformRow = (VariousInformRow)l_lisRecord.get(0);
            VariousInformParams l_variousInformParams =
                new VariousInformParams(l_variousInformRow);

            log.exiting(STR_METHOD_NAME);
            return l_variousInformParams;
        }

        /**
         * (is�����q�\����t)<BR>
         * �f�[�^�R�[�h�������q����̐\����t���ǂ����𔻒肷��B<BR>
         * <BR>
         * �����q����̐\���̏ꍇ��true�A����ȊO��false <BR>
         * <BR>
         * �P�j�����q�\������<BR>
         * <BR>
         * �@@����.��t�L���[Params.�f�[�^�R�[�h ��<BR>
         * �@@GI82C�F�U�֐\���i��s�j��t ���́A<BR>
         * �@@GI82H�F�U�֐\���i�X���j��t ���́A<BR>
         * �@@GI84C�F���E��c�d�q��t�E��������o�^��t <BR>
         * �@@�̏ꍇtrue��ԋp����B<BR>
         * <BR>
         * �Q�jfalse��ԋp����B<BR>
         * <BR>
         * @@param l_hostAccOpenAcceptParams - (��t�L���[Params)<BR>
         * �����J�ݓ`�[�o�^��t�L���[Params<BR>
         * <BR>
         * ���@@�����J�ݓ`�[�o�^��t�L���[Params�N���X�́ADDL��莩����������B
         * @@return boolean
         */
        private boolean isExistingAccountAccept(HostAccOpenAcceptParams l_hostAccOpenAcceptParams)
        {
            final String STR_METHOD_NAME = " isExistingAccountAccept(HostAccOpenAcceptParams)";
            log.entering(STR_METHOD_NAME);

            if (l_hostAccOpenAcceptParams == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            // ����.��t�L���[Params.�f�[�^�R�[�h
            String l_strRequestCode = l_hostAccOpenAcceptParams.getRequestCode();
            // ����.��t�L���[Params.�f�[�^�R�[�h ��
            // GI82C�F�U�֐\���i��s�j��t ���́A
            // GI82H�F�U�֐\���i�X���j��t ���́A
            // GI84C�F���E��c�d�q��t�E��������o�^��t�̏ꍇtrue��ԋp����B
            if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK_ACCEPT.equals(l_strRequestCode)
                || WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL_ACCEPT.equals(l_strRequestCode)
                || WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REG_ACCEPT.equals(l_strRequestCode))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }

            // �Q�jfalse��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        /**
         * (update����t���O)<BR>
         * �����J�݌����q�e�[�u���̈���t���O���X�V����B<BR>
         * <BR>
         * �P�j�@@�o�^�L���[�f�[�^�擾<BR>
         * �@@�����J�ݓ`�[�o�^��t�L���[.�f�[�^�R�[�h�ɑΉ�����o�^�L���[���ȉ��̏����Ō�������B<BR>
         * <BR>
         * �@@[��������]<BR>
         * �@@�o�^�L���[�e�[�u��(*1).���ʃR�[�h = ��t�L���[.���ʃR�[�h And�@@���o�^�L���[.���ʃR�[�h�́A<BR>
         * �@@�@@�`�[�̎��ʃR�[�h�iorder_request_number�j<BR>
         * �@@�o�^�L���[�e�[�u��(*1).�f�[�^�R�[�h = ��t�L���[.�f�[�^�R�[�h And<BR>
         * �@@�o�^�L���[�e�[�u��(*1).�،���ЃR�[�h = ��t�L���[.�،���ЃR�[�h And<BR>
         * �@@�o�^�L���[�e�[�u��(*1).���X�R�[�h = ��t�L���[.���X�R�[�h And<BR>
         * �@@�o�^�L���[�e�[�u��(*1).�ڋq�R�[�h = ��t�L���[.�ڋq�R�[�h<BR>
         * <BR>
         * �@@(*1) [�f�[�^�R�[�h�ɑΉ�����o�^�L���[�e�[�u����]<BR>
         * �@@GI82A�F�ڋq�o�^��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u��<BR>
         * �@@GI84E�F�ڋq�o�^�i����Ɓj��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u��<BR>
         * <BR>
         * �@@�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B<BR>
         * <BR>
         * �Q�j�@@�����J�݌����q�f�[�^�̎擾<BR>
         * <BR>
         * �@@�����J�݌����q�e�[�u�����ȉ��̏����Ō�������B<BR>
         * �@@<BR>
         * �@@[��������]<BR>
         * �@@�،���ЃR�[�h = �����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h<BR>
         * �@@���ʃR�[�h = �P�j�Ŏ擾�����o�^�L���[.���ʃR�[�h�i�����J�݌����q�j<BR>
         * <BR>
         * �@@�擾�ł��Ȃ������ꍇ�A�㑱�����͍s��Ȃ��B<BR>
         * <BR>
         * �R�j�@@����t���O�̐ݒ�<BR>
         * �@@�Q�j�Ō������ꂽ���R�[�h�Ɉȉ��̒l��ݒ肷��B<BR>
         * <BR>
         * �@@[�ݒ�l]<BR>
         * �@@����t���O�F"0�F�����"<BR>
         * �@@�X�V�����F��������<BR>
         * <BR>
         * �S�j�@@�X�V����<BR>
         * �@@�R�j�̐ݒ�l�Ō����J�݌����q�e�[�u���̍X�V�������s���B<BR>
         * <BR>
         * �@@�X�V���e�́ADB�X�V�d�l�u�����J�݌����qDB�X�V�i����t���O�j�d�l.xls�v �Q�ƁB<BR>
         * <BR>
         * @@param l_hostAccOpenAcceptParams - (�����J�ݓ`�[�o�^��t�L���[)<BR>
         * �����J�ݓ`�[�o�^��t�L���[<BR>
         * @@throws WEB3BaseException
         */
        protected void updatePrintFlag(HostAccOpenAcceptParams l_hostAccOpenAcceptParams)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "updatePrintFlag(HostAccOpenAcceptParams)";
            log.entering(STR_METHOD_NAME);

            ExpAccountOpenRow l_expAccountOpenRow = null;
            try
            {
                //�P�j�@@�o�^�L���[�f�[�^�擾
                //�����J�ݓ`�[�o�^��t�L���[.�f�[�^�R�[�h�ɑΉ�����o�^�L���[���ȉ��̏����Ō�������B
                //[��������]
                //�o�^�L���[�e�[�u��(*1).���ʃR�[�h = ��t�L���[.���ʃR�[�h And�@@���o�^�L���[.���ʃR�[�h�́A
                //�`�[�̎��ʃR�[�h�iorder_request_number�j
                //�o�^�L���[�e�[�u��(*1).�f�[�^�R�[�h = ��t�L���[.�f�[�^�R�[�h And
                //�o�^�L���[�e�[�u��(*1).�،���ЃR�[�h = ��t�L���[.�،���ЃR�[�h And
                //�o�^�L���[�e�[�u��(*1).���X�R�[�h = ��t�L���[.���X�R�[�h And
                //�o�^�L���[�e�[�u��(*1).�ڋq�R�[�h = ��t�L���[.�ڋq�R�[�h
                String l_strOrderRequestNumber = l_hostAccOpenAcceptParams.getOrderRequestNumber();
                String l_strRequestCode = l_hostAccOpenAcceptParams.getRequestCode();
                String l_strInstitutionCode = l_hostAccOpenAcceptParams.getInstitutionCode();
                String l_strBranchCode = l_hostAccOpenAcceptParams.getBranchCode();
                String l_strAccountCode = l_hostAccOpenAcceptParams.getAccountCode();
                String l_strAccOpenRequestNumber = null;

                String[] l_acceptReqCodes =
                    new String[]{"GI82A", "GI84E"};
                String[] l_registReqCodes =
                    new String[]{"GI821", "GI845"};

                String l_strRegistReqCode = null;
                for (int i = 0; i < l_acceptReqCodes.length; i++)
                {
                    if (l_acceptReqCodes[i].equals(l_strRequestCode))
                    {
                        l_strRegistReqCode = l_registReqCodes[i];
                        break;
                    }
                }
                //(*1) [�f�[�^�R�[�h�ɑΉ�����o�^�L���[�e�[�u����]
                //GI82A�F�ڋq�o�^��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u��
                //GI84E�F�ڋq�o�^�i����Ɓj��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u��
                //�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                // GI82A�F�ڋq�o�^��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u��
                if (WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST_ACCEPT.equals(l_strRequestCode))
                {
                    HostAccRegVoucherPK l_pk = new HostAccRegVoucherPK(
                        l_strOrderRequestNumber,
                        l_strRegistReqCode,
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
                    HostAccRegVoucherRow l_row =
                        (HostAccRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                    l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
                }
                // GI84E�F�ڋq�o�^�i����Ɓj��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u��
                else if (WEB3HostRequestCodeDef.ACCOPEN_ACC_REG_ACCEPT.equals(l_strRequestCode))
                {
                    HostAccRegVoucherPK l_pk = new HostAccRegVoucherPK(
                        l_strOrderRequestNumber,
                        l_strRegistReqCode,
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
                    HostAccRegVoucherRow l_row =
                        (HostAccRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                    l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
                }
                else
                {
                    log.debug("�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    return;
                }

                //�Q�j�@@�����J�݌����q�f�[�^�̎擾
                //�����J�݌����q�e�[�u�����ȉ��̏����Ō�������
                //�@@[��������]
                //�@@�،���ЃR�[�h = �����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h
                //�@@���ʃR�[�h = �P�j�Ŏ擾�����o�^�L���[.���ʃR�[�h�i�����J�݌����q�j
                l_expAccountOpenRow = ExpAccountOpenDao.findRowByPk(
                    new ExpAccountOpenPK(
                        l_hostAccOpenAcceptParams.getInstitutionCode(),
                        l_strAccOpenRequestNumber));

                //�R�j�@@����t���O�̐ݒ�
                ExpAccountOpenParams l_expAccountOpenParams =
                    new ExpAccountOpenParams(l_expAccountOpenRow);

                //�Q�j�Ō������ꂽ���R�[�h�Ɉȉ��̒l��ݒ肷��B
                //[�ݒ�l]
                //����t���O�F"0�F�����"
                l_expAccountOpenParams.setPrintFlag(WEB3PrintFlagDef.ENABLE_PRINT);

                //�X�V�����F��������
                l_expAccountOpenParams.setLastUpdatedTimestamp(
                    GtlUtils.getSystemTimestamp());

                //�S�j�@@�X�V����
                //�R�j�̐ݒ�l�Ō����J�݌����q�e�[�u���̍X�V�������s��
                //�X�V���e�́ADB�X�V�d�l�u�����J�݌����qDB�X�V�i����t���O�j�d�l.xls�v �Q�ƁB
                WEB3DataAccessUtility.updateRow(l_expAccountOpenParams);
            }
            catch (DataFindException l_ex)
            {
                log.debug("�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                return;
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

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * (get�،���Ѓv���t�@@�����X)<BR>
         * �،���Ѓv���t�@@�����X�f�[�^���擾����B<BR>
         * <BR>
         * �P�j����.�،���ЃR�[�h�ɊY������،����ID���擾����B<BR>
         * <BR>
         * �Q�j�ȉ��̏����ŁA�،���Ѓv���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR>
         * �@@�@@[����]<BR>
         * �@@�@@�،����ID = �P�j�Ŏ擾�����،����ID<BR>
         * �@@�@@�v���t�@@�����X�� = ����.�v���t�@@�����X��<BR>
         * �@@�@@�v���t�@@�����X���̘A�� = "1"<BR>
         * <BR>
         * �R�j �߂�l<BR>
         * �@@�Q�j�̏������A�������ʂ�0���̏ꍇ�A�߂�l�Ƃ��āu0�v��Ԃ��B<BR>
         * �@@�������ʂ�1���̏ꍇ�A�߂�l�Ƃ��āA�v���t�@@�����X�̒l ��Ԃ��B<BR>
         * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h<BR>
         * @@param l_strInstitutionPreferencesName - (�v���t�@@�����X��)<BR>
         * �v���t�@@�����X��<BR>
         * @@throws WEB3BaseException
         */
        protected String getInstitutionPreferences(
            String l_strInstitutionCode, 
            String l_strInstitutionPreferencesName) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getInstitutionPreferences(String, String)";
            log.entering(STR_METHOD_NAME);

            String l_strInstitutionPreferences = null;
            try
            {
                //�P�j����.�،���ЃR�[�h�ɊY������،����ID���擾����B
                AccountManager l_accountManager = GtlUtils.getAccountManager();

                Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
                long l_lngInstitutionId = l_institution.getInstitutionId();

                //�Q�j�ȉ��̏����ŁA�،���Ѓv���t�@@�����X�e�[�u�����烌�R�[�h���擾����B
                InstitutionPreferencesRow l_row = 
                    (InstitutionPreferencesRow)InstitutionPreferencesDao.findRowByInstitutionIdNameNameSerialNo(
                        l_lngInstitutionId, l_strInstitutionPreferencesName, 1);

                //�R�j �߂�l
                //�Q�j�̏������A�������ʂ�0���̏ꍇ�A�߂�l�Ƃ��āu0�v��Ԃ��B
                if (l_row == null)
                {
                    l_strInstitutionPreferences = "0";
                } 
                else
                {
                    //�������ʂ�1���̏ꍇ�A�߂�l�Ƃ��āA�v���t�@@�����X�̒l ��Ԃ��B
                    l_strInstitutionPreferences = l_row.getValue();
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            log.exiting(STR_METHOD_NAME);
            return l_strInstitutionPreferences;
        }

        /**
         * (update�����J�ݓo�^��)<BR>
         * �����J�݌����q�e�[�u���̌����o�^�����X�V����B<BR>
         * <BR>
         * �P�j�@@�o�^�L���[�f�[�^�擾<BR>
         * �@@�����J�ݓ`�[�o�^��t�L���[.�f�[�^�R�[�h�ɑΉ�����o�^�L���[���ȉ��̏����Ō�������B<BR>
         * <BR>
         * �@@[��������]<BR>
         * �@@�o�^�L���[�e�[�u��(*1).���ʃR�[�h = ��t�L���[.���ʃR�[�h And<BR>
         * �@@�@@���o�^�L���[.���ʃR�[�h�́A�`�[�̎��ʃR�[�h�iorder_request_number�j<BR>
         * �@@�o�^�L���[�e�[�u��(*1).�f�[�^�R�[�h = ��t�L���[.�f�[�^�R�[�h And<BR>
         * �@@�o�^�L���[�e�[�u��(*1).�،���ЃR�[�h = ��t�L���[.�،���ЃR�[�h And<BR>
         * �@@�o�^�L���[�e�[�u��(*1).���X�R�[�h = ��t�L���[.���X�R�[�h And<BR>
         * �@@�o�^�L���[�e�[�u��(*1).�ڋq�R�[�h = ��t�L���[.�ڋq�R�[�h<BR>
         * <BR>
         * �@@(*1) [�f�[�^�R�[�h�ɑΉ�����o�^�L���[�e�[�u����]<BR>
         * �@@GI82A�F�ڋq�o�^��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u��<BR>
         * �@@GI84E�F�ڋq�o�^�i����Ɓj��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u��<BR>
         * <BR>
         * �@@�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B<BR>
         * <BR>
         * �Q�j�@@�����J�݌����q�f�[�^�̎擾<BR>
         * <BR>
         * �@@�����J�݌����q�e�[�u�����ȉ��̏����Ō�������B�@@<BR>
         * �@@<BR>
         * �@@[��������]<BR>
         * �@@�،���ЃR�[�h = �����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h<BR>
         * �@@���ʃR�[�h = �P�j�Ŏ擾�����o�^�L���[.���ʃR�[�h�i�����J�݌����q�j<BR>
         * <BR>
         * �@@�擾�ł��Ȃ������ꍇ�A�㑱�����͍s��Ȃ��B<BR>
         * <BR>
         * �R�j�@@�����o�^���̐ݒ�<BR>
         * �@@�Q�j�Ō������ꂽ���R�[�h�Ɉȉ��̒l��ݒ肷��B<BR>
         * <BR>
         * �@@[�ݒ�l]<BR>
         * �@@�����o�^���F���������iYYYY/MM/DD 00:00:00�j<BR>
         * �@@�X�V�����F��������<BR>
         * <BR>
         * �S�j�@@�X�V����<BR>
         * �@@�R�j�̐ݒ�l�Ō����J�݌����q�e�[�u���̍X�V�������s���B<BR>
         * <BR>
         * �@@�X�V���e�́ADB�X�V�d�l�u�����J�݌����qDB�X�V�i�����J�ݓ��j�d�l.xls�v �Q�ƁB<BR>
         * <BR>
         * @@param l_hostAccOpenAcceptParams - (�����J�ݓ`�[�o�^��t�L���[)<BR>
         * �����J�ݓ`�[�o�^��t�L���[<BR>
         * �����J�݌����q�e�[�u���̌����o�^�����X�V����B<BR>
         * <BR>
         * [����]<BR> 
         * �����J�ݓ`�[�o�^��t�L���[�F �����J�ݓ`�[�o�^��t�L���[Params<BR>
         * @@throws WEB3BaseException
         */
        protected void updateAccOpenOpenDate(
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "updateAccOpenOpenDate(HostAccOpenAcceptParams)";
            log.entering(STR_METHOD_NAME);

            //�P�j�@@�o�^�L���[�f�[�^�擾
            //�����J�ݓ`�[�o�^��t�L���[.�f�[�^�R�[�h�ɑΉ�����o�^�L���[���ȉ��̏����Ō�������B
            //�o�^�L���[�e�[�u��(*1).���ʃR�[�h = ��t�L���[.���ʃR�[�h And
            //���o�^�L���[.���ʃR�[�h�́A�`�[�̎��ʃR�[�h�iorder_request_number�j
            //�o�^�L���[�e�[�u��(*1).�f�[�^�R�[�h = ��t�L���[.�f�[�^�R�[�h And
            //�o�^�L���[�e�[�u��(*1).�،���ЃR�[�h = ��t�L���[.�،���ЃR�[�h And
            //�o�^�L���[�e�[�u��(*1).���X�R�[�h = ��t�L���[.���X�R�[�h And
            //�o�^�L���[�e�[�u��(*1).�ڋq�R�[�h = ��t�L���[.�ڋq�R�[�h
            try
            {
                String l_strRequestCode = l_hostAccOpenAcceptParams.getRequestCode();

                String[] l_acceptReqCodes =
                    new String[]{"GI82A", "GI84E"};
                String[] l_registReqCodes =
                    new String[]{"GI821", "GI845"};

                String l_strRegistReqCode = null;
                for (int i = 0; i < l_acceptReqCodes.length; i++)
                {
                    if (l_acceptReqCodes[i].equals(l_strRequestCode))
                    {
                        l_strRegistReqCode = l_registReqCodes[i];
                        break;
                    }
                }
                HostAccRegVoucherRow l_hostAccRegVoucherRow = HostAccRegVoucherDao.findRowByPk(
                    l_hostAccOpenAcceptParams.getOrderRequestNumber(),
                    l_strRegistReqCode,
                    l_hostAccOpenAcceptParams.getInstitutionCode(),
                    l_hostAccOpenAcceptParams.getBranchCode(),
                    l_hostAccOpenAcceptParams.getAccountCode());

                //�Q�j�@@�����J�݌����q�f�[�^�̎擾
                //�����J�݌����q�e�[�u�����ȉ��̏����Ō�������B
                //�،���ЃR�[�h = �����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h
                //���ʃR�[�h = �P�j�Ŏ擾�����o�^�L���[.���ʃR�[�h�i�����J�݌����q�j
                ExpAccountOpenRow l_expAccountOpenRow = ExpAccountOpenDao.findRowByPk(
                    l_hostAccOpenAcceptParams.getInstitutionCode(),
                    l_hostAccRegVoucherRow.getAccOpenRequestNumber());

                //�R�j�@@�����o�^���̐ݒ�
                //�Q�j�Ō������ꂽ���R�[�h�Ɉȉ��̒l��ݒ肷��B
                //[�ݒ�l]
                ExpAccountOpenParams l_expAccountOpenParams = 
                    new ExpAccountOpenParams(l_expAccountOpenRow);
                //�����o�^���F���������iYYYY/MM/DD 00:00:00�j
                l_expAccountOpenParams.setAccountOpenDate(
                    WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
                //�X�V�����F��������
                l_expAccountOpenParams.setLastUpdatedTimestamp(
                    GtlUtils.getSystemTimestamp());

                //�S�j�@@�X�V����
                //�R�j�̐ݒ�l�Ō����J�݌����q�e�[�u���̍X�V�������s���B
                //�X�V���e�́ADB�X�V�d�l�u�����J�݌����qDB�X�V�i�����J�ݓ��j�d�l.xls�v �Q�ƁB
                log.exiting(STR_METHOD_NAME);
                Processors.getDefaultProcessor().doUpdateQuery(l_expAccountOpenParams);
            }
            catch (DataFindException l_ex)
            {
                //�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B
                log.exiting(STR_METHOD_NAME);
                return;
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
        }


        /**
         * (update�d�q��t�Ǘ�)<BR>
         * �d�q��t�Ǘ��e�[�u�����X�V����B<BR>
         * <BR>
         * �P�j�@@�o�^�L���[�f�[�^�擾<BR>
         * �@@�����J�ݓ`�[�o�^��t�L���[.�f�[�^�R�[�h�ɑΉ�����o�^�L���[���ȉ��̏����Ō�������B<BR>
         * <BR>
         * �@@[��������]<BR>
         * �@@�o�^�L���[�e�[�u��(*1).���ʃR�[�h = ��t�L���[.���ʃR�[�h And<BR>
         * �@@�@@���o�^�L���[.���ʃR�[�h�́A�`�[�̎��ʃR�[�h�iorder_request_number�j<BR>
         * �@@�o�^�L���[�e�[�u��(*1).�f�[�^�R�[�h = ��t�L���[.�f�[�^�R�[�h And<BR>
         * �@@�o�^�L���[�e�[�u��(*1).�،���ЃR�[�h = ��t�L���[.�،���ЃR�[�h And<BR>
         * �@@�o�^�L���[�e�[�u��(*1).���X�R�[�h = ��t�L���[.���X�R�[�h And<BR>
         * �@@�o�^�L���[�e�[�u��(*1).�ڋq�R�[�h = ��t�L���[.�ڋq�R�[�h<BR>
         * <BR>
         * �@@(*1) [�f�[�^�R�[�h�ɑΉ�����o�^�L���[�e�[�u����]<BR>
         * �@@GI84C�F���E��c�d�q��t�E��������o�^��t�@@���@@���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u��<BR>
         * <BR>
         * �@@�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B<BR>
         * <BR>
         * �Q�j�@@�d�q��t�Ǘ��e�[�u���f�[�^�̎擾<BR>
         * �@@�����J�ݓ`�[�o�^��t�L���[.�f�[�^�R�[�h�ɑΉ�����o�^�L���[���ȉ��̏����Ō�������B<BR>
         * <BR>
         * �@@[��������]<BR>
         * �@@�d�q��t�Ǘ��e�[�u��.�،���ЃR�[�h = ��t�L���[.�،���ЃR�[�h And<BR>
         * �@@�d�q��t�Ǘ��e�[�u��.���X�R�[�h = ��t�L���[.���X�R�[�h And<BR>
         * �@@�d�q��t�Ǘ��e�[�u��.�ڋq�R�[�h = ��t�L���[.�ڋq�R�[�h<BR>
         * <BR>
         * �@@�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B<BR>
         * <BR>
         * �R�j�@@�d�q��t�Ǘ��e�[�u���̍X�V����<BR>
         * <BR>
         * �@@�Q�j�Ō������ꂽ���R�[�h�̍X�V�������s���B<BR>
         * <BR>
         * �@@�X�V���e�́ADB�X�V�d�l�u�d�q��t�Ǘ��e�[�u���c�a�X�V�d�l.xls�v �Q�ƁB<BR>
         * �@@���P�j�Ō������ꂽ���R�[�h�ɂ��X�V�������s���B<BR>
         * <BR>
         * @@param l_hostAccOpenAcceptParams - (�����J�ݓ`�[�o�^��t�L���[)<BR>
         * �����J�ݓ`�[�o�^��t�L���[<BR>
         * @@throws WEB3BaseException
         */
        protected void updateEleDeliveryManagement(
                HostAccOpenAcceptParams l_hostAccOpenAcceptParams) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "updateEleDeliveryManagement(HostAccOpenAcceptParams)";
            log.entering(STR_METHOD_NAME);

            try
            {
                //�P�j�@@�o�^�L���[�f�[�^�擾
                //�@@�����J�ݓ`�[�o�^��t�L���[.�f�[�^�R�[�h�ɑΉ�����o�^�L���[���ȉ��̏����Ō�������B
                //�@@[��������]
                //�@@�o�^�L���[�e�[�u��(*1).���ʃR�[�h = ��t�L���[.���ʃR�[�h And
                //�@@�@@���o�^�L���[.���ʃR�[�h�́A�`�[�̎��ʃR�[�h�iorder_request_number�j
                //�@@�o�^�L���[�e�[�u��(*1).�f�[�^�R�[�h = ��t�L���[.�f�[�^�R�[�h And
                //�@@�o�^�L���[�e�[�u��(*1).�،���ЃR�[�h = ��t�L���[.�،���ЃR�[�h And
                //�@@�o�^�L���[�e�[�u��(*1).���X�R�[�h = ��t�L���[.���X�R�[�h And
                //�@@�o�^�L���[�e�[�u��(*1).�ڋq�R�[�h = ��t�L���[.�ڋq�R�[�h
                String l_strOrderRequestNumber = l_hostAccOpenAcceptParams.getOrderRequestNumber();
                String l_strRequestCode = l_hostAccOpenAcceptParams.getRequestCode();
                String l_strInstitutionCode = l_hostAccOpenAcceptParams.getInstitutionCode();
                String l_strBranchCode = l_hostAccOpenAcceptParams.getBranchCode();
                String l_strAccountCode = l_hostAccOpenAcceptParams.getAccountCode();

                String l_strRegistReqCode = null;

                if (WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REG_ACCEPT.equals(l_strRequestCode))
                {
                    l_strRegistReqCode = WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST;
                }

                //�@@(*1) [�f�[�^�R�[�h�ɑΉ�����o�^�L���[�e�[�u����]
                //�@@GI84C�F���E��c�d�q��t�E��������o�^��t�@@���@@���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u��
                //�@@�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B
                HostConditionRegVoucherRow l_hostConditionRegVoucherRow =
                    HostConditionRegVoucherDao.findRowByPk(
                        l_strOrderRequestNumber,
                        l_strRegistReqCode,
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);

                //�Q�j�@@�d�q��t�Ǘ��e�[�u���f�[�^�̎擾
                //�@@�����J�ݓ`�[�o�^��t�L���[.�f�[�^�R�[�h�ɑΉ�����o�^�L���[���ȉ��̏����Ō�������B
                //�@@[��������]
                //�@@�d�q��t�Ǘ��e�[�u��.�،���ЃR�[�h = ��t�L���[.�،���ЃR�[�h And
                //�@@�d�q��t�Ǘ��e�[�u��.���X�R�[�h = ��t�L���[.���X�R�[�h And
                //�@@�d�q��t�Ǘ��e�[�u��.�ڋq�R�[�h = ��t�L���[.�ڋq�R�[�h
                EleDeliveryManagementRow l_eleDeliveryManagementRow = null;

                l_eleDeliveryManagementRow =
                    EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);

                if (l_eleDeliveryManagementRow == null)
                {
                    //�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B
                    log.debug("�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    return;
                }

                //�R�j�@@�d�q��t�Ǘ��e�[�u���̍X�V����
                //�@@�Q�j�Ō������ꂽ���R�[�h�̍X�V�������s��
                //�@@�X�V���e�́ADB�X�V�d�l�u�d�q��t�Ǘ��e�[�u���c�a�X�V�d�l.xls�v �Q�ƁB
                //�@@���P�j�Ō������ꂽ���R�[�h�ɂ��X�V�������s���B
                EleDeliveryManagementParams l_eleDeliveryManagementParams =
                    new EleDeliveryManagementParams(l_eleDeliveryManagementRow);

                //�d�q��t�@@����񍐏�
                String l_strTradingEReportDiv =
                    l_hostConditionRegVoucherRow.getTradingEReportDiv();

                //�o�^�iGI311�j�L���[.�d�q��t�@@����񍐏� !=null
                if (l_strTradingEReportDiv != null)
                {
                    //����񍐏��\���敪
                    //9:SONAR���M��
                    l_eleDeliveryManagementParams.setTradingReportRegDiv(
                        WEB3ReportRegDivDef.SONAR_MAIL_SENDED);

                    //����񍐏���t�敪�X�V��
                    //��������
                    l_eleDeliveryManagementParams.setTradingReportDivUpdDate(
                        GtlUtils.getSystemTimestamp());
                }

                //����c���񍐏��@@�d�q��t�i�s�x�j
                String l_strPosReportCycleDiv =
                    l_hostConditionRegVoucherRow.getPosReportCycleDiv();

                //�o�^�iGI311�j�L���[.����c���񍐏��@@�d�q��t�i�s�x�j !=null
                if (l_strPosReportCycleDiv != null)
                {
                    //����c���񍐏��\���敪
                    //9:SONAR���M��
                    l_eleDeliveryManagementParams.setPositionReportRegDiv(
                        WEB3ReportRegDivDef.SONAR_MAIL_SENDED);

                    //����c���񍐏���t�敪�X�V��
                    //��������
                    l_eleDeliveryManagementParams.setPositionReportDivUpdDate(
                        GtlUtils.getSystemTimestamp());
                }

                //�d�q��t�@@���M�^�p�񍐏�
                String l_strInvEReportDiv =
                    l_hostConditionRegVoucherRow.getInvEReportDiv();

                //�o�^�iGI311�j�L���[.�d�q��t�@@���M�^�p�񍐏� !=null
                if (l_strInvEReportDiv != null)
                {
                    //�^�p�񍐏��\���敪
                    //9:SONAR���M��
                    l_eleDeliveryManagementParams.setOpeReportRegDiv(
                        WEB3ReportRegDivDef.SONAR_MAIL_SENDED);

                    //�^�p�񍐏���t�敪�X�V��
                    //��������
                    l_eleDeliveryManagementParams.setOpeReportDivUpdDate(
                        GtlUtils.getSystemTimestamp());
                }

                //�X�V�҃R�[�h
                l_eleDeliveryManagementParams.setLastUpdater(
                    WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);

                //�X�V���t
                l_eleDeliveryManagementParams.setLastUpdatedTimestamp(
                    GtlUtils.getSystemTimestamp());

                log.exiting(STR_METHOD_NAME);
                Processors.getDefaultProcessor().doUpdateQuery(
                    l_eleDeliveryManagementParams);
            }
            catch (DataFindException l_ex)
            {
                //�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B
                log.debug("�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                return;
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
        }
    }
}
@


1.1
log
@*** empty log message ***
@
text
@d2 1
a2 1
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
d4 1
a4 1
Author Name      : Daiwa Institute of Research
d31 1
d63 3
d83 1
d89 3
d377 18
d1176 207
@

