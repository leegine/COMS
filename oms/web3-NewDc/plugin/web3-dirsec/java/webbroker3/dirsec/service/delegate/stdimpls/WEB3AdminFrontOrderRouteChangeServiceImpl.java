head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontOrderRouteChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者発注経路切替サービス実装クラス(WEB3AdminFrontOrderRouteChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.116
Revesion History : 2007/02/17  李木子 (中訊) 実装の問題No.002-004
Revesion History : 2007/02/28  謝旋 (中訊) モデルNo.029,030,050
Revesion History : 2007/02/27  李木子 (中訊) 仕様変更モデルNo.050-058
Revesion History : 2007/02/27  孟亜南 (中訊) 仕様変更モデルNo.094
Revesion History : 2008/12/05  李キョウ (中訊) 仕様変更モデルNo.148,150
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3ChangeReqResDivDef;
import webbroker3.common.define.WEB3FrontOrderExchangeCodeDef;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3NoticeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.define.WEB3AdminFrontServiceStartDivDef;
import webbroker3.dirsec.define.WEB3AdminFrontSwitchStartDivDef;
import webbroker3.dirsec.define.WEB3AdminFrontSwtichStatusDef;
import webbroker3.dirsec.message.WEB3AdminFrontProcessNumberInfoUnit;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCommonRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeSelectRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeSelectResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.dirsec.service.delegate.WEB3AdminFrontOrderRouteChangeService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.gentrade.data.VirtualServerChangeRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者発注経路切替サービス)<BR>
 * <BR>
 * 管理者発注経路切替サービス実装クラス<BR>
 * <BR>
 * WEB3AdminFrontOrderRouteChangeServiceImpl<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontOrderRouteChangeServiceImpl implements WEB3AdminFrontOrderRouteChangeService{
    
    /**
    * Log Variable
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontOrderRouteChangeServiceImpl.class);

    
    /**
     * @@roseuid 43016EED0061
     */
    public WEB3AdminFrontOrderRouteChangeServiceImpl() 
    {
    
    }
   
    /**
     * 管理者発注経路切替サービスを行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドを呼び分ける。<BR>
     * <BR>
     * リクエストデータが、<BR>
     * 　@[管理者・発注経路切替選択リクエストの場合]<BR>
     * 　@　@this.get選択画面()をコールする。<BR>
     * <BR>
     * 　@[管理者・発注経路切替確認リクエストの場合]<BR>
     * 　@　@this.validate発注経路切替()をコールする。<BR>
     * <BR>
     * 　@[管理者・発注経路切替完了リクエストの場合]<BR>
     * 　@　@this.submit発注経路切替()をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 42D233400217
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;
 
        try
        {   
            // get選択画面をコールする。
            if (l_request instanceof WEB3AdminFrontRouteChangeSelectRequest)
            {
                l_response = getSelectScreen((WEB3AdminFrontRouteChangeSelectRequest) l_request);
            } 
            // validate発注経路切替をコールする。
            else if (l_request instanceof WEB3AdminFrontRouteChangeConfirmRequest)
            {
                l_response = validateChange((WEB3AdminFrontRouteChangeConfirmRequest) l_request);
            } 
            // submit発注経路切替をコールする。
            else if (l_request instanceof WEB3AdminFrontRouteChangeCompleteRequest)
            {
                l_response = submitChange((WEB3AdminFrontRouteChangeCompleteRequest) l_request);
            } 
            else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT リクエスト NOT 管理者発注経路切替サービスリクエスト");
            }
        } 
        catch (NotFoundException l_notFoundException)
        {
            log.error(l_notFoundException.getMessage(), l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_notFoundException.getMessage(),
                l_notFoundException);
        } catch (DataQueryException l_dqex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dqex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.toString(),
                l_dqex);

        } catch (DataNetworkException l_dnex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dnex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.toString(),
                l_dnex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;            
    }
   
    /**
     * 管理者発注経路切替選択画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（発注経路切替）get選択画面」参照。<BR>
     * @@param リクエストデータ - 管理者・発注経路切替選択リクエストオブジェクト<BR>
     * @@return 管理者・発注経路切替選択レスポンス<BR>
     * @@roseuid 42D233AA019A
     */
    protected WEB3AdminFrontRouteChangeSelectResponse getSelectScreen(WEB3AdminFrontRouteChangeSelectRequest l_request) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = 
            " getSelectScreen(WEB3AdminFrontRouteChangeSelectRequest  l_request)";
        log.entering(STR_METHOD_NAME);

        // ログイン情報インスタンス
        WEB3Administrator l_administrator = null;
        // フロント発注管理共通サービスインスタンス
        WEB3AdminDirSecFrontOrderCommonService l_commonService =
             (WEB3AdminDirSecFrontOrderCommonService)Services.getService(WEB3AdminDirSecFrontOrderCommonService.class);
        // 処理件数情報オブジェクトList
        List l_lstprocessInfoUnits = new ArrayList();
        // 処理件数情報クラス型配列
        WEB3AdminFrontProcessNumberInfoUnit[] InfoUnits = null;
        // 発注経路切替選択レスポンス
        WEB3AdminFrontRouteChangeSelectResponse l_response = null;
        // 画面表示用変換市場コード
        String l_dispEditMarketCode = null;
  
        // 1.1.リクエストパラメータのnullチェック
        l_request.validate();
        
        // 1.2.ログイン情報インスタンス取得
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 1.3.validate権限チェック()
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_FRONT_ROUTE_SWITCH,
            false);

        //1.4. isDIR管理者( )チェック DIR管理者でない場合、例外をスローする。
        boolean l_blnDir = l_administrator.isDirAdministrator();
        if (!l_blnDir)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        // 1.5.発注経路切替対象レコードを取得
        List l_switchRoutes = l_commonService.getFrontSwitchRouteTarget(l_request.institutionCode);
        
        // 1.6.発注経路切替対象レコードのサイズ分、Loop処理を行う。
        Iterator l_loopObj = l_switchRoutes.iterator();
        
        while(l_loopObj.hasNext()){
            // OrderSwitchingRowオブジェクトの抽出
            OrderSwitchingRow l_swtichRow = (OrderSwitchingParams)l_loopObj.next();
            
            // 証券会社コード取得
            String instCode = l_swtichRow.getInstitutionCode();
            
            // 市場コード取得
            String l_marCode = l_swtichRow.getMarketCode();
            
            // 銘柄タイプ取得
            String l_productType = Integer.toString(l_swtichRow.getProductType().intValue());
            
            // フロント発注システム区分取得
            String l_sysDiv = l_swtichRow.getFrontOrderSystemCode();
 
            // 1.6.1.処理件数情報クラスのオブジェクトを生成
            WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
            
            // 1.6.2.現発注経路を取得
            l_commonService.getNowOrderRoute(instCode, l_marCode, l_sysDiv, l_productType, l_processInfoUnit);
            
            // 1.6.3.仮想サーバ件数を取得
            l_commonService.getVitualServerCount(instCode, l_marCode, l_sysDiv, l_productType, l_processInfoUnit);
            
            // 1.6.4.切替処理方式区分が全訂正処理方式の場合、グレー注文数を取得する。
            if(l_request.changeProcessDiv.equals(WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE)){
                
                if (l_productType.equals(Integer.toString(ProductTypeEnum.EQUITY.intValue())))
                {
                	l_commonService.getGrayOrder(instCode, l_marCode, l_sysDiv, l_processInfoUnit);          
                }
                else if (l_productType.equals(Integer.toString(ProductTypeEnum.IFO.intValue())))
                {
                	l_commonService.getIfoGrayOrder(instCode, l_marCode, l_sysDiv, l_processInfoUnit);
                }
            }
            
            // 1.6.5.切替指示データ件数を取得
            boolean  l_boolSwitchDiv = getSwitchPointDataCount(instCode, l_marCode, l_sysDiv, l_productType,
                l_processInfoUnit, l_request.changeProcessDiv);

            // 1.6.6.切替情報を処理件数情報クラスに格納
            this.createSwitchInfo(l_request.changeProcessDiv, l_processInfoUnit, l_boolSwitchDiv);
            
            // 1.6.7.画面表示用の市場コードを取得する。
            l_dispEditMarketCode = l_commonService.getFrontOrderMarketCode(l_marCode, l_sysDiv);
            
            // 1.6.8.処理件数情報クラスオブジェクトに値をセット
            // 変換市場コード
            l_processInfoUnit.convertMarketCode =l_dispEditMarketCode;
            // 市場コード
            l_processInfoUnit.marketCode = l_swtichRow.getMarketCode();
            // 銘柄タイプ
            l_processInfoUnit.productType = Integer.toString(l_swtichRow.getProductType().intValue());
            // フロント発注システム区分
            l_processInfoUnit.frontOrderSystemCode = l_sysDiv;
            // 変更後発注経路区分
            l_processInfoUnit.changedSubmitOrderRouteDiv = null;
            // 処理件数情報オブジェクトをListに追加。
            l_lstprocessInfoUnits.add(l_processInfoUnit);
        }
        // 処理件数情報オブジェクトListを配列に変換
        InfoUnits = new WEB3AdminFrontProcessNumberInfoUnit[l_lstprocessInfoUnits.size()];
        
        InfoUnits = (WEB3AdminFrontProcessNumberInfoUnit[])l_lstprocessInfoUnits.toArray(InfoUnits);
        
        // 1.7.レスポンスオブジェクト生成
        l_response = (WEB3AdminFrontRouteChangeSelectResponse) l_request.createResponse();

        // 1.8.処理件数情報オブジェクトを格納        
        l_response.orderRouteInfoUnit = InfoUnits;
        
        // 1.9.レスポンスを返却
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }


    /**
     * 管理者発注経路切替確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（発注経路切替）validate発注経路切替」参照。<BR>
     * @@param リクエストデータ - 管理者・発注経路切替確認リクエストオブジェクト<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteComfirmResponse<BR>
     * @@roseuid 42D233AA01B9
     */
    protected WEB3AdminFrontRouteChangeConfirmResponse validateChange(WEB3AdminFrontRouteChangeConfirmRequest l_request)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateChange(WEB3AdminFrontRouteChangeConfirmRequest  l_request)";
        log.entering(STR_METHOD_NAME);
        // 切替起動区分チェックフラグ
        boolean l_boolSwitchDiv = false;
        // フロント発注管理共通サービスインスタンス
        WEB3AdminDirSecFrontOrderCommonService l_commonService =
             (WEB3AdminDirSecFrontOrderCommonService)Services.getService(WEB3AdminDirSecFrontOrderCommonService.class);
        // 発注経路切替確認レスポンス
        WEB3AdminFrontRouteChangeConfirmResponse l_response = null;
  
        // 1.1.リクエストパラメータのnullチェック
        l_request.validate();
        
        // 証券会社コード
        String l_institutionCode = l_request.institutionCode;
        // フロント発注取引所区分コード
        String l_frontOrderExcCode = l_commonService.getFrontOrderExchangeCode(l_request.convertMarketCode);
        // フロント発注システム区分
        String l_frontSysDiv = l_commonService.getFrontSystemDiv(l_request.convertMarketCode);
        
        // 1.2.切替起動区分チェックを実行
        l_boolSwitchDiv = this.validateSwitchBootDiv(l_institutionCode, l_request ,l_commonService);
        
        // validate切替起動区分（）がfalseの場合、例外をスローする。
        if(!l_boolSwitchDiv){
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_02206.error_message);
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02206,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // SONAR全障害チェック
        l_commonService.validateSonarCheck(l_institutionCode, l_frontOrderExcCode, l_frontSysDiv, l_request.productType);  

        // 1.5.レスポンスオブジェクト生成
        l_response = (WEB3AdminFrontRouteChangeConfirmResponse) l_request.createResponse();

        // 1.6.レスポンスを返却
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
   
    /**
     * 管理者発注経路切替完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（発注経路切替）submit発注経路切替」参照。<BR>
     * @@param リクエストデータ - 管理者・発注経路切替完了リクエストオブジェクト<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteResponse<BR>
     * @@roseuid 42D233AA01D9
     */
    protected WEB3AdminFrontRouteChangeCompleteResponse submitChange(WEB3AdminFrontRouteChangeCompleteRequest l_request) throws WEB3BaseException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = 
            "submitChange(WEB3AdminFrontRouteChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        // フロント発注管理共通サービスインスタンス
        WEB3AdminDirSecFrontOrderCommonService l_commonService =
             (WEB3AdminDirSecFrontOrderCommonService)Services.getService(WEB3AdminDirSecFrontOrderCommonService.class);
        // 発注経路切替完了レスポンス
        WEB3AdminFrontRouteChangeCompleteResponse l_response = null;
        
        // 1.1.1.リクエストパラメータのnullチェック
        l_request.validate();

        // サービス起動区分
        String l_serviceStartDiv = l_request.serviceStartDiv;
        // ログイン情報インスタンス
        WEB3Administrator l_administrator = null;
        // 証券会社コード
        String l_institutionCode = l_request.institutionCode;
        // フロント発注取引所区分コード
        String l_frontOrderExcCode = l_commonService.getFrontOrderExchangeCode(l_request.convertMarketCode);
        // フロント発注システム区分
        String l_frontSysDiv = l_commonService.getFrontSystemDiv(l_request.convertMarketCode);
        // 切替起動区分チェックフラグ
        boolean l_boolSwitchDiv = false;
       
        
        // 受付時間チェックは実施しない
		// 受付時間チェックがfalseならエラーを返却
