head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : Mθθztπs(WEB3MutualFixedBuyConditionUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 GΜ (u) VKμ¬
                 : 2006/07/22 GΜ (u) dlΟX f 460
Revesion History : 2008/07/08 €u¨ (u) dlΟX fNo.604,610
*/
package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (Mθθztπs)<BR>
 * Mθθztπs<BR>
 * 
 * @@author GΜ(u)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyConditionUnit extends Message  
{
    /**
     * (ΑΏR[h)<BR>
     * ΑΏR[h<BR>
     */
    public String mutualProductCode;
  
    /**
     * (ΑΏΌ)<BR>
     * ΑΏΌ<BR>
     */
    public String mutualProductName;
 
    /**
     * (MΑΏJeS[R[h)<BR>
     * MΑΏJeS[R[h<BR>
     */
    public String categoryCode;
 
    /**
     * (tΰz(X))<BR>
     * tΰz(X)<BR>
     */
    public String monthlyBuyAmount;
  
    /**
     * (tΰz(Οέ΅))<BR>
     * tΰz(Οέ΅)<BR>
     */
    public String increaseBuyAmount;
  
    /**
     * (ΑΏ\¦)<BR>
     * ΑΏ\¦<BR> 
     * ρ\¦@@APΕ\[gΙgp·ιB<BR>
     */
    public String displayOrder;
   
    /**
     * (KpJnN)<BR>
     * KpJnN<BR> 
     */
    public Date validStartDate;    
    
    /**
     * (XVϊ)<BR>
     * XVϊ<BR>
     */
    public Date updateDate;
    
    /**
     * (ϋΐψN)<BR>
     * ϋΐψN<BR>
     */
    public Date debitAccountYM;

    /**
     * (mθψΰziΟέ΅j)<BR>
     * mθψΰziΟέ΅j<BR>
     */
    public String definiteIncreaseBuyAmount;

    /**
     * (Ϊ_©{`FbN)<BR>
     * Ϊ_©{`FbN<BR>
     */
    public String checkResult;

    /**
     * (κβ~tO)<BR>
     * κβ~tO<BR>
     */
    public boolean suspensionFlag;

    /**
     * (sonarM`FbN)<BR>
     * sonarM`FbN<BR>
     */
    public String sonarSendCheck;

    /**
     * (MθθztπsΜCX^XπΆ¬·ιB)<BR>
     * ftHgRXgN^<BR>
     */
    public WEB3MutualFixedBuyConditionUnit()
    {
    }
}




@
