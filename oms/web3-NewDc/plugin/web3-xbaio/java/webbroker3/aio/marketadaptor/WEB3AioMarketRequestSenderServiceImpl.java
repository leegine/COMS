head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.36.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioMarketRequestSenderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���E���o�Ƀ��N�G�X�g���M�T�[�r�X�N���X(WEB3AioMarketRequestSenderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 �����(���u) �V�K�쐬
Revesion History : 2004/10/27 ���z (���u) ���r���[
Revesion History : 2004/12/06 ���E (���u) �c�Ή�
Revesion History : 2005/01/18 �ʉ� (SRA) ����e�X�g��QU02724�Ή�
Revesion History : 2005/01/18 �ʉ� (SRA) ����e�X�g��QU02728�Ή�
Revesion History : 2006/01/12 ��O�� (���u) �d�l�ύX�E���f��433
Revesion History : 2006/05/08 ��O�� (���u) ����e�X�g��QU02836�Ή�
Revesion History : 2006/07/12 ��O�� (���u) �d�l�ύX ���f��598
Revesion History : 2006/08/01 ���(SCS)�@@���f��No.609�EDB�X�V�d�l 096,097�Ή�
Revesion History : 2006/08/28 ���(SCS)�@@���f��No.632�EDB�X�V�d�l 108�Ή�
Revesion History : 2006/08/31 �Ԑi (���u) ���f��No.618
Revesion History : 2006/09/27 ��O�� (���u) DB�X�V�d�l No.120,121�Ή�
Revesion History : 2006/10/18 �Ԑi (���u) ���f��No.624
Revesion History : 2006/10/19 �Ԑi (���u) ���f��No.660
Revesion History : 2006/10/19 �Ԑi (���u) DB�X�V�d�l No.124�Ή�
Revesion History : 2006/10/24 ���G�� (���u) ���f��No.676�@@DB�X�V�d�l No.128�Ή�
Revesion History : 2006/10/24 ���G�� (���u) ���f��No.674
Revesion History : 2006/10/26 �Ԑi (���u) DB�X�V�d�l No.126�Ή�
Revesion History : 2006/11/06 ���(SCS)�@@DB�X�V�d�l No.133�Ή�
Revesion History : 2007/02/10 ���G��(���u)�@@���f��No.706,DB�X�V�d�l No.135�Ή�
Revesion History : 2007/03/12 �����q(���u)�@@���f��No.711,No.712,No.714  DB�X�V�d�l No.136,No.137�Ή�
Revesion History : 2007/03/12 �����q(���u)�@@DB�X�V�d�l No.138
Revesion History : 2007/03/28 �����q(���u)�@@�����̖��  No.010
Revesion History : 2007/03/29 �����q(���u)�@@DB�X�V�d�l No.139
Revesion History : 2007/07/12 ���^�](���u) �d�l�ύX���f��No.737 No.738
Revesion History : 2007/07/28 �Ј���(���u) �d�l�ύX���f��No.747
Revesion History : 2008/09/24 ���u��(���u) �d�l�ύX���f��No.991,1074 �c�a�X�V�d�l No.176,177
Revesion History : 2008/11/12 �哈(SCS) DB�X�V�d�l No.203
Revesion History : 2008/12/03 �哈(SCS) DB�X�V�d�l No.205
Revesion History : 2008/12/23 ���u��(���u) DB�X�V�d�l No.206
Revesion History : 2009/03/12 ���u��(���u) �d�l�ύX���f��No.1114 �c�a�X�V�d�l No.209,210,212,222
*/

package webbroker3.aio.marketadaptor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioMarketRequestSenderService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.market.messages.AioNewOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.aio.WEB3AioProductManager;
import webbroker3.aio.data.BankCashTransferStatusDao;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.data.CompBankConditionDao;
import webbroker3.aio.data.CompBankConditionRow;
import webbroker3.aio.data.HostCashTransOrderParams;
import webbroker3.aio.data.HostForeignCashTransOrderParams;
import webbroker3.aio.data.HostMrgsecTransferParams;
import webbroker3.aio.data.HostPaymentOrderDao;
import webbroker3.aio.data.HostPaymentOrderPK;
import webbroker3.aio.data.HostPaymentOrderParams;
import webbroker3.aio.data.HostPaymentOrderRow;
import webbroker3.aio.data.HostTransferOrderDao;
import webbroker3.aio.data.HostTransferOrderPK;
import webbroker3.aio.data.HostTransferOrderParams;
import webbroker3.aio.data.HostTransferOrderRow;
import webbroker3.aio.define.WEB3AioDescriptionDef;
import webbroker3.aio.define.WEB3AioOriginalTransferDateBlankDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioDeliveryTypeDef;
import webbroker3.common.define.WEB3AioHostCommodityDef;
import webbroker3.common.define.WEB3AioHostStatusDef;
import webbroker3.common.define.WEB3AioPaymentApplicationDivDef;
import webbroker3.common.define.WEB3AioTransferFlagDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3CurrencyCodeDef;
import webbroker3.common.define.WEB3DepositDef;
import webbroker3.common.define.WEB3ForceTransferDef;
import webbroker3.common.define.WEB3ForeignCashRemarkCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3InputUnitDef;
import webbroker3.common.define.WEB3InterLockDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PaymentApplyTriggerDef;
import webbroker3.common.define.WEB3RemarkCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.FinInstitutionDao;
import webbroker3.gentrade.data.FinInstitutionPK;
import webbroker3.gentrade.data.FinInstitutionRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���o���E���o�Ƀ��N�G�X�g���M�T�[�r�X)<BR>
 * ���o���E���o�Ƀ��N�G�X�g���M�T�[�r�X�N���X<BR>
 * �iAioMarketRequestSenderService�̎����N���X�j<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AioMarketRequestSenderServiceImpl 
    implements AioMarketRequestSenderService 
{

    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioMarketRequestSenderServiceImpl.class);

    /**
     * @@roseuid 415A471501A3
     */
    public WEB3AioMarketRequestSenderServiceImpl() 
    {
     
    }
    
    /**
     * (�V�K�������M)<BR>
     * �isend(AioNewOrderMarketRequestMessage)�̎����j <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i���N�G�X�g���M�j�V�K�������M�v�Q�ƁB <BR>
     * @@param l_cashTransOrderRequest - (���o���������N�G�X�g)<BR>
     * 
     * @@return MarketRequestSendResult
     * @@roseuid 40E2436001BD
     */
    public MarketRequestSendResult send(AioNewOrderMarketRequestMessage l_cashTransOrderRequest) 
    {
        
        final String l_strMethodName = "send("
            + "AioNewOrderMarketRequestMessage l_cashTransOrderRequest)";
        log.entering(l_strMethodName);
        if (l_cashTransOrderRequest == null)
        {
            log.debug("�p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // 1.1. getOrderId( )
        long l_lngOrderId = l_cashTransOrderRequest.getOrderId();
        // 1.2. �����C���X�^���X�𐶐�����
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.AIO);
        AioOrderManager l_orderManager = 
            (AioOrderManager)l_tradingModule.getOrderManager();
        //1.3. getOrderUnits( ) ---�����P�ʃI�u�W�F�N�g���擾����B
        // �����X�g��1�Ԗڂ̗v�f���擾����B
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            log.debug("Error In  getOrderUnits() with Order_Id = " + l_lngOrderId);
            WEB3BaseException l_baseEx = new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + l_strMethodName);
            ProcessingResult l_sendResult =
                ProcessingResult.newFailedResultInstance(l_baseEx.getErrorInfo());
            MarketRequestSendResult l_returnResult = 
                DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
            return l_returnResult;
        }
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];
        try
        {
            //1.4 �����P��.������� == 1001�F�o������ and �o���\���敪 == null or mf�i���M�j
            //     and �����o�H�敪 != 9�FHOST �̏ꍇ
            AioOrderUnitRow l_orderUnitRow = 
                (AioOrderUnitRow)l_aioOrderUnit.getDataSourceObject();
            if (OrderTypeEnum.CASH_OUT.equals(l_orderUnitRow.getOrderType()) 
                && (l_orderUnitRow.getPaymentApplicationDiv() == null
                || WEB3AioPaymentApplicationDivDef.MF.equals(l_orderUnitRow.getPaymentApplicationDiv()))
                && !WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
            {
                //1.4.1 �V�K�o���������M(AioOrderUnit)---- �o�������̑��M�������s���B
                this.openCashoutOrderSend(l_aioOrderUnit);
            }
            //1.5 �����P��.������� == 1002�F�������� 
            //     and �����o�H�敪 != 9�FHOST �̏ꍇ
            if (OrderTypeEnum.CASH_IN.equals(l_orderUnitRow.getOrderType()) 
                    && !WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
            {
                //1.5.1 �V�K�����������M(AioOrderUnit)---- ���������̑��M�������s���B �B
                this.openCashinOrderSend(l_aioOrderUnit);
            }
            //�i�����P��.������� == 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j
            //                or == 1006�F�U�֒���(�M�p�ۏ؋�����a���)
            //                or == 1007�F�U�֒���(�a������犔��؋���)
            //                or == 1008�F�U�֒���(����؋�������a���)
            //                or == 1011�i�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�j
            //                or == 1012�i�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�j
            //                or == 1013�i�O�������U�֒����i�a�������O�����������j�j�j
            //                or == 1021�iCFD�U�֒����i�a�������CFD�����j�j
            //                or == 1022�iCFD�U�֒����iCFD��������a����j�j
            //     and �����o�H�敪 != 9�FHOST �̏ꍇ
            if ((OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD.equals(l_orderUnitRow.getOrderType()))
                && !WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
            {
                //1.6.1 �V�K�U�֒������M(AioOrderUnit)---- �U�֒����̑��M�������s���B
                this.openTransferOrderSend(l_aioOrderUnit);
            } 
            
            //============remain zhou-yong No.2 begin ===========
            
            //1.7) �i �����P��.������� = 1009�i�،��U�֒����i�ی�a�肩���p�L���،��j�j or
            //        �����P��.������� = 1010�i�،��U�֒����i��p�L���،�����ی�a��j�j �j and
            //        �����P��.�����o�H�敪 != 9�iHOST)
            //        �̏ꍇ
            if((OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT.equals(l_orderUnitRow.getOrderType()))
                && !WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()) )
            {
                //�V�K�،��U�֒������M(AioOrderUnit)
                //�A�C�e���̒�`
                //�،��U�֒����̑��M�������s���B
                //[����] 
                //�����P�ʁF �����P�ʃI�u�W�F�N�g 
                this.openSecurityTransferOrderSend(l_aioOrderUnit);

            }
            
            //============remain zhou-yong No.2 end ===========            
            
            // �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h������I�������ꍇ�A
            //  DefaultMarketRequestSendResult.newSuccessResultInstance()�̖߂�l��Ԃ��B
            MarketRequestSendResult l_sendResult = 
                DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
            return l_sendResult;
        }
        catch (WEB3BaseException l_ex)
        {
            // �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h����O���X���[�����ꍇ
            // �|ProcessingResult.newFailedResultInstance()���R�[������ProcessingResult�I�u�W�F�N�g�𐶐�����B
            // �|DefaultMarketRequestSendResult.newFailedResultInstance()�̖߂�l��Ԃ��B
            ProcessingResult l_sendResult =
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo());
            MarketRequestSendResult l_returnResult = 
                DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
            return l_returnResult;
        }
        finally
        {
            log.exiting(l_strMethodName);
        }
    }
    
    /**
     * (����������M)<BR>
     * �isend(CancelOrderMarketRequestMessage, boolean)�̎����j <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i���N�G�X�g���M�j����������M�v�Q�ƁB <BR>
     * @@param l_orderCancelRequest - (����������N�G�X�g)<BR>
     * 
     * @@param l_isMarketNoSend - (is�s�ꖢ���M)<BR>
     * �s�ꖢ���M���ǂ����̃t���O<BR>
     * 
     * @@return MarketRequestSendResult
     * @@roseuid 40E2436800A4
     */
    public MarketRequestSendResult send(
            CancelOrderMarketRequestMessage l_orderCancelRequest, 
            boolean l_isMarketNoSend) 
    {
        final String l_strMethodName = "send("
            + "CancelOrderMarketRequestMessage l_orderCancelRequest, "
            + "boolean l_isMarketNoSend)";
        log.entering(l_strMethodName);
        if (l_orderCancelRequest == null)
        {
            log.debug("�p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // 1.1 getSubAccount( ) ---- �⏕�����I�u�W�F�N�g���擾����B
        SubAccount l_subAccount = l_orderCancelRequest.getSubAccount();
        // 1.2. getOrderId( )
        long l_lngOrderId = l_orderCancelRequest.getOrderId();
        // 1.3. �����C���X�^���X�𐶐�����
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.AIO);
        AioOrderManager l_orderManager = 
            (AioOrderManager)l_tradingModule.getOrderManager();
        //1.4. getOrderUnits( ) ---�����P�ʃI�u�W�F�N�g���擾����B
        // �����X�g��1�Ԗڂ̗v�f���擾����B
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            log.debug("Error In  getOrderUnits() with Order_Id = " + l_lngOrderId);
            WEB3BaseException l_baseEx = new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + l_strMethodName);
            ProcessingResult l_sendResult =
                ProcessingResult.newFailedResultInstance(l_baseEx.getErrorInfo());
            MarketRequestSendResult l_returnResult = 
                DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
            return l_returnResult;
        }
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];
        //1.5 getDataSourceObject( ) ---- �����P�ʍs�I�u�W�F�N�g���擾����B
        AioOrderUnitRow l_orderUnitRow = 
            (AioOrderUnitRow)l_aioOrderUnit.getDataSourceObject();
        //1.6 �����P��.�����J�e�S�� == 9�i���o���j �̏ꍇ
        if (OrderCategEnum.CASH_IN_OUT.equals(l_orderUnitRow.getOrderCateg()))
        {
            //�P�j�o�����������L���[Params�C���X�^���X�𐶐�����B 
            HostPaymentOrderParams l_params = new HostPaymentOrderParams();
            //�Q�j�o�����������L���[Params�Ƀv���p�e�B���Z�b�g����B 
            //�f�[�^�R�[�h request_code        �o�������FGI801
            l_params.setRequestCode(WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER);
            l_params.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            l_params.setBranchCode(l_subAccount.getMainAccount().getBranch().getBranchCode());
            l_params.setAccountCode(l_subAccount.getMainAccount().getAccountCode());
            l_params.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());
            // deleteRow(Row() --- ������ꂽ�����ɊY������o�����������L���[�e�[�u���̃��R�[�h���폜����B
            try
            {
                // test log
                log.debug("����������M#�o�����������L���[Params");
                
                HostPaymentOrderDao.findRowByPk(
                    (HostPaymentOrderPK) l_params.getPrimaryKey());

                WEB3DataAccessUtility.deleteRow(l_params);
            }
            catch (DataFindException l_ex)
            {
                //��Q�Ή� NO_U01828
                //HostPaymentOrderDao.findRowByPk()�ɂ��Y���f�[�^�����݂��Ȃ��ꍇ
                //������s�킸�Ɏ��̏������s���i�T�[�r�X���p�Ή��j
                log.debug("HostPaymentOrderDao.findRowByPk() - DataFindException -");
            }
            catch (DataQueryException l_ex)
            {
                // �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h����O���X���[�����ꍇ
                // �|ProcessingResult.newFailedResultInstance()��
                //      �R�[������ProcessingResult�I�u�W�F�N�g�𐶐�����B
                // �|DefaultMarketRequestSendResult.newFailedResultInstance()�̖߂�l��Ԃ��B
                log.error("Error In ������ꂽ������" +
                        "�Y������o�����������L���[�e�[�u���̃��R�[�h���폜����B", l_ex);
                
                WEB3BaseException l_baseEx = new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + l_strMethodName,
                        l_ex.getMessage(),
                        l_ex);
                ProcessingResult l_sendResult =
                    ProcessingResult.newFailedResultInstance(l_baseEx.getErrorInfo());
                MarketRequestSendResult l_returnResult = 
                    DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
                return l_returnResult;
            }
            catch (DataNetworkException l_ex)
            {
                log.error("Error In ������ꂽ������" +
                        "�Y������o�����������L���[�e�[�u���̃��R�[�h���폜����B", l_ex);
                WEB3BaseException l_baseEx = new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + l_strMethodName,
                        l_ex.getMessage(),
                        l_ex);
                ProcessingResult l_sendResult =
                    ProcessingResult.newFailedResultInstance(l_baseEx.getErrorInfo());
                MarketRequestSendResult l_returnResult = 
                    DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
                return l_returnResult;
            }
        }
        
        //1.7 �����P��.�����J�e�S�� == 13�i�U�ցjor 
        //    �����P��.�����J�e�S�� == 15�i�ב֕ۏ؋��U�ցj
        if (OrderCategEnum.CASH_TRANSFER.equals(l_orderUnitRow.getOrderCateg()) ||
            OrderCategEnum.FX.equals(l_orderUnitRow.getOrderCateg()))
        {
            //�P�j�U�֐��������L���[Params�̃C���X�^���X�𐶐�����B
            HostTransferOrderParams l_hostTransferOrderParams = new HostTransferOrderParams();
            //�Q�j�U�֐��������L���[Params�Ɉȉ��̃v���p�e�B���Z�b�g����B 
            //�f�[�^�R�[�h = "GI806"(�ۏ؋��U�֐���)
            l_hostTransferOrderParams.setRequestCode(WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ORDER);
            //�،���ЃR�[�h = �⏕����.getInstitution().getInstitutionCode()�̖߂�l
            l_hostTransferOrderParams.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            //���X�R�[�h = �⏕����.get����X().getBranchCode()�̖߂�l
            l_hostTransferOrderParams.setBranchCode(l_subAccount.getMainAccount().getBranch().getBranchCode());
            //�ڋq�R�[�h = �⏕����.getMainAccount().getAccountCode()�̖߂�l
            l_hostTransferOrderParams.setAccountCode(l_subAccount.getMainAccount().getAccountCode());
            //���ʃR�[�h = �����P��Params.���ʃR�[�h
            l_hostTransferOrderParams.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());

            log.debug("�،���ЃR�[�h = " + l_subAccount.getInstitution().getInstitutionCode());
            log.debug("���X�R�[�h = " + l_subAccount.getMainAccount().getBranch().getBranchCode());
            log.debug("�ڋq�R�[�h = " + l_subAccount.getMainAccount().getAccountCode());
            log.debug("���ʃR�[�h = " + l_orderUnitRow.getOrderRequestNumber());
            
            // deleteRow(Row() --- ������ꂽ�����ɊY������o�����������L���[�e�[�u���̃��R�[�h���폜����B
            try
            {
                // test log
                //log.debug("����������M#�U�֐��������L���[Params = " + 
                //        HostTransferOrderDao.findRowByPk(
                //        (HostTransferOrderPK)l_hostTransferOrderParams.getPrimaryKey()));
                
				HostTransferOrderDao.findRowByPk(
					(HostTransferOrderPK)l_hostTransferOrderParams.getPrimaryKey());
                   
                //1.7.1 ������ꂽ�����ɊY������U�֐��������L���[�e�[�u���̃��R�[�h���폜����B
                WEB3DataAccessUtility.deleteRow(l_hostTransferOrderParams);
            }
			catch (DataFindException l_ex)
			{
				//HostTransferOrderDao.findRowByPk()�ɂ��Y���f�[�^�����݂��Ȃ��ꍇ
				//������s�킸�Ɏ��̏������s��
				log.debug("HostTransferOrderDao.findRowByPk() - DataFindException -");
			}
            catch (DataQueryException l_ex)
            {
                // �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h����O���X���[�����ꍇ
                // �|ProcessingResult.newFailedResultInstance()���R�[������ProcessingResult�I�u�W�F�N�g�𐶐�����B
                // �|DefaultMarketRequestSendResult.newFailedResultInstance()�̖߂�l��Ԃ��B
                log.error("Error In ������ꂽ������" +
                        "�Y������U�֐��������L���[�e�[�u���̃��R�[�h���폜����B", l_ex);
                WEB3BaseException l_baseEx = new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + l_strMethodName,
                        l_ex.getMessage(),
                        l_ex);
                ProcessingResult l_sendResult =
                    ProcessingResult.newFailedResultInstance(l_baseEx.getErrorInfo());
                MarketRequestSendResult l_returnResult = 
                    DefaultMarketRequestSendResult.newFailedResultInstance(
                        l_sendResult);
                return l_returnResult;
            }
            catch (DataNetworkException l_ex)
            {
                log.error("Error In ������ꂽ������" +
                        "�Y������o�����������L���[�e�[�u���̃��R�[�h���폜����B", l_ex);
                WEB3BaseException l_baseEx = new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + l_strMethodName,
                        l_ex.getMessage(),
                        l_ex);
                ProcessingResult l_sendResult =
                    ProcessingResult.newFailedResultInstance(l_baseEx.getErrorInfo());
                MarketRequestSendResult l_returnResult = 
                    DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
                return l_returnResult;
            }
        }
        // �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h������I�������ꍇ�A
        //  DefaultMarketRequestSendResult.newSuccessResultInstance()�̖߂�l��Ԃ��B
        MarketRequestSendResult l_sendResult = 
            DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
        return l_sendResult;
    }
    
    /**
     * (create���������L���[�f�[�^)<BR>
     * ���������̃L���[�f�[�^�̐����iDB�o�^�j���s���B<BR>
     * <BR>
     * �P�j���o���`�[�L���[Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j���o���`�[�L���[Params�Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     *   ���ڍׂ́ADB�X�V�d�l�u���o���`�[�L���[�e�[�u��.xls�v�Q��<BR>
     * <BR>
     * ������<BR>
     * ���o�������P��.�����o�H���h�����A�g�h�̏ꍇ <BR>
     * <BR>
     * �E���Z�@@�փe�[�u�����ȉ��̏����Ō����B <BR>
     * [����] <BR>
     * �،���ЃR�[�h = �،����.�،���ЃR�[�h <BR>
     * ���Z�@@�փR�[�h = ���o�������P��.�U�֋L�q <BR>
     * <BR>
     * �ȉ��̃v���p�e�B���Z�b�g <BR>
     * �E���o���`�[�L���[Params.���o�����@@ <BR>
     * = ���Z�@@�փe�[�u��.���o���`�[�L���[�p���o�����@@ <BR>
     * �E�����`�[�L���[Params.����R�[�h <BR>
     * = ���Z�@@�փe�[�u��.���o���`�[�L���[�p����R�[�h <BR>
     * <BR>
     * ���ڍׂ́ADB�X�V�d�l�u��s�����ʒm_���o���`�[�L���[�e�[�u��.xls�v�Q�� <BR>
     * �R�j���o���`�[�L���[Params�̓��e��DB�ɓo�^����B<BR>
     * <BR>
     *   WEB3DataAccessUtility.insertRow()<BR>
     * <BR>
     *   [����]<BR>
     *   ���o���`�[�L���[Params<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40F647780100
     */
    protected void createCashinOrderQueueData(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String l_strMethodName = "createCashinOrderQueueData"
            + "(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);
        //�P�j���o���`�[�L���[Params�C���X�^���X�𐶐�����B
        HostCashTransOrderParams l_params = new HostCashTransOrderParams();
        //�Q�j���o���`�[�L���[Params�Ƀv���p�e�B���Z�b�g����B 
        //���ڍׂ́ADB�X�V�d�l�u���o���`�[�L���[�e�[�u��.xls�v�Q�� 
        //�f�[�^�R�[�h:  ���������FGI803
        l_params.setRequestCode(WEB3HostRequestCodeDef.AIO_SLIP_SERVE);
        try 
        {
            AccountManager l_accMgr = (AccountManager) GtlUtils.getAccountManager();
            log.debug(">>>>>>>>>WEB3GentradeAccountManager");
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_orderUnit.getAccountId());
            log.debug(">>>>>>>>>MainAccount");
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode =" + l_strInstitutionCode);
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            log.debug("l_strBranchCode =" + l_strBranchCode);
            String l_strAccountCode = l_mainAccount.getAccountCode();
            log.debug("l_strAccountCode =" + l_strAccountCode);
            
            //�،���ЃR�[�h:�����P��.���X�h�c�ɊY�����镔�X.�،���ЃR�[�h
            l_params.setInstitutionCode(l_strInstitutionCode);
            //���X�R�[�h:   �����P��.���X�h�c�ɊY�����镔�X.���X�R�[�h
            l_params.setBranchCode(l_strBranchCode);
            //�ڋq�R�[�h:   �����P��.�ڋq�h�c�ɊY������ڋq.�ڋq�R�[�h
            l_params.setAccountCode(l_strAccountCode);
            //���҃R�[�h:   �u�����N
            l_params.setTraderCode("     ");
            //���ʃR�[�h:   �����P��.���ʃR�[�h
            AioOrderUnitRow l_aioOrderUnitRow = 
                (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
            l_params.setOrderRequestNumber(l_aioOrderUnitRow.getOrderRequestNumber());
            
            //�o���z:       0
            l_params.setDebitAmount(0);
            
            //�����z:       �����P��.����
            l_params.setCreditAmount((long)l_aioOrderUnitRow.getQuantity());
            
            if (!WEB3OrderRootDivDef.CASH_IN_COOPERATION.equals(l_aioOrderUnitRow.getOrderRootDiv()))
            {
                //���o�����@@:   ��Еʌ��ϋ@@�֏����e�[�u��.���o�����@@
                // ���Z�@@�֘A�g���o���󋵃e�[�u��
                BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                    BankCashTransferStatusDao.findRowByInstitutionCodeBranchCodeOrderRequestNumber(
                            l_strInstitutionCode, l_strBranchCode, l_aioOrderUnitRow.getOrderRequestNumber());
                // test log
                log.debug("���Z�@@�֘A�g���o���󋵃e�[�u�� BankCashTransferStatusRow = " + l_bankCashTransferStatusRow);
                // ��Еʌ��ϋ@@�֏����e�[�u��
                CompBankConditionRow l_compBankConditionRow = 
                    CompBankConditionDao.findRowByInstitutionCodeBranchCodePaySchemeId(
                            l_strInstitutionCode, l_strBranchCode, l_bankCashTransferStatusRow.getPaySchemeId());
                // test log
                log.debug("��Еʌ��ϋ@@�֏����e�[�u�� CompBankConditionRow = " + l_compBankConditionRow);
                l_params.setCashTransferType(l_compBankConditionRow.getPaymentMethod());
                //����:         ��Еʌ��ϋ@@�֏����e�[�u��.���� 
                l_params.setSonarCode(l_compBankConditionRow.getSonarCode());
            }
            
            //������:       0
            l_params.setRemittanceFee(0);
            
            //��n���@@:     1'�F�X��
            l_params.setDeliveryType(WEB3AioDeliveryTypeDef.OTC);
            //�攭���Z��:   �u�����N
            l_params.setAdvanceSettlementDate("    ");
            //���֋敪:     �u�����N
            l_params.setPayTemporarilyDiv(" ");
            //�����o��:     "�����P��.�U�֗\��� < �����P��.��n���̏ꍇ�A�U�֗\���(MMDD)
            //              ���U�֗\�������c�Ɠ��̏ꍇ�́A���̗��c�Ɠ��Ɣ�r
            //              ����ȊO�̏ꍇ�A�u�����N"
            Timestamp l_dteEstTransferDate = l_aioOrderUnitRow.getEstTransferDate();
            String l_strDateType = 
                WEB3GentradeTradingTimeManagement.getBizDateType(l_dteEstTransferDate);
            // test log
            log.debug("�U�֗\���l_dteEstTransferDate�̉c�Ɠ��敪�@@=�@@" + l_strDateType);
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strDateType))
            {
                l_dteEstTransferDate = new WEB3GentradeBizDate(l_dteEstTransferDate).roll(1);
            }
            log.debug("new �U�֗\���l_dteEstTransferDate�@@=�@@" + l_dteEstTransferDate);
            if (WEB3DateUtility.compareToDay(l_dteEstTransferDate, l_aioOrderUnitRow.getDeliveryDate()) < 0)
            {
                l_params.setOrderOriginalDate(
                        WEB3DateUtility.formatDate(l_dteEstTransferDate, "yyyyMMdd").substring(4));
            }
            else
            {
                l_params.setOrderOriginalDate("    ");
            }
            //����敪:     �����N  
            l_params.setCancelDiv(" ");
            //�؏����s�敪:  �u�����N                                                                     
            l_params.setRecieptDiv(" ");
            //�ۏ؋��敪:   �u�u�����N                                                                        
            l_params.setGuaranteeDiv(" ");
            //�󒍓���: ThreadLocalSystemAttributesRegistry.getAttributes(�hxblocks.gtl.attributes.systemtimestamp�h)�̖߂�l                                                                     
            l_params.setOrderedTimestamp(GtlUtils.getSystemTimestamp());
            //�����敪:     0�F������ 
            l_params.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);
            
            //������ 
            //���o�������P��.�����o�H���h�����A�g�h�̏ꍇ 

            if (WEB3OrderRootDivDef.CASH_IN_COOPERATION.equals(l_aioOrderUnitRow.getOrderRootDiv()))
            {                
                //�E���Z�@@�փe�[�u�����ȉ��̏����Ō����B 
                //[����] 
                //�،���ЃR�[�h = �،����.�،���ЃR�[�h 
                //���Z�@@�փR�[�h = ���o�������P��.�U�֋L�q 
                FinInstitutionRow l_finInstitutionRow = FinInstitutionDao.findRowByPk(
                    l_strInstitutionCode,
                    l_aioOrderUnitRow.getDescription());
    
                //���ڍׂ́ADB�X�V�d�l�u��s�����ʒm_���o���`�[�L���[�e�[�u��.xls�v�Q�� 
                
                //���o�����@@ = ���Z�@@�փe�[�u��.���o���`�[�L���[�p���o�����@@
                l_params.setCashTransferType(l_finInstitutionRow.getCashTransferType());
                
                //�����o�� = �����P��.�U�֗\��� < �����P��.��n���̏ꍇ�A�U�֗\���(MMDD)
                //          ���U�֗\�������c�Ɠ��̏ꍇ�́A���̗��c�Ɠ��Ɣ�r
                //          ����ȊO�̏ꍇ�A�u�����N
                
                Date l_datEstTransferDate = l_aioOrderUnitRow.getEstTransferDate();
                Date l_datDeliveryDate = l_aioOrderUnitRow.getDeliveryDate();
                
                String l_strEstTransferDateType = 
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        new Timestamp(l_datEstTransferDate.getTime()));
    
                Date l_datCompareDate = null;
                    
                if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strEstTransferDateType))
                {
                    l_datCompareDate = new WEB3GentradeBizDate(
                        new Timestamp(l_datEstTransferDate.getTime())).roll(1);               
                }
                else
                {
                    l_datCompareDate = l_datEstTransferDate;
                }
                
                if (WEB3DateUtility.compareToDay(l_datCompareDate, l_datDeliveryDate) < 0)
                {
                    String l_strTransferDateMD = 
                        WEB3DateUtility.formatDate(l_datCompareDate, "MMdd");
                    
                    l_params.setOrderOriginalDate(l_strTransferDateMD);
                }
                else
                {
                    l_params.setOrderOriginalDate("    ");
                }
                
                //���� = ���Z�@@�փe�[�u��.���o���`�[�L���[�p�����A�g����R�[�h
                l_params.setSonarCode(l_finInstitutionRow.getCashTransferSonarCode());                
                          
            }
            
            //�R�j���o���`�[�L���[Params�̓��e��DB�ɓo�^����B 
            //WEB3DataAccessUtility.insertRow() 
            //[����] 
            //���o���`�[�L���[Params 
            WEB3DataAccessUtility.insertRow(l_params);
        } 
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMainAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In ���o���`�[�L���[Params�̓��e��DB�ɓo�^����", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In ���o���`�[�L���[Params�̓��e��DB�ɓo�^����", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        log.exiting(l_strMethodName);
    }
    
    /**
     * (create�o�������L���[�f�[�^)<BR>
     * �o�������̃L���[�f�[�^�̐����iDB�o�^�j���s���B<BR>
     * <BR>
     * �P�j�o�����������L���[Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�o�����������L���[Params�Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     *   ���ڍׂ́ADB�X�V�d�l�u�o�����������L���[�e�[�u��.xls�v�Q��<BR>
     * <BR>
     * �R�j�o�����������L���[Params�̓��e��DB�ɓo�^����B<BR>
     * <BR>
     *   WEB3DataAccessUtility.insertRow()<BR>
     * <BR>
     *   [����]<BR>
     *   �o�����������L���[Params<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)�����P�ʃI�u�W�F�N�g<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40F6493C0093
     */
    protected void createCashoutOrderQueueData(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String l_strMethodName = "createCashoutOrderQueueData"
            + "(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);
        try
        {
            //�P�j�o�����������L���[Params�C���X�^���X�𐶐�����B 
            HostPaymentOrderParams l_params = new HostPaymentOrderParams();
            //�Q�j�o�����������L���[Params�Ƀv���p�e�B���Z�b�g����B 
            //���ڍׂ́ADB�X�V�d�l�u�o�����������L���[�e�[�u��.xls�v�Q�� (�o���\��_�o�����������L���[�e�[�u��.xls)
            //�f�[�^�R�[�h request_code        �o�������FGI801
            l_params.setRequestCode(WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER);
    
            AccountManager l_accMgr = (AccountManager) GtlUtils.getAccountManager();
            log.debug(">>>>>>>>>WEB3GentradeAccountManager");
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_orderUnit.getAccountId());
            log.debug(">>>>>>>>>MainAccount");
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode =" + l_strInstitutionCode);
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            log.debug("l_strBranchCode =" + l_strBranchCode);
            String l_strAccountCode = l_mainAccount.getAccountCode();
            log.debug("l_strAccountCode =" + l_strAccountCode);
            
            //�،���ЃR�[�hinstitution_code    �����P��.���X�h�c�ɊY�����镔�X.�،���ЃR�[�h                                                                       
            l_params.setInstitutionCode(l_strInstitutionCode);
            //���X�R�[�h   branch_code         �����P��.���X�h�c�ɊY�����镔�X.���X�R�[�h                                                                        
            l_params.setBranchCode(l_strBranchCode);
            //�ڋq�R�[�h   account_code        �����P��.�ڋq�h�c�ɊY������ڋq.�ڋq�R�[�h                                                                        
            l_params.setAccountCode(l_strAccountCode);
            //���҃R�[�h   trader_code         �u�����N
			l_params.setTraderCode("     ");                                                                        
            //���ʃR�[�h   order_request_number�����P��.���ʃR�[�h                                                                        
            AioOrderUnitRow l_aioOrderUnitRow = 
                (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
            l_params.setOrderRequestNumber(l_aioOrderUnitRow.getOrderRequestNumber());
            //�o���z      payment_amount      �����P��.����                                                                      
            l_params.setPaymentAmount((int)l_aioOrderUnitRow.getQuantity());
            //�U���\���   est_transfer_date   �����P��.�U�֗\���                                                                        
            l_params.setEstTransferDate(l_aioOrderUnitRow.getEstTransferDate());
            //�󒍓���     ordered_timestamp   ���ݎ����i�V�X�e���^�C���X�^���v�j                                                                        
            l_params.setOrderedTimestamp(GtlUtils.getSystemTimestamp());
            //�����敪     status              3:������    
            l_params.setStatus(WEB3AioHostStatusDef.NOT_DEAL);

            //Test log
            log.debug("�o�����������L���[Params�̓��e = " + l_params);
            //�R�j�o�����������L���[Params�̓��e��DB�ɓo�^����B 
            //
            //WEB3DataAccessUtility.insertRow() 
            //
            //[����] 
            //�o�����������L���[Params 
            WEB3DataAccessUtility.insertRow(l_params);
        } 
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMainAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In �o�����������L���[Params�̓��e��DB�ɓo�^����", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In �o�����������L���[Params�̓��e��DB�ɓo�^����", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        log.exiting(l_strMethodName);
    }
    
    /**
     * (is���A���^�C���g���K���s)<BR>
     * �Y������،���Ђ����A���^�C���Ńg���K���s������Ƃ����ݒ�<BR>
     * �ɂȂ��Ă��邩�ǂ����ƃ`�F�b�N����B<BR>
     * <BR>
     * �P�j�،���Ѓe�[�u���̃��R�[�h���擾����B<BR>
     * <BR>
     *   ���X�i ����.���XID �j.getInstitution().getDataSourceObject()<BR>
     * <BR>
     * �Q�j�،����Params.�o���\���g���K���s = "2"�i���A���^�C�����{�j<BR>
     * �̏ꍇ�́Atrue��Ԃ��B<BR>
     *    ����ȊO�̏ꍇ�́Afalse��Ԃ��B<BR>
     * @@param l_lngBranchId - (���XID)<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@return boolean
     * @@roseuid 4121BCA800A4
     */
    protected boolean isSubmitRealTimeTrigger(long l_lngBranchId)
        throws WEB3BaseException
    {
        final String l_strMethodName = "isSubmitRealTimeTrigger"
            + "(String l_strBranchId) ";
        log.entering(l_strMethodName);
        try
        {
            //�P�j�،���Ѓe�[�u���̃��R�[�h���擾����B
            AccountManager l_accountManager = GtlUtils.getAccountManager();            
            Branch l_branch = l_accountManager.getBranch(l_lngBranchId);
            InstitutionRow l_institutionRow = 
                (InstitutionRow)l_branch.getInstitution().getDataSourceObject();
            //�Q�j�،����Params.�o���\���g���K���s = "2"�i���A���^�C�����{�j�̏ꍇ�́Atrue��Ԃ��B
            if (WEB3PaymentApplyTriggerDef.REAL_ENFORCEMENT.equals(
                    l_institutionRow.getPaymentApplyTrigger()))
            {
                log.exiting(l_strMethodName);
                return true;
            }
            // ����ȊO�̏ꍇ�́Afalse��Ԃ��B
            log.exiting(l_strMethodName);
            return false;
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In �،���Ѓe�[�u���̃��R�[�h���擾���� ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (update�����敪)<BR>
     * �o�����������L���[�e�[�u���̏����敪��'0'�i�������j�ɁA<BR>
     * �����P�ʃe�[�u����MQ�X�e�[�^�X��'1'�i���M�ς݁j�ɍX�V����B<BR>
     * <BR>
     * �P�j�o�����������L���[�e�[�u���̍X�V<BR>
     * <BR>
     * �P�|�P�j�ȉ��̏����ŁA�o�����������L���[�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     *   [��������]<BR>
     *   �f�[�^�R�[�h�F "GI801"<BR>
     *   �،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     *   ���X�R�[�h�F ����.���X�R�[�h<BR>
     *   �ڋq�R�[�h�F ����.�ڋq�R�[�h<BR>
     *   ���ʃR�[�h�F ����.���ʃR�[�h<BR>
     *   �����敪�F '3'�i�������j<BR>
     * <BR>
     * �P�|�Q�j�����敪��'0'���Z�b�g����B<BR>
     * <BR>
     * �P�|�R�j�Y�����R�[�h���X�V����B<BR>
     * <BR>
     * �Q�j�����P�ʃe�[�u���̍X�V<BR>
     * <BR>
     * �Q�|�P�j�ȉ��̏����ŁA�����P�ʃe�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     *   [��������]<BR>
     *   �����P��ID�F ����.�����P��ID<BR>
     * <BR>
     * �Q�|�Q�jMQ�X�e�[�^�X��'1'���A�X�V���t�ɃV�X�e���^�C���X�^���v���Z�b�g����B<BR>
     * <BR>
     * �Q�|�R�j�Y�����R�[�h���X�V����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4129E25302ED
     */
    public void updateStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strOrderRequestNumber, 
            long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String l_strMethodName = "updateStatus(String l_strInstitutionCode,"
            + "String l_strBranchCode, String l_strAccountCode, "
            + "String l_strOrderRequestNumber, long l_lngOrderUnitId)";
        log.entering(l_strMethodName);
        try
        {
            // �P�j�o�����������L���[�e�[�u���̍X�V
            // �P�|�P�j�ȉ��̏����ŁA�o�����������L���[�e�[�u�����烌�R�[�h���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisHostPaymentOrderRows = l_queryProcessor.doFindAllQuery(
                HostPaymentOrderRow.TYPE,
                "request_code=? and institution_code=? and branch_code=? "
                + "and account_code=? and order_request_number=? and status =? ",
                null,
                new Object[] { WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER,
                        l_strInstitutionCode, l_strBranchCode, 
                        l_strAccountCode, l_strOrderRequestNumber, 
                        WEB3AioHostStatusDef.NOT_DEAL});
            if (l_lisHostPaymentOrderRows.size() == 1)
            {
                HostPaymentOrderRow l_hostPaymentOrderRow = 
                    (HostPaymentOrderRow) l_lisHostPaymentOrderRows.get(0);
                HostPaymentOrderParams l_hostPaymentOrderParams = 
                    new HostPaymentOrderParams(l_hostPaymentOrderRow);
                // �P�|�Q�j�����敪��'0'���Z�b�g����B
                l_hostPaymentOrderParams.setStatus("0");
                // �P�|�R�j�Y�����R�[�h���X�V����B
                l_queryProcessor.doUpdateQuery(l_hostPaymentOrderParams);
            }
            // �Q�j�����P�ʃe�[�u���̍X�V
            // �Q�|�P�j�ȉ��̏����ŁA�����P�ʃe�[�u�����烌�R�[�h���擾����B
            //[��������] 
            //�����P��ID�F ����.�����P��ID 
            AioOrderUnitRow l_orderUnitRow = 
                AioOrderUnitDao.findRowByPk(l_lngOrderUnitId);
            log.debug("�����P�ʃe�[�u��Row := " + l_orderUnitRow);
            
            AioOrderUnitParams l_orderUnitParams = new AioOrderUnitParams(l_orderUnitRow);
            //�Q�|�Q�jMQ�X�e�[�^�X��'1'���A�X�V���t�ɃV�X�e���^�C���X�^���v���Z�b�g����B
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //�Q�|�R�j�Y�����R�[�h���X�V����B
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);
            log.debug("updated �����P�ʃe�[�u��Row := " + l_orderUnitParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException Error ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException Error In", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(l_strMethodName);
    }
    
    /**
     * (�g���K���s)<BR>
     * �g���K�𔭍s����B<BR>
     * <BR>
     * �P�jWEB3MQMessageSpec�𐶐�����B <BR>
     * <BR>
     *    WEB3MQMessageSpec(�،���ЃR�[�h, �f�[�^�R�[�h)<BR>
     * <BR>
     *    [����] <BR>
     *    �،���ЃR�[�h�F�@@����.�،���ЃR�[�h<BR>
     *    �f�[�^�R�[�h�F�@@����.�f�[�^�R�[�h<BR>
     * <BR>
     * �Q�jMQ�g���K�𔭍s����B<BR>
     * <BR>
     *    WEB3MQGatewayServiceImpl.send(MQ���b�Z�[�W���e)<BR>
     * <BR>
     *    [����]<BR>
     *    MQ���b�Z�[�W���e�F ��������WEB3MQMessageSpec<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strRequestCode - (�f�[�^�R�[�h)<BR>
     * @@roseuid 412B11A0015A
     */
    public void submitTrigger(String l_strInstitutionCode, 
            String l_strRequestCode) 
    {
        final String l_strMethodName = "submitTrigger(String l_strInstitutionCode, "
            + "String l_strRequestCode)";
        log.entering(l_strMethodName);
        // �P�jWEB3MQMessageSpec�𐶐�����B
        WEB3MQMessageSpec l_mqMessageSpec = 
            new WEB3MQMessageSpec(l_strInstitutionCode, l_strRequestCode);
        // �Q�jMQ�g���K�𔭍s����B
        WEB3MQGatewayService l_mqGatewayService =
            (WEB3MQGatewayService) Services.getService(WEB3MQGatewayService.class);
        log.debug("�g���K�𔭍s����........");
        l_mqGatewayService.send(l_mqMessageSpec);
        log.debug("�g���K�𔭍s����........OK!");
        log.exiting(l_strMethodName);
    }

    /**
     * (�g���K���s)<BR>
     * �g���K�𔭍s����B<BR>
     * <BR>
     * �P�jWEB3MQMessageSpec�𐶐�����B<BR>
     * <BR>
     *    WEB3MQMessageSpec(�،���ЃR�[�h, �f�[�^�R�[�h, ���[�U�f�[�^)<BR>
     * <BR>
     *    [����]<BR>
     *    �،���ЃR�[�h�F�@@����.�،���ЃR�[�h<BR>
     *    �f�[�^�R�[�h�F�@@����.�f�[�^�R�[�h<BR>
     *    ���[�U�f�[�^�F�@@����.���[�U�f�[�^<BR>
     * <BR>
     * �Q�jMQ�g���K�𔭍s����B<BR>
     * <BR>
     *    WEB3MQGatewayServiceImpl.send(MQ���b�Z�[�W���e)<BR>
     * <BR>
     *    [����]<BR>
     *    MQ���b�Z�[�W���e�F ��������WEB3MQMessageSpec<BR>
     * <BR>
     */
    public void submitTrigger(
        String l_strInstitutionCode, String l_strRequestCode, String l_strUser)
    {
        final String STR_METHOD_NAME =
            "submitTrigger(String l_strInstitutionCode, String l_strRequestCode, String l_strUser)";
        log.entering(STR_METHOD_NAME);

        // �P�jWEB3MQMessageSpec�𐶐�����B
        WEB3MQMessageSpec l_mqMessageSpec =
            new WEB3MQMessageSpec(l_strInstitutionCode, l_strRequestCode, l_strUser);

        // �Q�jMQ�g���K�𔭍s����B
        WEB3MQGatewayService l_mqGatewayService =
            (WEB3MQGatewayService) Services.getService(WEB3MQGatewayService.class);
        l_mqGatewayService.send(l_mqMessageSpec);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�V�K�����������M)<BR>
     * �V�K�̓��������̑��M���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���N�G�X�g���M�j�V�K�����������M�v �Q��<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413D5A78018E
     */
    protected void openCashinOrderSend(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String l_strMethodName = "openCashinOrderSend(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);

        AccountManager l_accountManager = GtlUtils.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accountManager.getSubAccount(
                    l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMSubAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.1 �����P��.�ʉ݃R�[�h != null�i�O�݁j �̏ꍇ�A
        //�ȉ��̏��������s�B
        AioOrderUnitRow l_aioOrderUnitRow = 
            (AioOrderUnitRow)l_orderUnit.getDataSourceObject();        
        if (l_aioOrderUnitRow.getCurrencyCode() != null)
        {
            //1.1.1  create�O�ݓ��������L���[�f�[�^
            //�O�ݓ��������̃L���[�f�[�^��DB�ɓo�^����B 
            //[����] 
            //�����P�ʁF ����.�����P�� 
            log.debug(" �����P��.�ʉ݃R�[�h != null�i�O�݁j �̏ꍇ" + l_aioOrderUnitRow.getCurrencyCode());
            this.createHostForeignCashinOrderData(l_orderUnit);
        } 
        else
        {
            //1.2 �����P��.�ʉ݃R�[�h == null�i�~�݁j �̏ꍇ�A
            //�ȉ��̏��������s�B
            //1.2.1 create���������L���[�f�[�^
            //���������̃L���[�f�[�^��DB�ɓo�^����B 
            //[����] 
            //�����P�ʁF ����.�����P�� 
            log.debug(" �����P��.�ʉ݃R�[�h == null�i�O�݁j �̏ꍇ" + l_aioOrderUnitRow.getCurrencyCode());
            this.createCashinOrderQueueData(l_orderUnit);
        }

        //1.3 is�g���K���s(String)
        boolean l_blnIsSubmitTrigger = 
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                    WEB3OrderingConditionDef.DEFAULT);
        
        // Test log
        log.debug("is�g���K���s(String) = " + l_blnIsSubmitTrigger);
        
        //1.4 is�g���K���s(String) = true �̏ꍇ
        if (l_blnIsSubmitTrigger)
        {
            String l_strDataCode = null;
            // �����P��.�ʉ݃R�[�h == null(�~��)�̏ꍇ
            if (l_aioOrderUnitRow.getCurrencyCode() == null)
            {
                //  �g���K���s(String, String)
                // [����]
                // �،���ЃR�[�h�F�@@����.�����P��.�،����ID�ɊY������،���ЃR�[�h
                // �f�[�^�R�[�h�F�hGI803T�h�i�����j
                l_strDataCode = WEB3HostRequestCodeDef.AIO_SLIP_SERVE + "T";
                this.submitTrigger(
                    l_subAccount.getInstitution().getInstitutionCode(), l_strDataCode);
            }
            // �����P��.�ʉ݃R�[�h != null(�~��)�̏ꍇ
            else
            {
                // �g���K���s(String, String, String)
                // [����]
                // �،���ЃR�[�h�F����:�����P��.�،����ID�ɊY������،���ЃR�[�h
                // �f�[�^�R�[�h�F�hGI804T�h�i�O�ݓ����j
                // ���[�U�f�[�^�F"2"�iWEB3OrderDivDef.CASHIN(����)�j
                l_strDataCode = WEB3HostRequestCodeDef.FOREIGN_CASH_TRANSFER_ORDER + "T";
                this.submitTrigger(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_strDataCode,
                    WEB3OrderDivDef.CASHIN);
            }
        }
        log.exiting(l_strMethodName);
    }
    
    /**
     * (�V�K�o���������M)<BR>
     * �V�K�̏o�������̑��M���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���N�G�X�g���M�j�V�K�o���������M�v �Q��<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413D5AD70130
     */
    protected void openCashoutOrderSend(AioOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String l_strMethodName = "openCashoutOrderSend(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accountManager.getSubAccount(
                l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMSubAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);
        }

        AioOrderUnitRow l_orderUnitRow = (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strCurrencyCode = l_orderUnitRow.getCurrencyCode();

        // 1.1  �~�݂̏ꍇ
        if (l_strCurrencyCode == null)
        {
            // 1.1.1 create�o�������L���[�f�[�^
            // �o�������̃L���[�f�[�^��DB�ɓo�^����B
            // [����]
            // �����P�ʁF ����.�����P��
            this.createCashoutOrderQueueData(l_orderUnit);
        }
        // 1.2 �O�݂̏ꍇ
        else
        {
           // 1.2.1  create�O�ݏo�������L���[�f�[�^
           // �O�ݏo�������̃L���[�f�[�^��DB�ɓo�^����B
           // [����]
           // �����P�ʁF ����.�����P��
            this.createHostForeignCashoutOrderData(l_orderUnit);
        }
        //1.3.is���A���^�C���g���K���s(String)
        // �Y���̏،���Ђ����A���^�C���Ńg���K���s�����{���邩���`�F�b�N����B
        //[����]
        // ���XID�F ����.�����P��.���XID
        boolean l_blnIsRealSubmitTrigger = 
            this.isSubmitRealTimeTrigger(
                l_subAccount.getMainAccount().getBranch().getBranchId());
        //1.4.is���A���^�C���g���K���s == false �̏ꍇ
        if (!l_blnIsRealSubmitTrigger)
        {
            return;
        }
        //1.5. is�g���K���s(String)
        boolean l_blnIsSubmitTrigger = 
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                    WEB3OrderingConditionDef.DEFAULT);
        // test log
        log.debug("is�g���K���s(String) = " + l_blnIsSubmitTrigger);

        // 1.6 is�g���K���s(String) = false �̏ꍇ
        if (!l_blnIsSubmitTrigger)
        {
            // 1.6.1
            return;
        }

        // 1.7 �~�݂̏ꍇ
        if (l_strCurrencyCode == null)
        {
            // �o�����������L���[�e�[�u���̊Y�����R�[�h�̏����敪���������ɍX�V����B
            // [����]
            // �،���ЃR�[�h�F ���X.getInstitution().getInstitutionCode()�̖߂�l
            // ���X�R�[�h�F ���X�i����.�����P��.���XID�j.getBranchCode()�̖߂�l
            // �ڋq�R�[�h�F �����i����.�����P��.����ID�j.getAccountCode()�̖߂�l
            // ���ʃR�[�h�F ����.�����P��.���ʃR�[�h
            // �����P��ID�F ����.�����P��.�����P��ID

          this.updateStatus(l_subAccount.getInstitution().getInstitutionCode(), 
                  l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                  l_subAccount.getMainAccount().getAccountCode(), 
                  l_orderUnitRow.getOrderRequestNumber(), 
                  l_orderUnitRow.getOrderUnitId());
          
          // �g���K�𔭍s����B
          // [����]
          // �،���ЃR�[�h�F ����.�����P��.�،����ID�ɊY������،���ЃR�[�h
          // �f�[�^�R�[�h�F �hGI801T�h
          String l_strDateCode = WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER + "T";
          this.submitTrigger(l_subAccount.getInstitution().getInstitutionCode(), l_strDateCode);
        }

        // 1.8 �O�݂̏ꍇ
        else
        {
            //�����P�ʃe�[�u����MQ�X�e�[�^�X��'1'�i���M�ς݁j�ɍX�V����B
            //[����]
            //�����P��ID�F ����.�����P��.�����P��ID
            this.updateMQStatus(l_orderUnitRow.getOrderUnitId());

            // �g���K�𔭍s����B
            // [����]
            // �،���ЃR�[�h�F ����.�����P��.�،����ID�ɊY������،���ЃR�[�h
            // �f�[�^�R�[�h�F �hGI804T�h
            // ���[�U�f�[�^�F 1�iWEB3OrderDivDef.CASHOUT�i�o���j�j
            String l_strDateCode = WEB3HostRequestCodeDef.FOREIGN_CASH_TRANSFER_ORDER + "T";

            this.submitTrigger(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_strDateCode,
                WEB3OrderDivDef.CASHOUT);
        }
        log.exiting(l_strMethodName);
    }
    
    /**
     * (�V�K�U�֒������M)<BR>
     * �V�K�̐U�֒����̑��M���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���N�G�X�g���M�j�V�K�U�֒������M�v �Q��<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413D70BB020B
     */
    protected void openTransferOrderSend(AioOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String l_strMethodName = "openTransferOrderSend(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);
        //1.1 �⏕�����I�u�W�F�N�g���擾����B
        //[����] 
        // ����ID�F ����.�����P��.����ID 
        // �⏕����ID�F ����.�����P��.�⏕����ID
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accountManager.getSubAccount(
                l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMSubAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        //1.2. �⏕����.getSubAccountType() != 1�i�a��������j OR
        //AioOrderUnit.getDescription() == "feq_transfer" �̏ꍇ        
        if (!SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(
                l_subAccount.getSubAccountType()) || 
            WEB3AioDescriptionDef.FEQ_TRANSFER.equals(
                l_orderUnit.getDescription()))
        {
            //1.2.1.return 
            log.exiting(l_strMethodName);
            return;
        }
        //1.3.create�U�֒����L���[�f�[�^(AioOrderUnit)
        this.createTransferOrderQueueData(l_orderUnit);
        //1.4. is�g���K���s(String)
        boolean l_blnIsSubmitTrigger = 
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);
        // test log
        log.debug("�V�K�U�֒������M#is�g���K���s(String) = " + l_blnIsSubmitTrigger);
        log.debug("�V�K�U�֒������M# system time = " + GtlUtils.getSystemTimestamp());
        //1.5. is�g���K���s(String) = true �̏ꍇ
        if (l_blnIsSubmitTrigger)
        {
            //1.5.1  �g���K���s(String, String)
            //[����] 
            // �،���ЃR�[�h�F�@@����.�����P��.�،����ID�ɊY������،���ЃR�[�h 
            // �f�[�^�R�[�h�F �hGI806T�h�i�ۏ؋��U�֐����j 
            this.submitTrigger(l_subAccount.getInstitution().getInstitutionCode(),
                WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ORDER + "T");
        }
        log.exiting(l_strMethodName);
    }
    
    /**
     * (create�U�֒����L���[�f�[�^)<BR>
     * �U�֒����̃L���[�f�[�^�̐����iDB�o�^�j���s���B<BR>
     * <BR>
     * �P�j�U�֐��������L���[Params�C���X�^���X�𐶐�����B<BR> 
     * <BR>
     * �Q�j�U�֐��������L���[Params�Ƀv���p�e�B���Z�b�g����B <BR>
     *<BR> 
     * �Q�|�P�j�U�֏o���z�i�ؕ��j�A�U�֓����z�i�ݕ��j�A�E�v�R�[�h�A�����敪�̒l�̐ݒ���@@ <BR>
     *<BR> 
     * �Q�|�P�|�P�j����.�����P��.������� = 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j�̏ꍇ <BR>
     *<BR> 
     *  �U�֏o���z�i�ؕ��j = 0 <BR>
     *  �U�֓����z�i�ݕ��j = �����P��.���� <BR>
     *  �E�v�R�[�h = 01�i�M�p�ۏ؋��j <BR>
     *<BR> 
     * �Q�|�P�|�Q�j����.�����P��.������� = 1007�i�U�֒����i�a������犔��؋����j�j�̏ꍇ <BR>
     *<BR> 
     *  �U�֏o���z�i�ؕ��j = �����P��.���� <BR>
     *  �U�֓����z�i�ݕ��j = 0 <BR>
     * �@@�@@�@@�E�����P��.�E�v�R�[�h�@@!=�@@null�@@�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�E�v�R�[�h = �����P��.�E�v�R�[�h<BR>
     * �@@�@@�@@�E�����P��.�E�v�R�[�h�@@==�@@null�̏ꍇ�A<BR>
     *�@@      �@@�@@�E�v�R�[�h = 72�i����؋����j <BR>
     *<BR> 
     * �Q�|�P�|�R�j����.�����P��.������� = 1008�i�U�֒����i����؋�������a����j�j�̏ꍇ <BR>
     * <BR>
     *  �U�֏o���z�i�ؕ��j = 0 <BR>
     *  �U�֓����z�i�ݕ��j = �����P��.���� <BR>
     * �@@�@@�@@�E�����P��.�E�v�R�[�h�@@!=�@@null�@@�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�E�v�R�[�h = �����P��.�E�v�R�[�h<BR>
     * �@@�@@�@@�E�����P��.�E�v�R�[�h�@@==�@@null�@@�̏ꍇ�A<BR>
     *      �@@�@@�@@�E�v�R�[�h = 72�i����؋����j<BR>
     *<BR> 
     * �Q�|�P�|�S�j����.�����P��.������� = 1011�i�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�j<BR>
     * �@@or 1021�iCFD�U�֒����i�a�������CFD�����j�j�̏ꍇ<BR>
     *<BR> 
     *  �U�֏o���z�i�ؕ��j = �����P��.���� <BR>
     *  �U�֓����z�i�ݕ��j = 0 <BR>
     * <BR>
     * �Q�|�P�|�T�j����.�����P��.������� = 1012�i�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�j<BR>
     * �@@or 1022�iCFD�U�֒����iCFD��������a����j�j�̏ꍇ<BR>
     * <BR>
     *  �U�֏o���z�i�ؕ��j = 0 <BR>
     *  �U�֓����z�i�ݕ��j = �����P��.���� <BR>
     * <BR>
     * �Q�|�P�|�U�j����.�����P��.������� = <BR> 
     *      1013�i�O�������U�֒����i�a�������O�����������j�j�̏ꍇ<BR> 
     * <BR>
     * �U�֏o���z�i�ؕ��j = �����P��.���� <BR>
     * �U�֓����z�i�ݕ��j = 0 <BR>
     * �E�v�R�[�h �@@ = 99�i���̑��j <BR>
     * <BR>
     * �Q�|�P�|�V�j����.�����P��.������� = 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j�̏ꍇ<BR>
     * <BR>
     * �U�֏o���z�i�ؕ��j = �����P��.����<BR>
     * �U�֓����z�i�ݕ��j = 0<BR>
     * �E�v�R�[�h         �@@ = 01�i�M�p�ۏ؋��j<BR>
     * <BR>
     * �Q�|�Q�j���̑��̍��ڂɂ��ẮADB�X�V�d�l�u�U�֐��������L���[�e�[�u��.xls�v�Q�� <BR>
     * <BR>
     * �R�j�U�֐��������L���[Params�̓��e��DB�ɓo�^����B <BR>
     * <BR>
     *  WEB3DataAccessUtility.insertRow() <BR>
     * <BR>
     *  [����] <BR>
     *   �U�֐��������L���[Params <BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413D749D019D
     */
    protected void createTransferOrderQueueData(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String l_strMethodName = "" +
            "createTransferOrderQueueData(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);

        //�P�j�U�֐��������L���[Params�C���X�^���X�𐶐�����B
        HostTransferOrderParams l_params = new HostTransferOrderParams();
        //�Q�j�U�֐��������L���[Params�Ƀv���p�e�B���Z�b�g����B 
        //�E�v��:�u�����N
        l_params.setRemarkName("          ");
        //�����U��:9�i�����j
        l_params.setInvoluntaryTransfer(WEB3ForceTransferDef.FORCE_TRANSFER);
        //���U�֌���:�u�����N
        l_params.setOriginalTransferDate(WEB3AioOriginalTransferDateBlankDef.BLANK);

        AioOrderUnitRow l_aioOrderUnitRow =
            (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strRemarkCode = l_aioOrderUnitRow.getRemarkCode();

        //�Q�|�P�j�U�֏o���z�i�ؕ��j�A�U�֓����z�i�ݕ��j�A�E�v�R�[�h�A�����U�֍��ڂ̒l�̐ݒ���@@
        //�Q�|�P�|�P�j����.�����P��.������� = 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j�̏ꍇ
        if (OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderUnit.getOrderType()))
        {
            //�U�֏o���z�i�ؕ��j = 0 
            l_params.setTransferAmountDebitor(0);
            //�U�֓����z�i�ݕ��j = �����P��.����
            l_params.setTransferAmountCreditor((int)l_orderUnit.getQuantity());
            //�E�v�R�[�h = 01�i�M�p�ۏ؋��j
            l_params.setRemarkCode(WEB3RemarkCodeDef.MARGIN_GUARANTEE);
        }
        //�Q�|�P�|�Q�j����.�����P��.������� = 1007�i�U�֒����i�a������犔��؋����j�j�̏ꍇ 
        if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderUnit.getOrderType()))
        {
            //�U�֏o���z�i�ؕ��j = �����P��.���� 
            l_params.setTransferAmountDebitor((int)l_orderUnit.getQuantity());
            //�U�֓����z�i�ݕ��j = 0
            l_params.setTransferAmountCreditor(0);
            
            //�E�����P��.�E�v�R�[�h�@@!=�@@null�@@�̏ꍇ�A
            if (l_strRemarkCode != null)
            {
                //�E�v�R�[�h = �����P��.�E�v�R�[�h
                l_params.setRemarkCode(l_strRemarkCode);
            }
            //�E�����P��.�E�v�R�[�h�@@==�@@null�̏ꍇ�A
            else
            {
                //    �E�v�R�[�h = 72�i����؋����j 
                l_params.setRemarkCode(WEB3RemarkCodeDef.STOCK_FUTURES_MARGIN);
            }
        }
        //�Q�|�P�|�R�j����.�����P��.������� = 1008�i�U�֒����i����؋�������a����j�j�̏ꍇ 
        if (OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(l_orderUnit.getOrderType()))
        {
            //�U�֏o���z�i�ؕ��j = 0 
            l_params.setTransferAmountDebitor(0);
            //�U�֓����z�i�ݕ��j = �����P��.����
            l_params.setTransferAmountCreditor((int)l_orderUnit.getQuantity());
            
            //�E�����P��.�E�v�R�[�h�@@!=�@@null�@@�̏ꍇ�A
            if (l_strRemarkCode != null)
            {
                //�E�v�R�[�h = �����P��.�E�v�R�[�h
                l_params.setRemarkCode(l_strRemarkCode);
            }
            //�E�����P��.�E�v�R�[�h�@@==�@@null�@@�̏ꍇ�A
            else
            {
                //    �E�v�R�[�h = 72�i����؋����j 
                l_params.setRemarkCode(WEB3RemarkCodeDef.STOCK_FUTURES_MARGIN);
            }
        }
        
        //�Q�|�P�|�S�j����.�����P��.������� = 1011�i�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�j
        //or 1021�iCFD�U�֒����i�a�������CFD�����j�j�̏ꍇ
        if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
            l_orderUnit.getOrderType())
            || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(
                l_orderUnit.getOrderType()))
        {
            //�U�֏o���z�i�ؕ��j = �����P��.����
            l_params.setTransferAmountDebitor((int)l_orderUnit.getQuantity());
            //�U�֓����z�i�ݕ��j = 0
            l_params.setTransferAmountCreditor(0);
            //�E�v�R�[�h = �����P��.�E�v�R�[�h
            l_params.setRemarkCode(l_strRemarkCode);
            //�E�v�� = �����P��.�E�v��
            if (!WEB3StringTypeUtility.isEmpty(l_aioOrderUnitRow.getRemarkName()))
            {
                l_params.setRemarkName(l_aioOrderUnitRow.getRemarkName());
            }
            //�����U��
            //�����P��.�E�v�R�[�h = 99�F���̑� and �����P��.�U�փ^�C�v = �P�F�����̏ꍇ�A�u�����N
            //��L�ȊO�̏ꍇ�A9�F����
            if (WEB3RemarkCodeDef.BLANK.equals(
                l_aioOrderUnitRow.getRemarkCode())
                && AssetTransferTypeEnum.CASH_IN.equals(
                    l_aioOrderUnitRow.getTransferType()))
            {
        	    //�����U�� = �u�����N
        	    l_params.setInvoluntaryTransfer(" ");
            }
            //���U�֌���
            //�������.������ = �����P��.��n��(MMDD)�̏ꍇ�A�u�����N���Z�b�g
            //��L�ȊO�̏ꍇ�A�����P��.��n���iMMDD�j���Z�b�g
            if (WEB3DateUtility.compareToDay(
                WEB3GentradeTradingTimeManagement.getOrderBizDate(),
                l_aioOrderUnitRow.getDeliveryDate()) != 0)
            {
                String l_strDeliveryMD = WEB3DateUtility.formatDate(
                    l_aioOrderUnitRow.getDeliveryDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_MD);
                l_params.setOriginalTransferDate(l_strDeliveryMD);
            }
        }
        
        //�Q�|�P�|�T�j����.�����P��.������� = 1012�i�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�j
        //or 1022�iCFD�U�֒����iCFD��������a����j�j�̏ꍇ
        if (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(
            l_orderUnit.getOrderType())
            || OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD.equals(
                l_orderUnit.getOrderType()))
        {
            //�U�֏o���z�i�ؕ��j = 0 
            l_params.setTransferAmountDebitor(0);
            //�U�֓����z�i�ݕ��j = �����P��.����
            l_params.setTransferAmountCreditor((int)l_orderUnit.getQuantity());
            //�E�v�R�[�h = �����P��.�E�v�R�[�h
            l_params.setRemarkCode(l_strRemarkCode);
            //�E�v�� = �����P��.�E�v��
            if (!WEB3StringTypeUtility.isEmpty(l_aioOrderUnitRow.getRemarkName()))
            {
                l_params.setRemarkName(l_aioOrderUnitRow.getRemarkName());
            }
            //�����U��
            //�����P��.�E�v�R�[�h = 99�F���̑� and �����P��.�U�փ^�C�v = �P�F�����̏ꍇ�A�u�����N
            //��L�ȊO�̏ꍇ�A9�F����
            if (WEB3RemarkCodeDef.BLANK.equals(
                l_aioOrderUnitRow.getRemarkCode())
                && AssetTransferTypeEnum.CASH_IN.equals(
                    l_aioOrderUnitRow.getTransferType()))
            {
        	    //�����U�� = �u�����N
        	    l_params.setInvoluntaryTransfer(" ");
            }
            //���U�֌���
            //�������.������ = �����P��.��n��(MMDD)�̏ꍇ�A�u�����N���Z�b�g
            //��L�ȊO�̏ꍇ�A�����P��.��n���iMMDD�j���Z�b�g
            if (WEB3DateUtility.compareToDay(
                WEB3GentradeTradingTimeManagement.getOrderBizDate(),
                l_aioOrderUnitRow.getDeliveryDate()) != 0)
            {
                String l_strDeliveryMD = WEB3DateUtility.formatDate(
                    l_aioOrderUnitRow.getDeliveryDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_MD);
                l_params.setOriginalTransferDate(l_strDeliveryMD);
            }
        }
        
        //�Q�|�P�|�U�j����.�����P��.������� = 1013�i�O�������U�֒����i�a�������O�����������j�j�̏ꍇ
        if (OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderUnit.getOrderType()))
        {
            //�U�֏o���z�i�ؕ��j = �����P��.����
            l_params.setTransferAmountDebitor((int)l_orderUnit.getQuantity());
            //�U�֓����z�i�ݕ��j = 0
            l_params.setTransferAmountCreditor(0);
            //�E�v�R�[�h �@@ = 99�i���̑��j
            l_params.setRemarkCode(WEB3RemarkCodeDef.BLANK);
        }

        //�Q�|�P�|�V�j����.�����P��.������� = 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j�̏ꍇ
        if(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_orderUnit.getOrderType()))
        {
            //�U�֏o���z�i�ؕ��j = �����P��.����
            l_params.setTransferAmountDebitor((int)l_orderUnit.getQuantity());
            //�U�֓����z�i�ݕ��j = 0
            l_params.setTransferAmountCreditor(0);
            //�E�v�R�[�h         �@@ = 01�i�M�p�ۏ؋��j
            l_params.setRemarkCode(WEB3RemarkCodeDef.MARGIN_GUARANTEE);
        }

        //�Q�|�Q�j���̑��̍��ڂɂ��ẮADB�X�V�d�l�u�U�֐��������L���[�e�[�u��.xls�v�Q��
        l_params.setRequestCode(WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ORDER);
        
        try 
        {
            AccountManager l_accMgr = (AccountManager) GtlUtils.getAccountManager();
            log.debug(">>>>>>>>>WEB3GentradeAccountManager");
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_orderUnit.getAccountId());
            log.debug(">>>>>>>>>MainAccount");
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode =" + l_strInstitutionCode);
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            log.debug("l_strBranchCode =" + l_strBranchCode);
            String l_strAccountCode = l_mainAccount.getAccountCode();
            log.debug("l_strAccountCode =" + l_strAccountCode);
            
            l_params.setInstitutionCode(l_strInstitutionCode);
            l_params.setBranchCode(l_strBranchCode);
            l_params.setAccountCode(l_strAccountCode);
			l_params.setTraderCode("     ");

            l_params.setCancelDiv(" ");

            l_params.setOrderRequestNumber(l_aioOrderUnitRow.getOrderRequestNumber());
            
            //�U�֎w����
            //�����P��.������
            //�����������́A00:00  
            l_params.setTransferDate(
                WEB3DateUtility.toDay(
                    WEB3DateUtility.getDate(l_aioOrderUnitRow.getBizDate(), "yyyyMMdd")));
            
            //�U�֎w������
            //���ݎ������g���K���s�����{���鎞�ԑт̏ꍇ�A00:00���Z�b�g
            //���ݎ������g���K���s�����{���Ȃ����ԑт̏ꍇ�A06:00���Z�b�g
            //�����t�����́A�����P��.������
            
            String l_strTransferTime;        
            if (WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT) == true)
            {
                log.debug("�g���K���s�����{���鎞�ԑт̏ꍇ�Ais�g���K���s = true");
                l_strTransferTime = l_aioOrderUnitRow.getBizDate() + "000000";
            }
            else
            {
                log.debug("�g���K���s�����{���Ȃ����ԑт̏ꍇ�Ais�g���K���s = false");
                l_strTransferTime = l_aioOrderUnitRow.getBizDate() + "060000";    
            }
            
            l_params.setTransferTime(
                WEB3DateUtility.getDate(l_strTransferTime, "yyyyMMddHHmmss"));
            
            //�����P��.MQ�X�e�[�^�X��0�F�����M�̏ꍇ�A3�F������
            //�ȊO�A0:������
            if (WEB3MqStatusDef.NOT_SEND_MAIL.equals(l_aioOrderUnitRow.getMqStatus()))
            {
                l_params.setStatus(WEB3AioHostStatusDef.NOT_DEAL);
            }
            else
            {
                l_params.setStatus(WEB3AioHostStatusDef.DEALING);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMainAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
       
        //�R�j�U�֐��������L���[Params�̓��e��DB�ɓo�^����B 
        //WEB3DataAccessUtility.insertRow() 
        //[����] 
        //�U�֐��������L���[Params 
        try 
        {
            WEB3DataAccessUtility.insertRow(l_params);
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In �U�֐��������L���[Params�̓��e��DB�ɓo�^���� ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In �U�֐��������L���[Params�̓��e��DB�ɓo�^���� ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        log.exiting(l_strMethodName);
    }
    
    /**
     * @@param arg0
     * @@return MarketRequestSendResult
     * @@roseuid 415A47150211
     */
    public MarketRequestSendResult send(MarketRequestMessage arg0) 
    {
        final String l_strMethodName = "send(MarketRequestMessage arg0)";
        log.entering(l_strMethodName);
        log.exiting(l_strMethodName);
        return null;
    }
    
    //=============remain zhou-yong 2004/12/6 NO.1 begin ===============
    /**
     * (�V�K�،��U�֒������M)<BR>
     * �V�K�̏،��U�֒����̑��M���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���N�G�X�g���M�j�V�K�،��U�֒������M�v �Q�� <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413D70BB020B
     */
    protected void openSecurityTransferOrderSend(AioOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String l_strMethodName = "openSecurityTransferOrderSend(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);


        //1.1) get�⏕����(,)
        //�A�C�e���̒�`
        //�⏕�����I�u�W�F�N�g���擾����B
        //[����] 
        //����ID�F ����.�����P��.����ID 
        //�⏕����ID�F ����.�����P��.�⏕����ID 
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);           
        WEB3GentradeAccountManager l_gentradeAccountManager =  //�g���A�J�E���g�}�l�[�W���擾���� 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
        try
        {
            SubAccount l_subAccount = l_gentradeAccountManager.getSubAccount(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId());
            log.debug("l_subAccount.getAccountId() = " + 
                l_subAccount.getAccountId());
            
            //1.2) �⏕����.getSubAccountType() != 1�i�a��������j
            //�̏ꍇ
            //1.2.1 return
            if(!SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                return;
            }
            
            //1.3) create�،��U�֒����L���[�f�[�^(AioOrderUnit)
            //�A�C�e���̒�`
            //�U�֒����̃L���[�f�[�^��DB�ɓo�^����B 
            //[����] 
            //�����P�ʁF ����.�����P�� 
            this.createSecurityTransferOrderQueueData(l_orderUnit);
            
            //1.4) is�g���K���s(String)
            //�A�C�e���̒�`
            //SONAR�փg���K�𔭍s�ł��鎞�ԑт��𔻒肷��B
            //[����] 
            //���������F 0�iDEFAULT�j 
            boolean l_blnIsSubmitTrigger = 
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                    WEB3OrderingConditionDef.DEFAULT);
            
            //1.5) is�g���K���s() = true
            if(l_blnIsSubmitTrigger)
            {
                //1.5.1) �g���K���s(String, String)
                //�A�C�e���̒�`
                //�g���K�𔭍s����B
                //[����] 
                //�،���ЃR�[�h�F�@@����.�����P��.�،����ID�ɊY������،���ЃR�[�h 
                //�f�[�^�R�[�h�F �hGI807T�h�i��p�U�֐����j 
                this.submitTrigger(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3HostRequestCodeDef.AIO_MRGSEC_TRANSFER + "T");
            }
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (create�،��U�֒����L���[�f�[�^ )<BR>
     * �،��U�֒����̃L���[�f�[�^�̐����iDB�o�^�j���s���B<BR>
     * <BR>
     *�P�j��p�U�֐����L���[Params�C���X�^���X�𐶐�����B<BR>
     *<BR>
     *�Q�j��p�U�֐����L���[Params�Ƀv���p�e�B���Z�b�g����B <BR>
     *  DB�X�V�d�l�u�،��U��_��p�U�֐����L���[�e�[�u��.xls�v�Q��<BR>
     *<BR>
     *�R�j��p�U�֐����L���[Params�̓��e��DB�ɓo�^����B <BR>
     *  WEB3DataAccessUtility.insertRow() <BR>
     *  [����] <BR>
     *  ��p�U�֐����L���[Params<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@roseuid 413D70BB020B
     */
    protected void createSecurityTransferOrderQueueData(AioOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String l_strMethodName = "createSecurityTransferOrderQueueData(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);

        try
        {
            AioOrderUnitRow l_orderUnitRow = (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //�P�j��p�U�֐����L���[Params�C���X�^���X�𐶐�����
            HostMrgsecTransferParams l_hostMrgsecTransferParams = new HostMrgsecTransferParams();
            
            //�Q�j��p�U�֐����L���[Params�Ƀv���p�e�B���Z�b�g����B
            //DB�X�V�d�l�u�،��U��_��p�U�֐����L���[�e�[�u��.xls�v�Q��
            
            //�f�[�^�R�[�h
            //GI807(��p�U�֐���)
            l_hostMrgsecTransferParams.setRequestCode(WEB3HostRequestCodeDef.AIO_MRGSEC_TRANSFER);
            
            //�،���ЃR�[�h
            //�����P��.���X�h�c�ɊY�����镔�X.�،���ЃR�[�h
            AccountManager l_accMgr = (AccountManager) GtlUtils.getAccountManager();
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_orderUnit.getAccountId());
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            
            l_hostMrgsecTransferParams.setInstitutionCode(l_strInstitutionCode);
            
            //���X�R�[�h
            //�����P��.���X�h�c�ɊY�����镔�X.���X�R�[�h
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            l_hostMrgsecTransferParams.setBranchCode(l_strBranchCode);
            
            //�ڋq�R�[�h
            //�����P��.�ڋq�h�c�ɊY������ڋq.�ڋq�R�[�h
            String l_strAccountCode = l_mainAccount.getAccountCode();
            l_hostMrgsecTransferParams.setAccountCode(l_strAccountCode); 
            
            //���҃R�[�h
            //�u�����N
            l_hostMrgsecTransferParams.setTraderCode("     ");
            
            //�U�֋敪
            //�����P��.������� = 1009�i�ی�a�肩���p�L���،��j�̏ꍇ�A
            //  02�F�ی쁨��p
            //�����P��.������� = 1010�i��p�L���،�����ی�a��j�̏ꍇ�A
            //  01�F��p���ی�
            if(OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES.equals(
                l_orderUnit.getOrderType()))
            {
                l_hostMrgsecTransferParams.setTransferFlag(WEB3AioTransferFlagDef.SAFE_DEPOSIT);
            }
            else if(OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT.equals(
                l_orderUnit.getOrderType()))
            {
                l_hostMrgsecTransferParams.setTransferFlag(WEB3AioTransferFlagDef.COLLATERAL_SECURITY);
            }
            
            //������
            //�u�����N
            l_hostMrgsecTransferParams.setOriginalTransferDate("    ");

            //����t���O
            //�u�����N
            l_hostMrgsecTransferParams.setCancelFlag(" ");

            //���i�敪
            //�����P��.�����^�C�v = 1�i�����j�̏ꍇ�A1�F��
            //�����P��.�����^�C�v = 2�i���j�̏ꍇ�A3�F��
            //�����P��.�����^�C�v = 3�i���M�j�̏ꍇ�A2�F���M
            //�����P��.�����^�C�v = 4�i�O���j�̏ꍇ�A4�F�O��
            if(ProductTypeEnum.EQUITY.equals(l_orderUnit.getProductType()))
            {
                l_hostMrgsecTransferParams.setCommodity(WEB3AioHostCommodityDef.EQUITY);
            }
            else if(ProductTypeEnum.BOND.equals(l_orderUnit.getProductType()))
            {
                l_hostMrgsecTransferParams.setCommodity(WEB3AioHostCommodityDef.BOND);
            }
            else if(ProductTypeEnum.MUTUAL_FUND.equals(l_orderUnit.getProductType()))
            {
                l_hostMrgsecTransferParams.setCommodity(WEB3AioHostCommodityDef.MUTUAL_FUND);
            }        
            
            //�����R�[�h
            //�����P��.�����^�C�v = 2�i���j�̏ꍇ�A
            //  �����P��.����ID�ɊY���������.�񍆃R�[�h(SONAR)+����.�����R�[�h(SONAR)�̏�4��
            //�����P��.�����^�C�v != 2�i���j�̏ꍇ�A
            //  �����P��.����ID�ɊY���������.�����R�[�h
            
            AioOrderUnitRow l_aioOrderUnitRow = 
                (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            String l_strProductCode = null;
            
            ProductTypeEnum l_productTypeEnum = l_aioOrderUnitRow.getProductType();

            WEB3AioProductManager l_aioProductManager = 
                (WEB3AioProductManager)GtlUtils.getTradingModule(ProductTypeEnum.AIO).getProductManager();
            
            Product l_product = l_aioProductManager.getProduct(l_productTypeEnum, l_aioOrderUnitRow.getProductId());
            
            log.debug("l_productTypeEnum = " + l_productTypeEnum);
            if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
            {   
              l_strProductCode = 
                  ((EqtypeProductRow)l_product.getDataSourceObject()).getProductCode();    
            }
            else if (ProductTypeEnum.BOND.equals(l_productTypeEnum))
            {
                String l_strHostIssueCode = 
                    ((BondProductRow)l_product.getDataSourceObject()).getHostProductIssueCode();
                String l_strHostBondProductCode = 
                    ((BondProductRow)l_product.getDataSourceObject()).getHostProductCode().substring(0, 4);
                l_strProductCode = l_strHostIssueCode + l_strHostBondProductCode;
            }
            else if (ProductTypeEnum.MUTUAL_FUND.equals(l_productTypeEnum))
            {
                l_strProductCode = 
                    ((MutualFundProductRow)l_product.getDataSourceObject()).getProductCode();    
            }
            else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_productTypeEnum))
            {
                l_strProductCode = 
                    ((FeqProductRow)l_product.getDataSourceObject()).getProductCode();    
            }
            
            //test log
            log.debug("l_strProductCode = " + l_strProductCode);
            
            l_hostMrgsecTransferParams.setProductCode(l_strProductCode);
          
            //����
            double l_dbQuantity =  Math.abs(l_orderUnit.getQuantity());
            // �����P��.�����^�C�v == 3�i���M�j�̏ꍇ
            if (ProductTypeEnum.MUTUAL_FUND.equals(l_orderUnit.getProductType()))
            {
                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow)l_product.getDataSourceObject();
                // ���M�����}�X�^.���M�����^�C�v == 2�i���O�j or �i���M�����}�X�^.���M�����^�C�v == 0�i���̑��j
                // and ���M�����}�X�^.�ʉ݃R�[�h != T0(�~)�j�̏ꍇ
                if (MutualFundTypeEnum.FOREIGN.equals(l_mutualFundProductRow.getFundType()) ||
                    (MutualFundTypeEnum.OTHER.equals(l_mutualFundProductRow.getFundType()) &&
                    !WEB3CurrencyCodeDef.T0.equals(l_mutualFundProductRow.getCurrencyCode())))
                {
                    // ���M�����}�X�^.���͒P�ʂ��h1�F1�h�̏ꍇ �����P��.���ʂ̐�Βl�@@�~�@@1000
                    if (WEB3InputUnitDef.ONE.equals(l_mutualFundProductRow.getInputUnit()))
                    {
                        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dbQuantity));
                        BigDecimal l_bdResult = l_bdQuantity.multiply(new BigDecimal(1000));
                        l_hostMrgsecTransferParams.setQuantity(l_bdResult.longValue());
                    }
                    // ���M�����}�X�^.���͒P�ʂ��h2�F1���h�̏ꍇ �����P��.���ʂ̐�Βl / 10000�@@�~�@@1000
                    else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_mutualFundProductRow.getInputUnit()))
                    {
                        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dbQuantity));
                        BigDecimal l_bdResult =
                            l_bdQuantity.divide(new BigDecimal(10000), 0).multiply(new BigDecimal(1000));
                        l_hostMrgsecTransferParams.setQuantity(l_bdResult.longValue());
                    }
                }
                // ����ȊO�̏ꍇ
                else
                {
                    // ���M�����}�X�^.���͒P�ʂ��h1�F1�h�̏ꍇ �����P��.���ʂ̐�Βl
                    if (WEB3InputUnitDef.ONE.equals(l_mutualFundProductRow.getInputUnit()))
                    {
                        l_hostMrgsecTransferParams.setQuantity((long)l_dbQuantity);
                    }
                    // ���M�����}�X�^.���͒P�ʂ��h2�F1���h�̏ꍇ �����P��.���ʂ̐�Βl / 10000
                    else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_mutualFundProductRow.getInputUnit()))
                    {
                        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dbQuantity));
                        BigDecimal l_bdResult =
                            l_bdQuantity.divide(new BigDecimal(10000), 0);
                        l_hostMrgsecTransferParams.setQuantity(l_bdResult.longValue());
                    }
                }
            }
            // �����P��.�����^�C�v != 3�i���M�ȊO�j�̏ꍇ
            // �@@�����P��.���ʂ̐�Βl
            else
            {
                l_hostMrgsecTransferParams.setQuantity((long)l_dbQuantity);
            }
            //�P��
            //�u�����N
            l_hostMrgsecTransferParams.setPrice(null);

            //�d�@@�敪
            //�����P��.�����^�C�v = 1�i�����j or 4�i�O���j�̏ꍇ�A�u�����N
            //�����P��.�����^�C�v = 2�i���j or 3�i���M�j�̏ꍇ�A1
            if(ProductTypeEnum.EQUITY.equals(l_orderUnit.getProductType())
                || ProductTypeEnum.FOREIGN_EQUITY.equals(l_orderUnit.getProductType()))
            {
                l_hostMrgsecTransferParams.setDelivType(" ");
            }
            else if(ProductTypeEnum.BOND.equals(l_orderUnit.getProductType())
                      || ProductTypeEnum.MUTUAL_FUND.equals(l_orderUnit.getProductType()))
            {
                l_hostMrgsecTransferParams.setDelivType("1");
            }
            
            //���ʃR�[�h
            //�����P��.���ʃR�[�h
            l_hostMrgsecTransferParams.setOrderRequestNumber(l_aioOrderUnitRow.getOrderRequestNumber());
            
            //�U�֎w����
            //�����P��.������
            //�����������́A00:00  
            l_hostMrgsecTransferParams.setTransferDate(
                WEB3DateUtility.toDay(
                    WEB3DateUtility.getDate(l_aioOrderUnitRow.getBizDate(), "yyyyMMdd")));

            //�U�֎w������
            //00:00:00  �����t�����́A�����P��.������  
            l_hostMrgsecTransferParams.setTransferTime(
                WEB3DateUtility.toDay(
                    WEB3DateUtility.getDate(l_aioOrderUnitRow.getBizDate(), "yyyyMMdd")));                           
            
            //�����敪
            //0�F������        
            l_hostMrgsecTransferParams.setStatus(WEB3StatusDef.NOT_DEAL);
            
            //��������敪
            //�����P��.�ŋ敪 = 1�i��ʁj�̏ꍇ�A0�F��ʌ���
            //�����P��.�ŋ敪 = 2�i����j or 3�i���肩����j�̏ꍇ�A1�F�������
            log.debug("l_orderUnit.getTaxType()= " + l_orderUnitRow.getTaxType());
            
            if(TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getTaxType()))
            {
                l_hostMrgsecTransferParams.setSpecificAccountDiv(WEB3TaxTypeSpecialDef.NORMAL);
            }
            else if(TaxTypeEnum.SPECIAL.equals(l_orderUnitRow.getTaxType())
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_orderUnit.getTaxType()))
            {
                l_hostMrgsecTransferParams.setSpecificAccountDiv(WEB3TaxTypeSpecialDef.SPECIAL);
            }  

            //�A��
            //�����P��.�����^�C�v = 1�i�����j�̏ꍇ�A0�F�@@�\�A��
            //�����P��.�����^�C�v != 1�i�����j�̏ꍇ�A �F�u�����N
            if (ProductTypeEnum.EQUITY.equals(l_orderUnit.getProductType()))
            {
                l_hostMrgsecTransferParams.setInterlock(
                    WEB3InterLockDef.INSTITUTE_INTERLOCKING_MOVEMENT);
            }
            else
            {
                l_hostMrgsecTransferParams.setInterlock(WEB3InterLockDef.DEFAULT);
            }

            //�a��
            //�F�u�����N
            l_hostMrgsecTransferParams.setDeposit(WEB3DepositDef.DEFAULT);

            //�R�j��p�U�֐����L���[Params�̓��e��DB�ɓo�^����
            //WEB3DataAccessUtility.insertRow()>
            //  [����] <BR>
            //  ��p�U�֐����L���[Params<BR>
            WEB3DataAccessUtility.insertRow(l_hostMrgsecTransferParams);

        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMainAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataException l_ex)
        {
            log.error("Error In ��p�U�֐����L���[Params�̓��e��DB�ɓo�^���� ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(l_strMethodName);
        
    }
    //=============remain zhou-yong 2004/12/6 NO.1 end ===============
    
    //=============remain wei-nianqiong 2005/01/21 NO.125 start ======
    /**
     * (update�U�֏����敪) <BR>
     * <BR>
     * �U�֐��������L���[�e�[�u���̏����敪��0�i�������j�ɁA <BR>
     * �����P�ʃe�[�u����MQ�X�e�[�^�X��1�i���M�ς݁j�ɍX�V����B <BR>
     * �X�V�㔭�����A�X�V���n�����ݒ肳��Ă���ꍇ�A�Ή����鍀�ڂ̍X�V���s���B <BR>
     * <BR>
     * �P�j�����P�ʃe�[�u���̍X�V <BR>
     * <BR>
     * �P�|�P�j�ȉ��̏����ŁA�����P�ʃe�[�u�����烌�R�[�h���擾����B <BR>
     * <BR>
     * [��������] <BR>
     * �����P��ID�F ����.�����P��ID <BR>
     * <BR>
     * �P�|�Q�j�X�V���e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uFX�ւ̐U��_�����P�ʃe�[�u��.xls�v <BR>
     * �uFX�ւ̐U��_�����P�ʃe�[�u��_DB�X�V�d�l[���ʒʒm]�v�V�[�g <BR>
     * <BR>
     * �P�|�R�j�Y�����R�[�h���X�V����B <BR>
     * <BR>
     * �Q�j���������e�[�u���̍X�V <BR>
     * <BR>
     * �Q�|�P�j�ȉ��̏����ŁA���������e�[�u�����烌�R�[�h���擾����B <BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@�����P��ID�F�@@����.�����P��ID <BR>
     * �@@��������ԍ��F�@@�P�j�Ŏ擾���������P��.���������ŏI�ʔ� <BR>
     * <BR>
     * �Q�|�Q�j�X�V���e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uFX�ւ̐U��_���������e�[�u��.xls�v <BR>
     * �uFX�ւ̐U��_���������e�[�u��_DB�X�V�d�l[���ʒʒm]�v�V�[�g <BR>
     * <BR>
     * �Q�|�R�j�Y�����R�[�h���X�V����B <BR>
     * <BR>
     * �R�j�����e�[�u���i�w�b�_�j�̍X�V <BR>
     * <BR>
     * �R�|�P�j�ȉ��̏����ŁA�����e�[�u���i�w�b�_�j���烌�R�[�h���擾����B <BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@����ID�@@���@@�P�j�Ŏ擾���������P��.����ID <BR>
     * <BR>
     * �R�|�Q�j�X�V���e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uFX�ւ̐U��_�����e�[�u��(�w�b�_).xls�v <BR>
     * �uFX�ւ̐U��_�����e�[�u��(�w�b�_)_DB�X�V�d�l[���ʒʒm]�v�V�[�g <BR>
     * <BR>
     * �R�|�R�j�Y�����R�[�h���X�V����B <BR>
     * <BR>
     * �S�j�U�֐��������L���[�e�[�u���̍X�V <BR>
     * <BR>
     * �S�|�P�j�ȉ��̏����ŁA�U�֐��������L���[�e�[�u�����烌�R�[�h���擾����B <BR>
     * <BR>
     * [��������] <BR>
     * �f�[�^�R�[�h�F GI806�i�ۏ؋��U�֐����j <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.���X�R�[�h <BR>
     * �ڋq�R�[�h�F ����.�ڋq�R�[�h <BR>
     * ���ʃR�[�h�F ����.���ʃR�[�h <BR>
     * �����敪�F 3�i�������j <BR>
     * <BR>
     * �S�|�Q�j�X�V���e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uFX�ւ̐U��_�U�֐��������L���[�e�[�u��.xls�v <BR>
     * �uFX�ւ̐U��_�U�֐��������L���[�e�[�u��_DB�X�V[���ʒʒm]�v�V�[�g <BR>
     * <BR>
     * �S�|�R�j�Y�����R�[�h���X�V����B <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strAccountCode - (�ڋq�R�[�h)
     * @@param l_strOrderRequestNumber  - (���ʃR�[�h)
     * @@param l_lngOrderUnitId - (�����P��ID)
     * @@param l_strUpdatedBizDate - (�X�V�㔭����)
     * @@param l_strUpdatedDeliveryDate - (�X�V���n��)
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 416D749D019D
     */   
    public void updateTransferProcessDiv(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strOrderRequestNumber , 
            long l_lngOrderUnitId, 
            String l_strUpdatedBizDate, 
            String l_strUpdatedDeliveryDate)
    throws WEB3BaseException
    {
        final String l_strMethodName = "updateTransferProcessDiv(" +
            "String, String, String, String, long, String, String)";
        log.entering(l_strMethodName);
        
        try
        {
            //�P�j�����P�ʃe�[�u���̍X�V 
            //�P�|�P�j�ȉ��̏����ŁA�����P�ʃe�[�u�����烌�R�[�h���擾����B 
            //[��������] 
            //�����P��ID�F ����.�����P��ID 
            AioOrderUnitRow l_orderUnitRow = 
                AioOrderUnitDao.findRowByPk(l_lngOrderUnitId);
            log.debug("�����P�ʃe�[�u��Row := " + l_orderUnitRow);
            
            AioOrderUnitParams l_orderUnitParams = new AioOrderUnitParams(l_orderUnitRow);

            //����.�X�V�㔭���� != null�̏ꍇ�A����.�X�V�㔭����
            //�ȊO�A�i�����l�j
            if (l_strUpdatedBizDate != null)
            {
                log.debug("����.�X�V�㔭���� != null�̏ꍇ");
                l_orderUnitParams.setBizDate(l_strUpdatedBizDate);
            }
            //����.�X�V���n�� != null�̏ꍇ�A����.�X�V���n��
            //�ȊO�A�i�����l�j
            if (l_strUpdatedDeliveryDate != null)
            {
                log.debug("����.�X�V���n�� != null�̏ꍇ");
                Date l_datDeliveryDate = 
                    WEB3DateUtility.getDate(l_strUpdatedDeliveryDate,"yyyyMMdd");
                l_orderUnitParams.setDeliveryDate(l_datDeliveryDate);
            }
            
            //�P�|�Q�j�X�V���e            
            //MQ�X�e�[�^�X��1�F���M�ς�
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            //�X�V���t�ɃV�X�e���^�C���X�^���v���Z�b�g����B
            l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //�P�|�R�j�Y�����R�[�h���X�V����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);
            log.debug("updated �����P�ʃe�[�u��Row := " + l_orderUnitParams);
            
            //�Q�j���������e�[�u���̍X�V 
            //�Q�|�P�j�ȉ��̏����ŁA���������e�[�u�����烌�R�[�h���擾����B 
            //[��������] 
            //�����P��ID�F�@@����.�����P��ID 
            //��������ԍ��F�@@�P�j�Ŏ擾���������P��.���������ŏI�ʔ� 

            List l_lisOrderActionRows = l_queryProcessor.doFindAllQuery(
                AioOrderActionRow.TYPE,
                "order_unit_id = ? and order_action_serial_no = ?",
                null,
                new Object[] {
                    new Long(l_lngOrderUnitId), 
                    new Long(l_orderUnitRow.getLastOrderActionSerialNo())
                    });
            if (l_lisOrderActionRows.size() == 1)
            {
                AioOrderActionRow l_orderActionRow = 
                    (AioOrderActionRow)l_lisOrderActionRows.get(0);
                AioOrderActionParams l_orderActionParams = 
                    new AioOrderActionParams(l_orderActionRow);
                l_orderActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //�Q�|�R�j�Y�����R�[�h���X�V����B
                l_queryProcessor.doUpdateQuery(l_orderActionParams);
                log.debug("updated ���������e�[�u��Row := " + l_orderActionParams);
                
                //�R�j�����e�[�u���i�w�b�_�j�̍X�V 
                //�R�|�P�j�ȉ��̏����ŁA�����e�[�u���i�w�b�_�j���烌�R�[�h���擾����B 
                //[��������] 
                //����ID�@@���@@�P�j�Ŏ擾���������P��.����ID 
                AioOrderRow l_orderRow = 
                    AioOrderDao.findRowByPk(l_orderUnitRow.getOrderId());
                log.debug("�����e�[�u��Row := " + l_orderRow);
                
                AioOrderParams l_orderParams = 
                    new AioOrderParams(l_orderRow);
                l_orderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //�R�|�R�j�Y�����R�[�h���X�V����B
                l_queryProcessor.doUpdateQuery(l_orderParams);
                log.debug("updated �����e�[�u��Row := " + l_orderParams);
            }
            
            //�S�j�U�֐��������L���[�e�[�u���̍X�V 
            //�S�|�P�j�ȉ��̏����ŁA�U�֐��������L���[�e�[�u�����烌�R�[�h���擾����B 
            //[��������] 
            //�f�[�^�R�[�h�F GI806�i�ۏ؋��U�֐����j 
            //�،���ЃR�[�h�F ����.�،���ЃR�[�h 
            //���X�R�[�h�F ����.���X�R�[�h 
            //�ڋq�R�[�h�F ����.�ڋq�R�[�h 
            //���ʃR�[�h�F ����.���ʃR�[�h 
            //�����敪�F 3�i�������j 
            List l_lisHostTransferOrderRows = l_queryProcessor.doFindAllQuery(
                HostTransferOrderRow.TYPE,
                "request_code = ? and institution_code = ? and " +
                "branch_code = ? and account_code = ? and " +
                "order_request_number = ? and status = ?",
                null,
                new Object[] {
                        WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ORDER, 
                        l_strInstitutionCode, 
                        l_strBranchCode, 
                        l_strAccountCode, 
                        l_strOrderRequestNumber,
                        WEB3AioHostStatusDef.NOT_DEAL
                        });
            
            if (l_lisHostTransferOrderRows.size() == 1)
            {
                HostTransferOrderRow l_hostTransferOrderRow = 
                    (HostTransferOrderRow)l_lisHostTransferOrderRows.get(0);
                HostTransferOrderParams l_hostTransferOrderParams = 
                    new HostTransferOrderParams(l_hostTransferOrderRow);
                
                log.debug("before �U�֐��������L���[�e�[�u��Row := " + 
                        l_hostTransferOrderRow);
                
                AccountManager l_accMgr = (AccountManager) GtlUtils.getAccountManager();
                MainAccount l_mainAccount =
                	l_accMgr.getMainAccount(l_orderUnitParams.getAccountId());

                WEB3GentradeBranch l_branch =
                	(WEB3GentradeBranch) l_mainAccount.getBranch();

                //���U�֌���:
                //�������.������ = �����P��.��n��(MMDD)�̏ꍇ�A�u�����N���Z�b�g
                //��L�ȊO�̏ꍇ�A�����P��.��n���iMMDD�j���Z�b�g
                if (WEB3DateUtility.compareToDay(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(),
                    l_orderUnitParams.getDeliveryDate()) == 0)
                {
                    l_hostTransferOrderParams.setOriginalTransferDate(
                        WEB3AioOriginalTransferDateBlankDef.BLANK);
                }
                else
                {
                    String l_strDeliveryMD = WEB3DateUtility.formatDate(
                        l_orderUnitParams.getDeliveryDate(),
                        WEB3GentradeTimeDef.DATE_FORMAT_MD);
                    l_hostTransferOrderParams.setOriginalTransferDate(l_strDeliveryMD);
                }

                //�����P��.������ �����������́A00:00
                l_hostTransferOrderParams.setTransferDate(
                        WEB3DateUtility.getDate(
                            l_orderUnitRow.getBizDate() + "00:00", 
                            "yyyyMMddHH:mm"));
                
                //���ݎ������g���K���s�����{���鎞�ԑт̏ꍇ�A00:00���Z�b�g            
                //�����t�����́A�����P��.������
                Date l_datTransferTime = null;
                if (WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
                {
                    log.debug("���ݎ������g���K���s�����{���鎞�ԑт̏ꍇ");
                    l_datTransferTime = WEB3DateUtility.getDate(
                        l_orderUnitRow.getBizDate() + "00:00",
                        "yyyyMMddHH:mm");
                }
                //���ݎ������g���K���s�����{���Ȃ����ԑт̏ꍇ�A06:00���Z�b�g
                //�����t�����́A�����P��.������
                else
                {
                    l_datTransferTime = WEB3DateUtility.getDate(
                        l_orderUnitRow.getBizDate() + "06:00",
                        "yyyyMMddHH:mm");
                }
                l_hostTransferOrderParams.setTransferTime(l_datTransferTime);
                
                //�����敪 = 0:������
                l_hostTransferOrderParams.setStatus(
                    WEB3AioHostStatusDef.DEALING); //0:������
                
                //�S�|�R�j�Y�����R�[�h���X�V����B
                l_queryProcessor.doUpdateQuery(l_hostTransferOrderParams);
                log.debug("updated �U�֐��������L���[�e�[�u��Row := " + 
                        l_hostTransferOrderParams);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException Error ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException Error In", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_ex) {
            log.error("NotFoundException Error In", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
		}
        
        log.exiting(l_strMethodName);
    }
    //=============remain wei-nianqiong 2005/01/21 NO.125 end ========
    
    /**
     * (update�U�֏����敪) <BR>
     * <BR>
     * �U�֐��������L���[�e�[�u���̏����敪��0�i�������j�ɁA <BR>
     * �����P�ʃe�[�u����MQ�X�e�[�^�X��1�i���M�ς݁j�ɍX�V����B <BR>
     * �X�V�㔭�����A�X�V���n�����ݒ肳��Ă���ꍇ�A�Ή����鍀�ڂ̍X�V���s���B <BR>
     * <BR>
     * �P�j�����P�ʃe�[�u���̍X�V <BR>
     * <BR>
     * �P�|�P�j�ȉ��̏����ŁA�����P�ʃe�[�u�����烌�R�[�h���擾����B <BR>
     * <BR>
     *   [��������] <BR>
     *   �����P��ID�F ����.�����P��ID <BR>
     * <BR>
     * �P�|�Q�j�X�V���e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V  <BR>
     * �uFX�ւ̐U��_�����P�ʃe�[�u��.xls�v <BR>
     * �uFX�ւ̐U��_�����P�ʃe�[�u��_DB�X�V�d�l(���ʒʒm2)�v�V�[�g <BR>
     * <BR>
     * �P�|�R�j�Y�����R�[�h���X�V����B <BR>
     * <BR>
     * �Q�j���������e�[�u���̍X�V <BR>
     * <BR>
     * �Q�|�P�j�ȉ��̏����ŁA���������e�[�u�����烌�R�[�h���擾����B <BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@�����P��ID�F�@@����.�����P��ID <BR>
     * �@@��������ԍ��F�@@�P�j�Ŏ擾���������P��.���������ŏI�ʔ�  <BR>
     * 
     * �Q�|�Q�j�X�V���e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V  <BR>
     * �uFX�ւ̐U��_���������e�[�u��.xls�v <BR>
     * �uFX�ւ̐U��_���������e�[�u��_DB�X�V�d�l(���ʒʒm2)�v�V�[�g <BR>
     * <BR>
     * �Q�|�R�j�Y�����R�[�h���X�V����B <BR>
     * <BR>
     * �R�j�����e�[�u���i�w�b�_�j�̍X�V <BR>
     * <BR>
     * �R�|�P�j�ȉ��̏����ŁA�����e�[�u���i�w�b�_�j���烌�R�[�h���擾����B <BR>
     * <BR>
     * �@@[��������]  <BR>
     * �@@����ID�@@���@@�P�j�Ŏ擾���������P��.����ID  <BR>
     * <BR>
     * �R�|�Q�j�X�V���e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V  <BR>
     * �uFX�ւ̐U��_�����e�[�u��(�w�b�_).xls�v <BR>
     * �uFX�ւ̐U��_�����e�[�u��(�w�b�_)_DB�X�V�d�l[���ʒʒm]�v�V�[�g <BR>
     * <BR>
     * �R�|�R�j�Y�����R�[�h���X�V����B <BR>
     * <BR>
     * �S�j�U�֐��������L���[�e�[�u���̍X�V  <BR>
     * <BR>
     * �S�|�P�j�ȉ��̏����ŁA�U�֐��������L���[�e�[�u�����烌�R�[�h���擾����B  <BR>
     * <BR>
     * [��������]  <BR>
     * �f�[�^�R�[�h�F GI806�i�ۏ؋��U�֐����j  <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h  <BR>
     * ���X�R�[�h�F ����.���X�R�[�h  <BR>
     * �ڋq�R�[�h�F ����.�ڋq�R�[�h  <BR>
     * ���ʃR�[�h�F ����.���ʃR�[�h  <BR>
     * �����敪�F 3�i�������j  <BR>
     * <BR>
     * �S�|�Q�j�X�V���e�͉��L���Q�ƁB  <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V  <BR>
     * �uFX�ւ̐U��_�U�֐��������L���[�e�[�u��.xls�v  <BR>
     * �u_�U�֐��������L���[�e�[�u��DB�X�V(���ʒʒm2)�v�V�[�g  <BR>
     * <BR>
     * �S�|�R�j�Y�����R�[�h���X�V����B <BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     * @@param l_strOrderRequestNumber  - (���ʃR�[�h)<BR>
     * ���ʃR�[�h
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID
     * @@param l_strUpdatedBizDate - (�X�V�㔭����)<BR>
     * �X�V�㔭���� <BR>
     * YYYYMMDD <BR>
     * ���X�V���Ȃ��ꍇ�Anull<BR>
     * @@param l_strUpdatedDeliveryDate - (�X�V���n��)<BR>
     * �X�V���n�� <BR>
     * YYYYMMDD<BR>
     * ���X�V���Ȃ��ꍇ�Anull
     * @@param l_strCashInOutAmount - (���o���z)<BR>
     * ���o���z<BR>
     * @@param l_strRemarkCode - (�E�v�R�[�h)<BR>
     * �E�v�R�[�h
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 416D749D019D
     */   
    public void updateTransferProcessDiv(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strOrderRequestNumber , 
        long l_lngOrderUnitId, 
        String l_strUpdatedBizDate, 
        String l_strUpdatedDeliveryDate, 
        String l_strCashInOutAmount, 
        String l_strRemarkCode) throws WEB3BaseException
    {
        final String l_strMethodName = "updateTransferProcessDiv(" +
            "String, String, String, String, long, String, String, String, String)";
        log.entering(l_strMethodName);
        
        try
        {
            //�P�j�����P�ʃe�[�u���̍X�V 
            //�P�|�P�j�ȉ��̏����ŁA�����P�ʃe�[�u�����烌�R�[�h���擾����B 
            //[��������] 
            //�����P��ID�F ����.�����P��ID 
            AioOrderUnitRow l_orderUnitRow = 
                AioOrderUnitDao.findRowByPk(l_lngOrderUnitId);

            AioOrderUnitParams l_orderUnitParams = 
                new AioOrderUnitParams(l_orderUnitRow);

            //�P�|�Q�j�X�V���e
            //�y��Trade�z�⑫����.DB�X�V  
            //�uFX�ւ̐U��_�����P�ʃe�[�u��.xls�v 
            //�uFX�ւ̐U��_�����P�ʃe�[�u��_DB�X�V�d�l(���ʒʒm2)�v�V�[�g 

            //����.���o���z
            log.debug("����.���o���z���Z�b�g");
            l_orderUnitParams.setQuantity(Double.parseDouble(l_strCashInOutAmount));

            //����.�X�V�㔭���� != null�̏ꍇ�A����.�X�V�㔭����
            //�ȊO�A�i�����l�j
            if (l_strUpdatedBizDate != null)
            {
                log.debug("����.�X�V�㔭���� != null�̏ꍇ");
                l_orderUnitParams.setBizDate(l_strUpdatedBizDate);
            }
            //����.�X�V���n�� != null�̏ꍇ�A����.�X�V���n��
            //�ȊO�A�i�����l�j
            if (l_strUpdatedDeliveryDate != null)
            {
                log.debug("����.�X�V���n�� != null�̏ꍇ");
                Date l_datDeliveryDate = 
                    WEB3DateUtility.getDate(l_strUpdatedDeliveryDate,"yyyyMMdd");
                l_orderUnitParams.setDeliveryDate(l_datDeliveryDate);
            }

            //MQ�X�e�[�^�X��1�F���M�ς�
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            //�X�V���t�ɃV�X�e���^�C���X�^���v���Z�b�g����B
            l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            //�E�v�R�[�h�Ɉ���.�E�v�R�[�h���Z�b�g����B
            l_orderUnitParams.setRemarkCode(l_strRemarkCode);
            
            //�P�|�R�j�Y�����R�[�h���X�V����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);
            
            //�Q�j���������e�[�u���̍X�V 
            //�Q�|�P�j�ȉ��̏����ŁA���������e�[�u�����烌�R�[�h���擾����B 
            //[��������] 
            //�����P��ID�F�@@����.�����P��ID 
            //��������ԍ��F�@@�P�j�Ŏ擾���������P��.���������ŏI�ʔ� 

            List l_lisOrderActionRows = l_queryProcessor.doFindAllQuery(
                AioOrderActionRow.TYPE,
                "order_unit_id = ? and order_action_serial_no = ?",
                null,
                new Object[] {
                    new Long(l_lngOrderUnitId), 
                    new Long(l_orderUnitRow.getLastOrderActionSerialNo())
                    });
            if (l_lisOrderActionRows.size() == 1)
            {
                AioOrderActionRow l_orderActionRow = 
                    (AioOrderActionRow)l_lisOrderActionRows.get(0);
                AioOrderActionParams l_orderActionParams = 
                    new AioOrderActionParams(l_orderActionRow);
                
                //�Q�|�Q�j�X�V���e�͉��L���Q�ƁB 

                //�y��Trade�z�⑫����.DB�X�V  
                //�uFX�ւ̐U��_���������e�[�u��.xls�v 
                //�uFX�ւ̐U��_���������e�[�u��_DB�X�V�d�l(���ʒʒm2)�v�V�[�g 

                //����.���o���z
                log.debug("����.���o���z���Z�b�g");
                l_orderActionParams.setQuantity(Double.parseDouble(l_strCashInOutAmount));

                l_orderActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //�Q�|�R�j�Y�����R�[�h���X�V����B
                l_queryProcessor.doUpdateQuery(l_orderActionParams);
                
                //�R�j�����e�[�u���i�w�b�_�j�̍X�V 
                //�R�|�P�j�ȉ��̏����ŁA�����e�[�u���i�w�b�_�j���烌�R�[�h���擾����B 
                //[��������] 
                //����ID�@@���@@�P�j�Ŏ擾���������P��.����ID 
                AioOrderRow l_orderRow = 
                    AioOrderDao.findRowByPk(l_orderUnitRow.getOrderId());
                
                AioOrderParams l_orderParams = 
                    new AioOrderParams(l_orderRow);
                
                //�R�|�Q�j�X�V���e�͉��L���Q�ƁB 

                //�y��Trade�z�⑫����.DB�X�V  
                //�uFX�ւ̐U��_�����e�[�u��(�w�b�_).xls�v 
                //�uFX�ւ̐U��_�����e�[�u��(�w�b�_)_DB�X�V�d�l[���ʒʒm]�v�V�[�g 

                l_orderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //�R�|�R�j�Y�����R�[�h���X�V����B
                l_queryProcessor.doUpdateQuery(l_orderParams);
            }
            
            //�S�j�U�֐��������L���[�e�[�u���̍X�V 
            //�S�|�P�j�ȉ��̏����ŁA�U�֐��������L���[�e�[�u�����烌�R�[�h���擾����B 
            //[��������] 
            //�f�[�^�R�[�h�F GI806�i�ۏ؋��U�֐����j 
            //�،���ЃR�[�h�F ����.�،���ЃR�[�h 
            //���X�R�[�h�F ����.���X�R�[�h 
            //�ڋq�R�[�h�F ����.�ڋq�R�[�h 
            //���ʃR�[�h�F ����.���ʃR�[�h 
            //�����敪�F 3�i�������j
            
            String l_strWhere = 
                "request_code = ? and institution_code = ? " +
                "and branch_code = ? and account_code = ? " +
                "and order_request_number = ? and status = ?";   
            
            Object[] l_objBind = 
                new Object[] {
                    WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ORDER, 
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    l_strOrderRequestNumber,
                    WEB3AioHostStatusDef.NOT_DEAL};
            
            List l_lisHostTransferOrderRows = 
                l_queryProcessor.doFindAllQuery(
                    HostTransferOrderRow.TYPE,
                    l_strWhere,
                    null,
                    l_objBind);
            
            //�S�|�Q�j�X�V���e�͉��L���Q�ƁB  
            //
            //�y��Trade�z�⑫����.DB�X�V  
            //�uFX�ւ̐U��_�U�֐��������L���[�e�[�u��.xls�v  
            //�u_�U�֐��������L���[�e�[�u��DB�X�V(���ʒʒm2)�v�V�[�g 
            if (l_lisHostTransferOrderRows.size() == 1)
            {
                HostTransferOrderRow l_hostTransferOrderRow = 
                    (HostTransferOrderRow)l_lisHostTransferOrderRows.get(0);
                
                HostTransferOrderParams l_hostTransferOrderParams = 
                    new HostTransferOrderParams(l_hostTransferOrderRow);

                AccountManager l_accMgr = 
                    (AccountManager) GtlUtils.getAccountManager();
                
                MainAccount l_mainAccount =
                    l_accMgr.getMainAccount(l_orderUnitParams.getAccountId());

                WEB3GentradeBranch l_branch =
                    (WEB3GentradeBranch) l_mainAccount.getBranch();

                //���U�֌���:
                //�������.������ = �����P��.��n��(MMDD)�̏ꍇ�A�u�����N���Z�b�g
                //��L�ȊO�̏ꍇ�A�����P��.��n���iMMDD�j���Z�b�g
                if (WEB3DateUtility.compareToDay(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(),
                    l_orderUnitParams.getDeliveryDate()) == 0)
                {
                    l_hostTransferOrderParams.setOriginalTransferDate(
                        WEB3AioOriginalTransferDateBlankDef.BLANK);
                }
                else
                {
                    String l_strDeliveryMD = WEB3DateUtility.formatDate(
                        l_orderUnitParams.getDeliveryDate(),
                        WEB3GentradeTimeDef.DATE_FORMAT_MD);
                    l_hostTransferOrderParams.setOriginalTransferDate(l_strDeliveryMD);
                }

                //�U�֏o���z�i�ؕ��j: ����.���o���z
                if (l_strCashInOutAmount != null)
                {
                    l_hostTransferOrderParams.setTransferAmountDebitor(
                        Integer.parseInt(l_strCashInOutAmount)); 
                }

                //�E�v�R�[�h : ����.�E�v�R�[�h
                l_hostTransferOrderParams.setRemarkCode(l_strRemarkCode);

                //�U�֎w����
                //�����P��.������ �����������́A00:00
                l_hostTransferOrderParams.setTransferDate(
                    WEB3DateUtility.getDate(
                        l_orderUnitRow.getBizDate() + "00:00", 
                        "yyyyMMddHH:mm"));
                
                //�U�֎w������
                //���ݎ������g���K���s�����{���鎞�ԑт̏ꍇ�A00:00���Z�b�g            
                //�����t�����́A�����P��.������
                Date l_datTransferTime = null;
                if (WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
                {
                    log.debug("���ݎ������g���K���s�����{���鎞�ԑт̏ꍇ");
                    l_datTransferTime = WEB3DateUtility.getDate(
                        l_orderUnitRow.getBizDate() + "00:00",
                        "yyyyMMddHH:mm");
                }
                //���ݎ������g���K���s�����{���Ȃ����ԑт̏ꍇ�A06:00���Z�b�g
                //�����t�����́A�����P��.������
                else
                {
                    l_datTransferTime = WEB3DateUtility.getDate(
                        l_orderUnitRow.getBizDate() + "06:00",
                        "yyyyMMddHH:mm");
                }
                l_hostTransferOrderParams.setTransferTime(l_datTransferTime);
                
                //�����敪 = 0:������
                l_hostTransferOrderParams.setStatus(
                    WEB3AioHostStatusDef.DEALING); //0:������
                
                //�S�|�R�j�Y�����R�[�h���X�V����B
                l_queryProcessor.doUpdateQuery(l_hostTransferOrderParams);
                log.debug("updated �U�֐��������L���[�e�[�u��Row := " + 
                        l_hostTransferOrderParams);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_ex) {
            log.error("Error In getMainAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(l_strMethodName);

    }
    
    /**
     * (create�O�ݓ��������L���[�f�[�^ )<BR>
     * �O�ݓ��������̃L���[�f�[�^�̐����iDB�o�^�j���s���B<BR>
     * <BR>
     * �P�j�O�ݓ��o���`�[�L���[Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�O�ݓ��o���`�[�L���[Params�Ƀv���p�e�B���Z�b�g����B<BR>
     *  <BR>
     * ���ڍׂ́ADB�X�V�d�l�u�O�ݓ��o���`�[�L���[�e�[�u��.xls�v�Q��<BR>
     * <BR>
     * <BR>
     * ������������������������������������������������<BR>
     * [���o�������P��.�����o�H���h�����A�g�h�̏ꍇ]<BR>
     * �ڍׂ͍X�V�d�l�u�����ʒm_�O�ݓ��o���`�[�L���[�e�[�u��.xls�v�Q��<BR>
     * ���o�����@@�A������R�[�h�݈̂ȉ��ɓ��L�B<BR>
     * <BR>
     * �E�O�ݓ��o���`�[�L���[Params.���o�����@@<BR>
     * = ���Z�@@�փe�[�u��.���o���`�[�L���[�p���o�����@@<BR>
     * �E�O�ݓ����`�[�L���[Params.����R�[�h<BR>
     * = ���Z�@@�փe�[�u��.���o���`�[�L���[�p����R�[�h<BR>
     * <BR>
     * ��<BR>
     * ���Z�@@�փe�[�u�����R�[�h�̎擾<BR>
     * ���Z�@@�փe�[�u�����ȉ��̏����Ō����B<BR>
     * [����]<BR>
     * �،���ЃR�[�h = �،����.�،���ЃR�[�h<BR>
     * ���Z�@@�փR�[�h = ���o�������P��.�U�֋L�q<BR>
     * <BR>
     * �i���o�������P��.�U�֋L�q��null�ł����A�Ɩ��I�ɂ͒l�͕K�������悤�Ɏ�������܂��B<BR>
     * �������V�X�e���I��null������\��������̂ŁA<BR>
     * ���Z�@@�փe�[�u���������́A�ȉ��̂悤�ɂ��Ă��������B�j<BR>
     * <BR>
     * �P�j���Z�@@�փe�[�u��PK���쐬<BR>
     * �i�R���X�g���N�^�����F�،���ЃR�[�h=�،����.�،���ЃR�[�h, ���Z�@@�փR�[�h = ���o�������P��.�U�֋L�q�j<BR>
     * �Q�j���Z�@@�փe�[�u��Dao��PK����<BR>
     * �R�j���R�[�h���Ȃ��ꍇ�ADataFindException�������邽��<BR>
     * DataFindException��catch������ǉ��B<BR>
     * WEB3SystemLayerException���X���[����B<BR>
     * <BR>
     * <BR>
     * ������������������������������������������������<BR>
     * <BR>
     * <BR>
     * �R�j�O�ݓ��o���`�[�L���[Params�̓��e��DB�ɓo�^����B<BR>
     * <BR>
     *�@@ WEB3DataAccessUtility.insertRow()<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�O�ݓ��o���`�[�L���[Params<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F647780100
     */
    protected void createHostForeignCashinOrderData(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createHostForeignCashinOrderData"
            + "(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        //�P�j�O�ݓ��o���`�[�L���[Params�C���X�^���X�𐶐�����B
        HostForeignCashTransOrderParams l_params = new HostForeignCashTransOrderParams();

        //�Q�j�O�ݓ��o���`�[�L���[Params�Ƀv���p�e�B���Z�b�g����
        //�ڍׂ́ADB�X�V�d�l�u�O�ݓ��o���`�[�L���[�e�[�u��.xls�v�Q��
        //�f�[�^�R�[�h:  ���������FGI804
        l_params.setRequestCode(WEB3HostRequestCodeDef.FOREIGN_CASH_TRANSFER_ORDER);
        // ���o�������P��.�����o�H���h�����A�g�h�̏ꍇ
        AioOrderUnitRow l_aioOrderUnitRow =
            (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (WEB3OrderRootDivDef.CASH_IN_COOPERATION.equals(
            l_aioOrderUnitRow.getOrderRootDiv()))
        {
            try
            {
                AccountManager l_accMgr = (AccountManager)GtlUtils.getAccountManager();
                MainAccount l_mainAccount = l_accMgr.getMainAccount(l_orderUnit.getAccountId());
                String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
                String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
                String l_strAccountCode = l_mainAccount.getAccountCode();

                //�،���ЃR�[�h:�����P��.���X�h�c�ɊY�����镔�X.�،���ЃR�[�h
                l_params.setInstitutionCode(l_strInstitutionCode);

                //���X�R�[�h:   �����P��.���X�h�c�ɊY�����镔�X.���X�R�[�h
                l_params.setBranchCode(l_strBranchCode);

                //�ڋq�R�[�h:   �����P��.�ڋq�h�c�ɊY������ڋq.�ڋq�R�[�h
                l_params.setAccountCode(l_strAccountCode);

                //���҃R�[�h:   �u�����N
                l_params.setTraderCode("     ");

                l_params.setOrderRequestNumber(l_aioOrderUnitRow.getOrderRequestNumber());

                // �ʉ݃R�[�h �����P��.�ʉ݃R�[�h
                l_params.setCurrencyCode(l_aioOrderUnitRow.getCurrencyCode());

                // ���o���敪:  2�F����
                l_params.setOrderDiv(WEB3OrderDivDef.CASHIN);

                //�o���z�i�O�݁j:       0
                l_params.setDebitAmount(0);

                //�����z�i�O�݁j:       �����P��.����
                l_params.setCreditAmount(l_aioOrderUnitRow.getQuantity());

                // �E�v�R�[�h:   01�F��s����
                l_params.setRemarkCode(WEB3ForeignCashRemarkCodeDef.BANK_REMITTANCE);

                // �o���z�i�~���Z�z�j:   0
                l_params.setDebitConvertAmount(0);

                // �����z(�~���Z�z�j:  �����P��.���o�����z(�~���Z�z�j
                l_params.setCreditConvertAmount(new Double(l_aioOrderUnitRow.getConvertAmount()).longValue());

                //�E�v���i�J�i) : �u�����N
                l_params.setRemarkName("                       ");

                //���o����:  �����P��.�U�֗\���
                String l_strCashTransDate =
                    WEB3DateUtility.formatDate(l_aioOrderUnitRow.getEstTransferDate(),"MMdd");
                l_params.setCashTransDate(l_strCashTransDate);

                //�����敪  :    �u�����N
                l_params.setCreditDiv(" ");

                //�����敪  :    �u�����N
                l_params.setForceDiv(" ");

                //����敪  :    �u�����N
                l_params.setCancelDiv(" ");

                // �P�j���Z�@@�փe�[�u��PK���쐬
                //�i�R���X�g���N�^�����F�،���ЃR�[�h=�،����.�،���ЃR�[�h,
                // ���Z�@@�փR�[�h = ���o�������P��.�U�֋L�q�j
                FinInstitutionPK l_finInstitutionPK = new FinInstitutionPK(
                    l_strInstitutionCode, l_orderUnit.getDescription());
                //���Z�@@�փe�[�u��Dao��PK����
                FinInstitutionRow l_finInsRow = FinInstitutionDao.findRowByPk(l_finInstitutionPK);

                // ���o�����@@ = ���Z�@@�փe�[�u��.���o���`�[�L���[�p���o�����@@
                l_params.setCashTransferType(l_finInsRow.getCashTransferType());

                // ����R�[�h = ���Z�@@�փe�[�u��.���o���`�[�L���[�p����R�[�h
                l_params.setSonarCode(l_finInsRow.getCashTransferSonarCode());

                // �בփ��[�g :    �u�����N
                l_params.setRate("         ");

                //�󒍓���  : ThreadLocalSystemAttributesRegistry.getAttribute
                //(�hxblocks.gtl.attributes.systemtimestamp�h)�̖߂�l
                l_params.setOrderedTimestamp(GtlUtils.getSystemTimestamp());

                //�����敪:     0�F������
                l_params.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

                //�R�j�O�ݓ��o���`�[�L���[Params�̓��e��DB�ɓo�^����B
                //WEB3DataAccessUtility.insertRow()
                //[����]
                //�O�ݓ��o���`�[�L���[Params
                WEB3DataAccessUtility.insertRow(l_params);
            }
            catch (NotFoundException l_ex)
            {
                log.error("Error In  getMainAccount()", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataFindException l_ex)
            {
                log.error("Error In  ���Z�@@�փe�[�u��Dao��PK����", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("Error In �O�ݓ��o���`�[�L���[Params�̓��e��DB�ɓo�^����", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex
                );
            }
            catch (DataNetworkException l_ex)
            {
                log.error("Error In �O�ݓ��o���`�[�L���[Params�̓��e��DB�ɓo�^����", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex
                );
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�O�ݏo�������L���[�f�[�^)<BR>
     * �O�ݏo�������̃L���[�f�[�^�̐����iDB�o�^�j���s���B <BR>
     * <BR>
     * �P�j�O�ݓ��o���`�[�L���[Params�C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j���Z�@@�փe�[�u�����R�[�h�̎擾  <BR>
     * ���Z�@@�փe�[�u�����ȉ��̏����Ō����B  <BR>
     * [����]  <BR>
     * �،���ЃR�[�h = ����.�����P��.���XID�ɊY������،���ЃR�[�h <BR>
     * ���Z�@@�փR�[�h = ALL�f0�f <BR>
     * <BR>
     * <BR>
     * �R�j�O�ݓ��o���`�[�L���[Params�Ƀv���p�e�B���Z�b�g����B <BR>
     * <BR>
     * ���ڍׂ͍X�V�d�l�u�O�ݏo���\��_�O�ݓ��o���`�[�L���[�e�[�u��.xls�v�Q��  <BR>
     * <BR>
     * �S�j�O�ݓ��o���`�[�L���[Params�̓��e��DB�ɓo�^����B <BR>
     * <BR>
     *   WEB3DataAccessUtility.insertRow() <BR>
     * <BR>
     *   [����] <BR>
     *   �O�ݓ��o���`�[�L���[Params<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    protected void createHostForeignCashoutOrderData(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createHostForeignCashoutOrderData"
            + "(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        //�P�j�O�ݓ��o���`�[�L���[Params�C���X�^���X�𐶐�����B
        HostForeignCashTransOrderParams l_params = new HostForeignCashTransOrderParams();

        //3�j�O�ݓ��o���`�[�L���[Params�Ƀv���p�e�B���Z�b�g����B
        //�f�[�^�R�[�h:  ���������FGI804
        l_params.setRequestCode(WEB3HostRequestCodeDef.FOREIGN_CASH_TRANSFER_ORDER);

        try
        {
            AccountManager l_accMgr = (AccountManager)GtlUtils.getAccountManager();
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_orderUnit.getAccountId());
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            String l_strAccountCode = l_mainAccount.getAccountCode();

            // �Q�j���Z�@@�փe�[�u�����R�[�h�̎擾
            // ���Z�@@�փe�[�u�����ȉ��̏����Ō����B
            // [����]
            // �،���ЃR�[�h = ����.�����P��.���XID�ɊY������،���ЃR�[�h
            // ���Z�@@�փR�[�h = ALL�f0'
            //���Z�@@�փe�[�u��Dao��PK����
            String l_strFinInstitutionCode = "000000000000000";
            FinInstitutionRow l_finInsRow =
                FinInstitutionDao.findRowByPk(l_strInstitutionCode, l_strFinInstitutionCode);

            //�،���ЃR�[�h:�����P��.���X�h�c�ɊY�����镔�X.�،���ЃR�[�h
            l_params.setInstitutionCode(l_strInstitutionCode);

            //���X�R�[�h:   �����P��.���X�h�c�ɊY�����镔�X.���X�R�[�h
            l_params.setBranchCode(l_strBranchCode);

            //�ڋq�R�[�h:   �����P��.�ڋq�h�c�ɊY������ڋq.�ڋq�R�[�h
            l_params.setAccountCode(l_strAccountCode);

            //���҃R�[�h:   �u�����N
            l_params.setTraderCode("     ");

            //���ʃR�[�h:   �����P��.���ʃR�[�h
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
            l_params.setOrderRequestNumber(l_aioOrderUnitRow.getOrderRequestNumber());

            // �ʉ݃R�[�h �����P��.�ʉ݃R�[�h
            l_params.setCurrencyCode(l_aioOrderUnitRow.getCurrencyCode());
            
            // ���o���敪 1�F�o��
            l_params.setOrderDiv(WEB3OrderDivDef.CASHOUT);

            //�o���z�i�O�݁j:      �����P��.��������
            l_params.setDebitAmount(l_aioOrderUnitRow.getQuantity());

            //�����z�i�O�݁j:       0
            l_params.setCreditAmount(0);

            // �E�v�R�[�h:   01:��s����
            l_params.setRemarkCode(WEB3ForeignCashRemarkCodeDef.BANK_REMITTANCE);

            // �o���z�i�~���Z�z�j:   �����P��.���o�����z(�~���Z�z�j
            l_params.setDebitConvertAmount(new Double(l_aioOrderUnitRow.getConvertAmount()).longValue());

            // �����z(�~���Z�z�j:  0
            l_params.setCreditConvertAmount(0);

            //�E�v���i�J�i) : �u�����N
            l_params.setRemarkName("                       ");

            //���o����:  �����P��.�U�֗\���
            Timestamp l_tsEstTransferDate = l_aioOrderUnitRow.getEstTransferDate();
            l_params.setCashTransDate(WEB3DateUtility.formatDate(l_tsEstTransferDate, "MMdd"));

            //�����敪  :    �u�����N
            l_params.setCreditDiv(" ");

            //�����敪  :    �u�����N
            l_params.setForceDiv(" ");

            //����敪  :    �u�����N
            l_params.setCancelDiv(" ");

            //���o�����@@ cash_transfer_type  ���Z�@@�փe�[�u��.���o���`�[�L���[�p���o�����@@                                                                     
            l_params.setCashTransferType(l_finInsRow.getCashTransferType());

            //���� sonar_code ���Z�@@�փe�[�u��.���o���`�[�L���[�p�����A�g����R�[�h                                                                     
            l_params.setSonarCode(l_finInsRow.getCashTransferSonarCode());

            // �בփ��[�g :    �u�����N
            l_params.setRate("         ");

            //�󒍓���  : ���ݎ����i�V�X�e���^�C���X�^���v�j
            l_params.setOrderedTimestamp(GtlUtils.getSystemTimestamp());

            //�����敪:     0�F������
            l_params.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

            //4�j�O�ݓ��o���`�[�L���[Params�̓��e��DB�ɓo�^����B
            //WEB3DataAccessUtility.insertRow()
            //[����]
            //�O�ݓ��o���`�[�L���[Params
            WEB3DataAccessUtility.insertRow(l_params);
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMainAccount()", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("Error In  ���Z�@@�փe�[�u��Dao��PK����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In �O�ݓ��o���`�[�L���[Params�̓��e��DB�ɓo�^����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In �O�ݓ��o���`�[�L���[Params�̓��e��DB�ɓo�^����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * (updateMQ�X�e�[�^�X)<BR>    
     * �����P�ʃe�[�u����MQ�X�e�[�^�X��'1'�i���M�ς݁j�ɍX�V����B<BR>
     * <BR>
     * �P�j�����P�ʃe�[�u���̍X�V<BR> 
     * <BR>
     * �P�|�P�j�ȉ��̏����ŁA�����P�ʃe�[�u�����烌�R�[�h���擾����B<BR> 
     * <BR>
     * �@@[��������]<BR> 
     * �@@�����P��ID�F ����.�����P��ID<BR>
     * <BR>
     * �P�|�Q�jMQ�X�e�[�^�X��'1'���A�X�V���t�ɃV�X�e���^�C���X�^���v���Z�b�g����B<BR> 
     * <BR>
     * �P�|�R�j�Y�����R�[�h���X�V����B<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (�����P��ID)
     * @@throws WEB3BaseException
     */
    public void updateMQStatus(long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateMQStatus(long l_lngOrderUnitId)";
        log.entering(STR_METHOD_NAME);
        
        try{
            // �P�|�P�j�ȉ��̏����ŁA�����P�ʃe�[�u�����烌�R�[�h���擾����B 
            // [��������] 
            // �����P��ID�F ����.�����P��ID 
            AioOrderUnitRow l_orderUnitRow = 
                AioOrderUnitDao.findRowByPk(l_lngOrderUnitId);
            log.debug("�����P�ʃe�[�u��Row := " + l_orderUnitRow);
            
            AioOrderUnitParams l_orderUnitParams = new AioOrderUnitParams(l_orderUnitRow);
            //�P�|�Q�jMQ�X�e�[�^�X��'1'���A�X�V���t�ɃV�X�e���^�C���X�^���v���Z�b�g����B 
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //�P�|�R�j�Y�����R�[�h���X�V����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);
            log.debug("updated �����P�ʃe�[�u��Row := " + l_orderUnitParams);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException Error ", l_ex);
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException Error In", l_ex);
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

}@
