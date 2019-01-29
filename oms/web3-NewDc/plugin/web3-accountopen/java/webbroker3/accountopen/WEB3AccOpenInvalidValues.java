head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInvalidValues.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設不備(WEB3AccOpenInvalidValues.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/16 張威 (中訊) 新規作成
*/

package webbroker3.accountopen;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountopen.data.AccOpenInvalidValuesDao;
import webbroker3.accountopen.data.AccOpenInvalidValuesParams;
import webbroker3.accountopen.data.AccOpenInvalidValuesRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設不備)<BR>
 * 口座開設不備<BR>
 *                                                         
 * @@author 張威
 * @@version 1.0
 */
public class WEB3AccOpenInvalidValues implements BusinessObject 
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenInvalidValues.class);

    /**
     * (口座開設不備行)<BR>
     * 口座開設不備行オブジェクト<BR>
     * <BR>
     * ※ 口座開設不備ParamsクラスはDDLより自動生成する。<BR>
     */
    private AccOpenInvalidValuesParams accOpenInvalidValuesParams;
    
    /**
     * @@roseuid 41B45E6B008C
     */
    public WEB3AccOpenInvalidValues() 
    {
     
    }
    
    /**
     * (口座開設不備)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 指定行オブジェクトをプロパティにセットし、インスタンスを生成する。 <BR>
     * <BR>
     * ※ 口座開設不備ParamsクラスはDDLより自動生成する。<BR>
     * @@param l_accOpenInvalidValuesParams - 口座開設不備行オブジェクト
     * 
     * @@return webbroker3.accountopen.WEB3AccOpenInvalidValues
     * @@roseuid 4191B4D00009
     */
    public WEB3AccOpenInvalidValues(AccOpenInvalidValuesParams l_accOpenInvalidValuesParams) 
    {
        this.accOpenInvalidValuesParams = l_accOpenInvalidValuesParams;
    }
    
    /**
     * (口座開設不備)<BR>
     * コンストラクタ<BR>
     * 口座開設不備オブジェクトを生成する。<BR>
     * <BR>
     * 以下の条件で口座開設不備テーブルを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@口座開設不備.証券会社コード = 証券会社コード<BR>
     * 　@口座開設不備.識別コード = 識別コード<BR>
     * <BR>
     * 検索結果の口座開設不備行オブジェクトを引数に指定して、<BR>
     * コンストラクタをコールする。 <BR>
     * コンストラクタの戻り値を返却する。 <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strRequestNumber - 識別コード
     * @@return webbroker3.accountopen.WEB3AccOpenInvalidValues
     * @@roseuid 4191B47C0330
     */
    public WEB3AccOpenInvalidValues(String l_strInstitutionCode, String l_strRequestNumber) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = " WEB3AccOpenInvalidValues(String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            AccOpenInvalidValuesRow l_accOpenInvalidValuesRow = AccOpenInvalidValuesDao.findRowByPk(l_strInstitutionCode, l_strRequestNumber);
            
            this.accOpenInvalidValuesParams = new AccOpenInvalidValuesParams(l_accOpenInvalidValuesRow);
        }
        catch (DataFindException l_ex)
        {
            throw new NotFoundException("検索結果に一致する行が存在しない");
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBアクセスが失敗の場合");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBアクセスが失敗の場合");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createForUpdateParams)<BR>
     * 　@this.口座開設不備行をコピーして、同じ内容の別インスタンスを作成する（clone）。 <BR>
     * 作成したコピーを自身のthis.口座開設不備行にセットする。 <BR>
     * @@roseuid 4191B9C50004
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        AccOpenInvalidValuesParams l_accOpenInvalidValuesParams = new AccOpenInvalidValuesParams(this.accOpenInvalidValuesParams);
        this.accOpenInvalidValuesParams = l_accOpenInvalidValuesParams;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get不備項目名)<BR>
     * 指定のindexに該当する不備項目名を取得する。<BR>
     * <BR>
     * ※ 引数indexは、不備項目の列名になっている番号を示す。<BR>
     * 　@例えば、（index=1）の場合は、this.口座開設不備行.不備項目名１ を<BR>
     * 返却する。<BR>
     * @@param l_intIndex - index（不備項目の番号）<BR>
     * <BR>
     * ※ 1～10の範囲で指定すること<BR>
     * 
     * @@return String
     * @@roseuid 4191B794017B
     */
    public String getInvalidItemName(int l_intIndex) 
    {
        final String STR_METHOD_NAME = " getInvalidItemName(int)";
        log.entering(STR_METHOD_NAME);
        
        if (l_intIndex < 1 || l_intIndex > 10)
        {
            log.debug("field 'item_name" + l_intIndex + "' not found.");
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("field 'item_name" + l_intIndex + "' not found.");
        }

        log.debug("不備項目名" + l_intIndex + "を取得する。");
        String l_strItem = (String)this.accOpenInvalidValuesParams.getColumn("item_name" + l_intIndex);
        
        log.exiting(STR_METHOD_NAME);
        return l_strItem;
    }
    
    /**
     * (getコメント)<BR>
     * 指定のindexに該当するコメントを取得する。<BR>
     * <BR>
     * ※ 引数indexは、不備項目の列名になっている番号を示す。<BR>
     * 　@例えば、（index=1）の場合は、this.口座開設不備行.コメント１ を返却する。<BR>
     * @@param l_intIndex - index（不備項目の番号）<BR>
     * <BR>
     * ※ 1～10の範囲で指定すること<BR>
     * 
     * @@return String
     * @@roseuid 4191B8AC00FE
     */
    public String getComment(int l_intIndex) 
    {
        final String STR_METHOD_NAME = " getComment(int)";
        log.entering(STR_METHOD_NAME);
        
        if (l_intIndex < 1 || l_intIndex > 10)
        {
            log.debug("field 'comment" + l_intIndex + "' not found.");
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("field 'comment" + l_intIndex + "' not found.");
        }

        log.debug("コメント" + l_intIndex + "を取得する。");
        String l_strComment = (String)this.accOpenInvalidValuesParams.getColumn("comment" + l_intIndex);
        
        log.exiting(STR_METHOD_NAME);
        return l_strComment;
    }
    
    /**
     * (is完了)<BR>
     * 指定のindexに該当する不備項目が完了しているかを判定する。<BR>
     * <BR>
     * 不備項目が登録されている場合、完了フラグの状態を返却する。<BR>
     * 但し、不備項目が登録されていないindexの場合は、完了していると<BR>
     * 判断する（trueを返却する）。<BR>
     * <BR>
     * 指定indexに不備が登録されていない場合（this.is不備項目登録(index) == false）、<BR>
     * trueを返却する。<BR>
     * 以外、indexに該当する完了フラグの内容を対応するboolean値にて返却する。<BR>
     * <BR>
     * ※ 引数indexは、不備項目の列名になっている番号を示す。<BR>
     * 　@例えば、（index=1）が指定された場合、this.口座開設不備行.完了フラグ１が<BR>
     * 示すboolean値を返却する。<BR>
     * @@param l_intIndex - index（不備項目の番号）<BR>
     * <BR>
     * ※ 1～10の範囲で指定すること<BR>
     * 
     * @@return boolean
     * @@roseuid 4191B8CF019A
     */
    public boolean isComplete(int l_intIndex) 
    {
        final String STR_METHOD_NAME = " isComplete(int)";
        log.entering(STR_METHOD_NAME);

        if (l_intIndex < 1 || l_intIndex > 10)
        {
            log.debug("field 'comp_flag" + l_intIndex + "' not found.");
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("field 'comp_flag" + l_intIndex + "' not found.");
        }

        boolean l_blnFlag = false;
        
        log.debug("不備項目が完了" + l_intIndex + "しているかを判定する。");
        BooleanEnum l_compFlag = (BooleanEnum)this.accOpenInvalidValuesParams.getColumn("comp_flag" + l_intIndex);
        
        if (BooleanEnum.TRUE.equals(l_compFlag))
        {
            log.debug("不備項目が完了" + l_intIndex + ": " + l_compFlag);
            l_blnFlag = true;
        }
        else if (BooleanEnum.FALSE.equals(l_compFlag))
        {
            log.debug("不備項目が完了" + l_intIndex + ": " + l_compFlag);
            l_blnFlag = false;
        }
        else
        {
            log.debug("不備項目が完了" + l_intIndex + ": " + l_compFlag);
            l_blnFlag = true;
        }

        if (!this.isInvalidItemRegist(l_intIndex))
        {
            log.debug("指定indexに不備が登録されていない場合（this.is不備項目登録(index) == false）、trueを返却する。");
            l_blnFlag = true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_blnFlag;
    }
    
    /**
     * (is完了)<BR>
     * 不備項目がすべて完了しているかを判定する。<BR>
     * <BR>
     * this.is完了(index)を10回コールする。（indexには、1～10を指定する）<BR>
     * 一つでもfalseｆが返却された場合、falseを返却する。<BR>
     * すべてのindexでtrueが返却された場合、trueを返却する。<BR>
     * @@return boolean
     * @@roseuid 419448320206
     */
    public boolean isComplete() 
    {
        final String STR_METHOD_NAME = " isComplete()";
        log.entering(STR_METHOD_NAME);
        
        for (int i = 1; i <= 10; i++)
        {
            if (!this.isComplete(i))
            {
                log.debug("index " + i + " false returned");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        log.debug("すべてのindexでtrueが返却された場合、trueを返却する。");
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (get備考１)<BR>
     * this.口座開設不備行.備考１を返却する。<BR>
     * @@return String
     * @@roseuid 4191B92B010E
     */
    public String getNote1() 
    {
        final String STR_METHOD_NAME = " getNote1()";
        log.entering(STR_METHOD_NAME);
        
        String l_strNote1 = this.accOpenInvalidValuesParams.getNote1();

        log.exiting(STR_METHOD_NAME);
        return l_strNote1;
    }
    
    /**
     * (get備考２)<BR>
     * this.口座開設不備行.備考２を返却する。<BR>
     * @@return String
     * @@roseuid 4191B93D010E
     */
    public String getNote2() 
    {
        final String STR_METHOD_NAME = " getNote2()";
        log.entering(STR_METHOD_NAME);
        
        String l_strNote2 = this.accOpenInvalidValuesParams.getNote2();

        log.exiting(STR_METHOD_NAME);
        return l_strNote2;
    }
    
    /**
     * (set不備項目)<BR>
     * 指定のindexに該当する不備項目（不備項目名、コメント、完了フラグ）をセットする。<BR>
     * <BR>
     * ※ 引数indexは、不備項目の列名になっている番号を示す。<BR>
     * 　@例えば、（index=1）の場合は、以下の通りセットを行う。<BR>
     * <BR>
     * 　@this.口座開設不備行.不備項目名１ = 不備項目名<BR>
     * 　@this.口座開設不備行.コメント１ = コメント<BR>
     * 　@this.口座開設不備行.完了フラグ１ = ※is完了()に該当するBooleanEnum値<BR>
     * @@param l_intIndex - index（不備項目の番号）<BR>
     * <BR>
     * ※ 1～10の範囲で指定すること<BR>
     * 
     * @@param l_strInvalidItemName - 不備項目名
     * @@param l_strComment - コメント
     * @@param l_blnIsComplete - 不備の修正が完了しているかの判定
     * @@roseuid 4191B9FD0052
     */
    public void setInvalidItem(int l_intIndex, String l_strInvalidItemName, String l_strComment, boolean l_blnIsComplete) 
    {
        final String STR_METHOD_NAME = " setInvalidItem(int, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_intIndex < 1 || l_intIndex > 10)
        {
            log.debug("Index " + l_intIndex + " is not a legal index number.");
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("Index " + l_intIndex + " is not a legal index number.");
        }
        
        log.debug("\n不備項目名: " + l_strInvalidItemName + "\nコメント: " + l_strComment + "\n不備の修正が完了しているかの判定 " + l_blnIsComplete);
        this.accOpenInvalidValuesParams.setColumn("item_name" + l_intIndex, l_strInvalidItemName);
        this.accOpenInvalidValuesParams.setColumn("comment" + l_intIndex, l_strComment);
        this.accOpenInvalidValuesParams.setColumn("comp_flag" + l_intIndex, l_blnIsComplete ? BooleanEnum.TRUE : BooleanEnum.FALSE);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set備考)<BR>
     * 備考１、２をセットする。<BR>
     * <BR>
     * this.口座開設不備行.備考１ = 備考１<BR>
     * this.口座開設不備行.備考２ = 備考２<BR>
     * @@param l_strNote1 - 備考１
     * @@param l_strNote2 - 備考２
     * @@roseuid 41949948006F
     */
    public void setNote(String l_strNote1, String l_strNote2) 
    {
        final String STR_METHOD_NAME = " setNote(String, String)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("\n備考１: " + l_strNote1 + "\n備考２: " + l_strNote2);
        this.accOpenInvalidValuesParams.setNote1(l_strNote1);
        this.accOpenInvalidValuesParams.setNote2(l_strNote2);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is不備項目登録)<BR>
     * 指定のindexに該当する不備項目（不備項目名、コメント、完了フラグ）が<BR>
     * 登録されているかを判定する。<BR>
     * <BR>
     * －指定のindexに対応する不備項目名，コメントが両方ともnullであれば、<BR>
     * 不備項目未登録と判定しfalseを返却する。<BR>
     * －指定のindexに対応する不備項目名，コメントのどちらかがnullでない場合ば、<BR>
     * 不備項目登録済みと判定しtrueを返却する。<BR>
     * <BR>
     * ※ 引数indexは、不備項目の列名になっている番号を示す。<BR>
     * 　@例えば、（index=1）の場合は、以下の判定を行う。<BR>
     * <BR>
     * 　@if (this.口座開設不備行.不備項目名１ == null) && <BR>
     * (this.口座開設不備行.コメント１ == null) {<BR>
     * 　@　@return false;<BR>
     * 　@} else {<BR>
     * 　@　@return true;<BR>
     * 　@}<BR>
     * @@param l_intIndex - index（不備項目の番号）<BR>
     * <BR>
     * ※ 1～10の範囲で指定すること<BR>
     * 
     * @@return boolean
     * @@roseuid 4191BF6C0033
     */
    public boolean isInvalidItemRegist(int l_intIndex) 
    {
        final String STR_METHOD_NAME = " isInvalidItemRegist(int)";
        log.entering(STR_METHOD_NAME);

        if (l_intIndex < 1 || l_intIndex > 10)
        {
            log.debug("Index " + l_intIndex + " is not a legal index number.");
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("Index " + l_intIndex + " is not a legal index number.");
        }

        if (this.accOpenInvalidValuesParams.getColumn("item_name" + l_intIndex) == null && this.accOpenInvalidValuesParams.getColumn("comment" + l_intIndex) == null)
        {
            log.debug("指定のindexに対応する不備項目名，コメントが両方ともnullであれば、不備項目未登録と判定しfalseを返却する。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.debug("指定のindexに対応する不備項目名，コメントのどちらかがnullでない場合ば、不備項目登録済みと判定しtrueを返却する。");
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (save口座開設不備)<BR>
     * 口座開設不備テーブルを更新する。<BR>
     * <BR>
     * １） 口座開設不備行オブジェクト取得 <BR>
     * 　@口座開設不備.getDataSourceObject()にて口座開設不備行を取得する。<BR> 
     * <BR>
     * ２） 更新情報をセットする。 <BR>
     * 　@口座開設不備行の入力データ以外の項目に値をセットする。 <BR>
     * <BR>
     * 　@更新者コード：　@更新者コード<BR>
     * 　@更新日時：　@TradingSystem.getSystemTimestamp() <BR>
     * <BR>
     * ３） DB更新 <BR>
     * 　@口座開設不備行オブジェクトの内容で、口座開設不備テーブルを更新する。<BR> 
     * 　@証券会社コード，識別コードに該当行が既に存在する場合はUpdate，<BR>
     * 　@存在しない場合はInsertにて更新する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strRequestNumber - 識別コード
     * @@param l_strUpdaterCode - 更新者コード
     * @@roseuid 4194867B008F
     */
    public void saveAccOpenInvalidValues(String l_strInstitutionCode, String l_strRequestNumber, String l_strUpdaterCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " saveAccOpenInvalidValues(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１） 口座開設不備行オブジェクト取得
        AccOpenInvalidValuesParams l_accOpenInvalidValuesParams = (AccOpenInvalidValuesParams)this.getDataSourceObject();
        
        //２） 更新情報をセットする。
        Timestamp l_tsForSetting = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        l_accOpenInvalidValuesParams.setLastUpdater(l_strUpdaterCode);
        l_accOpenInvalidValuesParams.setLastUpdatedTimestamp(l_tsForSetting);
        
        try
        {
            //３） DB更新
            AccOpenInvalidValuesRow l_accOpenInvalidValuesRow = 
                AccOpenInvalidValuesDao.findRowByInstitutionCodeAccOpenRequestNumber(l_strInstitutionCode,
                l_strRequestNumber); //DataNetworkException, DataQueryException

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor(); //DataNetworkException, DataFindException
            
            if (l_accOpenInvalidValuesRow != null)
            {
                log.debug("証券会社コード，識別コードに該当行が既に存在する場合はUpdateする。");
                l_queryProcessor.doUpdateQuery(l_accOpenInvalidValuesParams); //DataNetworkException, DataQueryException
            }
            else
            {
                l_accOpenInvalidValuesParams.setCreatedTimestamp(l_tsForSetting);
                log.debug("証券会社コード，識別コードに該当行が存在しない場合はInsertにて更新する。");
                l_queryProcessor.doInsertQuery(l_accOpenInvalidValuesParams); //DataNetworkException, DataQueryException
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBアクセスが失敗の場合");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBアクセスが失敗の場合");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return Object
     * @@roseuid 41B45E6B00EA
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return this.accOpenInvalidValuesParams;   
    }
}
@
