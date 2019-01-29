head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoUploadActionUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOアップロード履歴明細作成サービス実装クラス(WEB3IPOUploadHistoryUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
Revesion History : 2005/01/05 坂上(SRA) 残対応>>>053
*/

package webbroker3.ipo.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.ipo.define.WEB3LotDivDef;
import webbroker3.ipo.define.WEB3UploadStateDivDef;
import webbroker3.ipo.message.WEB3IPOUploadHistoryUnit;
import webbroker3.ipo.service.delegate.WEB3IpoUploadActionUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * IPOアップロード履歴明細作成サービス実装クラス
 *                                                              
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3IpoUploadActionUnitServiceImpl implements WEB3IpoUploadActionUnitService 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IpoUploadActionUnitServiceImpl.class);        
    
    /**
     * @@roseuid 4112F191020B
     */
    public WEB3IpoUploadActionUnitServiceImpl() 
    {
     
    }
    
    /**
     * (createアップロード履歴明細)<BR>
     * （管理者共通）アップロード行オブジェクトより、<BR>
     * IPOアップロード履歴明細オブジェクトを作成する。<BR>
     * <BR>
     * IPOアップロード履歴明細オブジェクトを生成し、<BR>
     * 以下の通りプロパティをセットして返却する。<BR>
     * <BR>
     * ○　@未抽選（アップロード履歴行 == null）の場合、<BR>
     * 　@IPOアップロード履歴明細.抽選区分 = ”新規抽選”<BR>
     * 　@IPOアップロード履歴明細.アップロード状態 = ”アップロード待ち”<BR>
     * 　@（以外、null）<BR>
     * <BR>
     * ○　@抽選済（アップロード履歴行 != null）の場合、<BR>
     * 　@IPOアップロード履歴明細.抽選区分 = アップロード履歴行.備考１<BR>
     * 　@IPOアップロード履歴明細.アップロード処理状態区分 = <BR>
     * 　@　@（アップロード履歴行.アップロード終了日時 == null）の場合、<BR>
     * ”アップロード中”<BR>
     * 　@　@（アップロード履歴行.アップロード終了日時 != null）の場合、<BR>
     * ”アップロード済”<BR>
     * 　@IPOアップロード履歴明細.アップロード件数 = <BR>
     * アップロード履歴行.アップロード件数<BR>
     * 　@IPOアップロード履歴明細.アップロード開始日時 = <BR>
     * アップロード履歴行.アップロード開始日時<BR>
     * 　@IPOアップロード履歴明細.アップロード終了日時 = <BR>
     * アップロード履歴行.アップロード終了日時<BR>
     * 　@IPOアップロード履歴明細.IPOエラー番号 = <BR>
     * アップロード履歴行.メッセージコード<BR>
     * @@throws WEB3BaseException
     * @@param l_administratorUploadParams - (アップロード履歴行)<BR>
     * アップロード履歴行オブジェクト<BR>
     * <BR>
     * ※　@（管理者共通）アップロードParamsクラスはDDLにて自動生成<BR>
     * @@return webbroker3.ipo.message.WEB3IpoUploadActionUnit
     * @@roseuid 40F224BE00AD
     */
    public WEB3IPOUploadHistoryUnit createUploadActionUnit(AdministratorUploadParams l_administratorUploadParams) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createUploadActionUnit(AdministratorUploadParams)";
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOUploadHistoryUnit l_ipoUploadHistoryUnit = 
            new WEB3IPOUploadHistoryUnit();
        if(l_administratorUploadParams == null)
        {
            //抽選区分
            l_ipoUploadHistoryUnit.lotDiv = WEB3LotDivDef.OPEN_LOTTERY;
            
            //アップロード状態 
            l_ipoUploadHistoryUnit.uploadStateDiv = WEB3UploadStateDivDef.DEFAULT;
            
            
            //* アップロード件数
            l_ipoUploadHistoryUnit.uploadNumber = null;
    
            //* アップロード開始日時
            l_ipoUploadHistoryUnit.uploadStartDate = null;

            //* アップロード終了日時
            l_ipoUploadHistoryUnit.uploadEndDate = null;
    
            //* IPOエラー番号<BR>
            l_ipoUploadHistoryUnit.ipoErrorId = null;
        }
        else
        {
            //抽選区分
            l_ipoUploadHistoryUnit.lotDiv = l_administratorUploadParams.getNote1();
            
            //アップロード処理状態区分
            if(l_administratorUploadParams.getUploadEndTimestamp() == null)
            {
                l_ipoUploadHistoryUnit.uploadStateDiv = WEB3UploadStateDivDef.UPLOAD_STATUS_PROCESS;
            }
            else
            {
                l_ipoUploadHistoryUnit.uploadStateDiv = WEB3UploadStateDivDef.UPLOAD_STATUS_COMPLETE;
            }
            
            //アップロード件数
            l_ipoUploadHistoryUnit.uploadNumber = Integer.toString(l_administratorUploadParams.getUploadCount());
            
            //アップロード開始日時
            l_ipoUploadHistoryUnit.uploadStartDate = l_administratorUploadParams.getUploadStartTimestamp();
            
            //アップロード終了日時
            l_ipoUploadHistoryUnit.uploadEndDate = l_administratorUploadParams.getUploadEndTimestamp();
            
            //IPOエラー番号
            l_ipoUploadHistoryUnit.ipoErrorId = l_administratorUploadParams.getMessageCode();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_ipoUploadHistoryUnit;
    }
}
@
