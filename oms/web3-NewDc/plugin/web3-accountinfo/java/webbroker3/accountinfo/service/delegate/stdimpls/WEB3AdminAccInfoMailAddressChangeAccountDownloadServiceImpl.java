head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�޻��޽Impl(WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
Revesion History : 2010/02/22 �����F (���u) ���f��262 267
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoMailAddressCsv;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.message.WEB3AccInfoAccountMailAddressInfo;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressChangeAccountDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountinfoMultiMailaddressFlagDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.AccountMailAddressRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�޻��޽Impl)<BR>
 * �Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�޻��޽�����N���X<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoMailAddressChangeAccountDownloadService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl.class);
    /**
     * @@roseuid 418F3A020157
     */
    public WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl() 
    {
     
    }
    
    /**
     * ���[���A�h���X�ύX�ڋq�_�E�����[�h���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq<BR>
     * �⍇��ظ��Ă̏ꍇ<BR>
     * �@@�|get���͉��()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq<BR>
     * �޳�۰��ظ��Ă̏ꍇ <BR>
     * �@@�|get�_�E�����[�h���()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq<BR>
     * ̧���޳�۰��ظ��Ă̏ꍇ <BR>
     * �@@�|get�_�E�����[�h�t�@@�C��()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F44102F9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest)
        {
            l_response = this.getInputScreen((WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest)
        {           
            l_response = this.getDownloadScreen((WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest)l_request) ;
        }
        else if (l_request instanceof WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest)
        {           
            l_response = this.getDownloadFile((WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest)l_request);
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
     * ���[���A�h���X�ύX�ڋq�_�E�����[�h�⍇����ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i���[���A�h���X�ύX�ڋq�c�k�jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A56ED003D
     */
    protected WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse getInputScreen(WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //1.2validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        
        WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse l_response = 
            (WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse)l_request.createResponse();

        Timestamp l_systemTimestamp = GtlUtils.getSystemTimestamp();
        Timestamp l_gentradeBizDate = WEB3GentradeUtils.getBizDate(l_systemTimestamp, -1);
        l_response.previousBizDate = WEB3DateUtility.toDay(l_gentradeBizDate);
        Date l_datPreviousDate = WEB3DateUtility.addDay(l_systemTimestamp, -1);
        l_response.previousDate = WEB3DateUtility.toDay(l_datPreviousDate);
        
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h���)<BR>
     * ���[���A�h���X�ύX�ڋq�_�E�����[�h��ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i���[���A�h���X�ύX�ڋq�c�k�jget�_�E�����[�h��ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F2DC01EF
     */
    protected WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse getDownloadScreen(WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getDownloadScreen(WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
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

        //get�_�E�����[�h�f�[�^(String, String[], Date, Date, ���q�l���\�[�g�L�[[], String)
        WEB3AccInfoAccountMailAddressInfo[] l_accountMailAddressInfos = 
            this.getDownloadData(
                l_strInstitutionCode, l_request.branchCode, l_request.startDate,
                l_request.endDate, l_request.sortKeys, l_strMultiMailAddressFlag);

        //�y�[�W���\���s��
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
        //�v���y�[�W�ԍ�
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);    

        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_accountMailAddressInfos, l_intPageIndex, l_intPageSize);
        
        //1.7�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�޳�۰��ڽ��ݽ(WEB3GenRequest)
        WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse l_response = 
            (WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse)l_request.createResponse();
        //(�\���y�[�W�ԍ�)
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + ""; 
        //���y�[�W��
        l_response.totalPages= l_pageIndexInfo.getTotalPages() + ""; 
        //�����R�[�h��
        l_response.totalRecords= l_pageIndexInfo.getTotalRecords() + ""; 
        //���ׂ̃��X�g
        l_response.mailAddressChangeAccountList = 
            (WEB3AccInfoAccountMailAddressInfo[])l_pageIndexInfo.getArrayReturned(WEB3AccInfoAccountMailAddressInfo.class);        
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * ���[���A�h���X�ύX�ڋq�_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i���[���A�h���X�ύX�ڋq�c�k�jget�_�E�����[�h�t�@@�C���v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F2DC01FF
     */
    protected WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse getDownloadFile(WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getDownloadFile(WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
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

        //get�_�E�����[�h�f�[�^(String, String[], Date, Date, ���q�l���\�[�g�L�[[], String)
        WEB3AccInfoAccountMailAddressInfo[] l_accountMailAddressInfos = 
            this.getDownloadData(
                l_strInstitutionCode, l_request.branchCode, l_request.startDate,
                l_request.endDate, l_request.sortKeys, l_strMultiMailAddressFlag);
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
            l_mailAddressCsv.setMailAddressUpdatedDate(l_intLineNumber, l_accountMailAddressInfos[i].updateDate);
            l_mailAddressCsv.setMailAddressUpdaterCode(l_intLineNumber, l_accountMailAddressInfos[i].updaterCode);
        }
        //1.9getCSV�t�@@�C���s( )
        String[] l_cvsFileLines = l_mailAddressCsv.getCsvFileLines();
        
        WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse l_response = 
            (WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse)l_request.createResponse();
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
     * �@@�ڋq�}�X�^.���X�R�[�h in �i���X�R�[�h[0]�C���X�R�[�h[1]�C�C�j And <BR>
     * �� �����̕��X�R�[�h[]�̗v�f��񋓂���B<BR>
     * �@@�ڋq�}�X�^.email�A�h���X�X�V���� >= �J�n���@@And<BR>
     * �@@�ڋq�}�X�^.email�A�h���X�X�V���� < �I�����̗���<BR>
     * <BR>
     * �@@[�擾���iorder by�j]<BR>
     * �@@���@@�����̃\�[�g�L�[.�L�[���ڂ��������ځ^�����ɂĎ擾����B<BR>
     * �@@�\�[�g�L�[.�L�[���ڂɑΉ�����ڋq�}�X�^�̍��ڂ͈ȉ��̒ʂ�Ƃ���B<BR>
     * <BR>
     * �@@�i�\�[�g�L�[.�L�[���� == ���X�R�[�h�j�F�@@�ڋq�}�X�^.���X�R�[�h<BR>
     * �@@�i�\�[�g�L�[.�L�[���� == �ڋq�R�[�h�j�F�@@�ڋq�}�X�^.�����R�[�h<BR>
     * �@@�i�\�[�g�L�[.�L�[���� == �X�V���j�F�@@�ڋq�}�X�^.email�A�h���X�X�V����<BR>
     * <BR>
     * �@@�P�|�Q�j�@@����.�������[���A�h���X�Ή����{�t���O == "�Q"�̏ꍇ�A�ȉ��������s���B<BR>
     * �@@�ڋq���[���A�h���X�e�[�u�����ȉ��̏����Ō�������B       <BR>
     *      <BR>
     * �@@[����]        <BR>
     * �@@�ڋq���[���A�h���X.�،���ЃR�[�h = �،���ЃR�[�h And     <BR>
     * �@@�ڋq���[���A�h���X.���X�R�[�h in �i���X�R�[�h[0]�C���X�R�[�h[1]�C�C�j<BR>
     * �@@ And �� �����̕��X�R�[�h[]�̗v�f��񋓂���B<BR>
     * �@@�ڋq���[���A�h���X.email�A�h���X�X�V���� >= �J�n���@@And      <BR>
     * �@@�ڋq���[���A�h���X.email�A�h���X�X�V���� < �I�����̗���   <BR>
     *      <BR>
     * �@@[�擾���iorder by�j]     <BR>
     * �@@���@@�����̃\�[�g�L�[.�L�[���ڂ��������ځ^�����ɂĎ擾����B      <BR>
     * �@@�\�[�g�L�[.�L�[���ڂɑΉ�����ڋq���[���A�h���X�̍��ڂ͈ȉ��̒ʂ�Ƃ���B<BR>
     *  <BR>
     * �@@�i�\�[�g�L�[.�L�[���� == ���X�R�[�h�j�F�@@�ڋq���[���A�h���X.���X�R�[�h  <BR>
     * �@@�i�\�[�g�L�[.�L�[���� == �ڋq�R�[�h�j�F�@@�ڋq���[���A�h���X.�����R�[�h  <BR>
     * �@@�i�\�[�g�L�[.�L�[���� == �A�h���X�敪�j�F�@@�ڋq���[���A�h���X.�A�h���X�敪<BR>
     * �@@�i�\�[�g�L�[.�L�[���� == �X�V���j�F�@@�ڋq���[���A�h���X.���[���A�h���X�X�V����<BR>
     * <BR>
     * �Q�j�@@�ڋq���[���A�h���X���ꗗList�i�FArrayList�j����<BR>
     * �@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�ڋq���[���A�h���X��񐶐�<BR>
     * �@@�R�|�P�j�@@����.�������[���A�h���X�Ή����{�t���O�I= "�Q" �̏ꍇ�A�ȉ��������s���B<BR>
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
     * <BR>
     * �@@(*1) �X�V�҃R�[�h<BR>
     * �@@�@@�i�ڋq�}�X�^�s.email�A�h���X�X�V�҃R�[�h == �ڋq�}�X�^�s.�����R�[�h�j�̏ꍇ�A<BR>
     * �@@�@@�����R�[�h�̍�6byte�B<BR>
     * �@@�@@�ȊO�A�ڋq�}�X�^�s.email�A�h���X�X�V�҃R�[�h�B<BR>
     * <BR>
     * �R�|�Q�j �@@����.�������[���A�h���X�Ή����{�t���O == "�Q"�̏ꍇ�A�ȉ��������s��<BR>
     * �@@�P�j�Ŏ擾�����e�s�I�u�W�F�N�g�i�F�ڋq���[���A�h���XParams�j���ɁA�S�j�̏������s���B<BR>
     * �@@�擾�����ڋq���[���A�h���X�s.�،���ЃR�[�h�A���X�R�[�h�A<BR>
     * �@@�ڋq�R�[�h�ɊY������ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�ڋq���[���A�h���X����ǉ��A�ȉ��̒ʂ�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�ڋq���[���A�h���X���.�،���ЃR�[�h = �ڋq���[���A�h���X�s.�،���ЃR�[�h<BR>
     * �@@�ڋq���[���A�h���X���.���X�R�[�h = �ڋq���[���A�h���X�s.���X�R�[�h<BR>
     * �@@�ڋq���[���A�h���X���.�ڋq�R�[�h = �ڋq���[���A�h���X�s.�����R�[�h�̍�6byte<BR>
     * �@@�ڋq���[���A�h���X���.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j�@@���ڋq���i�����j�Ƃ��Ďg�p<BR>
     * �@@�ڋq���[���A�h���X���.���[���A�h���X = �ڋq���[���A�h���X�s.���[���A�h���X<BR>
     * �@@�ڋq���[���A�h���X���.�X�V�� = �ڋq���[���A�h���X�s.���[���A�h���X�X�V����<BR>
     * �@@�ڋq���[���A�h���X���.�X�V�҃R�[�h = �ڋq���[���A�h���X�s.���[���A�h���X�X�V�҃R�[�h<BR>
     * <BR>
     * �S�j�@@�ڋq���[���A�h���X���ꗗList�i�FArrayList�j�ɃI�u�W�F�N�g��ǉ�����B<BR>
     * �@@�R�j�Ő��������I�u�W�F�N�g���ڋq���[���A�h���X���ꗗList�i�FArrayList�j�ɒǉ��iadd�j����B<BR>
     * <BR>
     * �T�j�@@�_�E�����[�h�f�[�^�ԋp<BR>
     * �@@�ڋq���[���A�h���X���ꗗList�i�FArrayList�j��z��ɕϊ��itoArray()�j���A<BR>
     * �ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCodes - ���X�R�[�h�z��
     * 
     * @@param l_datStartDate - �J�n��
     * @@param l_datEndDate - �I����
     * @@param l_sortKeys - �\�[�g�L�[
     * @@param l_strMultiMailAddressFlag - �������[���A�h���X�Ή����{�t���O
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountMailAddressInfo[]
     * @@roseuid 4147F2DC0201
     */
    protected WEB3AccInfoAccountMailAddressInfo[] getDownloadData(
        String l_strInstitutionCode, 
        String[] l_strBranchCodes, 
        Date l_datStartDate, 
        Date l_datEndDate, 
        WEB3AccInfoSortKey[] l_sortKeys,
        String l_strMultiMailAddressFlag) throws WEB3BaseException 
             
    {
        final String STR_METHOD_NAME = "getDownloadData(String, String[], Date, Date, WEB3AccInfoSortKey[], String)";
        log.entering(STR_METHOD_NAME);

        //�ڋq�}�X�^�e�[�u�����ȉ��̏����Ō�������
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");

        //���X������ǉ�����B���X�R�[�h[]�̗v�f����"?"��ҏW����B
        int l_intBranchCodesCnt = l_strBranchCodes.length;
        if (l_intBranchCodesCnt > 0)
        {
            StringBuffer l_sbQueryBranchCodes = new StringBuffer();

            for (int i = 0; i < l_intBranchCodesCnt; i++)
            {
                if (l_sbQueryBranchCodes.length() != 0)
                {
                    l_sbQueryBranchCodes.append(", ");
                }
                l_sbQueryBranchCodes.append("?");
            }
            l_sbWhere.append(" and branch_code in (" + l_sbQueryBranchCodes.toString() + ")") ;
        }       

        l_sbWhere.append(" and email_last_updated_timestamp >= ? ");                               
        l_sbWhere.append(" and email_last_updated_timestamp < ? ");

        //�ڋq���[���A�h���X���ꗗList�i�FArrayList�j����ArrayList�𐶐�����B
        List l_lisWhere = new ArrayList();
 
        l_lisWhere.add(l_strInstitutionCode);
        for (int i = 0; i < l_strBranchCodes.length; i++)
        {
            l_lisWhere.add(l_strBranchCodes[i]);           
        }        
        l_lisWhere.add(l_datStartDate);

        Date l_datEndDateNextDay = WEB3DateUtility.addDay(l_datEndDate, 1);
        l_lisWhere.add(l_datEndDateNextDay);

        Object[] l_objWhere = l_lisWhere.toArray();

        List l_lisRecords = null;
        QueryProcessor l_queryProcessor = null;
        List l_lisMailAddressInfos = new ArrayList();

        //����.�������[���A�h���X�Ή����{�t���O�I= "�Q" �̏ꍇ
        if (!WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strMultiMailAddressFlag))
        {
            //�擾���iorder by�j
            StringBuffer l_sbOrderBy = new StringBuffer();
            int l_intSortKeyCnt = l_sortKeys.length;
            if (l_intSortKeyCnt > 0)
            {
                StringBuffer l_sbSortKey = new StringBuffer();

                for (int i = 0; i < l_intSortKeyCnt; i++)
                {
                    if (l_sbSortKey.length() != 0)
                    {
                        l_sbSortKey.append(", ");
                    }
                    WEB3AccInfoSortKey l_accInfoSortKey = (WEB3AccInfoSortKey)l_sortKeys[i];
                    if (WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_accInfoSortKey.keyItem))
                    {
                        l_sbSortKey.append(" branch_code ");
                    }
                    else if (WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_accInfoSortKey.keyItem))
                    {
                        l_sbSortKey.append(" account_code ");
                    }
                    else if (WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(l_accInfoSortKey.keyItem))
                    {
                        l_sbSortKey.append(" email_last_updated_timestamp ");
                    }

                    if (WEB3AscDescDef.ASC.equals(l_accInfoSortKey.ascDesc))
                    {
                        l_sbSortKey.append(" ASC ");
                    }
                    else
                    {
                        l_sbSortKey.append(" DESC ");
                    }
                }
                l_sbOrderBy.append(l_sbSortKey.toString());
            }

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
                //�Ő��������I�u�W�F�N�g���ڋq���[���A�h���X���ꗗList�i�FArrayList�j�ɒǉ��iadd�j����B
                l_lisMailAddressInfos.add(l_accountMailAddressInfo);
            }
        }
        //����.�������[���A�h���X�Ή����{�t���O == "�Q"�̏ꍇ
        else
        {
            //�擾���iorder by�j
            StringBuffer l_sbOrderBy = new StringBuffer();
            int l_intSortKeyCnt = l_sortKeys.length;
            if (l_intSortKeyCnt > 0)
            {
                StringBuffer l_sbSortKey = new StringBuffer();

                for (int i = 0; i < l_intSortKeyCnt; i++)
                {
                    if (l_sbSortKey.length() != 0)
                    {
                        l_sbSortKey.append(", ");
                    }
                    WEB3AccInfoSortKey l_accInfoSortKey = (WEB3AccInfoSortKey)l_sortKeys[i]; 
                    if (WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_accInfoSortKey.keyItem))
                    {
                        l_sbSortKey.append(" branch_code ");
                    }
                    else if (WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_accInfoSortKey.keyItem))
                    {
                        l_sbSortKey.append(" account_code ");
                    }
                    else if (WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(l_accInfoSortKey.keyItem))
                    {
                        l_sbSortKey.append(" email_last_updated_timestamp ");
                    }

                    if (WEB3AscDescDef.ASC.equals(l_accInfoSortKey.ascDesc))
                    {
                        l_sbSortKey.append(" ASC ");
                    }
                    else
                    {
                        l_sbSortKey.append(" DESC ");
                    }
                }
                if (l_sbSortKey.length() != 0)
                {
                    l_sbSortKey.append(", ");
                }
                l_sbSortKey.append(" address_div ASC");
                l_sbOrderBy.append(l_sbSortKey.toString());
            }

            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords = l_queryProcessor.doFindAllQuery(
                    AccountMailAddressRow.TYPE,
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
                AccountMailAddressRow l_accountMailAddressRow = (AccountMailAddressRow)l_lisRecords.get(i);
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

                //�ڋq���[���A�h���X���.�X�V�҃R�[�h = �ڋq���[���A�h���X�s.���[���A�h���X�X�V�҃R�[�h
                l_accountMailAddressInfo.updaterCode = l_accountMailAddressRow.getEmailLastUpdater();
                //�Ő��������I�u�W�F�N�g���ڋq���[���A�h���X���ꗗList�i�FArrayList�j�ɒǉ��iadd�j����B
                l_lisMailAddressInfos.add(l_accountMailAddressInfo);
            }
        }
        //�ڋq���[���A�h���X���ꗗList�i�FArrayList�j��z��ɕϊ��itoArray()�j
        WEB3AccInfoAccountMailAddressInfo[] l_mailAddressInfos = 
            new WEB3AccInfoAccountMailAddressInfo[l_lisMailAddressInfos.size()];
        l_lisMailAddressInfos.toArray(l_mailAddressInfos);
        
        log.exiting(STR_METHOD_NAME);    
        return l_mailAddressInfos;
    }
}
@
