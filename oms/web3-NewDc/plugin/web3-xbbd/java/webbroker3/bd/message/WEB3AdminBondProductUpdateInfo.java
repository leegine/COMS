head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.37.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductUpdateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÂÁ¿XVîñ(WEB3AdminBondProductUpdateInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 æâÑQ (u) VKì¬
                     2006/10/08 ü· (u) dlÏXEf106A107
Revesion History : 2008/08/13 égC· (u) dlÏX@@f260
Revesion History : 2009/07/24 g (u) dlÏX@@f261
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (ÂÁ¿XVîñ)<BR>
 * ÂÁ¿XVîñNX
 *
 * @@author æâÑQ
 * @@version 1.0
 */
public class WEB3AdminBondProductUpdateInfo extends Message
{

    /**
     * (æµæª)<BR>
     * æµæª<BR>
     * <BR>
     * @@0FsÂ@@1FÇÒ@@2FÇÒ/Úq
     */
    public String tradeHandleDiv;

    /**
     * (æµJnú)<BR>
     * æµJnú
     */
    public Date tradeStartDate;

    /**
     * (æµI¹ú)<BR>
     * æµI¹ú
     */
    public Date tradeEndDate;

    /**
     * (åJnú)<BR>
     * åJnú
     */
    public Date recruitStartDate;

    /**
     * (ónú)<BR>
     * ónú<BR>
     */
    public Date deliveryDate;

    /**
     * (åI¹ú)<BR>
     * åI¹ú
     */
    public Date recruitEndDate;

    /**
     * (æª)<BR>
     * æª<BR>
     * <BR>
     * 1:t@@2:p@@3:å@@4:t^p
     */
    public String buySellDiv;

    /**
     * (Á¿¼)<BR>
     * Á¿¼
     */
    public String productName;

    /**
     * (tP¿)<BR>
     * tP¿
     */
    public String buyPrice;

    /**
     * (pP¿)<BR>
     * pP¿
     */
    public String sellPrice;

    /**
     * (\PÊ)<BR>
     * \PÊ
     */
    public String tradeUnit;

    /**
     * (ÅázÊ)<BR>
     * ÅázÊ
     */
    public String minFaceAmount;

    /**
     * (ÅzÊ)<BR>
     * ÅzÊ
     */
    public String maxFaceAmount;

    /**
     * (J_[AgsêR[h)<BR>
     * J_[AgsêR[h
     */
    public String calendarLinkedDiv;

    /**
     * (tónúÚ®ú)<BR>
     * tónúÚ®ú
     */
    public String buyDeliveryMove;

    /**
     * (pónúÚ®ú)<BR>
     * pónúÚ®ú
     */
    public String sellDeliveryMove;

    /**
     * (©®ñèæª)<BR>
     * ©®ñèæª
     * <BR>
     * 0Fñ©®ñè@@1F©®ñè
     */
    public String autoExecDiv;

    /**
     * (©®ñèg)<BR>
     * ©®ñèg
     */
    public String autoExecLimit;

    /**
     * (JXgfBAR[h)<BR>
     * JXgfBAR[h
     */
    public String custodianCode;

    /**
     * (îè¿¦)<BR>
     * îè¿¦
     */
    public String mediatorCommissionRate;

    /**
     * (düÌ×Ö[g)<BR>
     * düÌ×Ö[g
     */
    public String fxRateAtStock;

    /**
     * (æøÔ`FbNæª)<BR>
     * æøÔ`FbNæª
     */
    public String tradeTimeCheckDiv;

    /**
     * (å©U`®)<BR>
     * å©U`®
     */
    public String recruitInvitationForm;

    /**
     * (åøó¯æª)<BR>
     * åøó¯æª
     */
    public String recruitAcceptDiv;

    /**
     * @@roseuid 44E3363E02CE
     */
    public WEB3AdminBondProductUpdateInfo()
    {

    }
}
@
