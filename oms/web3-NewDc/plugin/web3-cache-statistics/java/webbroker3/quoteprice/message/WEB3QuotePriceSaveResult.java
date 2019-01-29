head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceSaveResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : レスポンスクラス(WEB3QuotePriceSaveResult.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/11/06 劉 (FLJ)新規作成
 */

package webbroker3.quoteprice.message;

import com.fitechlabs.xtrade.kernel.message.*;

/**
 * （レスポンスクラススクラス）。<br>
 * <br>
 * レスポンスクラス
 * @@author 劉 (FLJ)
 * @@version 1.0
 */
public class WEB3QuotePriceSaveResult
    extends Message
{

    /**
     * (会社コード)
     */
    public String institutionCd;

    /**
     * (成功数)
     */
    public Long success;

    /**
     * (失敗数)
     */
    public Long failure;

    /**
     * デフォルトコンストラクタ<BR>
     */
    public WEB3QuotePriceSaveResult()
    {
    }

}
@
