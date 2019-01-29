head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.26.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLoginLockDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログインロック区分(WEB3AccInfoLoginLockDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/12 何文敏 (中訊) 新規作成
*/

package webbroker3.accountinfo.define;

/**
 * ログインロック区分<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */

public interface WEB3AccInfoLoginLockDivDef
{
    /**
     * 0：全て
     */
    public final static String ALL = "0";

    /**
     * 1：ログインロック客
     */
    public final static String LOGIN_LOCK_ACCOUNT = "1";
}

@
