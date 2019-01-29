head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.26.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsNotifyOrderTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 条件付注文タイプの定数定義クラス(WEB3RlsNotifyOrderTypeDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/09/16 劉(FLJ) 新規作成
 */
package webbroker3.rlsgateway.define;

/**
 * 条件付注文タイプの定数定義クラス<br>
 *
 * @@author　@劉(FLJ)
 * @@version 1.0
 */
public interface WEB3RlsNotifyOrderTypeDef
{

    /**
     * 連続注文
     */
    public static final int EXECUTE = 1;

    /**
     * OCO注文
     */
    public static final int OCO = 2;

    /**
     * IFD注文
     */
    public static final int IFD = 3;

    /**
     * 逆指値
     */
    public static final int STOP_LlIMIT = 4;

    /**
     * W指値
     */
    public static final int W_LlIMIT = 5;

}
@
