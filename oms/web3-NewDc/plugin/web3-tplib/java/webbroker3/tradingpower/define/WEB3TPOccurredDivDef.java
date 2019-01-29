head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.49.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPOccurredDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「発生区分」の定数定義クラス(WEB3TPOccurredDivDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/02/02 kikuchi(SCS) 新規作成
 */
package webbroker3.tradingpower.define;

/**
 * 「発生区分」の定数定義クラス<br>
 *
 * @@author kikuchi
 * @@version 1.0
 */
public interface WEB3TPOccurredDivDef
{

    /**
     * 業務アプリ
     */
    public static final String WORK_APP = "1";

    /**
     * バッチ
     */
    public static final String BATCH = "2";

    /**
     * 管理者
     */
    public static final String ADMIN = "3";

}
@
