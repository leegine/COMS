head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ������ꗗ�T�[�r�XImpl(WEB3AdminBondProductListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 �����q (���u) �V�K�쐬
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.WEB3AdminBondQueryContainer;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.define.WEB3BondProductSearchKeyItemDivDef;
import webbroker3.bd.message.WEB3AdminBondProductConditionUnit;
import webbroker3.bd.message.WEB3AdminBondProductListConditionInfo;
import webbroker3.bd.message.WEB3AdminBondProductSearchInputRequest;
import webbroker3.bd.message.WEB3AdminBondProductSearchInputResponse;
import webbroker3.bd.message.WEB3AdminBondProductSearchListRequest;
import webbroker3.bd.message.WEB3AdminBondProductSearchListResponse;
import webbroker3.bd.message.WEB3BondSortKey;
import webbroker3.bd.service.delegate.WEB3AdminBondProductListService;

/**
 * (�Ǘ��ҍ������ꗗ�T�[�r�XImpl)<BR>
 * �Ǘ��ҍ��@@�����ꗗ�T�[�r�X�����N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0 
 */
public class WEB3AdminBondProductListServiceImpl implements WEB3AdminBondProductListService 
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductListServiceImpl.class);
    
    /**
     * @@roseuid 44E3362E006D
     */
    public WEB3AdminBondProductListServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��ҍ������ꗗ�Ɖ�����{����B <BR>
     * <BR>
     * ���ݽ�}�u�i���j�����ꗗexecute�v�Q��<BR>
     * @@param l_request - ��菈���p���N�G�X�g�̊��N���X�B<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44BD81120218
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);  
        
        //1.execute(WEB3GenRequest)
        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        WEB3GenResponse l_response = null;
        
        //1.1 l_request�����Ǘ��Җ����ꗗ��ʕ\�����N�G�X�g�̏ꍇ
        // get�����ꗗ��������(�Ǘ��ҍ������ꗗ��ʕ\�����N�G�X�g)
        if (l_request instanceof WEB3AdminBondProductSearchInputRequest)
        {
            l_response = getProductSearchListCondition(
                (WEB3AdminBondProductSearchInputRequest) l_request);
        }
        
        //1.2 l_request�����Ǘ��Җ����ꗗ�������N�G�X�g
        //search�����ꗗ(�Ǘ��ҍ������ꗗ�������N�G�X�g)
        else if (l_request instanceof WEB3AdminBondProductSearchListRequest)
        {
            l_response = searchProductList(
                (WEB3AdminBondProductSearchListRequest) l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����ꗗ��������)<BR>
     * ���Ǘ��Җ����ꗗ���͏������s���B <BR>
     * <BR>
     * �V�[�P���X�}�u�i���jget�����ꗗ�v�Q��<BR>
     * --------------------------------------------------<BR>
     * @@param l_request - ���Ǘ��Җ����ꗗ��ʕ\�����N�G�X�g<BR>
     * @@return WEB3AdminBondProductSearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B600D103B1
     */
    protected WEB3AdminBondProductSearchInputResponse getProductSearchListCondition(
        WEB3AdminBondProductSearchInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getProductSearchListCondition(WEB3AdminBondProductSearchInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();     
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҃��̃O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        //1.2 validate����(String, boolean)
        //[validate����()�Ɏw�肷�����]  
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�������Ǘ� 
        // is�X�V�F�@@false 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE, false);

        //1.3 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.4 get�戵�\��ʃR�[�h�ꗗ(String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();

        String[] l_strHandlingPossibleTypeCodeList =
           l_bondProductManager.getTradeHandlingPossibleBondCategCodeList(l_strInstitutionCode);
        
        //1.5 get�ʉ݃R�[�h�ꗗ(�،���ЃR�[�h : String)
        String[] l_strCurrencyCodes = 
            WEB3GentradeCurrency.getCurrencyCodeList(l_strInstitutionCode);
        
        //1.6 create���X�|���X( )
        WEB3AdminBondProductSearchInputResponse l_response = 
            (WEB3AdminBondProductSearchInputResponse) l_request.createResponse();
        
        //1.7 �v���p�e�B�Z�b�g
        //��ʃR�[�h�ꗗ
        l_response.bondKindCodeList = l_strHandlingPossibleTypeCodeList;
            
        //�ʉ݃R�[�h�ꗗ
        l_response.currencyCodeList = l_strCurrencyCodes;
        
        //�戵�敪�ꗗ
        String[] l_strTradeHandleDivList = {
            WEB3TradeHandleDivDef.DISABLED,
            WEB3TradeHandleDivDef.MANAGER,
            WEB3TradeHandleDivDef.MANAGER_CUSTOMER};
        l_response.tradeHandleDivList = l_strTradeHandleDivList;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (search�����ꗗ)<BR>
     * ���Ǘ��Җ����ꗗ�����������s���B <BR>
     * <BR>
     * �V�[�P���X�}�u�i���jsearch�����ꗗ�v�Q��<BR>
     * --------------------------------------------------<BR>
     * @@param ��_request - ���Ǘ��Җ����ꗗ�������N�G�X�g<BR>
     * @@return WEB3AdminBondProductSearchListResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B600D50305
     */
    protected WEB3AdminBondProductSearchListResponse searchProductList(
        WEB3AdminBondProductSearchListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "searchProductList(WEB3AdminBondProductSearchListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҃��̃O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����]  
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�������Ǘ� 
        // is�X�V�F�@@false
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE, false);
        
        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5 create��������(�Ǘ��ҍ������ꗗ��������)
        WEB3AdminBondQueryContainer l_creatQuetyCond = 
            (WEB3AdminBondQueryContainer)createQueryContainer(l_request.conditionInfo);
               
        //1.6 create�\�[�g����(���\�[�g�L�[[])
        String l_strCreatSortCond = this.createSortCond(l_request.sortKeys);
        
        //1.7 get���������X�g(String, String, Object[], String)
        //[����] 
        // �،���ЃR�[�h�Fget�،���ЃR�[�h()�̖߂�l 
        //��������������F���������R���e�i.get��������������()�̖߂�l
        //���������f�[�^�R���e�i�F���������R���e�i.get�����f�[�^�z��()�̖߂�l
        // �\�[�g����������Fcreate�\�[�g����()�̖߂�l

        String l_strGetQueryString = l_creatQuetyCond.getQueryString();
        Object[] l_objGetQueryStringData = l_creatQuetyCond.getQueryData();
         
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_productManager = 
             (WEB3BondProductManager) l_finApp.getTradingModule(
                 ProductTypeEnum.BOND).getProductManager();
         
        List l_list =
            l_productManager.getBondProductList(
                l_strInstitutionCode, 
                l_strGetQueryString, 
                l_objGetQueryStringData,
                l_strCreatSortCond);
        
        //1.8 create�������Ɖ���ꗗ(�_���r���[::List)
        WEB3AdminBondProductConditionUnit[] l_bondProductReferenceInfoList =
            this.createBondProductReferenceInfoList(l_list);
        
        int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
        
        //1.9 create���X�|���X( )
        WEB3AdminBondProductSearchListResponse l_response =
            (WEB3AdminBondProductSearchListResponse) l_request.createResponse();
 
        //1.10 WEB3PageIndexInfo(l_objs : �_���r���[::java::lang::Object[], 
        //     l_intRequestPageIndex : int, l_intRequestPageSize : int)
        WEB3PageIndexInfo l_pageIndexInfo = 
            new WEB3PageIndexInfo(
                l_bondProductReferenceInfoList, 
                l_intRequestPageIndex, 
                l_intRequestPageSize);
       
        //1.11 getTotalPages( )
        int l_intGetTotalPages = l_pageIndexInfo.getTotalPages();
        
        //1.12 getTotalRecords( )
        int l_intGetTotalRecords = l_pageIndexInfo.getTotalRecords();
        
        //1.13 getPageIndex( )
        int l_intGetPageIndex = l_pageIndexInfo.getPageIndex();
        
        //1.14 getArrayReturned( )
        WEB3AdminBondProductConditionUnit[] l_productReferenceInfoList = 
            (WEB3AdminBondProductConditionUnit[])l_pageIndexInfo.getArrayReturned(
                WEB3AdminBondProductConditionUnit.class);

        //1.15 �v���p�e�B�Z�b�g
        //���y�[�W��
        l_response.totalPages = l_intGetTotalPages + "";        
       
        //�\���y�[�W�ԍ�
        l_response.pageIndex = l_intGetPageIndex + "";     
        
        //�����R�[�h��
        l_response.totalRecords = l_intGetTotalRecords + "";
        
        //�������Ɖ���ꗗ
        if (l_intGetTotalPages == 0)
        {
            l_response.conditionList = null;
        }
        else
        {
            l_response.conditionList = l_productReferenceInfoList;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�����𕶎���ϊ�����B <BR>
     * <BR>
     * <BR>
     * (1)����.�\�[�g�L�[.�L�[���ڂ̐����A�Ή�����e�[�u���̗񕨗�����<BR>
     * �@@�@@�@@�����^�~���w���t�����\�[�g������������쐬����B <BR>
     * <BR>
     * �@@�@@�E�L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ� <BR>
     * �@@�@@�@@�@@�@@�@@�E�����R�[�h�iWEB3�j�F�@@�������e�[�u���D�ʉ݃R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�A�������e�[�u���D�����R�[�h�iSONAR)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�B�������e�[�u���D�񍆃R�[�h�iSONAR)<BR>
     * �@@�@@�@@�@@�@@�@@�E���s���@@�@@�@@�@@�@@�@@�@@�F�������e�[�u���D���s�� <BR>
     * �@@�@@�@@�@@�@@�@@�E���ғ��@@�@@�@@�@@�@@�@@�@@�F�������e�[�u���D���ғ� <BR>
     * <BR>
     * �@@�@@�E�����^�~���w��́A�\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ� <BR>
     * <BR>
     * <BR>
     * (2)�쐬�����\�[�g�����������ԋp����B<BR>
     * <BR>
     * ���L�[���ڕ�����i�V���{�����j�́A���b�Z�[�W��`�����Q�� <BR>
     * ���e�[�u�����F�������e�[�u�� <BR>
     * ���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q�� <BR>
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * ���\�[�g�L�[�̔z��<BR>
     * @@throws throws WEB3BaseException
     * @@return String
     * @@roseuid 44C444F301C4
     */
    protected String createSortCond(WEB3BondSortKey[] l_sortKey) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createSortCond(WEB3BondSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);

        //(1)����.�\�[�g�L�[.�L�[���ڂ̐����A�Ή�����e�[�u���̗񕨗�����
        // �@@�@@�@@�����^�~���w���t�����\�[�g������������쐬����B 
        if (l_sortKey == null || l_sortKey.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�����w��(null)�ł��B");
        }

        StringBuffer l_sbStringBuffer = new StringBuffer();        
        int l_intCnt = l_sortKey.length;
        
        //�L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ� 
        //�����R�[�h�iWEB3�j�F�@@�������e�[�u���D�ʉ݃R�[�h
        //                   �A�������e�[�u���D�����R�[�h�iSONAR)
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@  �B�������e�[�u���D�񍆃R�[�h�iSONAR)
        //���s���@@�@@�@@�@@�@@�@@�F�������e�[�u���D���s�� 
        //���ғ��@@�@@�@@�@@�@@�@@�F�������e�[�u���D���ғ� 
        //�����^�~���w��́A�\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ� 
        for (int i = 0; i < l_intCnt; i++)
        {
            String l_strKeyItem = l_sortKey[i].keyItem;
            String l_strAscDesc = l_sortKey[i].ascDesc;
            
            if (WEB3BondProductSearchKeyItemDivDef.PRODUCT_CODE .equals(l_strKeyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
                {
                    l_sbStringBuffer.append("currency_code asc, ");
                    l_sbStringBuffer.append("host_product_code asc, ");
                    l_sbStringBuffer.append("host_product_issue_code asc, ");
                }
                else
                {
                    l_sbStringBuffer.append("currency_code desc, ");
                    l_sbStringBuffer.append("host_product_code desc, ");
                    l_sbStringBuffer.append("host_product_issue_code desc, ");
                }
            }
            else if (WEB3BondProductSearchKeyItemDivDef.ISSUE_DATE.equals(l_strKeyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
                {
                    l_sbStringBuffer.append("issue_date asc, ");
                }
                else
                {
                    l_sbStringBuffer.append("issue_date desc, ");
                }
            }
            else if (WEB3BondProductSearchKeyItemDivDef.MATURITY_DATE.equals(l_strKeyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
                {
                    l_sbStringBuffer.append("maturity_date asc, ");
                }
                else
                {
                    l_sbStringBuffer.append("maturity_date desc, ");
                }                    
            }
        }
        
        String l_strOrderBy = "";
        if (l_sbStringBuffer.length() > 0)
        {
            l_strOrderBy = l_sbStringBuffer.toString();
            l_strOrderBy = " " + l_strOrderBy.substring(0, l_strOrderBy.length() - 2) + " ";
        }
           
        log.exiting(STR_METHOD_NAME);
        return l_strOrderBy;     
    }
              
    /**
     * (create��������)<BR>
     * �������ꗗ�̌��������{����ׂ̌��������R���e�i���쐬���ĕԋp����B <BR>
     * <BR>
     * �P�j�����̍��^�C�v!=null�̏ꍇ�F<BR>
     * �@@��������������Ɂ@@" and ���^�C�v = ? "�@@��ǉ�����<BR>
     * �@@���������f�[�^�Ɂ@@�����̍��^�C�v�@@��ǉ�����<BR>
     * <BR>
     * �Q�j�����̎�ʃR�[�h!=null�̏ꍇ�F<BR>
     * �@@��������������Ɂ@@" and ��ʃR�[�h = ? " ��ǉ�����<BR>
     * �@@���������f�[�^�Ɂ@@�����̎�ʃR�[�h�@@��ǉ�����<BR>
     * <BR>
     * �R�j�����̖����R�[�h(WEB3)!=null�̏ꍇ�F<BR>
     * �@@��������������Ɂ@@" and�@@�����R�[�h(WEB3) = ? " ��ǉ�����<BR>
     * �@@���������f�[�^�Ɂ@@�����̖����R�[�h(WEB3) ��ǉ�����<BR>
     * <BR>
     * �S�j�����̔��s��!=null�̏ꍇ�F<BR>
     * �@@��������������Ɂ@@" and ���s�� = ?"�@@�ǉ�����<BR>
     * �@@���������f�[�^�Ɂ@@�����̔��s���@@��ǉ�����<BR>
     * <BR>
     * �T�j�����̏��ғ�!=null�̏ꍇ�F<BR>
     * �@@��������������Ɂ@@" and ���ғ� = ? "�@@�ǉ�����<BR>
     * �@@���������f�[�^�Ɂ@@�����̏��ғ��@@��ǉ�����<BR>
     * <BR>
     * �U�j�����̗�����!=null�̏ꍇ�F<BR>
     * �@@��������������Ɂ@@" and ( ������1 = ?�@@or ������2 = ? ) "�@@�ǉ�����<BR>
     * �@@���������f�[�^�Ɂ@@�����̗������̒l�@@���@@���@@�ǉ�����B<BR>
     * �@@(�������P�Ɨ�����2�̗������������邽��)<BR>
     * <BR>
     * �V�j�����̒ʉ݃R�[�h!=null�̏ꍇ�F<BR>
     * �@@��������������Ɂ@@" and �ʉ݃R�[�h =�@@? " ��ǉ�����<BR>
     * �@@���������f�[�^�Ɂ@@�����̒ʉ݃R�[�h �@@��ǉ�����<BR>
     * <BR>
     * �W�j�����̎戵�敪!=null�̏ꍇ�F<BR>
     * �@@��������������Ɂ@@" and �戵�敪 =�@@? "�@@�ǉ�����<BR>
     * �@@���������f�[�^�Ɂ@@�����̎戵�敪�@@��ǉ�����<BR>
     * <BR>
     * ��L�쐬������������������ƌ��������f�[�^�����������R���e�i�ɐݒ肵�A�ԋp����B<BR>
     * @@param l_bondProductListConditionInfo - (���Ǘ��Җ����ꗗ��������)<BR>
     * ���N�G�X�g�̍��Ǘ��Җ����ꗗ��������<BR>
     * @@return WEB3AdminBondQueryContainer
     * @@roseuid 44C6F0B100F7
     */
    protected WEB3AdminBondQueryContainer createQueryContainer(
        WEB3AdminBondProductListConditionInfo l_bondProductListConditionInfo) 
    {
        final String STR_METHOD_NAME = 
            "createQueryContainer(" +
            "WEB3AdminBondExecRefConditionInfo l_bondProductListConditionInfo)";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbQueryString = new StringBuffer();
        List l_lisQueryStringDate = new ArrayList();
        
        if (l_bondProductListConditionInfo != null)
        {
            //�P�j�����̍��^�C�v!=null�̏ꍇ�F
            if (l_bondProductListConditionInfo.bondType != null)
            {
                //��������������Ɂ@@" and ���^�C�v = ? "�@@��ǉ����� 
                l_sbQueryString.append(" and bond_type = ? ");
                //���������f�[�^�Ɂ@@�����̍��^�C�v�@@��ǉ����� 
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.bondType);
            }
            
            //�Q�j�����̎�ʃR�[�h!=null�̏ꍇ�F 
            //��������������Ɂ@@" and ��ʃR�[�h = ? " ��ǉ����� 
            //���������f�[�^�Ɂ@@�����̎�ʃR�[�h�@@��ǉ����� 
            if (l_bondProductListConditionInfo.bondCategCode != null)
            {
                l_sbQueryString.append(" and bond_categ_code = ? ");
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.bondCategCode);
            }
            
            //�R�j�����̖����R�[�h(WEB3)!=null�̏ꍇ�F 
            //��������������Ɂ@@" and�@@�����R�[�h(WEB3) = ? " ��ǉ����� 
            //���������f�[�^�Ɂ@@�����̖����R�[�h(WEB3) ��ǉ����� 
            if (l_bondProductListConditionInfo.productCode != null)
            {
                l_sbQueryString.append(" and product_code = ? ");
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.productCode);
            }
            
            //�S�j�����̔��s��!=null�̏ꍇ�F 
            //��������������Ɂ@@" and ���s�� = ?"�@@�ǉ����� 
            //���������f�[�^�Ɂ@@�����̔��s���@@��ǉ����� 
            if (l_bondProductListConditionInfo.issueDate != null)
            {
                l_sbQueryString.append(" and issue_date = ? ");
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.issueDate);
            }
            
            //�T�j�����̏��ғ�!=null�̏ꍇ�F 
            //��������������Ɂ@@" and ���ғ� = ? "�@@�ǉ����� 
            //���������f�[�^�Ɂ@@�����̏��ғ��@@��ǉ�����
            if (l_bondProductListConditionInfo.maturityDate != null)
            {
                l_sbQueryString.append(" and  maturity_date = ? ");
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.maturityDate);
            }  
            
            //�U�j�����̗�����!=null�̏ꍇ�F 
            //��������������Ɂ@@" and ( ������1 = ?�@@or ������2 = ? ) "�@@�ǉ����� 
            //���������f�[�^�Ɂ@@�����̗������̒l�@@���@@���@@�ǉ�����B 
            //(�������P�Ɨ�����2�̗������������邽��) 
            if (l_bondProductListConditionInfo.interestPaymentDay != null)
            {
                l_sbQueryString.append(" and (interest_payment_day_1st = ? or interest_payment_day_2nd = ?) ");
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.interestPaymentDay);
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.interestPaymentDay);
            }
            
            //�V�j�����̒ʉ݃R�[�h!=null�̏ꍇ�F 
            //��������������Ɂ@@" and �ʉ݃R�[�h =�@@? " ��ǉ����� 
            //���������f�[�^�Ɂ@@�����̒ʉ݃R�[�h �@@��ǉ����� 
            if (l_bondProductListConditionInfo.currencyCode != null)
            {
                l_sbQueryString.append(" and currency_code = ? ");
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.currencyCode);
            }
            
            //�W�j�����̎戵�敪!=null�̏ꍇ�F 
            //��������������Ɂ@@" and �戵�敪 =�@@? "�@@�ǉ����� 
            //���������f�[�^�Ɂ@@�����̎戵�敪�@@��ǉ����� 
            if (l_bondProductListConditionInfo.tradeHandleDiv != null)
            {
                l_sbQueryString.append(" and trade_handle_div = ? ");
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.tradeHandleDiv);
            }
        }
        
        //��L�쐬������������������ƌ��������f�[�^�����������R���e�i�ɐݒ肵�A�ԋp����B 
        WEB3AdminBondQueryContainer l_adminBondQueryContainer = 
            new WEB3AdminBondQueryContainer();
        
        //��������������
        String l_strQueryString = l_sbQueryString.toString();
        //���������R���e�i
        
        Object[] l_objQueryStringData = new Object[l_lisQueryStringDate.size()];
        l_lisQueryStringDate.toArray(l_objQueryStringData);
        l_adminBondQueryContainer.setQueryString(l_strQueryString);
        l_adminBondQueryContainer.setQueryData(l_objQueryStringData);
       
        log.exiting(STR_METHOD_NAME);
        return l_adminBondQueryContainer;
    }
    
    /**
     * (create�������Ɖ���ꗗ)<BR>
     * ����.���������X�g����������Ɖ���̔z����쐬�B<BR>
     * <BR>
     * �P�j����.���������X�g��Loop����B<BR>
     * �@@�P�|�P�j�������Ɖ���𐶐�����B<BR>
     * <BR>
     * �@@�P�|�Q�j�������s�̑������Z�b�g����B<BR>
     * �@@�@@�E�������s.�����R�[�h�iWEB3�j = ������.�����R�[�h�iWEB3�j<BR>
     * �@@�@@�E�������s.�戵������ = ������.������<BR>
     * �@@�@@�E�������s.HOST������1�@@= ������.HOST������1<BR>
     * �@@�@@�E�������s.�ʉ݃R�[�h = ������.�ʉ݃R�[�h<BR>
     * �@@�@@�E�������s.�戵�敪 = ������.�戵�敪<BR>
     * �@@�@@�E�������s.���s���i = ������.���s���i<BR>
     * �@@�@@�E�������s.���� = ������.����<BR>
     * �@@�@@�E�������s.���s�� = ������.���s��<BR>
     * �@@�@@�E�������s.���ғ� = ������.���ғ�<BR>
     * �@@�@@�E�������s.�N�ԗ����� = ������.�N�ԗ�����<BR>
     * �@@�@@�E�������s.������1 = ������.������1<BR>
     * �@@�@@�E�������s.������2 = ������.������2<BR>
     * �@@�@@�E�������s.��ʃR�[�h = ������.��ʃR�[�h<BR>
     * <BR>
     * �@@�P�|�R�j�z��ɒǉ�����B<BR>
     * <BR>
     * �Q�jLoop�I����A�������Ɖ���̔z���Ԃ��B<BR>
     * @@param l_lisBondProductList - (���������X�g)<BR>
     * ���������X�g<BR>
     * @@return WEB3AdminBondProductConditionUnit[]
     * @@roseuid 44D67A68029F
     */
    protected WEB3AdminBondProductConditionUnit[] createBondProductReferenceInfoList(
        List l_lisBondProductList) 
    {
        final String STR_METHOD_NAME = 
            "createBondProductReferenceInfoList(List l_lisBondProductList)";
        log.entering(STR_METHOD_NAME);
        
        int l_intSize = 0;
        if (l_lisBondProductList != null && !l_lisBondProductList.isEmpty())
        {
            l_intSize = l_lisBondProductList.size();
        }
        
        ArrayList l_listProductConditionUnit = new ArrayList();
        
        //�P�j����.���������X�g��Loop����B
        //�P�|�P�j�������Ɖ���𐶐�����B

        for (int i = 0; i < l_intSize; i++)
        {
            WEB3AdminBondProductConditionUnit l_bondProductConditionUnit = 
                new WEB3AdminBondProductConditionUnit();
            BondProductRow l_bondProductRow = 
                (BondProductRow)l_lisBondProductList.get(i);
            if(l_bondProductRow != null)
            {
                //�P�|�Q�j�������s�̑������Z�b�g����B
                //�������s.�����R�[�h�iWEB3�j = ������.�����R�[�h�iWEB3�j
                l_bondProductConditionUnit.productCode = 
                    l_bondProductRow.getProductCode();
                
                //�������s.�戵������ = ������.������
                l_bondProductConditionUnit.handlingProductName = 
                    l_bondProductRow.getProductName();
                
                //�������s.HOST������1�@@= ������.HOST������1
                l_bondProductConditionUnit.hostProductName1 = 
                    l_bondProductRow.getHostProductName1();
                
                //�������s.�ʉ݃R�[�h = ������.�ʉ݃R�[�h
                l_bondProductConditionUnit.currencyCode = 
                    l_bondProductRow.getCurrencyCode();
                
                //�������s.�戵�敪 = ������.�戵�敪
                l_bondProductConditionUnit.tradeHandleDiv = 
                    l_bondProductRow.getTradeHandleDiv();
                
                //�������s.���s���i = ������.���s���i
                l_bondProductConditionUnit.issuePrice = 
                    WEB3StringTypeUtility.formatNumber(
                        l_bondProductRow.getIssuePrice());
                
                //�������s.���� = ������.����
                l_bondProductConditionUnit.coupon = 
                    WEB3StringTypeUtility.formatNumber(
                        l_bondProductRow.getCoupon());
                
                //�������s.���s�� = ������.���s��
                l_bondProductConditionUnit.issueDate = 
                    l_bondProductRow.getIssueDate();
                
                //�������s.���ғ� = ������.���ғ�
                l_bondProductConditionUnit.maturityDate = 
                    l_bondProductRow.getMaturityDate();
                
                //�������s.�N�ԗ����� = ������.�N�ԗ�����
                l_bondProductConditionUnit.yearlyInterestPayments = 
                    l_bondProductRow.getYearlyInterestPayments() + "";
                
                //�������s.������1 = ������.������1
                l_bondProductConditionUnit.interestPaymentDay1 = 
                    l_bondProductRow.getInterestPaymentDay1st();
                
                //�������s.������2 = ������.������2
                l_bondProductConditionUnit.interestPaymentDay2 = 
                    l_bondProductRow.getInterestPaymentDay2nd();
                
                //�������s.��ʃR�[�h = ������.��ʃR�[�h
                l_bondProductConditionUnit.bondCategCode = 
                    l_bondProductRow.getBondCategCode();
                
                //�P�|�R�j�z��ɒǉ�����
                l_listProductConditionUnit.add(l_bondProductConditionUnit);
            }
        }
        
        WEB3AdminBondProductConditionUnit[] l_adminBondProductConditionUnits = 
            new WEB3AdminBondProductConditionUnit[l_listProductConditionUnit.size()];
        l_listProductConditionUnit.toArray(l_adminBondProductConditionUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_adminBondProductConditionUnits;  
    }
}
@
