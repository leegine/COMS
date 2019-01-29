head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecUploadTableUpdateCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・アップロードテーブル終了日時更新完了リクエスト(WEB3AdminDirSecUploadTableUpdateConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/11周捷(中訊) 新規作成
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・アップロードテーブル終了日時更新完了リクエスト)<BR>
 * 管理者・アップロードテーブル終了日時更新完了リクエストクラス。
 * 
 * @@author 周捷(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecUploadTableUpdateCompleteRequest extends WEB3GenRequest
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "adminDirsec_UploadTable_Complete";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200608111321L;
	
	/**
	 * ログユーティリティ<BR>
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
			WEB3AdminDirSecUploadTableUpdateCompleteRequest.class);
	
	/**
	 * (アップロードID)<BR>
	 * 選択されたレコードのアップロードIDの配列。
	 */
	public String[] administratorUploadId;
	
	/**
	 * (暗証番号)<BR>
	 * 暗証番号。
	 */
	public String password;
	
	/**
	 * 当リクエストデータの整合性チェックを行う。<BR>  
	 * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>  
	 * <BR>
	 * １） アップロードIDチェック<BR>  
	 * 　@１－１）this.アップロードID == null 又は this.アップロードIDの長さ==0 の場合、<BR>  
	 * 　@　@　@　@　@「更新対象レコードが選択されていません」の例外をスローする。<BR>  
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02523<BR>
	 * <BR>
	 * ２） 暗証番号チェック<BR> 
	 * 　@２－１）　@this.暗証番号 == nullの場合、<BR>  
	 * 　@　@　@　@　@「暗証番号が未指定です。」の例外をスローする。<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_01090<BR>
	 * @@throws WEB3BaseException
	 */
	public void validate() throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validate()";
		log.entering(STR_METHOD_NAME);
		//１） アップロードIDチェック
		//１－１）this.アップロードID == null 又は this.アップロードIDの長さ==0 の場合、
		//「更新対象レコードが選択されていません」の例外をスローする。
		if (administratorUploadId == null || administratorUploadId.length == 0)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02523, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"更新対象レコードが選択されていません。");
		}
		
		//２） 暗証番号チェック
		//２－１）　@this.暗証番号 == nullの場合、
		//「暗証番号が未指定です。」の例外をスローする。
		if (password == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01090, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"暗証番号が未指定です。");
		}
		log.exiting(STR_METHOD_NAME);
	}
	
	/**
	 * @@roseuid 44BE20C0033C
	 */
	public WEB3AdminDirSecUploadTableUpdateCompleteRequest()
	{

	}

	/**
	 * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
	 * <BR>
	 * 
	 * @@return レスポンスオブジェクト
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3AdminDirSecUploadTableUpdateCompleteResponse(this);
	}
}
@
