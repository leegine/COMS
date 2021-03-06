head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleTemporaryOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ­§Ï¶Æïîñ(WEB3AdminForcedSettletemporaryOrderUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 æâÑQ (u) VKì¬
Revesion History : 2007/07/24 ½¶q (u) VKì¬@@dlÏXfNo.159
Revesion History : 2007/08/27 Äog (u) dlÏXfNo.163
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (­§Ï¶Æïîñ)<BR>
 * ­§Ï¶ÆïîñNX<BR>
 * <BR>
 * @@author æâÑQ
 * @@version 1.0
 */

public class WEB3AdminForcedSettleTemporaryOrderUnit extends Message
{
    
    /**
     * ¶ID<BR>
     */
    public String id;
    
    /**
     * (XR[h)<BR>
     * XR[h<BR>
     */
    public String branchCode;
    
    /**
     * (ÚqR[h)<BR>
     * ÚqR[h<BR>
     */
    public String accountCode;
    
    /**
     * (Úq¼)<BR>
     * Úq¼<BR>
     */
    public String accountName;
    
    /**
     * (Á¿R[h)<BR>
     * Á¿R[h<BR>
     */
    public String productCode;
    
    /**
     * (Á¿¼)<BR>
     * Á¿¼<BR>
     */
    public String productName;
    
    /**
     * (sêR[h)<BR>
     * sêR[h<BR>
     */
    public String marketCode;
    
    /**
     * (ûÀæª)<BR>
     * ûÀæª<BR>
     * <BR>
     * 0F@@êÊ<BR>
     * 1F@@Áè<BR>
     */
    public String taxType;
    
    /**
     * (æª)<BR>
     * æª<BR>
     * <BR>
     * 1F@@<BR>
     * 2F@@<BR>
     */
    public String contractType;
    
    /**
     * (ú)<BR>
     * ú<BR>
     */
    public Date openDate;
    
    /**
     * (Ïúú)<BR>
     * Ïúú<BR>
     */
    public Date closeDate;
    
    /**
     * ()<BR>
     * <BR>
     */
    public String contractQuantity;
    
    /**
     * (P¿)<BR>
     * P¿<BR>
     */
    public String contractPrice;
    
    /**
     * (ãà)<BR>
     * ãà<BR>
     */
    public String contractExecPrice;

    /**
     * (ÛØàaõ¦)<BR>
     * ÛØàaõ¦<BR>
     */
    public String marginCollateralRate;

    /**
     * (ÇØ­¶ú)<BR>
     * ÇØ­¶ú<BR>
     */
    public Date additionalOccurredDate;

    /**
     * (ÇØoßú)<BR>
     * ÇØoßú<BR>
     */
    public String additionalElapsedDays;

    /**
     * (ÙÏæª)<BR>
     * ÙÏæª<BR>
     * <BR>
     * 1F@@§xMp<BR>
     * 2F@@êÊMp<BR>
     */
    public String repaymentDiv;
    
    /**
     * (ÙÏúÀl)<BR>
     * ÙÏúÀl<BR>
     */
    public String repaymentTimeLimit;
    
    /**
     * (¶)<BR>
     * ¶<BR>
     */
    public String orderQuantity;
    
    /**
     * (¶P¿æª)<BR>
     * ¶P¿æª<BR>
     * <BR>
     * 0F@@¬s<BR>
     * 1F@@wl<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (¶P¿)<BR>
     * ¶P¿<BR>
     */
    public String orderPrice;
    
    /**
     * (­ú)<BR>
     * ­ú<BR>
     */
    public Date orderBizDate;
    
    /**
     * (ì¬ú)<BR>
     * ì¬ú<BR>
     */
    public Date createDate;
    
    /**
     * (iñj³Fú)<BR>
     * iñj³Fú<BR>
     */
    public Date approveDate;
    
    /**
     * (³FóÔ)<BR>
     * ³FóÔ<BR>
     * <BR>
     * 0F@@¢³F<BR>
     * 1F@@³FÏ<BR>
     * 2F@@ñ³F<BR>
     * 9F@@G[<BR>
     */
    public String approveState;
    
    /**
     * (³FÒR[h)<BR>
     * ³FÒR[h<BR>
     */
    public String checker;
    
    /**
     * (¶G[RR[h)<BR>
     * ¶G[RR[h<BR>
     * <BR>
     * 0005F@@cs«G[<BR>
     * 0006F@@â~Á¿G[<BR>
     * 0016F@@ÏúúÏG[<BR>
     * 0017F@@»øE»n¶o^ÏG[<BR>
     * 9001F@@»Ì¼G[<BR>
     */
    public String errorReason;
    
    /**
     * (î]¿z´ßtO)<BR>
     * î]¿z´ßtO<BR>
     * <BR>
     * falseF@@´ßÈµ<BR>
     * trueF@@î]¿zð´ß<BR>
     */
    public boolean baseAssetOverFlag;

    /**
     * (TZ]¿z)<BR>
     * TZ]¿z<BR>
     */
    public String estimatedAsset;

    /**
     * (­§ÏR)<BR>
     * ­§ÏR<BR>
     */
    public WEB3AdminForcedSettleReasonUnit forcedSettleReason;
    
    /**
     * @@roseuid 462CA4290341
     */
    public WEB3AdminForcedSettleTemporaryOrderUnit() 
    {
     
    }
}
@
