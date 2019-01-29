head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�޻��޽Impl(WEB3AdminAccInfoMailAddressDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
Revesion History : 2010/02/22 �����F (���u) ���f��261 ���f��271
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoMailAddressCsv;
import webbroker3.accountinfo.define.WEB3AccountOpenMailFlagDef;
import webbroker3.accountinfo.message.WEB3AccInfoAccountMailAddressInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountinfoMultiMailaddressFlagDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3MailAssortmentDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.data.AccountMailAddressRow;
import webbroker3.gentrade.data.MailAssortmentRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�޻��޽Impl)<BR>
 * �Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�޻��޽�����N���X<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressDownloadServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoMailAddressDownloadService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressDownloadServiceImpl.class);
    /**
     * @@roseuid 418F3A030148
     */
    public WEB3AdminAccInfoMailAddressDownloadServiceImpl() 
    {
     
    }
    
    /**
     * Ұٱ��ڽ�S���_�E�����[�h���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���Ұٱ��ڽ�S���⍇��ظ��Ă̏ꍇ <BR>
�@@   *   �|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���Ұٱ��ڽ�S��<BR>
     * �޳�۰��ظ��Ă̏ꍇ <BR>
     * �@@�|get�_�E�����[�h���()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���Ұٱ��ڽ�S��<BR>
     * ̧���޳�۰��ظ��Ă̏ꍇ <BR>
     * �@@�|get�_�E�����[�h�t�@@�C��()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147E3800143
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminAccInfoMailAddressFileDownloadRequest)
        {
            l_response = this.getDownloadFile((WEB3AdminAccInfoMailAddressFileDownloadRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoMailAddressDownloadRequest)
        {           
            l_response = this.getDownloadScreen((WEB3AdminAccInfoMailAddressDownloadRequest)l_request) ;
        }
        else if (l_request instanceof WEB3AdminAccInfoMailAddressInquiryRequest)
        {           
            l_response = this.getInputScreen((WEB3AdminAccInfoMailAddressInquiryRequest)l_request) ;
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
     * ���[���A�h���X�S���_�E�����[�h���͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i���[���A�h���X�S���c�k�jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ұٱ��ڽ�S���⍇��ظ��ăf�[�^�I�u�W�F�N�g 
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A56ED003D
     */
    protected WEB3AdminAccInfoMailAddressInquiryResponse getInputScreen(WEB3AdminAccInfoMailAddressInquiryRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoMailAddressInquiryRequest)";
        log.entering(STR_METHOD_NAME );
        //1.1getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //1.2validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        
        WEB3AdminAccInfoMailAddressInquiryResponse l_response = 
            (WEB3AdminAccInfoMailAddressInquiryResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }    
    /**
     * (get�_�E�����[�h���)<BR>
     * ���[���A�h���X�S���_�E�����[�h��ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i���[���A�h���X�S���c�k�jget�_�E�����[�h��ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147E389001A
     */
    protected WEB3AdminAccInfoMailAddressDownloadResponse getDownloadScreen(WEB3AdminAccInfoMailAddressDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadScreen(WEB3AdminAccInfoMailAddressDownloadRequest)";
        log.entering(STR_METHOD_NAME );
        
        //1.1validate( )
        l_request.validate();
        //1.2getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        //1.4validate���X����(String[])
        l_administrator.validateBranchPermission(l_request.branchCode);
        //1.5get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get���X�R�[�h( )
        String l_strBranchCode = l_administrator.getBranchCode();

        //get�������[���A�h���X�Ή����{(
        //���X�R�[�h : String, �،���ЃR�[�h : String, �v���t�@@�����X�� : String, �v���t�@@�����X���̘A�� : int)
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = new WEB3GentradeBranch(l_administrator.getInstitution(), l_strBranchCode);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        String l_strMultiMailAddressFlag = l_branch.getMultiMailAddressEnforcement(
            l_strBranchCode, l_strInstitutionCode, WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG, 1);

        //get�_�E�����[�h�f�[�^(String, String[], String, String, String, String)
        WEB3AccInfoAccountMailAddressInfo[] l_accountMailAddressInfos =
            this.getDownloadData(l_strInstitutionCode, l_request.branchCode, l_request.accountCodeFrom,
                l_request.accountCodeTo, l_request.sendFlag, l_strMultiMailAddressFlag);

        //1.8�v���p�e�B�Z�b�g
        
        //�y�[�W���\���s��
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
        //�v���y�[�W�ԍ�
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);    

        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_accountMailAddressInfos, l_intPageIndex, l_intPageSize);
        
        //1.7�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�޳�۰��ڽ��ݽ(WEB3GenRequest)
        WEB3AdminAccInfoMailAddressDownloadResponse l_response = 
            (WEB3AdminAccInfoMailAddressDownloadResponse)l_request.createResponse();
        //(�\���y�[�W�ԍ�)
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + ""; 
        //���y�[�W��
        l_response.totalPages= l_pageIndexInfo.getTotalPages() + ""; 
        //�����R�[�h��
        l_response.totalRecords= l_pageIndexInfo.getTotalRecords() + ""; 
        //���ׂ̃��X�g
        l_response.accountMailAddressList = 
            (WEB3AccInfoAccountMailAddressInfo[])l_pageIndexInfo.getArrayReturned(WEB3AccInfoAccountMailAddressInfo.class);        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * ���[���A�h���X�S���_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i���[���A�h���X�S���c�k�jget�_�E�����[�h�t�@@�C���v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ұٱ��ڽ�S��̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147E389001C
     */
    protected WEB3AdminAccInfoMailAddressFileDownloadResponse getDownloadFile(WEB3AdminAccInfoMailAddressFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminAccInfoMailAddressFileDownloadRequest)";
        log.entering(STR_METHOD_NAME );
        //1.1validate( )
        l_request.validate();
        //1.2getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        //1.4validate���X����(String[])
        l_administrator.validateBranchPermission(l_request.branchCode);
        //1.5get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        //get���X�R�[�h( )
        String l_strBranchCode = l_administrator.getBranchCode();

        //get�������[���A�h���X�Ή����{(���X�R�[�h : String, �،���ЃR�[�h : String, �v���t�@@�����X�� : String, �v���t�@@�����X���̘A�� : int)
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = new WEB3GentradeBranch(l_administrator.getInstitution(), l_strBranchCode);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        String l_strMultiMailAddressFlag = l_branch.getMultiMailAddressEnforcement(
            l_strBranchCode, l_strInstitutionCode, WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG, 1);

        //get�_�E�����[�h�f�[�^(String, String[], String, String, String, String)
        WEB3AccInfoAccountMailAddressInfo[] l_accountMailAddressInfos =
            this.getDownloadData(l_strInstitutionCode, l_request.branchCode, l_request.accountCodeFrom,
                l_request.accountCodeTo, l_request.sendFlag, l_strMultiMailAddressFlag);
        //1.7���[���A�h���XCSV( )
        WEB3AdminAccInfoMailAddressCsv l_mailAddressCsv = new WEB3AdminAccInfoMailAddressCsv();
        //1.8
        for (int i = 0; i < l_accountMailAddressInfos.length; i++)
        {
            int l_intLineNumber = l_mailAddressCsv.addRow();
            l_mailAddressCsv.setInstitutionCode(l_intLineNumber, l_accountMailAddressInfos[i].institutionCode);
            l_mailAddressCsv.setBranchCode(l_intLineNumber, l_accountMailAddressInfos[i].branchCode);
            l_mailAddressCsv.setAccountCode(l_intLineNumber, l_accountMailAddressInfos[i].accountCode);
            l_mailAddressCsv.setAccountName(l_intLineNumber, l_accountMailAddressInfos[i].accountName);
            l_mailAddressCsv.setMailAddress(l_intLineNumber, l_accountMailAddressInfos[i].mailAddress);
            l_mailAddressCsv.setSendFlag(l_intLineNumber,l_accountMailAddressInfos[i].sendFlag);
            l_mailAddressCsv.setMailAddressUpdatedDate(l_intLineNumber, l_accountMailAddressInfos[i].updateDate);
            l_mailAddressCsv.setMailAddressUpdaterCode(l_intLineNumber, l_accountMailAddressInfos[i].updaterCode);
        }
        //1.9getCSV�t�@@�C���s( )
        String[] l_cvsFileLines = l_mailAddressCsv.getCsvFileLines();
        
        WEB3AdminAccInfoMailAddressFileDownloadResponse l_response = 
            (WEB3AdminAccInfoMailAddressFileDownloadResponse)l_request.createResponse();
        l_response.downloadFile = l_cvsFileLines;
        l_response.currentDate = GtlUtils.getSystemTimestamp();    
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�f�[�^)<BR>
     * �ڋq�}�X�^�e�[�u�����A�_�E�����[�h�Ώۃf�[�^���擾����B<BR>
     * <BR>
     * �P�j�@@�ڋq�}�X�^�e�[�u�����͌ڋq���[���A�h���X�e�[�u������<BR>
     * �@@�P�|�P�j�@@����.�������[���A�h���X�Ή����{�t���O�I= "�Q" �̏ꍇ�A�ȉ��������s���B<BR>
     * �@@�ڋq�}�X�^�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�ڋq�}�X�^.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     * �@@�ڋq�}�X�^.���X�R�[�h in �i���X�R�[�h[0]�C���X�R�[�h[1]�C�C�j�i���P�j�@@And<BR>
     * �@@�ڋq�}�X�^.�����R�[�h >= �ڋq�R�[�h�i���j And<BR>
     * �@@�ڋq�}�X�^.�����R�[�h <= �ڋq�R�[�h�i���j And<BR>
     * �@@�ڋq�}�X�^.�ē����[�����M�t���O = ���M�t���O�i���Q�j<BR>
     * <BR>
     *  ���P�@@�����̕��X�R�[�h[]�̗v�f��񋓂���B<BR>
     *  ���Q�@@���M�t���O = �v �̏ꍇ true<BR>
     * �@@�@@�@@�@@���M�t���O = �s�v�̏ꍇ false<BR>
     * �@@�@@�@@�@@���M�t���O = �w��Ȃ��̏ꍇ�͌��������Ƃ��Ċ܂܂Ȃ��B<BR>
     * <BR>
     * �@@[�擾���iorder by�j]<BR>
     * �@@�ڋq�}�X�^.���X�R�[�h<BR>
     * �@@�ڋq�}�X�^.�����R�[�h<BR>
     * <BR>
     * �@@�P�|�Q�j�@@����.�������[���A�h���X�Ή����{�t���O == "�Q"�̏ꍇ�A�ȉ��������s���B<BR>
     * �@@�@@�P�|�Q�|�P�j�@@�ڋq���[���A�h���X�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�ڋq���[���A�h���X.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     * �@@�@@�ڋq���[���A�h���X.���X�R�[�h in �i���X�R�[�h[0]�C���X�R�[�h[1]�C�C�j�i���P�j�@@And<BR>
     * �@@�@@�ڋq���[���A�h���X.�����R�[�h >= �ڋq�R�[�h�i���j And    <BR>
     * �@@�@@�ڋq���[���A�h���X.�����R�[�h <= �ڋq�R�[�h�i���j    <BR>
     * �@@�@@���P�@@�����̕��X�R�[�h[]�̗v�f��񋓂���B <BR>
     *  <BR>
     * �@@�@@[�擾���iorder by�j]    <BR>
     * �@@�@@�ڋq���[���A�h���X.���X�R�[�h    <BR>
     * �@@�@@�ڋq���[���A�h���X.�����R�[�h    <BR>
     * �@@�@@�ڋq���[���A�h���X�e�[�u�����[���A�h���X�敪     <BR>
     *  <BR>
     * �@@�@@�P�|�Q�|�Q�j�@@�@@�ē����[�����M�t���O�ɂ�� ���[����ʃe�[�u������������B  <BR>
     *  <BR>
     * �@@�@@�@@�P�|�Q�|�Q�|�P�j�@@����.���M�t���O = �w��Ȃ��̏ꍇ�A�Q�j�̏������s���B <BR>
     *  <BR>
     * �@@�@@�@@�P�|�Q�|�Q�|�Q�j�@@����.���M�t���O = �v �̏ꍇ�A�P�|�Q�|�P�j�Ŏ擾�����ڋq���[���A�h���X�sLoop�������s���B <BR>
     * �@@�@@�@@�@@���[����ʃe�[�u�����ȉ��̏����Ō�������B<BR>
     *  <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h = �ڋq���[���A�h���X�s.���X�R�[�h<BR>
     * �@@�@@�@@�ڋq�R�[�h = �ڋq���[���A�h���X�s.�ڋq�R�[�h<BR>
     * �@@�@@�@@���[����ʋ敪=6:�ē����[��<BR>
     * �@@�@@�@@���R�[�h���擾�ł��Ȃ��ꍇ�A�Y���ڋq���[���A�h���X�s���폜���A�Q�j�̏������s���B<BR>
     *  <BR>
     * �@@�@@�@@�P�|�Q�|�Q�|�R�j�@@����.���M�t���O = �s�v �̏ꍇ�A�P�|�Q�|�P�j�Ŏ擾�����ڋq���[���A�h���X�sLoop�������s���B    <BR>
     * �@@�@@�@@�@@���[����ʃe�[�u�����ȉ��̏����Ō�������B<BR>
     *  <BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@���X�R�[�h = �ڋq���[���A�h���X�s.���X�R�[�h<BR>
     * �@@�@@�@@�@@�ڋq�R�[�h = �ڋq���[���A�h���X�s.�ڋq�R�[�h<BR>
     * �@@�@@�@@�@@���[����ʋ敪=6:�ē����[��<BR>
     * �@@�@@�@@�@@���R�[�h���擾�ł���ꍇ�A�Y���ڋq���[���A�h���X�s���폜���A�Q�j�̏������s���B<BR>
     * <BR>
     * �Q�j�@@�ڋq���[���A�h���X���ꗗList�i�FArrayList�j����<BR>
     * �@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�ڋq���[���A�h���X��񐶐�<BR>
     * <BR>
     * �@@�R�|�P�j�@@����.�������[���A�h���X�Ή����{�t���O�I= "�Q" �̏ꍇ�A<BR>
     * �@@�P�j�Ŏ擾�����e�s�I�u�W�F�N�g�i�F�ڋq�}�X�^Params�j���ɁA�S�j�̏������s���B<BR>
     * �@@�ڋq���[���A�h���X���𐶐����A�ȉ��̒ʂ�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq�}�X�^�s.�،���ЃR�[�h<BR>
     * �@@�ڋq���[���A�h���X���.���X�R�[�h = �ڋq�}�X�^�s.���X�R�[�h<BR>
     * �@@�ڋq���[���A�h���X���.�ڋq�R�[�h = �ڋq�}�X�^�s.�����R�[�h�̍�6byte<BR>
     * �@@�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j�@@���ڋq���i�����j�Ƃ��Ďg�p<BR>
     * �@@�ڋq���[���A�h���X���.���[���A�h���X = �ڋq�}�X�^�s.email�A�h���X<BR>
     * �@@�ڋq���[���A�h���X���.�X�V�� = �ڋq�}�X�^�s.email�A�h���X�X�V����<BR>
     * �@@�ڋq���[���A�h���X���.�X�V�҃R�[�h = (*1)<BR>
     * �@@�ڋq���[���A�h���X���.���M�t���O = �i*2�j<BR>
     * <BR>
     * �@@(*1) �X�V�҃R�[�h<BR>
     * �@@�i�ڋq�}�X�^�s.email�A�h���X�X�V�҃R�[�h == �ڋq�}�X�^�s.�����R�[�h�j�̏ꍇ�A�����R�[�h�̍�6byte�B<BR>
     * �@@�ȊO�A�ڋq�}�X�^�s.email�A�h���X�X�V�҃R�[�h�B<BR>
     * <BR>
     * �@@(*2)���M�t���O<BR>
     * �@@�ڋq�}�X�^�s.�ē����[�����M�t���O = �v �̏ꍇ 1<BR>
     * �@@�ڋq�}�X�^�s.�ē����[�����M�t���O = �s�v�̏ꍇ 0<BR>
     * <BR>
     * �@@�R�|�Q�j ����.�������[���A�h���X�Ή����{�t���O == "�Q"�̏ꍇ�A<BR>
     * �@@�P�j�Ŏ擾�����e�s�I�u�W�F�N�g�i�F�ڋq���[���A�h���XParams�j���ɁA�S�j�̏������s���B <BR>
     * �@@�ڋq���[���A�h���X����ǉ��A�ȉ��̒ʂ�v���p�e�B���Z�b�g����B    <BR>
     *  <BR>
     * �@@�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h        <BR>
     * �@@�ڋq���[���A�h���X���.���X�R�[�h = �ڋq���[���A�h���X�s.���X�R�[�h    <BR>
     * �@@�ڋq���[���A�h���X���.�ڋq�R�[�h = �ڋq���[���A�h���X�s.�����R�[�h�̍�6byte     <BR>
     * �@@�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j�@@���ڋq���i�����j�Ƃ��Ďg�p       <BR>
     * �@@�ڋq���[���A�h���X���.���[���A�h���X = �ڋq���[���A�h���X�s.���[���A�h���X    <BR>
     * �@@�ڋq���[���A�h���X���.�X�V�� = �ڋq���[���A�h���X�s.���[���A�h���X�X�V����        <BR>
     * �@@�ڋq���[���A�h���X���.�X�V�҃R�[�h = �ڋq���[���A�h���X.���[���A�h���X�X�V�҃R�[�h        <BR>
     * �@@�ڋq���[���A�h���X���.���M�t���O = �i*2�j        <BR>
     *      <BR>
     * �@@(*2)���M�t���O       <BR>
     * �@@�擾�����ڋq���[���A�h���X�s.�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h<BR>
     *   �y�у��[����ʋ敪���h6:�ē����[���h�ł��邱�ƂɊY�����郁�[����ʃI�u�W�F�N�g���擾����B     <BR>
     * �@@���R�[�h������ �̏ꍇ 1       <BR>
     * �@@���R�[�h�����݂��Ȃ��ꍇ 0<BR>
     * <BR>
     * �S�j�@@�ڋq���[���A�h���X���ꗗList�i�FArrayList�j�ɃI�u�W�F�N�g��ǉ�����B<BR>
     * �@@�R�j�Ő��������I�u�W�F�N�g���ڋq���[���A�h���X���ꗗList�i�FArrayList�j�ɒǉ��iadd�j����B<BR>
     * <BR>
     * �T�j�@@�_�E�����[�h�f�[�^�ԋp<BR>
     * �@@�ڋq���[���A�h���X���ꗗList�i�FArrayList�j��z��ɕϊ��itoArray()�j���A�ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCodes - ���X�R�[�h�z��
     * @@param l_strAccountCodeFrom - String �ڋq�R�[�h�i���j
     * @@param l_strAccountCodeTo - String �ڋq�R�[�h�i���j
     * @@param l_strSendFlag - String ���M�t���O
     * @@param l_strMultiMailAddressFlag - �������[���A�h���X�Ή����{�t���O
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountMailAddressInfo[]
     * @@roseuid 4147E389002A
     */
    protected WEB3AccInfoAccountMailAddressInfo[] getDownloadData
	(String l_strInstitutionCode, String[] l_strBranchCodes, String l_strAccountCodeFrom,
	String l_strAccountCodeTo, String l_strSendFlag,
    String l_strMultiMailAddressFlag) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getDownloadData(String, String[], String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null; 

        List l_listResults = new ArrayList();

        int l_intBranchCodesCnt = l_strBranchCodes.length;
        StringBuffer l_sbQueryBranchCodes = new StringBuffer();
        for (int i = 0; i < l_intBranchCodesCnt; i++)
        {
            if (l_sbQueryBranchCodes.length() != 0)
            {
                l_sbQueryBranchCodes.append(", ");
            }
            l_sbQueryBranchCodes.append("?");
            
        }           

        //����.�������[���A�h���X�Ή����{�t���O�I= "�Q" �̏ꍇ�A�ȉ��������s��
        //�ڋq�}�X�^�e�[�u�����ȉ��̏����Ō�������B
        if (!WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strMultiMailAddressFlag))
        {
            List l_lisRecords = null;
            //�ڋq�}�X�^�e�[�u�����ȉ��̏����Ō�������
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");        
            //���X������ǉ�����B���X�R�[�h[]�̗v�f����"?"��ҏW����B
            
            if (l_intBranchCodesCnt > 0)
            {             
                l_sbWhere.append(" and branch_code in (" + l_sbQueryBranchCodes.toString() + ")") ;             
            }  
            //�ڋq�}�X�^.�����R�[�h >= �ڋq�R�[�h�i���j And 
            l_sbWhere.append(" and SubStr(account_code,0,6) >= ?");
    		//�ڋq�}�X�^.�����R�[�h <= �ڋq�R�[�h�i���j And 
            l_sbWhere.append(" and SubStr(account_code,0,6) <= ?");
    		//�ڋq�}�X�^.�ē����[�����M�t���O = ���M�t���O�i���Q�j
            if (WEB3AccountOpenMailFlagDef.sendFlag.equals(l_strSendFlag)
            		|| WEB3AccountOpenMailFlagDef.unSendFlag.equals(l_strSendFlag))
            {
                l_sbWhere.append(" and information_mail_flag = ?");
            }

            //�擾���iorder by�j
            StringBuffer l_sbOrderBy = new StringBuffer();
            l_sbOrderBy.append(" branch_code, ");
            l_sbOrderBy.append(" account_code ");

            //�ڋq���[���A�h���X���ꗗList�i�FArrayList�j����ArrayList�𐶐�����B
            List l_listWhere = new ArrayList();
            l_listWhere.add(l_strInstitutionCode);
            for (int i = 0; i < l_intBranchCodesCnt; i++)
            {
                l_listWhere.add(l_strBranchCodes[i]);
            }
            l_listWhere.add(l_strAccountCodeFrom);
            l_listWhere.add(l_strAccountCodeTo);
            //���M�t���O = �v �̏ꍇ true
            //���M�t���O = �s�v�̏ꍇ false
            //���M�t���O = �w��Ȃ��̏ꍇ�͌��������Ƃ��Ċ܂܂Ȃ��B
            if (WEB3AccountOpenMailFlagDef.sendFlag.equals(l_strSendFlag))
            {
            	l_listWhere.add(BooleanEnum.TRUE);
            }
            else if (WEB3AccountOpenMailFlagDef.unSendFlag.equals(l_strSendFlag))
            {
            	l_listWhere.add(BooleanEnum.FALSE);
            }

            Object[] l_objWhere = l_listWhere.toArray();
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords = l_queryProcessor.doFindAllQuery(
                    MainAccountRow.TYPE,
                    l_sbWhere.toString(),
                    l_sbOrderBy.toString(),
                    null,
                    l_objWhere);
            }
            catch (DataQueryException l_ex) 
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex) 
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            for (int i = 0 ; i <l_lisRecords.size(); i++)
            {
                MainAccountRow l_mainAccountRow = (MainAccountRow)l_lisRecords.get(i);
                WEB3AccInfoAccountMailAddressInfo l_accountMailAddressInfo = new WEB3AccInfoAccountMailAddressInfo();

                //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq�}�X�^�s.�،���ЃR�[�h
                l_accountMailAddressInfo.institutionCode = l_mainAccountRow.getInstitutionCode();

                //�ڋq���[���A�h���X���.���X�R�[�h = �ڋq�}�X�^�s.���X�R�[�h
                l_accountMailAddressInfo.branchCode = l_mainAccountRow.getBranchCode();

                //�ڋq���[���A�h���X���.�ڋq�R�[�h = �ڋq�}�X�^�s.�����R�[�h�̍�6byte
                l_accountMailAddressInfo.accountCode = l_mainAccountRow.getAccountCode().substring(0, 6);

                //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j
                l_accountMailAddressInfo.accountName = l_mainAccountRow.getFamilyName();

                //�ڋq���[���A�h���X���.���[���A�h���X = �ڋq�}�X�^�s.email�A�h���X
                l_accountMailAddressInfo.mailAddress = l_mainAccountRow.getEmailAddress();
                //�ڋq���[���A�h���X���.�X�V�� = �ڋq�}�X�^�s.email�A�h���X�X�V����
                l_accountMailAddressInfo.updateDate = l_mainAccountRow.getEmailLastUpdatedTimestamp();

                //�ڋq���[���A�h���X���.�X�V�҃R�[�h = (*1)
                if (l_mainAccountRow.getEmailLastUpdater().equals(l_mainAccountRow.getAccountCode()))
                {
                    l_accountMailAddressInfo.updaterCode = l_mainAccountRow.getAccountCode().substring(0, 6);
                }
                else
                {
                    l_accountMailAddressInfo.updaterCode = l_mainAccountRow.getEmailLastUpdater();
                }
                //�ڋq�}�X�^�s.�ē����[�����M�t���O = �v �̏ꍇ 1 
                //�ڋq�}�X�^�s.�ē����[�����M�t���O = �s�v�̏ꍇ 0 
                if (BooleanEnum.TRUE.equals(l_mainAccountRow.getInformationMailFlag()))
                {
                    l_accountMailAddressInfo.sendFlag = WEB3AccountOpenMailFlagDef.sendFlag;
                }
                else if (BooleanEnum.FALSE.equals(l_mainAccountRow.getInformationMailFlag()))
                {
                    l_accountMailAddressInfo.sendFlag = WEB3AccountOpenMailFlagDef.unSendFlag;
                }

                //�Ő��������I�u�W�F�N�g���ڋq���[���A�h���X���ꗗList�i�FArrayList�j�ɒǉ��iadd�j����B
                l_listResults.add(l_accountMailAddressInfo);
            }
        }
        //����.�������[���A�h���X�Ή����{�t���O == "�Q"�̏ꍇ�A�ȉ��������s���B
        //�@@�ڋq���[���A�h���X�e�[�u���ƃ��[����ʃe�[�u�����ȉ��̏����Ō�������B
        else
        {
            
            StringBuffer l_sbWhereAccountMailAddress = new StringBuffer();
            //�ڋq���[���A�h���X.�،���ЃR�[�h = �،���ЃR�[�h
            l_sbWhereAccountMailAddress.append(" institution_code = ? ");
            //�ڋq���[���A�h���X.���X�R�[�h in �i���X�R�[�h[0]�C���X�R�[�h[1]�C�C�j�i���P�j
            if (l_intBranchCodesCnt > 0)
            {
                l_sbWhereAccountMailAddress.append(" and branch_code in (" + l_sbQueryBranchCodes.toString() + ")") ;
            }
            //�ڋq���[���A�h���X.�����R�[�h >= �ڋq�R�[�h�i���j  And
            l_sbWhereAccountMailAddress.append(" and SubStr(account_code,0,6) >= ?");
            //�ڋq���[���A�h���X.�����R�[�h <= �ڋq�R�[�h�i���j And
            l_sbWhereAccountMailAddress.append(" and SubStr(account_code,0,6) <= ?");

            //�擾���iorder by�j
            StringBuffer l_sbOrderByAccountMailAddress = new StringBuffer();
            l_sbOrderByAccountMailAddress.append(" branch_code, ");
            l_sbOrderByAccountMailAddress.append(" account_code, ");
            l_sbOrderByAccountMailAddress.append(" address_div ");

            //�ڋq���[���A�h���X���ꗗList�i�FArrayList�j����ArrayList�𐶐�����B
            List l_listWhereAccountMailAddress = new ArrayList();

            l_listWhereAccountMailAddress.add(l_strInstitutionCode);
            for (int i = 0; i < l_intBranchCodesCnt; i++)
            {
                l_listWhereAccountMailAddress.add(l_strBranchCodes[i]);
            }
            l_listWhereAccountMailAddress.add(l_strAccountCodeFrom);
            l_listWhereAccountMailAddress.add(l_strAccountCodeTo);

            Object[] l_objWhereAccountMailAddress = l_listWhereAccountMailAddress.toArray();
            List l_lisAccountMailAddressRecords = new ArrayList();
            try 
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                l_lisAccountMailAddressRecords = l_queryProcessor.doFindAllQuery(
                    AccountMailAddressRow.TYPE,
                    l_sbWhereAccountMailAddress.toString(),
                    l_sbOrderByAccountMailAddress.toString(),
                    null,
                    l_objWhereAccountMailAddress);
            }
            catch (DataQueryException l_ex) 
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            catch (DataNetworkException l_ex) 
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            int l_intSize = 0;
            if (l_lisAccountMailAddressRecords != null && l_lisAccountMailAddressRecords.size() != 0)
            {
                l_intSize = l_lisAccountMailAddressRecords.size();
            }
            AccountMailAddressRow[] l_accountMailAddressRows = new AccountMailAddressRow[l_intSize];
            l_lisAccountMailAddressRecords.toArray(l_accountMailAddressRows);
            int l_intFlag = 0;
            for (int i = 0; i < l_intSize; i++)
            {
                AccountMailAddressRow l_accountMailAddressRow = l_accountMailAddressRows[i];
                
                //���[����ʃe�[�u������������
                StringBuffer l_sbWhereMailAssortment = new StringBuffer();
                l_sbWhereMailAssortment.append(" institution_code = ? ");
                l_sbWhereMailAssortment.append(" and branch_code = ? ");
                l_sbWhereMailAssortment.append(" and account_code = ?");
                l_sbWhereMailAssortment.append(" and mail_assortment_div = ? ");
                List l_listWhereMailAssortment = new ArrayList();
                l_listWhereMailAssortment.add(l_accountMailAddressRow.getInstitutionCode());
                l_listWhereMailAssortment.add(l_accountMailAddressRow.getBranchCode());
                l_listWhereMailAssortment.add(l_accountMailAddressRow.getAccountCode());
                l_listWhereMailAssortment.add(WEB3MailAssortmentDivDef.GUIDE_MAIL);
                Object[] l_objWhereMailAssortment = l_listWhereMailAssortment.toArray();

                List l_lisMailAssortmentRecords = new ArrayList();
                try 
                {
                    l_queryProcessor = Processors.getDefaultProcessor();
                    l_lisMailAssortmentRecords = l_queryProcessor.doFindAllQuery(
                        MailAssortmentRow.TYPE,
                        l_sbWhereMailAssortment.toString(),
                        l_objWhereMailAssortment);
                }
                catch (DataQueryException l_ex) 
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex) 
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                if ((WEB3AccountOpenMailFlagDef.sendFlag.equals(l_strSendFlag)
                        && (l_lisMailAssortmentRecords == null || l_lisMailAssortmentRecords.size() == 0))
                    || (WEB3AccountOpenMailFlagDef.unSendFlag.equals(l_strSendFlag)
                        && (l_lisMailAssortmentRecords != null && l_lisMailAssortmentRecords.size() != 0)))
                {
                    l_lisAccountMailAddressRecords.remove(l_intFlag);
                }
                else
                {
                    l_intFlag++;
                    WEB3AccInfoAccountMailAddressInfo l_accountMailAddressInfo = new WEB3AccInfoAccountMailAddressInfo();

                    //�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h
                    l_accountMailAddressInfo.institutionCode = l_accountMailAddressRow.getInstitutionCode();

                    //�ڋq���[���A�h���X���.���X�R�[�h = �ڋq���[���A�h���X�s.���X�R�[�h
                    l_accountMailAddressInfo.branchCode = l_accountMailAddressRow.getBranchCode();

                    //�ڋq���[���A�h���X���.�ڋq�R�[�h = �ڋq���[���A�h���X�s.�����R�[�h�̍�6byte
                    l_accountMailAddressInfo.accountCode = l_accountMailAddressRow.getAccountCode().substring(0, 6);

                    //�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j
                    MainAccountRow l_accountRow = null;
                    try
                    {
                        l_accountRow = MainAccountDao.findRowByInstitutionCodeBranchCodeAccountCode(
                            l_accountMailAddressRow.getInstitutionCode(),
                            l_accountMailAddressRow.getBranchCode(),
                            l_accountMailAddressRow.getAccountCode());
                    }
                    catch (DataFindException l_ex)
                    {
                        log.error("�e�[�u���ɊY������f�[�^������܂���B");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    catch (DataNetworkException l_ex)
                    {
                        log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    catch (DataQueryException l_ex)
                    {
                        log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    l_accountMailAddressInfo.accountName = l_accountRow.getFamilyName();

                    //�ڋq���[���A�h���X���.���[���A�h���X = �ڋq���[���A�h���X�s.���[���A�h���X
                    l_accountMailAddressInfo.mailAddress = l_accountMailAddressRow.getEmailAddress();
                    //�ڋq���[���A�h���X���.�X�V�� = �ڋq���[���A�h���X�s.���[���A�h���X�X�V����
                    l_accountMailAddressInfo.updateDate = l_accountMailAddressRow.getEmailLastUpdatedTimestamp();

                    //�ڋq���[���A�h���X���.�X�V�҃R�[�h = �ڋq���[���A�h���X.���[���A�h���X�X�V�҃R�[�h
                    l_accountMailAddressInfo.updaterCode = l_accountMailAddressRow.getEmailLastUpdater();

                    //�擾�����ڋq���[���A�h���X�s.�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h
                    //�y�у��[����ʋ敪���h6:�ē����[���h�ł��邱�ƂɊY�����郁�[����ʃI�u�W�F�N�g���擾����B
                    //���R�[�h������ �̏ꍇ 1
                    //���R�[�h�����݂��Ȃ��ꍇ 0
                    if ( l_lisMailAssortmentRecords != null && l_lisMailAssortmentRecords.size() != 0)
                    {
                        l_accountMailAddressInfo.sendFlag = WEB3AccountOpenMailFlagDef.sendFlag;
                    }
                    else if (l_lisMailAssortmentRecords == null || l_lisMailAssortmentRecords.size() == 0)
                    {
                        l_accountMailAddressInfo.sendFlag = WEB3AccountOpenMailFlagDef.unSendFlag;
                    }

                    //���������I�u�W�F�N�g���ڋq���[���A�h���X���ꗗList�i�FArrayList�j�ɒǉ��iadd�j����B
                    l_listResults.add(l_accountMailAddressInfo);
                }
            }
        }

        //�ڋq���[���A�h���X���ꗗList�i�FArrayList�j��z��ɕϊ��itoArray()�j
        WEB3AccInfoAccountMailAddressInfo[] l_mailAddressInfos = new WEB3AccInfoAccountMailAddressInfo[l_listResults.size()];
        l_listResults.toArray(l_mailAddressInfos);

        log.exiting(STR_METHOD_NAME);    
        return l_mailAddressInfos;
    }
}
@
