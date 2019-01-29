head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�XImpl(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/17 �J�N���V (���u) �V�K�쐬
Revesion History : 2006/12/14 ����� (���u) �d�l�ύX���f��154,�c�a�X�V�d�l040
Revesion History : 2006/12/15 ����� (���u) �d�l�ύX���f��155,156
Revesion History : 2006/12/20 ���� (���u) �d�l�ύX���f��158
Revesion History : 2007/01/19 �����q (���u) �d�l�ύX���f��160�A�c�a�X�V�d�l042
Revesion History : 2007/02/10 �Ӑ� (���u) �d�l�ύX���f��188
Revesion History : 2009/02/12 SCS�哈 �d�l�ύX���f��255
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoMaster;
import webbroker3.accountinfo.WEB3AccInfoMobileOfficeInfoRegist;
import webbroker3.accountinfo.WEB3AccInfoOccupationChangeRegistVoucherCreated;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistPK;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistRow;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.define.WEB3JudgmentResultDivDef;
import webbroker3.accountinfo.define.WEB3RegistStateDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeChangeAccount;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountInfoAcceptStatusDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3OccupationcodeUpdateDef;
import webbroker3.common.define.WEB3RealnameUpdateDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.AccountInfoMstPK;
import webbroker3.gentrade.data.AccountInfoMstParams;
import webbroker3.gentrade.data.AccountInfoMstRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�XImpl)<BR>
 * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X�����N���X<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl
    extends WEB3AccInfoClientRequestService
    implements WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl.class);

    /**
     * @@roseuid 418F3A050232
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl()
    {

    }

    /**
     * 
     * �g�єԍ��E�Ζ�����ύX�\���ڋq�ꗗ�\�����������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���g�єԍ��E�Ζ�����<BR>
     * �ύX�\���ڋq�⍇��ظ��Ă̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���g�єԍ��E�Ζ�����<BR>
     * �ύX�\���ڋqظ��Ă̏ꍇ <BR>
     * �@@�|get���g�єԍ��E�Ζ�����ύX�\���ڋq�ꗗ()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ����m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate�ꊇ����()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ���芮�����N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�ꊇ����()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4149722B02CA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇��ظ��Ă̏ꍇ
        //�|get���͉��()���R�[������
        if (l_request instanceof WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest)
        {
            
            l_response = this.getInputScreen((WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest) l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋqظ��Ă̏ꍇ
        //�|get���g�єԍ��E�Ζ�����ύX�\���ڋq�ꗗ()���R�[������
        else if (l_request instanceof WEB3AdminAccInfoMobileOfficeRegistAccountRequest)
        {
            
            l_response = this.getRegistAccountList((WEB3AdminAccInfoMobileOfficeRegistAccountRequest) l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ����m�F���N�G�X�g�̏ꍇ
        //�|validate�ꊇ����()���R�[������B
        else if (l_request instanceof WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest)
        {
            l_response = this.validateJudgement((WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ���芮�����N�G�X�g�̏ꍇ
        //�|submit�ꊇ����()���R�[������B
        else if (l_request instanceof WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest)
        {
            l_response = this.submitJudgement((WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest)l_request);
        }
        else
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * �g�єԍ��E�Ζ�����ύX�\���ڋq�⍇�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�g�єԍ��E�Ζ�����ύX�\���⍇���jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A5EC103E6
     */
    protected WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse getInputScreen(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest l_request)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.2) validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);

        //1.3) �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇��ڽ��ݽ(WEB3GenRequest)
        WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse l_response =
            (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse) l_request.createResponse();

        //1.4) �v���p�e�B�Z�b�g
        Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
        
        //�O�c�Ɠ��F�@@TradingSystem.getSystemTimestamp()�̑O�c�Ɠ�
        Timestamp l_gentradeBizDate = WEB3GentradeUtils.getBizDate(l_tsSystemTimestamp, -1);
        l_response.previousBizDate = WEB3DateUtility.toDay(l_gentradeBizDate);
        
        //�O���F�@@TradingSystem.getSystemTimestamp()�̑O��
        Date l_datPreviousDate = WEB3DateUtility.addDay(l_tsSystemTimestamp,-1);
        l_response.previousDate = WEB3DateUtility.toDay(l_datPreviousDate);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ύX�\���ڋq�ꗗ)<BR>
     * �g�єԍ��E�Ζ�����ύX�\���ڋq�ꗗ�\���������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�g�єԍ��E�Ζ�����ύX�\���⍇���jget�ύX�\���ڋq�ꗗ�v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountResponse
     * @@roseuid 414971670098
     */
    protected WEB3AdminAccInfoMobileOfficeRegistAccountResponse getRegistAccountList(WEB3AdminAccInfoMobileOfficeRegistAccountRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getRegistAccountList(WEB3AdminAccInfoMobileOfficeRegistAccountRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) validate( )
        l_request.validate();

        //1.2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3) validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);

        //1.4) validate���X����(String[])
        l_administrator.validateBranchPermission(l_request.branchCode);

        //1.5) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //1.6) create��������������(String, String[], String, Date, Date, String)
        String l_strQueryString =
            this.createQueryString(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode,
                l_request.startDate,
                l_request.endDate,
                l_request.searchApplyStateDiv);

        //1.7) create���������f�[�^�R���e�i(String, String[], String, Date, Date, String)
        String[] l_strQueryContainer =
            this.createQueryContainer(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode,
                l_request.startDate,
                l_request.endDate,
                l_request.searchApplyStateDiv);
        
        //create�\�[�g����
        String l_sortCondition = this.createSortCondition(l_request.sortKeys);

        //1.8) get�g�єԍ��E�Ζ�����ύX�\��(String, String[])
        List l_lisAccInfoMobileOfficeInfoRegist =
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_strQueryString, l_strQueryContainer,l_sortCondition);

        //ArrayList()
        //�ύX�O�\���ڋqList�I�u�W�F�N�g�̐���
        ArrayList l_lisBeforeAccount = new ArrayList();
        
        //ArrayList()
        //�ύX��\���ڋqList�I�u�W�F�N�g�̐���
        ArrayList l_lisAfterAccount = new ArrayList();
        
        if(l_lisAccInfoMobileOfficeInfoRegist == null || l_lisAccInfoMobileOfficeInfoRegist.isEmpty())
        {
            WEB3AdminAccInfoMobileOfficeRegistAccountResponse l_response =
                (WEB3AdminAccInfoMobileOfficeRegistAccountResponse)l_request.createResponse();
                
            WEB3AccInfoMobileOfficeChangeAccount[] l_beforeAccounts = new WEB3AccInfoMobileOfficeChangeAccount[0]; 

            WEB3AccInfoMobileOfficeChangeAccount[] l_afterAccounts = new WEB3AccInfoMobileOfficeChangeAccount[0];

            l_response.beforeChangeAccountList = l_beforeAccounts;
            l_response.afterChangeAccountList = l_afterAccounts;

            //���y�[�W��
            l_response.totalPages = "0";

            //�����R�[�h��
            l_response.totalRecords = "0";

            //(�\���y�[�W�ԍ�)
            l_response.pageIndex = "1";

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //���N�G�X�g�f�[�^.�����敪 != null �̏ꍇ�A�ȉ��̏��������s
        WEB3PageIndexInfo l_pageIndexInfo = null;
        if (l_request.accountType != null)
        {
            //get�����敪���List(List, String)
            //�����̌����敪�ƍ��v����������List�𐶐����A�ԋp����B
            //[get�����敪���List�i�j�Ɏw�肷�����]
            //�g�єԍ��E�Ζ�����ύX�\��List �F get�g�єԍ��E�Ζ�����ύX�\���i�j�̖߂�l
            //�����敪 �F ���N�G�X�g�f�[�^.�����敪
            List l_lstAccountTypeInfo = WEB3AccInfoMobileOfficeInfoRegist.getAccountTypeInfoList(
                l_lisAccInfoMobileOfficeInfoRegist,
                l_request.accountType);
                
            if(l_lstAccountTypeInfo == null || l_lstAccountTypeInfo.isEmpty())
            {
                WEB3AdminAccInfoMobileOfficeRegistAccountResponse l_response =
                    (WEB3AdminAccInfoMobileOfficeRegistAccountResponse)l_request.createResponse();
                
                WEB3AccInfoMobileOfficeChangeAccount[] l_beforeAccounts = new WEB3AccInfoMobileOfficeChangeAccount[0]; 

                WEB3AccInfoMobileOfficeChangeAccount[] l_afterAccounts = new WEB3AccInfoMobileOfficeChangeAccount[0];

                l_response.beforeChangeAccountList = l_beforeAccounts;
                l_response.afterChangeAccountList = l_afterAccounts;

                //���y�[�W��
                l_response.totalPages = "0";

                //�����R�[�h��
                l_response.totalRecords = "0";

                //(�\���y�[�W�ԍ�)
                l_response.pageIndex = "1";

                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            //WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
            //WEB3PageIndexInfo�I�u�W�F�N�g�̐���
            //[�R���X�g���N�^�Ɏw�肷�����]
            //l_list �F get�����敪���List�i�j�̖߂�l
            //l_intRequestPageIndex �F ���N�G�X�g�f�[�^.�v���y�[�W�ԍ�
            //l_intRequestPageSize �F ���N�G�X�g�f�[�^.�y�[�W���\���s��
            l_pageIndexInfo = new WEB3PageIndexInfo(
                l_lstAccountTypeInfo,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));

            //getListReturned()
            //�w�肵���y�[�W�s�̃��R�[�h���擾����B
            List l_lstPageIndexInfo = l_pageIndexInfo.getListReturned();

            //getListReturned()�߂�l�̊e�v�f����LOOP
            int l_intPageIndexInfoLength = l_lstPageIndexInfo.size();
            for (int i = 0; i < l_intPageIndexInfoLength; i++)
            {
                //get�ύX�O�\�����(�ڋq)
                //�ڋq�I�u�W�F�N�g���A�ύX�O�̌g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g�𐶐����A
                //�ԋp����B
                //[get�ύX�O�\�����i�j�Ɏw�肷�����]
                //�ڋq�I�u�W�F�N�g�F�@@List.get�ii�j.get�ڋq�i�j�̖߂�l
                WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
                    (WEB3AccInfoMobileOfficeInfoRegist)l_lstPageIndexInfo.get(i);
                WEB3AccInfoMobileOfficeChangeAccount l_beforeChangeAccount =
                    l_accInfoMobileOfficeInfoRegist.getBeforeChangeInfo(
                        l_accInfoMobileOfficeInfoRegist.getMainAccount());

                //get�ύX��\�����
                //�g�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g���A�ύX��̌g�єԍ��E�Ζ�����
                //�ύX�\���ڋq�I�u�W�F�N�g�𐶐����A�ԋp����B
                WEB3AccInfoMobileOfficeChangeAccount l_afterChangeAccount =
                    l_accInfoMobileOfficeInfoRegist.getChangedChangeInfo();

                //add(arg0 : Object)
                //get�ύX�O�\�����i�j�̖߂�l��add()
                l_lisBeforeAccount.add(l_beforeChangeAccount);

                //add(arg0 : Object)
                //get�ύX��\�����i�j�̖߂�l��add()
                l_lisAfterAccount.add(l_afterChangeAccount);
            }
        }
        //���N�G�X�g�f�[�^.�����敪 == null �̏ꍇ�A�ȉ��̏��������s
        else
        {
            //WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
            //WEB3PageIndexInfo�I�u�W�F�N�g�̐���
            //[�R���X�g���N�^�Ɏw�肷�����]
            //l_list �F get�g�єԍ��E�Ζ�����ύX�\���i�j�̖߂�l
            //l_intRequestPageIndex �F ���N�G�X�g�f�[�^.�v���y�[�W�ԍ�
            //l_intRequestPageSize �F ���N�G�X�g�f�[�^.�y�[�W���\���s��
            l_pageIndexInfo = new WEB3PageIndexInfo(
                l_lisAccInfoMobileOfficeInfoRegist,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));

            //getListReturned()
            //�w�肵���y�[�W�s�̃��R�[�h���擾����B
            List l_lstPageIndexInfo = l_pageIndexInfo.getListReturned();

            //getListReturned()�߂�l�̊e�v�f����LOOP
            int l_intPageIndexInfoLength = l_lstPageIndexInfo.size();
            for (int i = 0; i < l_intPageIndexInfoLength; i++)
            {
                //get�ύX�O�\�����(�ڋq)
                //�ڋq�I�u�W�F�N�g���A�ύX�O�̌g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g�𐶐����A
                //�ԋp����B
                //[get�ύX�O�\�����i�j�Ɏw�肷�����]
                //�ڋq�I�u�W�F�N�g�F�@@List.get�ii�j.get�ڋq�i�j�̖߂�l
                WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
                    (WEB3AccInfoMobileOfficeInfoRegist)l_lstPageIndexInfo.get(i);
                WEB3AccInfoMobileOfficeChangeAccount l_beforeChangeAccount =
                    l_accInfoMobileOfficeInfoRegist.getBeforeChangeInfo(
                        l_accInfoMobileOfficeInfoRegist.getMainAccount());

                //get�ύX��\�����
                //�g�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g���A�ύX��̌g�єԍ��E�Ζ�����
                //�ύX�\���ڋq�I�u�W�F�N�g�𐶐����A�ԋp����B
                WEB3AccInfoMobileOfficeChangeAccount l_afterChangeAccount =
                    l_accInfoMobileOfficeInfoRegist.getChangedChangeInfo();

                //add(arg0 : Object)
                //get�ύX�O�\�����i�j�̖߂�l��add()
                l_lisBeforeAccount.add(l_beforeChangeAccount);

                //add(arg0 : Object)
                //get�ύX��\�����i�j�̖߂�l��add()
                l_lisAfterAccount.add(l_afterChangeAccount);
            }
        }

        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇��ڽ��ݽ()
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminAccInfoMobileOfficeRegistAccountResponse l_response =
            (WEB3AdminAccInfoMobileOfficeRegistAccountResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        WEB3AccInfoMobileOfficeChangeAccount[] l_beforeAccounts = null;
        if (!l_lisBeforeAccount.isEmpty())
        {
            l_beforeAccounts = new WEB3AccInfoMobileOfficeChangeAccount[l_lisBeforeAccount.size()];
            l_lisBeforeAccount.toArray(l_beforeAccounts);
        }

        WEB3AccInfoMobileOfficeChangeAccount[] l_afterAccounts = null;
        if (!l_lisAfterAccount.isEmpty())
        {
            l_afterAccounts = new WEB3AccInfoMobileOfficeChangeAccount[l_lisAfterAccount.size()];
            l_lisAfterAccount.toArray(l_afterAccounts);
        }

        l_response.beforeChangeAccountList = l_beforeAccounts;
        l_response.afterChangeAccountList = l_afterAccounts;

        //���y�[�W��
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";

        //�����R�[�h��
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";

        //(�\���y�[�W�ԍ�)
        l_response.pageIndex = l_request.pageIndex;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create��������������)<BR>
     * ���������������ҏW����B <BR>
     * <BR>
     * �P�j�@@�߂�l���� <BR>
     * �@@�߂�l�̌�������������C���X�^���X�i�FString�j�𐶐� <BR>
     * <BR>
     * �Q�j�@@�،���Џ����ǉ�<BR>
     * �@@�،���ЃR�[�h������ǉ�����B<BR>
     * <BR>
     * �@@" institution_code =  ? "<BR>
     * <BR>
     * �R�j�@@���X�����ǉ�<BR>
     * �@@���X������ǉ�����B���X�R�[�h[]�̗v�f����"?"��ҏW����B<BR>
     * <BR>
     * �@@" and branch_id in (?, ?,,,) "<BR>
     * <BR>
     * �S�j�@@�ڋq�R�[�h�����ǉ� <BR>
�@@   *    �ڋq�w��̏ꍇ�i�ڋq�R�[�h != null�j<BR>
     *    �ڋq�R�[�h�w�蕶�������������������ɒǉ�����B<BR>
�@@   *   " and account_code like ? "<BR>
     * <BR>
     * �T�j�@@�J�n���C�I���������ǉ� <BR>
     * �@@�J�n���C�I�������w�肳��Ă���ꍇ�i�J�n�� != null && �I���� != null�j�A<BR>
     * �@@�\�����͈͎̔w��𕶎���C���X�^���X�ɒǉ�����B<BR>
     * <BR>
     * �@@" and created_timestamp >= ? " +<BR>
     * �@@" and created_timestamp < ? "<BR>
     * <BR>
     * �U�j�@@�\���󋵋敪�����ǉ�<BR>
     * �@@�\���󋵋敪���w�肳��Ă���ꍇ�i�\���󋵋敪 != null�j�A<BR>
     * �@@�\���󋵋敪�w��𕶎���C���X�^���X�ɒǉ�����B<BR>
     * <BR>
     * �@@�|�\���󋵋敪 == �h����҂��h�A�܂��́h����҂��i�m�F���j�h�̏ꍇ<BR>
     * �@@�@@��������ō폜�ς݂̍s�͕\�����Ȃ��B<BR>
     * �@@�@@�@@" and (decision = ?  and delete_flag = 0)"<BR>
     * <BR>
     * �@@�|�\���󋵋敪 == �h����ς݁h�̏ꍇ<BR>
     * �@@�@@�@@" and decision in (?, ?)"<BR>
     * <BR>
     * �V�j�@@������C���X�^���X��ԋp <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCodes - ���X�R�[�h�z��
     * 
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_datStartDate - �J�n��
     * @@param l_datEndDate - �I����
     * @@param l_strRegistStateDiv - �\���󋵋敪<BR>
     * <BR>
     * 0�F�@@����҂�<BR>
     * 1�F�@@����҂��i�m�F���j<BR>
     * 2�F�@@����ς�<BR>
     * 
     * @@return String
     * @@roseuid 4149773302F9
     */
    protected String createQueryString(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strAccountCode,
        Date l_datStartDate,
        Date l_datEndDate,
        String l_strRegistStateDiv)
    {
        
        final String STR_METHOD_NAME =
            " createQueryString(" +
                " String l_strInstitutionCode, " +
                " String[] l_strBranchCodes, " +
                " String l_strAccountCode, " +
                " Date l_datStartDate, " +
                " Date l_datEndDate, " +
                " String l_strRegistStateDiv)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�߂�l���� �߂�l�̌�������������C���X�^���X�i�FString�j�𐶐�
        String l_strSearchCond;

        //2) �،���Џ����ǉ� �،���ЃR�[�h������ǉ�����B
        l_strSearchCond = " institution_code =  ? ";

        //3) ���X�����ǉ� ���X������ǉ�����B���X�R�[�h[]�̗v�f����"?"��ҏW����
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
            l_strSearchCond += " and branch_id in (" + l_sbQueryBranchCodes.toString() + ")";             
        }

        //4) �ڋq�w��̏ꍇ�i�ڋq�R�[�h != null�j�A�ڋq�R�[�h�w�蕶�������������������ɒǉ�����B
        if (l_strAccountCode != null && !"".equals(l_strAccountCode) )
        {
            
            l_strSearchCond += " and account_code like ? ";
        }

        //5) �J�n���C�I�������w�肳��Ă���ꍇ�i�J�n�� != null && �I���� != null�j�A�\�����͈͎̔w��𕶎���C���X�^���X�ɒǉ�����B
        if (l_datStartDate != null && l_datEndDate != null)
        {
            
            //l_strSearchCond += " and created_timestamp >= ?  and created_timestamp < ? ";
            l_strSearchCond += " and to_char (created_timestamp , 'YYYYMMDD') >= ? " + " and to_char (created_timestamp, 'YYYYMMDD') < ? ";
        }

        //6) �\���󋵋敪���w�肳��Ă���ꍇ�i�\���󋵋敪 != null�j�A�\���󋵋敪�w��𕶎���C���X�^���X�ɒǉ�����B
        if (l_strRegistStateDiv != null && !"".equals(l_strRegistStateDiv))
        {
            
            //�\���󋵋敪 == �h����҂��h�A�܂��́h����҂��i�m�F���j�h�̏ꍇ
            if (WEB3RegistStateDivDef.WAIT_DECISION.equals(l_strRegistStateDiv)
                || WEB3RegistStateDivDef.WAIT_DECISION_CONFIRMING.equals(l_strRegistStateDiv))
            {
                
                l_strSearchCond += " and (decision = ?  and delete_flag = 0)";

            }
            //�\���󋵋敪 == �h����ς݁h�̏ꍇ
            if (WEB3RegistStateDivDef.DECISION_COMPLETE.equals(l_strRegistStateDiv))
            {
                
                l_strSearchCond += " and decision in (?, ?)";
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_strSearchCond;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i��ҏW����B <BR>
     * <BR>
     * �P�j�@@�߂�l���� <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐� <BR>
     * <BR>
     * �Q�j�@@�،���Џ����ǉ�<BR>
     * �@@�،���ЃR�[�h�������ǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@�،���ЃR�[�h<BR>
     * <BR>
     * �R�j�@@���X�����ǉ�<BR>
     * �@@���X�R�[�h[]�ɊY�����镔�X�h�c�����ׂĒǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]�@@<BR>
     * �@@���X�h�c��<BR>
     * <BR>
     * �@@���A�J�E���g�}�l�[�W��.getBranch(�،���ЁC���X�R�[�h)�ɂĎ擾����B<BR>
     * �@@���،���ЃI�u�W�F�N�g�́A�A�J�E���g�}�l�[�W��.getInstitution(�،���ЃR�[�h)<BR>
     * �ɂĎ擾����B<BR>
     * <BR>
     *�S�j�@@�ڋq�R�[�h�����ǉ� <BR>
     *�@@  �ڋq�w��̏ꍇ�i�ڋq�R�[�h != null�j�A�ڋq�R�[�h����������X�g�ɒǉ�����B<BR>
     * <BR>
     * �@@ [add()�Ɏw�肷�����]<BR>
�@@   *    �ڋq�R�[�h + "%"<BR>
     * <BR>
     * �T�j�@@�J�n���C�I���������ǉ� <BR>
     * �@@�J�n���C�I�������w�肳��Ă���ꍇ�i�J�n�� != null && �I���� != null�j�A<BR>
     * �@@�J�n���C�I���������X�g�ɒǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@�J�n���𕶎���iyyyyMMdd�j�ɕϊ������l<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@�I�����̗����𕶎���iyyyyMMdd�j�ɕϊ������l<BR>
     * <BR>
     * �U�j�@@�\���󋵋敪�����ǉ�<BR>
     * �@@�\���󋵋敪���w�肳��Ă���ꍇ�i�\���󋵋敪 != null�j�A<BR>
     * �@@���茋�ʎw��𕶎���C���X�^���X�ɒǉ�����B<BR>
     * <BR>
     * �@@�|�\���󋵋敪 == �h����҂��h�A�܂��́h����҂��i�m�F���j�h�̏ꍇ<BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@���茋��.0�FDEFAULT�i������j<BR>
     * <BR>
     * �@@�|�\���󋵋敪 == �h����ς݁h�̏ꍇ<BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@���茋��.1�F���F<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@���茋��.2�F�s��<BR>
     * <BR>
     * �V�j�@@�z���ԋp <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCodes - ���X�R�[�h�z��
     * 
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_datStartDate - �J�n��
     * @@param l_datEndDate - �I����
     * @@param l_strRegistStateDiv - �\���󋵋敪<BR>
     * <BR>
     * 0�F�@@����҂�<BR>
     * 1�F�@@����҂��i�m�F���j<BR>
     * 2�F�@@����ς�<BR>
     * 
     * @@return String[]
     * @@roseuid 41497D650098
     */
    protected String[] createQueryContainer(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strAccountCode,
        Date l_datStartDate,
        Date l_datEndDate,
        String l_strRegistStateDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createQueryContainer(String l_strInstitutionCode," +
                " String[] l_strBranchCodes," +
                " String l_strAccountCode," +
                " Date l_datStartDate," +
                " Date l_datEndDate," +
                " String l_strRegistStateDiv)";
        log.entering(STR_METHOD_NAME);

        //1) �߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�
        List l_lisQueryContainer = new ArrayList();

        //2) �،���ЃR�[�h�������ǉ�����
        l_lisQueryContainer.add(l_strInstitutionCode);

        //3) ���X�R�[�h[]�ɊY�����镔�X�h�c�����ׂĒǉ�����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accManage = (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        try
        {
            
            //�،����
            Institution l_institution = l_accManage.getInstitution(l_strInstitutionCode);

            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                
                Branch l_branch = l_accManage.getBranch(l_institution, l_strBranchCodes[i]);
                long l_lngBranchId = l_branch.getBranchId();
                l_lisQueryContainer.add(new Long(l_lngBranchId) + "");
            }
        }
        catch (NotFoundException l_ex)
        {
            
            log.error(" Error in ���X�����ǉ�....... ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //4) �ڋq�w��̏ꍇ�i�ڋq�R�[�h != null�j�A�����h�c����������X�g�ɒǉ�����
        if ( l_strAccountCode != null && ! "".equals(l_strAccountCode))
        {
            
            l_lisQueryContainer.add(l_strAccountCode + "%");  
        }
        //5) �J�n���C�I�������w�肳��Ă���ꍇ�i�J�n�� != null && �I���� != null�j�A�J�n���C�I���������X�g�ɒǉ�����
        if (l_datStartDate != null && l_datEndDate != null)
        {
            
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datStartDate, "yyyyMMdd"));
            Date l_datNextDate = WEB3DateUtility.addDay(l_datEndDate,1);
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datNextDate, "yyyyMMdd"));
        }
        //6)�\���󋵋敪�����ǉ�
        if (WEB3RegistStateDivDef.WAIT_DECISION.equals(l_strRegistStateDiv)
            || WEB3RegistStateDivDef.WAIT_DECISION_CONFIRMING.equals(l_strRegistStateDiv))
        {
            
            l_lisQueryContainer.add(WEB3JudgmentResultDivDef.DEFAULT);
        }
        else if (WEB3RegistStateDivDef.DECISION_COMPLETE.equals(l_strRegistStateDiv))
        {
            
            l_lisQueryContainer.add(WEB3JudgmentResultDivDef.CONSENT);
            l_lisQueryContainer.add(WEB3JudgmentResultDivDef.IMPOSSIBILITY);
        }

        //7)�z���ԋp
        String[] l_strQueryDataContainers = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryDataContainers);
        
        

        log.exiting(STR_METHOD_NAME);
        return l_strQueryDataContainers;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�����������ҏW����B <BR>
     * <BR>
     * �e�[�u���񕨗������g�p���A�ȉ��̒ʂ�A�\�[�g����������iorder by��j��ҏW����B<BR> 
     * <BR>
     * �P�j�\�[�g�L�[��ҏW <BR>
     * �@@�P�|�P�j�\�[�g�L�[ = �\�������̏ꍇ <BR>
     * �@@�@@�@@�g�єԍ�.�Ζ�����ύX�\���e�[�u��.�\������ <BR>
     * <BR>
     * �@@�P�|�Q�j�\�[�g���� = ���X�R�[�h�̏ꍇ <BR>
     * �@@�@@�@@�g�єԍ�.�Ζ�����ύX�\���e�[�u��.���X�R�[�h <BR>
     * <BR>
     * �@@�P�|�R�j�\�[�g���� = �ڋq�R�[�h�̏ꍇ <BR>
     * �@@�@@�@@�g�єԍ�.�Ζ�����ύX�\���e�[�u��.�ڋq�R�[�h <BR>
     * <BR>
     * �@@�P�|�S�j�\�[�g���� = ��������̏ꍇ <BR>
     * �@@�@@�@@�g�єԍ�.�Ζ�����ύX�\���e�[�u��.������� <BR>
     * <BR>
     * �Q�j�\�[�g�����ɊY������\�[�g������ҏW����B <BR>
     * �@@�@@�@@�����Fasc <BR>
     * �@@�@@�@@�~���Fdesc <BR>
     * @@param String l_sortKeys- ���q�l���\�[�g�L�[
     */
    protected String createSortCondition(WEB3AccInfoSortKey[] l_sortKeys)
    {
    	String l_strSortConditon = "";
    	//�e�[�u���񕨗������g�p���A�ȉ��̒ʂ�A�\�[�g����������iorder by��j��ҏW����B
    	int l_intSortKeysLen = 0;
    	if (l_sortKeys != null)
    	{
    		l_intSortKeysLen = l_sortKeys.length;
    	}
    	for (int i = 0;i < l_intSortKeysLen;i++)
    	{
    		if (i != 0)
    		{
    			l_strSortConditon += ","; 
    		}
    		String l_sortKey = l_sortKeys[i].keyItem;
    		//�P�j�\�[�g�L�[��ҏW 
    		//�@@�P�|�P�j�\�[�g�L�[ = �\�������̏ꍇ 
    		//�@@�@@�@@�g�єԍ�.�Ζ�����ύX�\���e�[�u��.�\������ 
        	if (WEB3AccInfoKeyItemDef.APPLY_DATE.equals(l_sortKey))
        	{
        		l_strSortConditon += "created_timestamp ";
        	}

    		//�@@�P�|�Q�j�\�[�g���� = ���X�R�[�h�̏ꍇ 
    		//�@@�@@substr(�g�єԍ�.�Ζ�����ύX�\���e�[�u��.���XID, 3, 3)
        	else if (WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_sortKey))
        	{
        		l_strSortConditon += "substr(branch_id,3,3) ";
        	}
        	
    		//�@@�P�|�R�j�\�[�g���� = �ڋq�R�[�h�̏ꍇ 
    		//�@@�@@�@@�g�єԍ�.�Ζ�����ύX�\���e�[�u��.�ڋq�R�[�h 
        	else if (WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_sortKey))
        	{
        		l_strSortConditon += "account_code ";
        	}
        	
    		//�@@�P�|�S�j�\�[�g���� = ��������̏ꍇ 
    		//�@@�@@�@@�g�єԍ�.�Ζ�����ύX�\���e�[�u��.������� 
        	else if (WEB3AccInfoKeyItemDef.JUDGEMENT_DATE.equals(l_sortKey))
        	{
        		l_strSortConditon += "decision_timestamp ";
        	}
    		//�Q�j�\�[�g�����ɊY������\�[�g������ҏW����B 
    		//�@@�@@�@@�����Fasc 
    		//�@@�@@�@@�~���Fdesc 
        	if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
        	{
        		l_strSortConditon += "asc";
        	}
        	else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
        	{
        		l_strSortConditon += "desc";
        	}
     
    	}

   	

    	return l_strSortConditon;
    }

    /**
     * (validate�ꊇ����)<BR>
     * �g�g�єԍ��E�Ζ�����ύX�\���ꊇ����m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u���q�l���i�g�єԍ��E�Ζ�����ύX�\���⍇���jvalidate�ꊇ����v�Q�ƁB<BR>
     * ===================================================================== <BR>
     * �V�[�P���X�} �u���q�l���i�g�єԍ��E�Ζ�����ύX�\���⍇���jvalidate�ꊇ����v<BR>
     * (validate�ꊇ����)<BR>
     * �ύX�O�\���ڋqList�̃T�C�Y���O�̏ꍇ�A��O���X���[<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01169 <BR>
     * ===================================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ����m�F���N�G�X�g<BR>
     * @@throws WEB3BaseException
     * @@return WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse
     * @@roseuid 4149773302F9
     */
    protected WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse validateJudgement(
        WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateJudgement(WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);

        //validate���X����(���X�R�[�h : String[])
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //�ύX�O�\���ڋqList�I�u�W�F�N�g�̐���
        List l_lisBeforChangeInfo = new ArrayList();

        //�ύX��\���ڋqList�I�u�W�F�N�g�̐���
        List l_lisChangedChangeInfo = new ArrayList();

        int l_intAccountCodeLength = l_request.accountCode.length;
        // ���N�G�X�g�f�[�^.�ڋq�R�[�h�̗v�f���ALoop����
        for (int i = 0; i < l_intAccountCodeLength; i++)
        {
            String l_strBranchCode = l_request.branchCode[i];
            String l_strAccountCode = l_request.accountCode[i];
            //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_gentradeMainAccount = null;
            try
            {
                l_gentradeMainAccount =
                    l_accountManager.getMainAccount(
                        l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("�ڋq�R�[�h�ɑΉ�����ڋq�͓o�^����Ă��܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01987,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // get�g�єԍ��E�Ζ�����ύX�\��(�ڋq)
            WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
                WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_gentradeMainAccount);

            if (l_accInfoMobileOfficeInfoRegist != null)
            {
                //getDataSourceObject( )
                MobileOfficeInfoRegistRow l_mobileOfficeInfoRegistRow =
                    (MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject();

                MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams =
                    new MobileOfficeInfoRegistParams(l_mobileOfficeInfoRegistRow);

                //����m�F���t���O
                l_mobileOfficeInfoRegistParams.setDecisionFlag(BooleanEnum.TRUE);
                //�Ǘ���.�Ǘ��҃R�[�h
                l_mobileOfficeInfoRegistParams.setLastUpdater(l_strAdministratorCode);
                //���������@@��TradingSystem.getSystemTimestamp()
                l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                try
                {
                    //doUpdateQuery(arg0 : Row)
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    l_processor.doUpdateQuery(l_mobileOfficeInfoRegistParams);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //get�ύX�O�\�����(�ڋq)
                WEB3AccInfoMobileOfficeChangeAccount l_beforChangeInfo =
                    l_accInfoMobileOfficeInfoRegist.getBeforeChangeInfo(l_gentradeMainAccount);

                //get�ύX��\�����( )
                WEB3AccInfoMobileOfficeChangeAccount l_changedChangeInfo =
                    l_accInfoMobileOfficeInfoRegist.getChangedChangeInfo();

                //get�ύX�O�\�����i�j�̖߂�l��add()
                l_lisBeforChangeInfo.add(l_beforChangeInfo);

                //get�ύX��\�����i�j�̖߂�l��add()
                l_lisChangedChangeInfo.add(l_changedChangeInfo);
            }
        }

        //�ύX�O�\���ڋqList�̃T�C�Y���O�̏ꍇ�A��O���X���[
        //�G���[���b�Z�[�W
        //�u�g�єԍ��E�Ζ�����ύX�\�����f�[�^�s�����ł��B�v
        if (l_lisBeforChangeInfo.size() == 0)
        {
            log.debug("�g�єԍ��E�Ζ�����ύX�\�����f�[�^�s�����ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01169,
                this.getClass().getName() + STR_METHOD_NAME,
                "�g�єԍ��E�Ζ�����ύX�\�����f�[�^�s�����ł��B");
        }

        //createResponse( )
        WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse l_response =
            (WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse)l_request.createResponse();

        //�i*2�j�v���p�e�B�Z�b�g
        WEB3AccInfoMobileOfficeChangeAccount[] l_beforeChangeAccountList = null;
        WEB3AccInfoMobileOfficeChangeAccount[] l_changedChangeInfoList = null;
        l_beforeChangeAccountList =
            new WEB3AccInfoMobileOfficeChangeAccount[l_lisBeforChangeInfo.size()];
        l_lisBeforChangeInfo.toArray(l_beforeChangeAccountList);
        l_changedChangeInfoList =
            new WEB3AccInfoMobileOfficeChangeAccount[l_lisChangedChangeInfo.size()];
        l_lisChangedChangeInfo.toArray(l_changedChangeInfoList);

        l_response.beforeChangeAccountList = l_beforeChangeAccountList;
        l_response.afterChangeAccountList = l_changedChangeInfoList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�ꊇ����)<BR>
     * �g�єԍ��E�Ζ�����ύX�\���ꊇ���芮���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u���q�l���i�g�єԍ��E�Ζ�����ύX�\���⍇���jsubmit�ꊇ����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ���芮�����N�G�X�g<BR>
     * @@return WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4149773302F9
     */
    protected WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse submitJudgement(
        WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitJudgement(WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        //���N�G�X�g�f�[�^�̊ȈՃ`�F�b�N���s���B
        l_request.validate();

        //getInstanceFrom���O�C�����()
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��Ҍ����`�F�b�N���s���B
        //[validate����()�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ڋq��{���i��{�j
        //is�X�V�F�@@true
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);

        //validate����p�X���[�h(�p�X���[�h : String)
        //����p�X���[�h�����������̃`�F�b�N���s���B
        //[validate����p�X���[�h�i�j�Ɏw�肷�����]
        //�p�X���[�h �F ���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //validate���X����(���X�R�[�h : String[])
        //���Y�Ǘ��҂��A�w��̕��X����舵���邩���`�F�b�N����B
        //[validate���X�����i�j�Ɏw�肷�����]
        //���X�R�[�h[] �F ���N�G�X�g�f�[�^.���X�R�[�h[]
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get�،���ЃR�[�h()
        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get�Ǘ��҃R�[�h()
        //�Ǘ��҃R�[�h���擾����B
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //���N�G�X�g�f�[�^.�ڋq�R�[�h�̗v�f���ALoop����
        int l_intCustomerCodeLength = l_request.accountCode.length;
        for (int i = 0; i < l_intCustomerCodeLength; i++)
        {
            //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
            //�ڋq�I�u�W�F�N�g���擾����B
            //[get�ڋq()�Ɏw�肷�����]
            //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
            //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h[ i ]
            //�����R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h [ i ]
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_gentradeMainAccount = null;
            try
            {
                l_gentradeMainAccount =
                    l_gentradeAccountManager.getMainAccount(
                        l_strInstitutionCode,
                        l_request.branchCode[i],
                        l_request.accountCode[i]);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("�ڋq�R�[�h�ɑΉ�����ڋq�͓o�^����Ă��܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01987,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //���X�R�[�h
            String l_strBranchCode = l_gentradeMainAccount.getBranch().getBranchCode();
            //�ڋq�R�[�h
            String l_accountCode = l_gentradeMainAccount.getAccountCode();
            
        	//lock����(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        	//���������b�N����B
        	//[����] 
        	//�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        	//���X�R�[�h�F�@@get�ڋq()�̖߂�l.���X�R�[�h 
        	//�����R�[�h�F�@@get�ڋq()�̖߂�l.�ڋq�R�[�h [ i ]          
            l_gentradeAccountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_accountCode);

            //get�g�єԍ��E�Ζ�����ύX�\��(�ڋq)
            //�g�єԍ��E�Ζ�����ύX�\���f�[�^���擾����B
            //[get�g�єԍ��E�Ζ�����ύX�\��()�Ɏw�肷�����]
            //�ڋq�F�@@get�ڋq() �̖߂�l
            WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
                WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_gentradeMainAccount);

            //�ύX�\����񂪂���ꍇ�iget�g�єԍ��E�Ζ�����ύX�\��() != null�j�A
            //������C�ύX�\�������X�V����B
            if (l_accInfoMobileOfficeInfoRegist != null)
            {
                //createForUpdateParams()
                //�X�V�p�ɍs�I�u�W�F�N�g��clone�𐶐�����B
                l_accInfoMobileOfficeInfoRegist.createForUpdateParams();

                //set����(String, String)
                //��������Z�b�g����B
                //[set����()�Ɏw�肷�����]
                //�Ǘ��҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
                //���茋�ʁF�@@���N�G�X�g�f�[�^.���茋�ʋ敪
                l_accInfoMobileOfficeInfoRegist.setDecision(l_strAdministratorCode, l_request.judgmentResultDiv);

                //getDataSourceObject()
                //�g�єԍ��E�Ζ�����s�I�u�W�F�N�g���擾����B
                MobileOfficeInfoRegistRow l_mobileOfficeInfoRegistRow =
                    (MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject();

                MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams =
                    new MobileOfficeInfoRegistParams(l_mobileOfficeInfoRegistRow);

                MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParamsTemp =
                    new MobileOfficeInfoRegistParams(l_mobileOfficeInfoRegistRow);

                //����m�F���t���O:0�FFALSE�i����m�F���łȂ��j
                l_mobileOfficeInfoRegistParams.setDecisionFlag(BooleanEnum.FALSE);

                //����҃R�[�h:�Ǘ���.�Ǘ��҃R�[�h
                l_mobileOfficeInfoRegistParams.setDecisionUpdater(l_strAdministratorCode);

                //�������:���������@@��TradingSystem.getSystemTimestamp()
                l_mobileOfficeInfoRegistParams.setDecisionTimestamp(GtlUtils.getSystemTimestamp());

                //�폜�t���O:1�FTRUE�i�폜�F�����j
                l_mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.TRUE);

                //�X�V�҃R�[�h:�Ǘ���.�Ǘ��҃R�[�h
                l_mobileOfficeInfoRegistParams.setLastUpdater(l_strAdministratorCode);

                //�X�V����:���������@@��TradingSystem.getSystemTimestamp()
                l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                //doUpdateQuery(arg0 : Row)
                //�g�єԍ��E�Ζ�����ύX�\���e�[�u�����X�V����B
                //[doUpdateQuery()�Ɏw�肷�����]
                //arg0�i�FRow�j�F�@@getDataSourceObject()
                QueryProcessor l_processor = null;
                try
                {
                    l_processor = Processors.getDefaultProcessor();
                    l_processor.doUpdateQuery(l_mobileOfficeInfoRegistParams);
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

                //���N�G�X�g�f�[�^.���茋�ʋ敪 = 1�i���F�j�̏ꍇ�̂ݏ������{
                //����t���[
                //���N�G�X�g�f�[�^.���茋�ʋ敪 = 1�i���F�j�̏ꍇ�̂݁A���������{����B
                if (WEB3JudgmentResultDivDef.CONSENT.equals(l_request.judgmentResultDiv))
                {
                    //getDataSourceObject()
                    //�ڋq�I�u�W�F�N�g�iget�ڋq()�̖߂�l�j���A�s�I�u�W�F�N�g���擾����B
                    MainAccountRow l_mainAccountRow =
                        (MainAccountRow)l_gentradeMainAccount.getDataSourceObject();
                    MainAccountParams l_mainAccountParams = new MainAccountParams(l_mainAccountRow);

                    //�A����d�b�ԍ��i�g�сj:�g�єԍ��E�Ζ�����ύX�\���e�[�u��.�g�єԍ�
                    l_mainAccountParams.setMobile(l_mobileOfficeInfoRegistParams.getMobile());

                    //�Ζ��於��:�g�єԍ��E�Ζ�����ύX�\���e�[�u��.�Ζ��於��
                    l_mainAccountParams.setOffice(l_mobileOfficeInfoRegistParams.getOffice());

                    //�Ζ���X�֔ԍ�:�g�єԍ��E�Ζ�����ύX�\���e�[�u��.�Ζ���X�֔ԍ�
                    l_mainAccountParams.setOfficeZipCode(l_mobileOfficeInfoRegistParams.getOfficeZipCode());

                    //�Ζ���Z��:�g�єԍ��E�Ζ�����ύX�\���e�[�u��.�Ζ���Z��
                    l_mainAccountParams.setOfficeAddress(l_mobileOfficeInfoRegistParams.getOfficeAddress());

                    //�Ζ���d�b�ԍ�:�g�єԍ��E�Ζ�����ύX�\���e�[�u��.�Ζ���d�b�ԍ�
                    l_mainAccountParams.setOfficeTelephone(l_mobileOfficeInfoRegistParams.getOfficeTelephone());

                    //��E:�g�єԍ��E�Ζ�����ύX�\���e�[�u��.��E
                    l_mainAccountParams.setPost(l_mobileOfficeInfoRegistParams.getPost());

                    //�g�єԍ��E�Ζ�����X�V�҃R�[�h:�Ǘ���.�Ǘ��҃R�[�h
                    l_mainAccountParams.setMbOffLastUpdater(l_strAdministratorCode);

                    //�g�єԍ��E�Ζ�����X�V����:���������@@��TradingSystem.getSystemTimestamp()
                    l_mainAccountParams.setMbOffLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                    //�X�V����:���������@@��TradingSystem.getSystemTimestamp()
                    l_mainAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                    //doUpdateQuery(arg0 : Row)
                    //�ڋq�}�X�^�e�[�u���Ɍg�єԍ��E�Ζ�������X�V����B
                    try
                    {
                        l_processor = Processors.getDefaultProcessor();
                        l_processor.doUpdateQuery(l_mainAccountParams);
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

                    // get�⏕����(����ID : , �⏕�����^�C�v : )
                    // �⏕�������擾
                    // [get�⏕�����i�j�Ɏw�肷�����]
                    // ����ID �F �ڋq�I�u�W�F�N�g.getAccountId()
                    // �⏕�����^�C�v �F SubAccountTypeEnum EQUITY_SUB_ACCOUNT
                    SubAccountTypeEnum l_subAccountTyoeEnum = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
                    long l_lngAccountId = l_gentradeMainAccount.getAccountId();
                    SubAccount l_subAccount = null;
                    try
                    {
                        l_subAccount =
                            l_gentradeAccountManager.getSubAccount(l_lngAccountId,l_subAccountTyoeEnum);
                    }
                    catch (NotFoundException l_ex)
                    {
                        log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }

                    // get���X�v���t�@@�����X(�⏕����)
                    // �ڋq�I�u�W�F�N�g���A���X�p�v���t�@@�����X�e�[�u������
                    // �ڋq�������̍X�V�A�E�ƃR�[�h�X�V�`�F�b�N���擾����B
                    // [get���X�v���t�@@�����X()�Ɏw�肷�����]
                    // �⏕���� �F get�⏕�����i�j�̖߂�l
                    String[] l_intBranchPreferences = this.getBranchPreferences(l_subAccount);

                    //get�������}�X�^(�ڋq)
                    //�������}�X�^�f�[�^���擾����B
                    //[get�������}�X�^()�Ɏw�肷�����]
                    //�ڋq�F�@@get�ڋq() �̖߂�l
                    WEB3AccInfoMaster l_accInfoMaster = WEB3AccInfoMaster.getAccInfoMaster(l_gentradeMainAccount);
                    WEB3AccInfoMaster l_accInfoMasterTemp = WEB3AccInfoMaster.getAccInfoMaster(l_gentradeMainAccount);

                    //get�������}�X�^ != null �̏ꍇ�A�������}�X�^�����X�V����B
                    if (l_accInfoMaster != null)
                    {
                        //getDataSourceObject()
                        //�������}�X�^�s�I�u�W�F�N�g���擾����B
                        AccountInfoMstParams l_accountInfoMstParams = 
                            new AccountInfoMstParams((AccountInfoMstParams)l_accInfoMaster.getDataSourceObject());

                        //�������}�X�^Params.�X�V���� = ���������@@��TradingSystem.getSystemTimestamp()
                        l_accountInfoMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                        //�������}�X�^Params.�X�V�҃R�[�h = �Ǘ���.�g�єԍ�.�Ζ�����ύX�\���D�X�V�҃R�[�h
                        l_accountInfoMstParams.setLastUpdater(l_mobileOfficeInfoRegistParams.getLastUpdater());

                        //this.get�v���t�@@�����X()�̖߂�l.index(1)��"1"�̏ꍇ�A
                        //�g�єԍ��E�Ζ�����ύX�\��.�������̂P
                        //����ȊO�A�����l
                        if (WEB3RealnameUpdateDef.EXECUTE.equals(l_intBranchPreferences[1]))
                        {
                            l_accountInfoMstParams.setRealName1(l_mobileOfficeInfoRegistParams.getRealName1());
                        }

                        //this.get�v���t�@@�����X()�̖߂�l.index(1)��"1"�̏ꍇ�A
                        //�g�єԍ��E�Ζ�����ύX�\��.�������̂Q
                        //����ȊO�A�����l
                        if (WEB3RealnameUpdateDef.EXECUTE.equals(l_intBranchPreferences[1]))
                        {
                            l_accountInfoMstParams.setRealName2(l_mobileOfficeInfoRegistParams.getRealName2());
                        }

                        //this.get�v���t�@@�����X()�̖߂�l.index(0)��"1"�̏ꍇ�A
                        //�g�єԍ��E�Ζ�����ύX�\��.�E��
                        //����ȊO�A�����l
                        if (WEB3OccupationcodeUpdateDef.EXECUTE.equals(l_intBranchPreferences[0]))
                        {
                            l_accountInfoMstParams.setOccupationDiv(l_mobileOfficeInfoRegistParams.getOccupationDiv());
                        }

                        //�������}�X�^Params.��\�Җ��i�����j�� = �g�єԍ��E�Ζ�����ύX�\��.��\�Җ��i�����j��
                        l_accountInfoMstParams.setRepresentFamilyName(
                            l_mobileOfficeInfoRegistParams.getRepresentFamilyName());

                        //�������}�X�^Params.��\�Җ��i�����j�� = �g�єԍ��E�Ζ�����ύX�\��.��\�Җ��i�����j��
                        l_accountInfoMstParams.setRepresentGivenName(
                            l_mobileOfficeInfoRegistParams.getRepresentGivenName());

                        //�������}�X�^Params.��\�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����ύX�\��.��\�Җ��i�J�i�j��
                        l_accountInfoMstParams.setRepresentFamilyNameAlt1(
                            l_mobileOfficeInfoRegistParams.getRepresentFamilyNameAlt1());

                        //�������}�X�^Params.��\�Җ��i�J�i�j�� =  �g�єԍ��E�Ζ�����ύX�\��.��\�Җ��i�J�i�j��
                        l_accountInfoMstParams.setRepresentGivenNameAlt1(
                            l_mobileOfficeInfoRegistParams.getRepresentGivenNameAlt1());

                        //�������}�X�^Params.��\�Ҍ�= �g�єԍ��E�Ζ�����ύX�\��.��\�Ҍ�
                        l_accountInfoMstParams.setRepresentPower(l_mobileOfficeInfoRegistParams.getRepresentPower());

                        //�������}�X�^Params.����ӔC�Җ��i�����j��= �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�Җ��i�����j��
                        l_accountInfoMstParams.setDirectorFamilyName(
                            l_mobileOfficeInfoRegistParams.getDirectorFamilyName());

                        //�������}�X�^Params.����ӔC�Җ��i�����j��= �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�Җ��i�����j��
                        l_accountInfoMstParams.setDirectorGivenName(
                            l_mobileOfficeInfoRegistParams.getDirectorGivenName());

                        //�������}�X�^Params.����ӔC�Җ��i�J�i�j��= �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�Җ��i�J�i�j��
                        l_accountInfoMstParams.setDirectorFamilyNameAlt1(
                            l_mobileOfficeInfoRegistParams.getDirectorFamilyNameAlt1());

                        //�������}�X�^Params.����ӔC�Җ��i�J�i�j��= �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�Җ��i�J�i�j��
                        l_accountInfoMstParams.setDirectorGivenNameAlt1(
                            l_mobileOfficeInfoRegistParams.getDirectorGivenNameAlt1());

                        //�������}�X�^Params.����ӔC�ҁ@@��������= �g�єԍ��E�Ζ�����ύX�\��.����ӔC�ҏ�������
                        l_accountInfoMstParams.setDirectorDepartment(
                            l_mobileOfficeInfoRegistParams.getDirectorDepartment());

                        //�������}�X�^Params.����ӔC�ҁ@@��E= �g�єԍ��E�Ζ�����ύX�\��.����ӔC�Җ�E 
                        l_accountInfoMstParams.setDirectorPost(l_mobileOfficeInfoRegistParams.getDirectorPost());

                        //�������}�X�^Params.����ӔC�җX�֔ԍ�= �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�җX�֔ԍ�
                        l_accountInfoMstParams.setDirectorZipCode(l_mobileOfficeInfoRegistParams.getDirectorZipCode());

                        //�������}�X�^Params.����ӔC�ҏZ���P= �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�ҏZ���P 
                        l_accountInfoMstParams.setDirectorAddress1(
                            l_mobileOfficeInfoRegistParams.getDirectorAddress1());

                        //�������}�X�^Params.����ӔC�ҏZ���Q= �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�ҏZ���Q 
                        l_accountInfoMstParams.setDirectorAddress2(
                            l_mobileOfficeInfoRegistParams.getDirectorAddress2());

                        //�������}�X�^Params.����ӔC�ҏZ���R= �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�ҏZ���R
                        l_accountInfoMstParams.setDirectorAddress3(
                            l_mobileOfficeInfoRegistParams.getDirectorAddress3());

                        //�������}�X�^Params.����ӔC�Ґ��N�����@@�N��=
                        //�g�єԍ��E�Ζ�����ύX�\��.�����ӔC�Ґ��N�����N��
                        l_accountInfoMstParams.setDirectorEraBorn(l_mobileOfficeInfoRegistParams.getDirectorEraBorn());

                        //�������}�X�^Params.����ӔC�Ґ��N����= �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�Ґ��N����
                        l_accountInfoMstParams.setDirectorBornDate(
                            l_mobileOfficeInfoRegistParams.getDirectorBornDate());

                        //�������}�X�^Params.����ӔC�҉�В��ʔԍ�= �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�҉�В��ʔԍ�
                        l_accountInfoMstParams.setDirectorCorpTelephone(
                            l_mobileOfficeInfoRegistParams.getDirectorCorpTelephone());

                        //�������}�X�^Params.���̑��A����i�g�сA����j= �g�єԍ��E�Ζ�����ύX�\��.���̑��̘A����
                        l_accountInfoMstParams.setOtherContact(l_mobileOfficeInfoRegistParams.getOtherContact());

                        //�������}�X�^Params.���� = �g�єԍ��E�Ζ�����ύX�\��.����
                        l_accountInfoMstParams.setDepartment(l_mobileOfficeInfoRegistParams.getDepartment());

                        //�������}�X�^Params.�A����D�揇�� �P�� = �g�єԍ�.�Ζ�����ύX�\���D�A����D�揇�� 1��
                        l_accountInfoMstParams.setContactPriority1(
                            l_mobileOfficeInfoRegistParams.getContactPriority1());

                        //�������}�X�^Params.�A����D�揇�� 2�� = �g�єԍ�.�Ζ�����ύX�\���D�A����D�揇�� 2��
                        l_accountInfoMstParams.setContactPriority2(
                            l_mobileOfficeInfoRegistParams.getContactPriority2());

                        //�������}�X�^Params.�A����D�揇�� 3�� = �g�єԍ�.�Ζ�����ύX�\���D�A����D�揇�� 3��
                        l_accountInfoMstParams.setContactPriority3(
                            l_mobileOfficeInfoRegistParams.getContactPriority3());

                        //�������}�X�^Params.���� = �g�єԍ�.�Ζ�����ύX�\���D����
                        l_accountInfoMstParams.setNationality(l_mobileOfficeInfoRegistParams.getNationality());

                        //�������}�X�^Params.���Ђ��̑� = �g�єԍ�.�Ζ�����ύX�\���D���Ђ��̑�
                        l_accountInfoMstParams.setNationalityOther(
                            l_mobileOfficeInfoRegistParams.getNationalityOther());

                        //doInsertQuery(arg0 : Row)
                        //�������}�X�^�e�[�u���ɐV�K�s��}������B
                        try
                        {
                            l_processor = Processors.getDefaultProcessor();
                            l_processor.doUpdateQuery(l_accountInfoMstParams);
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

                    //get�������}�X�^ == null �̏ꍇ�A�������}�X�^����V�K�쐬����B
                    if (l_accInfoMaster == null)
                    {
                        //get�ύX��\�����()
                        WEB3AccInfoMobileOfficeChangeAccount l_accInfoMobileOfficeChangeAccount =
                            l_accInfoMobileOfficeInfoRegist.getChangedChangeInfo();

                        //createNew�������}�X�^(�ڋq, �g�єԍ��E�Ζ�����, String)
                        //�������}�X�^�I�u�W�F�N�g��V�K�쐬����B
                        //[createNew�������}�X�^()�Ɏw�肷�����]
                        //�ڋq�F get�ڋq()�̖߂�l
                        //�g�єԍ��E�Ζ�����F get�ύX��\�����̖߂�l.�g�єԍ��E�Ζ�����
                        //�X�V�҃R�[�h�F get�Ǘ��҃R�[�h()�̖߂�l
                        l_accInfoMaster = WEB3AccInfoMaster.createNewAccInfoMaster(
                            (MainAccount)l_gentradeMainAccount,
                            l_accInfoMobileOfficeChangeAccount.mobileOfficeInfo,
                            l_strAdministratorCode);

                        //getDataSourceObject()
                        //�������}�X�^�s�I�u�W�F�N�g���擾����B
                        AccountInfoMstRow l_accountInfoMstRow =
                            (AccountInfoMstRow)l_accInfoMaster.getDataSourceObject();

                        //doUpdateQuery(arg0 : Row)
                        //�������}�X�^�e�[�u�����X�V����B
                        try
                        {
                            l_processor = Processors.getDefaultProcessor();
                            l_processor.doInsertQuery(l_accountInfoMstRow);
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

                    boolean l_blnVoucherCreated = 
                        WEB3AccInfoOccupationChangeRegistVoucherCreated.isVoucherCreated(l_mobileOfficeInfoRegistParamsTemp, l_accInfoMasterTemp);

                    //is�`�[�쐬�i�j��true�̏ꍇ
                    if (l_blnVoucherCreated)
                    {
                        //get���X�v���t�@@�����X�i�j�̖߂�lindex[0]�̒l = "1" �̏ꍇ�A�ȉ��̏��������s
                        if (WEB3OccupationcodeUpdateDef.EXECUTE.equals(l_intBranchPreferences[0]))
                        {
                            //�E�ƕύX�\���`�[�쐬( )
                            WEB3AccInfoOccupationChangeRegistVoucherCreated l_occupationChangeRegistVoucherCreated =
                                new WEB3AccInfoOccupationChangeRegistVoucherCreated();

                            // create�E�ƕύX�`�[(long, String)
                            // �E�ƕύX�ɔ�����c�E�d�q��t�E��������`�[�iGI844�j���쐬����B
                            // [create�E�ƕύX�`�[�i�j�Ɏw�肷�����]
                            // �ύX�\��ID �F �g�єԍ��E�Ζ�����ύX�\�����.�g�єԍ��E�Ζ�����ύX�\��ID
                            // �E�ƃR�[�h �F �g�єԍ��E�Ζ�����ύX�\�����.�E�Ƌ敪
                            // �ڋq�I�u�W�F�N�g �F get�ڋq�i�j�̖߂�l
                            long l_lngChangeRegistID = l_mobileOfficeInfoRegistParams.getMobileOfficeInfoRegistId();
                            String l_strOccupationCode = l_mobileOfficeInfoRegistParams.getOccupationDiv();
                            l_occupationChangeRegistVoucherCreated.createOccupationChangeVoucher(
                                l_lngChangeRegistID, l_strOccupationCode ,l_gentradeMainAccount);
                            
                            try
                            {
                                //�����`�[�쐬�̏ꍇ�A�g�єԍ�.�Ζ�����ύX�\���e�[�u���̎�t���ʋ敪�� 
                                //"0�F��t����"�ɍX�V����B 
                                Map l_updateMap = new HashMap(); 
                                l_updateMap.put("accept_status", WEB3AccountInfoAcceptStatusDef.NOT_ACCEPT);
                                l_processor.doUpdateQuery(
                                    new MobileOfficeInfoRegistPK(l_mobileOfficeInfoRegistParams.getMobileOfficeInfoRegistId()), 
                                    l_updateMap);

                                //�������}�X�^�̐E�ƍX�V�������X�V����B
                                Map l_updateMap1 = new HashMap();
                                TradingSystem l_tradingSys = l_finApp.getTradingSystem();
                                l_updateMap1.put("occupation_updated_timestamp", l_tradingSys.getSystemTimestamp());
                                l_processor.doUpdateQuery(
                                    new AccountInfoMstPK(l_mobileOfficeInfoRegistParams.getAccountId()),
                                    l_updateMap1);
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
                    }
                }             
            }
        }

        //createResponse()
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse l_response =
            (WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���X�v���t�@@�����X )<BR>  
     * �ڋq�I�u�W�F�N�g���A���X�p�v���t�@@�����X�e�[�u������<BR>
     * �ڋq�������̍X�V�A�E�ƃR�[�h�X�V�`�F�b�N���擾����B<BR>
     * <BR>
     * �P�j�@@�ȉ��̗v�f�̔z��𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�|�P�j�@@DB���� �i�E�ƃR�[�h�X�V�j<BR>
     * �@@���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō������v���t�@@�����X�̒l���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���XID = �⏕����.getBranch().getBranchId() And<BR>
     * �@@�@@�v���t�@@�����X�� = �v���t�@@�����X��.���q�l���E�ƃR�[�h�X�V And<BR>
     * �@@�@@�v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A""(�󕶎�)���Z�b�g����B<BR>
     * <BR>
     * �P�|�Q�j�@@DB���� �i�ڋq�������̍X�V�j<BR>
     * �@@���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō������v���t�@@�����X�̒l���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���XID = �⏕����.getBranch().getBranchId() And<BR>
     * �@@�@@�v���t�@@�����X�� = �v���t�@@�����X��.���q�l���ڋq�������̍X�V And<BR>
     * �@@�@@�v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A""(�󕶎�)���Z�b�g����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@return l_strBranchPerferences
     * 
     */
    private String[] getBranchPreferences(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchPreferences(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        String[] l_strBranchPerferences = new String[2];
        try 
        {
            // �P�|�P�jDB���� �i�E�ƃR�[�h�X�V�j
            // ���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō������v���t�@@�����X�̒l���擾����B
            // ���XID = �⏕����.getBranch().getBranchId() And
            // �v���t�@@�����X�� = �v���t�@@�����X��.���q�l���E�ƃR�[�h�X�V And
            // �v���t�@@�����X���̘A�� = 1
            BranchPreferencesRow l_branchReferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_subAccount.getMainAccount().getBranch().getBranchId(),
                    WEB3BranchPreferencesNameDef.OCCUPATIONCODE_UPDATE,
                    1);

            //�������ʂ��擾�ł��Ȃ������ꍇ�A""(�󕶎�)���Z�b�g����B
            if (l_branchReferencesRow == null)
            {
                l_strBranchPerferences[0] = "";
            }
            else
            {
                l_strBranchPerferences[0] = l_branchReferencesRow.getValue();
            }

            // �P�|�Q�j�@@DB���� �i�ڋq�������̍X�V�j
            // ���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō������v���t�@@�����X�̒l���擾����
            // ���XID = �⏕����.getBranch().getBranchId() And
            // �v���t�@@�����X�� = �v���t�@@�����X��.���q�l���ڋq�������̍X�V And
            // �v���t�@@�����X���̘A�� = 1
            BranchPreferencesRow l_branchReferenceRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_subAccount.getMainAccount().getBranch().getBranchId(),
                    WEB3BranchPreferencesNameDef.REALNAME_UPDATE,
                    1);

            //�������ʂ��擾�ł��Ȃ������ꍇ�A""(�󕶎�)���Z�b�g����B
            if (l_branchReferenceRow == null)
            {
                l_strBranchPerferences[1] = "";
            }
            else
            {
                l_strBranchPerferences[1] = l_branchReferenceRow.getValue();
            }
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

        log.exiting(STR_METHOD_NAME);
        return l_strBranchPerferences;
    }
}
@
