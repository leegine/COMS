/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3RecoveryAsyncCommandFactory�N���X(WEB3RecoveryAsyncCommandFactory.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 �x��@�a��(FLJ) �V�K�쐬
 */

package jp.co.dir.dot.intellioms.recovery;

/**
 * ��Q���������R�}���h�t�@�N�g��
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public interface DOTRecoveryCommandFactory 
{
    /**
     * �񓯊������R�}���h���쐬����B
     * @param name �񓯊������R�}���h��
     * @return WEB3RecoveryAsyncCommand
     */
    DOTRecoveryCommand createCommand(String name);

}
