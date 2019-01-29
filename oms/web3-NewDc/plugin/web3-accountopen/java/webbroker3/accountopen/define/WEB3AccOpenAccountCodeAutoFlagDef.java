head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.40.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAccountCodeAutoFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客コード自動採番フラグ(WEB3AccOpenAccountCodeAutoFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/14 李俊 (中訊) 新規作成
*/

package webbroker3.accountopen.define;

/**
 * 顧客コード自動採番フラグ
 * 
 * @@author 李俊(中訊)
 * @@version 1.0
 */

public interface WEB3AccOpenAccountCodeAutoFlagDef {
    

    /**
     * 自動採番を行う
     */
    public static final String AUTO = "1";

    /**
     * 自動採番を行わない
     */
    public static final String NOT_AUTO = "0";

    

}
@
