head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M���������ʒmUnitServiceImpl(WEB3MutualTradeOrderNotifyUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ����  (���u) �V�K�쐬
Revesion History : 2004/08/24 ����� (���u) ���r���[
Revesion History : 2004/12/10 ���� (���u) �c�Ή�
Revesion History : 2005/11/18 ���� (���u) �t�B�f���e�B�Ή�
Revesion History : 2007/01/05 ���{ (SRA) �d�l�ύX No519
Revesion History : 2007/01/15 �Ԑi (���u) �d�l�ύX No520�i�{�ԏ�Q�[�FH00139�j
Revesion History : 2007/12/21 �g�C�� (���u) �d�l�ύX No587
Revesion History : 2007/12/26 ���g (���u) �d�l�ύX No588
Revesion History : 2008/04/28 ���g (���u) �d�l�ύX No599
Revesion History : 2008/05/12 ���g (���u) �d�l�ύX No603
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AvailableDatacodeDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3GftErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3InputUnitDef;
import webbroker3.common.define.WEB3OrderCancelDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SpecifyDivDef;
import webbroker3.common.define.WEB3SwtDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundBizLogicProvider;
import webbroker3.mf.WEB3MutualFundEstimatedPrice;
import webbroker3.mf.WEB3MutualFundNewOrderSpec;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradeOrderNotifyConfirmInterceptor;
import webbroker3.mf.data.HostXbmfOrderNotifyParams;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3MFTradeTypeDef;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendar;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

/**
 * (���M���������ʒmUnitServiceImpl)
 * ���M���������ʒm�P���T�[�r�X�����N���X���M���������ʒm�P�����Ƃ̏��������{����B<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3MutualTradeOrderNotifyUnitServiceImpl 
	implements WEB3MutualTradeOrderNotifyUnitService
{
    /**
     *  ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualTradeOrderNotifyUnitServiceImpl.class);
    
    /**
     * (notify���������ʒm)<BR>
     * ���M���������ʒm�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���M���������ʒm�jnotify���������ʒm�v�Q��
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u�i���M���������ʒm�jnotify���������ʒm�v: <BR>
     *    18((�g�����M�����}�l�[�W��.submitNewOrder()�̖߂�l.<BR>
     *       getProcessingResult().isSuccessfulResult()==false�̏ꍇ�A<BR>
     *       ��O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00191<BR>
     * ==========================================================<BR>
     * @@param l_orderNotifyQueueParams - ���M�����ʒm�L���[Params<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    public void notifyTradeOrderNotify(HostXbmfOrderNotifyParams l_orderNotifyQueueParams)
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyTradeOrderNotify(HostXbmfOrderNotifyParams l_orderNotifyQueueParams) ";
        log.entering(STR_METHOD_NAME);
        
        // If (���M�����ʒm�L���[Params == null 
        if (l_orderNotifyQueueParams == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B " +
                "with (���M�����ʒm�L���[Params)l_orderNotifyQueueParams ="+ 
                    l_orderNotifyQueueParams);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);     
        }

        //�g���A�J�E���g�}�l�[�W���̎擾
        FinApp l_finApp = 
            (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            //1.1�j�@@�،���ЃI�u�W�F�N�g�̎擾
            Institution l_institution = 
                l_accMgr.getInstitution(
                    l_orderNotifyQueueParams.getInstitutionCode());
            
            //1.2�j ���X�I�u�W�F�N�g���擾����B
            Branch l_branch = 
                l_accMgr.getBranch(
                    l_institution, 
                    l_orderNotifyQueueParams.getBranchCode());
            
			// 1.3�j�ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B
			//[�擾����]
			//���XID=�擾�������X.���XID
			//�v���t�@@�����X��="mf.available.datacode"
			//�v���t�@@�����X�̘A��=1
            BranchPreferencesRow l_branchPreFerencespRow = null;
            int l_intNameSerialNo = 1;
            int l_intFalg = 0;
            try
            {
                l_branchPreFerencespRow = BranchPreferencesDao.findRowByPk(
                    l_branch.getBranchId(),
                    WEB3BranchPreferencesNameDef.AVAILABLE_DATACODE,
                    l_intNameSerialNo);
            }
            catch (DataFindException l_ex)
            {
                log.debug("���R�[�h���擾�ł��Ȃ������ꍇ");
                log.exiting(STR_METHOD_NAME);                
                
                l_intFalg = 1;
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
			// 1.4�j�ȉ��̏����̂𖞂����ꍇ�́A�ȍ~�̏������X�L�b�v����B
            String l_strValue = null;
            if (l_intFalg != 1)
            {
                l_strValue = l_branchPreFerencespRow.getValue();
            }

            log.debug("l_strValue = " + l_strValue);
            //���g�p
            boolean l_blnIsDefault = 
                WEB3AvailableDatacodeDef.DEFAULT.equals(l_strValue);
            //�O�����M�戵�\
            boolean l_blnIsForeignMf = 
                WEB3AvailableDatacodeDef.FOREIGN_MF.equals(l_strValue);
            //��W�戵�\
            boolean l_blnIsRecruitOrder = 
                WEB3AvailableDatacodeDef.RECRUIT_ORDER.equals(l_strValue);
            //�O�����M�{��W�戵�\
            boolean l_blnIsAll = 
                WEB3AvailableDatacodeDef.ALL.equals(l_strValue);
            //�戵�s��
            boolean l_blnIsNone =
                WEB3AvailableDatacodeDef.NONE.equals(l_strValue);
            
            //�������M
            boolean l_blnIsDomesttc = 
                WEB3HostRequestCodeDef.MUTUAL_FUND_DOMESTIC_ORDER_NOTIFY.equals(
                    l_orderNotifyQueueParams.getRequestCode());
           //�O�����M
            boolean l_blnIsForeign = 
                WEB3HostRequestCodeDef.MUTUAL_FUND_FOREIGN_ORDER_NOTIFY.equals(
                    l_orderNotifyQueueParams.getRequestCode());
            //��W
            boolean l_blnIsRecruit = 
                WEB3HostRequestCodeDef.MUTUAL_FUND_RECRUIT_ORDER_NOTIFY.equals(
                    l_orderNotifyQueueParams.getRequestCode());

            //[����]
			//���擾�����v���t�@@�����X�̒l == �h���g�p�h or ���R�[�h���擾�ł��Ȃ������ꍇ
			//  ����.���M�����ʒm�L���[Params.�f�[�^�R�[�h != �h�������M�h
            //���擾�����v���t�@@�����X�̒l == �h�O�����M�戵�\�h �̏ꍇ
			//   ����.���M�����ʒm�L���[Params.�f�[�^�R�[�h != �i�h�������M�h or �h�O�����M�h�j
			//���擾�����v���t�@@�����X�̒l == �h��W�戵�\�h �̏ꍇ
			//   ����.���M�����ʒm�L���[Params.�f�[�^�R�[�h != �i�h�������M�h or �h��W�h�j
			//���擾�����v���t�@@�����X�̒l == �h�O�����M�{��W�戵�\�h �̏ꍇ
			//   ����.���M�����ʒm�L���[Params.�f�[�^�R�[�h != �i�h�������M�h or �h�O�����M�h or �h��W�h�j
            //���擾�����v���t�@@�����X�̒l == �h�戵�s�h�̏ꍇ
            if (((l_blnIsDefault || l_intFalg == 1) && !l_blnIsDomesttc) ||      
               (l_blnIsForeignMf && !(l_blnIsDomesttc || l_blnIsForeign)) ||
               (l_blnIsRecruitOrder && !(l_blnIsDomesttc || l_blnIsRecruit)) ||
               (l_blnIsAll && !(l_blnIsDomesttc || l_blnIsForeign || l_blnIsRecruit)) ||
               l_blnIsNone)
            {
                return;
            }
            
            if (WEB3OrderCancelDivDef.CANCEL_NOT.equals(l_orderNotifyQueueParams.getOrderCancelDiv()))
            {
                //1.5) create����(���M�����ʒm�L���[Params)
                this.createOrder(l_orderNotifyQueueParams);
            }
            else if (WEB3OrderCancelDivDef.CANCEL.equals(l_orderNotifyQueueParams.getOrderCancelDiv()))
            {
                //1.6 cancel����(���M�����ʒm�L���[Params)
                this.cancelOrder(l_orderNotifyQueueParams);
            }
         }
         catch (NotFoundException l_ex)
         {
             log.error("�Y���f�[�^�Ȃ��B", l_ex);
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_ex.getMessage(),
                 l_ex);
         }                
         log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create����)<BR>
     * ���������������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���M���������ʒm�j���������v �Q�ƁB
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u�i���M���������ʒm�j���������v: <BR>
     *    18((submitNewOrder()��萬���̖߂�l���Ԃ���Ȃ������ꍇ�A<BR>
     *       ��O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00191<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_orderNotifyQueueParams - ���M�����ʒm�L���[Params<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    protected void createOrder(HostXbmfOrderNotifyParams l_orderNotifyQueueParams) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createOrder(HostXbmfOrderNotifyParams l_orderNotifyQueueParams)" ;
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1.1 getInstitution
            //�g���A�J�E���g�}�l�[�W���̎擾
            FinApp l_finApp = 
                (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //�،���ЃI�u�W�F�N�g�̎擾
            Institution l_institution = 
                l_accMgr.getInstitution(
                    l_orderNotifyQueueParams.getInstitutionCode());
           
            //1.2 getBranch
            //���X�I�u�W�F�N�g���擾����B
            Branch l_branch = 
                l_accMgr.getBranch(
                    l_institution, 
                    l_orderNotifyQueueParams.getBranchCode());
            
            //1.3 getMainAccount
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount) l_accMgr.getMainAccount(
	                l_institution, 
	                l_branch, 
	                l_orderNotifyQueueParams.getAccountCode());

            //1.4 is�M�p�����J��
			boolean l_blnisMarginAccountEstablished =
				l_mainAccount.isMarginAccountEstablished(
				    WEB3GentradeRepaymentDivDef.DEFAULT);
	        
			//1.5 getSubAccount
			//[getSubAccount�ɓn���p�����^] 
			// �⏕�����^�C�v�F  
			//is�M�p�����J��=true �̏ꍇ�A
			//SubAccountTypeEnum.�����M�p��������i�ۏ؋��j  
			SubAccount l_subAccount = null;	
			if (l_blnisMarginAccountEstablished)
			{
				l_subAccount = 
				    l_mainAccount.getSubAccount(
				        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
			}
			else
			{
				//is�M�p�����J��=false �̏ꍇ�A
			    //SubAccountTypeEnum.������������i�a����j
				l_subAccount = 
				    l_mainAccount.getSubAccount(
				        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
			}
			
            //1.6 get���M����
			
			//�g�����M�����}�l�[�W�����擾����B
            WEB3MutualFundProductManager l_mutualFundProductManager = 
                (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getProductManager();
            WEB3MutualFundProduct l_mutualFundProduct = 
                (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                    l_institution, 
                    l_orderNotifyQueueParams.getProductCode());
            //�g�����M�����}�l�[�W���擾
            WEB3MutualFundOrderManager l_orderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();
            
			//1.7 if�@@�抷�敪���抷�̏ꍇ
            WEB3MutualFundProduct l_swtDivProduct = null;
            //�抷�敪���抷�̏ꍇ
            boolean l_blnIsSwitching = 
                WEB3SwtDivDef.SWITCHING.equals(l_orderNotifyQueueParams.getSwtDiv());
            if (l_blnIsSwitching)
            {
                //1.7.1 get���M����(Institution, String, String)
                l_swtDivProduct =  
                    (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                        l_institution, 
                        l_orderNotifyQueueParams.getSwtProductCode());
            }

            //�����敪 == ��W �̏ꍇ
            boolean l_blnIsRecruit = 
                WEB3MFTradeTypeDef.RECRUIT.equals(
                    l_orderNotifyQueueParams.getTradeType());
            //�����敪 == ���t �̏ꍇ
            boolean l_blnIsBuy = 
                WEB3MFTradeTypeDef.BUY.equals(
                    l_orderNotifyQueueParams.getTradeType());
            //�����敪 == ��� �̏ꍇ
            boolean l_blnIsSell =  
                WEB3MFTradeTypeDef.SELL.equals(
                    l_orderNotifyQueueParams.getTradeType());

            //��������
            double l_dblOrderQuantity = 0.0 ;
            
            //1.8 if  ��W�ȊO�̏ꍇ
            //1.8.1 �������ʂ̎擾
            //�擾�����g�����M����.get���͒P��()�̖߂�l���h1�F1�h�̏ꍇ 
            //�������� = ����.���M�����ʒm�L���[Params.get��������()
            //(*) �擾�����g�����M����.get���͒P��()�̖߂�l���h1�F1�h�̏ꍇ�@@
             //�������� = ����.���M�����ʒm�L���[Params.get��������()
            if(!l_blnIsRecruit){
                if ((WEB3InputUnitDef.ONE).equals(
                    l_mutualFundProduct.getInputUnit()))
                {
                    l_dblOrderQuantity = 
                        l_orderNotifyQueueParams.getQuantity();
                }
                //�擾�����g�����M����.get���͒P��()�̖߂�l���h2�F1���h�̏ꍇ 
                //�������� = ����.���M�����ʒm�L���[Params.get��������() �� 10000 
                else if ((WEB3InputUnitDef.TEN_THOUSAND).equals(
                    l_mutualFundProduct.getInputUnit()))
                {
                    l_dblOrderQuantity = 
                        l_orderNotifyQueueParams.getQuantity() * 10000;
                }
            }                    
            
            //1.9 if�@@�����敪 == ��W �̏ꍇ
    		//�����敪���i3�F��W�j�̏ꍇ�A 
    		//������������ 
    		//��n������W�I�����ƂȂ�
            //��W�I����
            Timestamp l_tsRecruitEndDate = null;
            //������
            Timestamp l_tsBizDate = null;
            //�w����@@�i��W�j
            String l_strRecruitSpecityDiv = null;
            //�T�Z��������
            double l_dblEstimateDealingQty = 0.0D;
            if (l_blnIsRecruit)
            {
                //1.9.1 get������( )
                //�������i=�����j���擾����B 
                l_tsBizDate = new Timestamp(
                        WEB3GentradeTradingTimeManagement.getOrderBizDate().getTime());

                //1.9.2 get��W�I����( )
                //��W�I�����i=��n���j���擾����B
                l_tsRecruitEndDate = l_mutualFundProduct.getRecruitEndDate();

                //1.9.3 get�w����@@�i��W�j( )
                l_strRecruitSpecityDiv =
                    l_mutualFundProduct.getRecruitSpecityDiv();                    
                if(WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(
                    l_strRecruitSpecityDiv))
                {
                    log.debug("�w����@@�i��W�j�G���[(�h�I���w��h���w��s��)�B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02297,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�w����@@�i��W�j�G���[(�h�I���w��h���w��s��)�B");
                }

                //1.9.4 �T�Z���������̎擾
                if ((WEB3InputUnitDef.ONE)
                    .equals(l_mutualFundProduct.getInputUnit()))
                {
                    l_dblEstimateDealingQty =
                        l_orderNotifyQueueParams.getQuantity();
                }
                else if (
                    (WEB3InputUnitDef.TEN_THOUSAND).equals(
                        l_mutualFundProduct.getInputUnit()))
                {
                    l_dblEstimateDealingQty =
                        l_orderNotifyQueueParams.getQuantity() * 10000;
                }

                //1.9.5 �������ʂ̎擾
                if (WEB3BuySellSwtSpecityDivDef
                    .QUANTITY_DESIGNATE
                    .equals(l_strRecruitSpecityDiv))
                {
                    l_dblOrderQuantity = l_dblEstimateDealingQty;
                }
                else if (
                    WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(
                        l_strRecruitSpecityDiv))
                {
                    l_dblOrderQuantity =
                        l_orderNotifyQueueParams.getEstimatedPrice();
                }
            }
            
            //1.10 if�@@�抷�敪���抷 �̏ꍇ
            
            //�抷����
            Timestamp l_tsSwtExecutedDate = null;
            //�抷��n��
            Timestamp l_datSwtDeliveryDate = null;
            
            if (l_blnIsSwitching)
            {
                //1.10.1  get�抷����(Institution, String, String)
				//�抷���̖������擾����B 
				//�mget�抷�����ɓn���p�����^�n 
				//�،���ЁF �擾�����،���ЃI�u�W�F�N�g 
				//�抷�������R�[�h�F 
                //����.���M�����ʒm�L���[Params.get�����R�[�h()�̖߂�l 
				//�抷������R�[�h�F 
                //����.���M�����ʒm�L���[Params.get���t�����R�[�h()�̖߂�l
                l_tsSwtExecutedDate = 
                    l_mutualFundProductManager.getSwtExecutedDate(
                        l_institution, 
                        l_orderNotifyQueueParams.getProductCode(),
                        l_orderNotifyQueueParams.getSwtProductCode());
                
                //1.10.2 get�抷��n��(Institution, String, String)
				//�抷���̎�n�����擾����B 
				//�mget�抷��n���ɓn���p�����^�n 
				//�،���ЁF �擾�����،���ЃI�u�W�F�N�g 
				//�抷�������R�[�h�F 
                //����.���M�����ʒm�L���[Params.get�����R�[�h()�̖߂�l 
				//�抷������R�[�h�F 
                //����.���M�����ʒm�L���[Params.get���t�����R�[�h()�̖߂�l
                l_datSwtDeliveryDate =
                    l_mutualFundProductManager.getSwtDeliveryDate(
                        l_institution,
                        l_orderNotifyQueueParams.getProductCode(),
                        l_orderNotifyQueueParams.getSwtProductCode());
            }
            
            //1.11 if ��W�A�抷�ȊO�̏ꍇ
            
            //����
            Timestamp l_tsExecTimestamp = null;
            //��n��
            Timestamp l_tsDeliveryTimestamp = null;

            Date l_datBizDate = null;
            if (!(l_blnIsRecruit || l_blnIsSwitching))
            {
                //�������擾
                //�g�����M����.is������������()���R�[��
                //[�g�����M����.is������������()�̈���]
                //������ʁF�擾�����������(*1)
                //(*1)������ʂ̎擾�B
                OrderTypeEnum l_orderTypeEnum = null;
                if (l_blnIsSell && !l_blnIsSwitching)
                {
                    //���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h1�F���t�h
                    //�����M�����ʒm�L���[Params.get�抷�敪()���h1�F�抷�h�łȂ��ꍇ�A202�F�����M��������
                    l_orderTypeEnum = OrderTypeEnum.MF_SELL;
                }
                else if (l_blnIsBuy)
                {
                    //���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h2�F���t�h�A201�F�����M��������
                    l_orderTypeEnum = OrderTypeEnum.MF_BUY;
                }

                boolean l_blnIsUnitTypeProduct =
                    l_mutualFundProduct.isUnitTypeProduct(l_orderTypeEnum);

                //�g�����M����.is������������() = true�̏ꍇ
                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
                if (l_blnIsUnitTypeProduct)
                {
                    if (OrderTypeEnum.MF_BUY.equals(l_orderTypeEnum))
                    {
                        //�擾�����������(*1)���u201�F�����M���������v�̏ꍇ
                        //�g�����M����.getDataSourceObject().get���t�I�����𔭒����Ƃ���B
                        l_datBizDate = l_mutualFundProductRow.getBuyEndDate();
                    }
                    else if (OrderTypeEnum.MF_SELL.equals(l_orderTypeEnum))
                    {
                        //�擾�����������(*1)���u202�F�����M���������v�̏ꍇ
                        //�g�����M����.getDataSourceObject().get���抷�I�����𔭒����Ƃ���B
                        l_datBizDate = l_mutualFundProductRow.getSellSwtEndDate();
                    }
                }
                else
                {
                    //�g�����M����.is������������() = false�̏ꍇ
                    //���M�����ʒm�L���[Params.get��������()�̖߂�l�𔭒����Ƃ���B
                    l_datBizDate = l_orderNotifyQueueParams.getBizDatetime();
                }

                //1.11.1 get����(Institution, String, Date)
                l_tsExecTimestamp = 
                    l_mutualFundProductManager.getExecutedDate(
                        l_institution, 
                        l_orderNotifyQueueParams.getProductCode(),
                        WEB3DateUtility.toDay(l_datBizDate));

                //(*)�������������̏ꍇ�̖����擾
                //�擾�����g�����M����.getProductId��3303910181800�܂���3303911181800�̏ꍇ
                //������.���M�����ʒm�L���[Params.get�����敪()���h1:���t�h�̏ꍇ
                long l_lngProductId = l_mutualFundProduct.getProductId();
                if ((l_lngProductId == 3303910181800L || l_lngProductId == 3303911181800L)
                    && l_blnIsSell)
                {
                    //�P�j�@@����J�����_�[���擾����
                    //GtlUtils.getFinObjectManager().getTradingCalendar()���R�[�����āA����J�����_�[���擾����B
                    //�mgetTradingCalendar�ɓn���p�����^�n
                    //�������ID�F
                    //�擾�����g�����M����.getProductId��3303910181800�̏ꍇ�A330003910181800
                    //�擾�����g�����M����.getProductId��3303911181800�̏ꍇ�A330003911181800
                    TradingCalendar l_tradingCalendar = null;
                    if (l_lngProductId == 3303910181800L)
                    {
                        l_tradingCalendar = GtlUtils.getFinObjectManager().getTradingCalendar(
                            330003910181800L);
                    }
                    else
                    {
                        l_tradingCalendar = GtlUtils.getFinObjectManager().getTradingCalendar(
                            330003911181800L);
                    }
                    //�Q�j�@@�������擾����
                    //����J�����_�[.roll()���R�[�����āA�������擾����B
                    //�mroll�ɓn���p�����^�n
                    //�������F �擾�������M������
                    //�ړ������F 2
                    Date l_datExecTimestamp =
                        l_tradingCalendar.roll(WEB3DateUtility.toDay(l_datBizDate), 2);
                    //�R�j�@@�擾����������Ԃ��B
                    //���O�����Ŏ擾�����������㏑������B
                    l_tsExecTimestamp =
                        new Timestamp(l_datExecTimestamp.getTime());
                }

                //1.11.2 get��n��(Institution, String, boolean,Date)
        		//�mget��n���ɓn���p�����^�n 
        		//�،���ЁF �擾�����،���ЃI�u�W�F�N�g 
        		//�����R�[�h�F ����.���M�����ʒm�L���[Params.get�����R�[�h()�̖߂�l 
        		//is���t�F 
        		//(*) ����.���M�����ʒm�L���[Params.get�����敪()
                //�̖߂�l���h1�F���t�h�̏ꍇ false ���w��B 
        		//(*) ����.���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h
                //2�F���t�h�̏ꍇ true ���w��B
                //�����F�擾��������
                l_tsDeliveryTimestamp  = 
                    l_mutualFundProductManager.getDeliveryDate(
                        l_institution,
                        l_orderNotifyQueueParams.getProductCode(),
                        l_blnIsBuy,
                        l_tsExecTimestamp);
            }
            
			double l_dblEstimatedPrice = 0.0D;

            //1.12 if�@@�f�[�^�R�[�h����W & �f�[�^�R�[�h���O�����M �̏ꍇ
            
            //�T�Z��n����I�u�W�F�N�g
            WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = null;   
           
            if (!WEB3HostRequestCodeDef.MUTUAL_FUND_RECRUIT_ORDER_NOTIFY.equals(
                	l_orderNotifyQueueParams.getRequestCode()) && 
                !WEB3HostRequestCodeDef.MUTUAL_FUND_FOREIGN_ORDER_NOTIFY.equals(
                    l_orderNotifyQueueParams.getRequestCode()))
            {
                //1.12.1 calc�T�Z��n���(SubAccount, �g�����M����, String, double, String, String)
        		//�m�����n 
        		//�⏕�����F �擾�����⏕�����I�u�W�F�N�g 
        		//�����F �擾�����g�����M�����I�u�W�F�N�g 
                //�����i�抷��j�F 
        		//(*)  ����.���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h
                //1�F���t�h�ŁA���� 
        		//����.���M�����ʒm�L���[Params.get�抷�敪()���h1�F�抷�h�̏ꍇ 
        		//�擾�����抷��̊g�����M�����I�u�W�F�N�g���w�� 
        		//(*) �抷�ȊO�̏ꍇ�Anull�Z�b�g 
                
                //�����敪
                String l_strStatus = null; 
                //�w����@@                                                               
                String l_strSpecifyDiv = null; 
                if (l_blnIsSell)
                {
                    //�����敪�F 
            		//(*) ����.���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h
                    //1�F���t�h�ŁA���� 
            		//����.���M�����ʒm�L���[Params.get�抷�敪()���h
                    //1�F�抷�h�łȂ��ꍇ�A�h2�F���h���w�� 
                    if (!l_blnIsSwitching)
                    {
                        l_strStatus = WEB3MFProcessDivDef.SELL;  
                    }
                    else
                    {
                        //(*) ����.���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h
                        //1�F���t�h�ŁA���� 
                		//����.���M�����ʒm�L���[Params.get�抷�敪()���h
                        //1�F�抷�h�̏ꍇ�A �h3�F�抷�h���w�� 
                        l_strStatus = WEB3MFProcessDivDef.SWITCHING;  
                    }
                }
                
                //(*) ����.���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h
                //2�F���t�h �̏ꍇ�A�h1�F���t�h���w�� 
                else if(l_blnIsBuy)
                {
                    l_strStatus = WEB3MFProcessDivDef.BUY;    
                }
        		//(*) ����.���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h
                //3�F��W�h�̏ꍇ�A �̏ꍇ�A�h4�F��W�h���w�� 
        		else if(l_blnIsRecruit)
        		{
        		    l_strStatus = WEB3MFProcessDivDef.RECRUIT;
        		}
        		
                //�������ʁF �擾������������ 
                //�w����@@�F 
        		//(*) ����.���M�����ʒm�L���[Params.get�w��()�̖߂�l���h
        		//1�F�����h�̏ꍇ �h4�F�����w��h���w�� 
        		//(*) ����.���M�����ʒm�L���[Params.get�w��()�̖߂�l���h
        		//2�F���z�h�̏ꍇ �h3�F���z�w��h���w�� 
                /*�w����@@�擾*/
                
                //(*) ����.���M�����ʒm�L���[Params.get�w��()�̖߂�l���h
                 //1�F�����h�̏ꍇ�h 4�F�����w��h���w��
                if ((WEB3SpecifyDivDef.COUNT).equals(
                    l_orderNotifyQueueParams.getSpecifyDiv()))
                {
                    l_strSpecifyDiv = 
                        WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE;      
                }
                
                //(*) ����.���M�����ʒm�L���[Params.get�w��()�̖߂�l���h
                 //2�F���z�h�̏ꍇ �h3�F���z�w��h���w��
                else if ((WEB3SpecifyDivDef.MONEY).equals(
                    l_orderNotifyQueueParams.getSpecifyDiv())) 
                {
                    l_strSpecifyDiv = 
                        WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE;    
                }
        		
                //���ϕ��@@�F ����.���M�����ʒm�L���[Params.get����()�̖߂�l 
        		//�������@@�̎擾
                String l_claimDiv = l_orderNotifyQueueParams.getClaimDiv();
                if (" ".equals(l_claimDiv))
                {
                    l_claimDiv = WEB3ClaimDivDef.SELL;
                }
                
                //�����敪�F ����.���M�����ʒm�L���[Params.get��������敪() 
        		//�����`���l���F ����.���M�����ʒm�L���[Params.���͋敪()�̖߂�l
                //�������F
                //(*) ����.���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h1�F���t�h
                //������.���M�����ʒm�L���[Params.get�抷�敪()���h1�F�抷�h�łȂ��ꍇ
                //(*) ����.���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h2�F���t�h�̏ꍇ�擾����������
                //(*) ����ȊO�A����.���M�����ʒm�L���[Params.��������()�̖߂�l
                Date l_datBizDatetime = null;
                if (l_blnIsSell && !l_blnIsSwitching)
                {
                    l_datBizDatetime = l_datBizDate;
                }
                else if (l_blnIsBuy)
                {
                    l_datBizDatetime = l_datBizDate;
                }
                else
                {
                    l_datBizDatetime = l_orderNotifyQueueParams.getBizDatetime();
                }

                //�T�Z��n����I�u�W�F�N�g���擾����
                l_mfEstimatedPrice =
                    l_orderManager.calcEstimateDeliveryAmount(
                        l_subAccount,
                        l_mutualFundProduct,
                        l_swtDivProduct,
                        l_strStatus,
                        l_dblOrderQuantity,
                        l_strSpecifyDiv,
                        l_orderNotifyQueueParams.getSettlementDiv(),
                        l_claimDiv,
                        l_orderNotifyQueueParams.getTaxType(),
                        l_orderNotifyQueueParams.getInputDiv(),
                        l_datBizDatetime);

                //�擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��n���()�̖߂�l�Z�b�g
                l_dblEstimatedPrice = 
                	l_mfEstimatedPrice.getEstimatedPrice();
            } 
            else
            {
				//(*) �u��W�v���邢�́u�O�����M�v�̏ꍇ
				//���M�����ʒm�L���[Params.get�T�Z���z()�̖߂�l�Z�b�g�B  
				l_dblEstimatedPrice = 
					l_orderNotifyQueueParams.getEstimatedPrice();            	
            }
            
            //1.13 if�@@�抷�敪���抷�̏ꍇ
            // �抷��̊T�Z���t�����̎擾
            double l_dblSwitchingEstimatedQty = 0.0D;
            if (l_blnIsSwitching)
            {
                WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
                    (WEB3MutualFundBizLogicProvider) l_finApp
                        .getTradingModule(ProductTypeEnum.MUTUAL_FUND)
                        .getBizLogicProvider();
                l_dblSwitchingEstimatedQty =
                    l_mfBizLogicProvider.calcEstimatedBuyQty(
                        l_swtDivProduct,
                        l_dblEstimatedPrice);
            }
            
            //1.14 ���M���������ʒm�m��C���^�Z�v�^( )
            WEB3MutualFundTradeOrderNotifyConfirmInterceptor 
            	l_tradeOrderNotifyConfirmInterceptor = 
            	    new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
            
            //1.15 setThreadLocalPersistenceEventInterceptor(
            //MutualFundOrderManagerPersistenceEventInterceptor)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_tradeOrderNotifyConfirmInterceptor);

            //1.16 ���v���p�e�B�E�Z�b�g��
    		//���ȉ��̏����ɂ�����A����̎�ʂ̒�` 

            //�O�����M�F���M�����ʒm�L���[Params.get�f�[�^�R�[�h()�̖߂�l���hCI813�F�O�����M�h 
            boolean l_blnIsForeignPossible = 
                WEB3HostRequestCodeDef.MUTUAL_FUND_FOREIGN_ORDER_NOTIFY.equals(
                    l_orderNotifyQueueParams.getRequestCode());
            
    		//�抷�F���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h1�F���t�h�A 
    		//���A ���M�����ʒm�L���[Params.get�抷�敪()���h1�F�抷�h 
            boolean l_blnIsSwtPossible = (l_blnIsSell && l_blnIsSwitching); 
            
    		//���t�F���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h2�F���t�h 
    		//  �@@�i���ʂȒf�肪�Ȃ�����A�������M�ƊO�����M�̗����̔��t���w���j
            boolean l_blnIsBuyPossible = l_blnIsBuy;
            
    		//���t�F���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h1�F���t�h�A
    		// ���A���M�����ʒm�L���[Params.get�抷�敪()���h1�F�抷�h�łȂ��ꍇ�A 
    		//  �@@�i���ʂȒf�肪�Ȃ�����A�������M�ƊO�����M�̗����̔��t���w���j 
            boolean l_blnIsSellPossible =  (l_blnIsSell && !l_blnIsSwitching); 
            
    		//��W�F���M�����ʒm�L���[Params.gett�����敪()�̖߂�l���h3�F��W�h 
            boolean l_blnIsRecruitPossible = l_blnIsRecruit;
            
    		//����.���M���������ʒm�m��C���^�Z�v�^.set�������( )���R�[������B 
            OrderTypeEnum l_orderType = null;
    		//�����F������ʁF 
    		//(*) �u���t�v�̏ꍇ�A �h201�F�����M���������h
            //�iOrderTypeEnum�ɒ�`�j���w�肷��B 
    		if (l_blnIsBuyPossible)
    		{
    		    l_orderType = OrderTypeEnum.MF_BUY;
    		}
            //(*) �u���t�v�̏ꍇ �h202�F�����M���������h
    		//�iOrderTypeEnum�ɒ�`�j���w�肷��B
    		else if (l_blnIsSellPossible)
    		{
    		    l_orderType = OrderTypeEnum.MF_SELL;
    		}
    		//(*) �u�抷�v�̏ꍇ�A�h204�F�����M���抷�����h
    		//�iOrderTypeEnum�ɒ�`�j���w�肷��B
    		else if (l_blnIsSwtPossible)
    		{
    		    l_orderType = OrderTypeEnum.MF_SWITCHING;
    		}
    		//(*) �u��W�v�̏ꍇ�A�h203�F�����M����W�����h
    		//�iOrderTypeEnum�ɒ�`�j���w�肷��B�@@�@@�@@�@@�@@ 
    		else if (l_blnIsRecruitPossible)
    		{
    		    l_orderType = OrderTypeEnum.MF_RECRUIT;
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setOrderType(l_orderType);
            
    		//�E����.���M���������ʒm�m��C���^�Z�v�^.set������( )���R�[������B
    		//�����F�������F
            //(*) �u���t�v�A�u���t�v�̏ꍇ
            //�擾����������
            //(*) ����ȊO�̏ꍇ
            //����.���M�����ʒm�L���[Params.get��������()�̖߂�l���Z�b�g
            if (l_blnIsBuyPossible || l_blnIsSellPossible)
            {
                l_tradeOrderNotifyConfirmInterceptor.setBizDate(
                    new Timestamp(l_datBizDate.getTime()));
            }
            else
            {
        		l_tradeOrderNotifyConfirmInterceptor.setBizDate(
        		    l_orderNotifyQueueParams.getBizDatetime());
            }
    		//����.���M���������ʒm�m��C���^�Z�v�^.set��n��( )���R�[������B
    		Timestamp l_tsDelivery = null;
    		//�����F��n���F 
    		//(*) �u��W�v�̏ꍇ 
            //get��W�I����()�̖߂�l���w�肷��B
    		if (l_blnIsRecruitPossible)
    		{
    		    l_tsDelivery = l_tsRecruitEndDate;
    		}
    		//(*) �u�抷�v�̏ꍇ
    		//get�抷��n��()�̖߂�l���w�肷��B 
    		else if (l_blnIsSwtPossible)
    		{
    		    l_tsDelivery = l_datSwtDeliveryDate;
    		}
    		//(*) ����ȊO�̏ꍇ�A�擾������n�����w�肷��B 
    		else
    		{
    		    l_tsDelivery = l_tsDeliveryTimestamp;
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setDeliveryDate(l_tsDelivery);
    		
    		//����.���M���������ʒm�m��C���^�Z�v�^.set���҃R�[�h�iSONAR�j()���R�[������B  
    		//�����F����.���M�����ʒm�L���[Params.get���҃R�[�h()�̖߂�l  
    		l_tradeOrderNotifyConfirmInterceptor.setSonarTraderCode(
    		    l_orderNotifyQueueParams.getTraderCode());
    		
    		//����.���M���������ʒm�m��C���^�Z�v�^.set���ʃR�[�h()���R�[������B  
    		//�����F����.���M�����ʒm�L���[Params.get���ʃR�[�h()�̖߂�l  
    		l_tradeOrderNotifyConfirmInterceptor.setDiscriminationCode(
    		    l_orderNotifyQueueParams.getOrderRequestNumber());
    		
    		//����.���M���������ʒm�m��C���^�Z�v�^.set�v�Z����z()���R�[������B  
    		double l_dblConstantValue = 0.0D;
    		//�����F�v�Z����z�F  
    		//(*) �u��W�v�̏ꍇ
    		//�����F����.���M�����ʒm�L���[Params.get����z()�̖߂�l�Z�b�g�B  
    		if (l_blnIsRecruitPossible)
    		{
    		    l_dblConstantValue = l_orderNotifyQueueParams.getConstantValue();
    		}
    		//(*) �u���t�v�̏ꍇ�A
    		//�擾�����g�����M�����I�u�W�F�N�g.get���t����z()�̖߂�l���w�肷��B
    		else if (l_blnIsBuyPossible)
    		{
    		    l_dblConstantValue = l_mutualFundProduct.getConstantValue();
    		}
    		//(*) �u���t�v�u�抷�v�̏ꍇ�A
    		//�擾�����g�����M�����I�u�W�F�N�g.get��񉿊z()�̖߂�l���w�肷��  
    		else if ((l_blnIsSellPossible) || (l_blnIsSwtPossible))
    		{
    		    l_dblConstantValue = l_mutualFundProduct.getSellValue();
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setConstantValue(l_dblConstantValue);
    		
    		//����.���M���������ʒm�m��C���^�Z�v�^.set�v�Z����z�i�抷��j()���R�[������B 
    		double l_dblSwtConstantValue = 0.0D;
    		//�����F�v�Z����z�i�抷��j�F  
    		//(*) �u�抷�v�̏ꍇ
    		//�擾�����抷������I�u�W�F�N�g.get���t����z()�̖߂�l���w�肷��B  
    		if (l_blnIsSwtPossible)
    		{
    		    l_dblSwtConstantValue = l_swtDivProduct.getConstantValue();

    		}
    		//(*) �u�抷�v�łȂ��ꍇDouble.NaN���w�肷��
    		else
    		{
    		    l_dblSwtConstantValue = Double.NaN;
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setSwitchingConstantValue(
    		    l_dblSwtConstantValue);
    		
    		//����.���M���������ʒm�m��C���^�Z�v�^.set����z�K�p��()���R�[������B
    		Timestamp l_tsConstantValueAppDate = null;
    		//�����F����z�K�p���F  
    		//(*) �u��W�v�̏ꍇ  null���w�肷��B 
    		//(*) �u��W�v�łȂ��ꍇ�A
            //�擾�����g�����M�����I�u�W�F�N�g.get����z�K�p��()�̖߂�l���w�肷��B 
    		if (!l_blnIsRecruitPossible)
    		{
    		    l_tsConstantValueAppDate = 
    		        l_mutualFundProduct.getConstantValueAppDate();
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setConstantValueAppDate(
    		    l_tsConstantValueAppDate);
    		
    		//����.���M���������ʒm�m��C���^�Z�v�^.set�T�Z��n���()���R�[������B  
    		//�����F�T�Z��n����F  
    		//(*) �u��W�v���邢�́u�O�����M�v�̏ꍇ
    		//���M�����ʒm�L���[Params.get�T�Z���z()�̖߂�l�Z�b�g�B  
    		//(*) ����ȊO�̏ꍇ
    		//�擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��n���()�̖߂�l�Z�b�g
    		l_tradeOrderNotifyConfirmInterceptor.setEstimatedPrice(
    		    l_dblEstimatedPrice);
    		
    		//����.���M���������ʒm�m��C���^�Z�v�^.set�T�Z��������()���R�[������B  
    		double l_dblEstimatedQty = 0.0D;
    		//(*) �u��W�v�̏ꍇ
            //�擾�����T�Z�����������Z�b�g����B  
    		if (l_blnIsRecruitPossible)
    		{
    		    l_dblEstimatedQty = l_dblEstimateDealingQty;
    		}
    		//(*) �u�O�����M�v�̏ꍇ�A
    		//���M�����ʒm�L���[Params.get���Z����()�̖߂�l�Z�b�g�B
    		else if (l_blnIsForeignPossible)
    		{
    		    l_dblEstimatedQty = l_orderNotifyQueueParams.getEstimatedUnit();
    		}
    		//(*) ����ȊO�̏ꍇ
    		//�擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��������()�̖߂�l�Z�b�g 
    		else
    		{
    		    l_dblEstimatedQty = l_mfEstimatedPrice.getEstimatedQty();
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setEstimatedQty(l_dblEstimatedQty);
    		
    		//����.���M���������ʒm�m��C���^�Z�v�^.set�T�Z���t�����i�抷��j()���R�[������B
    		//�����F�T�Z���������i�抷��j�F
            double l_dblSwtEstimatedQty = 0.0D;
            //(*) �u�抷�v�̏ꍇ�Acalc�T�Z���t����()�̖߂�l���w�肷��B
            if (l_blnIsSwtPossible)
            {
                l_dblSwtEstimatedQty = l_dblSwitchingEstimatedQty;
            }
            //(*) �u�抷�v�łȂ��ꍇ�ADouble.NaN���w�肷��B
            else
            {
                l_dblSwtEstimatedQty = Double.NaN;
            }
            l_tradeOrderNotifyConfirmInterceptor.setSwitchingEstimatedQty(
                l_dblSwtEstimatedQty);

    		//����.���M���������ʒm�m��C���^�Z�v�^.set�ŋ敪�i�抷��j()���R�[������B  
    		TaxTypeEnum l_switchingSubjectTaxDivision = null;
    		//�����F�ŋ敪�i�抷��j�F  
    		//(*) �u�抷�v�̏ꍇ
    		//���M�����ʒm�L���[Params.get��������敪�i�抷��j() �̖߂�l�� 
    		if (l_blnIsSwtPossible)
    		{
    		    //�h0�F��ʁh�Ȃ��TaxTypeEnum.NORMAL
                if ((WEB3TaxTypeSpecialDef.NORMAL).equals(
                    l_orderNotifyQueueParams.getSwtTaxType()))
                {
                    l_switchingSubjectTaxDivision = TaxTypeEnum.NORMAL;
                }
                //1�F����h�Ȃ��TaxTypeEnum.SPECIAL���w�肷��B 
                else if ((WEB3TaxTypeSpecialDef.SPECIAL).equals(
                    l_orderNotifyQueueParams.getSwtTaxType()))
                {
                    l_switchingSubjectTaxDivision = TaxTypeEnum.SPECIAL;
                }
    		}
    		//(*)�u�抷�v�łȂ��ꍇ null���w�肷��B  
    		else
    		{
    		    l_switchingSubjectTaxDivision = null;
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setSwitchingSubjectTaxDivision(
    		    l_switchingSubjectTaxDivision);
    		
    		//����.���M���������ʒm�m��C���^�Z�v�^.set��n���@@()���R�[������B  
    		String l_strDeliveryDiv = null;
    		//�����F��n���@@�F  
    		//(*) �u���t�v�̏ꍇ 
    		//���M�����ʒm�L���[Params.get��n���@@()�̖߂�l���w�肷��B 
    		//(*) �u���t�v�łȂ��ꍇ null���w�肷��B
    		if (l_blnIsSellPossible)
    		{
    		    l_strDeliveryDiv = l_orderNotifyQueueParams.getDeliveryMethod();
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setDeliveryDiv(l_strDeliveryDiv);

    		//����.���M���������ʒm�m��C���^�Z�v�^.set���M�^�C�v()���R�[������B  
    		//�����F���M�^�C�v�F �擾�����g�����M����.getMutualFundType()�̖߂�l  
    		l_tradeOrderNotifyConfirmInterceptor.setMutualFundType(
    		    l_mutualFundProduct.getMutualFundType().intValue() + "");

    		//����.���M���������ʒm�m��C���^�Z�v�^.set���M���敪()���R�[������B  
    		String l_strMutualFundSellDiv = null;
    		//�����F���M���敪�F  
    		if ((l_blnIsSellPossible) || (l_blnIsSwtPossible))
    		{
        		//(*) �u���t�vor �u�抷�v�A���A
    		    //���M�����ʒm�L���[Params.get�w��()�̖߂�l��
                //�h1�F�����h�̏ꍇ �h4�F�����w��h���w��  
                if ((WEB3SpecifyDivDef.COUNT).equals(
                    l_orderNotifyQueueParams.getSpecifyDiv()))
                {
                    l_strMutualFundSellDiv = WEB3SellDivDef.COUNT_DESIGNATE;
                }
        		//(*) �u���t�v�A���A
                //���M�����ʒm�L���[Params.get�w��()�̖߂�l���h
                //2�F���z�h�̏ꍇ �h3�F���z�w��h���w��  
                else if ((WEB3SpecifyDivDef.MONEY).equals(
                    l_orderNotifyQueueParams.getSpecifyDiv())) 
                {
                    l_strMutualFundSellDiv = WEB3SellDivDef.MONEY_DESIGNATE;
                }
    		}
    		//(*) ����ȊO�̏ꍇ null���w�� 
    		l_tradeOrderNotifyConfirmInterceptor.setMutualFundSellDiv(
    		    l_strMutualFundSellDiv);
    		

    		//����.���M���������ʒm�m��C���^�Z�v�^.set����()���R�[������B
    		Timestamp l_tsExecutionTimestamp = null;
    		//�����F�����F  
    		//(*) �u��W�v�̏ꍇ get������()�̖߂�l���w�肷��B 
    		if (l_blnIsRecruitPossible)
    		{
    		    l_tsExecutionTimestamp = l_tsBizDate;
    		}
    		//(*) �u�抷�v�̏ꍇ 
    		//�g�����M�����}�l�[�W���[.get�抷����()�̖߂�l���w�肷��B 
    		else if (l_blnIsSwtPossible)
    		{
    		    l_tsExecutionTimestamp = l_tsSwtExecutedDate;
    		}
    		//(*) ����ȊO�̏ꍇ �擾�����������w�肷��B 
    		else
    		{
    		    l_tsExecutionTimestamp = l_tsExecTimestamp;
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setExecutionTimestamp(
    		    l_tsExecutionTimestamp);

    		//����.���M���������ʒm�m��C���^�Z�v�^.set���ϋ敪()���R�[������B  
    		//�����F����.���M�����ʒm�L���[Params.get����()�̖߂�l  
            l_tradeOrderNotifyConfirmInterceptor.setSettlementType(
                l_orderNotifyQueueParams.getSettlementDiv());

    		//����.���M���������ʒm�m��C���^�Z�v�^.set���萔���敪()���R�[������B  
    		//�����F����.���M�����ʒm�L���[Params.get���萔���敪()�̖߂�l  
            l_tradeOrderNotifyConfirmInterceptor.setNoCommissionDivision(
                l_orderNotifyQueueParams.getCommissionDiv());

    		//����.���M���������ʒm�m��C���^�Z�v�^.set�����R�[�h�i�抷��j()���R�[������B  
    		//�����F����.���M�����ʒm�L���[Params.get���t�����R�[�h()�̖߂�l  
            l_tradeOrderNotifyConfirmInterceptor.setSwitchingSubjectMutualProductCode(
                l_orderNotifyQueueParams.getSwtProductCode());

    		//����.���M���������ʒm�m��C���^�Z�v�^.set�����敪()���R�[������B
            String l_strRequestDivision = null;
    		//�����F�����敪�F  
            if ((l_blnIsSellPossible) || (l_blnIsSwtPossible))
            {
        		//(*) �u���t�v�A���A
                //���M�����ʒm�L���[Params.get�����敪()�̖߂�l�� �h
                //�󔒁F���h�̏ꍇ �h0�F���h���w��
                if (l_orderNotifyQueueParams.getClaimDiv() != null && 
                        "".equals(l_orderNotifyQueueParams.getClaimDiv().trim()))
                {
                    l_strRequestDivision = WEB3ClaimDivDef.SELL;        
                }
        		//(*) �u���t�v�A���A
                //���M�����ʒm�L���[Params.get�����敪()�̖߂�l�� �h
                //1�F����h�̏ꍇ �h1�F����h���w��
                else if(WEB3ClaimDivDef.BUY.equals(
                    l_orderNotifyQueueParams.getClaimDiv()))
                {
                    l_strRequestDivision = WEB3ClaimDivDef.BUY;        
                }
            }
            //(*) ����ȊO�̏ꍇ null���w��
            l_tradeOrderNotifyConfirmInterceptor.setRequestDivision(
                l_strRequestDivision); 

    		//����.���M���������ʒm�m��C���^�Z�v�^.set�󒍓���()���R�[������B  
    		//�����F����.���M�����ʒm�L���[Params.get��������()�̖߂�l 
            l_tradeOrderNotifyConfirmInterceptor.setAcceptDate(
                l_orderNotifyQueueParams.getCreateDatetime());

    		//����.���M���������ʒm�m��C���^�Z�v�^.set������( )���R�[������B 
            Timestamp l_tsPaymentDate = null;
    		//�����F�������F  
    		//(*) �u��W�v�̏ꍇ�A
            //���M�����ʒm�L���[Params.get������()�̖߂�l���w�肷��B 
            if (l_blnIsRecruitPossible)
            {
                l_tsPaymentDate = l_orderNotifyQueueParams.getPaymentDate();
            }
    		//(*) �u��W�v�łȂ��ꍇ null���w�肷��B
            l_tradeOrderNotifyConfirmInterceptor.setPaymentDate(l_tsPaymentDate);
            
    		//����.���M���������ʒm�m��C���^�Z�v�^.setCPUNo( )���R�[������B 
            String l_strCpuNo = null;
    		//�����FCPUNo�F  
    		//(*) �u��W�v�̏ꍇ�A
            //���M�����ʒm�L���[Params.getCPUNo()�̖߂�l���w�肷��B 
    		//(*) �u��W�v�łȂ��ꍇ null���w�肷��B
            if (l_blnIsRecruitPossible)
            {
                l_strCpuNo = l_orderNotifyQueueParams.getCpuNo();
            }
    		//(*) �u��W�v�łȂ��ꍇ null���w�肷��B
            l_tradeOrderNotifyConfirmInterceptor.setCPUNo(l_strCpuNo);
            
            //1.17  �g�����M�V�K�������e
    		//�g�����M�V�K�������e�𐶐�����B 
    		//[�g�����M�V�K�������e�̃R���X�g���N�^�ɓn���p�����^] 
    		//�㗝���͎ҁF null 
    		//is���t�F 
    		//(*) ����.���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h1�F���t�h 
    		//	  �̏ꍇ false ���w��B 
    		//(*) ����.���M�����ʒm�L���[Params.get�����敪()�̖߂�l���h2�F���t�h 
    		//	  ���́A�h3�F��W�h�̏ꍇ true ���w��B 
    		//�����R�[�h�F ����.���M�����ʒm�L���[Params.get�����R�[�h()�̖߂�l 
    		//�������ʁF �擾������������ 
    		//�������ʃ^�C�v�F
            //[��W�̏ꍇ]
            //(*) �g�����M����.get�w����@@�i��W�j()�̖߂�l���h4�F�����w�� �h�̏ꍇ
            //QuantityTypeEnum.���ʂ��w��B
            //(*) �g�����M����.get�w����@@�i��W�j()�̖߂�l���h3�F���z�w�� �h�̏ꍇ
            //QuantityTypeEnum.���z���w��B
            //[����ȊO]  
    		//(*) ����.���M�����ʒm�L���[Params.get�w��()�̖߂�l���h1�F�����h 
    		//	  QuantityTypeEnum.���ʂ��w��B 
    		//(*) ����.���M�����ʒm�L���[Params.get�w��()�̖߂�l���h2�F���z�h 
    		//�̏ꍇ��QuantityTypeEnum.���z���w�� 
    		//�ŋ敪�F 
    		//(*) ����.���M�����ʒm�L���[Params.get��������敪()�̖߂�l�� 
    		//�h0�F��ʁh�Ȃ��TaxTypeEnum.NORMAL���A�h1�F����h�Ȃ�� 
    		//TaxTypeEnum.SPECIAL��ݒ肷��B
            
            /*�������ʃ^�C�v�擾*/
            QuantityTypeEnum l_orderQuantityType = null;
            if(l_blnIsRecruit){
                if (WEB3BuySellSwtSpecityDivDef
                    .QUANTITY_DESIGNATE
                    .equals(l_strRecruitSpecityDiv))
                {
                    l_orderQuantityType = QuantityTypeEnum.QUANTITY;
                }
                else if (
                    WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(
                        l_strRecruitSpecityDiv))
                {
                    l_orderQuantityType = QuantityTypeEnum.AMOUNT;
                }
            }else{
                //(*) ����.���M�����ʒm�L���[Params.get�w��()�̖߂�l���h
                 //1�F�����hQuantityTypeEnum.���ʂ��w��B
                if ((WEB3SpecifyDivDef.COUNT).equals(
                    l_orderNotifyQueueParams.getSpecifyDiv()))
                {
                    l_orderQuantityType = QuantityTypeEnum.QUANTITY ;      
                }
                //(*) ����.���M�����ʒm�L���[Params.get�w��()�̖߂�l���h
                 //2�F���z�h�̏ꍇ��QuantityTypeEnum.���z���w��
                else if ((WEB3SpecifyDivDef.MONEY).equals(
                    l_orderNotifyQueueParams.getSpecifyDiv())) 
                {
                    l_orderQuantityType = QuantityTypeEnum.AMOUNT ;    
                }
            }
            
            //(*) ����.���M�����ʒm�L���[Params.get��������敪()�̖߂�l��
            TaxTypeEnum l_taxTypeEnum = null;
		    //�h0�F��ʁh�Ȃ��TaxTypeEnum.NORMAL
            if ((WEB3TaxTypeSpecialDef.NORMAL).equals(
                l_orderNotifyQueueParams.getTaxType()))
            {
                l_taxTypeEnum = TaxTypeEnum.NORMAL;
            }
            //1�F����h�Ȃ��TaxTypeEnum.SPECIAL���w�肷��B 
            else if ((WEB3TaxTypeSpecialDef.SPECIAL).equals(
                l_orderNotifyQueueParams.getTaxType()))
            {
                l_taxTypeEnum = TaxTypeEnum.SPECIAL;
            } 

            boolean l_blnIsBuySpec = 
                WEB3MFTradeTypeDef.BUY.equals(l_orderNotifyQueueParams.getTradeType()) ||                 
                WEB3MFTradeTypeDef.RECRUIT.equals(l_orderNotifyQueueParams.getTradeType());
            
            //�V�K�������e�̐���
            WEB3MutualFundNewOrderSpec  l_mutualFundNewOrderSpec = 
                new WEB3MutualFundNewOrderSpec(
                    null,
                    l_blnIsBuySpec,
                    l_orderNotifyQueueParams.getProductCode(),
                    l_dblOrderQuantity,
                    l_orderQuantityType,
                    l_taxTypeEnum);

            //1.18 createNewOrderId( )
            long l_lngOrderId = l_orderManager.createNewOrderId();
            
            //1.19 setTimestamp( )
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //1.20 submitNewOrder
    		//[submitNewOrder�ɓn���p�����^] 
    		//�⏕�����F�@@�擾�����⏕�����I�u�W�F�N�g 
    		//�����^�C�v�F ProductTypeEnum.�����M�� 
    		//�V�K�������e�F �g�����M�V�K�������e 
    		//����ID�F �擾��������ID 
    		//����p�X���[�h�F 
    		//WEB3Crypt.decrypt()�̖߂�l��ݒ肷��B 
    		//�mdecrypt�ɓn���p�����^] 
    		//�Í���������F 
            //�擾�����ڋq�I�u�W�F�N�g.getTradingPassword()�̖߂�l 
    		//is�����R���ȗ��F true
            //����p�X���[�h
            WEB3Crypt l_webCrypt = new WEB3Crypt();
            
            //�g�����M�����}�l�[�W��.submitNewOrder() �̖߂�l   
            OrderSubmissionResult l_orderSubmissionResult = 
                l_orderManager.submitNewOrder(
                    l_subAccount,
                    ProductTypeEnum.MUTUAL_FUND,
                    l_mutualFundNewOrderSpec,
                    l_lngOrderId,
                    l_webCrypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);
            
            //1.21 ���g�����M�����}�l�[�W��.submitNewOrder()�̖߂�l
            //.getProcessingResult().isSuccessfulResult()==false�̏ꍇ��
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult() == false)
             {
                 log.debug("�Y���g�����M�����}�l�[�W��.submitNewOrder()�̖߂�l" +
                    ".getProcessingResult().isSuccessfulResult()" +
                    "==false�̏ꍇ�B");
                 throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�V�K�������s");
             }
            
            //1.22 �]�͍Čv�Z(�⏕���� : �⏕����)
			WEB3GentradeSubAccount l_genSubAccount = 
			    (WEB3GentradeSubAccount) l_subAccount;
			WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService = 
				(WEB3TPTradingPowerReCalcService) Services.getService(
					WEB3TPTradingPowerReCalcService.class);
			l_tpTradingPowerReCalcService.reCalcTradingPower(l_genSubAccount); 
	        log.exiting(STR_METHOD_NAME);
			
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (cancel����)<BR>
     * ��������������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���M���������ʒm�j��������v �Q�ƁB
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u�i���M���������ʒm�j��������v: <BR>
     *    1.2.5(�߂�l == �h�I���w��h �̏ꍇ�A��O���X���[����A<BR>
     *       ��O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_02297<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_orderNotifyQueueParams - ���M�����ʒm�L���[Params<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    protected void cancelOrder(HostXbmfOrderNotifyParams l_orderNotifyQueueParams) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelOrder(HostXbmfOrderNotifyParams l_orderNotifyQueueParams)" ;
        log.entering(STR_METHOD_NAME);
        
        //1.1 
		//�ȉ��̏����ŁA�����P�ʂ��擾����B
		//[����]
		//CPUNo = ����.���M�����ʒm�L���[Params.CPUNo
        List l_lisRowsMfOrderUnit = new ArrayList();
        String l_strWhereA = "cpu_no = ? ";
        Object[] l_bindVarsA = {l_orderNotifyQueueParams.getCpuNo()}; 
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRowsMfOrderUnit = l_queryProcessor.doFindAllQuery(
                MutualFundOrderUnitRow.TYPE,
                l_strWhereA,
                null, 
                l_bindVarsA);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
        
        //1.2.1 get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
		//�ڋq�I�u�W�F�N�g���擾����B 
		//[����] 
		//�،���ЃR�[�h�F ����.���M�����ʒm�L���[Params.��ЃR�[�h 
		//���X�R�[�h�F ����.���M�����ʒm�L���[Params.���X�R�[�h 
		//�����R�[�h�F ����.���M�����ʒm�L���[Params.�ڋq�R�[�h 
        
        //�g���A�J�E���g�}�l�[�W���̎擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount) l_accMgr.getMainAccount(
                l_orderNotifyQueueParams.getInstitutionCode(), 
                l_orderNotifyQueueParams.getBranchCode(), 
                l_orderNotifyQueueParams.getAccountCode());
        
        //1.2.2 is�M�p�����J��(�ٍϋ敪 : String)
		//�M�p�������J�݂��Ă��邩�ǂ������`�F�b�N����B 
		//[����] 
		//�ٍϋ敪�F �h�w��Ȃ��h 
		boolean l_blnisMarginAccountEstablished =
			l_mainAccount.isMarginAccountEstablished(
			    WEB3GentradeRepaymentDivDef.DEFAULT);
        
        //1.2.3 getSubAccount(arg0 : SubAccountTypeEnum)
		//�⏕�����I�u�W�F�N�g���擾����B 
		//[����] 
		//arg0�F �i�ȉ��̂Ƃ���j 

	    WEB3MutualFundProduct l_mutualFundProduct =  null;
	    SubAccount l_subAccount = null;
		try
        {
    		//is�M�p�����J��()�̖߂�l==true�̏ꍇ�A
		    //SubAccountTypeEnum.�ۏ؋����� 
			if (l_blnisMarginAccountEstablished)
            {
                l_subAccount = 
                    l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
			//is�M�p�����J��=false �̏ꍇ�A
			//SubAccountTypeEnum.������������i�a����j
			else
			{
				l_subAccount = 
				    l_mainAccount.getSubAccount(
				        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
			}
			
			//1.2.4 get���M����(Institution, String)
			//�g�����M�����}�l�[�W�����擾����B
			//�����I�u�W�F�N�g���擾����B 
			//[����] 
			//�،���ЁF �擾�����ڋq.getInstitution()�̖߂�l 
			//�����R�[�h�F ����.���M�����ʒm�L���[Params.�����R�[�h 
            WEB3MutualFundProductManager l_mutualFundProductManager = 
                (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getProductManager();
            l_mutualFundProduct = 
                (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                    l_mainAccount.getInstitution(), 
                    l_orderNotifyQueueParams.getProductCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.2.5  get�w����@@�i��W�j
        //�߂�l == �h�I���w��h �̏ꍇ�A��O���X���[����B
        if(WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(
            l_mutualFundProduct.getRecruitSpecityDiv()))
        {
			log.debug("�w����@@�i��W�j�G���[(�h�I���w��h���w��s��)�B");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02297,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"�w����@@�i��W�j�G���[(�h�I���w��h���w��s��)�B");
        }
        
        //1.2 (*)�����P�ʂ��擾�ł��Ȃ������ꍇ�iWEB����̒����̏ꍇ�j
        if(l_lisRowsMfOrderUnit == null || l_lisRowsMfOrderUnit.size() == 0)
        {
            //1.2.6
    		//�ȉ��̏����ŁA�����P�ʂ��擾����B
    		//[����]
    		//����ID = �擾�����ڋq.getAccontId()�̖߂�l
    		//�⏕����ID = �擾�����⏕����.getSubAccountId()�̖߂�l
    		//����ID = �擾��������.getProductId()�̖߂�l
    		//������� = �h��W�h
    		//������� != �h�������s�i�V�K�����j�h or �h�����ρi��������j�h
    		//������ = ����.���M�����ʒm�L���[Params.������
    		//�������� = �i�ȉ��̂Ƃ���j
    		//get�w����@@�i��W�j�̖߂�l == �h���z�w��h �̏ꍇ�A
            //����.���M�����ʒm�L���[Params.�T�Z���z
    		//get�w����@@�i��W�j�̖߂�l == �h�����w��h �̏ꍇ�A
            //(*)�擾�����g�����M����.get���͒P��()�̖߂�l���h1�F1�h�̏ꍇ
            //����.���M�����ʒm�L���[Params.��������
            //(*)�擾�����g�����M����.get���͒P��()�̖߂�l���h2�F1���h�̏ꍇ
            //����.���M�����ʒm�L���[Params.����
    		//[�\�[�g��]
    		//�󒍓����̏���
    		//���������擾�ł����ꍇ�́A�擪�̂��́i�󒍓������Â����́j�Ƃ���B
            String l_strWhereB = 
                "account_id = ? and sub_account_id = ? and product_id = ? and order_type = ? " +
                "and (order_status != ? and order_status != ?)  " +
                "and payment_date = ? and quantity = ?";
            
            //��������
            double l_dblOrderQuantity = 0.0D;
            //get�w����@@�i��W�j�̖߂�l == �h�����w��h �̏ꍇ
            if(WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                l_mutualFundProduct.getRecruitSpecityDiv()))
            {
                if ((WEB3InputUnitDef.ONE).equals(
                    l_mutualFundProduct.getInputUnit()))
                {
                    l_dblOrderQuantity = 
                        l_orderNotifyQueueParams.getQuantity();
                } 
                else if ((WEB3InputUnitDef.TEN_THOUSAND).equals(
                    l_mutualFundProduct.getInputUnit()))
                {
                    l_dblOrderQuantity = 
                        l_orderNotifyQueueParams.getQuantity() * 10000;
                }
            }
    		//get�w����@@�i��W�j�̖߂�l == �h���z�w��h �̏ꍇ�A 
            //����.���M�����ʒm�L���[Params.�T�Z���z
            else if ((WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE).equals(
                l_mutualFundProduct.getRecruitSpecityDiv())) 
            {
                l_dblOrderQuantity = 
                    l_orderNotifyQueueParams.getEstimatedPrice();
            }
            String l_strSortKey = "received_date_time";
            
            Object[] l_bindVarsB = 
                {new Long(l_subAccount.getAccountId()),
                new Long(l_subAccount.getSubAccountId()),
                new Long(l_mutualFundProduct.getProductId()),
                OrderTypeEnum.MF_RECRUIT, 
                OrderStatusEnum.NOT_ORDERED, 
                OrderStatusEnum.CANCELLED,
                l_orderNotifyQueueParams.getPaymentDate(),
                new Double(l_dblOrderQuantity)};   
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRowsMfOrderUnit = l_queryProcessor.doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_strWhereB,
                    l_strSortKey,
                    null, 
                    l_bindVarsB);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);    
            }
            
            if (l_lisRowsMfOrderUnit == null || l_lisRowsMfOrderUnit.size() == 0)
            {
    			log.debug("�e�[�u���ɊY������f�[�^������܂���B");
    			throw new WEB3SystemLayerException(
    				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
    				this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        MutualFundOrderUnitParams l_mfOrderUnitParams = 
            (MutualFundOrderUnitParams) l_lisRowsMfOrderUnit.get(0);
        
        //1.3 ���M��t�m��C���^�Z�v�^( )
        WEB3MutualFundAcceptConfirmInterceptor 
        	l_mfAcceptConfirmInterceptor = 
        	    new WEB3MutualFundAcceptConfirmInterceptor();
        
        //1.4 set�����G���[���R�R�[�h(String)
		//�G���[���R�R�[�h��ݒ肷��B 
		//[����] 
		//�G���[�R�[�h�F "0000"�i����j 
        l_mfAcceptConfirmInterceptor.setOrderErrorReasonCode(
            WEB3GftErrorReasonCodeDef.NORMAL);

	    //1.5 setThreadLocalPersistenceEventInterceptor(
        //arg0 : MutualFundOrderManagerPersistenceEventInterceptor)
        //�g�����M�����}�l�[�W���擾
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_mfAcceptConfirmInterceptor);

        //1.6 DefaultCancelOrderAcceptedMarketResponseMessage(arg0 : long)
        DefaultCancelOrderAcceptedMarketResponseMessage 
        	l_defaultMarketResponseMessage =
        	    new DefaultCancelOrderAcceptedMarketResponseMessage(
        	        l_mfOrderUnitParams.getOrderId());
        
        //1.7 process(arg0 : CancelOrderAcceptedMarketResponseMessage)
        MarketAdapter l_marketAdapter =
            l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getMarketAdapter();
        MutualFundMarketResponseReceiverCallbackService l_marketCallbackService =
            (MutualFundMarketResponseReceiverCallbackService) l_marketAdapter.getMarketResponseReceiverCallbackService();
        ProcessingResult l_processingResylt = 
            l_marketCallbackService.process(l_defaultMarketResponseMessage);
        if (l_processingResylt.isFailedResult())
        {
            log.debug("������t�������s�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00395,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "������t�������s�ł���");   
        }   

        //1.8 �]�͍Čv�Z(�⏕���� : �⏕����)
        // [�]�͍Čv�Z�ɓn���p�����^] 
        // �@@�⏕�����F�擾�����⏕�����I�u�W�F�N�g
		WEB3GentradeSubAccount l_genSubAccount = 
		    (WEB3GentradeSubAccount) l_subAccount;
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService = 
            (WEB3TPTradingPowerReCalcService) Services.getService(
                WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_genSubAccount);
        log.exiting(STR_METHOD_NAME);
    }
}
@
