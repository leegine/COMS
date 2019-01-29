head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMCompResultInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 比較結果情報 (WEB3AdminPMCompResultInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;

/**
 * （比較結果情報）<BR>
 * <BR>
 * 比較結果情報クラス<BR>
 * <BR>
 * WEB3AdminPMCompResultInfoUnit<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMCompResultInfoUnit extends WEB3AdminPMProductCondConfigCommon
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMCompResultInfoUnit.class);

   /**
    * （全件一致フラグ）<BR>
    * <BR>
    * 全件一致フラグ<BR>
    * <BR>
    * false：　@不一致<BR>
    * true：　@一致<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * allAgreementFlag<BR>
    * <BR>
    * false: FALSE<BR>
    * true: TRUE<BR>
    * <BR>
    */
   public boolean allAgreementFlag = true;



   /**
    * (コンストラクタ)<BR>
    * <BR>
    * コンストラクタ<BR>
    * <BR>
    * Constructor<BR>
    * <BR>
    * @@roseuid 4191B26F0115
    */
   public WEB3AdminPMCompResultInfoUnit()
   {

   }
}
@
