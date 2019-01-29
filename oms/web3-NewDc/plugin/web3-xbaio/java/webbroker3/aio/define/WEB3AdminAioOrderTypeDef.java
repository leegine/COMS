head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.47.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioOrderTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文種別定数定義(WEB3AdminAioOrderTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/03 何文敏 (中訊) 新規作成　@仕様変更モデル NO.693
*/
package webbroker3.aio.define;

/**
 * 注文種別定数定義
 *                                                                     
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminAioOrderTypeDef
{
    /**
     * 000：全て
     */
    public static final String ALL = "000";
    
    /**
     * 100：入金全て
     */
    public static final String CASHIN_ALL = "100";
    
    /**
     * 101：SONAR入金
     */
    public static final String SONAR_CASHIN = "101";
    
    /**
     * 102：バーチャル入金
     */
    public static final String VIRTUAL_CASHIN = "102";
    
    /**
     * 103：ネット入金
     */
    public static final String NET_CASHIN = "103";
    
    /**
     * 104：振替（株先証拠金から預り金）
     */
    public static final String CASHIN_TRANSFER_DEPOSIT_TO_MARGIN = "104";
    
    /**
     * 105：為替保証金振替（為替保証金から預り金）
     */
    public static final String CASHIN_FX_GUARANTY_TO_DESPOSIT= "105";
    
    /**
     * 106：その他振替（Xから預り金）
     */
    public static final String CASHIN_OTHER_X_TO_ACCOUNT_BALANCE = "106";
    
    /**
     * 200：出金全て
     */
    public static final String CASHOUT_ALL = "200";
    
    /**
     * 201：出金
     */
    public static final String CASHOUT = "201";
    
    /**
     * 202：振替（預り金から株先証拠金）
     */
    public static final String CASHOUT_TRANSFER_DEPOSIT_TO_MARGIN = "202";
    
    /**
     * 203：為替保証金振替（預り金から為替保証金）
     */
    public static final String CASHOUT_FX_GUARANTY_TO_DESPOSIT = "203";
    
    /**
     * 204：その他振替（預り金からX)
     */
    public static final String CASHOUT_OTHER_X_TO_ACCOUNT_BALANCE = "204";
}
@
