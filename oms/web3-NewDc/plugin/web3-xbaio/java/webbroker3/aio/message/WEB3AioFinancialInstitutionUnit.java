head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFinancialInstitutionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : Uæ‹à—Z‹@@ŠÖ–¾×(WEB3AioFinancialInstitutionUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ‰©Œš (’†u) V‹Kì¬
                   2004/10/25 ü—E(’†u) ƒŒƒrƒ…[
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (Uæ‹à—Z‹@@ŠÖ–¾×)<BR>
 * Uæ‹à—Z‹@@ŠÖ–¾×ƒNƒ‰ƒX<BR>
 * 
 * @@author ‰©Œš(’†u)
 * @@version 1.0 
 */
public class WEB3AioFinancialInstitutionUnit extends Message
{
    
    /**
     * (Uæ‹à—Z‹@@ŠÖƒR[ƒh)<BR>
     * Uæ‹à—Z‹@@ŠÖ‚ÌƒR[ƒh
     */
    public String finInstitutionCode;
    
    /**
     * (Uæ‹à—Z‹@@ŠÖ–¼Ì)<BR>
     * Uæ‹à—Z‹@@ŠÖ‚Ì–¼Ì
     */
    public String finInstitutionName;
    
    /**
     * (Uæ‹à—Z‹@@ŠÖ–¾×)<BR>
     * ƒRƒ“ƒXƒgƒ‰ƒNƒ^
     * @@return webbroker3.aio.message.WEB3AioFinancialInstitutionUnit
     * @@roseuid 40E2575103C1
     */
    public WEB3AioFinancialInstitutionUnit() 
    {
     
    }
    
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }       
        if(obj instanceof WEB3AioFinancialInstitutionUnit)
        {
            WEB3AioFinancialInstitutionUnit l_aioFinancialInstitutionUnit = (WEB3AioFinancialInstitutionUnit)obj;
            if(finInstitutionCode.equals(l_aioFinancialInstitutionUnit.finInstitutionCode) 
               && finInstitutionName.equals(l_aioFinancialInstitutionUnit.finInstitutionName))
            {
                return true;
            }
        }
        return false;
    }
}
@
