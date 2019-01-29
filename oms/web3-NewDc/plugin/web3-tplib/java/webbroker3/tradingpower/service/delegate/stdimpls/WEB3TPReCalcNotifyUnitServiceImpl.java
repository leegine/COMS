head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPReCalcNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͌v�Z�ʒm�ꌏ�T�[�r�XImpl(WEB3TPReCalcNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.WEB3TPTradingPowerUpd;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyParams;
import webbroker3.tradingpower.define.WEB3TPRealCalcFlagDef;
import webbroker3.tradingpower.define.WEB3TPStatusDef;
import webbroker3.tradingpower.service.delegate.WEB3TPReCalcNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;

/**
 * (�]�͌v�Z�ʒm�ꌏ�T�[�r�XImpl)
 */
public class WEB3TPReCalcNotifyUnitServiceImpl implements WEB3TPReCalcNotifyUnitService
{
    /**
     * �i���O�o�̓��[�e�B���e�B)�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPReCalcNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 423547C1020E
     */
    public WEB3TPReCalcNotifyUnitServiceImpl()
    {

    }

    /**
     * (notify�]�͍Čv�Z)
     * @@param l_subAccount - (�⏕����)
     * @@param �]�͍Čv�Z�L���[Params - (�]�͍Čv�Z�L���[Params)
     * @@roseuid 42353FAB0394
     */
    public void notifyReCalc(
        WEB3GentradeSubAccount l_subAccount,
        TpCalcResultExecNotifyParams l_params)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME =
            "notifyReCalc(WEB3GentradeSubAccount, TpCalcResultExecNotifyParams)";
        log.entering(STR_METHOD_NAME);

        /*
         * �������b�N����
         */
        l_subAccount.serializeOperationsWithWait();

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        /*
         * �]�͌v�Z�����𐶐�
         */
        WEB3TPCalcCondition l_calcCond = null;

        //�����v�Z�t���O���O���I�l�]���̏ꍇ
        if (l_params.real_calc_flag.equals(WEB3TPRealCalcFlagDef.CLOSING_PRICE) == true)
        {
            l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
        }
        //�����v�Z�t���O�������]���̏ꍇ
        else
        {
            l_calcCond = WEB3TPCalcCondition.createCalcConditionQuote(l_subAccount);
        }

        /*
         * �]�͍X�V
         */
        WEB3TPTradingPowerUpd l_tpUpd =
            new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, null);

        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //�]�͍X�V���e���擾
            List l_updResults = l_tpUpd.calcTradingpowerUpdResultEquity();
            //�]�͍X�V���e���e�[�u���ɑ}��
            l_tpUpd.saveTradingpowerUpdResultEquity(l_updResults);
        }
        //�M�p�ڋq�̏ꍇ
        else
        {
            //�]�͍X�V���e���擾
            List l_updResults =
                l_tpUpd.calcTradingpowerUpdResultMargin(l_params.mark_to_market_div);
            //�]�͍X�V���e���e�[�u���ɑ}��
            l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
        }

        /*
         * �V�X�e���^�C�v�X�^���v���擾
         */
        TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();
        Timestamp l_systemTimeStamp = tradingSystem.getSystemTimestamp();
        
        /*
         * �]�͌v�Z�L���[�e�[�u��.�����敪��1�F�����ςɍX�V����B
         */
        l_params.setStatus(WEB3TPStatusDef.DEAL);
        l_params.setLastUpdatedTimestamp(l_systemTimeStamp);
        QueryProcessor l_processor = Processors.getDefaultProcessor();
        l_processor.doUpdateQuery(l_params);

        log.exiting(STR_METHOD_NAME);
    }
}
@
