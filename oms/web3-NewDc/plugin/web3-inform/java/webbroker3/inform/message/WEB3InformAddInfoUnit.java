head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAddInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 各種連絡付加情報クラス(WEB3InformAddInfoUnit.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/21 凌建平(中訊) 作成
*/

package webbroker3.inform.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (各種連絡付加情報)<BR>
 * 各種連絡付加情報クラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3InformAddInfoUnit extends Message
{
   
   /**
    * (付加情報１)<BR>
    * 各種連絡情報について画面表示で使用する付加情報
    */
   public String addInfo1;
   
   /**
    * (付加情報２)<BR>
    * 各種連絡情報について画面表示で使用する付加情報
    */
   public String addInfo2;
   
   /**
    * （付加情報3）<BR>
    * 各種連絡情報について画面表示で使用する付加情報
    */
   public String addInfo3;
   
   /**
    * (付加情報４)<BR>
    * 各種連絡情報について画面表示で使用する付加情報
    */
   public String addInfo4;
   
   /**
    * (付加情報5)<BR>
    * 各種連絡情報について画面表示で使用する付加情報
    */
   public String addInfo5;
   
   /**
    * (付加情報6)<BR>
    * 各種連絡情報について画面表示で使用する付加情報
    */
   public String addInfo6;
   
   /**
    * (付加情報7)<BR>
    * 各種連絡情報について画面表示で使用する付加情報
    */
   public String addInfo7;
   
   /**
    * (付加情報8)<BR>
    * 各種連絡情報について画面表示で使用する付加情報
    */
   public String addInfo8;
   
   /**
    * (付加情報9)<BR>
    * 各種連絡情報について画面表示で使用する付加情報
    */
   public String addInfo9;
   
   /**
    * (付加情報10)<BR>
    * 各種連絡情報について画面表示で使用する付加情報
    */
   public String addInfo10;
   
   /**
    * (各種連絡付加情報)<BR>
    * コンストラクタ
    * @@roseuid 41EDCB79022E
    */
   public WEB3InformAddInfoUnit() 
   {
    
   }
}
@
