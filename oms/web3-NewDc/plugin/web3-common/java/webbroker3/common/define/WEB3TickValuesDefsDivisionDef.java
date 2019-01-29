head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.35.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TickValuesDefsDivisionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 呼値定義区分定義クラス(WEB3TickValuesDefsDivisionDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/18 髙橋　@良和(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 呼値定義区分定義クラス
 *
 * @@author 髙橋　@良和(SRA)
 * @@version 1.0
 */
public interface WEB3TickValuesDefsDivisionDef
{

    /**
     * 上場銘柄の値幅の場合
     */
    public final static int LISTING_PRICE_BID = 0;

    /**
     * JASDAQの値幅の場合
     */
    public final static int JASDAQ_PRICE_BID = 1;
}
@
