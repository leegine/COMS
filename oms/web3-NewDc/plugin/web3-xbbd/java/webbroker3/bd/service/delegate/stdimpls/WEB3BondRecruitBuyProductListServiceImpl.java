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
filename	WEB3BondRecruitBuyProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t�����ꗗ�T�[�r�XImpl(WEB3BondRecruitBuyProductListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/5 �s�p (���u) �V�K�쐬 
Revesion History : 2007/07/10 ���n�m (���u) ���f��No.194
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.bd.define.WEB3BondProductSortKeyItemDef;
import webbroker3.bd.define.WEB3BondReferenceTypeDef;
import webbroker3.bd.message.WEB3BondApplyBuyProductInfo;
import webbroker3.bd.message.WEB3BondApplyBuyProductListRequest;
import webbroker3.bd.message.WEB3BondApplyBuyProductListResponse;
import webbroker3.bd.message.WEB3BondCurrencyInfo;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyProductListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondTradeTypeDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.define.WEB3GentradeCurrencyCodeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (������/���t�����ꗗ�T�[�r�XImpl)<BR>
 * ������/���t�����ꗗ�T�[�r�XImpl
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3BondRecruitBuyProductListServiceImpl 
    extends WEB3BondClientRequestService implements WEB3BondRecruitBuyProductListService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondRecruitBuyProductListServiceImpl.class);   
    
    /**
     * @@roseuid 44FBFD3A00BB
     */
    public WEB3BondRecruitBuyProductListServiceImpl() 
    {
     
    }
    
    /**
     * ������/���t�����ꗗ�T�[�r�X���������{����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(��)����/���t�����ꗗ�v�Q�ƁB
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B7410C020B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (!(l_request instanceof WEB3BondApplyBuyProductListRequest))
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        WEB3BondApplyBuyProductListRequest l_listRequest = (WEB3BondApplyBuyProductListRequest) l_request;
        
        //1.1: validate( )
        l_listRequest.validate();
        
        //1.2: validate������t�\( )  
        WEB3BondTradingTimeManagement.validateOrderAccept();
        
        //1.3: get�⏕����( )
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.4:create��������������(String, String)
        String l_strQueryString = this.createQueryString(
            l_listRequest.referenceType, 
            l_listRequest.currencyCode);

        //1.5: create���������f�[�^�R���e�i(String, String)
        Object[] l_objsQueryContainer = this.createQueryDataContainer(
            l_listRequest.referenceType,
            l_listRequest.currencyCode);
        
        //1.6: create�\�[�g����������( )
        String l_strSortCond = this.createSortCond();
        
        //1.7: get���������X�g(String, String, Object[], String)
        FinApp l_finapp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finapp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productMgr = 
            (WEB3BondProductManager) l_tradingModule.getProductManager();

        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();

        List l_lisProducts = l_productMgr.getBondProductList(
            l_strInstitutionCode,
            l_strQueryString,
            l_objsQueryContainer,
            l_strSortCond);
        
        //1.8: get���������X�g()�̖߂�l�̗v�f����Loop����
        int l_intLoopCnt = 0;
        if (l_lisProducts != null && !l_lisProducts.isEmpty())
        {
            l_intLoopCnt = l_lisProducts.size();
        }
        
        List l_lisCurrencyInfos = new ArrayList();
        List l_lisCurrencyCodes = new ArrayList();
        for (int i = 0; i < l_intLoopCnt; i++)
        {
            WEB3BondProduct l_product = (WEB3BondProduct) l_productMgr.toProduct(
                (BondProductRow) l_lisProducts.get(i));
            
            //1.8.1: is�O�݌�( )
            boolean l_blnIsForeignCurrency = l_product.isForeignCurrency();
            
            //1.8.2: �����򏈗���is�O�݌�()�̖߂�l == true �̏ꍇ
            if (l_blnIsForeignCurrency)
            {
                //1.8.2.1: get�ʉ݃R�[�h( )
                String l_strCurrencyCode = l_product.getCurrencyCode();
                
                boolean l_blnIsDeal = false;
                if (i != 0)
                {
                    if (!l_lisCurrencyCodes.contains(l_strCurrencyCode))
                    {
                        l_lisCurrencyCodes.add(l_strCurrencyCode);
                        l_blnIsDeal = true;
                    }
                }
                else
                {
                    l_lisCurrencyCodes.add(l_strCurrencyCode);
                    l_blnIsDeal = true;
                }
                
                //1.8.2.2: get�ʉ݃R�[�h()�̖߂�l���A�쐬�����ʉݏ��.�ʉ݃R�[�h�Əd�����Ȃ��ꍇ
                if (l_blnIsDeal)
                {
                    //1.8.2.2.1: �i���ʁj�ʉ�(�،���ЃR�[�h : String, �ʉ݃R�[�h : String)
                    WEB3GentradeCurrency l_currency = WEB3GentradeCurrency.genCurrency(
                        l_strInstitutionCode, 
                        l_strCurrencyCode);
                    
                    
                    //1.8.2.2.2: get�בփ��[�g(is���t : boolean, is���v�Z : boolean, ���͈בփ��[�g : double)
                    double l_dblExchangeRate = l_currency.getExchangeRate(true, false, 0);
                    
                    //1.8.2.2.3:�ʉݏ��( )
                    WEB3BondCurrencyInfo l_currencyInfo = new WEB3BondCurrencyInfo();
                    
                    //1.8.2.2.4: �v���p�e�B�E�Z�b�g
                    l_currencyInfo.currencyCode = l_currency.getCurrencyCode();
                    l_currencyInfo.fxRate = WEB3StringTypeUtility.formatNumber(l_dblExchangeRate);
                    
                    l_lisCurrencyInfos.add(l_currencyInfo);                    
                }                
            }            
        }
        
        //1.9: WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisProducts,
            Integer.parseInt(l_listRequest.pageIndex),
            Integer.parseInt(l_listRequest.pageSize));
        
        //1.10: getArrayReturned(l_classType : Class)
        BondProductRow[] l_productRows = 
            (BondProductRow[]) l_pageIndexInfo.getArrayReturned(BondProductRow.class);
        
        //1.11: getArrayReturned()�̖߂�l�̗v�f����Loop����
        int l_intReturnCnt = 0;
        WEB3BondApplyBuyProductInfo[] l_applyBuyProductInfos = null;
        if (l_productRows != null && l_productRows.length > 0)
        {
            l_intReturnCnt = l_productRows.length;
            l_applyBuyProductInfos = new WEB3BondApplyBuyProductInfo[l_intReturnCnt];
        }
        
        for(int i = 0; i < l_intReturnCnt; i++)
        {
            BondProductRow l_productRow = l_productRows[i];
            
            //1.11.1: get�����敪( )
            String l_strTradeType = l_productRow.getTradeType();
            
            //1.11.2: ������/���t�������( )
            l_applyBuyProductInfos[i] = new WEB3BondApplyBuyProductInfo();
            
            //1.11.3: �v���p�e�B�E�Z�b�g
            //����ID     = ������.����ID
            l_applyBuyProductInfos[i].productId = 
                WEB3StringTypeUtility.formatNumber(l_productRow.getProductId());
            
            //������       = ������.������
            l_applyBuyProductInfos[i].productName = l_productRow.getProductName();
            
            //��ʃR�[�h     = ������.��ʃR�[�h
            l_applyBuyProductInfos[i].bondCategCode = l_productRow.getBondCategCode();
            
            //S&P       = ������.S&P
            l_applyBuyProductInfos[i].sAndP = l_productRow.getSAndP();
            
            //Moody's       = ������.MOODY'S
            l_applyBuyProductInfos[i].moodys = l_productRow.getMoodys();
            
            //����        = ������.����
            l_applyBuyProductInfos[i].coupon = 
                WEB3StringTypeUtility.formatNumber(l_productRow.getCoupon());
            
            //�N�ԗ�����    = ������.�N�ԗ�����
            l_applyBuyProductInfos[i].yearlyInterestPayments = 
                WEB3StringTypeUtility.formatNumber(l_productRow.getYearlyInterestPayments());
            
            //������1      = ������.������1
            l_applyBuyProductInfos[i].interestPaymentDay1 = l_productRow.getInterestPaymentDay1st();
            
            //������2      = ������.������2
            l_applyBuyProductInfos[i].interestPaymentDay2 = l_productRow.getInterestPaymentDay2nd();
            
            //�ʉ݃R�[�h     = ������.�ʉ݃R�[�h
            l_applyBuyProductInfos[i].currencyCode = l_productRow.getCurrencyCode();
            
            //�\���P��      = ������.�\���P��
            l_applyBuyProductInfos[i].tradeUnit = 
                WEB3StringTypeUtility.formatNumber(l_productRow.getTradeUnit());
            
            //�Œ�\������    = ������.�Œ�z��
            l_applyBuyProductInfos[i].minOrderQuantity = 
                WEB3StringTypeUtility.formatNumber(l_productRow.getMinFaceAmount());
            
            //�ō��\������    = ������.�ō��z��
            if (!l_productRow.getMaxFaceAmountIsNull())
            {
                l_applyBuyProductInfos[i].maxOrderQuantity = 
                    WEB3StringTypeUtility.formatNumber(l_productRow.getMaxFaceAmount());
            }
            
            //����J�n�� = ������.����J�n��(*1)
            //����I���� = ������.����I����(*1)
            //(*1)get�����敪()�̖߂�l == �h����h�̏ꍇ�̂݃Z�b�g����B����ȊO�̏ꍇ�Anull�B
            //����\�敪    = get�����敪()�̖߂�l == �h����hand ������.is����\() == true �̏ꍇ�A�h����h�i����\�敪�j
            //get�����敪()�̖߂�l == �h���t�h or �h���t/���p�h�̏ꍇ�A�h���t�h�i����\�敪�j
            if (WEB3BondTradeTypeDef.RECRUIT.equals(l_strTradeType))
            {
                l_applyBuyProductInfos[i].recruitStartDate = l_productRow.getRecruitStartDate();
                l_applyBuyProductInfos[i].recruitEndDate = l_productRow.getRecruitEndDate();

                WEB3BondProduct l_product = (WEB3BondProduct) l_productMgr.toProduct(
                        (BondProductRow) l_productRow);

                if (l_product.isRecruitPossible())
                {
                    l_applyBuyProductInfos[i].posibleDiv = WEB3BondDealDivDef.RECRUIT;
                }
            }
            else if (WEB3BondTradeTypeDef.BUY.equals(l_strTradeType) ||
                WEB3BondTradeTypeDef.BUY_SELL.equals(l_strTradeType))
            {
                l_applyBuyProductInfos[i].recruitStartDate = null;
                l_applyBuyProductInfos[i].recruitEndDate = null;
                l_applyBuyProductInfos[i].posibleDiv = WEB3BondDealDivDef.BUY;
            }  
            else
            {
                l_applyBuyProductInfos[i].recruitStartDate = null;
                l_applyBuyProductInfos[i].recruitEndDate = null;
            }
            
            //���t�P��      = ������.���t�P��
            if (!l_productRow.getBuyPriceIsNull())
            {
                l_applyBuyProductInfos[i].buyPrice = 
                    WEB3StringTypeUtility.formatNumber(l_productRow.getBuyPrice());
            }
            
            //���s��       = ������.���s��
            l_applyBuyProductInfos[i].issueDate = l_productRow.getIssueDate();
            
            //���ғ�       = ������.���ғ�
            l_applyBuyProductInfos[i].maturityDate = l_productRow.getMaturityDate();     
        }
        
        //1.12: getPageIndex( )
        String l_strPageIndex = 
            WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getPageIndex());
        
        //1.13: getTotalPages( )
        String l_strTotalPages = 
            WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getTotalPages());
        
        //1.14: getTotalRecords( )
        String l_strTotalRecords = 
            WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getTotalRecords());
        
        //1.15:createResponse( )
        WEB3BondApplyBuyProductListResponse l_response = 
            (WEB3BondApplyBuyProductListResponse) l_listRequest.createResponse();
        
        //1.16:�v���p�e�B�E�Z�b�g
        //�ʉݏ��ꗗ        = �ʉݏ��̔z��
        WEB3BondCurrencyInfo[] l_currencyInfos = null;
        if (!l_lisCurrencyInfos.isEmpty())
        {
            l_currencyInfos = new WEB3BondCurrencyInfo[l_lisCurrencyInfos.size()];
            l_lisCurrencyInfos.toArray(l_currencyInfos);
        }
        l_response.currencyList = l_currencyInfos;
        
        //����/���t���ꗗ     = ������/���t�������̔z��
        l_response.productList = l_applyBuyProductInfos;
        
        //�\���y�[�W�ԍ�       = getPageIndex()�̖߂�l
        l_response.pageIndex = l_strPageIndex;
        
        //���y�[�W��     = getTotalPages()�̖߂�l
        l_response.totalPages = l_strTotalPages;
        
        //�����R�[�h��        = getTotalRecords()�̖�
        l_response.totalRecords = l_strTotalRecords;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create��������������)<BR>
     * ����������������쐬����B <BR>
     * <BR>
     * (1)�ȉ��̕�������쐬����B<BR>
     * <BR>
     * �@@" �戵�敪 = ? and �戵�J�n���� <= ? and �戵�I������ > ?  and ���^�C�v = ? "<BR>
     * <BR>
     * (2)����.�Ɖ�敪 == �h����ꗗ�h�̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B <BR>
     * <BR>
     * �@@" and �����敪 = ? " <BR>
     * <BR>
     * (3)����.�Ɖ�敪 == �h���t�ꗗ�h �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B<BR>
     * <BR>
     * �@@" and �����敪 in (?, ?) " <BR>
     * <BR>
     * (4)����.�Ɖ�敪 == �h����/���t�ꗗ�h �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B<BR>
     * <BR>
     * �@@" and �����敪 in (?, ?, ?) " <BR>
     * <BR>
     * (5)����.�ʉ݃R�[�h �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B<BR>
     * <BR>
     * �@@(5-1)����.�ʉ݃R�[�h == "T0" �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@" and ( �ʉ݃R�[�h is null or �ʉ݃R�[�h = ? ) "<BR>
     * <BR>
     * �@@(5-2)����ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@" and �ʉ݃R�[�h = ? "<BR>
     * <BR>
     * (6)�쐬�����������ԋp����B<BR>
     * @@param l_strReferenceType - (�Ɖ�敪)<BR>
     * �Ɖ�敪
     * @@param l_strCurrencyCode - (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h
     * @@return String
     * @@roseuid 44B7669A00F2
     */
    protected String createQueryString(String l_strReferenceType, String l_strCurrencyCode) 
    {
        final String STR_METHOD_NAME = " createQueryString(String, String)";
        log.entering(STR_METHOD_NAME);
        
        String l_strReturn = "";
        
        //(1)�ȉ��̕�������쐬����B 
        //�@@" �戵�敪 = ? and �戵�J�n���� <= ? and �戵�I������ > ?  and ���^�C�v = ? " 
        l_strReturn = " trade_handle_div = ? and trade_start_date <= ? and trade_end_date > ? and bond_type = ? ";
        
        //(2)����.�Ɖ�敪 == �h����ꗗ�h�̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B  
        //�@@" and �����敪 = ? "  
        if (WEB3BondReferenceTypeDef.RECRUIT_LIST.equals(l_strReferenceType))
        {
            l_strReturn += " and trade_type = ?"; 
        }           
        //(3)����.�Ɖ�敪 == �h���t�ꗗ�h �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B  
        //�@@" and �����敪 in (?, ?) "  
        else if (WEB3BondReferenceTypeDef.BUY_LIST.equals(l_strReferenceType))
        {
            l_strReturn += " and trade_type in (?, ?)"; 
        }         
        //(4)����.�Ɖ�敪 == �h����/���t�ꗗ�h �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B 
        //�@@" and �����敪 in (?, ?, ?) "  
        else if (WEB3BondReferenceTypeDef.RECRUIT_BUY_LIST.equals(l_strReferenceType))
        {
            l_strReturn += " and trade_type in (?, ?, ?)"; 
        }
        
        //(5)����.�ʉ݃R�[�h �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B 
        if (l_strCurrencyCode != null)
        {
            //�@@(5-1)����.�ʉ݃R�[�h == "T0" �̏ꍇ 
            //�@@�@@�@@�@@" and ( �ʉ݃R�[�h is null or �ʉ݃R�[�h = ? ) " 
            if (WEB3GentradeCurrencyCodeDef.JPY.equals(l_strCurrencyCode))
            {
                l_strReturn += " and (currency_code is null or currency_code = ?)";
            }
            //�@@(5-2)����ȊO�̏ꍇ 
            //�@@�@@�@@�@@" and �ʉ݃R�[�h = ? " 
            else                
            {
                l_strReturn += " and currency_code = ?";
            }
        }

        //(6)�쐬�����������ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return l_strReturn + " ";
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^��Object�̔z��ɐݒ肵���X�|���X����B<BR>
     * <BR>
     * (1)Object�̔z����쐬����B<BR>
     * <BR>
     * (2)���ݓ������擾����B<BR>
     * <BR>
     * (3)(1)�ō쐬�����z��Ɉȉ����Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@"�Ǘ���/�ڋq"�i�戵�敪�j<BR>
     * �@@�@@�A(2)�Ŏ擾�������ݓ���<BR>
     * �@@�@@�B(2)�Ŏ擾�������ݓ���<BR>
     * �@@�@@�C"�O����"�i���^�C�v�j<BR>
     * <BR>
     * (3)����.�Ɖ�敪 == �h����ꗗ�h �̏ꍇ �A�z��Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@�@@�D"����"�i�����敪�j<BR>
     * <BR>
     * (4)����.�Ɖ�敪 == �h���t�ꗗ�h �̏ꍇ�A�z��Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@�@@�D"���t"(�����敪)<BR>
     * �@@�@@�E"���t/���p"�i�����敪�j<BR>
     * <BR>
     * (5)����.�Ɖ�敪 == �h����/���t�ꗗ�h �̏ꍇ �A�z��Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@�@@�D"����"�i�����敪�j<BR>
     * �@@�@@�E"���t"(�����敪)<BR>
     * �@@�@@�F"���t/���p"�i�����敪�j<BR>
     * <BR>
     * (5)����.�ʉ݃R�[�h �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@�@@����.�ʉ݃R�[�h<BR>
     * <BR>
     * (6)�쐬�����z���ԋp����B<BR>
     * @@param l_strReferenceType - (�Ɖ�敪)<BR>
     * �Ɖ�敪
     * @@param l_strCurrencyCode - (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h
     * @@return Object[]
     * @@roseuid 44BC4B3000AB
     */
    protected Object[] createQueryDataContainer(String l_strReferenceType, String l_strCurrencyCode) 
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //(1)Object�̔z����쐬����B 
        List l_lisContainer = new ArrayList();
        
        //(2)���ݓ������擾����B 
        Timestamp l_tsNewTime = GtlUtils.getSystemTimestamp();
        
        //(3)(1)�ō쐬�����z��Ɉȉ����Z�b�g����B 
        //�@@�@@�@@"�Ǘ���/�ڋq"�i�戵�敪�j 
        //�@@�@@�A(2)�Ŏ擾�������ݓ��� 
        //�@@�@@�B(2)�Ŏ擾�������ݓ��� 
        //�@@�@@�C"�O����"�i���^�C�v�j
        l_lisContainer.add(WEB3TradeHandleDivDef.MANAGER_CUSTOMER);
        l_lisContainer.add(l_tsNewTime);
        l_lisContainer.add(l_tsNewTime);
        l_lisContainer.add(BondTypeEnum.FOREIGN_BOND);
        
        //(3)����.�Ɖ�敪 == �h����ꗗ�h �̏ꍇ �A�z��Ɉȉ���ǉ�����B 
        //�@@�@@�D"����"�i�����敪�j 
        if (WEB3BondReferenceTypeDef.RECRUIT_LIST.equals(l_strReferenceType))
        {
            l_lisContainer.add(WEB3BondTradeTypeDef.RECRUIT);
        }        
        //(4)����.�Ɖ�敪 == �h���t�ꗗ�h �̏ꍇ�A�z��Ɉȉ���ǉ�����B 
        //
        //�@@�@@�D"���t"(�����敪) 
        //�@@�@@�E"���t/���p"�i�����敪�j 
        else if (WEB3BondReferenceTypeDef.BUY_LIST.equals(l_strReferenceType))
        {
            l_lisContainer.add(WEB3BondTradeTypeDef.BUY);
            l_lisContainer.add(WEB3BondTradeTypeDef.BUY_SELL);
        }        
        //(5)����.�Ɖ�敪 == �h����/���t�ꗗ�h �̏ꍇ �A�z��Ɉȉ���ǉ�����B 
        //�@@�@@�D"����"�i�����敪�j 
        //�@@�@@�E"���t"(�����敪) 
        //�@@�@@�F"���t/���p"�i�����敪�j 
        else if (WEB3BondReferenceTypeDef.RECRUIT_BUY_LIST.equals(l_strReferenceType))
        {
            l_lisContainer.add(WEB3BondTradeTypeDef.RECRUIT);
            l_lisContainer.add(WEB3BondTradeTypeDef.BUY);
            l_lisContainer.add(WEB3BondTradeTypeDef.BUY_SELL);
        } 
        //(5)����.�ʉ݃R�[�h �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B 
        //
        //�@@�@@����.�ʉ݃R�[�h 
        if (l_strCurrencyCode != null)
        {
            l_lisContainer.add(l_strCurrencyCode);
        }
            
        //(6)�쐬�����z���ԋp����B
        Object[] l_objReturns = new Object[l_lisContainer.size()];
        l_lisContainer.toArray(l_objReturns);
        
        log.exiting(STR_METHOD_NAME);
        return l_objReturns;
    }
    
    /**
     * (create�\�[�g����������)<BR>
     * �\�[�g������������쐬����B<BR>
     * <BR>
     * �ȉ��̏����Ń\�[�g������������쐬���A�쐬�����������ԋp����B<BR>
     * <BR>
     * [�\�[�g����]<BR>
     *  "��ʃR�[�h"�̏����A"�����R�[�h�iSONAR�j"�̏����A"�񍆃R�[�h(SONAR)"�̏���<BR>
     * <BR>
     * ���������̓����ږ��B
     * @@return String
     * @@roseuid 44BDB73F034B
     */
    protected String createSortCond() 
    {
        final String STR_METHOD_NAME = " createSortCond()";
        log.entering(STR_METHOD_NAME);
        
        //�ȉ��̏����Ń\�[�g������������쐬���A�쐬�����������ԋp����B 
        //[�\�[�g����] 
        // "��ʃR�[�h"�̏����A"�����R�[�h�iSONAR�j"�̏����A"�񍆃R�[�h(SONAR)"�̏��� 
        String l_strSortCond = " " + WEB3BondProductSortKeyItemDef.BOND_CATEG_CODE + " " + "asc" +
            ", " + WEB3BondProductSortKeyItemDef.HOST_PRODUCT_CODE + " " + "asc" + 
            ", " + WEB3BondProductSortKeyItemDef.HOST_PRODUCT_ISSUE_CODE + " " + "asc ";
        
        //���������̓����ږ��B    
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }
}
@
