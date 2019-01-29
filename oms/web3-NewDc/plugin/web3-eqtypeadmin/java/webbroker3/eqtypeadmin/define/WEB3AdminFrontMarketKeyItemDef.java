head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontMarketKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 市場通知履歴明細ソートキー項目 定数定義インタフェイス(WEB3AdminFrontMarketKeyItemDef.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.define;

/**
 * 市場通知履歴明細ソートキー項目 定数定義インタフェイス
 *
 * @@author sudhindrakinnal
 * @@version 1.0
 */
public interface WEB3AdminFrontMarketKeyItemDef
{
    /**
     * 部店コード
     */
    public final static String BRANCH_CODE = "branchCode";

    /**
     * 口座コード
     */
    public final static String ACCOUNT_CODE = "accountCode";

    /**
     * 銘柄コード
     */
    public final static String PRODUCT_CODE = "productCode";

	/**
	 * データ種別コード
	 */
	public final static String DATA_CLASS_CODE = "dataClassCode";

	/**
	 * 仮想サーバNo
	 */
	public final static String VIRTUAL_SERVER_NUMBER = "virtualServerNumber";
    
    /**
     * 通知受信日時
     */
    public final static String CREATED_TIMESTAMP = "createdTimestamp";


}@
