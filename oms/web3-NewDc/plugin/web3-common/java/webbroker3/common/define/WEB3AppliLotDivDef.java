head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AppliLotDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込抽選区分(WEB3AppliLotDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 郭英(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * サービス申込登録テーブル.申込抽選区分
 *
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3AppliLotDivDef
{

    /**
     * 0：試用申込  　@
     */
    public final static String TRIAL_APPLI = "0";

    /**
     * 1：申込　@
     */
    public final static String APPLI = "1";
    
    /**
     * 2：当選／本申込　@　@
     */
    public final static String ELECTION_FORMAL_APPLI = "2";
    
    /**
     * 3：落選　@
     */
    public final static String DEFEAT = "3";

    /**
     * 5:自動当選　@
     */
    public final static String AUTO_ELECTION = "5";
    
}
@
