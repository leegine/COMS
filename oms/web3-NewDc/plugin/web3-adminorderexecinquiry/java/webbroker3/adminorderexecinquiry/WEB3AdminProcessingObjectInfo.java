head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminProcessingObjectInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理対象情報(WEB3AdminProcessingObjectInfo.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.adminorderexecinquiry;

/**
 * (処理対象情報)<BR>
 * <BR>
 * 処理対象情報クラス<BR>
 * <BR>
 * ※AP層でのみ使用。<BR>
 * <BR>
 * ----<English>-------------<BR>
 * <BR>
 * ProcessingObjectInfo<BR>
 * <BR>
 * ※It is used only in AP layer<BR>
 * <BR>
 * @@author Amarnath
 * @@version 1.0
 */
public class WEB3AdminProcessingObjectInfo
{

    /**
     * (発注日一覧)<BR>
     * <BR>
     * 発注日の配列<BR>
     * <BR>
     * ----<English>---------<BR>
     * <BR>
     * orderBizDateList<BR>
     * <BR>
     * An array of bizDate<BR>
     * <BR>
     */
    public String[] orderBizDateList;

    /**
     * (注文経路区分一覧)<BR>
     * <BR>
     * 注文経路区分の配列<BR>
     * <BR>
     * ----<English>---------<BR>
     * <BR>
     * orderRootDivList<BR>
     * <BR>
     * An array of orderRootDiv<BR>
     * <BR>
     */
    public String[] orderRootDivList = null;

    /**
     * (市場コード一覧)<BR>
     * <BR>
     * 市場コードの配列<BR>
     * <BR>
     * ----<English>---------<BR>
     * <BR>
     * marketCodeList<BR>
     * <BR>
     * An array of marketCode<BR>
     * <BR>
     */
    public String[] marketCodeList = null;

    /**
     * @@roseuid 4213045C0334
     */
    public WEB3AdminProcessingObjectInfo()
    {

    }
}
@
