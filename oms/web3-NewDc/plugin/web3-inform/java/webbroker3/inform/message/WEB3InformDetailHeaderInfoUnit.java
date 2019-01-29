head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformDetailHeaderInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 各種連絡詳細情報クラス(WEB3InformDetailHeaderInfoUnit.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/20 凌建平(中訊) 作成
*/

package webbroker3.inform.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (各種連絡詳細情報)<BR>
 * 各種連絡詳細情報クラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3InformDetailHeaderInfoUnit extends WEB3InformDetailInfoUnit 
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformDetailHeaderInfoUnit.class);
   
   /**
    * (識別コード)
    */
   public String requestNumber;
   
   /**
    * (受付日時)
    */
   public Date receptionDate;
   
   /**
    * @@roseuid 41EF45B901E4
    */
   public WEB3InformDetailHeaderInfoUnit() 
   {
    
   }
   
   /**
    * (各種連絡詳細情報)<BR>
    * コンストラクタ<BR>
    * <BR>
    * 各項目に、引数.各種連絡行オブジェクトの同項目の値をセットする。<BR>
    * @@param l_variousInformParams - (各種連絡行)<BR>
    * 各種連絡行オブジェクト<BR>
    * @@roseuid 41EE0D380173
    */
   public WEB3InformDetailHeaderInfoUnit(VariousInformParams l_variousInformParams) throws WEB3BaseException 
   {
       super(l_variousInformParams);
       
       final String STR_METHOD_NAME = "WEB3InformDetailHeaderInfoUnit()";
       log.entering(STR_METHOD_NAME);

       // 識別コード
       this.requestNumber = l_variousInformParams.getRequestNumber();

       // 受付日時
       this.receptionDate = l_variousInformParams.getCreatedTimestamp();
       
       log.exiting(STR_METHOD_NAME);
   }
}
@
