head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioGftOperationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理区分定数定義（WEB3AdminAioGftOperationDivDef.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/27 武波 (中訊) 新規作成 モデル1040
Revision History : 2009/06/26 武波 (中訊) 新規作成 モデル1171
*/
package webbroker3.aio.define;

/**
 * 処理区分定数定義
 *
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3AdminAioGftOperationDivDef
{
    /**
     * 01：口座開設
     */
    public static final String ACCOUNT_OPEN = "01";

    /**
     * 02：入金
     */
    public static final String CASH_IN = "02";

    /**
     * 03：口座追加
     */
    public static final String ADD_ACCOUNT = "03";

    /**
     * 04：出金
     */
    public static final String CASH_OUT = "04";

    /**
     * 07：振替可能額
     */
    public static final String TRANSFER_ABLE_AMT = "07";
}
@
