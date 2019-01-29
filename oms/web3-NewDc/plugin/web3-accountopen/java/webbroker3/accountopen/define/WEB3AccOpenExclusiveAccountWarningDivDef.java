head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.40.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenExclusiveAccountWarningDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設区分 定数定義インタフェイス(WEB3AccOpenExclusiveAccountWarningDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/07 陳琦 (中訊) 新規作成
*/

package webbroker3.accountopen.define;

/**
 * 警告区分 定数定義インタフェイス
 * 
 * @@author 陳琦(中訊)
 * @@version 1.0
 */

public interface WEB3AccOpenExclusiveAccountWarningDivDef
{
    /**
     * 0：警告なし
     */
    public static final String NOT_WARNING = "0";
    
    /**
     * 1：注意
     */
    public static final String NOTICE = "1";

}
@
