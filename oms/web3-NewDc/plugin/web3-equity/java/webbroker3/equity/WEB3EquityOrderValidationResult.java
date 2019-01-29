head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderValidationResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������R������(WEB3EquityOrderValidationResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 �������F(SRA) �V�K�쐬
                   2005/01/05 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;

/**
 * �i���������R�����ʁj�B<BR>
 * <BR>
 * �ύX�����A��������Ȃǔ����R���̌��ʂ��������߂ɗ��p����N���X�B<BR>
 * �iEqTypeOrderValidationResult�̊g���N���X�j
 * @@version 1.0
 */
public class WEB3EquityOrderValidationResult extends EqTypeOrderValidationResult
{
    /**
     * �󔄂�K���Ώۃt���O�B<BR>
     */
    private boolean isShortSellingRestraint;

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �P�j�@@super�N���X�̃R���X�g���N�^���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����̋󔄂�K���Ώۃt���O���v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_result - (�����R������)
     * @@param isShortSellingRestraint - (�󔄂�K���Ώۃt���O)
     */
    public WEB3EquityOrderValidationResult(ProcessingResult l_result, boolean isShortSellingRestraint)
    {
        super(l_result);
        this.isShortSellingRestraint = isShortSellingRestraint;
    }

    /**
     * (set�󔄂�K���Ώۃt���O)<BR>
     * <BR>
     * �󔄂�K���Ώۃt���O���Z�b�g����B<BR>
     * <BR>
     * @@param isShortSellingRestraint - (�󔄂�K���Ώۃt���O)
     */
    public void setShortSellingRestraint(boolean isShortSellingRestraint)
    {
        this.isShortSellingRestraint = isShortSellingRestraint;
    }

    /**
     * (get�󔄂�K���Ώۃt���O)<BR>
     * <BR>
     * �󔄂�K���Ώۃt���O���擾����B<BR>
     * true�F�`�F�b�N����NG�i�K���Ώہj�Afalse�F�`�F�b�N����OK�i�K���ΏۊO�j<BR>
     * <BR>
     * @@return boolean
     */
    public boolean getShortSellingRestraint()
    {
        return this.isShortSellingRestraint;
    }
}
@
