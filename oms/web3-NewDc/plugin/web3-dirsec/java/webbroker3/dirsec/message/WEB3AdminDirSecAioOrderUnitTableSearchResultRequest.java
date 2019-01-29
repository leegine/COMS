head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAioOrderUnitTableSearchResultRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・注文単位テーブル検索結果リクエスト(WEB3AdminDirSecAioOrderUnitTableSearchResultRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  徐大方 (中訊) 新規作成
                 : 2007/01/08　@周捷(中訊) 仕様変更モデルNo.022
Revesion History : 2007/10/31  謝旋 (中訊) モデルNo.113
Revesion History : 2008/07/15  劉剣 (中訊) モデルNo.130
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.define.WEB3AdminDirOrderUnitTblKbnDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・注文単位テーブル検索結果リクエスト)<BR>
 * 管理者・注文単位テーブル検索結果リクエストクラス。<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecAioOrderUnitTableSearchResultRequest extends WEB3GenRequest
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "admin_dirsec_aio_order_unit_table_search_result";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200607192121L;

	/**
	 * ログユーティリティ<BR>
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
		WEB3AdminDirSecAioOrderUnitTableSearchResultRequest.class);

    /**
     * (注文単位テーブル区分)<BR>
     * 注文単位テーブル区分。 <BR>
     * <BR>
     * 0：　@外株<BR>
     * 1：　@入出金<BR>
     * 2：　@投信<BR>
     * 3：  株式<BR>
     * 4：  先物OP<BR>
     */
    public String orderUnitTblKbn;

	/**
	 * (注文単位ID)<BR>
	 * 注文単位ID。<BR>
	 */
	public String orderUnitId;

	/**
	 * @@roseuid 44BE20BF02CE
	 */
	public WEB3AdminDirSecAioOrderUnitTableSearchResultRequest()
	{

	}

	/**
	 * 当リクエストデータの整合性チェックを行う。 <BR>
	 * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
	 * <BR>
	 * １）注文単位IDチェック <BR>
	 * １－１）this.注文単位ID == null の場合、 <BR>
	 * 「注文単位IDが未入力です。」の例外をスローする。<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02505<BR>
	 * <BR>
	 * １－２）this.注文単位IDの桁数 > 18 の場合、 <BR>
	 * 「注文単位IDの桁数が不正です。」の例外をスローする。<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02506<BR>
	 * <BR>
	 * １－３）this.注文単位IDが数字以外の値であった場合、 <BR>
	 * 「注文単位IDが数字以外の値です。」の例外をスローする。 <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02507<BR>
     * <BR>
     * ２）注文単位テーブル区分チェック<BR>
     * 　@２－１）this.注文単位テーブル区分 == null の場合、<BR>
     * 　@　@　@　@　@「注文単位テーブルが未選択です。」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02704<BR>
     * <BR>
     * 　@２－２）this.注文単位テーブル区分 !=（0、1、2、3、4） の場合、<BR>
     * 　@　@　@　@　@「注文単位テーブルが存在しない区分です。」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02705<BR>
     * 
	 * @@throws WEB3BaseException
	 * @@roseuid 44B6F995004B
	 */
	public void validate() throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validate()";
		log.entering(STR_METHOD_NAME);

		// １）注文単位IDチェック
		// １－１）this.注文単位ID == null の場合、
		// 「注文単位IDが未入力です。」の例外をスローする。
		if (this.orderUnitId == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02505,
				this.getClass().getName() + STR_METHOD_NAME, 
				"注文単位IDが未入力です。");
		}

		// １－２）this.注文単位IDの桁数 > 18 の場合、
		// 「注文単位IDの桁数が不正です。」の例外をスローする。
		if (this.orderUnitId.length() > 18)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02506, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"注文単位IDの桁数が不正です。");
		}

		// １－３） this.注文単位IDが数字以外の値であった場合、
		// 「注文単位IDが数字以外の値です。」の例外をスローする。
		if (!WEB3StringTypeUtility.isDigit(this.orderUnitId))
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02507, 
				this.getClass().getName() + STR_METHOD_NAME,
				"注文単位IDが数字以外の値です。");
		}

        //２）注文単位テーブル区分チェック
        //　@２－１）this.注文単位テーブル区分 == null の場合、
        //　@　@　@　@　@「注文単位テーブルが未選択です。」の例外をスローする。
        if (this.orderUnitTblKbn == null)
        {
            log.debug("注文単位テーブルが未選択です。" + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02704,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文単位テーブルが未選択です。");
        }

        //２－２）this.注文単位テーブル区分 !=（0、1、2、3、4） の場合、
        //   　@　@「注文単位テーブルが存在しない区分です。」の例外をスローする。
        if (!WEB3AdminDirOrderUnitTblKbnDef.FEQ.equals(this.orderUnitTblKbn)
            && !WEB3AdminDirOrderUnitTblKbnDef.AIO.equals(this.orderUnitTblKbn)
            && !WEB3AdminDirOrderUnitTblKbnDef.MUTUAL.equals(this.orderUnitTblKbn)
            && !WEB3AdminDirOrderUnitTblKbnDef.EQ.equals(this.orderUnitTblKbn)
            && !WEB3AdminDirOrderUnitTblKbnDef.IFO.equals(this.orderUnitTblKbn))
        {
            log.debug("注文単位テーブルが存在しない区分です。" + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02705,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文単位テーブルが存在しない区分です。");
        }
	}

	/**
	 * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
	 * <BR>
	 * 
	 * @@return レスポンスオブジェクト
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3AdminDirSecAioOrderUnitTableSearchResultResponse(this);
	}
}
@
