head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBookPriceInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : »¨®ë¿P¿o^üÍX|X(WEB3EquityBookPriceInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 òº@@mm(SRA) VKì¬
                 : 2006/08/29 £«F(u) f 972
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 * i»¨®ë¿P¿o^üÍX|XjB<BR>
 * <BR>
 * »¨®ë¿P¿o^üÍX|XNX<BR>
 */
public class WEB3EquityBookPriceInputResponse extends WEB3GenResponse 
{
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502141000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_book_price_input";
    
    /**
     * (ÛLYID)<BR>
     * <BR>
     * ÛLYID<BR>
     */
    public String assetId;
    
    /**
     * (Á¿R[h)<BR>
     * <BR>
     * Á¿R[h<BR>
     */
    public String productCode;
    
    /**
     * (Á¿¼)<BR>
     * <BR>
     * Á¿¼<BR>
     */
    public String productName;
    
    /**
     * (ûÀæª)<BR>
     * <BR>
     * 0FêÊ@@1FÁè@@5FXgbNIvV<BR>
     */
    public String taxType;
    
    /**
     * (c)<BR>
     * <BR>
     * c<BR>
     */
    public String balanceQuantity;
    
    /**
     * (tÂ\)<BR>
     * <BR>
     * tÂ\<BR>
     */
    public String sellPossQuantity;
    
    /**
     * (¶)<BR>
     * <BR>
     * ¶<BR>
     */
    public String orderedQuantity;
    
    /**
     * (ts\)<BR>
     * <BR>
     * ts\<BR>
     */
    public String sellImpossQuantity;
    
    /**
     * (üÍë¿P¿)<BR>
     * <BR>
     * üÍë¿P¿<BR>
     * <BR>
     * ¦OñüÍµ½lð\¦B<BR>
     * @@¢üÍÌêÍnullðZbgB<BR>
     */
    public String inputBookPrice = null;
    
    /**
     * (ë¿P¿üÍú)<BR>
     * <BR>
     * ë¿P¿üÍú<BR>
     * <BR>
     * ¦OñüÍµ½úð\¦B<BR>
     * @@¢üÍÌêÍnullðZbgB<BR>
     */
    public Date bookPriceInputDate = null;
    
    /**
     * (TZë¿P¿)<BR>
     * <BR>
     * TZë¿P¿<BR>
     */
    public String estimatedBookPrice = null;
    
    /**
     * @@roseuid 4206CF2F004A<BR>
     */
    public WEB3EquityBookPriceInputResponse() 
    {
     
    }
    
    public WEB3EquityBookPriceInputResponse(WEB3EquityBookPriceInputRequest l_request)
    {
        super(l_request);
    }
}
@
