head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOLotResultUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : IÊ¾×bZ[Wf[^(WEB3IPOLotResultUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 £Ð (u) VKì¬
Revesion History : 2005/12/20 A¿§ (u) dlÏXNo.105C³
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * IÊ¾×bZ[Wf[^NX
 *                                                                
 * @@author £Ð
 * @@version 1.0
 */
public class WEB3IPOLotResultUnit extends Message
{
    
    /**
     * XR[h
     */
    public String branchCode;
    
    /**
     * ÚqR[h
     */
    public String accountCode;
    
    /**
     * Úq¼
     */
    public String accountName;
    
    /**
     * IÊæª<BR>
     * <BR>
     * 1F@@I<BR>
     * 2F@@â<BR>
     * 3F@@I<BR>
     * 21F@@âI<BR>
     * 23F@@âI
     */
    public String lotResultDiv;
    
    /**
     * IÊ
     */
    public String prizeQuantity;
    
    /**
     * wü\Ê
     */
    public String offerQuantity;
    
    /**
     * wü\«Þú
     */
    public Date offerCancelDate;
    
    /**
     * wü\óµæª<BR>
     * <BR>
     * 1F@@wü\<BR>
     * 2F@@«Þ
     */
    public String offerStateDiv;
    
    /**
     * ótóÔæª<BR>
     * <BR>
     * 0F@@DEFAULTi----j<BR>
     * 1F@@SONARMÏiótj<BR>
     * 2F@@SONAR½fÏiótÏj
     */
    public String receiveStateDiv;
    
    /**
     * DæÊ
     */
    public String priority;
    
    /**
     * µÒR[h
     */
    public String traderCode;

    /**
     * öJ¿i
     */
    public String publicOfferingPrice;
    
    /**
     * Mpæª
     */
    public String marginDiv;
    
    /**
     * IÔ
     */
    public String lotNumber;
    
    /**
     * ftHgRXgN^
     * @@roseuid 40EE26B50009
     */
    public WEB3IPOLotResultUnit() 
    {
     
    }
}
@
