head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferReportUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : OUÖ|[g¾×(WEB3FEqConTransferReportUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 üE(u) VKì¬
                   2006/02/08 ©(u) dlÏXEf481
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import java.util.Date;

/**
 * (OUÖ|[g¾×)<BR>
 * OUÖ|[g¾×NX
 * 
 * @@author üE(u)
 * @@version 1.0
 */
public class WEB3FEqConTransferReportUnit extends Message 
{
    
    /**
     * (UÖæª)<BR>
     * UÖæª<BR>
     * <BR>
     * 1Füà<BR>
     * 2Foà
     */
    public String transferDiv;
    
    /**
     * (ÚqR[h)<BR>
     * ÚqR[h
     */
    public String accountCode;
    
    /**
     * (¼Oi©j)<BR>
     * Úq¼(©)
     */
    public String familyName;
    
    /**
     * (¼Oi¼j)<BR>
     * Úq¼(¼)
     */
    public String name;
    
    /**
     * (¯ÊR[h)<BR>
     * ¯ÊR[h
     */
    public String requestNumber;
    
    /**
     * (ótú)<BR>
     * ótú
     */
    public Date receptionDate;
    
    /**
     * (UÖú)<BR>
     * UÖú
     */
    public String transferDate;
    
    /**
     * (UÖàz)<BR>
     * UÖàz
     */
    public String changeAmt;
    
    /**
     * (OûÀÔ)<BR>
     * OûÀÔ
     */
    public String fstkAccountCode;
    
    /**
     * (UWGótú)<BR>
     * UWGótú
     */
    public Date uwgReceptionDate;
    
    /**
     * (Xe[^Xæª)<BR>
     * 0FÏ<BR>
     * 1FÏ®¹<BR>
     * 2FÏG[<BR>
     * 3FæÁ
     */
    public String statusDiv;
    
    /**
     * (bZ[W)<BR>
     * 10000000FótÏ <BR> 
     * 20000000FÏ <BR> 
     * 90000000FæÁÏ <BR> 
     * 99999999FÏ¸siVXeG[j <BR> 
     * 00000000FÏ®¹<BR> 
     * 90000009:ûÀÁ
     */
    public String message;
    
    /**
     * (OUÖ|[g¾×)<BR>
     * RXgN^
     * @@roseuid 41D0BA6200ED
     */
    public WEB3FEqConTransferReportUnit() 
    {
     
    }
}
@
