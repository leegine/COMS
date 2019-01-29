head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBatoClientService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 電子鳩システム接続サービスインターフェイス(WEB3GentradeBatoClientService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 仲川(ＳＲＡ) 新規作成
Revesion History : 2008/05/20 趙林鵬 (中訊)モデルNo.328,329
Revesion History : 2008/06/17 趙林鵬 (中訊)モデルNo.330
*/
package webbroker3.gentrade.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeBatoFunctionDivDef;
import webbroker3.gentrade.define.WEB3GentradeBatoProspectusServiceResultDef;
import webbroker3.gentrade.define.WEB3GentradeBatoServiceRegServiceResultDef;
import webbroker3.gentrade.define.WEB3GentradeBatoTranHistServiceResultDef;
import webbroker3.gentrade.message.WEB3GentradeMultiCheckResults;
import webbroker3.gentrade.message.WEB3GentradeMultiDocCheckResultUnit;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;

/**
 * 電子鳩システム接続サービスインターフェイス
 */
public interface WEB3GentradeBatoClientService extends WEB3BusinessService 
{
   
    /**
     * 電子鳩システム接続サービス処理を行う。<br />
     * 
     * @@param l_request - リクエストデータ
     * @@exception  BUSINESS_ERROR_00013:　@受付時間外
     * @@exception  SYSTEM_ERROR_80005:　@該当データなし
     * @@exception  SYSTEM_ERROR_80003:　@DBエラー
     * @@return WEB3GenResponse<br />
     * @@roseuid 421036A8039E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
   
    /**
     * （validate目論見書閲覧）<br />
     * <br />
     * 目論見書閲覧済かを判定する。<br />
     * <br />
     * @@param l_typeCode 種別コード(ＰＲ層より取得)<br />
     * @@param l_productCode 銘柄コード<br />
     * @@return webbroker3.gentrade.message.WEB3GentradeProspectusResult
     * @@see WEB3GentradeProspectusResult
     * @@see WEB3GentradeBatoProspectusServiceResultDef
     * @@exception  BUSINESS_ERROR_00013:　@受付時間外
     * @@exception  BUSINESS_ERROR_01959:　@電子鳩エラー　@ 
     * @@exception  SYSTEM_ERROR_80003:　@DBエラー
     * @@exception  SYSTEM_ERROR_80005:　@該当データなし
     * @@roseuid 421179480180
     */
    public WEB3GentradeProspectusResult validateProspectus(String l_typeCode, String l_productCode) throws WEB3BaseException;
   
    /**
     * （validate電子鳩実施）<br />
     * <br />
     * 電子鳩システムでパラメータの機@能が実施されているかを判定する。<br />
     * <br />
     * [戻り値]<br />
     * ○引数.機@能区分が”電子鳩承諾チェック”の場合<br />
     *   0： 未同意顧客<br />
     *   1： 同意顧客<br />
     * ※　@WEB3GentradeBatoServiceRegServiceResultDefにて定数定義<br />
     * <br />
     * ○引数.機@能区分が”取引報告書実施チェック”の場合<br />
     *   0： 未同意顧客<br />
     *   1： 同意顧客<br />
     *   2： 未実施会社<br />
     * ※　@WEB3GentradeBatoTranHistServiceResultDefにて定数定義<br />
     * 
     * @@param l_functionDiv  機@能区分(WEB3GentradeBatoFunctionDivDef)
     * @@return 電子鳩システムの戻り値(WEB3GentradeBatoTranHistServiceResultDef,WEB3GentradeBatoServiceRegServiceResultDef)<br />
     * @@see WEB3GentradeBatoFunctionDivDef
     * @@see WEB3GentradeBatoTranHistServiceResultDef
     * @@see WEB3GentradeBatoServiceRegServiceResultDef
     * @@exception  BUSINESS_ERROR_00013:　@受付時間外
     * @@exception  BUSINESS_ERROR_01959:　@電子鳩エラー　@ 
     * @@exception  SYSTEM_ERROR_80003:　@DBエラー
     * @@exception  SYSTEM_ERROR_80005:　@該当データなし
     * @@exception  SYSTEM_ERROR_80006:  引数エラー
     * @@roseuid 421179490047
     */
    public String validateBato(String l_functionDiv) throws WEB3BaseException;
    
    /**
     * (is電子鳩停止中)<BR>
     * <BR>
     * 電子鳩システムが停止中かどうかを判定する。<BR>
     * <BR>
     * true： 停止中（障害中）<BR>
     * false： 稼動中<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isBatoStopping() throws WEB3BaseException;

    /**
     * (get電子鳩接続情報)<BR>
     * 電子鳩の接続情報(URL)を取得し、返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getBatoConnectionInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (validate複数銘柄目論見書閲覧)<BR>
     * 複数銘柄の目論見書閲覧済かを判定する。<BR>
     * <BR>
     * @@param l_multiDocCheckResultUnit - (複数銘柄目論見書閲覧チェックリスト)<BR>
     * 複数銘柄目論見書閲覧チェックリスト<BR>
     * @@param l_blnIsCheckFlag - (代理入力不可時チェックフラグ)<BR>
     * 代理入力不可時に業務エラーにするかしないかのフラグ<BR>
     * <BR>
     * 代理入力不可時チェックフラグ<BR>
     * true：チェックする<BR>
     * false：チェックしない<BR>
     * @@return WEB3GentradeMultiCheckResults
     * @@throws WEB3BaseException
     */
    public WEB3GentradeMultiCheckResults validateMultiProspectus(
        WEB3GentradeMultiDocCheckResultUnit[] l_multiDocCheckResultUnit,
        boolean l_blnIsCheckFlag) throws WEB3BaseException;
}
@
