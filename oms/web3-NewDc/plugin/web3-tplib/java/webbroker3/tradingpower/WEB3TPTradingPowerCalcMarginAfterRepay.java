head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerCalcMarginAfterRepay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ԍό㎑�Y�]�͏��(WEB3TPTradingPowerCalcMarginAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) �V�K�쐬
*/

package webbroker3.tradingpower;

import java.util.List;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ԍό㎑�Y�]�͏��)
 */
public class WEB3TPTradingPowerCalcMarginAfterRepay extends WEB3TPTradingPowerCalcMargin
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerCalcMarginAfterRepay.class);

    /**
     * (�������ԍό��ϑ��v)
     */
    protected double[] orderRepaySettleProfitLoss;

    /**
     * (���񔭒����ԍό��ϑ��v)
     */
    protected double[] currOrderRepaySettleProfitLoss;

    /**
     * @@roseuid 41E383F20299
     */
    public WEB3TPTradingPowerCalcMarginAfterRepay()
    {

    }

    /**
     * (�R���X�g���N�^)<BR>
     * <BR>
     * �������e�p�����[�^�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_calcResult - �i�]�͌v�Z���ʁj
     * @@param l_calcCondition - (�]�͌v�Z����)
     * @@param l_orderRepaySettleProfitLoss - (�������ԍό��ϑ��v)
     * @@param l_currOrderRepaySettleProfitLoss - (���񔭒����ԍό��ϑ��v)
     * @@roseuid 41D0B26F019A
     */
    public WEB3TPTradingPowerCalcMarginAfterRepay(
        List l_calcResult,
        WEB3TPCalcCondition l_calcCondition,
        double[] l_orderRepaySettleProfitLoss,
        double[] l_currOrderRepaySettleProfitLoss)
    {
        super(l_calcResult, l_calcCondition);

        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerCalcMarginAfterRepay(List, WEB3TPCalcCondition, double[], double[])";
        log.entering(STR_METHOD_NAME);
        
        this.orderRepaySettleProfitLoss = l_orderRepaySettleProfitLoss;
        this.currOrderRepaySettleProfitLoss = l_currOrderRepaySettleProfitLoss;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�������ԍό��ϑ��v)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�������ԍό��ϑ��v�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u�������ԍό��ϑ��v�v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�������ԍό��ϑ��v[T+n]<BR>
     * <BR>
     * @@param l_intSpecifiedPoint - (�w���)
     * @@return double
     * @@roseuid 41D0B1F201F8
     */
    public double getOrderRepaySettleProfitLoss(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getOrderRepaySettleProfitLoss(int)";

        /*
         * �����`�F�b�N���s���B
         */
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�w����́u�������ԍό��ϑ��v�v��ԋp����
        log.exiting(STR_METHOD_NAME);
        return this.orderRepaySettleProfitLoss[l_intSpecifiedPoint];
    }

    /**
     * (get���񔭒����ԍό��ϑ��v)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���񔭒����ԍό��ϑ��v�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u���񔭒����ԍό��ϑ��v�v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.���񔭒����ԍό��ϑ��v[T+n]<BR>
     * <BR>
     * @@param l_intSpecifiedPoint - (�w���)
     * @@return double
     * @@roseuid 41D0B1FA03DC
     */
    public double getCurrOrderRepaySettleProfitLoss(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getCurrOrderRepaySettleProfitLoss(int)";

        /*
         * �����`�F�b�N���s���B
         */
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�w����́u���񔭒����ԍό��ϑ��v�v��ԋp����
        log.exiting(STR_METHOD_NAME);
        return this.currOrderRepaySettleProfitLoss[l_intSpecifiedPoint];
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("calcResultMargin", this.getCalcResultMargin().toString())
            .append("calcResultDetailMargin", this.getCalcResultDetailMargin().toString())
            .append("orderRepaySettleProfitLoss[0]", this.getOrderRepaySettleProfitLoss(0))
            .append("orderRepaySettleProfitLoss[1]", this.getOrderRepaySettleProfitLoss(1))
            .append("orderRepaySettleProfitLoss[2]", this.getOrderRepaySettleProfitLoss(2))
            .append("orderRepaySettleProfitLoss[3]", this.getOrderRepaySettleProfitLoss(3))
            .append("orderRepaySettleProfitLoss[4]", this.getOrderRepaySettleProfitLoss(4))
            .append("orderRepaySettleProfitLoss[5]", this.getOrderRepaySettleProfitLoss(5))
            .append("currOrderRepaySettleProfitLoss[0]", this.getCurrOrderRepaySettleProfitLoss(0))
            .append("currOrderRepaySettleProfitLoss[1]", this.getCurrOrderRepaySettleProfitLoss(1))
            .append("currOrderRepaySettleProfitLoss[2]", this.getCurrOrderRepaySettleProfitLoss(2))
            .append("currOrderRepaySettleProfitLoss[3]", this.getCurrOrderRepaySettleProfitLoss(3))
            .append("currOrderRepaySettleProfitLoss[4]", this.getCurrOrderRepaySettleProfitLoss(4))
            .append("currOrderRepaySettleProfitLoss[5]", this.getCurrOrderRepaySettleProfitLoss(5))
            .toString();
    }
}
@
