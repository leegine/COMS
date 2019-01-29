head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.46.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiHashCalOrderDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ハッシュ計算手順区分(WEB3SrvRegiHashCalOrderDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 李頴淵(sinocom) 新規作成
Revesion History : 2006/06/14 凌建平(中訊) 仕様変更・ＤＢレイアウトNo.023
Revesion History : 2009/04/23 車進(中訊)  　@仕様変更・モデルNo.411
*/
package webbroker3.srvregi.define;

/**
 * ハッシュ計算手順区分
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3SrvRegiHashCalOrderDivDef
{
    /**
     * 0：指定無　@
     */
    public final static String DEFAULT = "0";
    
    /**
     * 1：電子鳩　@
     */
    public final static String ELE_PIGEON = "1";
    
    /**
     * 2：通常計算（１）　@
     */
    public final static String NORMAL1 = "2";
    
    /**
     * 3：通常計算（２）　@
     */
    public final static String NORMAL2 = "3";
    
    /**
     * 4：２段階計算　@
     */
    public final static String TWO_STEP_CALCULATION = "4";

    /**
     * 5：ログイン認証　@
     */
    public final static String LOGIN_CERTIFICATION = "5";

    /**
     * 7：シングルサインオン連携　@
     */
    public final static String SINGLE_SIGNON_COOPERATION = "7";
}
@
