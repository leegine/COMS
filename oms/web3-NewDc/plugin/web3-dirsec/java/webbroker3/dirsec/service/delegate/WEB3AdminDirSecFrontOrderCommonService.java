head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecFrontOrderCommonService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (フロント発注管理共通サービス) (WEB3AdminDirSecFrontOrderCommonService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.118
Revesion History : 2007/02/17  李木子 (中訊) 実装の問題No.003
Revesion History : 2007/02/27  謝旋 (中訊) モデルNo.023,042,043,047-049
Revesion History : 2007/02/28  孟亜南 (中訊) 仕様変更モデルNo.059
Revesion History : 2007/03/02  孟亜南 (中訊) 仕様変更モデルNo.095
*/
package webbroker3.dirsec.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.dirsec.message.WEB3AdminFrontProcessNumberInfoUnit;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminOrderRouteSwitchingInfo;
import webbroker3.gentrade.data.OrderSwitchingRow;


/**
 * (管理者フロント発注管理共通サービス)<BR>
 * <BR>
 * 管理者フロント発注管理共通サービスインタフェース<BR>
 * <BR>
 * WEB3AdminDirSecFrontOrderCommonService interface<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public interface WEB3AdminDirSecFrontOrderCommonService extends Service {
    /**
     * 発注先切替テーブルより、条件に該当するレコードを取得し、<BR>
     * 返却する。<BR>
     * @@param 証券会社コード - 証券会社コード<BR>
     * @@return 発注先切替Row[]<BR>
     * @@roseuid 42D24FD300B0
     */
    public OrderSwitchingRow[] getOrderRouteSwitchingRows(String l_strInstitutionCode) throws WEB3BusinessLayerException, WEB3SystemLayerException;
  
    /**
     * 障害仮想サーバ切替管理テーブルに"SONAR全障害"の<BR>
     * レコードが存在するかどうかチェックする。<BR>
     * @@param 証券会社コード - 証券会社コード<BR>
     * @@param フロント発注取引所区分コード - フロント発注取引所区分コード<BR>
     * @@param フロント発注システム区分 - フロント発注システム区分<BR>
     * @@param 銘柄タイプ - 銘柄タイプ<BR>
     * @@roseuid 42D24C6D02E2
     */
    public void validateSonarCheck(String l_strInstitutionCode, String l_strFrontExCode, 
                   String l_strFrontSystemCode, String l_strProductType) throws WEB3BusinessLayerException, WEB3SystemLayerException;

    /**
     * 発注経路の切替処理や、再起動処理を実行する。<BR>
     * @@param リクエストオブジェクト<BR>
     * @@roseuid 42F2B0410278
     */
    public void executeSwitchTransactionIssue(WEB3AdminFrontRouteChangeCompleteRequest l_request) throws WEB3SystemLayerException;

    /**
     * 引数の発注先切替Rowsより発注先情報の一覧を作成する。<BR>
     * @@param 発注先切替Rows - 発注先切替Rows型配列。<BR>
     * @@return 発注先情報[]<BR>
     * @@roseuid 42F7151B02D5
     */
    public WEB3AdminOrderRouteSwitchingInfo[] createSwitchRouteInfoList(OrderSwitchingRow[] l_switchRows);

    /**
     * 発注先切替テーブルからデータを検索し、結果を返却する。<BR>
     * @@param 証券会社コード - 証券会社コード。<BR>
     * @@return List<BR>
     * @@roseuid 42F716D00134
     */
    public List getFrontSwitchRouteTarget(String l_strInstitutionCode) throws WEB3BaseException;

    /**
     * 仮想サーバ情報テーブルから、仮想サーバ件数を取得し、処理件数情報オブジェクトに格納する。<BR>
     * @@param 証券会社コード - 証券会社コード<BR>
     * @@param 市場コード - 市場コード<BR>
     * @@param フロント発注システム区分 - フロント発注システム区分<BR>
     * @@param 銘柄タイプ - 銘柄タイプ<BR>
     * @@param 処理件数情報クラス - 処理件数情報クラスオブジェクト<BR>
     * @@roseuid 42F7174D0150
     */
    public void getVitualServerCount(String l_strInstitutionCode, String l_strMarketCode, String l_strFrontSystemCode,String l_ProductType, WEB3AdminFrontProcessNumberInfoUnit l_processInfo) throws WEB3SystemLayerException;

    /**
     * 障害仮想サーバ管理テーブルから切替指示応答区分が応答系のレコードを取得し、結果を<BR>
     * 返却する。<BR>
     * <BR>
     * @@param 証券会社コード - 証券会社コード。<BR>
     * @@param 市場コード - 市場コード。<BR>
     * @@param フロント発注システム区分 - フロント発注システム区分。<BR>
     * @@param 銘柄タイプ - 銘柄タイプ。<BR>
     * @@return List<BR>
     * @@roseuid 42E4A1EA0315
     */
    public List getSwitchRouteResRcord(String l_strInstitutionCode, String l_strMarketCode, String l_strFrontSystemCode ,String l_strProductType) throws WEB3SystemLayerException; 

    /**
     * 障害仮想サーバ管理テーブルから切替指示応答区分が要求系のレコードを取得し、結果を<BR>
     * 返却する。<BR>
     * <BR>
     * @@param 証券会社コード - 証券会社コード。<BR>
     * @@param 市場コード - 市場コード。<BR>
     * @@param フロント発注システム区分 - フロント発注システム区分。<BR>
     * @@param 銘柄タイプ - 銘柄タイプ。<BR>
     * @@return List<BR>
     * @@roseuid 42FC54A400A2
     */
    public List getSwitchRouteReqRcord(String l_strInstitutionCode, String l_strMarketCode, String l_strFrontSystemCode, String l_strProductType) throws WEB3SystemLayerException; 

    /**
     * 発注先切替テーブルから現発注経路を検索し、結果を返却する。<BR>
     * @@param 証券会社コード - 証券会社コード<BR>
     * @@param 市場コード - 市場コード<BR>
     * @@param フロント発注システム区分 - フロント発注システム区分<BR>
     * @@param 銘柄タイプ - 銘柄タイプ<BR>
     * @@param 処理件数情報クラス - 処理件数情報クラスオブジェクト<BR>
     * @@return String<BR>
     * @@roseuid 42F71F6502D0
     */
    public String getNowOrderRoute(String l_strInstitutionCode, String l_strMarketCode, String l_strFrontSystemCode,String l_ProductType, WEB3AdminFrontProcessNumberInfoUnit l_processInfo) throws WEB3SystemLayerException;

    /**
     * 統合キューテーブルから、"市場受付確認前"、"市場受付確認中"、"市場受付確認済"の<BR>
     * 注文件数を取得し、処理件数オブジェクトに格納する。<BR>
     * @@param 証券会社コード - 証券会社コード<BR>
     * @@param 市場コード - 市場コード<BR>
     * @@param フロント発注システム区分 - フロント発注システム区分<BR>
     * @@param 処理件数オブジェクト - 処理件数オブジェクト<BR>
     * @@roseuid 42F7213602B5
     */
    public void getGrayOrder(String l_strInstitutionCode, String l_strMarketCode, String l_strFrontSystemCode, WEB3AdminFrontProcessNumberInfoUnit l_processInfo) throws WEB3SystemLayerException;

    /**
     * データコードを返却する。<BR>
     * @@param 切替起動区分 - 切替起動区分<BR>
     * @@param 切替処理方式区分 - 切替処理方式区分<BR>
     * @@return String<BR>
     * @@roseuid 42F7255C0113
     */
    public String getDataCode(String l_strSwitchBootDiv,String l_changeStartDiv);

    /**
     * 発注先切替テーブルのDEOSレコードの有無をチェックする。<BR>
     * @@param 証券会社コード。<BR>
     * @@param 市場コード。<BR>
     * @@param フロント発注システム区分。<BR>
     * @@param 銘柄タイプ。<BR>
     * @@return java.lang.boolean<BR>
     * @@roseuid 42E46BB2019E
     */
    public boolean isFrontRoute(String l_strInstitutionCode, String l_strMarketCode, String l_frontSysDiv, 
        String l_strProductType) throws WEB3SystemLayerException;

    /**
     * フロント発注市場コードから、フロント発注取引所区分コードを取得する。<BR>
     * @@param フロント発注市場コード - フロント発注市場コード。<BR>
     * @@return String<BR>
     * @@roseuid 42F710A40139
     */
   public String getFrontOrderExchangeCode(String l_strConvertMarketCode);

   /**
    * フロント発注市場コードから、フロント発注システム区分を取得する。<BR>
    * @@param フロント発注市場コード - フロント発注市場コード。<BR>
    * @@return String<BR>
    * @@roseuid 42F711C40131
    */
  public String getFrontSystemDiv(String l_strConvertMarketCode);

  /**
   * 引数の市場コード、フロント発注システム区分コードから、画面表示用の<BR>
   * 市場コードに変換し、返却する。<BR>
   * @@param 市場コード - 市場コード。<BR>
   * @@param フロント発注システム区分 - フロント発注システム区分。<BR>
   * @@return String<BR>
   * @@roseuid 42F71618033C
   */
 public String getFrontOrderMarketCode(String l_strMarketCode, String l_strFrontSystemCode);

  /**
   * 先物OP注文取引キューテーブルから、"市場受付確認前"、"市場受付確認中"、<BR>
   * "市場受付確認済"の注文件数を取得し、処理件数オブジェクトに格納する。<BR>
   * @@param l_institutionCode - 証券会社コード<BR>
   * @@param l_marketCode - 市場コード<BR>
   * @@param l_frontSystemCode - フロント発注システム区分<BR>
   * @@param l_processInfoUnit - 処理件数情報<BR>
   * @@throws WEB3SystemLayerException
   */
 public void getIfoGrayOrder(String l_institutionCode, String l_marketCode, String l_frontSystemCode, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException;
 
 /**
  * ユーザデータを返却する。    <BR>
  * @@param 変換市場コード - 画面表示用変換市場コード。<BR>
  * @@param 銘柄タイプ。<BR>
  * @@param 切替起動区分 - 切替起動区分。<BR>
  * @@return String<BR>
  * @@roseuid 42F1E26B0138
  */
 public String getUserData(String l_strConvertMarketCode, String l_strProductType, String l_strChangeStartDiv);
 
}
@
