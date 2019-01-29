head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerReCalcServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͍Čv�Z�T�[�r�XImpl(WEB3TPTradingPowerReCalcServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/25 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyParams;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.define.WEB3TPOccurredDivDef;
import webbroker3.tradingpower.define.WEB3TPRealCalcFlagDef;
import webbroker3.tradingpower.define.WEB3TPStatusDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�]�͍Čv�Z�T�[�r�XImpl)<BR>
 */
public class WEB3TPTradingPowerReCalcServiceImpl implements WEB3TPTradingPowerReCalcService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerReCalcServiceImpl.class);

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3TPTradingPowerReCalcServiceImpl()
    {
        final String STR_METHOD_NAME = "WEB3TPTradingPowerReCalcServiceImpl()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�]�͍Čv�Z)<BR>
     * <BR>
     *  �P�j�]�͌v�Z�L���[Params�𐶐����A�ȉ��̒l��ݒ肷��B<BR>
     * <BR>
     * �@@[�ݒ�l]�@@<BR>
     * �@@�@@�E�����h�c = ����.�⏕����.getAccountId()<BR>
     * �@@�@@�E�����敪 = 1:�Ɩ��A�v��<BR>
     * �@@�@@�E�����v�Z�t���O = 0:�O���I�l�]���ōČv�Z<BR>
     * �@@�@@�E�l�􂢋敪 = 0:�ʏ�<BR>
     *�@@ �@@�E�����敪 = 0:������<BR>
     * <BR>
     * �@@�Q�j���������A�]�͌v�Z�L���[Params���A�]�͌v�Z�L���[�e�[�u����Insert����B<BR>
     * <BR>
     * �@@�@@��QueryProcessor.doInsertQuery(Params)���R�[��<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@Params�F���������]�͌v�Z�L���[Params<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     */
    public void reCalcTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "WEB3TPTradingPowerReCalcServiceImpl.reCalcTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        /*
         * ����.�⏕������null�̏ꍇ
         */
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        /*
         * �V�X�e���^�C�v�X�^���v���擾
         */
        TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();
        Timestamp l_systemTimeStamp = tradingSystem.getSystemTimestamp();
        
        /*
         * �]�͌v�Z�L���[Params�𐶐�����
         */
        TpCalcResultExecNotifyParams l_execNotifyPrams = new TpCalcResultExecNotifyParams();
        //����ID
        l_execNotifyPrams.setAccountId(l_subAccount.getAccountId());
        //�����敪
        l_execNotifyPrams.setOccurredDiv(WEB3TPOccurredDivDef.WORK_APP);
        //�����v�Z�t���O
        l_execNotifyPrams.setRealCalcFlag(WEB3TPRealCalcFlagDef.CLOSING_PRICE);
        //�l�􂢋敪
        l_execNotifyPrams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
        //�����敪
        l_execNotifyPrams.setStatus(WEB3TPStatusDef.NOT_DEAL);
        //�쐬����
        l_execNotifyPrams.setCreatedTimestamp(l_systemTimeStamp);
        //�X�V����
        l_execNotifyPrams.setLastUpdatedTimestamp(l_systemTimeStamp);
        
        /*
         * ���������A�]�͌v�Z�L���[Params���A�]�͌v�Z�L���[�e�[�u����Insert����B
         */
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_execNotifyPrams);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
