head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ϕۏ؋��ێ�������i��ԁj�������쐬�ꌏ�T�[�r�XImpl(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/17 �И���(���u) �V�K�쐬 �d�l�ύX���f��No.179
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�������ϕۏ؋��ێ�������i��ԁj�������쐬�ꌏ�T�[�r�XImpl)<BR>
 * �������ϕۏ؋��ێ�������i��ԁj�������쐬�ꌏ�T�[�r�X�̎����N���X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl
    extends WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl
    implements WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl.class);

    /**
     * @@roseuid 462CA423039E
     */
    public WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl()
    {

    }

    /**
     * (validate���ʋ�������)<BR>
     * ����ۗL����ڋq���{�ؕs�����ǂ����̔�����s���B<BR>
     * <BR>
     * �P�j�@@�⏕�������擾���� <BR>
     * �@@�g���A�J�E���g�}�l�[�W��.get�⏕����()��call����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@����ID�F�@@����.����Row.getAccountId()�̖߂�l<BR>
     * �@@�@@�⏕����ID�F�@@����.����Row.getSubAccountId()�̖߂�l<BR>
     * <BR>
     * �Q�j�@@�]�̓`�F�b�N���s���B<BR>
     * �@@����]�̓T�[�r�X.validate���ʋ������ρ`��ԁ`()<BR>
     * �@@��call���A�߂�l��ԋp����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�⏕�����F�@@get�⏕����()�̖߂�l <BR>
     * <BR>
     * @@param l_eqtypeContractRow - (����Row)<BR>
     * ����Row
     * @@return WEB3TPContractForcedSettleResult
     * @@throws WEB3BaseException
     */
    public WEB3TPContractForcedSettleResult validateContractForcedSettle(EqtypeContractRow l_eqtypeContractRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateContractForcedSettle(EqtypeContractRow)";
        log.entering(STR_METHOD_NAME);

        if (l_eqtypeContractRow == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //�⏕�������擾����
        //�@@[����]
        //�@@�@@����ID�F�@@����.����Row.getAccountId()�̖߂�l
        //�@@�@@�⏕����ID�F�@@����.����Row.getSubAccountId()�̖߂�l
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_eqtypeContractRow.getAccountId(),
                    l_eqtypeContractRow.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //�]�̓`�F�b�N���s���B
        //�@@����]�̓T�[�r�X.validate���ʋ������ρ`��ԁ`()
        //�@@��call���A�߂�l��ԋp����B
        //�@@[����]
        //�@@�@@�⏕�����F�@@get�⏕����()�̖߂�l
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3TPContractForcedSettleResult l_contractForcedSettleResult =
            l_tradingPowerService.validateContractForcedSettleIntermission(l_subAccount);

        log.exiting(STR_METHOD_NAME);
        return l_contractForcedSettleResult;
    }
}
@
