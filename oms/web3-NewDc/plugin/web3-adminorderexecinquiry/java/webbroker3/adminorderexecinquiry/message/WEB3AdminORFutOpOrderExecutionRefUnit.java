head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.45.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFutOpOrderExecutionRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ґ敨OP�������Ɖ�Unit (WEB3AdminORFutOpOrderExecutionRefUnit.java)
Author Name      : Daiwa Institute of Research
                 : 2006/10/18 ������ (���u) ���f��No.077
                   2006/12/19 �����F (���u) �d�l�ύX�E���f��087
*/
package webbroker3.adminorderexecinquiry.message;

/**
 * (�Ǘ��Ґ敨OP�������Ɖ�Unit)<BR>
 * <BR>
 * �Ǘ��Ґ敨OP�������Ɖ�Unit�N���X<BR>
 * <BR>
 * WEB3AdminORFutOpOrderExecutionRefUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORFutOpOrderExecutionRefUnit extends WEB3AdminOROrderExecutionRefCommon
{
    /**
     * (�����R�[�h)<BR>
     * <BR>
     * �����R�[�h<BR>
     * <BR>
     */
    public String productCode;

    /**
     * (������)<BR>
     * <BR>
     * ������<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

	/**
	 * (�s��R�[�h)<BR>
	 * <BR>
	 * �s��R�[�h<BR>
	 * <BR>
	 * marketCode<BR>
	 * <BR>
	 */
	public String marketCode;

    /**
     * (�����敪)<BR>
     * <BR>
     * �����敪<BR>
     * <BR>
     * 0�F�@@�I�v�V������������<BR>
     * 1�F�@@�敨�I�v�V��������<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * taxType<BR>
     * 0: Def.OPTIONS_LONG_ACCOUNT<BR>
     * 1: Def.FUTURES_OPTIONS_ACCOUNT<BR>
     * <BR>
     */
    public String taxType;

	/**
	 * (���������P���敪)<BR>
	 * <BR>
	 * ���������P���敪<BR>
	 * <BR>
	 * 0�F�@@�����Y����<BR>
	 * 1�F�@@�v���~�A��<BR>
	 * <BR>
	 * orderCondPriceDiv<BR>
	 * <BR>
	 * 0: Def.DEFAULT
	 * 1: Def.PREMIUM
	 * <BR>
	 */
	public String orderCondPriceDiv;

    /**
     * (�v�w�l�p���s����)<BR>
     * <BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s <BR>
     * <BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitExecCondType = null;

    /**
     * (�v�w�l�p�L����ԋ敪)<BR>
     * <BR>
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L���@@2�F�X�g�b�v���������� <BR>
     * <BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitEnableStatusDiv = null;

    /**
     * (�Ǘ��Ґ敨OP�������Ɖ�Unit)<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * WEB3AdminORFutOpOrderExecutionRefUnit<BR>
     * <BR>
     * constructor<BR>
     * <BR>
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefUnit
     * @@roseuid 41C787890134
     */
    public WEB3AdminORFutOpOrderExecutionRefUnit()
    {

    }
}
@
