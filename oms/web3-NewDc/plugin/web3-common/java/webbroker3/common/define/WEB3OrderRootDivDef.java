head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.48.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderRootDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文経路区分定数定義クラス(WEB3OrderRootDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/03/01 菊地(SRA) 新規作成
Revesion History : 2006/09/14 栄イ(中訊) 仕様変更 ＤＢレイアウト115、116
Revesion History : 2007/02/13 キョウ再平(中訊) IVR対応
Revesion History : 2007/04/23 栄イ(中訊) 仕様変更 ＤＢレイアウト144
*/
package webbroker3.common.define;

/**
 * 注文経路区分 定数定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3OrderRootDivDef
{
    /**
     * コールセンター（顧客成りすまし）
     */
     public static final String CALLCENTER = "1";
     
     /**
      * PC（通常のブラウザ）
      */
     public static final String PC = "2";
     
     /**
      * スリングショット（売買連携による取引）
      */
     public static final String SLINGSHOT = "3";
     
     /**
      * i-mode
      */
     public static final String I_MODE = "4";
     
     /**
      * Vodafone
      */
     public static final String VODAFONE = "5";
     
     /**
      * AU
      */
     public static final String AU = "6";
     
    /**
     * スリングショット(無料)
     */
    public static final String SLINGSHOT_NO_TOLL = "7";
     
     /**
      * HOST
      */
     public static final String HOST = "9";
     
    /**
     * 管理者
     */
     public static final String ADMIN = "A";
     
    /**
     * 保証金自動振替バッチ
     */
     public static final String DEPOSIT_AUTO_TRANSFER_BATCH = "B";

    /**
     * C：リッチクライアント（RICH_CLIENT）
     */
    public static final String RICH_CLIENT = "C";

    /**
     * D：入金連携
     */
    public static final String CASH_IN_COOPERATION = "D";

    /**
     * E：振替注文(預り金から大証金
     */
    public static final String FROM_DEPOSIT_AMOUNT_DSK = "E";
    
    /**
     * F：IVR(自動応答電話）
     */
    public static final String IVR = "F";

    /**
     * G：強制決済
     */
    public static final String FORCED_SETTLE = "G";
}
@
