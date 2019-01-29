head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済注文照会リクエスト(WEB3AdminForcedSettleReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 趙林鵬 (中訊) 新規作成  仕様変更モデルNo.128
Revesion History : 2008/01/17 孟亞南 (中訊) 仕様変更モデルNo.181 183 185 188
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ApproveStatusType;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ForcedSettleReasonType;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityApproveTypeDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityForcedSettleReasonDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・強制決済注文照会リクエスト)<BR>
 * 管理者・強制決済注文照会リクエストクラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminForcedSettleReferenceRequest extends WEB3GenRequest
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminForcedSettleReferenceRequest.class);

    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_reference";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;

    /**
     * (部店コード一覧)<BR>
     * 部店コードの配列<BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している<BR>
     * 　@取扱可能部店コード一覧がセットされる。<BR>
     */
    public String[] branchCodeList;

    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    public Date orderBizDate;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;

    /**
     * (承認状態)<BR>
     * 承認状態<BR>
     * <BR>
     * 0：　@未承認<BR>
     * 1：　@承認済<BR>
     * 2：　@非承認<BR>
     * 9：　@エラー<BR>
     */
    public String approveState;

    /**
     * (承認者コード)<BR>
     * 承認者コード<BR>
     */
    public String checker;

    /**
     * (作成日時From)<BR>
     * 作成日時From<BR>
     */
    public String createDateFrom;

    /**
     * (作成日時To)<BR>
     * 作成日時To<BR>
     */
    public String createDateTo;

    /**
     * (承認日時From)<BR>
     * 承認日時From<BR>
     */
    public String approveDateFrom;

    /**
     * (承認日時To)<BR>
     * 承認日時To<BR>
     */
    public String approveDateTo;

    /**
     * (強制決済理由)<BR>
     * 強制決済理由<BR>
     * <BR>
     * 0：　@決済期日到来<BR>
     * 1：　@保証金維持率割（オンライン開始前・軽度）<BR>
     * 2：　@保証金維持率割（オンライン開始前・重度）<BR>
     * 3：　@保証金維持率割（場間）<BR>
     * 4：  保証金維持率割（オンライン開始前・法@定）<BR>
     * 9：　@手動強制決済<BR>
     * 90：　@追証(第一)期日超過<BR>
     * 91：　@追証(第二)期日超過<BR>
     * <BR>
     * ※追証(第一)期日超過<BR>
     * 　@・・・ 保証金維持率割（オンライン開始前・軽度） or<BR>
     * 　@保証金維持率割（オンライン開始前・重度）のこと。<BR>
     * 追証(第二)期日超過<BR>
     * 　@・・・ 保証金維持率割（場間） or<BR>
     * 　@保証金維持率割（オンライン開始前・法@定）のこと。<BR>
     */
    public String forcedSettleReason;

    /**
     * (決済期日)<BR>
     * 決済期日<BR>
     */
    public Date closeDate;

    /**
     * (注文エラー理由コード)<BR>
     * 注文エラー理由コード<BR>
     * <BR>
     * 0005：　@建株残高不足エラー<BR>
     * 0006：　@売買停止銘柄エラー<BR>
     * 0016：　@決済期日到来済エラー<BR>
     * 0017：　@現引・現渡注文登録済エラー<BR>
     * 9001：　@その他エラー<BR>
     */
    public String errorReason;

    /**
     * (承認区分)<BR>
     * 承認区分<BR>
     * <BR>
     * 0：　@承認<BR>
     * 1：　@非承認<BR>
     * <BR>
     * ※管理者・強制決済仮注文承認／非承認サービスから<BR>
     * 　@コールされた場合のみセットされる。<BR>
     */
    public String approveType;

    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0：　@一般<BR>
     * 1：　@特定<BR>
     * <BR>
     * ※管理者・強制決済仮注文承認／非承認サービスから<BR>
     * 　@コールされた場合のみセットされる。<BR>
     */
    public String taxType;

    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     * <BR>
     * ※管理者・強制決済仮注文承認／非承認サービスから<BR>
     * 　@コールされた場合のみセットされる。<BR>
     */
    public String marketCode;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     * <BR>
     * ※ダウンロード機@能からコールされた場合は、<BR>
