head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.20.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginContractGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ¿wæ¨ÔÏÊê¾×s(WEB3FuturesCloseMarginContractGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 ç¾s (u) VKì¬
Revesion History : 2007/06/08 ·^] (u) dlÏXfNo.640
*/

package webbroker3.ifo.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * (¿wæ¨ÔÏÊê¾×s)<BR>
 * ¿wæ¨ÔÏÊê¾×sNX<BR>
 * @@author ç¾s
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginContractGroup extends Message
{

    /**
     * (P¿)<BR>
     */
    public String contractPrice;

    /**
     * (ú)<BR>
     */
    public Date openDate;

    /**
     * (ÔÏÊi¶ªÊj)<BR>
     */
    public String contractOrderQuantity;

    /**
     * (¶P¿æª)<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (¶P¿)<BR>
     */
    public String limitPrice;

    /**
     * (ñèÊ)<BR>
     */
    public String execQuantity;

    /**
     * (ñèP¿)<BR>
     */
    public String execPrice;

    /**
     * (è¿)<BR>
     */
    public String contractCommission;

    /**
     * (è¿ÁïÅ)<BR>
     */
    public String contractCommissionConsumptionTax;

    /**
     * (ãà)<BR>
     */
    public String contractExecPrice;

    /**
     * (Ï¹v)<BR>
     */
    public String settleProfitLoss;

    /**
     * (Ê)<BR>
     */
    public String closeMarginOrderNumber;
    
    /**
     * (§ïæª)<BR>
     * 1F[êi[êÀ{·éïÐÌ[êÔÑÌÝÝèj@@NULLF»Ì¼<BR>
     */
    public String sessionType;

    /**
     * @@roseuid 40F7AE1703A9
     */
    public WEB3FuturesCloseMarginContractGroup()
    {

    }
}
@
