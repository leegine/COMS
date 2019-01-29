head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.03.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ValidFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 有効区分  定数定義インタフェイス(WEB3ValidFlagDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/28 魏キン(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 有効区分 定数定義インタフェイス
 *
 * @@author 魏キン(中訊)
 * @@version 1.0
 */
public interface WEB3ValidFlagDef                
{
    /**
     *  0：valid
     */
    public static final String  VALID = "0";

    /**
     *  1:invalid
     */
    public static final String  INVALID = "1";

}
@
