head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3BVSBookValueSpecsServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �뉿�P�����׏Ɖ�T�[�r�XImpl(WEB3BVSBookValueSpecsServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/08  �Ɍ��t(���u) �V�K�쐬
*/
package webbroker3.tradehistory.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradehistory.WEB3HistoryTradeHistoryDataManager;
import webbroker3.tradehistory.data.BookValueSpecParams;
import webbroker3.tradehistory.message.WEB3BVSBookValueSpecsRequest;
import webbroker3.tradehistory.message.WEB3BVSBookValueSpecsResponse;
import webbroker3.tradehistory.message.WEB3BVSBookValueSpecsUnit;
import webbroker3.tradehistory.service.delegate.WEB3BVSBookValueSpecsService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.common.define.WEB3BookValueRecordDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;

/**
 * (�뉿�P�����׏Ɖ�T�[�r�XImpl)<BR>
 * �뉿�P�����׏Ɖ�T�[�r�X�����N���X<BR>
 * 
 * @@author �Ɍ��t
 * @@version 1.0 
 */
public class WEB3BVSBookValueSpecsServiceImpl extends WEB3ClientRequestService implements WEB3BVSBookValueSpecsService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3BVSBookValueSpecsServiceImpl.class);      
    
    /**
     * @@roseuid 418877BB0196
     */
    public WEB3BVSBookValueSpecsServiceImpl() 
    {
     
    }
    
    /**
     * �뉿�P�����׏Ɖ�����s���B<BR>
     * <BR>
     * this.get�뉿�P�����׏Ɖ�()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 416E4F390312
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null; 
        if(l_request instanceof WEB3BVSBookValueSpecsRequest)
        {
            
            l_response = this.getBookValueSpecs((WEB3BVSBookValueSpecsRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�뉿�P�����׏Ɖ�)<BR>
     * �뉿�P�����׏Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�뉿�P�����׏Ɖ�T�[�r�X)get�뉿�P�����׏Ɖ�v<BR>
     * ======================================================= <BR>
     *         �V�[�P���X�} :  �u(�뉿�P�����׏Ɖ�T�[�r�X)get�뉿�P�����׏Ɖ�v<BR>
     *         ��̈ʒu : 1.5 get�뉿�P�����׌���(�ڋq : �ڋq, ���i�R�[�h : String, �����R�[�h : String) <BR>
     *         get�뉿�P�����׌���()���\�b�h�̖߂�l == 0�̏ꍇ�A<BR>
     *         ���������������݃G���[�B<BR>
     *         class         :  WEB3BusinessLayerException<BR>
     *         tag            :  BUSINESS_ERROR_01081         <BR>
     * ======================================================= <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �뉿�P�����׏Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.plsbvs.message.WEB3BVSBookValueSpecsResponse
     * @@roseuid 416E4F4E0063
     */
    protected WEB3BVSBookValueSpecsResponse getBookValueSpecs(WEB3BVSBookValueSpecsRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getBookValueSpecs(WEB3BVSBookValueSpecsRequest l_request)";
        log.entering(STR_METHOD_NAME); 
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3 get�⏕����
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                
        //1.4 getMainAccount()
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //
        WEB3BVSBookValueSpecsResponse l_response = 
            (WEB3BVSBookValueSpecsResponse)l_request.createResponse();   
        //1.5 get�뉿�P�����׌���(�ڋq, String, String)
        WEB3HistoryTradeHistoryDataManager l_historyTradeHistoryDataManager = new WEB3HistoryTradeHistoryDataManager();
        int l_intBookValueSpecCount = l_historyTradeHistoryDataManager.getBookValueSpecCount(l_mainAccount, l_request.commodityCode, l_request.productCode);
        if (l_intBookValueSpecCount == 0)
        {
            l_response.productCode = l_request.productCode;
            l_response.productName = l_request.productName;
            l_response.currentProlossAmount = "0";
            l_response.totalPages = "1";
            l_response.totalRecords = "0";
            l_response.pageIndex = "1";
            l_response.bookValueUnits = null;
            return l_response;
        }
        
        //1.6 �擾�f�[�^�i�[�pArrayList�𐶐�����B
        List l_lisBookValueSpecs = new ArrayList();
        
        //1.7 create�c���f�[�^(�ڋq, String, String, String)
        WEB3BVSBookValueSpecsUnit l_web3BVSBookValueSpecsUnit = this.createBalanceData(l_mainAccount, l_request.commodityCode, l_request.productCode, l_request.displayTerm);
        
        //1.8 create�c���f�[�^()�̖߂�l != null�̏ꍇ  
        double l_dblSumBalanceProLoss = 0.0D;   
        if (l_web3BVSBookValueSpecsUnit != null)    
        {   
            // add(�c���f�[�^ : Object)
            l_lisBookValueSpecs.add(l_web3BVSBookValueSpecsUnit);
            l_dblSumBalanceProLoss = Double.parseDouble(l_web3BVSBookValueSpecsUnit.prolossAmount);  
        }   
    
        //1.10 get�뉿�P�����׈ꗗ(�ڋq, String, String, String, boolean)
        List l_lisBookValueSpecUnits = 
            l_historyTradeHistoryDataManager.getBookValueSpecList(
                l_mainAccount, 
                l_request.commodityCode, 
                l_request.productCode, 
                l_request.displayTerm, 
                false);       
        double l_dblsumproloss = 0;
        int l_intSize = 0;
        if (l_lisBookValueSpecUnits != null)
        {
            l_intSize = l_lisBookValueSpecUnits.size();
        }
        
        //1.11 (*)get�뉿�P�����׈ꗗ()�̖߂�l�̗v�f(=���v����Params)����Loop����
        for (int i = 0; i < l_intSize; i++)
        {
            //1.11.1 �뉿�P�����׏��()
            WEB3BVSBookValueSpecsUnit l_bookValueSpecsUnit = new WEB3BVSBookValueSpecsUnit();
            BookValueSpecParams l_bookValueSpecParams = (BookValueSpecParams)l_lisBookValueSpecUnits.get(i);
            if (l_bookValueSpecParams.getBookValueSpecIdIsSet())
            {
                l_bookValueSpecsUnit.bookValueSpecId = "" + l_bookValueSpecParams.getBookValueSpecId();
            }
            else
            {
                l_bookValueSpecsUnit.bookValueSpecId = null;
            }
            l_bookValueSpecsUnit.bookvalRecDiv = l_bookValueSpecParams.getRecDiv();
            l_bookValueSpecsUnit.calcDate = l_bookValueSpecParams.getCalcDate();
            l_bookValueSpecsUnit.deliveryDate = l_bookValueSpecParams.getDeliveryDate();
            l_bookValueSpecsUnit.buySellDiv = l_bookValueSpecParams.getBuySellDiv();
            l_bookValueSpecsUnit.tradeType = l_bookValueSpecParams.getTradeType();
            if (l_bookValueSpecParams.getQuantityIsNull())
            {
                l_bookValueSpecsUnit.quantity = null;
            }
            else
            {
                l_bookValueSpecsUnit.quantity = WEB3StringTypeUtility.formatNumber(l_bookValueSpecParams.getQuantity());
            }
            
            if (l_bookValueSpecParams.getPriceIsNull())
            {
                l_bookValueSpecsUnit.execPrice = null;
            }
            else
            {
                l_bookValueSpecsUnit.execPrice = WEB3StringTypeUtility.formatNumber(l_bookValueSpecParams.getPrice());
            }            
            l_bookValueSpecsUnit.ccyCode = l_bookValueSpecParams.getCcyCode();
            if (l_bookValueSpecParams.getNetAmountIsNull())
            {
                l_bookValueSpecsUnit.deliveryAmount = null;
            }
            else
            {
                l_bookValueSpecsUnit.deliveryAmount = WEB3StringTypeUtility.formatNumber(l_bookValueSpecParams.getNetAmount());
            }
            
            double l_dblproloss = l_bookValueSpecParams.getProloss1();
            String l_strtrade_type = l_bookValueSpecParams.getTradeType();
            String l_strbuy_sell = l_bookValueSpecParams.getBuySellDiv();
            if (!((l_dblproloss <= 0) && (
                (l_strbuy_sell.equals("2") && (l_strtrade_type.equals("11") || l_strtrade_type.equals("83") || l_strtrade_type.equals("92"))) ||
                (l_strbuy_sell.equals("2") && l_strtrade_type.equals("25")) ||
                (l_strbuy_sell.equals("2") && l_strtrade_type.equals("35")) ||
                (l_strbuy_sell.equals("2") && l_strtrade_type.equals("53")))))
            {
                if (l_bookValueSpecParams.getProloss1IsNull())
                {
                    l_bookValueSpecsUnit.prolossAmount = null;
                }
                else
                {
                    l_bookValueSpecsUnit.prolossAmount = WEB3StringTypeUtility.formatNumber(l_dblproloss);
                }
            }
            
            if (l_bookValueSpecParams.getCalcAmountIsNull())
            {
                l_bookValueSpecsUnit.calcAmount = null;
            }
            else
            {
                l_bookValueSpecsUnit.calcAmount = WEB3StringTypeUtility.formatNumber(l_bookValueSpecParams.getCalcAmount());
            }
            
            if (l_bookValueSpecParams.getBookAmountIsNull())
            {
                l_bookValueSpecsUnit.bookAmount = null;
            }
            else
            {
                l_bookValueSpecsUnit.bookAmount = WEB3StringTypeUtility.formatNumber(l_bookValueSpecParams.getBookAmount());
            }
            
            if (l_bookValueSpecParams.getBalQuantityIsNull())
            {
                
            }
            else
            {
                l_bookValueSpecsUnit.balQuantity = WEB3StringTypeUtility.formatNumber(l_bookValueSpecParams.getBalQuantity());
            }
            
            if (l_bookValueSpecParams.getBookValueIsNull())
            {
                l_bookValueSpecsUnit.bookPrice = null;
            }
            else
            {
                l_bookValueSpecsUnit.bookPrice = WEB3StringTypeUtility.formatNumber(l_bookValueSpecParams.getBookValue());
            }
            
            l_bookValueSpecsUnit.debitBalDiv = l_bookValueSpecParams.getDebitBalDiv();
            //1.11.3 add(���׃f�[�^ : Object)
            l_lisBookValueSpecs.add(l_bookValueSpecsUnit);
            //1.11.4 (*)���׃f�[�^�̑��v�̏W�v
            if (l_lisBookValueSpecs == null)
            {
                l_dblsumproloss = 0;
            }
            else
            {
                l_dblsumproloss += l_bookValueSpecParams.getProloss1();
            }            
        }

        //1.12 toArray()
        WEB3BVSBookValueSpecsUnit[] l_web3BVSBookValueSpecsUnits = new WEB3BVSBookValueSpecsUnit[l_lisBookValueSpecs.size()];
        l_lisBookValueSpecs.toArray(l_web3BVSBookValueSpecsUnits); 

        //1.13 createResponse       
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_web3BVSBookValueSpecsUnits, l_intPageIndex, l_intPageSize);
        WEB3BVSBookValueSpecsUnit[] l_returnSpecUnit = (WEB3BVSBookValueSpecsUnit[])l_pageIndexInfo.getArrayReturned(WEB3BVSBookValueSpecsUnit.class);

        //1.14 (*)�v���p�e�B�Z�b�g   
        l_response.productCode = l_request.productCode;
        l_response.productName = l_request.productName;
        l_response.currentProlossAmount = 
            WEB3StringTypeUtility.formatNumber(l_dblSumBalanceProLoss + l_dblsumproloss);       
        l_response.totalPages  = l_pageIndexInfo.getTotalPages() + "";
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";        
        l_response.bookValueUnits = l_returnSpecUnit;        

        log.exiting(STR_METHOD_NAME);  
        return l_response;
    }
    
    /**
     * (create�c���f�[�^)<BR>
     * �c���f�[�^���i�[�������v���׏��C���X�^���X��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�뉿�P�����׏Ɖ�T�[�r�X)create�c���f�[�^�v�Q��<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strCommodityCode - (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * <BR>
     * 10:�@@����<BR>
     * 11:�@@�M�p<BR>
     * 15:�@@�~�j��<BR>
     * 20:�@@���M<BR>
     * 21:�@@�O��<BR>
     * 22:�@@�ݓ�<BR>
     * 23:�@@MRF<BR>
     * 30:�@@��<BR>
     * 40:�@@�O��<BR>
     * 50:�@@����<BR>
     * 51�F ���w��OP<BR>
     * 52:�@@��<BR>
     * 53:�@@��OP<BR>
     * 54:�@@�XOP<BR>
     * 55:�@@�O��<BR>
     * 56:�@@�O��OP<BR>
     * 57:�@@��OP<BR>
     * 60:�@@�O��<BR>
     * 70:�@@��<BR>
     * 71:�@@��GP<BR>
     * 80:�@@����<BR>
     * 91:�@@CD<BR>
     * 92:�@@CP<BR>
     * 93:�@@BA<BR>
     * 99:�@@���K<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strDisplayTerm - (�\������)<BR>
     * �\������<BR>
     * <BR>
     * 0�F�@@�O�������ȍ~(DEFAULT)<BR>
     * 1�F�@@1������<BR>
     * 2�F�@@1�T�ԕ�<BR>
     * 3�F�@@�O��1����<BR>
     * @@return webbroker3.plsbvs.message.WEB3BVSBookValueSpecsUnit
     * @@roseuid 416E5EE803CE
     */
    protected WEB3BVSBookValueSpecsUnit createBalanceData(WEB3GentradeMainAccount l_mainAccount, String l_strCommodityCode, String l_strProductCode, String l_strDisplayTerm) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createBalanceData(WEB3GentradeMainAccount, String, String, String) ";
        log.entering(STR_METHOD_NAME);         
        
        //1.1 �뉿�P�����׏��()
        WEB3BVSBookValueSpecsUnit l_bookValueSpecsUnit = new WEB3BVSBookValueSpecsUnit();

        //1.2 get�ŏI�v�Z�N����from�뉿�P������(�ڋq, String, String, String)
        WEB3HistoryTradeHistoryDataManager l_historyTradeHistoryDataManager = new WEB3HistoryTradeHistoryDataManager();
        Date l_datFinalCalcDatefromBookValueSpec = 
            l_historyTradeHistoryDataManager.getFinalCalcDateFromBookValueSpec(
                l_mainAccount, 
                l_strCommodityCode, 
                l_strProductCode, 
                l_strDisplayTerm);

        //1.3 get�c�����R�[�hfrom�뉿�P������(�ڋq, String, String)
        List l_lisBalanceRecordfromBookValueSpec = 
            l_historyTradeHistoryDataManager.getBalanceRecordFromBookValueSpec(
                l_mainAccount, 
                l_strCommodityCode, 
                l_strProductCode);

        //1.4 get�ŏI�v�Z�N����from�뉿�P������()�̖߂�l == null�̏ꍇ
        if (l_datFinalCalcDatefromBookValueSpec == null)
        {
            if (l_lisBalanceRecordfromBookValueSpec != null)
            {
                //1.4.1 (*)�v���p�e�B�Z�b�g
                if (((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(0)).getBalQuantityIsNull())
                {
                    l_bookValueSpecsUnit.balQuantity = null;
                }
                else
                {
                    l_bookValueSpecsUnit.balQuantity = WEB3StringTypeUtility.formatNumber(((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(0)).getBalQuantity());
                }
                
                if (((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(0)).getBookValueIsNull())
                {
                    l_bookValueSpecsUnit.bookPrice = null;
                }
                else
                {
                    l_bookValueSpecsUnit.bookPrice = WEB3StringTypeUtility.formatNumber(((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(0)).getBookValue());
                }
                
                if (((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(0)).getBookAmountIsNull())
                {
                    l_bookValueSpecsUnit.bookAmount = null;
                }
                else
                {
                    l_bookValueSpecsUnit.bookAmount = WEB3StringTypeUtility.formatNumber(((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(0)).getBookAmount());
                }                
            }
            else    
            {   
                l_bookValueSpecsUnit.balQuantity = "0"; 
                l_bookValueSpecsUnit.bookPrice = "0";   
                l_bookValueSpecsUnit.bookAmount = "0";
            }
        }
        else
        {
            //1.5.1 (*)�������������񁕃p�����[�^�Z�b�g���\�[�g�����̍쐬
            StringBuffer l_sbQueryString = new StringBuffer(); 
            l_sbQueryString.append("and rec_div = ? ");          
            l_sbQueryString.append("and calc_date = ?");               

            List l_lisArrayList = new ArrayList();
            l_lisArrayList.add(WEB3BookValueRecordDivDef.DETAIL_REC);
            l_lisArrayList.add(l_datFinalCalcDatefromBookValueSpec);

            Object[] l_objArray = new Object[l_lisArrayList.size()];
            l_lisArrayList.toArray(l_objArray);
            
            String l_strSortCond = " sort_no desc";

            //1.5.2 get�뉿�P�����׈ꗗ(�ڋq, String, String, String, Object[], String)
            List l_lisgetBookValueSpecUnits = 
                l_historyTradeHistoryDataManager.getBookValueSpecList(
                    l_mainAccount, 
                    l_strCommodityCode, 
                    l_strProductCode, 
                    l_sbQueryString.toString(), 
                    l_objArray, 
                    l_strSortCond);

            if(l_lisgetBookValueSpecUnits == null 
                || l_lisgetBookValueSpecUnits.size() == 0)
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.5.3 (*)�v���p�e�B�Z�b�g
            if (((BookValueSpecParams)l_lisgetBookValueSpecUnits.get(0)).getBalQuantityIsNull())
            {
                l_bookValueSpecsUnit.balQuantity = null;
            }
            else
            {
                l_bookValueSpecsUnit.balQuantity = WEB3StringTypeUtility.formatNumber(((BookValueSpecParams)l_lisgetBookValueSpecUnits.get(0)).getBalQuantity());
            }
            
            if (((BookValueSpecParams)l_lisgetBookValueSpecUnits.get(0)).getBookValueIsNull())
            {
                l_bookValueSpecsUnit.bookPrice = null;
            }
            else
            {
                l_bookValueSpecsUnit.bookPrice = WEB3StringTypeUtility.formatNumber(((BookValueSpecParams)l_lisgetBookValueSpecUnits.get(0)).getBookValue());
            }
            
            if (((BookValueSpecParams)l_lisgetBookValueSpecUnits.get(0)).getBookAmountIsNull())
            {
                l_bookValueSpecsUnit.bookAmount = null;
            }
            else
            {
                l_bookValueSpecsUnit.bookAmount = WEB3StringTypeUtility.formatNumber(((BookValueSpecParams)l_lisgetBookValueSpecUnits.get(0)).getBookAmount());
            }
        }
        double l_dblsumproloss = 0;
        if (l_lisBalanceRecordfromBookValueSpec != null)
        {
            //1.6 (*)get�c�����R�[�hfrom�뉿�P������()�̖߂�l�̗v�f(=�뉿�P������Params)����Loop����
            int l_intSize = l_lisBalanceRecordfromBookValueSpec.size();
            for (int i = 0; i < l_intSize; i++)
            {
                //1.6.1 (*)���v�̏W�v
                l_dblsumproloss += ((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(i)).getProloss1() +
                    ((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(i)).getProloss2();
                
            }            
        }

        //1.7 get�뉿�P�����׈ꗗ(�ڋq, String, String, String, boolean)
        List l_lisBoolgetBookValueSpecUnits = l_historyTradeHistoryDataManager.getBookValueSpecList(l_mainAccount, l_strCommodityCode, l_strProductCode, l_strDisplayTerm, true);
        double l_dblsumproloss1 = 0;
        if (l_lisBoolgetBookValueSpecUnits != null)
        {
            //1.8 (*)get�뉿�P�����׈ꗗ()�̖߂�l�̗v�f(=�뉿�P������Params)����Loop����
            int l_intBookValueSize = l_lisBoolgetBookValueSpecUnits.size();
            for (int j = 0; j < l_intBookValueSize; j++)
            {
                //1.8.1 (*)���v�̏W�v
                l_dblsumproloss1 += ((BookValueSpecParams)l_lisBoolgetBookValueSpecUnits.get(j)).getProloss1();
            }            
        }       

        //1.9 (*)�v���p�e�B�Z�b�g
        if (l_lisBalanceRecordfromBookValueSpec != null)
        {
            l_bookValueSpecsUnit.bookValueSpecId = String.valueOf(((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(0)).getBookValueSpecId());    
        }
        else
        {
            l_bookValueSpecsUnit.bookValueSpecId = null;
        }
                
        l_bookValueSpecsUnit.bookvalRecDiv = WEB3BookValueRecordDivDef.BALANCE_REC;
        l_bookValueSpecsUnit.prolossAmount = WEB3StringTypeUtility.formatNumber(l_dblsumproloss + l_dblsumproloss1);
                
        log.exiting(STR_METHOD_NAME); 
        return l_bookValueSpecsUnit;
    }
}
@
