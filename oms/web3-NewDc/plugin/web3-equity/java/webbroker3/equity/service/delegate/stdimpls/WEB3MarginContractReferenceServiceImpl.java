head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������Ɖ�T�[�r�XImpl(WEB3MarginContractReferenceServiceImpl.java)
Author Name      : 2004/9/24  Ḗ@@��(���u) �V�K�쐬         
Revesion History : 2004/12/14 �X��   (SRA)  �c�Č��Ή�  
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginAccountTypeComparator;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginCloseDateComparator;
import webbroker3.equity.WEB3MarginCloseStatus;
import webbroker3.equity.WEB3MarginContractInfo;
import webbroker3.equity.WEB3MarginContractTypeComparator;
import webbroker3.equity.WEB3MarginContractUnitAppraisalProfitOrLossComparator;
import webbroker3.equity.WEB3MarginMarketCodeComparator;
import webbroker3.equity.WEB3MarginOpenDateComparator;
import webbroker3.equity.WEB3MarginProductCodeComparator;
import webbroker3.equity.WEB3MarginRepaymentNumComparator;
import webbroker3.equity.WEB3MarginRepaymentTypeComparator;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.message.WEB3MarginContractReferenceRequest;
import webbroker3.equity.message.WEB3MarginContractReferenceResponse;
import webbroker3.equity.message.WEB3MarginContractReferenceUnit;
import webbroker3.equity.message.WEB3MarginProductCodeNameUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.message.WEB3MarginSortKey;
import webbroker3.equity.service.delegate.WEB3MarginContractReferenceService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeOtherConstDef;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p��������Ɖ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p��������Ɖ�T�[�r�X�����N���X
 * @@author �@@��
 * @@version 1.0
 */
public class WEB3MarginContractReferenceServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginContractReferenceService 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginContractReferenceServiceImpl.class);

    /**
     * (�R���X�g���N�^)�B<BR>
     * @@roseuid 4140066D0040
     */
    public WEB3MarginContractReferenceServiceImpl() 
    {
    }
    
    /**
     * (execute)�B<BR>
     * <BR>
     * �M�p��������Ɖ�T�[�r�X���������{����B<BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p��������Ɖ�T�[�r�X�j�����Ɖ�v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     *   �V�[�P���X�}(�u�i�M�p��������Ɖ�T�[�r�X�j�����Ɖ�v) : (5*)�@@�B<BR>   
     *   (�����R�[�h�`�F�b�N)<BR>
     *   getProduct( )�Ŋ����������擾�ł��Ȃ������ꍇ�́A�u�����R�[�h�G���[�v�̗�O��throw���A�������I������B<BR>   
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00301<BR>
     *   ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4056A0210336
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "EXECUTE";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                                 
        }
        Institution l_institution = null;
        //1.1: validate
        WEB3MarginContractReferenceRequest l_request1 = (WEB3MarginContractReferenceRequest)l_request;
        l_request1.validate();
        
        
        boolean l_blnIsCloseMarginOrderAcceptPossible = true;
        boolean l_blnIsSwapMarginOrderAcceptPossible = true;
        WEB3BaseException l_wbeReturn = null;

        //validate������t�\ - �Ɖ�
        try
        {
            //������t���ԋ敪          -> 01:�����E�M�p (onCall()�Őݒ�ς�)
            //������t�g�����U�N�V����  -> 07:�Ɖ�        (onCall()�Őݒ�ς�)
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            //�Ɖ�s�̏ꍇ�̓G���[�I������B
            throw new WEB3BusinessLayerException(
                l_wbe.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //validate������t�\ - �ԍ�
        try
        {
            //������t���ԋ敪          -> 01:�����E�M�p (onCall()�Őݒ�ς�)
            //������t�g�����U�N�V����  -> 07:�ԍς��Z�b�g
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            l_wbeReturn = l_wbe;
            l_blnIsCloseMarginOrderAcceptPossible = false;
        }

        //validate������t�\ - �����E���n
        try
        {
            //������t���ԋ敪          -> 19:�����E���n���Z�b�g
            //������t�g�����U�N�V����  -> 04:�����E���n���Z�b�g
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.SWAP_MARGIN);
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsSwapMarginOrderAcceptPossible = false;
        }
        
        //�ԍϒ����A�������n�������Ɏ�t�s�̏ꍇ�̓G���[�𓊂���
        if ( !l_blnIsCloseMarginOrderAcceptPossible && !l_blnIsSwapMarginOrderAcceptPossible)
        {
            throw new WEB3BusinessLayerException(
                l_wbeReturn.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        
        //1.2: get sub account
        SubAccount l_subAccount = this.getSubAccount();
        //1.3:(����t���[) �����R�[�h�w�莞(���N�G�X�g�f�[�^.�����R�[�h!=null)�̂݉��L���������{����B
        FinApp l_fin = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_fin.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tm.getPositionManager();
        AccountManager l_accMa = l_fin.getAccountManager();
        WEB3EquityProductManager l_productMa = (WEB3EquityProductManager) l_tm.getProductManager(); 
        Market l_market = null;
        if (l_request1.productCode != null)
        {
            //1.3.1
            try 
            {
                l_institution = l_accMa.getInstitution(l_subAccount.getInstitution().getInstitutionCode());
                l_productMa.getProduct(l_institution,l_request1.productCode);
            } 
            catch (NotFoundException l_ex) 
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
            
            }           
        }
        //1.4
        WEB3MarginContractReferenceResponse l_response = (WEB3MarginContractReferenceResponse) l_request1.createResponse();
        //1.5�����E�s��v���_�E���쐬
        //1.5.1get�����ꗗ(�⏕����, ProductTypeEnum, String, String, String[])
        List l_listContracts = l_positionManager.getContracts(
            (WEB3GentradeSubAccount) l_subAccount,
            ProductTypeEnum.EQUITY,
            null,
            "product_id asc",
            null);
        //1.5.2���L�v���p�e�B�Z�b�g���s���A�߂�l��ԋp���A�������I������B
        if (l_listContracts.size() == 0)
        {
            l_response.productCodeNames = null;
            l_response.marketList = null;
            l_response.pageIndex = WEB3GentradeOtherConstDef.FORMAT_NUM5;
            l_response.totalRecords = WEB3GentradeOtherConstDef.FORMAT_NUM5;
            l_response.totalPages = WEB3GentradeOtherConstDef.FORMAT_NUM5;
            l_response.contractReferenceUnits = null;
            return l_response;
        }
        //1.5.3 product map
        Map l_productMap = new TreeMap();
        //1.5.4 market code map
        Map l_marketMap = new TreeMap();
        //1.5.5 loop to deal with contracts
        int l_intNum = l_listContracts.size();
        WEB3MarginCloseStatus l_status = null;
        WEB3EquityContract l_contractImpl = null;
        for(int i=0;i<l_intNum;i++)
        {
            //1.5.5.1get���Ϗ��(���� : ����)
            long  l_lngContractId = ((EqtypeContractRow) l_listContracts.get(i)).getContractId();      
            try 
            {
                l_contractImpl = (WEB3EquityContract) l_positionManager.getContract(l_lngContractId);
            } catch (NotFoundException l_nfe) 
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            l_status =
                l_positionManager.getMarginCloseStatus(l_contractImpl);      
            //1.5.2
            if (l_status.closedMarginFlag ==false&&
                l_status.closeMarginFlag == false&&
                l_status.closingMarginFlag == false)
            {
                continue;
            }
            //1.5.5.3 get product            
            Product l_product = l_contractImpl.getProduct();
            //1.5.5.4 get standard name
            WEB3EquityProduct l_productImpl = null;
            try 
            {
                l_productImpl = (WEB3EquityProduct) l_productMa.getProduct(l_product.getProductId());
            } catch (NotFoundException l_nfe) 
            {
                log.exiting(STR_METHOD_NAME);
                log.error(STR_METHOD_NAME,l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            EqtypeProductRow l_productRow = (EqtypeProductRow) l_productImpl.getDataSourceObject();
            String l_strName = l_productRow.getStandardName();
            //1.5.5.5 get product code
            String l_strProductCode =l_productImpl.getProductCode();
            //1.5.5.6
            WEB3MarginProductCodeNameUnit l_productCodeName = new WEB3MarginProductCodeNameUnit();
            //1.5.5.7 set property
            l_productCodeName.productCode = l_strProductCode;
            l_productCodeName.productName = l_strName;
            //1.5.5.8
            l_productMap.put(l_strProductCode,l_productCodeName);
            //1.5.9 get market id
            long l_lngMarketId = l_contractImpl.getMarketId();
            //1.5.5.10
            try 
            {
                l_market = l_fin.getFinObjectManager().getMarket(l_lngMarketId);
            } 
            catch (NotFoundException l_ex) 
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
            }
            //1.5.5.11 get market code
            String l_strMarketCode = l_market.getMarketCode();
            //1.5.5.12
            l_marketMap.put(new Long(l_lngMarketId), l_strMarketCode);
            //1.5.6 check product map
            //1.5.6.1
            if (l_productMap.size() == 0)
            {
                l_response.productCodeNames = null;
                l_response.marketList = null;
                l_response.pageIndex = WEB3GentradeOtherConstDef.FORMAT_NUM5;
                l_response.totalRecords = WEB3GentradeOtherConstDef.FORMAT_NUM5;
                l_response.totalPages = WEB3GentradeOtherConstDef.FORMAT_NUM5;
                l_response.contractReferenceUnits = null;
                return l_response;
            }
            //1.5.7
            Collection l_collection =l_productMap.values(); ;
            //1.5.8
            WEB3MarginProductCodeNameUnit[] l_objProducts = (WEB3MarginProductCodeNameUnit[]) l_collection.toArray(new WEB3MarginProductCodeNameUnit[l_collection.size()]);
            //1.5.9
            Collection l_collectionMarket = l_marketMap.values();
            //1.5.10
            String[] l_objMarkets = (String[]) l_collectionMarket.toArray(new String[l_collectionMarket.size()]);
            //1.5.11 set response �v���p�e�B
            l_response.productCodeNames = l_objProducts;
            l_response.marketList = l_objMarkets;
        }
        //1.6 reate��������������(String, String)
        String l_strResult = this.createSearchCondCharacter(l_request1.productCode,l_request1.marketCode);
        //1.7reate���������f�[�^�R���e�i(String, 
        String[] l_strDatas = this.createSearchCondDataContainers(l_request1.productCode,l_request1.marketCode);
        //1.8eate�������ꗗ(�⏕����, String, String, Strin
        String l_prodcutCode = l_request1.productCode;
        boolean l_blnProductCode ;
        if (l_prodcutCode != null)
        {
            l_blnProductCode = true; 
        }
        else
        {
            l_blnProductCode = false;    
        }
        WEB3MarginContractInfo[] l_listCreatreContact = this.createContractInfoList(
            (WEB3GentradeSubAccount) l_subAccount,
            l_request1.settlementState,
            l_strResult,
            l_strDatas,
            l_blnProductCode);
        //1.9 ���������Y���f�[�^�`�F�b�N�j
        //create�������ꗗ( )�̖߂�l��null�̏ꍇ(�����������Y���f�[�^�Ȃ�)�A
        //���L�v���p�e�B�Z�b�g���s���A�߂�l��ԋp���A�������I������B
        if (l_listCreatreContact == null|| l_listCreatreContact.length == 0)
        {
            l_response.pageIndex = WEB3GentradeOtherConstDef.FORMAT_NUM5;
            l_response.totalRecords = WEB3GentradeOtherConstDef.FORMAT_NUM5;
            l_response.totalPages = WEB3GentradeOtherConstDef.FORMAT_NUM5;
            l_response.contractReferenceUnits = null;
            return l_response;                
        }
        //1.10sort�������ꗗ(�M�p����������[], �M�p����\�[�g�L�[[])
        this.sortContractInfoList(l_listCreatreContact,l_request1.sortKeys);
        //1.11create�����Ɖ�׈ꗗfrom�������ꗗ(�M�p����������[])
        WEB3MarginContractReferenceUnit[] l_objUnits = this.createContractReferenceUnitListFromContractInfoList(l_listCreatreContact);
        //1.12create�y�[�W(�M�p��������Ɖ�N�G�X�g, �M�p��������Ɖ��[])
        WEB3MarginContractReferenceUnit[] l_units = this.createPage(l_request1,l_objUnits);
        //1.13 �v���p�e�B�Z�b�g
        int l_intTotalRecord = l_objUnits.length;
        int l_intPageTotalLine = Integer.parseInt(l_request1.pageSize);
        int l_intNeedPage = Integer.parseInt(l_request1.pageIndex);
        int l_intLastPage = l_intTotalRecord/l_intPageTotalLine;
        int l_intPageMod = l_intTotalRecord%l_intPageTotalLine;
        if (l_intPageMod != 0)
        {
            l_intLastPage++;    
        }
        
        l_response.totalPages = new Integer(l_intLastPage).toString();
        l_response.pageIndex = l_request1.pageIndex;
        if (l_intTotalRecord <= l_intPageTotalLine * (l_intNeedPage - 1))
        {
            l_response.pageIndex = new Integer(l_intLastPage).toString();
        }

        l_response.totalRecords = new Integer(l_intTotalRecord).toString();

        l_response.contractReferenceUnits = l_units;
        
        return l_response;
    }
    
    /**
     * (create��������������)�B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�����ƂɁA���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * �P�j�@@�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����.�����R�[�h��null�i�����R�[�h�w��j�̏ꍇ�A<BR>
     * ����ID�w��𕶎���C���X�^���X�ɒǉ�����B<BR>
     * �@@�@@�i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@" and product_id = ?"<BR>
     * <BR>
     * �R�j�@@����.�s��R�[�h��null�i�s��R�[�h�w��j�̏ꍇ�A<BR>
     * �s��ID�w��𕶎���C���X�^���X�ɒǉ�����B<BR>
     * �@@�@@�i�s��R�[�h�ɑΉ�����s��ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@" and market_id = ?"<BR>
     * <BR>
     * �S�j�@@������C���X�^���X��ԋp����B<BR>
     * �i����.�����R�[�h�A����.�s��R�[�h���ǂ����null�̏ꍇ�Anull��ԋp����j<BR>
     * <BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@param l_strMarketCode - �s��R�[�h<BR>
     * @@return String
     * @@roseuid 40E3F3B002B0
     */
    protected String createSearchCondCharacter(String l_strProductCode, String l_strMarketCode) 
    {
        final String METHOD_NAME = "createSearchCondCharacter";
        log.debug(METHOD_NAME);
        String  l_strQuery ;        
        if (l_strProductCode ==null)
        {
            return null;
        }
        else
        {
            l_strQuery = " and product_id = �H";
        }
        if (l_strMarketCode == null)
        {
            return null;
        }
        else
        {
            l_strQuery = l_strQuery + " and market_id = ? " ;
        }
        return l_strQuery;
    }
    
    /**
     * (create���������f�[�^�R���e�i)�B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^����A���������iwhere�ȉ��w��̕�����j<BR>
     * �̃p�����[�^���X�g���쐬����B<BR>
     * <BR>
     * �P�j�@@������z����쐬����B<BR>
     * <BR>
     * �Q�j�@@����.�����R�[�h��null�i�����R�[�h�w��j�̏ꍇ�A����ID��<BR>
     * �@@�@@������z��ɃZ�b�g����B�i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@����ID �� �g���v���_�N�g�}�l�[�W��.get����(�،���ЃI�u�W�F�N�g(*1), <BR>
     * �p�����[�^.�����R�[�h).����ID<BR>
     * <BR>
     * �R�j�@@����.�s��R�[�h��null�i�s��R�[�h�w��j�̏ꍇ�A�s��ID��<BR>
     * �@@�@@������z��ɃZ�b�g����B�i�s��R�[�h�ɑΉ�����s��ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@�s��ID �� �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket<BR>
     * (�،���ЃI�u�W�F�N�g(*1), ����.�s��R�[�h).�s��ID<BR>
     * <BR>
     * (*1)�،���ЃI�u�W�F�N�g�́A�⏕����.getInstitution( )�Ŏ擾���ݒ�<BR>
     * <BR>
     * �S�j�@@������z���ԋp����B<BR>
     * �i����.�����R�[�h�A����.�s��R�[�h���ǂ����null�̏ꍇ�Anull��ԋp����j<BR>
     * <BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@param l_strMarketCode - �s��R�[�h<BR>
     * @@return String[]<BR>
     * @@roseuid 40E3F6000095
     */
    protected String[] createSearchCondDataContainers(String l_strProductCode, String l_strMarketCode) throws WEB3BaseException  
    {
        final String METHOD_NAME = "createSearchCondDataContainers";
        FinApp l_fin = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_fin.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager = (WEB3EquityProductManager) l_tm.getProductManager();
        FinObjectManager l_finObject = l_fin.getFinObjectManager();
        long l_lngProductId = 0;
        long l_lngMarketId = 0;
        
        if (l_strProductCode == null)
        {
            return null; 
        }
        if (l_strMarketCode == null)
        {
            return null;
        }
        SubAccount l_subAccount = this.getSubAccount();
        Institution l_institution = l_subAccount.getInstitution();
        try 
        {
            l_lngProductId = l_productManager.getProduct(l_institution,l_strProductCode).getProductId();
            l_lngMarketId = l_finObject.getMarket(l_institution,l_strMarketCode).getMarketId();
            
        }
        catch (NotFoundException l_ex) 
        {
            log.error(METHOD_NAME,l_ex);
            throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + "." + METHOD_NAME);          
        }
        String[] l_strQury = {" "+ l_lngProductId," "+ l_lngMarketId } ;
        
        return l_strQury;
    }
    
    /**
     * (create�������ꗗ)�B<BR>
     * <BR>
     * �����Ɖ��ʂɕ\�����錚���Ɖ�ׂ̌��ƂȂ錚�����̈ꗗ���쐬����B<BR>
     * �ȉ��̂����ꂩ�̌��Ϗ�Ԃɓ��Ă͂܂錚�����𒊏o����B<BR>
     * (���Ϗ�Ԃ̎w�肪����ꍇ�ɂ́A�w�茈�Ϗ�Ԃ݂̂̌������Ƃ���)<BR>
     * �E���ύ�<BR>
     * �E������<BR>
     * �E���ϒ�<BR>
     * <BR>
     * ���Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�null��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p��������Ɖ�T�[�r�X�jcreate�������ꗗ�v�Q�ƁB<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_strDesCloseMarginStatus - (�w�茈�Ϗ��)<BR>
     * ���L�̂����ꂩ�B<BR>
     * <BR>
     * null�F�w��Ȃ� <BR>
     * 0�F���ύ�<BR>
     * 1�F������<BR>
     * 2�F���ϒ�<BR>
     * <BR>
     * @@param l_strSearchCondCharacter - �������� ������<BR>
     * @@param l_strSearchCondDataContainers - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i�@@
     * @@param l_blnIsProductDesignate - (is�����w��)<BR>
     * �����R�[�h���w�肳��Ă���ꍇ�Atrue�B�ȊO�Afalse�B<BR>
     * @@return WEB3MarginContractInfo[]
     * @@roseuid 40E519BF00E9
     */
    protected WEB3MarginContractInfo[] createContractInfoList(
        WEB3GentradeSubAccount l_subAccount, 
        String l_strDesCloseMarginStatus, 
        String l_strSearchCondCharacter, 
        String[] l_strSearchCondDataContainers, 
        boolean l_blnIsProductDesignate) throws WEB3BaseException
    {
        final String METHOD_NAME ="createContractInfoList";
        FinApp l_fin = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_fin.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tm.getPositionManager();       
        FinObjectManager l_finObject = l_fin.getFinObjectManager();
        Market l_market = null;
        ArrayList l_listContractIfo = null;
        //1.1 get �����ꗗ
        List l_listContracts = l_positionManager.getContracts(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            l_strSearchCondCharacter,           
            l_strSearchCondDataContainers);
        //1.2 �߂�l����
        if(l_listContracts == null)
        {
            return null;
        }
        //1.3
        List l_arrayLists = new ArrayList();
        //1.4�����v�f��loop����
        int l_intNum = l_listContracts.size();
        for(int i=0;i<l_intNum; i++)
        {
            WEB3EquityContract l_contract = null;
            long  l_lngContractId = ((EqtypeContractRow) l_listContracts.get(i)).getContractId();      
            try 
            {
                l_contract = (WEB3EquityContract) l_positionManager.getContract(l_lngContractId);
            } 
            catch (NotFoundException l_nfe) 
            {            
                log.error(METHOD_NAME,l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + 
                    l_nfe.getMessage(),
                    l_nfe);
            }
            //1.4.1
            long l_lngMarketId = l_contract.getMarketId();
            //1.4.2 get market�@@object
            try 
            {
                l_market = l_finObject.getMarket(l_lngMarketId);
            } 
            catch (NotFoundException l_ex) 
            {
                log.error(METHOD_NAME,l_ex);
                throw new WEB3BaseException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + "." + METHOD_NAME);                     
            }
            //1.4.3 get market code
            String l_strMarketCode = l_market.getMarketCode();
            //1.4.4 reset �s��R�[�h
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            //1.4.5 get ���Ϗ��
            WEB3MarginCloseStatus l_closeStatus = l_positionManager.getMarginCloseStatus(l_contract);
            //1.4.6 
            if (l_closeStatus.closedMarginFlag && ! l_closeStatus.closeMarginFlag && ! l_closeStatus.closingMarginFlag)
            {
                //1.4.6.1
                WEB3MarginContractInfo l_contractIfo = l_positionManager.createClosedMarginContractInfo(l_contract);
                //1.4.6.2
                l_arrayLists.add(l_contractIfo);
            }
            //1.4.7 
            else if (! l_closeStatus.closedMarginFlag && l_closeStatus.closeMarginFlag && ! l_closeStatus.closingMarginFlag)
            {
                //1.4.7.1
                WEB3MarginContractInfo l_contractIfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);
                //1.4.7.2 set���ω\�t���Oto�������
                this.setCloseMarginPossibleFlagToContractInfo(l_contractIfo, l_blnIsProductDesignate);
                //1.4.7.3 add object
                l_arrayLists.add(l_contractIfo);
            }
            //1.4.8
            else if (!l_closeStatus.closedMarginFlag&&!l_closeStatus.closeMarginFlag&&l_closeStatus.closingMarginFlag)
            {
                //1.4.8.1
                WEB3MarginContractInfo l_contractIfo = l_positionManager.createClosingMarginContractInfo(l_contract);
                //1.4.8.2
                l_arrayLists.add(l_contractIfo);
            }
            //1.4.9 
            else
            {
                //1.4.9.1
                this.createMultipleContractInfo(l_arrayLists,l_contract,l_closeStatus,l_blnIsProductDesignate);                                
            }
        }
        //1.5���Ϗ�Ԃ̎w�肪����ꍇ�̂݁A
        //(����.�w�茈�Ϗ�� != null)
        //�ȉ��̏��������{����B
        if (l_strDesCloseMarginStatus !=null)
        {
            //1.5.1 get �w�茈�Ϗ�Ԍ������ꗗ
            l_listContractIfo = this.getCloseMarginContractInfoList(l_strDesCloseMarginStatus,l_arrayLists);
            //1.5.2
            if (l_listContractIfo ==null)
            {
                return null;
            }
            else
            {
                int l_intSize = l_listContractIfo.size();
                WEB3MarginContractInfo[] l_contractIfos = (WEB3MarginContractInfo[]) l_listContractIfo.toArray(new WEB3MarginContractInfo[l_intSize]);
                return l_contractIfos;    
            }
        }
        else
        {
            int l_intSize = l_arrayLists.size();
            WEB3MarginContractInfo[] l_contractIfos = (WEB3MarginContractInfo[]) l_arrayLists.toArray(new WEB3MarginContractInfo[l_intSize]);
            return l_contractIfos;               
        }       
}
    

    /**
     * (create�����������)�B<BR>
     * <BR>
     * 1�̌����ŕ����̌��������쐬����ꍇ�̏������s���B <BR>
     * <BR>
     * �����̌��Ϗ�Ԃɂ��ƂÂ��A  <BR>
     * �����|�W�V�����}�l�[�W��.create���ύό������( ) <BR>
     * �����|�W�V�����}�l�[�W��.create�����ό������( ) <BR>
     * �����|�W�V�����}�l�[�W��.create���ϒ��������( ) <BR>
     * ���\�b�h�̂����ꂩ���R�[������B <BR>
     * <BR>
     * �P�j�@@���Ϗ�ԁF���L�̏ꍇ(�����ςƌ��ϒ�)�A�����ςƌ��ϒ���2���ׂ��쐬����B  <BR>
     * �@@����.���Ϗ��.���ύσt���O��false  <BR>
     * �@@����.���Ϗ��.�����σt���O��true   <BR>
     * �@@����.���Ϗ��.���ϒ��t���O��true <BR>
     * <BR>
     * �P�|�P�j�@@�����|�W�V�����}�l�[�W��.create�����ό������(����.����)  <BR>
     * �P�|�Q�j�@@this.set���ω\�t���Oto�������(�P?�P�̖߂�l�̐M�p����������A <BR>
     * �@@�@@�@@�@@�@@����.is�����w��A����.is�ԍϒ�����t�\�A����.is�������n������t�\) <BR>
     * �P�|�R�j�@@����.������񃊃X�g.add(�P?�Q�Ńv���p�e�B�Z�b�g�����M�p����������) <BR>
     * �P�|�S�j�@@�����|�W�V�����}�l�[�W��.create���ϒ��������(����.����)  <BR>
     * �P�|�T�j�@@����.������񃊃X�g.add(�P?�S�̖߂�l�̐M�p����������) <BR>
     * <BR>
     * �Q�j�@@���Ϗ�ԁF���L�̏ꍇ(���ύςƖ�����)�A���ύςƖ����ς�2���ׂ��쐬����B  <BR>
     * �@@����.���Ϗ��.���ύσt���O��true <BR>
     * �@@����.���Ϗ��.�����σt���O��true   <BR>
     * �@@����.���Ϗ��.���ϒ��t���O��false <BR>
     * <BR>
     * �Q�|�P�j�@@�����|�W�V�����}�l�[�W��.create���ύό������(����.����)  <BR>
     * �Q�|�Q�j�@@����.������񃊃X�g.add(�Q?�P�̖߂�l�̐M�p����������) <BR>
     * �Q�|�R�j�@@�����|�W�V�����}�l�[�W��.create�����ό������(����.����)  <BR>
     * �Q�|�S�j�@@this.set���ω\�t���Oto�������(�Q?�R�̖߂�l�̐M�p����������A <BR>
     * �@@�@@�@@�@@�@@����.is�����w��A����.is�ԍϒ�����t�\�A����.is�������n������t�\) <BR>
     * �Q�|�T�j�@@����.������񃊃X�g.add(�Q?�S�Ńv���p�e�B�Z�b�g�����M�p����������) <BR>
     * <BR>
     * �R�j�@@���Ϗ�ԁF���L�̏ꍇ(���ύςƌ��ϒ�)�A���ύςƌ��ϒ���2���ׂ��쐬����B  <BR>
     * �@@����.���Ϗ��.���ύσt���O��true <BR>
     * �@@����.���Ϗ��.�����σt���O��false   <BR>
     * �@@����.���Ϗ��.���ϒ��t���O��true <BR>
     * <BR>
     * �R�|�P�j�@@�����|�W�V�����}�l�[�W��.create���ύό������(����.����)  <BR>
     * �R�|�Q�j�@@����.������񃊃X�g.add(�R?�P�̖߂�l�̐M�p����������) <BR>
     * �R�|�R�j�@@�����|�W�V�����}�l�[�W��.create���ϒ��������(����.����)  <BR>
     * �R�|�S�j�@@����.������񃊃X�g.add(�R?�R�̖߂�l�̐M�p����������) <BR>
     * <BR>
     * �S�j�@@���Ϗ�ԁF���L�̏ꍇ(���ύςƖ����ςƌ��ϒ�)�A���ύςƖ����ςƌ��ϒ��� <BR>
     * �@@�@@�@@3���ׂ��쐬����B  <BR>
     * �@@����.���Ϗ��.���ύσt���O��true <BR>
     * �@@����.���Ϗ��.�����σt���O��true   <BR>
     * �@@����.���Ϗ��.���ϒ��t���O��true <BR>
     * <BR>
     * �S�|�P�j�@@�����|�W�V�����}�l�[�W��.create���ύό������(����.����)  <BR>
     * �S�|�Q�j�@@����.������񃊃X�g.add(�S?�P�̖߂�l�̐M�p����������) <BR>
     * �S�|�R�j�@@�����|�W�V�����}�l�[�W��.create�����ό������(����.����)  <BR>
     * �S�|�S�j�@@this.set���ω\�t���Oto�������(�S?�R�̖߂�l�̐M�p����������A <BR>
     * �@@�@@�@@�@@�@@����.is�����w��A����.is�ԍϒ�����t�\�A����.is�������n������t�\) <BR>
     * �S�|�T�j�@@����.������񃊃X�g.add(�S?�S�Ńv���p�e�B�Z�b�g�����M�p����������) <BR>
     * �S�|�U�j�@@�����|�W�V�����}�l�[�W��.create���ϒ��������(����.����)  <BR>
     * �S�|�V�j�@@����.������񃊃X�g.add(�S?�U�̖߂�l�̐M�p����������)<BR>
     * <BR>
     * @@param l_lisContractInfoList - (������񃊃X�g)<BR>
     * �쐬�������������i�[���郊�X�g
     * @@param l_contract - (����)<BR>
     * ���������쐬����Ώۂ̌���<BR>
     * @@param l_closeMarginStatus - (���Ϗ��)<BR>
     * @@param l_blnIsProductDesignate<BR>
     * �M�p������Ϗ��<BR>
     * @@roseuid 40ED1E020103
     */
    protected void createMultipleContractInfo(
        List l_lisContractInfoList, 
        WEB3EquityContract l_contract, 
        WEB3MarginCloseStatus l_closeStatus,
        boolean l_blnIsProductDesignate) throws WEB3BaseException
    {
        
        final String METHOD_NAME ="createContractInfoList";
        log.debug(METHOD_NAME);
        if (l_lisContractInfoList == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + METHOD_NAME);                                 
        }
        if (l_closeStatus == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + METHOD_NAME);                                 
        }
        FinApp l_fin = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_fin.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tm.getPositionManager();       

        WEB3MarginContractInfo l_contractIfo = null;

        //1) false true true �̏ꍇ
        if ( ! l_closeStatus.closedMarginFlag && l_closeStatus.closeMarginFlag && l_closeStatus.closingMarginFlag)
        {
            //�P�|�P�j�@@�����|�W�V�����}�l�[�W��.create�����ό������(����.����) <BR>
            l_contractIfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);
            //�P�|�Q�j�@@this.set���ω\�t���Oto�������(�P�|�P�̖߂�l�̐M�p����������A<BR>
            //����.is�����s��w��)<BR>
            this.setCloseMarginPossibleFlagToContractInfo(
                l_contractIfo, l_blnIsProductDesignate);
            //�P�|�R�j�@@����.������񃊃X�g.add(�P�|�Q�Ńv���p�e�B�Z�b�g�����M�p����������)<BR>
            l_lisContractInfoList.add(l_contractIfo);
            //�P�|�S�j�@@�����|�W�V�����}�l�[�W��.create���ϒ��������(����.����) <BR>
            l_contractIfo = l_positionManager.createClosingMarginContractInfo(l_contract);
            //�P�|�T�j�@@����.������񃊃X�g.add(�P�|�S�̖߂�l�̐M�p����������)<BR>
            l_lisContractInfoList.add(l_contractIfo);
                   
        }
         //2)true true false
        else if (l_closeStatus.closedMarginFlag && l_closeStatus.closeMarginFlag && ! l_closeStatus.closingMarginFlag)
        {
            //* �Q�|�P�j�@@�����|�W�V�����}�l�[�W��.create���ύό������(����.����) <BR>
            l_contractIfo = l_positionManager.createClosedMarginContractInfo(l_contract);
            //* �Q�|�Q�j�@@����.������񃊃X�g.add(�Q�|�P�̖߂�l�̐M�p����������)<BR>
            l_lisContractInfoList.add(l_contractIfo);
            //* �Q�|�R�j�@@�����|�W�V�����}�l�[�W��.create�����ό������(����.����) <BR>
            l_contractIfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);
            //* �Q�|�S�j�@@this.set���ω\�t���Oto�������(�Q�|�R�̖߂�l�̐M�p����������A<BR>
            //* �@@�@@�@@�@@�@@����.is�����s��w��)<BR>
            this.setCloseMarginPossibleFlagToContractInfo(
                l_contractIfo, l_blnIsProductDesignate);
            //* �Q�|�T�j�@@����.������񃊃X�g.add(�Q�|�S�Ńv���p�e�B�Z�b�g�����M�p����������)<BR>
            l_lisContractInfoList.add(l_contractIfo);

        }
        //3 true false true
        else if ( l_closeStatus.closedMarginFlag && ! l_closeStatus.closeMarginFlag && l_closeStatus.closingMarginFlag)
        {
            //* �R�|�P�j�@@�����|�W�V�����}�l�[�W��.create���ύό������(����.����) <BR>
            l_contractIfo = l_positionManager.createClosedMarginContractInfo(l_contract);
            //* �R�|�Q�j�@@����.������񃊃X�g.add(�R�|�P�̖߂�l�̐M�p����������)<BR>
            l_lisContractInfoList.add(l_contractIfo);
            //* �R�|�R�j�@@�����|�W�V�����}�l�[�W��.create���ϒ��������(����.����) <BR>
            l_contractIfo = l_positionManager.createClosingMarginContractInfo(l_contract);
            //* �R�|�S�j�@@����.������񃊃X�g.add(�R�|�R�̖߂�l�̐M�p����������)<BR>
            l_lisContractInfoList.add(l_contractIfo);            
        }
        //4 true true true 
        else if (l_closeStatus.closedMarginFlag && l_closeStatus.closeMarginFlag && l_closeStatus.closingMarginFlag)
        {
            
            //* �S�|�P�j�@@�����|�W�V�����}�l�[�W��.create���ύό������(����.����) <BR>
            l_contractIfo = l_positionManager.createClosedMarginContractInfo(l_contract);
            //* �S�|�Q�j�@@����.������񃊃X�g.add(�S�|�P�̖߂�l�̐M�p����������)<BR>
            l_lisContractInfoList.add(l_contractIfo);
            //* �S�|�R�j�@@�����|�W�V�����}�l�[�W��.create�����ό������(����.����) <BR>
            l_contractIfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);
            //* �S�|�S�j�@@this.set���ω\�t���Oto�������(�S�|�R�̖߂�l�̐M�p����������A<BR>
            //* �@@�@@�@@�@@�@@����.is�����s��w��)<BR>
            this.setCloseMarginPossibleFlagToContractInfo(
                l_contractIfo, l_blnIsProductDesignate);
            //* �S�|�T�j�@@����.������񃊃X�g.add<BR>
            //*         (�S�|�S�Ńv���p�e�B�Z�b�g�����M�p����������)<BR>
            l_lisContractInfoList.add(l_contractIfo);
            //* �S�|�U�j�@@�����|�W�V�����}�l�[�W��.create���ϒ��������(����.����) <BR>
            l_contractIfo = l_positionManager.createClosingMarginContractInfo(l_contract);
            //* �S�|�V�j�@@����.������񃊃X�g.add(�S�|�U�̖߂�l�̐M�p����������)<BR>            
            l_lisContractInfoList.add(l_contractIfo);
        }
    }
    
    /**
     * (create�����Ɖ�׈ꗗfrom�������ꗗ)�B<BR>
     * �������̈ꗗ����M�p��������Ɖ�ׂ̈ꗗ���쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����.�������ꗗ�v�f������Loop�����B<BR>
     * �@@�Q�|�P�j�@@�M�p��������Ɖ�׃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�M�p����ٍσI�u�W�F�N�g�𐶐����A���L�v���p�e�B�Z�b�g���s���B<BR>
     * �@@�@@�@@�@@�@@�@@�M�p����ٍ�.�ٍϋ敪 = �������[�C���f�b�N�X].�ٍϋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�M�p����ٍ�.�ٍϊ����l = �������[�C���f�b�N�X].�ٍϊ����l<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�������̊e�v���p�e�B�ݒ�l���A���������M�p��������Ɖ�ׂ�<BR>
     * �e�v���p�e�B�ɐݒ肷��B<BR>
     * �@@�����Ɖ��.ID = �������[�C���f�b�N�X].ID<BR>
     * �@@�����Ɖ��.�����R�[�h = �������[�C���f�b�N�X].�����R�[�h<BR>
     * �@@�����Ɖ��.������ = �������[�C���f�b�N�X].������<BR>
     * �@@�����Ɖ��.�s��R�[�h = �������[�C���f�b�N�X].�s��R�[�h<BR>
     * �@@�����Ɖ��.�����敪 = �������[�C���f�b�N�X].�����敪<BR>
     * �@@�����Ɖ��.���敪 = �������[�C���f�b�N�X].���敪<BR>
     * �@@�����Ɖ��.�ٍ� =�@@�Q�|�Q�j�ɂăv���p�e�B�Z�b�g�����M�p����ٍ� <BR>
     * �@@�����Ɖ��.���� = �������[�C���f�b�N�X].����<BR>
     * �@@�����Ɖ��.���P�� = �������[�C���f�b�N�X].���P��<BR>
     * �@@�����Ɖ��.������ = �������[�C���f�b�N�X].������<BR>
     * �@@�����Ɖ��.���� = �������[�C���f�b�N�X].����<BR>
     * �@@�����Ɖ��.����� = �������[�C���f�b�N�X].�����<BR>
     * �@@�����Ɖ��.���萔�� = �������[�C���f�b�N�X].���萔��<BR>
     * �@@�����Ɖ��.������ = �������[�C���f�b�N�X].������<BR>
     *   �����Ɖ��.�t���� = �������[�C���f�b�N�X].�t����<BR>
     * �@@�����Ɖ��.�݊��� = �������[�C���f�b�N�X].�݊���<BR>
     * �@@�����Ɖ��.������ = �������[�C���f�b�N�X].������<BR>
     * �@@�����Ɖ��.�Ǘ��� = �������[�C���f�b�N�X].�Ǘ���<BR>
     * �@@�����Ɖ��.���̑� = �������[�C���f�b�N�X].���̑�<BR>
     * �@@�����Ɖ��.�]�����v = �������[�C���f�b�N�X].�]�����v<BR>
     * �@@�����Ɖ��.���Ϗ�ԋ敪 = �������[�C���f�b�N�X].���Ϗ�ԋ敪<BR>
     * �@@�����Ɖ��.�ԍω\�t���O = �������[�C���f�b�N�X].�ԍω\�t���O<BR>
     * �@@�����Ɖ��.�������n�\�t���O = <BR>
     *              �������[�C���f�b�N�X].�������n�\�t���O<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�Q�|�R�j�Ńv���p�e�B���Z�b�g�����M�p��������Ɖ��<BR>
     *                �I�u�W�F�N�g��<BR>
     * ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@ArrayList.toArray( )�ɂ��M�p��������Ɖ�ׂ̔z���ԋp����B<BR>
     * <BR>
     * @@param l_contractInfoList - (�������ꗗ)<BR>
     * �M�p����������̈ꗗ<BR>
     * @@return WEB3MarginContractReferenceUnit[]
     * @@roseuid 40E4F5170260
     */
    protected WEB3MarginContractReferenceUnit[] createContractReferenceUnitListFromContractInfoList(
        WEB3MarginContractInfo[] l_contractInfoList) 
    {
        final String STR_METHOD_NAME = "createContractReferenceUnitListFromContractInfoList";
        if (l_contractInfoList == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                                 
        }
        //1�@@ArrayList�𐶐�����
        List l_lists = new ArrayList();
        //2�����v�f�ꗗloop
        int l_intNum = 0;
        if ( l_contractInfoList != null)
        {
            l_intNum = l_contractInfoList.length;
        }
        for (int i=0; i<l_intNum; i++)
        {
            //�M�p��������Ɖ�׃I�u�W�F�N�g�𐶐�����
            WEB3MarginContractReferenceUnit  l_referenceUnit = new WEB3MarginContractReferenceUnit();
            //�M�p����ٍσI�u�W�F�N�g�𐶐���
            WEB3MarginRepaymentUnit l_payUnit = new WEB3MarginRepaymentUnit();
            //set �M�p����ٍς��I�u�W�F�N�g
            l_payUnit.repaymentDiv = l_contractInfoList[i].repaymentType;
            l_payUnit.repaymentTimeLimit = l_contractInfoList[i].repaymentNum;
            //set �����Ɖ�׃v���p�e�B
            //* �@@�����Ɖ��.ID = �������[�C���f�b�N�X].ID
            l_referenceUnit.id = l_contractInfoList[i].id;
            //* �@@�����Ɖ��.�����R�[�h = �������[�C���f�b�N�X].�����R�[�h
            l_referenceUnit.productCode = l_contractInfoList[i].productCode;
            //* �@@�����Ɖ��.������ = �������[�C���f�b�N�X].������
            l_referenceUnit.productName = l_contractInfoList[i].standardName;
            //* �@@�����Ɖ��.�s��R�[�h = �������[�C���f�b�N�X].�s��R�[�h
            l_referenceUnit.marketCode = l_contractInfoList[i].marketCode;
            //* �@@�����Ɖ��.�����敪 = �������[�C���f�b�N�X].�����敪
            l_referenceUnit.taxType = l_contractInfoList[i].accountType;
            //* �@@�����Ɖ��.���敪 = �������[�C���f�b�N�X].���敪
            l_referenceUnit.contractType = l_contractInfoList[i].contractType;
            //* �@@�����Ɖ��.�ٍ� =�@@�Q�|�Q�j�ɂăv���p�e�B�Z�b�g�����M�p����ٍ�
            l_referenceUnit.repayment = l_payUnit;
            //* �@@�����Ɖ��.���� = �������[�C���f�b�N�X].����
            l_referenceUnit.openDate = l_contractInfoList[i].openDate;
            //* �@@�����Ɖ��.���P�� = �������[�C���f�b�N�X].���P��
            l_referenceUnit.contractPrice = l_contractInfoList[i].contractPrice;
            //* �@@�����Ɖ��.������ = �������[�C���f�b�N�X].������>
            l_referenceUnit.contractOrderQuantity = l_contractInfoList[i].quantity;
            //* �@@�����Ɖ��.���� = �������[�C���f�b�N�X].����
            l_referenceUnit.closeDate = l_contractInfoList[i].closeDate;
            //* �@@�����Ɖ��.����� = �������[�C���f�b�N�X].�����
            l_referenceUnit.contractExecPrice = l_contractInfoList[i].contractExecPrice;
            //* �@@�����Ɖ��.���萔�� = �������[�C���f�b�N�X].���萔��
            l_referenceUnit.contractCommission = l_contractInfoList[i].setupFee;
            //* �@@�����Ɖ��.������ = �������[�C���f�b�N�X].������
            l_referenceUnit.interestFee = l_contractInfoList[i].interestFee;
            //*   �����Ɖ��.�t���� = �������[�C���f�b�N�X].�t����
            l_referenceUnit.payInterestFee = l_contractInfoList[i].payInterestFee;
            //* �@@�����Ɖ��.�݊��� = �������[�C���f�b�N�X].�݊���
            l_referenceUnit.loanEquityFee = l_contractInfoList[i].loanEquityFee;
            //* �@@�����Ɖ��.������ = �������[�C���f�b�N�X].������
            l_referenceUnit.setupFee = l_contractInfoList[i].transferFee;
            //* �@@�����Ɖ��.�Ǘ��� = �������[�C���f�b�N�X].�Ǘ���
            l_referenceUnit.managementFee = l_contractInfoList[i].managementFee;
            //* �@@�����Ɖ��.���̑� = �������[�C���f�b�N�X].���̑�
            l_referenceUnit.otherwise = l_contractInfoList[i].other;
            //* �@@�����Ɖ��.�]�����v = �������[�C���f�b�N�X].�]�����v<BR>
            l_referenceUnit.appraisalProfitLoss = l_contractInfoList[i].takeExpensesOffEvaluationIncome;
            //* �@@�����Ɖ��.���Ϗ�ԋ敪 = �������[�C���f�b�N�X].���Ϗ�ԋ敪
            l_referenceUnit.settlementState = l_contractInfoList[i].closingStatusType;
            //* �@@�����Ɖ��.�ԍω\�t���O = �������[�C���f�b�N�X].�ԍω\�t���O
            l_referenceUnit.closeMarginFlag = l_contractInfoList[i].closingPossibleFlag;
            //* �@@�����Ɖ��.�������n�\�t���O = <BR>
            //*   �������[�C���f�b�N�X].�������n�\�t���O<BR>
            l_referenceUnit.swapFlag = l_contractInfoList[i].swapPossibleFlag;
            // add object
            l_lists.add(l_referenceUnit);       
        }
        //3
        WEB3MarginContractReferenceUnit[] l_referenceUnit1 = (WEB3MarginContractReferenceUnit[]) l_lists.toArray(new WEB3MarginContractReferenceUnit[l_intNum]);
        return  l_referenceUnit1;
    }
    
    /**
     * (create�y�[�W)<BR>
     * <BR>
     * �v���y�[�W�ԍ��ɊY������M�p��������Ɖ�ׂ̔z����쐬����B<BR>
     * <BR>
     * �P�j�@@�y�[�W���\���s���A�v���y�[�W�ԍ��̎擾<BR>
     * �y�[�W���\���s�� = ����.���N�G�X�g�f�[�^.�y�[�W���\���s��<BR>
     * �v���y�[�W�ԍ� = ����.���N�G�X�g�f�[�^.�v���y�[�W�ԍ�<BR>
     * <BR>
     * �Q�j�v���y�[�W�J�n�ʒu������<BR>
     * fromIndex �� �y�[�W���\���s�� �~ (�v���y�[�W�ԍ� - 1)<BR>
     * <BR>
     * ���A���A�����R�[�h�����v���y�[�W�ԍ��ɖ����Ȃ��ꍇ<BR>
     * �@@(�p�����[�^.�M�p��������Ɖ�ׂ̗v�f�� <= fromIndex)�A<BR>
     * �@@fromIndex �� �p�����[�^.�M�p��������Ɖ�ׂ̗v�f�� - �y�[�W���\���s��<BR>
     * �@@�Ƃ���B<BR>
     * <BR>
     * �R�j�v���y�[�W�I���ʒu������<BR>
     * toIndex �� (�y�[�W���\���s�� �~ �v���y�[�W�ԍ�) - 1<BR>
     * <BR>
     * ���A���A�����R�[�h�����v���y�[�W�ԍ��̍ő�l�ɖ����Ȃ��ꍇ<BR>
     * �@@(toIndex > (�p�����[�^.�M�p��������Ɖ�ׂ̗v�f�� - 1))�A<BR>
     * �@@toIndex    ���@@�p�����[�^.�M�p��������Ɖ�ׂ̗v�f�� - 1<BR>
     * �@@�Ƃ���B<BR>
     * <BR>
     * �S�jArrayList���쐬<BR>
     * <BR>
     * �T�jfromIndex�`toIndex�̊ԁA�ȉ��̏�����Loop����<BR>
     * �@@�쐬����ArrayList�ɃC���f�b�N�X�ԍ��̐M�p��������Ɖ�׃I�u�W�F�N�g��ǉ�<BR>
     * <BR>
     * �U�j�@@ArrayList.toArray�ŊY���y�[�W�ԍ��̌����Ɖ�ׂ̈ꗗ��ԋp����<BR>
     * <BR>
     * ����L�v�Z�ɂ����āA-1���s���Ă���̂͂T�j�ɂČ����Ɖ�׈ꗗ�̗v�f����<BR>
     * Loop�������s�����ꍇ�ɁA�C���f�b�N�X0����J�n����邽�߁B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p��������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@param l_contractReferenceUnitList - (�����Ɖ�׈ꗗ)<BR>
     * �M�p��������Ɖ�ׂ̔z��
     * @@return WEB3MarginContractReferenceUnit[]
     * @@roseuid 40E3F7370173
     */
    protected WEB3MarginContractReferenceUnit[] createPage(
        WEB3MarginContractReferenceRequest l_request, 
        WEB3MarginContractReferenceUnit[] l_contractReferenceUnitList) 
    {
        final String STR_METHOD_NAME = "create page";
        if (l_request == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                                 
        }
        if (l_contractReferenceUnitList == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                                 
        }
        //1�@@�y�[�W���\���s���A�v���y�[�W�ԍ��̎擾
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        //2 �v���y�[�W�J�n�ʒu������
        int l_intNum = l_contractReferenceUnitList.length;
        int l_fromIndex = l_intPageSize*(l_intPageIndex - 1);
        if (l_fromIndex >= l_intNum)
        {
            l_fromIndex = l_intNum - l_intPageSize;
        }
        //3 �v���y�[�W�I���ʒu������
        int l_intToIndex = l_intPageSize*l_intPageIndex - 1;
        if (l_intToIndex > (l_intNum -1))
        {
            l_intToIndex = l_intNum - 1;
        }
        //4ArrayList���쐬
        List l_lists = new ArrayList();
        //5
        for (int i = l_fromIndex; i<= l_intToIndex; i++)
        {
            l_lists.add(l_contractReferenceUnitList[i]);
        }
        int l_intNum1 = l_lists.size();
        //6
        WEB3MarginContractReferenceUnit[] l_conRefUnit = (WEB3MarginContractReferenceUnit[]) l_lists.toArray(new WEB3MarginContractReferenceUnit[l_intNum1]);
        return l_conRefUnit;
    }
    
    /**
     * (get�w�茈�Ϗ�Ԍ������ꗗ)�B<BR>
     * <BR>
     * �w�茈�Ϗ�Ԃ̌����݂̂̌������̈ꗗ���擾����B <BR>
     * <BR>
     * �P�j�@@�������ꗗ���Ƃ�Loop���� <BR>
     * <BR>
     * �@@�P�|�P�j�@@�������̌��Ϗ�ԋ敪���擾����B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@����.�w�茈�Ϗ�ԁ@@!=�@@�P�|�P�j�̌��Ϗ�ԋ敪�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�Y���̌��������������ꗗ����폜����B<BR>
     * <BR>
     * �Q�j�@@�w�茈�Ϗ�Ԃ݂̂̌������ꗗ��ԋp����B<BR>
     * <BR>
     * ���P�j�̌��ʁA�������ꗗ�̃T�C�Y��0�ɂȂ����ꍇ�ɂ́Anull��ԋp����B<BR>
     * @@param l_strCloseStatus - (�w�茈�Ϗ��)<BR>
     * ���L�̂����ꂩ�B <BR>
     * <BR>
     * 0�F���ύ� <BR>
     * 1�F������ <BR>
     * 2�F���ϒ� <BR>
     * @@param l_lisContractInfoList - (������񃊃X�g)<BR>
     * �������̈ꗗ���i�[���ꂽ���X�g
     * @@return ArrayList
     * @@roseuid 40ED1E020132
     */
    protected ArrayList getCloseMarginContractInfoList(String l_strCloseStatus, List l_lisContractInfoList) 
    {
        final String STR_METHOD_NAME = "getCloseMarginContractInfoList";
        if (l_lisContractInfoList == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                                 
        }
        int l_intNum = l_lisContractInfoList.size();
        if (l_intNum ==0)
        {
            return null;
        }
        for (int i=0;i < l_intNum; i++)
        {
            WEB3MarginContractInfo l_infoList = (WEB3MarginContractInfo) l_lisContractInfoList.get(i);
            //1-1 get ���Ϗ��
            String l_strStatus = l_infoList.closingStatusType;
            //1-2
            if (!l_strCloseStatus.equals(l_strStatus))
            {
                l_lisContractInfoList.remove(i);        
            }                
        }
        return (ArrayList) l_lisContractInfoList;
    }
    
    /**
     * (set���ω\�t���Oto�������)�B<BR>
     * <BR>
     * �w��̌������̕ԍω\�t���O�A����ь������n�\�t���O��ݒ���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p��������Ɖ�T�[�r�X�jset���ω\�t���Oto�������v�Q�ƁB<BR>
     * <BR>
     * @@param l_contractInfo - (�������)<BR>
     * �ԍω\�t���O�A�������n�\�t���O���Z�b�g����Ώۂ̌������
     * @@param l_blnIsProductDesignate - (is�����w��)<BR>
     * �����R�[�h���w�肳��Ă���ꍇ�Atrue�B�ȊO�Afalse�B<BR>
     * @@roseuid 40ED28FE03D1
     */
    protected void setCloseMarginPossibleFlagToContractInfo(
        WEB3MarginContractInfo l_contractInfo,
        boolean l_blnIsProductDesignate) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "setCloseMarginPossibleFlagToContractInfo";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeOrderValidator l_orderValidator = (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();
        WEB3GentradeFinObjectManager l_finObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();


        //�evalidate�̌��ʊi�[�p�ϐ�
        boolean l_blnIsValidTradingSubAccount  = true;     //isValid����\�ڋq
        boolean l_blnIsClsMgnOdrAptPossible    = true;     //validate������t�\�i�ԍρj
        boolean l_blnIsSwpMgnOdrAptPossible    = true;     //validate������t�\�i�����E���n�j
        boolean l_blnIsValidMarketCode         = true;     //isValid�s��R�[�h
        boolean l_blnIsValidProductCode        = true;     //isValid�����R�[�h
        boolean l_blnIsNotInsider              = true;     //isNot�C���T�C�_�[
        boolean l_blnIsNotStpdPdctForCls       = true;     //isNot�ڋq���������~�i�ԍρj
        boolean l_blnIsNotStpdPdctForSwp       = true;     //isNot�ڋq���������~�i�����E���n�j
        boolean l_blnIsValidTrddPdctForCls     = true;     //isValid��������i�M�p�^�ԍρj
        boolean l_blnIsValidTrddPdctForSwp     = true;     //isValid��������i�M�p�^�����E���n�j
        boolean l_blnIsValidHandlingMarket     = true;     //isValid�戵�\�s��i�M�p�j

        String l_strInstitutionCode             = null;     //�⏕�����ɕR�Â��،���ЃR�[�h
        WEB3GentradeBranch  l_branch            = null;     //�⏕�����ɕR�Â����X
        WEB3GentradeMarket  l_market            = null;     //validate�s��R�[�h���̎s��
        WEB3EquityProduct   l_product           = null;     //validate�������̖���
        WEB3EquityTradedProduct l_tradeProduct  = null;     //validate����������̎������
        
        
        //�M�p���������񂩂猚�敪�A�ԍώ��̒�����ʁA�����E���n���̒�����ʂ��擾
        boolean l_blnIsShortContract
            = (l_contractInfo.contractType.equals(ContractTypeEnum.SHORT));
        OrderTypeEnum l_OrderTypeEnumForCls
            = l_blnIsShortContract ? OrderTypeEnum.CLOSE_MARGIN_SHORT : OrderTypeEnum.CLOSE_MARGIN_LONG;
        OrderTypeEnum l_OrderTypeEnumForSwp
            = l_blnIsShortContract ? OrderTypeEnum.SWAP_MARGIN_LONG : OrderTypeEnum.SWAP_MARGIN_SHORT;
        
        
        //get�⏕�����i����сA�⏕��������،���ЃR�[�h�A���X���擾�j
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
        l_strInstitutionCode                = l_subAccount.getInstitution().getInstitutionCode();
        l_branch                            = l_subAccount.getWeb3GenBranch();        


        //validate����\�ڋq
        OrderValidationResult l_orderValidationResult = l_orderValidator.validateSubAccountForTrading(l_subAccount);
        l_blnIsValidTradingSubAccount = l_orderValidationResult.getProcessingResult().isSuccessfulResult();
        
        
        //reset�s��R�[�h
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_contractInfo.marketCode);
        
        
        //�ԍϒ�����t�\�`�F�b�N
        try
        {
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsClsMgnOdrAptPossible = false;
        }
        
        
        //�������n������t�\�`�F�b�N
        try
        {
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.SWAP_MARGIN);
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsSwpMgnOdrAptPossible = false;
        }
        
        
        //validate�s��R�[�h
        try
        {
            l_market = (WEB3GentradeMarket) l_orderManager.validateMarket(
                l_contractInfo.marketCode, l_strInstitutionCode);
            l_blnIsValidMarketCode = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsValidMarketCode = false;
            try
            {
                //(*)validate�s��R�[�h����O��throw�����ꍇ�ɂ́A
                //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket(�s��ID)�ɂĎ擾����B
                log.debug("validate�s��R�[�h�����s�����̂Ŋg�����Z�I�u�W�F�N�g�}�l�[�W������ȉ������Ŏs����擾");
                log.debug("(�s��R�[�h, �،���ЃR�[�h)=(["+l_contractInfo.marketCode+"],["+l_strInstitutionCode+"])");
                l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(
                    l_contractInfo.marketCode, l_strInstitutionCode);
            }
            catch (NotFoundException l_nfe)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(), l_nfe);
            }
        }
        
        
        //validate�����R�[�h
        try
        {
            l_product = (WEB3EquityProduct) l_orderManager.validateProductCode(
                l_contractInfo.productCode, l_strInstitutionCode, l_contractInfo.repaymentType);
            l_blnIsValidProductCode = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsValidProductCode = false;
        }
        
        
        //validate�C���T�C�_�[
        try
        {
            l_orderManager.validateInsider(l_subAccount, l_product);
            l_blnIsNotInsider = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsNotInsider = false;
        } 
        
        
        //validate�ڋq�����ʎ����~�i�ԍρj
        try
        {
            l_orderManager.validateAccountProductOrderStop(
                l_subAccount, l_product.getProductId(), l_OrderTypeEnumForCls);
            l_blnIsNotStpdPdctForCls = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsNotStpdPdctForCls = false;
        }
        
        
        //validate�ڋq�����ʎ����~�i�����E���n�j
        try
        {
            l_orderManager.validateAccountProductOrderStop(
                l_subAccount, l_product.getProductId(), l_OrderTypeEnumForSwp);
            l_blnIsNotStpdPdctForSwp = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsNotStpdPdctForSwp = false;
        }
        
        
        //validate��������i�ԍρj
        try
        {
            l_tradeProduct = (WEB3EquityTradedProduct) l_orderManager.validateTradedProductForMarginTrading(
                l_subAccount, l_product, l_market, l_branch,
                l_contractInfo.repaymentType, OrderCategEnum.CLOSE_MARGIN, l_blnIsShortContract);
            l_blnIsValidTrddPdctForCls = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsValidTrddPdctForCls = false;
        }
        
        
        //validate��������i�����E���n�j
        try
        {
            l_tradeProduct = (WEB3EquityTradedProduct) l_orderManager.validateTradedProductForMarginTrading(
                l_subAccount, l_product, l_market, l_branch,
                l_contractInfo.repaymentType, OrderCategEnum.SWAP_MARGIN, l_blnIsShortContract);
            l_blnIsValidTrddPdctForSwp = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsValidTrddPdctForSwp = false;
        }
        
        
        //validate�戵�\�s��
        if (l_blnIsValidTrddPdctForCls || l_blnIsValidTrddPdctForSwp)
        {
            try
            {
                l_orderManager.validateHandlingMarket(
                    l_branch, l_tradeProduct, l_contractInfo.marketCode,
                    l_contractInfo.repaymentType, Double.parseDouble(l_contractInfo.repaymentNum));
                l_blnIsValidHandlingMarket = true;
            }
            catch (WEB3BaseException l_wbe)
            {
                l_blnIsValidHandlingMarket = false;
            }
            catch (NumberFormatException l_nfe)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(), l_nfe);
            }
        }
        else
        {
            //validate��������ɂ����ĕԍώ��A�����E���n���̗�����false�������ꍇ
            //validate�戵�\�s����R�[������K�v�Ȃ��B
        }
        
        //�f�o�b�O�p���O�o��
        log.debug("validate����\�ڋq                -> [" + String.valueOf(l_blnIsValidTradingSubAccount) + "]");
        log.debug("validate�s��R�[�h                  -> [" + String.valueOf(l_blnIsValidMarketCode)        + "]");
        log.debug("validate�����R�[�h                  -> [" + String.valueOf(l_blnIsValidProductCode)       + "]");
        log.debug("validate�C���T�C�_�[                -> [" + String.valueOf(l_blnIsNotInsider)             + "]");
        log.debug("validate�ڋq���������~/�ԍ�       -> [" + String.valueOf(l_blnIsNotStpdPdctForCls)      + "]");
        log.debug("validate�ڋq���������~/�����E���n -> [" + String.valueOf(l_blnIsNotStpdPdctForSwp)      + "]");
        log.debug("validate��������i�M�p�j/�ԍ�       -> [" + String.valueOf(l_blnIsValidTrddPdctForCls)    + "]");
        log.debug("validate��������i�M�p�j/�����E���n -> [" + String.valueOf(l_blnIsValidTrddPdctForSwp)    + "]");
        log.debug("validate�戵�\�s��                -> [" + String.valueOf(l_blnIsValidHandlingMarket)    + "]");
        
        //�ԍω\�t���O�̐ݒ�
        l_contractInfo.closingPossibleFlag =
            l_blnIsValidTradingSubAccount &&
            l_blnIsClsMgnOdrAptPossible &&
            l_blnIsValidMarketCode &&
            l_blnIsValidProductCode &&
            l_blnIsNotInsider &&
            l_blnIsNotStpdPdctForCls &&
            l_blnIsValidTrddPdctForCls &&
            l_blnIsValidHandlingMarket;
        
        //�����E���n�\�t���O�̐ݒ�
        l_contractInfo.closingPossibleFlag =
            l_blnIsValidTradingSubAccount &&
            l_blnIsSwpMgnOdrAptPossible &&
            l_blnIsValidProductCode &&
            l_blnIsNotInsider &&
            l_blnIsNotStpdPdctForSwp &&
            l_blnIsValidTrddPdctForSwp &&
            l_blnIsValidHandlingMarket;
            
        log.debug("�ԍω\�t���O          -> [" + String.valueOf(l_contractInfo.closingPossibleFlag) + "]");
        log.debug("�����E���n�\�t���O    -> [" + String.valueOf(l_contractInfo.closingPossibleFlag) + "]");

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (sort�������ꗗ)�B<BR>
     * <BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Č������̔z��̃\�[�g���s���B<BR>
     * <BR>
     * �P�j�@@ArrayList���쐬<BR>
     * <BR>
     * �Q�j�@@����.�\�[�g�L�[�̔z�񐔕�Loop����<BR>
     * �@@�Q�|�P�j�@@����.�\�[�g�L�[.�L�[���ڂ��擾<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@����.�\�[�g�L�[.����/�~�����擾<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�L�[���ڂɂ�镪�򏈗�<BR>
     * �@@�@@�E�L�[���ڂ������R�[�h�ł������ꍇ�A�M�p�����R�[�hComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ��s��R�[�h�ł������ꍇ�A�M�p�s��R�[�hComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ������敪�ł������ꍇ�A�M�p�����敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ����敪�ł������ꍇ�A�M�p���敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ��ٍϋ敪�ł������ꍇ�A�M�p�ٍϋ敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ��ٍϊ����l�ł������ꍇ�A�M�p�ٍϊ����lComparator��<BR>
     * ��<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ������ł������ꍇ�A�M�p����Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ������ł������ꍇ�A�M�p����Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ��]�����v�ł������ꍇ�A�M�p�]�����vComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�Q�|�R�j�ɂč쐬����Comparator��ArrayList�ɒǉ�<BR>
     * <BR>
     * �R�j�@@ArrayList����Comparator�̔z����쐬<BR>
     * <BR>
     * �S�j�@@Comparator�̔z�񏇂Ɉ���.���������\�[�g<BR>
     * (web3-common)WEB3ArraysUtility.sort(����.�������ꗗ�A<BR>
     * �R�j�ō쐬����Comparator[])<BR>
     * <BR>
     * @@param l_contractInfoList - (�������ꗗ)<BR>
     * �M�p����������̈ꗗ<BR>
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * �M�p����\�[�g�L�[�̈ꗗ<BR>
     * @@roseuid 40E3FA6A019D
     */
    protected void sortContractInfoList(WEB3MarginContractInfo[] l_contractInfoList, WEB3MarginSortKey[] l_sortKey) 
    {
        final String STR_METHOD_NAME = "sortContractInfoList";
        //1ArrayList���쐬
        List l_infoList = new ArrayList();
        //2����.�\�[�g�L�[�̔z�񐔕�Loop����
        if (l_sortKey == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                                 
        }
        int l_intNum = l_sortKey.length;
        for  (int i=0; i < l_intNum; i++)
        {
            //�Q�|�P�j�@@����.�\�[�g�L�[.�L�[���ڂ��擾
            String l_strKeyItem = l_sortKey[i].keyItem;
            //�Q�|�Q�j�@@����.�\�[�g�L�[.����/�~�����擾
            String l_strAD = l_sortKey[i].ascDesc;
            //�Q�|�R�j�@@�L�[���ڂɂ�镪�򏈗�
            // �Q�|�S�j�@@�Q�|�R�j�ɂč쐬����Comparator��ArrayList�ɒǉ�
            //�L�[���ڂ������R�[�h�ł������ꍇ�A�M�p�����R�[�hComparator�𐶐�<BR>
            if (WEB3EquityKeyItemDef.PRODUCTCODE.equals(l_strKeyItem))
            {
                WEB3MarginProductCodeComparator l_product = new WEB3MarginProductCodeComparator(l_strAD);                
                l_infoList.add(l_product);
            }
            //�L�[���ڂ��s��R�[�h�ł������ꍇ�A�M�p�s��R�[�hComparator�𐶐�
            else if (WEB3EquityKeyItemDef.TRADEMARKET.equals(l_strKeyItem))
            {
                WEB3MarginMarketCodeComparator l_market = new WEB3MarginMarketCodeComparator(l_strAD);    
                l_infoList.add(l_market);
            }
            //�L�[���ڂ������敪�ł������ꍇ�A�M�p�����敪Comparator�𐶐�
            else if (WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(l_strKeyItem))
            {
                WEB3MarginAccountTypeComparator l_account = new WEB3MarginAccountTypeComparator(l_strAD);     
                l_infoList.add(l_account);
            }
            //�L�[���ڂ����敪�ł������ꍇ�A�M�p���敪Comparator�𐶐�
            else if (WEB3EquityKeyItemDef.CONTRACT_DIVISION.equals(l_strKeyItem))
            {
                WEB3MarginContractTypeComparator l_contract = new WEB3MarginContractTypeComparator(l_strAD);    
                l_infoList.add(l_contract);
            }
            //�L�[���ڂ��ٍϋ敪�ł������ꍇ�A�M�p�ٍϋ敪Comparator�𐶐�
            else if (WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(l_strKeyItem))
            {
                WEB3MarginRepaymentTypeComparator l_repayment = new WEB3MarginRepaymentTypeComparator(l_strAD);    
                l_infoList.add(l_repayment);
            }
            //�L�[���ڂ��ٍϊ����l�ł������ꍇ�A�M�p�ٍϊ����lComparator��
            else if (WEB3EquityKeyItemDef.REPAYMENTNUM.equals(l_strKeyItem))
            {
                WEB3MarginRepaymentNumComparator l_num = new WEB3MarginRepaymentNumComparator(l_strAD);   
                l_infoList.add(l_num);
            }
            //�L�[���ڂ������ł������ꍇ�A�M�p����Comparator�𐶐�
            else if (WEB3EquityKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {
                WEB3MarginOpenDateComparator l_openDate = new WEB3MarginOpenDateComparator(l_strAD);    
                l_infoList.add(l_openDate);
            }
            //�L�[���ڂ������ł������ꍇ�A�M�p����Comparator�𐶐�
            else if (WEB3EquityKeyItemDef.CLOSEDATE.equals(l_strKeyItem))
            {
                WEB3MarginCloseDateComparator l_closeDate = new  WEB3MarginCloseDateComparator(l_strAD);    
                l_infoList.add(l_closeDate);
            }
            //�L�[���ڂ��]�����v�ł������ꍇ�A�M�p�]�����vComparator�𐶐�
            else if (WEB3EquityKeyItemDef.INCOME.equals(l_strKeyItem))
            {
                WEB3MarginContractUnitAppraisalProfitOrLossComparator l_income = new WEB3MarginContractUnitAppraisalProfitOrLossComparator(l_strAD);    
                l_infoList.add(l_income);
            }              
        }
        //�R�jArrayList����Comparator�̔z����쐬<BR>
        int l_intNum1 = l_infoList.size();
        Comparator[] l_objects = (Comparator[]) l_infoList.toArray(new Comparator[l_intNum1]);
        //�S�jComparator�̔z�񏇂Ɉ���.���������\�[�g
        WEB3ArraysUtility.sort(l_contractInfoList,l_objects); 
    }
}
@
