head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioForeignCashTransOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�ݓ��o�������X�V�C���^�Z�v�^(WEB3AioForeignCashTransOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 ���G�� (���u) �V�K�쐬
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�ݓ��o�������X�V�C���^�Z�v�^)<BR>
 * �O�ݓ��o�������X�V�C���^�Z�v�^�N���X<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioForeignCashTransOrderUpdateInterceptor
    extends WEB3AioCashTransOrderUpdateInterceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioForeignCashTransOrderUpdateInterceptor.class);

    /**
     *�ʉ݃R�[�h
     */
    private String currencyCode;

    /**
     * ���o�����z(�~���Z�z)
     */
    private Double convertAmount;

    /**
     * (�O�ݓ��o�������X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * �C���X�^���X�𐶐����A�����̓��o���������e���v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_cashTransOrderSpec  - (���o���������e�I�u�W�F�N�g)
     */
    public WEB3AioForeignCashTransOrderUpdateInterceptor(
        WEB3AioNewOrderSpec l_cashTransOrderSpec)
    {
        super(l_cashTransOrderSpec);
    }

    /**
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     * �u���o���A�g_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR>
     * <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE <BR>
     * <BR>
     *�iOrderManagerPersistenceType�ɂĒ萔��`�B�j<BR>
     * @@param l_process - ����<BR>
     *�iOrderManagerPersistenceContext�ɂĒ萔��`<BR>
     * @@param l_aioOrderUnitParams - (�����P��Params)<BR>
     * �����P�ʂ̃p�����[�^�N���X<BR>
     * @@return AioOrderUnitParams
     */
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process,
        AioOrderUnitParams l_aioOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, AioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_aioOrderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            // �y��Trade�z�⑫����.DB�X�V
            // �u���o���A�g_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB
            // 8 �����J�e�S�� = 9�F���o���iOrderCategEnum.CASH_IN_OUT�j
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);

            // 15 ������ = �C���^�Z�v�^.������
            l_aioOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.bizDate, "yyyyMMdd"));

            // 16 ��n�� = �C���^�Z�v�^.��n��
            l_aioOrderUnitParams.setDeliveryDate(this.deliveryDate);

            // 18 �ŋ敪 = �f�t�H���g�F0
            l_aioOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);

            // 19 �~�j���敪 = �f�t�H���g�F0
            l_aioOrderUnitParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

            // 20 �󒍓��� = ThreadLocalSystemAttributesRegistry.getAttribute
            //    (�hxblocks.gtl.attributes.systemtimestamp�h)�̖߂�l
            l_aioOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());

            // 21 ���҃R�[�h�iSONAR�j= �ڋq.���҃R�[�h
            long l_lngAccountId = l_aioOrderUnitParams.getAccountId();

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            AccountManager l_accountManager = l_finApp.getAccountManager();

            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);

            if (l_mainAccount == null)
            {
                log.debug("�Y������ڋq�I�u�W�F�N�g������܂���");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
            l_aioOrderUnitParams.setSonarTraderCode(l_strSonarTraderCode);

            // 22 ���ʃR�[�h = �C���^�Z�v�^.���ʃR�[�h
            l_aioOrderUnitParams.setOrderRequestNumber(this.orderRequestNumber);

            // 23 .com�f�r�b�g���ώ���ԍ� = null�i�C���^�Z�v�^..com�f�r�b�g���ώ���ԍ��j
            l_aioOrderUnitParams.setComDebitNumber(null);

            // 24 �ۏ؋��敪 =  null�i�C���^�Z�v�^.�ۏ؋��敪�j
            l_aioOrderUnitParams.setGuaranteeDiv(null);

            // 25 �o���\���敪 = null�i�C���^�Z�v�^.�o���\���敪�j
            l_aioOrderUnitParams.setPaymentApplicationDiv(null);

            // 26 ��������敪 = 0:�����l
            l_aioOrderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);

            // 27 �����o�H�敪 = A�F�Ǘ���
            l_aioOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.ADMIN);

            // 28 �����G���[���R�R�[�h = 0000�F����
            l_aioOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

            // 29 MQ�X�e�[�^�X  = 0:�����M
            l_aioOrderUnitParams.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);

            // 36 �ʉ݃R�[�h = �C���^�Z�v�^.�ʉ݃R�[�h
            l_aioOrderUnitParams.setCurrencyCode(this.currencyCode);

            // 37 ���o�����z(�~���Z�z) = �C���^�Z�v�^.���o�����z(�~���Z�z)
            l_aioOrderUnitParams.setConvertAmount(this.convertAmount);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�Y������ڋq�I�u�W�F�N�g������܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

      log.exiting(STR_METHOD_NAME);
      return l_aioOrderUnitParams;
    }

    /**
     * (set�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h���Z�b�g����B<BR>
     * <BR>
     * @@param l_strCurrencyCode - �ʉ݃R�[�h
     */
    public void setCurrencyCode(String l_strCurrencyCode)
    {
        this.currencyCode = l_strCurrencyCode;
    }

    /**
     * (set���o�����z(�~���Z�z))<BR>
     * ���o�����z(�~���Z�z)���Z�b�g����B<BR>
     * <BR>
     * @@param l_convertAmount - ���o�����z(�~���Z�z)
     */
    public void setConvertAmount(Double l_convertAmount)
    {
        this.convertAmount = l_convertAmount;
    }
}
@
