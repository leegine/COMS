head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORProductMarketNumberInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品市場別件数情報 (WEB3AdminORProductMarketNumberInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/11 齊珂 (中訊) 仕様変更No.55修正
*/
package webbroker3.adminorderexecinquiry.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (商品市場別件数情報)<BR>
 * <BR>
 * 商品市場別件数情報クラス<BR>
 * <BR>
 * WEB3AdminORProductMarketNumberInfoUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORProductMarketNumberInfoUnit extends Message
{
    /**
     * 集計対象商品区分<BR>
     * <BR>
     * 0：　@現物株式<BR>
     * 1：　@信用取引<BR>
     * 2：　@株式ミニ投資<BR>
     * 3：　@外国株式<BR>
     * 4：　@オプション<BR>
     * 5：　@先物<BR>
     * 6：　@投信<BR>
     * 7：　@中国F<BR>
     * 8：　@MMF<BR>
     * 9：　@債券 <BR>
     * <BR>
     * -----<English>--------------<BR>
     * <BR>
     * sumProductType<BR>
     * 0: Def.EQUITY<BR>
     * 1: Def.MARGIN<BR>
     * 2: Def.MINI_STOCK<BR>
     * 3: Def.FORIGN_STOCK<BR>
     * 4: Def.OPTION<BR>
     * 5: Def.FUTURE<BR>
     * 6: Def.MF<BR>
     * 7: Def.MIDIUM_TERM_GOV_FUND<BR>
     * 8: Def.MMF_SET<BR>
     * 9: Def.BOND
     * <BR>
     */
    public String sumProductType;

    /**
     * (市場コード)<BR>
     * <BR>
     * 市場コード<BR>
     * <BR>
     * marketCode<BR>
     * <BR>
     */
    public String marketCode;

    /**
     * (注文件数)<BR>
     * <BR>
     * 注文件数<BR>
     * <BR>
     * orderNumber<BR>
     * <BR>
     */
    public String orderNumber;

    /**
     * (約定件数)<BR>
     * <BR>
     * 約定件数<BR>
     * <BR>
     * executeNumber<BR>
     * <BR>
     */
    public String executeNumber;

    /**
     * (商品市場別件数情報)<BR>
     * <BR>
     * コンストラクタ<BR>
     * <BR>
     * WEB3AdminORProductMarketNumberInfoUnit<BR>
     * <BR>
     * constructor<BR>
     * <BR>
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORProductMarketNumberInfoUnit
     * @@roseuid 419B4D56023E
     */
    public WEB3AdminORProductMarketNumberInfoUnit()
    {

    }
}
@
