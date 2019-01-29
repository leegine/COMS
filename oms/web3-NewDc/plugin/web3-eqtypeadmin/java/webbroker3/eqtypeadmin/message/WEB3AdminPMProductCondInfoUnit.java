head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄条件照会情報(WEB3AdminPMProductCondInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

/**
 * (銘柄条件照会情報)<BR>
 * <BR>
 * 銘柄条件照会情報クラス<BR>
 * <BR>
 * WEB3AdminPMProductCondInfoUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondInfoUnit extends Message
{

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondInfoUnit.class);

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
     * （銘柄登録情報一覧）
     * ----<English>--------------------
     * productRegistInfoList
     */
    public WEB3AdminPMProductRegistInfoUnit[] productRegistInfoList;

    /**
     * （銘柄条件照会情報）<BR>
     * <BR>
     * コンストラクタ<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
     * @@roseuid 41918404013C
     */
    public WEB3AdminPMProductCondInfoUnit()
    {

    }
}
@
