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
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : Y]¿zð¾× (WEB3TPAssetHistoryUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25  ä»(u) VKì¬
*/
package webbroker3.tradingpower.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (Y]¿zð¾×)<BR>
 * Y]¿zð¾×NXB<BR>
 * @@author ä»
 * @@version 1.0
 */
public class WEB3TPAssetHistoryUnit extends Message 
{
   
   /**
    * (út)<BR>
    * îú<BR>
    */
   public Date bizDate;
   
   /**
    * (aèà]¿z)<BR>
    * aèài»àEMRFj]¿z <BR>
    */
   public String accountBalance;
   
   /**
    * (®]¿z)<BR>
    * ®]¿z<BR>
    */
   public String equityAsset;
   
   /**
    * (®~j]¿z)<BR>
    * ®~j]¿z<BR>
    */
   public String mstkAsset;
   
   /**
    *(ÝÏ]¿z)<BR>
    * ÝÏ]¿z <BR>
    */
   public String ruitoAsset;
   
   /**
    * (Mõ]¿z)<BR>
    * Mõ]¿z<BR>
    */
   public String mutualAsset;
   
   /**
    * (Â]¿z)<BR>
    * Â]¿z <BR>
    */
   public String bondAsset;
   
   /**
    * (v]¿z)<BR>
    * ]¿zÌv<BR>
    */
   public String totalAsset;
   
   /**
    * (Oúä)<BR>
    * v]¿zÌOúä <BR>
    * ¦OÈOÌf[^ÌêÍAOä <BR>
    */
   public String comparedPreviousDay;
      
   /**
    * (RXgN^)
    * @@roseuid 41B54A5F0065
    */
   public WEB3TPAssetHistoryUnit() 
   {
   }
   
}
@
