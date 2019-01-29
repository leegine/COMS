head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : ‘Y•]‰¿Šz—š—ğ–¾× (WEB3TPAssetHistoryUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25  äˆ‹»(’†u) V‹Kì¬
*/
package webbroker3.tradingpower.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (‘Y•]‰¿Šz—š—ğ–¾×)<BR>
 * ‘Y•]‰¿Šz—š—ğ–¾×ƒNƒ‰ƒXB<BR>
 * @@author äˆ‹»
 * @@version 1.0
 */
public class WEB3TPAssetHistoryUnit extends Message 
{
   
   /**
    * (“ú•t)<BR>
    * Šî€“ú<BR>
    */
   public Date bizDate;
   
   /**
    * (—a‚è‹à•]‰¿Šz)<BR>
    * —a‚è‹àiŒ»‹àEMRFj•]‰¿Šz <BR>
    */
   public String accountBalance;
   
   /**
    * (Š”®•]‰¿Šz)<BR>
    * Š”®•]‰¿Šz<BR>
    */
   public String equityAsset;
   
   /**
    * (Š”®ƒ~ƒj“Š‘•]‰¿Šz)<BR>
    * Š”®ƒ~ƒj“Š‘•]‰¿Šz<BR>
    */
   public String mstkAsset;
   
   /**
    *(—İÏ“Š‘•]‰¿Šz)<BR>
    * —İÏ“Š‘•]‰¿Šz <BR>
    */
   public String ruitoAsset;
   
   /**
    * (“Š‘M‘õ•]‰¿Šz)<BR>
    * “Š‘M‘õ•]‰¿Šz<BR>
    */
   public String mutualAsset;
   
   /**
    * (ÂŒ”•]‰¿Šz)<BR>
    * ÂŒ”•]‰¿Šz <BR>
    */
   public String bondAsset;
   
   /**
    * (‡Œv•]‰¿Šz)<BR>
    * •]‰¿Šz‚Ì‡Œv<BR>
    */
   public String totalAsset;
   
   /**
    * (‘O“ú”ä)<BR>
    * ‡Œv•]‰¿Šz‚Ì‘O“ú”ä <BR>
    * ¦‘OŒˆÈ‘O‚Ìƒf[ƒ^‚Ìê‡‚ÍA‘OŒ”ä <BR>
    */
   public String comparedPreviousDay;
      
   /**
    * (ƒRƒ“ƒXƒgƒ‰ƒNƒ^)
    * @@roseuid 41B54A5F0065
    */
   public WEB3TPAssetHistoryUnit() 
   {
   }
   
}
@
