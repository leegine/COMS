head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecUploadTableUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : アップロードテーブルレコード詳細(WEB3AdminDirSecUploadTableUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/11周捷(中訊) 新規作成
*/
package webbroker3.dirsec.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * アップロードテーブルレコード詳細<BR>
 * 
 * @@author 周捷(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecUploadTableUnit extends Message
{
	/**
	 * (アップロードID)<BR>
	 * アップロードID。
	 */
	public String administratorUploadId;
	
	/**
	 * (証券会社コード)<BR>
	 * 証券会社コード。
	 */
	public String institutionCode;
	
	/**
	 * (部店コード)<BR>
	 * 部店コード。
	 */
	public String branchCode;
	
	/**
	 * (銘柄タイプ)<BR>
	 * 銘柄タイプ。
	 */
	public String productType;
	
	/**
	 * (アップロード開始日時)<BR>
	 * アップロード開始日時。
	 */
	public String uploadStartTimestamp;
	
	/**
	 * (アップロード終了日時)<BR>
	 * アップロード終了日時。
	 */
	public String uploadEndTimestamp;
	
	/**
	 * (メッセージコード)<BR>
	 * メッセージコード。
	 */
	public String messageCode;
	
	/**
	 * (アップロード件数)<BR>
	 * アップロード件数。
	 */
	public String uploadCount;
	
	/**
	 * (更新者コード)<BR>
	 * 更新者コード。
	 */
	public String lastUpdater;
	
	/**
	 * (データキー)<BR>
	 * データキー。
	 */
	public String uploadKey;
}
@
