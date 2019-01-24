/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : DOTBizDateProvider(DOTBizDateProvider.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/04/12 齋藤　栄三 (FLJ) 新規作成
*/
package jp.co.dir.dot.intellioms.rulesys;

import com.fitechlabs.fin.intellioms.rulesys.BizDateProvider;
import com.fitechlabs.fin.intellioms.util.Startable;

/**
 * DOTBizDateProvider
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTBizDateProvider extends BizDateProvider, Startable
{
    public static final String SHIFT_SYSTEM_TIMESTAMP = "shift.system.timestamp";

    public static final String SHIFT_SYSTEM_TIMESTAMP_MILLISECS = "shift.system.timestamp.millisecs";
    
    public static final String SYSTEM_BIZDATE = "system.bizdate";

    /**
     * getビジネス日付 <BR>
     * <BR>
     * ビジネス日付を取得する。 <BR>
     * <BR>
     * @return String
     */
    public String getBizDate();
}
