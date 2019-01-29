head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoAssetGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保有資産一覧行(WEB3RuitoAssetGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周 勇 (中訊) 新規作成
                   2004/12/03 韋念瓊 (中訊) 残対応
*/
package webbroker3.xbruito.message;

import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * 保有資産一覧行<BR>
 */
public class WEB3RuitoAssetGroup extends Message
{

    /**
     * 銘柄コード<BR>
     */
    public String ruitoProductCode;

    /**
     * 銘柄名<BR>
     */
    public String ruitoProductName;

    /**
     * 残高<BR>
     */
    public String ruitoBalance;

    /**
     * 内30日経過残高<BR>
     */
    public String ruito30DayPassBal;

    /**
     * 内30日未経過残高<BR>
     */
    public String ruito30DayNotPassBal;

    /**
     * 信託財産留保額<BR>
     */
    public String estateReserve;

    /**
     * 解約可能残高<BR>
     */
    public String ruitoSellPossBalance;

    /**
     * 解約注文明細<BR>
     */
    public webbroker3.xbruito.message.WEB3RuitoSellOrderUnit[] ruitoSellOrderUnits;

    /**
     * 解約（売付）可能区分  <BR>
     * null：取引可能  <BR>
     * １：システム取引停止エラー  <BR>
     * ２：受付時間エラー  <BR>
     * ３：取引停止中  <BR>
     * ４：緊急停止中  <BR>
     * ５：全部解約中  <BR>
     */
    public String sellPossDiv;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922B76032C
     */
    public WEB3RuitoAssetGroup()
    {
    }
}
@
