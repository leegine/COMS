head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAccProductTradeStopListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�Ǘ��Ҍڋq�����ʎ����~�ꗗ�T�[�r�X�����N���X)
                        (WEB3AdminEquityAccProductTradeStopListServiceImpl.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AccountProductOrderStopParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.define.WEB3AdminKeyItemDef;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopInfoUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccTradeStopSortKey;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopListService;

import webbroker3.equity.WEB3EquityProductManager;

/**
 * �i�Ǘ��Ҍڋq�����ʎ����~�ꗗ�T�[�r�X�����N���X�j<BR>
 * <BR>
 * �Ǘ��Ҍڋq�����ʎ����~�ꗗ�T�[�r�X�����N���X<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminEquityAccProductTradeStopListServiceImpl class<BR>
 * <BR>
 * @@author Rajesh Sharma
 * @@version 1.0
 */
public class WEB3AdminEquityAccProductTradeStopListServiceImpl extends
         WEB3ClientRequestService implements WEB3AdminEquityAccProductTradeStopListService
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAccProductTradeStopListServiceImpl.class);
   /**
    * @@roseuid 41FD944D0186
    */
   public WEB3AdminEquityAccProductTradeStopListServiceImpl()
   {

   }

   /**
    * �ڋq�����ʎ����~�ꗗ�\���������s���B<BR>
    * <BR>
    * this.get�ꗗ���()���R�[������B<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * Execute WEB3AdminEquityAccProductTradeStopListService screen process<BR>
    * <BR>
    * call this.getListScreen()<BR>
    * <BR>
    * @@param l_request - �i���N�G�X�g�j<BR>
    * <BR>
    * ���N�G�X�g<BR>
    * <BR>
    * l_request<BR>
    * <BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException WEB3BaseException
    * @@roseuid 41987EF002D6
    */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
                            "WEB3AdminEquityAccProductTradeStopListServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminPMAccProductTradeStopListRequest)
        {
            try
            {
                l_response = this.getListScreen((WEB3AdminPMAccProductTradeStopListRequest)
                                                                                         l_request);
            } catch (DataQueryException l_exce)
            {
                log.debug(l_exce.getMessage());
                String l_strMsg = "Error while aquiring the Data ";
                log.error(l_strMsg, l_exce);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exce.toString(),
                    l_exce);
            } catch (DataNetworkException l_exce)
            {
                log.debug(l_exce.getMessage());
                String l_strMsg = "Error while aquiring the Data ";
                log.error(l_strMsg, l_exce);
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exce.toString(),
                    l_exce);
            } catch (NotFoundException l_exce)
            {
                log.debug(l_exce.getMessage());
                String l_strMsg = "Error while aquiring the Data ";
                log.error(l_strMsg, l_exce);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exce.toString(),
                    l_exce);
            }
        } else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT ���N�G�X�g NOT �ڋq�����ʎ����~�ꗗ���N�G�X�g");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

   /**
    * (get�ꗗ���())<BR>
    * <BR>
    * �ڋq�����ʎ����~�ꗗ�\���������s���B<BR>
    * <BR>
    * �V�[�P���X�}<BR>
    * �u(�Ǘ��Ҍڋq�����ʎ����~�ꗗ�T�[�r�X)get�ꗗ��ʁv
    * �Q��<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * (getListScreen)<BR>
    * <BR>
    * Execute WEB3AdminEquityAccProductTradeStopListService screen process<BR>
    * <BR>
    * Refer to the sequence diagram "(administrator; account product trade stop list
    * service) getListScreen"<BR>
    * <BR>
    * @@param l_request - ���N�G�X�g�f�[�^<BR>
    * <BR>
    * �Ǘ��ҁE�ڋq�����ʎ����~�ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
    * <BR>
    * ---------<English>----------<BR>
    * <BR>
    * l_request<BR>
    * <BR>
    * WEB3AdminPMAccProductTradeStopListRequest object<BR>
    * <BR>
    * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListResponse
    * @@throws WEB3BaseException WEB3BaseException
    * @@throws DataNetworkException DataNetworkException
    * @@throws DataQueryException DataQueryException
    * @@throws DataFindException DataFindException
    * @@throws NotFoundException NotFoundException
    * @@roseuid 41987F230268
    */
    protected WEB3AdminPMAccProductTradeStopListResponse
        getListScreen(WEB3AdminPMAccProductTradeStopListRequest l_request)
            throws WEB3BaseException, DataFindException, DataNetworkException,
                DataQueryException, NotFoundException
    {
        WEB3AdminPMAccProductTradeStopListResponse l_response = null;
        WEB3Administrator l_web3Administrator = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_genTradeAccountManager = null;
        AccountProductOrderStopParams[]  l_accountProductOrderStopParams = null;
        WEB3AdminPMAccProductTradeStopInfoUnit l_accProductTradeStopInfoUnit =
            new WEB3AdminPMAccProductTradeStopInfoUnit();
        WEB3AdminPMEquityDataManager l_equityDataManager = new WEB3AdminPMEquityDataManager();
        WEB3AdminPMAccTradeStopSortKey[] l_sortKeys = null;
        WEB3PageIndexInfo l_pageIndexInfo = null;
        String[] l_strBranchCodes = null;
        String l_strAccountCode = null;
        String l_strProductCode = null;
        String l_strQueryCond = null;
        String l_strInstituionCode = null;
        String l_strSortCondList = null;
        Object[] l_dataContainers = null;
        List l_lisGetAccTradeStopInfo = null;
        ArrayList l_lisProductTradeStopInfoUnit = null;
        WEB3AdminPMAccProductTradeStopInfoUnit[] l_productTradeStopInfoUnits = null;
        int i = 0;
        int l_intPageIndex = 0;
        int l_intPageSize = 0;
        int l_intProductStopSize = 0;

        // Step 1.1 Input request values are validated
        l_request.validate();

        // Step 1.2 get the instance of Administrator
        l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step  1.3 validate authority
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_ACCOUNT_PRODUCT_STOP,
            false);

        //Step 1.4 call validate Branch Permission of  Administrator
        l_strBranchCodes = l_request.branchCodeList;
        l_web3Administrator.validateBranchPermission(l_strBranchCodes);

        /*Creating the variable to store the values of l_request,
            which are called in the following steps.*/
        l_strAccountCode = l_request.accountCode;
        l_strProductCode = l_request.productCode;
        l_sortKeys = l_request.sortKeys;

        l_strInstituionCode = l_web3Administrator.getInstitutionCode();

        //Step 1.5 Create the query String
        l_strQueryCond = this.createQueryString
                                (l_strInstituionCode, l_strBranchCodes, l_strAccountCode, l_strProductCode);
		
        //Step 1.6 Create the Object to hold the dataContainer
        l_dataContainers = this.createQueryDataContainer
                                (l_strInstituionCode,l_strBranchCodes,l_strAccountCode, l_strProductCode);

        //Step 1.7 Create the Sord condition List
        l_strSortCondList = this.createSortCond(l_sortKeys);

        //Step 1.8  Acquire the list of account product trade stop
        l_genTradeAccountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        l_lisGetAccTradeStopInfo  = l_genTradeAccountManager.getAccountProductOrderStopList
                      (l_strQueryCond, l_dataContainers, l_strSortCondList);

        //Step 1.9 Check for List is null
        if (l_lisGetAccTradeStopInfo == null)
        {
            // 1.9.1 Create a response object
           l_response = (WEB3AdminPMAccProductTradeStopListResponse) l_request.createResponse();

           //Step 1.9.2 Set the property to the response object
           l_response.totalPages = "1";
           l_response.totalRecords = "0";
           l_response.pageIndex = "1";
           l_response.accProductTradeStopInfo = null;

           //Step 1.9.3 Return the response object
           return l_response;
        }

        //Step 1.10 Create ArrayList to store WEB3AdminPMAccProductTradeStopInfoUnit
        l_lisProductTradeStopInfoUnit = new ArrayList();

        /*
         * Step 1.11 Calculation of From Index and LastIndex for the loop
         * Code is been modified as we are using the standard function for
         * From Index and To Index
         */
        l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        l_intPageSize = Integer.parseInt(l_request.pageSize);
        l_pageIndexInfo =
            new WEB3PageIndexInfo(l_lisGetAccTradeStopInfo, l_intPageIndex, l_intPageSize);

        l_accountProductOrderStopParams =
            (AccountProductOrderStopParams[]) l_pageIndexInfo
            .getArrayReturned(AccountProductOrderStopParams.class);

        l_intProductStopSize = l_accountProductOrderStopParams.length;
        //Step 1.11 Loop process from fromIndex to toIndex
        for (i = 0; i < l_intProductStopSize; i++)
        {
            //Step 1.11.1 Create WEB3AdminPMAccProductTradeStopInfoUnit
            l_accProductTradeStopInfoUnit =
                 l_equityDataManager.createAccProductTradeStopInfoUnit
                        (l_accountProductOrderStopParams[i]);

            //Step 1.11. 2 Set the created WEB3AdminPMAccProductTradeStopInfoUnit to ArrayList
            l_lisProductTradeStopInfoUnit.add(l_accProductTradeStopInfoUnit);
        }

        //Step 1.12 Create a array to store the Product Trade Stop Info Unit list
        l_productTradeStopInfoUnits =
            new WEB3AdminPMAccProductTradeStopInfoUnit[l_lisProductTradeStopInfoUnit.size()];
        l_lisProductTradeStopInfoUnit.toArray(l_productTradeStopInfoUnits);

        // 1.13  Create a response object
        l_response = (WEB3AdminPMAccProductTradeStopListResponse) l_request.createResponse();

        //Step 1.14 Set the property to the response object
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(
            l_pageIndexInfo.getTotalPages());
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(
            l_pageIndexInfo.getTotalRecords());
        l_response.pageIndex = WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getPageIndex());
        l_response.accProductTradeStopInfo = l_productTradeStopInfoUnits;

        //Step 1.15 Return the response object
        return l_response;
    }

   /**
    * �icreate��������������j<BR>
    * <BR>
    * ����������������쐬����B<BR>
    * <BR>
    * �P�j�ȉ��̏�����\������������������쐬����B<BR>
    * <BR>
    * �@@[����]<BR>
    * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h�@@����<BR>
    * �@@�@@�K�p�I���N���� >= ���ݓ��t<BR>
    * <BR>
    * �@@�������������� = " institution_code = ? "<BR>
    * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and apply_end_date >= ? "<BR>
    * <BR>
    * �Q�j�p�����[�^.���X�R�[�h != null�̏ꍇ�A<BR>
    * �@@�ȉ��̏�������������������ɒǉ�����B<BR>
    * �@@�����X�R�[�h�ɊY�����镔�XID�����������Ƃ���B<BR>
    * <BR>
    * �@@�������������� += "and branch_id = ? "<BR>
    * <BR>
    * �R�j�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
    * �@@�ȉ��̏�������������������ɒǉ�����B<BR>
    * �@@�������R�[�h�ɊY���������ID�����������Ƃ���B<BR>
    * <BR>
    * �@@�������������� += "and account_id = ? "<BR>
    * <BR>
    * �S�j�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
    * �@@�ȉ��̏�������������������ɒǉ�����B<BR>
    * �@@�������R�[�h�ɊY���������ID�����������Ƃ���B<BR>
    * <BR>
    * �@@�������������� += "and product_id = ? "<BR>
    * <BR>
    * �T�j�쐬�������������������ԋp����B<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * (createQueryString)<BR>
    * <BR>
    * 1)Create query string<BR>
    * <BR>
    * �@@[Condition]<BR>
    * �@@�@@institution_code = parameter.institutionCode�@@and<BR>
    * �@@�@@apply_start_date >= currentDate<BR>
    * <BR>
    * �@@l_strQueryCond = " institution_code = ? "<BR>
    * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and apply_end_date >= ? "<BR>
    * <BR>
    * 2)If parameter.branchCode != null<BR>
    * �@@Add the following condition tol_strQueryCond<BR>
    * �@@��Assumed that branch_id conrresponding to branchCode is l_strQueryCond .<BR>
    * <BR>
    * �@@l_strQueryCond  += "and branch_id = ? "<BR>
    * <BR>
    * 3)If parameter.accountCode != null<BR>
    * �@@Add the following condition to l_strQueryCond .<BR>
    * �@@��Assumed that account_id corresponding to accountCode is l_strQueryCond <BR>
    * <BR>
    * �@@l_strQueryCond  += "and account_id = ? "<BR>
    * <BR>
    * 4)If parameter.productCode != null<BR>
    * �@@Add the following condition to l_strQueryCond<BR>
    * �@@��Assumed that product_id corresponding to productCode is l_strQueryCond <BR>
    * <BR>
    * �@@l_strQueryCond  += "and product_id = ? "<BR>
    * <BR>
    * 5)Return created l_strQueryCond <BR>
    * <BR>
    * @@param l_strBranchCode - �i���X�R�[�h�j<BR>
    * <BR>
    * ���X�R�[�h<BR>
    * <BR>
    * l_strBranchCode<BR>
    * <BR>
    * @@param l_strAccountCode - �i�����R�[�h�j<BR>
    * <BR>
    * �����R�[�h<BR>
    * <BR>
    * l_strAccountCode<BR>
    * <BR>
    * @@param l_strProductCode - �i�����R�[�h�j<BR>
    * <BR>
    * �����R�[�h
    * <BR>
    * l_strProductCode<BR>
    * <BR>
    * @@return java.lang.String
    * @@roseuid 4198823C01BD
    */
   protected String
	 createQueryString(String l_strInstituionCode, String[] l_strBranchCodes, String l_strAccountCode, String l_strProductCode)
	 throws WEB3BaseException,DataFindException, DataNetworkException, DataQueryException
   {
	   final String STR_METHOD_NAME = "createQueryString()";
	   log.entering(STR_METHOD_NAME);
	    WEB3AdminPMEquityDataManager l_equityDataManager = new WEB3AdminPMEquityDataManager();
	
	   //Step 1: Create query string
	   String l_strQueryCond = new String("institution_code = ? and apply_end_date >= ? ");

	   //Step  2: If parameter.branchCode is not null
	   String[] l_strBranchIdList = l_equityDataManager.getBranchId(l_strInstituionCode,l_strBranchCodes);
	   if (l_strBranchIdList == null)
	   {
		   String l_strMsg = "No data for the Branch";
		   log.error("Error while aquiring the Branch Id ");
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                   this.getClass().getName() + "." + STR_METHOD_NAME, l_strMsg);
	   }
       l_strQueryCond += "and branch_id in (";
       StringBuffer l_strEdit = new StringBuffer();
	   for(int i = 0; i < l_strBranchIdList.length ; i++)
	   {
	       l_strEdit.append(" ?,");
	   } 
       l_strQueryCond += (l_strEdit.substring(0,l_strEdit.length() - 1) + ") ").toString();

	   //Step  3: If parameter.accountCode is not null
	   if (l_strAccountCode != null)
	   {
		   l_strQueryCond += "and account_id = ? ";
	   }

	   //Step  4: If parameter.productCode is not null
	   if (l_strProductCode != null)
	   {
		   l_strQueryCond += "and product_id = ? ";
	   }

	   log.exiting(STR_METHOD_NAME);

	   //StepL 5 return the string object
	   return l_strQueryCond;
   }

   /**
    * �icreate���������f�[�^�R���e�i�j<BR>
    * <BR>
    * ���������f�[�^�R���e�i���쐬����B<BR>
    * <BR>
    * �P�jArrayList�𐶐�����B<BR>
    * <BR>
    * �Q�j�ȉ��̒l���ォ�珇�ɐ�������ArrayList��<BR>
    * �@@�@@�Z�b�g����B<BR>
    * �@@�@@�E�p�����[�^.�،���ЃR�[�h<BR>
    * �@@�@@�E���ݎ���(*1)�̓��t����<BR>
    * <BR>
    * �R�j�p�����[�^.���X�R�[�h != null�̏ꍇ�A<BR>
    * �@@�p�����[�^.���X�R�[�h�ɊY�����镔�XID��ArrayList�ɒǉ�����B<BR>
    * �@@�����X�R�[�h�ɊY�����镔�XID���擾�ł��Ȃ������ꍇ�A<BR>
    * �@@�@@�u�Y�����X�f�[�^�Ȃ��v�̗�O���X���[����B<BR>
    * <BR>
    * class : WEB3BusinessLayerException<BR>
    * tag : BUSINESS_ERROR_01386<BR>
    * <BR>
    * �S�j�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
    * �@@�p�����[�^.�����R�[�h�ɊY���������ID��ArrayList�ɒǉ�����B<BR>
    * �@@�������R�[�h�ɊY���������ID���擾�ł��Ȃ������ꍇ�A<BR>
    * �@@�@@�u�Y���ڋq�f�[�^�Ȃ��v�̗�O���X���[����B<BR>
    * <BR>
    * �T�j�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
    * �@@�ȉ��̏����ɂ��A�p�����[�^�Z�b�g��ǉ�����B<BR>
    * <BR>
    * �@@[�p�����[�^.�����R�[�h == "00000"(�S����)�̏ꍇ]<BR>
    * �@@�@@0��ArrayList�ɒǉ�����B<BR>
    * �@@[��L�ȊO�̏ꍇ]<BR>
    * �@@�@@�p�����[�^.�����R�[�h�ɊY���������ID��ArrayList�ɒǉ�����B<BR>
    * �@@�@@�������R�[�h�ɊY���������ID���擾�ł��Ȃ������ꍇ�A<BR>
    * �@@�@@�u�Y�������f�[�^�Ȃ��v�̗�O���X���[����B<BR>
    * <BR>
    * class : WEB3BusinessLayerException<BR>
    * tag : BUSINESS_ERROR_00391<BR>
    * <BR>
    * �U�j�쐬����ArrayList.toArray()�̖߂�l��ԋp����B<BR>
    * <BR>
    * (*1)���ݎ���<BR>
    * ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TIMESTAMP_TAG)<BR>
    * �ɂĎ擾�������ݎ���<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * (createQueryDataContainer)<BR>
    * <BR>
    * Carete queryDataContainer<BR>
    * <BR>
    * 1)Created ArrayList<BR>
    * <BR>
    * 2)Set the following values to the created ArrayList.<BR>
    * �@@�@@�Eparameter.institutionCode<BR>
    * �@@�@@�EDate in timeStamp(*1)<BR>
    * <BR>
    * 3)If parameter.branchCode != null<BR>
    * �@@Add branch_id corresponding to parameter.branchCode to ArrayList<BR>
    * �@@��If it is disable to get  branch_id corresponding to branchCode,<BR>
    * �@@�@@Throw the exception [No corresponding branch data]<BR>
    * <BR>
    * class : WEB3BusinessLayerException<BR>
    * tag : BUSINESS_ERROR_01386<BR>
    * <BR>
    * 4)If parameter.accountCode != null<BR>
    * �@@Add account_id corresponding to accountCode to ArrayList<BR>
    * �@@��If it is disable to get  account_id corresponding to accountCode,<BR>
    * �@@�@@Throw the exception [No corresponding account data]<BR>
    * <BR>
    * 5)If parameter.productCode != null<BR>
    * �@@Add parameter set based on the following conditions.<BR>
    * <BR>
    * �@@[If parameter.productCode == "00000"(all products)]<BR>
    * �@@�@@Add 0 to ArrayList<BR>
    * �@@[For other cases]<BR>
    * �@@�@@Add product_id corresponding to parameter.productCode to ArrayList<BR>
    * �@@�@@��If it is disable to get product_id corresponding to productCode,<BR>
    * �@@�@@Throw the exception [No corresponding product data]<BR>
    * <BR>
    * class : WEB3BusinessLayerException<BR>
    * tag : BUSINESS_ERROR_00391<BR>
    * <BR>
    *
    * �U�jReturn the return value of created ArrayList.toArray()<BR>
    * <BR>
    * (*1)timeStamp<BR>
    * timeStamp acquired at <BR>
    * ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManageme
    * nt.TIMESTAMP_TAG)<BR>
    * <BR>
    * @@param l_strInstitutionCode - �i�،���ЃR�[�h�j<BR>
    * <BR>
    * �،���ЃR�[�h<BR>
    * <BR>
    * l_strInstitutionCode<BR>
    * <BR>
    * @@param l_strBranchCode - �i���X�R�[�h�j<BR>
    * <BR>
    * ���X�R�[�h<BR>
    * <BR>
    * l_strBranchCode<BR>
    * <BR>
    * @@param l_strAccountCode - �i�����R�[�h�j<BR>
    * <BR>
    * �����R�[�h<BR>
    * <BR>
    * l_strAccountCode<BR>
    * <BR>
    * @@param l_strProductCode - �i�����R�[�h�j<BR>
    * <BR>
    * @@throws WEB3BaseException WEB3BaseException
    * �����R�[�h
    * <BR>
    * l_strProductCode<BR>
    * <BR>
    * @@return Object[]
    * @@throws WEB3BaseException WEB3BaseException
    * @@throws DataFindException DataFindException
    * @@throws DataNetworkException DataNetworkException
    * @@throws DataQueryException DataQueryException
    * @@roseuid 4198823C01DC
    */
    protected Object[] createQueryDataContainer(String l_strInstitutionCode,
        String[] l_strBranchCodes, String l_strAccountCode, String l_strProductCode)
            throws WEB3BaseException, DataFindException, DataNetworkException,
                DataQueryException
    {
        final String STR_METHOD_NAME = "createQueryDataContainer()";
        log.entering(STR_METHOD_NAME);
        
		//getBranchId
		WEB3AdminPMEquityDataManager l_equityDataManager = new WEB3AdminPMEquityDataManager();
		String[] l_strBranchIdList = l_equityDataManager.getBranchId(l_strInstitutionCode,l_strBranchCodes);
		if (l_strBranchIdList == null)
		{
			String l_strMsg = "No data for the Branch";
			log.error("Error while aquiring the Branch Id ");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01386,
				this.getClass().getName() + "." + STR_METHOD_NAME, l_strMsg);

		}

        String l_strAccountId = null;
        String l_strProductId = null;
        Date l_dateTimeStamp = new Date();
        Object[] l_queryData = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = null;
        WEB3GentradeBranch l_gentradeBranch = null;
        long l_lngBranchId;
        long l_lngAccountId;
        long l_lngProductId;

        //Step 1 : Created ArrayList
        ArrayList l_arrQueryDataList = new ArrayList();

        //Step 2 : Add the Instititution Code and  Time Stamp to the ArrayList
        l_arrQueryDataList.add(l_strInstitutionCode);
        l_dateTimeStamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        l_arrQueryDataList.add(WEB3DateUtility.toDay(l_dateTimeStamp));
		
        l_gentradeAccountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        /*Step 3 : If Branch Code is not null then add the l_strBranchCode to array list,
                        else throw  exception*/
        if (l_strBranchCodes != null)
        {
            for (int i = 0; i < l_strBranchIdList.length; i++)
            {
                l_arrQueryDataList.add(l_strBranchIdList[i]);
            }	
        }

        /*Step 4 : If Account Code is not null then add the l_strAccountCode to array list,
                        else throw  exception*/
        if (l_strAccountCode != null)
        {
            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                try
                {
					WEB3GentradeMainAccount  l_gentradeMainAccount = (WEB3GentradeMainAccount)
					    l_gentradeAccountManager.getMainAccount(l_strInstitutionCode,
					    l_strBranchCodes[i], l_strAccountCode);
						l_lngAccountId = l_gentradeMainAccount.getAccountId();
						l_strAccountId = new Long(l_lngAccountId).toString();
						l_arrQueryDataList.add(l_strAccountId);
						break;
                } catch (WEB3SystemLayerException l_exp){
                	if (i == l_strBranchCodes.length -1)
                	{
						String l_strMsg = "No data for the Account";
						log.error("Error while aquiring the Account Id ");
						throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_01387,
							this.getClass().getName() + "." + STR_METHOD_NAME, l_strMsg, l_exp);
                	}
                }
            }
        }

        /*Step 5 : If Product Code is not null then add corresponding l_strProductCode to
                        array list, else throw  exception*/
        if (l_strProductCode != null)
        {
            /*Step 5.1 : if ProductCode == "00000"(all products) then add "0" to array list
                             else add l_strProductCode to the arraylist */
            if (l_strProductCode.equals("00000"))
            {
                l_arrQueryDataList.add("0");
            } else
            {
                try
                {

                    TradingModule l_tradingModule =
                        l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                    WEB3EquityProductManager l_equityProductManager =
                        (WEB3EquityProductManager) l_tradingModule.getProductManager();
                    WEB3GentradeInstitution l_instituion =
                        new WEB3GentradeInstitution(l_strInstitutionCode);
                    EqTypeProduct l_eqTypeProduct  =
                        l_equityProductManager.getProduct(l_instituion, l_strProductCode);
                    l_lngProductId = l_eqTypeProduct.getProductId();
                    l_strProductId = new Long(l_lngProductId).toString();
                    l_arrQueryDataList.add(l_strProductId);
                } catch (NotFoundException l_exp)
                {
                    String l_strMsg = "No data for the Product";
                    log.error("Error while aquiring the Product Id ");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                       this.getClass().getName() + "." + STR_METHOD_NAME, l_strMsg, l_exp);
                }
            }
        }
        //Step 6:  return value of created ArrayList.toArray()
        l_queryData = l_arrQueryDataList.toArray();
        log.exiting(STR_METHOD_NAME);
        return l_queryData;
    }

   /**
    * �icreate�\�[�g�����j<BR>
    * <BR>
    * �\�[�g�������쐬����B<BR>
    * <BR>
    * �P�j��̃\�[�g����������("")���쐬����B<BR>
    * �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏�����<BR>
    * �@@�J��Ԃ��B<BR>
    * �@@�Q�|�P�j�����Ώۂ̃\�[�g�L�[.�L�[���ڂ̒l�ɂ��A<BR>
    * �@@�@@�@@�@@�@@�쐬�����\�[�g����������ɏ�����ǉ�����B<BR>
    * �@@�@@�@@�@@�@@���\�[�g���������񂪋�("")�łȂ��ꍇ�́A�ȉ��̏������s���O��<BR>
    * �@@�@@�@@�@@�@@�@@","(�J���})���\�[�g����������ɒǉ����邱�ƁB<BR>
    * <BR>
    * �@@�@@�@@�@@�@@�L�[���ڂ��A<BR>
    * �@@�@@�@@�@@�@@["���X�R�[�h"�̏ꍇ]<BR>
    * �@@�@@�@@�@@�@@�@@�\�[�g���������� += "branch_id "<BR>
    * �@@�@@�@@�@@�@@["�ڋq�R�[�h"�̏ꍇ]<BR>
    * �@@�@@�@@�@@�@@�@@�\�[�g���������� += "account_id "<BR>
    * �@@�@@�@@�@@�@@["�����R�[�h"�̏ꍇ]<BR>
    * �@@�@@�@@�@@�@@�@@�\�[�g���������� += "product_id "<BR>
    * �@@�@@�@@�@@�@@["�L������From"�̏ꍇ]<BR>
    * �@@�@@�@@�@@�@@�@@�\�[�g���������� += "apply_start_date "<BR>
    * <BR>
    * �@@�Q�|�Q�j�����Ώۂ̃\�[�g�L�[.�����^�~���̒l�ɂ��A<BR>
    * �@@�@@�@@�@@�@@�����^�~�����\�[�g����������ɒǉ�����B<BR>
    * <BR>
    * �@@�@@�@@�@@�@@�����^�~�����A<BR>
    * �@@�@@�@@�@@�@@["A�F����"�̏ꍇ]<BR>
    * �@@�@@�@@�@@�@@�@@�\�[�g���������� += "asc "<BR>
    * �@@�@@�@@�@@�@@["D�F�~��"�̏ꍇ]<BR>
    * �@@�@@�@@�@@�@@�@@�\�[�g���������� += "desc "<BR>
    * <BR>
    * �Q�j�쐬�����\�[�g�����������ԋp����B<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * (createSortCond)<BR>
    * <BR>
    * Create sortCond<BR>
    * <BR>
    * 1)Create an empty sortCondList("")<BR>
    * 2)Loop process for as many times as elements of parameter.sortKeys <BR>
    * �@@2-1)Add the conditions to created sortCondList based on the values of
    * sortKeys.keyItem for the process<BR>
    * �@@�@@�@@�@@�@@��If sortCondList is not empty(""), add ","(comma) to sortCondList
    * before processing.<BR>
    * <BR>
    * �@@�@@�@@�@@�@@If a keyItem is ["branchCode"]<BR>
    * �@@�@@�@@�@@�@@�@@sortCondList += "branch_id "<BR>
    * �@@�@@�@@�@@�@@If a keyItem is ["accountCode"]<BR>
    * �@@�@@�@@�@@�@@�@@sortCondList += "account_id "<BR>
    * �@@�@@�@@�@@�@@If a keyItem is ["productCode"]<BR>
    * �@@�@@�@@�@@�@@�@@sortCondList += "product_id "<BR>
    * �@@�@@�@@�@@�@@If a keyItem is [" expirationStartDate"]<BR>
    * �@@�@@�@@�@@�@@�@@sortCondList += "apply_start_date "<BR>
    * <BR>
    * �@@2-2�jAdd ascDesc to sortCondList according to the value of sortKeys.ascDesc
    * for the process<BR>
    * <BR>
    * �@@�@@�@@�@@�@@If ascDesc is ["A: ASC"]<BR>
    * �@@�@@�@@�@@�@@�@@sortCondList += "asc "<BR>
    * �@@�@@�@@�@@�@@If ascDesc is ["D: DESC"]<BR>
    * �@@�@@�@@�@@�@@�@@sortCondList += "desc "<BR>
    * <BR>
    * 2)Return the created sortCondList<BR>
    * <BR>
    * @@param l_sortKeys - �i�\�[�g�L�[�j
    * <BR>
    * �\�[�g�L�[<BR>
    * <BR>
    * l_sortKeys<BR>
    * <BR>
    * @@return java.lang.String
    * @@roseuid 4198823C01FB
    */
    protected String createSortCond(WEB3AdminPMAccTradeStopSortKey[] l_sortKeys)
    {
        int i = 0;
        int l_intLen = l_sortKeys.length;

        //Step 1: Create an empty sortCondList("")
        String l_strSortCondList = new String("");

        //Step 2: Loop process for as many times as elements of parameter.sortKeys
        for (i = 0; i < l_intLen; i++)
        {
            /*If sortCondList is not empty(""), add ","(comma) to sortCondList
                before processing.*/
            if (!l_strSortCondList.equals(""))
            {
                l_strSortCondList += ", ";
            }

            /*Setp 2-1: Add the conditions to created sortCondList based on the values of
                        sortKeys.keyItem for the process*/
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.BRANCH_CODE))
            {
                l_strSortCondList += "branch_id";
            }

            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.ACCOUNT_CODE))
            {
                l_strSortCondList += "substr(account_id,9,6)";
            }

            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.PRODUCT_CODE))
            {
                l_strSortCondList += "product_id";
            }

            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.TERM_FROM))
            {
                l_strSortCondList += "apply_start_date";
            }

            //Step 2-2: Add ascDesc to sortCondList according to the value of sortKeys.ascDesc
            if (l_sortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
            {
                l_strSortCondList += " asc";
            }

            if (l_sortKeys[i].ascDesc.equals(WEB3AscDescDef.DESC))
            {
                l_strSortCondList += " desc";
            }
        }
        return l_strSortCondList;
    }
}
@
