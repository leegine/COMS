head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.46.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiAppliLotDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込抽選区分(WEB3SrvRegiAppliLotDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 李頴淵(sinocom) 新規作成
Revesion History : 2007/06/19 崔遠鵬(sinocom) 仕様変更モデルNo.249
*/
package webbroker3.srvregi.define;

/**
 * 申込抽選区分
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3SrvRegiAppliLotDivDef
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
     * 4：取消　@
     */
    public final static String CANCEL = "4";

    /**
     * 5:自動当選　@
     */
    public final static String AUTO_ELECTION = "5";
    
    /**
     * 6：全て　@
     */
    public final static String EVERYTHING = "6";

    /**
     * 7：無料対象
     */
    public final static String FREE_OBJECT = "7";

    /**
     * 8：申込不可
     */
    public final static String CANNOT_APPLI = "8";

    /**
     * 9：全て（属性用）
     */
    public final static String EVERYTHING_ATTRIBUTE = "9";
}
@
