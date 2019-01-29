head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.24.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DaemonTriggerTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : デーモントリガーテーブル・処理タイプ 定数定義インタフェイス(WEB3DaemonTriggerTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/16 凌建平 (中訊) 新規作成 共通仕様変更管理台帳(ＤＢレイアウト)No.351を対応
Revesion History : 2006/04/04 凌建平 (中訊) 修正 共通仕様変更管理台帳(ＤＢレイアウト)No.360-362を対応
Revesion History : 2006/05/11 凌建平 (中訊) 修正 ＤＢレイアウト(デーモントリガーテーブル)による
Revesion History : 2006/05/19 山田昌和 (SCS) 修正 ＤＢレイアウト(デーモントリガーテーブル)による
Revesion History : 2006/05/26 凌建平(中訊) 共通仕様変更・ＤＢレイアウト373
Revesion History : 2007/01/25 栄イ(中訊) 共通仕様変更・ＤＢレイアウト453
Revesion History : 2007/04/23 栄イ(中訊) 共通仕様変更・ＤＢレイアウト477、478
Revesion History : 2008/03/31 栄イ(中訊) 共通仕様変更・ＤＢレイアウト618
Revesion History : 2008/04/07 栄イ(中訊) 共通仕様変更・ＤＢレイアウト619
*/

package webbroker3.common.define;

/**
 * デーモントリガーテーブル・処理タイプ　@定数定義インタフェイス。
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3DaemonTriggerTypeDef
{
    /**
     * 1:約定(株・信用)
     */
    public static final String EXECUTE_EQUITY_MARGIN = "1";

    /**
     * 2:失効(株・信用)
     */
    public static final String EXPIRE_EQUITY_MARGIN = "2";

    /**
     * 3:注文受付（株・信用）
     */
    public static final String ORDER_ACCEPT_EQUITY_MARGIN = "3";

    /**
     * 4:訂正取消通知（株・信用）
     */
    public static final String CHANGE_CANCEL_EQUITY_MARGIN = "4";

    /**
     * 5:約定 (先物)
     */
    public static final String EXECUTE_FUTURE = "5";

    /**
     * 6:約定 (OPTION)
     */
    public static final String EXECUTE_OPTION = "6";

    /**
     * 7:失効(先物/OPTION)
     */
    public static final String EXPIRE_FUTURE_OPTION = "7";

    /**
     * 8:注文受付（先物.OPTION）
     */
    public static final String ORDER_ACCEPT_FUTURE_OPTION = "8";

    /**
     * 9:訂正取消通知（先物）
     */
    public static final String CHANGE_CANCEL_FUTURE = "9";

    /**
     * A:訂正取消通知（OPTION）
     */
    public static final String CHANGE_CANCEL_OPTION = "A";
    
    /**
     * B:余力更新
     */
    public static final String TRADING_POWER_UPDATE = "B";

    /**
     * C:訂正取消受付(株・信用)
     */
    public static final String CHANGE_CANCEL_ACCEPT_EQUITY_MARGIN = "C";

    /**
     * D:訂正取消受付(先物/OPTION)
     */
    public static final String CHANGE_CANCEL_ACCEPT_FUTURE_OPTION = "D";

    /**
     * E:1発デーモン（株・出来終了）
     */
    public static final String DAEMON_EQUITY_ORDER_EXEC_END= "E";

    /**
     * F:1発デーモン（株・注文繰越）
     */
    public static final String DAEMON_EQUITY_ORDER_CARRY_OVER = "F";

    /**
     * G:1発デーモン（OP・注文繰越）
     */
    public static final String DAEMON_OPTION_ORDER_CARRY_OVER = "G";

    /**
     * H:1発デーモン（先物・注文繰越）
     */
    public static final String DAEMON_FUTURE_ORDER_CARRY_OVER = "H";

    /**
     * J:余力更新（バッチ）
     */
    public static final String TRADING_POWER_UPDATE_BATCH = "J";
    
	/**
     * K:トリガー連続注文
     */
    public static final String TRIGGER_SUCC_ORDER = "K";

	/**
     * L:トリガー逆指値注文（Eqtype／Ifo）
     */
    public static final String EQTYPE_IFO_TRIGGER_STOP_ORDER = "L";

	/**
     * M：トリガー注文手動失効（Eqtype／Ifo）
     */
    public static final String EQTYPE_IFO_TRIGGER_MANUAL_EXPIRE = "M";

	/**
     * R:リッチクライアントプッシュ（Eqtype／Ifo）
     */
    public static final String EQTYPE_IFO_RICH_PUSH = "R";
    
	/**
     * V:1発デーモン（オリックス連携）
     */
    public static final String DAEMON_ORIX_CONNECT = "V";

	/**
     * P:オリックス入金ロード
     */
    public static final String ORIX_CASHIN_LOAD = "P";

	/**
     * O:オリックス入金通知
     */
    public static final String ORIX_CASHIN_NOTICE = "O";

	/**
     * N：手動失効（Eqtype）
     */
    public static final String EQTYPE_MANUAL_EXPIRE = "N";

	/**
     * U:1発デーモン（強制決済（オンライン開始前））
     */
    public static final String DAEMON_FORCED_SETTLE_BEFORE_ONLINE = "U";

    /**
     * W:手動失効（Ifo）
     */
    public static final String IFO_MANUAL_EXPIRE = "W";

    /**
     * Y：強制決済（承認）
     */
    public static final String FORCED_SETTLE_APPROVAL = "Y";

    /**
     * Z：強制決済（非承認）
     */
    public static final String FORCED_SETTLE_UNAPPROVAL = "Z";

    /**
     * j:書面未承諾 強制ログアウト
     */
    public static final String FPT_FORCE_LOGOUT = "j";
}
@
