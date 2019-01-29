head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.14.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontProcessNumberInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理件数情報 (WEB3AdminFrontProcessNumberInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.115
*/
package webbroker3.dirsec.message;

/**
 * 処理件数情報クラス<BR>
 * <BR>
 * WEB3AdminFrontProcessNumberInfoUnit<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontProcessNumberInfoUnit extends WEB3AdminOrderRouteInfo {

    /**
     * 確認前件数（グレー注文）<BR>
     */
    public String beforeNumber;
   
    /**
     * 確認中件数（グレー注文）<BR>
     */
    public String inNumber;
   
    /**
     * 確認済件数（グレー注文）<BR>
     */
    public String afterNumber;
   
    /**
     * 未処理件数（通番照会要求）<BR>
     */
    public String nonProcessNumber1;
   
    /**
     * 未処理件数（通知代行解除要求）<BR>
     */
    public String nonProcessNumber2;
   
    /**
     * 未処理件数（通知代行要求）<BR>
     */
    public String nonProcessNumber3;
   
    /**
     * 未処理件数（通知再送要求・受付系）<BR>
     */
    public String nonProcessNumber4;
   
    /**
     * 未処理件数（通知再送要求・約定系）<BR>
     */
    public String nonProcessNumber5;
   
    /**
     * 処理済件数（通番照会要求）<BR>
     */
    public String finProcessNumber1;
   
    /**
     * 処理済件数（通知代行解除要求）<BR>
     */
    public String finProcessNumber2;
   
    /**
     * 処理済件数（通知代行要求）<BR>
     */
    public String finProcessNumber3;
   
    /**
     * 処理済件数（通知再送要求・受付系）<BR>
     */
    public String finProcessNumber4;
   
    /**
     * 処理済件数（通知再送要求・約定系）<BR>
     */
    public String finProcessNumber5;
   
    /**
     * 仮想サーバ数<BR>
     */
    public String virtualServerQuantity;
   
    /**
     * @@roseuid 42FFFED5014B
     */
    public WEB3AdminFrontProcessNumberInfoUnit() 
    {
    
    }
}
@
