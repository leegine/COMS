head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultUploadUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果アップロード１件サービスインタフェイス(WEB3AdminIpoLotResultUploadUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 李海波 (中訊) 新規作成
*/

package webbroker3.ipo.service.delegate;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.ipo.WEB3IpoOrderImpl;


/**
 * 管理者IPO抽選結果アップロード１件サービスインタフェイス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3AdminIpoLotResultUploadUnitService extends Service 
{
    
    /**
     * 抽選結果を更新する。
     * @@param l_orderList - IPO申告のArrayList
     * 
     * @@param l_isNewLottery - 新規抽選かどうかの判定<BR>
     * <BR>
     * 　@true：　@新規抽選<BR>
     * 　@false：　@繰上抽選
     * @@param l_administrator - 管理者オブジェクト
     * 
     * @@param l_mainAccount - 顧客オブジェクト
     * @@param l_strLotResult - 抽選結果
     * @@param l_dblElectedQuantity - 当選数量
     * @@param l_lngSubstitutePriority - 優先順位
     * @@roseuid 40F61BB00292
     */
    public void updateLotResult(
        ArrayList l_orderList, 
        boolean l_isNewLottery, 
        WEB3Administrator l_administrator, 
        MainAccount l_mainAccount, 
        String l_strLotResult, 
        double l_dblElectedQuantity, 
        Long l_lngSubstitutePriority) throws WEB3BaseException;
        
    /**
     * 抽選結果を更新する。<BR>
     * @@param l_order - IPO申告オブジェクト
     * 
     * @@param l_isNewLottery - 新規抽選かどうかの判定<BR>
     * <BR>
     * 　@true：　@新規抽選<BR>
     * 　@false：　@繰上抽選
     * @@param l_administrator - 管理者オブジェクト
     * 
     * @@param l_strLotResult - 抽選結果
     * @@param l_dblElectedQuantity - 当選数量
     * @@param l_lngSubstitutePriority - 優先順位
     * @@roseuid 40F64C8F02B1
     */
    public void updateLotResult(
        WEB3IpoOrderImpl l_order, 
        boolean l_isNewLottery, 
        WEB3Administrator l_administrator, 
        String l_strLotResult, 
        double l_dblElectedQuantity, 
        Long l_lngSubstitutePriority) throws WEB3BaseException;
}
@
