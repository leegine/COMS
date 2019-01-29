head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccTradeStopInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq�����~��� (WEB3AdminPMAccTradeStopInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.util.WEB3LogUtility;

import webbroker3.eqtypeadmin.define.WEB3AdminEquityTradeStopDivDef;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * (�ڋq�����~���)<BR>
 * <BR>
 * �ڋq�����~���N���X<BR>
 * <BR>
 * WEB3AdminPMAccTradeStopInfoUnit<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccTradeStopInfoUnit extends Message
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMAccTradeStopInfoUnit.class);

    /**
     * �i������ʁj<BR>
     * <BR>
     * �������<BR>
     * <BR>
     * 1�F�@@��������������<BR>
     * 2�F�@@��������������<BR>
     * 3�F�@@�����M�p�V�K��������<BR>
     * 4�F�@@�����M�p�V�K��������<BR>
     * 5�F�@@�����M�p�����ԍϒ���<BR>
     * 6�F�@@�����M�p�����ԍϒ���<BR>
     * 7�F�@@�����M�p��������<BR>
     * 8�F�@@�����M�p���n����<BR>
     * 101�F�@@�����~�j��������<BR>
     * 102�F�@@�����~�j��������<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * orderType<BR>
     * <BR>
     * 1: Def.EQUITY_BUY<BR>
     * 2: Def.EQUITY_SELL<BR>
     * 3: Def.MARGIN_LONG<BR>
     * 4: Def.MARGIN_SHORT<BR>
     * 5: Def.CLOSE_MARGIN_LONG<BR>
     * 6: Def.CLOSE_MARGIN_SHORT<BR>
     * 7: Def.CLOSE_MARGIN_SHORT<BR>
     * 8: Def.SWAP_MARGIN_SHORT<BR>
     * 101: Def.MINI_STOCK_BUY<BR>
     * 102: Def.MINI_STOCK_SELL<BR>
     * <BR>
     */
    public String orderType;

    /**
     * �i�����~�敪�j<BR>
     * <BR>
     * �����~�敪<BR>
     * <BR>
     * 0�F�@@����\<BR>
     * 1�F�@@�����~<BR>
     * <BR>
     * --------<English>---------<BR>
     * <BR>
     * tradeStopDiv<BR>
     * <BR>
     * 0: Def.NORMAL<BR>
     * 1: Def.SUSPENTION<BR>
     * <BR>
     */
    public String tradeStopDiv;

    /**
     * �i�ύX������~�敪�j<BR>
     * <BR>
     * �ύX������~�敪<BR>
     * <BR>
     * 0�F�@@����\<BR>
     * 1�F�@@�����~<BR>
     * <BR>
     * --------<English>---------<BR>
     * <BR>
     * aftTradeStopDiv<BR>
     * <BR>
     * 0: Def.NORMAL<BR>
     * 1: Def.SUSPENTION<BR>
     * <BR>
     */
    public String aftTradeStopDiv = null;

    /**
     * (�R���X�g���N�^)<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
       eqtypeadmin.message.WEB3AdminPMAccTradeStopInfoUnit
     * @@roseuid 41ABE2DA0222
     */
    public WEB3AdminPMAccTradeStopInfoUnit()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j������ʃ`�F�b�N<BR>
     * �@@�P�|�P�jthis.������� == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u������ʂ�null�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01438<BR>
     * <BR>
     * �@@�P�|�Q�jthis.������ʂ��ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u������ʂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�E"1�F��������������"<BR>
     * �@@�@@�@@�@@�@@�E"2�F��������������"<BR>
     * �@@�@@�@@�@@�@@�E"3�F�����M�p�V�K��������"<BR>
     * �@@�@@�@@�@@�@@�E"4�F�����M�p�V�K��������"<BR>
     * �@@�@@�@@�@@�@@�E"5�F�����M�p�����ԍϒ���"<BR>
     * �@@�@@�@@�@@�@@�E"6�F�����M�p�����ԍϒ���"<BR>
     * �@@�@@�@@�@@�@@�E"7�F�����M�p��������"<BR>
     * �@@�@@�@@�@@�@@�E"8�F�����M�p���n����"<BR>
     * �@@�@@�@@�@@�@@�E"101�F�����~�j��������"<BR>
     * �@@�@@�@@�@@�@@�E"102�F�����~�j��������"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01439<BR>
     * <BR>
     * �Q�j�����~�敪�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�����~�敪 == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����~�敪��null�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01894<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�����~�敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����~�敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�E"0�F����\"<BR>
     * �@@�@@�@@�@@�@@�@@�E"1�F�����~"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01421<BR>
     * <BR>
     * �R�j�ύX������~�敪�`�F�b�N<BR>
     * �@@this.�ύX������~�敪 != null�̏ꍇ�A<BR>
     * �@@�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�jthis.�ύX������~�敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����~�敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�E"0�F����\"<BR>
     * �@@�@@�@@�@@�@@�@@�E"1�F�����~"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01421<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1) orderType check<BR>
     * �@@1-1) If this.orderType == null<BR>
     * �@@�@@�@@�@@�@@Throw the exception "orderType is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01438<BR>
     * <BR>
     * �@@1-2)If this.orderType is not either of the following values<BR>
     * �@@�@@�@@�@@�@@Throw the exception "orderType is a undefined value"<BR>
     * �@@�@@�@@�@@�@@�E"1: Def.EQUITY_BUY"<BR>
     * �@@�@@�@@�@@�@@�E"2: Def.EQUITY_SELL"<BR>
     * �@@�@@�@@�@@�@@�E"3: Def.MARGIN_LONG"<BR>
     * �@@�@@�@@�@@�@@�E"4: Def.MARGIN_SHORT"<BR>
     * �@@�@@�@@�@@�@@�E"5: Def.CLOSE_MARGIN_LONG"<BR>
     * �@@�@@�@@�@@�@@�E"6: Def.CLOSE_MARGIN_SHORT"<BR>
     * �@@�@@�@@�@@�@@�E"7: Def.CLOSE_MARGIN_SHORT"<BR>
     * �@@�@@�@@�@@�@@�E"8: Def.SWAP_MARGIN_SHORT"<BR>
     * �@@�@@�@@�@@�@@�E"101: Def.MINI_STOCK_BUY"<BR>
     * �@@�@@�@@�@@�@@�E"102: Def.MINI_STOCK_SELL"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01439<BR>
     * <BR>
     * 2) tradeStopDiv check<BR>
     * �@@2-1) If this.tradeStopDiv == null<BR>
     * �@@�@@�@@�@@�@@Throw the exception "tradeStopDiv is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01894<BR>
     * <BR>
     * �@@2-2) If this.tradeStopDiv is neither of the following values<BR>
     * �@@�@@�@@�@@�@@Throw the exception "tradeStopDiv is an undefined value"<BR>
     * �@@�@@�@@�@@�@@�@@�E"0: Def.NORMAL"<BR>
     * �@@�@@�@@�@@�@@�@@�E"1: Def.SUSPENTION"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01421<BR>
     * <BR>
     * 3)aftTradeStopDiv check<BR>
     * �@@If this.aftTradeStopDiv != null<BR>
     * �@@Check the followings<BR>
     * �@@3-1) If this.aftTradeStopDiv is neither of the following values<BR>
     * �@@�@@�@@�@@�@@Throw the exception "tradeStopDiv is an undefined value"<BR>
     * �@@�@@�@@�@@�@@�@@�E"0: Def.NORMAL"<BR>
     * �@@�@@�@@�@@�@@�@@�E"1: Def.SUSPENTION"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01421<BR>
     * <BR>
     * @@roseuid 4185F4B40021
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 if orderType is null, throw Exception.
        if (orderType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01438,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            //1-2 if orderType not equal to any of WEB3AdminEquityOrderTypeDef, throw Exception.
            if ((!String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.SWAP_MARGIN_LONG.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.MINI_STOCK_BUY.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.MINI_STOCK_SELL.intValue()).equals(orderType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01439,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-1 if tradeStopDiv is null, throw Exception
        if (tradeStopDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01894,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 2-2 if tradeStopDiv is Not NORMAL & SUSPENTION, throw Exception.
            if ((!WEB3AdminEquityTradeStopDivDef.NORMAL.equals(tradeStopDiv))
                && (!WEB3AdminEquityTradeStopDivDef.SUSPENTION.equals(tradeStopDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01421,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 if aftTradeStopDiv is Not NORMAL & SUSPENTION, throw Exception.
        if (aftTradeStopDiv != null)
        {
            if ((!WEB3AdminEquityTradeStopDivDef.NORMAL.equals(aftTradeStopDiv))
                && (!WEB3AdminEquityTradeStopDivDef.SUSPENTION.equals(aftTradeStopDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01421,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
