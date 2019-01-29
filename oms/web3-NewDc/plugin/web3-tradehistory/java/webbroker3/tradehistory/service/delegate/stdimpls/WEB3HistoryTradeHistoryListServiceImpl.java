head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������ꗗ�T�[�r�XImpl(WEB3HistoryTradeHistoryListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 �͌d�� (���u) �V�K�쐬
Revesion History : 2005/10/07 �� (SRA) �d�q�����g�p�Ή�
Revesion History : 2005/10/26 ���� (SRA) �����،��Ή�
Revesion History : 2005/11/08 ���ہi���{���u�j�O���Ή��E��QU02526�Ή�
Revesion History : 2006/01/23 ���u��(���{���u) �d�l�ύX�E���f��043
Revesion History : 2006/01/27 ��؁iSRA) �d�l�ύX�Ǘ�No.044
Revesion History : 2006/04/25 ������(���u) �d�l�ύX�E���f��047-051
Revesion History : 2006/05/01 ���(SCS) calc�����c���Acreate���������� �C���Ή�
Revesion History : 2006/05/29 ���n(SCS) create���������� �C���Ή�
Revesion History : 2006/06/27 ���(SCS) calc�����c�� �C���Ή��E���f��055
Revesion History : 2006/10/19 �����F (���u) ���f�� 057
Revesion History : 2007/06/14 ���n�m (���u) ���f�� 071
*/
package webbroker3.tradehistory.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3DepositMarginDivDef;
import webbroker3.common.define.WEB3IoDivDef;
import webbroker3.common.define.WEB3ReportDivDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TodayPaymentCheckDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.util.WEB3FeqDateUtility;

import webbroker3.tradehistory.WEB3HistoryTradeHistoryDataManager;
import webbroker3.tradehistory.WEB3HistoryTradeHistoryListCSV;
import webbroker3.tradehistory.data.TradeHistoryParams;
import webbroker3.tradehistory.data.TradeHistoryRow;
import webbroker3.tradehistory.data.TransactionHistoryDao;
import webbroker3.tradehistory.data.TransactionHistoryParams;
import webbroker3.tradehistory.data.TransactionHistoryRow;
import webbroker3.tradehistory.define.WEB3HistoryKeyItemDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryBondDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryCpitalGainTaxDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryEquityDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryEquityMarginDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryFuturesDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryFuturesOptionDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryMarginDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryMutualFundRuitoDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryOptionDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryProductCodeDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryProductDivDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryRemarkCodeDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryTradeCodeDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryForeignDef;
import webbroker3.tradehistory.message.WEB3HistoryDailyBalanceUnit;
import webbroker3.tradehistory.message.WEB3HistorySortKeyUnit;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryDownloadRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryDownloadResponse;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryListRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryListResponse;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryUnit;
import webbroker3.tradehistory.service.delegate.WEB3HistoryTradeHistoryListService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (��������ꗗ�T�[�r�XImpl)<BR>
 * ��������ꗗ�T�[�r�X�����N���X<BR>
 * 
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3HistoryTradeHistoryListServiceImpl extends WEB3ClientRequestService implements WEB3HistoryTradeHistoryListService 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3HistoryTradeHistoryListServiceImpl.class);   

    /**
     * (wk��n���z)         
     * wk��n���z�iwork�ϐ��j<BR> 
     * <BR>
     * ����work�ϐ���is���z�␳()��calc�����c��()�� <BR>
     * �v�Z�����ɂĎg�p���܂��B<BR>         
     */
    private double wkNetAmount = 0D;
    
    /**
     * @@roseuid 41789C470261
     */
    public WEB3HistoryTradeHistoryListServiceImpl() 
    {
     
    }
    
    /**
     * ��������ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �P�j�����̃��N�G�X�g�f�[�^���A��������ꗗ���N�G�X�g�̏ꍇ<BR>
     * �@@�@@�p�����[�^�̃��N�G�X�g�f�[�^��<BR>
     * �@@�@@��������ꗗ���N�G�X�g�ɃL���X�g����<BR>
     * �@@�@@get��������ꗗ���()���\�b�h���R�[������B<BR>
     * <BR>
     * �Q�j�����̃��N�G�X�g�f�[�^���A��������ꗗ�t�@@�C���_�E�����[�h���N�G�X�g�̏ꍇ<BR>
     * �@@�@@�p�����[�^�̃��N�G�X�g�f�[�^��<BR>
     * �@@�@@��������ꗗ�t�@@�C���_�E�����[�h���N�G�X�g�ɃL���X�g����<BR>
     * �@@�@@get��������ꗗ�t�@@�C���_�E�����[�h()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413C2ECB00E5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3HistoryTradeHistoryListRequest)
        {
            l_response = this.getTradeHistoryListScreen((WEB3HistoryTradeHistoryListRequest)l_request);
        }
        else if(l_request instanceof WEB3HistoryTradeHistoryDownloadRequest)
        {
            l_response = this.getTradeHistoryListDownload((WEB3HistoryTradeHistoryDownloadRequest)l_request);
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
     * (get��������ꗗ���)<BR>
     * ��������ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(��������ꗗ�T�[�r�X)get��������ꗗ��ʁv�Q��<BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �u(��������ꗗ�T�[�r�X)get��������ꗗ��ʁv        
     * <BR>
     *         ��̈ʒu    :  1.8  get��������ꗗ(String, String[], String)    <BR>
     *         �������Params�̈ꗗ���擾����B <BR>
     *         null���ԋp���ꂽ�ꍇ�́A <BR>
     *         �u�����Y����������݃G���[�v�̗�O���X���[����B  <BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_01070         <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �u(��������ꗗ�T�[�r�X)get��������ꗗ��ʁv        
     * <BR>
     *         ��̈ʒu    :  1.9.3  get��������ꗗ(String, String[], String)    <BR>
     *         �������Params�̈ꗗ���擾����B<BR>
     *         null���ԋp���ꂽ�ꍇ�́A<BR>
     *         �u�����Y����������݃G���[�v�̗�O���X���[����B <BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :   BUSINESS_ERROR_01070        <BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ��������ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistoryTradeHistoryListResponse
     * @@roseuid 413FAB3403D9
     */
    protected WEB3HistoryTradeHistoryListResponse getTradeHistoryListScreen(WEB3HistoryTradeHistoryListRequest l_request)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTradeHistoryListScreen(WEB3HistoryTradeHistoryListRequest l_request)";
        log.entering(STR_METHOD_NAME); 
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 get�⏕����
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
       
        //1.4 validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5 create��������������
        Date l_datListStartDate = WEB3DateUtility.getDate(l_request.listStartDate, "yyyyMMdd");
        Date l_datListEndDate = WEB3DateUtility.getDate(l_request.listEndDate, "yyyyMMdd");
        
        String l_queryString = this.createQueryString(l_datListStartDate, l_datListEndDate, l_request.productCode, l_request.commodityType) ;       
        
        //1.6 create���������f�[�^�R���e�i 
        String[] l_strQueryDataContainers = this.createQueryDataContainer(l_mainAccount, l_datListStartDate, l_datListEndDate, l_request.productCode, l_request.commodityType);

        //1.7 create�\�[�g����
        String l_strSortCond = this.createSortCond(l_request.sortKeys); 
        
        
        //1.8 get��������ꗗ
        List l_lisTradeHistorys = WEB3HistoryTradeHistoryDataManager.getTradeHistoryList(l_queryString, l_strQueryDataContainers, l_strSortCond);
        int  l_intSize = l_lisTradeHistorys.size();
        //1.15 createResponse()
        WEB3HistoryTradeHistoryListResponse l_response = (WEB3HistoryTradeHistoryListResponse)l_request.createResponse();

        if (l_intSize == 0)
        {
            l_response.tradingReportFlag = false;
            l_response.totalPages = "1";
            l_response.pageIndex = "1";
            l_response.totalRecords = "0";
            l_response.dailyBalanceUnits = null;
            return l_response;            
        }
        
        //1.9 (*1)����t���[
        if(l_request.listStartDate == null&&l_request.listEndDate == null )
        {
 
            //��������̍ŐV�̎�n�����擾����
            Date l_datLatestDeliveryDate = ((TradeHistoryParams)l_lisTradeHistorys.get(0)).getDeliveryDate();
            
            for (int i = 1;i < l_intSize;i ++)
            {
                if (WEB3DateUtility.compareToDay(((TradeHistoryParams)l_lisTradeHistorys.get(i)).getDeliveryDate(), l_datLatestDeliveryDate) > 0)
                {
                    l_datLatestDeliveryDate = ((TradeHistoryParams)l_lisTradeHistorys.get(i)).getDeliveryDate();
                }
            }
            //��������̍ŐV�̎�n���̈ꃖ���O���t���擾����
            Calendar l_calendar = new GregorianCalendar();
            l_calendar.setTime(l_datLatestDeliveryDate);
            l_calendar.add(Calendar.MONTH, -1);
            
            //1.9.1 create��������������         
            l_queryString = this.createQueryString(l_calendar.getTime(), l_datLatestDeliveryDate, l_request.productCode, l_request.commodityType) ;            

            //1.9.2 create���������f�[�^�R���e�i 
            l_strQueryDataContainers = this.createQueryDataContainer(l_mainAccount, l_calendar.getTime(), l_datLatestDeliveryDate, l_request.productCode, l_request.commodityType);

            //1.9.3 get��������ꗗ
            l_lisTradeHistorys = WEB3HistoryTradeHistoryDataManager.getTradeHistoryList(l_queryString, l_strQueryDataContainers, l_strSortCond);
            l_intSize = l_lisTradeHistorys.size();
            if (l_intSize == 0)
            {
                l_response.tradingReportFlag = false;
                l_response.totalPages = "1";
                l_response.pageIndex = "1";
                l_response.totalRecords = "0";
                l_response.dailyBalanceUnits = null;
                return l_response;           
            } 
        }
        
        //1.10 ����������I�u�W�F�N�g���i�[����ׂ̃��X�g�𐶐��B
        List l_lisTradeHistoryUnits = new ArrayList();
                       
        //1.11 ��n���ʎc�����I�u�W�F�N�g���i�[����ׂ̃��X�g�𐶐��B
        List l_lisDailyBalanceUnits = new ArrayList();
        
        //1.12 is�����o��(long)(��������ꗗ�T�[�r�XImpl::is�����o��)
		// [����] 
		//  ���XID�F�ڋq.getBranch().getBranchId()
        boolean l_blnIsTodayPayment =
            this.isTodayPayment(l_mainAccount.getBranch().getBranchId());

        //1.13 (*) get��������ꗗ()�̖߂�l�̂����A�\���Ώۍs(fromIndex �` toIndex)�̊�Loop���������{����             
        //�\���Ώۍs fromIndex �̌v�Z
        int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
   
        if (l_intRequestPageIndex < 1)
        {
            l_intRequestPageIndex = 1;
        }
        if (l_intRequestPageSize < 1)
        {
            l_intRequestPageSize = l_intSize;
        }
        
        // ���y�[�W��:
        int l_intTotalPages; 
        ///*-start--------------------------------------------- 
        // ���ׂ̗v�f�����y�[�W���\���s��
        if (l_intSize % l_intRequestPageSize == 0)
        {
            l_intTotalPages = l_intSize / l_intRequestPageSize;
        }
        //���]�肪�o��ꍇ�́A�{�P�����l��ݒ�
        else
        {
            l_intTotalPages = l_intSize / l_intRequestPageSize + 1;
        }
        //-end-----------------------------------------------         
        
        //�v���y�[�W�ԍ�
        int l_intPageIndex =
            (l_intRequestPageIndex > l_intTotalPages)
                ? l_intTotalPages
                : l_intRequestPageIndex;
        l_intPageIndex = (l_intPageIndex < 1) ? 1 : l_intPageIndex;
        
        //�\���ΏۍsfromIndex
        int l_intFromIndex = l_intRequestPageSize * (l_intPageIndex - 1);

        //�\���Ώۍs toIndex�̌v�Z  
        int l_intToIndex = l_intRequestPageSize * l_intPageIndex;
        if (l_intToIndex > l_intSize)
        {
            l_intToIndex = l_intSize;
        }
               
        for(int i = l_intFromIndex;i <= l_intToIndex - 1; i ++ )
        {
            //1.13.1 create����������
            WEB3HistoryTradeHistoryUnit l_web3HistoryTradeHistoryUnit = this.createTradeHistoryUnit((TradeHistoryParams)l_lisTradeHistorys.get(i));

			//1.13.2 is���z�␳(�������Params, �ڋq)(��������ꗗ�T�[�r�XImpl::is���z�␳)
			// �A�C�e���̒�`
			// 	���z�␳�̃��R�[�h���ʂ��A�␳���s���B 
			//  [����] 
			//  �@@�ڋq�F�@@getMainAccount()�̖߂�l 
			//  �@@�������Prams�F�����Ώۂ̎������Params
            
            //is�����o��()�̖߂�l��true�̏ꍇ�̂ݎ��{
            if (l_blnIsTodayPayment)
            {
                this.isPaymentRevision((TradeHistoryParams)l_lisTradeHistorys.get(i), l_mainAccount);    
            }
            
            //1.13.3 add(����������I�u�W�F�N�g)
            l_lisTradeHistoryUnits.add(l_web3HistoryTradeHistoryUnit);    
            
            //1.13.4 (*2)����t���[
            //Loop�����Ō�̗v�f�̏ꍇ �܂��́A
            //����̎������Params.��n���ƍ���̎������Params.��n�����قȂ�ꍇ
            //�ȉ��̏��������{����B
            //2004.11.24 17:30 �͌d�Ձ@@�C��
            if((i == l_intToIndex - 1)||
                ((i < l_intToIndex - 1)&&WEB3DateUtility.compareToDay(((TradeHistoryParams)l_lisTradeHistorys.get(i+1)).getDeliveryDate(), ((TradeHistoryParams)l_lisTradeHistorys.get(i)).getDeliveryDate()) != 0 ))
            {
                //1.13.4.1 ����������̔z����擾����B
                WEB3HistoryTradeHistoryUnit[] l_web3HistoryTradeHistoryUnits = new WEB3HistoryTradeHistoryUnit[l_lisTradeHistoryUnits.size()];
                l_lisTradeHistoryUnits.toArray(l_web3HistoryTradeHistoryUnits); 
                
                //1.13.4.2 create��n���ʎc�����
                WEB3HistoryDailyBalanceUnit l_web3HistoryDailyBalanceUnit= this.createDailyBalanceUnit(l_mainAccount, ((TradeHistoryParams)l_lisTradeHistorys.get(i)).getDeliveryDate(), l_web3HistoryTradeHistoryUnits);  
                
				//1.13.4.3 calc�����c��(��n���ʎc�����)(��������ꗗ�T�[�r�XImpl::calc�����c��)
				// [����] 
				// �@@��n���ʎc�����F�@@create��n���c�����()�̖߂�l 
                this.calcAccountBalance(l_web3HistoryDailyBalanceUnit);
                
                //1.13.4.4 toArray()�̖߂�l���v���p�e�B�F����������ꗗ�ɃZ�b�g�B
                l_web3HistoryDailyBalanceUnit.tradeHistoryUnits = l_web3HistoryTradeHistoryUnits;
                
                //1.13.4.5(*)��������������A�f�[�^�R���e�i�̍쐬
                //�ȑO�ɍ쐬������������������Ɏ�n���̏�����ǉ�����B

                //�������������� = create��������������()�̖߂�l + " and delivery_date = ? "
                //���������f�[�^�R���e�i = create���������f�[�^�R���e�i()�̖߂�l�̖����ɁA
                //        �����Ώۂ̎������Params.��n����ǉ�����B
                
                String l_query = l_queryString + " and to_char(delivery_date,'YYYY/MM/DD') = ? ";
                int l_lenth = 0;
                if (l_strQueryDataContainers != null)
                {
                    l_lenth = l_strQueryDataContainers.length;
                }
                
                String[] l_newContainers = new String[l_lenth + 1];
                for (int j = 0; j < l_lenth; j++)
                {
                    l_newContainers[j] = l_strQueryDataContainers[j];
                }
                Date l_dateDeliveryDate = ((TradeHistoryParams)l_lisTradeHistorys.get(i)).getDeliveryDate();
                l_newContainers[l_lenth] = WEB3DateUtility.formatDate(l_dateDeliveryDate,"yyyy/MM/dd");
                
                
                //1.13.4.6get��������ꗗ(�������������� : String, ���������f�[�^�R���e�i : String[], �\�[�g���� : String)
                //�w�肵����n���̎������Params�̈ꗗ���擾����B

                //[����]
                //��������������F�@@create��������������()�̖߂�l
                //���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l
                //�\�[�g�����F�@@create�\�[�g����()�̖߂�l
                List l_tradeHistoryList = WEB3HistoryTradeHistoryDataManager.getTradeHistoryList(l_query,l_newContainers,l_strSortCond);
                //1.13.4.7 is���v���׃����N(Date, String, ����������[])
                //���v���׃����N�t���O��\�邩�ǂ������ʂ���B

                //[����]
                //  ��n���F�@@�����Ώۂ̎������Params.��n��
                //  �����c���F�@@create��n���ʎc�����()�̖߂�l.�����c��
                //  ����������ꗗ�F�@@get��������ꗗ()�̖߂�l�@@����n���������ɉ����Č����������ʁB                              
                if (l_tradeHistoryList != null && l_tradeHistoryList.size() > 0)
                {
                    int l_intTradeHistoryListSize = l_tradeHistoryList.size();
                    WEB3HistoryTradeHistoryUnit[] l_historyTradeHistoryUnits = new WEB3HistoryTradeHistoryUnit[l_intTradeHistoryListSize];
                    
                    for (int j = 0; j < l_intTradeHistoryListSize; j++)
                    {
                        l_historyTradeHistoryUnits[j] =  createTradeHistoryUnit((TradeHistoryParams)l_tradeHistoryList.get(j));
                    }
                    
                    boolean l_isProfitLossLink = this.isProfitLossLink(l_dateDeliveryDate,l_web3HistoryDailyBalanceUnit.accountBalance,l_historyTradeHistoryUnits);
                    //1.14.4.8(*)is���v���׃����N()�̖߂�l���v���p�e�B�F���v���׃����N�t���O�ɃZ�b�g����B
                    l_web3HistoryDailyBalanceUnit.profitLossLink = l_isProfitLossLink;
                }

                //1.13.4.9 add(��n���ʎc�����I�u�W�F�N�g)
                l_lisDailyBalanceUnits.add(l_web3HistoryDailyBalanceUnit);
                
                //1.13.4.10 (���񏈗��̏���)������������i�[����ArrayList�̗v�f���N���A����B
                l_lisTradeHistoryUnits.clear();      
            }               
        }
        
        
        //1.14 ��n���ʎc�����̔z����擾����B
        WEB3HistoryDailyBalanceUnit[] l_web3HistoryDailyBalanceUnits = new WEB3HistoryDailyBalanceUnit[l_lisDailyBalanceUnits.size()];
        l_lisDailyBalanceUnits.toArray(l_web3HistoryDailyBalanceUnits);
        
//        WEB3GentradeBatoClientService l_batoClientService = 
//            (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);
//        //1.14 validate�d�q�����{(�@@�\�敪 : String)
//        String l_strBato = null;
        boolean l_blnTradingReportFlag;    
//        try
//        {
//            l_strBato = l_batoClientService.validateBato(WEB3GentradeBatoFunctionDivDef.BATO_TRAN_HIST_SERVICE);
//            //validate�d�q�����{()�̖߂�l == "0�F �ڋq�����{"�̏ꍇ�Afalse�B
//            if (WEB3GentradeBatoServiceRegServiceResultDef.NOT_AGREEMENT.equals(l_strBato))
//            {            
//                l_blnTradingReportFlag = false;
//            }
//            //�ȊO�Atrue���Z�b�g�B
//            else
//            {
//                l_blnTradingReportFlag = true;
//            }
//        }
//        catch(WEB3BusinessLayerException l_ex)
//        {
//            l_blnTradingReportFlag = true;
//        }
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        // ����񍐏���t�敪 == "1�F�d�q��t"�̏ꍇ�Atrue���Z�b�g�B
        if (l_mainAccountRow.getTradingReportDiv().equals(WEB3ReportDivDef.ACCEPT))
        {
            l_blnTradingReportFlag = true;
        }
        // �ȊO�Afalse���Z�b�g�B
        else
        {
            l_blnTradingReportFlag = false;
        }

        //1.16 ���X�|���X�f�[�^�� �v���p�e�B�Z�b�g
        l_response.tradingReportFlag = l_blnTradingReportFlag;
        l_response.totalPages = WEB3StringTypeUtility.formatNumber((double)l_intTotalPages);
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber((double)l_intSize);
        l_response.pageIndex = WEB3StringTypeUtility.formatNumber((double)l_intPageIndex);
        l_response.dailyBalanceUnits = l_web3HistoryDailyBalanceUnits;    

        log.exiting(STR_METHOD_NAME);  
        return l_response;
    }

    /**
     * (get��������ꗗ�t�@@�C���_�E�����[�h)<BR>
     * ��������ꗗ�t�@@�C���_�E�����[�h�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(��������ꗗ�T�[�r�X)get��������ꗗ�t�@@�C���_�E�����[�h�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ��������ꗗ�t�@@�C���_�E�����[�h���N�G�X�g<BR>
     * @@return WEB3HistoryTradeHistoryDownloadResponse
     * @@roseuid 413C2D7A03BF
     */
    protected WEB3HistoryTradeHistoryDownloadResponse getTradeHistoryListDownload(WEB3HistoryTradeHistoryDownloadRequest l_request)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTradeHistoryListDownload(WEB3HistoryTradeHistoryDownloadRequest)";
        log.entering(STR_METHOD_NAME); 

        //1.1 validate( )(��������ꗗ�t�@@�C���_�E�����[�h���N�G�X�g::validate)
        l_request.validate();
        
        //1.2 get�⏕����(�⏕�����^�C�v : SubAccountTypeEnum)
        //  [����] 
        //  �⏕�����^�C�v�F�@@SubAccountTypeEnum.�����������
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

		//�،���ЁF �iget�⏕����()�̖߂�l�j.getInstitutuin()�̖߂�l
		WEB3GentradeInstitution l_institution = 
			(WEB3GentradeInstitution) l_subAccount.getInstitution(); 
			
        //1.3 getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
       
        //1.4 validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5 ����J�����_�R���e�L�X�g�̐ݒ�
        //  ����J�����_�R���e�L�X�g����������_�E�����[�h�̐ݒ�ɂ���B
        //  �ȉ��̒l�Ŏ���J�����_�R���e�L�X�g�����Z�b�g����B
        //  �E����J�����_�R���e�L�X�g.��t���ԋ敪 = "30�F�_�E�����[�h"
        //  ����L�ȊO�͊����l�B
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        //��t���ԋ敪���擾
        String l_strTradingTimeType = l_context.getTradingTimeType();

        //��t���ԋ敪���Z�b�g����
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DOWNLOAD);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //1.6 validate�_�E�����[�h���ԑ�()
        WEB3GentradeTradingTimeManagement.validateDownloadTimeZone();

        //1.7 ����J�����_�R���e�L�X�g�̃��Z�b�g
        l_context.setTradingTimeType(l_strTradingTimeType);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        
        //1.8 create��������������(Date, Date, String, String)(��������ꗗ�T�[�r�XImpl::create��������������)
        //[����] 
        //  �\������From�F�@@���N�G�X�g�f�[�^.�\������From 
        //  �\������To�F�@@���N�G�X�g�f�[�^.�\������To 
        //  �����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h 
        //  ���i�敪�F�@@���N�G�X�g�f�[�^.���i�敪
        Date l_datListStartDate = WEB3DateUtility.getDate(l_request.listStartDate, "yyyyMMdd");
        Date l_datListEndDate = WEB3DateUtility.getDate(l_request.listEndDate, "yyyyMMdd");
        String l_strQueryString = this.createQueryString(
            l_datListStartDate, 
            l_datListEndDate, 
            l_request.productCode, 
            l_request.commodityType) ;            
        
        //1.9 create���������f�[�^�R���e�i(�ڋq, Date, Date, String, String)(��������ꗗ�T�[�r�XImpl::create���������f�[�^�R���e�i)
        //[����] 
        //  �ڋq�F�@@getMainAccount()�̖߂�l 
        //  �\������From�F�@@���N�G�X�g�f�[�^.�\������From 
        //  �\������To�F�@@���N�G�X�g�f�[�^.�\������To 
        //  �����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h 
        //  ���i�敪�F�@@���N�G�X�g�f�[�^.���i�敪
        String[] l_strQueryDataContainers = this.createQueryDataContainer(
            l_mainAccount,
            l_datListStartDate,
            l_datListEndDate,
            l_request.productCode,
            l_request.commodityType);

        //1.10 create�\�[�g����for�_�E�����[�h( )(��������ꗗ�T�[�r�XImpl::create�\�[�g����for�_�E�����[�h)
        String l_strSortCond = this.createSortCondForDownload(); 

        //1.11 get��������ꗗ(String, String[], String)(��������f�[�^�}�l�[�W��::get��������ꗗ)
        //[����] 
        //  ��������������F�@@create��������������()�̖߂�l 
        //  ���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l 
        //  �\�[�g�����F�@@create�\�[�g����()�̖߂�l
        List l_lisTradeHistoryList = WEB3HistoryTradeHistoryDataManager.getTradeHistoryList(
            l_strQueryString,
            l_strQueryDataContainers,
            l_strSortCond);

        //1.12 get�v���t�@@�����X(String)(��������ꗗ�T�[�r�XImpl::get�v���t�@@�����X)
        //[����] 
        //  �ݒ薼�́F�@@�Œ蕶��"DL_REC_COUNT_TRADEHISTORYLIST"
        String l_strPreferences = this.getPreferences(WEB3SystemPreferencesNameDef.DL_REC_COUNT_TRADEHISTORYLIST);

        //1.13 ���R�[�h������get�v���t�@@�����X()�̖߂�l�ȏ�̏ꍇ�A
        //  �u�Y�����錏�����_�E�����[�h�����𒴂��Ă��܂��B�v�G���[
        int l_intListCount = 0;
        if (l_lisTradeHistoryList != null)
        {
            l_intListCount = l_lisTradeHistoryList.size();
        }

        if (l_intListCount > Integer.parseInt(l_strPreferences))
        {
            log.debug("�u�Y�����錏�����_�E�����[�h�����𒴂��Ă��܂��B�v");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01957,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.14 ���R�[�h������0���̏ꍇ�A�u�����Y����������݃G���[�v
        if (l_intListCount == 0)
        {
            log.debug("�u�����Y����������݃G���[�v");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01070,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.15 ��������ꗗCSV( )(��������ꗗCSV::��������ꗗCSV)
        WEB3HistoryTradeHistoryListCSV l_tradeHistoryListCSV = new WEB3HistoryTradeHistoryListCSV();

        //�O��̎������Params.��n��
        Date l_datDeliveryDateBefore = null;

        //����̎������Params.��n��
        Date l_datDeliveryDateNow = null;
        
        //�����c��
		String l_strAccountBalance = null;
						
        //1.16 �������Params�̗v�f�����ALoop���������{����
        for (int i = 0; i < l_intListCount; i++)
        {
            // �O��̎������Params.��n�����擾
            l_datDeliveryDateBefore = l_datDeliveryDateNow;

            TradeHistoryRow l_tradeHistoryRow = (TradeHistoryRow) l_lisTradeHistoryList.get(i);
            TradeHistoryParams l_tradeHistoryParams = new TradeHistoryParams(l_tradeHistoryRow);

            // ����̎������Params.��n�����擾
            l_datDeliveryDateNow = l_tradeHistoryParams.getDeliveryDate();
					
            //1.16.1 add���׍s( )
            int l_intRowNumber = l_tradeHistoryListCSV.addRow();
            
            //1.16.2 set��n��(int, Date)(��������ꗗCSV::set��n��)
            //[set��n��()�Ɏw�肷�����] 
            //  �s�ԍ��F�@@add���׍s()�̖߂�l 
            //  ��n���F�@@�����Ώۂ̎������Params.��n��
            l_tradeHistoryListCSV.setDeliveryDate(
                l_intRowNumber,
                l_datDeliveryDateNow);
            
            //1.16.3 set����(int, Date)(��������ꗗCSV::set����)
            //[set����()�Ɏw�肷�����] 
            //  �s�ԍ��F�@@add���׍s()�̖߂�l 
            //  �����F�@@�����Ώۂ̎������Params.����
            l_tradeHistoryListCSV.setExecutionDate(
                l_intRowNumber,
                l_tradeHistoryParams.getExecDate());
    
            //1.16.4 set���i�R�[�h����(int, String, String, String)(��������ꗗCSV::set���i�R�[�h����)
            //[set���i�R�[�h����()�Ɏw�肷�����] 
            //  �s�ԍ��F�@@add���׍s()�̖߂�l 
            //  ��ЃR�[�h�F�@@�����Ώۂ̎������Params.�،���ЃR�[�h 
            //  ���i�R�[�h�F�@@�����Ώۂ̎������Params.���i�R�[�h 
            //  �ٍϋ敪�F�@@�����Ώۂ̎������Params.�ٍϋ敪
            l_tradeHistoryListCSV.setCommodityCode(
                l_intRowNumber,
                l_tradeHistoryParams.getInstitutionCode(),
                l_tradeHistoryParams.getCommodityCode(),
                l_tradeHistoryParams.getRepaymentType());
            
            //1.16.5 set����(int, String, String, String)(��������ꗗCSV::set����)
            //[set����()�Ɏw�肷�����] 
            //  �s�ԍ��F�@@add���׍s()�̖߂�l 
            //  ���i�R�[�h�F�@@�����Ώۂ̎������Params.���i�R�[�h 
            //  �����R�[�h�F�@@�����Ώۂ̎������Params.�����R�[�h 
            //  �����E�v���F�@@�����Ώۂ̎������Params.�����E�v��
            l_tradeHistoryListCSV.setProduct(
                l_intRowNumber,
                l_tradeHistoryParams.getCommodityCode(),
                l_tradeHistoryParams.getProductCode(),
                l_tradeHistoryParams.getProductName());
            
            //1.16.6 set�����敪����(int, String, String, String)(��������ꗗCSV::set�����敪����)
            //[set�����敪����()�Ɏw�肷�����] 
            //  �s�ԍ��F�@@add���׍s()�̖߂�l 
            //  ���i�R�[�h�F�@@�����Ώۂ̎������Params.���i�R�[�h 
            //  �����敪�F�@@�����Ώۂ̎������Params.�����敪 
            //  �E�v�R�[�h�F�@@�����Ώۂ̎������Params.�E�v�R�[�h
            //  ����R�[�h�F�@@�����Ώۂ̎������Params.����R�[�h
            l_tradeHistoryListCSV.setAccountType(
                l_intRowNumber,
                l_tradeHistoryParams.getCommodityCode(),
                l_tradeHistoryParams.getAccountDiv(),
                l_tradeHistoryParams.getRemarkCode(),
                l_tradeHistoryParams.getTradeCode());
    
            //1.16.7 set�|��E�v��(int, String, String)(��������ꗗCSV::set�|��E�v��)
            //[set�|��E�v��()�Ɏw�肷�����] 
            //  �s�ԍ��F�@@add���׍s()�̖߂�l 
            //  ���i�R�[�h�F�@@�����Ώۂ̎������Params.���i�R�[�h 
            //  �|��E�v���F�@@�����Ώۂ̎������Params.�|��E�v��
            l_tradeHistoryListCSV.setRemarkName(
                l_intRowNumber,
                l_tradeHistoryParams.getCommodityCode(),
                l_tradeHistoryParams.getRemarkName());
            
            //1.16.8 set����(int, String)(��������ꗗCSV::set����)
            //[set����()�Ɏw�肷�����] 
            //  �s�ԍ��F�@@add���׍s()�̖߂�l 
            //  ���ʁF�@@�����Ώۂ̎������Params.����
            l_tradeHistoryListCSV.setQuantity(
                l_intRowNumber,
                WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getQuantity()));
            
            //1.16.9 set�P��(int, String)(��������ꗗCSV::set�P��)
            //[set���P��()�Ɏw�肷�����] 
            //  �s�ԍ��F�@@add���׍s()�̖߂�l 
            //  �P���F�@@�����Ώۂ̎������Params.�P��
            l_tradeHistoryListCSV.setPrice(
                l_intRowNumber,
                WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getPrice()));
    
            //1.16.10 set�ʉݒP�ʖ���(int, String, String)(��������ꗗCSV::set�ʉݒP�ʖ���)
            //[set�ʉݒP�ʖ���()�Ɏw�肷�����] 
            //  �s�ԍ��F�@@add���׍s()�̖߂�l 
            //  ��ЃR�[�h�F�@@�����Ώۂ̎������Params.�،���ЃR�[�h 
            //  �ʉݒP�ʁF�@@�����Ώۂ̎������Params.�ʉݒP��
            l_tradeHistoryListCSV.setMonetaryUnit(
                l_intRowNumber,
                l_tradeHistoryParams.getInstitutionCode(),
                l_tradeHistoryParams.getMonetaryUnit());
            
            //1.16.11 set��n���z(int, String)(��������ꗗCSV::set��n���z)
            //[set����()�Ɏw�肷�����] 
            //  �s�ԍ��F�@@add���׍s()�̖߂�l 
            //  ��n���z�F�@@�����Ώۂ̎������Params.��n���z
            l_tradeHistoryListCSV.setNetAmount(
                l_intRowNumber,
                WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getNetAmount()));
    
            //1.16.12 set���n���v(int, String)(��������ꗗCSV::set���n���v)
            //[set���n���v()�Ɏw�肷�����] 
            //  �s�ԍ��F�@@add���׍s()�̖߂�l 
            //  ���n���v�F�@@�����Ώۂ̎������Params.���n���v
            l_tradeHistoryListCSV.setCapitalGain(
                l_intRowNumber,
                WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getCapitalGain()));
    
            //1.16.13 (*1)����t���[
            //(*1)����t���[
            // �O��̎������Params.��n���ƍ���̎������Params.��n�����قȂ�ꍇ
            //�����c�����`
            if (WEB3DateUtility.compare(l_datDeliveryDateBefore, l_datDeliveryDateNow) != 0)
            {
                //1.16.13.1 get�����c��(�ڋq, Date)(��������ꗗ�T�[�r�XImpl::get�����c��)
                //[����]  
                //  �ڋq�F�@@getMainAccount()�̖߂�l  
                //  ��n���F�@@�����Ώۂ̎������Params.��n�� 
                l_strAccountBalance = this.getAccountBalance(
                    l_mainAccount,
                    l_datDeliveryDateNow);
            }
    
            //1.16.14 set�����c��(int, String)(��������ꗗCSV::set�����c��)
            //[set�����c��()�Ɏw�肷�����] 
            //  �s�ԍ��F�@@add���׍s()�̖߂�l 
            //  �����c���F�@@get�����c��()�̖߂�l
            l_tradeHistoryListCSV.setAccountBalance(
                l_intRowNumber,
                l_strAccountBalance);
        }

        //1.17 getCSV�t�@@�C���s( )
        String[] l_strCsvFileLines = l_tradeHistoryListCSV.getCsvFileLines();
        
        //1.18 createResponse( )
        WEB3HistoryTradeHistoryDownloadResponse l_response =
            (WEB3HistoryTradeHistoryDownloadResponse)l_request.createResponse();

        //1.19 �v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�_�E�����[�h�t�@@�C��  ���@@getCSV�t�@@�C���s()�̖߂�l
        l_response.downloadFile = l_strCsvFileLines;

        //���ݓ���        ���@@TradingSystem.getSystemTimestamp()�̖߂�l
        l_response.currentDate = WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);  
        return l_response;
    }
    
    /**
     * (create��������������)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j�ȉ��̌ڋq������������������ɃZ�b�g����B<BR>
     * �@@�E�،���ЃR�[�h<BR>
     * �@@�E���X�R�[�h<BR>
     * �@@�E�ڋq�R�[�h<BR>
     * <BR>
     * �@@�������������� = "institution_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and branch_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and account_code = ? "<BR>
     * <BR>
     * �Q�j�p�����[�^.�\������From != null ���� <BR>
     * �@@�@@�p�����[�^.�\������To != null�̏ꍇ�A<BR>
     * �@@�@@�\������From�ƕ\������To�����������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� +=<BR>
     * �@@�@@"and delivery_date >= to_date(?, 'YYYYMMDD') "<BR>
     * �@@�@@+ "and delivery_date <= to_date(?, 'YYYYMMDD') "<BR>
     * <BR>
     * �R�j�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@�@@�����R�[�h�����������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and product_code = ? "<BR>
     * <BR>
     * �S�j�p�����[�^.���i�敪 != null�̏ꍇ�A<BR>
     * �@@�@@���i�R�[�h�A�E�v�R�[�h���ȉ��ɏ]�����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�p�����[�^.���i�敪���A<BR>
     * �@@�@@[�hA:�S���i�h�̏ꍇ]<BR>
     * �@@�@@�@@���i�R�[�h�A�E�v�R�[�h�����������ɒǉ����Ȃ��B<BR>
     * <BR>
     * �@@�@@[�hB:�����E�M�p�h�̏ꍇ]<BR>
     * �@@�@@�@@�������������� += "and commodity_code in (?, ?) "<BR>
     * <BR>
     * �@@�@@[�hC:�����h�܂��́hD:�M�p�h�܂��́hK:�O�������h�̏ꍇ]<BR>
     * �@@�@@�@@�������������� += "and commodity_code = ? "<BR>
     * <BR>
     * �@@�@@[�hE:�敨�E�I�v�V�����h�̏ꍇ]<BR>
     * �@@�@@�@@�������������� += "and commodity_code IN (?, ?, ?, ?, ?, ?, ?, ?) "<BR>
     * <BR>
     * �@@�@@[�hF:�敨�h�̏ꍇ]<BR>
     * �@@�@@�@@�������������� += "and commodity_code IN (?, ?, ?) "<BR>
     * <BR>
     * �@@�@@[�hG:�I�v�V�����h�̏ꍇ]<BR>
     * �@@�@@�@@�������������� += "and commodity_code IN (?, ?, ?, ?, ?) "<BR>
     * <BR>
     * �@@�@@[�hH:���M�E�ݓ��h�̏ꍇ]<BR>
     * �@@�@@�@@�������������� += "and (commodity_code IN (?, ?, ?, ?) "<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "or (commodity_code =? and remark_code in (?, ?) 
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and trade_code= ?)) " <BR>
     * <BR>
     * �@@�@@[�hI:���o���h�̏ꍇ]<BR>
     * �@@�@@�@@�������������� += "and commodity_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and remark_code not in (?, ?, ?, ?, ?, ?) "<BR>
     * <BR>
     * �@@�@@[�hJ:���n�v�Łh�̏ꍇ]<BR>
     * �@@�@@�@@�������������� += "and commodity_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and remark_code in (?, ?, ?, ?, ?, ?) "<BR>
     * <BR>
     * �@@�@@[�hL:���h�̏ꍇ] <BR>
     *�@@�@@�@@�������������� += "and commodity_code IN (?, ?) " <BR>
     * <BR>
     * �T�j�쐬�������������������ԋp����B<BR>
     * @@param l_datListStartDate - (�\������From)<BR>
     * �\������From<BR>
     * (YYYYMMDD)<BR>
     * @@param l_datListEndDate - (�\������To)<BR>
     * �\������To<BR>
     * (YYYYMMDD)<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strProductType - (���i�敪)<BR>
     * A�F�@@�S���i<BR>
     * B�F�@@�����E�M�p<BR>
     * C�F�@@����<BR>
     * D�F�@@�M�p<BR>
     * E�F�@@�敨�E�I�v�V����<BR>
     * F�F�@@�敨<BR>
     * G�F�@@�I�v�V����<BR>
     * H�F�@@���M�E�ݓ�<BR>
     * I�F�@@���o��<BR>
     * J�F�@@���n�v��<BR>
     * K�F�@@�O������<BR>
     * L�F�@@��<BR>
     * @@return String
     * @@roseuid 413C30C30143
     */
    protected String createQueryString(Date l_datListStartDate, Date l_datListEndDate, String l_strProductCode, String l_strProductType) 
    {
        final String STR_METHOD_NAME = " createQueryString(Date l_datListStartDate, Date l_datListEndDate, String l_strProductCode, String l_strProductType)"; 
        
        log.entering(STR_METHOD_NAME);  
        
        StringBuffer l_strQueryString = new StringBuffer();
        //
        // �P�j�ȉ��̌ڋq������������������ɃZ�b�g����B
        // �@@�E�،���ЃR�[�h
        // �@@�E���X�R�[�h
        // �@@�E�ڋq�R�[�h
        //
        l_strQueryString.append("institution_code = ? and branch_code = ? and account_code = ? ");
            
        //
        // �Q�j�p�����[�^.�\������From != null ���� �p�����[�^.�\������To != 
        //     null�̏ꍇ�A<BR>
        // �@@ �\������From�ƕ\������To�����������ɒǉ�����B
        //
        if ((l_datListStartDate != null) && (l_datListEndDate != null))
        {
            l_strQueryString.append("and delivery_date >= to_date(?, 'YYYYMMDD') "
                + "and delivery_date <= to_date(?, 'YYYYMMDD') ");
        }

        //
        // �R�j�p�����[�^.�����R�[�h != null�̏ꍇ�A
        // �@@�@@�����R�[�h�����������ɒǉ�����B
        //
        if (l_strProductCode != null)
        {
            l_strQueryString.append("and product_code = ? ");  	
        }
          
        //
        // �S�j�p�����[�^.���i�敪 != null�̏ꍇ�A
        // �@@�@@���i�R�[�h�A�E�v�R�[�h���ȉ��ɏ]�����������ɒǉ�����B
        //
        if (l_strProductType != null)
        {
            //
            // �@@�@@�p�����[�^.���i�敪���A
            //     [�hB:�����E�M�p�h�̏ꍇ]
            //
            if(WEB3TradeHistoryProductDivDef.EQUITY_MARGIN.equals(l_strProductType))
            {
                l_strQueryString.append("and commodity_code in (?, ?) ");    	
            } 
            //     [�hC:�����h�܂��́hD:�M�p�h�܂��́hK:�O�������h�̏ꍇ]<BR>
            else if((WEB3TradeHistoryProductDivDef.EQUITY.equals(l_strProductType)) || (WEB3TradeHistoryProductDivDef.MARGIN.equals(l_strProductType)) || (WEB3TradeHistoryProductDivDef.FOREIGN.equals(l_strProductType))) 
            {
                 l_strQueryString.append("and commodity_code = ? ");             
            } 
            //     [�hE:�敨�E�I�v�V�����h�̏ꍇ]
            else if(WEB3TradeHistoryProductDivDef.FUTURES_OPTION.equals(l_strProductType))
            {
                 l_strQueryString.append("and commodity_code IN (?, ?, ?, ?, ?, ?, ?, ?) ");    	
            }                          
            //     [�hF:�敨�h�̏ꍇ]
            else if(WEB3TradeHistoryProductDivDef.FUTURES.equals(l_strProductType))
            {
                 l_strQueryString.append("and commodity_code IN (?, ?, ?) ");    	
            }                        
            //     [�hG:�I�v�V�����h�̏ꍇ]
            else if(WEB3TradeHistoryProductDivDef.OPTION.equals(l_strProductType))
            {
                 l_strQueryString.append("and commodity_code IN (?, ?, ?, ?, ?) ");    	
            }  
            //    [�hH:���M�E�ݓ��h�̏ꍇ]
            else if(WEB3TradeHistoryProductDivDef.MUTUAL_FUND_RUITO.equals(l_strProductType))
            {
                 l_strQueryString.append("and (commodity_code IN (?, ?, ?, ?) ");
                 l_strQueryString.append("or (commodity_code =? and remark_code in (?, ?) and trade_code= ?)) ");
            }                                 
            //     [�hI:���o���h�̏ꍇ]
            else if(WEB3TradeHistoryProductDivDef.AIO.equals(l_strProductType))
            {
                 l_strQueryString.append("and commodity_code = ? "+
                    "and remark_code not in (?, ?, ?, ?, ?, ?) ");    	
            }                                    
            //     [�hJ:���n�v�Łh�̏ꍇ]
            else if(WEB3TradeHistoryProductDivDef.CPITAL_GAIN_TAX.equals(l_strProductType))
            {
                 l_strQueryString.append("and commodity_code = ? "+
                    "and remark_code in (?, ?, ?, ?, ?, ?) ");    	
            }          
            //     [�hL:���h�̏ꍇ]
            else if (WEB3TradeHistoryProductDivDef.BOND.equals(l_strProductType))
            {
                l_strQueryString.append("and commodity_code IN (?, ?) " );
            }
        }
        
        log.exiting(STR_METHOD_NAME);  
              
        // �T�j�쐬�������������������ԋp����B
        String l_strQueryStringReturn = l_strQueryString.toString();
        return l_strQueryStringReturn;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������������"?"�����ɃZ�b�g����p�����[�^���X�g(������z��)���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�ȉ��̏��ԂŌڋq����ArrayList�ɒǉ�����B<BR>
     * �@@�@@���ȍ~�AArrayList�ɒǉ�����ۂɂ́AString�^�ɕϊ����Ă���ǉ����邱�ƁB<BR>
     * �@@�E�،���ЃR�[�h<BR>
     * �@@�@@�p�����[�^.�ڋq.�،���ЃR�[�h<BR>
     * �@@�E���X�R�[�h<BR>
     * �@@�@@�p�����[�^.�ڋq.���X�R�[�h<BR>
     * �@@�E�ڋq�R�[�h<BR>
     * �@@�@@�p�����[�^.�ڋq.getAccountCode()<BR>
     * �@@�@@���擪6byte�̂݃Z�b�g����B<BR>
     * <BR>
     * �R�j�p�����[�^.�\������From != null ���� �p�����[�^.�\������To != null�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�ȉ��̏����ŕ\�����Ԃ�ArrayList�ɒǉ�����B<BR>
     * �@@�E�p�����[�^.�\������From<BR>
     * �@@�E�p�����[�^.�\������To<BR>
     * <BR>
     * �S�j�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@�@@�p�����[�^.�����R�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �T�j�p�����[�^.���i�敪 != null�̏ꍇ�A<BR>
     * �@@�@@���i�R�[�h�A�E�v�R�[�h���ȉ��ɏ]���A<BR>
     * �@@�@@�ォ�珇��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@[�hA:�S���i�h�̏ꍇ]<BR>
     * �@@�@@�@@���i�R�[�h�A�E�v�R�[�h��ArrayList�ɒǉ����Ȃ��B<BR>
     * <BR>
     * �@@�@@[�hB:�����E�M�p�h�̏ꍇ]<BR>
     * �@@�@@�@@�E"10:����"<BR>
     * �@@�@@�@@�E"11:�M�p"<BR>
     * <BR>
     * �@@�@@[�hC:�����h�̏ꍇ]<BR>
     * �@@�@@�@@�E"10:����"<BR>
     * <BR>
     * �@@�@@[�hD:�M�p�h�̏ꍇ]<BR>
     * �@@�@@�@@�E"11:�M�p"<BR>
     * <BR>
     * �@@�@@[�hE:�敨�E�I�v�V�����h�̏ꍇ]<BR>
     * �@@�@@�@@�E"50:�����敨"<BR>
     * �@@�@@�@@�E"51:�����I�v�V����"<BR>
     * �@@�@@�@@�E"52:���敨"<BR>
     * �@@�@@�@@�E"53:���敨�I�v�V����"<BR>
     * �@@�@@�@@�E"54:�X���I�v�V����"<BR>
     * �@@�@@�@@�E"55:�C�O�敨"<BR>
     * �@@�@@�@@�E"56:�C�O�敨�I�v�V����"<BR>
     * �@@�@@�@@�E"57:�����I�v�V����"<BR>
     * <BR>
     * �@@�@@[�hF:�敨�h�̏ꍇ]<BR>
     * �@@�@@�@@�E"50:�����敨"<BR>
     * �@@�@@�@@�E"52:���敨"<BR>
     * �@@�@@�@@�E"55:�C�O�敨"<BR>
     * <BR>
     * �@@�@@[�hG:�I�v�V�����h�̏ꍇ]<BR>
     * �@@�@@�@@�E"51:�����I�v�V����"<BR>
     * �@@�@@�@@�E"53:���敨�I�v�V����"<BR>
     * �@@�@@�@@�E"54:�X���I�v�V����"<BR>
     * �@@�@@�@@�E"56:�C�O�敨�I�v�V����"<BR>
     * �@@�@@�@@�E"57:�����I�v�V����"<BR>
     * <BR>
     * �@@�@@[�hH:���M�E�ݓ��h�̏ꍇ]<BR>
     * �@@�@@�@@�E"20:�������M"<BR>
     * �@@�@@�@@�E"21:�O�����M"<BR>
     * �@@�@@�@@�E"22:GP"<BR>
     * �@@�@@�@@�E"23:MRF"<BR>
     * �@@�@@�@@�E"00:MMF"<BR>
     * �@@�@@�@@�E"D102:���t"<BR>
     * �@@�@@�@@�E"D108:���t"<BR>
     * �@@�@@�@@�E"A3:�U��"<BR>
     * <BR>
     * �@@�@@[�hI:���o���h �܂��� �hJ:���n�v�Łh�̏ꍇ]<BR>
     * �@@�@@�@@�E"99:���K"<BR>
     * �@@�@@�@@�E"1079:���K((����)���n�v�Ŋҕt��)"<BR>
     * �@@�@@�@@�E"1080:���K((����)���n�v�Œ���)"<BR>
     * �@@�@@�@@�E"1082:���K(������n�v�Œ��� ����)"<BR>
     * �@@�@@�@@�E"1083:���K(������n�v�Œ��� �n����)"<BR>
     * �@@�@@�@@�E"1084:���K(������n�v�Ŋҕt ����)"<BR>
     * �@@�@@�@@�E"1085:���K(������n�v�Ŋҕt �n����)"<BR>
     * <BR>
     * �@@�@@[�hK:�O�������h�̏ꍇ]<BR>
     * �@@�@@�@@�E"40:�O������"<BR>
     * <BR>
     * �@@�@@[�hL:���h�̏ꍇ] <BR>
     *�@@�@@�@@�E"30:������" <BR>
     *�@@�@@�@@�E"60:�O����" <BR>
     * <BR>
     * �U�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_datListStartDate - (�\������From)<BR>
     * �\������From<BR>
     * (YYYYMMDD)<BR>
     * @@param l_datListEndDate - (�\������To)<BR>
     * �\������To<BR>
     * (YYYYMMDD)<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strProductType - (���i�敪)<BR>
     * A�F�@@�S���i<BR>
     * B�F�@@�����E�M�p<BR>
     * C�F�@@����<BR>
     * D�F�@@�M�p<BR>
     * E�F�@@�敨�E�I�v�V����<BR>
     * F�F�@@�敨<BR>
     * G�F�@@�I�v�V����<BR>
     * H�F�@@���M�E�ݓ�<BR>
     * I�F�@@���o��<BR>
     * J�F�@@���n�v��<BR>
     * K�F�@@�O������<BR>
     * L�F�@@�� <BR>
     * @@return java.lang.String[]
     * @@roseuid 413C30D3025B
     */
    protected String[] createQueryDataContainer(WEB3GentradeMainAccount l_mainAccount, Date l_datListStartDate, Date l_datListEndDate, String l_strProductCode, String l_strProductType) 
    {
        final String STR_METHOD_NAME = "createQueryDataContainer (WEB3GentradeMainAccount l_mainAccount, Date l_datListStartDate, Date l_datListEndDate, String l_strProductCode, String l_strProductType) "; 
        
        log.entering(STR_METHOD_NAME);  
       
        //* �P�jArrayList�𐶐�����B<BR>
        List l_lisContainers = new ArrayList();     
        
        //
        // �Q�j�ȉ��̏��ԂŌڋq����ArrayList�ɒǉ�����B<BR>
        // �@@�@@���ȍ~�AArrayList�ɒǉ�����ۂɂ́AString�^�ɕϊ����Ă���ǉ����邱�ƁB<BR>
        // �@@�E�،���ЃR�[�h<BR>
        // �@@�@@�p�����[�^.�ڋq.�،���ЃR�[�h<BR>
        // �@@�E���X�R�[�h<BR>
        // �@@�@@�p�����[�^.�ڋq.���X�R�[�h<BR>
        // �@@�E�ڋq�R�[�h<BR>
        // �@@�@@�p�����[�^.�ڋq.getAccountCode()<BR>
        // <BR>
        l_lisContainers.add(l_mainAccount.getInstitution().getInstitutionCode()) ;
        l_lisContainers.add(l_mainAccount.getBranch().getBranchCode());
        l_lisContainers.add(l_mainAccount.getAccountCode().substring(0, 6));
               
        //
        // �R�j�p�����[�^.�\������From != null ���� �p�����[�^.�\������To != 
        // null�̏ꍇ�A<BR>
        // �@@�@@�ȉ��̏����ŕ\�����Ԃ�ArrayList�ɒǉ�����B<BR>
        // �@@�E�p�����[�^.�\������From<BR>
        // �@@�E�p�����[�^.�\������To<BR>
        // <BR>
        if ((l_datListStartDate != null) && (l_datListEndDate != null))
        {
            l_lisContainers.add(WEB3DateUtility.formatDate(l_datListStartDate, "yyyyMMdd"));
            l_lisContainers.add(WEB3DateUtility.formatDate(l_datListEndDate, "yyyyMMdd"));
        } 

        // 
        // �S�j�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
        // �@@�@@�p�����[�^.�����R�[�h��ArrayList�ɒǉ�����B<BR>
        if (l_strProductCode != null)
        {
            l_lisContainers.add(l_strProductCode);    
        }
              
        // 
        // �T�j�p�����[�^.���i�敪 != null�̏ꍇ�A<BR>
        // �@@�@@���i�R�[�h�A�E�v�R�[�h���ȉ��ɏ]���A<BR>
        // �@@�@@�ォ�珇��ArrayList�ɒǉ�����B<BR>
        if (l_strProductType != null)
        { 
        
            // <BR>
            // �@@�@@[�hB:�����E�M�p�h�̏ꍇ]<BR>
            // �@@�@@�@@�E"10:����"<BR>
            // �@@�@@�@@�E"11:�M�p"<BR>
            if(WEB3TradeHistoryProductDivDef.EQUITY_MARGIN.equals(l_strProductType))
            {
                l_lisContainers.add(WEB3TradeHistoryEquityMarginDef.EQUITY);
                l_lisContainers.add(WEB3TradeHistoryEquityMarginDef.MARGIN);                                    
            }              
            // <BR>
            // �@@�@@[�hC:�����h�̏ꍇ]<BR>
            // �@@�@@�@@�E"10:����"<BR>
            else if(WEB3TradeHistoryProductDivDef.EQUITY.equals(l_strProductType)) 
            {
                l_lisContainers.add(WEB3TradeHistoryEquityDef.EQUITY);             
            }              
            // <BR>
            // �@@�@@[�hD:�M�p�h�̏ꍇ]<BR>
            // �@@�@@�@@�E"11:�M�p"<BR>
            else if(WEB3TradeHistoryProductDivDef.MARGIN.equals(l_strProductType)) 
            {
                l_lisContainers.add(WEB3TradeHistoryMarginDef.MARGIN);               
            }       
            // <BR>
            // �@@�@@[�hE:�敨�E�I�v�V�����h�̏ꍇ]<BR>
            // �@@�@@�@@�E"50:�����敨"<BR>
            // �@@�@@�@@�E"51:�����I�v�V����"<BR>
            // �@@�@@�@@�E"52:���敨"<BR>
            // �@@�@@�@@�E"53:���敨�I�v�V����"<BR>
            // �@@�@@�@@�E"54:�X���I�v�V����"<BR>
            // �@@�@@�@@�E"55:�C�O�敨"<BR>
            // �@@�@@�@@�E"56:�C�O�敨�I�v�V����"<BR>
            // �@@�@@�@@�E"57:�����I�v�V����"<BR>
            else if(WEB3TradeHistoryProductDivDef.FUTURES_OPTION.equals(l_strProductType))
            {
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.STOCK_FUTURES);
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.STOCK_OPTION);
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.BOND_FUTURES);
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.BOND_FUTURES_OPTION); 
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.STORE_OPTION);
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.FOREIGN_FUTURES);
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.FOREIGN_FUTURES_OPTION);
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.STOCK_BOND_OPTION);                                                                     
            }                       
            // <BR>
            // �@@�@@[�hF:�敨�h�̏ꍇ]<BR>
            // �@@�@@�@@�E"50:�����敨"<BR>
            // �@@�@@�@@�E"52:���敨"<BR>
            // �@@�@@�@@�E"55:�C�O�敨"<BR>
            else if(WEB3TradeHistoryProductDivDef.FUTURES.equals(l_strProductType))
            {
                l_lisContainers.add(WEB3TradeHistoryFuturesDef.STOCK_FUTURES);
                l_lisContainers.add(WEB3TradeHistoryFuturesDef.BOND_FUTURES);
                l_lisContainers.add(WEB3TradeHistoryFuturesDef.FOREIGN_FUTURES);        
            }                     
            // <BR>
            // �@@�@@[�hG:�I�v�V�����h�̏ꍇ]<BR>
            // �@@�@@�@@�E"51:�����I�v�V����"<BR>
            // �@@�@@�@@�E"53:���敨�I�v�V����"<BR>
            // �@@�@@�@@�E"54:�X���I�v�V����"<BR>
            // �@@�@@�@@�E"56:�C�O�敨�I�v�V����"<BR>
            // �@@�@@�@@�E"57:�����I�v�V����"<BR>
            else if(WEB3TradeHistoryProductDivDef.OPTION.equals(l_strProductType))
            {
                l_lisContainers.add(WEB3TradeHistoryOptionDef.STOCK_OPTION); 
                l_lisContainers.add(WEB3TradeHistoryOptionDef.BOND_FUTURES_OPTION);
                l_lisContainers.add(WEB3TradeHistoryOptionDef.STORE_OPTION);
                l_lisContainers.add(WEB3TradeHistoryOptionDef.FOREIGN_FUTURES_OPTION);
                l_lisContainers.add(WEB3TradeHistoryOptionDef.STOCK_BOND_OPTION);                                                                     
      
            }              
            // <BR>
            // �@@�@@[�hH:���M�E�ݓ��h�̏ꍇ]<BR>
            // �@@�@@�@@�E"20:�������M"<BR>
            // �@@�@@�@@�E"21:�O�����M"<BR>
            // �@@�@@�@@�E"22:GP"<BR>
            // �@@�@@�@@�E"23:MRF"<BR>
            // �@@�@@�@@�E"00:MMF"<BR>
            // �@@�@@�@@�E"D102:���t"<BR>
            // �@@�@@�@@�E"D108:���t"<BR>
            // �@@�@@�@@�E"A3:�U��"<BR>
            else if(WEB3TradeHistoryProductDivDef.MUTUAL_FUND_RUITO.equals(l_strProductType))
            {
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_DOMESTIC);
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_FOREIGN);
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.GP);
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.MRF);
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.MMF);
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.SELL);
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.BUY);
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.TRANSFER);
            }
            // <BR>
            // �@@�@@[�hI:���o���h �܂��� �hJ:���n�v�Łh�̏ꍇ]<BR>
            // �@@�@@�@@�E"99:���K"<BR>
            // �@@�@@�@@�E"1079:���K((����)���n�v�Ŋҕt��)"<BR>
            // �@@�@@�@@�E"1080:���K((����)���n�v�Œ���)"<BR>
            // �@@�@@�@@�E"1082:���K(������n�v�Œ��� ����)"<BR>
            // �@@�@@�@@�E"1083:���K(������n�v�Œ��� �n����)"<BR>
            // �@@�@@�@@�E"1084:���K(������n�v�Ŋҕt ����)"<BR>
            // �@@�@@�@@�E"1085:���K(������n�v�Ŋҕt �n����)"<BR>
            else if((WEB3TradeHistoryProductDivDef.AIO.equals(l_strProductType)) || (WEB3TradeHistoryProductDivDef.CPITAL_GAIN_TAX.equals(l_strProductType)))
            {
                l_lisContainers.add(WEB3TradeHistoryCpitalGainTaxDef.CASH);
                l_lisContainers.add(WEB3TradeHistoryCpitalGainTaxDef.CASH_CPITAL_GAIN_TAX_RETURN);
                l_lisContainers.add(WEB3TradeHistoryCpitalGainTaxDef.CASH_CPITAL_GAIN_TAX_COLLECTION);
                l_lisContainers.add(WEB3TradeHistoryCpitalGainTaxDef.CASH_CPITAL_GAIN_TAX_COLLECTION_NATION); 
                l_lisContainers.add(WEB3TradeHistoryCpitalGainTaxDef.CASH_CPITAL_GAIN_TAX_COLLECTION_REGION);
                l_lisContainers.add(WEB3TradeHistoryCpitalGainTaxDef.CASH_CPITAL_GAIN_TAX_RETURN_NATION);
                l_lisContainers.add(WEB3TradeHistoryCpitalGainTaxDef.CASH_CPITAL_GAIN_TAX_RETURN_REGION);
                 
            }
			// <BR>
			// �@@�@@[�hK:�O�������h�̏ꍇ]<BR>
			// �@@�@@�@@�E"40:�O������"<BR>
			else if(WEB3TradeHistoryProductDivDef.FOREIGN.equals(l_strProductType)) 
			{
				l_lisContainers.add(WEB3TradeHistoryForeignDef.FOREIGN);               
			}
            //�@@�@@[�hL:���h�̏ꍇ] 
            //�@@�E"30:������" 
            //�@@�E"60:�O����" 
            else if (WEB3TradeHistoryProductDivDef.BOND.equals((l_strProductType)))
            {
                l_lisContainers.add(WEB3TradeHistoryBondDef.DOMESTIC_BOND);
                l_lisContainers.add(WEB3TradeHistoryBondDef.FOREIGN_BOND);
            }
        } 
        
        //�U�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
        String[] l_strQueryDataContainers = new String[l_lisContainers.size()];
        l_lisContainers.toArray(l_strQueryDataContainers);

        log.exiting(STR_METHOD_NAME);         
        return l_strQueryDataContainers;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�������쐬���A�ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�P�|�P�j�\�[�g�L�[.�L�[���ڂƃ\�[�g�L�[.�����^�~���̑g�ݍ��킹<BR>
     * �@@�@@�@@�@@�@@�@@���\�[�g�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�L�[���ځ@@�@@�@@�@@�@@�@@�\�[�g����<BR>
     * �@@�@@�@@�@@�@@�@@---------�@@�@@�@@�@@�@@----------<BR>
     *             �E"��n��"�@@�@@���@@�@@"delivery_date"<BR>
     * �@@�@@�@@�@@�@@�@@�E"����"�@@�@@���@@�@@"exec_date"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����^�~���@@�@@�@@�@@�\�[�g����<BR>
     * �@@�@@�@@�@@�@@�@@---------�@@�@@�@@�@@�@@----------<BR>
     * �@@�@@�@@�@@�@@�@@�E"A:����"�@@�@@���@@�@@"asc"<BR>
     * �@@�@@�@@�@@�@@�@@�E"D:�~��"�@@�@@���@@�@@"desc"<BR>
     * <BR>
     * �Q�j���i�R�[�h�A�����R�[�h�A��n���z���\�[�g�����ɒǉ�����B<BR>
     * <BR>
     * �@@�\�[�g���� += "commodity_code asc "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@+ ", product_code asc "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@+ ", net_amount desc "<BR>
     * <BR>
     * �R�j�쐬�����\�[�g������ԋp����B<BR>
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * ��������\�[�g�L�[�I�u�W�F�N�g<BR>
     * @@return String
     * @@roseuid 413C30E102D8
     */
    protected String createSortCond(WEB3HistorySortKeyUnit[] l_sortKey) 
    {
        final String STR_METHOD_NAME = " createSortCond(WEB3HistorySortKeyUnit[] l_sortKey) "; 
        
        log.entering(STR_METHOD_NAME); 
        
        StringBuffer l_strSortCond = new StringBuffer();
        
        //
        // �P�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        // �@@�@@�P�|�P�j�\�[�g�L�[.�L�[���ڂƃ\�[�g�L�[.�����^�~���̑g�ݍ��킹
        // �@@�@@�@@�@@�@@�@@���\�[�g�����ɒǉ�����B
        int l_intSortKeyLength = l_sortKey.length; 
        for (int i = 0; i < l_intSortKeyLength; i ++)
        {
            if ( WEB3HistoryKeyItemDef.DELIVERY_DATE.equals(l_sortKey[i].keyItem))
            {
                if (l_strSortCond.length() != 0) 
                {
                    l_strSortCond.append(", ");
                }
                l_strSortCond.append("delivery_date");
            } 
            else if (WEB3HistoryKeyItemDef.EXEC_DATE.equals(l_sortKey[i].keyItem))
            {
                if (l_strSortCond.length() != 0) 
                {
                    l_strSortCond.append(", ");
                }
                l_strSortCond.append("exec_date");        
            }
            else
            {
                continue;
            }
                       
            if(WEB3AscDescDef.ASC.equals(l_sortKey[i].ascDesc))
            {
                 l_strSortCond.append( " asc");
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKey[i].ascDesc))
            {
                l_strSortCond.append(" desc");
            }   
        }    
        //
        // �Q�j���i�R�[�h�A�����R�[�h�A��n���z���\�[�g�����ɒǉ�����B
        // <BR>
        // �@@�\�[�g���� += "commodity_code asc "
        // �@@�@@�@@�@@�@@�@@�@@�@@�@@+ ", product_code asc "
        // �@@�@@�@@�@@�@@�@@�@@�@@�@@+ ", net_amount desc "
        if (l_strSortCond.length() == 0)
        {
            l_strSortCond.append("commodity_code asc "
                + ", product_code asc "
                + ", net_amount desc ");
        } 
        else
        {
            l_strSortCond.append(", commodity_code asc "
                + ", product_code asc "
                + ", net_amount desc ");        
        
        }  
        //* �R�j�쐬�����\�[�g������ԋp����B 
        log.exiting(STR_METHOD_NAME);  
        return l_strSortCond.toString();
    }
    
    /**
     * (get�����c��)<BR>
     * �����̏����ɊY����������c��(��n��c���̑��a)��ԋp����B<BR>
     * <BR>
     * �P�j��������f�[�^�}�l�[�W��.get�ڋq����c�������ꗗ()���R�[������B<BR>
     * <BR>
     * �@@[get�ڋq����c�������ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�،���ЃR�[�h�F�@@�p�����[�^.�ڋq.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�F�@@�p�����[�^.�ڋq.���X�R�[�h<BR>
     * �@@�����R�[�h�F�@@�p�����[�^.�ڋq.getAccountCode()<BR>
     * �@@��n���F�@@�p�����[�^.��n��<BR>
     * <BR>
     * �@@null���ԋp���ꂽ�ꍇ�́Anull��ԋp���A�I������B<BR>
     * <BR>
     * �Q�j�P�j�̃��\�b�h�̖߂�l(=List)�Ɋi�[����Ă���ڋq����c������Params<BR>
     * �@@�́u��c�v���ڂ̒l�𑍘a����B<BR>
     * <BR>
     * �R�j���a�����l�𕶎���ϊ����ĕԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 413C308E02DA
     */
    protected String getAccountBalance(WEB3GentradeMainAccount l_mainAccount, Date l_datDeliveryDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccountBalance(WEB3GentradeMainAccount l_mainAccount, Date l_datDeliveryDate)"; 
        
        log.entering(STR_METHOD_NAME);   
              
        // �P�j��������f�[�^�}�l�[�W��.get�ڋq����c�������ꗗ()���R�[������B<BR>        
        List l_lisRecords = WEB3HistoryTradeHistoryDataManager.getTransactionHistoryList(l_mainAccount.getInstitution().getInstitutionCode(), 
            l_mainAccount.getBranch().getBranchCode(), 
            l_mainAccount.getAccountCode().substring(0, 6), 
            l_datDeliveryDate);
        
        //null���ԋp���ꂽ�ꍇ�́Anull��ԋp���A�I������B
        if (l_lisRecords == null)
        {
            return null;
        }
         
        // �Q�j�P�j�̃��\�b�h�̖߂�l(=List)�Ɋi�[����Ă���ڋq����c������Params<BR>
        // �@@�́u��c�v���ڂ̒l�𑍘a����B<BR>
        double l_dblTotalConfirmedBalance = 0;
        
        if (l_lisRecords != null)
        {
            int l_intSize = l_lisRecords.size();       
            for (int i = 0;i < l_intSize;i++)
            {
                l_dblTotalConfirmedBalance += ((TransactionHistoryParams)l_lisRecords.get(i)).getConfirmedBalance(); 
            }
        }
        // �R�j���a�����l�𕶎���ϊ����ĕԋp����B<BR>
        String l_strTotalConfirmedBalance = WEB3StringTypeUtility.formatNumber(l_dblTotalConfirmedBalance);

        log.exiting(STR_METHOD_NAME); 
        return l_strTotalConfirmedBalance;
    }
    
    /**
     * (create����������)<BR>
     * �����̎������Params��������������쐬���A�ԋp����B<BR>
     * <BR>
     * �P�j����������C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j���M�^�C�v��ێ�����ׂ̕ϐ�wk���M�^�C�v�iString�^�@@�����l�Fnull�j���쐬����B<BR>
     * <BR>
     * �R�j�P�j�ɂĐ��������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     *   �������ID = �p�����[�^.�������Params.�������ID<BR>
     * �@@���� = �p�����[�^.�������Params.����<BR>
     * �@@���i�R�[�h = �p�����[�^.�������Params.���i�R�[�h<BR>
     * �@@�ٍϋ敪 = �p�����[�^.�������Params.�ٍϋ敪<BR>
     * �@@�����R�[�h = �p�����[�^.�������Params.�����R�[�h<BR>
     * <BR>
     * �@@[���M�̏ꍇ�̂݁A�������擾��𔻒f����]<BR>
     * �@@���M�i���i�R�[�h = "20" �܂��� "21")�̏ꍇ�A�ȉ��̏������s���B<BR>
     * �@@ �iget���X�v���t�@@�����X�i�ڋq.getBranch()�j != 1)�̏ꍇ�A<BR>
     * �@@�@@�@@�@@������ = �p�����[�^.�������Params.�����E�v��<BR>
     * �@@�@@���̑��̏ꍇ�A<BR>
     * �@@�@@�@@�@@get���M�����}�X�^�[�i�ڋq.getBranch().getInstitutionCode(),�p�����[�^.�������Params.�����R�[�h�j���s���B<BR>
     *         ������ = ���M�����}�X�^�[.���M������ <BR>
     * �@@�@@�@@�@@wk���M�^�C�v = ���M�����}�X�^�[.���M�^�C�v <BR>�@@�@@
     * �@@�@@�@@ �i���M�����}�X�^�[ = null�j�̏ꍇ�A������ = �p�����[�^.�������Params.�����E�E�v��<BR>
     * �@@���M�ȊO�̏ꍇ�A������ = �p�����[�^.�������Params.�����E�E�v��<BR>
     * <BR>
     * �@@�����敪 = �p�����[�^.�������Params.�����敪<BR>
     * �@@�|��E�v�� =wk���M�^�C�v=0 && �������Params.�o���敪 = 2(��) && �������Params.����R�[�h=11�̏ꍇ�A"����"���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@��L�ȊO�̏ꍇ�A�p�����[�^.�������Params.�|��E�v�����Z�b�g����B<BR> �@@�@@
     * �@@���� = �p�����[�^.�������Params.��������<BR>
     * �@@���ʒP�� = �p�����[�^.�������Params.���ʒP��<BR>
     * �@@�P�� = �p�����[�^.�������Params.���P��<BR>
     *   �����敪 = �p�����[�^.�������Params.�����敪<BR>
     * �@@��n���z = �p�����[�^.�������Params.��n���z<BR>
     * �@@���n���v = �p�����[�^.�������Params.���n���v<BR>
     *             ��null�̏ꍇ�Anull���Z�b�g�B<BR>
     * �@@���׊Ǘ��ԍ� = �p�����[�^.�������Params.���׊Ǘ��ԍ�<BR>
     * �@@�o���敪 = �p�����[�^.�������Params.�o���敪<BR>                                                                                                                        
     * �@@����R�[�h = �p�����[�^.�������Params.����R�[�h<BR>                                                                                                                      
     * �@@�E�v�R�[�h = �p�����[�^.�������Params.�E�v�R�[�h<BR>
     * �@@�ʉݒP�� = �p�����[�^.�������Params.�ʉݒP��<BR>     
     * �@@���ϋ敪 = �p�����[�^.�������Params.���ϋ敪<BR>                                                                                                                   
     * <BR>
     * �S�jthis.is�뉿�P�����׃����N()���R�[�����A���ʂ�<BR>
     * �@@������������������.�뉿�P�����׃����N�t���O�ɃZ�b�g����B<BR>
     * <BR>
     * �@@[is�뉿�P�����׃����N()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�������Params�F�@@�p�����[�^.�������Params<BR>
     * <BR>
     * �T�j�v���p�e�B�Z�b�g��������������C���X�^���X��ԋp����B<BR>
     * @@param l_tradeHistoryParams - (�������Params)<BR>
     * �������Params<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistoryTradeHistoryUnit
     * @@throws WEB3BaseException
     * @@roseuid 413D225A0350
     */
    protected WEB3HistoryTradeHistoryUnit createTradeHistoryUnit(TradeHistoryParams l_tradeHistoryParams)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createTradeHistoryUnit(TradeHistoryParams l_tradeHistoryParams)";
        log.entering(STR_METHOD_NAME); 

        // �P�j����������C���X�^���X�𐶐�����B
        WEB3HistoryTradeHistoryUnit l_web3HistoryTradeHistoryUnit = new WEB3HistoryTradeHistoryUnit();
        
        //�Q�j���M�^�C�v��ێ�����ׂ̕ϐ�wk���M�^�C�v�iString�^�@@�����l�Fnull�j���쐬����B
        String l_strWkMutualfundType = null;
        
        // �R�j�P�j�ɂĐ��������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
        //   �������ID = �p�����[�^.�������Params.�������ID
        l_web3HistoryTradeHistoryUnit.tradeHistoryId = WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getTradeHistoryId());
        
        // �@@���� = �p�����[�^.�������Params.����<BR>
        l_web3HistoryTradeHistoryUnit.execDate = l_tradeHistoryParams.getExecDate();
        
        // �@@���i�R�[�h = �p�����[�^.�������Params.���i�R�[�h
        l_web3HistoryTradeHistoryUnit.commodityCode = l_tradeHistoryParams.getCommodityCode();
        
        // �@@�ٍϋ敪 = �p�����[�^.�������Params.�ٍϋ敪
        l_web3HistoryTradeHistoryUnit.repaymentDiv = l_tradeHistoryParams.getRepaymentType();
        
        // �@@�����R�[�h = �p�����[�^.�������Params.�����R�[�h
        l_web3HistoryTradeHistoryUnit.productCode = l_tradeHistoryParams.getProductCode();
        
		// [���M�̏ꍇ�̂݁A�������擾��𔻒f����]
		//    ���M�i���i�R�[�h = "20" �܂��� "21")�̏ꍇ�A�ȉ��̏������s���B
		//      �iget���X�v���t�@@�����X�i�ڋq.getBranch()�j != 1)�̏ꍇ�A
		//�@@�@@       ������ = �p�����[�^.�������Params.�����E�v��
		//       ���̑��̏ꍇ�A
		//           get���M�����}�X�^�[�i�ڋq.getBranch().getInstitutionCode(),�p�����[�^.�������Params.�����R�[�h�j���s���B 
        //           ������ = ���M�����}�X�^�[.���M������ 
        //		     wk���M�^�C�v = ���M�����}�X�^�[.���M�^�C�v				
		//�@@        �i������ = null�j�̏ꍇ�A������ = �p�����[�^.�������Params.�����E�E�v��
		//    ���M�ȊO�̏ꍇ�A������ = �p�����[�^.�������Params.�����E�E�v��
		String l_strProductName = null;
		Integer l_preferences = this.getBranchPreferences(this.getMainAccount().getBranch().getBranchId());

		if (WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_DOMESTIC.equals(l_tradeHistoryParams.getCommodityCode())
			|| WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_FOREIGN.equals(l_tradeHistoryParams.getCommodityCode()))
		{
			if (l_preferences != null 
				&& l_preferences.intValue() == 1)
			{
				Object l_objMutualFundProduct = 
					this.getFundProductMaster(
						this.getMainAccount().getBranch().getInstitution().getInstitutionCode(),
						l_tradeHistoryParams.getProductCode());
				
				if (l_objMutualFundProduct != null)
				{
					MutualFundProductRow l_fundProductRow = (MutualFundProductRow)l_objMutualFundProduct;
					MutualFundProductParams l_mutualFundProductParams = new MutualFundProductParams(l_fundProductRow);
					
					//������ = ���M�����}�X�^�[.���M������
					l_strProductName = l_mutualFundProductParams.getStandardName();

					//wk���M�^�C�v = ���M�����}�X�^�[.���M�^�C�v
					l_strWkMutualfundType = l_mutualFundProductParams.getFundType().intValue() + "";
				}
				
				if (l_strProductName == null)
				{
					l_strProductName = l_tradeHistoryParams.getProductName();
				}
			}
			else
			{
				l_strProductName = l_tradeHistoryParams.getProductName();
			}
		}
		else
		{
			l_strProductName = l_tradeHistoryParams.getProductName();
		}
		l_web3HistoryTradeHistoryUnit.productName = l_strProductName;
        
        // �@@�����敪 = �p�����[�^.�������Params.�����敪
        l_web3HistoryTradeHistoryUnit.taxType = l_tradeHistoryParams.getAccountDiv();
        
        // �@@�|��E�v�� = wk���M�^�C�v=0 && �������Params.����R�[�h = 11�̏ꍇ�A"����"���Z�b�g����B
        //wk���M�^�C�v0�ȊO�̏ꍇ�A�p�����[�^.�������Params.�|��E�v�����Z�b�g����B 

        //2006/05/01 SCS��؏C���@@START
//        if ("0".equals(l_strWkMutualfundType))
        if ("0".equals(l_strWkMutualfundType) &&
            "2".equals(l_tradeHistoryParams.getIoDiv()) &&
            WEB3TradeHistoryTradeCodeDef.TRADE_CODE_11.equals(l_tradeHistoryParams.getTradeCode()))
        //2006/05/01 SCS��؏C���@@END

        {
            l_web3HistoryTradeHistoryUnit.remarkName = "����";
        }
        else
        {
            l_web3HistoryTradeHistoryUnit.remarkName = l_tradeHistoryParams.getRemarkName();
        }
        
        // �@@���� = �p�����[�^.�������Params.��������
        l_web3HistoryTradeHistoryUnit.quantity = WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getQuantity());
        
        // �@@���ʒP�� = �p�����[�^.�������Params.���ʒP��
        l_web3HistoryTradeHistoryUnit.quantityUnit = l_tradeHistoryParams.getQuantityType();
        
        // �@@�P�� = �p�����[�^.�������Params.���P��
        l_web3HistoryTradeHistoryUnit.price = WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getPrice());
        
        //   �����敪 = �p�����[�^.�������Params.�����敪
        l_web3HistoryTradeHistoryUnit.historyDealingType = l_tradeHistoryParams.getBuySellDiv();
        
        // �@@��n���z = �p�����[�^.�������Params.��n���z
        l_web3HistoryTradeHistoryUnit.deliveryAmount = WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getNetAmount());
        
        // �@@���n���v = �p�����[�^.�������Params.���n���v
        if (l_tradeHistoryParams.getCapitalGainIsNull())
        {
            l_web3HistoryTradeHistoryUnit.capitalProfitLoss = null;
        }
        else
        {
            l_web3HistoryTradeHistoryUnit.capitalProfitLoss = WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getCapitalGain());
        }
        
        // �@@���׊Ǘ��ԍ� = �p�����[�^.�������Params.���׊Ǘ��ԍ�
        if (l_tradeHistoryParams.getDetailsManagementNoIsNull() == true)                                                                                                                        
        {                                                                                                                       
            l_web3HistoryTradeHistoryUnit.detailsManageNo = null;                                                                                                                   
        }                                                                                                                       
        else                                                                                                                        
        {                                                                                                                       
            l_web3HistoryTradeHistoryUnit.detailsManageNo = "" + l_tradeHistoryParams.getDetailsManagementNo();                                                                                                                 
        }
                
        // �@@�o���敪 = �p�����[�^.�������Params.�o���敪    
        l_web3HistoryTradeHistoryUnit.ioDiv = l_tradeHistoryParams.getIoDiv();
                                                                                                                            
        //   ����R�[�h = �p�����[�^.�������Params.����R�[�h
        l_web3HistoryTradeHistoryUnit.tradeCode = l_tradeHistoryParams.getTradeCode();
                                                                                                                                
        //   �E�v�R�[�h = �p�����[�^.�������Params.�E�v�R�[�h     
        l_web3HistoryTradeHistoryUnit.remarkCode = l_tradeHistoryParams.getRemarkCode();
        
        //  �ʉݒP�� = �p�����[�^.�������Params.�ʉݒP��
        l_web3HistoryTradeHistoryUnit.monetaryUnit = l_tradeHistoryParams.getMonetaryUnit();
        
        //  ���ϋ敪 = �p�����[�^.�������Params.���ϋ敪 
        l_web3HistoryTradeHistoryUnit.settleDiv = l_tradeHistoryParams.getPaymentDiv();
        
        // �S�jthis.is�뉿�P�����׃����N()���R�[�����A���ʂ�<BR>
        // �@@������������������.�뉿�P�����׃����N�t���O�ɃZ�b�g����B
        l_web3HistoryTradeHistoryUnit.bookValueLink = this.isBookValueLink(l_tradeHistoryParams);
        
        //     �T�j�v���p�e�B�Z�b�g��������������C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);       
        return l_web3HistoryTradeHistoryUnit;
    }

    /**
     * (create��n���ʎc�����)<BR>
     * �����̎�n���ɊY�������n���ʎc�������쐬���A�ԋp����B<BR>
     * <BR>
     * �P�j��n���ʎc�����C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�jthis.get�����c��()���R�[������B<BR>
     * <BR>
     * �@@[get�����c��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�ڋq�F�@@�p�����[�^.�ڋq<BR>
     * �@@��n���F�@@�p�����[�^.��n��<BR>
     * <BR>
     * �R�j��n���ʎc�����C���X�^���X�Ƀv���p�e�B���Z�b�g����B<BR>
     *   �ڋq����c������ID = �ڋq����c������Row(*1).�ڋq����c������ID<BR>
     * �@@��n�� = �p�����[�^.��n��<BR>
     * �@@�����c�� = �Q�j�ɂĎ擾���������c��<BR>
     * �@@���v���׃����N�t���O = false<BR>
     * <BR>
     * 4�j�v���p�e�B�Z�b�g������n���ʎc�����C���X�^���X��ԋp����B<BR>
     * <BR>
     * (*1)�ڋq����c������Dao.findRowBy...()���\�b�h�ɂĎ擾����B<BR>
     * �@@�@@�����\�b�h���������ׁA�ȗ��B<BR>
     *�@@  [findRowBy...()�ɃZ�b�g����p�����[�^]<BR>
     *�@@�@@    �،���ЃR�[�h�F�@@�p�����[�^.�ڋq.�،���ЃR�[�h<BR>
     *�@@�@@    ���X�R�[�h�F�@@�p�����[�^.�ڋq.���X�R�[�h<BR>
     *�@@�@@    �ڋq�R�[�h�F�@@�p�����[�^.�ڋq.�����R�[�h<BR>
     *�@@�@@    ��n���F�@@�p�����[�^.��n��<BR>
     *�@@�@@    �a��敪�F�@@"�a���"<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@param l_tradeHistoryUnits - (����������ꗗ)<BR>
     * ����������I�u�W�F�N�g�̔z��<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistoryDailyBalanceUnit
     * @@throws WEB3BaseException
     * @@roseuid 413D362300B2
     */
    protected WEB3HistoryDailyBalanceUnit createDailyBalanceUnit(WEB3GentradeMainAccount l_mainAccount, Date l_datDeliveryDate, WEB3HistoryTradeHistoryUnit[] l_tradeHistoryUnits) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createDailyBalanceUnit(WEB3GentradeMainAccount l_mainAccount, Date l_deliveryDate, WEB3HistoryTradeHistoryUnit[] l_tradeHistoryUnits)"; 
        log.entering(STR_METHOD_NAME); 
        
        // �P�j��n���ʎc�����C���X�^���X�𐶐�����B
        WEB3HistoryDailyBalanceUnit l_web3HistoryDailyBalanceUnit = new WEB3HistoryDailyBalanceUnit();
        
        //(*1)�ڋq����c������Dao.findRowBy...()���\�b�h�ɂĎ擾����B
        //  �����\�b�h���������ׁA�ȗ��B
        //  [findRowBy...()�ɃZ�b�g����p�����[�^]
        //    �،���ЃR�[�h�F�@@�p�����[�^.�ڋq.�،���ЃR�[�h
        //    ���X�R�[�h�F�@@�p�����[�^.�ڋq.���X�R�[�h
        //    �ڋq�R�[�h�F�@@�p�����[�^.�ڋq.�����R�[�h
        //    ��n���F�@@�p�����[�^.��n��
        //    �a��敪�F�@@"�a���"
        TransactionHistoryRow l_transactionHistoryRow = null;
        try
        {
            l_transactionHistoryRow = 
                TransactionHistoryDao.findRowByInstitutionCodeBranchCodeAccountCodeDeliveryDateDepositMarginDiv(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    l_mainAccount.getAccountCode().substring(0, 6),
                    new Timestamp(l_datDeliveryDate.getTime()),
                    WEB3DepositMarginDivDef.FROM_DEPOSIT);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // �Q�jthis.get�����c��()���R�[������B
        String l_strAccountBalance = this.getAccountBalance(l_mainAccount, l_datDeliveryDate);
                
        // �S�j��n���ʎc�����C���X�^���X�Ƀv���p�e�B���Z�b�g����B
        //   �ڋq����c������ID = �ڋq����c������Row(*1).�ڋq����c������ID
        // �@@��n�� = �p�����[�^.��n��
        // �@@�����c�� = �Q�j�ɂĎ擾���������c��
        // �@@���v���׃����N�t���O = false
        if (l_transactionHistoryRow != null)
        {
            l_web3HistoryDailyBalanceUnit.transactionHistoryId = 
                WEB3StringTypeUtility.formatNumber(
                    l_transactionHistoryRow.getTransactionHistoryId());
        }
        else
        {
            l_web3HistoryDailyBalanceUnit.transactionHistoryId = null;
        }

        l_web3HistoryDailyBalanceUnit.deliveryDate = WEB3DateUtility.toDay(l_datDeliveryDate);
        l_web3HistoryDailyBalanceUnit.accountBalance = l_strAccountBalance;
        l_web3HistoryDailyBalanceUnit.profitLossLink = false;
        
        // �T�j�v���p�e�B�Z�b�g������n���ʎc�����C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);   
        return l_web3HistoryDailyBalanceUnit;
    }
    
    /**
     * (is�뉿�P�����׃����N)<BR>
     * �뉿�P�����׃����N���s�������ʂ���B<BR>
     * <BR>
     * �P�j�ȉ��̏���(�@@�`�D)��S�Ė������ꍇ��true�A�ȊO��false��ԋp����B<BR>
     * <BR>
     * �@@�@@�O�����c�Ɠ� <= �p�����[�^.�������Params.��n��<BR>
     * �@@�A�p�����[�^.�������Params.���� <=�@@�O���c�Ɠ�<BR>
     * �@@�B�p�����[�^.�������Params.�����敪 == �h1�F����h<BR>
     * �@@�C�p�����[�^.�������Params.���i�R�[�h���ȉ��̂��Âꂩ�ɊY���B<BR>
     * �@@�@@�E�h10�F���������h<BR>
     * �@@�@@�E�h40�F�O�������h<BR>
     * �@@�@@�E�h11�F�M�p�h ���� ����R�[�h���h53�F�����E���n�h<BR>
     * �@@�@@�E�h20�F�������M�h<BR>
     * �@@�@@�E�h21�F�O�����M�h<BR>
     * �@@�D�p�����[�^.�������Params.�o���敪 == �h1�F�o�h<BR>
     * @@param l_tradeHistoryParams - (�������Params)<BR>
     * �������Params<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 413D628B0007
     */
    protected boolean isBookValueLink(TradeHistoryParams l_tradeHistoryParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isBookValueLink(TradeHistoryParams l_tradeHistoryParams)"; 
        log.entering(STR_METHOD_NAME);  
        
        boolean l_blBookValueLink;
        
        //�O�����c�Ɠ� l_gentradeBizDate.roll(0)
        Calendar l_calendar = new GregorianCalendar();
        l_calendar.setTime(GtlUtils.getSystemTimestamp());
        
        l_calendar.add(Calendar.MONTH, -1);
        
        l_calendar.set(Calendar.DATE,l_calendar.getActualMinimum(Calendar.DATE));
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObj = 
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

       SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
       //�،���ЁF �iget�⏕����()�̖߂�l�j.getInstitutuin()�̖߂�l
       WEB3GentradeInstitution l_institution = 
	       (WEB3GentradeInstitution) l_subAccount.getInstitution(); 
       String l_strInstitutionCode = l_institution.getInstitutionCode();

       Market l_market = null;
		
       Date l_preBizDate = null;
			   
       //�O�������̏ꍇ
       if( WEB3TradeHistoryForeignDef.FOREIGN.equals(l_tradeHistoryParams.getCommodityCode()) )
       {
       	    //�����R�[�hNull�̏ꍇ�Afalse��ԋp
       	    if ( l_tradeHistoryParams.getProductCode() == null )
       	    {
				l_blBookValueLink = false;
				return l_blBookValueLink;       	    	
       	    }else {
				//�s��R�[�h
				String l_strMarketCode = null;
				
				//�O������            
				WEB3FeqProduct l_feqProduct = null;
				
				WEB3FeqOrderManager l_feqOrderManager = 
					(WEB3FeqOrderManager)l_finApp.getTradingModule(
						ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        		
        		try{
					//validate�O������(�،����, String)
					l_feqProduct =
						(WEB3FeqProduct) l_feqOrderManager.validateFeqProduct(
							l_institution, 
							l_tradeHistoryParams.getProductCode());
				        
					//get�s��( )
					WEB3GentradeMarket l_feq_market = l_feqProduct.getMarket();
					l_strMarketCode = l_feq_market.getMarketCode();
					log.debug("�s��R�[�h = " + l_strMarketCode);
                        
					//validate�s��(�s��)
					l_feqOrderManager.validateMarket(l_feq_market);
				
					WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(
				    	new Timestamp(l_calendar.getTime().getTime()));
				
					//�O�����c�Ɠ��̎擾
					while (!WEB3FeqDateUtility.isFeqBizDate(new Timestamp(l_calendar.getTime().getTime())))
					{
						l_calendar.add(Calendar.DATE, 1);
					}
					
					//�O���c�Ɠ��̎擾
					l_preBizDate = l_gentradeBizDate.calcFeqBizDate(
						l_strInstitutionCode, l_strMarketCode, GtlUtils.getSystemTimestamp(), -1);
				}
				catch (WEB3BaseException l_ex)
				{
					l_blBookValueLink = false;
					return l_blBookValueLink;
				}
       	    }
        }else
        //�������̏ꍇ
        {
			//�O�����c�Ɠ��̎擾
            while (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
                WEB3GentradeTradingTimeManagement.getBizDateType(
                    new Timestamp(l_calendar.getTime().getTime()))))
            {
                l_calendar.add(Calendar.DATE, 1);
            }
            
  	        //�O���c�Ɠ��̎擾
	        l_preBizDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(-1);
        }

		WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(
			new Timestamp(l_calendar.getTime().getTime()));
                                                
        if (WEB3DateUtility.compareToDay(l_gentradeBizDate.roll(0), l_tradeHistoryParams.getDeliveryDate()) <= 0&&
            WEB3DateUtility.compareToDay(l_tradeHistoryParams.getExecDate(), l_preBizDate) <= 0&&
            WEB3AccountDivDef.SPECIAL.equals(l_tradeHistoryParams.getAccountDiv()) &&
            (WEB3TradeHistoryProductCodeDef.DOMESTIC_STOCK.equals(l_tradeHistoryParams.getCommodityCode()) ||
            WEB3TradeHistoryProductCodeDef.FOREIGN_STOCK.equals(l_tradeHistoryParams.getCommodityCode()) || 
            (WEB3TradeHistoryProductCodeDef.MARGIN.equals(l_tradeHistoryParams.getCommodityCode()) && 
            WEB3TradeHistoryTradeCodeDef.SWAP.equals(l_tradeHistoryParams.getTradeCode())) ||
            WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_DOMESTIC.equals(l_tradeHistoryParams.getCommodityCode()) ||
            WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_FOREIGN.equals(l_tradeHistoryParams.getCommodityCode())) &&
            WEB3IoDivDef.OUTPUT.equals(l_tradeHistoryParams.getIoDiv()))
        {
            l_blBookValueLink = true;
        }
        else
        {
            l_blBookValueLink = false;        
        }
         
        log.exiting(STR_METHOD_NAME);           	
        return l_blBookValueLink;
    }
    
    /**
     * (is���v���׃����N)<BR>
     * ���v���׃����N���s�������ʂ���B<BR>
     * <BR>
     * �P�j�ȉ��̏���(�@@�`�B)��S�Ė������ꍇ��true�A�ȊO��false��ԋp����B<BR>
     * <BR>
     * �@@�@@�p�����[�^.�����c�� != null<BR>
     * �@@�A�O�������c�Ɠ� <= �p�����[�^.��n��<BR>
     * �@@�B�p�����[�^.����������ꗗ�̗v�f�����ȉ��̃`�F�b�N���s���A<BR>
     * �@@�@@�S�Ă̏����𖞂������������񂪈�ł����݂��邱�ƁB<BR>
     * �@@�@@�@@�E���׃f�[�^.���� <= �O���c�Ɠ�<BR>
     * �@@�@@�@@�E���׃f�[�^.�����敪 == "1�F����"<BR>
     * �@@�@@�@@�E(���׃f�[�^.���i�R�[�h == "11�F�M�p" ����<BR>
     * �@@�@@�@@�@@ ���׃f�[�^.�E�v�R�[�h == ("A201" or "A210" or "A214"))<BR>
     * �@@�@@�@@�@@�܂��́A<BR>
     * �@@�@@�@@�@@(���׃f�[�^.���i�R�[�h == ("10�F����" or "30�F������" or 
     * "40�F�O������") ����<BR>
     * �@@�@@�@@�@@�@@���׃f�[�^.�o���敪 == "2�F��")<BR>
     * �@@�@@�@@�@@�܂��́A
     * �@@�@@�@@�@@(���׃f�[�^.���i�R�[�h == ("20�F�������M" or "21�F�O�����M") ����
     *  �@@�@@�@@�@@���׃f�[�^.����R�[�h == "92�F����" ����
     *          ���׃f�[�^.�o���敪 == "2�F��") 
     * �@@�@@�@@�@@�܂��́A<BR>
     * �@@�@@�@@�@@(���׃f�[�^.�o���敪 == "2�F��" ���� <BR>
     * �@@�@@�@@�@@�@@���׃f�[�^.����R�[�h == "A2" ����<BR>
     * �@@�@@�@@�@@�@@���׃f�[�^.�E�v�R�[�h == "1079")<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@param l_strAccountBalance - (�����c��)<BR>
     * ��n��̌����c��<BR>
     * @@param l_tradeHistoryUnits - (����������ꗗ)<BR>
     * ����������I�u�W�F�N�g�̔z��<BR>
     * @@return Boolean
     * @@roseuid 413D488A001C
     */
    protected boolean isProfitLossLink(Date l_datDeliveryDate, String l_strAccountBalance, WEB3HistoryTradeHistoryUnit[] l_tradeHistoryUnits) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " isProfitLossLink(Date l_datDeliveryDate, String l_strAccountBalance, WEB3HistoryTradeHistoryUnit[] l_tradeHistoryUnits) "; 
        log.entering(STR_METHOD_NAME);  
        
        boolean l_blProfitLossLink;
    	  
        //�O�����c�Ɠ� l_gentradeBizDate.roll(0)
        Calendar l_calendar = new GregorianCalendar();
        l_calendar.setTime(GtlUtils.getSystemTimestamp());
        l_calendar.add(Calendar.MONTH, -1);
        l_calendar.set(Calendar.DATE,l_calendar.getActualMinimum(Calendar.DATE));

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObj = 
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
		//�،���ЁF �iget�⏕����()�̖߂�l�j.getInstitutuin()�̖߂�l
		WEB3GentradeInstitution l_institution = 
			(WEB3GentradeInstitution) l_subAccount.getInstitution(); 
		String l_strInstitutionCode = l_institution.getInstitutionCode();              

        //�@@�p�����[�^.�����c�� == null
        if (l_strAccountBalance == null )
        {
	        l_blProfitLossLink = false;
	        return l_blProfitLossLink;
        }        
        	               
        // �@@�B�p�����[�^.����������ꗗ�̗v�f�����ȉ��̃`�F�b�N���s���A
        // �@@�@@�S�Ă̏����𖞂������������񂪈�ł����݂��邱�ƁB
        l_blProfitLossLink = false;
        int l_intSize = l_tradeHistoryUnits.length;
        for (int i = 0; i < l_intSize; i ++ )
        {
			Market l_market = null;
		
			Date l_preBizDate = null;
					
			//�O�������̏ꍇ
			if( WEB3TradeHistoryForeignDef.FOREIGN.equals(l_tradeHistoryUnits[i].commodityCode) )
        	{
				//�����R�[�hNull�̏ꍇ�Afalse��ԋp
				if ( l_tradeHistoryUnits[i].productCode == null )
				{
					l_blProfitLossLink = false;
					return l_blProfitLossLink;
					
				}else{
					//�s��R�[�h
					String l_strMarketCode = null;
				
					//�O������            
					WEB3FeqProduct l_feqProduct = null;
				
					WEB3FeqOrderManager l_feqOrderManager = 
						(WEB3FeqOrderManager)l_finApp.getTradingModule(
							ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        		
        			try {
						//validate�O������(�،����, String)
						l_feqProduct =
							(WEB3FeqProduct) l_feqOrderManager.validateFeqProduct(
								l_institution, 
								l_tradeHistoryUnits[i].productCode);
				        
						//get�s��( )
						WEB3GentradeMarket l_feq_market = l_feqProduct.getMarket();
						l_strMarketCode = l_feq_market.getMarketCode();
						log.debug("�s��R�[�h = " + l_strMarketCode);
                        
						//validate�s��(�s��)
						l_feqOrderManager.validateMarket(l_feq_market);
					
						WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(
							new Timestamp(l_calendar.getTime().getTime()));
					
						//�O�����c�Ɠ��̎擾
						while (!WEB3FeqDateUtility.isFeqBizDate(new Timestamp(l_calendar.getTime().getTime())))
						{
							l_calendar.add(Calendar.DATE, 1);
						}
					
						//�O���c�Ɠ��̎擾
						l_preBizDate = l_gentradeBizDate.calcFeqBizDate(
								l_strInstitutionCode, l_strMarketCode, GtlUtils.getSystemTimestamp(), -1);
					}
					catch (WEB3BaseException l_ex)
					{
						l_blProfitLossLink = false;
						return l_blProfitLossLink;
					}
				}
        	}else
        	//�������̏ꍇ
        	{
				//�O�����c�Ɠ��̎擾
                while (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        new Timestamp(l_calendar.getTime().getTime()))))
                {
                    l_calendar.add(Calendar.DATE, 1);
                }				

                //�O���c�Ɠ��̎擾
				l_preBizDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(-1);
        	}
        	
			WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(
				new Timestamp(l_calendar.getTime().getTime()));
        	
			//�A�O�������c�Ɠ� > �p�����[�^.��n��
			if (WEB3DateUtility.compareToDay(l_gentradeBizDate.roll(0), l_datDeliveryDate) > 0)
			{
				l_blProfitLossLink = false;
				return l_blProfitLossLink;
			}       	
        	
            if(//���׃f�[�^.���� <= �O���c�Ɠ�
                (WEB3DateUtility.compareToDay(l_tradeHistoryUnits[i].execDate, l_preBizDate) <= 0)&&
                //���׃f�[�^.�����敪 == "1�F����"  
                (WEB3AccountDivDef.SPECIAL.equals(l_tradeHistoryUnits[i].taxType))&&
                // �@@�@@�@@�E(���׃f�[�^.���i�R�[�h == "11�F�M�p" ����
                // �@@�@@�@@�@@ ���׃f�[�^.�E�v�R�[�h == ("A201" or "A210" or "A214"))         �܂��́A
                // �@@�@@�@@�@@(���׃f�[�^.���i�R�[�h == ("10�F����" or "30�F������" or 
                //         "40�F�O������") ����
                // �@@�@@�@@�@@�@@���׃f�[�^.�o���敪 == "2�F��")                                    �@@�@@�@@�@@�܂��́A
                //         (���׃f�[�^.���i�R�[�h == ("20�F�������M" or "21�F�O�����M") ���� 
                //          ���׃f�[�^.����R�[�h == "92�F����" ���� 
                //          ���׃f�[�^.�o���敪 == "2�F��") 
                //          �܂��́A 
                // �@@�@@�@@�@@(���׃f�[�^.�o���敪 == "2�F��" ���� 
                // �@@�@@�@@�@@�@@���׃f�[�^.����R�[�h == "A2" ����
                // �@@�@@�@@�@@�@@���׃f�[�^.�E�v�R�[�h == "1079")             
                ((WEB3TradeHistoryProductCodeDef.MARGIN.equals(l_tradeHistoryUnits[i].commodityCode)&&
                  (WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_A201.equals(l_tradeHistoryUnits[i].remarkCode)||
                   WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_A210.equals(l_tradeHistoryUnits[i].remarkCode)||
                   WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_A214.equals(l_tradeHistoryUnits[i].remarkCode)))||               
                ((WEB3TradeHistoryProductCodeDef.EQUITY.equals(l_tradeHistoryUnits[i].commodityCode)||
                   WEB3TradeHistoryProductCodeDef.DOMESTIC_BOND.equals(l_tradeHistoryUnits[i].commodityCode)||
                   WEB3TradeHistoryProductCodeDef.FOREIGN_STOCK.equals(l_tradeHistoryUnits[i].commodityCode))&&
                   WEB3IoDivDef.INPUT.equals(l_tradeHistoryUnits[i].ioDiv))||
                ((WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_DOMESTIC.equals(l_tradeHistoryUnits[i].commodityCode)||
                   WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_FOREIGN.equals(l_tradeHistoryUnits[i].commodityCode))&&
                   WEB3IoDivDef.INPUT.equals(l_tradeHistoryUnits[i].ioDiv))||                
                (WEB3IoDivDef.INPUT.equals(l_tradeHistoryUnits[i].ioDiv)&&
                  WEB3TradeHistoryTradeCodeDef.TRADE_CODE_A2.equals(l_tradeHistoryUnits[i].tradeCode)&&
                  WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_1079.equals(l_tradeHistoryUnits[i].remarkCode)))              
            )
            {
                l_blProfitLossLink = true;
                break;        
            }
            else
            {
                continue;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_blProfitLossLink;    	   
    }

    /**
     * (create�\�[�g����for�_�E�����[�h)<BR>
     * �\�[�g�������쐬���A�ԋp����B<BR>
     * <BR>
     * �P�j��n���i�����j�A�����i�����j�A���i�R�[�h�i�����j�A<BR>
     *  �����R�[�h�i�����j�A��n���z�i�����j���\�[�g�����Ƃ��ĕҏW����B<BR>
     * <BR>
     * �@@�\�[�g����  = ""delivery_date asc"<BR>
     * �@@�@@�@@�@@�@@�@@�@@+ ", exec_date asc"<BR>
     * �@@�@@�@@�@@�@@�@@�@@+ ", commodity_code asc"<BR>
     * �@@�@@�@@�@@�@@�@@�@@+ ", product_code asc"<BR>
     * �@@�@@�@@�@@�@@�@@�@@+ ", net_amount asc"<BR>
     * <BR>
     * �Q�j�쐬�����\�[�g������ԋp����B<BR>
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * ��������\�[�g�L�[�I�u�W�F�N�g<BR>
     * @@return String
     * @@roseuid 413C30E102D8
     */
    protected String createSortCondForDownload() 
    {
        final String STR_METHOD_NAME = " createSortCondForDownload() "; 
        log.entering(STR_METHOD_NAME); 
        
        // �P�j��n���i�����j�A�����i�����j�A���i�R�[�h�i�����j�A
        // �����R�[�h�i�����j�A��n���z�i�����j���\�[�g�����Ƃ��ĕҏW����B
        String l_strSortCond = " delivery_date asc, exec_date asc, commodity_code asc,"
            + " product_code asc, net_amount asc ";

        log.exiting(STR_METHOD_NAME);  
        //�Q�j�쐬�����\�[�g������ԋp����B
        return l_strSortCond;
    }

    /**
     * (get�v���t�@@�����X)<BR>
     * �p�����[�^�Ɏw�肳�ꂽ�ݒ薼�̂̐ݒ�l���V�X�e���v���t�@@�����X�e�[�u������擾����B<BR>
     * <BR>
     * �P�j�V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B<BR>
     * <BR>
     * �@@[�擾����]<BR>
     * �@@���́i���ϐ����j = �p�����[�^.�ݒ薼��<BR>
     * <BR>
     * �Q�j�擾�����V�X�e���v���t�@@�����X�e�[�u���̃��R�[�h�̐ݒ�l��ԋp����B<BR>
     * @@param l_strPreferences - (���́i���ϐ����j)<BR>
     * @@return String
     * @@roseuid 413C30E102D8
     */
    protected String getPreferences(String l_strPreferences) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPreferences(String) "; 
        log.entering(STR_METHOD_NAME); 
        
        //�P�j�V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B
        // �@@[�擾����]
        // �@@���́i���ϐ����j = �p�����[�^.�ݒ薼��
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append(" name = ? ");

        Object[] l_objWhere = {l_strPreferences};

        List l_lisRecords = new ArrayList();
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                SystemPreferencesRow.TYPE,
                l_strWhere.toString(),
                null,
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //�Q�j�擾�����V�X�e���v���t�@@�����X�e�[�u���̃��R�[�h�̐ݒ�l��ԋp����B
        String l_strValue = null;
        if (l_lisRecords.size() != 0)
        {
            SystemPreferencesRow l_preferencesRow = (SystemPreferencesRow)l_lisRecords.get(0);

            l_strValue = l_preferencesRow.getValue();

            //�f�[�^�s�����̏ꍇ
            if (!WEB3StringTypeUtility.isInteger(l_strValue))
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                        + "�f�[�^�s�����G���[�B");
            }
        }

        log.exiting(STR_METHOD_NAME);  
        return l_strValue;
    }
    
	/**
	 * (get���X�v���t�@@�����X)<BR>
	 * �p�����[�^�Ɏw�肳�ꂽ�Y�����X�V�X�e���v���t�@@�����X�e�[�u���̃v���t�@@�����X�̒l��ԋp����B<BR>
	 * <BR>
	 * �P�j���X�V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B<BR>
	 * <BR>
	 * �@@[�擾����]<BR>
	 * �@@���XID = �p�����[�^.���XID<BR>
	 * �@@�v���t�@@�����X�� = �v���t�@@�����X��.�������擾��敪<BR>
	 * �@@�v���t�@@�����X���̘A�� = 1<BR>
	 * <BR>
	 * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A null��ԋp����B<BR>
	 * <BR>
	 * �Q�j�擾�������X�V�X�e���v���t�@@�����X�e�[�u���̃v���t�@@�����X�̒l��ԋp����B<BR>
	 * @@param l_lngBranchId - (���XID)<BR>
	 * @@return Integer
	 * @@roseuid 413C30E102D8
	 */
	protected Integer getBranchPreferences(long l_lngBranchId) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getBranchPreferences(long) "; 
		log.entering(STR_METHOD_NAME); 
        
		//�P�j���X�V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B
		//�@@[�擾����]
		//�@@���XID = �p�����[�^.���XID
		//�@@�v���t�@@�����X�� = �v���t�@@�����X��.�������擾��敪
		//�@@�v���t�@@�����X���̘A�� = 1
		StringBuffer l_strWhere = new StringBuffer();
		l_strWhere.append(" branch_id = ? ");
		l_strWhere.append(" and name = ? ");
		l_strWhere.append(" and name_serial_no = ? ");

		Object[] l_objWhere = {
			new Long(l_lngBranchId),
			WEB3BranchPreferencesNameDef.GETPRODUCTNAME_DIV,
			"1"};

		List l_lisRecords = null;
		try
		{
			QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
			l_lisRecords = l_QueryProcessor.doFindAllQuery(
				BranchPreferencesRow.TYPE,
				l_strWhere.toString(),
				null,
				null,
				l_objWhere);
		}
		catch (DataFindException l_dfe)
		{
			log.error(STR_METHOD_NAME, l_dfe);

			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_dfe.getMessage(),
				l_dfe);
		}
		catch (DataQueryException l_dqe)
		{
			log.error(STR_METHOD_NAME, l_dqe);

			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_dqe.getMessage(),
				l_dqe);
		}
		catch (DataNetworkException l_dne)
		{
			log.error(STR_METHOD_NAME, l_dne);

			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_dne.getMessage(),
				l_dne);
		}

		//�������ʂ��擾�ł��Ȃ������ꍇ�A null��ԋp����B
		//�Q�j�擾�������X�V�X�e���v���t�@@�����X�e�[�u���̃v���t�@@�����X�̒l��ԋp����B
		if (l_lisRecords == null || l_lisRecords.size() == 0)
		{
			log.exiting(STR_METHOD_NAME);  
			return null;
		}
		else
		{
			BranchPreferencesRow l_preferencesRow = (BranchPreferencesRow)l_lisRecords.get(0);

			String l_strValue = l_preferencesRow.getValue();
			if (!WEB3StringTypeUtility.isInteger(l_strValue))
			{
				log.exiting(STR_METHOD_NAME);
				return null;
			}

			log.exiting(STR_METHOD_NAME);
			return new Integer(l_strValue);
		}
	}
    
	/**
	 * (get���M�����}�X�^�[)<BR>
	 * �w�肳�ꂽ�p�����[�^�ɊY�����M�����}�X�^�f�[�^�̃I�u�W�F�N�g��ԋp����B<BR>
	 * <BR>
	 * �P�j���M�����}�X�^����ȉ��̏����Ń��R�[�h���擾����B<BR>
	 * <BR>�@@
	 * �@@[�擾����]<BR>
	 * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
	 * �@@�����R�[�h = �p�����[�^.�����R�[�h<BR>
	 * �@@�񍆃R�[�h = 0<BR>
	 * <BR>
	 * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A null��ԋp����B<BR> 
	 * <BR>
	 * �Q�j�擾�������M�����}�X�^�f�[�^�̃I�u�W�F�N�g��ԋp����B<BR>
	 * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
	 * @@param l_strProductCode - (�����R�[�h)<BR>
	 * @@return Object
	 * @@roseuid 413C30E102D8
	 */
	protected Object getFundProductMaster(
		String l_strInstitutionCode, 
		String l_strProductCode) 
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getFundProductMaster(String, String) "; 
		log.entering(STR_METHOD_NAME); 
        
		//�P�j���M�����}�X�^����ȉ��̏����Ń��R�[�h���擾����B
		//  [�擾����]
		//  �،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
		//  �����R�[�h = �p�����[�^.�����R�[�h
		//  �񍆃R�[�h = 0
		StringBuffer l_strWhere = new StringBuffer();
		l_strWhere.append(" institution_code = ? ");
		l_strWhere.append(" and product_code = ? ");
		l_strWhere.append(" and product_issue_code = ? ");

		Object[] l_objWhere = {
			l_strInstitutionCode,
			l_strProductCode,
			"0"};

		List l_lisRecords = null;
		try
		{
			QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
			l_lisRecords = l_QueryProcessor.doFindAllQuery(
				MutualFundProductRow.TYPE,
				l_strWhere.toString(),
				null,
				null,
				l_objWhere);
		}
		catch (DataFindException l_dfe)
		{
			log.error(STR_METHOD_NAME, l_dfe);
			return null;
		}
		catch (DataQueryException l_dqe)
		{
			log.error(STR_METHOD_NAME, l_dqe);

			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_dqe.getMessage(),
				l_dqe);
		}
		catch (DataNetworkException l_dne)
		{
			log.error(STR_METHOD_NAME, l_dne);

			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_dne.getMessage(),
				l_dne);
		}

		//�@@�������ʂ��擾�ł��Ȃ������ꍇ�A null��ԋp����B 
		//�Q�j�擾�������M�����}�X�^�f�[�^�̃I�u�W�F�N�g��ԋp����B
		MutualFundProductRow l_fundProductRow = null;
		if (!l_lisRecords.isEmpty())
		{
			l_fundProductRow = (MutualFundProductRow)l_lisRecords.get(0);
		}

		log.exiting(STR_METHOD_NAME);  
		return l_fundProductRow;
	}    

	/**
	 * (is�����o��)<BR>
	 * �p�����[�^�Ɏw�肳�ꂽ�Y�����X�V�X�e���v���t�@@�����X�e�[�u����<BR> 
	 * �v���t�@@�����X�̒l���������ꍇ��true�A�Ȃ������ꍇ��false��ԋp����B<BR> 
	 * <BR>
	 * �P�j���X�V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B<BR> 
	 * <BR>
	 * �@@[�擾����] <BR>
	 * �@@���XID = �p�����[�^.���XID <BR>
	 * �@@�v���t�@@�����X�� = �v���t�@@�����X��.�����o���`�F�b�N <BR>
	 * �@@�v���t�@@�����X���̘A�� = 1 <BR>
	 * <BR>
	 * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A false��ԋp����B <BR>
	 * <BR>
	 * �Q�j�擾�������X�V�X�e���v���t�@@�����X�e�[�u���̃v���t�@@�����X�̒l�� <BR>
	 * �@@�@@1�i�����o���\�j�̏ꍇ�Ature��ԋp����B <BR>
	 * �@@�@@����ȊO�̏ꍇ��false��ԋp����B<BR>
	 * @@param l_lngBranchId - (���XID)<BR>
	 * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 413C30E102D8
	 */
	protected boolean isTodayPayment(long l_lngBranchId)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " isTodayPayment(long)"; 
		log.entering(STR_METHOD_NAME);
		
        //�v���t�@@�����X���̘A�Ԃ��`
        final int NAME_SERIAL_NO = 1;

        //�P�j���X�V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B 
		// [�擾����] 
		//  �@@���XID = �p�����[�^.���XID 
		//  �@@�v���t�@@�����X�� = �v���t�@@�����X��.�����o���`�F�b�N 
		//  �@@�v���t�@@�����X���̘A�� = 1 
        BranchPreferencesRow l_branchPreferencesRow = null;
        try
        {
        	l_branchPreferencesRow = BranchPreferencesDao.findRowByPk(
            	l_lngBranchId,
                WEB3BranchPreferencesNameDef.TODAY_PAYMENT_CHECK,
                NAME_SERIAL_NO);
        }
        catch (DataFindException l_exp)
        {
            //�������ʂ��擾�ł��Ȃ������ꍇ�A false��ԋp����B 
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataQueryException l_exp)
        {
            log.error(l_exp.getMessage());
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }
        catch (DataNetworkException l_exp)
        {
            log.error(l_exp.getMessage());
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }

        //�Q�j�擾�������X�V�X�e���v���t�@@�����X�e�[�u���̃v���t�@@�����X�̒l�� 
		//  1�i�����o���\�j�̏ꍇ�Ature��ԋp����B 
        String l_strValue = l_branchPreferencesRow.getValue();
        if (WEB3TodayPaymentCheckDef.CHECK.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

		//  ����ȊO�̏ꍇ��false��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
	}
	
	/**
	 * (is���z�␳)<BR>
	 * ��n���z�̕␳���s���B<BR> 
	 * <BR>
	 * �P�j�ȉ��̏����ɂċ��z��␳���郌�R�[�h���𔻕ʂ���B<BR> 
	 * <BR>
	 * �@@�E�ڋq�}�X�^.MRF�����J�݋敪=1�i�����J�݁j<BR> 
	 * �@@�E�������.���i�R�[�h = 99�@@&& <BR> 
	 * �@@�@@�@@�������.����R�[�h = A1�@@&& <BR>
	 * �@@�@@�@@�������.�E�v�R�[�h = 0070�@@&& <BR>�@@ 
	 * �@@�@@�@@�������.�o���敪 = 1 <BR>
	 * <BR>
	 * �@@��L�����ɓ��Ă͂܂郌�R�[�h�������ꍇ�A<BR> 
	 * �@@�@@�w�@@wk��n���z = wk��n���z + �������.��n���z�@@�x<BR> 
	 * �@@��L�����ɓ��Ă͂܂�Ȃ��ꍇ�A�������s��Ȃ��B<BR>
	 * @@param l_tradeHistoryParams - (�������Params)<BR>
	 * @@param l_mainAccount - (�ڋq)<BR>
     * @@roseuid 413C30E102D8
	 */
	protected void isPaymentRevision(
		TradeHistoryParams l_tradeHistoryParams,
		WEB3GentradeMainAccount l_mainAccount)
	{
		final String STR_METHOD_NAME = " isPaymentRevision(TradeHistoryParams, WEB3GentradeMainAccount)"; 
		log.entering(STR_METHOD_NAME);
		
		//�P�j�ȉ��̏����ɂċ��z��␳���郌�R�[�h���𔻕ʂ���B 
		//	�E�ڋq�}�X�^.MRF�����J�݋敪=1�i�����J�݁j 
		//	�E�������.���i�R�[�h = 99�@@&&�@@ 
		//	�@@�������.����R�[�h = A1�@@&& 
		//	�@@�������.�E�v�R�[�h = 0070�@@&& �@@ 
		//	�@@�������.�o���敪 = 1 
		MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
		MainAccountParams l_mainAccountParams = new MainAccountParams(l_mainAccountRow);
		
		if (WEB3AccountOpenDef.OPEN.equals(l_mainAccountParams.getMrfAccOpenDiv())
			&& WEB3TradeHistoryProductCodeDef.MONEY.equals(l_tradeHistoryParams.getCommodityCode())
			&& WEB3TradeHistoryTradeCodeDef.TRADE_CODE_A1.equals(l_tradeHistoryParams.getTradeCode())
			&& WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_0070.equals(l_tradeHistoryParams.getRemarkCode())
			&& WEB3IoDivDef.OUTPUT.equals(l_tradeHistoryParams.getIoDiv()))
		{
			//	��L�����ɓ��Ă͂܂郌�R�[�h�������ꍇ�A 
			//	�@@�w�@@wk��n���z = wk��n���z + �������.��n���z�@@�x
		    
            BigDecimal l_bdNetAmount = new BigDecimal(l_tradeHistoryParams.getNetAmount());
            BigDecimal l_bdwkNetAmount = new BigDecimal(this.wkNetAmount);
            this.wkNetAmount = l_bdNetAmount.add(l_bdwkNetAmount).doubleValue();
		}
		
		//��L�����ɓ��Ă͂܂�Ȃ��ꍇ�A�������s��Ȃ��B
        log.exiting(STR_METHOD_NAME);
	}
	
	/**
	 * (calc�����c��)<BR>
	 * �ȉ��̏����̏ꍇ�A�����c���̌v�Z���s���B<BR> 
	 * <BR>
	 * wk��n���z != 0 && ��n���ʎc����� != null && ��n���ʎc�����.�����c�� != null �̏ꍇ�A<BR> 
   * �ȉ��̌v�Z�������s���B<BR>
	 * <BR>
	 * �P�j��n���ʎc�����.�����c��= <BR>
	 * �@@�@@�@@�@@�@@�@@�@@�@@��n���ʎc�����.�����c�� + wk��n���z <BR>
	 * <BR>
	 * �Q�j�@@wk��n���z������������B�iwk��n���z = 0�j<BR>
	 * <BR>
	 * @@param l_dailyBalanceUnit - (��n���ʎc�����)<BR>
     * @@roseuid 413C30E102D8
	 */
	protected void calcAccountBalance(WEB3HistoryDailyBalanceUnit l_dailyBalanceUnit)
	{
		final String STR_METHOD_NAME = " calcAccountBalance(WEB3HistoryDailyBalanceUnit)"; 
		log.entering(STR_METHOD_NAME);
		
		//wk��n���z != 0 && ��n���ʎc����� != null && ��n���ʎc�����.�����c�� != null
    //�̏ꍇ�A�ȉ��̌v�Z�������s���B
		if ((this.wkNetAmount != 0) && (l_dailyBalanceUnit != null) && (l_dailyBalanceUnit.accountBalance != null))
		{
			//�P�j��n���ʎc�����.�����c��= 
			//  ��n���ʎc�����.�����c�� - wk��n���z 
            BigDecimal l_bdAccountBalance = new BigDecimal(l_dailyBalanceUnit.accountBalance);
            BigDecimal l_bdwkNetAmount = new BigDecimal(this.wkNetAmount);

            //2006/05/01 SCS��؏C��START
//            double l_dblAccountBalance = l_bdAccountBalance.subtract(l_bdwkNetAmount).doubleValue();
            double l_dblAccountBalance = l_bdAccountBalance.add(l_bdwkNetAmount).doubleValue();
            //2006/05/01 SCS��؏C��END

			l_dailyBalanceUnit.accountBalance = 
			    WEB3StringTypeUtility.formatNumber(l_dblAccountBalance);
			
			//�Q�j�@@wk��n���z������������B�iwk��n���z = 0�j
			this.wkNetAmount = 0;
		}
		
		log.exiting(STR_METHOD_NAME);
	}
}
@
