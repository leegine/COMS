head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.03.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PriceRangeDefsDivisionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 値幅定義区分定義クラス(WEB3PriceRangeDefsDivisionDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/18 髙橋　@良和(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 値幅定義区分定義クラス
 *
 * @@author 髙橋　@良和(SRA)
 * @@version 1.0
 */
public interface WEB3PriceRangeDefsDivisionDef
{

    /**
     * 上場銘柄の値幅の場合
     */
    public final static int LISTING_PRICE_RANGE = 0;

    /**
     * JASDAQの値幅の場合
     */
    public final static int JASDAQ_PRICE_RANGE = 1;
}
@
