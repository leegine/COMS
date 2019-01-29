/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : NotifyTypeクラス(NotifyType.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/01 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;

/**
 * 通知経路
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public final class NotifyType extends Enum
{

    /**
     * 通知経路の整数値を定義した内部クラス
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class IntValues
    {

        /**
         * 通知経路：ルールエンジン
         */
        public static final int RULE_ENGINE = 0;

        /**
         * 通知経路：手動発注(顧客)
         */
        public static final int MANUAL_CUSTOMER = 1;

        /**
         * 通知経路：手動発注(オペレータ)
         */
        public static final int MANUAL_OPERATOR = 2;

        /**
         * 通知経路：手動発注(管理者)
         */
        public static final int MANUAL_ADMIN = 3;

        /**
         * 通知経路：監視(オンライン)
         */
        public static final int OBSERVER_ONLINE = 4;
        
    }

    /**
     * 通知経路：ルールエンジン
     */
    public static final NotifyType RULE_ENGINE = new NotifyType(
        IntValues.RULE_ENGINE,
        "RULE_ENGINE");

    /**
     * 通知経路：手動発注(顧客)
     */
    public static final NotifyType MANUAL_CUSTOMER = new NotifyType(
        IntValues.MANUAL_CUSTOMER,
        "MANUAL_CUSTOMER");

    /**
     * 通知経路：手動発注(オペレータ)
     */
    public static final NotifyType MANUAL_OPERATOR = new NotifyType(
        IntValues.MANUAL_OPERATOR,
        "MANUAL_OPERATOR");

    /**
     * 通知経路：手動発注(管理者)
     */
    public static final NotifyType MANUAL_ADMIN = new NotifyType(
        IntValues.MANUAL_ADMIN,
        "MANUAL_ADMIN");

    /**
     * 通知経路：監視(オンライン)
     */
    public static final NotifyType OBSERVER_ONLINE = new NotifyType(
        IntValues.OBSERVER_ONLINE,
        "OBSERVER_ONLINE");

    /**
     * コンストラクタ
     */
    private NotifyType(int l_intValue, String l_strValue)
    {
        super(l_intValue, l_strValue);
    }

}