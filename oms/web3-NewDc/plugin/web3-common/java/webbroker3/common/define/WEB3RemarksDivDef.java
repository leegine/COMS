head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.45.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3RemarksDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴テーブルの摘要区分インタフェイス(WEB3RemarksDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/19 凌建平(中訊) 作成
*/
package webbroker3.common.define;

/**
 * 入出庫履歴テーブルの摘要区分インタフェイス
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3RemarksDivDef 
{

    /**
     * 31（振決課税）
     */
    public static final String CONFIRMED_TAXFEE = "31";

    /**
     * 34（振決（優））
     */
    public static final String CONFIRMED_SECURITIES = "34";
    
    /**
     * 36（振決特優）
     */
    public static final String CONFIRMED_TOKU_SECURITIES = "36";

    /**
     * 01（口座振替）
     */
    public static final String ACCOUNT_CHANGE = "01";

    /**
     * 02（株式分割）
     */
    public static final String EQUITY_DEVIDE = "02";

    /**
     * 05（有償増資）
     */
    public static final String ONEROUS_INCREASE = "05";

    /**
     * 06（額面引下）
     */
    public static final String FEE_BELOW = "06";

    /**
     * 08（合併）
     */
    public static final String MERGE = "08";

    /**
     * 09（一斉転換）
     */
    public static final String ALL_CHANGE = "09";

    /**
     * 20（買取請求）
     */
    public static final String BUY_TAKE_REQUEST = "20";

    /**
     * 30（非流通）
     */
    public static final String NOT_CURRENT = "30";
}
@
