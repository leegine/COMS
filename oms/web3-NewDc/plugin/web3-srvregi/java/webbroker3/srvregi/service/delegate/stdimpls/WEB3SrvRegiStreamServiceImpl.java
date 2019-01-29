head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStreamServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p���A�g�T�[�r�XImpl(WEB3SrvRegiStreamServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/19 ���g �V�K�쐬 ���f��No.371,No.373,No.376,No.377
Revision History : 2008/06/20 ���g (���u) ���f��No.394
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiStreamCommon;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.define.WEB3SrvRegiTradingTypeDef;
import webbroker3.srvregi.message.WEB3SrvRegiStreamRequest;
import webbroker3.srvregi.message.WEB3SrvRegiStreamResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStreamService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.service.delegate.WEB3TPBondSimplexCooperationService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p���A�g�T�[�r�XImpl)<BR>
 * �T�[�r�X���p���A�g�T�[�r�X�����N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3SrvRegiStreamServiceImpl extends WEB3SrvRegiClientRequestService
    implements WEB3SrvRegiStreamService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStreamServiceImpl.class);

   /**
    * @@roseuid 4831260B03A9
    */
   public WEB3SrvRegiStreamServiceImpl()
   {

   }

   /**
    * �T�[�r�X���p���A�g�T�[�r�X�������s���B<BR>
    * <BR>
    * �V�[�P���X�}�u�i�T�[�r�X���p�j���A�g�v�Q��<BR>
     * <BR>
     * ======================================================== <BR>
     * ��̈ʒu�Fis�񋟒�()=false�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@tag�@@: BUSINESS_ERROR_01927 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * ��̈ʒu�Fvalidate�������R�[�h(�����R�[�h:String, �،���ЃR�[�h:String)=false�̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@tag�@@: BUSINESS_ERROR_01067 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * ��̈ʒu�Fvalidate�ژ_�����{��.�`�F�b�N����="�{������"�̏ꍇ�A<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@tag�@@: BUSINESS_ERROR_03092 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * ��̈ʒu�Fvalidate�ژ_�����{��.�`�F�b�N����="�{�����ρi��Q���j"�̏ꍇ�A<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@tag�@@:  BUSINESS_ERROR_01984<BR>
     * ======================================================== <BR>
     * <BR>
    * @@param l_request - (���N�G�X�g�f�[�^)<BR>
    * ���N�G�X�g�f�[�^<BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException
    * @@roseuid 48158D190042
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
       log.entering(STR_METHOD_NAME);

       if (l_request == null)
       {
           log.debug("�p�����[�^�l�s���B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
       }

       WEB3SrvRegiStreamResponse l_srvRegiStreamResponse = null;
       if (l_request instanceof WEB3SrvRegiStreamRequest)
       {
           WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = (WEB3SrvRegiStreamRequest)l_request;

           //validate( )
           l_srvRegiStreamRequest.validate();

           //validate������t�\( )
           WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

           //get�⏕����(�⏕�����^�C�v : SubAccountTypeEnum)
           //�⏕�����^�C�v="������������i�a����j"
           SubAccount l_subAccount =
               this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

           FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
           WEB3GentradeOrderValidator l_gentradeOrderValidator =
               (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

           //validate����\�ڋq(�⏕���� : SubAccount)
           OrderValidationResult l_orderValidationResult =
               l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
           if(l_orderValidationResult.getProcessingResult().isFailedResult())
           {
               log.debug("����\�ڋq�`�F�b�N�G���[");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   l_orderValidationResult.getProcessingResult().getErrorInfo(),
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "����\�ڋq�`�F�b�N�G���[");
           }

           //get�T�[�r�X�}�X�^�[(String, String, boolean)
           //�،���ЃR�[�h = �⏕�����I�u�W�F�N�g.getInstitution( ).getInstitutionCode( )�̖߂�l
           //�T�[�r�X�敪=���N�G�X�g�f�[�^.�T�[�r�X�敪
           //is�s���b�N=false
           WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement =
               new WEB3SrvRegiServiceInfoManagement();
           WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
               l_srvRegiServiceInfoManagement.getSrvMaster(
                   l_subAccount.getInstitution().getInstitutionCode(),
                   l_srvRegiStreamRequest.serviceDiv,
                   false);

           //is�񋟒�
           boolean l_blnIsProviding = l_srvRegiServiceMaster.isProviding();

           //�Eis�񋟒�()=false�̏ꍇ�A��O���X���[����B
           if (!l_blnIsProviding)
           {
               log.debug("�T�[�r�X����~���̎��̃G���[�i�T�[�r�X�N���j�B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_01927,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "�T�[�r�X����~���̎��̃G���[�i�T�[�r�X�N���j�B");
           }

           WEB3SrvRegiStreamCommon l_srvRegiStreamCommon =
               new WEB3SrvRegiStreamCommon();

           //���N�G�X�g.����敪 = 1 or ���N�G�X�g.����敪 = 3
           if (WEB3SrvRegiTradingTypeDef.BUY_PROCESS.equals(
               l_srvRegiStreamRequest.tradingType)
               || WEB3SrvRegiTradingTypeDef.PROSPECTUS_CHECK.equals(
                   l_srvRegiStreamRequest.tradingType))
           {
               WEB3GentradeBatoClientService l_service =
                   (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);

               //validate�������R�[�h(String, String)
               //�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h
               boolean l_blnIsBondProductCode = l_srvRegiStreamCommon.validateBondProductCode(
                   l_srvRegiStreamRequest.productCode, l_subAccount.getInstitution().getInstitutionCode());

               if (!l_blnIsBondProductCode)
               {
                   log.debug("�����R�[�h�̓��͂��s���ł��B");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                       getClass().getName() + "." + STR_METHOD_NAME,
                       "�����R�[�h�̓��͂��s���ł��B");
               }

               //validate�ژ_�����{��(��ʃR�[�h : String, �����R�[�h : String)
               //��ʃR�[�h�F ���N�G�X�g�f�[�^.��ʃR�[�h
               //�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h
               WEB3GentradeProspectusResult l_gentradeProspectusResult =
                   l_service.validateProspectus(
                       l_srvRegiStreamRequest.batTypeCode, l_srvRegiStreamRequest.productCode);

               //validate�ژ_�����{��.�`�F�b�N����="�{������"�̏ꍇ�A��O���X���[����B
               if (WEB3GentradeBatoCheckResultDef.UNINSPECTION.equals(
                   l_gentradeProspectusResult.checkResult))
               {
                   log.debug("�ژ_�����{���`�F�b�N���ʂ��{�����ςł��B");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_03092,
                       getClass().getName() + "." + STR_METHOD_NAME,
                       "�ژ_�����{���`�F�b�N���ʂ��{�����ςł��B");
               }

               //validate�ژ_�����{��.�`�F�b�N����="�{�����ρi��Q���j"�̏ꍇ�A��O���X���[����B
               if (WEB3GentradeBatoCheckResultDef.UNINSPECTION_TROUBLE.equals(
                   l_gentradeProspectusResult.checkResult))
               {
                   log.debug("[�d�q���V�X�e����Q��]��Q�������s�B");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01984,
                       getClass().getName() + "." + STR_METHOD_NAME,
                       "[�d�q���V�X�e����Q��]��Q�������s�B");
               }
           }

           WEB3GentradeSubAccount l_gentradeSubAccount =
               (WEB3GentradeSubAccount)l_subAccount;

           WEB3TPBondSimplexCooperationService l_bondSimplexCooperationService =
               (WEB3TPBondSimplexCooperationService)Services.getService(WEB3TPBondSimplexCooperationService.class);
           //���N�G�X�g.����敪 = 1
           if (WEB3SrvRegiTradingTypeDef.BUY_PROCESS.equals(
               l_srvRegiStreamRequest.tradingType))
           {
               //getMainAccount
               MainAccount l_mainAccount = l_gentradeSubAccount.getMainAccount();

               //getAccountId( )
               long l_lngAccountId = l_mainAccount.getAccountId();

               //get�㗝���͎�( )
               Trader l_trader = this.getTrader();

               //validate���A�g�]�̓`�F�b�N(�⏕����, Trader, double, Timestamp, Timestamp)
               //�⏕�����Fget�⏕����()�̖߂�l
               //�㗝���͎ҁFget�㗝���͎�()�̖߂�l
               //���p�����F���N�G�X�g�f�[�^.���z�iString�^��double�^�փL���X�g�j
               //��n���F���N�G�X�g�f�[�^.��n���iString�^��Timestamp�^�փL���X�g�j
               //�������F���N�G�X�g�f�[�^.�������iString�^��Timestamp�^�փL���X�g�j
               double l_dblAmount = Double.parseDouble(l_srvRegiStreamRequest.amount);

               Timestamp l_tsDeliveryDate =
                   new Timestamp(WEB3DateUtility.getDate(
                       l_srvRegiStreamRequest.deliveryDate,
                       WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());

               Timestamp l_tsOrderBizDate =
                   new Timestamp(WEB3DateUtility.getDate(
                       l_srvRegiStreamRequest.orderBizDate,
                       WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());

               l_srvRegiStreamCommon.validateBondOrgTradingPowerCheck(
                   l_gentradeSubAccount,
                   l_trader,
                   l_dblAmount,
                   l_tsDeliveryDate,
                   l_tsOrderBizDate);

               //save�����t���(����ID : ��ong, �����t��� : ��ouble,
               //�g�����U�N�V���������� : Date, ��n�� : Date, ����No : String)
               //����ID�F getAccountId()�̖߂�l
               //�����t����F ���N�G�X�g�f�[�^.���z�iString�^��double�^�փL���X�g�j
               //�g�����U�N�V�����������F ���N�G�X�g�f�[�^.�������iString�^��Date�^�փL���X�g�j
               //��n���F ���N�G�X�g�f�[�^.��n���iString�^��Date�^�փL���X�g�j
               //�������F ���N�G�X�g�f�[�^.������
               l_bondSimplexCooperationService.saveBondBuyAmount(
                   l_lngAccountId,
                   l_dblAmount,
                   WEB3DateUtility.getDate(
                       l_srvRegiStreamRequest.orderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                   WEB3DateUtility.getDate(
                       l_srvRegiStreamRequest.deliveryDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                   l_srvRegiStreamRequest.orderNo);
           }

           //���N�G�X�g.����敪 = 2
           if (WEB3SrvRegiTradingTypeDef.BUY_ORDER_CANCEL_PROCESS.equals(
               l_srvRegiStreamRequest.tradingType))
           {
               //cancel�����t���(����No : String)
               //���N�G�X�g�f�[�^.������
               l_bondSimplexCooperationService.cancelBondBuyAmount(l_srvRegiStreamRequest.orderNo);
           }

           //���N�G�X�g.����敪 = 1�@@or�@@���N�G�X�g.����敪 = 2
           if (WEB3SrvRegiTradingTypeDef.BUY_PROCESS.equals(
               l_srvRegiStreamRequest.tradingType)
               || WEB3SrvRegiTradingTypeDef.BUY_ORDER_CANCEL_PROCESS.equals(
                   l_srvRegiStreamRequest.tradingType))
           {
               //�]�͍Čv�Z(�⏕���� : �⏕����)
               //�⏕�����Fget�⏕����()�̖߂�l
               WEB3TPTradingPowerService l_service =
                    (WEB3TPTradingPowerService)Services.getService(
                            WEB3TPTradingPowerService.class);

                l_service.reCalcTradingPower(l_gentradeSubAccount);
           }

           //create���X�|���X( )
           l_srvRegiStreamResponse =
               (WEB3SrvRegiStreamResponse)l_srvRegiStreamRequest.createResponse();

           l_srvRegiStreamResponse.orderNo = l_srvRegiStreamRequest.orderNo;
       }
       else
       {
           log.debug("�p�����[�^�^�C�v�s���B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80018,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�^�C�v�s���B");
       }
       return l_srvRegiStreamResponse;
   }
}@
