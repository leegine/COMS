head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecSortKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キー項目定義インタフェイス(WEB3AdminDirSecSortKeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History :  2006/03/31 李俊(中訊) 新規作成   
Revesion History :  2008/04/18 車進(中訊) 仕様変更 モデルNo.116 
Revesion History :  2008/07/22 劉剣(中訊) 仕様変更 モデルNo.132
*/
package webbroker3.dirsec.define;

/**
 * (キー項目)<BR>
 * 
 * @@author 李俊(中訊)
 * @@version 1.0
 */
public interface WEB3AdminDirSecSortKeyItemDef
{
    
    /**
     * テーブル名
     */
    public static final String HOST_TABLE_NAME = "tableJpnName"; 
    
    /**
     * テーブル物理名
     */
    public static final String HOST_TABLE_PHYSICS_NAME = "tableName"; 
    
    /**
     * 識別コード
     */
    public static final String ORDER_REQUEST_NUMBER = "identityCode";
    
    /**
     * ステータス
     */
    public static final String BEFORESTATUS = "beforeStatus";
    
    /**
     * 作成日付
     */
    public static final String CREATED_TIMESTAMP = "createDate";

    /**
     * データコード
     */
    public static final String DATA_CODE = "dataCode";

    /**
     * 再発行可能フラグ
     */
    public static final String REISSUE_POSSIBLE_FLAG = "reissuePossibleFlag";

    /**
     * 伝票コード
     */
    public static final String REQUEST_CODE = "requestCode";

    /**
     * PTYPE
     */
    public static final String PTYTE = "pType";

    /**
     * AP下り処理名
     */
    public static final String AP_NAME = "apName";

}
@
