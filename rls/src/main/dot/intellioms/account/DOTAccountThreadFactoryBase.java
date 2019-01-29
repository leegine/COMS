/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �X���b�h���ƃA�J�E���g�����W�}�l�[�W���[�Ŏg�p����t�@�N�g��Base(DOTAccountThreadFactoryBase.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/09 �V���@�h�O(FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.account;

/**
 * �X���b�h���ƃA�J�E���g�����W�}�l�[�W���[�Ŏg�p����t�@�N�g��Base
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public abstract class DOTAccountThreadFactoryBase implements DOTAccountThreadFactory
{
    /**
     * @see DOTAccountThreadFactory#createAccountRangePerThread(long, long)
     */
    public AccountRangePerThread createAccountRangePerThread(long l_start, long l_end)
    {
        AccountRangePerThread l_accountRangePerThread = new AccountRangePerThread();
        l_accountRangePerThread.setAccountStart(l_start);
        l_accountRangePerThread.setAccountEnd(l_end);
        return l_accountRangePerThread;
    }
}
