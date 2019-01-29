head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����p�U�����������۰�޻��޽Impl(WEB3AdminAccInfoExclusiveTransferAccountUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/17�J�N���V (���u) �V�K�쐬
                   2005/12/26 �A���� (���u) �d�l�ύXNo.074
                   2006/02/03 ���ہi���{���u�j�d�l�ύXNo.085
                   2006/02/08 ������ (���u) �c�a���C�A�E�gNo.012
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoExclusiveTransferAccountUploadCsv;
import webbroker3.accountinfo.define.WEB3AccInfoUploadStateDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoUploadHistoryUnit;
import webbroker3.accountinfo.message.WEB3AdminAccInfoErrorAccount;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoExclusiveTransferAccountUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.ExclusiveUseAccountDao;
import webbroker3.gentrade.data.ExclusiveUseAccountParams;
import webbroker3.gentrade.data.FinInstitutionBankDao;
import webbroker3.gentrade.data.FinInstitutionBankRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l����p�U�����������۰�޻��޽Impl)<BR>
 * �Ǘ��҂��q�l����p�U�����������۰�޻��޽�����N���X<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadServiceImpl
    extends WEB3AccInfoClientRequestService
    implements WEB3AdminAccInfoExclusiveTransferAccountUploadService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoExclusiveTransferAccountUploadServiceImpl.class);

    /**
     * @@roseuid 418F3A090148
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadServiceImpl()
    {

    }

    /**
     * ��p�U��������A�b�v���[�h���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����p�U�������<BR>
     * ����۰�ޓ���ظ��Ă̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����p�U�������<BR>
     * ����۰�ފm�Fظ��Ă̏ꍇ <BR>
     * �@@�|validate�A�b�v���[�h�t�@@�C��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����p�U�������<BR>
     * ����۰�ފ���ظ��Ă̏ꍇ <BR>
     * �@@�|submit�A�b�v���[�h�t�@@�C��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����p�U�������<BR>
     * ����۰�ޒ��~ظ��Ă̏ꍇ <BR>
     * �@@�|undo�A�b�v���[�h()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415BC20B0266
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����p�U�����������۰�ޓ���ظ��Ă̏ꍇ
        if (l_request instanceof WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest)
        {
            
            l_response = this.getUploadScreen((WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest) l_request);
        }
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����p�U�����������۰�ފm�Fظ��Ă̏ꍇ 
        else if (l_request instanceof WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest)
        {
            
            l_response = this.validateUploadFile((WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest) l_request);
        }
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����p�U�����������۰�ފ���ظ��Ă̏ꍇ  
        else if (l_request instanceof WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest)
        {
            
            l_response = this.submitUploadFile((WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest) l_request);
        }
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����p�U�����������۰�ޒ��~ظ��Ă̏ꍇ 
        else if (l_request instanceof WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest)
        {
            
            l_response = this.undoUpload((WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest) l_request);
        }
        else
        {
            
            log.exiting(STR_METHOD_NAME);
            log.error(" �p�����[�^�^�C�v�s���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�A�b�v���[�h���)<BR>
     * ��p�U��������A�b�v���[�h��ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i��p�U��������t�k�jget�A�b�v���[�h��ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l����p�U�����������۰�ޓ���ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse
     * @@roseuid 415BC288010E
     */
    protected WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse getUploadScreen(WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest l_request)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = 
            " getUploadScreen(WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.2) validate����(String, boolean),validate������t�\()
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRANSFER_ACCOUNT, true);
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.3) ��p�U��������A�b�v���[�hCSV( )
        WEB3AdminAccInfoExclusiveTransferAccountUploadCsv l_eclusiveTransferAccountUploadCsv =
            new WEB3AdminAccInfoExclusiveTransferAccountUploadCsv();

        //1.4) get�ŐV�A�b�v���[�h����(long) 
        AdministratorUploadRow l_administratorUploadRow = l_eclusiveTransferAccountUploadCsv.getLatestUploadAction(0);

        //1.5)  �A�b�v���[�h���������݂���ꍇ�A�������{
        //1.6) �Ǘ��҂��q�l����p�U�����������۰�ޓ���ڽ��ݽ(WEB3GenRequest)
        WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse l_response = 
            (WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse) l_request.createResponse();
        if (l_administratorUploadRow != null)
        {
            
            //1.5.1) ���q�l���A�b�v���[�h���𖾍�( )
            WEB3AccInfoUploadHistoryUnit l_accInfoUploadHistoryUnit = new WEB3AccInfoUploadHistoryUnit();

            //1.5.2) �v���p�e�B�Z�b�g
            //�@@�A�b�v���[�h������ԋ敪
            //�i�A�b�v���[�h�s.�A�b�v���[�h�I������ == null�j�̏ꍇ�A�h�A�b�v���[�h��
            if (l_administratorUploadRow.getUploadEndTimestamp() == null)
            {
                
                l_accInfoUploadHistoryUnit.uploadStateDiv = WEB3AccInfoUploadStateDivDef.UPOAD_CONFIRMING;
            }
            
            //�i�A�b�v���[�h�s.�A�b�v���[�h�I������ != null�j�̏ꍇ�A�h�A�b�v���[�h�ρh
            else
            {
                
                l_accInfoUploadHistoryUnit.uploadStateDiv = WEB3AccInfoUploadStateDivDef.UPLOAD_COMPLETE;
            }
            
            //�A�b�v���[�h�����F�@@�A�b�v���[�h�s.�A�b�v���[�h����
            l_accInfoUploadHistoryUnit.uploadNumber = "" + l_administratorUploadRow.getUploadCount();
            
            //�A�b�v���[�h�J�n�����F�@@�A�b�v���[�h�s.�A�b�v���[�h�J�n���� 
            l_accInfoUploadHistoryUnit.uploadStartDate = l_administratorUploadRow.getUploadStartTimestamp();
            
            //�A�b�v���[�h�I�������F�@@�A�b�v���[�h�s.�A�b�v���[�h�I������ 
            l_accInfoUploadHistoryUnit.uploadEndDate = l_administratorUploadRow.getUploadEndTimestamp();
            
            //���q�l���G���[�ԍ��F�@@�A�b�v���[�h�s.���b�Z�[�W�R�[�h
            l_accInfoUploadHistoryUnit.accInfoErrorId = l_administratorUploadRow.getMessageCode();
            
            //1.7) �v���p�e�B�Z�b�g
            //�@@�A�b�v���[�h�����ꗗ�F�@@
            //�A�b�v���[�h���������݂���ꍇ�A���q�l���A�b�v���[�h���𖾍׃I�u�W�F�N�g�B            
            l_response.uploadHistoryList = l_accInfoUploadHistoryUnit;
        }
        
        //�A�b�v���[�h���������݂��Ȃ��ꍇ�Anull�B
        else
        {
            
            l_response.uploadHistoryList = null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�A�b�v���[�h�t�@@�C��)<BR>
     * ��p�U��������A�b�v���[�h�m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i��p�U��������t�k�jvalidate�A�b�v���[�h�t�@@�C���v�Q�ƁB <BR> 
     * @@param l_request - �Ǘ��҂��q�l����p�U�����������۰�ފm�Fظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse
     * @@roseuid 415BC2AC01AB
     */
    protected WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse validateUploadFile(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest l_request)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME =
            " validateUploadFile(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3)validate����(String, boolean),validate������t�\()
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRANSFER_ACCOUNT, true);
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.4) ��p�U��������A�b�v���[�hCSV()
        WEB3AdminAccInfoExclusiveTransferAccountUploadCsv l_eclusiveTransferAccountUploadCsv =
            new WEB3AdminAccInfoExclusiveTransferAccountUploadCsv();

        //1.5) validate�����A�b�v���[�h(long)
        l_eclusiveTransferAccountUploadCsv.validateSameTimeUpload(null);

        //1.6) get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //1.7) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.8)ArrayList()
        ArrayList l_array = new ArrayList();

        //1.9) save�A�b�v���[�h�J�n(long, String, String, String, String)
        l_eclusiveTransferAccountUploadCsv.saveUpLoadStart(0, null, null, null, l_strAdministratorCode);

        //1.10) ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[]�̊e�v�f����LOOP
        for (int i = 0; i < l_request.uploadFile.length; i++)
        {
            
            //1.10.1) add���׍s(String)
            //1.10.2) add���׍s()�ŗ�O���X���[���ꂽ�ꍇ
            int l_intAddRow = 0;
            try
            {
                
                l_intAddRow = l_eclusiveTransferAccountUploadCsv.addRow(l_request.uploadFile[i]);
            }
            catch(WEB3BaseException l_ex)
            {
                
                log.exiting(STR_METHOD_NAME);
                //1.10.2.1) save�A�b�v���[�h�G���[(ErrorInfo)
                l_eclusiveTransferAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());
                
                //1.10.2.2) throw �i��O�j
                throw new WEB3SystemLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //��s�̏ꍇ�iadd���׍s()�̖߂�l < 0�j�A���Y�v�f�̏����𒆒f�icontinue;�j
            if (l_intAddRow < 0)
            {
                continue;
            }

            //1.10.3) validate���׍s(long, String)
            //1.10.4) validate�d���ڋq(long)
            //1.10.5) validate���׍s()�Cvalidate�d���ڋq()�ɂė�O���X���[���ꂽ�ꍇ
            try
            {
                l_eclusiveTransferAccountUploadCsv.validateDetailsLine(l_intAddRow, l_strInstitutionCode);
                l_eclusiveTransferAccountUploadCsv.validateDuplicateAccount(l_intAddRow);
            }
            catch(WEB3BaseException l_ex)
            {
                
                log.exiting(STR_METHOD_NAME);
                //1.10.5.1) save�A�b�v���[�h�G���[(ErrorInfo)
                l_eclusiveTransferAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());
                
                //1.10.5.2) throw �i��O�j
                throw new WEB3SystemLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getErrorMessage() + l_eclusiveTransferAccountUploadCsv.getBranchCode() + "." + l_eclusiveTransferAccountUploadCsv.getAccountCode(l_intAddRow),
                    l_ex);
            }
            
            try
            {
                //�s�ԍ��F�@@�iadd���׍s()�̖߂�l�j
                //�،���ЃR�[�h�F�@@�Ǘ���.get�،���ЃR�[�h()
                l_eclusiveTransferAccountUploadCsv.getMainAccount(l_intAddRow,l_administrator.getInstitutionCode());
            }
            catch (WEB3BaseException l_ex)
            {
                //1.10.7.1�Ǘ��҂��q�l���G���[�ڋq( )
                WEB3AdminAccInfoErrorAccount l_accInfoErrorAccount = new WEB3AdminAccInfoErrorAccount();
                //1.10.7.2get���X�R�[�h(int)
                String l_strBranchCode = l_eclusiveTransferAccountUploadCsv.getBranchCode(l_intAddRow);
                //1.10.7.3get�ڋq�R�[�h(int)
                String l_strAccountCode = l_eclusiveTransferAccountUploadCsv.getAccountCode(l_intAddRow);
                //1.10.7.4
                //�v���p�e�B�Z�b�g

                //�Ǘ��҂��q�l���G���[�ڋq�C���X�^���X�ɒl���Z�b�g����B

                //���X�R�[�h�F�@@get���X�R�[�h()
                //�ڋq�R�[�h�F�@@get�ڋq�R�[�h()
                l_accInfoErrorAccount.accountCode = l_strAccountCode;
                l_accInfoErrorAccount.branchCode = l_strBranchCode;
                //1.10.7.5
                l_array.add(l_accInfoErrorAccount);
                //1.10.7.6
                l_eclusiveTransferAccountUploadCsv.deleteRow(l_intAddRow);
                
            }
        }
        //1.11toArray( )
        WEB3AdminAccInfoErrorAccount[] l_adminAccInfoErrorAccount = new WEB3AdminAccInfoErrorAccount[l_array.size()];
        l_array.toArray(l_adminAccInfoErrorAccount);

        //1.12) get���׍s��( )
        int l_intRowCount = l_eclusiveTransferAccountUploadCsv.getRowCount();

        //1.13) get�A�b�v���[�h�h�c( )
        long l_lngAdministratorUploadId = l_eclusiveTransferAccountUploadCsv.getAdministratorUploadId();

        //1.14) save�A�b�v���[�hTemp( )
        l_eclusiveTransferAccountUploadCsv.saveUploadTemp();

        //1.15) createResponse( )
        WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse l_response =
            (WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse) l_request.createResponse();
            
        //1.16) �v���p�e�B�Z�b�g
        //�A�b�v���[�h�����F�@@get�A�b�v���[�h����()
        l_response.uploadNumber = "" + l_intRowCount;
        
        //�A�b�v���[�h�h�c�F�@@get�A�b�v���[�h�h�c()
        l_response.uploadID = "" + l_lngAdministratorUploadId;
        
        //�@@�G���[�ڋq�ꗗ�F�@@�G���[�ڋqList.toArray()
        l_response.errorAccountList = l_adminAccInfoErrorAccount;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�A�b�v���[�h�t�@@�C��)<BR>
     * ��p�U��������A�b�v���[�h�����������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i��p�U��������t�k�jsubmit�A�b�v���[�h�t�@@�C���v�Q�ƁB <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u���q�l���i��p�U��������t�k�jsubmit�A�b�v���[�h�t�@@�C���v�Q�ƁB<BR>
     *  1.10.11.1.isExist���Z�@@��(String, String)<BR>
     * �@@�@@���݂��Ȃ��ꍇ�́A�u��s�f�[�^�s�����v�G���[���X���[����B
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_01314<BR> 
     *  ========================================================<BR>
     * @@param l_request - �Ǘ��҂��q�l����p�U�����������۰�ފ���ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse
     * @@roseuid 415BC2DE00E0
     */
    protected WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse submitUploadFile(WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " submitUploadFile(WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) validate( )
        l_request.validate();

        //1.2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3)validate����(String, boolean),validate������t�\()
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRANSFER_ACCOUNT, true);
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.4) validate����p�X���[�h(String)
        l_administrator.validateTradingPassword(l_request.password);

        //1.5) ��p�U��������A�b�v���[�hCSV(long)
        long l_lngUploadId = Long.parseLong(l_request.uploadID);
        WEB3AdminAccInfoExclusiveTransferAccountUploadCsv l_eclusiveTransferAccountUploadCsv =
            new WEB3AdminAccInfoExclusiveTransferAccountUploadCsv(l_lngUploadId);

        //1.6) validate�����A�b�v���[�h(long)
        l_eclusiveTransferAccountUploadCsv.validateSameTimeUpload(new Long(l_lngUploadId));

        //1.7) setDataFrom�A�b�v���[�hTemp(long)
        l_eclusiveTransferAccountUploadCsv.setDataFromUploadTemp(l_lngUploadId);

        //1.8) get���׍s��( )
        int l_intRowCount = l_eclusiveTransferAccountUploadCsv.getRowCount();

        //1.9) ���׍s�̐���LOOP����
        for (int i = 0; i < l_intRowCount; i++)
        {
            
            //1.9.1) get���X�R�[�h(int)
            String l_strBranchCode = l_eclusiveTransferAccountUploadCsv.getBranchCode(i);

            //1.9.2) get�ڋq(int, String)
            WEB3GentradeMainAccount l_mainAccount =
                l_eclusiveTransferAccountUploadCsv.getMainAccount(i, l_administrator.getInstitutionCode());

            //1.9.3) getAccountId( )
            long l_lngAccountId = l_mainAccount.getAccountId();

            // 1.9.4) get��s��(int)
            String l_strFinInstitutionName = l_eclusiveTransferAccountUploadCsv.getFinInstitutionName(i);

            //1.9.5) get�x�X��(int)
            String l_strFinBranchName = l_eclusiveTransferAccountUploadCsv.getFinBranchName(i);

            //1.9.6) get�x�X�R�[�h(int)
            String l_strFinBranchCode = l_eclusiveTransferAccountUploadCsv.getFinBranchCode(i);

            //1.9.7) get������ޖ�(int)
            String l_strFinAccountTypeName = l_eclusiveTransferAccountUploadCsv.getFinAccountTypeName(i);

            //1.9.8) get�����ԍ�(int)
            String l_strFinAccountNo = l_eclusiveTransferAccountUploadCsv.getFinAccountNo(i);

            //1.9.9) get�������`�l(int)
            String l_strFinAccountName = l_eclusiveTransferAccountUploadCsv.getFinAccountName(i);

            //1.10.10 get��s�R�[�h(int)
            String l_strFinInstitutionCode = l_eclusiveTransferAccountUploadCsv.getFinInstitutionCode(i);
            
            if (l_strFinInstitutionCode == null || l_strFinInstitutionCode.length() == 0)
            {
				log.debug("��s�R�[�h�������͂ł��B");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02346, 
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"��s�R�[�h�������͂ł��B");
            }
            
            //1.10.11 isExist���Z�@@��(String, String)
            //���݂��Ȃ��ꍇ�́A�u��s�f�[�^�s�����v�G���[���X���[����B
            if (!isExistFinInstitution(l_strFinInstitutionCode, l_strFinBranchCode))
            {
                log.debug("���Z�@@�ւ����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01314,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���Z�@@�ւ����݂��Ȃ��B");
            }
            
            //1.10.12) isExist��p�U�������(long)
            try
            {
                
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                Timestamp l_systemTimestamp = GtlUtils.getSystemTimestamp();
                
                if (this.isExistExclusiveTransferAccount(l_lngAccountId))
                {
                    
                    ExclusiveUseAccountParams l_exclusiveUseAccountParams =
                        new ExclusiveUseAccountParams(
                            ExclusiveUseAccountDao.findRowByPk(l_lngAccountId));

                    //1.10.13) doUpdateQuery(Row)
                    l_exclusiveUseAccountParams.setAccountId(l_lngAccountId);
                    l_exclusiveUseAccountParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
                    l_exclusiveUseAccountParams.setBranchCode(l_strBranchCode);
                    l_exclusiveUseAccountParams.setAccountCode(l_mainAccount.getAccountCode());
                    l_exclusiveUseAccountParams.setFinInstitutionName(l_strFinInstitutionName);
                    l_exclusiveUseAccountParams.setFinBranchName(l_strFinBranchName);
                    l_exclusiveUseAccountParams.setFinBranchCode(l_strFinBranchCode);
                    l_exclusiveUseAccountParams.setFinAccountTypeName(l_strFinAccountTypeName);
                    l_exclusiveUseAccountParams.setFinAccountNo(l_strFinAccountNo);
                    l_exclusiveUseAccountParams.setFinAccountName(l_strFinAccountName);
                    l_exclusiveUseAccountParams.setLastUpdater(l_administrator.getAdministratorCode());
                    l_exclusiveUseAccountParams.setLastUpdatedTimestamp(l_systemTimestamp);
                    l_exclusiveUseAccountParams.setFinInstitutionCode(l_strFinInstitutionCode);
                    l_queryProcessor.doUpdateQuery(l_exclusiveUseAccountParams);
                }
                else
                {
                    
                    ExclusiveUseAccountParams l_exclusiveUseAccountParams = new ExclusiveUseAccountParams();
                    
                    //1.10.14) doInsertQuery(Row) 
                    l_exclusiveUseAccountParams.setAccountId(l_lngAccountId);
                    l_exclusiveUseAccountParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
                    l_exclusiveUseAccountParams.setBranchCode(l_strBranchCode);
                    l_exclusiveUseAccountParams.setAccountCode(l_mainAccount.getAccountCode());
                    l_exclusiveUseAccountParams.setFinInstitutionName(l_strFinInstitutionName);
                    l_exclusiveUseAccountParams.setFinBranchName(l_strFinBranchName);
                    l_exclusiveUseAccountParams.setFinBranchCode(l_strFinBranchCode);
                    l_exclusiveUseAccountParams.setFinAccountTypeName(l_strFinAccountTypeName);
                    l_exclusiveUseAccountParams.setFinAccountNo(l_strFinAccountNo);
                    l_exclusiveUseAccountParams.setFinAccountName(l_strFinAccountName);
                    l_exclusiveUseAccountParams.setLastUpdater(l_administrator.getAdministratorCode());
                    l_exclusiveUseAccountParams.setCreatedTimestamp(l_systemTimestamp);
                    l_exclusiveUseAccountParams.setLastUpdatedTimestamp(l_systemTimestamp);
                    l_exclusiveUseAccountParams.setFinInstitutionCode(l_strFinInstitutionCode);
                    l_queryProcessor.doInsertQuery(l_exclusiveUseAccountParams);
                }
            }
            catch (DataFindException l_ex)
            {
                
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //1.11) save�A�b�v���[�h�I��( )
        l_eclusiveTransferAccountUploadCsv.saveUploadEnd();

        //1.12) delete�A�b�v���[�hTemp()
        l_eclusiveTransferAccountUploadCsv.deleteUploadTemp();

        //1.13) createResponse( )
        WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse l_response =
            (WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (undo�A�b�v���[�h)<BR>
     * ��p�U��������A�b�v���[�h���~�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i��p�U��������t�k�jundo�A�b�v���[�h�t�@@�C���v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l����p�U�����������۰�ޒ��~ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse
     * @@roseuid 415BC2F80208
     */
    protected WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse undoUpload(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest l_request)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = 
            " undoUpload(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);

        // ��p�U��������A�b�v���[�hCSV(long)
        WEB3AdminAccInfoExclusiveTransferAccountUploadCsv l_eclusiveTransferAccountUploadCsv =
            new WEB3AdminAccInfoExclusiveTransferAccountUploadCsv(Long.parseLong(l_request.uploadID));

        //delete�A�b�v���[�hTemp( )
        l_eclusiveTransferAccountUploadCsv.deleteUploadTemp();

        //save�A�b�v���[�h���~( )
        l_eclusiveTransferAccountUploadCsv.saveUploadStop();

        //createResponse( )
        WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse l_response =
            (WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (isExist��p�U�������)<BR>
     * ��p�U��������e�[�u���Ɋ����s�����݂��邩�𔻒肷��B<BR>
     * <BR>
     * �P�j�@@��p�U��������e�[�u�����A�w��̌���ID�ɊY������s���擾����B<BR>
     * �Q�j�@@�s���擾�ł����true�A�擾�ł��Ȃ����false��ԋp����B<BR>
     * @@param l_lngAccountId - ����ID
     * @@return boolean
     * @@roseuid 4161143E01BB
     */
    protected boolean isExistExclusiveTransferAccount(long l_lngAccountId) throws WEB3SystemLayerException
    {
        
        final String STR_METHOD_NAME = " isExistExclusiveTransferAccount(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            ExclusiveUseAccountDao.findRowByPk(l_lngAccountId);
        }
        catch (DataFindException l_ex)
        {
            
            return false;
        }
        catch (DataNetworkException l_ex)
        {
            
            log.error(" �\�����Ȃ��V�X�e���G���[���������܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            
            log.error(" �\�����Ȃ��V�X�e���G���[���������܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (isExist���Z�@@��)<BR>
     * ���Z�@@�փe�[�u���Ɋ����s�����݂��邩�𔻒肷��B<BR>
     * <BR>
     * �P�j�@@���Z�@@�ցi��s�j�}�X�^���A�w��̋�s�R�[�h�Ǝx�X�R�[�h�� <BR>
     * �@@�@@�@@�Y������s���擾����B <BR>
     * �Q�j�@@�s���擾�ł����true�A�擾�ł��Ȃ����false��ԋp����B<BR>
     * @@param l_strFinInstitutionCode - ��s�R�[�h
     * @@param l_strBranchCode - �x�X�R�[�h
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isExistFinInstitution(String l_strFinInstitutionCode, String l_strBranchCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isExistFinInstitution(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���Z�@@�ցi��s�j�}�X�^���A�w��̋�s�R�[�h�Ǝx�X�R�[�h�ɊY������s���擾����B
        FinInstitutionBankRow l_finInstitutionBankRow = null;
        try
        {
            l_finInstitutionBankRow = FinInstitutionBankDao.findRowByPk(
                l_strFinInstitutionCode,
                l_strBranchCode);
            
            //�Q�j�@@�s���擾�ł����true�A�擾�ł��Ȃ����false��ԋp����B
            if (l_finInstitutionBankRow != null) 
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        catch (DataFindException l_ex)
        {
            //��O���X���[
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataException l_ex)
        {
            //��O���X���[
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
        }
    }
}
@
