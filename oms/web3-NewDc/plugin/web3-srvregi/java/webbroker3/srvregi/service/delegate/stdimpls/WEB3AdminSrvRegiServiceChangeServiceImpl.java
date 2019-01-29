head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�T�[�r�XImpl(WEB3AdminSrvRegiServiceChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 �s�p (���u) �V�K�쐬
Revesion History : 2005/10/05 ��؁@@���R�I(SRA) �g�����X�����N�Ή�
Revesion History : 2005/10/18 ��؁@@���R�I(SRA) �t�B�f���e�B�Ή�
Revesion History : 2007/06/05 ���^�](���u) �d�l�ύX���f��No.251  �c�a�X�V�d�l 038
Revesion History : 2008/03/14 ����@@����  (SCS) QTP�A�g�Ή�
Revesion History : 2008/05/20 �Ԑi (���u) ���f��372
Revesion History : 2008/07/18 ���g (���u) ���f��397
Revesion History : 2009/04/23 �Ԑi (���u) ���f��412
Revesion History : 2009/05/20 �đo�g(���u) ���f��417
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3SrvStatusDef;
import webbroker3.common.define.WEB3SrvUseKeyTypeDef;
import webbroker3.common.define.WEB3SupplyDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiCommCond;
import webbroker3.srvregi.WEB3SrvRegiConsDoc;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUseKey;
import webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.data.SrvRegiCommCondRow;
import webbroker3.srvregi.define.WEB3SrvRegiHashCalOrderDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiMailDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiReservedWordDef;
import webbroker3.srvregi.define.WEB3SrvRegiSendHowToDivDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeConfirmResponse;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCommissionCondition;
import webbroker3.srvregi.message.WEB3SrvRegiChargeInfo;
import webbroker3.srvregi.message.WEB3SrvRegiExecKey;
import webbroker3.srvregi.message.WEB3SrvRegiLotteryInfo;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceChangeService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�T�[�r�XImpl)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�T�[�r�X�����N���X<BR>
 *
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceChangeServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiServiceChangeService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceChangeServiceImpl.class);

    /**
     * @@roseuid 416F392B01D4
     */
    public WEB3AdminSrvRegiServiceChangeServiceImpl()
    {

    }

    /**
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate�ύX( )�܂��́A<BR>
     * submit�ύX( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F514E001CD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";

        log.entering(STR_METHOD_NAME);

        //1.1:<l_request instanceof �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�m�F���N�G�X�g�̏ꍇ>
        if (l_request instanceof WEB3AdminSrvRegiServiceChangeConfirmRequest)
        {
            //1.1.1:validate�ύX(�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�m�F���N�G�X�g)
            log.debug(" WEB3AdminSrvRegiServiceChangeConfirmRequest ");
            WEB3AdminSrvRegiServiceChangeConfirmResponse l_serviceChangeConfirmResponse = validateChange(
                (WEB3AdminSrvRegiServiceChangeConfirmRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_serviceChangeConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminSrvRegiServiceChangeCompleteRequest)
        //1.2:<l_request instanceof �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�������N�G�X�g�̏ꍇ>
        {
            //1.2.1:submit�ύX(�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�������N�G�X�g)
            log.debug(" WEB3AdminSrvRegiServiceChangeCompleteRequest ");
            WEB3AdminSrvRegiServiceChangeCompleteResponse l_serviceChangeCompleteResponse = submitChange(
                (WEB3AdminSrvRegiServiceChangeCompleteRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_serviceChangeCompleteResponse;
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * (validate�ύX)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�R���������s���B<BR>
     * <BR>
     *  ========================================================<BR>
     *  �V�[�P���X�}(�u�Ǘ��҃T�[�r�X�ύX / �i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�ύX�R���v): <BR>
     *          1.8.2.2. get�m�F���[�����()�̖߂�l=null�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01823<BR>
     *  ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *  �V�[�P���X�}(�u�Ǘ��҃T�[�r�X�ύX / �i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�ύX�R���v): <BR>
     *          1.8.3.2. get�_��������[�����()�̖߂�l=null�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01824<BR>
     *  ==========================================================<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�ύX�R���v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�ύX�R���v): <BR>
     *         1.8.1.1.1.<���򏈗� *3><BR>
     *         <���򏈗� *3><BR>
     *          DIR�Ǘ��҂ł͂Ȃ��ꍇ�A���ݐ\�����Ԓ��̒��I���̕ҏW�͕s�B<BR>
     *          �i�Ǘ��҃I�u�W�F�N�g.isDIR�Ǘ���( )=false�ł���A<BR>
     *          ���N�G�X�g�f�[�^.��W���ԏ��.�\�����ԁi���j�����ݓ����̏ꍇ�A��O���X���[����B�j<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00988<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�ύX�R���v): <BR>
     *         1.8.1.1.4.<���򏈗� *4><BR>
     *           <���򏈗� *4><BR>
     *          �ߋ��̒��I���̕ύX�͕s�B<BR>
     *          �i���N�G�X�g�f�[�^.��W���ԏ��.�\�����ԁi���j�����ݓ����̏ꍇ�A��O���X���[����B�j <BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00989<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�ύX�R���v): <BR>
     *         1.8.1.1.3.isBizDate(Timestamp)<BR>
     *         �o�����ɉc�Ɠ����w�肳��Ă��邩�`�F�b�N����B<BR>
     *         is�c�Ɠ�( )=false�̏ꍇ�A��O���X���[����B<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00990<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�m�F���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F514F301EC
     */
    protected WEB3AdminSrvRegiServiceChangeConfirmResponse validateChange(WEB3AdminSrvRegiServiceChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminSrvRegiServiceChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1:validate( )
        l_request.validate();

        log.debug("validate request over!");

        //1.3:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE, true);

        //1.5:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.6:isDIR�Ǘ���( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //1.7:<���ݓ����̎擾>
        Timestamp l_tsNowDate = GtlUtils.getTradingSystem().getSystemTimestamp();

        log.debug("1.7:<���ݓ����̎擾>:over");

        //1.8:�����N�G�X�g�f�[�^.�X�e�[�^�X!="��~��"�̏ꍇ��
        if (!WEB3SrvStatusDef.STOP.equals(l_request.serviceStatus))
        {
            //1.8.1:get�T�[�r�X�}�X�^�[(String, String, boolean)
            WEB3SrvRegiServiceInfoManagement l_infoManager = new WEB3SrvRegiServiceInfoManagement();

            WEB3SrvRegiServiceMaster l_srvMaster = l_infoManager.getSrvMaster(
                l_strInstitutionCode, l_request.serviceDiv, false);
            //1.8.2:�����N�G�X�g�f�[�^.���[���敪�i�m�F���[���j="���M����"�̏ꍇ��
            if (WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(l_request.confirmMailDiv))
            {
                //1.8.2.1:get�m�F���[�����( )
                //1.8.2.2��get�m�F���[�����()�̖߂�l=null�̏ꍇ�A��O���X���[���遄
                if (l_srvMaster.getConfirmMailInfo() == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01823,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }

            //1.8.3:�����N�G�X�g�f�[�^.���[���敪�i�_��������[���j="���M����"�̏ꍇ��
            if (WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(l_request.noticeMailDiv))
            {
                //1.8.2.1:get�_��������[�����( )
                //1.8.2.2��get�_��������[�����()�̖߂�l=null�̏ꍇ�A��O���X���[���遄
                if (l_srvMaster.getEndMaiDivInfo() == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01824,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        //1.9:<���򏈗� *1>
        if (l_request.applyInfo != null)
        {
            log.debug("1.9:<���򏈗� *1>:start");
            //1.9.1:<�J��Ԃ����� *1>
            WEB3SrvRegiLotteryInfo l_lotInfo = null;
            int l_intApplyInfoCnt = l_request.applyInfo.length;
            log.debug("l_intApplyInfoCnt:" + l_intApplyInfoCnt);
            for (int i = 0; i < l_intApplyInfoCnt; i++)
            {
                log.debug("loop count:" + i);
                l_lotInfo = l_request.applyInfo[i];
                //1.9.1.1:<���򏈗� *2>
                if (!l_lotInfo.invalidDiv)
                {
                    log.debug("1.9.1.1:<���򏈗� *2>");
                    //1.9.1.1.1:<���򏈗� *3>
                    if (!l_blnIsDirAdmin)
                    {
                        log.debug("1.9.1.1.1:<���򏈗� *3>");

                        if (WEB3DateUtility.compareToMinute(l_tsNowDate, l_lotInfo.applyStartDate) >= 0)
                        {
                            log.debug(getClass().getName() + STR_METHOD_NAME);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00988,
                                this.getClass().getName() + STR_METHOD_NAME);
                        }
                    }

                    //1.9.1.1.2:<���򏈗� *4>
                    if (WEB3DateUtility.compareToMinute(l_tsNowDate, l_lotInfo.applyEndDate) >= 0)
                    {
                        log.debug("1.9.1.1.2:<���򏈗� *4>");
                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00989,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }

                    //1.9.1.1.3:is�c�Ɠ�(Timestamp)
                    Timestamp l_tsPaymentDate = new Timestamp(l_lotInfo.paymentDate.getTime());
                    if (!WEB3SrvRegiTradingTimeManagement.isBizDate(l_tsPaymentDate))
                    {
                        log.debug("1.9.1.1.3:is�c�Ɠ�(Timestamp)=false");

                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00990,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }

                    //1.9.1.1.4:<���ԃ`�F�b�N>
                    WEB3SrvRegiServiceInfoManagement l_srvInfoManagement = new WEB3SrvRegiServiceInfoManagement();

                    //1.9.1.1.4.1:validate�\������(String, String, Timestamp, Timestamp)
                    Timestamp l_tsApplyStartDate = new Timestamp(l_lotInfo.applyStartDate.getTime());
                    Timestamp l_tsApplyEndDate = new Timestamp(l_lotInfo.applyEndDate.getTime());
                    Long l_lngLotInfoId = null;
                    if (l_lotInfo.lotteryId != null)
                    {
                        l_lngLotInfoId = new Long(l_lotInfo.lotteryId);
                    }

                    log.debug("1.9.1.1.4.1:validate�\������(String, String, Long, Timestamp, Timestamp)");

                    l_srvInfoManagement.validateAppliDate(l_strInstitutionCode, l_request.serviceDiv,
                        l_lngLotInfoId,
                        l_tsApplyStartDate, l_tsApplyEndDate);

                    //1.9.1.1.4.2:validate�K�p����(String, String, Timestamp, Timestamp)
                    Timestamp l_trTialStartDate = new Timestamp(l_lotInfo.trialStartDate.getTime());
                    Timestamp l_tsTrialEndDate = new Timestamp(l_lotInfo.trialEndDate.getTime());

                    log.debug("1.9.1.1.4.2:validate�K�p����(String, String, Timestamp, Timestamp)");

                    l_srvInfoManagement.validateAppliPeriod(l_strInstitutionCode, l_request.serviceDiv,
                        l_lngLotInfoId,
                        l_trTialStartDate, l_tsTrialEndDate);
                }
            }
        }
		
		//��Q�Ή� NO_U02018
        //1.10: �����N�G�X�g�f�[�^.�X�e�[�^�X!="��~��"�A����isDir�Ǘ��ҁ�"true"�̏ꍇ��
        if (!WEB3SrvStatusDef.STOP.equals(l_request.serviceStatus) && l_blnIsDirAdmin)
        {
        	log.debug("1.10:isDir�Ǘ���=true && serviceStatus!=stop");
            //1.10.1:validate�T�[�r�X���p�L�[(String, String, String, String, String,
            //String, String, �T�[�r�X���p�N���L�[[ ], �T�[�r�X���p�N���L�[[ ])
            this.validateSrvUseKey(l_request.url,
                l_request.url2,
                l_request.hashCalHowToDiv,
                l_request.hashCalOrderDiv,
                l_request.sendHowToDiv,
                l_request.sendParamDiv,
                l_request.cryptAccountCodeDiv,
                l_request.hashList,
                l_request.paramList);
        }

        log.exiting(STR_METHOD_NAME);

        WEB3AdminSrvRegiServiceChangeConfirmResponse l_response = (WEB3AdminSrvRegiServiceChangeConfirmResponse)l_request.createResponse();
        return l_response;

    }

    /**
     * (submit�ύX)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�������s���B<BR>
     * <BR>
     *  ========================================================<BR>
     *  �V�[�P���X�}(�u�Ǘ��҃T�[�r�X�ύX / �i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�ύX�v): <BR>
     *          1.9.2.2. get�m�F���[�����()�̖߂�l=null�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01823<BR>
     *  ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *  �V�[�P���X�}(�u�Ǘ��҃T�[�r�X�ύX / �i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�ύX�v): <BR>
     *          1.9.3.2. get�_��������[�����()�̖߂�l=null�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01824<BR>
     *  ==========================================================<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�ύX�v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�ύX�v): <BR>
     *         1.9.1.1.1.<���򏈗� *3>  <BR>
     *         <���򏈗� *3><BR>
     *         DIR�Ǘ��҂ł͂Ȃ��ꍇ�A���ݐ\�����Ԓ��̒��I���̕ҏW�͕s�B<BR>
     *         �i�Ǘ��҃I�u�W�F�N�g.isDIR�Ǘ���( )=false�ł���A<BR>
     *       �@@���N�G�X�g�f�[�^.��W���ԏ��.�\�����ԁi���j�����ݓ����̏ꍇ�A��O���X���[����B�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00988<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�ύX�v): <BR>
     *         1.9.1.1.2.<���򏈗� *4> <BR>
     *          <���򏈗� *4><BR>
     *          �ߋ��̒��I���̕ύX�͕s�B<BR>
     *          �i���N�G�X�g�f�[�^.��W���ԏ��.�\�����ԁi���j�����ݓ����̏ꍇ�A��O���X���[����B�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00989<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�ύX�v): <BR>
     *         1.9.1.1.3.isBizDate(Timestamp)<BR>
     *          �o�����ɉc�Ɠ����w�肳��Ă��邩�`�F�b�N����B<BR>
     *          is�c�Ɠ�( )=false�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00990<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�������N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F514F80160
     */
    protected WEB3AdminSrvRegiServiceChangeCompleteResponse submitChange(WEB3AdminSrvRegiServiceChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminSrvRegiServiceChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1:validate( )
        l_request.validate();

        //1.3:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE, true);

        //1.5:validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);

        //1.6:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.7:isDIR�Ǘ���( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //1.8:<���ݓ����̎擾>
        Timestamp l_tsNowDate = GtlUtils.getTradingSystem().getSystemTimestamp();

        log.debug("1.8:<���ݓ����̎擾> over");

        //1.9:�����N�G�X�g�f�[�^.�X�e�[�^�X!="��~��"�̏ꍇ��
        if (!WEB3SrvStatusDef.STOP.equals(l_request.serviceStatus))
        {
            //1.9.1:get�T�[�r�X�}�X�^�[(String, String, boolean)
            WEB3SrvRegiServiceInfoManagement l_infoManager = new WEB3SrvRegiServiceInfoManagement();

            WEB3SrvRegiServiceMaster l_srvMaster = l_infoManager.getSrvMaster(
                l_strInstitutionCode, l_request.serviceDiv, false);
            //1.9.2:�����N�G�X�g�f�[�^.���[���敪�i�m�F���[���j="���M����"�̏ꍇ��
            if (WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(l_request.confirmMailDiv))
            {
                //1.9.2.1:get�m�F���[�����( )
                //1.9.2.2��get�m�F���[�����()�̖߂�l=null�̏ꍇ�A��O���X���[���遄
                if (l_srvMaster.getConfirmMailInfo() == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01823,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }

            //1.9.3:�����N�G�X�g�f�[�^.���[���敪�i�_��������[���j="���M����"�̏ꍇ��
            if (WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(l_request.noticeMailDiv))
            {
                //1.9.2.1:get�_��������[�����( )
                //1.9.2.2��get�_��������[�����()�̖߂�l=null�̏ꍇ�A��O���X���[���遄
                if (l_srvMaster.getEndMaiDivInfo() == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01824,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        //1.10:<���򏈗� *1>
        if (l_request.applyInfo != null)
        {
            log.debug("1.10:<���򏈗� *1> start");
            WEB3SrvRegiLotteryInfo l_lotInfo = null;
            int l_intAppliInfoCnt = l_request.applyInfo.length;
            log.debug("l_intAppliInfoCnt:" + l_intAppliInfoCnt);
            //1.10.1:<�J��Ԃ����� *1>
            for (int i = 0; i < l_intAppliInfoCnt; i++)
            {
                log.debug("loop count:" + i);
                l_lotInfo = l_request.applyInfo[i];
                //1.10.1.1:<���򏈗� *2>
                if (!l_lotInfo.invalidDiv)
                {
                    log.debug("1.10.1.1:<���򏈗� *2>");
                    //1.10.1.1.1:<���򏈗� *3>
                    if (!l_blnIsDirAdmin)
                    {
                        log.debug("l_blnIsDirAdmin=false");
                        if ( WEB3DateUtility.compareToMinute(l_tsNowDate, l_lotInfo.applyStartDate) >= 0)
                        {
                            log.debug("1.10.1.1.1:<���򏈗� *3> throw execption");
                            log.debug(getClass().getName() + STR_METHOD_NAME);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00988,
                                this.getClass().getName() + STR_METHOD_NAME);
                        }
                    }

                    //1.10.1.1.2:<���򏈗� *4>
                    if (WEB3DateUtility.compareToMinute(l_tsNowDate, l_lotInfo.applyEndDate) >= 0)
                    {
                        log.debug("1.10.1.1.2:<���򏈗� *3> throw execption");
                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00989,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }

                    //1.10.1.1.3:is�c�Ɠ�(Timestamp)
                    Timestamp l_tsPaymentDate = new Timestamp(l_lotInfo.paymentDate.getTime());
                    if (!WEB3SrvRegiTradingTimeManagement.isBizDate(l_tsPaymentDate))
                    {
                        log.debug("1.10.1.1.3:is�c�Ɠ�(Timestamp) throw execption");
                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00990,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }

                    //1.10.1.1.4:<���ԃ`�F�b�N>
                    WEB3SrvRegiServiceInfoManagement l_srvInfoManagement = new WEB3SrvRegiServiceInfoManagement();

                    //1.10.1.1.4.1:validate�\������(String, String, Timestamp, Timestamp)
                    Timestamp l_tsApplyStartDate = new Timestamp(l_lotInfo.applyStartDate.getTime());
                    Timestamp l_tsApplyEndDate = new Timestamp(l_lotInfo.applyEndDate.getTime());
                    Long l_lngLotInfoId = null;
                    if (l_lotInfo.lotteryId != null)
                    {
                        l_lngLotInfoId = new Long(l_lotInfo.lotteryId);
                    }

                    log.debug("1.10.1.1.4.1:validate�\������(String, String, Long, Timestamp, Timestamp)");
                    l_srvInfoManagement.validateAppliDate(l_strInstitutionCode, l_request.serviceDiv,
                        l_lngLotInfoId,
                        l_tsApplyStartDate, l_tsApplyEndDate);

                    //1.10.1.1.4.2:validate�K�p����(String, String, Timestamp, Timestamp)
                    Timestamp l_trTialStartDate = new Timestamp(l_lotInfo.trialStartDate.getTime());
                    Timestamp l_tsTrialEndDate = new Timestamp(l_lotInfo.trialEndDate.getTime());

                    log.debug("1.10.1.1.4.2:validate�K�p����(String, String, Timestamp, Timestamp)");
                    l_srvInfoManagement.validateAppliPeriod(l_strInstitutionCode, l_request.serviceDiv,
                        l_lngLotInfoId,
                        l_trTialStartDate, l_tsTrialEndDate);
                }
            }
            log.debug("1.10:<���򏈗� *1> over");
        }

		//��Q�Ή� NO_U02018
		//1.11: �����N�G�X�g�f�[�^.�X�e�[�^�X!="��~��"�A����isDir�Ǘ��ҁ�"true"�̏ꍇ��
        if (!WEB3SrvStatusDef.STOP.equals(l_request.serviceStatus))
        {
			log.debug("1.11.1:isDir�Ǘ���=true && serviceStatus!=stop");
            //1.11.1:validate�T�[�r�X���p�L�[(String, String, String, String, String,
            //String, String, �T�[�r�X���p�N���L�[[ ], �T�[�r�X���p�N���L�[[ ])
            this.validateSrvUseKey(l_request.url,
                l_request.url2,
                l_request.hashCalHowToDiv,
                l_request.hashCalOrderDiv,
                l_request.sendHowToDiv,
                l_request.sendParamDiv,
                l_request.cryptAccountCodeDiv,
                l_request.hashList,
                l_request.paramList);
        }

        //1.10:<�X�V����>
        //1:get�T�[�r�X�}�X�^�[(String, String, boolean)
        log.debug("1:get�T�[�r�X�}�X�^�[(String, String, boolean)");

        WEB3SrvRegiServiceInfoManagement l_srvInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvMaster =
            l_srvInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, true);

        //1.1:<�T�[�r�X�}�X�^�[�I�u�W�F�N�g�ւ̃v���p�e�B�E�Z�b�g>
        log.debug("1.1:<�T�[�r�X�}�X�^�[�I�u�W�F�N�g�ւ̃v���p�e�B�E�Z�b�g>");

        if (l_blnIsDirAdmin)
        {
            l_srvMaster.setSrvName(l_request.serviceName);
            l_srvMaster.setSrvUrl(l_request.url);
        }

        l_srvMaster.setStatus(l_request.serviceStatus);

        //1.1.1:save�T�[�r�X�}�X�^�[( )
        l_srvMaster.saveSrvMaster();

        boolean l_blnIsAppliRequired = l_srvMaster.isAppliRequired();

        if (l_blnIsAppliRequired)
        {
            //2:get�\���v�T�[�r�X(boolean)
            log.debug("2:get�\���v�T�[�r�X(boolean)");
            WEB3SrvRegiApplicationRequiredService l_appliRequireSrv = l_srvMaster.getAppliRequiredSrv(true);

            if (l_appliRequireSrv == null)
            {
                log.debug("2:get�\���v�T�[�r�X(boolean)=null");

                log.debug(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }

            log.debug("2.1:<�\���v�T�[�r�X�I�u�W�F�N�g�ւ̃v���p�e�B�E�Z�b�g>");
            //2.1:<�\���v�T�[�r�X�I�u�W�F�N�g�ւ̃v���p�e�B�E�Z�b�g>
            l_appliRequireSrv.setSummary(l_request.summary);

            l_appliRequireSrv.setTrialPeriodDiv(l_request.trialDiv);
            if (l_request.trialPeriod == null)
            {
                l_appliRequireSrv.setTrialPeriod(null);
            }
            else
            {
                l_appliRequireSrv.setTrialPeriod(new Integer(l_request.trialPeriod));
            }

            if (l_request.applyAbleStartDate == null)
            {
                l_appliRequireSrv.setAppliDateFrom(null);
            }
            else
            {
                l_appliRequireSrv.setAppliDateFrom(new Integer(l_request.applyAbleStartDate));
            }

            if (l_request.applyAbleEndDate == null)
            {
                l_appliRequireSrv.setAppliDateTo(null);
            }
            else
            {
                l_appliRequireSrv.setAppliDateTo(new Integer(l_request.applyAbleEndDate));
            }

            l_appliRequireSrv.setSrvContents(l_request.serviceContent);
            
            l_appliRequireSrv.setSrvExplanUrl(l_request.explainURL);

            l_appliRequireSrv.setStartMailDiv(l_request.confirmMailDiv);
            
            l_appliRequireSrv.setEndMailDiv(l_request.noticeMailDiv);

            if (l_request.noticeMailDate == null)
            {
                l_appliRequireSrv.setSendMailInterval(null);
            }
            else
            {
                l_appliRequireSrv.setSendMailInterval(new Integer(l_request.noticeMailDate));
            }
                l_appliRequireSrv.setElectricIssueDiv(l_request.elePigeonDiv);

            //�����Ώۊ���=���N�G�X�g�f�[�^.�����Ώۊ���
            if (l_request.freeTargetPeriod == null)
            {
            	l_appliRequireSrv.setFreeTargetPeriod(null);
            }
            else
            {
                l_appliRequireSrv.setFreeTargetPeriod(l_request.freeTargetPeriod);
            }

            if (l_blnIsDirAdmin)
            {
                //(*) ���N�G�X�g�f�[�^.�񋟋敪!=null�̏ꍇ
                if (l_request.offerType != null)
                {
                    log.debug("���N�G�X�g�f�[�^.�񋟋敪!=null�̏ꍇ");

                    //�񋟋敪=���N�G�X�g�f�[�^.�񋟋敪
                    l_appliRequireSrv.setSupplyDiv(l_request.offerType);
                    //�萔����������v�z=���N�G�X�g�f�[�^.�萔����������v�z
                    l_appliRequireSrv.setMinCommAmt(Double.parseDouble(l_request.commissionAttainTotal));
                }
                //(*) ���N�G�X�g�f�[�^.�񋟋敪=null�̏ꍇ
                else
                {
                    log.debug("���N�G�X�g�f�[�^.�񋟋敪=null�̏ꍇ");

                    //�񋟋敪=null
                    l_appliRequireSrv.setSupplyDiv(null);
                    //�萔����������v�z=0
                    l_appliRequireSrv.setMinCommAmt(0);
                }
            }

            //2.1.1:save�\���v�T�[�r�X( )
            l_appliRequireSrv.saveAppliRequiredSrv();
        }

        //3:get���ӏ�����(boolean)
        WEB3SrvRegiConsDoc l_consDoc = l_srvMaster.getConsDoc(true);

        //4:<get���ӏ�����( ).����==null�̏ꍇ>
        if (l_consDoc.getCons() == null)
        {
            log.debug("4:<get���ӏ�����( )==null�̏ꍇ");

            //4.1:saveNew���ӏ�����(String, String, String)
            l_consDoc = new WEB3SrvRegiConsDoc();
            l_consDoc.saveNewConsDoc(l_strInstitutionCode, l_request.serviceDiv, l_request.consentSentence);
        }
        else
        //5:<get���ӏ���?( ).����!=null�̏ꍇ>
        {
            log.debug("5:<get���ӏ�����( )!=null�̏ꍇ");

            //5.1:save���ӏ�����(String, String, String)
            l_consDoc.saveConsDoc(l_strInstitutionCode, l_request.serviceDiv, l_request.consentSentence);
        }

        //6:<���N�G�X�g�f�[�^.���p���ԗ������̌������A�J��Ԃ�>
        WEB3SrvRegiChargeInfo l_chargeInfo = null;
        if (l_request.chargeInfo != null)
        {
            log.debug("l_request.chargeInfo != null");
            int l_intChargeInfoCnt = l_request.chargeInfo.length;

            log.debug("l_intChargeInfoCnt:" + l_intChargeInfoCnt);
            for (int i=0; i < l_intChargeInfoCnt; i++)
            {
                log.debug("loop count:" + i);

                l_chargeInfo = l_request.chargeInfo[i];

                //6.1:<���N�G�X�g�f�[�^.���p���ԗ������.�����敪="�L��"�̏ꍇ>
                if (!l_chargeInfo.invalidDiv)
                {
                    log.debug("6.1:<���N�G�X�g�f�[�^.���p���ԗ������.�����敪=�L���̏ꍇ");

                    String l_strChargeId = l_chargeInfo.chargeId;

                    //6.1.1:<���N�G�X�g�f�[�^.���p���ԗ������.���p����ID=null�̏ꍇ>
                    if (l_strChargeId == null)
                    {
                        log.debug("6.1.1:<���N�G�X�g�f�[�^.���p���ԗ������.���p����ID=null�̏ꍇ>");

                        //6.1.1.1:createNew�T�[�r�X���p���ԗ���(String, String)
                        WEB3SrvRegiServiceUsePeriodAmt l_srvUsePeriodAmt =
                            WEB3SrvRegiServiceUsePeriodAmt.createNewSrvUsePeriodAmt(l_strInstitutionCode, l_request.serviceDiv);

                        //6.1.1.1.1 <�T�[�r�X���p���ԗ����I�u�W�F�N�g�ւ̃v���p�e�B�E�Z�b�g>
                        l_srvUsePeriodAmt.setSrvUsePeriodDiv(l_chargeInfo.chargeDiv);
                        l_srvUsePeriodAmt.setSrvUsePeriod(Integer.parseInt(l_chargeInfo.chargePeriod));
                        l_srvUsePeriodAmt.setUseAmt(Long.parseLong(l_chargeInfo.chargeAmt));

                        //6.1.1.1.2: saveNew�T�[�r�X���p���ԗ���( )
                        l_srvUsePeriodAmt.saveNewSrvUsePeriodAmt();
                    }
                    else
                    //6.1.2:<���N�G�X�g�f�[�^.���p���ԗ������.���p����ID!=null�̏ꍇ>
                    {
                        log.debug("6.1.2:<���N�G�X�g�f�[�^.���p���ԗ������.���p����ID!=null�̏ꍇ>");

                        //6.1.2.1:get�T�[�r�X���p���ԗ���(long, boolean)
                        WEB3SrvRegiServiceUsePeriodAmt l_srvUsePeriodAmt =
                            l_srvMaster.getSrvUseTermAmt(Long.parseLong(l_strChargeId), true);

                        if (l_srvUsePeriodAmt == null)
                        {
                            log.debug(getClass().getName() + STR_METHOD_NAME);
                            throw new WEB3BaseRuntimeException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                getClass().getName() + STR_METHOD_NAME);
                        }

                        //6.1.2.1.1:<�T�[�r�X���p���ԗ����I�u�W�F�N�g�ւ̃v���p�e�B�E�Z�b�g>
                        l_srvUsePeriodAmt.setSrvUsePeriodDiv(l_chargeInfo.chargeDiv);
                        l_srvUsePeriodAmt.setSrvUsePeriod(Integer.parseInt(l_chargeInfo.chargePeriod));
                        l_srvUsePeriodAmt.setUseAmt(Long.parseLong(l_chargeInfo.chargeAmt));

                        //6.1.2.1.1.1:save�T�[�r�X���p���ԗ���( )
                        l_srvUsePeriodAmt.saveSrvUsePeriodAmt();
                    }
                }
                //<���N�G�X�g�f�[�^.���p���ԗ������.�����敪="����"�̏ꍇ>
                else
                {
                    //<���N�G�X�g�f�[�^.���p���ԗ������.���p����ID!=null�̏ꍇ>
                    String l_strChargeId = l_chargeInfo.chargeId;

                    if (l_strChargeId != null)
                    {
                        //get�T�[�r�X���p���ԗ���(long, boolean)
                        WEB3SrvRegiServiceUsePeriodAmt l_srvUsePeriodAmt =
                            l_srvMaster.getSrvUseTermAmt(Long.parseLong(l_strChargeId), true);
                        //remove�T�[�r�X���p���ԗ���( )
                        l_srvUsePeriodAmt.removeSrvUsePeriodAmt();
                    }
                }
            }
        }

        //7:<���N�G�X�g�f�[�^.��W���ԏ��̌������A�J��Ԃ�>
        WEB3SrvRegiLotteryInfo l_lotInfo = null;

        if (l_request.applyInfo != null)
        {
            log.debug("l_request.applyInfo != null");

            int l_intAppliInfoCnt = l_request.applyInfo.length;
            log.debug("l_intAppliInfoCnt:" + l_intAppliInfoCnt);
            for (int i=0; i < l_intAppliInfoCnt; i++)
            {
                log.debug("loop count:" + i);

                l_lotInfo = l_request.applyInfo[i];

                //7.1:<���N�G�X�g�f�[�^.��W���ԏ��.�����敪="�L��"�̏ꍇ>
                if (!l_lotInfo.invalidDiv)
                {
                    log.debug("7.1:<���N�G�X�g�f�[�^.��W���ԏ��.�����敪=�L���̏ꍇ>");

                    String l_strLotInfoId = l_lotInfo.lotteryId;

                    //7.1.1:<���N�G�X�g�f�[�^.��W���ԏ��.���I���ID=null�̏ꍇ>
                    if (l_strLotInfoId == null)
                    {
                        log.debug("7.1.1:<���N�G�X�g�f�[�^.��W���ԏ��.���I���ID=null�̏ꍇ>");

                        //7.1.1.1:createNew�T�[�r�X���I���(String, String)
                        WEB3SrvRegiServiceLotInfo l_srvLotInfo =
                            WEB3SrvRegiServiceLotInfo.createNewSrvLotInfo(l_strInstitutionCode, l_request.serviceDiv);

                        //7.1.1.1.1<�T�[�r�X���I���I�u�W�F�N�g�ւ̃v���p�e�B�E�Z�b�g>
                        Timestamp l_tsApplyStartDate = new Timestamp(l_lotInfo.applyStartDate.getTime());
                        l_srvLotInfo.setAppliDateFrom(l_tsApplyStartDate);

                        Timestamp l_tsApplyEndDate = new Timestamp(l_lotInfo.applyEndDate.getTime());
                        l_srvLotInfo.setAppliDateTo(l_tsApplyEndDate);

                        if (l_lotInfo.lotteryDate == null)
                        {
                            l_srvLotInfo.setLotDate(null);
                        }
                        else
                        {
                            Timestamp l_tsLotteryDate = new Timestamp(l_lotInfo.lotteryDate.getTime());
                            l_srvLotInfo.setLotDate(l_tsLotteryDate);
                        }

                        Timestamp l_trTialStartDate = new Timestamp(l_lotInfo.trialStartDate.getTime());
                        l_srvLotInfo.setAppliStartDate(l_trTialStartDate);

                        Timestamp l_tsTrialEndDate = new Timestamp(l_lotInfo.trialEndDate.getTime());
                        l_srvLotInfo.setAppliEndDate(l_tsTrialEndDate);

                        l_srvLotInfo.setUseAmt(Long.parseLong(l_lotInfo.chargeAmt));

                        if (l_lotInfo.biddingPriceUnit == null)
                        {
                            l_srvLotInfo.setBiddingPrice(null);
                        }
                        else
                        {
                            l_srvLotInfo.setBiddingPrice(new Long(l_lotInfo.biddingPriceUnit));
                        }


                        Timestamp l_tsPaymentDate = new Timestamp(l_lotInfo.paymentDate.getTime());
                        l_srvLotInfo.setPaymentDate(l_tsPaymentDate);

                        if (l_lotInfo.applyMax == null)
                        {
                            l_srvLotInfo.setPublicOfferingQty(null);
                        }
                        else
                        {
                            l_srvLotInfo.setPublicOfferingQty(new Long(l_lotInfo.applyMax));
                        }

                        l_srvLotInfo.setInvestDiv(l_lotInfo.useDiv);
                        l_srvLotInfo.setHighSuccessBid(null);
                        l_srvLotInfo.setLowSuccessBid(null);
                        l_srvLotInfo.setAverageSuccessBid(null);

                        //7.1.1.1.2: saveNew�T�[�r�X���I���( )
                        l_srvLotInfo.saveNewSrvLotInfo();
                    }
                    else
                    //7.1.2:<���N�G�X�g�f�[�^.��W���ԏ��.���I���ID!=null�̏ꍇ>
                    {
                        log.debug("7.1.2:<���N�G�X�g�f�[�^.��W���ԏ��.���I���ID!=null�̏ꍇ>");

                        //7.1.2.1:get�T�[�r�X���I���(long, boolean)
                        WEB3SrvRegiServiceLotInfo l_srvLotInfo =
                            l_srvMaster.getSrvLotInfo(Long.parseLong(l_strLotInfoId), true);

                        if (l_srvLotInfo == null)
                        {
                            log.debug(getClass().getName() + STR_METHOD_NAME);
                            throw new WEB3BaseRuntimeException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                getClass().getName() + STR_METHOD_NAME);
                        }

                        //7.1.2.1.1:<�T�[�r�X���I���I�u�W�F�N�g�ւ̃v���p�e�B�E�Z�b�g>
                        Timestamp l_tsApplyStartDate = new Timestamp(l_lotInfo.applyStartDate.getTime());
                        l_srvLotInfo.setAppliDateFrom(l_tsApplyStartDate);

                        Timestamp l_tsApplyEndDate = new Timestamp(l_lotInfo.applyEndDate.getTime());
                        l_srvLotInfo.setAppliDateTo(l_tsApplyEndDate);

                        if (l_lotInfo.lotteryDate == null)
                        {
                            l_srvLotInfo.setLotDate(null);
                        }
                        else
                        {
                            Timestamp l_tsLotteryDate = new Timestamp(l_lotInfo.lotteryDate.getTime());
                            l_srvLotInfo.setLotDate(l_tsLotteryDate);
                        }

                        Timestamp l_trTialStartDate = new Timestamp(l_lotInfo.trialStartDate.getTime());
                        l_srvLotInfo.setAppliStartDate(l_trTialStartDate);

                        Timestamp l_tsTrialEndDate = new Timestamp(l_lotInfo.trialEndDate.getTime());
                        l_srvLotInfo.setAppliEndDate(l_tsTrialEndDate);

                        l_srvLotInfo.setUseAmt(Long.parseLong(l_lotInfo.chargeAmt));

                        if (l_lotInfo.biddingPriceUnit == null)
                        {
                            l_srvLotInfo.setBiddingPrice(null);
                        }
                        else
                        {
                            l_srvLotInfo.setBiddingPrice(new Long(l_lotInfo.biddingPriceUnit));
                        }

                        Timestamp l_tsPaymentDate = new Timestamp(l_lotInfo.paymentDate.getTime());
                        l_srvLotInfo.setPaymentDate(l_tsPaymentDate);

                        if (l_lotInfo.applyMax == null)
                        {
                            l_srvLotInfo.setPublicOfferingQty(null);
                        }
                        else
                        {
                            l_srvLotInfo.setPublicOfferingQty(new Long(l_lotInfo.applyMax));
                        }

                        l_srvLotInfo.setInvestDiv(l_lotInfo.useDiv);
                        l_srvLotInfo.setHighSuccessBid(null);
                        l_srvLotInfo.setLowSuccessBid(null);
                        l_srvLotInfo.setAverageSuccessBid(null);

                        //7.1.2.1.1.1:save�T�[�r�X���I���( )
                        l_srvLotInfo.saveSrvLotInfo();
                    }
                }
                // <���N�G�X�g�f�[�^.��W���ԏ��.�����敪="����"�̏ꍇ>
                else
                {
                    //<���N�G�X�g�f�[�^.��W���ԏ��.���I���ID!=null�̏ꍇ>
                    String l_strLotInfoId = l_lotInfo.lotteryId;

                    if (l_strLotInfoId != null)
                    {
                        //get�T�[�r�X���I���(long, boolean)
                        WEB3SrvRegiServiceLotInfo l_srvLotInfo =
                            l_srvMaster.getSrvLotInfo(Long.parseLong(l_strLotInfoId), true);
                        //remove�T�[�r�X���I���(String, String, long)
                        l_srvLotInfo.removeSrvLotInfo(l_strInstitutionCode, l_request.serviceDiv, Long.parseLong(l_strLotInfoId));
                    }
                }
            }

        }

        //8:��isDIR�Ǘ���()=true�̏ꍇ��
        if (l_blnIsDirAdmin)
        {
            //8.1:get��QURL( )
            String l_strUrl2 = l_srvMaster.getUrl2();
            //8.2:��get��QURL()==null�ł���A�����N�G�X�g�f�[�^.��QURL!=null�̏ꍇ��
            if (l_strUrl2 == null && l_request.url2 != null)
            {
                log.debug("new url2");

                //8.2.1:createNew�T�[�r�X���p�L�[(String, String, String)
                WEB3SrvRegiServiceUseKey l_srvUseKey =
                    WEB3SrvRegiServiceUseKey.createNewSrvUseKey(l_strInstitutionCode,
                        l_request.serviceDiv,
                        WEB3SrvUseKeyTypeDef.URL2);

                //8.2.2:set�T�[�r�X���p�L�[(String)
                l_srvUseKey.setSrvUseKey(l_request.url2);
                //8.2.3:saveNew�T�[�r�X���p�L�[( )
                l_srvUseKey.saveNewSrvUseKey();

            }
            //8.3��get��QURL()!=null�̏ꍇ��
            if (l_strUrl2 != null)
            {
                //8.3.1:get�T�[�r�X���p�L�[(String, long, boolean)
                WEB3SrvRegiServiceUseKey l_srvUseKey = l_srvMaster.getSrvUseKey(
                    WEB3SrvUseKeyTypeDef.URL2, 1, true);

                //8.3.2:�����N�G�X�g�f�[�^.��QURL!=null�̏ꍇ��
                if (l_request.url2 != null)
                {
                    log.debug("set url2");
                    //8.2.3.1: set�T�[�r�X���p�L�[(String)
                    l_srvUseKey.setSrvUseKey(l_request.url2);
                    //8.2.3.2: save�T�[�r�X���p�L�[( )
                    l_srvUseKey.saveSrvUseKey();
                }
                //8.3.3:�����N�G�X�g�f�[�^.��QURL==null�̏ꍇ��
                else
                {
                    log.debug("delete url2");
                    //8.3.3.1:remove�T�[�r�X���p�L�[( )
                    l_srvUseKey.removeSrvUseKey();
                }
            }

            //8.4:���T�[�r�X���p�L�[���͕K�{���ڂ̍X�V��
            //�n�b�V���v�Z�����敪
            //8.4.1:get�T�[�r�X���p�L�[(String, long, boolean)
            WEB3SrvRegiServiceUseKey l_srvUseKey = l_srvMaster.getSrvUseKey(
                WEB3SrvUseKeyTypeDef.HASH_CAL_HOW_TO_DIV, 1, true);

            if (l_srvUseKey == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //8.4.2:set�T�[�r�X���p�L�[(String)
            l_srvUseKey.setSrvUseKey(l_request.hashCalHowToDiv);
            //8.4.3:save�T�[�r�X���p�L�[( )
            l_srvUseKey.saveSrvUseKey();
            log.debug("�n�b�V���v�Z�����敪");

            //�n�b�V���v�Z�菇�敪
            //8.4.1:get�T�[�r�X���p�L�[(String, long, boolean)
            WEB3SrvRegiServiceUseKey l_srvUseKey2 = l_srvMaster.getSrvUseKey(
                WEB3SrvUseKeyTypeDef.HASH_CAL_ORDER_DIV, 1, true);

            if (l_srvUseKey2 == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //8.4.2:set�T�[�r�X���p�L�[(String)
            l_srvUseKey2.setSrvUseKey(l_request.hashCalOrderDiv);
            //8.4.3:save�T�[�r�X���p�L�[( )
            l_srvUseKey2.saveSrvUseKey();
            log.debug("�n�b�V���v�Z�菇�敪");

            //���M���@@�敪
            //8.4.1:get�T�[�r�X���p�L�[(String, long, boolean)
            WEB3SrvRegiServiceUseKey l_srvUseKey3 = l_srvMaster.getSrvUseKey(
                WEB3SrvUseKeyTypeDef.SEND_HOW_TO_DIV, 1, true);

            if (l_srvUseKey3 == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //8.4.2:set�T�[�r�X���p�L�[(String)
            l_srvUseKey3.setSrvUseKey(l_request.sendHowToDiv);
            //8.4.3:save�T�[�r�X���p�L�[( )
            l_srvUseKey3.saveSrvUseKey();
            log.debug("���M���@@�敪");

            //���M�p�����[�^�敪
            //8.4.1:get�T�[�r�X���p�L�[(String, long, boolean)
            WEB3SrvRegiServiceUseKey l_srvUseKey4 = l_srvMaster.getSrvUseKey(
                WEB3SrvUseKeyTypeDef.SEND_PARAM_DIV, 1, true);
            if (l_srvUseKey4 == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }
            //8.4.2:set�T�[�r�X���p�L�[(String)
            l_srvUseKey4.setSrvUseKey(l_request.sendParamDiv);
            //8.4.3:save�T�[�r�X���p�L�[( )
            l_srvUseKey4.saveSrvUseKey();
            log.debug("���M�p�����[�^�敪");

            //�Í����ڋq�R�[�h�敪
            //8.4.1:get�T�[�r�X���p�L�[(String, long, boolean)
            WEB3SrvRegiServiceUseKey l_srvUseKey5 = l_srvMaster.getSrvUseKey(
                WEB3SrvUseKeyTypeDef.CRYPT_ACCOUNT_CODE_DIV, 1, true);
            if (l_srvUseKey5 == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }
            //8.4.2:set�T�[�r�X���p�L�[(String)
            l_srvUseKey5.setSrvUseKey(l_request.cryptAccountCodeDiv);
            //8.4.3:save�T�[�r�X���p�L�[( )
            l_srvUseKey5.saveSrvUseKey();
            log.debug("�Í����ڋq�R�[�h�敪");

            //8.5:�����N�G�X�g�f�[�^.�n�b�V���l�ꗗ�̌������A�J��Ԃ���
            if (l_request.hashList != null)
            {
                int l_intCnt = l_request.hashList.length;

                for (int i = 0; i < l_intCnt; i++)
                {
                    WEB3SrvRegiExecKey l_exceKey = l_request.hashList[i];

                    //8.5.1:�����N�G�X�g�f�[�^.�n�b�V���l�ꗗ[n].���p�L�[ID!=null�̏ꍇ��
                    if (l_exceKey.keyId != null)
                    {
                        //8.5.1.1:get�T�[�r�X���p�L�[(String, long, boolean)
                        WEB3SrvRegiServiceUseKey l_srvUseKeyUpdate = l_srvMaster.getSrvUseKey(
                            WEB3SrvUseKeyTypeDef.HSAH_VALUE, Long.parseLong(l_exceKey.keyId), true);
                        //8.5.1.2:�����N�G�X�g�f�[�^.�n�b�V���l�ꗗ[n].�����敪="�L��"�̏ꍇ��
                        if (!l_exceKey.invalidDiv)
                        {
                            log.debug("set hash");
                            //8.5.1.2.1:set�T�[�r�X���p�L�[(String)
                            l_srvUseKeyUpdate.setSrvUseKey(l_exceKey.key);
                            //8.5.1.2.2:save�T�[�r�X���p�L�[( )
                            l_srvUseKeyUpdate.saveSrvUseKey();
                        }
                        //8.5.1.3:�����N�G�X�g�f�[�^.�n�b�V���l�ꗗ[n].�����敪="����"�̏ꍇ��
                        else
                        {
                            log.debug("delete hash");
                            //8.5.1.3.1:remove�T�[�r�X���p�L�[( )
                            l_srvUseKeyUpdate.removeSrvUseKey();
                        }
                    }
                    //8.5.2:�����N�G�X�g�f�[�^.�n�b�V���l�ꗗ[n].���p�L�[ID=null�̏ꍇ��
                    else
                    {
                        //8.5.2.1:�����N�G�X�g�f�[�^.�n�b�V���l�ꗗ[n].�����敪="�L��"�̏ꍇ��
                        if (!l_exceKey.invalidDiv)
                        {
                            log.debug("new hash");

                            //8.5.2.1.1:createNew�T�[�r�X���p�L�[(String, String, String)
                            WEB3SrvRegiServiceUseKey l_srvUseKeyNew =
                                WEB3SrvRegiServiceUseKey.createNewSrvUseKey(
                                    l_strInstitutionCode,
                                    l_request.serviceDiv,
                                    WEB3SrvUseKeyTypeDef.HSAH_VALUE);

                            //8.5.2.1.2:set�T�[�r�X���p�L�[(String)
                            l_srvUseKeyNew.setSrvUseKey(l_exceKey.key);

                            //8.5.2.1.3:saveNew�T�[�r�X���p�L�[( )
                            l_srvUseKeyNew.saveNewSrvUseKey();
                        }
                    }
                }
            }

            //8.6:�����N�G�X�g�f�[�^.���M�p�����[�^�ꗗ�̌������A�J��Ԃ���
            if (l_request.paramList != null)
            {
                int l_intCnt = l_request.paramList.length;

                for (int i = 0; i < l_intCnt; i++)
                {
                    WEB3SrvRegiExecKey l_exceKey = l_request.paramList[i];

                    //8.6.1:�����N�G�X�g�f�[�^.���M�p�����[�^�ꗗ[n].���p�L�[ID!=null�̏ꍇ��
                    if (l_exceKey.keyId != null)
                    {
                        //8.6.1.1:get�T�[�r�X���p�L�[(String, long, boolean)
                        WEB3SrvRegiServiceUseKey l_srvUseKeyUpdate = l_srvMaster.getSrvUseKey(
                            WEB3SrvUseKeyTypeDef.SEND_PARAM, Long.parseLong(l_exceKey.keyId), true);
                        //8.6.1.2:�����N�G�X�g�f�[�^.���M�p�����[�^�ꗗ[n].�����敪="�L��"�̏ꍇ��
                        if (!l_exceKey.invalidDiv)
                        {
                            log.debug("set params");
                            //8.6.1.2.1:set�T�[�r�X���p�L�[(String)
                            l_srvUseKeyUpdate.setSrvUseKey(l_exceKey.key);
                            //8.6.1.2.2:save�T�[�r�X���p�L�[( )
                            l_srvUseKeyUpdate.saveSrvUseKey();
                        }
                        //8.6.1.3:�����N�G�X�g�f�[�^.���M�p�����[�^�ꗗ[n].�����敪="����"�̏ꍇ��
                        else
                        {
                            log.debug("delete params");
                            //8.6.1.3.1:remove�T�[�r�X���p�L�[( )
                            l_srvUseKeyUpdate.removeSrvUseKey();
                        }
                    }
                    //8.6.2:�����N�G�X�g�f�[�^.���M�p�����[�^�ꗗ[n].���p�L�[ID=null�̏ꍇ��
                    else
                    {
                        //8.6.2.1:�����N�G�X�g�f�[�^.���M�p�����[�^�ꗗ[n].�����敪="�L��"�̏ꍇ��
                        if (!l_exceKey.invalidDiv)
                        {
                            log.debug("new params");
                            //8.6.2.1.1:createNew�T�[�r�X���p�L�[(String, String, String)
                            WEB3SrvRegiServiceUseKey l_srvUseKeyNew =
                                WEB3SrvRegiServiceUseKey.createNewSrvUseKey(
                                    l_strInstitutionCode,
                                    l_request.serviceDiv,
                                    WEB3SrvUseKeyTypeDef.SEND_PARAM);

                            //8.6.2.1.2:set�T�[�r�X���p�L�[(String)
                            l_srvUseKeyNew.setSrvUseKey(l_exceKey.key);

                            //8.6.2.1.3:saveNew�T�[�r�X���p�L�[( )
                            l_srvUseKeyNew.saveNewSrvUseKey();
                        }
                    }
                }
            }

			//��Q�Ή� NO_U01996
			log.debug("l_request.offerType = " + l_request.offerType);
			log.debug("applyCommissionConditions = " + l_request.applyCommissionConditions);
						
			if(l_request.applyCommissionConditions == null)
			{
				//�o�^�ςݓK�p�萔���ꗗ�̎擾
				List l_listCommCond = l_srvMaster.getCommCondList();
				
				for(int i=0; i<l_listCommCond.size(); i++)
				{
					SrvRegiCommCondRow l_srvRegiCommCond;
					//get�萔������()
					l_srvRegiCommCond = (SrvRegiCommCondRow) l_listCommCond.get(i);
								
					String l_institutionCode = l_srvRegiCommCond.getInstitutionCode();
					String l_srvDiv = l_srvRegiCommCond.getSrvDiv();
					String l_orderAccProduct = l_srvRegiCommCond.getOrderAccProduct();
					
					log.debug("�y�폜"+i+"�zinstitutionCode = "+l_institutionCode+"  srvDiv = "+l_srvDiv+"  orderAccProduct = "+l_orderAccProduct);
					
					WEB3SrvRegiCommCond l_commCond = 
						WEB3SrvRegiCommCond.createNewSrvRegiCommCondition(l_institutionCode, l_srvDiv, l_orderAccProduct);
						
					l_commCond.removeSrvRegiCommCondition();
				}
								
			}
            else
            {
                //8.7:<���N�G�X�g�f�[�^.�K�p�萔�������̌������A�J��Ԃ�>
                WEB3SrvRegiApplyCommissionCondition[] l_applyCommiConds = l_request.applyCommissionConditions;
                int l_intApplyCommiCondCnt = l_applyCommiConds.length;

                log.debug("l_intApplyCommiCondCnt:" + l_intApplyCommiCondCnt);

                for (int i = 0; i < l_intApplyCommiCondCnt; i++)
                {
					log.debug("applyCommissionConditions["+i+"].productKindDiv = " + l_request.applyCommissionConditions[i].productKindDiv);
					log.debug("applyCommissionConditions["+i+"].invalidDiv = " + l_request.applyCommissionConditions[i].invalidDiv);

                    //8.7.1.1get�萔������(String)
                    WEB3SrvRegiCommCond l_coomCond = l_srvMaster.getCommCond(l_applyCommiConds[i].productKindDiv);

					//8.7.2:get�萔������( )!=null�̏ꍇ>
                    if(l_coomCond != null){
						//8.7.2.1:{���N�G�X�g�f�[�^.�񋟋敪=����(0) or ����/�L��(1) or ����(2) or ����/�L��(3)} 
						//          and �K�p�萔������.�����敪="�L��"�̏ꍇ�A�폜�ΏۊO�i���̗v�f�փX�L�b�v�j
                    	if((l_request.offerType.equals(WEB3SupplyDivDef.FREE_SUPPLY) || 
							l_request.offerType.equals(WEB3SupplyDivDef.CHARGE_OR_FREE_SUPPLY) ||
							l_request.offerType.equals(WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA) ||
							l_request.offerType.equals(WEB3SupplyDivDef.CHARGE_OR_FREE_SUPPLY__UTUMIYA)) &&
							!l_applyCommiConds[i].invalidDiv)
						{
              				log.debug("8.7.2.1:get�萔������()!=null �y�폜�ΏۊO�z");
							continue;
						}
						log.debug("8.7.2.2:{���N�G�X�g�f�[�^.�񋟋敪=����(0) or ����/�L��(1)} and �K�p�萔������.�����敪=�����̏ꍇ>");
                        //8.7.2.2:remove�T�[�r�X���p�萔������( )
                        l_coomCond.removeSrvRegiCommCondition();
                    }

					//8.7.3:get�萔������( )=null�̏ꍇ>
                    if(l_coomCond == null)
                    {
						//8.7.3.1:<���N�G�X�g�f�[�^.�񋟋敪��null�ŁA���K�p�萔������.�����敪="�L��"�̏ꍇ>
						if (!l_request.offerType.equals(WEB3SupplyDivDef.NO_CONDITION_ATTACHED) && 
							!l_applyCommiConds[i].invalidDiv)
						{
							log.debug("8.7.3.1:<���N�G�X�g�f�[�^.�񋟋敪��null�ŁA���K�p�萔������.�����敪=�L���̏ꍇ>");

							//8.7.3.2:createNew�T�[�r�X���p�萔������(String, String, String)
							WEB3SrvRegiCommCond l_coomCond2 =
								WEB3SrvRegiCommCond.createNewSrvRegiCommCondition(
									l_strInstitutionCode,
									l_request.serviceDiv,
									l_applyCommiConds[i].productKindDiv);

							//8.7.3.3:saveNew�T�[�r�X���p�萔������( )
							l_coomCond2.saveNewSrvRegiCommCondition();
						}

                    }
                }
            }
        }
        log.exiting(STR_METHOD_NAME);

        WEB3AdminSrvRegiServiceChangeCompleteResponse l_response =
            (WEB3AdminSrvRegiServiceChangeCompleteResponse)l_request.createResponse();
        return l_response;
    }

    /**
     * (validate�T�[�r�X���p�L�[)<BR>
     * ���N�G�X�g�œn���ꂽ�T�[�r�X���p�L�[�̓��͒l(*1)�̐��������`�F�b�N����B<BR>
     * <BR>
     * (*1) �X�e�[�^�X!="��~��"�̏ꍇ�̂݁A�ȉ��̍��ڂ̃`�F�b�N���s���B<BR>
     * �@@�EURL <BR>
     * �@@�E��QURL <BR>
     * �@@�E�n�b�V���v�Z�����敪 <BR>
     * �@@�E�n�b�V���v�Z�菇�敪 <BR>
     * �@@�E���M���@@�敪 <BR>
     * �@@�E���M�p�����[�^�敪 <BR>
     * �@@�E�Í����ڋq�R�[�h�敪 <BR>
     * �@@�E�n�b�V���l�ꗗ <BR>
     * �@@�E���M�p�����[�^�ꗗ <BR>
     * <BR>
     * �P�j����.URL�Ɋ܂܂��\���̃`�F�b�N (*3) <BR>
     * �@@�|����.URL����"%%�`%%"�ŋ�؂�ꂽ�\��ꂪ���݂���ꍇ�A<BR>
     * �@@�N���X�u�T�[�r�X���p�\���ϊ��v�Œ�`���ꂽ�\���ƈقȂ�\��ꂪ�w�肳��Ă����ꍇ�A<BR>
     * �@@��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01827<BR>
     * <BR>
     * �Q�j����.��QURL�Ɋ܂܂��\���̃`�F�b�N (*3) <BR>
     * �@@�|����.��QURL����"%%�`%%"�ŋ�؂�ꂽ�\��ꂪ���݂���ꍇ�A<BR>
     *�@@ �N���X�u�T�[�r�X���p�\���ϊ��v�Œ�`���ꂽ�\���ƈقȂ�\��ꂪ�w�肳��Ă����ꍇ�A<BR>
     *�@@ ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01828<BR>
     * <BR>
     * �R�j����.���M�p�����[�^�ꗗ�Ɋ܂܂��\���̃`�F�b�N (*3) <BR>
     *�@@ �|����.���M�p�����[�^�ꗗ[n].���p�L�[����"%%�`%%"�ŋ�؂�ꂽ�\��ꂪ���݂���ꍇ�A<BR>
     *   �N���X�u�T�[�r�X���p�\���ϊ��v�Œ�`���ꂽ�\���ƈقȂ�\��ꂪ�w�肳��Ă����ꍇ�A<BR>
     *�@@ ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01829<BR>
     * <BR>
     * �S�j�Í����ڋq�R�[�h�̃`�F�b�N
     *   �|����.�Í����ڋq�R�[�h�敪="�L"�ł���A������.���M�p�����[�^�ꗗ�Ɋ܂܂��\����<BR>
     * �@@"�\���F�Í����ڋq�R�[�h"�����݂��Ȃ������ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01830<BR>
     * <BR>
     * �T�j�n�b�V���l�̌����`�F�b�N <BR>
     *   �|����.�n�b�V���v�Z�菇�敪="�d�q��"�܂���"�ʏ�v�Z�i�P�j"�̏ꍇ <BR>
     *�@@ ����.�n�b�V���l�ꗗ�̗L���Ȍ���!=2���̏ꍇ�A��O���X���[����B(*2)<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01831<BR>
     * <BR>
     *   �|����.�n�b�V���v�Z�菇�敪="�ʏ�v�Z�i�Q�j"�܂���"�Q�i�K�v�Z"�̏ꍇ <BR>
     * �@@����.�n�b�V���l�ꗗ�̗L���Ȍ���!=1���̏ꍇ�A��O���X���[����B(*2) <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01832<BR>
     * <BR>
     * �U�j��QURL�̃`�F�b�N <BR>
     *   �|����.���M���@@�敪="����i�P�j"�ł���A������.��QURL==null�̏ꍇ�A<BR>
     *�@@ ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01833<BR>
     * <BR>
     * (*2) ����.�n�b�V���l�ꗗ.�����敪="�L��"�̌�����p���Ĕ��肷��B<BR>
     * <BR>
     * (*3) �\���̃`�F�b�N�ɂ��� <BR>
     * �@@�@@�\���F���͋敪�����݂����ꍇ�A�ȉ��̃`�F�b�N���s���A<BR>
     * �@@�����ɍ��v���Ȃ������ꍇ�A��O���X���[����B<BR>
     * �@@�@@�\���F���͋敪�A�\���F���͋敪�����̗������܂܂�Ă��鎖�B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01825<BR>
     * <BR>
     * �@@�@@�i�\���F���͋敪�E�\���F���͋敪�����̏���������Ă��鎖���K�{�j<BR>
     * �@@�A�\���F���͋敪�A�\���F���͋敪�����̊Ԃɐ��l�������Ă��鎖�B<BR>
     *�@@�@@�i���l�ȊO�A�S�ăG���[�j<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01826<BR>
     * <BR>
     * @@param l_strUrl - (URL)<BR>
     * @@param l_strUrl2 - (��QURL)<BR>
     * @@param l_strHashCalHowToDiv - (�n�b�V���v�Z�����敪)<BR>
     * @@param l_strHashCalOrderDiv - (�n�b�V���v�Z�菇�敪)<BR>
     * @@param l_strSendHowToDiv - (���M���@@�敪)<BR>
     * @@param l_strSendParamDiv - (���M�p�����[�^�敪)<BR>
     * @@param l_strCryptAccountCodeDiv - (�Í����ڋq�R�[�h�敪)<BR>
     * @@param l_hashList - (�n�b�V���l�ꗗ)<BR>
     * @@param l_paramList - (���M�p�����[�^�ꗗ)<BR>
     * @@throws WEB3BaseException
     */
    protected void validateSrvUseKey(String l_strUrl,
        String l_strUrl2,
        String l_strHashCalHowToDiv,
        String l_strHashCalOrderDiv,
        String l_strSendHowToDiv,
        String l_strSendParamDiv,
        String l_strCryptAccountCodeDiv,
        WEB3SrvRegiExecKey[] l_hashList,
        WEB3SrvRegiExecKey[] l_paramList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSrvUseKey(String, String, String, " +
            "String, String, String, String, WEB3SrvRegiExecKey[], WEB3SrvRegiExecKey[])";

        log.entering(STR_METHOD_NAME);

        //�P�j����.URL�Ɋ܂܂��\���̃`�F�b�N (*3)
        //  �|����.URL����"%%�`%%"�ŋ�؂�ꂽ�\��ꂪ���݂���ꍇ�A
        //  �N���X�u�T�[�r�X���p�\���ϊ��v�Œ�`���ꂽ�\���ƈقȂ�\��ꂪ�w�肳��Ă����ꍇ�A
        //  ��O���X���[����B
        this.validateInputDiv(l_strUrl);
        String[] l_strReservedWords = this.getReservedWords(l_strUrl);
        if (l_strReservedWords != null)
        {
            int l_intCnt = l_strReservedWords.length;
            for (int i = 0; i < l_intCnt; i++)
            {
                String l_str = l_strReservedWords[i];
                int l_intInputDivFlag = l_str.indexOf(
                    WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV);
                if (l_intInputDivFlag >= 0)
                {
                    continue;
                }

                if (!this.validateWord(l_str))
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01827,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        //�Q�j����.��QURL�Ɋ܂܂��\���̃`�F�b�N (*3)
        //  �|����.��QURL����"%%�`%%"�ŋ�؂�ꂽ�\��ꂪ���݂���ꍇ�A
        //  �N���X�u�T�[�r�X���p�\���ϊ��v�Œ�`���ꂽ�\���ƈقȂ�\��ꂪ�w�肳��Ă����ꍇ�A
        //  ��O���X���[����B
        this.validateInputDiv(l_strUrl2);
        l_strReservedWords = this.getReservedWords(l_strUrl2);
        if (l_strReservedWords != null)
        {
            int l_intCnt = l_strReservedWords.length;
            for (int i = 0; i < l_intCnt; i++)
            {
                String l_str = l_strReservedWords[i];
                int l_intInputDivFlag = l_str.indexOf(
                    WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV);
                if (l_intInputDivFlag >= 0)
                {
                    continue;
                }

                if (!this.validateWord(l_str))
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01828,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        //�R�j����.���M�p�����[�^�ꗗ�Ɋ܂܂��\���̃`�F�b�N (*3)
        //  �|����.���M�p�����[�^�ꗗ[n].���p�L�[����"%%�`%%"�ŋ�؂�ꂽ�\��ꂪ���݂���ꍇ�A
        //  �N���X�u�T�[�r�X���p�\���ϊ��v�Œ�`���ꂽ�\���ƈقȂ�\��ꂪ�w�肳��Ă����ꍇ�A
        //  ��O���X���[����B
        if (l_paramList != null)
        {
            int l_intCnt = l_paramList.length;
            for (int i = 0; i < l_intCnt; i ++)
            {
                log.debug("i:" + i + "/" + l_intCnt);
                String l_strKey = l_paramList[i].key;
                this.validateInputDiv(l_strKey);
                l_strReservedWords = this.getReservedWords(l_strKey);
                if (l_strReservedWords != null)
                {
                    int l_intWordsCnt = l_strReservedWords.length;
                    for (int j = 0; j < l_intWordsCnt; j++)
                    {
                        log.debug("j:" + j + "/" + l_intWordsCnt);
                        String l_str = l_strReservedWords[j];
                        int l_intInputDivFlag = l_str.indexOf(
                            WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV);
                        if (l_intInputDivFlag >= 0)
                        {
                            continue;
                        }

                        if (!this.validateWord(l_str))
                        {
                            log.debug(getClass().getName() + STR_METHOD_NAME);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01829,
                                this.getClass().getName() + STR_METHOD_NAME);
                        }
                    }
                }
            }
        }

        //�S�j�Í����ڋq�R�[�h�̃`�F�b�N
        //  �|����.�Í����ڋq�R�[�h�敪="�L"�ł���A������.���M�p�����[�^�ꗗ�Ɋ܂܂��\����
        //  "�\���F�Í����ڋq�R�[�h"�����݂��Ȃ������ꍇ�A��O���X���[����B
        if (WEB3ConditionsValueDivDef.HAVE.equals(l_strCryptAccountCodeDiv))
        {
            if (l_paramList != null && l_paramList.length > 0)
            {
                int l_intCnt = l_paramList.length;
                int l_intExpFlag = 0;
                for (int i = 0; i < l_intCnt; i++)
                {
                    String l_strKey = l_paramList[i].key;
                    if (l_strKey != null)
                    {
                        int l_intFlag =
                        l_strKey.indexOf(WEB3SrvRegiReservedWordDef.RESERVED_WORD_ENCRYPTION_ACCOUNT_CODE);
                        if (l_intFlag >= 0)
                        {
                            l_intExpFlag ++;
                        }
                    }
                }
                if (l_intExpFlag == 0)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01830,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            else
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01830,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        //�T�j�n�b�V���l�̌����`�F�b�N
        //  �|����.�n�b�V���v�Z�菇�敪="�d�q��"�܂���"�ʏ�v�Z�i�P�j"�A�܂���"�Q�i�K�v�Z"�̏ꍇ
        //    ����.�n�b�V���l�ꗗ�̗L���Ȍ���!=2���̏ꍇ�A��O���X���[����B(*2)
        if (WEB3SrvRegiHashCalOrderDivDef.ELE_PIGEON.equals(l_strHashCalOrderDiv) ||
            WEB3SrvRegiHashCalOrderDivDef.NORMAL1.equals(l_strHashCalOrderDiv) ||
            WEB3SrvRegiHashCalOrderDivDef.TWO_STEP_CALCULATION.equals(l_strHashCalOrderDiv))
        {
            if (l_hashList == null)
            {
                l_hashList = new WEB3SrvRegiExecKey[0];
            }
            int l_intCnt = l_hashList.length;
            int l_intValidCnt = 0;
            for (int i = 0; i < l_intCnt; i++)
            {
                if (!l_hashList[i].invalidDiv)
                {
                    l_intValidCnt ++;
                }
            }
            if (l_intValidCnt != 2)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01831,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        //  �|����.�n�b�V���v�Z�菇�敪="�ʏ�v�Z�i�Q�j"�̏ꍇ
        //    ����.�n�b�V���l�ꗗ�̗L���Ȍ���!=1���̏ꍇ�A��O���X���[����B(*2)
        if (WEB3SrvRegiHashCalOrderDivDef.NORMAL2.equals(l_strHashCalOrderDiv))
        {
            if (l_hashList == null)
            {
                l_hashList = new WEB3SrvRegiExecKey[0];
            }
            int l_intCnt = l_hashList.length;
            int l_intValidCnt = 0;
            for (int i = 0; i < l_intCnt; i++)
            {
                if (!l_hashList[i].invalidDiv)
                {
                    l_intValidCnt ++;
                }
            }
            if (l_intValidCnt != 1)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01832,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }


        //�U�j��QURL�̃`�F�b�N
        //  �|����.���M���@@�敪="����i�P�j"�ł���A������.��QURL==null�̏ꍇ�A
        //    ��O���X���[����B
        if (WEB3SrvRegiSendHowToDivDef.SPECIAL1.equals(l_strSendHowToDiv))
        {
            if (l_strUrl2 == null)
            {                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01833,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }


        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ������URL�̒�����%%�ň͂܂ꂽ�\���̈ꗗ���擾����
     * 
     * @@param l_strUrl URL
     * @@return �\���̈ꗗ
     */
    private String[] getReservedWords(String l_strUrl)
    {
        if (WEB3StringTypeUtility.isEmptyOrBlank(l_strUrl))
        {
            return null;
        }

        final String l_strDoublePercents = "%%";
        List l_lisReservedWords = new ArrayList();

        int l_intUrlLength = l_strUrl.length();

        for (int i = 0; i < l_intUrlLength - 1; i++)
        {
            if (l_strDoublePercents.equals(l_strUrl.substring(i, i + 2)))
            {
                int l_intHead = 0;
                int l_intTail = 0;

                for (int j = i + 2; j < l_intUrlLength - 1; j++)
                {
                    l_intHead = i;
                    if (l_strDoublePercents.equals(l_strUrl.substring(j, j + 2)))
                    {
                        l_intTail = j + 2;
                        i = j + 1;
                        l_lisReservedWords.add(l_strUrl.substring(l_intHead, l_intTail));
                        break;
                    }
                }
            }
        }

        String[] l_strReservedWords = new String[l_lisReservedWords.size()];
        l_lisReservedWords.toArray(l_strReservedWords);

        return l_strReservedWords;

    }

    /**
     * ���͋敪�̃`�F�b�N
     * 
     * @@param l_strContent
     * @@throws WEB3BaseException
     */
    private void validateInputDiv(String l_strContent) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateInputDiv(String)";
        log.entering(STR_METHOD_NAME);

        log.debug("1:" + l_strContent);

        boolean l_blnEndFlag = false;

        if (l_strContent != null)
        {
            l_strContent = l_strContent.trim();
        }
        else
        {
            l_blnEndFlag = true;
        }
        log.debug("2:" + l_strContent);

        while (!l_blnEndFlag)
        {
            log.debug("check:" + l_strContent);
            int l_intStartFlag = l_strContent.indexOf(
                WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV);
            if (l_intStartFlag >= 0)
            {
                int l_intEndFlag = l_strContent.indexOf(
                    WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV_END);
                if (l_intEndFlag < 0)
                {
                    log.debug("has start,has not end");
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01825,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                else
                {
                    if (l_intStartFlag > l_intEndFlag)
                    {
                        log.debug("end....start");
                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01825,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }
                    else
                    {
                        String l_strEquals = l_strContent.substring(
                            l_intStartFlag + WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV.length(),
                            l_intEndFlag);
                        boolean l_blnIsNumber = WEB3StringTypeUtility.isNumber(l_strEquals);
                        if (!l_blnIsNumber)
                        {
                            log.debug("start.."+ l_strEquals + "..end");
                            log.debug(getClass().getName() + STR_METHOD_NAME);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01826,
                                this.getClass().getName() + STR_METHOD_NAME);
                        }
                    }
                }

                l_strContent = l_strContent.substring(l_intEndFlag +
                    WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV_END.length());

            }
            else
            {
                int l_intEndFlag = l_strContent.indexOf(
                    WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV_END);
                if (l_intEndFlag >= 0)
                {
                    log.debug("has not start, has end");
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01825,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                l_blnEndFlag = true;
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
	
	/**
	 * �\���̃`�F�b�N
	 * 
	 * �ȉ��̗\���̂����ꂩ�Ɉ�v����ꍇ�Atrue��ԋp����
	 * �E�\���F�،���ЃR�[�h
	 * �E�\���F���X�R�[�h
	 * �E�\���F�ڋq�R�[�h
	 * �E�\���F�����R�[�h
	 * �E�\���F�Í����ڋq�R�[�h
	 * �E�\���F�n�b�V���v�Z����
	 * �E�\���FToken
	 * �E�\���F�����`���l��
	 * �E�\���F����
	 * �E�\���F�M�p�����敪
	 * �E�\���F�敨OP�����敪�i��؁j
	 * �E�\���F�ڋq��
	 * �E�\���F�N�����iYYYYMMDD�j
	 * �E�\���F�N�����iYYYY-MM-DD-HH-MM�j
	 * �E�\���F�N�����iYYYYMMDDHHMM�j
	 * �E�\���F�_������i�K�p�I�����j
	 * �E�\���F�n�b�V���v�Z�v�f�i�P�j
	 * �E�\���F�n�b�V���v�Z�v�f�i�Q�j
	 * �E�\���F���Җ�
	 * �E�\���F���͋敪
	 * �E�\���F���͋敪����
	 * �E�\���F�i��u���j%%HSTR%%
	 * �E�\���F�i��u���j%%FUNDTYPE%%
	 * �E�\���F�i��u���j%%FUNDCODE%%
	 * �E�\���F�i��u���j%%DELYEAR%%
	 * �E�\���F�i��u���j%%DELMONTH%%
	 * �E�\���F�i��u���j%%PUTCALL%%
	 * �E�\���F�i��u���j%%STRIKEPRC%%
	 * �E�\���F�i��u���j%%TRADETYPE%%
	 * �E�\���F�i��u���j%%BUYSELLFLAG%%
	 * �E�\���F�i��u���j%%STKEXCODE%%
	 * �E�\���F�n�b�V�������ꂽ�ڋqID
	 * �E�\���F�s��R�[�h
	 * �E�\���F�^�C�v
	 * �E�\���FSSID�l
	 * �E�\���F�Í����ۗL�������
	 * �E�\���F�N�����iYYYYMMDDHHMISS�j
	 * �E�\���FGUID
	 * �E�\���FID
	 * �E�\���FPASS
     * �E���c�����X�g<BR>
     * �E�]�͎c�����X�g<BR>
     * �E���Y�]���z�ꗗ<BR>
     * �E������p�Í����p�X���[�h<BR>
     * �E�d�q��URL<BR>
     * �E���Z�敪 <BR>
     * �E���T�[�r�X���X�g<BR>
     * �E�\���F���FX���O�C��ID<BR>
     * �E�\���F���T�[�r�X�\����<BR>
     * �E�\���F�����ŋ敪<BR>
     * �E�\���F�����ŋ敪�i���N�j<BR>
     * �E�\���F�M�p�ŋ敪<BR>
     * �E�\���F�M�p�ŋ敪�i���N�j<BR>
     * �E�\���FCD�L�[<BR>
	 * <BR>
	 * ��v���Ȃ��ꍇ�Afalse��ԋp����
	 *  
	 * @@param l_strWord
	 * @@return
	 */
    private boolean validateWord(String l_strWord)
    {
        if (!(WEB3SrvRegiReservedWordDef.RESERVED_WORD_INSTITUTION_CODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_BRANCH_CODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_MAIN_ACCOUNT_CODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_PRODUCT_CODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_ENCRYPTION_ACCOUNT_CODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_HASH_CALC_VALUE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_TOKEN.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_ORDER_CHANEL.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_TRADER.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_MARGIN_ACCOUNT_DIV.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_FUOP_OSE_ACCOUNT_DIV.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_ACCOUNT_NAME.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_YYYYMMDD.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_YYYYMMDDHHMM.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_APPLI_EXPIRE_DATE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_HASH_ELEMENT_1.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_HASH_ELEMENT_2.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_TRADER_NAME.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV_END.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_HSTR.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_FUNDTYPE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_FUNDCODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_DELYEAR.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_DELMONTH.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_PUTCALL.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_STRIKEPRC.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_TRADETYPE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_BUYSELLFLAG.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_STKEXCODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_YYYYMMDDHHMM_2.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_HASH_ACCOUNT_ID.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_MARKET_CODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_TYPE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_SSID_VALUE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_ENCRYPTION_MF_ASSET.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_YEAR_MONTH_DAY.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_GUID.equals(l_strWord)
			|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_ID.equals(l_strWord)
			|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_PASS.equals(l_strWord)
			|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_BOND_BALANCE_LIST.equals(l_strWord)
        	|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_TRADINGPOWER_BALANCE_LIST.equals(l_strWord)
        	|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_STOCK_APPRAISAL_VALUE_INSPECTION.equals(l_strWord)
        	|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_BOND_ENCRYPT_PASS.equals(l_strWord)
        	|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_DENSHI_BATO_URL.equals(l_strWord)
        	|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_RESIDENT.equals(l_strWord)
        	|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_INFORMATION_SERVICE_LIST.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_OSE_LOGINID.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_OTHER_SRV_REGI_STATUS.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_EQUITY_TAXTYPE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_EQUITY_TAXTYPE_N.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_MARGIN_TAXTYPE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_MARGIN_TAXTYPE_N.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_CD_KEY.equals(l_strWord)))
        {
            return false;
        }
        else
        {
            return true;
        }

    }
}
@
