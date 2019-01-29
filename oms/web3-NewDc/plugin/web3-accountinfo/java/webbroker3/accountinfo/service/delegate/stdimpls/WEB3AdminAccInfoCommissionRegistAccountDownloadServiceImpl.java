head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽Impl(WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �d�� (���u) �V�K�쐬
Revesion History : 2008/08/18 ������ (���u) �d�l�ύX�E���f��No.241
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseMaster;
import webbroker3.accountinfo.WEB3AdminAccInfoCommissionChangeAccountCsv;
import webbroker3.accountinfo.data.CommissionCourseRegistPK;
import webbroker3.accountinfo.data.CommissionCourseRegistParams;
import webbroker3.accountinfo.data.CommissionCourseRegistRow;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionChangeAccountInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3CommissionCourseDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.CommCodeChgMstRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽Impl)<BR>
 * �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽�����N���X<BR>
 * 
 * @@author�@@�d��
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoCommissionRegistAccountDownloadService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl.class);
    
    /**
     * (�،���ЃR�[�h_���e���،�)<BR>
     * �萔��`�v���p�e�B�@@�،���ЃR�[�h�@@�h���e���،��h<BR>
     */
    private static final String INSTITUTION_CODE_RETELA_SECURITIES = "07";
    
    /**
     * (�،���ЃR�[�h_���،�)<BR>
     * �萔��`�v���p�e�B�@@�،���ЃR�[�h�@@�h���،��h<BR>
     */
    private static final String INSTITUTION_CODE_IWAI_SECURITIES = "0E";
    
    /**
     * @@roseuid 418F3A0401E4
     */
    public WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl() 
    {
     
    }
    
    /**
     * �萔���ύX�\���ڋq�_�E�����[�h���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔���ύX�\���ڋq<BR>
     * �޳�۰�ޖ⍇��ظ��Ă̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔���ύX�\���ڋq<BR>
     * �޳�۰��ظ��Ă̏ꍇ <BR>
     * �@@�|get�_�E�����[�h���()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔���ύX�\���ڋq<BR>
     * ̧���޳�۰��ظ��Ă̏ꍇ <BR>
     * �@@�|get�_�E�����[�h�t�@@�C��()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 414FAB54012C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�ޖ⍇��ظ��ăf�[�^�̏ꍇ
        if(l_request instanceof WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest)
        {  
            l_response = 
                this.getInputScreen(
                    (WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest)
                        l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A �Ǘ��҂��q�l���萔���ύX�\���ڋq̧���޳�۰��ظ��ăf�[�^�̏ꍇ
        else if(l_request instanceof WEB3AdminAccInfoCommissionChangeAccountDownloadRequest)
        {
            l_response = 
                this.getDownloadScreen(
                    (WEB3AdminAccInfoCommissionChangeAccountDownloadRequest)
                        l_request);
        }
        
        //      �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔���ύX�\���ڋq̧���޳�۰��ظ��ăf�[�^�̏ꍇ
        else if(l_request instanceof WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest)
        {
            l_response = 
                this.getDownloadFile(
                    (WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest)
                        l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (get���͉��)<BR>
     * �萔���ύX�\���ڋq�_�E�����[�h�⍇�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�萔���ύX�\���ڋq�c�k�jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�ޖ⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A5DE902BD
     */
    protected WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse getInputScreen(
        WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest l_request) 
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
          
        //validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION, false);
        
        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        WEB3AccInfoCommissionCourseMaster.getHandlingPossibleCommissionCourse(
            l_strInstitutionCode, WEB3CommisionProductCodeDef.LISTING_STOCK);
        
        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse l_response = 
            (WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse)l_request.createResponse();
            
        //TradingSystem.getSystemTimestamp()
        Timestamp l_timestamp = GtlUtils.getSystemTimestamp();
        
        Calendar l_calendar = new GregorianCalendar();
        l_calendar.setTime(l_timestamp);
        l_calendar.add(Calendar.MONTH, 1);
        l_calendar.set(Calendar.DATE, l_calendar.getActualMinimum(Calendar.DATE));
        
        l_response.trialStartDate = WEB3GentradeUtils.getBizDate(l_calendar.getTime(), 0);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h���)<BR>
     * �萔���ύX�\���ڋq�_�E�����[�h��ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�萔���ύX�\���ڋq�c�k�jget�_�E�����[�h��ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 414FAB860217
     */
    protected WEB3AdminAccInfoCommissionChangeAccountDownloadResponse getDownloadScreen(
        WEB3AdminAccInfoCommissionChangeAccountDownloadRequest l_request) 
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadScreen(WEB3AdminAccInfoCommissionChangeAccountDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate()
        l_request.validate();
        
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION, false);
        
        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //get�_�E�����[�h�f�[�^(String, Date, �Ǘ���)
        WEB3AccInfoCommissionChangeAccountInfo[] l_commissionChangeAccountInfo =this.getDownloadData(
            l_strInstitutionCode, 
            l_request.trialStartDate, 
            l_administrator,
            false);

        //createResponse( )
        WEB3AdminAccInfoCommissionChangeAccountDownloadResponse l_response = 
            (WEB3AdminAccInfoCommissionChangeAccountDownloadResponse)l_request.createResponse();
            
        //���X�|���X�f�[�^�v���p�e�B�ɕύX�\���ڋq�ꗗ�C���y�[�W���C�����R�[�h���C�\���y�[�W�ԍ����Z�b�g����B            
        int l_intPageSize = Integer.parseInt(l_request.pageSize);//�y�[�W���\���s��
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);//�v���y�[�W�ԍ�
        
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_commissionChangeAccountInfo, l_intPageIndex, l_intPageSize);
                    
        //�ύX�\���ڋq�ꗗ
        l_response.commissionCourseChangeList = 
            (WEB3AccInfoCommissionChangeAccountInfo[])l_pageIndexInfo.getArrayReturned(
                WEB3AccInfoCommissionChangeAccountInfo.class);
                   
        //���y�[�W��
        l_response.totalPages= l_pageIndexInfo.getTotalPages() + ""; 
        
        //(�\���y�[�W�ԍ�)
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + ""; 
        
        //�����R�[�h��
        l_response.totalRecords= l_pageIndexInfo.getTotalRecords() + "";  
        
        log.exiting(STR_METHOD_NAME);      
        return l_response;

    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �萔���ύX�\���ڋq�_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�萔���ύX�\���ڋq�c�k�jget�_�E�����[�h�t�@@�C���v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���萔���ύX�\���ڋq̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 414FAB860226
     */
    protected WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse getDownloadFile(
        WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest l_request) 
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getDownloadFile(WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate()
        l_request.validate();
        
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION, false);
        
        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //get�_�E�����[�h�f�[�^(String, Date, �Ǘ���)
        WEB3AccInfoCommissionChangeAccountInfo[] l_commissionChangeAccountInfo =this.getDownloadData(
            l_strInstitutionCode, 
            l_request.trialStartDate, 
            l_administrator,
            true);
        int l_intSize = 0;    
        if(l_commissionChangeAccountInfo != null)  
        {
            l_intSize = l_commissionChangeAccountInfo.length;
        }   
        //1.7���[���A�h���XCSV( )
        WEB3AdminAccInfoCommissionChangeAccountCsv l_commissionChangeAccountCsv = 
            new WEB3AdminAccInfoCommissionChangeAccountCsv();
        //1.8
        for (int i = 0; i < l_intSize; i++)
        {
            //add���׍s( )
            int l_intLineNumber = l_commissionChangeAccountCsv.addRow();
            
            //set���X�R�[�h(int, String)
            l_commissionChangeAccountCsv.setBranchCode(
                l_intLineNumber, 
            l_commissionChangeAccountInfo[i].branchCode);
            
            //set�ڋq�R�[�h(int, String)
            l_commissionChangeAccountCsv.setAccountCode(
                l_intLineNumber, 
                l_commissionChangeAccountInfo[i].accountCode.substring(0, 6));
            
            //set���i�R�[�h(int, String)
            l_commissionChangeAccountCsv.setProductCode(
                l_intLineNumber, 
                l_commissionChangeAccountInfo[i].instrumentsCode);
            
            //set�K�p�J�n��(int, Date)
            l_commissionChangeAccountCsv.setAppliStartDate(
                l_intLineNumber, 
                l_commissionChangeAccountInfo[i].trialStartDate);
                
            //set�萔��No.(int, String)
            l_commissionChangeAccountCsv.setCommissionNo(
                l_intLineNumber, 
                l_commissionChangeAccountInfo[i].commissionNo);
                
            //set������(int, String)
            l_commissionChangeAccountCsv.setChargeRatio(
                l_intLineNumber, 
                l_commissionChangeAccountInfo[i].collectRate);
                
            //set�K�p�I����(int, Date)
            l_commissionChangeAccountCsv.setAppliEndDate(
                l_intLineNumber, 
                l_commissionChangeAccountInfo[i].trialEndDate);
                
        }
        
        //1.9getCSV�t�@@�C���s( )
        String[] l_strCvsFileLines = l_commissionChangeAccountCsv.getCsvFileLines();
        
       //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse l_response = 
            (WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse)l_request.createResponse();
            
        l_response.downloadFile = l_strCvsFileLines;
        l_response.currentDate = GtlUtils.getSystemTimestamp(); 
           
        log.exiting(STR_METHOD_NAME);  
        return l_response;

    }
    
    /**
     * (get�_�E�����[�h�f�[�^)<BR>
     * �ϑ��萔���R�[�X�ύX�\���e�[�u�����A�_�E�����[�h�Ώۃf�[�^���擾����B<BR>
     * <BR>
     * �P�j�@@�ϑ��萔���R�[�X�ύX�\���e�[�u������<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\���e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��.�ύX�\�����ؓ���  <BR>
     * TradingSystem.getSystemTimestamp()�@@And<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��.�K�p�J�n������+�iYYYYMMDD�j = <BR>
     * �K�p�J�n��<BR>
     * <BR>
     * �ϑ��萔���R�[�X�ύX�\��.�폜�t���O = BooleanEnum.FALSE�B <BR>
     * �@@[�擾���iorder by�j]<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��.���X�h�c<BR>
     * �@@substr(�ϑ��萔���R�[�X�ύX�\��.�����h�c, 9, 6)<BR>
     * <BR>
     * �Q�j�@@�萔���ύX�ڋq���ꗗList�i�FArrayList�j����<BR>
     * �@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�萔���ύX�ڋq��񐶐�<BR>
     * �@@�P�j�Ŏ擾�����e�s�I�u�W�F�N�g�i�F�ϑ��萔���R�[�X�ύX�\��Params�j���ɁA<BR>
     * �R�|�P�j�`�R�|�Q�j�̏������s���B<BR>
     * �@@�R�|�P�j�@@�_�E�����[�h�σt���O���X�V����B<BR>
     * �@@�@@�� �_�E�����[�h�w���̏ꍇ�iis�_�E�����[�h == true�j
     * �@@�@@�Y���v�f�ɂ��āAQueryProcessor.doUpdateQuery()�ɂāA<BR>
     * �ȉ��̒ʂ�DB�X�V���s���B<BR>
     * <BR>
     * �@@�@@�ϑ��萔���R�[�X�ύX�\��Params.�_�E�����[�h�σt���O == <BR>
     * BooleanEnum.TRUE<BR>
     * �@@�@@�ϑ��萔���R�[�X�ύX�\��Params.�X�V�҃R�[�h == <BR>
     * �Ǘ���.�Ǘ��҃R�[�h<BR>
     * �@@�@@�ϑ��萔���R�[�X�ύX�\��Params.�X�V���� == <BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �@@�@@�� DB�X�V�d�l�u�萔���ύX�\���ڋq�c�k_�ϑ��萔���R�[�X<BR>
     * �ύX�\���e�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�萔���ύX�ڋq���𐶐����A�ȉ��̒ʂ�v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�萔���ύX�ڋq���.���X�R�[�h = <BR>
     * �ϑ��萔���R�[�X�ύX�\���s.���X�R�[�h<BR>
     * �@@�@@�萔���ύX�ڋq���.�ڋq�R�[�h = <BR>
     * �ϑ��萔���R�[�X�ύX�\���s.�����h�c�ɊY������ڋq.get�\���ڋq�R�[�h()<BR>
     * �@@�@@�萔���ύX�ڋq���.���i�R�[�h = �萔�����i�R�[�h.�h��ꊔ���h<BR>
     * �@@�@@�萔���ύX�ڋq���.�K�p�J�n�� = �K�p�J�n��<BR>
     * �@@�@@�萔���ύX�ڋq���.�萔��No. = this.get�萔��No�i�j�̖߂�l�B<BR>
     * <BR>
     * �@@�@@�@@�@@[get�萔��No()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@���XID = �ϑ��萔���R�[�X�ύX�\���s.���XID<BR>
     * �@@�@@�@@�@@�萔�����i�R�[�h = �ϑ��萔���R�[�X�ύX�\���s.�萔�����i�R�[�h<BR>
     * �@@�@@�@@�@@�K�p�J�n�� = �ϑ��萔���R�[�X�ύX�\���s.�K�p�J�n��<BR>
     * �@@�@@�@@�@@�萔���R�[�X�R�[�h = �ϑ��萔���R�[�X�ύX�\���s.�萔���R�[�X�R�[�h<BR>  
     * <BR>
     * �@@�@@�萔���ύX�ڋq���.������ = 100<BR>
     * �@@�@@�萔���ύX�ڋq���.�K�p�I���� = ���t�ő�l�iHighValue�F<BR>
     * 9999/12/31 00�F00�F00�j<BR>
     * <BR>
     * �@@�R�|�R�j�@@�萔���ύX�ڋq���ꗗList�i�FArrayList�j�ɃI�u�W�F�N�g��<BR>
     * �ǉ�����B<BR>
     * �@@�@@�R�|�Q�j�Ő��������I�u�W�F�N�g���萔���ύX�ڋq���ꗗList<BR>
     * �i�FArrayList�j�ɒǉ��iadd�j����B<BR>
     * <BR>
     * �S�j�@@�_�E�����[�h�f�[�^�ԋp<BR>
     * �@@�萔���ύX�ڋq���ꗗList�i�FArrayList�j��z��ɕϊ��itoArray()�j���A<BR>
     * �ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_datAppliStartDate - �K�p�J�n��
     * @@param l_administrator - �Ǘ��҃I�u�W�F�N�g
     * @@param l_blnIsDownloadData - is�_�E�����[�h
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionChangeAccountInfo[]
     * @@roseuid 414FAB860228
     */
    protected WEB3AccInfoCommissionChangeAccountInfo[] getDownloadData(
        String l_strInstitutionCode, 
        Date l_datAppliStartDate, 
        WEB3Administrator l_administrator,
        boolean l_blnIsDownloadData) throws WEB3BaseException 
        
    {       
        final String STR_METHOD_NAME = " getDownloadData(String, Date, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        try 
        {
            //�ڋq�}�X�^�e�[�u�����ȉ��̏����Ō�������
            StringBuffer l_sbWhere = new StringBuffer();
            
            //�ϑ��萔���R�[�X�ύX�\��.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
            l_sbWhere.append(" institution_code = ? ");  
            
            //* �@@�ϑ��萔���R�[�X�ύX�\��.�ύX�\�����ؓ��� < TradingSystem.getSystemTimestamp()<BR>
            l_sbWhere.append(" And regist_end_timestamp < ? "); 
            
            //�ϑ��萔���R�[�X�ύX�\��.�K�p�J�n������+�iYYYYMMDD�j= �K�p�J�n�� 
            l_sbWhere.append(" And to_char(appli_start_datetime,'YYYYMMDD')  = ? "); 
            
            //�ϑ��萔���R�[�X�ύX�\��.�폜�t���O = BooleanEnum.FALSE�B(***************QA:Ft-0033) 
            l_sbWhere.append(" And delete_flag = ? ");
        
            //�擾���iorder by�j
            StringBuffer l_sbOrderBy = new StringBuffer();
            l_sbOrderBy.append(" branch_id,");
            //substr(�ϑ��萔���R�[�X�ύX�\��.�����h�c, 9, 6)
            l_sbOrderBy.append(" substr(account_id, 9 , 6) ");
        
            //�ڋq���[���A�h���X���ꗗList�i�FArrayList�j����ArrayList�𐶐�����B
            List l_listWhere = new Vector();  
            l_listWhere.add(l_strInstitutionCode);
            l_listWhere.add(GtlUtils.getSystemTimestamp());
            l_listWhere.add(WEB3DateUtility.formatDate(l_datAppliStartDate,"yyyyMMdd"));
            l_listWhere.add(BooleanEnum.FALSE);
            
            Object[] l_objWhere = l_listWhere.toArray();

            List l_lisCommissionCourseRegistRow = null;
            QueryProcessor l_QueryProcessor = null; 

            l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisCommissionCourseRegistRow = l_QueryProcessor.doFindAllQuery(
                CommissionCourseRegistRow.TYPE,
                l_sbWhere.toString(),
                l_sbOrderBy.toString(),
                null,
                l_objWhere);
            
            int l_intSize = 0;
            if (l_lisCommissionCourseRegistRow != null && !l_lisCommissionCourseRegistRow.isEmpty())
            {
                l_intSize = l_lisCommissionCourseRegistRow.size();
            
            }

            List lisCommissionChangeAccountInfo = new Vector();
            CommissionCourseRegistParams l_commissionCourseRegistParams = null;
                        
            for (int i = 0; i < l_intSize; i++)
            {
                l_commissionCourseRegistParams = 
                    (CommissionCourseRegistParams) l_lisCommissionCourseRegistRow.get(i);

                CommissionCourseRegistPK l_pk = new CommissionCourseRegistPK();
                l_pk.commission_course_regist_id = 
                    l_commissionCourseRegistParams.getCommissionCourseRegistId();
                Map l_map = new HashMap();
                if (l_blnIsDownloadData)
                {
                    l_map.put("download_flag", BooleanEnum.TRUE);
                    l_map.put("last_updater", l_administrator.getAdministratorCode());
                    l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                    
                    QueryProcessor l_queryProcessor;
                    l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doUpdateQuery(l_pk, l_map);
                }
                                
                //�R�|�Q�j�@@�萔���ύX�ڋq���𐶐����A�ȉ��̒ʂ�v���p�e�B���Z�b�g����
                WEB3AccInfoCommissionChangeAccountInfo l_accInfoCommissionChangeAccountInfo =
                    new WEB3AccInfoCommissionChangeAccountInfo();
                    
                //�萔���ύX�ڋq���.���X�R�[�h = �ϑ��萔���R�[�X�ύX�\���s.���X�R�[�h
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                AccountManager l_accountManager = l_finApp.getAccountManager();
                                             
                Branch l_banch =
                    l_accountManager.getBranch(l_commissionCourseRegistParams.getBranchId());
                String l_strBranchCode = l_banch.getBranchCode();
                l_accInfoCommissionChangeAccountInfo.branchCode = l_strBranchCode;

                //�萔���ύX�ڋq���.�ڋq�R�[�h = �ϑ��萔���R�[�X�ύX�\���s.�����h�c�ɊY������ڋq.get�\���ڋq�R�[�h()
                String l_strAccountCode =
                    ((WEB3GentradeMainAccount)(l_accountManager.getMainAccount(
                        l_commissionCourseRegistParams.getAccountId()))).getDisplayAccountCode();
                l_accInfoCommissionChangeAccountInfo.accountCode = l_strAccountCode;

                //�萔���ύX�ڋq���.���i�R�[�h = �萔�����i�R�[�h.�h��ꊔ���h
                l_accInfoCommissionChangeAccountInfo.instrumentsCode = 
                    WEB3CommisionProductCodeDef.LISTING_STOCK;
                
                //�@@�萔���ύX�ڋq���.�K�p�J�n�� = �K�p�J�n��
                l_accInfoCommissionChangeAccountInfo.trialStartDate = l_datAppliStartDate;
                
                //�萔���ύX�ڋq���.�萔��No. = this.get�萔��No�i�j�̖߂�l
                l_accInfoCommissionChangeAccountInfo.commissionNo = 
                    this.getCommissionNo(
                    l_commissionCourseRegistParams.getBranchId(),
                    l_commissionCourseRegistParams.getCommProductCode(),
                    l_commissionCourseRegistParams.getAppliStartDatetime(),
                    l_commissionCourseRegistParams.getCommissionCourseDiv());
                
                //�萔���ύX�ڋq���.������ = 100
                l_accInfoCommissionChangeAccountInfo.collectRate = "100";
                
                //�萔���ύX�ڋq���.�K�p�I���� = ���t�ő�l�iHighValue�F9999/12/31 00�F00�F00�j
                l_accInfoCommissionChangeAccountInfo.trialEndDate = 
                    WEB3DateUtility.getDate("9999-12-31 00:00:00", "yyyy-MM-dd HH:mm:ss");
                
                //�萔���ύX�ڋq���ꗗList�i�FArrayList�j�ɃI�u�W�F�N�g��ǉ�����
                lisCommissionChangeAccountInfo.add(l_accInfoCommissionChangeAccountInfo);   
            }
                     
            WEB3AccInfoCommissionChangeAccountInfo[] l_passwordChangeAccountInfoes =
                new WEB3AccInfoCommissionChangeAccountInfo[lisCommissionChangeAccountInfo.size()];
            lisCommissionChangeAccountInfo.toArray(l_passwordChangeAccountInfoes);
                    
            log.exiting(STR_METHOD_NAME);  
            return l_passwordChangeAccountInfoes;                             

        }
        catch (DataFindException l_e) 
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }  
        catch (DataQueryException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__an unexpected error__ ",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }      

                 
    }

    /**
     * (get�萔��No)<BR>
     * �P�j�萔���R�[�X�R�[�h�ϊ��}�X�^����������B<BR>   
     * <BR>
     * �@@�@@���L�̏����ŁA�萔���R�[�X�R�[�h�ϊ��}�X�^�e�[�u������������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���XID = ����.���XID<BR>
     * �@@�@@�@@�@@�萔�����i�R�[�h = ����.�萔�����i�R�[�h<BR>
     * �@@�@@�@@�@@�K�p�J�n�� �� ����.�K�p�J�n��<BR>
     * �@@�@@�@@�@@�萔���R�[�X�R�[�h = ����.�萔���R�[�X�R�[�h<BR>
     * <BR>
     * �@@�@@����L�̏����Ń��R�[�h���擾�ł��Ȃ������ꍇ�́A���L�̏����ōČ�������B<BR>
     * �@@�@@�@@�@@���R�[�h���擾�ł��Ȃ��ꍇ�͗�O���X���[����B<BR>
     * �@@�@@�@@�@@class  : WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag    : BUSINESS_ERROR_00398    <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���XID = ����.���XID<BR>
     * �@@�@@�@@�@@�萔�����i�R�[�h = ����.�萔�����i�R�[�h<BR>
     * �@@�@@�@@�@@�K�p�J�n�� �� ����.�K�p�J�n��<BR>
     * �@@�@@�@@�@@�萔���R�[�X�R�[�h = "99"<BR>�@@
     * <BR>
     * �Q�j�擾�����萔���R�[�X�R�[�h�ϊ��}�X�^�I�u�W�F�N�g.�萔��No��ԋp����B<BR>
     * @@param l_lngBranchId - ���XID
     * @@param l_strCommissionProductCode - �萔�����i�R�[�h
     * @@param l_tsAppliStartDate - �K�p�J�n��
     * @@param l_strCommissionCourseCode - �萔���R�[�X�R�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4150DF4E0280
     */
    protected String getCommissionNo(long l_lngBranchId, String l_strCommissionProductCode,
        Timestamp l_tsAppliStartDate, String l_strCommissionCourseCode) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = "getCommissionNo(long, String, Timestamp, String)";
        log.entering(STR_METHOD_NAME); 

        //�P�j ���R�[�h���擾
        //�萔���R�[�X�R�[�h�ϊ��}�X�^�e�[�u������ȉ��̏����̃��R�[�h���擾����B 
        //[����] 
        StringBuffer l_strWhere = new StringBuffer();
        //���XID = ����.���XID
        l_strWhere.append(" branch_id = ? ");
        //�萔�����i�R�[�h = ����.�萔�����i�R�[�h
        l_strWhere.append(" and comm_product_code = ? ");
        //�K�p�J�n�� �� ����.�K�p�J�n��
        l_strWhere.append(" and appli_start_date <= ? ");

        String l_strAppliStartDate =
            WEB3DateUtility.formatDate(l_tsAppliStartDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        //�萔���R�[�X�R�[�h = ����.�萔���R�[�X�R�[�h
        l_strWhere.append(" and commission_course_div = ? ");

        Object[] l_objCommCodeChgMsts = {
            new Long(l_lngBranchId),
            l_strCommissionProductCode,
            l_strAppliStartDate,
            l_strCommissionCourseCode};

        List l_lisCommCodeChgMstRows = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisCommCodeChgMstRows = l_queryProcessor.doFindAllQuery(
                CommCodeChgMstRow.TYPE,
                l_strWhere.toString(),
                l_objCommCodeChgMsts);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }

        //��L�̏����Ń��R�[�h���擾�ł��Ȃ������ꍇ�́A���L�̏����ōČ�������B
        //���R�[�h���擾�ł��Ȃ��ꍇ�͗�O���X���[����B
        if (l_lisCommCodeChgMstRows == null || 
            l_lisCommCodeChgMstRows.isEmpty())
        {
            Object[] l_objCommCodeChgMstVars = {
                new Long(l_lngBranchId),
                l_strCommissionProductCode,
                l_strAppliStartDate,
                WEB3CommissionCourseDivDef.OTHER};
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisCommCodeChgMstRows = l_queryProcessor.doFindAllQuery(
                    CommCodeChgMstRow.TYPE,
                    l_strWhere.toString(),
                    l_objCommCodeChgMstVars);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);    
            }
            if (l_lisCommCodeChgMstRows == null || 
                l_lisCommCodeChgMstRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("�Y������f�[�^�����݂��܂���B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398, 
                    this.getClass().getName() + "."  + STR_METHOD_NAME,
                    "�Y������f�[�^�����݂��܂���B");
            }
        }

        //�Q�j�擾�����萔���R�[�X�R�[�h�ϊ��}�X�^�I�u�W�F�N�g.�萔��No��ԋp����
        CommCodeChgMstRow l_commCodeChgMstRow = (CommCodeChgMstRow)l_lisCommCodeChgMstRows.get(0);
        String l_strCommissionNo = l_commCodeChgMstRow.getCommissionNo();
        log.exiting(STR_METHOD_NAME);
        return l_strCommissionNo;
    }
}
@
