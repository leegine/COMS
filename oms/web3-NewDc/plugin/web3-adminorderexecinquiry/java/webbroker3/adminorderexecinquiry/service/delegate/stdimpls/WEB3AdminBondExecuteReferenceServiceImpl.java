head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ������Ɖ�T�[�r�XImpl(WEB3AdminBondExecuteReferenceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 �����q(���u) �V�K�쐬
                   2007/02/08 �����Q (���u) �d�l�ύX�E���f��090 
Revesion History : 2007/07/10 ������(���u) �d�l�ύX���f��No.100
Revesion History : 2007/09/26 ���g(���u) ���f��No.108
*/

package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

import webbroker3.adminorderexecinquiry.WEB3AdminBondQueryContainer;
import webbroker3.adminorderexecinquiry.WEB3AdminOrderExecuteDataManager;
import webbroker3.adminorderexecinquiry.define.WEB3BondExecRefUnitKeyItemDivDef;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondAccountCodeComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondBizDateComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondBranchCodeComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondDeliveryDateComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondExecDateComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondForeignDeliveryDateComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondForeignExecDateComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondOrderTypeComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondProductCodeComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondReceivedDateTimeComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondSettlementDivComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondTraderComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefConditionInfo;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionSortKeyUnit;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminBondExecuteReferenceService;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.define.WEB3BondTradingTypeListDef;
import webbroker3.bd.define.WEB3BondTypeListDef;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.OrderUnitIntroduceDivRow;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���Ǘ��Ғ������Ɖ�T�[�r�XImpl)<BR>
 * ���Ǘ��Ғ������Ɖ�T�[�r�X�����N���X<BR>
 *   
 * @@author �����q(���u)
 * @@version 1.0
 */
public class WEB3AdminBondExecuteReferenceServiceImpl 
	implements WEB3AdminBondExecuteReferenceService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteReferenceServiceImpl.class);
    
    /**
     * @@roseuid 44E335A40167
     */
    public WEB3AdminBondExecuteReferenceServiceImpl() 
    {
     
    }
    
    /**
     * ���Ǘ��Ғ������Ɖ�����{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���j�Ǘ��Ғ������Ɖ�execute�v�Q��
     * @@param l_request - (���N�G�X�g)<BR>
     * ��菈���p���N�G�X�g�̊��N���X�B
     * @@throws  
     * @@roseuid 44B7518602DF
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }   
        
        WEB3GenResponse l_response = null;
    
        if (l_request instanceof WEB3AdminORBondExecRefReferenceRequest)
        {
            l_response = 
            	this.getReferenceScreen((WEB3AdminORBondExecRefReferenceRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminORBondExecRefInputRequest)
        {
            l_response = 
            	this.getInputScreen((WEB3AdminORBondExecRefInputRequest) l_request);
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�������Ɖ����)<BR>
     * ���Ǘ��Ғ������Ɖ�����\���������s���B<BR> 
     * <BR>
     * �V�[�P���X�}�u(��)get�������Ɖ�����v�Q��<BR>
     * --------------------------------------------------
     * @@param request - (���N�G�X�g)
     * ���Ǘ��Ғ������Ɖ�����\�����N�G�X�g
     * @@return webbroker3.adminorderexecinquiry.message.WEB3AdminBondExecRefInputResponse
     * @@roseuid 44B753CC0294
     */
    protected WEB3AdminORBondExecRefInputResponse getInputScreen
        (WEB3AdminORBondExecRefInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminORBondExecRefInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate����(String, boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_ORDER_EXECUTE_REFERENCE, false);

        //1.3 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.4 get������( )
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.5 get�ʉ݃R�[�h�ꗗ(�،���ЃR�[�h : String)
        String[] l_strCurrencyCodes = 
            WEB3GentradeCurrency.getCurrencyCodeList(l_strInstitutionCode);
        
        //1.6 create���X�|���X( )
        WEB3AdminORBondExecRefInputResponse l_response = 
            (WEB3AdminORBondExecRefInputResponse) l_request.createResponse();
        
        //1.7 �v���p�e�B�Z�b�g       
        //������
        l_response.orderBizDate = l_datBizDate;
        
        //������ʈꗗ
        String[] l_strTradingTypeList = 
            {
                WEB3BondTradingTypeListDef.BUY,
                WEB3BondTradingTypeListDef.SELL,
                WEB3BondTradingTypeListDef.RECRUIT
            };
        l_response.tradingTypeList = l_strTradingTypeList;
        
        //�������敪�ꗗ
        String[] l_strExecutionStateList = 
            {
                WEB3BondOrderExecStatusDef.UNEXECUTED,
                WEB3BondOrderExecStatusDef.EXECUTED,
                WEB3BondOrderExecStatusDef.CANCELED
            };
        l_response.executionStateList = l_strExecutionStateList;
        
        //���ϋ敪�ꗗ
        String[] l_strSettleDivList = 
            {
                WEB3SettlementDivDef.JAPANESE_CURRENCY, 
                WEB3SettlementDivDef.FOREIGN_CURRENCY
            };
        l_response.settleDivList = l_strSettleDivList;
             
        //�ʉ݃R�[�h�ꗗ
        l_response.currencyCodeList = l_strCurrencyCodes;

        //���^�C�v�ꗗ
        String[] l_strBondTypeList =
            {
                WEB3BondTypeListDef.FOREIGN_BOND,
                WEB3BondTypeListDef.INDIVIDUAL_GOVERNMENT_BOND,
                WEB3BondTypeListDef.CORPORATE_BOND
            };
        l_response.bondTypeList = l_strBondTypeList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
    
    /**
     * (search�������Ɖ�ꗗ)<BR>
     * ���������Ɖ�\���ƌ������������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(��)search�������Ɖ�ꗗ�v�Q��<BR>
     * ---------------------------------------------------------------
     * @@param l_request - (���N�G�X�g)<BR>
     * ���Ǘ��Ғ������Ɖ���\�����N�G�X�g
     * @@return webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceResponse
     * @@throws WEB3BaseException 
     * @@throws NotFoundException 
     * @@throws  
     * @@throws NotFoundException 
     * @@roseuid 44B752B80105
     */
    protected WEB3AdminORBondExecRefReferenceResponse getReferenceScreen(
        WEB3AdminORBondExecRefReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getReferenceScreen(WEB3AdminORBondExecRefReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.validate( )
        l_request.validate();
        
        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_ORDER_EXECUTE_REFERENCE, false);
        
        //1.4 validate���X����(���X�R�[�h : String[])
        //���X�����̃`�F�b�N���s���B 
        //[����] 
        //���X�R�[�h�F�@@���N�G�X�g����擾�������X�R�[�h
        if (l_request.conditionInfo == null)
        {
            log.debug(STR_METHOD_NAME + "�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3Administrator.class +"." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        l_administrator.validateBranchPermission(l_request.conditionInfo.branchCode);
        
        //1.5 get�،���ЃR�[�h( )     
        l_admin.getInstitutionCode();
        
        //1.6 create��������(���Ǘ��Ғ������Ɖ���������, �Ǘ���)
        WEB3AdminBondQueryContainer l_bondQueryContainer = 
            this.createQueryContainer(l_request.conditionInfo, l_administrator);
        
        //1.7 get�������P�ʃ��X�g(���������� : String, �����f�[�^�R���e�i : Object[])
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();
        
        String l_strGetQueryString = l_bondQueryContainer.getQueryString();
        Object[] l_objgGetQueryStringDate = l_bondQueryContainer.getQueryData();
        
        List l_lisbondOrderUnitList = 
            l_bondOrderManager.getBondOrderUnitList(
                l_strGetQueryString, l_objgGetQueryStringDate); 
         
        //1.8 create�������Ɖ�s(�_���r���[::List)
        WEB3AdminORBondExecRefUnit[] l_createExecReferenceUnit = 
            this.createExecReferenceUnit(l_lisbondOrderUnitList);

        //1.9 sort�������Ɖ�s(���Ǘ��Ғ������Ɖ�s[], �������Ɖ�\�[�g�L�[[])
        WEB3AdminORBondExecRefUnit[] l_sortOrderExecRefUnit =
        	this.sortOrderExecRefUnit(
                l_createExecReferenceUnit, 
                l_request.sortKeys);
        
        //1.10 create���X�|���X( )
        WEB3AdminORBondExecRefReferenceResponse l_response = (
            WEB3AdminORBondExecRefReferenceResponse) l_request.createResponse();
                    
        //1.11 WEB3PageIndexInfo(l_objs : �_���r���[::java::lang::Object[], l_intRequestPageIndex : int, l_intRequestPageSize : int)
        int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_pageIndexInfo = 
            new WEB3PageIndexInfo(
                l_sortOrderExecRefUnit,               
                l_intRequestPageIndex,
                l_intRequestPageSize);
       
        //1.12 getTotalPages( )
        int l_intGetTotalPages = l_pageIndexInfo.getTotalPages();
        
        //1.13 getTotalRecords( )
        int l_intGetTotalRecords = l_pageIndexInfo.getTotalRecords();
        
        //1.14 getPageIndex( )
        int l_intGetPageIndex = l_pageIndexInfo.getPageIndex();
        
        List l_lisUnits = new ArrayList();
        Object[] l_objUnits = l_pageIndexInfo.getArrayReturned();
        for (int i = 0; i < l_objUnits.length; i++ )
        {
        	l_lisUnits.add(l_objUnits[i]);
        }
        //1.15 getArrayReturned( )
        WEB3AdminORBondExecRefUnit[] l_adminORBondExecRefUnit = 
        	new WEB3AdminORBondExecRefUnit[l_lisUnits.size()];
        l_lisUnits.toArray(l_adminORBondExecRefUnit);
               
        //1.16 �v���p�e�B�Z�b�g
        //���y�[�W��
        l_response.totalPages = l_intGetTotalPages + "";     
        
        //�\���y�[�W�ԍ�
        l_response.pageIndex = l_intGetPageIndex + "";        
        
        //�����R�[�h��
        l_response.totalRecords = l_intGetTotalRecords + "";
        
        if (l_intGetTotalPages == 0)
        {
            l_response.orderList = null;
        }
        else
        {
            l_response.orderList = l_adminORBondExecRefUnit;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (sort�������Ɖ�s)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��č��Ǘ��Ғ������Ɖ�s�f�[�^�̃\�[�g��<BR>
     * �s���B<BR> 
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>  
     * <BR>
     * �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR> 
     * �@@�@@�@@�@@�@@�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ������r����Comparator�𐶐�����B<BR>  
     * �@@�@@�@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]<BR>  
     * �@@�@@�@@�@@�@@�@@orderBy�F �\�[�g�L�[.�����^�~�� <BR>
     * <BR>
     * �@@�@@�@@�@@�AArrayList�ɐ�������Comparator��ǉ�����B<BR> 
     * <BR>
     * �R�jArrayList����Comparator�̔z����쐬���AWEB3ArraysUtility.sort()���\�b�h���R�[<BR>
     * ������B<BR>  
     * <BR>
     * �S)�\�[�g�������Ǘ��Ғ������Ɖ�s�̔z���ԋp����B<BR>
     * @@param ��_orBondExecRefUnit - (���Ǘ��Ғ������Ɖ�s�z��)<BR>
     * ���Ǘ��Ғ������Ɖ�s�̔z��
     * @@param l_orderExecRefUnit - (�\�[�g�L�[�z��)<BR>
     * �������Ɖ�\�[�g�L�[�̔z��
     * 
     * @@return WEB3AdminORBondExecRefUnit[]
     * @@roseuid 44B7560E016E
     */
    protected WEB3AdminORBondExecRefUnit[] sortOrderExecRefUnit(
        WEB3AdminORBondExecRefUnit[] ��_bondExecRefUnits, 
        WEB3AdminOROrderExecutionSortKeyUnit[] l_sortKeys) 
    {    
        final String STR_METHOD_NAME = 
            "sortOrderExecRefUnit(WEB3AdminORBondExecRefUnit[]," +
            "WEB3AdminOROrderExecutionSortKeyUnit[])";
        log.entering(STR_METHOD_NAME);
        
        //�P�jArrayList�𐶐�����B
        ArrayList l_lisComparator = new ArrayList();
        
        //�Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        //�@@�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ������r����Comparator�𐶐�����B
        //[�R���X�g���N�^�ɃZ�b�g����p�����[�^]
        //orderBy�F �\�[�g�L�[.�����^�~��
        int l_intLength = l_sortKeys.length;
        for (int i = 0; i < l_intLength; i ++)
        { 
            if (WEB3BondExecRefUnitKeyItemDivDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                //(1)(��)�ڋq�R�[�hComparator�𐶐�����B
                WEB3AdminBondAccountCodeComparator l_accountCodeComparator = 
                    new WEB3AdminBondAccountCodeComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_accountCodeComparator);
            }
            
            if (WEB3BondExecRefUnitKeyItemDivDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                //(��)�����R�[�h�iWEB3�jComparator
                WEB3AdminBondProductCodeComparator l_productCodeComparator  =
                    new WEB3AdminBondProductCodeComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_productCodeComparator);
            }
            
            if (WEB3BondExecRefUnitKeyItemDivDef.TRADING_TYPE.equals(l_sortKeys[i].keyItem))
            {
                //(��)�������Comparator
                WEB3AdminBondOrderTypeComparator l_orderTypeComparator =
                    new WEB3AdminBondOrderTypeComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_orderTypeComparator);
            }
            
            if (WEB3BondExecRefUnitKeyItemDivDef.SETTLE_DIV.equals(l_sortKeys[i].keyItem))
            {
                //(��)���ϋ敪Comparator
                WEB3AdminBondSettlementDivComparator l_settlementDivComparator =
                    new WEB3AdminBondSettlementDivComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_settlementDivComparator);
            }
            
            if (WEB3BondExecRefUnitKeyItemDivDef.ACCEPT_ORDER_TIMESTAMP.equals(l_sortKeys[i].keyItem))
            {
                //(��)�󒍓���Comparator
                WEB3AdminBondReceivedDateTimeComparator l_receivedDateTimeComparator =
                    new WEB3AdminBondReceivedDateTimeComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_receivedDateTimeComparator);
            }
       
            if (WEB3BondExecRefUnitKeyItemDivDef.ORDER_BIZ_DATE.equals(l_sortKeys[i].keyItem))
            {
                //(��)����������Comparator
                WEB3AdminBondBizDateComparator l_bizDateComparator = 
                    new WEB3AdminBondBizDateComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_bizDateComparator);
            }
            
            if (WEB3BondExecRefUnitKeyItemDivDef.DOMESTIC_EXECUTION_DATE.equals(l_sortKeys[i].keyItem))
            {
                //(��)��������Comparator
                WEB3AdminBondExecDateComparator l_execDateComparator  = 
                    new WEB3AdminBondExecDateComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_execDateComparator);
            }
            
            if (WEB3BondExecRefUnitKeyItemDivDef.FOREIGN_EXECUTION_DATE.equals(l_sortKeys[i].keyItem))
            {
                //(��)���n����Comparator
                WEB3AdminBondForeignExecDateComparator l_foreignExecDateComparator =
                    new WEB3AdminBondForeignExecDateComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_foreignExecDateComparator);
            }
            
            if (WEB3BondExecRefUnitKeyItemDivDef.DOMESTIC_DELIVERY_DATE.equals(l_sortKeys[i].keyItem))   
            {
                //(��)��n��Comparator
                WEB3AdminBondDeliveryDateComparator l_deliveryDateComparator =
                    new WEB3AdminBondDeliveryDateComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_deliveryDateComparator);
            }
                       
            if (WEB3BondExecRefUnitKeyItemDivDef.FOREIGN_DELIVERY_DATE.equals(l_sortKeys[i].keyItem))
            {
                //(��)���n��n��Comparator
                WEB3AdminBondForeignDeliveryDateComparator l_foreignDeliveryDateComparator  =
                    new WEB3AdminBondForeignDeliveryDateComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_foreignDeliveryDateComparator);
            }

            if (WEB3BondExecRefUnitKeyItemDivDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                //(��)���X�R�[�hComparator
                WEB3AdminBondBranchCodeComparator l_branchCodeComparator  =
                    new WEB3AdminBondBranchCodeComparator(l_sortKeys[i].ascDesc);

                l_lisComparator.add(l_branchCodeComparator);
            }

            if (WEB3BondExecRefUnitKeyItemDivDef.SONAR_TRADER_CODE.equals(l_sortKeys[i].keyItem))
            {
                //(��)����Comparator
                WEB3AdminBondTraderComparator l_traderComparator  =
                    new WEB3AdminBondTraderComparator(l_sortKeys[i].ascDesc);

                l_lisComparator.add(l_traderComparator);
            }
        }
        //3�jArrayList����Comparator�̔z����擾����B
        Comparator[] l_comparators = new Comparator[l_lisComparator.size()];
        l_lisComparator.toArray(l_comparators);
        WEB3ArraysUtility.sort(��_bondExecRefUnits, l_comparators);

        //�S)�\�[�g�������Ǘ��Ғ������Ɖ�s�̔z���ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return ��_bondExecRefUnits;   
    }
    
    /**
     * (create�������Ɖ�s)<BR>
     * ����.�������P�ʃ��X�g������Ǘ��Ғ������Ɖ�s�̔z����쐬�B<BR>
     * <BR>
     * �P�j����.�������P�ʃ��X�g�̗v�f����Loop����B<BR>
     * ���v���p�e�B�Z�b�g���A�e���ڂ�NULL�łȂ��ꍇ�̂݃Z�b�g����B<BR>
     * �@@�P�|�P�jLoop�̍������P�ʖ��̕��XID�A�ڋqID�A����ID�A�I�y���[�^�h�c�ɑΉ�����<BR>
     * �@@�@@�@@�@@�@@���X�R�[�h�A�\���ڋq�R�[�h�A�����R�[�h�iWEB3�j�A�������A�I�y���[�^�R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�E���X�R�[�h�ƕ\���ڋq�R�[�h�́A�g���A�J�E���g�}�l�[�W��.get�ڋq�i�ڋqID�j<BR>
     *�@@�@@�@@�@@�@@�@@�̎擾�I�u�W�F�N�g����擾<BR>
     * �@@�@@�@@�@@�@@�E�����R�[�h�iWEB3�j�Ɩ������́A���v���_�N�g�}�l�[�W��.get�������i<BR>
     *�@@�@@�@@�@@�@@�@@����ID�j�̎擾�I�u�W�F�N�g����擾<BR>
     * �@@�@@�@@�@@�@@�E�I�y���[�^�R�[�h�́A�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�����(�I�y���[<BR>
     *�@@�@@�@@�@@�@@�@@�^ID)�̎擾�I�u�W�F�N�g����擾<BR>
     * <BR>
     * �@@�P�|�Q�j���Ǘ��Ғ������Ɖ�s�𐶐�����B<BR>
     * <BR>
     * �@@�P�|�R�j���Ǘ��Ғ������Ɖ�s�̑������Z�b�g����B<BR>
     * <BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.����ID�@@�@@�@@�@@�@@�@@�@@���������P��.����ID<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.���X�R�[�h�@@�@@�@@�@@�@@���P�|�P�j�Ŏ擾�������X�R�[�h<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�ڋq�R�[�h�@@�@@�@@�@@�@@���P�|�P�j�Ŏ擾�����\���ڋq�R�[�h <BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�����R�[�h�iWEB3�j���P�|�P�j�Ŏ擾���������R�[�h(WEB3�j<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�������@@�@@�@@�@@�@@�@@�@@���P�|�P�j�Ŏ擾����������<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.���ϋ敪�@@�@@�@@�@@�@@���������P��.���ϋ敪<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�������ʁ@@�@@�@@�@@�@@���������P��.��������<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�w�l�@@�@@�@@�@@�@@�@@�@@�@@���������P��.�w�l<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.��������i�~�݁j�@@���������P��.��������i�~�݁j<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�o�ߗ��q�i�~�݁j�@@���������P��.�o�ߗ��q�i�~�݁j<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.��n����i�~�݁j�@@���������P��.��n����i�~�݁j<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�ʉ݃R�[�h�@@�@@�@@�@@�@@���������P��.�ʉ݃R�[�h<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.��������i�O�݁j�@@���������P��.��������i�O�݁j<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�o�ߗ��q�i�O�݁j�@@���������P��.�o�ߗ��q�i�O�݁j<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.��n����i�O�݁j�@@���������P��.��n����i�O�݁j<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�󒍓����@@�@@�@@�@@�@@�@@���������P��.�󒍓���<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�������@@�@@�@@�@@�@@�@@�@@���������P��.������<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�����@@�@@�@@�@@�@@�@@�@@���������P��.����<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.���n�����@@�@@�@@�@@���������P��.���n����<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�������敪�@@�@@�@@���������P��.�������敪<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�����o�H�敪�@@�@@�@@���������P��.�����o�H�敪<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.���񒍕��̒����`���l�����������P��.���񒍕��̒����`���l��<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�I�y���[�^�R�[�h�@@�@@�@@���P�|�P�j�Ŏ擾�����I�y���[�^�R�[�h<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.���҃R�[�h�iSONAR�j���������P��.���҃R�[�h�iSONAR�j<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.�Ǘ��҃R�[�h�@@�@@�@@�@@���������P��.�Ǘ��҃R�[�h<BR>
     * <BR>
     * �@@�P�|�S�j������ʂ��Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�������P��.get������ʔ���()�ō�������ʔ���I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�E��������ʔ���.is���偁��true�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@���Ǘ��Ғ������Ɖ�s.������ʁ@@�@@�@@�@@�@@���f����f<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�E��������ʔ���.is���t����true�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@���Ǘ��Ғ������Ɖ�s.������ʁ@@�@@�@@�@@�@@���f���t�f<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�E��������ʔ���.is���p����true�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@���Ǘ��Ғ������Ɖ�s.������ʁ@@�@@�@@�@@�@@���f���p�f<BR>
     * <BR>
     * �@@�P�|�T�j��n���A���n��n�����Z�b�g����B<BR>
     * �@@�@@�@@�@@�E��������ʔ���.is���咍�� == true�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@���Ǘ��Ғ������Ɖ�s.��n���@@�@@�@@�@@�@@�@@�@@���������P��.������<BR>
     * �@@�@@�@@�@@�@@�@@���Ǘ��Ғ������Ɖ�s.���n��n��(*1)�@@�@@���������P��.������<BR>
     * <BR>
     * �@@�@@�@@�@@�E��������ʔ���.is���咍�� != true�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@���Ǘ��Ғ������Ɖ�s.��n���@@�@@�@@�@@�@@�@@�@@���������P��.��n��<BR>
     * �@@�@@�@@�@@�@@�@@���Ǘ��Ғ������Ɖ�s.���n��n��(*1)�@@�@@���������P��.���n��n��<BR>
     * <BR>
     * �@@�@@�@@�@@(*1)�������P��.get���^�C�v != �h�O�����h �̏ꍇ�Anull���Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �@@�P�|�U�j�������P��.��萔�ʁ��O�̏ꍇ<BR>
     * �@@�@@�@@�@@�E���Ǘ��Ғ������Ɖ�s.��萔�ʁ@@�@@�@@�@@�@@���������P��.��萔��<BR>
     * <BR>
     * �@@�P�|�V�j�������P��.���P�����O�̏ꍇ<BR>
     * �@@�@@�E���Ǘ��Ғ������Ɖ�s.���P���@@�@@�@@�@@�@@���������P��.���P��<BR>
     * <BR>
     * �@@�P�|�W�j�בփ��[�g�͈ȉ��̂悤�ɐݒ肷��B<BR>
     * �@@�@@�E�������P��.get�������敪�����f�����f�̏ꍇ<BR>
     * �@@�@@�@@�@@���Ǘ��Ғ������Ɖ�s.�בփ��[�g���������P��.get��בփ��[�g()<BR>
     * �@@�@@�E�������P��.get�������敪�����f���ρf�̏ꍇ<BR>
     * �@@�@@�@@�@@���Ǘ��Ғ������Ɖ�s.�בփ��[�g���������P��.get���בփ��[�g()<BR>
     * �@@�@@�E��L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�������P��.���בփ��[�g != null�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@���Ǘ��Ғ������Ɖ�s.�בփ��[�g���������P��.get���בփ��[�g()<BR>
     * �@@�@@�@@�@@�������P��.��בփ��[�g != null�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@���Ǘ��Ғ������Ɖ�s.�בփ��[�g���������P��.get��בփ��[�g()<BR>
     * <BR>
     * �@@�P�|�X�j�Љ�X�敪�ƏЉ�X�R�[�h�͈ȉ��̂悤�ɐݒ肷��B<BR>
     * <BR>
     * �@@�@@�P�|�X�|�P�j�������P��.get�����P�ʏЉ�敪���Ă�<BR>
     * <BR>
     * �@@�@@�P�|�X�|�Q�j�擾���������P�ʏЉ�敪Row�I�u�W�F�N�g��null�łȂ��ꍇ�A�ȉ��̒l<BR>
     *�@@�@@�@@�@@�@@���Z�b�g����B<BR>
     * �@@�@@�@@�E���Ǘ��Ғ������Ɖ�s.�Љ�X�敪<BR>�@@�@@ 
     *�@@�@@�@@�@@�@@�������P�ʏЉ�敪Row�I�u�W�F�N�g.get�Љ�敪<BR>
     * �@@�@@�@@�E���Ǘ��Ғ������Ɖ�s.�Љ�X�R�[�h�@@�@@�������P�ʏЉ�敪Row�I�u�W�F�N<BR>
     *�@@�@@�@@�@@�@@�g.�Љ�X�R�[�h<BR>
     * <BR>
     * �@@�P�|�P�O�j���b�N�����{�^���敪�͈ȉ��̂悤�ɐݒ肷��B<BR>
     * �@@�@@�E���Ǘ��҃w���p�[�T�[�r�X.get���b�N�����{�^���敪�Ŏ擾�����l��ݒ肷��B<BR>
     * <BR>
     * <BR>
     * �@@�P�|�P�P�j���ύX�{�^���敪�͈ȉ��̂悤�ɐݒ肷��B<BR>
     * �@@�@@�E���Ǘ��҃w���p�[�T�[�r�X.get���ύX�{�^���敪()�Ŏ擾�����l��ݒ肷��B<BR>
     * <BR>
     * <BR>
     * �@@�P�|�P�Q�j����{�^���敪�͈ȉ��̂悤�ɐݒ肷��B<BR>
     * �@@�@@�E���Ǘ��҃w���p�[�T�[�r�X.get����{�^���敪()�Ŏ擾�����l��ݒ肷��B<BR>
     * <BR>
     * �@@�P�|�P�R�j���Ǘ��Ғ������Ɖ�s�̔z��ɍ��Ǘ��Ғ������Ɖ�s��ǉ�����B
     * <BR>
     * <BR>
     * �Q�jLoop�I����A���Ǘ��Ғ������Ɖ�s�̔z���Ԃ��B
     * @@param l_lisBondOrderUnits - (�������P�ʃ��X�g)<BR>
     * �g���������P�ʃ��X�g
     * @@return webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefUnit[]
     * @@throws WEB3BaseException 
     * @@throws  
     * @@throws NotFoundException 
     * @@roseuid 44B7569A01F0
     */
    protected WEB3AdminORBondExecRefUnit[] createExecReferenceUnit(
        List l_lisBondOrderUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createExecReferenceUnit(List l_lisBondOrderUnits)";
        log.entering(STR_METHOD_NAME);
        //�P�j����.�������P�ʃ��X�g�̗v�f����Loop����B 
        //�P�|�P�jLoop�̍������P�ʖ��̕��XID�A�ڋqID�A����ID�A�I�y���[�^�h�c�ɑΉ����� 
        //�@@�@@�@@�@@���X�R�[�h�A�ڋq�R�[�h�A�����R�[�h�iWEB3�j�A�������A�I�y���[�^�R�[�h���擾����B 
        
        ArrayList l_lisTemp = new ArrayList();
        for (int i = 0; i < l_lisBondOrderUnits.size(); i++)
        {              
        	BondOrderUnitRow l_orderUnitRow = (BondOrderUnitRow) l_lisBondOrderUnits.get(i);
            WEB3BondOrderUnit l_bondOrderUnit = new WEB3BondOrderUnit(l_orderUnitRow);
              
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
            	(WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            //�E���X�R�[�h�ƌڋq�R�[�h�́A�g���A�J�E���g�}�l�[�W��.get�ڋq�i�ڋqID�j�̎擾�I�u�W�F�N�g����擾 
            WEB3GentradeMainAccount l_mainAccount = null;
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            String l_strProductCode = null;
            String l_strProductName = null;
            String l_strTraderCode = null;
            Trader l_trader = null;
            BondOrderUnitRow l_bondOrderUnitRow = 
            	(BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();
            try
            {
                l_mainAccount = 
                	(WEB3GentradeMainAccount) l_accountManager.getMainAccount(
            			l_bondOrderUnit.getAccountId());

                l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
                l_strAccountCode = l_mainAccount.getDisplayAccountCode();
               
                //�E�����R�[�h�iWEB3�j�Ɩ������́A���v���_�N�g�}�l�[�W��.get�������i����ID�j�̎擾�I�u�W�F�N�g����擾 
                WEB3BondProductManager l_productManager = 
                    (WEB3BondProductManager) l_finApp.getTradingModule(
                		ProductTypeEnum.BOND).getProductManager();
                WEB3BondProduct l_bondProduct = null;
                l_bondProduct = 
                    (WEB3BondProduct) l_productManager.getBondProduct(
                		l_bondOrderUnit.getProduct().getProductId());
                l_strProductCode = l_bondProduct.getProductCode();
                l_strProductName = l_bondProduct.getProductName();
                
                //�E�I�y���[�^�R�[�h�́A�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�����(�I�y���[�^ID)�̎擾�I�u�W�F�N�g����擾
                WEB3GentradeFinObjectManager l_finObjManager = 
                    (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                if (!((BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject
                        ()).getTraderIdIsNull())
                {
                    l_trader = l_finObjManager.getTrader(l_bondOrderUnit.getTraderId());
                    l_strTraderCode = l_trader.getTraderCode();
                }
            } 
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
                  
            //�P�|�Q�j���Ǘ��Ғ������Ɖ�s�𐶐�����B
            WEB3AdminORBondExecRefUnit l_bondExecRefUnit = new WEB3AdminORBondExecRefUnit();
   
            //�P�|�R�j���Ǘ��Ғ������Ɖ�s�̑������Z�b�g����B 
            //  �E���Ǘ��Ғ������Ɖ�s.����ID�@@�@@�@@�@@�@@�@@�@@���������P��.����ID 
            if (l_bondOrderUnitRow.getOrderIdIsSet())
            {
            	l_bondExecRefUnit.id = l_bondOrderUnit.getOrderId() + "";
            }
          
            //�@@�E���Ǘ��Ғ������Ɖ�s.���X�R�[�h�@@�@@�@@�@@�@@���P�|�P�j�Ŏ擾�������X�R�[�h
            if (l_strBranchCode != null)
            {
            	l_bondExecRefUnit.branchCode = l_strBranchCode;
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.�ڋq�R�[�h�@@�@@�@@�@@�@@���P�|�P�j�Ŏ擾�����\���ڋq�R�[�h 
            if (l_strAccountCode != null)
            {
            	l_bondExecRefUnit.accountCode = l_strAccountCode;
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.�����R�[�h�iWEB3�j���P�|�P�j�Ŏ擾���������R�[�h(WEB3�j
            if (l_strProductCode != null)
            {
            	l_bondExecRefUnit.productCode = l_strProductCode;
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.�������@@�@@�@@�@@�@@�@@�@@���P�|�P�j�Ŏ擾����������
            if (l_strProductName != null)
            {
            	l_bondExecRefUnit.productName = l_strProductName;
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.���ϋ敪�@@�@@�@@�@@�@@���������P��.���ϋ敪
            if (l_bondOrderUnit.getSettlementDiv() != null)
            {
            	l_bondExecRefUnit.settleDiv = l_bondOrderUnit.getSettlementDiv();
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.�������ʁ@@�@@�@@�@@�@@���������P��.�������� 
            if (l_bondOrderUnitRow.getQuantityIsSet())
            {
	            l_bondExecRefUnit.orderQuantity = 
	                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getQuantity());
            }
           
            //�@@�E���Ǘ��Ғ������Ɖ�s.�w�l�@@�@@�@@�@@�@@�@@�@@�@@���������P��.�w�l 
            if (!l_bondOrderUnitRow.getLimitPriceIsNull())
            {
	            l_bondExecRefUnit.limitOrderPrice = 
	                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getLimitPrice()) ;
            }
            
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.��������i�~�݁j�@@���������P��.��������i�~�݁j
            if (!l_bondOrderUnitRow.getTradingPriceIsNull())
            {
	            l_bondExecRefUnit.yenTradePrice =
	                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getTradingPrice());
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.�o�ߗ��q�i�~�݁j�@@���������P��.�o�ߗ��q�i�~�݁j
            if (!l_bondOrderUnitRow.getAccruedInterestIsNull())
            {
	            l_bondExecRefUnit.yenAccruedInterest = 
	                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getAccruedInterest());
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.��n����i�~�݁j�@@���������P��.��n����i�~�݁j
            if (!l_bondOrderUnitRow.getEstimatedPriceIsNull())
            {
	            l_bondExecRefUnit.yenDeliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getEstimatedPrice());
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.�ʉ݃R�[�h�@@�@@�@@�@@�@@���������P��.�ʉ݃R�[�h
            if (l_bondOrderUnit.getCurrencyCode() != null)
            {
            	l_bondExecRefUnit.currencyCode = l_bondOrderUnit.getCurrencyCode();
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.��������i�O�݁j�@@���������P��.��������i�O�݁j
            if (!l_bondOrderUnitRow.getForeignTradingPriceIsNull())
            {
	            l_bondExecRefUnit.foreignTradePrice = 
	                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getForeignTradingPrice());
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.�o�ߗ��q�i�O�݁j�@@���������P��.�o�ߗ��q�i�O�݁j
            if (!l_bondOrderUnitRow.getForeignAccruedInterestIsNull())
            {
	            l_bondExecRefUnit.foreignAccruedInterest = 
	                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getForeignAccruedInterest());
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.��n����i�O�݁j�@@���������P��.��n����i�O�݁j
            if (!l_bondOrderUnitRow.getForeignEstimatedPriceIsNull())
            {
	            l_bondExecRefUnit.foreignDeliveryPrice = 
	                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getForeignEstimatedPrice());
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.�󒍓����@@�@@�@@�@@�@@�@@���������P��.�󒍓���
            if (l_bondOrderUnit.getReceivedDateTime() != null)
            {
	            l_bondExecRefUnit.acceptOrderTimeStamp = l_bondOrderUnit.getReceivedDateTime();
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.�������@@�@@�@@�@@�@@�@@�@@���������P��.������ 
            if (l_bondOrderUnitRow.getBizDateIsSet())
            {
	            l_bondExecRefUnit.orderBizDate = 
	                WEB3DateUtility.getDate(l_bondOrderUnit.getBizDate(), "yyyyMMdd");      
            }
            //�@@�E���Ǘ��Ғ������Ɖ�s.�����@@�@@�@@�@@�@@�@@�@@���������P��.���� 
            if (l_bondOrderUnit.getExecDate() != null)
            {
	            l_bondExecRefUnit.domesticExecutionDate = l_bondOrderUnit.getExecDate();
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.���n�����@@�@@�@@�@@���������P��.���n����
            if (l_bondOrderUnit.getForeignExecDate() != null)
            {
	            l_bondExecRefUnit.foreignExecutionDate = l_bondOrderUnit.getForeignExecDate();
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.�������敪�@@�@@�@@���������P��.�������敪 
            if (l_bondOrderUnitRow.getOrderExecStatusIsSet())
            {
            	l_bondExecRefUnit.executionState = l_bondOrderUnit.getOrderExecStatus();
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.�����o�H�敪�@@�@@�@@���������P��.�����o�H�敪
            if (l_bondOrderUnit.getOrderRootDiv() != null)
            {
            	l_bondExecRefUnit.orderRootDiv = l_bondOrderUnit.getOrderRootDiv();
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.���񒍕��̒����`���l�����������P��.���񒍕��̒����`���l��
            if (l_bondOrderUnit.getOrderChannel() != null)
            {
            	l_bondExecRefUnit.orderChannel = l_bondOrderUnit.getOrderChannel();
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.�I�y���[�^�R�[�h�@@�@@�@@���P�|�P�j�Ŏ擾�����I�y���[�^�R�[�h
            if (l_strTraderCode != null)
            {
            	l_bondExecRefUnit.operatorCode = l_strTraderCode;
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.���҃R�[�h�iSONAR�j���������P��.���҃R�[�h�iSONAR�j
            if (l_bondOrderUnit.getSonarTraderCode() != null)
            {
            	l_bondExecRefUnit.sonarTraderCode = l_bondOrderUnit.getSonarTraderCode();
            }
            
            //�@@�E���Ǘ��Ғ������Ɖ�s.�Ǘ��҃R�[�h�@@�@@�@@�@@���������P��.�Ǘ��҃R�[�h
            if (l_bondOrderUnit.getAdminstratorCode() != null)
            {
            	l_bondExecRefUnit.administratorCode = l_bondOrderUnit.getAdminstratorCode();
            }
   
            //�P�|�S�j������ʂ��Z�b�g����B 
            //�������P��.get������ʔ���()�ō�������ʔ���I�u�W�F�N�g���擾����B 
            //�E��������ʔ���.is���偁��true�̏ꍇ�A 
            //���Ǘ��Ғ������Ɖ�s.������ʁ@@�@@�@@�@@�@@���f����f 
            //�E��������ʔ���.is���t����true�̏ꍇ�A 
            //���Ǘ��Ғ������Ɖ�s.������ʁ@@�@@�@@�@@�@@���f���t�f
            //�E��������ʔ���.is���p����true�̏ꍇ�A
            //���Ǘ��Ғ������Ɖ�s.������ʁ@@�@@�@@�@@�@@���f���p�f
            WEB3BondOrderTypeJudge l_bondOrderTypeJudge = l_bondOrderUnit.getBondOrderTypeJudge();
            if (l_bondOrderTypeJudge.isRecruitOrder())
            {               
                l_bondExecRefUnit.tradingType = WEB3BondTradingTypeListDef.RECRUIT;
            }
            else if (l_bondOrderTypeJudge.isBuyOrder())
            {
                l_bondExecRefUnit.tradingType = WEB3BondTradingTypeListDef.BUY;
            }
            else if (l_bondOrderTypeJudge.isSellOrder())
            {
                l_bondExecRefUnit.tradingType = WEB3BondTradingTypeListDef.SELL;
            }
            
            //�P�|�T�j��n���A���n��n�����Z�b�g����B
            if (l_bondOrderTypeJudge.isRecruitOrder())
            {
                //�E��������ʔ���.is���咍�� == true�̏ꍇ�A
                //���Ǘ��Ғ������Ɖ�s.��n�����������P��.������
                l_bondExecRefUnit.domesticDeliveryDate = l_bondOrderUnit.getPaymentDate();

                //���Ǘ��Ғ������Ɖ�s.���n��n��(*1)���������P��.������
                l_bondExecRefUnit.foreignDeliveryDate = l_bondOrderUnit.getPaymentDate();
            }
            else
            {
                //�E��������ʔ���.is���咍�� != true�̏ꍇ�A
                //���Ǘ��Ғ������Ɖ�s.��n�����������P��.��n��
                l_bondExecRefUnit.domesticDeliveryDate = l_bondOrderUnit.getDeliveryDate();

                //���Ǘ��Ғ������Ɖ�s.���n��n��(*1)���������P��.���n��n��
                l_bondExecRefUnit.foreignDeliveryDate = l_bondOrderUnit.getForeignDeliveryDate();
            }
            //(*1)�������P��.get���^�C�v != �h�O�����h �̏ꍇ�Anull���Z�b�g����B
            if (!BondTypeEnum.FOREIGN_BOND.equals(l_bondOrderUnit.getBondType()))
            {
                l_bondExecRefUnit.foreignDeliveryDate = null;
            }

            //�P�|�U�j���Ǘ��Ғ������Ɖ�s.��萔�ʁ@@�@@�@@�@@�@@���������P��.��萔��
            if (l_bondOrderUnit.getExecutedQuantity() > 0)
            {
                l_bondExecRefUnit.execQuantity = WEB3StringTypeUtility.formatNumber(
                    l_bondOrderUnit.getExecutedQuantity());
            }
            
            //�P�|�V�j�������P��.���P�����O�̏ꍇ 
            //�E���Ǘ��Ғ������Ɖ�s.���P���@@�@@�@@�@@�@@���������P��.���P�� 
            if (l_bondOrderUnit.getExecutedPrice() > 0)
            {
                l_bondExecRefUnit.execPrice = WEB3StringTypeUtility.formatNumber(
                    l_bondOrderUnit.getExecutedPrice());
            }
            
            //1�|�W�j�בփ��[�g�͈ȉ��̂悤�ɐݒ肷��B
            //�E�������P��.get�������敪�����f�����f�̏ꍇ
            //   ���Ǘ��Ғ������Ɖ�s.�בփ��[�g���������P��.get��בփ��[�g()
            //�E�������P��.get�������敪�����f���ρf�̏ꍇ
            //   ���Ǘ��Ғ������Ɖ�s.�בփ��[�g���������P��.get���בփ��[�g()        
            //�E��L�ȊO�̏ꍇ
            //   �������P��.���בփ��[�g != null�̏ꍇ
            //     ���Ǘ��Ғ������Ɖ�s.�בփ��[�g���������P��.get���בփ��[�g()
            //   �������P��.��בփ��[�g != null�̏ꍇ
            //     ���Ǘ��Ғ������Ɖ�s.�בփ��[�g���������P��.get��בփ��[�g()
            if (WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_bondOrderUnit.getOrderExecStatus()))
            {
            	if (!l_bondOrderUnitRow.getBaseFxRateIsNull())
            	{
            		l_bondExecRefUnit.fxRate = WEB3StringTypeUtility.formatNumber(
        				l_bondOrderUnit.getBaseFxRate());
            	}
            }
            else if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_bondOrderUnit.getOrderExecStatus()))
            {
            	if (!l_bondOrderUnitRow.getExecFxRateIsNull())
            	{
	                l_bondExecRefUnit.fxRate = WEB3StringTypeUtility.formatNumber(
	                    l_bondOrderUnit.getExecFxRate());
            	}
            }
            else
            {
                if (!l_bondOrderUnitRow.getExecFxRateIsNull())
                {
                    l_bondExecRefUnit.fxRate = WEB3StringTypeUtility.formatNumber(
                        l_bondOrderUnit.getExecFxRate());
                }
                else if (!l_bondOrderUnitRow.getBaseFxRateIsNull())
                {
                    l_bondExecRefUnit.fxRate = WEB3StringTypeUtility.formatNumber(
                        l_bondOrderUnit.getBaseFxRate());
                }
            }
            
            //�@@�P�|�X�j�Љ�X�敪�ƏЉ�X�R�[�h�͈ȉ��̂悤�ɐݒ肷��B
            //�P�|�X�|�P�j�������P��.get�����P�ʏЉ�敪���Ă� 
            OrderUnitIntroduceDivRow l_orderUnitIntroduceDiv = l_bondOrderUnit.getOrderUnitIntroduceDiv();
            
            //�P�|�X�|�Q�j�擾���������P�ʏЉ�敪Row�I�u�W�F�N�g��null�łȂ��ꍇ�A�ȉ��̒l���Z�b�g����B 
            //�@@�@@�E���Ǘ��Ғ������Ɖ�s.�Љ�X�敪�@@�@@ �������P�ʏЉ�敪Row�I�u�W�F�N�g.get�Љ�敪
            if (l_orderUnitIntroduceDiv != null)
            {
	            l_bondExecRefUnit.introduceStoreDiv = l_orderUnitIntroduceDiv.getIntroduceBranchDiv();
	            
	            //�@@�@@�E���Ǘ��Ғ������Ɖ�s.�Љ�X�R�[�h�@@�@@�������P�ʏЉ�敪Row�I�u�W�F�N�g.�Љ�X�R�[�h 
	            l_bondExecRefUnit.introduceStoreCode =  l_orderUnitIntroduceDiv.getIntroduceBranchCode();
            }
            
            //�P�|�P0�j���b�N�����{�^���敪�͈ȉ��̂悤�ɐݒ肷��B 
            WEB3AdminBondHelperService l_service = 
                (WEB3AdminBondHelperService) Services.getService(WEB3AdminBondHelperService.class);
            
            //�@@�E���Ǘ��҃w���p�[�T�[�r�X.get���b�N�����{�^���敪�Ŏ擾�����l��ݒ肷��B             
            l_bondExecRefUnit.lockDiv = l_service.getOrderLockButtonDiv(l_bondOrderUnit);
            
            //�P�|�P�P�j���ύX�{�^���敪�͈ȉ��̂悤�ɐݒ肷��B 
            //�@@�E���Ǘ��҃w���p�[�T�[�r�X.get���ύX�{�^���敪()�Ŏ擾�����l��ݒ肷��B 
            l_bondExecRefUnit.execChgDiv = l_service.getExecuteChangButtonDiv(l_bondOrderUnit);
            
            //�P�|�P�Q�j����{�^���敪�͈ȉ��̂悤�ɐݒ肷��B 
            //�@@�E���Ǘ��҃w���p�[�T�[�r�X.get����{�^���敪()�Ŏ擾�����l��ݒ肷��B 
            l_bondExecRefUnit.cancelDiv = l_service.getCancelButtonDiv(l_bondOrderUnit);
            
            //�P�|�P�R�j���Ǘ��Ғ������Ɖ�s�̔z��ɍ��Ǘ��Ғ������Ɖ�s��ǉ�����B 
            
            l_lisTemp.add(l_bondExecRefUnit);
        }
        
        WEB3AdminORBondExecRefUnit[] l_adminORBondExecRefUnit =
            new WEB3AdminORBondExecRefUnit[l_lisTemp.size()];
        l_lisTemp.toArray(l_adminORBondExecRefUnit);

        log.exiting(STR_METHOD_NAME);
        return l_adminORBondExecRefUnit;
    }   

    /**
     * (create��������)<BR>
     *������茟�������{����ׂ̌��������R���e�i�𐶐����ĕԋp����B<BR>
     *<BR>
     *1) ���X��������������������ɒǉ�����B <BR>
     *�@@�@@���Ǘ��Ғ������Ɖ���������.���X�R�[�h�̗v�f�����A�g���A�J�E���g�}�l�[�W�����畔�X���擾�B <BR>
     *�@@�@@(�Y�����镔�X��񂪌�����Ȃ��ꍇ�A�G���[�u�Y�����X�f�[�^�Ȃ��v�G���[���X���[����) <BR>
     * �@@�@@class    : WEB3BusinessLayerException<BR>
     * �@@�@@tag      : BUSINESS_ERROR_01386<BR>
     *    ��������������� " ���XID in (?, ?,,,) "��ǉ����� <BR>
     *    ���������f�[�^�Ɂ@@��L�ɑΉ����镔�X.���XID�@@��ǉ����� <BR>
     *<BR>
     *2) ���Ǘ��Ғ������Ɖ���������.����ID!=null�̏ꍇ�F <BR>
     *    ��������������� " and ����ID = ? "��ǉ����� <BR><BR>
     *    ���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.����ID�@@��ǉ����� <BR>
     *<BR>
     *3) ���Ǘ��Ғ������Ɖ���������.�����R�[�h�iWEB3�j!=null�̏ꍇ�F <BR>
     *    �����R�[�h�iWEB3�j�ō������}�l�[�W��������������擾�B(�Y���������<BR>
     *    ����������Ȃ��ꍇ�A�G���[�u�w�肵�������R�[�h�ɍ��v���Ă�����������݂��܂���B�v<BR>
     *    ���X���[����) <BR>
     * �@@�@@class    : WEB3BusinessLayerException<BR>
     * �@@�@@tag      : BUSINESS_ERROR_00301<BR>
     *    ��������������� " and ����ID = ? "��ǉ����� <BR>
     *    ���������f�[�^�Ɂ@@������.����ID�@@��ǉ����� <BR>
     *<BR>
     *4) ���Ǘ��Ғ������Ɖ���������.�ڋq�R�[�h!=null�̏ꍇ�F <BR>
     *    �Ǘ��Ғ������Ɖ�f�[�^�}�l�[�W��.get�ڋq�ꗗ(�،���ЃR�[�h, ���X�R�[�h, �ڋq�R�[�h)�Ōڋq�ꗗ���擾�B <BR>
     *    (�Y������ڋq��������Ȃ��ꍇ�A�G���[�u�Y������ڋq�����݂��܂���B�v���X���[����) <BR>
     *    [����] <BR>
     * �@@�@@class    : WEB3BusinessLayerException<BR>
     * �@@�@@tag      : BUSINESS_ERROR_01035<BR>
     *�@@�@@�@@�،���ЃR�[�h�F�Ǘ���.�،���ЃR�[�h <BR>
     *�@@�@@�@@���X�R�[�h�F���Ǘ��Ғ������Ɖ���������.���X�R�[�h <BR>
     *�@@�@@�@@�ڋq�R�[�h�F���Ǘ��Ғ������Ɖ���������.�ڋq�R�[�h <BR>
     *    ��������������� " and ����ID in (?, ?,,,) "��ǉ����� <BR>
     *    ���������f�[�^�Ɂ@@��L�ɑΉ�����ڋq.����ID�@@��ǉ����� <BR>
     *<BR>
     *5) ���Ǘ��Ғ������Ɖ���������.������!=null�̏ꍇ�F <BR>
     *    ��������������� " and ������ = ? "��ǉ����� <BR>
     *    ���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.�������@@��ǉ����� <BR>
     *<BR>
     *6) ���Ǘ��Ғ������Ɖ���������.����!=null�̏ꍇ�F <BR>
     *    ��������������� " and ���� = ? "��ǉ����� <BR>
     *    ���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.�����@@��ǉ����� <BR>
     *<BR>
     *7) ���Ǘ��Ғ������Ɖ���������.�������!=null�̏ꍇ�F <BR>
     *    [������ʁ������t�@@���́@@������ʁ������p�@@�̏ꍇ] <BR>
     *    �@@�@@��������������� " and ������� = ? "��ǉ����� <BR>
     *    �@@�@@���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.������ʁ@@��ǉ����� <BR>
     *    �@@�@@��������������� " and ��� = ? "��ǉ����� <BR>
     *    �@@�@@���������f�[�^�Ɂ@@�����d�؎���@@��ǉ����� <BR>
     *�@@�@@[������ʁ�������@@�̏ꍇ] <BR>
     *    �@@�@@��������������� " and (�i������� = ? "��ǉ����� <BR>
     *    �@@�@@���������f�[�^�Ɂ@@�����t�@@��ǉ����� <BR>
     *    �@@�@@��������������� " and ��� = ?�j "��ǉ����� <BR>
     *    �@@�@@���������f�[�^�Ɂ@@��W����@@��ǉ����� <BR>
     *        ��������������� " or ������� = ? )"��ǉ����� <BR>
     *�@@�@@    ���������f�[�^�Ɂ@@���������咍���@@��ǉ����� <BR>
     *<BR>
     *8) ���Ǘ��Ғ������Ɖ���������.�������敪!=null�̏ꍇ�F <BR>
     *    ��������������� " and �������敪 = ? "��ǉ����� <BR>
     *    ���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.�������敪 ��ǉ����� <BR>
     *<BR>
     *9) ���Ǘ��Ғ������Ɖ���������.���ϋ敪!=null�̏ꍇ�F <BR>
     *    ��������������� " and ���ϋ敪 = ? "��ǉ����� <BR>
     *    ���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.���ϋ敪�@@��ǉ����� <BR>
     *<BR>
     *10) ���Ǘ��Ғ������Ɖ���������.�ʉ݃R�[�h!=null�̏ꍇ�F <BR>
     *    ��������������� " and �ʉ݃R�[�h = ? "��ǉ����� <BR>
     *    ���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.�ʉ݃R�[�h�@@��ǉ����� <BR>
     *<BR>
     *11) ���Ǘ��Ғ������Ɖ���������.�I�y���[�^�R�[�h!=null�̏ꍇ�F<BR>
     *    �Ǘ��Ғ������Ɖ�f�[�^�}�l�[�W��.get���҈ꗗ(�،����, ���X�R�[�h, <BR>
     *���҃R�[�h)�ň��҈ꗗ���擾�B<BR>
     *    (�Y�����鈵�҂�������Ȃ��ꍇ�A�G���[�u���҂����݂��Ȃ��B�v���X���[����)<BR>
     * �@@�@@class    : WEB3BusinessLayerException<BR>
     * �@@�@@tag      : BUSINESS_ERROR_01191<BR>
     *    [����]<BR>
     *�@@�@@�@@�،���ЁF�Ǘ���.�،����<BR>
     *�@@�@@�@@���X�R�[�h�F���Ǘ��Ғ������Ɖ���������.���X�R�[�h<BR>
     *�@@�@@�@@���҃R�[�h�F���Ǘ��Ғ������Ɖ���������.�I�y���[�^�R�[�h<BR>
     *    ��������������� " and �����ID in (?, ?,,,) "��ǉ�����<BR>
     *    ���������f�[�^�Ɂ@@��L�ɑΉ����鈵��.����ID�@@��ǉ�����<BR>
     *<BR>
     *12) ���Ǘ��Ғ������Ɖ���������.���҃R�[�h(SONAR)!=null�̏ꍇ�F <BR>
     *    ��������������� " and ���҃R�[�h(SONAR) = ? "��ǉ����� <BR>
     *    ���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.���҃R�[�h(SONAR)�@@���ȉ��̏����ŕҏW���A�ǉ����� <BR>
     *    [���҃R�[�h(SONAR)��5�������̏ꍇ <BR>
     *    �i���҃R�[�h(SONAR).length < 5�j] <BR>
     *     �E�O�h0�h�l�ɕҏW�������҃R�[�h(SONAR) <BR>
     *    [��L�ȊO�̏ꍇ] <BR>
     *     �E���҃R�[�h(SONAR) <BR>
     *<BR>
     *13) ���Ǘ��Ғ������Ɖ���������.�Ǘ��҃R�[�h!=null�̏ꍇ�F <BR>
     *    ��������������� " and �Ǘ��҃R�[�h = ? "��ǉ����� <BR>
     *    ���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.�Ǘ��҃R�[�h�@@��ǉ����� <BR>
     *<BR>
     *14�j ���Ǘ��Ғ������Ɖ���������.���^�C�v!=null�̏ꍇ�F <BR>
     *    ��������������� " and ���^�C�v = ? "��ǉ����� <BR>
     *    ���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.���^�C�v�@@��ǉ����� <BR>
     *<BR>
     *15) ��������������ƌ��������f�[�^�����������R���e�i�ɐݒ肵�āA�Ԃ� <BR> 
     * @@param l_conditionInfo - (���Ǘ��Ғ������Ɖ���������)<BR>
     * ���Ǘ��Ғ������Ɖ���������<BR>
     * <BR>
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ���<BR>
     * @@return webbroker3.adminorderexecinquiry.message.WEB3AdminBondQueryContainer
     * @@throws WEB3BaseException    
     * @@throws NotFoundException 
     * @@roseuid 44BB23BB03BC
     */
    protected WEB3AdminBondQueryContainer createQueryContainer(
        WEB3AdminORBondExecRefConditionInfo l_conditionInfo,
        WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createQueryContainer(WEB3AdminBondExecRefConditionInfo l_conditionInfo, WEB3Asministrator l_admin)";
        log.entering(STR_METHOD_NAME);
        
        if (l_conditionInfo == null)
        {
        	log.debug(STR_METHOD_NAME + "�p�����[�^�l�s���B");
        	log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3Administrator.class +"." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        StringBuffer l_sbQueryString = new StringBuffer();
        List l_lisQueryStringDate = new ArrayList();
        
        Institution l_institution = l_admin.getInstitution();
        
        //1) ���X��������������������ɒǉ�����B 
        //�@@�@@���Ǘ��Ғ������Ɖ���������.���X�R�[�h�̗v�f�����A�g���A�J�E���g�}�l�[�W�����畔�X���擾�B 
        //�@@�@@(�Y�����镔�X��񂪌�����Ȃ��ꍇ�A�G���[�u�Y�����X�f�[�^�Ȃ��v�G���[���X���[����) 
        //    ��������������� " ���XID in (?, ?,,,) "��ǉ����� 
        //    ���������f�[�^�Ɂ@@��L�ɑΉ����镔�X.���XID�@@��ǉ����� 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        int l_intLength = 0;
        if (l_conditionInfo.branchCode != null && l_conditionInfo.branchCode.length > 0)
        {
            l_intLength = l_conditionInfo.branchCode.length;
            
            String l_strTempBranchId = " branch_id in (";
            Branch l_branch = null;
            for (int i = 0; i < l_intLength; i++ )
            {
                try
                {
                    l_branch = l_accountManager.getBranch(l_institution, l_conditionInfo.branchCode[i]);
                } 
                catch (NotFoundException l_ex)
                {
                    log.error("�Y�����X�f�[�^�Ȃ�", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                l_strTempBranchId += "?, ";
                l_lisQueryStringDate.add(new Long(l_branch.getBranchId()));
            }
            
            l_strTempBranchId = l_strTempBranchId.substring(0, l_strTempBranchId.length() - 2) + ") ";
            
            l_sbQueryString.append(l_strTempBranchId);
        }

        //2)���Ǘ��Ғ������Ɖ���������.����ID!=null�̏ꍇ�F
        //������������� " and ����ID = ? "��ǉ����� 
        //���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.����ID�@@��ǉ����� 
        if (l_conditionInfo.id != null)
        {
            l_sbQueryString.append(" and order_id = ?");
            l_lisQueryStringDate.add(l_conditionInfo.id);
        }
     
        //3) ���Ǘ��Ғ������Ɖ���������.�����R�[�h�iWEB3�j!=null�̏ꍇ�F
        //�����R�[�h�iWEB3�j�ō������}�l�[�W��������������擾�B(�Y�����������
        //��������Ȃ��ꍇ�A�G���[�u�w�肵�������R�[�h�ɍ��v���Ă�����������݂��܂���B�v���X���[����) 
        //��������������� " and ����ID = ? "��ǉ����� 
        //���������f�[�^�Ɂ@@������.����ID�@@��ǉ����� 
        if (l_conditionInfo.productCode != null)
        {
            WEB3BondProductManager l_productManager = 
                (WEB3BondProductManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();
            WEB3BondProduct l_bondProduct = null;
            try
            {
                l_bondProduct = (WEB3BondProduct) l_productManager.getBondProduct(
                    l_institution, l_conditionInfo.productCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�w�肵�������R�[�h�ɍ��v���Ă�����������݂��܂���B ",l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            l_sbQueryString.append(" and product_id = ?");
            l_lisQueryStringDate.add(new Long(l_bondProduct.getProductId())); 
        }
        
        //4) ���Ǘ��Ғ������Ɖ���������.�ڋq�R�[�h!=null�̏ꍇ�F 
        //    �Ǘ��Ғ������Ɖ�f�[�^�}�l�[�W��.get�ڋq�ꗗ(�،���ЃR�[�h, ���X�R�[�h, �ڋq�R�[�h)�Ōڋq�ꗗ���擾�B 
        //    (�Y������ڋq��������Ȃ��ꍇ�A�G���[�u�Y������ڋq�����݂��܂���B�v���X���[����) 
        //    [����] 
        //�@@�@@�@@�،���ЃR�[�h�F�Ǘ���.�،���ЃR�[�h 
        //�@@�@@�@@���X�R�[�h�F���Ǘ��Ғ������Ɖ���������.���X�R�[�h 
        //�@@�@@�@@�ڋq�R�[�h�F���Ǘ��Ғ������Ɖ���������.�ڋq�R�[�h 
        //    ��������������� " and ����ID in (?, ?,,,) "��ǉ����� 
        //    ���������f�[�^�Ɂ@@��L�ɑΉ�����ڋq.����ID�@@��ǉ����� 
        WEB3AdminOrderExecuteDataManager l_orderExecuteDataManager =
            new WEB3AdminOrderExecuteDataManager();
        if (l_conditionInfo.accountCode != null)
        {
            WEB3GentradeMainAccount[] l_gentradeMainAccount = null;
            try
            {
                l_gentradeMainAccount =l_orderExecuteDataManager.getAccountList(
                    l_institution.getInstitutionCode(),
                    l_conditionInfo.branchCode,
                    l_conditionInfo.accountCode);                
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("�Y������ڋq�����݂��܂���", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            if (l_gentradeMainAccount != null && l_gentradeMainAccount.length > 0)
            {
                int l_intCnt = l_gentradeMainAccount.length;
                
                String l_strTempAccountId = " and account_id in (";
                for (int i = 0; i < l_intCnt; i++)
                {
                    long l_lngAccountId = l_gentradeMainAccount[i].getAccountId();

                    l_strTempAccountId += "?, ";
                    l_lisQueryStringDate.add(new Long(l_lngAccountId));
                }
                
                l_strTempAccountId = l_strTempAccountId.substring(0, l_strTempAccountId.length() - 2) + ") ";
                l_sbQueryString.append(l_strTempAccountId);
            }
        }    
        
        //5) ���Ǘ��Ғ������Ɖ���������.������!=null�̏ꍇ�F
        //��������������� " and ������ = ? "��ǉ����� 
        //���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.�������@@��ǉ�����
        if (l_conditionInfo.orderBizDate != null)
        {
            l_sbQueryString.append(" and biz_date = ?");
            String l_strBizDate = WEB3DateUtility.formatDate(l_conditionInfo.orderBizDate, "yyyyMMdd"); 
            l_lisQueryStringDate.add(l_strBizDate);
        }
        
        //6) ���Ǘ��Ғ������Ɖ���������.����!=null�̏ꍇ�F 
        //��������������� " and ���� = ? "��ǉ����� 
        //���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.�����@@��ǉ�����
        if (l_conditionInfo.executionUpdateDate != null)
        {
            l_sbQueryString.append(" and exec_date = ?");
            l_lisQueryStringDate.add(l_conditionInfo.executionUpdateDate);
        }
        
       // 7) ���Ǘ��Ғ������Ɖ���������.�������!=null�̏ꍇ�F 
       //[������ʁ������t�@@���́@@������ʁ������p�@@�̏ꍇ] 
       // �@@�@@��������������� " and ������� = ? "��ǉ����� 
       // �@@�@@���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.������ʁ@@��ǉ����� 
       //     ��������������� " and ��� = ? "��ǉ����� 
       //   �@@���������f�[�^�Ɂ@@�����d�؎���@@��ǉ�����
       //[������ʁ�������@@�̏ꍇ] 
       // �@@�@@��������������� " and (�i������� = ? "��ǉ����� 
       // �@@�@@���������f�[�^�Ɂ@@�����t�@@��ǉ����� 
       // �@@�@@��������������� " and ��� = ?�j "��ǉ����� 
       // �@@�@@���������f�[�^�Ɂ@@��W����@@��ǉ�����
       //     ��������������� " or ������� = ? )"��ǉ����� 
       //�@@   ���������f�[�^�Ɂ@@���������咍���@@��ǉ����� 
        if (l_conditionInfo.tradingType != null)
        {
            if (WEB3BondTradingTypeListDef.BUY.equals(l_conditionInfo.tradingType) ||
        		WEB3BondTradingTypeListDef.SELL.equals(l_conditionInfo.tradingType))
            {
                l_sbQueryString.append(" and order_type = ?");
                l_lisQueryStringDate.add(l_conditionInfo.tradingType);
                l_sbQueryString.append(" and deal_type = ?");
                l_lisQueryStringDate.add(WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING);
            }
            if (WEB3BondTradingTypeListDef.RECRUIT.equals(l_conditionInfo.tradingType))
            {
                l_sbQueryString.append(" and ((order_type = ? ");
                l_lisQueryStringDate.add(WEB3BondTradingTypeListDef.BUY);
                l_sbQueryString.append(" and deal_type = ?) ");
                l_lisQueryStringDate.add(WEB3DealTypeDef.RECRUIT_TRADING);
                l_sbQueryString.append(" or order_type = ? )");
                l_lisQueryStringDate.add(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            }
        }
        
        
        //8) ���Ǘ��Ғ������Ɖ���������.�������敪!=null�̏ꍇ�F 
        //��������������� " and �������敪 = ? "��ǉ����� 
        //���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.�������敪 ��ǉ����� 
        if (l_conditionInfo.executionState != null)
        {
            l_sbQueryString.append(" and order_exec_status = ?");
            l_lisQueryStringDate.add(l_conditionInfo.executionState);
        }
        
        //9) ���Ǘ��Ғ������Ɖ���������.���ϋ敪!=null�̏ꍇ�F 
        //��������������� " and ���ϋ敪 = ? "��ǉ����� 
        //���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.���ϋ敪�@@��ǉ�����
        if (l_conditionInfo.settleDiv != null)
        {
            l_sbQueryString.append(" and settlement_div = ?");
            l_lisQueryStringDate.add(l_conditionInfo.settleDiv);
        }
        
        //10) ���Ǘ��Ғ������Ɖ���������.�ʉ݃R�[�h!=null�̏ꍇ�F 
        //��������������� " and �ʉ݃R�[�h = ? "��ǉ����� 
        //���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.�ʉ݃R�[�h�@@��ǉ�����
        if (l_conditionInfo.currencyCode != null)
        {
            l_sbQueryString.append(" and currency_code = ?");
            l_lisQueryStringDate.add(l_conditionInfo.currencyCode);
        }
        
        //11) ���Ǘ��Ғ������Ɖ���������.�I�y���[�^�R�[�h!=null�̏ꍇ�F
        //    �Ǘ��Ғ������Ɖ�f�[�^�}�l�[�W��.get���҈ꗗ(�،����, ���X�R�[�h, 
        //���҃R�[�h)�ň��҈ꗗ���擾�B
        //    (�Y�����鈵�҂�������Ȃ��ꍇ�A�G���[�u���҂����݂��Ȃ��B�v���X���[����)
        //    [����]
        //�@@�@@�@@�،���ЁF�Ǘ���.�،����
        //�@@�@@�@@���X�R�[�h�F���Ǘ��Ғ������Ɖ���������.���X�R�[�h
        //�@@�@@�@@���҃R�[�h�F���Ǘ��Ғ������Ɖ���������.�I�y���[�^�R�[�h
        //    ��������������� " and �����ID in (?, ?,,,) "��ǉ�����
        //    ���������f�[�^�Ɂ@@��L�ɑΉ����鈵��.����ID�@@��ǉ�����
        if (l_conditionInfo.operatorCode != null)
        {
            WEB3GentradeTrader[] l_gentradeTrader = null; 
            try
            {
                l_gentradeTrader = l_orderExecuteDataManager.getTraderList(
                    l_institution, 
                    l_conditionInfo.branchCode,
                    l_conditionInfo.operatorCode);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("���҂����݂��Ȃ��B ",l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01191,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            if (l_gentradeTrader != null && l_gentradeTrader.length > 0)
            {
                String l_strTempTraderId = " and trader_id in(";
                l_intLength = l_gentradeTrader.length;
                for (int i = 0; i < l_intLength; i++)
                {
                    l_strTempTraderId += "?, "; 
                    l_lisQueryStringDate.add(new Long(l_gentradeTrader[i].getTraderId()));
                }
                l_strTempTraderId = l_strTempTraderId.substring(0, l_strTempTraderId.length() - 2) + ") ";
                l_sbQueryString.append(l_strTempTraderId);
            }
        }
        
        //12) ���Ǘ��Ғ������Ɖ���������.���҃R�[�h(SONAR)!=null�̏ꍇ�F 
        //��������������� " and ���҃R�[�h(SONAR) = ? "��ǉ����� 
        //���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.���҃R�[�h(SONAR)�@@���ȉ��̏����ŕҏW���A�ǉ�����
        //[���҃R�[�h(SONAR)��5�������̏ꍇ
        //�i���҃R�[�h(SONAR).length < 5�j]
        // �E�O�h0�h�l�ɕҏW�������҃R�[�h(SONAR)
        //[��L�ȊO�̏ꍇ]
        // �E���҃R�[�h(SONAR)
        if (l_conditionInfo.sonarTraderCode != null)
        {
            l_sbQueryString.append(" and sonar_trader_code = ?");
            
            int l_intSonarTraderCodeLen = 5;
            if (l_conditionInfo.sonarTraderCode.length() < l_intSonarTraderCodeLen)
            {
                StringBuffer l_strSonarTraderCodeFillZero = new StringBuffer();
                for (int i = l_conditionInfo.sonarTraderCode.length(); i < l_intSonarTraderCodeLen; i++)
                {
                    l_strSonarTraderCodeFillZero.append("0");
                }
                l_lisQueryStringDate.add(l_strSonarTraderCodeFillZero + l_conditionInfo.sonarTraderCode);
            }
            else
            {
                l_lisQueryStringDate.add(l_conditionInfo.sonarTraderCode);
            }
        }

        //13) ���Ǘ��Ғ������Ɖ���������.�Ǘ��҃R�[�h!=null�̏ꍇ�F 
        //��������������� " and �Ǘ��҃R�[�h = ? "��ǉ����� 
        //���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.�Ǘ��҃R�[�h�@@��ǉ�����
        if (l_conditionInfo.administratorCode != null)
        {
            l_sbQueryString.append(" and administrator_code = ?");
            l_lisQueryStringDate.add(l_conditionInfo.administratorCode);
        }
        
        //14�j ���Ǘ��Ғ������Ɖ���������.���^�C�v!=null�̏ꍇ�F
        //    ��������������� " and ���^�C�v = ? "��ǉ�����
        //    ���������f�[�^�Ɂ@@���Ǘ��Ғ������Ɖ���������.���^�C�v�@@��ǉ�����
        if(l_conditionInfo.bondType != null)
        {
            l_sbQueryString.append(" and bond_type = ?");
            l_lisQueryStringDate.add(l_conditionInfo.bondType);
        }
        //15) ��������������ƌ��������f�[�^�����������R���e�i�ɐݒ肵�āA�Ԃ�
        WEB3AdminBondQueryContainer l_adminBondQueryContainer = new WEB3AdminBondQueryContainer();
        String l_strQueryString = l_sbQueryString.toString();
        Object[] l_objQueryStringData = new Object[l_lisQueryStringDate.size()];
        l_lisQueryStringDate.toArray(l_objQueryStringData);
        l_adminBondQueryContainer.setQueryString(l_strQueryString);
        l_adminBondQueryContainer.setQueryData(l_objQueryStringData);
        
        log.exiting(STR_METHOD_NAME);
        return l_adminBondQueryContainer;
    }
}
@
