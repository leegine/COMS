head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoHyperBoxCommissionChangeDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽Impl(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �d�� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate.stdimpls;


import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoCommissionChangeAccountCsv;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionChangeAccountInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.SrvRegiApplicationRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽Impl)<BR>
 * �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽�����N���X<BR>
 * 
 * @@author�@@�d��
 * @@version 1.0
 */
public class WEB3AdminAccInfoHyperBoxCommissionChangeDownloadServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadServiceImpl.class);
    
    /**
     * (�T�[�r�X�敪_�n�C�p�[�{�b�N�X �I�[�N�V����)<BR>
     * �萔��`�v���p�e�B�@@�T�[�r�X�敪�@@�h�n�C�p�[�{�b�N�X �I�[�N�V�����h<BR>
     */
    private static  final String SERVICE_DIV_HYPER_BOX_OPTION = "33";
    
    /**
     * @@roseuid 418F3A0001A5
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeDownloadServiceImpl() 
    {
     
    }
    
    /**
     * ʲ�߰�ޯ���萔���ύX�ް��޳�۰�ޏ��������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ʲ�߰�ޯ���萔��<BR>
     * �ύX�޳�۰�ޖ⍇��ظ��Ă̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ʲ�߰�ޯ���萔��<BR>
     * �ύX�ް��޳�۰��ظ��Ă̏ꍇ <BR>
     * �@@�|get�_�E�����[�h���()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ʲ�߰�ޯ���萔��<BR>
     * �ύX�ް�̧���޳�۰��ظ��Ă̏ꍇ <BR>
     * �@@�|get�_�E�����[�h�t�@@�C��()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146A544008F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //�����̃��N�G�X�g�f�[�^���A �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�ޖ⍇��ظ��Ă̏ꍇ
        if(l_request instanceof WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest)
        {  
            l_response = this.getInputScreen((WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް��޳�۰��ظ��ăf�[�^�̏ꍇ
        else if(l_request instanceof WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest)
        {
            l_response = this.getDownloadScreen((WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް�̧���޳�۰��ظ��ăf�[�^�̏ꍇ
        else if(l_request instanceof WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest)
        {
            l_response = this.getDownloadFile((WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest)l_request);
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
     * �n�C�p�[�{�b�N�X�萔���ύX�_�E�����[�h���͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���iʲ�߰�ޯ���萔���ύX�c�k�jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�ޖ⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 41665B6F0097
     */
    protected WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse getInputScreen(WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest)";
        log.entering(STR_METHOD_NAME);
      //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_HYPERBOX_COMMISSION, false);
        
        WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse l_response = 
            (WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h���)<BR>
     * �n�C�p�[�{�b�N�X�萔���ύX�_�E�����[�h��ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���iʲ�߰�ޯ���萔���ύX�c�k�jget�_�E�����[�h��ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް��޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146A555031F
     */
    protected WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse getDownloadScreen(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadScreen(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate( )
        l_request.validate();
        
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
          //validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_HYPERBOX_COMMISSION, false);
          
          //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //get�_�E�����[�h�f�[�^(String, Date, Date, String, String)
        WEB3AccInfoCommissionChangeAccountInfo[] l_getDownloadData = 
            this.getDownloadData(
                l_strInstitutionCode,
                l_request.trialStartDate,
                l_request.trialEndDate,
                l_request.commissionNo,
                l_request.collectRate
                );
            
        WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse l_response = 
            (WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse)l_request.createResponse();

            
        int l_intPageSize = Integer.parseInt(l_request.pageSize);//�y�[�W���\���s��
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);//�v���y�[�W�ԍ�
        
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_getDownloadData, l_intPageIndex, l_intPageSize);
            
        l_response.hyperBoxCommissionList = 
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
     * �n�C�p�[�{�b�N�X�萔���ύX�_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���iʲ�߰�ޯ���萔���ύX�c�k�jget�_�E�����[�h�t�@@�C���v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް�̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146A5F103CB
     */
    protected WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse getDownloadFile(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate()
        l_request.validate();
        
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_HYPERBOX_COMMISSION, false);
        
        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        WEB3AccInfoCommissionChangeAccountInfo[] l_getDownloadData = 
            this.getDownloadData(
                l_strInstitutionCode,
                l_request.trialStartDate,
                l_request.trialEndDate,
                l_request.commissionNo,
                l_request.collectRate
                );
        int l_intSize = 0;
        if(l_getDownloadData != null)  
        {
            l_intSize = l_getDownloadData.length;
        }          
                
        WEB3AdminAccInfoCommissionChangeAccountCsv l_commissionChangeAccountCsv
            = new WEB3AdminAccInfoCommissionChangeAccountCsv();
        
        for (int i = 0; i < l_intSize; i++)
        {
            int l_intLineNumber = l_commissionChangeAccountCsv.addRow();
            l_commissionChangeAccountCsv.setBranchCode(
                l_intLineNumber, 
                l_getDownloadData[i].branchCode); 
                
            l_commissionChangeAccountCsv.setAccountCode(
                l_intLineNumber, 
                l_getDownloadData[i].accountCode); 
                
            l_commissionChangeAccountCsv.setProductCode(
                l_intLineNumber, 
                l_getDownloadData[i].instrumentsCode); 
                
            l_commissionChangeAccountCsv.setAppliStartDate(
                l_intLineNumber, 
                l_getDownloadData[i].trialStartDate); 
                
            l_commissionChangeAccountCsv.setCommissionNo(
                l_intLineNumber, 
                l_getDownloadData[i].commissionNo);
                
            l_commissionChangeAccountCsv.setChargeRatio(
                l_intLineNumber, 
                l_getDownloadData[i].collectRate);
                
            l_commissionChangeAccountCsv.setAppliEndDate(
                l_intLineNumber, 
                l_getDownloadData[i].trialEndDate);  
        } 
        String[] l_strCvsFileLines = l_commissionChangeAccountCsv.getCsvFileLines(); 
        
        WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse l_response = 
            (WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse)l_request.createResponse();
            
        l_response.downloadFile = l_strCvsFileLines; 
        l_response.currentDate = GtlUtils.getSystemTimestamp();   
          
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�f�[�^)<BR>
     * �T�[�r�X�\���o�^�e�[�u�����A�_�E�����[�h�Ώۃf�[�^���擾����B<BR>
     * �T�[�r�X�\���o�^��s�ɂ��āA��ꊔ���C�X��������2���쐬����B<BR>
     * <BR>
     * �P�j�@@�T�[�r�X�\���o�^�e�[�u������<BR>
     * �@@�T�[�r�X�\���o�^�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     *�@@�T�[�r�X�\���o�^�e�[�u��.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     *�@@�T�[�r�X�\���o�^�e�[�u��.�T�[�r�X�敪 = �T�[�r�X�敪_�n�C�p�[�{�b�N�X �I�[�N�V������ And<BR>
     *�@@�T�[�r�X�\���o�^�e�[�u��.�L���敪 = 0�F�L���@@And<BR>
     *�@@�T�[�r�X�\���o�^�e�[�u��.����敪 = 0�F�ʏ�@@And<BR>
     *�@@�T�[�r�X�\���o�^�e�[�u��.�\�����I�敪 in (2�F���I/�{�\�� , 5�F�������I) And<BR>
     *�@@�T�[�r�X�\���o�^�e�[�u��.�K�p�I���� �� �K�p�I����<BR>
     *<BR>
     *�@@���T�[�r�X�敪_�n�C�p�[�{�b�N�X �I�[�N�V����<BR>
     *�@@�{�T�[�r�X�N���X�ɒ萔��`���Ă���l�B   <BR> 
     * �@@[�擾���iorder by�j]<BR>
     * �@@�T�[�r�X�\���o�^�e�[�u��.���X�R�[�h<BR>
     * �@@�T�[�r�X�\���o�^�e�[�u��.�����R�[�h<BR>
     * <BR>
     * �Q�j�@@�萔���ύX�ڋq���ꗗList�i�FArrayList�j����<BR>
     * �@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�萔���ύX�ڋq��񐶐�<BR>
     * �@@�P�j�Ŏ擾�����e�s�I�u�W�F�N�g�i�F�T�[�r�X�\���o�^Params�j���ɁA<BR>
     * �R�|�P�j�`�R�|�R�j�̏������s���B<BR>
     * <BR>
     * �@@�R�|�P�j�@@�萔���ύX�ڋq���𐶐����A�ȉ��̒ʂ�v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�萔���ύX�ڋq���.���X�R�[�h = �T�[�r�X�\���o�^�s.���X�R�[�h<BR>
     * �@@�@@�萔���ύX�ڋq���.�ڋq�R�[�h = �T�[�r�X�\���o�^�s.�����R�[�h�̍�6byte<BR>
     * �@@�@@�萔���ύX�ڋq���.���i�R�[�h = �萔�����i�R�[�h.�h��ꊔ���h<BR>
     * �@@�@@�萔���ύX�ڋq���.�K�p�J�n�� = �K�p�J�n��<BR>
     * �@@�@@�萔���ύX�ڋq���.�萔��No. = �萔��No.<BR>
     * �@@�@@�萔���ύX�ڋq���.������ = ������<BR>
     * �@@�@@�萔���ύX�ڋq���.�K�p�I���� = �K�p�I����<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�萔���ύX�ڋq���ꗗList�i�FArrayList�j�ɃI�u�W�F�N�g��ǉ�����B<BR>
     * �@@�@@�R�|�P�j�Ő��������I�u�W�F�N�g���萔���ύX�ڋq���ꗗList<BR>
     * �i�FArrayList�j�ɒǉ��iadd�j����B<BR>
     * <BR>
     * �S�j�@@�_�E�����[�h�f�[�^�ԋp<BR>
     * �@@�萔���ύX�ڋq���ꗗList�i�FArrayList�j��z��ɕϊ��itoArray()�j���A<BR>
     * �ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_datAppliStartDate - �K�p�J�n��
     * @@param l_datAppliEndDate - �K�p�I����
     * @@param l_strCommissionNo - �萔��No.
     * @@param l_strChargeRatio - ������
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionChangeAccountInfo[]
     * @@roseuid 4146ADAF03AC
     */
    protected WEB3AccInfoCommissionChangeAccountInfo[] getDownloadData(
        String l_strInstitutionCode, 
        Date l_datAppliStartDate, 
        Date l_datAppliEndDate, 
        String l_strCommissionNo, 
        String l_strChargeRatio) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadData(String, Date, Date, String, String)";
        log.entering(STR_METHOD_NAME);
       
        StringBuffer l_sbWhere = new StringBuffer();

        //�T�[�r�X�\���o�^�e�[�u��.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
        l_sbWhere.append(" institution_code = ? "); 
        
        //�T�[�r�X�\���o�^�e�[�u��.�T�[�r�X�敪 = �T�[�r�X�敪_�n�C�p�[�{�b�N�X <BR>
        //�I�[�N�V������ And<BR>
        //l_sbWhere.append(" and srv_div = '"+ SERVICE_DIV_HYPER_BOX_OPTION +"'");
        l_sbWhere.append(" and srv_div = ?");  
        
        //�T�[�r�X�\���o�^�e�[�u��.�L���敪 = 0�F�L���@@And
        //l_sbWhere.append(" and effective_div = '"+ WEB3EffectiveDivDef.EFFECTIVE +"'");
        l_sbWhere.append(" and effective_div = ?");
        //�T�[�r�X�\���o�^�e�[�u��.����敪 = 0�F�ʏ�@@And
        l_sbWhere.append(" and cancel_div = ?");
        //l_sbWhere.append(" and cancel_div = '"+ WEB3SrvRegiCancelDivDef.USUAL_DEFAULT +"'");
        // �T�[�r�X�\���o�^�e�[�u��.�\�����I�敪 in (2�F���I/�{�\�� , 5�F�������I) And
        //l_sbWhere.append(" and appli_lot_div in ('"+ WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI + "', '"+ WEB3AppliLotDivDef.AUTO_ELECTION +"')");
        l_sbWhere.append(" and appli_lot_div in (?,?)");
        
        // �T�[�r�X�\���o�^�e�[�u��.�K�p�I���� �� �K�p�I����
        l_sbWhere.append("and appli_end_date >= ?");
        //�擾���iorder by�j
        StringBuffer l_sbOrderBy = new StringBuffer();
        l_sbOrderBy.append(" branch_code, ");
        l_sbOrderBy.append(" account_code ");
        
        List l_listWhere = new Vector();      
        l_listWhere.add(l_strInstitutionCode);

        l_listWhere.add(SERVICE_DIV_HYPER_BOX_OPTION);
        //     *�@@�T�[�r�X�\���o�^�e�[�u��.�T�[�r�X�敪 = �T�[�r�X�敪_�n�C�p�[�{�b�N�X �I�[�N�V������ And<BR>
        l_listWhere.add(WEB3EffectiveDivDef.EFFECTIVE);
        //�@@�T�[�r�X�\���o�^�e�[�u��.�L���敪 = 0�F�L���@@And<BR>
        l_listWhere.add(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);
        //�@@�T�[�r�X�\���o�^�e�[�u��.����敪 = 0�F�ʏ�@@And<BR>
        //�@@�T�[�r�X�\���o�^�e�[�u��.�\�����I�敪 in (2�F���I/�{�\�� , 5�F�������I) And<BR>
        l_listWhere.add(WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI);
        l_listWhere.add(WEB3AppliLotDivDef.AUTO_ELECTION);
        l_listWhere.add(l_datAppliEndDate);
        
        Object[] l_objWhere = l_listWhere.toArray();

        List l_lisSrvRegiApplicationRow = null;
        QueryProcessor l_QueryProcessor = null; 
        try 
        {
            l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisSrvRegiApplicationRow = l_QueryProcessor.doFindAllQuery(
                SrvRegiApplicationRow.TYPE,
                l_sbWhere.toString(),
                l_sbOrderBy.toString(),
                null,
                l_objWhere);
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
        List lisCommissionChangeAccountInfo = new Vector();
        for (int i = 0 ; i <l_lisSrvRegiApplicationRow.size(); i++)
        {
            SrvRegiApplicationRow l_applicationRow = (SrvRegiApplicationRow)l_lisSrvRegiApplicationRow.get(i);
            WEB3AccInfoCommissionChangeAccountInfo l_accInfoCommissionChangeAccountInfo =
                new WEB3AccInfoCommissionChangeAccountInfo();
                
            //* �@@�@@�萔���ύX�ڋq���.���X�R�[�h = �T�[�r�X�\���o�^�s.���X�R�[�h<BR>
            l_accInfoCommissionChangeAccountInfo.branchCode = l_applicationRow.getBranchCode();
            
            //* �@@�@@�萔���ύX�ڋq���.�ڋq�R�[�h = �T�[�r�X�\���o�^�s.�����R�[�h�̍�6byte<BR>
            l_accInfoCommissionChangeAccountInfo.accountCode = l_applicationRow.getAccountCode().substring(0, 6);
            
            // * �@@�@@�萔���ύX�ڋq���.���i�R�[�h = �萔�����i�R�[�h.�h��ꊔ���h<BR>
            l_accInfoCommissionChangeAccountInfo.instrumentsCode = WEB3CommisionProductCodeDef.LISTING_STOCK;
            
            // * �@@�@@�萔���ύX�ڋq���.�K�p�J�n�� = �K�p�J�n��<BR>
            l_accInfoCommissionChangeAccountInfo.trialStartDate = l_datAppliStartDate;
            
            // * �@@�@@�萔���ύX�ڋq���.�萔��No. = �萔��No.<BR>
            l_accInfoCommissionChangeAccountInfo.commissionNo = l_strCommissionNo;
            
            // * �@@�@@�萔���ύX�ڋq���.������ = ������<BR>
            l_accInfoCommissionChangeAccountInfo.collectRate = l_strChargeRatio;
            
            //* �@@�@@�萔���ύX�ڋq���.�K�p�I���� = �K�p�I����<BR>
            l_accInfoCommissionChangeAccountInfo.trialEndDate = l_datAppliEndDate;
            
            
            //�萔���ύX�ڋq���ꗗList�i�FArrayList�j�ɃI�u�W�F�N�g��ǉ�����
            lisCommissionChangeAccountInfo.add(l_accInfoCommissionChangeAccountInfo);
 
        }         
              
        //�萔���ύX�ڋq���ꗗList�i�FArrayList�j��z��ɕϊ��itoArray()�j
        WEB3AccInfoCommissionChangeAccountInfo[] l_accInfoCommissionChangeAccountInfos =
            new WEB3AccInfoCommissionChangeAccountInfo[lisCommissionChangeAccountInfo.size()];
        lisCommissionChangeAccountInfo.toArray(l_accInfoCommissionChangeAccountInfos);
        
        log.exiting(STR_METHOD_NAME);
        return l_accInfoCommissionChangeAccountInfos;
    }
}
@
