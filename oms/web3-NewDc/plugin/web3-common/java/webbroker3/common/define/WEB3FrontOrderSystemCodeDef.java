head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.33.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FrontOrderSystemCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : フロント発注システム区分定数定義インタフェイス(WEB3FrontOrderSystemCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 孟東(中訊)　@新規作成
Revesion History : 2009/05/21 趙林鵬(中訊)【共通】仕様変更管理台帳ＤＢレイアウト.687
*/
package webbroker3.common.define;

/**
 * フロント発注システム区分 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3FrontOrderSystemCodeDef
{
    /**
     * 1:東証（株式のみ)、名証（株式のみ)
     */
    public static final String TOKYO_NAGOYA = "1";

    /**
     * 1:大証
     */
    public static final String OSAKA_EXCHANGE_STOCK = "1";

    /**
     * 2:東証(先OPのみ)、名証(先OPのみ)
     */
    public static final String TOKYO_STOCK_JASDAQ_AUCTION = "2";

    /**
     * 3:JASDAQ-MM
     */
    public static final String JASDAQ_MM = "3";

    /**
     * 4:ヘラクレス
     */
    public static final String HERCULES = "4";

    /**
     * 5：JASDAQ
     */
    public static final String JASDAQ = "5";

    /**
     * 9：その他
     */
    public static final String OTHERS = "9";
}
@
