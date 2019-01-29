head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.40.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenCheckStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 審査状況区分 定数定義インタフェイス(WEB3AccOpenCheckStateDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/11 黄建 (中訊) 新規作成
*/

package webbroker3.accountopen.define;

/**
 * 審査状況区分 定数定義インタフェイス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public interface WEB3AccOpenCheckStateDivDef
{
    /**
     * 1：審査待ち
     */
    public static final String JUDGE_WAITING = "1";
    
    /**
     * 2：審査済み
     */
    public static final String JUDGE_COMPLETE = "2";
    
    /**
     * 3：承認済
     */
    public static final String APPROVAL_COMPLETE = "3";
    
    /**
     * 4：否認済
     */
    public static final String OPEN_COMPLETE = "4";
}
@
