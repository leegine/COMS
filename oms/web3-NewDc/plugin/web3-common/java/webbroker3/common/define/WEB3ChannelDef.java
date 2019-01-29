head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.45.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ChannelDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : チャネル定数定義クラス(WEB3ChannelDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 仲川 里織(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * チャネルの定数を定義する。
 *
 * @@author 仲川 里織(SRA)
 * @@version 1.0
 */
public interface WEB3ChannelDef
{

    /**
     * 営業店
     */
    public final static String BRANCH = "0";

    /**
     * インターネット
     */
    public final static String INTERNET = "1";
    
    /**
     * コールセンター
     */
    public final static String CALL_CENTER = "2";

    /**
     * モバイル
     */
    public final static String MOBILE = "3";

    /**
     * 定時定額
     */
    public final static String FIXED_TIME_AMOUNT = "5";
}
@
