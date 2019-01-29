head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOROrderRootNumberInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文経路別件数情報 (WEB3AdminOROrderRootNumberInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (注文経路別件数情報)<BR>
 * <BR>
 * 注文経路別件数情報クラス<BR>
 * <BR>
 * WEB3AdminOROrderRootNumberInfoUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOROrderRootNumberInfoUnit extends Message
{
    /**
     * (注文経路区分)<BR>
     * <BR>
     * 注文経路区分<BR>
     * <BR>
     * orderRootDiv<BR>
     * <BR>
     */
    public String orderRootDiv;

    /**
     * （商品市場別件数情報一覧）<BR>
     * <BR>
     */
    public WEB3AdminORProductMarketNumberInfoUnit[] productMarketNumberInfoList;

    /**
     * (注文経路別件数情報)<BR>
     * <BR>
     * コンストラクタ<BR>
     * <BR>
     * WEB3AdminOROrderRootNumberInfoUnit<BR>
     * <BR>
     * constructor<BR>
     * <BR>
     * webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderRootNumberInfoUnit
     * @@roseuid 419B4D4600D7
     */
    public WEB3AdminOROrderRootNumberInfoUnit()
    {

    }
}
@
