head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.26.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsOrderSubmitErrorCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 発注エラーコードの定数定義クラス(WEB3RlsOrderSubmitErrorCodeDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/09/16 劉(FLJ) 新規作成
 */
package webbroker3.rlsgateway.define;

/**
 * 発注エラーコードの定数定義クラス<br>
 *
 * @@author　@劉(FLJ)
 * @@version 1.0
 */
public interface WEB3RlsOrderSubmitErrorCodeDef
{

    /**
     * 正常発注
     */
    public static final String SUCCEED = null;

    /**
     * 発注遅延
     */
    public static final String DELAY = "01";

    /**
     * 遅延無視
     */
    public static final String FORCE_DELAY = "02";

    /**
     * そのた
     */
    public static final String OTHER = "03";

}
@
