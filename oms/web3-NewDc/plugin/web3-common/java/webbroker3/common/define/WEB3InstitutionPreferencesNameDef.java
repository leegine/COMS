head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.36.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3InstitutionPreferencesNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : プリファ@レンス名定数定義インタフェイス(WEB3InstitutionPreferencesNameDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/02 趙林鵬(中訊) 新規作成
Revision History : 2008/06/04 趙林鵬(中訊) ＤＢレイアウトNo.631
Revision History : 2008/08/19 趙林鵬(中訊) ＤＢレイアウトNo.639
Revision History : 2008/09/24 陸文静 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.650
Revision History : 2008/10/13 陸文静 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.656
Revision History : 2009/02/12 趙林鵬 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.671
Revision History : 2009/03/12 趙林鵬 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.676
Revision History : 2009/03/18 趙林鵬 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.680,683,684
Revision History : 2009/04/20 趙林鵬 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.686
Revision History : 2009/08/03 趙林鵬 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.689
Revision History : 2009/08/14 趙林鵬 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.691
Revision History : 2009/09/02 趙林鵬 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.695
Revision History : 2009/09/04 趙林鵬 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.696
Revision History : 2010/01/12 趙林鵬 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.701,702
Revision History : 2010/02/23 趙林鵬 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.704
*/

package webbroker3.common.define;

/**
 * プリファ@レンス名定数定義インタフェイス<BR>
 * （証券会社プリファ@レンステーブルのプリファ@レンス名の参考用）<BR>
 * <BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0
 */
public interface WEB3InstitutionPreferencesNameDef
{
    /**
     * srvregi.account.upload.maxcount：サービス利用：顧客アップロード可能件数
     */
    public final static String SRVREGI_ACCOUNT_UPLOAD_MAXCOUNT = "srvregi.account.upload.maxcount";

    /**
     * fx.aiolistcoursediv：入出金一覧FXコース区分
     */
    public final static String FX_AIOLISTCOURSEDIV = "fx.aiolistcoursediv";

    /**
     * accountinfo.commision.div.check:手数料区分チェック
     */
    public final static String ACCOUNTINFO_COMMISION_DIV_CHECK = "accountinfo.commision.div.check";

    /**
     * gft.accountopen.fxsystemcode:同時口座開設FXシステムコード
     */
    public final static String GFT_ACCOUNTOPEN_FXSYSTEMCODE = "gft.accountopen.fxsystemcode";

    /**
     * attention.info.comp.taking.div:注意情報強制取込実施
     */
    public final static String ATTENTION_INFO_COMP_TAKING_DIV= "attention.info.comp.taking.div";

    /**
     * accountopen.info.taking.div：口座開設区分情報取込実施
     */
    public final static String ACCOUNTOPEN_INFO_TAKING_DIV= "accountopen.info.taking.div";

    /**
     * fx.fxloginid.change.div：FXログインID変換実施
     */
    public final static String FX_FXLOGINID_CHANGE_DIV = "fx.fxloginid.change.div";

    /**
     * feq.order.emp.code.div：外国株式運用コード採番区分
     */
    public final static String FEQ_ORDER_EMP_CODE_DIV = "feq.order.emp.code.div";

    /**
     * accountopen.corporate.auto.div：口座開設法@人自動採番実施
     */
    public final static String ACCOUNTOPEN_CORPORATE_AUTO_DIV = "accountopen.corporate.auto.div";

    /**
     * accountopen.mailadd.reg.check.div：メールアドレス登録チェック
     */
    public final static String ACCOUNTOPEN_MAILADD_REG_CHECK_DIV = "accountopen.mailadd.reg.check.div";

    /**
     * accountopen.mail.send.div：法@人用メール送信
     */
    public final static String ACCOUNTOPEN_MAIL_SEND_DIV = "accountopen.mail.send.div";

    /**
     * feq.day.trade.div：外株日計り取引区分
     */
    public final static String FEQ_DAY_TRADE_DIV = "feq.day.trade.div";

    /**
     * feq.netting.div：外株ネッティング会社区分
     */
    public final static String FEQ_NETTING_DIV = "feq.netting.div";

    /**
     * accountopendate.update.determination：口座開設日更新判定区分
     */
    public final static String ACCOUNTOPENDATE_UPDATE_DETERMINATION = "accountopendate.update.determination";
}@
