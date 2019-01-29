/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3RecoveryConstantsクラス(DOTRecoveryConstants.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 堀野　和美(FLJ) 新規作成
 */

package jp.co.dir.dot.intellioms.recovery;

/**
 * 障害復旧パッケージで利用する定数定義
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public interface DOTRecoveryConstants 
{
    public final String CMD_RECOVERY_QUOTE_FROM_FILE = "web3.recovery.quote.from.file";    
    
    public final String CMD_RECOVERY_QUOTE_SINCE_FILE_LAST_UPDATED = "web3.recovery.quote.since.file.last.updated";    
    
    public final String CMD_RECOVERY_QUOTE_DURING_LOST_TIME = "web3.recovery.quote.during.lost.time";
    
}
