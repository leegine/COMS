head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiNewOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�V�K�����X�V�C���^�Z�v�^(WEB3SrvRegiNewOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �A�C��(���u) �V�K�쐬
              001: 2005/01/05 ���� (���u) �Ή����́Fweb3-DB��-V3.1
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.aio.WEB3AioCashTransUpdateInterceptor;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�V�K�����X�V�C���^�Z�v�^)<BR>
 * �T�[�r�X���p�V�K�����X�V�C���^�Z�v�^�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3SrvRegiNewOrderUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor
{

    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3SrvRegiNewOrderUpdateInterceptor.class);

    /**
     * (��n��)<BR>
     */
    private Timestamp deliveryDate;

    /**
     * (�T�[�r�X�敪)<BR>
     */
    private String srvDiv;

    /**
     * (�����o�H�敪)<BR>
     */
    private String orderRootDiv;

    /**
     * (������)<BR>
     */
    private Timestamp orderBizDate;

    /**
     * (�T�[�r�X���p�V�K�����X�V�C���^�Z�v�^)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 4100EF2903C8
     */
    public WEB3SrvRegiNewOrderUpdateInterceptor()
    {

    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * �A�Z�b�g�U�֎�������P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB <BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�A�Z�b�g�U�֎�������P��Params��<BR>
     * �@@�g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     * �u�T�[�r�X���p�\��_�����P�ʃe�[�u��.xls�v<BR>
     * �u�T�[�r�X���p�Ǘ��ҁE�ڋq�A�b�v���[�h_�����P�ʃe�[�u��.xls�v<BR>
     * �u�T�[�r�X���p�Ǘ��ҁE�ڋq�o�^_�����P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * @@param l_persistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_persistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_aioOrderUnitParams - (�A�Z�b�g�U�֎�������P��Params)<BR>
     * �i�����O�̃A�Z�b�g�U�֎�������P��Params<BR>
     *
     *
     * @@return AioOrderUnitParams
     * @@roseuid 4100EF100270
     */
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        AioOrderUnitParams l_aioOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            " mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, AioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_aioOrderUnitParams == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            //get account info.
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            long l_lngAccountId = l_aioOrderUnitParams.getAccountId();
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            MainAccount l_account = null;
            l_account = l_accountMgr.getMainAccount(l_lngAccountId);//NotFoundException

            //�ŋ敪
            l_aioOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);

            //�󒍓���
            Object l_receivedDateTime =
                ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            l_aioOrderUnitParams.setReceivedDateTime((Timestamp)l_receivedDateTime);

            //���҃R�[�h�iSONAR�j
            MainAccountRow l_accountRow = (MainAccountRow)l_account.getDataSourceObject();
            String l_strSonarCode = l_accountRow.getSonarTraderCode();
            l_aioOrderUnitParams.setSonarTraderCode(l_strSonarCode);

            //���ʔԍ��̔ԃC���^�[�t�F�[�X
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
                (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);
            String l_strNewNumber = null;
            l_strNewNumber =
                l_hostReqOrderNumberManageService.getNewNumber(
                    l_account.getInstitution().getInstitutionCode(),
                    l_account.getBranch().getBranchCode(),
                    ProductTypeEnum.AIO);//WEB3BaseException
            //���ʃR�[�h
            l_aioOrderUnitParams.setOrderRequestNumber(l_strNewNumber);

            //.com�f�r�b�g���ώ���ԍ�
            l_aioOrderUnitParams.setComDebitNumber(null);

            //�ۏ؋��敪
            l_aioOrderUnitParams.setGuaranteeDiv(null);

            //�o���\���敪
            l_aioOrderUnitParams.setPaymentApplicationDiv(this.getSrvDiv());

            //��������敪
            l_aioOrderUnitParams.setCancelType(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);

            //�����o�H�敪
            l_aioOrderUnitParams.setOrderRootDiv(this.orderRootDiv);

            //�����G���[���R�R�[�h
            l_aioOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

            //MQ�X�e�[�^�X
            l_aioOrderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);

            //category  QA:WEB3-SRVREGI-A-UT-0091.xls
            //zhangwei modified according to:web3-DB��-V3.1
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);

            //������
			l_aioOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.getOrderBizDate(), "yyyyMMdd"));

            //��n��
            l_aioOrderUnitParams.setDeliveryDate(this.getDeliveryDate());

            log.exiting(STR_METHOD_NAME);
            return l_aioOrderUnitParams;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,  STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(), STR_METHOD_NAME);
        }

    }

    /**
     * (set��n��)<BR>
     * ��n���̐ݒ���s���B<BR>
     * <BR>
     * �P�j�@@����.��n����this.��n���ɐݒ肷��B<BR>
     * @@param l_tsDeliveryDate - (��n��)<BR>
     * @@roseuid 4100F10F0231
     */
    public void setDeliveryDate(Timestamp l_tsDeliveryDate)
    {
        this.deliveryDate = l_tsDeliveryDate;
    }

    /**
     * (get��n��)<BR>
     * ��n����Ԃ��B<BR>
     * <BR>
     * �P�j�@@this.��n����Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 4100F11A03E7
     */
    public Timestamp getDeliveryDate()
    {
        return this.deliveryDate;
    }

    /**
     * (set�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪�̐ݒ���s���B<BR>
     * <BR>
     * �P�j�@@����.�T�[�r�X�敪��this.�T�[�r�X�敪�ɐݒ肷��B<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@roseuid 4100F16C034B
     */
    public void setSrvDiv(String l_strSrvDiv)
    {
        this.srvDiv = l_strSrvDiv;
    }

    /**
     * (get�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪��Ԃ��B<BR>
     * <BR>
     * �P�j�@@this.�T�[�r�X�敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4100F16C034D
     */
    public String getSrvDiv()
    {
        return this.srvDiv;
    }

    /**
     * (set�����o�H�敪)<BR>
     * �����o�H�敪�̐ݒ���s���B<BR>
     * <BR>
     * �P�j�@@����.�����o�H�敪��this.�����o�H�敪�ɐݒ肷��B<BR>
     * @@param l_strOrderRootDiv - (�����o�H�敪)<BR>
     * @@roseuid 4100F1E4009B
     */
    public void setOrderRootDiv(String l_strOrderRootDiv)
    {
        this.orderRootDiv = l_strOrderRootDiv;
    }

    /**
     * (get�����o�H�敪)<BR>
     * �����o�H�敪��Ԃ��B<BR>
     * <BR>
     * �P�j�@@this.�����o�H�敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 412BFED70269
     */
    public String getOrderRootDiv()
    {
        return this.orderRootDiv;
    }

    /**
     * (set������)<BR>
     * �������̐ݒ���s���B<BR>
     * <BR>
     * �P�j�@@����.��������this.�������ɐݒ肷��B<BR>
     * @@param l_tsOrderBizDate - (������)<BR>
     * @@roseuid 4100F10F0231
     */
    public void setOrderBizDate(Timestamp l_tsOrderBizDate)
    {
        this.orderBizDate = l_tsOrderBizDate;
    }

    /**
     * (get������)<BR>
     * ��������Ԃ��B<BR>
     * <BR>
     * �P�j�@@this.��������Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 4100F11A03E7
     */
    public Timestamp getOrderBizDate()
    {
        return this.orderBizDate;
    }
}
@
