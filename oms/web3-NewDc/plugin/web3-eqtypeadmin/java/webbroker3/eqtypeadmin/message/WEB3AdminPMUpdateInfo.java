head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.44.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMUpdateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 更新情報 (WEB3AdminPMUpdateInfo.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.HashMap;

import webbroker3.util.WEB3LogUtility;

/**
 * （更新情報）<BR>
 * <BR>
 * 更新情報クラス<BR>
 * ※AP層でのみ使用。<BR>
 * <BR>
 * -------<English>-------------<BR>
 * <BR>
 * WEB3AdminPMUpdateInfo class<BR>
 * ※It is used only in AP layer<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMUpdateInfo
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPMUpdateInfo.class);

    /**
     * （ID）<BR>
     * <BR>
     * ID<BR>
     * <BR>
     * id<BR>
     * <BR>
     */
    public long id;

    /**
     * （有効日）<BR>
     * <BR>
     * 有効日<BR>
     * ※更新対象が取引銘柄updqの場合はセット。<BR>
     * <BR>
     * --------<English>-----------<BR>
     * <BR>
     * expirationDate<BR>
     * ※Set if eqtype_traded_product table is to be updated<BR>
     * <BR>
     */
    public String expirationDate = null;

    /**
     * （更新列and値）<BR>
     * <BR>
     * key：更新列、value：更新値のHashMap<BR>
     * <BR>
     * key: updateRow, value: HashMap of updateValue<BR>
     * <BR>
     */
    public HashMap updateRowAndValue;

    /**
     * @@roseuid 41FE05EF00CB
     */
    public WEB3AdminPMUpdateInfo()
    {
        updateRowAndValue = new HashMap();
    }

    /**
     * （更新情報）<BR>
     * <BR>
     * コンストラクタ<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
     * @@return eqtypeadmin.message.UpdateInfo
     * @@roseuid 4190A84F0260
     */
    public WEB3AdminPMUpdateInfo updateInfo()
    {
        return new WEB3AdminPMUpdateInfo();
    }
}
@
