/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : スレッドごとアカウントレンジデータベースアダプター(DOTAccountThreadDbAdaptor.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/07 齋藤　栄三(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.account;

import java.util.List;

import com.fitechlabs.fin.intellioms.account.AccountIDRange;
import com.fitechlabs.fin.intellioms.persist.PersistException;

/**
 * スレッドごとアカウントレンジデータベースアダプター
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTAccountThreadDbAdaptor
{
    /**
     * DBに定義しているアカウントレンジを取得する。
     * 
     * @return AccountIDRange
     */
    public AccountIDRange getAccountIDRange(DOTAccountThreadDbParams l_context) throws PersistException;

    /**
     * スレッドごとアカウントレンジ設定を取得する。
     * 
     * @param スレッドごとアカウントレンジ
     * @return List
     */
    public List getAccountRangePerThread(DOTAccountThreadDbParams l_context) throws PersistException;
    
    /**
     * スレッドごとアカウントレンジ設定を削除する。
     * 
     * @param スレッドごとアカウントレンジ
     */
    public void deleteAccountRangePerThread(DOTAccountThreadDbParams l_context) throws PersistException;
    
    /**
     * スレッドごとアカウントレンジ設定を挿入する。
     * 
     * @param スレッドごとアカウントレンジ
     */
    public void insertAccountRangePerThread(DOTAccountThreadDbParams l_context) throws PersistException;
    
}