head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondScheduleUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄条件予定情報 (WEB3AdminPMProductCondScheduleUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;

/**
 * (銘柄条件予定情報)<BR>
 * <BR>
 * 銘柄条件予定情報クラス<BR>
 * <BR>
 * WEB3AdminPMProductCondScheduleUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondScheduleUnit extends WEB3AdminPMProductCondConfigCommon
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondScheduleUnit.class);

    /**
     * （銘柄コード）<BR>
     * <BR>
     * 銘柄コード<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * （銘柄名）<BR>
     * <BR>
     * 銘柄名<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

    /**
     * （市場コード）<BR>
     * <BR>
     * 市場コード<BR>
     * <BR>
     * 0：　@その他(市場共通)<BR>
     * 1：　@東京<BR>
     * 2：　@大阪<BR>
     * 3：　@名古屋<BR>
     * 6：　@福岡<BR>
     * 8：　@札幌<BR>
     * 9：　@NNM<BR>
     * 10：　@JASDAQ<BR>
     * <BR>
     * --------<English>-------------------<BR>
     * <BR>
     * MarketCode<BR>
     * <BR>
     * 0: Def.DEFAULT(common to markets)<BR>
     * 1: Def.TOKYO<BR>
     * 2: Def.OSAKA<BR>
     * 3: Def.NAGOYA<BR>
     * 6: Def.FUKUOKA<BR>
     * 8: Def.SAPPORO<BR>
     * 9: Def.NNM<BR>
     * 10: Def.JASDAQ<BR>
     * <BR>
     */
    public String marketCode;

    /**
     * （登録値）<BR>
     * <BR>
     * 登録値(予定)<BR>
     * <BR>
     * 特定の小、大項目区分以外は、文字列がセットされる。<BR>
     * <BR>
     * 特定の小、大項目区分については、<BR>
     * 「【補足資料】株式銘柄条件設定登録値コード定義」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * registData<BR>
     * <BR>
     * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     * ※Set the latest DB data in AP layer<BR>
     * <BR>
     * Refer to "[Supplement] equity product condition regist data code def list" about
     * specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     */
    public String registData;

    /**
     * （予定の適用期間From）<BR>
     * <BR>
     * 予定の適用期間From<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * applyStartDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     */
    public String applyStartDate;

    /**
     * （予定の適用期間To）<BR>
     * <BR>
     * 予定の適用期間To<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * applyEndDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     */
    public String applyEndDate;

	/**
	 * （更新者コード）<BR>
	 * <BR>
	 * 更新者コード<BR>
	 */
	public String lastUpdater;

    /**
     * (コンストラクタ)<BR>
     * <BR>
     * コンストラクタ<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
     * @@roseuid 4194162E014A
     */
    public WEB3AdminPMProductCondScheduleUnit()
    {

    }
}
@
