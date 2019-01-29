head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3InputOutputActionMarketDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴テーブルの市場区分インタフェイス(WEB3InputOutputActionMarketDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/19 凌建平(中訊) 作成
*/
package webbroker3.common.define;

/**
 * 入出庫履歴テーブルの市場区分インタフェイス
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3InputOutputActionMarketDivDef 
{

    /**
     * 0（入出庫データ）
     */
    public static final String SIO_DATA = "0";

    /**
     * 1（東京）
     */
    public static final String TOUKYOU = "1";
    
    /**
     * 2（大阪）
     */
    public static final String OSAKA = "2";

    /**
     * 3（名古屋）
     */
    public static final String NAGOYA = "3";
}
@
