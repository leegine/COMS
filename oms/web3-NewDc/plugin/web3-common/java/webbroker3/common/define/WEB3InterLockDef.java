head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.30.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3InterLockDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連動定数定義インタフェイス(WEB3InterLockDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/23 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 連動定数定義インタフェイス<BR>
 * (代用振替請求キューテーブルの連動の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3InterLockDef
{
    /**
     * ブランク：指定無し
     */
    public final static String DEFAULT = " ";

    /**
     * 0：機@構連動
     */
    public final static String INSTITUTE_INTERLOCKING_MOVEMENT = "0";
}@
