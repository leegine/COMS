head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.53.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OpenOtcDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 店頭公開区分定数定義クラス(WEB3OpenOtcDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 和田　@友一(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 店頭公開区分の定数を定義する。
 *
 * @@author 和田　@友一(SRA)
 * @@version 1.0
 */
public interface WEB3OpenOtcDivDef
{
    /**
     * DEFAULT
     */
    public final static String DEFAULT = "0";

    /**
     * 登録
     */
    public final static String REGISRTATION = "1";
    
    /**
     * 管理銘柄
     */
    public final static String MANAGEMENT_PRODUCT = "2";

    /**
     * マーケットメイク銘柄
     */
    public final static String MARKET_MAKE_PRODUCT = "3";
}
@
