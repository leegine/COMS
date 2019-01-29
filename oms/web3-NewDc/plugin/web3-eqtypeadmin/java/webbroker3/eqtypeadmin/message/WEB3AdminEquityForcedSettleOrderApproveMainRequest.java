head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderApproveMainRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済仮注文承認／非承認メインリクエスト(WEB3AdminEquityForcedSettleOrderApproveMainRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 趙林鵬 (中訊) 新規作成 モデルNo.128
Revision History : 2007/05/16 趙林鵬 (中訊) モデルNo.152
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・強制決済仮注文承認／非承認メインリクエスト)<BR>
 * 管理者・強制決済仮注文承認／非承認メインリクエストクラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleOrderApproveMainRequest extends WEB3BackRequest
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveMainRequest.class);

    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_forced_settle_order_approve_main";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;

    /**
     * (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    public String institutionCode;

    /**
     * (スレッドNo)<BR>
     * スレッドNo<BR>
     */
    public Long threadNo;

    /**
     * (From口座ID)<BR>
     * From口座ID<BR>
     */
    public Long rangeFrom;

    /**
     * (To口座ID)<BR>
     * To口座ID<BR>
     */
    public Long rangeTo;

    /**
     * (承認区分)<BR>
     * 承認区分<BR>
     * <BR>
     * 0：　@承認<BR>
     * 1：　@非承認<BR>
     */
    public String approveType;

    /**
     * (注文ID一覧)<BR>
     * 注文ID一覧<BR>
     */
    public String[] orderIdList;

    /**
     * (管理者ID)<BR>
     * 管理者ID<BR>
     */
    public Long administratorId;

    /**
     * (管理者・強制決済仮注文承認／非承認メインリクエスト)<BR>
     * コンストラクタ。<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest
     * @@roseuid 460755CC03C2
     */
    public WEB3AdminEquityForcedSettleOrderApproveMainRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@証券会社コードチェック<BR>
     * 　@１－１）　@this.証券会社コード == nullの場合、<BR>
     * 　@　@「証券会社コードが未指定です。」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00827<BR>
     * <BR>
     * ２）　@スレッドNoチェック<BR>
     * 　@２－１）　@this.スレッドNo == nullの場合、<BR>
     * 　@　@「スレッド番号の指定なし。」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01974<BR>
     * <BR>
     * ３）　@From口座IDチェック<BR>
     * 　@３－１）　@this.From口座ID == nullの場合、<BR>
     * 　@　@「From口座IDが未入力」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02421<BR>
     * <BR>
     * ４）　@To口座IDチェック<BR>
     * 　@４－１）　@this.To口座ID == nullの場合、<BR>
     * 　@　@「To口座ID（至）が未入力」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02422<BR>
     * <BR>
     * ５）　@承認区分チェック<BR>
     * 　@５－１）　@this.承認区分 == nullの場合、<BR>
     * 　@　@「承認区分がnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02761<BR>
     * <BR>
     * ６）　@注文ID一覧チェック<BR>
     * 　@６－１）　@this.注文ID一覧 == nullの場合、<BR>
     * 　@　@「IDがnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00600<BR>
     * <BR>
     * ７）　@管理者IDチェック
     * 　@６－１）　@this管理者ID == nullの場合、
     * 　@　@「IDがnull」の例外をスローする。 
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02776<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46075A5D03A3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

       // 当リクエストデータの整合性チェックを行う。
       // （ただし、当クラス内で完結する簡易チェックのみとする。）
       // １）　@証券会社コードチェック
       // 　@１－１）　@this.証券会社コード == nullの場合、
       // 　@　@「証券会社コードが未指定です。」の例外をスローする。
       if (this.institutionCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券会社コードが未指定です。");
        }

       // ２）　@スレッドNoチェック
       // 　@２－１）　@this.スレッドNo == nullの場合、
       // 　@　@「スレッド番号の指定なし。」の例外をスローする。
       if (this.threadNo == null)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_01974,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "スレッド番号の指定なし。");
       }

       // ３）　@From口座IDチェック
       // 　@３－１）　@this.From口座ID == nullの場合、
       // 　@　@「From口座IDが未入力」の例外をスローする。
       if (this.rangeFrom == null)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02421,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "From口座IDが未入力です。");
       }

       // ４）　@To口座IDチェック
       // 　@４－１）　@this.To口座ID == nullの場合、
       // 　@　@「To口座ID（至）が未入力」の例外をスローする。
       if (this.rangeTo == null)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02422,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "To口座ID（至）が未入力です。");
       }

       // ５）　@承認区分チェック
       // 　@５－１）　@this.承認区分 == nullの場合、
       // 　@　@「承認区分がnull」の例外をスローする。
       if (this.approveType == null)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02761,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "承認区分がnull。");
       }

       // ６）　@注文ID一覧チェック
       // 　@６－１）　@this.注文ID一覧 == nullの場合、
       // 　@　@「IDがnull」の例外をスローする。
       if (this.orderIdList == null)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00600,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "注文IDが未指定です。");
       }

       //７）　@管理者IDチェック 
       //６－１）　@this管理者ID == nullの場合、 
       //「IDがnull」の例外をスローする。
       if (this.administratorId == null)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02776,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "IDがnull。");
       }

       log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3AdminEquityForcedSettleOrderApproveMainResponse(this);
    }
}
@
