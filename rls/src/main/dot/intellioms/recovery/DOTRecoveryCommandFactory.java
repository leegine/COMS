/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3RecoveryAsyncCommandFactoryクラス(WEB3RecoveryAsyncCommandFactory.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 堀野　和美(FLJ) 新規作成
 */

package jp.co.dir.dot.intellioms.recovery;

/**
 * 障害復旧処理コマンドファクトリ
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public interface DOTRecoveryCommandFactory 
{
    /**
     * 非同期処理コマンドを作成する。
     * @param name 非同期処理コマンド名
     * @return WEB3RecoveryAsyncCommand
     */
    DOTRecoveryCommand createCommand(String name);

}
