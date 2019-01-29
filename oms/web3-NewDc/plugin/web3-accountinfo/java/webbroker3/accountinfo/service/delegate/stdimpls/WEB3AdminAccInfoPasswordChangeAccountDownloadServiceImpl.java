head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordChangeAccountDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�޻��޽Impl(WEB3AdminAccInfoPasswordChangeAccountDownloadServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoPasswordChangeAccountCsv;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoPasswordChangeAccountDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�޻��޽Impl)<BR>
 * �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�޻��޽�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordChangeAccountDownloadServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoPasswordChangeAccountDownloadService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoPasswordChangeAccountDownloadServiceImpl.class);   
        
    /**
     * @@roseuid 418F3A06005D
     */
    public WEB3AdminAccInfoPasswordChangeAccountDownloadServiceImpl() 
    {
     
    }
    
    /**
     * �Ïؔԍ��ύX�ڋq�_�E�����[�h���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq<BR>
     * �⍇��ظ��Ă̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq<BR>
     * �޳�۰��ظ��Ă̏ꍇ <BR>
     * �@@�|get�_�E�����[�h���()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq<BR>
     * ̧���޳�۰��ظ��Ă̏ꍇ <BR>
     * �@@�|get�_�E�����[�h�t�@@�C��()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B8BC70318
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminAccInfoPasswordChangeAccountInquiryRequest)
        {
            l_response = getInputScreen((WEB3AdminAccInfoPasswordChangeAccountInquiryRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoPasswordChangeAccountDownloadRequest)
        {
            l_response = getDownloadScreen((WEB3AdminAccInfoPasswordChangeAccountDownloadRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest)
        {
            l_response = getDownloadFile((WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest)l_request);
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request
                );
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * �Ïؔԍ��ύX�ڋq�_�E�����[�h�⍇����ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�Ïؔԍ��ύX�ڋq�c�k�jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B8BC70338
     */
    protected WEB3AdminAccInfoPasswordChangeAccountInquiryResponse getInputScreen(WEB3AdminAccInfoPasswordChangeAccountInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoPasswordChangeAccountInquiryRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //�Ǘ��҂̌����`�F�b�N���s���B 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_PASSWORD, false);
               
        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAccInfoPasswordChangeAccountInquiryResponse l_response = (WEB3AdminAccInfoPasswordChangeAccountInquiryResponse)l_request.createResponse();
        
        Timestamp l_tsCurrentDate = GtlUtils.getSystemTimestamp();
        
        //�O�c�Ɠ��F�@@TradingSystem.getSystemTimestamp()�̑O�c�Ɠ�
        l_response.previousBizDate = WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1);

        //�O���F�@@TradingSystem.getSystemTimestamp()�̑O��
        l_response.previousDate = WEB3DateUtility.addDay(l_tsCurrentDate, -1);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h���)<BR>
     * �Ïؔԍ��ύX�ڋq�_�E�����[�h��ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�Ïؔԍ��ύX�ڋq�c�k�jget�_�E�����[�h��ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B8BC70357
     */
    protected WEB3AdminAccInfoPasswordChangeAccountDownloadResponse getDownloadScreen(WEB3AdminAccInfoPasswordChangeAccountDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadScreen(WEB3AdminAccInfoPasswordChangeAccountDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //�Ǘ��҂̌����`�F�b�N���s���B 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_PASSWORD, false);
        
        //���X�������`�F�b�N����B 
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //�،���ЃR�[�h���擾����B 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //�_�E�����[�h�Ώۃf�[�^���擾����B
        WEB3AccInfoPasswordChangeAccountInfo[] l_passwordChangeAccountInfoes = getDownloadData(
                l_strInstitutionCode,       //�،���ЃR�[�h
                l_request.branchCode,       //���X�R�[�h[]
                l_request.startDate,        //�J�n��
                l_request.endDate,          //�I����
                l_request.sortKeys          //�\�[�g�L�[
                ); 
                
        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAccInfoPasswordChangeAccountDownloadResponse l_response = (WEB3AdminAccInfoPasswordChangeAccountDownloadResponse)l_request.createResponse();
        
        //���X�|���X�f�[�^�v���p�e�B�Ƀp�X���[�h�ύX�ڋq���ꗗ�C���y�[�W���C�����R�[�h���C�\���y�[�W�ԍ����Z�b�g����B
        int l_intpageIndex = Integer.parseInt(l_request.pageIndex); 
        int l_intpageSize = Integer.parseInt(l_request.pageSize); 
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_passwordChangeAccountInfoes, l_intpageIndex, l_intpageSize); 

        //���X�|���X�f�[�^.�p�X���[�h�ύX�ڋq���ꗗ�F�@@get�_�E�����[�h�f�[�^()�̖߂�l�̂����A�\���Ώۍs(fromIndex�`toIndex)�̗v�f�B
        l_response.passwordChangeAccountList = (WEB3AccInfoPasswordChangeAccountInfo[])l_pageIndexInfo.getArrayReturned(WEB3AccInfoPasswordChangeAccountInfo.class);

        //���X�|���X�f�[�^.���y�[�W���F�@@�i�����R�[�h�� / ���N�G�X�g�f�[�^.�y�[�W���\���s���j���v�Z���ʂ͏����_�ȉ�1�ʂ�؂�グ�������l�Ƃ���B
        l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();

        //���X�|���X�f�[�^.�����R�[�h���F�@@get�_�E�����[�h�f�[�^()�̖߂�l.length()
        l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
        
        //���X�|���X�f�[�^.�\���y�[�W�ԍ��F�@@�itoIndex / ���N�G�X�g�f�[�^.�y�[�W���\���s���j���v�Z���ʂ͏����_�ȉ�1�ʂ�؂�グ�������l�Ƃ���B
        l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �Ïؔԍ��ύX�ڋq�_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�Ïؔԍ��ύX�ڋq�c�k�jget�_�E�����[�h�t�@@�C���v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B8BC70376
     */
    protected WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse getDownloadFile(WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //�Ǘ��҂̌����`�F�b�N���s���B 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_PASSWORD, false);
        
        //���X�������`�F�b�N����B 
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //�،���ЃR�[�h���擾����B 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //�_�E�����[�h�Ώۃf�[�^���擾����B
        WEB3AccInfoPasswordChangeAccountInfo[] l_passwordChangeAccountInfoes = getDownloadData(
                l_strInstitutionCode,       //�،���ЃR�[�h
                l_request.branchCode,       //���X�R�[�h[]
                l_request.startDate,        //�J�n��
                l_request.endDate,          //�I����
                l_request.sortKeys          //�\�[�g�L�[
                ); 
 
        WEB3AdminAccInfoPasswordChangeAccountCsv l_passwordChangeAccountCsv = new WEB3AdminAccInfoPasswordChangeAccountCsv();
        
        //�擾�����_�E�����[�h�f�[�^�iget�_�E�����[�h�f�[�^()�̖߂�l�j�e�v�f���Ƃ�LOOP
        for (int i = 0; i < l_passwordChangeAccountInfoes.length; i++)
        {
            //���׍s��ǉ�����B 
            int l_intLineNum = l_passwordChangeAccountCsv.addRow();
            
            //�،���ЃR�[�h�𖾍׍s�ɃZ�b�g����B 
            l_passwordChangeAccountCsv.setInstitutionCode(l_intLineNum, l_passwordChangeAccountInfoes[i].institutionCode);

            //���X�R�[�h�𖾍׍s�ɃZ�b�g����B 
            l_passwordChangeAccountCsv.setBranchCode(l_intLineNum, l_passwordChangeAccountInfoes[i].branchCode);

            //�ڋq�R�[�h�𖾍׍s�ɃZ�b�g����B 
            l_passwordChangeAccountCsv.setAccountCode(l_intLineNum, l_passwordChangeAccountInfoes[i].accountCode);
            
            //�ڋq���𖾍׍s�ɃZ�b�g����B 
            l_passwordChangeAccountCsv.setAccountName(l_intLineNum, l_passwordChangeAccountInfoes[i].accountName);

            //�Ïؔԍ��X�V���𖾍׍s�ɃZ�b�g����B 
            l_passwordChangeAccountCsv.setPasswordUpdatedDate(l_intLineNum, l_passwordChangeAccountInfoes[i].updateDate);

            //�Ïؔԍ��X�V�҃R�[�h�𖾍׍s�ɃZ�b�g����B 
            l_passwordChangeAccountCsv.setPasswordUpdaterCode(l_intLineNum, l_passwordChangeAccountInfoes[i].updaterCode);            
        }
        
        //CSV�t�@@�C���s���擾����B
        String[] l_strCscFileLines = l_passwordChangeAccountCsv.getCsvFileLines();
        
        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse l_response = (WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse)l_request.createResponse();
 
        //���X�|���X�f�[�^.�_�E�����[�h�t�@@�C���F�@@getCSV�t�@@�C���s()�̖߂�l
        l_response.downloadFile = l_strCscFileLines;
        
        //���X�|���X�f�[�^.���ݓ����F�@@TradingSystem.getSystemTimestamp()
        l_response.currentDate = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�f�[�^)<BR>
     * �ڋq�}�X�^�e�[�u�����A�_�E�����[�h�Ώۃf�[�^���擾����B<BR>
     * <BR>
     * �P�j�@@�ڋq�}�X�^�e�[�u������<BR>
     * �@@�ڋq�}�X�^�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�ڋq�}�X�^.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     * �@@�ڋq�}�X�^.���X�R�[�h in �i���X�R�[�h[0]�C���X�R�[�h[1]�C�C�j And <BR>
     * �� �����̕��X�R�[�h[]�̗v�f��񋓂���B<BR>
     * �@@�ڋq�}�X�^.����p�X���[�h�X�V���� >= �J�n���@@And<BR>
     * �@@�ڋq�}�X�^.����p�X���[�h�X�V���� < �I�����̗���<BR>
     * <BR>
     * �@@[�擾���iorder by�j]<BR>
     * �@@���@@�����̃\�[�g�L�[.�L�[���ڂ��������ځ^�����ɂĎ擾����B<BR>
     * �@@�\�[�g�L�[.�L�[���ڂɑΉ�����ڋq�}�X�^�̍��ڂ͈ȉ��̒ʂ�Ƃ���B<BR>
     * <BR>
     * �@@�i�\�[�g�L�[.�L�[���� == ���X�R�[�h�j�F�@@�ڋq�}�X�^.���X�R�[�h<BR>
     * �@@�i�\�[�g�L�[.�L�[���� == �ڋq�R�[�h�j�F�@@�ڋq�}�X�^.�����R�[�h<BR>
     * �@@�i�\�[�g�L�[.�L�[���� == �X�V���j�F�@@�ڋq�}�X�^.����p�X���[�h�X�V����<BR>
     * <BR>
     * �Q�j�@@�ڋq�Ïؔԍ����ꗗList�i�FArrayList�j����<BR>
     * �@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�ڋq���[���A�h���X��񐶐�<BR>
     * �@@�P�j�Ŏ擾�����e�s�I�u�W�F�N�g�i�F�ڋq�}�X�^Params�j���ɁA<BR>
     * �R�|�P�j�`�R�|�Q�j�̏������s���B<BR>
     * <BR>
     * �@@�R�|�P�j�@@�ڋq���[���A�h���X���𐶐����A�ȉ��̒ʂ�v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�ڋq�Ïؔԍ����.�،���ЃR�[�h = �ڋq�}�X�^�s.�،���ЃR�[�h<BR>
     * �@@�@@�ڋq�Ïؔԍ����.���X�R�[�h = �ڋq�}�X�^�s.���X�R�[�h<BR>
     * �@@�@@�ڋq�Ïؔԍ����.�ڋq�R�[�h = �ڋq�}�X�^�s.�����R�[�h�̍�6byte<BR>
     * �@@�@@�ڋq�Ïؔԍ����.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j�@@<BR>
     * ���ڋq���i�����j�Ƃ��Ďg�p<BR>
     * �@@�@@�ڋq�Ïؔԍ����.�X�V�� = �ڋq�}�X�^�s.����p�X���[�h�X�V����<BR>
     * �@@�@@�ڋq�Ïؔԍ����.�X�V�҃R�[�h = (*1)<BR>
     * <BR>
     * �@@�@@�@@(*1) �X�V�҃R�[�h<BR>
     * �@@�@@�@@�i�ڋq�}�X�^�s.����p�X���[�h�X�V�҃R�[�h == �ڋq�}�X�^�s.�����R�[�h�j�̏ꍇ�A<BR>
     * �����R�[�h�̍�6byte�B<BR>
     * �@@�@@�@@�ȊO�A�ڋq�}�X�^�s.����p�X���[�h�X�V�҃R�[�h�A<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�ڋq�Ïؔԍ����ꗗList�i�FArrayList�j�ɃI�u�W�F�N�g��ǉ�����B<BR>
     * �@@�@@�R�|�P�j�Ő��������I�u�W�F�N�g���ڋq�Ïؔԍ����ꗗList<BR>
     * �i�FArrayList�j�ɒǉ��iadd�j����B<BR>
     * <BR>
     * �S�j�@@�_�E�����[�h�f�[�^�ԋp<BR>
     * �@@�ڋq�Ïؔԍ����ꗗList�i�FArrayList�j��z��ɕϊ��itoArray()�j���A<BR>
     * �ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCodes - ���X�R�[�h�z��
     * 
     * @@param l_datStartDate - �J�n��
     * @@param l_datEndDate - �I����
     * @@param l_sortKeys - �\�[�g�L�[
     * @@return webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeAccountInfo[]
     * @@roseuid 416B8BC70395
     */
    protected WEB3AccInfoPasswordChangeAccountInfo[] getDownloadData(String l_strInstitutionCode, String[] l_strBranchCodes, Date l_datStartDate, Date l_datEndDate, WEB3AccInfoSortKey[] l_sortKeys)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadData(String, String[], Date, Date, WEB3AccInfoSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�ڋq�}�X�^�e�[�u�����ȉ��̏����Ō�������B
            //[����]
            //�ڋq�}�X�^.�،���ЃR�[�h = �،���ЃR�[�h And
            //�ڋq�}�X�^.���X�R�[�h in �i���X�R�[�h[0]�C���X�R�[�h[1]�C�C�j And �� �����̕��X�R�[�h[]�̗v�f��񋓂���B
            //�ڋq�}�X�^.����p�X���[�h�X�V���� >= �J�n���@@And
            //�ڋq�}�X�^.����p�X���[�h�X�V���� < �I�����̗���
            
            int l_intBranchCodesCnt = l_strBranchCodes.length;
            
            Object[] l_queryContainer = new Object[l_intBranchCodesCnt + 3];
            String l_strQuery = "institution_code = ? ";
            l_queryContainer[0] = l_strInstitutionCode;            
            
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
                
                    l_queryContainer[i + 1] = l_strBranchCodes[i]; 
                }
                
                l_strQuery += " and branch_code in (" + l_sbQueryBranchCodes.toString() + ")";             
            }
                        
            l_strQuery += " and tr_pwd_last_update_timestamp >= ?  and tr_pwd_last_update_timestamp < ?";
            l_queryContainer[l_intBranchCodesCnt + 1] = l_datStartDate;
            l_queryContainer[l_intBranchCodesCnt + 2] = WEB3DateUtility.addDay(l_datEndDate, 1);
            
            //[�擾���iorder by�j] 
            //�i�\�[�g�L�[.�L�[���� == ���X�R�[�h�j�F�@@�ڋq�}�X�^.���X�R�[�h 
            //�i�\�[�g�L�[.�L�[���� == �ڋq�R�[�h�j�F�@@�ڋq�}�X�^.�����R�[�h 
            //�i�\�[�g�L�[.�L�[���� == �X�V���j�F�@@�ڋq�}�X�^.����p�X���[�h�X�V���� 
            StringBuffer l_sbOrderBy = new StringBuffer();
            for (int i = 0; i < l_sortKeys.length; i++)
            {
                String l_strKey = null;
                if (WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_strKey = "account_code";
                }
                else if (WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_strKey = "branch_code";
                }
                else if (WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(l_sortKeys[i].keyItem))
                {
                    l_strKey = "tr_pwd_last_update_timestamp";
                }
                
                String l_strAscDesc = null;
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strAscDesc = "asc";
                }
                else
                {
                    l_strAscDesc = "desc";
                }
                
                if (l_strKey != null)
                {
                    if (l_sbOrderBy.length() != 0)
                    {
                        l_sbOrderBy.append(", ");
                    }
                    l_sbOrderBy.append(l_strKey + " " + l_strAscDesc);
                }
            }
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                MainAccountRow.TYPE,
                l_strQuery,
                l_sbOrderBy.toString(),
                null,
                l_queryContainer
                );
               
            //�ڋq�Ïؔԍ����ꗗList�i�FArrayList�j����
            List l_lisPsswordChangeAccountInfoes = new ArrayList();
            
            int l_intSize = l_lisRecords.size();            
            for (int i = 0; i < l_intSize; i++)
            {
                MainAccountRow l_mainAccountRow = (MainAccountRow)l_lisRecords.get(i);
                
                //�ڋq�Ïؔԍ����𐶐����A�ȉ��̒ʂ�v���p�e�B���Z�b�g����B
                WEB3AccInfoPasswordChangeAccountInfo l_passwordChangeAccountInfo = new WEB3AccInfoPasswordChangeAccountInfo();
                
                //�ڋq�Ïؔԍ����.�،���ЃR�[�h = �ڋq�}�X�^�s.�،���ЃR�[�h
                l_passwordChangeAccountInfo.institutionCode = l_mainAccountRow.getInstitutionCode();
                
                //�ڋq�Ïؔԍ����.���X�R�[�h = �ڋq�}�X�^�s.���X�R�[�h
                l_passwordChangeAccountInfo.branchCode = l_mainAccountRow.getBranchCode();
                
                //�ڋq�Ïؔԍ����.�ڋq�R�[�h = �ڋq�}�X�^�s.�����R�[�h�̍�6byte
                l_passwordChangeAccountInfo.accountCode = l_mainAccountRow.getAccountCode().substring(0, 6);
                
                //�ڋq�Ïؔԍ����.�ڋq�� = �ڋq�}�X�^�s.���O�i�c���j�@@���ڋq���i�����j�Ƃ��Ďg�p
                l_passwordChangeAccountInfo.accountName = l_mainAccountRow.getFamilyName();
                
                //�ڋq�Ïؔԍ����.�X�V�� = �ڋq�}�X�^�s.����p�X���[�h�X�V����
                l_passwordChangeAccountInfo.updateDate = l_mainAccountRow.getTrPwdLastUpdateTimestamp();
                
                //�ڋq�Ïؔԍ����.�X�V�҃R�[�h = �X�V�҃R�[�h
                //�i�ڋq�}�X�^�s.����p�X���[�h�X�V�҃R�[�h == �ڋq�}�X�^�s.�����R�[�h�j�̏ꍇ�A�����R�[�h�̍�6byte�B
                //�ȊO�A�ڋq�}�X�^�s.����p�X���[�h�X�V�҃R�[�h�A 
                
                if (l_mainAccountRow.getAccountCode().equals(l_mainAccountRow.getTradingPasswordUpdater()))
                {
                    l_passwordChangeAccountInfo.updaterCode = l_mainAccountRow.getAccountCode().substring(0, 6);
                }
                else
                {
                    l_passwordChangeAccountInfo.updaterCode = l_mainAccountRow.getTradingPasswordUpdater();
                }
                
                l_lisPsswordChangeAccountInfoes.add(l_passwordChangeAccountInfo);
            }
            
            WEB3AccInfoPasswordChangeAccountInfo[] l_passwordChangeAccountInfoes =
                new WEB3AccInfoPasswordChangeAccountInfo[l_lisPsswordChangeAccountInfoes.size()];
            l_lisPsswordChangeAccountInfoes.toArray(l_passwordChangeAccountInfoes);
                    
            log.exiting(STR_METHOD_NAME);
            
            return l_passwordChangeAccountInfoes;
            
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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
