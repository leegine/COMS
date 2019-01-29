head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l�������ҏ��ꗗ�T�[�r�XImpl(WEB3AdminAccInfoInsiderInfoListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 ���C�g (���u) �V�K�쐬
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.message.WEB3AccInfoInsiderInfoUnit;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoInsiderInfoListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInsider;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.InsiderRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (�Ǘ��҂��q�l�������ҏ��ꗗ�T�[�r�XImpl)<BR>
 * �Ǘ��҂��q�l�������ҏ��ꗗ�T�[�r�X�����N���X<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoListServiceImpl implements WEB3AdminAccInfoInsiderInfoListService
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoInsiderInfoListServiceImpl.class);
    
    public WEB3AdminAccInfoInsiderInfoListServiceImpl()
    {
        
    }
    
    /**
     * �����ҏ��ꗗ���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l�������ҏ��ꗗ����ظ��Ă̏ꍇ <BR>
     * �|get���͉��()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l�������ҏ��ꗗظ��Ă̏ꍇ <BR>
     * �|get�ꗗ���()���R�[������B <BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminAccInfoInsiderInfoInputRequest)
        {
            l_response = getInputScreenDisplay((WEB3AdminAccInfoInsiderInfoInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoInsiderInfoListRequest)
        {
            l_response = getListScreenDisplay((WEB3AdminAccInfoInsiderInfoListRequest)l_request);
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
     * �����ҏ��ꗗ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��҂��q�l���i�����ҏ��ꗗ�jget���͉�ʁv<BR>
     * �Q��<BR>
     * 
     * @@param l_request - �Ǘ��҂��q�l���������ꗗ���̓��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return WEB3AdminAccInfoInsiderInfoInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B745B027C
     */
    protected WEB3AdminAccInfoInsiderInfoInputResponse getInputScreenDisplay(WEB3AdminAccInfoInsiderInfoInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreenDisplay(WEB3AdminAccInfoInsiderInfoInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3) validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRADING, false);
        
        //1.4) validate���X����(���X�R�[�h : String[])
        l_administrator.validateBranchPermission(l_request.branchCodeList);
        
        //1.5) createResponse( )
        WEB3AdminAccInfoInsiderInfoInputResponse l_response = 
            (WEB3AdminAccInfoInsiderInfoInputResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�ꗗ���)<BR>
     * �����ҏ��ꗗ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��҂��q�l���i�����ҏ��ꗗ�jget�ꗗ��ʁv<BR>
     * �Q��<BR>
     * @@param l_request - �Ǘ��҂��q�l���������ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return WEB3AdminAccInfoInsiderInfoListResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B745B027C
     */
    protected WEB3AdminAccInfoInsiderInfoListResponse getListScreenDisplay(WEB3AdminAccInfoInsiderInfoListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListScreenDisplay(WEB3AdminAccInfoInsiderInfoListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3) validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)(�@@�\�J�e�S���R�[�h.�ڋq����Ǘ�, false)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRADING, false);
        
        //1.4) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.5) validate���X����(���X�R�[�h : String[])
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.6) create��������������(String, String[], String, String)
        String l_strQueryString = 
            this.createQueryString(l_strInstitutionCode, l_request.branchCode, l_request.accountCode, l_request.productCode);
        
        //1.7) create���������f�[�^�R���e�i(String, String[], String, String)
        String[] l_strQueryContainer = 
            this.createQueryContainer(l_strInstitutionCode, l_request.branchCode, l_request.accountCode, l_request.productCode);
        
        //1.8) create�\�[�g����(���q�l���\�[�g�L�[[])
        String l_strSortCond =
            this.createSortCond(l_request.sortKeys);
        
        //1.9) get������(�������������� : String, ���������f�[�^�R���e�i : String[], �\�[�g���� : String)
        
        log.debug(l_strQueryString + l_strSortCond);
        for (int i = 0; i < l_strQueryContainer.length; i++)
        {
            log.debug(l_strQueryContainer[i]);
        }
        
        List l_insider = WEB3GentradeInsider.getInsider(l_strQueryString, l_strQueryContainer, l_strSortCond);
        
        //1.10) ArrayList( )
        List l_list = new ArrayList();
        
        //1.11) (*1)get������()�̖߂�l�̓��A�\���Ώۍs(fromIndex �` toIndex)�̊�Loop����
        int l_intPageSize = Integer.parseInt(l_request.pageSize);//�y�[�W���\���s��
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);//�v���y�[�W�ԍ�
        
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_insider, l_intPageIndex, l_intPageSize);
            
        WEB3GentradeInsider[] l_insiderLast = (WEB3GentradeInsider[])l_pageIndexInfo.getArrayReturned(WEB3GentradeInsider.class);
        
        for(int i = 0; i < l_insiderLast.length; i++ )
        {
            
            //1.11.1) create�����ҏ��ꗗUnit(������)
            WEB3AccInfoInsiderInfoUnit l_insiderInfoUnit = this.createInsiderInfoListUnit(l_insiderLast[i]);
            //1.11.2) add(arg0(=�����ҏ��ꗗUnit) : Object)
            l_list.add(l_insiderInfoUnit);
            
        }
        
        //1.12) toArray( )
        WEB3AccInfoInsiderInfoUnit[] l_insiderInfoUnits = new WEB3AccInfoInsiderInfoUnit[l_list.size()];
        l_list.toArray(l_insiderInfoUnits);

        //1.13) createResponse( )
        WEB3AdminAccInfoInsiderInfoListResponse l_response = 
            (WEB3AdminAccInfoInsiderInfoListResponse)l_request.createResponse();
        
        //1.14) (*2)�v���p�e�B�Z�b�g
        l_response.insideInfoList = l_insiderInfoUnits;
        l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();
        l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
        l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create��������������)<BR>
     * ���������������ҏW����B<BR> 
     * <BR>
     * �P�j�@@�߂�l���� <BR>
     * �@@�߂�l�̌�������������C���X�^���X�i�FString�j�𐶐� <BR>
     * <BR>
     * �Q�j�@@�،���Џ����ǉ�<BR>
     * �@@�،���ЃR�[�h������ǉ�����B<BR>
     * <BR>
     * �@@" institution_code =  ? "<BR>
     * <BR>
     * �R�j�@@���X�����ǉ� <BR>
     * �@@���X������ǉ�����B���X�R�[�h[]�̗v�f����"?"��ǉ�����B<BR> 
     * <BR>
     * �@@" and branch_id in (?, ?,,,) " <BR>
     * <BR>
     * �S�j�@@���������ǉ�<BR>
     * �@@�p�����[�^.�����R�[�h != null�̏ꍇ�A����������ǉ�����B<BR>
     * <BR>
     * �@@" and account_id = ? "<BR>
     * <BR>
     * �T�j�@@���������ǉ�<BR>
     * �@@�p�����[�^.�����R�[�h != null�̏ꍇ�A����������ǉ�����B<BR>
     * <BR>
     * �@@" and product_id = ? "<BR>
     * <BR>
     * �U�j�@@������C���X�^���X��ԋp <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCodes - ���X�R�[�h�z��
     * @@param l_strAccountCode - �����R�[�h
     * @@param l_strProductCode - �����R�[�h
     * @@return String
     */
    protected String createQueryString(String l_strInstitutionCode, String[] l_strBranchCodes,
        String l_strAccountCode, String l_strProductCode)
    {
        
        final String STR_METHOD_NAME = " createQueryString(String, String[], String, String)";
        log.entering(STR_METHOD_NAME);
        
        String l_strSearchCond;
        
       /*
        * �Q�j�@@�،���Џ����ǉ�<BR>
        * �@@�،���ЃR�[�h������ǉ�����B<BR>
        * <BR>
        * �@@" institution_code =  ? "<BR>
        */
        l_strSearchCond = " institution_code = ? ";
        
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
            l_strSearchCond += " and branch_id in (" + l_sbQueryBranchCodes.toString() + ")";          
        }
        
        /*
         *�S�j�@@���������ǉ�<BR>
         * �p�����[�^.�����R�[�h != null�̏ꍇ�A����������ǉ�����B<BR>
         */
        if(l_strAccountCode != null)
        {
            l_strSearchCond += " and account_id = ? ";
        }
                
        //�T�j�@@���������ǉ�
        if(l_strProductCode != null)
        {
            l_strSearchCond += " and product_id = ? ";
        }
        
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
     * �R�j�@@���X�����ǉ� <BR>
     * �@@���X�R�[�h[]�ɊY�����镔�X�h�c�����ׂĒǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]�@@<BR>
     * �@@���X�h�c��<BR>
     * <BR>
     * �@@���A�J�E���g�}�l�[�W��.getBranch(�،���ЁC���X�R�[�h)�ɂĎ擾����B<BR>
     * �@@���،���ЃI�u�W�F�N�g�́A�A�J�E���g�}�l�[�W��.getInstitution(�،���ЃR�[�h)�ɂĎ擾����B<BR>
     * <BR>
     * �S�j�@@���������ǉ�<BR>
     * �@@�p�����[�^.�����R�[�h != null�̏ꍇ�A�����R�[�h�ɊY����������h�c��ǉ�����B<BR>
     * <BR>
     * [add()�Ɏw�肷�����]�@@<BR>
     * �@@�����h�c��<BR>
     * <BR>
     * �@@���A�J�E���g�}�l�[�W��.get�ڋq(�،���ЃR�[�h�C���X�R�[�h�C�����R�[�h)�ɂĎ擾����B<BR>
     * <BR>
     * �T�j�@@���������ǉ�<BR>
     * �@@�p�����[�^.�����R�[�h != null�̏ꍇ�A�����R�[�h�ɊY����������h�c��ǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]�@@<BR>
     * �@@�����h�c��<BR>
     * <BR>
     * �@@���g���v���_�N�g�}�l�[�W��.getProduct(�،���ЁC�����R�[�h)�ɂĎ擾����B<BR>
     * <BR>
     * �U�j�@@�z���ԋp <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCodes - ���X�R�[�h�z��
     * @@param l_strAccountCode - �����R�[�h
     * @@param l_strProductCode - �����R�[�h
     * @@return String[]
     * @@roseuid 415103500340
     */
    protected String[] createQueryContainer(
        String l_strInstitutionCode, 
        String[] l_strBranchCodes,
        String l_strAccountCode, 
        String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createQueryContainer(String, String[], String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�
        List l_lisQueryContainer = new ArrayList();
        
        //�Q�j�@@�،���Џ����ǉ�
        l_lisQueryContainer.add(l_strInstitutionCode);
        
        //�R�j�@@���X�����ǉ�
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //�g���A�J�E���g�}�l�[�W���擾����
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        //�g���v���_�N�g�}�l�[�W��
        WEB3EquityProductManager l_productManager = 
            (WEB3EquityProductManager) (l_finApp.getTradingModule(ProductTypeEnum.EQUITY)).getProductManager();
            
        long l_lngBranchId = 0;
        Institution l_institution;
        try
        {
            l_institution = l_accMgr.getInstitution(l_strInstitutionCode);
            
            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                Branch l_branch = l_accMgr.getBranch(l_institution, l_strBranchCodes[i]);
                l_lngBranchId = l_branch.getBranchId();
                l_lisQueryContainer.add("" + l_lngBranchId);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in ���X�����ǉ�....... ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // �S�j�@@���������ǉ�
        if (l_strAccountCode != null)
        {
            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                try
                {
                    WEB3GentradeMainAccount  l_mainAccount =
                        (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstitutionCode,
                            l_strBranchCodes[i], l_strAccountCode);
                    l_lisQueryContainer.add(String.valueOf(l_mainAccount.getAccountId()));
                    break;
                } catch (WEB3SystemLayerException l_exp){
                    if (i == l_strBranchCodes.length -1)
                    {
                        String l_strMsg = "���͕��X�R�[�h:[" + l_strBranchCodes.toString() + "] "
                                        + "���͌ڋq�R�[�h:[" + l_strAccountCode + "]�ɊY������ڋq���擾�ł��܂���ł����B";
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01387,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strMsg);
                    }
                }
            }
        }
        
        //�T�j�@@���������ǉ�
        if (l_strProductCode != null)
        {
            try
            {
                EqTypeProduct l_product = l_productManager.getProduct(l_institution, l_strProductCode);
                long l_lngProductId = l_product.getProductId();
                l_lisQueryContainer.add("" + l_lngProductId);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

        }

        String[] l_strQueryDataContainers = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryDataContainers);

        log.exiting(STR_METHOD_NAME);
        return l_strQueryDataContainers;     
    }

    /**
     * (create�\�[�g����)<BR>
     *�\�[�g�����������ҏW����B <BR>
     *<BR>
     *�P�j�\�[�g����������쐬<BR>
     *�@@�e�[�u���񕨗������g�p���A�Ή�����\�[�g����������iorder by��j��ҏW����B<BR>
     *�@@�P�|�P�j�@@�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A<BR>
     *�@@�@@�@@�@@�@@�@@�\�[�g������������쐬����B<BR>
     *�@@�@@�P�|�P�|�P�j�@@�\�[�g�L�[.�L�[���ڂɑΉ�����e�[�u���񕨗�����<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�\�[�g�����ɒǉ�����B<BR>
     *<BR>
     *�@@�@@�@@[�����ҏ��ꗗUnit.���X�R�[�h�̏ꍇ]<BR>
     *�@@�@@�@@�@@�����҃}�X�^�[.���XID<BR>
     *�@@�@@�@@[�����ҏ��ꗗUnit.�ڋq�R�[�h�̏ꍇ]<BR>
     *�@@�@@�@@�@@substr(�����҃}�X�^�[.����ID, 9, 6)<BR>
     *�@@�@@�@@[�����ҏ��ꗗUnit.�����R�[�h�̏ꍇ]<BR>
     *�@@�@@�@@�@@�����҃}�X�^�[.����ID<BR>
     *�@@�@@�@@[�����ҏ��ꗗUnit.�֌W�R�[�h�̏ꍇ]<BR>
     *�@@�@@�@@�@@�����҃}�X�^�[.�֌W�敪<BR>
     *�@@�@@�@@[�����ҏ��ꗗUnit.�������̏ꍇ]<BR>
     *�@@�@@�@@�@@�����҃}�X�^�[.������<BR>
     *�@@�@@�@@[�����ҏ��ꗗUnit.��E���̏ꍇ]<BR>
     *�@@�@@�@@�@@�����҃}�X�^�[.��E��<BR>
     *�@@�@@�@@[�����ҏ��ꗗUnit.�o�^�󋵋敪�̏ꍇ]<BR>
     *�@@�@@�@@�@@�����҃}�X�^�[.�o�^�󋵋敪<BR>
     *<BR>
     *�@@�@@�P�|�P�|�Q�j�@@�\�[�g�L�[.�����^�~���ɑΉ�����\�[�g����(asc or desc)��<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�\�[�g�����ɒǉ�����B<BR>
     *<BR>
     *�Q�j �쐬�����\�[�g�����������ԋp����B<BR>
     * @@param  l_sortKeys - �\�[�g�L�[
     * @@return String
     * @@roseuid 4164C934006A
     */
    protected String createSortCond(WEB3AccInfoSortKey[] l_sortKeys)
    {
        StringBuffer l_sbQuery = new StringBuffer();
        
        int l_intLength = l_sortKeys.length;

        for (int i = 0; i < l_intLength; i++ )
        {
            
            if(WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" branch_id ");
            }
            else if(WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
            	//substr(�����҃}�X�^�[.����ID, 9, 6)
                l_sbQuery.append(" substr(account_id , 9 , 6) ");
            }
            else if(WEB3AccInfoKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" product_id ");
            }
            else if(WEB3AccInfoKeyItemDef.RELATION_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" relation_div ");
            }
            else if(WEB3AccInfoKeyItemDef.OFFICER_NAME.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" officer_name ");
            }
            else if(WEB3AccInfoKeyItemDef.POST_NAME.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" post_name ");
            }
            else if(WEB3AccInfoKeyItemDef.REGIST_DIV.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" regist_div ");
            }
            else
            {
                continue;
            }
            String l_sort = null;
            if("A".equals(l_sortKeys[i].ascDesc))
            {
                l_sort = "Asc";
            }
            else
            {
                l_sort = "Desc";
            }
            l_sbQuery.append(l_sort);
            
            if(i < l_intLength - 1)
            {
                l_sbQuery.append(", ");
            }
                        
        }
        
        return l_sbQuery.toString();

    }
    
    /**
     * (create�����ҏ��ꗗUnit)<BR>
     *�����̓����҃I�u�W�F�N�g���A�����ҏ��ꗗUnit���b�Z�[�W�f�[�^���쐬����B<BR>                                                  
     *             <BR>                                                       
     *�P�j�@@�����ҏ��ꗗUnit����   <BR>                                     
     *�@@�����ҏ��ꗗUnit�𐶐�����B  <BR>                                  
     *                      <BR>                                              
     *�Q�j�@@�v���p�e�B�Z�b�g     <BR>                                         
     *�@@�P�j�ɂĐ������������ҏ��ꗗUnit�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     *                                                 <BR>                  
     *�@@�����ҏ��ꗗUnit.���X�R�[�h = ���X(*1).���X�R�[�h     <BR>          
     *                                                    <BR>                
     *�@@�����ҏ��ꗗUnit.�ڋq�R�[�h = �ڋq(*2).get�\���ڋq�R�[�h  <BR>             
     *                                                                    
     *�@@�����ҏ��ꗗUnit.�ڋq���i�����j = �ڋq(*2).�ڋq���i�����j  <BR>     
     *�@@�����ҏ��ꗗUnit.�����R�[�h = ��������(*3).�����R�[�h <BR>          
     *                                                   <BR>                 
     *�@@�����ҏ��ꗗUnit.������ = ��������(*3).������   <BR>                
     *                                                        <BR>            
     *�@@�����ҏ��ꗗUnit.�֌W�R�[�h = �p�����[�^.������.�֌W�敪   <BR>     
     *�@@�����ҏ��ꗗUnit.������ = �p�����[�^.������.������    <BR>         
     *�@@�����ҏ��ꗗUnit.��E�� = �p�����[�^.������.��E��     <BR>         
     *�@@�����ҏ��ꗗUnit.�o�^�󋵋敪 = �p�����[�^.������.�o�^�󋵋敪  <BR>
     *                                                        <BR>            
     *�R�j�v���p�e�B�Z�b�g���������ҏ��ꗗUnit��ԋp����B <BR>             
     *                                        <BR>                            
     *(*1)�A�J�E���g�}�l�[�W��.getBranch(�p�����[�^.������.���XID)�ɂĎ擾<BR>
     *(*2)�A�J�E���g�}�l�[�W��.get�ڋq(�p�����[�^.������.����ID)�ɂĎ擾  <BR>
     *(*3)�g�������v���_�N�g�}�l�[�W��.getProduct(�p�����[�^.������.����ID)�ɂĎ擾<BR>
     *
     *@@param l_insiders - ������
     *@@return WEB3AccInfoInsiderInfoUnit
     */
    protected WEB3AccInfoInsiderInfoUnit createInsiderInfoListUnit(WEB3GentradeInsider l_insiders) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createInsiderInfoListUnit(WEB3GentradeInsider[])";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoInsiderInfoUnit l_insiderInfoUnit = new WEB3AccInfoInsiderInfoUnit();

        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) GtlUtils.getFinApp().getAccountManager();
        WEB3EquityProductManager l_productManager = 
            (WEB3EquityProductManager) (GtlUtils.getFinApp().getTradingModule(ProductTypeEnum.EQUITY)).getProductManager();
        
        long l_lngBranchId = ((InsiderRow)(l_insiders.getDataSourceObject())).getBranchId();
        long l_lngAccountId = ((InsiderRow)(l_insiders.getDataSourceObject())).getAccountId();
        long l_lngProductId = ((InsiderRow)(l_insiders.getDataSourceObject())).getProductId();
        
        try
        {
            //���X�R�[�h
            String l_strBranchCode = l_accMgr.getBranch(l_lngBranchId).getBranchCode();
            //�����R�[�h
            String l_strAccountCode = ((WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_lngAccountId)).getDisplayAccountCode();
            //�ڋq���i�����j
            String l_strAccountName = 
                ((MainAccountRow)l_accMgr.getMainAccount(l_lngAccountId).getDataSourceObject()).getFamilyName();
            //�����R�[�h
            String l_strProductCode = ((WEB3EquityProduct)l_productManager.getProduct(l_lngProductId)).getProductCode();
            //������
            String l_strProductName = 
                ((WEB3EquityProduct)l_productManager.getProduct(l_lngProductId)).getStandardName();
        
            l_insiderInfoUnit.branchCode = l_strBranchCode;
            l_insiderInfoUnit.accountCode = l_strAccountCode;
            l_insiderInfoUnit.accountName = l_strAccountName;
            l_insiderInfoUnit.productCode = l_strProductCode;
            l_insiderInfoUnit.productName = l_strProductName;
            l_insiderInfoUnit.relationCode = l_insiders.getRelationDiv();
            l_insiderInfoUnit.executive = l_insiders.getOfficerName();
            l_insiderInfoUnit.position = l_insiders.getPostName();
            if(((InsiderRow)l_insiders.getDataSourceObject()).getRegistDivIsSet())
            {
                l_insiderInfoUnit.registStateDiv = ((InsiderRow)l_insiders.getDataSourceObject()).getRegistDiv();
            }
            
        }
        catch(NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        return l_insiderInfoUnit;
    }

}
@
