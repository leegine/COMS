head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.45.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFutOpOrderExecutionRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者先物OP注文約定照会Unit (WEB3AdminORFutOpOrderExecutionRefUnit.java)
Author Name      : Daiwa Institute of Research
                 : 2006/10/18 唐性峰 (中訊) モデルNo.077
                   2006/12/19 張騰宇 (中訊) 仕様変更・モデル087
*/
package webbroker3.adminorderexecinquiry.message;

/**
 * (管理者先物OP注文約定照会Unit)<BR>
 * <BR>
 * 管理者先物OP注文約定照会Unitクラス<BR>
 * <BR>
 * WEB3AdminORFutOpOrderExecutionRefUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORFutOpOrderExecutionRefUnit extends WEB3AdminOROrderExecutionRefCommon
{
    /**
     * (銘柄コード)<BR>
     * <BR>
     * 銘柄コード<BR>
     * <BR>
     */
    public String productCode;

    /**
     * (銘柄名)<BR>
     * <BR>
     * 銘柄名<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

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
     * (口座区分)<BR>
     * <BR>
     * 口座区分<BR>
     * <BR>
     * 0：　@オプション買建口座<BR>
     * 1：　@先物オプション口座<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * taxType<BR>
     * 0: Def.OPTIONS_LONG_ACCOUNT<BR>
     * 1: Def.FUTURES_OPTIONS_ACCOUNT<BR>
     * <BR>
     */
    public String taxType;

	/**
	 * (発注条件単価区分)<BR>
	 * <BR>
	 * 発注条件単価区分<BR>
	 * <BR>
	 * 0：　@原資産時価<BR>
	 * 1：　@プレミアム<BR>
	 * <BR>
	 * orderCondPriceDiv<BR>
	 * <BR>
	 * 0: Def.DEFAULT
	 * 1: Def.PREMIUM
	 * <BR>
	 */
	public String orderCondPriceDiv;

    /**
     * (Ｗ指値用執行条件)<BR>
     * <BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行 <BR>
     * <BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wlimitExecCondType = null;

    /**
     * (Ｗ指値用有効状態区分)<BR>
     * <BR>
     * 0：リミット注文有効　@1：ストップ注文有効　@2：ストップ注文失効済 <BR>
     * <BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wlimitEnableStatusDiv = null;

    /**
     * (管理者先物OP注文約定照会Unit)<BR>
     * <BR>
     * コンストラクタ<BR>
     * <BR>
     * WEB3AdminORFutOpOrderExecutionRefUnit<BR>
     * <BR>
     * constructor<BR>
     * <BR>
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefUnit
     * @@roseuid 41C787890134
     */
    public WEB3AdminORFutOpOrderExecutionRefUnit()
    {

    }
}
@
