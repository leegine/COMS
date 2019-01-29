head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondSettingUpdateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄更新情報 (WEB3AdminPMProductCondSettingUpdateInfo.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.HashMap;
import java.util.ArrayList;

import webbroker3.util.WEB3LogUtility;

/**
 * （銘柄条件設定更新情報）<BR>
 * <BR><BR>
 * 銘柄条件設定更新情報クラス
 * ※AP層でのみ使用<BR>
 * <BR>
 * -----<English>-----<BR>
 * <BR>
 * ProductCondSettingUpdateInfo class<BR>
 * ※It is used only in AP layer<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMProductCondSettingUpdateInfo
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondSettingUpdateInfo.class);

    /**
     * （銘柄更新情報）<BR>
     * <BR>
     * 銘柄の更新情報<BR>
     * <BR>
     * productUpdateInfo<BR>
     * <BR>
     */
    public WEB3AdminPMUpdateInfo productUpdateInfo = null;

    /**
     * （株式銘柄更新情報）<BR>
     * <BR>
     * 株式銘柄の更新情報<BR>
     * <BR>
     * equityProductUpdateInfo<BR>
     * <BR>
     */
    public WEB3AdminPMUpdateInfo equityProductUpdateInfo = null;

    /**
     * （取引銘柄更新情報）<BR>
     * <BR>
     * 取引銘柄更新情報<BR>
     * <BR>
     * key：　@市場コード<BR>
     * value：　@更新情報<BR>
     * <BR>
     * ----<English>----------<BR>
     * <BR>
     * tradeProductUpdateInfo<BR>
     * <BR>
     * key: productCode<BR>
     * value: updateInfo<BR>
     * <BR>
     */
    public HashMap tradeProductUpdateInfo;

    /**
     * （取引銘柄(翌日)更新情報）<BR>
     * <BR>
     * 取引銘柄(翌日)更新情報<BR>
     * <BR>
     * key：　@市場コード<BR>
     * value：　@更新情報<BR>
     * <BR>
     * ----<English>-------<BR>
     * <BR>
     * tradeProductNextUpdateInfo<BR>
     * <BR>
     * key: marketCode<BR>
     * value: updateInfo<BR>
     * <BR>
     */
    public HashMap tradeProductNextUpdateInfo;

    /**
     * （取引銘柄(翌々日)更新情報）<BR>
     * <BR>
     * 取引銘柄(翌々日)更新情報<BR>
     * <BR>
     * key：　@市場コード<BR>
     * value：　@更新情報<BR>
     * <BR>
     * ----<English>---------------<BR>
     * <BR>
     * tradeProductNext2UpdateInfo<BR>
     * <BR>
     * key: marketCode<BR>
     * value: updateInfo<BR>
     * <BR>
     */
    public HashMap tradeProductNext2UpdateInfo;

    /**
     * （予定更新情報）<BR>
     * <BR>
     * 更新する銘柄条件設定Paramsを格納する。<BR>
     * <BR>
     * Store eqtypeProductConditionParam to update<BR>
     * <BR>
     */
    public ArrayList scheduleUpdateInfo;

    /**
     * （銘柄条件設定更新情報）<BR>
     * <BR>
     * コンストラクタ<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
     * @@roseuid 418AFEE4028A
     */
    public WEB3AdminPMProductCondSettingUpdateInfo()
    {

    }
}
@
