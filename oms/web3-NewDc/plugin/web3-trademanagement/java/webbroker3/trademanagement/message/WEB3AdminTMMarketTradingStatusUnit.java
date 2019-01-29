head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.21.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMMarketTradingStatusUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �s��ʎ����(WEB3AdminTMMarketTradingStatusUnit.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.define.WEB3AdminTMTradeStopDivDef;

/**
 * �i�s��ʎ���󋵁j<BR>
 * <BR>
 * �s��ʎ���󋵃N���X<BR>
 * <BR>
 * WEB3AdminTMMarketTradingStatusUnit<BR>
 * <BR>
 * WEB3AdminTMMarketTradingStatusUnit class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMMarketTradingStatusUnit extends Message
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMMarketTradingStatusUnit.class);
    /**
     * �i�s��R�[�h�j<BR>
     * <BR>
     * �s��R�[�h<BR>
     * <BR>
     * 0�F�@@���̑�<BR>
     * 1�F�@@����<BR>
     * 2�F�@@���<BR>
     * 3�F�@@���É�<BR>
     * 6�F�@@����<BR>
     * 8�F�@@�D�y<BR>
     * 9�F�@@NNM<BR>
     * 10�F�@@JASDAQ<BR>
     * (WEB3MarketCodeDef.java�ɂĒ�`)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * marketCode<BR>
     * <BR>
     * 0�F�@@Def.DEFAULT<BR>
     * 1�F�@@Def.TOKYO<BR>
     * 2�F�@@Def.OSAKA<BR>
     * 3�F�@@Def.NAGOYA<BR>
     * 6�F�@@Def.FUKUOKA<BR>
     * 8�F�@@Def.SAPPORO<BR>
     * 9�F�@@Def.NNM<BR>
     * 10�F�@@Def.JASDAQ<BR>
     * (It defines it with WEB3MarketCodeDef.java. )<BR>
     */
    public String marketCode;

    /**
     * �i�s�ꖼ�j<BR>
     * <BR>
     * �s�ꖼ<BR>
     * <BR>
     * marketName<BR>
     */
    public String marketName;

    /**
     * �i�����~�敪�j<BR>
     * <BR>
     * �����~�敪<BR>
     * <BR>
     * 0�F�@@����\<BR>
     * 1�F�@@�����~<BR>
     * 2�F�@@�戵�s��<BR>
     * <BR>
     * ��AP�w�ōŐVDB�f�[�^���Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * tradeStopDiv<BR>
     * <BR>
     * 0 : Def.NORMAL<BR>
     * 1 : Def.SUSPENTION<BR>
     * 2 : "DISABLE"<BR>
     * <BR>
     * ��The latest DB data is set in the AP layer. <BR>
     * <BR>
     */
    public String tradeStopDiv;

    /**
     * �i�ύX������~�敪�j<BR>
     * �ύX��̎����~�敪
     * <BR>
     * 0�F�@@����\<BR>
     * 1�F�@@�����~<BR>
     * 2�F�@@�戵�s��<BR>
     * <BR>
     * ��PR�w�ł̓��͒l���Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * afterTradeStopDiv<BR>
     * <BR>
     * 0 : Def.NORMAL<BR>
     * 1 : Def.SUSPENTION<BR>
     * 2 : "DISABLE"<BR>
     * <BR>
     * ��The input value in PR layer is set.<BR>
     */
    public String afterTradeStopDiv = null;

    /**
     * �i�R���X�g���N�^�j<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * @@roseuid 41776D3A010E
     */
    public WEB3AdminTMMarketTradingStatusUnit()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�s��R�[�h�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�s��R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00443<BR>
     * <BR>
     * �Q�j�����~�敪�`�F�b�N<BR>
     * �@@this.�����~�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�jthis.�����~�敪���ȉ��̒l�̂��Â�ɂ��Y�����Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����~�敪�G���[(����`�̒l)�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�E"0�F����\"<BR>
     * �@@�@@�@@�@@�@@�E"1�F�����~"<BR>
     * �@@�@@�@@�@@�@@�E"2�F�戵�s��"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01421<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)marketCode check<BR>
     *   1-1)If this.marketCode == null<BR>
     *            Throw the following error [marketCode is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00443<BR>
     * <BR>
     * 2)tradeStopDiv check<BR>
     *   2-1)If this.tradeStopDiv != null<BR>
     *     2-1-1)If tradeStopDiv = 0 : Def.NORMAL<BR>
     *                 tradeStopDiv = 1 : Def.SUSPENTION<BR>
     *                 tradeStopDiv = 2 : "DISABLE"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01421<BR>
     * @@roseuid 41775BA50341
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 marketCode = null, throw Exception
        if (this.marketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 2-1 if tradeStopDiv != null, throw Exception.
        if (this.tradeStopDiv != null)
        {
            // 2-1-1 if tradeStopDiv != NORMAL, SUSPENTION, DISABLE, throw Exception.
            if ((!WEB3AdminTMTradeStopDivDef.NORMAL.equals(this.tradeStopDiv))
                && (!WEB3AdminTMTradeStopDivDef.SUSPENTION.equals(this.tradeStopDiv))
                && (!WEB3AdminTMTradeStopDivDef.DISABLE.equals(this.tradeStopDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01421,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}@
