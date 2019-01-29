head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.26.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsRequestTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : リクエストタイプの定数定義クラス(WEB3RlsRequestTypeDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/09/16 劉(FLJ) 新規作成
 */
package webbroker3.rlsgateway.define;

/**
 * リクエストタイプの定数定義クラス<br>
 *
 * @@author　@劉(FLJ)
 * @@version 1.0
 */
public interface WEB3RlsRequestTypeDef
{

    /**
     * 注文約定
     */
    public static final int EXECUTE = 1;

    /**
     * 注文登録
     */
    public static final int NEW = 2;

    /**
     * 注文取消
     */
    public static final int CANCEL = 3;

    /**
     * 注文訂正
     */
    public static final int MOTIFY = 4;

}
@
