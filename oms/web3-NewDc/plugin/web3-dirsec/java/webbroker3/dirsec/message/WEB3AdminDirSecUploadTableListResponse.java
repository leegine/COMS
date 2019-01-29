head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecUploadTableListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者･アップロードテーブルレコード一覧レスポンス(WEB3AdminDirSecUploadTableListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/11周捷(中訊) 新規作成
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者･アップロードテーブルレコード一覧レスポンス)<BR>
 * 管理者・アップロードテーブルレコード一覧レスポンスクラス。
 * 
 * @@author 周捷(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecUploadTableListResponse extends WEB3GenResponse 
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "adminDirsec_UploadTable_List";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200608111321L;
	
	/**
	 * アップロードテーブルレコード詳細
	 */
	public WEB3AdminDirSecUploadTableUnit[] uploadTables;
	
    /**
     * @@roseuid 4158EB6602DD
     */
    public WEB3AdminDirSecUploadTableListResponse(
		WEB3AdminDirSecUploadTableListRequest l_request) 
    {
        super(l_request);
    }
}
@
