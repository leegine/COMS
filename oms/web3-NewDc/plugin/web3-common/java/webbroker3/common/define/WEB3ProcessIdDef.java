head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.03.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ProcessIdDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �v���Z�X�h�c�萔��`�C���^�t�F�C�X(WEB3ProcessIdDef.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2009/03/04 ��іQ(���u) �V�K�쐬
Revision History : 2009/09/17 ��іQ(���u)�y���ʁz�d�l�ύX�Ǘ��䒠�c�a���C�A�E�gNo.697
Revision History : 2010/07/16 �����C(���u)�y���ʁz�d�l�ύX�Ǘ��䒠�c�a���C�A�E�gNo.711
Revision History : 2010/09/10 �����F(���u)�y���ʁz�d�l�ύX�Ǘ��䒠�c�a���C�A�E�gNo.716
*/

package webbroker3.common.define;

/**
 * �v���Z�X�h�c�萔��`�C���^�t�F�C�X<BR>
 * (�v���Z�X�Ǘ��e�[�u���̃v���Z�X�h�c�̙ҏ�)<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public interface WEB3ProcessIdDef
{
    /**
     * 0001:�؋����s���m��
     */
    public final static String DEPOSIT_SHORTAGE_CONFIRM = "0001";

    /**
     * 0008:���Z�l�����M
     */
    public final static String QUICK_REPORT_RECEIVED = "0008";

    /**
     * 0011�F������l�����M
     */
    public final static String NEXT_DAY_BASE_PRICE_QUICK_REPORT_REC = "0011";

    /**
     * 0012�F�����I�l�����M
     */
    public final static String EQ_LAST_PRICE_QUICK_REPORT_REC = "0012";

    /**
     * 0013�F�敨�n�o��l��M
     */
    public final static String IFO_BASE_PRICE_REC = "0013";

    /**
     * 0014:�O�������בփl�b�e�B���O��������
     */
    public final static String FEQ_NETTING_EXCHANGE_COMPLETE = "0014";
}
@
