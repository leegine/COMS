head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticOrderCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������Ǘ��Ғ�������X�V�C���^�Z�v�^(WEB3AdminBondDomesticOrderCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.239
*/
package webbroker3.bd;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

/**
 * (�������Ǘ��Ғ�������X�V�C���^�Z�v�^)<BR>
 * �������Ǘ��Ғ�������X�V�C���^�Z�v�^<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminBondDomesticOrderCancelUpdateInterceptor extends WEB3AdminBondDefaultInterceptor
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticOrderCancelUpdateInterceptor.class);

    /**
     * (�������Ǘ��Ғ�������X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44D84FAD015E
     */
    public WEB3AdminBondDomesticOrderCancelUpdateInterceptor()
    {

    }

    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * �������P�ʃe�[�u���X�V�B<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * ���ڐݒ���e�́ADB�X�V�d�l<BR>
     * �u����������i�����_�������j_�������P�ʃe�[�u��DB�X�V�d�l.xls�v<BR>
     * �u����������i����_�������j_�������P�ʃe�[�u��DB�X�V�d�l.xls�v<BR>
     * <BR>
     * �Q�ƁB<BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params)
    {
        final String STR_METHOD_NAME =
            " mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_params == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        //�P�j�g�����ڃZ�b�g
        //���ڐݒ���e�� �u����������i�����_�������j_�������P�ʃe�[�u��DB�X�V�d�l.xls�v�Q�ƁB
        //���ڐݒ���e�� �u����������i����_�������j_�������P�ʃe�[�u��DB�X�V�d�l.xls�v�Q�ƁB
        //2�F�����
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.CANCELED);

        //  �������P��.��������������ԊǗ�.get�������̏ꍇ�A
        //�@@  0�F�����M
        //  ����ȊO�̏ꍇ�A
        //�@@�@@2:���M�s�v
        try
        {
            if (WEB3DateUtility.compareToDay(
                WEB3DateUtility.getDate(l_params.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                WEB3GentradeTradingTimeManagement.getOrderBizDate()) < 0)
            {
                l_params.setHostSendDiv(WEB3HostSendDivDef.UNSEND);
            }
            else
            {
                l_params.setHostSendDiv(WEB3HostSendDivDef.NOT_SEND);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Ǘ���.get�Ǘ��҃R�[�h()
        l_params.setAdministratorCode(this.getAdministrator().getAdministratorCode());

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
