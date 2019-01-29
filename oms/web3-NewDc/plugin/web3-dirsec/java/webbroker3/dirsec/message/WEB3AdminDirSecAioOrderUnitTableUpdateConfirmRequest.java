head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・注文単位テーブル注文状態更新確認リクエスト(WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  徐大方 (中訊) 新規作成
				   2006/08/11  徐大方 (中訊) 式樣變更・モデル014
Revesion History : 2007/10/31  謝旋 (中訊) モデルNo.113
Revesion History : 2008/07/15  劉剣 (中訊) モデルNo.130
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・注文単位テーブル注文状態更新確認リクエスト)<BR>
 * 管理者・注文単位テーブル注文状態更新確認リクエストクラス。<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest extends WEB3GenRequest
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "admin_dirsec_aio_order_unit_table_update_confirm";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200607192121L;

	/**
	 * ログユーティリティ<BR>
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
		WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest.class);

	/**
	 * (更新_注文状態)<BR>
	 * 更新する注文状態<BR>
	 */
	public String updateOrderStatus;

	/**
	 * (更新_注文有効状態)<BR>
	 * 更新する注文有効状態<BR>
	 */
	public String updateOrderOpenStatus;

    /**
     * (更新_約定状態)<BR>
     * 更新_約定状態
     */
    public String updateExecStatus;

    /**
     * (更新_発注日)<BR>
     * 更新する発注日<BR>
     */
    public String updateOrderBizDate;

	/**
	 * @@roseuid 44BE20BF037A
	 */
	public WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest()
	{

	}

	/**
	 * 当リクエストデータの整合性チェックを行う。 <BR>
	 * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
	 * <BR>
	 * １）注文状態チェック<BR>
	 * １－１） WEB3StringTypeUtility.isEmpty(this.更新_注文状態) == false の場合、<BR>
	 * 以下のチェックを行う。<BR>
	 * １－１－１） this.更新_注文状態が数字以外の値であった場合、<BR>
	 * 「注文状態が数字以外の値です。」の例外をスローする。<BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02508<BR>
	 * <BR>
	 * ２）注文有効状態チェック <BR>
	 * ２－１） WEB3StringTypeUtility.isEmpty(this.更新_注文有効状態) == false の場合、<BR>
	 * 以下のチェックを行う。<BR> 
	 * ２－１－１）this.更新_注文有効状態が数字以外の値であった場合、<BR>
	 * 「注文有効状態が数字以外の値です。」の例外をスローする。<BR> 
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02509<BR>
	 * <BR>
     * ３）約定状態チェック<BR>
     * ３－１）　@WEB3StringTypeUtility.isEmpty(this.更新_約定状態) == false　@の場合、<BR>
     * 　@　@以下のチェックを行う。<BR>
     * ３－１－１）this.更新_約定状態が数字以外の値であった場合、<BR>
     * 　@　@　@　@　@「約定状態が数字以外の値です。」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02951<BR>
     * <BR>
     * ４）発注日チェック <BR>
　@   * ４－１）　@WEB3StringTypeUtility.isEmpty(this.更新_発注日) == false　@の場合、 <BR>
　@　@ * 　@　@以下のチェックを行う。 <BR>
　@　@ * ４－１－１）this.更新_発注日の桁数 != 8 であった場合、 <BR>
　@　@ * 　@　@　@　@　@「発注日の桁数が不正です。」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02160<BR>
     * 
	 * @@throws WEB3BaseException
	 * @@roseuid 44475D860117
	 */
	public void validate() throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validate()";
		log.entering(STR_METHOD_NAME);

		// １）注文状態チェック
		// １－１） WEB3StringTypeUtility.isEmpty(this.更新_注文状態) == false の場合、
		// 以下のチェックを行う。
		// １－１－１）this.更新_注文状態チェックが数字以外の値であった場合、
		// 「注文状態が数字以外の値です。」の例外をスローする。
		if (!WEB3StringTypeUtility.isEmpty(this.updateOrderStatus))
		{
			if (!WEB3StringTypeUtility.isDigit(this.updateOrderStatus))
			{
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02508,
					this.getClass().getName() + STR_METHOD_NAME,
					"注文状態が数字以外の値です。");
			}
		}
		
		// ２）注文有効状態チェック
		// ２－１） WEB3StringTypeUtility.isEmpty(this.更新_注文有効状態) == false の場合、
		// 以下のチェックを行う。
		// ２－１－１）this.更新_注文有効状態チェックが数字以外の値であった場合、
		// 「注文有効状態が数字以外の値です。」の例外をスローする。
		if (!WEB3StringTypeUtility.isEmpty(this.updateOrderOpenStatus))
		{
			if (!WEB3StringTypeUtility.isDigit(this.updateOrderOpenStatus))
			{
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02509,
					this.getClass().getName() + STR_METHOD_NAME, 
					"注文有効状態が数字以外の値です。");
			}
		}

        //３）約定状態チェック
        //３－１）　@WEB3StringTypeUtility.isEmpty(this.更新_約定状態) == false　@の場合、
        //以下のチェックを行う。
        if (!WEB3StringTypeUtility.isEmpty(this.updateExecStatus))
        {
            //３－１－１）this.更新_約定状態が数字以外の値であった場合、
            //「約定状態が数字以外の値です。」の例外をスローする。
            if (!WEB3StringTypeUtility.isDigit(this.updateExecStatus))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02951,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "約定状態が数字以外の値です。");
            }
        }

        //４）発注日チェック
        //４－１）　@WEB3StringTypeUtility.isEmpty(this.更新_発注日) == false　@の場合、
        //以下のチェックを行う。
        if (!WEB3StringTypeUtility.isEmpty(this.updateOrderBizDate))
        {
            //４－１－１）this.更新_発注日の桁数 != 8 であった場合、
            //「発注日の桁数が不正です。」の例外をスローする。
            if (this.updateOrderBizDate.length() != 8)
            {
                log.debug("発注日の桁数が不正です。" + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02160,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注日エラー。");
            }
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
		return new WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse(this);
	}
}
@
