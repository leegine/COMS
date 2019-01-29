head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransForeignUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o���i�O�݁jUnitServiceImpl(WEB3AioSonarCashTransForeignUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 ���G�� (���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioSonarCashTransForeignUpdateInterceptor;
import webbroker3.aio.data.HostForeignCashTransferParams;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransForeignUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (SONAR���o���i�O�݁jUnitServiceImpl)<BR>
 * SONAR���o���i�O�݁jUnitService�����N���X<BR>
 *<BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignUnitServiceImpl 
    implements WEB3AioSonarCashTransForeignUnitService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransForeignUnitServiceImpl.class);  
    /**
     * (submit����)<BR>
     * SONAR����̒����̓o�^���s���A�V�K�����̒���ID��ԋp����B<BR> 
     *<BR>
     * �V�[�P���X�} <BR>
     *�u�iSONAR���o���i�O�݁j�jsubmit�����v �Q��<BR>
     * @@param l_hostCashTransferForeignParams - (�O�ݓ��o��Params�I�u�W�F�N�g)<BR>
     * �O�ݓ��o��Params�I�u�W�F�N�g<BR>
     * @@return long<BR>
     * @@throws WEB3BaseException
     */
    public long submitOrder(HostForeignCashTransferParams l_hostForeignCashTransferParams)
        throws  WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(HostForeignCashTransferParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_hostForeignCashTransferParams == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //1.1 get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �ڋq�R�[�h : String)
            //�ڋq�I�u�W�F�N�g���擾����B
            //[����]
            //�،���ЃR�[�h�F ����.�O�ݓ��o��Params.�،���ЃR�[�h
            //���X�R�[�h�F ����.�O�ݓ��o��Params.���X�R�[�h
            //�ڋq�R�[�h�F ����.�O�ݓ��o��Params.�ڋq�R�[�h
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            WEB3GentradeMainAccount l_mainAccount =
                l_accountManager.getMainAccount(
                    l_hostForeignCashTransferParams.getInstitutionCode(),
                    l_hostForeignCashTransferParams.getBranchCode(),
                    l_hostForeignCashTransferParams.getAccountCode());
            
            //1.2 getInstitution( )
            //�،���ЃI�u�W�F�N�g���擾����B
            String l_strInstitutionCode = 
                l_hostForeignCashTransferParams.getInstitutionCode();
            Institution l_institution = 
                l_accountManager.getInstitution(l_strInstitutionCode);
            
            //1.3 getTrader(�،���� : Institution, ���҃R�[�h : String, ���X�R�[�h : String)
            //���҃I�u�W�F�N�g���擾����B
            //[����]
            //�،���ЁF �،���ЃI�u�W�F�N�g
            //���҃R�[�h�F ����.�O�ݓ��o��Params.���҃R�[�h
            //���X�R�[�h�F ����.�O�ݓ��o��Params.���X�R�[�h
            String l_strTraderCode = l_hostForeignCashTransferParams.getTraderCode();
            
            // ���X�R�[�h�F ����.�U�֓��͒ʒm�L���[Params.���X�R�[�h
            Trader l_trader = null;
            if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strTraderCode))
            {
                FinObjectManager l_finObjectManager = GtlUtils.getFinObjectManager();
                l_trader = l_finObjectManager.getTrader(
                    l_institution,
                    l_strTraderCode,
                    l_hostForeignCashTransferParams.getBranchCode());
            }
            
            log.debug("���҃I�u�W�F�N�g" + l_trader);
            //1.4 get���iID(�،���� : Institution)
            //���o���p�̏��iID���擾����B
            //[����]
            //�،���ЁF �،���ЃI�u�W�F�N�g
            TradingModule l_TradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.AIO);
            WEB3AioOrderManager l_orderManager = 
                (WEB3AioOrderManager)l_TradingModule.getOrderManager();
            long l_lngProductId = l_orderManager.getProductId(l_institution);
            
            //1.5 ���o���������e(���� : Trader, ������� : OrderTypeEnum, 
            //�U�փ^�C�v : AssetTransferTypeEnum, ���iID : long, ���z : double, 
            //�L�q : String, �U�֗\��� : Date, ���ϋ@@��ID : String, ����ID : Long)
            //���o���������e�C���X�^���X�𐶐�����B
            //[����]
            //���ҁF ���҃I�u�W�F�N�g
            //������ʁF �i�ȉ��̂Ƃ���j
            //   ����.�O�ݓ��o��Params.���o���敪="1"�i�o���j�̏ꍇ�A1001�i�o�������j���Z�b�g����B
            //   ����.�O�ݓ��o��Params.���o���敪="2"�i�����j�̏ꍇ�A1002�i���������j���Z�b�g����B
            OrderTypeEnum l_orderType = null;
            if (WEB3OrderDivDef.CASHOUT.equals(l_hostForeignCashTransferParams.getOrderDiv()))
            {
                log.debug("����.�O�ݓ��o��Params.���o���敪=1�i�o���j�̏ꍇ" + l_orderType);
                l_orderType = OrderTypeEnum.CASH_OUT;
            }
            else if (WEB3OrderDivDef.CASHIN.equals(l_hostForeignCashTransferParams.getOrderDiv()))
            {
                log.debug("����.�O�ݓ��o��Params.���o���敪 = 2�i�����j�̏ꍇ" + l_orderType);
                l_orderType = OrderTypeEnum.CASH_IN;
            }
            
            log.debug("������ʁF" + l_orderType);
            //�U�փ^�C�v�F �i�ȉ��̂Ƃ���j
            //   ����.�O�ݓ��o��Params.���o���敪="1"�i�o���j�̏ꍇ�A2�i�o���j���Z�b�g����B
            //   ����.�O�ݓ��o��Params.���o���敪="2"�i�����j�̏ꍇ�A1�i�����j���Z�b�g����B
            AssetTransferTypeEnum l_assetTransferType = null;
            if (WEB3OrderDivDef.CASHOUT.equals(l_hostForeignCashTransferParams.getOrderDiv()))
            {
                log.debug("����.�O�ݓ��o��Params.���o���敪=1�i�o���j�̏ꍇ" + l_assetTransferType);
                l_assetTransferType = AssetTransferTypeEnum.CASH_OUT;
            }
            else if (WEB3OrderDivDef.CASHIN.equals(l_hostForeignCashTransferParams.getOrderDiv()))
            {
                log.debug("����.�O�ݓ��o��Params.���o���敪=2�i�����j�̏ꍇ" + l_assetTransferType);
                l_assetTransferType = AssetTransferTypeEnum.CASH_IN;
            }
            
            log.debug("�U�փ^�C�v�F" + l_assetTransferType);
            //���iID�F get���iID()�̖߂�l
            //���z�F ����.�O�ݓ��o��Params.�U�֓��o���z
            //�L�q�F null
            //�U�֗\����F ����.�O�ݓ��o��Params.���o����
            //���ϋ@@��ID�F null
            //����ID�F null
            WEB3AioNewOrderSpec l_aioNewOrderSpec = new WEB3AioNewOrderSpec(
                l_trader,
                l_orderType,
                l_assetTransferType,
                l_lngProductId,
                l_hostForeignCashTransferParams.getAmount(),
                null,
                l_hostForeignCashTransferParams.getCashTransDate(),
                null,
                null); 

            //1.6 getSubAccount(�⏕�����^�C�v : SubAccountTypeEnum)
            //�⏕�����I�u�W�F�N�g���擾����B
            //[����]
            //�⏕�����^�C�v�F 1�i�a��������j
            //================NotFoundException====================  
            SubAccount l_subAccount = 
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            //1.7 createNewOrderId( )
            //�V�K����ID���擾����B
            long l_lngNewOrderId = l_orderManager.createNewOrderId();
            
            //1.8 SONAR���o���i�O�݁j�X�V�C���^�Z�v�^(���o���������e : ���o���������e)
            //SONAR���o���i�O�݁j�X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B
            //[����]
            //���o���������e�F ���o���������e�I�u�W�F�N�g
            WEB3AioSonarCashTransForeignUpdateInterceptor 
            l_aioSonarCashTransForeignUpdateInterceptor = 
                new WEB3AioSonarCashTransForeignUpdateInterceptor(
                    l_aioNewOrderSpec);
            
            //1.9 (*) �v���p�e�B�Z�b�g
            //(*) �ȉ��̂Ƃ���ɃC���^�Z�v�^�̃v���p�e�B���Z�b�g����B
            //�C���^�Z�v�^.��n�� = ���o���������e.�U�֗\���
            l_aioSonarCashTransForeignUpdateInterceptor.setDeliveryDate(
                l_aioNewOrderSpec.getEstimatedTransferDate());
            
            //�C���^�Z�v�^.�󒍓��� = ����.�O�ݓ��o��Params.�^�C���X�^���v
            l_aioSonarCashTransForeignUpdateInterceptor.setReceivedDateTime(
                WEB3DateUtility.getDate(
                    l_hostForeignCashTransferParams.getCreatedTimestamp(), "yyyyMMddHHmmss")); 
                    
            //�C���^�Z�v�^.�ʉ݃R�[�h = ����.�O�ݓ��o��Params.�ʉ݃R�[�h
            l_aioSonarCashTransForeignUpdateInterceptor.setCurrencyCode(
                l_hostForeignCashTransferParams.getCurrencyCode());
            
            //�C���^�Z�v�^.���o�����z(�~���Z�z) = ����.�O�ݓ��o��Params.�U�֓��o���~���Z�z
            l_aioSonarCashTransForeignUpdateInterceptor.setConvertAmount(
                l_hostForeignCashTransferParams.getConvertAmount());
            
            //1.10 setThreadLocalPersistenceEventInterceptor(
            //SONAR���o���i�O�݁j�X�V�C���^�Z�v�^ : AioOrderManagerPersistenceEventInterceptor)
            //�C���^�Z�v�^���Z�b�g����B
            //[����]
            //SONAR���o���i�O�݁j�X�V�C���^�Z�v�^�F SONAR���o���i�O�݁j�X�V�C���^�Z�v�^�I�u�W�F�N�g
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_aioSonarCashTransForeignUpdateInterceptor);
            
            //1.11 submitNewOrder(�⏕���� : SubAccount, ���i�^�C�v : ProductTypeEnum, 
            //�������e : NewOrderSpec, ����ID : long, �p�X���[�h : String, isSkip�����R�� : boolean)
            //�����o�^�������s���B
            //[����]
            //�⏕�����F �⏕�����I�u�W�F�N�g
            //���i�^�C�v�F 5�i�����j
            //�������e�F ���o���������e�I�u�W�F�N�g
            //����ID�F createNewOrderId()�̖߂�l
            //�p�X���[�h�F �ڋq.getTradingPassword()�̖߂�l��WEB3Crypt.decrypt()�ŕ�����������
            //isSkip�����R���F true
            WEB3Crypt l_webCrypt = new WEB3Crypt();
            OrderSubmissionResult l_submitNewOrderResult =
                l_orderManager.submitNewOrder(
                    l_subAccount,
                    ProductTypeEnum.CASH,
                    l_aioNewOrderSpec,
                    l_lngNewOrderId,
                    l_webCrypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);
                    
            if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
            {
                log.debug("�����o�^�������s�� Error" +
                    l_submitNewOrderResult.getProcessingResult().getErrorInfo());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                    STR_METHOD_NAME);
            } 
            //1.12 return
            //����ID��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_lngNewOrderId;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�������擾����: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
    }
}
@
