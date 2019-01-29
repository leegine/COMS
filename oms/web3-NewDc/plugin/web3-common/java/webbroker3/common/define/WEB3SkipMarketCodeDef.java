head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.01.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SkipMarketCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : スキップ市場コード　@定数定義インタフェイス(WEB3SkipMarketCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 鄒政 (中訊) 新規作成
Revesion History : 2008/12/05 趙林鵬(中訊) 株式仕様変更管理台帳・ＤＢレイアウトNo165
*/
package webbroker3.common.define;

/**
 * スキップ市場コード　@定数定義インタフェイス。
 *
 * @@author 鄒政 
 * @@version 1.0
 */
public interface WEB3SkipMarketCodeDef
{
    /**
     * 1:東京
     */
    public static final String TOKYO = "1";
    
    /**
     *  2:大阪及びNNM
     */
    public static final String OSAKA_AND_NNM = "2";

    /**
     * 3:名古屋
     */
    public static final String NAGOYA = "3";

    /**
     * 6:JASDAQ
     */
    public static final String JASDAQ = "6";
    
    /**
     * F:全市場
     */    
    public static final String FULL_MARKET = "F";
}
@
