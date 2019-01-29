head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORMutualRuitoOrderExecutionRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者投信累投注文約定照会Unit(WEB3AdminORMutualRuitoOrderExecutionRefUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/19 周捷 (中訊) 仕様変更・モデル069
Revision History : 2007/01/12 周捷 (中訊) 仕様変更・モデル089
Revesion History : 2007/02/26 徐大方(中訊)仕様変更モデルNo.094
*/
package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

/**
 * (管理者投信累投注文約定照会Unit)<BR>
 * <BR>
 * 管理者投信累投注文約定照会Unitクラス<BR>
 * <BR>
 * WEB3AdminORMutualRuitoOrderExecutionRefUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORMutualRuitoOrderExecutionRefUnit extends WEB3AdminOROrderExecutionRefCommon
{
    /**
     * (口座区分)<BR>
     * <BR>
     * 口座区分<BR>
     * <BR>
     * 0：　@一般 1：　@特定 2：　@その他<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * taxType<BR>
     * 0: Def.NORMAL 1: Def.SPECIAL<BR>
     * <BR>
     */
    public String taxType = null;

    /**
     * (決済区分)<BR>
     * <BR>
     * 決済区分<BR>
     * <BR>
     * 1：　@円貨<BR>
     * 2：　@外貨<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * settleDiv<BR>
     * 1: Def.JAPANESE_CURRENCY<BR>
     * 2: Def.FOREIGN_CURRENCY<BR>
     * <BR>
     */
    public String settleDiv = null;

    /**
     * (銘柄ID)<BR>
     * <BR>
     * 銘柄ID<BR>
     * <BR>
     * productId<BR>
     * <BR>
     */
    public String productId;

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
     * (解約区分)<BR>
     * <BR>
     * 解約区分<BR>
     * <BR>
     * 2：　@全部指定<BR>
     * 3：　@金額指定<BR>
     * 4：　@口数指定 <BR>
     * ※買付の場合はnullをセット。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * sellDiv<BR>
     * 2: Def.ALL_DESIGNATE<BR>
     * 3: Def.MONEY_DESIGNATE<BR>
     * 4: Def.COUNT_DESIGNATE <BR>
     * If Def.BUY, set null<BR>
     * <BR>
     */
    public String sellDiv = null;

    /**
     * (受渡方法@)<BR>
     * <BR>
     * 受渡方法@<BR>
     * <BR>
     * 1：　@銀行振込<BR>
     * 2：　@証券口座入力<BR>
     * 3：　@無関係<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * deliveryDiv<BR>
     * 1: Def.BANK_TRANSFER<BR>
     * 2: Def.SECURITIES_ACCOUNT_INPUT_SELL<BR>
     * 3: Def.IRRELEVENT_BUY<BR>
     * <BR>
     */
    public String deliveryDiv;

    /**
     * (約定日)<BR>
     * <BR>
     * 約定日<BR>
     * <BR>
     * executionTimestamp<BR>
     * <BR>
     */
    public Date executionTimestamp;
    
    /**
     * (紹介区分)<BR>
     * 紹介区分<BR> 
     * <BR>
     * null:指定無し<BR> 
     * 1:直接取引<BR> 
     * 2:単純紹介<BR> 
     * 3:商品紹介<BR> 
     * 4:仲介取引<BR>
     */
    public String introduceStoreDiv;
    
    /**
     * (紹介店コード)<BR>
     */
    public String introduceStoreCode;

    /**
     * (請求区分)<BR>
     * 請求区分 <BR>
     * <BR>
     * 0：解約 <BR>
     * 1：買取<BR>
     */
    public String sellBuyDiv;

    /**
     * (注文数量区分)<BR>
     * 注文数量区分<BR>
     * <BR>
     * 0:口数<BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     */
    public String mutualOrderQuantityType;

    /**
     * (概算受渡代金通貨コード)<BR>
     * 概算受渡代金通貨コード<BR>
     * <BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     */
    public String estimatedPriceCurrencyCode;

    /**
     * (外貨MMFフラグ)<BR>
     * 外貨MMFフラグ<BR>
     * <BR>
     * true:銘柄が外貨MMF<BR>
     * false:銘柄が外貨MMFでない<BR>
     */
    public boolean frgnMmfFlag;

    /**
     * （管理者投信累投注文約定照会Unit）<BR>
     * <BR>
     * コンストラクタ<BR>
     * <BR>
     * -----<English>-----------------<BR>
     * <BR>
     * WEB3AdminORMutualRuitoOrderExecutionRefUnit<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRef
     * Unit
     * @@roseuid 41DB779F006D
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefUnit()
    {

    }
}
@