//        if(!l_commonService.isSubmitMarketTrigger(l_institutionCode, l_request.marketCode))
//        {                log.error(STR_METHOD_NAME +
//            WEB3ErrorCatalog.SYSTEM_ERROR_80015.error_message);
//            log.exiting(STR_METHOD_NAME);
//            
//            throw new WEB3BusinessLayerException(
//            WEB3ErrorCatalog.SYSTEM_ERROR_80015,
//            this.getClass().getName() + STR_METHOD_NAME);
//        }

        //発注先切替テーブルDEOSレコード存在チェック
		if(!l_commonService.isFrontRoute(l_institutionCode, l_request.marketCode, l_frontSysDiv, l_request.productType))
		{                log.error(STR_METHOD_NAME +
			WEB3ErrorCatalog.BUSINESS_ERROR_02216.error_message);
			log.exiting(STR_METHOD_NAME);
            
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02216,
				this.getClass().getName() + "." + STR_METHOD_NAME);         
		}
		
        // 1.1.サービス起動区分が管理者起動の場合のみ、チェック処理を行う。
        if(l_serviceStartDiv != null && l_serviceStartDiv.equals(WEB3AdminFrontServiceStartDivDef.ADMINISTRATOR_DIV))
        {
            // 1.1.2.ログイン情報インスタンス取得
            l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
            
            // 1.3.validate権限チェック()
            l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_FRONT_ROUTE_SWITCH,true);

            //1.4. isDIR管理者( )チェック DIR管理者でない場合、例外をスローする。
            boolean l_blnDir = l_administrator.isDirAdministrator();

            if (!l_blnDir)
            {
                log.error(STR_METHOD_NAME +
                    WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
                log.exiting(STR_METHOD_NAME);
            
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                    this.getClass().getName() + STR_METHOD_NAME);            
            }
             
            // 1.1.3.パスワード照合チェック
            l_administrator.validateTradingPassword(l_request.password);
            
            // 1.1.5.切替起動区分チェック
            l_boolSwitchDiv = this.validateSwitchBootDiv(l_institutionCode, l_request, l_commonService);

            // 1.1.6.切替起動区分チェックがfalseの場合、例外をスローする。
            if(!l_boolSwitchDiv){
            
                log.error(STR_METHOD_NAME +
                    WEB3ErrorCatalog.BUSINESS_ERROR_02206.error_message);
                log.exiting(STR_METHOD_NAME);
            
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02206,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            // 1.1.7.SONAR全障害エラーの場合、例外をスローする。
            l_commonService.validateSonarCheck(l_institutionCode, l_frontOrderExcCode, l_frontSysDiv, l_request.productType);
        }
        
        // 1.2.切替処理起動
        l_commonService.executeSwitchTransactionIssue(l_request);
  

        // 1.3.レスポンスオブジェクト生成
        l_response = (WEB3AdminFrontRouteChangeCompleteResponse) l_request.createResponse();

        // 1.4.レスポンスを返却
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
   
    /**
     * 仮想サーバ切替指示データの件数をカウントアップする。<BR>
     * <BR>
     * １）　@フロント発注管理共通サービス.get切替指示応答レコード（）で応答レコードを取得する。<BR>
     * <BR>
     * 　@[引数] 
     * 　@証券会社コード： パラメータ.証券会社コード 
     * 　@市場コード： パラメータ.市場コード 
     * 　@フロント発注システム区分： パラメータ.フロント発注システム区分 
     * 　@銘柄タイプ： パラメータ.銘柄タイプ 
     * <BR>
     * ２）　@if　@仮想サーバ切替指示応答系データ件数 != ０件の場合<BR>
     * <BR>
     * 　@２－１）　@set切替指示応答件数（）処理で、仮想サーバ切替指示データの件数をカウントアップする。<BR>
     * <BR>
     * <BR>
     * ３）　@else　@仮想サーバ切替指示応答系データ件数 == ０件の場合<BR>
     * <BR>
     * 　@３－１）　@フロント発注管理共通サービス.get切替指示要求レコード（）で要求レコードを取得する。<BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@証券会社コード： パラメータ.証券会社コード<BR> 
     * 　@　@市場コード： パラメータ.市場コード <BR>
     * 　@　@フロント発注システム区分： パラメータ.フロント発注システム区分<BR> 
     * 　@　@銘柄タイプ： パラメータ.銘柄タイプ <BR>
     * <BR>
     * 　@３－２）　@３－１） == ０件の場合、falseを返却する。<BR>
     * <BR>
     * 　@３－３）　@３－１） != <BR>
     * ０件の場合、set切替指示応答件数（）処理で、仮想サーバ切替指示データの件数をカウントアップする。<BR>
     * <BR>
     * ４）　@trueを返却する。<BR>
     * @@param 証券会社コード - 証券会社コード。<BR>
     * @@param 市場コード - 市場コード。<BR>
     * @@param フロント発注システム区分 - フロント発注システム区分。<BR>
     * @@param 銘柄タイプ - 銘柄タイプ。<BR>
     * @@param 処理件数情報オブジェクト - 処理件数情報オブジェクト。<BR>
     * @@param 切替処理方式区分 - 切替処理方式区分。<BR>
     * @@return boolean<BR>
     * @@roseuid 42EE0D22009C
     */
    protected boolean getSwitchPointDataCount(String l_institutionCode, String l_marketCode, 
    String l_frontSystemCode, String l_productType, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit,
    String l_changeProcessDiv) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = " getSwitchPointDataCount(String, String, String, String, WEB3AdminFrontProcessNumberInfoUnit, String)";

        log.entering(STR_METHOD_NAME);

        // 応答系レコード
        List l_lstresResRcords = null;
        // 要求系レコード
        List l_lstresReqRcords = null;
        
        // フロント発注管理共通サービスインスタンス
        WEB3AdminDirSecFrontOrderCommonService l_proCommonService =
             (WEB3AdminDirSecFrontOrderCommonService)Services.getService(WEB3AdminDirSecFrontOrderCommonService.class);
        
        // 応答系レコードを取得     
        l_lstresResRcords = l_proCommonService.getSwitchRouteResRcord(l_institutionCode,
            l_marketCode, l_frontSystemCode, l_productType);
        
        if(l_lstresResRcords.size() != 0){
            // 応答系レコードをカウントアップ
            this.setSwitchPointResCount(l_lstresResRcords, l_processInfoUnit, l_changeProcessDiv);
        }
        else if(l_lstresResRcords.size() == 0)
        {
            // 要求系レコードを取得     
            l_lstresReqRcords = l_proCommonService.getSwitchRouteReqRcord(l_institutionCode,
                l_marketCode, l_frontSystemCode, l_productType);
            
            // 要求系レコードが0件の場合、falseを返却
            if(l_lstresReqRcords.size() == 0){
                
                return false; 
            }
            else if(l_lstresReqRcords.size() != 0)
            {
                 // 要求系レコードをカウントアップ
                this.setSwitchPointReqCount(l_lstresReqRcords, l_processInfoUnit);
            }
        }
        return true;
    }
   
    /**
     * 以下の条件から、切替起動区分、画面表示用ステータスを設定する。<BR>
     * <BR>
     * <BR>
     * １）　@発注経路切替区分 == falseの場合<BR>
     * <BR>
     * 　@１－１）　@切替起動区分 = "0:発注経路切替"<BR>
     * <BR>
     * 　@１－２）　@画面表示用ステータス = "0:未処理"<BR>
     * <BR>
     * ２）　@発注経路切替区分 == trueの場合<BR>
     * <BR>
     * ２－１）　@切替処理方式が"１：全訂正処理方式"の場合、check全訂正処理（）を行う。<BR>
     * 　@　@　@if( validate全訂正処理() )の値が"false"の場合<BR>
     * <BR>
     * 　@　@　@　@切替起動区分 = "9:切替不可"<BR>
     * 　@　@　@　@画面表示用ステータス = "9:切替不可"<BR>
     * <BR>
     * ２－２）　@if(グレー注文：市場受付確認中件数 > 0 && 切替処理方式区分 = "1:全訂正処理方式")<BR>
     * <BR>
     * 　@　@　@切替起動区分 = "6:全訂正処理再起動"<BR>
     * 　@　@　@画面表示用ステータス = "1:切替中"<BR>
     * <BR>
     * ２－３）　@if(通番照会要求：未処理件数 > 0 && 切替処理方式区分 = <BR>
     * "0:通番照会処理方式")<BR>
     * <BR>
     * 　@　@　@切替起動区分 = "1:通番照会要求再起動"<BR>
     * 　@　@　@画面表示用ステータス = "1:切替中"<BR>
     * <BR>
     * <BR>
     * <BR>
     * ２－４）　@if(　@通知代行解除要求：未処理件数 > 0　@)<BR>
     * <BR>
     * 　@　@　@切替起動区分 = "2:通知代行解除要求再起動"<BR>
     * 　@　@　@画面表示用ステータス = "1:切替中"<BR>
     * <BR>
     * <BR>
     * ２－５）　@if(　@通知代行要求：未処理件数 > 0　@)<BR>
     * <BR>
     * 　@　@　@切替起動区分 = "3:通知代行要求再起動"<BR>
     * 　@　@　@画面表示用ステータス = "1:切替中"<BR>
     * <BR>
     * <BR>
     * ２－６）　@if(　@通知再送要求（受付系）：未処理件数 > 0　@)<BR>
     * <BR>
     * 　@　@　@切替起動区分 = "4:通知再送要求（受付系）再起動"<BR>
     * 　@　@　@画面表示用ステータス = "1:切替中"<BR>
     * <BR>
     * <BR>
     * ２－７）　@if(　@通知再送要求（約定系）：未処理件数 > 0　@)<BR>
     * <BR>
     * 　@　@　@切替起動区分 = "5:通知再送要求（約定系）再起動"<BR>
     * 　@　@　@画面表示用ステータス = "1:切替中"<BR>
     * <BR>
     * <BR>
     * ２－８）　@２－１）～２－７）を満たさない場合<BR>
     * <BR>
     * 　@切替起動区分 = "7:切替処理完了"<BR>
     * <BR>
     * 　@画面表示用ステータス = "2:切替済"<BR>
     * @@param リクエストオブジェクト - 管理者・発注経路切替選択リクエスト。<BR>
     * @@param 処理件数情報オブジェクト - 処理件数情報オブジェクト。<BR>
     * @@param 発注経路切替区分 - get切替指示データ件数（）の戻り値。<BR>
     * @@roseuid 42EEF7710138
     */
    protected void createSwitchInfo(String l_proChangeDiv, 
                                        WEB3AdminFrontProcessNumberInfoUnit l_proProcessInfoUnit, boolean l_boolProSwDiv) 
    {
        final String STR_METHOD_NAME = " createSwitchInfo(l_proRequest, l_proProcessInfoUnit, boolean)";

        log.entering(STR_METHOD_NAME);
        
        // 引数のbooleanがfalseの場合、発注経路切替処理は新規実行
        if(!l_boolProSwDiv)
        {   
            // 切替起動区分:0:発注経路切替
            l_proProcessInfoUnit.changeStartDiv =WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_ROUTE_SWITCH;
            // 画面用表示ステータス0:未処理
            l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.NO_DEAL;
        }
        else
        {
            // 切替処理方式区分 = "０:通番照会処理方式"の場合            
            if(l_proChangeDiv.equals(WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE))
            {
				// 全訂正処理がtrueの場合、切替不可を設定
				if(validateAllOrderChange(l_proProcessInfoUnit))
				{
					// 切替起動区分:"9:切替不可"
					l_proProcessInfoUnit.changeStartDiv =WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_NO_CHANGE;
					// 画面用表示ステータス"9:切替不可"
					l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_NO_CHANGE;
                    
					return;
				}
				
                // 通番照会要求：未処理件数 > 0 の場合
                if(Integer.parseInt(l_proProcessInfoUnit.nonProcessNumber1) > 0)
                {
                    //切替起動区分"1:通番照会要求再起動"
                    l_proProcessInfoUnit.changeStartDiv = WEB3AdminFrontSwitchStartDivDef.NUBER_REF_REQ_RESTART; 
                    // 画面表示用ステータス"1:切替中"
                    l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_CHANEGE_IN;
                    
                    return;
                }
            }
			// 切替処理方式区分 = "1:全訂正処理方式"の場合
			if(l_proChangeDiv.equals(WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE))
			{
				// 全訂正処理がfalseの場合、切替不可を設定
				if(!validateAllOrderChange(l_proProcessInfoUnit))
				{
					// 切替起動区分:"9:切替不可"
					l_proProcessInfoUnit.changeStartDiv =WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_NO_CHANGE;
					// 画面用表示ステータス"9:切替不可"
					l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_NO_CHANGE;
                    
					return;
				}
			}
            // 以後、通番照会処理、全訂正処理にかかわらず共通処理
            // 通知代行解除要求：未処理件数 > 0 の場合　@
            if(Integer.parseInt(l_proProcessInfoUnit.nonProcessNumber2) > 0)
            {

                //切替起動区分"2:通知代行解除要求再起動" 
                l_proProcessInfoUnit.changeStartDiv = WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REL_REQ_RESTART; 
                // 画面表示用ステータス"1:切替中"
                l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_CHANEGE_IN;
                
				return;
            }
            // 通知代行要求：未処理件数 > 0 の場合
            if(Integer.parseInt(l_proProcessInfoUnit.nonProcessNumber3) > 0)
            {
                // 切替起動区分 "3:通知代行要求再起動" 
                l_proProcessInfoUnit.changeStartDiv = WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REQ_RESTART;
                // 画面表示用ステータス = "1:切替中" 
                l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_CHANEGE_IN;
                
				return;
            }
			// 切替処理方式区分 = "1:全訂正処理方式"の場合
			if(l_proChangeDiv.equals(WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE))
			{
				// グレー注文：市場受付確認前件数 > 0 の場合
				if(Integer.parseInt(l_proProcessInfoUnit.beforeNumber) > 0)
				{
					// 切替起動区分:0:発注経路切替
					l_proProcessInfoUnit.changeStartDiv =WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_ROUTE_SWITCH;
					// 画面用表示ステータス0:未処理
					l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.NO_DEAL;
                       
					return;
				}
				// 「グレー注文：市場受付確認中件数 > 0」または「グレー注文：市場受付確認中件数 = 0 かつ通知再送要求（受付系）：処理済件数 = 0」の場合
				else if((Integer.parseInt(l_proProcessInfoUnit.inNumber) > 0 ) ||
						(Integer.parseInt(l_proProcessInfoUnit.inNumber) == 0 && Integer.parseInt(l_proProcessInfoUnit.finProcessNumber4) == 0))
				{
					// 切替起動区分 = "6:全訂正処理再起動"
					l_proProcessInfoUnit.changeStartDiv = WEB3AdminFrontSwitchStartDivDef.ALLCORR_REQ_RESTART;
					// 画面表示用ステータス = "1:切替中"
					l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_CHANEGE_IN;
                        
					return;
				}
                                       
			}
            // 通知再送要求（受付系）：未処理件数 > 0 の場合
            if(Integer.parseInt(l_proProcessInfoUnit.nonProcessNumber4) > 0)
            {
                // 切替起動区分 "4:通知再送要求（受付系）再起動" 
                l_proProcessInfoUnit.changeStartDiv = WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_ACC_RESTART;
                // 画面表示用ステータス = "1:切替中" 
                l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_CHANEGE_IN;
                
				return;
            }
            // 通知再送要求（約定系）：未処理件数 > 0 の場合
            if(Integer.parseInt(l_proProcessInfoUnit.nonProcessNumber5) > 0)
            {
                // 切替起動区分 "5:通知再送要求（約定系）再起動" 
                l_proProcessInfoUnit.changeStartDiv = WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_CONT_RESTART;
                // 画面表示用ステータス = "1:切替中" 
                l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_CHANEGE_IN;
                
				return;
            }
            
                // 切替起動区分 "7:切替処理完了"
                l_proProcessInfoUnit.changeStartDiv = WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_CHANGE_COMPLETE; 

                // 画面表示用ステータス "2:切替済"
                l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_CHANEGE_END; 
            
        }
    }
   
    /**
     * 全訂正処理に切替可能かチェックを行う。<BR>
     * <BR>
     * 1) booleanオブジェクト = falseを生成する。<BR>
     * <BR>
     * if(通番照会要求：未処理 + 通番照会要求：処理済 > 0)<BR>
     * <BR>
     * 　@falseを返却する。<BR>
     * <BR>
     * else<BR>
     * 　@trueを返却する。<BR>
     * @@param 処理件数情報オブジェクト - 処理件数情報オブジェクト。<BR>
     * @@return boolean<BR>
     * @@roseuid 42EEC82803D0
     */
    private boolean validateAllOrderChange(WEB3AdminFrontProcessNumberInfoUnit l_PriProcessInfoUnit) 
    {
        boolean l_orderChangeDiv = false;
        
        int l_intProInfo = Integer.parseInt(l_PriProcessInfoUnit.nonProcessNumber1) + Integer.parseInt(l_PriProcessInfoUnit.finProcessNumber1);
        
        if(l_intProInfo > 0)
        {
            return  l_orderChangeDiv;
        }
        else
        {
            l_orderChangeDiv = true;
        }

        return l_orderChangeDiv;
    }
    
    /**
     * リクエストで渡された切替起動区分と、現在の切替起動区分が正しいかチェックを行う。<BR>
     * <BR>
     * <BR>
     * １）　@処理件数情報オブジェクトの生成。<BR>
     * <BR>
     * ２）　@フロント発注管理共通サービス.getフロント発注システム区分（)で、フロント発注システム区分を取得する。<BR>
     * <BR>
     * ３）　@フロント発注管理共通サービス.get仮想サーバ件数（）で、仮想サーバ件数を取得する。<BR>
     * <BR>
     * 　@[引数]<BR> 
     * 　@証券会社コード： パラメータ.証券会社コード<BR> 
     * 　@市場コード： パラメータ.リクエスト.市場コード<BR>
     * 　@フロント発注システム区分： ２）のフロント発注システム区分<BR> 
     * 　@銘柄タイプ： パラメータ.リクエスト.銘柄タイプ<BR> 
     * 　@処理件数情報： １）の処理件数情報<BR> 
     * <BR>
     * ４）　@if（パラメータ.切替処理起動区分 = "１：全訂正処理方式切替"）の場合、<BR>
     * <BR>
     * 　@４－１）　@パラメータ.リクエスト.銘柄タイプが1：株式の場合<BR>
     * <BR>
     * 　@　@４－１－１）　@フロント発注管理共通サービス.getグレー注文（）で、グレー注文件数を取得する。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@証券会社コード： パラメータ.証券会社コード<BR>
     * 　@　@　@市場コード： パラメータ.リクエスト.市場コード<BR>
     * 　@　@　@フロント発注システム区分： ２）のフロント発注システム区分<BR>
     * 　@　@　@処理件数情報： １）の処理件数情報 <BR>
     * <BR>
     * 　@４－２）　@パラメータ.リクエスト.銘柄タイプが6：先物オプションの場合<BR>
     * <BR>
     * 　@　@４－２－１）　@フロント発注管理共通サービス.get先物OPグレー注文（）で、グレー注文件数を取得する。<BR>
     * <BR>
     * 　@　@　@[引数] <BR>
     * 　@　@　@証券会社コード： パラメータ.証券会社コード<BR>
     * 　@　@　@市場コード： パラメータ.リクエスト.市場コード<BR>
     * 　@　@　@フロント発注システム区分： ２）のフロント発注システム区分<BR>
     * 　@　@　@処理件数情報： １）の処理件数情報 <BR>
     * <BR>
     * ５）　@get切替指示データ件数（）で、仮想サーバ切替指示データ件数を取得する。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@証券会社コード： パラメータ.証券会社コード<BR>
     * 　@市場コード： パラメータ.リクエスト.市場コード<BR>
     * 　@フロント発注システム区分： ２）のフロント発注システム区分<BR>
     * 　@銘柄タイプ： パラメータ.リクエスト.銘柄タイプ<BR>
     * 　@処理件数情報： １）の処理件数情報<BR>
     * 　@切替処理方式区分： パラメータ.リクエスト.切替処理方式区分<BR>
     * <BR>
     * ６）　@create切替情報（）<BR>
     * <BR>
     * ７）　@処理件数情報.切替起動区分 != null、<BR>
     * 　@　@　@かつ、処理件数情報.切替起動区分 == パラメータ.リクエスト.切替起動区分の場合<BR> 
     * <BR>
     * 　@７－１）　@trueを返却。<BR>
     * <BR>
     * 　@７－２）　@elseの場合、falseを返却。<BR>
     * @@param 証券会社コード - 証券会社コード。<BR>
     * @@param リクエストオブジェクト - 管理者・発注経路切替共通リクエスト。<BR>
     * @@param フロント発注管理共通サービス - 共通管理サービスオブジェクト。<BR>
     * @@return boolean<BR>
     * @@roseuid 42F055BC022E
     */
    private boolean validateSwitchBootDiv(String l_priInstCode, WEB3AdminFrontRouteChangeCommonRequest l_priRequest, WEB3AdminDirSecFrontOrderCommonService l_priCommonObj) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "validateSwitchBootDiv(String, l_priRequest,l_priProcessInfo)";
        log.entering(STR_METHOD_NAME);
     
        // 戻り値
        boolean l_boolret = false;
        // 現切替起動区分
        String l_nowChangeStartDiv = null;
        // フロント発注システム区分
        String l_frontSysDiv = null;
        // 変換市場コード
        String l_convertMarketCode = null;
        // 切替処理方式区分
        String l_changeProDiv = null;
        // 市場コード
        String l_marketCode = null;
        // 銘柄タイプ
        String l_strProductType = null;
        
        // リクエストの切替起動区分を取得
        String l_paramChangeStartDiv = l_priRequest.changeStartDiv;
        
        // 処理件数情報クラスオブジェクトの生成
        WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
        
        // 変換市場コードをフィールドにセット
        l_convertMarketCode = l_priRequest.convertMarketCode;
        
        // 市場コードをフィールドにセット
        l_marketCode = l_priRequest.marketCode;
        
        // 切替処理方式区分をフィールドにセット
        l_changeProDiv = l_priRequest.changeProcessDiv;

        // 銘柄タイプ
        l_strProductType = l_priRequest.productType;
        
        // フロント発注システム区分を取得
        l_frontSysDiv = l_priCommonObj.getFrontSystemDiv( l_convertMarketCode);
        
        // 仮想サーバ件数を取得
        l_priCommonObj.getVitualServerCount(l_priInstCode, l_marketCode, l_frontSysDiv, l_strProductType, l_processInfoUnit);
        
        // 切替処理方式区分が全訂正処理方式の場合、グレー注文数を取得する。
        if(l_changeProDiv.equals(WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE)){
                
            if (l_strProductType.equals(Integer.toString(ProductTypeEnum.EQUITY.intValue())))
            {
                l_priCommonObj.getGrayOrder(l_priInstCode, l_marketCode, l_frontSysDiv, l_processInfoUnit);          
            }
            else if (l_strProductType.equals(Integer.toString(ProductTypeEnum.IFO.intValue())))
            {
            	l_priCommonObj.getIfoGrayOrder(l_priInstCode, l_marketCode, l_frontSysDiv, l_processInfoUnit);
            }
        }
        // 切替指示データ件数を取得
        boolean  l_boolSwitchDiv = getSwitchPointDataCount(l_priInstCode, l_marketCode,
            l_frontSysDiv, l_strProductType, l_processInfoUnit, l_changeProDiv);

        // 切替情報を処理件数情報クラスに格納
        this.createSwitchInfo(l_priRequest.changeProcessDiv, l_processInfoUnit, l_boolSwitchDiv);
        
        // 現切替起動区分を取得
        l_nowChangeStartDiv = l_processInfoUnit.changeStartDiv;
        
        // 現切替起動区分と、パラメータの切替起動区分が同一の場合はtrueを格納
        if(l_nowChangeStartDiv != null && l_nowChangeStartDiv.equals(l_paramChangeStartDiv))
        {
            l_boolret = true;
        }
    
        return l_boolret;
    }


   
    /**
     * 障害仮想サーバ切替管理テーブルから取得した切替指示要求系レコードをカウントアップし、件数を格納する。 <BR>
     *  <BR>
     * [パラメータ] <BR>
     * 切替指示要求レコード <BR>
     * 処理件数情報クラスオブジェクト <BR>
     *  <BR>
     * １）　@仮想サーバ切替指示データの件数分Loop処理を行い、以下の条件から、切替指示データをカウントアップする。 <BR>
     *  <BR>
     * 　@１－１）　@if（切替指示要求レコード.切替指示応答区分 = "通番照会要求"）  <BR>
     *  <BR>
     * 　@　@　@"通番照会要求：未処理"　@をカウントアップ。  <BR>
     *  <BR>
     * 　@１－２）　@if（切替指示要求レコード.切替指示応答区分 = "通知代行解除要求"）  <BR>
     *  <BR>
     * 　@　@　@"通知代行解除要求：未処理"　@をカウントアップ。  <BR>
     *  <BR>
     * 　@１－３）　@if（切替指示要求レコード.切替指示応答区分 = "通知代行要求"）  <BR>
     *  <BR>
     * 　@　@　@１－３－１）"通知代行要求：未処理"　@をカウントアップ。  <BR>
     *  <BR>
     * 　@　@　@１－３－２）　@if（切替指示要求レコード.フロント発注取引所区分コード = "東証" or "名証"）  <BR>
     *  <BR>
     * 　@　@　@　@　@"通知再送要求（受付系）：未処理", "通知再送要求（約定系）：未処理"をカウントアップ。  <BR>
     *  <BR>
     * 　@１－４）　@if（切替指示要求レコード.切替指示応答区分 = "通知再送要求" and  <BR>
     * 　@　@　@　@　@　@切替指示要求レコード.通知種別 = "受付系"）  <BR>
     *  <BR>
     * 　@　@　@"通知再送要求（受付系）：未処理"　@をカウントアップ。  <BR>
     *  <BR>
     * 　@１－５）　@if（切替指示要求レコード.切替指示応答区分 = "通知再送要求" and  <BR>
     * 　@　@　@　@　@　@切替指示要求レコード.通知種別 = "約定系"）  <BR>
     *  <BR>
     * 　@　@　@"通知再送要求（約定系）：未処理"　@をカウントアップ。  <BR>
     *  <BR>
     * ２）　@仮想サーバ切替指示データの”処理済”件数に0を設定する。  <BR>
     *  <BR>
     * 　@２－１）　@通番照会要求：処理済　@=　@０  <BR>
     *  <BR>
     * 　@２－２）　@通知代行解除要求：処理済　@=　@０  <BR>
     *  <BR>
     * 　@２－３）　@通知代行要求：処理済　@=　@０  <BR>
     *  <BR>
     * 　@２－４）　@通知再送要求（受付系）：処理済　@=　@０  <BR>
     *  <BR>
     * 　@２－５）　@通知再送要求（約定系）：処理済　@=　@０  <BR>
     * @@param 切替指示要求レコード - <BR>
     * 障害仮想サーバ切替管理テーブルから取得した切替指示要求系レコード。<BR>
     * @@param 処理件数情報クラスオブジェクト - 処理件数情報クラスオブジェクト。<BR>
     * @@roseuid 42FC6804011F
     */
    private void setSwitchPointReqCount(List l_priReqList, WEB3AdminFrontProcessNumberInfoUnit l_PriProcessInfoUnit) 
    {
        final String STR_METHOD_NAME = " setSwitchPointReqCount(String, String)";

        log.entering(STR_METHOD_NAME);

        // 通番照会要求：未処理   
        int l_intNumberRefRes_noTran = 0;
        // 通番照会要求：処理済   
        int l_intNumberRefRes_tran = 0;
        // 通知代行解除要求：未処理        
        int l_intNoticeAngRelRes_noTran = 0;
        // 通知代行解除要求：処理済        
        int l_intNoticeAngRelRes_tran = 0;
        // 通知代行要求：未処理
        int l_intNoticeAngRes_noTran = 0;
        // 通知代行要求：処理済        
        int l_intNoticeAngRes_tran = 0;
        // 通知再送要求（受付系）：未処理
        int l_intNoticeReSenRes_recept_noTran = 0;
        // 通知再送要求（受付系）：処理済        
        int l_intNoticeReSenRes_recept_tran = 0;
        // 通知再送要求（約定系）：未処理
        int l_intNoticeReSenRes_cont_noTran = 0;
        // 通知再送要求（約定系）：処理済        
        int l_intNoticeReSenRes_cont_tran = 0;
        
        // レコードのサイズ分、Loop処理
        Iterator l_iteObj = l_priReqList.iterator();
        
        while(l_iteObj.hasNext()){
            
            // VirtualServerChangeRowオブジェクトの抽出
            VirtualServerChangeRow l_changeRow = (VirtualServerChangeRow)l_iteObj.next();
            
            // 切替指示応答区分を取得
            String l_resDiv = l_changeRow.getChangeReqResDiv();

            //フロント発注取引所区分コードを取得
            String l_strFrontOrderExchangeCode = l_changeRow.getFrontOrderExchangeCode();

            // 通知種別を取得
            String l_noticeType = l_changeRow.getNoticeType();            
            
            // 処理区分を取得
            String status = l_changeRow.getStatus();

            // 通番照会要求：未処理件数をカウントアップ。
            if(l_resDiv.equals(WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_REQUEST))
            {
                l_intNumberRefRes_noTran = l_intNumberRefRes_noTran + 1;                
            }
            // 通知代行解除要求：未処理件数をカウントアップ。
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_REQUEST))
            {
                l_intNoticeAngRelRes_noTran = l_intNoticeAngRelRes_noTran + 1;                
            }
            //if（切替指示要求レコード.切替指示応答区分 = "通知代行要求"）        
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_AGENCY_REQUEST))
            {
                //通知代行要求：未処理件数をカウントアップ。
                l_intNoticeAngRes_noTran = l_intNoticeAngRes_noTran + 1;
                //if（切替指示要求レコード.フロント発注取引所区分コード = "東証" or "名証"）
                //"通知再送要求（受付系）：未処理", "通知再送要求（約定系）：未処理"をカウントアップ。
                if(WEB3FrontOrderExchangeCodeDef.TOKYO_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode)
                    || WEB3FrontOrderExchangeCodeDef.NAGOYA_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode))
                {
                    l_intNoticeReSenRes_recept_noTran = l_intNoticeReSenRes_recept_noTran + 1;
                    l_intNoticeReSenRes_cont_noTran = l_intNoticeReSenRes_cont_noTran + 1;
                }
            }
            // 通知再送要求（受付系）：未処理件数をカウントアップ。
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST) && 
            l_noticeType.equals(WEB3NoticeTypeDef.ACCEPT_TYPE))
            {
                l_intNoticeReSenRes_recept_noTran = l_intNoticeReSenRes_recept_noTran + 1;                
            }
            // 通知再送要求（約定系）：未処理件数をカウントアップ。
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST) && 
            l_noticeType.equals(WEB3NoticeTypeDef.EXECUTED_TYPE))
            {
                l_intNoticeReSenRes_cont_noTran = l_intNoticeReSenRes_cont_noTran + 1;                
            }
        }
        
        // 処理件数情報クラスオブジェクトに各件数を格納
        l_PriProcessInfoUnit.finProcessNumber1 = Integer.toString(l_intNumberRefRes_tran);
        l_PriProcessInfoUnit.finProcessNumber2 = Integer.toString(l_intNoticeAngRelRes_tran);
        l_PriProcessInfoUnit.finProcessNumber3 = Integer.toString(l_intNoticeAngRes_tran);
        l_PriProcessInfoUnit.finProcessNumber4 = Integer.toString(l_intNoticeReSenRes_recept_tran);
        l_PriProcessInfoUnit.finProcessNumber5 = Integer.toString(l_intNoticeReSenRes_cont_tran);
        l_PriProcessInfoUnit.nonProcessNumber1 = Integer.toString(l_intNumberRefRes_noTran);
        l_PriProcessInfoUnit.nonProcessNumber2 = Integer.toString(l_intNoticeAngRelRes_noTran);
        l_PriProcessInfoUnit.nonProcessNumber3 = Integer.toString(l_intNoticeAngRes_noTran);
        l_PriProcessInfoUnit.nonProcessNumber4 = Integer.toString(l_intNoticeReSenRes_recept_noTran);
        l_PriProcessInfoUnit.nonProcessNumber5 = Integer.toString(l_intNoticeReSenRes_cont_noTran);
    }
   
    /**
     * 障害仮想サーバ切替管理テーブルから取得した切替指示応答系レコードをカウントアップし、件数を格納する。 <BR>
     * <BR>
     * [パラメータ] <BR>
     * 切替指示応答レコード <BR>
     * 処理件数情報クラスオブジェクト <BR>
     * 切替処理方式区分 <BR>
     * <BR>
     * １）　@仮想サーバ切替指示データの件数分Loop処理を行い、以下の条件から、切替指示データをカウントアップする。 <BR>
     * <BR>
     * 　@１－１）　@if（切替指示応答レコード.切替指示応答区分 = "通番照会応答" and <BR>
     * 　@　@　@　@　@　@切替指示応答レコード.処理区分 = "処理済"） <BR>
     * <BR>
     * 　@　@　@"通番照会応答：処理済"　@をカウントアップ。 <BR>
     * <BR>
     * 　@１－２）　@if（切替指示応答レコード.切替指示応答区分 = "通知代行解除応答" and <BR>
     * 　@　@　@　@　@　@切替指示応答レコード.処理区分 = "処理済"） <BR>
     * <BR>
     * 　@　@　@"通知代行解除応答：処理済"　@をカウントアップ。 <BR>
     * <BR>
     * 　@１－３）　@if（切替指示応答レコード.切替指示応答区分 = "通知代行応答" and <BR>
     * 　@　@　@　@　@　@切替指示応答レコード.処理区分 = "処理済"） <BR>
     * <BR>
     * 　@　@　@１－３－１）　@"通知代行応答：処理済"　@をカウントアップ。 <BR>
     * <BR>
     * 　@　@　@１－３－２）　@if（切替指示応答レコード.フロント発注取引所区分コード = "東証" or "名証"） <BR>
     * <BR>
     * 　@　@　@　@　@"通知再送応答（受付系）：処理済", "通知再送応答（約定系）：処理済"をカウントアップ。 <BR>
     * <BR>
     * 　@１－４）　@if（切替指示応答レコード.切替指示応答区分 = "通知再送応答" and <BR>
     * 　@　@　@　@　@　@切替指示応答レコード.通知種別 = "受付系" and <BR>
     * 　@　@　@　@　@　@切替指示応答レコード.処理区分 = "処理済"） <BR>
     * <BR>
     * 　@　@　@"通知再送応答（受付系）：処理済"　@をカウントアップ。 <BR>
     * <BR>
     * 　@１－５）　@if（切替指示応答レコード.切替指示応答区分 = "通知再送応答" and <BR>
     * 　@　@　@　@　@　@切替指示応答レコード.通知種別 = "約定系" and <BR>
     * 　@　@　@　@　@　@切替指示応答レコード.処理区分 = "処理済"） <BR>
     * <BR>
     * 　@　@　@"通知再送応答（約定系）：処理済"　@をカウントアップ。 <BR>
     * <BR>
     * <BR>
     * ２）　@処理件数情報クラスオブジェクト.仮想サーバ数と算出した切替指示データの各処理済件数から未処理件数を算出する。 <BR>
     * <BR>
     * 　@２－１）「切替処理方式区分が"通番照会処理方式"かつ処理済件数 > 0」の場合　@ <BR>
     * 　@　@　@　@　@　@通番照会要求：未処理件数 = 処理件数情報クラスオブジェクト.仮想サーバ数 - 通番照会応答：処理済 <BR>
     * 　@　@　@　@　@　@ <BR>
     * 　@　@　@　@　@　@上記以外の場合 <BR>
     * 　@　@　@　@　@　@通番照会要求：未処理件数 = 0 <BR>
     * <BR>
     * 　@２－２）　@通知代行解除要求：未処理件数 = 処理件数情報クラスオブジェクト.仮想サーバ数 - 通知代行解除応答：処理済 <BR>
     * <BR>
     * 　@２－３）　@通知代行要求：未処理件数 = 処理件数情報クラスオブジェクト.仮想サーバ数 - 通知代行応答：処理済 <BR>
     * <BR>
     * 　@２－４）　@通知再送要求（受付系）：未処理件数 = 処理件数情報クラスオブジェクト.仮想サーバ数 - 通知再送応答（受付系） <BR>
     * <BR>
　@   *   ２－５）　@通知再送要求（約定系）：未処理件数 = 処理件数情報クラスオブジェクト.仮想サーバ数 - 通知再送応答（約定系）<BR>
     * @@param 切替指示応答レコード - <BR>
     * 障害仮想サーバ切替管理テーブルから取得した応答系レコード。<BR>
     * @@param 処理件数情報クラスオブジェクト - 処理件数情報クラスオブジェクト。<BR>
     * @@param 切替処理方式区分 - 切替処理方式区分。<BR>
     * @@roseuid 42FC625D010F
     */
    private void setSwitchPointResCount(List l_priResList, WEB3AdminFrontProcessNumberInfoUnit l_PriProcessInfoUnit, String l_PrichangeProcessDiv) 
    {
        final String STR_METHOD_NAME = " setSwitchPointResCount(String, String)";

        log.entering(STR_METHOD_NAME);

        // 通番照会応答：未処理   
        int l_intNumberRefRes_noTran = 0;
        // 通番照会応答：処理済   
        int l_intNumberRefRes_tran = 0;
        // 通知代行解除応答：未処理        
        int l_intNoticeAngRelRes_noTran = 0;
        // 通知代行解除応答：処理済        
        int l_intNoticeAngRelRes_tran = 0;
        // 通知代行応答：未処理
        int l_intNoticeAngRes_noTran = 0;
        // 通知代行応答：処理済        
        int l_intNoticeAngRes_tran = 0;
        // 通知再送応答（受付系）：未処理
        int l_intNoticeReSenRes_recept_noTran = 0;
        // 通知再送応答（受付系）：処理済        
        int l_intNoticeReSenRes_recept_tran = 0;
        // 通知再送応答（約定系）：未処理
        int l_intNoticeReSenRes_cont_noTran = 0;
        // 通知再送応答（約定系）：処理済        
        int l_intNoticeReSenRes_cont_tran = 0;

        // 仮想サーバ件数
        int l_intVirServerNonTran = Integer.parseInt(l_PriProcessInfoUnit.virtualServerQuantity);
        
        // レコードのサイズ分、Loop処理
        Iterator l_iteObj = l_priResList.iterator();
        
        while(l_iteObj.hasNext()){
            
            // VirtualServerChangeRowオブジェクトの抽出
            VirtualServerChangeRow l_changeRow = (VirtualServerChangeRow)l_iteObj.next();
            
            // 切替指示応答区分を取得
            String l_resDiv = l_changeRow.getChangeReqResDiv();

            //フロント発注取引所区分コードを取得
            String l_strFrontOrderExchangeCode = l_changeRow.getFrontOrderExchangeCode();

            // 通知種別を取得
            String l_noticeType = l_changeRow.getNoticeType();            
            
            // 処理区分を取得
            String status = l_changeRow.getStatus();

            // 通番照会要求：処理済件数をカウントアップ。
            if(l_resDiv.equals(WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_RESPONSE) && status.equals(WEB3FrontOrderStatusDef.SENDED))
            {
                l_intNumberRefRes_tran = l_intNumberRefRes_tran + 1;                
            }
            // 通知代行解除要求：処理済件数をカウントアップ。
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_RESPONSE) && status.equals(WEB3FrontOrderStatusDef.SENDED))
            {
                l_intNoticeAngRelRes_tran = l_intNoticeAngRelRes_tran + 1;                
            }
            //if（切替指示応答レコード.切替指示応答区分 = "通知代行応答" and
            //切替指示応答レコード.処理区分 = "処理済"）
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_AGENCY_RESPONSE) && status.equals(WEB3FrontOrderStatusDef.SENDED))
            {
                //通知代行応答：処理済件数をカウントアップ。
                l_intNoticeAngRes_tran = l_intNoticeAngRes_tran + 1;
                //if（切替指示応答レコード.フロント発注取引所区分コード = "東証" or "名証"）
                //"通知再送応答（受付系）：処理済", "通知再送応答（約定系）：処理済"をカウントアップ。
                if(WEB3FrontOrderExchangeCodeDef.TOKYO_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode)
                    || WEB3FrontOrderExchangeCodeDef.NAGOYA_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode))
                {
                    l_intNoticeReSenRes_recept_tran = l_intNoticeReSenRes_recept_tran + 1;
                    l_intNoticeReSenRes_cont_tran = l_intNoticeReSenRes_cont_tran + 1;
                }
            }
            // 通知再送要求（受付系）：処理済件数をカウントアップ。
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_RESEND_RESPONSE) && 
            l_noticeType.equals(WEB3NoticeTypeDef.ACCEPT_TYPE) && status.equals(WEB3FrontOrderStatusDef.SENDED))
            {
                l_intNoticeReSenRes_recept_tran = l_intNoticeReSenRes_recept_tran + 1;                
            }
            // 通知再送要求（約定系）：処理済件数をカウントアップ。
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_RESEND_RESPONSE) && 
            l_noticeType.equals(WEB3NoticeTypeDef.EXECUTED_TYPE) && status.equals(WEB3FrontOrderStatusDef.SENDED))
            {
                l_intNoticeReSenRes_cont_tran = l_intNoticeReSenRes_cont_tran + 1;                
            }
        }
        // 通番照会要求:未処理件数
        // 「通番照会処理方式かつ処理済件数 > 0」の場合のみ、カウント。全訂正処理方式では0を設定。
        if(l_PrichangeProcessDiv.equals(WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE) && (l_intNumberRefRes_tran > 0))
        {
            l_intNumberRefRes_noTran = l_intVirServerNonTran - l_intNumberRefRes_tran;
        }
        else
        {
            l_intNumberRefRes_noTran = 0;
        }
        // 通知代行解除要求:未処理件数
        l_intNoticeAngRelRes_noTran = l_intVirServerNonTran - l_intNoticeAngRelRes_tran;
        // 通知代行要求：未処理件数
        l_intNoticeAngRes_noTran = l_intVirServerNonTran - l_intNoticeAngRes_tran;
        // 通知再送要求（受付系）：未処理件数
        l_intNoticeReSenRes_recept_noTran = l_intVirServerNonTran - l_intNoticeReSenRes_recept_tran;
        // 通知再送要求（約定系）：未処理件数
        l_intNoticeReSenRes_cont_noTran =  l_intVirServerNonTran - l_intNoticeReSenRes_cont_tran;
        
        // 処理件数情報クラスオブジェクトに各件数を格納
        l_PriProcessInfoUnit.finProcessNumber1 = Integer.toString(l_intNumberRefRes_tran);
        l_PriProcessInfoUnit.finProcessNumber2 = Integer.toString(l_intNoticeAngRelRes_tran);
        l_PriProcessInfoUnit.finProcessNumber3 = Integer.toString(l_intNoticeAngRes_tran);
        l_PriProcessInfoUnit.finProcessNumber4 = Integer.toString(l_intNoticeReSenRes_recept_tran);
        l_PriProcessInfoUnit.finProcessNumber5 = Integer.toString(l_intNoticeReSenRes_cont_tran);
        l_PriProcessInfoUnit.nonProcessNumber1 = Integer.toString(l_intNumberRefRes_noTran);
        l_PriProcessInfoUnit.nonProcessNumber2 = Integer.toString(l_intNoticeAngRelRes_noTran);
        l_PriProcessInfoUnit.nonProcessNumber3 = Integer.toString(l_intNoticeAngRes_noTran);
        l_PriProcessInfoUnit.nonProcessNumber4 = Integer.toString(l_intNoticeReSenRes_recept_noTran);
        l_PriProcessInfoUnit.nonProcessNumber5 = Integer.toString(l_intNoticeReSenRes_cont_noTran);
    }

}
@
