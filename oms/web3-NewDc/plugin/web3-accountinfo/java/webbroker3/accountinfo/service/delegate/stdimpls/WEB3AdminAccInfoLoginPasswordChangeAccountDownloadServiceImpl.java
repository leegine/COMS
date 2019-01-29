head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽Impl(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoLoginPasswordChangeAccountCsv;
import webbroker3.accountinfo.WEB3AdminAccInfoLoginPasswordChangeAccountInfoAccountCodeComparator;
import webbroker3.accountinfo.WEB3AdminAccInfoLoginPasswordChangeAccountInfoBranchCodeComparator;
import webbroker3.accountinfo.WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.message.WEB3AccInfoLoginPasswordChangeAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3Toolkit;

/**
 * (�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽Impl)<BR>
 * �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordChangeAccountDownloadServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadServiceImpl.class); 
    
    /**
     * @@roseuid 418F3A010177
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountDownloadServiceImpl() 
    {
     
    }
    
    /**
     * �p�X���[�h�ύX�ڋq�_�E�����[�h���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���p�X���[�h�ύX�ڋq<BR>
     * �⍇��ظ��Ă̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * 
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���p�X���[�h�ύX�ڋq<BR>
     * �޳�۰��ظ��Ă̏ꍇ <BR>
     * �@@�|get�_�E�����[�h���()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���p�X���[�h�ύX�ڋq<BR>
     * ̧���޳�۰��ظ��Ă̏ꍇ <BR>
     * �@@�|get�_�E�����[�h�t�@@�C��()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146D68F0254
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest)
        {
            l_response = getInputScreen((WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest)
        {
            l_response = getDownloadScreen((WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest)
        {
            l_response = getDownloadFile((WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest)l_request);
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
     * �p�X���[�h�ύX�ڋq�_�E�����[�h�⍇����ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�p�X���[�h�ύX�ڋq�c�k�jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A5ADA032B
     */
    protected WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse getInputScreen(WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //�Ǘ��҂̌����`�F�b�N���s���B 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_PASSWORD, false);
               
        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse)l_request.createResponse();
        
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
     * �p�X���[�h�ύX�ڋq�_�E�����[�h��ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�p�X���[�h�ύX�ڋq�c�k�jget�_�E�����[�h��ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146D68F0264
     */
    protected WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse getDownloadScreen(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadScreen(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest l_request)";
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
        WEB3AccInfoLoginPasswordChangeAccountInfo[] l_loginPasswordChangeAccountInfoes = getDownloadData(
                l_strInstitutionCode,       //�،���ЃR�[�h
                l_request.branchCode,       //���X�R�[�h[]
                l_request.startDate,        //�J�n��
                l_request.endDate,          //�I����
                l_request.sortKeys          //�\�[�g�L�[
                ); 
                
        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse)l_request.createResponse();
        
        //���X�|���X�f�[�^�v���p�e�B�Ƀp�X���[�h�ύX�ڋq���ꗗ�C���y�[�W���C�����R�[�h���C�\���y�[�W�ԍ����Z�b�g����B
        int l_intpageIndex = Integer.parseInt(l_request.pageIndex); 
        int l_intpageSize = Integer.parseInt(l_request.pageSize); 
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_loginPasswordChangeAccountInfoes, l_intpageIndex, l_intpageSize); 

        //���X�|���X�f�[�^.�p�X���[�h�ύX�ڋq���ꗗ�F�@@get�_�E�����[�h�f�[�^()�̖߂�l�̂����A�\���Ώۍs(fromIndex�`toIndex)�̗v�f�B
        l_response.loginPasswordChangeAccountList = (WEB3AccInfoLoginPasswordChangeAccountInfo[])l_pageIndexInfo.getArrayReturned(WEB3AccInfoLoginPasswordChangeAccountInfo.class);

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
     * �p�X���[�h�ύX�ڋq�_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�p�X���[�h�ύX�ڋq�c�k�jget�_�E�����[�h�t�@@�C���v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146D68F0266
     */
    protected WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse getDownloadFile(WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest l_request)";
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
        WEB3AccInfoLoginPasswordChangeAccountInfo[] l_loginPasswordChangeAccountInfoes = getDownloadData(
                l_strInstitutionCode,       //�،���ЃR�[�h
                l_request.branchCode,       //���X�R�[�h[]
                l_request.startDate,        //�J�n��
                l_request.endDate,          //�I����
                l_request.sortKeys          //�\�[�g�L�[
                ); 
 
        WEB3AdminAccInfoLoginPasswordChangeAccountCsv l_loginPasswordChangeAccountCsv = new WEB3AdminAccInfoLoginPasswordChangeAccountCsv();
        
        //�擾�����_�E�����[�h�f�[�^�iget�_�E�����[�h�f�[�^()�̖߂�l�j�e�v�f���Ƃ�LOOP
        for (int i = 0; i < l_loginPasswordChangeAccountInfoes.length; i++)
        {
            //���׍s��ǉ�����B 
            int l_intLineNum = l_loginPasswordChangeAccountCsv.addRow();
            
            //�،���ЃR�[�h�𖾍׍s�ɃZ�b�g����B 
            l_loginPasswordChangeAccountCsv.setInstitutionCode(l_intLineNum, l_loginPasswordChangeAccountInfoes[i].institutionCode);

            //���X�R�[�h�𖾍׍s�ɃZ�b�g����B 
            l_loginPasswordChangeAccountCsv.setBranchCode(l_intLineNum, l_loginPasswordChangeAccountInfoes[i].branchCode);

            //�ڋq�R�[�h�𖾍׍s�ɃZ�b�g����B 
            l_loginPasswordChangeAccountCsv.setAccountCode(l_intLineNum, l_loginPasswordChangeAccountInfoes[i].accountCode);
            
            //�ڋq���𖾍׍s�ɃZ�b�g����B 
            l_loginPasswordChangeAccountCsv.setAccountName(l_intLineNum, l_loginPasswordChangeAccountInfoes[i].accountName);

            //�p�X���[�h�X�V���𖾍׍s�ɃZ�b�g����B 
            l_loginPasswordChangeAccountCsv.setLoginPasswordUpdatedDate(l_intLineNum, l_loginPasswordChangeAccountInfoes[i].updateDate);

            //�p�X���[�h�X�V�҃R�[�h�𖾍׍s�ɃZ�b�g����B 
            l_loginPasswordChangeAccountCsv.setLoginPasswordUpdaterCode(l_intLineNum, l_loginPasswordChangeAccountInfoes[i].updaterCode);
            
        }
        
        //CSV�t�@@�C���s���擾����B
        String[] l_strCscFileLines = l_loginPasswordChangeAccountCsv.getCsvFileLines();
        
        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse)l_request.createResponse();
 
        //���X�|���X�f�[�^.�_�E�����[�h�t�@@�C���F�@@getCSV�t�@@�C���s()�̖߂�l
        l_response.downloadFile = l_strCscFileLines;
        
        //���X�|���X�f�[�^.���ݓ����F�@@TradingSystem.getSystemTimestamp()
        l_response.currentDate = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�f�[�^)<BR>
     * ���O�C�������e�[�u���C���O�C���e�[�u���C�ڋq�}�X�^�e�[�u�����A<BR>
     * �_�E�����[�h�Ώۃf�[�^���擾����B<BR>
     * <BR>
     * �P�j�@@���O�C�������e�[�u������<BR>
     * �@@���O�C�������e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@���O�C������.���O�C�������� = ���O�C��������.�O��p�X���[�h�ύX���t<BR>
     * �iLAST_PASSWORDCHANGE_DATE�j�@@And<BR>
     * �@@���O�C������.���O�C�������l >= �J�n�����@@And<BR>
     * �@@���O�C������.���O�C�������l < �I�����̗�����<BR>
     * <BR>
     * �@@�� �J�n���C�I�����̓��t�����͈ȉ��̒ʂ�B���ԕ����́A<BR>
     * 0��0��0�b�ŕҏW����B<BR>
     * �@@YYYY.MM.DD HH24:MI:SS<BR>
     * <BR>
     * �Q�j�@@�p�X���[�h�ύX�ڋq���ꗗList�i�FArrayList�j����<BR>
     * �@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�p�X���[�h�ύX�ڋq��񐶐�<BR>
     * �@@�P�j�Ŏ擾�����e�s�I�u�W�F�N�g�i�F���O�C������Params�j���ɁA<BR>
     * �R�|�P�j�`�R�|�S�j�̏������s���B<BR>
     * <BR>
     * �@@�R�|�P�j�@@�ڋq�����擾<BR>
     * �@@�@@���O�C�������s.���O�C���h�c�ɊY������s�����O�C���e�[�u�����擾����B<BR>
     * �@@�@@�擾�������O�C���s.�����h�c�ɊY������ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�ڋq���擾�ł��Ȃ������ꍇ�A�܂��́A<BR>
     * �@@�@@�擾�����ڋq�̏،���ЃR�[�h�������̏،���ЃR�[�h�Ɉ�v���Ȃ��ꍇ�C<BR>
     * �@@�@@�܂��́A�����̕��X�R�[�h[]�Ɋ܂܂�Ȃ����X�R�[�h�̏ꍇ�́A<BR>
     * �@@�@@�Y���v�f�Ɋւ��ĂR�|�Q�j�C�R�|�R�j�C�R�|�S�j�̏������s��Ȃ��B�icontinue;�j<BR>
     * <BR>
     * �@@�R�|�Q�j�@@���O�C���h�c�ɊY�����郍�O�C�������擾<BR>
     * �@@�@@OpLoginAdminService.getLoginAttributes()�ɂāA<BR>
     * �@@�@@���O�C�������s.���O�C���h�c�ɊY������A�O��p�X���[�h�ύX���t�ȊO��<BR>
     * ���O�C�������i�FMap�j���擾����B<BR>
     * <BR>
     * �@@�R�|�R�j�@@�p�X���[�h�ύX�ڋq���𐶐����A�ȉ��̒ʂ�v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�p�X���[�h�ύX�ڋq���.�،���ЃR�[�h = �ڋq.�،���ЃR�[�h<BR>
     * �@@�@@�p�X���[�h�ύX�ڋq���.���X�R�[�h = �ڋq.���X�R�[�h<BR>
     * �@@�@@�p�X���[�h�ύX�ڋq���.�ڋq�R�[�h = �ڋq.get�\���ڋq�R�[�h()<BR>
     * �@@�@@�p�X���[�h�ύX�ڋq���.�ڋq�� = �ڋq.���O�i�c���j�@@<BR>
     * ���ڋq���i�����j�Ƃ��Ďg�p<BR>
     * �@@�@@�p�X���[�h�ύX�ڋq���.�X�V�� = <BR>
     * �@@�@@�@@�O��p�X���[�h�ύX���t�iLAST_PASSWORDCHANGE_DATE�j��<BR>
     * �Ή����郍�O�C�������l��<BR>
     * �@@�@@�p�X���[�h�ύX�ڋq���.�X�V�҃R�[�h = <BR>
     * �@@�@@�@@�O��p�X���[�h�X�V�҃R�[�h�iLAST_PASSWORDCHANGE_UPDATER�j<BR>
     * �ɑΉ����郍�O�C�������l��<BR>
     * �@@�@@�@@�A���A�i���O�C�������l == �ڋq�R�[�h�j�̏ꍇ�A<BR>
     * �ڋq.get�\���ڋq�R�[�h()�B<BR>
     * <BR>
     * �@@�@@�� �R�|�Q�j�Ŏ擾�������O�C�������i�FMap�j�̃��O�C�������l�B<BR>
     * <BR>
     * �@@�R�|�S�j�@@�p�X���[�h�ύX�ڋq���ꗗList�i�FArrayList�j�ɃI�u�W�F�N�g��<BR>
     * �ǉ�����B<BR>
     * �@@�@@�R�|�R�j�Ő��������I�u�W�F�N�g���p�X���[�h�ύX�ڋq���ꗗList<BR>
     * �i�FArrayList�j�ɒǉ��iadd�j����B<BR>
     * <BR>
     * �S�j�@@�ԋp�l����<BR>
     * �@@�p�X���[�h�ύX�ڋq���ꗗList�i�FArrayList�j���A<BR>
     * WEB3ArraysUtility.sort()�ɂ�sort����B <BR>
     * �@@sort��̔z���ԋp����B<BR>
     * <BR>
     * �@@[sort()�Ɏw�肷�����]<BR>
     * �@@Object[]�F�@@�p�X���[�h�ύX�ڋq���ꗗList�i�FArrayList�j.toArray()<BR>
     * �@@Comparator[]�F�@@ ��<BR>
     * <BR>
     * �@@�@@���@@�����̃\�[�g�L�[.�L�[���ڂ�����Comparator�I�u�W�F�N�g��z���<BR>
     * �����ɂĎw�肷��B<BR>
     * �@@�@@�߽ܰ�ޕύX�ڋq���.���X�R�[�hComparator(�\�[�g�L�[.�����^�~��)<BR>
     * �@@�@@�߽ܰ�ޕύX�ڋq���.�ڋq�R�[�hComparator(�\�[�g�L�[.�����^�~��)<BR>
     * �@@�@@�߽ܰ�ޕύX�ڋq���.�X�V��Comparator(�\�[�g�L�[.�����^�~��)<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCodes - ���X�R�[�h�z��
     * 
     * @@param l_datStartDate - �J�n��
     * @@param l_datEndDate - �I����
     * @@param l_sortKeys - �\�[�g�L�[
     * @@return webbroker3.accountinfo.message.WEB3AccInfoLoginPasswordChangeAccountInfo[]
     * @@roseuid 4146D68F0268
     */
    protected WEB3AccInfoLoginPasswordChangeAccountInfo[] getDownloadData(String l_strInstitutionCode, String[] l_strBranchCodes, Date l_datStartDate, Date l_datEndDate, WEB3AccInfoSortKey[] l_sortKeys)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadData(String, String[], Date, Date, WEB3AccInfoSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //���O�C�������e�[�u������
            //[����] 
            //���O�C������.���O�C�������� = ���O�C��������.�O��p�X���[�h�ύX���t�iLAST_PASSWORDCHANGE_DATE�j�@@And 
            //���O�C������.���O�C�������l >= �J�n�����@@And 
            //���O�C������.���O�C�������l < �I�����̗����� 
            
            String l_strQuery = "attribute_name = ? ";
            l_strQuery += " and attribute_value >= ?  and attribute_value < ?";
        
            Object[] l_queryContainer = new Object[] {
                WEB3LoginAttributeKeyDef.LAST_PWDCHANGE,
                WEB3DateUtility.formatDate(l_datStartDate, "yyyy.MM.dd HH:mm:ss"),
                WEB3DateUtility.formatDate(WEB3DateUtility.addDay(l_datEndDate, 1), "yyyy.MM.dd HH:mm:ss")
                };
                        
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                LoginAttributeRow.TYPE,
                l_strQuery,
                l_queryContainer
                );
                
            List l_lisLoginPasswordChangeAccountInfoes = new ArrayList();
            
            OpLoginAdminService l_opLoginAdminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            
            int l_intSize = l_lisRecords.size();            
            for (int i = 0; i < l_intSize; i++)
            {
                LoginAttributeRow l_loginAttrRow = (LoginAttributeRow)l_lisRecords.get(i);
                
                long l_lngLoginId = l_loginAttrRow.getLoginId();
                
                //�R�|�P�j�@@�ڋq�����擾 
                WEB3GentradeMainAccount l_mainAccount = null;
                try
                {
                    //���O�C�������s.���O�C���h�c�ɊY������s�����O�C���e�[�u�����擾����B
                    LoginRow l_loginRow = LoginDao.findRowByPk(l_lngLoginId);
                     
                    //�擾�������O�C���s.�����h�c�ɊY������ڋq�I�u�W�F�N�g���擾����B
                    l_mainAccount = (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_loginRow.getAccountId());
                    
                }
                catch (DataFindException l_ex)
                {
                    l_mainAccount = null;                    
                }
                catch (NotFoundException l_ex)
                {
                    l_mainAccount = null;
                } 
                
                if (l_mainAccount == null 
                    || !l_strInstitutionCode.equals(l_mainAccount.getInstitution().getInstitutionCode())
                    || !WEB3Toolkit.contain(l_strBranchCodes, l_mainAccount.getBranch().getBranchCode()))
                {
                    //�ڋq���擾�ł��Ȃ������ꍇ�A�܂��́A 
                    //�擾�����ڋq�̏،���ЃR�[�h�������̏،���ЃR�[�h�Ɉ�v���Ȃ��ꍇ�C 
                    //�܂��́A�����̕��X�R�[�h[]�Ɋ܂܂�Ȃ����X�R�[�h�̏ꍇ
                    
                    continue;
                }
                
                //�@@�R�|�Q�j�@@���O�C���h�c�ɊY�����郍�O�C�������擾 
                Map l_loginAttr = l_opLoginAdminService.getLoginAttributes(l_lngLoginId);

                //�p�X���[�h�X�V����
                Date l_loginPasswordUpdatedDate = null;
                String l_strDate = (String)l_loginAttr.get(WEB3LoginAttributeKeyDef.LAST_PWDCHANGE);
                if (l_strDate != null)
                {
                    try
                    {
                        l_loginPasswordUpdatedDate = WEB3PasswordUtility.loginAttributeDateFormat.parse(l_strDate);
                    }
                    catch (ParseException l_ex)
                    {
                        log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                }

                MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

                //�p�X���[�h�ύX�ڋq���𐶐����A�ȉ��̒ʂ�v���p�e�B���Z�b�g����B
                WEB3AccInfoLoginPasswordChangeAccountInfo l_loginPasswordChangeAccountInfo = new WEB3AccInfoLoginPasswordChangeAccountInfo();
                
                //�p�X���[�h�ύX�ڋq���.�،���ЃR�[�h = �ڋq.�،���ЃR�[�h
                l_loginPasswordChangeAccountInfo.institutionCode = l_mainAccountRow.getInstitutionCode();
                
                //�p�X���[�h�ύX�ڋq���.���X�R�[�h = �ڋq.���X�R�[�h
                l_loginPasswordChangeAccountInfo.branchCode = l_mainAccountRow.getBranchCode();
                
                //�p�X���[�h�ύX�ڋq���.�ڋq�R�[�h = �ڋq.get�\���ڋq�R�[�h() 
                l_loginPasswordChangeAccountInfo.accountCode = l_mainAccount.getDisplayAccountCode();
                
                //�p�X���[�h�ύX�ڋq���.�ڋq�� = �ڋq.���O�i�c���j�@@���ڋq���i�����j�Ƃ��Ďg�p
                l_loginPasswordChangeAccountInfo.accountName = l_mainAccountRow.getFamilyName();
                
                //�p�X���[�h�ύX�ڋq���.�X�V�� = �O��p�X���[�h�ύX���t�iLAST_PASSWORDCHANGE_DATE�j�ɑΉ����郍�O�C�������l
                l_loginPasswordChangeAccountInfo.updateDate = l_loginPasswordUpdatedDate;
                 
                //�p�X���[�h�ύX�ڋq���.�X�V�҃R�[�h = �O��p�X���[�h�X�V�҃R�[�h�iLAST_PASSWORDCHANGE_UPDATER�j�ɑΉ����郍�O�C�������l
                //�A���A�i���O�C�������l == �ڋq�R�[�h�j�̏ꍇ�A�ڋq.get�\���ڋq�R�[�h()�B 
                String l_strUpdaterCode = (String)l_loginAttr.get(WEB3LoginAttributeKeyDef.LAST_PASSWORDCHANGE_UPDATER);
                if (l_mainAccount.getAccountCode().equals(l_strUpdaterCode))
                {
                    l_loginPasswordChangeAccountInfo.updaterCode = l_mainAccount.getDisplayAccountCode();
                }
                else
                {
                    l_loginPasswordChangeAccountInfo.updaterCode = l_strUpdaterCode;
                }

                l_lisLoginPasswordChangeAccountInfoes.add(l_loginPasswordChangeAccountInfo);
            }
            

            WEB3AccInfoLoginPasswordChangeAccountInfo[] l_loginPasswordChangeAccountInfoes =
                new WEB3AccInfoLoginPasswordChangeAccountInfo[l_lisLoginPasswordChangeAccountInfoes.size()];
            l_lisLoginPasswordChangeAccountInfoes.toArray(l_loginPasswordChangeAccountInfoes);
        
            List l_lisComparators = new ArrayList();
            for (int i = 0; i < l_sortKeys.length; i++)
            {
                if (WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_lisComparators.add(new WEB3AdminAccInfoLoginPasswordChangeAccountInfoAccountCodeComparator(l_sortKeys[i].ascDesc));                
                }
                else if (WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_lisComparators.add(new WEB3AdminAccInfoLoginPasswordChangeAccountInfoBranchCodeComparator(l_sortKeys[i].ascDesc));
                }
                else if (WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(l_sortKeys[i].keyItem))
                {
                    l_lisComparators.add(new WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator(l_sortKeys[i].ascDesc));
                }
            }
            Comparator[] l_comparator = new Comparator[l_lisComparators.size()];
            l_lisComparators.toArray(l_comparator);
        
            WEB3ArraysUtility.sort(l_loginPasswordChangeAccountInfoes, l_comparator);
            
            log.exiting(STR_METHOD_NAME);
            
            return l_loginPasswordChangeAccountInfoes;
            
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
