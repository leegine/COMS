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
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ¤isêÊîñ (WEB3AdminORProductMarketNumberInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/11 êÏ (u) dlÏXNo.55C³
*/
package webbroker3.adminorderexecinquiry.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (¤isêÊîñ)<BR>
 * <BR>
 * ¤isêÊîñNX<BR>
 * <BR>
 * WEB3AdminORProductMarketNumberInfoUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORProductMarketNumberInfoUnit extends Message
{
    /**
     * WvÎÛ¤iæª<BR>
     * <BR>
     * 0F@@»¨®<BR>
     * 1F@@Mpæø<BR>
     * 2F@@®~j<BR>
     * 3F@@O®<BR>
     * 4F@@IvV<BR>
     * 5F@@æ¨<BR>
     * 6F@@M<BR>
     * 7F@@F<BR>
     * 8F@@MMF<BR>
     * 9F@@Â <BR>
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
     * (sêR[h)<BR>
     * <BR>
     * sêR[h<BR>
     * <BR>
     * marketCode<BR>
     * <BR>
     */
    public String marketCode;

    /**
     * (¶)<BR>
     * <BR>
     * ¶<BR>
     * <BR>
     * orderNumber<BR>
     * <BR>
     */
    public String orderNumber;

    /**
     * (ñè)<BR>
     * <BR>
     * ñè<BR>
     * <BR>
     * executeNumber<BR>
     * <BR>
     */
    public String executeNumber;

    /**
     * (¤isêÊîñ)<BR>
     * <BR>
     * RXgN^<BR>
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
