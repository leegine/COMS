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
filename	WEB3AdminPMProductRegistInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄登録情報 (WEB3AdminPMProductRegistInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;

/**
 * (銘柄登録情報)<BR>
 * <BR>
 * 銘柄登録情報クラス<BR>
 * <BR>
 * WEB3AdminPMProductRegistInfoUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductRegistInfoUnit extends WEB3AdminPMProductCondConfigCommon
{

    /**
    * Log Variable.<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductRegistInfoUnit.class);

    /**
        * （登録値）<BR>
        * <BR>
        * 登録値<BR>
        * <BR>
        * 特定の小、大項目区分以外は、文字列がセットされる。<BR>
        * <BR>
        * 特定の小、大項目区分については、<BR>
        * 「【補足資料】株式銘柄条件設定登録値コード定義」<BR>
        * 参照<BR>
        * <BR>
        * ----<English>--------------------<BR>
        * <BR>
        * registData<BR>
        * <BR>
        * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
        * <BR>
        * ※Set the latest DB data in AP layer<BR>
        * <BR>
        * Refer to "[Supplement] equity product condition regist data code def list" about
        * specific smallItemDiv and largeItemDiv<BR>
        * <BR>
        */
    public String registData;

   /**
   * @@roseuid 41FD8F5B01C5
   */
    public WEB3AdminPMProductRegistInfoUnit()
    {

    }
}
@
