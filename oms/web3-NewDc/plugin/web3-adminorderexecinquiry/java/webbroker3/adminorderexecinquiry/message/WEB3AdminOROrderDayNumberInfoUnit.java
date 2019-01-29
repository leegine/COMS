head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOROrderDayNumberInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 発注日別件数情報 (WEB3AdminOROrderDayNumberInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (発注日別件数情報)<BR>
 * <BR>
 * 発注日別件数情報クラス<BR>
 * <BR>
 * WEB3AdminOROrderDayNumberInfoUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOROrderDayNumberInfoUnit extends Message
{
    /**
     * (発注日)<BR>
     * <BR>
     * 発注日<BR>
     * <BR>
     * ※日別の場合は、YYYYMMDD
     * 　@月別の場合は、YYYYMM<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderBizDate<BR>
     * If Def.DAILY, YYYYMMDD<BR>
     * If Def.MONTHLY, YYYYMM<BR>
     * <BR>
     */
    public String orderBizDate;

    /**
     * （注文経路別件数情報一覧）<BR>
     * <BR>
     */
    public WEB3AdminOROrderRootNumberInfoUnit[] orderRootNumberInfoList;

    /**
     * 商品市場別合計件数情報一覧<BR>
     * <BR>
     */
    public WEB3AdminORProductMarketNumberInfoUnit[] productMarketSumNumberInfoList;

    /**
     * (発注日別件数情報)<BR>
     * <BR>
     * コンストラクタ<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * WEB3AdminOROrderDayNumberInfoUnit<BR>
     * <BR>
     * constructor<BR>
     * <BR
     * webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderDayNumberInfoUnit
     * @@roseuid 419B4D280210
     */
    public WEB3AdminOROrderDayNumberInfoUnit()
    {

    }
}
@