　@   * 　@ダウンロードページ番号となる。<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     * <BR>
     * ※ダウンロード機@能からコールされた場合は、<BR>
     * 　@ダウンロード件数となる。<BR>
     */
    public String pageSize;

    /**
     * (ソートキー)<BR>
     * ソートキー<BR>
     */
    public WEB3AdminForcedSettleSortKeyUnit[] sortKeys;

    /**
     * @@roseuid 462CA4250276
     */
    public WEB3AdminForcedSettleReferenceRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@部店コードチェック<BR>
     * 　@１－１）　@this.部店コード一覧 == nullの場合、<BR>
     * 　@　@「部店コードがnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01429       <BR>
     * <BR>
     * 　@１－２）　@this.部店コード一覧の要素数分以下の処理を行う。<BR>
     * 　@　@１－２－１）　@this.部店コードが以下の条件に該当する場合、<BR>
     * 　@　@　@「部店コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@・部店コード != 数字<BR>
     * 　@　@　@　@・部店コード.length != 3<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00779       <BR>
     * <BR>
     * ２）　@顧客コードチェック<BR>
     * 　@this.顧客コード != nullの場合、以下のチェックを行う。<BR>
     * 　@２－１）　@this.顧客コードが以下の条件に該当する場合、<BR>
     * 　@　@「顧客コードエラー」の例外をスローする。<BR>
     * 　@　@　@・顧客コード != 数字<BR>
     * 　@　@　@・顧客コード.length != 6<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00780       <BR>
     * <BR>
     * ３）　@銘柄コードチェック<BR>
     * 　@this.銘柄コード != nullの場合、以下のチェックを行う。<BR>
     * 　@３－１）　@this.銘柄コードが以下の条件に該当する場合、<BR>
     * 　@　@「銘柄コードエラー」の例外をスローする。<BR>
     * 　@　@　@・銘柄コード != 数字<BR>
     * 　@　@　@・銘柄コード.length != 5<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01067       <BR>
     * <BR>
     * ４）　@承認状態チェック<BR>
     * 　@this.承認状態 != nullの場合、以下のチェックを行う。<BR>
     * 　@４－１）　@this.承認状態に下記の項目以外が設定されていたら、<BR>
     * 　@　@「承認状態が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・"未承認"<BR>
     * 　@　@　@・"承認済"<BR>
     * 　@　@　@・"非承認"<BR>
     * 　@　@　@・"エラー"<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02753<BR>
     * <BR>
     * ５）　@作成日時From/To整合性チェック<BR>
     * 　@５－１）　@this.作成日時From != nullの場合、<BR>
     * 　@　@this.作成日時Fromを日付型に変換できない場合、<BR>
     * 　@　@　@　@　@「入力日付エラー（作成日時From）」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02754<BR>
     * <BR>
     * 　@５－２）　@this.作成日時To != nullの場合、<BR>
     * 　@　@this.作成日時Toを日付型に変換できない場合、<BR>
     * 　@　@　@　@　@「入力日付エラー（作成日時To）」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02755<BR>
     * <BR>
     * 　@５－３）　@this.作成日時From != null かつ 作成日時To != nullの場合、<BR>
     * 　@　@this.作成日時From > this.作成日時Toであれば、<BR>
     * 　@　@「作成日時From/To整合性エラー」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02715<BR>
     * <BR>
     * ６）　@承認日時From/To整合性チェック<BR>
     * 　@６－１）　@this.承認日時From != nullの場合、<BR>
     * 　@　@this.承認日時Fromを日付型に変換できない場合、<BR>
     * 　@　@　@　@　@「入力日付エラー（承認日時From）」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02756<BR>
     * <BR>
     * 　@６－２）　@this.承認日時To != nullの場合、<BR>
     * 　@　@this.承認日時Toを日付型に変換できない場合、<BR>
     * 　@　@　@　@　@「入力日付エラー（承認日時To）」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02757<BR>
     * <BR>
     * 　@６－３）　@this.承認日時From != null かつ 承認日時To != nullの場合、<BR>
     * 　@　@this.承認日時From > this.承認日時Toであれば、<BR>
     * 　@　@「承認日時From/To整合性エラー」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02715<BR>
     * <BR>
     * ７）　@強制決済理由チェック<BR>
     * 　@this.強制決済理由 != nullの場合、以下のチェックを行う。<BR>
     * 　@７－１）　@this.強制決済理由に下記の項目以外が設定されていたら、<BR>
     * 　@　@「強制決済理由が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・"決済期日到来"<BR>
     * 　@　@　@・"保証金維持率割（軽度）"<BR>
     * 　@　@　@・"保証金維持率割（重度）"<BR>
     * 　@　@　@・"保証金維持率割（場間）"<BR>
     * 　@　@　@・"保証金維持率割（法@定）"<BR>
     * 　@　@　@・"手動強制決済"<BR>
     * 　@　@　@・"追証(第一)期日超過"<BR>
     * 　@　@　@・"追証(第二)期日超過"<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02758<BR>
     * <BR>
     * ８）　@注文エラー理由コードチェック<BR>
     * 　@this.注文エラー理由コード != nullの場合、以下のチェックを行う。<BR>
     * 　@８－１）　@this.注文エラー理由コードに下記の項目以外が設定されていたら、<BR>
     * 　@　@「注文エラー理由コードが未定義の値」の例外をスローする。<BR>
     * 　@　@　@・"建株残高不足エラー"<BR>
     * 　@　@　@・"売買停止銘柄エラー"<BR>
     * 　@　@　@・"決済期日到来済エラー"<BR>
     * 　@　@　@・"現引・現渡注文登録済エラー"<BR>
     * 　@　@　@・"その他エラー"<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02759<BR>
     * <BR>
     * ９）　@承認区分チェック<BR>
     * 　@this.承認区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@９－１）　@this.承認区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@「承認区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・"承認"<BR>
     * 　@　@　@・"非承認"<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02760<BR>
     * <BR>
     * １０）　@口座区分チェック<BR>
     * 　@this.口座区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@１０－１）　@this.口座区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@「口座区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・"一般"<BR>
     * 　@　@　@・"特定"<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01303<BR>
     * <BR>
     * １１）　@市場コードチェック<BR>
     * 　@this.市場コード != nullの場合、以下のチェックを行う。<BR>
     * 　@１１－１）　@this.市場コードに下記の項目以外が設定されていたら、<BR>
     * 　@　@「市場コードが未定義の値」の例外をスローする。<BR>
     * 　@　@　@・"東京"<BR>
     * 　@　@　@・"大阪"<BR>
     * 　@　@　@・"名古屋"<BR>
     * 　@　@　@・"福岡"<BR>
     * 　@　@　@・"札幌"<BR>
     * 　@　@　@・"NNM"<BR>
     * 　@　@　@・"JASDAQ"<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00608<BR>
     * <BR>
     * １２）　@ソートキーチェック<BR>
     * 　@１２－１）　@this.ソートキーが未入力の場合、<BR>
     * 　@　@「ソートキーがnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@１２－２）　@this.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@　@１２－２－１）　@ソートキー.validate()をコールする。<BR>
     * <BR>
     * １３）　@要求ページ番号チェック<BR>
     * 　@１３－１）　@this.要求ページ番号 == nullであった場合、<BR>
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@１３－２）　@this.要求ページ番号が数字以外の値であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@１３－３）　@this.要求ページ番号 <= 0であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00616<BR>
     * <BR>
     * １４）　@ページ内表示行数チェック<BR>
     * 　@１４－１）　@this.ページ内表示行数 == nullであった場合、<BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00091<BR>
     * <BR>
     * 　@１４－２）　@this.ページ内表示行数が数字以外の値であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00092<BR>
     * <BR>
     * 　@１４－３）　@this.ページ内表示行数 <= 0であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 460200BE005C
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 当リクエストデータの整合性チェックを行う。
        // （ただし、当クラス内で完結する簡易チェックのみとする。）
        // １）　@部店コードチェック
        // 　@１－１）　@this.部店コード一覧 == nullの場合、
        // 　@　@「部店コードがnull」の例外をスローする。
        if (this.branchCodeList == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01429,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード一覧が未指定です。");
        }

        //   １－２）　@this.部店コード一覧の要素数分以下の処理を行う。
        // 　@　@１－２－１）　@this.部店コードが以下の条件に該当する場合、
        // 　@　@　@「部店コードエラー」の例外をスローする。
        // 　@　@　@　@・部店コード != 数字
        // 　@　@　@　@・部店コード.length != 3
        int l_intBranchCodeLength = 0;
        l_intBranchCodeLength = this.branchCodeList.length;
        for (int i = 0;  i < l_intBranchCodeLength; i++)
        {
            final int l_intThree = 3;
            if ((!WEB3StringTypeUtility.isDigit(branchCodeList[i]))
                || (WEB3StringTypeUtility.getByteLength(this.branchCodeList[i]) != l_intThree))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードの入力が不正です。");
            }
        }

        // ２）　@顧客コードチェック
        // 　@this.顧客コード != nullの場合、以下のチェックを行う。
        // 　@２－１）　@this.顧客コードが以下の条件に該当する場合、
        // 　@　@「顧客コードエラー」の例外をスローする。
        // 　@　@　@・顧客コード != 数字
        // 　@　@　@・顧客コード.length != 6
        if (this.accountCode != null)
        {
            final int l_intSix = 6;
            if ((!WEB3StringTypeUtility.isDigit(this.accountCode))
                || (WEB3StringTypeUtility.getByteLength(this.accountCode) != l_intSix))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードの入力が不正です。");
            }
        }

        // ３）　@銘柄コードチェック
        // 　@this.銘柄コード != nullの場合、以下のチェックを行う。
        // 　@３－１）　@this.銘柄コードが以下の条件に該当する場合、
        // 　@　@「銘柄コードエラー」の例外をスローする。
        // 　@　@　@・銘柄コード != 数字
        // 　@　@　@・銘柄コード.length != 5
        if (this.productCode != null)
        {
            final int l_intFive = 5;
            if ((!WEB3StringTypeUtility.isDigit(this.productCode))
                || (WEB3StringTypeUtility.getByteLength(this.productCode) != l_intFive))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄コードの入力が不正です。");
            }
        }

        // ４）　@承認状態チェック
        // 　@this.承認状態 != nullの場合、以下のチェックを行う。
        // 　@４－１）　@this.承認状態に下記の項目以外が設定されていたら、
        // 　@　@「承認状態が未定義の値」の例外をスローする。
        // 　@　@　@・"未承認"
        // 　@　@　@・"承認済"
        // 　@　@　@・"非承認"
        // 　@　@　@・"エラー"
        if (this.approveState != null)
        {
            if ((!WEB3ApproveStatusType.UNAPPROVED.equals(this.approveState))
                && (!WEB3ApproveStatusType.APPROVED.equals(this.approveState))
                && (!WEB3ApproveStatusType.NON_APPROVED.equals(this.approveState))
                && (!WEB3ApproveStatusType.ERROR.equals(this.approveState)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02753,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "承認状態が未定義の値。");
            }
        }

        //５）　@作成日時From/To整合性チェック
        // 　@５－１）　@this.作成日時From != nullの場合、
        // 　@　@this.作成日時Fromを日付型に変換できない場合、
        // 　@　@　@　@　@「入力日付エラー（作成日時From）」の例外をスローする。
        if (this.createDateFrom != null)
        {
            Date l_datCreateDateFrom = WEB3DateUtility.getDate(this.createDateFrom, "yyyyMMddHHmm");

            if (l_datCreateDateFrom == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02754,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力日付エラー（作成日時From）。");
            }
        }

        // 　@５－２）　@this.作成日時To != nullの場合、
        // 　@　@this.作成日時Toを日付型に変換できない場合、
        // 　@　@　@　@　@「入力日付エラー（作成日時To）」の例外をスローする。
        if (this.createDateTo != null)
        {
            Date l_datCreateDateTo = WEB3DateUtility.getDate(this.createDateTo, "yyyyMMddHHmm");

            if (l_datCreateDateTo == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02755,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力日付エラー（作成日時To）。");
            }
        }

        // 　@５－３）　@this.作成日時From != null かつ 作成日時To != nullの場合、
        // 　@　@this.作成日時From > this.作成日時Toであれば、
        // 　@　@「作成日時From/To整合性エラー」の例外をスローする。
        if ((this.createDateFrom != null) && (this.createDateTo != null))
        {
            Date l_datCreateDateFrom = WEB3DateUtility.getDate(this.createDateFrom, "yyyyMMddHHmm");
            Date l_datCreateDateTo = WEB3DateUtility.getDate(this.createDateTo, "yyyyMMddHHmm");

            int l_intResultCompare = WEB3DateUtility.compare(l_datCreateDateFrom, l_datCreateDateTo);
            if (l_intResultCompare > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "対象期間エラー。");
            }
        }

        // ６）　@承認日時From/To整合性チェック
        // 　@６－１）　@this.承認日時From != nullの場合、
        // 　@　@this.承認日時Fromを日付型に変換できない場合、
        // 　@　@　@　@　@「入力日付エラー（承認日時From）」の例外をスローする。
        if (this.approveDateFrom != null)
        {
            Date l_datApproveDateFrom = WEB3DateUtility.getDate(this.approveDateFrom, "yyyyMMddHHmm");

            if (l_datApproveDateFrom == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02756,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力日付エラー（承認日時From）。");
            }
        }

        // 　@６－２）　@this.承認日時To != nullの場合、
        // 　@　@this.承認日時Toを日付型に変換できない場合、
        // 　@　@　@　@　@「入力日付エラー（承認日時To）」の例外をスローする。
        if (this.approveDateTo != null)
        {
            Date l_datApproveDateTo = WEB3DateUtility.getDate(this.approveDateTo, "yyyyMMddHHmm");

            if (l_datApproveDateTo == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02757,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力日付エラー（承認日時To）。");
            }
         }

        // 　@６－３）　@this.承認日時From != null かつ 承認日時To != nullの場合、
        // 　@　@this.承認日時From > this.承認日時Toであれば、
        // 　@　@「承認日時From/To整合性エラー」の例外をスローする。
        if ((this.approveDateFrom != null) && (this.approveDateTo != null))
        {
            Date l_datApproveDateFrom = WEB3DateUtility.getDate(this.approveDateFrom, "yyyyMMddHHmm");
            Date l_datApproveDateTo = WEB3DateUtility.getDate(this.approveDateTo, "yyyyMMddHHmm");

            int l_intResultCompare = WEB3DateUtility.compare(l_datApproveDateFrom, l_datApproveDateTo);
            if (l_intResultCompare > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "対象期間エラー。");
            }
        }

        // ７）　@強制決済理由チェック
        // 　@this.強制決済理由 != nullの場合、以下のチェックを行う。
        // 　@７－１）　@this.強制決済理由に下記の項目以外が設定されていたら、
        // 　@　@「強制決済理由が未定義の値」の例外をスローする。
        // 　@　@　@・"決済期日到来"
        // 　@　@　@・"保証金維持率割（軽度）"
        // 　@　@　@・"保証金維持率割（重度）"
        // 　@　@　@・"保証金維持率割（場間）"
        // 　@　@　@・"保証金維持率割（法@定）"
        // 　@　@　@・"手動強制決済"
        //　@　@　@ ・"追証(第一)期日超過"
        //　@　@　@ ・"追証(第二)期日超過"
        if (this.forcedSettleReason != null)
        {
            if ((!WEB3ForcedSettleReasonType.FIXED_DATE_COMING.equals(this.forcedSettleReason))
                && (!WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS.equals(
                    this.forcedSettleReason))
                && (!WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS.equals(
                    this.forcedSettleReason))
                && (!WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET.equals(
                    this.forcedSettleReason))
                && (!WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL.equals(
                    this.forcedSettleReason))
                && (!WEB3ForcedSettleReasonType.MANUAL_FORCED_SETTLE.equals(this.forcedSettleReason))
                && (!WEB3AdminEquityForcedSettleReasonDef.ADDDEPOSIT_FIRST_DATE_EXCESS.equals(
                    this.forcedSettleReason))
                && (!WEB3AdminEquityForcedSettleReasonDef.ADDDEPOSIT_SECOND_DATE_EXCESS.equals(
                    this.forcedSettleReason)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02758,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "強制決済理由が未定義の値。");
            }
        }

        // ８）　@注文エラー理由コードチェック
        // 　@this.注文エラー理由コード != nullの場合、以下のチェックを行う。
        // 　@８－１）　@this.注文エラー理由コードに下記の項目以外が設定されていたら、
        // 　@　@「注文エラー理由コードが未定義の値」の例外をスローする。
        // 　@　@　@・"建株残高不足エラー"
        // 　@　@　@・"売買停止銘柄エラー"
        // 　@　@　@・"決済期日到来済エラー"
        // 　@　@　@・"現引・現渡注文登録済エラー"
        // 　@　@　@・"その他エラー"
        if (this.errorReason != null)
        {
            if ((!WEB3ErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR.equals(this.errorReason))
                && (!WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR.equals(this.errorReason))
                && (!WEB3ErrorReasonCodeDef.SETTLEDAY_CAME_ERROR.equals(this.errorReason))
                && (!WEB3ErrorReasonCodeDef.SWAP_MARGIN_REGISTED_ERROR.equals(this.errorReason))
                && (!WEB3ErrorReasonCodeDef.OTHRE_ERROR.equals(this.errorReason)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02759,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文エラー理由コードが未定義の値。");
            }
        }

        // ９）　@承認区分チェック
        // 　@this.承認区分 != nullの場合、以下のチェックを行う。
        // 　@９－１）　@this.承認区分に下記の項目以外が設定されていたら、
        // 　@　@「承認区分が未定義の値」の例外をスローする。
        // 　@　@　@・"承認"
        // 　@　@　@・"非承認"
        if (this.approveType != null)
        {
            if ((!WEB3AdminEquityApproveTypeDef.APPROVE.equals(this.approveType))
                && (!WEB3AdminEquityApproveTypeDef.DISAPPROVE.equals(this.approveType)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02760,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "承認区分が未定義の値。");
            }
        }

        // １０）　@口座区分チェック
        // 　@this.口座区分 != nullの場合、以下のチェックを行う。>
        // 　@１０－１）　@this.口座区分に下記の項目以外が設定されていたら、
        //　@　@「口座区分が未定義の値」の例外をスローする。
        // 　@　@　@・"一般"
        // 　@　@　@・"特定"
        if (this.taxType != null)
        {
            if ((!WEB3TaxTypeDef.NORMAL.equals(this.taxType))
                && (!WEB3TaxTypeDef.SPECIAL.equals(this.taxType)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01303,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "口座区分の値が存在しないコード値です。");
            }
        }

        // １１）　@市場コードチェック
        // 　@this.市場コード != nullの場合、以下のチェックを行う。
        // 　@１１－１）　@this.市場コードに下記の項目以外が設定されていたら、
        // 　@　@「市場コードが未定義の値」の例外をスローする。
        // 　@　@　@・"東京"
        // 　@　@　@・"大阪"
        // 　@　@　@・"名古屋"
        // 　@　@　@・"福岡"
        // 　@　@　@・"札幌"
        // 　@　@　@・"NNM"
        // 　@　@　@・"JASDAQ"
        if (this.marketCode != null)
        {
            if ((!WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode))
                && (!WEB3MarketCodeDef.JASDAQ.equals(this.marketCode))
                && (!WEB3MarketCodeDef.NAGOYA.equals(this.marketCode))
                && (!WEB3MarketCodeDef.NNM.equals(this.marketCode))
                && (!WEB3MarketCodeDef.OSAKA.equals(this.marketCode))
                && (!WEB3MarketCodeDef.SAPPORO.equals(this.marketCode))
                && (!WEB3MarketCodeDef.TOKYO.equals(this.marketCode)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "市場コードが存在しないコード値です。");
            }
        }

        // １２）　@ソートキーチェック
        // 　@１２－１）　@this.ソートキーが未入力の場合、
        // 　@　@「ソートキーがnull」の例外をスローする。
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }

        // 　@１２－２）　@this.ソートキーの要素数分以下の処理を繰り返す。
        // 　@　@１２－２－１）　@ソートキー.validate()をコールする。
        if (this.sortKeys != null)
        {
            int l_intSortKeysLength = this.sortKeys.length;
            for (int i = 0; i < l_intSortKeysLength; i++)
            {
                WEB3AdminForcedSettleSortKeyUnit l_adminForcedSettleSortKeyUnit = sortKeys[i];
                l_adminForcedSettleSortKeyUnit.validate();
            }
        }

        // １３）　@要求ページ番号チェック
        // 　@１３－１）　@this.要求ページ番号 == nullであった場合、
        // 　@　@　@　@「要求ページ番号がnull」の例外をスローする。
        if (this.pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        // 　@１３－２）　@this.要求ページ番号が数字以外の値であった場合、
        // 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }

        // 　@１３－３）　@this.要求ページ番号 <= 0であった場合、
        // 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。
        int l_intPageIndex = Integer.parseInt(this.pageIndex);
        if (l_intPageIndex <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        // １４）　@ページ内表示行数チェック
        // 　@１４－１）　@this.ページ内表示行数 == nullであった場合、
        // 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。");
        }

        // 　@１４－２）　@this.ページ内表示行数が数字以外の値であった場合、
        // 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }

        // 　@１４－３）　@this.ページ内表示行数 <= 0であった場合、
        // 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。
        int l_intPageSize = Integer.parseInt(pageSize);
        if (l_intPageSize <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminForcedSettleReferenceResponse(this);
    }
}
@
