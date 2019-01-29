head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.45.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3InvestDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 運用区分(WEB3InvestDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 郭英(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * サービス抽選情報テーブル.運用区分
 *
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3InvestDivDef
{

    /**
     * 0:無条件当選　@　@
     */
    public final static String NO_CONDITIONS_ELECTION = "0";

    /**
     * 1:通常運用（抽選有）　@　@
     */
    public final static String USUAL_SELECTION_LOT = "1";
    
    /**
     * 2:通常運用（抽選有-オークション）
     */
    public final static String USUAL_SELECTION_LOT_AUCTION = "2";
    
}
 
@
