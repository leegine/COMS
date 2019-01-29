head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.26.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsNotifyStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「処理区分」の定数定義クラス(WEB3RlsNotifyStatusDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/09/16 劉(FLJ) 新規作成
 */
package webbroker3.rlsgateway.define;

/**
 * 「処理区分」の定数定義クラス<br>
 *
 * @@author　@劉(FLJ)
 * @@version 1.0
 */
public interface WEB3RlsNotifyStatusDef
{

    /**
     * 未処理
     */
    public static final String NOT_DEAL = "0";

    /**
     * 処理済
     */
    public static final String DEAL = "1";

    /**
     * ルールエンジン受付
     */
    public static final String RLS_ACCEPT = "2";

    /**
     * プログラムエラー
     */
    public static final String PROGRAM_ERROR = "8";

    /**
     * データエラー
     */
    public static final String DATA_ERROR = "9";

}
@
