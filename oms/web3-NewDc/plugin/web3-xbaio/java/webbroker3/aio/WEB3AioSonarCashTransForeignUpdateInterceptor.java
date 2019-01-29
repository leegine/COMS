head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSonarCashTransForeignUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o���i�O�݁j�X�V�C���^�Z�v�^(WEB3AioSonarCashTransForeignUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 �Ԑi (���u) �V�K�쐬              
*/

package webbroker3.aio;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;


/**
 * (SONAR���o���i�O�݁j�X�V�C���^�Z�v�^)<BR>
 * SONAR���o���i�O�݁j�X�V�C���^�Z�v�^�N���X<BR>
 * 
 * @@author �Ԑi(���u)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignUpdateInterceptor extends 
    WEB3AioSonarCashTransUpdateInterceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSonarCashTransForeignUpdateInterceptor.class); 
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    private String currencyCode;
    
    /**
     * (���o�����z(�~���Z�z))<BR>
     * ���o�����z(�~���Z�z)<BR>
     */
    private double convertAmount;
    
    /**
     * (SONAR���o���i�O�݁j�X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�����̓��o���������e���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_cashTransOrderSpec - (���o���������e)<BR>
     * ���o���������e�I�u�W�F�N�g<BR>
     * @@return WEB3AioSonarCashTransForeignUpdateInterceptor
     * @@roseuid 44D85B3402C1
     */
    public WEB3AioSonarCashTransForeignUpdateInterceptor(WEB3AioNewOrderSpec l_cashTransOrderSpec) 
    {
        super(l_cashTransOrderSpec);
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB <BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uSONAR���o���i�O�݁j_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE<BR>
     * <BR>
     * �iOrderManagerPersistenceType�ɂĒ萔��`�B�j<BR>
     * @@param l_process - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * �����P�ʂ̃p�����[�^�N���X<BR>
     * @@return AioOrderUnitParams
     * @@roseuid 44D85BA80315
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, " +
            "OrderManagerPersistenceContext, " +
            "AioOrderUnitParams)";
        
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            // 1)�����J�e�S��
            l_orderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);
            
            // 2)��n��
            l_orderUnitParams.setDeliveryDate(this.deliveryDate);
            
            // 3)�ŋ敪
            l_orderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            
            // 4)�~�j���敪
            l_orderUnitParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
            
            // 5)�󒍓���
            l_orderUnitParams.setReceivedDateTime(this.receivedDateTime);
            
            // 6)���҃R�[�h(SONAR)�̐ݒ���s���B
            long l_lngAccountId = l_orderUnitParams.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();   
            MainAccount l_acc = l_accountManager.getMainAccount(l_lngAccountId);
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_acc.getDataSourceObject();
            String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
            l_orderUnitParams.setSonarTraderCode(l_strSonarTraderCode);
            
            // 7)���ʃR�[�h�̐ݒ���s���B
            l_orderUnitParams.setOrderRequestNumber(null);
            
            // 8).com�f�r�b�g���ώ���ԍ��̐ݒ���s���B
            l_orderUnitParams.setComDebitNumber(null);
            
            // 9)�ۏ؋��敪�̐ݒ���s���B
            l_orderUnitParams.setGuaranteeDiv(null);
            
            // 10)�o���\���敪�̐ݒ���s���B
            l_orderUnitParams.setPaymentApplicationDiv(null);
            
            // 11)��������敪�̐ݒ���s���B
            l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            
            // 12)�����o�H�敪�̐ݒ���s���B
            l_orderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
            
            // 13)�����G���[���R�R�[�h�̐ݒ���s���B
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            // 14)MQ�X�e�[�^�X�̐ݒ���s���B
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            
            // 15)�ʉ݃R�[�h�̐ݒ���s���B
            l_orderUnitParams.setCurrencyCode(this.currencyCode);
            
            //16)���o�����z(�~���Z�z)�̐ݒ���s���B
            l_orderUnitParams.setConvertAmount(this.convertAmount);
        }
        catch(NotFoundException l_ex)
        {
            log.error("�Y������ڋq�I�u�W�F�N�g������܂���", l_ex); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
           
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (set�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h���Z�b�g����B<BR>
     * @@param l_strCurrencyCode - (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     * @@roseuid 44D85EEF0347
     */
    public void setCurrencyCode(String l_strCurrencyCode) 
    {
        final String STR_METHOD_NAME = "setCurrencyCode(String)";
        log.entering(STR_METHOD_NAME);
        
        this.currencyCode = l_strCurrencyCode;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���o�����z(�~���Z�z))<BR>
     * ���o�����z(�~���Z�z)���Z�b�g����B<BR>
     * @@param l_dblConvertAmount - (���o�����z(�~���Z�z))<BR>
     * ���o�����z(�~���Z�z)<BR>
     * @@roseuid 44E45E4C0219
     */
    public void setConvertAmount(double l_dblConvertAmount) 
    {
        final String STR_METHOD_NAME = "setConvertAmount(double)";
        log.entering(STR_METHOD_NAME);
        
        this.convertAmount = l_dblConvertAmount;
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
