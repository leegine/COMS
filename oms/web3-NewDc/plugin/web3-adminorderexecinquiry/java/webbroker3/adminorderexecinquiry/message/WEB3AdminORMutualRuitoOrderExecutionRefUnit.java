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
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒMÝ¶ñèÆïUnit(WEB3AdminORMutualRuitoOrderExecutionRefUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/19 ü· (u) dlÏXEf069
Revision History : 2007/01/12 ü· (u) dlÏXEf089
Revesion History : 2007/02/26 åû(u)dlÏXfNo.094
*/
package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

/**
 * (ÇÒMÝ¶ñèÆïUnit)<BR>
 * <BR>
 * ÇÒMÝ¶ñèÆïUnitNX<BR>
 * <BR>
 * WEB3AdminORMutualRuitoOrderExecutionRefUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORMutualRuitoOrderExecutionRefUnit extends WEB3AdminOROrderExecutionRefCommon
{
    /**
     * (ûÀæª)<BR>
     * <BR>
     * ûÀæª<BR>
     * <BR>
     * 0F@@êÊ 1F@@Áè 2F@@»Ì¼<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * taxType<BR>
     * 0: Def.NORMAL 1: Def.SPECIAL<BR>
     * <BR>
     */
    public String taxType = null;

    /**
     * (Ïæª)<BR>
     * <BR>
     * Ïæª<BR>
     * <BR>
     * 1F@@~Ý<BR>
     * 2F@@OÝ<BR>
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
     * (Á¿ID)<BR>
     * <BR>
     * Á¿ID<BR>
     * <BR>
     * productId<BR>
     * <BR>
     */
    public String productId;

    /**
     * (Á¿¼)<BR>
     * <BR>
     * Á¿¼<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

    /**
     * (ðñæª)<BR>
     * <BR>
     * ðñæª<BR>
     * <BR>
     * 2F@@Swè<BR>
     * 3F@@àzwè<BR>
     * 4F@@ûwè <BR>
     * ¦tÌêÍnullðZbgB<BR>
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
     * (ónû@@)<BR>
     * <BR>
     * ónû@@<BR>
     * <BR>
     * 1F@@âsU<BR>
     * 2F@@ØûÀüÍ<BR>
     * 3F@@³ÖW<BR>
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
     * (ñèú)<BR>
     * <BR>
     * ñèú<BR>
     * <BR>
     * executionTimestamp<BR>
     * <BR>
     */
    public Date executionTimestamp;
    
    /**
     * (Ðîæª)<BR>
     * Ðîæª<BR> 
     * <BR>
     * null:wè³µ<BR> 
     * 1:¼Úæø<BR> 
     * 2:PÐî<BR> 
     * 3:¤iÐî<BR> 
     * 4:îæø<BR>
     */
    public String introduceStoreDiv;
    
    /**
     * (ÐîXR[h)<BR>
     */
    public String introduceStoreCode;

    /**
     * (¿æª)<BR>
     * ¿æª <BR>
     * <BR>
     * 0Fðñ <BR>
     * 1Fæ<BR>
     */
    public String sellBuyDiv;

    /**
     * (¶Êæª)<BR>
     * ¶Êæª<BR>
     * <BR>
     * 0:û<BR>
     * A0:US$@@A1:C$@@A2:A$@@A3:HK$@@A4:S$<BR>
     * A5:NZ$@@D0:@@D1:Irish @@F0:Fr<BR>
     * F1:SFr@@I0:DM@@J0:G@@K0:Lit@@L0:AS<BR>
     * M0:DKr@@M1:NKr@@M2:SKr@@N0:Pts@@T0:~<BR>
     * T3:FIM@@U1:Bath@@Z3:ECU@@Z4:EUR<BR>
     */
    public String mutualOrderQuantityType;

    /**
     * (TZónãàÊÝR[h)<BR>
     * TZónãàÊÝR[h<BR>
     * <BR>
     * A0:US$@@A1:C$@@A2:A$@@A3:HK$@@A4:S$<BR>
     * A5:NZ$@@D0:@@D1:Irish @@F0:Fr<BR>
     * F1:SFr@@I0:DM@@J0:G@@K0:Lit@@L0:AS<BR>
     * M0:DKr@@M1:NKr@@M2:SKr@@N0:Pts@@T0:~<BR>
     * T3:FIM@@U1:Bath@@Z3:ECU@@Z4:EUR<BR>
     */
    public String estimatedPriceCurrencyCode;

    /**
     * (OÝMMFtO)<BR>
     * OÝMMFtO<BR>
     * <BR>
     * true:Á¿ªOÝMMF<BR>
     * false:Á¿ªOÝMMFÅÈ¢<BR>
     */
    public boolean frgnMmfFlag;

    /**
     * iÇÒMÝ¶ñèÆïUnitj<BR>
     * <BR>
     * RXgN^<BR>
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
