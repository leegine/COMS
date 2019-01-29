head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeCancelNotifyDataAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������ʒm�f�[�^�A�_�v�^(WEB3EquityChangeCancelNotifyDataAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 �������F(SRA) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.util.WEB3LogUtility;

/**
 * �i������������ʒm�f�[�^�A�_�v�^�j�B
 * @@author �������F
 * @@version 1.0
 */
public class WEB3EquityChangeCancelNotifyDataAdapter
{
    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityChangeCancelNotifyDataAdapter.class);

    /**
     * ������������ʒm�L���[Params<BR>
     */
    private HostEqtypeOrderClmdReceiptParams hostEqtypeOrderClmdReceiptParams;

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    private WEB3EquityChangeCancelNotifyDataAdapter()
    {
    }

    /**
     * (create)<BR>
     * <BR>
     * ������������ʒm�f�[�^�A�_�v�^�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�������B<BR>
     * �Q�j�@@���������C���X�^���X�Ɉ����̃L���[�f�[�^���Z�b�g����B<BR>
     * �R�j�@@�C���X�^���X��ԋp����B<BR>
     * <BR>
     * �i�f�t�H���g�R���X�g���N�^��private�Ƃ��A�{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j<BR>
     * <BR>
     * @@param l_equityReceiveChangeCancelQueueParams - (������������ʒm�L���[Params)<BR>
     * �y������������ʒm�L���[�e�[�u���z�f�[�^�I�u�W�F�N�g
     * @@return WEB3EquityChangeCancelNotifyDataAdapter
     */
    public static WEB3EquityChangeCancelNotifyDataAdapter create(
        HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams)
    {
        final String STR_METHOD_NAME = "create(HostEqtypeOrderClmdReceiptParams)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�{�C���X�^���X�𐶐�������B
        WEB3EquityChangeCancelNotifyDataAdapter l_adapter =
            new WEB3EquityChangeCancelNotifyDataAdapter();

        // �Q�j�@@���������C���X�^���X�Ɉ����̃L���[�f�[�^���Z�b�g����B
        l_adapter.hostEqtypeOrderClmdReceiptParams =
            l_hostEqtypeOrderClmdReceiptParams;

        log.exiting(STR_METHOD_NAME);
        // �R�j�@@�C���X�^���X��ԋp����B
        return l_adapter;
    }

    /**
     * (is�w�l)<BR>
     * <BR>
     * ������������ʒm�L���[Params.������w�l��0�̏ꍇ�Afalse��Ԃ��B<BR>
     * ��L�ȊO�̏ꍇ�́Atrue��Ԃ��B<BR>
     * @@return boolean
     */
    public boolean isLimitPrice()
    {
        if (hostEqtypeOrderClmdReceiptParams.getModifiedLimitPrice() == 0.0D)
        {
            return false;
        }

        return true;
    }

    /**
     * (is���s)<BR>
     * <BR>
     * this.is�w�l() == true�̏ꍇ��false���A<BR>
     * this.is�w�l() == false�̏ꍇ��true���A�ԋp����B<BR>
     * @@return boolean
     */
    public boolean isMarketOrder()
    {
        return (this.isLimitPrice() == false);
    }

    /**
     * (get���s����)<BR>
     * <BR>
     * �y������������ʒm�L���[�e�[�u���z�����㎷�s����(SONAR)�ɉ�����<BR>
     * EqTypeExecutionConditionType��Ԃ��B<BR>
     * <BR>
     * �g�����������}�l�[�W��.get���s����(<BR>
     * �@@������������ʒm�L���[Params.�����㎷�s����(SONAR))��delegate����B<BR>
     * @@return EqTypeExecutionConditionType
     * @@throws WEB3BaseException
     */
    public EqTypeExecutionConditionType getExecCondType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecCondType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        return l_orderManager.getExecutionConditionType(hostEqtypeOrderClmdReceiptParams.getModifiedExecutionType());
    }

    /**
     * (get�l�i����)<BR>
     * <BR>
     * �y����������������ʒm�L���[�e�[�u���z������l�i����(SONAR)�ɉ�����<BR>
     * WEB�V�ɂ�����l�i������Ԃ��B<BR>
     * <BR>
     * �g�����������}�l�[�W��.get�l�i����(������������ʒm�L���[Params.�l�i����(SONAR))��<BR>
     * delegate����B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getPriceConditionType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPriceConditionType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        return l_orderManager.getPriceConditionType(hostEqtypeOrderClmdReceiptParams.getModifiedPriceConditionType());
    }
}
@
