head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesOpenContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�敨�V�K�����̓T�[�r�XImpl(WEB3ToSuccFuturesOpenContractInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 ���n(���u) �V�K�쐬���f��256
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractInputServiceImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�敨�V�K�����̓T�[�r�XImpl)<BR>
 * �i�A���j�����w���敨�V�K�����̓T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3ToSuccFuturesOpenContractInputServiceImpl extends WEB3FuturesOpenContractInputServiceImpl
    implements WEB3ToSuccFuturesOpenContractInputService
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesOpenContractInputServiceImpl.class);


    /**
     * @@roseuid 47D6593302EE
     */
    public WEB3ToSuccFuturesOpenContractInputServiceImpl()
    {

    }

    /**
     * �i�A���j�����w���敨�V�K�����̓T�[�r�X���������{����B<BR>
     * <BR>
     * this.create���͉�ʂ��R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A93ADF0063
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

        //this.create���͉�ʂ��R�[������
        //����
        //���N�G�X�g
        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccFuturesOpenInputRequest)
        {
            l_response = this.createInput((WEB3SuccFuturesOpenInputRequest)l_request);
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

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create���͉��)<BR>
     * �i�A���j�����w���敨�V�K�����͉�ʂ�\������B<BR>
     * <BR>
     * �u�i�i�A���j�敨�V�K������)���͉�ʕ\���f�[�^�擾�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccFuturesOpenInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A93ADF0072
     */
    protected WEB3SuccFuturesOpenInputResponse createInput(WEB3SuccFuturesOpenInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createInput(WEB3SuccFuturesOpenInputRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();

        //�e�����̒����P�ʃI�u�W�F�N�g���擾����
        //�����͈ȉ��̒ʂ�ɃZ�b�g����
        //�i�e�����j����ID�F�@@���N�G�X�g.�A���������ʏ��̓�����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(Long.parseLong(l_request.succCommonInfo.parentOrderId));

        //�⏕�������擾����
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)getSubAccount();

        //�A���������ʂ̃`�F�b�N���s��
        //�����͈ȉ��̒ʂ�ɃZ�b�g����
        //�⏕�����F�@@this.get�⏕����()
        //�����^�C�v�F�@@"�敨�I�v�V����"
        //�敨�^�I�v�V�����敪�F�@@"�敨"
        //�A����������敪�F�@@���N�G�X�g.�A���������ʏ��.�A����������敪
        //�e�����̒����P�ʁF�@@�擾�����e�����̒����P�ʃI�u�W�F�N�g
        l_orderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.FUTURES,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //�A�������ő�ݒ萔�𒴉߂��Ȃ����ǂ������`�F�b�N����
        //�e�����̒����P�ʁF�@@�擾�����e�����̒����P�ʃI�u�W�F�N�g
        l_orderManager.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        //super�N���X�̓��͉�ʕ\�����\�b�h���R�[������
        //�����͈ȉ��̒ʂ�ɃZ�b�g����
        //���N�G�X�g�f�[�^�F�����̃��N�G�X�g�f�[�^
        WEB3SuccFuturesOpenInputResponse l_response = (WEB3SuccFuturesOpenInputResponse)super.createInput(l_request);

        //���Δ���������ǂ����𔻒肷��
        //�����͈ȉ��̒ʂ�ɃZ�b�g����
        //�A����������敪�F�@@���N�G�X�g.�A���������ʏ��̓�����
        //�e�����̒����P�ʁF�@@�擾�����e�����̒����P�ʃI�u�W�F�N�g
        boolean l_blnReversingTrade = l_orderManager.isReversingTrade(
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //�v���p�e�B�Z�b�g
        //���u�i�A���j�����w���敨�V�K���������͉�ʃ��X�|���X�v�ɂ̂ݑ��݂���v���p�e�B
        //�������ʁF�@@
        //�@@���A�������}�l�[�W��.is���Δ������()==true�̏ꍇ
        //�@@�@@�@@�i�e�����̒����P��.�������ʁj���Z�b�g�B
        //�@@���A�������}�l�[�W��.is���Δ������()==false�̏ꍇ
        //�@@�@@�@@null���Z�b�g�B
        if (l_blnReversingTrade)
        {
            l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnit.getQuantity());
        }
        else
        {
            l_response.orderQuantity = null;
        }

        //-----------------------------------------------------------------
        //���قȂ�l���Z�b�g����v���p�e�B
        //
        //���s�����ꗗ�F�@@"������"�݂̂��Z�b�g�B
        //���������敪�ꗗ�F�@@"�w��Ȃ�"�݂̂��Z�b�g�B
        //�v�w�l�p���s�����ꗗ�F�@@null���Z�b�g�B
        //-----------------------------------------------------------------
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};
        l_response.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};
        l_response.wlimitExecCondList = null;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
