head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.01.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointAdjust.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント調整(WEB3PointAdjust.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/30 張学剛 (中訊) 新規作成
*/
package webbroker3.point;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.point.data.PointAdjustParams;

/**
 * (ポイント調整)<BR>
 * ポイント調整クラス<BR>
 * 
 * @@author 張学剛
 * @@version 1.0 
 */
public class WEB3PointAdjust implements BusinessObject 
{
    
    /**
     * (ポイント調整行)<BR>
     * ポイント調整行オブジェクト<BR>
     */
    private PointAdjustParams pointAdjustParams;
    
    /**
     * (ポイント調整)<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）空のポイント調整Paramsオブジェクトを生成し、this.ポイント調整行にセットする。<BR>
     * <BR>
     * ２）引数を、this.ポイント調整行の各項目にセットする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@param l_intAdjustPoint - (調整ポイント)<BR>
     * 調整ポイント<BR>
     * 
     * @@roseuid 419477A60000
     */
    public WEB3PointAdjust(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, int l_intAdjustPoint) 
    {
        //１）空のポイント調整Paramsオブジェクトを生成し、this.ポイント調整行にセットする。
        PointAdjustParams l_params = new PointAdjustParams();
        
        l_params.setInstitutionCode(l_strInstitutionCode);
        l_params.setBranchCode(l_strBranchCode);
        l_params.setAccountCode(l_strAccountCode);
        l_params.setAdjustPoint(l_intAdjustPoint);
        
        //２）引数を、this.ポイント調整行の各項目にセットする。
        this.pointAdjustParams = l_params;
    }
    
    /**
     * this.ポイント調整行を返却する。<BR>
     * @@return Object
     * @@roseuid 41947AF603D8
     */
    public Object getDataSourceObject() 
    {
        return this.pointAdjustParams;
    }
}@
