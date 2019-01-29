head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFinanceAmountRepayUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Z���z�ԍ�UnitServiceImpl(WEB3AioFinanceAmountRepayUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ������ (���u) �V�K�쐬       
                   2006/10/23 ����� (���u) �d�l�ύX���f��670
                   2006/11/06 ����� (���u) �d�l�ύX���f��683
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioSecurityLoanUpdateInterceptor;
import webbroker3.aio.data.PayRequiredAmountParams;
import webbroker3.aio.define.WEB3AioUpdaterCodeDivDef;
import webbroker3.aio.service.delegate.WEB3AioFinanceAmountRepayUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3PayRequiredAmountStatusDef;
import webbroker3.common.define.WEB3PaymentStopDivDef;
import webbroker3.common.define.WEB3SecuredLoanSecAccOpenDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.SecurityShortageAccountPK;
import webbroker3.gentrade.data.SecurityShortageAccountParams;
import webbroker3.gentrade.data.SecurityShortageAccountRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
/**
 * (�Z���z�ԍ�UnitServiceImpl)<BR>
 * �Z���z�ԍ�UnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AioFinanceAmountRepayUnitServiceImpl implements WEB3AioFinanceAmountRepayUnitService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioFinanceAmountRepayUnitServiceImpl.class);
    
    /**
     * @@roseuid 4510F52E0148
     */
    public WEB3AioFinanceAmountRepayUnitServiceImpl() 
    {
     
    }
    
    /**
     * �ԍϕK�v�z�f�[�^�X�V�������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Z���z�ԍρj�ԍϕK�v�z�f�[�^�X�V�v�Q�ƁB<BR>
     * @@param l_payRequiredAmountParams - (�ԍϕK�v�z�f�[�^Params)<BR>
     * �ԍϕK�v�z�f�[�^�̍s�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩������<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 4506A0E70047
     */
    public void execute(PayRequiredAmountParams l_payRequiredAmountParams) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(PayRequiredAmountParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_payRequiredAmountParams == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            //1.1�@@���������b�N����B 
            //�@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B 
            //�������͕ԍϕK�v�z�f�[�^Params����擾�B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            //�،���ЃR�[�h���擾����
            String l_strInstitutionCode =
                l_payRequiredAmountParams.getInstitutionCode();

            //���X�R�[�h���擾����
            String l_strBranchCode = l_payRequiredAmountParams.getBranchCode();

            //�����R�[�h���擾����
            String l_strAccountCode = l_payRequiredAmountParams.getAccountCode();

            l_accountManager.lockAccount(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode);

            //1.2 get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
            //�ڋq�I�u�W�F�N�g���擾����B 
            //�m�����n 
            //�،���ЃR�[�h�F�@@�ԍϕK�v�z�f�[�^Params.�،���ЃR�[�h 
            //���X�R�[�h�F�@@�ԍϕK�v�z�f�[�^Params.���X�R�[�h 
            //�ڋq�R�[�h�F�@@�ԍϕK�v�z�f�[�^Params.�ڋq�R�[�h
            WEB3GentradeMainAccount l_gentradeMainAccount = l_accountManager.getMainAccount(
                l_payRequiredAmountParams.getInstitutionCode(),
                l_payRequiredAmountParams.getBranchCode(),
                l_payRequiredAmountParams.getAccountCode());
            
            //1.3 is�M�p�����J��(�ٍϋ敪 : String)
            //�M�p�����J�݋敪���擾����B 
            //[����] 
            //�ٍϋ敪�F�h�w��Ȃ��h
            boolean l_blnIsMarginAccountEstablished = 
                l_gentradeMainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
            
            //1.4 is�敨OP�����J��(�敨�^�I�v�V�����敪 : String)
            //�敨�����J�݋敪���擾����B 
            //[����] 
            //�敨/�I�v�V�����敪�F�h1�h(�敨) 
            //[�߂�l] 
            //�敨�����J�݋敪
            boolean l_blnFuturesAccountOpenDiv = 
                l_gentradeMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES);
            
            //1.5 is�敨OP�����J��(�敨�^�I�v�V�����敪 : String)
            //�I�v�V���������J�݋敪���擾����B 
            //[����] 
            //�敨/�I�v�V�����敪�F�h2�h(�I�v�V����) 
            //[�߂�l] 
            //�I�v�V���������J�݋敪
            boolean l_blnOptionAccountOpenDiv = 
                l_gentradeMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION);
            
            //1.6  get�ڋq�s( )
            //�ڋqParams���擾����B
            MainAccountParams l_mainAccountParams = 
                (MainAccountParams)l_gentradeMainAccount.getMainAccountRow();
            
            if (!l_payRequiredAmountParams.getPayRequiredAmountIsNull())
            {
                boolean l_blnFlag = true;
                //1.6 is�M�p�����J��() = false(�M�p�q�łȂ�) ���� �敨�����J�݋敪 = false 
                //���� �I�v�V���������J�݋敪 = false ���� �ڋqParams.�،��S�ۃ��[���敪�@@= 1(�J��)�̏ꍇ
                if (!(l_blnIsMarginAccountEstablished 
                    || l_blnFuturesAccountOpenDiv 
                    || l_blnOptionAccountOpenDiv)
                    && l_mainAccountParams.getSecuredLoanSecAccOpenDiv().equals(WEB3SecuredLoanSecAccOpenDivDef.OPEN))
                {
                    //1.7.1 get�⏕����(����ID : , �⏕�����^�C�v : )
                    //�⏕�����I�u�W�F�N�g���擾����B 
                    //[����] 
                    //����ID�F�@@�ԍϕK�v�z�f�[�^Params.����ID 
                    //�⏕�����^�C�v�F 1�i������������i�a����j�j
                    WEB3GentradeSubAccount l_subAccount = 
                        (WEB3GentradeSubAccount) l_accountManager.getSubAccount( 
                            l_payRequiredAmountParams.getAccountId(), SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
     
                    //1.7.2 get��؋��ւ̐U�։\�z(�⏕���� : �⏕����, ��n�� : Date)
                    //��؋��ւ̐U�։\�z���擾����B 
                    //[�����̐ݒ�] 
                    //�⏕�����F�@@get�⏕����()�̖߂�l 
                    //��n���F�@@�ԍϕK�v�z�f�[�^Params.�������̗��X�c�Ɠ�
                    WEB3TPTradingPowerService l_tradingPower = 
                        (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
                    Timestamp l_tsBaseTime = 
                        new Timestamp(
                            WEB3DateUtility.getDate(l_payRequiredAmountParams.getProcDate(), "yyyyMMdd").getTime());
                    WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsBaseTime);
                    Timestamp l_tsNextDate = l_genBizDate.roll(2);

                    double l_dblOsakaTransferableTradingPower = 
                        l_tradingPower.getOsakaTransferableTradingPower(
                            l_subAccount,
                            l_tsNextDate);

                    
                    //1.7.3  (*)�v���p�e�B�Z�b�g
                    //(*)�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
                    //�]�́F�@@get��؋��ւ̐U�։\�z()�̖߂�l
                    //�ԍϕK�v�z�F�@@�ԍϕK�v�z�f�[�^Params.�ԍϕK�v�z
                    double l_dblDecisionPayAmount = 0D;
                    
                    if (l_dblOsakaTransferableTradingPower <= l_payRequiredAmountParams.getPayRequiredAmount())
                    {
                        //�E(�]�́@@<= �ԍϕK�v�z)�̏ꍇ
                        //   �m��ԍϊz = �]��
                        log.debug("�]�́@@<= �ԍϕK�v�z�̏ꍇ");
                        l_dblDecisionPayAmount = l_dblOsakaTransferableTradingPower;

                    }
                    else if (l_dblOsakaTransferableTradingPower > l_payRequiredAmountParams.getPayRequiredAmount())
                    {
                        //�E(�]�́@@> �ԍϕK�v�z)�̏ꍇ
                        //�@@�m��ԍϊz = �ԍϕK�v�z
                        log.debug("(�]�́@@> �ԍϕK�v�z)�̏ꍇ");
                        l_dblDecisionPayAmount = l_payRequiredAmountParams.getPayRequiredAmount();
                    }
                    //3�j�m��ԍϊz��100�~�P�ʂ܂Ő؂�̂�
                    BigDecimal l_bdDecisionPayAmount= new BigDecimal(String.valueOf(l_dblDecisionPayAmount));
                    BigDecimal l_bdThousand = new BigDecimal("1000");
                    l_bdDecisionPayAmount = 
                        l_bdDecisionPayAmount.divide(
                            l_bdThousand, 0, BigDecimal.ROUND_DOWN).multiply(l_bdThousand);
                    l_dblDecisionPayAmount = l_bdDecisionPayAmount.doubleValue();
                    l_payRequiredAmountParams.setDecisionPayAmount(l_dblDecisionPayAmount);

                    //1.7.4 get���iID(�،���� : Institution)
                    //���iID�i����ID�j���擾����B 
                    //[����] 
                    //�،���ЁF �⏕����.get����X().getInstitution()�̖߂�l
                    TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
                    WEB3AioOrderManager l_orderManager = 
                        (WEB3AioOrderManager) l_tradingModule.getOrderManager();

                    long l_lngProductId = 
                        l_orderManager.getProductId(l_subAccount.getWeb3GenBranch().getInstitution());

                    //1.7.5 ���o���������e(�㗝���͎� : Trader, ������� : OrderTypeEnum, 
                    //�U�փ^�C�v : AssetTransferTypeEnum, ���iID : long, ���z : double, 
                    //�L�q : String, �U�֗\��� : Date, ���ϋ@@��ID : String, ����ID : Long)
                    //
                    //���o���������e�𐶐�����B 
                    //[�����̐ݒ�] 
                    //�㗝���͎ҁF�@@null 
                    //������ʁF 1019�i�U�֒���(�a��������؋�)�j 
                    //�U�փ^�C�v�F�@@2�i�o���j 
                    //���iID�F�@@get���iID()�̖߂�l 
                    //���z�F�@@�m��ԍϊz 
                    //�L�q�F�@@null 
                    //�U�֗\����F�@@�ԍϕK�v�z�f�[�^Params.�������̗��X�c�Ɠ� 
                    //���ϋ@@��ID�F�@@null 
                    //����ID�F�@@null
                    WEB3AioNewOrderSpec l_aioNewOrderSpec = new WEB3AioNewOrderSpec(
                        null,
                        OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK,
                        AssetTransferTypeEnum.CASH_OUT,
                        l_lngProductId,
                        l_dblDecisionPayAmount,
                        null,
                        l_tsNextDate,
                        null,
                        null); 
                    
                    //1.7.6 �،��S�ۃ��[���X�V�C���^�Z�v�^(���o���������e : ���o���������e)
                    //�R���X�g���N�^ 
                    //�C���X�^���X�𐶐����A�����̓��o���������e���v���p�e�B�ɃZ�b�g����B
                    WEB3AioSecurityLoanUpdateInterceptor l_securityLoanUpdateInterceptor =
                        new WEB3AioSecurityLoanUpdateInterceptor(l_aioNewOrderSpec);
                    
                    //1.7.7 (*)�v���p�e�B�Z�b�g
                    //(*)�ȉ��̂悤�Ƀv���p�e�B�l���Z�b�g����B
                    //������ = �ԍϕK�v�z�f�[�^Params.�������̗��c�Ɠ�
                    //��n�� = �ԍϕK�v�z�f�[�^Params.�������̗��X�c�Ɠ�
                    //�����o�H = "E"(�U�֒���(�a��������؋�))
                    l_securityLoanUpdateInterceptor.setOrderBizDate(l_genBizDate.roll(1));
                    l_securityLoanUpdateInterceptor.setDeliveryDate(l_tsNextDate);
                    l_securityLoanUpdateInterceptor.setOrderRootDiv(WEB3OrderRootDivDef.FROM_DEPOSIT_AMOUNT_DSK);
                    
                    //1.7.8 createNewOrderId( )
                    //�V�K����ID���̔Ԃ���B
                    long l_lngNewOrderId = l_orderManager.createNewOrderId();
                    
                    //1.7.9 setThreadLocalPersistenceEventInterceptor(arg0 : AioOrderManagerPersistenceEventInterceptor)
                    //�C���^�Z�v�^���Z�b�g����B  
                    //[����]  
                    //���o�������X�V�C���^�Z�v�^�F�@@�����������o�������X�V�C���^�Z�v�^
                    l_orderManager.setThreadLocalPersistenceEventInterceptor(l_securityLoanUpdateInterceptor);
                    
                    //1.7.10 submitNewOrder(arg0 : SubAccount, arg1 : ProductTypeEnum, arg2 : NewOrderSpec,
                    //arg3 : long, arg4 : String, arg5 : boolean)
                    //�����o�^�������s���B 
                    //[����] 
                    //�⏕�����F�@@�⏕���� 
                    //���i�^�C�v�F�@@5�i�����j 
                    //���o���������e�F�@@���o���������e()�̖߂�l 
                    //�����h�c�F�@@createNewOrderId()�̖߂�l 
                    //�p�X���[�h�F�@@�@@�ڋq.getTradingPassword()�̖߂�l��WEB3Crypt.decrvpt()�ŕ����������� 
                    //isSkip�����R���F�@@true 
                    WEB3Crypt l_web3Crypt = new WEB3Crypt();
                    OrderSubmissionResult l_submissionResult =
                        l_orderManager.submitNewOrder(
                            l_subAccount,
                            ProductTypeEnum.CASH,
                            l_aioNewOrderSpec,
                            l_lngNewOrderId,
                            l_web3Crypt.decrypt(l_gentradeMainAccount.getTradingPassword()),
                            true);
                    if (l_submissionResult.getProcessingResult().isFailedResult())
                    {
                        l_blnFlag = false;
                    }
                    
                    if (l_blnFlag)
                    {
                        //1.7.11 doFindByPrimaryKeyQuery(arg0 : PrimaryKey)
                        //�S�ەs���ڋq�f�[�^�e�[�u������A�ȉ��̏����̃��R�[�h���擾����B 
                        //[����] 
                        //����ID = �ԍϕK�v�z�f�[�^Params.����ID 
                        //[�߂�l] 
                        //�S�ەs���ڋq�f�[�^Row
                        boolean l_blnFindRow = true;
                        SecurityShortageAccountRow l_securityShortageAccountRow = null;
                        QueryProcessor l_queryProcessor = null;
                        try
                        {         
                            l_queryProcessor = Processors.getDefaultProcessor();
                            PrimaryKey l_primaryKey = new SecurityShortageAccountPK(
                                l_payRequiredAmountParams.getAccountId());

                            l_securityShortageAccountRow =
                                (SecurityShortageAccountRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_primaryKey);
                        }

                        catch (DataQueryException l_ex)
                        {
                            l_blnFindRow = false;
                            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                        }
                        catch (DataNetworkException l_ex)
                        {
                            l_blnFindRow = false;
                            log.debug("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
                        }

                        if (l_blnFindRow)
                        {
                            SecurityShortageAccountParams l_securityShortageAccountParams =
                                new SecurityShortageAccountParams(l_securityShortageAccountRow);

                            //1.7.12  �S�ەs���ڋq�f�[�^Params.������ = �ԍϕK�v�z�f�[�^Params.�������@@�����@@
                            //�S�ەs���ڋq�f�[�^Params.�o����~�敪 = ' 2 ' �̏ꍇ
                            if (l_securityShortageAccountParams.getProcDate().equals(
                                l_payRequiredAmountParams.getProcDate()) &&
                                WEB3PaymentStopDivDef.PART.equals(l_securityShortageAccountParams.getPaymentStopDiv()))
                            {
                                //1.7.12.1 
                                String l_strPaymentStopAmount = "0";
                                if (l_securityShortageAccountParams.getPaymentStopAmount() != null)
                                {
                                    l_strPaymentStopAmount = 
                                        l_securityShortageAccountParams.getPaymentStopAmount();
                                }
                                if (Double.parseDouble(l_strPaymentStopAmount) > 
                                    l_payRequiredAmountParams.getDecisionPayAmount())
                                {
                                    BigDecimal l_bdResult = new BigDecimal(
                                        l_strPaymentStopAmount).subtract(
                                            new BigDecimal(WEB3StringTypeUtility.formatNumber(
                                                l_payRequiredAmountParams.getDecisionPayAmount())));
                                    l_securityShortageAccountParams.setPaymentStopAmount(
                                        WEB3StringTypeUtility.formatNumber(l_bdResult.doubleValue()));
                                }
                                else
                                {
                                    l_securityShortageAccountParams.setPaymentStopAmount("0");
                                }
                                
                                //1.7.12.2 �v���p�e�B�Z�b�g
                                l_securityShortageAccountParams.setLastUpdater(WEB3AioUpdaterCodeDivDef.AIO_LOAN);
                                l_securityShortageAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                                //1.7.12.3 doUpdateQuery(arg0 : Row)
                                //�S�ەs���ڋq�f�[�^�e�[�u���̃��R�[�h���X�V����B 
                                //�m�����n 
                                //�S�ەs���ڋq�f�[�^Params
                                try
                                {
                                    l_queryProcessor.doUpdateQuery(l_securityShortageAccountParams);
                                }
                                catch (DataQueryException l_ex)
                                {
                                    log.debug("Error in DataQueryException... ", l_ex);
                                }
                                catch (DataNetworkException l_ex)
                                {
                                    log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                                }
                            }
                        }
                        
                        //1.7.13 �]�͍Čv�Z(�⏕���� : �⏕����)
                        //�]�͍X�V���s���B 
                        //[�����̐ݒ�] 
                        //�⏕�����F�@@�⏕����
                        WEB3TPTradingPowerReCalcService l_tradingPowerReCalcService =
                            (WEB3TPTradingPowerReCalcService)Services.getService(
                                WEB3TPTradingPowerReCalcService.class);

                        l_tradingPowerReCalcService.reCalcTradingPower(l_subAccount);

                        //1.7.14  (*)�v���p�e�B�Z�b�g
                        //(*)�ԍϕK�v�z�f�[�^Params�̈ȉ��̒l���Z�b�g����B
                        //��L����������I���̏ꍇ�F
                        //�@@�����敪 = 1(����I��)
                        l_payRequiredAmountParams.setStatus(WEB3PayRequiredAmountStatusDef.NORMAL_END);
                    }  
                }     
                if (l_blnFlag)
                {
                    //1.8 (*)is�M�p�����J��() = true(�M�p�q) �Ⴕ���� �敨�����J�݋敪 = true �Ⴕ���� �I�v�V���������J�݋敪 = true �̏ꍇ
                    if (l_blnIsMarginAccountEstablished
                        || l_blnFuturesAccountOpenDiv
                        || l_blnOptionAccountOpenDiv)
                    {
                        //1.8.1 (*)�v���p�e�B�Z�b�g
                        //(*)�ԍϕK�v�z�f�[�^Params�̈ȉ��̒l���X�V����B
                        //�����敪 = 2(�����ڋq�ȊO�G���[)
                        //�m��ԍϊz = null
                        l_payRequiredAmountParams.setStatus(WEB3PayRequiredAmountStatusDef.EQTYPE_COSTOMER_EXCEPT);
                        l_payRequiredAmountParams.setDecisionPayAmount(null);
                        
                    }
                    
                    //1.9  (*)��L�����𖞂����Ȃ��ꍇ�@@���@@�ڋqParams.�،��S�ۃ��[���敪 = 0(���J��)�̏ꍇ
                    else if (l_mainAccountParams.getSecuredLoanSecAccOpenDiv().equals(
                        WEB3SecuredLoanSecAccOpenDivDef.NOT_OPEN))
                    {
                        //1.9.1 (*)�v���p�e�B�Z�b�g
                        //(*)�ԍϕK�v�z�f�[�^Params�̈ȉ��̒l���X�V����B
                        //�����敪 = 3(�،��S�ۃ��[�����J��)
                        //�m��ԍϊz = null
                        l_payRequiredAmountParams.setStatus(
                            WEB3PayRequiredAmountStatusDef.SECURITIES_WARRANTY_LOAN_NOT_OPEN);
                        l_payRequiredAmountParams.setDecisionPayAmount(null);
                        
                    } 
                    
                    //1.10 (*)�v���p�e�B�Z�b�g
                    //(*)�ԍϕK�v�z�f�[�^Params�̈ȉ��̒l���X�V����B
                    //�X�V�҃R�[�h = "aio_loan"
                    //�X�V���� = ������
                    l_payRequiredAmountParams.setLastUpdater(WEB3AioUpdaterCodeDivDef.AIO_LOAN);
                    l_payRequiredAmountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp()); 
                }                      
                else
                {
                    //1.11  (*)�v���p�e�B�Z�b�g
                    //��L�����ŃG���[�����������ꍇ�ȉ��̃v���p�e�B�l���Z�b�g����B
                    //�����敪 = 9(�G���[)
                    //�m��ԍϊz = null
                    //�X�V�҃R�[�h = "aio_loan"
                    //�X�V���� = ������
                    log.debug("Error in submitNewOrder");
                    l_payRequiredAmountParams.setStatus(WEB3PayRequiredAmountStatusDef.ERROR);
                    l_payRequiredAmountParams.setDecisionPayAmount(null);
                    l_payRequiredAmountParams.setLastUpdater(WEB3AioUpdaterCodeDivDef.AIO_LOAN);
                    l_payRequiredAmountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp()); 
                }
            }
            else 
            {
                //1.12  (*)�v���p�e�B�Z�b�g
                //��L�����ŃG���[�����������ꍇ�ȉ��̃v���p�e�B�l���Z�b�g����B
                //�����敪 = 9(�G���[)
                //�m��ԍϊz = null
                //�X�V�҃R�[�h = "aio_loan"
                //�X�V���� = ������
                log.debug("�ԍϕK�v�z�f�[�^Params.�ԍϕK�v�z�̖߂�l==null�̏ꍇ");
                l_payRequiredAmountParams.setStatus(WEB3PayRequiredAmountStatusDef.ERROR);
                l_payRequiredAmountParams.setDecisionPayAmount(null);
                l_payRequiredAmountParams.setLastUpdater(WEB3AioUpdaterCodeDivDef.AIO_LOAN);
                l_payRequiredAmountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp()); 
            }

        }
        catch (NotFoundException l_ex)
        {
            //1.10  (*)�v���p�e�B�Z�b�g
            //��L�����ŃG���[�����������ꍇ�ȉ��̃v���p�e�B�l���Z�b�g����B
            //�����敪 = 9(�G���[)
            //�m��ԍϊz = null
            //�X�V�҃R�[�h = "aio_loan"
            //�X�V���� = ������
            log.debug("�e�[�u���ɊY������f�[�^������܂���B" + l_ex);
            l_payRequiredAmountParams.setStatus(WEB3PayRequiredAmountStatusDef.ERROR);
            l_payRequiredAmountParams.setDecisionPayAmount(null);
            l_payRequiredAmountParams.setLastUpdater(WEB3AioUpdaterCodeDivDef.AIO_LOAN);
            l_payRequiredAmountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp()); 
        }
        catch (WEB3BaseException l_ex)
        {
            //1.10  (*)�v���p�e�B�Z�b�g
            //��L�����ŃG���[�����������ꍇ�ȉ��̃v���p�e�B�l���Z�b�g����B
            //�����敪 = 9(�G���[)
            //�m��ԍϊz = null
            //�X�V�҃R�[�h = "aio_loan"
            //�X�V���� = ������
            log.debug("�G���[ in 'get�ڋq' �Ⴕ���� 'get���iID'" + l_ex);
            l_payRequiredAmountParams.setStatus(WEB3PayRequiredAmountStatusDef.ERROR);
            l_payRequiredAmountParams.setDecisionPayAmount(null);
            l_payRequiredAmountParams.setLastUpdater(WEB3AioUpdaterCodeDivDef.AIO_LOAN);
            l_payRequiredAmountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp()); 
        }
        
        //1.11 doUpdateQuery(arg0 : Row)
        //�ԍϕK�v�z�f�[�^�e�[�u���̃��R�[�h���X�V����B 
        //�m�����n 
        //�ԍϕK�v�z�f�[�^Params
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_payRequiredAmountParams);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        log.exiting(STR_METHOD_NAME);
    }
}
@
